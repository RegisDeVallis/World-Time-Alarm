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

public class S16Vector extends SimpleVector
    implements Externalizable, Comparable
{

    protected static short empty[] = new short[0];
    short data[];

    public S16Vector()
    {
        data = empty;
    }

    public S16Vector(int i)
    {
        data = new short[i];
        size = i;
    }

    public S16Vector(int i, short word0)
    {
        short aword0[] = new short[i];
        data = aword0;
        size = i;
        while (--i >= 0) 
        {
            aword0[i] = word0;
        }
    }

    public S16Vector(Sequence sequence)
    {
        data = new short[sequence.size()];
        addAll(sequence);
    }

    public S16Vector(short aword0[])
    {
        data = aword0;
        size = aword0.length;
    }

    protected void clearBuffer(int i, int j)
    {
        int l;
        for (int k = i; --j >= 0; k = l)
        {
            short aword0[] = data;
            l = k + 1;
            aword0[k] = 0;
        }

    }

    public int compareTo(Object obj)
    {
        return compareToInt(this, (S16Vector)obj);
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
        return 20;
    }

    public String getTag()
    {
        return "s16";
    }

    public final int intAtBuffer(int i)
    {
        return data[i];
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        int i = objectinput.readInt();
        short aword0[] = new short[i];
        for (int j = 0; j < i; j++)
        {
            aword0[j] = objectinput.readShort();
        }

        data = aword0;
        size = i;
    }

    public Object setBuffer(int i, Object obj)
    {
        short word0 = data[i];
        data[i] = Convert.toShort(obj);
        return Convert.toObject(word0);
    }

    public void setBufferLength(int i)
    {
        int j = data.length;
        if (j != i)
        {
            short aword0[] = new short[i];
            short aword1[] = data;
            if (j >= i)
            {
                j = i;
            }
            System.arraycopy(aword1, 0, aword0, 0, j);
            data = aword0;
        }
    }

    public final void setShortAt(int i, short word0)
    {
        if (i > size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            data[i] = word0;
            return;
        }
    }

    public final void setShortAtBuffer(int i, short word0)
    {
        data[i] = word0;
    }

    public final short shortAt(int i)
    {
        if (i > size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            return data[i];
        }
    }

    public final short shortAtBuffer(int i)
    {
        return data[i];
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        int i = size;
        objectoutput.writeInt(i);
        for (int j = 0; j < i; j++)
        {
            objectoutput.writeShort(data[j]);
        }

    }

}
