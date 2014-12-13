// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.reflect;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.LambdaExp;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;

public class TypeSwitch extends MethodProc
    implements Inlineable
{

    public static final TypeSwitch typeSwitch = new TypeSwitch("typeswitch");

    public TypeSwitch(String s)
    {
        setName(s);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplyTypeSwitch");
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        Object aobj[] = callcontext.getArgs();
        Object obj = aobj[0];
        int i = -1 + aobj.length;
        for (int j = 1; j < i; j++)
        {
            if (((MethodProc)aobj[j]).match1(obj, callcontext) >= 0)
            {
                return;
            }
        }

        ((Procedure)aobj[i]).check1(obj, callcontext);
    }

    public void compile(ApplyExp applyexp, Compilation compilation, Target target)
    {
        Expression aexpression[] = applyexp.getArgs();
        CodeAttr codeattr = compilation.getCode();
        codeattr.pushScope();
        gnu.bytecode.Variable variable = codeattr.addLocal(Type.pointer_type);
        aexpression[0].compile(compilation, Target.pushObject);
        codeattr.emitStore(variable);
        for (int i = 1; i < aexpression.length;)
        {
            if (i > 1)
            {
                codeattr.emitElse();
            }
            int k = i + 1;
            Expression expression = aexpression[i];
            if (expression instanceof LambdaExp)
            {
                LambdaExp lambdaexp = (LambdaExp)expression;
                Declaration declaration = lambdaexp.firstDecl();
                Type type = declaration.getType();
                if (!declaration.getCanRead())
                {
                    declaration = null;
                } else
                {
                    declaration.allocateVariable(codeattr);
                }
                if (type instanceof TypeValue)
                {
                    ((TypeValue)type).emitTestIf(variable, declaration, compilation);
                } else
                {
                    if (k < aexpression.length)
                    {
                        codeattr.emitLoad(variable);
                        type.emitIsInstance(codeattr);
                        codeattr.emitIfIntNotZero();
                    }
                    if (declaration != null)
                    {
                        codeattr.emitLoad(variable);
                        declaration.compileStore(compilation);
                    }
                }
                lambdaexp.allocChildClasses(compilation);
                lambdaexp.body.compileWithPosition(compilation, target);
                i = k;
            } else
            {
                throw new Error("not implemented: typeswitch arg not LambdaExp");
            }
        }

        for (int j = -2 + aexpression.length; --j >= 0;)
        {
            codeattr.emitFi();
        }

        codeattr.popScope();
    }

    public Type getReturnType(Expression aexpression[])
    {
        return Type.pointer_type;
    }

    public int numArgs()
    {
        return -4094;
    }

}
