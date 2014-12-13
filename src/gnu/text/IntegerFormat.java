// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.math.BigInteger;
import java.text.FieldPosition;

// Referenced classes of package gnu.text:
//            ReportFormat

public class IntegerFormat extends ReportFormat
{

    public static final int MIN_DIGITS = 64;
    public static final int PAD_RIGHT = 16;
    public static final int SHOW_BASE = 8;
    public static final int SHOW_GROUPS = 1;
    public static final int SHOW_PLUS = 2;
    public static final int SHOW_SPACE = 4;
    public static final int UPPERCASE = 32;
    public int base;
    public int commaChar;
    public int commaInterval;
    public int flags;
    public int minWidth;
    public int padChar;

    public IntegerFormat()
    {
        base = 10;
        minWidth = 1;
        padChar = 32;
        commaChar = 44;
        commaInterval = 3;
        flags = 0;
    }

    public String convertToIntegerString(Object obj, int i)
    {
        if (!(obj instanceof Number))
        {
            return null;
        }
        if (obj instanceof BigInteger)
        {
            return ((BigInteger)obj).toString(i);
        } else
        {
            return Long.toString(((Number)obj).longValue(), i);
        }
    }

    public int format(Object obj, int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        int j;
        char c;
        char c1;
        int k;
        boolean flag;
        boolean flag1;
        boolean flag2;
        String s;
        Object aobj[];
        if (obj instanceof Object[])
        {
            aobj = (Object[])(Object[])obj;
        } else
        {
            aobj = null;
        }
        j = getParam(minWidth, 1, aobj, i);
        if (minWidth == 0xa0000000)
        {
            i++;
        }
        c = getParam(padChar, ' ', aobj, i);
        if (padChar == 0xa0000000)
        {
            i++;
        }
        c1 = getParam(commaChar, ',', aobj, i);
        if (commaChar == 0xa0000000)
        {
            i++;
        }
        k = getParam(commaInterval, 3, aobj, i);
        if (commaInterval == 0xa0000000)
        {
            i++;
        }
        if ((1 & flags) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if ((0x10 & flags) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (c == '0')
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (aobj != null)
        {
            int i3 = aobj.length;
            if (i >= i3)
            {
                writer.write("#<missing format argument>");
                return i;
            }
            obj = aobj[i];
        }
        int l = base;
        s = convertToIntegerString(obj, l);
        if (s == null) goto _L2; else goto _L1
_L1:
        char c2;
        boolean flag3;
        int i1;
        int l1;
        c2 = s.charAt(0);
        int j1;
        int k1;
        if (c2 == '-')
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        i1 = s.length();
        if (flag3)
        {
            j1 = i1 - 1;
        } else
        {
            j1 = i1;
        }
        if (flag)
        {
            k1 = (j1 - 1) / k;
        } else
        {
            k1 = 0;
        }
        l1 = j1 + k1;
        if (flag3 || (6 & flags) != 0)
        {
            l1++;
        }
        if ((8 & flags) == 0) goto _L4; else goto _L3
_L3:
        if (base != 16) goto _L6; else goto _L5
_L5:
        l1 += 2;
_L4:
        if ((0x40 & flags) != 0)
        {
            l1 = j1;
            if (i1 == 1 && c2 == '0' && j == 0)
            {
                i1 = 0;
            }
        }
        if (!flag1 && !flag2)
        {
            for (; j > l1; j--)
            {
                writer.write(c);
            }

        }
        break; /* Loop/switch isn't completed */
_L6:
        if (base == 8 && c2 != '0')
        {
            l1++;
        }
        if (true) goto _L4; else goto _L7
_L7:
        int j2;
        boolean flag4;
        if (flag3)
        {
            writer.write(45);
            j2 = 0 + 1;
            i1--;
        } else
        if ((2 & flags) != 0)
        {
            writer.write(43);
            j2 = 0;
        } else
        {
            int i2 = 4 & flags;
            j2 = 0;
            if (i2 != 0)
            {
                writer.write(32);
                j2 = 0;
            }
        }
        if (base > 10 && (0x20 & flags) != 0)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        if ((8 & flags) != 0)
        {
            if (base == 16)
            {
                writer.write(48);
                byte byte0;
                if (flag4)
                {
                    byte0 = 88;
                } else
                {
                    byte0 = 120;
                }
                writer.write(byte0);
            } else
            if (base == 8 && c2 != '0')
            {
                writer.write(48);
            }
        }
        int k2;
        int l2;
        char c3;
        if (flag2)
        {
            for (; j > l1; j--)
            {
                writer.write(c);
            }

        }
        k2 = j2;
          goto _L8
_L11:
        while (i1 != 0) 
        {
            l2 = k2 + 1;
            c3 = s.charAt(k2);
            if (flag4)
            {
                c3 = Character.toUpperCase(c3);
            }
            writer.write(c3);
            i1--;
            if (flag && i1 > 0 && i1 % k == 0)
            {
                writer.write(c1);
            }
            k2 = l2;
        }
        if (flag1)
        {
            for (; j > l1; j--)
            {
                writer.write(c);
            }

        }
          goto _L9
_L2:
        print(writer, obj.toString());
_L9:
        return i + 1;
_L8:
        if (true) goto _L11; else goto _L10
_L10:
    }

    public int format(Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        return format(((Object) (aobj)), i, writer, fieldposition);
    }
}
