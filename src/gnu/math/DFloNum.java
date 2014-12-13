// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Referenced classes of package gnu.math:
//            RealNum, IntNum, RatNum, Numeric

public class DFloNum extends RealNum
    implements Externalizable
{

    private static final DFloNum one = new DFloNum(1.0D);
    double value;

    public DFloNum()
    {
    }

    public DFloNum(double d)
    {
        value = d;
    }

    public DFloNum(String s)
        throws NumberFormatException
    {
        value = Double.valueOf(s).doubleValue();
        if (value == 0.0D && s.charAt(0) == '-')
        {
            value = 0.0D;
        }
    }

    public static DFloNum asDFloNumOrNull(Object obj)
    {
        if (obj instanceof DFloNum)
        {
            return (DFloNum)obj;
        }
        if ((obj instanceof RealNum) || (obj instanceof Number))
        {
            return new DFloNum(((Number)obj).doubleValue());
        } else
        {
            return null;
        }
    }

    public static int compare(double d, double d1)
    {
        if (d > d1)
        {
            return 1;
        }
        if (d < d1)
        {
            return -1;
        }
        return d != d1 ? -2 : 0;
    }

    public static int compare(IntNum intnum, IntNum intnum1, double d)
    {
        int i = 1;
        if (Double.isNaN(d))
        {
            i = -2;
        } else
        if (Double.isInfinite(d))
        {
            if (d >= 0.0D)
            {
                i = -1;
            }
            if (intnum1.isZero())
            {
                if (intnum.isZero())
                {
                    return -2;
                }
                i >>= 1;
                if (!intnum.isNegative())
                {
                    return ~i;
                }
            }
        } else
        {
            long l = Double.doubleToLongBits(d);
            int j;
            int k;
            long l1;
            long l2;
            IntNum intnum2;
            if (l < 0L)
            {
                j = i;
            } else
            {
                j = 0;
            }
            k = 0x7ff & (int)(l >> 52);
            l1 = l & 0xfffffffffffffL;
            if (k == 0)
            {
                l2 = l1 << i;
            } else
            {
                l2 = l1 | 0x10000000000000L;
            }
            if (j != 0)
            {
                l2 = -l2;
            }
            intnum2 = IntNum.make(l2);
            if (k >= 1075)
            {
                intnum2 = IntNum.shift(intnum2, k - 1075);
            } else
            {
                intnum = IntNum.shift(intnum, 1075 - k);
            }
            return IntNum.compare(intnum, IntNum.times(intnum2, intnum1));
        }
        return i;
    }

    public static DFloNum make(double d)
    {
        return new DFloNum(d);
    }

    public static final DFloNum one()
    {
        return one;
    }

    public static RatNum toExact(double d)
    {
        int i = 1;
        if (Double.isInfinite(d))
        {
            if (d < 0.0D)
            {
                i = -1;
            }
            return RatNum.infinity(i);
        }
        if (Double.isNaN(d))
        {
            throw new ArithmeticException("cannot convert NaN to exact rational");
        }
        long l = Double.doubleToLongBits(d);
        int j;
        int k;
        long l1;
        long l2;
        IntNum intnum;
        if (l < 0L)
        {
            j = i;
        } else
        {
            j = 0;
        }
        k = 0x7ff & (int)(l >> 52);
        l1 = l & 0xfffffffffffffL;
        if (k == 0)
        {
            l2 = l1 << i;
        } else
        {
            l2 = l1 | 0x10000000000000L;
        }
        if (j != 0)
        {
            l2 = -l2;
        }
        intnum = IntNum.make(l2);
        if (k >= 1075)
        {
            return IntNum.shift(intnum, k - 1075);
        } else
        {
            return RatNum.make(intnum, IntNum.shift(IntNum.one(), 1075 - k));
        }
    }

    public Numeric add(Object obj, int i)
    {
        if (obj instanceof RealNum)
        {
            return new DFloNum(value + (double)i * ((RealNum)obj).doubleValue());
        }
        if (!(obj instanceof Numeric))
        {
            throw new IllegalArgumentException();
        } else
        {
            return ((Numeric)obj).addReversed(this, i);
        }
    }

    public Numeric addReversed(Numeric numeric, int i)
    {
        if (numeric instanceof RealNum)
        {
            return new DFloNum(((RealNum)numeric).doubleValue() + (double)i * value);
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    public int compare(Object obj)
    {
        if (obj instanceof RatNum)
        {
            RatNum ratnum = (RatNum)obj;
            int i = compare(ratnum.numerator(), ratnum.denominator(), value);
            if (i < -1)
            {
                return i;
            } else
            {
                return -i;
            }
        } else
        {
            return compare(value, ((RealNum)obj).doubleValue());
        }
    }

    public int compareReversed(Numeric numeric)
    {
        if (numeric instanceof RatNum)
        {
            RatNum ratnum = (RatNum)numeric;
            return compare(ratnum.numerator(), ratnum.denominator(), value);
        } else
        {
            return compare(((RealNum)numeric).doubleValue(), value);
        }
    }

    public Numeric div(Object obj)
    {
        if (obj instanceof RealNum)
        {
            return new DFloNum(value / ((RealNum)obj).doubleValue());
        }
        if (!(obj instanceof Numeric))
        {
            throw new IllegalArgumentException();
        } else
        {
            return ((Numeric)obj).divReversed(this);
        }
    }

    public Numeric divReversed(Numeric numeric)
    {
        if (numeric instanceof RealNum)
        {
            return new DFloNum(((RealNum)numeric).doubleValue() / value);
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    public final double doubleValue()
    {
        return value;
    }

    public boolean equals(Object obj)
    {
        return obj != null && (obj instanceof DFloNum) && Double.doubleToLongBits(((DFloNum)obj).value) == Double.doubleToLongBits(value);
    }

    public int hashCode()
    {
        return (int)value;
    }

    public boolean isExact()
    {
        return false;
    }

    public boolean isNegative()
    {
        return value < 0.0D;
    }

    public boolean isZero()
    {
        return value == 0.0D;
    }

    public long longValue()
    {
        return (long)value;
    }

    public Numeric mul(Object obj)
    {
        if (obj instanceof RealNum)
        {
            return new DFloNum(value * ((RealNum)obj).doubleValue());
        }
        if (!(obj instanceof Numeric))
        {
            throw new IllegalArgumentException();
        } else
        {
            return ((Numeric)obj).mulReversed(this);
        }
    }

    public Numeric mulReversed(Numeric numeric)
    {
        if (numeric instanceof RealNum)
        {
            return new DFloNum(((RealNum)numeric).doubleValue() * value);
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    public Numeric neg()
    {
        return new DFloNum(-value);
    }

    public Numeric power(IntNum intnum)
    {
        return new DFloNum(Math.pow(doubleValue(), intnum.doubleValue()));
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        value = objectinput.readDouble();
    }

    public int sign()
    {
        if (value > 0.0D)
        {
            return 1;
        }
        if (value < 0.0D)
        {
            return -1;
        }
        return value != 0.0D ? -2 : 0;
    }

    public String toString()
    {
        if (value == (1.0D / 0.0D))
        {
            return "+inf.0";
        }
        if (value == (-1.0D / 0.0D))
        {
            return "-inf.0";
        }
        if (Double.isNaN(value))
        {
            return "+nan.0";
        } else
        {
            return Double.toString(value);
        }
    }

    public String toString(int i)
    {
        if (i == 10)
        {
            return toString();
        } else
        {
            return (new StringBuilder()).append("#d").append(toString()).toString();
        }
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeDouble(value);
    }

}
