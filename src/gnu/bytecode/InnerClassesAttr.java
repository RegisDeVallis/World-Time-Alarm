// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package gnu.bytecode:
//            Attribute, ClassType, CpoolClass, ConstantPool, 
//            CpoolUtf8, ClassTypeWriter, Access

public class InnerClassesAttr extends Attribute
{

    int count;
    short data[];

    public InnerClassesAttr(ClassType classtype)
    {
        super("InnerClasses");
        addToFrontOf(classtype);
    }

    public InnerClassesAttr(short aword0[], ClassType classtype)
    {
        this(classtype);
        count = (short)(aword0.length >> 2);
        data = aword0;
    }

    public static InnerClassesAttr getFirstInnerClasses(Attribute attribute)
    {
        do
        {
            if (attribute == null || (attribute instanceof InnerClassesAttr))
            {
                return (InnerClassesAttr)attribute;
            }
            attribute = attribute.next;
        } while (true);
    }

    void addClass(CpoolClass cpoolclass, ClassType classtype)
    {
        int i = count;
        count = i + 1;
        int j = i * 4;
        ConstantPool constantpool;
        ClassType classtype1;
        String s;
        int k;
        ClassType classtype2;
        short aword1[];
        int l;
        short word0;
        int i1;
        if (data == null)
        {
            data = new short[16];
        } else
        if (j >= data.length)
        {
            short aword0[] = new short[j * 2];
            System.arraycopy(data, 0, aword0, 0, j);
            data = aword0;
        }
        constantpool = classtype.constants;
        classtype1 = (ClassType)cpoolclass.getClassType();
        s = classtype1.getSimpleName();
        if (s == null || s.length() == 0)
        {
            k = 0;
        } else
        {
            k = constantpool.addUtf8(s).index;
        }
        data[j] = (short)cpoolclass.index;
        classtype2 = classtype1.getDeclaringClass();
        aword1 = data;
        l = j + 1;
        word0 = 0;
        if (classtype2 != null)
        {
            word0 = (short)constantpool.addClass(classtype2).index;
        }
        aword1[l] = word0;
        data[j + 2] = (short)k;
        i1 = 0xffffffdf & classtype1.getModifiers();
        data[j + 3] = (short)i1;
    }

    public void assignConstants(ClassType classtype)
    {
        super.assignConstants(classtype);
    }

    public int getLength()
    {
        return 2 + 8 * count;
    }

    public void print(ClassTypeWriter classtypewriter)
    {
        ClassType classtype;
        ConstantPool constantpool;
        int i;
        classtype = (ClassType)container;
        String s2;
        int j1;
        int k1;
        char c;
        if (data == null)
        {
            constantpool = null;
        } else
        {
            constantpool = classtype.getConstants();
        }
        classtypewriter.print("Attribute \"");
        classtypewriter.print(getName());
        classtypewriter.print("\", length:");
        classtypewriter.print(getLength());
        classtypewriter.print(", count: ");
        classtypewriter.println(count);
        i = 0;
_L10:
        if (i >= count) goto _L2; else goto _L1
_L1:
        int j;
        CpoolClass cpoolclass;
        ClassType classtype1;
        int k;
        String s;
        String s1;
        if (constantpool == null)
        {
            j = 0;
        } else
        {
            j = 0xffff & data[i * 4];
        }
        if (constantpool == null || j == 0)
        {
            cpoolclass = null;
        } else
        {
            cpoolclass = constantpool.getForcedClass(j);
        }
        if (cpoolclass != null && (cpoolclass.clas instanceof ClassType))
        {
            classtype1 = (ClassType)cpoolclass.clas;
        } else
        {
            classtype1 = null;
        }
        classtypewriter.print(' ');
        if (j == 0 && classtype1 != null)
        {
            k = classtype1.getModifiers();
        } else
        {
            k = 0xffff & data[3 + i * 4];
        }
        classtypewriter.print(Access.toString(k, 'I'));
        classtypewriter.print(' ');
        if (j == 0 && classtype1 != null)
        {
            s = classtype1.getSimpleName();
        } else
        {
            int l = 0xffff & data[2 + i * 4];
            if (constantpool == null || l == 0)
            {
                s = "(Anonymous)";
            } else
            {
                classtypewriter.printOptionalIndex(l);
                s = ((CpoolUtf8)(CpoolUtf8)constantpool.getForced(l, 1)).string;
            }
        }
        classtypewriter.print(s);
        classtypewriter.print(" = ");
        if (cpoolclass != null)
        {
            s1 = cpoolclass.getClassName();
        } else
        {
            s1 = "(Unknown)";
        }
        classtypewriter.print(s1);
        classtypewriter.print("; ");
        if (j != 0 || classtype1 == null) goto _L4; else goto _L3
_L3:
        s2 = classtype1.getName();
        j1 = s2.lastIndexOf('.');
        if (j1 > 0)
        {
            s2 = s2.substring(j1 + 1);
        }
        k1 = 1 + s2.lastIndexOf('$');
        if (k1 >= s2.length()) goto _L6; else goto _L5
_L5:
        c = s2.charAt(k1);
        if (c < '0' || c > '9') goto _L6; else goto _L7
_L7:
        classtypewriter.print("not a member");
_L8:
        classtypewriter.println();
        i++;
        continue; /* Loop/switch isn't completed */
_L6:
        classtypewriter.print("member of ");
        classtypewriter.print(classtype.getName());
        continue; /* Loop/switch isn't completed */
_L4:
        int i1 = 0xffff & data[1 + i * 4];
        if (i1 == 0)
        {
            classtypewriter.print("not a member");
        } else
        {
            classtypewriter.print("member of ");
            classtypewriter.print(((CpoolClass)constantpool.getForced(i1, 7)).getStringName());
        }
        if (true) goto _L8; else goto _L2
_L2:
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public void write(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeShort(count);
        for (int i = 0; i < count; i++)
        {
            dataoutputstream.writeShort(data[i * 4]);
            dataoutputstream.writeShort(data[1 + i * 4]);
            dataoutputstream.writeShort(data[2 + i * 4]);
            dataoutputstream.writeShort(data[3 + i * 4]);
        }

    }
}
