// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.commonlisp.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class defun extends Syntax
{

    Lambda lambdaSyntax;

    public defun(Lambda lambda)
    {
        lambdaSyntax = lambda;
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Object obj;
        Declaration declaration;
        obj = pair.getCdr();
        declaration = null;
        if (!(obj instanceof Pair)) goto _L2; else goto _L1
_L1:
        Pair pair1;
        Object obj1;
        pair1 = (Pair)obj;
        obj1 = pair1.getCar();
        if (!(obj1 instanceof Symbol) && !(obj1 instanceof String)) goto _L4; else goto _L3
_L3:
        Object obj2 = obj1.toString();
_L5:
        if (obj2 != null && (pair1.getCdr() instanceof Pair))
        {
            Pair pair2 = (Pair)pair1.getCdr();
            LambdaExp lambdaexp = new LambdaExp();
            lambdaSyntax.rewrite(lambdaexp, pair2.getCar(), pair2.getCdr(), translator, null);
            lambdaexp.setSymbol(obj2);
            if (pair2 instanceof PairWithPosition)
            {
                lambdaexp.setLocation((PairWithPosition)pair2);
            }
            LambdaExp lambdaexp1 = lambdaexp;
            SetExp setexp = new SetExp(obj2, lambdaexp1);
            setexp.setDefining(true);
            setexp.setFuncDef(true);
            if (declaration != null)
            {
                setexp.setBinding(declaration);
                if ((declaration.context instanceof ModuleExp) && declaration.getCanWrite())
                {
                    lambdaexp1 = null;
                }
                declaration.noteValue(lambdaexp1);
            }
            return setexp;
        }
        break; /* Loop/switch isn't completed */
_L4:
        boolean flag = obj1 instanceof Declaration;
        declaration = null;
        obj2 = null;
        if (flag)
        {
            declaration = (Declaration)obj1;
            obj2 = declaration.getSymbol();
        }
        if (true) goto _L5; else goto _L2
_L2:
        return translator.syntaxError((new StringBuilder()).append("invalid syntax for ").append(getName()).toString());
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeexp, Translator translator)
    {
        Pair pair1;
label0:
        {
            if (pair.getCdr() instanceof Pair)
            {
                pair1 = (Pair)pair.getCdr();
                if ((pair1.getCar() instanceof String) || (pair1.getCar() instanceof Symbol))
                {
                    break label0;
                }
            }
            return super.scanForDefinitions(pair, vector, scopeexp, translator);
        }
        Object obj = pair1.getCar();
        Declaration declaration = scopeexp.lookup(obj);
        if (declaration == null)
        {
            declaration = new Declaration(obj);
            declaration.setProcedureDecl(true);
            scopeexp.addDeclaration(declaration);
        } else
        {
            translator.error('w', (new StringBuilder()).append("duplicate declaration for `").append(obj).append("'").toString());
        }
        if (scopeexp instanceof ModuleExp)
        {
            declaration.setCanRead(true);
        }
        vector.addElement(Translator.makePair(pair, this, Translator.makePair(pair1, declaration, pair1.getCdr())));
        return true;
    }
}
