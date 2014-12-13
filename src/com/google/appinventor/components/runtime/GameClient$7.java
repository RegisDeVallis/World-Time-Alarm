// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.util.Log;

// Referenced classes of package com.google.appinventor.components.runtime:
//            GameClient, EventDispatcher

class val.playerId
    implements Runnable
{

    final GameClient this$0;
    final String val$playerId;

    public void run()
    {
        if (!val$playerId.equals(UserEmailAddress()))
        {
            Log.d("GameClient", (new StringBuilder()).append("Player joined: ").append(val$playerId).toString());
            GameClient gameclient = GameClient.this;
            Object aobj[] = new Object[1];
            aobj[0] = val$playerId;
            EventDispatcher.dispatchEvent(gameclient, "PlayerJoined", aobj);
        }
    }

    er()
    {
        this$0 = final_gameclient;
        val$playerId = String.this;
        super();
    }
}
