// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.lists.Consumer;
import gnu.mapping.OutPort;
import gnu.mapping.SimpleSymbol;

public class Symbols
{

    private static int gensym_counter;

    private Symbols()
    {
    }

    static int generateInt()
    {
        gnu/expr/Symbols;
        JVM INSTR monitorenter ;
        int i;
        i = 1 + gensym_counter;
        gensym_counter = i;
        gnu/expr/Symbols;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public static final SimpleSymbol gentemp()
    {
        return SimpleSymbol.valueOf((new StringBuilder()).append("GS.").append(Integer.toString(generateInt())).toString());
    }

    public static final String intern(String s)
    {
        return make(s);
    }

    public static String make(String s)
    {
        return s.intern();
    }

    public static void print(String s, Consumer consumer)
    {
        boolean flag;
        if ((consumer instanceof OutPort) && ((OutPort)consumer).printReadable)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            int i = s.length();
            for (int j = 0; j < i; j++)
            {
                char c = s.charAt(j);
                if (!Character.isLowerCase(c) && c != '!' && c != '$' && c != '%' && c != '&' && c != '*' && c != '/' && c != ':' && c != '<' && c != '=' && c != '>' && c != '?' && c != '~' && c != '_' && c != '^' && (c != '+' && c != '-' || j <= 0 && i != 1) && (!Character.isDigit(c) || j <= 0) && (c != '.' || j != 0 && s.charAt(j - 1) != '.'))
                {
                    consumer.write(92);
                }
                consumer.write(c);
            }

        } else
        {
            consumer.write(s);
        }
    }
}
