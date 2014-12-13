// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.commonlisp.lang;

import gnu.expr.BeginExp;
import gnu.expr.Expression;
import gnu.expr.LetExp;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class prog1 extends Syntax
{

    public static final prog1 prog1 = new prog1("prog1", 1);
    public static final prog1 prog2 = new prog1("prog2", 2);
    int index;

    public prog1(String s, int i)
    {
        index = i;
        setName(s);
    }

    public Expression rewrite(Object obj, Translator translator)
    {
        int i = LList.length(obj);
        if (i < index)
        {
            return translator.syntaxError((new StringBuilder()).append("too few expressions in ").append(getName()).toString());
        }
        if (index == 2)
        {
            Pair pair2 = (Pair)obj;
            return new BeginExp(translator.rewrite(pair2.getCar()), prog1.rewrite(pair2.getCdr(), translator));
        }
        Expression aexpression[] = new Expression[1];
        LetExp letexp = new LetExp(aexpression);
        Expression aexpression1[] = new Expression[i];
        Pair pair = (Pair)obj;
        aexpression[0] = translator.rewrite(pair.getCar());
        Object obj1 = pair.getCdr();
        for (int j = 0; j < i - 1; j++)
        {
            Pair pair1 = (Pair)obj1;
            aexpression1[j] = translator.rewrite(pair1.getCar());
            obj1 = pair1.getCdr();
        }

        gnu.expr.Declaration declaration = letexp.addDeclaration((Object)null);
        aexpression1[i - 1] = new ReferenceExp(declaration);
        letexp.body = BeginExp.canonicalize(aexpression1);
        translator.mustCompileHere();
        return letexp;
    }

}
