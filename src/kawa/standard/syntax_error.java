// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class syntax_error extends Syntax
{

    public static final syntax_error syntax_error;

    public syntax_error()
    {
    }

    public static Expression error(Object obj, Object aobj[])
    {
        StringBuffer stringbuffer;
        Translator translator;
        Object obj1;
        stringbuffer = new StringBuffer();
        int i = aobj.length;
        if (aobj == null || i == 0)
        {
            stringbuffer.append("invalid syntax");
        } else
        {
            int j = 0;
            while (j < i) 
            {
                stringbuffer.append(aobj[j]);
                j++;
            }
        }
        translator = (Translator)Compilation.getCurrent();
        if (translator == null)
        {
            throw new RuntimeException(stringbuffer.toString());
        }
        obj1 = translator.pushPositionOf(obj);
        Expression expression = translator.syntaxError(stringbuffer.toString());
        translator.popPositionOf(obj1);
        return expression;
        Exception exception;
        exception;
        translator.popPositionOf(obj1);
        throw exception;
    }

    public Expression rewrite(Object obj, Translator translator)
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i;
        for (i = 0; obj instanceof Pair; i++)
        {
            Pair pair = (Pair)obj;
            if (i > 0)
            {
                stringbuffer.append(' ');
            }
            stringbuffer.append(pair.getCar());
            obj = pair.getCdr();
        }

        if (obj != LList.Empty)
        {
            if (i > 0)
            {
                stringbuffer.append(' ');
            }
            stringbuffer.append(obj);
        }
        return translator.syntaxError(stringbuffer.toString());
    }

    static 
    {
        syntax_error = new syntax_error();
        syntax_error.setName("%syntax-error");
    }
}
