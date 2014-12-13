// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Keyword;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.PairClassType;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.TypeValue;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;

// Referenced classes of package gnu.kawa.reflect:
//            SlotSet, ClassMethods, Invoke, ArrayNew, 
//            ArraySet

public class CompileInvoke
{

    public CompileInvoke()
    {
    }

    private static void append(PrimProcedure aprimprocedure[], int i, StringBuffer stringbuffer)
    {
        for (int j = 0; j < i; j++)
        {
            stringbuffer.append("\n  candidate: ");
            stringbuffer.append(aprimprocedure[j]);
        }

    }

    static Object[] checkKeywords(ObjectType objecttype, Expression aexpression[], int i, ClassType classtype)
    {
        int j = aexpression.length;
        int k;
        for (k = 0; 1 + (i + k * 2) < j && (aexpression[i + k * 2].valueIfConstant() instanceof Keyword); k++) { }
        Object aobj[] = new Object[k];
        int l = 0;
        while (l < k) 
        {
            String s = ((Keyword)aexpression[i + l * 2].valueIfConstant()).getName();
            Object obj = SlotSet.lookupMember(objecttype, s, classtype);
            if (obj == null)
            {
                obj = objecttype.getMethod(ClassExp.slotToMethodName("add", s), SlotSet.type1Array);
            }
            if (obj == null)
            {
                obj = s;
            }
            aobj[l] = obj;
            l++;
        }
        return aobj;
    }

    private static String getMethodName(Expression aexpression[], char c)
    {
        if (c == 'N')
        {
            return "<init>";
        }
        byte byte0;
        if (c == 'P')
        {
            byte0 = 2;
        } else
        {
            byte0 = 1;
        }
        if (aexpression.length >= byte0 + 1)
        {
            return ClassMethods.checkName(aexpression[byte0], false);
        } else
        {
            return null;
        }
    }

    protected static PrimProcedure[] getMethods(ObjectType objecttype, String s, ClassType classtype, Invoke invoke)
    {
        char c = 'P';
        char c1 = invoke.kind;
        if (c1 != c)
        {
            if (c1 == '*' || c1 == 'V')
            {
                c = 'V';
            } else
            {
                c = '\0';
            }
        }
        return ClassMethods.getMethods(objecttype, s, c, classtype, invoke.language);
    }

    public static PrimProcedure getStaticMethod(ClassType classtype, String s, Expression aexpression[])
    {
        gnu/kawa/reflect/CompileInvoke;
        JVM INSTR monitorenter ;
        PrimProcedure aprimprocedure[];
        long l;
        aprimprocedure = getMethods(classtype, s, null, Invoke.invokeStatic);
        l = selectApplicable(aprimprocedure, classtype, aexpression, aexpression.length, 0, -1);
        int i;
        int j;
        i = (int)(l >> 32);
        j = (int)l;
        if (aprimprocedure != null) goto _L2; else goto _L1
_L1:
        int k = -1;
_L8:
        if (k >= 0) goto _L4; else goto _L3
_L3:
        PrimProcedure primprocedure = null;
_L6:
        gnu/kawa/reflect/CompileInvoke;
        JVM INSTR monitorexit ;
        return primprocedure;
_L2:
        if (i <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        k = MethodProc.mostSpecific(aprimprocedure, i);
        continue; /* Loop/switch isn't completed */
_L4:
        primprocedure = aprimprocedure[k];
        if (true) goto _L6; else goto _L5
        Exception exception;
        exception;
        throw exception;
_L5:
        if (j == 1)
        {
            k = 0;
        } else
        {
            k = -1;
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    static int hasKeywordArgument(int i, Expression aexpression[])
    {
        for (int j = i; j < aexpression.length; j++)
        {
            if (aexpression[j].valueIfConstant() instanceof Keyword)
            {
                return j;
            }
        }

        return aexpression.length;
    }

    private static long selectApplicable(PrimProcedure aprimprocedure[], ObjectType objecttype, Expression aexpression[], int i, int j, int k)
    {
        Type atype[] = new Type[i];
        int l = 0;
        if (k >= 0)
        {
            int j1 = 0 + 1;
            atype[0] = objecttype;
            l = j1;
        }
        int i1 = j;
        while (i1 < aexpression.length && l < atype.length) 
        {
            Expression expression = aexpression[i1];
            Object obj;
            if (InlineCalls.checkIntValue(expression) != null)
            {
                obj = Type.intType;
            } else
            if (InlineCalls.checkLongValue(expression) != null)
            {
                obj = Type.longType;
            } else
            {
                obj = null;
                if (true)
                {
                    obj = expression.getType();
                }
            }
            atype[l] = ((Type) (obj));
            i1++;
            l++;
        }
        return ClassMethods.selectApplicable(aprimprocedure, atype);
    }

    public static Expression validateApplyInvoke(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        Invoke invoke;
        char c;
        Compilation compilation;
        Expression aexpression[];
        int i;
        Expression expression;
        Object obj;
        String s;
        int j;
        byte byte0;
        byte byte1;
        LetExp letexp1;
        Declaration declaration1;
        BeginExp beginexp1;
        String s2;
        {
            ArrayType arraytype;
            Type type4;
            boolean flag1;
            ApplyExp applyexp3;
label0:
            {
                invoke = (Invoke)procedure;
                c = invoke.kind;
                compilation = inlinecalls.getCompilation();
                aexpression = applyexp.getArgs();
                i = aexpression.length;
                if (!compilation.mustCompile || i == 0 || (c == 'V' || c == '*') && i == 1)
                {
                    applyexp.visitArgs(inlinecalls);
                    return applyexp;
                }
                expression = inlinecalls.visit(aexpression[0], null);
                aexpression[0] = expression;
                Type type1;
                int l7;
                Object obj6;
                PrimType primtype;
                Expression expression2;
                boolean flag2;
                Object obj8;
                boolean flag3;
                String s3;
                if (c == 'V' || c == '*')
                {
                    type1 = expression.getType();
                } else
                {
                    type1 = invoke.language.getTypeFor(expression);
                }
                if ((type1 instanceof PairClassType) && c == 'N')
                {
                    obj = ((PairClassType)type1).instanceType;
                } else
                if (type1 instanceof ObjectType)
                {
                    obj = (ObjectType)type1;
                } else
                {
                    obj = null;
                }
                s = getMethodName(aexpression, c);
                if (c == 'V' || c == '*')
                {
                    j = i - 1;
                    byte0 = 2;
                    byte1 = 0;
                } else
                if (c == 'N')
                {
                    j = i;
                    byte1 = -1;
                    byte0 = 0;
                } else
                if (c == 'S' || c == 's')
                {
                    j = i - 2;
                    byte0 = 2;
                    byte1 = -1;
                } else
                if (c == 'P')
                {
                    j = i - 2;
                    byte0 = 3;
                    byte1 = 1;
                } else
                {
                    applyexp.visitArgs(inlinecalls);
                    return applyexp;
                }
                if (c != 'N' || !(obj instanceof ArrayType))
                {
                    break MISSING_BLOCK_LABEL_817;
                }
                arraytype = (ArrayType)obj;
                type4 = arraytype.getComponentType();
                l7 = aexpression.length;
                flag1 = false;
                obj6 = null;
                if (l7 < 3)
                {
                    break label0;
                }
                flag2 = aexpression[1] instanceof QuoteExp;
                flag1 = false;
                obj6 = null;
                if (!flag2)
                {
                    break label0;
                }
                obj8 = ((QuoteExp)aexpression[1]).getValue();
                flag3 = obj8 instanceof Keyword;
                flag1 = false;
                obj6 = null;
                if (!flag3)
                {
                    break label0;
                }
                s3 = ((Keyword)obj8).getName();
                if (!"length".equals(s3))
                {
                    boolean flag4 = "size".equals(s3);
                    flag1 = false;
                    obj6 = null;
                    if (!flag4)
                    {
                        break label0;
                    }
                }
                obj6 = aexpression[2];
                flag1 = true;
            }
            if (obj6 == null)
            {
                obj6 = QuoteExp.getInstance(new Integer(-1 + aexpression.length));
            }
            primtype = Type.intType;
            expression2 = inlinecalls.visit(((Expression) (obj6)), primtype);
            applyexp3 = new ApplyExp(new ArrayNew(type4), new Expression[] {
                expression2
            });
            applyexp3.setType(arraytype);
            if (flag1 && aexpression.length == 3)
            {
                return applyexp3;
            }
            letexp1 = new LetExp(new Expression[] {
                applyexp3
            });
            declaration1 = letexp1.addDeclaration((String)null, arraytype);
            declaration1.noteValue(applyexp3);
            beginexp1 = new BeginExp();
            int i8 = 0;
            int j8;
            int k8;
            Expression expression3;
            Expression expression4;
            ArraySet arrayset;
            Expression aexpression7[];
            Object obj7;
            if (flag1)
            {
                j8 = 3;
            } else
            {
                j8 = 1;
                i8 = 0;
            }
        }
        k8 = aexpression.length;
        if (j8 >= k8)
        {
            break; /* Loop/switch isn't completed */
        }
        expression3 = aexpression[j8];
        if (!flag1 || j8 + 1 >= aexpression.length || !(expression3 instanceof QuoteExp))
        {
            break MISSING_BLOCK_LABEL_661;
        }
        obj7 = ((QuoteExp)expression3).getValue();
        if (!(obj7 instanceof Keyword))
        {
            break MISSING_BLOCK_LABEL_661;
        }
        s2 = ((Keyword)obj7).getName();
        try
        {
            i8 = Integer.parseInt(s2);
        }
        catch (Throwable throwable)
        {
            compilation.error('e', (new StringBuilder()).append("non-integer keyword '").append(s2).append("' in array constructor").toString());
            return applyexp;
        }
        j8++;
        expression3 = aexpression[j8];
        expression4 = inlinecalls.visit(expression3, type4);
        arrayset = new ArraySet(type4);
        aexpression7 = new Expression[3];
        aexpression7[0] = new ReferenceExp(declaration1);
        aexpression7[1] = QuoteExp.getInstance(new Integer(i8));
        aexpression7[2] = expression4;
        beginexp1.add(new ApplyExp(arrayset, aexpression7));
        i8++;
        j8++;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_574;
_L1:
        beginexp1.add(new ReferenceExp(declaration1));
        letexp1.body = beginexp1;
        return letexp1;
        ClassType classtype;
        Object obj1;
        PrimProcedure aprimprocedure[];
        int k;
        int l;
label1:
        {
            if (obj == null || s == null)
            {
                break MISSING_BLOCK_LABEL_2728;
            }
            if ((obj instanceof TypeValue) && c == 'N')
            {
                Procedure procedure1 = ((TypeValue)obj).getConstructor();
                if (procedure1 != null)
                {
                    Expression aexpression6[] = new Expression[i - 1];
                    System.arraycopy(aexpression, 1, aexpression6, 0, i - 1);
                    return inlinecalls.visit(new ApplyExp(procedure1, aexpression6), type);
                }
            }
            int k5;
            Object aobj[];
            if (compilation == null)
            {
                classtype = null;
            } else
            if (compilation.curClass != null)
            {
                classtype = compilation.curClass;
            } else
            {
                classtype = compilation.mainClass;
            }
            obj1 = obj;
            try
            {
                aprimprocedure = getMethods(((ObjectType) (obj1)), s, classtype, invoke);
                k = ClassMethods.selectApplicable(aprimprocedure, j);
            }
            catch (Exception exception)
            {
                compilation.error('w', (new StringBuilder()).append("unknown class: ").append(((ObjectType) (obj)).getName()).toString());
                return applyexp;
            }
            l = -1;
            if (c != 'N')
            {
                break label1;
            }
            k5 = hasKeywordArgument(1, aexpression);
            if (k5 >= aexpression.length)
            {
                if (k > 0)
                {
                    break label1;
                }
                Type atype[] = new Type[1];
                atype[0] = Compilation.typeClassType;
                if (ClassMethods.selectApplicable(aprimprocedure, atype) >> 32 != 1L)
                {
                    break label1;
                }
            }
            aobj = checkKeywords(((ObjectType) (obj1)), aexpression, k5, classtype);
            if (2 * aobj.length == aexpression.length - k5 || ClassMethods.selectApplicable(ClassMethods.getMethods(((ObjectType) (obj1)), "add", 'V', null, invoke.language), 2) > 0)
            {
                StringBuffer stringbuffer2 = null;
                int l5 = 0;
                do
                {
                    int i6 = aobj.length;
                    if (l5 >= i6)
                    {
                        break;
                    }
                    if (aobj[l5] instanceof String)
                    {
                        Object obj5;
                        if (stringbuffer2 == null)
                        {
                            stringbuffer2 = new StringBuffer();
                            stringbuffer2.append("no field or setter ");
                        } else
                        {
                            stringbuffer2.append(", ");
                        }
                        stringbuffer2.append('`');
                        obj5 = aobj[l5];
                        stringbuffer2.append(obj5);
                        stringbuffer2.append('\'');
                    }
                    l5++;
                } while (true);
                if (stringbuffer2 != null)
                {
                    stringbuffer2.append(" in class ");
                    String s1 = ((ObjectType) (obj)).getName();
                    stringbuffer2.append(s1);
                    compilation.error('w', stringbuffer2.toString());
                    return applyexp;
                }
                ApplyExp applyexp2;
                Object obj3;
                if (k5 < aexpression.length)
                {
                    Expression aexpression5[] = new Expression[k5];
                    System.arraycopy(aexpression, 0, aexpression5, 0, k5);
                    applyexp2 = (ApplyExp)inlinecalls.visit(new ApplyExp(applyexp.getFunction(), aexpression5), ((Type) (obj1)));
                } else
                {
                    PrimProcedure primprocedure2 = aprimprocedure[0];
                    Expression aexpression2[] = {
                        expression
                    };
                    applyexp2 = new ApplyExp(primprocedure2, aexpression2);
                }
                applyexp2.setType(((Type) (obj1)));
                obj3 = applyexp2;
                if (aexpression.length > 0)
                {
                    int j6 = 0;
                    do
                    {
                        int k6 = aobj.length;
                        if (j6 >= k6)
                        {
                            break;
                        }
                        Object obj4 = aobj[j6];
                        Type type3;
                        Expression expression1;
                        Expression aexpression4[];
                        SlotSet slotset;
                        if (obj4 instanceof Method)
                        {
                            type3 = ((Method)obj4).getParameterTypes()[0];
                        } else
                        if (obj4 instanceof Field)
                        {
                            type3 = ((Field)obj4).getType();
                        } else
                        {
                            type3 = null;
                        }
                        if (type3 != null)
                        {
                            type3 = invoke.language.getLangTypeFor(type3);
                        }
                        expression1 = inlinecalls.visit(aexpression[1 + (k5 + j6 * 2)], type3);
                        aexpression4 = new Expression[3];
                        aexpression4[0] = applyexp2;
                        aexpression4[1] = new QuoteExp(obj4);
                        aexpression4[2] = expression1;
                        slotset = SlotSet.setFieldReturnObject;
                        applyexp2 = new ApplyExp(slotset, aexpression4);
                        applyexp2.setType(((Type) (obj1)));
                        j6++;
                    } while (true);
                    int l6;
                    int i7;
                    if (k5 == aexpression.length)
                    {
                        l6 = 1;
                    } else
                    {
                        l6 = k5 + 2 * aobj.length;
                    }
                    obj3 = applyexp2;
                    i7 = aexpression.length;
                    if (l6 < i7)
                    {
                        LetExp letexp = new LetExp(new Expression[] {
                            obj3
                        });
                        Declaration declaration = letexp.addDeclaration((String)null, ((Type) (obj1)));
                        declaration.noteValue(((Expression) (obj3)));
                        BeginExp beginexp = new BeginExp();
                        int j7 = l6;
                        do
                        {
                            int k7 = aexpression.length;
                            if (j7 >= k7)
                            {
                                break;
                            }
                            Expression aexpression3[] = new Expression[3];
                            aexpression3[0] = new ReferenceExp(declaration);
                            aexpression3[1] = QuoteExp.getInstance("add");
                            aexpression3[2] = aexpression[j7];
                            beginexp.add(inlinecalls.visit(new ApplyExp(Invoke.invoke, aexpression3), null));
                            j7++;
                        } while (true);
                        beginexp.add(new ReferenceExp(declaration));
                        letexp.body = beginexp;
                        obj3 = letexp;
                    }
                }
                return inlinecalls.checkType(((Expression) (obj3)).setLine(applyexp), type);
            }
        }
        if (k < 0) goto _L4; else goto _L3
_L3:
        int j3 = 1;
_L6:
        boolean flag;
        Object obj2;
        if (j3 >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        int k3 = i - 1;
        if (j3 == k3)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (c == 'P' && j3 == 2 || c != 'N' && j3 == 1)
        {
            obj2 = null;
        } else
        {
label2:
            {
                if (c != 'P' || j3 != 1)
                {
                    break label2;
                }
                obj2 = obj1;
            }
        }
_L8:
        aexpression[j3] = inlinecalls.visit(aexpression[j3], ((Type) (obj2)));
        j3++;
        if (true) goto _L6; else goto _L5
        obj2 = null;
        if (k <= 0) goto _L8; else goto _L7
_L7:
        int k4;
        int i4;
        int j4;
        if (c == 'N')
        {
            i4 = 1;
        } else
        {
            i4 = byte0;
        }
        j4 = j3 - i4;
        k4 = 0;
_L11:
        if (k4 >= k) goto _L8; else goto _L9
_L9:
        PrimProcedure primprocedure1 = aprimprocedure[k4];
        int i5;
        int j5;
        if (c != 'S' && primprocedure1.takesTarget())
        {
            i5 = 1;
        } else
        {
            i5 = 0;
        }
        j5 = j4 + i5;
        if (flag && primprocedure1.takesVarArgs() && j5 == primprocedure1.minArgs())
        {
            obj2 = null;
        } else
        {
            Type type2 = primprocedure1.getParameterType(j5);
            if (k4 == 0)
            {
                obj2 = type2;
            } else
            if ((type2 instanceof PrimType) != (obj2 instanceof PrimType))
            {
                obj2 = null;
            } else
            {
                obj2 = Type.lowestCommonSuperType(((Type) (obj2)), type2);
            }
        }
        if (obj2 == null) goto _L8; else goto _L10
_L10:
        k4++;
          goto _L11
_L5:
        int i1;
        int j1;
        long l4 = selectApplicable(aprimprocedure, ((ObjectType) (obj1)), aexpression, j, byte0, byte1);
        j1 = (int)(l4 >> 32);
        i1 = (int)l4;
_L13:
        int k1 = aprimprocedure.length;
        if (j1 + i1 == 0 && c == 'N')
        {
            Invoke invoke1 = Invoke.invokeStatic;
            aprimprocedure = getMethods(((ObjectType) (obj1)), "valueOf", classtype, invoke1);
            byte0 = 1;
            j = i - 1;
            long l3 = selectApplicable(aprimprocedure, ((ObjectType) (obj1)), aexpression, j, byte0, -1);
            j1 = (int)(l3 >> 32);
            i1 = (int)l3;
        }
        if (j1 + i1 == 0)
        {
            if (c == 'P' || compilation.warnInvokeUnknownMethod())
            {
                if (c == 'N')
                {
                    s = (new StringBuilder()).append(s).append("/valueOf").toString();
                }
                StringBuilder stringbuilder = new StringBuilder();
                int l1;
                int i2;
                int j2;
                int k2;
                int l2;
                char c3;
                if (k1 + aprimprocedure.length == 0)
                {
                    stringbuilder.append("no accessible method '");
                } else
                if (k == 0xfff10000)
                {
                    stringbuilder.append("too few arguments for method '");
                } else
                if (k == 0xfff20000)
                {
                    stringbuilder.append("too many arguments for method '");
                } else
                {
                    stringbuilder.append("no possibly applicable method '");
                }
                stringbuilder.append(s);
                stringbuilder.append("' in ");
                stringbuilder.append(((ObjectType) (obj)).getName());
                if (c == 'P')
                {
                    c3 = 'e';
                } else
                {
                    c3 = 'w';
                }
                compilation.error(c3, stringbuilder.toString());
            }
        } else
        {
label3:
            {
                if (j1 != 1 && (j1 != 0 || i1 != 1))
                {
                    break label3;
                }
                l = 0;
            }
        }
          goto _L12
_L4:
        i1 = 0;
        j1 = 0;
          goto _L13
        if (j1 <= 0)
        {
            break MISSING_BLOCK_LABEL_2597;
        }
        l = MethodProc.mostSpecific(aprimprocedure, j1);
        if (l >= 0 || c != 'S') goto _L15; else goto _L14
_L14:
        i3 = 0;
_L19:
        if (i3 >= j1) goto _L15; else goto _L16
_L16:
        if (!aprimprocedure[i3].getStaticFlag())
        {
            break MISSING_BLOCK_LABEL_2584;
        }
        if (l < 0) goto _L18; else goto _L17
_L17:
        l = -1;
_L15:
        if (l < 0 && (c == 'P' || compilation.warnInvokeUnknownMethod()))
        {
            StringBuffer stringbuffer1 = new StringBuffer();
            stringbuffer1.append("more than one definitely applicable method `");
            stringbuffer1.append(s);
            stringbuffer1.append("' in ");
            stringbuffer1.append(((ObjectType) (obj)).getName());
            append(aprimprocedure, j1, stringbuffer1);
            char c2;
            if (c == 'P')
            {
                c2 = 'e';
            } else
            {
                c2 = 'w';
            }
            compilation.error(c2, stringbuffer1.toString());
        }
          goto _L12
_L18:
        l = i3;
        i3++;
          goto _L19
        if (c == 'P' || compilation.warnInvokeUnknownMethod())
        {
            StringBuffer stringbuffer = new StringBuffer();
            stringbuffer.append("more than one possibly applicable method '");
            stringbuffer.append(s);
            stringbuffer.append("' in ");
            stringbuffer.append(((ObjectType) (obj)).getName());
            append(aprimprocedure, i1, stringbuffer);
            char c1;
            if (c == 'P')
            {
                c1 = 'e';
            } else
            {
                c1 = 'w';
            }
            compilation.error(c1, stringbuffer.toString());
        }
_L12:
        if (l < 0)
        {
            break MISSING_BLOCK_LABEL_2728;
        }
        Expression aexpression1[] = new Expression[j];
        PrimProcedure primprocedure = aprimprocedure[l];
        primprocedure.takesVarArgs();
        l1 = 0;
        if (byte1 >= 0)
        {
            l2 = 0 + 1;
            aexpression1[0] = aexpression[byte1];
            l1 = l2;
        }
        i2 = byte0;
        int i3;
        do
        {
            j2 = aexpression.length;
            if (i2 >= j2)
            {
                break;
            }
            k2 = aexpression1.length;
            if (l1 >= k2)
            {
                break;
            }
            aexpression1[l1] = aexpression[i2];
            i2++;
            l1++;
        } while (true);
        ApplyExp applyexp1 = new ApplyExp(primprocedure, aexpression1);
        applyexp1.setLine(applyexp);
        return inlinecalls.visitApplyOnly(applyexp1, type);
        applyexp.visitArgs(inlinecalls);
        return applyexp;
          goto _L11
    }
}
