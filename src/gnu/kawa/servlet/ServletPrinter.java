// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.servlet;

import gnu.kawa.xml.HttpPrinter;
import java.io.IOException;

// Referenced classes of package gnu.kawa.servlet:
//            HttpOutputStream, HttpRequestContext

public class ServletPrinter extends HttpPrinter
{

    HttpRequestContext hctx;

    public ServletPrinter(HttpRequestContext httprequestcontext, int i)
        throws IOException
    {
        super(new HttpOutputStream(httprequestcontext, i));
        hctx = httprequestcontext;
    }

    public void addHeader(String s, String s1)
    {
        if (s.equalsIgnoreCase("Content-type"))
        {
            super.sawContentType = s1;
            hctx.setContentType(s1);
        } else
        if (s.equalsIgnoreCase("Status"))
        {
            int i = s1.length();
            int j = 0;
            int k = 0;
            while (k < i) 
            {
                if (k >= i)
                {
                    hctx.statusCode = j;
                    return;
                }
                char c = s1.charAt(k);
                int l = Character.digit(c, 10);
                if (l >= 0)
                {
                    j = l + j * 10;
                    k++;
                } else
                {
                    if (c == ' ')
                    {
                        k++;
                    }
                    hctx.statusCode = j;
                    hctx.statusReasonPhrase = s1.substring(k);
                    return;
                }
            }
        } else
        {
            hctx.setResponseHeader(s, s1);
            return;
        }
    }

    public void printHeaders()
    {
    }

    public boolean reset(boolean flag)
    {
        return ((HttpOutputStream)ostream).reset() & super.reset(flag);
    }
}
