// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;


// Referenced classes of package gnu.lists:
//            GapVector, SimpleVector, Consumer

public class StableVector extends GapVector
{

    static final int END_POSITION = 1;
    protected static final int FREE_POSITION = -2;
    static final int START_POSITION;
    protected int free;
    protected int positions[];

    protected StableVector()
    {
    }

    public StableVector(SimpleVector simplevector)
    {
        super(simplevector);
        positions = new int[16];
        positions[0] = 0;
        positions[1] = 1 | simplevector.getBufferLength() << 1;
        free = -1;
        for (int i = positions.length; --i > 1;)
        {
            positions[i] = free;
            free = i;
        }

    }

    protected int addPos(int i, Object obj)
    {
        int j = positions[i];
        int k = j >>> 1;
        if (k >= gapStart)
        {
            k += gapEnd - gapStart;
        }
        if ((j & 1) == 0)
        {
            if (i == 0)
            {
                i = createPos(0, true);
            } else
            {
                positions[i] = j | 1;
            }
        }
        add(k, obj);
        return i;
    }

    protected void adjustPositions(int i, int j, int k)
    {
        if (free >= -1)
        {
            unchainFreelist();
        }
        int l = i ^ 0x80000000;
        int i1 = j ^ 0x80000000;
        int j1 = positions.length;
        do
        {
            if (--j1 <= 0)
            {
                break;
            }
            int k1 = positions[j1];
            if (k1 != -2)
            {
                int l1 = k1 ^ 0x80000000;
                if (l1 >= l && l1 <= i1)
                {
                    positions[j1] = k1 + k;
                }
            }
        } while (true);
    }

    protected int allocPositionIndex()
    {
        if (free == -2)
        {
            chainFreelist();
        }
        if (free < 0)
        {
            int j = positions.length;
            int ai[] = new int[j * 2];
            System.arraycopy(positions, 0, ai, 0, j);
            for (int k = j * 2; --k >= j;)
            {
                ai[k] = free;
                free = k;
            }

            positions = ai;
        }
        int i = free;
        free = positions[free];
        return i;
    }

    protected void chainFreelist()
    {
        free = -1;
        int i = positions.length;
        do
        {
            if (--i <= 1)
            {
                break;
            }
            if (positions[i] == -2)
            {
                positions[i] = free;
                free = i;
            }
        } while (true);
    }

    public void consumePosRange(int i, int j, Consumer consumer)
    {
        super.consumePosRange(positions[i], positions[j], consumer);
    }

    public int copyPos(int i)
    {
        if (i > 1)
        {
            int j = allocPositionIndex();
            positions[j] = positions[i];
            i = j;
        }
        return i;
    }

    public int createPos(int i, boolean flag)
    {
        int j = 1;
        if (i == 0 && !flag)
        {
            return 0;
        }
        if (flag && i == size())
        {
            return j;
        }
        if (i > gapStart || i == gapStart && flag)
        {
            i += gapEnd - gapStart;
        }
        int k = allocPositionIndex();
        int ai[] = positions;
        int l = i << 1;
        if (!flag)
        {
            j = 0;
        }
        ai[k] = j | l;
        return k;
    }

    public int endPos()
    {
        return 1;
    }

    public void fillPosRange(int i, int j, Object obj)
    {
        fillPosRange(positions[i], positions[j], obj);
    }

    protected void gapReserve(int i, int j)
    {
        int k;
        int l;
        k = gapEnd;
        l = gapStart;
        if (j <= k - l) goto _L2; else goto _L1
_L1:
        int i1;
        int j1;
        i1 = base.size;
        super.gapReserve(i, j);
        j1 = base.size;
        if (i != l) goto _L4; else goto _L3
_L3:
        adjustPositions(k << 1, 1 | j1 << 1, j1 - i1 << 1);
_L6:
        return;
_L4:
        adjustPositions(k << 1, 1 | i1 << 1, l - k << 1);
        adjustPositions(gapStart << 1, 1 | j1 << 1, gapEnd - gapStart << 1);
        return;
_L2:
        if (i != gapStart)
        {
            shiftGap(i);
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public boolean hasNext(int i)
    {
        int j = positions[i] >>> 1;
        if (j >= gapStart)
        {
            j += gapEnd - gapStart;
        }
        return j < base.getBufferLength();
    }

    protected boolean isAfterPos(int i)
    {
        return (1 & positions[i]) != 0;
    }

    public int nextIndex(int i)
    {
        int j = positions[i] >>> 1;
        if (j > gapStart)
        {
            j -= gapEnd - gapStart;
        }
        return j;
    }

    public int nextPos(int i)
    {
        int j = positions[i];
        int k = j >>> 1;
        if (k >= gapStart)
        {
            k += gapEnd - gapStart;
        }
        if (k >= base.getBufferLength())
        {
            releasePos(i);
            return 0;
        }
        if (i == 0)
        {
            i = createPos(0, true);
        }
        positions[i] = j | 1;
        return i;
    }

    public void releasePos(int i)
    {
        if (i >= 2)
        {
            if (free == -2)
            {
                chainFreelist();
            }
            positions[i] = free;
            free = i;
        }
    }

    protected void removePosRange(int i, int j)
    {
        super.removePosRange(positions[i], positions[j]);
        int k = gapStart;
        int l = gapEnd;
        if (free >= -1)
        {
            unchainFreelist();
        }
        int i1 = positions.length;
        do
        {
            if (--i1 <= 0)
            {
                break;
            }
            int j1 = positions[i1];
            if (j1 != -2)
            {
                int k1 = j1 >> 1;
                boolean flag;
                if ((j1 & 1) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    if (k1 >= k && k1 < l)
                    {
                        positions[i1] = 1 | gapEnd << 1;
                    }
                } else
                if (k1 > k && k1 <= l)
                {
                    positions[i1] = gapStart << 1;
                }
            }
        } while (true);
    }

    protected void shiftGap(int i)
    {
        int j;
        int k;
        j = gapStart;
        k = i - j;
        if (k <= 0) goto _L2; else goto _L1
_L1:
        int l;
        int i1;
        int j1;
        int k1 = gapEnd;
        int l1 = k1 + k;
        j1 = j - k1 << 1;
        l = k1 << 1;
        i1 = -1 + (l1 << 1);
_L6:
        super.shiftGap(i);
        adjustPositions(l, i1, j1);
_L4:
        return;
_L2:
        if (i == j) goto _L4; else goto _L3
_L3:
        l = 1 + (i << 1);
        i1 = j << 1;
        j1 = gapEnd - j << 1;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public int startPos()
    {
        return 0;
    }

    protected void unchainFreelist()
    {
        int j;
        for (int i = free; i >= 0; i = j)
        {
            j = positions[i];
            positions[i] = -2;
        }

        free = -2;
    }
}
