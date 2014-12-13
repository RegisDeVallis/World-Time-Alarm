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

public class F64Vector extends SimpleVector
    implements Externalizable, Comparable
{

    protected static double empty[] = new double[0];
    double data[];

    public F64Vector()
    {
        data = empty;
    }

    public F64Vector(int i)
    {
        data = new double[i];
        size = i;
    }

    public F64Vector(int i, double d)
    {
        double ad[] = new double[i];
        data = ad;
        size = i;
        while (--i >= 0) 
        {
            ad[i] = d;
        }
    }

    public F64Vector(Sequence sequence)
    {
        data = new double[sequence.size()];
        addAll(sequence);
    }

    public F64Vector(double ad[])
    {
        data = ad;
        size = ad.length;
    }

    protected void clearBuffer(int i, int j)
    {
        int l;
        for (int k = i; --j >= 0; k = l)
        {
            double ad[] = data;
            l = k + 1;
            ad[k] = 0.0D;
        }

    }

    public int compareTo(Object obj)
    {
        F64Vector f64vector = (F64Vector)obj;
        double ad[] = data;
        double ad1[] = f64vector.data;
        int i = size;
        int j = f64vector.size;
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
            double d = ad[l];
            double d1 = ad1[l];
            if (d != d1)
            {
                return d <= d1 ? -1 : 1;
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
            consumer.writeDouble(data[j]);
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
                consumer.writeDouble(data[k]);
                k++;
            }
        }
    }

    public final double doubleAt(int i)
    {
        if (i >= size)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            return data[i];
        }
    }

    public final double doubleAtBuffer(int i)
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
        return 26;
    }

    public String getTag()
    {
        return "f64";
    }

    public final int intAtBuffer(int i)
    {
        return (int)data[i];
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        int i = objectinput.readInt();
        double ad[] = new double[i];
        for (int j = 0; j < i; j++)
        {
            ad[j] = objectinput.readDouble();
        }

        data = ad;
        size = i;
    }

    public final Object setBuffer(int i, Object obj)
    {
        Object obj1 = Convert.toObject(data[i]);
        data[i] = Convert.toDouble(obj);
        return obj1;
    }

    public void setBufferLength(int i)
    {
        int j = data.length;
        if (j != i)
        {
            double ad[] = new double[i];
            double ad1[] = data;
            if (j >= i)
            {
                j = i;
            }
            System.arraycopy(ad1, 0, ad, 0, j);
            data = ad;
        }
    }

    public final void setDoubleAt(int i, double d)
    {
        if (i > size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            data[i] = d;
            return;
        }
    }

    public final void setDoubleAtBuffer(int i, double d)
    {
        data[i] = d;
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        int i = size;
        objectoutput.writeInt(i);
        for (int j = 0; j < i; j++)
        {
            objectoutput.writeDouble(data[j]);
        }

    }

}
