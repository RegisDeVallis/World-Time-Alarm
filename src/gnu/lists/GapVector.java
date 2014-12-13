// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;


// Referenced classes of package gnu.lists:
//            AbstractSequence, Sequence, SimpleVector, Consumer

public class GapVector extends AbstractSequence
    implements Sequence
{

    public SimpleVector base;
    public int gapEnd;
    public int gapStart;

    protected GapVector()
    {
    }

    public GapVector(SimpleVector simplevector)
    {
        base = simplevector;
        gapStart = 0;
        gapEnd = simplevector.size;
    }

    public void add(int i, Object obj)
    {
        gapReserve(i, 1);
        base.set(i, obj);
        gapStart = 1 + gapStart;
    }

    protected int addPos(int i, Object obj)
    {
        int j = i >>> 1;
        if (j >= gapStart)
        {
            j += gapEnd - gapStart;
        }
        add(j, obj);
        return 1 | j + 1 << 1;
    }

    public void consumePosRange(int i, int j, Consumer consumer)
    {
        if (!consumer.ignoring())
        {
            int k = i >>> 1;
            int l = j >>> 1;
            if (k < gapStart)
            {
                int i1;
                if (l > gapStart)
                {
                    i1 = l;
                } else
                {
                    i1 = gapStart;
                }
                consumePosRange(i, i1 << 1, consumer);
            }
            if (l > gapEnd)
            {
                if (k < gapEnd)
                {
                    k = gapEnd;
                }
                consumePosRange(k << 1, j, consumer);
                return;
            }
        }
    }

    public int createPos(int i, boolean flag)
    {
        if (i > gapStart)
        {
            i += gapEnd - gapStart;
        }
        int j = i << 1;
        boolean flag1;
        if (flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        return flag1 | j;
    }

    public void fill(Object obj)
    {
        base.fill(gapEnd, base.size, obj);
        base.fill(0, gapStart, obj);
    }

    public void fillPosRange(int i, int j, Object obj)
    {
        int k;
        int l;
        int i1;
        int j1;
        if (i == -1)
        {
            k = base.size;
        } else
        {
            k = i >>> 1;
        }
        if (j == -1)
        {
            l = base.size;
        } else
        {
            l = j >>> 1;
        }
        if (gapStart < l)
        {
            i1 = gapStart;
        } else
        {
            i1 = l;
        }
        for (j1 = k; j1 < i1; j1++)
        {
            base.setBuffer(j1, obj);
        }

        for (int k1 = gapEnd; k1 < l; k1++)
        {
            base.setBuffer(k1, obj);
        }

    }

    protected final void gapReserve(int i)
    {
        gapReserve(gapStart, i);
    }

    protected void gapReserve(int i, int j)
    {
        int k = 16;
        if (j > gapEnd - gapStart)
        {
            int l = base.size;
            int i1;
            int j1;
            int k1;
            if (l >= k)
            {
                k = l * 2;
            }
            i1 = l - (gapEnd - gapStart);
            j1 = i1 + j;
            if (k < j1)
            {
                k = j1;
            }
            k1 = i + (k - i1);
            base.resizeShift(gapStart, gapEnd, i, k1);
            gapStart = i;
            gapEnd = k1;
        } else
        if (i != gapStart)
        {
            shiftGap(i);
            return;
        }
    }

    public Object get(int i)
    {
        if (i >= gapStart)
        {
            i += gapEnd - gapStart;
        }
        return base.get(i);
    }

    public int getNextKind(int i)
    {
        if (hasNext(i))
        {
            return base.getElementKind();
        } else
        {
            return 0;
        }
    }

    public int getSegment(int i, int j)
    {
        int k = size();
        if (i < 0 || i > k)
        {
            i = -1;
        } else
        {
            if (j < 0)
            {
                j = 0;
            } else
            if (i + j > k)
            {
                j = k - i;
            }
            if (i + j > gapStart)
            {
                if (i >= gapStart)
                {
                    return i + (gapEnd - gapStart);
                }
                if (gapStart - i > j >> 1)
                {
                    shiftGap(i + j);
                    return i;
                } else
                {
                    shiftGap(i);
                    return i + (gapEnd - gapStart);
                }
            }
        }
        return i;
    }

    public boolean hasNext(int i)
    {
        int j = i >>> 1;
        if (j >= gapStart)
        {
            j += gapEnd - gapStart;
        }
        return j < base.size;
    }

    protected boolean isAfterPos(int i)
    {
        return (i & 1) != 0;
    }

    protected int nextIndex(int i)
    {
        int j;
        if (i == -1)
        {
            j = base.size;
        } else
        {
            j = i >>> 1;
        }
        if (j > gapStart)
        {
            j -= gapEnd - gapStart;
        }
        return j;
    }

    protected void removePosRange(int i, int j)
    {
        int k;
        int l;
        k = i >>> 1;
        l = j >>> 1;
        if (k <= gapEnd) goto _L2; else goto _L1
_L1:
        shiftGap((k - gapEnd) + gapStart);
_L4:
        if (k < gapStart)
        {
            base.clearBuffer(k, gapStart - k);
            gapStart = k;
        }
        if (l > gapEnd)
        {
            base.clearBuffer(gapEnd, l - gapEnd);
            gapEnd = l;
        }
        return;
_L2:
        if (l < gapStart)
        {
            shiftGap(l);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public Object set(int i, Object obj)
    {
        if (i >= gapStart)
        {
            i += gapEnd - gapStart;
        }
        return base.set(i, obj);
    }

    protected void shiftGap(int i)
    {
        int j = i - gapStart;
        if (j <= 0) goto _L2; else goto _L1
_L1:
        base.shift(gapEnd, gapStart, j);
_L4:
        gapEnd = j + gapEnd;
        gapStart = i;
        return;
_L2:
        if (j < 0)
        {
            base.shift(i, j + gapEnd, -j);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public int size()
    {
        return base.size - (gapEnd - gapStart);
    }
}
