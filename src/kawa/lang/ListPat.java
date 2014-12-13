// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.text.ReportFormat;

// Referenced classes of package kawa.lang:
//            Pattern

public class ListPat extends Pattern
{

    Object default_value;
    int max_length;
    int min_length;

    public ListPat(int i)
    {
        min_length = i;
        max_length = i;
    }

    public ListPat(int i, int j)
    {
        min_length = i;
        max_length = j;
    }

    public ListPat(int i, int j, Object obj)
    {
        min_length = i;
        max_length = j;
        default_value = obj;
    }

    public static boolean match(int i, int j, Object obj, Object obj1, Object aobj[], int k)
    {
        int l;
        for (l = 0; l < j; l++)
        {
            if (!(obj1 instanceof Pair))
            {
                break MISSING_BLOCK_LABEL_47;
            }
            Pair pair = (Pair)obj1;
            aobj[k + l] = pair.getCar();
            obj1 = pair.getCdr();
        }

          goto _L1
        if (l >= i) goto _L1; else goto _L2
_L2:
        return false;
_L1:
        if (l != j || obj1 == LList.Empty)
        {
            for (; l < j; l++)
            {
                aobj[k + l] = obj;
            }

            return true;
        }
        if (true) goto _L2; else goto _L3
_L3:
    }

    public static Object[] match(int i, int j, Object obj, Object obj1)
    {
        Object aobj[] = new Object[j];
        if (match(i, j, obj, obj1, aobj, 0))
        {
            return aobj;
        } else
        {
            return null;
        }
    }

    public boolean match(Object obj, Object aobj[], int i)
    {
        return match(min_length, max_length, default_value, obj, aobj, i);
    }

    public void print(Consumer consumer)
    {
        consumer.write("#<list-pattern min:");
        consumer.write(Integer.toString(min_length));
        consumer.write(" max:");
        consumer.write(Integer.toString(max_length));
        consumer.write(" default:");
        ReportFormat.print(default_value, consumer);
        consumer.write(62);
    }

    public int varCount()
    {
        return max_length;
    }
}
