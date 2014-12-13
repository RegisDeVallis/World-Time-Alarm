// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.lists.FString;
import gnu.mapping.Symbol;
import gnu.mapping.Table2D;
import gnu.mapping.Values;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectOutput;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.IdentityHashMap;
import java.util.regex.Pattern;

// Referenced classes of package gnu.expr:
//            Compilation, Literal, StackTarget

public class LitTable
    implements ObjectOutput
{

    static Table2D staticTable = new Table2D(100);
    Compilation comp;
    IdentityHashMap literalTable;
    Literal literalsChain;
    int literalsCount;
    ClassType mainClass;
    int stackPointer;
    Type typeStack[];
    Object valueStack[];

    public LitTable(Compilation compilation)
    {
        literalTable = new IdentityHashMap(100);
        valueStack = new Object[20];
        typeStack = new Type[20];
        comp = compilation;
        mainClass = compilation.mainClass;
    }

    private void store(Literal literal, boolean flag, CodeAttr codeattr)
    {
        if (literal.field != null)
        {
            if (!flag)
            {
                codeattr.emitDup(literal.type);
            }
            codeattr.emitPutStatic(literal.field);
        }
        literal.flags = 8 | literal.flags;
    }

    public void close()
    {
    }

    public void emit()
        throws IOException
    {
        for (Literal literal = literalsChain; literal != null; literal = literal.next)
        {
            writeObject(literal.value);
        }

        for (Literal literal1 = literalsChain; literal1 != null; literal1 = literal1.next)
        {
            emit(literal1, true);
        }

        literalTable = null;
        literalsCount = 0;
    }

    void emit(Literal literal, boolean flag)
    {
        CodeAttr codeattr = comp.getCode();
        if (literal.value != null) goto _L2; else goto _L1
_L1:
        if (!flag)
        {
            codeattr.emitPushNull();
        }
_L4:
        return;
_L2:
        if (literal.value instanceof String)
        {
            if (!flag)
            {
                codeattr.emitPushString(literal.value.toString());
                return;
            }
            continue; /* Loop/switch isn't completed */
        }
        if ((8 & literal.flags) != 0)
        {
            if (!flag)
            {
                codeattr.emitGetStatic(literal.field);
                return;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (!(literal.value instanceof Object[]))
        {
            break; /* Loop/switch isn't completed */
        }
        int j = literal.argValues.length;
        Type type = ((ArrayType)literal.type).getComponentType();
        codeattr.emitPushInt(j);
        codeattr.emitNewArray(type);
        store(literal, flag, codeattr);
        int k = 0;
        while (k < j) 
        {
            Literal literal1 = (Literal)literal.argValues[k];
            if (literal1.value != null)
            {
                codeattr.emitDup(type);
                codeattr.emitPushInt(k);
                emit(literal1, false);
                codeattr.emitArrayStore(type);
            }
            k++;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (literal.type instanceof ArrayType)
        {
            codeattr.emitPushPrimArray(literal.value, (ArrayType)literal.type);
            store(literal, flag, codeattr);
            return;
        }
        if (literal.value instanceof Class)
        {
            Class class1 = (Class)literal.value;
            if (class1.isPrimitive())
            {
                String s1 = class1.getName();
                if (s1.equals("int"))
                {
                    s1 = "integer";
                }
                codeattr.emitGetStatic(ClassType.make((new StringBuilder()).append("java.lang.").append(Character.toUpperCase(s1.charAt(0))).append(s1.substring(1)).toString()).getDeclaredField("TYPE"));
            } else
            {
                comp.loadClassRef((ObjectType)Type.make(class1));
            }
            store(literal, flag, codeattr);
            return;
        }
        if ((literal.value instanceof ClassType) && !((ClassType)literal.value).isExisting())
        {
            comp.loadClassRef((ClassType)literal.value);
            Method method2 = Compilation.typeType.getDeclaredMethod("valueOf", 1);
            if (method2 == null)
            {
                method2 = Compilation.typeType.getDeclaredMethod("make", 1);
            }
            codeattr.emitInvokeStatic(method2);
            codeattr.emitCheckcast(Compilation.typeClassType);
            store(literal, flag, codeattr);
            return;
        }
        ClassType classtype = (ClassType)literal.type;
        boolean flag1;
        boolean flag2;
        Method method;
        Method method1;
        boolean flag3;
        if ((4 & literal.flags) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        flag2 = false;
        method = null;
        if (!flag1)
        {
            boolean flag4 = literal.value instanceof Symbol;
            method = null;
            if (!flag4)
            {
                method = getMethod(classtype, "valueOf", literal, true);
            }
            if (method == null && !(literal.value instanceof Values))
            {
                String s = "make";
                if (literal.value instanceof Pattern)
                {
                    s = "compile";
                }
                method = getMethod(classtype, s, literal, true);
            }
            if (method != null)
            {
                flag2 = true;
            } else
            {
                int i = literal.argTypes.length;
                flag2 = false;
                if (i > 0)
                {
                    method = getMethod(classtype, "<init>", literal, false);
                    flag2 = false;
                }
            }
            if (method == null)
            {
                flag1 = true;
            }
        }
        if (flag1)
        {
            method = getMethod(classtype, "set", literal, false);
        }
        if (method == null && literal.argTypes.length > 0)
        {
            error((new StringBuilder()).append("no method to construct ").append(literal.type).toString());
        }
        if (flag2)
        {
            putArgs(literal, codeattr);
            codeattr.emitInvokeStatic(method);
        } else
        if (flag1)
        {
            codeattr.emitNew(classtype);
            codeattr.emitDup(classtype);
            codeattr.emitInvokeSpecial(classtype.getDeclaredMethod("<init>", 0));
        } else
        {
            codeattr.emitNew(classtype);
            codeattr.emitDup(classtype);
            putArgs(literal, codeattr);
            codeattr.emitInvokeSpecial(method);
        }
        if (flag2 || (literal.value instanceof Values))
        {
            method1 = null;
        } else
        {
            method1 = classtype.getDeclaredMethod("readResolve", 0);
        }
        if (method1 != null)
        {
            codeattr.emitInvokeVirtual(method1);
            classtype.emitCoerceFromObject(codeattr);
        }
        if (flag && (!flag1 || method == null))
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        store(literal, flag3, codeattr);
        if (flag1 && method != null)
        {
            if (!flag)
            {
                codeattr.emitDup(classtype);
            }
            putArgs(literal, codeattr);
            codeattr.emitInvokeVirtual(method);
            return;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    void error(String s)
    {
        throw new Error(s);
    }

    public Literal findLiteral(Object obj)
    {
        if (obj != null) goto _L2; else goto _L1
_L1:
        Literal literal = Literal.nullLiteral;
_L4:
        return literal;
_L2:
        literal = (Literal)literalTable.get(obj);
        if (literal != null) goto _L4; else goto _L3
_L3:
        Class class1;
        Type type;
        if (comp.immediate)
        {
            return new Literal(obj, this);
        }
        class1 = obj.getClass();
        type = Type.make(class1);
        Table2D table2d = staticTable;
        table2d;
        JVM INSTR monitorenter ;
        Literal literal1 = (Literal)staticTable.get(obj, null, null);
        if (literal1 == null) goto _L6; else goto _L5
_L5:
        if (literal1.value == obj) goto _L7; else goto _L6
_L6:
        if (!(type instanceof ClassType)) goto _L7; else goto _L8
_L8:
        Class class2 = class1;
        ClassType classtype = (ClassType)type;
_L15:
        if (staticTable.get(class2, Boolean.TRUE, null) != null) goto _L7; else goto _L9
_L9:
        Field field;
        staticTable.put(class2, Boolean.TRUE, class2);
        field = classtype.getFields();
_L14:
        if (field == null)
        {
            break MISSING_BLOCK_LABEL_301;
        }
        int i = field.getModifiers();
        if ((i & 0x19) != 25) goto _L11; else goto _L10
_L10:
        Object obj1 = field.getReflectField().get(null);
        if (obj1 == null) goto _L11; else goto _L12
_L12:
        boolean flag = class2.isInstance(obj1);
        if (flag) goto _L13; else goto _L11
_L11:
        Field field1 = field.getNext();
        field = field1;
          goto _L14
_L13:
        Literal literal2;
        literal2 = new Literal(obj1, field, this);
        staticTable.put(obj1, null, literal2);
        if (obj == obj1)
        {
            literal1 = literal2;
        }
          goto _L11
        Throwable throwable;
        throwable;
        error((new StringBuilder()).append("caught ").append(throwable).append(" getting static field ").append(field).toString());
          goto _L11
        Exception exception;
        exception;
        table2d;
        JVM INSTR monitorexit ;
        throw exception;
        class2 = class2.getSuperclass();
        if (class2 != null)
        {
            break MISSING_BLOCK_LABEL_335;
        }
_L7:
        table2d;
        JVM INSTR monitorexit ;
        if (literal1 != null)
        {
            literalTable.put(obj, literal1);
            return literal1;
        } else
        {
            return new Literal(obj, type, this);
        }
        classtype = (ClassType)Type.make(class2);
          goto _L15
    }

    public void flush()
    {
    }

    Method getMethod(ClassType classtype, String s, Literal literal, boolean flag)
    {
        Type atype[];
        Method method;
        int i;
        Method method1;
        long l;
        boolean flag1;
        Type atype1[];
        atype = literal.argTypes;
        method = classtype.getDeclaredMethods();
        i = atype.length;
        method1 = null;
        l = 0L;
        flag1 = false;
        atype1 = null;
_L3:
        if (method == null)
        {
            break; /* Loop/switch isn't completed */
        }
          goto _L1
_L5:
        method = method.getNext();
        if (true) goto _L3; else goto _L2
_L1:
        if (!s.equals(method.getName()) || flag != method.getStaticFlag()) goto _L5; else goto _L4
_L4:
        long l1;
        Type atype3[];
        int i2;
        int j2;
        l1 = 0L;
        atype3 = method.getParameterTypes();
        i2 = 0;
        j2 = 0;
_L15:
        if (i2 != i)
        {
            continue; /* Loop/switch isn't completed */
        }
        int j3 = atype3.length;
        if (j2 != j3)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (method1 != null && (l == 0L || l1 != 0L)) goto _L7; else goto _L6
_L6:
        method1 = method;
        atype1 = atype3;
        l = l1;
          goto _L5
_L7:
        if (l1 != 0L) goto _L5; else goto _L8
_L8:
        boolean flag3;
        boolean flag4;
        int k3;
        flag3 = false;
        flag4 = false;
        k3 = i;
_L12:
        int l3;
        if (--k3 >= 0)
        {
            l3 = atype1[k3].compare(atype3[k3]);
            if (l3 == 1)
            {
                continue; /* Loop/switch isn't completed */
            }
            flag4 = true;
            if (!flag3)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
_L11:
        if (flag3)
        {
            method1 = method;
            atype1 = atype3;
        }
        if (flag3 && flag4)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
          goto _L5
        if (l3 == -1) goto _L10; else goto _L9
_L9:
        break MISSING_BLOCK_LABEL_224;
_L10:
        break; /* Loop/switch isn't completed */
        flag3 = true;
        if (!flag4) goto _L12; else goto _L11
        if (i2 == i) goto _L5; else goto _L13
_L13:
        int k2 = atype3.length;
        if (j2 == k2) goto _L5; else goto _L14
_L14:
        Type type2;
        Type type3;
        type2 = atype[i2];
        type3 = atype3[j2];
        if (!type2.isSubtype(type3))
        {
            continue; /* Loop/switch isn't completed */
        }
_L21:
        i2++;
        j2++;
          goto _L15
        if (!(type3 instanceof ArrayType) || j2 >= 64 || type2 != Type.intType && type2 != Type.shortType) goto _L5; else goto _L16
_L16:
        int l2;
        Type type4;
        l2 = ((Number)literal.argValues[i2]).intValue();
        if (l2 < 0 && classtype.getName().equals("gnu.math.IntNum"))
        {
            l2 += 0x80000000;
        }
        type4 = ((ArrayType)type3).getComponentType();
        if (l2 < 0 || i2 + l2 >= i) goto _L5; else goto _L17
_L17:
        int i3 = l2;
_L20:
        if (--i3 < 0) goto _L19; else goto _L18
_L18:
        Type type5 = atype[1 + (i2 + i3)];
        if ((type4 instanceof PrimType) ? type4.getSignature() == type5.getSignature() : type5.isSubtype(type4)) goto _L20; else goto _L5
_L19:
        i2 += l2;
        l1 |= 1 << j2;
          goto _L21
_L2:
        if (!flag1) goto _L23; else goto _L22
_L22:
        method1 = null;
_L25:
        return method1;
_L23:
        if (l == 0L) goto _L25; else goto _L24
_L24:
        Object aobj[];
        Type atype2[];
        int j;
        int k;
        aobj = new Object[atype1.length];
        atype2 = new Type[atype1.length];
        j = 0;
        k = 0;
_L27:
        Type type;
        if (j == i)
        {
            literal.argValues = aobj;
            literal.argTypes = atype2;
            return method1;
        }
        type = atype1[k];
        if ((l & (long)(1 << k)) != 0L)
        {
            break; /* Loop/switch isn't completed */
        }
        aobj[k] = literal.argValues[j];
        atype2[k] = literal.argTypes[j];
_L28:
        j++;
        k++;
        if (true) goto _L27; else goto _L26
_L26:
        int i1 = ((Number)literal.argValues[j]).intValue();
        boolean flag2 = classtype.getName().equals("gnu.math.IntNum");
        if (flag2)
        {
            i1 += 0x80000000;
        }
        Type type1 = ((ArrayType)type).getComponentType();
        atype2[k] = type;
        aobj[k] = Array.newInstance(type1.getReflectClass(), i1);
        Object aobj1[] = literal.argValues;
        if (flag2)
        {
            int ai[] = (int[])(int[])aobj[k];
            for (int k1 = i1; k1 > 0; k1--)
            {
                ai[i1 - k1] = ((Integer)aobj1[j + k1]).intValue();
            }

        } else
        {
            for (int j1 = i1; --j1 >= 0;)
            {
                Object obj = aobj[k];
                Object obj1 = aobj1[j1 + (j + 1)];
                Array.set(obj, j1, obj1);
            }

        }
        Literal literal1 = new Literal(aobj[k], type);
        if (type1 instanceof ObjectType)
        {
            literal1.argValues = (Object[])(Object[])aobj[k];
        }
        aobj[k] = literal1;
        j += i1;
          goto _L28
        if (true) goto _L27; else goto _L29
_L29:
          goto _L5
    }

    void push(Object obj, Type type)
    {
        if (stackPointer >= valueStack.length)
        {
            Object aobj[] = new Object[2 * valueStack.length];
            Type atype[] = new Type[2 * typeStack.length];
            System.arraycopy(((Object) (valueStack)), 0, ((Object) (aobj)), 0, stackPointer);
            System.arraycopy(typeStack, 0, atype, 0, stackPointer);
            valueStack = aobj;
            typeStack = atype;
        }
        valueStack[stackPointer] = obj;
        typeStack[stackPointer] = type;
        stackPointer = 1 + stackPointer;
    }

    void putArgs(Literal literal, CodeAttr codeattr)
    {
        Type atype[] = literal.argTypes;
        int i = atype.length;
        int j = 0;
        while (j < i) 
        {
            Object obj = literal.argValues[j];
            if (obj instanceof Literal)
            {
                emit((Literal)obj, false);
            } else
            {
                comp.compileConstant(obj, new StackTarget(atype[j]));
            }
            j++;
        }
    }

    public void write(int i)
        throws IOException
    {
        error("cannot handle call to write(int) when externalizing literal");
    }

    public void write(byte abyte0[])
        throws IOException
    {
        error("cannot handle call to write(byte[]) when externalizing literal");
    }

    public void write(byte abyte0[], int i, int j)
        throws IOException
    {
        error("cannot handle call to write(byte[],int,int) when externalizing literal");
    }

    public void writeBoolean(boolean flag)
    {
        push(new Boolean(flag), Type.booleanType);
    }

    public void writeByte(int i)
    {
        push(new Byte((byte)i), Type.byteType);
    }

    public void writeBytes(String s)
        throws IOException
    {
        error("cannot handle call to writeBytes(String) when externalizing literal");
    }

    public void writeChar(int i)
    {
        push(new Character((char)i), Type.charType);
    }

    public void writeChars(String s)
    {
        push(s, Type.string_type);
    }

    public void writeDouble(double d)
    {
        push(new Double(d), Type.doubleType);
    }

    public void writeFloat(float f)
    {
        push(new Float(f), Type.floatType);
    }

    public void writeInt(int i)
    {
        push(new Integer(i), Type.intType);
    }

    public void writeLong(long l)
    {
        push(new Long(l), Type.longType);
    }

    public void writeObject(Object obj)
        throws IOException
    {
        Literal literal = findLiteral(obj);
        if ((3 & literal.flags) == 0) goto _L2; else goto _L1
_L1:
        if (literal.field == null && obj != null && !(obj instanceof String))
        {
            literal.assign(this);
        }
        if ((2 & literal.flags) == 0)
        {
            literal.flags = 4 | literal.flags;
        }
_L5:
        push(literal, literal.type);
        return;
_L2:
        int i;
        literal.flags = 1 | literal.flags;
        i = stackPointer;
        if (!(obj instanceof FString) || ((FString)obj).size() >= 65535) goto _L4; else goto _L3
_L3:
        push(obj.toString(), Type.string_type);
_L6:
        int j = stackPointer - i;
        Pattern pattern;
        BigDecimal bigdecimal;
        Object aobj[];
        int k;
        if (j == 0)
        {
            literal.argValues = Values.noArgs;
            literal.argTypes = Type.typeArray0;
        } else
        {
            literal.argValues = new Object[j];
            literal.argTypes = new Type[j];
            System.arraycopy(((Object) (valueStack)), i, ((Object) (literal.argValues)), 0, j);
            System.arraycopy(typeStack, i, literal.argTypes, 0, j);
            stackPointer = i;
        }
        literal.flags = 2 | literal.flags;
        if (true) goto _L5; else goto _L4
_L4:
        if (obj instanceof Externalizable)
        {
            ((Externalizable)obj).writeExternal(this);
        } else
        if (obj instanceof Object[])
        {
            aobj = (Object[])(Object[])obj;
            k = 0;
            while (k < aobj.length) 
            {
                writeObject(aobj[k]);
                k++;
            }
        } else
        if (obj != null && !(obj instanceof String) && !(literal.type instanceof ArrayType))
        {
            if (obj instanceof BigInteger)
            {
                writeChars(obj.toString());
            } else
            if (obj instanceof BigDecimal)
            {
                bigdecimal = (BigDecimal)obj;
                writeObject(bigdecimal.unscaledValue());
                writeInt(bigdecimal.scale());
            } else
            if (obj instanceof Integer)
            {
                push(obj, Type.intType);
            } else
            if (obj instanceof Short)
            {
                push(obj, Type.shortType);
            } else
            if (obj instanceof Byte)
            {
                push(obj, Type.byteType);
            } else
            if (obj instanceof Long)
            {
                push(obj, Type.longType);
            } else
            if (obj instanceof Double)
            {
                push(obj, Type.doubleType);
            } else
            if (obj instanceof Float)
            {
                push(obj, Type.floatType);
            } else
            if (obj instanceof Character)
            {
                push(obj, Type.charType);
            } else
            if (obj instanceof Class)
            {
                push(obj, Type.java_lang_Class_type);
            } else
            if (obj instanceof Pattern)
            {
                pattern = (Pattern)obj;
                push(pattern.pattern(), Type.string_type);
                push(Integer.valueOf(pattern.flags()), Type.intType);
            } else
            {
                error((new StringBuilder()).append(obj.getClass().getName()).append(" does not implement Externalizable").toString());
            }
        }
          goto _L6
    }

    public void writeShort(int i)
    {
        push(new Short((short)i), Type.shortType);
    }

    public void writeUTF(String s)
    {
        push(s, Type.string_type);
    }

}
