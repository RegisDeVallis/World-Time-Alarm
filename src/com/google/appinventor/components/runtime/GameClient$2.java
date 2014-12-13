// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import java.util.List;

// Referenced classes of package com.google.appinventor.components.runtime:
//            GameClient, EventDispatcher

class val.contents
    implements Runnable
{

    final GameClient this$0;
    final List val$contents;
    final String val$sender;
    final String val$type;

    public void run()
    {
        GameClient gameclient = GameClient.this;
        Object aobj[] = new Object[3];
        aobj[0] = val$type;
        aobj[1] = val$sender;
        aobj[2] = val$contents;
        EventDispatcher.dispatchEvent(gameclient, "GotMessage", aobj);
    }

    er()
    {
        this$0 = final_gameclient;
        val$type = s;
        val$sender = s1;
        val$contents = List.this;
        super();
    }
}
