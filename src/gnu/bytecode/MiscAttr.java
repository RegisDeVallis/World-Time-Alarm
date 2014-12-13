// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package gnu.bytecode:
//            Attribute, ClassTypeWriter

public class MiscAttr extends Attribute
{

    byte data[];
    int dataLength;
    int offset;

    public MiscAttr(String s, byte abyte0[])
    {
        this(s, abyte0, 0, abyte0.length);
    }

    public MiscAttr(String s, byte abyte0[], int i, int j)
    {
        super(s);
        data = abyte0;
        offset = i;
        dataLength = j;
    }

    public int getLength()
    {
        return dataLength;
    }

    public void print(ClassTypeWriter classtypewriter)
    {
        super.print(classtypewriter);
        int i = getLength();
        int j = 0;
        do
        {
            if (j >= i)
            {
                break;
            }
            byte byte0 = data[j];
            if (j % 20 == 0)
            {
                classtypewriter.print(' ');
            }
            classtypewriter.print(' ');
            classtypewriter.print(Character.forDigit(0xf & byte0 >> 4, 16));
            classtypewriter.print(Character.forDigit(byte0 & 0xf, 16));
            if (++j % 20 == 0 || j == i)
            {
                classtypewriter.println();
            }
        } while (true);
    }

    protected void put1(int i)
    {
        if (data != null) goto _L2; else goto _L1
_L1:
        data = new byte[20];
_L4:
        byte abyte1[] = data;
        int j = dataLength;
        dataLength = j + 1;
        abyte1[j] = (byte)i;
        return;
_L2:
        if (dataLength >= data.length)
        {
            byte abyte0[] = new byte[2 * data.length];
            System.arraycopy(data, 0, abyte0, 0, dataLength);
            data = abyte0;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void put2(int i)
    {
        put1((byte)(i >> 8));
        put1((byte)i);
    }

    protected void put2(int i, int j)
    {
        data[i] = (byte)(j >> 8);
        data[i + 1] = (byte)j;
    }

    protected int u1()
    {
        int i = offset;
        offset = i + 1;
        return u1(i);
    }

    protected int u1(int i)
    {
        return 0xff & data[i];
    }

    protected int u2()
    {
        int i = u2(offset);
        offset = 2 + offset;
        return i;
    }

    protected int u2(int i)
    {
        return ((0xff & data[i]) << 8) + (0xff & data[i + 1]);
    }

    public void write(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.write(data, offset, dataLength);
    }
}
