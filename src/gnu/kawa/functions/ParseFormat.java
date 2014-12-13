// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.lists.FString;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.InPort;
import gnu.mapping.Procedure1;
import gnu.text.CompoundFormat;
import gnu.text.LineBufferedReader;
import gnu.text.LiteralFormat;
import gnu.text.PadFormat;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.text.Format;
import java.text.ParseException;
import java.util.Vector;

// Referenced classes of package gnu.kawa.functions:
//            LispFormat, ObjectFormat, IntegerFormat

public class ParseFormat extends Procedure1
{

    public static final int PARAM_FROM_LIST = 0xa0000000;
    public static final int PARAM_UNSPECIFIED = 0xc0000000;
    public static final int SEEN_HASH = 16;
    public static final int SEEN_MINUS = 1;
    public static final int SEEN_PLUS = 2;
    public static final int SEEN_SPACE = 4;
    public static final int SEEN_ZERO = 8;
    public static final ParseFormat parseFormat = new ParseFormat(false);
    boolean emacsStyle;

    public ParseFormat(boolean flag)
    {
        emacsStyle = true;
        emacsStyle = flag;
    }

    public static ReportFormat asFormat(Object obj, char c)
    {
        CharArrayInPort chararrayinport;
        Exception exception;
        ReportFormat reportformat;
        FString fstring;
        try
        {
            if (obj instanceof ReportFormat)
            {
                return (ReportFormat)obj;
            }
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException((new StringBuilder()).append("Error parsing format (").append(ioexception).append(")").toString());
        }
        catch (ParseException parseexception)
        {
            throw new RuntimeException((new StringBuilder()).append("Invalid format (").append(parseexception).append(")").toString());
        }
        catch (IndexOutOfBoundsException indexoutofboundsexception)
        {
            throw new RuntimeException("End while parsing format");
        }
        if (c != '~')
        {
            break MISSING_BLOCK_LABEL_30;
        }
        return new LispFormat(obj.toString());
        if (!(obj instanceof FString)) goto _L2; else goto _L1
_L1:
        fstring = (FString)obj;
        chararrayinport = new CharArrayInPort(fstring.data, fstring.size);
_L4:
        reportformat = parseFormat(chararrayinport, c);
        chararrayinport.close();
        return reportformat;
_L2:
        chararrayinport = new CharArrayInPort(obj.toString());
        if (true) goto _L4; else goto _L3
_L3:
        exception;
        chararrayinport.close();
        throw exception;
    }

    public static ReportFormat parseFormat(LineBufferedReader linebufferedreader, char c)
        throws ParseException, IOException
    {
        StringBuffer stringbuffer;
        int i;
        Vector vector;
        stringbuffer = new StringBuffer(100);
        i = 0;
        vector = new Vector();
_L28:
        int j;
        j = linebufferedreader.read();
        if (j >= 0)
        {
            if (j != c)
            {
                stringbuffer.append((char)j);
                continue; /* Loop/switch isn't completed */
            }
            j = linebufferedreader.read();
            if (j == c)
            {
                stringbuffer.append((char)j);
                continue; /* Loop/switch isn't completed */
            }
        }
        int k = stringbuffer.length();
        if (k > 0)
        {
            char ac[] = new char[k];
            stringbuffer.getChars(0, k, ac, 0);
            stringbuffer.setLength(0);
            vector.addElement(new LiteralFormat(ac));
        }
        if (j >= 0) goto _L2; else goto _L1
_L2:
        if (j == 36)
        {
            int k2 = Character.digit((char)linebufferedreader.read(), 10);
            if (k2 < 0)
            {
                throw new ParseException("missing number (position) after '%$'", -1);
            }
            do
            {
                j = linebufferedreader.read();
                l2 = Character.digit((char)j, 10);
                if (l2 >= 0)
                {
                    k2 = l2 + k2 * 10;
                } else
                {
                    break;
                }
            } while (true);
            i = k2 - 1;
        }
        l = 0;
_L21:
        (char)j;
        JVM INSTR lookupswitch 5: default 284
    //                   32: 502
    //                   35: 521
    //                   43: 493
    //                   45: 478
    //                   48: 511;
           goto _L3 _L4 _L5 _L6 _L7 _L8
_L3:
        i1 = 0xc0000000;
        j1 = Character.digit((char)j, 10);
        if (j1 < 0) goto _L10; else goto _L9
_L9:
        i1 = j1;
_L23:
        j = linebufferedreader.read();
        j2 = Character.digit((char)j, 10);
        if (j2 >= 0) goto _L12; else goto _L11
_L11:
        k1 = 0xc0000000;
        if (j != 46) goto _L14; else goto _L13
_L13:
        if (j != 42) goto _L16; else goto _L15
_L15:
        k1 = 0xa0000000;
_L14:
        j;
        JVM INSTR lookupswitch 10: default 444
    //                   83: 595
    //                   88: 686
    //                   100: 686
    //                   101: 878
    //                   102: 878
    //                   103: 878
    //                   105: 686
    //                   111: 686
    //                   115: 595
    //                   120: 686;
           goto _L17 _L18 _L19 _L19 _L20 _L20 _L20 _L19 _L19 _L18 _L19
_L17:
        throw new ParseException((new StringBuilder()).append("unknown format character '").append(j).append("'").toString(), -1);
_L7:
        l |= 1;
_L22:
        j = linebufferedreader.read();
          goto _L21
_L6:
        l |= 2;
          goto _L22
_L4:
        l |= 4;
          goto _L22
_L8:
        l |= 8;
          goto _L22
_L5:
        l |= 0x10;
          goto _L22
_L12:
        i1 = j2 + i1 * 10;
          goto _L23
_L10:
        if (j == 42)
        {
            i1 = 0xa0000000;
        }
          goto _L11
_L16:
        k1 = 0;
_L25:
        j = linebufferedreader.read();
        i2 = Character.digit((char)j, 10);
        if (i2 < 0) goto _L14; else goto _L24
_L24:
        k1 = i2 + k1 * 10;
          goto _L25
          goto _L14
_L18:
        Object obj;
        Object obj1;
        boolean flag;
        ObjectFormat objectformat1;
        if (j == 83)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        objectformat1 = new ObjectFormat(flag, k1);
        obj = objectformat1;
_L26:
        Format aformat[];
        if (i1 > 0)
        {
            ObjectFormat objectformat;
            char c1;
            byte byte0;
            int l1;
            byte byte1;
            byte byte2;
            if ((l & 8) != 0)
            {
                c1 = '0';
            } else
            {
                c1 = ' ';
            }
            if ((l & 1) != 0)
            {
                byte0 = 100;
            } else
            if (c1 == '0')
            {
                byte0 = -1;
            } else
            {
                byte0 = 0;
            }
            obj1 = new PadFormat(((Format) (obj)), i1, c1, byte0);
        } else
        {
            obj1 = obj;
        }
        vector.addElement(obj1);
        i++;
        continue; /* Loop/switch isn't completed */
_L19:
        l1 = 0;
        if (j == 100 || j == 105)
        {
            byte1 = 10;
        } else
        if (j == 111)
        {
            byte1 = 8;
            l1 = 0;
        } else
        {
            byte1 = 16;
            l1 = 0;
            if (j == 88)
            {
                l1 = 32;
            }
        }
        if ((l & 9) == 8)
        {
            byte2 = 48;
        } else
        {
            byte2 = 32;
        }
        if ((l & 0x10) != 0)
        {
            l1 |= 8;
        }
        if ((l & 2) != 0)
        {
            l1 |= 2;
        }
        if ((l & 1) != 0)
        {
            l1 |= 0x10;
        }
        if ((l & 4) != 0)
        {
            l1 |= 4;
        }
        if (k1 != 0xc0000000)
        {
            l &= -9;
            obj = IntegerFormat.getInstance(byte1, k1, 48, 0xc0000000, 0xc0000000, l1 | 0x40);
        } else
        {
            obj = IntegerFormat.getInstance(byte1, i1, byte2, 0xc0000000, 0xc0000000, l1);
        }
          goto _L26
_L20:
        objectformat = new ObjectFormat(false);
        obj = objectformat;
          goto _L26
_L1:
        int i3 = vector.size();
        int l;
        int i1;
        int j1;
        int k1;
        int i2;
        int j2;
        if (i3 == 1)
        {
            Object obj2 = vector.elementAt(0);
            if (obj2 instanceof ReportFormat)
            {
                return (ReportFormat)obj2;
            }
        }
        aformat = new Format[i3];
        vector.copyInto(aformat);
        return new CompoundFormat(aformat);
        if (true) goto _L28; else goto _L27
_L27:
    }

    public Object apply1(Object obj)
    {
        char c;
        if (emacsStyle)
        {
            c = '?';
        } else
        {
            c = '~';
        }
        return asFormat(obj, c);
    }

    public ReportFormat parseFormat(LineBufferedReader linebufferedreader)
        throws ParseException, IOException
    {
        char c;
        if (emacsStyle)
        {
            c = '?';
        } else
        {
            c = '~';
        }
        return parseFormat(linebufferedreader, c);
    }

}
