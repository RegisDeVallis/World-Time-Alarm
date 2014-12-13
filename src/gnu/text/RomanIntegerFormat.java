// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class RomanIntegerFormat extends NumberFormat
{

    static final String codes = "IVXLCDM";
    private static RomanIntegerFormat newRoman;
    private static RomanIntegerFormat oldRoman;
    public boolean oldStyle;

    public RomanIntegerFormat()
    {
    }

    public RomanIntegerFormat(boolean flag)
    {
        oldStyle = flag;
    }

    public static String format(int i)
    {
        return format(i, false);
    }

    public static String format(int i, boolean flag)
    {
        if (i <= 0 || i >= 4999)
        {
            return Integer.toString(i);
        }
        StringBuffer stringbuffer = new StringBuffer(20);
        int j = 3;
        int k = 1000;
        while (j >= 0) 
        {
            int l = i / k;
            i -= l * k;
            if (l != 0)
            {
                if (!flag && (l == 4 || l == 9))
                {
                    stringbuffer.append("IVXLCDM".charAt(j * 2));
                    stringbuffer.append("IVXLCDM".charAt(j * 2 + (l + 1) / 5));
                } else
                {
                    int i1 = l;
                    if (i1 >= 5)
                    {
                        stringbuffer.append("IVXLCDM".charAt(1 + j * 2));
                        i1 -= 5;
                    }
                    while (--i1 >= 0) 
                    {
                        stringbuffer.append("IVXLCDM".charAt(j * 2));
                    }
                }
            }
            k /= 10;
            j--;
        }
        return stringbuffer.toString();
    }

    public static RomanIntegerFormat getInstance(boolean flag)
    {
        if (flag)
        {
            if (oldRoman == null)
            {
                oldRoman = new RomanIntegerFormat(true);
            }
            return oldRoman;
        }
        if (newRoman == null)
        {
            newRoman = new RomanIntegerFormat(false);
        }
        return newRoman;
    }

    public StringBuffer format(double d, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        long l = (long)d;
        if ((double)l == d)
        {
            return format(l, stringbuffer, fieldposition);
        } else
        {
            stringbuffer.append(Double.toString(d));
            return stringbuffer;
        }
    }

    public StringBuffer format(long l, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        if (l <= 0L) goto _L2; else goto _L1
_L1:
        String s;
        long l1;
        int i;
        int k;
        if (oldStyle)
        {
            k = 4999;
        } else
        {
            k = 3999;
        }
        if (l >= (long)k) goto _L2; else goto _L3
_L3:
        s = format((int)l, oldStyle);
_L5:
        if (fieldposition == null)
        {
            break MISSING_BLOCK_LABEL_126;
        }
        l1 = 1L;
        i = s.length();
        for (int j = i; --j > 0;)
        {
            l1 = 9L + 10L * l1;
        }

        break; /* Loop/switch isn't completed */
_L2:
        s = Long.toString(l);
        if (true) goto _L5; else goto _L4
_L4:
        StringBuffer stringbuffer1 = new StringBuffer(i);
        (new DecimalFormat("0")).format(l1, stringbuffer1, fieldposition);
        stringbuffer.append(s);
        return stringbuffer;
    }

    public Number parse(String s, ParsePosition parseposition)
    {
        throw new Error("RomanIntegerFormat.parseObject - not implemented");
    }
}
