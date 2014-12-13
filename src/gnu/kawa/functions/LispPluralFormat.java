// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.math.IntNum;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispPluralFormat extends ReportFormat
{

    boolean backup;
    boolean y;

    LispPluralFormat()
    {
    }

    public static LispPluralFormat getInstance(boolean flag, boolean flag1)
    {
        LispPluralFormat lisppluralformat = new LispPluralFormat();
        lisppluralformat.backup = flag;
        lisppluralformat.y = flag1;
        return lisppluralformat;
    }

    public int format(Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        if (backup)
        {
            i--;
        }
        int j = i + 1;
        boolean flag;
        if (aobj[i] != IntNum.one())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (y)
        {
            String s;
            if (flag)
            {
                s = "ies";
            } else
            {
                s = "y";
            }
            print(writer, s);
        } else
        if (flag)
        {
            writer.write(115);
            return j;
        }
        return j;
    }
}
