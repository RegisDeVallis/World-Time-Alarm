// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

// Referenced classes of package gnu.kawa.xml:
//            TreeScanner

public class AncestorAxis extends TreeScanner
{

    public AncestorAxis()
    {
    }

    public static AncestorAxis make(NodePredicate nodepredicate)
    {
        AncestorAxis ancestoraxis = new AncestorAxis();
        ancestoraxis.type = nodepredicate;
        return ancestoraxis;
    }

    private static void scan(AbstractSequence abstractsequence, int i, int j, NodePredicate nodepredicate, PositionConsumer positionconsumer)
    {
        int k = abstractsequence.parentPos(i);
        if (k != j)
        {
            scan(abstractsequence, k, j, nodepredicate, positionconsumer);
            if (nodepredicate.isInstancePos(abstractsequence, k))
            {
                positionconsumer.writePosition(abstractsequence, k);
            }
        }
    }

    public void scan(AbstractSequence abstractsequence, int i, PositionConsumer positionconsumer)
    {
        scan(abstractsequence, i, abstractsequence.endPos(), type, positionconsumer);
    }
}
