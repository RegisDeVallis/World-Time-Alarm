// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;

import java.io.PrintStream;

// Referenced classes of package gnu.lists:
//            SeqPosition, AbstractSequence

public class TreePosition extends SeqPosition
    implements Cloneable
{

    int depth;
    int istack[];
    AbstractSequence sstack[];
    int start;
    private Object xpos;

    public TreePosition()
    {
        depth = -1;
    }

    public TreePosition(AbstractSequence abstractsequence, int i)
    {
        super(abstractsequence, i, false);
    }

    public TreePosition(TreePosition treeposition)
    {
        set(treeposition);
    }

    public TreePosition(Object obj)
    {
        xpos = obj;
        depth = -1;
    }

    public Object clone()
    {
        return new TreePosition(this);
    }

    public void dump()
    {
        System.err.println((new StringBuilder()).append("TreePosition dump depth:").append(depth).append(" start:").append(start).toString());
        int i = 0;
        while (i <= depth) 
        {
            AbstractSequence abstractsequence;
            PrintStream printstream;
            StringBuilder stringbuilder;
            int j;
            if (i == 0)
            {
                abstractsequence = sequence;
            } else
            {
                abstractsequence = sstack[depth - i];
            }
            System.err.print((new StringBuilder()).append("#").append(i).append(" seq:").append(abstractsequence).toString());
            printstream = System.err;
            stringbuilder = (new StringBuilder()).append(" ipos:");
            if (i == 0)
            {
                j = ipos;
            } else
            {
                j = istack[depth - i];
            }
            printstream.println(stringbuilder.append(j).toString());
            i++;
        }
    }

    public Object getAncestor(int i)
    {
        if (i == 0)
        {
            return sequence.getPosNext(ipos);
        }
        int j = depth - i;
        if (j <= 0)
        {
            return getRoot();
        } else
        {
            int k = j + start;
            return sstack[k].getPosNext(istack[k]);
        }
    }

    public int getDepth()
    {
        return 1 + depth;
    }

    public Object getPosNext()
    {
        if (sequence == null)
        {
            return xpos;
        } else
        {
            return sequence.getPosNext(ipos);
        }
    }

    public AbstractSequence getRoot()
    {
        if (depth == 0)
        {
            return sequence;
        } else
        {
            return sstack[start];
        }
    }

    public boolean gotoAttributesStart()
    {
        if (sequence == null)
        {
            if (!(xpos instanceof AbstractSequence));
            return false;
        } else
        {
            return sequence.gotoAttributesStart(this);
        }
    }

    public boolean gotoChildrenStart()
    {
        if (sequence == null)
        {
            if (!(xpos instanceof AbstractSequence))
            {
                return false;
            }
            depth = 0;
            sequence = (AbstractSequence)xpos;
            setPos(sequence.startPos());
        } else
        if (!sequence.gotoChildrenStart(this))
        {
            return false;
        }
        return true;
    }

    public final boolean gotoParent()
    {
        if (sequence == null)
        {
            return false;
        } else
        {
            return sequence.gotoParent(this);
        }
    }

    public void pop()
    {
        sequence.releasePos(ipos);
        popNoRelease();
    }

    public void popNoRelease()
    {
        int i = -1 + depth;
        depth = i;
        if (i < 0)
        {
            xpos = sequence;
            sequence = null;
            return;
        } else
        {
            sequence = sstack[start + depth];
            ipos = istack[start + depth];
            return;
        }
    }

    public void push(AbstractSequence abstractsequence, int i)
    {
        int j = depth + start;
        if (j < 0) goto _L2; else goto _L1
_L1:
        if (j != 0) goto _L4; else goto _L3
_L3:
        istack = new int[8];
        sstack = new AbstractSequence[8];
_L6:
        sstack[j] = sequence;
        istack[j] = ipos;
_L2:
        depth = 1 + depth;
        sequence = abstractsequence;
        ipos = i;
        return;
_L4:
        if (j >= istack.length)
        {
            int k = j * 2;
            int ai[] = new int[k];
            new Object[k];
            AbstractSequence aabstractsequence[] = new AbstractSequence[k];
            System.arraycopy(istack, 0, ai, 0, depth);
            System.arraycopy(sstack, 0, aabstractsequence, 0, depth);
            istack = ai;
            sstack = aabstractsequence;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void release()
    {
        while (sequence != null) 
        {
            sequence.releasePos(ipos);
            pop();
        }
        xpos = null;
    }

    public void set(TreePosition treeposition)
    {
        release();
        int i = treeposition.depth;
        depth = i;
        if (i < 0)
        {
            xpos = treeposition.xpos;
            return;
        }
        if (sstack == null || sstack.length <= i)
        {
            sstack = new AbstractSequence[i + 10];
        }
        if (istack == null || istack.length <= i)
        {
            istack = new int[i + 10];
        }
        for (int j = 0; j < depth; j++)
        {
            int k = j + treeposition.start;
            AbstractSequence abstractsequence1 = treeposition.sstack[k];
            sstack[-1 + depth] = abstractsequence1;
            istack[depth - j] = abstractsequence1.copyPos(treeposition.istack[k]);
        }

        AbstractSequence abstractsequence = treeposition.sequence;
        sequence = abstractsequence;
        ipos = abstractsequence.copyPos(treeposition.ipos);
    }
}
