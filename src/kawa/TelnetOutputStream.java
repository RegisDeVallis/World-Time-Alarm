// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TelnetOutputStream extends FilterOutputStream
{

    public TelnetOutputStream(OutputStream outputstream)
    {
        super(outputstream);
    }

    public void write(int i)
        throws IOException
    {
        if (i == 255)
        {
            out.write(i);
        }
        out.write(i);
    }

    public void write(byte abyte0[])
        throws IOException
    {
        write(abyte0, 0, abyte0.length);
    }

    public void write(byte abyte0[], int i, int j)
        throws IOException
    {
        int k = i + j;
        for (int l = i; l < k; l++)
        {
            if (abyte0[l] == -1)
            {
                out.write(abyte0, i, (l + 1) - i);
                i = l;
            }
        }

        out.write(abyte0, i, k - i);
    }

    public void writeCommand(int i)
        throws IOException
    {
        out.write(255);
        out.write(i);
    }

    public final void writeCommand(int i, int j)
        throws IOException
    {
        out.write(255);
        out.write(i);
        out.write(j);
    }

    public final void writeDo(int i)
        throws IOException
    {
        writeCommand(253, i);
    }

    public final void writeDont(int i)
        throws IOException
    {
        writeCommand(254, i);
    }

    public final void writeSubCommand(int i, byte abyte0[])
        throws IOException
    {
        writeCommand(250, i);
        write(abyte0);
        writeCommand(240);
    }

    public final void writeWill(int i)
        throws IOException
    {
        writeCommand(251, i);
    }

    public final void writeWont(int i)
        throws IOException
    {
        writeCommand(252, i);
    }
}
