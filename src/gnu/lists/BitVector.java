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

public class BitVector extends SimpleVector
    implements Externalizable
{

    protected static boolean empty[] = new boolean[0];
    boolean data[];

    public BitVector()
    {
        data = empty;
    }

    public BitVector(int i)
    {
        data = new boolean[i];
        size = i;
    }

    public BitVector(int i, boolean flag)
    {
        boolean aflag[] = new boolean[i];
        data = aflag;
        size = i;
        if (flag)
        {
            while (--i >= 0) 
            {
                aflag[i] = true;
            }
        }
    }

    public BitVector(Sequence sequence)
    {
        data = new boolean[sequence.size()];
        addAll(sequence);
    }

    public BitVector(boolean aflag[])
    {
        data = aflag;
        size = aflag.length;
    }

    public final boolean booleanAt(int i)
    {
        if (i > size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            return data[i];
        }
    }

    public final boolean booleanAtBuffer(int i)
    {
        return data[i];
    }

    protected void clearBuffer(int i, int j)
    {
        int l;
        for (int k = i; --j >= 0; k = l)
        {
            boolean aflag[] = data;
            l = k + 1;
            aflag[k] = false;
        }

    }

    public boolean consumeNext(int i, Consumer consumer)
    {
        int j = i >>> 1;
        if (j >= size)
        {
            return false;
        } else
        {
            consumer.writeBoolean(data[j]);
            return true;
        }
    }

    public void consumePosRange(int i, int j, Consumer consumer)
    {
        if (!consumer.ignoring())
        {
            int k = i >>> 1;
            int l = j >>> 1;
            while (k < l) 
            {
                consumer.writeBoolean(data[k]);
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
        return 27;
    }

    public String getTag()
    {
        return "b";
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        int i = objectinput.readInt();
        boolean aflag[] = new boolean[i];
        for (int j = 0; j < i; j++)
        {
            aflag[j] = objectinput.readBoolean();
        }

        data = aflag;
        size = i;
    }

    public final void setBooleanAt(int i, boolean flag)
    {
        if (i > size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            data[i] = flag;
            return;
        }
    }

    public final void setBooleanAtBuffer(int i, boolean flag)
    {
        data[i] = flag;
    }

    public Object setBuffer(int i, Object obj)
    {
        boolean flag = data[i];
        data[i] = Convert.toBoolean(obj);
        return Convert.toObject(flag);
    }

    public void setBufferLength(int i)
    {
        int j = data.length;
        if (j != i)
        {
            boolean aflag[] = new boolean[i];
            boolean aflag1[] = data;
            if (j >= i)
            {
                j = i;
            }
            System.arraycopy(aflag1, 0, aflag, 0, j);
            data = aflag;
        }
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        int i = size;
        objectoutput.writeInt(i);
        for (int j = 0; j < i; j++)
        {
            objectoutput.writeBoolean(data[j]);
        }

    }

}
