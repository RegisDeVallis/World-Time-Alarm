// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.acra.collector;

import android.os.Process;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import org.acra.ACRA;
import org.acra.ACRAConfiguration;
import org.acra.util.BoundedLinkedList;

// Referenced classes of package org.acra.collector:
//            Compatibility

class LogCatCollector
{

    private static final int DEFAULT_TAIL_COUNT = 100;

    LogCatCollector()
    {
    }

    public static String collectLogCat(String s)
    {
        String s1;
        BoundedLinkedList boundedlinkedlist;
        String s2;
        int i = Process.myPid();
        boolean flag = ACRA.getConfig().logcatFilterByPid();
        s1 = null;
        if (flag)
        {
            s1 = null;
            if (i > 0)
            {
                s1 = (new StringBuilder()).append(Integer.toString(i)).append("):").toString();
            }
        }
        ArrayList arraylist = new ArrayList();
        arraylist.add("logcat");
        if (s != null)
        {
            arraylist.add("-b");
            arraylist.add(s);
        }
        ArrayList arraylist1 = new ArrayList(Arrays.asList(ACRA.getConfig().logcatArguments()));
        int j = arraylist1.indexOf("-t");
        int k;
        BufferedReader bufferedreader;
        if (j > -1 && j < arraylist1.size())
        {
            k = Integer.parseInt((String)arraylist1.get(j + 1));
            if (Compatibility.getAPILevel() < 8)
            {
                arraylist1.remove(j + 1);
                arraylist1.remove(j);
                arraylist1.add("-d");
            }
        } else
        {
            k = -1;
        }
        if (k <= 0)
        {
            k = 100;
        }
        boundedlinkedlist = new BoundedLinkedList(k);
        arraylist.addAll(arraylist1);
        bufferedreader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec((String[])arraylist.toArray(new String[arraylist.size()])).getInputStream()), 8192);
        Log.d(ACRA.LOG_TAG, "Retrieving logcat output...");
_L5:
        s2 = bufferedreader.readLine();
        if (s2 != null) goto _L2; else goto _L1
_L1:
        return boundedlinkedlist.toString();
_L2:
        if (s1 == null) goto _L4; else goto _L3
_L3:
        if (!s2.contains(s1)) goto _L5; else goto _L4
_L4:
        boundedlinkedlist.add((new StringBuilder()).append(s2).append("\n").toString());
          goto _L5
        IOException ioexception;
        ioexception;
        Log.e(ACRA.LOG_TAG, "LogCatCollector.collectLogCat could not retrieve data.", ioexception);
          goto _L1
    }
}
