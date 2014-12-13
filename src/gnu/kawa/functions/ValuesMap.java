// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.IgnoreTarget;
import gnu.expr.Inlineable;
import gnu.expr.LambdaExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.StackTarget;
import gnu.expr.Target;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class ValuesMap extends MethodProc
    implements Inlineable
{

    public static final ValuesMap valuesMap = new ValuesMap(-1);
    public static final ValuesMap valuesMapWithPos = new ValuesMap(1);
    private final int startCounter;

    private ValuesMap(int i)
    {
        startCounter = i;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyValuesMap");
    }

    static LambdaExp canInline(ApplyExp applyexp, ValuesMap valuesmap)
    {
        byte byte0 = 2;
        Expression aexpression[] = applyexp.getArgs();
        if (aexpression.length == byte0)
        {
            Expression expression = aexpression[0];
            if (expression instanceof LambdaExp)
            {
                LambdaExp lambdaexp = (LambdaExp)expression;
                if (lambdaexp.min_args == lambdaexp.max_args)
                {
                    int i = lambdaexp.min_args;
                    if (valuesmap.startCounter < 0)
                    {
                        byte0 = 1;
                    }
                    if (i == byte0)
                    {
                        return lambdaexp;
                    }
                }
            }
        }
        return null;
    }

    public static void compileInlined(LambdaExp lambdaexp, Expression expression, int i, Method method, Compilation compilation, Target target)
    {
        Declaration declaration = lambdaexp.firstDecl();
        CodeAttr codeattr = compilation.getCode();
        Scope scope = codeattr.pushScope();
        Type type = declaration.getType();
        gnu.bytecode.Variable variable;
        Declaration declaration1;
        Expression aexpression[];
        Object obj;
        gnu.bytecode.Variable variable1;
        gnu.bytecode.Variable variable2;
        gnu.bytecode.Variable variable3;
        Label label;
        Label label1;
        if (i >= 0)
        {
            variable = scope.addVariable(codeattr, Type.intType, "position");
            codeattr.emitPushInt(i);
            codeattr.emitStore(variable);
            declaration1 = new Declaration(variable);
        } else
        {
            variable = null;
            declaration1 = null;
        }
        if (declaration.isSimple() && method == null)
        {
            declaration.allocateVariable(codeattr);
        } else
        {
            String s = Compilation.mangleNameIfNeeded(declaration.getName());
            declaration = new Declaration(codeattr.addLocal(type.getImplementationType(), s));
        }
        if (i >= 0)
        {
            aexpression = new Expression[2];
            ReferenceExp referenceexp3 = new ReferenceExp(declaration);
            aexpression[0] = referenceexp3;
            ReferenceExp referenceexp4 = new ReferenceExp(declaration1);
            aexpression[1] = referenceexp4;
        } else
        {
            aexpression = new Expression[1];
            ReferenceExp referenceexp = new ReferenceExp(declaration);
            aexpression[0] = referenceexp;
        }
        obj = new ApplyExp(lambdaexp, aexpression);
        if (method != null)
        {
            if (((Expression) (obj)).getType().getImplementationType() != Type.booleanType)
            {
                Expression aexpression1[] = new Expression[2];
                aexpression1[0] = ((Expression) (obj));
                ReferenceExp referenceexp2 = new ReferenceExp(declaration1);
                aexpression1[1] = referenceexp2;
                obj = new ApplyExp(method, aexpression1);
            }
            ReferenceExp referenceexp1 = new ReferenceExp(declaration);
            obj = new IfExp(((Expression) (obj)), referenceexp1, QuoteExp.voidExp);
        }
        variable1 = codeattr.addLocal(Type.intType);
        variable2 = codeattr.addLocal(Type.pointer_type);
        variable3 = codeattr.addLocal(Type.intType);
        expression.compileWithPosition(compilation, Target.pushObject);
        codeattr.emitStore(variable2);
        codeattr.emitPushInt(0);
        codeattr.emitStore(variable1);
        label = new Label(codeattr);
        label1 = new Label(codeattr);
        label.define(codeattr);
        codeattr.emitLoad(variable2);
        codeattr.emitLoad(variable1);
        codeattr.emitInvokeStatic(Compilation.typeValues.getDeclaredMethod("nextIndex", 2));
        codeattr.emitDup(Type.intType);
        codeattr.emitStore(variable3);
        codeattr.emitGotoIfIntLtZero(label1);
        codeattr.emitLoad(variable2);
        codeattr.emitLoad(variable1);
        codeattr.emitInvokeStatic(Compilation.typeValues.getDeclaredMethod("nextValue", 2));
        StackTarget.convert(compilation, Type.objectType, type);
        declaration.compileStore(compilation);
        ((Expression) (obj)).compile(compilation, target);
        if (i >= 0)
        {
            codeattr.emitInc(variable, (short)1);
        }
        codeattr.emitLoad(variable3);
        codeattr.emitStore(variable1);
        codeattr.emitGoto(label);
        label1.define(codeattr);
        codeattr.popScope();
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        Procedure procedure = (Procedure)callcontext.getNextArg();
        gnu.lists.Consumer _tmp = callcontext.consumer;
        Object obj = callcontext.getNextArg();
        Procedure.checkArgCount(procedure, 1);
        if (obj instanceof Values)
        {
            int i = 0;
            int j = startCounter;
            Values values = (Values)obj;
            do
            {
                i = values.nextPos(i);
                if (i == 0)
                {
                    break;
                }
                Object obj1 = values.getPosPrevious(i);
                if (startCounter >= 0)
                {
                    int k = j + 1;
                    procedure.check2(obj1, IntNum.make(j), callcontext);
                    j = k;
                } else
                {
                    procedure.check1(obj1, callcontext);
                }
                callcontext.runUntilDone();
            } while (true);
        } else
        {
            if (startCounter >= 0)
            {
                procedure.check2(obj, IntNum.make(startCounter), callcontext);
            } else
            {
                procedure.check1(obj, callcontext);
            }
            callcontext.runUntilDone();
        }
    }

    public void compile(ApplyExp applyexp, Compilation compilation, Target target)
    {
        LambdaExp lambdaexp = canInline(applyexp, this);
        if (lambdaexp == null)
        {
            ApplyExp.compile(applyexp, compilation, target);
            return;
        }
        Expression aexpression[] = applyexp.getArgs();
        if (!(target instanceof IgnoreTarget) && !(target instanceof ConsumerTarget))
        {
            ConsumerTarget.compileUsingConsumer(applyexp, compilation, target);
            return;
        } else
        {
            compileInlined(lambdaexp, aexpression[1], startCounter, null, compilation, target);
            return;
        }
    }

    public Type getReturnType(Expression aexpression[])
    {
        return Type.pointer_type;
    }

    public int numArgs()
    {
        return 8194;
    }

}
