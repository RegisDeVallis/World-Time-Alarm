// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.os.Handler;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import twitter4j.DirectMessage;
import twitter4j.Twitter;
import twitter4j.TwitterException;

// Referenced classes of package com.google.appinventor.components.runtime:
//            Twitter, Form

class this._cls1
    implements Runnable
{

    final is._cls0 this$1;

    public void run()
    {
        Twitter.access$1300(_fld0).clear();
        DirectMessage directmessage;
        for (Iterator iterator = ssages.iterator(); iterator.hasNext(); Twitter.access$1300(_fld0).add((new StringBuilder()).append(directmessage.getSenderScreenName()).append(" ").append(directmessage.getText()).toString()))
        {
            directmessage = (DirectMessage)iterator.next();
        }

        DirectMessagesReceived(Twitter.access$1300(_fld0));
    }

    ssages()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class com/google/appinventor/components/runtime/Twitter$8

/* anonymous class */
    class Twitter._cls8
        implements Runnable
    {

        List messages;
        final com.google.appinventor.components.runtime.Twitter this$0;

        public void run()
        {
            messages = Twitter.access$200(com.google.appinventor.components.runtime.Twitter.this).getDirectMessages();
            Twitter.access$100(com.google.appinventor.components.runtime.Twitter.this).post(new Twitter._cls8._cls1());
            return;
            TwitterException twitterexception;
            twitterexception;
            Form form = com.google.appinventor.components.runtime.Twitter.this.form;
            com.google.appinventor.components.runtime.Twitter twitter = com.google.appinventor.components.runtime.Twitter.this;
            Object aobj[] = new Object[1];
            aobj[0] = twitterexception.getMessage();
            form.dispatchErrorOccurredEvent(twitter, "RequestDirectMessages", 309, aobj);
            Twitter.access$100(com.google.appinventor.components.runtime.Twitter.this).post(new Twitter._cls8._cls1());
            return;
            Exception exception;
            exception;
            Twitter.access$100(com.google.appinventor.components.runtime.Twitter.this).post(new Twitter._cls8._cls1());
            throw exception;
        }

            
            {
                this$0 = com.google.appinventor.components.runtime.Twitter.this;
                super();
                messages = Collections.emptyList();
            }
    }

}
