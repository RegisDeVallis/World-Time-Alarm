// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Arithmetic;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.BitOps;
import gnu.math.Complex;
import gnu.math.DComplex;
import gnu.math.DFloNum;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.Quantity;
import gnu.math.RatNum;
import gnu.math.RealNum;
import gnu.math.Unit;
import java.math.BigDecimal;
import java.math.BigInteger;
import kawa.standard.Scheme;

// Referenced classes of package kawa.lib:
//            misc

public class numbers extends ModuleBody
{

    public static final numbers $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final SimpleSymbol Lit1 = (SimpleSymbol)(new SimpleSymbol("signum")).readResolve();
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
    static final IntNum Lit2 = IntNum.make(1);
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
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod abs;
    public static final ModuleMethod acos;
    public static final ModuleMethod angle;
    public static final ModuleMethod asin;
    public static final GenericProc atan;
    public static final ModuleMethod bitwise$Mnbit$Mncount;
    public static final ModuleMethod bitwise$Mnbit$Mnfield;
    public static final ModuleMethod bitwise$Mnbit$Mnset$Qu;
    public static final ModuleMethod bitwise$Mncopy$Mnbit;
    public static final ModuleMethod bitwise$Mncopy$Mnbit$Mnfield;
    public static final ModuleMethod bitwise$Mnfirst$Mnbit$Mnset;
    public static final ModuleMethod bitwise$Mnif;
    public static final ModuleMethod bitwise$Mnlength;
    public static final ModuleMethod bitwise$Mnreverse$Mnbit$Mnfield;
    public static final ModuleMethod bitwise$Mnrotate$Mnbit$Mnfield;
    public static final ModuleMethod ceiling;
    public static final ModuleMethod complex$Qu;
    public static final ModuleMethod cos;
    public static final ModuleMethod denominator;
    public static final ModuleMethod div$Mnand$Mnmod;
    public static final ModuleMethod div0$Mnand$Mnmod0;
    public static final ModuleMethod duration;
    public static final ModuleMethod exact;
    public static final ModuleMethod exact$Mn$Grinexact;
    public static final ModuleMethod exact$Qu;
    public static final ModuleMethod exp;
    public static final ModuleMethod floor;
    public static final ModuleMethod gcd;
    public static final ModuleMethod imag$Mnpart;
    public static final ModuleMethod inexact;
    public static final ModuleMethod inexact$Mn$Grexact;
    public static final ModuleMethod inexact$Qu;
    public static final ModuleMethod integer$Qu;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn2;
    static final ModuleMethod lambda$Fn3;
    static final ModuleMethod lambda$Fn4;
    public static final ModuleMethod lcm;
    public static final ModuleMethod log;
    public static final ModuleMethod logcount;
    public static final ModuleMethod logop;
    public static final ModuleMethod logtest;
    public static final ModuleMethod magnitude;
    public static final ModuleMethod make$Mnpolar;
    public static final ModuleMethod make$Mnquantity;
    public static final ModuleMethod make$Mnrectangular;
    public static final ModuleMethod max;
    public static final ModuleMethod min;
    public static final ModuleMethod negative$Qu;
    public static final ModuleMethod number$Mn$Grstring;
    public static final ModuleMethod number$Qu;
    public static final ModuleMethod numerator;
    public static final ModuleMethod positive$Qu;
    public static final ModuleMethod quantity$Mn$Grnumber;
    public static final ModuleMethod quantity$Mn$Grunit;
    public static final ModuleMethod quantity$Qu;
    public static final ModuleMethod rational$Qu;
    public static final ModuleMethod rationalize;
    public static final ModuleMethod real$Mnpart;
    public static final ModuleMethod real$Qu;
    public static final ModuleMethod round;
    public static final ModuleMethod sin;
    public static final GenericProc sqrt;
    public static final ModuleMethod string$Mn$Grnumber;
    public static final ModuleMethod tan;
    public static final ModuleMethod truncate;
    public static final ModuleMethod zero$Qu;

    public numbers()
    {
        ModuleInfo.register(this);
    }

    public static Number abs(Number number)
    {
        if (number instanceof Numeric)
        {
            number = ((Numeric)number).abs();
        } else
        if (Scheme.numGEq.apply2(number, Lit0) == Boolean.FALSE)
        {
            return (Number)AddOp.$Mn.apply1(number);
        }
        return number;
    }

    public static double acos(double d)
    {
        return Math.acos(d);
    }

    public static RealNum angle(Complex complex)
    {
        return complex.angle();
    }

    public static double asin(double d)
    {
        return Math.asin(d);
    }

    public static int bitwiseBitCount(IntNum intnum)
    {
        if (IntNum.compare(intnum, 0L) >= 0)
        {
            return BitOps.bitCount(intnum);
        } else
        {
            return -1 - BitOps.bitCount(BitOps.not(intnum));
        }
    }

    public static IntNum bitwiseBitField(IntNum intnum, int i, int j)
    {
        return BitOps.extract(intnum, i, j);
    }

    public static IntNum bitwiseCopyBit(IntNum intnum, int i, int j)
    {
        return BitOps.setBitValue(intnum, i, j);
    }

    public static IntNum bitwiseCopyBitField(IntNum intnum, int i, int j, IntNum intnum1)
    {
        int k = IntNum.shift(-1, i);
        IntNum intnum2 = BitOps.not(IntNum.make(IntNum.shift(-1, j)));
        return bitwiseIf(BitOps.and(IntNum.make(k), intnum2), IntNum.shift(intnum1, i), intnum);
    }

    public static int bitwiseFirstBitSet(IntNum intnum)
    {
        return BitOps.lowestBitSet(intnum);
    }

    public static IntNum bitwiseIf(IntNum intnum, IntNum intnum1, IntNum intnum2)
    {
        return BitOps.ior(BitOps.and(intnum, intnum1), BitOps.and(BitOps.not(intnum), intnum2));
    }

    public static int bitwiseLength(IntNum intnum)
    {
        return intnum.intLength();
    }

    public static IntNum bitwiseReverseBitField(IntNum intnum, int i, int j)
    {
        return BitOps.reverseBits(intnum, i, j);
    }

    public static IntNum bitwiseRotateBitField(IntNum intnum, int i, int j, int k)
    {
        int l = j - i;
        if (l > 0)
        {
            int i1 = k % l;
            int j1;
            IntNum intnum1;
            if (i1 < 0)
            {
                j1 = i1 + l;
            } else
            {
                j1 = i1;
            }
            intnum1 = bitwiseBitField(intnum, i, j);
            intnum = bitwiseCopyBitField(intnum, i, j, BitOps.ior(IntNum.shift(intnum1, j1), IntNum.shift(intnum1, j1 - l)));
        }
        return intnum;
    }

    public static RealNum ceiling(RealNum realnum)
    {
        return realnum.toInt(Numeric.CEILING);
    }

    public static double cos(double d)
    {
        return Math.cos(d);
    }

    public static IntNum denominator(RatNum ratnum)
    {
        return ratnum.denominator();
    }

    public static Object div0AndMod0(RealNum realnum, RealNum realnum1)
    {
        Object obj = DivideOp.div0.apply2(realnum, realnum1);
        RealNum realnum2;
        Object obj1;
        RealNum realnum3;
        try
        {
            realnum2 = LangObjType.coerceRealNum(obj);
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "q", -2, obj);
        }
        obj1 = AddOp.$Mn.apply2(realnum, MultiplyOp.$St.apply2(realnum2, realnum1));
        try
        {
            realnum3 = LangObjType.coerceRealNum(obj1);
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "r", -2, obj1);
        }
        return misc.values(new Object[] {
            realnum2, realnum3
        });
    }

    public static Object divAndMod(RealNum realnum, RealNum realnum1)
    {
        Object obj = DivideOp.div.apply2(realnum, realnum1);
        RealNum realnum2;
        Object obj1;
        RealNum realnum3;
        try
        {
            realnum2 = LangObjType.coerceRealNum(obj);
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "q", -2, obj);
        }
        obj1 = AddOp.$Mn.apply2(realnum, MultiplyOp.$St.apply2(realnum2, realnum1));
        try
        {
            realnum3 = LangObjType.coerceRealNum(obj1);
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "r", -2, obj1);
        }
        return misc.values(new Object[] {
            realnum2, realnum3
        });
    }

    public static Duration duration(Object obj)
    {
        String s;
        if (obj == null)
        {
            s = null;
        } else
        {
            s = obj.toString();
        }
        return Duration.parseDuration(s);
    }

    public static Number exact(Number number)
    {
        return Arithmetic.toExact(number);
    }

    public static Number exact$To$Inexact(Number number)
    {
        return Arithmetic.toInexact(number);
    }

    public static Complex exp(Complex complex)
    {
        return complex.exp();
    }

    public static RealNum floor(RealNum realnum)
    {
        return realnum.toInt(Numeric.FLOOR);
    }

    public static transient IntNum gcd(IntNum aintnum[])
    {
        int i = aintnum.length;
        IntNum intnum;
        if (i == 0)
        {
            intnum = Lit0;
        } else
        {
            intnum = aintnum[0];
            int j = 1;
            while (j < i) 
            {
                intnum = IntNum.gcd(intnum, aintnum[j]);
                j++;
            }
        }
        return intnum;
    }

    public static RealNum imagPart(Complex complex)
    {
        return complex.im();
    }

    public static Number inexact(Number number)
    {
        return Arithmetic.toInexact(number);
    }

    public static Number inexact$To$Exact(Number number)
    {
        return Arithmetic.toExact(number);
    }

    public static boolean isBitwiseBitSet(IntNum intnum, int i)
    {
        return BitOps.bitValue(intnum, i);
    }

    public static boolean isComplex(Object obj)
    {
        return obj instanceof Complex;
    }

    public static boolean isExact(Object obj)
    {
        boolean flag = obj instanceof Number;
        if (flag)
        {
            flag = Arithmetic.isExact((Number)obj);
        }
        return flag;
    }

    public static boolean isInexact(Object obj)
    {
        boolean flag = obj instanceof Number;
        if (flag)
        {
            flag = 1 & 1 + Arithmetic.isExact((Number)obj);
        }
        return flag;
    }

    public static boolean isInteger(Object obj)
    {
        boolean flag = obj instanceof IntNum;
        if (!flag)
        {
            flag = obj instanceof DFloNum;
            if (flag)
            {
                DFloNum dflonum;
                boolean flag1;
                try
                {
                    dflonum = (DFloNum)obj;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "gnu.math.DFloNum.doubleValue()", 1, obj);
                }
                if (Math.IEEEremainder(dflonum.doubleValue(), 1.0D) == 0.0D)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                flag = flag1;
            }
            if (!flag)
            {
                flag = obj instanceof Number;
                if (flag)
                {
                    flag = obj instanceof Long;
                    if (!flag)
                    {
                        flag = obj instanceof Integer;
                        if (!flag)
                        {
                            flag = obj instanceof Short;
                            if (!flag)
                            {
                                return obj instanceof BigInteger;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    public static boolean isNegative(RealNum realnum)
    {
        return realnum.isNegative();
    }

    public static boolean isNumber(Object obj)
    {
        return obj instanceof Number;
    }

    public static boolean isPositive(RealNum realnum)
    {
        return realnum.sign() > 0;
    }

    public static boolean isQuantity(Object obj)
    {
        return obj instanceof Quantity;
    }

    public static boolean isRational(Object obj)
    {
        return RatNum.asRatNumOrNull(obj) != null;
    }

    public static boolean isReal(Object obj)
    {
        return RealNum.asRealNumOrNull(obj) != null;
    }

    public static boolean isZero(Number number)
    {
        boolean flag = true;
        if (!(number instanceof Numeric)) goto _L2; else goto _L1
_L1:
        flag = ((Numeric)number).isZero();
_L4:
        return flag;
_L2:
        if (!(number instanceof BigInteger))
        {
            break; /* Loop/switch isn't completed */
        }
        if (Scheme.numEqu.apply2(Lit0, GetNamedPart.getNamedPart.apply2((BigInteger)number, Lit1)) == Boolean.FALSE)
        {
            return false;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (!(number instanceof BigDecimal))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (Scheme.numEqu.apply2(Lit0, GetNamedPart.getNamedPart.apply2((BigDecimal)number, Lit1)) != Boolean.FALSE) goto _L4; else goto _L5
_L5:
        return false;
        if (number.doubleValue() == 0.0D) goto _L4; else goto _L6
_L6:
        return false;
    }

    static double lambda1(double d, double d1)
    {
        return Math.atan2(d, d1);
    }

    static double lambda2(double d)
    {
        return Math.atan(d);
    }

    static Quantity lambda3(Quantity quantity)
    {
        return Quantity.make(quantity.number().sqrt(), quantity.unit().sqrt());
    }

    static double lambda4(double d)
    {
        return Math.sqrt(d);
    }

    public static transient IntNum lcm(IntNum aintnum[])
    {
        int i = aintnum.length;
        IntNum intnum;
        if (i == 0)
        {
            intnum = Lit2;
        } else
        {
            intnum = IntNum.abs(aintnum[0]);
            int j = 1;
            while (j < i) 
            {
                intnum = IntNum.lcm(intnum, aintnum[j]);
                j++;
            }
        }
        return intnum;
    }

    public static Complex log(Complex complex)
    {
        return complex.log();
    }

    public static int logcount(IntNum intnum)
    {
        if (IntNum.compare(intnum, 0L) < 0)
        {
            intnum = BitOps.not(intnum);
        }
        return BitOps.bitCount(intnum);
    }

    public static IntNum logop(int i, IntNum intnum, IntNum intnum1)
    {
        return BitOps.bitOp(i, intnum, intnum1);
    }

    public static boolean logtest(IntNum intnum, IntNum intnum1)
    {
        return BitOps.test(intnum, intnum1);
    }

    public static Number magnitude(Number number)
    {
        return abs(number);
    }

    public static DComplex makePolar(double d, double d1)
    {
        return Complex.polar(d, d1);
    }

    public static Quantity makeQuantity(Object obj, Object obj1)
    {
        if (obj1 instanceof Unit)
        {
            String s;
            Object obj2;
            Complex complex;
            Unit unit;
            try
            {
                unit = (Unit)obj1;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "u", -2, obj1);
            }
            obj2 = unit;
        } else
        {
            if (obj1 == null)
            {
                s = null;
            } else
            {
                s = obj1.toString();
            }
            obj2 = Unit.lookup(s);
        }
        if (obj2 == null)
        {
            throw (Throwable)new IllegalArgumentException(Format.formatToString(0, new Object[] {
                "unknown unit: ~s", obj1
            }).toString());
        }
        try
        {
            complex = (Complex)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "gnu.math.Quantity.make(gnu.math.Complex,gnu.math.Unit)", 1, obj);
        }
        return Quantity.make(complex, ((Unit) (obj2)));
    }

    public static Complex makeRectangular(RealNum realnum, RealNum realnum1)
    {
        return Complex.make(realnum, realnum1);
    }

    public static transient Object max(Object aobj[])
    {
        int i = aobj.length;
        Object obj = aobj[0];
        RealNum realnum;
        RealNum realnum1;
        int j;
        try
        {
            realnum = LangObjType.coerceRealNum(obj);
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "result", -2, obj);
        }
        realnum1 = realnum;
        j = 1;
        while (j < i) 
        {
            Object obj1 = aobj[j];
            RealNum realnum2;
            try
            {
                realnum2 = LangObjType.coerceRealNum(obj1);
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "gnu.math.RealNum.max(real)", 2, obj1);
            }
            realnum1 = realnum1.max(realnum2);
            j++;
        }
        return realnum1;
    }

    public static transient Object min(Object aobj[])
    {
        int i = aobj.length;
        Object obj = aobj[0];
        RealNum realnum;
        RealNum realnum1;
        int j;
        try
        {
            realnum = LangObjType.coerceRealNum(obj);
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "result", -2, obj);
        }
        realnum1 = realnum;
        j = 0;
        while (j < i) 
        {
            Object obj1 = aobj[j];
            RealNum realnum2;
            try
            {
                realnum2 = LangObjType.coerceRealNum(obj1);
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "gnu.math.RealNum.min(real)", 2, obj1);
            }
            realnum1 = realnum1.min(realnum2);
            j++;
        }
        return realnum1;
    }

    public static CharSequence number$To$String(Number number)
    {
        return number$To$String(number, 10);
    }

    public static CharSequence number$To$String(Number number, int i)
    {
        return new FString(Arithmetic.toString(number, i));
    }

    public static IntNum numerator(RatNum ratnum)
    {
        return ratnum.numerator();
    }

    public static Complex quantity$To$Number(Quantity quantity)
    {
        quantity.unit();
        if (quantity.doubleValue() == 1.0D)
        {
            return quantity.number();
        } else
        {
            return Complex.make(quantity.reValue(), quantity.imValue());
        }
    }

    public static Unit quantity$To$Unit(Quantity quantity)
    {
        return quantity.unit();
    }

    public static RealNum rationalize(RealNum realnum, RealNum realnum1)
    {
        return RatNum.rationalize(LangObjType.coerceRealNum(realnum.sub(realnum1)), LangObjType.coerceRealNum(realnum.add(realnum1)));
    }

    public static RealNum realPart(Complex complex)
    {
        return complex.re();
    }

    public static RealNum round(RealNum realnum)
    {
        return realnum.toInt(Numeric.ROUND);
    }

    public static double sin(double d)
    {
        return Math.sin(d);
    }

    public static Object string$To$Number(CharSequence charsequence)
    {
        return string$To$Number(charsequence, 10);
    }

    public static Object string$To$Number(CharSequence charsequence, int i)
    {
        Object obj = LispReader.parseNumber(charsequence, i);
        if (obj instanceof Numeric)
        {
            return obj;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static double tan(double d)
    {
        return Math.tan(d);
    }

    public static RealNum truncate(RealNum realnum)
    {
        return realnum.toInt(Numeric.TRUNCATE);
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        case 12: // '\f'
        case 13: // '\r'
        case 15: // '\017'
        case 16: // '\020'
        case 17: // '\021'
        case 18: // '\022'
        case 25: // '\031'
        case 33: // '!'
        case 37: // '%'
        case 38: // '&'
        case 47: // '/'
        case 48: // '0'
        case 49: // '1'
        case 50: // '2'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
        case 58: // ':'
        case 59: // ';'
        case 61: // '='
        case 63: // '?'
        case 66: // 'B'
        default:
            return super.apply1(modulemethod, obj);

        case 1: // '\001'
            if (isNumber(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 2: // '\002'
            if (isQuantity(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 3: // '\003'
            if (isComplex(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 4: // '\004'
            if (isReal(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 5: // '\005'
            if (isRational(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 6: // '\006'
            if (isInteger(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 7: // '\007'
            if (isExact(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 8: // '\b'
            if (isInexact(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 9: // '\t'
            Quantity quantity;
            Quantity quantity1;
            CharSequence charsequence;
            Number number;
            IntNum intnum;
            IntNum intnum1;
            IntNum intnum2;
            IntNum intnum3;
            Number number1;
            Number number2;
            Number number3;
            Number number4;
            Complex complex;
            Number number5;
            Complex complex1;
            Complex complex2;
            double d;
            Quantity quantity2;
            double d1;
            double d2;
            double d3;
            double d4;
            double d5;
            double d6;
            Complex complex3;
            Complex complex4;
            RealNum realnum;
            RealNum realnum1;
            RealNum realnum2;
            RealNum realnum3;
            RatNum ratnum;
            RatNum ratnum1;
            Number number6;
            RealNum realnum4;
            RealNum realnum5;
            Number number7;
            try
            {
                number7 = (Number)obj;
            }
            catch (ClassCastException classcastexception35)
            {
                throw new WrongType(classcastexception35, "zero?", 1, obj);
            }
            if (isZero(number7))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 10: // '\n'
            try
            {
                realnum5 = LangObjType.coerceRealNum(obj);
            }
            catch (ClassCastException classcastexception34)
            {
                throw new WrongType(classcastexception34, "positive?", 1, obj);
            }
            if (isPositive(realnum5))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 11: // '\013'
            try
            {
                realnum4 = LangObjType.coerceRealNum(obj);
            }
            catch (ClassCastException classcastexception33)
            {
                throw new WrongType(classcastexception33, "negative?", 1, obj);
            }
            if (isNegative(realnum4))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 14: // '\016'
            try
            {
                number6 = (Number)obj;
            }
            catch (ClassCastException classcastexception32)
            {
                throw new WrongType(classcastexception32, "abs", 1, obj);
            }
            return abs(number6);

        case 19: // '\023'
            try
            {
                ratnum1 = LangObjType.coerceRatNum(obj);
            }
            catch (ClassCastException classcastexception31)
            {
                throw new WrongType(classcastexception31, "numerator", 1, obj);
            }
            return numerator(ratnum1);

        case 20: // '\024'
            try
            {
                ratnum = LangObjType.coerceRatNum(obj);
            }
            catch (ClassCastException classcastexception30)
            {
                throw new WrongType(classcastexception30, "denominator", 1, obj);
            }
            return denominator(ratnum);

        case 21: // '\025'
            try
            {
                realnum3 = LangObjType.coerceRealNum(obj);
            }
            catch (ClassCastException classcastexception29)
            {
                throw new WrongType(classcastexception29, "floor", 1, obj);
            }
            return floor(realnum3);

        case 22: // '\026'
            try
            {
                realnum2 = LangObjType.coerceRealNum(obj);
            }
            catch (ClassCastException classcastexception28)
            {
                throw new WrongType(classcastexception28, "ceiling", 1, obj);
            }
            return ceiling(realnum2);

        case 23: // '\027'
            try
            {
                realnum1 = LangObjType.coerceRealNum(obj);
            }
            catch (ClassCastException classcastexception27)
            {
                throw new WrongType(classcastexception27, "truncate", 1, obj);
            }
            return truncate(realnum1);

        case 24: // '\030'
            try
            {
                realnum = LangObjType.coerceRealNum(obj);
            }
            catch (ClassCastException classcastexception26)
            {
                throw new WrongType(classcastexception26, "round", 1, obj);
            }
            return round(realnum);

        case 26: // '\032'
            try
            {
                complex4 = (Complex)obj;
            }
            catch (ClassCastException classcastexception25)
            {
                throw new WrongType(classcastexception25, "exp", 1, obj);
            }
            return exp(complex4);

        case 27: // '\033'
            try
            {
                complex3 = (Complex)obj;
            }
            catch (ClassCastException classcastexception24)
            {
                throw new WrongType(classcastexception24, "log", 1, obj);
            }
            return log(complex3);

        case 28: // '\034'
            try
            {
                d6 = ((Number)obj).doubleValue();
            }
            catch (ClassCastException classcastexception23)
            {
                throw new WrongType(classcastexception23, "sin", 1, obj);
            }
            return Double.valueOf(sin(d6));

        case 29: // '\035'
            try
            {
                d5 = ((Number)obj).doubleValue();
            }
            catch (ClassCastException classcastexception22)
            {
                throw new WrongType(classcastexception22, "cos", 1, obj);
            }
            return Double.valueOf(cos(d5));

        case 30: // '\036'
            try
            {
                d4 = ((Number)obj).doubleValue();
            }
            catch (ClassCastException classcastexception21)
            {
                throw new WrongType(classcastexception21, "tan", 1, obj);
            }
            return Double.valueOf(tan(d4));

        case 31: // '\037'
            try
            {
                d3 = ((Number)obj).doubleValue();
            }
            catch (ClassCastException classcastexception20)
            {
                throw new WrongType(classcastexception20, "asin", 1, obj);
            }
            return Double.valueOf(asin(d3));

        case 32: // ' '
            try
            {
                d2 = ((Number)obj).doubleValue();
            }
            catch (ClassCastException classcastexception19)
            {
                throw new WrongType(classcastexception19, "acos", 1, obj);
            }
            return Double.valueOf(acos(d2));

        case 34: // '"'
            try
            {
                d1 = ((Number)obj).doubleValue();
            }
            catch (ClassCastException classcastexception18)
            {
                throw new WrongType(classcastexception18, "lambda", 1, obj);
            }
            return Double.valueOf(lambda2(d1));

        case 35: // '#'
            try
            {
                quantity2 = (Quantity)obj;
            }
            catch (ClassCastException classcastexception17)
            {
                throw new WrongType(classcastexception17, "lambda", 1, obj);
            }
            return lambda3(quantity2);

        case 36: // '$'
            try
            {
                d = ((Number)obj).doubleValue();
            }
            catch (ClassCastException classcastexception16)
            {
                throw new WrongType(classcastexception16, "lambda", 1, obj);
            }
            return Double.valueOf(lambda4(d));

        case 39: // '\''
            try
            {
                complex2 = (Complex)obj;
            }
            catch (ClassCastException classcastexception15)
            {
                throw new WrongType(classcastexception15, "real-part", 1, obj);
            }
            return realPart(complex2);

        case 40: // '('
            try
            {
                complex1 = (Complex)obj;
            }
            catch (ClassCastException classcastexception14)
            {
                throw new WrongType(classcastexception14, "imag-part", 1, obj);
            }
            return imagPart(complex1);

        case 41: // ')'
            try
            {
                number5 = (Number)obj;
            }
            catch (ClassCastException classcastexception13)
            {
                throw new WrongType(classcastexception13, "magnitude", 1, obj);
            }
            return magnitude(number5);

        case 42: // '*'
            try
            {
                complex = (Complex)obj;
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "angle", 1, obj);
            }
            return angle(complex);

        case 43: // '+'
            try
            {
                number4 = (Number)obj;
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "inexact", 1, obj);
            }
            return inexact(number4);

        case 44: // ','
            try
            {
                number3 = (Number)obj;
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "exact", 1, obj);
            }
            return exact(number3);

        case 45: // '-'
            try
            {
                number2 = (Number)obj;
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "exact->inexact", 1, obj);
            }
            return exact$To$Inexact(number2);

        case 46: // '.'
            try
            {
                number1 = (Number)obj;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "inexact->exact", 1, obj);
            }
            return inexact$To$Exact(number1);

        case 54: // '6'
            try
            {
                intnum3 = LangObjType.coerceIntNum(obj);
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "logcount", 1, obj);
            }
            return Integer.valueOf(logcount(intnum3));

        case 55: // '7'
            try
            {
                intnum2 = LangObjType.coerceIntNum(obj);
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "bitwise-bit-count", 1, obj);
            }
            return Integer.valueOf(bitwiseBitCount(intnum2));

        case 56: // '8'
            try
            {
                intnum1 = LangObjType.coerceIntNum(obj);
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "bitwise-length", 1, obj);
            }
            return Integer.valueOf(bitwiseLength(intnum1));

        case 57: // '9'
            try
            {
                intnum = LangObjType.coerceIntNum(obj);
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "bitwise-first-bit-set", 1, obj);
            }
            return Integer.valueOf(bitwiseFirstBitSet(intnum));

        case 60: // '<'
            try
            {
                number = (Number)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "number->string", 1, obj);
            }
            return number$To$String(number);

        case 62: // '>'
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string->number", 1, obj);
            }
            return string$To$Number(charsequence);

        case 64: // '@'
            try
            {
                quantity1 = (Quantity)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "quantity->number", 1, obj);
            }
            return quantity$To$Number(quantity1);

        case 65: // 'A'
            try
            {
                quantity = (Quantity)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "quantity->unit", 1, obj);
            }
            return quantity$To$Unit(quantity);

        case 67: // 'C'
            return duration(obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 15: // '\017'
            CharSequence charsequence;
            int i;
            Number number;
            int j;
            IntNum intnum;
            IntNum intnum1;
            IntNum intnum2;
            int k;
            double d;
            double d1;
            RealNum realnum;
            RealNum realnum1;
            double d2;
            double d3;
            RealNum realnum2;
            RealNum realnum3;
            RealNum realnum4;
            RealNum realnum5;
            RealNum realnum6;
            RealNum realnum7;
            try
            {
                realnum6 = LangObjType.coerceRealNum(obj);
            }
            catch (ClassCastException classcastexception18)
            {
                throw new WrongType(classcastexception18, "div-and-mod", 1, obj);
            }
            try
            {
                realnum7 = LangObjType.coerceRealNum(obj1);
            }
            catch (ClassCastException classcastexception19)
            {
                throw new WrongType(classcastexception19, "div-and-mod", 2, obj1);
            }
            return divAndMod(realnum6, realnum7);

        case 16: // '\020'
            try
            {
                realnum4 = LangObjType.coerceRealNum(obj);
            }
            catch (ClassCastException classcastexception16)
            {
                throw new WrongType(classcastexception16, "div0-and-mod0", 1, obj);
            }
            try
            {
                realnum5 = LangObjType.coerceRealNum(obj1);
            }
            catch (ClassCastException classcastexception17)
            {
                throw new WrongType(classcastexception17, "div0-and-mod0", 2, obj1);
            }
            return div0AndMod0(realnum4, realnum5);

        case 25: // '\031'
            try
            {
                realnum2 = LangObjType.coerceRealNum(obj);
            }
            catch (ClassCastException classcastexception14)
            {
                throw new WrongType(classcastexception14, "rationalize", 1, obj);
            }
            try
            {
                realnum3 = LangObjType.coerceRealNum(obj1);
            }
            catch (ClassCastException classcastexception15)
            {
                throw new WrongType(classcastexception15, "rationalize", 2, obj1);
            }
            return rationalize(realnum2, realnum3);

        case 33: // '!'
            try
            {
                d2 = ((Number)obj).doubleValue();
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "lambda", 1, obj);
            }
            try
            {
                d3 = ((Number)obj1).doubleValue();
            }
            catch (ClassCastException classcastexception13)
            {
                throw new WrongType(classcastexception13, "lambda", 2, obj1);
            }
            return Double.valueOf(lambda1(d2, d3));

        case 37: // '%'
            try
            {
                realnum = LangObjType.coerceRealNum(obj);
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "make-rectangular", 1, obj);
            }
            try
            {
                realnum1 = LangObjType.coerceRealNum(obj1);
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "make-rectangular", 2, obj1);
            }
            return makeRectangular(realnum, realnum1);

        case 38: // '&'
            try
            {
                d = ((Number)obj).doubleValue();
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "make-polar", 1, obj);
            }
            try
            {
                d1 = ((Number)obj1).doubleValue();
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "make-polar", 2, obj1);
            }
            return makePolar(d, d1);

        case 48: // '0'
            try
            {
                intnum2 = LangObjType.coerceIntNum(obj);
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "bitwise-bit-set?", 1, obj);
            }
            try
            {
                k = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "bitwise-bit-set?", 2, obj1);
            }
            if (isBitwiseBitSet(intnum2, k))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 53: // '5'
            try
            {
                intnum = LangObjType.coerceIntNum(obj);
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "logtest", 1, obj);
            }
            try
            {
                intnum1 = LangObjType.coerceIntNum(obj1);
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "logtest", 2, obj1);
            }
            if (logtest(intnum, intnum1))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 60: // '<'
            try
            {
                number = (Number)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "number->string", 1, obj);
            }
            try
            {
                j = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "number->string", 2, obj1);
            }
            return number$To$String(number, j);

        case 62: // '>'
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string->number", 1, obj);
            }
            try
            {
                i = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string->number", 2, obj1);
            }
            return string$To$Number(charsequence, i);

        case 66: // 'B'
            return makeQuantity(obj, obj1);
        }
    }

    public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply3(modulemethod, obj, obj1, obj2);

        case 47: // '/'
            IntNum intnum;
            int i;
            int j;
            IntNum intnum1;
            IntNum intnum2;
            IntNum intnum3;
            IntNum intnum4;
            int k;
            int l;
            IntNum intnum5;
            int i1;
            int j1;
            int k1;
            IntNum intnum6;
            IntNum intnum7;
            try
            {
                k1 = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "logop", 1, obj);
            }
            try
            {
                intnum6 = LangObjType.coerceIntNum(obj1);
            }
            catch (ClassCastException classcastexception13)
            {
                throw new WrongType(classcastexception13, "logop", 2, obj1);
            }
            try
            {
                intnum7 = LangObjType.coerceIntNum(obj2);
            }
            catch (ClassCastException classcastexception14)
            {
                throw new WrongType(classcastexception14, "logop", 3, obj2);
            }
            return logop(k1, intnum6, intnum7);

        case 49: // '1'
            try
            {
                intnum5 = LangObjType.coerceIntNum(obj);
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "bitwise-copy-bit", 1, obj);
            }
            try
            {
                i1 = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "bitwise-copy-bit", 2, obj1);
            }
            try
            {
                j1 = ((Number)obj2).intValue();
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "bitwise-copy-bit", 3, obj2);
            }
            return bitwiseCopyBit(intnum5, i1, j1);

        case 51: // '3'
            try
            {
                intnum4 = LangObjType.coerceIntNum(obj);
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "bitwise-bit-field", 1, obj);
            }
            try
            {
                k = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "bitwise-bit-field", 2, obj1);
            }
            try
            {
                l = ((Number)obj2).intValue();
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "bitwise-bit-field", 3, obj2);
            }
            return bitwiseBitField(intnum4, k, l);

        case 52: // '4'
            try
            {
                intnum1 = LangObjType.coerceIntNum(obj);
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "bitwise-if", 1, obj);
            }
            try
            {
                intnum2 = LangObjType.coerceIntNum(obj1);
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "bitwise-if", 2, obj1);
            }
            try
            {
                intnum3 = LangObjType.coerceIntNum(obj2);
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "bitwise-if", 3, obj2);
            }
            return bitwiseIf(intnum1, intnum2, intnum3);

        case 59: // ';'
            break;
        }
        try
        {
            intnum = LangObjType.coerceIntNum(obj);
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "bitwise-reverse-bit-field", 1, obj);
        }
        try
        {
            i = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "bitwise-reverse-bit-field", 2, obj1);
        }
        try
        {
            j = ((Number)obj2).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "bitwise-reverse-bit-field", 3, obj2);
        }
        return bitwiseReverseBitField(intnum, i, j);
    }

    public Object apply4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply4(modulemethod, obj, obj1, obj2, obj3);

        case 50: // '2'
            IntNum intnum;
            int i;
            int j;
            int k;
            IntNum intnum1;
            int l;
            int i1;
            IntNum intnum2;
            try
            {
                intnum1 = LangObjType.coerceIntNum(obj);
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "bitwise-copy-bit-field", 1, obj);
            }
            try
            {
                l = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "bitwise-copy-bit-field", 2, obj1);
            }
            try
            {
                i1 = ((Number)obj2).intValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "bitwise-copy-bit-field", 3, obj2);
            }
            try
            {
                intnum2 = LangObjType.coerceIntNum(obj3);
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "bitwise-copy-bit-field", 4, obj3);
            }
            return bitwiseCopyBitField(intnum1, l, i1, intnum2);

        case 58: // ':'
            break;
        }
        try
        {
            intnum = LangObjType.coerceIntNum(obj);
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "bitwise-rotate-bit-field", 1, obj);
        }
        try
        {
            i = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "bitwise-rotate-bit-field", 2, obj1);
        }
        try
        {
            j = ((Number)obj2).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "bitwise-rotate-bit-field", 3, obj2);
        }
        try
        {
            k = ((Number)obj3).intValue();
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "bitwise-rotate-bit-field", 4, obj3);
        }
        return bitwiseRotateBitField(intnum, i, j, k);
    }

    public Object applyN(ModuleMethod modulemethod, Object aobj[])
    {
        switch (modulemethod.selector)
        {
        case 14: // '\016'
        case 15: // '\017'
        case 16: // '\020'
        default:
            return super.applyN(modulemethod, aobj);

        case 12: // '\f'
            return max(aobj);

        case 13: // '\r'
            return min(aobj);

        case 17: // '\021'
            int j = aobj.length;
            IntNum aintnum1[] = new IntNum[j];
            do
            {
                if (--j < 0)
                {
                    return gcd(aintnum1);
                }
                Object obj1 = aobj[j];
                int i;
                IntNum aintnum[];
                IntNum intnum;
                IntNum intnum1;
                try
                {
                    intnum1 = LangObjType.coerceIntNum(obj1);
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "gcd", 0, obj1);
                }
                aintnum1[j] = intnum1;
            } while (true);

        case 18: // '\022'
            i = aobj.length;
            aintnum = new IntNum[i];
            break;
        }
        do
        {
            if (--i < 0)
            {
                return lcm(aintnum);
            }
            Object obj = aobj[i];
            try
            {
                intnum = LangObjType.coerceIntNum(obj);
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "lcm", 0, obj);
            }
            aintnum[i] = intnum;
        } while (true);
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 12: // '\f'
        case 13: // '\r'
        case 15: // '\017'
        case 16: // '\020'
        case 17: // '\021'
        case 18: // '\022'
        case 25: // '\031'
        case 33: // '!'
        case 37: // '%'
        case 38: // '&'
        case 47: // '/'
        case 48: // '0'
        case 49: // '1'
        case 50: // '2'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
        case 58: // ':'
        case 59: // ';'
        case 61: // '='
        case 63: // '?'
        case 66: // 'B'
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 67: // 'C'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 65: // 'A'
            if (!(obj instanceof Quantity))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 64: // '@'
            if (!(obj instanceof Quantity))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 62: // '>'
            if (obj instanceof CharSequence)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 60: // '<'
            if (!(obj instanceof Number))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 57: // '9'
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

        case 56: // '8'
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

        case 55: // '7'
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

        case 54: // '6'
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

        case 46: // '.'
            if (!(obj instanceof Number))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 45: // '-'
            if (!(obj instanceof Number))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 44: // ','
            if (!(obj instanceof Number))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 43: // '+'
            if (!(obj instanceof Number))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 42: // '*'
            if (!(obj instanceof Complex))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 41: // ')'
            if (!(obj instanceof Number))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 40: // '('
            if (!(obj instanceof Complex))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 39: // '\''
            if (!(obj instanceof Complex))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 36: // '$'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 35: // '#'
            if (!(obj instanceof Quantity))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 34: // '"'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 32: // ' '
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 31: // '\037'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 30: // '\036'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 29: // '\035'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 28: // '\034'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 27: // '\033'
            if (!(obj instanceof Complex))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 26: // '\032'
            if (!(obj instanceof Complex))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 24: // '\030'
            if (RealNum.asRealNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 23: // '\027'
            if (RealNum.asRealNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 22: // '\026'
            if (RealNum.asRealNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 21: // '\025'
            if (RealNum.asRealNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 20: // '\024'
            if (RatNum.asRatNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 19: // '\023'
            if (RatNum.asRatNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 14: // '\016'
            if (!(obj instanceof Number))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 11: // '\013'
            if (RealNum.asRealNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 10: // '\n'
            if (RealNum.asRealNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 9: // '\t'
            if (!(obj instanceof Number))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 8: // '\b'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 7: // '\007'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 6: // '\006'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 5: // '\005'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 4: // '\004'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 3: // '\003'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 2: // '\002'
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
        default:
            return super.match2(modulemethod, obj, obj1, callcontext);

        case 66: // 'B'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 62: // '>'
            if (obj instanceof CharSequence)
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 60: // '<'
            if (!(obj instanceof Number))
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

        case 53: // '5'
            if (IntNum.asIntNumOrNull(obj) != null)
            {
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
            } else
            {
                return 0xfff40001;
            }

        case 48: // '0'
            if (IntNum.asIntNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 38: // '&'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 37: // '%'
            if (RealNum.asRealNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                if (RealNum.asRealNumOrNull(obj1) != null)
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

        case 33: // '!'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 25: // '\031'
            if (RealNum.asRealNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                if (RealNum.asRealNumOrNull(obj1) != null)
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

        case 16: // '\020'
            if (RealNum.asRealNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                if (RealNum.asRealNumOrNull(obj1) != null)
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

        case 15: // '\017'
            break;
        }
        if (RealNum.asRealNumOrNull(obj) != null)
        {
            callcontext.value1 = obj;
            if (RealNum.asRealNumOrNull(obj1) != null)
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
    }

    public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match3(modulemethod, obj, obj1, obj2, callcontext);

        case 59: // ';'
            if (IntNum.asIntNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 52: // '4'
            if (IntNum.asIntNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                if (IntNum.asIntNumOrNull(obj1) != null)
                {
                    callcontext.value2 = obj1;
                    if (IntNum.asIntNumOrNull(obj2) != null)
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

        case 51: // '3'
            if (IntNum.asIntNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 49: // '1'
            if (IntNum.asIntNumOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 47: // '/'
            callcontext.value1 = obj;
            break;
        }
        if (IntNum.asIntNumOrNull(obj1) != null)
        {
            callcontext.value2 = obj1;
            if (IntNum.asIntNumOrNull(obj2) != null)
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
    }

    public int match4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match4(modulemethod, obj, obj1, obj2, obj3, callcontext);

        case 58: // ':'
            if (IntNum.asIntNumOrNull(obj) != null)
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
                return 0xfff40001;
            }

        case 50: // '2'
            break;
        }
        if (IntNum.asIntNumOrNull(obj) != null)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            if (IntNum.asIntNumOrNull(obj3) != null)
            {
                callcontext.value4 = obj3;
                callcontext.proc = modulemethod;
                callcontext.pc = 4;
                return 0;
            } else
            {
                return 0xfff40004;
            }
        } else
        {
            return 0xfff40001;
        }
    }

    public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 14: // '\016'
        case 15: // '\017'
        case 16: // '\020'
        default:
            return super.matchN(modulemethod, aobj, callcontext);

        case 18: // '\022'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 17: // '\021'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 13: // '\r'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 12: // '\f'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
        atan = new GenericProc("atan");
        GenericProc genericproc = atan;
        Object aobj[] = new Object[2];
        aobj[0] = lambda$Fn1;
        aobj[1] = lambda$Fn2;
        genericproc.setProperties(aobj);
        sqrt = new GenericProc("sqrt");
        GenericProc genericproc1 = sqrt;
        Object aobj1[] = new Object[2];
        aobj1[0] = lambda$Fn3;
        aobj1[1] = lambda$Fn4;
        genericproc1.setProperties(aobj1);
    }

    static 
    {
        Lit63 = (SimpleSymbol)(new SimpleSymbol("duration")).readResolve();
        Lit62 = (SimpleSymbol)(new SimpleSymbol("make-quantity")).readResolve();
        Lit61 = (SimpleSymbol)(new SimpleSymbol("quantity->unit")).readResolve();
        Lit60 = (SimpleSymbol)(new SimpleSymbol("quantity->number")).readResolve();
        Lit59 = (SimpleSymbol)(new SimpleSymbol("string->number")).readResolve();
        Lit58 = (SimpleSymbol)(new SimpleSymbol("number->string")).readResolve();
        Lit57 = (SimpleSymbol)(new SimpleSymbol("bitwise-reverse-bit-field")).readResolve();
        Lit56 = (SimpleSymbol)(new SimpleSymbol("bitwise-rotate-bit-field")).readResolve();
        Lit55 = (SimpleSymbol)(new SimpleSymbol("bitwise-first-bit-set")).readResolve();
        Lit54 = (SimpleSymbol)(new SimpleSymbol("bitwise-length")).readResolve();
        Lit53 = (SimpleSymbol)(new SimpleSymbol("bitwise-bit-count")).readResolve();
        Lit52 = (SimpleSymbol)(new SimpleSymbol("logcount")).readResolve();
        Lit51 = (SimpleSymbol)(new SimpleSymbol("logtest")).readResolve();
        Lit50 = (SimpleSymbol)(new SimpleSymbol("bitwise-if")).readResolve();
        Lit49 = (SimpleSymbol)(new SimpleSymbol("bitwise-bit-field")).readResolve();
        Lit48 = (SimpleSymbol)(new SimpleSymbol("bitwise-copy-bit-field")).readResolve();
        Lit47 = (SimpleSymbol)(new SimpleSymbol("bitwise-copy-bit")).readResolve();
        Lit46 = (SimpleSymbol)(new SimpleSymbol("bitwise-bit-set?")).readResolve();
        Lit45 = (SimpleSymbol)(new SimpleSymbol("logop")).readResolve();
        Lit44 = (SimpleSymbol)(new SimpleSymbol("inexact->exact")).readResolve();
        Lit43 = (SimpleSymbol)(new SimpleSymbol("exact->inexact")).readResolve();
        Lit42 = (SimpleSymbol)(new SimpleSymbol("exact")).readResolve();
        Lit41 = (SimpleSymbol)(new SimpleSymbol("inexact")).readResolve();
        Lit40 = (SimpleSymbol)(new SimpleSymbol("angle")).readResolve();
        Lit39 = (SimpleSymbol)(new SimpleSymbol("magnitude")).readResolve();
        Lit38 = (SimpleSymbol)(new SimpleSymbol("imag-part")).readResolve();
        Lit37 = (SimpleSymbol)(new SimpleSymbol("real-part")).readResolve();
        Lit36 = (SimpleSymbol)(new SimpleSymbol("make-polar")).readResolve();
        Lit35 = (SimpleSymbol)(new SimpleSymbol("make-rectangular")).readResolve();
        Lit34 = (SimpleSymbol)(new SimpleSymbol("acos")).readResolve();
        Lit33 = (SimpleSymbol)(new SimpleSymbol("asin")).readResolve();
        Lit32 = (SimpleSymbol)(new SimpleSymbol("tan")).readResolve();
        Lit31 = (SimpleSymbol)(new SimpleSymbol("cos")).readResolve();
        Lit30 = (SimpleSymbol)(new SimpleSymbol("sin")).readResolve();
        Lit29 = (SimpleSymbol)(new SimpleSymbol("log")).readResolve();
        Lit28 = (SimpleSymbol)(new SimpleSymbol("exp")).readResolve();
        Lit27 = (SimpleSymbol)(new SimpleSymbol("rationalize")).readResolve();
        Lit26 = (SimpleSymbol)(new SimpleSymbol("round")).readResolve();
        Lit25 = (SimpleSymbol)(new SimpleSymbol("truncate")).readResolve();
        Lit24 = (SimpleSymbol)(new SimpleSymbol("ceiling")).readResolve();
        Lit23 = (SimpleSymbol)(new SimpleSymbol("floor")).readResolve();
        Lit22 = (SimpleSymbol)(new SimpleSymbol("denominator")).readResolve();
        Lit21 = (SimpleSymbol)(new SimpleSymbol("numerator")).readResolve();
        Lit20 = (SimpleSymbol)(new SimpleSymbol("lcm")).readResolve();
        Lit19 = (SimpleSymbol)(new SimpleSymbol("gcd")).readResolve();
        Lit18 = (SimpleSymbol)(new SimpleSymbol("div0-and-mod0")).readResolve();
        Lit17 = (SimpleSymbol)(new SimpleSymbol("div-and-mod")).readResolve();
        Lit16 = (SimpleSymbol)(new SimpleSymbol("abs")).readResolve();
        Lit15 = (SimpleSymbol)(new SimpleSymbol("min")).readResolve();
        Lit14 = (SimpleSymbol)(new SimpleSymbol("max")).readResolve();
        Lit13 = (SimpleSymbol)(new SimpleSymbol("negative?")).readResolve();
        Lit12 = (SimpleSymbol)(new SimpleSymbol("positive?")).readResolve();
        Lit11 = (SimpleSymbol)(new SimpleSymbol("zero?")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("inexact?")).readResolve();
        Lit9 = (SimpleSymbol)(new SimpleSymbol("exact?")).readResolve();
        Lit8 = (SimpleSymbol)(new SimpleSymbol("integer?")).readResolve();
        Lit7 = (SimpleSymbol)(new SimpleSymbol("rational?")).readResolve();
        Lit6 = (SimpleSymbol)(new SimpleSymbol("real?")).readResolve();
        Lit5 = (SimpleSymbol)(new SimpleSymbol("complex?")).readResolve();
        Lit4 = (SimpleSymbol)(new SimpleSymbol("quantity?")).readResolve();
        Lit3 = (SimpleSymbol)(new SimpleSymbol("number?")).readResolve();
        $instance = new numbers();
        numbers numbers1 = $instance;
        number$Qu = new ModuleMethod(numbers1, 1, Lit3, 4097);
        quantity$Qu = new ModuleMethod(numbers1, 2, Lit4, 4097);
        complex$Qu = new ModuleMethod(numbers1, 3, Lit5, 4097);
        real$Qu = new ModuleMethod(numbers1, 4, Lit6, 4097);
        rational$Qu = new ModuleMethod(numbers1, 5, Lit7, 4097);
        integer$Qu = new ModuleMethod(numbers1, 6, Lit8, 4097);
        exact$Qu = new ModuleMethod(numbers1, 7, Lit9, 4097);
        inexact$Qu = new ModuleMethod(numbers1, 8, Lit10, 4097);
        zero$Qu = new ModuleMethod(numbers1, 9, Lit11, 4097);
        positive$Qu = new ModuleMethod(numbers1, 10, Lit12, 4097);
        negative$Qu = new ModuleMethod(numbers1, 11, Lit13, 4097);
        max = new ModuleMethod(numbers1, 12, Lit14, -4096);
        min = new ModuleMethod(numbers1, 13, Lit15, -4096);
        abs = new ModuleMethod(numbers1, 14, Lit16, 4097);
        div$Mnand$Mnmod = new ModuleMethod(numbers1, 15, Lit17, 8194);
        div0$Mnand$Mnmod0 = new ModuleMethod(numbers1, 16, Lit18, 8194);
        gcd = new ModuleMethod(numbers1, 17, Lit19, -4096);
        lcm = new ModuleMethod(numbers1, 18, Lit20, -4096);
        numerator = new ModuleMethod(numbers1, 19, Lit21, 4097);
        denominator = new ModuleMethod(numbers1, 20, Lit22, 4097);
        floor = new ModuleMethod(numbers1, 21, Lit23, 4097);
        ceiling = new ModuleMethod(numbers1, 22, Lit24, 4097);
        truncate = new ModuleMethod(numbers1, 23, Lit25, 4097);
        round = new ModuleMethod(numbers1, 24, Lit26, 4097);
        rationalize = new ModuleMethod(numbers1, 25, Lit27, 8194);
        exp = new ModuleMethod(numbers1, 26, Lit28, 4097);
        log = new ModuleMethod(numbers1, 27, Lit29, 4097);
        sin = new ModuleMethod(numbers1, 28, Lit30, 4097);
        cos = new ModuleMethod(numbers1, 29, Lit31, 4097);
        tan = new ModuleMethod(numbers1, 30, Lit32, 4097);
        asin = new ModuleMethod(numbers1, 31, Lit33, 4097);
        acos = new ModuleMethod(numbers1, 32, Lit34, 4097);
        ModuleMethod modulemethod = new ModuleMethod(numbers1, 33, null, 8194);
        modulemethod.setProperty("source-location", "numbers.scm:146");
        lambda$Fn1 = modulemethod;
        ModuleMethod modulemethod1 = new ModuleMethod(numbers1, 34, null, 4097);
        modulemethod1.setProperty("source-location", "numbers.scm:148");
        lambda$Fn2 = modulemethod1;
        ModuleMethod modulemethod2 = new ModuleMethod(numbers1, 35, null, 4097);
        modulemethod2.setProperty("source-location", "numbers.scm:152");
        lambda$Fn3 = modulemethod2;
        ModuleMethod modulemethod3 = new ModuleMethod(numbers1, 36, null, 4097);
        modulemethod3.setProperty("source-location", "numbers.scm:156");
        lambda$Fn4 = modulemethod3;
        make$Mnrectangular = new ModuleMethod(numbers1, 37, Lit35, 8194);
        make$Mnpolar = new ModuleMethod(numbers1, 38, Lit36, 8194);
        real$Mnpart = new ModuleMethod(numbers1, 39, Lit37, 4097);
        imag$Mnpart = new ModuleMethod(numbers1, 40, Lit38, 4097);
        magnitude = new ModuleMethod(numbers1, 41, Lit39, 4097);
        angle = new ModuleMethod(numbers1, 42, Lit40, 4097);
        inexact = new ModuleMethod(numbers1, 43, Lit41, 4097);
        exact = new ModuleMethod(numbers1, 44, Lit42, 4097);
        exact$Mn$Grinexact = new ModuleMethod(numbers1, 45, Lit43, 4097);
        inexact$Mn$Grexact = new ModuleMethod(numbers1, 46, Lit44, 4097);
        logop = new ModuleMethod(numbers1, 47, Lit45, 12291);
        bitwise$Mnbit$Mnset$Qu = new ModuleMethod(numbers1, 48, Lit46, 8194);
        bitwise$Mncopy$Mnbit = new ModuleMethod(numbers1, 49, Lit47, 12291);
        bitwise$Mncopy$Mnbit$Mnfield = new ModuleMethod(numbers1, 50, Lit48, 16388);
        bitwise$Mnbit$Mnfield = new ModuleMethod(numbers1, 51, Lit49, 12291);
        bitwise$Mnif = new ModuleMethod(numbers1, 52, Lit50, 12291);
        logtest = new ModuleMethod(numbers1, 53, Lit51, 8194);
        logcount = new ModuleMethod(numbers1, 54, Lit52, 4097);
        bitwise$Mnbit$Mncount = new ModuleMethod(numbers1, 55, Lit53, 4097);
        bitwise$Mnlength = new ModuleMethod(numbers1, 56, Lit54, 4097);
        bitwise$Mnfirst$Mnbit$Mnset = new ModuleMethod(numbers1, 57, Lit55, 4097);
        bitwise$Mnrotate$Mnbit$Mnfield = new ModuleMethod(numbers1, 58, Lit56, 16388);
        bitwise$Mnreverse$Mnbit$Mnfield = new ModuleMethod(numbers1, 59, Lit57, 12291);
        number$Mn$Grstring = new ModuleMethod(numbers1, 60, Lit58, 8193);
        string$Mn$Grnumber = new ModuleMethod(numbers1, 62, Lit59, 8193);
        quantity$Mn$Grnumber = new ModuleMethod(numbers1, 64, Lit60, 4097);
        quantity$Mn$Grunit = new ModuleMethod(numbers1, 65, Lit61, 4097);
        make$Mnquantity = new ModuleMethod(numbers1, 66, Lit62, 8194);
        duration = new ModuleMethod(numbers1, 67, Lit63, 4097);
        $instance.run();
    }
}
