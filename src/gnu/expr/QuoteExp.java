// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.mapping.WrongArguments;
import gnu.text.SourceLocator;

// Referenced classes of package gnu.expr:
//            Expression, Special, IgnoreTarget, Compilation, 
//            StackTarget, Target, Language, InlineCalls, 
//            Declaration, ApplyExp, PrimProcedure, ReferenceExp, 
//            ExpVisitor

public class QuoteExp extends Expression
{

    public static final int EXPLICITLY_TYPED = 2;
    public static final int SHARED_CONSTANT = 4;
    public static QuoteExp abstractExp;
    public static final QuoteExp classObjectExp;
    public static QuoteExp falseExp;
    public static QuoteExp nullExp;
    public static QuoteExp trueExp;
    public static QuoteExp undefined_exp;
    public static QuoteExp voidExp;
    protected Type type;
    Object value;

    public QuoteExp(Object obj)
    {
        value = obj;
    }

    public QuoteExp(Object obj, Type type1)
    {
        value = obj;
        setType(type1);
    }

    public static QuoteExp getInstance(Object obj)
    {
        return getInstance(obj, null);
    }

    public static QuoteExp getInstance(Object obj, SourceLocator sourcelocator)
    {
        if (obj == null)
        {
            return nullExp;
        }
        if (obj == Type.pointer_type)
        {
            return classObjectExp;
        }
        if (obj == Special.undefined)
        {
            return undefined_exp;
        }
        if (obj == Values.empty)
        {
            return voidExp;
        }
        if (obj instanceof Boolean)
        {
            if (((Boolean)obj).booleanValue())
            {
                return trueExp;
            } else
            {
                return falseExp;
            }
        }
        QuoteExp quoteexp = new QuoteExp(obj);
        if (sourcelocator != null)
        {
            quoteexp.setLocation(sourcelocator);
        }
        return quoteexp;
    }

    static QuoteExp makeShared(Object obj)
    {
        QuoteExp quoteexp = new QuoteExp(obj);
        quoteexp.setFlag(4);
        return quoteexp;
    }

    static QuoteExp makeShared(Object obj, Type type1)
    {
        QuoteExp quoteexp = new QuoteExp(obj, type1);
        quoteexp.setFlag(4);
        return quoteexp;
    }

    public void apply(CallContext callcontext)
    {
        callcontext.writeValue(value);
    }

    public void compile(Compilation compilation, Target target)
    {
        if (type == null || type == Type.pointer_type || (target instanceof IgnoreTarget) || (type instanceof ObjectType) && type.isInstance(value))
        {
            compilation.compileConstant(value, target);
            return;
        } else
        {
            compilation.compileConstant(value, StackTarget.getInstance(type));
            target.compileFromStack(compilation, type);
            return;
        }
    }

    public Expression deepCopy(IdentityHashTable identityhashtable)
    {
        return this;
    }

    public final Type getRawType()
    {
        return type;
    }

    public final Type getType()
    {
        if (type != null)
        {
            return type;
        }
        if (value == Values.empty)
        {
            return Type.voidType;
        }
        if (value == null)
        {
            return Type.nullType;
        }
        if (this == undefined_exp)
        {
            return Type.pointer_type;
        } else
        {
            return Type.make(value.getClass());
        }
    }

    public final Object getValue()
    {
        return value;
    }

    public boolean isExplicitlyTyped()
    {
        return getFlag(2);
    }

    public boolean isSharedConstant()
    {
        return getFlag(4);
    }

    protected boolean mustCompile()
    {
        return false;
    }

    public void print(OutPort outport)
    {
        Object obj;
        gnu.lists.AbstractFormat abstractformat;
        outport.startLogicalBlock("(Quote", ")", 2);
        outport.writeSpaceLinear();
        obj = value;
        if (obj instanceof Expression)
        {
            obj = obj.toString();
        }
        abstractformat = outport.objectFormat;
        outport.objectFormat = Language.getDefaultLanguage().getFormat(true);
        outport.print(obj);
        if (type != null)
        {
            outport.print(" ::");
            outport.print(type.getName());
        }
        outport.objectFormat = abstractformat;
        outport.endLogicalBlock(")");
        return;
        Exception exception;
        exception;
        outport.objectFormat = abstractformat;
        throw exception;
    }

    public void setType(Type type1)
    {
        type = type1;
        setFlag(2);
    }

    public boolean side_effects()
    {
        return false;
    }

    public String toString()
    {
        return (new StringBuilder()).append("QuoteExp[").append(value).append("]").toString();
    }

    public Expression validateApply(ApplyExp applyexp, InlineCalls inlinecalls, Type type1, Declaration declaration)
    {
        if (this != undefined_exp) goto _L2; else goto _L1
_L1:
        return applyexp;
_L2:
        Procedure procedure;
        int i;
        Compilation compilation;
        Object obj = getValue();
        if (!(obj instanceof Procedure))
        {
            String s1;
            if (declaration == null || obj == null)
            {
                s1 = "called value is not a procedure";
            } else
            {
                s1 = (new StringBuilder()).append("calling ").append(declaration.getName()).append(" which is a ").append(obj.getClass().getName()).toString();
            }
            return inlinecalls.noteError(s1);
        }
        procedure = (Procedure)obj;
        i = applyexp.getArgCount();
        String s = WrongArguments.checkArgCount(procedure, i);
        if (s != null)
        {
            return inlinecalls.noteError(s);
        }
        Expression expression = inlinecalls.maybeInline(applyexp, type1, procedure);
        if (expression != null)
        {
            return expression;
        }
        Expression aexpression[] = applyexp.args;
        MethodProc methodproc;
        int j;
        if (procedure instanceof MethodProc)
        {
            methodproc = (MethodProc)procedure;
        } else
        {
            methodproc = null;
        }
        j = 0;
        while (j < i) 
        {
            Type type2;
            if (methodproc != null)
            {
                type2 = methodproc.getParameterType(j);
            } else
            {
                type2 = null;
            }
            if (j == i - 1 && type2 != null && methodproc.maxArgs() < 0 && j == methodproc.minArgs())
            {
                type2 = null;
            }
            aexpression[j] = inlinecalls.visit(aexpression[j], type2);
            j++;
        }
        if (applyexp.getFlag(4))
        {
            Expression expression1 = applyexp.inlineIfConstant(procedure, inlinecalls);
            if (expression1 != applyexp)
            {
                return inlinecalls.visit(expression1, type1);
            }
        }
        compilation = inlinecalls.getCompilation();
        if (!compilation.inlineOk(procedure))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (ApplyExp.asInlineable(procedure) == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (applyexp.getFunction() != this)
        {
            ApplyExp applyexp2 = new ApplyExp(this, applyexp.getArgs());
            return applyexp2.setLine(applyexp);
        }
        if (true) goto _L1; else goto _L3
_L3:
        PrimProcedure primprocedure;
        ApplyExp applyexp1;
        primprocedure = PrimProcedure.getMethodFor(procedure, declaration, applyexp.args, compilation.getLanguage());
        if (primprocedure == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!primprocedure.getStaticFlag() && declaration != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        applyexp1 = new ApplyExp(primprocedure, applyexp.args);
_L5:
        return applyexp1.setLine(applyexp);
        if (declaration.base == null) goto _L1; else goto _L4
_L4:
        Expression aexpression1[] = new Expression[i + 1];
        System.arraycopy(applyexp.getArgs(), 0, aexpression1, 1, i);
        aexpression1[0] = new ReferenceExp(declaration.base);
        applyexp1 = new ApplyExp(primprocedure, aexpression1);
          goto _L5
        if (true) goto _L1; else goto _L6
_L6:
    }

    public final Object valueIfConstant()
    {
        return value;
    }

    protected Object visit(ExpVisitor expvisitor, Object obj)
    {
        return expvisitor.visitQuoteExp(this, obj);
    }

    static 
    {
        undefined_exp = makeShared(Special.undefined);
        abstractExp = makeShared(Special.abstractSpecial);
        voidExp = makeShared(Values.empty, Type.voidType);
        trueExp = makeShared(Boolean.TRUE);
        falseExp = makeShared(Boolean.FALSE);
        nullExp = makeShared(null, Type.nullType);
        classObjectExp = makeShared(Type.objectType);
    }
}
