// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.Special;
import gnu.lists.FString;
import gnu.mapping.Values;
import gnu.text.LineBufferedReader;
import java.io.IOException;

public class read_line
{

    public read_line()
    {
    }

    public static Object apply(LineBufferedReader linebufferedreader, String s)
        throws IOException
    {
        Object obj;
        if (linebufferedreader.read() < 0)
        {
            obj = Special.eof;
        } else
        {
label0:
            {
label1:
                {
                    int i = -1 + linebufferedreader.pos;
                    int j = linebufferedreader.limit;
                    char ac[] = linebufferedreader.buffer;
                    byte byte0 = -1;
                    int l;
                    StringBuffer stringbuffer;
                    char c;
                    int i1;
                    char c1;
                    int j1;
                    for (int k = i; k < j; k = j1)
                    {
                        j1 = k + 1;
                        char c2 = ac[k];
                        if (c2 != '\r' && c2 != '\n')
                        {
                            break label0;
                        }
                        l = j1 - 1;
                        if (s == "trim" || s == "peek")
                        {
                            if (s == "peek")
                            {
                                byte0 = 0;
                            }
                            int k1;
                            if (c2 == '\n')
                            {
                                k1 = 1;
                            } else
                            {
                                if (l + 1 >= j)
                                {
                                    break label1;
                                }
                                if (ac[l + 1] == '\n')
                                {
                                    k1 = 2;
                                } else
                                {
                                    k1 = 1;
                                }
                            }
                            linebufferedreader.pos = l + k1;
                        } else
                        {
                            if (s != "concat" || c2 != '\n')
                            {
                                break label1;
                            }
                            l++;
                            linebufferedreader.pos = l;
                        }
                        return new FString(ac, i, l - i);
                    }

                    l = k;
                }
                stringbuffer = new StringBuffer(100);
                if (l > i)
                {
                    stringbuffer.append(ac, i, l - i);
                }
                linebufferedreader.pos = l;
                if (s == "peek")
                {
                    c = 'P';
                } else
                if (s == "concat" || s == "split")
                {
                    c = 'A';
                } else
                {
                    c = 'I';
                }
                linebufferedreader.readLine(stringbuffer, c);
                i1 = stringbuffer.length();
                if (s == "split")
                {
                    if (i1 == 0)
                    {
                        byte0 = 0;
                    } else
                    {
                        c1 = stringbuffer.charAt(i1 - 1);
                        if (c1 == '\r')
                        {
                            byte0 = 1;
                        } else
                        if (c1 != '\n')
                        {
                            byte0 = 0;
                        } else
                        if (c1 > '\002' && stringbuffer.charAt(i1 - 2) == '\r')
                        {
                            byte0 = 2;
                        } else
                        {
                            byte0 = 1;
                        }
                        i1 -= byte0;
                    }
                }
                obj = new FString(stringbuffer, 0, i1);
                if (s == "split")
                {
                    return new Values(new Object[] {
                        obj, new FString(stringbuffer, i1 - byte0, byte0)
                    });
                }
            }
        }
        return obj;
    }
}
