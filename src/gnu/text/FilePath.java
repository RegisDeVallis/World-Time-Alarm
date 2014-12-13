// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import gnu.lists.FString;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

// Referenced classes of package gnu.text:
//            Path, URIPath, URLPath

public class FilePath extends Path
    implements Comparable
{

    final File file;
    final String path;

    private FilePath(File file1)
    {
        file = file1;
        path = file1.toString();
    }

    private FilePath(File file1, String s)
    {
        file = file1;
        path = s;
    }

    public static FilePath coerceToFilePathOrNull(Object obj)
    {
        if (obj instanceof FilePath)
        {
            return (FilePath)obj;
        }
        if (obj instanceof URIPath)
        {
            return valueOf(new File(((URIPath)obj).uri));
        }
        if (obj instanceof URI)
        {
            return valueOf(new File((URI)obj));
        }
        if (obj instanceof File)
        {
            return valueOf((File)obj);
        }
        String s;
        if (obj instanceof FString)
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

    public static FilePath makeFilePath(Object obj)
    {
        FilePath filepath = coerceToFilePathOrNull(obj);
        if (filepath == null)
        {
            throw new WrongType((String)null, -4, obj, "filepath");
        } else
        {
            return filepath;
        }
    }

    private static URI toUri(File file1)
    {
        String s;
        char c;
        URI uri;
        try
        {
            if (file1.isAbsolute())
            {
                return file1.toURI();
            }
            s = file1.toString();
            c = File.separatorChar;
        }
        catch (Throwable throwable)
        {
            throw WrappedException.wrapIfNeeded(throwable);
        }
        if (c == '/')
        {
            break MISSING_BLOCK_LABEL_35;
        }
        s = s.replace(c, '/');
        uri = new URI(null, null, s, null);
        return uri;
    }

    public static FilePath valueOf(File file1)
    {
        return new FilePath(file1);
    }

    public static FilePath valueOf(String s)
    {
        return new FilePath(new File(s), s);
    }

    public int compareTo(FilePath filepath)
    {
        return file.compareTo(filepath.file);
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((FilePath)obj);
    }

    public boolean delete()
    {
        return toFile().delete();
    }

    public boolean equals(Object obj)
    {
        return (obj instanceof FilePath) && file.equals(((FilePath)obj).file);
    }

    public boolean exists()
    {
        return file.exists();
    }

    public Path getCanonical()
    {
label0:
        {
            FilePath filepath;
            try
            {
                File file1 = file.getCanonicalFile();
                if (file1.equals(file))
                {
                    break label0;
                }
                filepath = valueOf(file1);
            }
            catch (Throwable throwable)
            {
                return this;
            }
            this = filepath;
        }
        return this;
    }

    public long getContentLength()
    {
        long l = file.length();
        if (l == 0L && !file.exists())
        {
            l = -1L;
        }
        return l;
    }

    public String getLast()
    {
        return file.getName();
    }

    public long getLastModified()
    {
        return file.lastModified();
    }

    public FilePath getParent()
    {
        File file1 = file.getParentFile();
        if (file1 == null)
        {
            return null;
        } else
        {
            return valueOf(file1);
        }
    }

    public volatile Path getParent()
    {
        return getParent();
    }

    public String getPath()
    {
        return file.getPath();
    }

    public String getScheme()
    {
        if (isAbsolute())
        {
            return "file";
        } else
        {
            return null;
        }
    }

    public int hashCode()
    {
        return file.hashCode();
    }

    public boolean isAbsolute()
    {
        return this == Path.userDirPath || file.isAbsolute();
    }

    public boolean isDirectory()
    {
        if (!file.isDirectory()) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        char c;
        if (file.exists())
        {
            break; /* Loop/switch isn't completed */
        }
        int i = path.length();
        if (i <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        c = path.charAt(i - 1);
        if (c == '/' || c == File.separatorChar) goto _L1; else goto _L3
_L3:
        return false;
    }

    public InputStream openInputStream()
        throws IOException
    {
        return new FileInputStream(file);
    }

    public OutputStream openOutputStream()
        throws IOException
    {
        return new FileOutputStream(file);
    }

    public Path resolve(String s)
    {
        if (Path.uriSchemeSpecified(s))
        {
            return URLPath.valueOf(s);
        }
        File file1 = new File(s);
        if (file1.isAbsolute())
        {
            return valueOf(file1);
        }
        char c = File.separatorChar;
        if (c != '/')
        {
            s = s.replace('/', c);
        }
        File file2;
        if (this == Path.userDirPath)
        {
            file2 = new File(System.getProperty("user.dir"), s);
        } else
        {
            File file3;
            if (isDirectory())
            {
                file3 = file;
            } else
            {
                file3 = file.getParentFile();
            }
            file2 = new File(file3, s);
        }
        return valueOf(file2);
    }

    public File toFile()
    {
        return file;
    }

    public String toString()
    {
        return path;
    }

    public URL toURL()
    {
        if (this == Path.userDirPath)
        {
            return resolve("").toURL();
        }
        if (!isAbsolute())
        {
            return getAbsolute().toURL();
        }
        URL url;
        try
        {
            url = file.toURI().toURL();
        }
        catch (Throwable throwable)
        {
            throw WrappedException.wrapIfNeeded(throwable);
        }
        return url;
    }

    public URI toUri()
    {
        if (this == Path.userDirPath)
        {
            return resolve("").toURI();
        } else
        {
            return toUri(file);
        }
    }
}
