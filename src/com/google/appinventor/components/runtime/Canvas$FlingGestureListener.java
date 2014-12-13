// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.view.MotionEvent;
import com.google.appinventor.components.runtime.util.BoundingBox;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.appinventor.components.runtime:
//            Canvas, Sprite

class this._cls0 extends android.view.ureListener
{

    final Canvas this$0;

    public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
    {
        float f2 = Math.max(0, (int)motionevent.getX());
        float f3 = Math.max(0, (int)motionevent.getY());
        float f4 = f / 1000F;
        float f5 = f1 / 1000F;
        float f6 = (float)Math.sqrt(f4 * f4 + f5 * f5);
        float f7 = (float)(-Math.toDegrees(Math.atan2(f5, f4)));
        int i = Width();
        int j = Height();
        BoundingBox boundingbox = new BoundingBox(Math.max(0, -12 + (int)f2), Math.max(0, -12 + (int)f3), Math.min(i - 1, 12 + (int)f2), Math.min(j - 1, 12 + (int)f3));
        boolean flag = false;
        Iterator iterator = Canvas.access$000(Canvas.this).iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Sprite sprite = (Sprite)iterator.next();
            if (sprite.Enabled() && sprite.Visible() && sprite.intersectsWith(boundingbox))
            {
                sprite.Flung(f2, f3, f6, f7, f4, f5);
                flag = true;
            }
        } while (true);
        Flung(f2, f3, f6, f7, f4, f5, flag);
        return true;
    }

    ()
    {
        this$0 = Canvas.this;
        super();
    }
}
