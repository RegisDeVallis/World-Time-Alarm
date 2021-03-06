// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import gnu.kawa.util.AbstractWeakHashTable;
import java.io.PrintWriter;
import java.util.HashMap;

// Referenced classes of package gnu.bytecode:
//            PrimType, ObjectType, ClassType, Method, 
//            ArrayType, CodeAttr

public abstract class Type
    implements java.lang.reflect.Type
{
    static class ClassToTypeMap extends AbstractWeakHashTable
    {

        protected Class getKeyFromValue(Type type)
        {
            return type.reflectClass;
        }

        protected volatile Object getKeyFromValue(Object obj)
        {
            return getKeyFromValue((Type)obj);
        }

        protected boolean matches(Class class1, Class class2)
        {
            return class1 == class2;
        }

        ClassToTypeMap()
        {
        }
    }


    public static final PrimType booleanType;
    public static final Method booleanValue_method;
    public static final ClassType boolean_ctype;
    public static final PrimType boolean_type;
    public static final PrimType byteType;
    public static final PrimType byte_type;
    public static final PrimType charType;
    public static final PrimType char_type;
    public static final Method clone_method;
    public static final PrimType doubleType;
    public static final Method doubleValue_method;
    public static final PrimType double_type;
    public static final ObjectType errorType = new ClassType("(error type)");
    public static final PrimType floatType;
    public static final Method floatValue_method;
    public static final PrimType float_type;
    public static final PrimType intType;
    public static final Method intValue_method;
    public static final PrimType int_type;
    public static final ClassType java_lang_Class_type;
    public static final ClassType javalangBooleanType;
    public static final ClassType javalangClassType;
    public static final ClassType javalangNumberType;
    public static final ClassType javalangObjectType;
    public static ClassType javalangStringType;
    public static final ClassType javalangThrowableType;
    public static final PrimType longType;
    public static final Method longValue_method;
    public static final PrimType long_type;
    static ClassToTypeMap mapClassToType;
    static HashMap mapNameToType;
    public static final PrimType neverReturnsType;
    public static final ObjectType nullType = new ObjectType("(type of null)");
    public static final ClassType number_type;
    public static final ClassType objectType;
    public static final ClassType pointer_type;
    public static final PrimType shortType;
    public static final PrimType short_type;
    public static final ClassType string_type;
    public static final ClassType throwable_type;
    public static final ClassType toStringType;
    public static final Method toString_method;
    public static final ClassType tostring_type;
    public static final Type typeArray0[];
    public static final PrimType voidType;
    public static final PrimType void_type;
    ArrayType array_type;
    protected Class reflectClass;
    String signature;
    int size;
    String this_name;

    protected Type()
    {
    }

    public Type(Type type)
    {
        this_name = type.this_name;
        signature = type.signature;
        size = type.size;
        reflectClass = type.reflectClass;
    }

    Type(String s, String s1)
    {
        this_name = s;
        signature = s1;
    }

    public static Type getType(String s)
    {
        HashMap hashmap = mapNameToType;
        hashmap;
        JVM INSTR monitorenter ;
        Object obj = (Type)hashmap.get(s);
        if (obj != null) goto _L2; else goto _L1
_L1:
        if (!s.endsWith("[]")) goto _L4; else goto _L3
_L3:
        obj = ArrayType.make(s);
_L5:
        hashmap.put(s, obj);
_L2:
        hashmap;
        JVM INSTR monitorexit ;
        return ((Type) (obj));
_L4:
        ClassType classtype;
        classtype = new ClassType(s);
        classtype.flags = 0x10 | classtype.flags;
        obj = classtype;
          goto _L5
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static boolean isMoreSpecific(Type atype[], Type atype1[])
    {
        if (atype.length != atype1.length)
        {
            return false;
        }
        for (int i = atype.length; --i >= 0;)
        {
            if (!atype[i].isSubtype(atype1[i]))
            {
                return false;
            }
        }

        return true;
    }

    public static boolean isValidJavaTypeName(String s)
    {
_L2:
        return false;
        boolean flag = false;
        int i;
        for (i = s.length(); i > 2 && s.charAt(i - 1) == ']' && s.charAt(i - 2) == '['; i -= 2) { }
        int j = 0;
        while (j < i) 
        {
            char c = s.charAt(j);
            if (c == '.')
            {
                if (!flag)
                {
                    break MISSING_BLOCK_LABEL_100;
                }
                flag = false;
            } else
            if (flag ? Character.isJavaIdentifierPart(c) : Character.isJavaIdentifierStart(c))
            {
                flag = true;
            } else
            {
                break MISSING_BLOCK_LABEL_100;
            }
            j++;
        }
        if (j == i)
        {
            return true;
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static Type lookupType(String s)
    {
        Type type;
        synchronized (mapNameToType)
        {
            type = (Type)hashmap.get(s);
        }
        return type;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static Type lowestCommonSuperType(Type type, Type type1)
    {
        if (type != neverReturnsType) goto _L2; else goto _L1
_L1:
        type = type1;
_L4:
        return type;
_L2:
        if (type1 == neverReturnsType)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (type == null || type1 == null)
        {
            return null;
        }
        if (!(type instanceof PrimType) || !(type1 instanceof PrimType))
        {
            break; /* Loop/switch isn't completed */
        }
        if (type != type1)
        {
            type = ((PrimType)type).promotedType();
            if (type != ((PrimType)type1).promotedType())
            {
                return null;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (type.isSubtype(type1))
        {
            return type1;
        }
        if (!type1.isSubtype(type))
        {
            if (!(type instanceof ClassType) || !(type1 instanceof ClassType))
            {
                return objectType;
            }
            ClassType classtype = (ClassType)type;
            ClassType classtype1 = (ClassType)type1;
            if (classtype.isInterface() || classtype1.isInterface())
            {
                return objectType;
            } else
            {
                return lowestCommonSuperType(((Type) (classtype.getSuperclass())), ((Type) (classtype1.getSuperclass())));
            }
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public static Type make(Class class1)
    {
        gnu/bytecode/Type;
        JVM INSTR monitorenter ;
        if (mapClassToType == null) goto _L2; else goto _L1
_L1:
        Object obj1 = (Type)mapClassToType.get(class1);
        if (obj1 == null) goto _L2; else goto _L3
_L3:
        gnu/bytecode/Type;
        JVM INSTR monitorexit ;
        return ((Type) (obj1));
_L2:
        if (!class1.isArray()) goto _L5; else goto _L4
_L4:
        Object obj = ArrayType.make(make(class1.getComponentType()));
_L6:
        registerTypeForClass(class1, ((Type) (obj)));
        obj1 = obj;
          goto _L3
_L5:
        if (class1.isPrimitive())
        {
            throw new Error("internal error - primitive type not found");
        }
        break MISSING_BLOCK_LABEL_88;
        Exception exception;
        exception;
        gnu/bytecode/Type;
        JVM INSTR monitorexit ;
        throw exception;
        String s = class1.getName();
        HashMap hashmap = mapNameToType;
        hashmap;
        JVM INSTR monitorenter ;
        obj = (Type)hashmap.get(s);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_131;
        }
        if (((Type) (obj)).reflectClass == class1 || ((Type) (obj)).reflectClass == null)
        {
            break MISSING_BLOCK_LABEL_168;
        }
        ClassType classtype;
        classtype = new ClassType(s);
        classtype.flags = 0x10 | classtype.flags;
        obj = classtype;
        mapNameToType.put(s, obj);
        hashmap;
        JVM INSTR monitorexit ;
          goto _L6
        Exception exception1;
        exception1;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception1;
          goto _L3
    }

    public static void printSignature(String s, int i, int j, PrintWriter printwriter)
    {
        if (j != 0)
        {
            char c = s.charAt(i);
            if (j == 1)
            {
                PrimType primtype = signatureToPrimitive(c);
                if (primtype != null)
                {
                    printwriter.print(primtype.getName());
                    return;
                }
            } else
            {
                if (c == '[')
                {
                    printSignature(s, i + 1, j - 1, printwriter);
                    printwriter.print("[]");
                    return;
                }
                if (c == 'L' && j > 2 && s.indexOf(';', i) == i + (j - 1))
                {
                    printwriter.print(s.substring(i + 1, i + (j - 1)).replace('/', '.'));
                    return;
                } else
                {
                    printwriter.append(s, i, j - i);
                    return;
                }
            }
        }
    }

    public static void registerTypeForClass(Class class1, Type type)
    {
        gnu/bytecode/Type;
        JVM INSTR monitorenter ;
        ClassToTypeMap classtotypemap = mapClassToType;
        if (classtotypemap != null)
        {
            break MISSING_BLOCK_LABEL_23;
        }
        classtotypemap = new ClassToTypeMap();
        mapClassToType = classtotypemap;
        type.reflectClass = class1;
        classtotypemap.put(class1, type);
        gnu/bytecode/Type;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static int signatureLength(String s)
    {
        return signatureLength(s, 0);
    }

    public static int signatureLength(String s, int i)
    {
        if (s.length() > i)
        {
            char c = s.charAt(i);
            int j = 0;
            for (; c == '['; c = s.charAt(i))
            {
                j++;
                i++;
            }

            if (signatureToPrimitive(c) != null)
            {
                return j + 1;
            }
            if (c == 'L')
            {
                int k = s.indexOf(';', i);
                if (k > 0)
                {
                    return (1 + (j + k)) - i;
                }
            }
        }
        return -1;
    }

    public static String signatureToName(String s)
    {
        int i = s.length();
        if (i != 0) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        char c;
        int j;
        String s1;
        c = s.charAt(0);
        if (i == 1)
        {
            PrimType primtype = signatureToPrimitive(c);
            if (primtype != null)
            {
                return primtype.getName();
            }
        }
        if (c != '[')
        {
            continue; /* Loop/switch isn't completed */
        }
        j = 1;
        if (j < i && s.charAt(j) == '[')
        {
            j++;
        }
        s1 = signatureToName(s.substring(j));
        if (s1 == null) goto _L1; else goto _L3
_L3:
        StringBuffer stringbuffer = new StringBuffer(50);
        stringbuffer.append(s1);
        while (--j >= 0) 
        {
            stringbuffer.append("[]");
        }
        return stringbuffer.toString();
        if (c != 'L' || i <= 2 || s.indexOf(';') != i - 1) goto _L1; else goto _L4
_L4:
        return s.substring(1, i - 1).replace('/', '.');
    }

    public static PrimType signatureToPrimitive(char c)
    {
        switch (c)
        {
        default:
            return null;

        case 66: // 'B'
            return byteType;

        case 67: // 'C'
            return charType;

        case 68: // 'D'
            return doubleType;

        case 70: // 'F'
            return floatType;

        case 83: // 'S'
            return shortType;

        case 73: // 'I'
            return intType;

        case 74: // 'J'
            return longType;

        case 90: // 'Z'
            return booleanType;

        case 86: // 'V'
            return voidType;
        }
    }

    public static Type signatureToType(String s)
    {
        return signatureToType(s, 0, s.length());
    }

    public static Type signatureToType(String s, int i, int j)
    {
        if (j != 0) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        char c;
        Type type;
        c = s.charAt(i);
        if (j == 1)
        {
            PrimType primtype = signatureToPrimitive(c);
            if (primtype != null)
            {
                return primtype;
            }
        }
        if (c != '[')
        {
            continue; /* Loop/switch isn't completed */
        }
        type = signatureToType(s, i + 1, j - 1);
        if (type == null) goto _L1; else goto _L3
_L3:
        return ArrayType.make(type);
        if (c != 'L' || j <= 2 || s.indexOf(';', i) != i + (j - 1)) goto _L1; else goto _L4
_L4:
        return ClassType.make(s.substring(i + 1, i + (j - 1)).replace('/', '.'));
    }

    protected static int swappedCompareResult(int i)
    {
        if (i == 1)
        {
            i = -1;
        } else
        if (i == -1)
        {
            return 1;
        }
        return i;
    }

    public abstract Object coerceFromObject(Object obj);

    public Object coerceToObject(Object obj)
    {
        return obj;
    }

    public abstract int compare(Type type);

    public void emitCoerceFromObject(CodeAttr codeattr)
    {
        throw new Error((new StringBuilder()).append("unimplemented emitCoerceFromObject for ").append(this).toString());
    }

    public void emitCoerceToObject(CodeAttr codeattr)
    {
    }

    public void emitConvertFromPrimitive(Type type, CodeAttr codeattr)
    {
        type.emitCoerceToObject(codeattr);
    }

    public void emitIsInstance(CodeAttr codeattr)
    {
        codeattr.emitInstanceof(this);
    }

    public Type getImplementationType()
    {
        return this;
    }

    public final String getName()
    {
        return this_name;
    }

    public Type getRealType()
    {
        return this;
    }

    public Class getReflectClass()
    {
        return reflectClass;
    }

    public String getSignature()
    {
        return signature;
    }

    public final int getSize()
    {
        return size;
    }

    public int getSizeInWords()
    {
        return size <= 4 ? 1 : 2;
    }

    public int hashCode()
    {
        String s = toString();
        if (s == null)
        {
            return 0;
        } else
        {
            return s.hashCode();
        }
    }

    public boolean isExisting()
    {
        return true;
    }

    public boolean isInstance(Object obj)
    {
        return getReflectClass().isInstance(obj);
    }

    public final boolean isSubtype(Type type)
    {
        int i = compare(type);
        return i == -1 || i == 0;
    }

    public final boolean isVoid()
    {
        return size == 0;
    }

    public Type promote()
    {
        if (size < 4)
        {
            this = intType;
        }
        return this;
    }

    protected void setName(String s)
    {
        this_name = s;
    }

    public void setReflectClass(Class class1)
    {
        reflectClass = class1;
    }

    protected void setSignature(String s)
    {
        signature = s;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Type ").append(getName()).toString();
    }

    static 
    {
        byteType = new PrimType("byte", "B", 1, Byte.TYPE);
        shortType = new PrimType("short", "S", 2, Short.TYPE);
        intType = new PrimType("int", "I", 4, Integer.TYPE);
        longType = new PrimType("long", "J", 8, Long.TYPE);
        floatType = new PrimType("float", "F", 4, Float.TYPE);
        doubleType = new PrimType("double", "D", 8, Double.TYPE);
        booleanType = new PrimType("boolean", "Z", 1, Boolean.TYPE);
        charType = new PrimType("char", "C", 2, Character.TYPE);
        voidType = new PrimType("void", "V", 0, Void.TYPE);
        byte_type = byteType;
        short_type = shortType;
        int_type = intType;
        long_type = longType;
        float_type = floatType;
        double_type = doubleType;
        boolean_type = booleanType;
        char_type = charType;
        void_type = voidType;
        mapNameToType = new HashMap();
        mapNameToType.put("byte", byteType);
        mapNameToType.put("short", shortType);
        mapNameToType.put("int", intType);
        mapNameToType.put("long", longType);
        mapNameToType.put("float", floatType);
        mapNameToType.put("double", doubleType);
        mapNameToType.put("boolean", booleanType);
        mapNameToType.put("char", charType);
        mapNameToType.put("void", voidType);
        neverReturnsType = new PrimType(voidType);
        neverReturnsType.this_name = "(never-returns)";
        javalangStringType = ClassType.make("java.lang.String");
        toStringType = new ClassType("java.lang.String");
        javalangObjectType = ClassType.make("java.lang.Object");
        objectType = javalangObjectType;
        javalangBooleanType = ClassType.make("java.lang.Boolean");
        javalangThrowableType = ClassType.make("java.lang.Throwable");
        typeArray0 = new Type[0];
        toString_method = objectType.getDeclaredMethod("toString", 0);
        javalangNumberType = ClassType.make("java.lang.Number");
        clone_method = Method.makeCloneMethod(objectType);
        intValue_method = javalangNumberType.addMethod("intValue", typeArray0, intType, 1);
        longValue_method = javalangNumberType.addMethod("longValue", typeArray0, longType, 1);
        floatValue_method = javalangNumberType.addMethod("floatValue", typeArray0, floatType, 1);
        doubleValue_method = javalangNumberType.addMethod("doubleValue", typeArray0, doubleType, 1);
        booleanValue_method = javalangBooleanType.addMethod("booleanValue", typeArray0, booleanType, 1);
        javalangClassType = ClassType.make("java.lang.Class");
        pointer_type = javalangObjectType;
        string_type = javalangStringType;
        tostring_type = toStringType;
        java_lang_Class_type = javalangClassType;
        boolean_ctype = javalangBooleanType;
        throwable_type = javalangThrowableType;
        number_type = javalangNumberType;
    }
}
