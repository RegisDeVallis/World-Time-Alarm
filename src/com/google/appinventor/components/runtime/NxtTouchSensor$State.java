// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;


// Referenced classes of package com.google.appinventor.components.runtime:
//            NxtTouchSensor

private static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES PRESSED;
    public static final .VALUES RELEASED;
    public static final .VALUES UNKNOWN;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/google/appinventor/components/runtime/NxtTouchSensor$State, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0);
        PRESSED = new <init>("PRESSED", 1);
        RELEASED = new <init>("RELEASED", 2);
        e_3B_.clone aclone[] = new <init>[3];
        aclone[0] = UNKNOWN;
        aclone[1] = PRESSED;
        aclone[2] = RELEASED;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
