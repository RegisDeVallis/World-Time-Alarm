// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.lists.TreePosition;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;

public class Children extends MethodProc
{

    public static final Children children = new Children();

    public Children()
    {
    }

    public static void children(TreeList treelist, int i, Consumer consumer)
    {
        int j = treelist.gotoChildrenStart(i);
        if (j >= 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int k = treelist.nextDataIndex(i);
        do
        {
            int l = j << 1;
            int i1 = treelist.nextNodeIndex(j, k);
            i1;
            if (i1 == j)
            {
                i1 = treelist.nextDataIndex(j);
            }
            if (i1 < 0)
            {
                continue;
            }
            if (consumer instanceof PositionConsumer)
            {
                ((PositionConsumer)consumer).writePosition(treelist, l);
            } else
            {
                treelist.consumeIRange(j, i1, consumer);
            }
            j = i1;
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
    }

    public static void children(Object obj, Consumer consumer)
    {
        if (obj instanceof TreeList)
        {
            children((TreeList)obj, 0, consumer);
        } else
        if ((obj instanceof SeqPosition) && !(obj instanceof TreePosition))
        {
            SeqPosition seqposition = (SeqPosition)obj;
            if (seqposition.sequence instanceof TreeList)
            {
                children((TreeList)seqposition.sequence, seqposition.ipos >> 1, consumer);
                return;
            }
        }
    }

    public void apply(CallContext callcontext)
    {
        Consumer consumer = callcontext.consumer;
        Object obj = callcontext.getNextArg();
        callcontext.lastArg();
        if (obj instanceof Values)
        {
            TreeList treelist = (TreeList)obj;
            int i = 0;
            do
            {
                int j = treelist.getNextKind(i << 1);
                if (j == 0)
                {
                    return;
                }
                if (j == 32)
                {
                    children(treelist.getPosNext(i << 1), consumer);
                } else
                {
                    children(treelist, i, consumer);
                }
                i = treelist.nextDataIndex(i);
            } while (true);
        } else
        {
            children(obj, consumer);
            return;
        }
    }

    public int numArgs()
    {
        return 4097;
    }

}
