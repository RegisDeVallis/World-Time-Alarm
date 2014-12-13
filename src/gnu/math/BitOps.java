// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.math;


// Referenced classes of package gnu.math:
//            IntNum, MPN

public class BitOps
{

    static final byte bit4_count[] = {
        0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 
        2, 3, 2, 3, 3, 4
    };

    private BitOps()
    {
    }

    public static IntNum and(IntNum intnum, int i)
    {
        if (intnum.words == null)
        {
            return IntNum.make(i & intnum.ival);
        }
        if (i >= 0)
        {
            return IntNum.make(i & intnum.words[0]);
        }
        int j = intnum.ival;
        int ai[] = new int[j];
        for (ai[0] = i & intnum.words[0]; --j > 0; ai[j] = intnum.words[j]) { }
        return IntNum.make(ai, intnum.ival);
    }

    public static IntNum and(IntNum intnum, IntNum intnum1)
    {
        if (intnum1.words == null)
        {
            return and(intnum, intnum1.ival);
        }
        if (intnum.words == null)
        {
            return and(intnum1, intnum.ival);
        }
        if (intnum.ival < intnum1.ival)
        {
            IntNum intnum2 = intnum;
            intnum = intnum1;
            intnum1 = intnum2;
        }
        int i;
        int ai[];
        int j;
        if (intnum1.isNegative())
        {
            i = intnum.ival;
        } else
        {
            i = intnum1.ival;
        }
        ai = new int[i];
        for (j = 0; j < intnum1.ival; j++)
        {
            ai[j] = intnum.words[j] & intnum1.words[j];
        }

        for (; j < i; j++)
        {
            ai[j] = intnum.words[j];
        }

        return IntNum.make(ai, i);
    }

    public static int bitCount(int i)
    {
        int j = 0;
        for (; i != 0; i >>>= 4)
        {
            j += bit4_count[i & 0xf];
        }

        return j;
    }

    public static int bitCount(IntNum intnum)
    {
        int ai[] = intnum.words;
        int i;
        int j;
        if (ai == null)
        {
            i = 1;
            j = bitCount(intnum.ival);
        } else
        {
            i = intnum.ival;
            j = bitCount(ai, i);
        }
        if (intnum.isNegative())
        {
            j = i * 32 - j;
        }
        return j;
    }

    public static int bitCount(int ai[], int i)
    {
        int j;
        for (j = 0; --i >= 0; j += bitCount(ai[i])) { }
        return j;
    }

    public static IntNum bitOp(int i, IntNum intnum, IntNum intnum1)
    {
        switch (i)
        {
        default:
            IntNum intnum2 = new IntNum();
            setBitOp(intnum2, i, intnum, intnum1);
            intnum = intnum2.canonicalize();
            // fall through

        case 3: // '\003'
            return intnum;

        case 0: // '\0'
            return IntNum.zero();

        case 1: // '\001'
            return and(intnum, intnum1);

        case 5: // '\005'
            return intnum1;

        case 15: // '\017'
            return IntNum.minusOne();
        }
    }

    public static boolean bitValue(IntNum intnum, int i)
    {
        int j = intnum.ival;
        if (intnum.words != null) goto _L2; else goto _L1
_L1:
        if (i < 32) goto _L4; else goto _L3
_L3:
        if (j >= 0) goto _L6; else goto _L5
_L5:
        return true;
_L6:
        return false;
_L4:
        if ((1 & j >> i) != 0) goto _L5; else goto _L7
_L7:
        return false;
_L2:
        int k;
        k = i >> 5;
        if (k < j)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (intnum.words[j - 1] < 0) goto _L5; else goto _L8
_L8:
        return false;
        if ((1 & intnum.words[k] >> i) != 0) goto _L5; else goto _L9
_L9:
        return false;
    }

    static int[] dataBufferFor(IntNum intnum, int i)
    {
        int j = intnum.ival;
        int k = i + 1 >> 5;
        int ai[];
        if (intnum.words == null)
        {
            if (k == 0)
            {
                k = 1;
            }
            ai = new int[k];
            ai[0] = j;
            if (j < 0)
            {
                for (int l1 = 1; l1 < k; l1++)
                {
                    ai[l1] = -1;
                }

            }
        } else
        {
            int l = i + 1 >> 5;
            int i1;
            if (l > j)
            {
                i1 = l;
            } else
            {
                i1 = j;
            }
            ai = new int[i1];
            for (int j1 = j; --j1 >= 0;)
            {
                ai[j1] = intnum.words[j1];
            }

            if (ai[j - 1] < 0)
            {
                for (int k1 = j; k1 < l; k1++)
                {
                    ai[k1] = -1;
                }

            }
        }
        return ai;
    }

    public static IntNum extract(IntNum intnum, int i, int j)
    {
        if (j >= 32) goto _L2; else goto _L1
_L1:
        int j3;
        if (intnum.words == null)
        {
            j3 = intnum.ival;
        } else
        {
            j3 = intnum.words[0];
        }
        intnum = IntNum.make((j3 & (-1 ^ -1 << j)) >> i);
_L4:
        return intnum;
_L2:
        int k;
        int l;
        boolean flag;
        int j2;
        if (intnum.words == null)
        {
            if (intnum.ival >= 0)
            {
                int i3;
                if (i >= 31)
                {
                    i3 = 0;
                } else
                {
                    i3 = intnum.ival >> i;
                }
                return IntNum.make(i3);
            }
            k = 1;
        } else
        {
            k = intnum.ival;
        }
        flag = intnum.isNegative();
        if (j <= k * 32)
        {
            break MISSING_BLOCK_LABEL_184;
        }
        j = k * 32;
        if (!flag && i == 0) goto _L4; else goto _L3
_L3:
        l = j - i;
        if (l < 64)
        {
            long l2;
            if (intnum.words == null)
            {
                j2 = intnum.ival;
                int k2;
                if (i >= 32)
                {
                    k2 = 31;
                } else
                {
                    k2 = i;
                }
                l2 = j2 >> k2;
            } else
            {
                l2 = MPN.rshift_long(intnum.words, k, i);
            }
            return IntNum.make(l2 & (-1L ^ -1L << l));
        }
        break MISSING_BLOCK_LABEL_214;
        k = j + 31 >> 5;
          goto _L3
        int i1 = i >> 5;
        int ai[] = new int[(1 + (j >> 5)) - i1];
        if (intnum.words == null)
        {
            int l1;
            int i2;
            if (i >= 32)
            {
                i2 = -1;
            } else
            {
                i2 = intnum.ival >> i;
            }
            ai[0] = i2;
        } else
        {
            int j1 = k - i1;
            int k1 = i & 0x1f;
            MPN.rshift0(ai, intnum.words, i1, j1, k1);
        }
        l1 = l >> 5;
        ai[l1] = ai[l1] & (-1 ^ -1 << l);
        return IntNum.make(ai, l1 + 1);
    }

    public static IntNum ior(IntNum intnum, IntNum intnum1)
    {
        return bitOp(7, intnum, intnum1);
    }

    public static int lowestBitSet(int i)
    {
        int j;
        if (i == 0)
        {
            j = -1;
        } else
        {
            for (j = 0; (i & 0xff) == 0; j += 8)
            {
                i >>>= 8;
            }

            while ((i & 3) == 0) 
            {
                i >>>= 2;
                j += 2;
            }
            if ((i & 1) == 0)
            {
                return j + 1;
            }
        }
        return j;
    }

    public static int lowestBitSet(IntNum intnum)
    {
        int ai[] = intnum.words;
        if (ai == null)
        {
            return lowestBitSet(intnum.ival);
        }
        for (int i = intnum.ival; i < 0;)
        {
            int j = lowestBitSet(ai[0]);
            if (j >= 0)
            {
                return j + 0;
            }
        }

        return -1;
    }

    public static IntNum not(IntNum intnum)
    {
        return bitOp(12, intnum, IntNum.zero());
    }

    public static IntNum reverseBits(IntNum intnum, int i, int j)
    {
        int k = intnum.ival;
        if (intnum.words == null && j < 63)
        {
            long l3 = k;
            int i4 = i;
            for (int j4 = j - 1; i4 < j4; j4--)
            {
                long l4 = 1L & l3 >> i4;
                long l5 = 1L & l3 >> j4;
                l3 = l3 & (-1L ^ (1L << i4 | 1L << j4)) | l4 << j4 | l5 << i4;
                i4++;
            }

            return IntNum.make(l3);
        }
        int ai[] = dataBufferFor(intnum, j - 1);
        int l = i;
        int i1 = j - 1;
        while (l < i1) 
        {
            int j1 = l >> 5;
            int k1 = i1 >> 5;
            int l1 = ai[j1];
            int i2 = 1 & l1 >> l;
            int j3;
            if (j1 == k1)
            {
                int k3 = 1 & l1 >> i1;
                j3 = (int)((long)l1 & (-1L ^ (1L << l | 1L << i1))) | i2 << i1 | k3 << l;
            } else
            {
                int j2 = ai[k1];
                int k2 = 1 & j2 >> (i1 & 0x1f);
                int l2 = l1 & (-1 ^ 1 << (l & 0x1f));
                int i3 = j2 & (-1 ^ 1 << (i1 & 0x1f));
                j3 = l2 | k2 << (l & 0x1f);
                ai[k1] = i3 | i2 << (i1 & 0x1f);
            }
            ai[j1] = j3;
            l++;
            i1--;
        }
        return IntNum.make(ai, ai.length);
    }

    public static void setBitOp(IntNum intnum, int i, IntNum intnum1, IntNum intnum2)
    {
        int j;
        int k;
        int l;
        int i1;
        int ai[];
        int j1;
        int k1;
        int l1;
        int i2;
        if (intnum2.words != null && (intnum1.words == null || intnum1.ival < intnum2.ival))
        {
            IntNum intnum3 = intnum1;
            intnum1 = intnum2;
            intnum2 = intnum3;
            i = swappedOp(i);
        }
        if (intnum2.words == null)
        {
            j = intnum2.ival;
            k = 1;
        } else
        {
            j = intnum2.words[0];
            k = intnum2.ival;
        }
        if (intnum1.words == null)
        {
            l = intnum1.ival;
            i1 = 1;
        } else
        {
            l = intnum1.words[0];
            i1 = intnum1.ival;
        }
        if (i1 > 1)
        {
            intnum.realloc(i1);
        }
        ai = intnum.words;
        j1 = 0;
        i;
        JVM INSTR tableswitch 0 14: default 136
    //                   0 263
    //                   1 1257
    //                   2 1251
    //                   3 407
    //                   4 1245
    //                   5 1239
    //                   6 1233
    //                   7 1227
    //                   8 1221
    //                   9 1215
    //                   10 1209
    //                   11 1203
    //                   12 932
    //                   13 1197
    //                   14 1191;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16
_L3:
        break MISSING_BLOCK_LABEL_1257;
_L1:
        l1 = -1;
        k1 = 0;
_L21:
        if (k1 + 1 == i1)
        {
            j1 = 0;
        }
        j1;
        JVM INSTR tableswitch 0 2: default 184
    //                   0 1081
    //                   1 1117
    //                   2 1153;
           goto _L17 _L18 _L19 _L20
_L17:
        i2 = k1;
_L46:
        intnum.ival = i2;
        return;
_L2:
        j1 = 0;
        k1 = 0;
        l1 = 0;
          goto _L21
_L23:
        int j2 = k1 + 1;
        ai[k1] = l1;
        l = intnum1.words[j2];
        j = intnum2.words[j2];
        k1 = j2;
_L58:
        l1 = l & j;
        if (k1 + 1 < k) goto _L23; else goto _L22
_L22:
        j1 = 0;
        if (j < 0)
        {
            j1 = 1;
        }
          goto _L21
_L25:
        int k2 = k1 + 1;
        ai[k1] = l1;
        l = intnum1.words[k2];
        j = intnum2.words[k2];
        k1 = k2;
_L57:
        l1 = l & ~j;
        if (k1 + 1 < k) goto _L25; else goto _L24
_L24:
        j1 = 0;
        if (j >= 0)
        {
            j1 = 1;
        }
          goto _L21
_L5:
        l1 = l;
        j1 = 1;
        k1 = 0;
          goto _L21
_L27:
        int l2 = k1 + 1;
        ai[k1] = l1;
        l = intnum1.words[l2];
        j = intnum2.words[l2];
        k1 = l2;
_L56:
        l1 = j & ~l;
        if (k1 + 1 < k) goto _L27; else goto _L26
_L26:
        j1 = 0;
        if (j < 0)
        {
            j1 = 2;
        }
          goto _L21
_L29:
        int i3 = k1 + 1;
        ai[k1] = l1;
        intnum1.words[i3];
        j = intnum2.words[i3];
        k1 = i3;
_L55:
        l1 = j;
        if (k1 + 1 < k) goto _L29; else goto _L28
_L28:
        j1 = 0;
          goto _L21
_L31:
        int j3 = k1 + 1;
        ai[k1] = l1;
        l = intnum1.words[j3];
        j = intnum2.words[j3];
        k1 = j3;
_L54:
        l1 = l ^ j;
        if (k1 + 1 < k) goto _L31; else goto _L30
_L30:
        if (j < 0)
        {
            j1 = 2;
        } else
        {
            j1 = 1;
        }
          goto _L21
_L33:
        int k3 = k1 + 1;
        ai[k1] = l1;
        l = intnum1.words[k3];
        j = intnum2.words[k3];
        k1 = k3;
_L53:
        l1 = l | j;
        if (k1 + 1 < k) goto _L33; else goto _L32
_L32:
        j1 = 0;
        if (j >= 0)
        {
            j1 = 1;
        }
          goto _L21
_L35:
        int l3 = k1 + 1;
        ai[k1] = l1;
        l = intnum1.words[l3];
        j = intnum2.words[l3];
        k1 = l3;
_L52:
        l1 = -1 ^ (l | j);
        if (k1 + 1 < k) goto _L35; else goto _L34
_L34:
        j1 = 0;
        if (j >= 0)
        {
            j1 = 2;
        }
          goto _L21
_L37:
        int i4 = k1 + 1;
        ai[k1] = l1;
        l = intnum1.words[i4];
        j = intnum2.words[i4];
        k1 = i4;
_L51:
        l1 = -1 ^ (l ^ j);
        if (k1 + 1 < k) goto _L37; else goto _L36
_L36:
        if (j >= 0)
        {
            j1 = 2;
        } else
        {
            j1 = 1;
        }
          goto _L21
_L39:
        int j4 = k1 + 1;
        ai[k1] = l1;
        intnum1.words[j4];
        j = intnum2.words[j4];
        k1 = j4;
_L50:
        l1 = ~j;
        if (k1 + 1 < k) goto _L39; else goto _L38
_L38:
        j1 = 0;
          goto _L21
_L41:
        int k4 = k1 + 1;
        ai[k1] = l1;
        l = intnum1.words[k4];
        j = intnum2.words[k4];
        k1 = k4;
_L49:
        l1 = l | ~j;
        if (k1 + 1 < k) goto _L41; else goto _L40
_L40:
        j1 = 0;
        if (j < 0)
        {
            j1 = 1;
        }
          goto _L21
_L14:
        l1 = ~l;
        j1 = 2;
        k1 = 0;
          goto _L21
_L43:
        int l4 = k1 + 1;
        ai[k1] = l1;
        l = intnum1.words[l4];
        j = intnum2.words[l4];
        k1 = l4;
_L48:
        l1 = j | ~l;
        if (k1 + 1 < k) goto _L43; else goto _L42
_L42:
        j1 = 0;
        if (j >= 0)
        {
            j1 = 2;
        }
          goto _L21
_L45:
        int i5 = k1 + 1;
        ai[k1] = l1;
        l = intnum1.words[i5];
        j = intnum2.words[i5];
        k1 = i5;
_L47:
        l1 = -1 ^ l & j;
        if (k1 + 1 < k) goto _L45; else goto _L44
_L44:
        j1 = 0;
        if (j < 0)
        {
            j1 = 2;
        }
          goto _L21
_L18:
        if (k1 == 0 && ai == null)
        {
            intnum.ival = l1;
            k1;
            return;
        }
        i2 = k1 + 1;
        ai[k1] = l1;
          goto _L46
_L19:
        ai[k1] = l1;
        i2 = k1;
        while (++i2 < i1) 
        {
            ai[i2] = intnum1.words[i2];
        }
          goto _L46
_L20:
        ai[k1] = l1;
        i2 = k1;
        while (++i2 < i1) 
        {
            ai[i2] = -1 ^ intnum1.words[i2];
        }
          goto _L46
_L16:
        k1 = 0;
          goto _L47
_L15:
        k1 = 0;
          goto _L48
_L13:
        k1 = 0;
          goto _L49
_L12:
        k1 = 0;
          goto _L50
_L11:
        k1 = 0;
          goto _L51
_L10:
        k1 = 0;
          goto _L52
_L9:
        k1 = 0;
          goto _L53
_L8:
        k1 = 0;
          goto _L54
_L7:
        k1 = 0;
          goto _L55
_L6:
        k1 = 0;
          goto _L56
_L4:
        k1 = 0;
          goto _L57
        k1 = 0;
          goto _L58
    }

    public static IntNum setBitValue(IntNum intnum, int i, int j)
    {
        int k;
        int l;
        int i1;
        k = 31;
        l = j & 1;
        i1 = intnum.ival;
        if (intnum.words != null) goto _L2; else goto _L1
_L1:
        if (i < k)
        {
            k = i;
        }
        if ((1 & i1 >> k) != l) goto _L4; else goto _L3
_L3:
        return intnum;
_L4:
        if (i < 63)
        {
            return IntNum.make((long)i1 ^ (long)(1 << i));
        }
        break; /* Loop/switch isn't completed */
_L2:
        int j1 = i >> 5;
        int k1;
        int ai[];
        int l1;
        if (j1 >= i1)
        {
            if (intnum.words[i1 - 1] < 0)
            {
                k1 = 1;
            } else
            {
                k1 = 0;
            }
        } else
        {
            k1 = 1 & intnum.words[j1] >> i;
        }
        if (k1 == l) goto _L3; else goto _L5
_L5:
        ai = dataBufferFor(intnum, i);
        l1 = i >> 5;
        ai[l1] = ai[l1] ^ 1 << (i & 0x1f);
        return IntNum.make(ai, ai.length);
    }

    public static int swappedOp(int i)
    {
        return "\000\001\004\005\002\003\006\007\b\t\f\r\n\013\016\017".charAt(i);
    }

    public static boolean test(IntNum intnum, int i)
    {
        boolean flag;
label0:
        {
            if (intnum.words == null)
            {
                return (i & intnum.ival) != 0;
            }
            if (i >= 0)
            {
                int j = i & intnum.words[0];
                flag = false;
                if (j == 0)
                {
                    break label0;
                }
            }
            flag = true;
        }
        return flag;
    }

    public static boolean test(IntNum intnum, IntNum intnum1)
    {
        if (intnum1.words == null)
        {
            return test(intnum, intnum1.ival);
        }
        if (intnum.words == null)
        {
            return test(intnum1, intnum.ival);
        }
        if (intnum.ival < intnum1.ival)
        {
            IntNum intnum2 = intnum;
            intnum = intnum1;
            intnum1 = intnum2;
        }
        for (int i = 0; i < intnum1.ival; i++)
        {
            if ((intnum.words[i] & intnum1.words[i]) != 0)
            {
                return true;
            }
        }

        return intnum1.isNegative();
    }

    public static IntNum xor(IntNum intnum, IntNum intnum1)
    {
        return bitOp(6, intnum, intnum1);
    }

}
