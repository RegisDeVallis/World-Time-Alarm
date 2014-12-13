// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.FluidLetExp;
import gnu.expr.NameLookup;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class fluid_let extends Syntax
{

    public static final fluid_let fluid_let;
    Expression defaultInit;
    boolean star;

    public fluid_let()
    {
        star = false;
    }

    public fluid_let(boolean flag, Expression expression)
    {
        star = flag;
        defaultInit = expression;
    }

    public Expression rewrite(Object obj, Object obj1, Translator translator)
    {
        FluidLetExp fluidletexp;
        Object obj2;
        Object obj3;
        Object obj4;
        int i;
        Expression aexpression[];
        int j;
        Pair pair;
        Declaration declaration;
        Declaration declaration1;
        Object obj5;
        if (star)
        {
            i = 1;
        } else
        {
            i = LList.length(obj);
        }
        aexpression = new Expression[i];
        fluidletexp = new FluidLetExp(aexpression);
        j = 0;
_L10:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_451;
        }
        pair = (Pair)obj;
        obj2 = translator.pushPositionOf(pair);
        obj3 = pair.getCar();
        if (!(obj3 instanceof String) && !(obj3 instanceof Symbol)) goto _L2; else goto _L1
_L1:
        obj4 = defaultInit;
_L3:
        declaration = fluidletexp.addDeclaration(obj3);
        declaration1 = translator.lexical.lookup(obj3, false);
        if (declaration1 == null)
        {
            break MISSING_BLOCK_LABEL_132;
        }
        declaration1.maybeIndirectBinding(translator);
        declaration.base = declaration1;
        declaration1.setFluid(true);
        declaration1.setCanWrite(true);
        declaration.setCanWrite(true);
        declaration.setFluid(true);
        declaration.setIndirectBinding(true);
        if (obj4 != null)
        {
            break MISSING_BLOCK_LABEL_166;
        }
        obj4 = new ReferenceExp(obj3);
        aexpression[j] = ((Expression) (obj4));
        declaration.noteValue(null);
        obj5 = pair.getCdr();
        obj = obj5;
        translator.popPositionOf(obj2);
        j++;
        continue; /* Loop/switch isn't completed */
_L2:
        Pair pair1;
label0:
        {
            if (!(obj3 instanceof Pair))
            {
                break MISSING_BLOCK_LABEL_398;
            }
            pair1 = (Pair)obj3;
            if (!(pair1.getCar() instanceof String) && !(pair1.getCar() instanceof Symbol) && !(pair1.getCar() instanceof SyntaxForm))
            {
                break MISSING_BLOCK_LABEL_398;
            }
            obj3 = pair1.getCar();
            if (obj3 instanceof SyntaxForm)
            {
                obj3 = ((SyntaxForm)obj3).getDatum();
            }
            if (pair1.getCdr() != LList.Empty)
            {
                break label0;
            }
            obj4 = defaultInit;
        }
          goto _L3
        Expression expression1;
        Pair pair2;
        if (pair1.getCdr() instanceof Pair)
        {
            pair2 = (Pair)pair1.getCdr();
            if (pair2.getCdr() == LList.Empty)
            {
                break MISSING_BLOCK_LABEL_384;
            }
        }
        expression1 = translator.syntaxError((new StringBuilder()).append("bad syntax for value of ").append(obj3).append(" in ").append(getName()).toString());
        translator.popPositionOf(obj2);
        return expression1;
        obj4 = translator.rewrite(pair2.getCar());
          goto _L3
        Expression expression = translator.syntaxError((new StringBuilder()).append("invalid ").append(getName()).append(" syntax").toString());
        translator.popPositionOf(obj2);
        return expression;
        Exception exception;
        exception;
        translator.popPositionOf(obj2);
        throw exception;
        translator.push(fluidletexp);
        if (!star) goto _L5; else goto _L4
_L4:
        LList llist = LList.Empty;
        if (obj == llist) goto _L5; else goto _L6
_L6:
        fluidletexp.body = rewrite(obj, obj1, translator);
_L8:
        translator.pop(fluidletexp);
        return fluidletexp;
_L5:
        fluidletexp.body = translator.rewrite_body(obj1);
        if (true) goto _L8; else goto _L7
_L7:
        if (true) goto _L10; else goto _L9
_L9:
    }

    public Expression rewrite(Object obj, Translator translator)
    {
        if (!(obj instanceof Pair))
        {
            return translator.syntaxError("missing let arguments");
        } else
        {
            Pair pair = (Pair)obj;
            return rewrite(pair.getCar(), pair.getCdr(), translator);
        }
    }

    static 
    {
        fluid_let = new fluid_let();
        fluid_let.setName("fluid-set");
    }
}
