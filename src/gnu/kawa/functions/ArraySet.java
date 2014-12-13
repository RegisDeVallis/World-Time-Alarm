// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.lists.Array;
import gnu.lists.Sequence;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;

public class ArraySet extends ProcedureN
{

    public static final ArraySet arraySet = new ArraySet();

    public ArraySet()
    {
    }

    public static void arraySet(Array array, Sequence sequence, Object obj)
    {
        int i = sequence.size();
        int ai[] = new int[i];
        for (int j = 0; j < i; j++)
        {
            ai[j] = ((Number)sequence.get(j)).intValue();
        }

        array.set(ai, obj);
    }

    public Object apply3(Object obj, Object obj1, Object obj2)
        throws Throwable
    {
        if (obj1 instanceof Sequence)
        {
            arraySet((Array)obj, (Sequence)obj1, obj2);
            return Values.empty;
        } else
        {
            return super.apply3(obj, obj1, obj2);
        }
    }

    public Object applyN(Object aobj[])
        throws Throwable
    {
        Array array = (Array)aobj[0];
        if (aobj.length == 3)
        {
            Object obj = aobj[1];
            if (obj instanceof Sequence)
            {
                arraySet(array, (Sequence)obj, aobj[2]);
                return Values.empty;
            }
        }
        int i = -2 + aobj.length;
        int ai[] = new int[i];
        for (int j = i; --j >= 0;)
        {
            ai[j] = ((Number)aobj[j + 1]).intValue();
        }

        array.set(ai, aobj[i + 1]);
        return Values.empty;
    }

}
