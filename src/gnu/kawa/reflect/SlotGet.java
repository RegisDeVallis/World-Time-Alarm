// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Member;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Referenced classes of package gnu.kawa.reflect:
//            SlotSet, ClassMethods

public class SlotGet extends Procedure2
    implements HasSetter, Inlineable
{

    public static final SlotGet field;
    static Class noClasses[] = new Class[0];
    public static final SlotGet slotRef;
    public static final SlotGet staticField;
    boolean isStatic;
    Procedure setter;

    public SlotGet(String s, boolean flag)
    {
        super(s);
        isStatic = flag;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplySlotGet");
    }

    public SlotGet(String s, boolean flag, Procedure procedure)
    {
        this(s, flag);
        setter = procedure;
    }

    static Class coerceToClass(Object obj)
    {
        if (obj instanceof Class)
        {
            return (Class)obj;
        }
        if (obj instanceof Type)
        {
            return ((Type)obj).getReflectClass();
        } else
        {
            throw new RuntimeException("argument is neither Class nor Type");
        }
    }

    public static Object field(Object obj, String s)
    {
        return field.apply2(obj, s);
    }

    public static Object getSlotValue(boolean flag, Object obj, String s, String s1, String s2, String s3, Language language)
    {
        Object obj1;
        if (flag)
        {
            obj1 = coerceToClass(obj);
        } else
        {
            obj1 = obj.getClass();
        }
        if (s1 != "length" || !((Class) (obj1)).isArray()) goto _L2; else goto _L1
_L1:
        obj1 = Integer.valueOf(Array.getLength(obj));
_L4:
        return obj1;
_L2:
        if (s1 == "class") goto _L4; else goto _L3
_L3:
        boolean flag1;
        flag1 = false;
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_159;
        }
        Field field2 = ((Class) (obj1)).getField(s1);
        Field field1 = field2;
_L6:
        flag1 = false;
        if (field1 == null)
        {
            break MISSING_BLOCK_LABEL_159;
        }
        if (flag && (8 & field1.getModifiers()) == 0)
        {
            throw new RuntimeException((new StringBuilder()).append("cannot access non-static field '").append(s1).append('\'').toString());
        }
        break; /* Loop/switch isn't completed */
        Exception exception1;
        exception1;
        field1 = null;
        if (true) goto _L6; else goto _L5
_L5:
        Object obj4 = language.coerceToObject(field1.getType(), field1.get(obj));
        return obj4;
        IllegalAccessException illegalaccessexception1;
        illegalaccessexception1;
        flag1 = true;
_L9:
        if (s2 == null) goto _L8; else goto _L7
_L7:
        String s4 = s2;
_L10:
        Method method1 = ((Class) (obj1)).getMethod(s4, noClasses);
        Method method;
        method = method1;
        break MISSING_BLOCK_LABEL_184;
        exception2;
        exception2.printStackTrace();
        flag1 = false;
          goto _L9
_L8:
        s5 = ClassExp.slotToMethodName("get", s);
        s4 = s5;
          goto _L10
        exception;
        if (s3 == null)
        {
            break MISSING_BLOCK_LABEL_297;
        }
        s4 = s3;
_L12:
        method = ((Class) (obj1)).getMethod(s4, noClasses);
          goto _L11
        s4 = ClassExp.slotToMethodName("is", s);
          goto _L12
        Object obj2 = method.invoke(obj, Values.noArgs);
        obj3 = language.coerceToObject(method.getReturnType(), obj2);
        return obj3;
_L11:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_308;
        }
        Exception exception;
        Object obj3;
        String s5;
        Exception exception2;
        try
        {
            if ((8 & method.getModifiers()) == 0)
            {
                throw new RuntimeException((new StringBuilder()).append("cannot call non-static getter method '").append(s4).append('\'').toString());
            }
            break MISSING_BLOCK_LABEL_308;
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
        if (flag1)
        {
            throw new RuntimeException((new StringBuilder()).append("illegal access for field ").append(s1).toString());
        } else
        {
            throw new RuntimeException((new StringBuilder()).append("no such field ").append(s1).append(" in ").append(((Class) (obj1)).getName()).toString());
        }
    }

    public static Member lookupMember(ObjectType objecttype, String s, ClassType classtype)
    {
        gnu.bytecode.Field field1 = objecttype.getField(Compilation.mangleNameIfNeeded(s), -1);
        if (field1 == null) goto _L2; else goto _L1
_L1:
        if (classtype == null)
        {
            classtype = Type.pointer_type;
        }
        if (!classtype.isAccessible(field1, objecttype)) goto _L2; else goto _L3
_L3:
        gnu.bytecode.Method method;
        return field1;
_L2:
        if ((method = objecttype.getMethod(ClassExp.slotToMethodName("get", s), Type.typeArray0)) != null)
        {
            return method;
        }
        if (true) goto _L3; else goto _L4
_L4:
    }

    public static ApplyExp makeGetField(Expression expression, String s)
    {
        Expression aexpression[] = new Expression[2];
        aexpression[0] = expression;
        aexpression[1] = new QuoteExp(s);
        return new ApplyExp(field, aexpression);
    }

    public static Object staticField(Object obj, String s)
    {
        return staticField.apply2(obj, s);
    }

    public Object apply2(Object obj, Object obj1)
    {
        String s = null;
        String s1 = null;
        String s2;
        String s3;
        if (obj1 instanceof gnu.bytecode.Field)
        {
            s3 = ((gnu.bytecode.Field)obj1).getName();
            s2 = Compilation.demangleName(s3, true);
        } else
        if (obj1 instanceof gnu.bytecode.Method)
        {
            String s4 = ((gnu.bytecode.Method)obj1).getName();
            s2 = Compilation.demangleName(s4, false);
            if (s4.startsWith("get"))
            {
                s = s4;
            } else
            {
                boolean flag = s4.startsWith("is");
                s = null;
                s1 = null;
                if (flag)
                {
                    s1 = s4;
                    s = null;
                }
            }
            s3 = null;
        } else
        if ((obj1 instanceof SimpleSymbol) || (obj1 instanceof CharSequence))
        {
            s2 = obj1.toString();
            s3 = Compilation.mangleNameIfNeeded(s2);
            s = null;
            s1 = null;
        } else
        {
            throw new WrongType(this, 2, obj1, "string");
        }
        if ("class".equals(s3))
        {
            s3 = "class";
        } else
        if ("length".equals(s3))
        {
            s3 = "length";
        }
        return getSlotValue(isStatic, obj, s2, s3, s, s1, Language.getDefaultLanguage());
    }

    public void compile(ApplyExp applyexp, Compilation compilation, Target target)
    {
        Expression aexpression[] = applyexp.getArgs();
        Expression expression = aexpression[0];
        Expression expression1 = aexpression[1];
        Language language = compilation.getLanguage();
        Type type;
        CodeAttr codeattr;
        if (isStatic)
        {
            type = language.getTypeFor(expression);
        } else
        {
            type = expression.getType();
        }
        codeattr = compilation.getCode();
        if ((type instanceof ObjectType) && (expression1 instanceof QuoteExp))
        {
            ObjectType objecttype = (ObjectType)type;
            Object obj = ((QuoteExp)expression1).getValue();
            if (obj instanceof gnu.bytecode.Field)
            {
                gnu.bytecode.Field field1 = (gnu.bytecode.Field)obj;
                boolean flag1;
                Expression expression3;
                Target target2;
                if ((8 & field1.getModifiers()) != 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                expression3 = aexpression[0];
                if (flag1)
                {
                    target2 = Target.Ignore;
                } else
                {
                    target2 = Target.pushValue(objecttype);
                }
                expression3.compile(compilation, target2);
                if (flag1)
                {
                    if (true)
                    {
                        codeattr.emitGetStatic(field1);
                    }
                } else
                {
                    codeattr.emitGetField(field1);
                }
                target.compileFromStack(compilation, language.getLangTypeFor(field1.getType()));
                return;
            }
            if (obj instanceof gnu.bytecode.Method)
            {
                gnu.bytecode.Method method = (gnu.bytecode.Method)obj;
                method.getModifiers();
                boolean flag = method.getStaticFlag();
                Expression expression2 = aexpression[0];
                Target target1;
                if (flag)
                {
                    target1 = Target.Ignore;
                } else
                {
                    target1 = Target.pushValue(objecttype);
                }
                expression2.compile(compilation, target1);
                if (flag)
                {
                    codeattr.emitInvokeStatic(method);
                } else
                {
                    codeattr.emitInvoke(method);
                }
                target.compileFromStack(compilation, method.getReturnType());
                return;
            }
        }
        String s = ClassMethods.checkName(expression1);
        if ((type instanceof ArrayType) && "length".equals(s) && !isStatic)
        {
            aexpression[0].compile(compilation, Target.pushValue(type));
            codeattr.emitArrayLength();
            target.compileFromStack(compilation, LangPrimType.intType);
            return;
        } else
        {
            ApplyExp.compile(applyexp, compilation, target);
            return;
        }
    }

    public Type getReturnType(Expression aexpression[])
    {
        if (aexpression.length == 2)
        {
            Expression expression = aexpression[0];
            Expression expression1 = aexpression[1];
            if (expression1 instanceof QuoteExp)
            {
                Object obj = ((QuoteExp)expression1).getValue();
                if (obj instanceof gnu.bytecode.Field)
                {
                    return ((gnu.bytecode.Field)obj).getType();
                }
                if (obj instanceof gnu.bytecode.Method)
                {
                    return ((gnu.bytecode.Method)obj).getReturnType();
                }
                if (!isStatic && (expression.getType() instanceof ArrayType) && "length".equals(ClassMethods.checkName(expression1, true)))
                {
                    return LangPrimType.intType;
                }
            }
        }
        return Type.pointer_type;
    }

    public Procedure getSetter()
    {
        if (setter == null)
        {
            return super.getSetter();
        } else
        {
            return setter;
        }
    }

    public void set2(Object obj, Object obj1, Object obj2)
    {
        SlotSet.apply(isStatic, obj, (String)obj1, obj2);
    }

    public void setN(Object aobj[])
    {
        int i = aobj.length;
        if (i != 3)
        {
            throw new WrongArguments(getSetter(), i);
        } else
        {
            set2(aobj[0], aobj[1], aobj[2]);
            return;
        }
    }

    static 
    {
        field = new SlotGet("field", false, SlotSet.set$Mnfield$Ex);
        slotRef = new SlotGet("slot-ref", false, SlotSet.set$Mnfield$Ex);
        staticField = new SlotGet("static-field", true, SlotSet.set$Mnstatic$Mnfield$Ex);
    }
}
