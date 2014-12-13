// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package gnu.bytecode:
//            Attribute, CodeAttr, ClassTypeWriter

public class LineNumbersAttr extends Attribute
{

    int linenumber_count;
    short linenumber_table[];

    public LineNumbersAttr(CodeAttr codeattr)
    {
        super("LineNumberTable");
        addToFrontOf(codeattr);
        codeattr.lines = this;
    }

    public LineNumbersAttr(short aword0[], CodeAttr codeattr)
    {
        this(codeattr);
        linenumber_table = aword0;
        linenumber_count = aword0.length >> 1;
    }

    public final int getLength()
    {
        return 2 + 4 * linenumber_count;
    }

    public int getLineCount()
    {
        return linenumber_count;
    }

    public short[] getLineNumberTable()
    {
        return linenumber_table;
    }

    public void print(ClassTypeWriter classtypewriter)
    {
        classtypewriter.print("Attribute \"");
        classtypewriter.print(getName());
        classtypewriter.print("\", length:");
        classtypewriter.print(getLength());
        classtypewriter.print(", count: ");
        classtypewriter.println(linenumber_count);
        for (int i = 0; i < linenumber_count; i++)
        {
            classtypewriter.print("  line: ");
            classtypewriter.print(0xffff & linenumber_table[1 + i * 2]);
            classtypewriter.print(" at pc: ");
            classtypewriter.println(0xffff & linenumber_table[i * 2]);
        }

    }

    public void put(int i, int j)
    {
        if (linenumber_table != null) goto _L2; else goto _L1
_L1:
        linenumber_table = new short[32];
_L4:
        linenumber_table[2 * linenumber_count] = (short)j;
        linenumber_table[1 + 2 * linenumber_count] = (short)i;
        linenumber_count = 1 + linenumber_count;
        return;
_L2:
        if (2 * linenumber_count >= linenumber_table.length)
        {
            short aword0[] = new short[2 * linenumber_table.length];
            System.arraycopy(linenumber_table, 0, aword0, 0, 2 * linenumber_count);
            linenumber_table = aword0;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void write(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeShort(linenumber_count);
        int i = 2 * linenumber_count;
        for (int j = 0; j < i; j++)
        {
            dataoutputstream.writeShort(linenumber_table[j]);
        }

    }
}
