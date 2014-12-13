// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.kawa.reflect.ArrayGet;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.math.Numeric;
import gnu.text.Char;
import java.io.Externalizable;

// Referenced classes of package gnu.kawa.functions:
//            Setter, IsEqv, SetArrayExp, SetListExp

public class CompilationHelpers
{

    public static final Declaration setterDecl;
    static final Field setterField;
    static final ClassType setterType;
    static final ClassType typeList = ClassType.make("java.util.List");

    public CompilationHelpers()
    {
    }

    private static boolean nonNumeric(Expression expression)
    {
        boolean flag = expression instanceof QuoteExp;
        boolean flag1 = false;
        if (flag)
        {
            Object obj = ((QuoteExp)expression).getValue();
            boolean flag2 = obj instanceof Numeric;
            flag1 = false;
            if (!flag2)
            {
                boolean flag3 = obj instanceof Char;
                flag1 = false;
                if (!flag3)
                {
                    boolean flag4 = obj instanceof Symbol;
                    flag1 = false;
                    if (!flag4)
                    {
                        flag1 = true;
                    }
                }
            }
        }
        return flag1;
    }

    public static Expression validateApplyToArgs(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        Expression aexpression[];
        int i;
        aexpression = applyexp.getArgs();
        i = -1 + aexpression.length;
        if (i < 0) goto _L2; else goto _L1
_L1:
        Expression expression;
        Type type1;
        Language language;
        int j;
        ApplyExp applyexp1;
        expression = aexpression[0];
        if (!expression.getFlag(1))
        {
            if (expression instanceof LambdaExp)
            {
                Expression aexpression2[] = new Expression[i];
                System.arraycopy(aexpression, 1, aexpression2, 0, i);
                return inlinecalls.visit((new ApplyExp(expression, aexpression2)).setLine(applyexp), type);
            }
            expression = inlinecalls.visit(expression, null);
            aexpression[0] = expression;
        }
        type1 = expression.getType().getRealType();
        Compilation compilation = inlinecalls.getCompilation();
        language = compilation.getLanguage();
        if (type1.isSubtype(Compilation.typeProcedure))
        {
            Expression aexpression1[] = new Expression[i];
            System.arraycopy(aexpression, 1, aexpression1, 0, i);
            ApplyExp applyexp2 = new ApplyExp(expression, aexpression1);
            applyexp2.setLine(applyexp);
            return expression.validateApply(applyexp2, inlinecalls, type, null);
        }
        j = CompileReflect.checkKnownClass(type1, compilation);
        applyexp1 = null;
        if (j >= 0) goto _L4; else goto _L3
_L3:
        if (applyexp1 != null)
        {
            applyexp1.setLine(applyexp);
            return inlinecalls.visitApplyOnly(applyexp1, type);
        }
        break; /* Loop/switch isn't completed */
_L4:
        if (type1.isSubtype(Compilation.typeType) || language.getTypeFor(expression, false) != null)
        {
            applyexp1 = new ApplyExp(Invoke.make, aexpression);
        } else
        if (type1 instanceof ArrayType)
        {
            applyexp1 = new ApplyExp(new ArrayGet(((ArrayType)type1).getComponentType()), aexpression);
        } else
        {
            boolean flag = type1 instanceof ClassType;
            applyexp1 = null;
            if (flag)
            {
                ClassType classtype = (ClassType)type1;
                boolean flag1 = classtype.isSubclass(typeList);
                applyexp1 = null;
                if (flag1)
                {
                    applyexp1 = null;
                    if (i == 1)
                    {
                        Type atype[] = new Type[1];
                        atype[0] = Type.intType;
                        applyexp1 = new ApplyExp(classtype.getMethod("get", atype), aexpression);
                    }
                }
            }
        }
        if (true) goto _L3; else goto _L2
_L2:
        applyexp.visitArgs(inlinecalls);
        return applyexp;
    }

    public static Expression validateIsEqv(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        Expression aexpression[] = applyexp.getArgs();
        if (nonNumeric(aexpression[0]) || nonNumeric(aexpression[1]))
        {
            applyexp = new ApplyExp(((IsEqv)procedure).isEq, aexpression);
        }
        return applyexp;
    }

    public static Expression validateSetter(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        Expression aexpression[];
        applyexp.visitArgs(inlinecalls);
        aexpression = applyexp.getArgs();
        if (aexpression.length != 1) goto _L2; else goto _L1
_L1:
        Expression expression;
        Type type1;
        expression = aexpression[0];
        type1 = expression.getType();
        if (!(type1 instanceof ArrayType)) goto _L4; else goto _L3
_L3:
        applyexp = new SetArrayExp(expression, (ArrayType)type1);
_L2:
        return applyexp;
_L4:
        if (!(type1 instanceof ClassType) || !((ClassType)type1).isSubclass(typeList))
        {
            break; /* Loop/switch isn't completed */
        }
        if (!(applyexp instanceof SetListExp))
        {
            return new SetListExp(applyexp.getFunction(), aexpression);
        }
        if (true) goto _L2; else goto _L5
_L5:
        if (expression instanceof ReferenceExp)
        {
            Declaration declaration1 = ((ReferenceExp)expression).getBinding();
            if (declaration1 != null)
            {
                expression = declaration1.getValue();
            }
        }
        if (expression instanceof QuoteExp)
        {
            Object obj = ((QuoteExp)expression).getValue();
            if (obj instanceof Procedure)
            {
                Procedure procedure1 = ((Procedure)obj).getSetter();
                if (procedure1 instanceof Procedure)
                {
                    if (procedure1 instanceof Externalizable)
                    {
                        return new QuoteExp(procedure1);
                    }
                    Declaration declaration = Declaration.getDeclaration((Procedure)procedure1);
                    if (declaration != null)
                    {
                        return new ReferenceExp(declaration);
                    }
                }
            }
        }
        if (true) goto _L2; else goto _L6
_L6:
    }

    static 
    {
        setterType = ClassType.make("gnu.kawa.functions.Setter");
        setterField = setterType.getDeclaredField("setter");
        setterDecl = new Declaration("setter", setterField);
        setterDecl.noteValue(new QuoteExp(Setter.setter));
    }
}
