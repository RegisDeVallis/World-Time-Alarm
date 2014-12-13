// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.runtime.collect.Maps;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryLeakUtil
{

    private static final String LOG_TAG = "MemoryLeakUtil";
    private static final Map TRACKED_OBJECTS = Maps.newTreeMap();
    private static final AtomicInteger prefixGenerator = new AtomicInteger(0);

    private MemoryLeakUtil()
    {
    }

    public static void checkAllTrackedObjects(boolean flag, boolean flag1)
    {
        Log.i("MemoryLeakUtil", "Checking Tracked Objects ----------------------------------------");
        System.gc();
        int i = 0;
        int j = 0;
        Iterator iterator = TRACKED_OBJECTS.entrySet().iterator();
        do
        {
            if (iterator.hasNext())
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                String s = (String)entry.getKey();
                Object obj = ((WeakReference)entry.getValue()).get();
                if (obj != null)
                {
                    i++;
                } else
                {
                    j++;
                    if (flag1)
                    {
                        iterator.remove();
                    }
                }
                if (flag)
                {
                    String s1 = s.substring(1 + s.indexOf("_"));
                    StringBuilder stringbuilder = (new StringBuilder()).append("Object with tag ").append(s1).append(" has ");
                    String s2;
                    if (obj != null)
                    {
                        s2 = "not ";
                    } else
                    {
                        s2 = "";
                    }
                    Log.i("MemoryLeakUtil", stringbuilder.append(s2).append("been garbage collected.").toString());
                }
                continue;
            }
            Log.i("MemoryLeakUtil", (new StringBuilder()).append("summary: collected ").append(j).toString());
            Log.i("MemoryLeakUtil", (new StringBuilder()).append("summary: remaining ").append(i).toString());
            Log.i("MemoryLeakUtil", "-----------------------------------------------------------------");
            return;
        } while (true);
    }

    public static boolean isTrackedObjectCollected(String s, boolean flag)
    {
        System.gc();
        WeakReference weakreference = (WeakReference)TRACKED_OBJECTS.get(s);
        if (weakreference != null)
        {
            Object obj = weakreference.get();
            String s1 = s.substring(1 + s.indexOf("_"));
            StringBuilder stringbuilder = (new StringBuilder()).append("Object with tag ").append(s1).append(" has ");
            String s2;
            if (obj != null)
            {
                s2 = "not ";
            } else
            {
                s2 = "";
            }
            Log.i("MemoryLeakUtil", stringbuilder.append(s2).append("been garbage collected.").toString());
            if (flag && obj == null)
            {
                TRACKED_OBJECTS.remove(s);
            }
            return obj == null;
        } else
        {
            throw new IllegalArgumentException("key not found");
        }
    }

    public static String trackObject(String s, Object obj)
    {
        String s1;
        if (s == null)
        {
            s1 = (new StringBuilder()).append(prefixGenerator.incrementAndGet()).append("_").toString();
        } else
        {
            s1 = (new StringBuilder()).append(prefixGenerator.incrementAndGet()).append("_").append(s).toString();
        }
        TRACKED_OBJECTS.put(s1, new WeakReference(obj));
        return s1;
    }

}
