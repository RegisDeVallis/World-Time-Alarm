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
import gnu.math.Complex;
import gnu.text.Char;
import kawa.lib.numbers;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            printf

public class lambda.Fn3 extends ModuleBody
{
    public class printf.frame1 extends ModuleBody
    {

        Object digs;
        Object ex;
        final ModuleMethod lambda$Fn4;
        Object sgn;
        printf.frame0 staticLink;

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 1)
            {
                return lambda8(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda8(Object obj, Object obj1, Object obj2)
        {
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Object aobj[] = new Object[7];
            aobj[0] = staticLink.staticLink.proc;
            aobj[1] = sgn;
            aobj[2] = digs;
            aobj[3] = ex;
            aobj[4] = obj;
            aobj[5] = obj1;
            aobj[6] = obj2;
            return applytoargs.applyN(aobj);
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 1)
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            } else
            {
                return super.match3(modulemethod, obj, obj1, obj2, callcontext);
            }
        }

        public printf.frame1()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 1, null, 12291);
            modulemethod.setProperty("source-location", "printf.scm:126");
            lambda$Fn4 = modulemethod;
        }
    }


    Object digs;
    Object ex;
    final ModuleMethod lambda$Fn2;
    final ModuleMethod lambda$Fn3;
    Object num;
    Object sgn;
     staticLink;

    public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
    {
        if (modulemethod.selector == 3)
        {
            return lambda7(obj, obj1, obj2);
        } else
        {
            return super.apply3(modulemethod, obj, obj1, obj2);
        }
    }

    public Object apply4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3)
    {
        if (modulemethod.selector == 2)
        {
            return lambda6(obj, obj1, obj2, obj3);
        } else
        {
            return super.apply4(modulemethod, obj, obj1, obj2, obj3);
        }
    }

    Object lambda6(Object obj, Object obj1, Object obj2, Object obj3)
    {
        Object obj4 = Scheme.numEqu.apply2(obj, Integer.valueOf(-1 + staticLink.));
        boolean flag;
        gnu.kawa.functions.ApplyToArgs applytoargs;
        Object aobj[];
        Char char1;
        CharSequence charsequence;
        int i;
        try
        {
            flag = ((Boolean)obj4).booleanValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "x", -2, obj4);
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        char1 = printf.Lit3;
        Object obj5 = staticLink.tr;
        try
        {
            charsequence = (CharSequence)obj5;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 1, obj5);
        }
        try
        {
            i = ((Number)obj).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "string-ref", 2, obj);
        }
        if (!unicode.isCharCi$Eq(char1, Char.make(strings.stringRef(charsequence, i)))) goto _L4; else goto _L3
_L3:
        applytoargs = Scheme.applyToArgs;
        aobj = new Object[7];
        aobj[0] = staticLink.roc;
        aobj[1] = sgn;
        aobj[2] = digs;
        aobj[3] = ex;
        aobj[4] = obj1;
        aobj[5] = obj2;
        aobj[6] = obj3;
        return applytoargs.applyN(aobj);
_L2:
        if (flag) goto _L3; else goto _L4
_L4:
        return ambda1parseError();
    }

    Object lambda7(Object obj, Object obj1, Object obj2)
    {
          = new <init>();
        .staticLink = this;
        .sgn = obj;
        .digs = obj1;
        .ex = obj2;
        Object obj3 = num;
        Complex complex;
        try
        {
            complex = (Complex)obj3;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "imag-part", 1, obj3);
        }
        return printf.stdio$ClParseFloat(numbers.number$To$String(numbers.imagPart(complex)), .Fn4);
    }

    public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        if (modulemethod.selector == 3)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;
        } else
        {
            return super.match3(modulemethod, obj, obj1, obj2, callcontext);
        }
    }

    public int match4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3, CallContext callcontext)
    {
        if (modulemethod.selector == 2)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.value4 = obj3;
            callcontext.proc = modulemethod;
            callcontext.pc = 4;
            return 0;
        } else
        {
            return super.match4(modulemethod, obj, obj1, obj2, obj3, callcontext);
        }
    }

    public lambda.Fn4()
    {
        ModuleMethod modulemethod = new ModuleMethod(this, 2, null, 16388);
        modulemethod.setProperty("source-location", "printf.scm:111");
        lambda$Fn2 = modulemethod;
        ModuleMethod modulemethod1 = new ModuleMethod(this, 3, null, 12291);
        modulemethod1.setProperty("source-location", "printf.scm:123");
        lambda$Fn3 = modulemethod1;
    }
}
