// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.math;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

// Referenced classes of package gnu.math:
//            DFloNum, ExponentialFormat, IntNum, RealNum, 
//            Complex, RatNum

public class FixedRealFormat extends Format
{

    private int d;
    private int i;
    public boolean internalPad;
    public char overflowChar;
    public char padChar;
    public int scale;
    public boolean showPlus;
    public int width;

    public FixedRealFormat()
    {
    }

    private void format(StringBuffer stringbuffer, FieldPosition fieldposition, int j, int k, int l, int i1, int j1)
    {
label0:
        {
            int _tmp = k + l;
            int k1 = getMinimumIntegerDigits();
            int l1;
            int i2;
            int j2;
            if (k >= 0 && k > k1)
            {
                l1 = 0;
            } else
            {
                l1 = k1 - k;
            }
            if (k + l1 <= 0 && (width <= 0 || width > i1 + (l + 1)))
            {
                l1++;
            }
            i2 = 1 + (l1 + (i1 + j));
            j2 = width - i2;
            for (int k2 = l1; --k2 >= 0;)
            {
                stringbuffer.insert(j1 + i1, '0');
            }

            if (j2 >= 0)
            {
                int i3 = j1;
                if (internalPad && i1 > 0)
                {
                    i3++;
                }
                while (--j2 >= 0) 
                {
                    stringbuffer.insert(i3, padChar);
                }
            } else
            if (overflowChar != 0)
            {
                stringbuffer.setLength(j1);
                i = width;
                do
                {
                    int l2 = -1 + i;
                    i = l2;
                    if (l2 < 0)
                    {
                        break;
                    }
                    stringbuffer.append(overflowChar);
                } while (true);
                break label0;
            }
            stringbuffer.insert(stringbuffer.length() - l, '.');
        }
    }

    public StringBuffer format(double d1, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        if (Double.isNaN(d1) || Double.isInfinite(d1))
        {
            return stringbuffer.append(d1);
        }
        if (getMaximumFractionDigits() >= 0)
        {
            format(((RealNum) (DFloNum.toExact(d1))), stringbuffer, fieldposition);
            return stringbuffer;
        }
        boolean flag;
        int j;
        int k;
        String s;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        if (d1 < 0.0D)
        {
            flag = true;
            d1 = -d1;
        } else
        {
            flag = false;
        }
        j = stringbuffer.length();
        k = 1;
        if (flag)
        {
            stringbuffer.append('-');
        } else
        if (showPlus)
        {
            stringbuffer.append('+');
        } else
        {
            k = 0;
        }
        s = Double.toString(d1);
        l = scale;
        i1 = s.indexOf('E');
        if (i1 >= 0)
        {
            int i5 = i1 + 1;
            if (s.charAt(i5) == '+')
            {
                i5++;
            }
            l += Integer.parseInt(s.substring(i5));
            s = s.substring(0, i1);
        }
        j1 = s.indexOf('.');
        k1 = s.length();
        if (j1 >= 0)
        {
            l -= -1 + (k1 - j1);
            int _tmp = k1 - 1;
            StringBuilder stringbuilder = (new StringBuilder()).append(s.substring(0, j1));
            int l4 = j1 + 1;
            s = stringbuilder.append(s.substring(l4)).toString();
        }
        l1 = s.length();
        i2 = 0;
        do
        {
            int j2 = l1 - 1;
            if (i2 >= j2 || s.charAt(i2) != '0')
            {
                break;
            }
            i2++;
        } while (true);
        if (i2 > 0)
        {
            s = s.substring(i2);
            l1 -= i2;
        }
        int k2 = l1 + l;
        int i3;
        if (width > 0)
        {
            while (k2 < 0) 
            {
                stringbuffer.append('0');
                k2++;
                l1++;
            }
            i3 = (-1 + (width - k)) - k2;
        } else
        {
            int l2;
            if (l1 > 16)
            {
                l2 = 16;
            } else
            {
                l2 = l1;
            }
            i3 = l2 - k2;
        }
        if (i3 < 0)
        {
            i3 = 0;
        }
        stringbuffer.append(s);
        while (l > 0) 
        {
            stringbuffer.append('0');
            l--;
            l1++;
        }
        int j3 = j + k;
        int k3 = i3 + (j3 + k2);
        int l3 = stringbuffer.length();
        char c;
        boolean flag1;
        byte byte0;
        if (k3 >= l3)
        {
            k3 = l3;
            c = '0';
        } else
        {
            c = stringbuffer.charAt(k3);
        }
        if (c >= 53)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1)
        {
            byte0 = 57;
        } else
        {
            byte0 = 48;
        }
        for (; k3 > j3 + k2 && stringbuffer.charAt(k3 - 1) == byte0; k3--) { }
        int i4 = k3 - j3;
        int j4 = i4 - k2;
        if (flag1 && ExponentialFormat.addOne(stringbuffer, j3, k3))
        {
            k2++;
            j4 = 0;
            i4 = k2;
        }
        if (j4 == 0 && (width <= 0 || 1 + (k + k2) < width))
        {
            j4 = 1;
            i4++;
            stringbuffer.insert(j3 + k2, '0');
        }
        stringbuffer.setLength(j3 + i4);
        int k4;
        if (flag)
        {
            k4 = 1;
        } else
        {
            k4 = 0;
        }
        format(stringbuffer, fieldposition, i4, k2, j4, k4, j);
        return stringbuffer;
    }

    public StringBuffer format(long l, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        format(((RealNum) (IntNum.make(l))), stringbuffer, fieldposition);
        return stringbuffer;
    }

    public StringBuffer format(Object obj, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        RealNum realnum = RealNum.asRealNumOrNull(obj);
        if (realnum == null)
        {
            if (obj instanceof Complex)
            {
                String s = obj.toString();
                for (int j = width - s.length(); --j >= 0;)
                {
                    stringbuffer.append(' ');
                }

                stringbuffer.append(s);
                return stringbuffer;
            }
            realnum = (RealNum)obj;
        }
        return format(realnum.doubleValue(), stringbuffer, fieldposition);
    }

    public void format(RealNum realnum, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        if (realnum instanceof RatNum)
        {
            int j = getMaximumFractionDigits();
            if (j >= 0)
            {
                RatNum ratnum = (RatNum)realnum;
                boolean flag = ratnum.isNegative();
                if (flag)
                {
                    ratnum = ratnum.rneg();
                }
                int k = stringbuffer.length();
                int l = 1;
                String s;
                int i1;
                if (flag)
                {
                    stringbuffer.append('-');
                } else
                if (showPlus)
                {
                    stringbuffer.append('+');
                } else
                {
                    l = 0;
                }
                s = RealNum.toScaledInt(ratnum, j + scale).toString();
                stringbuffer.append(s);
                i1 = s.length();
                format(stringbuffer, fieldposition, i1, i1 - j, j, l, k);
                return;
            }
        }
        format(realnum.doubleValue(), stringbuffer, fieldposition);
    }

    public int getMaximumFractionDigits()
    {
        return d;
    }

    public int getMinimumIntegerDigits()
    {
        return i;
    }

    public Number parse(String s, ParsePosition parseposition)
    {
        throw new Error("RealFixedFormat.parse - not implemented");
    }

    public Object parseObject(String s, ParsePosition parseposition)
    {
        throw new Error("RealFixedFormat.parseObject - not implemented");
    }

    public void setMaximumFractionDigits(int j)
    {
        d = j;
    }

    public void setMinimumIntegerDigits(int j)
    {
        i = j;
    }
}
