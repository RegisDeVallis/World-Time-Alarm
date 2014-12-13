// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.append;

public class pregexp extends ModuleBody
{
    public class frame extends ModuleBody
    {

        Object backrefs;
        Object case$Mnsensitive$Qu;
        Procedure identity;
        Object n;
        Object s;
        Object sn;
        Object start;

        public static Object lambda2identity(Object obj)
        {
            return obj;
        }

        static Boolean lambda4()
        {
            return Boolean.FALSE;
        }

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 15)
            {
                return lambda2identity(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        public Object lambda3sub(Object obj, Object obj1, Object obj2, Object obj3)
        {
            frame0 frame0_1 = new frame0();
            frame0_1.staticLink = this;
            frame0_1._fld1 = obj;
            frame0_1.i = obj1;
            frame0_1.sk = obj2;
            frame0_1.fk = obj3;
            if (Scheme.isEqv.apply2(frame0_1._fld1, pregexp.Lit10) != Boolean.FALSE)
            {
                if (Scheme.numEqu.apply2(frame0_1.i, start) != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply2(frame0_1.sk, frame0_1.i);
                } else
                {
                    return Scheme.applyToArgs.apply1(frame0_1.fk);
                }
            }
            if (Scheme.isEqv.apply2(frame0_1._fld1, pregexp.Lit12) != Boolean.FALSE)
            {
                if (Scheme.numGEq.apply2(frame0_1.i, n) != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply2(frame0_1.sk, frame0_1.i);
                } else
                {
                    return Scheme.applyToArgs.apply1(frame0_1.fk);
                }
            }
            if (Scheme.isEqv.apply2(frame0_1._fld1, pregexp.Lit23) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply2(frame0_1.sk, frame0_1.i);
            }
            if (Scheme.isEqv.apply2(frame0_1._fld1, pregexp.Lit26) != Boolean.FALSE)
            {
                if (pregexp.isPregexpAtWordBoundary(s, frame0_1.i, n) != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply2(frame0_1.sk, frame0_1.i);
                } else
                {
                    return Scheme.applyToArgs.apply1(frame0_1.fk);
                }
            }
            if (Scheme.isEqv.apply2(frame0_1._fld1, pregexp.Lit28) != Boolean.FALSE)
            {
                if (pregexp.isPregexpAtWordBoundary(s, frame0_1.i, n) != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply1(frame0_1.fk);
                } else
                {
                    return Scheme.applyToArgs.apply2(frame0_1.sk, frame0_1.i);
                }
            }
            boolean flag = characters.isChar(frame0_1._fld1);
            if (flag ? Scheme.numLss.apply2(frame0_1.i, n) != Boolean.FALSE : flag)
            {
                int i;
                CharSequence charsequence2;
                int k1;
                ModuleMethod modulemethod1;
                Object obj26;
                CharSequence charsequence3;
                Object obj27;
                int l1;
                if (case$Mnsensitive$Qu != Boolean.FALSE)
                {
                    modulemethod1 = characters.char$Eq$Qu;
                } else
                {
                    modulemethod1 = unicode.char$Mnci$Eq$Qu;
                }
                obj26 = s;
                boolean flag1;
                CharSequence charsequence;
                int j;
                char c;
                ModuleMethod modulemethod;
                Object obj6;
                Object aobj[];
                Object obj7;
                Object obj8;
                Object aobj1[];
                Boolean boolean1;
                int k;
                boolean flag2;
                Boolean boolean2;
                int l;
                Object obj11;
                Object obj12;
                Object obj13;
                Object obj14;
                Object obj15;
                Object obj16;
                Object obj17;
                Object obj18;
                Object aobj2[];
                Object obj19;
                CharSequence charsequence1;
                int i1;
                int j1;
                Object aobj3[];
                Object obj23;
                try
                {
                    charsequence3 = (CharSequence)obj26;
                }
                catch (ClassCastException classcastexception9)
                {
                    throw new WrongType(classcastexception9, "string-ref", 1, obj26);
                }
                obj27 = frame0_1.i;
                try
                {
                    l1 = ((Number)obj27).intValue();
                }
                catch (ClassCastException classcastexception10)
                {
                    throw new WrongType(classcastexception10, "string-ref", 2, obj27);
                }
                if (modulemethod1.apply2(Char.make(strings.stringRef(charsequence3, l1)), frame0_1._fld1) != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply2(frame0_1.sk, AddOp.$Pl.apply2(frame0_1.i, pregexp.Lit8));
                } else
                {
                    return Scheme.applyToArgs.apply1(frame0_1.fk);
                }
            }
            i = 1 & 1 + lists.isPair(frame0_1._fld1);
            if (i == 0 ? i != 0 : Scheme.numLss.apply2(frame0_1.i, n) != Boolean.FALSE)
            {
                Object obj24 = s;
                Object obj25;
                try
                {
                    charsequence2 = (CharSequence)obj24;
                }
                catch (ClassCastException classcastexception7)
                {
                    throw new WrongType(classcastexception7, "string-ref", 1, obj24);
                }
                obj25 = frame0_1.i;
                try
                {
                    k1 = ((Number)obj25).intValue();
                }
                catch (ClassCastException classcastexception8)
                {
                    throw new WrongType(classcastexception8, "string-ref", 2, obj25);
                }
                if (pregexp.isPregexpCheckIfInCharClass(Char.make(strings.stringRef(charsequence2, k1)), frame0_1._fld1) != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply2(frame0_1.sk, AddOp.$Pl.apply2(frame0_1.i, pregexp.Lit8));
                } else
                {
                    return Scheme.applyToArgs.apply1(frame0_1.fk);
                }
            }
            flag1 = lists.isPair(frame0_1._fld1);
            if (flag1 ? (obj23 = Scheme.isEqv.apply2(lists.car.apply1(frame0_1._fld1), pregexp.Lit83)) == Boolean.FALSE ? obj23 != Boolean.FALSE : Scheme.numLss.apply2(frame0_1.i, n) != Boolean.FALSE : flag1)
            {
                Object obj4 = s;
                Object obj5;
                try
                {
                    charsequence = (CharSequence)obj4;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "string-ref", 1, obj4);
                }
                obj5 = frame0_1.i;
                try
                {
                    j = ((Number)obj5).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-ref", 2, obj5);
                }
                c = strings.stringRef(charsequence, j);
                if (case$Mnsensitive$Qu != Boolean.FALSE)
                {
                    modulemethod = characters.char$Ls$Eq$Qu;
                } else
                {
                    modulemethod = unicode.char$Mnci$Ls$Eq$Qu;
                }
                obj6 = modulemethod.apply2(lists.cadr.apply1(frame0_1._fld1), Char.make(c));
                if (obj6 == Boolean.FALSE ? obj6 != Boolean.FALSE : modulemethod.apply2(Char.make(c), lists.caddr.apply1(frame0_1._fld1)) != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply2(frame0_1.sk, AddOp.$Pl.apply2(frame0_1.i, pregexp.Lit8));
                } else
                {
                    return Scheme.applyToArgs.apply1(frame0_1.fk);
                }
            }
            if (lists.isPair(frame0_1._fld1))
            {
                obj7 = lists.car.apply1(frame0_1._fld1);
                if (Scheme.isEqv.apply2(obj7, pregexp.Lit83) != Boolean.FALSE)
                {
                    if (Scheme.numGEq.apply2(frame0_1.i, n) != Boolean.FALSE)
                    {
                        return Scheme.applyToArgs.apply1(frame0_1.fk);
                    } else
                    {
                        aobj3 = new Object[1];
                        aobj3[0] = pregexp.Lit101;
                        return pregexp.pregexpError$V(aobj3);
                    }
                }
                if (Scheme.isEqv.apply2(obj7, pregexp.Lit82) != Boolean.FALSE)
                {
                    if (Scheme.numGEq.apply2(frame0_1.i, n) != Boolean.FALSE)
                    {
                        return Scheme.applyToArgs.apply1(frame0_1.fk);
                    } else
                    {
                        return frame0_1.lambda5loupOneOfChars(lists.cdr.apply1(frame0_1._fld1));
                    }
                }
                if (Scheme.isEqv.apply2(obj7, pregexp.Lit17) != Boolean.FALSE)
                {
                    if (Scheme.numGEq.apply2(frame0_1.i, n) != Boolean.FALSE)
                    {
                        return Scheme.applyToArgs.apply1(frame0_1.fk);
                    } else
                    {
                        return lambda3sub(lists.cadr.apply1(frame0_1._fld1), frame0_1.i, frame0_1.Fn2, frame0_1.Fn3);
                    }
                }
                if (Scheme.isEqv.apply2(obj7, pregexp.Lit5) != Boolean.FALSE)
                {
                    return frame0_1.lambda6loupSeq(lists.cdr.apply1(frame0_1._fld1), frame0_1.i);
                }
                if (Scheme.isEqv.apply2(obj7, pregexp.Lit4) != Boolean.FALSE)
                {
                    return frame0_1.lambda7loupOr(lists.cdr.apply1(frame0_1._fld1));
                }
                if (Scheme.isEqv.apply2(obj7, pregexp.Lit20) != Boolean.FALSE)
                {
                    obj18 = pregexp.pregexpListRef(backrefs, lists.cadr.apply1(frame0_1._fld1));
                    if (obj18 != Boolean.FALSE)
                    {
                        obj19 = lists.cdr.apply1(obj18);
                    } else
                    {
                        aobj2 = new Object[3];
                        aobj2[0] = pregexp.Lit101;
                        aobj2[1] = pregexp.Lit102;
                        aobj2[2] = frame0_1._fld1;
                        pregexp.pregexpError$V(aobj2);
                        obj19 = Boolean.FALSE;
                    }
                    if (obj19 != Boolean.FALSE)
                    {
                        Object obj20 = s;
                        Object obj21;
                        Object obj22;
                        try
                        {
                            charsequence1 = (CharSequence)obj20;
                        }
                        catch (ClassCastException classcastexception4)
                        {
                            throw new WrongType(classcastexception4, "substring", 1, obj20);
                        }
                        obj21 = lists.car.apply1(obj19);
                        try
                        {
                            i1 = ((Number)obj21).intValue();
                        }
                        catch (ClassCastException classcastexception5)
                        {
                            throw new WrongType(classcastexception5, "substring", 2, obj21);
                        }
                        obj22 = lists.cdr.apply1(obj19);
                        try
                        {
                            j1 = ((Number)obj22).intValue();
                        }
                        catch (ClassCastException classcastexception6)
                        {
                            throw new WrongType(classcastexception6, "substring", 3, obj22);
                        }
                        return pregexp.pregexpStringMatch(strings.substring(charsequence1, i1, j1), s, frame0_1.i, n, frame0_1.Fn4, frame0_1.fk);
                    } else
                    {
                        return Scheme.applyToArgs.apply2(frame0_1.sk, frame0_1.i);
                    }
                }
                if (Scheme.isEqv.apply2(obj7, pregexp.Lit100) != Boolean.FALSE)
                {
                    return lambda3sub(lists.cadr.apply1(frame0_1._fld1), frame0_1.i, frame0_1.Fn5, frame0_1.fk);
                }
                if (Scheme.isEqv.apply2(obj7, pregexp.Lit103) != Boolean.FALSE)
                {
                    if (lambda3sub(lists.cadr.apply1(frame0_1._fld1), frame0_1.i, identity, pregexp.lambda$Fn6) != Boolean.FALSE)
                    {
                        return Scheme.applyToArgs.apply2(frame0_1.sk, frame0_1.i);
                    } else
                    {
                        return Scheme.applyToArgs.apply1(frame0_1.fk);
                    }
                }
                if (Scheme.isEqv.apply2(obj7, pregexp.Lit104) != Boolean.FALSE)
                {
                    if (lambda3sub(lists.cadr.apply1(frame0_1._fld1), frame0_1.i, identity, pregexp.lambda$Fn7) != Boolean.FALSE)
                    {
                        return Scheme.applyToArgs.apply1(frame0_1.fk);
                    } else
                    {
                        return Scheme.applyToArgs.apply2(frame0_1.sk, frame0_1.i);
                    }
                }
                if (Scheme.isEqv.apply2(obj7, pregexp.Lit105) != Boolean.FALSE)
                {
                    obj15 = n;
                    obj16 = sn;
                    n = frame0_1.i;
                    sn = frame0_1.i;
                    obj17 = lambda3sub(LList.list4(pregexp.Lit5, pregexp.Lit106, lists.cadr.apply1(frame0_1._fld1), pregexp.Lit12), pregexp.Lit73, identity, pregexp.lambda$Fn8);
                    n = obj15;
                    sn = obj16;
                    if (obj17 != Boolean.FALSE)
                    {
                        return Scheme.applyToArgs.apply2(frame0_1.sk, frame0_1.i);
                    } else
                    {
                        return Scheme.applyToArgs.apply1(frame0_1.fk);
                    }
                }
                if (Scheme.isEqv.apply2(obj7, pregexp.Lit107) != Boolean.FALSE)
                {
                    obj12 = n;
                    obj13 = sn;
                    n = frame0_1.i;
                    sn = frame0_1.i;
                    obj14 = lambda3sub(LList.list4(pregexp.Lit5, pregexp.Lit108, lists.cadr.apply1(frame0_1._fld1), pregexp.Lit12), pregexp.Lit73, identity, pregexp.lambda$Fn9);
                    n = obj12;
                    sn = obj13;
                    if (obj14 != Boolean.FALSE)
                    {
                        return Scheme.applyToArgs.apply1(frame0_1.fk);
                    } else
                    {
                        return Scheme.applyToArgs.apply2(frame0_1.sk, frame0_1.i);
                    }
                }
                if (Scheme.isEqv.apply2(obj7, pregexp.Lit109) != Boolean.FALSE)
                {
                    obj11 = lambda3sub(lists.cadr.apply1(frame0_1._fld1), frame0_1.i, identity, pregexp.lambda$Fn10);
                    if (obj11 != Boolean.FALSE)
                    {
                        return Scheme.applyToArgs.apply2(frame0_1.sk, obj11);
                    } else
                    {
                        return Scheme.applyToArgs.apply1(frame0_1.fk);
                    }
                }
                obj8 = Scheme.isEqv.apply2(obj7, pregexp.Lit60);
                if (obj8 == Boolean.FALSE ? Scheme.isEqv.apply2(obj7, pregexp.Lit61) != Boolean.FALSE : obj8 != Boolean.FALSE)
                {
                    frame0_1.old = case$Mnsensitive$Qu;
                    case$Mnsensitive$Qu = Scheme.isEqv.apply2(lists.car.apply1(frame0_1._fld1), pregexp.Lit60);
                    return lambda3sub(lists.cadr.apply1(frame0_1._fld1), frame0_1.i, frame0_1.Fn11, frame0_1.Fn12);
                }
                if (Scheme.isEqv.apply2(obj7, pregexp.Lit68) != Boolean.FALSE)
                {
                    Object obj9 = lists.cadr.apply1(frame0_1._fld1);
                    try
                    {
                        boolean1 = Boolean.FALSE;
                    }
                    catch (ClassCastException classcastexception2)
                    {
                        throw new WrongType(classcastexception2, "maximal?", -2, obj9);
                    }
                    if (obj9 != boolean1)
                    {
                        k = 1;
                    } else
                    {
                        k = 0;
                    }
                    frame0_1.Qu = 1 & k + 1;
                    frame0_1.p = lists.caddr.apply1(frame0_1._fld1);
                    frame0_1.q = lists.cadddr.apply1(frame0_1._fld1);
                    if (frame0_1.Qu)
                    {
                        Object obj10 = frame0_1.q;
                        try
                        {
                            boolean2 = Boolean.FALSE;
                        }
                        catch (ClassCastException classcastexception3)
                        {
                            throw new WrongType(classcastexception3, "could-loop-infinitely?", -2, obj10);
                        }
                        if (obj10 != boolean2)
                        {
                            l = 1;
                        } else
                        {
                            l = 0;
                        }
                        flag2 = 1 & l + 1;
                    } else
                    {
                        flag2 = frame0_1.Qu;
                    }
                    frame0_1.Qu = flag2;
                    frame0_1.re = lists.car.apply1(lists.cddddr.apply1(frame0_1._fld1));
                    return frame0_1.lambda8loupP(pregexp.Lit73, frame0_1.i);
                } else
                {
                    aobj1 = new Object[1];
                    aobj1[0] = pregexp.Lit101;
                    return pregexp.pregexpError$V(aobj1);
                }
            }
            if (Scheme.numGEq.apply2(frame0_1.i, n) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply1(frame0_1.fk);
            } else
            {
                aobj = new Object[1];
                aobj[0] = pregexp.Lit101;
                return pregexp.pregexpError$V(aobj);
            }
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 15)
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

        public frame()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 15, pregexp.Lit112, 4097);
            modulemethod.setProperty("source-location", "pregexp.scm:460");
            identity = modulemethod;
        }
    }

    public class frame0 extends ModuleBody
    {

        boolean could$Mnloop$Mninfinitely$Qu;
        Object fk;
        Object i;
        final ModuleMethod lambda$Fn11;
        final ModuleMethod lambda$Fn12;
        final ModuleMethod lambda$Fn2;
        final ModuleMethod lambda$Fn3;
        final ModuleMethod lambda$Fn4;
        final ModuleMethod lambda$Fn5;
        boolean maximal$Qu;
        Object old;
        Object p;
        Object q;
        Object re;
        Object re$1;
        Object sk;
        frame staticLink;

        static Boolean lambda13()
        {
            return Boolean.FALSE;
        }

        static Boolean lambda14()
        {
            return Boolean.FALSE;
        }

        static Boolean lambda15()
        {
            return Boolean.FALSE;
        }

        static Boolean lambda16()
        {
            return Boolean.FALSE;
        }

        static Boolean lambda17()
        {
            return Boolean.FALSE;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.apply0(modulemethod);

            case 10: // '\n'
                return lambda10();

            case 14: // '\016'
                return lambda19();
            }
        }

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            switch (modulemethod.selector)
            {
            case 10: // '\n'
            default:
                return super.apply1(modulemethod, obj);

            case 9: // '\t'
                return lambda9(obj);

            case 11: // '\013'
                return lambda11(obj);

            case 12: // '\f'
                return lambda12(obj);

            case 13: // '\r'
                return lambda18(obj);
            }
        }

        Object lambda10()
        {
            return Scheme.applyToArgs.apply2(sk, AddOp.$Pl.apply2(i, pregexp.Lit8));
        }

        Object lambda11(Object obj)
        {
            return Scheme.applyToArgs.apply2(sk, obj);
        }

        Object lambda12(Object obj)
        {
            Object obj1 = lists.assv(re$1, staticLink.backrefs);
            Pair pair;
            try
            {
                pair = (Pair)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "set-cdr!", 1, obj1);
            }
            lists.setCdr$Ex(pair, lists.cons(i, obj));
            return Scheme.applyToArgs.apply2(sk, obj);
        }

        Object lambda18(Object obj)
        {
            staticLink.Qu = old;
            return Scheme.applyToArgs.apply2(sk, obj);
        }

        Object lambda19()
        {
            staticLink.Qu = old;
            return Scheme.applyToArgs.apply1(fk);
        }

        public Object lambda5loupOneOfChars(Object obj)
        {
            frame1 frame1_1 = new frame1();
            frame1_1.staticLink = this;
            frame1_1.chars = obj;
            if (lists.isNull(frame1_1.chars))
            {
                return Scheme.applyToArgs.apply1(fk);
            } else
            {
                return staticLink.lambda3sub(lists.car.apply1(frame1_1.chars), i, sk, frame1_1.Fn13);
            }
        }

        public Object lambda6loupSeq(Object obj, Object obj1)
        {
            frame2 frame2_1 = new frame2();
            frame2_1.staticLink = this;
            frame2_1.res = obj;
            if (lists.isNull(frame2_1.res))
            {
                return Scheme.applyToArgs.apply2(sk, obj1);
            } else
            {
                return staticLink.lambda3sub(lists.car.apply1(frame2_1.res), obj1, frame2_1.Fn14, fk);
            }
        }

        public Object lambda7loupOr(Object obj)
        {
            frame3 frame3_1 = new frame3();
            frame3_1.staticLink = this;
            frame3_1.res = obj;
            if (lists.isNull(frame3_1.res))
            {
                return Scheme.applyToArgs.apply1(fk);
            } else
            {
                return staticLink.lambda3sub(lists.car.apply1(frame3_1.res), i, frame3_1.Fn15, frame3_1.Fn16);
            }
        }

        public Object lambda8loupP(Object obj, Object obj1)
        {
            frame4 frame4_1 = new frame4();
            frame4_1.staticLink = this;
            frame4_1.k = obj;
            frame4_1.i = obj1;
            if (Scheme.numLss.apply2(frame4_1.k, p) != Boolean.FALSE)
            {
                return staticLink.lambda3sub(re, frame4_1.i, frame4_1.Fn17, fk);
            }
            Object obj2;
            if (q != Boolean.FALSE)
            {
                obj2 = AddOp.$Mn.apply2(q, p);
            } else
            {
                obj2 = q;
            }
            frame4_1.q = obj2;
            return frame4_1.lambda24loupQ(pregexp.Lit73, frame4_1.i);
        }

        Object lambda9(Object obj)
        {
            return Scheme.applyToArgs.apply1(fk);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.match0(modulemethod, callcontext);

            case 14: // '\016'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;

            case 10: // '\n'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            }
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            case 10: // '\n'
            default:
                return super.match1(modulemethod, obj, callcontext);

            case 13: // '\r'
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;

            case 12: // '\f'
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;

            case 11: // '\013'
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;

            case 9: // '\t'
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }
        }

        public frame0()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 9, null, 4097);
            modulemethod.setProperty("source-location", "pregexp.scm:513");
            lambda$Fn2 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 10, null, 0);
            modulemethod1.setProperty("source-location", "pregexp.scm:514");
            lambda$Fn3 = modulemethod1;
            ModuleMethod modulemethod2 = new ModuleMethod(this, 11, null, 4097);
            modulemethod2.setProperty("source-location", "pregexp.scm:541");
            lambda$Fn4 = modulemethod2;
            ModuleMethod modulemethod3 = new ModuleMethod(this, 12, null, 4097);
            modulemethod3.setProperty("source-location", "pregexp.scm:545");
            lambda$Fn5 = modulemethod3;
            ModuleMethod modulemethod4 = new ModuleMethod(this, 13, null, 4097);
            modulemethod4.setProperty("source-location", "pregexp.scm:587");
            lambda$Fn11 = modulemethod4;
            ModuleMethod modulemethod5 = new ModuleMethod(this, 14, null, 0);
            modulemethod5.setProperty("source-location", "pregexp.scm:590");
            lambda$Fn12 = modulemethod5;
        }
    }

    public class frame1 extends ModuleBody
    {

        Object chars;
        final ModuleMethod lambda$Fn13;
        frame0 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 1)
            {
                return lambda20();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        Object lambda20()
        {
            return staticLink.lambda5loupOneOfChars(lists.cdr.apply1(chars));
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

        public frame1()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 1, null, 0);
            modulemethod.setProperty("source-location", "pregexp.scm:508");
            lambda$Fn13 = modulemethod;
        }
    }

    public class frame2 extends ModuleBody
    {

        final ModuleMethod lambda$Fn14;
        Object res;
        frame0 staticLink;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 2)
            {
                return lambda21(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda21(Object obj)
        {
            return staticLink.lambda6loupSeq(lists.cdr.apply1(res), obj);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 2)
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

        public frame2()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 2, null, 4097);
            modulemethod.setProperty("source-location", "pregexp.scm:519");
            lambda$Fn14 = modulemethod;
        }
    }

    public class frame3 extends ModuleBody
    {

        final ModuleMethod lambda$Fn15;
        final ModuleMethod lambda$Fn16;
        Object res;
        frame0 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 4)
            {
                return lambda23();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 3)
            {
                return lambda22(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda22(Object obj)
        {
            Object obj1 = Scheme.applyToArgs.apply2(staticLink.sk, obj);
            if (obj1 != Boolean.FALSE)
            {
                return obj1;
            } else
            {
                return staticLink.lambda7loupOr(lists.cdr.apply1(res));
            }
        }

        Object lambda23()
        {
            return staticLink.lambda7loupOr(lists.cdr.apply1(res));
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 4)
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
            if (modulemethod.selector == 3)
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

        public frame3()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 3, null, 4097);
            modulemethod.setProperty("source-location", "pregexp.scm:526");
            lambda$Fn15 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 4, null, 0);
            modulemethod1.setProperty("source-location", "pregexp.scm:529");
            lambda$Fn16 = modulemethod1;
        }
    }

    public class frame4 extends ModuleBody
    {

        Object i;
        Object k;
        final ModuleMethod lambda$Fn17;
        Object q;
        frame0 staticLink;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 8)
            {
                return lambda25(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        public Object lambda24loupQ(Object obj, Object obj1)
        {
            frame5 frame5_1 = new frame5();
            frame5_1.staticLink = this;
            frame5_1.k = obj;
            frame5_1.i = obj1;
            frame5_1.fk = frame5_1.fk;
            Object obj2;
            if (q == Boolean.FALSE ? q != Boolean.FALSE : Scheme.numGEq.apply2(frame5_1.k, q) != Boolean.FALSE)
            {
                obj2 = frame5_1.lambda26fk();
            } else
            {
                if (staticLink.Qu)
                {
                    return staticLink.staticLink.lambda3sub(staticLink.re, frame5_1.i, frame5_1.Fn18, frame5_1.fk);
                }
                obj2 = frame5_1.lambda26fk();
                if (obj2 == Boolean.FALSE)
                {
                    return staticLink.staticLink.lambda3sub(staticLink.re, frame5_1.i, frame5_1.Fn19, frame5_1.fk);
                }
            }
            return obj2;
        }

        Object lambda25(Object obj)
        {
            if (staticLink.Qu ? Scheme.numEqu.apply2(obj, i) != Boolean.FALSE : staticLink.Qu)
            {
                Object aobj[] = new Object[2];
                aobj[0] = pregexp.Lit101;
                aobj[1] = pregexp.Lit110;
                pregexp.pregexpError$V(aobj);
            }
            return staticLink.lambda8loupP(AddOp.$Pl.apply2(k, pregexp.Lit8), obj);
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

        public frame4()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 8, null, 4097);
            modulemethod.setProperty("source-location", "pregexp.scm:602");
            lambda$Fn17 = modulemethod;
        }
    }

    public class frame5 extends ModuleBody
    {

        Procedure fk;
        Object i;
        Object k;
        final ModuleMethod lambda$Fn18;
        final ModuleMethod lambda$Fn19;
        frame4 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 5)
            {
                return lambda26fk();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.apply1(modulemethod, obj);

            case 6: // '\006'
                return lambda27(obj);

            case 7: // '\007'
                return lambda28(obj);
            }
        }

        public Object lambda26fk()
        {
            return Scheme.applyToArgs.apply2(staticLink.staticLink.sk, i);
        }

        Object lambda27(Object obj)
        {
            Object obj1;
            if (staticLink.staticLink.Qu ? Scheme.numEqu.apply2(obj, i) != Boolean.FALSE : staticLink.staticLink.Qu)
            {
                Object aobj[] = new Object[2];
                aobj[0] = pregexp.Lit101;
                aobj[1] = pregexp.Lit110;
                pregexp.pregexpError$V(aobj);
            }
            obj1 = staticLink.lambda24loupQ(AddOp.$Pl.apply2(k, pregexp.Lit8), obj);
            if (obj1 != Boolean.FALSE)
            {
                return obj1;
            } else
            {
                return lambda26fk();
            }
        }

        Object lambda28(Object obj)
        {
            return staticLink.lambda24loupQ(AddOp.$Pl.apply2(k, pregexp.Lit8), obj);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 5)
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
            switch (modulemethod.selector)
            {
            default:
                return super.match1(modulemethod, obj, callcontext);

            case 7: // '\007'
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;

            case 6: // '\006'
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }
        }

        public frame5()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 5, pregexp.Lit111, 0);
            modulemethod.setProperty("source-location", "pregexp.scm:612");
            fk = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 6, null, 4097);
            modulemethod1.setProperty("source-location", "pregexp.scm:617");
            lambda$Fn18 = modulemethod1;
            ModuleMethod modulemethod2 = new ModuleMethod(this, 7, null, 4097);
            modulemethod2.setProperty("source-location", "pregexp.scm:628");
            lambda$Fn19 = modulemethod2;
        }
    }


    public static Char $Stpregexp$Mncomment$Mnchar$St;
    public static Object $Stpregexp$Mnnul$Mnchar$Mnint$St;
    public static Object $Stpregexp$Mnreturn$Mnchar$St;
    public static Object $Stpregexp$Mnspace$Mnsensitive$Qu$St;
    public static Object $Stpregexp$Mntab$Mnchar$St;
    public static IntNum $Stpregexp$Mnversion$St;
    public static final pregexp $instance;
    static final IntNum Lit0 = IntNum.make(0x131f246);
    static final Char Lit1 = Char.make(59);
    static final SimpleSymbol Lit10 = (SimpleSymbol)(new SimpleSymbol(":bos")).readResolve();
    static final SimpleSymbol Lit100;
    static final SimpleSymbol Lit101;
    static final SimpleSymbol Lit102 = (SimpleSymbol)(new SimpleSymbol("non-existent-backref")).readResolve();
    static final SimpleSymbol Lit103;
    static final SimpleSymbol Lit104;
    static final SimpleSymbol Lit105;
    static final PairWithPosition Lit106;
    static final SimpleSymbol Lit107;
    static final PairWithPosition Lit108;
    static final SimpleSymbol Lit109;
    static final Char Lit11;
    static final SimpleSymbol Lit110 = (SimpleSymbol)(new SimpleSymbol("greedy-quantifier-operand-could-be-empty")).readResolve();
    static final SimpleSymbol Lit111 = (SimpleSymbol)(new SimpleSymbol("fk")).readResolve();
    static final SimpleSymbol Lit112 = (SimpleSymbol)(new SimpleSymbol("identity")).readResolve();
    static final Char Lit113 = Char.make(38);
    static final SimpleSymbol Lit114;
    static final SimpleSymbol Lit115 = (SimpleSymbol)(new SimpleSymbol("pattern-must-be-compiled-or-string-regexp")).readResolve();
    static final PairWithPosition Lit116;
    static final SimpleSymbol Lit117;
    static final SimpleSymbol Lit118;
    static final SimpleSymbol Lit119;
    static final SimpleSymbol Lit12 = (SimpleSymbol)(new SimpleSymbol(":eos")).readResolve();
    static final SimpleSymbol Lit120;
    static final SimpleSymbol Lit121;
    static final SimpleSymbol Lit122;
    static final SimpleSymbol Lit123;
    static final SimpleSymbol Lit124;
    static final SimpleSymbol Lit125;
    static final SimpleSymbol Lit126;
    static final SimpleSymbol Lit127;
    static final SimpleSymbol Lit128;
    static final SimpleSymbol Lit129;
    static final Char Lit13;
    static final SimpleSymbol Lit130;
    static final SimpleSymbol Lit131;
    static final SimpleSymbol Lit132;
    static final SimpleSymbol Lit133;
    static final SimpleSymbol Lit134;
    static final SimpleSymbol Lit135;
    static final SimpleSymbol Lit14;
    static final Char Lit15;
    static final IntNum Lit16 = IntNum.make(2);
    static final SimpleSymbol Lit17;
    static final Char Lit18;
    static final Char Lit19;
    static final Char Lit2 = Char.make(97);
    static final SimpleSymbol Lit20 = (SimpleSymbol)(new SimpleSymbol(":backref")).readResolve();
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22 = (SimpleSymbol)(new SimpleSymbol("backslash")).readResolve();
    static final SimpleSymbol Lit23 = (SimpleSymbol)(new SimpleSymbol(":empty")).readResolve();
    static final Char Lit24 = Char.make(10);
    static final Char Lit25 = Char.make(98);
    static final SimpleSymbol Lit26 = (SimpleSymbol)(new SimpleSymbol(":wbdry")).readResolve();
    static final Char Lit27 = Char.make(66);
    static final SimpleSymbol Lit28 = (SimpleSymbol)(new SimpleSymbol(":not-wbdry")).readResolve();
    static final Char Lit29 = Char.make(100);
    static final Char Lit3 = Char.make(32);
    static final SimpleSymbol Lit30;
    static final Char Lit31 = Char.make(68);
    static final PairWithPosition Lit32;
    static final Char Lit33 = Char.make(110);
    static final Char Lit34 = Char.make(114);
    static final Char Lit35 = Char.make(115);
    static final SimpleSymbol Lit36;
    static final Char Lit37 = Char.make(83);
    static final PairWithPosition Lit38;
    static final Char Lit39 = Char.make(116);
    static final SimpleSymbol Lit4 = (SimpleSymbol)(new SimpleSymbol(":or")).readResolve();
    static final Char Lit40 = Char.make(119);
    static final SimpleSymbol Lit41;
    static final Char Lit42 = Char.make(87);
    static final PairWithPosition Lit43;
    static final Char Lit44 = Char.make(58);
    static final SimpleSymbol Lit45;
    static final Char Lit46;
    static final Char Lit47;
    static final Char Lit48 = Char.make(61);
    static final PairWithPosition Lit49;
    static final SimpleSymbol Lit5 = (SimpleSymbol)(new SimpleSymbol(":seq")).readResolve();
    static final Char Lit50 = Char.make(33);
    static final PairWithPosition Lit51;
    static final Char Lit52 = Char.make(62);
    static final PairWithPosition Lit53;
    static final Char Lit54 = Char.make(60);
    static final PairWithPosition Lit55;
    static final PairWithPosition Lit56;
    static final SimpleSymbol Lit57;
    static final Char Lit58 = Char.make(45);
    static final Char Lit59 = Char.make(105);
    static final Char Lit6;
    static final SimpleSymbol Lit60 = (SimpleSymbol)(new SimpleSymbol(":case-sensitive")).readResolve();
    static final SimpleSymbol Lit61 = (SimpleSymbol)(new SimpleSymbol(":case-insensitive")).readResolve();
    static final Char Lit62 = Char.make(120);
    static final PairWithPosition Lit63;
    static final SimpleSymbol Lit64;
    static final Char Lit65;
    static final Char Lit66;
    static final Char Lit67;
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69 = (SimpleSymbol)(new SimpleSymbol("minimal?")).readResolve();
    static final Char Lit7;
    static final SimpleSymbol Lit70 = (SimpleSymbol)(new SimpleSymbol("at-least")).readResolve();
    static final SimpleSymbol Lit71 = (SimpleSymbol)(new SimpleSymbol("at-most")).readResolve();
    static final SimpleSymbol Lit72 = (SimpleSymbol)(new SimpleSymbol("next-i")).readResolve();
    static final IntNum Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75 = (SimpleSymbol)(new SimpleSymbol("left-brace-must-be-followed-by-number")).readResolve();
    static final SimpleSymbol Lit76;
    static final Char Lit77 = Char.make(44);
    static final Char Lit78;
    static final SimpleSymbol Lit79 = (SimpleSymbol)(new SimpleSymbol(":none-of-chars")).readResolve();
    static final IntNum Lit8 = IntNum.make(1);
    static final SimpleSymbol Lit80;
    static final SimpleSymbol Lit81 = (SimpleSymbol)(new SimpleSymbol("character-class-ended-too-soon")).readResolve();
    static final SimpleSymbol Lit82 = (SimpleSymbol)(new SimpleSymbol(":one-of-chars")).readResolve();
    static final SimpleSymbol Lit83 = (SimpleSymbol)(new SimpleSymbol(":char-range")).readResolve();
    static final Char Lit84 = Char.make(95);
    static final SimpleSymbol Lit85 = (SimpleSymbol)(new SimpleSymbol(":alnum")).readResolve();
    static final SimpleSymbol Lit86 = (SimpleSymbol)(new SimpleSymbol(":alpha")).readResolve();
    static final SimpleSymbol Lit87 = (SimpleSymbol)(new SimpleSymbol(":ascii")).readResolve();
    static final SimpleSymbol Lit88 = (SimpleSymbol)(new SimpleSymbol(":blank")).readResolve();
    static final SimpleSymbol Lit89 = (SimpleSymbol)(new SimpleSymbol(":cntrl")).readResolve();
    static final Char Lit9;
    static final SimpleSymbol Lit90 = (SimpleSymbol)(new SimpleSymbol(":graph")).readResolve();
    static final SimpleSymbol Lit91 = (SimpleSymbol)(new SimpleSymbol(":lower")).readResolve();
    static final SimpleSymbol Lit92 = (SimpleSymbol)(new SimpleSymbol(":print")).readResolve();
    static final SimpleSymbol Lit93 = (SimpleSymbol)(new SimpleSymbol(":punct")).readResolve();
    static final SimpleSymbol Lit94 = (SimpleSymbol)(new SimpleSymbol(":upper")).readResolve();
    static final SimpleSymbol Lit95 = (SimpleSymbol)(new SimpleSymbol(":xdigit")).readResolve();
    static final Char Lit96 = Char.make(99);
    static final Char Lit97 = Char.make(101);
    static final Char Lit98 = Char.make(102);
    static final SimpleSymbol Lit99;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn10;
    static final ModuleMethod lambda$Fn6;
    static final ModuleMethod lambda$Fn7;
    static final ModuleMethod lambda$Fn8;
    static final ModuleMethod lambda$Fn9;
    public static final ModuleMethod pregexp;
    public static final ModuleMethod pregexp$Mnat$Mnword$Mnboundary$Qu;
    public static final ModuleMethod pregexp$Mnchar$Mnword$Qu;
    public static final ModuleMethod pregexp$Mncheck$Mnif$Mnin$Mnchar$Mnclass$Qu;
    public static final ModuleMethod pregexp$Mnerror;
    public static final ModuleMethod pregexp$Mninvert$Mnchar$Mnlist;
    public static final ModuleMethod pregexp$Mnlist$Mnref;
    public static final ModuleMethod pregexp$Mnmake$Mnbackref$Mnlist;
    public static final ModuleMethod pregexp$Mnmatch;
    public static final ModuleMethod pregexp$Mnmatch$Mnpositions;
    public static final ModuleMethod pregexp$Mnmatch$Mnpositions$Mnaux;
    public static final ModuleMethod pregexp$Mnquote;
    public static final ModuleMethod pregexp$Mnread$Mnbranch;
    public static final ModuleMethod pregexp$Mnread$Mnchar$Mnlist;
    public static final ModuleMethod pregexp$Mnread$Mncluster$Mntype;
    public static final ModuleMethod pregexp$Mnread$Mnescaped$Mnchar;
    public static final ModuleMethod pregexp$Mnread$Mnescaped$Mnnumber;
    public static final ModuleMethod pregexp$Mnread$Mnnums;
    public static final ModuleMethod pregexp$Mnread$Mnpattern;
    public static final ModuleMethod pregexp$Mnread$Mnpiece;
    public static final ModuleMethod pregexp$Mnread$Mnposix$Mnchar$Mnclass;
    public static final ModuleMethod pregexp$Mnread$Mnsubpattern;
    public static final ModuleMethod pregexp$Mnreplace;
    public static final ModuleMethod pregexp$Mnreplace$Mnaux;
    public static final ModuleMethod pregexp$Mnreplace$St;
    public static final ModuleMethod pregexp$Mnreverse$Ex;
    public static final ModuleMethod pregexp$Mnsplit;
    public static final ModuleMethod pregexp$Mnstring$Mnmatch;
    public static final ModuleMethod pregexp$Mnwrap$Mnquantifier$Mnif$Mnany;

    public pregexp()
    {
        ModuleInfo.register(this);
    }

    public static Object isPregexpAtWordBoundary(Object obj, Object obj1, Object obj2)
    {
        Object obj3 = Scheme.numEqu.apply2(obj1, Lit73);
        boolean flag;
        Object obj4;
        boolean flag1;
        CharSequence charsequence;
        int i;
        char c;
        CharSequence charsequence1;
        Object obj5;
        int j;
        char c1;
        Object obj6;
        Object obj7;
        Object obj8;
        Boolean boolean1;
        int k;
        int l;
        try
        {
            flag = ((Boolean)obj3).booleanValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "x", -2, obj3);
        }
        if (flag)
        {
            if (flag)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        obj4 = Scheme.numGEq.apply2(obj1, obj2);
        try
        {
            flag1 = ((Boolean)obj4).booleanValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "x", -2, obj4);
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
        }
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "string-ref", 1, obj);
        }
        try
        {
            i = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "string-ref", 2, obj1);
        }
        c = strings.stringRef(charsequence, i);
        try
        {
            charsequence1 = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "string-ref", 1, obj);
        }
        obj5 = AddOp.$Mn.apply2(obj1, Lit8);
        try
        {
            j = ((Number)obj5).intValue();
        }
        catch (ClassCastException classcastexception5)
        {
            throw new WrongType(classcastexception5, "string-ref", 2, obj5);
        }
        c1 = strings.stringRef(charsequence1, j);
        obj6 = isPregexpCheckIfInCharClass(Char.make(c), Lit41);
        obj7 = isPregexpCheckIfInCharClass(Char.make(c1), Lit41);
        if (obj6 != Boolean.FALSE)
        {
            Boolean boolean2;
            if (obj7 != Boolean.FALSE)
            {
                boolean2 = Boolean.FALSE;
            } else
            {
                boolean2 = Boolean.TRUE;
            }
            obj8 = boolean2;
        } else
        {
            obj8 = obj6;
        }
        if (obj8 != Boolean.FALSE)
        {
            return obj8;
        }
        try
        {
            boolean1 = Boolean.FALSE;
        }
        catch (ClassCastException classcastexception6)
        {
            throw new WrongType(classcastexception6, "x", -2, obj6);
        }
        if (obj6 != boolean1)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        l = 1 & k + 1;
        if (l != 0)
        {
            return obj7;
        }
        if (l != 0)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static boolean isPregexpCharWord(Object obj)
    {
        Char char1;
        boolean flag;
        try
        {
            char1 = (Char)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "char-alphabetic?", 1, obj);
        }
        flag = unicode.isCharAlphabetic(char1);
        if (!flag)
        {
            Char char2;
            try
            {
                char2 = (Char)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "char-numeric?", 1, obj);
            }
            flag = unicode.isCharNumeric(char2);
            if (!flag)
            {
                Char char3;
                try
                {
                    char3 = (Char)obj;
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "char=?", 1, obj);
                }
                return characters.isChar$Eq(char3, Lit84);
            }
        }
        return flag;
    }

    public static Object isPregexpCheckIfInCharClass(Object obj, Object obj1)
    {
        if (Scheme.isEqv.apply2(obj1, Lit14) != Boolean.FALSE)
        {
            Object aobj[];
            Char char1;
            boolean flag;
            Char char2;
            boolean flag1;
            Char char3;
            boolean flag2;
            Char char4;
            boolean flag3;
            Char char5;
            boolean flag4;
            Char char6;
            boolean flag5;
            Char char7;
            Char char8;
            boolean flag6;
            Char char9;
            boolean flag7;
            Char char10;
            Char char11;
            Char char12;
            Char char13;
            int i;
            boolean flag8;
            Char char14;
            int j;
            Char char15;
            int k;
            Char char16;
            Char char17;
            Char char18;
            Char char19;
            int l;
            boolean flag9;
            Char char20;
            Char char21;
            Char char22;
            Char char23;
            boolean flag10;
            Char char24;
            Char char25;
            Char char26;
            Char char27;
            Char char28;
            boolean flag11;
            Char char29;
            Char char30;
            try
            {
                char30 = (Char)obj;
            }
            catch (ClassCastException classcastexception29)
            {
                throw new WrongType(classcastexception29, "char=?", 1, obj);
            }
            if (characters.isChar$Eq(char30, Lit24))
            {
                return Boolean.FALSE;
            } else
            {
                return Boolean.TRUE;
            }
        }
        if (Scheme.isEqv.apply2(obj1, Lit85) != Boolean.FALSE)
        {
            try
            {
                char28 = (Char)obj;
            }
            catch (ClassCastException classcastexception27)
            {
                throw new WrongType(classcastexception27, "char-alphabetic?", 1, obj);
            }
            flag11 = unicode.isCharAlphabetic(char28);
            if (flag11)
            {
                if (flag11)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            try
            {
                char29 = (Char)obj;
            }
            catch (ClassCastException classcastexception28)
            {
                throw new WrongType(classcastexception28, "char-numeric?", 1, obj);
            }
            if (unicode.isCharNumeric(char29))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (Scheme.isEqv.apply2(obj1, Lit86) != Boolean.FALSE)
        {
            try
            {
                char27 = (Char)obj;
            }
            catch (ClassCastException classcastexception26)
            {
                throw new WrongType(classcastexception26, "char-alphabetic?", 1, obj);
            }
            if (unicode.isCharAlphabetic(char27))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (Scheme.isEqv.apply2(obj1, Lit87) != Boolean.FALSE)
        {
            try
            {
                char26 = (Char)obj;
            }
            catch (ClassCastException classcastexception25)
            {
                throw new WrongType(classcastexception25, "char->integer", 1, obj);
            }
            if (characters.char$To$Integer(char26) < 128)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (Scheme.isEqv.apply2(obj1, Lit88) != Boolean.FALSE)
        {
            Object obj2;
            try
            {
                char23 = (Char)obj;
            }
            catch (ClassCastException classcastexception22)
            {
                throw new WrongType(classcastexception22, "char=?", 1, obj);
            }
            flag10 = characters.isChar$Eq(char23, Lit3);
            if (flag10)
            {
                if (flag10)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            try
            {
                char24 = (Char)obj;
            }
            catch (ClassCastException classcastexception23)
            {
                throw new WrongType(classcastexception23, "char=?", 1, obj);
            }
            obj2 = $Stpregexp$Mntab$Mnchar$St;
            try
            {
                char25 = (Char)obj2;
            }
            catch (ClassCastException classcastexception24)
            {
                throw new WrongType(classcastexception24, "char=?", 2, obj2);
            }
            if (characters.isChar$Eq(char24, char25))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (Scheme.isEqv.apply2(obj1, Lit89) != Boolean.FALSE)
        {
            try
            {
                char22 = (Char)obj;
            }
            catch (ClassCastException classcastexception21)
            {
                throw new WrongType(classcastexception21, "char->integer", 1, obj);
            }
            if (characters.char$To$Integer(char22) < 32)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (Scheme.isEqv.apply2(obj1, Lit30) != Boolean.FALSE)
        {
            try
            {
                char21 = (Char)obj;
            }
            catch (ClassCastException classcastexception20)
            {
                throw new WrongType(classcastexception20, "char-numeric?", 1, obj);
            }
            if (unicode.isCharNumeric(char21))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (Scheme.isEqv.apply2(obj1, Lit90) != Boolean.FALSE)
        {
            try
            {
                char19 = (Char)obj;
            }
            catch (ClassCastException classcastexception18)
            {
                throw new WrongType(classcastexception18, "char->integer", 1, obj);
            }
            l = characters.char$To$Integer(char19);
            flag9 = false;
            if (l >= 32)
            {
                flag9 = true;
            }
            if (flag9)
            {
                try
                {
                    char20 = (Char)obj;
                }
                catch (ClassCastException classcastexception19)
                {
                    throw new WrongType(classcastexception19, "char-whitespace?", 1, obj);
                }
                if (unicode.isCharWhitespace(char20))
                {
                    return Boolean.FALSE;
                } else
                {
                    return Boolean.TRUE;
                }
            }
            if (flag9)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (Scheme.isEqv.apply2(obj1, Lit91) != Boolean.FALSE)
        {
            try
            {
                char18 = (Char)obj;
            }
            catch (ClassCastException classcastexception17)
            {
                throw new WrongType(classcastexception17, "char-lower-case?", 1, obj);
            }
            if (unicode.isCharLowerCase(char18))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (Scheme.isEqv.apply2(obj1, Lit92) != Boolean.FALSE)
        {
            try
            {
                char17 = (Char)obj;
            }
            catch (ClassCastException classcastexception16)
            {
                throw new WrongType(classcastexception16, "char->integer", 1, obj);
            }
            if (characters.char$To$Integer(char17) >= 32)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (Scheme.isEqv.apply2(obj1, Lit93) != Boolean.FALSE)
        {
            try
            {
                char13 = (Char)obj;
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "char->integer", 1, obj);
            }
            i = characters.char$To$Integer(char13);
            flag8 = false;
            if (i >= 32)
            {
                flag8 = true;
            }
            if (flag8)
            {
                try
                {
                    char14 = (Char)obj;
                }
                catch (ClassCastException classcastexception13)
                {
                    throw new WrongType(classcastexception13, "char-whitespace?", 1, obj);
                }
                j = 1 & 1 + unicode.isCharWhitespace(char14);
                if (j != 0)
                {
                    try
                    {
                        char15 = (Char)obj;
                    }
                    catch (ClassCastException classcastexception14)
                    {
                        throw new WrongType(classcastexception14, "char-alphabetic?", 1, obj);
                    }
                    k = 1 & 1 + unicode.isCharAlphabetic(char15);
                    if (k != 0)
                    {
                        try
                        {
                            char16 = (Char)obj;
                        }
                        catch (ClassCastException classcastexception15)
                        {
                            throw new WrongType(classcastexception15, "char-numeric?", 1, obj);
                        }
                        if (unicode.isCharNumeric(char16))
                        {
                            return Boolean.FALSE;
                        } else
                        {
                            return Boolean.TRUE;
                        }
                    }
                    if (k != 0)
                    {
                        return Boolean.TRUE;
                    } else
                    {
                        return Boolean.FALSE;
                    }
                }
                if (j != 0)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            if (flag8)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (Scheme.isEqv.apply2(obj1, Lit36) != Boolean.FALSE)
        {
            try
            {
                char12 = (Char)obj;
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "char-whitespace?", 1, obj);
            }
            if (unicode.isCharWhitespace(char12))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (Scheme.isEqv.apply2(obj1, Lit94) != Boolean.FALSE)
        {
            try
            {
                char11 = (Char)obj;
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "char-upper-case?", 1, obj);
            }
            if (unicode.isCharUpperCase(char11))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (Scheme.isEqv.apply2(obj1, Lit41) != Boolean.FALSE)
        {
            try
            {
                char8 = (Char)obj;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "char-alphabetic?", 1, obj);
            }
            flag6 = unicode.isCharAlphabetic(char8);
            if (flag6)
            {
                if (flag6)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            try
            {
                char9 = (Char)obj;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "char-numeric?", 1, obj);
            }
            flag7 = unicode.isCharNumeric(char9);
            if (flag7)
            {
                if (flag7)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            try
            {
                char10 = (Char)obj;
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "char=?", 1, obj);
            }
            if (characters.isChar$Eq(char10, Lit84))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (Scheme.isEqv.apply2(obj1, Lit95) != Boolean.FALSE)
        {
            try
            {
                char1 = (Char)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "char-numeric?", 1, obj);
            }
            flag = unicode.isCharNumeric(char1);
            if (flag)
            {
                if (flag)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            try
            {
                char2 = (Char)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "char-ci=?", 1, obj);
            }
            flag1 = unicode.isCharCi$Eq(char2, Lit2);
            if (flag1)
            {
                if (flag1)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            try
            {
                char3 = (Char)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "char-ci=?", 1, obj);
            }
            flag2 = unicode.isCharCi$Eq(char3, Lit25);
            if (flag2)
            {
                if (flag2)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            try
            {
                char4 = (Char)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "char-ci=?", 1, obj);
            }
            flag3 = unicode.isCharCi$Eq(char4, Lit96);
            if (flag3)
            {
                if (flag3)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            try
            {
                char5 = (Char)obj;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "char-ci=?", 1, obj);
            }
            flag4 = unicode.isCharCi$Eq(char5, Lit29);
            if (flag4)
            {
                if (flag4)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            try
            {
                char6 = (Char)obj;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "char-ci=?", 1, obj);
            }
            flag5 = unicode.isCharCi$Eq(char6, Lit97);
            if (flag5)
            {
                if (flag5)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            try
            {
                char7 = (Char)obj;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "char-ci=?", 1, obj);
            }
            if (unicode.isCharCi$Eq(char7, Lit98))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        } else
        {
            aobj = new Object[1];
            aobj[0] = Lit99;
            return pregexpError$V(aobj);
        }
    }

    public static Object lambda1sub(Object obj)
    {
        if (lists.isPair(obj))
        {
            Object obj1 = lists.car.apply1(obj);
            Object obj2 = lambda1sub(lists.cdr.apply1(obj));
            if (Scheme.isEqv.apply2(obj1, Lit100) != Boolean.FALSE)
            {
                return lists.cons(lists.cons(obj, Boolean.FALSE), obj2);
            } else
            {
                Object aobj[] = new Object[2];
                aobj[0] = lambda1sub(obj1);
                aobj[1] = obj2;
                return append.append$V(aobj);
            }
        } else
        {
            return LList.Empty;
        }
    }

    public static Pair pregexp(Object obj)
    {
        $Stpregexp$Mnspace$Mnsensitive$Qu$St = Boolean.TRUE;
        SimpleSymbol simplesymbol = Lit100;
        gnu.expr.GenericProc genericproc = lists.car;
        IntNum intnum = Lit73;
        CharSequence charsequence;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj);
        }
        return LList.list2(simplesymbol, genericproc.apply1(pregexpReadPattern(obj, intnum, Integer.valueOf(strings.stringLength(charsequence)))));
    }

    public static Object pregexpError$V(Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        ports.display("Error:");
        Object obj = llist;
        do
        {
            if (obj == LList.Empty)
            {
                ports.newline();
                return misc.error$V("pregexp-error", new Object[0]);
            }
            Pair pair;
            Object obj1;
            try
            {
                pair = (Pair)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "arg0", -2, obj);
            }
            obj1 = pair.getCar();
            ports.display(Lit3);
            ports.write(obj1);
            obj = pair.getCdr();
        } while (true);
    }

    public static Object pregexpInvertCharList(Object obj)
    {
        Object obj1 = lists.car.apply1(obj);
        Pair pair;
        try
        {
            pair = (Pair)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "set-car!", 1, obj1);
        }
        lists.setCar$Ex(pair, Lit79);
        return obj;
    }

    public static Object pregexpListRef(Object obj, Object obj1)
    {
        Object obj2 = Lit73;
        do
        {
            if (lists.isNull(obj))
            {
                return Boolean.FALSE;
            }
            if (Scheme.numEqu.apply2(obj2, obj1) != Boolean.FALSE)
            {
                return lists.car.apply1(obj);
            }
            obj = lists.cdr.apply1(obj);
            obj2 = AddOp.$Pl.apply2(obj2, Lit8);
        } while (true);
    }

    public static Object pregexpMakeBackrefList(Object obj)
    {
        return lambda1sub(obj);
    }

    public static Object pregexpMatch$V(Object obj, Object obj1, Object aobj[])
    {
        Object obj2;
        LList llist = LList.makeList(aobj, 0);
        obj2 = Scheme.apply.apply4(pregexp$Mnmatch$Mnpositions, obj, obj1, llist);
        if (obj2 == Boolean.FALSE) goto _L2; else goto _L1
_L1:
        Object obj3;
        Object obj4;
        obj3 = LList.Empty;
        obj4 = obj2;
_L6:
        if (obj4 != LList.Empty) goto _L4; else goto _L3
_L3:
        obj2 = LList.reverseInPlace(obj3);
_L2:
        return obj2;
_L4:
        Pair pair;
        Object obj5;
        Object obj6;
        try
        {
            pair = (Pair)obj4;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "arg0", -2, obj4);
        }
        obj5 = pair.getCdr();
        obj6 = pair.getCar();
        if (obj6 != Boolean.FALSE)
        {
            CharSequence charsequence;
            Object obj7;
            int i;
            Object obj8;
            int j;
            try
            {
                charsequence = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "substring", 1, obj1);
            }
            obj7 = lists.car.apply1(obj6);
            try
            {
                i = ((Number)obj7).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "substring", 2, obj7);
            }
            obj8 = lists.cdr.apply1(obj6);
            try
            {
                j = ((Number)obj8).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "substring", 3, obj8);
            }
            obj6 = strings.substring(charsequence, i, j);
        }
        obj3 = Pair.make(obj6, obj3);
        obj4 = obj5;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static Object pregexpMatchPositions$V(Object obj, Object obj1, Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        CharSequence charsequence;
        int i;
        Object obj2;
        Object obj4;
        Object obj5;
        if (strings.isString(obj))
        {
            obj = pregexp(obj);
        } else
        if (!lists.isPair(obj))
        {
            Object aobj1[] = new Object[3];
            aobj1[0] = Lit114;
            aobj1[1] = Lit115;
            aobj1[2] = obj;
            pregexpError$V(aobj1);
        }
        try
        {
            charsequence = (CharSequence)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj1);
        }
        i = strings.stringLength(charsequence);
        if (lists.isNull(llist))
        {
            obj2 = Lit73;
        } else
        {
            obj2 = lists.car.apply1(llist);
            Object obj3 = lists.cdr.apply1(llist);
            try
            {
                llist = (LList)obj3;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "opt-args", -2, obj3);
            }
        }
        if (lists.isNull(llist))
        {
            obj4 = Integer.valueOf(i);
        } else
        {
            obj4 = lists.car.apply1(llist);
        }
        obj5 = obj2;
        do
        {
            Object obj6 = Scheme.numLEq.apply2(obj5, obj4);
            boolean flag;
            try
            {
                flag = ((Boolean)obj6).booleanValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "x", -2, obj6);
            }
            if (flag)
            {
                Integer integer = Integer.valueOf(i);
                Object obj7 = pregexpMatchPositionsAux(obj, obj1, integer, obj2, obj4, obj5);
                if (obj7 != Boolean.FALSE)
                {
                    return obj7;
                }
                obj5 = AddOp.$Pl.apply2(obj5, Lit8);
            } else
            if (flag)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        } while (true);
    }

    public static Object pregexpMatchPositionsAux(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
    {
        frame frame6 = new frame();
        frame6.s = obj1;
        frame6.sn = obj2;
        frame6.start = obj3;
        frame6.n = obj4;
        Procedure procedure = frame6.identity;
        Object obj6 = pregexpMakeBackrefList(obj);
        frame6.Qu = Boolean.TRUE;
        frame6.backrefs = obj6;
        frame6.identity = procedure;
        frame6.lambda3sub(obj, obj5, frame6.identity, lambda$Fn1);
        Object obj7 = frame6.backrefs;
        Object obj8 = LList.Empty;
        do
        {
            if (obj7 == LList.Empty)
            {
                LList llist = LList.reverseInPlace(obj8);
                Object obj10 = lists.car.apply1(llist);
                Pair pair;
                Object obj9;
                if (obj10 != Boolean.FALSE)
                {
                    return llist;
                } else
                {
                    return obj10;
                }
            }
            try
            {
                pair = (Pair)obj7;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "arg0", -2, obj7);
            }
            obj9 = pair.getCdr();
            obj8 = Pair.make(lists.cdr.apply1(pair.getCar()), obj8);
            obj7 = obj9;
        } while (true);
    }

    public static Object pregexpQuote(Object obj)
    {
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
        obj1 = Integer.valueOf(-1 + strings.stringLength(charsequence));
        obj2 = LList.Empty;
        do
        {
            if (Scheme.numLss.apply2(obj1, Lit73) != Boolean.FALSE)
            {
                Object obj3;
                CharSequence charsequence1;
                int i;
                char c;
                Pair pair;
                LList llist;
                try
                {
                    llist = (LList)obj2;
                }
                catch (ClassCastException classcastexception3)
                {
                    throw new WrongType(classcastexception3, "list->string", 1, obj2);
                }
                return strings.list$To$String(llist);
            }
            obj3 = AddOp.$Mn.apply2(obj1, Lit8);
            try
            {
                charsequence1 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 1, obj);
            }
            try
            {
                i = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 2, obj1);
            }
            c = strings.stringRef(charsequence1, i);
            if (lists.memv(Char.make(c), Lit116) != Boolean.FALSE)
            {
                pair = lists.cons(Lit19, lists.cons(Char.make(c), obj2));
            } else
            {
                pair = lists.cons(Char.make(c), obj2);
            }
            obj2 = pair;
            obj1 = obj3;
        } while (true);
    }

    public static Object pregexpReadBranch(Object obj, Object obj1, Object obj2)
    {
        Object obj3 = LList.Empty;
        do
        {
            if (Scheme.numGEq.apply2(obj1, obj2) != Boolean.FALSE)
            {
                return LList.list2(lists.cons(Lit5, pregexpReverse$Ex(obj3)), obj1);
            }
            CharSequence charsequence;
            int i;
            char c;
            boolean flag;
            Object obj4;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-ref", 1, obj);
            }
            try
            {
                i = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 2, obj1);
            }
            c = strings.stringRef(charsequence, i);
            flag = characters.isChar$Eq(Char.make(c), Lit7);
            if (flag ? flag : characters.isChar$Eq(Char.make(c), Lit6))
            {
                return LList.list2(lists.cons(Lit5, pregexpReverse$Ex(obj3)), obj1);
            }
            obj4 = pregexpReadPiece(obj, obj1, obj2);
            obj3 = lists.cons(lists.car.apply1(obj4), obj3);
            obj1 = lists.cadr.apply1(obj4);
        } while (true);
    }

    public static Object pregexpReadCharList(Object obj, Object obj1, Object obj2)
    {
        Object obj3 = LList.Empty;
_L10:
        Object obj6;
        if (Scheme.numGEq.apply2(obj1, obj2) != Boolean.FALSE)
        {
            Object aobj1[] = new Object[2];
            aobj1[0] = Lit80;
            aobj1[1] = Lit81;
            return pregexpError$V(aobj1);
        }
        CharSequence charsequence;
        int i;
        char c;
        CharSequence charsequence1;
        int j;
        Object obj5;
        boolean flag;
        boolean flag1;
        Object obj8;
        SimpleSymbol simplesymbol;
        CharSequence charsequence2;
        int k;
        CharSequence charsequence3;
        int l;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-ref", 1, obj);
        }
        try
        {
            i = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 2, obj1);
        }
        c = strings.stringRef(charsequence, i);
        if (Scheme.isEqv.apply2(Char.make(c), Lit46) != Boolean.FALSE)
        {
            if (lists.isNull(obj3))
            {
                obj3 = lists.cons(Char.make(c), obj3);
                obj1 = AddOp.$Pl.apply2(obj1, Lit8);
            } else
            {
                return LList.list2(lists.cons(Lit82, pregexpReverse$Ex(obj3)), AddOp.$Pl.apply2(obj1, Lit8));
            }
            continue; /* Loop/switch isn't completed */
        }
        if (Scheme.isEqv.apply2(Char.make(c), Lit19) != Boolean.FALSE)
        {
            Object obj10 = pregexpReadEscapedChar(obj, obj1, obj2);
            if (obj10 != Boolean.FALSE)
            {
                obj3 = lists.cons(lists.car.apply1(obj10), obj3);
                obj1 = lists.cadr.apply1(obj10);
            } else
            {
                Object aobj[] = new Object[2];
                aobj[0] = Lit80;
                aobj[1] = Lit22;
                return pregexpError$V(aobj);
            }
            continue; /* Loop/switch isn't completed */
        }
        if (Scheme.isEqv.apply2(Char.make(c), Lit58) == Boolean.FALSE) goto _L2; else goto _L1
_L1:
        flag = lists.isNull(obj3);
        if (!flag) goto _L4; else goto _L3
_L3:
        if (!flag) goto _L6; else goto _L5
_L5:
        obj3 = lists.cons(Char.make(c), obj3);
        obj1 = AddOp.$Pl.apply2(obj1, Lit8);
        continue; /* Loop/switch isn't completed */
_L4:
        obj6 = AddOp.$Pl.apply2(obj1, Lit8);
        Object obj7 = Scheme.numLss.apply2(obj6, obj2);
        try
        {
            flag1 = ((Boolean)obj7).booleanValue();
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "x", -2, obj7);
        }
        if (!flag1) goto _L8; else goto _L7
_L7:
        try
        {
            charsequence3 = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception7)
        {
            throw new WrongType(classcastexception7, "string-ref", 1, obj);
        }
        try
        {
            l = ((Number)obj6).intValue();
        }
        catch (ClassCastException classcastexception8)
        {
            throw new WrongType(classcastexception8, "string-ref", 2, obj6);
        }
        if (characters.isChar$Eq(Char.make(strings.stringRef(charsequence3, l)), Lit46)) goto _L5; else goto _L6
_L6:
        obj8 = lists.car.apply1(obj3);
        if (characters.isChar(obj8))
        {
            simplesymbol = Lit83;
            Object obj9;
            try
            {
                charsequence2 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "string-ref", 1, obj);
            }
            obj9 = AddOp.$Pl.apply2(obj1, Lit8);
            try
            {
                k = ((Number)obj9).intValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "string-ref", 2, obj9);
            }
            obj3 = lists.cons(LList.list3(simplesymbol, obj8, Char.make(strings.stringRef(charsequence2, k))), lists.cdr.apply1(obj3));
            obj1 = AddOp.$Pl.apply2(obj1, Lit16);
        } else
        {
            obj3 = lists.cons(Char.make(c), obj3);
            obj1 = AddOp.$Pl.apply2(obj1, Lit8);
        }
        continue; /* Loop/switch isn't completed */
_L8:
        if (!flag1) goto _L6; else goto _L5
_L2:
        if (Scheme.isEqv.apply2(Char.make(c), Lit15) != Boolean.FALSE)
        {
            Object obj4;
            try
            {
                charsequence1 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 1, obj);
            }
            obj4 = AddOp.$Pl.apply2(obj1, Lit8);
            try
            {
                j = ((Number)obj4).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "string-ref", 2, obj4);
            }
            if (characters.isChar$Eq(Char.make(strings.stringRef(charsequence1, j)), Lit44))
            {
                obj5 = pregexpReadPosixCharClass(obj, AddOp.$Pl.apply2(obj1, Lit16), obj2);
                obj3 = lists.cons(lists.car.apply1(obj5), obj3);
                obj1 = lists.cadr.apply1(obj5);
            } else
            {
                obj3 = lists.cons(Char.make(c), obj3);
                obj1 = AddOp.$Pl.apply2(obj1, Lit8);
            }
        } else
        {
            obj3 = lists.cons(Char.make(c), obj3);
            obj1 = AddOp.$Pl.apply2(obj1, Lit8);
        }
        if (true) goto _L10; else goto _L9
_L9:
    }

    public static Object pregexpReadClusterType(Object obj, Object obj1, Object obj2)
    {
        CharSequence charsequence;
        int i;
        char c;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-ref", 1, obj);
        }
        try
        {
            i = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 2, obj1);
        }
        c = strings.stringRef(charsequence, i);
        if (Scheme.isEqv.apply2(Char.make(c), Lit47) != Boolean.FALSE)
        {
            Object obj3 = AddOp.$Pl.apply2(obj1, Lit8);
            CharSequence charsequence1;
            int j;
            char c1;
            try
            {
                charsequence1 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 1, obj);
            }
            try
            {
                j = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "string-ref", 2, obj3);
            }
            c1 = strings.stringRef(charsequence1, j);
            if (Scheme.isEqv.apply2(Char.make(c1), Lit44) != Boolean.FALSE)
            {
                return LList.list2(LList.Empty, AddOp.$Pl.apply2(obj3, Lit8));
            }
            if (Scheme.isEqv.apply2(Char.make(c1), Lit48) != Boolean.FALSE)
            {
                return LList.list2(Lit49, AddOp.$Pl.apply2(obj3, Lit8));
            }
            if (Scheme.isEqv.apply2(Char.make(c1), Lit50) != Boolean.FALSE)
            {
                return LList.list2(Lit51, AddOp.$Pl.apply2(obj3, Lit8));
            }
            if (Scheme.isEqv.apply2(Char.make(c1), Lit52) != Boolean.FALSE)
            {
                return LList.list2(Lit53, AddOp.$Pl.apply2(obj3, Lit8));
            }
            if (Scheme.isEqv.apply2(Char.make(c1), Lit54) != Boolean.FALSE)
            {
                Object obj4;
                Boolean boolean1;
                CharSequence charsequence2;
                int k;
                char c2;
                Object aobj[];
                SimpleSymbol simplesymbol;
                CharSequence charsequence3;
                Object obj5;
                int l;
                char c3;
                Object obj6;
                try
                {
                    charsequence3 = (CharSequence)obj;
                }
                catch (ClassCastException classcastexception6)
                {
                    throw new WrongType(classcastexception6, "string-ref", 1, obj);
                }
                obj5 = AddOp.$Pl.apply2(obj3, Lit8);
                try
                {
                    l = ((Number)obj5).intValue();
                }
                catch (ClassCastException classcastexception7)
                {
                    throw new WrongType(classcastexception7, "string-ref", 2, obj5);
                }
                c3 = strings.stringRef(charsequence3, l);
                if (Scheme.isEqv.apply2(Char.make(c3), Lit48) != Boolean.FALSE)
                {
                    obj6 = Lit55;
                } else
                if (Scheme.isEqv.apply2(Char.make(c3), Lit50) != Boolean.FALSE)
                {
                    obj6 = Lit56;
                } else
                {
                    Object aobj1[] = new Object[1];
                    aobj1[0] = Lit57;
                    obj6 = pregexpError$V(aobj1);
                }
                return LList.list2(obj6, AddOp.$Pl.apply2(obj3, Lit16));
            }
            obj4 = LList.Empty;
            boolean1 = Boolean.FALSE;
            do
            {
                try
                {
                    charsequence2 = (CharSequence)obj;
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "string-ref", 1, obj);
                }
                try
                {
                    k = ((Number)obj3).intValue();
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "string-ref", 2, obj3);
                }
                c2 = strings.stringRef(charsequence2, k);
                if (Scheme.isEqv.apply2(Char.make(c2), Lit58) != Boolean.FALSE)
                {
                    obj3 = AddOp.$Pl.apply2(obj3, Lit8);
                    boolean1 = Boolean.TRUE;
                } else
                if (Scheme.isEqv.apply2(Char.make(c2), Lit59) != Boolean.FALSE)
                {
                    obj3 = AddOp.$Pl.apply2(obj3, Lit8);
                    if (boolean1 != Boolean.FALSE)
                    {
                        simplesymbol = Lit60;
                    } else
                    {
                        simplesymbol = Lit61;
                    }
                    obj4 = lists.cons(simplesymbol, obj4);
                    boolean1 = Boolean.FALSE;
                } else
                if (Scheme.isEqv.apply2(Char.make(c2), Lit62) != Boolean.FALSE)
                {
                    $Stpregexp$Mnspace$Mnsensitive$Qu$St = boolean1;
                    obj3 = AddOp.$Pl.apply2(obj3, Lit8);
                    boolean1 = Boolean.FALSE;
                } else
                if (Scheme.isEqv.apply2(Char.make(c2), Lit44) != Boolean.FALSE)
                {
                    return LList.list2(obj4, AddOp.$Pl.apply2(obj3, Lit8));
                } else
                {
                    aobj = new Object[1];
                    aobj[0] = Lit57;
                    return pregexpError$V(aobj);
                }
            } while (true);
        } else
        {
            return LList.list2(Lit63, obj1);
        }
    }

    public static Object pregexpReadEscapedChar(Object obj, Object obj1, Object obj2)
    {
        Object obj3 = Scheme.numLss.apply2(AddOp.$Pl.apply2(obj1, Lit8), obj2);
        boolean flag;
        try
        {
            flag = ((Boolean)obj3).booleanValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "x", -2, obj3);
        }
        if (flag)
        {
            CharSequence charsequence;
            Object obj4;
            int i;
            char c;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 1, obj);
            }
            obj4 = AddOp.$Pl.apply2(obj1, Lit8);
            try
            {
                i = ((Number)obj4).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 2, obj4);
            }
            c = strings.stringRef(charsequence, i);
            if (Scheme.isEqv.apply2(Char.make(c), Lit25) != Boolean.FALSE)
            {
                return LList.list2(Lit26, AddOp.$Pl.apply2(obj1, Lit16));
            }
            if (Scheme.isEqv.apply2(Char.make(c), Lit27) != Boolean.FALSE)
            {
                return LList.list2(Lit28, AddOp.$Pl.apply2(obj1, Lit16));
            }
            if (Scheme.isEqv.apply2(Char.make(c), Lit29) != Boolean.FALSE)
            {
                return LList.list2(Lit30, AddOp.$Pl.apply2(obj1, Lit16));
            }
            if (Scheme.isEqv.apply2(Char.make(c), Lit31) != Boolean.FALSE)
            {
                return LList.list2(Lit32, AddOp.$Pl.apply2(obj1, Lit16));
            }
            if (Scheme.isEqv.apply2(Char.make(c), Lit33) != Boolean.FALSE)
            {
                return LList.list2(Lit24, AddOp.$Pl.apply2(obj1, Lit16));
            }
            if (Scheme.isEqv.apply2(Char.make(c), Lit34) != Boolean.FALSE)
            {
                return LList.list2($Stpregexp$Mnreturn$Mnchar$St, AddOp.$Pl.apply2(obj1, Lit16));
            }
            if (Scheme.isEqv.apply2(Char.make(c), Lit35) != Boolean.FALSE)
            {
                return LList.list2(Lit36, AddOp.$Pl.apply2(obj1, Lit16));
            }
            if (Scheme.isEqv.apply2(Char.make(c), Lit37) != Boolean.FALSE)
            {
                return LList.list2(Lit38, AddOp.$Pl.apply2(obj1, Lit16));
            }
            if (Scheme.isEqv.apply2(Char.make(c), Lit39) != Boolean.FALSE)
            {
                return LList.list2($Stpregexp$Mntab$Mnchar$St, AddOp.$Pl.apply2(obj1, Lit16));
            }
            if (Scheme.isEqv.apply2(Char.make(c), Lit40) != Boolean.FALSE)
            {
                return LList.list2(Lit41, AddOp.$Pl.apply2(obj1, Lit16));
            }
            if (Scheme.isEqv.apply2(Char.make(c), Lit42) != Boolean.FALSE)
            {
                return LList.list2(Lit43, AddOp.$Pl.apply2(obj1, Lit16));
            } else
            {
                return LList.list2(Char.make(c), AddOp.$Pl.apply2(obj1, Lit16));
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

    public static Object pregexpReadEscapedNumber(Object obj, Object obj1, Object obj2)
    {
        Object obj3 = Scheme.numLss.apply2(AddOp.$Pl.apply2(obj1, Lit8), obj2);
        boolean flag;
        try
        {
            flag = ((Boolean)obj3).booleanValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "x", -2, obj3);
        }
        if (flag)
        {
            CharSequence charsequence;
            Object obj4;
            int i;
            char c;
            boolean flag1;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 1, obj);
            }
            obj4 = AddOp.$Pl.apply2(obj1, Lit8);
            try
            {
                i = ((Number)obj4).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 2, obj4);
            }
            c = strings.stringRef(charsequence, i);
            flag1 = unicode.isCharNumeric(Char.make(c));
            if (flag1)
            {
                Object obj5 = AddOp.$Pl.apply2(obj1, Lit16);
                Pair pair = LList.list1(Char.make(c));
                do
                {
                    if (Scheme.numGEq.apply2(obj5, obj2) != Boolean.FALSE)
                    {
                        Object obj7 = pregexpReverse$Ex(pair);
                        CharSequence charsequence1;
                        int j;
                        char c1;
                        LList llist;
                        LList llist1;
                        try
                        {
                            llist1 = (LList)obj7;
                        }
                        catch (ClassCastException classcastexception6)
                        {
                            throw new WrongType(classcastexception6, "list->string", 1, obj7);
                        }
                        return LList.list2(numbers.string$To$Number(strings.list$To$String(llist1)), obj5);
                    }
                    try
                    {
                        charsequence1 = (CharSequence)obj;
                    }
                    catch (ClassCastException classcastexception3)
                    {
                        throw new WrongType(classcastexception3, "string-ref", 1, obj);
                    }
                    try
                    {
                        j = ((Number)obj5).intValue();
                    }
                    catch (ClassCastException classcastexception4)
                    {
                        throw new WrongType(classcastexception4, "string-ref", 2, obj5);
                    }
                    c1 = strings.stringRef(charsequence1, j);
                    if (unicode.isCharNumeric(Char.make(c1)))
                    {
                        obj5 = AddOp.$Pl.apply2(obj5, Lit8);
                        pair = lists.cons(Char.make(c1), pair);
                    } else
                    {
                        Object obj6 = pregexpReverse$Ex(pair);
                        try
                        {
                            llist = (LList)obj6;
                        }
                        catch (ClassCastException classcastexception5)
                        {
                            throw new WrongType(classcastexception5, "list->string", 1, obj6);
                        }
                        return LList.list2(numbers.string$To$Number(strings.list$To$String(llist)), obj5);
                    }
                } while (true);
            }
            if (flag1)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
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

    public static Object pregexpReadNums(Object obj, Object obj1, Object obj2)
    {
        Object obj3 = LList.Empty;
        Object obj4 = LList.Empty;
        IntNum intnum = Lit8;
        Object obj5 = obj1;
        do
        {
            if (Scheme.numGEq.apply2(obj5, obj2) != Boolean.FALSE)
            {
                Object aobj[] = new Object[1];
                aobj[0] = Lit76;
                pregexpError$V(aobj);
            }
            CharSequence charsequence;
            int i;
            char c;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-ref", 1, obj);
            }
            try
            {
                i = ((Number)obj5).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 2, obj5);
            }
            c = strings.stringRef(charsequence, i);
            if (unicode.isCharNumeric(Char.make(c)))
            {
                if (Scheme.numEqu.apply2(intnum, Lit8) != Boolean.FALSE)
                {
                    obj3 = lists.cons(Char.make(c), obj3);
                    Object obj12 = AddOp.$Pl.apply2(obj5, Lit8);
                    intnum = Lit8;
                    obj5 = obj12;
                } else
                {
                    obj4 = lists.cons(Char.make(c), obj4);
                    Object obj11 = AddOp.$Pl.apply2(obj5, Lit8);
                    intnum = Lit16;
                    obj5 = obj11;
                }
            } else
            {
                boolean flag = unicode.isCharWhitespace(Char.make(c));
                if (flag ? $Stpregexp$Mnspace$Mnsensitive$Qu$St == Boolean.FALSE : flag)
                {
                    obj5 = AddOp.$Pl.apply2(obj5, Lit8);
                } else
                {
                    boolean flag1 = characters.isChar$Eq(Char.make(c), Lit77);
                    if (flag1 ? Scheme.numEqu.apply2(intnum, Lit8) != Boolean.FALSE : flag1)
                    {
                        Object obj10 = AddOp.$Pl.apply2(obj5, Lit8);
                        intnum = Lit16;
                        obj5 = obj10;
                    } else
                    if (characters.isChar$Eq(Char.make(c), Lit78))
                    {
                        Object obj6 = pregexpReverse$Ex(obj3);
                        LList llist;
                        Object obj7;
                        Object obj8;
                        LList llist1;
                        Object obj9;
                        Boolean boolean1;
                        int j;
                        int k;
                        try
                        {
                            llist = (LList)obj6;
                        }
                        catch (ClassCastException classcastexception2)
                        {
                            throw new WrongType(classcastexception2, "list->string", 1, obj6);
                        }
                        obj7 = numbers.string$To$Number(strings.list$To$String(llist));
                        obj8 = pregexpReverse$Ex(obj4);
                        try
                        {
                            llist1 = (LList)obj8;
                        }
                        catch (ClassCastException classcastexception3)
                        {
                            throw new WrongType(classcastexception3, "list->string", 1, obj8);
                        }
                        obj9 = numbers.string$To$Number(strings.list$To$String(llist1));
                        try
                        {
                            boolean1 = Boolean.FALSE;
                        }
                        catch (ClassCastException classcastexception4)
                        {
                            throw new WrongType(classcastexception4, "x", -2, obj7);
                        }
                        if (obj7 != boolean1)
                        {
                            j = 1;
                        } else
                        {
                            j = 0;
                        }
                        k = 1 & j + 1;
                        if (k == 0 ? k != 0 : Scheme.numEqu.apply2(intnum, Lit8) != Boolean.FALSE)
                        {
                            return LList.list3(Lit73, Boolean.FALSE, obj5);
                        }
                        if (Scheme.numEqu.apply2(intnum, Lit8) != Boolean.FALSE)
                        {
                            return LList.list3(obj7, obj7, obj5);
                        } else
                        {
                            return LList.list3(obj7, obj9, obj5);
                        }
                    } else
                    {
                        return Boolean.FALSE;
                    }
                }
            }
        } while (true);
    }

    public static Object pregexpReadPattern(Object obj, Object obj1, Object obj2)
    {
        Object obj3;
        if (Scheme.numGEq.apply2(obj1, obj2) != Boolean.FALSE)
        {
            return LList.list2(LList.list2(Lit4, LList.list1(Lit5)), obj1);
        }
        obj3 = LList.Empty;
_L6:
        Object obj4 = Scheme.numGEq.apply2(obj1, obj2);
        boolean flag;
        CharSequence charsequence;
        int i;
        CharSequence charsequence1;
        int j;
        Object obj5;
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
        if (!flag) goto _L4; else goto _L3
_L3:
        return LList.list2(lists.cons(Lit4, pregexpReverse$Ex(obj3)), obj1);
_L2:
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 1, obj);
        }
        try
        {
            i = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "string-ref", 2, obj1);
        }
        if (characters.isChar$Eq(Char.make(strings.stringRef(charsequence, i)), Lit6)) goto _L3; else goto _L4
_L4:
        try
        {
            charsequence1 = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "string-ref", 1, obj);
        }
        try
        {
            j = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "string-ref", 2, obj1);
        }
        if (characters.isChar$Eq(Char.make(strings.stringRef(charsequence1, j)), Lit7))
        {
            obj1 = AddOp.$Pl.apply2(obj1, Lit8);
        }
        obj5 = pregexpReadBranch(obj, obj1, obj2);
        obj3 = lists.cons(lists.car.apply1(obj5), obj3);
        obj1 = lists.cadr.apply1(obj5);
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static Object pregexpReadPiece(Object obj, Object obj1, Object obj2)
    {
        CharSequence charsequence;
        int i;
        char c;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-ref", 1, obj);
        }
        try
        {
            i = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 2, obj1);
        }
        c = strings.stringRef(charsequence, i);
        if (Scheme.isEqv.apply2(Char.make(c), Lit9) != Boolean.FALSE)
        {
            return LList.list2(Lit10, AddOp.$Pl.apply2(obj1, Lit8));
        }
        if (Scheme.isEqv.apply2(Char.make(c), Lit11) != Boolean.FALSE)
        {
            return LList.list2(Lit12, AddOp.$Pl.apply2(obj1, Lit8));
        }
        if (Scheme.isEqv.apply2(Char.make(c), Lit13) != Boolean.FALSE)
        {
            return pregexpWrapQuantifierIfAny(LList.list2(Lit14, AddOp.$Pl.apply2(obj1, Lit8)), obj, obj2);
        }
        if (Scheme.isEqv.apply2(Char.make(c), Lit15) != Boolean.FALSE)
        {
            Object obj7 = AddOp.$Pl.apply2(obj1, Lit8);
            Object obj8 = Scheme.numLss.apply2(obj7, obj2);
            boolean flag;
            try
            {
                flag = ((Boolean)obj8).booleanValue();
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "x", -2, obj8);
            }
            if (flag)
            {
                Object obj3;
                int j;
                Boolean boolean1;
                CharSequence charsequence1;
                int k;
                char c1;
                Object obj4;
                Object obj5;
                Object aobj[];
                Object obj6;
                Object obj9;
                Object obj10;
                Object obj11;
                CharSequence charsequence2;
                int l;
                try
                {
                    charsequence2 = (CharSequence)obj;
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "string-ref", 1, obj);
                }
                try
                {
                    l = ((Number)obj7).intValue();
                }
                catch (ClassCastException classcastexception6)
                {
                    throw new WrongType(classcastexception6, "string-ref", 2, obj7);
                }
                obj9 = Char.make(strings.stringRef(charsequence2, l));
            } else
            if (flag)
            {
                obj9 = Boolean.TRUE;
            } else
            {
                obj9 = Boolean.FALSE;
            }
            if (Scheme.isEqv.apply2(obj9, Lit9) != Boolean.FALSE)
            {
                obj11 = pregexpReadCharList(obj, AddOp.$Pl.apply2(obj1, Lit16), obj2);
                obj10 = LList.list2(LList.list2(Lit17, lists.car.apply1(obj11)), lists.cadr.apply1(obj11));
            } else
            {
                obj10 = pregexpReadCharList(obj, obj7, obj2);
            }
            return pregexpWrapQuantifierIfAny(obj10, obj, obj2);
        }
        if (Scheme.isEqv.apply2(Char.make(c), Lit18) != Boolean.FALSE)
        {
            return pregexpWrapQuantifierIfAny(pregexpReadSubpattern(obj, AddOp.$Pl.apply2(obj1, Lit8), obj2), obj, obj2);
        }
        if (Scheme.isEqv.apply2(Char.make(c), Lit19) != Boolean.FALSE)
        {
            obj4 = pregexpReadEscapedNumber(obj, obj1, obj2);
            if (obj4 != Boolean.FALSE)
            {
                obj6 = LList.list2(LList.list2(Lit20, lists.car.apply1(obj4)), lists.cadr.apply1(obj4));
            } else
            {
                obj5 = pregexpReadEscapedChar(obj, obj1, obj2);
                if (obj5 != Boolean.FALSE)
                {
                    obj6 = LList.list2(lists.car.apply1(obj5), lists.cadr.apply1(obj5));
                } else
                {
                    aobj = new Object[2];
                    aobj[0] = Lit21;
                    aobj[1] = Lit22;
                    obj6 = pregexpError$V(aobj);
                }
            }
            return pregexpWrapQuantifierIfAny(obj6, obj, obj2);
        }
        obj3 = $Stpregexp$Mnspace$Mnsensitive$Qu$St;
        if (obj3 == Boolean.FALSE ? (j = 1 & 1 + unicode.isCharWhitespace(Char.make(c))) == 0 ? j != 0 : !characters.isChar$Eq(Char.make(c), Lit1) : obj3 != Boolean.FALSE)
        {
            return pregexpWrapQuantifierIfAny(LList.list2(Char.make(c), AddOp.$Pl.apply2(obj1, Lit8)), obj, obj2);
        }
        boolean1 = Boolean.FALSE;
        do
        {
            if (Scheme.numGEq.apply2(obj1, obj2) != Boolean.FALSE)
            {
                return LList.list2(Lit23, obj1);
            }
            try
            {
                charsequence1 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 1, obj);
            }
            try
            {
                k = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "string-ref", 2, obj1);
            }
            c1 = strings.stringRef(charsequence1, k);
            if (boolean1 != Boolean.FALSE)
            {
                obj1 = AddOp.$Pl.apply2(obj1, Lit8);
                if (characters.isChar$Eq(Char.make(c1), Lit24))
                {
                    boolean1 = Boolean.FALSE;
                } else
                {
                    boolean1 = Boolean.TRUE;
                }
            } else
            if (unicode.isCharWhitespace(Char.make(c1)))
            {
                obj1 = AddOp.$Pl.apply2(obj1, Lit8);
                boolean1 = Boolean.FALSE;
            } else
            if (characters.isChar$Eq(Char.make(c1), Lit1))
            {
                obj1 = AddOp.$Pl.apply2(obj1, Lit8);
                boolean1 = Boolean.TRUE;
            } else
            {
                return LList.list2(Lit23, obj1);
            }
        } while (true);
    }

    public static Object pregexpReadPosixCharClass(Object obj, Object obj1, Object obj2)
    {
        Object obj3;
        Boolean boolean1 = Boolean.FALSE;
        Pair pair = LList.list1(Lit44);
        do
        {
            if (Scheme.numGEq.apply2(obj1, obj2) != Boolean.FALSE)
            {
                Object aobj2[] = new Object[1];
                aobj2[0] = Lit45;
                return pregexpError$V(aobj2);
            }
            CharSequence charsequence;
            int i;
            char c;
            Object aobj[];
            boolean flag;
            CharSequence charsequence1;
            int j;
            LList llist;
            Object obj6;
            Object aobj1[];
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-ref", 1, obj);
            }
            try
            {
                i = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 2, obj1);
            }
            c = strings.stringRef(charsequence, i);
            if (characters.isChar$Eq(Char.make(c), Lit9))
            {
                boolean1 = Boolean.TRUE;
                obj1 = AddOp.$Pl.apply2(obj1, Lit8);
            } else
            {
label0:
                {
                    if (!unicode.isCharAlphabetic(Char.make(c)))
                    {
                        break label0;
                    }
                    obj1 = AddOp.$Pl.apply2(obj1, Lit8);
                    pair = lists.cons(Char.make(c), pair);
                }
            }
        } while (true);
        if (!characters.isChar$Eq(Char.make(c), Lit44))
        {
            break MISSING_BLOCK_LABEL_314;
        }
        obj3 = Scheme.numGEq.apply2(AddOp.$Pl.apply2(obj1, Lit8), obj2);
        try
        {
            flag = ((Boolean)obj3).booleanValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "x", -2, obj3);
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        if (!flag) goto _L4; else goto _L3
_L3:
        aobj1 = new Object[1];
        aobj1[0] = Lit45;
        return pregexpError$V(aobj1);
_L2:
        Object obj4;
        try
        {
            charsequence1 = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "string-ref", 1, obj);
        }
        obj4 = AddOp.$Pl.apply2(obj1, Lit8);
        try
        {
            j = ((Number)obj4).intValue();
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "string-ref", 2, obj4);
        }
        if (!characters.isChar$Eq(Char.make(strings.stringRef(charsequence1, j)), Lit46)) goto _L3; else goto _L4
_L4:
        Object obj5 = pregexpReverse$Ex(pair);
        try
        {
            llist = (LList)obj5;
        }
        catch (ClassCastException classcastexception5)
        {
            throw new WrongType(classcastexception5, "list->string", 1, obj5);
        }
        obj6 = misc.string$To$Symbol(strings.list$To$String(llist));
        if (boolean1 != Boolean.FALSE)
        {
            obj6 = LList.list2(Lit17, obj6);
        }
        return LList.list2(obj6, AddOp.$Pl.apply2(obj1, Lit16));
        aobj = new Object[1];
        aobj[0] = Lit45;
        return pregexpError$V(aobj);
    }

    public static Object pregexpReadSubpattern(Object obj, Object obj1, Object obj2)
    {
        Object obj8;
        Object obj3 = $Stpregexp$Mnspace$Mnsensitive$Qu$St;
        Object obj4 = pregexpReadClusterType(obj, obj1, obj2);
        Object obj5 = lists.car.apply1(obj4);
        Object obj6 = pregexpReadPattern(obj, lists.cadr.apply1(obj4), obj2);
        $Stpregexp$Mnspace$Mnsensitive$Qu$St = obj3;
        Object obj7 = lists.car.apply1(obj6);
        obj8 = lists.cadr.apply1(obj6);
        Object obj9 = Scheme.numLss.apply2(obj8, obj2);
        boolean flag;
        Object aobj[];
        Object obj10;
        CharSequence charsequence;
        int i;
        try
        {
            flag = ((Boolean)obj9).booleanValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "x", -2, obj9);
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 1, obj);
        }
        try
        {
            i = ((Number)obj8).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "string-ref", 2, obj8);
        }
        if (!characters.isChar$Eq(Char.make(strings.stringRef(charsequence, i)), Lit6)) goto _L4; else goto _L3
_L3:
        if (lists.isNull(obj5))
        {
            return LList.list2(obj7, AddOp.$Pl.apply2(obj8, Lit8));
        }
        obj10 = lists.cdr.apply1(obj5);
        obj7 = LList.list2(lists.car.apply1(obj5), obj7);
        obj5 = obj10;
        continue; /* Loop/switch isn't completed */
_L2:
        if (flag)
        {
            continue; /* Loop/switch isn't completed */
        }
_L4:
        aobj = new Object[1];
        aobj[0] = Lit64;
        return pregexpError$V(aobj);
        if (true) goto _L3; else goto _L5
_L5:
    }

    public static Object pregexpReplace(Object obj, Object obj1, Object obj2)
    {
        CharSequence charsequence;
        int i;
        Object aobj[];
        Object obj3;
        CharSequence charsequence1;
        int j;
        Object obj4;
        Object obj5;
        Object aobj1[];
        CharSequence charsequence2;
        int k;
        CharSequence charsequence3;
        int l;
        try
        {
            charsequence = (CharSequence)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj1);
        }
        i = strings.stringLength(charsequence);
        aobj = new Object[2];
        aobj[0] = Lit73;
        aobj[1] = Integer.valueOf(i);
        obj3 = pregexpMatchPositions$V(obj, obj1, aobj);
        if (obj3 == Boolean.FALSE)
        {
            return obj1;
        }
        try
        {
            charsequence1 = (CharSequence)obj2;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-length", 1, obj2);
        }
        j = strings.stringLength(charsequence1);
        obj4 = lists.caar.apply1(obj3);
        obj5 = lists.cdar.apply1(obj3);
        aobj1 = new Object[3];
        try
        {
            charsequence2 = (CharSequence)obj1;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "substring", 1, obj1);
        }
        try
        {
            k = ((Number)obj4).intValue();
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "substring", 3, obj4);
        }
        aobj1[0] = strings.substring(charsequence2, 0, k);
        aobj1[1] = pregexpReplaceAux(obj1, obj2, Integer.valueOf(j), obj3);
        try
        {
            charsequence3 = (CharSequence)obj1;
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "substring", 1, obj1);
        }
        try
        {
            l = ((Number)obj5).intValue();
        }
        catch (ClassCastException classcastexception5)
        {
            throw new WrongType(classcastexception5, "substring", 2, obj5);
        }
        aobj1[2] = strings.substring(charsequence3, l, i);
        return strings.stringAppend(aobj1);
    }

    public static Object pregexpReplace$St(Object obj, Object obj1, Object obj2)
    {
        if (strings.isString(obj))
        {
            obj = pregexp(obj);
        }
        CharSequence charsequence;
        int i;
        CharSequence charsequence1;
        int j;
        Object obj3;
        Object obj4;
        try
        {
            charsequence = (CharSequence)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj1);
        }
        i = strings.stringLength(charsequence);
        try
        {
            charsequence1 = (CharSequence)obj2;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-length", 1, obj2);
        }
        j = strings.stringLength(charsequence1);
        obj3 = Lit73;
        obj4 = "";
        do
        {
            if (Scheme.numGEq.apply2(obj3, Integer.valueOf(i)) != Boolean.FALSE)
            {
                return obj4;
            }
            Object aobj[] = new Object[2];
            aobj[0] = obj3;
            aobj[1] = Integer.valueOf(i);
            Object obj5 = pregexpMatchPositions$V(obj, obj1, aobj);
            Object obj7;
            if (obj5 == Boolean.FALSE)
            {
                if (Scheme.numEqu.apply2(obj3, Lit73) == Boolean.FALSE)
                {
                    Object aobj2[] = new Object[2];
                    aobj2[0] = obj4;
                    Object obj6;
                    Object aobj1[];
                    CharSequence charsequence2;
                    int k;
                    int l;
                    CharSequence charsequence3;
                    int i1;
                    try
                    {
                        charsequence3 = (CharSequence)obj1;
                    }
                    catch (ClassCastException classcastexception5)
                    {
                        throw new WrongType(classcastexception5, "substring", 1, obj1);
                    }
                    try
                    {
                        i1 = ((Number)obj3).intValue();
                    }
                    catch (ClassCastException classcastexception6)
                    {
                        throw new WrongType(classcastexception6, "substring", 2, obj3);
                    }
                    aobj2[1] = strings.substring(charsequence3, i1, i);
                    obj1 = strings.stringAppend(aobj2);
                }
                return obj1;
            }
            obj6 = lists.cdar.apply1(obj5);
            aobj1 = new Object[3];
            aobj1[0] = obj4;
            try
            {
                charsequence2 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "substring", 1, obj1);
            }
            try
            {
                k = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "substring", 2, obj3);
            }
            obj7 = lists.caar.apply1(obj5);
            try
            {
                l = ((Number)obj7).intValue();
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "substring", 3, obj7);
            }
            aobj1[1] = strings.substring(charsequence2, k, l);
            aobj1[2] = pregexpReplaceAux(obj1, obj2, Integer.valueOf(j), obj5);
            obj4 = strings.stringAppend(aobj1);
            obj3 = obj6;
        } while (true);
    }

    public static Object pregexpReplaceAux(Object obj, Object obj1, Object obj2, Object obj3)
    {
        Object obj4 = Lit73;
        Object obj5 = "";
        do
        {
            if (Scheme.numGEq.apply2(obj4, obj2) != Boolean.FALSE)
            {
                return obj5;
            }
            CharSequence charsequence;
            int i;
            char c;
            try
            {
                charsequence = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-ref", 1, obj1);
            }
            try
            {
                i = ((Number)obj4).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 2, obj4);
            }
            c = strings.stringRef(charsequence, i);
            if (characters.isChar$Eq(Char.make(c), Lit19))
            {
                Object obj6 = pregexpReadEscapedNumber(obj1, obj4, obj2);
                Object obj8;
                if (obj6 != Boolean.FALSE)
                {
                    obj8 = lists.car.apply1(obj6);
                } else
                {
                    Object aobj[];
                    Object aobj1[];
                    CharSequence charsequence1;
                    Object obj7;
                    int j;
                    Object obj9;
                    Object aobj2[];
                    CharSequence charsequence2;
                    int k;
                    int l;
                    Object aobj3[];
                    Object aobj4[];
                    try
                    {
                        charsequence1 = (CharSequence)obj1;
                    }
                    catch (ClassCastException classcastexception2)
                    {
                        throw new WrongType(classcastexception2, "string-ref", 1, obj1);
                    }
                    obj7 = AddOp.$Pl.apply2(obj4, Lit8);
                    try
                    {
                        j = ((Number)obj7).intValue();
                    }
                    catch (ClassCastException classcastexception3)
                    {
                        throw new WrongType(classcastexception3, "string-ref", 2, obj7);
                    }
                    if (characters.isChar$Eq(Char.make(strings.stringRef(charsequence1, j)), Lit113))
                    {
                        obj8 = Lit73;
                    } else
                    {
                        obj8 = Boolean.FALSE;
                    }
                }
                if (obj6 != Boolean.FALSE)
                {
                    obj4 = lists.cadr.apply1(obj6);
                } else
                if (obj8 != Boolean.FALSE)
                {
                    obj4 = AddOp.$Pl.apply2(obj4, Lit16);
                } else
                {
                    obj4 = AddOp.$Pl.apply2(obj4, Lit8);
                }
                if (obj8 == Boolean.FALSE)
                {
                    CharSequence charsequence3;
                    int i1;
                    char c1;
                    try
                    {
                        charsequence3 = (CharSequence)obj1;
                    }
                    catch (ClassCastException classcastexception7)
                    {
                        throw new WrongType(classcastexception7, "string-ref", 1, obj1);
                    }
                    try
                    {
                        i1 = ((Number)obj4).intValue();
                    }
                    catch (ClassCastException classcastexception8)
                    {
                        throw new WrongType(classcastexception8, "string-ref", 2, obj4);
                    }
                    c1 = strings.stringRef(charsequence3, i1);
                    obj4 = AddOp.$Pl.apply2(obj4, Lit8);
                    if (!characters.isChar$Eq(Char.make(c1), Lit11))
                    {
                        aobj3 = new Object[2];
                        aobj3[0] = obj5;
                        aobj4 = new Object[1];
                        aobj4[0] = Char.make(c1);
                        aobj3[1] = strings.$make$string$(aobj4);
                        obj5 = strings.stringAppend(aobj3);
                    }
                } else
                {
                    obj9 = pregexpListRef(obj3, obj8);
                    if (obj9 != Boolean.FALSE)
                    {
                        aobj2 = new Object[2];
                        aobj2[0] = obj5;
                        Object obj10;
                        Object obj11;
                        try
                        {
                            charsequence2 = (CharSequence)obj;
                        }
                        catch (ClassCastException classcastexception4)
                        {
                            throw new WrongType(classcastexception4, "substring", 1, obj);
                        }
                        obj10 = lists.car.apply1(obj9);
                        try
                        {
                            k = ((Number)obj10).intValue();
                        }
                        catch (ClassCastException classcastexception5)
                        {
                            throw new WrongType(classcastexception5, "substring", 2, obj10);
                        }
                        obj11 = lists.cdr.apply1(obj9);
                        try
                        {
                            l = ((Number)obj11).intValue();
                        }
                        catch (ClassCastException classcastexception6)
                        {
                            throw new WrongType(classcastexception6, "substring", 3, obj11);
                        }
                        aobj2[1] = strings.substring(charsequence2, k, l);
                        obj5 = strings.stringAppend(aobj2);
                    }
                }
            } else
            {
                obj4 = AddOp.$Pl.apply2(obj4, Lit8);
                aobj = new Object[2];
                aobj[0] = obj5;
                aobj1 = new Object[1];
                aobj1[0] = Char.make(c);
                aobj[1] = strings.$make$string$(aobj1);
                obj5 = strings.stringAppend(aobj);
            }
        } while (true);
    }

    public static Object pregexpReverse$Ex(Object obj)
    {
        Object obj1 = LList.Empty;
        do
        {
            if (lists.isNull(obj))
            {
                return obj1;
            }
            Object obj2 = lists.cdr.apply1(obj);
            Pair pair;
            try
            {
                pair = (Pair)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "set-cdr!", 1, obj);
            }
            lists.setCdr$Ex(pair, obj1);
            obj1 = obj;
            obj = obj2;
        } while (true);
    }

    public static Object pregexpSplit(Object obj, Object obj1)
    {
        CharSequence charsequence;
        int i;
        Object obj2;
        Object obj3;
        Boolean boolean1;
        try
        {
            charsequence = (CharSequence)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj1);
        }
        i = strings.stringLength(charsequence);
        obj2 = Lit73;
        obj3 = LList.Empty;
        boolean1 = Boolean.FALSE;
        do
        {
            if (Scheme.numGEq.apply2(obj2, Integer.valueOf(i)) != Boolean.FALSE)
            {
                return pregexpReverse$Ex(obj3);
            }
            Object aobj[] = new Object[2];
            aobj[0] = obj2;
            aobj[1] = Integer.valueOf(i);
            Object obj4 = pregexpMatchPositions$V(obj, obj1, aobj);
            if (obj4 != Boolean.FALSE)
            {
                Object obj5 = lists.car.apply1(obj4);
                Object obj6 = lists.car.apply1(obj5);
                Object obj7 = lists.cdr.apply1(obj5);
                if (Scheme.numEqu.apply2(obj6, obj7) != Boolean.FALSE)
                {
                    Object obj9 = AddOp.$Pl.apply2(obj7, Lit8);
                    Integer integer;
                    CharSequence charsequence1;
                    int j;
                    boolean flag;
                    CharSequence charsequence2;
                    int k;
                    int l;
                    CharSequence charsequence3;
                    int i1;
                    Object obj10;
                    int j1;
                    try
                    {
                        charsequence3 = (CharSequence)obj1;
                    }
                    catch (ClassCastException classcastexception7)
                    {
                        throw new WrongType(classcastexception7, "substring", 1, obj1);
                    }
                    try
                    {
                        i1 = ((Number)obj2).intValue();
                    }
                    catch (ClassCastException classcastexception8)
                    {
                        throw new WrongType(classcastexception8, "substring", 2, obj2);
                    }
                    obj10 = AddOp.$Pl.apply2(obj6, Lit8);
                    try
                    {
                        j1 = ((Number)obj10).intValue();
                    }
                    catch (ClassCastException classcastexception9)
                    {
                        throw new WrongType(classcastexception9, "substring", 3, obj10);
                    }
                    obj3 = lists.cons(strings.substring(charsequence3, i1, j1), obj3);
                    boolean1 = Boolean.TRUE;
                    obj2 = obj9;
                } else
                {
                    Object obj8 = Scheme.numEqu.apply2(obj6, obj2);
                    try
                    {
                        flag = ((Boolean)obj8).booleanValue();
                    }
                    catch (ClassCastException classcastexception3)
                    {
                        throw new WrongType(classcastexception3, "x", -2, obj8);
                    }
                    if (flag ? boolean1 != Boolean.FALSE : flag)
                    {
                        boolean1 = Boolean.FALSE;
                        obj2 = obj7;
                    } else
                    {
                        try
                        {
                            charsequence2 = (CharSequence)obj1;
                        }
                        catch (ClassCastException classcastexception4)
                        {
                            throw new WrongType(classcastexception4, "substring", 1, obj1);
                        }
                        try
                        {
                            k = ((Number)obj2).intValue();
                        }
                        catch (ClassCastException classcastexception5)
                        {
                            throw new WrongType(classcastexception5, "substring", 2, obj2);
                        }
                        try
                        {
                            l = ((Number)obj6).intValue();
                        }
                        catch (ClassCastException classcastexception6)
                        {
                            throw new WrongType(classcastexception6, "substring", 3, obj6);
                        }
                        obj3 = lists.cons(strings.substring(charsequence2, k, l), obj3);
                        boolean1 = Boolean.FALSE;
                        obj2 = obj7;
                    }
                }
            } else
            {
                integer = Integer.valueOf(i);
                try
                {
                    charsequence1 = (CharSequence)obj1;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "substring", 1, obj1);
                }
                try
                {
                    j = ((Number)obj2).intValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "substring", 2, obj2);
                }
                obj3 = lists.cons(strings.substring(charsequence1, j, i), obj3);
                boolean1 = Boolean.FALSE;
                obj2 = integer;
            }
        } while (true);
    }

    public static Object pregexpStringMatch(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
    {
        CharSequence charsequence;
        int i;
        Object obj6;
        Object obj7;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj);
        }
        i = strings.stringLength(charsequence);
        if (Scheme.numGrt.apply2(Integer.valueOf(i), obj3) != Boolean.FALSE)
        {
            return Scheme.applyToArgs.apply1(obj5);
        }
        obj6 = Lit73;
        obj7 = obj2;
        do
        {
            if (Scheme.numGEq.apply2(obj6, Integer.valueOf(i)) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply2(obj4, obj7);
            }
            if (Scheme.numGEq.apply2(obj7, obj3) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply1(obj5);
            }
            CharSequence charsequence1;
            int j;
            Char char1;
            CharSequence charsequence2;
            int k;
            try
            {
                charsequence1 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 1, obj);
            }
            try
            {
                j = ((Number)obj6).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 2, obj6);
            }
            char1 = Char.make(strings.stringRef(charsequence1, j));
            try
            {
                charsequence2 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "string-ref", 1, obj1);
            }
            try
            {
                k = ((Number)obj7).intValue();
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "string-ref", 2, obj7);
            }
            if (characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence2, k))))
            {
                obj6 = AddOp.$Pl.apply2(obj6, Lit8);
                obj7 = AddOp.$Pl.apply2(obj7, Lit8);
            } else
            {
                return Scheme.applyToArgs.apply1(obj5);
            }
        } while (true);
    }

    public static Object pregexpWrapQuantifierIfAny(Object obj, Object obj1, Object obj2)
    {
        Object obj3;
        Object obj4;
        obj3 = lists.car.apply1(obj);
        obj4 = lists.cadr.apply1(obj);
_L5:
        if (Scheme.numGEq.apply2(obj4, obj2) == Boolean.FALSE) goto _L2; else goto _L1
_L1:
        return obj;
_L2:
        Object obj11;
        Object obj12;
        Object obj13;
        CharSequence charsequence;
        int i;
        char c;
        boolean flag;
        Object obj5;
        Object obj6;
        Object obj7;
        Pair pair;
        Pair pair1;
        Object obj8;
        Pair pair2;
        Pair pair3;
        CharSequence charsequence1;
        int j;
        char c1;
        boolean flag1;
        Pair pair4;
        Pair pair5;
        Pair pair6;
        Pair pair7;
        Pair pair8;
        Pair pair9;
        Object aobj[];
        Pair pair10;
        Pair pair11;
        Pair pair12;
        Pair pair13;
        Pair pair14;
        Pair pair15;
        try
        {
            charsequence = (CharSequence)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-ref", 1, obj1);
        }
        try
        {
            i = ((Number)obj4).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 2, obj4);
        }
        c = strings.stringRef(charsequence, i);
        flag = unicode.isCharWhitespace(Char.make(c));
        if (flag ? $Stpregexp$Mnspace$Mnsensitive$Qu$St == Boolean.FALSE : flag)
        {
            obj4 = AddOp.$Pl.apply2(obj4, Lit8);
            continue; /* Loop/switch isn't completed */
        }
        obj5 = Scheme.isEqv.apply2(Char.make(c), Lit65);
        if (obj5 == Boolean.FALSE ? (obj6 = Scheme.isEqv.apply2(Char.make(c), Lit66)) == Boolean.FALSE ? (obj7 = Scheme.isEqv.apply2(Char.make(c), Lit47)) == Boolean.FALSE ? Scheme.isEqv.apply2(Char.make(c), Lit67) == Boolean.FALSE : obj7 == Boolean.FALSE : obj6 == Boolean.FALSE : obj5 == Boolean.FALSE) goto _L1; else goto _L3
_L3:
        pair = LList.list1(Lit68);
        LList.chain4(pair, Lit69, Lit70, Lit71, obj3);
        pair1 = LList.list2(pair, Lit72);
        if (Scheme.isEqv.apply2(Char.make(c), Lit65) != Boolean.FALSE)
        {
            Object obj22 = lists.cddr.apply1(pair);
            Object obj23;
            try
            {
                pair14 = (Pair)obj22;
            }
            catch (ClassCastException classcastexception16)
            {
                throw new WrongType(classcastexception16, "set-car!", 1, obj22);
            }
            lists.setCar$Ex(pair14, Lit73);
            obj23 = lists.cdddr.apply1(pair);
            try
            {
                pair15 = (Pair)obj23;
            }
            catch (ClassCastException classcastexception17)
            {
                throw new WrongType(classcastexception17, "set-car!", 1, obj23);
            }
            lists.setCar$Ex(pair15, Boolean.FALSE);
        } else
        if (Scheme.isEqv.apply2(Char.make(c), Lit66) != Boolean.FALSE)
        {
            Object obj20 = lists.cddr.apply1(pair);
            Object obj21;
            try
            {
                pair12 = (Pair)obj20;
            }
            catch (ClassCastException classcastexception14)
            {
                throw new WrongType(classcastexception14, "set-car!", 1, obj20);
            }
            lists.setCar$Ex(pair12, Lit8);
            obj21 = lists.cdddr.apply1(pair);
            try
            {
                pair13 = (Pair)obj21;
            }
            catch (ClassCastException classcastexception15)
            {
                throw new WrongType(classcastexception15, "set-car!", 1, obj21);
            }
            lists.setCar$Ex(pair13, Boolean.FALSE);
        } else
        if (Scheme.isEqv.apply2(Char.make(c), Lit47) != Boolean.FALSE)
        {
            Object obj18 = lists.cddr.apply1(pair);
            Object obj19;
            try
            {
                pair10 = (Pair)obj18;
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "set-car!", 1, obj18);
            }
            lists.setCar$Ex(pair10, Lit73);
            obj19 = lists.cdddr.apply1(pair);
            try
            {
                pair11 = (Pair)obj19;
            }
            catch (ClassCastException classcastexception13)
            {
                throw new WrongType(classcastexception13, "set-car!", 1, obj19);
            }
            lists.setCar$Ex(pair11, Lit8);
        } else
        if (Scheme.isEqv.apply2(Char.make(c), Lit67) != Boolean.FALSE)
        {
            obj8 = pregexpReadNums(obj1, AddOp.$Pl.apply2(obj4, Lit8), obj2);
            if (obj8 == Boolean.FALSE)
            {
                aobj = new Object[2];
                aobj[0] = Lit74;
                aobj[1] = Lit75;
                pregexpError$V(aobj);
            }
            Object obj9 = lists.cddr.apply1(pair);
            Object obj10;
            try
            {
                pair2 = (Pair)obj9;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "set-car!", 1, obj9);
            }
            lists.setCar$Ex(pair2, lists.car.apply1(obj8));
            obj10 = lists.cdddr.apply1(pair);
            try
            {
                pair3 = (Pair)obj10;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "set-car!", 1, obj10);
            }
            lists.setCar$Ex(pair3, lists.cadr.apply1(obj8));
            obj4 = lists.caddr.apply1(obj8);
        }
        obj11 = AddOp.$Pl.apply2(obj4, Lit8);
        do
        {
label0:
            {
                {
                    if (Scheme.numGEq.apply2(obj11, obj2) == Boolean.FALSE)
                    {
                        break label0;
                    }
                    Object obj16 = lists.cdr.apply1(pair);
                    Object obj17;
                    try
                    {
                        pair8 = (Pair)obj16;
                    }
                    catch (ClassCastException classcastexception10)
                    {
                        throw new WrongType(classcastexception10, "set-car!", 1, obj16);
                    }
                    lists.setCar$Ex(pair8, Boolean.FALSE);
                    obj17 = lists.cdr.apply1(pair1);
                    try
                    {
                        pair9 = (Pair)obj17;
                    }
                    catch (ClassCastException classcastexception11)
                    {
                        throw new WrongType(classcastexception11, "set-car!", 1, obj17);
                    }
                    lists.setCar$Ex(pair9, obj11);
                }
                return pair1;
            }
            try
            {
                charsequence1 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "string-ref", 1, obj1);
            }
            try
            {
                j = ((Number)obj11).intValue();
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "string-ref", 2, obj11);
            }
            c1 = strings.stringRef(charsequence1, j);
            flag1 = unicode.isCharWhitespace(Char.make(c1));
            if (flag1 ? $Stpregexp$Mnspace$Mnsensitive$Qu$St == Boolean.FALSE : flag1)
            {
                obj11 = AddOp.$Pl.apply2(obj11, Lit8);
            } else
            {
                break MISSING_BLOCK_LABEL_774;
            }
        } while (true);
label1:
        {
            if (!characters.isChar$Eq(Char.make(c1), Lit47))
            {
                break label1;
            }
            Object obj14 = lists.cdr.apply1(pair);
            Object obj15;
            try
            {
                pair6 = (Pair)obj14;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "set-car!", 1, obj14);
            }
            lists.setCar$Ex(pair6, Boolean.TRUE);
            obj15 = lists.cdr.apply1(pair1);
            try
            {
                pair7 = (Pair)obj15;
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "set-car!", 1, obj15);
            }
            lists.setCar$Ex(pair7, AddOp.$Pl.apply2(obj11, Lit8));
        }
        break MISSING_BLOCK_LABEL_317;
        obj12 = lists.cdr.apply1(pair);
        try
        {
            pair4 = (Pair)obj12;
        }
        catch (ClassCastException classcastexception6)
        {
            throw new WrongType(classcastexception6, "set-car!", 1, obj12);
        }
        lists.setCar$Ex(pair4, Boolean.FALSE);
        obj13 = lists.cdr.apply1(pair1);
        pair5 = (Pair)obj13;
        lists.setCar$Ex(pair5, obj11);
        break MISSING_BLOCK_LABEL_317;
        ClassCastException classcastexception7;
        classcastexception7;
        throw new WrongType(classcastexception7, "set-car!", 1, obj13);
        if (true) goto _L5; else goto _L4
_L4:
    }

    public Object apply0(ModuleMethod modulemethod)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply0(modulemethod);

        case 36: // '$'
            return frame.lambda4();

        case 37: // '%'
            return frame0.lambda13();

        case 38: // '&'
            return frame0.lambda14();

        case 39: // '\''
            return frame0.lambda15();

        case 40: // '('
            return frame0.lambda16();

        case 41: // ')'
            return frame0.lambda17();
        }
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply1(modulemethod, obj);

        case 16: // '\020'
            return pregexpReverse$Ex(obj);

        case 28: // '\034'
            return pregexpInvertCharList(obj);

        case 31: // '\037'
            if (isPregexpCharWord(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 35: // '#'
            return pregexpMakeBackrefList(obj);

        case 44: // ','
            return pregexp(obj);

        case 50: // '2'
            return pregexpQuote(obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 33: // '!'
            return isPregexpCheckIfInCharClass(obj, obj1);

        case 34: // '"'
            return pregexpListRef(obj, obj1);

        case 47: // '/'
            return pregexpSplit(obj, obj1);
        }
    }

    public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
    {
        switch (modulemethod.selector)
        {
        case 28: // '\034'
        case 30: // '\036'
        case 31: // '\037'
        case 33: // '!'
        case 34: // '"'
        case 35: // '#'
        case 36: // '$'
        case 37: // '%'
        case 38: // '&'
        case 39: // '\''
        case 40: // '('
        case 41: // ')'
        case 42: // '*'
        case 43: // '+'
        case 44: // ','
        case 45: // '-'
        case 46: // '.'
        case 47: // '/'
        default:
            return super.apply3(modulemethod, obj, obj1, obj2);

        case 18: // '\022'
            return pregexpReadPattern(obj, obj1, obj2);

        case 19: // '\023'
            return pregexpReadBranch(obj, obj1, obj2);

        case 20: // '\024'
            return pregexpReadPiece(obj, obj1, obj2);

        case 21: // '\025'
            return pregexpReadEscapedNumber(obj, obj1, obj2);

        case 22: // '\026'
            return pregexpReadEscapedChar(obj, obj1, obj2);

        case 23: // '\027'
            return pregexpReadPosixCharClass(obj, obj1, obj2);

        case 24: // '\030'
            return pregexpReadClusterType(obj, obj1, obj2);

        case 25: // '\031'
            return pregexpReadSubpattern(obj, obj1, obj2);

        case 26: // '\032'
            return pregexpWrapQuantifierIfAny(obj, obj1, obj2);

        case 27: // '\033'
            return pregexpReadNums(obj, obj1, obj2);

        case 29: // '\035'
            return pregexpReadCharList(obj, obj1, obj2);

        case 32: // ' '
            return isPregexpAtWordBoundary(obj, obj1, obj2);

        case 48: // '0'
            return pregexpReplace(obj, obj1, obj2);

        case 49: // '1'
            return pregexpReplace$St(obj, obj1, obj2);
        }
    }

    public Object apply4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3)
    {
        if (modulemethod.selector == 43)
        {
            return pregexpReplaceAux(obj, obj1, obj2, obj3);
        } else
        {
            return super.apply4(modulemethod, obj, obj1, obj2, obj3);
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

        case 17: // '\021'
            return pregexpError$V(aobj);

        case 30: // '\036'
            return pregexpStringMatch(aobj[0], aobj[1], aobj[2], aobj[3], aobj[4], aobj[5]);

        case 42: // '*'
            return pregexpMatchPositionsAux(aobj[0], aobj[1], aobj[2], aobj[3], aobj[4], aobj[5]);

        case 45: // '-'
            Object obj2 = aobj[0];
            Object obj3 = aobj[1];
            int j = -2 + aobj.length;
            Object aobj2[] = new Object[j];
            do
            {
                if (--j < 0)
                {
                    return pregexpMatchPositions$V(obj2, obj3, aobj2);
                }
                aobj2[j] = aobj[j + 2];
            } while (true);

        case 46: // '.'
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
                return pregexpMatch$V(obj, obj1, aobj1);
            }
            aobj1[i] = aobj[i + 2];
        } while (true);
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match0(modulemethod, callcontext);

        case 41: // ')'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 40: // '('
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 39: // '\''
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 38: // '&'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 37: // '%'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 36: // '$'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 50: // '2'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 44: // ','
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 35: // '#'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 31: // '\037'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 28: // '\034'
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

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match2(modulemethod, obj, obj1, callcontext);

        case 47: // '/'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 34: // '"'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 33: // '!'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        }
    }

    public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 28: // '\034'
        case 30: // '\036'
        case 31: // '\037'
        case 33: // '!'
        case 34: // '"'
        case 35: // '#'
        case 36: // '$'
        case 37: // '%'
        case 38: // '&'
        case 39: // '\''
        case 40: // '('
        case 41: // ')'
        case 42: // '*'
        case 43: // '+'
        case 44: // ','
        case 45: // '-'
        case 46: // '.'
        case 47: // '/'
        default:
            return super.match3(modulemethod, obj, obj1, obj2, callcontext);

        case 49: // '1'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 48: // '0'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 32: // ' '
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 29: // '\035'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 27: // '\033'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 26: // '\032'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 25: // '\031'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 24: // '\030'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 23: // '\027'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 22: // '\026'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 21: // '\025'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 20: // '\024'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 19: // '\023'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 18: // '\022'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;
        }
    }

    public int match4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3, CallContext callcontext)
    {
        if (modulemethod.selector == 43)
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

    public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.matchN(modulemethod, aobj, callcontext);

        case 46: // '.'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 45: // '-'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 42: // '*'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 30: // '\036'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 17: // '\021'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
        $Stpregexp$Mnversion$St = Lit0;
        $Stpregexp$Mncomment$Mnchar$St = Lit1;
        $Stpregexp$Mnnul$Mnchar$Mnint$St = Integer.valueOf(-97 + characters.char$To$Integer(Lit2));
        $Stpregexp$Mnreturn$Mnchar$St = characters.integer$To$Char(13 + ((Number)$Stpregexp$Mnnul$Mnchar$Mnint$St).intValue());
        $Stpregexp$Mntab$Mnchar$St = characters.integer$To$Char(9 + ((Number)$Stpregexp$Mnnul$Mnchar$Mnint$St).intValue());
        $Stpregexp$Mnspace$Mnsensitive$Qu$St = Boolean.TRUE;
    }

    static 
    {
        Lit135 = (SimpleSymbol)(new SimpleSymbol("pregexp-quote")).readResolve();
        Lit134 = (SimpleSymbol)(new SimpleSymbol("pregexp-replace*")).readResolve();
        Lit133 = (SimpleSymbol)(new SimpleSymbol("pregexp-replace")).readResolve();
        Lit132 = (SimpleSymbol)(new SimpleSymbol("pregexp-split")).readResolve();
        Lit131 = (SimpleSymbol)(new SimpleSymbol("pregexp-match")).readResolve();
        Lit130 = (SimpleSymbol)(new SimpleSymbol("pregexp")).readResolve();
        Lit129 = (SimpleSymbol)(new SimpleSymbol("pregexp-replace-aux")).readResolve();
        Lit128 = (SimpleSymbol)(new SimpleSymbol("pregexp-make-backref-list")).readResolve();
        Lit127 = (SimpleSymbol)(new SimpleSymbol("pregexp-list-ref")).readResolve();
        Lit126 = (SimpleSymbol)(new SimpleSymbol("pregexp-at-word-boundary?")).readResolve();
        Lit125 = (SimpleSymbol)(new SimpleSymbol("pregexp-char-word?")).readResolve();
        Lit124 = (SimpleSymbol)(new SimpleSymbol("pregexp-string-match")).readResolve();
        Lit123 = (SimpleSymbol)(new SimpleSymbol("pregexp-invert-char-list")).readResolve();
        Lit122 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-escaped-char")).readResolve();
        Lit121 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-escaped-number")).readResolve();
        Lit120 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-branch")).readResolve();
        Lit119 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-pattern")).readResolve();
        Lit118 = (SimpleSymbol)(new SimpleSymbol("pregexp-error")).readResolve();
        Lit117 = (SimpleSymbol)(new SimpleSymbol("pregexp-reverse!")).readResolve();
        Char char1 = Char.make(92);
        Lit19 = char1;
        Char char2 = Char.make(46);
        Lit13 = char2;
        Char char3 = Char.make(63);
        Lit47 = char3;
        Char char4 = Char.make(42);
        Lit65 = char4;
        Char char5 = Char.make(43);
        Lit66 = char5;
        Char char6 = Char.make(124);
        Lit7 = char6;
        Char char7 = Char.make(94);
        Lit9 = char7;
        Char char8 = Char.make(36);
        Lit11 = char8;
        Char char9 = Char.make(91);
        Lit15 = char9;
        Char char10 = Char.make(93);
        Lit46 = char10;
        Char char11 = Char.make(123);
        Lit67 = char11;
        Char char12 = Char.make(125);
        Lit78 = char12;
        Char char13 = Char.make(40);
        Lit18 = char13;
        Char char14 = Char.make(41);
        Lit6 = char14;
        Lit116 = PairWithPosition.make(char1, PairWithPosition.make(char2, PairWithPosition.make(char3, PairWithPosition.make(char4, PairWithPosition.make(char5, PairWithPosition.make(char6, PairWithPosition.make(char7, PairWithPosition.make(char8, PairWithPosition.make(char9, PairWithPosition.make(char10, PairWithPosition.make(char11, PairWithPosition.make(char12, PairWithPosition.make(char13, PairWithPosition.make(char14, LList.Empty, "pregexp.scm", 0x302039), "pregexp.scm", 0x302035), "pregexp.scm", 0x302031), "pregexp.scm", 0x30202d), "pregexp.scm", 0x302029), "pregexp.scm", 0x302025), "pregexp.scm", 0x30103d), "pregexp.scm", 0x301039), "pregexp.scm", 0x301035), "pregexp.scm", 0x301031), "pregexp.scm", 0x30102d), "pregexp.scm", 0x301029), "pregexp.scm", 0x301025), "pregexp.scm", 0x301020);
        Lit114 = (SimpleSymbol)(new SimpleSymbol("pregexp-match-positions")).readResolve();
        Lit109 = (SimpleSymbol)(new SimpleSymbol(":no-backtrack")).readResolve();
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol(":between")).readResolve();
        Lit68 = simplesymbol;
        Boolean boolean1 = Boolean.FALSE;
        IntNum intnum = IntNum.make(0);
        Lit73 = intnum;
        Boolean boolean2 = Boolean.FALSE;
        SimpleSymbol simplesymbol1 = (SimpleSymbol)(new SimpleSymbol(":any")).readResolve();
        Lit14 = simplesymbol1;
        Lit108 = PairWithPosition.make(simplesymbol, PairWithPosition.make(boolean1, PairWithPosition.make(intnum, PairWithPosition.make(boolean2, PairWithPosition.make(simplesymbol1, LList.Empty, "pregexp.scm", 0x23b041), "pregexp.scm", 0x23b03e), "pregexp.scm", 0x23b03c), "pregexp.scm", 0x23b039), "pregexp.scm", 0x23b02f);
        Lit107 = (SimpleSymbol)(new SimpleSymbol(":neg-lookbehind")).readResolve();
        Lit106 = PairWithPosition.make(Lit68, PairWithPosition.make(Boolean.FALSE, PairWithPosition.make(Lit73, PairWithPosition.make(Boolean.FALSE, PairWithPosition.make(Lit14, LList.Empty, "pregexp.scm", 0x232041), "pregexp.scm", 0x23203e), "pregexp.scm", 0x23203c), "pregexp.scm", 0x232039), "pregexp.scm", 0x23202f);
        Lit105 = (SimpleSymbol)(new SimpleSymbol(":lookbehind")).readResolve();
        Lit104 = (SimpleSymbol)(new SimpleSymbol(":neg-lookahead")).readResolve();
        Lit103 = (SimpleSymbol)(new SimpleSymbol(":lookahead")).readResolve();
        Lit101 = (SimpleSymbol)(new SimpleSymbol("pregexp-match-positions-aux")).readResolve();
        Lit100 = (SimpleSymbol)(new SimpleSymbol(":sub")).readResolve();
        Lit99 = (SimpleSymbol)(new SimpleSymbol("pregexp-check-if-in-char-class?")).readResolve();
        Lit80 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-char-list")).readResolve();
        Lit76 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-nums")).readResolve();
        Lit74 = (SimpleSymbol)(new SimpleSymbol("pregexp-wrap-quantifier-if-any")).readResolve();
        Lit64 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-subpattern")).readResolve();
        Lit63 = PairWithPosition.make(Lit100, LList.Empty, "pregexp.scm", 0xe6016);
        Lit57 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-cluster-type")).readResolve();
        Lit56 = PairWithPosition.make(Lit107, LList.Empty, "pregexp.scm", 0xd601f);
        Lit55 = PairWithPosition.make(Lit105, LList.Empty, "pregexp.scm", 0xd501f);
        Lit53 = PairWithPosition.make(Lit109, LList.Empty, "pregexp.scm", 0xd201c);
        Lit51 = PairWithPosition.make(Lit104, LList.Empty, "pregexp.scm", 0xd101c);
        Lit49 = PairWithPosition.make(Lit103, LList.Empty, "pregexp.scm", 0xd001c);
        Lit45 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-posix-char-class")).readResolve();
        SimpleSymbol simplesymbol2 = (SimpleSymbol)(new SimpleSymbol(":neg-char")).readResolve();
        Lit17 = simplesymbol2;
        SimpleSymbol simplesymbol3 = (SimpleSymbol)(new SimpleSymbol(":word")).readResolve();
        Lit41 = simplesymbol3;
        Lit43 = PairWithPosition.make(simplesymbol2, PairWithPosition.make(simplesymbol3, LList.Empty, "pregexp.scm", 0xaa027), "pregexp.scm", 0xaa01c);
        SimpleSymbol simplesymbol4 = Lit17;
        SimpleSymbol simplesymbol5 = (SimpleSymbol)(new SimpleSymbol(":space")).readResolve();
        Lit36 = simplesymbol5;
        Lit38 = PairWithPosition.make(simplesymbol4, PairWithPosition.make(simplesymbol5, LList.Empty, "pregexp.scm", 0xa7027), "pregexp.scm", 0xa701c);
        SimpleSymbol simplesymbol6 = Lit17;
        SimpleSymbol simplesymbol7 = (SimpleSymbol)(new SimpleSymbol(":digit")).readResolve();
        Lit30 = simplesymbol7;
        Lit32 = PairWithPosition.make(simplesymbol6, PairWithPosition.make(simplesymbol7, LList.Empty, "pregexp.scm", 0xa3027), "pregexp.scm", 0xa301c);
        Lit21 = (SimpleSymbol)(new SimpleSymbol("pregexp-read-piece")).readResolve();
        $instance = new pregexp();
        pregexp pregexp1 = $instance;
        ModuleMethod modulemethod = new ModuleMethod(pregexp1, 16, Lit117, 4097);
        modulemethod.setProperty("source-location", "pregexp.scm:47");
        pregexp$Mnreverse$Ex = modulemethod;
        ModuleMethod modulemethod1 = new ModuleMethod(pregexp1, 17, Lit118, -4096);
        modulemethod1.setProperty("source-location", "pregexp.scm:57");
        pregexp$Mnerror = modulemethod1;
        ModuleMethod modulemethod2 = new ModuleMethod(pregexp1, 18, Lit119, 12291);
        modulemethod2.setProperty("source-location", "pregexp.scm:65");
        pregexp$Mnread$Mnpattern = modulemethod2;
        ModuleMethod modulemethod3 = new ModuleMethod(pregexp1, 19, Lit120, 12291);
        modulemethod3.setProperty("source-location", "pregexp.scm:79");
        pregexp$Mnread$Mnbranch = modulemethod3;
        ModuleMethod modulemethod4 = new ModuleMethod(pregexp1, 20, Lit21, 12291);
        modulemethod4.setProperty("source-location", "pregexp.scm:91");
        pregexp$Mnread$Mnpiece = modulemethod4;
        ModuleMethod modulemethod5 = new ModuleMethod(pregexp1, 21, Lit121, 12291);
        modulemethod5.setProperty("source-location", "pregexp.scm:138");
        pregexp$Mnread$Mnescaped$Mnnumber = modulemethod5;
        ModuleMethod modulemethod6 = new ModuleMethod(pregexp1, 22, Lit122, 12291);
        modulemethod6.setProperty("source-location", "pregexp.scm:155");
        pregexp$Mnread$Mnescaped$Mnchar = modulemethod6;
        ModuleMethod modulemethod7 = new ModuleMethod(pregexp1, 23, Lit45, 12291);
        modulemethod7.setProperty("source-location", "pregexp.scm:174");
        pregexp$Mnread$Mnposix$Mnchar$Mnclass = modulemethod7;
        ModuleMethod modulemethod8 = new ModuleMethod(pregexp1, 24, Lit57, 12291);
        modulemethod8.setProperty("source-location", "pregexp.scm:200");
        pregexp$Mnread$Mncluster$Mntype = modulemethod8;
        ModuleMethod modulemethod9 = new ModuleMethod(pregexp1, 25, Lit64, 12291);
        modulemethod9.setProperty("source-location", "pregexp.scm:233");
        pregexp$Mnread$Mnsubpattern = modulemethod9;
        ModuleMethod modulemethod10 = new ModuleMethod(pregexp1, 26, Lit74, 12291);
        modulemethod10.setProperty("source-location", "pregexp.scm:254");
        pregexp$Mnwrap$Mnquantifier$Mnif$Mnany = modulemethod10;
        ModuleMethod modulemethod11 = new ModuleMethod(pregexp1, 27, Lit76, 12291);
        modulemethod11.setProperty("source-location", "pregexp.scm:300");
        pregexp$Mnread$Mnnums = modulemethod11;
        ModuleMethod modulemethod12 = new ModuleMethod(pregexp1, 28, Lit123, 4097);
        modulemethod12.setProperty("source-location", "pregexp.scm:323");
        pregexp$Mninvert$Mnchar$Mnlist = modulemethod12;
        ModuleMethod modulemethod13 = new ModuleMethod(pregexp1, 29, Lit80, 12291);
        modulemethod13.setProperty("source-location", "pregexp.scm:330");
        pregexp$Mnread$Mnchar$Mnlist = modulemethod13;
        ModuleMethod modulemethod14 = new ModuleMethod(pregexp1, 30, Lit124, 24582);
        modulemethod14.setProperty("source-location", "pregexp.scm:368");
        pregexp$Mnstring$Mnmatch = modulemethod14;
        ModuleMethod modulemethod15 = new ModuleMethod(pregexp1, 31, Lit125, 4097);
        modulemethod15.setProperty("source-location", "pregexp.scm:379");
        pregexp$Mnchar$Mnword$Qu = modulemethod15;
        ModuleMethod modulemethod16 = new ModuleMethod(pregexp1, 32, Lit126, 12291);
        modulemethod16.setProperty("source-location", "pregexp.scm:387");
        pregexp$Mnat$Mnword$Mnboundary$Qu = modulemethod16;
        ModuleMethod modulemethod17 = new ModuleMethod(pregexp1, 33, Lit99, 8194);
        modulemethod17.setProperty("source-location", "pregexp.scm:399");
        pregexp$Mncheck$Mnif$Mnin$Mnchar$Mnclass$Qu = modulemethod17;
        ModuleMethod modulemethod18 = new ModuleMethod(pregexp1, 34, Lit127, 8194);
        modulemethod18.setProperty("source-location", "pregexp.scm:429");
        pregexp$Mnlist$Mnref = modulemethod18;
        ModuleMethod modulemethod19 = new ModuleMethod(pregexp1, 35, Lit128, 4097);
        modulemethod19.setProperty("source-location", "pregexp.scm:448");
        pregexp$Mnmake$Mnbackref$Mnlist = modulemethod19;
        ModuleMethod modulemethod20 = new ModuleMethod(pregexp1, 36, null, 0);
        modulemethod20.setProperty("source-location", "pregexp.scm:463");
        lambda$Fn1 = modulemethod20;
        ModuleMethod modulemethod21 = new ModuleMethod(pregexp1, 37, null, 0);
        modulemethod21.setProperty("source-location", "pregexp.scm:551");
        lambda$Fn6 = modulemethod21;
        ModuleMethod modulemethod22 = new ModuleMethod(pregexp1, 38, null, 0);
        modulemethod22.setProperty("source-location", "pregexp.scm:556");
        lambda$Fn7 = modulemethod22;
        ModuleMethod modulemethod23 = new ModuleMethod(pregexp1, 39, null, 0);
        modulemethod23.setProperty("source-location", "pregexp.scm:564");
        lambda$Fn8 = modulemethod23;
        ModuleMethod modulemethod24 = new ModuleMethod(pregexp1, 40, null, 0);
        modulemethod24.setProperty("source-location", "pregexp.scm:573");
        lambda$Fn9 = modulemethod24;
        ModuleMethod modulemethod25 = new ModuleMethod(pregexp1, 41, null, 0);
        modulemethod25.setProperty("source-location", "pregexp.scm:578");
        lambda$Fn10 = modulemethod25;
        ModuleMethod modulemethod26 = new ModuleMethod(pregexp1, 42, Lit101, 24582);
        modulemethod26.setProperty("source-location", "pregexp.scm:459");
        pregexp$Mnmatch$Mnpositions$Mnaux = modulemethod26;
        ModuleMethod modulemethod27 = new ModuleMethod(pregexp1, 43, Lit129, 16388);
        modulemethod27.setProperty("source-location", "pregexp.scm:639");
        pregexp$Mnreplace$Mnaux = modulemethod27;
        ModuleMethod modulemethod28 = new ModuleMethod(pregexp1, 44, Lit130, 4097);
        modulemethod28.setProperty("source-location", "pregexp.scm:665");
        pregexp = modulemethod28;
        ModuleMethod modulemethod29 = new ModuleMethod(pregexp1, 45, Lit114, -4094);
        modulemethod29.setProperty("source-location", "pregexp.scm:670");
        pregexp$Mnmatch$Mnpositions = modulemethod29;
        ModuleMethod modulemethod30 = new ModuleMethod(pregexp1, 46, Lit131, -4094);
        modulemethod30.setProperty("source-location", "pregexp.scm:690");
        pregexp$Mnmatch = modulemethod30;
        ModuleMethod modulemethod31 = new ModuleMethod(pregexp1, 47, Lit132, 8194);
        modulemethod31.setProperty("source-location", "pregexp.scm:700");
        pregexp$Mnsplit = modulemethod31;
        ModuleMethod modulemethod32 = new ModuleMethod(pregexp1, 48, Lit133, 12291);
        modulemethod32.setProperty("source-location", "pregexp.scm:723");
        pregexp$Mnreplace = modulemethod32;
        ModuleMethod modulemethod33 = new ModuleMethod(pregexp1, 49, Lit134, 12291);
        modulemethod33.setProperty("source-location", "pregexp.scm:736");
        pregexp$Mnreplace$St = modulemethod33;
        ModuleMethod modulemethod34 = new ModuleMethod(pregexp1, 50, Lit135, 4097);
        modulemethod34.setProperty("source-location", "pregexp.scm:764");
        pregexp$Mnquote = modulemethod34;
        $instance.run();
    }
}
