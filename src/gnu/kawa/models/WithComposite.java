// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.models;

import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

// Referenced classes of package gnu.kawa.models:
//            Paintable

public class WithComposite
    implements Paintable
{

    Composite composite[];
    Paintable paintable[];

    public WithComposite()
    {
    }

    public static WithComposite make(Paintable paintable1, Composite composite1)
    {
        WithComposite withcomposite = new WithComposite();
        withcomposite.paintable = (new Paintable[] {
            paintable1
        });
        withcomposite.composite = (new Composite[] {
            composite1
        });
        return withcomposite;
    }

    public static WithComposite make(Paintable apaintable[], Composite acomposite[])
    {
        WithComposite withcomposite = new WithComposite();
        withcomposite.paintable = apaintable;
        withcomposite.composite = acomposite;
        return withcomposite;
    }

    public static WithComposite make(Object aobj[])
    {
        int i = 0;
        int j = aobj.length;
        do
        {
            if (--j < 0)
            {
                break;
            }
            if (aobj[j] instanceof Paintable)
            {
                i++;
            }
        } while (true);
        Paintable apaintable[] = new Paintable[i];
        Composite acomposite[] = new Composite[i];
        Composite composite1 = null;
        int k = 0;
        int l = 0;
        while (l < aobj.length) 
        {
            Object obj = aobj[l];
            if (obj instanceof Paintable)
            {
                apaintable[k] = (Paintable)aobj[l];
                acomposite[k] = composite1;
                k++;
            } else
            {
                composite1 = (Composite)obj;
            }
            l++;
        }
        return make(apaintable, acomposite);
    }

    public Rectangle2D getBounds2D()
    {
        int i = paintable.length;
        Rectangle2D rectangle2d;
        if (i == 0)
        {
            rectangle2d = null;
        } else
        {
            rectangle2d = paintable[0].getBounds2D();
            int j = 1;
            while (j < i) 
            {
                rectangle2d = rectangle2d.createUnion(paintable[j].getBounds2D());
                j++;
            }
        }
        return rectangle2d;
    }

    public void paint(Graphics2D graphics2d)
    {
        Composite composite1;
        Composite composite2;
        composite1 = graphics2d.getComposite();
        composite2 = composite1;
        int i = paintable.length;
        int j = 0;
_L2:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        Composite composite3 = composite[j];
        if (composite3 == null || composite3 == composite2)
        {
            break MISSING_BLOCK_LABEL_53;
        }
        graphics2d.setComposite(composite3);
        composite2 = composite3;
        paintable[j].paint(graphics2d);
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        if (composite2 != composite1)
        {
            graphics2d.setComposite(composite1);
        }
        return;
        Exception exception;
        exception;
        if (composite2 != composite1)
        {
            graphics2d.setComposite(composite1);
        }
        throw exception;
    }

    public Paintable transform(AffineTransform affinetransform)
    {
        int i = paintable.length;
        Paintable apaintable[] = new Paintable[i];
        for (int j = 0; j < i; j++)
        {
            apaintable[j] = paintable[j].transform(affinetransform);
        }

        return make(apaintable, composite);
    }
}
