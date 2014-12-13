// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;


// Referenced classes of package gnu.mapping:
//            Location, Procedure, WrappedException

public class ProcLocation extends Location
{

    Object args[];
    Procedure proc;

    public ProcLocation(Procedure procedure, Object aobj[])
    {
        proc = procedure;
        args = aobj;
    }

    public Object get(Object obj)
    {
        Object obj1;
        try
        {
            obj1 = proc.applyN(args);
        }
        catch (RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch (Error error)
        {
            throw error;
        }
        catch (Throwable throwable)
        {
            throw new WrappedException(throwable);
        }
        return obj1;
    }

    public boolean isBound()
    {
        return true;
    }

    public void set(Object obj)
    {
        int i = args.length;
        Object aobj[] = new Object[i + 1];
        aobj[i] = obj;
        System.arraycopy(((Object) (args)), 0, ((Object) (aobj)), 0, i);
        try
        {
            proc.setN(aobj);
            return;
        }
        catch (RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch (Error error)
        {
            throw error;
        }
        catch (Throwable throwable)
        {
            throw new WrappedException(throwable);
        }
    }
}
