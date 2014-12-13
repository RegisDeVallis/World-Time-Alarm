// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;


// Referenced classes of package gnu.expr:
//            ExpExpVisitor, ClassExp, LambdaExp, ScopeExp, 
//            Declaration, Compilation, Expression

public class ChainLambdas extends ExpExpVisitor
{

    public ChainLambdas()
    {
    }

    public static void chainLambdas(Expression expression, Compilation compilation)
    {
        ChainLambdas chainlambdas = new ChainLambdas();
        chainlambdas.setContext(compilation);
        chainlambdas.visit(expression, null);
    }

    protected Expression visitClassExp(ClassExp classexp, ScopeExp scopeexp)
    {
        LambdaExp lambdaexp = currentLambda;
        if (lambdaexp != null && !(lambdaexp instanceof ClassExp))
        {
            classexp.nextSibling = lambdaexp.firstChild;
            lambdaexp.firstChild = classexp;
        }
        visitScopeExp(classexp, scopeexp);
        return classexp;
    }

    protected volatile Object visitClassExp(ClassExp classexp, Object obj)
    {
        return visitClassExp(classexp, (ScopeExp)obj);
    }

    protected Expression visitLambdaExp(LambdaExp lambdaexp, ScopeExp scopeexp)
    {
        LambdaExp lambdaexp1 = currentLambda;
        if (lambdaexp1 != null && !(lambdaexp1 instanceof ClassExp))
        {
            lambdaexp.nextSibling = lambdaexp1.firstChild;
            lambdaexp1.firstChild = lambdaexp;
        }
        lambdaexp.outer = scopeexp;
        lambdaexp.firstChild = null;
        lambdaexp.visitChildrenOnly(this, lambdaexp);
        lambdaexp.visitProperties(this, lambdaexp);
        LambdaExp lambdaexp2 = null;
        LambdaExp lambdaexp4;
        for (LambdaExp lambdaexp3 = lambdaexp.firstChild; lambdaexp3 != null; lambdaexp3 = lambdaexp4)
        {
            lambdaexp4 = lambdaexp3.nextSibling;
            lambdaexp3.nextSibling = lambdaexp2;
            lambdaexp2 = lambdaexp3;
        }

        lambdaexp.firstChild = lambdaexp2;
        if (lambdaexp.getName() == null && lambdaexp.nameDecl != null)
        {
            lambdaexp.setName(lambdaexp.nameDecl.getName());
        }
        lambdaexp.setIndexes();
        if (lambdaexp.mustCompile())
        {
            comp.mustCompileHere();
        }
        return lambdaexp;
    }

    protected volatile Object visitLambdaExp(LambdaExp lambdaexp, Object obj)
    {
        return visitLambdaExp(lambdaexp, (ScopeExp)obj);
    }

    protected Expression visitScopeExp(ScopeExp scopeexp, ScopeExp scopeexp1)
    {
        scopeexp.outer = scopeexp1;
        scopeexp.visitChildren(this, scopeexp);
        scopeexp.setIndexes();
        if (scopeexp.mustCompile())
        {
            comp.mustCompileHere();
        }
        return scopeexp;
    }

    protected volatile Object visitScopeExp(ScopeExp scopeexp, Object obj)
    {
        return visitScopeExp(scopeexp, (ScopeExp)obj);
    }
}
