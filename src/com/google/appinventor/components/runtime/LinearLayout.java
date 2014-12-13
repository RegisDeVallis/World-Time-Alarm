// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.content.Context;
import android.view.ViewGroup;

// Referenced classes of package com.google.appinventor.components.runtime:
//            Layout, AndroidViewComponent

public final class LinearLayout
    implements Layout
{

    private final android.widget.LinearLayout layoutManager;

    LinearLayout(Context context, int i)
    {
        this(context, i, null, null);
    }

    LinearLayout(final Context final_context, int i, final Integer preferredEmptyWidth, Integer integer)
    {
        if (preferredEmptyWidth == null && integer != null || preferredEmptyWidth != null && integer == null)
        {
            throw new IllegalArgumentException("LinearLayout - preferredEmptyWidth and preferredEmptyHeight must be either both null or both not null");
        }
        layoutManager = new android.widget.LinearLayout(integer) {

            final LinearLayout this$0;
            final Integer val$preferredEmptyHeight;
            final Integer val$preferredEmptyWidth;

            private int getSize(int k, int l)
            {
                int i1 = android.view.View.MeasureSpec.getMode(k);
                int j1 = android.view.View.MeasureSpec.getSize(k);
                int k1;
                if (i1 == 0x40000000)
                {
                    k1 = j1;
                } else
                {
                    k1 = l;
                    if (i1 == 0x80000000)
                    {
                        return Math.min(k1, j1);
                    }
                }
                return k1;
            }

            protected void onMeasure(int k, int l)
            {
                if (preferredEmptyWidth == null || preferredEmptyHeight == null)
                {
                    super.onMeasure(k, l);
                    return;
                }
                if (getChildCount() != 0)
                {
                    super.onMeasure(k, l);
                    return;
                } else
                {
                    setMeasuredDimension(getSize(k, preferredEmptyWidth.intValue()), getSize(l, preferredEmptyHeight.intValue()));
                    return;
                }
            }

            
            {
                this$0 = LinearLayout.this;
                preferredEmptyWidth = integer;
                preferredEmptyHeight = integer1;
                super(final_context);
            }
        };
        android.widget.LinearLayout linearlayout = layoutManager;
        int j;
        if (i == 0)
        {
            j = 0;
        } else
        {
            j = 1;
        }
        linearlayout.setOrientation(j);
    }

    public void add(AndroidViewComponent androidviewcomponent)
    {
        layoutManager.addView(androidviewcomponent.getView(), new android.widget.LayoutParams(-2, -2, 0.0F));
    }

    public ViewGroup getLayoutManager()
    {
        return layoutManager;
    }

    public void setHorizontalGravity(int i)
    {
        layoutManager.setHorizontalGravity(i);
    }

    public void setVerticalGravity(int i)
    {
        layoutManager.setVerticalGravity(i);
    }
}
