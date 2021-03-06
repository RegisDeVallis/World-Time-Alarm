// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Referenced classes of package gnu.lists:
//            SimpleVector, Sequence, Consumer, Convert

public class S32Vector extends SimpleVector
    implements Externalizable, Comparable
{

    protected static int empty[] = new int[0];
    int data[];

    public S32Vector()
    {
        data = empty;
    }

    public S32Vector(int i)
    {
        data = new int[i];
        size = i;
    }

    public S32Vector(int i, int j)
    {
        int ai[] = new int[i];
        data = ai;
        size = i;
        while (--i >= 0) 
        {
            ai[i] = j;
        }
    }

    public S32Vector(Sequence sequence)
    {
        data = new int[sequence.size()];
        addAll(sequence);
    }

    public S32Vector(int ai[])
    {
        data = ai;
        size = ai.length;
    }

    protected void clearBuffer(int i, int j)
    {
        int l;
        for (int k = i; --j >= 0; k = l)
        {
            int ai[] = data;
            l = k + 1;
            ai[k] = 0;
        }

    }

    public int compareTo(Object obj)
    {
        return compareToInt(this, (S32Vector)obj);
    }

    public boolean consumeNext(int i, Consumer consumer)
    {
        int j = i >>> 1;
        if (j >= size)
        {
            return false;
        } else
        {
            consumer.writeInt(data[j]);
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
                consumer.writeInt(data[k]);
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
            return Convert.toObject(data[i]);
        }
    }

    protected Object getBuffer()
    {
        return data;
    }

    public final Object getBuffer(int i)
    {
        return Convert.toObject(data[i]);
    }

    public int getBufferLength()
    {
        return data.length;
    }

    public int getElementKind()
    {
        return 22;
    }

    public String getTag()
    {
        return "s32";
    }

    public final int intAt(int i)
    {
        if (i > size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            return data[i];
        }
    }

    public final int intAtBuffer(int i)
    {
        return data[i];
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        int i = objectinput.readInt();
        int ai[] = new int[i];
        for (int j = 0; j < i; j++)
        {
            ai[j] = objectinput.readInt();
        }

        data = ai;
        size = i;
    }

    public Object setBuffer(int i, Object obj)
    {
        int j = data[i];
        data[i] = Convert.toInt(obj);
        return Convert.toObject(j);
    }

    public void setBufferLength(int i)
    {
        int j = data.length;
        if (j != i)
        {
            int ai[] = new int[i];
            int ai1[] = data;
            if (j >= i)
            {
                j = i;
            }
            System.arraycopy(ai1, 0, ai, 0, j);
            data = ai;
        }
    }

    public final void setIntAt(int i, int j)
    {
        if (i > size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            data[i] = j;
            return;
        }
    }

    public final void setIntAtBuffer(int i, int j)
    {
        data[i] = j;
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        int i = size;
        objectoutput.writeInt(i);
        for (int j = 0; j < i; j++)
        {
            objectoutput.writeInt(data[j]);
        }

    }

}
