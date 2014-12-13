// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.Externalizable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

// Referenced classes of package gnu.bytecode:
//            ObjectType, AttrContainer, Member, Type, 
//            Field, Method, ConstantPool, CpoolClass, 
//            EnclosingMethodAttr, InnerClassesAttr, Attribute, Filter, 
//            SourceDebugExtAttr, SourceFileAttr, CpoolEntry

public class ClassType extends ObjectType
    implements AttrContainer, Externalizable, Member
{
    static class AbstractMethodFilter
        implements Filter
    {

        public static final AbstractMethodFilter instance = new AbstractMethodFilter();

        public boolean select(Object obj)
        {
            return ((gnu.bytecode.Method)obj).isAbstract();
        }


        AbstractMethodFilter()
        {
        }
    }


    public static final int JDK_1_1_VERSION = 0x2d0003;
    public static final int JDK_1_2_VERSION = 0x2e0000;
    public static final int JDK_1_3_VERSION = 0x2f0000;
    public static final int JDK_1_4_VERSION = 0x300000;
    public static final int JDK_1_5_VERSION = 0x310000;
    public static final int JDK_1_6_VERSION = 0x320000;
    public static final int JDK_1_7_VERSION = 0x330000;
    public static final ClassType noClasses[] = new ClassType[0];
    int Code_name_index;
    int ConstantValue_name_index;
    int LineNumberTable_name_index;
    int LocalVariableTable_name_index;
    int access_flags;
    Attribute attributes;
    int classfileFormatVersion;
    ConstantPool constants;
    public gnu.bytecode.Method constructor;
    boolean emitDebugInfo;
    Member enclosingMember;
    gnu.bytecode.Field fields;
    int fields_count;
    ClassType firstInnerClass;
    int interfaceIndexes[];
    ClassType interfaces[];
    gnu.bytecode.Field last_field;
    gnu.bytecode.Method last_method;
    gnu.bytecode.Method methods;
    int methods_count;
    ClassType nextInnerClass;
    SourceDebugExtAttr sourceDbgExt;
    ClassType superClass;
    int superClassIndex;
    int thisClassIndex;

    public ClassType()
    {
        classfileFormatVersion = 0x2d0003;
        superClassIndex = -1;
        emitDebugInfo = true;
    }

    public ClassType(String s)
    {
        classfileFormatVersion = 0x2d0003;
        superClassIndex = -1;
        emitDebugInfo = true;
        setName(s);
    }

    public static ClassType make(String s)
    {
        return (ClassType)Type.getType(s);
    }

    public static ClassType make(String s, ClassType classtype)
    {
        ClassType classtype1 = make(s);
        if (classtype1.superClass == null)
        {
            classtype1.setSuper(classtype);
        }
        return classtype1;
    }

    public static byte[] to_utf8(String s)
    {
        byte abyte0[];
        if (s == null)
        {
            abyte0 = null;
        } else
        {
            int i = s.length();
            int j = 0;
            int k = 0;
            while (k < i) 
            {
                char c1 = s.charAt(k);
                if (c1 > 0 && c1 <= '\177')
                {
                    j++;
                } else
                if (c1 <= '\u07FF')
                {
                    j += 2;
                } else
                {
                    j += 3;
                }
                k++;
            }
            abyte0 = new byte[j];
            int l = 0;
            int i1 = 0;
            while (l < i) 
            {
                char c = s.charAt(l);
                int l1;
                if (c > 0 && c <= '\177')
                {
                    l1 = i1 + 1;
                    abyte0[i1] = (byte)c;
                } else
                if (c <= '\u07FF')
                {
                    int i2 = i1 + 1;
                    abyte0[i1] = (byte)(0xc0 | 0x1f & c >> 6);
                    int j2 = i2 + 1;
                    abyte0[i2] = (byte)(0x80 | 0x3f & c >> 0);
                    l1 = j2;
                } else
                {
                    int j1 = i1 + 1;
                    abyte0[i1] = (byte)(0xe0 | 0xf & c >> 12);
                    int k1 = j1 + 1;
                    abyte0[j1] = (byte)(0x80 | 0x3f & c >> 6);
                    l1 = k1 + 1;
                    abyte0[k1] = (byte)(0x80 | 0x3f & c >> 0);
                }
                l++;
                i1 = l1;
            }
        }
        return abyte0;
    }

    void addEnclosingMember()
    {
        this;
        JVM INSTR monitorenter ;
        int i = flags;
        if ((i & 0x18) == 16) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Class class1;
        Class class2;
        class1 = getReflectClass();
        flags = 8 | flags;
        class2 = class1.getEnclosingClass();
        if (class2 == null) goto _L1; else goto _L3
_L3:
        Method method;
        if (class1.isMemberClass())
        {
            break MISSING_BLOCK_LABEL_106;
        }
        method = class1.getEnclosingMethod();
        if (method == null)
        {
            break MISSING_BLOCK_LABEL_82;
        }
        enclosingMember = addMethod(method);
          goto _L1
        Exception exception;
        exception;
        throw exception;
        Constructor constructor1 = class1.getEnclosingConstructor();
        if (constructor1 == null)
        {
            break MISSING_BLOCK_LABEL_106;
        }
        enclosingMember = addMethod(constructor1);
          goto _L1
        enclosingMember = (ClassType)Type.make(class2);
          goto _L1
    }

    public gnu.bytecode.Field addField()
    {
        return new gnu.bytecode.Field(this);
    }

    public gnu.bytecode.Field addField(String s)
    {
        gnu.bytecode.Field field = new gnu.bytecode.Field(this);
        field.setName(s);
        return field;
    }

    public final gnu.bytecode.Field addField(String s, Type type)
    {
        gnu.bytecode.Field field = new gnu.bytecode.Field(this);
        field.setName(s);
        field.setType(type);
        return field;
    }

    public final gnu.bytecode.Field addField(String s, Type type, int i)
    {
        gnu.bytecode.Field field = addField(s, type);
        field.flags = i;
        return field;
    }

    public void addFields()
    {
        this;
        JVM INSTR monitorenter ;
        Class class1 = getReflectClass();
        Field afield1[] = class1.getDeclaredFields();
        Field afield[] = afield1;
_L3:
        int i = afield.length;
        int j = 0;
_L2:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        Field field = afield[j];
        if ("this$0".equals(field.getName()))
        {
            flags = 0x20 | flags;
        }
        addField(field.getName(), Type.make(field.getType()), field.getModifiers());
        j++;
        if (true) goto _L2; else goto _L1
        SecurityException securityexception;
        securityexception;
        afield = class1.getFields();
          goto _L3
_L1:
        flags = 1 | flags;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void addInterface(ClassType classtype)
    {
        if (interfaces != null && interfaces.length != 0) goto _L2; else goto _L1
_L1:
        int i;
        i = 0;
        interfaces = new ClassType[1];
_L4:
        interfaces[i] = classtype;
        return;
_L2:
        i = interfaces.length;
        for (int j = i; --j >= 0;)
        {
            if (interfaces[j] == classtype)
            {
                return;
            }
        }

        ClassType aclasstype[] = new ClassType[i + 1];
        System.arraycopy(interfaces, 0, aclasstype, 0, i);
        interfaces = aclasstype;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void addMemberClass(ClassType classtype)
    {
        ClassType classtype1 = null;
        for (ClassType classtype2 = firstInnerClass; classtype2 != null; classtype2 = classtype2.nextInnerClass)
        {
            if (classtype2 == classtype)
            {
                return;
            }
            classtype1 = classtype2;
        }

        if (classtype1 == null)
        {
            firstInnerClass = classtype;
            return;
        } else
        {
            classtype1.nextInnerClass = classtype;
            return;
        }
    }

    public void addMemberClasses()
    {
        this;
        JVM INSTR monitorenter ;
        int i = flags;
        if ((i & 0x14) == 16) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Class aclass[];
        int j;
        Class class1 = getReflectClass();
        flags = 4 | flags;
        aclass = class1.getClasses();
        j = aclass.length;
        int k;
        if (j <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        k = 0;
_L4:
        if (k >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        addMemberClass((ClassType)Type.make(aclass[k]));
        k++;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L1; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    gnu.bytecode.Method addMethod()
    {
        return new gnu.bytecode.Method(this, 0);
    }

    public gnu.bytecode.Method addMethod(String s)
    {
        return addMethod(s, 0);
    }

    public gnu.bytecode.Method addMethod(String s, int i)
    {
        gnu.bytecode.Method method = new gnu.bytecode.Method(this, i);
        method.setName(s);
        return method;
    }

    public gnu.bytecode.Method addMethod(String s, int i, Type atype[], Type type)
    {
        this;
        JVM INSTR monitorenter ;
        gnu.bytecode.Method method = getDeclaredMethod(s, atype);
        if (method == null) goto _L2; else goto _L1
_L1:
        if (!type.equals(method.getReturnType())) goto _L2; else goto _L3
_L3:
        int j = method.access_flags;
        if ((j & i) != i) goto _L2; else goto _L4
_L4:
        gnu.bytecode.Method method2 = method;
_L6:
        this;
        JVM INSTR monitorexit ;
        return method2;
_L2:
        gnu.bytecode.Method method1;
        method1 = addMethod(s, i);
        method1.arg_types = atype;
        method1.return_type = type;
        method2 = method1;
        if (true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public gnu.bytecode.Method addMethod(String s, String s1, int i)
    {
        gnu.bytecode.Method method = addMethod(s, i);
        method.setSignature(s1);
        return method;
    }

    public gnu.bytecode.Method addMethod(String s, Type atype[], Type type, int i)
    {
        return addMethod(s, i, atype, type);
    }

    public gnu.bytecode.Method addMethod(Constructor constructor1)
    {
        Class aclass[] = constructor1.getParameterTypes();
        int i = constructor1.getModifiers();
        int j = aclass.length;
        Type atype[] = new Type[j];
        while (--j >= 0) 
        {
            atype[j] = Type.make(aclass[j]);
        }
        return addMethod("<init>", i, atype, ((Type) (Type.voidType)));
    }

    public gnu.bytecode.Method addMethod(Method method)
    {
        int i = method.getModifiers();
        Class aclass[] = method.getParameterTypes();
        int j = aclass.length;
        Type atype[] = new Type[j];
        while (--j >= 0) 
        {
            atype[j] = Type.make(aclass[j]);
        }
        Type type = Type.make(method.getReturnType());
        return addMethod(method.getName(), i, atype, type);
    }

    public void addMethods(Class class1)
    {
        this;
        JVM INSTR monitorenter ;
        flags = 2 | flags;
        Method amethod1[] = class1.getDeclaredMethods();
        Method amethod[] = amethod1;
_L2:
        int i = amethod.length;
        Exception exception;
        SecurityException securityexception;
        Method method;
        SecurityException securityexception1;
        Constructor aconstructor[];
        int k;
        Constructor constructor1;
        Constructor aconstructor1[];
        for (int j = 0; j >= i; j++)
        {
            break MISSING_BLOCK_LABEL_84;
        }

        method = amethod[j];
        if (!method.getDeclaringClass().equals(class1))
        {
            break MISSING_BLOCK_LABEL_155;
        }
        break; /* Loop/switch isn't completed */
        securityexception;
        amethod = class1.getMethods();
        if (true) goto _L2; else goto _L1
_L1:
        addMethod(method);
        break MISSING_BLOCK_LABEL_155;
        exception;
        throw exception;
        aconstructor1 = class1.getDeclaredConstructors();
        aconstructor = aconstructor1;
_L4:
        k = aconstructor.length;
        for (int l = 0; l >= k; l++)
        {
            break MISSING_BLOCK_LABEL_153;
        }

        constructor1 = aconstructor[l];
        if (!constructor1.getDeclaringClass().equals(class1))
        {
            break MISSING_BLOCK_LABEL_161;
        }
        break; /* Loop/switch isn't completed */
        securityexception1;
        aconstructor = class1.getConstructors();
        if (true) goto _L4; else goto _L3
_L3:
        addMethod(constructor1);
        break MISSING_BLOCK_LABEL_161;
    }

    public final void addModifiers(int i)
    {
        access_flags = i | access_flags;
    }

    public gnu.bytecode.Method checkSingleAbstractMethod()
    {
        gnu.bytecode.Method amethod[];
        int i;
        gnu.bytecode.Method method;
        int j;
        amethod = getAbstractMethods();
        i = amethod.length;
        method = null;
        j = 0;
_L5:
        if (j >= i) goto _L2; else goto _L1
_L1:
        gnu.bytecode.Method method1;
        gnu.bytecode.Method method2;
        method1 = amethod[j];
        method2 = getMethod(method1.getName(), method1.getParameterTypes());
        if (method2 == null || method2.isAbstract()) goto _L4; else goto _L3
_L3:
        j++;
          goto _L5
_L4:
        if (method == null)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        method = null;
_L2:
        return method;
        method = method1;
          goto _L3
    }

    public void cleanupAfterCompilation()
    {
        for (gnu.bytecode.Method method = methods; method != null; method = method.getNext())
        {
            method.cleanupAfterCompilation();
        }

        constants = null;
        attributes = null;
        sourceDbgExt = null;
    }

    public int compare(Type type)
    {
        byte byte0 = -1;
        if (type != nullType) goto _L2; else goto _L1
_L1:
        return 1;
_L2:
        ClassType classtype;
        if (!(type instanceof ClassType))
        {
            return swappedCompareResult(type.compare(this));
        }
        String s = getName();
        if (s != null && s.equals(type.getName()))
        {
            return 0;
        }
        classtype = (ClassType)type;
        if (isSubclass(classtype))
        {
            return byte0;
        }
        if (classtype.isSubclass(this))
        {
            continue; /* Loop/switch isn't completed */
        }
        if (this == toStringType)
        {
            if (classtype != Type.javalangObjectType)
            {
                byte0 = 1;
            }
            return byte0;
        }
        if (classtype != toStringType)
        {
            break; /* Loop/switch isn't completed */
        }
        if (this != Type.javalangObjectType)
        {
            return byte0;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (isInterface())
        {
            if (classtype != Type.javalangObjectType)
            {
                byte0 = -2;
            }
            return byte0;
        }
        if (classtype.isInterface())
        {
            if (this != Type.javalangObjectType)
            {
                return -2;
            }
        } else
        {
            return -3;
        }
        if (true) goto _L1; else goto _L4
_L4:
    }

    public final int countMethods(Filter filter, int i)
    {
        Vector vector = new Vector();
        getMethods(filter, i, vector);
        return vector.size();
    }

    public void doFixups()
    {
        if (constants == null)
        {
            constants = new ConstantPool();
        }
        if (thisClassIndex == 0)
        {
            thisClassIndex = constants.addClass(this).index;
        }
        if (superClass == this)
        {
            setSuper((ClassType)null);
        }
        if (superClassIndex < 0)
        {
            int j;
            int k;
            int l;
            if (superClass == null)
            {
                l = 0;
            } else
            {
                l = constants.addClass(superClass).index;
            }
            superClassIndex = l;
        }
        if (interfaces != null && interfaceIndexes == null)
        {
            j = interfaces.length;
            interfaceIndexes = new int[j];
            for (k = 0; k < j; k++)
            {
                interfaceIndexes[k] = constants.addClass(interfaces[k]).index;
            }

        }
        for (gnu.bytecode.Field field = fields; field != null; field = field.next)
        {
            field.assign_constants(this);
        }

        for (gnu.bytecode.Method method = methods; method != null; method = method.next)
        {
            method.assignConstants();
        }

        if (!(enclosingMember instanceof gnu.bytecode.Method)) goto _L2; else goto _L1
_L1:
        EnclosingMethodAttr enclosingmethodattr = EnclosingMethodAttr.getFirstEnclosingMethod(getAttributes());
        if (enclosingmethodattr == null)
        {
            enclosingmethodattr = new EnclosingMethodAttr(this);
        }
        enclosingmethodattr.method = (gnu.bytecode.Method)enclosingMember;
_L4:
        for (ClassType classtype = firstInnerClass; classtype != null; classtype = classtype.nextInnerClass)
        {
            constants.addClass(classtype);
        }

        break; /* Loop/switch isn't completed */
_L2:
        if (enclosingMember instanceof ClassType)
        {
            constants.addClass((ClassType)enclosingMember);
        }
        if (true) goto _L4; else goto _L3
_L3:
        InnerClassesAttr innerclassesattr = InnerClassesAttr.getFirstInnerClasses(getAttributes());
        if (innerclassesattr != null)
        {
            innerclassesattr.setSkipped(true);
        }
        Attribute.assignConstants(this, this);
        int i = 1;
        while (i <= constants.count) 
        {
            CpoolEntry cpoolentry = constants.pool[i];
            if (cpoolentry instanceof CpoolClass)
            {
                CpoolClass cpoolclass = (CpoolClass)cpoolentry;
                if ((cpoolclass.clas instanceof ClassType) && ((ClassType)cpoolclass.clas).getEnclosingMember() != null)
                {
                    if (innerclassesattr == null)
                    {
                        innerclassesattr = new InnerClassesAttr(this);
                    }
                    innerclassesattr.addClass(cpoolclass, this);
                }
            }
            i++;
        }
        if (innerclassesattr != null)
        {
            innerclassesattr.setSkipped(false);
            innerclassesattr.assignConstants(this);
        }
        return;
    }

    public gnu.bytecode.Method[] getAbstractMethods()
    {
        return getMethods(AbstractMethodFilter.instance, 2);
    }

    public final Attribute getAttributes()
    {
        return attributes;
    }

    public short getClassfileMajorVersion()
    {
        return (short)(classfileFormatVersion >> 16);
    }

    public short getClassfileMinorVersion()
    {
        return (short)(0xffff & classfileFormatVersion);
    }

    public int getClassfileVersion()
    {
        return classfileFormatVersion;
    }

    public final CpoolEntry getConstant(int i)
    {
        if (constants == null || constants.pool == null || i > constants.count)
        {
            return null;
        } else
        {
            return constants.pool[i];
        }
    }

    public final ConstantPool getConstants()
    {
        return constants;
    }

    public ClassType getDeclaredClass(String s)
    {
        addMemberClasses();
        for (ClassType classtype = firstInnerClass; classtype != null; classtype = classtype.nextInnerClass)
        {
            if (s.equals(classtype.getSimpleName()))
            {
                return classtype;
            }
        }

        return null;
    }

    public gnu.bytecode.Field getDeclaredField(String s)
    {
        for (gnu.bytecode.Field field = getFields(); field != null; field = field.next)
        {
            if (s.equals(field.name))
            {
                return field;
            }
        }

        return null;
    }

    public gnu.bytecode.Method getDeclaredMethod(String s, int i)
    {
        this;
        JVM INSTR monitorenter ;
        gnu.bytecode.Method method = null;
        Exception exception;
        int j;
        gnu.bytecode.Method method1;
        if ("<init>".equals(s) && hasOuterLink())
        {
            j = 1;
        } else
        {
            j = 0;
        }
        method1 = getDeclaredMethods();
_L2:
        if (method1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (!s.equals(method1.getName()) || i + j != method1.getParameterTypes().length)
        {
            break MISSING_BLOCK_LABEL_139;
        }
        if (method == null)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        throw new Error((new StringBuilder()).append("ambiguous call to getDeclaredMethod(\"").append(s).append("\", ").append(i).append(")\n - ").append(method).append("\n - ").append(method1).toString());
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        method = method1;
        method1 = method1.next;
        if (true) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return method;
    }

    public gnu.bytecode.Method getDeclaredMethod(String s, Type atype[])
    {
        int i;
        gnu.bytecode.Method method;
        if ("<init>".equals(s) && hasOuterLink())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        method = getDeclaredMethods();
        if (method == null)
        {
            break MISSING_BLOCK_LABEL_154;
        }
        if (s.equals(method.getName()))
        {
            break; /* Loop/switch isn't completed */
        }
_L4:
        method = method.next;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_25;
_L1:
        Type atype1[];
        int j;
        atype1 = method.getParameterTypes();
        if (atype == null || atype == atype1 && i == 0)
        {
            return method;
        }
        j = atype.length;
        if (j != atype1.length - i) goto _L4; else goto _L3
_L3:
        Type type;
        Type type1;
        do
        {
            if (--j < 0)
            {
                break;
            }
            type = atype1[j + i];
            type1 = atype[j];
        } while (type == type1 || type1 == null || type.getSignature().equals(type1.getSignature()));
        if (j >= 0) goto _L4; else goto _L5
_L5:
        return method;
        return null;
    }

    public final gnu.bytecode.Method getDeclaredMethods()
    {
        this;
        JVM INSTR monitorenter ;
        gnu.bytecode.Method method;
        if ((0x12 & flags) == 16)
        {
            addMethods(getReflectClass());
        }
        method = methods;
        this;
        JVM INSTR monitorexit ;
        return method;
        Exception exception;
        exception;
        throw exception;
    }

    public ClassType getDeclaringClass()
    {
        addEnclosingMember();
        if (enclosingMember instanceof ClassType)
        {
            return (ClassType)enclosingMember;
        } else
        {
            return null;
        }
    }

    public Member getEnclosingMember()
    {
        addEnclosingMember();
        return enclosingMember;
    }

    public gnu.bytecode.Field getField(String s)
    {
        return getField(s, 1);
    }

    public gnu.bytecode.Field getField(String s, int i)
    {
        this;
        JVM INSTR monitorenter ;
        ClassType classtype = this;
_L10:
        gnu.bytecode.Field field = classtype.getDeclaredField(s);
        if (field == null) goto _L2; else goto _L1
_L1:
        if (i == -1) goto _L4; else goto _L3
_L3:
        int k = field.getModifiers();
        if ((k & i) == 0) goto _L2; else goto _L4
_L4:
        gnu.bytecode.Field field1 = field;
_L8:
        this;
        JVM INSTR monitorexit ;
        return field1;
_L2:
        ClassType aclasstype[] = classtype.getInterfaces();
        if (aclasstype == null) goto _L6; else goto _L5
_L5:
        int j = 0;
_L11:
        if (j >= aclasstype.length) goto _L6; else goto _L7
_L7:
        gnu.bytecode.Field field2 = aclasstype[j].getField(s, i);
        if (field2 == null)
        {
            break MISSING_BLOCK_LABEL_116;
        }
        field1 = field2;
          goto _L8
_L6:
        ClassType classtype1 = classtype.getSuperclass();
        classtype = classtype1;
        if (classtype != null) goto _L10; else goto _L9
_L9:
        field1 = null;
          goto _L8
        Exception exception;
        exception;
        throw exception;
        j++;
          goto _L11
    }

    public final int getFieldCount()
    {
        return fields_count;
    }

    public final gnu.bytecode.Field getFields()
    {
        this;
        JVM INSTR monitorenter ;
        gnu.bytecode.Field field;
        if ((0x11 & flags) == 16)
        {
            addFields();
        }
        field = fields;
        this;
        JVM INSTR monitorexit ;
        return field;
        Exception exception;
        exception;
        throw exception;
    }

    public ClassType[] getInterfaces()
    {
        this;
        JVM INSTR monitorenter ;
        if (interfaces != null || (0x10 & flags) == 0 || getReflectClass() == null) goto _L2; else goto _L1
_L1:
        Class aclass[];
        int i;
        aclass = reflectClass.getInterfaces();
        i = aclass.length;
        if (i != 0) goto _L4; else goto _L3
_L3:
        ClassType aclasstype1[] = noClasses;
_L6:
        interfaces = aclasstype1;
        int j = 0;
_L5:
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        interfaces[j] = (ClassType)Type.make(aclass[j]);
        j++;
        if (true) goto _L5; else goto _L2
_L4:
        aclasstype1 = new ClassType[i];
          goto _L6
_L2:
        ClassType aclasstype[] = interfaces;
        this;
        JVM INSTR monitorexit ;
        return aclasstype;
        Exception exception;
        exception;
        throw exception;
    }

    public gnu.bytecode.Method[] getMatchingMethods(String s, Type atype[], int i)
    {
        int j = 0;
        Vector vector = new Vector(10);
        gnu.bytecode.Method method = methods;
        do
        {
            if (method == null)
            {
                break;
            }
            if (s.equals(method.getName()) && (i & 8) == (8 & method.access_flags) && (i & 1) <= (1 & method.access_flags) && method.arg_types.length == atype.length)
            {
                j++;
                vector.addElement(method);
            }
            method = method.getNext();
        } while (true);
        gnu.bytecode.Method amethod[] = new gnu.bytecode.Method[j];
        vector.copyInto(amethod);
        return amethod;
    }

    public gnu.bytecode.Method getMethod(String s, Type atype[])
    {
        this;
        JVM INSTR monitorenter ;
        ClassType classtype = this;
_L4:
        gnu.bytecode.Method method = classtype.getDeclaredMethod(s, atype);
        gnu.bytecode.Method method1 = method;
        if (method1 == null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return method1;
_L2:
        classtype = classtype.getSuperclass();
        if (classtype != null) goto _L4; else goto _L3
_L3:
        ClassType classtype1 = this;
_L8:
        ClassType aclasstype[] = classtype1.getInterfaces();
        int i;
        if (aclasstype == null)
        {
            break MISSING_BLOCK_LABEL_84;
        }
        i = 0;
_L6:
        if (i >= aclasstype.length)
        {
            break MISSING_BLOCK_LABEL_84;
        }
        method1 = aclasstype[i].getDeclaredMethod(s, atype);
        if (method1 != null) goto _L1; else goto _L5
_L5:
        i++;
          goto _L6
        ClassType classtype2 = classtype1.getSuperclass();
        classtype1 = classtype2;
        if (classtype1 != null) goto _L8; else goto _L7
_L7:
        method1 = null;
          goto _L1
        Exception exception;
        exception;
        throw exception;
    }

    public gnu.bytecode.Method getMethod(Method method)
    {
        String s = method.getName();
        Class aclass[] = method.getParameterTypes();
        Type atype[] = new Type[aclass.length];
        for (int i = aclass.length; --i >= 0;)
        {
            atype[i] = Type.make(aclass[i]);
        }

        return addMethod(s, method.getModifiers(), atype, Type.make(method.getReturnType()));
    }

    public final int getMethodCount()
    {
        return methods_count;
    }

    public int getMethods(Filter filter, int i, List list)
    {
        int j;
        String s;
        ClassType classtype;
        j = 0;
        s = null;
        classtype = this;
_L8:
        String s1;
        gnu.bytecode.Method method;
        if (classtype == null)
        {
            break MISSING_BLOCK_LABEL_118;
        }
        s1 = classtype.getPackageName();
        method = classtype.getDeclaredMethods();
_L5:
        if (method == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (classtype == this) goto _L2; else goto _L1
_L1:
        int l = method.getModifiers();
          goto _L3
_L6:
        method = method.getNext();
        if (true) goto _L5; else goto _L4
_L3:
        if ((l & 2) != 0 || (l & 5) == 0 && !s1.equals(s)) goto _L6; else goto _L2
_L2:
        if (filter.select(method))
        {
            if (list != null)
            {
                list.add(method);
            }
            j++;
        }
          goto _L6
_L4:
        s = s1;
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_121;
        }
        return j;
        if (i > 1)
        {
            ClassType aclasstype[] = classtype.getInterfaces();
            if (aclasstype != null)
            {
                for (int k = 0; k < aclasstype.length; k++)
                {
                    j += aclasstype[k].getMethods(filter, i, list);
                }

            }
        }
        classtype = classtype.getSuperclass();
        if (true) goto _L8; else goto _L7
_L7:
    }

    public int getMethods(Filter filter, int i, gnu.bytecode.Method amethod[], int j)
    {
        Vector vector = new Vector();
        getMethods(filter, i, ((List) (vector)));
        int k = vector.size();
        for (int l = 0; l < k; l++)
        {
            amethod[j + l] = (gnu.bytecode.Method)vector.elementAt(l);
        }

        return k;
    }

    public final gnu.bytecode.Method getMethods()
    {
        return methods;
    }

    public gnu.bytecode.Method[] getMethods(Filter filter, int i)
    {
        Vector vector = new Vector();
        getMethods(filter, i, ((List) (vector)));
        int j = vector.size();
        gnu.bytecode.Method amethod[] = new gnu.bytecode.Method[j];
        for (int k = 0; k < j; k++)
        {
            amethod[k] = (gnu.bytecode.Method)vector.elementAt(k);
        }

        return amethod;
    }

    public gnu.bytecode.Method[] getMethods(Filter filter, boolean flag)
    {
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        return getMethods(filter, i);
    }

    public final int getModifiers()
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        if (access_flags == 0 && (0x10 & flags) != 0 && getReflectClass() != null)
        {
            access_flags = reflectClass.getModifiers();
        }
        i = access_flags;
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public ClassType getOuterLinkType()
    {
        if (!hasOuterLink())
        {
            return null;
        } else
        {
            return (ClassType)getDeclaredField("this$0").getType();
        }
    }

    public String getPackageName()
    {
        String s = getName();
        int i = s.lastIndexOf('.');
        if (i < 0)
        {
            return "";
        } else
        {
            return s.substring(0, i);
        }
    }

    public String getSimpleName()
    {
        this;
        JVM INSTR monitorenter ;
        if ((0x10 & flags) == 0) goto _L2; else goto _L1
_L1:
        Class class1 = getReflectClass();
        if (class1 == null) goto _L2; else goto _L3
_L3:
        String s2 = reflectClass.getSimpleName();
        String s = s2;
_L7:
        this;
        JVM INSTR monitorexit ;
        return s;
        Throwable throwable;
        throwable;
_L2:
        int i;
        s = getName();
        i = s.lastIndexOf('.');
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        s = s.substring(i + 1);
        int j = s.lastIndexOf('$');
        if (j < 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        int k = s.length();
        int l = j + 1;
_L5:
        if (l >= k)
        {
            break; /* Loop/switch isn't completed */
        }
        char c = s.charAt(l);
        if (c < '0' || c > '9')
        {
            break; /* Loop/switch isn't completed */
        }
        l++;
        if (true) goto _L5; else goto _L4
_L4:
        String s1 = s.substring(l);
        s = s1;
        if (true) goto _L7; else goto _L6
_L6:
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean getStaticFlag()
    {
        return (8 & getModifiers()) != 0;
    }

    public ClassType getSuperclass()
    {
        this;
        JVM INSTR monitorenter ;
        ClassType classtype;
        if (superClass == null && !isInterface() && !"java.lang.Object".equals(getName()) && (0x10 & flags) != 0 && getReflectClass() != null)
        {
            superClass = (ClassType)make(reflectClass.getSuperclass());
        }
        classtype = superClass;
        this;
        JVM INSTR monitorexit ;
        return classtype;
        Exception exception;
        exception;
        throw exception;
    }

    public final boolean hasOuterLink()
    {
        getFields();
        return (0x20 & flags) != 0;
    }

    public final boolean implementsInterface(ClassType classtype)
    {
        ClassType classtype1;
        if (this != classtype)
        {
            if ((classtype1 = getSuperclass()) == null || !classtype1.implementsInterface(classtype))
            {
label0:
                {
                    ClassType aclasstype[] = getInterfaces();
                    if (aclasstype == null)
                    {
                        break label0;
                    }
                    int i = aclasstype.length;
                    do
                    {
                        if (--i < 0)
                        {
                            break label0;
                        }
                    } while (!aclasstype[i].implementsInterface(classtype));
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    public boolean isAccessible(ClassType classtype, ObjectType objecttype, int i)
    {
        int j = classtype.getModifiers();
        if ((i & 1) == 0 || (j & 1) == 0)
        {
            String s = getName();
            String s1 = classtype.getName();
            if (!s.equals(s1))
            {
                if ((i & 2) != 0)
                {
                    return false;
                }
                int k = s.lastIndexOf('.');
                String s2;
                int l;
                String s3;
                if (k >= 0)
                {
                    s2 = s.substring(0, k);
                } else
                {
                    s2 = "";
                }
                l = s1.lastIndexOf('.');
                if (l >= 0)
                {
                    s3 = s1.substring(0, l);
                } else
                {
                    s3 = "";
                }
                if (!s2.equals(s3))
                {
                    if ((j & 1) == 0)
                    {
                        return false;
                    }
                    if ((i & 4) == 0 || !isSubclass(classtype) || (objecttype instanceof ClassType) && !((ClassType)objecttype).isSubclass(this))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean isAccessible(Member member, ObjectType objecttype)
    {
        if (member.getStaticFlag())
        {
            objecttype = null;
        }
        return isAccessible(member.getDeclaringClass(), objecttype, member.getModifiers());
    }

    public final boolean isInterface()
    {
        return (0x200 & getModifiers()) != 0;
    }

    public final boolean isSubclass(ClassType classtype)
    {
        boolean flag = true;
        if (!classtype.isInterface()) goto _L2; else goto _L1
_L1:
        flag = implementsInterface(classtype);
_L4:
        return flag;
_L2:
        if ((this != toStringType || classtype != javalangStringType) && (this != javalangStringType || classtype != toStringType))
        {
            ClassType classtype1 = this;
label0:
            do
            {
label1:
                {
                    if (classtype1 == null)
                    {
                        break label1;
                    }
                    if (classtype1 == classtype)
                    {
                        break label0;
                    }
                    classtype1 = classtype1.getSuperclass();
                }
            } while (true);
        }
        if (true) goto _L4; else goto _L3
_L3:
        return false;
    }

    public final boolean isSubclass(String s)
    {
        ClassType classtype = this;
        do
        {
            if (s.equals(classtype.getName()))
            {
                return true;
            }
            classtype = classtype.getSuperclass();
        } while (classtype != null);
        return false;
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        setName(objectinput.readUTF());
        flags = 0x10 | flags;
    }

    public Object readResolve()
        throws ObjectStreamException
    {
        String s = getName();
        HashMap hashmap = mapNameToType;
        hashmap;
        JVM INSTR monitorenter ;
        Type type = (Type)hashmap.get(s);
        if (type == null)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        hashmap;
        JVM INSTR monitorexit ;
        return type;
        hashmap.put(s, this);
        hashmap;
        JVM INSTR monitorexit ;
        return this;
        Exception exception;
        exception;
        hashmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final void setAttributes(Attribute attribute)
    {
        attributes = attribute;
    }

    public void setClassfileVersion(int i)
    {
        classfileFormatVersion = i;
    }

    public void setClassfileVersion(int i, int j)
    {
        classfileFormatVersion = 0x10000 * (i & 0xffff) + j * 65535;
    }

    public void setClassfileVersionJava5()
    {
        setClassfileVersion(0x310000);
    }

    public void setEnclosingMember(Member member)
    {
        enclosingMember = member;
    }

    public final void setInterface(boolean flag)
    {
        if (flag)
        {
            access_flags = 0x600 | access_flags;
            return;
        } else
        {
            access_flags = 0xfffffdff & access_flags;
            return;
        }
    }

    public void setInterfaces(ClassType aclasstype[])
    {
        interfaces = aclasstype;
    }

    public final void setModifiers(int i)
    {
        access_flags = i;
    }

    public void setName(String s)
    {
        this_name = s;
        setSignature((new StringBuilder()).append("L").append(s.replace('.', '/')).append(";").toString());
    }

    public final gnu.bytecode.Field setOuterLink(ClassType classtype)
    {
        if ((0x10 & flags) != 0)
        {
            throw new Error((new StringBuilder()).append("setOuterLink called for existing class ").append(getName()).toString());
        }
        gnu.bytecode.Field field = getDeclaredField("this$0");
        if (field == null)
        {
            field = addField("this$0", classtype);
            flags = 0x20 | flags;
            for (gnu.bytecode.Method method = methods; method != null; method = method.getNext())
            {
                if ("<init>".equals(method.getName()))
                {
                    if (method.code != null)
                    {
                        throw new Error((new StringBuilder()).append("setOuterLink called when ").append(method).append(" has code").toString());
                    }
                    Type atype[] = method.arg_types;
                    Type atype1[] = new Type[1 + atype.length];
                    System.arraycopy(atype, 0, atype1, 1, atype.length);
                    atype1[0] = classtype;
                    method.arg_types = atype1;
                    method.signature = null;
                }
            }

        } else
        if (!classtype.equals(field.getType()))
        {
            throw new Error((new StringBuilder()).append("inconsistent setOuterLink call for ").append(getName()).toString());
        }
        return field;
    }

    public void setSourceFile(String s)
    {
        if (sourceDbgExt != null)
        {
            sourceDbgExt.addFile(s);
            if (sourceDbgExt.fileCount > 1)
            {
                return;
            }
        }
        String s1 = SourceFileAttr.fixSourceFile(s);
        int i = s1.lastIndexOf('/');
        if (i >= 0)
        {
            s1 = s1.substring(i + 1);
        }
        SourceFileAttr.setSourceFile(this, s1);
    }

    public void setStratum(String s)
    {
        if (sourceDbgExt == null)
        {
            sourceDbgExt = new SourceDebugExtAttr(this);
        }
        sourceDbgExt.addStratum(s);
    }

    public void setSuper(ClassType classtype)
    {
        superClass = classtype;
    }

    public void setSuper(String s)
    {
        ClassType classtype;
        if (s == null)
        {
            classtype = Type.pointer_type;
        } else
        {
            classtype = make(s);
        }
        setSuper(classtype);
    }

    public String toString()
    {
        return (new StringBuilder()).append("ClassType ").append(getName()).toString();
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeUTF(getName());
    }

    public byte[] writeToArray()
    {
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream(500);
        try
        {
            writeToStream(bytearrayoutputstream);
        }
        catch (IOException ioexception)
        {
            throw new InternalError(ioexception.toString());
        }
        return bytearrayoutputstream.toByteArray();
    }

    public void writeToFile()
        throws IOException
    {
        writeToFile((new StringBuilder()).append(this_name.replace('.', File.separatorChar)).append(".class").toString());
    }

    public void writeToFile(String s)
        throws IOException
    {
        BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(new FileOutputStream(s));
        writeToStream(bufferedoutputstream);
        bufferedoutputstream.close();
    }

    public void writeToStream(OutputStream outputstream)
        throws IOException
    {
        DataOutputStream dataoutputstream = new DataOutputStream(outputstream);
        doFixups();
        dataoutputstream.writeInt(0xcafebabe);
        dataoutputstream.writeShort(getClassfileMinorVersion());
        dataoutputstream.writeShort(getClassfileMajorVersion());
        if (constants == null)
        {
            dataoutputstream.writeShort(1);
        } else
        {
            constants.write(dataoutputstream);
        }
        dataoutputstream.writeShort(access_flags);
        dataoutputstream.writeShort(thisClassIndex);
        dataoutputstream.writeShort(superClassIndex);
        if (interfaceIndexes == null)
        {
            dataoutputstream.writeShort(0);
        } else
        {
            int i = interfaceIndexes.length;
            dataoutputstream.writeShort(i);
            int j = 0;
            while (j < i) 
            {
                dataoutputstream.writeShort(interfaceIndexes[j]);
                j++;
            }
        }
        dataoutputstream.writeShort(fields_count);
        for (gnu.bytecode.Field field = fields; field != null; field = field.next)
        {
            field.write(dataoutputstream, this);
        }

        dataoutputstream.writeShort(methods_count);
        for (gnu.bytecode.Method method = methods; method != null; method = method.next)
        {
            method.write(dataoutputstream, this);
        }

        Attribute.writeAll(this, dataoutputstream);
        flags = 3 | flags;
    }

}
