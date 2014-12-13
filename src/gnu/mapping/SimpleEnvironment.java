// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Hashtable;
import java.util.Set;

// Referenced classes of package gnu.mapping:
//            Environment, NamedLocation, PlainLocation, UnboundLocationException, 
//            Location, ThreadLocation, InheritingEnvironment, IndirectableLocation, 
//            Symbol, EnvironmentMappings, LocationEnumeration, SharedLocation

public class SimpleEnvironment extends Environment
{

    int currentTimestamp;
    int log2Size;
    private int mask;
    int num_bindings;
    NamedLocation sharedTail;
    NamedLocation table[];

    public SimpleEnvironment()
    {
        this(64);
    }

    public SimpleEnvironment(int i)
    {
        for (log2Size = 4; i > 1 << log2Size; log2Size = 1 + log2Size) { }
        int j = 1 << log2Size;
        table = new NamedLocation[j];
        mask = j - 1;
        sharedTail = new PlainLocation(null, null, this);
    }

    public SimpleEnvironment(String s)
    {
        this();
        setName(s);
    }

    public static Location getCurrentLocation(String s)
    {
        return getCurrent().getLocation(s, true);
    }

    public static Object lookup_global(Symbol symbol)
        throws UnboundLocationException
    {
        Location location = getCurrent().lookup(symbol);
        if (location == null)
        {
            throw new UnboundLocationException(symbol);
        } else
        {
            return location.get();
        }
    }

    NamedLocation addLocation(Symbol symbol, Object obj, int i, Location location)
    {
        if ((location instanceof ThreadLocation) && ((ThreadLocation)location).property == obj)
        {
            location = ((ThreadLocation)location).getLocation();
        }
        NamedLocation namedlocation = lookupDirect(symbol, obj, i);
        if (location == namedlocation)
        {
            return namedlocation;
        }
        boolean flag;
        if (namedlocation != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            namedlocation = addUnboundLocation(symbol, obj, i);
        }
        if ((3 & flags) != 3)
        {
            if (flag)
            {
                flag = namedlocation.isBound();
            }
            if (flag ? (2 & flags) == 0 : (1 & flags) == 0 && location.isBound())
            {
                redefineError(symbol, obj, namedlocation);
            }
        }
        if ((0x20 & flags) != 0)
        {
            namedlocation.base = ((SimpleEnvironment)((InheritingEnvironment)this).getParent(0)).addLocation(symbol, obj, i, location);
        } else
        {
            namedlocation.base = location;
        }
        namedlocation.value = IndirectableLocation.INDIRECT_FLUIDS;
        return namedlocation;
    }

    public NamedLocation addLocation(Symbol symbol, Object obj, Location location)
    {
        return addLocation(symbol, obj, symbol.hashCode() ^ System.identityHashCode(obj), location);
    }

    protected NamedLocation addUnboundLocation(Symbol symbol, Object obj, int i)
    {
        NamedLocation namedlocation = newEntry(symbol, obj, i & mask);
        namedlocation.base = null;
        namedlocation.value = Location.UNBOUND;
        return namedlocation;
    }

    public NamedLocation define(Symbol symbol, Object obj, int i, Object obj1)
    {
        int j = i & mask;
        NamedLocation namedlocation = table[j];
        do
        {
            if (namedlocation == null)
            {
                NamedLocation namedlocation1 = newEntry(symbol, obj, j);
                namedlocation1.set(obj1);
                return namedlocation1;
            }
            if (namedlocation.matches(symbol, obj))
            {
                if (namedlocation.isBound() ? !getCanDefine() : !getCanRedefine())
                {
                    redefineError(symbol, obj, namedlocation);
                }
                namedlocation.base = null;
                namedlocation.value = obj1;
                return namedlocation;
            }
            namedlocation = namedlocation.next;
        } while (true);
    }

    public void define(Symbol symbol, Object obj, Object obj1)
    {
        define(symbol, obj, symbol.hashCode() ^ System.identityHashCode(obj), obj1);
    }

    public Set entrySet()
    {
        return new EnvironmentMappings(this);
    }

    public LocationEnumeration enumerateAllLocations()
    {
        return enumerateLocations();
    }

    public LocationEnumeration enumerateLocations()
    {
        LocationEnumeration locationenumeration = new LocationEnumeration(table, 1 << log2Size);
        locationenumeration.env = this;
        return locationenumeration;
    }

    public NamedLocation getLocation(Symbol symbol, Object obj, int i, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        NamedLocation namedlocation = lookup(symbol, obj, i);
        NamedLocation namedlocation1 = namedlocation;
        if (namedlocation1 == null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return namedlocation1;
_L2:
        if (!flag)
        {
            namedlocation1 = null;
            continue; /* Loop/switch isn't completed */
        }
        NamedLocation namedlocation2 = addUnboundLocation(symbol, obj, i);
        namedlocation1 = namedlocation2;
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    protected boolean hasMoreElements(LocationEnumeration locationenumeration)
    {
        do
        {
            if (locationenumeration.nextLoc == null)
            {
                locationenumeration.prevLoc = null;
                int i = -1 + locationenumeration.index;
                locationenumeration.index = i;
                if (i < 0)
                {
                    return false;
                }
                locationenumeration.nextLoc = locationenumeration.bindings[locationenumeration.index];
                if (locationenumeration.nextLoc == null)
                {
                    continue;
                }
            }
            if (locationenumeration.nextLoc.name == null)
            {
                locationenumeration.nextLoc = locationenumeration.nextLoc.next;
            } else
            {
                return true;
            }
        } while (true);
    }

    public NamedLocation lookup(Symbol symbol, Object obj, int i)
    {
        return lookupDirect(symbol, obj, i);
    }

    public NamedLocation lookupDirect(Symbol symbol, Object obj, int i)
    {
        int j = i & mask;
        for (NamedLocation namedlocation = table[j]; namedlocation != null; namedlocation = namedlocation.next)
        {
            if (namedlocation.matches(symbol, obj))
            {
                return namedlocation;
            }
        }

        return null;
    }

    NamedLocation newEntry(Symbol symbol, Object obj, int i)
    {
        NamedLocation namedlocation = newLocation(symbol, obj);
        NamedLocation namedlocation1 = table[i];
        if (namedlocation1 == null)
        {
            namedlocation1 = sharedTail;
        }
        namedlocation.next = namedlocation1;
        table[i] = namedlocation;
        num_bindings = 1 + num_bindings;
        if (num_bindings >= table.length)
        {
            rehash();
        }
        return namedlocation;
    }

    NamedLocation newLocation(Symbol symbol, Object obj)
    {
        if ((8 & flags) != 0)
        {
            return new SharedLocation(symbol, obj, currentTimestamp);
        } else
        {
            return new PlainLocation(symbol, obj);
        }
    }

    public void put(Symbol symbol, Object obj, Object obj1)
    {
        boolean flag;
        NamedLocation namedlocation;
        if ((4 & flags) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        namedlocation = getLocation(symbol, obj, flag);
        if (namedlocation == null)
        {
            throw new UnboundLocationException(symbol);
        }
        if (namedlocation.isConstant())
        {
            throw new IllegalStateException((new StringBuilder()).append("attempt to modify read-only location: ").append(symbol).append(" in ").append(this).append(" loc:").append(namedlocation).toString());
        } else
        {
            namedlocation.set(obj1);
            return;
        }
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        setSymbol(objectinput.readObject());
    }

    public Object readResolve()
        throws ObjectStreamException
    {
        String s = getName();
        Environment environment = (Environment)envTable.get(s);
        if (environment != null)
        {
            return environment;
        } else
        {
            envTable.put(s, this);
            return this;
        }
    }

    protected void redefineError(Symbol symbol, Object obj, Location location)
    {
        throw new IllegalStateException((new StringBuilder()).append("prohibited define/redefine of ").append(symbol).append(" in ").append(this).toString());
    }

    void rehash()
    {
        NamedLocation anamedlocation[] = table;
        int i = anamedlocation.length;
        int j = i * 2;
        NamedLocation anamedlocation1[] = new NamedLocation[j];
        int k = j - 1;
        for (int l = i; --l >= 0;)
        {
            NamedLocation namedlocation = anamedlocation[l];
            while (namedlocation != null && namedlocation != sharedTail) 
            {
                NamedLocation namedlocation1 = namedlocation.next;
                Symbol symbol = namedlocation.name;
                Object obj = namedlocation.property;
                int i1 = k & (symbol.hashCode() ^ System.identityHashCode(obj));
                NamedLocation namedlocation2 = anamedlocation1[i1];
                if (namedlocation2 == null)
                {
                    namedlocation2 = sharedTail;
                }
                namedlocation.next = namedlocation2;
                anamedlocation1[i1] = namedlocation;
                namedlocation = namedlocation1;
            }
        }

        table = anamedlocation1;
        log2Size = 1 + log2Size;
        mask = k;
    }

    public int size()
    {
        return num_bindings;
    }

    protected void toStringBase(StringBuffer stringbuffer)
    {
    }

    public String toStringVerbose()
    {
        StringBuffer stringbuffer = new StringBuffer();
        toStringBase(stringbuffer);
        return (new StringBuilder()).append("#<environment ").append(getName()).append(" num:").append(num_bindings).append(" ts:").append(currentTimestamp).append(stringbuffer).append('>').toString();
    }

    public Location unlink(Symbol symbol, Object obj, int i)
    {
        int j = i & mask;
        NamedLocation namedlocation = null;
        NamedLocation namedlocation2;
        for (NamedLocation namedlocation1 = table[j]; namedlocation1 != null; namedlocation1 = namedlocation2)
        {
            namedlocation2 = namedlocation1.next;
            if (namedlocation1.matches(symbol, obj))
            {
                if (!getCanRedefine())
                {
                    redefineError(symbol, obj, namedlocation1);
                }
                if (namedlocation == null)
                {
                    table[j] = namedlocation2;
                } else
                {
                    namedlocation.next = namedlocation1;
                }
                num_bindings = -1 + num_bindings;
                return namedlocation1;
            }
            namedlocation = namedlocation1;
        }

        return null;
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(getSymbol());
    }
}
