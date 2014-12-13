// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.lists.Array;
import gnu.lists.FVector;
import gnu.lists.GeneralArray;
import gnu.lists.SimpleVector;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class Arrays
{

    static final int shapeStrides[] = {
        2, 1
    };
    static final int zeros2[] = new int[2];

    public Arrays()
    {
    }

    public static int effectiveIndex(Array array, Procedure procedure, Object aobj[], int ai[])
        throws Throwable
    {
        Object obj = procedure.applyN(aobj);
        if (obj instanceof Values)
        {
            Values values = (Values)obj;
            int i = 0;
            int j = 0;
            do
            {
                i = values.nextPos(i);
                if (i == 0)
                {
                    break;
                }
                ai[j] = ((Number)values.getPosPrevious(i)).intValue();
                j++;
            } while (true);
        } else
        {
            ai[0] = ((Number)obj).intValue();
        }
        return array.getEffectiveIndex(ai);
    }

    public static Array make(Array array, Object obj)
    {
        int i = array.getSize(0);
        int ai[] = new int[i];
        int ai1[] = null;
        int j = 1;
        int i1;
        for (int k = i; --k >= 0; j *= i1)
        {
            int l = ((Number)array.getRowMajor(k * 2)).intValue();
            i1 = ((Number)array.getRowMajor(1 + k * 2)).intValue() - l;
            ai[k] = i1;
            if (l == 0)
            {
                continue;
            }
            if (ai1 == null)
            {
                ai1 = new int[i];
            }
            ai1[k] = l;
        }

        return GeneralArray.makeSimple(ai1, ai, new FVector(j, obj));
    }

    public static Array makeSimple(Array array, SimpleVector simplevector)
    {
        int i = array.getSize(0);
        int ai[] = new int[i];
        int ai1[] = null;
        int j = i;
        do
        {
            if (--j < 0)
            {
                break;
            }
            int k = ((Number)array.getRowMajor(j * 2)).intValue();
            ai[j] = ((Number)array.getRowMajor(1 + j * 2)).intValue() - k;
            if (k != 0)
            {
                if (ai1 == null)
                {
                    ai1 = new int[i];
                }
                ai1[j] = k;
            }
        } while (true);
        return GeneralArray.makeSimple(ai1, ai, simplevector);
    }

    public static Array shape(Object aobj[])
    {
        int i = aobj.length;
        if ((i & 1) != 0)
        {
            throw new RuntimeException("shape: not an even number of arguments");
        } else
        {
            int ai[] = {
                i >> 1, 2
            };
            return (new FVector(aobj)).transpose(zeros2, ai, 0, shapeStrides);
        }
    }

    public static Array shareArray(Array array, Array array1, Procedure procedure)
        throws Throwable
    {
        int i = array1.getSize(0);
        Object aobj[] = new Object[i];
        int ai[] = new int[i];
        int ai1[] = new int[i];
        boolean flag = false;
        int j = i;
        do
        {
            if (--j < 0)
            {
                break;
            }
            Object obj1 = array1.getRowMajor(j * 2);
            aobj[j] = obj1;
            int l1 = ((Number)obj1).intValue();
            ai1[j] = l1;
            int i2 = ((Number)array1.getRowMajor(1 + j * 2)).intValue() - l1;
            ai[j] = i2;
            if (i2 <= 0)
            {
                flag = true;
            }
        } while (true);
        int k = array.rank();
        int ai2[] = new int[i];
        int l;
        if (flag)
        {
            l = 0;
        } else
        {
            int ai3[] = new int[k];
            l = effectiveIndex(array, procedure, aobj, ai3);
            int i1 = i;
            while (--i1 >= 0) 
            {
                int j1 = ai[i1];
                int k1 = ai1[i1];
                if (j1 <= 1)
                {
                    ai2[i1] = 0;
                } else
                {
                    Object obj = aobj[i1];
                    aobj[i1] = IntNum.make(k1 + 1);
                    ai2[i1] = effectiveIndex(array, procedure, aobj, ai3) - l;
                    aobj[i1] = obj;
                }
            }
        }
        return array.transpose(ai1, ai, l, ai2);
    }

}
