// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.DivideOp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import kawa.standard.Scheme;

// Referenced classes of package kawa.lib:
//            srfi95, lists

public class ion extends ModuleBody
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

    public ion()
    {
    }
}
