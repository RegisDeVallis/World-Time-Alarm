// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.standard.Scheme;

// Referenced classes of package kawa.lib:
//            lists, srfi95

public class ion extends ModuleBody
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

    public ion()
    {
    }
}
