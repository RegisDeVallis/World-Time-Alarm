// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.commonlisp.lang;

import gnu.expr.Expression;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class function extends Syntax
{

    Syntax lambda;

    public function(Syntax syntax)
    {
        lambda = syntax;
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Object obj = pair.getCdr();
        if (obj instanceof Pair)
        {
            Pair pair1 = (Pair)obj;
            if (pair1.getCdr() != LList.Empty)
            {
                return translator.syntaxError("too many forms after 'function'");
            }
            Object obj1 = pair1.getCar();
            if ((obj1 instanceof String) || (obj1 instanceof Symbol))
            {
                ReferenceExp referenceexp = new ReferenceExp(obj1);
                referenceexp.setProcedureName(true);
                referenceexp.setFlag(8);
                return referenceexp;
            }
            if (obj1 instanceof Pair)
            {
                Pair pair2 = (Pair)obj1;
                Object obj2 = pair2.getCar();
                if ((obj2 instanceof String) ? "lambda".equals(obj2) : (obj2 instanceof Symbol) && "lambda".equals(((Symbol)obj2).getName()))
                {
                    return lambda.rewriteForm(pair2, translator);
                }
            }
        }
        return translator.syntaxError("function must be followed by name or lambda expression");
    }
}
