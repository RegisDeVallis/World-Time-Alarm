// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import com.google.appinventor.components.runtime.util.IClientLoginHelper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

// Referenced classes of package com.google.appinventor.components.runtime:
//            FusiontablesControl

private class <init> extends AsyncTask
{

    private ProgressDialog progress;
    final FusiontablesControl this$0;

    protected volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected transient String doInBackground(String as[])
    {
        String s;
        try
        {
            org.apache.http.client.methods.HttpUriRequest httpurirequest = FusiontablesControl.access$200(FusiontablesControl.this, as[0]);
            Log.d("fusion", (new StringBuilder()).append("Fetching: ").append(as[0]).toString());
            HttpResponse httpresponse = FusiontablesControl.access$300(FusiontablesControl.this).execute(httpurirequest);
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            httpresponse.getEntity().writeTo(bytearrayoutputstream);
            Log.d("fusion", (new StringBuilder()).append("Response: ").append(httpresponse.getStatusLine().toString()).toString());
            s = bytearrayoutputstream.toString();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return ioexception.getMessage();
        }
        return s;
    }

    protected volatile void onPostExecute(Object obj)
    {
        onPostExecute((String)obj);
    }

    protected void onPostExecute(String s)
    {
        progress.dismiss();
        GotResult(s);
    }

    protected void onPreExecute()
    {
        progress = ProgressDialog.show(FusiontablesControl.access$000(FusiontablesControl.this), "Fusiontables", "processing query...", true);
    }

    private ()
    {
        this$0 = FusiontablesControl.this;
        super();
        progress = null;
    }

    progress(progress progress1)
    {
        this();
    }
}
