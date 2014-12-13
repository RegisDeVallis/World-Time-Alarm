// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.IgnoreTarget;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.Procedure;
import gnu.math.IntNum;

// Referenced classes of package gnu.kawa.functions:
//            AddOp, BitwiseOp, DivideOp, ArithOp, 
//            Arithmetic, NumberPredicate

public class CompileArith
    implements Inlineable
{

    public static CompileArith $Mn;
    public static CompileArith $Pl;
    int op;
    Procedure proc;

    CompileArith(Object obj, int i)
    {
        proc = (Procedure)obj;
        op = i;
    }

    static int adjustReturnKind(int i, int j)
    {
        if (j < 4 || j > 7 || i <= 0) goto _L2; else goto _L1
_L1:
        j;
        JVM INSTR tableswitch 4 7: default 48
    //                   4 50
    //                   5 58
    //                   6 48
    //                   7 73;
           goto _L2 _L3 _L4 _L2 _L5
_L2:
        return i;
_L3:
        if (i <= 4)
        {
            return 6;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (i <= 10 && i != 7)
        {
            return 8;
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (i <= 10)
        {
            return 4;
        }
        if (true) goto _L2; else goto _L6
_L6:
    }

    public static boolean appropriateIntConstant(Expression aexpression[], int i, InlineCalls inlinecalls)
    {
        QuoteExp quoteexp = inlinecalls.fixIntValue(aexpression[i]);
        if (quoteexp != null)
        {
            aexpression[i] = quoteexp;
            return true;
        } else
        {
            return false;
        }
    }

    public static boolean appropriateLongConstant(Expression aexpression[], int i, InlineCalls inlinecalls)
    {
        QuoteExp quoteexp = inlinecalls.fixLongValue(aexpression[i]);
        if (quoteexp != null)
        {
            aexpression[i] = quoteexp;
            return true;
        } else
        {
            return false;
        }
    }

    public static CompileArith forBitwise(Object obj)
    {
        return new CompileArith(obj, ((BitwiseOp)obj).op);
    }

    public static CompileArith forDiv(Object obj)
    {
        return new CompileArith(obj, ((DivideOp)obj).op);
    }

    public static CompileArith forMul(Object obj)
    {
        return new CompileArith(obj, 3);
    }

    public static int getReturnKind(int i, int j, int k)
    {
        if (k >= 9 && k <= 12)
        {
            return i;
        }
        if (i <= 0 || i > j && j > 0)
        {
            j = i;
        }
        return j;
    }

    public static Expression pairwise(Procedure procedure, Expression expression, Expression aexpression[], InlineCalls inlinecalls)
    {
        int i = aexpression.length;
        Object obj = aexpression[0];
        int j = 1;
        while (j < i) 
        {
            Expression aexpression1[] = new Expression[2];
            aexpression1[0] = ((Expression) (obj));
            aexpression1[1] = aexpression[j];
            ApplyExp applyexp = new ApplyExp(expression, aexpression1);
            Expression expression1 = inlinecalls.maybeInline(applyexp, null, procedure);
            if (expression1 != null)
            {
                obj = expression1;
            } else
            {
                obj = applyexp;
            }
            j++;
        }
        return ((Expression) (obj));
    }

    public static Expression validateApplyAdd(AddOp addop, ApplyExp applyexp, InlineCalls inlinecalls)
    {
        Expression aexpression[] = applyexp.getArgs();
        if (aexpression.length == 1 && addop.plusOrMinus < 0)
        {
            Type type = aexpression[0].getType();
            if (type instanceof PrimType)
            {
                char c = type.getSignature().charAt(0);
                int i = 0;
                Object obj = null;
                if (c != 'V')
                {
                    i = 0;
                    obj = null;
                    if (c != 'Z')
                    {
                        i = 0;
                        obj = null;
                        if (c != 'C')
                        {
                            if (c == 'D')
                            {
                                i = 119;
                                obj = LangPrimType.doubleType;
                            } else
                            if (c == 'F')
                            {
                                i = 118;
                                obj = LangPrimType.floatType;
                            } else
                            if (c == 'J')
                            {
                                i = 117;
                                obj = LangPrimType.longType;
                            } else
                            {
                                i = 116;
                                obj = LangPrimType.intType;
                            }
                        }
                    }
                }
                if (obj != null)
                {
                    applyexp = new ApplyExp(PrimProcedure.makeBuiltinUnary(i, ((Type) (obj))), aexpression);
                }
            }
        }
        return applyexp;
    }

    public static Expression validateApplyArithOp(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        int i;
        Expression aexpression[];
        i = ((ArithOp)procedure).op;
        applyexp.visitArgs(inlinecalls);
        aexpression = applyexp.getArgs();
        if (aexpression.length <= 2) goto _L2; else goto _L1
_L1:
        applyexp = pairwise(procedure, applyexp.getFunction(), aexpression, inlinecalls);
_L4:
        return applyexp;
_L2:
        int l;
        Expression expression = applyexp.inlineIfConstant(procedure, inlinecalls);
        if (expression != applyexp)
        {
            return expression;
        }
        if (aexpression.length != 2)
        {
            int j1 = aexpression.length;
            l = 0;
            if (j1 != 1)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        int j = Arithmetic.classifyType(aexpression[0].getType());
        int k;
        if (aexpression.length == 2 && (i < 9 || i > 12))
        {
            int i1 = Arithmetic.classifyType(aexpression[1].getType());
            k = getReturnKind(j, i1, i);
            if (k == 4)
            {
                if (j == 1 && appropriateIntConstant(aexpression, 1, inlinecalls))
                {
                    k = 1;
                } else
                if (i1 == 1 && appropriateIntConstant(aexpression, 0, inlinecalls))
                {
                    k = 1;
                } else
                if (j == 2 && appropriateLongConstant(aexpression, 1, inlinecalls))
                {
                    k = 2;
                } else
                if (i1 == 2 && appropriateLongConstant(aexpression, 0, inlinecalls))
                {
                    k = 2;
                }
            }
        } else
        {
            k = j;
        }
        l = adjustReturnKind(k, i);
        applyexp.setType(Arithmetic.kindType(l));
        if (!inlinecalls.getCompilation().mustCompile) goto _L4; else goto _L3
_L3:
        switch (i)
        {
        case 3: // '\003'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        default:
            return applyexp;

        case 1: // '\001'
        case 2: // '\002'
            return validateApplyAdd((AddOp)procedure, applyexp, inlinecalls);

        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
            return validateApplyDiv((DivideOp)procedure, applyexp, inlinecalls);

        case 16: // '\020'
            break;
        }
        continue; /* Loop/switch isn't completed */
        if (l <= 0) goto _L4; else goto _L5
_L5:
        return validateApplyNot(applyexp, l, inlinecalls);
    }

    public static Expression validateApplyDiv(DivideOp divideop, ApplyExp applyexp, InlineCalls inlinecalls)
    {
        Expression aexpression[] = applyexp.getArgs();
        if (aexpression.length == 1)
        {
            Expression aexpression1[] = new Expression[2];
            aexpression1[0] = QuoteExp.getInstance(IntNum.one());
            aexpression1[1] = aexpression[0];
            applyexp = new ApplyExp(applyexp.getFunction(), aexpression1);
        }
        return applyexp;
    }

    public static Expression validateApplyNot(ApplyExp applyexp, int i, InlineCalls inlinecalls)
    {
        if (applyexp.getArgCount() == 1)
        {
            Expression expression = applyexp.getArg(0);
            if (i == 1 || i == 2)
            {
                Expression aexpression[] = new Expression[2];
                aexpression[0] = expression;
                aexpression[1] = QuoteExp.getInstance(IntNum.minusOne());
                applyexp = inlinecalls.visitApplyOnly(new ApplyExp(BitwiseOp.xor, aexpression), null);
            } else
            {
                String s;
                if (i == 4)
                {
                    s = "gnu.math.BitOps";
                } else
                if (i == 3)
                {
                    s = "java.meth.BigInteger";
                } else
                {
                    s = null;
                }
                if (s != null)
                {
                    return new ApplyExp(ClassType.make(s).getDeclaredMethod("not", 1), applyexp.getArgs());
                }
            }
        }
        return applyexp;
    }

    public static Expression validateApplyNumberCompare(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        Expression expression = applyexp.inlineIfConstant(procedure, inlinecalls);
        if (expression != applyexp)
        {
            return expression;
        } else
        {
            return applyexp;
        }
    }

    public static Expression validateApplyNumberPredicate(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        int _tmp = ((NumberPredicate)procedure).op;
        Expression aexpression[] = applyexp.getArgs();
        aexpression[0] = inlinecalls.visit(aexpression[0], LangObjType.integerType);
        applyexp.setType(Type.booleanType);
        return applyexp;
    }

    public void compile(ApplyExp applyexp, Compilation compilation, Target target)
    {
        Expression aexpression[];
        int i;
        int j;
        int k;
        int l;
        Object obj;
        DivideOp divideop;
        aexpression = applyexp.getArgs();
        i = aexpression.length;
        if (i == 0)
        {
            compilation.compileConstant(((ArithOp)proc).defaultResult(), target);
            return;
        }
        if (i == 1 || (target instanceof IgnoreTarget))
        {
            ApplyExp.compile(applyexp, compilation, target);
            return;
        }
        j = Arithmetic.classifyType(aexpression[0].getType());
        k = Arithmetic.classifyType(aexpression[1].getType());
        l = getReturnKind(j, k, op);
        Type type = Arithmetic.kindType(l);
        if (l == 0 || i != 2)
        {
            ApplyExp.compile(applyexp, compilation, target);
            return;
        }
        int i1 = Arithmetic.classifyType(target.getType());
        if ((i1 == 1 || i1 == 2) && l >= 1 && l <= 4)
        {
            l = i1;
            Target target2;
            if (i1 == 1)
            {
                obj = LangPrimType.intType;
            } else
            {
                obj = LangPrimType.longType;
            }
        } else
        if ((i1 == 8 || i1 == 7) && l > 2 && l <= 10)
        {
            l = i1;
            if (i1 == 7)
            {
                obj = LangPrimType.floatType;
            } else
            {
                obj = LangPrimType.doubleType;
            }
        } else
        if (l == 7)
        {
            obj = LangPrimType.floatType;
        } else
        if (l == 8 || l == 9)
        {
            l = 8;
            obj = LangPrimType.doubleType;
        } else
        {
            obj = type;
        }
_L10:
        if (op >= 4 && op <= 8)
        {
            divideop = (DivideOp)proc;
            if (divideop.op != 4 || l > 4 && l < 6 && l > 9)
            {
                if ((divideop.op != 5 || l > 10 || l == 7) && (divideop.op != 4 || l != 10))
                {
                    continue; /* Loop/switch isn't completed */
                }
                l = 8;
            }
        }
_L2:
        if (op == 4 && l <= 10 && l != 8 && l != 7)
        {
            gnu.bytecode.Method method;
            if (l == 6 || l > 4)
            {
                LangObjType langobjtype;
                if (l == 6)
                {
                    langobjtype = Arithmetic.typeRatNum;
                } else
                {
                    langobjtype = Arithmetic.typeRealNum;
                }
                obj = langobjtype;
                method = langobjtype.getDeclaredMethod("divide", 2);
            } else
            {
                obj = Arithmetic.typeIntNum;
                method = Arithmetic.typeRatNum.getDeclaredMethod("make", 2);
            }
            target2 = StackTarget.getInstance(((Type) (obj)));
            aexpression[0].compile(compilation, target2);
            aexpression[1].compile(compilation, target2);
            compilation.getCode().emitInvokeStatic(method);
        } else
        {
label0:
            {
                if (l != 4 || op != 1 && op != 3 && op != 2 && op != 13 && op != 14 && op != 15 && op != 7 && op != 8 && (op < 9 || op > 11))
                {
                    break label0;
                }
                compileIntNum(aexpression[0], aexpression[1], j, k, compilation);
            }
        }
_L4:
        target.compileFromStack(compilation, ((Type) (obj)));
        return;
        if ((divideop.op == 7 || divideop.op == 6 && l <= 4) && (divideop.getRoundingMode() == 3 || l == 4 || l == 7 || l == 8) || divideop.op == 8 && (divideop.getRoundingMode() == 3 || l == 4)) goto _L2; else goto _L1
_L1:
        ApplyExp.compile(applyexp, compilation, target);
        return;
        Target target1;
        CodeAttr codeattr;
        int j1;
        if (l != 1 && l != 2 && (l != 7 && l != 8 || op > 8 && op < 13))
        {
            break MISSING_BLOCK_LABEL_957;
        }
        target1 = StackTarget.getInstance(((Type) (obj)));
        codeattr = compilation.getCode();
        j1 = 0;
_L7:
        if (j1 >= i) goto _L4; else goto _L3
_L3:
        if (j1 == 1 && op >= 9 && op <= 12)
        {
            target1 = StackTarget.getInstance(Type.intType);
        }
        aexpression[j1].compile(compilation, target1);
        if (j1 != 0) goto _L6; else goto _L5
_L5:
        j1++;
          goto _L7
_L6:
        switch (l)
        {
        case 1: // '\001'
        case 2: // '\002'
        case 7: // '\007'
        case 8: // '\b'
            if (op == 9)
            {
                Type atype[] = new Type[2];
                atype[0] = ((Type) (obj));
                atype[1] = Type.intType;
                codeattr.emitInvokeStatic(ClassType.make("gnu.math.IntNum").getDeclaredMethod("shift", atype));
            } else
            {
                codeattr.emitBinop(primitiveOpcode(), (PrimType)((Type) (obj)).getImplementationType());
            }
            break;
        }
        if (true) goto _L5; else goto _L8
_L8:
        ApplyExp.compile(applyexp, compilation, target);
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public boolean compileIntNum(Expression expression, Expression expression1, int i, int j, Compilation compilation)
    {
        Object obj;
        Object obj1;
        CodeAttr codeattr;
        Type atype[];
        Object obj2;
        String s;
        if (op == 2 && (expression1 instanceof QuoteExp))
        {
            Object obj3 = expression1.valueIfConstant();
            long l1;
            boolean flag2;
            if (j <= 2)
            {
                l1 = ((Number)obj3).longValue();
                if (l1 > 0xffffffff80000000L && l1 <= 0x7fffffffL)
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
            } else
            if (obj3 instanceof IntNum)
            {
                IntNum intnum = (IntNum)obj3;
                l1 = intnum.longValue();
                flag2 = intnum.inRange(0xffffffff80000001L, 0x7fffffffL);
            } else
            {
                l1 = 0L;
                flag2 = false;
            }
            if (flag2)
            {
                return $Pl.compileIntNum(expression, ((Expression) (QuoteExp.getInstance(Integer.valueOf((int)(-l1))))), i, 1, compilation);
            }
        }
        boolean flag;
        boolean flag1;
        if (op == 1 || op == 3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            if (InlineCalls.checkIntValue(expression) != null)
            {
                i = 1;
            }
            if (InlineCalls.checkIntValue(expression1) != null)
            {
                j = 1;
            }
            if (i == 1 && j != 1)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1 && (!expression.side_effects() || !expression1.side_effects()))
            {
                return compileIntNum(expression1, expression, j, i, compilation);
            }
            int k;
            if (i == 1)
            {
                obj1 = Type.intType;
            } else
            {
                obj1 = Arithmetic.typeIntNum;
            }
            if (j == 1)
            {
                obj = Type.intType;
            } else
            {
                obj = Arithmetic.typeIntNum;
            }
        } else
        if (op >= 9 && op <= 12)
        {
            obj1 = Arithmetic.typeIntNum;
            obj = Type.intType;
            flag1 = false;
        } else
        {
            obj = Arithmetic.typeIntNum;
            obj1 = obj;
            flag1 = false;
        }
        expression.compile(compilation, ((Type) (obj1)));
        expression1.compile(compilation, ((Type) (obj)));
        codeattr = compilation.getCode();
        if (flag1)
        {
            codeattr.emitSwap();
            obj1 = Arithmetic.typeIntNum;
            obj = LangPrimType.intType;
        }
        atype = null;
        obj2 = Arithmetic.typeIntNum;
        k = op;
        s = null;
        k;
        JVM INSTR tableswitch 1 15: default 396
    //                   1 469
    //                   2 511
    //                   3 522
    //                   4 571
    //                   5 571
    //                   6 571
    //                   7 571
    //                   8 571
    //                   9 721
    //                   10 685
    //                   11 685
    //                   12 396
    //                   13 533
    //                   14 538
    //                   15 548;
           goto _L1 _L2 _L3 _L4 _L5 _L5 _L5 _L5 _L5 _L6 _L7 _L7 _L1 _L8 _L9 _L10
_L1:
        throw new Error();
_L2:
        s = "add";
_L12:
        if (atype == null)
        {
            atype = (new Type[] {
                obj1, obj
            });
        }
        codeattr.emitInvokeStatic(((ObjectType) (obj2)).getMethod(s, atype));
        return true;
_L3:
        s = "sub";
        atype = null;
        continue; /* Loop/switch isn't completed */
_L4:
        s = "times";
        atype = null;
        continue; /* Loop/switch isn't completed */
_L8:
        s = "and";
_L9:
        if (s == null)
        {
            s = "ior";
        }
_L10:
        if (s == null)
        {
            s = "xor";
        }
        obj2 = ClassType.make("gnu.math.BitOps");
        atype = null;
        continue; /* Loop/switch isn't completed */
_L5:
        DivideOp divideop;
        if (op == 8)
        {
            s = "remainder";
        } else
        {
            s = "quotient";
        }
        divideop = (DivideOp)proc;
        if (op == 8 && divideop.rounding_mode == 1)
        {
            s = "modulo";
            atype = null;
        } else
        {
            int l = divideop.rounding_mode;
            atype = null;
            if (l != 3)
            {
                codeattr.emitPushInt(divideop.rounding_mode);
                atype = new Type[3];
                atype[0] = ((Type) (obj1));
                atype[1] = ((Type) (obj));
                atype[2] = Type.intType;
            }
        }
        continue; /* Loop/switch isn't completed */
_L7:
        if (op == 10)
        {
            s = "shiftLeft";
        } else
        {
            s = "shiftRight";
        }
        obj2 = ClassType.make("gnu.kawa.functions.BitwiseOp");
        atype = null;
        continue; /* Loop/switch isn't completed */
_L6:
        s = "shift";
        atype = null;
        if (true) goto _L12; else goto _L11
_L11:
    }

    public int getReturnKind(Expression aexpression[])
    {
        int i = aexpression.length;
        int j;
        if (i == 0)
        {
            j = 4;
        } else
        {
            ClassType _tmp = Type.pointer_type;
            j = 0;
            int k = 0;
            while (k < i) 
            {
                int l = Arithmetic.classifyType(aexpression[k].getType());
                if (k == 0 || l == 0 || l > j)
                {
                    j = l;
                }
                k++;
            }
        }
        return j;
    }

    public Type getReturnType(Expression aexpression[])
    {
        return Arithmetic.kindType(adjustReturnKind(getReturnKind(aexpression), op));
    }

    public int primitiveOpcode()
    {
        switch (op)
        {
        case 9: // '\t'
        default:
            return -1;

        case 1: // '\001'
            return 96;

        case 2: // '\002'
            return 100;

        case 3: // '\003'
            return 104;

        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
            return 108;

        case 8: // '\b'
            return 112;

        case 10: // '\n'
            return 120;

        case 11: // '\013'
            return 122;

        case 12: // '\f'
            return 124;

        case 13: // '\r'
            return 126;

        case 14: // '\016'
            return 128;

        case 15: // '\017'
            return 130;
        }
    }

    static 
    {
        $Pl = new CompileArith(AddOp.$Pl, 1);
        $Mn = new CompileArith(AddOp.$Mn, 2);
    }
}
