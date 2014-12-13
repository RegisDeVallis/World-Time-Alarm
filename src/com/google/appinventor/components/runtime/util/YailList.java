// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Collection;
import java.util.List;
import org.json.JSONException;

// Referenced classes of package com.google.appinventor.components.runtime.util:
//            YailConstants, JsonUtil

public class YailList extends Pair
{

    public YailList()
    {
        super(YailConstants.YAIL_HEADER, LList.Empty);
    }

    private YailList(Object obj)
    {
        super(YailConstants.YAIL_HEADER, obj);
    }

    public static YailList makeEmptyList()
    {
        return new YailList();
    }

    public static YailList makeList(Collection collection)
    {
        return new YailList(Pair.makeList(collection.toArray(), 0));
    }

    public static YailList makeList(List list)
    {
        return new YailList(Pair.makeList(list));
    }

    public static YailList makeList(Object aobj[])
    {
        return new YailList(Pair.makeList(aobj, 0));
    }

    public Object getObject(int i)
    {
        return get(i + 1);
    }

    public String getString(int i)
    {
        return get(i + 1).toString();
    }

    public int size()
    {
        return -1 + super.size();
    }

    public Object[] toArray()
    {
        if (cdr instanceof Pair)
        {
            return ((Pair)cdr).toArray();
        }
        if (cdr instanceof LList)
        {
            return ((LList)cdr).toArray();
        } else
        {
            throw new YailRuntimeError("YailList cannot be represented as an array", "YailList Error.");
        }
    }

    public String toJSONString()
    {
        StringBuilder stringbuilder;
        String s;
        int i;
        int j;
        Object obj;
        String s1;
        try
        {
            stringbuilder = new StringBuilder();
        }
        catch (JSONException jsonexception)
        {
            throw new YailRuntimeError("List failed to convert to JSON.", "JSON Creation Error.");
        }
        s = "";
        stringbuilder.append('[');
        i = size();
        j = 1;
_L2:
        if (j > i)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = get(j);
        stringbuilder.append(s).append(JsonUtil.getJsonRepresentation(obj));
        s = ",";
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        stringbuilder.append(']');
        s1 = stringbuilder.toString();
        return s1;
    }

    public String toString()
    {
        if (cdr instanceof Pair)
        {
            return ((Pair)cdr).toString();
        }
        if (cdr instanceof LList)
        {
            return ((LList)cdr).toString();
        } else
        {
            throw new RuntimeException("YailList cannot be represented as a String");
        }
    }

    public String[] toStringArray()
    {
        int i = size();
        String as[] = new String[i];
        for (int j = 1; j <= i; j++)
        {
            as[j - 1] = String.valueOf(get(j));
        }

        return as;
    }
}
