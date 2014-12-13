// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.PatternScope;
import kawa.lang.Quote;
import kawa.lang.SyntaxTemplate;
import kawa.lang.Translator;

public class syntax extends Quote
{

    static final Method makeTemplateScopeMethod;
    public static final syntax quasiSyntax = new syntax("quasisyntax", true);
    public static final syntax syntax = new syntax("syntax", false);
    static final ClassType typeTemplateScope;

    public syntax(String s, boolean flag)
    {
        super(s, flag);
    }

    static Expression makeSyntax(Object obj, Translator translator)
    {
        SyntaxTemplate syntaxtemplate = new SyntaxTemplate(obj, null, translator);
        Object obj1 = QuoteExp.nullExp;
        PatternScope patternscope = translator.patternScope;
        if (patternscope != null && patternscope.matchArray != null)
        {
            obj1 = new ReferenceExp(patternscope.matchArray);
        }
        Expression aexpression[] = new Expression[3];
        aexpression[0] = new QuoteExp(syntaxtemplate);
        aexpression[1] = ((Expression) (obj1));
        aexpression[2] = new ReferenceExp(translator.templateScopeDecl);
        return new ApplyExp(ClassType.make("kawa.lang.SyntaxTemplate").getDeclaredMethod("execute", 2), aexpression);
    }

    protected boolean expandColonForms()
    {
        return false;
    }

    protected Expression leaf(Object obj, Translator translator)
    {
        return makeSyntax(obj, translator);
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Pair pair1;
        Declaration declaration;
label0:
        {
            if (pair.getCdr() instanceof Pair)
            {
                pair1 = (Pair)(Pair)pair.getCdr();
                if (pair1.getCdr() == LList.Empty)
                {
                    break label0;
                }
            }
            return translator.syntaxError("syntax forms requires a single form");
        }
        declaration = translator.templateScopeDecl;
        if (declaration == null)
        {
            translator.letStart();
            ApplyExp applyexp = new ApplyExp(makeTemplateScopeMethod, Expression.noExpressions);
            Declaration declaration1 = translator.letVariable(null, typeTemplateScope, applyexp);
            declaration1.setCanRead();
            translator.templateScopeDecl = declaration1;
            translator.letEnter();
        }
        Object obj = pair1.getCar();
        int i;
        Object obj1;
        gnu.expr.LetExp letexp;
        if (isQuasi)
        {
            i = 1;
        } else
        {
            i = -1;
        }
        obj1 = coerceExpression(expand(obj, i, translator), translator);
        if (declaration != null)
        {
            break MISSING_BLOCK_LABEL_144;
        }
        letexp = translator.letDone(((Expression) (obj1)));
        obj1 = letexp;
        translator.templateScopeDecl = declaration;
        return ((Expression) (obj1));
        Exception exception;
        exception;
        translator.templateScopeDecl = declaration;
        throw exception;
    }

    static 
    {
        typeTemplateScope = ClassType.make("kawa.lang.TemplateScope");
        makeTemplateScopeMethod = typeTemplateScope.getDeclaredMethod("make", 0);
    }
}
