// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.TypeValue;
import gnu.lists.AbstractSequence;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Referenced classes of package gnu.kawa.xml:
//            NodeType, KProcessingInstruction

public class ProcessingInstructionType extends NodeType
    implements TypeValue, Externalizable
{

    static final Method coerceMethod;
    static final Method coerceOrNullMethod;
    public static final ProcessingInstructionType piNodeTest = new ProcessingInstructionType(null);
    public static final ClassType typeProcessingInstructionType;
    String target;

    public ProcessingInstructionType(String s)
    {
        String s1;
        if (s == null)
        {
            s1 = "processing-instruction()";
        } else
        {
            s1 = (new StringBuilder()).append("processing-instruction(").append(s).append(")").toString();
        }
        super(s1);
        target = s;
    }

    public static KProcessingInstruction coerce(Object obj, String s)
    {
        KProcessingInstruction kprocessinginstruction = coerceOrNull(obj, s);
        if (kprocessinginstruction == null)
        {
            throw new ClassCastException();
        } else
        {
            return kprocessinginstruction;
        }
    }

    public static KProcessingInstruction coerceOrNull(Object obj, String s)
    {
        KProcessingInstruction kprocessinginstruction = (KProcessingInstruction)NodeType.coerceOrNull(obj, 32);
        if (kprocessinginstruction != null && (s == null || s.equals(kprocessinginstruction.getTarget())))
        {
            return kprocessinginstruction;
        } else
        {
            return null;
        }
    }

    public static ProcessingInstructionType getInstance(String s)
    {
        if (s == null)
        {
            return piNodeTest;
        } else
        {
            return new ProcessingInstructionType(s);
        }
    }

    public Object coerceFromObject(Object obj)
    {
        return coerce(obj, target);
    }

    public void emitCoerceFromObject(CodeAttr codeattr)
    {
        codeattr.emitPushString(target);
        codeattr.emitInvokeStatic(coerceMethod);
    }

    protected void emitCoerceOrNullMethod(Variable variable, Compilation compilation)
    {
        CodeAttr codeattr = compilation.getCode();
        if (variable != null)
        {
            codeattr.emitLoad(variable);
        }
        codeattr.emitPushString(target);
        codeattr.emitInvokeStatic(coerceOrNullMethod);
    }

    public Type getImplementationType()
    {
        return ClassType.make("gnu.kawa.xml.KProcessingInstruction");
    }

    public boolean isInstance(Object obj)
    {
        return coerceOrNull(obj, target) != null;
    }

    public boolean isInstancePos(AbstractSequence abstractsequence, int i)
    {
        int j = abstractsequence.getNextKind(i);
        if (j != 37) goto _L2; else goto _L1
_L1:
        boolean flag;
label0:
        {
            if (target != null)
            {
                boolean flag1 = target.equals(abstractsequence.getNextTypeObject(i));
                flag = false;
                if (!flag1)
                {
                    break label0;
                }
            }
            flag = true;
        }
_L4:
        return flag;
_L2:
        flag = false;
        if (j == 32)
        {
            return isInstance(abstractsequence.getPosNext(i));
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        target = (String)objectinput.readObject();
    }

    public String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("ProcessingInstructionType ");
        String s;
        if (target == null)
        {
            s = "*";
        } else
        {
            s = target;
        }
        return stringbuilder.append(s).toString();
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(target);
    }

    static 
    {
        typeProcessingInstructionType = ClassType.make("gnu.kawa.xml.ProcessingInstructionType");
        coerceMethod = typeProcessingInstructionType.getDeclaredMethod("coerce", 2);
        coerceOrNullMethod = typeProcessingInstructionType.getDeclaredMethod("coerceOrNull", 2);
    }
}
