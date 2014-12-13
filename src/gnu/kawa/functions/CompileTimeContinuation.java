// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.bytecode.ExitableBlock;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.ProcedureN;

// Referenced classes of package gnu.kawa.functions:
//            AppendValues

class CompileTimeContinuation extends ProcedureN
    implements Inlineable
{

    Target blockTarget;
    ExitableBlock exitableBlock;

    CompileTimeContinuation()
    {
    }

    public Object applyN(Object aobj[])
        throws Throwable
    {
        throw new Error("internal error");
    }

    public void compile(ApplyExp applyexp, Compilation compilation, Target target)
    {
        compilation.getCode();
        Expression aexpression[] = applyexp.getArgs();
        int i = aexpression.length;
        boolean flag;
        if ((blockTarget instanceof IgnoreTarget) || (blockTarget instanceof ConsumerTarget))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            target.getType();
        }
        if (flag || i == 1)
        {
            for (int j = 0; j < i; j++)
            {
                aexpression[j].compileWithPosition(compilation, blockTarget);
            }

        } else
        {
            AppendValues appendvalues = AppendValues.appendValues;
            appendvalues.compile(new ApplyExp(appendvalues, aexpression), compilation, blockTarget);
        }
        exitableBlock.exit();
    }

    public Type getReturnType(Expression aexpression[])
    {
        return Type.neverReturnsType;
    }
}
