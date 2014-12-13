// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;


// Referenced classes of package gnu.mapping:
//            Named, Namespace, Symbol

public abstract class PropertySet
    implements Named
{

    public static final Symbol nameKey;
    private Object properties[];

    public PropertySet()
    {
    }

    public static Object[] setProperty(Object aobj[], Object obj, Object obj1)
    {
        Object aobj1[] = aobj;
        if (aobj1 != null) goto _L2; else goto _L1
_L1:
        int i;
        aobj1 = new Object[10];
        aobj = aobj1;
        i = 0;
_L4:
        aobj1[i] = obj;
        aobj1[i + 1] = obj1;
        return aobj;
_L2:
        i = -1;
        int j = aobj1.length;
        do
        {
            if ((j -= 2) < 0)
            {
                break;
            }
            Object obj2 = aobj1[j];
            if (obj2 == obj)
            {
                aobj1[j + 1];
                aobj1[j + 1] = obj1;
                return aobj;
            }
            if (obj2 == null)
            {
                i = j;
            }
        } while (true);
        if (i < 0)
        {
            i = aobj1.length;
            aobj = new Object[i * 2];
            System.arraycopy(((Object) (aobj1)), 0, ((Object) (aobj)), 0, i);
            aobj1 = aobj;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public String getName()
    {
        Object obj = getProperty(nameKey, null);
        if (obj == null)
        {
            return null;
        }
        if (obj instanceof Symbol)
        {
            return ((Symbol)obj).getName();
        } else
        {
            return obj.toString();
        }
    }

    public Object getProperty(Object obj, Object obj1)
    {
label0:
        {
            if (properties == null)
            {
                break label0;
            }
            int i = properties.length;
            do
            {
                if ((i -= 2) < 0)
                {
                    break label0;
                }
            } while (properties[i] != obj);
            obj1 = properties[i + 1];
        }
        return obj1;
    }

    public Object getSymbol()
    {
        return getProperty(nameKey, null);
    }

    public Object removeProperty(Object obj)
    {
        Object aobj[] = properties;
        if (aobj == null)
        {
            return null;
        }
        for (int i = aobj.length; (i -= 2) >= 0;)
        {
            if (aobj[i] == obj)
            {
                Object obj1 = aobj[i + 1];
                aobj[i] = null;
                aobj[i + 1] = null;
                return obj1;
            }
        }

        return null;
    }

    public final void setName(String s)
    {
        setProperty(nameKey, s);
    }

    public void setProperty(Object obj, Object obj1)
    {
        this;
        JVM INSTR monitorenter ;
        properties = setProperty(properties, obj, obj1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void setSymbol(Object obj)
    {
        setProperty(nameKey, obj);
    }

    static 
    {
        nameKey = Namespace.EmptyNamespace.getSymbol("name");
    }
}
