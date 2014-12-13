// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_variable extends Syntax
{

    public static final define_variable define_variable;

    public define_variable()
    {
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Object obj;
        boolean flag;
        Declaration declaration;
        Expression expression;
        obj = pair.getCdr();
        flag = obj instanceof Pair;
        declaration = null;
        expression = null;
        if (!flag) goto _L2; else goto _L1
_L1:
        Pair pair1;
        Object obj2;
        pair1 = (Pair)obj;
        obj2 = pair1.getCar();
        if (!(obj2 instanceof String) && !(obj2 instanceof Symbol)) goto _L4; else goto _L3
_L3:
        Object obj1 = translator.syntaxError((new StringBuilder()).append(getName()).append(" is only allowed in a <body>").toString());
_L11:
        return ((Expression) (obj1));
_L4:
        boolean flag1;
        flag1 = obj2 instanceof Declaration;
        declaration = null;
        expression = null;
        if (!flag1) goto _L2; else goto _L5
_L5:
        Object obj3;
        declaration = (Declaration)pair1.getCar();
        obj3 = pair1.getCdr();
        if (!(obj3 instanceof Pair)) goto _L7; else goto _L6
_L6:
        Pair pair2 = (Pair)obj3;
        if (pair2.getCdr() != LList.Empty) goto _L7; else goto _L8
_L8:
        expression = translator.rewrite(pair2.getCar());
_L2:
        if (declaration == null)
        {
            return translator.syntaxError((new StringBuilder()).append("invalid syntax for ").append(getName()).toString());
        }
        break; /* Loop/switch isn't completed */
_L7:
        LList llist = LList.Empty;
        expression = null;
        if (obj3 != llist)
        {
            declaration = null;
            expression = null;
        }
        if (true) goto _L2; else goto _L9
_L9:
        if (expression == null)
        {
            return QuoteExp.voidExp;
        }
        obj1 = new SetExp(declaration, expression);
        ((SetExp) (obj1)).setDefining(true);
        ((SetExp) (obj1)).setSetIfUnbound(true);
        if (declaration != null)
        {
            ((SetExp) (obj1)).setBinding(declaration);
            if ((declaration.context instanceof ModuleExp) && declaration.getCanWrite())
            {
                expression = null;
            }
            declaration.noteValue(expression);
            return ((Expression) (obj1));
        }
        if (true) goto _L11; else goto _L10
_L10:
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeexp, Translator translator)
    {
        if (!(pair.getCdr() instanceof Pair))
        {
            return super.scanForDefinitions(pair, vector, scopeexp, translator);
        }
        Pair pair1 = (Pair)pair.getCdr();
        Object obj = pair1.getCar();
        if ((obj instanceof String) || (obj instanceof Symbol))
        {
            if (scopeexp.lookup(obj) != null)
            {
                translator.error('e', (new StringBuilder()).append("duplicate declaration for '").append(obj).append("'").toString());
            }
            Declaration declaration = scopeexp.addDeclaration(obj);
            translator.push(declaration);
            declaration.setSimple(false);
            declaration.setPrivate(true);
            declaration.setFlag(0x10040000L);
            declaration.setCanRead(true);
            declaration.setCanWrite(true);
            declaration.setIndirectBinding(true);
            pair = Translator.makePair(pair, this, Translator.makePair(pair1, declaration, pair1.getCdr()));
        }
        vector.addElement(pair);
        return true;
    }

    static 
    {
        define_variable = new define_variable();
        define_variable.setName("define-variable");
    }
}
