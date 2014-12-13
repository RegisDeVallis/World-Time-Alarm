// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import kawa.lib.misc;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            srfi13

public class  extends ModuleBody
{

    Object end1;
    final ModuleMethod lambda$Fn109 = new ModuleMethod(this, 92, null, 0);
    final ModuleMethod lambda$Fn110 = new ModuleMethod(this, 93, null, 8194);
    Object rest;
    Object start1;
    _cls2 staticLink;

    static Boolean lambda111(Object obj)
    {
        return Boolean.FALSE;
    }

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 92)
        {
            return lambda109();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 93)
        {
            return lambda110(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda109()
    {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls$Gr, staticLink.s2, rest);
    }

    Object lambda110(Object obj, Object obj1)
    {
        int i = 1;
        Object obj2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(end1, start1), AddOp.$Mn.apply2(obj1, obj));
        Boolean boolean1;
        int j;
        int k;
        int l;
        try
        {
            boolean1 = Boolean.FALSE;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "x", -2, obj2);
        }
        if (obj2 != boolean1)
        {
            j = i;
        } else
        {
            j = 0;
        }
        k = 1 & j + 1;
        if (k != 0)
        {
            if (k != 0)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (staticLink.s1 == staticLink.s2)
        {
            l = i;
        } else
        {
            l = 0;
        }
        if (l != 0)
        {
            Object obj3 = Scheme.numEqu.apply2(start1, obj);
            int i1;
            Boolean boolean2;
            try
            {
                boolean2 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "x", -2, obj3);
            }
            if (obj3 == boolean2)
            {
                i = 0;
            }
        } else
        {
            i = l;
        }
        i1 = 1 & i + 1;
        if (i1 != 0)
        {
            return srfi13.$PcStringCompareCi(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, misc.values, srfi13.lambda$Fn111, misc.values);
        }
        if (i1 != 0)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 92)
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
        if (modulemethod.selector == 93)
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

    public ()
    {
    }
}
