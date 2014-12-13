// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.functions.AddOp;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.math.IntNum;

// Referenced classes of package gnu.expr:
//            AccessExp, Declaration, ApplyExp, Expression, 
//            QuoteExp, ReferenceExp, Language, ModuleExp, 
//            ScopeExp, LambdaExp, IgnoreTarget, Compilation, 
//            BindingInitializer, Target, ClassExp, ExpVisitor

public class SetExp extends AccessExp
{

    public static final int BAD_SHORT = 0x10000;
    public static final int DEFINING_FLAG = 2;
    public static final int GLOBAL_FLAG = 4;
    public static final int HAS_VALUE = 64;
    public static final int PREFER_BINDING2 = 8;
    public static final int PROCEDURE = 16;
    public static final int SET_IF_UNBOUND = 32;
    Expression new_value;

    public SetExp(Declaration declaration, Expression expression)
    {
        binding = declaration;
        symbol = declaration.getSymbol();
        new_value = expression;
    }

    public SetExp(Object obj, Expression expression)
    {
        symbol = obj;
        new_value = expression;
    }

    public static int canUseInc(Expression expression, Declaration declaration)
    {
        Variable variable = declaration.getVariable();
        if (!declaration.isSimple() || variable.getType().getImplementationType().promote() != Type.intType || !(expression instanceof ApplyExp)) goto _L2; else goto _L1
_L1:
        ApplyExp applyexp = (ApplyExp)expression;
        if (applyexp.getArgCount() != 2) goto _L2; else goto _L3
_L3:
        Object obj = applyexp.getFunction().valueIfConstant();
        if (obj != AddOp.$Pl) goto _L5; else goto _L4
_L4:
        int i = 1;
_L12:
        Expression expression1;
        Expression expression2;
        expression1 = applyexp.getArg(0);
        expression2 = applyexp.getArg(1);
        if ((expression1 instanceof QuoteExp) && i > 0)
        {
            Expression expression3 = expression2;
            expression2 = expression1;
            expression1 = expression3;
        }
        if (!(expression1 instanceof ReferenceExp)) goto _L2; else goto _L6
_L6:
        ReferenceExp referenceexp = (ReferenceExp)expression1;
        if (referenceexp.getBinding() == declaration && !referenceexp.getDontDereference()) goto _L7; else goto _L2
_L2:
        return 0x10000;
_L5:
        if (obj == AddOp.$Mn)
        {
            i = -1;
            continue; /* Loop/switch isn't completed */
        }
        if (true) goto _L2; else goto _L7
_L7:
        Object obj1;
        int l;
        obj1 = expression2.valueIfConstant();
        if (!(obj1 instanceof Integer))
        {
            continue; /* Loop/switch isn't completed */
        }
        l = ((Integer)obj1).intValue();
        if (i < 0)
        {
            l = -l;
        }
        if ((short)l != l) goto _L2; else goto _L8
_L8:
        return l;
        if (!(obj1 instanceof IntNum)) goto _L2; else goto _L9
_L9:
        IntNum intnum = (IntNum)obj1;
        int j = 32767;
        int k = -j;
        if (i > 0)
        {
            k--;
        } else
        {
            j++;
        }
        if (IntNum.compare(intnum, k) < 0 || IntNum.compare(intnum, j) > 0) goto _L2; else goto _L10
_L10:
        return i * intnum.intValue();
        if (true) goto _L12; else goto _L11
_L11:
    }

    public static SetExp makeDefinition(Declaration declaration, Expression expression)
    {
        SetExp setexp = new SetExp(declaration, expression);
        setexp.setDefining(true);
        return setexp;
    }

    public static SetExp makeDefinition(Object obj, Expression expression)
    {
        SetExp setexp = new SetExp(obj, expression);
        setexp.setDefining(true);
        return setexp;
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        Environment environment = Environment.getCurrent();
        Symbol symbol;
        Language language;
        boolean flag;
        Object obj;
        if (this.symbol instanceof Symbol)
        {
            symbol = (Symbol)this.symbol;
        } else
        {
            symbol = environment.getSymbol(this.symbol.toString());
        }
        language = Language.getDefaultLanguage();
        flag = isFuncDef();
        obj = null;
        if (flag)
        {
            boolean flag1 = language.hasSeparateFunctionNamespace();
            obj = null;
            if (flag1)
            {
                obj = EnvironmentKey.FUNCTION;
            }
        }
        if (isSetIfUnbound())
        {
            Location location = environment.getLocation(symbol, obj);
            if (!location.isBound())
            {
                location.set(new_value.eval(environment));
            }
            if (getHasValue())
            {
                callcontext.writeValue(location);
            }
        } else
        {
            Object obj1 = new_value.eval(environment);
            if (binding != null && !(binding.context instanceof ModuleExp))
            {
                Object aobj[] = callcontext.evalFrames[ScopeExp.nesting(binding.context)];
                if (binding.isIndirectBinding())
                {
                    if (isDefining())
                    {
                        aobj[binding.evalIndex] = Location.make(symbol);
                    }
                    ((Location)aobj[binding.evalIndex]).set(new_value);
                } else
                {
                    aobj[binding.evalIndex] = obj1;
                }
            } else
            if (isDefining())
            {
                environment.define(symbol, obj, obj1);
            } else
            {
                environment.put(symbol, obj, obj1);
            }
            if (getHasValue())
            {
                callcontext.writeValue(obj1);
                return;
            }
        }
    }

    public void compile(Compilation compilation, Target target)
    {
        CodeAttr codeattr;
        boolean flag;
        boolean flag1;
        Declaration declaration;
        if ((new_value instanceof LambdaExp) && (target instanceof IgnoreTarget) && ((LambdaExp)new_value).getInlineOnly())
        {
            return;
        }
        codeattr = compilation.getCode();
        Expression expression;
        if (getHasValue() && !(target instanceof IgnoreTarget))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        flag1 = false;
        declaration = binding;
        expression = declaration.getValue();
        if (!(expression instanceof LambdaExp) || !(declaration.context instanceof ModuleExp) || declaration.ignorable() || ((LambdaExp)expression).getName() == null || expression != new_value) goto _L2; else goto _L1
_L1:
        ((LambdaExp)new_value).compileSetField(compilation);
_L10:
        if (flag && !flag1)
        {
            throw new Error("SetExp.compile: not implemented - return value");
        }
        break; /* Loop/switch isn't completed */
_L2:
        Object obj;
        Declaration declaration1;
        if ((declaration.shouldEarlyInit() || declaration.isAlias()) && (declaration.context instanceof ModuleExp) && isDefining() && !declaration.ignorable())
        {
            if (declaration.shouldEarlyInit())
            {
                BindingInitializer.create(declaration, new_value, compilation);
            }
            flag1 = false;
            if (flag)
            {
                declaration.load(this, 0, compilation, Target.pushObject);
                flag1 = true;
            }
            continue; /* Loop/switch isn't completed */
        }
        obj = this;
        declaration1 = contextDecl();
        if (isDefining()) goto _L4; else goto _L3
_L3:
        if (declaration == null || !declaration.isAlias()) goto _L4; else goto _L5
_L5:
        Expression expression1 = declaration.getValue();
        if (expression1 instanceof ReferenceExp) goto _L6; else goto _L4
_L4:
        if (!declaration.ignorable())
        {
            break; /* Loop/switch isn't completed */
        }
        new_value.compile(compilation, Target.Ignore);
        flag1 = false;
        continue; /* Loop/switch isn't completed */
_L6:
        ReferenceExp referenceexp;
        Declaration declaration2;
        referenceexp = (ReferenceExp)expression1;
        declaration2 = referenceexp.binding;
        if (declaration2 == null || declaration1 != null && declaration2.needsContext()) goto _L4; else goto _L7
_L7:
        declaration1 = referenceexp.contextDecl();
        obj = referenceexp;
        declaration = declaration2;
        if (true) goto _L3; else goto _L8
_L8:
        if (declaration.isAlias() && isDefining())
        {
            declaration.load(this, 2, compilation, Target.pushObject);
            ClassType classtype = ClassType.make("gnu.mapping.IndirectableLocation");
            codeattr.emitCheckcast(classtype);
            new_value.compile(compilation, Target.pushObject);
            codeattr.emitInvokeVirtual(classtype.getDeclaredMethod("setAlias", 1));
            flag1 = false;
        } else
        if (declaration.isIndirectBinding())
        {
            declaration.load(((AccessExp) (obj)), 2, compilation, Target.pushObject);
            boolean flag2 = isSetIfUnbound();
            flag1 = false;
            if (flag2)
            {
                flag1 = false;
                if (flag)
                {
                    codeattr.emitDup();
                    flag1 = true;
                }
                codeattr.pushScope();
                codeattr.emitDup();
                Variable variable1 = codeattr.addLocal(Compilation.typeLocation);
                codeattr.emitStore(variable1);
                codeattr.emitInvokeVirtual(Compilation.typeLocation.getDeclaredMethod("isBound", 0));
                codeattr.emitIfIntEqZero();
                codeattr.emitLoad(variable1);
            }
            new_value.compile(compilation, Target.pushObject);
            if (flag && !isSetIfUnbound())
            {
                codeattr.emitDupX();
                flag1 = true;
            }
            codeattr.emitInvokeVirtual(Compilation.typeLocation.getDeclaredMethod("set", 1));
            if (isSetIfUnbound())
            {
                codeattr.emitFi();
                codeattr.popScope();
            }
        } else
        if (declaration.isSimple())
        {
            Type type1 = declaration.getType();
            Variable variable = declaration.getVariable();
            if (variable == null)
            {
                variable = declaration.allocateVariable(codeattr);
            }
            int i = canUseInc(new_value, declaration);
            if (i != 0x10000)
            {
                CodeAttr codeattr1 = compilation.getCode();
                short word0 = (short)i;
                codeattr1.emitInc(variable, word0);
                flag1 = false;
                if (flag)
                {
                    codeattr.emitLoad(variable);
                    flag1 = true;
                }
            } else
            {
                new_value.compile(compilation, declaration);
                flag1 = false;
                if (flag)
                {
                    codeattr.emitDup(type1);
                    flag1 = true;
                }
                codeattr.emitStore(variable);
            }
        } else
        if ((declaration.context instanceof ClassExp) && declaration.field == null && !getFlag(16) && ((ClassExp)declaration.context).isMakingClassPair())
        {
            String s = ClassExp.slotToMethodName("set", declaration.getName());
            ClassExp classexp = (ClassExp)declaration.context;
            gnu.bytecode.Method method = classexp.type.getDeclaredMethod(s, 1);
            classexp.loadHeapFrame(compilation);
            new_value.compile(compilation, declaration);
            flag1 = false;
            if (flag)
            {
                codeattr.emitDupX();
                flag1 = true;
            }
            codeattr.emitInvoke(method);
        } else
        {
            Field field = declaration.field;
            if (!field.getStaticFlag())
            {
                declaration.loadOwningObject(declaration1, compilation);
            }
            Type type = field.getType();
            new_value.compile(compilation, declaration);
            compilation.usedClass(field.getDeclaringClass());
            if (field.getStaticFlag())
            {
                flag1 = false;
                if (flag)
                {
                    codeattr.emitDup(type);
                    flag1 = true;
                }
                codeattr.emitPutStatic(field);
            } else
            {
                flag1 = false;
                if (flag)
                {
                    codeattr.emitDupX();
                    flag1 = true;
                }
                codeattr.emitPutField(field);
            }
        }
        if (true) goto _L10; else goto _L9
_L9:
        if (flag)
        {
            target.compileFromStack(compilation, getType());
            return;
        } else
        {
            compilation.compileConstant(Values.empty, target);
            return;
        }
    }

    public final boolean getHasValue()
    {
        return (0x40 & flags) != 0;
    }

    public final Expression getNewValue()
    {
        return new_value;
    }

    public final Type getType()
    {
        if (!getHasValue())
        {
            return Type.voidType;
        }
        if (binding == null)
        {
            return Type.pointer_type;
        } else
        {
            return binding.getType();
        }
    }

    public final boolean isDefining()
    {
        return (2 & flags) != 0;
    }

    public final boolean isFuncDef()
    {
        return (0x10 & flags) != 0;
    }

    public final boolean isSetIfUnbound()
    {
        return (0x20 & flags) != 0;
    }

    protected boolean mustCompile()
    {
        return false;
    }

    public void print(OutPort outport)
    {
        String s;
        if (isDefining())
        {
            s = "(Define";
        } else
        {
            s = "(Set";
        }
        outport.startLogicalBlock(s, ")", 2);
        outport.writeSpaceFill();
        printLineColumn(outport);
        if (binding == null || symbol.toString() != binding.getName())
        {
            outport.print('/');
            outport.print(symbol);
        }
        if (binding != null)
        {
            outport.print('/');
            outport.print(binding);
        }
        outport.writeSpaceLinear();
        new_value.print(outport);
        outport.endLogicalBlock(")");
    }

    public final void setDefining(boolean flag)
    {
        if (flag)
        {
            flags = 2 | flags;
            return;
        } else
        {
            flags = -3 & flags;
            return;
        }
    }

    public final void setFuncDef(boolean flag)
    {
        if (flag)
        {
            flags = 0x10 | flags;
            return;
        } else
        {
            flags = 0xffffffef & flags;
            return;
        }
    }

    public final void setHasValue(boolean flag)
    {
        if (flag)
        {
            flags = 0x40 | flags;
            return;
        } else
        {
            flags = 0xffffffbf & flags;
            return;
        }
    }

    public final void setSetIfUnbound(boolean flag)
    {
        if (flag)
        {
            flags = 0x20 | flags;
            return;
        } else
        {
            flags = 0xffffffdf & flags;
            return;
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("SetExp[").append(symbol).append(":=").append(new_value).append(']').toString();
    }

    protected Object visit(ExpVisitor expvisitor, Object obj)
    {
        return expvisitor.visitSetExp(this, obj);
    }

    protected void visitChildren(ExpVisitor expvisitor, Object obj)
    {
        new_value = expvisitor.visitAndUpdate(new_value, obj);
    }
}
