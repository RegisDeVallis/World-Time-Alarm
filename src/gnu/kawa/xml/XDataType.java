// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.kawa.reflect.InstanceOf;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.DateTime;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.math.RealNum;
import gnu.math.Unit;
import gnu.text.Path;
import gnu.text.Printable;
import gnu.text.URIPath;
import gnu.xml.TextUtils;
import java.math.BigDecimal;

// Referenced classes of package gnu.kawa.xml:
//            KNode, UntypedAtomic, XTimeType, BinaryObject, 
//            Base64Binary, HexBinary, Nodes

public class XDataType extends Type
    implements TypeValue
{

    public static final int ANY_ATOMIC_TYPE_CODE = 3;
    public static final int ANY_SIMPLE_TYPE_CODE = 2;
    public static final int ANY_URI_TYPE_CODE = 33;
    public static final int BASE64_BINARY_TYPE_CODE = 34;
    public static final int BOOLEAN_TYPE_CODE = 31;
    public static final int BYTE_TYPE_CODE = 11;
    public static final int DATE_TIME_TYPE_CODE = 20;
    public static final int DATE_TYPE_CODE = 21;
    public static final int DAY_TIME_DURATION_TYPE_CODE = 30;
    public static final BigDecimal DECIMAL_ONE = BigDecimal.valueOf(1L);
    public static final int DECIMAL_TYPE_CODE = 4;
    public static final Double DOUBLE_ONE = makeDouble(1.0D);
    public static final int DOUBLE_TYPE_CODE = 19;
    public static final Double DOUBLE_ZERO = makeDouble(0.0D);
    public static final int DURATION_TYPE_CODE = 28;
    public static final int ENTITY_TYPE_CODE = 47;
    public static final Float FLOAT_ONE = makeFloat(1.0F);
    public static final int FLOAT_TYPE_CODE = 18;
    public static final Float FLOAT_ZERO = makeFloat(0.0F);
    public static final int G_DAY_TYPE_CODE = 26;
    public static final int G_MONTH_DAY_TYPE_CODE = 25;
    public static final int G_MONTH_TYPE_CODE = 27;
    public static final int G_YEAR_MONTH_TYPE_CODE = 23;
    public static final int G_YEAR_TYPE_CODE = 24;
    public static final int HEX_BINARY_TYPE_CODE = 35;
    public static final int IDREF_TYPE_CODE = 46;
    public static final int ID_TYPE_CODE = 45;
    public static final int INTEGER_TYPE_CODE = 5;
    public static final int INT_TYPE_CODE = 9;
    public static final int LANGUAGE_TYPE_CODE = 41;
    public static final int LONG_TYPE_CODE = 8;
    public static final int NAME_TYPE_CODE = 43;
    public static final int NCNAME_TYPE_CODE = 44;
    public static final int NEGATIVE_INTEGER_TYPE_CODE = 7;
    public static final int NMTOKEN_TYPE_CODE = 42;
    public static final int NONNEGATIVE_INTEGER_TYPE_CODE = 12;
    public static final int NON_POSITIVE_INTEGER_TYPE_CODE = 6;
    public static final int NORMALIZED_STRING_TYPE_CODE = 39;
    public static final int NOTATION_TYPE_CODE = 36;
    public static final XDataType NotationType = new XDataType("NOTATION", ClassType.make("gnu.kawa.xml.Notation"), 36);
    public static final int POSITIVE_INTEGER_TYPE_CODE = 17;
    public static final int QNAME_TYPE_CODE = 32;
    public static final int SHORT_TYPE_CODE = 10;
    public static final int STRING_TYPE_CODE = 38;
    public static final int TIME_TYPE_CODE = 22;
    public static final int TOKEN_TYPE_CODE = 40;
    public static final int UNSIGNED_BYTE_TYPE_CODE = 16;
    public static final int UNSIGNED_INT_TYPE_CODE = 14;
    public static final int UNSIGNED_LONG_TYPE_CODE = 13;
    public static final int UNSIGNED_SHORT_TYPE_CODE = 15;
    public static final int UNTYPED_ATOMIC_TYPE_CODE = 37;
    public static final int UNTYPED_TYPE_CODE = 48;
    public static final int YEAR_MONTH_DURATION_TYPE_CODE = 29;
    public static final XDataType anyAtomicType;
    public static final XDataType anySimpleType;
    public static final XDataType anyURIType = new XDataType("anyURI", ClassType.make("gnu.text.Path"), 33);
    public static final XDataType base64BinaryType = new XDataType("base64Binary", ClassType.make("gnu.kawa.xml.Base64Binary"), 34);
    public static final XDataType booleanType;
    public static final XDataType dayTimeDurationType = new XDataType("dayTimeDuration", ClassType.make("gnu.math.Duration"), 30);
    public static final XDataType decimalType = new XDataType("decimal", ClassType.make("java.lang.Number"), 4);
    public static final XDataType doubleType = new XDataType("double", ClassType.make("java.lang.Double"), 19);
    public static final XDataType durationType = new XDataType("duration", ClassType.make("gnu.math.Duration"), 28);
    public static final XDataType floatType = new XDataType("float", ClassType.make("java.lang.Float"), 18);
    public static final XDataType hexBinaryType = new XDataType("hexBinary", ClassType.make("gnu.kawa.xml.HexBinary"), 35);
    public static final XDataType stringStringType = new XDataType("String", ClassType.make("java.lang.String"), 38);
    public static final XDataType stringType = new XDataType("string", ClassType.make("java.lang.CharSequence"), 38);
    public static final XDataType untypedAtomicType = new XDataType("string", ClassType.make("gnu.kawa.xml.UntypedAtomic"), 37);
    public static final XDataType untypedType;
    public static final XDataType yearMonthDurationType = new XDataType("yearMonthDuration", ClassType.make("gnu.math.Duration"), 29);
    XDataType baseType;
    Type implementationType;
    Object name;
    int typeCode;

    public XDataType(Object obj, Type type, int i)
    {
        super(type);
        name = obj;
        if (obj != null)
        {
            setName(obj.toString());
        }
        implementationType = type;
        typeCode = i;
    }

    public static Double makeDouble(double d)
    {
        return Double.valueOf(d);
    }

    public static Float makeFloat(float f)
    {
        return Float.valueOf(f);
    }

    public Object cast(Object obj)
    {
        Object obj1 = KNode.atomicValue(obj);
        if (!(obj1 instanceof UntypedAtomic)) goto _L2; else goto _L1
_L1:
        if (typeCode != 37) goto _L4; else goto _L3
_L3:
        return obj1;
_L4:
        return valueOf(obj1.toString());
_L2:
        if (obj1 instanceof String)
        {
            return valueOf(obj1.toString());
        }
        typeCode;
        JVM INSTR tableswitch 4 38: default 208
    //                   4 316
    //                   5 208
    //                   6 208
    //                   7 208
    //                   8 208
    //                   9 208
    //                   10 208
    //                   11 208
    //                   12 208
    //                   13 208
    //                   14 208
    //                   15 208
    //                   16 208
    //                   17 208
    //                   18 400
    //                   19 458
    //                   20 581
    //                   21 581
    //                   22 581
    //                   23 516
    //                   24 516
    //                   25 516
    //                   26 516
    //                   27 516
    //                   28 608
    //                   29 617
    //                   30 626
    //                   31 236
    //                   32 208
    //                   33 231
    //                   34 635
    //                   35 657
    //                   36 208
    //                   37 219
    //                   38 214;
           goto _L5 _L6 _L5 _L5 _L5 _L5 _L5 _L5 _L5 _L5 _L5 _L5 _L5 _L5 _L5 _L7 _L8 _L9 _L9 _L9 _L10 _L10 _L10 _L10 _L10 _L11 _L12 _L13 _L14 _L5 _L15 _L16 _L17 _L5 _L18 _L19
_L5:
        return coerceFromObject(obj1);
_L19:
        return TextUtils.asString(obj1);
_L18:
        return new UntypedAtomic(TextUtils.stringValue(obj1));
_L15:
        return URIPath.makeURI(obj1);
_L14:
        if (obj1 instanceof Boolean)
        {
            Boolean boolean2;
            if (((Boolean)obj1).booleanValue())
            {
                boolean2 = Boolean.TRUE;
            } else
            {
                boolean2 = Boolean.FALSE;
            }
            return boolean2;
        }
        if (!(obj1 instanceof Number)) goto _L5; else goto _L20
_L20:
        double d = ((Number)obj1).doubleValue();
        Boolean boolean1;
        if (d == 0.0D || Double.isNaN(d))
        {
            boolean1 = Boolean.FALSE;
        } else
        {
            boolean1 = Boolean.TRUE;
        }
        return boolean1;
_L6:
        if (obj1 instanceof BigDecimal) goto _L3; else goto _L21
_L21:
        if (obj1 instanceof RealNum)
        {
            return ((RealNum)obj1).asBigDecimal();
        }
        if ((obj1 instanceof Float) || (obj1 instanceof Double))
        {
            return BigDecimal.valueOf(((Number)obj1).doubleValue());
        }
        if (!(obj1 instanceof Boolean)) goto _L5; else goto _L22
_L22:
        IntNum intnum;
        if (((Boolean)obj1).booleanValue())
        {
            intnum = IntNum.one();
        } else
        {
            intnum = IntNum.zero();
        }
        return cast(intnum);
_L7:
        if (obj1 instanceof Float) goto _L3; else goto _L23
_L23:
        if (obj1 instanceof Number)
        {
            return makeFloat(((Number)obj1).floatValue());
        }
        if (!(obj1 instanceof Boolean)) goto _L5; else goto _L24
_L24:
        Float float1;
        if (((Boolean)obj1).booleanValue())
        {
            float1 = FLOAT_ONE;
        } else
        {
            float1 = FLOAT_ZERO;
        }
        return float1;
_L8:
        if (obj1 instanceof Double) goto _L3; else goto _L25
_L25:
        if (obj1 instanceof Number)
        {
            return makeDouble(((Number)obj1).doubleValue());
        }
        if (obj1 instanceof Boolean)
        {
            Double double1;
            if (((Boolean)obj1).booleanValue())
            {
                double1 = DOUBLE_ONE;
            } else
            {
                double1 = DOUBLE_ZERO;
            }
            return double1;
        }
          goto _L5
_L10:
        if (obj1 instanceof DateTime)
        {
            int j = XTimeType.components(((XTimeType)this).typeCode);
            DateTime datetime = (DateTime)obj1;
            int k = datetime.components();
            if (j == k || (k & 0xe) == 14)
            {
                return datetime.cast(j);
            } else
            {
                throw new ClassCastException();
            }
        }
          goto _L5
_L9:
        if (obj1 instanceof DateTime)
        {
            int i = XTimeType.components(((XTimeType)this).typeCode);
            return ((DateTime)obj1).cast(i);
        }
          goto _L5
_L11:
        return castToDuration(obj1, Unit.duration);
_L12:
        return castToDuration(obj1, Unit.month);
_L13:
        return castToDuration(obj1, Unit.second);
_L16:
        if (obj1 instanceof BinaryObject)
        {
            return new Base64Binary(((BinaryObject)obj1).getBytes());
        }
_L17:
        if (!(obj1 instanceof BinaryObject)) goto _L5; else goto _L26
_L26:
        return new HexBinary(((BinaryObject)obj1).getBytes());
    }

    Duration castToDuration(Object obj, Unit unit)
    {
        if (obj instanceof Duration)
        {
            Duration duration = (Duration)obj;
            if (duration.unit() == unit)
            {
                return duration;
            }
            int i = duration.getTotalMonths();
            long l = duration.getTotalSeconds();
            int j = duration.getNanoSecondsOnly();
            if (unit == Unit.second)
            {
                i = 0;
            }
            if (unit == Unit.month)
            {
                l = 0L;
                j = 0;
            }
            return Duration.make(i, l, j, unit);
        } else
        {
            return (Duration)coerceFromObject(obj);
        }
    }

    public boolean castable(Object obj)
    {
        try
        {
            cast(obj);
        }
        catch (Throwable throwable)
        {
            return false;
        }
        return true;
    }

    public Object coerceFromObject(Object obj)
    {
        if (!isInstance(obj))
        {
            throw new ClassCastException((new StringBuilder()).append("cannot cast ").append(obj).append(" to ").append(name).toString());
        } else
        {
            return obj;
        }
    }

    public int compare(Type type)
    {
        if (this == type || this == stringStringType && type == stringType || this == stringType && type == stringStringType)
        {
            return 0;
        } else
        {
            return implementationType.compare(type);
        }
    }

    public Expression convertValue(Expression expression)
    {
        return null;
    }

    public void emitCoerceFromObject(CodeAttr codeattr)
    {
        Compilation.getCurrent().compileConstant(this, Target.pushObject);
        gnu.bytecode.Method method = ClassType.make("gnu.kawa.xml.XDataType").getDeclaredMethod("coerceFromObject", 1);
        codeattr.emitSwap();
        codeattr.emitInvokeVirtual(method);
        implementationType.emitCoerceFromObject(codeattr);
    }

    public void emitCoerceToObject(CodeAttr codeattr)
    {
        if (typeCode == 31)
        {
            implementationType.emitCoerceToObject(codeattr);
            return;
        } else
        {
            super.emitCoerceToObject(codeattr);
            return;
        }
    }

    public void emitIsInstance(Variable variable, Compilation compilation, Target target)
    {
        InstanceOf.emitIsInstance(this, variable, compilation, target);
    }

    public void emitTestIf(Variable variable, Declaration declaration, Compilation compilation)
    {
        CodeAttr codeattr = compilation.getCode();
        if (typeCode == 31)
        {
            if (variable != null)
            {
                codeattr.emitLoad(variable);
            }
            Type.javalangBooleanType.emitIsInstance(codeattr);
            codeattr.emitIfIntNotZero();
            if (declaration != null)
            {
                codeattr.emitLoad(variable);
                Type.booleanType.emitCoerceFromObject(codeattr);
                declaration.compileStore(compilation);
            }
        } else
        {
            compilation.compileConstant(this, Target.pushObject);
            if (variable == null)
            {
                codeattr.emitSwap();
            } else
            {
                codeattr.emitLoad(variable);
            }
            codeattr.emitInvokeVirtual(Compilation.typeType.getDeclaredMethod("isInstance", 1));
            codeattr.emitIfIntNotZero();
            if (declaration != null)
            {
                codeattr.emitLoad(variable);
                emitCoerceFromObject(codeattr);
                declaration.compileStore(compilation);
                return;
            }
        }
    }

    public Procedure getConstructor()
    {
        return null;
    }

    public Type getImplementationType()
    {
        return implementationType;
    }

    public Class getReflectClass()
    {
        return implementationType.getReflectClass();
    }

    public boolean isInstance(Object obj)
    {
        boolean flag = true;
        typeCode;
        JVM INSTR lookupswitch 13: default 120
    //                   2: 128
    //                   3: 144
    //                   4: 190
    //                   18: 180
    //                   19: 185
    //                   28: 214
    //                   29: 219
    //                   30: 241
    //                   31: 175
    //                   33: 170
    //                   37: 165
    //                   38: 160
    //                   48: 126;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L14:
        break; /* Loop/switch isn't completed */
_L1:
        flag = super.isInstance(obj);
_L16:
        return flag;
_L2:
        if ((obj instanceof SeqPosition) || (obj instanceof Nodes))
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if ((obj instanceof Values) || (obj instanceof SeqPosition))
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L13:
        return obj instanceof CharSequence;
_L12:
        return obj instanceof UntypedAtomic;
_L11:
        return obj instanceof Path;
_L10:
        return obj instanceof Boolean;
_L5:
        return obj instanceof Float;
_L6:
        return obj instanceof Double;
_L4:
        boolean flag1;
label0:
        {
            if (!(obj instanceof BigDecimal))
            {
                boolean flag2 = obj instanceof IntNum;
                flag1 = false;
                if (!flag2)
                {
                    break label0;
                }
            }
            flag1 = flag;
        }
        return flag1;
_L7:
        return obj instanceof Duration;
_L8:
        if (!(obj instanceof Duration) || ((Duration)obj).unit() != Unit.month)
        {
            return false;
        }
        continue; /* Loop/switch isn't completed */
_L9:
        if (!(obj instanceof Duration) || ((Duration)obj).unit() != Unit.second)
        {
            return false;
        }
        if (true) goto _L16; else goto _L15
_L15:
    }

    public void print(Object obj, Consumer consumer)
    {
        if (obj instanceof Printable)
        {
            ((Printable)obj).print(consumer);
            return;
        } else
        {
            consumer.write(toString(obj));
            return;
        }
    }

    public String toString(Object obj)
    {
        return obj.toString();
    }

    public Object valueOf(String s)
    {
        switch (typeCode)
        {
        default:
            throw new RuntimeException((new StringBuilder()).append("valueOf not implemented for ").append(name).toString());

        case 38: // '&'
            return s;

        case 37: // '%'
            return new UntypedAtomic(s);

        case 33: // '!'
            return URIPath.makeURI(TextUtils.replaceWhitespace(s, true));

        case 31: // '\037'
            String s3 = s.trim();
            if (s3.equals("true") || s3.equals("1"))
            {
                return Boolean.TRUE;
            }
            if (s3.equals("false") || s3.equals("0"))
            {
                return Boolean.FALSE;
            } else
            {
                throw new IllegalArgumentException((new StringBuilder()).append("not a valid boolean: '").append(s3).append("'").toString());
            }

        case 18: // '\022'
        case 19: // '\023'
            String s2 = s.trim();
            if ("INF".equals(s2))
            {
                s2 = "Infinity";
            } else
            if ("-INF".equals(s2))
            {
                s2 = "-Infinity";
            }
            if (typeCode == 18)
            {
                return Float.valueOf(s2);
            } else
            {
                return Double.valueOf(s2);
            }

        case 4: // '\004'
            String s1 = s.trim();
            for (int i = s1.length(); --i >= 0;)
            {
                char c = s1.charAt(i);
                if (c == 'e' || c == 'E')
                {
                    throw new IllegalArgumentException((new StringBuilder()).append("not a valid decimal: '").append(s1).append("'").toString());
                }
            }

            return new BigDecimal(s1);

        case 28: // '\034'
            return Duration.parseDuration(s);

        case 29: // '\035'
            return Duration.parseYearMonthDuration(s);

        case 30: // '\036'
            return Duration.parseDayTimeDuration(s);

        case 34: // '"'
            return Base64Binary.valueOf(s);

        case 35: // '#'
            return HexBinary.valueOf(s);
        }
    }

    static 
    {
        anySimpleType = new XDataType("anySimpleType", Type.objectType, 2);
        anyAtomicType = new XDataType("anyAtomicType", Type.objectType, 3);
        booleanType = new XDataType("boolean", Type.booleanType, 31);
        untypedType = new XDataType("untyped", Type.objectType, 48);
    }
}
