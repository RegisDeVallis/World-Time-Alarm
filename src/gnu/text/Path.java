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
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

// Referenced classes of package gnu.text:
//            FilePath, URLPath, URIPath

public abstract class Path
{

    public static Path defaultPath;
    private static ThreadLocal pathLocation = new ThreadLocal();
    public static final FilePath userDirPath;

    protected Path()
    {
    }

    public static Path coerceToPathOrNull(Object obj)
    {
        if (obj instanceof Path)
        {
            return (Path)obj;
        }
        if (obj instanceof URL)
        {
            return URLPath.valueOf((URL)obj);
        }
        if (obj instanceof URI)
        {
            return URIPath.valueOf((URI)obj);
        }
        if (obj instanceof File)
        {
            return FilePath.valueOf((File)obj);
        }
        String s;
        if (obj instanceof FString)
        {
            s = obj.toString();
        } else
        {
            if (!(obj instanceof String))
            {
                return null;
            }
            s = (String)obj;
        }
        if (uriSchemeSpecified(s))
        {
            return URIPath.valueOf(s);
        } else
        {
            return FilePath.valueOf(s);
        }
    }

    public static Path currentPath()
    {
        Path path = (Path)pathLocation.get();
        if (path != null)
        {
            return path;
        } else
        {
            return defaultPath;
        }
    }

    public static InputStream openInputStream(Object obj)
        throws IOException
    {
        return valueOf(obj).openInputStream();
    }

    public static String relativize(String s, String s1)
        throws URISyntaxException, IOException
    {
label0:
        {
            String _tmp = s;
            String s2 = (new URI(s1)).normalize().toString();
            String s3 = URLPath.valueOf(s).toURI().normalize().toString();
            int i = s2.length();
            int j = s3.length();
            int k = 0;
            int l = 0;
            int i1 = 0;
            String s5;
            StringBuilder stringbuilder;
label1:
            do
            {
                char c;
label2:
                {
                    if (k < i && k < j)
                    {
                        c = s2.charAt(k);
                        if (c == s3.charAt(k))
                        {
                            break label2;
                        }
                    }
                    if (i1 <= 0 || l <= i1 + 2 && i > i1 + 2 && s2.charAt(i1 + 2) == '/')
                    {
                        break label0;
                    }
                    String s4 = s2.substring(l + 1);
                    s5 = s3.substring(l + 1);
                    stringbuilder = new StringBuilder();
                    int j1 = s4.length();
                    do
                    {
                        if (--j1 < 0)
                        {
                            break;
                        }
                        if (s4.charAt(j1) == '/')
                        {
                            stringbuilder.append("../");
                        }
                    } while (true);
                    break label1;
                }
                if (c == '/')
                {
                    l = k;
                }
                if (c == ':')
                {
                    i1 = k;
                }
                k++;
            } while (true);
            stringbuilder.append(s5);
            s = stringbuilder.toString();
        }
        return s;
    }

    public static void setCurrentPath(Path path)
    {
        pathLocation.set(path);
    }

    public static URL toURL(String s)
    {
        URL url;
        try
        {
            if (!uriSchemeSpecified(s))
            {
                Path path = currentPath().resolve(s);
                if (path.isAbsolute())
                {
                    return path.toURL();
                }
                s = path.toString();
            }
            url = new URL(s);
        }
        catch (Throwable throwable)
        {
            throw WrappedException.wrapIfNeeded(throwable);
        }
        return url;
    }

    public static int uriSchemeLength(String s)
    {
        int i = s.length();
        for (int j = 0; j < i; j++)
        {
            char c = s.charAt(j);
            if (c == ':')
            {
                return j;
            }
            if (j != 0 ? !Character.isLetterOrDigit(c) && c != '+' && c != '-' && c != '.' : !Character.isLetter(c))
            {
                return -1;
            }
        }

        return -1;
    }

    public static boolean uriSchemeSpecified(String s)
    {
        boolean flag;
        int i;
label0:
        {
            boolean flag1;
label1:
            {
                flag = true;
                i = uriSchemeLength(s);
                if (i != flag || File.separatorChar != '\\')
                {
                    break label0;
                }
                char c = s.charAt(0);
                if (c >= 'a')
                {
                    flag1 = false;
                    if (c <= 'z')
                    {
                        break label1;
                    }
                }
                if (c >= 'A')
                {
                    flag1 = false;
                    if (c <= 'Z')
                    {
                        break label1;
                    }
                }
                flag1 = flag;
            }
            return flag1;
        }
        if (i <= 0)
        {
            flag = false;
        }
        return flag;
    }

    public static Path valueOf(Object obj)
    {
        Path path = coerceToPathOrNull(obj);
        if (path == null)
        {
            throw new WrongType((String)null, -4, obj, "path");
        } else
        {
            return path;
        }
    }

    public boolean delete()
    {
        return false;
    }

    public boolean exists()
    {
        return getLastModified() != 0L;
    }

    public Path getAbsolute()
    {
        if (this == userDirPath)
        {
            return resolve("");
        } else
        {
            return currentPath().resolve(this);
        }
    }

    public String getAuthority()
    {
        return null;
    }

    public Path getCanonical()
    {
        return getAbsolute();
    }

    public CharSequence getCharContent(boolean flag)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    public long getContentLength()
    {
        return -1L;
    }

    public Path getDirectory()
    {
        if (isDirectory())
        {
            return this;
        } else
        {
            return resolve("");
        }
    }

    public String getExtension()
    {
        String s = getPath();
        if (s != null) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        int i = s.length();
_L5:
        if (--i <= 0) goto _L1; else goto _L3
_L3:
        char c;
        boolean flag;
        c = s.charAt(i);
        flag = false;
        if (c == '.')
        {
            c = s.charAt(i - 1);
            flag = true;
        }
        if (c == '/' || (this instanceof FilePath) && c == File.separatorChar) goto _L1; else goto _L4
_L4:
        if (flag)
        {
            return s.substring(i + 1);
        }
          goto _L5
    }

    public String getFragment()
    {
        return null;
    }

    public String getHost()
    {
        return null;
    }

    public String getLast()
    {
        String s = getPath();
        if (s == null)
        {
            return null;
        }
        int i = s.length();
        int j = i;
        int k = i;
        do
        {
            char c;
            do
            {
                if (--k <= 0)
                {
                    return "";
                }
                c = s.charAt(k);
            } while (c != '/' && (!(this instanceof FilePath) || c != File.separatorChar));
            if (k + 1 == i)
            {
                j = k;
            } else
            {
                return s.substring(k + 1, j);
            }
        } while (true);
    }

    public abstract long getLastModified();

    public String getName()
    {
        return toString();
    }

    public Path getParent()
    {
        String s;
        if (isDirectory())
        {
            s = "..";
        } else
        {
            s = "";
        }
        return resolve(s);
    }

    public abstract String getPath();

    public int getPort()
    {
        return -1;
    }

    public String getQuery()
    {
        return null;
    }

    public abstract String getScheme();

    public String getUserInfo()
    {
        return null;
    }

    public abstract boolean isAbsolute();

    public boolean isDirectory()
    {
        String s = toString();
        int i = s.length();
        if (i > 0)
        {
            char c = s.charAt(i - 1);
            if (c == '/' || c == File.separatorChar)
            {
                return true;
            }
        }
        return false;
    }

    public abstract InputStream openInputStream()
        throws IOException;

    public abstract OutputStream openOutputStream()
        throws IOException;

    public Reader openReader(boolean flag)
        throws IOException
    {
        throw new UnsupportedOperationException();
    }

    public Writer openWriter()
        throws IOException
    {
        return new OutputStreamWriter(openOutputStream());
    }

    public Path resolve(Path path)
    {
        if (path.isAbsolute())
        {
            return path;
        } else
        {
            return resolve(path.toString());
        }
    }

    public abstract Path resolve(String s);

    public final URI toURI()
    {
        return toUri();
    }

    public String toURIString()
    {
        return toUri().toString();
    }

    public abstract URL toURL();

    public abstract URI toUri();

    static 
    {
        userDirPath = FilePath.valueOf(new File("."));
        defaultPath = userDirPath;
    }
}
