// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;

// Referenced classes of package gnu.text:
//            ReportFormat

public class PadFormat extends ReportFormat
{

    Format fmt;
    int minWidth;
    char padChar;
    int where;

    public PadFormat(Format format1, int i)
    {
        this(format1, i, ' ', 100);
    }

    public PadFormat(Format format1, int i, char c, int j)
    {
        fmt = format1;
        minWidth = i;
        padChar = c;
        where = j;
    }

    public static int format(Format format1, Object aobj[], int i, Writer writer, char c, int j, int k, int l, 
            int i1, FieldPosition fieldposition)
        throws IOException
    {
        int j1;
label0:
        {
            String s;
label1:
            {
                int i2;
label2:
                {
label3:
                    {
label4:
                        {
                            StringBuffer stringbuffer = new StringBuffer(200);
                            int k1;
                            int l1;
                            int j2;
                            char c1;
                            int k2;
                            char c2;
                            if (format1 instanceof ReportFormat)
                            {
                                j1 = ((ReportFormat)format1).format(aobj, i, stringbuffer, fieldposition);
                            } else
                            if (format1 instanceof MessageFormat)
                            {
                                format1.format(((Object) (aobj)), stringbuffer, fieldposition);
                                j1 = aobj.length;
                            } else
                            {
                                format1.format(aobj[i], stringbuffer, fieldposition);
                                j1 = i + 1;
                            }
                            k1 = stringbuffer.length();
                            l1 = padNeeded(k1, j, k, l);
                            s = stringbuffer.toString();
                            if (l1 <= 0)
                            {
                                break label1;
                            }
                            if (i1 != -1)
                            {
                                break label2;
                            }
                            if (k1 <= 0)
                            {
                                break label3;
                            }
                            c1 = s.charAt(0);
                            if (c1 != '-')
                            {
                                k2 = 0;
                                if (c1 != '+')
                                {
                                    break label4;
                                }
                            }
                            k2 = 0 + 1;
                            writer.write(c1);
                        }
                        if (k1 - k2 > 2 && s.charAt(k2) == '0')
                        {
                            writer.write(48);
                            k2++;
                            c2 = s.charAt(k2);
                            if (c2 == 'x' || c2 == 'X')
                            {
                                k2++;
                                writer.write(c2);
                            }
                        }
                        if (k2 > 0)
                        {
                            s = s.substring(k2);
                        }
                    }
                    i1 = 0;
                }
                i2 = (l1 * i1) / 100;
                for (j2 = l1 - i2; --j2 >= 0;)
                {
                    writer.write(c);
                }

                writer.write(s);
                while (--i2 >= 0) 
                {
                    writer.write(c);
                }
                break label0;
            }
            writer.write(s);
        }
        return j1;
    }

    public static int padNeeded(int i, int j, int k, int l)
    {
        int i1 = i + l;
        if (k <= 1)
        {
            k = j - i1;
        }
        for (; i1 < j; i1 += k) { }
        return i1 - i;
    }

    public int format(Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        return format(fmt, aobj, i, writer, padChar, minWidth, 1, 0, where, fieldposition);
    }
}
