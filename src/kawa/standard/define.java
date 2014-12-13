// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.LangExp;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

// Referenced classes of package kawa.standard:
//            SchemeCompilation

public class define extends Syntax
{

    public static final define defineRaw;
    Lambda lambda;

    public define(Lambda lambda1)
    {
        lambda = lambda1;
    }

    String getName(int i)
    {
        if ((i & 4) != 0)
        {
            return "define-private";
        }
        if ((i & 8) != 0)
        {
            return "define-constant";
        } else
        {
            return "define";
        }
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Pair pair1 = (Pair)pair.getCdr();
        Pair pair2 = (Pair)pair1.getCdr();
        Pair pair3 = (Pair)((Pair)pair2.getCdr()).getCdr();
        Object obj = Translator.stripSyntax(pair1.getCar());
        int i = ((Number)Translator.stripSyntax(pair2.getCar())).intValue();
        boolean flag;
        Object obj3;
        if ((i & 4) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!(obj instanceof Declaration))
        {
            obj3 = translator.syntaxError((new StringBuilder()).append(getName(i)).append(" is only allowed in a <body>").toString());
        } else
        {
            Declaration declaration = (Declaration)obj;
            if (declaration.getFlag(8192L))
            {
                Expression expression = declaration.getTypeExp();
                if (expression instanceof LangExp)
                {
                    declaration.setType(translator.exp2Type((Pair)((LangExp)expression).getLangValue()));
                }
            }
            Object obj1;
            if ((i & 2) != 0)
            {
                LambdaExp lambdaexp = (LambdaExp)declaration.getValue();
                Object obj4 = pair3.getCdr();
                lambda.rewriteBody(lambdaexp, obj4, translator);
                obj1 = lambdaexp;
                if (!Compilation.inlineOk)
                {
                    declaration.noteValue(null);
                }
            } else
            {
                obj1 = translator.rewrite(pair3.getCar());
                Object obj2;
                if ((declaration.context instanceof ModuleExp) && !flag && declaration.getCanWrite())
                {
                    obj2 = null;
                } else
                {
                    obj2 = obj1;
                }
                declaration.noteValue(((Expression) (obj2)));
            }
            obj3 = new SetExp(declaration, ((Expression) (obj1)));
            ((SetExp) (obj3)).setDefining(true);
            if (flag && !(translator.currentScope() instanceof ModuleExp))
            {
                translator.error('w', (new StringBuilder()).append("define-private not at top level ").append(translator.currentScope()).toString());
                return ((Expression) (obj3));
            }
        }
        return ((Expression) (obj3));
    }

    public void scanForm(Pair pair, ScopeExp scopeexp, Translator translator)
    {
        Pair pair1 = (Pair)pair.getCdr();
        Pair pair2 = (Pair)pair1.getCdr();
        Pair pair3 = (Pair)pair2.getCdr();
        Pair pair4 = (Pair)pair3.getCdr();
        SyntaxForm syntaxform = null;
        Object obj;
        for (obj = pair1.getCar(); obj instanceof SyntaxForm; obj = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj;
        }

        int i = ((Number)Translator.stripSyntax(pair2.getCar())).intValue();
        boolean flag;
        boolean flag1;
        Object obj1;
        Object obj2;
        Declaration declaration;
        Object obj3;
        Pair pair5;
        if ((i & 4) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if ((i & 8) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        translator.currentScope();
        obj1 = translator.namespaceResolve(obj);
        if (!(obj1 instanceof Symbol))
        {
            translator.error('e', (new StringBuilder()).append("'").append(obj1).append("' is not a valid identifier").toString());
            obj1 = null;
        }
        obj2 = translator.pushPositionOf(pair1);
        declaration = translator.define(obj1, syntaxform, scopeexp);
        translator.popPositionOf(obj2);
        obj3 = declaration.getSymbol();
        if (flag)
        {
            declaration.setFlag(0x1000000L);
            declaration.setPrivate(true);
        }
        if (flag1)
        {
            declaration.setFlag(16384L);
        }
        declaration.setFlag(0x40000L);
        if ((i & 2) != 0)
        {
            LambdaExp lambdaexp = new LambdaExp();
            lambdaexp.setSymbol(obj3);
            if (Compilation.inlineOk)
            {
                declaration.setProcedureDecl(true);
                declaration.setType(Compilation.typeProcedure);
                lambdaexp.nameDecl = declaration;
            }
            Object obj4 = pair4.getCar();
            Object obj5 = pair4.getCdr();
            Translator.setLine(lambdaexp, pair1);
            lambda.rewriteFormals(lambdaexp, obj4, translator, null);
            Object obj6 = lambda.rewriteAttrs(lambdaexp, obj5, translator);
            if (obj6 != obj5)
            {
                Object obj7 = pair2.getCar();
                Object obj8 = pair3.getCar();
                Pair pair6 = new Pair(obj4, obj6);
                pair2 = new Pair(obj7, new Pair(obj8, pair6));
            }
            declaration.noteValue(lambdaexp);
        }
        if ((scopeexp instanceof ModuleExp) && !flag && (!Compilation.inlineOk || translator.sharedModuleDefs()))
        {
            declaration.setCanWrite(true);
        }
        if ((i & 1) != 0)
        {
            LangExp langexp = new LangExp(pair3);
            declaration.setTypeExp(langexp);
            declaration.setFlag(8192L);
        }
        pair5 = Translator.makePair(pair, this, Translator.makePair(pair1, declaration, pair2));
        Translator.setLine(declaration, pair1);
        translator.formStack.addElement(pair5);
    }

    static 
    {
        defineRaw = new define(SchemeCompilation.lambda);
    }
}
