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
    final ModuleMethod lambda$Fn103 = new ModuleMethod(this, 88, null, 0);
    final ModuleMethod lambda$Fn104 = new ModuleMethod(this, 89, null, 8194);
    Object rest;
    Object start1;
    _cls2 staticLink;

    static Boolean lambda105(Object obj)
    {
        return Boolean.FALSE;
    }

    static Boolean lambda106(Object obj)
    {
        return Boolean.FALSE;
    }

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 88)
        {
            return lambda103();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 89)
        {
            return lambda104(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda103()
    {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Eq, staticLink.s2, rest);
    }

    Object lambda104(Object obj, Object obj1)
    {
        Object obj2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(end1, start1), AddOp.$Mn.apply2(obj1, obj));
        boolean flag;
        try
        {
            flag = ((Boolean)obj2).booleanValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "x", -2, obj2);
        }
        if (flag)
        {
            boolean flag1;
            if (staticLink.s1 == staticLink.s2)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                Object obj3 = Scheme.numEqu.apply2(start1, obj);
                boolean flag2;
                try
                {
                    flag2 = ((Boolean)obj3).booleanValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "x", -2, obj3);
                }
                flag1 = flag2;
            }
            if (flag1)
            {
                if (flag1)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            } else
            {
                return srfi13.$PcStringCompareCi(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, srfi13.lambda$Fn105, misc.values, srfi13.lambda$Fn106);
            }
        }
        if (flag)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 88)
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
        if (modulemethod.selector == 89)
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
