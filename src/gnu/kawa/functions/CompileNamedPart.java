// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Language;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.kawa.reflect.ClassMethods;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.SlotSet;
import gnu.mapping.Environment;
import gnu.mapping.HasNamedParts;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import kawa.lang.Translator;

// Referenced classes of package gnu.kawa.functions:
//            GetNamedExp, GetNamedInstancePart, NamedPart, NamedPartSetter, 
//            SetNamedInstancePart

public class CompileNamedPart
{

    static final ClassType typeHasNamedParts = ClassType.make("gnu.mapping.HasNamedParts");

    public CompileNamedPart()
    {
    }

    public static String combineName(Expression expression, Expression expression1)
    {
label0:
        {
            Object obj;
            String s;
label1:
            {
                obj = expression1.valueIfConstant();
                if (!(obj instanceof SimpleSymbol))
                {
                    break label0;
                }
                if (expression instanceof ReferenceExp)
                {
                    s = ((ReferenceExp)expression).getSimpleName();
                    if (s != null)
                    {
                        break label1;
                    }
                }
                if (!(expression instanceof GetNamedExp))
                {
                    break label0;
                }
                s = ((GetNamedExp)expression).combinedName;
                if (s == null)
                {
                    break label0;
                }
            }
            return (new StringBuilder()).append(s).append(':').append(obj).toString().intern();
        }
        return null;
    }

    public static Expression makeExp(Type type, String s)
    {
        return makeExp(((Expression) (new QuoteExp(type))), ((Expression) (new QuoteExp(s))));
    }

    public static Expression makeExp(Expression expression, Expression expression1)
    {
        String s = combineName(expression, expression1);
        Environment environment = Environment.getCurrent();
        if (s != null)
        {
            Translator translator = (Translator)Compilation.getCurrent();
            Symbol symbol1 = Namespace.EmptyNamespace.getSymbol(s);
            Declaration declaration = translator.lexical.lookup(symbol1, false);
            if (!Declaration.isUnknown(declaration))
            {
                return new ReferenceExp(declaration);
            }
            if (symbol1 != null && environment.isBound(symbol1, null))
            {
                return new ReferenceExp(s);
            }
        }
        if (!(expression instanceof ReferenceExp))
        {
            break MISSING_BLOCK_LABEL_162;
        }
        ReferenceExp referenceexp = (ReferenceExp)expression;
        if (!referenceexp.isUnknown())
        {
            break MISSING_BLOCK_LABEL_162;
        }
        Object obj = referenceexp.getSymbol();
        GetNamedExp getnamedexp;
        Symbol symbol;
        String s1;
        QuoteExp quoteexp;
        if (obj instanceof Symbol)
        {
            symbol = (Symbol)obj;
        } else
        {
            symbol = environment.getSymbol(obj.toString());
        }
        if (environment.get(symbol, null) != null)
        {
            break MISSING_BLOCK_LABEL_162;
        }
        s1 = referenceexp.getName();
        quoteexp = QuoteExp.getInstance(Type.make(ClassType.getContextClass(s1)));
        expression = quoteexp;
_L2:
        getnamedexp = new GetNamedExp(new Expression[] {
            expression, expression1
        });
        getnamedexp.combinedName = s;
        return getnamedexp;
        Throwable throwable;
        throwable;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static Expression makeExp(Expression expression, String s)
    {
        return makeExp(expression, ((Expression) (new QuoteExp(s))));
    }

    public static Expression makeGetNamedInstancePartExp(Expression expression)
    {
        if (expression instanceof QuoteExp)
        {
            Object obj = ((QuoteExp)expression).getValue();
            if (obj instanceof SimpleSymbol)
            {
                return QuoteExp.getInstance(new GetNamedInstancePart(obj.toString()));
            }
        }
        Expression aexpression[] = new Expression[2];
        aexpression[0] = new QuoteExp(ClassType.make("gnu.kawa.functions.GetNamedInstancePart"));
        aexpression[1] = expression;
        return new ApplyExp(Invoke.make, aexpression);
    }

    public static Expression validateGetNamedInstancePart(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        Expression aexpression[] = applyexp.getArgs();
        GetNamedInstancePart getnamedinstancepart = (GetNamedInstancePart)procedure;
        Expression aexpression1[];
        Object obj;
        if (getnamedinstancepart.isField)
        {
            aexpression1 = new Expression[2];
            aexpression1[0] = aexpression[0];
            aexpression1[1] = new QuoteExp(getnamedinstancepart.pname);
            obj = SlotGet.field;
        } else
        {
            int i = aexpression.length;
            aexpression1 = new Expression[i + 1];
            aexpression1[0] = aexpression[0];
            aexpression1[1] = new QuoteExp(getnamedinstancepart.pname);
            System.arraycopy(aexpression, 1, aexpression1, 2, i - 1);
            obj = Invoke.invoke;
        }
        return inlinecalls.visitApplyOnly(new ApplyExp(((Procedure) (obj)), aexpression1), type);
    }

    public static Expression validateGetNamedPart(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        Expression aexpression[];
        applyexp.visitArgs(inlinecalls);
        aexpression = applyexp.getArgs();
        if (aexpression.length == 2 && (aexpression[1] instanceof QuoteExp) && (applyexp instanceof GetNamedExp)) goto _L2; else goto _L1
_L1:
        return applyexp;
_L2:
        Declaration declaration;
        String s;
        Type type1;
        Compilation compilation;
        Language language;
        Type type2;
        ClassType classtype;
        GetNamedExp getnamedexp;
        Expression expression = aexpression[0];
        boolean flag = expression instanceof ReferenceExp;
        declaration = null;
        if (flag)
        {
            ReferenceExp referenceexp = (ReferenceExp)expression;
            if ("*".equals(referenceexp.getName()))
            {
                return makeGetNamedInstancePartExp(aexpression[1]);
            }
            declaration = referenceexp.getBinding();
        }
        s = ((QuoteExp)aexpression[1]).getValue().toString();
        type1 = expression.getType();
        if (expression != QuoteExp.nullExp);
        compilation = inlinecalls.getCompilation();
        language = compilation.getLanguage();
        type2 = language.getTypeFor(expression, false);
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
        getnamedexp = (GetNamedExp)applyexp;
        if (type2 != null)
        {
            if (s.equals("<>"))
            {
                QuoteExp quoteexp1 = new QuoteExp(type2);
                return quoteexp1;
            }
            if (type2 instanceof ObjectType)
            {
                if (s.equals("new"))
                {
                    return getnamedexp.setProcedureKind('N');
                }
                if (s.equals("instance?"))
                {
                    return getnamedexp.setProcedureKind('I');
                }
                if (s.equals("@"))
                {
                    return getnamedexp.setProcedureKind('C');
                }
            }
        }
        if (!(type2 instanceof ObjectType))
        {
            break; /* Loop/switch isn't completed */
        }
        if (s.length() > 1 && s.charAt(0) == '.')
        {
            NamedPart namedpart = new NamedPart(type2, s, 'D');
            QuoteExp quoteexp = new QuoteExp(namedpart);
            return quoteexp;
        }
        if (CompileReflect.checkKnownClass(type2, compilation) >= 0)
        {
            gnu.expr.PrimProcedure aprimprocedure1[] = ClassMethods.getMethods((ObjectType)type2, Compilation.mangleName(s), '\0', classtype, language);
            if (aprimprocedure1 != null && aprimprocedure1.length > 0)
            {
                getnamedexp.methods = aprimprocedure1;
                return getnamedexp.setProcedureKind('S');
            } else
            {
                ApplyExp applyexp3 = new ApplyExp(SlotGet.staticField, aexpression);
                applyexp3.setLine(applyexp);
                return inlinecalls.visitApplyOnly(applyexp3, type);
            }
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (type2 == null);
        if (!type1.isSubtype(Compilation.typeClassType) && !type1.isSubtype(Type.javalangClassType))
        {
            if (type1 instanceof ObjectType)
            {
                ObjectType objecttype = (ObjectType)type1;
                gnu.expr.PrimProcedure aprimprocedure[] = ClassMethods.getMethods(objecttype, Compilation.mangleName(s), 'V', classtype, language);
                if (aprimprocedure != null && aprimprocedure.length > 0)
                {
                    getnamedexp.methods = aprimprocedure;
                    return getnamedexp.setProcedureKind('M');
                }
                if (type1.isSubtype(typeHasNamedParts))
                {
                    if (declaration != null)
                    {
                        Object obj = Declaration.followAliases(declaration).getConstantValue();
                        if (obj != null)
                        {
                            HasNamedParts hasnamedparts = (HasNamedParts)obj;
                            if (hasnamedparts.isConstant(s))
                            {
                                return QuoteExp.getInstance(hasnamedparts.get(s));
                            }
                        }
                    }
                    Expression aexpression1[] = new Expression[2];
                    aexpression1[0] = aexpression[0];
                    aexpression1[1] = QuoteExp.getInstance(s);
                    ApplyExp applyexp2 = new ApplyExp(typeHasNamedParts.getDeclaredMethod("get", 1), aexpression1);
                    return applyexp2.setLine(applyexp);
                }
                if (SlotGet.lookupMember(objecttype, s, classtype) != null || s.equals("length") && (type1 instanceof ArrayType))
                {
                    ApplyExp applyexp1 = new ApplyExp(SlotGet.field, aexpression);
                    applyexp1.setLine(applyexp);
                    return inlinecalls.visitApplyOnly(applyexp1, type);
                }
            }
            if (compilation.warnUnknownMember())
            {
                compilation.error('w', (new StringBuilder()).append("no known slot '").append(s).append("' in ").append(type1.getName()).toString());
                return applyexp;
            }
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public static Expression validateNamedPart(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        Expression aexpression[] = applyexp.getArgs();
        NamedPart namedpart = (NamedPart)procedure;
        String s;
        switch (namedpart.kind)
        {
        default:
            return applyexp;

        case 68: // 'D'
            s = namedpart.member.toString().substring(1);
            break;
        }
        Expression aexpression1[] = new Expression[2];
        aexpression1[1] = QuoteExp.getInstance(s);
        SlotGet slotget;
        ApplyExp applyexp1;
        if (aexpression.length > 0)
        {
            aexpression1[0] = Compilation.makeCoercion(aexpression[0], new QuoteExp(namedpart.container));
            slotget = SlotGet.field;
        } else
        {
            aexpression1[0] = QuoteExp.getInstance(namedpart.container);
            slotget = SlotGet.staticField;
        }
        applyexp1 = new ApplyExp(slotget, aexpression1);
        applyexp1.setLine(applyexp);
        return inlinecalls.visitApplyOnly(applyexp1, type);
    }

    public static Expression validateNamedPartSetter(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        NamedPart namedpart;
        applyexp.visitArgs(inlinecalls);
        namedpart = (NamedPart)((NamedPartSetter)procedure).getGetter();
        if (namedpart.kind != 'D') goto _L2; else goto _L1
_L1:
        Expression aexpression[];
        aexpression = new Expression[3];
        aexpression[1] = QuoteExp.getInstance(namedpart.member.toString().substring(1));
        aexpression[2] = applyexp.getArgs()[0];
        if (applyexp.getArgCount() != 1) goto _L4; else goto _L3
_L3:
        SlotSet slotset;
        aexpression[0] = QuoteExp.getInstance(namedpart.container);
        slotset = SlotSet.set$Mnstatic$Mnfield$Ex;
_L7:
        ApplyExp applyexp1 = new ApplyExp(slotset, aexpression);
        applyexp1.setLine(applyexp);
        applyexp = inlinecalls.visitApplyOnly(applyexp1, type);
_L2:
        return applyexp;
_L4:
        if (applyexp.getArgCount() != 2) goto _L2; else goto _L5
_L5:
        aexpression[0] = Compilation.makeCoercion(applyexp.getArgs()[0], new QuoteExp(namedpart.container));
        slotset = SlotSet.set$Mnfield$Ex;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public static Expression validateSetNamedInstancePart(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        Expression aexpression[] = applyexp.getArgs();
        String s = ((SetNamedInstancePart)procedure).pname;
        Expression aexpression1[] = new Expression[3];
        aexpression1[0] = aexpression[0];
        aexpression1[1] = new QuoteExp(s);
        aexpression1[2] = aexpression[1];
        return inlinecalls.visitApplyOnly(new ApplyExp(SlotSet.set$Mnfield$Ex, aexpression1), type);
    }

    public static Expression validateSetNamedPart(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        Expression aexpression[];
        String s;
        Type type1;
        ClassType classtype;
        applyexp.visitArgs(inlinecalls);
        aexpression = applyexp.getArgs();
        if (aexpression.length != 3 || !(aexpression[1] instanceof QuoteExp))
        {
            return applyexp;
        }
        Expression expression = aexpression[0];
        s = ((QuoteExp)aexpression[1]).getValue().toString();
        type1 = expression.getType();
        Compilation compilation = inlinecalls.getCompilation();
        Type type2 = compilation.getLanguage().getTypeFor(expression);
        ApplyExp applyexp1;
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
        applyexp1 = applyexp;
        if (!(type2 instanceof ClassType)) goto _L2; else goto _L1
_L1:
        applyexp = new ApplyExp(SlotSet.set$Mnstatic$Mnfield$Ex, aexpression);
_L4:
        if (applyexp != applyexp1)
        {
            applyexp.setLine(applyexp1);
        }
        applyexp.setType(Type.voidType);
        return applyexp;
_L2:
        if ((type1 instanceof ClassType) && SlotSet.lookupMember((ClassType)type1, s, classtype) != null)
        {
            applyexp = new ApplyExp(SlotSet.set$Mnfield$Ex, aexpression);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

}
