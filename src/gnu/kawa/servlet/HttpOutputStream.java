// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.servlet;

import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package gnu.kawa.servlet:
//            HttpRequestContext

class HttpOutputStream extends OutputStream
{

    byte buffer[];
    HttpRequestContext context;
    int count;
    OutputStream out;

    public HttpOutputStream(HttpRequestContext httprequestcontext, int i)
    {
        context = httprequestcontext;
        buffer = new byte[i];
    }

    public void close()
        throws IOException
    {
        if (out == null)
        {
            maybeSendResponseHeaders(count);
            out = context.getResponseStream();
        }
        flush();
        out.close();
    }

    public void flush()
        throws IOException
    {
        if (out == null)
        {
            maybeSendResponseHeaders(-1);
            out = context.getResponseStream();
        }
        if (count > 0)
        {
            out.write(buffer, 0, count);
            count = 0;
        }
    }

    void maybeSendResponseHeaders(int i)
        throws IOException
    {
        int j = context.statusCode;
        if (j != -999)
        {
            context.sendResponseHeaders(j, context.statusReasonPhrase, i);
            context.statusCode = -999;
        }
    }

    public boolean reset()
    {
        count = 0;
        OutputStream outputstream = out;
        boolean flag = false;
        if (outputstream == null)
        {
            flag = true;
        }
        return flag;
    }

    public void write(int i)
        throws IOException
    {
        if (count >= buffer.length)
        {
            flush();
        }
        byte abyte0[] = buffer;
        int j = count;
        count = j + 1;
        abyte0[j] = (byte)i;
    }

    public void write(byte abyte0[], int i, int j)
        throws IOException
    {
        for (int k = buffer.length - count; j > k; k = buffer.length)
        {
            System.arraycopy(abyte0, i, buffer, count, k);
            count = k + count;
            flush();
            i += k;
            j -= k;
        }

        if (j > 0)
        {
            System.arraycopy(abyte0, i, buffer, count, j);
            count = j + count;
        }
    }
}
