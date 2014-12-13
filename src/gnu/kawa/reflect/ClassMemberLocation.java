// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.Symbol;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrappedException;
import java.lang.reflect.Field;

// Referenced classes of package gnu.kawa.reflect:
//            StaticFieldLocation, FieldLocation

public abstract class ClassMemberLocation extends Location
{

    Object instance;
    String mname;
    Field rfield;
    ClassType type;

    public ClassMemberLocation(Object obj, ClassType classtype, String s)
    {
        instance = obj;
        type = classtype;
        mname = s;
    }

    public ClassMemberLocation(Object obj, Class class1, String s)
    {
        instance = obj;
        type = (ClassType)Type.make(class1);
        mname = s;
    }

    public ClassMemberLocation(Object obj, Field field)
    {
        instance = obj;
        rfield = field;
        mname = field.getName();
    }

    public static void define(Object obj, Field field, String s, Language language, Environment environment)
        throws IllegalAccessException
    {
        Object obj1 = field.get(obj);
        Type type1 = Type.make(field.getType());
        boolean flag = type1.isSubtype(Compilation.typeLocation);
        type1.isSubtype(Compilation.typeProcedure);
        int i = field.getModifiers();
        boolean flag1;
        Object obj2;
        Symbol symbol;
        Object obj3;
        Object obj4;
        if ((i & 0x10) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 && (obj1 instanceof Named) && !flag)
        {
            obj2 = ((Named)obj1).getSymbol();
        } else
        {
            obj2 = Compilation.demangleName(field.getName(), true);
        }
        if (obj2 instanceof Symbol)
        {
            symbol = (Symbol)obj2;
        } else
        {
            if (s == null)
            {
                s = "";
            }
            String s1 = obj2.toString().intern();
            symbol = Symbol.make(s, s1);
        }
        obj3 = null;
        if (flag && flag1)
        {
            obj4 = (Location)obj1;
        } else
        {
            obj3 = null;
            if (flag1)
            {
                obj3 = language.getEnvPropertyFor(field, obj1);
            }
            boolean flag2;
            if ((i & 8) != 0)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (flag2)
            {
                obj4 = new StaticFieldLocation(field);
            } else
            {
                obj4 = new FieldLocation(obj, field);
            }
        }
        environment.addLocation(symbol, obj3, ((Location) (obj4)));
    }

    public static void defineAll(Object obj, Language language, Environment environment)
        throws IllegalAccessException
    {
        Field afield[] = obj.getClass().getFields();
        int i = afield.length;
        do
        {
            if (--i < 0)
            {
                break;
            }
            Field field = afield[i];
            String s = field.getName();
            if (!s.startsWith("$Prvt$") && !s.endsWith("$instance"))
            {
                define(obj, field, null, language, environment);
            }
        } while (true);
    }

    public Object get(Object obj)
    {
        Field field = getRField();
        if (field == null)
        {
            return obj;
        }
        Object obj1;
        try
        {
            obj1 = field.get(instance);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw WrappedException.wrapIfNeeded(illegalaccessexception);
        }
        return obj1;
    }

    public ClassType getDeclaringClass()
    {
        return type;
    }

    public final Object getInstance()
    {
        return instance;
    }

    public String getMemberName()
    {
        return mname;
    }

    public Class getRClass()
    {
        Field field = rfield;
        if (field != null)
        {
            return field.getDeclaringClass();
        }
        Class class1;
        try
        {
            class1 = type.getReflectClass();
        }
        catch (Exception exception)
        {
            return null;
        }
        return class1;
    }

    public Field getRField()
    {
        Field field = rfield;
        if (field == null)
        {
            try
            {
                field = type.getReflectClass().getField(mname);
                rfield = field;
            }
            catch (Exception exception)
            {
                return null;
            }
        }
        return field;
    }

    public boolean isBound()
    {
        return getRField() != null;
    }

    public boolean isConstant()
    {
        return getRField() != null && (0x10 & rfield.getModifiers()) != 0;
    }

    public void set(Object obj)
    {
        setup();
        try
        {
            rfield.set(instance, obj);
            return;
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw WrappedException.wrapIfNeeded(illegalaccessexception);
        }
    }

    public final void setInstance(Object obj)
    {
        instance = obj;
    }

    void setup()
    {
        if (rfield != null)
        {
            break MISSING_BLOCK_LABEL_29;
        }
        Class class1;
        try
        {
            class1 = type.getReflectClass();
        }
        catch (RuntimeException runtimeexception)
        {
            UnboundLocationException unboundlocationexception = new UnboundLocationException(null, (new StringBuilder()).append("Unbound location - ").append(runtimeexception.toString()).toString());
            unboundlocationexception.initCause(runtimeexception);
            throw unboundlocationexception;
        }
        rfield = class1.getField(mname);
        return;
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
        UnboundLocationException unboundlocationexception1 = new UnboundLocationException(null, (new StringBuilder()).append("Unbound location  - no field ").append(mname).append(" in ").append(type.getName()).toString());
        unboundlocationexception1.initCause(nosuchfieldexception);
        throw unboundlocationexception1;
    }
}
