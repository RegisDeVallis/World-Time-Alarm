// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.AbstractSequence;

// Referenced classes of package gnu.kawa.xml:
//            Nodes

public class SortedNodes extends Nodes
{

    int nesting;

    public SortedNodes()
    {
        nesting = 0;
    }

    int compareIndex(int i, AbstractSequence abstractsequence, int j)
    {
        if (data[i] != '\uF10F')
        {
            throw new RuntimeException("invalid kind of value to compare");
        } else
        {
            return AbstractSequence.compare((AbstractSequence)objects[getIntN(i + 1)], getIntN(i + 3), abstractsequence, j);
        }
    }

    int find(int i, int j, AbstractSequence abstractsequence, int k)
    {
        int l = 0;
        for (int i1 = j; l < i1;)
        {
            int j1 = l + i1 >>> 1;
            int k1 = compareIndex(i + j1 * 5, abstractsequence, k);
            if (k1 == 0)
            {
                return -1;
            }
            if (k1 > 0)
            {
                i1 = j1;
            } else
            {
                l = j1 + 1;
            }
        }

        return i + l * 5;
    }

    public void writePosition(AbstractSequence abstractsequence, int i)
    {
        if (count <= 0) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        j = -5 + gapStart;
        k = compareIndex(j, abstractsequence, i);
        if (k >= 0) goto _L4; else goto _L3
_L3:
        int k1;
        int j1 = gapEnd;
        k1 = find(j1, (data.length - j1) / 5, abstractsequence, i);
        if (k1 >= 0) goto _L6; else goto _L5
_L5:
        return;
_L6:
        int l1 = k1 - gapEnd;
        if (l1 > 0)
        {
            System.arraycopy(data, gapEnd, data, gapStart, l1);
            gapEnd = k1;
            gapStart = l1 + gapStart;
        }
_L2:
        super.writePosition(abstractsequence, i);
        return;
_L4:
        if (k == 0) goto _L5; else goto _L7
_L7:
        int l = find(0, j / 5, abstractsequence, i);
        if (l < 0) goto _L5; else goto _L8
_L8:
        int i1 = gapStart - l;
        if (i1 > 0)
        {
            System.arraycopy(data, l, data, gapEnd - i1, i1);
            gapStart = l;
            gapEnd = gapEnd - i1;
        }
          goto _L2
    }
}
