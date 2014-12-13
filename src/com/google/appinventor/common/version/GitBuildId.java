// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.common.version;


public final class GitBuildId
{

    public static final String ACRA_URI = "${acra.uri}";
    public static final String ANT_BUILD_DATE = "November 20 2014";
    public static final String GIT_BUILD_FINGERPRINT = "4691144ce0b11fa5ce7766ad7d1400cc8f184d9a";
    public static final String GIT_BUILD_VERSION = "nb139";

    private GitBuildId()
    {
    }

    public static String getAcraUri()
    {
        if ("${acra.uri}".equals("${acra.uri}"))
        {
            return "";
        } else
        {
            return "${acra.uri}".trim();
        }
    }

    public static String getDate()
    {
        return "November 20 2014";
    }

    public static String getFingerprint()
    {
        return "4691144ce0b11fa5ce7766ad7d1400cc8f184d9a";
    }

    public static String getVersion()
    {
        String s = "nb139";
        if (s == "" || s.contains(" "))
        {
            s = "none";
        }
        return s;
    }
}
