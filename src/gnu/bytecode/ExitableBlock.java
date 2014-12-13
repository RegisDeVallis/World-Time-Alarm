// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;


// Referenced classes of package gnu.bytecode:
//            CodeAttr, Label, TryState, Type, 
//            Variable

public class ExitableBlock
{

    CodeAttr code;
    TryState currentTryState;
    Label endLabel;
    TryState initialTryState;
    ExitableBlock nextCase;
    ExitableBlock outer;
    Type resultType;
    Variable resultVariable;
    int switchCase;

    ExitableBlock(Type type, CodeAttr codeattr, boolean flag)
    {
        code = codeattr;
        resultType = type;
        initialTryState = codeattr.try_stack;
        if (flag && type != null)
        {
            codeattr.pushScope();
            Variable variable = codeattr.addLocal(type);
            resultVariable = variable;
            codeattr.emitStoreDefaultValue(variable);
            int i = 1 + codeattr.exitableBlockLevel;
            codeattr.exitableBlockLevel = i;
            switchCase = i;
        }
        endLabel = new Label(codeattr);
    }

    public void exit()
    {
        if (resultVariable != null)
        {
            code.emitStore(resultVariable);
        }
        exit(TryState.outerHandler(code.try_stack, initialTryState));
    }

    void exit(TryState trystate)
    {
        if (trystate == initialTryState)
        {
            code.emitGoto(endLabel);
            return;
        }
        if (code.useJsr())
        {
            for (TryState trystate1 = code.try_stack; trystate1 != initialTryState; trystate1 = trystate1.previous)
            {
                if (trystate1.finally_subr != null && trystate1.finally_ret_addr == null)
                {
                    code.emitJsr(trystate1.finally_subr);
                }
            }

            code.emitGoto(endLabel);
            return;
        }
        if (currentTryState == null)
        {
            linkCase(trystate);
        }
        if (trystate.saved_result != null)
        {
            code.emitStoreDefaultValue(trystate.saved_result);
        }
        code.emitPushInt(switchCase);
        code.emitPushNull();
        code.emitGoto(trystate.finally_subr);
    }

    public Label exitIsGoto()
    {
        if (TryState.outerHandler(code.try_stack, initialTryState) == initialTryState)
        {
            return endLabel;
        } else
        {
            return null;
        }
    }

    void finish()
    {
        if (resultVariable != null && code.reachableHere())
        {
            code.emitStore(resultVariable);
        }
        endLabel.define(code);
        if (resultVariable != null)
        {
            code.emitLoad(resultVariable);
            code.popScope();
            CodeAttr codeattr = code;
            codeattr.exitableBlockLevel = -1 + codeattr.exitableBlockLevel;
        }
    }

    void linkCase(TryState trystate)
    {
        if (currentTryState != trystate)
        {
            if (currentTryState != null)
            {
                throw new Error();
            }
            nextCase = trystate.exitCases;
            trystate.exitCases = this;
            currentTryState = trystate;
        }
    }
}
