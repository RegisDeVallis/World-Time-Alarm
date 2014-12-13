// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.util.Log;
import android.view.MotionEvent;
import com.google.appinventor.components.runtime.util.BoundingBox;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.appinventor.components.runtime:
//            Canvas, Sprite

class drag
{

    public static final int FINGER_HEIGHT = 24;
    public static final int FINGER_WIDTH = 24;
    private static final int HALF_FINGER_HEIGHT = 12;
    private static final int HALF_FINGER_WIDTH = 12;
    public static final int TAP_THRESHOLD = 30;
    private static final int UNSET = -1;
    private boolean drag;
    private final List draggedSprites = new ArrayList();
    private boolean isDrag;
    private float lastX;
    private float lastY;
    private float startX;
    private float startY;
    final Canvas this$0;

    void parse(MotionEvent motionevent)
    {
        float f;
        float f1;
        BoundingBox boundingbox;
        int i = Width();
        int j = Height();
        f = Math.max(0, (int)motionevent.getX());
        f1 = Math.max(0, (int)motionevent.getY());
        boundingbox = new BoundingBox(Math.max(0, -12 + (int)f), Math.max(0, -12 + (int)f1), Math.min(i - 1, 12 + (int)f), Math.min(j - 1, 12 + (int)f1));
        motionevent.getAction();
        JVM INSTR tableswitch 0 2: default 128
    //                   0 129
    //                   1 608
    //                   2 278;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        draggedSprites.clear();
        startX = f;
        startY = f1;
        lastX = f;
        lastY = f1;
        drag = false;
        isDrag = false;
        Iterator iterator4 = Canvas.access$000(Canvas.this).iterator();
        do
        {
            if (!iterator4.hasNext())
            {
                break;
            }
            Sprite sprite4 = (Sprite)iterator4.next();
            if (sprite4.Enabled() && sprite4.Visible() && sprite4.intersectsWith(boundingbox))
            {
                draggedSprites.add(sprite4);
                sprite4.TouchDown(startX, startY);
            }
        } while (true);
        TouchDown(startX, startY);
        return;
_L4:
        if (startX == -1F || startY == -1F || lastX == -1F || lastY == -1F)
        {
            Log.w("Canvas", (new StringBuilder()).append("In Canvas.MotionEventParser.parse(), an ACTION_MOVE was passed without a preceding ACTION_DOWN: ").append(motionevent).toString());
        }
        if (isDrag || Math.abs(f - startX) >= 30F || Math.abs(f1 - startY) >= 30F)
        {
            isDrag = true;
            drag = true;
            Iterator iterator2 = Canvas.access$000(Canvas.this).iterator();
            do
            {
                if (!iterator2.hasNext())
                {
                    break;
                }
                Sprite sprite3 = (Sprite)iterator2.next();
                if (!draggedSprites.contains(sprite3) && sprite3.Enabled() && sprite3.Visible() && sprite3.intersectsWith(boundingbox))
                {
                    draggedSprites.add(sprite3);
                }
            } while (true);
            boolean flag1 = false;
            Iterator iterator3 = draggedSprites.iterator();
            do
            {
                if (!iterator3.hasNext())
                {
                    break;
                }
                Sprite sprite2 = (Sprite)iterator3.next();
                if (sprite2.Enabled() && sprite2.Visible())
                {
                    sprite2.Dragged(startX, startY, lastX, lastY, f, f1);
                    flag1 = true;
                }
            } while (true);
            Dragged(startX, startY, lastX, lastY, f, f1, flag1);
            lastX = f;
            lastY = f1;
            return;
        }
          goto _L1
_L3:
        if (!drag)
        {
            boolean flag = false;
            Iterator iterator1 = draggedSprites.iterator();
            do
            {
                if (!iterator1.hasNext())
                {
                    break;
                }
                Sprite sprite1 = (Sprite)iterator1.next();
                if (sprite1.Enabled() && sprite1.Visible())
                {
                    sprite1.Touched(f, f1);
                    sprite1.TouchUp(f, f1);
                    flag = true;
                }
            } while (true);
            Touched(f, f1, flag);
        } else
        {
            Iterator iterator = draggedSprites.iterator();
            while (iterator.hasNext()) 
            {
                Sprite sprite = (Sprite)iterator.next();
                if (sprite.Enabled() && sprite.Visible())
                {
                    sprite.Touched(f, f1);
                    sprite.TouchUp(f, f1);
                }
            }
        }
        TouchUp(f, f1);
        drag = false;
        startX = -1F;
        startY = -1F;
        lastX = -1F;
        lastY = -1F;
        return;
    }

    ()
    {
        this$0 = Canvas.this;
        super();
        startX = -1F;
        startY = -1F;
        lastX = -1F;
        lastY = -1F;
        isDrag = false;
        drag = false;
    }
}
