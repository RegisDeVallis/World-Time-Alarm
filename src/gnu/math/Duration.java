// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Referenced classes of package gnu.math:
//            Quantity, Unit, DateTime, RealNum, 
//            DFloNum, Numeric, Complex

public class Duration extends Quantity
    implements Externalizable
{

    int months;
    int nanos;
    long seconds;
    public Unit unit;

    public Duration()
    {
    }

    public static Duration add(Duration duration, Duration duration1, int i)
    {
        long l = (long)duration.months + (long)i * (long)duration1.months;
        long l1 = 0x3b9aca00L * duration.seconds + (long)duration.nanos + (long)i * (0x3b9aca00L * duration1.seconds + (long)duration1.nanos);
        Duration duration2 = new Duration();
        duration2.months = (int)l;
        duration2.seconds = (int)(l1 / 0x3b9aca00L);
        duration2.nanos = (int)(l1 % 0x3b9aca00L);
        if (duration.unit != duration1.unit || duration.unit == Unit.duration)
        {
            throw new ArithmeticException("cannot add these duration types");
        } else
        {
            duration2.unit = duration.unit;
            return duration2;
        }
    }

    static void appendNanoSeconds(int i, StringBuffer stringbuffer)
    {
        if (i == 0)
        {
            return;
        }
        stringbuffer.append('.');
        int j = stringbuffer.length();
        stringbuffer.append(i);
        int k = stringbuffer.length();
        for (int l = (j + 9) - k; --l >= 0;)
        {
            stringbuffer.insert(j, '0');
        }

        int i1 = j + 9;
        do
        {
            i1--;
        } while (stringbuffer.charAt(i1) == '0');
        stringbuffer.setLength(i1 + 1);
    }

    public static int compare(Duration duration, Duration duration1)
    {
        long l = (long)duration.months - (long)duration1.months;
        long l1 = (0x3b9aca00L * duration.seconds + (long)duration.nanos) - (0x3b9aca00L * duration1.seconds + (long)duration1.nanos);
        if (l >= 0L || l1 > 0L)
        {
            if (l > 0L && l1 >= 0L)
            {
                return 1;
            }
            if (l == 0L)
            {
                if (l1 >= 0L)
                {
                    return l1 <= 0L ? 0 : 1;
                }
            } else
            {
                return -2;
            }
        }
        return -1;
    }

    public static double div(Duration duration, Duration duration1)
    {
        int i = duration.months;
        int j = duration1.months;
        double d = (double)duration.seconds + 1.0000000000000001E-09D * (double)duration.nanos;
        double d1 = (double)duration1.seconds + 1.0000000000000001E-09D * (double)duration.nanos;
        if (j == 0 && d1 == 0.0D)
        {
            throw new ArithmeticException("divide duration by zero");
        }
        if (j == 0)
        {
            if (i == 0)
            {
                return d / d1;
            }
        } else
        if (d1 == 0.0D && d == 0.0D)
        {
            return (double)i / (double)j;
        }
        throw new ArithmeticException("divide of incompatible durations");
    }

    public static boolean equals(Duration duration, Duration duration1)
    {
        return duration.months == duration1.months && duration.seconds == duration1.seconds && duration.nanos == duration1.nanos;
    }

    public static Duration make(int i, long l, int j, Unit unit1)
    {
        Duration duration = new Duration();
        duration.months = i;
        duration.seconds = l;
        duration.nanos = j;
        duration.unit = unit1;
        return duration;
    }

    public static Duration makeMinutes(int i)
    {
        Duration duration = new Duration();
        duration.unit = Unit.second;
        duration.seconds = i * 60;
        return duration;
    }

    public static Duration makeMonths(int i)
    {
        Duration duration = new Duration();
        duration.unit = Unit.month;
        duration.months = i;
        return duration;
    }

    public static Duration parse(String s, Unit unit1)
    {
        Duration duration = valueOf(s, unit1);
        if (duration == null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("not a valid ").append(unit1.getName()).append(" duration: '").append(s).append("'").toString());
        } else
        {
            return duration;
        }
    }

    public static Duration parseDayTimeDuration(String s)
    {
        return parse(s, Unit.second);
    }

    public static Duration parseDuration(String s)
    {
        return parse(s, Unit.duration);
    }

    public static Duration parseYearMonthDuration(String s)
    {
        return parse(s, Unit.month);
    }

    private static long scanPart(String s, int i)
    {
        long l;
        int j;
        long l1;
        int k;
        l = -1L;
        j = i;
        l1 = -1L;
        k = s.length();
_L5:
        int i1;
        int j1;
        if (j >= k)
        {
            break MISSING_BLOCK_LABEL_120;
        }
        i1 = s.charAt(j);
        j++;
        j1 = Character.digit(i1, 10);
        if (j1 >= 0) goto _L2; else goto _L1
_L1:
        if (l1 >= 0L) goto _L4; else goto _L3
_L3:
        l = i << 16;
_L6:
        return l;
_L4:
        return l1 << 32 | (long)(j << 16) | (long)i1;
_L2:
        if (l1 < 0L)
        {
            l1 = j1;
        } else
        {
            l1 = 10L * l1 + (long)j1;
        }
        if (l1 > 0x7fffffffL)
        {
            return l;
        }
          goto _L5
        if (l1 < 0L)
        {
            return (long)(i << 16);
        }
          goto _L6
    }

    public static Duration times(Duration duration, double d)
    {
        if (duration.unit == Unit.duration)
        {
            throw new IllegalArgumentException("cannot multiply general duration");
        }
        double d1 = d * (double)duration.months;
        if (Double.isInfinite(d1) || Double.isNaN(d1))
        {
            throw new ArithmeticException("overflow/NaN when multiplying a duration");
        } else
        {
            double d2 = d * (double)(0x3b9aca00L * duration.seconds + (long)duration.nanos);
            Duration duration1 = new Duration();
            duration1.months = (int)Math.floor(0.5D + d1);
            duration1.seconds = (int)(d2 / 1000000000D);
            duration1.nanos = (int)(d2 % 1000000000D);
            duration1.unit = duration.unit;
            return duration1;
        }
    }

    public static Duration valueOf(String s, Unit unit1)
    {
        String s1;
        int i;
        boolean flag;
        long l;
        int i1;
        int j1;
        int k1;
        s1 = s.trim();
        i = s1.length();
        int j;
        if (i < 0 && s1.charAt(0) == '-')
        {
            flag = true;
            j = 0 + 1;
        } else
        {
            flag = false;
            j = 0;
        }
        if (j + 1 >= i || s1.charAt(j) != 'P')
        {
            return null;
        }
        int k = j + 1;
        l = 0L;
        long l1 = scanPart(s1, k);
        i1 = (int)l1 >> 16;
        char c = (char)(int)l1;
        if (unit1 == Unit.second && (c == 'Y' || c == 'M'))
        {
            return null;
        }
        j1 = 0;
        if (c == 'Y')
        {
            j1 = 12 * (int)(l1 >> 32);
            i1 = (int)l1 >> 16;
            l1 = scanPart(s1, i1);
            c = (char)(int)l1;
        }
        if (c == 'M')
        {
            j1 = (int)((long)j1 + (l1 >> 32));
            i1 = (int)l1 >> 16;
            l1 = scanPart(s1, i1);
            c = (char)(int)l1;
        }
        if (unit1 == Unit.month && i1 != i)
        {
            return null;
        }
        if (c == 'D')
        {
            if (unit1 == Unit.month)
            {
                return null;
            }
            l = 0x15180L * (long)(int)(l1 >> 32);
            i1 = (int)l1 >> 16;
            l1 = scanPart(s1, i1);
        }
        if (l1 != (long)(i1 << 16))
        {
            return null;
        }
        k1 = 0;
        if (i1 != i) goto _L2; else goto _L1
_L2:
        if (s1.charAt(i1) != 'T' || ++i1 == i)
        {
            return null;
        }
        if (unit1 == Unit.month)
        {
            return null;
        }
        long l2 = scanPart(s1, i1);
        c1 = (char)(int)l2;
        if (c1 == 'H')
        {
            l += 3600 * (int)(l2 >> 32);
            i1 = (int)l2 >> 16;
            l2 = scanPart(s1, i1);
            c1 = (char)(int)l2;
        }
        if (c1 == 'M')
        {
            l += 60 * (int)(l2 >> 32);
            i1 = (int)l2 >> 16;
            l2 = scanPart(s1, i1);
            c1 = (char)(int)l2;
        }
        if (c1 == 'S' || c1 == '.')
        {
            l += (int)(l2 >> 32);
            i1 = (int)l2 >> 16;
        }
        k1 = 0;
        if (c1 != '.') goto _L1; else goto _L3
_L3:
        i2 = i1 + 1;
        k1 = 0;
        if (i2 >= i) goto _L1; else goto _L4
_L4:
        j2 = Character.digit(s1.charAt(i1), 10);
        k1 = 0;
        if (j2 < 0) goto _L1; else goto _L5
_L5:
        int k2;
        int i3;
        k2 = 0;
        i3 = i1;
_L8:
        if (i3 >= i)
        {
            break MISSING_BLOCK_LABEL_691;
        }
        i1 = i3 + 1;
        c1 = s1.charAt(i3);
        l3 = Character.digit(c1, 10);
        if (l3 >= 0) goto _L7; else goto _L6
_L6:
        int j3 = k2;
_L10:
        do
        {
            int k3 = j3 + 1;
            if (j3 >= 9)
            {
                continue; /* Loop/switch isn't completed */
            }
            k1 *= 10;
            j3 = k3;
        } while (true);
_L7:
        if (k2 < 9)
        {
            k1 = l3 + k1 * 10;
        } else
        if (k2 == 9 && l3 >= 5)
        {
            k1++;
        }
        k2++;
        i3 = i1;
          goto _L8
        if (c1 == 'S') goto _L1; else goto _L9
_L9:
        return null;
_L1:
        char c1;
        int i2;
        int j2;
        int l3;
        if (i1 != i)
        {
            return null;
        }
        Duration duration = new Duration();
        if (flag)
        {
            j1 = -j1;
            l = -l;
            k1 = -k1;
        }
        duration.months = j1;
        duration.seconds = l;
        duration.nanos = k1;
        duration.unit = unit1;
        return duration;
        j3 = k2;
        i1 = i3;
          goto _L10
    }

    public Numeric add(Object obj, int i)
    {
        if (obj instanceof Duration)
        {
            return add(this, (Duration)obj, i);
        }
        if ((obj instanceof DateTime) && i == 1)
        {
            return DateTime.add((DateTime)obj, this, 1);
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    public int compare(Object obj)
    {
        if (obj instanceof Duration)
        {
            return compare(this, (Duration)obj);
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    public Numeric div(Object obj)
    {
        if (obj instanceof RealNum)
        {
            double d = ((RealNum)obj).doubleValue();
            if (d == 0.0D || Double.isNaN(d))
            {
                throw new ArithmeticException("divide of duration by 0 or NaN");
            } else
            {
                return times(this, 1.0D / d);
            }
        }
        if (obj instanceof Duration)
        {
            return new DFloNum(div(this, (Duration)obj));
        } else
        {
            return ((Numeric)obj).divReversed(this);
        }
    }

    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof Duration))
        {
            return false;
        } else
        {
            return equals(this, (Duration)obj);
        }
    }

    public int getDays()
    {
        return (int)(seconds / 0x15180L);
    }

    public int getHours()
    {
        return (int)((seconds / 3600L) % 24L);
    }

    public int getMinutes()
    {
        return (int)((seconds / 60L) % 60L);
    }

    public int getMonths()
    {
        return months % 12;
    }

    public long getNanoSeconds()
    {
        return 0x3b9aca00L * seconds + (long)nanos;
    }

    public int getNanoSecondsOnly()
    {
        return nanos;
    }

    public int getSecondsOnly()
    {
        return (int)(seconds % 60L);
    }

    public long getTotalMinutes()
    {
        return seconds / 60L;
    }

    public int getTotalMonths()
    {
        return months;
    }

    public long getTotalSeconds()
    {
        return seconds;
    }

    public int getYears()
    {
        return months / 12;
    }

    public int hashCode()
    {
        return months ^ (int)seconds ^ nanos;
    }

    public boolean isExact()
    {
        return false;
    }

    public boolean isZero()
    {
        return months == 0 && seconds == 0L && nanos == 0;
    }

    public Numeric mul(Object obj)
    {
        if (obj instanceof RealNum)
        {
            return times(this, ((RealNum)obj).doubleValue());
        } else
        {
            return ((Numeric)obj).mulReversed(this);
        }
    }

    public Numeric mulReversed(Numeric numeric)
    {
        if (!(numeric instanceof RealNum))
        {
            throw new IllegalArgumentException();
        } else
        {
            return times(this, ((RealNum)numeric).doubleValue());
        }
    }

    public Complex number()
    {
        throw new Error("number needs to be implemented!");
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        months = objectinput.readInt();
        seconds = objectinput.readLong();
        nanos = objectinput.readInt();
        unit = (Unit)objectinput.readObject();
    }

    public String toString()
    {
        StringBuffer stringbuffer;
        stringbuffer = new StringBuffer();
        int i = months;
        long l = seconds;
        int j = nanos;
        boolean flag;
        int k;
        long l1;
        long l2;
        long l3;
        if (i < 0 || l < 0L || j < 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            i = -i;
            l = -l;
            j = -j;
            stringbuffer.append('-');
        }
        stringbuffer.append('P');
        k = i / 12;
        if (k != 0)
        {
            stringbuffer.append(k);
            stringbuffer.append('Y');
            i -= k * 12;
        }
        if (i != 0)
        {
            stringbuffer.append(i);
            stringbuffer.append('M');
        }
        l1 = l / 0x15180L;
        if (l1 != 0L)
        {
            stringbuffer.append(l1);
            stringbuffer.append('D');
            l -= 0x15180L * l1;
        }
        if (l == 0L && j == 0) goto _L2; else goto _L1
_L1:
        stringbuffer.append('T');
        l2 = l / 3600L;
        if (l2 != 0L)
        {
            stringbuffer.append(l2);
            stringbuffer.append('H');
            l -= 3600L * l2;
        }
        l3 = l / 60L;
        if (l3 != 0L)
        {
            stringbuffer.append(l3);
            stringbuffer.append('M');
            l -= 60L * l3;
        }
        if (l != 0L || j != 0)
        {
            stringbuffer.append(l);
            appendNanoSeconds(j, stringbuffer);
            stringbuffer.append('S');
        }
_L4:
        return stringbuffer.toString();
_L2:
        if (stringbuffer.length() == 1)
        {
            String s;
            if (unit == Unit.month)
            {
                s = "0M";
            } else
            {
                s = "T0S";
            }
            stringbuffer.append(s);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public Unit unit()
    {
        return unit;
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeInt(months);
        objectoutput.writeLong(seconds);
        objectoutput.writeInt(nanos);
        objectoutput.writeObject(unit);
    }
}
