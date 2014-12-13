// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

// Referenced classes of package gnu.kawa.functions:
//            LispFormat, ObjectFormat

class LispPrettyFormat extends ReportFormat
{

    Format body;
    boolean perLine;
    String prefix;
    boolean seenAt;
    Format segments[];
    String suffix;

    LispPrettyFormat()
    {
    }

    public int format(Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        String s;
        String s1;
        OutPort outport;
        int k;
        s = prefix;
        s1 = suffix;
        int j;
        if (writer instanceof OutPort)
        {
            outport = (OutPort)writer;
        } else
        {
            outport = null;
        }
        if (!seenAt) goto _L2; else goto _L1
_L1:
        if (outport == null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        outport.startLogicalBlock(s, perLine, suffix);
        j = ReportFormat.format(body, aobj, i, writer, fieldposition);
        k = j;
_L4:
        if (outport != null)
        {
            outport.endLogicalBlock(s1);
        }
        return k;
_L2:
        Object obj;
        Object aobj1[];
        obj = aobj[i];
        aobj1 = LispFormat.asArray(obj);
        if (aobj1 == null)
        {
            s1 = "";
            s = s1;
        }
        if (outport == null)
        {
            break MISSING_BLOCK_LABEL_136;
        }
        outport.startLogicalBlock(s, perLine, suffix);
        if (aobj1 != null)
        {
            break MISSING_BLOCK_LABEL_153;
        }
        ObjectFormat.format(obj, writer, -1, true);
        break MISSING_BLOCK_LABEL_187;
        ReportFormat.format(body, aobj1, 0, writer, fieldposition);
        break MISSING_BLOCK_LABEL_187;
        Exception exception;
        exception;
        if (outport != null)
        {
            outport.endLogicalBlock(s1);
        }
        throw exception;
        k = i + 1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("LispPrettyFormat[");
        stringbuffer.append("prefix: \"");
        stringbuffer.append(prefix);
        stringbuffer.append("\", suffix: \"");
        stringbuffer.append(suffix);
        stringbuffer.append("\", body: ");
        stringbuffer.append(body);
        stringbuffer.append("]");
        return stringbuffer.toString();
    }
}
