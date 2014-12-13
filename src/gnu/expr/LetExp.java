// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.OutPort;

// Referenced classes of package gnu.expr:
//            ScopeExp, QuoteExp, Declaration, Expression, 
//            Compilation, Target, CheckedTarget, BindingInitializer, 
//            ExpVisitor

public class LetExp extends ScopeExp
{

    public Expression body;
    public Expression inits[];

    public LetExp(Expression aexpression[])
    {
        inits = aexpression;
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        int i;
        Object aobj[];
        Object aobj1[][];
        Object aobj3[];
        int j;
        Declaration declaration;
        setIndexes();
        i = ScopeExp.nesting(this);
        aobj = new Object[frameSize];
        aobj1 = callcontext.evalFrames;
        Declaration declaration1;
        if (aobj1 == null)
        {
            aobj1 = new Object[i + 10][];
            callcontext.evalFrames = aobj1;
        } else
        if (i >= aobj1.length)
        {
            Object aobj2[][] = new Object[i + 10][];
            System.arraycopy(((Object) (aobj1)), 0, ((Object) (aobj2)), 0, aobj1.length);
            aobj1 = aobj2;
            callcontext.evalFrames = aobj2;
        }
        aobj3 = aobj1[i];
        aobj1[i] = aobj;
        j = 0;
        declaration = firstDecl();
_L4:
        if (declaration == null)
        {
            break MISSING_BLOCK_LABEL_223;
        }
        if (inits[j] != QuoteExp.undefined_exp) goto _L2; else goto _L1
_L1:
        declaration1 = declaration.nextDecl();
        declaration = declaration1;
        j++;
        continue; /* Loop/switch isn't completed */
_L2:
        Object obj;
        Type type;
        obj = evalVariable(j, callcontext);
        type = declaration.type;
        if (type == null)
        {
            break MISSING_BLOCK_LABEL_177;
        }
        if (type != Type.pointer_type)
        {
            obj = type.coerceFromObject(obj);
        }
        Location location;
        if (!declaration.isIndirectBinding())
        {
            break MISSING_BLOCK_LABEL_203;
        }
        location = declaration.makeIndirectLocationFor();
        location.set(obj);
        obj = location;
        aobj[j] = obj;
          goto _L1
        Exception exception;
        exception;
        aobj1[i] = aobj3;
        throw exception;
        body.apply(callcontext);
        aobj1[i] = aobj3;
        return;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void compile(Compilation compilation, Target target)
    {
        CodeAttr codeattr = compilation.getCode();
        Declaration declaration = firstDecl();
        int i = 0;
        while (i < inits.length) 
        {
            Object obj = inits[i];
            boolean flag = declaration.needsInit();
            if (flag && declaration.isSimple())
            {
                declaration.allocateVariable(codeattr);
            }
            Target target1;
            if (!flag || declaration.isIndirectBinding() && obj == QuoteExp.undefined_exp)
            {
                target1 = Target.Ignore;
            } else
            {
                Type type = declaration.getType();
                target1 = CheckedTarget.getInstance(declaration);
                if (obj == QuoteExp.undefined_exp)
                {
                    if (type instanceof PrimType)
                    {
                        obj = new QuoteExp(new Byte((byte)0));
                    } else
                    if (type != null && type != Type.pointer_type)
                    {
                        obj = QuoteExp.nullExp;
                    }
                }
            }
            ((Expression) (obj)).compileWithPosition(compilation, target1);
            i++;
            declaration = declaration.nextDecl();
        }
        codeattr.enterScope(getVarScope());
        store_rest(compilation, 0, firstDecl());
        body.compileWithPosition(compilation, target);
        popScope(codeattr);
    }

    protected Object evalVariable(int i, CallContext callcontext)
        throws Throwable
    {
        return inits[i].eval(callcontext);
    }

    public Expression getBody()
    {
        return body;
    }

    public final Type getType()
    {
        return body.getType();
    }

    protected boolean mustCompile()
    {
        return false;
    }

    public void print(OutPort outport)
    {
        print(outport, "(Let", ")");
    }

    public void print(OutPort outport, String s, String s1)
    {
        outport.startLogicalBlock((new StringBuilder()).append(s).append("#").append(id).toString(), s1, 2);
        outport.writeSpaceFill();
        printLineColumn(outport);
        outport.startLogicalBlock("(", false, ")");
        Declaration declaration = firstDecl();
        int i = 0;
        while (declaration != null) 
        {
            if (i > 0)
            {
                outport.writeSpaceFill();
            }
            outport.startLogicalBlock("(", false, ")");
            declaration.printInfo(outport);
            if (inits != null)
            {
                outport.writeSpaceFill();
                outport.print('=');
                outport.writeSpaceFill();
                if (i >= inits.length)
                {
                    outport.print("<missing init>");
                } else
                if (inits[i] == null)
                {
                    outport.print("<null>");
                } else
                {
                    inits[i].print(outport);
                }
                i++;
            }
            outport.endLogicalBlock(")");
            declaration = declaration.nextDecl();
        }
        outport.endLogicalBlock(")");
        outport.writeSpaceLinear();
        if (body == null)
        {
            outport.print("<null body>");
        } else
        {
            body.print(outport);
        }
        outport.endLogicalBlock(s1);
    }

    public void setBody(Expression expression)
    {
        body = expression;
    }

    void store_rest(Compilation compilation, int i, Declaration declaration)
    {
        if (declaration != null)
        {
            store_rest(compilation, i + 1, declaration.nextDecl());
            if (declaration.needsInit())
            {
                if (declaration.isIndirectBinding())
                {
                    CodeAttr codeattr = compilation.getCode();
                    if (inits[i] == QuoteExp.undefined_exp)
                    {
                        Object obj = declaration.getSymbol();
                        compilation.compileConstant(obj, Target.pushObject);
                        codeattr.emitInvokeStatic(BindingInitializer.makeLocationMethod(obj));
                    } else
                    {
                        declaration.pushIndirectBinding(compilation);
                    }
                }
                declaration.compileStore(compilation);
            }
        }
    }

    protected Object visit(ExpVisitor expvisitor, Object obj)
    {
        return expvisitor.visitLetExp(this, obj);
    }

    protected void visitChildren(ExpVisitor expvisitor, Object obj)
    {
        visitInitializers(expvisitor, obj);
        if (expvisitor.exitValue == null)
        {
            body = expvisitor.visitAndUpdate(body, obj);
        }
    }

    public void visitInitializers(ExpVisitor expvisitor, Object obj)
    {
        Declaration declaration = firstDecl();
        for (int i = 0; i < inits.length;)
        {
            Expression expression = inits[i];
            if (expression == null)
            {
                throw new Error((new StringBuilder()).append("null1 init for ").append(this).append(" i:").append(i).append(" d:").append(declaration).toString());
            }
            Expression expression1 = expvisitor.visitAndUpdate(expression, obj);
            if (expression1 == null)
            {
                throw new Error((new StringBuilder()).append("null2 init for ").append(this).append(" was:").append(expression).toString());
            }
            inits[i] = expression1;
            if (declaration.value == expression)
            {
                declaration.value = expression1;
            }
            i++;
            declaration = declaration.nextDecl();
        }

    }
}
