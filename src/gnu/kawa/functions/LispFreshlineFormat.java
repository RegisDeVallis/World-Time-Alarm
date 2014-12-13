// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispFreshlineFormat extends ReportFormat
{

    int count;

    public LispFreshlineFormat(int i)
    {
        count = i;
    }

    public int format(Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        int j = getParam(count, 1, aobj, i);
        if (count == 0xa0000000)
        {
            i++;
        }
        if (j > 0)
        {
            if (writer instanceof OutPort)
            {
                ((OutPort)writer).freshLine();
                j--;
            }
            while (--j >= 0) 
            {
                writer.write(10);
            }
        }
        return i;
    }
}
