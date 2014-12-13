// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.NodePredicate;
import gnu.lists.PositionConsumer;

// Referenced classes of package gnu.kawa.xml:
//            TreeScanner

public class FollowingAxis extends TreeScanner
{

    public FollowingAxis()
    {
    }

    public static FollowingAxis make(NodePredicate nodepredicate)
    {
        FollowingAxis followingaxis = new FollowingAxis();
        followingaxis.type = nodepredicate;
        return followingaxis;
    }

    public void scan(AbstractSequence abstractsequence, int i, PositionConsumer positionconsumer)
    {
        int j = abstractsequence.endPos();
        int k = abstractsequence.nextPos(i);
        if (k != 0 && type.isInstancePos(abstractsequence, k))
        {
            positionconsumer.writePosition(abstractsequence, k);
        }
        do
        {
            k = abstractsequence.nextMatching(k, type, j, true);
            if (k == 0)
            {
                return;
            }
            positionconsumer.writePosition(abstractsequence, k);
        } while (true);
    }
}
