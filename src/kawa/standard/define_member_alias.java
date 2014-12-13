// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.reflect.Invoke;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_member_alias extends Syntax
{

    public static final define_member_alias define_member_alias;

    public define_member_alias()
    {
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Pair pair1;
label0:
        {
            Object obj = pair.getCdr();
            if (obj instanceof Pair)
            {
                pair1 = (Pair)obj;
                if ((pair1.getCar() instanceof String) || (pair1.getCar() instanceof Declaration))
                {
                    break label0;
                }
            }
            return translator.syntaxError((new StringBuilder()).append("missing name in ").append(getName()).toString());
        }
        if (!(pair1.getCdr() instanceof Pair)) goto _L2; else goto _L1
_L1:
        Object obj2;
        Object obj3;
        Object obj1 = pair1.getCar();
        String s;
        Pair pair2;
        Expression expression;
        ClassType classtype;
        Expression aexpression[];
        if (obj1 instanceof Declaration)
        {
            s = ((Declaration)obj1).getName();
        } else
        {
            s = (String)obj1;
        }
        pair2 = (Pair)pair1.getCdr();
        expression = translator.rewrite(pair2.getCar());
        obj2 = pair2.getCdr();
        if (obj2 != LList.Empty) goto _L4; else goto _L3
_L3:
        obj3 = new QuoteExp(Compilation.mangleName(s));
_L5:
        if (obj3 != null)
        {
            classtype = ClassType.make("gnu.kawa.reflect.ClassMemberConstraint");
            aexpression = new Expression[3];
            aexpression[0] = new QuoteExp(s);
            aexpression[1] = expression;
            aexpression[2] = ((Expression) (obj3));
            return Invoke.makeInvokeStatic(classtype, "define", aexpression);
        }
        break; /* Loop/switch isn't completed */
_L4:
        boolean flag = obj2 instanceof Pair;
        obj3 = null;
        if (flag)
        {
            Pair pair3 = (Pair)obj2;
            Object obj4 = pair3.getCdr();
            LList llist = LList.Empty;
            obj3 = null;
            if (obj4 == llist)
            {
                obj3 = translator.rewrite(pair3.getCar());
            }
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
            if ((pair.getCdr() instanceof Pair) && !(translator.currentScope() instanceof ModuleExp))
            {
                pair1 = (Pair)pair.getCdr();
                if (pair1.getCar() instanceof String)
                {
                    break label0;
                }
            }
            return super.scanForDefinitions(pair, vector, scopeexp, translator);
        }
        Declaration declaration = scopeexp.addDeclaration((String)pair1.getCar(), Compilation.typeSymbol);
        declaration.setIndirectBinding(true);
        vector.addElement(Translator.makePair(pair, this, Translator.makePair(pair1, declaration, pair1.getCdr())));
        return true;
    }

    static 
    {
        define_member_alias = new define_member_alias();
        define_member_alias.setName("define-member-alias");
    }
}
