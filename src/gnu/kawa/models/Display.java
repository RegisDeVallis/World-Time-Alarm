// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.models;

import gnu.lists.FString;
import gnu.mapping.ThreadLocation;
import gnu.mapping.WrappedException;
import java.awt.Dimension;
import java.awt.geom.Dimension2D;
import java.lang.reflect.Method;

// Referenced classes of package gnu.kawa.models:
//            Label, Model, Box, Button, 
//            DrawImage, Spacer, Text, Window

public abstract class Display
{

    public static ThreadLocation myDisplay = new ThreadLocation("my-display");

    public Display()
    {
    }

    public static Dimension asDimension(Dimension2D dimension2d)
    {
        if ((dimension2d instanceof Dimension) || dimension2d == null)
        {
            return (Dimension)dimension2d;
        } else
        {
            return new Dimension((int)(0.5D + dimension2d.getWidth()), (int)(0.5D + dimension2d.getHeight()));
        }
    }

    public static Display getInstance()
    {
        Object obj;
        String s;
        String s1;
        obj = myDisplay.get(null);
        if (obj instanceof Display)
        {
            return (Display)obj;
        }
        Class aclass[];
        int i;
        Display display;
        if (obj == null)
        {
            s = "swing";
        } else
        {
            s = obj.toString();
        }
        aclass = new Class[0];
_L6:
        i = s.indexOf(',');
        s1 = null;
        if (i >= 0)
        {
            s1 = s.substring(i + 1);
            s = s.substring(0, i);
        }
        if (!s.equals("swing")) goto _L2; else goto _L1
_L1:
        s = "gnu.kawa.swingviews.SwingDisplay";
_L4:
        display = (Display)Class.forName(s).getDeclaredMethod("getInstance", aclass).invoke(null, new Object[0]);
        return display;
_L2:
        if (s.equals("swt"))
        {
            s = "gnu.kawa.swtviews.SwtDisplay";
        } else
        if (s.equals("echo2"))
        {
            s = "gnu.kawa.echo2.Echo2Display";
        }
        if (true) goto _L4; else goto _L3
_L3:
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        if (s1 == null)
        {
            throw new RuntimeException((new StringBuilder()).append("no display toolkit: ").append(obj).toString());
        }
        s = s1;
        continue; /* Loop/switch isn't completed */
        Throwable throwable;
        throwable;
        throw WrappedException.wrapIfNeeded(throwable);
        if (true) goto _L6; else goto _L5
_L5:
    }

    public abstract void addBox(Box box, Object obj);

    public abstract void addButton(Button button, Object obj);

    public abstract void addImage(DrawImage drawimage, Object obj);

    public abstract void addLabel(Label label, Object obj);

    public void addSpacer(Spacer spacer, Object obj)
    {
        throw new Error("makeView called on Spacer");
    }

    public void addText(Text text, Object obj)
    {
        throw new Error("makeView called on Text");
    }

    public abstract void addView(Object obj, Object obj1);

    public Model coerceToModel(Object obj)
    {
        if ((obj instanceof FString) || (obj instanceof String))
        {
            return new Label(obj.toString());
        } else
        {
            return (Model)obj;
        }
    }

    public abstract Window makeWindow();

}
