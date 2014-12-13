// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.math;


class MPN
{

    MPN()
    {
    }

    public static int add_1(int ai[], int ai1[], int i, int j)
    {
        long l = 0xffffffffL & (long)j;
        for (int k = 0; k < i; k++)
        {
            long l1 = l + (0xffffffffL & (long)ai1[k]);
            ai[k] = (int)l1;
            l = l1 >> 32;
        }

        return (int)l;
    }

    public static int add_n(int ai[], int ai1[], int ai2[], int i)
    {
        long l = 0L;
        for (int j = 0; j < i; j++)
        {
            long l1 = l + ((0xffffffffL & (long)ai1[j]) + (0xffffffffL & (long)ai2[j]));
            ai[j] = (int)l1;
            l = l1 >>> 32;
        }

        return (int)l;
    }

    public static int chars_per_word(int i)
    {
        byte byte0;
        byte0 = 16;
        if (i >= 10)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        if (i >= 8)
        {
            break MISSING_BLOCK_LABEL_43;
        }
        if (i > 2) goto _L2; else goto _L1
_L1:
        byte0 = 32;
_L4:
        return byte0;
_L2:
        if (i == 3)
        {
            return 20;
        }
        if (i == 4) goto _L4; else goto _L3
_L3:
        return 18 - i;
        return 10;
        if (i < 12)
        {
            return 9;
        }
        if (i <= byte0)
        {
            return 8;
        }
        if (i <= 23)
        {
            return 7;
        }
        if (i <= 40)
        {
            return 6;
        }
        return i > 256 ? 1 : 4;
    }

    public static int cmp(int ai[], int i, int ai1[], int j)
    {
        if (i > j)
        {
            return 1;
        }
        if (i < j)
        {
            return -1;
        } else
        {
            return cmp(ai, ai1, i);
        }
    }

    public static int cmp(int ai[], int ai1[], int i)
    {
        while (--i >= 0) 
        {
            int j = ai[i];
            int k = ai1[i];
            if (j != k)
            {
                return (j ^ 0x80000000) <= (0x80000000 ^ k) ? -1 : 1;
            }
        }
        return 0;
    }

    public static int count_leading_zeros(int i)
    {
        int j;
        if (i == 0)
        {
            j = 32;
        } else
        {
            j = 0;
            int k = 16;
            while (k > 0) 
            {
                int l = i >>> k;
                if (l == 0)
                {
                    j += k;
                } else
                {
                    i = l;
                }
                k >>= 1;
            }
        }
        return j;
    }

    public static void divide(int ai[], int i, int ai1[], int j)
    {
        int k = i;
        do
        {
            int l;
            if (ai[k] == ai1[j - 1])
            {
                l = -1;
            } else
            {
                l = (int)udiv_qrnnd(((long)ai[k] << 32) + (0xffffffffL & (long)ai[k - 1]), ai1[j - 1]);
            }
            if (l != 0)
            {
                int i1 = submul_1(ai, k - j, ai1, j, l);
                long l2;
                for (long l1 = (0xffffffffL & (long)ai[k]) - (0xffffffffL & (long)i1); l1 != 0L; l1 = l2 - 1L)
                {
                    l--;
                    l2 = 0L;
                    for (int j1 = 0; j1 < j; j1++)
                    {
                        long l3 = l2 + ((0xffffffffL & (long)ai[j1 + (k - j)]) + (0xffffffffL & (long)ai1[j1]));
                        ai[j1 + (k - j)] = (int)l3;
                        l2 = l3 >>> 32;
                    }

                    ai[k] = (int)(l2 + (long)ai[k]);
                }

            }
            ai[k] = l;
        } while (--k >= j);
    }

    public static int divmod_1(int ai[], int ai1[], int i, int j)
    {
        int k = i - 1;
        long l = ai1[k];
        long l1;
        if ((l & 0xffffffffL) >= (0xffffffffL & (long)j))
        {
            l1 = 0L;
        } else
        {
            int i1 = k - 1;
            ai[k] = 0;
            l1 = l << 32;
            k = i1;
        }
        for (; k >= 0; k--)
        {
            int j1 = ai1[k];
            l1 = udiv_qrnnd(0xffffffff00000000L & l1 | 0xffffffffL & (long)j1, j);
            ai[k] = (int)l1;
        }

        return (int)(l1 >> 32);
    }

    static int findLowestBit(int i)
    {
        int j;
        for (j = 0; (i & 0xf) == 0; j += 4)
        {
            i >>= 4;
        }

        if ((i & 3) == 0)
        {
            i >>= 2;
            j += 2;
        }
        if ((i & 1) == 0)
        {
            j++;
        }
        return j;
    }

    static int findLowestBit(int ai[])
    {
        int i = 0;
        do
        {
            if (ai[i] != 0)
            {
                return i * 32 + findLowestBit(ai[i]);
            }
            i++;
        } while (true);
    }

    public static int gcd(int ai[], int ai1[], int i)
    {
        int j = 0;
_L2:
        int l;
        int i1;
        int j1;
        int ai2[];
        int ai3[];
        int k = ai[j] | ai1[j];
        if (k != 0)
        {
            l = j;
            i1 = findLowestBit(k);
            j1 = i - l;
            rshift0(ai, ai, l, j1, i1);
            rshift0(ai1, ai1, l, j1, i1);
            if ((1 & ai[0]) != 0)
            {
                ai2 = ai;
                ai3 = ai1;
            } else
            {
                ai2 = ai1;
                ai3 = ai;
            }
            break MISSING_BLOCK_LABEL_68;
        } else
        {
            j++;
            continue; /* Loop/switch isn't completed */
        }
label0:
        {
            do
            {
                int k1;
                for (k1 = 0; ai3[k1] == 0; k1++) { }
                if (k1 > 0)
                {
                    int j3;
                    for (j3 = 0; j3 < j1 - k1; j3++)
                    {
                        ai3[j3] = ai3[j3 + k1];
                    }

                    for (; j3 < j1; j3++)
                    {
                        ai3[j3] = 0;
                    }

                }
                int l1 = findLowestBit(ai3[0]);
                if (l1 > 0)
                {
                    rshift(ai3, ai3, 0, j1, l1);
                }
                int i2 = cmp(ai2, ai3, j1);
                if (i2 == 0)
                {
                    if (l + i1 <= 0)
                    {
                        break label0;
                    }
                    int ai4[];
                    int k2;
                    if (i1 > 0)
                    {
                        int l2 = lshift(ai, l, ai, j1, i1);
                        if (l2 != 0)
                        {
                            int i3 = j1 + 1;
                            ai[j1 + l] = l2;
                            j1 = i3;
                        }
                    } else
                    {
                        int j2 = j1;
                        while (--j2 >= 0) 
                        {
                            ai[j2 + l] = ai[j2];
                        }
                    }
                    for (k2 = l; --k2 >= 0;)
                    {
                        ai[k2] = 0;
                    }

                    break;
                }
                if (i2 > 0)
                {
                    sub_n(ai2, ai2, ai3, j1);
                    ai4 = ai2;
                    ai2 = ai3;
                    ai3 = ai4;
                } else
                {
                    sub_n(ai3, ai3, ai2, j1);
                }
                while (ai2[j1 - 1] == 0 && ai3[j1 - 1] == 0) 
                {
                    j1--;
                }
            } while (true);
            j1 += l;
        }
        return j1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static int intLength(int i)
    {
        if (i < 0)
        {
            i = ~i;
        }
        return 32 - count_leading_zeros(i);
    }

    public static int intLength(int ai[], int i)
    {
        int j = i - 1;
        return intLength(ai[j]) + j * 32;
    }

    public static int lshift(int ai[], int i, int ai1[], int j, int k)
    {
        int l = 32 - k;
        int i1 = j - 1;
        int j1 = ai1[i1];
        int k1 = j1 >>> l;
        int l1 = i + 1;
        while (--i1 >= 0) 
        {
            int i2 = ai1[i1];
            ai[l1 + i1] = j1 << k | i2 >>> l;
            j1 = i2;
        }
        ai[l1 + i1] = j1 << k;
        return k1;
    }

    public static void mul(int ai[], int ai1[], int i, int ai2[], int j)
    {
        ai[i] = mul_1(ai, ai1, i, ai2[0]);
        for (int k = 1; k < j; k++)
        {
            long l = 0xffffffffL & (long)ai2[k];
            long l1 = 0L;
            for (int i1 = 0; i1 < i; i1++)
            {
                long l2 = l1 + (l * (0xffffffffL & (long)ai1[i1]) + (0xffffffffL & (long)ai[k + i1]));
                ai[k + i1] = (int)l2;
                l1 = l2 >>> 32;
            }

            ai[k + i] = (int)l1;
        }

    }

    public static int mul_1(int ai[], int ai1[], int i, int j)
    {
        long l = 0xffffffffL & (long)j;
        long l1 = 0L;
        for (int k = 0; k < i; k++)
        {
            long l2 = l1 + l * (0xffffffffL & (long)ai1[k]);
            ai[k] = (int)l2;
            l1 = l2 >>> 32;
        }

        return (int)l1;
    }

    public static int rshift(int ai[], int ai1[], int i, int j, int k)
    {
        int l = 32 - k;
        int i1 = ai1[i];
        int j1 = i1 << l;
        int k1;
        for (k1 = 1; k1 < j; k1++)
        {
            int l1 = ai1[i + k1];
            ai[k1 - 1] = i1 >>> k | l1 << l;
            i1 = l1;
        }

        ai[k1 - 1] = i1 >>> k;
        return j1;
    }

    public static void rshift0(int ai[], int ai1[], int i, int j, int k)
    {
        if (k > 0)
        {
            rshift(ai, ai1, i, j, k);
        } else
        {
            int l = 0;
            while (l < j) 
            {
                ai[l] = ai1[l + i];
                l++;
            }
        }
    }

    public static long rshift_long(int ai[], int i, int j)
    {
        int k = j >> 5;
        int l = j & 0x1f;
        byte byte0;
        int i1;
        int j1;
        int k1;
        if (ai[i - 1] < 0)
        {
            byte0 = -1;
        } else
        {
            byte0 = 0;
        }
        if (k >= i)
        {
            i1 = byte0;
        } else
        {
            i1 = ai[k];
        }
        j1 = k + 1;
        if (j1 >= i)
        {
            k1 = byte0;
        } else
        {
            k1 = ai[j1];
        }
        if (l != 0)
        {
            int l1 = j1 + 1;
            int i2;
            if (l1 >= i)
            {
                i2 = byte0;
            } else
            {
                i2 = ai[l1];
            }
            i1 = i1 >>> l | k1 << 32 - l;
            k1 = k1 >>> l | i2 << 32 - l;
        }
        return (long)k1 << 32 | 0xffffffffL & (long)i1;
    }

    public static int set_str(int ai[], byte abyte0[], int i, int j)
    {
        if ((j & j - 1) == 0)
        {
            int j3 = 0;
            int k3 = 0;
            int l3 = j;
            do
            {
                l3 >>= 1;
                if (l3 == 0)
                {
                    break;
                }
                k3++;
            } while (true);
            int i4 = 0;
            int j4 = i;
            int i1 = 0;
            while (--j4 >= 0) 
            {
                byte byte0 = abyte0[j4];
                i4 |= byte0 << j3;
                j3 += k3;
                int k;
                int l;
                int j1;
                int k1;
                int l1;
                int i2;
                int j2;
                int k2;
                int l2;
                int i3;
                int k4;
                int l4;
                if (j3 >= 32)
                {
                    l4 = i1 + 1;
                    ai[i1] = i4;
                    j3 -= 32;
                    i4 = byte0 >> k3 - j3;
                } else
                {
                    l4 = i1;
                }
                i1 = l4;
            }
            if (i4 != 0)
            {
                k4 = i1 + 1;
                ai[i1] = i4;
                return k4;
            }
        } else
        {
            k = chars_per_word(j);
            l = 0;
            i1 = 0;
            while (l < i) 
            {
                j1 = i - l;
                if (j1 > k)
                {
                    j1 = k;
                }
                k1 = l + 1;
                l1 = abyte0[l];
                i2 = j;
                for (l = k1; --j1 > 0; l = i3)
                {
                    l2 = l1 * j;
                    i3 = l + 1;
                    l1 = l2 + abyte0[l];
                    i2 *= j;
                }

                if (i1 == 0)
                {
                    j2 = l1;
                } else
                {
                    j2 = mul_1(ai, ai, i1, i2) + add_1(ai, ai, i1, l1);
                }
                if (j2 != 0)
                {
                    k2 = i1 + 1;
                    ai[i1] = j2;
                } else
                {
                    k2 = i1;
                }
                i1 = k2;
            }
        }
        return i1;
    }

    public static int sub_n(int ai[], int ai1[], int ai2[], int i)
    {
        int j = 0;
        int k = 0;
        while (k < i) 
        {
            int l = ai2[k];
            int i1 = ai1[k];
            int j1 = l + j;
            int k1;
            int l1;
            int i2;
            if ((j1 ^ 0x80000000) < (j ^ 0x80000000))
            {
                k1 = 1;
            } else
            {
                k1 = 0;
            }
            l1 = i1 - j1;
            if ((l1 ^ 0x80000000) > (i1 ^ 0x80000000))
            {
                i2 = 1;
            } else
            {
                i2 = 0;
            }
            j = k1 + i2;
            ai[k] = l1;
            k++;
        }
        return j;
    }

    public static int submul_1(int ai[], int i, int ai1[], int j, int k)
    {
        long l = 0xffffffffL & (long)k;
        int i1 = 0;
        int j1 = 0;
        do
        {
            long l1 = l * (0xffffffffL & (long)ai1[j1]);
            int k1 = (int)l1;
            int i2 = (int)(l1 >> 32);
            int j2 = k1 + i1;
            int k2;
            int l2;
            int i3;
            if ((0x80000000 ^ j2) < (0x80000000 ^ i1))
            {
                k2 = 1;
            } else
            {
                k2 = 0;
            }
            i1 = k2 + i2;
            l2 = ai[i + j1];
            i3 = l2 - j2;
            if ((0x80000000 ^ i3) > (0x80000000 ^ l2))
            {
                i1++;
            }
            ai[i + j1] = i3;
        } while (++j1 < j);
        return i1;
    }

    public static long udiv_qrnnd(long l, int i)
    {
        long l1;
        long l2;
        l1 = l >>> 32;
        l2 = l & 0xffffffffL;
        if (i < 0) goto _L2; else goto _L1
_L1:
        long l8;
        long l10;
        if (l1 < (0xffffffffL & (long)i - l1 - (l2 >>> 31)))
        {
            l8 = l / (long)i;
            l10 = l % (long)i;
        } else
        {
            long l11 = l - ((long)i << 31);
            long l12 = l11 / (long)i;
            l10 = l11 % (long)i;
            l8 = l12 - 0x80000000L;
        }
_L4:
        return l10 << 32 | 0xffffffffL & l8;
_L2:
        long l3 = i >>> 1;
        long l4 = l >>> 1;
        if (l1 < l3 || l1 >> 1 < l3)
        {
            long l9;
            if (l1 < l3)
            {
                l8 = l4 / l3;
                l9 = l4 % l3;
            } else
            {
                long l5 = -1L ^ l4 - (l3 << 32);
                long l6 = l5 / l3;
                long l7 = l5 % l3;
                l8 = 0xffffffffL & (-1L ^ l6);
                l9 = l3 - 1L - l7;
            }
            l10 = 2L * l9 + (1L & l2);
            if ((i & 1) != 0)
            {
                if (l10 >= l8)
                {
                    l10 -= l8;
                } else
                if (l8 - l10 <= (0xffffffffL & (long)i))
                {
                    l10 = (l10 - l8) + (long)i;
                    l8--;
                } else
                {
                    l10 = (l10 - l8) + (long)i + (long)i;
                    l8 -= 2L;
                }
            }
        } else
        if (l2 >= (0xffffffffL & (long)(-i)))
        {
            l8 = -1L;
            l10 = l2 + (long)i;
        } else
        {
            l8 = -2L;
            l10 = l2 + (long)i + (long)i;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }
}
