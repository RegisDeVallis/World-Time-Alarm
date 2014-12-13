// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.LetExp;
import gnu.expr.QuoteExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Macro;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class let_syntax extends Syntax
{

    public static final let_syntax let_syntax = new let_syntax(false, "let-syntax");
    public static final let_syntax letrec_syntax = new let_syntax(true, "letrec-syntax");
    boolean recursive;

    public let_syntax(boolean flag, String s)
    {
        super(s);
        recursive = flag;
    }

    private void push(LetExp letexp, Translator translator, Stack stack)
    {
        translator.push(letexp);
        if (stack != null)
        {
            for (int i = stack.size(); --i >= 0;)
            {
                translator.pushRenamedAlias((Declaration)stack.pop());
            }

        }
    }

    public Expression rewrite(Object obj, Translator translator)
    {
        if (!(obj instanceof Pair))
        {
            return translator.syntaxError("missing let-syntax arguments");
        }
        Pair pair = (Pair)obj;
        Object obj1 = pair.getCar();
        Object obj2 = pair.getCdr();
        int i = Translator.listLength(obj1);
        if (i < 0)
        {
            return translator.syntaxError("bindings not a proper list");
        }
        Stack stack = null;
        int j = 0;
        Expression aexpression[] = new Expression[i];
        Declaration adeclaration[] = new Declaration[i];
        Macro amacro[] = new Macro[i];
        Pair apair[] = new Pair[i];
        SyntaxForm asyntaxform[] = new SyntaxForm[i];
        LetExp letexp = new LetExp(aexpression);
        SyntaxForm syntaxform = null;
        int k = 0;
        while (k < i) 
        {
            for (; obj1 instanceof SyntaxForm; obj1 = syntaxform.getDatum())
            {
                syntaxform = (SyntaxForm)obj1;
            }

            SyntaxForm syntaxform1 = syntaxform;
            Pair pair1 = (Pair)obj1;
            Object obj3 = pair1.getCar();
            if (obj3 instanceof SyntaxForm)
            {
                syntaxform1 = (SyntaxForm)obj3;
                obj3 = syntaxform1.getDatum();
            }
            if (!(obj3 instanceof Pair))
            {
                return translator.syntaxError((new StringBuilder()).append(getName()).append(" binding is not a pair").toString());
            }
            Pair pair2 = (Pair)obj3;
            Object obj4 = pair2.getCar();
            SyntaxForm syntaxform2 = syntaxform1;
            for (; obj4 instanceof SyntaxForm; obj4 = syntaxform2.getDatum())
            {
                syntaxform2 = (SyntaxForm)obj4;
            }

            if (!(obj4 instanceof String) && !(obj4 instanceof Symbol))
            {
                return translator.syntaxError((new StringBuilder()).append("variable in ").append(getName()).append(" binding is not a symbol").toString());
            }
            Object obj5;
            for (obj5 = pair2.getCdr(); obj5 instanceof SyntaxForm; obj5 = syntaxform1.getDatum())
            {
                syntaxform1 = (SyntaxForm)obj5;
            }

            if (!(obj5 instanceof Pair))
            {
                return translator.syntaxError((new StringBuilder()).append(getName()).append(" has no value for '").append(obj4).append("'").toString());
            }
            Pair pair3 = (Pair)obj5;
            if (pair3.getCdr() != LList.Empty)
            {
                return translator.syntaxError((new StringBuilder()).append("let binding for '").append(obj4).append("' is improper list").toString());
            }
            Declaration declaration1 = new Declaration(obj4);
            Macro macro2 = Macro.make(declaration1);
            amacro[k] = macro2;
            apair[k] = pair3;
            asyntaxform[k] = syntaxform1;
            letexp.addDeclaration(declaration1);
            Object obj6;
            Object obj7;
            if (syntaxform2 == null)
            {
                obj6 = null;
            } else
            {
                obj6 = syntaxform2.getScope();
            }
            if (obj6 != null)
            {
                Declaration declaration2 = translator.makeRenamedAlias(declaration1, ((gnu.expr.ScopeExp) (obj6)));
                if (stack == null)
                {
                    stack = new Stack();
                }
                stack.push(declaration2);
                j++;
            }
            if (syntaxform1 != null)
            {
                obj7 = syntaxform1.getScope();
            } else
            if (recursive)
            {
                obj7 = letexp;
            } else
            {
                obj7 = translator.currentScope();
            }
            macro2.setCapturedScope(((gnu.expr.ScopeExp) (obj7)));
            adeclaration[k] = declaration1;
            aexpression[k] = QuoteExp.nullExp;
            obj1 = pair1.getCdr();
            k++;
        }
        if (recursive)
        {
            push(letexp, translator, stack);
        }
        Macro macro = translator.currentMacroDefinition;
        for (int l = 0; l < i; l++)
        {
            Macro macro1 = amacro[l];
            translator.currentMacroDefinition = macro1;
            Expression expression1 = translator.rewrite_car(apair[l], asyntaxform[l]);
            aexpression[l] = expression1;
            Declaration declaration = adeclaration[l];
            macro1.expander = expression1;
            QuoteExp quoteexp = new QuoteExp(macro1);
            declaration.noteValue(quoteexp);
            if (expression1 instanceof LambdaExp)
            {
                LambdaExp lambdaexp = (LambdaExp)expression1;
                lambdaexp.nameDecl = declaration;
                lambdaexp.setSymbol(declaration.getSymbol());
            }
        }

        translator.currentMacroDefinition = macro;
        if (!recursive)
        {
            push(letexp, translator, stack);
        }
        Expression expression = translator.rewrite_body(obj2);
        translator.pop(letexp);
        translator.popRenamedAlias(j);
        return expression;
    }

}
