// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangObjType;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.math.RatNum;
import java.math.BigDecimal;
import java.math.BigInteger;

// Referenced classes of package gnu.kawa.functions:
//            Arithmetic

public class NumberCompare extends ProcedureN
    implements Inlineable
{

    static final int RESULT_EQU = 0;
    static final int RESULT_GRT = 1;
    static final int RESULT_LSS = -1;
    static final int RESULT_NAN = -2;
    static final int RESULT_NEQ = -3;
    public static final int TRUE_IF_EQU = 8;
    public static final int TRUE_IF_GRT = 16;
    public static final int TRUE_IF_LSS = 4;
    public static final int TRUE_IF_NAN = 2;
    public static final int TRUE_IF_NEQ = 1;
    int flags;
    Language language;

    public static boolean $Eq(Object obj, Object obj1)
    {
        return apply2(8, obj, obj1);
    }

    public static boolean $Eq$V(Object obj, Object obj1, Object obj2, Object aobj[])
    {
        boolean flag1;
label0:
        {
            boolean flag = $Eq(obj, obj1);
            flag1 = false;
            if (!flag)
            {
                break label0;
            }
            boolean flag2 = $Eq(obj1, obj2);
            flag1 = false;
            if (!flag2)
            {
                break label0;
            }
            if (aobj.length != 0)
            {
                boolean flag3 = $Eq(obj2, aobj[0]);
                flag1 = false;
                if (!flag3)
                {
                    break label0;
                }
                boolean flag4 = applyN(8, aobj);
                flag1 = false;
                if (!flag4)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    public static boolean $Gr(Object obj, Object obj1)
    {
        return apply2(16, obj, obj1);
    }

    public static boolean $Gr$Eq(Object obj, Object obj1)
    {
        return apply2(24, obj, obj1);
    }

    public static boolean $Gr$Eq$V(Object obj, Object obj1, Object obj2, Object aobj[])
    {
        boolean flag1;
label0:
        {
            boolean flag = $Gr$Eq(obj, obj1);
            flag1 = false;
            if (!flag)
            {
                break label0;
            }
            boolean flag2 = $Gr$Eq(obj1, obj2);
            flag1 = false;
            if (!flag2)
            {
                break label0;
            }
            if (aobj.length != 0)
            {
                boolean flag3 = $Gr$Eq(obj2, aobj[0]);
                flag1 = false;
                if (!flag3)
                {
                    break label0;
                }
                boolean flag4 = applyN(24, aobj);
                flag1 = false;
                if (!flag4)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    public static boolean $Gr$V(Object obj, Object obj1, Object obj2, Object aobj[])
    {
        boolean flag1;
label0:
        {
            boolean flag = $Gr(obj, obj1);
            flag1 = false;
            if (!flag)
            {
                break label0;
            }
            boolean flag2 = $Gr(obj1, obj2);
            flag1 = false;
            if (!flag2)
            {
                break label0;
            }
            if (aobj.length != 0)
            {
                boolean flag3 = $Gr(obj2, aobj[0]);
                flag1 = false;
                if (!flag3)
                {
                    break label0;
                }
                boolean flag4 = applyN(16, aobj);
                flag1 = false;
                if (!flag4)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    public static boolean $Ls(Object obj, Object obj1)
    {
        return apply2(4, obj, obj1);
    }

    public static boolean $Ls$Eq(Object obj, Object obj1)
    {
        return apply2(12, obj, obj1);
    }

    public static boolean $Ls$Eq$V(Object obj, Object obj1, Object obj2, Object aobj[])
    {
        boolean flag1;
label0:
        {
            boolean flag = $Ls$Eq(obj, obj1);
            flag1 = false;
            if (!flag)
            {
                break label0;
            }
            boolean flag2 = $Ls$Eq(obj1, obj2);
            flag1 = false;
            if (!flag2)
            {
                break label0;
            }
            if (aobj.length != 0)
            {
                boolean flag3 = $Ls$Eq(obj2, aobj[0]);
                flag1 = false;
                if (!flag3)
                {
                    break label0;
                }
                boolean flag4 = applyN(12, aobj);
                flag1 = false;
                if (!flag4)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    public static boolean $Ls$V(Object obj, Object obj1, Object obj2, Object aobj[])
    {
        boolean flag1;
label0:
        {
            boolean flag = $Ls(obj, obj1);
            flag1 = false;
            if (!flag)
            {
                break label0;
            }
            boolean flag2 = $Ls(obj1, obj2);
            flag1 = false;
            if (!flag2)
            {
                break label0;
            }
            if (aobj.length != 0)
            {
                boolean flag3 = $Ls(obj2, aobj[0]);
                flag1 = false;
                if (!flag3)
                {
                    break label0;
                }
                boolean flag4 = applyN(4, aobj);
                flag1 = false;
                if (!flag4)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
        return flag1;
    }

    public NumberCompare()
    {
    }

    public static boolean apply2(int i, Object obj, Object obj1)
    {
        return (i & 1 << 3 + compare(obj, obj1, true)) != 0;
    }

    static boolean applyN(int i, Object aobj[])
    {
        for (int j = 0; j < -1 + aobj.length; j++)
        {
            if (!apply2(i, aobj[j], aobj[j + 1]))
            {
                return false;
            }
        }

        return true;
    }

    public static boolean applyWithPromotion(int i, Object obj, Object obj1)
    {
        return checkCompareCode(compare(obj, obj1, false), i);
    }

    public static boolean checkCompareCode(int i, int j)
    {
        return (j & 1 << i + 3) != 0;
    }

    static int classify(Expression expression)
    {
        int i = Arithmetic.classifyType(expression.getType());
        if (i == 4 && (expression instanceof QuoteExp))
        {
            Object obj = ((QuoteExp)expression).getValue();
            if (obj instanceof IntNum)
            {
                int j = ((IntNum)obj).intLength();
                if (j < 32)
                {
                    i = 1;
                } else
                if (j < 64)
                {
                    return 2;
                }
            }
        }
        return i;
    }

    public static int compare(Object obj, int i, Object obj1, int j, boolean flag)
    {
        if (i < 0 || j < 0)
        {
            return -3;
        }
        int k;
        if (i < j)
        {
            k = j;
        } else
        {
            k = i;
        }
        k;
        JVM INSTR tableswitch 1 9: default 72
    //                   1 90
    //                   2 122
    //                   3 156
    //                   4 168
    //                   5 180
    //                   6 192
    //                   7 204
    //                   8 266
    //                   9 266;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L9
_L1:
        return Arithmetic.asNumeric(obj).compare(Arithmetic.asNumeric(obj1));
_L2:
        int i1 = Arithmetic.asInt(obj);
        int j1 = Arithmetic.asInt(obj1);
        if (i1 < j1)
        {
            return -1;
        }
        return i1 <= j1 ? 0 : 1;
_L3:
        long l = Arithmetic.asLong(obj);
        long l1 = Arithmetic.asLong(obj1);
        if (l < l1)
        {
            return -1;
        }
        return l <= l1 ? 0 : 1;
_L4:
        return Arithmetic.asBigInteger(obj).compareTo(Arithmetic.asBigInteger(obj1));
_L5:
        return IntNum.compare(Arithmetic.asIntNum(obj), Arithmetic.asIntNum(obj1));
_L6:
        return Arithmetic.asBigDecimal(obj).compareTo(Arithmetic.asBigDecimal(obj1));
_L7:
        return RatNum.compare(Arithmetic.asRatNum(obj), Arithmetic.asRatNum(obj1));
_L8:
        if (!flag || i > 6 && j > 6)
        {
            float f = Arithmetic.asFloat(obj);
            float f1 = Arithmetic.asFloat(obj1);
            if (f > f1)
            {
                return 1;
            }
            if (f < f1)
            {
                return -1;
            }
            return f != f1 ? -2 : 0;
        }
_L9:
        if (flag && (i <= 6 || j <= 6)) goto _L1; else goto _L10
_L10:
        double d = Arithmetic.asDouble(obj);
        double d1 = Arithmetic.asDouble(obj1);
        if (d > d1)
        {
            return 1;
        }
        if (d < d1)
        {
            return -1;
        }
        return d != d1 ? -2 : 0;
    }

    public static int compare(Object obj, Object obj1, boolean flag)
    {
        return compare(obj, Arithmetic.classifyValue(obj), obj1, Arithmetic.classifyValue(obj1), flag);
    }

    public static NumberCompare make(Language language1, String s, int i)
    {
        NumberCompare numbercompare = new NumberCompare();
        numbercompare.language = language1;
        numbercompare.setName(s);
        numbercompare.flags = i;
        numbercompare.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyNumberCompare");
        return numbercompare;
    }

    public Object apply2(Object obj, Object obj1)
    {
        return getLanguage().booleanObject(apply2(flags, obj, obj1));
    }

    public Object applyN(Object aobj[])
    {
        return getLanguage().booleanObject(applyN(flags, aobj));
    }

    public void compile(ApplyExp applyexp, Compilation compilation, Target target)
    {
        Expression aexpression[] = applyexp.getArgs();
        if (aexpression.length != 2) goto _L2; else goto _L1
_L1:
        Object obj;
        Object obj1;
        int i;
        int j;
        CodeAttr codeattr;
        obj = aexpression[0];
        obj1 = aexpression[1];
        i = classify(((Expression) (obj)));
        j = classify(((Expression) (obj1)));
        codeattr = compilation.getCode();
        if (i <= 0 || j <= 0 || i > 10 || j > 10 || i == 6 && j == 6) goto _L2; else goto _L3
_L3:
        int k;
        if (!(target instanceof ConditionalTarget))
        {
            IfExp.compile(applyexp, QuoteExp.trueExp, QuoteExp.falseExp, compilation, target);
            return;
        }
        k = flags;
        if (k == 1)
        {
            k = 20;
        }
        if (i > 4 || j > 4 || i <= 2 && j <= 2) goto _L5; else goto _L4
_L4:
        Type atype[];
        atype = new Type[2];
        atype[0] = Arithmetic.typeIntNum;
        if (j > 2) goto _L7; else goto _L6
_L6:
        atype[1] = Type.longType;
_L21:
        obj = new ApplyExp(new PrimProcedure(Arithmetic.typeIntNum.getMethod("compare", atype)), aexpression);
        obj1 = new QuoteExp(IntNum.zero());
        j = 1;
        i = j;
_L5:
        StackTarget stacktarget;
        gnu.bytecode.Label label;
        int l;
        gnu.bytecode.PrimType primtype;
        ConditionalTarget conditionaltarget;
        Object obj2;
        if (i <= 1 && j <= 1)
        {
            primtype = Type.intType;
        } else
        if (i <= 2 && j <= 2)
        {
            primtype = Type.longType;
        } else
        {
            primtype = Type.doubleType;
        }
        stacktarget = new StackTarget(primtype);
        conditionaltarget = (ConditionalTarget)target;
        if ((obj instanceof QuoteExp) && !(obj1 instanceof QuoteExp))
        {
            Object obj3 = obj1;
            obj1 = obj;
            obj = obj3;
            if (k != 8 && k != 20)
            {
                k ^= 0x14;
            }
        }
        if (conditionaltarget.trueBranchComesFirst)
        {
            label = conditionaltarget.ifFalse;
        } else
        {
            label = conditionaltarget.ifTrue;
        }
        if (conditionaltarget.trueBranchComesFirst)
        {
            k ^= 0x1c;
        }
        k;
        JVM INSTR lookupswitch 6: default 388
    //                   4: 614
    //                   8: 606
    //                   12: 638
    //                   16: 598
    //                   20: 622
    //                   24: 630;
           goto _L8 _L9 _L10 _L11 _L12 _L13 _L14
_L8:
        l = 0;
_L18:
        ((Expression) (obj)).compile(compilation, stacktarget);
        if (i > 1 || j > 1 || !(obj1 instanceof QuoteExp)) goto _L16; else goto _L15
_L15:
        obj2 = ((QuoteExp)obj1).getValue();
        if (!(obj2 instanceof IntNum) || !((IntNum)obj2).isZero()) goto _L16; else goto _L17
_L17:
        codeattr.emitGotoIfCompare1(label, l);
_L19:
        conditionaltarget.emitGotoFirstBranch(codeattr);
        return;
_L7:
        if (i <= 2 && ((obj instanceof QuoteExp) || (obj1 instanceof QuoteExp) || (obj instanceof ReferenceExp) || (obj1 instanceof ReferenceExp)))
        {
            atype[1] = Type.longType;
            aexpression = (new Expression[] {
                obj1, obj
            });
            if (k != 8 && k != 20)
            {
                k ^= 0x14;
            }
        } else
        {
            atype[1] = Arithmetic.typeIntNum;
        }
        continue; /* Loop/switch isn't completed */
_L12:
        l = 157;
          goto _L18
_L10:
        l = 153;
          goto _L18
_L9:
        l = 155;
          goto _L18
_L13:
        l = 154;
          goto _L18
_L14:
        l = 156;
          goto _L18
_L11:
        l = 158;
          goto _L18
_L16:
        ((Expression) (obj1)).compile(compilation, stacktarget);
        codeattr.emitGotoIfCompare2(label, l);
          goto _L19
_L2:
        ApplyExp.compile(applyexp, compilation, target);
        return;
        if (true) goto _L21; else goto _L20
_L20:
    }

    protected final Language getLanguage()
    {
        return language;
    }

    public Type getReturnType(Expression aexpression[])
    {
        return Type.booleanType;
    }

    public int numArgs()
    {
        return -4094;
    }
}
