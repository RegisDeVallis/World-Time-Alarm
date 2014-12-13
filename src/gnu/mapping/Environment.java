// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;

import java.util.Hashtable;

// Referenced classes of package gnu.mapping:
//            PropertySet, SimpleEnvironment, InheritingEnvironment, EnvironmentKey, 
//            NamedLocation, LocationEnumeration, Location, SharedLocation, 
//            Symbol, Namespace, UnboundLocationException

public abstract class Environment extends PropertySet
{
    static class InheritedLocal extends InheritableThreadLocal
    {

        protected Environment childValue(Environment environment)
        {
            if (environment == null)
            {
                environment = Environment.getCurrent();
            }
            SimpleEnvironment simpleenvironment = environment.cloneForThread();
            simpleenvironment.flags = 8 | simpleenvironment.flags;
            simpleenvironment.flags = 0xffffffef & simpleenvironment.flags;
            return simpleenvironment;
        }

        protected volatile Object childValue(Object obj)
        {
            return childValue((Environment)obj);
        }

        InheritedLocal()
        {
        }
    }


    static final int CAN_DEFINE = 1;
    static final int CAN_IMPLICITLY_DEFINE = 4;
    static final int CAN_REDEFINE = 2;
    static final int DIRECT_INHERITED_ON_SET = 16;
    public static final int INDIRECT_DEFINES = 32;
    static final int THREAD_SAFE = 8;
    protected static final InheritedLocal curEnvironment = new InheritedLocal();
    static final Hashtable envTable = new Hashtable(50);
    static Environment global;
    int flags;

    public Environment()
    {
        flags = 23;
    }

    public static Environment current()
    {
        return getCurrent();
    }

    public static Environment getCurrent()
    {
        Object obj = (Environment)curEnvironment.get();
        if (obj == null)
        {
            obj = make(Thread.currentThread().getName(), global);
            obj.flags = 8 | ((Environment) (obj)).flags;
            curEnvironment.set(obj);
        }
        return ((Environment) (obj));
    }

    public static Environment getGlobal()
    {
        return global;
    }

    public static Environment getInstance(String s)
    {
        if (s == null)
        {
            s = "";
        }
        Hashtable hashtable = envTable;
        hashtable;
        JVM INSTR monitorenter ;
        Environment environment = (Environment)envTable.get(s);
        if (environment == null)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        hashtable;
        JVM INSTR monitorexit ;
        return environment;
        SimpleEnvironment simpleenvironment;
        simpleenvironment = new SimpleEnvironment();
        simpleenvironment.setName(s);
        envTable.put(s, simpleenvironment);
        hashtable;
        JVM INSTR monitorexit ;
        return simpleenvironment;
        Exception exception;
        exception;
        hashtable;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static InheritingEnvironment make(String s, Environment environment)
    {
        return new InheritingEnvironment(s, environment);
    }

    public static SimpleEnvironment make()
    {
        return new SimpleEnvironment();
    }

    public static SimpleEnvironment make(String s)
    {
        return new SimpleEnvironment(s);
    }

    public static void restoreCurrent(Environment environment)
    {
        curEnvironment.set(environment);
    }

    public static void setCurrent(Environment environment)
    {
        curEnvironment.set(environment);
    }

    public static void setGlobal(Environment environment)
    {
        global = environment;
    }

    public static Environment setSaveCurrent(Environment environment)
    {
        Environment environment1 = (Environment)curEnvironment.get();
        curEnvironment.set(environment);
        return environment1;
    }

    public static Environment user()
    {
        return getCurrent();
    }

    public abstract NamedLocation addLocation(Symbol symbol, Object obj, Location location);

    public final void addLocation(EnvironmentKey environmentkey, Location location)
    {
        addLocation(environmentkey.getKeySymbol(), environmentkey.getKeyProperty(), location);
    }

    public final void addLocation(NamedLocation namedlocation)
    {
        addLocation(namedlocation.getKeySymbol(), namedlocation.getKeyProperty(), ((Location) (namedlocation)));
    }

    SimpleEnvironment cloneForThread()
    {
        InheritingEnvironment inheritingenvironment = new InheritingEnvironment(null, this);
        if (this instanceof InheritingEnvironment)
        {
            InheritingEnvironment inheritingenvironment1 = (InheritingEnvironment)this;
            int i = inheritingenvironment1.numInherited;
            inheritingenvironment.numInherited = i;
            inheritingenvironment.inherited = new Environment[i];
            for (int j = 0; j < i; j++)
            {
                inheritingenvironment.inherited[j] = inheritingenvironment1.inherited[j];
            }

        }
        LocationEnumeration locationenumeration = enumerateLocations();
        do
        {
            if (!locationenumeration.hasMoreElements())
            {
                break;
            }
            Location location = locationenumeration.nextLocation();
            Symbol symbol = location.getKeySymbol();
            Object obj = location.getKeyProperty();
            if (symbol != null && (location instanceof NamedLocation))
            {
                Object obj1 = (NamedLocation)location;
                if (((NamedLocation) (obj1)).base == null)
                {
                    SharedLocation sharedlocation = new SharedLocation(symbol, obj, 0);
                    sharedlocation.value = ((NamedLocation) (obj1)).value;
                    obj1.base = sharedlocation;
                    obj1.value = null;
                    obj1 = sharedlocation;
                }
                inheritingenvironment.addUnboundLocation(symbol, obj, symbol.hashCode() ^ System.identityHashCode(obj)).base = ((Location) (obj1));
            }
        } while (true);
        return inheritingenvironment;
    }

    public final boolean containsKey(Object obj)
    {
        boolean flag = obj instanceof EnvironmentKey;
        Object obj1 = null;
        if (flag)
        {
            EnvironmentKey environmentkey = (EnvironmentKey)obj;
            obj = environmentkey.getKeySymbol();
            obj1 = environmentkey.getKeyProperty();
        }
        Symbol symbol;
        if (obj instanceof Symbol)
        {
            symbol = (Symbol)obj;
        } else
        {
            symbol = getSymbol((String)obj);
        }
        return isBound(symbol, obj1);
    }

    public Namespace defaultNamespace()
    {
        return Namespace.getDefault();
    }

    public abstract void define(Symbol symbol, Object obj, Object obj1);

    public abstract LocationEnumeration enumerateAllLocations();

    public abstract LocationEnumeration enumerateLocations();

    public final Object get(EnvironmentKey environmentkey, Object obj)
    {
        return get(environmentkey.getKeySymbol(), environmentkey.getKeyProperty(), obj);
    }

    public Object get(Symbol symbol)
    {
        String s = Location.UNBOUND;
        Object obj = get(symbol, null, s);
        if (obj == s)
        {
            throw new UnboundLocationException(symbol);
        } else
        {
            return obj;
        }
    }

    public Object get(Symbol symbol, Object obj, Object obj1)
    {
        Location location = lookup(symbol, obj);
        if (location == null)
        {
            return obj1;
        } else
        {
            return location.get(obj1);
        }
    }

    public final Object get(Object obj)
    {
        boolean flag = obj instanceof EnvironmentKey;
        Object obj1 = null;
        if (flag)
        {
            EnvironmentKey environmentkey = (EnvironmentKey)obj;
            obj = environmentkey.getKeySymbol();
            obj1 = environmentkey.getKeyProperty();
        }
        Symbol symbol;
        if (obj instanceof Symbol)
        {
            symbol = (Symbol)obj;
        } else
        {
            symbol = getSymbol((String)obj);
        }
        return get(symbol, obj1, null);
    }

    public final Object get(String s, Object obj)
    {
        return get(getSymbol(s), null, obj);
    }

    public boolean getCanDefine()
    {
        return (1 & flags) != 0;
    }

    public boolean getCanRedefine()
    {
        return (2 & flags) != 0;
    }

    public final Object getChecked(String s)
    {
        Object obj = get(s, Location.UNBOUND);
        if (obj == Location.UNBOUND)
        {
            throw new UnboundLocationException((new StringBuilder()).append(s).append(" in ").append(this).toString());
        } else
        {
            return obj;
        }
    }

    public int getFlags()
    {
        return flags;
    }

    public final Object getFunction(Symbol symbol)
    {
        String s = Location.UNBOUND;
        Object obj = get(symbol, EnvironmentKey.FUNCTION, s);
        if (obj == s)
        {
            throw new UnboundLocationException(symbol);
        } else
        {
            return obj;
        }
    }

    public final Object getFunction(Symbol symbol, Object obj)
    {
        return get(symbol, EnvironmentKey.FUNCTION, obj);
    }

    public final Location getLocation(Symbol symbol)
    {
        return getLocation(symbol, null, true);
    }

    public final Location getLocation(Symbol symbol, Object obj)
    {
        return getLocation(symbol, obj, true);
    }

    public final Location getLocation(Object obj, boolean flag)
    {
        boolean flag1 = obj instanceof EnvironmentKey;
        Object obj1 = null;
        if (flag1)
        {
            EnvironmentKey environmentkey = (EnvironmentKey)obj;
            obj = environmentkey.getKeySymbol();
            obj1 = environmentkey.getKeyProperty();
        }
        Symbol symbol;
        if (obj instanceof Symbol)
        {
            symbol = (Symbol)obj;
        } else
        {
            symbol = getSymbol((String)obj);
        }
        return getLocation(symbol, obj1, flag);
    }

    public abstract NamedLocation getLocation(Symbol symbol, Object obj, int i, boolean flag);

    public final NamedLocation getLocation(Symbol symbol, Object obj, boolean flag)
    {
        return getLocation(symbol, obj, symbol.hashCode() ^ System.identityHashCode(obj), flag);
    }

    public Symbol getSymbol(String s)
    {
        return defaultNamespace().getSymbol(s);
    }

    protected abstract boolean hasMoreElements(LocationEnumeration locationenumeration);

    public final boolean isBound(Symbol symbol)
    {
        return isBound(symbol, null);
    }

    public boolean isBound(Symbol symbol, Object obj)
    {
        Location location = lookup(symbol, obj);
        if (location == null)
        {
            return false;
        } else
        {
            return location.isBound();
        }
    }

    public final boolean isLocked()
    {
        return (3 & flags) == 0;
    }

    public final Location lookup(Symbol symbol)
    {
        return getLocation(symbol, null, false);
    }

    public final Location lookup(Symbol symbol, Object obj)
    {
        return getLocation(symbol, obj, false);
    }

    public abstract NamedLocation lookup(Symbol symbol, Object obj, int i);

    public final Object put(Object obj, Object obj1)
    {
        Location location = getLocation(obj, true);
        Object obj2 = location.get(null);
        location.set(obj1);
        return obj2;
    }

    public final Object put(String s, Object obj)
    {
        return put(s, obj);
    }

    public final void put(Symbol symbol, Object obj)
    {
        put(symbol, null, obj);
    }

    public void put(Symbol symbol, Object obj, Object obj1)
    {
        Location location = getLocation(symbol, obj);
        if (location.isConstant())
        {
            define(symbol, obj, obj1);
            return;
        } else
        {
            location.set(obj1);
            return;
        }
    }

    public final void putFunction(Symbol symbol, Object obj)
    {
        put(symbol, EnvironmentKey.FUNCTION, obj);
    }

    public final Object remove(EnvironmentKey environmentkey)
    {
        Symbol symbol = environmentkey.getKeySymbol();
        Object obj = environmentkey.getKeyProperty();
        return remove(symbol, obj, symbol.hashCode() ^ System.identityHashCode(obj));
    }

    public final Object remove(Symbol symbol, Object obj)
    {
        return remove(symbol, obj, symbol.hashCode() ^ System.identityHashCode(obj));
    }

    public Object remove(Symbol symbol, Object obj, int i)
    {
        Location location = unlink(symbol, obj, i);
        if (location == null)
        {
            return null;
        } else
        {
            Object obj1 = location.get(null);
            location.undefine();
            return obj1;
        }
    }

    public final Object remove(Object obj)
    {
        if (obj instanceof EnvironmentKey)
        {
            EnvironmentKey environmentkey = (EnvironmentKey)obj;
            return remove(environmentkey.getKeySymbol(), environmentkey.getKeyProperty());
        }
        Symbol symbol;
        if (obj instanceof Symbol)
        {
            symbol = (Symbol)obj;
        } else
        {
            symbol = getSymbol((String)obj);
        }
        return remove(symbol, null, symbol.hashCode() ^ System.identityHashCode(null));
    }

    public final void remove(Symbol symbol)
    {
        remove(symbol, null, symbol.hashCode());
    }

    public final void removeFunction(Symbol symbol)
    {
        remove(symbol, EnvironmentKey.FUNCTION);
    }

    public void setCanDefine(boolean flag)
    {
        if (flag)
        {
            flags = 1 | flags;
            return;
        } else
        {
            flags = -2 & flags;
            return;
        }
    }

    public void setCanRedefine(boolean flag)
    {
        if (flag)
        {
            flags = 2 | flags;
            return;
        } else
        {
            flags = -3 & flags;
            return;
        }
    }

    public void setFlag(boolean flag, int i)
    {
        if (flag)
        {
            flags = i | flags;
            return;
        } else
        {
            flags = flags & ~i;
            return;
        }
    }

    public final void setIndirectDefines()
    {
        flags = 0x20 | flags;
        ((InheritingEnvironment)this).baseTimestamp = 0x7fffffff;
    }

    public void setLocked()
    {
        flags = -8 & flags;
    }

    public String toString()
    {
        return (new StringBuilder()).append("#<environment ").append(getName()).append('>').toString();
    }

    public String toStringVerbose()
    {
        return toString();
    }

    public Location unlink(Symbol symbol, Object obj, int i)
    {
        throw new RuntimeException("unsupported operation: unlink (aka undefine)");
    }

}
