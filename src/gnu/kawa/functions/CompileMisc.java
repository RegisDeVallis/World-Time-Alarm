// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.ConsumerTarget;
import gnu.expr.Declaration;
import gnu.expr.ExpVisitor;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.IgnoreTarget;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.expr.TryExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.reflect.CompileReflect;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.lists.LList;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.WrongArguments;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.functions:
//            CompileTimeContinuation, Convert, ConstantFunction0, Map, 
//            ValuesMap, Not

public class CompileMisc
    implements Inlineable
{
    static class ExitThroughFinallyChecker extends ExpVisitor
    {

        Declaration decl;

        public static boolean check(Declaration declaration, Expression expression)
        {
            ExitThroughFinallyChecker exitthroughfinallychecker = new ExitThroughFinallyChecker();
            exitthroughfinallychecker.decl = declaration;
            exitthroughfinallychecker.visit(expression, null);
            return exitthroughfinallychecker.exitValue != null;
        }

        protected Expression defaultValue(Expression expression, TryExp tryexp)
        {
            return expression;
        }

        protected volatile Object defaultValue(Expression expression, Object obj)
        {
            return defaultValue(expression, (TryExp)obj);
        }

        protected Expression visitReferenceExp(ReferenceExp referenceexp, TryExp tryexp)
        {
            if (decl == referenceexp.getBinding() && tryexp != null)
            {
                exitValue = Boolean.TRUE;
            }
            return referenceexp;
        }

        protected volatile Object visitReferenceExp(ReferenceExp referenceexp, Object obj)
        {
            return visitReferenceExp(referenceexp, (TryExp)obj);
        }

        protected Expression visitTryExp(TryExp tryexp, TryExp tryexp1)
        {
            if (tryexp.getFinallyClause() != null)
            {
                tryexp1 = tryexp;
            }
            visitExpression(tryexp, tryexp1);
            return tryexp;
        }

        protected volatile Object visitTryExp(TryExp tryexp, Object obj)
        {
            return visitTryExp(tryexp, (TryExp)obj);
        }

        ExitThroughFinallyChecker()
        {
        }
    }


    static final int CONVERT = 2;
    static final int NOT = 3;
    static Method coerceMethod;
    public static final ClassType typeContinuation = ClassType.make("kawa.lang.Continuation");
    static ClassType typeType;
    int code;
    Procedure proc;

    public CompileMisc(Procedure procedure, int i)
    {
        proc = procedure;
        code = i;
    }

    private static LambdaExp canInlineCallCC(ApplyExp applyexp)
    {
        Expression aexpression[] = applyexp.getArgs();
        if (aexpression.length == 1)
        {
            Expression expression = aexpression[0];
            if (expression instanceof LambdaExp)
            {
                LambdaExp lambdaexp = (LambdaExp)expression;
                if (lambdaexp.min_args == 1 && lambdaexp.max_args == 1 && !lambdaexp.firstDecl().getCanWrite())
                {
                    return lambdaexp;
                }
            }
        }
        return null;
    }

    public static void compileCallCC(ApplyExp applyexp, Compilation compilation, Target target, Procedure procedure)
    {
        LambdaExp lambdaexp = canInlineCallCC(applyexp);
        if (lambdaexp == null)
        {
            ApplyExp.compile(applyexp, compilation, target);
            return;
        }
        CodeAttr codeattr = compilation.getCode();
        Declaration declaration = lambdaexp.firstDecl();
        if (declaration.isSimple() && !declaration.getCanRead() && !declaration.getCanWrite())
        {
            CompileTimeContinuation compiletimecontinuation = new CompileTimeContinuation();
            Type type;
            if (target instanceof StackTarget)
            {
                type = target.getType();
            } else
            {
                type = null;
            }
            compiletimecontinuation.exitableBlock = codeattr.startExitableBlock(type, ExitThroughFinallyChecker.check(declaration, lambdaexp.body));
            compiletimecontinuation.blockTarget = target;
            declaration.setValue(new QuoteExp(compiletimecontinuation));
            lambdaexp.body.compile(compilation, target);
            codeattr.endExitableBlock();
            return;
        }
        gnu.bytecode.Variable variable = codeattr.pushScope().addVariable(codeattr, typeContinuation, null);
        Declaration declaration1 = new Declaration(variable);
        codeattr.emitNew(typeContinuation);
        codeattr.emitDup(typeContinuation);
        compilation.loadCallContext();
        codeattr.emitInvokeSpecial(typeContinuation.getDeclaredMethod("<init>", 1));
        codeattr.emitStore(variable);
        Object obj;
        Expression aexpression[];
        ReferenceExp referenceexp;
        if ((target instanceof IgnoreTarget) || (target instanceof ConsumerTarget))
        {
            obj = null;
        } else
        {
            obj = Type.objectType;
        }
        codeattr.emitTryStart(false, ((Type) (obj)));
        aexpression = new Expression[1];
        referenceexp = new ReferenceExp(declaration1);
        aexpression[0] = referenceexp;
        (new ApplyExp(lambdaexp, aexpression)).compile(compilation, target);
        if (codeattr.reachableHere())
        {
            codeattr.emitLoad(variable);
            codeattr.emitPushInt(1);
            codeattr.emitPutField(typeContinuation.getField("invoked"));
        }
        codeattr.emitTryEnd();
        codeattr.emitCatchStart(null);
        codeattr.emitLoad(variable);
        if (target instanceof ConsumerTarget)
        {
            compilation.loadCallContext();
            codeattr.emitInvokeStatic(typeContinuation.getDeclaredMethod("handleException$X", 3));
        } else
        {
            codeattr.emitInvokeStatic(typeContinuation.getDeclaredMethod("handleException", 2));
            target.compileFromStack(compilation, Type.objectType);
        }
        codeattr.emitCatchEnd();
        codeattr.emitTryCatchEnd();
        codeattr.popScope();
    }

    public static void compileConvert(Convert convert, ApplyExp applyexp, Compilation compilation, Target target)
    {
        Expression aexpression[] = applyexp.getArgs();
        if (aexpression.length != 2)
        {
            throw new Error((new StringBuilder()).append("wrong number of arguments to ").append(convert.getName()).toString());
        }
        CodeAttr codeattr = compilation.getCode();
        Type type = Scheme.getTypeValue(aexpression[0]);
        if (type != null)
        {
            aexpression[1].compile(compilation, Target.pushValue(type));
            if (codeattr.reachableHere())
            {
                target.compileFromStack(compilation, type);
            }
            return;
        }
        if (typeType == null)
        {
            typeType = ClassType.make("gnu.bytecode.Type");
        }
        if (coerceMethod == null)
        {
            coerceMethod = typeType.addMethod("coerceFromObject", Compilation.apply1args, Type.pointer_type, 1);
        }
        aexpression[0].compile(compilation, LangObjType.typeClassType);
        aexpression[1].compile(compilation, Target.pushObject);
        codeattr.emitInvokeVirtual(coerceMethod);
        target.compileFromStack(compilation, Type.pointer_type);
    }

    public static CompileMisc forConvert(Object obj)
    {
        return new CompileMisc((Procedure)obj, 2);
    }

    public static CompileMisc forNot(Object obj)
    {
        return new CompileMisc((Procedure)obj, 3);
    }

    public static Expression validateApplyAppendValues(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        Expression aexpression[] = applyexp.getArgs();
        Expression expression;
        if (aexpression.length == 1)
        {
            expression = aexpression[0];
        } else
        {
            if (aexpression.length == 0)
            {
                return QuoteExp.voidExp;
            }
            expression = applyexp.inlineIfConstant(procedure, inlinecalls);
            if (expression == applyexp)
            {
                return applyexp;
            }
        }
        return expression;
    }

    public static Expression validateApplyCallCC(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        LambdaExp lambdaexp = canInlineCallCC(applyexp);
        if (lambdaexp != null)
        {
            lambdaexp.setInlineOnly(true);
            lambdaexp.returnContinuation = applyexp;
            lambdaexp.inlineHome = inlinecalls.getCurrentLambda();
            Declaration declaration = lambdaexp.firstDecl();
            if (!declaration.getFlag(8192L))
            {
                declaration.setType(typeContinuation);
            }
        }
        applyexp.visitArgs(inlinecalls);
        return applyexp;
    }

    public static Expression validateApplyConstantFunction0(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        int i = applyexp.getArgCount();
        if (i != 0 && inlinecalls != null)
        {
            return inlinecalls.noteError(WrongArguments.checkArgCount(procedure, i));
        } else
        {
            return ((ConstantFunction0)procedure).constant;
        }
    }

    public static Expression validateApplyConvert(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        Compilation compilation = inlinecalls.getCompilation();
        Language language = compilation.getLanguage();
        Expression aexpression[] = applyexp.getArgs();
        if (aexpression.length == 2)
        {
            aexpression[0] = inlinecalls.visit(aexpression[0], null);
            Type type1 = language.getTypeFor(aexpression[0]);
            if (type1 instanceof Type)
            {
                aexpression[0] = new QuoteExp(type1);
                aexpression[1] = inlinecalls.visit(aexpression[1], type1);
                CompileReflect.checkKnownClass(type1, compilation);
                applyexp.setType(type1);
                return applyexp;
            }
        }
        applyexp.visitArgs(inlinecalls);
        return applyexp;
    }

    public static Expression validateApplyFormat(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        Object obj = Type.objectType;
        Expression aexpression[] = applyexp.getArgs();
        if (aexpression.length > 0)
        {
            ClassType classtype = ClassType.make("gnu.kawa.functions.Format");
            Object obj1 = aexpression[0].valueIfConstant();
            Type type1 = aexpression[0].getType();
            if (obj1 == Boolean.FALSE || type1.isSubtype(LangObjType.stringType))
            {
                int i;
                Expression aexpression1[];
                ApplyExp applyexp1;
                if (obj1 == Boolean.FALSE)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                aexpression1 = new Expression[(1 + aexpression.length) - i];
                aexpression1[0] = new QuoteExp(Integer.valueOf(0), Type.intType);
                System.arraycopy(aexpression, i, aexpression1, 1, -1 + aexpression1.length);
                applyexp1 = new ApplyExp(classtype.getDeclaredMethod("formatToString", 2), aexpression1);
                applyexp1.setType(Type.javalangStringType);
                return applyexp1;
            }
            if (obj1 == Boolean.TRUE || type1.isSubtype(ClassType.make("java.io.Writer")))
            {
                if (obj1 == Boolean.TRUE)
                {
                    Expression aexpression2[] = new Expression[aexpression.length];
                    aexpression2[0] = QuoteExp.nullExp;
                    System.arraycopy(aexpression, 1, aexpression2, 1, -1 + aexpression.length);
                    aexpression = aexpression2;
                }
                ApplyExp applyexp2 = new ApplyExp(classtype.getDeclaredMethod("formatToWriter", 3), aexpression);
                applyexp2.setType(Type.voidType);
                return applyexp2;
            }
            if (type1.isSubtype(ClassType.make("java.io.OutputStream")))
            {
                obj = Type.voidType;
            }
        }
        applyexp.setType(((Type) (obj)));
        return null;
    }

    public static Expression validateApplyMakeProcedure(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        Expression aexpression[];
        int i;
        Object obj;
        int j;
        String s;
        int k;
        applyexp.visitArgs(inlinecalls);
        aexpression = applyexp.getArgs();
        i = aexpression.length;
        obj = null;
        j = 0;
        s = null;
        k = 0;
_L8:
        if (k >= i) goto _L2; else goto _L1
_L1:
        Expression expression2 = aexpression[k];
        if (!(expression2 instanceof QuoteExp)) goto _L4; else goto _L3
_L3:
        Object obj2 = ((QuoteExp)expression2).getValue();
        if (!(obj2 instanceof Keyword)) goto _L4; else goto _L5
_L5:
        String s2 = ((Keyword)obj2).getName();
        k++;
        Expression expression3 = aexpression[k];
        if (s2 == "name")
        {
            if (expression3 instanceof QuoteExp)
            {
                s = ((QuoteExp)expression3).getValue().toString();
            }
        } else
        if (s2 == "method")
        {
            j++;
            obj = expression3;
        }
_L6:
        k++;
        continue; /* Loop/switch isn't completed */
_L4:
        j++;
        obj = expression2;
        if (true) goto _L6; else goto _L2
_L2:
        if (j == 1 && (obj instanceof LambdaExp))
        {
            LambdaExp lambdaexp = (LambdaExp)obj;
            int l = 0;
            while (l < i) 
            {
                Expression expression = aexpression[l];
                if (expression instanceof QuoteExp)
                {
                    Object obj1 = ((QuoteExp)expression).getValue();
                    if (obj1 instanceof Keyword)
                    {
                        String s1 = ((Keyword)obj1).getName();
                        l++;
                        Expression expression1 = aexpression[l];
                        if (s1 == "name")
                        {
                            lambdaexp.setName(s);
                        } else
                        if (s1 != "method")
                        {
                            lambdaexp.setProperty(Namespace.EmptyNamespace.getSymbol(s1), expression1);
                        }
                    }
                }
                l++;
            }
        } else
        {
            obj = applyexp;
        }
        return ((Expression) (obj));
        if (true) goto _L8; else goto _L7
_L7:
    }

    public static Expression validateApplyMap(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        Map map = (Map)procedure;
        boolean flag = map.collect;
        applyexp.visitArgs(inlinecalls);
        Expression aexpression[] = applyexp.getArgs();
        int i = aexpression.length;
        if (i < 2)
        {
            return applyexp;
        }
        int j = i - 1;
        Object obj = aexpression[0];
        boolean flag1;
        Expression aexpression1[];
        LetExp letexp;
        Declaration declaration;
        Expression aexpression2[];
        LetExp letexp1;
        int k;
        LambdaExp lambdaexp;
        Declaration declaration1;
        Expression aexpression3[];
        LetExp letexp2;
        Declaration adeclaration[];
        Declaration adeclaration1[];
        if (!((Expression) (obj)).side_effects())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        aexpression1 = (new Expression[] {
            obj
        });
        letexp = new LetExp(aexpression1);
        declaration = letexp.addDeclaration("%proc", Compilation.typeProcedure);
        declaration.noteValue(aexpression[0]);
        aexpression2 = new Expression[1];
        letexp1 = new LetExp(aexpression2);
        letexp.setBody(letexp1);
        if (flag)
        {
            k = j + 1;
        } else
        {
            k = j;
        }
        lambdaexp = new LambdaExp(k);
        aexpression2[0] = lambdaexp;
        declaration1 = letexp1.addDeclaration("%loop");
        declaration1.noteValue(lambdaexp);
        aexpression3 = new Expression[j];
        letexp2 = new LetExp(aexpression3);
        adeclaration = new Declaration[j];
        adeclaration1 = new Declaration[j];
        for (int l = 0; l < j; l++)
        {
            String s = (new StringBuilder()).append("arg").append(l).toString();
            adeclaration[l] = lambdaexp.addDeclaration(s);
            adeclaration1[l] = letexp2.addDeclaration(s, Compilation.typePair);
            aexpression3[l] = new ReferenceExp(adeclaration[l]);
            adeclaration1[l].noteValue(aexpression3[l]);
        }

        Declaration declaration2;
        Expression aexpression4[];
        int i1;
        Expression aexpression5[];
        if (flag)
        {
            declaration2 = lambdaexp.addDeclaration("result");
        } else
        {
            declaration2 = null;
        }
        aexpression4 = new Expression[j + 1];
        if (flag)
        {
            i1 = j + 1;
        } else
        {
            i1 = j;
        }
        aexpression5 = new Expression[i1];
        for (int j1 = 0; j1 < j; j1++)
        {
            aexpression4[j1 + 1] = inlinecalls.visitApplyOnly(SlotGet.makeGetField(new ReferenceExp(adeclaration1[j1]), "car"), null);
            aexpression5[j1] = inlinecalls.visitApplyOnly(SlotGet.makeGetField(new ReferenceExp(adeclaration1[j1]), "cdr"), null);
        }

        if (!flag1)
        {
            obj = new ReferenceExp(declaration);
        }
        aexpression4[0] = ((Expression) (obj));
        ApplyExp applyexp1 = new ApplyExp(new ReferenceExp(map.applyFieldDecl), aexpression4);
        Expression expression = inlinecalls.visitApplyOnly(applyexp1, null);
        if (flag)
        {
            Expression aexpression9[] = new Expression[2];
            aexpression9[0] = expression;
            ReferenceExp referenceexp2 = new ReferenceExp(declaration2);
            aexpression9[1] = referenceexp2;
            aexpression5[j] = Invoke.makeInvokeStatic(Compilation.typePair, "make", aexpression9);
        }
        ReferenceExp referenceexp = new ReferenceExp(declaration1);
        ApplyExp applyexp2 = new ApplyExp(referenceexp, aexpression5);
        Object obj1 = inlinecalls.visitApplyOnly(applyexp2, null);
        int k1;
        Expression aexpression6[];
        QuoteExp quoteexp;
        int l1;
        if (!flag)
        {
            BeginExp beginexp = new BeginExp(expression, ((Expression) (obj1)));
            obj1 = beginexp;
        }
        lambdaexp.body = ((Expression) (obj1));
        letexp2.setBody(lambdaexp.body);
        lambdaexp.body = letexp2;
        if (flag)
        {
            k1 = j + 1;
        } else
        {
            k1 = j;
        }
        aexpression6 = new Expression[k1];
        quoteexp = new QuoteExp(LList.Empty);
        l1 = j;
        while (--l1 >= 0) 
        {
            Expression aexpression8[] = new Expression[2];
            aexpression8[0] = new ReferenceExp(adeclaration[l1]);
            aexpression8[1] = quoteexp;
            Object obj3;
            ApplyExp applyexp4;
            Expression expression1;
            Expression expression2;
            IfExp ifexp;
            if (flag)
            {
                obj3 = new ReferenceExp(declaration2);
            } else
            {
                obj3 = QuoteExp.voidExp;
            }
            applyexp4 = new ApplyExp(map.isEq, aexpression8);
            expression1 = inlinecalls.visitApplyOnly(applyexp4, null);
            expression2 = lambdaexp.body;
            ifexp = new IfExp(expression1, ((Expression) (obj3)), expression2);
            lambdaexp.body = ifexp;
            aexpression6[l1] = aexpression[l1 + 1];
        }
        if (flag)
        {
            aexpression6[j] = quoteexp;
        }
        ReferenceExp referenceexp1 = new ReferenceExp(declaration1);
        ApplyExp applyexp3 = new ApplyExp(referenceexp1, aexpression6);
        Object obj2 = inlinecalls.visitApplyOnly(applyexp3, null);
        if (flag)
        {
            Expression aexpression7[] = {
                obj2
            };
            obj2 = Invoke.makeInvokeStatic(Compilation.scmListType, "reverseInPlace", aexpression7);
        }
        letexp1.setBody(((Expression) (obj2)));
        if (flag1)
        {
            return letexp1;
        } else
        {
            return letexp;
        }
    }

    public static Expression validateApplyNot(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        applyexp.setType(inlinecalls.getCompilation().getLanguage().getTypeFor(Boolean.TYPE));
        return applyexp.inlineIfConstant(procedure, inlinecalls);
    }

    public static Expression validateApplyValuesMap(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        LambdaExp lambdaexp = ValuesMap.canInline(applyexp, (ValuesMap)procedure);
        if (lambdaexp != null)
        {
            lambdaexp.setInlineOnly(true);
            lambdaexp.returnContinuation = applyexp;
            lambdaexp.inlineHome = inlinecalls.getCurrentLambda();
        }
        return applyexp;
    }

    public void compile(ApplyExp applyexp, Compilation compilation, Target target)
    {
        switch (code)
        {
        default:
            throw new Error();

        case 2: // '\002'
            compileConvert((Convert)proc, applyexp, compilation, target);
            return;

        case 3: // '\003'
            compileNot((Not)proc, applyexp, compilation, target);
            break;
        }
    }

    public void compileNot(Not not, ApplyExp applyexp, Compilation compilation, Target target)
    {
        Expression expression = applyexp.getArgs()[0];
        Language language = not.language;
        if (target instanceof ConditionalTarget)
        {
            ConditionalTarget conditionaltarget = (ConditionalTarget)target;
            ConditionalTarget conditionaltarget1 = new ConditionalTarget(conditionaltarget.ifFalse, conditionaltarget.ifTrue, language);
            boolean flag;
            if (!conditionaltarget.trueBranchComesFirst)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            conditionaltarget1.trueBranchComesFirst = flag;
            expression.compile(compilation, conditionaltarget1);
            return;
        }
        CodeAttr codeattr = compilation.getCode();
        Type type = target.getType();
        if ((target instanceof StackTarget) && type.getSignature().charAt(0) == 'Z')
        {
            expression.compile(compilation, target);
            codeattr.emitNot(target.getType());
            return;
        } else
        {
            QuoteExp quoteexp = QuoteExp.getInstance(language.booleanObject(true));
            IfExp.compile(expression, QuoteExp.getInstance(language.booleanObject(false)), quoteexp, compilation, target);
            return;
        }
    }

}
