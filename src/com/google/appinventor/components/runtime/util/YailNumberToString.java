// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import java.text.DecimalFormat;

public final class YailNumberToString
{

    private static final double BIGBOUND = 1000000D;
    private static final double SMALLBOUND = 9.9999999999999995E-07D;
    private static final String decPattern = "#####0.0####";
    private static final DecimalFormat formatterDec = new DecimalFormat("#####0.0####");
    private static final DecimalFormat formatterSci = new DecimalFormat("0.####E0");
    private static final String sciPattern = "0.####E0";

    public YailNumberToString()
    {
    }

    public static String format(double d)
    {
        double d1 = Math.abs(d);
        if (d1 < 1000000D && d1 > 9.9999999999999995E-07D)
        {
            return formatterDec.format(d);
        } else
        {
            return formatterSci.format(d);
        }
    }

}
