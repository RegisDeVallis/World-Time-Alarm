// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

// Referenced classes of package gnu.kawa.functions:
//            LispFormat

class LispIterationFormat extends ReportFormat
{

    boolean atLeastOnce;
    Format body;
    int maxIterations;
    boolean seenAt;
    boolean seenColon;

    LispIterationFormat()
    {
    }

    public static int format(Format format1, int i, Object aobj[], int j, Writer writer, boolean flag, boolean flag1)
        throws IOException
    {
        int k;
        k = 0;
        break MISSING_BLOCK_LABEL_3;
_L2:
        return j;
_L5:
        if (k == i && i != -1 || j == aobj.length && (k > 0 || !flag1)) goto _L2; else goto _L1
_L1:
        int l;
        if (!flag)
        {
            break; /* Loop/switch isn't completed */
        }
        Object aobj1[] = LispFormat.asArray(aobj[j]);
        if (aobj1 != null);
        l = ReportFormat.format(format1, aobj1, 0, writer, null);
        j++;
        if (ReportFormat.resultCode(l) == 242) goto _L2; else goto _L3
_L3:
        k++;
        if (true) goto _L5; else goto _L4
_L4:
        j = ReportFormat.format(format1, aobj, j, writer, null);
        if (j < 0)
        {
            return ReportFormat.nextArg(j);
        }
          goto _L3
        if (true) goto _L5; else goto _L6
_L6:
    }

    public int format(Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        int j = getParam(maxIterations, -1, aobj, i);
        if (maxIterations == 0xa0000000)
        {
            i++;
        }
        Object obj = body;
        if (obj == null)
        {
            int k = i + 1;
            Object obj2 = aobj[i];
            boolean flag;
            boolean flag1;
            if (obj2 instanceof Format)
            {
                obj = (Format)obj2;
                i = k;
            } else
            {
                LispFormat lispformat;
                try
                {
                    lispformat = new LispFormat(obj2.toString());
                }
                catch (Exception exception)
                {
                    print(writer, "<invalid argument for \"~{~}\" format>");
                    return aobj.length;
                }
                obj = lispformat;
                i = k;
            }
        }
        if (seenAt)
        {
            flag = seenColon;
            flag1 = atLeastOnce;
            return format(((Format) (obj)), j, aobj, i, writer, flag, flag1);
        }
        Object obj1 = aobj[i];
        Object aobj1[] = LispFormat.asArray(obj1);
        if (aobj1 == null)
        {
            writer.write((new StringBuilder()).append("{").append(obj1).append("}".toString()).toString());
        } else
        {
            format(((Format) (obj)), j, aobj1, 0, writer, seenColon, atLeastOnce);
        }
        return i + 1;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("LispIterationFormat[");
        stringbuffer.append(body);
        stringbuffer.append("]");
        return stringbuffer.toString();
    }
}
