// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.ThisExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class thisRef extends Syntax
{

    public static final thisRef thisSyntax;

    public thisRef()
    {
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        if (pair.getCdr() == LList.Empty)
        {
            LambdaExp lambdaexp = translator.curMethodLambda;
            Declaration declaration;
            if (lambdaexp == null)
            {
                declaration = null;
            } else
            {
                declaration = lambdaexp.firstDecl();
            }
            if (declaration == null || !declaration.isThisParameter())
            {
                declaration = null;
                if (lambdaexp == null || lambdaexp.nameDecl == null)
                {
                    translator.error('e', "use of 'this' not in a named method");
                } else
                if (lambdaexp.nameDecl.isStatic())
                {
                    translator.error('e', "use of 'this' in a static method");
                    declaration = null;
                } else
                {
                    declaration = new Declaration(ThisExp.THIS_NAME);
                    lambdaexp.add(null, declaration);
                    lambdaexp.nameDecl.setFlag(4096L);
                }
            }
            return new ThisExp(declaration);
        } else
        {
            return translator.syntaxError("this with parameter not implemented");
        }
    }

    static 
    {
        thisSyntax = new thisRef();
        thisSyntax.setName("this");
    }
}
