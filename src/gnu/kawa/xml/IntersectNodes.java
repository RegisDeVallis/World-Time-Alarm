// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;

// Referenced classes of package gnu.kawa.xml:
//            SortedNodes

public class IntersectNodes extends Procedure2
{

    public static final IntersectNodes exceptNodes = new IntersectNodes(true);
    public static final IntersectNodes intersectNodes = new IntersectNodes(false);
    boolean isExcept;

    public IntersectNodes(boolean flag)
    {
        isExcept = flag;
    }

    public Object apply2(Object obj, Object obj1)
    {
        SortedNodes sortednodes;
        SortedNodes sortednodes1;
        SortedNodes sortednodes2;
        int i;
        AbstractSequence abstractsequence;
        int j;
        int k;
        int l;
        sortednodes = new SortedNodes();
        sortednodes1 = new SortedNodes();
        sortednodes2 = new SortedNodes();
        Values.writeValues(obj, sortednodes);
        Values.writeValues(obj1, sortednodes1);
        i = 0;
        abstractsequence = null;
        j = 0;
        k = 0;
        l = 0;
_L1:
        AbstractSequence abstractsequence1 = sortednodes.getSeq(l);
        if (abstractsequence1 == null)
        {
            return sortednodes2;
        }
        int i1 = sortednodes.getPos(l);
        int j1;
        boolean flag;
        int k1;
        if (k == -1)
        {
            k = AbstractSequence.compare(abstractsequence1, i1, abstractsequence, j);
            j1 = i;
        } else
        if (k == 0)
        {
            k = 1;
            j1 = i;
        } else
        {
            j1 = i;
        }
label0:
        {
            if (k > 0)
            {
                abstractsequence = sortednodes1.getSeq(j1);
                if (abstractsequence != null)
                {
                    break label0;
                }
                k = -2;
            }
            if (k == 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag != isExcept)
            {
                sortednodes2.writePosition(abstractsequence1, i1);
            }
            l++;
            i = j1;
        }
          goto _L1
        k1 = j1 + 1;
        j = sortednodes1.getPos(j1);
        k = AbstractSequence.compare(abstractsequence1, i1, abstractsequence, j);
        j1 = k1;
        break MISSING_BLOCK_LABEL_99;
    }

}
