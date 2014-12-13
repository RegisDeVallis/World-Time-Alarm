// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;


// Referenced classes of package com.google.appinventor.components.runtime:
//            GameClient, EventDispatcher

class val.instanceId
    implements Runnable
{

    final GameClient this$0;
    final String val$instanceId;

    public void run()
    {
        GameClient gameclient = GameClient.this;
        Object aobj[] = new Object[1];
        aobj[0] = val$instanceId;
        EventDispatcher.dispatchEvent(gameclient, "InstanceIdChanged", aobj);
    }

    er()
    {
        this$0 = final_gameclient;
        val$instanceId = String.this;
        super();
    }
}
