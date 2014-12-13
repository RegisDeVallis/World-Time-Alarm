// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

// Referenced classes of package gnu.text:
//            LineBufferedReader, SourceMessages, SyntaxException, SourceError

public class Lexer extends Reader
{

    protected boolean interactive;
    SourceMessages messages;
    protected int nesting;
    protected LineBufferedReader port;
    private int saveTokenBufferLength;
    public char tokenBuffer[];
    public int tokenBufferLength;

    public Lexer(LineBufferedReader linebufferedreader)
    {
        messages = null;
        tokenBuffer = new char[100];
        tokenBufferLength = 0;
        saveTokenBufferLength = -1;
        port = linebufferedreader;
    }

    public Lexer(LineBufferedReader linebufferedreader, SourceMessages sourcemessages)
    {
        messages = null;
        tokenBuffer = new char[100];
        tokenBufferLength = 0;
        saveTokenBufferLength = -1;
        port = linebufferedreader;
        messages = sourcemessages;
    }

    public static long readDigitsInBuffer(LineBufferedReader linebufferedreader, int i)
    {
        long l;
        long l1;
        int j;
        boolean flag;
        l = 0L;
        l1 = 0x7fffffffffffffffL / (long)i;
        j = linebufferedreader.pos;
        int k = linebufferedreader.limit;
        flag = false;
        if (j >= k)
        {
            return 0L;
        }
_L3:
        int i1 = Character.digit(linebufferedreader.buffer[j], i);
        if (i1 >= 0) goto _L2; else goto _L1
_L1:
        linebufferedreader.pos = j;
        if (flag)
        {
            return -1L;
        } else
        {
            return l;
        }
_L2:
        if (l > l1)
        {
            flag = true;
        } else
        {
            l = l * (long)i + (long)i1;
        }
        if (l < 0L)
        {
            flag = true;
        }
        if (++j < linebufferedreader.limit) goto _L3; else goto _L1
    }

    public boolean checkErrors(PrintWriter printwriter, int i)
    {
        return messages != null && messages.checkErrors(printwriter, i);
    }

    public boolean checkNext(char c)
        throws IOException
    {
        int i = port.read();
        if (i == c)
        {
            return true;
        }
        if (i >= 0)
        {
            port.unread_quick();
        }
        return false;
    }

    public void clearErrors()
    {
        if (messages != null)
        {
            messages.clearErrors();
        }
    }

    public void close()
        throws IOException
    {
        port.close();
    }

    public void eofError(String s)
        throws SyntaxException
    {
        fatal(s);
    }

    public void eofError(String s, int i, int j)
        throws SyntaxException
    {
        error('f', port.getName(), i, j, s);
        throw new SyntaxException(messages);
    }

    public void error(char c, String s)
    {
        int i = port.getLineNumber();
        int j = port.getColumnNumber();
        String s1 = port.getName();
        int k = i + 1;
        int l;
        if (j >= 0)
        {
            l = j + 1;
        } else
        {
            l = 0;
        }
        error(c, s1, k, l, s);
    }

    public void error(char c, String s, int i, int j, String s1)
    {
        if (messages == null)
        {
            messages = new SourceMessages();
        }
        messages.error(c, s, i, j, s1);
    }

    public void error(String s)
    {
        error('e', s);
    }

    public void fatal(String s)
        throws SyntaxException
    {
        error('f', s);
        throw new SyntaxException(messages);
    }

    public int getColumnNumber()
    {
        return port.getColumnNumber();
    }

    public SourceError getErrors()
    {
        if (messages == null)
        {
            return null;
        } else
        {
            return messages.getErrors();
        }
    }

    public int getLineNumber()
    {
        return port.getLineNumber();
    }

    public SourceMessages getMessages()
    {
        return messages;
    }

    public String getName()
    {
        return port.getName();
    }

    public final LineBufferedReader getPort()
    {
        return port;
    }

    public boolean isInteractive()
    {
        return interactive;
    }

    public void mark()
        throws IOException
    {
        if (saveTokenBufferLength >= 0)
        {
            throw new Error("internal error: recursive call to mark not allowed");
        } else
        {
            port.mark(0x7fffffff);
            saveTokenBufferLength = tokenBufferLength;
            return;
        }
    }

    public int peek()
        throws IOException
    {
        return port.peek();
    }

    public void popNesting(char c)
    {
        getPort().readState = c;
        nesting = -1 + nesting;
    }

    public char pushNesting(char c)
    {
        nesting = 1 + nesting;
        LineBufferedReader linebufferedreader = getPort();
        char c1 = linebufferedreader.readState;
        linebufferedreader.readState = c;
        return c1;
    }

    public int read()
        throws IOException
    {
        return port.read();
    }

    public int read(char ac[], int i, int j)
        throws IOException
    {
        return port.read(ac, i, j);
    }

    public boolean readDelimited(String s)
        throws IOException, SyntaxException
    {
        tokenBufferLength = 0;
        int i = s.length();
        char c = s.charAt(i - 1);
        do
        {
            int j = read();
            if (j < 0)
            {
                return false;
            }
            if (j == c)
            {
                int k = tokenBufferLength;
                int l = i - 1;
                int i1 = k - l;
                if (i1 >= 0)
                {
                    do
                    {
                        if (l == 0)
                        {
                            tokenBufferLength = i1;
                            return true;
                        }
                        l--;
                    } while (tokenBuffer[i1 + l] == s.charAt(l));
                }
            }
            tokenBufferAppend((char)j);
        } while (true);
    }

    public int readOptionalExponent()
        throws IOException
    {
        boolean flag;
        int j;
        int k;
        int i = read();
        flag = false;
        if (i == 43 || i == 45)
        {
            j = read();
        } else
        {
            j = i;
            i = 0;
        }
        if (j < 0) goto _L2; else goto _L1
_L1:
        k = Character.digit((char)j, 10);
        if (k >= 0) goto _L3; else goto _L2
_L2:
        if (i != 0)
        {
            error("exponent sign not followed by digit");
        }
        k = 1;
_L5:
        if (j >= 0)
        {
            unread(j);
        }
        if (i == 45)
        {
            k = -k;
        }
        int l;
        if (flag)
        {
            return i != 45 ? 0x7fffffff : 0x80000000;
        } else
        {
            return k;
        }
_L3:
        j = read();
        l = Character.digit((char)j, 10);
        if (l < 0) goto _L5; else goto _L4
_L4:
        if (k > 0xccccccb)
        {
            flag = true;
        }
        k = l + k * 10;
          goto _L3
    }

    public int readUnicodeChar()
        throws IOException
    {
        int i = port.read();
        if (i >= 55296 && i < 56319)
        {
            int j = port.read();
            if (j >= 56320 && j <= 57343)
            {
                i = 0x10000 + ((i - 55296 << 10) + (i - 56320));
            }
        }
        return i;
    }

    public void reset()
        throws IOException
    {
        if (saveTokenBufferLength < 0)
        {
            throw new Error("internal error: reset called without prior mark");
        } else
        {
            port.reset();
            saveTokenBufferLength = -1;
            return;
        }
    }

    public boolean seenErrors()
    {
        return messages != null && messages.seenErrors();
    }

    public void setInteractive(boolean flag)
    {
        interactive = flag;
    }

    public void setMessages(SourceMessages sourcemessages)
    {
        messages = sourcemessages;
    }

    public void skip()
        throws IOException
    {
        port.skip();
    }

    protected void skip_quick()
        throws IOException
    {
        port.skip_quick();
    }

    public void tokenBufferAppend(int i)
    {
        if (i >= 0x10000)
        {
            tokenBufferAppend(55296 + (i - 0x10000 >> 10));
            i = 56320 + (i & 0x3ff);
        }
        int j = tokenBufferLength;
        char ac[] = tokenBuffer;
        if (j == tokenBuffer.length)
        {
            tokenBuffer = new char[j * 2];
            System.arraycopy(ac, 0, tokenBuffer, 0, j);
            ac = tokenBuffer;
        }
        ac[j] = (char)i;
        tokenBufferLength = j + 1;
    }

    public String tokenBufferString()
    {
        return new String(tokenBuffer, 0, tokenBufferLength);
    }

    protected void unread()
        throws IOException
    {
        port.unread();
    }

    public void unread(int i)
        throws IOException
    {
        if (i >= 0)
        {
            port.unread();
        }
    }

    protected void unread_quick()
        throws IOException
    {
        port.unread_quick();
    }
}
