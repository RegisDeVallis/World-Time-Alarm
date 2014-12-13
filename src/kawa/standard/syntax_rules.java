// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRules;
import kawa.lang.Translator;

public class syntax_rules extends Syntax
{

    public static final syntax_rules syntax_rules;

    public syntax_rules()
    {
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Pair pair1 = (Pair)pair.getCdr();
        return new QuoteExp(new SyntaxRules(SyntaxPattern.getLiteralsList(pair1.getCar(), null, translator), pair1.getCdr(), translator));
    }

    static 
    {
        syntax_rules = new syntax_rules();
        syntax_rules.setName("syntax-rules");
    }
}
