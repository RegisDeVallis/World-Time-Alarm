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

public class F32Vector extends SimpleVector
    implements Externalizable, Comparable
{

    protected static float empty[] = new float[0];
    float data[];

    public F32Vector()
    {
        data = empty;
    }

    public F32Vector(int i)
    {
        data = new float[i];
        size = i;
    }

    public F32Vector(int i, float f)
    {
        float af[] = new float[i];
        data = af;
        size = i;
        while (--i >= 0) 
        {
            af[i] = f;
        }
    }

    public F32Vector(Sequence sequence)
    {
        data = new float[sequence.size()];
        addAll(sequence);
    }

    public F32Vector(float af[])
    {
        data = af;
        size = af.length;
    }

    protected void clearBuffer(int i, int j)
    {
        int l;
        for (int k = i; --j >= 0; k = l)
        {
            float af[] = data;
            l = k + 1;
            af[k] = 0.0F;
        }

    }

    public int compareTo(Object obj)
    {
        F32Vector f32vector = (F32Vector)obj;
        float af[] = data;
        float af1[] = f32vector.data;
        int i = size;
        int j = f32vector.size;
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
            float f = af[l];
            float f1 = af1[l];
            if (f != f1)
            {
                return f <= f1 ? -1 : 1;
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
            consumer.writeFloat(data[j]);
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
                consumer.writeFloat(data[k]);
                k++;
            }
        }
    }

    public final float floatAt(int i)
    {
        if (i >= size)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            return data[i];
        }
    }

    public final float floatAtBuffer(int i)
    {
        return data[i];
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
        return 25;
    }

    public String getTag()
    {
        return "f32";
    }

    public final int intAtBuffer(int i)
    {
        return (int)data[i];
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        int i = objectinput.readInt();
        float af[] = new float[i];
        for (int j = 0; j < i; j++)
        {
            af[j] = objectinput.readFloat();
        }

        data = af;
        size = i;
    }

    public final Object setBuffer(int i, Object obj)
    {
        Object obj1 = Convert.toObject(data[i]);
        data[i] = Convert.toFloat(obj);
        return obj1;
    }

    public void setBufferLength(int i)
    {
        int j = data.length;
        if (j != i)
        {
            float af[] = new float[i];
            float af1[] = data;
            if (j >= i)
            {
                j = i;
            }
            System.arraycopy(af1, 0, af, 0, j);
            data = af;
        }
    }

    public final void setFloatAt(int i, float f)
    {
        if (i > size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            data[i] = f;
            return;
        }
    }

    public final void setFloatAtBuffer(int i, float f)
    {
        data[i] = f;
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        int i = size;
        objectoutput.writeInt(i);
        for (int j = 0; j < i; j++)
        {
            objectoutput.writeFloat(data[j]);
        }

    }

}
