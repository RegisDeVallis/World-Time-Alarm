// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.util.List;
import java.util.Vector;

// Referenced classes of package gnu.bytecode:
//            Type, CodeAttr, ClassType, ArrayType, 
//            Field, Method, Filter

public class ObjectType extends Type
{

    static final int ADD_ENCLOSING_DONE = 8;
    static final int ADD_FIELDS_DONE = 1;
    static final int ADD_MEMBERCLASSES_DONE = 4;
    static final int ADD_METHODS_DONE = 2;
    static final int EXISTING_CLASS = 16;
    static final int HAS_OUTER_LINK = 32;
    public int flags;

    protected ObjectType()
    {
        size = 4;
    }

    public ObjectType(String s)
    {
        this_name = s;
        size = 4;
    }

    public static Class getContextClass(String s)
        throws ClassNotFoundException
    {
        Class class1;
        try
        {
            class1 = Class.forName(s, false, gnu/bytecode/ObjectType.getClassLoader());
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            return Class.forName(s, false, getContextClassLoader());
        }
        return class1;
    }

    public static ClassLoader getContextClassLoader()
    {
        ClassLoader classloader;
        try
        {
            classloader = ClassLoader.getSystemClassLoader();
        }
        catch (SecurityException securityexception)
        {
            return gnu/bytecode/ObjectType.getClassLoader();
        }
        return classloader;
    }

    public Object coerceFromObject(Object obj)
    {
        if (obj != null)
        {
            if (this == Type.toStringType)
            {
                obj = obj.toString();
            } else
            {
                Class class1 = getReflectClass();
                Class class2 = obj.getClass();
                if (!class1.isAssignableFrom(class2))
                {
                    throw new ClassCastException((new StringBuilder()).append("don't know how to coerce ").append(class2.getName()).append(" to ").append(getName()).toString());
                }
            }
        }
        return obj;
    }

    public int compare(Type type)
    {
        return type != nullType ? -1 : 0;
    }

    public void emitCoerceFromObject(CodeAttr codeattr)
    {
        if (this == Type.toStringType)
        {
            codeattr.emitDup();
            codeattr.emitIfNull();
            codeattr.emitPop(1);
            codeattr.emitPushNull();
            codeattr.emitElse();
            codeattr.emitInvokeVirtual(Type.toString_method);
            codeattr.emitFi();
        } else
        if (this != Type.objectType)
        {
            codeattr.emitCheckcast(this);
            return;
        }
    }

    public Field getField(String s, int i)
    {
        return null;
    }

    public Type getImplementationType()
    {
        if (this == nullType)
        {
            this = objectType;
        } else
        if (this == toStringType)
        {
            return javalangStringType;
        }
        return this;
    }

    public String getInternalName()
    {
        return getName().replace('.', '/');
    }

    public Method getMethod(String s, Type atype[])
    {
        return Type.objectType.getMethod(s, atype);
    }

    public int getMethods(Filter filter, int i, List list)
    {
        return Type.objectType.getMethods(filter, i, list);
    }

    public final int getMethods(Filter filter, int i, Vector vector, String s)
    {
        return Type.objectType.getMethods(filter, i, vector, s);
    }

    public Class getReflectClass()
    {
        try
        {
            if (reflectClass == null)
            {
                reflectClass = getContextClass(getInternalName().replace('/', '.'));
            }
            flags = 0x10 | flags;
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            if ((0x10 & flags) != 0)
            {
                RuntimeException runtimeexception = new RuntimeException((new StringBuilder()).append("no such class: ").append(getName()).toString());
                runtimeexception.initCause(classnotfoundexception);
                throw runtimeexception;
            }
        }
        return reflectClass;
    }

    public final boolean isExisting()
    {
        boolean flag;
label0:
        {
            Type type = getImplementationType();
            if (type instanceof ArrayType)
            {
                type = ((ArrayType)type).getComponentType();
            }
            if (type == this)
            {
                return (0x10 & flags) != 0;
            }
            if (type instanceof ObjectType)
            {
                boolean flag1 = ((ObjectType)type).isExisting();
                flag = false;
                if (!flag1)
                {
                    break label0;
                }
            }
            flag = true;
        }
        return flag;
    }

    public boolean isInstance(Object obj)
    {
        if (this == nullType)
        {
            return obj == null;
        } else
        {
            return super.isInstance(obj);
        }
    }

    public Type promote()
    {
        if (this == nullType)
        {
            this = objectType;
        }
        return this;
    }

    public final void setExisting(boolean flag)
    {
        if (flag)
        {
            flags = 0x10 | flags;
            return;
        } else
        {
            flags = 0xffffffef & flags;
            return;
        }
    }
}
