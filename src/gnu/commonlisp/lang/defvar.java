// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.commonlisp.lang;

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

// Referenced classes of package gnu.commonlisp.lang:
//            CommonLisp

public class defvar extends Syntax
{

    boolean force;

    public defvar(boolean flag)
    {
        force = flag;
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Object obj;
        boolean flag;
        Declaration declaration;
        Object obj1;
        Expression expression;
        obj = pair.getCdr();
        flag = obj instanceof Pair;
        declaration = null;
        obj1 = null;
        expression = null;
        if (!flag) goto _L2; else goto _L1
_L1:
        Pair pair1;
        boolean flag1;
        pair1 = (Pair)obj;
        flag1 = pair1.getCar() instanceof Declaration;
        declaration = null;
        obj1 = null;
        expression = null;
        if (!flag1) goto _L2; else goto _L3
_L3:
        declaration = (Declaration)pair1.getCar();
        obj1 = declaration.getSymbol();
        if (!(pair1.getCdr() instanceof Pair)) goto _L5; else goto _L4
_L4:
        Pair pair2 = (Pair)pair1.getCdr();
        expression = translator.rewrite(pair2.getCar());
        if (pair2.getCdr() == LList.Empty);
_L2:
        if (obj1 != null) goto _L7; else goto _L6
_L6:
        Object obj2 = translator.syntaxError((new StringBuilder()).append("invalid syntax for ").append(getName()).toString());
_L9:
        return ((Expression) (obj2));
_L5:
        Object obj3 = pair1.getCdr();
        LList llist = LList.Empty;
        expression = null;
        if (obj3 != llist)
        {
            obj1 = null;
            expression = null;
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (expression == null)
        {
            if (!force)
            {
                break; /* Loop/switch isn't completed */
            }
            expression = CommonLisp.nilExpr;
        }
        obj2 = new SetExp(obj1, expression);
        if (!force)
        {
            ((SetExp) (obj2)).setSetIfUnbound(true);
        }
        ((SetExp) (obj2)).setDefining(true);
        if (declaration != null)
        {
            ((SetExp) (obj2)).setBinding(declaration);
            if ((declaration.context instanceof ModuleExp) && declaration.getCanWrite())
            {
                expression = null;
            }
            declaration.noteValue(expression);
            return ((Expression) (obj2));
        }
        if (true) goto _L9; else goto _L8
_L8:
        return new QuoteExp(obj1);
        if (true) goto _L2; else goto _L10
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
            Declaration declaration = scopeexp.lookup(obj);
            if (declaration == null)
            {
                declaration = new Declaration(obj);
                declaration.setFlag(0x10000000L);
                scopeexp.addDeclaration(declaration);
            } else
            {
                translator.error('w', (new StringBuilder()).append("duplicate declaration for `").append(obj).append("'").toString());
            }
            pair = Translator.makePair(pair, this, Translator.makePair(pair1, declaration, pair1.getCdr()));
            if (scopeexp instanceof ModuleExp)
            {
                declaration.setCanRead(true);
                declaration.setCanWrite(true);
            }
        }
        vector.addElement(pair);
        return true;
    }
}
