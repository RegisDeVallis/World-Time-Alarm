// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.reflect.OccurrenceType;

// Referenced classes of package gnu.expr:
//            Target, IgnoreTarget, Expression, Compilation, 
//            StackTarget

public class ConsumerTarget extends Target
{

    Variable consumer;
    boolean isContextTarget;

    public ConsumerTarget(Variable variable)
    {
        consumer = variable;
    }

    public static void compileUsingConsumer(Expression expression, Compilation compilation, Target target)
    {
        if ((target instanceof ConsumerTarget) || (target instanceof IgnoreTarget))
        {
            expression.compile(compilation, target);
            return;
        } else
        {
            ClassType classtype = Compilation.typeValues;
            compileUsingConsumer(expression, compilation, target, classtype.getDeclaredMethod("make", 0), classtype.getDeclaredMethod("canonicalize", 0));
            return;
        }
    }

    public static void compileUsingConsumer(Expression expression, Compilation compilation, Target target, Method method, Method method1)
    {
        CodeAttr codeattr = compilation.getCode();
        Scope scope = codeattr.pushScope();
        Object obj;
        Variable variable;
        ConsumerTarget consumertarget;
        if (method.getName() == "<init>")
        {
            ClassType classtype = method.getDeclaringClass();
            obj = classtype;
            codeattr.emitNew(classtype);
            codeattr.emitDup(((Type) (obj)));
            codeattr.emitInvoke(method);
        } else
        {
            obj = method.getReturnType();
            codeattr.emitInvokeStatic(method);
        }
        variable = scope.addVariable(codeattr, ((Type) (obj)), null);
        consumertarget = new ConsumerTarget(variable);
        codeattr.emitStore(variable);
        expression.compile(compilation, consumertarget);
        codeattr.emitLoad(variable);
        if (method1 != null)
        {
            codeattr.emitInvoke(method1);
        }
        codeattr.popScope();
        if (method1 != null)
        {
            obj = method1.getReturnType();
        }
        target.compileFromStack(compilation, ((Type) (obj)));
    }

    public static Target makeContextTarget(Compilation compilation)
    {
        CodeAttr codeattr = compilation.getCode();
        compilation.loadCallContext();
        codeattr.emitGetField(Compilation.typeCallContext.getDeclaredField("consumer"));
        Variable variable = codeattr.getCurrentScope().addVariable(codeattr, Compilation.typeConsumer, "$result");
        codeattr.emitStore(variable);
        ConsumerTarget consumertarget = new ConsumerTarget(variable);
        consumertarget.isContextTarget = true;
        return consumertarget;
    }

    public void compileFromStack(Compilation compilation, Type type)
    {
        compileFromStack(compilation, type, -1);
    }

    void compileFromStack(Compilation compilation, Type type, int i)
    {
        CodeAttr codeattr;
        Type type1;
        String s;
        Object obj;
        boolean flag;
        char c;
        codeattr = compilation.getCode();
        type1 = type.getImplementationType();
        if (!(type1 instanceof PrimType))
        {
            break MISSING_BLOCK_LABEL_282;
        }
        c = type1.getSignature().charAt(0);
        flag = false;
        obj = null;
        s = null;
        c;
        JVM INSTR lookupswitch 9: default 124
    //                   66: 192
    //                   67: 252
    //                   68: 237
    //                   70: 222
    //                   73: 192
    //                   74: 207
    //                   83: 192
    //                   86: 191
    //                   90: 267;
           goto _L1 _L2 _L3 _L4 _L5 _L2 _L6 _L2 _L7 _L8
_L1:
        Method method;
        Method method1;
        if (i < 0)
        {
            if (flag)
            {
                codeattr.pushScope();
                Variable variable = codeattr.addLocal(type1);
                codeattr.emitStore(variable);
                codeattr.emitLoad(consumer);
                codeattr.emitLoad(variable);
                codeattr.popScope();
            } else
            {
                codeattr.emitLoad(consumer);
                codeattr.emitSwap();
            }
        }
        method = null;
        if (true)
        {
            method = null;
            if (s != null)
            {
                Type atype[] = {
                    obj
                };
                method = Compilation.typeConsumer.getDeclaredMethod(s, atype);
            }
        }
        if (method != null)
        {
            codeattr.emitInvokeInterface(method);
        }
        if (c == 'C')
        {
            codeattr.emitPop(1);
        }
_L7:
        return;
_L2:
        s = "writeInt";
        obj = Type.intType;
        flag = false;
          goto _L1
_L6:
        s = "writeLong";
        obj = Type.longType;
        flag = true;
          goto _L1
_L5:
        s = "writeFloat";
        obj = Type.floatType;
        flag = false;
          goto _L1
_L4:
        s = "writeDouble";
        obj = Type.doubleType;
        flag = true;
          goto _L1
_L3:
        s = "append";
        obj = Type.charType;
        flag = false;
          goto _L1
_L8:
        s = "writeBoolean";
        obj = Type.booleanType;
        flag = false;
          goto _L1
        if (i == 1 || OccurrenceType.itemCountIsOne(type1))
        {
            s = "writeObject";
            obj = Type.pointer_type;
            flag = false;
            c = '\0';
        } else
        {
            method1 = Compilation.typeValues.getDeclaredMethod("writeValues", 2);
            codeattr.emitLoad(consumer);
            if (i == 0)
            {
                codeattr.emitSwap();
            }
            codeattr.emitInvokeStatic(method1);
            return;
        }
          goto _L1
    }

    public boolean compileWrite(Expression expression, Compilation compilation)
    {
        Type type = expression.getType().getImplementationType();
        if ((type instanceof PrimType) && !type.isVoid() || OccurrenceType.itemCountIsOne(type))
        {
            compilation.getCode().emitLoad(consumer);
            expression.compile(compilation, StackTarget.getInstance(type));
            compileFromStack(compilation, type, 1);
            return true;
        } else
        {
            return false;
        }
    }

    public Variable getConsumerVariable()
    {
        return consumer;
    }

    public Type getType()
    {
        return Compilation.scmSequenceType;
    }

    public final boolean isContextTarget()
    {
        return isContextTarget;
    }
}
