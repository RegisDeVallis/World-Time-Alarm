// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import java.util.List;

// Referenced classes of package com.google.appinventor.components.runtime:
//            GameClient, EventDispatcher

class val.response
    implements Runnable
{

    final GameClient this$0;
    final String val$command;
    final List val$response;

    public void run()
    {
        GameClient gameclient = GameClient.this;
        Object aobj[] = new Object[2];
        aobj[0] = val$command;
        aobj[1] = val$response;
        EventDispatcher.dispatchEvent(gameclient, "ServerCommandSuccess", aobj);
    }

    r()
    {
        this$0 = final_gameclient;
        val$command = s;
        val$response = List.this;
        super();
    }
}
