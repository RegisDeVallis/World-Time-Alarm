// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.Type;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.text.Options;
import java.util.Vector;

// Referenced classes of package gnu.expr:
//            Expression, QuoteExp, Target, Compilation, 
//            ExpVisitor

public class BeginExp extends Expression
{

    Vector compileOptions;
    Expression exps[];
    int length;

    public BeginExp()
    {
    }

    public BeginExp(Expression expression, Expression expression1)
    {
        exps = new Expression[2];
        exps[0] = expression;
        exps[1] = expression1;
        length = 2;
    }

    public BeginExp(Expression aexpression[])
    {
        exps = aexpression;
        length = aexpression.length;
    }

    public static final Expression canonicalize(Expression expression)
    {
        if (expression instanceof BeginExp)
        {
            BeginExp beginexp = (BeginExp)expression;
            if (beginexp.compileOptions == null)
            {
                int i = beginexp.length;
                if (i == 0)
                {
                    return QuoteExp.voidExp;
                }
                if (i == 1)
                {
                    return canonicalize(beginexp.exps[0]);
                }
            }
        }
        return expression;
    }

    public static final Expression canonicalize(Expression aexpression[])
    {
        int i = aexpression.length;
        if (i == 0)
        {
            return QuoteExp.voidExp;
        }
        if (i == 1)
        {
            return canonicalize(aexpression[0]);
        } else
        {
            return new BeginExp(aexpression);
        }
    }

    public final void add(Expression expression)
    {
        if (exps == null)
        {
            exps = new Expression[8];
        }
        if (length == exps.length)
        {
            Expression aexpression1[] = new Expression[2 * length];
            System.arraycopy(exps, 0, aexpression1, 0, length);
            exps = aexpression1;
        }
        Expression aexpression[] = exps;
        int i = length;
        length = i + 1;
        aexpression[i] = expression;
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        int i;
        gnu.lists.Consumer consumer;
        int j;
        i = length;
        consumer = callcontext.consumer;
        callcontext.consumer = VoidConsumer.instance;
        j = 0;
_L2:
        if (j >= i - 1)
        {
            break; /* Loop/switch isn't completed */
        }
        exps[j].eval(callcontext);
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        callcontext.consumer = consumer;
        exps[j].apply(callcontext);
        return;
        Exception exception;
        exception;
        callcontext.consumer = consumer;
        throw exception;
    }

    public void compile(Compilation compilation, Target target)
    {
        pushOptions(compilation);
        int i = length;
        int j = 0;
_L2:
        if (j >= i - 1)
        {
            break; /* Loop/switch isn't completed */
        }
        exps[j].compileWithPosition(compilation, Target.Ignore);
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        exps[j].compileWithPosition(compilation, target);
        popOptions(compilation);
        return;
        Exception exception;
        exception;
        popOptions(compilation);
        throw exception;
    }

    public final int getExpressionCount()
    {
        return length;
    }

    public final Expression[] getExpressions()
    {
        return exps;
    }

    public Type getType()
    {
        return exps[-1 + length].getType();
    }

    protected boolean mustCompile()
    {
        return false;
    }

    public void popOptions(Compilation compilation)
    {
        if (compileOptions != null && compilation != null)
        {
            compilation.currentOptions.popOptionValues(compileOptions);
        }
    }

    public void print(OutPort outport)
    {
        outport.startLogicalBlock("(Begin", ")", 2);
        outport.writeSpaceFill();
        printLineColumn(outport);
        if (compileOptions != null)
        {
            outport.writeSpaceFill();
            outport.startLogicalBlock("(CompileOptions", ")", 2);
            int k = compileOptions.size();
            for (int l = 0; l < k; l += 3)
            {
                Object obj = compileOptions.elementAt(l);
                Object obj1 = compileOptions.elementAt(l + 2);
                outport.writeSpaceFill();
                outport.startLogicalBlock("", "", 2);
                outport.print(obj);
                outport.print(':');
                outport.writeSpaceLinear();
                outport.print(obj1);
                outport.endLogicalBlock("");
            }

            outport.endLogicalBlock(")");
        }
        int i = length;
        for (int j = 0; j < i; j++)
        {
            outport.writeSpaceLinear();
            exps[j].print(outport);
        }

        outport.endLogicalBlock(")");
    }

    public void pushOptions(Compilation compilation)
    {
        if (compileOptions != null && compilation != null)
        {
            compilation.currentOptions.pushOptionValues(compileOptions);
        }
    }

    public void setCompileOptions(Vector vector)
    {
        compileOptions = vector;
    }

    public final void setExpressions(Expression aexpression[])
    {
        exps = aexpression;
        length = aexpression.length;
    }

    protected Object visit(ExpVisitor expvisitor, Object obj)
    {
        pushOptions(expvisitor.comp);
        Object obj1 = expvisitor.visitBeginExp(this, obj);
        popOptions(expvisitor.comp);
        return obj1;
        Exception exception;
        exception;
        popOptions(expvisitor.comp);
        throw exception;
    }

    protected void visitChildren(ExpVisitor expvisitor, Object obj)
    {
        exps = expvisitor.visitExps(exps, length, obj);
    }
}
