// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;


// Referenced classes of package gnu.lists:
//            ExtPosition, LList, Pair, SeqPosition, 
//            AbstractSequence

class LListPosition extends ExtPosition
{

    Object xpos;

    public LListPosition(LList llist, int i, boolean flag)
    {
        set(llist, i, flag);
    }

    public LListPosition(LListPosition llistposition)
    {
        sequence = llistposition.sequence;
        ipos = llistposition.ipos;
        xpos = llistposition.xpos;
    }

    public SeqPosition copy()
    {
        return new LListPosition(this);
    }

    public Object getNext()
    {
        Pair pair = getNextPair();
        if (pair == null)
        {
            return LList.eofValue;
        } else
        {
            return pair.car;
        }
    }

    public Pair getNextPair()
    {
        Object obj;
        if ((1 & ipos) > 0)
        {
            if (xpos == null)
            {
                obj = sequence;
                if (ipos >> 1 != 0)
                {
                    obj = ((Pair)obj).cdr;
                }
            } else
            {
                obj = ((Pair)(Pair)((Pair)xpos).cdr).cdr;
            }
        } else
        if (xpos == null)
        {
            obj = sequence;
        } else
        {
            obj = ((Pair)xpos).cdr;
        }
        if (obj == LList.Empty)
        {
            return null;
        } else
        {
            return (Pair)obj;
        }
    }

    public Object getPrevious()
    {
        Pair pair = getPreviousPair();
        if (pair == null)
        {
            return LList.eofValue;
        } else
        {
            return pair.car;
        }
    }

    public Pair getPreviousPair()
    {
        int i = 1 & ipos;
        Object obj = xpos;
        if (i > 0)
        {
            if (obj == null)
            {
                obj = sequence;
            } else
            {
                obj = ((Pair)obj).cdr;
            }
        } else
        if (obj == null)
        {
            return null;
        }
        if (obj == LList.Empty)
        {
            return null;
        } else
        {
            return (Pair)obj;
        }
    }

    public boolean gotoNext()
    {
        Object obj;
        boolean flag;
        if ((1 & ipos) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        ipos;
        obj = xpos;
        if (obj == null) goto _L2; else goto _L1
_L1:
        if (flag)
        {
            obj = ((Pair)obj).cdr;
        }
        if (((Pair)obj).cdr != LList.Empty) goto _L4; else goto _L3
_L3:
        return false;
_L4:
        xpos = obj;
        ipos = 2 + (1 | ipos);
_L6:
        return true;
_L2:
        if (ipos >> 1 != 0)
        {
            break MISSING_BLOCK_LABEL_103;
        }
        if (sequence == LList.Empty) goto _L3; else goto _L5
_L5:
        ipos = 3;
          goto _L6
        AbstractSequence abstractsequence = sequence;
        if (((Pair)abstractsequence).cdr == LList.Empty) goto _L3; else goto _L7
_L7:
        ipos = 5;
        xpos = abstractsequence;
          goto _L6
    }

    public boolean gotoPrevious()
    {
        if (ipos >>> 1 == 0)
        {
            return false;
        }
        if ((1 & ipos) != 0)
        {
            ipos = -3 + ipos;
            return true;
        } else
        {
            int i = nextIndex();
            set((LList)sequence, i - 1, false);
            return true;
        }
    }

    public boolean hasNext()
    {
        boolean flag = true;
        if (xpos != null) goto _L2; else goto _L1
_L1:
        if (ipos >> 1 != 0) goto _L4; else goto _L3
_L3:
        boolean flag1;
        if (sequence != LList.Empty)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        flag = flag1;
_L6:
        return flag;
_L4:
        if (((Pair)sequence).cdr == LList.Empty)
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        Object obj = ((Pair)xpos).cdr;
        if ((1 & ipos) > 0)
        {
            obj = ((Pair)obj).cdr;
        }
        if (obj == LList.Empty)
        {
            return false;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public boolean hasPrevious()
    {
        return ipos >>> 1 != 0;
    }

    public int nextIndex()
    {
        return ipos >> 1;
    }

    public void set(AbstractSequence abstractsequence, int i, boolean flag)
    {
        set((LList)abstractsequence, i, flag);
    }

    public void set(LList llist, int i, boolean flag)
    {
        sequence = llist;
        int j = i << 1;
        boolean flag1;
        int k;
        if (flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        ipos = flag1 | j;
        if (flag)
        {
            k = i - 2;
        } else
        {
            k = i - 1;
        }
        if (k >= 0)
        {
            Object obj;
            for (obj = llist; --k >= 0; obj = ((Pair)obj).cdr) { }
            xpos = obj;
            return;
        } else
        {
            xpos = null;
            return;
        }
    }

    public void setNext(Object obj)
    {
        getNextPair().car = obj;
    }

    public void setPrevious(Object obj)
    {
        getPreviousPair().car = obj;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("LListPos[");
        stringbuffer.append("index:");
        stringbuffer.append(ipos);
        if (isAfter())
        {
            stringbuffer.append(" after");
        }
        if (position >= 0)
        {
            stringbuffer.append(" position:");
            stringbuffer.append(position);
        }
        stringbuffer.append(']');
        return stringbuffer.toString();
    }
}
