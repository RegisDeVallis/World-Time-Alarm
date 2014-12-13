// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import kawa.lib.lists;
import kawa.standard.Scheme;
import kawa.standard.call_with_values;

// Referenced classes of package gnu.kawa.slib:
//            srfi1

public class lambda.Fn25 extends ModuleBody
{
    public class srfi1.frame28 extends ModuleBody
    {

        srfi1.frame27 staticLink;

        public Object lambda41lp(Object obj, Object obj1)
        {
            srfi1.frame29 frame29_1 = new srfi1.frame29();
            frame29_1.staticLink = this;
            frame29_1.heads = obj;
            frame29_1.tails = obj1;
            return call_with_values.callWithValues(frame29_1.lambda$Fn26, frame29_1.lambda$Fn27);
        }

        public srfi1.frame28()
        {
        }
    }

    public class srfi1.frame29 extends ModuleBody
    {

        Object heads;
        final ModuleMethod lambda$Fn26 = new ModuleMethod(this, 24, null, 0);
        final ModuleMethod lambda$Fn27;
        srfi1.frame28 staticLink;
        Object tails;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 24)
            {
                return lambda42();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 25)
            {
                return lambda43(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda42()
        {
            return srfi1.$PcCars$PlCdrs(tails);
        }

        Object lambda43(Object obj, Object obj1)
        {
            if (kawa.lib.lists.isPair(obj))
            {
                Object obj2 = Scheme.apply.apply2(staticLink.staticLink.pred, heads);
                if (obj2 != Boolean.FALSE)
                {
                    obj2 = staticLink.lambda41lp(obj, obj1);
                }
                return obj2;
            } else
            {
                return Scheme.apply.apply2(staticLink.staticLink.pred, heads);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 24)
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
            if (modulemethod.selector == 25)
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

        public srfi1.frame29()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 25, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:1381");
            lambda$Fn27 = modulemethod;
        }
    }


    final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 26, null, 0);
    final ModuleMethod lambda$Fn25;
    Object lis1;
    LList lists;
    Procedure pred;

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 26)
        {
            return lambda39();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 27)
        {
            return lambda40(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda39()
    {
        return srfi1.$PcCars$PlCdrs(kawa.lib.lists.cons(lis1, lists));
    }

    Object lambda40(Object obj, Object obj1)
    {
        lists lists1 = new <init>();
        lists1.staticLink = this;
        int i = 1 & 1 + kawa.lib.lists.isPair(obj);
        if (i != 0)
        {
            if (i != 0)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        } else
        {
            return lists1.lambda41lp(obj, obj1);
        }
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 26)
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
        if (modulemethod.selector == 27)
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

    public lambda.Fn27()
    {
        ModuleMethod modulemethod = new ModuleMethod(this, 27, null, 8194);
        modulemethod.setProperty("source-location", "srfi1.scm:1378");
        lambda$Fn25 = modulemethod;
    }
}
