// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;


// Referenced classes of package com.google.appinventor.components.runtime.util:
//            MediaUtil

private static final class  extends Enum
{

    private static final .VALUES $VALUES[];
    public static final .VALUES ASSET;
    public static final .VALUES CONTACT_URI;
    public static final .VALUES CONTENT_URI;
    public static final .VALUES FILE_URL;
    public static final .VALUES REPL_ASSET;
    public static final .VALUES SDCARD;
    public static final .VALUES URL;

    public static  valueOf(String s)
    {
        return ()Enum.valueOf(com/google/appinventor/components/runtime/util/MediaUtil$MediaSource, s);
    }

    public static [] values()
    {
        return ([])$VALUES.clone();
    }

    static 
    {
        ASSET = new <init>("ASSET", 0);
        REPL_ASSET = new <init>("REPL_ASSET", 1);
        SDCARD = new <init>("SDCARD", 2);
        FILE_URL = new <init>("FILE_URL", 3);
        URL = new <init>("URL", 4);
        CONTENT_URI = new <init>("CONTENT_URI", 5);
        CONTACT_URI = new <init>("CONTACT_URI", 6);
        e_3B_.clone aclone[] = new <init>[7];
        aclone[0] = ASSET;
        aclone[1] = REPL_ASSET;
        aclone[2] = SDCARD;
        aclone[3] = FILE_URL;
        aclone[4] = URL;
        aclone[5] = CONTENT_URI;
        aclone[6] = CONTACT_URI;
        $VALUES = aclone;
    }

    private (String s, int i)
    {
        super(s, i);
    }
}
