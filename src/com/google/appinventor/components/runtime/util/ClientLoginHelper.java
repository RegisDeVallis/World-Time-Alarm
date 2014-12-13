// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

// Referenced classes of package com.google.appinventor.components.runtime.util:
//            IClientLoginHelper, AccountChooser

public class ClientLoginHelper
    implements IClientLoginHelper
{

    private static final String ACCOUNT_TYPE = "com.google";
    private static final String AUTHORIZATION_HEADER_PREFIX = "GoogleLogin auth=";
    private static final String LOG_TAG = "ClientLoginHelper";
    private AccountChooser accountChooser;
    private AccountManager accountManager;
    private Activity activity;
    private String authToken;
    private HttpClient client;
    private boolean initialized;
    private String service;

    public ClientLoginHelper(Activity activity1, String s, String s1, HttpClient httpclient)
    {
        initialized = false;
        service = s;
        if (httpclient == null)
        {
            httpclient = new DefaultHttpClient();
        }
        client = httpclient;
        activity = activity1;
        accountManager = AccountManager.get(activity1);
        accountChooser = new AccountChooser(activity1, s, s1, s);
    }

    private static void addGoogleAuthHeader(HttpUriRequest httpurirequest, String s)
    {
        if (s != null)
        {
            Log.i("ClientLoginHelper", (new StringBuilder()).append("adding auth token token: ").append(s).toString());
            httpurirequest.addHeader("Authorization", (new StringBuilder()).append("GoogleLogin auth=").append(s).toString());
        }
    }

    private void initialize()
        throws ClientProtocolException
    {
        if (!initialized)
        {
            Log.i("ClientLoginHelper", "initializing");
            if (isUiThread())
            {
                throw new IllegalArgumentException("Can't initialize login helper from UI thread");
            }
            authToken = getAuthToken();
            initialized = true;
        }
    }

    private boolean isUiThread()
    {
        return Looper.getMainLooper().getThread().equals(Thread.currentThread());
    }

    private static void removeGoogleAuthHeaders(HttpUriRequest httpurirequest)
    {
        Header aheader[] = httpurirequest.getAllHeaders();
        int i = aheader.length;
        for (int j = 0; j < i; j++)
        {
            Header header = aheader[j];
            if (header.getName().equalsIgnoreCase("Authorization") && header.getValue().startsWith("GoogleLogin auth="))
            {
                Log.i("ClientLoginHelper", (new StringBuilder()).append("Removing header:").append(header).toString());
                httpurirequest.removeHeader(header);
            }
        }

    }

    public HttpResponse execute(HttpUriRequest httpurirequest)
        throws ClientProtocolException, IOException
    {
        initialize();
        addGoogleAuthHeader(httpurirequest, authToken);
        HttpResponse httpresponse = client.execute(httpurirequest);
        if (httpresponse.getStatusLine().getStatusCode() == 401)
        {
            Log.i("ClientLoginHelper", (new StringBuilder()).append("Invalid token: ").append(authToken).toString());
            accountManager.invalidateAuthToken("com.google", authToken);
            authToken = getAuthToken();
            removeGoogleAuthHeaders(httpurirequest);
            addGoogleAuthHeader(httpurirequest, authToken);
            Log.i("ClientLoginHelper", (new StringBuilder()).append("new token: ").append(authToken).toString());
            httpresponse = client.execute(httpurirequest);
        }
        return httpresponse;
    }

    public void forgetAccountName()
    {
        accountChooser.forgetAccountName();
    }

    public String getAuthToken()
        throws ClientProtocolException
    {
        AccountManagerFuture accountmanagerfuture;
        android.accounts.Account account = accountChooser.findAccount();
        if (account == null)
        {
            break MISSING_BLOCK_LABEL_83;
        }
        accountmanagerfuture = accountManager.getAuthToken(account, service, null, activity, null, null);
        Log.i("ClientLoginHelper", (new StringBuilder()).append("Have account, auth token: ").append(accountmanagerfuture).toString());
        String s = ((Bundle)accountmanagerfuture.getResult()).getString("authtoken");
        return s;
        AuthenticatorException authenticatorexception;
        authenticatorexception;
        authenticatorexception.printStackTrace();
_L2:
        throw new ClientProtocolException("Can't get valid authentication token");
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        OperationCanceledException operationcanceledexception;
        operationcanceledexception;
        operationcanceledexception.printStackTrace();
        if (true) goto _L2; else goto _L1
_L1:
    }
}
