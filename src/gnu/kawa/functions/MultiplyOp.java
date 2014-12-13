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

public class MultiplyOp extends ArithOp
{

    public static final MultiplyOp $St = new MultiplyOp("*");

    public MultiplyOp(String s)
    {
        super(s, 3);
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyArithOp");
        Procedure.compilerKey.set(this, "*gnu.kawa.functions.CompileArith:forMul");
    }

    public static Object apply(Object obj, Object obj1)
    {
        return ((Numeric)obj).mul(obj1);
    }

    public Object applyN(Object aobj[])
    {
        int i = aobj.length;
        if (i != 0) goto _L2; else goto _L1
_L1:
        Object obj = IntNum.one();
_L4:
        return obj;
_L2:
        int j;
        int k;
        obj = (Number)aobj[0];
        j = Arithmetic.classifyValue(obj);
        k = 1;
_L15:
        if (k >= i) goto _L4; else goto _L3
_L3:
        Object obj1;
        obj1 = aobj[k];
        int l = Arithmetic.classifyValue(obj1);
        if (j < l)
        {
            j = l;
        }
        j;
        JVM INSTR tableswitch 1 9: default 112
    //                   1 131
    //                   2 152
    //                   3 173
    //                   4 189
    //                   5 205
    //                   6 221
    //                   7 237
    //                   8 258
    //                   9 279;
           goto _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L14:
        break MISSING_BLOCK_LABEL_279;
_L5:
        obj = Arithmetic.asNumeric(obj).mul(Arithmetic.asNumeric(obj1));
_L16:
        k++;
          goto _L15
_L6:
        obj = new Integer(Arithmetic.asInt(obj) * Arithmetic.asInt(obj1));
          goto _L16
_L7:
        obj = new Long(Arithmetic.asLong(obj) * Arithmetic.asLong(obj1));
          goto _L16
_L8:
        obj = Arithmetic.asBigInteger(obj).multiply(Arithmetic.asBigInteger(obj1));
          goto _L16
_L9:
        obj = IntNum.times(Arithmetic.asIntNum(obj), Arithmetic.asIntNum(obj1));
          goto _L16
_L10:
        obj = Arithmetic.asBigDecimal(obj).multiply(Arithmetic.asBigDecimal(obj1));
          goto _L16
_L11:
        obj = RatNum.times(Arithmetic.asRatNum(obj), Arithmetic.asRatNum(obj1));
          goto _L16
_L12:
        obj = new Float(Arithmetic.asFloat(obj) * Arithmetic.asFloat(obj1));
          goto _L16
_L13:
        obj = new Double(Arithmetic.asDouble(obj) * Arithmetic.asDouble(obj1));
          goto _L16
        obj = new DFloNum(Arithmetic.asDouble(obj) * Arithmetic.asDouble(obj1));
          goto _L16
    }

    public Object defaultResult()
    {
        return IntNum.one();
    }

}
