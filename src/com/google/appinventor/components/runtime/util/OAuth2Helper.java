// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.extensions.android2.auth.GoogleAccountManager;
import java.io.IOException;

public class OAuth2Helper
{

    public static final String PREF_ACCOUNT_NAME = "accountName";
    public static final String PREF_AUTH_TOKEN = "authToken";
    public static final String TAG = "OAuthHelper";
    private static String errorMessage = "Error during OAuth";

    public OAuth2Helper()
    {
    }

    private AccountManagerFuture getAccountManagerResult(Activity activity, GoogleCredential googlecredential, String s, String s1)
    {
        GoogleAccountManager googleaccountmanager = new GoogleAccountManager(activity);
        googleaccountmanager.invalidateAuthToken(googlecredential.getAccessToken());
        AccountManager.get(activity).invalidateAuthToken(s, null);
        android.accounts.Account account = googleaccountmanager.getAccountByName(s1);
        if (account != null)
        {
            Log.i("OAuthHelper", "Getting token by account");
            return googleaccountmanager.getAccountManager().getAuthToken(account, s, true, null, null);
        } else
        {
            Log.i("OAuthHelper", "Getting token by features, possibly prompting user to select an account");
            return googleaccountmanager.getAccountManager().getAuthTokenByFeatures("com.google", s, null, activity, null, null, null, null);
        }
    }

    public static String getErrorMessage()
    {
        Log.i("OAuthHelper", (new StringBuilder()).append("getErrorMessage = ").append(errorMessage).toString());
        return errorMessage;
    }

    private boolean isUiThread()
    {
        return Looper.getMainLooper().getThread().equals(Thread.currentThread());
    }

    private void persistCredentials(SharedPreferences sharedpreferences, String s, String s1)
    {
        Log.i("OAuthHelper", (new StringBuilder()).append("Persisting credentials, acct =").append(s).toString());
        android.content.SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString("accountName", s);
        editor.putString("authToken", s1);
        editor.commit();
    }

    public static void resetAccountCredential(Activity activity)
    {
        Log.i("OAuthHelper", "Reset credentials");
        android.content.SharedPreferences.Editor editor = activity.getPreferences(0).edit();
        editor.remove("authToken");
        editor.remove("accountName");
        editor.commit();
    }

    public String getRefreshedAuthToken(Activity activity, String s)
    {
        Log.i("OAuthHelper", "getRefreshedAuthToken()");
        if (isUiThread())
        {
            throw new IllegalArgumentException("Can't get authtoken from UI thread");
        }
        SharedPreferences sharedpreferences = activity.getPreferences(0);
        String s1 = sharedpreferences.getString("accountName", null);
        String s2 = sharedpreferences.getString("authToken", null);
        GoogleCredential googlecredential = new GoogleCredential();
        googlecredential.setAccessToken(s2);
        AccountManagerFuture accountmanagerfuture = getAccountManagerResult(activity, googlecredential, s, s1);
        try
        {
            Bundle bundle = (Bundle)accountmanagerfuture.getResult();
            s2 = bundle.get("authtoken").toString();
            persistCredentials(sharedpreferences, bundle.getString("authAccount"), s2);
        }
        catch (OperationCanceledException operationcanceledexception)
        {
            operationcanceledexception.printStackTrace();
            resetAccountCredential(activity);
            errorMessage = "Error: operation cancelled";
            return s2;
        }
        catch (AuthenticatorException authenticatorexception)
        {
            authenticatorexception.printStackTrace();
            errorMessage = "Error: Authenticator error";
            return s2;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            errorMessage = "Error: I/O error";
            return s2;
        }
        return s2;
    }

}
