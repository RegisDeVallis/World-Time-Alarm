// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrongType;

// Referenced classes of package gnu.kawa.slib:
//            srfi13

public class ception extends ModuleBody
{

    final ModuleMethod lambda$Fn155 = new ModuleMethod(this, 132, null, 0);
    final ModuleMethod lambda$Fn156 = new ModuleMethod(this, 133, null, 8194);
    Object s;

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 132)
        {
            return lambda155();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 133)
        {
            return lambda156(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda155()
    {
        ModuleMethod modulemethod = srfi13.string$Mntrim;
        Object obj = s;
        Location location = srfi13.loc$rest;
        Object obj1;
        try
        {
            obj1 = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("srfi13.scm", 1023, 53);
            throw unboundlocationexception;
        }
        return srfi13.stringParseFinalStart$PlEnd(modulemethod, obj, obj1);
    }

    Object lambda156(Object obj, Object obj1)
    {
        Object obj2 = s;
        Location location = srfi13.loc$criterion;
        Object obj3;
        Object obj4;
        try
        {
            obj3 = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("srfi13.scm", 1024, 29);
            throw unboundlocationexception;
        }
        obj4 = srfi13.stringSkip$V(obj2, obj3, new Object[] {
            obj, obj1
        });
        if (obj4 != Boolean.FALSE)
        {
            Object obj5 = s;
            CharSequence charsequence;
            int i;
            int j;
            try
            {
                charsequence = (CharSequence)obj5;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "%substring/shared", 0, obj5);
            }
            try
            {
                i = ((Number)obj4).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "%substring/shared", 1, obj4);
            }
            try
            {
                j = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "%substring/shared", 2, obj1);
            }
            return srfi13.$PcSubstring$SlShared(charsequence, i, j);
        } else
        {
            return "";
        }
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 132)
        {
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;
        } else
        {
            return super.match0(modulemethod, callcontext);
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        if (modulemethod.selector == 133)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        } else
        {
            return super.match2(modulemethod, obj, obj1, callcontext);
        }
    }

    public ception()
    {
    }
}
