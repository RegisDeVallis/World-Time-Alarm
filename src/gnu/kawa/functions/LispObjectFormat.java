// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.text.PadFormat;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispObjectFormat extends ReportFormat
{

    ReportFormat base;
    int colInc;
    int minPad;
    int minWidth;
    int padChar;
    int where;

    public LispObjectFormat(ReportFormat reportformat, int i, int j, int k, int l, int i1)
    {
        base = reportformat;
        minWidth = i;
        colInc = j;
        minPad = k;
        padChar = l;
        where = i1;
    }

    public int format(Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        int j = getParam(minWidth, 0, aobj, i);
        if (minWidth == 0xa0000000)
        {
            i++;
        }
        int k = getParam(colInc, 1, aobj, i);
        if (colInc == 0xa0000000)
        {
            i++;
        }
        int l = getParam(minPad, 0, aobj, i);
        if (minPad == 0xa0000000)
        {
            i++;
        }
        char c = getParam(padChar, ' ', aobj, i);
        if (padChar == 0xa0000000)
        {
            i++;
        }
        ReportFormat reportformat = base;
        int i1 = where;
        return PadFormat.format(reportformat, aobj, i, writer, c, j, k, l, i1, fieldposition);
    }
}
