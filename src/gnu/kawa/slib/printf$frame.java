// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.Complex;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.numbers;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            printf

public class lambda.Fn1 extends ModuleBody
{
    public class printf.frame0 extends ModuleBody
    {

        Object digs;
        Object ex;
        final ModuleMethod lambda$Fn2;
        final ModuleMethod lambda$Fn3;
        Object num;
        Object sgn;
        printf.frame staticLink;

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
            Object obj4 = Scheme.numEqu.apply2(obj, Integer.valueOf(-1 + staticLink.n));
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
            Object obj5 = staticLink.str;
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
            aobj[0] = staticLink.proc;
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
            return printf.frame.lambda1parseError();
        }

        Object lambda7(Object obj, Object obj1, Object obj2)
        {
            printf.frame1 frame1_1 = new printf.frame1();
            frame1_1.staticLink = this;
            frame1_1.sgn = obj;
            frame1_1.digs = obj1;
            frame1_1.ex = obj2;
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
            return printf.stdio$ClParseFloat(numbers.number$To$String(numbers.imagPart(complex)), frame1_1.lambda$Fn4);
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

        public printf.frame0()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 2, null, 16388);
            modulemethod.setProperty("source-location", "printf.scm:111");
            lambda$Fn2 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 3, null, 12291);
            modulemethod1.setProperty("source-location", "printf.scm:123");
            lambda$Fn3 = modulemethod1;
        }
    }

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

    public class printf.frame2 extends ModuleBody
    {

        Object cont;
        final ModuleMethod lambda$Fn5;
        final ModuleMethod lambda$Fn6;
        printf.frame staticLink;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 11)
            {
                return lambda9(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 10)
            {
                return lambda10(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda10(Object obj, Object obj1)
        {
            printf.frame3 frame3_1 = new printf.frame3();
            frame3_1.staticLink = this;
            frame3_1.sgn = obj1;
            return staticLink.lambda3digits(obj, frame3_1.lambda$Fn7);
        }

        Object lambda9(Object obj)
        {
            return staticLink.lambda2sign(obj, lambda$Fn6);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 11)
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

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 10)
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

        public printf.frame2()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 10, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:81");
            lambda$Fn6 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 11, null, 4097);
            modulemethod1.setProperty("source-location", "printf.scm:78");
            lambda$Fn5 = modulemethod1;
        }
    }

    public class printf.frame3 extends ModuleBody
    {

        final ModuleMethod lambda$Fn7;
        Object sgn;
        printf.frame2 staticLink;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 9)
            {
                return lambda11(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda11(Object obj, Object obj1)
        {
            printf.frame4 frame4_1 = new printf.frame4();
            frame4_1.staticLink = this;
            frame4_1.idigs = obj1;
            ModuleMethod modulemethod = frame4_1.lambda$Fn8;
            Object obj2 = Scheme.numLss.apply2(obj, Integer.valueOf(staticLink.staticLink.n));
            boolean flag;
            Char char1;
            CharSequence charsequence;
            int i;
            try
            {
                flag = ((Boolean)obj2).booleanValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj2);
            }
            if (!flag) goto _L2; else goto _L1
_L1:
            char1 = printf.Lit11;
            Object obj3 = staticLink.staticLink.str;
            try
            {
                charsequence = (CharSequence)obj3;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 1, obj3);
            }
            try
            {
                i = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 2, obj);
            }
            if (!characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence, i)))) goto _L4; else goto _L3
_L3:
            return Scheme.applyToArgs.apply2(modulemethod, AddOp.$Pl.apply2(obj, printf.Lit7));
_L2:
            if (flag) goto _L3; else goto _L4
_L4:
            return Scheme.applyToArgs.apply2(modulemethod, obj);
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 9)
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

        public printf.frame3()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 9, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:84");
            lambda$Fn7 = modulemethod;
        }
    }

    public class printf.frame4 extends ModuleBody
    {

        Object idigs;
        final ModuleMethod lambda$Fn8;
        final ModuleMethod lambda$Fn9;
        printf.frame3 staticLink;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 8)
            {
                return lambda12(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 7)
            {
                return lambda13(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda12(Object obj)
        {
            return staticLink.staticLink.staticLink.lambda3digits(obj, lambda$Fn9);
        }

        Object lambda13(Object obj, Object obj1)
        {
            printf.frame5 frame5_1 = new printf.frame5();
            frame5_1.staticLink = this;
            frame5_1.fdigs = obj1;
            ModuleMethod modulemethod = frame5_1.lambda$Fn10;
            printf.frame frame8 = staticLink.staticLink.staticLink;
            printf.frame6 frame6_1 = new printf.frame6();
            frame6_1.staticLink = frame8;
            frame6_1.cont = modulemethod;
            if (Scheme.numGEq.apply2(obj, Integer.valueOf(staticLink.staticLink.staticLink.n)) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply3(frame6_1.cont, obj, printf.Lit1);
            }
            Object obj2 = staticLink.staticLink.staticLink.str;
            CharSequence charsequence;
            int i;
            try
            {
                charsequence = (CharSequence)obj2;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-ref", 1, obj2);
            }
            try
            {
                i = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 2, obj);
            }
            if (lists.memv(Char.make(strings.stringRef(charsequence, i)), printf.Lit10) != Boolean.FALSE)
            {
                return staticLink.staticLink.staticLink.lambda2sign(AddOp.$Pl.apply2(obj, printf.Lit7), frame6_1.lambda$Fn11);
            } else
            {
                return Scheme.applyToArgs.apply3(frame6_1.cont, obj, printf.Lit1);
            }
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 8)
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

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 7)
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

        public printf.frame4()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 7, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:90");
            lambda$Fn9 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 8, null, 4097);
            modulemethod1.setProperty("source-location", "printf.scm:87");
            lambda$Fn8 = modulemethod1;
        }
    }

    public class printf.frame5 extends ModuleBody
    {

        Object fdigs;
        final ModuleMethod lambda$Fn10;
        printf.frame4 staticLink;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 6)
            {
                return lambda14(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda14(Object obj, Object obj1)
        {
            Object aobj[] = new Object[3];
            aobj[0] = "0";
            aobj[1] = staticLink.idigs;
            aobj[2] = fdigs;
            gnu.lists.FString fstring = strings.stringAppend(aobj);
            int i = strings.stringLength(fstring);
            Object obj2 = printf.Lit7;
            AddOp addop = AddOp.$Pl;
            Object obj3 = staticLink.idigs;
            CharSequence charsequence;
            Object obj4;
            try
            {
                charsequence = (CharSequence)obj3;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-length", 1, obj3);
            }
            obj4 = addop.apply2(obj1, Integer.valueOf(strings.stringLength(charsequence)));
            do
            {
                if (Scheme.numGEq.apply2(obj2, Integer.valueOf(i)) != Boolean.FALSE)
                {
                    gnu.kawa.functions.ApplyToArgs applytoargs1 = Scheme.applyToArgs;
                    Object aobj2[] = new Object[5];
                    aobj2[0] = staticLink.staticLink.staticLink.cont;
                    aobj2[1] = obj;
                    aobj2[2] = staticLink.staticLink.sgn;
                    aobj2[3] = "0";
                    aobj2[4] = printf.Lit7;
                    return applytoargs1.applyN(aobj2);
                }
                Char char1 = printf.Lit9;
                int j;
                try
                {
                    j = ((Number)obj2).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-ref", 2, obj2);
                }
                if (characters.isChar$Eq(char1, Char.make(strings.stringRef(fstring, j))))
                {
                    obj2 = AddOp.$Pl.apply2(obj2, printf.Lit7);
                    obj4 = AddOp.$Mn.apply2(obj4, printf.Lit7);
                } else
                {
                    gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
                    Object aobj1[] = new Object[5];
                    aobj1[0] = staticLink.staticLink.staticLink.cont;
                    aobj1[1] = obj;
                    aobj1[2] = staticLink.staticLink.sgn;
                    Object obj5 = AddOp.$Mn.apply2(obj2, printf.Lit7);
                    int k;
                    try
                    {
                        k = ((Number)obj5).intValue();
                    }
                    catch (ClassCastException classcastexception2)
                    {
                        throw new WrongType(classcastexception2, "substring", 2, obj5);
                    }
                    aobj1[3] = strings.substring(fstring, k, i);
                    aobj1[4] = obj4;
                    return applytoargs.applyN(aobj1);
                }
            } while (true);
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 6)
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

        public printf.frame5()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 6, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:92");
            lambda$Fn10 = modulemethod;
        }
    }

    public class printf.frame6 extends ModuleBody
    {

        Object cont;
        final ModuleMethod lambda$Fn11;
        printf.frame staticLink;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 5)
            {
                return lambda15(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda15(Object obj, Object obj1)
        {
            printf.frame7 frame7_1 = new printf.frame7();
            frame7_1.staticLink = this;
            frame7_1.sgn = obj1;
            return staticLink.lambda3digits(obj, frame7_1.lambda$Fn12);
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 5)
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

        public printf.frame6()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 5, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:67");
            lambda$Fn11 = modulemethod;
        }
    }

    public class printf.frame7 extends ModuleBody
    {

        final ModuleMethod lambda$Fn12;
        Object sgn;
        printf.frame6 staticLink;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 4)
            {
                return lambda16(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda16(Object obj, Object obj1)
        {
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Object obj2 = staticLink.cont;
            Char char1 = printf.Lit5;
            Object obj3 = sgn;
            Char char2;
            try
            {
                char2 = (Char)obj3;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "char=?", 2, obj3);
            }
            if (characters.isChar$Eq(char1, char2))
            {
                AddOp addop = AddOp.$Mn;
                CharSequence charsequence;
                Object obj4;
                CharSequence charsequence1;
                try
                {
                    charsequence1 = (CharSequence)obj1;
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "string->number", 1, obj1);
                }
                obj4 = addop.apply1(numbers.string$To$Number(charsequence1));
            } else
            {
                try
                {
                    charsequence = (CharSequence)obj1;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string->number", 1, obj1);
                }
                obj4 = numbers.string$To$Number(charsequence);
            }
            return applytoargs.apply3(obj2, obj, obj4);
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 4)
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

        public printf.frame7()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 4, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:69");
            lambda$Fn12 = modulemethod;
        }
    }


    final ModuleMethod lambda$Fn1;
    int n;
    Object proc;
    Object str;

    public static Boolean lambda1parseError()
    {
        return Boolean.FALSE;
    }

    public Object apply4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3)
    {
        if (modulemethod.selector == 12)
        {
            return lambda5(obj, obj1, obj2, obj3);
        } else
        {
            return super.apply4(modulemethod, obj, obj1, obj2, obj3);
        }
    }

    public Object lambda2sign(Object obj, Object obj1)
    {
        if (Scheme.numLss.apply2(obj, Integer.valueOf(n)) != Boolean.FALSE)
        {
            Object obj2 = str;
            CharSequence charsequence;
            int i;
            char c;
            Object obj3;
            try
            {
                charsequence = (CharSequence)obj2;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-ref", 1, obj2);
            }
            try
            {
                i = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 2, obj);
            }
            c = strings.stringRef(charsequence, i);
            obj3 = Scheme.isEqv.apply2(Char.make(c), printf.Lit5);
            if (obj3 == Boolean.FALSE ? Scheme.isEqv.apply2(Char.make(c), printf.Lit6) != Boolean.FALSE : obj3 != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply3(obj1, AddOp.$Pl.apply2(obj, printf.Lit7), Char.make(c));
            } else
            {
                return Scheme.applyToArgs.apply3(obj1, obj, printf.Lit6);
            }
        } else
        {
            return Values.empty;
        }
    }

    public Object lambda3digits(Object obj, Object obj1)
    {
        Object obj2 = obj;
_L5:
        Object obj3 = Scheme.numGEq.apply2(obj2, Integer.valueOf(n));
        boolean flag;
        CharSequence charsequence;
        int i;
        boolean flag1;
        Char char1;
        CharSequence charsequence1;
        int j;
        gnu.kawa.functions.ApplyToArgs applytoargs;
        CharSequence charsequence2;
        int k;
        int l;
        Object obj7;
        try
        {
            flag = ((Boolean)obj3).booleanValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "x", -2, obj3);
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        if (flag) goto _L4; else goto _L3
_L3:
        obj2 = AddOp.$Pl.apply2(obj2, printf.Lit7);
          goto _L5
_L2:
        Object obj4 = str;
        try
        {
            charsequence = (CharSequence)obj4;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 1, obj4);
        }
        try
        {
            i = ((Number)obj2).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "string-ref", 2, obj2);
        }
        flag1 = unicode.isCharNumeric(Char.make(strings.stringRef(charsequence, i)));
        if (!flag1) goto _L7; else goto _L6
_L6:
        if (flag1) goto _L3; else goto _L4
_L4:
        applytoargs = Scheme.applyToArgs;
        Object obj5;
        if (Scheme.numEqu.apply2(obj, obj2) != Boolean.FALSE)
        {
            obj7 = "0";
        } else
        {
            Object obj6 = str;
            ClassCastException classcastexception3;
            ClassCastException classcastexception4;
            try
            {
                charsequence2 = (CharSequence)obj6;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "substring", 1, obj6);
            }
            try
            {
                k = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "substring", 2, obj);
            }
            try
            {
                l = ((Number)obj2).intValue();
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "substring", 3, obj2);
            }
            obj7 = strings.substring(charsequence2, k, l);
        }
        return applytoargs.apply3(obj1, obj2, obj7);
_L7:
        char1 = printf.Lit8;
        obj5 = str;
        try
        {
            charsequence1 = (CharSequence)obj5;
        }
        // Misplaced declaration of an exception variable
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "string-ref", 1, obj5);
        }
        try
        {
            j = ((Number)obj2).intValue();
        }
        // Misplaced declaration of an exception variable
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "string-ref", 2, obj2);
        }
        if (!characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence1, j)))) goto _L4; else goto _L3
    }

    public Object lambda4real(Object obj, Object obj1)
    {
        ModuleMethod modulemethod;
          = new ();
        .staticLink = this;
        .cont = obj1;
        modulemethod = .Fn5;
_L5:
        Object obj2 = Scheme.numLss.apply2(obj, Integer.valueOf(-1 + n));
        boolean flag;
        CharSequence charsequence;
        int i;
        char c;
        Object obj5;
        Object obj6;
        Char char1;
        CharSequence charsequence1;
        int j;
        try
        {
            flag = ((Boolean)obj2).booleanValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "x", -2, obj2);
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        char1 = printf.Lit8;
        Object obj7 = str;
        try
        {
            charsequence1 = (CharSequence)obj7;
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "string-ref", 1, obj7);
        }
        try
        {
            j = ((Number)obj).intValue();
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "string-ref", 2, obj);
        }
        if (!characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence1, j)))) goto _L4; else goto _L3
_L3:
        Object obj3 = str;
        Object obj4;
        try
        {
            charsequence = (CharSequence)obj3;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 1, obj3);
        }
        obj4 = AddOp.$Pl.apply2(obj, printf.Lit7);
        try
        {
            i = ((Number)obj4).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "string-ref", 2, obj4);
        }
        c = strings.stringRef(charsequence, i);
        obj5 = Scheme.isEqv.apply2(Char.make(c), printf.Lit12);
        if (obj5 == Boolean.FALSE ? (obj6 = Scheme.isEqv.apply2(Char.make(c), printf.Lit3)) == Boolean.FALSE ? Scheme.isEqv.apply2(Char.make(c), printf.Lit13) != Boolean.FALSE : obj6 != Boolean.FALSE : obj5 != Boolean.FALSE)
        {
            obj = AddOp.$Pl.apply2(obj, printf.Lit14);
        } else
        if (Scheme.isEqv.apply2(Char.make(c), printf.Lit11) != Boolean.FALSE)
        {
            return Scheme.applyToArgs.apply2(modulemethod, obj);
        } else
        {
            return lambda1parseError();
        }
          goto _L5
_L2:
        if (flag) goto _L3; else goto _L4
_L4:
        return Scheme.applyToArgs.apply2(modulemethod, obj);
    }

    Object lambda5(Object obj, Object obj1, Object obj2, Object obj3)
    {
          = new ();
        .staticLink = this;
        .sgn = obj1;
        .digs = obj2;
        .ex = obj3;
        if (Scheme.numEqu.apply2(obj, Integer.valueOf(n)) != Boolean.FALSE)
        {
            return Scheme.applyToArgs.apply4(proc, .sgn, .digs, .ex);
        }
        Object obj4 = str;
        CharSequence charsequence;
        int i;
        gnu.kawa.functions.IsEqv iseqv;
        Object obj5;
        CharSequence charsequence1;
        int j;
        try
        {
            charsequence = (CharSequence)obj4;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-ref", 1, obj4);
        }
        try
        {
            i = ((Number)obj).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 2, obj);
        }
        if (lists.memv(Char.make(strings.stringRef(charsequence, i)), printf.Lit2) != Boolean.FALSE)
        {
            return lambda4real(obj, .Fn2);
        }
        iseqv = Scheme.isEqv;
        obj5 = str;
        try
        {
            charsequence1 = (CharSequence)obj5;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "string-ref", 1, obj5);
        }
        try
        {
            j = ((Number)obj).intValue();
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "string-ref", 2, obj);
        }
        if (iseqv.apply2(Char.make(strings.stringRef(charsequence1, j)), printf.Lit4) != Boolean.FALSE)
        {
            Object obj6 = str;
            CharSequence charsequence2;
            try
            {
                charsequence2 = (CharSequence)obj6;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "string->number", 1, obj6);
            }
            .num = numbers.string$To$Number(charsequence2);
            if (.num != Boolean.FALSE)
            {
                Object obj7 = .num;
                Complex complex;
                try
                {
                    complex = (Complex)obj7;
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "real-part", 1, obj7);
                }
                return printf.stdio$ClParseFloat(numbers.number$To$String(numbers.realPart(complex)), .Fn3);
            } else
            {
                return lambda1parseError();
            }
        } else
        {
            return Boolean.FALSE;
        }
    }

    public int match4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3, CallContext callcontext)
    {
        if (modulemethod.selector == 12)
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

    public .lambda.Fn5()
    {
        ModuleMethod modulemethod = new ModuleMethod(this, 12, null, 16388);
        modulemethod.setProperty("source-location", "printf.scm:106");
        lambda$Fn1 = modulemethod;
    }
}
