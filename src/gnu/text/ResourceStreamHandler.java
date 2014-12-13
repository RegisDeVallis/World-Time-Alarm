// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class ResourceStreamHandler extends URLStreamHandler
{

    public static final String CLASS_RESOURCE_URI_PREFIX = "class-resource:/";
    public static final int CLASS_RESOURCE_URI_PREFIX_LENGTH = 16;
    ClassLoader cloader;

    public ResourceStreamHandler(ClassLoader classloader)
    {
        cloader = classloader;
    }

    public static URL makeURL(Class class1)
        throws MalformedURLException
    {
        String s = class1.getName();
        int i = s.lastIndexOf('.');
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("class-resource:/");
        if (i >= 0)
        {
            stringbuilder.append(s.substring(0, i));
            stringbuilder.append('/');
            s = s.substring(i + 1);
        }
        stringbuilder.append(s);
        return new URL(null, stringbuilder.toString(), new ResourceStreamHandler(class1.getClassLoader()));
    }

    public URLConnection openConnection(URL url)
        throws IOException
    {
        String s = url.toString();
        String s1 = s.substring(16);
        int i = s1.indexOf('/');
        if (i > 0)
        {
            s1 = (new StringBuilder()).append(s1.substring(0, i).replace('.', '/')).append(s1.substring(i)).toString();
        }
        URL url1 = cloader.getResource(s1);
        if (url1 == null)
        {
            throw new FileNotFoundException(s);
        } else
        {
            return url1.openConnection();
        }
    }
}
