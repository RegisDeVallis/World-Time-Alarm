// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.Target;
import gnu.mapping.Procedure2;

public class IsEq extends Procedure2
    implements Inlineable
{

    Language language;

    public IsEq(Language language1, String s)
    {
        language = language1;
        setName(s);
    }

    public static void compile(Expression aexpression[], Compilation compilation, Target target, Language language1)
    {
        CodeAttr codeattr = compilation.getCode();
        aexpression[0].compile(compilation, Target.pushObject);
        aexpression[1].compile(compilation, Target.pushObject);
        if (target instanceof ConditionalTarget)
        {
            ConditionalTarget conditionaltarget = (ConditionalTarget)target;
            if (conditionaltarget.trueBranchComesFirst)
            {
                codeattr.emitGotoIfNE(conditionaltarget.ifFalse);
            } else
            {
                codeattr.emitGotoIfEq(conditionaltarget.ifTrue);
            }
            conditionaltarget.emitGotoFirstBranch(codeattr);
            return;
        }
        codeattr.emitIfEq();
        Object obj;
        if (target.getType() instanceof ClassType)
        {
            Object obj1 = language1.booleanObject(true);
            Object obj2 = language1.booleanObject(false);
            compilation.compileConstant(obj1, Target.pushObject);
            codeattr.emitElse();
            compilation.compileConstant(obj2, Target.pushObject);
            if ((obj1 instanceof Boolean) && (obj2 instanceof Boolean))
            {
                obj = Compilation.scmBooleanType;
            } else
            {
                obj = Type.pointer_type;
            }
        } else
        {
            codeattr.emitPushInt(1);
            codeattr.emitElse();
            codeattr.emitPushInt(0);
            obj = language1.getTypeFor(Boolean.TYPE);
        }
        codeattr.emitFi();
        target.compileFromStack(compilation, ((Type) (obj)));
    }

    public boolean apply(Object obj, Object obj1)
    {
        return obj == obj1;
    }

    public Object apply2(Object obj, Object obj1)
    {
        Language language1 = language;
        boolean flag;
        if (obj == obj1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return language1.booleanObject(flag);
    }

    public void compile(ApplyExp applyexp, Compilation compilation, Target target)
    {
        compile(applyexp.getArgs(), compilation, target, language);
    }

    public Type getReturnType(Expression aexpression[])
    {
        return language.getTypeFor(Boolean.TYPE);
    }
}
