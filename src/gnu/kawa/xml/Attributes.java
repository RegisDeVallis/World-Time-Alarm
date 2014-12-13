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
import java.io.PrintStream;

public class Attributes extends MethodProc
{

    public static final Attributes attributes = new Attributes();

    public Attributes()
    {
    }

    public static void attributes(TreeList treelist, int i, Consumer consumer)
    {
        int j = treelist.gotoAttributesStart(i);
        System.out.print((new StringBuilder()).append("Attributes called, at:").append(j).append(" ").toString());
        treelist.dump();
        do
        {
            int k;
label0:
            {
                if (j >= 0)
                {
                    k = j << 1;
                    if (treelist.getNextKind(k) == 35)
                    {
                        break label0;
                    }
                }
                return;
            }
            int l = treelist.nextDataIndex(j);
            if (consumer instanceof PositionConsumer)
            {
                ((PositionConsumer)consumer).writePosition(treelist, k);
            } else
            {
                treelist.consumeIRange(j, l, consumer);
            }
            j = l;
        } while (true);
    }

    public static void attributes(Object obj, Consumer consumer)
    {
        if (obj instanceof TreeList)
        {
            attributes((TreeList)obj, 0, consumer);
        } else
        if ((obj instanceof SeqPosition) && !(obj instanceof TreePosition))
        {
            SeqPosition seqposition = (SeqPosition)obj;
            if (seqposition.sequence instanceof TreeList)
            {
                attributes((TreeList)seqposition.sequence, seqposition.ipos >> 1, consumer);
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
                    attributes(treelist.getPosNext(i << 1), consumer);
                } else
                {
                    attributes(treelist, i, consumer);
                }
                i = treelist.nextDataIndex(i);
            } while (true);
        } else
        {
            attributes(obj, consumer);
            return;
        }
    }

    public int numArgs()
    {
        return 4097;
    }

}
