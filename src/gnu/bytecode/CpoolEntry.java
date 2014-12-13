// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package gnu.bytecode:
//            ConstantPool, ClassTypeWriter

public abstract class CpoolEntry
{

    int hash;
    public int index;
    CpoolEntry next;

    protected CpoolEntry()
    {
    }

    public CpoolEntry(ConstantPool constantpool, int i)
    {
        hash = i;
        if (constantpool.locked)
        {
            throw new Error("adding new entry to locked contant pool");
        }
        int j = 1 + constantpool.count;
        constantpool.count = j;
        index = j;
        if (constantpool.pool != null) goto _L2; else goto _L1
_L1:
        constantpool.pool = new CpoolEntry[60];
_L4:
        if (constantpool.hashTab == null || (double)index >= 0.59999999999999998D * (double)constantpool.hashTab.length)
        {
            constantpool.rehash();
        }
        constantpool.pool[index] = this;
        add_hashed(constantpool);
        return;
_L2:
        if (index >= constantpool.pool.length)
        {
            int k = constantpool.pool.length;
            CpoolEntry acpoolentry[] = new CpoolEntry[2 * constantpool.pool.length];
            for (int l = 0; l < k; l++)
            {
                acpoolentry[l] = constantpool.pool[l];
            }

            constantpool.pool = acpoolentry;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    void add_hashed(ConstantPool constantpool)
    {
        CpoolEntry acpoolentry[] = constantpool.hashTab;
        int i = (0x7fffffff & hash) % acpoolentry.length;
        next = acpoolentry[i];
        acpoolentry[i] = this;
    }

    public int getIndex()
    {
        return index;
    }

    public abstract int getTag();

    public int hashCode()
    {
        return hash;
    }

    public abstract void print(ClassTypeWriter classtypewriter, int i);

    abstract void write(DataOutputStream dataoutputstream)
        throws IOException;
}
