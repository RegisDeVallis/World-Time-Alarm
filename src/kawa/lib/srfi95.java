// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.standard.Scheme;
import kawa.standard.append;

// Referenced classes of package kawa.lib:
//            lists, vectors, numbers

public class srfi95 extends ModuleBody
{
    public class frame extends ModuleBody
    {

        Object key;
        Object less$Qu;

        public Object lambda1loop(Object obj, Object obj1, Object obj2, Object obj3, Object obj4, Object obj5)
        {
            if (Scheme.applyToArgs.apply3(less$Qu, obj4, obj1) != Boolean.FALSE)
            {
                if (lists.isNull(obj5))
                {
                    return lists.cons(obj3, lists.cons(obj, obj2));
                } else
                {
                    return lists.cons(obj3, lambda1loop(obj, obj1, obj2, lists.car.apply1(obj5), Scheme.applyToArgs.apply2(key, lists.car.apply1(obj5)), lists.cdr.apply1(obj5)));
                }
            }
            if (lists.isNull(obj2))
            {
                return lists.cons(obj, lists.cons(obj3, obj5));
            } else
            {
                return lists.cons(obj, lambda1loop(lists.car.apply1(obj2), Scheme.applyToArgs.apply2(key, lists.car.apply1(obj2)), lists.cdr.apply1(obj2), obj3, obj4, obj5));
            }
        }

        public frame()
        {
        }
    }

    public class frame0 extends ModuleBody
    {

        Object keyer;
        Object less$Qu;
        Object seq;

        public Object lambda2step(Object obj)
        {
            if (Scheme.numGrt.apply2(obj, srfi95.Lit1) != Boolean.FALSE)
            {
                Object obj7 = DivideOp.quotient.apply2(obj, srfi95.Lit1);
                return srfi95.sort$ClMerge$Ex(lambda2step(obj7), lambda2step(AddOp.$Mn.apply2(obj, obj7)), less$Qu, keyer);
            }
            if (Scheme.numEqu.apply2(obj, srfi95.Lit1) != Boolean.FALSE)
            {
                Object obj2 = lists.car.apply1(seq);
                Object obj3 = lists.cadr.apply1(seq);
                Object obj4 = seq;
                seq = lists.cddr.apply1(seq);
                Object obj5;
                if (Scheme.applyToArgs.apply3(less$Qu, Scheme.applyToArgs.apply2(keyer, obj3), Scheme.applyToArgs.apply2(keyer, obj2)) != Boolean.FALSE)
                {
                    Pair pair;
                    Pair pair1;
                    Pair pair2;
                    Object obj6;
                    Pair pair3;
                    try
                    {
                        pair2 = (Pair)obj4;
                    }
                    catch (ClassCastException classcastexception2)
                    {
                        throw new WrongType(classcastexception2, "set-car!", 1, obj4);
                    }
                    lists.setCar$Ex(pair2, obj3);
                    obj6 = lists.cdr.apply1(obj4);
                    try
                    {
                        pair3 = (Pair)obj6;
                    }
                    catch (ClassCastException classcastexception3)
                    {
                        throw new WrongType(classcastexception3, "set-car!", 1, obj6);
                    }
                    lists.setCar$Ex(pair3, obj2);
                }
                obj5 = lists.cdr.apply1(obj4);
                try
                {
                    pair1 = (Pair)obj5;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "set-cdr!", 1, obj5);
                }
                lists.setCdr$Ex(pair1, LList.Empty);
                return obj4;
            }
            if (Scheme.numEqu.apply2(obj, srfi95.Lit2) != Boolean.FALSE)
            {
                Object obj1 = seq;
                seq = lists.cdr.apply1(seq);
                try
                {
                    pair = (Pair)obj1;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "set-cdr!", 1, obj1);
                }
                lists.setCdr$Ex(pair, LList.Empty);
                return obj1;
            } else
            {
                return LList.Empty;
            }
        }

        public frame0()
        {
        }
    }

    public class frame1 extends ModuleBody
    {

        Object key;
        Object less$Qu;

        public Object lambda3loop(Object obj, Object obj1, Object obj2, Object obj3, Object obj4)
        {
            if (Scheme.applyToArgs.apply3(less$Qu, obj4, obj2) != Boolean.FALSE)
            {
                Pair pair2;
                try
                {
                    pair2 = (Pair)obj;
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "set-cdr!", 1, obj);
                }
                lists.setCdr$Ex(pair2, obj3);
                if (lists.isNull(lists.cdr.apply1(obj3)))
                {
                    Pair pair;
                    Pair pair1;
                    Pair pair3;
                    try
                    {
                        pair3 = (Pair)obj3;
                    }
                    catch (ClassCastException classcastexception3)
                    {
                        throw new WrongType(classcastexception3, "set-cdr!", 1, obj3);
                    }
                    lists.setCdr$Ex(pair3, obj1);
                    return Values.empty;
                } else
                {
                    return lambda3loop(obj3, obj1, obj2, lists.cdr.apply1(obj3), Scheme.applyToArgs.apply2(key, lists.cadr.apply1(obj3)));
                }
            }
            try
            {
                pair = (Pair)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "set-cdr!", 1, obj);
            }
            lists.setCdr$Ex(pair, obj1);
            if (lists.isNull(lists.cdr.apply1(obj1)))
            {
                try
                {
                    pair1 = (Pair)obj1;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "set-cdr!", 1, obj1);
                }
                lists.setCdr$Ex(pair1, obj3);
                return Values.empty;
            } else
            {
                return lambda3loop(obj1, lists.cdr.apply1(obj1), Scheme.applyToArgs.apply2(key, lists.cadr.apply1(obj1)), obj3, obj4);
            }
        }

        public frame1()
        {
        }
    }


    public static final ModuleMethod $Pcsort$Mnlist;
    public static final ModuleMethod $Pcsort$Mnvector;
    public static final ModuleMethod $Pcvector$Mnsort$Ex;
    public static final srfi95 $instance;
    static final IntNum Lit0 = IntNum.make(-1);
    static final IntNum Lit1 = IntNum.make(2);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final IntNum Lit2 = IntNum.make(1);
    static final IntNum Lit3 = IntNum.make(0);
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    static final ModuleMethod identity;
    public static final ModuleMethod merge;
    public static final ModuleMethod merge$Ex;
    public static final ModuleMethod sort;
    public static final ModuleMethod sort$Ex;
    public static final ModuleMethod sorted$Qu;

    public static Object $PcSortList(Object obj, Object obj1, Object obj2)
    {
        frame0 frame0_1;
        Object obj5;
        Object obj7;
        frame0_1 = new frame0();
        frame0_1.seq = obj;
        frame0_1.Qu = obj1;
        frame0_1.keyer = Special.undefined;
        Object obj3;
        LList llist1;
        if (obj2 != Boolean.FALSE)
        {
            obj3 = lists.car;
        } else
        {
            obj3 = identity;
        }
        frame0_1.keyer = obj3;
        if (obj2 == Boolean.FALSE) goto _L2; else goto _L1
_L1:
        obj5 = frame0_1.seq;
_L4:
        Object obj4;
        if (lists.isNull(obj5))
        {
            Object obj6 = frame0_1.seq;
            LList llist;
            ClassCastException classcastexception1;
            Pair pair;
            Pair pair1;
            try
            {
                llist1 = (LList)obj6;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "length", 1, obj6);
            }
            frame0_1.seq = frame0_1.lambda2step(Integer.valueOf(lists.length(llist1)));
            obj7 = frame0_1.seq;
            break MISSING_BLOCK_LABEL_99;
        } else
        {
            try
            {
                pair = (Pair)obj5;
            }
            // Misplaced declaration of an exception variable
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "set-car!", 1, obj5);
            }
            lists.setCar$Ex(pair, lists.cons(Scheme.applyToArgs.apply2(obj2, lists.car.apply1(obj5)), lists.car.apply1(obj5)));
            obj5 = lists.cdr.apply1(obj5);
            continue; /* Loop/switch isn't completed */
        }
        do
        {
            if (lists.isNull(obj7))
            {
                return frame0_1.seq;
            }
            try
            {
                pair1 = (Pair)obj7;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "set-car!", 1, obj7);
            }
            lists.setCar$Ex(pair1, lists.cdar.apply1(obj7));
            obj7 = lists.cdr.apply1(obj7);
        } while (true);
_L2:
        obj4 = frame0_1.seq;
        llist = (LList)obj4;
        return frame0_1.lambda2step(Integer.valueOf(lists.length(llist)));
        ClassCastException classcastexception;
        classcastexception;
        throw new WrongType(classcastexception, "length", 1, obj4);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public static void $PcSortVector(Sequence sequence, Object obj)
    {
        $PcSortVector(sequence, obj, Boolean.FALSE);
    }

    public static void $PcSortVector(Sequence sequence, Object obj, Object obj1)
    {
        gnu.lists.FVector fvector = vectors.makeVector(sequence.size());
        Object obj2 = $PcSortList(rank$Mn1Array$To$List(sequence), obj, obj1);
        Object obj3 = Lit3;
        while (!lists.isNull(obj2)) 
        {
            int i;
            try
            {
                i = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "vector-set!", 2, obj3);
            }
            vectors.vectorSet$Ex(fvector, i, lists.car.apply1(obj2));
            obj2 = lists.cdr.apply1(obj2);
            obj3 = AddOp.$Pl.apply2(obj3, Lit2);
        }
    }

    public static Object $PcVectorSort$Ex(Sequence sequence, Object obj, Object obj1)
    {
        Object obj2 = $PcSortList(rank$Mn1Array$To$List(sequence), obj, obj1);
        for (Object obj3 = Lit3; !lists.isNull(obj2); obj3 = AddOp.$Pl.apply2(obj3, Lit2))
        {
            sequence.set(((Number)obj3).intValue(), lists.car.apply1(obj2));
            obj2 = lists.cdr.apply1(obj2);
        }

        return sequence;
    }

    public srfi95()
    {
        ModuleInfo.register(this);
    }

    static Object identity(Object obj)
    {
        return obj;
    }

    public static Object isSorted(Object obj, Object obj1)
    {
        return isSorted(obj, obj1, identity);
    }

    public static Object isSorted(Object obj, Object obj1, Object obj2)
    {
        if (lists.isNull(obj))
        {
            return Boolean.TRUE;
        }
        if (obj instanceof Sequence)
        {
            Sequence sequence;
            int k;
            boolean flag1;
            Object obj7;
            Object obj8;
            try
            {
                sequence = (Sequence)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "arr", -2, obj);
            }
            k = -1 + sequence.size();
            if (k <= 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
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
            obj7 = Integer.valueOf(k - 1);
            obj8 = Scheme.applyToArgs.apply2(obj2, sequence.get(k));
            do
            {
                Object obj3;
                Object obj4;
                boolean flag;
                Object obj5;
                Boolean boolean1;
                int i;
                int j;
                gnu.math.RealNum realnum;
                boolean flag2;
                gnu.kawa.functions.ApplyToArgs applytoargs;
                int l;
                Object obj9;
                Object obj10;
                try
                {
                    realnum = LangObjType.coerceRealNum(obj7);
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "negative?", 1, obj7);
                }
                flag2 = numbers.isNegative(realnum);
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
                applytoargs = Scheme.applyToArgs;
                try
                {
                    l = ((Number)obj7).intValue();
                }
                catch (ClassCastException classcastexception3)
                {
                    throw new WrongType(classcastexception3, "gnu.lists.Sequence.get(int)", 2, obj7);
                }
                obj9 = applytoargs.apply2(obj2, sequence.get(l));
                obj10 = Scheme.applyToArgs.apply3(obj1, obj9, obj8);
                if (obj10 != Boolean.FALSE)
                {
                    obj7 = AddOp.$Pl.apply2(Lit0, obj7);
                    obj8 = obj9;
                } else
                {
                    return obj10;
                }
            } while (true);
        }
        if (lists.isNull(lists.cdr.apply1(obj)))
        {
            return Boolean.TRUE;
        }
        obj3 = Scheme.applyToArgs.apply2(obj2, lists.car.apply1(obj));
        obj4 = lists.cdr.apply1(obj);
        do
        {
            flag = lists.isNull(obj4);
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
            obj5 = Scheme.applyToArgs.apply2(obj2, lists.car.apply1(obj4));
            Object obj6 = Scheme.applyToArgs.apply3(obj1, obj5, obj3);
            try
            {
                boolean1 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj6);
            }
            if (obj6 != boolean1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            j = 1 & i + 1;
            if (j != 0)
            {
                obj4 = lists.cdr.apply1(obj4);
                obj3 = obj5;
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

    public static Object merge(Object obj, Object obj1, Object obj2)
    {
        return merge(obj, obj1, obj2, identity);
    }

    public static Object merge(Object obj, Object obj1, Object obj2, Object obj3)
    {
        frame frame2 = new frame();
        frame2.Qu = obj2;
        frame2.key = obj3;
        if (lists.isNull(obj))
        {
            return obj1;
        }
        if (lists.isNull(obj1))
        {
            return obj;
        } else
        {
            return frame2.lambda1loop(lists.car.apply1(obj), Scheme.applyToArgs.apply2(frame2.key, lists.car.apply1(obj)), lists.cdr.apply1(obj), lists.car.apply1(obj1), Scheme.applyToArgs.apply2(frame2.key, lists.car.apply1(obj1)), lists.cdr.apply1(obj1));
        }
    }

    public static Object merge$Ex(Object obj, Object obj1, Object obj2)
    {
        return merge$Ex(obj, obj1, obj2, identity);
    }

    public static Object merge$Ex(Object obj, Object obj1, Object obj2, Object obj3)
    {
        return sort$ClMerge$Ex(obj, obj1, obj2, obj3);
    }

    static Object rank$Mn1Array$To$List(Sequence sequence)
    {
        int i = -1 + sequence.size();
        Object obj = LList.Empty;
        do
        {
            if (i < 0)
            {
                return obj;
            }
            obj = lists.cons(sequence.get(i), obj);
            i--;
        } while (true);
    }

    public static Object sort(Sequence sequence, Object obj)
    {
        return sort(sequence, obj, Boolean.FALSE);
    }

    public static Object sort(Sequence sequence, Object obj, Object obj1)
    {
        if (lists.isList(sequence))
        {
            Object aobj[] = new Object[2];
            aobj[0] = sequence;
            aobj[1] = LList.Empty;
            return $PcSortList(append.append$V(aobj), obj, obj1);
        } else
        {
            $PcSortVector(sequence, obj, obj1);
            return Values.empty;
        }
    }

    static Object sort$ClMerge$Ex(Object obj, Object obj1, Object obj2, Object obj3)
    {
        frame1 frame1_1 = new frame1();
        frame1_1.Qu = obj2;
        frame1_1.key = obj3;
        if (lists.isNull(obj))
        {
            return obj1;
        }
        if (lists.isNull(obj1))
        {
            return obj;
        }
        Object obj4 = Scheme.applyToArgs.apply2(frame1_1.key, lists.car.apply1(obj));
        Object obj5 = Scheme.applyToArgs.apply2(frame1_1.key, lists.car.apply1(obj1));
        if (Scheme.applyToArgs.apply3(frame1_1.Qu, obj5, obj4) != Boolean.FALSE)
        {
            if (lists.isNull(lists.cdr.apply1(obj1)))
            {
                Pair pair;
                Pair pair1;
                try
                {
                    pair1 = (Pair)obj1;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "set-cdr!", 1, obj1);
                }
                lists.setCdr$Ex(pair1, obj);
                return obj1;
            } else
            {
                frame1_1.lambda3loop(obj1, obj, obj4, lists.cdr.apply1(obj1), Scheme.applyToArgs.apply2(frame1_1.key, lists.cadr.apply1(obj1)));
                return obj1;
            }
        }
        if (lists.isNull(lists.cdr.apply1(obj)))
        {
            try
            {
                pair = (Pair)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "set-cdr!", 1, obj);
            }
            lists.setCdr$Ex(pair, obj1);
        } else
        {
            frame1_1.lambda3loop(obj, lists.cdr.apply1(obj), Scheme.applyToArgs.apply2(frame1_1.key, lists.cadr.apply1(obj)), obj1, obj5);
        }
        return obj;
    }

    public static Object sort$Ex(Sequence sequence, Object obj)
    {
        return sort$Ex(sequence, obj, Boolean.FALSE);
    }

    public static Object sort$Ex(Sequence sequence, Object obj, Object obj1)
    {
        if (lists.isList(sequence))
        {
            Object obj2 = $PcSortList(sequence, obj, obj1);
            if (obj2 != sequence)
            {
                Object obj3;
                for (obj3 = obj2; lists.cdr.apply1(obj3) != sequence; obj3 = lists.cdr.apply1(obj3)) { }
                Pair pair;
                Object obj4;
                Object obj5;
                Pair pair1;
                Pair pair2;
                Pair pair3;
                Pair pair4;
                try
                {
                    pair = (Pair)obj3;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "set-cdr!", 1, obj3);
                }
                lists.setCdr$Ex(pair, obj2);
                obj4 = lists.car.apply1(sequence);
                obj5 = lists.cdr.apply1(sequence);
                try
                {
                    pair1 = (Pair)sequence;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "set-car!", 1, sequence);
                }
                lists.setCar$Ex(pair1, lists.car.apply1(obj2));
                try
                {
                    pair2 = (Pair)sequence;
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "set-cdr!", 1, sequence);
                }
                lists.setCdr$Ex(pair2, lists.cdr.apply1(obj2));
                try
                {
                    pair3 = (Pair)obj2;
                }
                catch (ClassCastException classcastexception3)
                {
                    throw new WrongType(classcastexception3, "set-car!", 1, obj2);
                }
                lists.setCar$Ex(pair3, obj4);
                try
                {
                    pair4 = (Pair)obj2;
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "set-cdr!", 1, obj2);
                }
                lists.setCdr$Ex(pair4, obj5);
            }
            return sequence;
        } else
        {
            return $PcVectorSort$Ex(sequence, obj, obj1);
        }
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        if (modulemethod.selector == 1)
        {
            return identity(obj);
        } else
        {
            return super.apply1(modulemethod, obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 2: // '\002'
            return isSorted(obj, obj1);

        case 9: // '\t'
            Sequence sequence;
            Sequence sequence1;
            Sequence sequence2;
            try
            {
                sequence2 = (Sequence)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "sort!", 1, obj);
            }
            return sort$Ex(sequence2, obj1);

        case 12: // '\f'
            try
            {
                sequence1 = (Sequence)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "%sort-vector", 1, obj);
            }
            $PcSortVector(sequence1, obj1);
            return Values.empty;

        case 14: // '\016'
            break;
        }
        try
        {
            sequence = (Sequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "sort", 1, obj);
        }
        return sort(sequence, obj1);
    }

    public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
    {
        switch (modulemethod.selector)
        {
        case 3: // '\003'
        case 5: // '\005'
        case 7: // '\007'
        case 10: // '\n'
        case 13: // '\r'
        default:
            return super.apply3(modulemethod, obj, obj1, obj2);

        case 2: // '\002'
            return isSorted(obj, obj1, obj2);

        case 4: // '\004'
            return merge(obj, obj1, obj2);

        case 6: // '\006'
            return merge$Ex(obj, obj1, obj2);

        case 8: // '\b'
            return $PcSortList(obj, obj1, obj2);

        case 9: // '\t'
            Sequence sequence;
            Sequence sequence1;
            Sequence sequence2;
            Sequence sequence3;
            try
            {
                sequence3 = (Sequence)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "sort!", 1, obj);
            }
            return sort$Ex(sequence3, obj1, obj2);

        case 11: // '\013'
            try
            {
                sequence2 = (Sequence)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "%vector-sort!", 1, obj);
            }
            return $PcVectorSort$Ex(sequence2, obj1, obj2);

        case 12: // '\f'
            try
            {
                sequence1 = (Sequence)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "%sort-vector", 1, obj);
            }
            $PcSortVector(sequence1, obj1, obj2);
            return Values.empty;

        case 14: // '\016'
            break;
        }
        try
        {
            sequence = (Sequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "sort", 1, obj);
        }
        return sort(sequence, obj1, obj2);
    }

    public Object apply4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3)
    {
        switch (modulemethod.selector)
        {
        case 5: // '\005'
        default:
            return super.apply4(modulemethod, obj, obj1, obj2, obj3);

        case 4: // '\004'
            return merge(obj, obj1, obj2, obj3);

        case 6: // '\006'
            return merge$Ex(obj, obj1, obj2, obj3);
        }
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

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        int i = 0xfff40001;
        modulemethod.selector;
        JVM INSTR lookupswitch 4: default 52
    //                   2: 165
    //                   9: 132
    //                   12: 99
    //                   14: 66;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        i = super.match2(modulemethod, obj, obj1, callcontext);
_L7:
        return i;
_L5:
        if (!(obj instanceof Sequence)) goto _L7; else goto _L6
_L6:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.proc = modulemethod;
        callcontext.pc = 2;
        return 0;
_L4:
        if (!(obj instanceof Sequence)) goto _L7; else goto _L8
_L8:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.proc = modulemethod;
        callcontext.pc = 2;
        return 0;
_L3:
        if (!(obj instanceof Sequence)) goto _L7; else goto _L9
_L9:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.proc = modulemethod;
        callcontext.pc = 2;
        return 0;
_L2:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.proc = modulemethod;
        callcontext.pc = 2;
        return 0;
    }

    public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        int i = 0xfff40001;
        modulemethod.selector;
        JVM INSTR tableswitch 2 14: default 76
    //                   2 351
    //                   3 76
    //                   4 318
    //                   5 76
    //                   6 285
    //                   7 76
    //                   8 252
    //                   9 212
    //                   10 76
    //                   11 172
    //                   12 132
    //                   13 76
    //                   14 92;
           goto _L1 _L2 _L1 _L3 _L1 _L4 _L1 _L5 _L6 _L1 _L7 _L8 _L1 _L9
_L1:
        i = super.match3(modulemethod, obj, obj1, obj2, callcontext);
_L11:
        return i;
_L9:
        if (!(obj instanceof Sequence)) goto _L11; else goto _L10
_L10:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.value3 = obj2;
        callcontext.proc = modulemethod;
        callcontext.pc = 3;
        return 0;
_L8:
        if (!(obj instanceof Sequence)) goto _L11; else goto _L12
_L12:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.value3 = obj2;
        callcontext.proc = modulemethod;
        callcontext.pc = 3;
        return 0;
_L7:
        if (!(obj instanceof Sequence)) goto _L11; else goto _L13
_L13:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.value3 = obj2;
        callcontext.proc = modulemethod;
        callcontext.pc = 3;
        return 0;
_L6:
        if (!(obj instanceof Sequence)) goto _L11; else goto _L14
_L14:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.value3 = obj2;
        callcontext.proc = modulemethod;
        callcontext.pc = 3;
        return 0;
_L5:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.value3 = obj2;
        callcontext.proc = modulemethod;
        callcontext.pc = 3;
        return 0;
_L4:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.value3 = obj2;
        callcontext.proc = modulemethod;
        callcontext.pc = 3;
        return 0;
_L3:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.value3 = obj2;
        callcontext.proc = modulemethod;
        callcontext.pc = 3;
        return 0;
_L2:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.value3 = obj2;
        callcontext.proc = modulemethod;
        callcontext.pc = 3;
        return 0;
    }

    public int match4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 5: // '\005'
        default:
            return super.match4(modulemethod, obj, obj1, obj2, obj3, callcontext);

        case 6: // '\006'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.value4 = obj3;
            callcontext.proc = modulemethod;
            callcontext.pc = 4;
            return 0;

        case 4: // '\004'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.value4 = obj3;
            callcontext.proc = modulemethod;
            callcontext.pc = 4;
            return 0;
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit12 = (SimpleSymbol)(new SimpleSymbol("sort")).readResolve();
        Lit11 = (SimpleSymbol)(new SimpleSymbol("%sort-vector")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("%vector-sort!")).readResolve();
        Lit9 = (SimpleSymbol)(new SimpleSymbol("sort!")).readResolve();
        Lit8 = (SimpleSymbol)(new SimpleSymbol("%sort-list")).readResolve();
        Lit7 = (SimpleSymbol)(new SimpleSymbol("merge!")).readResolve();
        Lit6 = (SimpleSymbol)(new SimpleSymbol("merge")).readResolve();
        Lit5 = (SimpleSymbol)(new SimpleSymbol("sorted?")).readResolve();
        Lit4 = (SimpleSymbol)(new SimpleSymbol("identity")).readResolve();
        $instance = new srfi95();
        srfi95 srfi95_1 = $instance;
        identity = new ModuleMethod(srfi95_1, 1, Lit4, 4097);
        sorted$Qu = new ModuleMethod(srfi95_1, 2, Lit5, 12290);
        merge = new ModuleMethod(srfi95_1, 4, Lit6, 16387);
        merge$Ex = new ModuleMethod(srfi95_1, 6, Lit7, 16387);
        $Pcsort$Mnlist = new ModuleMethod(srfi95_1, 8, Lit8, 12291);
        sort$Ex = new ModuleMethod(srfi95_1, 9, Lit9, 12290);
        $Pcvector$Mnsort$Ex = new ModuleMethod(srfi95_1, 11, Lit10, 12291);
        $Pcsort$Mnvector = new ModuleMethod(srfi95_1, 12, Lit11, 12290);
        sort = new ModuleMethod(srfi95_1, 14, Lit12, 12290);
        $instance.run();
    }
}
