// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;

import gnu.lists.LList;
import gnu.lists.Pair;

// Referenced classes of package gnu.mapping:
//            Location, Environment, Symbol, Namespace

public class PropertyLocation extends Location
{

    Pair pair;

    public PropertyLocation()
    {
    }

    public static Object getProperty(Object obj, Object obj1, Object obj2)
    {
        return getProperty(obj, obj1, obj2, Environment.getCurrent());
    }

    public static Object getProperty(Object obj, Object obj1, Object obj2, Environment environment)
    {
label0:
        {
            if (!(obj instanceof Symbol))
            {
                if (!(obj instanceof String))
                {
                    break label0;
                }
                obj = Namespace.getDefaultSymbol((String)obj);
            }
            return environment.get((Symbol)obj, obj1, obj2);
        }
        return plistGet(environment.get(Symbol.PLIST, obj, LList.Empty), obj1, obj2);
    }

    public static Object getPropertyList(Object obj)
    {
        return Environment.getCurrent().get(Symbol.PLIST, obj, LList.Empty);
    }

    public static Object getPropertyList(Object obj, Environment environment)
    {
        return environment.get(Symbol.PLIST, obj, LList.Empty);
    }

    public static Object plistGet(Object obj, Object obj1, Object obj2)
    {
        do
        {
            if (!(obj instanceof Pair))
            {
                break;
            }
            Pair pair1 = (Pair)obj;
            if (pair1.getCar() != obj1)
            {
                continue;
            }
            obj2 = ((Pair)pair1.getCdr()).getCar();
            break;
        } while (true);
        return obj2;
    }

    public static Object plistPut(Object obj, Object obj1, Object obj2)
    {
        Pair pair2;
        for (Object obj3 = obj; obj3 instanceof Pair; obj3 = pair2.getCdr())
        {
            Pair pair1 = (Pair)obj3;
            pair2 = (Pair)pair1.getCdr();
            if (pair1.getCar() == obj1)
            {
                pair2.setCar(obj2);
                return obj;
            }
        }

        return new Pair(obj1, new Pair(obj2, obj));
    }

    public static Object plistRemove(Object obj, Object obj1)
    {
        Pair pair1 = null;
        Object obj2 = obj;
        do
        {
            Pair pair3;
label0:
            {
label1:
                {
                    if (obj2 instanceof Pair)
                    {
                        Pair pair2 = (Pair)obj2;
                        pair3 = (Pair)pair2.getCdr();
                        obj2 = pair3.getCdr();
                        if (pair2.getCar() != obj1)
                        {
                            break label0;
                        }
                        if (pair1 != null)
                        {
                            break label1;
                        }
                        obj = obj2;
                    }
                    return obj;
                }
                pair1.setCdr(obj2);
                return obj;
            }
            pair1 = pair3;
        } while (true);
    }

    public static void putProperty(Object obj, Object obj1, Object obj2)
    {
        putProperty(obj, obj1, obj2, Environment.getCurrent());
    }

    public static void putProperty(Object obj, Object obj1, Object obj2, Environment environment)
    {
label0:
        {
label1:
            {
                if (!(obj instanceof Symbol))
                {
                    if (!(obj instanceof String))
                    {
                        break label1;
                    }
                    obj = Namespace.getDefaultSymbol((String)obj);
                }
                Location location = environment.lookup((Symbol)obj, obj1);
                if (location != null)
                {
                    Location location2 = location.getBase();
                    if (location2 instanceof PropertyLocation)
                    {
                        ((PropertyLocation)location2).set(obj2);
                        return;
                    }
                }
                break label0;
            }
            Location location3 = environment.getLocation(Symbol.PLIST, obj);
            location3.set(plistPut(location3.get(LList.Empty), obj1, obj2));
            return;
        }
        Location location1 = environment.getLocation(Symbol.PLIST, obj);
        Pair pair1 = new Pair(obj2, location1.get(LList.Empty));
        location1.set(new Pair(obj1, pair1));
        PropertyLocation propertylocation = new PropertyLocation();
        propertylocation.pair = pair1;
        environment.addLocation((Symbol)obj, obj1, propertylocation);
    }

    public static boolean removeProperty(Object obj, Object obj1)
    {
        return removeProperty(obj, obj1, Environment.getCurrent());
    }

    public static boolean removeProperty(Object obj, Object obj1, Environment environment)
    {
        Location location = environment.lookup(Symbol.PLIST, obj);
        if (location != null) goto _L2; else goto _L1
_L1:
        Object obj2;
        return false;
_L2:
        if (!((obj2 = location.get(LList.Empty)) instanceof Pair)) goto _L1; else goto _L3
_L3:
        Pair pair1;
        Pair pair2;
        pair1 = (Pair)obj2;
        pair2 = null;
_L5:
        if (pair1.getCar() == obj1)
        {
            Object obj4 = ((Pair)pair1.getCdr()).getCdr();
            Object obj3;
            if (pair2 == null)
            {
                location.set(obj4);
            } else
            {
                pair2.setCdr(obj4);
            }
            if (obj instanceof Symbol)
            {
                environment.remove((Symbol)obj, obj1);
            }
            return true;
        }
        obj3 = pair1.getCdr();
        if (!(obj3 instanceof Pair)) goto _L1; else goto _L4
_L4:
        pair2 = pair1;
        pair1 = (Pair)obj3;
          goto _L5
    }

    public static void setPropertyList(Object obj, Object obj1)
    {
        setPropertyList(obj, obj1, Environment.getCurrent());
    }

    public static void setPropertyList(Object obj, Object obj1, Environment environment)
    {
        environment;
        JVM INSTR monitorenter ;
        Location location = environment.lookup(Symbol.PLIST, obj);
        if (!(obj instanceof Symbol)) goto _L2; else goto _L1
_L1:
        Symbol symbol;
        Object obj2;
        symbol = (Symbol)obj;
        obj2 = location.get(LList.Empty);
_L6:
        if (obj2 instanceof Pair) goto _L4; else goto _L3
_L3:
        Object obj3 = obj1;
_L7:
        if (obj3 instanceof Pair) goto _L5; else goto _L2
_L2:
        location.set(obj1);
        environment;
        JVM INSTR monitorexit ;
        return;
_L4:
        Pair pair3 = (Pair)obj2;
        Object obj5 = pair3.getCar();
        if (plistGet(obj1, obj5, null) != null)
        {
            environment.remove(symbol, obj5);
        }
        obj2 = ((Pair)pair3.getCdr()).getCdr();
          goto _L6
_L5:
        Pair pair1;
        Object obj4;
        Location location1;
        pair1 = (Pair)obj3;
        obj4 = pair1.getCar();
        location1 = environment.lookup(symbol, obj4);
        if (location1 == null)
        {
            break MISSING_BLOCK_LABEL_190;
        }
        PropertyLocation propertylocation;
        Location location2 = location1.getBase();
        if (!(location2 instanceof PropertyLocation))
        {
            break MISSING_BLOCK_LABEL_190;
        }
        propertylocation = (PropertyLocation)location2;
_L8:
        Pair pair2 = (Pair)pair1.getCdr();
        propertylocation.pair = pair2;
        obj3 = pair2.getCdr();
          goto _L7
        propertylocation = new PropertyLocation();
        environment.addLocation(symbol, obj4, propertylocation);
          goto _L8
        Exception exception;
        exception;
        environment;
        JVM INSTR monitorexit ;
        throw exception;
          goto _L6
    }

    public final Object get(Object obj)
    {
        return pair.getCar();
    }

    public boolean isBound()
    {
        return true;
    }

    public final void set(Object obj)
    {
        pair.setCar(obj);
    }
}
