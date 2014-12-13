// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;


// Referenced classes of package gnu.lists:
//            SeqPosition, ExtPosition

public class PositionManager
{

    static final PositionManager manager = new PositionManager();
    int freeListHead;
    int ivals[];
    SeqPosition positions[];

    public PositionManager()
    {
        positions = new SeqPosition[50];
        ivals = new int[50];
        freeListHead = -1;
        addToFreeList(ivals, 1, ivals.length);
    }

    private void addToFreeList(int ai[], int i, int j)
    {
        int k = freeListHead;
        for (int l = i; l < j; l++)
        {
            ai[l] = k;
            k = l;
        }

        freeListHead = k;
    }

    private int getFreeSlot()
    {
        int i = freeListHead;
        if (i < 0)
        {
            int j = positions.length;
            SeqPosition aseqposition[] = new SeqPosition[j * 2];
            int ai[] = new int[j * 2];
            System.arraycopy(positions, 0, aseqposition, 0, j);
            System.arraycopy(ivals, 0, ai, 0, j);
            positions = aseqposition;
            ivals = ai;
            addToFreeList(ai, j, j * 2);
            i = freeListHead;
        }
        freeListHead = ivals[i];
        return i;
    }

    public static SeqPosition getPositionObject(int i)
    {
        SeqPosition seqposition;
        synchronized (manager)
        {
            seqposition = positionmanager.positions[i];
        }
        return seqposition;
        exception;
        positionmanager;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int register(SeqPosition seqposition)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        i = getFreeSlot();
        positions[i] = seqposition;
        ivals[i] = -1;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public void release(int i)
    {
        this;
        JVM INSTR monitorenter ;
        SeqPosition seqposition = positions[i];
        if (seqposition instanceof ExtPosition)
        {
            ((ExtPosition)seqposition).position = -1;
        }
        positions[i] = null;
        ivals[i] = freeListHead;
        freeListHead = i;
        seqposition.release();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

}
