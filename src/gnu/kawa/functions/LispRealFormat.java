// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.math.ExponentialFormat;
import gnu.math.FixedRealFormat;
import gnu.math.RealNum;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

class LispRealFormat extends ReportFormat
{

    int arg1;
    int arg2;
    int arg3;
    int arg4;
    int arg5;
    int arg6;
    int arg7;
    int argsUsed;
    boolean internalPad;
    char op;
    boolean showPlus;

    LispRealFormat()
    {
        int i;
        if (arg1 == 0xb0000000 || arg2 == 0xb0000000 || arg3 == 0xb0000000 || arg4 == 0xb0000000 || arg5 == 0xb0000000 || arg6 == 0xb0000000 || arg7 == 0xb0000000)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        argsUsed = i;
        if (arg1 == 0xa0000000)
        {
            argsUsed = 2 + argsUsed;
        }
        if (arg2 == 0xa0000000)
        {
            argsUsed = 2 + argsUsed;
        }
        if (arg3 == 0xa0000000)
        {
            argsUsed = 2 + argsUsed;
        }
        if (arg4 == 0xa0000000)
        {
            argsUsed = 2 + argsUsed;
        }
        if (arg5 == 0xa0000000)
        {
            argsUsed = 2 + argsUsed;
        }
        if (arg6 == 0xa0000000)
        {
            argsUsed = 2 + argsUsed;
        }
        if (arg7 == 0xa0000000)
        {
            argsUsed = 2 + argsUsed;
        }
    }

    public int format(Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        StringBuffer stringbuffer = new StringBuffer(100);
        Format format1 = resolve(aobj, i);
        int j = i + (argsUsed >> 1);
        int k = j + 1;
        format1.format((RealNum)aobj[j], stringbuffer, fieldposition);
        writer.write(stringbuffer.toString());
        return k;
    }

    public Format resolve(Object aobj[], int i)
    {
        if (op == '$')
        {
            FixedRealFormat fixedrealformat = new FixedRealFormat();
            int j = getParam(arg1, 2, aobj, i);
            if (arg1 == 0xa0000000)
            {
                i++;
            }
            int k = getParam(arg2, 1, aobj, i);
            if (arg2 == 0xa0000000)
            {
                i++;
            }
            int l = getParam(arg3, 0, aobj, i);
            if (arg3 == 0xa0000000)
            {
                i++;
            }
            char c = getParam(arg4, ' ', aobj, i);
            if (arg4 == 0xa0000000)
            {
                int _tmp = i + 1;
            }
            fixedrealformat.setMaximumFractionDigits(j);
            fixedrealformat.setMinimumIntegerDigits(k);
            fixedrealformat.width = l;
            fixedrealformat.padChar = c;
            fixedrealformat.internalPad = internalPad;
            fixedrealformat.showPlus = showPlus;
            return fixedrealformat;
        }
        if (op == 'F')
        {
            FixedRealFormat fixedrealformat1 = new FixedRealFormat();
            int i1 = getParam(arg1, 0, aobj, i);
            if (arg1 == 0xa0000000)
            {
                i++;
            }
            int j1 = getParam(arg2, -1, aobj, i);
            if (arg2 == 0xa0000000)
            {
                i++;
            }
            int k1 = getParam(arg3, 0, aobj, i);
            if (arg3 == 0xa0000000)
            {
                i++;
            }
            fixedrealformat1.overflowChar = getParam(arg4, '\0', aobj, i);
            if (arg4 == 0xa0000000)
            {
                i++;
            }
            char c1 = getParam(arg5, ' ', aobj, i);
            if (arg5 == 0xa0000000)
            {
                int _tmp1 = i + 1;
            }
            fixedrealformat1.setMaximumFractionDigits(j1);
            fixedrealformat1.setMinimumIntegerDigits(0);
            fixedrealformat1.width = i1;
            fixedrealformat1.scale = k1;
            fixedrealformat1.padChar = c1;
            fixedrealformat1.internalPad = internalPad;
            fixedrealformat1.showPlus = showPlus;
            return fixedrealformat1;
        }
        ExponentialFormat exponentialformat = new ExponentialFormat();
        exponentialformat.exponentShowSign = true;
        exponentialformat.width = getParam(arg1, 0, aobj, i);
        if (arg1 == 0xa0000000)
        {
            i++;
        }
        exponentialformat.fracDigits = getParam(arg2, -1, aobj, i);
        if (arg2 == 0xa0000000)
        {
            i++;
        }
        exponentialformat.expDigits = getParam(arg3, 0, aobj, i);
        if (arg3 == 0xa0000000)
        {
            i++;
        }
        exponentialformat.intDigits = getParam(arg4, 1, aobj, i);
        if (arg4 == 0xa0000000)
        {
            i++;
        }
        exponentialformat.overflowChar = getParam(arg5, '\0', aobj, i);
        if (arg5 == 0xa0000000)
        {
            i++;
        }
        exponentialformat.padChar = getParam(arg6, ' ', aobj, i);
        if (arg6 == 0xa0000000)
        {
            i++;
        }
        exponentialformat.exponentChar = getParam(arg7, 'E', aobj, i);
        if (arg7 == 0xa0000000)
        {
            int _tmp2 = i + 1;
        }
        boolean flag;
        if (op == 'G')
        {
            flag = true;
        } else
        {
            flag = false;
        }
        exponentialformat.general = flag;
        exponentialformat.showPlus = showPlus;
        return exponentialformat;
    }
}
