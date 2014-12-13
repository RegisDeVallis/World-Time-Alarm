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
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.lists.FString;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;

// Referenced classes of package gnu.kawa.reflect:
//            SlotGet, Invoke, SlotSet

public class CompileReflect
{

    public CompileReflect()
    {
    }

    public static int checkKnownClass(Type type, Compilation compilation)
    {
        if ((type instanceof ClassType) && type.isExisting())
        {
            try
            {
                type.getReflectClass();
            }
            catch (Exception exception)
            {
                compilation.error('e', (new StringBuilder()).append("unknown class: ").append(type.getName()).toString());
                return -1;
            }
            return 1;
        } else
        {
            return 0;
        }
    }

    public static ApplyExp inlineClassName(ApplyExp applyexp, int i, InlineCalls inlinecalls)
    {
        Compilation compilation;
        Expression aexpression[];
        Type type;
label0:
        {
            compilation = inlinecalls.getCompilation();
            Language language = compilation.getLanguage();
            aexpression = applyexp.getArgs();
            if (aexpression.length > i)
            {
                type = language.getTypeFor(aexpression[i]);
                if (type instanceof Type)
                {
                    break label0;
                }
            }
            return applyexp;
        }
        checkKnownClass(type, compilation);
        Expression aexpression1[] = new Expression[aexpression.length];
        System.arraycopy(aexpression, 0, aexpression1, 0, aexpression.length);
        aexpression1[i] = new QuoteExp(type);
        ApplyExp applyexp1 = new ApplyExp(applyexp.getFunction(), aexpression1);
        applyexp1.setLine(applyexp);
        return applyexp1;
    }

    public static Expression validateApplyInstanceOf(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        ApplyExp applyexp1 = inlineClassName(applyexp, 1, inlinecalls);
        Expression aexpression[] = applyexp1.getArgs();
        if (aexpression.length == 2)
        {
            Expression expression = aexpression[0];
            Expression expression1 = aexpression[1];
            if (expression1 instanceof QuoteExp)
            {
                Object obj = ((QuoteExp)expression1).getValue();
                if (obj instanceof Type)
                {
                    Object obj1 = (Type)obj;
                    if (obj1 instanceof PrimType)
                    {
                        obj1 = ((PrimType)obj1).boxedType();
                    }
                    if (expression instanceof QuoteExp)
                    {
                        if (((Type) (obj1)).isInstance(((QuoteExp)expression).getValue()))
                        {
                            return QuoteExp.trueExp;
                        } else
                        {
                            return QuoteExp.falseExp;
                        }
                    }
                    if (!expression.side_effects())
                    {
                        int i = ((Type) (obj1)).compare(expression.getType());
                        if (i == 1 || i == 0)
                        {
                            return QuoteExp.trueExp;
                        }
                        if (i == -3)
                        {
                            return QuoteExp.falseExp;
                        }
                    }
                }
            }
        }
        return applyexp1;
    }

    public static Expression validateApplySlotGet(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        Compilation compilation;
        Language language;
        boolean flag;
        Expression aexpression[];
        Expression expression;
        Expression expression1;
        Object obj;
        applyexp.visitArgs(inlinecalls);
        compilation = inlinecalls.getCompilation();
        language = compilation.getLanguage();
        flag = ((SlotGet)procedure).isStatic;
        aexpression = applyexp.getArgs();
        expression = aexpression[0];
        expression1 = aexpression[1];
        obj = expression1.valueIfConstant();
        if (!(obj instanceof String) && !(obj instanceof FString) && !(obj instanceof Symbol)) goto _L2; else goto _L1
_L1:
        String s = obj.toString();
        if (!flag) goto _L4; else goto _L3
_L3:
        Type type1;
        int j;
        type1 = language.getTypeFor(expression);
        j = checkKnownClass(type1, compilation);
        if (j >= 0) goto _L5; else goto _L2
_L2:
        return applyexp;
_L5:
        if ("class".equals(s))
        {
            if (j > 0)
            {
                return QuoteExp.getInstance(type1.getReflectClass());
            } else
            {
                Method method1 = Compilation.typeType.getDeclaredMethod("getReflectClass", 0);
                ApplyExp applyexp4 = new ApplyExp(method1, new Expression[] {
                    expression
                });
                return applyexp4;
            }
        }
        if (type1 != null)
        {
            Expression aexpression3[] = new Expression[2];
            QuoteExp quoteexp2 = new QuoteExp(type1);
            aexpression3[0] = quoteexp2;
            aexpression3[1] = expression1;
            ApplyExp applyexp3 = new ApplyExp(applyexp.getFunction(), aexpression3);
            applyexp3.setLine(applyexp);
            applyexp = applyexp3;
        }
_L6:
        ObjectType objecttype;
        ClassType classtype;
        gnu.bytecode.Member member;
        Field field;
        if (!(type1 instanceof ArrayType))
        {
            if (!(type1 instanceof ObjectType))
            {
                break MISSING_BLOCK_LABEL_741;
            }
            objecttype = (ObjectType)type1;
            boolean flag2;
            if (compilation.curClass != null)
            {
                classtype = compilation.curClass;
            } else
            {
                classtype = compilation.mainClass;
            }
            member = SlotGet.lookupMember(objecttype, s, classtype);
            if (!(member instanceof Field))
            {
                break MISSING_BLOCK_LABEL_467;
            }
            field = (Field)member;
            if ((8 & field.getModifiers()) != 0)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (flag && !flag2)
            {
                ErrorExp errorexp3 = new ErrorExp((new StringBuilder()).append("cannot access non-static field `").append(s).append("' using `").append(procedure.getName()).append('\'').toString(), compilation);
                return errorexp3;
            }
            break MISSING_BLOCK_LABEL_395;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        type1 = expression.getType();
          goto _L6
        if (true) goto _L2; else goto _L7
_L7:
        if (classtype != null && !classtype.isAccessible(field, objecttype))
        {
            ErrorExp errorexp2 = new ErrorExp((new StringBuilder()).append("field ").append(field.getDeclaringClass().getName()).append('.').append(s).append(" is not accessible here").toString(), compilation);
            return errorexp2;
        }
        break MISSING_BLOCK_LABEL_622;
        if (member instanceof Method)
        {
            Method method = (Method)member;
            ClassType classtype2 = method.getDeclaringClass();
            int i = method.getModifiers();
            boolean flag1 = method.getStaticFlag();
            if (flag && !flag1)
            {
                ErrorExp errorexp1 = new ErrorExp((new StringBuilder()).append("cannot call non-static getter method `").append(s).append("' using `").append(procedure.getName()).append('\'').toString(), compilation);
                return errorexp1;
            }
            if (classtype != null && !classtype.isAccessible(classtype2, objecttype, i))
            {
                ErrorExp errorexp = new ErrorExp((new StringBuilder()).append("method ").append(method).append(" is not accessible here").toString(), compilation);
                return errorexp;
            }
        }
        if (member != null)
        {
            Expression aexpression2[] = new Expression[2];
            aexpression2[0] = expression;
            QuoteExp quoteexp1 = new QuoteExp(member);
            aexpression2[1] = quoteexp1;
            ApplyExp applyexp2 = new ApplyExp(applyexp.getFunction(), aexpression2);
            applyexp2.setLine(applyexp);
            return applyexp2;
        }
        ClassType classtype1 = Type.pointer_type;
        if (type1 != classtype1 && compilation.warnUnknownMember())
        {
            compilation.error('e', (new StringBuilder()).append("no slot `").append(s).append("' in ").append(objecttype.getName()).toString());
        }
        String s1 = Compilation.mangleNameIfNeeded(s).intern();
        String s2 = ClassExp.slotToMethodName("get", s);
        String s3 = ClassExp.slotToMethodName("is", s);
        Invoke invoke = Invoke.invokeStatic;
        Expression aexpression1[] = new Expression[9];
        aexpression1[0] = QuoteExp.getInstance("gnu.kawa.reflect.SlotGet");
        aexpression1[1] = QuoteExp.getInstance("getSlotValue");
        QuoteExp quoteexp;
        ApplyExp applyexp1;
        if (flag)
        {
            quoteexp = QuoteExp.trueExp;
        } else
        {
            quoteexp = QuoteExp.falseExp;
        }
        aexpression1[2] = quoteexp;
        aexpression1[3] = aexpression[0];
        aexpression1[4] = QuoteExp.getInstance(s);
        aexpression1[5] = QuoteExp.getInstance(s1);
        aexpression1[6] = QuoteExp.getInstance(s2);
        aexpression1[7] = QuoteExp.getInstance(s3);
        aexpression1[8] = QuoteExp.getInstance(language);
        applyexp1 = new ApplyExp(invoke, aexpression1);
        applyexp1.setLine(applyexp);
        return inlinecalls.visitApplyOnly(applyexp1, null);
    }

    public static Expression validateApplySlotSet(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        SlotSet slotset = (SlotSet)procedure;
        if (slotset.isStatic && inlinecalls.getCompilation().mustCompile)
        {
            applyexp = inlineClassName(applyexp, 0, inlinecalls);
        }
        Object obj;
        if (slotset.returnSelf && applyexp.getArgCount() == 3)
        {
            obj = applyexp.getArg(0).getType();
        } else
        {
            obj = Type.voidType;
        }
        applyexp.setType(((Type) (obj)));
        return applyexp;
    }

    public static Expression validateApplyTypeSwitch(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        Expression aexpression[] = applyexp.getArgs();
        for (int i = 1; i < aexpression.length; i++)
        {
            if (aexpression[i] instanceof LambdaExp)
            {
                LambdaExp lambdaexp = (LambdaExp)aexpression[i];
                lambdaexp.setInlineOnly(true);
                lambdaexp.returnContinuation = applyexp;
                lambdaexp.inlineHome = inlinecalls.getCurrentLambda();
            }
        }

        return applyexp;
    }
}
