// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package gnu.bytecode:
//            Attribute, CodeAttr, Method, VarEnumerator, 
//            Variable, ClassType, ConstantPool, CpoolUtf8, 
//            Type, Scope, ClassTypeWriter, Label

public class LocalVarsAttr extends Attribute
{

    public Scope current_scope;
    private Method method;
    Scope parameter_scope;
    Variable used[];

    public LocalVarsAttr(CodeAttr codeattr)
    {
        super("LocalVariableTable");
        addToFrontOf(codeattr);
        method = (Method)codeattr.getContainer();
        codeattr.locals = this;
    }

    public LocalVarsAttr(Method method1)
    {
        super("LocalVariableTable");
        CodeAttr codeattr = method1.code;
        method = method1;
        codeattr.locals = this;
    }

    public VarEnumerator allVars()
    {
        return new VarEnumerator(parameter_scope);
    }

    public void assignConstants(ClassType classtype)
    {
        super.assignConstants(classtype);
        VarEnumerator varenumerator = allVars();
        do
        {
            Variable variable = varenumerator.nextVar();
            if (variable == null)
            {
                break;
            }
            if (variable.isSimple() && variable.name != null)
            {
                if (variable.name_index == 0)
                {
                    variable.name_index = classtype.getConstants().addUtf8(variable.getName()).index;
                }
                if (variable.signature_index == 0)
                {
                    variable.signature_index = classtype.getConstants().addUtf8(variable.getType().getSignature()).index;
                }
            }
        } while (true);
    }

    public void enterScope(Scope scope)
    {
        CodeAttr codeattr;
        Variable variable;
        scope.linkChild(current_scope);
        current_scope = scope;
        codeattr = method.getCode();
        variable = scope.firstVar();
_L1:
        if (variable == null)
        {
            break MISSING_BLOCK_LABEL_139;
        }
        if (variable.isSimple())
        {
            if (!variable.isAssigned())
            {
                variable.allocateLocal(codeattr);
            } else
            {
                if (used[variable.offset] != null)
                {
                    continue; /* Loop/switch isn't completed */
                }
                used[variable.offset] = variable;
            }
        }
_L3:
        variable = variable.nextVar();
          goto _L1
        if (used[variable.offset] == variable) goto _L3; else goto _L2
_L2:
        throw new Error((new StringBuilder()).append("inconsistent local variable assignments for ").append(variable).append(" != ").append(used[variable.offset]).toString());
    }

    public final int getCount()
    {
        int i = 0;
        VarEnumerator varenumerator = allVars();
        do
        {
            Variable variable = varenumerator.nextVar();
            if (variable == null)
            {
                break;
            }
            if (variable.shouldEmit())
            {
                i++;
            }
        } while (true);
        return i;
    }

    public final int getLength()
    {
        return 2 + 10 * getCount();
    }

    public final Method getMethod()
    {
        return method;
    }

    public final boolean isEmpty()
    {
        VarEnumerator varenumerator = allVars();
        do
        {
            Variable variable = varenumerator.nextVar();
            if (variable != null)
            {
                if (variable.isSimple() && variable.name != null)
                {
                    return false;
                }
            } else
            {
                return true;
            }
        } while (true);
    }

    public void preserveVariablesUpto(Scope scope)
    {
        for (Scope scope1 = current_scope; scope1 != scope; scope1 = scope1.parent)
        {
            scope1.preserved = true;
        }

    }

    public void print(ClassTypeWriter classtypewriter)
    {
        VarEnumerator varenumerator;
        varenumerator = allVars();
        classtypewriter.print("Attribute \"");
        classtypewriter.print(getName());
        classtypewriter.print("\", length:");
        classtypewriter.print(getLength());
        classtypewriter.print(", count: ");
        classtypewriter.println(getCount());
        varenumerator.reset();
_L9:
        Variable variable = varenumerator.nextVar();
        if (variable == null) goto _L2; else goto _L1
_L1:
        Scope scope;
        if (!variable.isSimple() || variable.name == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        classtypewriter.print("  slot#");
        classtypewriter.print(variable.offset);
        classtypewriter.print(": name: ");
        classtypewriter.printOptionalIndex(variable.name_index);
        classtypewriter.print(variable.getName());
        classtypewriter.print(", type: ");
        classtypewriter.printOptionalIndex(variable.signature_index);
        classtypewriter.printSignature(variable.getType());
        classtypewriter.print(" (pc: ");
        scope = variable.scope;
        if (scope == null || scope.start == null || scope.end == null) goto _L4; else goto _L3
_L3:
        int i = scope.start.position;
        if (i < 0) goto _L4; else goto _L5
_L5:
        int j = scope.end.position;
        if (j >= 0) goto _L6; else goto _L4
_L4:
        classtypewriter.print("unknown");
_L7:
        classtypewriter.println(')');
        continue; /* Loop/switch isn't completed */
_L6:
        classtypewriter.print(i);
        classtypewriter.print(" length: ");
        classtypewriter.print(j - i);
        if (true) goto _L7; else goto _L2
_L2:
        return;
        if (true) goto _L9; else goto _L8
_L8:
    }

    public void write(DataOutputStream dataoutputstream)
        throws IOException
    {
        VarEnumerator varenumerator = allVars();
        dataoutputstream.writeShort(getCount());
        varenumerator.reset();
        do
        {
            Variable variable = varenumerator.nextVar();
            if (variable == null)
            {
                break;
            }
            if (variable.shouldEmit())
            {
                Scope scope = variable.scope;
                int i = scope.start.position;
                int j = scope.end.position;
                dataoutputstream.writeShort(i);
                dataoutputstream.writeShort(j - i);
                dataoutputstream.writeShort(variable.name_index);
                dataoutputstream.writeShort(variable.signature_index);
                dataoutputstream.writeShort(variable.offset);
            }
        } while (true);
    }
}
