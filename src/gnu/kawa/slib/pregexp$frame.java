// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.WrongType;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            pregexp

public class identity extends ModuleBody
{
    public class pregexp.frame0 extends ModuleBody
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
        pregexp.frame staticLink;

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
            staticLink.case$Mnsensitive$Qu = old;
            return Scheme.applyToArgs.apply2(sk, obj);
        }

        Object lambda19()
        {
            staticLink.case$Mnsensitive$Qu = old;
            return Scheme.applyToArgs.apply1(fk);
        }

        public Object lambda5loupOneOfChars(Object obj)
        {
            pregexp.frame1 frame1_1 = new pregexp.frame1();
            frame1_1.staticLink = this;
            frame1_1.chars = obj;
            if (lists.isNull(frame1_1.chars))
            {
                return Scheme.applyToArgs.apply1(fk);
            } else
            {
                return staticLink.lambda3sub(lists.car.apply1(frame1_1.chars), i, sk, frame1_1.lambda$Fn13);
            }
        }

        public Object lambda6loupSeq(Object obj, Object obj1)
        {
            pregexp.frame2 frame2_1 = new pregexp.frame2();
            frame2_1.staticLink = this;
            frame2_1.res = obj;
            if (lists.isNull(frame2_1.res))
            {
                return Scheme.applyToArgs.apply2(sk, obj1);
            } else
            {
                return staticLink.lambda3sub(lists.car.apply1(frame2_1.res), obj1, frame2_1.lambda$Fn14, fk);
            }
        }

        public Object lambda7loupOr(Object obj)
        {
            pregexp.frame3 frame3_1 = new pregexp.frame3();
            frame3_1.staticLink = this;
            frame3_1.res = obj;
            if (lists.isNull(frame3_1.res))
            {
                return Scheme.applyToArgs.apply1(fk);
            } else
            {
                return staticLink.lambda3sub(lists.car.apply1(frame3_1.res), i, frame3_1.lambda$Fn15, frame3_1.lambda$Fn16);
            }
        }

        public Object lambda8loupP(Object obj, Object obj1)
        {
            pregexp.frame4 frame4_1 = new pregexp.frame4();
            frame4_1.staticLink = this;
            frame4_1.k = obj;
            frame4_1.i = obj1;
            if (Scheme.numLss.apply2(frame4_1.k, p) != Boolean.FALSE)
            {
                return staticLink.lambda3sub(re, frame4_1.i, frame4_1.lambda$Fn17, fk);
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

        public pregexp.frame0()
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

    public class pregexp.frame1 extends ModuleBody
    {

        Object chars;
        final ModuleMethod lambda$Fn13;
        pregexp.frame0 staticLink;

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

        public pregexp.frame1()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 1, null, 0);
            modulemethod.setProperty("source-location", "pregexp.scm:508");
            lambda$Fn13 = modulemethod;
        }
    }

    public class pregexp.frame2 extends ModuleBody
    {

        final ModuleMethod lambda$Fn14;
        Object res;
        pregexp.frame0 staticLink;

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

        public pregexp.frame2()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 2, null, 4097);
            modulemethod.setProperty("source-location", "pregexp.scm:519");
            lambda$Fn14 = modulemethod;
        }
    }

    public class pregexp.frame3 extends ModuleBody
    {

        final ModuleMethod lambda$Fn15;
        final ModuleMethod lambda$Fn16;
        Object res;
        pregexp.frame0 staticLink;

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

        public pregexp.frame3()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 3, null, 4097);
            modulemethod.setProperty("source-location", "pregexp.scm:526");
            lambda$Fn15 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 4, null, 0);
            modulemethod1.setProperty("source-location", "pregexp.scm:529");
            lambda$Fn16 = modulemethod1;
        }
    }

    public class pregexp.frame4 extends ModuleBody
    {

        Object i;
        Object k;
        final ModuleMethod lambda$Fn17;
        Object q;
        pregexp.frame0 staticLink;

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
            pregexp.frame5 frame5_1 = new pregexp.frame5();
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
                if (staticLink.maximal$Qu)
                {
                    return staticLink.staticLink.lambda3sub(staticLink.re, frame5_1.i, frame5_1.lambda$Fn18, frame5_1.fk);
                }
                obj2 = frame5_1.lambda26fk();
                if (obj2 == Boolean.FALSE)
                {
                    return staticLink.staticLink.lambda3sub(staticLink.re, frame5_1.i, frame5_1.lambda$Fn19, frame5_1.fk);
                }
            }
            return obj2;
        }

        Object lambda25(Object obj)
        {
            if (staticLink.could$Mnloop$Mninfinitely$Qu ? Scheme.numEqu.apply2(obj, i) != Boolean.FALSE : staticLink.could$Mnloop$Mninfinitely$Qu)
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

        public pregexp.frame4()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 8, null, 4097);
            modulemethod.setProperty("source-location", "pregexp.scm:602");
            lambda$Fn17 = modulemethod;
        }
    }

    public class pregexp.frame5 extends ModuleBody
    {

        Procedure fk;
        Object i;
        Object k;
        final ModuleMethod lambda$Fn18;
        final ModuleMethod lambda$Fn19;
        pregexp.frame4 staticLink;

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
            if (staticLink.staticLink.could$Mnloop$Mninfinitely$Qu ? Scheme.numEqu.apply2(obj, i) != Boolean.FALSE : staticLink.staticLink.could$Mnloop$Mninfinitely$Qu)
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

        public pregexp.frame5()
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
          = new ();
        .staticLink = this;
        ._fld1 = obj;
        .i = obj1;
        .sk = obj2;
        .fk = obj3;
        if (Scheme.isEqv.apply2(._fld1, pregexp.Lit10) != Boolean.FALSE)
        {
            if (Scheme.numEqu.apply2(.i, start) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply2(.sk, .i);
            } else
            {
                return Scheme.applyToArgs.apply1(.fk);
            }
        }
        if (Scheme.isEqv.apply2(._fld1, pregexp.Lit12) != Boolean.FALSE)
        {
            if (Scheme.numGEq.apply2(.i, n) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply2(.sk, .i);
            } else
            {
                return Scheme.applyToArgs.apply1(.fk);
            }
        }
        if (Scheme.isEqv.apply2(._fld1, pregexp.Lit23) != Boolean.FALSE)
        {
            return Scheme.applyToArgs.apply2(.sk, .i);
        }
        if (Scheme.isEqv.apply2(._fld1, pregexp.Lit26) != Boolean.FALSE)
        {
            if (pregexp.isPregexpAtWordBoundary(s, .i, n) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply2(.sk, .i);
            } else
            {
                return Scheme.applyToArgs.apply1(.fk);
            }
        }
        if (Scheme.isEqv.apply2(._fld1, pregexp.Lit28) != Boolean.FALSE)
        {
            if (pregexp.isPregexpAtWordBoundary(s, .i, n) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply1(.fk);
            } else
            {
                return Scheme.applyToArgs.apply2(.sk, .i);
            }
        }
        boolean flag = characters.isChar(._fld1);
        if (flag ? Scheme.numLss.apply2(.i, n) != Boolean.FALSE : flag)
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
            obj27 = .i;
            try
            {
                l1 = ((Number)obj27).intValue();
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "string-ref", 2, obj27);
            }
            if (modulemethod1.apply2(Char.make(strings.stringRef(charsequence3, l1)), ._fld1) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply2(.sk, AddOp.$Pl.apply2(.i, pregexp.Lit8));
            } else
            {
                return Scheme.applyToArgs.apply1(.fk);
            }
        }
        i = 1 & 1 + lists.isPair(._fld1);
        if (i == 0 ? i != 0 : Scheme.numLss.apply2(.i, n) != Boolean.FALSE)
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
            obj25 = .i;
            try
            {
                k1 = ((Number)obj25).intValue();
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "string-ref", 2, obj25);
            }
            if (pregexp.isPregexpCheckIfInCharClass(Char.make(strings.stringRef(charsequence2, k1)), ._fld1) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply2(.sk, AddOp.$Pl.apply2(.i, pregexp.Lit8));
            } else
            {
                return Scheme.applyToArgs.apply1(.fk);
            }
        }
        flag1 = lists.isPair(._fld1);
        if (flag1 ? (obj23 = Scheme.isEqv.apply2(lists.car.apply1(._fld1), pregexp.Lit83)) == Boolean.FALSE ? obj23 != Boolean.FALSE : Scheme.numLss.apply2(.i, n) != Boolean.FALSE : flag1)
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
            obj5 = .i;
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
            obj6 = modulemethod.apply2(lists.cadr.apply1(._fld1), Char.make(c));
            if (obj6 == Boolean.FALSE ? obj6 != Boolean.FALSE : modulemethod.apply2(Char.make(c), lists.caddr.apply1(._fld1)) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply2(.sk, AddOp.$Pl.apply2(.i, pregexp.Lit8));
            } else
            {
                return Scheme.applyToArgs.apply1(.fk);
            }
        }
        if (lists.isPair(._fld1))
        {
            obj7 = lists.car.apply1(._fld1);
            if (Scheme.isEqv.apply2(obj7, pregexp.Lit83) != Boolean.FALSE)
            {
                if (Scheme.numGEq.apply2(.i, n) != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply1(.fk);
                } else
                {
                    aobj3 = new Object[1];
                    aobj3[0] = pregexp.Lit101;
                    return pregexp.pregexpError$V(aobj3);
                }
            }
            if (Scheme.isEqv.apply2(obj7, pregexp.Lit82) != Boolean.FALSE)
            {
                if (Scheme.numGEq.apply2(.i, n) != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply1(.fk);
                } else
                {
                    return .lambda5loupOneOfChars(lists.cdr.apply1(._fld1));
                }
            }
            if (Scheme.isEqv.apply2(obj7, pregexp.Lit17) != Boolean.FALSE)
            {
                if (Scheme.numGEq.apply2(.i, n) != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply1(.fk);
                } else
                {
                    return lambda3sub(lists.cadr.apply1(._fld1), .i, .Fn2, .Fn3);
                }
            }
            if (Scheme.isEqv.apply2(obj7, pregexp.Lit5) != Boolean.FALSE)
            {
                return .lambda6loupSeq(lists.cdr.apply1(._fld1), .i);
            }
            if (Scheme.isEqv.apply2(obj7, pregexp.Lit4) != Boolean.FALSE)
            {
                return .lambda7loupOr(lists.cdr.apply1(._fld1));
            }
            if (Scheme.isEqv.apply2(obj7, pregexp.Lit20) != Boolean.FALSE)
            {
                obj18 = pregexp.pregexpListRef(backrefs, lists.cadr.apply1(._fld1));
                if (obj18 != Boolean.FALSE)
                {
                    obj19 = lists.cdr.apply1(obj18);
                } else
                {
                    aobj2 = new Object[3];
                    aobj2[0] = pregexp.Lit101;
                    aobj2[1] = pregexp.Lit102;
                    aobj2[2] = ._fld1;
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
                    return pregexp.pregexpStringMatch(strings.substring(charsequence1, i1, j1), s, .i, n, .Fn4, .fk);
                } else
                {
                    return Scheme.applyToArgs.apply2(.sk, .i);
                }
            }
            if (Scheme.isEqv.apply2(obj7, pregexp.Lit100) != Boolean.FALSE)
            {
                return lambda3sub(lists.cadr.apply1(._fld1), .i, .Fn5, .fk);
            }
            if (Scheme.isEqv.apply2(obj7, pregexp.Lit103) != Boolean.FALSE)
            {
                if (lambda3sub(lists.cadr.apply1(._fld1), .i, identity, pregexp.lambda$Fn6) != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply2(.sk, .i);
                } else
                {
                    return Scheme.applyToArgs.apply1(.fk);
                }
            }
            if (Scheme.isEqv.apply2(obj7, pregexp.Lit104) != Boolean.FALSE)
            {
                if (lambda3sub(lists.cadr.apply1(._fld1), .i, identity, pregexp.lambda$Fn7) != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply1(.fk);
                } else
                {
                    return Scheme.applyToArgs.apply2(.sk, .i);
                }
            }
            if (Scheme.isEqv.apply2(obj7, pregexp.Lit105) != Boolean.FALSE)
            {
                obj15 = n;
                obj16 = sn;
                n = .i;
                sn = .i;
                obj17 = lambda3sub(LList.list4(pregexp.Lit5, pregexp.Lit106, lists.cadr.apply1(._fld1), pregexp.Lit12), pregexp.Lit73, identity, pregexp.lambda$Fn8);
                n = obj15;
                sn = obj16;
                if (obj17 != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply2(.sk, .i);
                } else
                {
                    return Scheme.applyToArgs.apply1(.fk);
                }
            }
            if (Scheme.isEqv.apply2(obj7, pregexp.Lit107) != Boolean.FALSE)
            {
                obj12 = n;
                obj13 = sn;
                n = .i;
                sn = .i;
                obj14 = lambda3sub(LList.list4(pregexp.Lit5, pregexp.Lit108, lists.cadr.apply1(._fld1), pregexp.Lit12), pregexp.Lit73, identity, pregexp.lambda$Fn9);
                n = obj12;
                sn = obj13;
                if (obj14 != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply1(.fk);
                } else
                {
                    return Scheme.applyToArgs.apply2(.sk, .i);
                }
            }
            if (Scheme.isEqv.apply2(obj7, pregexp.Lit109) != Boolean.FALSE)
            {
                obj11 = lambda3sub(lists.cadr.apply1(._fld1), .i, identity, pregexp.lambda$Fn10);
                if (obj11 != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply2(.sk, obj11);
                } else
                {
                    return Scheme.applyToArgs.apply1(.fk);
                }
            }
            obj8 = Scheme.isEqv.apply2(obj7, pregexp.Lit60);
            if (obj8 == Boolean.FALSE ? Scheme.isEqv.apply2(obj7, pregexp.Lit61) != Boolean.FALSE : obj8 != Boolean.FALSE)
            {
                .old = case$Mnsensitive$Qu;
                case$Mnsensitive$Qu = Scheme.isEqv.apply2(lists.car.apply1(._fld1), pregexp.Lit60);
                return lambda3sub(lists.cadr.apply1(._fld1), .i, .Fn11, .Fn12);
            }
            if (Scheme.isEqv.apply2(obj7, pregexp.Lit68) != Boolean.FALSE)
            {
                Object obj9 = lists.cadr.apply1(._fld1);
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
                .Qu = 1 & k + 1;
                .p = lists.caddr.apply1(._fld1);
                .q = lists.cadddr.apply1(._fld1);
                if (.Qu)
                {
                    Object obj10 = .q;
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
                    flag2 = .Qu;
                }
                .Qu = flag2;
                .re = lists.car.apply1(lists.cddddr.apply1(._fld1));
                return .lambda8loupP(pregexp.Lit73, .i);
            } else
            {
                aobj1 = new Object[1];
                aobj1[0] = pregexp.Lit101;
                return pregexp.pregexpError$V(aobj1);
            }
        }
        if (Scheme.numGEq.apply2(.i, n) != Boolean.FALSE)
        {
            return Scheme.applyToArgs.apply1(.fk);
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

    public .lambda.Fn12()
    {
        ModuleMethod modulemethod = new ModuleMethod(this, 15, pregexp.Lit112, 4097);
        modulemethod.setProperty("source-location", "pregexp.scm:460");
        identity = modulemethod;
    }
}
