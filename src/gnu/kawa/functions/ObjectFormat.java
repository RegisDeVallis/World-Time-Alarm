// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.lists.AbstractFormat;
import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.ParsePosition;
import kawa.standard.Scheme;

public class ObjectFormat extends ReportFormat
{

    private static ObjectFormat plainFormat;
    private static ObjectFormat readableFormat;
    int maxChars;
    boolean readable;

    public ObjectFormat(boolean flag)
    {
        readable = flag;
        maxChars = 0xc0000000;
    }

    public ObjectFormat(boolean flag, int i)
    {
        readable = flag;
        maxChars = i;
    }

    public static int format(Object aobj[], int i, Writer writer, int j, boolean flag)
        throws IOException
    {
        Object obj;
        if (i >= aobj.length)
        {
            obj = "#<missing format argument>";
            i--;
            flag = false;
            j = -1;
        } else
        {
            obj = aobj[i];
        }
        format(obj, writer, j, flag);
        return i + 1;
    }

    public static boolean format(Object obj, Writer writer, int i, boolean flag)
        throws IOException
    {
        if (i < 0 && (writer instanceof OutPort))
        {
            print(obj, (OutPort)writer, flag);
            return true;
        }
        if (i < 0 && (writer instanceof CharArrayWriter))
        {
            OutPort outport1 = new OutPort(writer);
            print(obj, outport1, flag);
            outport1.close();
            return true;
        }
        CharArrayWriter chararraywriter = new CharArrayWriter();
        OutPort outport = new OutPort(chararraywriter);
        print(obj, outport, flag);
        outport.close();
        int j = chararraywriter.size();
        if (i < 0 || j <= i)
        {
            chararraywriter.writeTo(writer);
            return true;
        } else
        {
            writer.write(chararraywriter.toCharArray(), 0, i);
            return false;
        }
    }

    public static ObjectFormat getInstance(boolean flag)
    {
        if (flag)
        {
            if (readableFormat == null)
            {
                readableFormat = new ObjectFormat(true);
            }
            return readableFormat;
        }
        if (plainFormat == null)
        {
            plainFormat = new ObjectFormat(false);
        }
        return plainFormat;
    }

    private static void print(Object obj, OutPort outport, boolean flag)
    {
        boolean flag1;
        AbstractFormat abstractformat;
        flag1 = outport.printReadable;
        abstractformat = outport.objectFormat;
        outport.printReadable = flag;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_50;
        }
        AbstractFormat abstractformat1 = Scheme.writeFormat;
_L1:
        outport.objectFormat = abstractformat1;
        abstractformat1.writeObject(obj, outport);
        outport.printReadable = flag1;
        outport.objectFormat = abstractformat;
        return;
        abstractformat1 = Scheme.displayFormat;
          goto _L1
        Exception exception;
        exception;
        outport.printReadable = flag1;
        outport.objectFormat = abstractformat;
        throw exception;
    }

    public int format(Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        int j = getParam(maxChars, -1, aobj, i);
        if (maxChars == 0xa0000000)
        {
            i++;
        }
        return format(aobj, i, writer, j, readable);
    }

    public Object parseObject(String s, ParsePosition parseposition)
    {
        throw new RuntimeException("ObjectFormat.parseObject - not implemented");
    }
}
