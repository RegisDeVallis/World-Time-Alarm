// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

// Referenced classes of package gnu.lists:
//            SimpleVector, Consumable, Consumer

public class FVector extends SimpleVector
    implements Externalizable, Consumable, Comparable
{

    protected static Object empty[] = new Object[0];
    public Object data[];

    public FVector()
    {
        data = empty;
    }

    public FVector(int i)
    {
        size = i;
        data = new Object[i];
    }

    public FVector(int i, Object obj)
    {
        Object aobj[] = new Object[i];
        if (obj != null)
        {
            for (int j = 0; j < i; j++)
            {
                aobj[j] = obj;
            }

        }
        data = aobj;
        size = i;
    }

    public FVector(List list)
    {
        data = new Object[list.size()];
        addAll(list);
    }

    public FVector(Object aobj[])
    {
        size = aobj.length;
        data = aobj;
    }

    public static transient FVector make(Object aobj[])
    {
        return new FVector(aobj);
    }

    protected void clearBuffer(int i, int j)
    {
        Object aobj[] = data;
        int l;
        for (int k = i; --j >= 0; k = l)
        {
            l = k + 1;
            aobj[k] = null;
        }

    }

    public int compareTo(Object obj)
    {
        FVector fvector = (FVector)obj;
        Object aobj[] = data;
        Object aobj1[] = fvector.data;
        int i = size;
        int j = fvector.size;
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
            int i1 = ((Comparable)aobj[l]).compareTo((Comparable)aobj1[l]);
            if (i1 != 0)
            {
                return i1;
            }
        }

        return i - j;
    }

    public void consume(Consumer consumer)
    {
        consumer.startElement("#vector");
        int i = size;
        for (int j = 0; j < i; j++)
        {
            consumer.writeObject(data[j]);
        }

        consumer.endElement();
    }

    public boolean consumeNext(int i, Consumer consumer)
    {
        int j = i >>> 1;
        if (j >= size)
        {
            return false;
        } else
        {
            consumer.writeObject(data[j]);
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
                consumer.writeObject(data[k]);
                k++;
            }
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != null && (obj instanceof FVector)) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        FVector fvector = (FVector)obj;
        int i = size;
        if (fvector.data != null && fvector.size == i)
        {
            Object aobj[] = data;
            Object aobj1[] = fvector.data;
            int j = 0;
label0:
            do
            {
label1:
                {
                    if (j >= i)
                    {
                        break label1;
                    }
                    if (!aobj[j].equals(aobj1[j]))
                    {
                        break label0;
                    }
                    j++;
                }
            } while (true);
        }
        if (true) goto _L1; else goto _L3
_L3:
        return true;
    }

    public final Object get(int i)
    {
        if (i >= size)
        {
            throw new ArrayIndexOutOfBoundsException();
        } else
        {
            return data[i];
        }
    }

    protected Object getBuffer()
    {
        return ((Object) (data));
    }

    public final Object getBuffer(int i)
    {
        return data[i];
    }

    public int getBufferLength()
    {
        return data.length;
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        int i = objectinput.readInt();
        Object aobj[] = new Object[i];
        for (int j = 0; j < i; j++)
        {
            aobj[j] = objectinput.readObject();
        }

        size = i;
        data = aobj;
    }

    public final void setAll(Object obj)
    {
        Object aobj[] = data;
        for (int i = size; --i >= 0;)
        {
            aobj[i] = obj;
        }

    }

    public final Object setBuffer(int i, Object obj)
    {
        Object obj1 = data[i];
        data[i] = obj;
        return obj1;
    }

    public void setBufferLength(int i)
    {
        int j = data.length;
        if (j != i)
        {
            Object aobj[] = new Object[i];
            Object aobj1[] = data;
            if (j >= i)
            {
                j = i;
            }
            System.arraycopy(((Object) (aobj1)), 0, ((Object) (aobj)), 0, j);
            data = aobj;
        }
    }

    public void shift(int i, int j, int k)
    {
        System.arraycopy(((Object) (data)), i, ((Object) (data)), j, k);
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        int i = size;
        objectoutput.writeInt(i);
        for (int j = 0; j < i; j++)
        {
            objectoutput.writeObject(data[j]);
        }

    }

}
