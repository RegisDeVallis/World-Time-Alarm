// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;

import java.lang.ref.WeakReference;

// Referenced classes of package gnu.mapping:
//            Entry, Symbol

public class Table2D
{

    private static Table2D instance = new Table2D();
    int log2Size;
    int mask;
    int num_bindings;
    Entry table[];

    public Table2D()
    {
        this(64);
    }

    public Table2D(int i)
    {
        for (log2Size = 4; i > 1 << log2Size; log2Size = 1 + log2Size) { }
        int j = 1 << log2Size;
        table = new Entry[j];
        mask = j - 1;
    }

    public static final Table2D getInstance()
    {
        return instance;
    }

    public Object get(Object obj, Object obj1, Object obj2)
    {
        Entry entry = lookup(obj, obj1, System.identityHashCode(obj), System.identityHashCode(obj1), false);
        if (entry == null || entry.value == entry)
        {
            return obj2;
        } else
        {
            return entry.value;
        }
    }

    public boolean isBound(Object obj, Object obj1)
    {
        Entry entry = lookup(obj, obj1, System.identityHashCode(obj), System.identityHashCode(obj1), false);
        boolean flag = false;
        if (entry != null)
        {
            Object obj2 = entry.value;
            flag = false;
            if (obj2 != entry)
            {
                flag = true;
            }
        }
        return flag;
    }

    protected Entry lookup(Object obj, Object obj1, int i, int j, boolean flag)
    {
        int k = (i ^ j) & mask;
        Entry entry = null;
        Entry entry1 = table[k];
        Entry entry2 = entry1;
        while (entry2 != null) 
        {
            Object obj4 = entry2.key1;
            Object obj5 = entry2.key2;
            boolean flag1 = obj4 instanceof WeakReference;
            boolean flag2 = false;
            Entry entry4;
            if (flag1)
            {
                obj4 = ((WeakReference)obj4).get();
                if (obj4 == null)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
            }
            if (obj5 instanceof WeakReference)
            {
                obj5 = ((WeakReference)obj5).get();
                if (obj5 != null);
                flag2 = true;
            }
            entry4 = entry2.chain;
            if (flag2)
            {
                if (entry == null)
                {
                    table[k] = entry4;
                } else
                {
                    entry.chain = entry4;
                }
                num_bindings = -1 + num_bindings;
                entry2.value = entry2;
            } else
            {
                if (obj4 == obj && obj5 == obj1)
                {
                    return entry2;
                }
                entry = entry2;
            }
            entry2 = entry4;
        }
        if (flag)
        {
            Entry entry3 = new Entry();
            Object obj2 = wrapReference(obj);
            Object obj3 = wrapReference(obj1);
            entry3.key1 = obj2;
            entry3.key2 = obj3;
            num_bindings = 1 + num_bindings;
            entry3.chain = entry1;
            table[k] = entry3;
            entry3.value = entry3;
            return entry3;
        } else
        {
            return null;
        }
    }

    public Object put(Object obj, Object obj1, Object obj2)
    {
        Entry entry = lookup(obj, obj1, System.identityHashCode(obj), System.identityHashCode(obj1), true);
        Object obj3 = entry.getValue();
        entry.value = obj2;
        return obj3;
    }

    void rehash()
    {
        Entry aentry[] = table;
        int i = aentry.length;
        int j = i * 2;
        Entry aentry1[] = new Entry[j];
        int k = j - 1;
        num_bindings = 0;
        for (int l = i; --l >= 0;)
        {
            Entry entry = aentry[l];
            while (entry != null) 
            {
                Object obj = entry.key1;
                Object obj1 = entry.key2;
                boolean flag = obj instanceof WeakReference;
                boolean flag1 = false;
                if (flag)
                {
                    obj = ((WeakReference)obj).get();
                    Entry entry1;
                    if (obj == null)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                }
                if (obj1 instanceof WeakReference)
                {
                    obj1 = ((WeakReference)obj1).get();
                    if (obj1 == null)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                }
                entry1 = entry.chain;
                if (flag1)
                {
                    entry.value = entry;
                } else
                {
                    int i1 = k & (System.identityHashCode(obj) ^ System.identityHashCode(obj1));
                    entry.chain = aentry1[i1];
                    aentry1[i1] = entry;
                    num_bindings = 1 + num_bindings;
                }
                entry = entry1;
            }
        }

        table = aentry1;
        log2Size = 1 + log2Size;
        mask = k;
    }

    public Object remove(Object obj, Object obj1)
    {
        return remove(obj, obj1, System.identityHashCode(obj) ^ System.identityHashCode(obj1));
    }

    public Object remove(Object obj, Object obj1, int i)
    {
        return remove(obj, obj1, i);
    }

    public Object remove(Object obj, Object obj1, int i, int j)
    {
        int k = (i ^ j) & mask;
        Entry entry = null;
        Entry entry1 = table[k];
        while (entry1 != null) 
        {
            Object obj2 = entry1.key1;
            Object obj3 = entry1.key2;
            boolean flag = obj2 instanceof WeakReference;
            boolean flag1 = false;
            Entry entry2;
            Object obj4;
            boolean flag2;
            if (flag)
            {
                obj2 = ((WeakReference)obj2).get();
                if (obj2 == null)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
            }
            if (obj3 instanceof WeakReference)
            {
                obj3 = ((WeakReference)obj3).get();
                if (obj3 == null)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
            }
            entry2 = entry1.chain;
            obj4 = entry1.value;
            if (obj2 == obj && obj3 == obj1)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (flag1 || flag2)
            {
                if (entry == null)
                {
                    table[k] = entry2;
                } else
                {
                    entry.chain = entry2;
                }
                num_bindings = -1 + num_bindings;
                entry1.value = entry1;
            } else
            {
                if (flag2)
                {
                    return obj4;
                }
                entry = entry1;
            }
            entry1 = entry2;
        }
        return null;
    }

    protected Object wrapReference(Object obj)
    {
        if (obj == null || (obj instanceof Symbol))
        {
            return obj;
        } else
        {
            return new WeakReference(obj);
        }
    }

}
