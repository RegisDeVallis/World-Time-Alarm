// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.WrongType;
import kawa.lib.numbers;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            srfi13

public class lambda.Fn199 extends ModuleBody
{

    final ModuleMethod lambda$Fn198;
    final ModuleMethod lambda$Fn199;
    int patlen;
    Object s;

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply1(modulemethod, obj);

        case 173: 
            return lambda198(obj);

        case 174: 
            break;
        }
        if (lambda199(obj))
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    Object lambda198(Object obj)
    {
        return srfi13.stringParseStart$PlEnd(srfi13.string$Mnkmp$Mnpartial$Mnsearch, s, obj);
    }

    boolean lambda199(Object obj)
    {
        boolean flag = numbers.isInteger(obj);
        if (flag)
        {
            flag = numbers.isExact(obj);
            if (flag)
            {
                Object obj1 = Scheme.numLEq.apply2(srfi13.Lit0, obj);
                boolean flag1;
                try
                {
                    flag1 = ((Boolean)obj1).booleanValue();
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "x", -2, obj1);
                }
                flag = flag1;
                if (flag)
                {
                    flag = ((Boolean)Scheme.numLss.apply2(obj, Integer.valueOf(patlen))).booleanValue();
                }
            }
        }
        return flag;
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 174: 
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 173: 
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        }
    }

    public ()
    {
        ModuleMethod modulemethod = new ModuleMethod(this, 173, null, 4097);
        modulemethod.setProperty("source-location", "srfi13.scm:1468");
        lambda$Fn198 = modulemethod;
        ModuleMethod modulemethod1 = new ModuleMethod(this, 174, null, 4097);
        modulemethod1.setProperty("source-location", "srfi13.scm:1472");
        lambda$Fn199 = modulemethod1;
    }
}
