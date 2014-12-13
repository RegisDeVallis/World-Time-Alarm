// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.BeginExp;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.ScopeExp;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.text.Options;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class with_compile_options extends Syntax
{

    public static final with_compile_options with_compile_options;

    public with_compile_options()
    {
    }

    public static Object getOptions(Object obj, Stack stack, Syntax syntax, Translator translator)
    {
        boolean flag;
        Options options;
        SyntaxForm syntaxform;
        flag = false;
        options = translator.currentOptions;
        syntaxform = null;
_L9:
        for (; obj instanceof SyntaxForm; obj = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj;
        }

        if (obj instanceof Pair) goto _L2; else goto _L1
_L1:
        if (!flag)
        {
            translator.error('e', (new StringBuilder()).append("no option keyword in ").append(syntax.getName()).toString());
        }
        return Translator.wrapSyntax(obj, syntaxform);
_L2:
        Pair pair;
        Object obj1;
        pair = (Pair)obj;
        obj1 = Translator.stripSyntax(pair.getCar());
        if (!(obj1 instanceof Keyword)) goto _L1; else goto _L3
_L3:
        String s;
        Object obj2;
        s = ((Keyword)obj1).getName();
        flag = true;
        obj2 = translator.pushPositionOf(pair);
        Object obj3;
        for (obj3 = pair.getCdr(); obj3 instanceof SyntaxForm; obj3 = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj3;
        }

        LList llist;
        if (obj3 instanceof Pair)
        {
            break MISSING_BLOCK_LABEL_215;
        }
        translator.error('e', (new StringBuilder()).append("keyword ").append(s).append(" not followed by value").toString());
        llist = LList.Empty;
        translator.popPositionOf(obj2);
        return llist;
        Object obj4;
        Object obj5;
        Pair pair1 = (Pair)obj3;
        obj4 = Translator.stripSyntax(pair1.getCar());
        obj = pair1.getCdr();
        obj5 = options.getLocal(s);
        if (options.getInfo(s) != null)
        {
            break MISSING_BLOCK_LABEL_292;
        }
        translator.error('w', (new StringBuilder()).append("unknown compile option: ").append(s).toString());
        translator.popPositionOf(obj2);
        continue; /* Loop/switch isn't completed */
        if (!(obj4 instanceof FString)) goto _L5; else goto _L4
_L4:
        obj4 = obj4.toString();
_L7:
        options.set(s, obj4, translator.getMessages());
        if (stack == null)
        {
            break MISSING_BLOCK_LABEL_345;
        }
        stack.push(s);
        stack.push(obj5);
        stack.push(obj4);
        translator.popPositionOf(obj2);
        continue; /* Loop/switch isn't completed */
_L5:
        if ((obj4 instanceof Boolean) || (obj4 instanceof Number)) goto _L7; else goto _L6
_L6:
        translator.error('e', (new StringBuilder()).append("invalid literal value for key ").append(s).toString());
        obj4 = null;
          goto _L7
        Exception exception;
        exception;
        translator.popPositionOf(obj2);
        throw exception;
        if (true) goto _L9; else goto _L8
_L8:
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Object obj = pair.getCdr();
        if (!(obj instanceof Pair)) goto _L2; else goto _L1
_L1:
        Pair pair1 = (Pair)obj;
        if (!(pair1.getCar() instanceof Stack)) goto _L2; else goto _L3
_L3:
        Stack stack;
        Object obj1;
        stack = (Stack)pair1.getCar();
        obj1 = pair1.getCdr();
        translator.currentOptions.pushOptionValues(stack);
_L8:
        Expression expression = translator.rewrite_body(obj1);
        if (!(expression instanceof BeginExp)) goto _L5; else goto _L4
_L4:
        BeginExp beginexp = (BeginExp)expression;
_L6:
        beginexp.setCompileOptions(stack);
        translator.currentOptions.popOptionValues(stack);
        return beginexp;
_L2:
        stack = new Stack();
        obj1 = getOptions(obj, stack, this, translator);
        continue; /* Loop/switch isn't completed */
_L5:
        beginexp = new BeginExp(new Expression[] {
            expression
        });
          goto _L6
        Exception exception;
        exception;
        translator.currentOptions.popOptionValues(stack);
        throw exception;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public void scanForm(Pair pair, ScopeExp scopeexp, Translator translator)
    {
        Stack stack = new Stack();
        Object obj = getOptions(pair.getCdr(), stack, this, translator);
        if (obj == LList.Empty)
        {
            return;
        }
        if (obj == pair.getCdr())
        {
            translator.scanBody(obj, scopeexp, false);
            return;
        } else
        {
            Pair pair1 = new Pair(stack, translator.scanBody(obj, scopeexp, true));
            translator.currentOptions.popOptionValues(stack);
            translator.formStack.add(Translator.makePair(pair, pair.getCar(), pair1));
            return;
        }
    }

    static 
    {
        with_compile_options = new with_compile_options();
        with_compile_options.setName("with-compile-options");
    }
}
