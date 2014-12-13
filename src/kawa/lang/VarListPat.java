// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.lists.Consumer;
import gnu.lists.Pair;

// Referenced classes of package kawa.lang:
//            Pattern

public class VarListPat extends Pattern
{

    int min_length;

    public VarListPat(int i)
    {
        min_length = i;
    }

    public boolean match(Object obj, Object aobj[], int i)
    {
        int j;
        for (j = 0; j < min_length;)
        {
            if (obj instanceof Pair)
            {
                Pair pair = (Pair)obj;
                aobj[i + j] = pair.getCar();
                obj = pair.getCdr();
                j++;
            } else
            {
                return false;
            }
        }

        aobj[i + j] = obj;
        return true;
    }

    public void print(Consumer consumer)
    {
        consumer.write("#<varlist-pattern min:");
        consumer.writeInt(min_length);
        consumer.write(62);
    }

    public int varCount()
    {
        return 1 + min_length;
    }
}
