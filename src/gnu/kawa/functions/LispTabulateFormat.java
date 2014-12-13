// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispTabulateFormat extends ReportFormat
{

    int colinc;
    int colnum;
    int padChar;
    boolean relative;

    public LispTabulateFormat(int i, int j, int k, boolean flag)
    {
        colnum = i;
        colinc = j;
        relative = flag;
        padChar = k;
    }

    public int format(Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        int j = getParam(colnum, 1, aobj, i);
        if (colnum == 0xa0000000)
        {
            i++;
        }
        int k = getParam(colinc, 1, aobj, i);
        if (colinc == 0xa0000000)
        {
            i++;
        }
        char c = getParam(padChar, ' ', aobj, i);
        if (padChar == 0xa0000000)
        {
            i++;
        }
        int l = -1;
        if (writer instanceof OutPort)
        {
            l = ((OutPort)writer).getColumnNumber();
        }
        int i1;
        if (l >= 0)
        {
            if (!relative)
            {
                if (l >= j)
                {
                    if (k <= 0)
                    {
                        i1 = 0;
                    } else
                    {
                        i1 = k - (l - j) % k;
                    }
                }
            } else
            {
                i1 = (j + k) - (l + j) % k;
            }
        } else
        if (relative)
        {
            i1 = j;
        } else
        {
            i1 = 2;
        }
        for (i1 = j - l; --i1 >= 0;)
        {
            writer.write(c);
        }

        return i;
    }
}
