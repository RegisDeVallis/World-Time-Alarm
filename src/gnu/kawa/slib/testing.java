// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.IsEqual;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import kawa.lang.Eval;
import kawa.lang.Macro;
import kawa.lang.Pattern;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.parameters;
import kawa.lib.ports;
import kawa.lib.std_syntax;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.readchar;
import kawa.standard.syntax_case;

public class testing extends ModuleBody
{
    public class test.Mnrunner
    {

        public Object aux$Mnvalue;
        public Object count$Mnlist;
        public Object fail$Mncount;
        public Object fail$Mnlist;
        public Object fail$Mnsave;
        public Object group$Mnstack;
        public Object on$Mnbad$Mncount;
        public Object on$Mnbad$Mnend$Mnname;
        public Object on$Mnfinal;
        public Object on$Mngroup$Mnbegin;
        public Object on$Mngroup$Mnend;
        public Object on$Mntest$Mnbegin;
        public Object on$Mntest$Mnend;
        public Object pass$Mncount;
        public Object result$Mnalist;
        public Object run$Mnlist;
        public Object skip$Mncount;
        public Object skip$Mnlist;
        public Object skip$Mnsave;
        public Object total$Mncount;
        public Object xfail$Mncount;
        public Object xpass$Mncount;

        public test.Mnrunner()
        {
        }
    }

    public class frame extends ModuleBody
    {

        Object p;

        public Object lambda4loop(Object obj)
        {
            if (obj == p)
            {
                return lists.cdr.apply1(obj);
            } else
            {
                return lists.cons(lists.car.apply1(obj), lambda4loop(lists.cdr.apply1(obj)));
            }
        }

        public frame()
        {
        }
    }

    public class frame0 extends ModuleBody
    {

        Object error;
        final ModuleMethod lambda$Fn4;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 1)
            {
                if (lambda5(obj, obj1))
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        boolean lambda5(Object obj, Object obj1)
        {
            Object obj2 = Scheme.numGEq.apply2(obj, AddOp.$Mn.apply2(obj1, error));
            boolean flag;
            boolean flag1;
            try
            {
                flag = ((Boolean)obj2).booleanValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj2);
            }
            flag1 = flag;
            if (flag1)
            {
                flag1 = ((Boolean)Scheme.numLEq.apply2(obj, AddOp.$Pl.apply2(obj1, error))).booleanValue();
            }
            return flag1;
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 1)
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

        public frame0()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 1, null, 8194);
            modulemethod.setProperty("source-location", "testing.scm:640");
            lambda$Fn4 = modulemethod;
        }
    }

    public class frame1 extends ModuleBody
    {

        Object first;
        final ModuleMethod lambda$Fn10;
        final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 2, null, 0);
        final ModuleMethod lambda$Fn6 = new ModuleMethod(this, 3, null, 0);
        final ModuleMethod lambda$Fn7;
        final ModuleMethod lambda$Fn8 = new ModuleMethod(this, 5, null, 0);
        final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 6, null, 0);
        Object r;
        LList rest;
        Object saved$Mnrunner;
        Object saved$Mnrunner$1;

        public Object apply0(ModuleMethod modulemethod)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.apply0(modulemethod);

            case 2: // '\002'
                return lambda6();

            case 3: // '\003'
                return lambda7();

            case 4: // '\004'
                return lambda8();

            case 5: // '\005'
                return lambda9();

            case 6: // '\006'
                return lambda10();

            case 7: // '\007'
                return lambda11();
            }
        }

        Object lambda10()
        {
            return Scheme.apply.apply3(testing.test$Mnapply, first, rest);
        }

        Object lambda11()
        {
            return ((Procedure)testing.test$Mnrunner$Mncurrent).apply1(saved$Mnrunner);
        }

        Object lambda6()
        {
            return ((Procedure)testing.test$Mnrunner$Mncurrent).apply1(first);
        }

        Object lambda7()
        {
            return Scheme.apply.apply2(testing.test$Mnapply, rest);
        }

        Object lambda8()
        {
            return ((Procedure)testing.test$Mnrunner$Mncurrent).apply1(saved$Mnrunner$1);
        }

        Object lambda9()
        {
            return ((Procedure)testing.test$Mnrunner$Mncurrent).apply1(r);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.match0(modulemethod, callcontext);

            case 7: // '\007'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;

            case 6: // '\006'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;

            case 5: // '\005'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;

            case 4: // '\004'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;

            case 3: // '\003'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;

            case 2: // '\002'
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            }
        }

        public frame1()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 4, null, 0);
            modulemethod.setProperty("source-location", "testing.scm:897");
            lambda$Fn7 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 7, null, 0);
            modulemethod1.setProperty("source-location", "testing.scm:897");
            lambda$Fn10 = modulemethod1;
        }
    }

    public class frame2 extends ModuleBody
    {

        Object count;
        Object i;
        final ModuleMethod lambda$Fn11;
        Object n;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 8)
            {
                if (lambda12(obj))
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

        boolean lambda12(Object obj)
        {
            i = AddOp.$Pl.apply2(i, testing.Lit13);
            Object obj1 = Scheme.numGEq.apply2(i, n);
            boolean flag;
            boolean flag1;
            try
            {
                flag = ((Boolean)obj1).booleanValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj1);
            }
            flag1 = flag;
            if (flag1)
            {
                flag1 = ((Boolean)Scheme.numLss.apply2(i, AddOp.$Pl.apply2(n, count))).booleanValue();
            }
            return flag1;
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

        public frame2()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 8, null, 4097);
            modulemethod.setProperty("source-location", "testing.scm:903");
            lambda$Fn11 = modulemethod;
        }
    }

    public class frame3 extends ModuleBody
    {

        final ModuleMethod lambda$Fn12;
        LList pred$Mnlist;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 9)
            {
                return lambda13(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda13(Object obj)
        {
            Boolean boolean1 = Boolean.TRUE;
            Object obj1 = pred$Mnlist;
            do
            {
                if (lists.isNull(obj1))
                {
                    return boolean1;
                }
                if (Scheme.applyToArgs.apply2(lists.car.apply1(obj1), obj) == Boolean.FALSE)
                {
                    boolean1 = Boolean.FALSE;
                }
                obj1 = lists.cdr.apply1(obj1);
            } while (true);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 9)
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
            ModuleMethod modulemethod = new ModuleMethod(this, 9, null, 4097);
            modulemethod.setProperty("source-location", "testing.scm:915");
            lambda$Fn12 = modulemethod;
        }
    }

    public class frame4 extends ModuleBody
    {

        final ModuleMethod lambda$Fn13;
        LList pred$Mnlist;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 10)
            {
                return lambda14(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda14(Object obj)
        {
            Boolean boolean1 = Boolean.FALSE;
            Object obj1 = pred$Mnlist;
            do
            {
                if (lists.isNull(obj1))
                {
                    return boolean1;
                }
                if (Scheme.applyToArgs.apply2(lists.car.apply1(obj1), obj) != Boolean.FALSE)
                {
                    boolean1 = Boolean.TRUE;
                }
                obj1 = lists.cdr.apply1(obj1);
            } while (true);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 10)
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
            ModuleMethod modulemethod = new ModuleMethod(this, 10, null, 4097);
            modulemethod.setProperty("source-location", "testing.scm:931");
            lambda$Fn13 = modulemethod;
        }
    }

    public class frame5 extends ModuleBody
    {

        final ModuleMethod lambda$Fn14;
        Object name;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 11)
            {
                if (lambda15(obj))
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

        boolean lambda15(Object obj)
        {
            return IsEqual.apply(name, testing.testRunnerTestName(obj));
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

        public frame5()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 11, null, 4097);
            modulemethod.setProperty("source-location", "testing.scm:971");
            lambda$Fn14 = modulemethod;
        }
    }


    public static final ModuleMethod $Pctest$Mnbegin;
    static final ModuleMethod $Pctest$Mnnull$Mncallback;
    public static final ModuleMethod $Prvt$$Pctest$Mnapproximimate$Eq;
    public static final ModuleMethod $Prvt$$Pctest$Mnas$Mnspecifier;
    public static final Macro $Prvt$$Pctest$Mncomp1body;
    public static final Macro $Prvt$$Pctest$Mncomp2body;
    public static final ModuleMethod $Prvt$$Pctest$Mnend;
    public static final Macro $Prvt$$Pctest$Mnerror;
    public static final Macro $Prvt$$Pctest$Mnevaluate$Mnwith$Mncatch;
    public static final ModuleMethod $Prvt$$Pctest$Mnmatch$Mnall;
    public static final ModuleMethod $Prvt$$Pctest$Mnmatch$Mnany;
    public static final ModuleMethod $Prvt$$Pctest$Mnmatch$Mnnth;
    public static final ModuleMethod $Prvt$$Pctest$Mnon$Mntest$Mnbegin;
    public static final ModuleMethod $Prvt$$Pctest$Mnon$Mntest$Mnend;
    public static final ModuleMethod $Prvt$$Pctest$Mnreport$Mnresult;
    public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist;
    public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist$Ex;
    public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist;
    public static final ModuleMethod $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist$Ex;
    public static final ModuleMethod $Prvt$$Pctest$Mnshould$Mnexecute;
    public static final Macro $Prvt$test$Mngroup;
    public static final testing $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final SimpleSymbol Lit1 = (SimpleSymbol)(new SimpleSymbol("result-kind")).readResolve();
    static final PairWithPosition Lit10;
    static final SyntaxPattern Lit100 = new SyntaxPattern("<\f\007\f\017\f\027\b\f\037\b", new Object[0], 4);
    static final SyntaxTemplate Lit101;
    static final SyntaxPattern Lit102 = new SyntaxPattern(",\f\007\f\017\b\f\027\b", new Object[0], 3);
    static final SyntaxTemplate Lit103;
    static final SimpleSymbol Lit104;
    static final SyntaxTemplate Lit105;
    static final SimpleSymbol Lit106;
    static final SyntaxTemplate Lit107;
    static final SimpleSymbol Lit108;
    static final SyntaxTemplate Lit109;
    static final PairWithPosition Lit11;
    static final SimpleSymbol Lit110;
    static final SyntaxPattern Lit111 = new SyntaxPattern("\\\f\007\f\017\f\027\f\037\f'\b\f/\b", new Object[0], 6);
    static final SyntaxTemplate Lit112;
    static final SyntaxPattern Lit113 = new SyntaxPattern("L\f\007\f\017\f\027\f\037\b\f'\b", new Object[0], 5);
    static final SyntaxTemplate Lit114;
    static final SimpleSymbol Lit115;
    static final SyntaxRules Lit116;
    static final SimpleSymbol Lit117;
    static final SyntaxPattern Lit118 = new SyntaxPattern("L\f\007\f\017\f\027\f\037\b\f'\b", new Object[0], 5);
    static final SyntaxTemplate Lit119;
    static final SimpleSymbol Lit12;
    static final SyntaxPattern Lit120 = new SyntaxPattern("<\f\007\f\017\f\027\b\f\037\b", new Object[0], 4);
    static final SyntaxTemplate Lit121;
    static final SyntaxPattern Lit122 = new SyntaxPattern(",\f\007\f\017\b\f\027\b", new Object[0], 3);
    static final SyntaxTemplate Lit123;
    static final SimpleSymbol Lit124;
    static final SimpleSymbol Lit125;
    static final SyntaxRules Lit126;
    static final SimpleSymbol Lit127;
    static final SimpleSymbol Lit128;
    static final SyntaxRules Lit129;
    static final IntNum Lit13;
    static final SimpleSymbol Lit130;
    static final SimpleSymbol Lit131;
    static final SyntaxRules Lit132;
    static final SimpleSymbol Lit133;
    static final SimpleSymbol Lit134;
    static final SyntaxRules Lit135;
    static final SimpleSymbol Lit136;
    static final SimpleSymbol Lit137;
    static final SyntaxRules Lit138;
    static final SimpleSymbol Lit139;
    static final SimpleSymbol Lit14;
    static final SyntaxRules Lit140;
    static final SimpleSymbol Lit141;
    static final SimpleSymbol Lit142;
    static final SimpleSymbol Lit143;
    static final SimpleSymbol Lit144;
    static final SimpleSymbol Lit145;
    static final SimpleSymbol Lit146;
    static final SimpleSymbol Lit147;
    static final SimpleSymbol Lit148;
    static final SimpleSymbol Lit149;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit150;
    static final SimpleSymbol Lit151;
    static final SimpleSymbol Lit152;
    static final SimpleSymbol Lit153;
    static final SimpleSymbol Lit154;
    static final SimpleSymbol Lit155;
    static final SimpleSymbol Lit156;
    static final SimpleSymbol Lit157;
    static final SimpleSymbol Lit158;
    static final SimpleSymbol Lit159;
    static final SyntaxPattern Lit16 = new SyntaxPattern("L\f\007\f\017\f\027\f\037\b\f'\f/\b", new Object[0], 6);
    static final SimpleSymbol Lit160;
    static final SimpleSymbol Lit161;
    static final SimpleSymbol Lit162;
    static final SimpleSymbol Lit163;
    static final SimpleSymbol Lit164;
    static final SimpleSymbol Lit165;
    static final SyntaxTemplate Lit17;
    static final SyntaxPattern Lit18 = new SyntaxPattern("<\f\007\f\017\f\027\b\f\037\f'\b", new Object[0], 5);
    static final SyntaxTemplate Lit19;
    static final SimpleSymbol Lit2 = (SimpleSymbol)(new SimpleSymbol("skip")).readResolve();
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final SimpleSymbol Lit3 = (SimpleSymbol)(new SimpleSymbol("xfail")).readResolve();
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final SimpleSymbol Lit33;
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit36;
    static final SimpleSymbol Lit37;
    static final SimpleSymbol Lit38;
    static final SimpleSymbol Lit39;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final SimpleSymbol Lit42;
    static final SimpleSymbol Lit43;
    static final SimpleSymbol Lit44;
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit50;
    static final SimpleSymbol Lit51;
    static final SimpleSymbol Lit52;
    static final SimpleSymbol Lit53;
    static final SimpleSymbol Lit54;
    static final SimpleSymbol Lit55;
    static final SimpleSymbol Lit56;
    static final SimpleSymbol Lit57;
    static final SimpleSymbol Lit58;
    static final SimpleSymbol Lit59;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit60;
    static final SimpleSymbol Lit61;
    static final SimpleSymbol Lit62;
    static final SimpleSymbol Lit63;
    static final SimpleSymbol Lit64;
    static final SimpleSymbol Lit65;
    static final SimpleSymbol Lit66;
    static final SimpleSymbol Lit67;
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit70;
    static final SyntaxRules Lit71;
    static final SimpleSymbol Lit72;
    static final SyntaxRules Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75;
    static final SyntaxRules Lit76;
    static final SimpleSymbol Lit77;
    static final SimpleSymbol Lit78;
    static final SimpleSymbol Lit79;
    static final PairWithPosition Lit8;
    static final SimpleSymbol Lit80;
    static final SimpleSymbol Lit81;
    static final SimpleSymbol Lit82;
    static final SimpleSymbol Lit83;
    static final SimpleSymbol Lit84;
    static final SyntaxRules Lit85;
    static final SimpleSymbol Lit86;
    static final SimpleSymbol Lit87;
    static final SimpleSymbol Lit88;
    static final SimpleSymbol Lit89;
    static final SimpleSymbol Lit9;
    static final SyntaxRules Lit90;
    static final SimpleSymbol Lit91;
    static final SimpleSymbol Lit92;
    static final SyntaxRules Lit93;
    static final SimpleSymbol Lit94;
    static final SyntaxPattern Lit95 = new SyntaxPattern(",\f\007\f\017\b\f\027\b", new Object[0], 3);
    static final SyntaxTemplate Lit96;
    static final SyntaxPattern Lit97 = new SyntaxPattern("\034\f\007\b\f\017\b", new Object[0], 2);
    static final SyntaxTemplate Lit98;
    static final SimpleSymbol Lit99;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn2;
    static final ModuleMethod lambda$Fn3;
    public static final ModuleMethod test$Mnapply;
    public static final Macro test$Mnapproximate;
    public static final Macro test$Mnassert;
    public static final Macro test$Mnend;
    public static final Macro test$Mneq;
    public static final Macro test$Mnequal;
    public static final Macro test$Mneqv;
    public static final Macro test$Mnerror;
    public static final Macro test$Mnexpect$Mnfail;
    public static final Macro test$Mngroup$Mnwith$Mncleanup;
    public static Boolean test$Mnlog$Mnto$Mnfile;
    public static final Macro test$Mnmatch$Mnall;
    public static final Macro test$Mnmatch$Mnany;
    public static final ModuleMethod test$Mnmatch$Mnname;
    public static final Macro test$Mnmatch$Mnnth;
    public static final ModuleMethod test$Mnon$Mnbad$Mncount$Mnsimple;
    public static final ModuleMethod test$Mnon$Mnbad$Mnend$Mnname$Mnsimple;
    public static final ModuleMethod test$Mnon$Mnfinal$Mnsimple;
    public static final ModuleMethod test$Mnon$Mngroup$Mnbegin$Mnsimple;
    public static final ModuleMethod test$Mnon$Mngroup$Mnend$Mnsimple;
    static final ModuleMethod test$Mnon$Mntest$Mnbegin$Mnsimple;
    public static final ModuleMethod test$Mnon$Mntest$Mnend$Mnsimple;
    public static final ModuleMethod test$Mnpassed$Qu;
    public static final ModuleMethod test$Mnread$Mneval$Mnstring;
    public static final ModuleMethod test$Mnresult$Mnalist;
    public static final ModuleMethod test$Mnresult$Mnalist$Ex;
    public static final ModuleMethod test$Mnresult$Mnclear;
    public static final ModuleMethod test$Mnresult$Mnkind;
    public static final Macro test$Mnresult$Mnref;
    public static final ModuleMethod test$Mnresult$Mnremove;
    public static final ModuleMethod test$Mnresult$Mnset$Ex;
    static final Class test$Mnrunner = gnu/kawa/slib/test$Mnrunner;
    public static final ModuleMethod test$Mnrunner$Mnaux$Mnvalue;
    public static final ModuleMethod test$Mnrunner$Mnaux$Mnvalue$Ex;
    public static final ModuleMethod test$Mnrunner$Mncreate;
    public static Object test$Mnrunner$Mncurrent;
    public static Object test$Mnrunner$Mnfactory;
    public static final ModuleMethod test$Mnrunner$Mnfail$Mncount;
    public static final ModuleMethod test$Mnrunner$Mnfail$Mncount$Ex;
    public static final ModuleMethod test$Mnrunner$Mnget;
    public static final ModuleMethod test$Mnrunner$Mngroup$Mnpath;
    public static final ModuleMethod test$Mnrunner$Mngroup$Mnstack;
    public static final ModuleMethod test$Mnrunner$Mngroup$Mnstack$Ex;
    public static final ModuleMethod test$Mnrunner$Mnnull;
    public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mncount;
    public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mncount$Ex;
    public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mnend$Mnname;
    public static final ModuleMethod test$Mnrunner$Mnon$Mnbad$Mnend$Mnname$Ex;
    public static final ModuleMethod test$Mnrunner$Mnon$Mnfinal;
    public static final ModuleMethod test$Mnrunner$Mnon$Mnfinal$Ex;
    public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnbegin;
    public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnbegin$Ex;
    public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnend;
    public static final ModuleMethod test$Mnrunner$Mnon$Mngroup$Mnend$Ex;
    public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnbegin;
    public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnbegin$Ex;
    public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnend;
    public static final ModuleMethod test$Mnrunner$Mnon$Mntest$Mnend$Ex;
    public static final ModuleMethod test$Mnrunner$Mnpass$Mncount;
    public static final ModuleMethod test$Mnrunner$Mnpass$Mncount$Ex;
    public static final ModuleMethod test$Mnrunner$Mnreset;
    public static final ModuleMethod test$Mnrunner$Mnsimple;
    public static final ModuleMethod test$Mnrunner$Mnskip$Mncount;
    public static final ModuleMethod test$Mnrunner$Mnskip$Mncount$Ex;
    public static final ModuleMethod test$Mnrunner$Mntest$Mnname;
    public static final ModuleMethod test$Mnrunner$Mnxfail$Mncount;
    public static final ModuleMethod test$Mnrunner$Mnxfail$Mncount$Ex;
    public static final ModuleMethod test$Mnrunner$Mnxpass$Mncount;
    public static final ModuleMethod test$Mnrunner$Mnxpass$Mncount$Ex;
    public static final ModuleMethod test$Mnrunner$Qu;
    public static final Macro test$Mnskip;
    public static final Macro test$Mnwith$Mnrunner;

    static Object $PcTestAnySpecifierMatches(Object obj, Object obj1)
    {
        Boolean boolean1 = Boolean.FALSE;
        Object obj2 = obj;
        do
        {
            if (lists.isNull(obj2))
            {
                return boolean1;
            }
            if ($PcTestSpecificierMatches(lists.car.apply1(obj2), obj1) != Boolean.FALSE)
            {
                boolean1 = Boolean.TRUE;
            }
            obj2 = lists.cdr.apply1(obj2);
        } while (true);
    }

    public static Procedure $PcTestApproximimate$Eq(Object obj)
    {
        frame0 frame0_1 = new frame0();
        frame0_1.error = obj;
        return frame0_1.Fn4;
    }

    public static Object $PcTestAsSpecifier(Object obj)
    {
        if (misc.isProcedure(obj))
        {
            return obj;
        }
        if (numbers.isInteger(obj))
        {
            return $PcTestMatchNth(Lit13, obj);
        }
        if (strings.isString(obj))
        {
            return testMatchName(obj);
        } else
        {
            return misc.error$V("not a valid test specifier", new Object[0]);
        }
    }

    public static void $PcTestBegin(Object obj, Object obj1)
    {
        if (((Procedure)test$Mnrunner$Mncurrent).apply0() == Boolean.FALSE)
        {
            ((Procedure)test$Mnrunner$Mncurrent).apply1(testRunnerCreate());
        }
        Object obj2 = ((Procedure)test$Mnrunner$Mncurrent).apply0();
        gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
        test.Mnrunner mnrunner;
        test.Mnrunner mnrunner1;
        test.Mnrunner mnrunner2;
        Object obj3;
        test.Mnrunner mnrunner3;
        test.Mnrunner mnrunner4;
        test.Mnrunner mnrunner5;
        Object obj4;
        test.Mnrunner mnrunner6;
        test.Mnrunner mnrunner7;
        test.Mnrunner mnrunner8;
        Pair pair;
        test.Mnrunner mnrunner9;
        test.Mnrunner mnrunner10;
        test.Mnrunner mnrunner11;
        try
        {
            mnrunner = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-runner-on-group-begin", 0, obj2);
        }
        applytoargs.apply4(testRunnerOnGroupBegin(mnrunner), obj2, obj, obj1);
        try
        {
            mnrunner1 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "%test-runner-skip-save!", 0, obj2);
        }
        try
        {
            mnrunner2 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "%test-runner-skip-list", 0, obj2);
        }
        obj3 = $PcTestRunnerSkipList(mnrunner2);
        try
        {
            mnrunner3 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "%test-runner-skip-save", 0, obj2);
        }
        $PcTestRunnerSkipSave$Ex(mnrunner1, lists.cons(obj3, $PcTestRunnerSkipSave(mnrunner3)));
        try
        {
            mnrunner4 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "%test-runner-fail-save!", 0, obj2);
        }
        try
        {
            mnrunner5 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception5)
        {
            throw new WrongType(classcastexception5, "%test-runner-fail-list", 0, obj2);
        }
        obj4 = $PcTestRunnerFailList(mnrunner5);
        try
        {
            mnrunner6 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception6)
        {
            throw new WrongType(classcastexception6, "%test-runner-fail-save", 0, obj2);
        }
        $PcTestRunnerFailSave$Ex(mnrunner4, lists.cons(obj4, $PcTestRunnerFailSave(mnrunner6)));
        try
        {
            mnrunner7 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception7)
        {
            throw new WrongType(classcastexception7, "%test-runner-count-list!", 0, obj2);
        }
        try
        {
            mnrunner8 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception8)
        {
            throw new WrongType(classcastexception8, "%test-runner-total-count", 0, obj2);
        }
        pair = lists.cons($PcTestRunnerTotalCount(mnrunner8), obj1);
        try
        {
            mnrunner9 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception9)
        {
            throw new WrongType(classcastexception9, "%test-runner-count-list", 0, obj2);
        }
        $PcTestRunnerCountList$Ex(mnrunner7, lists.cons(pair, $PcTestRunnerCountList(mnrunner9)));
        try
        {
            mnrunner10 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception10)
        {
            throw new WrongType(classcastexception10, "test-runner-group-stack!", 0, obj2);
        }
        try
        {
            mnrunner11 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception11)
        {
            throw new WrongType(classcastexception11, "test-runner-group-stack", 0, obj2);
        }
        testRunnerGroupStack$Ex(mnrunner10, lists.cons(obj, testRunnerGroupStack(mnrunner11)));
    }

    static Object $PcTestComp2(Object obj, Object obj1)
    {
        Pair pair = LList.list3(obj1, LList.list2(Lit15, $PcTestSourceLine2(obj1)), obj);
        Object aobj[] = SyntaxPattern.allocVars(6, null);
        if (Lit16.match(pair, aobj, 0))
        {
            TemplateScope templatescope1 = TemplateScope.make();
            return Lit17.execute(aobj, templatescope1);
        }
        if (Lit18.match(pair, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            return Lit19.execute(aobj, templatescope);
        } else
        {
            return syntax_case.error("syntax-case", pair);
        }
    }

    public static Object $PcTestEnd(Object obj, Object obj1)
    {
        Object obj2 = testRunnerGet();
        test.Mnrunner mnrunner;
        Object obj3;
        Object obj4;
        test.Mnrunner mnrunner1;
        try
        {
            mnrunner = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-runner-group-stack", 0, obj2);
        }
        obj3 = testRunnerGroupStack(mnrunner);
        obj4 = $PcTestFormatLine(obj2);
        try
        {
            mnrunner1 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "test-result-alist!", 0, obj2);
        }
        testResultAlist$Ex(mnrunner1, obj1);
        if (lists.isNull(obj3))
        {
            misc.error$V(strings.stringAppend(new Object[] {
                obj4, "test-end not in a group"
            }), new Object[0]);
        }
        if (obj == Boolean.FALSE ? obj != Boolean.FALSE : !IsEqual.apply(obj, lists.car.apply1(obj3)))
        {
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            test.Mnrunner mnrunner2;
            test.Mnrunner mnrunner3;
            Object obj5;
            Object obj6;
            Object obj7;
            AddOp addop;
            test.Mnrunner mnrunner4;
            Object obj8;
            gnu.kawa.functions.ApplyToArgs applytoargs1;
            test.Mnrunner mnrunner5;
            gnu.kawa.functions.ApplyToArgs applytoargs2;
            test.Mnrunner mnrunner6;
            test.Mnrunner mnrunner7;
            gnu.expr.GenericProc genericproc;
            test.Mnrunner mnrunner8;
            test.Mnrunner mnrunner9;
            gnu.expr.GenericProc genericproc1;
            test.Mnrunner mnrunner10;
            test.Mnrunner mnrunner11;
            gnu.expr.GenericProc genericproc2;
            test.Mnrunner mnrunner12;
            test.Mnrunner mnrunner13;
            gnu.expr.GenericProc genericproc3;
            test.Mnrunner mnrunner14;
            test.Mnrunner mnrunner15;
            gnu.expr.GenericProc genericproc4;
            test.Mnrunner mnrunner16;
            test.Mnrunner mnrunner17;
            test.Mnrunner mnrunner18;
            gnu.kawa.functions.ApplyToArgs applytoargs3;
            test.Mnrunner mnrunner19;
            try
            {
                mnrunner2 = (test.Mnrunner)obj2;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "test-runner-on-bad-end-name", 0, obj2);
            }
            applytoargs.apply4(testRunnerOnBadEndName(mnrunner2), obj2, obj, lists.car.apply1(obj3));
        }
        try
        {
            mnrunner3 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "%test-runner-count-list", 0, obj2);
        }
        obj5 = $PcTestRunnerCountList(mnrunner3);
        obj6 = lists.cdar.apply1(obj5);
        obj7 = lists.caar.apply1(obj5);
        addop = AddOp.$Mn;
        try
        {
            mnrunner4 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "%test-runner-total-count", 0, obj2);
        }
        obj8 = addop.apply2($PcTestRunnerTotalCount(mnrunner4), obj7);
        if (obj6 == Boolean.FALSE ? obj6 != Boolean.FALSE : Scheme.numEqu.apply2(obj6, obj8) == Boolean.FALSE)
        {
            applytoargs1 = Scheme.applyToArgs;
            try
            {
                mnrunner5 = (test.Mnrunner)obj2;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "test-runner-on-bad-count", 0, obj2);
            }
            applytoargs1.apply4(testRunnerOnBadCount(mnrunner5), obj2, obj8, obj6);
        }
        applytoargs2 = Scheme.applyToArgs;
        try
        {
            mnrunner6 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception6)
        {
            throw new WrongType(classcastexception6, "test-runner-on-group-end", 0, obj2);
        }
        applytoargs2.apply2(testRunnerOnGroupEnd(mnrunner6), obj2);
        try
        {
            mnrunner7 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception7)
        {
            throw new WrongType(classcastexception7, "test-runner-group-stack!", 0, obj2);
        }
        genericproc = lists.cdr;
        try
        {
            mnrunner8 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception8)
        {
            throw new WrongType(classcastexception8, "test-runner-group-stack", 0, obj2);
        }
        testRunnerGroupStack$Ex(mnrunner7, genericproc.apply1(testRunnerGroupStack(mnrunner8)));
        try
        {
            mnrunner9 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception9)
        {
            throw new WrongType(classcastexception9, "%test-runner-skip-list!", 0, obj2);
        }
        genericproc1 = lists.car;
        try
        {
            mnrunner10 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception10)
        {
            throw new WrongType(classcastexception10, "%test-runner-skip-save", 0, obj2);
        }
        $PcTestRunnerSkipList$Ex(mnrunner9, genericproc1.apply1($PcTestRunnerSkipSave(mnrunner10)));
        try
        {
            mnrunner11 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception11)
        {
            throw new WrongType(classcastexception11, "%test-runner-skip-save!", 0, obj2);
        }
        genericproc2 = lists.cdr;
        try
        {
            mnrunner12 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception12)
        {
            throw new WrongType(classcastexception12, "%test-runner-skip-save", 0, obj2);
        }
        $PcTestRunnerSkipSave$Ex(mnrunner11, genericproc2.apply1($PcTestRunnerSkipSave(mnrunner12)));
        try
        {
            mnrunner13 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception13)
        {
            throw new WrongType(classcastexception13, "%test-runner-fail-list!", 0, obj2);
        }
        genericproc3 = lists.car;
        try
        {
            mnrunner14 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception14)
        {
            throw new WrongType(classcastexception14, "%test-runner-fail-save", 0, obj2);
        }
        $PcTestRunnerFailList$Ex(mnrunner13, genericproc3.apply1($PcTestRunnerFailSave(mnrunner14)));
        try
        {
            mnrunner15 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception15)
        {
            throw new WrongType(classcastexception15, "%test-runner-fail-save!", 0, obj2);
        }
        genericproc4 = lists.cdr;
        try
        {
            mnrunner16 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception16)
        {
            throw new WrongType(classcastexception16, "%test-runner-fail-save", 0, obj2);
        }
        $PcTestRunnerFailSave$Ex(mnrunner15, genericproc4.apply1($PcTestRunnerFailSave(mnrunner16)));
        try
        {
            mnrunner17 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception17)
        {
            throw new WrongType(classcastexception17, "%test-runner-count-list!", 0, obj2);
        }
        $PcTestRunnerCountList$Ex(mnrunner17, lists.cdr.apply1(obj5));
        try
        {
            mnrunner18 = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception18)
        {
            throw new WrongType(classcastexception18, "test-runner-group-stack", 0, obj2);
        }
        if (lists.isNull(testRunnerGroupStack(mnrunner18)))
        {
            applytoargs3 = Scheme.applyToArgs;
            try
            {
                mnrunner19 = (test.Mnrunner)obj2;
            }
            catch (ClassCastException classcastexception19)
            {
                throw new WrongType(classcastexception19, "test-runner-on-final", 0, obj2);
            }
            return applytoargs3.apply2(testRunnerOnFinal(mnrunner19), obj2);
        } else
        {
            return Values.empty;
        }
    }

    static void $PcTestFinalReport1(Object obj, Object obj1, Object obj2)
    {
        if (Scheme.numGrt.apply2(obj, Lit0) != Boolean.FALSE)
        {
            ports.display(obj1, obj2);
            ports.display(obj, obj2);
            ports.newline(obj2);
        }
    }

    static void $PcTestFinalReportSimple(Object obj, Object obj1)
    {
        test.Mnrunner mnrunner;
        test.Mnrunner mnrunner1;
        test.Mnrunner mnrunner2;
        test.Mnrunner mnrunner3;
        test.Mnrunner mnrunner4;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-runner-pass-count", 0, obj);
        }
        $PcTestFinalReport1(testRunnerPassCount(mnrunner), "# of expected passes      ", obj1);
        try
        {
            mnrunner1 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "test-runner-xfail-count", 0, obj);
        }
        $PcTestFinalReport1(testRunnerXfailCount(mnrunner1), "# of expected failures    ", obj1);
        try
        {
            mnrunner2 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "test-runner-xpass-count", 0, obj);
        }
        $PcTestFinalReport1(testRunnerXpassCount(mnrunner2), "# of unexpected successes ", obj1);
        try
        {
            mnrunner3 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "test-runner-fail-count", 0, obj);
        }
        $PcTestFinalReport1(testRunnerFailCount(mnrunner3), "# of unexpected failures  ", obj1);
        try
        {
            mnrunner4 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "test-runner-skip-count", 0, obj);
        }
        $PcTestFinalReport1(testRunnerSkipCount(mnrunner4), "# of skipped tests        ", obj1);
    }

    static Object $PcTestFormatLine(Object obj)
    {
        test.Mnrunner mnrunner;
        Object obj1;
        Object obj2;
        Object obj3;
        Object obj4;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-result-alist", 0, obj);
        }
        obj1 = testResultAlist(mnrunner);
        obj2 = lists.assq(Lit4, obj1);
        obj3 = lists.assq(Lit5, obj1);
        if (obj2 != Boolean.FALSE)
        {
            obj4 = lists.cdr.apply1(obj2);
        } else
        {
            obj4 = "";
        }
        if (obj3 != Boolean.FALSE)
        {
            Object aobj[] = new Object[4];
            aobj[0] = obj4;
            aobj[1] = ":";
            Object obj5 = lists.cdr.apply1(obj3);
            Number number;
            try
            {
                number = (Number)obj5;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "number->string", 1, obj5);
            }
            aobj[2] = numbers.number$To$String(number);
            aobj[3] = ": ";
            return strings.stringAppend(aobj);
        } else
        {
            return "";
        }
    }

    public static Procedure $PcTestMatchAll$V(Object aobj[])
    {
        frame3 frame3_1 = new frame3();
        frame3_1.Mnlist = LList.makeList(aobj, 0);
        return frame3_1.Fn12;
    }

    public static Procedure $PcTestMatchAny$V(Object aobj[])
    {
        frame4 frame4_1 = new frame4();
        frame4_1.Mnlist = LList.makeList(aobj, 0);
        return frame4_1.Fn13;
    }

    public static Procedure $PcTestMatchNth(Object obj, Object obj1)
    {
        frame2 frame2_1 = new frame2();
        frame2_1.n = obj;
        frame2_1.count = obj1;
        frame2_1.i = Lit0;
        return frame2_1.Fn11;
    }

    static Boolean $PcTestNullCallback(Object obj)
    {
        return Boolean.FALSE;
    }

    static void $PcTestOnBadCountWrite(Object obj, Object obj1, Object obj2, Object obj3)
    {
        ports.display("*** Total number of tests was ", obj3);
        ports.display(obj1, obj3);
        ports.display(" but should be ", obj3);
        ports.display(obj2, obj3);
        ports.display(". ***", obj3);
        ports.newline(obj3);
        ports.display("*** Discrepancy indicates testsuite error or exceptions. ***", obj3);
        ports.newline(obj3);
    }

    public static boolean $PcTestOnTestBegin(Object obj)
    {
        $PcTestShouldExecute(obj);
        gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
        test.Mnrunner mnrunner;
        SimpleSymbol simplesymbol;
        SimpleSymbol simplesymbol1;
        test.Mnrunner mnrunner1;
        Object obj1;
        Object obj2;
        int i;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-runner-on-test-begin", 0, obj);
        }
        applytoargs.apply2(testRunnerOnTestBegin(mnrunner), obj);
        simplesymbol = Lit2;
        simplesymbol1 = Lit1;
        try
        {
            mnrunner1 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "test-result-alist", 0, obj);
        }
        obj1 = lists.assq(simplesymbol1, testResultAlist(mnrunner1));
        if (obj1 != Boolean.FALSE)
        {
            obj2 = lists.cdr.apply1(obj1);
        } else
        {
            obj2 = Boolean.FALSE;
        }
        if (simplesymbol == obj2)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        return 1 & i + 1;
    }

    public static Object $PcTestOnTestEnd(Object obj, Object obj1)
    {
        SimpleSymbol simplesymbol = Lit1;
        SimpleSymbol simplesymbol1 = Lit1;
        test.Mnrunner mnrunner;
        Object obj2;
        Object obj3;
        SimpleSymbol simplesymbol2;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-result-alist", 0, obj);
        }
        obj2 = lists.assq(simplesymbol1, testResultAlist(mnrunner));
        if (obj2 != Boolean.FALSE)
        {
            obj3 = lists.cdr.apply1(obj2);
        } else
        {
            obj3 = Boolean.FALSE;
        }
        if (obj3 == Lit3)
        {
            if (obj1 != Boolean.FALSE)
            {
                simplesymbol2 = Lit9;
            } else
            {
                simplesymbol2 = Lit3;
            }
        } else
        if (obj1 != Boolean.FALSE)
        {
            simplesymbol2 = Lit12;
        } else
        {
            simplesymbol2 = Lit14;
        }
        return testResultSet$Ex(obj, simplesymbol, simplesymbol2);
    }

    public static Object $PcTestReportResult()
    {
        Object obj = testRunnerGet();
        Object obj1 = testResultKind$V(new Object[] {
            obj
        });
        if (Scheme.isEqv.apply2(obj1, Lit12) != Boolean.FALSE)
        {
            test.Mnrunner mnrunner;
            AddOp addop;
            IntNum intnum;
            test.Mnrunner mnrunner1;
            test.Mnrunner mnrunner2;
            AddOp addop1;
            IntNum intnum1;
            test.Mnrunner mnrunner3;
            gnu.kawa.functions.ApplyToArgs applytoargs;
            test.Mnrunner mnrunner4;
            test.Mnrunner mnrunner5;
            AddOp addop2;
            IntNum intnum2;
            test.Mnrunner mnrunner6;
            test.Mnrunner mnrunner7;
            AddOp addop3;
            IntNum intnum3;
            test.Mnrunner mnrunner8;
            test.Mnrunner mnrunner9;
            AddOp addop4;
            IntNum intnum4;
            test.Mnrunner mnrunner10;
            test.Mnrunner mnrunner11;
            AddOp addop5;
            IntNum intnum5;
            test.Mnrunner mnrunner12;
            try
            {
                mnrunner11 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "test-runner-pass-count!", 0, obj);
            }
            addop5 = AddOp.$Pl;
            intnum5 = Lit13;
            try
            {
                mnrunner12 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "test-runner-pass-count", 0, obj);
            }
            testRunnerPassCount$Ex(mnrunner11, addop5.apply2(intnum5, testRunnerPassCount(mnrunner12)));
        } else
        if (Scheme.isEqv.apply2(obj1, Lit14) != Boolean.FALSE)
        {
            try
            {
                mnrunner9 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "test-runner-fail-count!", 0, obj);
            }
            addop4 = AddOp.$Pl;
            intnum4 = Lit13;
            try
            {
                mnrunner10 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "test-runner-fail-count", 0, obj);
            }
            testRunnerFailCount$Ex(mnrunner9, addop4.apply2(intnum4, testRunnerFailCount(mnrunner10)));
        } else
        if (Scheme.isEqv.apply2(obj1, Lit9) != Boolean.FALSE)
        {
            try
            {
                mnrunner7 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "test-runner-xpass-count!", 0, obj);
            }
            addop3 = AddOp.$Pl;
            intnum3 = Lit13;
            try
            {
                mnrunner8 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "test-runner-xpass-count", 0, obj);
            }
            testRunnerXpassCount$Ex(mnrunner7, addop3.apply2(intnum3, testRunnerXpassCount(mnrunner8)));
        } else
        if (Scheme.isEqv.apply2(obj1, Lit3) != Boolean.FALSE)
        {
            try
            {
                mnrunner5 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "test-runner-xfail-count!", 0, obj);
            }
            addop2 = AddOp.$Pl;
            intnum2 = Lit13;
            try
            {
                mnrunner6 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "test-runner-xfail-count", 0, obj);
            }
            testRunnerXfailCount$Ex(mnrunner5, addop2.apply2(intnum2, testRunnerXfailCount(mnrunner6)));
        } else
        {
            try
            {
                mnrunner = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "test-runner-skip-count!", 0, obj);
            }
            addop = AddOp.$Pl;
            intnum = Lit13;
            try
            {
                mnrunner1 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "test-runner-skip-count", 0, obj);
            }
            testRunnerSkipCount$Ex(mnrunner, addop.apply2(intnum, testRunnerSkipCount(mnrunner1)));
        }
        try
        {
            mnrunner2 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "%test-runner-total-count!", 0, obj);
        }
        addop1 = AddOp.$Pl;
        intnum1 = Lit13;
        try
        {
            mnrunner3 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "%test-runner-total-count", 0, obj);
        }
        $PcTestRunnerTotalCount$Ex(mnrunner2, addop1.apply2(intnum1, $PcTestRunnerTotalCount(mnrunner3)));
        applytoargs = Scheme.applyToArgs;
        try
        {
            mnrunner4 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "test-runner-on-test-end", 0, obj);
        }
        return applytoargs.apply2(testRunnerOnTestEnd(mnrunner4), obj);
    }

    static test.Mnrunner $PcTestRunnerAlloc()
    {
        return new test.Mnrunner();
    }

    static Object $PcTestRunnerCountList(test.Mnrunner mnrunner)
    {
        return mnrunner.count$Mnlist;
    }

    static void $PcTestRunnerCountList$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.count$Mnlist = obj;
    }

    public static Object $PcTestRunnerFailList(test.Mnrunner mnrunner)
    {
        return mnrunner.fail$Mnlist;
    }

    public static void $PcTestRunnerFailList$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.fail$Mnlist = obj;
    }

    static Object $PcTestRunnerFailSave(test.Mnrunner mnrunner)
    {
        return mnrunner.fail$Mnsave;
    }

    static void $PcTestRunnerFailSave$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.fail$Mnsave = obj;
    }

    static Object $PcTestRunnerRunList(test.Mnrunner mnrunner)
    {
        return mnrunner.run$Mnlist;
    }

    static void $PcTestRunnerRunList$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.run$Mnlist = obj;
    }

    public static Object $PcTestRunnerSkipList(test.Mnrunner mnrunner)
    {
        return mnrunner.skip$Mnlist;
    }

    public static void $PcTestRunnerSkipList$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.skip$Mnlist = obj;
    }

    static Object $PcTestRunnerSkipSave(test.Mnrunner mnrunner)
    {
        return mnrunner.skip$Mnsave;
    }

    static void $PcTestRunnerSkipSave$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.skip$Mnsave = obj;
    }

    static Object $PcTestRunnerTotalCount(test.Mnrunner mnrunner)
    {
        return mnrunner.total$Mncount;
    }

    static void $PcTestRunnerTotalCount$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.total$Mncount = obj;
    }

    public static Object $PcTestShouldExecute(Object obj)
    {
        test.Mnrunner mnrunner;
        Object obj1;
        int i;
        int j;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "%test-runner-run-list", 0, obj);
        }
        obj1 = $PcTestRunnerRunList(mnrunner);
        if (obj1 == Boolean.TRUE)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            Object obj2 = $PcTestAnySpecifierMatches(obj1, obj);
            Boolean boolean1;
            test.Mnrunner mnrunner1;
            test.Mnrunner mnrunner2;
            try
            {
                boolean1 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "x", -2, obj2);
            }
            if (obj2 != boolean1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
        }
        j = 1 & i + 1;
        if (j == 0) goto _L2; else goto _L1
_L1:
        if (j == 0) goto _L4; else goto _L3
_L3:
        testResultSet$Ex(obj, Lit1, Lit2);
        return Boolean.FALSE;
_L2:
        try
        {
            mnrunner1 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "%test-runner-skip-list", 0, obj);
        }
        if ($PcTestAnySpecifierMatches($PcTestRunnerSkipList(mnrunner1), obj) != Boolean.FALSE) goto _L3; else goto _L4
_L4:
        try
        {
            mnrunner2 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "%test-runner-fail-list", 0, obj);
        }
        if ($PcTestAnySpecifierMatches($PcTestRunnerFailList(mnrunner2), obj) != Boolean.FALSE)
        {
            testResultSet$Ex(obj, Lit1, Lit3);
            return Lit3;
        } else
        {
            return Boolean.TRUE;
        }
    }

    static Pair $PcTestSourceLine2(Object obj)
    {
        Object obj1 = std_syntax.syntaxLine(obj);
        Object obj2 = $PcTestSyntaxFile(obj);
        Object obj3;
        Pair pair;
        if (obj1 != Boolean.FALSE)
        {
            obj3 = LList.list1(lists.cons(Lit5, obj1));
        } else
        {
            obj3 = LList.Empty;
        }
        pair = lists.cons(Lit6, std_syntax.syntaxObject$To$Datum(obj));
        if (obj2 != Boolean.FALSE)
        {
            obj3 = lists.cons(lists.cons(Lit4, obj2), obj3);
        }
        return lists.cons(pair, obj3);
    }

    static Object $PcTestSpecificierMatches(Object obj, Object obj1)
    {
        return Scheme.applyToArgs.apply2(obj, obj1);
    }

    static Object $PcTestSyntaxFile(Object obj)
    {
        return std_syntax.syntaxSource(obj);
    }

    static Object $PcTestWriteResult1(Object obj, Object obj1)
    {
        ports.display("  ", obj1);
        ports.display(lists.car.apply1(obj), obj1);
        ports.display(": ", obj1);
        ports.write(lists.cdr.apply1(obj), obj1);
        ports.newline(obj1);
        return Values.empty;
    }

    public testing()
    {
        ModuleInfo.register(this);
    }

    public static Object isTestPassed$V(Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        Object obj;
        SimpleSymbol simplesymbol;
        test.Mnrunner mnrunner;
        Object obj1;
        Object obj2;
        if (lists.isPair(llist))
        {
            obj = lists.car.apply1(llist);
        } else
        {
            obj = testRunnerGet();
        }
        simplesymbol = Lit1;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-result-alist", 0, obj);
        }
        obj1 = lists.assq(simplesymbol, testResultAlist(mnrunner));
        if (obj1 != Boolean.FALSE)
        {
            obj2 = lists.cdr.apply1(obj1);
        } else
        {
            obj2 = Boolean.FALSE;
        }
        return lists.memq(obj2, Lit11);
    }

    public static boolean isTestRunner(Object obj)
    {
        return obj instanceof test.Mnrunner;
    }

    static Boolean lambda1(Object obj, Object obj1, Object obj2)
    {
        return Boolean.FALSE;
    }

    static Object lambda16(Object obj)
    {
        Pair pair = LList.list2(obj, LList.list2(Lit15, $PcTestSourceLine2(obj)));
        Object aobj[] = SyntaxPattern.allocVars(3, null);
        if (Lit95.match(pair, aobj, 0))
        {
            TemplateScope templatescope1 = TemplateScope.make();
            return Lit96.execute(aobj, templatescope1);
        }
        if (Lit97.match(pair, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            return Lit98.execute(aobj, templatescope);
        } else
        {
            return syntax_case.error("syntax-case", pair);
        }
    }

    static Object lambda17(Object obj)
    {
        Pair pair = LList.list2(obj, LList.list2(Lit15, $PcTestSourceLine2(obj)));
        Object aobj[] = SyntaxPattern.allocVars(4, null);
        if (Lit100.match(pair, aobj, 0))
        {
            TemplateScope templatescope1 = TemplateScope.make();
            return Lit101.execute(aobj, templatescope1);
        }
        if (Lit102.match(pair, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            return Lit103.execute(aobj, templatescope);
        } else
        {
            return syntax_case.error("syntax-case", pair);
        }
    }

    static Object lambda18(Object obj)
    {
        TemplateScope templatescope = TemplateScope.make();
        return $PcTestComp2(Lit105.execute(null, templatescope), obj);
    }

    static Object lambda19(Object obj)
    {
        TemplateScope templatescope = TemplateScope.make();
        return $PcTestComp2(Lit107.execute(null, templatescope), obj);
    }

    static Boolean lambda2(Object obj, Object obj1, Object obj2)
    {
        return Boolean.FALSE;
    }

    static Object lambda20(Object obj)
    {
        TemplateScope templatescope = TemplateScope.make();
        return $PcTestComp2(Lit109.execute(null, templatescope), obj);
    }

    static Object lambda21(Object obj)
    {
        Pair pair = LList.list2(obj, LList.list2(Lit15, $PcTestSourceLine2(obj)));
        Object aobj[] = SyntaxPattern.allocVars(6, null);
        if (Lit111.match(pair, aobj, 0))
        {
            TemplateScope templatescope1 = TemplateScope.make();
            return Lit112.execute(aobj, templatescope1);
        }
        if (Lit113.match(pair, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            return Lit114.execute(aobj, templatescope);
        } else
        {
            return syntax_case.error("syntax-case", pair);
        }
    }

    static Object lambda22(Object obj)
    {
        Pair pair = LList.list2(obj, LList.list2(Lit15, $PcTestSourceLine2(obj)));
        Object aobj[] = SyntaxPattern.allocVars(5, null);
        if (Lit118.match(pair, aobj, 0))
        {
            TemplateScope templatescope2 = TemplateScope.make();
            return Lit119.execute(aobj, templatescope2);
        }
        if (Lit120.match(pair, aobj, 0))
        {
            TemplateScope templatescope1 = TemplateScope.make();
            return Lit121.execute(aobj, templatescope1);
        }
        if (Lit122.match(pair, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            return Lit123.execute(aobj, templatescope);
        } else
        {
            return syntax_case.error("syntax-case", pair);
        }
    }

    static Boolean lambda3(Object obj, Object obj1, Object obj2)
    {
        return Boolean.FALSE;
    }

    public static Object testApply$V(Object obj, Object aobj[])
    {
        frame1 frame1_1 = new frame1();
        frame1_1.first = obj;
        frame1_1.rest = LList.makeList(aobj, 0);
        if (isTestRunner(frame1_1.first))
        {
            frame1_1._fld1 = ((Procedure)test$Mnrunner$Mncurrent).apply0();
            return misc.dynamicWind(frame1_1.Fn5, frame1_1.Fn6, frame1_1.Fn7);
        }
        Object obj1 = ((Procedure)test$Mnrunner$Mncurrent).apply0();
        Object obj2;
        if (obj1 != Boolean.FALSE)
        {
            test.Mnrunner mnrunner1;
            Object obj3;
            try
            {
                mnrunner1 = (test.Mnrunner)obj1;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "%test-runner-run-list", 0, obj1);
            }
            obj3 = $PcTestRunnerRunList(mnrunner1);
            if (lists.isNull(frame1_1.rest))
            {
                gnu.kawa.functions.ApplyToArgs applytoargs;
                test.Mnrunner mnrunner;
                test.Mnrunner mnrunner2;
                Pair pair;
                test.Mnrunner mnrunner3;
                test.Mnrunner mnrunner4;
                LList llist;
                try
                {
                    mnrunner4 = (test.Mnrunner)obj1;
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "%test-runner-run-list!", 0, obj1);
                }
                try
                {
                    llist = (LList)obj3;
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "reverse!", 1, obj3);
                }
                $PcTestRunnerRunList$Ex(mnrunner4, lists.reverse$Ex(llist));
                return Scheme.applyToArgs.apply1(frame1_1.first);
            }
            try
            {
                mnrunner2 = (test.Mnrunner)obj1;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "%test-runner-run-list!", 0, obj1);
            }
            if (obj3 == Boolean.TRUE)
            {
                pair = LList.list1(frame1_1.first);
            } else
            {
                pair = lists.cons(frame1_1.first, obj3);
            }
            $PcTestRunnerRunList$Ex(mnrunner2, pair);
            Scheme.apply.apply2(test$Mnapply, frame1_1.rest);
            try
            {
                mnrunner3 = (test.Mnrunner)obj1;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "%test-runner-run-list!", 0, obj1);
            }
            $PcTestRunnerRunList$Ex(mnrunner3, obj3);
            return Values.empty;
        }
        frame1_1.r = testRunnerCreate();
        frame1_1.Mnrunner = ((Procedure)test$Mnrunner$Mncurrent).apply0();
        misc.dynamicWind(frame1_1.Fn8, frame1_1.Fn9, frame1_1.Fn10);
        applytoargs = Scheme.applyToArgs;
        obj2 = frame1_1.r;
        try
        {
            mnrunner = (test.Mnrunner)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-runner-on-final", 0, obj2);
        }
        return applytoargs.apply2(testRunnerOnFinal(mnrunner), frame1_1.r);
    }

    public static Procedure testMatchName(Object obj)
    {
        frame5 frame5_1 = new frame5();
        frame5_1.name = obj;
        return frame5_1.Fn14;
    }

    public static void testOnBadCountSimple(Object obj, Object obj1, Object obj2)
    {
        $PcTestOnBadCountWrite(obj, obj1, obj2, ports.current$Mnoutput$Mnport.apply0());
        test.Mnrunner mnrunner;
        Object obj3;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-runner-aux-value", 0, obj);
        }
        obj3 = testRunnerAuxValue(mnrunner);
        if (ports.isOutputPort(obj3))
        {
            $PcTestOnBadCountWrite(obj, obj1, obj2, obj3);
        }
    }

    public static Object testOnBadEndNameSimple(Object obj, Object obj1, Object obj2)
    {
        Object aobj[] = new Object[5];
        aobj[0] = $PcTestFormatLine(obj);
        aobj[1] = "test-end ";
        aobj[2] = obj1;
        aobj[3] = " does not match test-begin ";
        aobj[4] = obj2;
        return misc.error$V(strings.stringAppend(aobj), new Object[0]);
    }

    public static void testOnFinalSimple(Object obj)
    {
        $PcTestFinalReportSimple(obj, ports.current$Mnoutput$Mnport.apply0());
        test.Mnrunner mnrunner;
        Object obj1;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-runner-aux-value", 0, obj);
        }
        obj1 = testRunnerAuxValue(mnrunner);
        if (ports.isOutputPort(obj1))
        {
            $PcTestFinalReportSimple(obj, obj1);
        }
    }

    public static Boolean testOnGroupBeginSimple(Object obj, Object obj1, Object obj2)
    {
        test.Mnrunner mnrunner;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-runner-group-stack", 0, obj);
        }
        if (lists.isNull(testRunnerGroupStack(mnrunner)))
        {
            ports.display("%%%% Starting test ");
            ports.display(obj1);
            test.Mnrunner mnrunner1;
            Object obj3;
            Object obj4;
            Path path;
            gnu.mapping.OutPort outport;
            test.Mnrunner mnrunner2;
            if (strings.isString(Boolean.TRUE))
            {
                obj4 = Boolean.TRUE;
            } else
            {
                obj4 = strings.stringAppend(new Object[] {
                    obj1, ".log"
                });
            }
            try
            {
                path = Path.valueOf(obj4);
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "open-output-file", 1, obj4);
            }
            outport = ports.openOutputFile(path);
            ports.display("%%%% Starting test ", outport);
            ports.display(obj1, outport);
            ports.newline(outport);
            try
            {
                mnrunner2 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "test-runner-aux-value!", 0, obj);
            }
            testRunnerAuxValue$Ex(mnrunner2, outport);
            ports.display("  (Writing full log to \"");
            ports.display(obj4);
            ports.display("\")");
            ports.newline();
        }
        try
        {
            mnrunner1 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "test-runner-aux-value", 0, obj);
        }
        obj3 = testRunnerAuxValue(mnrunner1);
        if (ports.isOutputPort(obj3))
        {
            ports.display("Group begin: ", obj3);
            ports.display(obj1, obj3);
            ports.newline(obj3);
        }
        return Boolean.FALSE;
    }

    public static Boolean testOnGroupEndSimple(Object obj)
    {
        test.Mnrunner mnrunner;
        Object obj1;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-runner-aux-value", 0, obj);
        }
        obj1 = testRunnerAuxValue(mnrunner);
        if (ports.isOutputPort(obj1))
        {
            ports.display("Group end: ", obj1);
            gnu.expr.GenericProc genericproc = lists.car;
            test.Mnrunner mnrunner1;
            try
            {
                mnrunner1 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "test-runner-group-stack", 0, obj);
            }
            ports.display(genericproc.apply1(testRunnerGroupStack(mnrunner1)), obj1);
            ports.newline(obj1);
        }
        return Boolean.FALSE;
    }

    static Object testOnTestBeginSimple(Object obj)
    {
        test.Mnrunner mnrunner;
        Object obj1;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-runner-aux-value", 0, obj);
        }
        obj1 = testRunnerAuxValue(mnrunner);
        if (ports.isOutputPort(obj1))
        {
            test.Mnrunner mnrunner1;
            Object obj2;
            Object obj3;
            Object obj4;
            Object obj5;
            Object obj6;
            try
            {
                mnrunner1 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "test-result-alist", 0, obj);
            }
            obj2 = testResultAlist(mnrunner1);
            obj3 = lists.assq(Lit4, obj2);
            obj4 = lists.assq(Lit5, obj2);
            obj5 = lists.assq(Lit6, obj2);
            obj6 = lists.assq(Lit7, obj2);
            ports.display("Test begin:", obj1);
            ports.newline(obj1);
            if (obj6 != Boolean.FALSE)
            {
                $PcTestWriteResult1(obj6, obj1);
            }
            if (obj3 != Boolean.FALSE)
            {
                $PcTestWriteResult1(obj3, obj1);
            }
            if (obj4 != Boolean.FALSE)
            {
                $PcTestWriteResult1(obj4, obj1);
            }
            if (obj3 != Boolean.FALSE)
            {
                return $PcTestWriteResult1(obj5, obj1);
            } else
            {
                return Values.empty;
            }
        } else
        {
            return Values.empty;
        }
    }

    public static Object testOnTestEndSimple(Object obj)
    {
        test.Mnrunner mnrunner;
        Object obj1;
        SimpleSymbol simplesymbol;
        test.Mnrunner mnrunner1;
        Object obj2;
        Object obj3;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-runner-aux-value", 0, obj);
        }
        obj1 = testRunnerAuxValue(mnrunner);
        simplesymbol = Lit1;
        try
        {
            mnrunner1 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "test-result-alist", 0, obj);
        }
        obj2 = lists.assq(simplesymbol, testResultAlist(mnrunner1));
        if (obj2 != Boolean.FALSE)
        {
            obj3 = lists.cdr.apply1(obj2);
        } else
        {
            obj3 = Boolean.FALSE;
        }
        if (lists.memq(obj3, Lit8) != Boolean.FALSE)
        {
            test.Mnrunner mnrunner2;
            Object obj4;
            Object obj5;
            test.Mnrunner mnrunner3;
            Object obj6;
            Object obj7;
            Object obj8;
            Object obj9;
            String s;
            try
            {
                mnrunner3 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "test-result-alist", 0, obj);
            }
            obj6 = testResultAlist(mnrunner3);
            obj7 = lists.assq(Lit4, obj6);
            obj8 = lists.assq(Lit5, obj6);
            obj9 = lists.assq(Lit7, obj6);
            if (obj7 != Boolean.FALSE || obj8 != Boolean.FALSE)
            {
                if (obj7 != Boolean.FALSE)
                {
                    ports.display(lists.cdr.apply1(obj7));
                }
                ports.display(":");
                if (obj8 != Boolean.FALSE)
                {
                    ports.display(lists.cdr.apply1(obj8));
                }
                ports.display(": ");
            }
            if (obj3 == Lit9)
            {
                s = "XPASS";
            } else
            {
                s = "FAIL";
            }
            ports.display(s);
            if (obj9 != Boolean.FALSE)
            {
                ports.display(" ");
                ports.display(lists.cdr.apply1(obj9));
            }
            ports.newline();
        }
        if (ports.isOutputPort(obj1))
        {
            ports.display("Test end:", obj1);
            ports.newline(obj1);
            try
            {
                mnrunner2 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "test-result-alist", 0, obj);
            }
            for (obj4 = testResultAlist(mnrunner2); lists.isPair(obj4); obj4 = lists.cdr.apply1(obj4))
            {
                obj5 = lists.car.apply1(obj4);
                if (lists.memq(lists.car.apply1(obj5), Lit10) == Boolean.FALSE)
                {
                    $PcTestWriteResult1(obj5, obj1);
                }
            }

            return Values.empty;
        } else
        {
            return Values.empty;
        }
    }

    public static Object testReadEvalString(Object obj)
    {
        CharSequence charsequence;
        gnu.mapping.InPort inport;
        Object obj1;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "open-input-string", 1, obj);
        }
        inport = ports.openInputString(charsequence);
        obj1 = ports.read(inport);
        if (ports.isEofObject(readchar.readChar.apply1(inport)))
        {
            return Eval.eval.apply1(obj1);
        } else
        {
            return misc.error$V("(not at eof)", new Object[0]);
        }
    }

    public static Object testResultAlist(test.Mnrunner mnrunner)
    {
        return mnrunner.result$Mnalist;
    }

    public static void testResultAlist$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.result$Mnalist = obj;
    }

    public static void testResultClear(Object obj)
    {
        test.Mnrunner mnrunner;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-result-alist!", 0, obj);
        }
        testResultAlist$Ex(mnrunner, LList.Empty);
    }

    public static Object testResultKind$V(Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        Object obj;
        SimpleSymbol simplesymbol;
        test.Mnrunner mnrunner;
        Object obj1;
        if (lists.isPair(llist))
        {
            obj = lists.car.apply1(llist);
        } else
        {
            obj = ((Procedure)test$Mnrunner$Mncurrent).apply0();
        }
        simplesymbol = Lit1;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-result-alist", 0, obj);
        }
        obj1 = lists.assq(simplesymbol, testResultAlist(mnrunner));
        if (obj1 != Boolean.FALSE)
        {
            return lists.cdr.apply1(obj1);
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static void testResultRemove(Object obj, Object obj1)
    {
        frame frame6 = new frame();
        test.Mnrunner mnrunner;
        Object obj2;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-result-alist", 0, obj);
        }
        obj2 = testResultAlist(mnrunner);
        frame6.p = lists.assq(obj1, obj2);
        if (frame6.p != Boolean.FALSE)
        {
            test.Mnrunner mnrunner1;
            try
            {
                mnrunner1 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "test-result-alist!", 0, obj);
            }
            testResultAlist$Ex(mnrunner1, frame6.lambda4loop(obj2));
        }
    }

    public static Object testResultSet$Ex(Object obj, Object obj1, Object obj2)
    {
        test.Mnrunner mnrunner;
        Object obj3;
        Object obj4;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-result-alist", 0, obj);
        }
        obj3 = testResultAlist(mnrunner);
        obj4 = lists.assq(obj1, obj3);
        if (obj4 != Boolean.FALSE)
        {
            test.Mnrunner mnrunner1;
            Pair pair;
            try
            {
                pair = (Pair)obj4;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "set-cdr!", 1, obj4);
            }
            lists.setCdr$Ex(pair, obj2);
            return Values.empty;
        }
        try
        {
            mnrunner1 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "test-result-alist!", 0, obj);
        }
        testResultAlist$Ex(mnrunner1, lists.cons(lists.cons(obj1, obj2), obj3));
        return Values.empty;
    }

    public static Object testRunnerAuxValue(test.Mnrunner mnrunner)
    {
        return mnrunner.aux$Mnvalue;
    }

    public static void testRunnerAuxValue$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.aux$Mnvalue = obj;
    }

    public static Object testRunnerCreate()
    {
        return Scheme.applyToArgs.apply1(((Procedure)test$Mnrunner$Mnfactory).apply0());
    }

    public static Object testRunnerFailCount(test.Mnrunner mnrunner)
    {
        return mnrunner.fail$Mncount;
    }

    public static void testRunnerFailCount$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.fail$Mncount = obj;
    }

    public static Object testRunnerGet()
    {
        Object obj = ((Procedure)test$Mnrunner$Mncurrent).apply0();
        if (obj == Boolean.FALSE)
        {
            misc.error$V("test-runner not initialized - test-begin missing?", new Object[0]);
        }
        return obj;
    }

    public static LList testRunnerGroupPath(Object obj)
    {
        test.Mnrunner mnrunner;
        Object obj1;
        LList llist;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-runner-group-stack", 0, obj);
        }
        obj1 = testRunnerGroupStack(mnrunner);
        try
        {
            llist = (LList)obj1;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "reverse", 1, obj1);
        }
        return lists.reverse(llist);
    }

    public static Object testRunnerGroupStack(test.Mnrunner mnrunner)
    {
        return mnrunner.group$Mnstack;
    }

    public static void testRunnerGroupStack$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.group$Mnstack = obj;
    }

    public static test.Mnrunner testRunnerNull()
    {
        test.Mnrunner mnrunner = $PcTestRunnerAlloc();
        testRunnerReset(mnrunner);
        testRunnerOnGroupBegin$Ex(mnrunner, lambda$Fn1);
        testRunnerOnGroupEnd$Ex(mnrunner, $Pctest$Mnnull$Mncallback);
        testRunnerOnFinal$Ex(mnrunner, $Pctest$Mnnull$Mncallback);
        testRunnerOnTestBegin$Ex(mnrunner, $Pctest$Mnnull$Mncallback);
        testRunnerOnTestEnd$Ex(mnrunner, $Pctest$Mnnull$Mncallback);
        testRunnerOnBadCount$Ex(mnrunner, lambda$Fn2);
        testRunnerOnBadEndName$Ex(mnrunner, lambda$Fn3);
        return mnrunner;
    }

    public static Object testRunnerOnBadCount(test.Mnrunner mnrunner)
    {
        return mnrunner.on$Mnbad$Mncount;
    }

    public static void testRunnerOnBadCount$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.on$Mnbad$Mncount = obj;
    }

    public static Object testRunnerOnBadEndName(test.Mnrunner mnrunner)
    {
        return mnrunner.on$Mnbad$Mnend$Mnname;
    }

    public static void testRunnerOnBadEndName$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.on$Mnbad$Mnend$Mnname = obj;
    }

    public static Object testRunnerOnFinal(test.Mnrunner mnrunner)
    {
        return mnrunner.on$Mnfinal;
    }

    public static void testRunnerOnFinal$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.on$Mnfinal = obj;
    }

    public static Object testRunnerOnGroupBegin(test.Mnrunner mnrunner)
    {
        return mnrunner.on$Mngroup$Mnbegin;
    }

    public static void testRunnerOnGroupBegin$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.on$Mngroup$Mnbegin = obj;
    }

    public static Object testRunnerOnGroupEnd(test.Mnrunner mnrunner)
    {
        return mnrunner.on$Mngroup$Mnend;
    }

    public static void testRunnerOnGroupEnd$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.on$Mngroup$Mnend = obj;
    }

    public static Object testRunnerOnTestBegin(test.Mnrunner mnrunner)
    {
        return mnrunner.on$Mntest$Mnbegin;
    }

    public static void testRunnerOnTestBegin$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.on$Mntest$Mnbegin = obj;
    }

    public static Object testRunnerOnTestEnd(test.Mnrunner mnrunner)
    {
        return mnrunner.on$Mntest$Mnend;
    }

    public static void testRunnerOnTestEnd$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.on$Mntest$Mnend = obj;
    }

    public static Object testRunnerPassCount(test.Mnrunner mnrunner)
    {
        return mnrunner.pass$Mncount;
    }

    public static void testRunnerPassCount$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.pass$Mncount = obj;
    }

    public static void testRunnerReset(Object obj)
    {
        test.Mnrunner mnrunner;
        test.Mnrunner mnrunner1;
        test.Mnrunner mnrunner2;
        test.Mnrunner mnrunner3;
        test.Mnrunner mnrunner4;
        test.Mnrunner mnrunner5;
        test.Mnrunner mnrunner6;
        test.Mnrunner mnrunner7;
        test.Mnrunner mnrunner8;
        test.Mnrunner mnrunner9;
        test.Mnrunner mnrunner10;
        test.Mnrunner mnrunner11;
        test.Mnrunner mnrunner12;
        test.Mnrunner mnrunner13;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-result-alist!", 0, obj);
        }
        testResultAlist$Ex(mnrunner, LList.Empty);
        try
        {
            mnrunner1 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "test-runner-pass-count!", 0, obj);
        }
        testRunnerPassCount$Ex(mnrunner1, Lit0);
        try
        {
            mnrunner2 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "test-runner-fail-count!", 0, obj);
        }
        testRunnerFailCount$Ex(mnrunner2, Lit0);
        try
        {
            mnrunner3 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "test-runner-xpass-count!", 0, obj);
        }
        testRunnerXpassCount$Ex(mnrunner3, Lit0);
        try
        {
            mnrunner4 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "test-runner-xfail-count!", 0, obj);
        }
        testRunnerXfailCount$Ex(mnrunner4, Lit0);
        try
        {
            mnrunner5 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception5)
        {
            throw new WrongType(classcastexception5, "test-runner-skip-count!", 0, obj);
        }
        testRunnerSkipCount$Ex(mnrunner5, Lit0);
        try
        {
            mnrunner6 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception6)
        {
            throw new WrongType(classcastexception6, "%test-runner-total-count!", 0, obj);
        }
        $PcTestRunnerTotalCount$Ex(mnrunner6, Lit0);
        try
        {
            mnrunner7 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception7)
        {
            throw new WrongType(classcastexception7, "%test-runner-count-list!", 0, obj);
        }
        $PcTestRunnerCountList$Ex(mnrunner7, LList.Empty);
        try
        {
            mnrunner8 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception8)
        {
            throw new WrongType(classcastexception8, "%test-runner-run-list!", 0, obj);
        }
        $PcTestRunnerRunList$Ex(mnrunner8, Boolean.TRUE);
        try
        {
            mnrunner9 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception9)
        {
            throw new WrongType(classcastexception9, "%test-runner-skip-list!", 0, obj);
        }
        $PcTestRunnerSkipList$Ex(mnrunner9, LList.Empty);
        try
        {
            mnrunner10 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception10)
        {
            throw new WrongType(classcastexception10, "%test-runner-fail-list!", 0, obj);
        }
        $PcTestRunnerFailList$Ex(mnrunner10, LList.Empty);
        try
        {
            mnrunner11 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception11)
        {
            throw new WrongType(classcastexception11, "%test-runner-skip-save!", 0, obj);
        }
        $PcTestRunnerSkipSave$Ex(mnrunner11, LList.Empty);
        try
        {
            mnrunner12 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception12)
        {
            throw new WrongType(classcastexception12, "%test-runner-fail-save!", 0, obj);
        }
        $PcTestRunnerFailSave$Ex(mnrunner12, LList.Empty);
        try
        {
            mnrunner13 = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception13)
        {
            throw new WrongType(classcastexception13, "test-runner-group-stack!", 0, obj);
        }
        testRunnerGroupStack$Ex(mnrunner13, LList.Empty);
    }

    public static test.Mnrunner testRunnerSimple()
    {
        test.Mnrunner mnrunner = $PcTestRunnerAlloc();
        testRunnerReset(mnrunner);
        testRunnerOnGroupBegin$Ex(mnrunner, test$Mnon$Mngroup$Mnbegin$Mnsimple);
        testRunnerOnGroupEnd$Ex(mnrunner, test$Mnon$Mngroup$Mnend$Mnsimple);
        testRunnerOnFinal$Ex(mnrunner, test$Mnon$Mnfinal$Mnsimple);
        testRunnerOnTestBegin$Ex(mnrunner, test$Mnon$Mntest$Mnbegin$Mnsimple);
        testRunnerOnTestEnd$Ex(mnrunner, test$Mnon$Mntest$Mnend$Mnsimple);
        testRunnerOnBadCount$Ex(mnrunner, test$Mnon$Mnbad$Mncount$Mnsimple);
        testRunnerOnBadEndName$Ex(mnrunner, test$Mnon$Mnbad$Mnend$Mnname$Mnsimple);
        return mnrunner;
    }

    public static Object testRunnerSkipCount(test.Mnrunner mnrunner)
    {
        return mnrunner.skip$Mncount;
    }

    public static void testRunnerSkipCount$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.skip$Mncount = obj;
    }

    public static Object testRunnerTestName(Object obj)
    {
        SimpleSymbol simplesymbol = Lit7;
        test.Mnrunner mnrunner;
        Object obj1;
        try
        {
            mnrunner = (test.Mnrunner)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "test-result-alist", 0, obj);
        }
        obj1 = lists.assq(simplesymbol, testResultAlist(mnrunner));
        if (obj1 != Boolean.FALSE)
        {
            return lists.cdr.apply1(obj1);
        } else
        {
            return "";
        }
    }

    public static Object testRunnerXfailCount(test.Mnrunner mnrunner)
    {
        return mnrunner.xfail$Mncount;
    }

    public static void testRunnerXfailCount$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.xfail$Mncount = obj;
    }

    public static Object testRunnerXpassCount(test.Mnrunner mnrunner)
    {
        return mnrunner.xpass$Mncount;
    }

    public static void testRunnerXpassCount$Ex(test.Mnrunner mnrunner, Object obj)
    {
        mnrunner.xpass$Mncount = obj;
    }

    public Object apply0(ModuleMethod modulemethod)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply0(modulemethod);

        case 53: // '5'
            return testRunnerNull();

        case 54: // '6'
            return testRunnerSimple();

        case 55: // '7'
            return testRunnerGet();

        case 56: // '8'
            return testRunnerCreate();

        case 72: // 'H'
            return $PcTestReportResult();
        }
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        case 14: // '\016'
        case 16: // '\020'
        case 18: // '\022'
        case 20: // '\024'
        case 22: // '\026'
        case 24: // '\030'
        case 26: // '\032'
        case 28: // '\034'
        case 30: // '\036'
        case 32: // ' '
        case 34: // '"'
        case 36: // '$'
        case 38: // '&'
        case 40: // '('
        case 42: // '*'
        case 44: // ','
        case 46: // '.'
        case 50: // '2'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
        case 54: // '6'
        case 55: // '7'
        case 56: // '8'
        case 58: // ':'
        case 59: // ';'
        case 61: // '='
        case 62: // '>'
        case 64: // '@'
        case 67: // 'C'
        case 69: // 'E'
        case 70: // 'F'
        case 71: // 'G'
        case 72: // 'H'
        case 74: // 'J'
        case 84: // 'T'
        case 85: // 'U'
        case 86: // 'V'
        case 87: // 'W'
        default:
            return super.apply1(modulemethod, obj);

        case 12: // '\f'
            if (isTestRunner(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 13: // '\r'
            test.Mnrunner mnrunner;
            test.Mnrunner mnrunner1;
            test.Mnrunner mnrunner2;
            test.Mnrunner mnrunner3;
            test.Mnrunner mnrunner4;
            test.Mnrunner mnrunner5;
            test.Mnrunner mnrunner6;
            test.Mnrunner mnrunner7;
            test.Mnrunner mnrunner8;
            test.Mnrunner mnrunner9;
            test.Mnrunner mnrunner10;
            test.Mnrunner mnrunner11;
            test.Mnrunner mnrunner12;
            test.Mnrunner mnrunner13;
            test.Mnrunner mnrunner14;
            test.Mnrunner mnrunner15;
            test.Mnrunner mnrunner16;
            try
            {
                mnrunner16 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception16)
            {
                throw new WrongType(classcastexception16, "test-runner-pass-count", 1, obj);
            }
            return testRunnerPassCount(mnrunner16);

        case 15: // '\017'
            try
            {
                mnrunner15 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception15)
            {
                throw new WrongType(classcastexception15, "test-runner-fail-count", 1, obj);
            }
            return testRunnerFailCount(mnrunner15);

        case 17: // '\021'
            try
            {
                mnrunner14 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception14)
            {
                throw new WrongType(classcastexception14, "test-runner-xpass-count", 1, obj);
            }
            return testRunnerXpassCount(mnrunner14);

        case 19: // '\023'
            try
            {
                mnrunner13 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception13)
            {
                throw new WrongType(classcastexception13, "test-runner-xfail-count", 1, obj);
            }
            return testRunnerXfailCount(mnrunner13);

        case 21: // '\025'
            try
            {
                mnrunner12 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "test-runner-skip-count", 1, obj);
            }
            return testRunnerSkipCount(mnrunner12);

        case 23: // '\027'
            try
            {
                mnrunner11 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "%test-runner-skip-list", 1, obj);
            }
            return $PcTestRunnerSkipList(mnrunner11);

        case 25: // '\031'
            try
            {
                mnrunner10 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "%test-runner-fail-list", 1, obj);
            }
            return $PcTestRunnerFailList(mnrunner10);

        case 27: // '\033'
            try
            {
                mnrunner9 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "test-runner-group-stack", 1, obj);
            }
            return testRunnerGroupStack(mnrunner9);

        case 29: // '\035'
            try
            {
                mnrunner8 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "test-runner-on-test-begin", 1, obj);
            }
            return testRunnerOnTestBegin(mnrunner8);

        case 31: // '\037'
            try
            {
                mnrunner7 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "test-runner-on-test-end", 1, obj);
            }
            return testRunnerOnTestEnd(mnrunner7);

        case 33: // '!'
            try
            {
                mnrunner6 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "test-runner-on-group-begin", 1, obj);
            }
            return testRunnerOnGroupBegin(mnrunner6);

        case 35: // '#'
            try
            {
                mnrunner5 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "test-runner-on-group-end", 1, obj);
            }
            return testRunnerOnGroupEnd(mnrunner5);

        case 37: // '%'
            try
            {
                mnrunner4 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "test-runner-on-final", 1, obj);
            }
            return testRunnerOnFinal(mnrunner4);

        case 39: // '\''
            try
            {
                mnrunner3 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "test-runner-on-bad-count", 1, obj);
            }
            return testRunnerOnBadCount(mnrunner3);

        case 41: // ')'
            try
            {
                mnrunner2 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "test-runner-on-bad-end-name", 1, obj);
            }
            return testRunnerOnBadEndName(mnrunner2);

        case 43: // '+'
            try
            {
                mnrunner1 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "test-result-alist", 1, obj);
            }
            return testResultAlist(mnrunner1);

        case 45: // '-'
            try
            {
                mnrunner = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "test-runner-aux-value", 1, obj);
            }
            return testRunnerAuxValue(mnrunner);

        case 47: // '/'
            testRunnerReset(obj);
            return Values.empty;

        case 48: // '0'
            return testRunnerGroupPath(obj);

        case 49: // '1'
            return $PcTestNullCallback(obj);

        case 57: // '9'
            return $PcTestShouldExecute(obj);

        case 60: // '<'
            return testOnGroupEndSimple(obj);

        case 63: // '?'
            testOnFinalSimple(obj);
            return Values.empty;

        case 65: // 'A'
            return testOnTestBeginSimple(obj);

        case 66: // 'B'
            return testOnTestEndSimple(obj);

        case 68: // 'D'
            testResultClear(obj);
            return Values.empty;

        case 73: // 'I'
            if ($PcTestOnTestBegin(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 75: // 'K'
            return testRunnerTestName(obj);

        case 76: // 'L'
            return $PcTestApproximimate$Eq(obj);

        case 88: // 'X'
            return $PcTestAsSpecifier(obj);

        case 89: // 'Y'
            return testMatchName(obj);

        case 90: // 'Z'
            return testReadEvalString(obj);

        case 77: // 'M'
            return lambda16(obj);

        case 78: // 'N'
            return lambda17(obj);

        case 79: // 'O'
            return lambda18(obj);

        case 80: // 'P'
            return lambda19(obj);

        case 81: // 'Q'
            return lambda20(obj);

        case 82: // 'R'
            return lambda21(obj);

        case 83: // 'S'
            return lambda22(obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 14: // '\016'
            test.Mnrunner mnrunner;
            test.Mnrunner mnrunner1;
            test.Mnrunner mnrunner2;
            test.Mnrunner mnrunner3;
            test.Mnrunner mnrunner4;
            test.Mnrunner mnrunner5;
            test.Mnrunner mnrunner6;
            test.Mnrunner mnrunner7;
            test.Mnrunner mnrunner8;
            test.Mnrunner mnrunner9;
            test.Mnrunner mnrunner10;
            test.Mnrunner mnrunner11;
            test.Mnrunner mnrunner12;
            test.Mnrunner mnrunner13;
            test.Mnrunner mnrunner14;
            test.Mnrunner mnrunner15;
            test.Mnrunner mnrunner16;
            try
            {
                mnrunner16 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception16)
            {
                throw new WrongType(classcastexception16, "test-runner-pass-count!", 1, obj);
            }
            testRunnerPassCount$Ex(mnrunner16, obj1);
            return Values.empty;

        case 16: // '\020'
            try
            {
                mnrunner15 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception15)
            {
                throw new WrongType(classcastexception15, "test-runner-fail-count!", 1, obj);
            }
            testRunnerFailCount$Ex(mnrunner15, obj1);
            return Values.empty;

        case 18: // '\022'
            try
            {
                mnrunner14 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception14)
            {
                throw new WrongType(classcastexception14, "test-runner-xpass-count!", 1, obj);
            }
            testRunnerXpassCount$Ex(mnrunner14, obj1);
            return Values.empty;

        case 20: // '\024'
            try
            {
                mnrunner13 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception13)
            {
                throw new WrongType(classcastexception13, "test-runner-xfail-count!", 1, obj);
            }
            testRunnerXfailCount$Ex(mnrunner13, obj1);
            return Values.empty;

        case 22: // '\026'
            try
            {
                mnrunner12 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "test-runner-skip-count!", 1, obj);
            }
            testRunnerSkipCount$Ex(mnrunner12, obj1);
            return Values.empty;

        case 24: // '\030'
            try
            {
                mnrunner11 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "%test-runner-skip-list!", 1, obj);
            }
            $PcTestRunnerSkipList$Ex(mnrunner11, obj1);
            return Values.empty;

        case 26: // '\032'
            try
            {
                mnrunner10 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "%test-runner-fail-list!", 1, obj);
            }
            $PcTestRunnerFailList$Ex(mnrunner10, obj1);
            return Values.empty;

        case 28: // '\034'
            try
            {
                mnrunner9 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "test-runner-group-stack!", 1, obj);
            }
            testRunnerGroupStack$Ex(mnrunner9, obj1);
            return Values.empty;

        case 30: // '\036'
            try
            {
                mnrunner8 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "test-runner-on-test-begin!", 1, obj);
            }
            testRunnerOnTestBegin$Ex(mnrunner8, obj1);
            return Values.empty;

        case 32: // ' '
            try
            {
                mnrunner7 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "test-runner-on-test-end!", 1, obj);
            }
            testRunnerOnTestEnd$Ex(mnrunner7, obj1);
            return Values.empty;

        case 34: // '"'
            try
            {
                mnrunner6 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "test-runner-on-group-begin!", 1, obj);
            }
            testRunnerOnGroupBegin$Ex(mnrunner6, obj1);
            return Values.empty;

        case 36: // '$'
            try
            {
                mnrunner5 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "test-runner-on-group-end!", 1, obj);
            }
            testRunnerOnGroupEnd$Ex(mnrunner5, obj1);
            return Values.empty;

        case 38: // '&'
            try
            {
                mnrunner4 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "test-runner-on-final!", 1, obj);
            }
            testRunnerOnFinal$Ex(mnrunner4, obj1);
            return Values.empty;

        case 40: // '('
            try
            {
                mnrunner3 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "test-runner-on-bad-count!", 1, obj);
            }
            testRunnerOnBadCount$Ex(mnrunner3, obj1);
            return Values.empty;

        case 42: // '*'
            try
            {
                mnrunner2 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "test-runner-on-bad-end-name!", 1, obj);
            }
            testRunnerOnBadEndName$Ex(mnrunner2, obj1);
            return Values.empty;

        case 44: // ','
            try
            {
                mnrunner1 = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "test-result-alist!", 1, obj);
            }
            testResultAlist$Ex(mnrunner1, obj1);
            return Values.empty;

        case 46: // '.'
            try
            {
                mnrunner = (test.Mnrunner)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "test-runner-aux-value!", 1, obj);
            }
            testRunnerAuxValue$Ex(mnrunner, obj1);
            return Values.empty;

        case 58: // ':'
            $PcTestBegin(obj, obj1);
            return Values.empty;

        case 64: // '@'
            return $PcTestEnd(obj, obj1);

        case 69: // 'E'
            testResultRemove(obj, obj1);
            return Values.empty;

        case 74: // 'J'
            return $PcTestOnTestEnd(obj, obj1);

        case 85: // 'U'
            return $PcTestMatchNth(obj, obj1);
        }
    }

    public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply3(modulemethod, obj, obj1, obj2);

        case 50: // '2'
            return lambda1(obj, obj1, obj2);

        case 51: // '3'
            return lambda2(obj, obj1, obj2);

        case 52: // '4'
            return lambda3(obj, obj1, obj2);

        case 59: // ';'
            return testOnGroupBeginSimple(obj, obj1, obj2);

        case 61: // '='
            testOnBadCountSimple(obj, obj1, obj2);
            return Values.empty;

        case 62: // '>'
            return testOnBadEndNameSimple(obj, obj1, obj2);

        case 67: // 'C'
            return testResultSet$Ex(obj, obj1, obj2);
        }
    }

    public Object applyN(ModuleMethod modulemethod, Object aobj[])
    {
        switch (modulemethod.selector)
        {
        default:
            return super.applyN(modulemethod, aobj);

        case 70: // 'F'
            return testResultKind$V(aobj);

        case 71: // 'G'
            return isTestPassed$V(aobj);

        case 84: // 'T'
            Object obj = aobj[0];
            int i = -1 + aobj.length;
            Object aobj1[] = new Object[i];
            do
            {
                if (--i < 0)
                {
                    return testApply$V(obj, aobj1);
                }
                aobj1[i] = aobj[i + 1];
            } while (true);

        case 86: // 'V'
            return $PcTestMatchAll$V(aobj);

        case 87: // 'W'
            return $PcTestMatchAny$V(aobj);
        }
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match0(modulemethod, callcontext);

        case 72: // 'H'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 56: // '8'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 55: // '7'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 54: // '6'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 53: // '5'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 14: // '\016'
        case 16: // '\020'
        case 18: // '\022'
        case 20: // '\024'
        case 22: // '\026'
        case 24: // '\030'
        case 26: // '\032'
        case 28: // '\034'
        case 30: // '\036'
        case 32: // ' '
        case 34: // '"'
        case 36: // '$'
        case 38: // '&'
        case 40: // '('
        case 42: // '*'
        case 44: // ','
        case 46: // '.'
        case 50: // '2'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
        case 54: // '6'
        case 55: // '7'
        case 56: // '8'
        case 58: // ':'
        case 59: // ';'
        case 61: // '='
        case 62: // '>'
        case 64: // '@'
        case 67: // 'C'
        case 69: // 'E'
        case 70: // 'F'
        case 71: // 'G'
        case 72: // 'H'
        case 74: // 'J'
        case 84: // 'T'
        case 85: // 'U'
        case 86: // 'V'
        case 87: // 'W'
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 83: // 'S'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 82: // 'R'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 81: // 'Q'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 80: // 'P'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 79: // 'O'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 78: // 'N'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 77: // 'M'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 90: // 'Z'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 89: // 'Y'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 88: // 'X'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 76: // 'L'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 75: // 'K'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 73: // 'I'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 68: // 'D'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 66: // 'B'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 65: // 'A'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 63: // '?'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 60: // '<'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 57: // '9'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 49: // '1'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 48: // '0'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 47: // '/'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 45: // '-'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 43: // '+'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 41: // ')'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 39: // '\''
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 37: // '%'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 35: // '#'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 33: // '!'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 31: // '\037'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 29: // '\035'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 27: // '\033'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 25: // '\031'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 23: // '\027'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 21: // '\025'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 19: // '\023'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 17: // '\021'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 15: // '\017'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 13: // '\r'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 12: // '\f'
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

        case 85: // 'U'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 74: // 'J'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 69: // 'E'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 64: // '@'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 58: // ':'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 46: // '.'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 44: // ','
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 42: // '*'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 40: // '('
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 38: // '&'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 36: // '$'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 34: // '"'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 32: // ' '
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 30: // '\036'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 28: // '\034'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 26: // '\032'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 24: // '\030'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 22: // '\026'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 20: // '\024'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 18: // '\022'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 16: // '\020'
            if (!(obj instanceof test.Mnrunner))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 14: // '\016'
            break;
        }
        if (!(obj instanceof test.Mnrunner))
        {
            return 0xfff40001;
        } else
        {
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
        default:
            return super.match3(modulemethod, obj, obj1, obj2, callcontext);

        case 67: // 'C'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 62: // '>'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 61: // '='
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 59: // ';'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 52: // '4'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 51: // '3'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 50: // '2'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;
        }
    }

    public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.matchN(modulemethod, aobj, callcontext);

        case 87: // 'W'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 86: // 'V'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 84: // 'T'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 71: // 'G'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 70: // 'F'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
        test$Mnlog$Mnto$Mnfile = Boolean.TRUE;
        test$Mnrunner$Mncurrent = parameters.makeParameter(Boolean.FALSE);
        test$Mnrunner$Mnfactory = parameters.makeParameter(test$Mnrunner$Mnsimple);
    }

    static 
    {
        Lit165 = (SimpleSymbol)(new SimpleSymbol("dynamic-wind")).readResolve();
        Lit164 = (SimpleSymbol)(new SimpleSymbol("p")).readResolve();
        Lit163 = (SimpleSymbol)(new SimpleSymbol("exp")).readResolve();
        Lit162 = (SimpleSymbol)(new SimpleSymbol("res")).readResolve();
        Lit161 = (SimpleSymbol)(new SimpleSymbol("if")).readResolve();
        Lit160 = (SimpleSymbol)(new SimpleSymbol("name")).readResolve();
        Lit159 = (SimpleSymbol)(new SimpleSymbol("instance?")).readResolve();
        Lit158 = (SimpleSymbol)(new SimpleSymbol("cond")).readResolve();
        Lit157 = (SimpleSymbol)(new SimpleSymbol("actual-error")).readResolve();
        Lit156 = (SimpleSymbol)(new SimpleSymbol("<java.lang.Throwable>")).readResolve();
        Lit155 = (SimpleSymbol)(new SimpleSymbol("actual-value")).readResolve();
        Lit154 = (SimpleSymbol)(new SimpleSymbol("try-catch")).readResolve();
        Lit153 = (SimpleSymbol)(new SimpleSymbol("et")).readResolve();
        Lit152 = (SimpleSymbol)(new SimpleSymbol("expected-error")).readResolve();
        Lit151 = (SimpleSymbol)(new SimpleSymbol("ex")).readResolve();
        Lit150 = (SimpleSymbol)(new SimpleSymbol("let*")).readResolve();
        Lit149 = (SimpleSymbol)(new SimpleSymbol("r")).readResolve();
        Lit148 = (SimpleSymbol)(new SimpleSymbol("saved-runner")).readResolve();
        Lit147 = (SimpleSymbol)(new SimpleSymbol("lambda")).readResolve();
        Lit146 = (SimpleSymbol)(new SimpleSymbol("test-runner-current")).readResolve();
        Lit145 = (SimpleSymbol)(new SimpleSymbol("cons")).readResolve();
        Lit144 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
        Lit143 = (SimpleSymbol)(new SimpleSymbol("runner")).readResolve();
        Lit142 = (SimpleSymbol)(new SimpleSymbol("test-read-eval-string")).readResolve();
        Lit141 = (SimpleSymbol)(new SimpleSymbol("test-match-name")).readResolve();
        Object aobj[] = new Object[1];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("test-expect-fail")).readResolve();
        Lit139 = simplesymbol;
        aobj[0] = simplesymbol;
        SyntaxRule asyntaxrule[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
        Object aobj1[] = new Object[8];
        aobj1[0] = Lit144;
        SimpleSymbol simplesymbol1 = Lit143;
        SimpleSymbol simplesymbol2 = (SimpleSymbol)(new SimpleSymbol("test-runner-get")).readResolve();
        Lit60 = simplesymbol2;
        aobj1[1] = PairWithPosition.make(PairWithPosition.make(simplesymbol1, PairWithPosition.make(PairWithPosition.make(simplesymbol2, LList.Empty, "testing.scm", 0x3c5014), LList.Empty, "testing.scm", 0x3c5014), "testing.scm", 0x3c500c), LList.Empty, "testing.scm", 0x3c500b);
        SimpleSymbol simplesymbol3 = (SimpleSymbol)(new SimpleSymbol("%test-runner-fail-list!")).readResolve();
        Lit34 = simplesymbol3;
        aobj1[2] = simplesymbol3;
        aobj1[3] = Lit143;
        aobj1[4] = Lit145;
        SimpleSymbol simplesymbol4 = (SimpleSymbol)(new SimpleSymbol("test-match-all")).readResolve();
        Lit131 = simplesymbol4;
        aobj1[5] = simplesymbol4;
        SimpleSymbol simplesymbol5 = (SimpleSymbol)(new SimpleSymbol("%test-as-specifier")).readResolve();
        Lit136 = simplesymbol5;
        aobj1[6] = simplesymbol5;
        SimpleSymbol simplesymbol6 = (SimpleSymbol)(new SimpleSymbol("%test-runner-fail-list")).readResolve();
        Lit33 = simplesymbol6;
        aobj1[7] = PairWithPosition.make(PairWithPosition.make(simplesymbol6, PairWithPosition.make(Lit143, LList.Empty, "testing.scm", 0x3c801e), "testing.scm", 0x3c8006), LList.Empty, "testing.scm", 0x3c8006);
        asyntaxrule[0] = new SyntaxRule(syntaxpattern, "\003", "\021\030\004\021\030\f\b\021\030\024\021\030\034\b\021\030$Q\021\030,\b\005\021\0304\b\003\030<", aobj1, 1);
        Lit140 = new SyntaxRules(aobj, asyntaxrule, 1);
        Object aobj2[] = new Object[1];
        SimpleSymbol simplesymbol7 = (SimpleSymbol)(new SimpleSymbol("test-skip")).readResolve();
        Lit137 = simplesymbol7;
        aobj2[0] = simplesymbol7;
        SyntaxRule asyntaxrule1[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern1 = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
        Object aobj3[] = new Object[8];
        aobj3[0] = Lit144;
        aobj3[1] = PairWithPosition.make(PairWithPosition.make(Lit143, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 0x3bd014), LList.Empty, "testing.scm", 0x3bd014), "testing.scm", 0x3bd00c), LList.Empty, "testing.scm", 0x3bd00b);
        SimpleSymbol simplesymbol8 = (SimpleSymbol)(new SimpleSymbol("%test-runner-skip-list!")).readResolve();
        Lit32 = simplesymbol8;
        aobj3[2] = simplesymbol8;
        aobj3[3] = Lit143;
        aobj3[4] = Lit145;
        aobj3[5] = Lit131;
        aobj3[6] = Lit136;
        SimpleSymbol simplesymbol9 = (SimpleSymbol)(new SimpleSymbol("%test-runner-skip-list")).readResolve();
        Lit31 = simplesymbol9;
        aobj3[7] = PairWithPosition.make(PairWithPosition.make(simplesymbol9, PairWithPosition.make(Lit143, LList.Empty, "testing.scm", 0x3c001e), "testing.scm", 0x3c0006), LList.Empty, "testing.scm", 0x3c0006);
        asyntaxrule1[0] = new SyntaxRule(syntaxpattern1, "\003", "\021\030\004\021\030\f\b\021\030\024\021\030\034\b\021\030$Q\021\030,\b\005\021\0304\b\003\030<", aobj3, 1);
        Lit138 = new SyntaxRules(aobj2, asyntaxrule1, 1);
        Object aobj4[] = new Object[1];
        SimpleSymbol simplesymbol10 = (SimpleSymbol)(new SimpleSymbol("test-match-any")).readResolve();
        Lit134 = simplesymbol10;
        aobj4[0] = simplesymbol10;
        SyntaxRule asyntaxrule2[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern2 = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
        Object aobj5[] = new Object[2];
        SimpleSymbol simplesymbol11 = (SimpleSymbol)(new SimpleSymbol("%test-match-any")).readResolve();
        Lit133 = simplesymbol11;
        aobj5[0] = simplesymbol11;
        aobj5[1] = Lit136;
        asyntaxrule2[0] = new SyntaxRule(syntaxpattern2, "\003", "\021\030\004\b\005\021\030\f\b\003", aobj5, 1);
        Lit135 = new SyntaxRules(aobj4, asyntaxrule2, 1);
        Object aobj6[] = new Object[1];
        aobj6[0] = Lit131;
        SyntaxRule asyntaxrule3[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern3 = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
        Object aobj7[] = new Object[2];
        SimpleSymbol simplesymbol12 = (SimpleSymbol)(new SimpleSymbol("%test-match-all")).readResolve();
        Lit130 = simplesymbol12;
        aobj7[0] = simplesymbol12;
        aobj7[1] = Lit136;
        asyntaxrule3[0] = new SyntaxRule(syntaxpattern3, "\003", "\021\030\004\b\005\021\030\f\b\003", aobj7, 1);
        Lit132 = new SyntaxRules(aobj6, asyntaxrule3, 1);
        Object aobj8[] = new Object[1];
        SimpleSymbol simplesymbol13 = (SimpleSymbol)(new SimpleSymbol("test-match-nth")).readResolve();
        Lit128 = simplesymbol13;
        aobj8[0] = simplesymbol13;
        SyntaxRule asyntaxrule4[] = new SyntaxRule[2];
        SyntaxPattern syntaxpattern4 = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
        Object aobj9[] = new Object[2];
        aobj9[0] = Lit128;
        IntNum intnum = IntNum.make(1);
        Lit13 = intnum;
        aobj9[1] = PairWithPosition.make(intnum, LList.Empty, "testing.scm", 0x38e018);
        asyntaxrule4[0] = new SyntaxRule(syntaxpattern4, "\001", "\021\030\004\t\003\030\f", aobj9, 0);
        SyntaxPattern syntaxpattern5 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
        Object aobj10[] = new Object[1];
        SimpleSymbol simplesymbol14 = (SimpleSymbol)(new SimpleSymbol("%test-match-nth")).readResolve();
        Lit127 = simplesymbol14;
        aobj10[0] = simplesymbol14;
        asyntaxrule4[1] = new SyntaxRule(syntaxpattern5, "\001\001", "\021\030\004\t\003\b\013", aobj10, 0);
        Lit129 = new SyntaxRules(aobj8, asyntaxrule4, 2);
        Object aobj11[] = new Object[1];
        SimpleSymbol simplesymbol15 = (SimpleSymbol)(new SimpleSymbol("test-with-runner")).readResolve();
        Lit125 = simplesymbol15;
        aobj11[0] = simplesymbol15;
        SyntaxRule asyntaxrule5[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern6 = new SyntaxPattern("\f\030\f\007\r\017\b\b\b", new Object[0], 2);
        Object aobj12[] = new Object[6];
        aobj12[0] = Lit144;
        aobj12[1] = PairWithPosition.make(PairWithPosition.make(Lit148, PairWithPosition.make(PairWithPosition.make(Lit146, LList.Empty, "testing.scm", 0x37d01a), LList.Empty, "testing.scm", 0x37d01a), "testing.scm", 0x37d00c), LList.Empty, "testing.scm", 0x37d00b);
        aobj12[2] = Lit165;
        aobj12[3] = Lit147;
        aobj12[4] = Lit146;
        aobj12[5] = PairWithPosition.make(PairWithPosition.make(Lit147, PairWithPosition.make(LList.Empty, PairWithPosition.make(PairWithPosition.make(Lit146, PairWithPosition.make(Lit148, LList.Empty, "testing.scm", 0x38102c), "testing.scm", 0x381017), LList.Empty, "testing.scm", 0x381017), "testing.scm", 0x381014), "testing.scm", 0x38100c), LList.Empty, "testing.scm", 0x38100c);
        asyntaxrule5[0] = new SyntaxRule(syntaxpattern6, "\001\003", "\021\030\004\021\030\f\b\021\030\024Y\021\030\034\t\020\b\021\030$\b\003A\021\030\034\t\020\b\r\013\030,", aobj12, 1);
        Lit126 = new SyntaxRules(aobj11, asyntaxrule5, 2);
        Lit124 = (SimpleSymbol)(new SimpleSymbol("test-apply")).readResolve();
        Object aobj13[] = new Object[6];
        aobj13[0] = Lit150;
        aobj13[1] = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 0x35a00e), LList.Empty, "testing.scm", 0x35a00e), "testing.scm", 0x35a00b), LList.Empty, "testing.scm", 0x35a00a);
        SimpleSymbol simplesymbol16 = (SimpleSymbol)(new SimpleSymbol("test-result-alist!")).readResolve();
        Lit52 = simplesymbol16;
        aobj13[2] = simplesymbol16;
        aobj13[3] = Lit149;
        SimpleSymbol simplesymbol17 = (SimpleSymbol)(new SimpleSymbol("%test-error")).readResolve();
        Lit115 = simplesymbol17;
        aobj13[4] = simplesymbol17;
        aobj13[5] = Boolean.TRUE;
        Lit123 = new SyntaxTemplate("\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b\023\b\021\030$\021\030\034\021\030,\b\013", aobj13, 0);
        Object aobj14[] = new Object[5];
        aobj14[0] = Lit150;
        aobj14[1] = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 0x35500e), LList.Empty, "testing.scm", 0x35500e), "testing.scm", 0x35500b), LList.Empty, "testing.scm", 0x35500a);
        aobj14[2] = Lit52;
        aobj14[3] = Lit149;
        aobj14[4] = Lit115;
        Lit121 = new SyntaxTemplate("\001\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b\033\b\021\030$\021\030\034\t\013\b\023", aobj14, 0);
        Object aobj15[] = new Object[8];
        aobj15[0] = Lit150;
        aobj15[1] = PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 0x34f00e), LList.Empty, "testing.scm", 0x34f00e), "testing.scm", 0x34f00b);
        aobj15[2] = Lit160;
        aobj15[3] = Lit52;
        aobj15[4] = Lit149;
        aobj15[5] = Lit145;
        SimpleSymbol simplesymbol18 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
        Lit15 = simplesymbol18;
        SimpleSymbol simplesymbol19 = (SimpleSymbol)(new SimpleSymbol("test-name")).readResolve();
        Lit7 = simplesymbol19;
        aobj15[6] = PairWithPosition.make(simplesymbol18, PairWithPosition.make(simplesymbol19, LList.Empty, "testing.scm", 0x351029), "testing.scm", 0x351029);
        aobj15[7] = Lit115;
        Lit119 = new SyntaxTemplate("\001\001\001\001\001", "\021\030\004I\021\030\f\b\021\030\024\b\013\251\021\030\034\021\030$\b\021\030,A\021\030,\021\0304\b\013\b#\b\021\030<\021\030$\t\023\b\033", aobj15, 0);
        Lit117 = (SimpleSymbol)(new SimpleSymbol("test-error")).readResolve();
        Object aobj16[] = new Object[1];
        aobj16[0] = Lit115;
        SyntaxRule asyntaxrule6[] = new SyntaxRule[2];
        Object aobj17[] = new Object[1];
        aobj17[0] = Boolean.TRUE;
        SyntaxPattern syntaxpattern7 = new SyntaxPattern("\f\030\f\007\f\002\f\017\b", aobj17, 2);
        Object aobj18[] = new Object[14];
        aobj18[0] = Lit158;
        SimpleSymbol simplesymbol20 = (SimpleSymbol)(new SimpleSymbol("%test-on-test-begin")).readResolve();
        Lit86 = simplesymbol20;
        aobj18[1] = simplesymbol20;
        SimpleSymbol simplesymbol21 = (SimpleSymbol)(new SimpleSymbol("test-result-set!")).readResolve();
        Lit78 = simplesymbol21;
        aobj18[2] = simplesymbol21;
        aobj18[3] = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit152, LList.Empty, "testing.scm", 0x31301d), "testing.scm", 0x31301d), PairWithPosition.make(Boolean.TRUE, LList.Empty, "testing.scm", 0x31302c), "testing.scm", 0x31301c);
        SimpleSymbol simplesymbol22 = (SimpleSymbol)(new SimpleSymbol("%test-on-test-end")).readResolve();
        Lit87 = simplesymbol22;
        aobj18[4] = simplesymbol22;
        aobj18[5] = Lit154;
        aobj18[6] = Lit144;
        aobj18[7] = PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "testing.scm", 0x31701e), "testing.scm", 0x31701e);
        aobj18[8] = PairWithPosition.make(Boolean.FALSE, LList.Empty, "testing.scm", 0x318009);
        aobj18[9] = Lit151;
        aobj18[10] = Lit156;
        aobj18[11] = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit157, LList.Empty, "testing.scm", 0x31a020), "testing.scm", 0x31a020), PairWithPosition.make(Lit151, LList.Empty, "testing.scm", 0x31a02d), "testing.scm", 0x31a01f);
        aobj18[12] = PairWithPosition.make(Boolean.TRUE, LList.Empty, "testing.scm", 0x31b00b);
        SimpleSymbol simplesymbol23 = (SimpleSymbol)(new SimpleSymbol("%test-report-result")).readResolve();
        Lit83 = simplesymbol23;
        aobj18[13] = PairWithPosition.make(PairWithPosition.make(simplesymbol23, LList.Empty, "testing.scm", 0x31c008), LList.Empty, "testing.scm", 0x31c008);
        asyntaxrule6[0] = new SyntaxRule(syntaxpattern7, "\001\001", "\021\030\004\b)\021\030\f\b\0039\021\030\024\t\003\030\034\u0169\021\030$\t\003\b\021\030,\221\021\0304\t\020Q\021\030\024\t\003\021\030<\b\013\030D\b\021\030L\021\030T9\021\030\024\t\003\030\\\030d\030l", aobj18, 0);
        SyntaxPattern syntaxpattern8 = new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3);
        Object aobj19[] = new Object[15];
        aobj19[0] = Lit161;
        aobj19[1] = Lit86;
        aobj19[2] = Lit144;
        aobj19[3] = Lit153;
        aobj19[4] = Lit78;
        aobj19[5] = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit152, LList.Empty, "testing.scm", 0x32001c), "testing.scm", 0x32001c), PairWithPosition.make(Lit153, LList.Empty, "testing.scm", 0x32002b), "testing.scm", 0x32001b);
        aobj19[6] = Lit87;
        aobj19[7] = Lit154;
        aobj19[8] = PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "testing.scm", 0x32401d), "testing.scm", 0x32401d);
        aobj19[9] = PairWithPosition.make(Boolean.FALSE, LList.Empty, "testing.scm", 0x325008);
        aobj19[10] = Lit151;
        aobj19[11] = Lit156;
        aobj19[12] = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit157, LList.Empty, "testing.scm", 0x32701f), "testing.scm", 0x32701f), PairWithPosition.make(Lit151, LList.Empty, "testing.scm", 0x32702c), "testing.scm", 0x32701e);
        aobj19[13] = PairWithPosition.make(PairWithPosition.make(Lit158, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("and")).readResolve(), PairWithPosition.make(PairWithPosition.make(Lit159, PairWithPosition.make(Lit153, PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("<gnu.bytecode.ClassType>")).readResolve(), LList.Empty, "testing.scm", 0x328024), "testing.scm", 0x328021), "testing.scm", 0x328016), PairWithPosition.make(PairWithPosition.make(PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("$lookup$")).readResolve(), Pair.make((SimpleSymbol)(new SimpleSymbol("gnu.bytecode.ClassType")).readResolve(), Pair.make(Pair.make((SimpleSymbol)(new SimpleSymbol("quasiquote")).readResolve(), Pair.make((SimpleSymbol)(new SimpleSymbol("isSubclass")).readResolve(), LList.Empty)), LList.Empty)), "testing.scm", 0x329009), PairWithPosition.make(Lit153, PairWithPosition.make(Lit156, LList.Empty, "testing.scm", 0x32902e), "testing.scm", 0x32902b), "testing.scm", 0x329008), LList.Empty, "testing.scm", 0x329008), "testing.scm", 0x328016), "testing.scm", 0x328011), PairWithPosition.make(PairWithPosition.make(Lit159, PairWithPosition.make(Lit151, PairWithPosition.make(Lit153, LList.Empty, "testing.scm", 0x32a018), "testing.scm", 0x32a015), "testing.scm", 0x32a00a), LList.Empty, "testing.scm", 0x32a00a), "testing.scm", 0x328010), PairWithPosition.make(PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("else")).readResolve(), PairWithPosition.make(Boolean.TRUE, LList.Empty, "testing.scm", 0x32b00f), "testing.scm", 0x32b009), LList.Empty, "testing.scm", 0x32b009), "testing.scm", 0x328010), "testing.scm", 0x32800a), LList.Empty, "testing.scm", 0x32800a);
        aobj19[14] = PairWithPosition.make(PairWithPosition.make(Lit83, LList.Empty, "testing.scm", 0x32c007), LList.Empty, "testing.scm", 0x32c007);
        asyntaxrule6[1] = new SyntaxRule(syntaxpattern8, "\001\001\001", "\021\030\004)\021\030\f\b\003\b\021\030\0241\b\021\030\034\b\0139\021\030$\t\003\030,\u0169\021\0304\t\003\b\021\030<\221\021\030\024\t\020Q\021\030$\t\003\021\030D\b\023\030L\b\021\030T\021\030\\9\021\030$\t\003\030d\030l\030t", aobj19, 0);
        Lit116 = new SyntaxRules(aobj16, asyntaxrule6, 3);
        Object aobj20[] = new Object[6];
        aobj20[0] = Lit150;
        aobj20[1] = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 0x2c800c), LList.Empty, "testing.scm", 0x2c800c), "testing.scm", 0x2c8009), LList.Empty, "testing.scm", 0x2c8008);
        aobj20[2] = Lit52;
        aobj20[3] = Lit149;
        SimpleSymbol simplesymbol24 = (SimpleSymbol)(new SimpleSymbol("%test-comp2body")).readResolve();
        Lit89 = simplesymbol24;
        aobj20[4] = simplesymbol24;
        SimpleSymbol simplesymbol25 = (SimpleSymbol)(new SimpleSymbol("%test-approximimate=")).readResolve();
        Lit91 = simplesymbol25;
        aobj20[5] = simplesymbol25;
        Lit114 = new SyntaxTemplate("\001\001\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b#\b\021\030$\021\030\034)\021\030,\b\033\t\013\b\023", aobj20, 0);
        Object aobj21[] = new Object[9];
        aobj21[0] = Lit150;
        aobj21[1] = PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 0x2c200c), LList.Empty, "testing.scm", 0x2c200c), "testing.scm", 0x2c2009);
        aobj21[2] = Lit160;
        aobj21[3] = Lit52;
        aobj21[4] = Lit149;
        aobj21[5] = Lit145;
        aobj21[6] = PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "testing.scm", 0x2c4027), "testing.scm", 0x2c4027);
        aobj21[7] = Lit89;
        aobj21[8] = Lit91;
        Lit112 = new SyntaxTemplate("\001\001\001\001\001\001", "\021\030\004I\021\030\f\b\021\030\024\b\013\251\021\030\034\021\030$\b\021\030,A\021\030,\021\0304\b\013\b+\b\021\030<\021\030$)\021\030D\b#\t\023\b\033", aobj21, 0);
        Lit110 = (SimpleSymbol)(new SimpleSymbol("test-approximate")).readResolve();
        Object aobj22[] = new Object[1];
        aobj22[0] = (SimpleSymbol)(new SimpleSymbol("equal?")).readResolve();
        Lit109 = new SyntaxTemplate("", "\030\004", aobj22, 0);
        Lit108 = (SimpleSymbol)(new SimpleSymbol("test-equal")).readResolve();
        Object aobj23[] = new Object[1];
        aobj23[0] = (SimpleSymbol)(new SimpleSymbol("eq?")).readResolve();
        Lit107 = new SyntaxTemplate("", "\030\004", aobj23, 0);
        Lit106 = (SimpleSymbol)(new SimpleSymbol("test-eq")).readResolve();
        Object aobj24[] = new Object[1];
        aobj24[0] = (SimpleSymbol)(new SimpleSymbol("eqv?")).readResolve();
        Lit105 = new SyntaxTemplate("", "\030\004", aobj24, 0);
        Lit104 = (SimpleSymbol)(new SimpleSymbol("test-eqv")).readResolve();
        Object aobj25[] = new Object[5];
        aobj25[0] = Lit150;
        aobj25[1] = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 0x2a700e), LList.Empty, "testing.scm", 0x2a700e), "testing.scm", 0x2a700b), LList.Empty, "testing.scm", 0x2a700a);
        aobj25[2] = Lit52;
        aobj25[3] = Lit149;
        SimpleSymbol simplesymbol26 = (SimpleSymbol)(new SimpleSymbol("%test-comp1body")).readResolve();
        Lit92 = simplesymbol26;
        aobj25[4] = simplesymbol26;
        Lit103 = new SyntaxTemplate("\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b\023\b\021\030$\021\030\034\b\013", aobj25, 0);
        Object aobj26[] = new Object[8];
        aobj26[0] = Lit150;
        aobj26[1] = PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 0x2a100e), LList.Empty, "testing.scm", 0x2a100e), "testing.scm", 0x2a100b);
        aobj26[2] = Lit160;
        aobj26[3] = Lit52;
        aobj26[4] = Lit149;
        aobj26[5] = Lit145;
        aobj26[6] = PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "testing.scm", 0x2a3029), "testing.scm", 0x2a3029);
        aobj26[7] = Lit92;
        Lit101 = new SyntaxTemplate("\001\001\001\001", "\021\030\004I\021\030\f\b\021\030\024\b\013\251\021\030\034\021\030$\b\021\030,A\021\030,\021\0304\b\013\b\033\b\021\030<\021\030$\b\023", aobj26, 0);
        Lit99 = (SimpleSymbol)(new SimpleSymbol("test-assert")).readResolve();
        Object aobj27[] = new Object[2];
        SimpleSymbol simplesymbol27 = (SimpleSymbol)(new SimpleSymbol("%test-end")).readResolve();
        Lit69 = simplesymbol27;
        aobj27[0] = simplesymbol27;
        aobj27[1] = Boolean.FALSE;
        Lit98 = new SyntaxTemplate("\001\001", "\021\030\004\021\030\f\b\013", aobj27, 0);
        Object aobj28[] = new Object[1];
        aobj28[0] = Lit69;
        Lit96 = new SyntaxTemplate("\001\001\001", "\021\030\004\t\013\b\023", aobj28, 0);
        Lit94 = (SimpleSymbol)(new SimpleSymbol("test-end")).readResolve();
        Object aobj29[] = new Object[1];
        aobj29[0] = Lit92;
        SyntaxRule asyntaxrule7[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern9 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
        Object aobj30[] = new Object[10];
        aobj30[0] = Lit144;
        aobj30[1] = Lit161;
        aobj30[2] = Lit86;
        aobj30[3] = Lit162;
        SimpleSymbol simplesymbol28 = (SimpleSymbol)(new SimpleSymbol("%test-evaluate-with-catch")).readResolve();
        Lit84 = simplesymbol28;
        aobj30[4] = simplesymbol28;
        aobj30[5] = Lit78;
        aobj30[6] = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "testing.scm", 0x28b01e), "testing.scm", 0x28b01e), PairWithPosition.make(Lit162, LList.Empty, "testing.scm", 0x28b02b), "testing.scm", 0x28b01d);
        aobj30[7] = Lit87;
        aobj30[8] = PairWithPosition.make(Lit162, LList.Empty, "testing.scm", 0x28c01e);
        aobj30[9] = PairWithPosition.make(PairWithPosition.make(Lit83, LList.Empty, "testing.scm", 0x28d008), LList.Empty, "testing.scm", 0x28d008);
        asyntaxrule7[0] = new SyntaxRule(syntaxpattern9, "\001\001", "\021\030\004\t\020\u0171\021\030\f)\021\030\024\b\003\b\021\030\004\t\020\b\021\030\004Q\b\021\030\034\b\021\030$\b\0139\021\030,\t\003\0304\b\021\030<\t\003\030D\030L", aobj30, 0);
        Lit93 = new SyntaxRules(aobj29, asyntaxrule7, 2);
        Object aobj31[] = new Object[1];
        aobj31[0] = Lit89;
        SyntaxRule asyntaxrule8[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern10 = new SyntaxPattern("\f\030\f\007\f\017\f\027\f\037\b", new Object[0], 4);
        Object aobj32[] = new Object[12];
        aobj32[0] = Lit144;
        aobj32[1] = Lit161;
        aobj32[2] = Lit86;
        aobj32[3] = Lit163;
        aobj32[4] = Lit78;
        aobj32[5] = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("expected-value")).readResolve(), LList.Empty, "testing.scm", 0x27901a), "testing.scm", 0x27901a), PairWithPosition.make(Lit163, LList.Empty, "testing.scm", 0x279029), "testing.scm", 0x279019);
        aobj32[6] = Lit162;
        aobj32[7] = Lit84;
        aobj32[8] = PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit155, LList.Empty, "testing.scm", 0x27b01c), "testing.scm", 0x27b01c), PairWithPosition.make(Lit162, LList.Empty, "testing.scm", 0x27b029), "testing.scm", 0x27b01b);
        aobj32[9] = Lit87;
        aobj32[10] = PairWithPosition.make(Lit163, PairWithPosition.make(Lit162, LList.Empty, "testing.scm", 0x27c026), "testing.scm", 0x27c022);
        aobj32[11] = PairWithPosition.make(PairWithPosition.make(Lit83, LList.Empty, "testing.scm", 0x27d006), LList.Empty, "testing.scm", 0x27d006);
        asyntaxrule8[0] = new SyntaxRule(syntaxpattern10, "\001\001\001\001", "\021\030\004\t\020\u01F1\021\030\f)\021\030\024\b\003\b\021\030\0041\b\021\030\034\b\0239\021\030$\t\003\030,\b\021\030\004Q\b\021\0304\b\021\030<\b\0339\021\030$\t\003\030D\b\021\030L\t\003\b\t\013\030T\030\\", aobj32, 0);
        Lit90 = new SyntaxRules(aobj31, asyntaxrule8, 4);
        Lit88 = (SimpleSymbol)(new SimpleSymbol("test-runner-test-name")).readResolve();
        Object aobj33[] = new Object[1];
        aobj33[0] = Lit84;
        SyntaxRule asyntaxrule9[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern11 = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
        Object aobj34[] = new Object[2];
        aobj34[0] = Lit154;
        aobj34[1] = PairWithPosition.make(PairWithPosition.make(Lit151, PairWithPosition.make(Lit156, PairWithPosition.make(PairWithPosition.make(Lit78, PairWithPosition.make(PairWithPosition.make(Lit146, LList.Empty, "testing.scm", 0x23d01b), PairWithPosition.make(PairWithPosition.make(Lit15, PairWithPosition.make(Lit157, LList.Empty, "testing.scm", 0x23d032), "testing.scm", 0x23d032), PairWithPosition.make(Lit151, LList.Empty, "testing.scm", 0x23d03f), "testing.scm", 0x23d031), "testing.scm", 0x23d01b), "testing.scm", 0x23d009), PairWithPosition.make(Boolean.FALSE, LList.Empty, "testing.scm", 0x23e009), "testing.scm", 0x23d009), "testing.scm", 0x23c009), "testing.scm", 0x23c005), LList.Empty, "testing.scm", 0x23c005);
        asyntaxrule9[0] = new SyntaxRule(syntaxpattern11, "\001", "\021\030\004\t\003\030\f", aobj34, 0);
        Lit85 = new SyntaxRules(aobj33, asyntaxrule9, 1);
        Lit82 = (SimpleSymbol)(new SimpleSymbol("test-passed?")).readResolve();
        Lit81 = (SimpleSymbol)(new SimpleSymbol("test-result-kind")).readResolve();
        Lit80 = (SimpleSymbol)(new SimpleSymbol("test-result-remove")).readResolve();
        Lit79 = (SimpleSymbol)(new SimpleSymbol("test-result-clear")).readResolve();
        Lit77 = (SimpleSymbol)(new SimpleSymbol("test-on-test-end-simple")).readResolve();
        Object aobj35[] = new Object[1];
        SimpleSymbol simplesymbol29 = (SimpleSymbol)(new SimpleSymbol("test-result-ref")).readResolve();
        Lit75 = simplesymbol29;
        aobj35[0] = simplesymbol29;
        SyntaxRule asyntaxrule10[] = new SyntaxRule[2];
        SyntaxPattern syntaxpattern12 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
        Object aobj36[] = new Object[2];
        aobj36[0] = Lit75;
        aobj36[1] = PairWithPosition.make(Boolean.FALSE, LList.Empty, "testing.scm", 0x1d8024);
        asyntaxrule10[0] = new SyntaxRule(syntaxpattern12, "\001\001", "\021\030\004\t\003\t\013\030\f", aobj36, 0);
        SyntaxPattern syntaxpattern13 = new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3);
        Object aobj37[] = new Object[6];
        aobj37[0] = Lit144;
        aobj37[1] = Lit164;
        aobj37[2] = (SimpleSymbol)(new SimpleSymbol("assq")).readResolve();
        SimpleSymbol simplesymbol30 = (SimpleSymbol)(new SimpleSymbol("test-result-alist")).readResolve();
        Lit51 = simplesymbol30;
        aobj37[3] = simplesymbol30;
        aobj37[4] = Lit161;
        aobj37[5] = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("cdr")).readResolve(), PairWithPosition.make(Lit164, LList.Empty, "testing.scm", 0x1db013), "testing.scm", 0x1db00e);
        asyntaxrule10[1] = new SyntaxRule(syntaxpattern13, "\001\001\001", "\021\030\004\201\b\021\030\f\b\021\030\024\t\013\b\021\030\034\b\003\b\021\030$\021\030\f\021\030,\b\023", aobj37, 0);
        Lit76 = new SyntaxRules(aobj35, asyntaxrule10, 3);
        Lit74 = (SimpleSymbol)(new SimpleSymbol("test-on-test-begin-simple")).readResolve();
        Object aobj38[] = new Object[1];
        SimpleSymbol simplesymbol31 = (SimpleSymbol)(new SimpleSymbol("test-group-with-cleanup")).readResolve();
        Lit72 = simplesymbol31;
        aobj38[0] = simplesymbol31;
        SyntaxRule asyntaxrule11[] = new SyntaxRule[3];
        SyntaxPattern syntaxpattern14 = new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3);
        Object aobj39[] = new Object[4];
        SimpleSymbol simplesymbol32 = (SimpleSymbol)(new SimpleSymbol("test-group")).readResolve();
        Lit70 = simplesymbol32;
        aobj39[0] = simplesymbol32;
        aobj39[1] = Lit165;
        aobj39[2] = PairWithPosition.make(Lit147, PairWithPosition.make(LList.Empty, PairWithPosition.make(Boolean.FALSE, LList.Empty, "testing.scm", 0x1be00f), "testing.scm", 0x1be00c), "testing.scm", 0x1be004);
        aobj39[3] = Lit147;
        asyntaxrule11[0] = new SyntaxRule(syntaxpattern14, "\001\001\001", "\021\030\004\t\003\b\021\030\f\021\030\0249\021\030\034\t\020\b\013\b\021\030\034\t\020\b\023", aobj39, 0);
        SyntaxPattern syntaxpattern15 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
        Object aobj40[] = new Object[2];
        aobj40[0] = Lit72;
        aobj40[1] = Boolean.FALSE;
        asyntaxrule11[1] = new SyntaxRule(syntaxpattern15, "\001\001", "\021\030\004\t\003\021\030\f\b\013", aobj40, 0);
        SyntaxPattern syntaxpattern16 = new SyntaxPattern("\f\030\f\007\f\017\f\027\f\037#", new Object[0], 5);
        Object aobj41[] = new Object[2];
        aobj41[0] = Lit72;
        aobj41[1] = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
        asyntaxrule11[2] = new SyntaxRule(syntaxpattern16, "\001\001\001\001\0", "\021\030\004\t\0039\021\030\f\t\013\b\023\t\033\"", aobj41, 0);
        Lit73 = new SyntaxRules(aobj38, asyntaxrule11, 5);
        Object aobj42[] = new Object[1];
        aobj42[0] = Lit70;
        SyntaxRule asyntaxrule12[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern17 = new SyntaxPattern("\f\030\f\007\013", new Object[0], 2);
        Object aobj43[] = new Object[13];
        aobj43[0] = Lit144;
        aobj43[1] = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit146, LList.Empty, "testing.scm", 0x1b000f), LList.Empty, "testing.scm", 0x1b000f), "testing.scm", 0x1b000c), LList.Empty, "testing.scm", 0x1b000b);
        aobj43[2] = Lit52;
        aobj43[3] = Lit149;
        aobj43[4] = (SimpleSymbol)(new SimpleSymbol("list")).readResolve();
        aobj43[5] = Lit145;
        aobj43[6] = PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "testing.scm", 0x1b202b), "testing.scm", 0x1b202b);
        aobj43[7] = Lit161;
        SimpleSymbol simplesymbol33 = (SimpleSymbol)(new SimpleSymbol("%test-should-execute")).readResolve();
        Lit62 = simplesymbol33;
        aobj43[8] = PairWithPosition.make(simplesymbol33, PairWithPosition.make(Lit149, LList.Empty, "testing.scm", 0x1b3022), "testing.scm", 0x1b300c);
        aobj43[9] = Lit165;
        aobj43[10] = Lit147;
        aobj43[11] = (SimpleSymbol)(new SimpleSymbol("test-begin")).readResolve();
        aobj43[12] = Lit94;
        asyntaxrule12[0] = new SyntaxRule(syntaxpattern17, "\001\0", "\021\030\004\021\030\f\231\021\030\024\021\030\034\b\021\030$\b\021\030,\021\0304\b\003\b\021\030<\021\030D\b\021\030LY\021\030T\t\020\b\021\030\\\b\0031\021\030T\t\020\n\b\021\030T\t\020\b\021\030d\b\003", aobj43, 0);
        Lit71 = new SyntaxRules(aobj42, asyntaxrule12, 2);
        Lit68 = (SimpleSymbol)(new SimpleSymbol("test-on-final-simple")).readResolve();
        Lit67 = (SimpleSymbol)(new SimpleSymbol("test-on-bad-end-name-simple")).readResolve();
        Lit66 = (SimpleSymbol)(new SimpleSymbol("test-on-bad-count-simple")).readResolve();
        Lit65 = (SimpleSymbol)(new SimpleSymbol("test-on-group-end-simple")).readResolve();
        Lit64 = (SimpleSymbol)(new SimpleSymbol("test-on-group-begin-simple")).readResolve();
        Lit63 = (SimpleSymbol)(new SimpleSymbol("%test-begin")).readResolve();
        Lit61 = (SimpleSymbol)(new SimpleSymbol("test-runner-create")).readResolve();
        Lit59 = (SimpleSymbol)(new SimpleSymbol("test-runner-simple")).readResolve();
        Lit58 = (SimpleSymbol)(new SimpleSymbol("test-runner-null")).readResolve();
        Lit57 = (SimpleSymbol)(new SimpleSymbol("%test-null-callback")).readResolve();
        Lit56 = (SimpleSymbol)(new SimpleSymbol("test-runner-group-path")).readResolve();
        Lit55 = (SimpleSymbol)(new SimpleSymbol("test-runner-reset")).readResolve();
        Lit54 = (SimpleSymbol)(new SimpleSymbol("test-runner-aux-value!")).readResolve();
        Lit53 = (SimpleSymbol)(new SimpleSymbol("test-runner-aux-value")).readResolve();
        Lit50 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-bad-end-name!")).readResolve();
        Lit49 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-bad-end-name")).readResolve();
        Lit48 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-bad-count!")).readResolve();
        Lit47 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-bad-count")).readResolve();
        Lit46 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-final!")).readResolve();
        Lit45 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-final")).readResolve();
        Lit44 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-group-end!")).readResolve();
        Lit43 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-group-end")).readResolve();
        Lit42 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-group-begin!")).readResolve();
        Lit41 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-group-begin")).readResolve();
        Lit40 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-test-end!")).readResolve();
        Lit39 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-test-end")).readResolve();
        Lit38 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-test-begin!")).readResolve();
        Lit37 = (SimpleSymbol)(new SimpleSymbol("test-runner-on-test-begin")).readResolve();
        Lit36 = (SimpleSymbol)(new SimpleSymbol("test-runner-group-stack!")).readResolve();
        Lit35 = (SimpleSymbol)(new SimpleSymbol("test-runner-group-stack")).readResolve();
        Lit30 = (SimpleSymbol)(new SimpleSymbol("test-runner-skip-count!")).readResolve();
        Lit29 = (SimpleSymbol)(new SimpleSymbol("test-runner-skip-count")).readResolve();
        Lit28 = (SimpleSymbol)(new SimpleSymbol("test-runner-xfail-count!")).readResolve();
        Lit27 = (SimpleSymbol)(new SimpleSymbol("test-runner-xfail-count")).readResolve();
        Lit26 = (SimpleSymbol)(new SimpleSymbol("test-runner-xpass-count!")).readResolve();
        Lit25 = (SimpleSymbol)(new SimpleSymbol("test-runner-xpass-count")).readResolve();
        Lit24 = (SimpleSymbol)(new SimpleSymbol("test-runner-fail-count!")).readResolve();
        Lit23 = (SimpleSymbol)(new SimpleSymbol("test-runner-fail-count")).readResolve();
        Lit22 = (SimpleSymbol)(new SimpleSymbol("test-runner-pass-count!")).readResolve();
        Lit21 = (SimpleSymbol)(new SimpleSymbol("test-runner-pass-count")).readResolve();
        Lit20 = (SimpleSymbol)(new SimpleSymbol("test-runner?")).readResolve();
        Object aobj44[] = new Object[5];
        aobj44[0] = Lit150;
        aobj44[1] = PairWithPosition.make(PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 0x2b400c), LList.Empty, "testing.scm", 0x2b400c), "testing.scm", 0x2b4009), LList.Empty, "testing.scm", 0x2b4008);
        aobj44[2] = Lit52;
        aobj44[3] = Lit149;
        aobj44[4] = Lit89;
        Lit19 = new SyntaxTemplate("\001\001\001\001\001", "\021\030\004\021\030\fA\021\030\024\021\030\034\b\033\b\021\030$\021\030\034\t#\t\013\b\023", aobj44, 0);
        Object aobj45[] = new Object[8];
        aobj45[0] = Lit150;
        aobj45[1] = PairWithPosition.make(Lit149, PairWithPosition.make(PairWithPosition.make(Lit60, LList.Empty, "testing.scm", 0x2ae00c), LList.Empty, "testing.scm", 0x2ae00c), "testing.scm", 0x2ae009);
        aobj45[2] = Lit160;
        aobj45[3] = Lit52;
        aobj45[4] = Lit149;
        aobj45[5] = Lit145;
        aobj45[6] = PairWithPosition.make(Lit15, PairWithPosition.make(Lit7, LList.Empty, "testing.scm", 0x2b0027), "testing.scm", 0x2b0027);
        aobj45[7] = Lit89;
        Lit17 = new SyntaxTemplate("\001\001\001\001\001\001", "\021\030\004I\021\030\f\b\021\030\024\b\013\251\021\030\034\021\030$\b\021\030,A\021\030,\021\0304\b\013\b#\b\021\030<\021\030$\t+\t\023\b\033", aobj45, 0);
        Lit14 = (SimpleSymbol)(new SimpleSymbol("fail")).readResolve();
        Lit12 = (SimpleSymbol)(new SimpleSymbol("pass")).readResolve();
        SimpleSymbol simplesymbol34 = Lit12;
        SimpleSymbol simplesymbol35 = (SimpleSymbol)(new SimpleSymbol("xpass")).readResolve();
        Lit9 = simplesymbol35;
        Lit11 = PairWithPosition.make(simplesymbol34, PairWithPosition.make(simplesymbol35, LList.Empty, "testing.scm", 0x21e038), "testing.scm", 0x21e032);
        SimpleSymbol simplesymbol36 = Lit7;
        SimpleSymbol simplesymbol37 = (SimpleSymbol)(new SimpleSymbol("source-file")).readResolve();
        Lit4 = simplesymbol37;
        SimpleSymbol simplesymbol38 = (SimpleSymbol)(new SimpleSymbol("source-line")).readResolve();
        Lit5 = simplesymbol38;
        SimpleSymbol simplesymbol39 = (SimpleSymbol)(new SimpleSymbol("source-form")).readResolve();
        Lit6 = simplesymbol39;
        Lit10 = PairWithPosition.make(simplesymbol36, PairWithPosition.make(simplesymbol37, PairWithPosition.make(simplesymbol38, PairWithPosition.make(simplesymbol39, LList.Empty, "testing.scm", 0x1fa02a), "testing.scm", 0x1fa01e), "testing.scm", 0x1fa012), "testing.scm", 0x1fa007);
        Lit8 = PairWithPosition.make(Lit14, PairWithPosition.make(Lit9, LList.Empty, "testing.scm", 0x1e001b), "testing.scm", 0x1e0015);
        $instance = new testing();
        testing testing1 = $instance;
        test$Mnrunner$Qu = new ModuleMethod(testing1, 12, Lit20, 4097);
        test$Mnrunner$Mnpass$Mncount = new ModuleMethod(testing1, 13, Lit21, 4097);
        test$Mnrunner$Mnpass$Mncount$Ex = new ModuleMethod(testing1, 14, Lit22, 8194);
        test$Mnrunner$Mnfail$Mncount = new ModuleMethod(testing1, 15, Lit23, 4097);
        test$Mnrunner$Mnfail$Mncount$Ex = new ModuleMethod(testing1, 16, Lit24, 8194);
        test$Mnrunner$Mnxpass$Mncount = new ModuleMethod(testing1, 17, Lit25, 4097);
        test$Mnrunner$Mnxpass$Mncount$Ex = new ModuleMethod(testing1, 18, Lit26, 8194);
        test$Mnrunner$Mnxfail$Mncount = new ModuleMethod(testing1, 19, Lit27, 4097);
        test$Mnrunner$Mnxfail$Mncount$Ex = new ModuleMethod(testing1, 20, Lit28, 8194);
        test$Mnrunner$Mnskip$Mncount = new ModuleMethod(testing1, 21, Lit29, 4097);
        test$Mnrunner$Mnskip$Mncount$Ex = new ModuleMethod(testing1, 22, Lit30, 8194);
        $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist = new ModuleMethod(testing1, 23, Lit31, 4097);
        $Prvt$$Pctest$Mnrunner$Mnskip$Mnlist$Ex = new ModuleMethod(testing1, 24, Lit32, 8194);
        $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist = new ModuleMethod(testing1, 25, Lit33, 4097);
        $Prvt$$Pctest$Mnrunner$Mnfail$Mnlist$Ex = new ModuleMethod(testing1, 26, Lit34, 8194);
        test$Mnrunner$Mngroup$Mnstack = new ModuleMethod(testing1, 27, Lit35, 4097);
        test$Mnrunner$Mngroup$Mnstack$Ex = new ModuleMethod(testing1, 28, Lit36, 8194);
        test$Mnrunner$Mnon$Mntest$Mnbegin = new ModuleMethod(testing1, 29, Lit37, 4097);
        test$Mnrunner$Mnon$Mntest$Mnbegin$Ex = new ModuleMethod(testing1, 30, Lit38, 8194);
        test$Mnrunner$Mnon$Mntest$Mnend = new ModuleMethod(testing1, 31, Lit39, 4097);
        test$Mnrunner$Mnon$Mntest$Mnend$Ex = new ModuleMethod(testing1, 32, Lit40, 8194);
        test$Mnrunner$Mnon$Mngroup$Mnbegin = new ModuleMethod(testing1, 33, Lit41, 4097);
        test$Mnrunner$Mnon$Mngroup$Mnbegin$Ex = new ModuleMethod(testing1, 34, Lit42, 8194);
        test$Mnrunner$Mnon$Mngroup$Mnend = new ModuleMethod(testing1, 35, Lit43, 4097);
        test$Mnrunner$Mnon$Mngroup$Mnend$Ex = new ModuleMethod(testing1, 36, Lit44, 8194);
        test$Mnrunner$Mnon$Mnfinal = new ModuleMethod(testing1, 37, Lit45, 4097);
        test$Mnrunner$Mnon$Mnfinal$Ex = new ModuleMethod(testing1, 38, Lit46, 8194);
        test$Mnrunner$Mnon$Mnbad$Mncount = new ModuleMethod(testing1, 39, Lit47, 4097);
        test$Mnrunner$Mnon$Mnbad$Mncount$Ex = new ModuleMethod(testing1, 40, Lit48, 8194);
        test$Mnrunner$Mnon$Mnbad$Mnend$Mnname = new ModuleMethod(testing1, 41, Lit49, 4097);
        test$Mnrunner$Mnon$Mnbad$Mnend$Mnname$Ex = new ModuleMethod(testing1, 42, Lit50, 8194);
        test$Mnresult$Mnalist = new ModuleMethod(testing1, 43, Lit51, 4097);
        test$Mnresult$Mnalist$Ex = new ModuleMethod(testing1, 44, Lit52, 8194);
        test$Mnrunner$Mnaux$Mnvalue = new ModuleMethod(testing1, 45, Lit53, 4097);
        test$Mnrunner$Mnaux$Mnvalue$Ex = new ModuleMethod(testing1, 46, Lit54, 8194);
        test$Mnrunner$Mnreset = new ModuleMethod(testing1, 47, Lit55, 4097);
        test$Mnrunner$Mngroup$Mnpath = new ModuleMethod(testing1, 48, Lit56, 4097);
        $Pctest$Mnnull$Mncallback = new ModuleMethod(testing1, 49, Lit57, 4097);
        ModuleMethod modulemethod = new ModuleMethod(testing1, 50, null, 12291);
        modulemethod.setProperty("source-location", "testing.scm:182");
        lambda$Fn1 = modulemethod;
        ModuleMethod modulemethod1 = new ModuleMethod(testing1, 51, null, 12291);
        modulemethod1.setProperty("source-location", "testing.scm:187");
        lambda$Fn2 = modulemethod1;
        ModuleMethod modulemethod2 = new ModuleMethod(testing1, 52, null, 12291);
        modulemethod2.setProperty("source-location", "testing.scm:188");
        lambda$Fn3 = modulemethod2;
        test$Mnrunner$Mnnull = new ModuleMethod(testing1, 53, Lit58, 0);
        test$Mnrunner$Mnsimple = new ModuleMethod(testing1, 54, Lit59, 0);
        test$Mnrunner$Mnget = new ModuleMethod(testing1, 55, Lit60, 0);
        test$Mnrunner$Mncreate = new ModuleMethod(testing1, 56, Lit61, 0);
        $Prvt$$Pctest$Mnshould$Mnexecute = new ModuleMethod(testing1, 57, Lit62, 4097);
        $Pctest$Mnbegin = new ModuleMethod(testing1, 58, Lit63, 8194);
        test$Mnon$Mngroup$Mnbegin$Mnsimple = new ModuleMethod(testing1, 59, Lit64, 12291);
        test$Mnon$Mngroup$Mnend$Mnsimple = new ModuleMethod(testing1, 60, Lit65, 4097);
        test$Mnon$Mnbad$Mncount$Mnsimple = new ModuleMethod(testing1, 61, Lit66, 12291);
        test$Mnon$Mnbad$Mnend$Mnname$Mnsimple = new ModuleMethod(testing1, 62, Lit67, 12291);
        test$Mnon$Mnfinal$Mnsimple = new ModuleMethod(testing1, 63, Lit68, 4097);
        $Prvt$$Pctest$Mnend = new ModuleMethod(testing1, 64, Lit69, 8194);
        $Prvt$test$Mngroup = Macro.make(Lit70, Lit71, $instance);
        test$Mngroup$Mnwith$Mncleanup = Macro.make(Lit72, Lit73, $instance);
        test$Mnon$Mntest$Mnbegin$Mnsimple = new ModuleMethod(testing1, 65, Lit74, 4097);
        test$Mnresult$Mnref = Macro.make(Lit75, Lit76, $instance);
        test$Mnon$Mntest$Mnend$Mnsimple = new ModuleMethod(testing1, 66, Lit77, 4097);
        test$Mnresult$Mnset$Ex = new ModuleMethod(testing1, 67, Lit78, 12291);
        test$Mnresult$Mnclear = new ModuleMethod(testing1, 68, Lit79, 4097);
        test$Mnresult$Mnremove = new ModuleMethod(testing1, 69, Lit80, 8194);
        test$Mnresult$Mnkind = new ModuleMethod(testing1, 70, Lit81, -4096);
        test$Mnpassed$Qu = new ModuleMethod(testing1, 71, Lit82, -4096);
        $Prvt$$Pctest$Mnreport$Mnresult = new ModuleMethod(testing1, 72, Lit83, 0);
        $Prvt$$Pctest$Mnevaluate$Mnwith$Mncatch = Macro.make(Lit84, Lit85, $instance);
        $Prvt$$Pctest$Mnon$Mntest$Mnbegin = new ModuleMethod(testing1, 73, Lit86, 4097);
        $Prvt$$Pctest$Mnon$Mntest$Mnend = new ModuleMethod(testing1, 74, Lit87, 8194);
        test$Mnrunner$Mntest$Mnname = new ModuleMethod(testing1, 75, Lit88, 4097);
        $Prvt$$Pctest$Mncomp2body = Macro.make(Lit89, Lit90, $instance);
        $Prvt$$Pctest$Mnapproximimate$Eq = new ModuleMethod(testing1, 76, Lit91, 4097);
        $Prvt$$Pctest$Mncomp1body = Macro.make(Lit92, Lit93, $instance);
        SimpleSymbol simplesymbol40 = Lit94;
        ModuleMethod modulemethod3 = new ModuleMethod(testing1, 77, null, 4097);
        modulemethod3.setProperty("source-location", "testing.scm:660");
        test$Mnend = Macro.make(simplesymbol40, modulemethod3, $instance);
        SimpleSymbol simplesymbol41 = Lit99;
        ModuleMethod modulemethod4 = new ModuleMethod(testing1, 78, null, 4097);
        modulemethod4.setProperty("source-location", "testing.scm:669");
        test$Mnassert = Macro.make(simplesymbol41, modulemethod4, $instance);
        SimpleSymbol simplesymbol42 = Lit104;
        ModuleMethod modulemethod5 = new ModuleMethod(testing1, 79, null, 4097);
        modulemethod5.setProperty("source-location", "testing.scm:696");
        test$Mneqv = Macro.make(simplesymbol42, modulemethod5, $instance);
        SimpleSymbol simplesymbol43 = Lit106;
        ModuleMethod modulemethod6 = new ModuleMethod(testing1, 80, null, 4097);
        modulemethod6.setProperty("source-location", "testing.scm:698");
        test$Mneq = Macro.make(simplesymbol43, modulemethod6, $instance);
        SimpleSymbol simplesymbol44 = Lit108;
        ModuleMethod modulemethod7 = new ModuleMethod(testing1, 81, null, 4097);
        modulemethod7.setProperty("source-location", "testing.scm:700");
        test$Mnequal = Macro.make(simplesymbol44, modulemethod7, $instance);
        SimpleSymbol simplesymbol45 = Lit110;
        ModuleMethod modulemethod8 = new ModuleMethod(testing1, 82, null, 4097);
        modulemethod8.setProperty("source-location", "testing.scm:702");
        test$Mnapproximate = Macro.make(simplesymbol45, modulemethod8, $instance);
        $Prvt$$Pctest$Mnerror = Macro.make(Lit115, Lit116, $instance);
        SimpleSymbol simplesymbol46 = Lit117;
        ModuleMethod modulemethod9 = new ModuleMethod(testing1, 83, null, 4097);
        modulemethod9.setProperty("source-location", "testing.scm:843");
        test$Mnerror = Macro.make(simplesymbol46, modulemethod9, $instance);
        test$Mnapply = new ModuleMethod(testing1, 84, Lit124, -4095);
        test$Mnwith$Mnrunner = Macro.make(Lit125, Lit126, $instance);
        $Prvt$$Pctest$Mnmatch$Mnnth = new ModuleMethod(testing1, 85, Lit127, 8194);
        test$Mnmatch$Mnnth = Macro.make(Lit128, Lit129, $instance);
        $Prvt$$Pctest$Mnmatch$Mnall = new ModuleMethod(testing1, 86, Lit130, -4096);
        test$Mnmatch$Mnall = Macro.make(Lit131, Lit132, $instance);
        $Prvt$$Pctest$Mnmatch$Mnany = new ModuleMethod(testing1, 87, Lit133, -4096);
        test$Mnmatch$Mnany = Macro.make(Lit134, Lit135, $instance);
        $Prvt$$Pctest$Mnas$Mnspecifier = new ModuleMethod(testing1, 88, Lit136, 4097);
        test$Mnskip = Macro.make(Lit137, Lit138, $instance);
        test$Mnexpect$Mnfail = Macro.make(Lit139, Lit140, $instance);
        test$Mnmatch$Mnname = new ModuleMethod(testing1, 89, Lit141, 4097);
        test$Mnread$Mneval$Mnstring = new ModuleMethod(testing1, 90, Lit142, 4097);
        $instance.run();
    }
}
