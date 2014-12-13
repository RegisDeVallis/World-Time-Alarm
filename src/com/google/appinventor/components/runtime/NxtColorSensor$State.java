// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;


// Referenced classes of package com.google.appinventor.components.runtime:
//            NxtColorSensor

private static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES ABOVE_RANGE;
    public static final .VALUES BELOW_RANGE;
    public static final .VALUES UNKNOWN;
    public static final .VALUES WITHIN_RANGE;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/google/appinventor/components/runtime/NxtColorSensor$State, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        UNKNOWN = new <init>("UNKNOWN", 0);
        BELOW_RANGE = new <init>("BELOW_RANGE", 1);
        WITHIN_RANGE = new <init>("WITHIN_RANGE", 2);
        ABOVE_RANGE = new <init>("ABOVE_RANGE", 3);
        e_3B_.clone aclone[] = new <init>[4];
        aclone[0] = UNKNOWN;
        aclone[1] = BELOW_RANGE;
        aclone[2] = WITHIN_RANGE;
        aclone[3] = ABOVE_RANGE;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
