// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package gnu.math:
//            RatNum, MPN, Numeric, RealNum

public class IntNum extends RatNum
    implements Externalizable
{

    static final int maxFixNum = 1024;
    static final int minFixNum = -100;
    static final int numFixNum = 1125;
    static final IntNum smallFixNums[];
    public int ival;
    public int words[];

    public IntNum()
    {
    }

    public IntNum(int i)
    {
        ival = i;
    }

    public static IntNum abs(IntNum intnum)
    {
        if (intnum.isNegative())
        {
            intnum = neg(intnum);
        }
        return intnum;
    }

    public static final IntNum add(int i, int j)
    {
        return make((long)i + (long)j);
    }

    public static IntNum add(IntNum intnum, int i)
    {
        if (intnum.words == null)
        {
            return add(intnum.ival, i);
        } else
        {
            IntNum intnum1 = new IntNum(0);
            intnum1.setAdd(intnum, i);
            return intnum1.canonicalize();
        }
    }

    public static IntNum add(IntNum intnum, IntNum intnum1)
    {
        return add(intnum, intnum1, 1);
    }

    public static IntNum add(IntNum intnum, IntNum intnum1, int i)
    {
        if (intnum.words == null && intnum1.words == null)
        {
            return make((long)i * (long)intnum1.ival + (long)intnum.ival);
        }
        if (i != 1)
        {
            if (i == -1)
            {
                intnum1 = neg(intnum1);
            } else
            {
                intnum1 = times(intnum1, make(i));
            }
        }
        if (intnum.words == null)
        {
            return add(intnum1, intnum.ival);
        }
        if (intnum1.words == null)
        {
            return add(intnum, intnum1.ival);
        }
        if (intnum1.ival > intnum.ival)
        {
            IntNum intnum3 = intnum;
            intnum = intnum1;
            intnum1 = intnum3;
        }
        IntNum intnum2 = alloc(1 + intnum.ival);
        int j = intnum1.ival;
        long l = MPN.add_n(intnum2.words, intnum.words, intnum1.words, j);
        long l1;
        if (intnum1.words[j - 1] < 0)
        {
            l1 = 0xffffffffL;
        } else
        {
            l1 = 0L;
        }
        for (; j < intnum.ival; j++)
        {
            long l2 = l + (l1 + (0xffffffffL & (long)intnum.words[j]));
            intnum2.words[j] = (int)l2;
            l = l2 >>> 32;
        }

        if (intnum.words[j - 1] < 0)
        {
            l1--;
        }
        intnum2.words[j] = (int)(l + l1);
        intnum2.ival = j + 1;
        return intnum2.canonicalize();
    }

    public static IntNum alloc(int i)
    {
        if (i <= 1)
        {
            return new IntNum();
        } else
        {
            IntNum intnum = new IntNum();
            intnum.words = new int[i];
            return intnum;
        }
    }

    public static IntNum asIntNumOrNull(Object obj)
    {
        if (obj instanceof IntNum)
        {
            return (IntNum)obj;
        }
        if (obj instanceof BigInteger)
        {
            return valueOf(obj.toString(), 10);
        }
        if ((obj instanceof Number) && ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Short) || (obj instanceof Byte)))
        {
            return make(((Number)obj).longValue());
        } else
        {
            return null;
        }
    }

    public static int compare(IntNum intnum, long l)
    {
        if (intnum.words != null) goto _L2; else goto _L1
_L1:
        long l1 = intnum.ival;
_L8:
        if (l1 >= l) goto _L4; else goto _L3
_L3:
        return -1;
_L2:
        boolean flag;
        flag = intnum.isNegative();
        boolean flag1;
        if (l < 0L)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag != flag1)
        {
            if (!flag)
            {
                return 1;
            }
        } else
        {
            int i;
            if (intnum.words == null)
            {
                i = 1;
            } else
            {
                i = intnum.ival;
            }
            if (i == 1)
            {
                l1 = intnum.words[0];
            } else
            {
                if (i != 2)
                {
                    continue; /* Loop/switch isn't completed */
                }
                l1 = intnum.longValue();
            }
            continue; /* Loop/switch isn't completed */
        }
        if (true) goto _L3; else goto _L5
_L5:
        if (flag) goto _L3; else goto _L6
_L6:
        return 1;
_L4:
        if (l1 > l)
        {
            return 1;
        }
        return 0;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public static int compare(IntNum intnum, IntNum intnum1)
    {
        int i = 1;
        if (intnum.words != null || intnum1.words != null) goto _L2; else goto _L1
_L1:
        if (intnum.ival >= intnum1.ival) goto _L4; else goto _L3
_L3:
        return -1;
_L4:
        if (intnum.ival > intnum1.ival)
        {
            return i;
        } else
        {
            return 0;
        }
_L2:
        int j = intnum.isNegative();
        if (j != intnum1.isNegative())
        {
            if (j == 0)
            {
                return i;
            }
        } else
        {
            int k;
            int l;
            if (intnum.words == null)
            {
                k = i;
            } else
            {
                k = intnum.ival;
            }
            if (intnum1.words == null)
            {
                l = i;
            } else
            {
                l = intnum1.ival;
            }
            if (k != l)
            {
                int i1 = 0;
                if (k > l)
                {
                    i1 = i;
                }
                if (i1 == j)
                {
                    i = -1;
                }
                return i;
            } else
            {
                return MPN.cmp(intnum.words, intnum1.words, k);
            }
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public static void divide(long l, long l1, IntNum intnum, IntNum intnum1, int i)
    {
        boolean flag;
        if (i == 5)
        {
            if (l1 < 0L)
            {
                i = 2;
            } else
            {
                i = 1;
            }
        }
        if (l >= 0L) goto _L2; else goto _L1
_L1:
        flag = true;
        if (l != 0x8000000000000000L) goto _L4; else goto _L3
_L3:
        divide(make(l), make(l1), intnum, intnum1, i);
_L12:
        return;
_L4:
        l = -l;
_L13:
        if (l1 >= 0L) goto _L6; else goto _L5
_L5:
        boolean flag1 = true;
        if (l1 != 0x8000000000000000L) goto _L8; else goto _L7
_L7:
        if (i != 3) goto _L10; else goto _L9
_L9:
        if (intnum != null)
        {
            intnum.set(0);
        }
        if (intnum1 == null) goto _L12; else goto _L11
_L11:
        intnum1.set(l);
        return;
_L2:
        flag = false;
          goto _L13
_L10:
        divide(make(l), make(l1), intnum, intnum1, i);
        return;
_L8:
        l1 = -l1;
_L19:
        long l2;
        long l3;
        boolean flag2;
        int j;
        boolean flag3;
        l2 = l / l1;
        l3 = l % l1;
        flag2 = flag ^ flag1;
        j = l3 != 0L;
        flag3 = false;
        if (j == 0) goto _L15; else goto _L14
_L14:
        flag3 = false;
        i;
        JVM INSTR tableswitch 1 4: default 200
    //                   1 281
    //                   2 281
    //                   3 200
    //                   4 312;
           goto _L16 _L17 _L17 _L16 _L18
_L16:
        break; /* Loop/switch isn't completed */
_L18:
        break MISSING_BLOCK_LABEL_312;
_L15:
        if (intnum != null)
        {
            if (flag3)
            {
                l2++;
            }
            if (flag2)
            {
                l2 = -l2;
            }
            intnum.set(l2);
        }
        if (intnum1 != null)
        {
            if (flag3)
            {
                l3 = l1 - l3;
                boolean flag4;
                if (!flag)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
            }
            if (flag)
            {
                l3 = -l3;
            }
            intnum1.set(l3);
            return;
        }
          goto _L12
_L6:
        flag1 = false;
          goto _L19
_L17:
        if (i == 1)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        flag3 = false;
        if (flag2 == flag4)
        {
            flag3 = true;
        }
          goto _L15
        if (l3 > l1 - (1L & l2) >> 1)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
          goto _L15
    }

    public static void divide(IntNum intnum, IntNum intnum1, IntNum intnum2, IntNum intnum3, int i)
    {
        if (intnum.words != null && intnum.ival > 2 || intnum1.words != null && intnum1.ival > 2) goto _L2; else goto _L1
_L1:
        long l;
        long l1;
        l = intnum.longValue();
        l1 = intnum1.longValue();
        if (l == 0x8000000000000000L || l1 == 0x8000000000000000L) goto _L2; else goto _L3
_L3:
        divide(l, l1, intnum2, intnum3, i);
_L14:
        return;
_L2:
        boolean flag;
        boolean flag1;
        boolean flag2;
        int j;
        int ai[];
        int ai1[];
        int i1;
        int j1;
        flag = intnum.isNegative();
        flag1 = intnum1.isNegative();
        flag2 = flag ^ flag1;
        if (intnum1.words == null)
        {
            j = 1;
        } else
        {
            j = intnum1.ival;
        }
        ai = new int[j];
        intnum1.getAbsolute(ai);
        for (; j > 1 && ai[j - 1] == 0; j--) { }
        int k;
        if (intnum.words == null)
        {
            k = 1;
        } else
        {
            k = intnum.ival;
        }
        ai1 = new int[k + 2];
        intnum.getAbsolute(ai1);
        for (i1 = k; i1 > 1 && ai1[i1 - 1] == 0; i1--) { }
        j1 = MPN.cmp(ai1, i1, ai, j);
        if (j1 >= 0) goto _L5; else goto _L4
_L4:
        int j2;
        int k2;
        int ai2[] = ai1;
        ai1 = ai;
        ai = ai2;
        j2 = i1;
        k2 = 1;
        ai1[0] = 0;
        i1;
_L7:
        for (; j2 > 1 && ai[j2 - 1] == 0; j2--) { }
        break; /* Loop/switch isn't completed */
_L5:
        if (j1 == 0)
        {
            ai1[0] = 1;
            k2 = 1;
            ai[0] = 0;
            j2 = 1;
            i1;
            continue; /* Loop/switch isn't completed */
        }
        if (j != 1)
        {
            break; /* Loop/switch isn't completed */
        }
        k2 = i1;
        j2 = 1;
        int k4 = ai[0];
        ai[0] = MPN.divmod_1(ai1, ai1, i1, k4);
        i1;
        if (true) goto _L7; else goto _L6
_L6:
        int k1 = MPN.count_leading_zeros(ai[j - 1]);
        if (k1 != 0)
        {
            MPN.lshift(ai, 0, ai, j, k1);
            int i4 = MPN.lshift(ai1, 0, ai1, i1, k1);
            int j4 = i1 + 1;
            ai1[i1] = i4;
            i1 = j4;
        }
        int i2;
        boolean flag3;
        IntNum intnum4;
        IntNum intnum5;
        int i3;
        int j3;
        IntNum intnum6;
        int k3;
        boolean flag4;
        int l3;
        if (i1 == j)
        {
            i2 = i1 + 1;
            ai1[i1] = 0;
        } else
        {
            i2 = i1;
        }
        MPN.divide(ai1, i2, ai, j);
        j2 = j;
        MPN.rshift0(ai, ai1, 0, j2, k1);
        k2 = (i2 + 1) - j;
        if (intnum2 != null)
        {
            int l2 = 0;
            while (l2 < k2) 
            {
                ai1[l2] = ai1[l2 + j];
                l2++;
            }
        }
        if (true) goto _L7; else goto _L8
_L8:
        if (ai[j2 - 1] < 0)
        {
            ai[j2] = 0;
            j2++;
        }
        if (j2 > 1) goto _L10; else goto _L9
_L9:
        l3 = ai[0];
        flag3 = false;
        if (l3 == 0) goto _L11; else goto _L10
_L10:
        if (i == 5)
        {
            if (flag1)
            {
                i = 2;
            } else
            {
                i = 1;
            }
        }
        flag3 = false;
        i;
        JVM INSTR tableswitch 1 4: default 576
    //                   1 689
    //                   2 689
    //                   3 576
    //                   4 720;
           goto _L11 _L12 _L12 _L11 _L13
_L11:
        if (intnum2 != null)
        {
            if (ai1[k2 - 1] < 0)
            {
                ai1[k2] = 0;
                k2++;
            }
            intnum2.set(ai1, k2);
            if (flag2)
            {
                if (flag3)
                {
                    intnum2.setInvert();
                } else
                {
                    intnum2.setNegative();
                }
            } else
            if (flag3)
            {
                intnum2.setAdd(1);
            }
        }
        if (intnum3 != null)
        {
            intnum3.set(ai, j2);
            if (flag3)
            {
                if (intnum1.words == null)
                {
                    intnum6 = intnum3;
                    if (flag1)
                    {
                        k3 = ai[0] + intnum1.ival;
                    } else
                    {
                        k3 = ai[0] - intnum1.ival;
                    }
                    intnum6.set(k3);
                } else
                {
                    if (flag1)
                    {
                        j3 = 1;
                    } else
                    {
                        j3 = -1;
                    }
                    intnum6 = add(intnum3, intnum1, j3);
                }
                if (flag)
                {
                    intnum3.setNegative(intnum6);
                    return;
                } else
                {
                    intnum3.set(intnum6);
                    return;
                }
            }
            continue; /* Loop/switch isn't completed */
        }
        if (true) goto _L14; else goto _L12
_L12:
        if (i == 1)
        {
            flag4 = true;
        } else
        {
            flag4 = false;
        }
        flag3 = false;
        if (flag2 == flag4)
        {
            flag3 = true;
        }
          goto _L11
_L13:
        if (intnum3 == null)
        {
            intnum4 = new IntNum();
        } else
        {
            intnum4 = intnum3;
        }
        intnum4.set(ai, j2);
        intnum5 = shift(intnum4, 1);
        if (flag1)
        {
            intnum5.setNegative();
        }
        i3 = compare(intnum5, intnum1);
        if (flag1)
        {
            i3 = -i3;
        }
        if (i3 == 1 || i3 == 0 && (1 & ai1[0]) != 0)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
          goto _L11
        if (!flag) goto _L14; else goto _L15
_L15:
        intnum3.setNegative();
        return;
    }

    public static boolean equals(IntNum intnum, IntNum intnum1)
    {
        if (intnum.words != null || intnum1.words != null) goto _L2; else goto _L1
_L1:
        if (intnum.ival != intnum1.ival) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (intnum.words == null || intnum1.words == null || intnum.ival != intnum1.ival)
        {
            return false;
        }
        int i = intnum.ival;
        while (--i >= 0) 
        {
            if (intnum.words[i] != intnum1.words[i])
            {
                return false;
            }
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public static final int gcd(int i, int j)
    {
        if (j > i)
        {
            int l = i;
            i = j;
            j = l;
        }
        do
        {
            if (j == 0)
            {
                return i;
            }
            if (j == 1)
            {
                return j;
            }
            int k = j;
            j = i % j;
            i = k;
        } while (true);
    }

    public static IntNum gcd(IntNum intnum, IntNum intnum1)
    {
        int i = intnum.ival;
        int j = intnum1.ival;
        if (intnum.words == null)
        {
            if (i == 0)
            {
                return abs(intnum1);
            }
            if (intnum1.words == null && i != 0x80000000 && j != 0x80000000)
            {
                if (i < 0)
                {
                    i = -i;
                }
                if (j < 0)
                {
                    j = -j;
                }
                return make(gcd(i, j));
            }
            i = 1;
        }
        if (intnum1.words == null)
        {
            if (j == 0)
            {
                return abs(intnum);
            }
            j = 1;
        }
        int k;
        int ai[];
        int ai1[];
        int l;
        IntNum intnum2;
        if (i > j)
        {
            k = i;
        } else
        {
            k = j;
        }
        ai = new int[k];
        ai1 = new int[k];
        intnum.getAbsolute(ai);
        intnum1.getAbsolute(ai1);
        l = MPN.gcd(ai, ai1, k);
        intnum2 = new IntNum(0);
        if (ai[l - 1] < 0)
        {
            int i1 = l + 1;
            ai[l] = 0;
            l = i1;
        }
        intnum2.ival = l;
        intnum2.words = ai;
        return intnum2.canonicalize();
    }

    public static int intValue(Object obj)
    {
        IntNum intnum = (IntNum)obj;
        if (intnum.words != null)
        {
            throw new ClassCastException("integer too large");
        } else
        {
            return intnum.ival;
        }
    }

    public static IntNum lcm(IntNum intnum, IntNum intnum1)
    {
        if (intnum.isZero() || intnum1.isZero())
        {
            return zero();
        } else
        {
            IntNum intnum2 = abs(intnum);
            IntNum intnum3 = abs(intnum1);
            IntNum intnum4 = new IntNum();
            divide(times(intnum2, intnum3), gcd(intnum2, intnum3), intnum4, null, 3);
            return intnum4.canonicalize();
        }
    }

    public static IntNum make(int i)
    {
        if (i >= -100 && i <= 1024)
        {
            return smallFixNums[i + 100];
        } else
        {
            return new IntNum(i);
        }
    }

    public static IntNum make(long l)
    {
        if (l >= -100L && l <= 1024L)
        {
            return smallFixNums[100 + (int)l];
        }
        int i = (int)l;
        if ((long)i == l)
        {
            return new IntNum(i);
        } else
        {
            IntNum intnum = alloc(2);
            intnum.ival = 2;
            intnum.words[0] = i;
            intnum.words[1] = (int)(l >> 32);
            return intnum;
        }
    }

    public static IntNum make(int ai[])
    {
        return make(ai, ai.length);
    }

    public static IntNum make(int ai[], int i)
    {
        if (ai == null)
        {
            return make(i);
        }
        int j = wordsNeeded(ai, i);
        if (j <= 1)
        {
            if (j == 0)
            {
                return zero();
            } else
            {
                return make(ai[0]);
            }
        } else
        {
            IntNum intnum = new IntNum();
            intnum.words = ai;
            intnum.ival = j;
            return intnum;
        }
    }

    public static IntNum makeU(long l)
    {
        if (l >= 0L)
        {
            return make(l);
        } else
        {
            IntNum intnum = alloc(3);
            intnum.ival = 3;
            intnum.words[0] = (int)l;
            intnum.words[1] = (int)(l >> 32);
            intnum.words[2] = 0;
            return intnum;
        }
    }

    public static IntNum minusOne()
    {
        return smallFixNums[99];
    }

    public static IntNum modulo(IntNum intnum, IntNum intnum1)
    {
        return remainder(intnum, intnum1, 1);
    }

    public static IntNum neg(IntNum intnum)
    {
        if (intnum.words == null && intnum.ival != 0x80000000)
        {
            return make(-intnum.ival);
        } else
        {
            IntNum intnum1 = new IntNum(0);
            intnum1.setNegative(intnum);
            return intnum1.canonicalize();
        }
    }

    public static boolean negate(int ai[], int ai1[], int i)
    {
        long l = 1L;
        boolean flag;
        int j;
        if (ai1[i - 1] < 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        for (j = 0; j < i; j++)
        {
            long l1 = l + (0xffffffffL & (long)(-1 ^ ai1[j]));
            ai[j] = (int)l1;
            l = l1 >> 32;
        }

        return flag && ai[i - 1] < 0;
    }

    public static final IntNum one()
    {
        return smallFixNums[101];
    }

    public static IntNum power(IntNum intnum, int i)
    {
        if (i > 0) goto _L2; else goto _L1
_L1:
        if (i != 0) goto _L4; else goto _L3
_L3:
        intnum = one();
_L6:
        return intnum;
_L4:
        throw new Error("negative exponent");
_L2:
        if (!intnum.isZero())
        {
            int j;
            int k;
            boolean flag;
            int ai[];
            int ai1[];
            int ai2[];
            int l;
            if (intnum.words == null)
            {
                j = 1;
            } else
            {
                j = intnum.ival;
            }
            k = (i * intnum.intLength() >> 5) + j * 2;
            if (intnum.isNegative() && (i & 1) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            ai = new int[k];
            ai1 = new int[k];
            ai2 = new int[k];
            intnum.getAbsolute(ai);
            l = 1;
            ai1[0] = 1;
            do
            {
                if ((i & 1) != 0)
                {
                    MPN.mul(ai2, ai, j, ai1, l);
                    int ai4[] = ai2;
                    ai2 = ai1;
                    ai1 = ai4;
                    for (l += j; ai1[l - 1] == 0; l--) { }
                }
                i >>= 1;
                if (i == 0)
                {
                    if (ai1[l - 1] < 0)
                    {
                        l++;
                    }
                    if (flag)
                    {
                        negate(ai1, ai1, l);
                    }
                    return make(ai1, l);
                }
                MPN.mul(ai2, ai, j, ai, j);
                int ai3[] = ai2;
                ai2 = ai;
                ai = ai3;
                j *= 2;
                while (ai[j - 1] == 0) 
                {
                    j--;
                }
            } while (true);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static IntNum quotient(IntNum intnum, IntNum intnum1)
    {
        return quotient(intnum, intnum1, 3);
    }

    public static IntNum quotient(IntNum intnum, IntNum intnum1, int i)
    {
        IntNum intnum2 = new IntNum();
        divide(intnum, intnum1, intnum2, null, i);
        return intnum2.canonicalize();
    }

    public static IntNum remainder(IntNum intnum, IntNum intnum1)
    {
        return remainder(intnum, intnum1, 3);
    }

    public static IntNum remainder(IntNum intnum, IntNum intnum1, int i)
    {
        if (intnum1.isZero())
        {
            return intnum;
        } else
        {
            IntNum intnum2 = new IntNum();
            divide(intnum, intnum1, null, intnum2, i);
            return intnum2.canonicalize();
        }
    }

    public static int shift(int i, int j)
    {
        if (j < 32)
        {
            if (j >= 0)
            {
                return i << j;
            }
            int k = -j;
            if (k >= 32)
            {
                if (i < 0)
                {
                    return -1;
                }
            } else
            {
                return i >> k;
            }
        }
        return 0;
    }

    public static long shift(long l, int i)
    {
        if (i < 32)
        {
            if (i >= 0)
            {
                return l << i;
            }
            int j = -i;
            if (j >= 32)
            {
                if (l < 0L)
                {
                    return -1L;
                }
            } else
            {
                return l >> j;
            }
        }
        return 0L;
    }

    public static IntNum shift(IntNum intnum, int i)
    {
        if (intnum.words != null) goto _L2; else goto _L1
_L1:
        if (i > 0) goto _L4; else goto _L3
_L3:
        if (i <= -32) goto _L6; else goto _L5
_L5:
        int k = intnum.ival >> -i;
_L10:
        intnum = make(k);
_L8:
        return intnum;
_L6:
        int j = intnum.ival;
        k = 0;
        if (j < 0)
        {
            k = -1;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (i < 32)
        {
            return make((long)intnum.ival << i);
        }
_L2:
        if (i == 0) goto _L8; else goto _L7
_L7:
        IntNum intnum1 = new IntNum(0);
        intnum1.setShift(intnum, i);
        return intnum1.canonicalize();
        if (true) goto _L10; else goto _L9
_L9:
    }

    public static IntNum sub(IntNum intnum, IntNum intnum1)
    {
        return add(intnum, intnum1, -1);
    }

    public static final IntNum ten()
    {
        return smallFixNums[110];
    }

    public static final IntNum times(int i, int j)
    {
        return make((long)i * (long)j);
    }

    public static final IntNum times(IntNum intnum, int i)
    {
        if (i == 0)
        {
            intnum = zero();
        } else
        if (i != 1)
        {
            int ai[] = intnum.words;
            int j = intnum.ival;
            if (ai == null)
            {
                return make((long)j * (long)i);
            }
            IntNum intnum1 = alloc(j + 1);
            boolean flag;
            if (ai[j - 1] < 0)
            {
                flag = true;
                negate(intnum1.words, ai, j);
                ai = intnum1.words;
            } else
            {
                flag = false;
            }
            if (i < 0)
            {
                if (!flag)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                i = -i;
            }
            intnum1.words[j] = MPN.mul_1(intnum1.words, ai, j, i);
            intnum1.ival = j + 1;
            if (flag)
            {
                intnum1.setNegative();
            }
            return intnum1.canonicalize();
        }
        return intnum;
    }

    public static final IntNum times(IntNum intnum, IntNum intnum1)
    {
        if (intnum1.words == null)
        {
            return times(intnum, intnum1.ival);
        }
        if (intnum.words == null)
        {
            return times(intnum1, intnum.ival);
        }
        int i = intnum.ival;
        int j = intnum1.ival;
        int ai[];
        boolean flag;
        int ai1[];
        if (intnum.isNegative())
        {
            flag = true;
            ai = new int[i];
            negate(ai, intnum.words, i);
        } else
        {
            ai = intnum.words;
            flag = false;
        }
        if (intnum1.isNegative())
        {
            IntNum intnum2;
            int ai2[];
            int k;
            if (!flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            ai1 = new int[j];
            negate(ai1, intnum1.words, j);
        } else
        {
            ai1 = intnum1.words;
        }
        if (i < j)
        {
            ai2 = ai;
            ai = ai1;
            ai1 = ai2;
            k = i;
            i = j;
            j = k;
        }
        intnum2 = alloc(i + j);
        MPN.mul(intnum2.words, ai, i, ai1, j);
        intnum2.ival = i + j;
        if (flag)
        {
            intnum2.setNegative();
        }
        return intnum2.canonicalize();
    }

    public static IntNum valueOf(String s)
        throws NumberFormatException
    {
        return valueOf(s, 10);
    }

    public static IntNum valueOf(String s, int i)
        throws NumberFormatException
    {
        int j;
        byte abyte0[];
        boolean flag;
        int k;
        int l;
        j = s.length();
        if (j + i <= 28)
        {
            if (j > 1 && s.charAt(0) == '+' && Character.digit(s.charAt(1), i) >= 0)
            {
                s = s.substring(1);
            }
            return make(Long.parseLong(s, i));
        }
        abyte0 = new byte[j];
        flag = false;
        k = 0;
        l = 0;
_L2:
        char c;
        int i1;
        if (k >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        c = s.charAt(k);
        if (c == '-' && k == 0)
        {
            flag = true;
            i1 = l;
        } else
        if (c == '+' && k == 0)
        {
            i1 = l;
        } else
        {
label0:
            {
                if (c == '_')
                {
                    break MISSING_BLOCK_LABEL_235;
                }
                if (l != 0)
                {
                    break label0;
                }
                if (c == ' ')
                {
                    break MISSING_BLOCK_LABEL_235;
                }
                if (c != '\t')
                {
                    break label0;
                }
                i1 = l;
            }
        }
_L3:
        k++;
        l = i1;
        if (true) goto _L2; else goto _L1
        int j1 = Character.digit(c, i);
        if (j1 < 0)
        {
            throw new NumberFormatException((new StringBuilder()).append("For input string: \"").append(s).append('"').toString());
        }
        i1 = l + 1;
        abyte0[l] = (byte)j1;
          goto _L3
_L1:
        return valueOf(abyte0, l, flag, i);
        i1 = l;
          goto _L3
    }

    public static IntNum valueOf(byte abyte0[], int i, boolean flag, int j)
    {
        int ai[] = new int[1 + i / MPN.chars_per_word(j)];
        int k = MPN.set_str(ai, abyte0, i, j);
        if (k == 0)
        {
            return zero();
        }
        if (ai[k - 1] < 0)
        {
            int l = k + 1;
            ai[k] = 0;
            k = l;
        }
        if (flag)
        {
            negate(ai, ai, k);
        }
        return make(ai, k);
    }

    public static IntNum valueOf(char ac[], int i, int j, int k, boolean flag)
    {
        byte abyte0[];
        int l;
        int i1;
        abyte0 = new byte[j];
        l = 0;
        i1 = 0;
_L2:
        char c;
        int j1;
        if (l >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        c = ac[i + l];
        if (c == '-')
        {
            flag = true;
            j1 = i1;
        } else
        {
label0:
            {
                if (c == '_')
                {
                    break MISSING_BLOCK_LABEL_123;
                }
                if (i1 != 0)
                {
                    break label0;
                }
                if (c == ' ')
                {
                    break MISSING_BLOCK_LABEL_123;
                }
                if (c != '\t')
                {
                    break label0;
                }
                j1 = i1;
            }
        }
_L3:
        l++;
        i1 = j1;
        if (true) goto _L2; else goto _L1
        int k1;
        for (k1 = Character.digit(c, k); k1 >= 0; k1 = Character.digit(c, k))
        {
            break MISSING_BLOCK_LABEL_106;
        }

_L1:
        return valueOf(abyte0, i1, flag, k);
        j1 = i1 + 1;
        abyte0[i1] = (byte)k1;
          goto _L3
        j1 = i1;
          goto _L3
    }

    public static int wordsNeeded(int ai[], int i)
    {
        int j = i;
        if (j <= 0) goto _L2; else goto _L1
_L1:
        int k;
        j--;
        k = ai[j];
        if (k != -1) goto _L4; else goto _L3
_L3:
        int l;
        do
        {
            if (j <= 0)
            {
                break;
            }
            l = ai[j - 1];
            if (l >= 0)
            {
                break;
            }
            j--;
        } while (l == -1);
_L2:
        return j + 1;
_L4:
        if (k != 0 || j <= 0) goto _L2; else goto _L5
_L5:
        k = ai[j - 1];
        if (k < 0) goto _L2; else goto _L6
_L6:
        j--;
          goto _L4
    }

    public static final IntNum zero()
    {
        return smallFixNums[100];
    }

    public Numeric add(Object obj, int i)
    {
        if (obj instanceof IntNum)
        {
            return add(this, (IntNum)obj, i);
        }
        if (!(obj instanceof Numeric))
        {
            throw new IllegalArgumentException();
        } else
        {
            return ((Numeric)obj).addReversed(this, i);
        }
    }

    public BigDecimal asBigDecimal()
    {
        if (words == null)
        {
            return new BigDecimal(ival);
        }
        if (ival <= 2)
        {
            return BigDecimal.valueOf(longValue());
        } else
        {
            return new BigDecimal(toString());
        }
    }

    public BigInteger asBigInteger()
    {
        if (words == null || ival <= 2)
        {
            return BigInteger.valueOf(longValue());
        } else
        {
            return new BigInteger(toString());
        }
    }

    public IntNum canonicalize()
    {
        if (words != null)
        {
            int i = wordsNeeded(words, ival);
            ival = i;
            if (i <= 1)
            {
                if (ival == 1)
                {
                    ival = words[0];
                }
                words = null;
            }
        }
        if (words == null && ival >= -100 && ival <= 1024)
        {
            this = smallFixNums[100 + ival];
        }
        return this;
    }

    boolean checkBits(int i)
    {
        boolean flag = true;
        if (i > 0)
        {
            if (words == null)
            {
                if (i > 31 || (ival & -1 + (flag << i)) != 0)
                {
                    return flag;
                }
            } else
            {
                int j;
                for (j = 0; j < i >> 5; j++)
                {
                    if (words[j] != 0)
                    {
                        return flag;
                    }
                }

                if ((i & 0x1f) == 0 || (words[j] & -1 + (flag << (i & 0x1f))) == 0)
                {
                    flag = false;
                }
                return flag;
            }
        }
        return false;
    }

    public int compare(Object obj)
    {
        if (obj instanceof IntNum)
        {
            return compare(this, (IntNum)obj);
        } else
        {
            return ((RealNum)obj).compareReversed(this);
        }
    }

    public final IntNum denominator()
    {
        return one();
    }

    public Numeric div(Object obj)
    {
        if (obj instanceof RatNum)
        {
            RatNum ratnum = (RatNum)obj;
            return RatNum.make(times(this, ratnum.denominator()), ratnum.numerator());
        }
        if (!(obj instanceof Numeric))
        {
            throw new IllegalArgumentException();
        } else
        {
            return ((Numeric)obj).divReversed(this);
        }
    }

    public double doubleValue()
    {
        if (words == null)
        {
            return (double)ival;
        }
        if (ival <= 2)
        {
            return (double)longValue();
        }
        if (isNegative())
        {
            return neg(this).roundToDouble(0, true, false);
        } else
        {
            return roundToDouble(0, false, false);
        }
    }

    public boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof IntNum))
        {
            return false;
        } else
        {
            return equals(this, (IntNum)obj);
        }
    }

    public void format(int i, StringBuffer stringbuffer)
    {
        if (i == 10)
        {
            if (words == null)
            {
                stringbuffer.append(ival);
                return;
            }
            if (ival <= 2)
            {
                stringbuffer.append(longValue());
                return;
            }
        }
        stringbuffer.append(toString(i));
    }

    public void format(int i, StringBuilder stringbuilder)
    {
        if (words != null) goto _L2; else goto _L1
_L1:
        if (i != 10) goto _L4; else goto _L3
_L3:
        stringbuilder.append(ival);
_L5:
        return;
_L4:
        stringbuilder.append(Integer.toString(ival, i));
        return;
_L2:
        boolean flag;
        int ai[];
        int j;
label0:
        {
            if (ival <= 2)
            {
                long l3 = longValue();
                if (i == 10)
                {
                    stringbuilder.append(l3);
                    return;
                } else
                {
                    stringbuilder.append(Long.toString(l3, i));
                    return;
                }
            }
            flag = isNegative();
            int k2;
            int l2;
            if (flag || i != 16)
            {
                ai = new int[ival];
                getAbsolute(ai);
            } else
            {
                ai = words;
            }
            j = ival;
            if (i != 16)
            {
                break label0;
            }
            if (flag)
            {
                stringbuilder.append('-');
            }
            k2 = stringbuilder.length();
            l2 = j;
            while (--l2 >= 0) 
            {
                int i3 = ai[l2];
                int j3 = 8;
                while (--j3 >= 0) 
                {
                    int k3 = 0xf & i3 >> j3 * 4;
                    if (k3 > 0 || stringbuilder.length() > k2)
                    {
                        stringbuilder.append(Character.forDigit(k3, 16));
                    }
                }
            }
        }
          goto _L5
        int k;
        int l;
        int j1;
        k = MPN.chars_per_word(i);
        l = i;
        for (int i1 = k; --i1 > 0;)
        {
            l *= i;
        }

        j1 = stringbuilder.length();
_L7:
        int k1;
        int l1;
        k1 = MPN.divmod_1(ai, ai, j, l);
        for (; j > 0 && ai[j - 1] == 0; j--) { }
        l1 = k;
_L8:
        if (--l1 >= 0 && (j != 0 || k1 != 0))
        {
            break MISSING_BLOCK_LABEL_376;
        }
        if (j != 0) goto _L7; else goto _L6
_L6:
        if (flag)
        {
            stringbuilder.append('-');
        }
        int i2 = -1 + stringbuilder.length();
        while (j1 < i2) 
        {
            char c = stringbuilder.charAt(j1);
            stringbuilder.setCharAt(j1, stringbuilder.charAt(i2));
            stringbuilder.setCharAt(i2, c);
            j1++;
            i2--;
        }
          goto _L5
        int j2;
        if (k1 < 0)
        {
            j2 = (int)((-1L & (long)k1) % (long)i);
            k1 /= i;
        } else
        {
            j2 = k1 % i;
            k1 /= i;
        }
        stringbuilder.append(Character.forDigit(j2, i));
          goto _L8
    }

    public void getAbsolute(int ai[])
    {
        int i;
        if (words == null)
        {
            i = 1;
            ai[0] = ival;
        } else
        {
            i = ival;
            int j = i;
            while (--j >= 0) 
            {
                ai[j] = words[j];
            }
        }
        if (ai[i - 1] < 0)
        {
            negate(ai, ai, i);
        }
        for (int k = ai.length; --k > i;)
        {
            ai[k] = 0;
        }

    }

    public int hashCode()
    {
        if (words == null)
        {
            return ival;
        } else
        {
            return words[0] + words[-1 + ival];
        }
    }

    public boolean inIntRange()
    {
        return inRange(0xffffffff80000000L, 0x7fffffffL);
    }

    public boolean inLongRange()
    {
        return inRange(0x8000000000000000L, 0x7fffffffffffffffL);
    }

    public boolean inRange(long l, long l1)
    {
        return compare(this, l) >= 0 && compare(this, l1) <= 0;
    }

    public int intLength()
    {
        if (words == null)
        {
            return MPN.intLength(ival);
        } else
        {
            return MPN.intLength(words, ival);
        }
    }

    public int intValue()
    {
        if (words == null)
        {
            return ival;
        } else
        {
            return words[0];
        }
    }

    public final boolean isMinusOne()
    {
        return words == null && ival == -1;
    }

    public final boolean isNegative()
    {
        int i;
        if (words == null)
        {
            i = ival;
        } else
        {
            i = words[-1 + ival];
        }
        return i < 0;
    }

    public final boolean isOdd()
    {
        int i;
        int j;
        boolean flag;
        if (words == null)
        {
            i = ival;
        } else
        {
            i = words[0];
        }
        j = i & 1;
        flag = false;
        if (j != 0)
        {
            flag = true;
        }
        return flag;
    }

    public final boolean isOne()
    {
        return words == null && ival == 1;
    }

    public final boolean isZero()
    {
        return words == null && ival == 0;
    }

    public long longValue()
    {
        if (words == null)
        {
            return (long)ival;
        }
        if (ival == 1)
        {
            return (long)words[0];
        } else
        {
            return ((long)words[1] << 32) + (0xffffffffL & (long)words[0]);
        }
    }

    public Numeric mul(Object obj)
    {
        if (obj instanceof IntNum)
        {
            return times(this, (IntNum)obj);
        }
        if (!(obj instanceof Numeric))
        {
            throw new IllegalArgumentException();
        } else
        {
            return ((Numeric)obj).mulReversed(this);
        }
    }

    public Numeric neg()
    {
        return neg(this);
    }

    public final IntNum numerator()
    {
        return this;
    }

    public Numeric power(IntNum intnum)
    {
        if (!isOne()) goto _L2; else goto _L1
_L1:
        return this;
_L2:
        if (!isMinusOne())
        {
            break; /* Loop/switch isn't completed */
        }
        if (!intnum.isOdd())
        {
            return one();
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (intnum.words == null && intnum.ival >= 0)
        {
            return power(this, intnum.ival);
        }
        if (isZero())
        {
            if (intnum.isNegative())
            {
                return RatNum.infinity(-1);
            }
        } else
        {
            return super.power(intnum);
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        int i = objectinput.readInt();
        if (i <= 0xc0000000)
        {
            i &= 0x7fffffff;
            if (i == 1)
            {
                i = objectinput.readInt();
            } else
            {
                int ai[] = new int[i];
                for (int j = i; --j >= 0;)
                {
                    ai[j] = objectinput.readInt();
                }

                words = ai;
            }
        }
        ival = i;
    }

    public Object readResolve()
        throws ObjectStreamException
    {
        return canonicalize();
    }

    public void realloc(int i)
    {
        if (i == 0)
        {
            if (words != null)
            {
                if (ival > 0)
                {
                    ival = words[0];
                }
                words = null;
            }
        } else
        if (words == null || words.length < i || words.length > i + 2)
        {
            int ai[] = new int[i];
            if (words == null)
            {
                ai[0] = ival;
                ival = 1;
            } else
            {
                if (i < ival)
                {
                    ival = i;
                }
                System.arraycopy(words, 0, ai, 0, ival);
            }
            words = ai;
            return;
        }
    }

    public double roundToDouble(int i, boolean flag, boolean flag1)
    {
        int j = intLength();
        int k = i + (j - 1);
        if (k < -1075)
        {
            return !flag ? 0.0D : 0.0D;
        }
        if (k > 1023)
        {
            return !flag ? (1.0D / 0.0D) : (-1.0D / 0.0D);
        }
        int l;
        int i1;
        long l1;
        if (k >= -1022)
        {
            l = 53;
        } else
        {
            l = 1022 + (k + 53);
        }
        i1 = j - (l + 1);
        if (i1 > 0)
        {
            if (words == null)
            {
                l1 = ival >> i1;
            } else
            {
                l1 = MPN.rshift_long(words, ival, i1);
            }
        } else
        {
            l1 = longValue() << -i1;
        }
        if (k == 1023 && l1 >> 1 == 0x1fffffffffffffL)
        {
            if (flag1 || checkBits(j - l))
            {
                return !flag ? (1.0D / 0.0D) : (-1.0D / 0.0D);
            }
            return !flag ? 1.7976931348623157E+308D : -1.7976931348623157E+308D;
        }
        long l3;
        int j1;
        long l4;
        if ((1L & l1) == 1L && ((2L & l1) == 2L || flag1 || checkBits(i1)))
        {
            l1 += 2L;
            long l2;
            if ((0x40000000000000L & l1) != 0L)
            {
                k++;
                l1 >>= 1;
            } else
            if (l == 52 && (0x20000000000000L & l1) != 0L)
            {
                k++;
            }
        }
        l2 = l1 >> 1;
        if (flag)
        {
            l3 = 0x8000000000000000L;
        } else
        {
            l3 = 0L;
        }
        j1 = 1023 + k;
        if (j1 <= 0)
        {
            l4 = 0L;
        } else
        {
            l4 = (long)j1 << 52;
        }
        return Double.longBitsToDouble(l2 & 0xffefffffffffffffL | (l3 | l4));
    }

    public final void set(int i)
    {
        words = null;
        ival = i;
    }

    public final void set(long l)
    {
        int i = (int)l;
        if ((long)i == l)
        {
            ival = i;
            words = null;
            return;
        } else
        {
            realloc(2);
            words[0] = i;
            words[1] = (int)(l >> 32);
            ival = 2;
            return;
        }
    }

    public final void set(IntNum intnum)
    {
        if (intnum.words == null)
        {
            set(intnum.ival);
        } else
        if (this != intnum)
        {
            realloc(intnum.ival);
            System.arraycopy(intnum.words, 0, words, 0, intnum.ival);
            ival = intnum.ival;
            return;
        }
    }

    public final void set(int ai[], int i)
    {
        ival = i;
        words = ai;
    }

    public final void setAdd(int i)
    {
        setAdd(this, i);
    }

    public void setAdd(IntNum intnum, int i)
    {
        if (intnum.words == null)
        {
            set((long)intnum.ival + (long)i);
            return;
        }
        int j = intnum.ival;
        realloc(j + 1);
        long l = i;
        for (int k = 0; k < j; k++)
        {
            long l1 = l + (0xffffffffL & (long)intnum.words[k]);
            words[k] = (int)l1;
            l = l1 >> 32;
        }

        if (intnum.words[j - 1] < 0)
        {
            l--;
        }
        words[j] = (int)l;
        ival = wordsNeeded(words, j + 1);
    }

    void setInvert()
    {
        if (words == null)
        {
            ival = -1 ^ ival;
        } else
        {
            int i = ival;
            while (--i >= 0) 
            {
                words[i] = -1 ^ words[i];
            }
        }
    }

    public final void setNegative()
    {
        setNegative(this);
    }

    public void setNegative(IntNum intnum)
    {
        int i = intnum.ival;
        if (intnum.words == null)
        {
            if (i == 0x80000000)
            {
                set(-(long)i);
                return;
            } else
            {
                set(-i);
                return;
            }
        }
        realloc(i + 1);
        if (negate(words, intnum.words, i))
        {
            int ai[] = words;
            int j = i + 1;
            ai[i] = 0;
            i = j;
        }
        ival = i;
    }

    void setShift(IntNum intnum, int i)
    {
        if (i > 0)
        {
            setShiftLeft(intnum, i);
            return;
        } else
        {
            setShiftRight(intnum, -i);
            return;
        }
    }

    void setShiftLeft(IntNum intnum, int i)
    {
        if (intnum.words != null) goto _L2; else goto _L1
_L1:
        if (i >= 32) goto _L4; else goto _L3
_L3:
        set((long)intnum.ival << i);
_L6:
        return;
_L4:
        int ai[];
        int j;
        ai = new int[1];
        ai[0] = intnum.ival;
        j = 1;
        break MISSING_BLOCK_LABEL_39;
_L2:
        ai = intnum.words;
        j = intnum.ival;
        int k = i >> 5;
        int l = i & 0x1f;
        int i1 = j + k;
        if (l == 0)
        {
            realloc(i1);
            for (int i2 = j; --i2 >= 0;)
            {
                words[i2 + k] = ai[i2];
            }

        } else
        {
            i1++;
            realloc(i1);
            int j1 = MPN.lshift(words, k, ai, j, l);
            int k1 = 32 - l;
            words[i1 - 1] = (j1 << k1) >> k1;
        }
        ival = i1;
        int l1 = k;
        while (--l1 >= 0) 
        {
            words[l1] = 0;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    void setShiftRight(IntNum intnum, int i)
    {
        int j = -1;
        if (intnum.words != null) goto _L2; else goto _L1
_L1:
        if (i >= 32) goto _L4; else goto _L3
_L3:
        j = intnum.ival >> i;
_L8:
        set(j);
_L6:
        return;
_L4:
        if (intnum.ival >= 0)
        {
            j = 0;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        boolean flag;
        int l;
        int i1;
        if (i == 0)
        {
            set(intnum);
            return;
        }
        flag = intnum.isNegative();
        int k = i >> 5;
        l = i & 0x1f;
        i1 = intnum.ival - k;
        if (i1 <= 0)
        {
            if (!flag)
            {
                j = 0;
            }
            set(j);
            return;
        }
        if (words == null || words.length < i1)
        {
            realloc(i1);
        }
        MPN.rshift0(words, intnum.words, k, i1, l);
        ival = i1;
        if (!flag) goto _L6; else goto _L5
_L5:
        int ai[] = words;
        int j1 = i1 - 1;
        ai[j1] = ai[j1] | -2 << 31 - l;
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public int sign()
    {
        int i;
        int ai[];
        i = ival;
        ai = words;
        if (ai != null) goto _L2; else goto _L1
_L1:
        if (i <= 0) goto _L4; else goto _L3
_L3:
        return 1;
_L4:
        return i >= 0 ? 0 : -1;
_L2:
        int j = i - 1;
        int k = ai[j];
        if (k <= 0)
        {
            if (k < 0)
            {
                return -1;
            }
            do
            {
                if (j == 0)
                {
                    return 0;
                }
                j--;
            } while (ai[j] == 0);
            return 1;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public IntNum toExactInt(int i)
    {
        return this;
    }

    public RealNum toInt(int i)
    {
        return this;
    }

    public String toString(int i)
    {
        if (words == null)
        {
            return Integer.toString(ival, i);
        }
        if (ival <= 2)
        {
            return Long.toString(longValue(), i);
        } else
        {
            StringBuilder stringbuilder = new StringBuilder(ival * (1 + MPN.chars_per_word(i)));
            format(i, stringbuilder);
            return stringbuilder.toString();
        }
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        int i;
        int k;
        if (words == null)
        {
            i = 1;
        } else
        {
            i = wordsNeeded(words, ival);
        }
        if (i > 1) goto _L2; else goto _L1
_L1:
        if (words == null)
        {
            k = ival;
        } else
        {
            int j = words.length;
            k = 0;
            if (j != 0)
            {
                k = words[0];
            }
        }
        if (k < 0xc0000000) goto _L4; else goto _L3
_L3:
        objectoutput.writeInt(k);
_L6:
        return;
_L4:
        objectoutput.writeInt(0x80000001);
        objectoutput.writeInt(k);
        return;
_L2:
        objectoutput.writeInt(0x80000000 | i);
        while (--i >= 0) 
        {
            objectoutput.writeInt(words[i]);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    static 
    {
        smallFixNums = new IntNum[1125];
        for (int i = 1125; --i >= 0;)
        {
            smallFixNums[i] = new IntNum(i - 100);
        }

    }
}
