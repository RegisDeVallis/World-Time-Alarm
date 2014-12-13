// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

// Referenced classes of package gnu.kawa.xml:
//            TreeScanner

public class PrecedingSiblingAxis extends TreeScanner
{

    public PrecedingSiblingAxis()
    {
    }

    public static PrecedingSiblingAxis make(NodePredicate nodepredicate)
    {
        PrecedingSiblingAxis precedingsiblingaxis = new PrecedingSiblingAxis();
        precedingsiblingaxis.type = nodepredicate;
        return precedingsiblingaxis;
    }

    public void scan(AbstractSequence abstractsequence, int i, PositionConsumer positionconsumer)
    {
        int j;
        int k;
        j = abstractsequence.endPos();
        k = abstractsequence.parentPos(i);
        if (k != j) goto _L2; else goto _L1
_L1:
        int l;
        return;
_L2:
        if ((l = abstractsequence.firstChildPos(k)) == 0)
        {
            continue;
        }
        if (type.isInstancePos(abstractsequence, l))
        {
            positionconsumer.writePosition(abstractsequence, l);
        }
        do
        {
            l = abstractsequence.nextMatching(l, type, i, false);
            if (l == 0)
            {
                continue;
            }
            positionconsumer.writePosition(abstractsequence, l);
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
    }
}
