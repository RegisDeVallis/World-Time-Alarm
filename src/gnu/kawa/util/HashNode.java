// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.util;


public class HashNode
    implements java.util.Map.Entry
{

    int hash;
    Object key;
    public HashNode next;
    Object value;

    public HashNode(Object obj, Object obj1)
    {
        key = obj;
        value = obj1;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof HashNode)
        {
            HashNode hashnode = (HashNode)obj;
            if ((key != null ? key.equals(hashnode.key) : hashnode.key == null) && (value != null ? value.equals(hashnode.value) : hashnode.value == null))
            {
                return true;
            }
        }
        return false;
    }

    public Object get(Object obj)
    {
        return getValue();
    }

    public Object getKey()
    {
        return key;
    }

    public Object getValue()
    {
        return value;
    }

    public int hashCode()
    {
        int i;
        Object obj;
        int j;
        if (key == null)
        {
            i = 0;
        } else
        {
            i = key.hashCode();
        }
        obj = value;
        j = 0;
        if (obj != null)
        {
            j = value.hashCode();
        }
        return i ^ j;
    }

    public Object setValue(Object obj)
    {
        Object obj1 = value;
        value = obj;
        return obj1;
    }
}
