// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;

public class InstanceOf extends Procedure2
    implements Inlineable
{

    static Method instanceMethod;
    static ClassType typeType;
    protected Language language;

    public InstanceOf(Language language1)
    {
        language = language1;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplyInstanceOf");
    }

    public InstanceOf(Language language1, String s)
    {
        this(language1);
        setName(s);
    }

    public static void emitIsInstance(TypeValue typevalue, Variable variable, Compilation compilation, Target target)
    {
        CodeAttr codeattr = compilation.getCode();
        typevalue.emitTestIf(null, null, compilation);
        ConditionalTarget conditionaltarget;
        if (target instanceof ConditionalTarget)
        {
            conditionaltarget = (ConditionalTarget)target;
            codeattr.emitGoto(conditionaltarget.ifTrue);
        } else
        {
            codeattr.emitPushInt(1);
            conditionaltarget = null;
        }
        codeattr.emitElse();
        if (conditionaltarget != null)
        {
            codeattr.emitGoto(conditionaltarget.ifFalse);
        } else
        {
            codeattr.emitPushInt(0);
        }
        codeattr.emitFi();
        if (conditionaltarget == null)
        {
            target.compileFromStack(compilation, compilation.getLanguage().getTypeFor(Boolean.TYPE));
        }
    }

    public Object apply2(Object obj, Object obj1)
    {
        Object obj2 = language.asType(obj1);
        if (obj2 instanceof PrimType)
        {
            obj2 = ((PrimType)obj2).boxedType();
        }
        return language.booleanObject(((Type) (obj2)).isInstance(obj));
    }

    public void compile(ApplyExp applyexp, Compilation compilation, Target target)
    {
        Expression aexpression[];
        CodeAttr codeattr;
        Object obj;
        aexpression = applyexp.getArgs();
        codeattr = compilation.getCode();
        Expression expression = aexpression[1];
        if (expression instanceof QuoteExp)
        {
label0:
            {
                Type type;
                try
                {
                    type = language.asType(((QuoteExp)expression).getValue());
                }
                catch (Exception exception)
                {
                    compilation.error('w', (new StringBuilder()).append("unknown type spec: ").append(null).toString());
                    obj = null;
                    continue; /* Loop/switch isn't completed */
                }
                obj = type;
                break label0;
            }
        }
        obj = language.getTypeFor(expression);
        if (true) goto _L2; else goto _L1
_L2:
        if (obj == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (obj instanceof PrimType)
        {
            obj = ((PrimType)obj).boxedType();
        }
        aexpression[0].compile(compilation, Target.pushObject);
        if (obj instanceof TypeValue)
        {
            ((TypeValue)obj).emitIsInstance(null, compilation, target);
            return;
        }
        ((Type) (obj)).emitIsInstance(codeattr);
        compilation.usedClass(((Type) (obj)));
_L4:
        target.compileFromStack(compilation, language.getTypeFor(Boolean.TYPE));
        return;
_L1:
        if (typeType == null)
        {
            typeType = ClassType.make("gnu.bytecode.Type");
            instanceMethod = typeType.addMethod("isInstance", Compilation.apply1args, Type.boolean_type, 1);
        }
        aexpression[1].compile(compilation, typeType);
        aexpression[0].compile(compilation, Target.pushObject);
        codeattr.emitInvokeVirtual(instanceMethod);
        if (true) goto _L4; else goto _L3
_L3:
    }

    public Type getReturnType(Expression aexpression[])
    {
        return language.getTypeFor(Boolean.TYPE);
    }
}
