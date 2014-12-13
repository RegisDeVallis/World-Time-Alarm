// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.acra.sender;

import android.net.Uri;
import android.util.Log;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.acra.ACRA;
import org.acra.ACRAConfiguration;
import org.acra.ReportField;
import org.acra.collector.CrashReportData;
import org.acra.util.HttpRequest;

// Referenced classes of package org.acra.sender:
//            ReportSender, ReportSenderException

public class HttpPostSender
    implements ReportSender
{

    private final Uri mFormUri;
    private final Map mMapping;

    public HttpPostSender(String s, Map map)
    {
        mFormUri = Uri.parse(s);
        mMapping = map;
    }

    public HttpPostSender(Map map)
    {
        mFormUri = null;
        mMapping = map;
    }

    private static boolean isNull(String s)
    {
        return s == null || "ACRA-NULL-STRING".equals(s);
    }

    private Map remap(Map map)
    {
        ReportField areportfield[] = ACRA.getConfig().customReportContent();
        if (areportfield.length == 0)
        {
            areportfield = ACRA.DEFAULT_REPORT_FIELDS;
        }
        HashMap hashmap = new HashMap(map.size());
        ReportField areportfield1[] = areportfield;
        int i = areportfield1.length;
        int j = 0;
        while (j < i) 
        {
            ReportField reportfield = areportfield1[j];
            if (mMapping == null || mMapping.get(reportfield) == null)
            {
                hashmap.put(reportfield.toString(), map.get(reportfield));
            } else
            {
                hashmap.put(mMapping.get(reportfield), map.get(reportfield));
            }
            j++;
        }
        return hashmap;
    }

    public void send(CrashReportData crashreportdata)
        throws ReportSenderException
    {
        Map map = remap(crashreportdata);
        if (mFormUri != null) goto _L2; else goto _L1
_L1:
        URL url = new URL(ACRA.getConfig().formUri());
_L7:
        Log.d(ACRA.LOG_TAG, (new StringBuilder()).append("Connect to ").append(url.toString()).toString());
        if (!isNull(ACRA.getConfig().formUriBasicAuthLogin())) goto _L4; else goto _L3
_L3:
        String s = null;
_L8:
        String s1;
        boolean flag;
        HttpRequest httprequest;
        try
        {
            flag = isNull(ACRA.getConfig().formUriBasicAuthPassword());
        }
        catch (IOException ioexception)
        {
            throw new ReportSenderException("Error while sending report to Http Post Form.", ioexception);
        }
        s1 = null;
        if (!flag) goto _L6; else goto _L5
_L5:
        httprequest = new HttpRequest();
        httprequest.setConnectionTimeOut(ACRA.getConfig().connectionTimeout());
        httprequest.setSocketTimeOut(ACRA.getConfig().socketTimeout());
        httprequest.setMaxNrRetries(ACRA.getConfig().maxNumberOfRequestRetries());
        httprequest.setLogin(s);
        httprequest.setPassword(s1);
        httprequest.sendPost(url, map);
        return;
_L2:
        url = new URL(mFormUri.toString());
          goto _L7
_L4:
        s = ACRA.getConfig().formUriBasicAuthLogin();
          goto _L8
_L6:
        String s2 = ACRA.getConfig().formUriBasicAuthPassword();
        s1 = s2;
          goto _L5
    }
}
