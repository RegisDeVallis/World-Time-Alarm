// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import java.io.File;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;

// Referenced classes of package com.google.appinventor.components.runtime:
//            Twitter, Form

class val.status
    implements Runnable
{

    final com.google.appinventor.components.runtime.Twitter this$0;
    final String val$imagePath;
    final String val$status;

    public void run()
    {
        try
        {
            String s = val$imagePath;
            if (s.startsWith("file://"))
            {
                s = val$imagePath.replace("file://", "");
            }
            File file = new File(s);
            if (file.exists())
            {
                StatusUpdate statusupdate = new StatusUpdate(val$status);
                statusupdate.setMedia(file);
                Twitter.access$200(com.google.appinventor.components.runtime.Twitter.this).updateStatus(statusupdate);
                return;
            }
        }
        catch (TwitterException twitterexception)
        {
            Form form = com.google.appinventor.components.runtime.Twitter.this.form;
            com.google.appinventor.components.runtime.Twitter twitter = com.google.appinventor.components.runtime.Twitter.this;
            Object aobj[] = new Object[1];
            aobj[0] = twitterexception.getMessage();
            form.dispatchErrorOccurredEvent(twitter, "TweetWithImage", 306, aobj);
            return;
        }
        com.google.appinventor.components.runtime.Twitter.this.form.dispatchErrorOccurredEvent(com.google.appinventor.components.runtime.Twitter.this, "TweetWithImage", 315, new Object[0]);
        return;
    }

    _cls9()
    {
        this$0 = final_twitter;
        val$imagePath = s;
        val$status = String.this;
        super();
    }
}
