// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;

import java.io.PrintWriter;

// Referenced classes of package gnu.mapping:
//            PlainLocation, Namespace, ThreadLocation, UnboundLocationException, 
//            Symbol, IndirectableLocation

public abstract class Location
{

    public static final String UNBOUND = new String("(unbound)");

    public Location()
    {
    }

    public static IndirectableLocation make(Symbol symbol)
    {
        PlainLocation plainlocation = new PlainLocation(symbol, null);
        plainlocation.base = null;
        plainlocation.value = UNBOUND;
        return plainlocation;
    }

    public static IndirectableLocation make(String s)
    {
        PlainLocation plainlocation = new PlainLocation(Namespace.EmptyNamespace.getSymbol(s.intern()), null);
        plainlocation.base = null;
        plainlocation.value = UNBOUND;
        return plainlocation;
    }

    public static Location make(Object obj, String s)
    {
        ThreadLocation threadlocation = new ThreadLocation(s);
        threadlocation.setGlobal(obj);
        return threadlocation;
    }

    public boolean entered()
    {
        return false;
    }

    public final Object get()
    {
        String s = UNBOUND;
        Object obj = get(s);
        if (obj == s)
        {
            throw new UnboundLocationException(this);
        } else
        {
            return obj;
        }
    }

    public abstract Object get(Object obj);

    public Location getBase()
    {
        return this;
    }

    public Object getKeyProperty()
    {
        return null;
    }

    public Symbol getKeySymbol()
    {
        return null;
    }

    public final Object getValue()
    {
        return get(null);
    }

    public boolean isBound()
    {
        String s = UNBOUND;
        return get(s) != s;
    }

    public boolean isConstant()
    {
        return false;
    }

    public void print(PrintWriter printwriter)
    {
        printwriter.print("#<location ");
        Symbol symbol = getKeySymbol();
        if (symbol != null)
        {
            printwriter.print(symbol);
        }
        String s = UNBOUND;
        Object obj = get(s);
        if (obj != s)
        {
            printwriter.print(" -> ");
            printwriter.print(obj);
        } else
        {
            printwriter.print("(unbound)");
        }
        printwriter.print('>');
    }

    public abstract void set(Object obj);

    public void setRestore(Object obj)
    {
        set(obj);
    }

    public final Object setValue(Object obj)
    {
        Object obj1 = get(null);
        set(obj);
        return obj1;
    }

    public Object setWithSave(Object obj)
    {
        Object obj1 = get(UNBOUND);
        set(obj);
        return obj1;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append(getClass().getName());
        Symbol symbol = getKeySymbol();
        stringbuffer.append('[');
        if (symbol != null)
        {
            stringbuffer.append(symbol);
            Object obj = getKeyProperty();
            if (obj != null && obj != this)
            {
                stringbuffer.append('/');
                stringbuffer.append(obj);
            }
        }
        stringbuffer.append("]");
        return stringbuffer.toString();
    }

    public void undefine()
    {
        set(UNBOUND);
    }

}
