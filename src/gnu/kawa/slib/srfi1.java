// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Map;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
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
import gnu.math.Numeric;
import kawa.lang.Continuation;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.standard.Scheme;
import kawa.standard.append;
import kawa.standard.call_with_values;

public class srfi1 extends ModuleBody
{
    public class frame extends ModuleBody
    {

        public static Object lambda2recur(Object obj)
        {
            frame0 frame0_1 = new frame0();
            frame0_1.lis = obj;
            if (srfi1.isNullList(frame0_1.lis) != Boolean.FALSE)
            {
                Object aobj[] = new Object[2];
                aobj[0] = frame0_1.lis;
                aobj[1] = frame0_1.lis;
                return misc.values(aobj);
            } else
            {
                frame0_1.elt = lists.car.apply1(frame0_1.lis);
                return call_with_values.callWithValues(frame0_1.Fn1, frame0_1.Fn2);
            }
        }

        public frame()
        {
        }
    }

    public class frame0 extends ModuleBody
    {

        Object elt;
        final ModuleMethod lambda$Fn1 = new ModuleMethod(this, 1, null, 0);
        final ModuleMethod lambda$Fn2;
        Object lis;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 1)
            {
                return lambda3();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 2)
            {
                return lambda4(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda3()
        {
            return frame.lambda2recur(lists.cdr.apply1(lis));
        }

        Object lambda4(Object obj, Object obj1)
        {
            Object aobj[] = new Object[2];
            aobj[0] = lists.cons(lists.car.apply1(elt), obj);
            aobj[1] = lists.cons(lists.cadr.apply1(elt), obj1);
            return misc.values(aobj);
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

        public frame0()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 2, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:627");
            lambda$Fn2 = modulemethod;
        }
    }

    public class frame1 extends ModuleBody
    {

        public static Object lambda5recur(Object obj)
        {
            frame2 frame2_1 = new frame2();
            frame2_1.lis = obj;
            if (srfi1.isNullList(frame2_1.lis) != Boolean.FALSE)
            {
                Object aobj[] = new Object[3];
                aobj[0] = frame2_1.lis;
                aobj[1] = frame2_1.lis;
                aobj[2] = frame2_1.lis;
                return misc.values(aobj);
            } else
            {
                frame2_1.elt = lists.car.apply1(frame2_1.lis);
                return call_with_values.callWithValues(frame2_1.Fn3, frame2_1.Fn4);
            }
        }

        public frame1()
        {
        }
    }

    public class frame10 extends ModuleBody
    {

        Procedure f;
        Object zero;

        public Object lambda19recur(Object obj)
        {
            Object obj1 = srfi1.$PcCdrs(obj);
            if (lists.isNull(obj1))
            {
                return zero;
            } else
            {
                gnu.kawa.functions.Apply apply = Scheme.apply;
                Procedure procedure = f;
                Object aobj[] = new Object[2];
                aobj[0] = obj;
                aobj[1] = LList.list1(lambda19recur(obj1));
                return apply.apply2(procedure, srfi1.append$Ex$V(aobj));
            }
        }

        public Object lambda20recur(Object obj)
        {
            if (srfi1.isNullList(obj) != Boolean.FALSE)
            {
                return zero;
            } else
            {
                return f.apply2(obj, lambda20recur(lists.cdr.apply1(obj)));
            }
        }

        public frame10()
        {
        }
    }

    public class frame11 extends ModuleBody
    {

        Procedure f;

        public Object lambda21recur(Object obj, Object obj1)
        {
            if (lists.isPair(obj1))
            {
                obj = f.apply2(obj, lambda21recur(lists.car.apply1(obj1), lists.cdr.apply1(obj1)));
            }
            return obj;
        }

        public frame11()
        {
        }
    }

    public class frame12 extends ModuleBody
    {

        Procedure f;
        final ModuleMethod lambda$Fn11;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 11)
            {
                lambda22(obj);
                return Values.empty;
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        void lambda22(Object obj)
        {
            Pair pair;
            try
            {
                pair = (Pair)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "set-car!", 1, obj);
            }
            lists.setCar$Ex(pair, f.apply1(lists.car.apply1(obj)));
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

        public frame12()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 11, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:961");
            lambda$Fn11 = modulemethod;
        }
    }

    public class frame13 extends ModuleBody
    {

        Procedure f;

        public Object lambda23recur(Object obj, Object obj1)
        {
            frame14 frame14_1 = new frame14();
            frame14_1.staticLink = this;
            frame14_1.lists = obj;
            frame14_1.res = obj1;
            return call_with_values.callWithValues(frame14_1.Fn12, frame14_1.Fn13);
        }

        public frame13()
        {
        }
    }

    public class frame14 extends ModuleBody
    {

        final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 12, null, 0);
        final ModuleMethod lambda$Fn13;
        Object lists;
        Object res;
        frame13 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 12)
            {
                return lambda24();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 13)
            {
                return lambda25(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda24()
        {
            return srfi1.$PcCars$PlCdrs(lists);
        }

        Object lambda25(Object obj, Object obj1)
        {
            if (srfi1.isNotPair(obj))
            {
                Object obj3 = res;
                Object obj2;
                LList llist;
                try
                {
                    llist = (LList)obj3;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "reverse!", 1, obj3);
                }
                return kawa.lib.lists.reverse$Ex(llist);
            }
            obj2 = Scheme.apply.apply2(staticLink.f, obj);
            if (obj2 != Boolean.FALSE)
            {
                return staticLink.lambda23recur(obj1, kawa.lib.lists.cons(obj2, res));
            } else
            {
                return staticLink.lambda23recur(obj1, res);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 12)
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
            if (modulemethod.selector == 13)
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

        public frame14()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 13, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:969");
            lambda$Fn13 = modulemethod;
        }
    }

    public class frame15 extends ModuleBody
    {

        final ModuleMethod lambda$Fn14;
        Object pred;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 14)
            {
                if (lambda26(obj))
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

        boolean lambda26(Object obj)
        {
            int i;
            if (Scheme.applyToArgs.apply2(pred, obj) != Boolean.FALSE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            return 1 & i + 1;
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

        public frame15()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 14, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1199");
            lambda$Fn14 = modulemethod;
        }
    }

    public class frame16 extends ModuleBody
    {

        final ModuleMethod lambda$Fn15;
        Object pred;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 15)
            {
                if (lambda27(obj))
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

        boolean lambda27(Object obj)
        {
            int i;
            if (Scheme.applyToArgs.apply2(pred, obj) != Boolean.FALSE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            return 1 & i + 1;
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

        public frame16()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 15, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1200");
            lambda$Fn15 = modulemethod;
        }
    }

    public class frame17 extends ModuleBody
    {

        final ModuleMethod lambda$Fn16;
        Object maybe$Mn$Eq;
        Object x;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 16)
            {
                if (lambda28(obj))
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

        boolean lambda28(Object obj)
        {
            int i;
            if (Scheme.applyToArgs.apply3(maybe$Mn$Eq, x, obj) != Boolean.FALSE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            return 1 & i + 1;
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

        public frame17()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 16, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1222");
            lambda$Fn16 = modulemethod;
        }
    }

    public class frame18 extends ModuleBody
    {

        final ModuleMethod lambda$Fn17;
        Object maybe$Mn$Eq;
        Object x;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 17)
            {
                if (lambda29(obj))
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

        boolean lambda29(Object obj)
        {
            int i;
            if (Scheme.applyToArgs.apply3(maybe$Mn$Eq, x, obj) != Boolean.FALSE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            return 1 & i + 1;
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 17)
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

        public frame18()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 17, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1225");
            lambda$Fn17 = modulemethod;
        }
    }

    public class frame19 extends ModuleBody
    {

        Procedure maybe$Mn$Eq;

        public Object lambda30recur(Object obj)
        {
            if (srfi1.isNullList(obj) == Boolean.FALSE)
            {
                Object obj1 = lists.car.apply1(obj);
                Object obj2 = lists.cdr.apply1(obj);
                Object obj3 = lambda30recur(srfi1.delete(obj1, obj2, maybe$Mn$Eq));
                if (obj2 != obj3)
                {
                    return lists.cons(obj1, obj3);
                }
            }
            return obj;
        }

        public frame19()
        {
        }
    }

    public class frame2 extends ModuleBody
    {

        Object elt;
        final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, null, 0);
        final ModuleMethod lambda$Fn4;
        Object lis;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 3)
            {
                return lambda6();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 4)
            {
                return lambda7(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda6()
        {
            return frame1.lambda5recur(lists.cdr.apply1(lis));
        }

        Object lambda7(Object obj, Object obj1, Object obj2)
        {
            Object aobj[] = new Object[3];
            aobj[0] = lists.cons(lists.car.apply1(elt), obj);
            aobj[1] = lists.cons(lists.cadr.apply1(elt), obj1);
            aobj[2] = lists.cons(lists.caddr.apply1(elt), obj2);
            return misc.values(aobj);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 3)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 4)
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

        public frame2()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 4, null, 12291);
            modulemethod.setProperty("source-location", "srfi1.scm:635");
            lambda$Fn4 = modulemethod;
        }
    }

    public class frame20 extends ModuleBody
    {

        Procedure maybe$Mn$Eq;

        public Object lambda31recur(Object obj)
        {
            if (srfi1.isNullList(obj) == Boolean.FALSE)
            {
                Object obj1 = lists.car.apply1(obj);
                Object obj2 = lists.cdr.apply1(obj);
                Object obj3 = lambda31recur(srfi1.delete$Ex(obj1, obj2, maybe$Mn$Eq));
                if (obj2 != obj3)
                {
                    return lists.cons(obj1, obj3);
                }
            }
            return obj;
        }

        public frame20()
        {
        }
    }

    public class frame21 extends ModuleBody
    {

        Object key;
        final ModuleMethod lambda$Fn18;
        Object maybe$Mn$Eq;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 18)
            {
                if (lambda32(obj))
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

        boolean lambda32(Object obj)
        {
            int i;
            if (Scheme.applyToArgs.apply3(maybe$Mn$Eq, key, lists.car.apply1(obj)) != Boolean.FALSE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            return 1 & i + 1;
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

        public frame21()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 18, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1280");
            lambda$Fn18 = modulemethod;
        }
    }

    public class frame22 extends ModuleBody
    {

        Object key;
        final ModuleMethod lambda$Fn19;
        Object maybe$Mn$Eq;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 19)
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
            int i;
            if (Scheme.applyToArgs.apply3(maybe$Mn$Eq, key, lists.car.apply1(obj)) != Boolean.FALSE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            return 1 & i + 1;
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 19)
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

        public frame22()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 19, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1283");
            lambda$Fn19 = modulemethod;
        }
    }

    public class frame23 extends ModuleBody
    {

        Procedure pred;

        public Object lambda34recur(Object obj)
        {
            if (srfi1.isNullList(obj) != Boolean.FALSE)
            {
                return LList.Empty;
            }
            Object obj1 = lists.car.apply1(obj);
            if (pred.apply1(obj1) != Boolean.FALSE)
            {
                return lists.cons(obj1, lambda34recur(lists.cdr.apply1(obj)));
            } else
            {
                return LList.Empty;
            }
        }

        public frame23()
        {
        }
    }

    public class frame24 extends ModuleBody
    {

        final ModuleMethod lambda$Fn20;
        Object pred;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 20)
            {
                if (lambda35(obj))
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

        boolean lambda35(Object obj)
        {
            int i;
            if (Scheme.applyToArgs.apply2(pred, obj) != Boolean.FALSE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            return 1 & i + 1;
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

        public frame24()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 20, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1343");
            lambda$Fn20 = modulemethod;
        }
    }

    public class frame25 extends ModuleBody
    {

        final ModuleMethod lambda$Fn21;
        Object pred;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 21)
            {
                if (lambda36(obj))
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

        boolean lambda36(Object obj)
        {
            int i;
            if (Scheme.applyToArgs.apply2(pred, obj) != Boolean.FALSE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            return 1 & i + 1;
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

        public frame25()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 21, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1344");
            lambda$Fn21 = modulemethod;
        }
    }

    public class frame26 extends ModuleBody
    {

        final ModuleMethod lambda$Fn22 = new ModuleMethod(this, 22, null, 0);
        final ModuleMethod lambda$Fn23;
        Object lis1;
        LList lists;
        Procedure pred;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 22)
            {
                return lambda37();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 23)
            {
                return lambda38(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda37()
        {
            return srfi1.$PcCars$PlCdrs(kawa.lib.lists.cons(lis1, lists));
        }

        Object lambda38(Object obj, Object obj1)
        {
            boolean flag = kawa.lib.lists.isPair(obj);
            Object obj2;
            Object obj3;
            Object obj4;
            Object obj5;
            if (flag)
            {
                do
                {
                    obj2 = srfi1.$PcCars$PlCdrs$SlPair(obj1);
                    obj3 = lists.car.apply1(obj2);
                    obj4 = lists.cdr.apply1(obj2);
                    if (kawa.lib.lists.isPair(obj3))
                    {
                        obj5 = Scheme.apply.apply2(pred, obj);
                        if (obj5 != Boolean.FALSE)
                        {
                            return obj5;
                        }
                        obj1 = obj4;
                        obj = obj3;
                    } else
                    {
                        return Scheme.apply.apply2(pred, obj);
                    }
                } while (true);
            }
            if (flag)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 22)
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
            if (modulemethod.selector == 23)
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

        public frame26()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 23, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:1350");
            lambda$Fn23 = modulemethod;
        }
    }

    public class frame27 extends ModuleBody
    {

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
            frame28 frame28_1 = new frame28();
            frame28_1.staticLink = this;
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
                return frame28_1.lambda41lp(obj, obj1);
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

        public frame27()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 27, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:1378");
            lambda$Fn25 = modulemethod;
        }
    }

    public class frame28 extends ModuleBody
    {

        frame27 staticLink;

        public Object lambda41lp(Object obj, Object obj1)
        {
            frame29 frame29_1 = new frame29();
            frame29_1.staticLink = this;
            frame29_1.heads = obj;
            frame29_1.tails = obj1;
            return call_with_values.callWithValues(frame29_1.Fn26, frame29_1.Fn27);
        }

        public frame28()
        {
        }
    }

    public class frame29 extends ModuleBody
    {

        Object heads;
        final ModuleMethod lambda$Fn26 = new ModuleMethod(this, 24, null, 0);
        final ModuleMethod lambda$Fn27;
        frame28 staticLink;
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
            if (lists.isPair(obj))
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

        public frame29()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 25, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:1381");
            lambda$Fn27 = modulemethod;
        }
    }

    public class frame3 extends ModuleBody
    {

        public static Object lambda8recur(Object obj)
        {
            frame4 frame4_1 = new frame4();
            frame4_1.lis = obj;
            if (srfi1.isNullList(frame4_1.lis) != Boolean.FALSE)
            {
                Object aobj[] = new Object[4];
                aobj[0] = frame4_1.lis;
                aobj[1] = frame4_1.lis;
                aobj[2] = frame4_1.lis;
                aobj[3] = frame4_1.lis;
                return misc.values(aobj);
            } else
            {
                frame4_1.elt = lists.car.apply1(frame4_1.lis);
                return call_with_values.callWithValues(frame4_1.Fn5, frame4_1.Fn6);
            }
        }

        public frame3()
        {
        }
    }

    public class frame30 extends ModuleBody
    {

        Procedure pred;

        public Object lambda44lp(Object obj, Object obj1)
        {
            frame31 frame31_1 = new frame31();
            frame31_1.staticLink = this;
            frame31_1.lists = obj;
            frame31_1.n = obj1;
            return call_with_values.callWithValues(frame31_1.Fn28, frame31_1.Fn29);
        }

        public frame30()
        {
        }
    }

    public class frame31 extends ModuleBody
    {

        final ModuleMethod lambda$Fn28 = new ModuleMethod(this, 28, null, 0);
        final ModuleMethod lambda$Fn29;
        Object lists;
        Object n;
        frame30 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 28)
            {
                return lambda45();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 29)
            {
                return lambda46(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda45()
        {
            return srfi1.$PcCars$PlCdrs(lists);
        }

        Object lambda46(Object obj, Object obj1)
        {
            boolean flag = kawa.lib.lists.isPair(obj);
            if (flag)
            {
                if (Scheme.apply.apply2(staticLink.pred, obj) != Boolean.FALSE)
                {
                    return n;
                } else
                {
                    return staticLink.lambda44lp(obj1, AddOp.$Pl.apply2(n, srfi1.Lit1));
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

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 28)
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
            if (modulemethod.selector == 29)
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

        public frame31()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 29, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:1404");
            lambda$Fn29 = modulemethod;
        }
    }

    public class frame32 extends ModuleBody
    {

        Procedure $Eq;
        final ModuleMethod lambda$Fn30;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 30)
            {
                return lambda47(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda47(Object obj, Object obj1)
        {
            if (lists.member(obj, obj1, $Eq) != Boolean.FALSE)
            {
                return obj1;
            } else
            {
                return lists.cons(obj, obj1);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 30)
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

        public frame32()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 30, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:1466");
            lambda$Fn30 = modulemethod;
        }
    }

    public class frame33 extends ModuleBody
    {

        Procedure $Eq;
        final ModuleMethod lambda$Fn31;
        final ModuleMethod lambda$Fn32;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.apply2(modulemethod, obj, obj1);

            case 32: // ' '
                return lambda49(obj, obj1);

            case 33: // '!'
                return lambda48(obj, obj1);
            }
        }

        Object lambda48(Object obj, Object obj1)
        {
            if (!lists.isNull(obj))
            {
                if (lists.isNull(obj1))
                {
                    return obj;
                }
                if (obj != obj1)
                {
                    return srfi1.fold$V(lambda$Fn32, obj1, obj, new Object[0]);
                }
            }
            return obj1;
        }

        Object lambda49(Object obj, Object obj1)
        {
            frame34 frame34_1 = new frame34();
            frame34_1.staticLink = this;
            frame34_1.elt = obj;
            if (srfi1.any$V(frame34_1.Fn33, obj1, new Object[0]) != Boolean.FALSE)
            {
                return obj1;
            } else
            {
                return lists.cons(frame34_1.elt, obj1);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.match2(modulemethod, obj, obj1, callcontext);

            case 33: // '!'
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;

            case 32: // ' '
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }

        public frame33()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 32, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:1476");
            lambda$Fn32 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 33, null, 8194);
            modulemethod1.setProperty("source-location", "srfi1.scm:1471");
            lambda$Fn31 = modulemethod1;
        }
    }

    public class frame34 extends ModuleBody
    {

        Object elt;
        final ModuleMethod lambda$Fn33;
        frame33 staticLink;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 31)
            {
                return lambda50(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda50(Object obj)
        {
            return staticLink.Eq.apply2(obj, elt);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 31)
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

        public frame34()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 31, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1476");
            lambda$Fn33 = modulemethod;
        }
    }

    public class frame35 extends ModuleBody
    {

        Procedure $Eq;
        final ModuleMethod lambda$Fn34;
        final ModuleMethod lambda$Fn35;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.apply2(modulemethod, obj, obj1);

            case 35: // '#'
                return lambda52(obj, obj1);

            case 36: // '$'
                return lambda51(obj, obj1);
            }
        }

        Object lambda51(Object obj, Object obj1)
        {
            if (!lists.isNull(obj))
            {
                if (lists.isNull(obj1))
                {
                    return obj;
                }
                if (obj != obj1)
                {
                    return srfi1.pairFold$V(lambda$Fn35, obj1, obj, new Object[0]);
                }
            }
            return obj1;
        }

        Object lambda52(Object obj, Object obj1)
        {
            frame36 frame36_1 = new frame36();
            frame36_1.staticLink = this;
            frame36_1.elt = lists.car.apply1(obj);
            if (srfi1.any$V(frame36_1.Fn36, obj1, new Object[0]) != Boolean.FALSE)
            {
                return obj1;
            }
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
            return obj;
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.match2(modulemethod, obj, obj1, callcontext);

            case 36: // '$'
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;

            case 35: // '#'
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }

        public frame35()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 35, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:1488");
            lambda$Fn35 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 36, null, 8194);
            modulemethod1.setProperty("source-location", "srfi1.scm:1483");
            lambda$Fn34 = modulemethod1;
        }
    }

    public class frame36 extends ModuleBody
    {

        Object elt;
        final ModuleMethod lambda$Fn36;
        frame35 staticLink;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 34)
            {
                return lambda53(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda53(Object obj)
        {
            return staticLink.Eq.apply2(obj, elt);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 34)
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

        public frame36()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 34, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1490");
            lambda$Fn36 = modulemethod;
        }
    }

    public class frame37 extends ModuleBody
    {

        Procedure $Eq;
        final ModuleMethod lambda$Fn37;
        Object lists;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 38)
            {
                return lambda54(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda54(Object obj)
        {
            frame38 frame38_1 = new frame38();
            frame38_1.staticLink = this;
            frame38_1.x = obj;
            return srfi1.every$V(frame38_1.Fn38, lists, new Object[0]);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 38)
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

        public frame37()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 38, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1501");
            lambda$Fn37 = modulemethod;
        }
    }

    public class frame38 extends ModuleBody
    {

        final ModuleMethod lambda$Fn38;
        frame37 staticLink;
        Object x;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 37)
            {
                return lambda55(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda55(Object obj)
        {
            return lists.member(x, obj, staticLink.Eq);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 37)
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

        public frame38()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 37, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1502");
            lambda$Fn38 = modulemethod;
        }
    }

    public class frame39 extends ModuleBody
    {

        Procedure $Eq;
        final ModuleMethod lambda$Fn39;
        Object lists;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 40)
            {
                return lambda56(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda56(Object obj)
        {
            frame40 frame40_1 = new frame40();
            frame40_1.staticLink = this;
            frame40_1.x = obj;
            return srfi1.every$V(frame40_1.Fn40, lists, new Object[0]);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 40)
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

        public frame39()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 40, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1509");
            lambda$Fn39 = modulemethod;
        }
    }

    public class frame4 extends ModuleBody
    {

        Object elt;
        final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 5, null, 0);
        final ModuleMethod lambda$Fn6;
        Object lis;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 5)
            {
                return lambda9();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3)
        {
            if (modulemethod.selector == 6)
            {
                return lambda10(obj, obj1, obj2, obj3);
            } else
            {
                return super.apply4(modulemethod, obj, obj1, obj2, obj3);
            }
        }

        Object lambda10(Object obj, Object obj1, Object obj2, Object obj3)
        {
            Object aobj[] = new Object[4];
            aobj[0] = lists.cons(lists.car.apply1(elt), obj);
            aobj[1] = lists.cons(lists.cadr.apply1(elt), obj1);
            aobj[2] = lists.cons(lists.caddr.apply1(elt), obj2);
            aobj[3] = lists.cons(lists.cadddr.apply1(elt), obj3);
            return misc.values(aobj);
        }

        Object lambda9()
        {
            return frame3.lambda8recur(lists.cdr.apply1(lis));
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

        public int match4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3, CallContext callcontext)
        {
            if (modulemethod.selector == 6)
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

        public frame4()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 6, null, 16388);
            modulemethod.setProperty("source-location", "srfi1.scm:644");
            lambda$Fn6 = modulemethod;
        }
    }

    public class frame40 extends ModuleBody
    {

        final ModuleMethod lambda$Fn40;
        frame39 staticLink;
        Object x;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 39)
            {
                return lambda57(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda57(Object obj)
        {
            return lists.member(x, obj, staticLink.Eq);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 39)
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

        public frame40()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 39, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1510");
            lambda$Fn40 = modulemethod;
        }
    }

    public class frame41 extends ModuleBody
    {

        Procedure $Eq;
        final ModuleMethod lambda$Fn41;
        Object lists;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 42)
            {
                return lambda58(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda58(Object obj)
        {
            frame42 frame42_1 = new frame42();
            frame42_1.staticLink = this;
            frame42_1.x = obj;
            return srfi1.every$V(frame42_1.Fn42, lists, new Object[0]);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 42)
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

        public frame41()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 42, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1518");
            lambda$Fn41 = modulemethod;
        }
    }

    public class frame42 extends ModuleBody
    {

        final ModuleMethod lambda$Fn42;
        frame41 staticLink;
        Object x;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 41)
            {
                if (lambda59(obj))
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

        boolean lambda59(Object obj)
        {
            int i;
            if (lists.member(x, obj, staticLink.Eq) != Boolean.FALSE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            return 1 & i + 1;
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 41)
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

        public frame42()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 41, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1519");
            lambda$Fn42 = modulemethod;
        }
    }

    public class frame43 extends ModuleBody
    {

        Procedure $Eq;
        final ModuleMethod lambda$Fn43;
        Object lists;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 44)
            {
                return lambda60(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda60(Object obj)
        {
            frame44 frame44_1 = new frame44();
            frame44_1.staticLink = this;
            frame44_1.x = obj;
            return srfi1.every$V(frame44_1.Fn44, lists, new Object[0]);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 44)
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

        public frame43()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 44, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1527");
            lambda$Fn43 = modulemethod;
        }
    }

    public class frame44 extends ModuleBody
    {

        final ModuleMethod lambda$Fn44;
        frame43 staticLink;
        Object x;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 43)
            {
                if (lambda61(obj))
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

        boolean lambda61(Object obj)
        {
            int i;
            if (lists.member(x, obj, staticLink.Eq) != Boolean.FALSE)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            return 1 & i + 1;
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 43)
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

        public frame44()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 43, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1528");
            lambda$Fn44 = modulemethod;
        }
    }

    public class frame45 extends ModuleBody
    {

        Procedure $Eq;
        final ModuleMethod lambda$Fn45;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 48)
            {
                return lambda62(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda62(Object obj, Object obj1)
        {
            frame46 frame46_1 = new frame46();
            frame46_1.staticLink = this;
            frame46_1.b = obj;
            frame46_1.a = obj1;
            return call_with_values.callWithValues(frame46_1.Fn46, frame46_1.Fn47);
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 48)
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

        public frame45()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 48, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:1534");
            lambda$Fn45 = modulemethod;
        }
    }

    public class frame46 extends ModuleBody
    {

        Object a;
        Object b;
        final ModuleMethod lambda$Fn46 = new ModuleMethod(this, 46, null, 0);
        final ModuleMethod lambda$Fn47;
        frame45 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 46)
            {
                return lambda63();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 47)
            {
                return lambda64(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda63()
        {
            Procedure procedure = staticLink.Eq;
            Object obj = a;
            Object aobj[] = new Object[1];
            aobj[0] = b;
            return srfi1.lsetDiff$PlIntersection$V(procedure, obj, aobj);
        }

        Object lambda64(Object obj, Object obj1)
        {
            frame47 frame47_1 = new frame47();
            frame47_1.staticLink = this;
            frame47_1.Mnb = obj1;
            if (lists.isNull(obj))
            {
                Procedure procedure = staticLink.Eq;
                Object obj2 = b;
                Object aobj1[] = new Object[1];
                aobj1[0] = a;
                return srfi1.lsetDifference$V(procedure, obj2, aobj1);
            }
            if (lists.isNull(frame47_1.Mnb))
            {
                Object aobj[] = new Object[2];
                aobj[0] = b;
                aobj[1] = a;
                return append.append$V(aobj);
            } else
            {
                return srfi1.fold$V(frame47_1.Fn48, obj, b, new Object[0]);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 46)
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
            if (modulemethod.selector == 47)
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

        public frame46()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 47, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:1544");
            lambda$Fn47 = modulemethod;
        }
    }

    public class frame47 extends ModuleBody
    {

        Object a$Mnint$Mnb;
        final ModuleMethod lambda$Fn48;
        frame46 staticLink;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 45)
            {
                return lambda65(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda65(Object obj, Object obj1)
        {
            if (lists.member(obj, a$Mnint$Mnb, staticLink.staticLink.Eq) != Boolean.FALSE)
            {
                return obj1;
            } else
            {
                return lists.cons(obj, obj1);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 45)
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

        public frame47()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 45, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:1547");
            lambda$Fn48 = modulemethod;
        }
    }

    public class frame48 extends ModuleBody
    {

        Procedure $Eq;
        final ModuleMethod lambda$Fn49;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 52)
            {
                return lambda66(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda66(Object obj, Object obj1)
        {
            frame49 frame49_1 = new frame49();
            frame49_1.staticLink = this;
            frame49_1.b = obj;
            frame49_1.a = obj1;
            return call_with_values.callWithValues(frame49_1.Fn50, frame49_1.Fn51);
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 52)
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

        public frame48()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 52, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:1555");
            lambda$Fn49 = modulemethod;
        }
    }

    public class frame49 extends ModuleBody
    {

        Object a;
        Object b;
        final ModuleMethod lambda$Fn50 = new ModuleMethod(this, 50, null, 0);
        final ModuleMethod lambda$Fn51;
        frame48 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 50)
            {
                return lambda67();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 51)
            {
                return lambda68(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda67()
        {
            Procedure procedure = staticLink.Eq;
            Object obj = a;
            Object aobj[] = new Object[1];
            aobj[0] = b;
            return srfi1.lsetDiff$PlIntersection$Ex$V(procedure, obj, aobj);
        }

        Object lambda68(Object obj, Object obj1)
        {
            frame50 frame50_1 = new frame50();
            frame50_1.staticLink = this;
            frame50_1.Mnb = obj1;
            if (lists.isNull(obj))
            {
                Procedure procedure = staticLink.Eq;
                Object obj2 = b;
                Object aobj1[] = new Object[1];
                aobj1[0] = a;
                return srfi1.lsetDifference$Ex$V(procedure, obj2, aobj1);
            }
            if (lists.isNull(frame50_1.Mnb))
            {
                Object aobj[] = new Object[2];
                aobj[0] = b;
                aobj[1] = a;
                return srfi1.append$Ex$V(aobj);
            } else
            {
                return srfi1.pairFold$V(frame50_1.Fn52, obj, b, new Object[0]);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 50)
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
            if (modulemethod.selector == 51)
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

        public frame49()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 51, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:1565");
            lambda$Fn51 = modulemethod;
        }
    }

    public class frame5 extends ModuleBody
    {

        public static Object lambda11recur(Object obj)
        {
            frame6 frame6_1 = new frame6();
            frame6_1.lis = obj;
            if (srfi1.isNullList(frame6_1.lis) != Boolean.FALSE)
            {
                Object aobj[] = new Object[5];
                aobj[0] = frame6_1.lis;
                aobj[1] = frame6_1.lis;
                aobj[2] = frame6_1.lis;
                aobj[3] = frame6_1.lis;
                aobj[4] = frame6_1.lis;
                return misc.values(aobj);
            } else
            {
                frame6_1.elt = lists.car.apply1(frame6_1.lis);
                return call_with_values.callWithValues(frame6_1.Fn7, frame6_1.Fn8);
            }
        }

        public frame5()
        {
        }
    }

    public class frame50 extends ModuleBody
    {

        Object a$Mnint$Mnb;
        final ModuleMethod lambda$Fn52;
        frame49 staticLink;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 49)
            {
                return lambda69(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda69(Object obj, Object obj1)
        {
            if (lists.member(lists.car.apply1(obj), a$Mnint$Mnb, staticLink.staticLink.Eq) != Boolean.FALSE)
            {
                return obj1;
            }
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
            return obj;
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 49)
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

        public frame50()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 49, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:1568");
            lambda$Fn52 = modulemethod;
        }
    }

    public class frame51 extends ModuleBody
    {

        Procedure $Eq;
        final ModuleMethod lambda$Fn53;
        LList lists;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 54)
            {
                if (lambda70(obj))
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

        boolean lambda70(Object obj)
        {
            frame52 frame52_1 = new frame52();
            frame52_1.staticLink = this;
            frame52_1.elt = obj;
            Object obj1 = srfi1.any$V(frame52_1.Fn54, lists, new Object[0]);
            Boolean boolean1 = Boolean.FALSE;
            int i = 0;
            if (obj1 != boolean1)
            {
                i = 1;
            }
            return 1 & i + 1;
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 54)
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

        public frame51()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 54, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1579");
            lambda$Fn53 = modulemethod;
        }
    }

    public class frame52 extends ModuleBody
    {

        Object elt;
        final ModuleMethod lambda$Fn54;
        frame51 staticLink;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 53)
            {
                return lambda71(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda71(Object obj)
        {
            return lists.member(elt, obj, staticLink.Eq);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 53)
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

        public frame52()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 53, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1580");
            lambda$Fn54 = modulemethod;
        }
    }

    public class frame53 extends ModuleBody
    {

        Procedure $Eq;
        final ModuleMethod lambda$Fn55;
        LList lists;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 56)
            {
                if (lambda72(obj))
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

        boolean lambda72(Object obj)
        {
            frame54 frame54_1 = new frame54();
            frame54_1.staticLink = this;
            frame54_1.elt = obj;
            Object obj1 = srfi1.any$V(frame54_1.Fn56, lists, new Object[0]);
            Boolean boolean1 = Boolean.FALSE;
            int i = 0;
            if (obj1 != boolean1)
            {
                i = 1;
            }
            return 1 & i + 1;
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 56)
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

        public frame53()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 56, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1587");
            lambda$Fn55 = modulemethod;
        }
    }

    public class frame54 extends ModuleBody
    {

        Object elt;
        final ModuleMethod lambda$Fn56;
        frame53 staticLink;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 55)
            {
                return lambda73(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda73(Object obj)
        {
            return lists.member(elt, obj, staticLink.Eq);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 55)
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

        public frame54()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 55, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1588");
            lambda$Fn56 = modulemethod;
        }
    }

    public class frame55 extends ModuleBody
    {

        Continuation abort;

        public Object lambda74recur(Object obj)
        {
            if (lists.isPair(obj))
            {
                Object obj1 = lists.car.apply1(obj);
                if (srfi1.isNullList(obj1) != Boolean.FALSE)
                {
                    return abort.apply1(LList.Empty);
                } else
                {
                    return lists.cons(lists.cdr.apply1(obj1), lambda74recur(lists.cdr.apply1(obj)));
                }
            } else
            {
                return LList.Empty;
            }
        }

        public frame55()
        {
        }
    }

    public class frame56 extends ModuleBody
    {

        Object last$Mnelt;

        public Object lambda75recur(Object obj)
        {
            if (lists.isPair(obj))
            {
                return lists.cons(lists.caar.apply1(obj), lambda75recur(lists.cdr.apply1(obj)));
            } else
            {
                return LList.list1(last$Mnelt);
            }
        }

        public frame56()
        {
        }
    }

    public class frame57 extends ModuleBody
    {

        Continuation abort;

        public Object lambda76recur(Object obj)
        {
            frame58 frame58_1 = new frame58();
            frame58_1.staticLink = this;
            frame58_1.lists = obj;
            if (lists.isPair(frame58_1.lists))
            {
                return call_with_values.callWithValues(frame58_1.Fn57, frame58_1.Fn58);
            } else
            {
                Object aobj[] = new Object[2];
                aobj[0] = LList.Empty;
                aobj[1] = LList.Empty;
                return misc.values(aobj);
            }
        }

        public frame57()
        {
        }
    }

    public class frame58 extends ModuleBody
    {

        final ModuleMethod lambda$Fn57 = new ModuleMethod(this, 61, null, 0);
        final ModuleMethod lambda$Fn58;
        Object lists;
        frame57 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 61)
            {
                return lambda77();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 62)
            {
                return lambda78(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda77()
        {
            return srfi1.car$PlCdr(lists);
        }

        Object lambda78(Object obj, Object obj1)
        {
            frame59 frame59_1 = new frame59();
            frame59_1.staticLink = this;
            frame59_1.list = obj;
            frame59_1.Mnlists = obj1;
            if (srfi1.isNullList(frame59_1.list) != Boolean.FALSE)
            {
                return staticLink.abort.apply2(LList.Empty, LList.Empty);
            } else
            {
                return call_with_values.callWithValues(frame59_1.Fn59, frame59_1.Fn60);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 61)
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
            if (modulemethod.selector == 62)
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

        public frame58()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 62, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:762");
            lambda$Fn58 = modulemethod;
        }
    }

    public class frame59 extends ModuleBody
    {

        final ModuleMethod lambda$Fn59 = new ModuleMethod(this, 59, null, 0);
        final ModuleMethod lambda$Fn60;
        Object list;
        Object other$Mnlists;
        frame58 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 59)
            {
                return lambda79();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 60)
            {
                return lambda80(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda79()
        {
            return srfi1.car$PlCdr(list);
        }

        Object lambda80(Object obj, Object obj1)
        {
            frame60 frame60_1 = new frame60();
            frame60_1.staticLink = this;
            frame60_1.a = obj;
            frame60_1.d = obj1;
            return call_with_values.callWithValues(frame60_1.Fn61, frame60_1.Fn62);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 59)
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
            if (modulemethod.selector == 60)
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

        public frame59()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 60, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:764");
            lambda$Fn60 = modulemethod;
        }
    }

    public class frame6 extends ModuleBody
    {

        Object elt;
        final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 7, null, 0);
        final ModuleMethod lambda$Fn8;
        Object lis;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 7)
            {
                return lambda12();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object applyN(ModuleMethod modulemethod, Object aobj[])
        {
            if (modulemethod.selector == 8)
            {
                return lambda13(aobj[0], aobj[1], aobj[2], aobj[3], aobj[4]);
            } else
            {
                return super.applyN(modulemethod, aobj);
            }
        }

        Object lambda12()
        {
            return frame5.lambda11recur(lists.cdr.apply1(lis));
        }

        Object lambda13(Object obj, Object obj1, Object obj2, Object obj3, Object obj4)
        {
            Object aobj[] = new Object[5];
            aobj[0] = lists.cons(lists.car.apply1(elt), obj);
            aobj[1] = lists.cons(lists.cadr.apply1(elt), obj1);
            aobj[2] = lists.cons(lists.caddr.apply1(elt), obj2);
            aobj[3] = lists.cons(lists.cadddr.apply1(elt), obj3);
            aobj[4] = lists.cons(lists.car.apply1(lists.cddddr.apply1(elt)), obj4);
            return misc.values(aobj);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 7)
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
            if (modulemethod.selector == 8)
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

        public frame6()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 8, null, 20485);
            modulemethod.setProperty("source-location", "srfi1.scm:654");
            lambda$Fn8 = modulemethod;
        }
    }

    public class frame60 extends ModuleBody
    {

        Object a;
        Object d;
        final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 57, null, 0);
        final ModuleMethod lambda$Fn62;
        frame59 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 57)
            {
                return lambda81();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 58)
            {
                return lambda82(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda81()
        {
            return staticLink.staticLink.staticLink.lambda76recur(staticLink.Mnlists);
        }

        Object lambda82(Object obj, Object obj1)
        {
            Object aobj[] = new Object[2];
            aobj[0] = lists.cons(a, obj);
            aobj[1] = lists.cons(d, obj1);
            return misc.values(aobj);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 57)
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
            if (modulemethod.selector == 58)
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

        public frame60()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 58, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:765");
            lambda$Fn62 = modulemethod;
        }
    }

    public class frame61 extends ModuleBody
    {

        final ModuleMethod lambda$Fn63 = new ModuleMethod(this, 63, null, 0);
        Object lists;

        static Pair lambda84(Object obj, Object obj1)
        {
            return kawa.lib.lists.cons(obj, obj1);
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 63)
            {
                return lambda83();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        Object lambda83()
        {
            return srfi1.$PcCars$PlCdrs(lists);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 63)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public frame61()
        {
        }
    }

    public class frame62 extends ModuleBody
    {

        Object cars$Mnfinal;

        public frame62()
        {
        }
    }

    public class frame63 extends ModuleBody
    {

        Continuation abort;
        frame62 staticLink;

        public Object lambda85recur(Object obj)
        {
            frame64 frame64_1 = new frame64();
            frame64_1.staticLink = this;
            frame64_1.lists = obj;
            if (lists.isPair(frame64_1.lists))
            {
                return call_with_values.callWithValues(frame64_1.Fn65, frame64_1.Fn66);
            } else
            {
                Object aobj[] = new Object[2];
                aobj[0] = LList.list1(staticLink.Mnfinal);
                aobj[1] = LList.Empty;
                return misc.values(aobj);
            }
        }

        public frame63()
        {
        }
    }

    public class frame64 extends ModuleBody
    {

        final ModuleMethod lambda$Fn65 = new ModuleMethod(this, 68, null, 0);
        final ModuleMethod lambda$Fn66;
        Object lists;
        frame63 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 68)
            {
                return lambda86();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 69)
            {
                return lambda87(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda86()
        {
            return srfi1.car$PlCdr(lists);
        }

        Object lambda87(Object obj, Object obj1)
        {
            frame65 frame65_1 = new frame65();
            frame65_1.staticLink = this;
            frame65_1.list = obj;
            frame65_1.Mnlists = obj1;
            if (srfi1.isNullList(frame65_1.list) != Boolean.FALSE)
            {
                return staticLink.abort.apply2(LList.Empty, LList.Empty);
            } else
            {
                return call_with_values.callWithValues(frame65_1.Fn67, frame65_1.Fn68);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 68)
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
            if (modulemethod.selector == 69)
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

        public frame64()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 69, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:783");
            lambda$Fn66 = modulemethod;
        }
    }

    public class frame65 extends ModuleBody
    {

        final ModuleMethod lambda$Fn67 = new ModuleMethod(this, 66, null, 0);
        final ModuleMethod lambda$Fn68;
        Object list;
        Object other$Mnlists;
        frame64 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 66)
            {
                return lambda88();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 67)
            {
                return lambda89(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda88()
        {
            return srfi1.car$PlCdr(list);
        }

        Object lambda89(Object obj, Object obj1)
        {
            frame66 frame66_1 = new frame66();
            frame66_1.staticLink = this;
            frame66_1.a = obj;
            frame66_1.d = obj1;
            return call_with_values.callWithValues(frame66_1.Fn69, frame66_1.Fn70);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 66)
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
            if (modulemethod.selector == 67)
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

        public frame65()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 67, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:785");
            lambda$Fn68 = modulemethod;
        }
    }

    public class frame66 extends ModuleBody
    {

        Object a;
        Object d;
        final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 64, null, 0);
        final ModuleMethod lambda$Fn70;
        frame65 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 64)
            {
                return lambda90();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 65)
            {
                return lambda91(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda90()
        {
            return staticLink.staticLink.staticLink.lambda85recur(staticLink.Mnlists);
        }

        Object lambda91(Object obj, Object obj1)
        {
            Object aobj[] = new Object[2];
            aobj[0] = lists.cons(a, obj);
            aobj[1] = lists.cons(d, obj1);
            return misc.values(aobj);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 64)
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
            if (modulemethod.selector == 65)
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

        public frame66()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 65, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:786");
            lambda$Fn70 = modulemethod;
        }
    }

    public class frame67 extends ModuleBody
    {

        public static Object lambda92recur(Object obj)
        {
            frame68 frame68_1 = new frame68();
            frame68_1.lists = obj;
            if (lists.isPair(frame68_1.lists))
            {
                return call_with_values.callWithValues(frame68_1.Fn71, frame68_1.Fn72);
            } else
            {
                Object aobj[] = new Object[2];
                aobj[0] = LList.Empty;
                aobj[1] = LList.Empty;
                return misc.values(aobj);
            }
        }

        public frame67()
        {
        }
    }

    public class frame68 extends ModuleBody
    {

        final ModuleMethod lambda$Fn71 = new ModuleMethod(this, 74, null, 0);
        final ModuleMethod lambda$Fn72;
        Object lists;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 74)
            {
                return lambda93();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 75)
            {
                return lambda94(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda93()
        {
            return srfi1.car$PlCdr(lists);
        }

        Object lambda94(Object obj, Object obj1)
        {
            frame69 frame69_1 = new frame69();
            frame69_1.staticLink = this;
            frame69_1.list = obj;
            frame69_1.Mnlists = obj1;
            return call_with_values.callWithValues(frame69_1.Fn73, frame69_1.Fn74);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 74)
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
            if (modulemethod.selector == 75)
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

        public frame68()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 75, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:794");
            lambda$Fn72 = modulemethod;
        }
    }

    public class frame69 extends ModuleBody
    {

        final ModuleMethod lambda$Fn73 = new ModuleMethod(this, 72, null, 0);
        final ModuleMethod lambda$Fn74;
        Object list;
        Object other$Mnlists;
        frame68 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 72)
            {
                return lambda95();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 73)
            {
                return lambda96(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda95()
        {
            return srfi1.car$PlCdr(list);
        }

        Object lambda96(Object obj, Object obj1)
        {
            frame70 frame70_1 = new frame70();
            frame70_1.staticLink = this;
            frame70_1.a = obj;
            frame70_1.d = obj1;
            return call_with_values.callWithValues(frame70_1.Fn75, frame70_1.Fn76);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 72)
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
            if (modulemethod.selector == 73)
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

        public frame69()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 73, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:795");
            lambda$Fn74 = modulemethod;
        }
    }

    public class frame7 extends ModuleBody
    {

        Procedure kons;

        public Object lambda14lp(Object obj, Object obj1)
        {
            frame8 frame8_1 = new frame8();
            frame8_1.staticLink = this;
            frame8_1.lists = obj;
            frame8_1.ans = obj1;
            return call_with_values.callWithValues(frame8_1.Fn9, frame8_1.Fn10);
        }

        public frame7()
        {
        }
    }

    public class frame70 extends ModuleBody
    {

        Object a;
        Object d;
        final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 70, null, 0);
        final ModuleMethod lambda$Fn76;
        frame69 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 70)
            {
                return lambda97();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 71)
            {
                return lambda98(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda97()
        {
            return frame67.lambda92recur(staticLink.Mnlists);
        }

        Object lambda98(Object obj, Object obj1)
        {
            Object aobj[] = new Object[2];
            aobj[0] = lists.cons(a, obj);
            aobj[1] = lists.cons(d, obj1);
            return misc.values(aobj);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 70)
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
            if (modulemethod.selector == 71)
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

        public frame70()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 71, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:796");
            lambda$Fn76 = modulemethod;
        }
    }

    public class frame71 extends ModuleBody
    {

        final ModuleMethod lambda$Fn77 = new ModuleMethod(this, 76, null, 0);
        Object lists;

        static Pair lambda100(Object obj, Object obj1)
        {
            return kawa.lib.lists.cons(obj, obj1);
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 76)
            {
                return lambda99();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        Object lambda99()
        {
            return srfi1.$PcCars$PlCdrs$SlNoTest(lists);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 76)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public frame71()
        {
        }
    }

    public class frame72 extends ModuleBody
    {

        Object $Eq;
        final ModuleMethod lambda$Fn79;
        Object lis2;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 77)
            {
                return lambda101(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda101(Object obj)
        {
            Object obj1 = lis2;
            Object obj2 = $Eq;
            Procedure procedure;
            try
            {
                procedure = (Procedure)obj2;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "member", 3, obj2);
            }
            return lists.member(obj, obj1, procedure);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 77)
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

        public frame72()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 77, null, 4097);
            modulemethod.setProperty("source-location", "srfi1.scm:1443");
            lambda$Fn79 = modulemethod;
        }
    }

    public class frame8 extends ModuleBody
    {

        Object ans;
        final ModuleMethod lambda$Fn10;
        final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 9, null, 0);
        Object lists;
        frame7 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 9)
            {
                return lambda15();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 10)
            {
                return lambda16(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda15()
        {
            return srfi1.$PcCars$PlCdrs$Pl(lists, ans);
        }

        Object lambda16(Object obj, Object obj1)
        {
            if (kawa.lib.lists.isNull(obj))
            {
                return ans;
            } else
            {
                return staticLink.lambda14lp(obj1, Scheme.apply.apply2(staticLink.kons, obj));
            }
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

        public frame8()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 10, null, 8194);
            modulemethod.setProperty("source-location", "srfi1.scm:859");
            lambda$Fn10 = modulemethod;
        }
    }

    public class frame9 extends ModuleBody
    {

        Object knil;
        Procedure kons;

        public Object lambda17recur(Object obj)
        {
            Object obj1 = srfi1.$PcCdrs(obj);
            if (lists.isNull(obj1))
            {
                return knil;
            } else
            {
                return Scheme.apply.apply2(kons, srfi1.$PcCars$Pl(obj, lambda17recur(obj1)));
            }
        }

        public Object lambda18recur(Object obj)
        {
            if (srfi1.isNullList(obj) != Boolean.FALSE)
            {
                return knil;
            } else
            {
                Object obj1 = lists.car.apply1(obj);
                return kons.apply2(obj1, lambda18recur(lists.cdr.apply1(obj)));
            }
        }

        public frame9()
        {
        }
    }


    public static final Macro $Pcevery;
    public static final int $Pcprovide$Pclist$Mnlib = 123;
    public static final int $Pcprovide$Pcsrfi$Mn1 = 123;
    public static final srfi1 $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final IntNum Lit1 = IntNum.make(1);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit100;
    static final SimpleSymbol Lit101;
    static final SimpleSymbol Lit102;
    static final SimpleSymbol Lit103;
    static final SimpleSymbol Lit104;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2 = (SimpleSymbol)(new SimpleSymbol("tmp")).readResolve();
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
    static final SimpleSymbol Lit3;
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
    static final SimpleSymbol Lit71;
    static final SimpleSymbol Lit72;
    static final SimpleSymbol Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75;
    static final SimpleSymbol Lit76;
    static final SimpleSymbol Lit77;
    static final SimpleSymbol Lit78;
    static final SimpleSymbol Lit79;
    static final SimpleSymbol Lit8;
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
    static final SimpleSymbol Lit90;
    static final SimpleSymbol Lit91;
    static final SimpleSymbol Lit92;
    static final SimpleSymbol Lit93;
    static final SimpleSymbol Lit94;
    static final SimpleSymbol Lit95;
    static final SimpleSymbol Lit96;
    static final SimpleSymbol Lit97;
    static final SimpleSymbol Lit98;
    static final SimpleSymbol Lit99;
    public static final ModuleMethod alist$Mncons;
    public static final ModuleMethod alist$Mncopy;
    public static final ModuleMethod alist$Mndelete;
    public static final ModuleMethod alist$Mndelete$Ex;
    public static final ModuleMethod any;
    public static final ModuleMethod append$Ex;
    public static final ModuleMethod append$Mnmap;
    public static final ModuleMethod append$Mnmap$Ex;
    public static final ModuleMethod append$Mnreverse;
    public static final ModuleMethod append$Mnreverse$Ex;
    public static final ModuleMethod _fldbreak;
    public static final ModuleMethod break$Ex;
    public static final ModuleMethod car$Plcdr;
    public static final ModuleMethod circular$Mnlist;
    public static final ModuleMethod circular$Mnlist$Qu;
    public static final ModuleMethod concatenate;
    public static final ModuleMethod concatenate$Ex;
    public static final ModuleMethod cons$St;
    public static final ModuleMethod count;
    public static final ModuleMethod delete;
    public static final ModuleMethod delete$Ex;
    public static final ModuleMethod delete$Mnduplicates;
    public static final ModuleMethod delete$Mnduplicates$Ex;
    public static final ModuleMethod dotted$Mnlist$Qu;
    public static final ModuleMethod drop;
    public static final ModuleMethod drop$Mnright;
    public static final ModuleMethod drop$Mnright$Ex;
    public static final ModuleMethod drop$Mnwhile;
    public static final ModuleMethod eighth;
    public static final ModuleMethod every;
    public static final ModuleMethod fifth;
    public static final ModuleMethod filter;
    public static final ModuleMethod filter$Ex;
    public static final ModuleMethod filter$Mnmap;
    public static final ModuleMethod find;
    public static final ModuleMethod find$Mntail;
    public static GenericProc first;
    public static final ModuleMethod fold;
    public static final ModuleMethod fold$Mnright;
    public static GenericProc fourth;
    public static final ModuleMethod iota;
    static final ModuleMethod lambda$Fn64;
    static final ModuleMethod lambda$Fn78;
    public static final ModuleMethod last;
    public static final ModuleMethod last$Mnpair;
    public static final ModuleMethod length$Pl;
    public static final ModuleMethod list$Eq;
    public static final ModuleMethod list$Mncopy;
    public static final ModuleMethod list$Mnindex;
    public static final ModuleMethod list$Mntabulate;
    public static final ModuleMethod lset$Eq;
    public static final ModuleMethod lset$Ls$Eq;
    public static final ModuleMethod lset$Mnadjoin;
    public static final ModuleMethod lset$Mndiff$Plintersection;
    public static final ModuleMethod lset$Mndiff$Plintersection$Ex;
    public static final ModuleMethod lset$Mndifference;
    public static final ModuleMethod lset$Mndifference$Ex;
    public static final ModuleMethod lset$Mnintersection;
    public static final ModuleMethod lset$Mnintersection$Ex;
    public static final ModuleMethod lset$Mnunion;
    public static final ModuleMethod lset$Mnunion$Ex;
    public static final ModuleMethod lset$Mnxor;
    public static final ModuleMethod lset$Mnxor$Ex;
    public static final ModuleMethod make$Mnlist;
    public static final ModuleMethod map$Ex;
    public static Map map$Mnin$Mnorder;
    public static final ModuleMethod ninth;
    public static final ModuleMethod not$Mnpair$Qu;
    public static final ModuleMethod null$Mnlist$Qu;
    public static final ModuleMethod pair$Mnfold;
    public static final ModuleMethod pair$Mnfold$Mnright;
    public static final ModuleMethod pair$Mnfor$Mneach;
    public static final ModuleMethod partition;
    public static final ModuleMethod partition$Ex;
    public static final ModuleMethod proper$Mnlist$Qu;
    public static final ModuleMethod reduce;
    public static final ModuleMethod reduce$Mnright;
    public static final ModuleMethod remove;
    public static final ModuleMethod remove$Ex;
    public static GenericProc second;
    public static final ModuleMethod seventh;
    public static final ModuleMethod sixth;
    public static final ModuleMethod span;
    public static final ModuleMethod span$Ex;
    public static final ModuleMethod split$Mnat;
    public static final ModuleMethod split$Mnat$Ex;
    public static final ModuleMethod take;
    public static final ModuleMethod take$Ex;
    public static final ModuleMethod take$Mnright;
    public static final ModuleMethod take$Mnwhile;
    public static final ModuleMethod take$Mnwhile$Ex;
    public static final ModuleMethod tenth;
    public static GenericProc third;
    public static final ModuleMethod unfold;
    public static final ModuleMethod unfold$Mnright;
    public static final ModuleMethod unzip1;
    public static final ModuleMethod unzip2;
    public static final ModuleMethod unzip3;
    public static final ModuleMethod unzip4;
    public static final ModuleMethod unzip5;
    public static final ModuleMethod xcons;
    public static final ModuleMethod zip;

    static Object $PcCars$Pl(Object obj, Object obj1)
    {
        frame56 frame56_1 = new frame56();
        frame56_1.Mnelt = obj1;
        return frame56_1.lambda75recur(obj);
    }

    static Object $PcCars$PlCdrs(Object obj)
    {
        Continuation continuation = new Continuation(CallContext.getInstance());
        Object obj1;
        frame57 frame57_1 = new frame57();
        frame57_1.abort = continuation;
        obj1 = frame57_1.lambda76recur(obj);
        continuation.invoked = true;
        return obj1;
        Exception exception;
        exception;
        return Continuation.handleException(exception, continuation);
    }

    static Object $PcCars$PlCdrs$Pl(Object obj, Object obj1)
    {
        frame62 frame62_1;
        Continuation continuation;
        frame62_1 = new frame62();
        frame62_1.Mnfinal = obj1;
        continuation = new Continuation(CallContext.getInstance());
        Object obj2;
        frame63 frame63_1 = new frame63();
        frame63_1.staticLink = frame62_1;
        frame63_1.abort = continuation;
        obj2 = frame63_1.lambda85recur(obj);
        continuation.invoked = true;
        return obj2;
        Exception exception;
        exception;
        return Continuation.handleException(exception, continuation);
    }

    static Object $PcCars$PlCdrs$SlNoTest(Object obj)
    {
        new frame67();
        return frame67.lambda92recur(obj);
    }

    static Object $PcCars$PlCdrs$SlNoTest$SlPair(Object obj)
    {
        frame71 frame71_1 = new frame71();
        frame71_1.lists = obj;
        return call_with_values.callWithValues(frame71_1.Fn77, lambda$Fn78);
    }

    static Object $PcCars$PlCdrs$SlPair(Object obj)
    {
        frame61 frame61_1 = new frame61();
        frame61_1.lists = obj;
        return call_with_values.callWithValues(frame61_1.Fn63, lambda$Fn64);
    }

    static Object $PcCdrs(Object obj)
    {
        Continuation continuation = new Continuation(CallContext.getInstance());
        Object obj1;
        frame55 frame55_1 = new frame55();
        frame55_1.abort = continuation;
        obj1 = frame55_1.lambda74recur(obj);
        continuation.invoked = true;
        return obj1;
        Exception exception;
        exception;
        return Continuation.handleException(exception, continuation);
    }

    static Object $PcLset2$Ls$Eq(Object obj, Object obj1, Object obj2)
    {
        frame72 frame72_1 = new frame72();
        frame72_1.Eq = obj;
        frame72_1.lis2 = obj2;
        return every$V(frame72_1.Fn79, obj1, new Object[0]);
    }

    public srfi1()
    {
        ModuleInfo.register(this);
    }

    public static Pair alistCons(Object obj, Object obj1, Object obj2)
    {
        return lists.cons(lists.cons(obj, obj1), obj2);
    }

    public static LList alistCopy(Object obj)
    {
        Object obj1 = LList.Empty;
        Object obj2 = obj;
        do
        {
            if (obj2 == LList.Empty)
            {
                return LList.reverseInPlace(obj1);
            }
            Pair pair;
            Object obj3;
            Object obj4;
            try
            {
                pair = (Pair)obj2;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "arg0", -2, obj2);
            }
            obj3 = pair.getCdr();
            obj4 = pair.getCar();
            obj1 = Pair.make(lists.cons(lists.car.apply1(obj4), lists.cdr.apply1(obj4)), obj1);
            obj2 = obj3;
        } while (true);
    }

    public static Object alistDelete(Object obj, Object obj1)
    {
        return alistDelete(obj, obj1, Scheme.isEqual);
    }

    public static Object alistDelete(Object obj, Object obj1, Object obj2)
    {
        frame21 frame21_1 = new frame21();
        frame21_1.key = obj;
        frame21_1.Eq = obj2;
        return filter(frame21_1.Fn18, obj1);
    }

    public static Object alistDelete$Ex(Object obj, Object obj1)
    {
        return alistDelete$Ex(obj, obj1, Scheme.isEqual);
    }

    public static Object alistDelete$Ex(Object obj, Object obj1, Object obj2)
    {
        frame22 frame22_1 = new frame22();
        frame22_1.key = obj;
        frame22_1.Eq = obj2;
        return filter$Ex(frame22_1.Fn19, obj1);
    }

    public static Object any$V(Procedure procedure, Object obj, Object aobj[])
    {
        frame26 frame26_1;
        frame26_1 = new frame26();
        frame26_1.pred = procedure;
        frame26_1.lis1 = obj;
        frame26_1.lists = LList.makeList(aobj, 0);
        if (!lists.isPair(frame26_1.lists)) goto _L2; else goto _L1
_L1:
        Object obj4 = call_with_values.callWithValues(frame26_1.Fn22, frame26_1.Fn23);
_L4:
        return obj4;
_L2:
label0:
        {
            Object obj1 = isNullList(frame26_1.lis1);
            Boolean boolean1;
            int i;
            int j;
            Object obj2;
            Object obj3;
            try
            {
                boolean1 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj1);
            }
            i = 0;
            if (obj1 != boolean1)
            {
                i = 1;
            }
            j = 1 & i + 1;
            if (j == 0)
            {
                break label0;
            }
            obj2 = lists.car.apply1(frame26_1.lis1);
            obj3 = lists.cdr.apply1(frame26_1.lis1);
            do
            {
                if (isNullList(obj3) != Boolean.FALSE)
                {
                    return frame26_1.pred.apply1(obj2);
                }
                obj4 = frame26_1.pred.apply1(obj2);
                if (obj4 != Boolean.FALSE)
                {
                    break;
                }
                obj2 = lists.car.apply1(obj3);
                obj3 = lists.cdr.apply1(obj3);
            } while (true);
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (j != 0)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static Object append$Ex$V(Object aobj[])
    {
        Object obj4;
        Object obj = LList.makeList(aobj, 0);
        Object obj1 = LList.Empty;
        Object obj2;
        Object obj3;
        do
        {
            if (!lists.isPair(obj))
            {
                return obj1;
            }
            obj2 = lists.car.apply1(obj);
            obj3 = lists.cdr.apply1(obj);
            if (lists.isPair(obj2))
            {
                break;
            }
            obj1 = obj2;
            obj = obj3;
        } while (true);
        Pair pair;
        Pair pair1;
        Pair pair2;
        try
        {
            pair = (Pair)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "last-pair", 0, obj2);
        }
        obj4 = lastPair(pair);
_L3:
        if (!lists.isPair(obj3)) goto _L2; else goto _L1
_L1:
        Object obj5 = lists.car.apply1(obj3);
        obj3 = lists.cdr.apply1(obj3);
        try
        {
            pair1 = (Pair)obj4;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "set-cdr!", 1, obj4);
        }
        lists.setCdr$Ex(pair1, obj5);
        if (lists.isPair(obj5))
        {
            try
            {
                pair2 = (Pair)obj5;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "last-pair", 0, obj5);
            }
            obj4 = lastPair(pair2);
        }
        if (true) goto _L3; else goto _L2
_L2:
        return obj2;
    }

    public static Object appendMap$Ex$V(Object obj, Object obj1, Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        if (lists.isPair(llist))
        {
            return Scheme.apply.apply2(append$Ex, Scheme.apply.apply4(Scheme.map, obj, obj1, llist));
        }
        gnu.kawa.functions.Apply apply = Scheme.apply;
        ModuleMethod modulemethod = append$Ex;
        Object obj2 = LList.Empty;
        Object obj3 = obj1;
        do
        {
            if (obj3 == LList.Empty)
            {
                return apply.apply2(modulemethod, LList.reverseInPlace(obj2));
            }
            Pair pair;
            Object obj4;
            try
            {
                pair = (Pair)obj3;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "arg0", -2, obj3);
            }
            obj4 = pair.getCdr();
            obj2 = Pair.make(Scheme.applyToArgs.apply2(obj, pair.getCar()), obj2);
            obj3 = obj4;
        } while (true);
    }

    public static Object appendMap$V(Object obj, Object obj1, Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        if (lists.isPair(llist))
        {
            return Scheme.apply.apply2(append.append, Scheme.apply.apply4(Scheme.map, obj, obj1, llist));
        }
        gnu.kawa.functions.Apply apply = Scheme.apply;
        append append1 = append.append;
        Object obj2 = LList.Empty;
        Object obj3 = obj1;
        do
        {
            if (obj3 == LList.Empty)
            {
                return apply.apply2(append1, LList.reverseInPlace(obj2));
            }
            Pair pair;
            Object obj4;
            try
            {
                pair = (Pair)obj3;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "arg0", -2, obj3);
            }
            obj4 = pair.getCdr();
            obj2 = Pair.make(Scheme.applyToArgs.apply2(obj, pair.getCar()), obj2);
            obj3 = obj4;
        } while (true);
    }

    public static Object appendReverse(Object obj, Object obj1)
    {
        do
        {
            if (isNullList(obj) != Boolean.FALSE)
            {
                return obj1;
            }
            Object obj2 = lists.cdr.apply1(obj);
            obj1 = lists.cons(lists.car.apply1(obj), obj1);
            obj = obj2;
        } while (true);
    }

    public static Object appendReverse$Ex(Object obj, Object obj1)
    {
        do
        {
            if (isNullList(obj) != Boolean.FALSE)
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

    public static Object _mthbreak(Object obj, Object obj1)
    {
        frame24 frame24_1 = new frame24();
        frame24_1.pred = obj;
        return span(frame24_1.Fn20, obj1);
    }

    public static Object break$Ex(Object obj, Object obj1)
    {
        frame25 frame25_1 = new frame25();
        frame25_1.pred = obj;
        return span$Ex(frame25_1.Fn21, obj1);
    }

    public static Object car$PlCdr(Object obj)
    {
        Object aobj[] = new Object[2];
        aobj[0] = lists.car.apply1(obj);
        aobj[1] = lists.cdr.apply1(obj);
        return misc.values(aobj);
    }

    public static Pair circularList$V(Object obj, Object aobj[])
    {
        Pair pair = lists.cons(obj, LList.makeList(aobj, 0));
        Object obj1 = lastPair(pair);
        Pair pair1;
        try
        {
            pair1 = (Pair)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "set-cdr!", 1, obj1);
        }
        lists.setCdr$Ex(pair1, pair);
        return pair;
    }

    public static Object concatenate(Object obj)
    {
        return reduceRight(append.append, LList.Empty, obj);
    }

    public static Object concatenate$Ex(Object obj)
    {
        return reduceRight(append$Ex, LList.Empty, obj);
    }

    public static transient Object cons$St(Object aobj[])
    {
        return LList.consX(aobj);
    }

    public static Object count$V(Procedure procedure, Object obj, Object aobj[])
    {
        Object obj1;
        Object obj2;
        obj1 = LList.makeList(aobj, 0);
        if (!lists.isPair(obj1))
        {
            break MISSING_BLOCK_LABEL_118;
        }
        obj2 = Lit0;
_L4:
        if (isNullList(obj) == Boolean.FALSE) goto _L2; else goto _L1
_L1:
        return obj2;
_L2:
        Object obj6;
        Object obj7;
        Object obj5 = $PcCars$PlCdrs$SlPair(obj1);
        obj6 = lists.car.apply1(obj5);
        obj7 = lists.cdr.apply1(obj5);
        if (lists.isNull(obj6)) goto _L1; else goto _L3
_L3:
        Object obj8 = lists.cdr.apply1(obj);
        if (Scheme.apply.apply3(procedure, lists.car.apply1(obj), obj6) != Boolean.FALSE)
        {
            obj2 = AddOp.$Pl.apply2(obj2, Lit1);
        }
        obj1 = obj7;
        obj = obj8;
          goto _L4
        obj2 = Lit0;
        Object obj3 = obj;
        while (isNullList(obj3) == Boolean.FALSE) 
        {
            Object obj4 = lists.cdr.apply1(obj3);
            if (procedure.apply1(lists.car.apply1(obj3)) != Boolean.FALSE)
            {
                obj2 = AddOp.$Pl.apply2(obj2, Lit1);
            }
            obj3 = obj4;
        }
          goto _L1
    }

    public static Object delete(Object obj, Object obj1)
    {
        return delete(obj, obj1, Scheme.isEqual);
    }

    public static Object delete(Object obj, Object obj1, Object obj2)
    {
        frame17 frame17_1 = new frame17();
        frame17_1.x = obj;
        frame17_1.Eq = obj2;
        return filter(frame17_1.Fn16, obj1);
    }

    public static Object delete$Ex(Object obj, Object obj1)
    {
        return delete$Ex(obj, obj1, Scheme.isEqual);
    }

    public static Object delete$Ex(Object obj, Object obj1, Object obj2)
    {
        frame18 frame18_1 = new frame18();
        frame18_1.x = obj;
        frame18_1.Eq = obj2;
        return filter$Ex(frame18_1.Fn17, obj1);
    }

    public static Object deleteDuplicates(Object obj)
    {
        return deleteDuplicates(obj, ((Procedure) (Scheme.isEqual)));
    }

    public static Object deleteDuplicates(Object obj, Procedure procedure)
    {
        frame19 frame19_1 = new frame19();
        frame19_1.Eq = procedure;
        return frame19_1.lambda30recur(obj);
    }

    public static Object deleteDuplicates$Ex(Object obj)
    {
        return deleteDuplicates$Ex(obj, ((Procedure) (Scheme.isEqual)));
    }

    public static Object deleteDuplicates$Ex(Object obj, Procedure procedure)
    {
        frame20 frame20_1 = new frame20();
        frame20_1.Eq = procedure;
        return frame20_1.lambda31recur(obj);
    }

    public static Object drop(Object obj, IntNum intnum)
    {
        do
        {
            Number number;
            try
            {
                number = (Number)intnum;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "zero?", 1, intnum);
            }
            if (numbers.isZero(number))
            {
                return obj;
            }
            obj = lists.cdr.apply1(obj);
            intnum = ((IntNum) (AddOp.$Mn.apply2(intnum, Lit1)));
        } while (true);
    }

    public static Object dropRight(Object obj, IntNum intnum)
    {
        return lambda1recur(obj, drop(obj, intnum));
    }

    public static Object dropRight$Ex(Object obj, IntNum intnum)
    {
        Object obj1 = drop(obj, intnum);
        if (lists.isPair(obj1))
        {
            Object obj2 = lists.cdr.apply1(obj1);
            Object obj3 = obj;
            for (; lists.isPair(obj2); obj2 = lists.cdr.apply1(obj2))
            {
                obj3 = lists.cdr.apply1(obj3);
            }

            Pair pair;
            try
            {
                pair = (Pair)obj3;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "set-cdr!", 1, obj3);
            }
            lists.setCdr$Ex(pair, LList.Empty);
            return obj;
        } else
        {
            return LList.Empty;
        }
    }

    public static Object dropWhile(Procedure procedure, Object obj)
    {
_L6:
        if (isNullList(obj) == Boolean.FALSE) goto _L2; else goto _L1
_L1:
        obj = LList.Empty;
_L4:
        return obj;
_L2:
        if (procedure.apply1(lists.car.apply1(obj)) == Boolean.FALSE) goto _L4; else goto _L3
_L3:
        obj = lists.cdr.apply1(obj);
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static Object eighth(Object obj)
    {
        return lists.cadddr.apply1(lists.cddddr.apply1(obj));
    }

    public static Object every$V(Procedure procedure, Object obj, Object aobj[])
    {
        frame27 frame27_1;
        frame27_1 = new frame27();
        frame27_1.pred = procedure;
        frame27_1.lis1 = obj;
        frame27_1.lists = LList.makeList(aobj, 0);
        if (!lists.isPair(frame27_1.lists)) goto _L2; else goto _L1
_L1:
        Object obj1 = call_with_values.callWithValues(frame27_1.Fn24, frame27_1.Fn25);
_L4:
        return obj1;
_L2:
        obj1 = isNullList(frame27_1.lis1);
        if (obj1 != Boolean.FALSE)
        {
            continue;
        }
        Object obj2 = lists.car.apply1(frame27_1.lis1);
        Object obj3 = lists.cdr.apply1(frame27_1.lis1);
        do
        {
            if (isNullList(obj3) != Boolean.FALSE)
            {
                return frame27_1.pred.apply1(obj2);
            }
            obj1 = frame27_1.pred.apply1(obj2);
            if (obj1 == Boolean.FALSE)
            {
                continue;
            }
            obj2 = lists.car.apply1(obj3);
            obj3 = lists.cdr.apply1(obj3);
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static Object fifth(Object obj)
    {
        return lists.car.apply1(lists.cddddr.apply1(obj));
    }

    public static Object filter(Procedure procedure, Object obj)
    {
        Object obj1 = LList.Empty;
        do
        {
            if (isNullList(obj) != Boolean.FALSE)
            {
                Object obj2;
                Object obj3;
                LList llist;
                try
                {
                    llist = (LList)obj1;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "reverse!", 1, obj1);
                }
                return lists.reverse$Ex(llist);
            }
            obj2 = lists.car.apply1(obj);
            obj3 = lists.cdr.apply1(obj);
            if (procedure.apply1(obj2) != Boolean.FALSE)
            {
                obj1 = lists.cons(obj2, obj1);
                obj = obj3;
            } else
            {
                obj = obj3;
            }
        } while (true);
    }

    public static Object filter$Ex(Procedure procedure, Object obj)
    {
        Object obj1 = obj;
_L6:
        if (isNullList(obj1) == Boolean.FALSE) goto _L2; else goto _L1
_L1:
        return obj1;
_L2:
        Object obj2;
        Object obj3;
        if (procedure.apply1(lists.car.apply1(obj1)) == Boolean.FALSE)
        {
            obj1 = lists.cdr.apply1(obj1);
            continue; /* Loop/switch isn't completed */
        }
        obj2 = lists.cdr.apply1(obj1);
        obj3 = obj1;
_L3:
        while (lists.isPair(obj2)) 
        {
label0:
            {
                if (procedure.apply1(lists.car.apply1(obj2)) == Boolean.FALSE)
                {
                    break label0;
                }
                Object obj6 = lists.cdr.apply1(obj2);
                obj3 = obj2;
                obj2 = obj6;
            }
        }
          goto _L1
        Object obj4 = lists.cdr.apply1(obj2);
_L4:
label1:
        {
            if (!lists.isPair(obj4))
            {
                break MISSING_BLOCK_LABEL_177;
            }
            if (procedure.apply1(lists.car.apply1(obj4)) == Boolean.FALSE)
            {
                break label1;
            }
            Pair pair;
            Pair pair1;
            Object obj5;
            try
            {
                pair1 = (Pair)obj3;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "set-cdr!", 1, obj3);
            }
            lists.setCdr$Ex(pair1, obj4);
            obj5 = lists.cdr.apply1(obj4);
            obj3 = obj4;
            obj2 = obj5;
        }
          goto _L3
          goto _L1
        obj4 = lists.cdr.apply1(obj4);
          goto _L4
        pair = (Pair)obj3;
        lists.setCdr$Ex(pair, obj4);
        return obj1;
        ClassCastException classcastexception;
        classcastexception;
        throw new WrongType(classcastexception, "set-cdr!", 1, obj3);
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static Object filterMap$V(Procedure procedure, Object obj, Object aobj[])
    {
        frame13 frame13_1 = new frame13();
        frame13_1.f = procedure;
        LList llist = LList.makeList(aobj, 0);
        if (lists.isPair(llist))
        {
            return frame13_1.lambda23recur(lists.cons(obj, llist), LList.Empty);
        }
        Object obj1 = LList.Empty;
        Object obj2 = obj;
        do
        {
            if (isNullList(obj2) != Boolean.FALSE)
            {
                Object obj3;
                Object obj4;
                LList llist1;
                try
                {
                    llist1 = (LList)obj1;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "reverse!", 1, obj1);
                }
                return lists.reverse$Ex(llist1);
            }
            obj3 = frame13_1.f.apply1(lists.car.apply1(obj2));
            obj4 = lists.cdr.apply1(obj2);
            if (obj3 != Boolean.FALSE)
            {
                obj1 = lists.cons(obj3, obj1);
                obj2 = obj4;
            } else
            {
                obj2 = obj4;
            }
        } while (true);
    }

    public static Object find(Object obj, Object obj1)
    {
        Procedure procedure;
        Object obj2;
        try
        {
            procedure = (Procedure)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "find-tail", 0, obj);
        }
        obj2 = findTail(procedure, obj1);
        if (obj2 != Boolean.FALSE)
        {
            return lists.car.apply1(obj2);
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static Object findTail(Procedure procedure, Object obj)
    {
        do
        {
            Object obj1 = isNullList(obj);
            Boolean boolean1;
            int i;
            int j;
            try
            {
                boolean1 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj1);
            }
            if (obj1 != boolean1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            j = 1 & i + 1;
            if (j != 0)
            {
                if (procedure.apply1(lists.car.apply1(obj)) != Boolean.FALSE)
                {
                    return obj;
                }
                obj = lists.cdr.apply1(obj);
            } else
            if (j != 0)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        } while (true);
    }

    public static Object fold$V(Procedure procedure, Object obj, Object obj1, Object aobj[])
    {
        frame7 frame7_1 = new frame7();
        frame7_1.kons = procedure;
        LList llist = LList.makeList(aobj, 0);
        if (lists.isPair(llist))
        {
            obj = frame7_1.lambda14lp(lists.cons(obj1, llist), obj);
        } else
        {
            Object obj2 = obj1;
            while (isNullList(obj2) == Boolean.FALSE) 
            {
                Object obj3 = lists.cdr.apply1(obj2);
                obj = frame7_1.kons.apply2(lists.car.apply1(obj2), obj);
                obj2 = obj3;
            }
        }
        return obj;
    }

    public static Object foldRight$V(Procedure procedure, Object obj, Object obj1, Object aobj[])
    {
        frame9 frame9_1 = new frame9();
        frame9_1.kons = procedure;
        frame9_1.knil = obj;
        LList llist = LList.makeList(aobj, 0);
        if (lists.isPair(llist))
        {
            return frame9_1.lambda17recur(lists.cons(obj1, llist));
        } else
        {
            return frame9_1.lambda18recur(obj1);
        }
    }

    public static Object iota(IntNum intnum)
    {
        return iota(intnum, ((Numeric) (Lit0)), ((Numeric) (Lit1)));
    }

    public static Object iota(IntNum intnum, Numeric numeric)
    {
        return iota(intnum, numeric, ((Numeric) (Lit1)));
    }

    public static Object iota(IntNum intnum, Numeric numeric, Numeric numeric1)
    {
        if (IntNum.compare(intnum, 0L) < 0)
        {
            Object aobj[] = new Object[2];
            aobj[0] = iota;
            aobj[1] = intnum;
            misc.error$V("Negative step count", aobj);
        }
        Object obj = AddOp.$Pl.apply2(numeric, MultiplyOp.$St.apply2(IntNum.add(intnum, -1), numeric1));
        Object obj1;
        Object obj2;
        try
        {
            obj1 = (Numeric)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "last-val", -2, obj);
        }
        obj2 = LList.Empty;
        while (Scheme.numLEq.apply2(intnum, Lit0) == Boolean.FALSE) 
        {
            intnum = ((IntNum) (AddOp.$Mn.apply2(intnum, Lit1)));
            Object obj3 = AddOp.$Mn.apply2(obj1, numeric1);
            obj2 = lists.cons(obj1, obj2);
            obj1 = obj3;
        }
        return obj2;
    }

    public static Object isCircularList(Object obj)
    {
        Object obj1 = obj;
        do
        {
            boolean flag = lists.isPair(obj);
            if (flag)
            {
                Object obj2 = lists.cdr.apply1(obj);
                boolean flag1 = lists.isPair(obj2);
                if (flag1)
                {
                    obj = lists.cdr.apply1(obj2);
                    obj1 = lists.cdr.apply1(obj1);
                    boolean flag2;
                    if (obj == obj1)
                    {
                        flag2 = true;
                    } else
                    {
                        flag2 = false;
                    }
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
                } else
                if (flag1)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
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

    public static Object isDottedList(Object obj)
    {
        Object obj1 = obj;
        while (lists.isPair(obj)) 
        {
            Object obj2 = lists.cdr.apply1(obj);
            if (lists.isPair(obj2))
            {
                obj = lists.cdr.apply1(obj2);
                obj1 = lists.cdr.apply1(obj1);
                int i;
                int j;
                if (obj == obj1)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                j = 1 & i + 1;
                if (j == 0)
                {
                    if (j != 0)
                    {
                        return Boolean.TRUE;
                    } else
                    {
                        return Boolean.FALSE;
                    }
                }
            } else
            if (lists.isNull(obj2))
            {
                return Boolean.FALSE;
            } else
            {
                return Boolean.TRUE;
            }
        }
        if (lists.isNull(obj))
        {
            return Boolean.FALSE;
        } else
        {
            return Boolean.TRUE;
        }
    }

    public static boolean isNotPair(Object obj)
    {
        return 1 & 1 + lists.isPair(obj);
    }

    public static Object isNullList(Object obj)
    {
        if (obj instanceof Pair)
        {
            return Boolean.FALSE;
        }
        if (obj == LList.Empty)
        {
            return Boolean.TRUE;
        } else
        {
            return misc.error$V("null-list?: argument out of domain", new Object[] {
                obj
            });
        }
    }

    public static Object isProperList(Object obj)
    {
        Object obj1 = obj;
        while (lists.isPair(obj)) 
        {
            Object obj2 = lists.cdr.apply1(obj);
            if (lists.isPair(obj2))
            {
                obj = lists.cdr.apply1(obj2);
                obj1 = lists.cdr.apply1(obj1);
                int i;
                int j;
                if (obj == obj1)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                j = 1 & i + 1;
                if (j == 0)
                {
                    if (j != 0)
                    {
                        return Boolean.TRUE;
                    } else
                    {
                        return Boolean.FALSE;
                    }
                }
            } else
            if (lists.isNull(obj2))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        if (lists.isNull(obj))
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static Object lambda1recur(Object obj, Object obj1)
    {
        if (lists.isPair(obj1))
        {
            return lists.cons(lists.car.apply1(obj), lambda1recur(lists.cdr.apply1(obj), lists.cdr.apply1(obj1)));
        } else
        {
            return LList.Empty;
        }
    }

    public static Object last(Object obj)
    {
        GenericProc genericproc = lists.car;
        Pair pair;
        try
        {
            pair = (Pair)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "last-pair", 0, obj);
        }
        return genericproc.apply1(lastPair(pair));
    }

    public static Object lastPair(Pair pair)
    {
        do
        {
            Object obj = lists.cdr.apply1(pair);
            if (lists.isPair(obj))
            {
                pair = ((Pair) (obj));
            } else
            {
                return pair;
            }
        } while (true);
    }

    public static Object length$Pl(Object obj)
    {
        Object obj1 = Lit0;
        Object obj2 = obj;
        while (lists.isPair(obj)) 
        {
            Object obj3 = lists.cdr.apply1(obj);
            Object obj4 = AddOp.$Pl.apply2(obj1, Lit1);
            if (lists.isPair(obj3))
            {
                obj = lists.cdr.apply1(obj3);
                obj2 = lists.cdr.apply1(obj2);
                obj1 = AddOp.$Pl.apply2(obj4, Lit1);
                int i;
                int j;
                if (obj == obj2)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                j = 1 & i + 1;
                if (j == 0)
                {
                    if (j != 0)
                    {
                        return Boolean.TRUE;
                    } else
                    {
                        return Boolean.FALSE;
                    }
                }
            } else
            {
                return obj4;
            }
        }
        return obj1;
    }

    public static Object list$Eq$V(Object obj, Object aobj[])
    {
        Object obj1;
        Object obj2;
        LList llist = LList.makeList(aobj, 0);
        boolean flag = lists.isNull(llist);
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
        obj1 = lists.car.apply1(llist);
        obj2 = lists.cdr.apply1(llist);
_L4:
        Object obj4;
        boolean flag1 = lists.isNull(obj2);
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
        Object obj3 = lists.car.apply1(obj2);
        obj2 = lists.cdr.apply1(obj2);
        if (obj1 == obj3)
        {
            obj1 = obj3;
            continue; /* Loop/switch isn't completed */
        }
        obj4 = obj3;
_L2:
        Object obj5;
        if (isNullList(obj1) != Boolean.FALSE)
        {
            Object obj7 = isNullList(obj4);
            if (obj7 != Boolean.FALSE)
            {
                obj1 = obj4;
            } else
            {
                return obj7;
            }
            continue; /* Loop/switch isn't completed */
        }
        obj5 = isNullList(obj4);
        Boolean boolean1 = Boolean.FALSE;
        int i;
        int j;
        if (obj5 != boolean1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        j = 1 & i + 1;
        if (j != 0)
        {
            Object obj6 = Scheme.applyToArgs.apply3(obj, lists.car.apply1(obj1), lists.car.apply1(obj4));
            if (obj6 != Boolean.FALSE)
            {
                obj1 = lists.cdr.apply1(obj1);
                obj4 = lists.cdr.apply1(obj4);
            } else
            {
                return obj6;
            }
        } else
        if (j != 0)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
        if (true) goto _L2; else goto _L1
_L1:
        ClassCastException classcastexception;
        classcastexception;
        throw new WrongType(classcastexception, "x", -2, obj5);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static LList listCopy(LList llist)
    {
        Object obj = LList.Empty;
        Object obj1 = LList.Empty;
        while (lists.isPair(llist)) 
        {
            Pair pair = lists.cons(lists.car.apply1(llist), LList.Empty);
            if (obj1 == LList.Empty)
            {
                obj = pair;
            } else
            {
                Pair pair1;
                try
                {
                    pair1 = (Pair)obj1;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "set-cdr!", 1, obj1);
                }
                lists.setCdr$Ex(pair1, pair);
            }
            obj1 = pair;
            llist = (LList)lists.cdr.apply1(llist);
        }
        return ((LList) (obj));
    }

    public static Object listIndex$V(Procedure procedure, Object obj, Object aobj[])
    {
        frame30 frame30_1;
        LList llist;
        frame30_1 = new frame30();
        frame30_1.pred = procedure;
        llist = LList.makeList(aobj, 0);
        if (!lists.isPair(llist)) goto _L2; else goto _L1
_L1:
        Object obj1 = frame30_1.lambda44lp(lists.cons(obj, llist), Lit0);
_L4:
        return obj1;
_L2:
        Object obj2;
        obj1 = Lit0;
        obj2 = obj;
_L5:
        Object obj3 = isNullList(obj2);
        Boolean boolean1;
        int i;
        int j;
        try
        {
            boolean1 = Boolean.FALSE;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "x", -2, obj3);
        }
        if (obj3 != boolean1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        j = 1 & i + 1;
        if (j == 0)
        {
            break MISSING_BLOCK_LABEL_142;
        }
        if (frame30_1.pred.apply1(lists.car.apply1(obj2)) != Boolean.FALSE) goto _L4; else goto _L3
_L3:
        obj2 = lists.cdr.apply1(obj2);
        obj1 = AddOp.$Pl.apply2(obj1, Lit1);
          goto _L5
        if (j != 0)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static Object listTabulate(Object obj, Procedure procedure)
    {
        int i = 1 & 1 + numbers.isInteger(obj);
        Object obj1;
        Object obj2;
        if (i == 0 ? Scheme.numLss.apply2(obj, Lit0) != Boolean.FALSE : i != 0)
        {
            misc.error$V("list-tabulate arg#1 must be a non-negative integer", new Object[0]);
        }
        obj1 = AddOp.$Mn.apply2(obj, Lit1);
        obj2 = LList.Empty;
        Object obj3;
        for (; Scheme.numLss.apply2(obj1, Lit0) == Boolean.FALSE; obj1 = obj3)
        {
            obj3 = AddOp.$Mn.apply2(obj1, Lit1);
            obj2 = lists.cons(procedure.apply1(obj1), obj2);
        }

        return obj2;
    }

    public static Object lset$Eq$V(Procedure procedure, Object aobj[])
    {
        Object obj;
        Object obj1;
        LList llist = LList.makeList(aobj, 0);
        int i = 1 & 1 + lists.isPair(llist);
        if (i != 0)
        {
            if (i != 0)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        obj = lists.car.apply1(llist);
        obj1 = lists.cdr.apply1(llist);
_L3:
        Object obj2;
        Object obj3;
        int j = 1 & 1 + lists.isPair(obj1);
        if (j != 0)
        {
            if (j != 0)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        obj2 = lists.car.apply1(obj1);
        obj1 = lists.cdr.apply1(obj1);
        boolean flag;
        if (obj == obj2)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        Boolean boolean1;
        if (flag)
        {
            boolean1 = Boolean.TRUE;
        } else
        {
            boolean1 = Boolean.FALSE;
        }
        obj3 = boolean1;
_L4:
        if (obj3 != Boolean.FALSE)
        {
            obj = obj2;
        } else
        {
            return obj3;
        }
        if (true) goto _L3; else goto _L2
_L2:
        obj3 = $PcLset2$Ls$Eq(procedure, obj, obj2);
        if (obj3 != Boolean.FALSE)
        {
            obj3 = $PcLset2$Ls$Eq(procedure, obj2, obj);
        }
          goto _L4
    }

    public static Object lset$Ls$Eq$V(Procedure procedure, Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        int i = 1 & 1 + lists.isPair(llist);
        if (i != 0)
        {
            if (i != 0)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        Object obj = lists.car.apply1(llist);
        Object obj1 = lists.cdr.apply1(llist);
        do
        {
            int j = 1 & 1 + lists.isPair(obj1);
            if (j != 0)
            {
                if (j != 0)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            Object obj2 = lists.car.apply1(obj1);
            obj1 = lists.cdr.apply1(obj1);
            boolean flag;
            Object obj3;
            if (obj2 == obj)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                Boolean boolean1;
                if (flag)
                {
                    boolean1 = Boolean.TRUE;
                } else
                {
                    boolean1 = Boolean.FALSE;
                }
                obj3 = boolean1;
            } else
            {
                obj3 = $PcLset2$Ls$Eq(procedure, obj, obj2);
            }
            if (obj3 != Boolean.FALSE)
            {
                obj = obj2;
            } else
            {
                return obj3;
            }
        } while (true);
    }

    public static Object lsetAdjoin$V(Procedure procedure, Object obj, Object aobj[])
    {
        frame32 frame32_1 = new frame32();
        frame32_1.Eq = procedure;
        LList llist = LList.makeList(aobj, 0);
        return fold$V(frame32_1.Fn30, obj, llist, new Object[0]);
    }

    public static Object lsetDiff$PlIntersection$Ex$V(Procedure procedure, Object obj, Object aobj[])
    {
        frame53 frame53_1 = new frame53();
        frame53_1.Eq = procedure;
        frame53_1.lists = LList.makeList(aobj, 0);
        if (every$V(null$Mnlist$Qu, frame53_1.lists, new Object[0]) != Boolean.FALSE)
        {
            Object aobj2[] = new Object[2];
            aobj2[0] = obj;
            aobj2[1] = LList.Empty;
            return misc.values(aobj2);
        }
        if (lists.memq(obj, frame53_1.lists) != Boolean.FALSE)
        {
            Object aobj1[] = new Object[2];
            aobj1[0] = LList.Empty;
            aobj1[1] = obj;
            return misc.values(aobj1);
        } else
        {
            return partition$Ex(frame53_1.Fn55, obj);
        }
    }

    public static Object lsetDiff$PlIntersection$V(Procedure procedure, Object obj, Object aobj[])
    {
        frame51 frame51_1 = new frame51();
        frame51_1.Eq = procedure;
        frame51_1.lists = LList.makeList(aobj, 0);
        if (every$V(null$Mnlist$Qu, frame51_1.lists, new Object[0]) != Boolean.FALSE)
        {
            Object aobj2[] = new Object[2];
            aobj2[0] = obj;
            aobj2[1] = LList.Empty;
            return misc.values(aobj2);
        }
        if (lists.memq(obj, frame51_1.lists) != Boolean.FALSE)
        {
            Object aobj1[] = new Object[2];
            aobj1[0] = LList.Empty;
            aobj1[1] = obj;
            return misc.values(aobj1);
        } else
        {
            return partition(frame51_1.Fn53, obj);
        }
    }

    public static Object lsetDifference$Ex$V(Procedure procedure, Object obj, Object aobj[])
    {
        frame43 frame43_1 = new frame43();
        frame43_1.Eq = procedure;
        LList llist = LList.makeList(aobj, 0);
        frame43_1.lists = filter(lists.pair$Qu, llist);
        if (lists.isNull(frame43_1.lists))
        {
            return obj;
        }
        if (lists.memq(obj, frame43_1.lists) != Boolean.FALSE)
        {
            return LList.Empty;
        } else
        {
            return filter$Ex(frame43_1.Fn43, obj);
        }
    }

    public static Object lsetDifference$V(Procedure procedure, Object obj, Object aobj[])
    {
        frame41 frame41_1 = new frame41();
        frame41_1.Eq = procedure;
        LList llist = LList.makeList(aobj, 0);
        frame41_1.lists = filter(lists.pair$Qu, llist);
        if (lists.isNull(frame41_1.lists))
        {
            return obj;
        }
        if (lists.memq(obj, frame41_1.lists) != Boolean.FALSE)
        {
            return LList.Empty;
        } else
        {
            return filter(frame41_1.Fn41, obj);
        }
    }

    public static Object lsetIntersection$Ex$V(Procedure procedure, Object obj, Object aobj[])
    {
        frame39 frame39_1 = new frame39();
        frame39_1.Eq = procedure;
        frame39_1.lists = delete(obj, LList.makeList(aobj, 0), Scheme.isEq);
        if (any$V(null$Mnlist$Qu, frame39_1.lists, new Object[0]) != Boolean.FALSE)
        {
            obj = LList.Empty;
        } else
        if (!lists.isNull(frame39_1.lists))
        {
            return filter$Ex(frame39_1.Fn39, obj);
        }
        return obj;
    }

    public static Object lsetIntersection$V(Procedure procedure, Object obj, Object aobj[])
    {
        frame37 frame37_1 = new frame37();
        frame37_1.Eq = procedure;
        frame37_1.lists = delete(obj, LList.makeList(aobj, 0), Scheme.isEq);
        if (any$V(null$Mnlist$Qu, frame37_1.lists, new Object[0]) != Boolean.FALSE)
        {
            obj = LList.Empty;
        } else
        if (!lists.isNull(frame37_1.lists))
        {
            return filter(frame37_1.Fn37, obj);
        }
        return obj;
    }

    public static Object lsetUnion$Ex$V(Procedure procedure, Object aobj[])
    {
        frame35 frame35_1 = new frame35();
        frame35_1.Eq = procedure;
        LList llist = LList.makeList(aobj, 0);
        return reduce(frame35_1.Fn34, LList.Empty, llist);
    }

    public static Object lsetUnion$V(Procedure procedure, Object aobj[])
    {
        frame33 frame33_1 = new frame33();
        frame33_1.Eq = procedure;
        LList llist = LList.makeList(aobj, 0);
        return reduce(frame33_1.Fn31, LList.Empty, llist);
    }

    public static Object lsetXor$Ex$V(Procedure procedure, Object aobj[])
    {
        frame48 frame48_1 = new frame48();
        frame48_1.Eq = procedure;
        LList llist = LList.makeList(aobj, 0);
        return reduce(frame48_1.Fn49, LList.Empty, llist);
    }

    public static Object lsetXor$V(Procedure procedure, Object aobj[])
    {
        frame45 frame45_1 = new frame45();
        frame45_1.Eq = procedure;
        LList llist = LList.makeList(aobj, 0);
        return reduce(frame45_1.Fn45, LList.Empty, llist);
    }

    public static Object makeList$V(Object obj, Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        int i = 1 & 1 + numbers.isInteger(obj);
        Object obj1;
        Object obj2;
        if (i == 0 ? Scheme.numLss.apply2(obj, Lit0) != Boolean.FALSE : i != 0)
        {
            misc.error$V("make-list arg#1 must be a non-negative integer", new Object[0]);
        }
        if (lists.isNull(llist))
        {
            obj1 = Boolean.FALSE;
        } else
        if (lists.isNull(lists.cdr.apply1(llist)))
        {
            obj1 = lists.car.apply1(llist);
        } else
        {
            Object aobj1[] = new Object[1];
            aobj1[0] = lists.cons(obj, llist);
            obj1 = misc.error$V("Too many arguments to MAKE-LIST", aobj1);
        }
        obj2 = LList.Empty;
        for (Object obj3 = obj; Scheme.numLEq.apply2(obj3, Lit0) == Boolean.FALSE;)
        {
            obj3 = AddOp.$Mn.apply2(obj3, Lit1);
            obj2 = lists.cons(obj1, obj2);
        }

        return obj2;
    }

    public static Object map$Ex$V(Procedure procedure, Object obj, Object aobj[])
    {
        frame12 frame12_1 = new frame12();
        frame12_1.f = procedure;
        Object obj1 = LList.makeList(aobj, 0);
        if (lists.isPair(obj1))
        {
            Object obj2 = obj;
            while (isNullList(obj2) == Boolean.FALSE) 
            {
                Object obj3 = $PcCars$PlCdrs$SlNoTest$SlPair(obj1);
                Object obj4 = lists.car.apply1(obj3);
                Object obj5 = lists.cdr.apply1(obj3);
                Pair pair;
                try
                {
                    pair = (Pair)obj2;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "set-car!", 1, obj2);
                }
                lists.setCar$Ex(pair, Scheme.apply.apply3(frame12_1.f, lists.car.apply1(obj2), obj4));
                obj2 = lists.cdr.apply1(obj2);
                obj1 = obj5;
            }
        } else
        {
            pairForEach$V(frame12_1.Fn11, obj, new Object[0]);
        }
        return obj;
    }

    public static Object ninth(Object obj)
    {
        return lists.car.apply1(lists.cddddr.apply1(lists.cddddr.apply1(obj)));
    }

    public static Object pairFold$V(Procedure procedure, Object obj, Object obj1, Object aobj[])
    {
        Object obj5;
        LList llist = LList.makeList(aobj, 0);
        if (!lists.isPair(llist))
        {
            break MISSING_BLOCK_LABEL_88;
        }
        obj5 = lists.cons(obj1, llist);
_L3:
        Object obj6 = $PcCdrs(obj5);
        if (!lists.isNull(obj6)) goto _L2; else goto _L1
_L1:
        return obj;
_L2:
        gnu.kawa.functions.Apply apply = Scheme.apply;
        Object aobj1[] = new Object[2];
        aobj1[0] = obj5;
        aobj1[1] = LList.list1(obj);
        Object obj7 = apply.apply2(procedure, append$Ex$V(aobj1));
        obj5 = obj6;
        obj = obj7;
          goto _L3
        Object obj2 = obj1;
        while (isNullList(obj2) == Boolean.FALSE) 
        {
            Object obj3 = lists.cdr.apply1(obj2);
            Object obj4 = procedure.apply2(obj2, obj);
            obj2 = obj3;
            obj = obj4;
        }
          goto _L1
    }

    public static Object pairFoldRight$V(Procedure procedure, Object obj, Object obj1, Object aobj[])
    {
        frame10 frame10_1 = new frame10();
        frame10_1.f = procedure;
        frame10_1.zero = obj;
        LList llist = LList.makeList(aobj, 0);
        if (lists.isPair(llist))
        {
            return frame10_1.lambda19recur(lists.cons(obj1, llist));
        } else
        {
            return frame10_1.lambda20recur(obj1);
        }
    }

    public static Object pairForEach$V(Procedure procedure, Object obj, Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        if (lists.isPair(llist))
        {
            Object obj3 = lists.cons(obj, llist);
            do
            {
                Object obj4 = $PcCdrs(obj3);
                if (lists.isPair(obj4))
                {
                    Scheme.apply.apply2(procedure, obj3);
                    obj3 = obj4;
                } else
                {
                    return Values.empty;
                }
            } while (true);
        }
        Object obj2;
        for (Object obj1 = obj; isNullList(obj1) == Boolean.FALSE; obj1 = obj2)
        {
            obj2 = lists.cdr.apply1(obj1);
            procedure.apply1(obj1);
        }

        return Values.empty;
    }

    public static Object partition(Procedure procedure, Object obj)
    {
        Object obj1 = LList.Empty;
        Object obj2 = LList.Empty;
        do
        {
            if (isNullList(obj) != Boolean.FALSE)
            {
                Object aobj[] = new Object[2];
                Object obj3;
                Object obj4;
                LList llist;
                LList llist1;
                try
                {
                    llist = (LList)obj1;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "reverse!", 1, obj1);
                }
                aobj[0] = lists.reverse$Ex(llist);
                try
                {
                    llist1 = (LList)obj2;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "reverse!", 1, obj2);
                }
                aobj[1] = lists.reverse$Ex(llist1);
                return misc.values(aobj);
            }
            obj3 = lists.car.apply1(obj);
            obj4 = lists.cdr.apply1(obj);
            if (procedure.apply1(obj3) != Boolean.FALSE)
            {
                obj1 = lists.cons(obj3, obj1);
                obj = obj4;
            } else
            {
                obj2 = lists.cons(obj3, obj2);
                obj = obj4;
            }
        } while (true);
    }

    public static Object partition$Ex(Procedure procedure, Object obj)
    {
        Pair pair = lists.cons(Lit2, LList.Empty);
        Pair pair1 = lists.cons(Lit2, LList.Empty);
        Object obj1 = pair;
        Object obj2 = pair1;
        do
        {
            if (isNotPair(obj))
            {
                Pair pair2;
                Object obj3;
                Pair pair3;
                Object obj4;
                Pair pair4;
                Pair pair5;
                Object aobj[];
                try
                {
                    pair4 = (Pair)obj1;
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "set-cdr!", 1, obj1);
                }
                lists.setCdr$Ex(pair4, LList.Empty);
                try
                {
                    pair5 = (Pair)obj2;
                }
                catch (ClassCastException classcastexception3)
                {
                    throw new WrongType(classcastexception3, "set-cdr!", 1, obj2);
                }
                lists.setCdr$Ex(pair5, LList.Empty);
                aobj = new Object[2];
                aobj[0] = lists.cdr.apply1(pair);
                aobj[1] = lists.cdr.apply1(pair1);
                return misc.values(aobj);
            }
            if (procedure.apply1(lists.car.apply1(obj)) != Boolean.FALSE)
            {
                try
                {
                    pair3 = (Pair)obj1;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "set-cdr!", 1, obj1);
                }
                lists.setCdr$Ex(pair3, obj);
                obj4 = lists.cdr.apply1(obj);
                obj1 = obj;
                obj = obj4;
            } else
            {
                try
                {
                    pair2 = (Pair)obj2;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "set-cdr!", 1, obj2);
                }
                lists.setCdr$Ex(pair2, obj);
                obj3 = lists.cdr.apply1(obj);
                obj2 = obj;
                obj = obj3;
            }
        } while (true);
    }

    public static Object reduce(Procedure procedure, Object obj, Object obj1)
    {
        if (isNullList(obj1) != Boolean.FALSE)
        {
            return obj;
        } else
        {
            return fold$V(procedure, lists.car.apply1(obj1), lists.cdr.apply1(obj1), new Object[0]);
        }
    }

    public static Object reduceRight(Procedure procedure, Object obj, Object obj1)
    {
        frame11 frame11_1 = new frame11();
        frame11_1.f = procedure;
        if (isNullList(obj1) != Boolean.FALSE)
        {
            return obj;
        } else
        {
            return frame11_1.lambda21recur(lists.car.apply1(obj1), lists.cdr.apply1(obj1));
        }
    }

    public static Object remove(Object obj, Object obj1)
    {
        frame15 frame15_1 = new frame15();
        frame15_1.pred = obj;
        return filter(frame15_1.Fn14, obj1);
    }

    public static Object remove$Ex(Object obj, Object obj1)
    {
        frame16 frame16_1 = new frame16();
        frame16_1.pred = obj;
        return filter$Ex(frame16_1.Fn15, obj1);
    }

    public static Object seventh(Object obj)
    {
        return lists.caddr.apply1(lists.cddddr.apply1(obj));
    }

    public static Object sixth(Object obj)
    {
        return lists.cadr.apply1(lists.cddddr.apply1(obj));
    }

    public static Object span(Procedure procedure, Object obj)
    {
        Object obj1 = LList.Empty;
        do
        {
            if (isNullList(obj) != Boolean.FALSE)
            {
                Object aobj1[] = new Object[2];
                Object obj2;
                Object aobj[];
                LList llist;
                LList llist1;
                try
                {
                    llist1 = (LList)obj1;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "reverse!", 1, obj1);
                }
                aobj1[0] = lists.reverse$Ex(llist1);
                aobj1[1] = obj;
                return misc.values(aobj1);
            }
            obj2 = lists.car.apply1(obj);
            if (procedure.apply1(obj2) != Boolean.FALSE)
            {
                obj = lists.cdr.apply1(obj);
                obj1 = lists.cons(obj2, obj1);
            } else
            {
                aobj = new Object[2];
                try
                {
                    llist = (LList)obj1;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "reverse!", 1, obj1);
                }
                aobj[0] = lists.reverse$Ex(llist);
                aobj[1] = obj;
                return misc.values(aobj);
            }
        } while (true);
    }

    public static Object span$Ex(Procedure procedure, Object obj)
    {
        Object obj2;
        Object obj3;
        Object obj1 = isNullList(obj);
        if (obj1 == Boolean.FALSE ? procedure.apply1(lists.car.apply1(obj)) == Boolean.FALSE : obj1 != Boolean.FALSE)
        {
            Object aobj[] = new Object[2];
            aobj[0] = LList.Empty;
            aobj[1] = obj;
            return misc.values(aobj);
        }
        obj2 = lists.cdr.apply1(obj);
        obj3 = obj;
_L6:
        if (isNullList(obj2) == Boolean.FALSE) goto _L2; else goto _L1
_L1:
        Object obj4 = obj2;
_L4:
        return misc.values(new Object[] {
            obj, obj4
        });
_L2:
        if (procedure.apply1(lists.car.apply1(obj2)) != Boolean.FALSE)
        {
            Object obj5 = lists.cdr.apply1(obj2);
            obj3 = obj2;
            obj2 = obj5;
            continue; /* Loop/switch isn't completed */
        }
        Pair pair = (Pair)obj3;
        lists.setCdr$Ex(pair, LList.Empty);
        obj4 = obj2;
        if (true) goto _L4; else goto _L3
_L3:
        ClassCastException classcastexception;
        classcastexception;
        throw new WrongType(classcastexception, "set-cdr!", 1, obj3);
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static Object splitAt(Object obj, IntNum intnum)
    {
        Object obj1 = LList.Empty;
        do
        {
            Number number;
            try
            {
                number = (Number)intnum;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "zero?", 1, intnum);
            }
            if (numbers.isZero(number))
            {
                Object aobj[] = new Object[2];
                Object obj2;
                LList llist;
                try
                {
                    llist = (LList)obj1;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "reverse!", 1, obj1);
                }
                aobj[0] = lists.reverse$Ex(llist);
                aobj[1] = obj;
                return misc.values(aobj);
            }
            obj1 = lists.cons(lists.car.apply1(obj), obj1);
            obj2 = lists.cdr.apply1(obj);
            intnum = ((IntNum) (AddOp.$Mn.apply2(intnum, Lit1)));
            obj = obj2;
        } while (true);
    }

    public static Object splitAt$Ex(Object obj, IntNum intnum)
    {
        if (numbers.isZero(intnum))
        {
            Object aobj[] = new Object[2];
            aobj[0] = LList.Empty;
            aobj[1] = obj;
            return misc.values(aobj);
        }
        Object obj1 = drop(obj, IntNum.add(intnum, -1));
        Object obj2 = lists.cdr.apply1(obj1);
        Pair pair;
        try
        {
            pair = (Pair)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "set-cdr!", 1, obj1);
        }
        lists.setCdr$Ex(pair, LList.Empty);
        return misc.values(new Object[] {
            obj, obj2
        });
    }

    public static Object take(Object obj, IntNum intnum)
    {
        Object obj1 = LList.Empty;
        do
        {
            Number number;
            try
            {
                number = (Number)intnum;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "zero?", 1, intnum);
            }
            if (numbers.isZero(number))
            {
                Object obj2;
                LList llist;
                try
                {
                    llist = (LList)obj1;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "reverse!", 1, obj1);
                }
                return lists.reverse$Ex(llist);
            }
            obj2 = lists.cdr.apply1(obj);
            intnum = ((IntNum) (AddOp.$Mn.apply2(intnum, Lit1)));
            obj1 = lists.cons(lists.car.apply1(obj), obj1);
            obj = obj2;
        } while (true);
    }

    public static Object take$Ex(Object obj, IntNum intnum)
    {
        if (numbers.isZero(intnum))
        {
            return LList.Empty;
        }
        Object obj1 = drop(obj, IntNum.add(intnum, -1));
        Pair pair;
        try
        {
            pair = (Pair)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "set-cdr!", 1, obj1);
        }
        lists.setCdr$Ex(pair, LList.Empty);
        return obj;
    }

    public static Object takeRight(Object obj, IntNum intnum)
    {
        Object obj1 = drop(obj, intnum);
        Object obj2 = obj;
        for (; lists.isPair(obj1); obj1 = lists.cdr.apply1(obj1))
        {
            obj2 = lists.cdr.apply1(obj2);
        }

        return obj2;
    }

    public static Object takeWhile(Procedure procedure, Object obj)
    {
        frame23 frame23_1 = new frame23();
        frame23_1.pred = procedure;
        return frame23_1.lambda34recur(obj);
    }

    public static Object takeWhile$Ex(Procedure procedure, Object obj)
    {
        Object obj1 = isNullList(obj);
        if (obj1 == Boolean.FALSE ? procedure.apply1(lists.car.apply1(obj)) == Boolean.FALSE : obj1 != Boolean.FALSE)
        {
            obj = LList.Empty;
        } else
        {
            Object obj2 = lists.cdr.apply1(obj);
            Object obj3 = obj;
            while (lists.isPair(obj2)) 
            {
                if (procedure.apply1(lists.car.apply1(obj2)) != Boolean.FALSE)
                {
                    Object obj4 = lists.cdr.apply1(obj2);
                    obj3 = obj2;
                    obj2 = obj4;
                } else
                {
                    Pair pair;
                    try
                    {
                        pair = (Pair)obj3;
                    }
                    catch (ClassCastException classcastexception)
                    {
                        throw new WrongType(classcastexception, "set-cdr!", 1, obj3);
                    }
                    lists.setCdr$Ex(pair, LList.Empty);
                    return obj;
                }
            }
        }
        return obj;
    }

    public static Object tenth(Object obj)
    {
        return lists.cadr.apply1(lists.cddddr.apply1(lists.cddddr.apply1(obj)));
    }

    public static Object unfold$V(Procedure procedure, Procedure procedure1, Procedure procedure2, Object obj, Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        if (lists.isPair(llist))
        {
            Object obj3 = lists.car.apply1(llist);
            if (lists.isPair(lists.cdr.apply1(llist)))
            {
                gnu.kawa.functions.Apply apply = Scheme.apply;
                Object aobj1[] = new Object[8];
                aobj1[0] = misc.error;
                aobj1[1] = "Too many arguments";
                aobj1[2] = unfold;
                aobj1[3] = procedure;
                aobj1[4] = procedure1;
                aobj1[5] = procedure2;
                aobj1[6] = obj;
                aobj1[7] = llist;
                return apply.applyN(aobj1);
            }
            Object obj4 = LList.Empty;
            do
            {
                if (procedure.apply1(obj) != Boolean.FALSE)
                {
                    return appendReverse$Ex(obj4, Scheme.applyToArgs.apply2(obj3, obj));
                }
                Object obj5 = procedure2.apply1(obj);
                obj4 = lists.cons(procedure1.apply1(obj), obj4);
                obj = obj5;
            } while (true);
        }
        Object obj1 = LList.Empty;
        do
        {
            if (procedure.apply1(obj) != Boolean.FALSE)
            {
                Object obj2;
                LList llist1;
                try
                {
                    llist1 = (LList)obj1;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "reverse!", 1, obj1);
                }
                return lists.reverse$Ex(llist1);
            }
            obj2 = procedure2.apply1(obj);
            obj1 = lists.cons(procedure1.apply1(obj), obj1);
            obj = obj2;
        } while (true);
    }

    public static Object unfoldRight(Procedure procedure, Procedure procedure1, Procedure procedure2, Object obj)
    {
        return unfoldRight(procedure, procedure1, procedure2, obj, LList.Empty);
    }

    public static Object unfoldRight(Procedure procedure, Procedure procedure1, Procedure procedure2, Object obj, Object obj1)
    {
        do
        {
            if (procedure.apply1(obj) != Boolean.FALSE)
            {
                return obj1;
            }
            Object obj2 = procedure2.apply1(obj);
            obj1 = lists.cons(procedure1.apply1(obj), obj1);
            obj = obj2;
        } while (true);
    }

    public static LList unzip1(Object obj)
    {
        Object obj1 = LList.Empty;
        Object obj2 = obj;
        do
        {
            if (obj2 == LList.Empty)
            {
                return LList.reverseInPlace(obj1);
            }
            Pair pair;
            Object obj3;
            try
            {
                pair = (Pair)obj2;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "arg0", -2, obj2);
            }
            obj3 = pair.getCdr();
            obj1 = Pair.make(lists.car.apply1(pair.getCar()), obj1);
            obj2 = obj3;
        } while (true);
    }

    public static Object unzip2(Object obj)
    {
        new frame();
        return frame.lambda2recur(obj);
    }

    public static Object unzip3(Object obj)
    {
        new frame1();
        return frame1.lambda5recur(obj);
    }

    public static Object unzip4(Object obj)
    {
        new frame3();
        return frame3.lambda8recur(obj);
    }

    public static Object unzip5(Object obj)
    {
        new frame5();
        return frame5.lambda11recur(obj);
    }

    public static Pair xcons(Object obj, Object obj1)
    {
        return lists.cons(obj1, obj);
    }

    public static Object zip$V(Object obj, Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        return Scheme.apply.apply4(Scheme.map, LangObjType.listType, obj, llist);
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply1(modulemethod, obj);

        case 82: // 'R'
            Pair pair;
            IntNum intnum;
            LList llist;
            try
            {
                llist = (LList)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "list-copy", 1, obj);
            }
            return listCopy(llist);

        case 83: // 'S'
            try
            {
                intnum = LangObjType.coerceIntNum(obj);
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "iota", 1, obj);
            }
            return iota(intnum);

        case 87: // 'W'
            return isProperList(obj);

        case 88: // 'X'
            return isDottedList(obj);

        case 89: // 'Y'
            return isCircularList(obj);

        case 90: // 'Z'
            if (isNotPair(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 91: // '['
            return isNullList(obj);

        case 93: // ']'
            return length$Pl(obj);

        case 95: // '_'
            return fifth(obj);

        case 96: // '`'
            return sixth(obj);

        case 97: // 'a'
            return seventh(obj);

        case 98: // 'b'
            return eighth(obj);

        case 99: // 'c'
            return ninth(obj);

        case 100: // 'd'
            return tenth(obj);

        case 101: // 'e'
            return car$PlCdr(obj);

        case 110: // 'n'
            return last(obj);

        case 111: // 'o'
            try
            {
                pair = (Pair)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "last-pair", 1, obj);
            }
            return lastPair(pair);

        case 112: // 'p'
            return unzip1(obj);

        case 113: // 'q'
            return unzip2(obj);

        case 114: // 'r'
            return unzip3(obj);

        case 115: // 's'
            return unzip4(obj);

        case 116: // 't'
            return unzip5(obj);

        case 120: // 'x'
            return concatenate(obj);

        case 121: // 'y'
            return concatenate$Ex(obj);

        case 147: 
            return deleteDuplicates(obj);

        case 149: 
            return deleteDuplicates$Ex(obj);

        case 152: 
            return alistCopy(obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 78: // 'N'
            return xcons(obj, obj1);

        case 80: // 'P'
            Procedure procedure;
            Procedure procedure1;
            Procedure procedure2;
            Procedure procedure3;
            Procedure procedure4;
            Procedure procedure5;
            Procedure procedure6;
            Procedure procedure7;
            Procedure procedure8;
            Procedure procedure9;
            Procedure procedure10;
            Procedure procedure11;
            IntNum intnum;
            IntNum intnum1;
            IntNum intnum2;
            IntNum intnum3;
            IntNum intnum4;
            IntNum intnum5;
            IntNum intnum6;
            IntNum intnum7;
            IntNum intnum8;
            Numeric numeric;
            Procedure procedure12;
            try
            {
                procedure12 = (Procedure)obj1;
            }
            catch (ClassCastException classcastexception22)
            {
                throw new WrongType(classcastexception22, "list-tabulate", 2, obj1);
            }
            return listTabulate(obj, procedure12);

        case 83: // 'S'
            try
            {
                intnum8 = LangObjType.coerceIntNum(obj);
            }
            catch (ClassCastException classcastexception20)
            {
                throw new WrongType(classcastexception20, "iota", 1, obj);
            }
            try
            {
                numeric = LangObjType.coerceNumeric(obj1);
            }
            catch (ClassCastException classcastexception21)
            {
                throw new WrongType(classcastexception21, "iota", 2, obj1);
            }
            return iota(intnum8, numeric);

        case 102: // 'f'
            try
            {
                intnum7 = LangObjType.coerceIntNum(obj1);
            }
            catch (ClassCastException classcastexception19)
            {
                throw new WrongType(classcastexception19, "take", 2, obj1);
            }
            return take(obj, intnum7);

        case 103: // 'g'
            try
            {
                intnum6 = LangObjType.coerceIntNum(obj1);
            }
            catch (ClassCastException classcastexception18)
            {
                throw new WrongType(classcastexception18, "drop", 2, obj1);
            }
            return drop(obj, intnum6);

        case 104: // 'h'
            try
            {
                intnum5 = LangObjType.coerceIntNum(obj1);
            }
            catch (ClassCastException classcastexception17)
            {
                throw new WrongType(classcastexception17, "take!", 2, obj1);
            }
            return take$Ex(obj, intnum5);

        case 105: // 'i'
            try
            {
                intnum4 = LangObjType.coerceIntNum(obj1);
            }
            catch (ClassCastException classcastexception16)
            {
                throw new WrongType(classcastexception16, "take-right", 2, obj1);
            }
            return takeRight(obj, intnum4);

        case 106: // 'j'
            try
            {
                intnum3 = LangObjType.coerceIntNum(obj1);
            }
            catch (ClassCastException classcastexception15)
            {
                throw new WrongType(classcastexception15, "drop-right", 2, obj1);
            }
            return dropRight(obj, intnum3);

        case 107: // 'k'
            try
            {
                intnum2 = LangObjType.coerceIntNum(obj1);
            }
            catch (ClassCastException classcastexception14)
            {
                throw new WrongType(classcastexception14, "drop-right!", 2, obj1);
            }
            return dropRight$Ex(obj, intnum2);

        case 108: // 'l'
            try
            {
                intnum1 = LangObjType.coerceIntNum(obj1);
            }
            catch (ClassCastException classcastexception13)
            {
                throw new WrongType(classcastexception13, "split-at", 2, obj1);
            }
            return splitAt(obj, intnum1);

        case 109: // 'm'
            try
            {
                intnum = LangObjType.coerceIntNum(obj1);
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "split-at!", 2, obj1);
            }
            return splitAt$Ex(obj, intnum);

        case 118: // 'v'
            return appendReverse(obj, obj1);

        case 119: // 'w'
            return appendReverse$Ex(obj, obj1);

        case 137: 
            try
            {
                procedure11 = (Procedure)obj;
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "filter", 1, obj);
            }
            return filter(procedure11, obj1);

        case 138: 
            try
            {
                procedure10 = (Procedure)obj;
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "filter!", 1, obj);
            }
            return filter$Ex(procedure10, obj1);

        case 139: 
            try
            {
                procedure9 = (Procedure)obj;
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "partition", 1, obj);
            }
            return partition(procedure9, obj1);

        case 140: 
            try
            {
                procedure8 = (Procedure)obj;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "partition!", 1, obj);
            }
            return partition$Ex(procedure8, obj1);

        case 141: 
            return remove(obj, obj1);

        case 142: 
            return remove$Ex(obj, obj1);

        case 143: 
            return delete(obj, obj1);

        case 145: 
            return delete$Ex(obj, obj1);

        case 147: 
            try
            {
                procedure7 = (Procedure)obj1;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "delete-duplicates", 2, obj1);
            }
            return deleteDuplicates(obj, procedure7);

        case 149: 
            try
            {
                procedure6 = (Procedure)obj1;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "delete-duplicates!", 2, obj1);
            }
            return deleteDuplicates$Ex(obj, procedure6);

        case 153: 
            return alistDelete(obj, obj1);

        case 155: 
            return alistDelete$Ex(obj, obj1);

        case 157: 
            return find(obj, obj1);

        case 158: 
            try
            {
                procedure5 = (Procedure)obj;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "find-tail", 1, obj);
            }
            return findTail(procedure5, obj1);

        case 159: 
            try
            {
                procedure4 = (Procedure)obj;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "take-while", 1, obj);
            }
            return takeWhile(procedure4, obj1);

        case 160: 
            try
            {
                procedure3 = (Procedure)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "drop-while", 1, obj);
            }
            return dropWhile(procedure3, obj1);

        case 161: 
            try
            {
                procedure2 = (Procedure)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "take-while!", 1, obj);
            }
            return takeWhile$Ex(procedure2, obj1);

        case 162: 
            try
            {
                procedure1 = (Procedure)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "span", 1, obj);
            }
            return span(procedure1, obj1);

        case 163: 
            try
            {
                procedure = (Procedure)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "span!", 1, obj);
            }
            return span$Ex(procedure, obj1);

        case 164: 
            return _mthbreak(obj, obj1);

        case 165: 
            return break$Ex(obj, obj1);

        case 182: 
            return frame61.lambda84(obj, obj1);

        case 183: 
            return frame71.lambda100(obj, obj1);
        }
    }

    public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply3(modulemethod, obj, obj1, obj2);

        case 83: // 'S'
            Procedure procedure;
            Procedure procedure1;
            IntNum intnum;
            Numeric numeric;
            Numeric numeric1;
            try
            {
                intnum = LangObjType.coerceIntNum(obj);
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "iota", 1, obj);
            }
            try
            {
                numeric = LangObjType.coerceNumeric(obj1);
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "iota", 2, obj1);
            }
            try
            {
                numeric1 = LangObjType.coerceNumeric(obj2);
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "iota", 3, obj2);
            }
            return iota(intnum, numeric, numeric1);

        case 130: 
            try
            {
                procedure1 = (Procedure)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "reduce", 1, obj);
            }
            return reduce(procedure1, obj1, obj2);

        case 131: 
            try
            {
                procedure = (Procedure)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "reduce-right", 1, obj);
            }
            return reduceRight(procedure, obj1, obj2);

        case 143: 
            return delete(obj, obj1, obj2);

        case 145: 
            return delete$Ex(obj, obj1, obj2);

        case 151: 
            return alistCons(obj, obj1, obj2);

        case 153: 
            return alistDelete(obj, obj1, obj2);

        case 155: 
            return alistDelete$Ex(obj, obj1, obj2);
        }
    }

    public Object apply4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3)
    {
        if (modulemethod.selector == 123)
        {
            Procedure procedure;
            Procedure procedure1;
            Procedure procedure2;
            try
            {
                procedure = (Procedure)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "unfold-right", 1, obj);
            }
            try
            {
                procedure1 = (Procedure)obj1;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "unfold-right", 2, obj1);
            }
            try
            {
                procedure2 = (Procedure)obj2;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "unfold-right", 3, obj2);
            }
            return unfoldRight(procedure, procedure1, procedure2, obj3);
        } else
        {
            return super.apply4(modulemethod, obj, obj1, obj2, obj3);
        }
    }

    public Object applyN(ModuleMethod modulemethod, Object aobj[])
    {
        Object obj;
        switch (modulemethod.selector)
        {
        default:
            return super.applyN(modulemethod, aobj);

        case 79: // 'O'
            Object obj61 = aobj[0];
            int l7 = -1 + aobj.length;
            Object aobj31[] = new Object[l7];
            do
            {
                if (--l7 < 0)
                {
                    return makeList$V(obj61, aobj31);
                }
                aobj31[l7] = aobj[l7 + 1];
            } while (true);

        case 81: // 'Q'
            return cons$St(aobj);

        case 86: // 'V'
            Object obj60 = aobj[0];
            int k7 = -1 + aobj.length;
            Object aobj30[] = new Object[k7];
            do
            {
                if (--k7 < 0)
                {
                    return circularList$V(obj60, aobj30);
                }
                aobj30[k7] = aobj[k7 + 1];
            } while (true);

        case 92: // '\\'
            Object obj59 = aobj[0];
            int j7 = -1 + aobj.length;
            Object aobj29[] = new Object[j7];
            do
            {
                if (--j7 < 0)
                {
                    return list$Eq$V(obj59, aobj29);
                }
                aobj29[j7] = aobj[j7 + 1];
            } while (true);

        case 94: // '^'
            Object obj58 = aobj[0];
            int i7 = -1 + aobj.length;
            Object aobj28[] = new Object[i7];
            do
            {
                if (--i7 < 0)
                {
                    return zip$V(obj58, aobj28);
                }
                aobj28[i7] = aobj[i7 + 1];
            } while (true);

        case 117: // 'u'
            return append$Ex$V(aobj);

        case 122: // 'z'
            Object obj56 = aobj[0];
            Procedure procedure;
            Object obj1;
            int i;
            Object aobj1[];
            Procedure procedure1;
            Object obj3;
            int j;
            Object aobj2[];
            Procedure procedure2;
            int k;
            Object aobj3[];
            Procedure procedure3;
            int l;
            Object aobj4[];
            Procedure procedure4;
            Object obj7;
            int i1;
            Object aobj5[];
            Procedure procedure5;
            Object obj9;
            int j1;
            Object aobj6[];
            Procedure procedure6;
            Object obj11;
            int k1;
            Object aobj7[];
            Procedure procedure7;
            Object obj13;
            int l1;
            Object aobj8[];
            Procedure procedure8;
            int i2;
            Object aobj9[];
            Procedure procedure9;
            int j2;
            Object aobj10[];
            Procedure procedure10;
            Object obj17;
            int k2;
            Object aobj11[];
            Procedure procedure11;
            int l2;
            Object aobj12[];
            Procedure procedure12;
            int i3;
            Object aobj13[];
            Procedure procedure13;
            Object obj21;
            int j3;
            Object aobj14[];
            Procedure procedure14;
            Object obj23;
            int k3;
            Object aobj15[];
            Procedure procedure15;
            Object obj25;
            int l3;
            Object aobj16[];
            Procedure procedure16;
            Object obj27;
            int i4;
            Object aobj17[];
            Procedure procedure17;
            Object obj29;
            int j4;
            Object aobj18[];
            Procedure procedure18;
            Object obj31;
            int k4;
            Object aobj19[];
            Object obj32;
            Object obj33;
            int l4;
            Object aobj20[];
            Object obj34;
            Object obj35;
            int i5;
            Object aobj21[];
            Procedure procedure19;
            Object obj37;
            Object obj38;
            int j5;
            Object aobj22[];
            Procedure procedure20;
            Object obj40;
            Object obj41;
            int k5;
            Object aobj23[];
            Procedure procedure21;
            Object obj43;
            Object obj44;
            int l5;
            Object aobj24[];
            Procedure procedure22;
            Object obj46;
            Object obj47;
            int i6;
            Object aobj25[];
            Procedure procedure23;
            Procedure procedure24;
            Procedure procedure25;
            Object obj51;
            int j6;
            Object aobj26[];
            int k6;
            Procedure procedure26;
            Procedure procedure27;
            Procedure procedure28;
            Object obj55;
            Procedure procedure29;
            Object obj57;
            int l6;
            Object aobj27[];
            try
            {
                procedure29 = (Procedure)obj56;
            }
            catch (ClassCastException classcastexception29)
            {
                throw new WrongType(classcastexception29, "count", 1, obj56);
            }
            obj57 = aobj[1];
            l6 = -2 + aobj.length;
            aobj27 = new Object[l6];
            do
            {
                if (--l6 < 0)
                {
                    return count$V(procedure29, obj57, aobj27);
                }
                aobj27[l6] = aobj[l6 + 2];
            } while (true);

        case 123: // '{'
            k6 = -4 + aobj.length;
            Object obj52 = aobj[0];
            Object obj53;
            Object obj54;
            try
            {
                procedure26 = (Procedure)obj52;
            }
            catch (ClassCastException classcastexception26)
            {
                throw new WrongType(classcastexception26, "unfold-right", 1, obj52);
            }
            obj53 = aobj[1];
            try
            {
                procedure27 = (Procedure)obj53;
            }
            catch (ClassCastException classcastexception27)
            {
                throw new WrongType(classcastexception27, "unfold-right", 2, obj53);
            }
            obj54 = aobj[2];
            try
            {
                procedure28 = (Procedure)obj54;
            }
            catch (ClassCastException classcastexception28)
            {
                throw new WrongType(classcastexception28, "unfold-right", 3, obj54);
            }
            obj55 = aobj[3];
            if (k6 <= 0)
            {
                return unfoldRight(procedure26, procedure27, procedure28, obj55);
            } else
            {
                int _tmp = k6 - 1;
                return unfoldRight(procedure26, procedure27, procedure28, obj55, aobj[4]);
            }

        case 125: // '}'
            Object obj48 = aobj[0];
            Object obj49;
            Object obj50;
            try
            {
                procedure23 = (Procedure)obj48;
            }
            catch (ClassCastException classcastexception23)
            {
                throw new WrongType(classcastexception23, "unfold", 1, obj48);
            }
            obj49 = aobj[1];
            try
            {
                procedure24 = (Procedure)obj49;
            }
            catch (ClassCastException classcastexception24)
            {
                throw new WrongType(classcastexception24, "unfold", 2, obj49);
            }
            obj50 = aobj[2];
            try
            {
                procedure25 = (Procedure)obj50;
            }
            catch (ClassCastException classcastexception25)
            {
                throw new WrongType(classcastexception25, "unfold", 3, obj50);
            }
            obj51 = aobj[3];
            j6 = -4 + aobj.length;
            aobj26 = new Object[j6];
            do
            {
                if (--j6 < 0)
                {
                    return unfold$V(procedure23, procedure24, procedure25, obj51, aobj26);
                }
                aobj26[j6] = aobj[j6 + 4];
            } while (true);

        case 126: // '~'
            Object obj45 = aobj[0];
            try
            {
                procedure22 = (Procedure)obj45;
            }
            catch (ClassCastException classcastexception22)
            {
                throw new WrongType(classcastexception22, "fold", 1, obj45);
            }
            obj46 = aobj[1];
            obj47 = aobj[2];
            i6 = -3 + aobj.length;
            aobj25 = new Object[i6];
            do
            {
                if (--i6 < 0)
                {
                    return fold$V(procedure22, obj46, obj47, aobj25);
                }
                aobj25[i6] = aobj[i6 + 3];
            } while (true);

        case 127: // '\177'
            Object obj42 = aobj[0];
            try
            {
                procedure21 = (Procedure)obj42;
            }
            catch (ClassCastException classcastexception21)
            {
                throw new WrongType(classcastexception21, "fold-right", 1, obj42);
            }
            obj43 = aobj[1];
            obj44 = aobj[2];
            l5 = -3 + aobj.length;
            aobj24 = new Object[l5];
            do
            {
                if (--l5 < 0)
                {
                    return foldRight$V(procedure21, obj43, obj44, aobj24);
                }
                aobj24[l5] = aobj[l5 + 3];
            } while (true);

        case 128: 
            Object obj39 = aobj[0];
            try
            {
                procedure20 = (Procedure)obj39;
            }
            catch (ClassCastException classcastexception20)
            {
                throw new WrongType(classcastexception20, "pair-fold-right", 1, obj39);
            }
            obj40 = aobj[1];
            obj41 = aobj[2];
            k5 = -3 + aobj.length;
            aobj23 = new Object[k5];
            do
            {
                if (--k5 < 0)
                {
                    return pairFoldRight$V(procedure20, obj40, obj41, aobj23);
                }
                aobj23[k5] = aobj[k5 + 3];
            } while (true);

        case 129: 
            Object obj36 = aobj[0];
            try
            {
                procedure19 = (Procedure)obj36;
            }
            catch (ClassCastException classcastexception19)
            {
                throw new WrongType(classcastexception19, "pair-fold", 1, obj36);
            }
            obj37 = aobj[1];
            obj38 = aobj[2];
            j5 = -3 + aobj.length;
            aobj22 = new Object[j5];
            do
            {
                if (--j5 < 0)
                {
                    return pairFold$V(procedure19, obj37, obj38, aobj22);
                }
                aobj22[j5] = aobj[j5 + 3];
            } while (true);

        case 132: 
            obj34 = aobj[0];
            obj35 = aobj[1];
            i5 = -2 + aobj.length;
            aobj21 = new Object[i5];
            do
            {
                if (--i5 < 0)
                {
                    return appendMap$V(obj34, obj35, aobj21);
                }
                aobj21[i5] = aobj[i5 + 2];
            } while (true);

        case 133: 
            obj32 = aobj[0];
            obj33 = aobj[1];
            l4 = -2 + aobj.length;
            aobj20 = new Object[l4];
            do
            {
                if (--l4 < 0)
                {
                    return appendMap$Ex$V(obj32, obj33, aobj20);
                }
                aobj20[l4] = aobj[l4 + 2];
            } while (true);

        case 134: 
            Object obj30 = aobj[0];
            try
            {
                procedure18 = (Procedure)obj30;
            }
            catch (ClassCastException classcastexception18)
            {
                throw new WrongType(classcastexception18, "pair-for-each", 1, obj30);
            }
            obj31 = aobj[1];
            k4 = -2 + aobj.length;
            aobj19 = new Object[k4];
            do
            {
                if (--k4 < 0)
                {
                    return pairForEach$V(procedure18, obj31, aobj19);
                }
                aobj19[k4] = aobj[k4 + 2];
            } while (true);

        case 135: 
            Object obj28 = aobj[0];
            try
            {
                procedure17 = (Procedure)obj28;
            }
            catch (ClassCastException classcastexception17)
            {
                throw new WrongType(classcastexception17, "map!", 1, obj28);
            }
            obj29 = aobj[1];
            j4 = -2 + aobj.length;
            aobj18 = new Object[j4];
            do
            {
                if (--j4 < 0)
                {
                    return map$Ex$V(procedure17, obj29, aobj18);
                }
                aobj18[j4] = aobj[j4 + 2];
            } while (true);

        case 136: 
            Object obj26 = aobj[0];
            try
            {
                procedure16 = (Procedure)obj26;
            }
            catch (ClassCastException classcastexception16)
            {
                throw new WrongType(classcastexception16, "filter-map", 1, obj26);
            }
            obj27 = aobj[1];
            i4 = -2 + aobj.length;
            aobj17 = new Object[i4];
            do
            {
                if (--i4 < 0)
                {
                    return filterMap$V(procedure16, obj27, aobj17);
                }
                aobj17[i4] = aobj[i4 + 2];
            } while (true);

        case 166: 
            Object obj24 = aobj[0];
            try
            {
                procedure15 = (Procedure)obj24;
            }
            catch (ClassCastException classcastexception15)
            {
                throw new WrongType(classcastexception15, "any", 1, obj24);
            }
            obj25 = aobj[1];
            l3 = -2 + aobj.length;
            aobj16 = new Object[l3];
            do
            {
                if (--l3 < 0)
                {
                    return any$V(procedure15, obj25, aobj16);
                }
                aobj16[l3] = aobj[l3 + 2];
            } while (true);

        case 167: 
            Object obj22 = aobj[0];
            try
            {
                procedure14 = (Procedure)obj22;
            }
            catch (ClassCastException classcastexception14)
            {
                throw new WrongType(classcastexception14, "every", 1, obj22);
            }
            obj23 = aobj[1];
            k3 = -2 + aobj.length;
            aobj15 = new Object[k3];
            do
            {
                if (--k3 < 0)
                {
                    return every$V(procedure14, obj23, aobj15);
                }
                aobj15[k3] = aobj[k3 + 2];
            } while (true);

        case 168: 
            Object obj20 = aobj[0];
            try
            {
                procedure13 = (Procedure)obj20;
            }
            catch (ClassCastException classcastexception13)
            {
                throw new WrongType(classcastexception13, "list-index", 1, obj20);
            }
            obj21 = aobj[1];
            j3 = -2 + aobj.length;
            aobj14 = new Object[j3];
            do
            {
                if (--j3 < 0)
                {
                    return listIndex$V(procedure13, obj21, aobj14);
                }
                aobj14[j3] = aobj[j3 + 2];
            } while (true);

        case 169: 
            Object obj19 = aobj[0];
            try
            {
                procedure12 = (Procedure)obj19;
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "lset<=", 1, obj19);
            }
            i3 = -1 + aobj.length;
            aobj13 = new Object[i3];
            do
            {
                if (--i3 < 0)
                {
                    return lset$Ls$Eq$V(procedure12, aobj13);
                }
                aobj13[i3] = aobj[i3 + 1];
            } while (true);

        case 170: 
            Object obj18 = aobj[0];
            try
            {
                procedure11 = (Procedure)obj18;
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "lset=", 1, obj18);
            }
            l2 = -1 + aobj.length;
            aobj12 = new Object[l2];
            do
            {
                if (--l2 < 0)
                {
                    return lset$Eq$V(procedure11, aobj12);
                }
                aobj12[l2] = aobj[l2 + 1];
            } while (true);

        case 171: 
            Object obj16 = aobj[0];
            try
            {
                procedure10 = (Procedure)obj16;
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "lset-adjoin", 1, obj16);
            }
            obj17 = aobj[1];
            k2 = -2 + aobj.length;
            aobj11 = new Object[k2];
            do
            {
                if (--k2 < 0)
                {
                    return lsetAdjoin$V(procedure10, obj17, aobj11);
                }
                aobj11[k2] = aobj[k2 + 2];
            } while (true);

        case 172: 
            Object obj15 = aobj[0];
            try
            {
                procedure9 = (Procedure)obj15;
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "lset-union", 1, obj15);
            }
            j2 = -1 + aobj.length;
            aobj10 = new Object[j2];
            do
            {
                if (--j2 < 0)
                {
                    return lsetUnion$V(procedure9, aobj10);
                }
                aobj10[j2] = aobj[j2 + 1];
            } while (true);

        case 173: 
            Object obj14 = aobj[0];
            try
            {
                procedure8 = (Procedure)obj14;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "lset-union!", 1, obj14);
            }
            i2 = -1 + aobj.length;
            aobj9 = new Object[i2];
            do
            {
                if (--i2 < 0)
                {
                    return lsetUnion$Ex$V(procedure8, aobj9);
                }
                aobj9[i2] = aobj[i2 + 1];
            } while (true);

        case 174: 
            Object obj12 = aobj[0];
            try
            {
                procedure7 = (Procedure)obj12;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "lset-intersection", 1, obj12);
            }
            obj13 = aobj[1];
            l1 = -2 + aobj.length;
            aobj8 = new Object[l1];
            do
            {
                if (--l1 < 0)
                {
                    return lsetIntersection$V(procedure7, obj13, aobj8);
                }
                aobj8[l1] = aobj[l1 + 2];
            } while (true);

        case 175: 
            Object obj10 = aobj[0];
            try
            {
                procedure6 = (Procedure)obj10;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "lset-intersection!", 1, obj10);
            }
            obj11 = aobj[1];
            k1 = -2 + aobj.length;
            aobj7 = new Object[k1];
            do
            {
                if (--k1 < 0)
                {
                    return lsetIntersection$Ex$V(procedure6, obj11, aobj7);
                }
                aobj7[k1] = aobj[k1 + 2];
            } while (true);

        case 176: 
            Object obj8 = aobj[0];
            try
            {
                procedure5 = (Procedure)obj8;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "lset-difference", 1, obj8);
            }
            obj9 = aobj[1];
            j1 = -2 + aobj.length;
            aobj6 = new Object[j1];
            do
            {
                if (--j1 < 0)
                {
                    return lsetDifference$V(procedure5, obj9, aobj6);
                }
                aobj6[j1] = aobj[j1 + 2];
            } while (true);

        case 177: 
            Object obj6 = aobj[0];
            try
            {
                procedure4 = (Procedure)obj6;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "lset-difference!", 1, obj6);
            }
            obj7 = aobj[1];
            i1 = -2 + aobj.length;
            aobj5 = new Object[i1];
            do
            {
                if (--i1 < 0)
                {
                    return lsetDifference$Ex$V(procedure4, obj7, aobj5);
                }
                aobj5[i1] = aobj[i1 + 2];
            } while (true);

        case 178: 
            Object obj5 = aobj[0];
            try
            {
                procedure3 = (Procedure)obj5;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "lset-xor", 1, obj5);
            }
            l = -1 + aobj.length;
            aobj4 = new Object[l];
            do
            {
                if (--l < 0)
                {
                    return lsetXor$V(procedure3, aobj4);
                }
                aobj4[l] = aobj[l + 1];
            } while (true);

        case 179: 
            Object obj4 = aobj[0];
            try
            {
                procedure2 = (Procedure)obj4;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "lset-xor!", 1, obj4);
            }
            k = -1 + aobj.length;
            aobj3 = new Object[k];
            do
            {
                if (--k < 0)
                {
                    return lsetXor$Ex$V(procedure2, aobj3);
                }
                aobj3[k] = aobj[k + 1];
            } while (true);

        case 180: 
            Object obj2 = aobj[0];
            try
            {
                procedure1 = (Procedure)obj2;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "lset-diff+intersection", 1, obj2);
            }
            obj3 = aobj[1];
            j = -2 + aobj.length;
            aobj2 = new Object[j];
            do
            {
                if (--j < 0)
                {
                    return lsetDiff$PlIntersection$V(procedure1, obj3, aobj2);
                }
                aobj2[j] = aobj[j + 2];
            } while (true);

        case 181: 
            obj = aobj[0];
            break;
        }
        try
        {
            procedure = (Procedure)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "lset-diff+intersection!", 1, obj);
        }
        obj1 = aobj[1];
        i = -2 + aobj.length;
        aobj1 = new Object[i];
        do
        {
            if (--i < 0)
            {
                return lsetDiff$PlIntersection$Ex$V(procedure, obj1, aobj1);
            }
            aobj1[i] = aobj[i + 2];
        } while (true);
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 152: 
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 149: 
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 147: 
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 121: // 'y'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 120: // 'x'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 116: // 't'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 115: // 's'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 114: // 'r'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 113: // 'q'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 112: // 'p'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 111: // 'o'
            if (!(obj instanceof Pair))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 110: // 'n'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 101: // 'e'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 100: // 'd'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 99: // 'c'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 98: // 'b'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 97: // 'a'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 96: // '`'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 95: // '_'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 93: // ']'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 91: // '['
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

        case 87: // 'W'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 83: // 'S'
            if (IntNum.asIntNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 82: // 'R'
            break;
        }
        if (obj instanceof LList)
        {
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        } else
        {
            return 0xfff40001;
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match2(modulemethod, obj, obj1, callcontext);

        case 183: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 182: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 165: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 164: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 163: 
            if (!(obj instanceof Procedure))
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

        case 162: 
            if (!(obj instanceof Procedure))
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

        case 161: 
            if (!(obj instanceof Procedure))
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

        case 160: 
            if (!(obj instanceof Procedure))
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

        case 159: 
            if (!(obj instanceof Procedure))
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

        case 158: 
            if (!(obj instanceof Procedure))
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

        case 157: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 155: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 153: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 149: 
            callcontext.value1 = obj;
            if (!(obj1 instanceof Procedure))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 147: 
            callcontext.value1 = obj;
            if (!(obj1 instanceof Procedure))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 145: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 143: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 142: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 141: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 140: 
            if (!(obj instanceof Procedure))
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

        case 139: 
            if (!(obj instanceof Procedure))
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

        case 138: 
            if (!(obj instanceof Procedure))
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

        case 137: 
            if (!(obj instanceof Procedure))
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

        case 119: // 'w'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 118: // 'v'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 109: // 'm'
            callcontext.value1 = obj;
            if (IntNum.asIntNumOrNull(obj1) != null)
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 108: // 'l'
            callcontext.value1 = obj;
            if (IntNum.asIntNumOrNull(obj1) != null)
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 107: // 'k'
            callcontext.value1 = obj;
            if (IntNum.asIntNumOrNull(obj1) != null)
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 106: // 'j'
            callcontext.value1 = obj;
            if (IntNum.asIntNumOrNull(obj1) != null)
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 105: // 'i'
            callcontext.value1 = obj;
            if (IntNum.asIntNumOrNull(obj1) != null)
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 104: // 'h'
            callcontext.value1 = obj;
            if (IntNum.asIntNumOrNull(obj1) != null)
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 103: // 'g'
            callcontext.value1 = obj;
            if (IntNum.asIntNumOrNull(obj1) != null)
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 102: // 'f'
            callcontext.value1 = obj;
            if (IntNum.asIntNumOrNull(obj1) != null)
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 83: // 'S'
            if (IntNum.asIntNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                if (Numeric.asNumericOrNull(obj1) != null)
                {
                    callcontext.value2 = obj1;
                    callcontext.proc = modulemethod;
                    callcontext.pc = 2;
                    return 0;
                } else
                {
                    return 0xfff40002;
                }
            } else
            {
                return 0xfff40001;
            }

        case 80: // 'P'
            callcontext.value1 = obj;
            if (!(obj1 instanceof Procedure))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 78: // 'N'
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

        case 155: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 153: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 151: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 145: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 143: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 131: 
            if (!(obj instanceof Procedure))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            }

        case 130: 
            if (!(obj instanceof Procedure))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            }

        case 83: // 'S'
            break;
        }
        if (IntNum.asIntNumOrNull(obj) != null)
        {
            callcontext.value1 = obj;
            if (Numeric.asNumericOrNull(obj1) != null)
            {
                callcontext.value2 = obj1;
                if (Numeric.asNumericOrNull(obj2) != null)
                {
                    callcontext.value3 = obj2;
                    callcontext.proc = modulemethod;
                    callcontext.pc = 3;
                    return 0;
                } else
                {
                    return 0xfff40003;
                }
            } else
            {
                return 0xfff40002;
            }
        } else
        {
            return 0xfff40001;
        }
    }

    public int match4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3, CallContext callcontext)
    {
        if (modulemethod.selector == 123)
        {
            if (!(obj instanceof Procedure))
            {
                return 0xfff40001;
            }
            callcontext.value1 = obj;
            if (!(obj1 instanceof Procedure))
            {
                return 0xfff40002;
            }
            callcontext.value2 = obj1;
            if (!(obj2 instanceof Procedure))
            {
                return 0xfff40003;
            } else
            {
                callcontext.value3 = obj2;
                callcontext.value4 = obj3;
                callcontext.proc = modulemethod;
                callcontext.pc = 4;
                return 0;
            }
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

        case 181: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 180: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 179: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 178: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 177: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 176: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 175: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 174: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 173: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 172: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 171: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 170: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 169: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 168: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 167: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 166: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 136: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 135: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 134: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 133: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 132: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 129: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 128: 
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 127: // '\177'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 126: // '~'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 125: // '}'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 123: // '{'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 122: // 'z'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 117: // 'u'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 94: // '^'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 92: // '\\'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 86: // 'V'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 81: // 'Q'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 79: // 'O'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
        first = lists.car;
        second = lists.cadr;
        third = lists.caddr;
        fourth = lists.cadddr;
        map$Mnin$Mnorder = Scheme.map;
    }

    static 
    {
        Lit104 = (SimpleSymbol)(new SimpleSymbol("cdr")).readResolve();
        Lit103 = (SimpleSymbol)(new SimpleSymbol("car")).readResolve();
        Lit102 = (SimpleSymbol)(new SimpleSymbol("lp")).readResolve();
        Lit101 = (SimpleSymbol)(new SimpleSymbol("head")).readResolve();
        Lit100 = (SimpleSymbol)(new SimpleSymbol("tail")).readResolve();
        Lit99 = (SimpleSymbol)(new SimpleSymbol("lset-diff+intersection!")).readResolve();
        Lit98 = (SimpleSymbol)(new SimpleSymbol("lset-diff+intersection")).readResolve();
        Lit97 = (SimpleSymbol)(new SimpleSymbol("lset-xor!")).readResolve();
        Lit96 = (SimpleSymbol)(new SimpleSymbol("lset-xor")).readResolve();
        Lit95 = (SimpleSymbol)(new SimpleSymbol("lset-difference!")).readResolve();
        Lit94 = (SimpleSymbol)(new SimpleSymbol("lset-difference")).readResolve();
        Lit93 = (SimpleSymbol)(new SimpleSymbol("lset-intersection!")).readResolve();
        Lit92 = (SimpleSymbol)(new SimpleSymbol("lset-intersection")).readResolve();
        Lit91 = (SimpleSymbol)(new SimpleSymbol("lset-union!")).readResolve();
        Lit90 = (SimpleSymbol)(new SimpleSymbol("lset-union")).readResolve();
        Lit89 = (SimpleSymbol)(new SimpleSymbol("lset-adjoin")).readResolve();
        Lit88 = (SimpleSymbol)(new SimpleSymbol("lset=")).readResolve();
        Lit87 = (SimpleSymbol)(new SimpleSymbol("lset<=")).readResolve();
        Lit86 = (SimpleSymbol)(new SimpleSymbol("list-index")).readResolve();
        Object aobj[] = new Object[1];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("%every")).readResolve();
        Lit84 = simplesymbol;
        aobj[0] = simplesymbol;
        SyntaxRule asyntaxrule[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
        Object aobj1[] = new Object[10];
        aobj1[0] = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
        aobj1[1] = Lit102;
        aobj1[2] = Lit101;
        aobj1[3] = Lit103;
        aobj1[4] = Lit100;
        aobj1[5] = Lit104;
        aobj1[6] = (SimpleSymbol)(new SimpleSymbol("and")).readResolve();
        SimpleSymbol simplesymbol1 = (SimpleSymbol)(new SimpleSymbol("null-list?")).readResolve();
        Lit14 = simplesymbol1;
        aobj1[7] = PairWithPosition.make(simplesymbol1, PairWithPosition.make(Lit100, LList.Empty, "srfi1.scm", 0x575018), "srfi1.scm", 0x57500c);
        aobj1[8] = PairWithPosition.make(Lit101, LList.Empty, "srfi1.scm", 0x575024);
        aobj1[9] = PairWithPosition.make(PairWithPosition.make(Lit102, PairWithPosition.make(PairWithPosition.make(Lit103, PairWithPosition.make(Lit100, LList.Empty, "srfi1.scm", 0x575033), "srfi1.scm", 0x57502e), PairWithPosition.make(PairWithPosition.make(Lit104, PairWithPosition.make(Lit100, LList.Empty, "srfi1.scm", 0x57503e), "srfi1.scm", 0x575039), LList.Empty, "srfi1.scm", 0x575039), "srfi1.scm", 0x57502e), "srfi1.scm", 0x57502a), LList.Empty, "srfi1.scm", 0x57502a);
        asyntaxrule[0] = new SyntaxRule(syntaxpattern, "\001\001", "\021\030\004\021\030\f\241I\021\030\024\b\021\030\034\b\013\b\021\030$\b\021\030,\b\013\b\021\0304\021\030<!\t\003\030D\030L", aobj1, 0);
        Lit85 = new SyntaxRules(aobj, asyntaxrule, 2);
        Lit83 = (SimpleSymbol)(new SimpleSymbol("every")).readResolve();
        Lit82 = (SimpleSymbol)(new SimpleSymbol("any")).readResolve();
        Lit81 = (SimpleSymbol)(new SimpleSymbol("break!")).readResolve();
        Lit80 = (SimpleSymbol)(new SimpleSymbol("break")).readResolve();
        Lit79 = (SimpleSymbol)(new SimpleSymbol("span!")).readResolve();
        Lit78 = (SimpleSymbol)(new SimpleSymbol("span")).readResolve();
        Lit77 = (SimpleSymbol)(new SimpleSymbol("take-while!")).readResolve();
        Lit76 = (SimpleSymbol)(new SimpleSymbol("drop-while")).readResolve();
        Lit75 = (SimpleSymbol)(new SimpleSymbol("take-while")).readResolve();
        Lit74 = (SimpleSymbol)(new SimpleSymbol("find-tail")).readResolve();
        Lit73 = (SimpleSymbol)(new SimpleSymbol("find")).readResolve();
        Lit72 = (SimpleSymbol)(new SimpleSymbol("alist-delete!")).readResolve();
        Lit71 = (SimpleSymbol)(new SimpleSymbol("alist-delete")).readResolve();
        Lit70 = (SimpleSymbol)(new SimpleSymbol("alist-copy")).readResolve();
        Lit69 = (SimpleSymbol)(new SimpleSymbol("alist-cons")).readResolve();
        Lit68 = (SimpleSymbol)(new SimpleSymbol("delete-duplicates!")).readResolve();
        Lit67 = (SimpleSymbol)(new SimpleSymbol("delete-duplicates")).readResolve();
        Lit66 = (SimpleSymbol)(new SimpleSymbol("delete!")).readResolve();
        Lit65 = (SimpleSymbol)(new SimpleSymbol("delete")).readResolve();
        Lit64 = (SimpleSymbol)(new SimpleSymbol("remove!")).readResolve();
        Lit63 = (SimpleSymbol)(new SimpleSymbol("remove")).readResolve();
        Lit62 = (SimpleSymbol)(new SimpleSymbol("partition!")).readResolve();
        Lit61 = (SimpleSymbol)(new SimpleSymbol("partition")).readResolve();
        Lit60 = (SimpleSymbol)(new SimpleSymbol("filter!")).readResolve();
        Lit59 = (SimpleSymbol)(new SimpleSymbol("filter")).readResolve();
        Lit58 = (SimpleSymbol)(new SimpleSymbol("filter-map")).readResolve();
        Lit57 = (SimpleSymbol)(new SimpleSymbol("map!")).readResolve();
        Lit56 = (SimpleSymbol)(new SimpleSymbol("pair-for-each")).readResolve();
        Lit55 = (SimpleSymbol)(new SimpleSymbol("append-map!")).readResolve();
        Lit54 = (SimpleSymbol)(new SimpleSymbol("append-map")).readResolve();
        Lit53 = (SimpleSymbol)(new SimpleSymbol("reduce-right")).readResolve();
        Lit52 = (SimpleSymbol)(new SimpleSymbol("reduce")).readResolve();
        Lit51 = (SimpleSymbol)(new SimpleSymbol("pair-fold")).readResolve();
        Lit50 = (SimpleSymbol)(new SimpleSymbol("pair-fold-right")).readResolve();
        Lit49 = (SimpleSymbol)(new SimpleSymbol("fold-right")).readResolve();
        Lit48 = (SimpleSymbol)(new SimpleSymbol("fold")).readResolve();
        Lit47 = (SimpleSymbol)(new SimpleSymbol("unfold")).readResolve();
        Lit46 = (SimpleSymbol)(new SimpleSymbol("unfold-right")).readResolve();
        Lit45 = (SimpleSymbol)(new SimpleSymbol("count")).readResolve();
        Lit44 = (SimpleSymbol)(new SimpleSymbol("concatenate!")).readResolve();
        Lit43 = (SimpleSymbol)(new SimpleSymbol("concatenate")).readResolve();
        Lit42 = (SimpleSymbol)(new SimpleSymbol("append-reverse!")).readResolve();
        Lit41 = (SimpleSymbol)(new SimpleSymbol("append-reverse")).readResolve();
        Lit40 = (SimpleSymbol)(new SimpleSymbol("append!")).readResolve();
        Lit39 = (SimpleSymbol)(new SimpleSymbol("unzip5")).readResolve();
        Lit38 = (SimpleSymbol)(new SimpleSymbol("unzip4")).readResolve();
        Lit37 = (SimpleSymbol)(new SimpleSymbol("unzip3")).readResolve();
        Lit36 = (SimpleSymbol)(new SimpleSymbol("unzip2")).readResolve();
        Lit35 = (SimpleSymbol)(new SimpleSymbol("unzip1")).readResolve();
        Lit34 = (SimpleSymbol)(new SimpleSymbol("last-pair")).readResolve();
        Lit33 = (SimpleSymbol)(new SimpleSymbol("last")).readResolve();
        Lit32 = (SimpleSymbol)(new SimpleSymbol("split-at!")).readResolve();
        Lit31 = (SimpleSymbol)(new SimpleSymbol("split-at")).readResolve();
        Lit30 = (SimpleSymbol)(new SimpleSymbol("drop-right!")).readResolve();
        Lit29 = (SimpleSymbol)(new SimpleSymbol("drop-right")).readResolve();
        Lit28 = (SimpleSymbol)(new SimpleSymbol("take-right")).readResolve();
        Lit27 = (SimpleSymbol)(new SimpleSymbol("take!")).readResolve();
        Lit26 = (SimpleSymbol)(new SimpleSymbol("drop")).readResolve();
        Lit25 = (SimpleSymbol)(new SimpleSymbol("take")).readResolve();
        Lit24 = (SimpleSymbol)(new SimpleSymbol("car+cdr")).readResolve();
        Lit23 = (SimpleSymbol)(new SimpleSymbol("tenth")).readResolve();
        Lit22 = (SimpleSymbol)(new SimpleSymbol("ninth")).readResolve();
        Lit21 = (SimpleSymbol)(new SimpleSymbol("eighth")).readResolve();
        Lit20 = (SimpleSymbol)(new SimpleSymbol("seventh")).readResolve();
        Lit19 = (SimpleSymbol)(new SimpleSymbol("sixth")).readResolve();
        Lit18 = (SimpleSymbol)(new SimpleSymbol("fifth")).readResolve();
        Lit17 = (SimpleSymbol)(new SimpleSymbol("zip")).readResolve();
        Lit16 = (SimpleSymbol)(new SimpleSymbol("length+")).readResolve();
        Lit15 = (SimpleSymbol)(new SimpleSymbol("list=")).readResolve();
        Lit13 = (SimpleSymbol)(new SimpleSymbol("not-pair?")).readResolve();
        Lit12 = (SimpleSymbol)(new SimpleSymbol("circular-list?")).readResolve();
        Lit11 = (SimpleSymbol)(new SimpleSymbol("dotted-list?")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("proper-list?")).readResolve();
        Lit9 = (SimpleSymbol)(new SimpleSymbol("circular-list")).readResolve();
        Lit8 = (SimpleSymbol)(new SimpleSymbol("iota")).readResolve();
        Lit7 = (SimpleSymbol)(new SimpleSymbol("list-copy")).readResolve();
        Lit6 = (SimpleSymbol)(new SimpleSymbol("cons*")).readResolve();
        Lit5 = (SimpleSymbol)(new SimpleSymbol("list-tabulate")).readResolve();
        Lit4 = (SimpleSymbol)(new SimpleSymbol("make-list")).readResolve();
        Lit3 = (SimpleSymbol)(new SimpleSymbol("xcons")).readResolve();
        $instance = new srfi1();
        $Pcprovide$Pcsrfi$Mn1 = 123;
        $Pcprovide$Pclist$Mnlib = 123;
        srfi1 srfi1_1 = $instance;
        xcons = new ModuleMethod(srfi1_1, 78, Lit3, 8194);
        make$Mnlist = new ModuleMethod(srfi1_1, 79, Lit4, -4095);
        list$Mntabulate = new ModuleMethod(srfi1_1, 80, Lit5, 8194);
        cons$St = new ModuleMethod(srfi1_1, 81, Lit6, -4096);
        list$Mncopy = new ModuleMethod(srfi1_1, 82, Lit7, 4097);
        iota = new ModuleMethod(srfi1_1, 83, Lit8, 12289);
        circular$Mnlist = new ModuleMethod(srfi1_1, 86, Lit9, -4095);
        proper$Mnlist$Qu = new ModuleMethod(srfi1_1, 87, Lit10, 4097);
        dotted$Mnlist$Qu = new ModuleMethod(srfi1_1, 88, Lit11, 4097);
        circular$Mnlist$Qu = new ModuleMethod(srfi1_1, 89, Lit12, 4097);
        not$Mnpair$Qu = new ModuleMethod(srfi1_1, 90, Lit13, 4097);
        null$Mnlist$Qu = new ModuleMethod(srfi1_1, 91, Lit14, 4097);
        list$Eq = new ModuleMethod(srfi1_1, 92, Lit15, -4095);
        length$Pl = new ModuleMethod(srfi1_1, 93, Lit16, 4097);
        zip = new ModuleMethod(srfi1_1, 94, Lit17, -4095);
        fifth = new ModuleMethod(srfi1_1, 95, Lit18, 4097);
        sixth = new ModuleMethod(srfi1_1, 96, Lit19, 4097);
        seventh = new ModuleMethod(srfi1_1, 97, Lit20, 4097);
        eighth = new ModuleMethod(srfi1_1, 98, Lit21, 4097);
        ninth = new ModuleMethod(srfi1_1, 99, Lit22, 4097);
        tenth = new ModuleMethod(srfi1_1, 100, Lit23, 4097);
        car$Plcdr = new ModuleMethod(srfi1_1, 101, Lit24, 4097);
        take = new ModuleMethod(srfi1_1, 102, Lit25, 8194);
        drop = new ModuleMethod(srfi1_1, 103, Lit26, 8194);
        take$Ex = new ModuleMethod(srfi1_1, 104, Lit27, 8194);
        take$Mnright = new ModuleMethod(srfi1_1, 105, Lit28, 8194);
        drop$Mnright = new ModuleMethod(srfi1_1, 106, Lit29, 8194);
        drop$Mnright$Ex = new ModuleMethod(srfi1_1, 107, Lit30, 8194);
        split$Mnat = new ModuleMethod(srfi1_1, 108, Lit31, 8194);
        split$Mnat$Ex = new ModuleMethod(srfi1_1, 109, Lit32, 8194);
        last = new ModuleMethod(srfi1_1, 110, Lit33, 4097);
        last$Mnpair = new ModuleMethod(srfi1_1, 111, Lit34, 4097);
        unzip1 = new ModuleMethod(srfi1_1, 112, Lit35, 4097);
        unzip2 = new ModuleMethod(srfi1_1, 113, Lit36, 4097);
        unzip3 = new ModuleMethod(srfi1_1, 114, Lit37, 4097);
        unzip4 = new ModuleMethod(srfi1_1, 115, Lit38, 4097);
        unzip5 = new ModuleMethod(srfi1_1, 116, Lit39, 4097);
        append$Ex = new ModuleMethod(srfi1_1, 117, Lit40, -4096);
        append$Mnreverse = new ModuleMethod(srfi1_1, 118, Lit41, 8194);
        append$Mnreverse$Ex = new ModuleMethod(srfi1_1, 119, Lit42, 8194);
        concatenate = new ModuleMethod(srfi1_1, 120, Lit43, 4097);
        concatenate$Ex = new ModuleMethod(srfi1_1, 121, Lit44, 4097);
        count = new ModuleMethod(srfi1_1, 122, Lit45, -4094);
        unfold$Mnright = new ModuleMethod(srfi1_1, 123, Lit46, 20484);
        unfold = new ModuleMethod(srfi1_1, 125, Lit47, -4092);
        fold = new ModuleMethod(srfi1_1, 126, Lit48, -4093);
        fold$Mnright = new ModuleMethod(srfi1_1, 127, Lit49, -4093);
        pair$Mnfold$Mnright = new ModuleMethod(srfi1_1, 128, Lit50, -4093);
        pair$Mnfold = new ModuleMethod(srfi1_1, 129, Lit51, -4093);
        reduce = new ModuleMethod(srfi1_1, 130, Lit52, 12291);
        reduce$Mnright = new ModuleMethod(srfi1_1, 131, Lit53, 12291);
        append$Mnmap = new ModuleMethod(srfi1_1, 132, Lit54, -4094);
        append$Mnmap$Ex = new ModuleMethod(srfi1_1, 133, Lit55, -4094);
        pair$Mnfor$Mneach = new ModuleMethod(srfi1_1, 134, Lit56, -4094);
        map$Ex = new ModuleMethod(srfi1_1, 135, Lit57, -4094);
        filter$Mnmap = new ModuleMethod(srfi1_1, 136, Lit58, -4094);
        filter = new ModuleMethod(srfi1_1, 137, Lit59, 8194);
        filter$Ex = new ModuleMethod(srfi1_1, 138, Lit60, 8194);
        partition = new ModuleMethod(srfi1_1, 139, Lit61, 8194);
        partition$Ex = new ModuleMethod(srfi1_1, 140, Lit62, 8194);
        remove = new ModuleMethod(srfi1_1, 141, Lit63, 8194);
        remove$Ex = new ModuleMethod(srfi1_1, 142, Lit64, 8194);
        delete = new ModuleMethod(srfi1_1, 143, Lit65, 12290);
        delete$Ex = new ModuleMethod(srfi1_1, 145, Lit66, 12290);
        delete$Mnduplicates = new ModuleMethod(srfi1_1, 147, Lit67, 8193);
        delete$Mnduplicates$Ex = new ModuleMethod(srfi1_1, 149, Lit68, 8193);
        alist$Mncons = new ModuleMethod(srfi1_1, 151, Lit69, 12291);
        alist$Mncopy = new ModuleMethod(srfi1_1, 152, Lit70, 4097);
        alist$Mndelete = new ModuleMethod(srfi1_1, 153, Lit71, 12290);
        alist$Mndelete$Ex = new ModuleMethod(srfi1_1, 155, Lit72, 12290);
        find = new ModuleMethod(srfi1_1, 157, Lit73, 8194);
        find$Mntail = new ModuleMethod(srfi1_1, 158, Lit74, 8194);
        take$Mnwhile = new ModuleMethod(srfi1_1, 159, Lit75, 8194);
        drop$Mnwhile = new ModuleMethod(srfi1_1, 160, Lit76, 8194);
        take$Mnwhile$Ex = new ModuleMethod(srfi1_1, 161, Lit77, 8194);
        span = new ModuleMethod(srfi1_1, 162, Lit78, 8194);
        span$Ex = new ModuleMethod(srfi1_1, 163, Lit79, 8194);
        _fldbreak = new ModuleMethod(srfi1_1, 164, Lit80, 8194);
        break$Ex = new ModuleMethod(srfi1_1, 165, Lit81, 8194);
        any = new ModuleMethod(srfi1_1, 166, Lit82, -4094);
        every = new ModuleMethod(srfi1_1, 167, Lit83, -4094);
        $Pcevery = Macro.make(Lit84, Lit85, $instance);
        list$Mnindex = new ModuleMethod(srfi1_1, 168, Lit86, -4094);
        lset$Ls$Eq = new ModuleMethod(srfi1_1, 169, Lit87, -4095);
        lset$Eq = new ModuleMethod(srfi1_1, 170, Lit88, -4095);
        lset$Mnadjoin = new ModuleMethod(srfi1_1, 171, Lit89, -4094);
        lset$Mnunion = new ModuleMethod(srfi1_1, 172, Lit90, -4095);
        lset$Mnunion$Ex = new ModuleMethod(srfi1_1, 173, Lit91, -4095);
        lset$Mnintersection = new ModuleMethod(srfi1_1, 174, Lit92, -4094);
        lset$Mnintersection$Ex = new ModuleMethod(srfi1_1, 175, Lit93, -4094);
        lset$Mndifference = new ModuleMethod(srfi1_1, 176, Lit94, -4094);
        lset$Mndifference$Ex = new ModuleMethod(srfi1_1, 177, Lit95, -4094);
        lset$Mnxor = new ModuleMethod(srfi1_1, 178, Lit96, -4095);
        lset$Mnxor$Ex = new ModuleMethod(srfi1_1, 179, Lit97, -4095);
        lset$Mndiff$Plintersection = new ModuleMethod(srfi1_1, 180, Lit98, -4094);
        lset$Mndiff$Plintersection$Ex = new ModuleMethod(srfi1_1, 181, Lit99, -4094);
        lambda$Fn64 = new ModuleMethod(srfi1_1, 182, null, 8194);
        lambda$Fn78 = new ModuleMethod(srfi1_1, 183, null, 8194);
        $instance.run();
    }
}
