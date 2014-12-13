// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;

import java.util.Collection;
import java.util.Iterator;

// Referenced classes of package gnu.lists:
//            AbstractSequence, Sequence, Array, Consumer, 
//            Convert, GeneralArray

public abstract class SimpleVector extends AbstractSequence
    implements Sequence, Array
{

    public int size;

    public SimpleVector()
    {
    }

    protected static int compareToInt(SimpleVector simplevector, SimpleVector simplevector1)
    {
        int i = simplevector.size;
        int j = simplevector1.size;
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
            int i1 = simplevector.intAtBuffer(l);
            int j1 = simplevector1.intAtBuffer(l);
            if (11 != j1)
            {
                return i1 <= j1 ? -1 : 1;
            }
        }

        return i - j;
    }

    protected static int compareToLong(SimpleVector simplevector, SimpleVector simplevector1)
    {
        int i = simplevector.size;
        int j = simplevector1.size;
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
            long l1 = simplevector.longAtBuffer(l);
            long l2 = simplevector1.longAtBuffer(l);
            if (l1 != l2)
            {
                return l1 <= l2 ? -1 : 1;
            }
        }

        return i - j;
    }

    public void add(int i, Object obj)
    {
        int j = 16;
        int k = 1 + size;
        size = k;
        int l = getBufferLength();
        if (k > l)
        {
            if (l >= j)
            {
                j = l * 2;
            }
            setBufferLength(j);
        }
        size = k;
        if (size != i)
        {
            shift(i, i + 1, size - i);
        }
        set(i, obj);
    }

    public boolean add(Object obj)
    {
        add(size, obj);
        return true;
    }

    public boolean addAll(int i, Collection collection)
    {
        boolean flag = false;
        int j = collection.size();
        setSize(j + size);
        shift(i, i + j, size - j - i);
        for (Iterator iterator = collection.iterator(); iterator.hasNext();)
        {
            int k = i + 1;
            set(i, iterator.next());
            flag = true;
            i = k;
        }

        return flag;
    }

    protected int addPos(int i, Object obj)
    {
        int j = i >>> 1;
        add(j, obj);
        return 3 + (j << 1);
    }

    public void clear()
    {
        setSize(0);
    }

    protected abstract void clearBuffer(int i, int j);

    public void consume(int i, int j, Consumer consumer)
    {
        consumePosRange(i << 1, i + j << 1, consumer);
    }

    public boolean consumeNext(int i, Consumer consumer)
    {
        int j = i >>> 1;
        if (j >= size)
        {
            return false;
        } else
        {
            consumer.writeObject(getBuffer(j));
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
                consumer.writeObject(getBuffer(k));
                k++;
            }
        }
    }

    public int createPos(int i, boolean flag)
    {
        int j = i << 1;
        boolean flag1;
        if (flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        return flag1 | j;
    }

    public void fill(int i, int j, Object obj)
    {
        if (i < 0 || j > size)
        {
            throw new IndexOutOfBoundsException();
        }
        for (int k = i; k < j; k++)
        {
            setBuffer(k, obj);
        }

    }

    public void fill(Object obj)
    {
        for (int i = size; --i >= 0;)
        {
            setBuffer(i, obj);
        }

    }

    public void fillPosRange(int i, int j, Object obj)
    {
        int k;
        int l;
        if (i == -1)
        {
            k = size;
        } else
        {
            k = i >>> 1;
        }
        if (j == -1)
        {
            l = size;
        } else
        {
            l = j >>> 1;
        }
        for (; k < l; k++)
        {
            setBuffer(k, obj);
        }

    }

    public Object get(int i)
    {
        if (i >= size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            return getBuffer(i);
        }
    }

    protected abstract Object getBuffer();

    protected abstract Object getBuffer(int i);

    public abstract int getBufferLength();

    public int getElementKind()
    {
        return 32;
    }

    public int getNextKind(int i)
    {
        if (hasNext(i))
        {
            return getElementKind();
        } else
        {
            return 0;
        }
    }

    public Object getPosNext(int i)
    {
        int j = i >>> 1;
        if (j >= size)
        {
            return eofValue;
        } else
        {
            return getBuffer(j);
        }
    }

    public Object getRowMajor(int i)
    {
        return get(i);
    }

    public String getTag()
    {
        return null;
    }

    public int intAt(int i)
    {
        if (i >= size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            return intAtBuffer(i);
        }
    }

    public int intAtBuffer(int i)
    {
        return Convert.toInt(getBuffer(i));
    }

    protected boolean isAfterPos(int i)
    {
        return (i & 1) != 0;
    }

    public long longAt(int i)
    {
        if (i >= size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            return longAtBuffer(i);
        }
    }

    public long longAtBuffer(int i)
    {
        return Convert.toLong(getBuffer(i));
    }

    protected int nextIndex(int i)
    {
        if (i == -1)
        {
            return size;
        } else
        {
            return i >>> 1;
        }
    }

    public int nextPos(int i)
    {
        int j;
        if (i != -1)
        {
            if ((j = i >>> 1) != size)
            {
                return 3 + (j << 1);
            }
        }
        return 0;
    }

    public Object remove(int i)
    {
        if (i < 0 || i >= size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            Object obj = get(i);
            shift(i + 1, i, 1);
            size = -1 + size;
            clearBuffer(size, 1);
            return obj;
        }
    }

    public boolean remove(Object obj)
    {
        int i = indexOf(obj);
        if (i < 0)
        {
            return false;
        } else
        {
            shift(i + 1, i, 1);
            size = -1 + size;
            clearBuffer(size, 1);
            return true;
        }
    }

    public boolean removeAll(Collection collection)
    {
        boolean flag = false;
        int i = 0;
        int j = 0;
        while (j < size) 
        {
            Object obj = get(j);
            if (collection.contains(obj))
            {
                flag = true;
            } else
            {
                if (flag)
                {
                    set(i, obj);
                }
                i++;
            }
            j++;
        }
        setSize(i);
        return flag;
    }

    public void removePos(int i, int j)
    {
        int k = i >>> 1;
        if (k > size)
        {
            k = size;
        }
        int l;
        int i1;
        if (j >= 0)
        {
            l = k;
            i1 = k + j;
        } else
        {
            l = k + j;
            i1 = k;
            j = -j;
        }
        if (l < 0 || i1 >= size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            shift(i1, l, size - i1);
            size = size - j;
            clearBuffer(size, j);
            return;
        }
    }

    protected void removePosRange(int i, int j)
    {
        int k = i >>> 1;
        int l = j >>> 1;
        if (k >= l)
        {
            return;
        }
        if (l > size)
        {
            l = size;
        }
        shift(l, k, size - l);
        int i1 = l - k;
        size = size - i1;
        clearBuffer(size, i1);
    }

    protected void resizeShift(int i, int j, int k, int l)
    {
        int i1 = j - i;
        int j1 = l - k;
        int k1 = getBufferLength();
        int l1 = j1 + (k1 - i1);
        if (l1 > k1)
        {
            setBufferLength(l1);
            size = l1;
        }
        int i2 = i - k;
        if (i2 >= 0)
        {
            int k2 = k1 - j;
            shift(j, l1 - k2, k2);
            if (i2 > 0)
            {
                shift(k, l, i2);
            }
        } else
        {
            int j2 = l1 - l;
            shift(k1 - j2, l, j2);
            shift(j, i, k - i);
        }
        clearBuffer(k, j1);
    }

    public boolean retainAll(Collection collection)
    {
        boolean flag = false;
        int i = 0;
        int j = 0;
        while (j < size) 
        {
            Object obj = get(j);
            if (!collection.contains(obj))
            {
                flag = true;
            } else
            {
                if (flag)
                {
                    set(i, obj);
                }
                i++;
            }
            j++;
        }
        setSize(i);
        return flag;
    }

    public Object set(int i, Object obj)
    {
        if (i >= size)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            Object obj1 = getBuffer(i);
            setBuffer(i, obj);
            return obj1;
        }
    }

    protected abstract Object setBuffer(int i, Object obj);

    public abstract void setBufferLength(int i);

    public void setSize(int i)
    {
        int j = 16;
        int k = size;
        size = i;
        if (i < k)
        {
            clearBuffer(i, k - i);
        } else
        {
            int l = getBufferLength();
            if (i > l)
            {
                if (l >= j)
                {
                    j = l * 2;
                }
                if (i <= j)
                {
                    i = j;
                }
                setBufferLength(i);
                return;
            }
        }
    }

    public void shift(int i, int j, int k)
    {
        Object obj = getBuffer();
        System.arraycopy(obj, i, obj, j, k);
    }

    public final int size()
    {
        return size;
    }

    public Array transpose(int ai[], int ai1[], int i, int ai2[])
    {
        GeneralArray generalarray = new GeneralArray();
        generalarray.strides = ai2;
        generalarray.dimensions = ai1;
        generalarray.lowBounds = ai;
        generalarray.offset = i;
        generalarray.base = this;
        generalarray.simple = false;
        return generalarray;
    }
}
