// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

// Referenced classes of package gnu.kawa.xml:
//            TreeScanner

public class ChildAxis extends TreeScanner
{

    public ChildAxis()
    {
    }

    public static ChildAxis make(NodePredicate nodepredicate)
    {
        ChildAxis childaxis = new ChildAxis();
        childaxis.type = nodepredicate;
        return childaxis;
    }

    public void scan(AbstractSequence abstractsequence, int i, PositionConsumer positionconsumer)
    {
        for (int j = abstractsequence.firstChildPos(i, type); j != 0; j = abstractsequence.nextMatching(j, type, -1, false))
        {
            positionconsumer.writePosition(abstractsequence, j);
        }

    }
}
