// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LetExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class let extends Syntax
{

    public static final let let;

    public let()
    {
    }

    public Expression rewrite(Object obj, Translator translator)
    {
        if (!(obj instanceof Pair))
        {
            return translator.syntaxError("missing let arguments");
        }
        Pair pair = (Pair)obj;
        Object obj1 = pair.getCar();
        Object obj2 = pair.getCdr();
        int i = Translator.listLength(obj1);
        if (i < 0)
        {
            return translator.syntaxError("bindings not a proper list");
        }
        Expression aexpression[] = new Expression[i];
        LetExp letexp = new LetExp(aexpression);
        Stack stack = null;
        int j = 0;
        SyntaxForm syntaxform = null;
        for (int k = 0; k < i; k++)
        {
            Pair pair1;
            SyntaxForm syntaxform1;
            Object obj5;
            Declaration declaration;
            Pair pair3;
            Object obj7;
label0:
            {
label1:
                {
                    for (; obj1 instanceof SyntaxForm; obj1 = syntaxform.getDatum())
                    {
                        syntaxform = (SyntaxForm)obj1;
                    }

                    pair1 = (Pair)obj1;
                    Object obj3 = pair1.getCar();
                    syntaxform1 = syntaxform;
                    if (obj3 instanceof SyntaxForm)
                    {
                        syntaxform1 = (SyntaxForm)obj3;
                        obj3 = syntaxform1.getDatum();
                    }
                    if (!(obj3 instanceof Pair))
                    {
                        return translator.syntaxError((new StringBuilder()).append("let binding is not a pair:").append(obj3).toString());
                    }
                    Pair pair2 = (Pair)obj3;
                    Object obj4 = pair2.getCar();
                    kawa.lang.TemplateScope templatescope;
                    if (obj4 instanceof SyntaxForm)
                    {
                        SyntaxForm syntaxform2 = (SyntaxForm)obj4;
                        obj4 = syntaxform2.getDatum();
                        templatescope = syntaxform2.getScope();
                    } else
                    if (syntaxform1 == null)
                    {
                        templatescope = null;
                    } else
                    {
                        templatescope = syntaxform1.getScope();
                    }
                    obj5 = translator.namespaceResolve(obj4);
                    if (!(obj5 instanceof Symbol))
                    {
                        return translator.syntaxError((new StringBuilder()).append("variable ").append(obj5).append(" in let binding is not a symbol: ").append(obj).toString());
                    }
                    declaration = letexp.addDeclaration(obj5);
                    declaration.setFlag(0x40000L);
                    if (templatescope != null)
                    {
                        Declaration declaration1 = translator.makeRenamedAlias(declaration, templatescope);
                        if (stack == null)
                        {
                            stack = new Stack();
                        }
                        stack.push(declaration1);
                        j++;
                    }
                    Object obj6;
                    for (obj6 = pair2.getCdr(); obj6 instanceof SyntaxForm; obj6 = syntaxform1.getDatum())
                    {
                        syntaxform1 = (SyntaxForm)obj6;
                    }

                    if (!(obj6 instanceof Pair))
                    {
                        return translator.syntaxError((new StringBuilder()).append("let has no value for '").append(obj5).append("'").toString());
                    }
                    pair3 = (Pair)obj6;
                    for (obj7 = pair3.getCdr(); obj7 instanceof SyntaxForm; obj7 = syntaxform1.getDatum())
                    {
                        syntaxform1 = (SyntaxForm)obj7;
                    }

                    if (!translator.matches(pair3.getCar(), "::"))
                    {
                        break label0;
                    }
                    if (obj7 instanceof Pair)
                    {
                        pair3 = (Pair)obj7;
                        if (pair3.getCdr() != LList.Empty)
                        {
                            break label1;
                        }
                    }
                    return translator.syntaxError("missing type after '::' in let");
                }
                for (obj7 = pair3.getCdr(); obj7 instanceof SyntaxForm; obj7 = syntaxform1.getDatum())
                {
                    syntaxform1 = (SyntaxForm)obj7;
                }

            }
            Pair pair4;
            if (obj7 == LList.Empty)
            {
                pair4 = pair3;
            } else
            if (obj7 instanceof Pair)
            {
                declaration.setType(translator.exp2Type(pair3));
                declaration.setFlag(8192L);
                pair4 = (Pair)obj7;
            } else
            {
                return translator.syntaxError((new StringBuilder()).append("let binding for '").append(obj5).append("' is improper list").toString());
            }
            aexpression[k] = translator.rewrite_car(pair4, syntaxform1);
            if (pair4.getCdr() != LList.Empty)
            {
                return translator.syntaxError((new StringBuilder()).append("junk after declaration of ").append(obj5).toString());
            }
            declaration.noteValue(aexpression[k]);
            obj1 = pair1.getCdr();
        }

        for (int l = j; --l >= 0;)
        {
            translator.pushRenamedAlias((Declaration)stack.pop());
        }

        translator.push(letexp);
        letexp.body = translator.rewrite_body(obj2);
        translator.pop(letexp);
        translator.popRenamedAlias(j);
        return letexp;
    }

    static 
    {
        let = new let();
        let.setName("let");
    }
}
