// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import gnu.lists.Consumer;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.MessageFormat;
import java.text.ParsePosition;

// Referenced classes of package gnu.text:
//            Char, Printable

public abstract class ReportFormat extends Format
{

    public static final int PARAM_FROM_COUNT = 0xb0000000;
    public static final int PARAM_FROM_LIST = 0xa0000000;
    public static final int PARAM_UNSPECIFIED = 0xc0000000;

    public ReportFormat()
    {
    }

    public static int format(Format format1, Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        if (format1 instanceof ReportFormat)
        {
            return ((ReportFormat)format1).format(aobj, i, writer, fieldposition);
        }
        StringBuffer stringbuffer = new StringBuffer();
        int k;
        int l;
        char ac[];
        if (format1 instanceof MessageFormat)
        {
            k = format(format1, aobj, i, stringbuffer, fieldposition);
        } else
        {
            int j = i + 1;
            format1.format(aobj[i], stringbuffer, fieldposition);
            k = j;
        }
        l = stringbuffer.length();
        ac = new char[l];
        stringbuffer.getChars(0, l, ac, 0);
        writer.write(ac);
        return k;
    }

    public static int format(Format format1, Object aobj[], int i, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        if (format1 instanceof ReportFormat)
        {
            return ((ReportFormat)format1).format(aobj, i, stringbuffer, fieldposition);
        }
        Object aobj1[];
        int j;
        if (format1 instanceof MessageFormat)
        {
            j = aobj.length - i;
            if (i > 0)
            {
                Object aobj2[] = new Object[aobj.length - i];
                System.arraycopy(((Object) (aobj)), i, ((Object) (aobj2)), 0, aobj2.length);
                aobj1 = aobj2;
            } else
            {
                aobj1 = aobj;
            }
        } else
        {
            aobj1 = ((Object []) (aobj[i]));
            j = 1;
        }
        format1.format(((Object) (aobj1)), stringbuffer, fieldposition);
        return i + j;
    }

    protected static char getParam(int i, char c, Object aobj[], int j)
    {
        return (char)getParam(i, c, aobj, j);
    }

    protected static int getParam(int i, int j, Object aobj[], int k)
    {
        if (i != 0xb0000000) goto _L2; else goto _L1
_L1:
        j = aobj.length - k;
_L4:
        return j;
_L2:
        if (i != 0xa0000000)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (aobj == null) goto _L4; else goto _L3
_L3:
        return getParam(aobj[k], j);
        if (i == 0xc0000000) goto _L4; else goto _L5
_L5:
        return i;
    }

    public static int getParam(Object obj, int i)
    {
        if (obj instanceof Number)
        {
            i = ((Number)obj).intValue();
        } else
        {
            if (obj instanceof Character)
            {
                return ((Character)obj).charValue();
            }
            if (obj instanceof Char)
            {
                return ((Char)obj).charValue();
            }
        }
        return i;
    }

    public static int nextArg(int i)
    {
        return 0xffffff & i;
    }

    public static void print(Writer writer, String s)
        throws IOException
    {
        if (writer instanceof PrintWriter)
        {
            ((PrintWriter)writer).print(s);
            return;
        } else
        {
            writer.write(s.toCharArray());
            return;
        }
    }

    public static void print(Object obj, Consumer consumer)
    {
        if (obj instanceof Printable)
        {
            ((Printable)obj).print(consumer);
            return;
        }
        String s;
        if (obj == null)
        {
            s = "null";
        } else
        {
            s = obj.toString();
        }
        consumer.write(s);
    }

    public static int result(int i, int j)
    {
        return j | i << 24;
    }

    public static int resultCode(int i)
    {
        return i >>> 24;
    }

    public int format(Object obj, int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        if (obj instanceof Object[])
        {
            return format((Object[])(Object[])obj, i, writer, fieldposition);
        } else
        {
            return format(new Object[] {
                obj
            }, i, writer, fieldposition);
        }
    }

    public abstract int format(Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException;

    public int format(Object aobj[], int i, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        CharArrayWriter chararraywriter = new CharArrayWriter();
        int j;
        try
        {
            j = format(aobj, i, ((Writer) (chararraywriter)), fieldposition);
        }
        catch (IOException ioexception)
        {
            throw new Error((new StringBuilder()).append("unexpected exception: ").append(ioexception).toString());
        }
        if (j < 0)
        {
            return j;
        } else
        {
            stringbuffer.append(chararraywriter.toCharArray());
            return j;
        }
    }

    public StringBuffer format(Object obj, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        format((Object[])(Object[])obj, 0, stringbuffer, fieldposition);
        return stringbuffer;
    }

    public Object parseObject(String s, ParsePosition parseposition)
    {
        throw new Error("ReportFormat.parseObject - not implemented");
    }
}
