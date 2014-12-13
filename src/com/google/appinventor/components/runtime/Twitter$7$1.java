// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.os.Handler;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

// Referenced classes of package com.google.appinventor.components.runtime:
//            Twitter, Form

class this._cls1
    implements Runnable
{

    final is._cls0 this$1;

    public void run()
    {
        Twitter.access$1200(_fld0).clear();
        User user;
        for (Iterator iterator = iends.iterator(); iterator.hasNext(); Twitter.access$1200(_fld0).add(user.getName()))
        {
            user = (User)iterator.next();
        }

        FollowersReceived(Twitter.access$1200(_fld0));
    }

    iends()
    {
        this$1 = this._cls1.this;
        super();
    }

    // Unreferenced inner class com/google/appinventor/components/runtime/Twitter$7

/* anonymous class */
    class Twitter._cls7
        implements Runnable
    {

        List friends;
        final com.google.appinventor.components.runtime.Twitter this$0;

        public void run()
        {
            long al[];
            int i;
            al = Twitter.access$200(com.google.appinventor.components.runtime.Twitter.this).getFollowersIDs(-1L).getIDs();
            i = al.length;
            int j = 0;
_L2:
            if (j >= i)
            {
                break; /* Loop/switch isn't completed */
            }
            long l = al[j];
            friends.add(Twitter.access$200(com.google.appinventor.components.runtime.Twitter.this).showUser(l));
            j++;
            if (true) goto _L2; else goto _L1
_L1:
            Twitter.access$100(com.google.appinventor.components.runtime.Twitter.this).post(new Twitter._cls7._cls1());
            return;
            TwitterException twitterexception;
            twitterexception;
            Form form = com.google.appinventor.components.runtime.Twitter.this.form;
            com.google.appinventor.components.runtime.Twitter twitter = com.google.appinventor.components.runtime.Twitter.this;
            Object aobj[] = new Object[1];
            aobj[0] = twitterexception.getMessage();
            form.dispatchErrorOccurredEvent(twitter, "RequestFollowers", 308, aobj);
            Twitter.access$100(com.google.appinventor.components.runtime.Twitter.this).post(new Twitter._cls7._cls1());
            return;
            Exception exception;
            exception;
            Twitter.access$100(com.google.appinventor.components.runtime.Twitter.this).post(new Twitter._cls7._cls1());
            throw exception;
        }

            
            {
                this$0 = com.google.appinventor.components.runtime.Twitter.this;
                super();
                friends = new ArrayList();
            }
    }

}
