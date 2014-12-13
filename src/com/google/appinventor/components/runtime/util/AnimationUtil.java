// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

// Referenced classes of package com.google.appinventor.components.runtime.util:
//            SdkLevel, EclairUtil

public final class AnimationUtil
{

    private AnimationUtil()
    {
    }

    public static void ApplyAnimation(View view, String s)
    {
        if (s.equals("ScrollRightSlow"))
        {
            ApplyHorizontalScrollAnimation(view, false, 8000);
        } else
        {
            if (s.equals("ScrollRight"))
            {
                ApplyHorizontalScrollAnimation(view, false, 4000);
                return;
            }
            if (s.equals("ScrollRightFast"))
            {
                ApplyHorizontalScrollAnimation(view, false, 1000);
                return;
            }
            if (s.equals("ScrollLeftSlow"))
            {
                ApplyHorizontalScrollAnimation(view, true, 8000);
                return;
            }
            if (s.equals("ScrollLeft"))
            {
                ApplyHorizontalScrollAnimation(view, true, 4000);
                return;
            }
            if (s.equals("ScrollLeftFast"))
            {
                ApplyHorizontalScrollAnimation(view, true, 1000);
                return;
            }
            if (s.equals("Stop"))
            {
                view.clearAnimation();
                return;
            }
        }
    }

    public static void ApplyCloseScreenAnimation(Activity activity, String s)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        int j;
        if (SdkLevel.getLevel() <= 4)
        {
            Log.e("AnimationUtil", "Screen animations are not available on android versions less than 2.0.");
            return;
        }
        if (s.equalsIgnoreCase("fade"))
        {
            j = activity.getResources().getIdentifier("fadeout", "anim", activity.getPackageName());
            i = activity.getResources().getIdentifier("hold", "anim", activity.getPackageName());
        } else
        if (s.equalsIgnoreCase("zoom"))
        {
            j = activity.getResources().getIdentifier("zoom_exit_reverse", "anim", activity.getPackageName());
            i = activity.getResources().getIdentifier("zoom_enter_reverse", "anim", activity.getPackageName());
        } else
        if (s.equalsIgnoreCase("slidehorizontal"))
        {
            j = activity.getResources().getIdentifier("slide_exit_reverse", "anim", activity.getPackageName());
            i = activity.getResources().getIdentifier("slide_enter_reverse", "anim", activity.getPackageName());
        } else
        {
            if (!s.equalsIgnoreCase("slidevertical"))
            {
                continue; /* Loop/switch isn't completed */
            }
            j = activity.getResources().getIdentifier("slide_v_exit_reverse", "anim", activity.getPackageName());
            i = activity.getResources().getIdentifier("slide_v_enter_reverse", "anim", activity.getPackageName());
        }
_L4:
        EclairUtil.overridePendingTransitions(activity, i, j);
        return;
        if (!s.equalsIgnoreCase("none")) goto _L1; else goto _L3
_L3:
        i = 0;
        j = 0;
          goto _L4
        if (true) goto _L1; else goto _L5
_L5:
    }

    private static void ApplyHorizontalScrollAnimation(View view, boolean flag, int i)
    {
        float f;
        AnimationSet animationset;
        TranslateAnimation translateanimation;
        if (flag)
        {
            f = 1.0F;
        } else
        {
            f = -1F;
        }
        animationset = new AnimationSet(true);
        animationset.setRepeatCount(-1);
        animationset.setRepeatMode(1);
        translateanimation = new TranslateAnimation(2, 0.7F * f, 2, f * -0.7F, 2, 0.0F, 2, 0.0F);
        translateanimation.setStartOffset(0L);
        translateanimation.setDuration(i);
        translateanimation.setFillAfter(true);
        animationset.addAnimation(translateanimation);
        view.startAnimation(animationset);
    }

    public static void ApplyOpenScreenAnimation(Activity activity, String s)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        int j;
        if (SdkLevel.getLevel() <= 4)
        {
            Log.e("AnimationUtil", "Screen animations are not available on android versions less than 2.0.");
            return;
        }
        if (s.equalsIgnoreCase("fade"))
        {
            i = activity.getResources().getIdentifier("fadein", "anim", activity.getPackageName());
            j = activity.getResources().getIdentifier("hold", "anim", activity.getPackageName());
        } else
        if (s.equalsIgnoreCase("zoom"))
        {
            j = activity.getResources().getIdentifier("zoom_exit", "anim", activity.getPackageName());
            i = activity.getResources().getIdentifier("zoom_enter", "anim", activity.getPackageName());
        } else
        if (s.equalsIgnoreCase("slidehorizontal"))
        {
            j = activity.getResources().getIdentifier("slide_exit", "anim", activity.getPackageName());
            i = activity.getResources().getIdentifier("slide_enter", "anim", activity.getPackageName());
        } else
        {
            if (!s.equalsIgnoreCase("slidevertical"))
            {
                continue; /* Loop/switch isn't completed */
            }
            j = activity.getResources().getIdentifier("slide_v_exit", "anim", activity.getPackageName());
            i = activity.getResources().getIdentifier("slide_v_enter", "anim", activity.getPackageName());
        }
_L4:
        EclairUtil.overridePendingTransitions(activity, i, j);
        return;
        if (!s.equalsIgnoreCase("none")) goto _L1; else goto _L3
_L3:
        i = 0;
        j = 0;
          goto _L4
        if (true) goto _L1; else goto _L5
_L5:
    }
}
