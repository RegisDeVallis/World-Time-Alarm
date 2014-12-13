// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.mapping.LazyPropertyKey;
import gnu.mapping.Procedure;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package gnu.kawa.functions:
//            ArithOp, Arithmetic

public class AddOp extends ArithOp
{

    public static final AddOp $Mn = new AddOp("-", -1);
    public static final AddOp $Pl = new AddOp("+", 1);
    int plusOrMinus;

    public static Object $Mn(Object obj)
    {
        switch (Arithmetic.classifyValue(obj))
        {
        default:
            return Arithmetic.asNumeric(obj).neg();

        case 1: // '\001'
            return new Integer(-Arithmetic.asInt(obj));

        case 2: // '\002'
            return new Long(-Arithmetic.asLong(obj));

        case 3: // '\003'
            return Arithmetic.asBigInteger(obj).negate();

        case 4: // '\004'
            return IntNum.neg(Arithmetic.asIntNum(obj));

        case 5: // '\005'
            return Arithmetic.asBigDecimal(obj).negate();

        case 6: // '\006'
            return RatNum.neg(Arithmetic.asRatNum(obj));

        case 7: // '\007'
            return new Float(-Arithmetic.asFloat(obj));

        case 8: // '\b'
            return new Double(-Arithmetic.asDouble(obj));

        case 9: // '\t'
            return new DFloNum(-Arithmetic.asDouble(obj));
        }
    }

    public static Object $Mn(Object obj, Object obj1)
    {
        return apply2(-1, obj, obj1);
    }

    public static Object $Mn$V(Object obj, Object obj1, Object obj2, Object aobj[])
    {
        return applyN(-1, apply2(-1, apply2(-1, obj, obj1), obj2), aobj);
    }

    public static Object $Pl(Object obj, Object obj1)
    {
        return apply2(1, obj, obj1);
    }

    public static Object $Pl$V(Object obj, Object obj1, Object obj2, Object aobj[])
    {
        return applyN(1, apply2(1, apply2(1, obj, obj1), obj2), aobj);
    }

    public AddOp(String s, int i)
    {
        int j;
        String s1;
        if (i > 0)
        {
            j = 1;
        } else
        {
            j = 2;
        }
        super(s, j);
        plusOrMinus = 1;
        plusOrMinus = i;
        if (i > 0)
        {
            s1 = "gnu.kawa.functions.CompileArith:$Pl";
        } else
        {
            s1 = "gnu.kawa.functions.CompileArith:$Mn";
        }
        Procedure.compilerKey.set(this, s1);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
    }

    public static Object apply2(int i, Object obj, Object obj1)
    {
        int j = Arithmetic.classifyValue(obj);
        int k = Arithmetic.classifyValue(obj1);
        int l;
        if (j < k)
        {
            l = k;
        } else
        {
            l = j;
        }
        double d;
        double d1;
        switch (l)
        {
        default:
            return Arithmetic.asNumeric(obj).add(Arithmetic.asNumeric(obj1), i);

        case 1: // '\001'
            int i1 = Arithmetic.asInt(obj);
            int j1 = Arithmetic.asInt(obj1);
            int k1;
            Integer integer;
            if (i > 0)
            {
                k1 = i1 + j1;
            } else
            {
                k1 = i1 - j1;
            }
            integer = new Integer(k1);
            return integer;

        case 2: // '\002'
            long l1 = Arithmetic.asLong(obj);
            long l2 = Arithmetic.asLong(obj1);
            long l3;
            Long long1;
            if (i > 0)
            {
                l3 = l1 + l2;
            } else
            {
                l3 = l1 - l2;
            }
            long1 = new Long(l3);
            return long1;

        case 3: // '\003'
            BigInteger biginteger = Arithmetic.asBigInteger(obj);
            BigInteger biginteger1 = Arithmetic.asBigInteger(obj1);
            if (i > 0)
            {
                return biginteger.add(biginteger1);
            } else
            {
                return biginteger.subtract(biginteger1);
            }

        case 4: // '\004'
            return IntNum.add(Arithmetic.asIntNum(obj), Arithmetic.asIntNum(obj1), i);

        case 5: // '\005'
            BigDecimal bigdecimal = Arithmetic.asBigDecimal(obj);
            BigDecimal bigdecimal1 = Arithmetic.asBigDecimal(obj1);
            if (i > 0)
            {
                return bigdecimal.add(bigdecimal1);
            } else
            {
                return bigdecimal.subtract(bigdecimal1);
            }

        case 6: // '\006'
            return RatNum.add(Arithmetic.asRatNum(obj), Arithmetic.asRatNum(obj1), i);

        case 7: // '\007'
            float f = Arithmetic.asFloat(obj);
            float f1 = Arithmetic.asFloat(obj1);
            float f2;
            Float float1;
            if (i > 0)
            {
                f2 = f + f1;
            } else
            {
                f2 = f - f1;
            }
            float1 = new Float(f2);
            return float1;

        case 8: // '\b'
            double d3 = Arithmetic.asDouble(obj);
            double d4 = Arithmetic.asDouble(obj1);
            double d5;
            Double double1;
            if (i > 0)
            {
                d5 = d3 + d4;
            } else
            {
                d5 = d3 - d4;
            }
            double1 = new Double(d5);
            return double1;

        case 9: // '\t'
            d = Arithmetic.asDouble(obj);
            d1 = Arithmetic.asDouble(obj1);
            break;
        }
        double d2;
        DFloNum dflonum;
        if (i > 0)
        {
            d2 = d + d1;
        } else
        {
            d2 = d - d1;
        }
        dflonum = new DFloNum(d2);
        return dflonum;
    }

    public static Object applyN(int i, Object obj, Object aobj[])
    {
        int j = aobj.length;
        Object obj1 = obj;
        for (int k = 0; k < j; k++)
        {
            obj1 = apply2(i, obj1, aobj[k]);
        }

        return obj1;
    }

    public static Object applyN(int i, Object aobj[])
    {
        int j = aobj.length;
        Object obj;
        if (j == 0)
        {
            obj = IntNum.zero();
        } else
        {
            obj = aobj[0];
            if (j == 1 && i < 0)
            {
                return $Mn(obj);
            }
            int k = 1;
            while (k < j) 
            {
                obj = apply2(i, obj, aobj[k]);
                k++;
            }
        }
        return obj;
    }

    public Object applyN(Object aobj[])
    {
        return applyN(plusOrMinus, aobj);
    }

}
