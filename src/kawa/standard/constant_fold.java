// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class constant_fold extends Syntax
{

    public static final constant_fold constant_fold;

    public constant_fold()
    {
    }

    static Object checkConstant(Expression expression, Translator translator)
    {
        Object obj;
        if (expression instanceof QuoteExp)
        {
            obj = ((QuoteExp)expression).getValue();
        } else
        {
            boolean flag = expression instanceof ReferenceExp;
            obj = null;
            if (flag)
            {
                ReferenceExp referenceexp = (ReferenceExp)expression;
                Declaration declaration = referenceexp.getBinding();
                if (declaration == null || declaration.getFlag(0x10000L))
                {
                    return Environment.user().get(referenceexp.getName(), null);
                } else
                {
                    return Declaration.followAliases(declaration).getConstantValue();
                }
            }
        }
        return obj;
    }

    public Expression rewrite(Object obj, Translator translator)
    {
        Expression expression = translator.rewrite(obj);
        if (!(expression instanceof ApplyExp))
        {
            return expression;
        }
        ApplyExp applyexp = (ApplyExp)expression;
        Object obj1 = checkConstant(applyexp.getFunction(), translator);
        if (!(obj1 instanceof Procedure))
        {
            return expression;
        }
        Expression aexpression[] = applyexp.getArgs();
        int i = aexpression.length;
        Object aobj[] = new Object[i];
        while (--i >= 0) 
        {
            Object obj2 = checkConstant(aexpression[i], translator);
            if (obj2 == null)
            {
                return expression;
            }
            aobj[i] = obj2;
        }
        QuoteExp quoteexp;
        try
        {
            quoteexp = new QuoteExp(((Procedure)obj1).applyN(aobj));
        }
        catch (Throwable throwable)
        {
            Expression expression1 = translator.syntaxError("caught exception in constant-fold:");
            translator.syntaxError(throwable.toString());
            return expression1;
        }
        return quoteexp;
    }

    static 
    {
        constant_fold = new constant_fold();
        constant_fold.setName("constant-fold");
    }
}
