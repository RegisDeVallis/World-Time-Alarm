// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import gnu.mapping.WrappedException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

// Referenced classes of package gnu.text:
//            URIPath, ResourceStreamHandler, Path

public class URLPath extends URIPath
{

    final URL url;

    URLPath(URL url1)
    {
        super(toUri(url1));
        url = url1;
    }

    public static URLPath classResourcePath(Class class1)
    {
        URL url3 = ResourceStreamHandler.makeURL(class1);
        URL url2 = url3;
_L2:
        return valueOf(url2);
        SecurityException securityexception;
        securityexception;
        URL url1;
        try
        {
            String s = (new StringBuilder()).append(class1.getName().replace('.', '/')).append(".class").toString();
            url1 = class1.getClassLoader().getResource(s);
        }
        catch (Throwable throwable)
        {
            throw WrappedException.wrapIfNeeded(throwable);
        }
        url2 = url1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static int getContentLength(URL url1)
    {
        int i;
        try
        {
            i = url1.openConnection().getContentLength();
        }
        catch (Throwable throwable)
        {
            return -1;
        }
        return i;
    }

    public static long getLastModified(URL url1)
    {
        long l;
        try
        {
            l = url1.openConnection().getLastModified();
        }
        catch (Throwable throwable)
        {
            return 0L;
        }
        return l;
    }

    public static InputStream openInputStream(URL url1)
        throws IOException
    {
        return url1.openConnection().getInputStream();
    }

    public static OutputStream openOutputStream(URL url1)
        throws IOException
    {
        String s;
        s = url1.toString();
        if (!s.startsWith("file:"))
        {
            break MISSING_BLOCK_LABEL_41;
        }
        FileOutputStream fileoutputstream = new FileOutputStream(new File(new URI(s)));
        return fileoutputstream;
        Throwable throwable;
        throwable;
        URLConnection urlconnection = url1.openConnection();
        urlconnection.setDoInput(false);
        urlconnection.setDoOutput(true);
        return urlconnection.getOutputStream();
    }

    public static URI toUri(URL url1)
    {
        URI uri;
        try
        {
            uri = url1.toURI();
        }
        catch (Throwable throwable)
        {
            throw WrappedException.wrapIfNeeded(throwable);
        }
        return uri;
    }

    public static URLPath valueOf(URL url1)
    {
        return new URLPath(url1);
    }

    public long getContentLength()
    {
        return (long)getContentLength(url);
    }

    public long getLastModified()
    {
        return getLastModified(url);
    }

    public boolean isAbsolute()
    {
        return true;
    }

    public InputStream openInputStream()
        throws IOException
    {
        return openInputStream(url);
    }

    public OutputStream openOutputStream()
        throws IOException
    {
        return openOutputStream(url);
    }

    public Path resolve(String s)
    {
        URLPath urlpath;
        try
        {
            urlpath = valueOf(new URL(url, s));
        }
        catch (Throwable throwable)
        {
            throw WrappedException.wrapIfNeeded(throwable);
        }
        return urlpath;
    }

    public String toURIString()
    {
        return url.toString();
    }

    public URL toURL()
    {
        return url;
    }

    public URI toUri()
    {
        return toUri(url);
    }
}
