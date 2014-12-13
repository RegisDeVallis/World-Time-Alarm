// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.math.IntNum;
import gnu.math.RealNum;
import gnu.text.EnglishIntegerFormat;
import gnu.text.RomanIntegerFormat;
import java.text.Format;

public class IntegerFormat extends gnu.text.IntegerFormat
{

    private static IntegerFormat plainDecimalFormat;

    public IntegerFormat()
    {
    }

    public static IntegerFormat getInstance()
    {
        if (plainDecimalFormat == null)
        {
            plainDecimalFormat = new IntegerFormat();
        }
        return plainDecimalFormat;
    }

    public static Format getInstance(int i, int j, int k, int l, int i1, int j1)
    {
        boolean flag = true;
        if (i == 0xc0000000)
        {
            if (k == 0xc0000000 && k == 0xc0000000 && l == 0xc0000000 && i1 == 0xc0000000)
            {
                if ((j1 & 1) == 0)
                {
                    flag = false;
                }
                if ((j1 & 2) != 0)
                {
                    return RomanIntegerFormat.getInstance(flag);
                } else
                {
                    return EnglishIntegerFormat.getInstance(flag);
                }
            }
            i = 10;
        }
        if (j == 0xc0000000)
        {
            j = 1;
        }
        if (k == 0xc0000000)
        {
            k = 32;
        }
        if (l == 0xc0000000)
        {
            l = 44;
        }
        if (i1 == 0xc0000000)
        {
            i1 = 3;
        }
        if (i == 10 && j == flag && k == 32 && l == 44 && i1 == 3 && j1 == 0)
        {
            return getInstance();
        } else
        {
            IntegerFormat integerformat = new IntegerFormat();
            integerformat.base = i;
            integerformat.minWidth = j;
            integerformat.padChar = k;
            integerformat.commaChar = l;
            integerformat.commaInterval = i1;
            integerformat.flags = j1;
            return integerformat;
        }
    }

    public String convertToIntegerString(Object obj, int i)
    {
        if (obj instanceof RealNum)
        {
            return ((RealNum)obj).toExactInt(4).toString(i);
        } else
        {
            return super.convertToIntegerString(obj, i);
        }
    }
}
