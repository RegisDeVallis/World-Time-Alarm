// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.commonlisp.lang;

import gnu.expr.BeginExp;
import gnu.expr.Expression;
import gnu.expr.SetExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

// Referenced classes of package gnu.commonlisp.lang:
//            CommonLisp

public class setq extends Syntax
{

    public setq()
    {
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Object obj;
        Vector vector;
        obj = pair.getCdr();
        vector = null;
_L7:
        if (obj == LList.Empty) goto _L2; else goto _L1
_L1:
        if (obj instanceof Pair) goto _L4; else goto _L3
_L3:
        Object obj4 = translator.syntaxError("invalid syntax for setq");
_L6:
        return ((Expression) (obj4));
_L4:
        Pair pair1 = (Pair)obj;
        Object obj1 = pair1.getCar();
        Object obj2;
        Object obj3;
        if ((obj1 instanceof Symbol) || (obj1 instanceof String))
        {
            obj2 = obj1;
        } else
        if (obj1 == CommonLisp.FALSE)
        {
            obj2 = "nil";
        } else
        {
            return translator.syntaxError("invalid variable name in setq");
        }
        obj3 = pair1.getCdr();
        if (!(obj3 instanceof Pair))
        {
            return translator.syntaxError("wrong number of arguments for setq");
        }
        Pair pair2 = (Pair)obj3;
        Expression expression = translator.rewrite(pair2.getCar());
        obj = pair2.getCdr();
        obj4 = new SetExp(obj2, expression);
        ((SetExp) (obj4)).setFlag(8);
        if (obj != LList.Empty)
        {
            break; /* Loop/switch isn't completed */
        }
        ((SetExp) (obj4)).setHasValue(true);
        if (vector == null) goto _L6; else goto _L5
_L5:
        if (vector == null)
        {
            vector = new Vector(10);
        }
        vector.addElement(obj4);
          goto _L7
_L2:
        if (vector == null)
        {
            return CommonLisp.nilExpr;
        } else
        {
            Expression aexpression[] = new Expression[vector.size()];
            vector.copyInto(aexpression);
            return new BeginExp(aexpression);
        }
    }
}
