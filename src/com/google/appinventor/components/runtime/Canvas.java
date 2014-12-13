// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import com.google.appinventor.components.runtime.util.BoundingBox;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.PaintUtil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

// Referenced classes of package com.google.appinventor.components.runtime:
//            AndroidViewComponent, ComponentContainer, Form, EventDispatcher, 
//            Sprite

public final class Canvas extends AndroidViewComponent
    implements ComponentContainer
{
    private final class CanvasView extends View
    {

        private BitmapDrawable backgroundDrawable;
        private Bitmap bitmap;
        private android.graphics.Canvas canvas;
        private Bitmap completeCache;
        private Bitmap scaledBackgroundBitmap;
        final Canvas this$0;

        private Bitmap buildCache()
        {
            setDrawingCacheEnabled(true);
            destroyDrawingCache();
            Bitmap bitmap1 = getDrawingCache();
            if (bitmap1 == null)
            {
                int i = getWidth();
                int j = getHeight();
                bitmap1 = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
                android.graphics.Canvas canvas1 = new android.graphics.Canvas(bitmap1);
                layout(0, 0, i, j);
                draw(canvas1);
            }
            return bitmap1;
        }

        private void clearDrawingLayer()
        {
            canvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
            invalidate();
        }

        private void drawTextAtAngle(String s, int i, int j, float f)
        {
            canvas.save();
            canvas.rotate(-f, i, j);
            canvas.drawText(s, i, j, paint);
            canvas.restore();
            invalidate();
        }

        private int getBackgroundPixelColor(int i, int j)
        {
            if (i >= 0 && i < bitmap.getWidth() && j >= 0 && j < bitmap.getHeight()) goto _L2; else goto _L1
_L1:
            int k = 0xffffff;
_L4:
            return k;
_L2:
            int l;
            try
            {
                k = bitmap.getPixel(i, j);
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                Log.e("Canvas", String.format("Returning COLOR_NONE (exception) from getBackgroundPixelColor.", new Object[0]));
                return 0xffffff;
            }
            if (k != 0) goto _L4; else goto _L3
_L3:
            if (backgroundDrawable != null)
            {
                if (scaledBackgroundBitmap == null)
                {
                    scaledBackgroundBitmap = Bitmap.createScaledBitmap(backgroundDrawable.getBitmap(), bitmap.getWidth(), bitmap.getHeight(), false);
                }
                return scaledBackgroundBitmap.getPixel(i, j);
            }
            if (Color.alpha(backgroundColor) == 0)
            {
                break MISSING_BLOCK_LABEL_127;
            }
            l = backgroundColor;
            return l;
            return 0xffffff;
        }

        private int getPixelColor(int i, int j)
        {
            if (i < 0 || i >= bitmap.getWidth() || j < 0 || j >= bitmap.getHeight())
            {
                return 0xffffff;
            }
            if (completeCache == null)
            {
                Iterator iterator = sprites.iterator();
                boolean flag1;
                do
                {
                    boolean flag = iterator.hasNext();
                    flag1 = false;
                    if (!flag)
                    {
                        break;
                    }
                    if (!((Sprite)iterator.next()).Visible())
                    {
                        continue;
                    }
                    flag1 = true;
                    break;
                } while (true);
                if (!flag1)
                {
                    return getBackgroundPixelColor(i, j);
                }
                completeCache = buildCache();
            }
            int k;
            try
            {
                k = completeCache.getPixel(i, j);
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                Log.e("Canvas", String.format("Returning COLOR_NONE (exception) from getPixelColor.", new Object[0]));
                return 0xffffff;
            }
            return k;
        }

        private int getSize(int i, int j)
        {
            int k = android.view.View.MeasureSpec.getMode(i);
            int l = android.view.View.MeasureSpec.getSize(i);
            int i1;
            if (k == 0x40000000)
            {
                i1 = l;
            } else
            {
                i1 = j;
                if (k == 0x80000000)
                {
                    return Math.min(i1, l);
                }
            }
            return i1;
        }

        public void onDraw(android.graphics.Canvas canvas1)
        {
            completeCache = null;
            super.onDraw(canvas1);
            canvas1.drawBitmap(bitmap, 0.0F, 0.0F, null);
            for (Iterator iterator = sprites.iterator(); iterator.hasNext(); ((Sprite)iterator.next()).onDraw(canvas1)) { }
            drawn = true;
        }

        protected void onMeasure(int i, int j)
        {
            int k;
            int l;
            if (backgroundDrawable != null)
            {
                Bitmap bitmap1 = backgroundDrawable.getBitmap();
                k = bitmap1.getWidth();
                l = bitmap1.getHeight();
            } else
            {
                k = 32;
                l = 48;
            }
            setMeasuredDimension(getSize(i, k), getSize(j, l));
        }

        protected void onSizeChanged(int i, int j, int k, int l)
        {
            int i1;
            int j1;
            i1 = bitmap.getWidth();
            j1 = bitmap.getHeight();
            if (i == i1 && j == j1) goto _L2; else goto _L1
_L1:
            Bitmap bitmap1 = bitmap;
            Bitmap bitmap2 = Bitmap.createScaledBitmap(bitmap1, i, j, false);
            if (!bitmap2.isMutable()) goto _L4; else goto _L3
_L3:
            bitmap = bitmap2;
            canvas = new android.graphics.Canvas(bitmap);
_L6:
            scaledBackgroundBitmap = null;
_L2:
            return;
_L4:
            try
            {
                bitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
                canvas = new android.graphics.Canvas(bitmap);
                Rect rect = new Rect(0, 0, i1, j1);
                RectF rectf = new RectF(0.0F, 0.0F, i, j);
                canvas.drawBitmap(bitmap1, rect, rectf, null);
            }
            catch (IllegalArgumentException illegalargumentexception)
            {
                Log.e("Canvas", (new StringBuilder()).append("Bad values to createScaledBimap w = ").append(i).append(", h = ").append(j).toString());
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        public boolean onTouchEvent(MotionEvent motionevent)
        {
            container.$form().dontGrabTouchEventsForComponent();
            motionEventParser.parse(motionevent);
            mGestureDetector.onTouchEvent(motionevent);
            return true;
        }

        public void setBackgroundColor(int i)
        {
            backgroundColor = i;
            if (backgroundDrawable == null)
            {
                super.setBackgroundColor(i);
            }
            clearDrawingLayer();
        }

        void setBackgroundImage(String s)
        {
            Canvas canvas1 = Canvas.this;
            if (s == null)
            {
                s = "";
            }
            canvas1.backgroundImagePath = s;
            backgroundDrawable = null;
            scaledBackgroundBitmap = null;
            if (!TextUtils.isEmpty(backgroundImagePath))
            {
                try
                {
                    backgroundDrawable = MediaUtil.getBitmapDrawable(container.$form(), backgroundImagePath);
                }
                catch (IOException ioexception)
                {
                    Log.e("Canvas", (new StringBuilder()).append("Unable to load ").append(backgroundImagePath).toString());
                }
            }
            setBackgroundDrawable(backgroundDrawable);
            if (backgroundDrawable == null)
            {
                super.setBackgroundColor(backgroundColor);
            }
            clearDrawingLayer();
        }








        public CanvasView(Context context1)
        {
            this$0 = Canvas.this;
            super(context1);
            bitmap = Bitmap.createBitmap(32, 48, android.graphics.Bitmap.Config.ARGB_8888);
            canvas = new android.graphics.Canvas(bitmap);
        }
    }

    class FlingGestureListener extends android.view.GestureDetector.SimpleOnGestureListener
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
            Iterator iterator = sprites.iterator();
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

        FlingGestureListener()
        {
            this$0 = Canvas.this;
            super();
        }
    }

    class MotionEventParser
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
        //                       0 129
        //                       1 608
        //                       2 278;
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
            Iterator iterator4 = sprites.iterator();
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
                Iterator iterator2 = sprites.iterator();
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

        MotionEventParser()
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


    private static final int DEFAULT_BACKGROUND_COLOR = -1;
    private static final float DEFAULT_LINE_WIDTH = 2F;
    private static final int DEFAULT_PAINT_COLOR = 0xff000000;
    private static final int FLING_INTERVAL = 1000;
    private static final String LOG_TAG = "Canvas";
    private static final int MIN_WIDTH_HEIGHT = 1;
    private int backgroundColor;
    private String backgroundImagePath;
    private final Activity context;
    private boolean drawn;
    private final GestureDetector mGestureDetector;
    private final MotionEventParser motionEventParser = new MotionEventParser();
    private final Paint paint = new Paint();
    private int paintColor;
    private final List sprites = new LinkedList();
    private int textAlignment;
    private final CanvasView view;

    public Canvas(ComponentContainer componentcontainer)
    {
        super(componentcontainer);
        backgroundImagePath = "";
        context = componentcontainer.$context();
        view = new CanvasView(context);
        componentcontainer.$add(this);
        paint.setStrokeWidth(2.0F);
        PaintColor(0xff000000);
        BackgroundColor(-1);
        TextAlignment(0);
        FontSize(14F);
        mGestureDetector = new GestureDetector(context, new FlingGestureListener());
    }

    private void changePaint(Paint paint1, int i)
    {
        if (i == 0)
        {
            PaintUtil.changePaint(paint1, 0xff000000);
            return;
        }
        if (i == 0xffffff)
        {
            PaintUtil.changePaintTransparent(paint1);
            return;
        } else
        {
            PaintUtil.changePaint(paint1, i);
            return;
        }
    }

    private String saveFile(File file, android.graphics.Bitmap.CompressFormat compressformat, String s)
    {
        FileOutputStream fileoutputstream = new FileOutputStream(file);
        if (view.completeCache != null) goto _L2; else goto _L1
_L1:
        Bitmap bitmap1 = view.buildCache();
        Bitmap bitmap = bitmap1;
_L4:
        boolean flag = bitmap.compress(compressformat, 100, fileoutputstream);
        fileoutputstream.close();
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        return file.getAbsolutePath();
_L2:
        bitmap = view.completeCache;
        if (true) goto _L4; else goto _L3
        Exception exception;
        exception;
        try
        {
            fileoutputstream.close();
            throw exception;
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            Form form1 = container.$form();
            Object aobj1[] = new Object[1];
            aobj1[0] = file.getAbsolutePath();
            form1.dispatchErrorOccurredEvent(this, s, 707, aobj1);
        }
        catch (IOException ioexception)
        {
            Form form = container.$form();
            Object aobj[] = new Object[1];
            aobj[0] = ioexception.getMessage();
            form.dispatchErrorOccurredEvent(this, s, 708, aobj);
        }
        return "";
_L3:
        container.$form().dispatchErrorOccurredEvent(this, s, 1001, new Object[0]);
        break MISSING_BLOCK_LABEL_121;
    }

    public void $add(AndroidViewComponent androidviewcomponent)
    {
        throw new UnsupportedOperationException("Canvas.$add() called");
    }

    public Activity $context()
    {
        return context;
    }

    public Form $form()
    {
        return container.$form();
    }

    public int BackgroundColor()
    {
        return backgroundColor;
    }

    public void BackgroundColor(int i)
    {
        view.setBackgroundColor(i);
    }

    public String BackgroundImage()
    {
        return backgroundImagePath;
    }

    public void BackgroundImage(String s)
    {
        view.setBackgroundImage(s);
    }

    public void Clear()
    {
        view.clearDrawingLayer();
    }

    public void Dragged(float f, float f1, float f2, float f3, float f4, float f5, boolean flag)
    {
        Object aobj[] = new Object[7];
        aobj[0] = Float.valueOf(f);
        aobj[1] = Float.valueOf(f1);
        aobj[2] = Float.valueOf(f2);
        aobj[3] = Float.valueOf(f3);
        aobj[4] = Float.valueOf(f4);
        aobj[5] = Float.valueOf(f5);
        aobj[6] = Boolean.valueOf(flag);
        EventDispatcher.dispatchEvent(this, "Dragged", aobj);
    }

    public void DrawCircle(int i, int j, float f)
    {
        view.canvas.drawCircle(i, j, f, paint);
        view.invalidate();
    }

    public void DrawLine(int i, int j, int k, int l)
    {
        view.canvas.drawLine(i, j, k, l, paint);
        view.invalidate();
    }

    public void DrawPoint(int i, int j)
    {
        view.canvas.drawPoint(i, j, paint);
        view.invalidate();
    }

    public void DrawText(String s, int i, int j)
    {
        view.canvas.drawText(s, i, j, paint);
        view.invalidate();
    }

    public void DrawTextAtAngle(String s, int i, int j, float f)
    {
        view.drawTextAtAngle(s, i, j, f);
    }

    public void Flung(float f, float f1, float f2, float f3, float f4, float f5, boolean flag)
    {
        Object aobj[] = new Object[7];
        aobj[0] = Float.valueOf(f);
        aobj[1] = Float.valueOf(f1);
        aobj[2] = Float.valueOf(f2);
        aobj[3] = Float.valueOf(f3);
        aobj[4] = Float.valueOf(f4);
        aobj[5] = Float.valueOf(f5);
        aobj[6] = Boolean.valueOf(flag);
        EventDispatcher.dispatchEvent(this, "Flung", aobj);
    }

    public float FontSize()
    {
        return paint.getTextSize();
    }

    public void FontSize(float f)
    {
        paint.setTextSize(f);
    }

    public int GetBackgroundPixelColor(int i, int j)
    {
        return view.getBackgroundPixelColor(i, j);
    }

    public int GetPixelColor(int i, int j)
    {
        return view.getPixelColor(i, j);
    }

    public void Height(int i)
    {
        if (i > 0 || i == -2 || i == -1)
        {
            super.Height(i);
            return;
        } else
        {
            container.$form().dispatchErrorOccurredEvent(this, "Height", 1003, new Object[0]);
            return;
        }
    }

    public float LineWidth()
    {
        return paint.getStrokeWidth();
    }

    public void LineWidth(float f)
    {
        paint.setStrokeWidth(f);
    }

    public int PaintColor()
    {
        return paintColor;
    }

    public void PaintColor(int i)
    {
        paintColor = i;
        changePaint(paint, i);
    }

    public String Save()
    {
        String s = saveFile(FileUtil.getPictureFile("png"), android.graphics.Bitmap.CompressFormat.PNG, "Save");
        return s;
        IOException ioexception;
        ioexception;
        Form form = container.$form();
        Object aobj[] = new Object[1];
        aobj[0] = ioexception.getMessage();
        form.dispatchErrorOccurredEvent(this, "Save", 708, aobj);
_L2:
        return "";
        com.google.appinventor.components.runtime.util.FileUtil.FileException fileexception;
        fileexception;
        container.$form().dispatchErrorOccurredEvent(this, "Save", fileexception.getErrorMessageNumber(), new Object[0]);
        if (true) goto _L2; else goto _L1
_L1:
    }

    public String SaveAs(String s)
    {
        android.graphics.Bitmap.CompressFormat compressformat;
        String s1;
        if (s.endsWith(".jpg") || s.endsWith(".jpeg"))
        {
            compressformat = android.graphics.Bitmap.CompressFormat.JPEG;
        } else
        if (s.endsWith(".png"))
        {
            compressformat = android.graphics.Bitmap.CompressFormat.PNG;
        } else
        if (!s.contains("."))
        {
            s = (new StringBuilder()).append(s).append(".png").toString();
            compressformat = android.graphics.Bitmap.CompressFormat.PNG;
        } else
        {
            container.$form().dispatchErrorOccurredEvent(this, "SaveAs", 706, new Object[0]);
            return "";
        }
        s1 = saveFile(FileUtil.getExternalFile(s), compressformat, "SaveAs");
        return s1;
        IOException ioexception;
        ioexception;
        Form form = container.$form();
        Object aobj[] = new Object[1];
        aobj[0] = ioexception.getMessage();
        form.dispatchErrorOccurredEvent(this, "SaveAs", 708, aobj);
_L2:
        return "";
        com.google.appinventor.components.runtime.util.FileUtil.FileException fileexception;
        fileexception;
        container.$form().dispatchErrorOccurredEvent(this, "SaveAs", fileexception.getErrorMessageNumber(), new Object[0]);
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void SetBackgroundPixelColor(int i, int j, int k)
    {
        Paint paint1 = new Paint();
        PaintUtil.changePaint(paint1, k);
        view.canvas.drawPoint(i, j, paint1);
        view.invalidate();
    }

    public int TextAlignment()
    {
        return textAlignment;
    }

    public void TextAlignment(int i)
    {
        textAlignment = i;
        switch (i)
        {
        default:
            return;

        case 0: // '\0'
            paint.setTextAlign(android.graphics.Paint.Align.LEFT);
            return;

        case 1: // '\001'
            paint.setTextAlign(android.graphics.Paint.Align.CENTER);
            return;

        case 2: // '\002'
            paint.setTextAlign(android.graphics.Paint.Align.RIGHT);
            break;
        }
    }

    public void TouchDown(float f, float f1)
    {
        Object aobj[] = new Object[2];
        aobj[0] = Float.valueOf(f);
        aobj[1] = Float.valueOf(f1);
        EventDispatcher.dispatchEvent(this, "TouchDown", aobj);
    }

    public void TouchUp(float f, float f1)
    {
        Object aobj[] = new Object[2];
        aobj[0] = Float.valueOf(f);
        aobj[1] = Float.valueOf(f1);
        EventDispatcher.dispatchEvent(this, "TouchUp", aobj);
    }

    public void Touched(float f, float f1, boolean flag)
    {
        Object aobj[] = new Object[3];
        aobj[0] = Float.valueOf(f);
        aobj[1] = Float.valueOf(f1);
        aobj[2] = Boolean.valueOf(flag);
        EventDispatcher.dispatchEvent(this, "Touched", aobj);
    }

    public void Width(int i)
    {
        if (i > 0 || i == -2 || i == -1)
        {
            super.Width(i);
            return;
        } else
        {
            container.$form().dispatchErrorOccurredEvent(this, "Width", 1002, new Object[0]);
            return;
        }
    }

    void addSprite(Sprite sprite)
    {
        for (int i = 0; i < sprites.size(); i++)
        {
            if (((Sprite)sprites.get(i)).Z() > sprite.Z())
            {
                sprites.add(i, sprite);
                return;
            }
        }

        sprites.add(sprite);
    }

    void changeSpriteLayer(Sprite sprite)
    {
        removeSprite(sprite);
        addSprite(sprite);
        view.invalidate();
    }

    protected void findSpriteCollisions(Sprite sprite)
    {
        Iterator iterator = sprites.iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Sprite sprite1 = (Sprite)iterator.next();
            if (sprite1 != sprite)
            {
                if (sprite.CollidingWith(sprite1))
                {
                    if (!sprite.Visible() || !sprite.Enabled() || !sprite1.Visible() || !sprite1.Enabled() || !Sprite.colliding(sprite1, sprite))
                    {
                        sprite.NoLongerCollidingWith(sprite1);
                        sprite1.NoLongerCollidingWith(sprite);
                    }
                } else
                if (sprite.Visible() && sprite.Enabled() && sprite1.Visible() && sprite1.Enabled() && Sprite.colliding(sprite1, sprite))
                {
                    sprite.CollidedWith(sprite1);
                    sprite1.CollidedWith(sprite);
                }
            }
        } while (true);
    }

    public View getView()
    {
        return view;
    }

    public boolean ready()
    {
        return drawn;
    }

    void registerChange(Sprite sprite)
    {
        view.invalidate();
        findSpriteCollisions(sprite);
    }

    void removeSprite(Sprite sprite)
    {
        sprites.remove(sprite);
    }

    public void setChildHeight(AndroidViewComponent androidviewcomponent, int i)
    {
        throw new UnsupportedOperationException("Canvas.setChildHeight() called");
    }

    public void setChildWidth(AndroidViewComponent androidviewcomponent, int i)
    {
        throw new UnsupportedOperationException("Canvas.setChildWidth() called");
    }



/*
    static boolean access$102(Canvas canvas, boolean flag)
    {
        canvas.drawn = flag;
        return flag;
    }

*/





/*
    static String access$402(Canvas canvas, String s)
    {
        canvas.backgroundImagePath = s;
        return s;
    }

*/



/*
    static int access$502(Canvas canvas, int i)
    {
        canvas.backgroundColor = i;
        return i;
    }

*/

}
