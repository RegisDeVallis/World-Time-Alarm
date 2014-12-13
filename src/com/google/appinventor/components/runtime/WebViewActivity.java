// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public final class WebViewActivity extends Activity
{

    public WebViewActivity()
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        WebView webview = new WebView(this);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setWebViewClient(new WebViewClient() {

            final WebViewActivity this$0;

            public boolean shouldOverrideUrlLoading(WebView webview1, String s2)
            {
                Log.i("WebView", (new StringBuilder()).append("Handling url ").append(s2).toString());
                Uri uri1 = Uri.parse(s2);
                if (uri1.getScheme().equals("appinventor"))
                {
                    Intent intent1 = new Intent();
                    intent1.setData(uri1);
                    setResult(-1, intent1);
                    finish();
                } else
                {
                    webview1.loadUrl(s2);
                }
                return true;
            }

            
            {
                this$0 = WebViewActivity.this;
                super();
            }
        });
        setContentView(webview);
        Intent intent = getIntent();
        if (intent != null && intent.getData() != null)
        {
            Uri uri = intent.getData();
            String s = uri.getScheme();
            String s1 = uri.getHost();
            Log.i("WebView", (new StringBuilder()).append("Got intent with URI: ").append(uri).append(", scheme=").append(s).append(", host=").append(s1).toString());
            webview.loadUrl(uri.toString());
        }
    }
}
