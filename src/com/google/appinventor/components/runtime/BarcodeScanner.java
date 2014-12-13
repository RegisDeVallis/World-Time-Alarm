// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;

// Referenced classes of package com.google.appinventor.components.runtime:
//            AndroidNonvisibleComponent, ActivityResultListener, Component, ComponentContainer, 
//            EventDispatcher, Form

public class BarcodeScanner extends AndroidNonvisibleComponent
    implements ActivityResultListener, Component
{

    private static final String SCANNER_RESULT_NAME = "SCAN_RESULT";
    private static final String SCAN_INTENT = "com.google.zxing.client.android.SCAN";
    private final ComponentContainer container;
    private int requestCode;
    private String result;

    public BarcodeScanner(ComponentContainer componentcontainer)
    {
        super(componentcontainer.$form());
        result = "";
        container = componentcontainer;
    }

    public void AfterScan(String s)
    {
        EventDispatcher.dispatchEvent(this, "AfterScan", new Object[] {
            s
        });
    }

    public void DoScan()
    {
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        if (requestCode == 0)
        {
            requestCode = form.registerForActivityResult(this);
        }
        try
        {
            container.$context().startActivityForResult(intent, requestCode);
            return;
        }
        catch (ActivityNotFoundException activitynotfoundexception)
        {
            container.$form().dispatchErrorOccurredEvent(this, "BarcodeScanner", 1501, new Object[] {
                ""
            });
        }
    }

    public String Result()
    {
        return result;
    }

    public void resultReturned(int i, int j, Intent intent)
    {
        if (i == requestCode && j == -1)
        {
            if (intent.hasExtra("SCAN_RESULT"))
            {
                result = intent.getStringExtra("SCAN_RESULT");
            } else
            {
                result = "";
            }
            AfterScan(result);
        }
    }
}
