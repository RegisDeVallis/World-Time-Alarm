// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ClassExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

// Referenced classes of package kawa.standard:
//            location

public class define_alias extends Syntax
{

    public static final define_alias define_alias;

    public define_alias()
    {
    }

    public Expression rewrite(Object obj, Translator translator)
    {
        return translator.syntaxError("define-alias is only allowed in a <body>");
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeexp, Translator translator)
    {
        Object obj;
        SyntaxForm syntaxform;
        obj = pair.getCdr();
        syntaxform = null;
        for (; obj instanceof SyntaxForm; obj = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj;
        }

        if (!(obj instanceof Pair)) goto _L2; else goto _L1
_L1:
        SyntaxForm syntaxform1;
        Object obj1;
        Object obj2;
        Pair pair1 = (Pair)obj;
        syntaxform1 = syntaxform;
        for (obj1 = pair1.getCar(); obj1 instanceof SyntaxForm; obj1 = syntaxform1.getDatum())
        {
            syntaxform1 = (SyntaxForm)obj1;
        }

        for (obj2 = pair1.getCdr(); obj2 instanceof SyntaxForm; obj2 = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj2;
        }

        if (!(obj1 instanceof String) && !(obj1 instanceof Symbol) || !(obj2 instanceof Pair)) goto _L2; else goto _L3
_L3:
        Pair pair2 = (Pair)obj2;
        if (pair2.getCdr() != LList.Empty) goto _L2; else goto _L4
_L4:
        Declaration declaration;
        Expression expression;
        declaration = translator.define(obj1, syntaxform1, scopeexp);
        declaration.setIndirectBinding(true);
        declaration.setAlias(true);
        expression = translator.rewrite_car(pair2, syntaxform);
        if (!(expression instanceof ReferenceExp)) goto _L6; else goto _L5
_L5:
        ReferenceExp referenceexp;
        Declaration declaration1;
        referenceexp = (ReferenceExp)expression;
        declaration1 = Declaration.followAliases(referenceexp.getBinding());
        if (declaration1 == null) goto _L8; else goto _L7
_L7:
        Expression expression1 = declaration1.getValue();
        if (!(expression1 instanceof ClassExp) && !(expression1 instanceof ModuleExp)) goto _L8; else goto _L9
_L9:
        declaration.setIndirectBinding(false);
        declaration.setFlag(16384L);
_L10:
        translator.mustCompileHere();
        translator.push(declaration);
        SetExp setexp = new SetExp(declaration, expression);
        translator.setLineOf(setexp);
        declaration.noteValue(expression);
        setexp.setDefining(true);
        vector.addElement(setexp);
        return true;
_L8:
        referenceexp.setDontDereference(true);
        continue; /* Loop/switch isn't completed */
_L6:
        if (expression instanceof QuoteExp)
        {
            declaration.setIndirectBinding(false);
            declaration.setFlag(16384L);
        } else
        {
            expression = location.rewrite(expression, translator);
            declaration.setType(ClassType.make("gnu.mapping.Location"));
        }
        if (true) goto _L10; else goto _L2
_L2:
        translator.error('e', "invalid syntax for define-alias");
        return false;
    }

    static 
    {
        define_alias = new define_alias();
        define_alias.setName("define-alias");
    }
}
