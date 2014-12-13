// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipLoader extends ClassLoader
{

    private Vector loadedClasses;
    int size;
    ZipFile zar;
    private String zipname;

    public ZipLoader(String s)
        throws IOException
    {
        zipname = s;
        zar = new ZipFile(s);
        size = 0;
        Enumeration enumeration = zar.entries();
        do
        {
            if (!enumeration.hasMoreElements())
            {
                break;
            }
            if (!((ZipEntry)enumeration.nextElement()).isDirectory())
            {
                size = 1 + size;
            }
        } while (true);
        loadedClasses = new Vector(size);
    }

    public void close()
        throws IOException
    {
        if (zar != null)
        {
            zar.close();
        }
        zar = null;
    }

    public Class loadAllClasses()
        throws IOException
    {
        Enumeration enumeration = zar.entries();
        Class class1 = null;
        Class class2;
        for (; enumeration.hasMoreElements(); loadedClasses.addElement(class2))
        {
            ZipEntry zipentry = (ZipEntry)enumeration.nextElement();
            String s = zipentry.getName().replace('/', '.');
            String s1 = s.substring(0, s.length() - "/class".length());
            int i = (int)zipentry.getSize();
            java.io.InputStream inputstream = zar.getInputStream(zipentry);
            byte abyte0[] = new byte[i];
            (new DataInputStream(inputstream)).readFully(abyte0);
            class2 = defineClass(s1, abyte0, 0, i);
            if (class1 == null)
            {
                class1 = class2;
            }
            loadedClasses.addElement(s1);
        }

        close();
        return class1;
    }

    public Class loadClass(String s, boolean flag)
        throws ClassNotFoundException
    {
        int i = loadedClasses.indexOf(s);
        Class class1;
        if (i >= 0)
        {
            class1 = (Class)loadedClasses.elementAt(i + 1);
        } else
        if (zar == null && loadedClasses.size() == 2 * size)
        {
            class1 = Class.forName(s);
        } else
        {
            String s1 = (new StringBuilder()).append(s.replace('.', '/')).append(".class").toString();
            ZipFile zipfile = zar;
            boolean flag1 = false;
            ZipEntry zipentry;
            if (zipfile == null)
            {
                try
                {
                    zar = new ZipFile(zipname);
                }
                catch (IOException ioexception2)
                {
                    throw new ClassNotFoundException((new StringBuilder()).append("IOException while loading ").append(s1).append(" from ziparchive \"").append(s).append("\": ").append(ioexception2.toString()).toString());
                }
                flag1 = true;
            }
            zipentry = zar.getEntry(s1);
            if (zipentry == null)
            {
                if (flag1)
                {
                    try
                    {
                        close();
                    }
                    catch (IOException ioexception1)
                    {
                        throw new RuntimeException((new StringBuilder()).append("failed to close \"").append(zipname).append("\"").toString());
                    }
                }
                class1 = Class.forName(s);
            } else
            {
                try
                {
                    int j = (int)zipentry.getSize();
                    java.io.InputStream inputstream = zar.getInputStream(zipentry);
                    byte abyte0[] = new byte[j];
                    (new DataInputStream(inputstream)).readFully(abyte0);
                    class1 = defineClass(s, abyte0, 0, j);
                    loadedClasses.addElement(s);
                    loadedClasses.addElement(class1);
                    if (2 * size == loadedClasses.size())
                    {
                        close();
                    }
                }
                catch (IOException ioexception)
                {
                    throw new ClassNotFoundException((new StringBuilder()).append("IOException while loading ").append(s1).append(" from ziparchive \"").append(s).append("\": ").append(ioexception.toString()).toString());
                }
            }
        }
        if (flag)
        {
            resolveClass(class1);
        }
        return class1;
    }
}
