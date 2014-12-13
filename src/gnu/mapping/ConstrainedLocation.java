// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;


// Referenced classes of package gnu.mapping:
//            Location, Procedure, WrappedException, Symbol

public class ConstrainedLocation extends Location
{

    protected Location base;
    protected Procedure converter;

    public ConstrainedLocation()
    {
    }

    public static ConstrainedLocation make(Location location, Procedure procedure)
    {
        ConstrainedLocation constrainedlocation = new ConstrainedLocation();
        constrainedlocation.base = location;
        constrainedlocation.converter = procedure;
        return constrainedlocation;
    }

    protected Object coerce(Object obj)
    {
        Object obj1;
        try
        {
            obj1 = converter.apply1(obj);
        }
        catch (Throwable throwable)
        {
            throw WrappedException.wrapIfNeeded(throwable);
        }
        return obj1;
    }

    public final Object get(Object obj)
    {
        return base.get(obj);
    }

    public Object getKeyProperty()
    {
        return base.getKeyProperty();
    }

    public Symbol getKeySymbol()
    {
        return base.getKeySymbol();
    }

    public boolean isBound()
    {
        return base.isBound();
    }

    public boolean isConstant()
    {
        return base.isConstant();
    }

    public final void set(Object obj)
    {
        base.set(coerce(obj));
    }

    public void setRestore(Object obj)
    {
        base.setRestore(obj);
    }

    public Object setWithSave(Object obj)
    {
        return base.setWithSave(coerce(obj));
    }
}
