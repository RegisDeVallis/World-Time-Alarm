// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongType;

public class append extends ProcedureN
{

    public static final append append;

    public append()
    {
    }

    public static Object append$V(Object aobj[])
    {
        int i = aobj.length;
        Object obj1;
        if (i == 0)
        {
            obj1 = LList.Empty;
        } else
        {
            Object obj = aobj[i - 1];
            int j = i - 1;
            obj1 = obj;
            while (--j >= 0) 
            {
                Object obj2 = aobj[j];
                Pair pair = null;
                Object obj3 = null;
                while (obj2 instanceof Pair) 
                {
                    Pair pair1 = (Pair)obj2;
                    Pair pair2 = new Pair(pair1.getCar(), null);
                    Pair pair3;
                    if (pair == null)
                    {
                        pair3 = pair2;
                    } else
                    {
                        pair.setCdr(pair2);
                        pair3 = ((Pair) (obj3));
                    }
                    pair = pair2;
                    obj2 = pair1.getCdr();
                    obj3 = pair3;
                }
                if (obj2 != LList.Empty)
                {
                    throw new WrongType(append, j + 1, aobj[j], "list");
                }
                if (pair != null)
                {
                    pair.setCdr(obj1);
                } else
                {
                    obj3 = obj1;
                }
                obj1 = obj3;
            }
        }
        return obj1;
    }

    public Object applyN(Object aobj[])
    {
        return append$V(aobj);
    }

    static 
    {
        append = new append();
        append.setName("append");
    }
}
