// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

// Referenced classes of package kawa:
//            TelnetOutputStream, TelnetInputStream

public class Telnet
    implements Runnable
{

    public static final int DO = 253;
    public static final int DONT = 254;
    public static final int ECHO = 1;
    static final int EOF = 236;
    static final int IAC = 255;
    static final int IP = 244;
    static final int LINEMODE = 34;
    static final int NAWS = 31;
    static final int NOP = 241;
    static final int OPTION_NO = 0;
    static final int OPTION_WANTNO = 1;
    static final int OPTION_WANTNO_OPPOSITE = 2;
    static final int OPTION_WANTYES = 3;
    static final int OPTION_WANTYES_OPPOSITE = 4;
    static final int OPTION_YES = 5;
    static final int SB = 250;
    static final int SE = 240;
    public static final int SUPPRESS_GO_AHEAD = 3;
    static final int TM = 6;
    static final int TTYPE = 24;
    public static final int WILL = 251;
    public static final int WONT = 252;
    TelnetInputStream in;
    boolean isServer;
    final byte optionsState[] = new byte[256];
    TelnetOutputStream out;
    final byte preferredLineMode = 3;
    InputStream sin;
    OutputStream sout;
    public byte terminalType[];
    public short windowHeight;
    public short windowWidth;

    public Telnet(Socket socket, boolean flag)
        throws IOException
    {
        sin = socket.getInputStream();
        sout = socket.getOutputStream();
        out = new TelnetOutputStream(sout);
        in = new TelnetInputStream(sin, this);
        isServer = flag;
    }

    public static void main(String args[])
    {
        String s;
        int i;
        if (args.length == 0)
        {
            usage();
        }
        s = args[0];
        i = 23;
        if (args.length > 1)
        {
            i = Integer.parseInt(args[1]);
        }
        TelnetOutputStream telnetoutputstream;
        Thread thread;
        byte abyte0[];
        Telnet telnet = new Telnet(new Socket(s, i), false);
        telnetoutputstream = telnet.getOutputStream();
        thread = new Thread(telnet);
        thread.setPriority(1 + Thread.currentThread().getPriority());
        thread.start();
        abyte0 = new byte[1024];
_L1:
        int j = System.in.read();
        if (j < 0)
        {
            int k;
            try
            {
                thread.stop();
                return;
            }
            catch (Exception exception)
            {
                System.err.println(exception);
            }
            break MISSING_BLOCK_LABEL_181;
        }
        abyte0[0] = (byte)j;
        k = System.in.available();
        if (k <= 0)
        {
            break MISSING_BLOCK_LABEL_156;
        }
        if (k > -1 + abyte0.length)
        {
            k = -1 + abyte0.length;
        }
        k = System.in.read(abyte0, 1, k);
        telnetoutputstream.write(abyte0, 0, k + 1);
          goto _L1
    }

    static void usage()
    {
        System.err.println("Usage:  [java] kawa.Telnet HOST [PORT#]");
        System.exit(-1);
    }

    boolean change(int i, int j)
    {
_L2:
        return true;
        if (j == 6 || isServer && j == 31) goto _L2; else goto _L1
_L1:
        if (isServer && i == 251 && j == 34)
        {
            byte abyte1[] = {
                1, 3
            };
            try
            {
                out.writeSubCommand(34, abyte1);
            }
            catch (IOException ioexception1)
            {
                return true;
            }
            return true;
        }
        if (isServer && i == 251 && j == 24)
        {
            byte abyte0[] = {
                1
            };
            try
            {
                out.writeSubCommand(j, abyte0);
            }
            catch (IOException ioexception)
            {
                return true;
            }
            return true;
        }
        if (isServer || j != 1)
        {
            break; /* Loop/switch isn't completed */
        }
        if (i == 253)
        {
            return false;
        }
        if (i == 251) goto _L2; else goto _L3
_L3:
        return false;
    }

    public TelnetInputStream getInputStream()
    {
        return in;
    }

    public TelnetOutputStream getOutputStream()
    {
        return out;
    }

    void handle(int i, int j)
        throws IOException
    {
        boolean flag;
        char c;
        char c1;
        boolean flag1;
        byte byte0;
        flag = true;
        c = '\376';
        c1 = '\375';
        if (i < c1)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        if ((i & 1) == 0)
        {
            flag = false;
        }
        byte0 = optionsState[j];
        if (flag1)
        {
            byte0 >>= 3;
        }
        7 & byte0 >> 3;
        JVM INSTR tableswitch 0 5: default 92
    //                   0 183
    //                   1 263
    //                   2 269
    //                   3 302
    //                   4 325
    //                   5 135;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        break; /* Loop/switch isn't completed */
_L6:
        break MISSING_BLOCK_LABEL_325;
_L10:
        byte byte1;
        TelnetOutputStream telnetoutputstream;
        TelnetOutputStream telnetoutputstream1;
        TelnetOutputStream telnetoutputstream2;
        TelnetOutputStream telnetoutputstream3;
        char c2;
        TelnetOutputStream telnetoutputstream4;
        char c3;
        if (flag1)
        {
            byte1 = (byte)(0xc7 & optionsState[j] | byte0 << 3);
        } else
        {
            byte1 = (byte)(byte0 | 0xf8 & optionsState[j]);
        }
        optionsState[j] = byte1;
_L9:
        return;
_L7:
        if (flag) goto _L9; else goto _L8
_L8:
        change(i, j);
        telnetoutputstream4 = out;
        if (flag1)
        {
            c3 = c;
        } else
        {
            c3 = '\374';
        }
        telnetoutputstream4.writeCommand(c3, j);
        byte0 = 0;
          goto _L10
_L2:
        if (!flag) goto _L9; else goto _L11
_L11:
        if (change(i, j))
        {
            byte0 = 5;
            telnetoutputstream3 = out;
            if (flag1)
            {
                c2 = c1;
            } else
            {
                c2 = '\373';
            }
            telnetoutputstream3.writeCommand(c2, j);
        } else
        {
            telnetoutputstream2 = out;
            if (!flag1)
            {
                c = '\374';
            }
            telnetoutputstream2.writeCommand(c, j);
        }
          goto _L10
_L3:
        byte0 = 0;
          goto _L10
_L4:
        byte0 = 3;
        telnetoutputstream1 = out;
        if (!flag1)
        {
            c1 = '\373';
        }
        telnetoutputstream1.writeCommand(c1, j);
          goto _L10
_L5:
        if (flag)
        {
            byte0 = 5;
            change(i, j);
        } else
        {
            byte0 = 0;
        }
          goto _L10
        if (flag)
        {
            byte0 = 1;
            telnetoutputstream = out;
            if (!flag1)
            {
                c = '\374';
            }
            telnetoutputstream.writeCommand(c, j);
        } else
        {
            byte0 = 0;
        }
          goto _L10
    }

    public void request(int i, int j)
        throws IOException
    {
        boolean flag;
        byte byte0;
        flag = true;
        boolean flag1;
        if (i >= 253)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        if ((i & 1) == 0)
        {
            flag = false;
        }
        byte0 = optionsState[j];
        if (flag1)
        {
            byte0 >>= 3;
        }
        byte0 & 7;
        JVM INSTR tableswitch 0 5: default 80
    //                   0 123
    //                   1 161
    //                   2 171
    //                   3 181
    //                   4 188
    //                   5 142;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        byte byte1;
        if (flag1)
        {
            byte1 = (byte)(0xc7 & optionsState[j] | byte0 << 3);
        } else
        {
            byte1 = (byte)(byte0 | 0xf8 & optionsState[j]);
        }
        optionsState[j] = byte1;
        return;
_L2:
        if (flag)
        {
            byte0 = 3;
            out.writeCommand(i, j);
        }
          goto _L1
_L7:
        if (!flag)
        {
            byte0 = 1;
            out.writeCommand(i, j);
        }
          goto _L1
_L3:
        if (flag)
        {
            byte0 = 2;
        }
          goto _L1
_L4:
        if (!flag)
        {
            byte0 = 1;
        }
          goto _L1
_L5:
        if (!flag)
        {
            byte0 = 4;
        }
_L6:
        if (!flag) goto _L1; else goto _L8
_L8:
        byte0 = 3;
          goto _L1
    }

    public void run()
    {
        TelnetInputStream telnetinputstream;
        byte abyte0[];
        telnetinputstream = getInputStream();
        abyte0 = new byte[1024];
_L1:
        int i = telnetinputstream.read();
        if (i < 0)
        {
            return;
        }
        int j;
        try
        {
            abyte0[0] = (byte)i;
            j = telnetinputstream.available();
        }
        catch (IOException ioexception)
        {
            System.err.println(ioexception);
            System.exit(-1);
            return;
        }
        if (j <= 0)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        if (j > -1 + abyte0.length)
        {
            j = -1 + abyte0.length;
        }
        j = telnetinputstream.read(abyte0, 1, j);
        System.out.write(abyte0, 0, j + 1);
          goto _L1
    }

    public void subCommand(byte abyte0[], int i, int j)
    {
        abyte0[i];
        JVM INSTR lookupswitch 3: default 36
    //                   24: 81
    //                   31: 37
    //                   34: 144;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L3:
        if (j == 5)
        {
            windowWidth = (short)((abyte0[1] << 8) + (0xff & abyte0[2]));
            windowHeight = (short)((abyte0[3] << 8) + (0xff & abyte0[4]));
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        byte abyte1[] = new byte[j - 1];
        System.arraycopy(abyte0, 1, abyte1, 0, j - 1);
        terminalType = abyte1;
        System.err.println((new StringBuilder()).append("terminal type: '").append(new String(abyte1)).append("'").toString());
        return;
_L4:
        System.err.println((new StringBuilder()).append("SBCommand LINEMODE ").append(abyte0[1]).append(" len:").append(j).toString());
        if (abyte0[1] == 3)
        {
            int k = 2;
            while (k + 2 < j) 
            {
                System.err.println((new StringBuilder()).append("  ").append(abyte0[k]).append(",").append(abyte0[k + 1]).append(",").append(abyte0[k + 2]).toString());
                k += 3;
            }
        }
        if (true) goto _L1; else goto _L5
_L5:
    }
}
