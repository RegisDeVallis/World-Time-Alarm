// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.QuoteExp;
import gnu.kawa.reflect.ArraySet;
import gnu.kawa.reflect.Invoke;

class SetArrayExp extends ApplyExp
{

    public static final ClassType typeSetArray = ClassType.make("gnu.kawa.functions.SetArray");
    Type elementType;

    public SetArrayExp(Expression expression, ArrayType arraytype)
    {
        Invoke invoke = Invoke.make;
        Expression aexpression[] = new Expression[2];
        aexpression[0] = new QuoteExp(typeSetArray);
        aexpression[1] = expression;
        super(invoke, aexpression);
        elementType = arraytype.getComponentType();
    }

    public Expression validateApply(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Declaration declaration)
    {
        applyexp.visitArgs(inlinecalls);
        Expression aexpression[] = applyexp.getArgs();
        if (aexpression.length == 2)
        {
            Expression expression = getArgs()[1];
            Expression aexpression1[] = new Expression[3];
            aexpression1[0] = expression;
            aexpression1[1] = aexpression[0];
            aexpression1[2] = aexpression[1];
            applyexp = inlinecalls.visitApplyOnly(new ApplyExp(new ArraySet(elementType), aexpression1), type);
        }
        return applyexp;
    }

}
