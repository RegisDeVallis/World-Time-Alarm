// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

// Referenced classes of package gnu.text:
//            Path

public class LineBufferedReader extends Reader
{

    public static final int BUFFER_SIZE = 8192;
    private static final int CONVERT_CR = 1;
    private static final int DONT_KEEP_FULL_LINES = 8;
    private static final int PREV_WAS_CR = 4;
    private static final int USER_BUFFER = 2;
    public char buffer[];
    private int flags;
    int highestPos;
    protected Reader in;
    public int limit;
    protected int lineNumber;
    private int lineStartPos;
    protected int markPos;
    Path path;
    public int pos;
    protected int readAheadLimit;
    public char readState;

    public LineBufferedReader(InputStream inputstream)
    {
        readState = '\n';
        readAheadLimit = 0;
        in = new InputStreamReader(inputstream);
    }

    public LineBufferedReader(Reader reader)
    {
        readState = '\n';
        readAheadLimit = 0;
        in = reader;
    }

    private void clearMark()
    {
        readAheadLimit = 0;
        int i = lineStartPos;
        int j = 0;
        if (i >= 0)
        {
            j = lineStartPos;
        }
        do
        {
            char c;
            do
            {
                if (++j >= pos)
                {
                    return;
                }
                c = buffer[j - 1];
            } while (c != '\n' && (c != '\r' || getConvertCR() && buffer[j] == '\n'));
            lineNumber = 1 + lineNumber;
            lineStartPos = j;
        } while (true);
    }

    static int countLines(char ac[], int i, int j)
    {
        int k = 0;
        int l = 0;
        for (int i1 = i; i1 < j; i1++)
        {
            char c = ac[i1];
            if (c == '\n' && l != 13 || c == '\r')
            {
                k++;
            }
            l = c;
        }

        return k;
    }

    private void reserve(char ac[], int i)
        throws IOException
    {
        int j = i + limit;
        if (j > ac.length) goto _L2; else goto _L1
_L1:
        int k = 0;
_L4:
        if (limit > 0)
        {
            System.arraycopy(buffer, k, ac, 0, limit);
        }
        buffer = ac;
        return;
_L2:
        int l;
        k = pos;
        if (readAheadLimit > 0 && markPos < pos)
        {
            if (pos - markPos > readAheadLimit || (2 & flags) != 0 && j - markPos > ac.length)
            {
                clearMark();
            } else
            {
                k = markPos;
            }
        }
        l = j - ac.length;
        if (l > k || k > lineStartPos && (8 & flags) == 0)
        {
            break; /* Loop/switch isn't completed */
        }
_L5:
        lineStartPos = lineStartPos - k;
        limit = limit - k;
        markPos = markPos - k;
        pos = pos - k;
        highestPos = highestPos - k;
        if (true) goto _L4; else goto _L3
_L3:
        if (l <= lineStartPos && k > lineStartPos)
        {
            k = lineStartPos;
        } else
        if ((2 & flags) != 0)
        {
            k -= k - l >> 2;
        } else
        {
            if (lineStartPos >= 0)
            {
                k = lineStartPos;
            }
            ac = new char[2 * ac.length];
        }
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
    }

    public void close()
        throws IOException
    {
        in.close();
    }

    public int fill(int i)
        throws IOException
    {
        return in.read(buffer, pos, i);
    }

    public int getColumnNumber()
    {
        if (pos > 0)
        {
            char c1 = buffer[-1 + pos];
            if (c1 == '\n' || c1 == '\r')
            {
                return 0;
            }
        }
        if (readAheadLimit <= 0)
        {
            return pos - lineStartPos;
        }
        int i = lineStartPos;
        int j = 0;
        int k;
        int i1;
        if (i >= 0)
        {
            j = lineStartPos;
        }
        for (k = j; k < pos; k = i1)
        {
            char ac[] = buffer;
            i1 = k + 1;
            char c = ac[k];
            if (c == '\n' || c == '\r')
            {
                j = i1;
            }
        }

        int l = pos - j;
        if (lineStartPos < 0)
        {
            l -= lineStartPos;
        }
        return l;
    }

    public final boolean getConvertCR()
    {
        return (1 & flags) != 0;
    }

    public int getLineNumber()
    {
        int i = lineNumber;
        if (readAheadLimit == 0)
        {
            if (pos > 0 && pos > lineStartPos)
            {
                char c = buffer[-1 + pos];
                if (c == '\n' || c == '\r')
                {
                    i++;
                }
            }
            return i;
        }
        char ac[] = buffer;
        int j;
        if (lineStartPos < 0)
        {
            j = 0;
        } else
        {
            j = lineStartPos;
        }
        return i + countLines(ac, j, pos);
    }

    public String getName()
    {
        if (path == null)
        {
            return null;
        } else
        {
            return path.toString();
        }
    }

    public Path getPath()
    {
        return path;
    }

    public char getReadState()
    {
        return readState;
    }

    public void incrLineNumber(int i, int j)
    {
        lineNumber = i + lineNumber;
        lineStartPos = j;
    }

    public void lineStart(boolean flag)
        throws IOException
    {
    }

    public void mark(int i)
    {
        this;
        JVM INSTR monitorenter ;
        if (readAheadLimit > 0)
        {
            clearMark();
        }
        readAheadLimit = i;
        markPos = pos;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean markSupported()
    {
        return true;
    }

    public int peek()
        throws IOException
    {
        if (pos < limit && pos > 0)
        {
            char c = buffer[-1 + pos];
            if (c != '\n' && c != '\r')
            {
                char c1 = buffer[pos];
                if (c1 == '\r' && getConvertCR())
                {
                    c1 = '\n';
                }
                return c1;
            }
        }
        int i = read();
        if (i >= 0)
        {
            unread_quick();
        }
        return i;
    }

    public int read()
        throws IOException
    {
        char c;
        char c1;
        int j;
        if (pos > 0)
        {
            c = buffer[-1 + pos];
        } else
        if ((4 & flags) != 0)
        {
            c = '\r';
        } else
        if (lineStartPos >= 0)
        {
            c = '\n';
        } else
        {
            c = '\0';
        }
        if (c == '\r' || c == '\n')
        {
            if (lineStartPos < pos && (readAheadLimit == 0 || pos <= markPos))
            {
                lineStartPos = pos;
                lineNumber = 1 + lineNumber;
            }
            boolean flag;
            if (pos < highestPos)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (c != '\n' || (pos > 1 ? buffer[-2 + pos] != '\r' : (4 & flags) == 0))
            {
                lineStart(flag);
            }
            if (!flag)
            {
                highestPos = 1 + pos;
            }
        }
        if (pos < limit) goto _L2; else goto _L1
_L1:
        if (buffer == null)
        {
            buffer = new char[8192];
        } else
        if (limit == buffer.length)
        {
            reserve(buffer, 1);
        }
        if (pos == 0)
        {
            if (c == '\r')
            {
                flags = 4 | flags;
            } else
            {
                flags = -5 & flags;
            }
        }
        j = fill(buffer.length - pos);
        if (j > 0) goto _L4; else goto _L3
_L3:
        c1 = '\uFFFF';
_L6:
        return c1;
_L4:
        limit = j + limit;
_L2:
        char ac[] = buffer;
        int i = pos;
        pos = i + 1;
        c1 = ac[i];
        if (c1 != '\n')
        {
            continue; /* Loop/switch isn't completed */
        }
        if (c != '\r') goto _L6; else goto _L5
_L5:
        if (lineStartPos == -1 + pos)
        {
            lineNumber = -1 + lineNumber;
            lineStartPos = -1 + lineStartPos;
        }
        if (!getConvertCR()) goto _L6; else goto _L7
_L7:
        return read();
        if (c1 != '\r' || !getConvertCR()) goto _L6; else goto _L8
_L8:
        return 10;
    }

    public int read(char ac[], int i, int j)
        throws IOException
    {
        int k;
        int l;
        int i1;
        if (pos >= limit)
        {
            k = 0;
        } else
        if (pos > 0)
        {
            k = buffer[-1 + pos];
        } else
        if ((4 & flags) != 0 || lineStartPos >= 0)
        {
            k = 10;
        } else
        {
            k = 0;
        }
_L6:
        l = j;
        i1 = i;
_L2:
        int l1;
        int i2;
        if (l <= 0)
        {
            break MISSING_BLOCK_LABEL_277;
        }
        if (pos >= limit || k == 10 || k == 13)
        {
            if (pos >= limit && l < j)
            {
                return j - l;
            }
            k = read();
            if (k < 0)
            {
                int k1 = j - l;
                if (k1 <= 0)
                {
                    return -1;
                } else
                {
                    return k1;
                }
            }
            int j1 = i1 + 1;
            ac[i1] = (char)k;
            l--;
            i1 = j1;
            continue; /* Loop/switch isn't completed */
        }
        l1 = pos;
        i2 = limit;
        if (l < i2 - l1)
        {
            i2 = l1 + l;
        }
_L3:
label0:
        {
            if (l1 < i2)
            {
                k = buffer[l1];
                if (k != 10 && k != 13)
                {
                    break label0;
                }
            }
            l -= l1 - pos;
            pos = l1;
        }
        if (true) goto _L2; else goto _L1
_L1:
        int j2 = i1 + 1;
        ac[i1] = (char)k;
        l1++;
        i1 = j2;
          goto _L3
        if (true) goto _L2; else goto _L4
_L4:
        return j;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public String readLine()
        throws IOException
    {
        int j;
        int l;
label0:
        {
label1:
            {
                int i = read();
                if (i < 0)
                {
                    return null;
                }
                if (i == 13 || i == 10)
                {
                    return "";
                }
                j = -1 + pos;
                do
                {
                    if (pos >= limit)
                    {
                        break;
                    }
                    char ac[] = buffer;
                    int k = pos;
                    pos = k + 1;
                    char c = ac[k];
                    if (c != '\r' && c != '\n')
                    {
                        continue;
                    }
                    l = -1 + pos;
                    if (c == '\n' || getConvertCR())
                    {
                        break label0;
                    }
                    if (pos < limit)
                    {
                        break label1;
                    }
                    pos = -1 + pos;
                    break;
                } while (true);
                StringBuffer stringbuffer = new StringBuffer(100);
                stringbuffer.append(buffer, j, pos - j);
                readLine(stringbuffer, 'I');
                return stringbuffer.toString();
            }
            if (buffer[pos] == '\n')
            {
                pos = 1 + pos;
            }
        }
        return new String(buffer, j, l - j);
    }

    public void readLine(StringBuffer stringbuffer, char c)
        throws IOException
    {
_L7:
        if (read() >= 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        i = -1 + pos;
        pos = i;
        char c1;
        do
        {
            if (pos >= limit)
            {
                break MISSING_BLOCK_LABEL_185;
            }
            char ac[] = buffer;
            int j = pos;
            pos = j + 1;
            c1 = ac[j];
        } while (c1 != '\r' && c1 != '\n');
        stringbuffer.append(buffer, i, (-1 + pos) - i);
        if (c == 'P')
        {
            pos = -1 + pos;
            return;
        }
        if (!getConvertCR() && c1 != '\n')
        {
            break; /* Loop/switch isn't completed */
        }
        if (c != 'I')
        {
            stringbuffer.append('\n');
            return;
        }
        if (true) goto _L1; else goto _L3
_L3:
        int k;
        if (c != 'I')
        {
            stringbuffer.append('\r');
        }
        k = read();
        if (k != 10)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (c == 'I') goto _L1; else goto _L4
_L4:
        stringbuffer.append('\n');
        return;
        if (k < 0) goto _L1; else goto _L5
_L5:
        unread_quick();
        return;
        stringbuffer.append(buffer, i, pos - i);
        if (true) goto _L7; else goto _L6
_L6:
    }

    public boolean ready()
        throws IOException
    {
        return pos < limit || in.ready();
    }

    public void reset()
        throws IOException
    {
        if (readAheadLimit <= 0)
        {
            throw new IOException("mark invalid");
        }
        if (pos > highestPos)
        {
            highestPos = pos;
        }
        pos = markPos;
        readAheadLimit = 0;
    }

    public void setBuffer(char ac[])
        throws IOException
    {
        if (ac == null)
        {
            if (buffer != null)
            {
                char ac1[] = new char[buffer.length];
                System.arraycopy(buffer, 0, ac1, 0, buffer.length);
                buffer = ac1;
            }
            flags = -3 & flags;
            return;
        }
        if (limit - pos > ac.length)
        {
            throw new IOException("setBuffer - too short");
        } else
        {
            flags = 2 | flags;
            reserve(ac, 0);
            return;
        }
    }

    public final void setConvertCR(boolean flag)
    {
        if (flag)
        {
            flags = 1 | flags;
            return;
        } else
        {
            flags = -2 & flags;
            return;
        }
    }

    public void setKeepFullLines(boolean flag)
    {
        if (flag)
        {
            flags = -9 & flags;
            return;
        } else
        {
            flags = 8 | flags;
            return;
        }
    }

    public void setLineNumber(int i)
    {
        lineNumber = lineNumber + (i - getLineNumber());
    }

    public void setName(Object obj)
    {
        setPath(Path.valueOf(obj));
    }

    public void setPath(Path path1)
    {
        path = path1;
    }

    public int skip(int i)
        throws IOException
    {
        if (i >= 0) goto _L2; else goto _L1
_L1:
        int j1;
        for (j1 = -i; j1 > 0 && pos > 0; j1--)
        {
            unread();
        }

        i += j1;
_L3:
        return i;
_L2:
        int j;
        int k;
        j = i;
        if (pos >= limit)
        {
            k = 0;
        } else
        if (pos > 0)
        {
            k = buffer[-1 + pos];
        } else
        if ((4 & flags) != 0 || lineStartPos >= 0)
        {
            k = 10;
        } else
        {
            k = 0;
        }
_L4:
        while (j > 0) 
        {
label0:
            {
                if (k != 10 && k != 13 && pos < limit)
                {
                    break label0;
                }
                k = read();
                if (k < 0)
                {
                    return i - j;
                }
                j--;
            }
        }
          goto _L3
        int l;
        int i1;
        l = pos;
        i1 = limit;
        if (j < i1 - l)
        {
            i1 = l + j;
        }
_L5:
label1:
        {
            if (l < i1)
            {
                k = buffer[l];
                if (k != 10 && k != 13)
                {
                    break label1;
                }
            }
            j -= l - pos;
            pos = l;
        }
          goto _L4
          goto _L3
        l++;
          goto _L5
    }

    public void skip()
        throws IOException
    {
        read();
    }

    public void skipRestOfLine()
        throws IOException
    {
_L5:
        int i = read();
        if (i >= 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int j;
        if (i != 13)
        {
            continue; /* Loop/switch isn't completed */
        }
        if ((j = read()) < 0 || j == 10) goto _L1; else goto _L3
_L3:
        unread();
        return;
        if (i != 10) goto _L5; else goto _L4
_L4:
    }

    public final void skip_quick()
        throws IOException
    {
        pos = 1 + pos;
    }

    public void unread()
        throws IOException
    {
        if (pos == 0)
        {
            throw new IOException("unread too much");
        }
        pos = -1 + pos;
        char c = buffer[pos];
        if (c == '\n' || c == '\r')
        {
            if (pos > 0 && c == '\n' && getConvertCR() && buffer[-1 + pos] == '\r')
            {
                pos = -1 + pos;
            }
            if (pos < lineStartPos)
            {
                lineNumber = -1 + lineNumber;
                int i = pos;
                do
                {
                    if (i <= 0)
                    {
                        break;
                    }
                    char ac[] = buffer;
                    i--;
                    char c1 = ac[i];
                    if (c1 != '\r' && c1 != '\n')
                    {
                        continue;
                    }
                    i++;
                    break;
                } while (true);
                lineStartPos = i;
            }
        }
    }

    public void unread_quick()
    {
        pos = -1 + pos;
    }
}
