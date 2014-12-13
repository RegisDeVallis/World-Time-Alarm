// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;
import gnu.lists.TreeList;

// Referenced classes of package gnu.kawa.xml:
//            TreeScanner

public class DescendantAxis extends TreeScanner
{

    public DescendantAxis()
    {
    }

    public static DescendantAxis make(NodePredicate nodepredicate)
    {
        DescendantAxis descendantaxis = new DescendantAxis();
        descendantaxis.type = nodepredicate;
        return descendantaxis;
    }

    public void scan(AbstractSequence abstractsequence, int i, PositionConsumer positionconsumer)
    {
        if (abstractsequence instanceof TreeList) goto _L2; else goto _L1
_L1:
        for (int l = abstractsequence.firstChildPos(i); l != 0; l = abstractsequence.nextPos(l))
        {
            if (type.isInstancePos(abstractsequence, l))
            {
                positionconsumer.writePosition(abstractsequence, l);
            }
            scan(abstractsequence, l, positionconsumer);
        }

          goto _L3
_L2:
        int j;
        int k;
        j = abstractsequence.nextPos(i);
        k = i;
_L6:
        k = abstractsequence.nextMatching(k, type, j, true);
        if (k != 0) goto _L4; else goto _L3
_L3:
        return;
_L4:
        positionconsumer.writePosition(abstractsequence, k);
        if (true) goto _L6; else goto _L5
_L5:
    }
}
