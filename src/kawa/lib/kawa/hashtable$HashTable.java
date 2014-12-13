// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib.kawa;

import gnu.kawa.functions.SetNamedPart;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.util.AbstractHashTable;
import gnu.kawa.util.GeneralHashTable;
import gnu.kawa.util.HashNode;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import kawa.lib.lists;
import kawa.standard.thisRef;

// Referenced classes of package kawa.lib.kawa:
//            hashtable

public class tNamedPart extends GeneralHashTable
{

    public Procedure equivalenceFunction;
    public Procedure hashFunction;
    public boolean mutable;

    private void $finit$()
    {
        mutable = true;
    }

    public Object clone()
    {
        return new <init>(this, true);
    }

    public Pair entriesVectorPair()
    {
        FVector fvector = new FVector();
        FVector fvector1 = new FVector();
        java.util.ble able[] = super.table;
        HashNode ahashnode[];
        int i;
        try
        {
            ahashnode = (HashNode[])able;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "table", -2, able);
        }
        for (i = -1 + ahashnode.length; i >= 0; i--)
        {
            for (HashNode hashnode = ahashnode[i]; hashnode != null; hashnode = getEntryNext(hashnode))
            {
                fvector.add(hashnode.getKey());
                fvector1.add(hashnode.getValue());
            }

        }

        return lists.cons(fvector, fvector1);
    }

    public Object fold(Procedure procedure, Object obj)
    {
        java.util.ble able[] = super.table;
        HashNode ahashnode[];
        int i;
        try
        {
            ahashnode = (HashNode[])able;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "table", -2, able);
        }
        for (i = -1 + ahashnode.length; i >= 0; i--)
        {
            for (HashNode hashnode = ahashnode[i]; hashnode != null; hashnode = getEntryNext(hashnode))
            {
                obj = procedure.apply3(hashnode.getKey(), hashnode.getValue(), obj);
            }

        }

        return obj;
    }

    public int hash(Object obj)
    {
        return ((Number)hashFunction.apply1(obj)).intValue();
    }

    public FVector keysVector()
    {
        FVector fvector = new FVector();
        java.util.ble able[] = super.table;
        HashNode ahashnode[];
        int i;
        try
        {
            ahashnode = (HashNode[])able;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "table", -2, able);
        }
        for (i = -1 + ahashnode.length; i >= 0; i--)
        {
            for (HashNode hashnode = ahashnode[i]; hashnode != null; hashnode = getEntryNext(hashnode))
            {
                fvector.add(hashnode.getKey());
            }

        }

        return fvector;
    }

    public boolean matches(Object obj, Object obj1)
    {
        return equivalenceFunction.apply2(obj, obj1) != Boolean.FALSE;
    }

    public void putAll(equivalenceFunction equivalencefunction)
    {
        java.util.ble able[] = ((AbstractHashTable) (equivalencefunction)).table;
        HashNode ahashnode[];
        int i;
        try
        {
            ahashnode = (HashNode[])able;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "table", -2, able);
        }
        for (i = -1 + ahashnode.length; i >= 0; i--)
        {
            for (HashNode hashnode = ahashnode[i]; hashnode != null; hashnode = equivalencefunction.getEntryNext(hashnode))
            {
                put(hashnode.getKey(), hashnode.getValue());
            }

        }

    }

    public Object toAlist()
    {
        Object obj = LList.Empty;
        java.util.ble able[] = super.table;
        HashNode ahashnode[];
        int i;
        try
        {
            ahashnode = (HashNode[])able;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "table", -2, able);
        }
        for (i = -1 + ahashnode.length; i >= 0; i--)
        {
            for (HashNode hashnode = ahashnode[i]; hashnode != null; hashnode = getEntryNext(hashnode))
            {
                obj = lists.cons(lists.cons(hashnode.getKey(), hashnode.getValue()), obj);
            }

        }

        return obj;
    }

    public HashNode[] toNodeArray()
    {
        HashNode ahashnode[] = new HashNode[size()];
        int i = 0;
        java.util.ble able[] = super.table;
        HashNode ahashnode1[];
        int j;
        try
        {
            ahashnode1 = (HashNode[])able;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "table", -2, able);
        }
        for (j = -1 + ahashnode1.length; j >= 0;)
        {
            HashNode hashnode = ahashnode1[j];
            int k;
            int l;
            for (k = i; hashnode != null; k = l)
            {
                ahashnode[k] = hashnode;
                l = k + 1;
                hashnode = getEntryNext(hashnode);
            }

            j--;
            i = k;
        }

        return ahashnode;
    }

    public LList toNodeList()
    {
        LList llist = LList.Empty;
        java.util.ble able[] = super.table;
        HashNode ahashnode[];
        int i;
        Object obj;
        try
        {
            ahashnode = (HashNode[])able;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "table", -2, able);
        }
        i = -1 + ahashnode.length;
        obj = llist;
        for (; i >= 0; i--)
        {
            for (HashNode hashnode = ahashnode[i]; hashnode != null;)
            {
                Pair pair = lists.cons(hashnode, obj);
                hashnode = getEntryNext(hashnode);
                obj = pair;
            }

        }

        return (LList)obj;
    }

    public void walk(Procedure procedure)
    {
        java.util.ble able[] = super.table;
        HashNode ahashnode[];
        int i;
        try
        {
            ahashnode = (HashNode[])able;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "table", -2, able);
        }
        for (i = -1 + ahashnode.length; i >= 0; i--)
        {
            for (HashNode hashnode = ahashnode[i]; hashnode != null; hashnode = getEntryNext(hashnode))
            {
                procedure.apply2(hashnode.getKey(), hashnode.getValue());
            }

        }

    }

    public (Procedure procedure, Procedure procedure1)
    {
        $finit$();
        equivalenceFunction = procedure;
        hashFunction = procedure1;
    }

    public hashFunction(Procedure procedure, Procedure procedure1, int i)
    {
        super(i);
        $finit$();
        equivalenceFunction = procedure;
        hashFunction = procedure1;
    }

    public hashFunction(hashFunction hashfunction, boolean flag)
    {
        $finit$();
        Invoke invoke = Invoke.invokeSpecial;
        Object aobj[] = new Object[5];
        aobj[0] = hashtable.hashtable;
        aobj[1] = this;
        aobj[2] = hashfunction.equivalenceFunction.apply0();
        aobj[3] = hashfunction.hashFunction.apply0();
        aobj[4] = Integer.valueOf(100 + hashfunction.size());
        invoke.applyN(aobj);
        putAll(hashfunction);
        SetNamedPart setnamedpart = SetNamedPart.setNamedPart;
        thisRef thisref = thisRef.thisSyntax;
        gnu.mapping.SimpleSymbol simplesymbol = hashtable.Lit0;
        Boolean boolean1;
        if (flag)
        {
            boolean1 = Boolean.TRUE;
        } else
        {
            boolean1 = Boolean.FALSE;
        }
        setnamedpart.apply3(thisref, simplesymbol, boolean1);
    }
}
