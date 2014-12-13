// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.KeyPair;
import gnu.mapping.Symbol;
import gnu.text.SourceLocator;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

// Referenced classes of package gnu.expr:
//            ExpExpVisitor, LambdaExp, Expression, Compilation, 
//            Language, ModuleExp, Declaration, ScopeExp, 
//            ClassExp, ReferenceExp, FluidLetExp, ApplyExp, 
//            QuoteExp, PrimProcedure, LetExp, BeginExp, 
//            SetExp, ThisExp

public class FindCapturedVars extends ExpExpVisitor
{

    int backJumpPossible;
    ModuleExp currentModule;
    Hashtable unknownDecls;

    public FindCapturedVars()
    {
        backJumpPossible = 0;
        unknownDecls = null;
        currentModule = null;
    }

    static Expression checkInlineable(LambdaExp lambdaexp, Set set)
    {
        Expression expression;
label0:
        {
            if (lambdaexp.returnContinuation == LambdaExp.unknownContinuation)
            {
                return lambdaexp.returnContinuation;
            }
            if (set.contains(lambdaexp))
            {
                return lambdaexp.returnContinuation;
            }
            if (lambdaexp.getCanRead() || lambdaexp.isClassMethod() || lambdaexp.min_args != lambdaexp.max_args)
            {
                lambdaexp.returnContinuation = LambdaExp.unknownContinuation;
                return LambdaExp.unknownContinuation;
            }
            set.add(lambdaexp);
            expression = lambdaexp.returnContinuation;
            if (lambdaexp.tailCallers == null)
            {
                break label0;
            }
            Expression expression1;
label1:
            do
            {
                for (Iterator iterator = lambdaexp.tailCallers.iterator(); iterator.hasNext();)
                {
                    LambdaExp lambdaexp1 = (LambdaExp)iterator.next();
                    expression1 = checkInlineable(lambdaexp1, set);
                    if (expression1 == LambdaExp.unknownContinuation)
                    {
                        if (expression == null || expression == lambdaexp1.body)
                        {
                            expression = lambdaexp1.body;
                            lambdaexp.inlineHome = lambdaexp1;
                        } else
                        {
                            lambdaexp.returnContinuation = LambdaExp.unknownContinuation;
                            return expression1;
                        }
                    } else
                    {
                        if (expression != null)
                        {
                            continue label1;
                        }
                        expression = expression1;
                        if (lambdaexp.inlineHome == null)
                        {
                            if (!lambdaexp.nestedIn(lambdaexp1))
                            {
                                lambdaexp1 = lambdaexp1.inlineHome;
                            }
                            lambdaexp.inlineHome = lambdaexp1;
                        }
                    }
                }

                break label0;
            } while ((expression1 == null || expression == expression1) && !lambdaexp.getFlag(32));
            lambdaexp.returnContinuation = LambdaExp.unknownContinuation;
            return LambdaExp.unknownContinuation;
        }
        return expression;
    }

    public static void findCapturedVars(Expression expression, Compilation compilation)
    {
        FindCapturedVars findcapturedvars = new FindCapturedVars();
        findcapturedvars.setContext(compilation);
        expression.visit(findcapturedvars, null);
    }

    Declaration allocUnboundDecl(Object obj, boolean flag)
    {
        Object obj1 = obj;
        Declaration declaration;
        if (flag && (obj instanceof Symbol))
        {
            if (!getCompilation().getLanguage().hasSeparateFunctionNamespace())
            {
                flag = false;
            } else
            {
                obj1 = new KeyPair((Symbol)obj, EnvironmentKey.FUNCTION);
            }
        }
        if (unknownDecls == null)
        {
            unknownDecls = new Hashtable(100);
            declaration = null;
        } else
        {
            declaration = (Declaration)unknownDecls.get(obj1);
        }
        if (declaration == null)
        {
            declaration = currentModule.addDeclaration(obj);
            declaration.setSimple(false);
            declaration.setPrivate(true);
            if (flag)
            {
                declaration.setProcedureDecl(true);
            }
            if (currentModule.isStatic())
            {
                declaration.setFlag(2048L);
            }
            declaration.setCanRead(true);
            declaration.setCanWrite(true);
            declaration.setFlag(0x50000L);
            declaration.setIndirectBinding(true);
            unknownDecls.put(obj1, declaration);
        }
        return declaration;
    }

    public void capture(Declaration declaration)
    {
_L2:
        return;
        if (!declaration.getCanRead() && !declaration.getCanCall() || declaration.field != null && declaration.field.getStaticFlag() || comp.immediate && declaration.hasConstantValue()) goto _L2; else goto _L1
_L1:
        LambdaExp lambdaexp;
        LambdaExp lambdaexp1;
        lambdaexp = getCurrentLambda();
        ScopeExp scopeexp = declaration.getContext();
        if (scopeexp == null)
        {
            throw new Error((new StringBuilder()).append("null context for ").append(declaration).append(" curL:").append(lambdaexp).toString());
        }
        lambdaexp1 = scopeexp.currentLambda();
        LambdaExp lambdaexp2 = null;
        for (LambdaExp lambdaexp3 = null; lambdaexp != lambdaexp1 && lambdaexp.getInlineOnly(); lambdaexp3 = lambdaexp3.nextSibling)
        {
            LambdaExp lambdaexp9 = lambdaexp.outerLambda();
            if (lambdaexp9 != lambdaexp2)
            {
                lambdaexp3 = lambdaexp9.firstChild;
                lambdaexp2 = lambdaexp9;
            }
            if (lambdaexp3 == null || lambdaexp.inlineHome == null)
            {
                lambdaexp.setCanCall(false);
                return;
            }
            lambdaexp = lambdaexp.getCaller();
        }

        if (!comp.usingCPStyle()) goto _L4; else goto _L3
_L3:
        if (lambdaexp instanceof ModuleExp) goto _L2; else goto _L5
_L5:
        Expression expression = declaration.getValue();
        if (expression != null && (expression instanceof LambdaExp)) goto _L7; else goto _L6
_L6:
        LambdaExp lambdaexp4 = null;
_L11:
        if (!declaration.getFlag(0x10000L)) goto _L9; else goto _L8
_L8:
        LambdaExp lambdaexp8 = lambdaexp;
_L13:
        if (lambdaexp8 != lambdaexp1)
        {
label0:
            {
                if (lambdaexp8.nameDecl == null || !lambdaexp8.nameDecl.getFlag(2048L))
                {
                    break label0;
                }
                declaration.setFlag(2048L);
            }
        }
_L9:
        if (declaration.base != null)
        {
            declaration.base.setCanRead(true);
            capture(declaration.base);
            return;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (lambdaexp == lambdaexp1)
        {
            return;
        }
          goto _L5
_L7:
        lambdaexp4 = (LambdaExp)expression;
        if (lambdaexp4.getInlineOnly()) goto _L2; else goto _L10
_L10:
        if (!lambdaexp4.isHandlingTailCalls())
        {
            continue; /* Loop/switch isn't completed */
        }
        lambdaexp4 = null;
          goto _L11
        if (lambdaexp4 != lambdaexp || declaration.getCanRead()) goto _L11; else goto _L12
_L12:
        return;
        lambdaexp8 = lambdaexp8.outerLambda();
          goto _L13
        if (!declaration.getCanRead() && !declaration.getCanCall() && lambdaexp4 != null) goto _L2; else goto _L14
_L14:
        if (declaration.isStatic()) goto _L16; else goto _L15
_L15:
        LambdaExp lambdaexp6;
        LambdaExp lambdaexp5 = lambdaexp;
        if (!declaration.isFluid())
        {
            lambdaexp5.setImportsLexVars();
        }
        lambdaexp6 = lambdaexp5.outerLambda();
_L20:
        if (lambdaexp6 == lambdaexp1 || lambdaexp6 == null) goto _L16; else goto _L17
_L17:
        LambdaExp lambdaexp7 = lambdaexp6;
        if (declaration.getCanRead() || lambdaexp4 != lambdaexp6) goto _L18; else goto _L16
_L16:
        if (lambdaexp1 == null)
        {
            System.err.println((new StringBuilder()).append("null declLambda for ").append(declaration).append(" curL:").append(lambdaexp).toString());
            for (ScopeExp scopeexp1 = declaration.context; scopeexp1 != null; scopeexp1 = scopeexp1.outer)
            {
                System.err.println((new StringBuilder()).append("- context:").append(scopeexp1).toString());
            }

        }
        break; /* Loop/switch isn't completed */
_L18:
        Declaration declaration1 = lambdaexp7.nameDecl;
        if (declaration1 != null && declaration1.getFlag(2048L))
        {
            comp.error('e', (new StringBuilder()).append("static ").append(lambdaexp7.getName()).append(" references non-static ").append(declaration.getName()).toString());
        }
        if ((lambdaexp7 instanceof ClassExp) && lambdaexp7.getName() != null && ((ClassExp)lambdaexp7).isSimple())
        {
            comp.error('w', lambdaexp7.nameDecl, "simple class ", (new StringBuilder()).append(" requiring lexical link (because of reference to ").append(declaration.getName()).append(") - use define-class instead").toString());
        }
        lambdaexp7.setNeedsStaticLink();
        lambdaexp6 = lambdaexp7.outerLambda();
        if (true) goto _L20; else goto _L19
_L19:
        lambdaexp1.capture(declaration);
        return;
    }

    void capture(Declaration declaration, Declaration declaration1)
    {
        if (declaration1.isAlias() && (declaration1.value instanceof ReferenceExp))
        {
            ReferenceExp referenceexp = (ReferenceExp)declaration1.value;
            Declaration declaration2 = referenceexp.binding;
            if (declaration2 != null && (declaration == null || !declaration2.needsContext()))
            {
                capture(referenceexp.contextDecl(), declaration2);
                return;
            }
        }
        for (; declaration1.isFluid() && (declaration1.context instanceof FluidLetExp); declaration1 = declaration1.base) { }
        if (declaration != null && declaration1.needsContext())
        {
            capture(declaration);
            return;
        } else
        {
            capture(declaration1);
            return;
        }
    }

    void maybeWarnNoDeclarationSeen(Object obj, Compilation compilation, SourceLocator sourcelocator)
    {
        if (compilation.warnUndefinedVariable())
        {
            compilation.error('w', (new StringBuilder()).append("no declaration seen for ").append(obj).toString(), sourcelocator);
        }
    }

    protected Expression visitApplyExp(ApplyExp applyexp, Void void1)
    {
        int i = backJumpPossible;
        if (!(applyexp.func instanceof ReferenceExp) || Compilation.defaultCallConvention > 1) goto _L2; else goto _L1
_L1:
        boolean flag1;
        boolean flag2;
        Declaration declaration1 = Declaration.followAliases(((ReferenceExp)applyexp.func).binding);
        flag1 = false;
        flag2 = false;
        if (declaration1 != null)
        {
            boolean flag9 = declaration1.context instanceof ModuleExp;
            flag1 = false;
            flag2 = false;
            if (flag9)
            {
                boolean flag10 = declaration1.isPublic();
                flag1 = false;
                flag2 = false;
                if (!flag10)
                {
                    boolean flag11 = declaration1.getFlag(4096L);
                    flag1 = false;
                    flag2 = false;
                    if (!flag11)
                    {
                        Expression expression2 = declaration1.getValue();
                        boolean flag12 = expression2 instanceof LambdaExp;
                        flag1 = false;
                        flag2 = false;
                        if (flag12)
                        {
                            boolean flag13 = ((LambdaExp)expression2).getNeedsClosureEnv();
                            flag1 = false;
                            flag2 = false;
                            if (!flag13)
                            {
                                flag2 = true;
                            }
                        }
                    }
                }
            }
        }
_L4:
        if (!flag2)
        {
            applyexp.func = (Expression)applyexp.func.visit(this, void1);
        }
        if (exitValue == null && !flag1)
        {
            applyexp.args = visitExps(applyexp.args, void1);
        }
        if (backJumpPossible > i)
        {
            applyexp.setFlag(8);
        }
        return applyexp;
_L2:
        boolean flag = applyexp.func instanceof QuoteExp;
        flag1 = false;
        flag2 = false;
        if (flag)
        {
            int j = applyexp.getArgCount();
            flag1 = false;
            flag2 = false;
            if (j > 0)
            {
                Object obj = ((QuoteExp)applyexp.func).getValue();
                Expression expression = applyexp.getArg(0);
                boolean flag3 = obj instanceof PrimProcedure;
                flag1 = false;
                flag2 = false;
                if (flag3)
                {
                    boolean flag4 = expression instanceof ReferenceExp;
                    flag1 = false;
                    flag2 = false;
                    if (flag4)
                    {
                        (PrimProcedure)obj;
                        Declaration declaration = Declaration.followAliases(((ReferenceExp)expression).binding);
                        flag1 = false;
                        flag2 = false;
                        if (declaration != null)
                        {
                            boolean flag5 = declaration.context instanceof ModuleExp;
                            flag1 = false;
                            flag2 = false;
                            if (flag5)
                            {
                                boolean flag6 = declaration.getFlag(4096L);
                                flag1 = false;
                                flag2 = false;
                                if (!flag6)
                                {
                                    Expression expression1 = declaration.getValue();
                                    boolean flag7 = expression1 instanceof ClassExp;
                                    flag1 = false;
                                    flag2 = false;
                                    if (flag7)
                                    {
                                        Expression aexpression[] = applyexp.getArgs();
                                        boolean flag8 = ((LambdaExp)expression1).getNeedsClosureEnv();
                                        flag1 = false;
                                        flag2 = false;
                                        if (!flag8)
                                        {
                                            applyexp.nextCall = declaration.firstCall;
                                            declaration.firstCall = applyexp;
                                            for (int k = 1; k < aexpression.length; k++)
                                            {
                                                aexpression[k].visit(this, void1);
                                            }

                                            flag1 = true;
                                            flag2 = flag1;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected volatile Object visitApplyExp(ApplyExp applyexp, Object obj)
    {
        return visitApplyExp(applyexp, (Void)obj);
    }

    protected Expression visitClassExp(ClassExp classexp, Void void1)
    {
        Expression expression = (Expression)super.visitClassExp(classexp, void1);
        if (classexp.explicitInit || classexp.instanceType.isInterface()) goto _L2; else goto _L1
_L1:
        Compilation.getConstructor(classexp.instanceType, classexp);
_L4:
        if (classexp.isSimple() && classexp.getNeedsClosureEnv() && classexp.nameDecl != null && classexp.nameDecl.getType() == Compilation.typeClass)
        {
            classexp.nameDecl.setType(Compilation.typeClassType);
        }
        return expression;
_L2:
        if (classexp.getNeedsClosureEnv())
        {
            LambdaExp lambdaexp = classexp.firstChild;
            while (lambdaexp != null) 
            {
                if ("*init*".equals(lambdaexp.getName()))
                {
                    lambdaexp.setNeedsStaticLink(true);
                }
                lambdaexp = lambdaexp.nextSibling;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected volatile Object visitClassExp(ClassExp classexp, Object obj)
    {
        return visitClassExp(classexp, (Void)obj);
    }

    public volatile void visitDefaultArgs(LambdaExp lambdaexp, Object obj)
    {
        visitDefaultArgs(lambdaexp, (Void)obj);
    }

    public void visitDefaultArgs(LambdaExp lambdaexp, Void void1)
    {
        if (lambdaexp.defaultArgs != null)
        {
            super.visitDefaultArgs(lambdaexp, void1);
            Declaration declaration = lambdaexp.firstDecl();
            while (declaration != null) 
            {
                if (!declaration.isSimple())
                {
                    lambdaexp.setFlag(true, 512);
                    return;
                }
                declaration = declaration.nextDecl();
            }
        }
    }

    protected Expression visitFluidLetExp(FluidLetExp fluidletexp, Void void1)
    {
        for (Declaration declaration = fluidletexp.firstDecl(); declaration != null; declaration = declaration.nextDecl())
        {
            if (declaration.base == null)
            {
                Object obj = declaration.getSymbol();
                Declaration declaration1 = allocUnboundDecl(obj, false);
                maybeWarnNoDeclarationSeen(obj, comp, fluidletexp);
                capture(declaration1);
                declaration.base = declaration1;
            }
        }

        return (Expression)super.visitLetExp(fluidletexp, void1);
    }

    protected volatile Object visitFluidLetExp(FluidLetExp fluidletexp, Object obj)
    {
        return visitFluidLetExp(fluidletexp, (Void)obj);
    }

    protected Expression visitLambdaExp(LambdaExp lambdaexp, Void void1)
    {
        if (checkInlineable(lambdaexp, new LinkedHashSet()) != LambdaExp.unknownContinuation && (!(lambdaexp.outer instanceof ModuleExp) || lambdaexp.nameDecl == null))
        {
            lambdaexp.setInlineOnly(true);
            backJumpPossible = 1 + backJumpPossible;
        }
        return (Expression)super.visitLambdaExp(lambdaexp, void1);
    }

    protected volatile Object visitLambdaExp(LambdaExp lambdaexp, Object obj)
    {
        return visitLambdaExp(lambdaexp, (Void)obj);
    }

    protected Expression visitLetExp(LetExp letexp, Void void1)
    {
        if (letexp.body instanceof BeginExp)
        {
            Expression aexpression[] = letexp.inits;
            int i = aexpression.length;
            Expression aexpression1[] = ((BeginExp)letexp.body).exps;
            int j = 0;
            Declaration declaration = letexp.firstDecl();
            for (int k = 0; k < aexpression1.length && j < i; k++)
            {
                Expression expression = aexpression1[k];
                if (!(expression instanceof SetExp))
                {
                    continue;
                }
                SetExp setexp = (SetExp)expression;
                if (setexp.binding != declaration || aexpression[j] != QuoteExp.nullExp || !setexp.isDefining())
                {
                    continue;
                }
                Expression expression1 = setexp.new_value;
                if (((expression1 instanceof QuoteExp) || (expression1 instanceof LambdaExp)) && declaration.getValue() == expression1)
                {
                    aexpression[j] = expression1;
                    aexpression1[k] = QuoteExp.voidExp;
                }
                j++;
                declaration = declaration.nextDecl();
            }

        }
        return (Expression)super.visitLetExp(letexp, void1);
    }

    protected volatile Object visitLetExp(LetExp letexp, Object obj)
    {
        return visitLetExp(letexp, (Void)obj);
    }

    protected Expression visitModuleExp(ModuleExp moduleexp, Void void1)
    {
        ModuleExp moduleexp1;
        Hashtable hashtable;
        moduleexp1 = currentModule;
        hashtable = unknownDecls;
        currentModule = moduleexp;
        unknownDecls = null;
        Expression expression = visitLambdaExp(moduleexp, void1);
        currentModule = moduleexp1;
        unknownDecls = hashtable;
        return expression;
        Exception exception;
        exception;
        currentModule = moduleexp1;
        unknownDecls = hashtable;
        throw exception;
    }

    protected volatile Object visitModuleExp(ModuleExp moduleexp, Object obj)
    {
        return visitModuleExp(moduleexp, (Void)obj);
    }

    protected Expression visitReferenceExp(ReferenceExp referenceexp, Void void1)
    {
        Declaration declaration = referenceexp.getBinding();
        if (declaration == null)
        {
            declaration = allocUnboundDecl(referenceexp.getSymbol(), referenceexp.isProcedureName());
            referenceexp.setBinding(declaration);
        }
        if (declaration.getFlag(0x10000L) && comp.resolve(referenceexp.getSymbol(), referenceexp.isProcedureName()) == null)
        {
            maybeWarnNoDeclarationSeen(referenceexp.getSymbol(), comp, referenceexp);
        }
        capture(referenceexp.contextDecl(), declaration);
        return referenceexp;
    }

    protected volatile Object visitReferenceExp(ReferenceExp referenceexp, Object obj)
    {
        return visitReferenceExp(referenceexp, (Void)obj);
    }

    protected Expression visitSetExp(SetExp setexp, Void void1)
    {
        Declaration declaration = setexp.binding;
        if (declaration == null)
        {
            declaration = allocUnboundDecl(setexp.getSymbol(), setexp.isFuncDef());
            setexp.binding = declaration;
        }
        if (!declaration.ignorable())
        {
            if (!setexp.isDefining())
            {
                declaration = Declaration.followAliases(declaration);
            }
            capture(setexp.contextDecl(), declaration);
        }
        return (Expression)super.visitSetExp(setexp, void1);
    }

    protected volatile Object visitSetExp(SetExp setexp, Object obj)
    {
        return visitSetExp(setexp, (Void)obj);
    }

    protected Expression visitThisExp(ThisExp thisexp, Void void1)
    {
        if (thisexp.isForContext())
        {
            getCurrentLambda().setImportsLexVars();
            return thisexp;
        } else
        {
            return visitReferenceExp(thisexp, void1);
        }
    }

    protected volatile Object visitThisExp(ThisExp thisexp, Object obj)
    {
        return visitThisExp(thisexp, (Void)obj);
    }
}
