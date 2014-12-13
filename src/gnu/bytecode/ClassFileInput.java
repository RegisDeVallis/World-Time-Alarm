// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package gnu.bytecode:
//            ClassType, ConstantPool, CpoolClass, SourceFileAttr, 
//            Method, CodeAttr, LineNumbersAttr, LocalVarsAttr, 
//            Scope, Label, Variable, Member, 
//            SignatureAttr, StackMapTableAttr, Field, RuntimeAnnotationsAttr, 
//            ConstantValueAttr, InnerClassesAttr, EnclosingMethodAttr, SourceDebugExtAttr, 
//            MiscAttr, AttrContainer, Attribute, CpoolUtf8

public class ClassFileInput extends DataInputStream
{

    ClassType ctype;
    InputStream str;

    public ClassFileInput(ClassType classtype, InputStream inputstream)
        throws IOException, ClassFormatError
    {
        super(inputstream);
        ctype = classtype;
        if (!readHeader())
        {
            throw new ClassFormatError("invalid magic number");
        } else
        {
            classtype.constants = readConstants();
            readClassInfo();
            readFields();
            readMethods();
            readAttributes(classtype);
            return;
        }
    }

    public ClassFileInput(InputStream inputstream)
        throws IOException
    {
        super(inputstream);
    }

    public static ClassType readClassType(InputStream inputstream)
        throws IOException, ClassFormatError
    {
        ClassType classtype = new ClassType();
        new ClassFileInput(classtype, inputstream);
        return classtype;
    }

    CpoolClass getClassConstant(int i)
    {
        return (CpoolClass)ctype.constants.getForced(i, 7);
    }

    public Attribute readAttribute(String s, int i, AttrContainer attrcontainer)
        throws IOException
    {
        if (s != "SourceFile" || !(attrcontainer instanceof ClassType)) goto _L2; else goto _L1
_L1:
        Object obj = new SourceFileAttr(readUnsignedShort(), (ClassType)attrcontainer);
_L4:
        return ((Attribute) (obj));
_L2:
        if (s == "Code" && (attrcontainer instanceof Method))
        {
            CodeAttr codeattr1 = new CodeAttr((Method)attrcontainer);
            codeattr1.fixup_count = -1;
            codeattr1.setMaxStack(readUnsignedShort());
            codeattr1.setMaxLocals(readUnsignedShort());
            byte abyte4[] = new byte[readInt()];
            readFully(abyte4);
            codeattr1.setCode(abyte4);
            int j3 = readUnsignedShort();
            for (int k3 = 0; k3 < j3; k3++)
            {
                codeattr1.addHandler(readUnsignedShort(), readUnsignedShort(), readUnsignedShort(), readUnsignedShort());
            }

            readAttributes(codeattr1);
            return codeattr1;
        }
        if (s == "LineNumberTable" && (attrcontainer instanceof CodeAttr))
        {
            int l2 = 2 * readUnsignedShort();
            short aword2[] = new short[l2];
            for (int i3 = 0; i3 < l2; i3++)
            {
                aword2[i3] = readShort();
            }

            return new LineNumbersAttr(aword2, (CodeAttr)attrcontainer);
        }
        if (s != "LocalVariableTable" || !(attrcontainer instanceof CodeAttr))
        {
            break; /* Loop/switch isn't completed */
        }
        CodeAttr codeattr = (CodeAttr)attrcontainer;
        obj = new LocalVarsAttr(codeattr);
        Method method1 = ((LocalVarsAttr) (obj)).getMethod();
        if (((LocalVarsAttr) (obj)).parameter_scope == null)
        {
            obj.parameter_scope = method1.pushScope();
        }
        Scope scope = ((LocalVarsAttr) (obj)).parameter_scope;
        if (scope.end == null)
        {
            Label label = new Label(codeattr.PC);
            scope.end = label;
        }
        ConstantPool constantpool = method1.getConstants();
        int j1 = readUnsignedShort();
        int k1 = scope.start.position;
        int l1 = scope.end.position;
        int i2 = 0;
        while (i2 < j1) 
        {
            Variable variable = new Variable();
            int j2 = readUnsignedShort();
            int k2 = j2 + readUnsignedShort();
            if (j2 != k1 || k2 != l1)
            {
                for (; scope.parent != null && (j2 < scope.start.position || k2 > scope.end.position); scope = scope.parent) { }
                Scope scope1 = scope;
                Label label1 = new Label(j2);
                Label label2 = new Label(k2);
                scope = new Scope(label1, label2);
                scope.linkChild(scope1);
                k1 = j2;
                l1 = k2;
            }
            scope.addVariable(variable);
            variable.setName(readUnsignedShort(), constantpool);
            variable.setSignature(readUnsignedShort(), constantpool);
            variable.offset = readUnsignedShort();
            i2++;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (s == "Signature" && (attrcontainer instanceof Member))
        {
            return new SignatureAttr(readUnsignedShort(), (Member)attrcontainer);
        }
        if (s == "StackMapTable" && (attrcontainer instanceof CodeAttr))
        {
            byte abyte3[] = new byte[i];
            readFully(abyte3, 0, i);
            return new StackMapTableAttr(abyte3, (CodeAttr)attrcontainer);
        }
        if ((s == "RuntimeVisibleAnnotations" || s == "RuntimeInvisibleAnnotations") && ((attrcontainer instanceof Field) || (attrcontainer instanceof Method) || (attrcontainer instanceof ClassType)))
        {
            byte abyte0[] = new byte[i];
            readFully(abyte0, 0, i);
            return new RuntimeAnnotationsAttr(s, abyte0, attrcontainer);
        }
        if (s == "ConstantValue" && (attrcontainer instanceof Field))
        {
            return new ConstantValueAttr(readUnsignedShort());
        }
        if (s == "InnerClasses" && (attrcontainer instanceof ClassType))
        {
            int l = 4 * readUnsignedShort();
            short aword1[] = new short[l];
            for (int i1 = 0; i1 < l; i1++)
            {
                aword1[i1] = readShort();
            }

            return new InnerClassesAttr(aword1, (ClassType)attrcontainer);
        }
        if (s == "EnclosingMethod" && (attrcontainer instanceof ClassType))
        {
            return new EnclosingMethodAttr(readUnsignedShort(), readUnsignedShort(), (ClassType)attrcontainer);
        }
        if (s == "Exceptions" && (attrcontainer instanceof Method))
        {
            Method method = (Method)attrcontainer;
            int j = readUnsignedShort();
            short aword0[] = new short[j];
            for (int k = 0; k < j; k++)
            {
                aword0[k] = readShort();
            }

            method.setExceptions(aword0);
            return method.getExceptionAttr();
        }
        if (s == "SourceDebugExtension" && (attrcontainer instanceof ClassType))
        {
            SourceDebugExtAttr sourcedebugextattr = new SourceDebugExtAttr((ClassType)attrcontainer);
            byte abyte2[] = new byte[i];
            readFully(abyte2, 0, i);
            sourcedebugextattr.data = abyte2;
            sourcedebugextattr.dlength = i;
            return sourcedebugextattr;
        } else
        {
            byte abyte1[] = new byte[i];
            readFully(abyte1, 0, i);
            return new MiscAttr(s, abyte1);
        }
    }

    public int readAttributes(AttrContainer attrcontainer)
        throws IOException
    {
        int i;
        Attribute attribute;
        int j;
        i = readUnsignedShort();
        attribute = attrcontainer.getAttributes();
        j = 0;
_L7:
        if (j >= i) goto _L2; else goto _L1
_L1:
        if (attribute == null) goto _L4; else goto _L3
_L3:
        Attribute attribute2 = attribute.getNext();
        if (attribute2 != null) goto _L5; else goto _L4
_L4:
        int k = readUnsignedShort();
        CpoolUtf8 cpoolutf8 = (CpoolUtf8)ctype.constants.getForced(k, 1);
        int l = readInt();
        cpoolutf8.intern();
        Attribute attribute1 = readAttribute(cpoolutf8.string, l, attrcontainer);
        if (attribute1 != null)
        {
            if (attribute1.getNameIndex() == 0)
            {
                attribute1.setNameIndex(k);
            }
            if (attribute == null)
            {
                attrcontainer.setAttributes(attribute1);
            } else
            {
                if (attrcontainer.getAttributes() == attribute1)
                {
                    attrcontainer.setAttributes(attribute1.getNext());
                    attribute1.setNext(null);
                }
                attribute.setNext(attribute1);
            }
            attribute = attribute1;
        }
        j++;
        continue; /* Loop/switch isn't completed */
_L5:
        attribute = attribute2;
        if (true) goto _L3; else goto _L2
_L2:
        return i;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public void readClassInfo()
        throws IOException
    {
        ctype.access_flags = readUnsignedShort();
        ctype.thisClassIndex = readUnsignedShort();
        String s = getClassConstant(ctype.thisClassIndex).name.string;
        ctype.this_name = s.replace('/', '.');
        ctype.setSignature((new StringBuilder()).append("L").append(s).append(";").toString());
        ctype.superClassIndex = readUnsignedShort();
        int i;
        if (ctype.superClassIndex == 0)
        {
            ctype.setSuper((ClassType)null);
        } else
        {
            String s1 = getClassConstant(ctype.superClassIndex).name.string;
            ctype.setSuper(s1.replace('/', '.'));
        }
        i = readUnsignedShort();
        if (i > 0)
        {
            ctype.interfaces = new ClassType[i];
            ctype.interfaceIndexes = new int[i];
            for (int j = 0; j < i; j++)
            {
                int k = readUnsignedShort();
                ctype.interfaceIndexes[j] = k;
                String s2 = ((CpoolClass)ctype.constants.getForced(k, 7)).name.string.replace('/', '.');
                ctype.interfaces[j] = ClassType.make(s2);
            }

        }
    }

    public ConstantPool readConstants()
        throws IOException
    {
        return new ConstantPool(this);
    }

    public void readFields()
        throws IOException
    {
        int i = readUnsignedShort();
        ConstantPool constantpool = ctype.constants;
        for (int j = 0; j < i; j++)
        {
            int k = readUnsignedShort();
            int l = readUnsignedShort();
            int i1 = readUnsignedShort();
            Field field = ctype.addField();
            field.setName(l, constantpool);
            field.setSignature(i1, constantpool);
            field.flags = k;
            readAttributes(field);
        }

    }

    public void readFormatVersion()
        throws IOException
    {
        int i = readUnsignedShort();
        int j = readUnsignedShort();
        ctype.classfileFormatVersion = i + 0x10000 * j;
    }

    public boolean readHeader()
        throws IOException
    {
        if (readInt() != 0xcafebabe)
        {
            return false;
        } else
        {
            readFormatVersion();
            return true;
        }
    }

    public void readMethods()
        throws IOException
    {
        int i = readUnsignedShort();
        for (int j = 0; j < i; j++)
        {
            int k = readUnsignedShort();
            int l = readUnsignedShort();
            int i1 = readUnsignedShort();
            Method method = ctype.addMethod(null, k);
            method.setName(l);
            method.setSignature(i1);
            readAttributes(method);
        }

    }

    public final void skipAttribute(int i)
        throws IOException
    {
        int k;
        for (int j = 0; j < i; j += k)
        {
            k = (int)skip(i - j);
            if (k != 0)
            {
                continue;
            }
            if (read() < 0)
            {
                throw new EOFException("EOF while reading class files attributes");
            }
            k = 1;
        }

    }
}
