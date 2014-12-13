// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongType;

public class vector_append extends ProcedureN
{

    public static final vector_append vectorAppend = new vector_append("vector-append");

    public vector_append(String s)
    {
        super(s);
    }

    public static FVector apply$V(Object aobj[])
    {
        int i = 0;
        int j = aobj.length;
        for (int k = j; --k >= 0;)
        {
            Object obj1 = aobj[k];
            if (obj1 instanceof FVector)
            {
                i += ((FVector)obj1).size();
            } else
            {
                int k2 = LList.listLength(obj1, false);
                if (k2 < 0)
                {
                    throw new WrongType(vectorAppend, k, obj1, "list or vector");
                }
                i += k2;
            }
        }

        Object aobj1[] = new Object[i];
        int l = 0;
        int i1 = 0;
        while (i1 < j) 
        {
            Object obj = aobj[i1];
            if (obj instanceof FVector)
            {
                FVector fvector = (FVector)obj;
                int k1 = fvector.size();
                int l1 = 0;
                int i2;
                int j2;
                for (i2 = l; l1 < k1; i2 = j2)
                {
                    j2 = i2 + 1;
                    aobj1[i2] = fvector.get(l1);
                    l1++;
                }

                l = i2;
            } else
            if (obj instanceof Pair)
            {
                while (obj != LList.Empty) 
                {
                    Pair pair = (Pair)obj;
                    int j1 = l + 1;
                    aobj1[l] = pair.getCar();
                    obj = pair.getCdr();
                    l = j1;
                }
            }
            i1++;
        }
        return new FVector(aobj1);
    }

    public Object applyN(Object aobj[])
    {
        return apply$V(aobj);
    }

}
