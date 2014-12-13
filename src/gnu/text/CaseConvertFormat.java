// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

// Referenced classes of package gnu.text:
//            ReportFormat

public class CaseConvertFormat extends ReportFormat
{

    Format baseFormat;
    char code;

    public CaseConvertFormat(Format format1, char c)
    {
        baseFormat = format1;
        code = c;
    }

    public int format(Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        StringBuffer stringbuffer = new StringBuffer(100);
        int j = format(baseFormat, aobj, i, stringbuffer, fieldposition);
        int k = stringbuffer.length();
        char c = ' ';
        int l = 0;
        while (l < k) 
        {
            char c1 = stringbuffer.charAt(l);
            char c2;
            if (code == 'U')
            {
                c2 = Character.toUpperCase(c1);
            } else
            if (code == 'T' && l == 0 || code == 'C' && !Character.isLetterOrDigit(c))
            {
                c2 = Character.toTitleCase(c1);
            } else
            {
                c2 = Character.toLowerCase(c1);
            }
            c = c2;
            writer.write(c2);
            l++;
        }
        return j;
    }

    public Format getBaseFormat()
    {
        return baseFormat;
    }

    public void setBaseFormat(Format format1)
    {
        baseFormat = format1;
    }
}
