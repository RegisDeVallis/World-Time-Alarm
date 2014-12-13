// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.LazyPropertyKey;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.text.SourceMessages;

// Referenced classes of package gnu.expr:
//            Expression, QuoteExp, PrimProcedure, Inlineable, 
//            LambdaExp, IgnoreTarget, ReferenceExp, Declaration, 
//            Compilation, Target, ConsumerTarget, StackTarget, 
//            SetExp, InlineCalls, ExpVisitor

public class ApplyExp extends Expression
{

    public static final int INLINE_IF_CONSTANT = 4;
    public static final int MAY_CONTAIN_BACK_JUMP = 8;
    public static final int TAILCALL = 2;
    Expression args[];
    LambdaExp context;
    Expression func;
    public ApplyExp nextCall;
    protected Type type;

    public ApplyExp(Method method, Expression aexpression[])
    {
        func = new QuoteExp(new PrimProcedure(method));
        args = aexpression;
    }

    public ApplyExp(Expression expression, Expression aexpression[])
    {
        func = expression;
        args = aexpression;
    }

    public ApplyExp(Procedure procedure, Expression aexpression[])
    {
        func = new QuoteExp(procedure);
        args = aexpression;
    }

    public static Inlineable asInlineable(Procedure procedure)
    {
        if (procedure instanceof Inlineable)
        {
            return (Inlineable)procedure;
        } else
        {
            return (Inlineable)Procedure.compilerKey.get(procedure);
        }
    }

    public static void compile(ApplyExp applyexp, Compilation compilation, Target target)
    {
        compile(applyexp, compilation, target, false);
    }

    static void compile(ApplyExp applyexp, Compilation compilation, Target target, boolean flag)
    {
        int i;
        Expression expression;
        Declaration declaration;
        i = applyexp.args.length;
        expression = applyexp.func;
        declaration = null;
        if (!(expression instanceof LambdaExp)) goto _L2; else goto _L1
_L1:
        LambdaExp lambdaexp = (LambdaExp)expression;
        if (lambdaexp.getName() != null) goto _L4; else goto _L3
_L3:
        Object obj = null;
_L11:
        if (!flag || !(obj instanceof Procedure)) goto _L6; else goto _L5
_L5:
        Procedure procedure = (Procedure)obj;
        if (!(target instanceof IgnoreTarget) || !procedure.isSideEffectFree()) goto _L8; else goto _L7
_L7:
        for (int i1 = 0; i1 < i; i1++)
        {
            applyexp.args[i1].compile(compilation, target);
        }

          goto _L9
_L2:
        if (expression instanceof ReferenceExp)
        {
            ReferenceExp referenceexp = (ReferenceExp)expression;
            declaration = referenceexp.contextDecl();
            Declaration declaration1 = referenceexp.binding;
            do
            {
                ReferenceExp referenceexp1;
label0:
                {
                    if (declaration1 != null && declaration1.isAlias() && (declaration1.value instanceof ReferenceExp))
                    {
                        referenceexp1 = (ReferenceExp)declaration1.value;
                        if (declaration == null && !declaration1.needsContext() && referenceexp1.binding != null)
                        {
                            break label0;
                        }
                    }
                    boolean flag7 = declaration1.getFlag(0x10000L);
                    lambdaexp = null;
                    Object obj1 = null;
                    if (!flag7)
                    {
                        Expression expression1 = declaration1.getValue();
                        declaration1.getName();
                        lambdaexp = null;
                        if (expression1 != null)
                        {
                            boolean flag9 = expression1 instanceof LambdaExp;
                            lambdaexp = null;
                            if (flag9)
                            {
                                lambdaexp = (LambdaExp)expression1;
                            }
                        }
                        obj1 = null;
                        if (expression1 != null)
                        {
                            boolean flag8 = expression1 instanceof QuoteExp;
                            obj1 = null;
                            if (flag8)
                            {
                                obj1 = ((QuoteExp)expression1).getValue();
                            }
                        }
                    }
                    obj = obj1;
                    continue; /* Loop/switch isn't completed */
                }
                declaration1 = referenceexp1.binding;
                declaration = referenceexp1.contextDecl();
            } while (true);
        }
        boolean flag1 = expression instanceof QuoteExp;
        lambdaexp = null;
        if (flag1)
        {
            obj = ((QuoteExp)expression).getValue();
            lambdaexp = null;
            declaration = null;
            continue; /* Loop/switch isn't completed */
        }
          goto _L4
_L8:
        boolean flag6;
        try
        {
            flag6 = inlineCompile(procedure, applyexp, compilation, target);
        }
        catch (Throwable throwable)
        {
            compilation.getMessages().error('e', (new StringBuilder()).append("caught exception in inline-compiler for ").append(obj).append(" - ").append(throwable).toString(), throwable);
            return;
        }
        if (!flag6) goto _L6; else goto _L9
_L9:
        return;
_L6:
        CodeAttr codeattr;
        boolean flag2;
        boolean flag3;
        int ai[];
        Method method;
label1:
        {
            gnu.bytecode.PrimType primtype;
label2:
            {
                codeattr = compilation.getCode();
                if (lambdaexp == null)
                {
                    break label1;
                }
                if (lambdaexp.max_args >= 0 && i > lambdaexp.max_args || i < lambdaexp.min_args)
                {
                    throw new Error((new StringBuilder()).append("internal error - wrong number of parameters for ").append(lambdaexp).toString());
                }
                int l = lambdaexp.getCallConvention();
                if (!compilation.inlineOk(lambdaexp) || l > 2 && (l != 3 || applyexp.isTailCall()))
                {
                    break label1;
                }
                Method method1 = lambdaexp.getMethod(i);
                if (method1 == null)
                {
                    break label1;
                }
                PrimProcedure primprocedure = new PrimProcedure(method1, lambdaexp);
                boolean flag4 = method1.getStaticFlag();
                boolean flag5;
                if (flag4)
                {
                    Variable variable1 = lambdaexp.declareClosureEnv();
                    flag5 = false;
                    if (variable1 == null)
                    {
                        break label2;
                    }
                }
                flag5 = false;
                if (flag4)
                {
                    flag5 = true;
                }
                if (compilation.curLambda == lambdaexp)
                {
                    Variable variable;
                    if (lambdaexp.closureEnv != null)
                    {
                        variable = lambdaexp.closureEnv;
                    } else
                    {
                        variable = lambdaexp.thisVariable;
                    }
                    codeattr.emitLoad(variable);
                } else
                if (declaration != null)
                {
                    Target target1 = Target.pushObject;
                    declaration.load(null, 0, compilation, target1);
                } else
                {
                    lambdaexp.getOwningLambda().loadHeapFrame(compilation);
                }
            }
            if (flag5)
            {
                primtype = Type.voidType;
            } else
            {
                primtype = null;
            }
            primprocedure.compile(primtype, applyexp, compilation, target);
            return;
        }
        if (applyexp.isTailCall() && lambdaexp != null && lambdaexp == compilation.curLambda)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (lambdaexp != null && lambdaexp.getInlineOnly() && !flag2 && lambdaexp.min_args == i)
        {
            pushArgs(lambdaexp, applyexp.args, null, compilation);
            if (lambdaexp.getFlag(128))
            {
                popParams(codeattr, lambdaexp, null, false);
                codeattr.emitTailCall(false, lambdaexp.getVarScope());
                return;
            } else
            {
                lambdaexp.flags = 0x80 | lambdaexp.flags;
                LambdaExp lambdaexp1 = compilation.curLambda;
                compilation.curLambda = lambdaexp;
                lambdaexp.allocChildClasses(compilation);
                lambdaexp.allocParameters(compilation);
                popParams(codeattr, lambdaexp, null, false);
                lambdaexp.enterFunction(compilation);
                lambdaexp.body.compileWithPosition(compilation, target);
                lambdaexp.compileEnd(compilation);
                lambdaexp.generateApplyMethods(compilation);
                codeattr.popScope();
                compilation.curLambda = lambdaexp1;
                return;
            }
        }
        if (compilation.curLambda.isHandlingTailCalls() && (applyexp.isTailCall() || (target instanceof ConsumerTarget)) && !compilation.curLambda.getInlineOnly())
        {
            ClassType classtype = Compilation.typeCallContext;
            expression.compile(compilation, new StackTarget(Compilation.typeProcedure));
            if (i <= 4)
            {
                for (int k = 0; k < i; k++)
                {
                    applyexp.args[k].compileWithPosition(compilation, Target.pushObject);
                }

                compilation.loadCallContext();
                codeattr.emitInvoke(Compilation.typeProcedure.getDeclaredMethod((new StringBuilder()).append("check").append(i).toString(), i + 1));
            } else
            {
                compileToArray(applyexp.args, compilation);
                compilation.loadCallContext();
                codeattr.emitInvoke(Compilation.typeProcedure.getDeclaredMethod("checkN", 2));
            }
            if (applyexp.isTailCall())
            {
                codeattr.emitReturn();
                return;
            }
            if (((ConsumerTarget)target).isContextTarget())
            {
                compilation.loadCallContext();
                codeattr.emitInvoke(classtype.getDeclaredMethod("runUntilDone", 0));
                return;
            } else
            {
                compilation.loadCallContext();
                codeattr.emitLoad(((ConsumerTarget)target).getConsumerVariable());
                codeattr.emitInvoke(classtype.getDeclaredMethod("runUntilValue", 1));
                return;
            }
        }
        if (!flag2)
        {
            expression.compile(compilation, new StackTarget(Compilation.typeProcedure));
        }
        if (flag2)
        {
            if (lambdaexp.min_args != lambdaexp.max_args)
            {
                flag3 = true;
            } else
            {
                flag3 = false;
            }
        } else
        if (i > 4)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        ai = null;
        if (flag3)
        {
            compileToArray(applyexp.args, compilation);
            method = Compilation.applyNmethod;
            break MISSING_BLOCK_LABEL_1159;
        } else
        {
            if (flag2)
            {
                ai = new int[applyexp.args.length];
                pushArgs(lambdaexp, applyexp.args, ai, compilation);
                method = null;
                continue;
            }
            int j = 0;
            do
            {
label3:
                {
                    if (j < i)
                    {
                        applyexp.args[j].compileWithPosition(compilation, Target.pushObject);
                        if (codeattr.reachableHere())
                        {
                            break label3;
                        }
                    }
                    method = Compilation.applymethods[i];
                    ai = null;
                    continue;
                }
                j++;
            } while (true);
        }
        do
        {
            if (!codeattr.reachableHere())
            {
                compilation.error('e', "unreachable code");
                return;
            }
            if (flag2)
            {
                popParams(codeattr, lambdaexp, ai, flag3);
                codeattr.emitTailCall(false, lambdaexp.getVarScope());
                return;
            } else
            {
                codeattr.emitInvokeVirtual(method);
                target.compileFromStack(compilation, Type.pointer_type);
                return;
            }
        } while (true);
_L4:
        declaration = null;
        obj = null;
        if (true) goto _L11; else goto _L10
_L10:
    }

    public static void compileToArray(Expression aexpression[], Compilation compilation)
    {
        CodeAttr codeattr = compilation.getCode();
        if (aexpression.length == 0)
        {
            codeattr.emitGetStatic(Compilation.noArgsField);
        } else
        {
            codeattr.emitPushInt(aexpression.length);
            codeattr.emitNewArray(Type.pointer_type);
            int i = 0;
            while (i < aexpression.length) 
            {
                Expression expression = aexpression[i];
                if (compilation.usingCPStyle() && !(expression instanceof QuoteExp) && !(expression instanceof ReferenceExp))
                {
                    expression.compileWithPosition(compilation, Target.pushObject);
                    codeattr.emitSwap();
                    codeattr.emitDup(1, 1);
                    codeattr.emitSwap();
                    codeattr.emitPushInt(i);
                    codeattr.emitSwap();
                } else
                {
                    codeattr.emitDup(Compilation.objArrayType);
                    codeattr.emitPushInt(i);
                    expression.compileWithPosition(compilation, Target.pushObject);
                }
                codeattr.emitArrayStore(Type.pointer_type);
                i++;
            }
        }
    }

    static Expression derefFunc(Expression expression)
    {
        if (expression instanceof ReferenceExp)
        {
            Declaration declaration = Declaration.followAliases(((ReferenceExp)expression).binding);
            if (declaration != null && !declaration.getFlag(0x10000L))
            {
                expression = declaration.getValue();
            }
        }
        return expression;
    }

    static boolean inlineCompile(Procedure procedure, ApplyExp applyexp, Compilation compilation, Target target)
        throws Throwable
    {
        Inlineable inlineable = asInlineable(procedure);
        if (inlineable == null)
        {
            return false;
        } else
        {
            inlineable.compile(applyexp, compilation, target);
            return true;
        }
    }

    private static void popParams(CodeAttr codeattr, int i, int j, int ai[], Declaration declaration, Variable variable)
    {
label0:
        {
            if (j > 0)
            {
                int k = j - 1;
                int l = i + 1;
                Declaration declaration1 = declaration.nextDecl();
                Variable variable1;
                if (declaration.getVariable() == null)
                {
                    variable1 = variable;
                } else
                {
                    variable1 = variable.nextVar();
                }
                popParams(codeattr, l, k, ai, declaration1, variable1);
                if (!declaration.ignorable())
                {
                    if (ai == null || ai[i] == 0x10000)
                    {
                        break label0;
                    }
                    codeattr.emitInc(variable, (short)ai[i]);
                }
            }
            return;
        }
        codeattr.emitStore(variable);
    }

    private static void popParams(CodeAttr codeattr, LambdaExp lambdaexp, int ai[], boolean flag)
    {
        Variable variable = lambdaexp.getVarScope().firstVar();
        Declaration declaration = lambdaexp.firstDecl();
        if (variable != null && variable.getName() == "this")
        {
            variable = variable.nextVar();
        }
        if (variable != null && variable.getName() == "$ctx")
        {
            variable = variable.nextVar();
        }
        if (variable != null && variable.getName() == "argsArray")
        {
            if (flag)
            {
                popParams(codeattr, 0, 1, null, declaration, variable);
                return;
            }
            variable = variable.nextVar();
        }
        popParams(codeattr, 0, lambdaexp.min_args, ai, declaration, variable);
    }

    private static void pushArgs(LambdaExp lambdaexp, Expression aexpression[], int ai[], Compilation compilation)
    {
        Declaration declaration;
        int i;
        int j;
        declaration = lambdaexp.firstDecl();
        i = aexpression.length;
        j = 0;
_L2:
        Expression expression;
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_100;
        }
        expression = aexpression[j];
        if (!declaration.ignorable())
        {
            break; /* Loop/switch isn't completed */
        }
        expression.compile(compilation, Target.Ignore);
_L5:
        declaration = declaration.nextDecl();
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        if (ai == null) goto _L4; else goto _L3
_L3:
        int k;
        k = SetExp.canUseInc(expression, declaration);
        ai[j] = k;
        if (k != 0x10000) goto _L5; else goto _L4
_L4:
        expression.compileWithPosition(compilation, StackTarget.getInstance(declaration.getType()));
          goto _L5
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        Object obj = func.eval(callcontext);
        int i = args.length;
        Object aobj[] = new Object[i];
        for (int j = 0; j < i; j++)
        {
            aobj[j] = args[j].eval(callcontext);
        }

        ((Procedure)obj).checkN(aobj, callcontext);
    }

    public void compile(Compilation compilation, Target target)
    {
        compile(this, compilation, target, true);
    }

    public Expression deepCopy(IdentityHashTable identityhashtable)
    {
        Expression expression = deepCopy(func, identityhashtable);
        Expression aexpression[] = deepCopy(args, identityhashtable);
        if (expression == null && func != null || aexpression == null && args != null)
        {
            return null;
        } else
        {
            ApplyExp applyexp = new ApplyExp(expression, aexpression);
            applyexp.flags = getFlags();
            return applyexp;
        }
    }

    public Expression getArg(int i)
    {
        return args[i];
    }

    public final int getArgCount()
    {
        return args.length;
    }

    public final Expression[] getArgs()
    {
        return args;
    }

    public final Expression getFunction()
    {
        return func;
    }

    public final Object getFunctionValue()
    {
        if (func instanceof QuoteExp)
        {
            return ((QuoteExp)func).getValue();
        } else
        {
            return null;
        }
    }

    public final Type getType()
    {
        Expression expression;
        if (type != null)
        {
            return type;
        }
        expression = derefFunc(func);
        type = Type.objectType;
        if (!(expression instanceof QuoteExp)) goto _L2; else goto _L1
_L1:
        Object obj = ((QuoteExp)expression).getValue();
        if (obj instanceof Procedure)
        {
            type = ((Procedure)obj).getReturnType(args);
        }
_L4:
        return type;
_L2:
        if (expression instanceof LambdaExp)
        {
            type = ((LambdaExp)expression).getReturnType();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final Type getTypeRaw()
    {
        return type;
    }

    public final Expression inlineIfConstant(Procedure procedure, InlineCalls inlinecalls)
    {
        return inlineIfConstant(procedure, inlinecalls.getMessages());
    }

    public final Expression inlineIfConstant(Procedure procedure, SourceMessages sourcemessages)
    {
        Object aobj[];
        int j;
        int i = args.length;
        aobj = new Object[i];
        j = i;
_L10:
        if (--j < 0) goto _L2; else goto _L1
_L1:
        Expression expression = args[j];
        if (!(expression instanceof ReferenceExp)) goto _L4; else goto _L3
_L3:
        Declaration declaration = ((ReferenceExp)expression).getBinding();
        if (declaration == null) goto _L4; else goto _L5
_L5:
        expression = declaration.getValue();
        if (expression != QuoteExp.undefined_exp) goto _L4; else goto _L6
_L6:
        return this;
_L4:
        if (!(expression instanceof QuoteExp)) goto _L6; else goto _L7
_L7:
        aobj[j] = ((QuoteExp)expression).getValue();
        continue; /* Loop/switch isn't completed */
_L2:
        QuoteExp quoteexp = new QuoteExp(procedure.applyN(aobj), type);
        return quoteexp;
        Throwable throwable;
        throwable;
        if (sourcemessages == null) goto _L6; else goto _L8
_L8:
        sourcemessages.error('w', (new StringBuilder()).append("call to ").append(procedure).append(" throws ").append(throwable).toString());
        return this;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public final boolean isTailCall()
    {
        return getFlag(2);
    }

    protected boolean mustCompile()
    {
        return false;
    }

    public void print(OutPort outport)
    {
        outport.startLogicalBlock("(Apply", ")", 2);
        if (isTailCall())
        {
            outport.print(" [tailcall]");
        }
        if (type != null && type != Type.pointer_type)
        {
            outport.print(" => ");
            outport.print(type);
        }
        outport.writeSpaceFill();
        printLineColumn(outport);
        func.print(outport);
        for (int i = 0; i < args.length; i++)
        {
            outport.writeSpaceLinear();
            args[i].print(outport);
        }

        outport.endLogicalBlock(")");
    }

    public void setArg(int i, Expression expression)
    {
        args[i] = expression;
    }

    public void setArgs(Expression aexpression[])
    {
        args = aexpression;
    }

    public void setFunction(Expression expression)
    {
        func = expression;
    }

    public final void setTailCall(boolean flag)
    {
        setFlag(flag, 2);
    }

    public final void setType(Type type1)
    {
        type = type1;
    }

    public boolean side_effects()
    {
        Object obj = derefFunc(func).valueIfConstant();
        if (!(obj instanceof Procedure) || !((Procedure)obj).isSideEffectFree()) goto _L2; else goto _L1
_L1:
        Expression aexpression[];
        int i;
        int j;
        aexpression = args;
        i = aexpression.length;
        j = 0;
_L5:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        if (!aexpression[j].side_effects()) goto _L3; else goto _L2
_L2:
        return true;
_L3:
        j++;
        if (true) goto _L5; else goto _L4
_L4:
        return false;
    }

    public String toString()
    {
        if (this == LambdaExp.unknownContinuation)
        {
            return "ApplyExp[unknownContinuation]";
        }
        StringBuilder stringbuilder = (new StringBuilder()).append("ApplyExp/");
        int i;
        if (args == null)
        {
            i = 0;
        } else
        {
            i = args.length;
        }
        return stringbuilder.append(i).append('[').append(func).append(']').toString();
    }

    protected Object visit(ExpVisitor expvisitor, Object obj)
    {
        return expvisitor.visitApplyExp(this, obj);
    }

    public void visitArgs(InlineCalls inlinecalls)
    {
        args = inlinecalls.visitExps(args, args.length, null);
    }

    protected void visitChildren(ExpVisitor expvisitor, Object obj)
    {
        func = expvisitor.visitAndUpdate(func, obj);
        if (expvisitor.exitValue == null)
        {
            args = expvisitor.visitExps(args, args.length, obj);
        }
    }
}
