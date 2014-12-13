// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import java.io.IOException;
import java.util.concurrent.SynchronousQueue;

public class AccountChooser
{
    class SelectAccount extends Thread
        implements android.content.DialogInterface.OnClickListener, android.content.DialogInterface.OnCancelListener
    {

        private String accountNames[];
        private SynchronousQueue queue;
        final AccountChooser this$0;

        public void onCancel(DialogInterface dialoginterface)
        {
            Log.i("AccountChooser", "Chose: canceled");
            onClick(dialoginterface, -1);
        }

        public void onClick(DialogInterface dialoginterface, int i)
        {
            if (i < 0)
            {
                break MISSING_BLOCK_LABEL_54;
            }
            try
            {
                String s = accountNames[i];
                Log.i("AccountChooser", (new StringBuilder()).append("Chose: ").append(s).toString());
                queue.put(s);
            }
            catch (InterruptedException interruptedexception) { }
            dialoginterface.dismiss();
            return;
            queue.put("");
            break MISSING_BLOCK_LABEL_47;
        }

        public void run()
        {
            activity.runOnUiThread(new Runnable() {

                final SelectAccount this$1;

                public void run()
                {
                    (new android.app.AlertDialog.Builder(activity)).setTitle(Html.fromHtml(chooseAccountPrompt)).setOnCancelListener(SelectAccount.this).setSingleChoiceItems(accountNames, -1, SelectAccount.this).show();
                    Log.i("AccountChooser", "Dialog showing!");
                }

            
            {
                this$1 = SelectAccount.this;
                super();
            }
            });
        }


        SelectAccount(Account aaccount[], SynchronousQueue synchronousqueue)
        {
            this$0 = AccountChooser.this;
            super();
            queue = synchronousqueue;
            accountNames = new String[aaccount.length];
            for (int i = 0; i < aaccount.length; i++)
            {
                accountNames[i] = aaccount[i].name;
            }

        }
    }


    private static final String ACCOUNT_PREFERENCE = "account";
    private static final String ACCOUNT_TYPE = "com.google";
    private static final String LOG_TAG = "AccountChooser";
    private static final String NO_ACCOUNT = "";
    private AccountManager accountManager;
    private Activity activity;
    private String chooseAccountPrompt;
    private String preferencesKey;
    private String service;

    public AccountChooser(Activity activity1, String s, String s1, String s2)
    {
        activity = activity1;
        service = s;
        chooseAccountPrompt = s1;
        preferencesKey = s2;
        accountManager = AccountManager.get(activity1);
    }

    private Account chooseAccount(String s, Account aaccount[])
    {
        int i = aaccount.length;
        for (int j = 0; j < i; j++)
        {
            Account account = aaccount[j];
            if (account.name.equals(s))
            {
                Log.i("AccountChooser", (new StringBuilder()).append("chose account: ").append(s).toString());
                return account;
            }
        }

        return null;
    }

    private String createAccount()
    {
        AccountManagerFuture accountmanagerfuture;
        Log.i("AccountChooser", "Adding auth token account ...");
        accountmanagerfuture = accountManager.addAccount("com.google", service, null, null, activity, null, null);
        String s;
        s = ((Bundle)accountmanagerfuture.getResult()).getString("authAccount");
        Log.i("AccountChooser", (new StringBuilder()).append("created: ").append(s).toString());
        return s;
        OperationCanceledException operationcanceledexception;
        operationcanceledexception;
        operationcanceledexception.printStackTrace();
_L2:
        return null;
        AuthenticatorException authenticatorexception;
        authenticatorexception;
        authenticatorexception.printStackTrace();
        continue; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        if (true) goto _L2; else goto _L1
_L1:
    }

    private String getPersistedAccountName()
    {
        return getPreferences().getString("account", null);
    }

    private SharedPreferences getPreferences()
    {
        return activity.getSharedPreferences(preferencesKey, 0);
    }

    private void persistAccountName(String s)
    {
        Log.i("AccountChooser", (new StringBuilder()).append("persisting account: ").append(s).toString());
        getPreferences().edit().putString("account", s).commit();
    }

    private String selectAccount(Account aaccount[])
    {
        SynchronousQueue synchronousqueue = new SynchronousQueue();
        (new SelectAccount(aaccount, synchronousqueue)).start();
        Log.i("AccountChooser", "Select: waiting for user...");
        String s;
        try
        {
            s = (String)synchronousqueue.take();
        }
        catch (InterruptedException interruptedexception)
        {
            interruptedexception.printStackTrace();
            s = null;
        }
        Log.i("AccountChooser", (new StringBuilder()).append("Selected: ").append(s).toString());
        if (s == "")
        {
            s = null;
        }
        return s;
    }

    public Account findAccount()
    {
        Account aaccount[] = accountManager.getAccountsByType("com.google");
        if (aaccount.length != 1) goto _L2; else goto _L1
_L1:
        Account account;
        persistAccountName(aaccount[0].name);
        account = aaccount[0];
_L4:
        return account;
_L2:
        if (aaccount.length == 0)
        {
            String s2 = createAccount();
            if (s2 != null)
            {
                persistAccountName(s2);
                return accountManager.getAccountsByType("com.google")[0];
            } else
            {
                Log.i("AccountChooser", "User failed to create a valid account");
                return null;
            }
        }
        String s = getPersistedAccountName();
        if (s == null)
        {
            break; /* Loop/switch isn't completed */
        }
        account = chooseAccount(s, aaccount);
        if (account != null) goto _L4; else goto _L3
_L3:
        String s1 = selectAccount(aaccount);
        if (s1 != null)
        {
            persistAccountName(s1);
            return chooseAccount(s1, aaccount);
        } else
        {
            Log.i("AccountChooser", "User failed to choose an account");
            return null;
        }
    }

    public void forgetAccountName()
    {
        getPreferences().edit().remove("account").commit();
    }


}
