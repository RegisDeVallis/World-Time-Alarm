// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;

// Referenced classes of package gnu.expr:
//            ClassExp, Compilation, LambdaExp, Target, 
//            ExpVisitor

public class ObjectExp extends ClassExp
{

    public ObjectExp()
    {
        super(true);
    }

    public void compile(Compilation compilation, Target target)
    {
        compileMembers(compilation);
        CodeAttr codeattr = compilation.getCode();
        codeattr.emitNew(type);
        codeattr.emitDup(1);
        gnu.bytecode.Method method = Compilation.getConstructor(type, this);
        if (closureEnvField != null)
        {
            LambdaExp lambdaexp = outerLambda();
            gnu.bytecode.Variable variable;
            if (Compilation.defaultCallConvention < 2)
            {
                variable = getOwningLambda().heapFrame;
            } else
            if (lambdaexp.heapFrame != null)
            {
                variable = lambdaexp.heapFrame;
            } else
            {
                variable = lambdaexp.closureEnv;
            }
            if (variable == null)
            {
                codeattr.emitPushThis();
            } else
            {
                codeattr.emitLoad(variable);
            }
        }
        codeattr.emitInvokeSpecial(method);
        target.compileFromStack(compilation, getCompiledClassType(compilation));
    }

    public Type getType()
    {
        return type;
    }

    protected Object visit(ExpVisitor expvisitor, Object obj)
    {
        return expvisitor.visitObjectExp(this, obj);
    }
}
