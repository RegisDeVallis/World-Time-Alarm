// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;


// Referenced classes of package gnu.lists:
//            GeneralArray, Sequence, Consumer, SimpleVector

public class GeneralArray1 extends GeneralArray
    implements Sequence
{

    public GeneralArray1()
    {
    }

    public void consumePosRange(int i, int j, Consumer consumer)
    {
        if (!consumer.ignoring())
        {
            int k = i;
            while (!equals(k, j)) 
            {
                if (!hasNext(k))
                {
                    throw new RuntimeException();
                }
                base.consume(offset + strides[0] * (k >>> 1), 1, consumer);
                k = nextPos(k);
            }
        }
    }

    public int createPos(int i, boolean flag)
    {
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

    protected int nextIndex(int i)
    {
        if (i == -1)
        {
            return size();
        } else
        {
            return i >>> 1;
        }
    }
}
