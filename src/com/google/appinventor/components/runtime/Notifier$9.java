// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.content.DialogInterface;

// Referenced classes of package com.google.appinventor.components.runtime:
//            Notifier

class this._cls0
    implements android.content.face.OnClickListener
{

    final Notifier this$0;

    public void onClick(DialogInterface dialoginterface, int i)
    {
        AfterTextInput("Cancel");
    }

    ()
    {
        this$0 = Notifier.this;
        super();
    }
}
