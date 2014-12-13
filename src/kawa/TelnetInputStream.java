// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

// Referenced classes of package kawa:
//            Telnet

public class TelnetInputStream extends FilterInputStream
{

    static final int SB_IAC = 400;
    protected byte buf[];
    Telnet connection;
    int count;
    int pos;
    int state;
    int subCommandLength;

    public TelnetInputStream(InputStream inputstream, Telnet telnet)
        throws IOException
    {
        super(inputstream);
        state = 0;
        subCommandLength = 0;
        buf = new byte[512];
        connection = telnet;
    }

    public int read()
        throws IOException
    {
_L7:
        if (pos < count) goto _L2; else goto _L1
_L1:
        int j;
        int i1 = in.available();
        int j1;
        if (i1 <= 0)
        {
            i1 = 1;
        } else
        if (i1 > buf.length - subCommandLength)
        {
            i1 = buf.length - subCommandLength;
        }
        j1 = in.read(buf, subCommandLength, i1);
        pos = subCommandLength;
        count = j1;
        if (j1 > 0) goto _L2; else goto _L3
_L3:
        j = -1;
_L5:
        return j;
_L2:
        byte abyte0[] = buf;
        int i = pos;
        pos = i + 1;
        j = 0xff & abyte0[i];
        if (state != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        if (j == 255)
        {
            state = 255;
            continue; /* Loop/switch isn't completed */
        }
        if (true) goto _L5; else goto _L4
_L4:
        if (state == 255)
        {
            if (j == 255)
            {
                state = 0;
                return 255;
            }
            if (j == 251 || j == 252 || j == 253 || j == 254 || j == 250)
            {
                state = j;
            } else
            if (j == 244)
            {
                System.err.println("Interrupt Process");
                state = 0;
            } else
            {
                if (j == 236)
                {
                    return -1;
                }
                state = 0;
            }
        } else
        if (state == 251 || state == 252 || state == 253 || state == 254)
        {
            connection.handle(state, j);
            state = 0;
        } else
        if (state == 250)
        {
            if (j == 255)
            {
                state = 400;
            } else
            {
                byte abyte2[] = buf;
                int l = subCommandLength;
                subCommandLength = l + 1;
                abyte2[l] = (byte)j;
            }
        } else
        if (state == 400)
        {
            if (j == 255)
            {
                byte abyte1[] = buf;
                int k = subCommandLength;
                subCommandLength = k + 1;
                abyte1[k] = (byte)j;
                state = 250;
            } else
            if (j == 240)
            {
                connection.subCommand(buf, 0, subCommandLength);
                state = 0;
                subCommandLength = 0;
            } else
            {
                state = 0;
                subCommandLength = 0;
            }
        } else
        {
            System.err.println((new StringBuilder()).append("Bad state ").append(state).toString());
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    public int read(byte abyte0[], int i, int j)
        throws IOException
    {
        if (j > 0) goto _L2; else goto _L1
_L1:
        int k = 0;
_L4:
        return k;
_L2:
        int i1;
        if (state == 0)
        {
            int k1 = pos;
            int l1 = count;
            i1 = 0;
            if (k1 < l1)
            {
                break; /* Loop/switch isn't completed */
            }
        }
        k = read();
        if (k < 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        int l = i + 1;
        abyte0[i] = (byte)k;
        i1 = 0 + 1;
        i = l;
        break; /* Loop/switch isn't completed */
        if (true) goto _L4; else goto _L3
_L3:
        if (state != 0) goto _L6; else goto _L5
_L5:
        if (pos >= count || i1 >= j) goto _L6; else goto _L7
_L7:
        byte byte0 = buf[pos];
        if (byte0 != -1) goto _L8; else goto _L6
_L6:
        return i1;
_L8:
        int j1 = i + 1;
        abyte0[i] = byte0;
        i1++;
        pos = 1 + pos;
        i = j1;
        if (true) goto _L5; else goto _L9
_L9:
    }
}
