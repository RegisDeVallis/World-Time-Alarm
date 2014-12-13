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
import gnu.math.RealNum;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

// Referenced classes of package gnu.kawa.functions:
//            ArithOp, Arithmetic

public class DivideOp extends ArithOp
{

    public static final DivideOp $Sl = new DivideOp("/", 4);
    public static final DivideOp div;
    public static final DivideOp div0;
    public static final DivideOp idiv;
    public static final DivideOp mod;
    public static final DivideOp mod0;
    public static final DivideOp modulo;
    public static final DivideOp quotient;
    public static final DivideOp remainder;
    int rounding_mode;

    public DivideOp(String s, int i)
    {
        super(s, i);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
        Procedure.compilerKey.set(this, "*gnu.kawa.functions.CompileArith:forDiv");
    }

    public Object applyN(Object aobj[])
        throws Throwable
    {
        int i = aobj.length;
        if (i != 0) goto _L2; else goto _L1
_L1:
        Object obj2 = IntNum.one();
_L15:
        return obj2;
_L2:
        Object obj;
        int j;
        int k;
        obj = (Number)aobj[0];
        if (i == 1)
        {
            return apply2(IntNum.one(), obj);
        }
        j = Arithmetic.classifyValue(obj);
        k = 1;
_L25:
        if (k >= i) goto _L4; else goto _L3
_L3:
        Object obj1;
        int i1;
        obj1 = aobj[k];
        int l = Arithmetic.classifyValue(obj1);
        if (j < l)
        {
            j = l;
        }
        i1 = j;
        if (j >= 4) goto _L6; else goto _L5
_L5:
        op;
        JVM INSTR tableswitch 4 5: default 112
    //                   4 264
    //                   5 264;
           goto _L7 _L8 _L8
_L7:
        if (rounding_mode != 3 || j != 1 && j != 2)
        {
            i1 = 4;
        }
_L6:
        if (op == 5 && j <= 10)
        {
            i1 = 10;
            if (j != 8 && j != 7)
            {
                j = 9;
            }
        } else
        if (i1 == 8 || i1 == 7)
        {
            i1 = 9;
            if (op == 7)
            {
                j = i1;
            }
        }
        i1;
        JVM INSTR tableswitch 1 9: default 220
    //                   1 314
    //                   2 432
    //                   3 220
    //                   4 494
    //                   5 612
    //                   6 220
    //                   7 220
    //                   8 220
    //                   9 829;
           goto _L9 _L10 _L11 _L9 _L12 _L13 _L9 _L9 _L9 _L14
_L9:
        obj2 = Arithmetic.asNumeric(obj);
        Numeric numeric = Arithmetic.asNumeric(obj1);
        double d;
        double d1;
        BigDecimal bigdecimal;
        BigDecimal bigdecimal1;
        RoundingMode roundingmode;
        MathContext mathcontext;
        long l1;
        long l2;
        long l3;
        int j1;
        int k1;
        int i2;
        if (op == 8 && numeric.isZero())
        {
            if (!numeric.isExact())
            {
                return ((Numeric) (obj2)).toInexact();
            }
        } else
        {
            Numeric numeric1 = ((Numeric) (obj2)).div(numeric);
            if (op == 8)
            {
                Numeric numeric2 = ((RealNum)numeric1).toInt(getRoundingMode()).mul(numeric);
                numeric1 = ((Numeric) (obj2)).sub(numeric2);
            }
            switch (op)
            {
            default:
                obj = numeric1;
                break;

            case 7: // '\007'
                obj = ((RealNum)numeric1).toExactInt(rounding_mode);
                j = 4;
                i1 = j;
                break;

            case 6: // '\006'
                obj = ((RealNum)numeric1).toInt(rounding_mode);
                break;

            case 5: // '\005'
                obj = numeric1.toInexact();
                break;
            }
            continue; /* Loop/switch isn't completed */
        }
          goto _L15
_L8:
        j = 4;
        i1 = j;
          goto _L6
_L10:
        j1 = Arithmetic.asInt(obj);
        k1 = Arithmetic.asInt(obj1);
        op;
        JVM INSTR tableswitch 8 8: default 348
    //                   8 422;
           goto _L16 _L17
_L16:
        i2 = j1 / k1;
_L26:
        obj = Integer.valueOf(i2);
_L29:
        if (j == i1) goto _L19; else goto _L18
_L18:
        j;
        JVM INSTR tableswitch 1 8: default 416
    //                   1 1102
    //                   2 1113
    //                   3 1146
    //                   4 416
    //                   5 416
    //                   6 416
    //                   7 1124
    //                   8 1135;
           goto _L19 _L20 _L21 _L22 _L19 _L19 _L19 _L23 _L24
_L19:
        k++;
          goto _L25
_L17:
        i2 = j1 % k1;
          goto _L26
_L11:
        l1 = Arithmetic.asLong(obj);
        l2 = Arithmetic.asLong(obj1);
        op;
        JVM INSTR tableswitch 8 8: default 468
    //                   8 484;
           goto _L27 _L28
_L27:
        l3 = l1 / l2;
_L30:
        obj = Long.valueOf(l3);
          goto _L29
_L28:
        l3 = l1 % l2;
          goto _L30
_L12:
        switch (op)
        {
        case 4: // '\004'
            obj = RatNum.make(Arithmetic.asIntNum(obj), Arithmetic.asIntNum(obj1));
            if (obj instanceof IntNum)
            {
                j = 4;
            } else
            {
                j = 6;
            }
            i1 = j;
            break;

        case 6: // '\006'
        case 7: // '\007'
            obj = IntNum.quotient(Arithmetic.asIntNum(obj), Arithmetic.asIntNum(obj1), getRoundingMode());
            break;

        case 8: // '\b'
            obj = IntNum.remainder(Arithmetic.asIntNum(obj), Arithmetic.asIntNum(obj1), getRoundingMode());
            break;
        }
        if (true) goto _L29; else goto _L31
_L31:
_L13:
        bigdecimal = Arithmetic.asBigDecimal(obj);
        bigdecimal1 = Arithmetic.asBigDecimal(obj1);
        getRoundingMode();
        JVM INSTR tableswitch 1 5: default 664
    //                   1 734
    //                   2 742
    //                   3 750
    //                   4 664
    //                   5 758;
           goto _L32 _L33 _L34 _L35 _L32 _L36
_L32:
        roundingmode = RoundingMode.HALF_EVEN;
_L37:
        mathcontext = new MathContext(0, roundingmode);
        switch (op)
        {
        case 4: // '\004'
            obj = bigdecimal.divide(bigdecimal1);
            break;

        case 6: // '\006'
            obj = bigdecimal.divideToIntegralValue(bigdecimal1, mathcontext);
            break;

        case 7: // '\007'
            obj = bigdecimal.divideToIntegralValue(bigdecimal1, mathcontext).toBigInteger();
            i1 = 3;
            j = i1;
            break;

        case 8: // '\b'
            obj = bigdecimal.remainder(bigdecimal1, mathcontext);
            break;
        }
        break; /* Loop/switch isn't completed */
_L33:
        roundingmode = RoundingMode.FLOOR;
          goto _L37
_L34:
        roundingmode = RoundingMode.CEILING;
          goto _L37
_L35:
        roundingmode = RoundingMode.DOWN;
          goto _L37
_L36:
        if (bigdecimal1.signum() < 0)
        {
            RoundingMode.CEILING;
        } else
        {
            RoundingMode.FLOOR;
        }
        if (true) goto _L32; else goto _L38
_L38:
        if (true) goto _L29; else goto _L39
_L39:
_L14:
        d = Arithmetic.asDouble(obj);
        d1 = Arithmetic.asDouble(obj1);
        switch (op)
        {
        case 4: // '\004'
        case 5: // '\005'
            obj = DFloNum.make(d / d1);
            break;

        case 6: // '\006'
            obj = Double.valueOf(RealNum.toInt(d / d1, getRoundingMode()));
            break;

        case 7: // '\007'
            obj = RealNum.toExactInt(d / d1, getRoundingMode());
            i1 = 4;
            j = i1;
            break;

        case 8: // '\b'
            if (d1 != 0.0D)
            {
                d -= d1 * RealNum.toInt(d / d1, getRoundingMode());
            }
            obj = DFloNum.make(d);
            break;
        }
        continue; /* Loop/switch isn't completed */
_L20:
        obj = Integer.valueOf(((Number) (obj)).intValue());
          goto _L19
_L21:
        obj = Long.valueOf(((Number) (obj)).longValue());
          goto _L19
_L23:
        obj = Float.valueOf(((Number) (obj)).floatValue());
          goto _L19
_L24:
        obj = Double.valueOf(((Number) (obj)).doubleValue());
          goto _L19
_L22:
        obj = Arithmetic.asBigInteger(obj);
          goto _L19
        if (true) goto _L29; else goto _L40
_L40:
_L4:
        return obj;
          goto _L25
    }

    public int getRoundingMode()
    {
        return rounding_mode;
    }

    public int numArgs()
    {
        return op != 4 ? 8194 : -4095;
    }

    static 
    {
        idiv = new DivideOp("idiv", 7);
        quotient = new DivideOp("quotient", 6);
        remainder = new DivideOp("remainder", 8);
        modulo = new DivideOp("modulo", 8);
        div = new DivideOp("div", 6);
        mod = new DivideOp("mod", 8);
        div0 = new DivideOp("div0", 6);
        mod0 = new DivideOp("mod0", 8);
        idiv.rounding_mode = 3;
        quotient.rounding_mode = 3;
        remainder.rounding_mode = 3;
        modulo.rounding_mode = 1;
        div.rounding_mode = 5;
        mod.rounding_mode = 5;
        div0.rounding_mode = 4;
        mod0.rounding_mode = 4;
    }
}
