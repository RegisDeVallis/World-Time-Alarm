// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.text.SourceMessages;
import java.lang.reflect.InvocationTargetException;

// Referenced classes of package gnu.expr:
//            ExpExpVisitor, QuoteExp, LambdaExp, Expression, 
//            Declaration, ApplyExp, LetExp, Compilation, 
//            ObjectExp, Language, TypeValue, BeginExp, 
//            IfExp, ReferenceExp, ScopeExp, SetExp, 
//            TryExp

public class InlineCalls extends ExpExpVisitor
{

    private static Class inlinerMethodArgTypes[];

    public InlineCalls(Compilation compilation)
    {
        setContext(compilation);
    }

    public static Integer checkIntValue(Expression expression)
    {
        if (expression instanceof QuoteExp)
        {
            QuoteExp quoteexp = (QuoteExp)expression;
            Object obj = quoteexp.getValue();
            if (!quoteexp.isExplicitlyTyped() && (obj instanceof IntNum))
            {
                IntNum intnum = (IntNum)obj;
                if (intnum.inIntRange())
                {
                    return Integer.valueOf(intnum.intValue());
                }
            }
        }
        return null;
    }

    public static Long checkLongValue(Expression expression)
    {
        if (expression instanceof QuoteExp)
        {
            QuoteExp quoteexp = (QuoteExp)expression;
            Object obj = quoteexp.getValue();
            if (!quoteexp.isExplicitlyTyped() && (obj instanceof IntNum))
            {
                IntNum intnum = (IntNum)obj;
                if (intnum.inLongRange())
                {
                    return Long.valueOf(intnum.longValue());
                }
            }
        }
        return null;
    }

    private static Class[] getInlinerMethodArgTypes()
        throws Exception
    {
        gnu/expr/InlineCalls;
        JVM INSTR monitorenter ;
        Class aclass[] = inlinerMethodArgTypes;
        if (aclass != null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        aclass = new Class[4];
        aclass[0] = Class.forName("gnu.expr.ApplyExp");
        aclass[1] = Class.forName("gnu.expr.InlineCalls");
        aclass[2] = Class.forName("gnu.bytecode.Type");
        aclass[3] = Class.forName("gnu.mapping.Procedure");
        inlinerMethodArgTypes = aclass;
        gnu/expr/InlineCalls;
        JVM INSTR monitorexit ;
        return aclass;
        Exception exception;
        exception;
        throw exception;
    }

    public static Expression inlineCall(LambdaExp lambdaexp, Expression aexpression[], boolean flag)
    {
        if (lambdaexp.keywords != null || lambdaexp.nameDecl != null && !flag)
        {
            return null;
        }
        boolean flag1;
        if (lambdaexp.max_args < 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (lambdaexp.min_args == lambdaexp.max_args && lambdaexp.min_args == aexpression.length || flag1 && lambdaexp.min_args == 0)
        {
            Declaration declaration = null;
            int i = 0;
            IdentityHashTable identityhashtable;
            Expression aexpression1[];
            if (flag)
            {
                identityhashtable = new IdentityHashTable();
                aexpression1 = Expression.deepCopy(aexpression, identityhashtable);
                if (aexpression1 == null && aexpression != null)
                {
                    return null;
                }
            } else
            {
                identityhashtable = null;
                aexpression1 = aexpression;
            }
            if (flag1)
            {
                Expression aexpression2[] = new Expression[1 + aexpression.length];
                aexpression2[0] = QuoteExp.getInstance(lambdaexp.firstDecl().type);
                System.arraycopy(aexpression, 0, aexpression2, 1, aexpression.length);
                aexpression1 = new Expression[1];
                aexpression1[0] = new ApplyExp(Invoke.make, aexpression2);
            }
            LetExp letexp = new LetExp(aexpression1);
            Declaration declaration1 = lambdaexp.firstDecl();
            while (declaration1 != null) 
            {
                Declaration declaration2 = declaration1.nextDecl();
                if (flag)
                {
                    Declaration declaration3 = letexp.addDeclaration(declaration1.symbol, declaration1.type);
                    if (declaration1.typeExp != null)
                    {
                        declaration3.typeExp = Expression.deepCopy(declaration1.typeExp);
                        if (declaration3.typeExp == null)
                        {
                            return null;
                        }
                    }
                    identityhashtable.put(declaration1, declaration3);
                } else
                {
                    lambdaexp.remove(declaration, declaration1);
                    letexp.add(declaration, declaration1);
                }
                if (!flag1 && !declaration1.getCanWrite())
                {
                    declaration1.setValue(aexpression1[i]);
                }
                declaration = declaration1;
                declaration1 = declaration2;
                i++;
            }
            Expression expression = lambdaexp.body;
            if (flag)
            {
                expression = Expression.deepCopy(expression, identityhashtable);
                if (expression == null && lambdaexp.body != null)
                {
                    return null;
                }
            }
            letexp.body = expression;
            return letexp;
        } else
        {
            return null;
        }
    }

    public static Expression inlineCalls(Expression expression, Compilation compilation)
    {
        return (new InlineCalls(compilation)).visit(expression, ((Type) (null)));
    }

    public Expression checkType(Expression expression, Type type)
    {
        int i = 1;
        Object obj = expression.getType();
        if ((type instanceof ClassType) && ((ClassType)type).isInterface() && ((Type) (obj)).isSubtype(Compilation.typeProcedure) && !((Type) (obj)).isSubtype(type))
        {
            if (expression instanceof LambdaExp)
            {
                Method method = ((ClassType)type).checkSingleAbstractMethod();
                if (method != null)
                {
                    LambdaExp lambdaexp = (LambdaExp)expression;
                    ObjectExp objectexp = new ObjectExp();
                    objectexp.setLocation(expression);
                    Expression aexpression[] = new Expression[i];
                    aexpression[0] = new QuoteExp(type);
                    objectexp.supers = aexpression;
                    objectexp.setTypes(getCompilation());
                    String s = method.getName();
                    objectexp.addMethod(lambdaexp, s);
                    objectexp.addDeclaration(s, Compilation.typeProcedure);
                    objectexp.firstChild = lambdaexp;
                    objectexp.declareParts(comp);
                    return visit(objectexp, type);
                }
            }
            i = 1;
        } else
        {
            if (obj == Type.toStringType)
            {
                obj = Type.javalangStringType;
            }
            if (type == null || type.compare(((Type) (obj))) != -3)
            {
                i = 0;
            }
            if (i != 0 && (type instanceof TypeValue))
            {
                Expression expression1 = ((TypeValue)type).convertValue(expression);
                if (expression1 != null)
                {
                    return expression1;
                }
            }
        }
        if (i != 0)
        {
            Language language = comp.getLanguage();
            comp.error('w', (new StringBuilder()).append("type ").append(language.formatType(((Type) (obj)))).append(" is incompatible with required type ").append(language.formatType(type)).toString(), expression);
        }
        return expression;
    }

    public QuoteExp fixIntValue(Expression expression)
    {
        Integer integer = checkIntValue(expression);
        if (integer != null)
        {
            return new QuoteExp(integer, comp.getLanguage().getTypeFor(Integer.TYPE));
        } else
        {
            return null;
        }
    }

    public QuoteExp fixLongValue(Expression expression)
    {
        Long long1 = checkLongValue(expression);
        if (long1 != null)
        {
            return new QuoteExp(long1, comp.getLanguage().getTypeFor(Long.TYPE));
        } else
        {
            return null;
        }
    }

    public Expression maybeInline(ApplyExp applyexp, Type type, Procedure procedure)
    {
        procedure;
        JVM INSTR monitorenter ;
        Object obj = procedure.getProperty(Procedure.validateApplyKey, null);
        if (!(obj instanceof String)) goto _L2; else goto _L1
_L1:
        String s;
        int i;
        s = (String)obj;
        i = s.indexOf(':');
        java.lang.reflect.Method method;
        method = null;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_88;
        }
        String s1 = s.substring(0, i);
        String s2 = s.substring(i + 1);
        method = Class.forName(s1, true, procedure.getClass().getClassLoader()).getDeclaredMethod(s2, getInlinerMethodArgTypes());
        if (method != null) goto _L4; else goto _L3
_L3:
        error('e', (new StringBuilder()).append("inliner property string for ").append(procedure).append(" is not of the form CLASS:METHOD").toString());
        procedure;
        JVM INSTR monitorexit ;
        return null;
_L2:
        procedure;
        JVM INSTR monitorexit ;
        if (obj == null) goto _L6; else goto _L5
_L5:
        Object aobj[] = {
            applyexp, this, type, procedure
        };
        if (!(obj instanceof Procedure)) goto _L8; else goto _L7
_L7:
        Expression expression1 = (Expression)((Procedure)obj).applyN(aobj);
        return expression1;
        Exception exception;
        exception;
        procedure;
        JVM INSTR monitorexit ;
        try
        {
            throw exception;
        }
        catch (Throwable throwable)
        {
            if (throwable instanceof InvocationTargetException)
            {
                throwable = ((InvocationTargetException)throwable).getTargetException();
            }
            messages.error('e', (new StringBuilder()).append("caught exception in inliner for ").append(procedure).append(" - ").append(throwable).toString(), throwable);
        }
_L6:
        return null;
_L8:
        if (!(obj instanceof java.lang.reflect.Method)) goto _L6; else goto _L9
_L9:
        Expression expression = (Expression)((java.lang.reflect.Method)obj).invoke(null, aobj);
        return expression;
_L4:
        obj = method;
        if (true) goto _L2; else goto _L10
_L10:
    }

    public Expression visit(Expression expression, Type type)
    {
        if (!expression.getFlag(1))
        {
            expression.setFlag(1);
            expression = (Expression)super.visit(expression, type);
            expression.setFlag(1);
        }
        return checkType(expression, type);
    }

    public volatile Object visit(Expression expression, Object obj)
    {
        return visit(expression, (Type)obj);
    }

    protected Expression visitApplyExp(ApplyExp applyexp, Type type)
    {
        Expression expression = applyexp.func;
        if (expression instanceof LambdaExp)
        {
            LambdaExp _tmp = (LambdaExp)expression;
            Expression expression2 = inlineCall((LambdaExp)expression, applyexp.args, false);
            if (expression2 != null)
            {
                return visit(expression2, type);
            }
        }
        Expression expression1 = visit(expression, ((Type) (null)));
        applyexp.func = expression1;
        return expression1.validateApply(applyexp, this, type, null);
    }

    protected volatile Object visitApplyExp(ApplyExp applyexp, Object obj)
    {
        return visitApplyExp(applyexp, (Type)obj);
    }

    public final Expression visitApplyOnly(ApplyExp applyexp, Type type)
    {
        return applyexp.func.validateApply(applyexp, this, type, null);
    }

    protected Expression visitBeginExp(BeginExp beginexp, Type type)
    {
        int i = -1 + beginexp.length;
        int j = 0;
        while (j <= i) 
        {
            Expression aexpression[] = beginexp.exps;
            Expression expression = beginexp.exps[j];
            Type type1;
            if (j < i)
            {
                type1 = null;
            } else
            {
                type1 = type;
            }
            aexpression[j] = visit(expression, type1);
            j++;
        }
        return beginexp;
    }

    protected volatile Object visitBeginExp(BeginExp beginexp, Object obj)
    {
        return visitBeginExp(beginexp, (Type)obj);
    }

    protected Expression visitIfExp(IfExp ifexp, Type type)
    {
        Expression expression = (Expression)ifexp.test.visit(this, null);
        if (expression instanceof ReferenceExp)
        {
            Declaration declaration = ((ReferenceExp)expression).getBinding();
            if (declaration != null)
            {
                Expression expression1 = declaration.getValue();
                if ((expression1 instanceof QuoteExp) && expression1 != QuoteExp.undefined_exp)
                {
                    expression = expression1;
                }
            }
        }
        ifexp.test = expression;
        if (exitValue == null)
        {
            ifexp.then_clause = visit(ifexp.then_clause, type);
        }
        if (exitValue == null && ifexp.else_clause != null)
        {
            ifexp.else_clause = visit(ifexp.else_clause, type);
        }
        if (expression instanceof QuoteExp)
        {
            ifexp = ifexp.select(comp.getLanguage().isTrue(((QuoteExp)expression).getValue()));
        } else
        if (expression.getType().isVoid())
        {
            boolean flag = comp.getLanguage().isTrue(Values.empty);
            comp.error('w', (new StringBuilder()).append("void-valued condition is always ").append(flag).toString());
            return new BeginExp(expression, ifexp.select(flag));
        }
        return ifexp;
    }

    protected volatile Object visitIfExp(IfExp ifexp, Object obj)
    {
        return visitIfExp(ifexp, (Type)obj);
    }

    protected Expression visitLambdaExp(LambdaExp lambdaexp, Type type)
    {
        Declaration declaration = lambdaexp.firstDecl();
        if (declaration != null && declaration.isThisParameter() && !lambdaexp.isClassMethod() && declaration.type == null)
        {
            declaration.setType(comp.mainClass);
        }
        return visitScopeExp(lambdaexp, type);
    }

    protected volatile Object visitLambdaExp(LambdaExp lambdaexp, Object obj)
    {
        return visitLambdaExp(lambdaexp, (Type)obj);
    }

    protected Expression visitLetExp(LetExp letexp, Type type)
    {
        Declaration declaration = letexp.firstDecl();
        int i = letexp.inits.length;
        int j = 0;
        while (j < i) 
        {
            Expression expression2 = letexp.inits[j];
            boolean flag = declaration.getFlag(8192L);
            Type type1;
            Expression expression3;
            if (flag && expression2 != QuoteExp.undefined_exp)
            {
                type1 = declaration.getType();
            } else
            {
                type1 = null;
            }
            expression3 = visit(expression2, type1);
            letexp.inits[j] = expression3;
            if (declaration.value == expression2)
            {
                declaration.value = expression3;
                if (!flag)
                {
                    declaration.setType(expression3.getType());
                }
            }
            j++;
            declaration = declaration.nextDecl();
        }
        if (exitValue == null)
        {
            letexp.body = visit(letexp.body, type);
        }
        if (letexp.body instanceof ReferenceExp)
        {
            ReferenceExp referenceexp = (ReferenceExp)letexp.body;
            Declaration declaration1 = referenceexp.getBinding();
            if (declaration1 != null && declaration1.context == letexp && !referenceexp.getDontDereference() && i == 1)
            {
                Expression expression = letexp.inits[0];
                Expression expression1 = declaration1.getTypeExp();
                if (expression1 != QuoteExp.classObjectExp)
                {
                    expression = visitApplyOnly(Compilation.makeCoercion(expression, expression1), null);
                }
                return expression;
            }
        }
        return letexp;
    }

    protected volatile Object visitLetExp(LetExp letexp, Object obj)
    {
        return visitLetExp(letexp, (Type)obj);
    }

    protected Expression visitQuoteExp(QuoteExp quoteexp, Type type)
    {
        if (quoteexp.getRawType() == null && !quoteexp.isSharedConstant())
        {
            Object obj = quoteexp.getValue();
            if (obj != null)
            {
                Object obj1 = comp.getLanguage().getTypeFor(obj.getClass());
                if (obj1 == Type.toStringType)
                {
                    obj1 = Type.javalangStringType;
                }
                quoteexp.type = ((Type) (obj1));
                if (type instanceof PrimType)
                {
                    char c = type.getSignature().charAt(0);
                    QuoteExp quoteexp1;
                    if (c == 'I')
                    {
                        quoteexp1 = fixIntValue(quoteexp);
                    } else
                    if (c == 'J')
                    {
                        quoteexp1 = fixLongValue(quoteexp);
                    } else
                    {
                        quoteexp1 = null;
                    }
                    if (quoteexp1 != null)
                    {
                        quoteexp = quoteexp1;
                    }
                }
            }
        }
        return quoteexp;
    }

    protected volatile Object visitQuoteExp(QuoteExp quoteexp, Object obj)
    {
        return visitQuoteExp(quoteexp, (Type)obj);
    }

    protected Expression visitReferenceExp(ReferenceExp referenceexp, Type type)
    {
        Declaration declaration = referenceexp.getBinding();
        if (declaration != null && declaration.field == null && !declaration.getCanWrite())
        {
            Expression expression = declaration.getValue();
            if ((expression instanceof QuoteExp) && expression != QuoteExp.undefined_exp)
            {
                return visitQuoteExp((QuoteExp)expression, type);
            }
            if ((expression instanceof ReferenceExp) && !declaration.isAlias())
            {
                ReferenceExp referenceexp1 = (ReferenceExp)expression;
                Declaration declaration1 = referenceexp1.getBinding();
                Type type1 = declaration.getType();
                if (declaration1 != null && !declaration1.getCanWrite() && (type1 == null || type1 == Type.pointer_type || type1 == declaration1.getType()) && !referenceexp1.getDontDereference())
                {
                    return visitReferenceExp(referenceexp1, type);
                }
            }
            if (!referenceexp.isProcedureName() && (0x100080L & declaration.flags) == 0x100080L)
            {
                comp.error('e', (new StringBuilder()).append("unimplemented: reference to method ").append(declaration.getName()).append(" as variable").toString());
                comp.error('e', declaration, "here is the definition of ", "");
            }
        }
        return (Expression)super.visitReferenceExp(referenceexp, type);
    }

    protected volatile Object visitReferenceExp(ReferenceExp referenceexp, Object obj)
    {
        return visitReferenceExp(referenceexp, (Type)obj);
    }

    protected Expression visitScopeExp(ScopeExp scopeexp, Type type)
    {
        scopeexp.visitChildren(this, null);
        visitDeclarationTypes(scopeexp);
        Declaration declaration = scopeexp.firstDecl();
        while (declaration != null) 
        {
            if (declaration.type == null)
            {
                Expression expression = declaration.getValue();
                declaration.type = Type.objectType;
                Object obj;
                if (expression != null && expression != QuoteExp.undefined_exp)
                {
                    obj = expression.getType();
                } else
                {
                    obj = Type.objectType;
                }
                declaration.setType(((Type) (obj)));
            }
            declaration = declaration.nextDecl();
        }
        return scopeexp;
    }

    protected volatile Object visitScopeExp(ScopeExp scopeexp, Object obj)
    {
        return visitScopeExp(scopeexp, (Type)obj);
    }

    protected Expression visitSetExp(SetExp setexp, Type type)
    {
        Declaration declaration = setexp.getBinding();
        super.visitSetExp(setexp, type);
        if (!setexp.isDefining() && declaration != null && (0x100080L & declaration.flags) == 0x100080L)
        {
            comp.error('e', (new StringBuilder()).append("can't assign to method ").append(declaration.getName()).toString(), setexp);
        }
        if (declaration != null && declaration.getFlag(8192L) && CompileReflect.checkKnownClass(declaration.getType(), comp) < 0)
        {
            declaration.setType(Type.errorType);
        }
        return setexp;
    }

    protected volatile Object visitSetExp(SetExp setexp, Object obj)
    {
        return visitSetExp(setexp, (Type)obj);
    }

    protected Expression visitSetExpValue(Expression expression, Type type, Declaration declaration)
    {
        Type type1;
        if (declaration == null || declaration.isAlias())
        {
            type1 = null;
        } else
        {
            type1 = declaration.type;
        }
        return visit(expression, type1);
    }

    protected volatile Expression visitSetExpValue(Expression expression, Object obj, Declaration declaration)
    {
        return visitSetExpValue(expression, (Type)obj, declaration);
    }

    protected Expression visitTryExp(TryExp tryexp, Type type)
    {
        if (tryexp.getCatchClauses() == null && tryexp.getFinallyClause() == null)
        {
            return visit(tryexp.try_clause, type);
        } else
        {
            return (Expression)super.visitTryExp(tryexp, type);
        }
    }

    protected volatile Object visitTryExp(TryExp tryexp, Object obj)
    {
        return visitTryExp(tryexp, (Type)obj);
    }
}
