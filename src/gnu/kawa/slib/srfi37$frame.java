// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.IsEqual;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.WrongType;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.call_with_values;

// Referenced classes of package gnu.kawa.slib:
//            srfi37

public class  extends ModuleBody
{
    public class srfi37.frame0 extends ModuleBody
    {

        final ModuleMethod lambda$Fn1;
        final ModuleMethod lambda$Fn2;
        Object name;
        srfi37.frame staticLink;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.apply1(modulemethod, obj);

            case 1: // '\001'
                if (lambda7(obj))
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }

            case 2: // '\002'
                return lambda6(obj);
            }
        }

        Object lambda6(Object obj)
        {
            option.Mntype mntype;
            try
            {
                mntype = (option.Mntype)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "option-names", 0, obj);
            }
            return srfi37.frame.lambda1find(srfi37.optionNames(mntype), lambda$Fn2);
        }

        boolean lambda7(Object obj)
        {
            return IsEqual.apply(name, obj);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.match1(modulemethod, obj, callcontext);

            case 2: // '\002'
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;

            case 1: // '\001'
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }
        }

        public srfi37.frame0()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 1, null, 4097);
            modulemethod.setProperty("source-location", "srfi37.scm:75");
            lambda$Fn2 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 2, null, 4097);
            modulemethod1.setProperty("source-location", "srfi37.scm:72");
            lambda$Fn1 = modulemethod1;
        }
    }

    public class srfi37.frame1 extends ModuleBody
    {

        Object args;
        Object index;
        final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, null, 0);
        final ModuleMethod lambda$Fn4 = new ModuleMethod(this, 4, null, -4096);
        final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 5, null, 0);
        final ModuleMethod lambda$Fn6 = new ModuleMethod(this, 6, null, -4096);
        final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 7, null, 0);
        final ModuleMethod lambda$Fn8 = new ModuleMethod(this, 8, null, -4096);
        char name;
        Object option;
        Object seeds;
        Object shorts;
        srfi37.frame staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            switch (modulemethod.selector)
            {
            case 4: // '\004'
            case 6: // '\006'
            default:
                return super.apply0(modulemethod);

            case 3: // '\003'
                return lambda8();

            case 5: // '\005'
                return lambda10();

            case 7: // '\007'
                return lambda12();
            }
        }

        public Object applyN(ModuleMethod modulemethod, Object aobj[])
        {
            switch (modulemethod.selector)
            {
            case 5: // '\005'
            case 7: // '\007'
            default:
                return super.applyN(modulemethod, aobj);

            case 4: // '\004'
                return lambda9$V(aobj);

            case 6: // '\006'
                return lambda11$V(aobj);

            case 8: // '\b'
                return lambda13$V(aobj);
            }
        }

        Object lambda10()
        {
            gnu.kawa.functions.Apply apply = Scheme.apply;
            Object aobj[] = new Object[5];
            Object obj = option;
            option.Mntype mntype;
            try
            {
                mntype = (option.Mntype)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "option-processor", 0, obj);
            }
            aobj[0] = srfi37.optionProcessor(mntype);
            aobj[1] = option;
            aobj[2] = Char.make(name);
            aobj[3] = lists.car.apply1(args);
            aobj[4] = seeds;
            return apply.applyN(aobj);
        }

        Object lambda11$V(Object aobj[])
        {
            LList llist = LList.makeList(aobj, 0);
            return staticLink.lambda5scanArgs(lists.cdr.apply1(args), llist);
        }

        Object lambda12()
        {
            gnu.kawa.functions.Apply apply = Scheme.apply;
            Object aobj[] = new Object[5];
            Object obj = option;
            option.Mntype mntype;
            try
            {
                mntype = (option.Mntype)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "option-processor", 0, obj);
            }
            aobj[0] = srfi37.optionProcessor(mntype);
            aobj[1] = option;
            aobj[2] = Char.make(name);
            aobj[3] = Boolean.FALSE;
            aobj[4] = seeds;
            return apply.applyN(aobj);
        }

        Object lambda13$V(Object aobj[])
        {
            LList llist = LList.makeList(aobj, 0);
            return staticLink.lambda3scanShortOptions(AddOp.$Pl.apply2(index, srfi37.Lit0), shorts, args, llist);
        }

        Object lambda8()
        {
            gnu.kawa.functions.Apply apply = Scheme.apply;
            Object aobj[] = new Object[5];
            Object obj = option;
            option.Mntype mntype;
            Object obj1;
            CharSequence charsequence;
            Object obj2;
            int i;
            Object obj3;
            CharSequence charsequence1;
            try
            {
                mntype = (option.Mntype)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "option-processor", 0, obj);
            }
            aobj[0] = srfi37.optionProcessor(mntype);
            aobj[1] = option;
            aobj[2] = Char.make(name);
            obj1 = shorts;
            try
            {
                charsequence = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "substring", 1, obj1);
            }
            obj2 = AddOp.$Pl.apply2(index, srfi37.Lit0);
            try
            {
                i = ((Number)obj2).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "substring", 2, obj2);
            }
            obj3 = shorts;
            try
            {
                charsequence1 = (CharSequence)obj3;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "string-length", 1, obj3);
            }
            aobj[3] = strings.substring(charsequence, i, strings.stringLength(charsequence1));
            aobj[4] = seeds;
            return apply.applyN(aobj);
        }

        Object lambda9$V(Object aobj[])
        {
            LList llist = LList.makeList(aobj, 0);
            return staticLink.lambda5scanArgs(args, llist);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            case 4: // '\004'
            case 6: // '\006'
            default:
                return super.match0(modulemethod, callcontext);

            case 7: // '\007'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;

            case 5: // '\005'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;

            case 3: // '\003'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            }
        }

        public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            case 5: // '\005'
            case 7: // '\007'
            default:
                return super.matchN(modulemethod, aobj, callcontext);

            case 8: // '\b'
                callcontext.values = aobj;
                callcontext.proc = modulemethod;
                callcontext.pc = 5;
                return 0;

            case 6: // '\006'
                callcontext.values = aobj;
                callcontext.proc = modulemethod;
                callcontext.pc = 5;
                return 0;

            case 4: // '\004'
                callcontext.values = aobj;
                callcontext.proc = modulemethod;
                callcontext.pc = 5;
                return 0;
            }
        }

        public srfi37.frame1()
        {
        }
    }

    public class srfi37.frame2 extends ModuleBody
    {

        final ModuleMethod lambda$Fn10 = new ModuleMethod(this, 10, null, -4096);
        final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 9, null, 0);
        Object operands;
        Object seeds;
        srfi37.frame staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 9)
            {
                return lambda14();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object applyN(ModuleMethod modulemethod, Object aobj[])
        {
            if (modulemethod.selector == 10)
            {
                return lambda15$V(aobj);
            } else
            {
                return super.applyN(modulemethod, aobj);
            }
        }

        Object lambda14()
        {
            return Scheme.apply.apply3(staticLink.operand$Mnproc, lists.car.apply1(operands), seeds);
        }

        Object lambda15$V(Object aobj[])
        {
            LList llist = LList.makeList(aobj, 0);
            return staticLink.lambda4scanOperands(lists.cdr.apply1(operands), llist);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 9)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
        {
            if (modulemethod.selector == 10)
            {
                callcontext.values = aobj;
                callcontext.proc = modulemethod;
                callcontext.pc = 5;
                return 0;
            } else
            {
                return super.matchN(modulemethod, aobj, callcontext);
            }
        }

        public srfi37.frame2()
        {
        }
    }

    public class srfi37.frame3 extends ModuleBody
    {

        Object arg;
        Object args;
        final ModuleMethod lambda$Fn11 = new ModuleMethod(this, 17, null, 0);
        final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 18, null, 4097);
        final ModuleMethod lambda$Fn19 = new ModuleMethod(this, 19, null, 0);
        final ModuleMethod lambda$Fn20 = new ModuleMethod(this, 20, null, -4096);
        final ModuleMethod lambda$Fn21 = new ModuleMethod(this, 21, null, 0);
        final ModuleMethod lambda$Fn22 = new ModuleMethod(this, 22, null, -4096);
        final ModuleMethod lambda$Fn23 = new ModuleMethod(this, 23, null, 0);
        final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 24, null, -4096);
        CharSequence name;
        Object option;
        Object seeds;
        srfi37.frame staticLink;
        Object temp;

        public Object apply0(ModuleMethod modulemethod)
        {
            switch (modulemethod.selector)
            {
            case 18: // '\022'
            case 20: // '\024'
            case 22: // '\026'
            default:
                return super.apply0(modulemethod);

            case 17: // '\021'
                return lambda16();

            case 19: // '\023'
                return lambda24();

            case 21: // '\025'
                return lambda26();

            case 23: // '\027'
                return lambda28();
            }
        }

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 18)
            {
                return lambda17(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        public Object applyN(ModuleMethod modulemethod, Object aobj[])
        {
            switch (modulemethod.selector)
            {
            case 21: // '\025'
            case 23: // '\027'
            default:
                return super.applyN(modulemethod, aobj);

            case 20: // '\024'
                return lambda25$V(aobj);

            case 22: // '\026'
                return lambda27$V(aobj);

            case 24: // '\030'
                return lambda29$V(aobj);
            }
        }

        CharSequence lambda16()
        {
            Object obj = arg;
            CharSequence charsequence;
            Object obj1;
            int i;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "substring", 1, obj);
            }
            obj1 = temp;
            try
            {
                i = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "substring", 3, obj1);
            }
            return strings.substring(charsequence, 2, i);
        }

        Object lambda17(Object obj)
        {
            srfi37.frame4 frame4_1 = new srfi37.frame4();
            frame4_1.staticLink = this;
            frame4_1.x = obj;
            return call_with_values.callWithValues(frame4_1.lambda$Fn13, frame4_1.lambda$Fn14);
        }

        Object lambda24()
        {
            gnu.kawa.functions.Apply apply = Scheme.apply;
            Object aobj[] = new Object[5];
            Object obj = option;
            option.Mntype mntype;
            try
            {
                mntype = (option.Mntype)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "option-processor", 0, obj);
            }
            aobj[0] = srfi37.optionProcessor(mntype);
            aobj[1] = option;
            aobj[2] = name;
            aobj[3] = lists.car.apply1(args);
            aobj[4] = seeds;
            return apply.applyN(aobj);
        }

        Object lambda25$V(Object aobj[])
        {
            LList llist = LList.makeList(aobj, 0);
            return staticLink.lambda5scanArgs(lists.cdr.apply1(args), llist);
        }

        Object lambda26()
        {
            gnu.kawa.functions.Apply apply = Scheme.apply;
            Object aobj[] = new Object[5];
            Object obj = option;
            option.Mntype mntype;
            try
            {
                mntype = (option.Mntype)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "option-processor", 0, obj);
            }
            aobj[0] = srfi37.optionProcessor(mntype);
            aobj[1] = option;
            aobj[2] = name;
            aobj[3] = Boolean.FALSE;
            aobj[4] = seeds;
            return apply.applyN(aobj);
        }

        Object lambda27$V(Object aobj[])
        {
            LList llist = LList.makeList(aobj, 0);
            return staticLink.lambda5scanArgs(args, llist);
        }

        Object lambda28()
        {
            return Scheme.apply.apply3(staticLink.operand$Mnproc, arg, seeds);
        }

        Object lambda29$V(Object aobj[])
        {
            LList llist = LList.makeList(aobj, 0);
            return staticLink.lambda5scanArgs(args, llist);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            case 18: // '\022'
            case 20: // '\024'
            case 22: // '\026'
            default:
                return super.match0(modulemethod, callcontext);

            case 23: // '\027'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;

            case 21: // '\025'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;

            case 19: // '\023'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;

            case 17: // '\021'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            }
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 18)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return super.match1(modulemethod, obj, callcontext);
            }
        }

        public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            case 21: // '\025'
            case 23: // '\027'
            default:
                return super.matchN(modulemethod, aobj, callcontext);

            case 24: // '\030'
                callcontext.values = aobj;
                callcontext.proc = modulemethod;
                callcontext.pc = 5;
                return 0;

            case 22: // '\026'
                callcontext.values = aobj;
                callcontext.proc = modulemethod;
                callcontext.pc = 5;
                return 0;

            case 20: // '\024'
                callcontext.values = aobj;
                callcontext.proc = modulemethod;
                callcontext.pc = 5;
                return 0;
            }
        }

        public srfi37.frame3()
        {
        }
    }

    public class srfi37.frame4 extends ModuleBody
    {

        final ModuleMethod lambda$Fn13 = new ModuleMethod(this, 15, null, 0);
        final ModuleMethod lambda$Fn14 = new ModuleMethod(this, 16, null, 4097);
        srfi37.frame3 staticLink;
        Object x;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 15)
            {
                return lambda18();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 16)
            {
                return lambda19(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        CharSequence lambda18()
        {
            Object obj = staticLink.arg;
            CharSequence charsequence;
            Object obj1;
            int i;
            Object obj2;
            CharSequence charsequence1;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "substring", 1, obj);
            }
            obj1 = AddOp.$Pl.apply2(staticLink.temp, srfi37.Lit0);
            try
            {
                i = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "substring", 2, obj1);
            }
            obj2 = staticLink.arg;
            try
            {
                charsequence1 = (CharSequence)obj2;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-length", 1, obj2);
            }
            return strings.substring(charsequence, i, strings.stringLength(charsequence1));
        }

        Object lambda19(Object obj)
        {
            srfi37.frame5 frame5_1 = new srfi37.frame5();
            frame5_1.staticLink = this;
            frame5_1.x = obj;
            return call_with_values.callWithValues(frame5_1.lambda$Fn15, frame5_1.lambda$Fn16);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 15)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 16)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return super.match1(modulemethod, obj, callcontext);
            }
        }

        public srfi37.frame4()
        {
        }
    }

    public class srfi37.frame5 extends ModuleBody
    {

        final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 13, null, 0);
        final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 14, null, 4097);
        srfi37.frame4 staticLink;
        Object x;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 13)
            {
                return lambda20();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 14)
            {
                return lambda21(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda20()
        {
            Object obj = staticLink.staticLink.staticLink.lambda2findOption(staticLink.x);
            if (obj != Boolean.FALSE)
            {
                return obj;
            } else
            {
                return srfi37.option(LList.list1(staticLink.x), Boolean.TRUE, Boolean.FALSE, staticLink.staticLink.staticLink.unrecognized$Mnoption$Mnproc);
            }
        }

        Object lambda21(Object obj)
        {
            srfi37.frame6 frame6_1 = new srfi37.frame6();
            frame6_1.staticLink = this;
            frame6_1.x = obj;
            return call_with_values.callWithValues(frame6_1.lambda$Fn17, frame6_1.lambda$Fn18);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 13)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 14)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return super.match1(modulemethod, obj, callcontext);
            }
        }

        public srfi37.frame5()
        {
        }
    }

    public class srfi37.frame6 extends ModuleBody
    {

        final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
        final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
        srfi37.frame5 staticLink;
        Object x;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 11)
            {
                return lambda22();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object applyN(ModuleMethod modulemethod, Object aobj[])
        {
            if (modulemethod.selector == 12)
            {
                return lambda23$V(aobj);
            } else
            {
                return super.applyN(modulemethod, aobj);
            }
        }

        Object lambda22()
        {
            gnu.kawa.functions.Apply apply = Scheme.apply;
            Object aobj[] = new Object[5];
            Object obj = x;
            option.Mntype mntype;
            try
            {
                mntype = (option.Mntype)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "option-processor", 0, obj);
            }
            aobj[0] = srfi37.optionProcessor(mntype);
            aobj[1] = x;
            aobj[2] = staticLink.staticLink.x;
            aobj[3] = staticLink.x;
            aobj[4] = staticLink.staticLink.staticLink.seeds;
            return apply.applyN(aobj);
        }

        Object lambda23$V(Object aobj[])
        {
            LList llist = LList.makeList(aobj, 0);
            return staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(staticLink.staticLink.staticLink.args, llist);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 11)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
        {
            if (modulemethod.selector == 12)
            {
                callcontext.values = aobj;
                callcontext.proc = modulemethod;
                callcontext.pc = 5;
                return 0;
            } else
            {
                return super.matchN(modulemethod, aobj, callcontext);
            }
        }

        public srfi37.frame6()
        {
        }
    }


    Object operand$Mnproc;
    Object options;
    Object unrecognized$Mnoption$Mnproc;

    public static Object lambda1find(Object obj, Object obj1)
    {
        if (lists.isNull(obj))
        {
            return Boolean.FALSE;
        }
        if (Scheme.applyToArgs.apply2(obj1, lists.car.apply1(obj)) != Boolean.FALSE)
        {
            return lists.car.apply1(obj);
        } else
        {
            return lambda1find(lists.cdr.apply1(obj), obj1);
        }
    }

    public Object lambda2findOption(Object obj)
    {
          = new ();
        .staticLink = this;
        .name = obj;
        return lambda1find(options, .Fn1);
    }

    public Object lambda3scanShortOptions(Object obj, Object obj1, Object obj2, Object obj3)
    {
          = new ();
        .staticLink = this;
        .index = obj;
        .shorts = obj1;
        .args = obj2;
        .seeds = obj3;
        gnu.kawa.functions.NumberCompare numbercompare = Scheme.numEqu;
        Object obj4 = .index;
        Object obj5 = .shorts;
        CharSequence charsequence;
        Object obj6;
        CharSequence charsequence1;
        Object obj7;
        int i;
        Object obj8;
        gnu.kawa.functions.NumberCompare numbercompare1;
        Object obj9;
        Object obj10;
        CharSequence charsequence2;
        Object obj11;
        boolean flag;
         1;
        Object obj13;
         2;
        Object obj15;
         3;
        try
        {
            charsequence = (CharSequence)obj5;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj5);
        }
        if (numbercompare.apply2(obj4, Integer.valueOf(strings.stringLength(charsequence))) != Boolean.FALSE)
        {
            return lambda5scanArgs(.args, .seeds);
        }
        obj6 = .shorts;
        try
        {
            charsequence1 = (CharSequence)obj6;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 1, obj6);
        }
        obj7 = .index;
        try
        {
            i = ((Number)obj7).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "string-ref", 2, obj7);
        }
        .name = strings.stringRef(charsequence1, i);
        obj8 = lambda2findOption(Char.make(.name));
        if (obj8 == Boolean.FALSE)
        {
            obj8 = srfi37.option(LList.list1(Char.make(.name)), Boolean.FALSE, Boolean.FALSE, unrecognized$Mnoption$Mnproc);
        }
        .option = obj8;
        numbercompare1 = Scheme.numLss;
        obj9 = AddOp.$Pl.apply2(.index, srfi37.Lit0);
        obj10 = .shorts;
        try
        {
            charsequence2 = (CharSequence)obj10;
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "string-length", 1, obj10);
        }
        obj11 = numbercompare1.apply2(obj9, Integer.valueOf(strings.stringLength(charsequence2)));
        try
        {
            flag = ((Boolean)obj11).booleanValue();
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "x", -2, obj11);
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        Object obj14 = .option;
        try
        {
            2 = ()obj14;
        }
        catch (ClassCastException classcastexception6)
        {
            throw new WrongType(classcastexception6, "option-required-arg?", 0, obj14);
        }
        obj15 = srfi37.isOptionRequiredArg(2);
        if (obj15 == Boolean.FALSE) goto _L4; else goto _L3
_L3:
        if (obj15 == Boolean.FALSE) goto _L6; else goto _L5
_L5:
        return call_with_values.callWithValues(.Fn3, .Fn4);
_L4:
        Object obj16 = .option;
        try
        {
            3 = ()obj16;
        }
        catch (ClassCastException classcastexception7)
        {
            throw new WrongType(classcastexception7, "option-optional-arg?", 0, obj16);
        }
        if (srfi37.isOptionOptionalArg(3) != Boolean.FALSE) goto _L5; else goto _L6
_L6:
        Object obj12 = .option;
        try
        {
            1 = ()obj12;
        }
        catch (ClassCastException classcastexception5)
        {
            throw new WrongType(classcastexception5, "option-required-arg?", 0, obj12);
        }
        obj13 = srfi37.isOptionRequiredArg(1);
        if (obj13 == Boolean.FALSE ? obj13 != Boolean.FALSE : lists.isPair(.args))
        {
            return call_with_values.callWithValues(.Fn5, .Fn6);
        } else
        {
            return call_with_values.callWithValues(.Fn7, .Fn8);
        }
_L2:
        if (!flag) goto _L6; else goto _L5
    }

    public Object lambda4scanOperands(Object obj, Object obj1)
    {
          = new ();
        .staticLink = this;
        .operands = obj;
        .seeds = obj1;
        if (lists.isNull(.operands))
        {
            return Scheme.apply.apply2(misc.values, .seeds);
        } else
        {
            return call_with_values.callWithValues(.Fn9, .Fn10);
        }
    }

    public Object lambda5scanArgs(Object obj, Object obj1)
    {
        Object obj4;
          = new ();
        .staticLink = this;
        .seeds = obj1;
        if (lists.isNull(obj))
        {
            return Scheme.apply.apply2(misc.values, .seeds);
        }
        Object obj2 = lists.car.apply1(obj);
        .args = lists.cdr.apply1(obj);
        .arg = obj2;
        if (strings.isString$Eq("--", .arg))
        {
            return lambda4scanOperands(.args, .seeds);
        }
        Object obj3 = .arg;
        CharSequence charsequence;
        boolean flag;
        CharSequence charsequence1;
        boolean flag1;
        CharSequence charsequence2;
        CharSequence charsequence3;
        Object obj8;
         1;
        Object obj10;
        CharSequence charsequence4;
        boolean flag2;
        CharSequence charsequence5;
        CharSequence charsequence6;
        CharSequence charsequence7;
        Char char1;
        CharSequence charsequence8;
        Char char2;
        CharSequence charsequence9;
        boolean flag3;
        Char char3;
        CharSequence charsequence10;
        Char char4;
        Object obj17;
        CharSequence charsequence11;
        boolean flag4;
        Char char5;
        CharSequence charsequence12;
        boolean flag5;
        Char char6;
        CharSequence charsequence13;
        int i;
        gnu.kawa.functions.NumberCompare numbercompare;
        CharSequence charsequence14;
        Char char7;
        CharSequence charsequence15;
        int j;
        try
        {
            charsequence = (CharSequence)obj3;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj3);
        }
        if (strings.stringLength(charsequence) > 4)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_449;
        }
        char4 = srfi37.Lit1;
        obj17 = .arg;
        try
        {
            charsequence11 = (CharSequence)obj17;
        }
        catch (ClassCastException classcastexception11)
        {
            throw new WrongType(classcastexception11, "string-ref", 1, obj17);
        }
        flag4 = characters.isChar$Eq(char4, Char.make(strings.stringRef(charsequence11, 0)));
        if (!flag4) goto _L2; else goto _L1
_L1:
        char5 = srfi37.Lit1;
        Object obj18 = .arg;
        try
        {
            charsequence12 = (CharSequence)obj18;
        }
        catch (ClassCastException classcastexception12)
        {
            throw new WrongType(classcastexception12, "string-ref", 1, obj18);
        }
        flag5 = characters.isChar$Eq(char5, Char.make(strings.stringRef(charsequence12, 1)));
        if (!flag5) goto _L4; else goto _L3
_L3:
        char6 = srfi37.Lit2;
        Object obj19 = .arg;
        try
        {
            charsequence13 = (CharSequence)obj19;
        }
        catch (ClassCastException classcastexception13)
        {
            throw new WrongType(classcastexception13, "string-ref", 1, obj19);
        }
        i = 1 & 1 + characters.isChar$Eq(char6, Char.make(strings.stringRef(charsequence13, 2)));
        if (i == 0) goto _L6; else goto _L5
_L5:
        obj4 = srfi37.Lit3;
_L11:
        numbercompare = Scheme.numEqu;
        Object obj20 = .arg;
        try
        {
            charsequence14 = (CharSequence)obj20;
        }
        catch (ClassCastException classcastexception14)
        {
            throw new WrongType(classcastexception14, "string-length", 1, obj20);
        }
        if (numbercompare.apply2(obj4, Integer.valueOf(strings.stringLength(charsequence14))) == Boolean.FALSE) goto _L8; else goto _L7
_L7:
        obj4 = Boolean.FALSE;
_L10:
        .temp = obj4;
        if (.temp != Boolean.FALSE)
        {
            return call_with_values.callWithValues(.Fn11, .Fn12);
        }
        break MISSING_BLOCK_LABEL_470;
_L8:
        char7 = srfi37.Lit2;
        Object obj21 = .arg;
        try
        {
            charsequence15 = (CharSequence)obj21;
        }
        catch (ClassCastException classcastexception15)
        {
            throw new WrongType(classcastexception15, "string-ref", 1, obj21);
        }
        try
        {
            j = ((Number)obj4).intValue();
        }
        catch (ClassCastException classcastexception16)
        {
            throw new WrongType(classcastexception16, "string-ref", 2, obj4);
        }
        if (characters.isChar$Eq(char7, Char.make(strings.stringRef(charsequence15, j)))) goto _L10; else goto _L9
_L9:
        obj4 = AddOp.$Pl.apply2(srfi37.Lit0, obj4);
          goto _L11
_L6:
        if (i != 0)
        {
            obj4 = Boolean.TRUE;
        } else
        {
            obj4 = Boolean.FALSE;
        }
          goto _L10
_L4:
        if (flag5)
        {
            obj4 = Boolean.TRUE;
        } else
        {
            obj4 = Boolean.FALSE;
        }
          goto _L10
_L2:
        if (flag4)
        {
            obj4 = Boolean.TRUE;
        } else
        {
            obj4 = Boolean.FALSE;
        }
          goto _L10
        if (flag)
        {
            obj4 = Boolean.TRUE;
        } else
        {
            obj4 = Boolean.FALSE;
        }
          goto _L10
        Object obj5 = .arg;
        try
        {
            charsequence1 = (CharSequence)obj5;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-length", 1, obj5);
        }
        if (strings.stringLength(charsequence1) > 3)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (!flag1) goto _L13; else goto _L12
_L12:
        char2 = srfi37.Lit1;
        Object obj15 = .arg;
        try
        {
            charsequence9 = (CharSequence)obj15;
        }
        catch (ClassCastException classcastexception9)
        {
            throw new WrongType(classcastexception9, "string-ref", 1, obj15);
        }
        flag3 = characters.isChar$Eq(char2, Char.make(strings.stringRef(charsequence9, 0)));
        if (!flag3) goto _L15; else goto _L14
_L14:
        char3 = srfi37.Lit1;
        Object obj16 = .arg;
        try
        {
            charsequence10 = (CharSequence)obj16;
        }
        catch (ClassCastException classcastexception10)
        {
            throw new WrongType(classcastexception10, "string-ref", 1, obj16);
        }
        if (!characters.isChar$Eq(char3, Char.make(strings.stringRef(charsequence10, 1)))) goto _L17; else goto _L16
_L16:
        Object obj6 = .arg;
        Object obj7;
        Object obj9;
        try
        {
            charsequence2 = (CharSequence)obj6;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "substring", 1, obj6);
        }
        obj7 = .arg;
        try
        {
            charsequence3 = (CharSequence)obj7;
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "string-length", 1, obj7);
        }
        .name = strings.substring(charsequence2, 2, strings.stringLength(charsequence3));
        obj8 = lambda2findOption(.name);
        if (obj8 == Boolean.FALSE)
        {
            obj8 = srfi37.option(LList.list1(.name), Boolean.FALSE, Boolean.FALSE, unrecognized$Mnoption$Mnproc);
        }
        .option = obj8;
        obj9 = .option;
        try
        {
            1 = ()obj9;
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "option-required-arg?", 0, obj9);
        }
        obj10 = srfi37.isOptionRequiredArg(1);
        if (obj10 == Boolean.FALSE ? obj10 != Boolean.FALSE : lists.isPair(.args))
        {
            return call_with_values.callWithValues(.Fn19, .Fn20);
        } else
        {
            return call_with_values.callWithValues(.Fn21, .Fn22);
        }
_L15:
        if (flag3) goto _L16; else goto _L17
_L17:
        Object obj11 = .arg;
        try
        {
            charsequence4 = (CharSequence)obj11;
        }
        catch (ClassCastException classcastexception5)
        {
            throw new WrongType(classcastexception5, "string-length", 1, obj11);
        }
        if (strings.stringLength(charsequence4) > 1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!flag2) goto _L19; else goto _L18
_L18:
        char1 = srfi37.Lit1;
        Object obj14 = .arg;
        try
        {
            charsequence8 = (CharSequence)obj14;
        }
        catch (ClassCastException classcastexception8)
        {
            throw new WrongType(classcastexception8, "string-ref", 1, obj14);
        }
        if (!characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence8, 0)))) goto _L21; else goto _L20
_L20:
        Object obj12 = .arg;
        Object obj13;
        try
        {
            charsequence5 = (CharSequence)obj12;
        }
        catch (ClassCastException classcastexception6)
        {
            throw new WrongType(classcastexception6, "substring", 1, obj12);
        }
        obj13 = .arg;
        try
        {
            charsequence6 = (CharSequence)obj13;
        }
        catch (ClassCastException classcastexception7)
        {
            throw new WrongType(classcastexception7, "string-length", 1, obj13);
        }
        charsequence7 = strings.substring(charsequence5, 1, strings.stringLength(charsequence6));
        return lambda3scanShortOptions(srfi37.Lit4, charsequence7, .args, .seeds);
_L13:
        if (!flag1) goto _L17; else goto _L16
_L19:
        if (flag2) goto _L20; else goto _L21
_L21:
        return call_with_values.callWithValues(.Fn23, .Fn24);
    }

    public ()
    {
    }
}
