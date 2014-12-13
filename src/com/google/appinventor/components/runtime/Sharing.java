// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import java.io.File;

// Referenced classes of package com.google.appinventor.components.runtime:
//            AndroidNonvisibleComponent, ComponentContainer, Form

public class Sharing extends AndroidNonvisibleComponent
{

    public Sharing(ComponentContainer componentcontainer)
    {
        super(componentcontainer.$form());
    }

    public void ShareFile(String s)
    {
        ShareFileWithMessage(s, "");
    }

    public void ShareFileWithMessage(String s, String s1)
    {
        if (!s.startsWith("file://"))
        {
            s = (new StringBuilder()).append("file://").append(s).toString();
        }
        Uri uri = Uri.parse(s);
        if ((new File(uri.getPath())).isFile())
        {
            String s3 = s.substring(1 + s.lastIndexOf(".")).toLowerCase();
            String s4 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(s3);
            Intent intent = new Intent("android.intent.action.SEND");
            intent.putExtra("android.intent.extra.STREAM", uri);
            intent.setType(s4);
            if (s1.length() > 0)
            {
                intent.putExtra("android.intent.extra.TEXT", s1);
            }
            form.startActivity(intent);
            return;
        }
        String s2 = "ShareFile";
        if (s1.equals(""))
        {
            s2 = "ShareFileWithMessage";
        }
        form.dispatchErrorOccurredEvent(this, s2, 2001, new Object[] {
            s
        });
    }

    public void ShareMessage(String s)
    {
        Intent intent = new Intent("android.intent.action.SEND");
        intent.putExtra("android.intent.extra.TEXT", s);
        intent.setType("text/plain");
        form.startActivity(intent);
    }
}
