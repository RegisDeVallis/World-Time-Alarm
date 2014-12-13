// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.math;

import java.math.BigDecimal;

// Referenced classes of package gnu.math:
//            Complex, DFloNum, RatNum, IntNum, 
//            DComplex, Numeric

public abstract class RealNum extends Complex
    implements Comparable
{

    public RealNum()
    {
    }

    public static RealNum add(RealNum realnum, RealNum realnum1, int i)
    {
        return (RealNum)(RealNum)realnum.add(realnum1, i);
    }

    public static RealNum asRealNumOrNull(Object obj)
    {
        if (obj instanceof RealNum)
        {
            return (RealNum)obj;
        }
        if ((obj instanceof Float) || (obj instanceof Double))
        {
            return new DFloNum(((Number)obj).doubleValue());
        } else
        {
            return RatNum.asRatNumOrNull(obj);
        }
    }

    public static RealNum divide(RealNum realnum, RealNum realnum1)
    {
        return (RealNum)(RealNum)realnum.div(realnum1);
    }

    public static RealNum times(RealNum realnum, RealNum realnum1)
    {
        return (RealNum)(RealNum)realnum.mul(realnum1);
    }

    public static IntNum toExactInt(double d)
    {
        if (Double.isInfinite(d) || Double.isNaN(d))
        {
            throw new ArithmeticException((new StringBuilder()).append("cannot convert ").append(d).append(" to exact integer").toString());
        }
        long l = Double.doubleToLongBits(d);
        boolean flag;
        int i;
        long l1;
        long l2;
        if (l < 0L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        i = 0x7ff & (int)(l >> 52);
        l1 = l & 0xfffffffffffffL;
        if (i == 0)
        {
            l2 = l1 << 1;
        } else
        {
            l2 = l1 | 0x10000000000000L;
        }
        if (i <= 1075)
        {
            int j = 1075 - i;
            if (j > 53)
            {
                return IntNum.zero();
            }
            long l4 = l2 >> j;
            long l5;
            if (flag)
            {
                l5 = -l4;
            } else
            {
                l5 = l4;
            }
            return IntNum.make(l5);
        }
        long l3;
        if (flag)
        {
            l3 = -l2;
        } else
        {
            l3 = l2;
        }
        return IntNum.shift(IntNum.make(l3), i - 1075);
    }

    public static IntNum toExactInt(double d, int i)
    {
        return toExactInt(toInt(d, i));
    }

    public static double toInt(double d, int i)
    {
        switch (i)
        {
        default:
            return d;

        case 1: // '\001'
            return Math.floor(d);

        case 2: // '\002'
            return Math.ceil(d);

        case 3: // '\003'
            if (d < 0.0D)
            {
                return Math.ceil(d);
            } else
            {
                return Math.floor(d);
            }

        case 4: // '\004'
            return Math.rint(d);
        }
    }

    public static IntNum toScaledInt(double d, int i)
    {
        return toScaledInt(DFloNum.toExact(d), i);
    }

    public static IntNum toScaledInt(RatNum ratnum, int i)
    {
        if (i != 0)
        {
            IntNum intnum = IntNum.ten();
            int j;
            IntNum intnum1;
            IntNum intnum2;
            IntNum intnum3;
            if (i < 0)
            {
                j = -i;
            } else
            {
                j = i;
            }
            intnum1 = IntNum.power(intnum, j);
            intnum2 = ratnum.numerator();
            intnum3 = ratnum.denominator();
            if (i >= 0)
            {
                intnum2 = IntNum.times(intnum2, intnum1);
            } else
            {
                intnum3 = IntNum.times(intnum3, intnum1);
            }
            ratnum = RatNum.make(intnum2, intnum3);
        }
        return ratnum.toExactInt(4);
    }

    public static String toStringDecimal(String s)
    {
        int i = s.indexOf('E');
        if (i >= 0)
        {
            int j = s.length();
            char c = s.charAt(j - 1);
            if (c != 'y' && c != 'N')
            {
                StringBuffer stringbuffer = new StringBuffer(j + 10);
                boolean flag;
                if (s.charAt(0) == '-')
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (s.charAt(i + 1) != '-')
                {
                    throw new Error((new StringBuilder()).append("not implemented: toStringDecimal given non-negative exponent: ").append(s).toString());
                }
                int k = i + 2;
                int l = 0;
                int i2;
                for (int i1 = k; i1 < j; i1 = i2)
                {
                    int l1 = l * 10;
                    i2 = i1 + 1;
                    l = l1 + (-48 + s.charAt(i1));
                }

                if (flag)
                {
                    stringbuffer.append('-');
                }
                stringbuffer.append("0.");
                while (--l > 0) 
                {
                    stringbuffer.append('0');
                }
                int j1 = 0;
                do
                {
                    int k1 = j1 + 1;
                    char c1 = s.charAt(j1);
                    if (c1 != 'E')
                    {
                        boolean flag1;
                        boolean flag2;
                        if (c1 != '-')
                        {
                            flag1 = true;
                        } else
                        {
                            flag1 = false;
                        }
                        if (c1 != '.')
                        {
                            flag2 = true;
                        } else
                        {
                            flag2 = false;
                        }
                        if (flag2 & flag1 && (c1 != '0' || k1 < i))
                        {
                            stringbuffer.append(c1);
                            j1 = k1;
                        } else
                        {
                            j1 = k1;
                        }
                    } else
                    {
                        return stringbuffer.toString();
                    }
                } while (true);
            }
        }
        return s;
    }

    public static int toStringScientific(String s, StringBuffer stringbuffer)
    {
        boolean flag;
        int i;
        int j;
        int l;
        int i2;
        int j2;
        if (s.charAt(0) == '-')
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            stringbuffer.append('-');
        }
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        j = s.length();
        if (s.charAt(i) != '0') goto _L2; else goto _L1
_L1:
        i2 = i;
        j2 = i;
_L9:
        int k2;
        if (j2 == j)
        {
            stringbuffer.append("0");
            l = 0;
            j2;
            break MISSING_BLOCK_LABEL_71;
        }
        k2 = j2 + 1;
        c2 = s.charAt(j2);
        if (c2 < '0' || c2 > '9' || c2 == '0' && k2 != j) goto _L4; else goto _L3
_L3:
        stringbuffer.append(c2);
        stringbuffer.append('.');
        if (c2 == '0')
        {
            l = 0;
        } else
        {
            l = 2 + (i2 - k2);
        }
        if (k2 != j)
        {
            break MISSING_BLOCK_LABEL_379;
        }
        stringbuffer.append('0');
        continue; /* Loop/switch isn't completed */
        i1 = k2;
        int l2;
        for (; i1 < j; i1 = l2)
        {
            l2 = i1 + 1;
            stringbuffer.append(s.charAt(i1));
        }

          goto _L5
_L4:
        j2 = k2;
        break MISSING_BLOCK_LABEL_379;
_L2:
        if (flag)
        {
            byte0 = 2;
        } else
        {
            byte0 = 1;
        }
        k = j - byte0;
        l = s.indexOf('.') + (k - j);
        i1 = i + 1;
        stringbuffer.append(s.charAt(i));
        stringbuffer.append('.');
        for (; i1 < j; i1 = l1)
        {
            l1 = i1 + 1;
            c1 = s.charAt(i1);
            if (c1 != '.')
            {
                stringbuffer.append(c1);
            }
        }

          goto _L5
_L7:
        int j1 = stringbuffer.length();
        int k1 = -1;
        char c;
        char c2;
        do
        {
            j1--;
            c = stringbuffer.charAt(j1);
            if (c != '0')
            {
                break;
            }
            k1 = j1;
        } while (true);
        if (c == '.')
        {
            k1 = j1 + 2;
        }
        if (k1 >= 0)
        {
            stringbuffer.setLength(k1);
        }
        return l;
_L5:
        i1;
        if (true) goto _L7; else goto _L6
_L6:
        if (true) goto _L9; else goto _L8
_L8:
    }

    public static String toStringScientific(double d)
    {
        return toStringScientific(Double.toString(d));
    }

    public static String toStringScientific(float f)
    {
        return toStringScientific(Float.toString(f));
    }

    public static String toStringScientific(String s)
    {
        if (s.indexOf('E') < 0)
        {
            int i = s.length();
            char c = s.charAt(i - 1);
            if (c != 'y' && c != 'N')
            {
                StringBuffer stringbuffer = new StringBuffer(i + 10);
                int j = toStringScientific(s, stringbuffer);
                stringbuffer.append('E');
                stringbuffer.append(j);
                return stringbuffer.toString();
            }
        }
        return s;
    }

    public Numeric abs()
    {
        if (isNegative())
        {
            this = neg();
        }
        return this;
    }

    public abstract Numeric add(Object obj, int i);

    public BigDecimal asBigDecimal()
    {
        return new BigDecimal(doubleValue());
    }

    public int compareTo(Object obj)
    {
        return compare(obj);
    }

    public abstract Numeric div(Object obj);

    public Complex exp()
    {
        return new DFloNum(Math.exp(doubleValue()));
    }

    public final RealNum im()
    {
        return IntNum.zero();
    }

    public abstract boolean isNegative();

    public boolean isZero()
    {
        return sign() == 0;
    }

    public Complex log()
    {
        double d = doubleValue();
        if (d < 0.0D)
        {
            return DComplex.log(d, 0.0D);
        } else
        {
            return new DFloNum(Math.log(d));
        }
    }

    public RealNum max(RealNum realnum)
    {
        boolean flag;
        Object obj;
        if (isExact() && realnum.isExact())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (grt(realnum))
        {
            obj = this;
        } else
        {
            obj = realnum;
        }
        if (!flag && ((RealNum) (obj)).isExact())
        {
            obj = new DFloNum(((RealNum) (obj)).doubleValue());
        }
        return ((RealNum) (obj));
    }

    public RealNum min(RealNum realnum)
    {
        boolean flag;
        Object obj;
        if (isExact() && realnum.isExact())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (grt(realnum))
        {
            obj = realnum;
        } else
        {
            obj = this;
        }
        if (!flag && ((RealNum) (obj)).isExact())
        {
            obj = new DFloNum(((RealNum) (obj)).doubleValue());
        }
        return ((RealNum) (obj));
    }

    public abstract Numeric mul(Object obj);

    public final RealNum re()
    {
        return this;
    }

    public RealNum rneg()
    {
        return (RealNum)neg();
    }

    public abstract int sign();

    public final Complex sin()
    {
        return new DFloNum(Math.sin(doubleValue()));
    }

    public final Complex sqrt()
    {
        double d = doubleValue();
        if (d >= 0.0D)
        {
            return new DFloNum(Math.sqrt(d));
        } else
        {
            return DComplex.sqrt(d, 0.0D);
        }
    }

    public volatile Complex toExact()
    {
        return toExact();
    }

    public volatile Numeric toExact()
    {
        return toExact();
    }

    public RatNum toExact()
    {
        return DFloNum.toExact(doubleValue());
    }

    public IntNum toExactInt(int i)
    {
        return toExactInt(doubleValue(), i);
    }

    public volatile Complex toInexact()
    {
        return toInexact();
    }

    public volatile Numeric toInexact()
    {
        return toInexact();
    }

    public RealNum toInexact()
    {
        if (isExact())
        {
            this = new DFloNum(doubleValue());
        }
        return this;
    }

    public RealNum toInt(int i)
    {
        return new DFloNum(toInt(doubleValue(), i));
    }

    public IntNum toScaledInt(int i)
    {
        return toScaledInt(toExact(), i);
    }
}
