// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.Type;
import gnu.kawa.functions.AppendValues;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package gnu.expr:
//            ExpExpVisitor, ScopeExp, Declaration, LambdaExp, 
//            ReferenceExp, ApplyExp, Compilation, ClassExp, 
//            QuoteExp, Expression, BeginExp, BlockExp, 
//            FluidLetExp, IfExp, LetExp, SetExp, 
//            SynchronizedExp, TryExp, CatchClause

public class FindTailCalls extends ExpExpVisitor
{

    public FindTailCalls()
    {
    }

    public static void findTailCalls(Expression expression, Compilation compilation)
    {
        FindTailCalls findtailcalls = new FindTailCalls();
        findtailcalls.setContext(compilation);
        findtailcalls.visit(expression, expression);
    }

    public void postVisitDecls(ScopeExp scopeexp)
    {
        for (Declaration declaration = scopeexp.firstDecl(); declaration != null; declaration = declaration.nextDecl())
        {
            Expression expression = declaration.getValue();
            if (expression instanceof LambdaExp)
            {
                LambdaExp lambdaexp = (LambdaExp)expression;
                if (declaration.getCanRead())
                {
                    lambdaexp.setCanRead(true);
                }
                if (declaration.getCanCall())
                {
                    lambdaexp.setCanCall(true);
                }
            }
            if (!declaration.getFlag(1024L) || !(expression instanceof ReferenceExp))
            {
                continue;
            }
            Declaration declaration1 = ((ReferenceExp)expression).contextDecl();
            if (declaration1 != null && declaration1.isPrivate())
            {
                declaration1.setFlag(0x80000L);
            }
        }

    }

    protected Expression visitApplyExp(ApplyExp applyexp, Expression expression)
    {
        boolean flag;
        LambdaExp lambdaexp;
        if (expression == currentLambda.body)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            applyexp.setTailCall(true);
        }
        applyexp.context = currentLambda;
        if (applyexp.func instanceof ReferenceExp)
        {
            Declaration declaration = Declaration.followAliases(((ReferenceExp)applyexp.func).binding);
            lambdaexp = null;
            if (declaration != null)
            {
                if (!declaration.getFlag(2048L))
                {
                    applyexp.nextCall = declaration.firstCall;
                    declaration.firstCall = applyexp;
                }
                Compilation compilation = getCompilation();
                declaration.setCanCall();
                if (!compilation.mustCompile)
                {
                    declaration.setCanRead();
                }
                Expression expression1 = declaration.getValue();
                boolean flag1 = expression1 instanceof LambdaExp;
                lambdaexp = null;
                if (flag1)
                {
                    lambdaexp = (LambdaExp)expression1;
                }
            }
        } else
        if ((applyexp.func instanceof LambdaExp) && !(applyexp.func instanceof ClassExp))
        {
            lambdaexp = (LambdaExp)applyexp.func;
            visitLambdaExp(lambdaexp, false);
            lambdaexp.setCanCall(true);
        } else
        if ((applyexp.func instanceof QuoteExp) && ((QuoteExp)applyexp.func).getValue() == AppendValues.appendValues)
        {
            lambdaexp = null;
        } else
        {
            applyexp.func = visitExpression(applyexp.func, applyexp.func);
            lambdaexp = null;
        }
        break MISSING_BLOCK_LABEL_142;
        if (lambdaexp != null && lambdaexp.returnContinuation != expression && (lambdaexp != currentLambda || !flag))
        {
            if (flag)
            {
                if (lambdaexp.tailCallers == null)
                {
                    lambdaexp.tailCallers = new HashSet();
                }
                lambdaexp.tailCallers.add(currentLambda);
            } else
            if (lambdaexp.returnContinuation == null)
            {
                lambdaexp.returnContinuation = expression;
                lambdaexp.inlineHome = currentLambda;
            } else
            {
                lambdaexp.returnContinuation = LambdaExp.unknownContinuation;
                lambdaexp.inlineHome = null;
            }
        }
        applyexp.args = visitExps(applyexp.args);
        return applyexp;
    }

    protected volatile Object visitApplyExp(ApplyExp applyexp, Object obj)
    {
        return visitApplyExp(applyexp, (Expression)obj);
    }

    protected Expression visitBeginExp(BeginExp beginexp, Expression expression)
    {
        int i = -1 + beginexp.length;
        int j = 0;
        while (j <= i) 
        {
            Expression aexpression[] = beginexp.exps;
            Expression expression1 = beginexp.exps[j];
            Expression expression2;
            if (j == i)
            {
                expression2 = expression;
            } else
            {
                expression2 = beginexp.exps[j];
            }
            aexpression[j] = (Expression)expression1.visit(this, expression2);
            j++;
        }
        return beginexp;
    }

    protected volatile Object visitBeginExp(BeginExp beginexp, Object obj)
    {
        return visitBeginExp(beginexp, (Expression)obj);
    }

    protected Expression visitBlockExp(BlockExp blockexp, Expression expression)
    {
        blockexp.body = (Expression)blockexp.body.visit(this, expression);
        if (blockexp.exitBody != null)
        {
            blockexp.exitBody = (Expression)blockexp.exitBody.visit(this, blockexp.exitBody);
        }
        return blockexp;
    }

    protected volatile Object visitBlockExp(BlockExp blockexp, Object obj)
    {
        return visitBlockExp(blockexp, (Expression)obj);
    }

    protected Expression visitClassExp(ClassExp classexp, Expression expression)
    {
        LambdaExp lambdaexp;
        lambdaexp = currentLambda;
        currentLambda = classexp;
        LambdaExp lambdaexp1 = classexp.firstChild;
_L2:
        if (lambdaexp1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (exitValue != null)
        {
            break; /* Loop/switch isn't completed */
        }
        visitLambdaExp(lambdaexp1, false);
        lambdaexp1 = lambdaexp1.nextSibling;
        if (true) goto _L2; else goto _L1
_L1:
        currentLambda = lambdaexp;
        return classexp;
        Exception exception;
        exception;
        currentLambda = lambdaexp;
        throw exception;
    }

    protected volatile Object visitClassExp(ClassExp classexp, Object obj)
    {
        return visitClassExp(classexp, (Expression)obj);
    }

    protected Expression visitExpression(Expression expression, Expression expression1)
    {
        return (Expression)super.visitExpression(expression, expression);
    }

    protected volatile Object visitExpression(Expression expression, Object obj)
    {
        return visitExpression(expression, (Expression)obj);
    }

    public Expression[] visitExps(Expression aexpression[])
    {
        int i = aexpression.length;
        for (int j = 0; j < i; j++)
        {
            Expression expression = aexpression[j];
            aexpression[j] = (Expression)visit(expression, expression);
        }

        return aexpression;
    }

    protected Expression visitFluidLetExp(FluidLetExp fluidletexp, Expression expression)
    {
        for (Declaration declaration = fluidletexp.firstDecl(); declaration != null; declaration = declaration.nextDecl())
        {
            declaration.setCanRead(true);
            if (declaration.base != null)
            {
                declaration.base.setCanRead(true);
            }
        }

        visitLetDecls(fluidletexp);
        fluidletexp.body = (Expression)fluidletexp.body.visit(this, fluidletexp.body);
        postVisitDecls(fluidletexp);
        return fluidletexp;
    }

    protected volatile Object visitFluidLetExp(FluidLetExp fluidletexp, Object obj)
    {
        return visitFluidLetExp(fluidletexp, (Expression)obj);
    }

    protected Expression visitIfExp(IfExp ifexp, Expression expression)
    {
        ifexp.test = (Expression)ifexp.test.visit(this, ifexp.test);
        ifexp.then_clause = (Expression)ifexp.then_clause.visit(this, expression);
        Expression expression1 = ifexp.else_clause;
        if (expression1 != null)
        {
            ifexp.else_clause = (Expression)expression1.visit(this, expression);
        }
        return ifexp;
    }

    protected volatile Object visitIfExp(IfExp ifexp, Object obj)
    {
        return visitIfExp(ifexp, (Expression)obj);
    }

    protected Expression visitLambdaExp(LambdaExp lambdaexp, Expression expression)
    {
        visitLambdaExp(lambdaexp, true);
        return lambdaexp;
    }

    protected volatile Object visitLambdaExp(LambdaExp lambdaexp, Object obj)
    {
        return visitLambdaExp(lambdaexp, (Expression)obj);
    }

    final void visitLambdaExp(LambdaExp lambdaexp, boolean flag)
    {
        LambdaExp lambdaexp1;
        lambdaexp1 = currentLambda;
        currentLambda = lambdaexp;
        if (flag)
        {
            lambdaexp.setCanRead(true);
        }
        if (lambdaexp.defaultArgs != null)
        {
            lambdaexp.defaultArgs = visitExps(lambdaexp.defaultArgs);
        }
        if (exitValue != null || lambdaexp.body == null) goto _L2; else goto _L1
_L1:
        Expression expression;
        expression = lambdaexp.body;
        if (!lambdaexp.getInlineOnly())
        {
            break MISSING_BLOCK_LABEL_94;
        }
        Object obj = lambdaexp;
_L3:
        lambdaexp.body = (Expression)expression.visit(this, obj);
_L2:
        currentLambda = lambdaexp1;
        postVisitDecls(lambdaexp);
        return;
        obj = lambdaexp.body;
          goto _L3
        Exception exception;
        exception;
        currentLambda = lambdaexp1;
        throw exception;
    }

    void visitLetDecls(LetExp letexp)
    {
        Declaration declaration = letexp.firstDecl();
        int i = letexp.inits.length;
        for (int j = 0; j < i;)
        {
            Expression expression = visitSetExp(declaration, letexp.inits[j]);
            if (expression == QuoteExp.undefined_exp)
            {
                Expression expression1 = declaration.getValue();
                if ((expression1 instanceof LambdaExp) || expression1 != expression && (expression1 instanceof QuoteExp))
                {
                    expression = expression1;
                }
            }
            letexp.inits[j] = expression;
            j++;
            declaration = declaration.nextDecl();
        }

    }

    protected Expression visitLetExp(LetExp letexp, Expression expression)
    {
        visitLetDecls(letexp);
        letexp.body = (Expression)letexp.body.visit(this, expression);
        postVisitDecls(letexp);
        return letexp;
    }

    protected volatile Object visitLetExp(LetExp letexp, Object obj)
    {
        return visitLetExp(letexp, (Expression)obj);
    }

    protected Expression visitReferenceExp(ReferenceExp referenceexp, Expression expression)
    {
        Declaration declaration = Declaration.followAliases(referenceexp.binding);
        if (declaration == null) goto _L2; else goto _L1
_L1:
        Type type = declaration.type;
        if (type == null || !type.isVoid()) goto _L4; else goto _L3
_L3:
        referenceexp = QuoteExp.voidExp;
_L6:
        return referenceexp;
_L4:
        declaration.setCanRead(true);
_L2:
        Declaration declaration1 = referenceexp.contextDecl();
        if (declaration1 != null)
        {
            declaration1.setCanRead(true);
            return referenceexp;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected volatile Object visitReferenceExp(ReferenceExp referenceexp, Object obj)
    {
        return visitReferenceExp(referenceexp, (Expression)obj);
    }

    final Expression visitSetExp(Declaration declaration, Expression expression)
    {
        if (declaration != null && declaration.getValue() == expression && (expression instanceof LambdaExp) && !(expression instanceof ClassExp) && !declaration.isPublic())
        {
            LambdaExp lambdaexp = (LambdaExp)expression;
            visitLambdaExp(lambdaexp, false);
            return lambdaexp;
        } else
        {
            return (Expression)expression.visit(this, expression);
        }
    }

    protected Expression visitSetExp(SetExp setexp, Expression expression)
    {
        Declaration declaration = setexp.binding;
        if (declaration != null && declaration.isAlias())
        {
            if (setexp.isDefining())
            {
                setexp.new_value = (Expression)setexp.new_value.visit(this, setexp.new_value);
                return setexp;
            }
            declaration = Declaration.followAliases(declaration);
        }
        Declaration declaration1 = setexp.contextDecl();
        if (declaration1 != null)
        {
            declaration1.setCanRead(true);
        }
        Expression expression1 = visitSetExp(declaration, setexp.new_value);
        if (declaration != null && (declaration.context instanceof LetExp) && expression1 == declaration.getValue() && ((expression1 instanceof LambdaExp) || (expression1 instanceof QuoteExp)))
        {
            return QuoteExp.voidExp;
        } else
        {
            setexp.new_value = expression1;
            return setexp;
        }
    }

    protected volatile Object visitSetExp(SetExp setexp, Object obj)
    {
        return visitSetExp(setexp, (Expression)obj);
    }

    protected Expression visitSynchronizedExp(SynchronizedExp synchronizedexp, Expression expression)
    {
        synchronizedexp.object = (Expression)synchronizedexp.object.visit(this, synchronizedexp.object);
        synchronizedexp.body = (Expression)synchronizedexp.body.visit(this, synchronizedexp.body);
        return synchronizedexp;
    }

    protected volatile Object visitSynchronizedExp(SynchronizedExp synchronizedexp, Object obj)
    {
        return visitSynchronizedExp(synchronizedexp, (Expression)obj);
    }

    protected Expression visitTryExp(TryExp tryexp, Expression expression)
    {
        Expression expression1;
        CatchClause catchclause;
        if (tryexp.finally_clause == null)
        {
            expression1 = expression;
        } else
        {
            expression1 = tryexp.try_clause;
        }
        tryexp.try_clause = (Expression)tryexp.try_clause.visit(this, expression1);
        catchclause = tryexp.catch_clauses;
        while (exitValue == null && catchclause != null) 
        {
            Expression expression3;
            if (tryexp.finally_clause == null)
            {
                expression3 = expression;
            } else
            {
                expression3 = catchclause.body;
            }
            catchclause.body = (Expression)catchclause.body.visit(this, expression3);
            catchclause = catchclause.getNext();
        }
        Expression expression2 = tryexp.finally_clause;
        if (expression2 != null)
        {
            tryexp.finally_clause = (Expression)expression2.visit(this, expression2);
        }
        return tryexp;
    }

    protected volatile Object visitTryExp(TryExp tryexp, Object obj)
    {
        return visitTryExp(tryexp, (Expression)obj);
    }
}
