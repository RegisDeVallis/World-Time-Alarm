// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.QuoteExp;
import gnu.kawa.reflect.Invoke;

class SetListExp extends ApplyExp
{

    public SetListExp(Expression expression, Expression aexpression[])
    {
        super(expression, aexpression);
    }

    public Expression validateApply(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Declaration declaration)
    {
        applyexp.visitArgs(inlinecalls);
        Expression aexpression[] = applyexp.getArgs();
        if (aexpression.length == 2)
        {
            Expression aexpression1[] = new Expression[4];
            aexpression1[0] = getArgs()[0];
            aexpression1[1] = QuoteExp.getInstance("set");
            aexpression1[2] = Compilation.makeCoercion(aexpression[0], Type.intType);
            aexpression1[3] = aexpression[1];
            applyexp = Compilation.makeCoercion(inlinecalls.visitApplyOnly(new ApplyExp(Invoke.invoke, aexpression1), type), Type.voidType);
        }
        return applyexp;
    }
}
