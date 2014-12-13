// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispEscapeFormat extends ReportFormat
{

    public static final int ESCAPE_ALL = 242;
    public static final int ESCAPE_NORMAL = 241;
    public static final LispEscapeFormat alwaysTerminate = new LispEscapeFormat(0, 0xc0000000);
    boolean escapeAll;
    int param1;
    int param2;
    int param3;

    public LispEscapeFormat(int i, int j)
    {
        param1 = i;
        param2 = j;
        param3 = 0xc0000000;
    }

    public LispEscapeFormat(int i, int j, int k)
    {
        param1 = i;
        param2 = j;
        param3 = k;
    }

    static Numeric getParam(int i, Object aobj[], int j)
    {
        if (i == 0xb0000000)
        {
            return IntNum.make(aobj.length - j);
        }
        if (i == 0xa0000000)
        {
            Object obj = aobj[j];
            if (obj instanceof Numeric)
            {
                return (Numeric)obj;
            }
            if (obj instanceof Number)
            {
                if ((obj instanceof Float) || (obj instanceof Double))
                {
                    return new DFloNum(((Number)obj).doubleValue());
                } else
                {
                    return IntNum.make(((Number)obj).longValue());
                }
            }
            if (obj instanceof Char)
            {
                return new IntNum(((Char)obj).intValue());
            }
            if (obj instanceof Character)
            {
                return new IntNum(((Character)obj).charValue());
            } else
            {
                return new DFloNum((0.0D / 0.0D));
            }
        } else
        {
            return IntNum.make(i);
        }
    }

    public int format(Object aobj[], int i, Writer writer, FieldPosition fieldposition)
        throws IOException
    {
        boolean flag = true;
        int _tmp = i;
        int j;
        if (param1 == 0xc0000000)
        {
            if (i != aobj.length)
            {
                flag = false;
            }
        } else
        if (param2 == 0xc0000000 && param1 == 0)
        {
            flag = true;
        } else
        {
            Numeric numeric = getParam(param1, aobj, i);
            if (param1 == 0xa0000000)
            {
                i++;
            }
            if (param2 == 0xc0000000)
            {
                flag = numeric.isZero();
            } else
            {
                Numeric numeric1 = getParam(param2, aobj, i);
                if (param2 == 0xa0000000)
                {
                    i++;
                }
                if (param3 == 0xc0000000)
                {
                    flag = numeric.equals(numeric1);
                } else
                {
                    Numeric numeric2 = getParam(param3, aobj, i);
                    if (param3 == 0xa0000000)
                    {
                        i++;
                    }
                    if (!numeric1.geq(numeric) || !numeric2.geq(numeric1))
                    {
                        flag = false;
                    }
                }
            }
        }
        j = 0;
        if (flag)
        {
            if (escapeAll)
            {
                j = 242;
            } else
            {
                j = 241;
            }
        }
        return result(j, i);
    }

}
