// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;


// Referenced classes of package gnu.lists:
//            AbstractSequence, Array, FVector, SimpleVector, 
//            GeneralArray1

public class GeneralArray extends AbstractSequence
    implements Array
{

    static final int zeros[] = new int[8];
    SimpleVector base;
    int dimensions[];
    int lowBounds[];
    int offset;
    boolean simple;
    int strides[];

    public GeneralArray()
    {
        simple = true;
    }

    public GeneralArray(int ai[])
    {
        simple = true;
        int i = 1;
        int j = ai.length;
        int ai1[];
        if (j <= zeros.length)
        {
            lowBounds = zeros;
        } else
        {
            lowBounds = new int[j];
        }
        ai1 = new int[j];
        for (int k = j; --k >= 0;)
        {
            ai1[k] = i;
            i *= ai[k];
        }

        base = new FVector(i);
        dimensions = ai;
        offset = 0;
    }

    public static Array makeSimple(int ai[], int ai1[], SimpleVector simplevector)
    {
        int i = ai1.length;
        if (ai == null)
        {
            ai = zeros;
            if (i > ai.length)
            {
                ai = new int[i];
            }
        }
        if (i == 1 && ai[0] == 0)
        {
            return simplevector;
        }
        GeneralArray generalarray = new GeneralArray();
        int ai2[] = new int[i];
        int j = 1;
        for (int k = i; --k >= 0;)
        {
            ai2[k] = j;
            j *= ai1[k];
        }

        generalarray.strides = ai2;
        generalarray.dimensions = ai1;
        generalarray.lowBounds = ai;
        generalarray.base = simplevector;
        return generalarray;
    }

    public static void toString(Array array, StringBuffer stringbuffer)
    {
        stringbuffer.append("#<array");
        int i = array.rank();
        for (int j = 0; j < i; j++)
        {
            stringbuffer.append(' ');
            int k = array.getLowBound(j);
            int l = array.getSize(j);
            if (k != 0)
            {
                stringbuffer.append(k);
                stringbuffer.append(':');
            }
            stringbuffer.append(k + l);
        }

        stringbuffer.append('>');
    }

    public int createPos(int i, boolean flag)
    {
        int j = offset;
        for (int k = dimensions.length; --k >= 0;)
        {
            int i1 = dimensions[k];
            int j1 = i % i1;
            i /= i1;
            j += j1 * strides[k];
        }

        int l = j << 1;
        boolean flag1;
        if (flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        return flag1 | l;
    }

    public Object get(int i)
    {
        return getRowMajor(i);
    }

    public Object get(int ai[])
    {
        return base.get(getEffectiveIndex(ai));
    }

    public int getEffectiveIndex(int ai[])
    {
        int i = offset;
        int j = dimensions.length;
label0:
        do
        {
            int i1;
label1:
            {
                if (--j < 0)
                {
                    break label0;
                }
                int k = ai[j];
                int l = lowBounds[j];
                if (k >= l)
                {
                    i1 = k - l;
                    if (i1 < dimensions[j])
                    {
                        break label1;
                    }
                }
                throw new IndexOutOfBoundsException();
            }
            i += i1 * strides[j];
        } while (true);
        return i;
    }

    public int getLowBound(int i)
    {
        return lowBounds[i];
    }

    public Object getRowMajor(int i)
    {
        if (simple)
        {
            return base.get(i);
        }
        int j = offset;
        for (int k = dimensions.length; --k >= 0;)
        {
            int l = dimensions[k];
            int i1 = i % l;
            i /= l;
            j += i1 * strides[k];
        }

        return base.get(j);
    }

    public int getSize(int i)
    {
        return dimensions[i];
    }

    public int rank()
    {
        return dimensions.length;
    }

    public Object set(int ai[], Object obj)
    {
        return base.set(getEffectiveIndex(ai), obj);
    }

    public int size()
    {
        int i = 1;
        for (int j = dimensions.length; --j >= 0;)
        {
            i *= dimensions[j];
        }

        return i;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        toString(((Array) (this)), stringbuffer);
        return stringbuffer.toString();
    }

    public Array transpose(int ai[], int ai1[], int i, int ai2[])
    {
        Object obj;
        if (ai1.length == 1 && ai[0] == 0)
        {
            obj = new GeneralArray1();
        } else
        {
            obj = new GeneralArray();
        }
        obj.offset = i;
        obj.strides = ai2;
        obj.dimensions = ai1;
        obj.lowBounds = ai;
        obj.base = base;
        obj.simple = false;
        return ((Array) (obj));
    }

}
