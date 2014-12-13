// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib.kawa;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.SetNamedPart;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.util.AbstractHashTable;
import gnu.kawa.util.GeneralHashTable;
import gnu.kawa.util.HashNode;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.thisRef;

public class hashtable extends ModuleBody
{
    public class HashTable extends GeneralHashTable
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
            return new HashTable(this, true);
        }

        public Pair entriesVectorPair()
        {
            FVector fvector = new FVector();
            FVector fvector1 = new FVector();
            java.util.Map.Entry aentry[] = super.table;
            HashNode ahashnode[];
            int i;
            try
            {
                ahashnode = (HashNode[])aentry;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "table", -2, aentry);
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
            java.util.Map.Entry aentry[] = super.table;
            HashNode ahashnode[];
            int i;
            try
            {
                ahashnode = (HashNode[])aentry;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "table", -2, aentry);
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
            java.util.Map.Entry aentry[] = super.table;
            HashNode ahashnode[];
            int i;
            try
            {
                ahashnode = (HashNode[])aentry;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "table", -2, aentry);
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

        public void putAll(HashTable hashtable1)
        {
            java.util.Map.Entry aentry[] = ((AbstractHashTable) (hashtable1)).table;
            HashNode ahashnode[];
            int i;
            try
            {
                ahashnode = (HashNode[])aentry;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "table", -2, aentry);
            }
            for (i = -1 + ahashnode.length; i >= 0; i--)
            {
                for (HashNode hashnode = ahashnode[i]; hashnode != null; hashnode = hashtable1.getEntryNext(hashnode))
                {
                    put(hashnode.getKey(), hashnode.getValue());
                }

            }

        }

        public Object toAlist()
        {
            Object obj = LList.Empty;
            java.util.Map.Entry aentry[] = super.table;
            HashNode ahashnode[];
            int i;
            try
            {
                ahashnode = (HashNode[])aentry;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "table", -2, aentry);
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
            java.util.Map.Entry aentry[] = super.table;
            HashNode ahashnode1[];
            int j;
            try
            {
                ahashnode1 = (HashNode[])aentry;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "table", -2, aentry);
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
            java.util.Map.Entry aentry[] = super.table;
            HashNode ahashnode[];
            int i;
            Object obj;
            try
            {
                ahashnode = (HashNode[])aentry;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "table", -2, aentry);
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
            java.util.Map.Entry aentry[] = super.table;
            HashNode ahashnode[];
            int i;
            try
            {
                ahashnode = (HashNode[])aentry;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "table", -2, aentry);
            }
            for (i = -1 + ahashnode.length; i >= 0; i--)
            {
                for (HashNode hashnode = ahashnode[i]; hashnode != null; hashnode = getEntryNext(hashnode))
                {
                    procedure.apply2(hashnode.getKey(), hashnode.getValue());
                }

            }

        }

        public HashTable(Procedure procedure, Procedure procedure1)
        {
            $finit$();
            equivalenceFunction = procedure;
            hashFunction = procedure1;
        }

        public HashTable(Procedure procedure, Procedure procedure1, int i)
        {
            super(i);
            $finit$();
            equivalenceFunction = procedure;
            hashFunction = procedure1;
        }

        public HashTable(HashTable hashtable1, boolean flag)
        {
            $finit$();
            Invoke invoke = Invoke.invokeSpecial;
            Object aobj[] = new Object[5];
            aobj[0] = hashtable.hashtable;
            aobj[1] = this;
            aobj[2] = hashtable1.equivalenceFunction.apply0();
            aobj[3] = hashtable1.hashFunction.apply0();
            aobj[4] = Integer.valueOf(100 + hashtable1.size());
            invoke.applyN(aobj);
            putAll(hashtable1);
            SetNamedPart setnamedpart = SetNamedPart.setNamedPart;
            thisRef thisref = thisRef.thisSyntax;
            SimpleSymbol simplesymbol = hashtable.Lit0;
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


    public static final Location $Prvt$do = StaticFieldLocation.make("kawa.lib.std_syntax", "do");
    public static final Class $Prvt$hashnode = gnu/kawa/util/HashNode;
    public static final Location $Prvt$let$St = StaticFieldLocation.make("kawa.lib.std_syntax", "let$St");
    public static final hashtable $instance;
    static final SimpleSymbol Lit0 = (SimpleSymbol)(new SimpleSymbol("mutable")).readResolve();
    static final SimpleSymbol Lit1;
    public static final Class hashtable = kawa/lib/kawa/hashtable$HashTable;
    public static final ModuleMethod hashtable$Mncheck$Mnmutable;

    public hashtable()
    {
        ModuleInfo.register(this);
    }

    public static void hashtableCheckMutable(HashTable hashtable1)
    {
        if (!hashtable1.mutable)
        {
            misc.error$V("cannot modify non-mutable hashtable", new Object[0]);
        }
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        if (modulemethod.selector == 1)
        {
            HashTable hashtable1;
            try
            {
                hashtable1 = (HashTable)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "hashtable-check-mutable", 1, obj);
            }
            hashtableCheckMutable(hashtable1);
            return Values.empty;
        } else
        {
            return super.apply1(modulemethod, obj);
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        if (modulemethod.selector == 1)
        {
            if (!(obj instanceof HashTable))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }
        } else
        {
            return super.match1(modulemethod, obj, callcontext);
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit1 = (SimpleSymbol)(new SimpleSymbol("hashtable-check-mutable")).readResolve();
        $instance = new hashtable();
        hashtable$Mncheck$Mnmutable = new ModuleMethod($instance, 1, Lit1, 4097);
        $instance.run();
    }
}
