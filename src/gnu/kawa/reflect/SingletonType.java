// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.mapping.Values;

// Referenced classes of package gnu.kawa.reflect:
//            OccurrenceType

public class SingletonType extends ObjectType
{

    static final SingletonType instance = new SingletonType("singleton");

    public SingletonType(String s)
    {
        super(s);
    }

    public static Object coerceToSingleton(Object obj)
    {
        if (obj instanceof Values)
        {
            obj = ((Values)obj).canonicalize();
        }
        if (obj == null || (obj instanceof Values))
        {
            throw new ClassCastException("value is not a singleton");
        } else
        {
            return obj;
        }
    }

    public static final SingletonType getInstance()
    {
        return instance;
    }

    public Object coerceFromObject(Object obj)
    {
        return coerceToSingleton(obj);
    }

    public int compare(Type type)
    {
        byte byte0 = -1;
        int i = OccurrenceType.itemCountRange(type);
        int j = i & 0xfff;
        int k = i >> 12;
        if (k == 0 || j > 1)
        {
            byte0 = -3;
        } else
        {
            if (j == 1 && k == 1)
            {
                return Type.pointer_type.compare(type);
            }
            int l = Type.pointer_type.compare(type);
            if (l != 0 && l != byte0)
            {
                return -2;
            }
        }
        return byte0;
    }

    public void emitCoerceFromObject(CodeAttr codeattr)
    {
        codeattr.emitInvokeStatic(ClassType.make("gnu.kawa.reflect.SingletonType").getDeclaredMethod("coerceToSingleton", 1));
    }

    public Type getImplementationType()
    {
        return Type.pointer_type;
    }

    public Class getReflectClass()
    {
        return getImplementationType().getReflectClass();
    }

    public boolean isInstance(Object obj)
    {
        return obj != null && !(obj instanceof Values);
    }

}
