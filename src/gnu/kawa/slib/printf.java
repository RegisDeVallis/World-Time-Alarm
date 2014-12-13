// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.CharSeq;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.Complex;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;
import kawa.standard.append;

// Referenced classes of package gnu.kawa.slib:
//            genwrite

public class printf extends ModuleBody
{
    public class frame extends ModuleBody
    {

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
            frame2 frame2_1 = new frame2();
            frame2_1.staticLink = this;
            frame2_1.cont = obj1;
            modulemethod = frame2_1.Fn5;
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
            frame0 frame0_1 = new frame0();
            frame0_1.staticLink = this;
            frame0_1.sgn = obj1;
            frame0_1.digs = obj2;
            frame0_1.ex = obj3;
            if (Scheme.numEqu.apply2(obj, Integer.valueOf(n)) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply4(proc, frame0_1.sgn, frame0_1.digs, frame0_1.ex);
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
                return lambda4real(obj, frame0_1.Fn2);
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
                frame0_1.num = numbers.string$To$Number(charsequence2);
                if (frame0_1.num != Boolean.FALSE)
                {
                    Object obj7 = frame0_1.num;
                    Complex complex;
                    try
                    {
                        complex = (Complex)obj7;
                    }
                    catch (ClassCastException classcastexception5)
                    {
                        throw new WrongType(classcastexception5, "real-part", 1, obj7);
                    }
                    return printf.stdio$ClParseFloat(numbers.number$To$String(numbers.realPart(complex)), frame0_1.Fn3);
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

        public frame()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 12, null, 16388);
            modulemethod.setProperty("source-location", "printf.scm:106");
            lambda$Fn1 = modulemethod;
        }
    }

    public class frame0 extends ModuleBody
    {

        Object digs;
        Object ex;
        final ModuleMethod lambda$Fn2;
        final ModuleMethod lambda$Fn3;
        Object num;
        Object sgn;
        frame staticLink;

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
            return frame.lambda1parseError();
        }

        Object lambda7(Object obj, Object obj1, Object obj2)
        {
            frame1 frame1_1 = new frame1();
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
            return printf.stdio$ClParseFloat(numbers.number$To$String(numbers.imagPart(complex)), frame1_1.Fn4);
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

        public frame0()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 2, null, 16388);
            modulemethod.setProperty("source-location", "printf.scm:111");
            lambda$Fn2 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 3, null, 12291);
            modulemethod1.setProperty("source-location", "printf.scm:123");
            lambda$Fn3 = modulemethod1;
        }
    }

    public class frame1 extends ModuleBody
    {

        Object digs;
        Object ex;
        final ModuleMethod lambda$Fn4;
        Object sgn;
        frame0 staticLink;

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

        public frame1()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 1, null, 12291);
            modulemethod.setProperty("source-location", "printf.scm:126");
            lambda$Fn4 = modulemethod;
        }
    }

    public class frame10 extends ModuleBody
    {

        Object alternate$Mnform;
        Object args;
        Object blank;
        final ModuleMethod lambda$Fn13;
        final ModuleMethod lambda$Fn14;
        final ModuleMethod lambda$Fn15;
        final ModuleMethod lambda$Fn16;
        Object leading$Mn0s;
        Object left$Mnadjust;
        Object os;
        Procedure pad;
        Object pr;
        Object precision;
        Object signed;
        frame9 staticLink;
        Object width;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.apply1(modulemethod, obj);

            case 16: // '\020'
                return lambda25(obj);

            case 17: // '\021'
                if (lambda26(obj))
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }

            case 18: // '\022'
                return lambda27(obj);

            case 19: // '\023'
                break;
            }
            if (lambda28(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }

        public Object applyN(ModuleMethod modulemethod, Object aobj[])
        {
            if (modulemethod.selector == 15)
            {
                Object obj = aobj[0];
                int i = -1 + aobj.length;
                Object aobj1[] = new Object[i];
                do
                {
                    if (--i < 0)
                    {
                        return lambda23pad$V(obj, aobj1);
                    }
                    aobj1[i] = aobj[i + 1];
                } while (true);
            } else
            {
                return super.applyN(modulemethod, aobj);
            }
        }

        public Object lambda22readFormatNumber()
        {
            if (Scheme.isEqv.apply2(printf.Lit66, staticLink.fc) != Boolean.FALSE)
            {
                staticLink.lambda18mustAdvance();
                Object obj5 = lists.car.apply1(args);
                args = lists.cdr.apply1(args);
                return obj5;
            }
            Object obj = staticLink.fc;
            Object obj1 = printf.Lit1;
            do
            {
                Object obj2 = staticLink.fc;
                Char char1;
                try
                {
                    char1 = (Char)obj2;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "char-numeric?", 1, obj2);
                }
                if (unicode.isCharNumeric(char1))
                {
                    staticLink.lambda18mustAdvance();
                    Object obj3 = staticLink.fc;
                    AddOp addop = AddOp.$Pl;
                    Object obj4 = MultiplyOp.$St.apply2(obj1, printf.Lit45);
                    Object aobj[];
                    if (obj instanceof Object[])
                    {
                        aobj = (Object[])obj;
                    } else
                    {
                        aobj = (new Object[] {
                            obj
                        });
                    }
                    obj1 = addop.apply2(obj4, numbers.string$To$Number(strings.$make$string$(aobj)));
                    obj = obj3;
                } else
                {
                    return obj1;
                }
            } while (true);
        }

        public Object lambda23pad$V(Object obj, Object aobj[])
        {
            LList llist = LList.makeList(aobj, 0);
            CharSequence charsequence;
            Object obj1;
            Object obj2;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-length", 1, obj);
            }
            obj1 = Integer.valueOf(strings.stringLength(charsequence));
            obj2 = llist;
            do
            {
                if (Scheme.numGEq.apply2(obj1, width) != Boolean.FALSE)
                {
                    return lists.cons(obj, llist);
                }
                Object obj3;
                if (lists.isNull(obj2))
                {
                    Object obj4;
                    if (left$Mnadjust != Boolean.FALSE)
                    {
                        Object aobj1[] = new Object[2];
                        aobj1[0] = llist;
                        Object obj6 = AddOp.$Mn.apply2(width, obj1);
                        AddOp addop;
                        CharSequence charsequence1;
                        int i;
                        int j;
                        int k;
                        try
                        {
                            k = ((Number)obj6).intValue();
                        }
                        catch (ClassCastException classcastexception4)
                        {
                            throw new WrongType(classcastexception4, "make-string", 1, obj6);
                        }
                        aobj1[1] = LList.list1(strings.makeString(k, printf.Lit29));
                        return lists.cons(obj, append.append$V(aobj1));
                    }
                    if (leading$Mn0s != Boolean.FALSE)
                    {
                        Object obj5 = AddOp.$Mn.apply2(width, obj1);
                        try
                        {
                            j = ((Number)obj5).intValue();
                        }
                        catch (ClassCastException classcastexception3)
                        {
                            throw new WrongType(classcastexception3, "make-string", 1, obj5);
                        }
                        return lists.cons(obj, lists.cons(strings.makeString(j, printf.Lit9), llist));
                    }
                    obj4 = AddOp.$Mn.apply2(width, obj1);
                    try
                    {
                        i = ((Number)obj4).intValue();
                    }
                    catch (ClassCastException classcastexception2)
                    {
                        throw new WrongType(classcastexception2, "make-string", 1, obj4);
                    }
                    return lists.cons(strings.makeString(i, printf.Lit29), lists.cons(obj, llist));
                }
                addop = AddOp.$Pl;
                obj3 = lists.car.apply1(obj2);
                try
                {
                    charsequence1 = (CharSequence)obj3;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-length", 1, obj3);
                }
                obj1 = addop.apply2(obj1, Integer.valueOf(strings.stringLength(charsequence1)));
                obj2 = lists.cdr.apply1(obj2);
            } while (true);
        }

        public Object lambda24integerConvert(Object obj, Object obj1, Object obj2)
        {
            Object obj3 = precision;
            gnu.math.RealNum realnum;
            Object obj4;
            Object obj5;
            Object obj8;
            try
            {
                realnum = LangObjType.coerceRealNum(obj3);
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "negative?", 1, obj3);
            }
            ClassCastException classcastexception2;
            ClassCastException classcastexception3;
            ClassCastException classcastexception4;
            ClassCastException classcastexception5;
            ClassCastException classcastexception6;
            ClassCastException classcastexception7;
            if (!numbers.isNegative(realnum))
            {
                leading$Mn0s = Boolean.FALSE;
                Object obj9 = precision;
                Boolean boolean1;
                int i;
                int j;
                gnu.kawa.functions.IsEqv iseqv;
                Char char1;
                CharSequence charsequence;
                String s;
                Object aobj[];
                gnu.kawa.functions.NumberCompare numbercompare;
                CharSequence charsequence1;
                Object obj6;
                AddOp addop;
                Object obj7;
                CharSequence charsequence2;
                int k;
                String s1;
                CharSequence charsequence3;
                CharSequence charsequence4;
                Number number;
                int l;
                Symbol symbol;
                Number number1;
                boolean flag;
                try
                {
                    number1 = (Number)obj9;
                }
                catch (ClassCastException classcastexception11)
                {
                    throw new WrongType(classcastexception11, "zero?", 1, obj9);
                }
                flag = numbers.isZero(number1);
                if (flag ? Scheme.isEqv.apply2(printf.Lit1, obj) != Boolean.FALSE : flag)
                {
                    obj = "";
                }
                obj4 = obj;
            } else
            {
                obj4 = obj;
            }
            if (misc.isSymbol(obj4))
            {
                try
                {
                    symbol = (Symbol)obj4;
                }
                catch (ClassCastException classcastexception10)
                {
                    throw new WrongType(classcastexception10, "symbol->string", 1, obj4);
                }
                obj5 = misc.symbol$To$String(symbol);
            } else
            if (numbers.isNumber(obj4))
            {
                try
                {
                    number = (Number)obj4;
                }
                catch (ClassCastException classcastexception8)
                {
                    throw new WrongType(classcastexception8, "number->string", 1, obj4);
                }
                try
                {
                    l = ((Number)obj1).intValue();
                }
                catch (ClassCastException classcastexception9)
                {
                    throw new WrongType(classcastexception9, "number->string", 2, obj1);
                }
                obj5 = numbers.number$To$String(number, l);
            } else
            {
                try
                {
                    boolean1 = Boolean.FALSE;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "x", -2, obj4);
                }
                if (obj4 != boolean1)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                j = 1 & i + 1;
                if (j == 0 ? lists.isNull(obj4) : j != 0)
                {
                    obj5 = "0";
                } else
                if (strings.isString(obj4))
                {
                    obj5 = obj4;
                } else
                {
                    obj5 = "1";
                }
            }
            if (obj2 != Boolean.FALSE)
            {
                obj5 = Scheme.applyToArgs.apply2(obj2, obj5);
            }
            if (!IsEqual.apply("", obj5)) goto _L2; else goto _L1
_L1:
            s = "";
_L3:
            aobj = new Object[2];
            numbercompare = Scheme.numLss;
            try
            {
                charsequence1 = (CharSequence)obj5;
            }
            // Misplaced declaration of an exception variable
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "string-length", 1, obj5);
            }
            if (numbercompare.apply2(Integer.valueOf(strings.stringLength(charsequence1)), precision) != Boolean.FALSE)
            {
                addop = AddOp.$Mn;
                obj7 = precision;
                try
                {
                    charsequence2 = (CharSequence)obj5;
                }
                // Misplaced declaration of an exception variable
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "string-length", 1, obj5);
                }
                obj8 = addop.apply2(obj7, Integer.valueOf(strings.stringLength(charsequence2)));
                try
                {
                    k = ((Number)obj8).intValue();
                }
                // Misplaced declaration of an exception variable
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "make-string", 1, obj8);
                }
                obj6 = strings.makeString(k, printf.Lit9);
            } else
            {
                obj6 = "";
            }
            aobj[0] = obj6;
            aobj[1] = obj5;
            return lambda23pad$V(s, aobj);
_L2:
            iseqv = Scheme.isEqv;
            char1 = printf.Lit5;
            try
            {
                charsequence = (CharSequence)obj5;
            }
            // Misplaced declaration of an exception variable
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 1, obj5);
            }
            if (iseqv.apply2(char1, Char.make(strings.stringRef(charsequence, 0))) == Boolean.FALSE)
            {
                break MISSING_BLOCK_LABEL_445;
            }
            try
            {
                charsequence3 = (CharSequence)obj5;
            }
            // Misplaced declaration of an exception variable
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "substring", 1, obj5);
            }
            charsequence4 = (CharSequence)obj5;
            obj5 = strings.substring(charsequence3, 1, strings.stringLength(charsequence4));
            s = "-";
              goto _L3
            if (signed != Boolean.FALSE)
            {
                s = "+";
            } else
            if (blank != Boolean.FALSE)
            {
                s = " ";
            } else
            if (alternate$Mnform != Boolean.FALSE)
            {
                if (Scheme.isEqv.apply2(obj1, printf.Lit48) != Boolean.FALSE)
                {
                    s1 = "0";
                } else
                if (Scheme.isEqv.apply2(obj1, printf.Lit50) != Boolean.FALSE)
                {
                    s1 = "0x";
                } else
                {
                    s1 = "";
                }
                s = s1;
            } else
            {
                s = "";
            }
              goto _L3
            classcastexception7;
            throw new WrongType(classcastexception7, "string-length", 1, obj5);
            if (true) goto _L5; else goto _L4
_L5:
            break MISSING_BLOCK_LABEL_98;
_L4:
        }

        Object lambda25(Object obj)
        {
            AddOp addop = AddOp.$Pl;
            Object obj1 = pr;
            CharSequence charsequence;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-length", 1, obj);
            }
            pr = addop.apply2(obj1, Integer.valueOf(strings.stringLength(charsequence)));
            return Scheme.applyToArgs.apply2(staticLink.out, obj);
        }

        boolean lambda26(Object obj)
        {
            Special _tmp = Special.undefined;
            AddOp addop = AddOp.$Mn;
            Object obj1 = pr;
            CharSequence charsequence;
            Object obj2;
            gnu.math.RealNum realnum;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-length", 1, obj);
            }
            obj2 = addop.apply2(obj1, Integer.valueOf(strings.stringLength(charsequence)));
            try
            {
                realnum = LangObjType.coerceRealNum(obj2);
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "negative?", 1, obj2);
            }
            if (numbers.isNegative(realnum))
            {
                gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
                Object obj4 = staticLink.out;
                Object obj3;
                gnu.math.RealNum realnum1;
                CharSequence charsequence1;
                Object obj5;
                int i;
                try
                {
                    charsequence1 = (CharSequence)obj;
                }
                catch (ClassCastException classcastexception3)
                {
                    throw new WrongType(classcastexception3, "substring", 1, obj);
                }
                obj5 = pr;
                try
                {
                    i = ((Number)obj5).intValue();
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "substring", 3, obj5);
                }
                applytoargs.apply2(obj4, strings.substring(charsequence1, 0, i));
                obj3 = printf.Lit1;
            } else
            {
                Scheme.applyToArgs.apply2(staticLink.out, obj);
                obj3 = obj2;
            }
            pr = obj3;
            try
            {
                realnum1 = LangObjType.coerceRealNum(obj2);
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "positive?", 1, obj2);
            }
            return numbers.isPositive(realnum1);
        }

        Boolean lambda27(Object obj)
        {
            AddOp addop = AddOp.$Mn;
            Object obj1 = pr;
            CharSequence charsequence;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-length", 1, obj);
            }
            pr = addop.apply2(obj1, Integer.valueOf(strings.stringLength(charsequence)));
            if (os == Boolean.FALSE)
            {
                Scheme.applyToArgs.apply2(staticLink.out, obj);
            } else
            {
                Object obj2 = pr;
                gnu.math.RealNum realnum;
                try
                {
                    realnum = LangObjType.coerceRealNum(obj2);
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "negative?", 1, obj2);
                }
                if (numbers.isNegative(realnum))
                {
                    Scheme.applyToArgs.apply2(staticLink.out, os);
                    os = Boolean.FALSE;
                    Scheme.applyToArgs.apply2(staticLink.out, obj);
                } else
                {
                    Object aobj[] = new Object[2];
                    aobj[0] = os;
                    aobj[1] = obj;
                    os = strings.stringAppend(aobj);
                }
            }
            return Boolean.TRUE;
        }

        boolean lambda28(Object obj)
        {
            Special _tmp = Special.undefined;
            AddOp addop = AddOp.$Mn;
            Object obj1 = pr;
            CharSequence charsequence;
            Object obj2;
            gnu.math.RealNum realnum;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-length", 1, obj);
            }
            obj2 = addop.apply2(obj1, Integer.valueOf(strings.stringLength(charsequence)));
            try
            {
                realnum = LangObjType.coerceRealNum(obj2);
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "negative?", 1, obj2);
            }
            if (numbers.isNegative(realnum))
            {
                Object aobj1[] = new Object[2];
                aobj1[0] = os;
                Object aobj[];
                gnu.math.RealNum realnum1;
                CharSequence charsequence1;
                Object obj3;
                int i;
                try
                {
                    charsequence1 = (CharSequence)obj;
                }
                catch (ClassCastException classcastexception3)
                {
                    throw new WrongType(classcastexception3, "substring", 1, obj);
                }
                obj3 = pr;
                try
                {
                    i = ((Number)obj3).intValue();
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "substring", 3, obj3);
                }
                aobj1[1] = strings.substring(charsequence1, 0, i);
                os = strings.stringAppend(aobj1);
            } else
            {
                aobj = new Object[2];
                aobj[0] = os;
                aobj[1] = obj;
                os = strings.stringAppend(aobj);
            }
            pr = obj2;
            try
            {
                realnum1 = LangObjType.coerceRealNum(obj2);
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "positive?", 1, obj2);
            }
            return numbers.isPositive(realnum1);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.match1(modulemethod, obj, callcontext);

            case 19: // '\023'
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;

            case 18: // '\022'
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;

            case 17: // '\021'
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;

            case 16: // '\020'
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }
        }

        public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
        {
            if (modulemethod.selector == 15)
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

        public frame10()
        {
            pad = new ModuleMethod(this, 15, printf.Lit67, -4095);
            ModuleMethod modulemethod = new ModuleMethod(this, 16, null, 4097);
            modulemethod.setProperty("source-location", "printf.scm:472");
            lambda$Fn13 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 17, null, 4097);
            modulemethod1.setProperty("source-location", "printf.scm:476");
            lambda$Fn14 = modulemethod1;
            ModuleMethod modulemethod2 = new ModuleMethod(this, 18, null, 4097);
            modulemethod2.setProperty("source-location", "printf.scm:484");
            lambda$Fn15 = modulemethod2;
            ModuleMethod modulemethod3 = new ModuleMethod(this, 19, null, 4097);
            modulemethod3.setProperty("source-location", "printf.scm:494");
            lambda$Fn16 = modulemethod3;
        }
    }

    public class frame11 extends ModuleBody
    {

        Object fc;
        Procedure format$Mnreal;
        final ModuleMethod lambda$Fn17;
        frame10 staticLink;

        public Object applyN(ModuleMethod modulemethod, Object aobj[])
        {
            Object obj;
            Object obj1;
            Object obj2;
            int i;
            Object aobj1[];
            switch (modulemethod.selector)
            {
            default:
                return super.applyN(modulemethod, aobj);

            case 13: // '\r'
                Object obj3 = aobj[0];
                Object obj4 = aobj[1];
                Object obj5 = aobj[2];
                Object obj6 = aobj[3];
                int j = -4 + aobj.length;
                Object aobj2[] = new Object[j];
                do
                {
                    if (--j < 0)
                    {
                        return lambda30formatReal$V(obj3, obj4, obj5, obj6, aobj2);
                    }
                    aobj2[j] = aobj[j + 4];
                } while (true);

            case 14: // '\016'
                obj = aobj[0];
                obj1 = aobj[1];
                obj2 = aobj[2];
                i = -3 + aobj.length;
                aobj1 = new Object[i];
                break;
            }
            do
            {
                if (--i < 0)
                {
                    return lambda31$V(obj, obj1, obj2, aobj1);
                }
                aobj1[i] = aobj[i + 3];
            } while (true);
        }

        public Object lambda29f(Object obj, Object obj1, Object obj2)
        {
            CharSequence charsequence;
            Object obj3;
            Object obj4;
            Object obj5;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "stdio:round-string", 0, obj);
            }
            obj3 = AddOp.$Pl.apply2(obj1, staticLink.precision);
            if (obj2 != Boolean.FALSE)
            {
                obj4 = obj1;
            } else
            {
                obj4 = obj2;
            }
            obj5 = printf.stdio$ClRoundString(charsequence, obj3, obj4);
            if (Scheme.numGEq.apply2(obj1, printf.Lit1) != Boolean.FALSE)
            {
                Object obj7;
                Number number1;
                IntNum intnum;
                Object aobj1[];
                Object obj10;
                CharSequence charsequence2;
                int j;
                int k;
                CharSequence charsequence3;
                CharSequence charsequence4;
                int l;
                CharSequence charsequence5;
                CharSequence charsequence6;
                boolean flag1;
                Object obj11;
                try
                {
                    number1 = (Number)obj1;
                }
                catch (ClassCastException classcastexception3)
                {
                    throw new WrongType(classcastexception3, "zero?", 1, obj1);
                }
                if (numbers.isZero(number1))
                {
                    intnum = printf.Lit1;
                } else
                {
                    Char char1 = printf.Lit9;
                    Number number;
                    Object aobj[];
                    int i;
                    boolean flag;
                    Object obj9;
                    String s;
                    CharSequence charsequence1;
                    try
                    {
                        charsequence1 = (CharSequence)obj5;
                    }
                    catch (ClassCastException classcastexception4)
                    {
                        throw new WrongType(classcastexception4, "string-ref", 1, obj5);
                    }
                    if (characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence1, 0))))
                    {
                        intnum = printf.Lit7;
                    } else
                    {
                        intnum = printf.Lit1;
                    }
                }
                aobj1 = new Object[2];
                aobj1[0] = printf.Lit7;
                aobj1[1] = AddOp.$Pl.apply2(printf.Lit7, obj1);
                obj10 = numbers.max(aobj1);
                try
                {
                    charsequence2 = (CharSequence)obj5;
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "substring", 1, obj5);
                }
                try
                {
                    j = intnum.intValue();
                }
                catch (ClassCastException classcastexception6)
                {
                    throw new WrongType(classcastexception6, "substring", 2, intnum);
                }
                try
                {
                    k = ((Number)obj10).intValue();
                }
                catch (ClassCastException classcastexception7)
                {
                    throw new WrongType(classcastexception7, "substring", 3, obj10);
                }
                charsequence3 = strings.substring(charsequence2, j, k);
                try
                {
                    charsequence4 = (CharSequence)obj5;
                }
                catch (ClassCastException classcastexception8)
                {
                    throw new WrongType(classcastexception8, "substring", 1, obj5);
                }
                try
                {
                    l = ((Number)obj10).intValue();
                }
                catch (ClassCastException classcastexception9)
                {
                    throw new WrongType(classcastexception9, "substring", 2, obj10);
                }
                try
                {
                    charsequence5 = (CharSequence)obj5;
                }
                catch (ClassCastException classcastexception10)
                {
                    throw new WrongType(classcastexception10, "string-length", 1, obj5);
                }
                charsequence6 = strings.substring(charsequence4, l, strings.stringLength(charsequence5));
                flag1 = strings.isString$Eq(charsequence6, "");
                if (flag1 ? staticLink.Mnform == Boolean.FALSE : flag1)
                {
                    obj11 = LList.Empty;
                } else
                {
                    obj11 = LList.list2(".", charsequence6);
                }
                obj7 = lists.cons(charsequence3, obj11);
            } else
            {
                Object obj6 = staticLink.precision;
                try
                {
                    number = (Number)obj6;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "zero?", 1, obj6);
                }
                if (numbers.isZero(number))
                {
                    if (staticLink.Mnform != Boolean.FALSE)
                    {
                        s = "0.";
                    } else
                    {
                        s = "0";
                    }
                    return LList.list1(s);
                }
                if (obj2 != Boolean.FALSE)
                {
                    flag = strings.isString$Eq(obj5, "");
                    if (flag)
                    {
                        obj9 = LList.list1("0");
                    } else
                    if (flag)
                    {
                        obj9 = Boolean.TRUE;
                    } else
                    {
                        obj9 = Boolean.FALSE;
                    }
                    obj7 = obj9;
                } else
                {
                    obj7 = obj2;
                }
                if (obj7 == Boolean.FALSE)
                {
                    aobj = new Object[2];
                    aobj[0] = staticLink.precision;
                    aobj[1] = AddOp.$Mn.apply2(printf.Lit17, obj1);
                    Object obj8 = numbers.min(aobj);
                    try
                    {
                        i = ((Number)obj8).intValue();
                    }
                    catch (ClassCastException classcastexception2)
                    {
                        throw new WrongType(classcastexception2, "make-string", 1, obj8);
                    }
                    return LList.list3("0.", strings.makeString(i, printf.Lit9), obj5);
                }
            }
            return obj7;
        }

        public Object lambda30formatReal$V(Object obj, Object obj1, Object obj2, Object obj3, Object aobj[])
        {
            LList llist = LList.makeList(aobj, 0);
            if (!lists.isNull(llist)) goto _L2; else goto _L1
_L1:
            Object obj9;
            Object obj11;
            Object obj20;
            Char char1 = printf.Lit5;
            Object aobj1[];
            Object aobj2[];
            Char char2;
            String s;
            Object obj4;
            Object obj5;
            Object obj6;
            Object obj7;
            String s1;
            gnu.math.RealNum realnum;
            DivideOp divideop;
            AddOp addop;
            IntNum intnum;
            Object obj8;
            boolean flag;
            Object obj10;
            Boolean boolean1;
            int i;
            int j;
            gnu.kawa.functions.NumberCompare numbercompare;
            Object obj12;
            Object obj13;
            Object obj14;
            CharSequence charsequence;
            Object obj15;
            Char char3;
            CharSequence charsequence1;
            CharSequence charsequence2;
            int k;
            CharSequence charsequence3;
            CharSequence charsequence4;
            AddOp addop1;
            IntNum intnum2;
            CharSequence charsequence5;
            int l;
            gnu.lists.Pair pair;
            boolean flag1;
            String s2;
            Char char4;
            String s3;
            gnu.math.RealNum realnum1;
            String s4;
            gnu.lists.Pair pair1;
            gnu.kawa.functions.NumberCompare numbercompare1;
            IntNum intnum3;
            IntNum intnum4;
            String s5;
            gnu.lists.Pair pair2;
            Number number;
            Boolean boolean2;
            AddOp addop2;
            Object obj18;
            Object obj19;
            frame10 frame10_1;
            Object aobj3[];
            Object aobj4[];
            FVector fvector;
            int i1;
            DivideOp divideop1;
            AddOp addop3;
            IntNum intnum5;
            Boolean boolean3;
            try
            {
                char2 = (Char)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "char=?", 2, obj1);
            }
            if (characters.isChar$Eq(char1, char2))
            {
                s = "-";
            } else
            if (obj != Boolean.FALSE)
            {
                s = "+";
            } else
            if (staticLink.blank != Boolean.FALSE)
            {
                s = " ";
            } else
            {
                s = "";
            }
_L11:
            obj4 = Scheme.isEqv.apply2(fc, printf.Lit13);
            if (obj4 == Boolean.FALSE ? Scheme.isEqv.apply2(fc, printf.Lit54) != Boolean.FALSE : obj4 != Boolean.FALSE) goto _L4; else goto _L3
_L4:
            obj14 = Boolean.FALSE;
_L9:
            Object obj16;
            IntNum intnum1;
            Object obj17;
            try
            {
                charsequence = (CharSequence)obj2;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "stdio:round-string", 0, obj2);
            }
            obj15 = AddOp.$Pl.apply2(printf.Lit7, staticLink.precision);
            if (obj14 != Boolean.FALSE)
            {
                obj14 = printf.Lit1;
            }
            obj16 = printf.stdio$ClRoundString(charsequence, obj15, obj14);
            char3 = printf.Lit9;
            try
            {
                charsequence1 = (CharSequence)obj16;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "string-ref", 1, obj16);
            }
            if (characters.isChar$Eq(char3, Char.make(strings.stringRef(charsequence1, 0))))
            {
                intnum1 = printf.Lit7;
            } else
            {
                intnum1 = printf.Lit1;
            }
            try
            {
                charsequence2 = (CharSequence)obj16;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "substring", 1, obj16);
            }
            k = 1 + intnum1.intValue();
            try
            {
                charsequence3 = (CharSequence)obj16;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "string-length", 1, obj16);
            }
            charsequence4 = strings.substring(charsequence2, k, strings.stringLength(charsequence3));
            if (!numbers.isZero(intnum1))
            {
                addop1 = AddOp.$Mn;
                intnum2 = printf.Lit7;
                obj3 = addop1.apply2(obj3, intnum2);
            }
            try
            {
                charsequence5 = (CharSequence)obj16;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "substring", 1, obj16);
            }
            try
            {
                l = intnum1.intValue();
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "substring", 2, intnum1);
            }
            pair = LList.list1(strings.substring(charsequence5, l, 1 + intnum1.intValue()));
            flag1 = strings.isString$Eq(charsequence4, "");
            if (flag1 ? staticLink.Mnform == Boolean.FALSE : flag1)
            {
                s2 = "";
            } else
            {
                s2 = ".";
            }
            obj17 = fc;
            try
            {
                char4 = (Char)obj17;
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "char-upper-case?", 1, obj17);
            }
            if (unicode.isCharUpperCase(char4))
            {
                s3 = "E";
            } else
            {
                s3 = "e";
            }
            try
            {
                realnum1 = LangObjType.coerceRealNum(obj3);
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "negative?", 1, obj3);
            }
            if (numbers.isNegative(realnum1))
            {
                s4 = "-";
            } else
            {
                s4 = "+";
            }
            pair1 = LList.chain4(pair, s2, charsequence4, s3, s4);
            numbercompare1 = Scheme.numLss;
            intnum3 = printf.Lit60;
            intnum4 = printf.Lit45;
            if (numbercompare1.apply3(intnum3, obj3, intnum4) != Boolean.FALSE)
            {
                s5 = "0";
            } else
            {
                s5 = "";
            }
            pair2 = LList.chain1(pair1, s5);
            try
            {
                number = (Number)obj3;
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "abs", 1, obj3);
            }
            LList.chain1(pair2, numbers.number$To$String(numbers.abs(number)));
            obj7 = pair;
_L5:
            return lists.cons(s, obj7);
_L3:
            obj5 = Scheme.isEqv.apply2(fc, printf.Lit25);
            if (obj5 == Boolean.FALSE ? Scheme.isEqv.apply2(fc, printf.Lit26) != Boolean.FALSE : obj5 != Boolean.FALSE)
            {
                boolean3 = Boolean.FALSE;
                obj7 = lambda29f(obj2, obj3, boolean3);
            } else
            {
                break MISSING_BLOCK_LABEL_603;
            }
              goto _L5
            obj6 = Scheme.isEqv.apply2(fc, printf.Lit55);
            if (obj6 == Boolean.FALSE ? Scheme.isEqv.apply2(fc, printf.Lit56) != Boolean.FALSE : obj6 != Boolean.FALSE) goto _L7; else goto _L6
_L7:
            obj11 = staticLink.Mnform;
            try
            {
                boolean1 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "strip-0s", -2, obj11);
            }
            if (obj11 != boolean1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            j = 1 & i + 1;
            staticLink.Mnform = Boolean.FALSE;
            numbercompare = Scheme.numLEq;
            obj12 = AddOp.$Mn.apply2(printf.Lit7, staticLink.precision);
            obj13 = staticLink.precision;
            if (numbercompare.apply3(obj12, obj3, obj13) == Boolean.FALSE)
            {
                break MISSING_BLOCK_LABEL_1085;
            }
            staticLink.precision = AddOp.$Mn.apply2(staticLink.precision, obj3);
            if (j != 0)
            {
                boolean2 = Boolean.TRUE;
            } else
            {
                boolean2 = Boolean.FALSE;
            }
            obj7 = lambda29f(obj2, obj3, boolean2);
              goto _L5
_L6:
            if (Scheme.isEqv.apply2(fc, printf.Lit57) != Boolean.FALSE)
            {
                s1 = "";
            } else
            {
label0:
                {
                    if (Scheme.isEqv.apply2(fc, printf.Lit58) == Boolean.FALSE)
                    {
                        break label0;
                    }
                    s1 = " ";
                }
            }
            try
            {
                realnum = LangObjType.coerceRealNum(obj3);
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "negative?", 1, obj3);
            }
            if (numbers.isNegative(realnum))
            {
                divideop1 = DivideOp.quotient;
                addop3 = AddOp.$Mn;
                intnum5 = printf.Lit61;
                obj8 = divideop1.apply2(addop3.apply2(obj3, intnum5), printf.Lit61);
            } else
            {
                divideop = DivideOp.quotient;
                addop = AddOp.$Mn;
                intnum = printf.Lit7;
                obj8 = divideop.apply2(addop.apply2(obj3, intnum), printf.Lit61);
            }
            obj9 = Scheme.numLss.apply3(printf.Lit17, AddOp.$Pl.apply2(obj8, printf.Lit48), Integer.valueOf(vectors.vectorLength(printf.Lit62)));
            try
            {
                flag = ((Boolean)obj9).booleanValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "x", -2, obj9);
            }
            if (flag)
            {
                obj10 = obj8;
            } else
            if (flag)
            {
                obj10 = Boolean.TRUE;
            } else
            {
                obj10 = Boolean.FALSE;
            }
            if (obj10 == Boolean.FALSE) goto _L7; else goto _L8
_L8:
            addop2 = AddOp.$Mn;
            obj18 = MultiplyOp.$St.apply2(printf.Lit61, obj10);
            obj19 = addop2.apply2(obj3, obj18);
            frame10_1 = staticLink;
            aobj3 = new Object[2];
            aobj3[0] = printf.Lit1;
            aobj3[1] = AddOp.$Mn.apply2(staticLink.precision, obj19);
            frame10_1.precision = numbers.max(aobj3);
            aobj4 = new Object[2];
            aobj4[0] = lambda29f(obj2, obj19, Boolean.FALSE);
            fvector = printf.Lit62;
            obj20 = AddOp.$Pl.apply2(obj10, printf.Lit48);
            i1 = ((Number)obj20).intValue();
            aobj4[1] = LList.list2(s1, vectors.vectorRef(fvector, i1));
            obj7 = append.append$V(aobj4);
              goto _L5
            staticLink.precision = AddOp.$Mn.apply2(staticLink.precision, printf.Lit7);
            if (j != 0)
            {
                obj14 = Boolean.TRUE;
            } else
            {
                obj14 = Boolean.FALSE;
            }
              goto _L9
            obj7 = Values.empty;
              goto _L5
_L2:
            aobj1 = new Object[3];
            aobj2 = new Object[0];
            aobj1[0] = lambda30formatReal$V(obj, obj1, obj2, obj3, aobj2);
            aobj1[1] = Scheme.apply.apply3(format$Mnreal, Boolean.TRUE, llist);
            aobj1[2] = printf.Lit63;
            return append.append$V(aobj1);
              goto _L9
            ClassCastException classcastexception13;
            classcastexception13;
            throw new WrongType(classcastexception13, "vector-ref", 2, obj20);
            if (true) goto _L11; else goto _L10
_L10:
        }

        Object lambda31$V(Object obj, Object obj1, Object obj2, Object aobj[])
        {
            LList llist = LList.makeList(aobj, 0);
            gnu.kawa.functions.Apply apply = Scheme.apply;
            Procedure procedure = staticLink.pad;
            gnu.kawa.functions.Apply apply1 = Scheme.apply;
            Object aobj1[] = new Object[6];
            aobj1[0] = format$Mnreal;
            aobj1[1] = staticLink.signed;
            aobj1[2] = obj;
            aobj1[3] = obj1;
            aobj1[4] = obj2;
            aobj1[5] = llist;
            return apply.apply2(procedure, apply1.applyN(aobj1));
        }

        public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.matchN(modulemethod, aobj, callcontext);

            case 14: // '\016'
                callcontext.values = aobj;
                callcontext.proc = modulemethod;
                callcontext.pc = 5;
                return 0;

            case 13: // '\r'
                callcontext.values = aobj;
                callcontext.proc = modulemethod;
                callcontext.pc = 5;
                return 0;
            }
        }

        public frame11()
        {
            format$Mnreal = new ModuleMethod(this, 13, printf.Lit64, -4092);
            ModuleMethod modulemethod = new ModuleMethod(this, 14, null, -4093);
            modulemethod.setProperty("source-location", "printf.scm:401");
            lambda$Fn17 = modulemethod;
        }
    }

    public class frame12 extends ModuleBody
    {

        Object cnt;
        final ModuleMethod lambda$Fn18;
        Object port;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 20)
            {
                return lambda32(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Boolean lambda32(Object obj)
        {
            if (strings.isString(obj))
            {
                AddOp addop = AddOp.$Pl;
                CharSequence charsequence;
                try
                {
                    charsequence = (CharSequence)obj;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "string-length", 1, obj);
                }
                cnt = addop.apply2(Integer.valueOf(strings.stringLength(charsequence)), cnt);
                ports.display(obj, port);
                return Boolean.TRUE;
            } else
            {
                cnt = AddOp.$Pl.apply2(printf.Lit7, cnt);
                ports.display(obj, port);
                return Boolean.TRUE;
            }
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 20)
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

        public frame12()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 20, null, 4097);
            modulemethod.setProperty("source-location", "printf.scm:546");
            lambda$Fn18 = modulemethod;
        }
    }

    public class frame13 extends ModuleBody
    {

        Object cnt;
        Object end;
        final ModuleMethod lambda$Fn19;
        Object s;
        Object str;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 21)
            {
                if (lambda33(obj))
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        boolean lambda33(Object obj)
        {
            if (!strings.isString(obj)) goto _L2; else goto _L1
_L1:
label0:
            {
                Object obj11;
                Object obj12;
                Object obj13;
label1:
                {
                    Object obj7;
                    if (str == Boolean.FALSE)
                    {
                        gnu.kawa.functions.NumberCompare numbercompare = Scheme.numGEq;
                        Object obj10 = AddOp.$Mn.apply2(end, cnt);
                        Object obj1;
                        Boolean boolean1;
                        int i;
                        int j;
                        Object aobj[];
                        CharSequence charsequence;
                        CharSeq charseq;
                        int k;
                        char c;
                        int l;
                        char c1;
                        Object aobj1[];
                        CharSequence charsequence1;
                        Object obj6;
                        CharSeq charseq1;
                        int i1;
                        CharSequence charsequence2;
                        int j1;
                        CharSequence charsequence3;
                        Object aobj2[];
                        CharSequence charsequence4;
                        int k1;
                        CharSequence charsequence5;
                        try
                        {
                            charsequence3 = (CharSequence)obj;
                        }
                        catch (ClassCastException classcastexception10)
                        {
                            throw new WrongType(classcastexception10, "string-length", 1, obj);
                        }
                        if (numbercompare.apply2(obj10, Integer.valueOf(strings.stringLength(charsequence3))) == Boolean.FALSE)
                        {
                            break label1;
                        }
                    }
                    aobj1 = new Object[2];
                    try
                    {
                        charsequence1 = (CharSequence)obj;
                    }
                    catch (ClassCastException classcastexception5)
                    {
                        throw new WrongType(classcastexception5, "string-length", 1, obj);
                    }
                    aobj1[0] = Integer.valueOf(strings.stringLength(charsequence1));
                    aobj1[1] = AddOp.$Mn.apply2(end, cnt);
                    obj6 = numbers.min(aobj1);
                    obj7 = printf.Lit1;
                    while (Scheme.numGEq.apply2(obj7, obj6) == Boolean.FALSE) 
                    {
                        Object obj8 = s;
                        Object obj9;
                        try
                        {
                            charseq1 = (CharSeq)obj8;
                        }
                        catch (ClassCastException classcastexception6)
                        {
                            throw new WrongType(classcastexception6, "string-set!", 1, obj8);
                        }
                        obj9 = cnt;
                        try
                        {
                            i1 = ((Number)obj9).intValue();
                        }
                        catch (ClassCastException classcastexception7)
                        {
                            throw new WrongType(classcastexception7, "string-set!", 2, obj9);
                        }
                        try
                        {
                            charsequence2 = (CharSequence)obj;
                        }
                        catch (ClassCastException classcastexception8)
                        {
                            throw new WrongType(classcastexception8, "string-ref", 1, obj);
                        }
                        try
                        {
                            j1 = ((Number)obj7).intValue();
                        }
                        catch (ClassCastException classcastexception9)
                        {
                            throw new WrongType(classcastexception9, "string-ref", 2, obj7);
                        }
                        strings.stringSet$Ex(charseq1, i1, strings.stringRef(charsequence2, j1));
                        cnt = AddOp.$Pl.apply2(cnt, printf.Lit7);
                        obj7 = AddOp.$Pl.apply2(obj7, printf.Lit7);
                    }
                    break label0;
                }
                aobj2 = new Object[2];
                obj11 = s;
                try
                {
                    charsequence4 = (CharSequence)obj11;
                }
                catch (ClassCastException classcastexception11)
                {
                    throw new WrongType(classcastexception11, "substring", 1, obj11);
                }
                obj12 = cnt;
                try
                {
                    k1 = ((Number)obj12).intValue();
                }
                catch (ClassCastException classcastexception12)
                {
                    throw new WrongType(classcastexception12, "substring", 3, obj12);
                }
                aobj2[0] = strings.substring(charsequence4, 0, k1);
                aobj2[1] = obj;
                s = strings.stringAppend(aobj2);
                obj13 = s;
                try
                {
                    charsequence5 = (CharSequence)obj13;
                }
                catch (ClassCastException classcastexception13)
                {
                    throw new WrongType(classcastexception13, "string-length", 1, obj13);
                }
                cnt = Integer.valueOf(strings.stringLength(charsequence5));
                end = cnt;
            }
_L4:
            if (str != Boolean.FALSE)
            {
                if (Scheme.numGEq.apply2(cnt, end) != Boolean.FALSE)
                {
                    l = 1;
                } else
                {
                    l = 0;
                }
            } else
            if (str != Boolean.FALSE)
            {
                l = 1;
            } else
            {
                l = 0;
            }
            return 1 & l + 1;
_L2:
            if (str != Boolean.FALSE)
            {
                obj1 = Scheme.numGEq.apply2(cnt, end);
            } else
            {
                obj1 = str;
            }
            if (obj1 == Boolean.FALSE)
            {
                Object obj2 = str;
                Object obj4;
                Object obj5;
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
                    i = 1;
                } else
                {
                    i = 0;
                }
                j = 1 & i + 1;
                if (j == 0 ? j != 0 : Scheme.numGEq.apply2(cnt, end) != Boolean.FALSE)
                {
                    aobj = new Object[2];
                    aobj[0] = s;
                    aobj[1] = strings.makeString(100);
                    s = strings.stringAppend(aobj);
                    Object obj3 = s;
                    try
                    {
                        charsequence = (CharSequence)obj3;
                    }
                    catch (ClassCastException classcastexception1)
                    {
                        throw new WrongType(classcastexception1, "string-length", 1, obj3);
                    }
                    end = Integer.valueOf(strings.stringLength(charsequence));
                }
                obj4 = s;
                try
                {
                    charseq = (CharSeq)obj4;
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "string-set!", 1, obj4);
                }
                obj5 = cnt;
                try
                {
                    k = ((Number)obj5).intValue();
                }
                catch (ClassCastException classcastexception3)
                {
                    throw new WrongType(classcastexception3, "string-set!", 2, obj5);
                }
                if (characters.isChar(obj))
                {
                    try
                    {
                        c1 = ((Char)obj).charValue();
                    }
                    catch (ClassCastException classcastexception4)
                    {
                        throw new WrongType(classcastexception4, "string-set!", 3, obj);
                    }
                    c = c1;
                } else
                {
                    c = '?';
                }
                strings.stringSet$Ex(charseq, k, c);
                cnt = AddOp.$Pl.apply2(cnt, printf.Lit7);
            }
            if (true) goto _L4; else goto _L3
_L3:
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 21)
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

        public frame13()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 21, null, 4097);
            modulemethod.setProperty("source-location", "printf.scm:564");
            lambda$Fn19 = modulemethod;
        }
    }

    public class frame2 extends ModuleBody
    {

        Object cont;
        final ModuleMethod lambda$Fn5;
        final ModuleMethod lambda$Fn6;
        frame staticLink;

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
            frame3 frame3_1 = new frame3();
            frame3_1.staticLink = this;
            frame3_1.sgn = obj1;
            return staticLink.lambda3digits(obj, frame3_1.Fn7);
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

        public frame2()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 10, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:81");
            lambda$Fn6 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 11, null, 4097);
            modulemethod1.setProperty("source-location", "printf.scm:78");
            lambda$Fn5 = modulemethod1;
        }
    }

    public class frame3 extends ModuleBody
    {

        final ModuleMethod lambda$Fn7;
        Object sgn;
        frame2 staticLink;

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
            frame4 frame4_1 = new frame4();
            frame4_1.staticLink = this;
            frame4_1.idigs = obj1;
            ModuleMethod modulemethod = frame4_1.Fn8;
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

        public frame3()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 9, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:84");
            lambda$Fn7 = modulemethod;
        }
    }

    public class frame4 extends ModuleBody
    {

        Object idigs;
        final ModuleMethod lambda$Fn8;
        final ModuleMethod lambda$Fn9;
        frame3 staticLink;

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
            frame5 frame5_1 = new frame5();
            frame5_1.staticLink = this;
            frame5_1.fdigs = obj1;
            ModuleMethod modulemethod = frame5_1.Fn10;
            frame frame14 = staticLink.staticLink.staticLink;
            frame6 frame6_1 = new frame6();
            frame6_1.staticLink = frame14;
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
                return staticLink.staticLink.staticLink.lambda2sign(AddOp.$Pl.apply2(obj, printf.Lit7), frame6_1.Fn11);
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

        public frame4()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 7, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:90");
            lambda$Fn9 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 8, null, 4097);
            modulemethod1.setProperty("source-location", "printf.scm:87");
            lambda$Fn8 = modulemethod1;
        }
    }

    public class frame5 extends ModuleBody
    {

        Object fdigs;
        final ModuleMethod lambda$Fn10;
        frame4 staticLink;

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

        public frame5()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 6, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:92");
            lambda$Fn10 = modulemethod;
        }
    }

    public class frame6 extends ModuleBody
    {

        Object cont;
        final ModuleMethod lambda$Fn11;
        frame staticLink;

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
            frame7 frame7_1 = new frame7();
            frame7_1.staticLink = this;
            frame7_1.sgn = obj1;
            return staticLink.lambda3digits(obj, frame7_1.Fn12);
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

        public frame6()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 5, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:67");
            lambda$Fn11 = modulemethod;
        }
    }

    public class frame7 extends ModuleBody
    {

        final ModuleMethod lambda$Fn12;
        Object sgn;
        frame6 staticLink;

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

        public frame7()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 4, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:69");
            lambda$Fn12 = modulemethod;
        }
    }

    public class frame8 extends ModuleBody
    {

        CharSequence str;

        public Object lambda17dig(Object obj)
        {
            CharSequence charsequence = str;
            int i;
            char c;
            try
            {
                i = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-ref", 2, obj);
            }
            c = strings.stringRef(charsequence, i);
            if (unicode.isCharNumeric(Char.make(c)))
            {
                Object aobj[] = new Object[1];
                aobj[0] = Char.make(c);
                return numbers.string$To$Number(strings.$make$string$(aobj));
            } else
            {
                return printf.Lit1;
            }
        }

        public frame8()
        {
        }
    }

    public class frame9 extends ModuleBody
    {

        LList args;
        Object fc;
        int fl;
        Object format$Mnstring;
        Object out;
        Object pos;

        public Object lambda18mustAdvance()
        {
            pos = AddOp.$Pl.apply2(printf.Lit7, pos);
            if (Scheme.numGEq.apply2(pos, Integer.valueOf(fl)) != Boolean.FALSE)
            {
                return lambda20incomplete();
            }
            Object obj = format$Mnstring;
            CharSequence charsequence;
            Object obj1;
            int i;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-ref", 1, obj);
            }
            obj1 = pos;
            try
            {
                i = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 2, obj1);
            }
            fc = Char.make(strings.stringRef(charsequence, i));
            return Values.empty;
        }

        public boolean lambda19isEndOfFormat()
        {
            return ((Boolean)Scheme.numGEq.apply2(pos, Integer.valueOf(fl))).booleanValue();
        }

        public Object lambda20incomplete()
        {
            SimpleSymbol simplesymbol = printf.Lit34;
            Object aobj[] = new Object[2];
            aobj[0] = "conversion specification incomplete";
            aobj[1] = format$Mnstring;
            return misc.error$V(simplesymbol, aobj);
        }

        public Object lambda21out$St(Object obj)
        {
            Object obj1;
label0:
            {
                if (strings.isString(obj))
                {
                    return Scheme.applyToArgs.apply2(out, obj);
                }
                boolean flag;
                while (!(flag = lists.isNull(obj))) 
                {
                    obj1 = Scheme.applyToArgs.apply2(out, lists.car.apply1(obj));
                    if (obj1 == Boolean.FALSE)
                    {
                        break label0;
                    }
                    obj = lists.cdr.apply1(obj);
                }
                if (flag)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            return obj1;
        }

        public frame9()
        {
        }
    }


    public static final printf $instance;
    static final IntNum Lit0 = IntNum.make(-15);
    static final IntNum Lit1 = IntNum.make(0);
    static final PairWithPosition Lit10;
    static final Char Lit11 = Char.make(46);
    static final Char Lit12;
    static final Char Lit13;
    static final IntNum Lit14 = IntNum.make(2);
    static final IntNum Lit15 = IntNum.make(5);
    static final IntNum Lit16 = IntNum.make(9);
    static final IntNum Lit17 = IntNum.make(-1);
    static final Char Lit18 = Char.make(92);
    static final Char Lit19 = Char.make(110);
    static final PairWithPosition Lit2;
    static final Char Lit20 = Char.make(78);
    static final Char Lit21 = Char.make(10);
    static final Char Lit22 = Char.make(116);
    static final Char Lit23 = Char.make(84);
    static final Char Lit24 = Char.make(9);
    static final Char Lit25;
    static final Char Lit26;
    static final Char Lit27 = Char.make(12);
    static final Char Lit28 = Char.make(37);
    static final Char Lit29 = Char.make(32);
    static final Char Lit3;
    static final Char Lit30;
    static final Char Lit31;
    static final Char Lit32 = Char.make(104);
    static final PairWithPosition Lit33;
    static final SimpleSymbol Lit34;
    static final Char Lit35;
    static final Char Lit36 = Char.make(67);
    static final Char Lit37;
    static final Char Lit38;
    static final Char Lit39;
    static final Char Lit4 = Char.make(64);
    static final Char Lit40 = Char.make(65);
    static final Char Lit41;
    static final Char Lit42 = Char.make(73);
    static final Char Lit43;
    static final Char Lit44 = Char.make(85);
    static final IntNum Lit45 = IntNum.make(10);
    static final Char Lit46;
    static final Char Lit47 = Char.make(79);
    static final IntNum Lit48 = IntNum.make(8);
    static final Char Lit49;
    static final Char Lit5;
    static final IntNum Lit50 = IntNum.make(16);
    static final Char Lit51 = Char.make(88);
    static final Char Lit52;
    static final Char Lit53 = Char.make(66);
    static final Char Lit54;
    static final Char Lit55;
    static final Char Lit56 = Char.make(71);
    static final Char Lit57;
    static final Char Lit58 = Char.make(75);
    static final IntNum Lit59 = IntNum.make(6);
    static final Char Lit6;
    static final IntNum Lit60 = IntNum.make(-10);
    static final IntNum Lit61 = IntNum.make(3);
    static final FVector Lit62 = FVector.make(new Object[] {
        "y", "z", "a", "f", "p", "n", "u", "m", "", "k", 
        "M", "G", "T", "P", "E", "Z", "Y"
    });
    static final PairWithPosition Lit63;
    static final SimpleSymbol Lit64 = (SimpleSymbol)(new SimpleSymbol("format-real")).readResolve();
    static final Char Lit65 = Char.make(63);
    static final Char Lit66 = Char.make(42);
    static final SimpleSymbol Lit67 = (SimpleSymbol)(new SimpleSymbol("pad")).readResolve();
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final IntNum Lit7 = IntNum.make(1);
    static final SimpleSymbol Lit70;
    static final SimpleSymbol Lit71;
    static final SimpleSymbol Lit72;
    static final Char Lit8 = Char.make(35);
    static final Char Lit9 = Char.make(48);
    public static final ModuleMethod fprintf;
    public static final ModuleMethod printf;
    public static final ModuleMethod sprintf;
    public static final boolean stdio$Clhex$Mnupper$Mncase$Qu;
    public static final ModuleMethod stdio$Cliprintf;
    public static final ModuleMethod stdio$Clparse$Mnfloat;
    public static final ModuleMethod stdio$Clround$Mnstring;

    public printf()
    {
        ModuleInfo.register(this);
    }

    public static Object fprintf$V(Object obj, Object obj1, Object aobj[])
    {
        frame12 frame12_1 = new frame12();
        frame12_1.port = obj;
        LList llist = LList.makeList(aobj, 0);
        frame12_1.cnt = Lit1;
        Scheme.apply.apply4(stdio$Cliprintf, frame12_1.Fn18, obj1, llist);
        return frame12_1.cnt;
    }

    public static Object printf$V(Object obj, Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        return Scheme.apply.apply4(fprintf, ports.current$Mnoutput$Mnport.apply0(), obj, llist);
    }

    public static Object sprintf$V(Object obj, Object obj1, Object aobj[])
    {
        frame13 frame13_1 = new frame13();
        frame13_1.str = obj;
        LList llist = LList.makeList(aobj, 0);
        frame13_1.cnt = Lit1;
        Object obj2;
        Object obj3;
        CharSequence charsequence;
        Object obj4;
        Object obj5;
        if (strings.isString(frame13_1.str))
        {
            obj2 = frame13_1.str;
        } else
        if (numbers.isNumber(frame13_1.str))
        {
            Object obj6 = frame13_1.str;
            SimpleSymbol simplesymbol;
            Object aobj1[];
            CharSequence charsequence1;
            int i;
            int j;
            try
            {
                j = ((Number)obj6).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "make-string", 1, obj6);
            }
            obj2 = strings.makeString(j);
        } else
        if (frame13_1.str == Boolean.FALSE)
        {
            obj2 = strings.makeString(100);
        } else
        {
            simplesymbol = Lit68;
            aobj1 = new Object[2];
            aobj1[0] = "first argument not understood";
            aobj1[1] = frame13_1.str;
            obj2 = misc.error$V(simplesymbol, aobj1);
        }
        frame13_1.s = obj2;
        obj3 = frame13_1.s;
        try
        {
            charsequence = (CharSequence)obj3;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj3);
        }
        frame13_1.end = Integer.valueOf(strings.stringLength(charsequence));
        Scheme.apply.apply4(stdio$Cliprintf, frame13_1.Fn19, obj1, llist);
        if (strings.isString(frame13_1.str))
        {
            return frame13_1.cnt;
        }
        if (Scheme.isEqv.apply2(frame13_1.end, frame13_1.cnt) != Boolean.FALSE)
        {
            return frame13_1.s;
        }
        obj4 = frame13_1.s;
        try
        {
            charsequence1 = (CharSequence)obj4;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "substring", 1, obj4);
        }
        obj5 = frame13_1.cnt;
        try
        {
            i = ((Number)obj5).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "substring", 3, obj5);
        }
        return strings.substring(charsequence1, 0, i);
    }

    public static Object stdio$ClIprintf$V(Object obj, Object obj1, Object aobj[])
    {
        Object obj31;
        Object obj33;
        Object obj59;
        Object obj60;
        frame9 frame9_1 = new frame9();
        frame9_1.out = obj;
        frame9_1.Mnstring = obj1;
        frame9_1.args = LList.makeList(aobj, 0);
        if (IsEqual.apply("", frame9_1.Mnstring))
        {
            break MISSING_BLOCK_LABEL_3761;
        }
        IntNum intnum = Lit17;
        Object obj2 = frame9_1.Mnstring;
        CharSequence charsequence;
        int i;
        Object obj3;
        CharSequence charsequence1;
        Object obj4;
        frame10 frame10_1;
        CharSequence charsequence2;
        int j;
        boolean flag;
        Object obj7;
        Boolean boolean1;
        Boolean boolean2;
        Boolean boolean3;
        Boolean boolean4;
        Boolean boolean5;
        IntNum intnum1;
        Object obj8;
        gnu.math.RealNum realnum;
        Object obj10;
        Object obj11;
        Object obj12;
        Object obj13;
        Object obj14;
        Object obj15;
        Object obj16;
        Object obj17;
        Object obj18;
        Object obj19;
        Object obj20;
        Object obj21;
        Object obj22;
        Object obj23;
        Object obj24;
        Object obj25;
        Object obj26;
        Object obj27;
        Object obj28;
        Object obj29;
        Object obj30;
        Object obj32;
        frame11 frame11_1;
        gnu.math.RealNum realnum1;
        Number number;
        boolean flag1;
        Object obj35;
        Object obj36;
        Symbol symbol;
        Number number1;
        Char char1;
        Object obj38;
        IntNum intnum2;
        Object obj39;
        Object obj40;
        IntNum intnum3;
        Object obj41;
        Object obj42;
        Boolean boolean6;
        Boolean boolean7;
        Object obj43;
        gnu.math.RealNum realnum2;
        ModuleMethod modulemethod;
        Object obj45;
        Boolean boolean8;
        int k;
        gnu.kawa.functions.NumberCompare numbercompare;
        Object obj47;
        CharSequence charsequence3;
        gnu.kawa.functions.ApplyToArgs applytoargs;
        Object obj49;
        AddOp addop;
        Object obj50;
        CharSequence charsequence4;
        int l;
        gnu.kawa.functions.ApplyToArgs applytoargs1;
        Object obj53;
        int i1;
        gnu.kawa.functions.ApplyToArgs applytoargs2;
        Object obj55;
        int j1;
        gnu.math.RealNum realnum3;
        gnu.math.RealNum realnum4;
        gnu.math.RealNum realnum5;
        boolean flag2;
        gnu.kawa.functions.NumberCompare numbercompare1;
        Object obj61;
        CharSequence charsequence5;
        CharSequence charsequence6;
        int k1;
        gnu.kawa.functions.NumberCompare numbercompare2;
        Object obj63;
        CharSequence charsequence7;
        AddOp addop1;
        Object obj64;
        CharSequence charsequence8;
        int l1;
        Char char2;
        AddOp addop2;
        Object obj66;
        CharSequence charsequence9;
        int i2;
        Symbol symbol1;
        gnu.kawa.functions.ApplyToArgs applytoargs3;
        Object obj69;
        Object obj70;
        Object aobj1[];
        Char char3;
        SimpleSymbol simplesymbol;
        Object aobj2[];
        Object obj72;
        Object obj73;
        Object obj74;
        Object obj75;
        Object obj76;
        Boolean boolean9;
        try
        {
            charsequence = (CharSequence)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj2);
        }
        i = strings.stringLength(charsequence);
        obj3 = frame9_1.Mnstring;
        try
        {
            charsequence1 = (CharSequence)obj3;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 1, obj3);
        }
        frame9_1.fc = Char.make(strings.stringRef(charsequence1, 0));
        frame9_1.fl = i;
        frame9_1.pos = intnum;
        obj4 = frame9_1.args;
        frame10_1 = new frame10();
        frame10_1.staticLink = frame9_1;
        frame10_1.args = obj4;
        frame9_1.pos = AddOp.$Pl.apply2(Lit7, frame9_1.pos);
        if (Scheme.numGEq.apply2(frame9_1.pos, Integer.valueOf(frame9_1.fl)) != Boolean.FALSE)
        {
            frame9_1.fc = Boolean.FALSE;
        } else
        {
            Object obj5 = frame9_1.Mnstring;
            Object obj6;
            try
            {
                charsequence2 = (CharSequence)obj5;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 1, obj5);
            }
            obj6 = frame9_1.pos;
            try
            {
                j = ((Number)obj6).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "string-ref", 2, obj6);
            }
            frame9_1.fc = Char.make(strings.stringRef(charsequence2, j));
        }
        flag = frame9_1.lambda19isEndOfFormat();
        if (!flag) goto _L2; else goto _L1
_L1:
        if (flag)
        {
            boolean9 = Boolean.TRUE;
        } else
        {
            boolean9 = Boolean.FALSE;
        }
        obj7 = boolean9;
_L8:
        return obj7;
_L2:
label0:
        {
            if (Scheme.isEqv.apply2(Lit18, frame9_1.fc) == Boolean.FALSE)
            {
                break label0;
            }
            frame9_1.lambda18mustAdvance();
            obj72 = frame9_1.fc;
            obj73 = Scheme.isEqv.apply2(obj72, Lit19);
            if (obj73 == Boolean.FALSE ? Scheme.isEqv.apply2(obj72, Lit20) != Boolean.FALSE : obj73 != Boolean.FALSE)
            {
                obj76 = Scheme.applyToArgs.apply2(frame9_1.out, Lit21);
            } else
            {
                obj74 = Scheme.isEqv.apply2(obj72, Lit22);
                if (obj74 == Boolean.FALSE ? Scheme.isEqv.apply2(obj72, Lit23) != Boolean.FALSE : obj74 != Boolean.FALSE)
                {
                    obj76 = Scheme.applyToArgs.apply2(frame9_1.out, Lit24);
                } else
                {
                    obj75 = Scheme.isEqv.apply2(obj72, Lit25);
                    if (obj75 == Boolean.FALSE ? Scheme.isEqv.apply2(obj72, Lit26) != Boolean.FALSE : obj75 != Boolean.FALSE)
                    {
                        obj76 = Scheme.applyToArgs.apply2(frame9_1.out, Lit27);
                    } else
                    if (Scheme.isEqv.apply2(obj72, Lit21) != Boolean.FALSE)
                    {
                        obj76 = Boolean.TRUE;
                    } else
                    {
                        obj76 = Scheme.applyToArgs.apply2(frame9_1.out, frame9_1.fc);
                    }
                }
            }
            if (obj76 != Boolean.FALSE)
            {
                obj4 = frame10_1.args;
            } else
            {
                return obj76;
            }
        }
        break MISSING_BLOCK_LABEL_108;
        if (Scheme.isEqv.apply2(Lit28, frame9_1.fc) == Boolean.FALSE)
        {
            break MISSING_BLOCK_LABEL_3727;
        }
        frame9_1.lambda18mustAdvance();
        boolean1 = Boolean.FALSE;
        boolean2 = Boolean.FALSE;
        boolean3 = Boolean.FALSE;
        boolean4 = Boolean.FALSE;
        boolean5 = Boolean.FALSE;
        intnum1 = Lit1;
        frame10_1.precision = Lit17;
        frame10_1.width = intnum1;
        frame10_1.Mn0s = boolean5;
        frame10_1.Mnform = boolean4;
        frame10_1.blank = boolean3;
        frame10_1.signed = boolean2;
        frame10_1.Mnadjust = boolean1;
        frame10_1.pad = frame10_1.pad;
_L4:
        obj8 = frame9_1.fc;
        if (Scheme.isEqv.apply2(obj8, Lit5) == Boolean.FALSE)
        {
            break; /* Loop/switch isn't completed */
        }
        frame10_1.Mnadjust = Boolean.TRUE;
_L5:
        frame9_1.lambda18mustAdvance();
        if (true) goto _L4; else goto _L3
_L3:
        if (Scheme.isEqv.apply2(obj8, Lit6) != Boolean.FALSE)
        {
            frame10_1.signed = Boolean.TRUE;
        } else
        if (Scheme.isEqv.apply2(obj8, Lit29) != Boolean.FALSE)
        {
            frame10_1.blank = Boolean.TRUE;
        } else
        if (Scheme.isEqv.apply2(obj8, Lit8) != Boolean.FALSE)
        {
            frame10_1.Mnform = Boolean.TRUE;
        } else
        {
label1:
            {
                if (Scheme.isEqv.apply2(obj8, Lit9) == Boolean.FALSE)
                {
                    break label1;
                }
                frame10_1.Mn0s = Boolean.TRUE;
            }
        }
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
        if (frame10_1.Mnadjust != Boolean.FALSE)
        {
            frame10_1.Mn0s = Boolean.FALSE;
        }
        if (frame10_1.signed != Boolean.FALSE)
        {
            frame10_1.blank = Boolean.FALSE;
        }
        frame10_1.width = frame10_1.lambda22readFormatNumber();
        Object obj9 = frame10_1.width;
        try
        {
            realnum = LangObjType.coerceRealNum(obj9);
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "negative?", 1, obj9);
        }
        if (numbers.isNegative(realnum))
        {
            frame10_1.Mnadjust = Boolean.TRUE;
            frame10_1.width = AddOp.$Mn.apply1(frame10_1.width);
        }
        if (Scheme.isEqv.apply2(Lit11, frame9_1.fc) != Boolean.FALSE)
        {
            frame9_1.lambda18mustAdvance();
            frame10_1.precision = frame10_1.lambda22readFormatNumber();
        }
        obj10 = frame9_1.fc;
        obj11 = Scheme.isEqv.apply2(obj10, Lit30);
        if (obj11 == Boolean.FALSE ? (obj12 = Scheme.isEqv.apply2(obj10, Lit31)) == Boolean.FALSE ? Scheme.isEqv.apply2(obj10, Lit32) != Boolean.FALSE : obj12 != Boolean.FALSE : obj11 != Boolean.FALSE)
        {
            frame9_1.lambda18mustAdvance();
        }
        if (lists.isNull(frame10_1.args))
        {
            Object obj71 = frame9_1.fc;
            try
            {
                char3 = (Char)obj71;
            }
            catch (ClassCastException classcastexception29)
            {
                throw new WrongType(classcastexception29, "char-downcase", 1, obj71);
            }
            if (lists.memv(unicode.charDowncase(char3), Lit33) != Boolean.FALSE)
            {
                simplesymbol = Lit34;
                aobj2 = new Object[3];
                aobj2[0] = "wrong number of arguments";
                aobj2[1] = Integer.valueOf(lists.length(frame9_1.args));
                aobj2[2] = frame9_1.Mnstring;
                misc.error$V(simplesymbol, aobj2);
            }
        }
        obj13 = frame9_1.fc;
        obj14 = Scheme.isEqv.apply2(obj13, Lit35);
        if (obj14 == Boolean.FALSE ? Scheme.isEqv.apply2(obj13, Lit36) != Boolean.FALSE : obj14 != Boolean.FALSE)
        {
            applytoargs3 = Scheme.applyToArgs;
            obj69 = frame9_1.out;
            obj70 = lists.car.apply1(frame10_1.args);
            if (obj70 instanceof Object[])
            {
                aobj1 = (Object[])obj70;
            } else
            {
                aobj1 = (new Object[] {
                    obj70
                });
            }
            obj7 = applytoargs3.apply2(obj69, strings.$make$string$(aobj1));
        } else
        {
            break MISSING_BLOCK_LABEL_1246;
        }
        if (obj7 == Boolean.FALSE) goto _L8; else goto _L7
_L7:
        obj4 = lists.cdr.apply1(frame10_1.args);
        break MISSING_BLOCK_LABEL_108;
        obj15 = Scheme.isEqv.apply2(obj13, Lit37);
        if (obj15 == Boolean.FALSE ? Scheme.isEqv.apply2(obj13, Lit38) != Boolean.FALSE : obj15 != Boolean.FALSE) goto _L10; else goto _L9
_L10:
        if (misc.isSymbol(lists.car.apply1(frame10_1.args)))
        {
            Object obj68 = lists.car.apply1(frame10_1.args);
            try
            {
                symbol1 = (Symbol)obj68;
            }
            catch (ClassCastException classcastexception28)
            {
                throw new WrongType(classcastexception28, "symbol->string", 1, obj68);
            }
            obj59 = misc.symbol$To$String(symbol1);
        } else
        if (lists.car.apply1(frame10_1.args) == Boolean.FALSE)
        {
            obj59 = "(NULL)";
        } else
        {
            obj59 = lists.car.apply1(frame10_1.args);
        }
        obj60 = frame10_1.precision;
        try
        {
            realnum5 = LangObjType.coerceRealNum(obj60);
        }
        catch (ClassCastException classcastexception19)
        {
            throw new WrongType(classcastexception19, "negative?", 1, obj60);
        }
        flag2 = numbers.isNegative(realnum5);
        if (!flag2) goto _L12; else goto _L11
_L11:
        if (flag2) goto _L14; else goto _L13
_L13:
        ClassCastException classcastexception20;
        Object obj62;
        try
        {
            charsequence6 = (CharSequence)obj59;
        }
        catch (ClassCastException classcastexception21)
        {
            throw new WrongType(classcastexception21, "substring", 1, obj59);
        }
        obj62 = frame10_1.precision;
        try
        {
            k1 = ((Number)obj62).intValue();
        }
        catch (ClassCastException classcastexception22)
        {
            throw new WrongType(classcastexception22, "substring", 3, obj62);
        }
        obj59 = strings.substring(charsequence6, 0, k1);
_L14:
        numbercompare2 = Scheme.numLEq;
        obj63 = frame10_1.width;
        try
        {
            charsequence7 = (CharSequence)obj59;
        }
        catch (ClassCastException classcastexception23)
        {
            throw new WrongType(classcastexception23, "string-length", 1, obj59);
        }
        if (numbercompare2.apply2(obj63, Integer.valueOf(strings.stringLength(charsequence7))) == Boolean.FALSE)
        {
            if (frame10_1.Mnadjust != Boolean.FALSE)
            {
                addop2 = AddOp.$Mn;
                obj66 = frame10_1.width;
                Object obj67;
                try
                {
                    charsequence9 = (CharSequence)obj59;
                }
                catch (ClassCastException classcastexception26)
                {
                    throw new WrongType(classcastexception26, "string-length", 1, obj59);
                }
                obj67 = addop2.apply2(obj66, Integer.valueOf(strings.stringLength(charsequence9)));
                try
                {
                    i2 = ((Number)obj67).intValue();
                }
                catch (ClassCastException classcastexception27)
                {
                    throw new WrongType(classcastexception27, "make-string", 1, obj67);
                }
                obj59 = LList.list2(obj59, strings.makeString(i2, Lit29));
            } else
            {
                addop1 = AddOp.$Mn;
                obj64 = frame10_1.width;
                Object obj65;
                try
                {
                    charsequence8 = (CharSequence)obj59;
                }
                catch (ClassCastException classcastexception24)
                {
                    throw new WrongType(classcastexception24, "string-length", 1, obj59);
                }
                obj65 = addop1.apply2(obj64, Integer.valueOf(strings.stringLength(charsequence8)));
                try
                {
                    l1 = ((Number)obj65).intValue();
                }
                catch (ClassCastException classcastexception25)
                {
                    throw new WrongType(classcastexception25, "make-string", 1, obj65);
                }
                if (frame10_1.Mn0s != Boolean.FALSE)
                {
                    char2 = Lit9;
                } else
                {
                    char2 = Lit29;
                }
                obj59 = LList.list2(strings.makeString(l1, char2), obj59);
            }
        }
        obj7 = frame9_1.St(obj59);
        if (obj7 != Boolean.FALSE)
        {
            obj4 = lists.cdr.apply1(frame10_1.args);
            break MISSING_BLOCK_LABEL_108;
        }
          goto _L8
_L9:
        obj16 = Scheme.isEqv.apply2(obj13, Lit39);
        if (obj16 == Boolean.FALSE ? Scheme.isEqv.apply2(obj13, Lit40) != Boolean.FALSE : obj16 != Boolean.FALSE) goto _L16; else goto _L15
_L16:
        frame10_1.pr = frame10_1.precision;
        frame10_1.os = "";
        obj42 = lists.car.apply1(frame10_1.args);
        if (frame10_1.Mnform != Boolean.FALSE)
        {
            boolean6 = Boolean.FALSE;
        } else
        {
            boolean6 = Boolean.TRUE;
        }
        boolean7 = Boolean.FALSE;
        obj43 = frame10_1.Mnadjust;
        if (obj43 == Boolean.FALSE) goto _L18; else goto _L17
_L17:
        Object obj58 = frame10_1.pr;
        try
        {
            realnum4 = LangObjType.coerceRealNum(obj58);
        }
        catch (ClassCastException classcastexception18)
        {
            throw new WrongType(classcastexception18, "negative?", 1, obj58);
        }
        if (!numbers.isNegative(realnum4)) goto _L20; else goto _L19
_L19:
        frame10_1.pr = Lit1;
        modulemethod = frame10_1.Fn13;
_L26:
        genwrite.genericWrite(obj42, boolean6, boolean7, modulemethod);
        obj45 = frame10_1.Mnadjust;
        if (obj45 == Boolean.FALSE) goto _L22; else goto _L21
_L21:
        Object obj57 = frame10_1.precision;
        Object obj44;
        ClassCastException classcastexception10;
        try
        {
            realnum3 = LangObjType.coerceRealNum(obj57);
        }
        catch (ClassCastException classcastexception17)
        {
            throw new WrongType(classcastexception17, "negative?", 1, obj57);
        }
        if (!numbers.isNegative(realnum3)) goto _L24; else goto _L23
_L23:
        if (Scheme.numGrt.apply2(frame10_1.width, frame10_1.pr) != Boolean.FALSE)
        {
            applytoargs2 = Scheme.applyToArgs;
            obj55 = frame9_1.out;
            Object obj56 = AddOp.$Mn.apply2(frame10_1.width, frame10_1.pr);
            try
            {
                j1 = ((Number)obj56).intValue();
            }
            catch (ClassCastException classcastexception16)
            {
                throw new WrongType(classcastexception16, "make-string", 1, obj56);
            }
            applytoargs2.apply2(obj55, strings.makeString(j1, Lit29));
        }
_L27:
        obj4 = lists.cdr.apply1(frame10_1.args);
        break MISSING_BLOCK_LABEL_108;
_L12:
        numbercompare1 = Scheme.numGEq;
        obj61 = frame10_1.precision;
        try
        {
            charsequence5 = (CharSequence)obj59;
        }
        // Misplaced declaration of an exception variable
        catch (ClassCastException classcastexception20)
        {
            throw new WrongType(classcastexception20, "string-length", 1, obj59);
        }
        if (numbercompare1.apply2(obj61, Integer.valueOf(strings.stringLength(charsequence5))) != Boolean.FALSE) goto _L14; else goto _L13
_L15:
label2:
        {
            obj17 = Scheme.isEqv.apply2(obj13, Lit12);
            if (obj17 == Boolean.FALSE ? (obj18 = Scheme.isEqv.apply2(obj13, Lit41)) == Boolean.FALSE ? (obj19 = Scheme.isEqv.apply2(obj13, Lit3)) == Boolean.FALSE ? (obj20 = Scheme.isEqv.apply2(obj13, Lit42)) == Boolean.FALSE ? (obj21 = Scheme.isEqv.apply2(obj13, Lit43)) == Boolean.FALSE ? Scheme.isEqv.apply2(obj13, Lit44) != Boolean.FALSE : obj21 != Boolean.FALSE : obj20 != Boolean.FALSE : obj19 != Boolean.FALSE : obj18 != Boolean.FALSE : obj17 != Boolean.FALSE)
            {
                break label2;
            } else
            {
                break MISSING_BLOCK_LABEL_2551;
            }
        }
        obj7 = frame9_1.St(frame10_1.lambda24integerConvert(lists.car.apply1(frame10_1.args), Lit45, Boolean.FALSE));
        if (obj7 == Boolean.FALSE) goto _L8; else goto _L25
_L25:
        obj4 = lists.cdr.apply1(frame10_1.args);
        break MISSING_BLOCK_LABEL_108;
_L18:
        if (obj43 != Boolean.FALSE) goto _L19; else goto _L20
_L20:
        if (frame10_1.Mnadjust != Boolean.FALSE)
        {
            modulemethod = frame10_1.Fn14;
        } else
        {
            obj44 = frame10_1.pr;
            try
            {
                realnum2 = LangObjType.coerceRealNum(obj44);
            }
            // Misplaced declaration of an exception variable
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "negative?", 1, obj44);
            }
            if (numbers.isNegative(realnum2))
            {
                frame10_1.pr = frame10_1.width;
                modulemethod = frame10_1.Fn15;
            } else
            {
                modulemethod = frame10_1.Fn16;
            }
        }
          goto _L26
_L22:
        if (obj45 != Boolean.FALSE) goto _L23; else goto _L24
_L24:
        if (frame10_1.Mnadjust != Boolean.FALSE)
        {
            if (Scheme.numGrt.apply2(frame10_1.width, AddOp.$Mn.apply2(frame10_1.precision, frame10_1.pr)) != Boolean.FALSE)
            {
                applytoargs1 = Scheme.applyToArgs;
                obj53 = frame9_1.out;
                Object obj54 = AddOp.$Mn.apply2(frame10_1.width, AddOp.$Mn.apply2(frame10_1.precision, frame10_1.pr));
                try
                {
                    i1 = ((Number)obj54).intValue();
                }
                catch (ClassCastException classcastexception15)
                {
                    throw new WrongType(classcastexception15, "make-string", 1, obj54);
                }
                applytoargs1.apply2(obj53, strings.makeString(i1, Lit29));
            }
        } else
        {
            Object obj46 = frame10_1.os;
            try
            {
                boolean8 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "x", -2, obj46);
            }
            if (obj46 != boolean8)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            if ((1 & k + 1) == 0)
            {
                numbercompare = Scheme.numLEq;
                obj47 = frame10_1.width;
                Object obj48 = frame10_1.os;
                try
                {
                    charsequence3 = (CharSequence)obj48;
                }
                catch (ClassCastException classcastexception12)
                {
                    throw new WrongType(classcastexception12, "string-length", 1, obj48);
                }
                if (numbercompare.apply2(obj47, Integer.valueOf(strings.stringLength(charsequence3))) != Boolean.FALSE)
                {
                    Scheme.applyToArgs.apply2(frame9_1.out, frame10_1.os);
                } else
                {
                    applytoargs = Scheme.applyToArgs;
                    obj49 = frame9_1.out;
                    addop = AddOp.$Mn;
                    obj50 = frame10_1.width;
                    Object obj51 = frame10_1.os;
                    Object obj52;
                    try
                    {
                        charsequence4 = (CharSequence)obj51;
                    }
                    catch (ClassCastException classcastexception13)
                    {
                        throw new WrongType(classcastexception13, "string-length", 1, obj51);
                    }
                    obj52 = addop.apply2(obj50, Integer.valueOf(strings.stringLength(charsequence4)));
                    try
                    {
                        l = ((Number)obj52).intValue();
                    }
                    catch (ClassCastException classcastexception14)
                    {
                        throw new WrongType(classcastexception14, "make-string", 1, obj52);
                    }
                    if (applytoargs.apply2(obj49, strings.makeString(l, Lit29)) != Boolean.FALSE)
                    {
                        Scheme.applyToArgs.apply2(frame9_1.out, frame10_1.os);
                    }
                }
            }
        }
          goto _L27
        obj22 = Scheme.isEqv.apply2(obj13, Lit46);
        if (obj22 == Boolean.FALSE ? Scheme.isEqv.apply2(obj13, Lit47) != Boolean.FALSE : obj22 != Boolean.FALSE)
        {
            obj7 = frame9_1.St(frame10_1.lambda24integerConvert(lists.car.apply1(frame10_1.args), Lit48, Boolean.FALSE));
        } else
        {
            break MISSING_BLOCK_LABEL_2765;
        }
        if (obj7 == Boolean.FALSE) goto _L8; else goto _L28
_L28:
        obj4 = lists.cdr.apply1(frame10_1.args);
        break MISSING_BLOCK_LABEL_108;
        if (Scheme.isEqv.apply2(obj13, Lit49) == Boolean.FALSE)
        {
            break MISSING_BLOCK_LABEL_2860;
        }
        obj40 = lists.car.apply1(frame10_1.args);
        intnum3 = Lit50;
        if (stdio$Clhex$Mnupper$Mncase$Qu)
        {
            obj41 = unicode.string$Mndowncase;
        } else
        {
            obj41 = Boolean.FALSE;
        }
        obj7 = frame9_1.St(frame10_1.lambda24integerConvert(obj40, intnum3, obj41));
        if (obj7 == Boolean.FALSE) goto _L8; else goto _L29
_L29:
        obj4 = lists.cdr.apply1(frame10_1.args);
        break MISSING_BLOCK_LABEL_108;
        if (Scheme.isEqv.apply2(obj13, Lit51) == Boolean.FALSE)
        {
            break MISSING_BLOCK_LABEL_2955;
        }
        obj38 = lists.car.apply1(frame10_1.args);
        intnum2 = Lit50;
        if (stdio$Clhex$Mnupper$Mncase$Qu)
        {
            obj39 = Boolean.FALSE;
        } else
        {
            obj39 = unicode.string$Mnupcase;
        }
        obj7 = frame9_1.St(frame10_1.lambda24integerConvert(obj38, intnum2, obj39));
        if (obj7 == Boolean.FALSE) goto _L8; else goto _L30
_L30:
        obj4 = lists.cdr.apply1(frame10_1.args);
        break MISSING_BLOCK_LABEL_108;
        obj23 = Scheme.isEqv.apply2(obj13, Lit52);
        if (obj23 == Boolean.FALSE ? Scheme.isEqv.apply2(obj13, Lit53) != Boolean.FALSE : obj23 != Boolean.FALSE)
        {
            obj7 = frame9_1.St(frame10_1.lambda24integerConvert(lists.car.apply1(frame10_1.args), Lit14, Boolean.FALSE));
        } else
        {
            break MISSING_BLOCK_LABEL_3053;
        }
        if (obj7 == Boolean.FALSE) goto _L8; else goto _L31
_L31:
        obj4 = lists.cdr.apply1(frame10_1.args);
        break MISSING_BLOCK_LABEL_108;
        if (Scheme.isEqv.apply2(obj13, Lit28) == Boolean.FALSE)
        {
            break MISSING_BLOCK_LABEL_3103;
        }
        obj7 = Scheme.applyToArgs.apply2(frame9_1.out, Lit28);
        if (obj7 == Boolean.FALSE) goto _L8; else goto _L32
_L32:
        obj4 = frame10_1.args;
        break MISSING_BLOCK_LABEL_108;
        obj24 = Scheme.isEqv.apply2(obj13, Lit25);
        if (obj24 == Boolean.FALSE ? (obj25 = Scheme.isEqv.apply2(obj13, Lit26)) == Boolean.FALSE ? (obj26 = Scheme.isEqv.apply2(obj13, Lit13)) == Boolean.FALSE ? (obj27 = Scheme.isEqv.apply2(obj13, Lit54)) == Boolean.FALSE ? (obj28 = Scheme.isEqv.apply2(obj13, Lit55)) == Boolean.FALSE ? (obj29 = Scheme.isEqv.apply2(obj13, Lit56)) == Boolean.FALSE ? (obj30 = Scheme.isEqv.apply2(obj13, Lit57)) == Boolean.FALSE ? Scheme.isEqv.apply2(obj13, Lit58) == Boolean.FALSE : obj30 == Boolean.FALSE : obj29 == Boolean.FALSE : obj28 == Boolean.FALSE : obj27 == Boolean.FALSE : obj26 == Boolean.FALSE : obj25 == Boolean.FALSE : obj24 == Boolean.FALSE) goto _L34; else goto _L33
_L33:
        obj31 = lists.car.apply1(frame10_1.args);
        obj32 = frame9_1.fc;
        frame11_1 = new frame11();
        frame11_1.staticLink = frame10_1;
        frame11_1.fc = obj32;
        obj33 = frame10_1.precision;
        try
        {
            realnum1 = LangObjType.coerceRealNum(obj33);
        }
        catch (ClassCastException classcastexception5)
        {
            throw new WrongType(classcastexception5, "negative?", 1, obj33);
        }
        if (!numbers.isNegative(realnum1)) goto _L36; else goto _L35
_L35:
        frame10_1.precision = Lit59;
_L40:
        if (numbers.isNumber(obj31))
        {
            Object obj34;
            ClassCastException classcastexception6;
            Object obj37;
            ClassCastException classcastexception9;
            try
            {
                number1 = (Number)obj31;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "exact->inexact", 1, obj31);
            }
            obj35 = numbers.number$To$String(numbers.exact$To$Inexact(number1));
        } else
        if (strings.isString(obj31))
        {
            obj35 = obj31;
        } else
        if (misc.isSymbol(obj31))
        {
            try
            {
                symbol = (Symbol)obj31;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "symbol->string", 1, obj31);
            }
            obj35 = misc.symbol$To$String(symbol);
        } else
        {
            obj35 = "???";
        }
        frame11_1.Mnreal = frame11_1.Mnreal;
        obj36 = stdio$ClParseFloat(obj35, frame11_1.Fn17);
        if (obj36 == Boolean.FALSE)
        {
            obj36 = frame10_1.V("???", new Object[0]);
        }
        obj7 = frame9_1.St(obj36);
        if (obj7 != Boolean.FALSE)
        {
            obj4 = lists.cdr.apply1(frame10_1.args);
            break MISSING_BLOCK_LABEL_108;
        }
          goto _L8
_L34:
        if (frame9_1.lambda19isEndOfFormat())
        {
            return frame9_1.lambda20incomplete();
        }
        obj7 = Scheme.applyToArgs.apply2(frame9_1.out, Lit28);
        continue; /* Loop/switch isn't completed */
_L36:
        obj34 = frame10_1.precision;
        try
        {
            number = (Number)obj34;
        }
        // Misplaced declaration of an exception variable
        catch (ClassCastException classcastexception6)
        {
            throw new WrongType(classcastexception6, "zero?", 1, obj34);
        }
        flag1 = numbers.isZero(number);
        if (!flag1) goto _L38; else goto _L37
_L37:
        obj37 = frame11_1.fc;
        try
        {
            char1 = (Char)obj37;
        }
        // Misplaced declaration of an exception variable
        catch (ClassCastException classcastexception9)
        {
            throw new WrongType(classcastexception9, "char-ci=?", 1, obj37);
        }
        if (!unicode.isCharCi$Eq(char1, Lit55)) goto _L40; else goto _L39
_L39:
        frame10_1.precision = Lit7;
        break; /* Loop/switch isn't completed */
_L38:
        if (!flag1) goto _L40; else goto _L39
        if (obj7 == Boolean.FALSE) goto _L8; else goto _L41
_L41:
        obj7 = Scheme.applyToArgs.apply2(frame9_1.out, frame9_1.fc);
        if (obj7 == Boolean.FALSE) goto _L8; else goto _L42
_L42:
        obj7 = Scheme.applyToArgs.apply2(frame9_1.out, Lit65);
        if (obj7 == Boolean.FALSE) goto _L8; else goto _L43
_L43:
        obj4 = frame10_1.args;
        break MISSING_BLOCK_LABEL_108;
        obj7 = Scheme.applyToArgs.apply2(frame9_1.out, frame9_1.fc);
        if (obj7 == Boolean.FALSE) goto _L8; else goto _L44
_L44:
        obj4 = frame10_1.args;
        break MISSING_BLOCK_LABEL_108;
        return Values.empty;
    }

    public static Object stdio$ClParseFloat(Object obj, Object obj1)
    {
        frame frame14 = new frame();
        frame14.str = obj;
        frame14.proc = obj1;
        Object obj2 = frame14.str;
        CharSequence charsequence;
        try
        {
            charsequence = (CharSequence)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj2);
        }
        frame14.n = strings.stringLength(charsequence);
        return frame14.lambda4real(Lit1, frame14.Fn1);
    }

    public static Object stdio$ClRoundString(CharSequence charsequence, Object obj, Object obj1)
    {
        frame8 frame8_1;
        int i;
        frame8_1 = new frame8();
        frame8_1.str = charsequence;
        i = -1 + strings.stringLength(frame8_1.str);
        if (Scheme.numLss.apply2(obj, Lit1) == Boolean.FALSE) goto _L2; else goto _L1
_L1:
        Object obj3 = "";
_L12:
        if (obj1 == Boolean.FALSE) goto _L4; else goto _L3
_L3:
        Object obj10;
        Object obj11;
        CharSequence charsequence1;
        Object obj2;
        ClassCastException classcastexception;
        int j;
        Object obj4;
        Object obj5;
        ClassCastException classcastexception1;
        boolean flag;
        Object obj6;
        ClassCastException classcastexception2;
        boolean flag1;
        Object obj7;
        Object obj8;
        ClassCastException classcastexception3;
        CharSeq charseq;
        ClassCastException classcastexception4;
        int k;
        ClassCastException classcastexception5;
        CharSeq charseq1;
        ClassCastException classcastexception6;
        int l;
        Object obj9;
        ClassCastException classcastexception7;
        Number number;
        CharSequence charsequence2;
        boolean flag2;
        Char char1;
        CharSequence charsequence3;
        int i1;
        CharSequence charsequence4;
        int j1;
        Object obj13;
        Object obj14;
        ClassCastException classcastexception14;
        Number number1;
        Object aobj[];
        AddOp addop;
        Object obj15;
        ClassCastException classcastexception15;
        Number number2;
        Object aobj1[];
        ClassCastException classcastexception16;
        int k1;
        Char char2;
        Object obj16;
        try
        {
            charsequence2 = (CharSequence)obj3;
        }
        catch (ClassCastException classcastexception8)
        {
            throw new WrongType(classcastexception8, "string-length", 1, obj3);
        }
        obj10 = Integer.valueOf(-1 + strings.stringLength(charsequence2));
        obj11 = Scheme.numLEq.apply2(obj10, obj1);
        try
        {
            flag2 = ((Boolean)obj11).booleanValue();
        }
        catch (ClassCastException classcastexception9)
        {
            throw new WrongType(classcastexception9, "x", -2, obj11);
        }
        if (!flag2) goto _L6; else goto _L5
_L5:
        if (!flag2) goto _L8; else goto _L7
_L7:
        ClassCastException classcastexception10;
        ClassCastException classcastexception11;
        Object obj12;
        try
        {
            charsequence4 = (CharSequence)obj3;
        }
        catch (ClassCastException classcastexception12)
        {
            throw new WrongType(classcastexception12, "substring", 1, obj3);
        }
        obj12 = AddOp.$Pl.apply2(obj10, Lit7);
        try
        {
            j1 = ((Number)obj12).intValue();
        }
        catch (ClassCastException classcastexception13)
        {
            throw new WrongType(classcastexception13, "substring", 3, obj12);
        }
        obj3 = strings.substring(charsequence4, 0, j1);
_L4:
        return obj3;
_L2:
        {
            if (Scheme.numEqu.apply2(Integer.valueOf(i), obj) != Boolean.FALSE)
            {
                obj3 = frame8_1.str;
                continue; /* Loop/switch isn't completed */
            }
            if (Scheme.numLss.apply2(Integer.valueOf(i), obj) != Boolean.FALSE)
            {
                aobj = new Object[2];
                aobj[0] = Lit1;
                addop = AddOp.$Mn;
                if (obj1 != Boolean.FALSE)
                {
                    obj = obj1;
                }
                aobj[1] = addop.apply2(obj, Integer.valueOf(i));
                obj15 = numbers.max(aobj);
                try
                {
                    number2 = (Number)obj15;
                }
                // Misplaced declaration of an exception variable
                catch (ClassCastException classcastexception15)
                {
                    throw new WrongType(classcastexception15, "zero?", 1, obj15);
                }
                if (numbers.isZero(number2))
                {
                    obj16 = frame8_1.str;
                } else
                {
                    aobj1 = new Object[2];
                    aobj1[0] = frame8_1.str;
                    try
                    {
                        k1 = ((Number)obj15).intValue();
                    }
                    // Misplaced declaration of an exception variable
                    catch (ClassCastException classcastexception16)
                    {
                        throw new WrongType(classcastexception16, "make-string", 1, obj15);
                    }
                    if (unicode.isCharNumeric(Char.make(strings.stringRef(frame8_1.str, i))))
                    {
                        char2 = Lit9;
                    } else
                    {
                        char2 = Lit8;
                    }
                    aobj1[1] = strings.makeString(k1, char2);
                    obj16 = strings.stringAppend(aobj1);
                }
                obj3 = obj16;
                continue; /* Loop/switch isn't completed */
            }
            charsequence1 = frame8_1.str;
            obj2 = AddOp.$Pl.apply2(obj, Lit7);
            try
            {
                j = ((Number)obj2).intValue();
            }
            // Misplaced declaration of an exception variable
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "substring", 3, obj2);
            }
            obj3 = strings.substring(charsequence1, 0, j);
            obj4 = frame8_1.lambda17dig(AddOp.$Pl.apply2(Lit7, obj));
            obj5 = Scheme.numGrt.apply2(obj4, Lit15);
            try
            {
                flag = ((Boolean)obj5).booleanValue();
            }
            // Misplaced declaration of an exception variable
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "x", -2, obj5);
            }
            if (flag)
            {
                if (!flag)
                {
                    continue; /* Loop/switch isn't completed */
                }
                break MISSING_BLOCK_LABEL_429;
            }
            obj6 = Scheme.numEqu.apply2(obj4, Lit15);
            try
            {
                flag1 = ((Boolean)obj6).booleanValue();
            }
            // Misplaced declaration of an exception variable
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "x", -2, obj6);
            }
            if (!flag1)
            {
                continue; /* Loop/switch isn't completed */
            }
            obj13 = AddOp.$Pl.apply2(Lit14, obj);
        }
        if (Scheme.numGrt.apply2(obj13, Integer.valueOf(i)) == Boolean.FALSE)
        {
            break MISSING_BLOCK_LABEL_591;
        }
        if ((1 & ((Number)frame8_1.lambda17dig(obj)).intValue()) == 0) goto _L10; else goto _L9
_L10:
        break; /* Loop/switch isn't completed */
        obj14 = frame8_1.lambda17dig(obj13);
        try
        {
            number1 = (Number)obj14;
        }
        // Misplaced declaration of an exception variable
        catch (ClassCastException classcastexception14)
        {
            throw new WrongType(classcastexception14, "zero?", 1, obj14);
        }
        if (!numbers.isZero(number1)) goto _L9; else goto _L11
_L11:
        obj13 = AddOp.$Pl.apply2(obj13, Lit7);
        break MISSING_BLOCK_LABEL_553;
        if (!flag1) goto _L12; else goto _L9
_L9:
        obj7 = obj;
        do
        {
            obj8 = frame8_1.lambda17dig(obj7);
            if (Scheme.numLss.apply2(obj8, Lit16) != Boolean.FALSE)
            {
                try
                {
                    charseq1 = (CharSeq)obj3;
                }
                // Misplaced declaration of an exception variable
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "string-set!", 1, obj3);
                }
                try
                {
                    l = ((Number)obj7).intValue();
                }
                // Misplaced declaration of an exception variable
                catch (ClassCastException classcastexception6)
                {
                    throw new WrongType(classcastexception6, "string-set!", 2, obj7);
                }
                obj9 = AddOp.$Pl.apply2(obj8, Lit7);
                try
                {
                    number = (Number)obj9;
                }
                // Misplaced declaration of an exception variable
                catch (ClassCastException classcastexception7)
                {
                    throw new WrongType(classcastexception7, "number->string", 1, obj9);
                }
                strings.stringSet$Ex(charseq1, l, strings.stringRef(numbers.number$To$String(number), 0));
                break; /* Loop/switch isn't completed */
            }
            try
            {
                charseq = (CharSeq)obj3;
            }
            // Misplaced declaration of an exception variable
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "string-set!", 1, obj3);
            }
            try
            {
                k = ((Number)obj7).intValue();
            }
            // Misplaced declaration of an exception variable
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "string-set!", 2, obj7);
            }
            strings.stringSet$Ex(charseq, k, '0');
            obj7 = AddOp.$Mn.apply2(obj7, Lit7);
        } while (true);
_L6:
        char1 = Lit9;
        try
        {
            charsequence3 = (CharSequence)obj3;
        }
        // Misplaced declaration of an exception variable
        catch (ClassCastException classcastexception10)
        {
            throw new WrongType(classcastexception10, "string-ref", 1, obj3);
        }
        i1 = ((Number)obj10).intValue();
        if (!characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence3, i1)))) goto _L7; else goto _L8
_L8:
        obj10 = AddOp.$Mn.apply2(obj10, Lit7);
        break MISSING_BLOCK_LABEL_70;
        classcastexception11;
        throw new WrongType(classcastexception11, "string-ref", 2, obj10);
        if (true) goto _L12; else goto _L13
_L13:
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 22)
        {
            return stdio$ClParseFloat(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
    {
        if (modulemethod.selector == 23)
        {
            CharSequence charsequence;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "stdio:round-string", 1, obj);
            }
            return stdio$ClRoundString(charsequence, obj1, obj2);
        } else
        {
            return super.apply3(modulemethod, obj, obj1, obj2);
        }
    }

    public Object applyN(ModuleMethod modulemethod, Object aobj[])
    {
        Object obj;
        Object obj1;
        int i;
        Object aobj1[];
        switch (modulemethod.selector)
        {
        default:
            return super.applyN(modulemethod, aobj);

        case 24: // '\030'
            Object obj5 = aobj[0];
            Object obj6 = aobj[1];
            int l = -2 + aobj.length;
            Object aobj4[] = new Object[l];
            do
            {
                if (--l < 0)
                {
                    return stdio$ClIprintf$V(obj5, obj6, aobj4);
                }
                aobj4[l] = aobj[l + 2];
            } while (true);

        case 25: // '\031'
            Object obj3 = aobj[0];
            Object obj4 = aobj[1];
            int k = -2 + aobj.length;
            Object aobj3[] = new Object[k];
            do
            {
                if (--k < 0)
                {
                    return fprintf$V(obj3, obj4, aobj3);
                }
                aobj3[k] = aobj[k + 2];
            } while (true);

        case 26: // '\032'
            Object obj2 = aobj[0];
            int j = -1 + aobj.length;
            Object aobj2[] = new Object[j];
            do
            {
                if (--j < 0)
                {
                    return printf$V(obj2, aobj2);
                }
                aobj2[j] = aobj[j + 1];
            } while (true);

        case 27: // '\033'
            obj = aobj[0];
            obj1 = aobj[1];
            i = -2 + aobj.length;
            aobj1 = new Object[i];
            break;
        }
        do
        {
            if (--i < 0)
            {
                return sprintf$V(obj, obj1, aobj1);
            }
            aobj1[i] = aobj[i + 2];
        } while (true);
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        if (modulemethod.selector == 22)
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

    public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        if (modulemethod.selector == 23)
        {
            if (obj instanceof CharSequence)
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            } else
            {
                return 0xfff40001;
            }
        } else
        {
            return super.match3(modulemethod, obj, obj1, obj2, callcontext);
        }
    }

    public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.matchN(modulemethod, aobj, callcontext);

        case 27: // '\033'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 26: // '\032'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 25: // '\031'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 24: // '\030'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
        stdio$Clhex$Mnupper$Mncase$Qu = strings.isString$Eq("-F", numbers.number$To$String(Lit0, 16));
    }

    static 
    {
        Lit72 = (SimpleSymbol)(new SimpleSymbol("fprintf")).readResolve();
        Lit71 = (SimpleSymbol)(new SimpleSymbol("stdio:iprintf")).readResolve();
        Lit70 = (SimpleSymbol)(new SimpleSymbol("stdio:round-string")).readResolve();
        Lit69 = (SimpleSymbol)(new SimpleSymbol("stdio:parse-float")).readResolve();
        Lit68 = (SimpleSymbol)(new SimpleSymbol("sprintf")).readResolve();
        Lit63 = PairWithPosition.make("i", LList.Empty, "printf.scm", 0x18f00b);
        Lit57 = Char.make(107);
        Lit55 = Char.make(103);
        Lit54 = Char.make(69);
        Lit52 = Char.make(98);
        Lit49 = Char.make(120);
        Lit46 = Char.make(111);
        Lit43 = Char.make(117);
        Lit41 = Char.make(68);
        Lit39 = Char.make(97);
        Lit38 = Char.make(83);
        Lit37 = Char.make(115);
        Lit35 = Char.make(99);
        Lit34 = (SimpleSymbol)(new SimpleSymbol("printf")).readResolve();
        Char char1 = Lit35;
        Char char2 = Lit37;
        Char char3 = Lit39;
        Char char4 = Char.make(100);
        Lit12 = char4;
        Char char5 = Char.make(105);
        Lit3 = char5;
        Char char6 = Lit43;
        Char char7 = Lit46;
        Char char8 = Lit49;
        Char char9 = Lit52;
        Char char10 = Char.make(102);
        Lit25 = char10;
        Char char11 = Char.make(101);
        Lit13 = char11;
        Lit33 = PairWithPosition.make(char1, PairWithPosition.make(char2, PairWithPosition.make(char3, PairWithPosition.make(char4, PairWithPosition.make(char5, PairWithPosition.make(char6, PairWithPosition.make(char7, PairWithPosition.make(char8, PairWithPosition.make(char9, PairWithPosition.make(char10, PairWithPosition.make(char11, PairWithPosition.make(Lit55, PairWithPosition.make(Lit57, LList.Empty, "printf.scm", 0x1b3014), "printf.scm", 0x1b3010), "printf.scm", 0x1b300c), "printf.scm", 0x1b3008), "printf.scm", 0x1b2028), "printf.scm", 0x1b2024), "printf.scm", 0x1b2020), "printf.scm", 0x1b201c), "printf.scm", 0x1b2018), "printf.scm", 0x1b2014), "printf.scm", 0x1b2010), "printf.scm", 0x1b200c), "printf.scm", 0x1b2007);
        Lit31 = Char.make(76);
        Lit30 = Char.make(108);
        Lit26 = Char.make(70);
        Lit10 = PairWithPosition.make(Lit13, PairWithPosition.make(Lit37, PairWithPosition.make(Lit25, PairWithPosition.make(Lit12, PairWithPosition.make(Lit30, PairWithPosition.make(Lit54, PairWithPosition.make(Lit38, PairWithPosition.make(Lit26, PairWithPosition.make(Lit41, PairWithPosition.make(Lit31, LList.Empty, "printf.scm", 0x4102c), "printf.scm", 0x41028), "printf.scm", 0x41024), "printf.scm", 0x41020), "printf.scm", 0x4101c), "printf.scm", 0x41018), "printf.scm", 0x41014), "printf.scm", 0x41010), "printf.scm", 0x4100c), "printf.scm", 0x41007);
        Lit6 = Char.make(43);
        Lit5 = Char.make(45);
        Lit2 = PairWithPosition.make(Lit6, PairWithPosition.make(Lit5, LList.Empty, "printf.scm", 0x6d027), "printf.scm", 0x6d022);
        $instance = new printf();
        printf printf1 = $instance;
        stdio$Clparse$Mnfloat = new ModuleMethod(printf1, 22, Lit69, 8194);
        stdio$Clround$Mnstring = new ModuleMethod(printf1, 23, Lit70, 12291);
        stdio$Cliprintf = new ModuleMethod(printf1, 24, Lit71, -4094);
        fprintf = new ModuleMethod(printf1, 25, Lit72, -4094);
        printf = new ModuleMethod(printf1, 26, Lit34, -4095);
        sprintf = new ModuleMethod(printf1, 27, Lit68, -4094);
        $instance.run();
    }
}
