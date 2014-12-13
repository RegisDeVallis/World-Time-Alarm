// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.SeqPosition;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import gnu.mapping.WrongType;

public class NodeCompare extends Procedure2
{

    public static final NodeCompare $Eq = make("is", 8);
    public static final NodeCompare $Gr = make(">>", 16);
    public static final NodeCompare $Ls = make("<<", 4);
    public static final NodeCompare $Ne = make("isnot", 20);
    static final int RESULT_EQU = 0;
    static final int RESULT_GRT = 1;
    static final int RESULT_LSS = -1;
    static final int TRUE_IF_EQU = 8;
    static final int TRUE_IF_GRT = 16;
    static final int TRUE_IF_LSS = 4;
    int flags;

    public NodeCompare()
    {
    }

    public static NodeCompare make(String s, int i)
    {
        NodeCompare nodecompare = new NodeCompare();
        nodecompare.setName(s);
        nodecompare.flags = i;
        return nodecompare;
    }

    public Object apply2(Object obj, Object obj1)
    {
        if (obj == null || obj1 == null)
        {
            obj = null;
        } else
        if (obj != Values.empty)
        {
            if (obj1 == Values.empty)
            {
                return obj1;
            }
            AbstractSequence abstractsequence;
            int j;
            AbstractSequence abstractsequence1;
            int l;
            int i1;
            if (obj instanceof AbstractSequence)
            {
                abstractsequence = (AbstractSequence)obj;
                j = abstractsequence.startPos();
            } else
            {
                int i;
                try
                {
                    SeqPosition seqposition = (SeqPosition)obj;
                    abstractsequence = seqposition.sequence;
                    i = seqposition.getPos();
                }
                catch (ClassCastException classcastexception)
                {
                    throw WrongType.make(classcastexception, this, 1, obj);
                }
                j = i;
            }
            if (obj1 instanceof AbstractSequence)
            {
                abstractsequence1 = (AbstractSequence)obj1;
                l = abstractsequence1.startPos();
            } else
            {
                int k;
                try
                {
                    SeqPosition seqposition1 = (SeqPosition)obj1;
                    abstractsequence1 = seqposition1.sequence;
                    k = seqposition1.getPos();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw WrongType.make(classcastexception1, this, 2, obj1);
                }
                l = k;
            }
            if (abstractsequence == abstractsequence1)
            {
                i1 = abstractsequence.compare(j, l);
            } else
            {
                if (this == $Eq)
                {
                    return Boolean.FALSE;
                }
                if (this == $Ne)
                {
                    return Boolean.TRUE;
                }
                i1 = abstractsequence.stableCompare(abstractsequence1);
            }
            if ((1 << i1 + 3 & flags) != 0)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        return obj;
    }

}
