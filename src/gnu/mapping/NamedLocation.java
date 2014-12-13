// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;


// Referenced classes of package gnu.mapping:
//            IndirectableLocation, EnvironmentKey, Symbol, Environment, 
//            Location, ThreadLocation, SharedLocation

public abstract class NamedLocation extends IndirectableLocation
    implements java.util.Map.Entry, EnvironmentKey
{

    final Symbol name;
    NamedLocation next;
    final Object property;

    public NamedLocation(NamedLocation namedlocation)
    {
        name = namedlocation.name;
        property = namedlocation.property;
    }

    public NamedLocation(Symbol symbol, Object obj)
    {
        name = symbol;
        property = obj;
    }

    public boolean entered()
    {
        return next != null;
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof NamedLocation) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        NamedLocation namedlocation = (NamedLocation)obj;
        if (name != null) goto _L4; else goto _L3
_L3:
        if (namedlocation.name != null)
        {
            continue; /* Loop/switch isn't completed */
        }
_L6:
        Object obj1;
        Object obj2;
        if (property == namedlocation.property)
        {
            obj1 = getValue();
            obj2 = namedlocation.getValue();
            if (obj1 == obj2)
            {
                return true;
            }
            continue; /* Loop/switch isn't completed */
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (name.equals(namedlocation.name)) goto _L6; else goto _L5
_L5:
        break; /* Loop/switch isn't completed */
        if (true) goto _L1; else goto _L7
_L7:
        return false;
        if (obj1 == null || obj2 == null) goto _L1; else goto _L8
_L8:
        return obj1.equals(obj2);
    }

    public Environment getEnvironment()
    {
        for (NamedLocation namedlocation = this; namedlocation != null; namedlocation = namedlocation.next)
        {
            if (namedlocation.name != null)
            {
                continue;
            }
            Environment environment = (Environment)namedlocation.value;
            if (environment != null)
            {
                return environment;
            }
        }

        return super.getEnvironment();
    }

    public final Object getKey()
    {
        if (property == null)
        {
            this = name;
        }
        return this;
    }

    public final Object getKeyProperty()
    {
        return property;
    }

    public final Symbol getKeySymbol()
    {
        return name;
    }

    public int hashCode()
    {
        int i = name.hashCode() ^ System.identityHashCode(property);
        Object obj = getValue();
        if (obj != null)
        {
            i ^= obj.hashCode();
        }
        return i;
    }

    public final boolean matches(EnvironmentKey environmentkey)
    {
        return Symbol.equals(environmentkey.getKeySymbol(), name) && environmentkey.getKeyProperty() == property;
    }

    public final boolean matches(Symbol symbol, Object obj)
    {
        return Symbol.equals(symbol, name) && obj == property;
    }

    public void setRestore(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        if (value != INDIRECT_FLUIDS) goto _L2; else goto _L1
_L1:
        base.setRestore(obj);
_L3:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (!(obj instanceof Location))
        {
            break MISSING_BLOCK_LABEL_51;
        }
        value = null;
        base = (Location)obj;
          goto _L3
        Exception exception;
        exception;
        throw exception;
        value = obj;
        base = null;
          goto _L3
    }

    public Object setWithSave(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        if (value != INDIRECT_FLUIDS) goto _L2; else goto _L1
_L1:
        Object obj2 = base.setWithSave(obj);
        Object obj1 = obj2;
_L4:
        this;
        JVM INSTR monitorexit ;
        return obj1;
_L2:
        ThreadLocation threadlocation = ThreadLocation.makeAnonymous(name);
        threadlocation.global.base = base;
        threadlocation.global.value = value;
        setAlias(threadlocation);
        NamedLocation namedlocation = threadlocation.getLocation();
        namedlocation.value = obj;
        namedlocation.base = null;
        obj1 = threadlocation.global;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }
}
