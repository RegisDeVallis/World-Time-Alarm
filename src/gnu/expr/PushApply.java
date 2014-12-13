// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;


// Referenced classes of package gnu.expr:
//            ExpVisitor, Expression, ApplyExp, LetExp, 
//            FluidLetExp, BeginExp

public class PushApply extends ExpVisitor
{

    public PushApply()
    {
    }

    public static void pushApply(Expression expression)
    {
        (new PushApply()).visit(expression, null);
    }

    protected Expression defaultValue(Expression expression, Void void1)
    {
        return expression;
    }

    protected volatile Object defaultValue(Expression expression, Object obj)
    {
        return defaultValue(expression, (Void)obj);
    }

    protected Expression update(Expression expression, Expression expression1)
    {
        return expression1;
    }

    protected volatile Expression update(Expression expression, Object obj)
    {
        return update(expression, (Expression)obj);
    }

    protected Expression visitApplyExp(ApplyExp applyexp, Void void1)
    {
        Expression expression = applyexp.func;
        if ((expression instanceof LetExp) && !(expression instanceof FluidLetExp))
        {
            LetExp letexp = (LetExp)expression;
            Expression expression1 = letexp.body;
            letexp.body = applyexp;
            applyexp.func = expression1;
            return (Expression)visit(letexp, void1);
        }
        if (expression instanceof BeginExp)
        {
            BeginExp beginexp = (BeginExp)expression;
            Expression aexpression[] = beginexp.exps;
            int i = -1 + beginexp.exps.length;
            applyexp.func = aexpression[i];
            aexpression[i] = applyexp;
            return (Expression)visit(beginexp, void1);
        } else
        {
            applyexp.visitChildren(this, void1);
            return applyexp;
        }
    }

    protected volatile Object visitApplyExp(ApplyExp applyexp, Object obj)
    {
        return visitApplyExp(applyexp, (Void)obj);
    }
}
