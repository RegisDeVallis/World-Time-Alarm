// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.acra.collector;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.acra.ACRA;
import org.acra.ACRAConfiguration;

final class SharedPreferencesCollector
{

    SharedPreferencesCollector()
    {
    }

    public static String collect(Context context)
    {
        StringBuilder stringbuilder = new StringBuilder();
        TreeMap treemap = new TreeMap();
        treemap.put("default", PreferenceManager.getDefaultSharedPreferences(context));
        String as[] = ACRA.getConfig().additionalSharedPreferences();
        if (as != null)
        {
            int i = as.length;
            for (int j = 0; j < i; j++)
            {
                String s2 = as[j];
                treemap.put(s2, context.getSharedPreferences(s2, 0));
            }

        }
        Iterator iterator = treemap.keySet().iterator();
        while (iterator.hasNext()) 
        {
            String s = (String)iterator.next();
            stringbuilder.append(s).append("\n");
            SharedPreferences sharedpreferences = (SharedPreferences)treemap.get(s);
            if (sharedpreferences != null)
            {
                Map map = sharedpreferences.getAll();
                if (map != null && map.size() > 0)
                {
                    Iterator iterator1 = map.keySet().iterator();
                    do
                    {
                        if (!iterator1.hasNext())
                        {
                            break;
                        }
                        String s1 = (String)iterator1.next();
                        if (!filteredKey(s1))
                        {
                            if (map.get(s1) != null)
                            {
                                stringbuilder.append(s1).append("=").append(map.get(s1).toString()).append("\n");
                            } else
                            {
                                stringbuilder.append(s1).append("=").append("null\n");
                            }
                        }
                    } while (true);
                } else
                {
                    stringbuilder.append("empty\n");
                }
            } else
            {
                stringbuilder.append("null\n");
            }
            stringbuilder.append("\n");
        }
        return stringbuilder.toString();
    }

    private static boolean filteredKey(String s)
    {
        String as[] = ACRA.getConfig().excludeMatchingSharedPreferencesKeys();
        if (as.length < 0)
        {
            return s.matches(as[0]);
        } else
        {
            return false;
        }
    }
}
