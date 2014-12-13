// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public abstract class AbstractHashTable extends AbstractMap
{
    static class AbstractEntrySet extends AbstractSet
    {

        AbstractHashTable htable;

        public Iterator iterator()
        {
            return new Iterator() {

                int curIndex;
                java.util.Map.Entry currentEntry;
                java.util.Map.Entry nextEntry;
                int nextIndex;
                java.util.Map.Entry previousEntry;
                final AbstractEntrySet this$0;

                private void advance()
                {
                    do
                    {
                        if (nextEntry != null)
                        {
                            break;
                        }
                        int i = -1 + nextIndex;
                        nextIndex = i;
                        if (i < 0)
                        {
                            break;
                        }
                        nextEntry = htable.table[nextIndex];
                    } while (true);
                }

                public boolean hasNext()
                {
                    if (curIndex < 0)
                    {
                        nextIndex = htable.table.length;
                        curIndex = nextIndex;
                        advance();
                    }
                    return nextEntry != null;
                }

                public volatile Object next()
                {
                    return next();
                }

                public java.util.Map.Entry next()
                {
                    if (nextEntry == null)
                    {
                        throw new NoSuchElementException();
                    } else
                    {
                        previousEntry = currentEntry;
                        currentEntry = nextEntry;
                        curIndex = nextIndex;
                        nextEntry = htable.getEntryNext(currentEntry);
                        advance();
                        return currentEntry;
                    }
                }

                public void remove()
                {
                    if (previousEntry == currentEntry)
                    {
                        throw new IllegalStateException();
                    }
                    AbstractHashTable abstracthashtable;
                    if (previousEntry == null)
                    {
                        htable.table[curIndex] = nextEntry;
                    } else
                    {
                        htable.setEntryNext(previousEntry, nextEntry);
                    }
                    abstracthashtable = htable;
                    abstracthashtable.num_bindings = -1 + abstracthashtable.num_bindings;
                    previousEntry = currentEntry;
                }

            
            {
                this$0 = AbstractEntrySet.this;
                super();
                curIndex = -1;
            }
            };
        }

        public int size()
        {
            return htable.size();
        }

        public AbstractEntrySet(AbstractHashTable abstracthashtable)
        {
            htable = abstracthashtable;
        }
    }


    public static final int DEFAULT_INITIAL_SIZE = 64;
    protected int mask;
    protected int num_bindings;
    protected java.util.Map.Entry table[];

    public AbstractHashTable()
    {
        this(64);
    }

    public AbstractHashTable(int i)
    {
        int j;
        for (j = 4; i > 1 << j; j++) { }
        int k = 1 << j;
        table = allocEntries(k);
        mask = k - 1;
    }

    protected abstract java.util.Map.Entry[] allocEntries(int i);

    public void clear()
    {
        java.util.Map.Entry aentry[] = table;
        for (int i = aentry.length; --i >= 0;)
        {
            java.util.Map.Entry entry1;
            for (java.util.Map.Entry entry = aentry[i]; entry != null; entry = entry1)
            {
                entry1 = getEntryNext(entry);
                setEntryNext(entry, null);
            }

            aentry[i] = null;
        }

        num_bindings = 0;
    }

    public Set entrySet()
    {
        return new AbstractEntrySet(this);
    }

    public Object get(Object obj)
    {
        return get(obj, null);
    }

    public Object get(Object obj, Object obj1)
    {
        java.util.Map.Entry entry = getNode(obj);
        if (entry == null)
        {
            return obj1;
        } else
        {
            return entry.getValue();
        }
    }

    protected abstract int getEntryHashCode(java.util.Map.Entry entry);

    protected abstract java.util.Map.Entry getEntryNext(java.util.Map.Entry entry);

    public java.util.Map.Entry getNode(Object obj)
    {
        int i = hash(obj);
        int j = hashToIndex(i);
        for (java.util.Map.Entry entry = table[j]; entry != null; entry = getEntryNext(entry))
        {
            if (matches(obj, i, entry))
            {
                return entry;
            }
        }

        return null;
    }

    public int hash(Object obj)
    {
        if (obj == null)
        {
            return 0;
        } else
        {
            return obj.hashCode();
        }
    }

    protected int hashToIndex(int i)
    {
        return (i ^ i >>> 15) & mask;
    }

    protected abstract java.util.Map.Entry makeEntry(Object obj, int i, Object obj1);

    protected boolean matches(Object obj, int i, java.util.Map.Entry entry)
    {
        return getEntryHashCode(entry) == i && matches(entry.getKey(), obj);
    }

    protected boolean matches(Object obj, Object obj1)
    {
        return obj == obj1 || obj != null && obj.equals(obj1);
    }

    public Object put(Object obj, int i, Object obj1)
    {
        int j = hashToIndex(i);
        java.util.Map.Entry entry = table[j];
        java.util.Map.Entry entry1 = entry;
        do
        {
            if (entry1 == null)
            {
                int k = 1 + num_bindings;
                num_bindings = k;
                if (k >= table.length)
                {
                    rehash();
                    j = hashToIndex(i);
                    entry = table[j];
                }
                java.util.Map.Entry entry2 = makeEntry(obj, i, obj1);
                setEntryNext(entry2, entry);
                table[j] = entry2;
                return null;
            }
            if (matches(obj, i, entry1))
            {
                Object obj2 = entry1.getValue();
                entry1.setValue(obj1);
                return obj2;
            }
            entry1 = getEntryNext(entry1);
        } while (true);
    }

    public Object put(Object obj, Object obj1)
    {
        return put(obj, hash(obj), obj1);
    }

    protected void rehash()
    {
        java.util.Map.Entry aentry[] = table;
        int i = aentry.length;
        int j = i * 2;
        java.util.Map.Entry aentry1[] = allocEntries(j);
        int k = j - 1;
        table = aentry1;
        mask = k;
        for (int l = i; --l >= 0;)
        {
            java.util.Map.Entry entry = aentry[l];
            if (entry != null && getEntryNext(entry) != null)
            {
                java.util.Map.Entry entry3 = null;
                do
                {
                    java.util.Map.Entry entry4 = entry;
                    entry = getEntryNext(entry4);
                    setEntryNext(entry4, entry3);
                    entry3 = entry4;
                } while (entry != null);
                entry = entry3;
            }
            java.util.Map.Entry entry1 = entry;
            while (entry1 != null) 
            {
                java.util.Map.Entry entry2 = getEntryNext(entry1);
                int i1 = hashToIndex(getEntryHashCode(entry1));
                setEntryNext(entry1, aentry1[i1]);
                aentry1[i1] = entry1;
                entry1 = entry2;
            }
        }

    }

    public Object remove(Object obj)
    {
        int i = hash(obj);
        int j = hashToIndex(i);
        java.util.Map.Entry entry = null;
        java.util.Map.Entry entry2;
        for (java.util.Map.Entry entry1 = table[j]; entry1 != null; entry1 = entry2)
        {
            entry2 = getEntryNext(entry1);
            if (matches(obj, i, entry1))
            {
                if (entry == null)
                {
                    table[j] = entry2;
                } else
                {
                    setEntryNext(entry, entry2);
                }
                num_bindings = -1 + num_bindings;
                return entry1.getValue();
            }
            entry = entry1;
        }

        return null;
    }

    protected abstract void setEntryNext(java.util.Map.Entry entry, java.util.Map.Entry entry1);

    public int size()
    {
        return num_bindings;
    }
}
