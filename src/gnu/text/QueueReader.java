// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import gnu.lists.CharSeq;
import java.io.IOException;
import java.io.Reader;

public class QueueReader extends Reader
    implements Appendable
{

    boolean EOFseen;
    char buffer[];
    int limit;
    int mark;
    int pos;
    int readAheadLimit;

    public QueueReader()
    {
    }

    public QueueReader append(char c)
    {
        this;
        JVM INSTR monitorenter ;
        reserveSpace(1);
        char ac[] = buffer;
        int i = limit;
        limit = i + 1;
        ac[i] = c;
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        return this;
        Exception exception;
        exception;
        throw exception;
    }

    public QueueReader append(CharSequence charsequence)
    {
        if (charsequence == null)
        {
            charsequence = "null";
        }
        return append(charsequence, 0, charsequence.length());
    }

    public QueueReader append(CharSequence charsequence, int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        int k;
        if (charsequence == null)
        {
            charsequence = "null";
        }
        k = j - i;
        int l;
        char ac[];
        reserveSpace(k);
        l = limit;
        ac = buffer;
        if (!(charsequence instanceof String)) goto _L2; else goto _L1
_L1:
        ((String)charsequence).getChars(i, j, ac, l);
_L6:
        limit = l + k;
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        return this;
_L2:
        if (charsequence instanceof CharSeq)
        {
            ((CharSeq)charsequence).getChars(i, j, ac, l);
            continue; /* Loop/switch isn't completed */
        }
        break MISSING_BLOCK_LABEL_101;
        Exception exception;
        exception;
        throw exception;
        int i1;
        int j1;
        i1 = i;
        j1 = l;
_L4:
        int k1;
        if (i1 >= j)
        {
            continue; /* Loop/switch isn't completed */
        }
        k1 = j1 + 1;
        ac[j1] = charsequence.charAt(i1);
        i1++;
        j1 = k1;
        if (true) goto _L4; else goto _L3
_L3:
        if (true) goto _L6; else goto _L5
_L5:
    }

    public volatile Appendable append(char c)
        throws IOException
    {
        return append(c);
    }

    public volatile Appendable append(CharSequence charsequence)
        throws IOException
    {
        return append(charsequence);
    }

    public volatile Appendable append(CharSequence charsequence, int i, int j)
        throws IOException
    {
        return append(charsequence, i, j);
    }

    public void append(char ac[])
    {
        append(ac, 0, ac.length);
    }

    public void append(char ac[], int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        reserveSpace(j);
        System.arraycopy(ac, i, buffer, limit, j);
        limit = j + limit;
        notifyAll();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void appendEOF()
    {
        this;
        JVM INSTR monitorenter ;
        EOFseen = true;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void checkAvailable()
    {
    }

    public void close()
    {
        this;
        JVM INSTR monitorenter ;
        pos = 0;
        limit = 0;
        mark = 0;
        EOFseen = true;
        buffer = null;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void mark(int i)
    {
        this;
        JVM INSTR monitorenter ;
        readAheadLimit = i;
        mark = pos;
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

    public int read()
    {
        this;
        JVM INSTR monitorenter ;
_L8:
        if (pos < limit) goto _L2; else goto _L1
_L1:
        boolean flag = EOFseen;
        if (!flag) goto _L4; else goto _L3
_L3:
        char c = '\uFFFF';
_L6:
        this;
        JVM INSTR monitorexit ;
        return c;
_L4:
        checkAvailable();
        try
        {
            wait();
        }
        catch (InterruptedException interruptedexception) { }
        continue; /* Loop/switch isn't completed */
_L2:
        char ac[] = buffer;
        int i = pos;
        pos = i + 1;
        c = ac[i];
        if (true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public int read(char ac[], int i, int j)
    {
        this;
        JVM INSTR monitorenter ;
        if (j != 0) goto _L2; else goto _L1
_L1:
        int l = 0;
_L8:
        this;
        JVM INSTR monitorexit ;
        return l;
_L6:
        checkAvailable();
        int k;
        try
        {
            wait();
        }
        catch (InterruptedException interruptedexception) { }
_L2:
        if (pos < limit) goto _L4; else goto _L3
_L3:
        if (!EOFseen) goto _L6; else goto _L5
_L5:
        l = -1;
        continue; /* Loop/switch isn't completed */
_L4:
        k = limit - pos;
        if (j > k)
        {
            j = k;
        }
        System.arraycopy(buffer, pos, ac, i, j);
        pos = j + pos;
        l = j;
        if (true) goto _L8; else goto _L7
_L7:
        Exception exception;
        exception;
        throw exception;
    }

    public boolean ready()
    {
        this;
        JVM INSTR monitorenter ;
        if (pos < limit) goto _L2; else goto _L1
_L1:
        boolean flag1 = EOFseen;
        if (!flag1) goto _L3; else goto _L2
_L2:
        boolean flag = true;
_L5:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L3:
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
        Exception exception;
        exception;
        throw exception;
    }

    protected void reserveSpace(int i)
    {
        if (buffer == null)
        {
            buffer = new char[i + 100];
        } else
        if (buffer.length < i + limit)
        {
            resize(i);
            return;
        }
    }

    public void reset()
    {
        this;
        JVM INSTR monitorenter ;
        if (readAheadLimit > 0)
        {
            pos = mark;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    void resize(int i)
    {
        int j = limit - pos;
        char ac[];
        if (readAheadLimit > 0 && pos - mark <= readAheadLimit)
        {
            j = limit - mark;
        } else
        {
            mark = pos;
        }
        if (buffer.length < j + i)
        {
            ac = new char[i + j * 2];
        } else
        {
            ac = buffer;
        }
        System.arraycopy(buffer, mark, ac, 0, j);
        buffer = ac;
        pos = pos - mark;
        mark = 0;
        limit = j;
    }
}
