// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ExitableBlock;
import gnu.bytecode.Type;
import gnu.kawa.util.IdentityHashTable;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;

// Referenced classes of package gnu.expr:
//            Expression, QuoteExp, BlockExitException, Compilation, 
//            BlockExp, Declaration, ExpVisitor, Target

public class ExitExp extends Expression
{

    BlockExp block;
    Expression result;

    public ExitExp(BlockExp blockexp)
    {
        result = QuoteExp.voidExp;
        block = blockexp;
    }

    public ExitExp(Expression expression, BlockExp blockexp)
    {
        result = expression;
        block = blockexp;
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        throw new BlockExitException(this, result.eval(callcontext));
    }

    public void compile(Compilation compilation, Target target)
    {
        compilation.getCode();
        Object obj;
        if (result == null)
        {
            obj = QuoteExp.voidExp;
        } else
        {
            obj = result;
        }
        ((Expression) (obj)).compileWithPosition(compilation, block.exitTarget);
        block.exitableBlock.exit();
    }

    protected Expression deepCopy(IdentityHashTable identityhashtable)
    {
        Expression expression = deepCopy(result, identityhashtable);
        if (expression == null && result != null)
        {
            return null;
        }
        Object obj = identityhashtable.get(block);
        BlockExp blockexp;
        ExitExp exitexp;
        if (obj == null)
        {
            blockexp = block;
        } else
        {
            blockexp = (BlockExp)obj;
        }
        exitexp = new ExitExp(expression, blockexp);
        exitexp.flags = getFlags();
        return exitexp;
    }

    public Type getType()
    {
        return Type.neverReturnsType;
    }

    protected boolean mustCompile()
    {
        return false;
    }

    public void print(OutPort outport)
    {
        outport.startLogicalBlock("(Exit", false, ")");
        outport.writeSpaceFill();
        if (block == null || block.label == null)
        {
            outport.print("<unknown>");
        } else
        {
            outport.print(block.label.getName());
        }
        if (result != null)
        {
            outport.writeSpaceLinear();
            result.print(outport);
        }
        outport.endLogicalBlock(")");
    }

    protected Object visit(ExpVisitor expvisitor, Object obj)
    {
        return expvisitor.visitExitExp(this, obj);
    }

    protected void visitChildren(ExpVisitor expvisitor, Object obj)
    {
        result = expvisitor.visitAndUpdate(result, obj);
    }
}
