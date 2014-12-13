// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.math;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

// Referenced classes of package gnu.math:
//            Quantity, Unit, Duration, Numeric, 
//            Complex

public class DateTime extends Quantity
    implements Cloneable
{

    public static final int DATE_MASK = 14;
    static final int DAY_COMPONENT = 3;
    public static final int DAY_MASK = 8;
    public static TimeZone GMT = TimeZone.getTimeZone("GMT");
    static final int HOURS_COMPONENT = 4;
    public static final int HOURS_MASK = 16;
    static final int MINUTES_COMPONENT = 5;
    public static final int MINUTES_MASK = 32;
    static final int MONTH_COMPONENT = 2;
    public static final int MONTH_MASK = 4;
    static final int SECONDS_COMPONENT = 6;
    public static final int SECONDS_MASK = 64;
    static final int TIMEZONE_COMPONENT = 7;
    public static final int TIMEZONE_MASK = 128;
    public static final int TIME_MASK = 112;
    static final int YEAR_COMPONENT = 1;
    public static final int YEAR_MASK = 2;
    private static final Date minDate = new Date(0x8000000000000000L);
    GregorianCalendar calendar;
    int mask;
    int nanoSeconds;
    Unit unit;

    public DateTime(int i)
    {
        unit = Unit.date;
        calendar = new GregorianCalendar();
        calendar.setGregorianChange(minDate);
        calendar.clear();
        mask = i;
    }

    public DateTime(int i, GregorianCalendar gregoriancalendar)
    {
        unit = Unit.date;
        calendar = gregoriancalendar;
        mask = i;
    }

    public static DateTime add(DateTime datetime, Duration duration, int i)
    {
        if (duration.unit == Unit.duration || duration.unit == Unit.month && (0xe & datetime.mask) != 14)
        {
            throw new IllegalArgumentException("invalid date/time +/- duration combinatuion");
        }
        DateTime datetime1 = new DateTime(datetime.mask, (GregorianCalendar)datetime.calendar.clone());
        if (duration.months != 0)
        {
            int j = 12 * datetime1.getYear() + datetime1.calendar.get(2) + i * duration.months;
            int k = datetime1.calendar.get(5);
            long l;
            long l1;
            long l2;
            int j1;
            int k1;
            int i2;
            if (j >= 12)
            {
                j1 = j / 12;
                k1 = j % 12;
                datetime1.calendar.set(0, 1);
                i2 = daysInMonth(k1, j1);
            } else
            {
                int i1 = 11 - j;
                datetime1.calendar.set(0, 0);
                j1 = 1 + i1 / 12;
                k1 = 11 - i1 % 12;
                i2 = daysInMonth(k1, 1);
            }
            if (k > i2)
            {
                k = i2;
            }
            datetime1.calendar.set(j1, k1, k);
        }
        l = (long)datetime.nanoSeconds + (long)i * (0x3b9aca00L * duration.seconds + (long)duration.nanos);
        if (l != 0L)
        {
            if ((0x70 & datetime.mask) == 0)
            {
                l2 = l % 0x4e94914f0000L;
                if (l2 < 0L)
                {
                    l2 += 0x4e94914f0000L;
                }
                l -= l2;
            }
            l1 = datetime1.calendar.getTimeInMillis() + 1000L * (l / 0x3b9aca00L);
            datetime1.calendar.setTimeInMillis(l1);
            datetime1.nanoSeconds = (int)(l % 0x3b9aca00L);
        }
        return datetime1;
    }

    public static DateTime addMinutes(DateTime datetime, int i)
    {
        return addSeconds(datetime, i * 60);
    }

    public static DateTime addSeconds(DateTime datetime, int i)
    {
        DateTime datetime1 = new DateTime(datetime.mask, (GregorianCalendar)datetime.calendar.clone());
        long l = 0x3b9aca00L * (long)i;
        if (l != 0L)
        {
            long l1 = l + (long)datetime.nanoSeconds;
            long l2 = datetime.calendar.getTimeInMillis() + l1 / 0xf4240L;
            datetime1.calendar.setTimeInMillis(l2);
            datetime1.nanoSeconds = (int)(l1 % 0xf4240L);
        }
        return datetime1;
    }

    private static void append(int i, StringBuffer stringbuffer, int j)
    {
        int k = stringbuffer.length();
        stringbuffer.append(i);
        for (int l = (k + j) - stringbuffer.length(); --l >= 0;)
        {
            stringbuffer.insert(k, '0');
        }

    }

    public static int compare(DateTime datetime, DateTime datetime1)
    {
        long l = datetime.calendar.getTimeInMillis();
        long l1 = datetime1.calendar.getTimeInMillis();
        if ((0xe & (datetime.mask | datetime1.mask)) == 0)
        {
            if (l < 0L)
            {
                l += 0x5265c00L;
            }
            if (l1 < 0L)
            {
                l1 += 0x5265c00L;
            }
        }
        int i = datetime.nanoSeconds;
        int j = datetime1.nanoSeconds;
        long l2 = l + (long)(i / 0xf4240);
        long l3 = l1 + (long)(j / 0xf4240);
        int k = i % 0xf4240;
        int i1 = j % 0xf4240;
        if (l2 < l3)
        {
            return -1;
        }
        if (l2 > l3)
        {
            return 1;
        }
        if (k < i1)
        {
            return -1;
        }
        return k <= i1 ? 0 : 1;
    }

    public static int daysInMonth(int i, int j)
    {
        switch (i)
        {
        case 2: // '\002'
        case 4: // '\004'
        case 6: // '\006'
        case 7: // '\007'
        case 9: // '\t'
        default:
            return 31;

        case 3: // '\003'
        case 5: // '\005'
        case 8: // '\b'
        case 10: // '\n'
            return 30;

        case 1: // '\001'
            break;
        }
        return !isLeapYear(j) ? 28 : 29;
    }

    public static boolean isLeapYear(int i)
    {
        return i % 4 == 0 && (i % 100 != 0 || i % 400 == 0);
    }

    public static TimeZone minutesToTimeZone(int i)
    {
        if (i == 0)
        {
            return GMT;
        } else
        {
            StringBuffer stringbuffer = new StringBuffer("GMT");
            toStringZone(i, stringbuffer);
            return TimeZone.getTimeZone(stringbuffer.toString());
        }
    }

    public static DateTime parse(String s, int i)
    {
        boolean flag = true;
        DateTime datetime = new DateTime(i);
        String s1 = s.trim();
        int j = s1.length();
        boolean flag1;
        int k;
        if ((i & 0xe) != 0)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        if ((i & 0x70) == 0)
        {
            flag = false;
        }
        k = 0;
        if (flag1)
        {
            k = datetime.parseDate(s1, 0, i);
            if (flag)
            {
                if (k < 0 || k >= j || s1.charAt(k) != 'T')
                {
                    k = -1;
                } else
                {
                    k++;
                }
            }
        }
        if (flag)
        {
            k = datetime.parseTime(s1, k);
        }
        if (datetime.parseZone(s1, k) != j)
        {
            throw new NumberFormatException((new StringBuilder()).append("Unrecognized date/time '").append(s1).append('\'').toString());
        } else
        {
            return datetime;
        }
    }

    private static int parseDigits(String s, int i)
    {
        int j = i;
        int k = -1;
        int l = s.length();
        do
        {
label0:
            {
                int i1;
                if (j < l)
                {
                    i1 = Character.digit(s.charAt(j), 10);
                    if (i1 >= 0)
                    {
                        break label0;
                    }
                }
                if (k < 0)
                {
                    return j;
                } else
                {
                    return j | k << 16;
                }
            }
            if (k > 20000)
            {
                return 0;
            }
            if (k < 0)
            {
                k = i1;
            } else
            {
                k = i1 + k * 10;
            }
            j++;
        } while (true);
    }

    public static Duration sub(DateTime datetime, DateTime datetime1)
    {
        long l = datetime.calendar.getTimeInMillis();
        long l1 = datetime1.calendar.getTimeInMillis();
        int i = datetime.nanoSeconds;
        int j = datetime1.nanoSeconds;
        long l2 = l + (long)(i / 0xf4240);
        long l3 = l1 + (long)(j / 0xf4240);
        int _tmp = i % 0xf4240;
        int k = j % 0xf4240;
        long l4 = l2 - l3;
        long l5 = l4 / 1000L;
        int i1 = (int)((0xf4240L * (l4 % 1000L) + (long)k) - (long)k);
        return Duration.make(0, l5 + (long)(i1 / 0x3b9aca00), i1 % 0x3b9aca00, Unit.second);
    }

    public static void toStringZone(int i, StringBuffer stringbuffer)
    {
        if (i == 0)
        {
            stringbuffer.append('Z');
            return;
        }
        if (i < 0)
        {
            stringbuffer.append('-');
            i = -i;
        } else
        {
            stringbuffer.append('+');
        }
        append(i / 60, stringbuffer, 2);
        stringbuffer.append(':');
        append(i % 60, stringbuffer, 2);
    }

    public Numeric add(Object obj, int i)
    {
        if (obj instanceof Duration)
        {
            return add(this, (Duration)obj, i);
        }
        if ((obj instanceof DateTime) && i == -1)
        {
            return sub(this, (DateTime)obj);
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    public Numeric addReversed(Numeric numeric, int i)
    {
        if ((numeric instanceof Duration) && i == 1)
        {
            return add(this, (Duration)numeric, i);
        } else
        {
            throw new IllegalArgumentException();
        }
    }

    public DateTime adjustTimezone(int i)
    {
        DateTime datetime = new DateTime(mask, (GregorianCalendar)calendar.clone());
        TimeZone timezone;
        if (i == 0)
        {
            timezone = GMT;
        } else
        {
            StringBuffer stringbuffer = new StringBuffer("GMT");
            toStringZone(i, stringbuffer);
            timezone = TimeZone.getTimeZone(stringbuffer.toString());
        }
        datetime.calendar.setTimeZone(timezone);
        if ((0x80 & datetime.mask) != 0)
        {
            long l = calendar.getTimeInMillis();
            datetime.calendar.setTimeInMillis(l);
            if ((0x70 & mask) == 0)
            {
                datetime.calendar.set(11, 0);
                datetime.calendar.set(12, 0);
                datetime.calendar.set(13, 0);
                datetime.nanoSeconds = 0;
            }
            return datetime;
        } else
        {
            datetime.mask = 0x80 | datetime.mask;
            return datetime;
        }
    }

    public DateTime cast(int i)
    {
        int j = 0xffffff7f & mask;
        if (i == j)
        {
            return this;
        }
        DateTime datetime = new DateTime(i, (GregorianCalendar)calendar.clone());
        if ((i & ~j) != 0 && (j != 14 || i != 126))
        {
            throw new ClassCastException("cannot cast DateTime - missing conponents");
        }
        int k;
        if (isZoneUnspecified())
        {
            datetime.mask = 0xffffff7f & datetime.mask;
        } else
        {
            datetime.mask = 0x80 | datetime.mask;
        }
        k = j & ~i;
        if ((k & 0x70) != 0)
        {
            datetime.calendar.clear(11);
            datetime.calendar.clear(12);
            datetime.calendar.clear(13);
        } else
        {
            datetime.nanoSeconds = nanoSeconds;
        }
        if ((k & 2) != 0)
        {
            datetime.calendar.clear(1);
            datetime.calendar.clear(0);
        }
        if ((k & 4) != 0)
        {
            datetime.calendar.clear(2);
        }
        if ((k & 8) != 0)
        {
            datetime.calendar.clear(5);
        }
        return datetime;
    }

    public int compare(Object obj)
    {
        if (obj instanceof DateTime)
        {
            return compare(this, (DateTime)obj);
        } else
        {
            return ((Numeric)obj).compareReversed(this);
        }
    }

    public int components()
    {
        return 0xffffff7f & mask;
    }

    public int getDay()
    {
        return calendar.get(5);
    }

    public int getHours()
    {
        return calendar.get(11);
    }

    public int getMinutes()
    {
        return calendar.get(12);
    }

    public int getMonth()
    {
        return 1 + calendar.get(2);
    }

    public int getNanoSecondsOnly()
    {
        return nanoSeconds;
    }

    public int getSecondsOnly()
    {
        return calendar.get(13);
    }

    public int getWholeSeconds()
    {
        return calendar.get(13);
    }

    public int getYear()
    {
        int i = calendar.get(1);
        if (calendar.get(0) == 0)
        {
            i = 1 - i;
        }
        return i;
    }

    public int getZoneMinutes()
    {
        return calendar.getTimeZone().getRawOffset() / 60000;
    }

    public boolean isExact()
    {
        return (0x70 & mask) == 0;
    }

    public boolean isZero()
    {
        throw new Error("DateTime.isZero not meaningful!");
    }

    public boolean isZoneUnspecified()
    {
        return (0x80 & mask) == 0;
    }

    public Complex number()
    {
        throw new Error("number needs to be implemented!");
    }

    int parseDate(String s, int i, int j)
    {
        if (i >= 0) goto _L2; else goto _L1
_L1:
        int l = i;
_L4:
        return l;
_L2:
        int k;
        int j1;
        k = s.length();
        boolean flag = false;
        if (i < k)
        {
            char c = s.charAt(i);
            flag = false;
            if (c == '-')
            {
                i++;
                flag = true;
            }
        }
        l = i;
        if ((j & 2) == 0)
        {
            if (!flag)
            {
                return -1;
            }
            j1 = -1;
        } else
        {
            int i1 = parseDigits(s, l);
            j1 = i1 >> 16;
            l = i1 & 0xffff;
            if (l != i + 4 && (l <= i + 4 || s.charAt(i) == '0'))
            {
                return -1;
            }
            if (flag || j1 == 0)
            {
                calendar.set(0, 0);
                calendar.set(1, j1 + 1);
            } else
            {
                calendar.set(1, j1);
            }
        }
        if ((j & 0xc) == 0) goto _L4; else goto _L3
_L3:
        int k1;
        if (l >= k || s.charAt(l) != '-')
        {
            return -1;
        }
        k1 = ++l;
        if ((j & 4) == 0) goto _L6; else goto _L5
_L5:
        int l1;
        int k3 = parseDigits(s, k1);
        l1 = k3 >> 16;
        l = k3 & 0xffff;
        if (l1 <= 0 || l1 > 12 || l != k1 + 2)
        {
            return -1;
        }
        calendar.set(2, l1 - 1);
        if ((j & 8) == 0) goto _L4; else goto _L7
_L7:
        if (l >= k || s.charAt(l) != '-')
        {
            return -1;
        }
        break; /* Loop/switch isn't completed */
_L6:
        l1 = -1;
        if (true) goto _L7; else goto _L8
_L8:
        int i2 = l + 1;
        int j2 = parseDigits(s, i2);
        int k2 = j2 >> 16;
        int l2 = j2 & 0xffff;
        if (k2 > 0 && l2 == i2 + 2)
        {
            int j3;
            if ((j & 4) == 0)
            {
                j3 = 31;
            } else
            {
                int i3 = l1 - 1;
                if ((j & 2) == 0)
                {
                    j1 = 2000;
                }
                j3 = daysInMonth(i3, j1);
            }
            if (k2 <= j3)
            {
                calendar.set(5, k2);
                return l2;
            }
        }
        return -1;
    }

    int parseTime(String s, int i)
    {
        int j;
        int l;
        int i1;
        if (i < 0)
        {
            return i;
        }
        j = s.length();
        int k = parseDigits(s, i);
        l = k >> 16;
        i1 = k & 0xffff;
        if (l > 24 || i1 != i + 2 || i1 == j || s.charAt(i1) != ':') goto _L2; else goto _L1
_L1:
        int j1;
        int l1;
        int i2;
        j1 = i1 + 1;
        int k1 = parseDigits(s, j1);
        l1 = k1 >> 16;
        i2 = k1 & 0xffff;
        if (l1 >= 60 || i2 != j1 + 2 || i2 == j || s.charAt(i2) != ':') goto _L2; else goto _L3
_L3:
        int j2;
        int l2;
        int i3;
        j2 = i2 + 1;
        int k2 = parseDigits(s, j2);
        l2 = k2 >> 16;
        i3 = k2 & 0xffff;
        if (l2 >= 60 || i3 != j2 + 2) goto _L2; else goto _L4
_L4:
        if (i3 + 1 >= j || s.charAt(i3) != '.' || Character.digit(s.charAt(i3 + 1), 10) < 0) goto _L6; else goto _L5
_L5:
        int j3;
        int k3;
        i3++;
        j3 = 0;
        k3 = 0;
_L12:
        int j4;
        if (i3 >= j)
        {
            break MISSING_BLOCK_LABEL_386;
        }
        j4 = Character.digit(s.charAt(i3), 10);
        if (j4 >= 0) goto _L8; else goto _L7
_L7:
        int l3 = k3;
_L11:
        int i4 = l3 + 1;
        if (l3 >= 9) goto _L10; else goto _L9
_L9:
        j3 *= 10;
        l3 = i4;
          goto _L11
_L8:
        if (k3 < 9)
        {
            j3 = j4 + j3 * 10;
        } else
        if (k3 == 9 && j4 >= 5)
        {
            j3++;
        }
        k3++;
        i3++;
          goto _L12
_L10:
        nanoSeconds = j3;
_L6:
        if (l == 24 && (l1 != 0 || l2 != 0 || nanoSeconds != 0))
        {
            return -1;
        } else
        {
            calendar.set(11, l);
            calendar.set(12, l1);
            calendar.set(13, l2);
            return i3;
        }
_L2:
        return -1;
        l3 = k3;
          goto _L11
    }

    int parseZone(String s, int i)
    {
        if (i >= 0)
        {
            int j = parseZoneMinutes(s, i);
            if (j == 0)
            {
                return -1;
            }
            if (j != i)
            {
                int k = j >> 16;
                int l = j & 0xffff;
                TimeZone timezone;
                if (k == 0)
                {
                    timezone = GMT;
                } else
                {
                    timezone = TimeZone.getTimeZone((new StringBuilder()).append("GMT").append(s.substring(i, l)).toString());
                }
                calendar.setTimeZone(timezone);
                mask = 0x80 | mask;
                return l;
            }
        }
        return i;
    }

    int parseZoneMinutes(String s, int i)
    {
        int j = s.length();
        if (i != j && i >= 0) goto _L2; else goto _L1
_L1:
        int k = i;
_L4:
        return k;
_L2:
        char c;
        int j1;
        int k1;
        int l1;
        int j2;
        int l2;
        c = s.charAt(i);
        if (c == 'Z')
        {
            return i + 1;
        }
        if (c != '+' && c != '-')
        {
            return i;
        }
        int l = i + 1;
        int i1 = parseDigits(s, l);
        j1 = i1 >> 16;
        k = 0;
        if (j1 > 14)
        {
            continue; /* Loop/switch isn't completed */
        }
        k1 = j1 * 60;
        l1 = i1 & 0xffff;
        int i2 = l + 2;
        k = 0;
        if (l1 != i2)
        {
            continue; /* Loop/switch isn't completed */
        }
        k = 0;
        if (l1 >= j)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (s.charAt(l1) != ':')
        {
            break; /* Loop/switch isn't completed */
        }
        j2 = l1 + 1;
        int k2 = parseDigits(s, j2);
        l1 = k2 & 0xffff;
        l2 = k2 >> 16;
        if (l2 <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        k = 0;
        if (l2 >= 60)
        {
            continue; /* Loop/switch isn't completed */
        }
        k = 0;
        if (j1 == 14) goto _L4; else goto _L3
_L3:
        int i3;
        k1 += l2;
        i3 = j2 + 2;
        k = 0;
        if (l1 != i3) goto _L4; else goto _L5
_L5:
        k = 0;
        if (k1 <= 840)
        {
            if (c == '-')
            {
                k1 = -k1;
            }
            return l1 | k1 << 16;
        }
        if (true) goto _L4; else goto _L6
_L6:
    }

    public void setTimeZone(TimeZone timezone)
    {
        calendar.setTimeZone(timezone);
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        toString(stringbuffer);
        return stringbuffer.toString();
    }

    public void toString(StringBuffer stringbuffer)
    {
        boolean flag = true;
        int i = components();
        boolean flag1;
        if ((i & 0xe) != 0)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        if ((i & 0x70) == 0)
        {
            flag = false;
        }
        if (flag1)
        {
            toStringDate(stringbuffer);
            if (flag)
            {
                stringbuffer.append('T');
            }
        }
        if (flag)
        {
            toStringTime(stringbuffer);
        }
        toStringZone(stringbuffer);
    }

    public void toStringDate(StringBuffer stringbuffer)
    {
        int i = components();
        if ((i & 2) != 0)
        {
            int j = calendar.get(1);
            if (calendar.get(0) == 0 && --j != 0)
            {
                stringbuffer.append('-');
            }
            append(j, stringbuffer, 4);
        } else
        {
            stringbuffer.append('-');
        }
        if ((i & 0xc) != 0)
        {
            stringbuffer.append('-');
            if ((i & 4) != 0)
            {
                append(getMonth(), stringbuffer, 2);
            }
            if ((i & 8) != 0)
            {
                stringbuffer.append('-');
                append(getDay(), stringbuffer, 2);
            }
        }
    }

    public void toStringTime(StringBuffer stringbuffer)
    {
        append(getHours(), stringbuffer, 2);
        stringbuffer.append(':');
        append(getMinutes(), stringbuffer, 2);
        stringbuffer.append(':');
        append(getWholeSeconds(), stringbuffer, 2);
        Duration.appendNanoSeconds(nanoSeconds, stringbuffer);
    }

    public void toStringZone(StringBuffer stringbuffer)
    {
        if (isZoneUnspecified())
        {
            return;
        } else
        {
            toStringZone(getZoneMinutes(), stringbuffer);
            return;
        }
    }

    public Unit unit()
    {
        return unit;
    }

    public DateTime withZoneUnspecified()
    {
        if (isZoneUnspecified())
        {
            return this;
        } else
        {
            DateTime datetime = new DateTime(mask, (GregorianCalendar)calendar.clone());
            datetime.calendar.setTimeZone(TimeZone.getDefault());
            datetime.mask = 0xffffff7f & datetime.mask;
            return datetime;
        }
    }

}
