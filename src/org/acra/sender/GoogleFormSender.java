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

public class GoogleFormSender
    implements ReportSender
{

    private final Uri mFormUri;

    public GoogleFormSender()
    {
        mFormUri = null;
    }

    public GoogleFormSender(String s)
    {
        mFormUri = Uri.parse(String.format(ACRA.getConfig().googleFormUrlFormat(), new Object[] {
            s
        }));
    }

    private Map remap(Map map)
    {
        int i;
        HashMap hashmap;
        ReportField areportfield1[];
        int j;
        int k;
        ReportField areportfield[] = ACRA.getConfig().customReportContent();
        if (areportfield.length == 0)
        {
            areportfield = ACRA.DEFAULT_REPORT_FIELDS;
        }
        i = 0;
        hashmap = new HashMap();
        areportfield1 = areportfield;
        j = areportfield1.length;
        k = 0;
_L2:
        ReportField reportfield;
        if (k >= j)
        {
            break MISSING_BLOCK_LABEL_261;
        }
        reportfield = areportfield1[k];
        static class _cls1
        {

            static final int $SwitchMap$org$acra$ReportField[];

            static 
            {
                $SwitchMap$org$acra$ReportField = new int[ReportField.values().length];
                try
                {
                    $SwitchMap$org$acra$ReportField[ReportField.APP_VERSION_NAME.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$org$acra$ReportField[ReportField.ANDROID_VERSION.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1)
                {
                    return;
                }
            }
        }

        switch (_cls1..SwitchMap.org.acra.ReportField[reportfield.ordinal()])
        {
        default:
            hashmap.put((new StringBuilder()).append("entry.").append(i).append(".single").toString(), map.get(reportfield));
            break;

        case 1: // '\001'
            break; /* Loop/switch isn't completed */

        case 2: // '\002'
            break MISSING_BLOCK_LABEL_197;
        }
_L3:
        i++;
        k++;
        if (true) goto _L2; else goto _L1
_L1:
        hashmap.put((new StringBuilder()).append("entry.").append(i).append(".single").toString(), (new StringBuilder()).append("'").append((String)map.get(reportfield)).toString());
          goto _L3
        hashmap.put((new StringBuilder()).append("entry.").append(i).append(".single").toString(), (new StringBuilder()).append("'").append((String)map.get(reportfield)).toString());
          goto _L3
        return hashmap;
    }

    public void send(CrashReportData crashreportdata)
        throws ReportSenderException
    {
        Uri uri;
        Map map;
        if (mFormUri == null)
        {
            String s = ACRA.getConfig().googleFormUrlFormat();
            Object aobj[] = new Object[1];
            aobj[0] = ACRA.getConfig().formKey();
            uri = Uri.parse(String.format(s, aobj));
        } else
        {
            uri = mFormUri;
        }
        map = remap(crashreportdata);
        map.put("pageNumber", "0");
        map.put("backupCache", "");
        map.put("submit", "Envoyer");
        try
        {
            URL url = new URL(uri.toString());
            Log.d(ACRA.LOG_TAG, (new StringBuilder()).append("Sending report ").append((String)crashreportdata.get(ReportField.REPORT_ID)).toString());
            Log.d(ACRA.LOG_TAG, (new StringBuilder()).append("Connect to ").append(url).toString());
            HttpRequest httprequest = new HttpRequest();
            httprequest.setConnectionTimeOut(ACRA.getConfig().connectionTimeout());
            httprequest.setSocketTimeOut(ACRA.getConfig().socketTimeout());
            httprequest.setMaxNrRetries(ACRA.getConfig().maxNumberOfRequestRetries());
            httprequest.sendPost(url, map);
            return;
        }
        catch (IOException ioexception)
        {
            throw new ReportSenderException("Error while sending report to Google Form.", ioexception);
        }
    }
}
