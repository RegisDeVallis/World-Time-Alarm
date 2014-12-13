// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.reflect.SlotSet;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.standard.Scheme;

public class lists extends ModuleBody
{

    public static final Location $Prvt$define$Mnprocedure = StaticFieldLocation.make("kawa.lib.std_syntax", "define$Mnprocedure");
    public static final lists $instance;
    static final Keyword Lit0 = Keyword.make("setter");
    static final SimpleSymbol Lit1 = (SimpleSymbol)(new SimpleSymbol("car")).readResolve();
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2 = (SimpleSymbol)(new SimpleSymbol("cdr")).readResolve();
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod assoc;
    public static final ModuleMethod assq;
    public static final ModuleMethod assv;
    public static final GenericProc caaaar;
    static final ModuleMethod caaaar$Fn28;
    public static final GenericProc caaadr;
    static final ModuleMethod caaadr$Fn30;
    public static final GenericProc caaar;
    static final ModuleMethod caaar$Fn12;
    public static final GenericProc caadar;
    static final ModuleMethod caadar$Fn32;
    public static final GenericProc caaddr;
    static final ModuleMethod caaddr$Fn34;
    public static final GenericProc caadr;
    static final ModuleMethod caadr$Fn14;
    public static final GenericProc caar;
    static final ModuleMethod caar$Fn4;
    public static final GenericProc cadaar;
    static final ModuleMethod cadaar$Fn36;
    public static final GenericProc cadadr;
    static final ModuleMethod cadadr$Fn38;
    public static final GenericProc cadar;
    static final ModuleMethod cadar$Fn16;
    public static final GenericProc caddar;
    static final ModuleMethod caddar$Fn40;
    public static final GenericProc cadddr;
    static final ModuleMethod cadddr$Fn42;
    public static final GenericProc caddr;
    static final ModuleMethod caddr$Fn18;
    public static final GenericProc cadr;
    static final ModuleMethod cadr$Fn6;
    public static final GenericProc car;
    static final ModuleMethod car$Fn1;
    public static final GenericProc cdaaar;
    static final ModuleMethod cdaaar$Fn44;
    public static final GenericProc cdaadr;
    static final ModuleMethod cdaadr$Fn46;
    public static final GenericProc cdaar;
    static final ModuleMethod cdaar$Fn20;
    public static final GenericProc cdadar;
    static final ModuleMethod cdadar$Fn48;
    public static final GenericProc cdaddr;
    static final ModuleMethod cdaddr$Fn50;
    public static final GenericProc cdadr;
    static final ModuleMethod cdadr$Fn22;
    public static final GenericProc cdar;
    static final ModuleMethod cdar$Fn8;
    public static final GenericProc cddaar;
    static final ModuleMethod cddaar$Fn52;
    public static final GenericProc cddadr;
    static final ModuleMethod cddadr$Fn54;
    public static final GenericProc cddar;
    static final ModuleMethod cddar$Fn24;
    public static final GenericProc cdddar;
    static final ModuleMethod cdddar$Fn56;
    public static final GenericProc cddddr;
    static final ModuleMethod cddddr$Fn58;
    public static final GenericProc cdddr;
    static final ModuleMethod cdddr$Fn26;
    public static final GenericProc cddr;
    static final ModuleMethod cddr$Fn10;
    public static final GenericProc cdr;
    static final ModuleMethod cdr$Fn2;
    public static final ModuleMethod cons;
    static final ModuleMethod lambda$Fn11;
    static final ModuleMethod lambda$Fn13;
    static final ModuleMethod lambda$Fn15;
    static final ModuleMethod lambda$Fn17;
    static final ModuleMethod lambda$Fn19;
    static final ModuleMethod lambda$Fn21;
    static final ModuleMethod lambda$Fn23;
    static final ModuleMethod lambda$Fn25;
    static final ModuleMethod lambda$Fn27;
    static final ModuleMethod lambda$Fn29;
    static final ModuleMethod lambda$Fn3;
    static final ModuleMethod lambda$Fn31;
    static final ModuleMethod lambda$Fn33;
    static final ModuleMethod lambda$Fn35;
    static final ModuleMethod lambda$Fn37;
    static final ModuleMethod lambda$Fn39;
    static final ModuleMethod lambda$Fn41;
    static final ModuleMethod lambda$Fn43;
    static final ModuleMethod lambda$Fn45;
    static final ModuleMethod lambda$Fn47;
    static final ModuleMethod lambda$Fn49;
    static final ModuleMethod lambda$Fn5;
    static final ModuleMethod lambda$Fn51;
    static final ModuleMethod lambda$Fn53;
    static final ModuleMethod lambda$Fn55;
    static final ModuleMethod lambda$Fn57;
    static final ModuleMethod lambda$Fn7;
    static final ModuleMethod lambda$Fn9;
    public static final ModuleMethod length;
    public static final ModuleMethod list$Mnref;
    public static final ModuleMethod list$Mntail;
    public static final ModuleMethod list$Qu;
    public static final ModuleMethod member;
    public static final ModuleMethod memq;
    public static final ModuleMethod memv;
    public static final ModuleMethod null$Qu;
    public static final ModuleMethod pair$Qu;
    public static final ModuleMethod reverse;
    public static final ModuleMethod reverse$Ex;
    public static final ModuleMethod set$Mncar$Ex;
    public static final ModuleMethod set$Mncdr$Ex;

    public lists()
    {
        ModuleInfo.register(this);
    }

    public static Object assoc(Object obj, Object obj1)
    {
        return assoc(obj, obj1, ((Procedure) (Scheme.isEqual)));
    }

    public static Object assoc(Object obj, Object obj1, Procedure procedure)
    {
_L6:
        if (obj1 != LList.Empty) goto _L2; else goto _L1
_L1:
        Object obj3 = Boolean.FALSE;
_L4:
        return obj3;
_L2:
        Object obj2 = car.apply1(obj1);
        try
        {
            obj3 = (Pair)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "pair", -2, obj2);
        }
        if (procedure.apply2(((Pair) (obj3)).getCar(), obj) != Boolean.FALSE) goto _L4; else goto _L3
_L3:
        obj1 = cdr.apply1(obj1);
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static Object assq(Object obj, Object obj1)
    {
_L6:
        if (obj1 != LList.Empty) goto _L2; else goto _L1
_L1:
        Object obj3 = Boolean.FALSE;
_L4:
        return obj3;
_L2:
        Object obj2 = car.apply1(obj1);
        try
        {
            obj3 = (Pair)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "pair", -2, obj2);
        }
        if (((Pair) (obj3)).getCar() == obj) goto _L4; else goto _L3
_L3:
        obj1 = cdr.apply1(obj1);
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static Object assv(Object obj, Object obj1)
    {
_L6:
        if (obj1 != LList.Empty) goto _L2; else goto _L1
_L1:
        Object obj3 = Boolean.FALSE;
_L4:
        return obj3;
_L2:
        Object obj2 = car.apply1(obj1);
        try
        {
            obj3 = (Pair)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "pair", -2, obj2);
        }
        if (Scheme.isEqv.apply2(((Pair) (obj3)).getCar(), obj) != Boolean.FALSE) goto _L4; else goto _L3
_L3:
        obj1 = cdr.apply1(obj1);
        if (true) goto _L6; else goto _L5
_L5:
    }

    static Object caaaar(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCar()).getCar()).getCar()).getCar();
    }

    static Object caaadr(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCdr()).getCar()).getCar()).getCar();
    }

    static Object caaar(Object obj)
    {
        return ((Pair)((Pair)((Pair)obj).getCar()).getCar()).getCar();
    }

    static Object caadar(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCar()).getCdr()).getCar()).getCar();
    }

    static Object caaddr(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCdr()).getCdr()).getCar()).getCar();
    }

    static Object caadr(Object obj)
    {
        return ((Pair)((Pair)((Pair)obj).getCdr()).getCar()).getCar();
    }

    static Object caar(Object obj)
    {
        return ((Pair)((Pair)obj).getCar()).getCar();
    }

    static Object cadaar(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCar()).getCar()).getCdr()).getCar();
    }

    static Object cadadr(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCdr()).getCar()).getCdr()).getCar();
    }

    static Object cadar(Object obj)
    {
        return ((Pair)((Pair)((Pair)obj).getCar()).getCdr()).getCar();
    }

    static Object caddar(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCar()).getCdr()).getCdr()).getCar();
    }

    static Object cadddr(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCdr()).getCdr()).getCdr()).getCar();
    }

    static Object caddr(Object obj)
    {
        return ((Pair)((Pair)((Pair)obj).getCdr()).getCdr()).getCar();
    }

    static Object cadr(Object obj)
    {
        return ((Pair)((Pair)obj).getCdr()).getCar();
    }

    static Object car(Pair pair)
    {
        return pair.getCar();
    }

    static Object cdaaar(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCar()).getCar()).getCar()).getCdr();
    }

    static Object cdaadr(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCdr()).getCar()).getCar()).getCdr();
    }

    static Object cdaar(Object obj)
    {
        return ((Pair)((Pair)((Pair)obj).getCar()).getCar()).getCdr();
    }

    static Object cdadar(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCar()).getCdr()).getCar()).getCdr();
    }

    static Object cdaddr(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCdr()).getCdr()).getCar()).getCdr();
    }

    static Object cdadr(Object obj)
    {
        return ((Pair)((Pair)((Pair)obj).getCdr()).getCar()).getCdr();
    }

    static Object cdar(Object obj)
    {
        return ((Pair)((Pair)obj).getCar()).getCdr();
    }

    static Object cddaar(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCar()).getCar()).getCdr()).getCdr();
    }

    static Object cddadr(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCdr()).getCar()).getCdr()).getCdr();
    }

    static Object cddar(Object obj)
    {
        return ((Pair)((Pair)((Pair)obj).getCar()).getCdr()).getCdr();
    }

    static Object cdddar(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCar()).getCdr()).getCdr()).getCdr();
    }

    static Object cddddr(Object obj)
    {
        return ((Pair)((Pair)((Pair)((Pair)obj).getCdr()).getCdr()).getCdr()).getCdr();
    }

    static Object cdddr(Object obj)
    {
        return ((Pair)((Pair)((Pair)obj).getCdr()).getCdr()).getCdr();
    }

    static Object cddr(Object obj)
    {
        return ((Pair)((Pair)obj).getCdr()).getCdr();
    }

    static Object cdr(Pair pair)
    {
        return pair.getCdr();
    }

    public static Pair cons(Object obj, Object obj1)
    {
        return new Pair(obj, obj1);
    }

    public static boolean isList(Object obj)
    {
        int i = LList.listLength(obj, false);
        boolean flag = false;
        if (i >= 0)
        {
            flag = true;
        }
        return flag;
    }

    public static boolean isNull(Object obj)
    {
        return obj == LList.Empty;
    }

    public static boolean isPair(Object obj)
    {
        return obj instanceof Pair;
    }

    static void lambda1(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)obj).getCar(), Lit1, obj1);
    }

    static void lambda10(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)obj).getCdr()).getCar(), Lit2, obj1);
    }

    static void lambda11(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)obj).getCar()).getCdr(), Lit2, obj1);
    }

    static void lambda12(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)obj).getCdr()).getCdr(), Lit2, obj1);
    }

    static void lambda13(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCar()).getCar()).getCar(), Lit1, obj1);
    }

    static void lambda14(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCdr()).getCar()).getCar(), Lit1, obj1);
    }

    static void lambda15(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCar()).getCdr()).getCar(), Lit1, obj1);
    }

    static void lambda16(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCdr()).getCdr()).getCar(), Lit1, obj1);
    }

    static void lambda17(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCar()).getCar()).getCdr(), Lit1, obj1);
    }

    static void lambda18(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCdr()).getCar()).getCdr(), Lit1, obj1);
    }

    static void lambda19(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCar()).getCdr()).getCdr(), Lit1, obj1);
    }

    static void lambda2(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)obj).getCdr(), Lit1, obj1);
    }

    static void lambda20(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCdr()).getCdr()).getCdr(), Lit1, obj1);
    }

    static void lambda21(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCar()).getCar()).getCar(), Lit2, obj1);
    }

    static void lambda22(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCdr()).getCar()).getCar(), Lit2, obj1);
    }

    static void lambda23(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCar()).getCdr()).getCar(), Lit2, obj1);
    }

    static void lambda24(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCdr()).getCdr()).getCar(), Lit2, obj1);
    }

    static void lambda25(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCar()).getCar()).getCdr(), Lit2, obj1);
    }

    static void lambda26(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCdr()).getCar()).getCdr(), Lit2, obj1);
    }

    static void lambda27(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCar()).getCdr()).getCdr(), Lit2, obj1);
    }

    static void lambda28(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)((Pair)obj).getCdr()).getCdr()).getCdr(), Lit2, obj1);
    }

    static void lambda3(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)obj).getCar(), Lit2, obj1);
    }

    static void lambda4(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)obj).getCdr(), Lit2, obj1);
    }

    static void lambda5(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)obj).getCar()).getCar(), Lit1, obj1);
    }

    static void lambda6(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)obj).getCdr()).getCar(), Lit1, obj1);
    }

    static void lambda7(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)obj).getCar()).getCdr(), Lit1, obj1);
    }

    static void lambda8(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)obj).getCdr()).getCdr(), Lit1, obj1);
    }

    static void lambda9(Object obj, Object obj1)
    {
        SlotSet.set$Mnfield$Ex.apply3(((Pair)((Pair)obj).getCar()).getCar(), Lit2, obj1);
    }

    public static int length(LList llist)
    {
        return LList.length(llist);
    }

    public static Object listRef(Object obj, int i)
    {
        return car.apply1(listTail(obj, i));
    }

    public static Object listTail(Object obj, int i)
    {
        return LList.listTail(obj, i);
    }

    public static Object member(Object obj, Object obj1)
    {
        return member(obj, obj1, ((Procedure) (Scheme.isEqual)));
    }

    public static Object member(Object obj, Object obj1, Procedure procedure)
    {
        Object obj2 = obj1;
_L3:
        boolean flag = obj2 instanceof Pair;
        if (!flag) goto _L2; else goto _L1
_L1:
        Pair pair;
        try
        {
            pair = (Pair)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "p", -2, obj2);
        }
        if (procedure.apply2(obj, pair.getCar()) != Boolean.FALSE)
        {
            return obj2;
        }
        obj2 = pair.getCdr();
        if (true) goto _L3; else goto _L2
_L2:
        if (flag)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static Object memq(Object obj, Object obj1)
    {
        Object obj2 = obj1;
_L3:
        boolean flag = obj2 instanceof Pair;
        if (!flag) goto _L2; else goto _L1
_L1:
        Pair pair;
        try
        {
            pair = (Pair)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "p", -2, obj2);
        }
        if (obj == pair.getCar())
        {
            return obj2;
        }
        obj2 = pair.getCdr();
        if (true) goto _L3; else goto _L2
_L2:
        if (flag)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static Object memv(Object obj, Object obj1)
    {
        Object obj2 = obj1;
_L3:
        boolean flag = obj2 instanceof Pair;
        if (!flag) goto _L2; else goto _L1
_L1:
        Pair pair;
        try
        {
            pair = (Pair)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "p", -2, obj2);
        }
        if (Scheme.isEqv.apply2(obj, pair.getCar()) != Boolean.FALSE)
        {
            return obj2;
        }
        obj2 = pair.getCdr();
        if (true) goto _L3; else goto _L2
_L2:
        if (flag)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static LList reverse(LList llist)
    {
        Object obj = LList.Empty;
        Object obj1 = llist;
        do
        {
            if (isNull(obj1))
            {
                return (LList)obj;
            }
            Pair pair;
            try
            {
                pair = (Pair)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "pair", -2, obj1);
            }
            obj1 = cdr.apply1(pair);
            obj = cons(car.apply1(pair), obj);
        } while (true);
    }

    public static LList reverse$Ex(LList llist)
    {
        return LList.reverseInPlace(llist);
    }

    public static void setCar$Ex(Pair pair, Object obj)
    {
        pair.setCar(obj);
    }

    public static void setCdr$Ex(Pair pair, Object obj)
    {
        pair.setCdr(obj);
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        case 2: // '\002'
        case 4: // '\004'
        case 5: // '\005'
        case 8: // '\b'
        case 10: // '\n'
        case 12: // '\f'
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
        case 48: // '0'
        case 50: // '2'
        case 52: // '4'
        case 54: // '6'
        case 56: // '8'
        case 58: // ':'
        case 60: // '<'
        case 62: // '>'
        case 66: // 'B'
        case 67: // 'C'
        default:
            return super.apply1(modulemethod, obj);

        case 1: // '\001'
            if (isPair(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 3: // '\003'
            if (isNull(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 6: // '\006'
            LList llist;
            LList llist1;
            LList llist2;
            Pair pair;
            Pair pair1;
            try
            {
                pair1 = (Pair)obj;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "car", 1, obj);
            }
            return car(pair1);

        case 7: // '\007'
            try
            {
                pair = (Pair)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "cdr", 1, obj);
            }
            return cdr(pair);

        case 9: // '\t'
            return caar(obj);

        case 11: // '\013'
            return cadr(obj);

        case 13: // '\r'
            return cdar(obj);

        case 15: // '\017'
            return cddr(obj);

        case 17: // '\021'
            return caaar(obj);

        case 19: // '\023'
            return caadr(obj);

        case 21: // '\025'
            return cadar(obj);

        case 23: // '\027'
            return caddr(obj);

        case 25: // '\031'
            return cdaar(obj);

        case 27: // '\033'
            return cdadr(obj);

        case 29: // '\035'
            return cddar(obj);

        case 31: // '\037'
            return cdddr(obj);

        case 33: // '!'
            return caaaar(obj);

        case 35: // '#'
            return caaadr(obj);

        case 37: // '%'
            return caadar(obj);

        case 39: // '\''
            return caaddr(obj);

        case 41: // ')'
            return cadaar(obj);

        case 43: // '+'
            return cadadr(obj);

        case 45: // '-'
            return caddar(obj);

        case 47: // '/'
            return cadddr(obj);

        case 49: // '1'
            return cdaaar(obj);

        case 51: // '3'
            return cdaadr(obj);

        case 53: // '5'
            return cdadar(obj);

        case 55: // '7'
            return cdaddr(obj);

        case 57: // '9'
            return cddaar(obj);

        case 59: // ';'
            return cddadr(obj);

        case 61: // '='
            return cdddar(obj);

        case 63: // '?'
            return cddddr(obj);

        case 64: // '@'
            try
            {
                llist2 = (LList)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "length", 1, obj);
            }
            return Integer.valueOf(length(llist2));

        case 65: // 'A'
            try
            {
                llist1 = (LList)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "reverse", 1, obj);
            }
            return reverse(llist1);

        case 68: // 'D'
            if (isList(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 69: // 'E'
            break;
        }
        try
        {
            llist = (LList)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "reverse!", 1, obj);
        }
        return reverse$Ex(llist);
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        case 3: // '\003'
        case 6: // '\006'
        case 7: // '\007'
        case 9: // '\t'
        case 11: // '\013'
        case 13: // '\r'
        case 15: // '\017'
        case 17: // '\021'
        case 19: // '\023'
        case 21: // '\025'
        case 23: // '\027'
        case 25: // '\031'
        case 27: // '\033'
        case 29: // '\035'
        case 31: // '\037'
        case 33: // '!'
        case 35: // '#'
        case 37: // '%'
        case 39: // '\''
        case 41: // ')'
        case 43: // '+'
        case 45: // '-'
        case 47: // '/'
        case 49: // '1'
        case 51: // '3'
        case 53: // '5'
        case 55: // '7'
        case 57: // '9'
        case 59: // ';'
        case 61: // '='
        case 63: // '?'
        case 64: // '@'
        case 65: // 'A'
        case 68: // 'D'
        case 69: // 'E'
        case 73: // 'I'
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 2: // '\002'
            return cons(obj, obj1);

        case 4: // '\004'
            int i;
            int j;
            Pair pair;
            Pair pair1;
            try
            {
                pair1 = (Pair)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "set-car!", 1, obj);
            }
            setCar$Ex(pair1, obj1);
            return Values.empty;

        case 5: // '\005'
            try
            {
                pair = (Pair)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "set-cdr!", 1, obj);
            }
            setCdr$Ex(pair, obj1);
            return Values.empty;

        case 8: // '\b'
            lambda1(obj, obj1);
            return Values.empty;

        case 10: // '\n'
            lambda2(obj, obj1);
            return Values.empty;

        case 12: // '\f'
            lambda3(obj, obj1);
            return Values.empty;

        case 14: // '\016'
            lambda4(obj, obj1);
            return Values.empty;

        case 16: // '\020'
            lambda5(obj, obj1);
            return Values.empty;

        case 18: // '\022'
            lambda6(obj, obj1);
            return Values.empty;

        case 20: // '\024'
            lambda7(obj, obj1);
            return Values.empty;

        case 22: // '\026'
            lambda8(obj, obj1);
            return Values.empty;

        case 24: // '\030'
            lambda9(obj, obj1);
            return Values.empty;

        case 26: // '\032'
            lambda10(obj, obj1);
            return Values.empty;

        case 28: // '\034'
            lambda11(obj, obj1);
            return Values.empty;

        case 30: // '\036'
            lambda12(obj, obj1);
            return Values.empty;

        case 32: // ' '
            lambda13(obj, obj1);
            return Values.empty;

        case 34: // '"'
            lambda14(obj, obj1);
            return Values.empty;

        case 36: // '$'
            lambda15(obj, obj1);
            return Values.empty;

        case 38: // '&'
            lambda16(obj, obj1);
            return Values.empty;

        case 40: // '('
            lambda17(obj, obj1);
            return Values.empty;

        case 42: // '*'
            lambda18(obj, obj1);
            return Values.empty;

        case 44: // ','
            lambda19(obj, obj1);
            return Values.empty;

        case 46: // '.'
            lambda20(obj, obj1);
            return Values.empty;

        case 48: // '0'
            lambda21(obj, obj1);
            return Values.empty;

        case 50: // '2'
            lambda22(obj, obj1);
            return Values.empty;

        case 52: // '4'
            lambda23(obj, obj1);
            return Values.empty;

        case 54: // '6'
            lambda24(obj, obj1);
            return Values.empty;

        case 56: // '8'
            lambda25(obj, obj1);
            return Values.empty;

        case 58: // ':'
            lambda26(obj, obj1);
            return Values.empty;

        case 60: // '<'
            lambda27(obj, obj1);
            return Values.empty;

        case 62: // '>'
            lambda28(obj, obj1);
            return Values.empty;

        case 66: // 'B'
            try
            {
                j = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "list-tail", 2, obj1);
            }
            return listTail(obj, j);

        case 67: // 'C'
            try
            {
                i = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "list-ref", 2, obj1);
            }
            return listRef(obj, i);

        case 70: // 'F'
            return memq(obj, obj1);

        case 71: // 'G'
            return memv(obj, obj1);

        case 72: // 'H'
            return member(obj, obj1);

        case 74: // 'J'
            return assq(obj, obj1);

        case 75: // 'K'
            return assv(obj, obj1);

        case 76: // 'L'
            return assoc(obj, obj1);
        }
    }

    public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply3(modulemethod, obj, obj1, obj2);

        case 72: // 'H'
            Procedure procedure;
            Procedure procedure1;
            try
            {
                procedure1 = (Procedure)obj2;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "member", 3, obj2);
            }
            return member(obj, obj1, procedure1);

        case 76: // 'L'
            break;
        }
        try
        {
            procedure = (Procedure)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "assoc", 3, obj2);
        }
        return assoc(obj, obj1, procedure);
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 2: // '\002'
        case 4: // '\004'
        case 5: // '\005'
        case 8: // '\b'
        case 10: // '\n'
        case 12: // '\f'
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
        case 48: // '0'
        case 50: // '2'
        case 52: // '4'
        case 54: // '6'
        case 56: // '8'
        case 58: // ':'
        case 60: // '<'
        case 62: // '>'
        case 66: // 'B'
        case 67: // 'C'
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 69: // 'E'
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

        case 68: // 'D'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 65: // 'A'
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

        case 64: // '@'
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

        case 63: // '?'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 61: // '='
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 59: // ';'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 57: // '9'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 55: // '7'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 53: // '5'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 51: // '3'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 49: // '1'
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
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 43: // '+'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 41: // ')'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 39: // '\''
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 37: // '%'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 35: // '#'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 33: // '!'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 31: // '\037'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 29: // '\035'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 27: // '\033'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 25: // '\031'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 23: // '\027'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 21: // '\025'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 19: // '\023'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 17: // '\021'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 15: // '\017'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 13: // '\r'
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

        case 7: // '\007'
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

        case 6: // '\006'
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

        case 3: // '\003'
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

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 3: // '\003'
        case 6: // '\006'
        case 7: // '\007'
        case 9: // '\t'
        case 11: // '\013'
        case 13: // '\r'
        case 15: // '\017'
        case 17: // '\021'
        case 19: // '\023'
        case 21: // '\025'
        case 23: // '\027'
        case 25: // '\031'
        case 27: // '\033'
        case 29: // '\035'
        case 31: // '\037'
        case 33: // '!'
        case 35: // '#'
        case 37: // '%'
        case 39: // '\''
        case 41: // ')'
        case 43: // '+'
        case 45: // '-'
        case 47: // '/'
        case 49: // '1'
        case 51: // '3'
        case 53: // '5'
        case 55: // '7'
        case 57: // '9'
        case 59: // ';'
        case 61: // '='
        case 63: // '?'
        case 64: // '@'
        case 65: // 'A'
        case 68: // 'D'
        case 69: // 'E'
        case 73: // 'I'
        default:
            return super.match2(modulemethod, obj, obj1, callcontext);

        case 76: // 'L'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 75: // 'K'
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

        case 72: // 'H'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 71: // 'G'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 70: // 'F'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 67: // 'C'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 66: // 'B'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 62: // '>'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 60: // '<'
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

        case 56: // '8'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 54: // '6'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 52: // '4'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 50: // '2'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 48: // '0'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 46: // '.'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 44: // ','
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 42: // '*'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 40: // '('
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 38: // '&'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 36: // '$'
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

        case 32: // ' '
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 30: // '\036'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 28: // '\034'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 26: // '\032'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 24: // '\030'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 22: // '\026'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 20: // '\024'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 18: // '\022'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 16: // '\020'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 14: // '\016'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 12: // '\f'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 10: // '\n'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 8: // '\b'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 5: // '\005'
            if (!(obj instanceof Pair))
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

        case 4: // '\004'
            if (!(obj instanceof Pair))
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

        case 2: // '\002'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        }
    }

    public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        int i = 0xfff40003;
        modulemethod.selector;
        JVM INSTR lookupswitch 2: default 36
    //                   72: 93
    //                   76: 52;
           goto _L1 _L2 _L3
_L1:
        i = super.match3(modulemethod, obj, obj1, obj2, callcontext);
_L5:
        return i;
_L3:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        if (obj2 instanceof Procedure)
        {
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        if (obj2 instanceof Procedure)
        {
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;
        }
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
        car = new GenericProc("car");
        GenericProc genericproc = car;
        Object aobj[] = new Object[3];
        aobj[0] = Lit0;
        aobj[1] = set$Mncar$Ex;
        aobj[2] = car$Fn1;
        genericproc.setProperties(aobj);
        cdr = new GenericProc("cdr");
        GenericProc genericproc1 = cdr;
        Object aobj1[] = new Object[3];
        aobj1[0] = Lit0;
        aobj1[1] = set$Mncdr$Ex;
        aobj1[2] = cdr$Fn2;
        genericproc1.setProperties(aobj1);
        caar = new GenericProc("caar");
        GenericProc genericproc2 = caar;
        Object aobj2[] = new Object[3];
        aobj2[0] = Lit0;
        aobj2[1] = lambda$Fn3;
        aobj2[2] = caar$Fn4;
        genericproc2.setProperties(aobj2);
        cadr = new GenericProc("cadr");
        GenericProc genericproc3 = cadr;
        Object aobj3[] = new Object[3];
        aobj3[0] = Lit0;
        aobj3[1] = lambda$Fn5;
        aobj3[2] = cadr$Fn6;
        genericproc3.setProperties(aobj3);
        cdar = new GenericProc("cdar");
        GenericProc genericproc4 = cdar;
        Object aobj4[] = new Object[3];
        aobj4[0] = Lit0;
        aobj4[1] = lambda$Fn7;
        aobj4[2] = cdar$Fn8;
        genericproc4.setProperties(aobj4);
        cddr = new GenericProc("cddr");
        GenericProc genericproc5 = cddr;
        Object aobj5[] = new Object[3];
        aobj5[0] = Lit0;
        aobj5[1] = lambda$Fn9;
        aobj5[2] = cddr$Fn10;
        genericproc5.setProperties(aobj5);
        caaar = new GenericProc("caaar");
        GenericProc genericproc6 = caaar;
        Object aobj6[] = new Object[3];
        aobj6[0] = Lit0;
        aobj6[1] = lambda$Fn11;
        aobj6[2] = caaar$Fn12;
        genericproc6.setProperties(aobj6);
        caadr = new GenericProc("caadr");
        GenericProc genericproc7 = caadr;
        Object aobj7[] = new Object[3];
        aobj7[0] = Lit0;
        aobj7[1] = lambda$Fn13;
        aobj7[2] = caadr$Fn14;
        genericproc7.setProperties(aobj7);
        cadar = new GenericProc("cadar");
        GenericProc genericproc8 = cadar;
        Object aobj8[] = new Object[3];
        aobj8[0] = Lit0;
        aobj8[1] = lambda$Fn15;
        aobj8[2] = cadar$Fn16;
        genericproc8.setProperties(aobj8);
        caddr = new GenericProc("caddr");
        GenericProc genericproc9 = caddr;
        Object aobj9[] = new Object[3];
        aobj9[0] = Lit0;
        aobj9[1] = lambda$Fn17;
        aobj9[2] = caddr$Fn18;
        genericproc9.setProperties(aobj9);
        cdaar = new GenericProc("cdaar");
        GenericProc genericproc10 = cdaar;
        Object aobj10[] = new Object[3];
        aobj10[0] = Lit0;
        aobj10[1] = lambda$Fn19;
        aobj10[2] = cdaar$Fn20;
        genericproc10.setProperties(aobj10);
        cdadr = new GenericProc("cdadr");
        GenericProc genericproc11 = cdadr;
        Object aobj11[] = new Object[3];
        aobj11[0] = Lit0;
        aobj11[1] = lambda$Fn21;
        aobj11[2] = cdadr$Fn22;
        genericproc11.setProperties(aobj11);
        cddar = new GenericProc("cddar");
        GenericProc genericproc12 = cddar;
        Object aobj12[] = new Object[3];
        aobj12[0] = Lit0;
        aobj12[1] = lambda$Fn23;
        aobj12[2] = cddar$Fn24;
        genericproc12.setProperties(aobj12);
        cdddr = new GenericProc("cdddr");
        GenericProc genericproc13 = cdddr;
        Object aobj13[] = new Object[3];
        aobj13[0] = Lit0;
        aobj13[1] = lambda$Fn25;
        aobj13[2] = cdddr$Fn26;
        genericproc13.setProperties(aobj13);
        caaaar = new GenericProc("caaaar");
        GenericProc genericproc14 = caaaar;
        Object aobj14[] = new Object[3];
        aobj14[0] = Lit0;
        aobj14[1] = lambda$Fn27;
        aobj14[2] = caaaar$Fn28;
        genericproc14.setProperties(aobj14);
        caaadr = new GenericProc("caaadr");
        GenericProc genericproc15 = caaadr;
        Object aobj15[] = new Object[3];
        aobj15[0] = Lit0;
        aobj15[1] = lambda$Fn29;
        aobj15[2] = caaadr$Fn30;
        genericproc15.setProperties(aobj15);
        caadar = new GenericProc("caadar");
        GenericProc genericproc16 = caadar;
        Object aobj16[] = new Object[3];
        aobj16[0] = Lit0;
        aobj16[1] = lambda$Fn31;
        aobj16[2] = caadar$Fn32;
        genericproc16.setProperties(aobj16);
        caaddr = new GenericProc("caaddr");
        GenericProc genericproc17 = caaddr;
        Object aobj17[] = new Object[3];
        aobj17[0] = Lit0;
        aobj17[1] = lambda$Fn33;
        aobj17[2] = caaddr$Fn34;
        genericproc17.setProperties(aobj17);
        cadaar = new GenericProc("cadaar");
        GenericProc genericproc18 = cadaar;
        Object aobj18[] = new Object[3];
        aobj18[0] = Lit0;
        aobj18[1] = lambda$Fn35;
        aobj18[2] = cadaar$Fn36;
        genericproc18.setProperties(aobj18);
        cadadr = new GenericProc("cadadr");
        GenericProc genericproc19 = cadadr;
        Object aobj19[] = new Object[3];
        aobj19[0] = Lit0;
        aobj19[1] = lambda$Fn37;
        aobj19[2] = cadadr$Fn38;
        genericproc19.setProperties(aobj19);
        caddar = new GenericProc("caddar");
        GenericProc genericproc20 = caddar;
        Object aobj20[] = new Object[3];
        aobj20[0] = Lit0;
        aobj20[1] = lambda$Fn39;
        aobj20[2] = caddar$Fn40;
        genericproc20.setProperties(aobj20);
        cadddr = new GenericProc("cadddr");
        GenericProc genericproc21 = cadddr;
        Object aobj21[] = new Object[3];
        aobj21[0] = Lit0;
        aobj21[1] = lambda$Fn41;
        aobj21[2] = cadddr$Fn42;
        genericproc21.setProperties(aobj21);
        cdaaar = new GenericProc("cdaaar");
        GenericProc genericproc22 = cdaaar;
        Object aobj22[] = new Object[3];
        aobj22[0] = Lit0;
        aobj22[1] = lambda$Fn43;
        aobj22[2] = cdaaar$Fn44;
        genericproc22.setProperties(aobj22);
        cdaadr = new GenericProc("cdaadr");
        GenericProc genericproc23 = cdaadr;
        Object aobj23[] = new Object[3];
        aobj23[0] = Lit0;
        aobj23[1] = lambda$Fn45;
        aobj23[2] = cdaadr$Fn46;
        genericproc23.setProperties(aobj23);
        cdadar = new GenericProc("cdadar");
        GenericProc genericproc24 = cdadar;
        Object aobj24[] = new Object[3];
        aobj24[0] = Lit0;
        aobj24[1] = lambda$Fn47;
        aobj24[2] = cdadar$Fn48;
        genericproc24.setProperties(aobj24);
        cdaddr = new GenericProc("cdaddr");
        GenericProc genericproc25 = cdaddr;
        Object aobj25[] = new Object[3];
        aobj25[0] = Lit0;
        aobj25[1] = lambda$Fn49;
        aobj25[2] = cdaddr$Fn50;
        genericproc25.setProperties(aobj25);
        cddaar = new GenericProc("cddaar");
        GenericProc genericproc26 = cddaar;
        Object aobj26[] = new Object[3];
        aobj26[0] = Lit0;
        aobj26[1] = lambda$Fn51;
        aobj26[2] = cddaar$Fn52;
        genericproc26.setProperties(aobj26);
        cddadr = new GenericProc("cddadr");
        GenericProc genericproc27 = cddadr;
        Object aobj27[] = new Object[3];
        aobj27[0] = Lit0;
        aobj27[1] = lambda$Fn53;
        aobj27[2] = cddadr$Fn54;
        genericproc27.setProperties(aobj27);
        cdddar = new GenericProc("cdddar");
        GenericProc genericproc28 = cdddar;
        Object aobj28[] = new Object[3];
        aobj28[0] = Lit0;
        aobj28[1] = lambda$Fn55;
        aobj28[2] = cdddar$Fn56;
        genericproc28.setProperties(aobj28);
        cddddr = new GenericProc("cddddr");
        GenericProc genericproc29 = cddddr;
        Object aobj29[] = new Object[3];
        aobj29[0] = Lit0;
        aobj29[1] = lambda$Fn57;
        aobj29[2] = cddddr$Fn58;
        genericproc29.setProperties(aobj29);
    }

    static 
    {
        Lit19 = (SimpleSymbol)(new SimpleSymbol("assoc")).readResolve();
        Lit18 = (SimpleSymbol)(new SimpleSymbol("assv")).readResolve();
        Lit17 = (SimpleSymbol)(new SimpleSymbol("assq")).readResolve();
        Lit16 = (SimpleSymbol)(new SimpleSymbol("member")).readResolve();
        Lit15 = (SimpleSymbol)(new SimpleSymbol("memv")).readResolve();
        Lit14 = (SimpleSymbol)(new SimpleSymbol("memq")).readResolve();
        Lit13 = (SimpleSymbol)(new SimpleSymbol("reverse!")).readResolve();
        Lit12 = (SimpleSymbol)(new SimpleSymbol("list?")).readResolve();
        Lit11 = (SimpleSymbol)(new SimpleSymbol("list-ref")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("list-tail")).readResolve();
        Lit9 = (SimpleSymbol)(new SimpleSymbol("reverse")).readResolve();
        Lit8 = (SimpleSymbol)(new SimpleSymbol("length")).readResolve();
        Lit7 = (SimpleSymbol)(new SimpleSymbol("set-cdr!")).readResolve();
        Lit6 = (SimpleSymbol)(new SimpleSymbol("set-car!")).readResolve();
        Lit5 = (SimpleSymbol)(new SimpleSymbol("null?")).readResolve();
        Lit4 = (SimpleSymbol)(new SimpleSymbol("cons")).readResolve();
        Lit3 = (SimpleSymbol)(new SimpleSymbol("pair?")).readResolve();
        $instance = new lists();
        lists lists1 = $instance;
        pair$Qu = new ModuleMethod(lists1, 1, Lit3, 4097);
        cons = new ModuleMethod(lists1, 2, Lit4, 8194);
        null$Qu = new ModuleMethod(lists1, 3, Lit5, 4097);
        set$Mncar$Ex = new ModuleMethod(lists1, 4, Lit6, 8194);
        set$Mncdr$Ex = new ModuleMethod(lists1, 5, Lit7, 8194);
        ModuleMethod modulemethod = new ModuleMethod(lists1, 6, "car", 4097);
        modulemethod.setProperty("source-location", "lists.scm:31");
        car$Fn1 = modulemethod;
        ModuleMethod modulemethod1 = new ModuleMethod(lists1, 7, "cdr", 4097);
        modulemethod1.setProperty("source-location", "lists.scm:36");
        cdr$Fn2 = modulemethod1;
        lambda$Fn3 = new ModuleMethod(lists1, 8, null, 8194);
        caar$Fn4 = new ModuleMethod(lists1, 9, "caar", 4097);
        lambda$Fn5 = new ModuleMethod(lists1, 10, null, 8194);
        cadr$Fn6 = new ModuleMethod(lists1, 11, "cadr", 4097);
        lambda$Fn7 = new ModuleMethod(lists1, 12, null, 8194);
        cdar$Fn8 = new ModuleMethod(lists1, 13, "cdar", 4097);
        lambda$Fn9 = new ModuleMethod(lists1, 14, null, 8194);
        cddr$Fn10 = new ModuleMethod(lists1, 15, "cddr", 4097);
        lambda$Fn11 = new ModuleMethod(lists1, 16, null, 8194);
        caaar$Fn12 = new ModuleMethod(lists1, 17, "caaar", 4097);
        lambda$Fn13 = new ModuleMethod(lists1, 18, null, 8194);
        caadr$Fn14 = new ModuleMethod(lists1, 19, "caadr", 4097);
        lambda$Fn15 = new ModuleMethod(lists1, 20, null, 8194);
        cadar$Fn16 = new ModuleMethod(lists1, 21, "cadar", 4097);
        lambda$Fn17 = new ModuleMethod(lists1, 22, null, 8194);
        caddr$Fn18 = new ModuleMethod(lists1, 23, "caddr", 4097);
        lambda$Fn19 = new ModuleMethod(lists1, 24, null, 8194);
        cdaar$Fn20 = new ModuleMethod(lists1, 25, "cdaar", 4097);
        lambda$Fn21 = new ModuleMethod(lists1, 26, null, 8194);
        cdadr$Fn22 = new ModuleMethod(lists1, 27, "cdadr", 4097);
        lambda$Fn23 = new ModuleMethod(lists1, 28, null, 8194);
        cddar$Fn24 = new ModuleMethod(lists1, 29, "cddar", 4097);
        lambda$Fn25 = new ModuleMethod(lists1, 30, null, 8194);
        cdddr$Fn26 = new ModuleMethod(lists1, 31, "cdddr", 4097);
        lambda$Fn27 = new ModuleMethod(lists1, 32, null, 8194);
        caaaar$Fn28 = new ModuleMethod(lists1, 33, "caaaar", 4097);
        lambda$Fn29 = new ModuleMethod(lists1, 34, null, 8194);
        caaadr$Fn30 = new ModuleMethod(lists1, 35, "caaadr", 4097);
        lambda$Fn31 = new ModuleMethod(lists1, 36, null, 8194);
        caadar$Fn32 = new ModuleMethod(lists1, 37, "caadar", 4097);
        lambda$Fn33 = new ModuleMethod(lists1, 38, null, 8194);
        caaddr$Fn34 = new ModuleMethod(lists1, 39, "caaddr", 4097);
        lambda$Fn35 = new ModuleMethod(lists1, 40, null, 8194);
        cadaar$Fn36 = new ModuleMethod(lists1, 41, "cadaar", 4097);
        lambda$Fn37 = new ModuleMethod(lists1, 42, null, 8194);
        cadadr$Fn38 = new ModuleMethod(lists1, 43, "cadadr", 4097);
        lambda$Fn39 = new ModuleMethod(lists1, 44, null, 8194);
        caddar$Fn40 = new ModuleMethod(lists1, 45, "caddar", 4097);
        lambda$Fn41 = new ModuleMethod(lists1, 46, null, 8194);
        cadddr$Fn42 = new ModuleMethod(lists1, 47, "cadddr", 4097);
        lambda$Fn43 = new ModuleMethod(lists1, 48, null, 8194);
        cdaaar$Fn44 = new ModuleMethod(lists1, 49, "cdaaar", 4097);
        lambda$Fn45 = new ModuleMethod(lists1, 50, null, 8194);
        cdaadr$Fn46 = new ModuleMethod(lists1, 51, "cdaadr", 4097);
        lambda$Fn47 = new ModuleMethod(lists1, 52, null, 8194);
        cdadar$Fn48 = new ModuleMethod(lists1, 53, "cdadar", 4097);
        lambda$Fn49 = new ModuleMethod(lists1, 54, null, 8194);
        cdaddr$Fn50 = new ModuleMethod(lists1, 55, "cdaddr", 4097);
        lambda$Fn51 = new ModuleMethod(lists1, 56, null, 8194);
        cddaar$Fn52 = new ModuleMethod(lists1, 57, "cddaar", 4097);
        lambda$Fn53 = new ModuleMethod(lists1, 58, null, 8194);
        cddadr$Fn54 = new ModuleMethod(lists1, 59, "cddadr", 4097);
        lambda$Fn55 = new ModuleMethod(lists1, 60, null, 8194);
        cdddar$Fn56 = new ModuleMethod(lists1, 61, "cdddar", 4097);
        lambda$Fn57 = new ModuleMethod(lists1, 62, null, 8194);
        cddddr$Fn58 = new ModuleMethod(lists1, 63, "cddddr", 4097);
        length = new ModuleMethod(lists1, 64, Lit8, 4097);
        reverse = new ModuleMethod(lists1, 65, Lit9, 4097);
        list$Mntail = new ModuleMethod(lists1, 66, Lit10, 8194);
        list$Mnref = new ModuleMethod(lists1, 67, Lit11, 8194);
        list$Qu = new ModuleMethod(lists1, 68, Lit12, 4097);
        reverse$Ex = new ModuleMethod(lists1, 69, Lit13, 4097);
        memq = new ModuleMethod(lists1, 70, Lit14, 8194);
        memv = new ModuleMethod(lists1, 71, Lit15, 8194);
        member = new ModuleMethod(lists1, 72, Lit16, 12290);
        assq = new ModuleMethod(lists1, 74, Lit17, 8194);
        assv = new ModuleMethod(lists1, 75, Lit18, 8194);
        assoc = new ModuleMethod(lists1, 76, Lit19, 12290);
        $instance.run();
    }
}
