// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import com.google.api.client.extensions.android2.AndroidHttp;
import com.google.api.client.googleapis.services.GoogleKeyInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.fusiontables.Fusiontables;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.OAuth2Helper;

// Referenced classes of package com.google.appinventor.components.runtime:
//            FusiontablesControl, ComponentContainer

private class dialog extends AsyncTask
{

    private static final String TAG = "QueryProcessorV1";
    private final Activity activity;
    private final ProgressDialog dialog;
    final FusiontablesControl this$0;

    private String serviceAuthRequest(String s)
    {
        com.google.api.client.http.HttpTransport httptransport;
        GsonFactory gsonfactory;
        httptransport = AndroidHttp.newCompatibleTransport();
        gsonfactory = new GsonFactory();
        Log.i("SERVICE_ACCOUNT", (new StringBuilder()).append("keyPath ").append(FusiontablesControl.access$1000(FusiontablesControl.this)).toString());
        com.google.api.client.http.HttpResponse httpresponse;
        if (FusiontablesControl.access$1100(FusiontablesControl.this) == null)
        {
            FusiontablesControl.access$1102(FusiontablesControl.this, MediaUtil.copyMediaToTempFile(FusiontablesControl.access$1200(FusiontablesControl.this).$form(), FusiontablesControl.access$1000(FusiontablesControl.this)));
        }
        com.google.api.client.googleapis.auth.oauth2.log log = (new com.google.api.client.googleapis.auth.oauth2.s._cls0()).(httptransport).ry(gsonfactory).countId(FusiontablesControl.access$1400(FusiontablesControl.this));
        String as[] = new String[1];
        as[0] = FusiontablesControl.access$1300(FusiontablesControl.this);
        com.google.api.services.fusiontables.orV1 orv1 = (new com.google.api.services.fusiontables.orV1.this._cls0(httptransport, gsonfactory, log.countScopes(as).countPrivateKeyFromP12File(FusiontablesControl.access$1100(FusiontablesControl.this))._mth0())).stInitializer(new GoogleKeyInitializer(ApiKey())).stInitializer().query().stInitializer(s);
        orv1.stInitializer("alt", "csv");
        httpresponse = orv1.d();
        if (httpresponse == null) goto _L2; else goto _L1
_L1:
        FusiontablesControl.access$502(FusiontablesControl.this, FusiontablesControl.httpResponseToString(httpresponse));
        Log.i("QueryProcessorV1", (new StringBuilder()).append("Query = ").append(s).append("\nResultStr = ").append(FusiontablesControl.access$500(FusiontablesControl.this)).toString());
_L3:
        Log.i("SERVICE_ACCOUNT", "executed sql query");
_L4:
        return FusiontablesControl.access$500(FusiontablesControl.this);
_L2:
        FusiontablesControl.access$502(FusiontablesControl.this, FusiontablesControl.access$900(FusiontablesControl.this));
        Log.i("QueryProcessorV1", (new StringBuilder()).append("Error:  ").append(FusiontablesControl.access$900(FusiontablesControl.this)).toString());
          goto _L3
        Throwable throwable;
        throwable;
        throwable.printStackTrace();
        FusiontablesControl.access$902(FusiontablesControl.this, throwable.getMessage());
          goto _L4
    }

    private String userAuthRequest(String s)
    {
        FusiontablesControl.access$502(FusiontablesControl.this, "");
        String s1 = (new OAuth2Helper()).getRefreshedAuthToken(activity, FusiontablesControl.access$600(FusiontablesControl.this));
        if (s1 != null)
        {
            if (s.toLowerCase().contains("create table"))
            {
                FusiontablesControl.access$502(FusiontablesControl.this, FusiontablesControl.access$800(FusiontablesControl.this, FusiontablesControl.access$700(FusiontablesControl.this, s), s1));
                return FusiontablesControl.access$500(FusiontablesControl.this);
            }
            com.google.api.client.http.HttpResponse httpresponse = sendQuery(s, s1);
            if (httpresponse != null)
            {
                FusiontablesControl.access$502(FusiontablesControl.this, FusiontablesControl.httpResponseToString(httpresponse));
                Log.i("QueryProcessorV1", (new StringBuilder()).append("Query = ").append(s).append("\nResultStr = ").append(FusiontablesControl.access$500(FusiontablesControl.this)).toString());
            } else
            {
                FusiontablesControl.access$502(FusiontablesControl.this, FusiontablesControl.access$900(FusiontablesControl.this));
                Log.i("QueryProcessorV1", (new StringBuilder()).append("Error:  ").append(FusiontablesControl.access$900(FusiontablesControl.this)).toString());
            }
            return FusiontablesControl.access$500(FusiontablesControl.this);
        } else
        {
            return OAuth2Helper.getErrorMessage();
        }
    }

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected transient String doInBackground(String as[])
    {
        String s = as[0];
        Log.i("QueryProcessorV1", (new StringBuilder()).append("Starting doInBackground ").append(s).toString());
        if (FusiontablesControl.access$400(FusiontablesControl.this))
        {
            return serviceAuthRequest(s);
        } else
        {
            return userAuthRequest(s);
        }
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((String)obj);
    }

    protected void onPostExecute(String s)
    {
        Log.i("fusion", (new StringBuilder()).append("Query result ").append(s).toString());
        if (s == null)
        {
            s = "Error";
        }
        dialog.dismiss();
        GotResult(s);
    }

    protected void onPreExecute()
    {
        dialog.setMessage("Fusiontables...");
        dialog.show();
    }

    (Activity activity1)
    {
        this$0 = FusiontablesControl.this;
        super();
        Log.i("QueryProcessorV1", "Creating AsyncFusiontablesQuery");
        activity = activity1;
        dialog = new ProgressDialog(activity1);
    }
}
