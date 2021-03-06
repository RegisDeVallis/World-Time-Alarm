// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispRepositionFormat extends ReportFormat
{

    boolean absolute;
    boolean backwards;
    int count;

    public LispRepositionFormat(int i, boolean flag, boolean flag1)
    {
        count = i;
        backwards = flag;
        absolute = flag1;
    }

    public int format(Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        int j = count;
        int k;
        int l;
        if (absolute)
        {
            k = 0;
        } else
        {
            k = 1;
        }
        l = getParam(j, k, aobj, i);
        if (!absolute)
        {
            if (backwards)
            {
                l = -l;
            }
            l += i;
        }
        if (l < 0)
        {
            return 0;
        }
        if (l > aobj.length)
        {
            return aobj.length;
        } else
        {
            return l;
        }
    }
}
