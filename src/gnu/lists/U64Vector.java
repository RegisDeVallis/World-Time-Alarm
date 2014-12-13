// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Referenced classes of package gnu.lists:
//            SimpleVector, S64Vector, Sequence, Consumer, 
//            Convert

public class U64Vector extends SimpleVector
    implements Externalizable, Comparable
{

    long data[];

    public U64Vector()
    {
        data = S64Vector.empty;
    }

    public U64Vector(int i)
    {
        data = new long[i];
        size = i;
    }

    public U64Vector(int i, long l)
    {
        long al[] = new long[i];
        data = al;
        size = i;
        while (--i >= 0) 
        {
            al[i] = l;
        }
    }

    public U64Vector(Sequence sequence)
    {
        data = new long[sequence.size()];
        addAll(sequence);
    }

    public U64Vector(long al[])
    {
        data = al;
        size = al.length;
    }

    protected void clearBuffer(int i, int j)
    {
        int l;
        for (int k = i; --j >= 0; k = l)
        {
            long al[] = data;
            l = k + 1;
            al[k] = 0L;
        }

    }

    public int compareTo(Object obj)
    {
        U64Vector u64vector = (U64Vector)obj;
        long al[] = data;
        long al1[] = u64vector.data;
        int i = size;
        int j = u64vector.size;
        int k;
        int l;
        if (i > j)
        {
            k = j;
        } else
        {
            k = i;
        }
        for (l = 0; l < k; l++)
        {
            long l1 = al[l];
            long l2 = al1[l];
            if (l1 != l2)
            {
                return (0x8000000000000000L ^ l1) <= (0x8000000000000000L ^ l2) ? -1 : 1;
            }
        }

        return i - j;
    }

    public boolean consumeNext(int i, Consumer consumer)
    {
        int j = i >>> 1;
        if (j >= size)
        {
            return false;
        } else
        {
            consumer.writeLong(data[j]);
            return true;
        }
    }

    public void consumePosRange(int i, int j, Consumer consumer)
    {
        if (!consumer.ignoring())
        {
            int k = i >>> 1;
            int l = j >>> 1;
            if (l > size)
            {
                l = size;
            }
            while (k < l) 
            {
                consumer.writeLong(data[k]);
                k++;
            }
        }
    }

    public final Object get(int i)
    {
        if (i > size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            return Convert.toObjectUnsigned(data[i]);
        }
    }

    protected Object getBuffer()
    {
        return data;
    }

    public final Object getBuffer(int i)
    {
        return Convert.toObjectUnsigned(data[i]);
    }

    public int getBufferLength()
    {
        return data.length;
    }

    public int getElementKind()
    {
        return 23;
    }

    public String getTag()
    {
        return "u64";
    }

    public final int intAtBuffer(int i)
    {
        return (int)data[i];
    }

    public final long longAt(int i)
    {
        if (i > size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            return data[i];
        }
    }

    public final long longAtBuffer(int i)
    {
        return data[i];
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        int i = objectinput.readInt();
        long al[] = new long[i];
        for (int j = 0; j < i; j++)
        {
            al[j] = objectinput.readLong();
        }

        data = al;
        size = i;
    }

    public Object setBuffer(int i, Object obj)
    {
        long l = data[i];
        data[i] = Convert.toLongUnsigned(obj);
        return Convert.toObjectUnsigned(l);
    }

    public void setBufferLength(int i)
    {
        int j = data.length;
        if (j != i)
        {
            long al[] = new long[i];
            long al1[] = data;
            if (j >= i)
            {
                j = i;
            }
            System.arraycopy(al1, 0, al, 0, j);
            data = al;
        }
    }

    public final void setLongAt(int i, long l)
    {
        if (i > size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            data[i] = l;
            return;
        }
    }

    public final void setLongAtBuffer(int i, long l)
    {
        data[i] = l;
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        int i = size;
        objectoutput.writeInt(i);
        for (int j = 0; j < i; j++)
        {
            objectoutput.writeLong(data[j]);
        }

    }
}
