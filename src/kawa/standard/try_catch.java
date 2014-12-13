// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.CatchClause;
import gnu.expr.Compilation;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.TryExp;
import gnu.lists.FVector;
import kawa.lang.Lambda;
import kawa.lang.Translator;

// Referenced classes of package kawa.standard:
//            SchemeCompilation

public class try_catch
{

    public try_catch()
    {
    }

    public static Expression rewrite(Object obj, Object obj1)
    {
        Translator translator = (Translator)Compilation.getCurrent();
        Expression expression = translator.rewrite(obj);
        CatchClause catchclause = null;
        CatchClause catchclause1 = null;
        FVector fvector = (FVector)obj1;
        int i = fvector.size();
        int j = 0;
        while (j < i) 
        {
            Expression expression1 = SchemeCompilation.lambda.rewrite(fvector.get(j), translator);
            if (expression1 instanceof ErrorExp)
            {
                return expression1;
            }
            if (!(expression1 instanceof LambdaExp))
            {
                return translator.syntaxError("internal error with try-catch");
            }
            CatchClause catchclause2 = new CatchClause((LambdaExp)expression1);
            if (catchclause == null)
            {
                catchclause1 = catchclause2;
            } else
            {
                catchclause.setNext(catchclause2);
            }
            catchclause = catchclause2;
            j++;
        }
        if (expression instanceof ErrorExp)
        {
            return expression;
        } else
        {
            TryExp tryexp = new TryExp(expression, null);
            tryexp.setCatchClauses(catchclause1);
            return tryexp;
        }
    }
}
