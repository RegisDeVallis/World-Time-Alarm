// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.math;

import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package gnu.math:
//            RealNum, IntNum, IntFraction, DFloNum, 
//            Numeric, Complex

public abstract class RatNum extends RealNum
{

    public static final IntNum ten_exp_9 = IntNum.make(0x3b9aca00);

    public RatNum()
    {
    }

    public static RatNum add(RatNum ratnum, RatNum ratnum1, int i)
    {
        IntNum intnum = ratnum.numerator();
        IntNum intnum1 = ratnum.denominator();
        IntNum intnum2 = ratnum1.numerator();
        IntNum intnum3 = ratnum1.denominator();
        if (IntNum.equals(intnum1, intnum3))
        {
            return make(IntNum.add(intnum, intnum2, i), intnum1);
        } else
        {
            return make(IntNum.add(IntNum.times(intnum3, intnum), IntNum.times(intnum2, intnum1), i), IntNum.times(intnum1, intnum3));
        }
    }

    public static RatNum asRatNumOrNull(Object obj)
    {
        if (obj instanceof RatNum)
        {
            return (RatNum)obj;
        }
        if (obj instanceof BigDecimal)
        {
            return valueOf((BigDecimal)obj);
        } else
        {
            return IntNum.asIntNumOrNull(obj);
        }
    }

    public static int compare(RatNum ratnum, RatNum ratnum1)
    {
        return IntNum.compare(IntNum.times(ratnum.numerator(), ratnum1.denominator()), IntNum.times(ratnum1.numerator(), ratnum.denominator()));
    }

    public static RatNum divide(RatNum ratnum, RatNum ratnum1)
    {
        return make(IntNum.times(ratnum.numerator(), ratnum1.denominator()), IntNum.times(ratnum.denominator(), ratnum1.numerator()));
    }

    public static boolean equals(RatNum ratnum, RatNum ratnum1)
    {
        return IntNum.equals(ratnum.numerator(), ratnum1.numerator()) && IntNum.equals(ratnum.denominator(), ratnum1.denominator());
    }

    public static RatNum infinity(int i)
    {
        return new IntFraction(IntNum.make(i), IntNum.zero());
    }

    public static RatNum make(IntNum intnum, IntNum intnum1)
    {
        IntNum intnum2 = IntNum.gcd(intnum, intnum1);
        if (intnum1.isNegative())
        {
            intnum2 = IntNum.neg(intnum2);
        }
        if (!intnum2.isOne())
        {
            intnum = IntNum.quotient(intnum, intnum2);
            intnum1 = IntNum.quotient(intnum1, intnum2);
        }
        if (intnum1.isOne())
        {
            return intnum;
        } else
        {
            return new IntFraction(intnum, intnum1);
        }
    }

    public static RatNum neg(RatNum ratnum)
    {
        IntNum intnum = ratnum.numerator();
        IntNum intnum1 = ratnum.denominator();
        return make(IntNum.neg(intnum), intnum1);
    }

    public static RealNum rationalize(RealNum realnum, RealNum realnum1)
    {
        if (realnum.grt(realnum1))
        {
            realnum = simplest_rational2(realnum1, realnum);
        } else
        if (realnum1.grt(realnum))
        {
            if (realnum.sign() > 0)
            {
                return simplest_rational2(realnum, realnum1);
            }
            if (realnum1.isNegative())
            {
                return (RealNum)simplest_rational2((RealNum)realnum1.neg(), (RealNum)realnum.neg()).neg();
            } else
            {
                return IntNum.zero();
            }
        }
        return realnum;
    }

    private static RealNum simplest_rational2(RealNum realnum, RealNum realnum1)
    {
        RealNum realnum2 = realnum.toInt(1);
        RealNum realnum3 = realnum1.toInt(1);
        if (!realnum.grt(realnum2))
        {
            return realnum2;
        }
        if (realnum2.equals(realnum3))
        {
            RealNum realnum4 = (RealNum)IntNum.one().div(realnum1.sub(realnum3));
            RealNum realnum5 = (RealNum)IntNum.one().div(realnum.sub(realnum2));
            return (RealNum)realnum2.add(IntNum.one().div(simplest_rational2(realnum4, realnum5)), 1);
        } else
        {
            return (RealNum)realnum2.add(IntNum.one(), 1);
        }
    }

    public static RatNum times(RatNum ratnum, RatNum ratnum1)
    {
        return make(IntNum.times(ratnum.numerator(), ratnum1.numerator()), IntNum.times(ratnum.denominator(), ratnum1.denominator()));
    }

    public static RatNum valueOf(BigDecimal bigdecimal)
    {
        Object obj;
        int i;
        obj = IntNum.valueOf(bigdecimal.unscaledValue().toString(), 10);
        for (i = bigdecimal.scale(); i >= 9; i -= 9)
        {
            obj = divide(((RatNum) (obj)), ten_exp_9);
        }

        for (; i <= -9; i += 9)
        {
            obj = times(((RatNum) (obj)), ten_exp_9);
        }

        int j;
        if (i > 0)
        {
            j = i;
        } else
        {
            j = -i;
        }
        j;
        JVM INSTR tableswitch 1 8: default 112
    //                   1 120
    //                   2 138
    //                   3 148
    //                   4 159
    //                   5 170
    //                   6 180
    //                   7 190
    //                   8 200;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9
_L9:
        break MISSING_BLOCK_LABEL_200;
_L1:
        return ((RatNum) (obj));
_L2:
        IntNum intnum = IntNum.make(10);
_L10:
        if (i > 0)
        {
            return divide(((RatNum) (obj)), intnum);
        } else
        {
            return times(((RatNum) (obj)), intnum);
        }
_L3:
        intnum = IntNum.make(100);
          goto _L10
_L4:
        intnum = IntNum.make(1000);
          goto _L10
_L5:
        intnum = IntNum.make(10000);
          goto _L10
_L6:
        intnum = IntNum.make(0x186a0);
          goto _L10
_L7:
        intnum = IntNum.make(0xf4240);
          goto _L10
_L8:
        intnum = IntNum.make(0x989680);
          goto _L10
        intnum = IntNum.make(0x5f5e100);
          goto _L10
    }

    public abstract IntNum denominator();

    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof RatNum))
        {
            return false;
        } else
        {
            return equals(this, (RatNum)obj);
        }
    }

    public boolean isExact()
    {
        return true;
    }

    public boolean isZero()
    {
        return numerator().isZero();
    }

    public abstract IntNum numerator();

    public Numeric power(IntNum intnum)
    {
        boolean flag;
        if (intnum.isNegative())
        {
            flag = true;
            intnum = IntNum.neg(intnum);
        } else
        {
            flag = false;
        }
        if (intnum.words == null)
        {
            IntNum intnum1 = IntNum.power(numerator(), intnum.ival);
            IntNum intnum2 = IntNum.power(denominator(), intnum.ival);
            if (flag)
            {
                return make(intnum2, intnum1);
            } else
            {
                return make(intnum1, intnum2);
            }
        }
        double d = doubleValue();
        boolean flag1;
        double d1;
        if (d < 0.0D && intnum.isOdd())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        d1 = Math.pow(d, intnum.doubleValue());
        if (flag)
        {
            d1 = 1.0D / d1;
        }
        if (flag1)
        {
            d1 = -d1;
        }
        return new DFloNum(d1);
    }

    public final RatNum rneg()
    {
        return neg(this);
    }

    public volatile RealNum rneg()
    {
        return rneg();
    }

    public volatile Complex toExact()
    {
        return toExact();
    }

    public volatile Numeric toExact()
    {
        return toExact();
    }

    public final RatNum toExact()
    {
        return this;
    }

    public IntNum toExactInt(int i)
    {
        return IntNum.quotient(numerator(), denominator(), i);
    }

    public RealNum toInt(int i)
    {
        return IntNum.quotient(numerator(), denominator(), i);
    }

}
