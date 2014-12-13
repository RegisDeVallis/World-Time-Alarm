// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;
import kawa.standard.append;

// Referenced classes of package gnu.kawa.slib:
//            condition, srfi1

public class conditions extends ModuleBody
{
    public class condition.Mntype
    {

        public Object all$Mnfields;
        public Object fields;
        public Object name;
        public Object supertype;

        public String toString()
        {
            StringBuffer stringbuffer = new StringBuffer("#<condition-type ");
            stringbuffer.append(name);
            stringbuffer.append(">");
            return stringbuffer.toString();
        }

        public condition.Mntype(Object obj, Object obj1, Object obj2, Object obj3)
        {
            name = obj;
            supertype = obj1;
            fields = obj2;
            all$Mnfields = obj3;
        }
    }

    public class frame extends ModuleBody
    {

        final ModuleMethod lambda$Fn1;
        condition.Mntype type;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 1)
            {
                if (lambda2(obj))
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

        boolean lambda2(Object obj)
        {
            Object obj1 = lists.car.apply1(obj);
            condition.Mntype mntype;
            try
            {
                mntype = (condition.Mntype)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "condition-subtype?", 0, obj1);
            }
            return conditions.isConditionSubtype(mntype, type);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 1)
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
            ModuleMethod modulemethod = new ModuleMethod(this, 1, null, 4097);
            modulemethod.setProperty("source-location", "conditions.scm:166");
            lambda$Fn1 = modulemethod;
        }
    }


    public static Object $Amcondition;
    public static Object $Amerror;
    public static Object $Ammessage;
    public static Object $Amserious;
    static final Class $Lscondition$Mntype$Gr = gnu/kawa/slib/condition$Mntype;
    public static final Class $Prvt$$Lscondition$Gr = gnu/kawa/slib/condition;
    public static final ModuleMethod $Prvt$type$Mnfield$Mnalist$Mn$Grcondition;
    public static final conditions $instance;
    static final SimpleSymbol Lit0 = (SimpleSymbol)(new SimpleSymbol("&condition")).readResolve();
    static final SimpleSymbol Lit1 = (SimpleSymbol)(new SimpleSymbol("&message")).readResolve();
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SyntaxRules Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SyntaxRules Lit19;
    static final PairWithPosition Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit3 = (SimpleSymbol)(new SimpleSymbol("&serious")).readResolve();
    static final SimpleSymbol Lit4 = (SimpleSymbol)(new SimpleSymbol("&error")).readResolve();
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SyntaxRules Lit9;
    public static final Macro condition;
    public static final ModuleMethod condition$Mnhas$Mntype$Qu;
    public static final ModuleMethod condition$Mnref;
    static final Macro condition$Mntype$Mnfield$Mnalist;
    public static final ModuleMethod condition$Mntype$Qu;
    public static final ModuleMethod condition$Qu;
    public static final Macro define$Mncondition$Mntype;
    public static final ModuleMethod extract$Mncondition;
    public static final ModuleMethod make$Mncompound$Mncondition;
    public static final ModuleMethod make$Mncondition;
    public static final ModuleMethod make$Mncondition$Mntype;

    public conditions()
    {
        ModuleInfo.register(this);
    }

    static Object checkConditionTypeFieldAlist(Object obj)
    {
        Object obj1 = obj;
_L3:
        Object obj9;
        Object obj11;
        Object obj13;
        if (lists.isNull(obj1))
        {
            break MISSING_BLOCK_LABEL_267;
        }
        Object obj2 = lists.car.apply1(obj1);
        Object obj3 = lists.car.apply1(obj2);
        condition.Mntype mntype;
        Object obj4;
        Object obj5;
        Object obj6;
        try
        {
            mntype = (condition.Mntype)obj3;
        }
        catch (ClassCastException classcastexception)
        {
            WrongType wrongtype = new WrongType(classcastexception, "type", -2, obj3);
            throw wrongtype;
        }
        obj4 = lists.cdr.apply1(obj2);
        obj5 = LList.Empty;
        obj6 = obj4;
        do
        {
            if (obj6 == LList.Empty)
            {
                LList llist = LList.reverseInPlace(obj5);
                Object obj8 = mntype.all$Mnfields;
                obj9 = srfi1.lsetDifference$V(Scheme.isEq, obj8, new Object[] {
                    llist
                });
                break MISSING_BLOCK_LABEL_90;
            }
            Pair pair;
            Object obj7;
            Pair pair1;
            Object obj10;
            Object obj12;
            condition.Mntype mntype1;
            condition.Mntype mntype2;
            boolean flag;
            try
            {
                pair = (Pair)obj6;
            }
            catch (ClassCastException classcastexception1)
            {
                WrongType wrongtype1 = new WrongType(classcastexception1, "arg0", -2, obj6);
                throw wrongtype1;
            }
            obj7 = pair.getCdr();
            obj5 = Pair.make(lists.car.apply1(pair.getCar()), obj5);
            obj6 = obj7;
        } while (true);
_L1:
        {
            if (obj9 == LList.Empty)
            {
                obj1 = lists.cdr.apply1(obj1);
                continue; /* Loop/switch isn't completed */
            }
            try
            {
                pair1 = (Pair)obj9;
            }
            catch (ClassCastException classcastexception2)
            {
                WrongType wrongtype2 = new WrongType(classcastexception2, "arg0", -2, obj9);
                throw wrongtype2;
            }
            obj10 = pair1.getCar();
            obj11 = conditionTypeFieldSupertype(mntype, obj10);
            obj12 = obj;
        }
        obj13 = lists.car.apply1(lists.car.apply1(obj12));
        try
        {
            mntype1 = (condition.Mntype)obj13;
        }
        catch (ClassCastException classcastexception3)
        {
            WrongType wrongtype3 = new WrongType(classcastexception3, "condition-subtype?", 0, obj13);
            throw wrongtype3;
        }
        mntype2 = (condition.Mntype)obj11;
label0:
        {
            flag = isConditionSubtype(mntype1, mntype2);
            if (!flag)
            {
                break label0;
            }
            if (!flag)
            {
                misc.error$V("missing field in condition construction", new Object[] {
                    mntype, obj10
                });
            }
            obj9 = pair1.getCdr();
        }
          goto _L1
        obj12 = lists.cdr.apply1(obj12);
        break MISSING_BLOCK_LABEL_174;
        return Values.empty;
        ClassCastException classcastexception4;
        classcastexception4;
        WrongType wrongtype4 = new WrongType(classcastexception4, "condition-subtype?", 1, obj11);
        throw wrongtype4;
        if (true) goto _L3; else goto _L2
_L2:
    }

    static Object conditionMessage(Object obj)
    {
        condition condition1;
        Object obj1;
        condition.Mntype mntype;
        try
        {
            condition1 = (condition)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "extract-condition", 0, obj);
        }
        obj1 = $Ammessage;
        try
        {
            mntype = (condition.Mntype)obj1;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "extract-condition", 1, obj1);
        }
        return conditionRef(extractCondition(condition1, mntype), Lit5);
    }

    public static Object conditionRef(condition condition1, Object obj)
    {
        return typeFieldAlistRef(condition1.type$Mnfield$Mnalist, obj);
    }

    static Object conditionTypeFieldSupertype(condition.Mntype mntype, Object obj)
    {
_L6:
        if (mntype != Boolean.FALSE) goto _L2; else goto _L1
_L1:
        mntype = Boolean.FALSE;
_L4:
        return mntype;
_L2:
        if (lists.memq(obj, mntype.fields) != Boolean.FALSE) goto _L4; else goto _L3
_L3:
        mntype = (condition.Mntype)mntype.supertype;
        if (true) goto _L6; else goto _L5
_L5:
    }

    static Object conditionTypes(Object obj)
    {
        Object obj1 = ((condition)obj).type$Mnfield$Mnalist;
        Object obj2 = LList.Empty;
        do
        {
            if (obj1 == LList.Empty)
            {
                return LList.reverseInPlace(obj2);
            }
            Pair pair;
            Object obj3;
            try
            {
                pair = (Pair)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "arg0", -2, obj1);
            }
            obj3 = pair.getCdr();
            obj2 = Pair.make(lists.car.apply1(pair.getCar()), obj2);
            obj1 = obj3;
        } while (true);
    }

    public static condition extractCondition(condition condition1, condition.Mntype mntype)
    {
        frame frame1 = new frame();
        frame1.type = mntype;
        Object obj = srfi1.find(frame1.Fn1, condition1.type$Mnfield$Mnalist);
        if (obj == Boolean.FALSE)
        {
            Object aobj[] = new Object[2];
            aobj[0] = condition1;
            aobj[1] = frame1.type;
            misc.error$V("extract-condition: invalid condition type", aobj);
        }
        condition.Mntype mntype1 = frame1.type;
        Object obj1 = frame1.type.all$Mnfields;
        Object obj2 = LList.Empty;
        do
        {
            if (obj1 == LList.Empty)
            {
                return new condition(LList.list1(lists.cons(mntype1, LList.reverseInPlace(obj2))));
            }
            Pair pair;
            Object obj3;
            try
            {
                pair = (Pair)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "arg0", -2, obj1);
            }
            obj3 = pair.getCdr();
            obj2 = Pair.make(lists.assq(pair.getCar(), lists.cdr.apply1(obj)), obj2);
            obj1 = obj3;
        } while (true);
    }

    public static boolean isCondition(Object obj)
    {
        return obj instanceof condition;
    }

    public static boolean isConditionHasType(Object obj, condition.Mntype mntype)
    {
        Object obj1 = conditionTypes(obj);
        do
        {
            Object obj2 = lists.car.apply1(obj1);
            condition.Mntype mntype1;
            boolean flag;
            try
            {
                mntype1 = (condition.Mntype)obj2;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "condition-subtype?", 0, obj2);
            }
            flag = isConditionSubtype(mntype1, mntype);
            if (flag)
            {
                return flag;
            }
            obj1 = lists.cdr.apply1(obj1);
        } while (true);
    }

    static boolean isConditionSubtype(condition.Mntype mntype, condition.Mntype mntype1)
    {
        do
        {
            if (mntype == Boolean.FALSE)
            {
                return false;
            }
            if (mntype == mntype1)
            {
                return true;
            }
            mntype = (condition.Mntype)mntype.supertype;
        } while (true);
    }

    public static boolean isConditionType(Object obj)
    {
        return obj instanceof condition.Mntype;
    }

    static boolean isError(Object obj)
    {
        boolean flag = isCondition(obj);
        if (flag)
        {
            Object obj1 = $Amerror;
            condition.Mntype mntype;
            try
            {
                mntype = (condition.Mntype)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "condition-has-type?", 1, obj1);
            }
            flag = isConditionHasType(obj, mntype);
        }
        return flag;
    }

    static boolean isMessageCondition(Object obj)
    {
        boolean flag = isCondition(obj);
        if (flag)
        {
            Object obj1 = $Ammessage;
            condition.Mntype mntype;
            try
            {
                mntype = (condition.Mntype)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "condition-has-type?", 1, obj1);
            }
            flag = isConditionHasType(obj, mntype);
        }
        return flag;
    }

    static boolean isSeriousCondition(Object obj)
    {
        boolean flag = isCondition(obj);
        if (flag)
        {
            Object obj1 = $Amserious;
            condition.Mntype mntype;
            try
            {
                mntype = (condition.Mntype)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "condition-has-type?", 1, obj1);
            }
            flag = isConditionHasType(obj, mntype);
        }
        return flag;
    }

    public static Object lambda1label(Object obj)
    {
        if (lists.isNull(obj))
        {
            return LList.Empty;
        } else
        {
            return lists.cons(lists.cons(lists.car.apply1(obj), lists.cadr.apply1(obj)), lambda1label(lists.cddr.apply1(obj)));
        }
    }

    public static condition makeCompoundCondition$V(Object obj, Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        gnu.kawa.functions.Apply apply = Scheme.apply;
        append append1 = append.append;
        Object obj1 = lists.cons(obj, llist);
        Object obj2 = LList.Empty;
        do
        {
            if (obj1 == LList.Empty)
            {
                return new condition(apply.apply2(append1, LList.reverseInPlace(obj2)));
            }
            Pair pair;
            Object obj3;
            try
            {
                pair = (Pair)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "arg0", -2, obj1);
            }
            obj3 = pair.getCdr();
            obj2 = Pair.make(Scheme.applyToArgs.apply2(condition$Mntype$Mnfield$Mnalist, pair.getCar()), obj2);
            obj1 = obj3;
        } while (true);
    }

    public static condition makeCondition$V(condition.Mntype mntype, Object aobj[])
    {
        Object obj = lambda1label(LList.makeList(aobj, 0));
        gnu.kawa.functions.IsEq iseq = Scheme.isEq;
        Object aobj1[] = new Object[2];
        aobj1[0] = mntype.all$Mnfields;
        Object obj1 = LList.Empty;
        Object obj2 = obj;
        do
        {
            if (obj2 == LList.Empty)
            {
                aobj1[1] = LList.reverseInPlace(obj1);
                if (srfi1.lset$Eq$V(iseq, aobj1) == Boolean.FALSE)
                {
                    misc.error$V("condition fields don't match condition type", new Object[0]);
                }
                return new condition(LList.list1(lists.cons(mntype, obj)));
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

    public static condition.Mntype makeConditionType(Symbol symbol, condition.Mntype mntype, Object obj)
    {
        if (!lists.isNull(srfi1.lsetIntersection$V(Scheme.isEq, mntype.all$Mnfields, new Object[] {
    obj
})))
        {
            misc.error$V("duplicate field name", new Object[0]);
        }
        Object aobj[] = new Object[2];
        aobj[0] = mntype.all$Mnfields;
        aobj[1] = obj;
        return new condition.Mntype(symbol, mntype, obj, append.append$V(aobj));
    }

    public static condition typeFieldAlist$To$Condition(Object obj)
    {
        Object obj1;
        Object obj2;
        LList llist = LList.Empty;
        obj1 = obj;
        obj2 = llist;
_L1:
        Object obj6;
        if (obj1 == LList.Empty)
        {
            return new condition(LList.reverseInPlace(obj2));
        }
        Pair pair;
        Object obj3;
        Object obj4;
        Object obj5;
        Object obj7;
        Pair pair1;
        Object obj8;
        Object obj9;
        Object obj10;
        try
        {
            pair = (Pair)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "arg0", -2, obj1);
        }
        obj3 = pair.getCdr();
        obj4 = pair.getCar();
        obj5 = lists.car.apply1(obj4);
        obj6 = ((condition.Mntype)lists.car.apply1(obj4)).all$Mnfields;
        obj7 = LList.Empty;
label0:
        {
            if (obj6 != LList.Empty)
            {
                break label0;
            }
            obj2 = Pair.make(lists.cons(obj5, LList.reverseInPlace(obj7)), obj2);
            obj1 = obj3;
        }
          goto _L1
        try
        {
            pair1 = (Pair)obj6;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "arg0", -2, obj6);
        }
        obj8 = pair1.getCdr();
        obj9 = pair1.getCar();
        obj10 = lists.assq(obj9, lists.cdr.apply1(obj4));
        if (obj10 == Boolean.FALSE)
        {
            obj10 = lists.cons(obj9, typeFieldAlistRef(obj, obj9));
        }
        obj7 = Pair.make(obj10, obj7);
        obj6 = obj8;
        break MISSING_BLOCK_LABEL_78;
    }

    static Object typeFieldAlistRef(Object obj, Object obj1)
    {
        do
        {
            if (lists.isNull(obj))
            {
                return misc.error$V("type-field-alist-ref: field not found", new Object[] {
                    obj, obj1
                });
            }
            Object obj2 = lists.assq(obj1, lists.cdr.apply1(lists.car.apply1(obj)));
            if (obj2 != Boolean.FALSE)
            {
                return lists.cdr.apply1(obj2);
            }
            obj = lists.cdr.apply1(obj);
        } while (true);
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply1(modulemethod, obj);

        case 2: // '\002'
            if (isConditionType(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 4: // '\004'
            if (isCondition(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 10: // '\n'
            return typeFieldAlist$To$Condition(obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        case 8: // '\b'
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 6: // '\006'
            condition condition1;
            condition.Mntype mntype;
            condition condition2;
            condition.Mntype mntype1;
            try
            {
                mntype1 = (condition.Mntype)obj1;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "condition-has-type?", 2, obj1);
            }
            if (isConditionHasType(obj, mntype1))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 7: // '\007'
            try
            {
                condition2 = (condition)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "condition-ref", 1, obj);
            }
            return conditionRef(condition2, obj1);

        case 9: // '\t'
            break;
        }
        try
        {
            condition1 = (condition)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "extract-condition", 1, obj);
        }
        try
        {
            mntype = (condition.Mntype)obj1;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "extract-condition", 2, obj1);
        }
        return extractCondition(condition1, mntype);
    }

    public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
    {
        if (modulemethod.selector == 3)
        {
            Symbol symbol;
            condition.Mntype mntype;
            try
            {
                symbol = (Symbol)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "make-condition-type", 1, obj);
            }
            try
            {
                mntype = (condition.Mntype)obj1;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "make-condition-type", 2, obj1);
            }
            return makeConditionType(symbol, mntype, obj2);
        } else
        {
            return super.apply3(modulemethod, obj, obj1, obj2);
        }
    }

    public Object applyN(ModuleMethod modulemethod, Object aobj[])
    {
        switch (modulemethod.selector)
        {
        case 6: // '\006'
        case 7: // '\007'
        default:
            return super.applyN(modulemethod, aobj);

        case 5: // '\005'
            Object obj1 = aobj[0];
            Object obj;
            int i;
            Object aobj1[];
            condition.Mntype mntype;
            int j;
            Object aobj2[];
            try
            {
                mntype = (condition.Mntype)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "make-condition", 1, obj1);
            }
            j = -1 + aobj.length;
            aobj2 = new Object[j];
            do
            {
                if (--j < 0)
                {
                    return makeCondition$V(mntype, aobj2);
                }
                aobj2[j] = aobj[j + 1];
            } while (true);

        case 8: // '\b'
            obj = aobj[0];
            i = -1 + aobj.length;
            aobj1 = new Object[i];
            break;
        }
        do
        {
            if (--i < 0)
            {
                return makeCompoundCondition$V(obj, aobj1);
            }
            aobj1[i] = aobj[i + 1];
        } while (true);
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 10: // '\n'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 4: // '\004'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 2: // '\002'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        int i = 0xfff40001;
        modulemethod.selector;
        JVM INSTR tableswitch 6 9: default 40
    //                   6 131
    //                   7 98
    //                   8 40
    //                   9 54;
           goto _L1 _L2 _L3 _L1 _L4
_L1:
        i = super.match2(modulemethod, obj, obj1, callcontext);
_L6:
        return i;
_L4:
        if (!(obj instanceof condition)) goto _L6; else goto _L5
_L5:
        callcontext.value1 = obj;
        if (!(obj1 instanceof condition.Mntype))
        {
            return 0xfff40002;
        } else
        {
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        }
_L3:
        if (!(obj instanceof condition)) goto _L6; else goto _L7
_L7:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.proc = modulemethod;
        callcontext.pc = 2;
        return 0;
_L2:
        callcontext.value1 = obj;
        if (!(obj1 instanceof condition.Mntype))
        {
            return 0xfff40002;
        } else
        {
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        }
    }

    public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        if (modulemethod.selector == 3)
        {
            if (!(obj instanceof Symbol))
            {
                return 0xfff40001;
            }
            callcontext.value1 = obj;
            if (!(obj1 instanceof condition.Mntype))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
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
        case 6: // '\006'
        case 7: // '\007'
        default:
            return super.matchN(modulemethod, aobj, callcontext);

        case 8: // '\b'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 5: // '\005'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
        $Amcondition = new condition.Mntype(Lit0, Boolean.FALSE, LList.Empty, LList.Empty);
        SimpleSymbol simplesymbol = Lit1;
        Object obj = $Amcondition;
        condition.Mntype mntype;
        SimpleSymbol simplesymbol1;
        Object obj1;
        condition.Mntype mntype1;
        SimpleSymbol simplesymbol2;
        Object obj2;
        condition.Mntype mntype2;
        try
        {
            mntype = (condition.Mntype)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "make-condition-type", 1, obj);
        }
        $Ammessage = makeConditionType(simplesymbol, mntype, Lit2);
        simplesymbol1 = Lit3;
        obj1 = $Amcondition;
        try
        {
            mntype1 = (condition.Mntype)obj1;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "make-condition-type", 1, obj1);
        }
        $Amserious = makeConditionType(simplesymbol1, mntype1, LList.Empty);
        simplesymbol2 = Lit4;
        obj2 = $Amserious;
        try
        {
            mntype2 = (condition.Mntype)obj2;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "make-condition-type", 1, obj2);
        }
        $Amerror = makeConditionType(simplesymbol2, mntype2, LList.Empty);
    }

    static 
    {
        Lit22 = (SimpleSymbol)(new SimpleSymbol("thing")).readResolve();
        Lit21 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
        Lit20 = (SimpleSymbol)(new SimpleSymbol("type-field-alist->condition")).readResolve();
        Object aobj[] = new Object[1];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("condition")).readResolve();
        Lit18 = simplesymbol;
        aobj[0] = simplesymbol;
        SyntaxRule asyntaxrule[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern = new SyntaxPattern("\f\030]\f\007-\f\017\f\027\b\b\020\b\000\030\b", new Object[0], 3);
        Object aobj1[] = new Object[4];
        aobj1[0] = Lit20;
        aobj1[1] = (SimpleSymbol)(new SimpleSymbol("list")).readResolve();
        aobj1[2] = (SimpleSymbol)(new SimpleSymbol("cons")).readResolve();
        aobj1[3] = Lit21;
        asyntaxrule[0] = new SyntaxRule(syntaxpattern, "\003\005\005", "\021\030\004\b\021\030\f\b\005\021\030\024\t\003\b\021\030\f\b\r\021\030\024)\021\030\034\b\013\b\023", aobj1, 2);
        Lit19 = new SyntaxRules(aobj, asyntaxrule, 3);
        Lit17 = (SimpleSymbol)(new SimpleSymbol("extract-condition")).readResolve();
        Lit16 = (SimpleSymbol)(new SimpleSymbol("make-compound-condition")).readResolve();
        Lit15 = (SimpleSymbol)(new SimpleSymbol("condition-ref")).readResolve();
        Object aobj2[] = new Object[1];
        SimpleSymbol simplesymbol1 = (SimpleSymbol)(new SimpleSymbol("condition-type-field-alist")).readResolve();
        Lit13 = simplesymbol1;
        aobj2[0] = simplesymbol1;
        SyntaxRule asyntaxrule1[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern1 = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
        Object aobj3[] = new Object[3];
        aobj3[0] = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("$lookup$")).readResolve(), Pair.make((SimpleSymbol)(new SimpleSymbol("*")).readResolve(), Pair.make(Pair.make((SimpleSymbol)(new SimpleSymbol("quasiquote")).readResolve(), Pair.make((SimpleSymbol)(new SimpleSymbol(".type-field-alist")).readResolve(), LList.Empty)), LList.Empty)), "conditions.scm", 0x8e007);
        aobj3[1] = (SimpleSymbol)(new SimpleSymbol("as")).readResolve();
        aobj3[2] = (SimpleSymbol)(new SimpleSymbol("<condition>")).readResolve();
        asyntaxrule1[0] = new SyntaxRule(syntaxpattern1, "\001", "\021\030\004\b\021\030\f\021\030\024\b\003", aobj3, 0);
        Lit14 = new SyntaxRules(aobj2, asyntaxrule1, 1);
        Lit12 = (SimpleSymbol)(new SimpleSymbol("condition-has-type?")).readResolve();
        Lit11 = (SimpleSymbol)(new SimpleSymbol("make-condition")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("condition?")).readResolve();
        Object aobj4[] = new Object[1];
        SimpleSymbol simplesymbol2 = (SimpleSymbol)(new SimpleSymbol("define-condition-type")).readResolve();
        Lit8 = simplesymbol2;
        aobj4[0] = simplesymbol2;
        SyntaxRule asyntaxrule2[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern2 = new SyntaxPattern("\f\030\f\007\f\017\f\027-\f\037\f'\b\030\020\b", new Object[0], 5);
        Object aobj5[] = new Object[13];
        aobj5[0] = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
        aobj5[1] = (SimpleSymbol)(new SimpleSymbol("define")).readResolve();
        SimpleSymbol simplesymbol3 = (SimpleSymbol)(new SimpleSymbol("make-condition-type")).readResolve();
        Lit7 = simplesymbol3;
        aobj5[2] = simplesymbol3;
        aobj5[3] = Lit21;
        aobj5[4] = PairWithPosition.make(Lit22, LList.Empty, "conditions.scm", 0x5001c);
        aobj5[5] = (SimpleSymbol)(new SimpleSymbol("and")).readResolve();
        aobj5[6] = PairWithPosition.make(Lit10, PairWithPosition.make(Lit22, LList.Empty, "conditions.scm", 0x5101b), "conditions.scm", 0x5100f);
        aobj5[7] = Lit12;
        aobj5[8] = Lit22;
        aobj5[9] = PairWithPosition.make(Lit18, LList.Empty, "conditions.scm", 0x5301c);
        aobj5[10] = Lit15;
        aobj5[11] = Lit17;
        aobj5[12] = Lit18;
        asyntaxrule2[0] = new SyntaxRule(syntaxpattern2, "\001\001\001\003\003", "\021\030\004\311\021\030\f\t\003\b\021\030\024)\021\030\034\b\003\t\013\b\021\030\034\b\b\035\033\301\021\030\f!\t\023\030$\b\021\030,\021\0304\b\021\030<\021\030D\b\003\b%\021\030\f!\t#\030L\b\021\030TA\021\030\\\021\030d\b\003\b\021\030\034\b\033", aobj5, 1);
        Lit9 = new SyntaxRules(aobj4, asyntaxrule2, 5);
        Lit6 = (SimpleSymbol)(new SimpleSymbol("condition-type?")).readResolve();
        Lit5 = (SimpleSymbol)(new SimpleSymbol("message")).readResolve();
        Lit2 = PairWithPosition.make(Lit5, LList.Empty, "conditions.scm", 0xe2003);
        $instance = new conditions();
        conditions conditions1 = $instance;
        condition$Mntype$Qu = new ModuleMethod(conditions1, 2, Lit6, 4097);
        make$Mncondition$Mntype = new ModuleMethod(conditions1, 3, Lit7, 12291);
        define$Mncondition$Mntype = Macro.make(Lit8, Lit9, $instance);
        condition$Qu = new ModuleMethod(conditions1, 4, Lit10, 4097);
        make$Mncondition = new ModuleMethod(conditions1, 5, Lit11, -4095);
        condition$Mnhas$Mntype$Qu = new ModuleMethod(conditions1, 6, Lit12, 8194);
        condition$Mntype$Mnfield$Mnalist = Macro.make(Lit13, Lit14, $instance);
        condition$Mnref = new ModuleMethod(conditions1, 7, Lit15, 8194);
        make$Mncompound$Mncondition = new ModuleMethod(conditions1, 8, Lit16, -4095);
        extract$Mncondition = new ModuleMethod(conditions1, 9, Lit17, 8194);
        condition = Macro.make(Lit18, Lit19, $instance);
        $Prvt$type$Mnfield$Mnalist$Mn$Grcondition = new ModuleMethod(conditions1, 10, Lit20, 4097);
        $instance.run();
    }
}
