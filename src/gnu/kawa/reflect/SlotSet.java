// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.CheckedTarget;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.lists.FString;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure3;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import java.lang.reflect.InvocationTargetException;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.reflect:
//            SlotGet

public class SlotSet extends Procedure3
    implements Inlineable
{

    public static final SlotSet set$Mnfield$Ex = new SlotSet("set-field!", false);
    public static final SlotSet set$Mnstatic$Mnfield$Ex = new SlotSet("set-static-field!", true);
    public static final SlotSet setFieldReturnObject;
    static final Type type1Array[] = new Type[1];
    boolean isStatic;
    boolean returnSelf;

    public SlotSet(String s, boolean flag)
    {
        super(s);
        isStatic = flag;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplySlotSet");
    }

    public static void apply(boolean flag, Object obj, Object obj1, Object obj2)
    {
        Language language;
        String s;
        String s1;
        Class class1;
        java.lang.reflect.Field field1;
        language = Language.getDefaultLanguage();
        if ((obj1 instanceof String) || (obj1 instanceof FString) || (obj1 instanceof Symbol))
        {
            s = obj1.toString();
            s1 = Compilation.mangleNameIfNeeded(s);
            if (flag)
            {
                class1 = SlotGet.coerceToClass(obj);
            } else
            {
                class1 = obj.getClass();
            }
        } else
        {
            s = ((Member)obj1).getName();
            s1 = s;
            class1 = null;
        }
        if (!(obj1 instanceof Field)) goto _L2; else goto _L1
_L1:
        field1 = ((Field)obj1).getReflectField();
_L3:
        field1.set(obj, language.coerceFromObject(field1.getType(), obj2));
        return;
_L2:
        java.lang.reflect.Field field = class1.getField(s1);
        field1 = field;
          goto _L3
        IllegalAccessException illegalaccessexception1;
        illegalaccessexception1;
        boolean flag1 = true;
_L15:
        boolean flag2 = obj1 instanceof Method;
        if (!flag2) goto _L5; else goto _L4
_L4:
        String s3 = s1;
_L8:
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_171;
        }
        boolean flag3 = s3.startsWith("set");
        if (!flag3)
        {
            flag2 = false;
        }
        if (!flag2) goto _L7; else goto _L6
_L6:
        String s7 = (new StringBuilder()).append("get").append(s3.substring(3)).toString();
_L9:
        java.lang.reflect.Method method2 = class1.getMethod(s7, SlotGet.noClasses);
        java.lang.reflect.Method method = method2;
_L12:
        String s2;
        Exception exception;
        String s4;
        String s5;
        String s6;
        try
        {
            Class aclass[] = new Class[1];
            aclass[0] = method.getReturnType();
            java.lang.reflect.Method method1 = class1.getMethod(s3, aclass);
            Object aobj[] = new Object[1];
            aobj[0] = language.coerceFromObject(aclass[0], obj2);
            method1.invoke(obj, aobj);
            return;
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            throw WrappedException.wrapIfNeeded(invocationtargetexception.getTargetException());
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            flag1 = true;
        }
        catch (NoSuchMethodException nosuchmethodexception) { }
        break MISSING_BLOCK_LABEL_386;
_L5:
        s2 = ClassExp.slotToMethodName("set", s);
        s3 = s2;
          goto _L8
_L7:
        s6 = ClassExp.slotToMethodName("get", s);
        s7 = s6;
          goto _L9
        exception;
        if (!flag2) goto _L11; else goto _L10
_L10:
        s5 = (new StringBuilder()).append("is").append(s3.substring(3)).toString();
_L13:
        method = class1.getMethod(s5, SlotGet.noClasses);
          goto _L12
_L11:
        s4 = ClassExp.slotToMethodName("is", s);
        s5 = s4;
          goto _L13
        if (flag1)
        {
            throw new RuntimeException((new StringBuilder()).append("illegal access for field ").append(s).toString());
        } else
        {
            throw new RuntimeException((new StringBuilder()).append("no such field ").append(s).append(" in ").append(class1.getName()).toString());
        }
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
        flag1 = false;
        if (true) goto _L15; else goto _L14
_L14:
    }

    static void compileSet(Procedure procedure, ObjectType objecttype, Expression expression, Object obj, Compilation compilation)
    {
        CodeAttr codeattr;
        Language language;
        boolean flag;
        Field field;
        codeattr = compilation.getCode();
        language = compilation.getLanguage();
        boolean flag2;
        Type type;
        if ((procedure instanceof SlotSet) && ((SlotSet)procedure).isStatic)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!(obj instanceof Field)) goto _L2; else goto _L1
_L1:
        field = (Field)obj;
        flag2 = field.getStaticFlag();
        type = language.getLangTypeFor(field.getType());
        if (flag && !flag2)
        {
            compilation.error('e', (new StringBuilder()).append("cannot access non-static field `").append(field.getName()).append("' using `").append(procedure.getName()).append('\'').toString());
        }
        expression.compile(compilation, CheckedTarget.getInstance(type));
        if (!flag2) goto _L4; else goto _L3
_L3:
        codeattr.emitPutStatic(field);
_L6:
        return;
_L4:
        codeattr.emitPutField(field);
        return;
_L2:
        if (obj instanceof Method)
        {
            Method method = (Method)obj;
            boolean flag1 = method.getStaticFlag();
            if (flag && !flag1)
            {
                compilation.error('e', (new StringBuilder()).append("cannot call non-static getter method `").append(method.getName()).append("' using `").append(procedure.getName()).append('\'').toString());
            }
            expression.compile(compilation, CheckedTarget.getInstance(language.getLangTypeFor(method.getParameterTypes()[0])));
            if (flag1)
            {
                codeattr.emitInvokeStatic(method);
            } else
            {
                codeattr.emitInvoke(method);
            }
            if (!method.getReturnType().isVoid())
            {
                codeattr.emitPop(1);
                return;
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static Member lookupMember(ObjectType objecttype, String s, ClassType classtype)
    {
        Field field = objecttype.getField(Compilation.mangleNameIfNeeded(s), -1);
        if (field == null) goto _L2; else goto _L1
_L1:
        if (classtype == null)
        {
            classtype = Type.pointer_type;
        }
        if (!classtype.isAccessible(field, objecttype)) goto _L2; else goto _L3
_L3:
        Method method;
        return field;
_L2:
        if ((method = objecttype.getMethod(ClassExp.slotToMethodName("set", s), type1Array)) != null)
        {
            return method;
        }
        if (true) goto _L3; else goto _L4
_L4:
    }

    public static void setField(Object obj, String s, Object obj1)
    {
        apply(false, obj, s, obj1);
    }

    public static void setStaticField(Object obj, String s, Object obj1)
    {
        apply(true, obj, s, obj1);
    }

    public Object apply3(Object obj, Object obj1, Object obj2)
    {
        apply(isStatic, obj, obj1, obj2);
        if (returnSelf)
        {
            return obj;
        } else
        {
            return Values.empty;
        }
    }

    public void compile(ApplyExp applyexp, Compilation compilation, Target target)
    {
        Expression aexpression[] = applyexp.getArgs();
        int i = aexpression.length;
        if (i != 3)
        {
            String s1;
            if (i < 3)
            {
                s1 = "too few";
            } else
            {
                s1 = "too many";
            }
            compilation.error('e', (new StringBuilder()).append(s1).append(" arguments to `").append(getName()).append('\'').toString());
            compilation.compileConstant(null, target);
            return;
        }
        Expression expression = aexpression[0];
        Expression expression1 = aexpression[1];
        Expression _tmp = aexpression[2];
        Type type;
        if (isStatic)
        {
            type = Scheme.exp2Type(expression);
        } else
        {
            type = expression.getType();
        }
        if ((type instanceof ObjectType) && (expression1 instanceof QuoteExp))
        {
            Object obj = ((QuoteExp)expression1).getValue();
            ObjectType objecttype = (ObjectType)type;
            ClassType classtype;
            String s;
            Member member;
            if (compilation.curClass != null)
            {
                classtype = compilation.curClass;
            } else
            {
                classtype = compilation.mainClass;
            }
            if ((obj instanceof String) || (obj instanceof FString) || (obj instanceof Symbol))
            {
                s = obj.toString();
                member = lookupMember(objecttype, s, classtype);
                if (member == null && type != Type.pointer_type && compilation.warnUnknownMember())
                {
                    compilation.error('w', (new StringBuilder()).append("no slot `").append(s).append("' in ").append(objecttype.getName()).toString());
                }
            } else
            if (obj instanceof Member)
            {
                member = (Member)obj;
                s = member.getName();
            } else
            {
                s = null;
                member = null;
            }
            if (member != null)
            {
                boolean flag;
                Expression expression2;
                Target target1;
                if ((8 & member.getModifiers()) != 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (classtype != null && !classtype.isAccessible(member, objecttype))
                {
                    compilation.error('e', (new StringBuilder()).append("slot '").append(s).append("' in ").append(member.getDeclaringClass().getName()).append(" not accessible here").toString());
                }
                expression2 = aexpression[0];
                if (flag)
                {
                    target1 = Target.Ignore;
                } else
                {
                    target1 = Target.pushValue(objecttype);
                }
                expression2.compile(compilation, target1);
                if (returnSelf)
                {
                    compilation.getCode().emitDup(objecttype.getImplementationType());
                }
                compileSet(this, objecttype, aexpression[2], member, compilation);
                if (returnSelf)
                {
                    target.compileFromStack(compilation, objecttype);
                    return;
                } else
                {
                    compilation.compileConstant(Values.empty, target);
                    return;
                }
            }
        }
        ApplyExp.compile(applyexp, compilation, target);
    }

    static 
    {
        setFieldReturnObject = new SlotSet("set-field-return-object!", false);
        setFieldReturnObject.returnSelf = true;
    }
}
