// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.math;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

// Referenced classes of package gnu.math:
//            RealNum, IntNum

public class ExponentialFormat extends Format
{

    static final double LOG10 = Math.log(10D);
    public int expDigits;
    public char exponentChar;
    public boolean exponentShowSign;
    public int fracDigits;
    public boolean general;
    public int intDigits;
    public char overflowChar;
    public char padChar;
    public boolean showPlus;
    public int width;

    public ExponentialFormat()
    {
        fracDigits = -1;
        exponentChar = 'E';
    }

    static boolean addOne(StringBuffer stringbuffer, int i, int j)
    {
        int k = j;
        do
        {
            if (k == i)
            {
                stringbuffer.insert(k, '1');
                return true;
            }
            k--;
            char c = stringbuffer.charAt(k);
            if (c != '9')
            {
                stringbuffer.setCharAt(k, (char)(c + 1));
                return false;
            }
            stringbuffer.setCharAt(k, '0');
        } while (true);
    }

    StringBuffer format(double d, String s, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        boolean flag1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l2;
        boolean flag2;
        int i3;
        boolean flag3;
        int l3;
        i = intDigits;
        j = fracDigits;
        boolean flag;
        int k1;
        if (d < 0.0D)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            d = -d;
        }
        k = stringbuffer.length();
        l = 1;
        if (flag)
        {
            if (j >= 0)
            {
                stringbuffer.append('-');
            }
        } else
        if (showPlus)
        {
            stringbuffer.append('+');
        } else
        {
            l = 0;
        }
        i1 = stringbuffer.length();
        if (Double.isNaN(d) || Double.isInfinite(d))
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (j < 0 || flag1)
        {
            if (s == null)
            {
                s = Double.toString(d);
            }
            int j1 = s.indexOf('E');
            int j3;
            int k3;
            if (j1 >= 0)
            {
                stringbuffer.append(s);
                int i6 = j1 + i1;
                int j6 = i6 + 1;
                boolean flag5;
                byte byte0;
                int k6;
                if (s.charAt(j6) == '-')
                {
                    flag5 = true;
                } else
                {
                    flag5 = false;
                }
                k1 = 0;
                if (flag5)
                {
                    byte0 = 2;
                } else
                {
                    byte0 = 1;
                }
                k6 = i6 + byte0;
                do
                {
                    int l6 = stringbuffer.length();
                    if (k6 >= l6)
                    {
                        break;
                    }
                    k1 = k1 * 10 + (-48 + stringbuffer.charAt(k6));
                    k6++;
                } while (true);
                if (flag5)
                {
                    k1 = -k1;
                }
                stringbuffer.setLength(i6);
            } else
            {
                k1 = RealNum.toStringScientific(s, stringbuffer);
            }
            if (flag)
            {
                i1++;
            }
            stringbuffer.deleteCharAt(i1 + 1);
            l1 = stringbuffer.length() - i1;
            if (l1 > 1 && stringbuffer.charAt(-1 + (i1 + l1)) == '0')
            {
                l1--;
                stringbuffer.setLength(i1 + l1);
            }
            i2 = -1 + (l1 - k1);
        } else
        {
            int i7;
            int j7;
            int k7;
            if (i > 0)
            {
                i7 = 1;
            } else
            {
                i7 = i;
            }
            l1 = j + i7;
            j7 = (int)(1000D + Math.log(d) / LOG10);
            if (j7 == 0x80000000)
            {
                k7 = 0;
            } else
            {
                k7 = j7 - 1000;
            }
            i2 = -1 + (l1 - k7);
            RealNum.toScaledInt(d, i2).format(10, stringbuffer);
            k1 = l1 - 1 - i2;
        }
        j2 = k1 - (i - 1);
        if (j2 < 0)
        {
            k2 = -j2;
        } else
        {
            k2 = j2;
        }
        if (k2 >= 1000)
        {
            l2 = 4;
        } else
        if (k2 >= 100)
        {
            l2 = 3;
        } else
        if (k2 >= 10)
        {
            l2 = 2;
        } else
        {
            l2 = 1;
        }
_L9:
        if (expDigits > l2)
        {
            l2 = expDigits;
        }
        flag2 = true;
        if (!general)
        {
            i3 = 0;
        } else
        if (expDigits > 0)
        {
            i3 = 2 + expDigits;
        } else
        {
            i3 = 4;
        }
        if (j < 0)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (!general && !flag3) goto _L2; else goto _L1
_L1:
        j3 = l1 - i2;
        if (flag3)
        {
            if (j3 < 7)
            {
                j = j3;
            } else
            {
                j = 7;
            }
            if (l1 > j)
            {
                j = l1;
            }
        }
        k3 = j - j3;
        if (!general || j3 < 0 || k3 < 0) goto _L4; else goto _L3
_L3:
        l1 = j;
        i = j3;
        flag2 = false;
_L2:
        for (l3 = i1 + l1; stringbuffer.length() < l3; stringbuffer.append('0')) { }
        break MISSING_BLOCK_LABEL_763;
_L4:
        if (!flag3)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (width > 0)
        {
            break; /* Loop/switch isn't completed */
        }
        l1 = j;
_L6:
        if (l1 <= 0)
        {
            l1 = 1;
        }
        if (true) goto _L2; else goto _L5
_L5:
        l1 = -3 + (width - l - l2);
        if (i < 0)
        {
            l1 -= i;
        }
        if (l1 > j)
        {
            l1 = j;
        }
          goto _L6
        if (true) goto _L2; else goto _L7
_L7:
        char c;
        boolean flag4;
        int i4;
        if (l3 == stringbuffer.length())
        {
            c = '0';
        } else
        {
            c = stringbuffer.charAt(l3);
        }
        if (c >= 53)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        if (flag4 && addOne(stringbuffer, i1, l3))
        {
            i2++;
        }
        i2 - (stringbuffer.length() - l3);
        stringbuffer.setLength(l3);
        i4 = i1;
        if (i < 0)
        {
            for (int l5 = i; ++l5 <= 0;)
            {
                stringbuffer.insert(i1, '0');
            }

        } else
        {
            for (; i1 + i > l3; l3++)
            {
                stringbuffer.append('0');
            }

            i4 += i;
        }
        if (flag1)
        {
            flag2 = false;
        } else
        {
            stringbuffer.insert(i4, '.');
        }
        if (flag2)
        {
            stringbuffer.append(exponentChar);
            if (exponentShowSign || j2 < 0)
            {
                char c1;
                int i5;
                int j5;
                int k5;
                if (j2 >= 0)
                {
                    c1 = '+';
                } else
                {
                    c1 = '-';
                }
                stringbuffer.append(c1);
            }
            i5 = stringbuffer.length();
            stringbuffer.append(k2);
            j5 = stringbuffer.length();
            k5 = expDigits - (j5 - i5);
            if (k5 > 0)
            {
                j5 + k5;
                while (--k5 >= 0) 
                {
                    stringbuffer.insert(i5, '0');
                }
            }
        } else
        {
            l2 = 0;
        }
        int j4 = stringbuffer.length() - k;
        int k4 = width - j4;
        if (flag3 && (i4 + 1 == stringbuffer.length() || stringbuffer.charAt(i4 + 1) == exponentChar) && (width <= 0 || k4 > 0))
        {
            k4--;
            stringbuffer.insert(i4 + 1, '0');
        }
        if ((k4 >= 0 || width <= 0) && (!flag2 || l2 <= expDigits || expDigits <= 0 || overflowChar == 0))
        {
            if (i <= 0 && (k4 > 0 || width <= 0))
            {
                stringbuffer.insert(i1, '0');
                k4--;
            }
            if (!flag2 && width > 0)
            {
                while (--i3 >= 0) 
                {
                    stringbuffer.append(' ');
                    k4--;
                }
            }
            while (--k4 >= 0) 
            {
                stringbuffer.insert(k, padChar);
            }
        } else
        if (overflowChar != 0)
        {
            stringbuffer.setLength(k);
            for (int l4 = width; --l4 >= 0;)
            {
                stringbuffer.append(overflowChar);
            }

        }
        return stringbuffer;
        if (true) goto _L9; else goto _L8
_L8:
    }

    public StringBuffer format(double d, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        String s;
        if (fracDigits < 0)
        {
            s = Double.toString(d);
        } else
        {
            s = null;
        }
        return format(d, s, stringbuffer, fieldposition);
    }

    public StringBuffer format(float f, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        double d = f;
        String s;
        if (fracDigits < 0)
        {
            s = Float.toString(f);
        } else
        {
            s = null;
        }
        return format(d, s, stringbuffer, fieldposition);
    }

    public StringBuffer format(long l, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        return format(l, stringbuffer, fieldposition);
    }

    public StringBuffer format(Object obj, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        return format(((RealNum)obj).doubleValue(), stringbuffer, fieldposition);
    }

    public Number parse(String s, ParsePosition parseposition)
    {
        throw new Error("ExponentialFormat.parse - not implemented");
    }

    public Object parseObject(String s, ParsePosition parseposition)
    {
        throw new Error("ExponentialFormat.parseObject - not implemented");
    }

}
