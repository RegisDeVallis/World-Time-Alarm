// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import android.content.res.ColorStateList;
import android.graphics.Typeface;
import android.widget.TextView;

public class TextViewUtil
{

    private TextViewUtil()
    {
    }

    public static float getFontSize(TextView textview)
    {
        return textview.getTextSize();
    }

    public static String getText(TextView textview)
    {
        return textview.getText().toString();
    }

    public static boolean isEnabled(TextView textview)
    {
        return textview.isEnabled();
    }

    public static void setAlignment(TextView textview, int i, boolean flag)
    {
        i;
        JVM INSTR tableswitch 0 2: default 28
    //                   0 36
    //                   1 59
    //                   2 64;
           goto _L1 _L2 _L3 _L4
_L4:
        break MISSING_BLOCK_LABEL_64;
_L1:
        throw new IllegalArgumentException();
_L2:
        byte byte0 = 3;
_L5:
        byte byte1;
        if (flag)
        {
            byte1 = 16;
        } else
        {
            byte1 = 48;
        }
        textview.setGravity(byte0 | byte1);
        textview.invalidate();
        return;
_L3:
        byte0 = 1;
          goto _L5
        byte0 = 5;
          goto _L5
    }

    public static void setBackgroundColor(TextView textview, int i)
    {
        textview.setBackgroundColor(i);
        textview.invalidate();
    }

    public static void setEnabled(TextView textview, boolean flag)
    {
        textview.setEnabled(flag);
        textview.invalidate();
    }

    public static void setFontSize(TextView textview, float f)
    {
        textview.setTextSize(f);
        textview.requestLayout();
    }

    public static void setFontTypeface(TextView textview, int i, boolean flag, boolean flag1)
    {
        i;
        JVM INSTR tableswitch 0 3: default 32
    //                   0 40
    //                   1 91
    //                   2 83
    //                   3 99;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        throw new IllegalArgumentException();
_L2:
        Typeface typeface = Typeface.DEFAULT;
_L7:
        int j = 0;
        if (flag)
        {
            j = false | true;
        }
        if (flag1)
        {
            j |= 2;
        }
        textview.setTypeface(Typeface.create(typeface, j));
        textview.requestLayout();
        return;
_L4:
        typeface = Typeface.SERIF;
        continue; /* Loop/switch isn't completed */
_L3:
        typeface = Typeface.SANS_SERIF;
        continue; /* Loop/switch isn't completed */
_L5:
        typeface = Typeface.MONOSPACE;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public static void setText(TextView textview, String s)
    {
        textview.setText(s);
        textview.requestLayout();
    }

    public static void setTextColor(TextView textview, int i)
    {
        textview.setTextColor(i);
        textview.invalidate();
    }

    public static void setTextColors(TextView textview, ColorStateList colorstatelist)
    {
        textview.setTextColor(colorstatelist);
    }
}
