// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.UnboundLocationException;

// Referenced classes of package gnu.expr:
//            AccessExp, Declaration, QuoteExp, LambdaExp, 
//            Expression, ModuleExp, ScopeExp, ConsumerTarget, 
//            Compilation, ApplyExp, ExpVisitor, Target, 
//            InlineCalls

public class ReferenceExp extends AccessExp
{

    public static final int DONT_DEREFERENCE = 2;
    public static final int PREFER_BINDING2 = 8;
    public static final int PROCEDURE_NAME = 4;
    public static final int TYPE_NAME = 16;
    static int counter;
    int id;

    public ReferenceExp(Declaration declaration)
    {
        this(declaration.getSymbol(), declaration);
    }

    public ReferenceExp(Object obj)
    {
        int i = 1 + counter;
        counter = i;
        id = i;
        symbol = obj;
    }

    public ReferenceExp(Object obj, Declaration declaration)
    {
        int i = 1 + counter;
        counter = i;
        id = i;
        symbol = obj;
        binding = declaration;
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        if (binding == null || !binding.isAlias() || getDontDereference() || !(binding.value instanceof ReferenceExp)) goto _L2; else goto _L1
_L1:
        Object obj2;
        ReferenceExp referenceexp = (ReferenceExp)binding.value;
        if (referenceexp.getDontDereference() && referenceexp.binding != null)
        {
            Expression expression = referenceexp.binding.getValue();
            if ((expression instanceof QuoteExp) || (expression instanceof ReferenceExp) || (expression instanceof LambdaExp))
            {
                expression.apply(callcontext);
                return;
            }
        }
        obj2 = binding.value.eval(callcontext);
_L11:
        if (!getDontDereference() && binding.isIndirectBinding())
        {
            obj2 = ((Location)obj2).get();
        }
        callcontext.writeValue(obj2);
        return;
_L2:
        if (binding == null || binding.field == null || !binding.field.getDeclaringClass().isExisting() || getDontDereference() && !binding.isIndirectBinding())
        {
            break MISSING_BLOCK_LABEL_317;
        }
        if (!binding.field.getStaticFlag()) goto _L4; else goto _L3
_L3:
        Object obj3 = null;
_L5:
        Object obj4;
        try
        {
            obj2 = binding.field.getReflectField().get(obj3);
        }
        catch (Exception exception)
        {
            throw new UnboundLocationException((new StringBuilder()).append("exception evaluating ").append(this.symbol).append(" from ").append(binding.field).append(" - ").append(exception).toString(), this);
        }
        continue; /* Loop/switch isn't completed */
_L4:
        obj4 = contextDecl().getValue().eval(callcontext);
        obj3 = obj4;
          goto _L5
        Environment environment;
        Symbol symbol;
        Object obj;
        Object obj1;
        if (binding != null && ((binding.value instanceof QuoteExp) || (binding.value instanceof LambdaExp)) && binding.value != QuoteExp.undefined_exp && (!getDontDereference() || binding.isIndirectBinding()))
        {
            obj2 = binding.value.eval(callcontext);
            continue; /* Loop/switch isn't completed */
        }
        if (binding != null && (!(binding.context instanceof ModuleExp) || binding.isPrivate()))
        {
            break MISSING_BLOCK_LABEL_554;
        }
        environment = Environment.getCurrent();
        boolean flag;
        if (this.symbol instanceof Symbol)
        {
            symbol = (Symbol)this.symbol;
        } else
        {
            symbol = environment.getSymbol(this.symbol.toString());
        }
        flag = getFlag(8);
        obj = null;
        if (flag)
        {
            boolean flag1 = isProcedureName();
            obj = null;
            if (flag1)
            {
                obj = EnvironmentKey.FUNCTION;
            }
        }
        if (!getDontDereference()) goto _L7; else goto _L6
_L6:
        obj1 = environment.getLocation(symbol, obj);
_L9:
        callcontext.writeValue(obj1);
        return;
_L7:
        String s;
        s = Location.UNBOUND;
        obj1 = environment.get(symbol, obj, s);
        if (obj1 != s) goto _L9; else goto _L8
_L8:
        throw new UnboundLocationException(symbol, this);
        obj2 = callcontext.evalFrames[ScopeExp.nesting(binding.context)][binding.evalIndex];
        if (true) goto _L11; else goto _L10
_L10:
    }

    public void compile(Compilation compilation, Target target)
    {
        if (!(target instanceof ConsumerTarget) || !((ConsumerTarget)target).compileWrite(this, compilation))
        {
            binding.load(this, flags, compilation, target);
        }
    }

    protected Expression deepCopy(IdentityHashTable identityhashtable)
    {
        Declaration declaration = (Declaration)identityhashtable.get(binding, binding);
        ReferenceExp referenceexp = new ReferenceExp(identityhashtable.get(symbol, symbol), declaration);
        referenceexp.flags = getFlags();
        return referenceexp;
    }

    public final boolean getDontDereference()
    {
        return (2 & flags) != 0;
    }

    public Type getType()
    {
        Declaration declaration = binding;
        Object obj;
        if (declaration == null || declaration.isFluid())
        {
            obj = Type.pointer_type;
        } else
        {
            if (getDontDereference())
            {
                return Compilation.typeLocation;
            }
            Declaration declaration1 = Declaration.followAliases(declaration);
            obj = declaration1.getType();
            if (obj == null || obj == Type.pointer_type)
            {
                Expression expression = declaration1.getValue();
                if (expression != null && expression != QuoteExp.undefined_exp)
                {
                    Expression expression1 = declaration1.value;
                    declaration1.value = null;
                    obj = expression.getType();
                    declaration1.value = expression1;
                }
            }
            if (obj == Type.toStringType)
            {
                return Type.javalangStringType;
            }
        }
        return ((Type) (obj));
    }

    public final boolean isProcedureName()
    {
        return (4 & flags) != 0;
    }

    public boolean isSingleValue()
    {
        if (binding != null && binding.getFlag(0x40000L))
        {
            return true;
        } else
        {
            return super.isSingleValue();
        }
    }

    public final boolean isUnknown()
    {
        return Declaration.isUnknown(binding);
    }

    protected boolean mustCompile()
    {
        return false;
    }

    public void print(OutPort outport)
    {
        outport.print("(Ref/");
        outport.print(id);
        if (symbol != null && (binding == null || symbol.toString() != binding.getName()))
        {
            outport.print('/');
            outport.print(symbol);
        }
        if (binding != null)
        {
            outport.print('/');
            outport.print(binding);
        }
        outport.print(")");
    }

    public final void setDontDereference(boolean flag)
    {
        setFlag(flag, 2);
    }

    public final void setProcedureName(boolean flag)
    {
        setFlag(flag, 4);
    }

    public boolean side_effects()
    {
        return binding == null || !binding.isLexical();
    }

    public String toString()
    {
        return (new StringBuilder()).append("RefExp/").append(symbol).append('/').append(id).append('/').toString();
    }

    public Expression validateApply(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Declaration declaration)
    {
        Declaration declaration1 = binding;
        if (declaration1 != null && !declaration1.getFlag(0x10000L))
        {
            Declaration declaration2 = Declaration.followAliases(declaration1);
            if (!declaration2.isIndirectBinding())
            {
                Expression expression = declaration2.getValue();
                if (expression != null)
                {
                    return expression.validateApply(applyexp, inlinecalls, type, declaration2);
                }
            }
        } else
        if (getSymbol() instanceof Symbol)
        {
            Symbol symbol = (Symbol)getSymbol();
            Object obj = Environment.getCurrent().getFunction(symbol, null);
            if (obj instanceof Procedure)
            {
                return (new QuoteExp(obj)).validateApply(applyexp, inlinecalls, type, null);
            }
        }
        applyexp.visitArgs(inlinecalls);
        return applyexp;
    }

    public final Object valueIfConstant()
    {
        if (binding != null)
        {
            Expression expression = binding.getValue();
            if (expression != null)
            {
                return expression.valueIfConstant();
            }
        }
        return null;
    }

    protected Object visit(ExpVisitor expvisitor, Object obj)
    {
        return expvisitor.visitReferenceExp(this, obj);
    }
}
