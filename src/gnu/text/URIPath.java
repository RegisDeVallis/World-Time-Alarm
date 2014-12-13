// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import gnu.lists.FString;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

// Referenced classes of package gnu.text:
//            Path, URLPath, URIStringPath, FilePath

public class URIPath extends Path
    implements Comparable
{

    final URI uri;

    URIPath(URI uri1)
    {
        uri = uri1;
    }

    public static URIPath coerceToURIPathOrNull(Object obj)
    {
        if (obj instanceof URIPath)
        {
            return (URIPath)obj;
        }
        if (obj instanceof URL)
        {
            return URLPath.valueOf((URL)obj);
        }
        if (obj instanceof URI)
        {
            return valueOf((URI)obj);
        }
        String s;
        if ((obj instanceof File) || (obj instanceof Path) || (obj instanceof FString))
        {
            s = obj.toString();
        } else
        if (obj instanceof String)
        {
            s = (String)obj;
        } else
        {
            return null;
        }
        return valueOf(s);
    }

    public static String encodeForUri(String s, char c)
    {
        StringBuffer stringbuffer = new StringBuffer();
        int i = s.length();
        int j = 0;
        while (j < i) 
        {
            int k = j + 1;
            int l = s.charAt(j);
            if (l >= 55296 && l < 56320 && k < i)
            {
                int l2 = 1024 * (l - 55296);
                int i3 = k + 1;
                l = 0x10000 + (l2 + (s.charAt(k) - 56320));
                k = i3;
            }
            if (c != 'H' ? l >= 97 && l <= 122 || l >= 65 && l <= 90 || l >= 48 && l <= 57 || l == 45 || l == 95 || l == 46 || l == 126 || c == 'I' && (l == 59 || l == 47 || l == 63 || l == 58 || l == 42 || l == 39 || l == 40 || l == 41 || l == 64 || l == 38 || l == 61 || l == 43 || l == 36 || l == 44 || l == 91 || l == 93 || l == 35 || l == 33 || l == 37) : l >= 32 && l <= 126)
            {
                stringbuffer.append((char)l);
            } else
            {
                int i1 = stringbuffer.length();
                int j1 = 0;
                if (l >= 128)
                {
                    if (l < 2048)
                    {
                        j1 = 0;
                    } else
                    if (l < 0x10000)
                    {
                        j1 = 0;
                    } else
                    {
                        j1 = 0;
                    }
                }
                do
                {
                    int k1;
                    int l1;
                    int i2;
                    if (j1 == 0)
                    {
                        k1 = 7;
                    } else
                    {
                        k1 = 6 - j1;
                    }
                    if (l < 1 << k1)
                    {
                        l1 = l;
                        if (j1 > 0)
                        {
                            l1 |= 0xff & 65408 >> j1;
                        }
                        l = 0;
                    } else
                    {
                        l1 = 0x80 | l & 0x3f;
                        l >>= 6;
                    }
                    j1++;
                    i2 = 0;
                    while (i2 <= 1) 
                    {
                        int j2 = l1 & 0xf;
                        int k2;
                        if (j2 <= 9)
                        {
                            k2 = j2 + 48;
                        } else
                        {
                            k2 = 65 + (j2 - 10);
                        }
                        stringbuffer.insert(i1, (char)k2);
                        l1 >>= 4;
                        i2++;
                    }
                    stringbuffer.insert(i1, '%');
                } while (l != 0);
            }
            j = k;
        }
        return stringbuffer.toString();
    }

    public static URIPath makeURI(Object obj)
    {
        URIPath uripath = coerceToURIPathOrNull(obj);
        if (uripath == null)
        {
            throw new WrongType((String)null, -4, obj, "URI");
        } else
        {
            return uripath;
        }
    }

    public static URIPath valueOf(String s)
    {
        URIStringPath uristringpath;
        try
        {
            uristringpath = new URIStringPath(new URI(encodeForUri(s, 'I')), s);
        }
        catch (Throwable throwable)
        {
            throw WrappedException.wrapIfNeeded(throwable);
        }
        return uristringpath;
    }

    public static URIPath valueOf(URI uri1)
    {
        return new URIPath(uri1);
    }

    public int compareTo(URIPath uripath)
    {
        return uri.compareTo(uripath.uri);
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((URIPath)obj);
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof URIPath) && uri.equals(((URIPath)obj).uri);
    }

    public boolean exists()
    {
        boolean flag = true;
        URLConnection urlconnection = toURL().openConnection();
        Throwable throwable;
        long l;
        if (urlconnection instanceof HttpURLConnection)
        {
            if (((HttpURLConnection)urlconnection).getResponseCode() == 200)
            {
                return flag;
            } else
            {
                return false;
            }
        }
        l = urlconnection.getLastModified();
        if (l == 0L)
        {
            return false;
        }
        break MISSING_BLOCK_LABEL_50;
        throwable;
        flag = false;
        return flag;
    }

    public String getAuthority()
    {
        return uri.getAuthority();
    }

    public Path getCanonical()
    {
        if (isAbsolute())
        {
            URI uri1 = uri.normalize();
            if (uri1 == uri)
            {
                return this;
            } else
            {
                return valueOf(uri1);
            }
        } else
        {
            return getAbsolute().getCanonical();
        }
    }

    public long getContentLength()
    {
        return (long)URLPath.getContentLength(toURL());
    }

    public String getFragment()
    {
        return uri.getFragment();
    }

    public String getHost()
    {
        return uri.getHost();
    }

    public long getLastModified()
    {
        return URLPath.getLastModified(toURL());
    }

    public String getPath()
    {
        return uri.getPath();
    }

    public int getPort()
    {
        return uri.getPort();
    }

    public String getQuery()
    {
        return uri.getQuery();
    }

    public String getScheme()
    {
        return uri.getScheme();
    }

    public String getUserInfo()
    {
        return uri.getUserInfo();
    }

    public int hashCode()
    {
        return uri.hashCode();
    }

    public boolean isAbsolute()
    {
        return uri.isAbsolute();
    }

    public InputStream openInputStream()
        throws IOException
    {
        return URLPath.openInputStream(toURL());
    }

    public OutputStream openOutputStream()
        throws IOException
    {
        return URLPath.openOutputStream(toURL());
    }

    public Path resolve(String s)
    {
        if (Path.uriSchemeSpecified(s))
        {
            return valueOf(s);
        }
        char c = File.separatorChar;
        if (c != '/')
        {
            if (s.length() >= 2 && (s.charAt(1) == ':' && Character.isLetter(s.charAt(0)) || s.charAt(0) == c && s.charAt(1) == c))
            {
                return FilePath.valueOf(new File(s));
            }
            s = s.replace(c, '/');
        }
        URI uri1;
        try
        {
            uri1 = uri.resolve(new URI(null, s, null));
        }
        catch (Throwable throwable)
        {
            throw WrappedException.wrapIfNeeded(throwable);
        }
        return valueOf(uri1);
    }

    public String toString()
    {
        return toURIString();
    }

    public String toURIString()
    {
        return uri.toString();
    }

    public URL toURL()
    {
        return Path.toURL(uri.toString());
    }

    public URI toUri()
    {
        return uri;
    }
}
