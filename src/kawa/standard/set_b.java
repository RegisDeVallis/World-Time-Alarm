// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.functions.CompilationHelpers;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

// Referenced classes of package kawa.standard:
//            Scheme

public class set_b extends Syntax
{

    public static final set_b set;

    public set_b()
    {
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Object obj;
        SyntaxForm syntaxform;
        obj = pair.getCdr();
        syntaxform = null;
        for (; obj instanceof SyntaxForm; obj = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj;
        }

        if (obj instanceof Pair) goto _L2; else goto _L1
_L1:
        Object obj3 = translator.syntaxError("missing name");
_L4:
        return ((Expression) (obj3));
_L2:
        Expression expression;
        Pair pair2;
label0:
        {
            Pair pair1 = (Pair)obj;
            expression = translator.rewrite_car(pair1, syntaxform);
            Object obj1;
            for (obj1 = pair1.getCdr(); obj1 instanceof SyntaxForm; obj1 = syntaxform.getDatum())
            {
                syntaxform = (SyntaxForm)obj1;
            }

            if (obj1 instanceof Pair)
            {
                pair2 = (Pair)obj1;
                if (pair2.getCdr() == LList.Empty)
                {
                    break label0;
                }
            }
            return translator.syntaxError("missing or extra arguments to set!");
        }
        Expression expression1 = translator.rewrite_car(pair2, syntaxform);
        if (expression instanceof ApplyExp)
        {
            ApplyExp applyexp = (ApplyExp)expression;
            Expression aexpression[] = applyexp.getArgs();
            int i = aexpression.length;
            Expression expression2 = applyexp.getFunction();
            int j = aexpression.length;
            int k = 0;
            if (j > 0)
            {
                boolean flag = expression2 instanceof ReferenceExp;
                k = 0;
                if (flag)
                {
                    Declaration declaration4 = ((ReferenceExp)expression2).getBinding();
                    Declaration declaration5 = Scheme.applyFieldDecl;
                    k = 0;
                    if (declaration4 == declaration5)
                    {
                        k = 1;
                        i--;
                        expression2 = aexpression[0];
                    }
                }
            }
            Expression aexpression1[] = {
                expression2
            };
            Expression aexpression2[] = new Expression[i + 1];
            System.arraycopy(aexpression, k, aexpression2, 0, i);
            aexpression2[i] = expression1;
            Declaration declaration3 = CompilationHelpers.setterDecl;
            ReferenceExp referenceexp1 = new ReferenceExp(declaration3);
            ApplyExp applyexp1 = new ApplyExp(referenceexp1, aexpression1);
            ApplyExp applyexp2 = new ApplyExp(applyexp1, aexpression2);
            return applyexp2;
        }
        if (!(expression instanceof ReferenceExp))
        {
            return translator.syntaxError("first set! argument is not a variable name");
        }
        ReferenceExp referenceexp = (ReferenceExp)expression;
        Declaration declaration = referenceexp.getBinding();
        Object obj2 = referenceexp.getSymbol();
        obj3 = new SetExp(obj2, expression1);
        Declaration declaration1 = referenceexp.contextDecl();
        ((SetExp) (obj3)).setContextDecl(declaration1);
        if (declaration != null)
        {
            declaration.setCanWrite(true);
            ((SetExp) (obj3)).setBinding(declaration);
            Declaration declaration2 = Declaration.followAliases(declaration);
            if (declaration2 != null)
            {
                declaration2.noteValue(expression1);
            }
            if (declaration2.getFlag(16384L))
            {
                return translator.syntaxError((new StringBuilder()).append("constant variable ").append(declaration2.getName()).append(" is set!").toString());
            }
            if (declaration2.context != translator.mainLambda && (declaration2.context instanceof ModuleExp) && !declaration2.getFlag(0x10000000L) && !declaration2.context.getFlag(0x100000))
            {
                translator.error('w', declaration2, "imported variable ", " is set!");
                return ((Expression) (obj3));
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    static 
    {
        set = new set_b();
        set.setName("set!");
    }
}
