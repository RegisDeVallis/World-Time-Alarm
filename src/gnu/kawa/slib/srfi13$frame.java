// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            srfi13

public class lambda.Fn2 extends ModuleBody
{

    Object args;
    final ModuleMethod lambda$Fn1 = new ModuleMethod(this, 1, null, 0);
    final ModuleMethod lambda$Fn2;
    Object proc;
    Object s;
    int slen;
    Object start;

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 1)
        {
            return lambda1();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 2)
        {
            return lambda2(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda1()
    {
        if (lists.isPair(args))
        {
            Object obj = lists.car.apply1(args);
            Object obj1 = lists.cdr.apply1(args);
            boolean flag = numbers.isInteger(obj);
            boolean flag1;
            if (flag ? (flag1 = numbers.isExact(obj)) ? Scheme.numLEq.apply2(obj, Integer.valueOf(slen)) != Boolean.FALSE : flag1 : flag)
            {
                return misc.values(new Object[] {
                    obj, obj1
                });
            } else
            {
                Object aobj1[] = new Object[3];
                aobj1[0] = proc;
                aobj1[1] = obj;
                aobj1[2] = s;
                return misc.error$V("Illegal substring END spec", aobj1);
            }
        } else
        {
            Object aobj[] = new Object[2];
            aobj[0] = Integer.valueOf(slen);
            aobj[1] = args;
            return misc.values(aobj);
        }
    }

    Object lambda2(Object obj, Object obj1)
    {
        if (Scheme.numLEq.apply2(start, obj) != Boolean.FALSE)
        {
            Object aobj1[] = new Object[3];
            aobj1[0] = obj1;
            aobj1[1] = start;
            aobj1[2] = obj;
            return misc.values(aobj1);
        } else
        {
            Object aobj[] = new Object[4];
            aobj[0] = proc;
            aobj[1] = start;
            aobj[2] = obj;
            aobj[3] = s;
            return misc.error$V("Illegal substring START/END spec", aobj);
        }
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 1)
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
        if (modulemethod.selector == 2)
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
        ModuleMethod modulemethod = new ModuleMethod(this, 2, null, 8194);
        modulemethod.setProperty("source-location", "srfi13.scm:150");
        lambda$Fn2 = modulemethod;
    }
}
