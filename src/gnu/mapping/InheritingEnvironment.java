// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;


// Referenced classes of package gnu.mapping:
//            SimpleEnvironment, Environment, LocationEnumeration, NamedLocation, 
//            IndirectableLocation, SharedLocation, Symbol, Namespace

public class InheritingEnvironment extends SimpleEnvironment
{

    int baseTimestamp;
    Environment inherited[];
    Namespace namespaceMap[];
    int numInherited;
    Object propertyMap[];

    public InheritingEnvironment(String s, Environment environment)
    {
        super(s);
        addParent(environment);
        if (environment instanceof SimpleEnvironment)
        {
            SimpleEnvironment simpleenvironment = (SimpleEnvironment)environment;
            int i = 1 + simpleenvironment.currentTimestamp;
            simpleenvironment.currentTimestamp = i;
            baseTimestamp = i;
            currentTimestamp = i;
        }
    }

    public void addParent(Environment environment)
    {
        if (numInherited != 0) goto _L2; else goto _L1
_L1:
        inherited = new Environment[4];
_L4:
        inherited[numInherited] = environment;
        numInherited = 1 + numInherited;
        return;
_L2:
        if (numInherited <= inherited.length)
        {
            Environment aenvironment[] = new Environment[2 * numInherited];
            System.arraycopy(inherited, 0, aenvironment, 0, numInherited);
            inherited = aenvironment;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public LocationEnumeration enumerateAllLocations()
    {
        LocationEnumeration locationenumeration = new LocationEnumeration(table, 1 << log2Size);
        locationenumeration.env = this;
        if (inherited != null && inherited.length > 0)
        {
            locationenumeration.inherited = inherited[0].enumerateAllLocations();
            locationenumeration.index = 0;
        }
        return locationenumeration;
    }

    public NamedLocation getLocation(Symbol symbol, Object obj, int i, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        NamedLocation namedlocation = lookupDirect(symbol, obj, i);
        if (namedlocation == null) goto _L2; else goto _L1
_L1:
        if (flag) goto _L4; else goto _L3
_L3:
        boolean flag1 = namedlocation.isBound();
        if (!flag1) goto _L2; else goto _L4
_L4:
        NamedLocation namedlocation4 = namedlocation;
_L12:
        this;
        JVM INSTR monitorexit ;
        return namedlocation4;
_L2:
        if ((0x20 & flags) == 0 || !flag) goto _L6; else goto _L5
_L5:
        NamedLocation namedlocation1 = inherited[0].getLocation(symbol, obj, i, true);
_L9:
        if (namedlocation1 == null)
        {
            break MISSING_BLOCK_LABEL_216;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_244;
        }
        namedlocation4 = addUnboundLocation(symbol, obj, i);
        if ((1 & flags) == 0 && namedlocation1.isBound())
        {
            redefineError(symbol, obj, namedlocation4);
        }
        namedlocation4.base = namedlocation1;
        if (namedlocation1.value != IndirectableLocation.INDIRECT_FLUIDS) goto _L8; else goto _L7
_L7:
        namedlocation4.value = namedlocation1.value;
_L10:
        if (namedlocation4 instanceof SharedLocation)
        {
            ((SharedLocation)namedlocation4).timestamp = baseTimestamp;
        }
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
_L6:
        namedlocation1 = lookupInherited(symbol, obj, i);
          goto _L9
_L8:
label0:
        {
            if ((0x10 & flags) == 0)
            {
                break label0;
            }
            namedlocation4.value = IndirectableLocation.DIRECT_ON_SET;
        }
          goto _L10
        namedlocation4.value = null;
          goto _L10
        NamedLocation namedlocation2;
        namedlocation2 = null;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_237;
        }
        NamedLocation namedlocation3 = addUnboundLocation(symbol, obj, i);
        namedlocation2 = namedlocation3;
        namedlocation4 = namedlocation2;
        continue; /* Loop/switch isn't completed */
        namedlocation4 = namedlocation1;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public final int getNumParents()
    {
        return numInherited;
    }

    public final Environment getParent(int i)
    {
        return inherited[i];
    }

    protected boolean hasMoreElements(LocationEnumeration locationenumeration)
    {
        if (locationenumeration.inherited == null) goto _L2; else goto _L1
_L1:
        NamedLocation namedlocation = locationenumeration.nextLoc;
_L6:
        locationenumeration.inherited.nextLoc = namedlocation;
        if (locationenumeration.inherited.hasMoreElements()) goto _L4; else goto _L3
_L3:
        locationenumeration.prevLoc = null;
        locationenumeration.nextLoc = locationenumeration.inherited.nextLoc;
        int i = 1 + locationenumeration.index;
        locationenumeration.index = i;
        if (i != numInherited)
        {
            break; /* Loop/switch isn't completed */
        }
        locationenumeration.inherited = null;
        locationenumeration.bindings = table;
        locationenumeration.index = 1 << log2Size;
_L2:
        return super.hasMoreElements(locationenumeration);
_L4:
        NamedLocation namedlocation1 = locationenumeration.inherited.nextLoc;
        if (lookup(namedlocation1.name, namedlocation1.property) == namedlocation1)
        {
            locationenumeration.nextLoc = namedlocation1;
            return true;
        }
        namedlocation = namedlocation1.next;
        if (true) goto _L6; else goto _L5
_L5:
        locationenumeration.inherited = inherited[locationenumeration.index].enumerateAllLocations();
        if (true) goto _L1; else goto _L7
_L7:
    }

    public NamedLocation lookup(Symbol symbol, Object obj, int i)
    {
        NamedLocation namedlocation = super.lookup(symbol, obj, i);
        if (namedlocation != null && namedlocation.isBound())
        {
            return namedlocation;
        } else
        {
            return lookupInherited(symbol, obj, i);
        }
    }

    public NamedLocation lookupInherited(Symbol symbol, Object obj, int i)
    {
        int j = 0;
_L6:
        Symbol symbol1;
        Object obj1;
        if (j >= numInherited)
        {
            break MISSING_BLOCK_LABEL_216;
        }
        symbol1 = symbol;
        obj1 = obj;
        if (namespaceMap == null || namespaceMap.length <= j * 2) goto _L2; else goto _L1
_L1:
        Namespace namespace;
        Namespace namespace1;
        namespace = namespaceMap[j * 2];
        namespace1 = namespaceMap[1 + j * 2];
        if (namespace == null && namespace1 == null) goto _L2; else goto _L3
_L3:
        if (symbol.getNamespace() == namespace1) goto _L5; else goto _L4
_L4:
        j++;
          goto _L6
_L5:
        symbol1 = Symbol.make(namespace, symbol.getName());
_L2:
        if (propertyMap == null || propertyMap.length <= j * 2)
        {
            break; /* Loop/switch isn't completed */
        }
        Object obj2 = propertyMap[j * 2];
        Object obj3 = propertyMap[1 + j * 2];
        if (obj2 == null && obj3 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (obj != obj3)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj1 = obj2;
        break; /* Loop/switch isn't completed */
        if (true) goto _L4; else goto _L7
_L7:
        NamedLocation namedlocation = inherited[j].lookup(symbol1, obj1, i);
        if (namedlocation == null || !namedlocation.isBound() || (namedlocation instanceof SharedLocation) && ((SharedLocation)namedlocation).timestamp >= baseTimestamp) goto _L4; else goto _L8
_L8:
        return namedlocation;
        return null;
    }

    protected void toStringBase(StringBuffer stringbuffer)
    {
        stringbuffer.append(" baseTs:");
        stringbuffer.append(baseTimestamp);
        for (int i = 0; i < numInherited; i++)
        {
            stringbuffer.append(" base:");
            stringbuffer.append(inherited[i].toStringVerbose());
        }

    }
}
