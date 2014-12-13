// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.models;


// Referenced classes of package gnu.kawa.models:
//            Viewable, WeakListener, ModelListener

public abstract class Model
    implements Viewable
{

    transient WeakListener listeners;

    public Model()
    {
    }

    public void addListener(ModelListener modellistener)
    {
        listeners = new WeakListener(modellistener, listeners);
    }

    public void addListener(WeakListener weaklistener)
    {
        weaklistener.next = listeners;
        listeners = weaklistener;
    }

    public void notifyListeners(String s)
    {
        WeakListener weaklistener = null;
        WeakListener weaklistener1 = listeners;
        while (weaklistener1 != null) 
        {
            Object obj = weaklistener1.get();
            WeakListener weaklistener2 = weaklistener1.next;
            if (obj == null)
            {
                if (weaklistener != null)
                {
                    weaklistener.next = weaklistener2;
                }
            } else
            {
                weaklistener = weaklistener1;
                weaklistener1.update(obj, this, s);
            }
            weaklistener1 = weaklistener2;
        }
    }
}
