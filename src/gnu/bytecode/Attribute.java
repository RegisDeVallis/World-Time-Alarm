// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package gnu.bytecode:
//            AttrContainer, ClassType, ConstantPool, CpoolUtf8, 
//            ClassTypeWriter

public abstract class Attribute
{

    AttrContainer container;
    String name;
    int name_index;
    Attribute next;

    public Attribute(String s)
    {
        name = s;
    }

    public static void assignConstants(AttrContainer attrcontainer, ClassType classtype)
    {
        for (Attribute attribute = attrcontainer.getAttributes(); attribute != null; attribute = attribute.next)
        {
            if (!attribute.isSkipped())
            {
                attribute.assignConstants(classtype);
            }
        }

    }

    public static int count(AttrContainer attrcontainer)
    {
        int i = 0;
        for (Attribute attribute = attrcontainer.getAttributes(); attribute != null; attribute = attribute.next)
        {
            if (!attribute.isSkipped())
            {
                i++;
            }
        }

        return i;
    }

    public static Attribute get(AttrContainer attrcontainer, String s)
    {
        for (Attribute attribute = attrcontainer.getAttributes(); attribute != null; attribute = attribute.next)
        {
            if (attribute.getName() == s)
            {
                return attribute;
            }
        }

        return null;
    }

    public static int getLengthAll(AttrContainer attrcontainer)
    {
        int i = 0;
        for (Attribute attribute = attrcontainer.getAttributes(); attribute != null; attribute = attribute.next)
        {
            if (!attribute.isSkipped())
            {
                i += 6 + attribute.getLength();
            }
        }

        return i;
    }

    public static void writeAll(AttrContainer attrcontainer, DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeShort(count(attrcontainer));
        Attribute attribute = attrcontainer.getAttributes();
        while (attribute != null) 
        {
            if (!attribute.isSkipped())
            {
                if (attribute.name_index == 0)
                {
                    throw new Error("Attribute.writeAll called without assignConstants");
                }
                dataoutputstream.writeShort(attribute.name_index);
                dataoutputstream.writeInt(attribute.getLength());
                attribute.write(dataoutputstream);
            }
            attribute = attribute.next;
        }
    }

    public void addToFrontOf(AttrContainer attrcontainer)
    {
        setContainer(attrcontainer);
        setNext(attrcontainer.getAttributes());
        attrcontainer.setAttributes(this);
    }

    public void assignConstants(ClassType classtype)
    {
        if (name_index == 0)
        {
            name_index = classtype.getConstants().addUtf8(name).getIndex();
        }
    }

    public final AttrContainer getContainer()
    {
        return container;
    }

    public abstract int getLength();

    public final String getName()
    {
        return name;
    }

    public final int getNameIndex()
    {
        return name_index;
    }

    public final Attribute getNext()
    {
        return next;
    }

    public final boolean isSkipped()
    {
        return name_index < 0;
    }

    public void print(ClassTypeWriter classtypewriter)
    {
        classtypewriter.print("Attribute \"");
        classtypewriter.print(getName());
        classtypewriter.print("\", length:");
        classtypewriter.println(getLength());
    }

    public final void setContainer(AttrContainer attrcontainer)
    {
        container = attrcontainer;
    }

    public final void setName(String s)
    {
        name = s.intern();
    }

    public final void setNameIndex(int i)
    {
        name_index = i;
    }

    public final void setNext(Attribute attribute)
    {
        next = attribute;
    }

    public final void setSkipped()
    {
        name_index = -1;
    }

    public final void setSkipped(boolean flag)
    {
        int i;
        if (flag)
        {
            i = -1;
        } else
        {
            i = 0;
        }
        name_index = i;
    }

    public abstract void write(DataOutputStream dataoutputstream)
        throws IOException;
}
