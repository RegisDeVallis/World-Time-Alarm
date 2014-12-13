// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;

import java.io.PrintWriter;

// Referenced classes of package gnu.lists:
//            CharSeq

public class Strings
{

    public Strings()
    {
    }

    public static void makeCapitalize(CharSeq charseq)
    {
        char c = ' ';
        int i = charseq.length();
        int j = 0;
        while (j < i) 
        {
            char c1 = charseq.charAt(j);
            char c2;
            if (!Character.isLetterOrDigit(c))
            {
                c2 = Character.toTitleCase(c1);
            } else
            {
                c2 = Character.toLowerCase(c1);
            }
            charseq.setCharAt(j, c2);
            c = c2;
            j++;
        }
    }

    public static void makeLowerCase(CharSeq charseq)
    {
        for (int i = charseq.length(); --i >= 0;)
        {
            charseq.setCharAt(i, Character.toLowerCase(charseq.charAt(i)));
        }

    }

    public static void makeUpperCase(CharSeq charseq)
    {
        for (int i = charseq.length(); --i >= 0;)
        {
            charseq.setCharAt(i, Character.toUpperCase(charseq.charAt(i)));
        }

    }

    public static void printQuoted(CharSequence charsequence, PrintWriter printwriter, int i)
    {
        int j;
        int k;
        j = charsequence.length();
        printwriter.print('"');
        k = 0;
_L8:
        char c;
        if (k >= j)
        {
            break MISSING_BLOCK_LABEL_180;
        }
        c = charsequence.charAt(k);
        if (c != '\\' && c != '"') goto _L2; else goto _L1
_L1:
        printwriter.print('\\');
_L4:
        printwriter.print(c);
_L5:
        k++;
        continue; /* Loop/switch isn't completed */
_L2:
        if (i <= 0) goto _L4; else goto _L3
_L3:
        if (c == '\n')
        {
            printwriter.print("\\n");
        } else
        if (c == '\r')
        {
            printwriter.print("\\r");
        } else
        if (c == '\t')
        {
            printwriter.print("\\t");
        } else
        if (c == '\007')
        {
            printwriter.print("\\a");
        } else
        if (c == '\b')
        {
            printwriter.print("\\b");
        } else
        {
            if (c != '\013')
            {
                continue; /* Loop/switch isn't completed */
            }
            printwriter.print("\\v");
        }
          goto _L5
        if (c != '\f') goto _L4; else goto _L6
_L6:
        printwriter.print("\\f");
          goto _L5
        printwriter.print('"');
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }
}
