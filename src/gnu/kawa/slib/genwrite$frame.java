// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Format;
import gnu.lists.FVector;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lib.lists;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            genwrite

public class  extends ModuleBody
{

    Object display$Qu;
    Object output;
    Object width;

    public static Object lambda1isReadMacro(Object obj)
    {
        Object obj1 = lists.car.apply1(obj);
        Object obj2 = lists.cdr.apply1(obj);
        Object obj3 = Scheme.isEqv.apply2(obj1, genwrite.Lit30);
        boolean flag;
        if (obj3 == Boolean.FALSE ? (obj4 = Scheme.isEqv.apply2(obj1, genwrite.Lit31)) == Boolean.FALSE ? (obj5 = Scheme.isEqv.apply2(obj1, genwrite.Lit32)) == Boolean.FALSE ? Scheme.isEqv.apply2(obj1, genwrite.Lit33) != Boolean.FALSE : obj5 != Boolean.FALSE : obj4 != Boolean.FALSE : obj3 != Boolean.FALSE)
        {
            flag = lists.isPair(obj2);
            Object obj4;
            Object obj5;
            if (flag)
            {
                if (lists.isNull(lists.cdr.apply1(obj2)))
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
        } else
        {
            return Boolean.FALSE;
        }
        if (flag)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static Object lambda2readMacroBody(Object obj)
    {
        return lists.cadr.apply1(obj);
    }

    public static Object lambda3readMacroPrefix(Object obj)
    {
        Object obj1 = lists.car.apply1(obj);
        lists.cdr.apply1(obj);
        if (Scheme.isEqv.apply2(obj1, genwrite.Lit30) != Boolean.FALSE)
        {
            return "'";
        }
        if (Scheme.isEqv.apply2(obj1, genwrite.Lit31) != Boolean.FALSE)
        {
            return "`";
        }
        if (Scheme.isEqv.apply2(obj1, genwrite.Lit32) != Boolean.FALSE)
        {
            return ",";
        }
        if (Scheme.isEqv.apply2(obj1, genwrite.Lit33) != Boolean.FALSE)
        {
            return ",@";
        } else
        {
            return Values.empty;
        }
    }

    public Object lambda4out(Object obj, Object obj1)
    {
        if (obj1 != Boolean.FALSE)
        {
            Object obj2 = Scheme.applyToArgs.apply2(output, obj);
            if (obj2 != Boolean.FALSE)
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
                obj2 = addop.apply2(obj1, Integer.valueOf(strings.stringLength(charsequence)));
            }
            return obj2;
        } else
        {
            return obj1;
        }
    }

    public Object lambda5wr(Object obj, Object obj1)
    {
        if (!lists.isPair(obj)) goto _L2; else goto _L1
_L1:
        Object obj4 = obj;
        if (lambda1isReadMacro(obj4) == Boolean.FALSE) goto _L4; else goto _L3
_L3:
        obj1 = lambda5wr(lambda2readMacroBody(obj4), lambda4out(lambda3readMacroPrefix(obj4), obj1));
_L5:
        return obj1;
_L4:
        obj = obj4;
_L6:
        if (lists.isPair(obj))
        {
            Object obj2 = lists.cdr.apply1(obj);
            if (obj1 != Boolean.FALSE)
            {
                obj1 = lambda5wr(lists.car.apply1(obj), lambda4out("(", obj1));
            }
            while (obj1 != Boolean.FALSE) 
            {
                if (lists.isPair(obj2))
                {
                    Object obj3 = lists.cdr.apply1(obj2);
                    obj1 = lambda5wr(lists.car.apply1(obj2), lambda4out(" ", obj1));
                    obj2 = obj3;
                } else
                if (lists.isNull(obj2))
                {
                    return lambda4out(")", obj1);
                } else
                {
                    return lambda4out(")", lambda5wr(obj2, lambda4out(" . ", obj1)));
                }
            }
        } else
        {
            return lambda4out("()", obj1);
        }
        if (true) goto _L5; else goto _L2
_L2:
        if (!lists.isNull(obj))
        {
            if (vectors.isVector(obj))
            {
                Object aobj[];
                String s;
                FVector fvector;
                gnu.lists.LList llist;
                try
                {
                    fvector = (FVector)obj;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "vector->list", 1, obj);
                }
                llist = vectors.vector$To$List(fvector);
                obj1 = lambda4out("#", obj1);
                obj = llist;
            } else
            {
                aobj = new Object[2];
                if (display$Qu != Boolean.FALSE)
                {
                    s = "~a";
                } else
                {
                    s = "~s";
                }
                aobj[0] = s;
                aobj[1] = obj;
                return lambda4out(Format.formatToString(0, aobj), obj1);
            }
        }
          goto _L6
    }

    public ()
    {
    }
}
