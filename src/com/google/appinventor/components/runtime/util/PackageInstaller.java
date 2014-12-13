// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import com.google.appinventor.components.runtime.Form;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

// Referenced classes of package com.google.appinventor.components.runtime.util:
//            AsynchUtil

public class PackageInstaller
{

    private static final String LOG_TAG = "PackageInstaller(AppInventor)";
    private static final String REPL_ASSET_DIR = "/sdcard/AppInventor/assets/";

    private PackageInstaller()
    {
    }

    public static void doPackageInstall(Form form, String s)
    {
        AsynchUtil.runAsynchronously(new Runnable(s, form) {

            final Form val$form;
            final String val$inurl;

            public void run()
            {
                File file;
                BufferedInputStream bufferedinputstream;
                FileOutputStream fileoutputstream;
                byte abyte0[];
                URLConnection urlconnection = (new URL(inurl)).openConnection();
                file = new File("/sdcard/AppInventor/assets/");
                bufferedinputstream = new BufferedInputStream(urlconnection.getInputStream());
                fileoutputstream = new FileOutputStream(new File((new StringBuilder()).append(file).append("/package.apk").toString()));
                abyte0 = new byte[32768];
_L1:
                int i = bufferedinputstream.read(abyte0, 0, 32768);
label0:
                {
                    if (i <= 0)
                    {
                        break label0;
                    }
                    try
                    {
                        fileoutputstream.write(abyte0, 0, i);
                    }
                    catch (Exception exception)
                    {
                        Form form1 = form;
                        Form form2 = form;
                        Object aobj[] = new Object[1];
                        aobj[0] = inurl;
                        form1.dispatchErrorOccurredEvent(form2, "PackageInstaller", 1101, aobj);
                        return;
                    }
                }
                  goto _L1
                bufferedinputstream.close();
                fileoutputstream.close();
                Log.d("PackageInstaller(AppInventor)", (new StringBuilder()).append("About to Install package from ").append(inurl).toString());
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(Uri.fromFile(new File((new StringBuilder()).append(file).append("/package.apk").toString())), "application/vnd.android.package-archive");
                form.startActivity(intent);
                return;
            }

            
            {
                inurl = s;
                form = form1;
                super();
            }
        });
    }
}
