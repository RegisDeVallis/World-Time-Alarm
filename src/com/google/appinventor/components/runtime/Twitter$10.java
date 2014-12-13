// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import twitter4j.Twitter;
import twitter4j.TwitterException;

// Referenced classes of package com.google.appinventor.components.runtime:
//            Twitter, Form

class val.user
    implements Runnable
{

    final com.google.appinventor.components.runtime.Twitter this$0;
    final String val$user;

    public void run()
    {
        try
        {
            Twitter.access$200(com.google.appinventor.components.runtime.Twitter.this).createFriendship(val$user);
            return;
        }
        catch (TwitterException twitterexception)
        {
            Form form = com.google.appinventor.components.runtime.Twitter.this.form;
            com.google.appinventor.components.runtime.Twitter twitter = com.google.appinventor.components.runtime.Twitter.this;
            Object aobj[] = new Object[1];
            aobj[0] = twitterexception.getMessage();
            form.dispatchErrorOccurredEvent(twitter, "Follow", 311, aobj);
            return;
        }
    }

    ()
    {
        this$0 = final_twitter;
        val$user = String.this;
        super();
    }
}
