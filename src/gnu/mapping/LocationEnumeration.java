// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;

import java.util.Enumeration;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Referenced classes of package gnu.mapping:
//            SimpleEnvironment, NamedLocation, Location

public class LocationEnumeration
    implements Iterator, Enumeration
{

    NamedLocation bindings[];
    SimpleEnvironment env;
    int index;
    LocationEnumeration inherited;
    NamedLocation nextLoc;
    NamedLocation prevLoc;

    public LocationEnumeration(SimpleEnvironment simpleenvironment)
    {
        this(simpleenvironment.table, 1 << simpleenvironment.log2Size);
    }

    public LocationEnumeration(NamedLocation anamedlocation[], int i)
    {
        bindings = anamedlocation;
        index = i;
    }

    public boolean hasMoreElements()
    {
        return env.hasMoreElements(this);
    }

    public boolean hasNext()
    {
        return hasMoreElements();
    }

    public Location next()
    {
        return nextElement();
    }

    public volatile Object next()
    {
        return next();
    }

    public Location nextElement()
    {
        return nextLocation();
    }

    public volatile Object nextElement()
    {
        return nextElement();
    }

    public Location nextLocation()
    {
        if (nextLoc == null && !hasMoreElements())
        {
            throw new NoSuchElementException();
        }
        NamedLocation _tmp = prevLoc;
        if (prevLoc == null)
        {
            NamedLocation namedlocation1 = bindings[index];
            if (nextLoc != namedlocation1)
            {
                prevLoc = namedlocation1;
            }
        }
        for (; prevLoc != null && prevLoc.next != nextLoc; prevLoc = prevLoc.next) { }
        NamedLocation namedlocation = nextLoc;
        nextLoc = nextLoc.next;
        return namedlocation;
    }

    public void remove()
    {
        NamedLocation namedlocation;
        if (prevLoc != null)
        {
            namedlocation = prevLoc.next;
        } else
        {
            namedlocation = bindings[index];
        }
        if (namedlocation == null || namedlocation.next != nextLoc)
        {
            throw new IllegalStateException();
        }
        namedlocation.next = null;
        SimpleEnvironment simpleenvironment;
        if (prevLoc != null)
        {
            prevLoc.next = nextLoc;
        } else
        {
            bindings[index] = nextLoc;
        }
        simpleenvironment = env;
        simpleenvironment.num_bindings = -1 + simpleenvironment.num_bindings;
    }
}
