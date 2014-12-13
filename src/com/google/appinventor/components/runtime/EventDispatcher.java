// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.appinventor.components.runtime:
//            HandlesEventDispatching, Component

public class EventDispatcher
{
    private static final class EventClosure
    {

        private final String componentId;
        private final String eventName;

        public boolean equals(Object obj)
        {
            if (this != obj)
            {
                if (obj == null || getClass() != obj.getClass())
                {
                    return false;
                }
                EventClosure eventclosure = (EventClosure)obj;
                if (!componentId.equals(eventclosure.componentId))
                {
                    return false;
                }
                if (!eventName.equals(eventclosure.eventName))
                {
                    return false;
                }
            }
            return true;
        }

        public int hashCode()
        {
            return 31 * eventName.hashCode() + componentId.hashCode();
        }



        private EventClosure(String s, String s1)
        {
            componentId = s;
            eventName = s1;
        }

    }

    private static final class EventRegistry
    {

        private final HandlesEventDispatching dispatchDelegate;
        private final HashMap eventClosuresMap = new HashMap();


        EventRegistry(HandlesEventDispatching handleseventdispatching)
        {
            dispatchDelegate = handleseventdispatching;
        }
    }


    private static final boolean DEBUG;
    private static final Map mapDispatchDelegateToEventRegistry = new HashMap();

    private EventDispatcher()
    {
    }

    private static transient boolean delegateDispatchEvent(HandlesEventDispatching handleseventdispatching, Set set, Component component, Object aobj[])
    {
        boolean flag = false;
        Iterator iterator = set.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            EventClosure eventclosure = (EventClosure)iterator.next();
            if (handleseventdispatching.dispatchEvent(component, eventclosure.componentId, eventclosure.eventName, aobj))
            {
                flag = true;
            }
        } while (true);
        return flag;
    }

    public static transient boolean dispatchEvent(Component component, String s, Object aobj[])
    {
        HandlesEventDispatching handleseventdispatching = component.getDispatchDelegate();
        boolean flag = handleseventdispatching.canDispatchEvent(component, s);
        boolean flag1 = false;
        if (flag)
        {
            Set set = (Set)getEventRegistry(handleseventdispatching).eventClosuresMap.get(s);
            flag1 = false;
            if (set != null)
            {
                int i = set.size();
                flag1 = false;
                if (i > 0)
                {
                    flag1 = delegateDispatchEvent(handleseventdispatching, set, component, aobj);
                }
            }
        }
        return flag1;
    }

    private static EventRegistry getEventRegistry(HandlesEventDispatching handleseventdispatching)
    {
        EventRegistry eventregistry = (EventRegistry)mapDispatchDelegateToEventRegistry.get(handleseventdispatching);
        if (eventregistry == null)
        {
            eventregistry = new EventRegistry(handleseventdispatching);
            mapDispatchDelegateToEventRegistry.put(handleseventdispatching, eventregistry);
        }
        return eventregistry;
    }

    public static String makeFullEventName(String s, String s1)
    {
        return (new StringBuilder()).append(s).append('$').append(s1).toString();
    }

    public static void registerEventForDelegation(HandlesEventDispatching handleseventdispatching, String s, String s1)
    {
        EventRegistry eventregistry = getEventRegistry(handleseventdispatching);
        Object obj = (Set)eventregistry.eventClosuresMap.get(s1);
        if (obj == null)
        {
            obj = new HashSet();
            eventregistry.eventClosuresMap.put(s1, obj);
        }
        ((Set) (obj)).add(new EventClosure(s, s1));
    }

    public static void removeDispatchDelegate(HandlesEventDispatching handleseventdispatching)
    {
        EventRegistry eventregistry = removeEventRegistry(handleseventdispatching);
        if (eventregistry != null)
        {
            eventregistry.eventClosuresMap.clear();
        }
    }

    private static EventRegistry removeEventRegistry(HandlesEventDispatching handleseventdispatching)
    {
        return (EventRegistry)mapDispatchDelegateToEventRegistry.remove(handleseventdispatching);
    }

    public static void unregisterAllEventsForDelegation()
    {
        for (Iterator iterator = mapDispatchDelegateToEventRegistry.values().iterator(); iterator.hasNext(); ((EventRegistry)iterator.next()).eventClosuresMap.clear()) { }
    }

    public static void unregisterEventForDelegation(HandlesEventDispatching handleseventdispatching, String s, String s1)
    {
        Set set = (Set)getEventRegistry(handleseventdispatching).eventClosuresMap.get(s1);
        if (set != null && !set.isEmpty())
        {
            HashSet hashset = new HashSet();
            Iterator iterator = set.iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                EventClosure eventclosure = (EventClosure)iterator.next();
                if (eventclosure.componentId.equals(s))
                {
                    hashset.add(eventclosure);
                }
            } while (true);
            Iterator iterator1 = hashset.iterator();
            while (iterator1.hasNext()) 
            {
                set.remove((EventClosure)iterator1.next());
            }
        }
    }

}
