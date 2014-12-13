// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package gnu.bytecode:
//            Attribute, ClassType, ConstantPool, CpoolClass, 
//            ClassTypeWriter, Method

public class ExceptionsAttr extends Attribute
{

    short exception_table[];
    ClassType exceptions[];

    public ExceptionsAttr(Method method)
    {
        super("Exceptions");
        addToFrontOf(method);
    }

    public void assignConstants(ClassType classtype)
    {
        super.assignConstants(classtype);
        ConstantPool constantpool = classtype.getConstants();
        int i = exceptions.length;
        exception_table = new short[i];
        for (int j = i - 1; j >= 0; j--)
        {
            exception_table[j] = (short)constantpool.addClass(exceptions[j]).index;
        }

    }

    public final ClassType[] getExceptions()
    {
        return exceptions;
    }

    public final int getLength()
    {
        int i;
        if (exceptions == null)
        {
            i = 0;
        } else
        {
            i = exceptions.length;
        }
        return 2 + i * 2;
    }

    public void print(ClassTypeWriter classtypewriter)
    {
        classtypewriter.print("Attribute \"");
        classtypewriter.print(getName());
        classtypewriter.print("\", length:");
        classtypewriter.print(getLength());
        classtypewriter.print(", count: ");
        int i = exceptions.length;
        classtypewriter.println(i);
        for (int j = 0; j < i; j++)
        {
            int k = 0xffff & exception_table[j];
            classtypewriter.print("  ");
            classtypewriter.printOptionalIndex(k);
            classtypewriter.printConstantTersely(k, 7);
            classtypewriter.println();
        }

    }

    public void setExceptions(ClassType aclasstype[])
    {
        exceptions = aclasstype;
    }

    public void setExceptions(short aword0[], ClassType classtype)
    {
        exception_table = aword0;
        exceptions = new ClassType[aword0.length];
        ConstantPool constantpool = classtype.getConstants();
        for (int i = -1 + aword0.length; i >= 0; i--)
        {
            exceptions[i] = (ClassType)((CpoolClass)constantpool.getPoolEntry(aword0[i])).getClassType();
        }

    }

    public void write(DataOutputStream dataoutputstream)
        throws IOException
    {
        int i = exceptions.length;
        dataoutputstream.writeShort(i);
        for (int j = 0; j < i; j++)
        {
            dataoutputstream.writeShort(exception_table[j]);
        }

    }
}
