// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;

// Referenced classes of package gnu.expr:
//            StackTarget, LambdaExp, Compilation, Target, 
//            Declaration

public class CheckedTarget extends StackTarget
{

    static Method initWrongTypeProcMethod;
    static Method initWrongTypeStringMethod;
    static ClassType typeClassCastException;
    static ClassType typeWrongType;
    int argno;
    LambdaExp proc;
    String procname;

    public CheckedTarget(Type type)
    {
        super(type);
        argno = -4;
    }

    public CheckedTarget(Type type, LambdaExp lambdaexp, int i)
    {
        super(type);
        proc = lambdaexp;
        procname = lambdaexp.getName();
        argno = i;
    }

    public CheckedTarget(Type type, String s, int i)
    {
        super(type);
        procname = s;
        argno = i;
    }

    public static void emitCheckedCoerce(Compilation compilation, LambdaExp lambdaexp, int i, Type type)
    {
        emitCheckedCoerce(compilation, lambdaexp, lambdaexp.getName(), i, type, null);
    }

    public static void emitCheckedCoerce(Compilation compilation, LambdaExp lambdaexp, int i, Type type, Variable variable)
    {
        emitCheckedCoerce(compilation, lambdaexp, lambdaexp.getName(), i, type, variable);
    }

    static void emitCheckedCoerce(Compilation compilation, LambdaExp lambdaexp, String s, int i, Type type, Variable variable)
    {
        CodeAttr codeattr = compilation.getCode();
        boolean flag = codeattr.isInTry();
        initWrongType();
        Label label = new Label(codeattr);
        gnu.bytecode.Scope scope;
        int j;
        if (variable == null && type != Type.toStringType)
        {
            scope = codeattr.pushScope();
            variable = codeattr.addLocal(Type.objectType);
            codeattr.emitDup(1);
            codeattr.emitStore(variable);
        } else
        {
            scope = null;
        }
        j = codeattr.getPC();
        label.define(codeattr);
        emitCoerceFromObject(type, compilation);
        if (codeattr.getPC() == j || type == Type.toStringType)
        {
            if (scope != null)
            {
                codeattr.popScope();
            }
            return;
        }
        Label label1 = new Label(codeattr);
        label1.define(codeattr);
        Label label2 = new Label(codeattr);
        label2.setTypes(codeattr);
        if (flag)
        {
            codeattr.emitGoto(label2);
        }
        codeattr.setUnreachable();
        int k = 0;
        if (!flag)
        {
            k = codeattr.beginFragment(label2);
        }
        codeattr.addHandler(label, label1, typeClassCastException);
        boolean flag1 = false;
        if (lambdaexp != null)
        {
            boolean flag2 = lambdaexp.isClassGenerated();
            flag1 = false;
            if (flag2)
            {
                boolean flag3 = compilation.method.getStaticFlag();
                flag1 = false;
                if (!flag3)
                {
                    ClassType classtype = compilation.method.getDeclaringClass();
                    ClassType classtype1 = lambdaexp.getCompiledClassType(compilation);
                    flag1 = false;
                    if (classtype == classtype1)
                    {
                        flag1 = true;
                    }
                }
            }
        }
        int l = compilation.getLineNumber();
        if (l > 0)
        {
            codeattr.putLineNumber(l);
        }
        codeattr.emitNew(typeWrongType);
        codeattr.emitDupX();
        codeattr.emitSwap();
        Method method;
        if (flag1)
        {
            codeattr.emitPushThis();
        } else
        {
            if (s == null && i != -4)
            {
                s = "lambda";
            }
            codeattr.emitPushString(s);
        }
        codeattr.emitPushInt(i);
        codeattr.emitLoad(variable);
        if (flag1)
        {
            method = initWrongTypeProcMethod;
        } else
        {
            method = initWrongTypeStringMethod;
        }
        codeattr.emitInvokeSpecial(method);
        if (scope != null)
        {
            codeattr.popScope();
        }
        codeattr.emitThrow();
        if (flag)
        {
            label2.define(codeattr);
            return;
        } else
        {
            codeattr.endFragment(k);
            return;
        }
    }

    public static void emitCheckedCoerce(Compilation compilation, String s, int i, Type type)
    {
        emitCheckedCoerce(compilation, null, s, i, type, null);
    }

    public static Target getInstance(Type type)
    {
        if (type == Type.objectType)
        {
            return Target.pushObject;
        } else
        {
            return new CheckedTarget(type);
        }
    }

    public static Target getInstance(Type type, LambdaExp lambdaexp, int i)
    {
        if (type == Type.objectType)
        {
            return Target.pushObject;
        } else
        {
            return new CheckedTarget(type, lambdaexp, i);
        }
    }

    public static Target getInstance(Type type, String s, int i)
    {
        if (type == Type.objectType)
        {
            return Target.pushObject;
        } else
        {
            return new CheckedTarget(type, s, i);
        }
    }

    public static Target getInstance(Declaration declaration)
    {
        return getInstance(declaration.getType(), declaration.getName(), -2);
    }

    private static void initWrongType()
    {
        if (typeClassCastException == null)
        {
            typeClassCastException = ClassType.make("java.lang.ClassCastException");
        }
        if (typeWrongType == null)
        {
            typeWrongType = ClassType.make("gnu.mapping.WrongType");
            Type atype[] = new Type[4];
            atype[0] = typeClassCastException;
            atype[1] = Compilation.javaStringType;
            atype[2] = Type.intType;
            atype[3] = Type.objectType;
            initWrongTypeStringMethod = typeWrongType.addMethod("<init>", 1, atype, Type.voidType);
            Type atype1[] = new Type[4];
            atype1[0] = typeClassCastException;
            atype1[1] = Compilation.typeProcedure;
            atype1[2] = Type.intType;
            atype1[3] = Type.objectType;
            initWrongTypeProcMethod = typeWrongType.addMethod("<init>", 1, atype1, Type.voidType);
        }
    }

    public void compileFromStack(Compilation compilation, Type type)
    {
        if (!compileFromStack0(compilation, type))
        {
            emitCheckedCoerce(compilation, proc, procname, argno, this.type, null);
        }
    }
}
