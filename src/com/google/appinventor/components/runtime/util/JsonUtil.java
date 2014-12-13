// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import gnu.lists.FString;
import gnu.math.IntFraction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

// Referenced classes of package com.google.appinventor.components.runtime.util:
//            YailList

public class JsonUtil
{

    private JsonUtil()
    {
    }

    public static Object convertJsonItem(Object obj)
        throws JSONException
    {
        if (obj == null)
        {
            obj = "null";
        } else
        {
            if (obj instanceof JSONObject)
            {
                return getListFromJsonObject((JSONObject)obj);
            }
            if (obj instanceof JSONArray)
            {
                return getListFromJsonArray((JSONArray)obj);
            }
            if (obj.equals(Boolean.FALSE) || (obj instanceof String) && ((String)obj).equalsIgnoreCase("false"))
            {
                return Boolean.valueOf(false);
            }
            if (obj.equals(Boolean.TRUE) || (obj instanceof String) && ((String)obj).equalsIgnoreCase("true"))
            {
                return Boolean.valueOf(true);
            }
            if (!(obj instanceof Number))
            {
                return obj.toString();
            }
        }
        return obj;
    }

    public static String getJsonRepresentation(Object obj)
        throws JSONException
    {
        if (obj == null || obj.equals(null))
        {
            return "null";
        }
        if (obj instanceof FString)
        {
            return JSONObject.quote(obj.toString());
        }
        if (obj instanceof YailList)
        {
            return ((YailList)obj).toJSONString();
        }
        if (obj instanceof IntFraction)
        {
            return JSONObject.numberToString(Double.valueOf(((IntFraction)obj).doubleValue()));
        }
        if (obj instanceof Number)
        {
            return JSONObject.numberToString((Number)obj);
        }
        if (obj instanceof Boolean)
        {
            return obj.toString();
        }
        if (obj.getClass().isArray())
        {
            StringBuilder stringbuilder = new StringBuilder();
            stringbuilder.append("[");
            String s = "";
            Object aobj[] = (Object[])(Object[])obj;
            int i = aobj.length;
            for (int j = 0; j < i; j++)
            {
                Object obj1 = aobj[j];
                stringbuilder.append(s).append(getJsonRepresentation(obj1));
                s = ",";
            }

            stringbuilder.append("]");
            return stringbuilder.toString();
        } else
        {
            return JSONObject.quote(obj.toString());
        }
    }

    public static List getListFromJsonArray(JSONArray jsonarray)
        throws JSONException
    {
        ArrayList arraylist = new ArrayList();
        for (int i = 0; i < jsonarray.length(); i++)
        {
            arraylist.add(convertJsonItem(jsonarray.get(i)));
        }

        return arraylist;
    }

    public static List getListFromJsonObject(JSONObject jsonobject)
        throws JSONException
    {
        ArrayList arraylist = new ArrayList();
        Iterator iterator = jsonobject.keys();
        ArrayList arraylist1 = new ArrayList();
        for (; iterator.hasNext(); arraylist1.add(iterator.next())) { }
        Collections.sort(arraylist1);
        ArrayList arraylist2;
        for (Iterator iterator1 = arraylist1.iterator(); iterator1.hasNext(); arraylist.add(arraylist2))
        {
            String s = (String)iterator1.next();
            arraylist2 = new ArrayList();
            arraylist2.add(s);
            arraylist2.add(convertJsonItem(jsonobject.get(s)));
        }

        return arraylist;
    }

    public static Object getObjectFromJson(String s)
        throws JSONException
    {
        Object obj;
        if (s == null || s.equals(""))
        {
            obj = "";
        } else
        {
            obj = (new JSONTokener(s)).nextValue();
            if (obj == null || obj.equals(null))
            {
                return null;
            }
            if (!(obj instanceof String) && !(obj instanceof Number) && !(obj instanceof Boolean))
            {
                if (obj instanceof JSONArray)
                {
                    return getListFromJsonArray((JSONArray)obj);
                }
                if (obj instanceof JSONObject)
                {
                    return getListFromJsonObject((JSONObject)obj);
                } else
                {
                    throw new JSONException("Invalid JSON string.");
                }
            }
        }
        return obj;
    }

    public static List getStringListFromJsonArray(JSONArray jsonarray)
        throws JSONException
    {
        ArrayList arraylist = new ArrayList();
        for (int i = 0; i < jsonarray.length(); i++)
        {
            arraylist.add(jsonarray.getString(i));
        }

        return arraylist;
    }
}
