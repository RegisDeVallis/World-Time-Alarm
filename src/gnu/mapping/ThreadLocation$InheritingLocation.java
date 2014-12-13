// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;


// Referenced classes of package gnu.mapping:
//            ThreadLocation, SharedLocation, NamedLocation

public class this._cls0 extends InheritableThreadLocal
{

    final ThreadLocation this$0;

    protected SharedLocation childValue(NamedLocation namedlocation)
    {
        if (property != ThreadLocation.ANONYMOUS)
        {
            throw new Error();
        }
        if (namedlocation == null)
        {
            namedlocation = (SharedLocation)getLocation();
        }
        Object obj = namedlocation;
        if (((NamedLocation) (obj)).base == null)
        {
            SharedLocation sharedlocation = new SharedLocation(name, property, 0);
            sharedlocation.value = ((NamedLocation) (obj)).value;
            obj.base = sharedlocation;
            obj.value = null;
            obj = sharedlocation;
        }
        SharedLocation sharedlocation1 = new SharedLocation(name, property, 0);
        sharedlocation1.value = null;
        sharedlocation1.base = ((Location) (obj));
        return sharedlocation1;
    }

    protected volatile Object childValue(Object obj)
    {
        return childValue((NamedLocation)obj);
    }

    public ()
    {
        this$0 = ThreadLocation.this;
        super();
    }
}
