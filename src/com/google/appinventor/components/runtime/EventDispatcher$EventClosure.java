// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;


// Referenced classes of package com.google.appinventor.components.runtime:
//            EventDispatcher

private static final class <init>
{

    private final String componentId;
    private final String eventName;

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            <init> <init>1 = (<init>)obj;
            if (!componentId.equals(<init>1.componentId))
            {
                return false;
            }
            if (!eventName.equals(<init>1.eventName))
            {
                return false;
            }
        }
        return true;
    }

    public int hashCode()
    {
        return 31 * eventName.hashCode() + componentId.hashCode();
    }



    private (String s, String s1)
    {
        componentId = s;
        eventName = s1;
    }

    eventName(String s, String s1, eventName eventname)
    {
        this(s, s1);
    }
}
