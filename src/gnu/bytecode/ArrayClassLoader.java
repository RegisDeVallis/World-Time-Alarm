// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;

// Referenced classes of package gnu.bytecode:
//            ClassType

public class ArrayClassLoader extends ClassLoader
{

    Hashtable cmap;
    URL context;
    Hashtable map;

    public ArrayClassLoader()
    {
        map = new Hashtable(100);
        cmap = new Hashtable(100);
    }

    public ArrayClassLoader(ClassLoader classloader)
    {
        super(classloader);
        map = new Hashtable(100);
        cmap = new Hashtable(100);
    }

    public ArrayClassLoader(String as[], byte abyte0[][])
    {
        map = new Hashtable(100);
        cmap = new Hashtable(100);
        for (int i = abyte0.length; --i >= 0;)
        {
            addClass(as[i], abyte0[i]);
        }

    }

    public ArrayClassLoader(byte abyte0[][])
    {
        map = new Hashtable(100);
        cmap = new Hashtable(100);
        for (int i = abyte0.length; --i >= 0;)
        {
            addClass((new StringBuilder()).append("lambda").append(i).toString(), abyte0[i]);
        }

    }

    public static Package getContextPackage(String s)
    {
        Package package1;
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        if (!(classloader instanceof ArrayClassLoader))
        {
            break MISSING_BLOCK_LABEL_26;
        }
        package1 = ((ArrayClassLoader)classloader).getPackage(s);
        return package1;
        SecurityException securityexception;
        securityexception;
        return Package.getPackage(s);
    }

    public void addClass(ClassType classtype)
    {
        map.put(classtype.getName(), classtype);
    }

    public void addClass(Class class1)
    {
        cmap.put(class1.getName(), class1);
    }

    public void addClass(String s, byte abyte0[])
    {
        map.put(s, abyte0);
    }

    protected URL findResource(String s)
    {
        if (context == null)
        {
            break MISSING_BLOCK_LABEL_30;
        }
        URL url;
        url = new URL(context, s);
        url.openConnection().connect();
        return url;
        Throwable throwable;
        throwable;
        return super.findResource(s);
    }

    public InputStream getResourceAsStream(String s)
    {
        Object obj = super.getResourceAsStream(s);
        if (obj == null && s.endsWith(".class"))
        {
            String s1 = s.substring(0, -6 + s.length()).replace('/', '.');
            Object obj1 = map.get(s1);
            if (obj1 instanceof byte[])
            {
                obj = new ByteArrayInputStream((byte[])(byte[])obj1);
            }
        }
        return ((InputStream) (obj));
    }

    public URL getResourceContext()
    {
        return context;
    }

    public Class loadClass(String s)
        throws ClassNotFoundException
    {
        Object obj1;
        Class class1;
        Object obj2;
        Object obj = cmap.get(s);
        if (obj != null)
        {
            return (Class)obj;
        }
        obj1 = map.get(s);
        if (obj1 instanceof ClassType)
        {
            ClassType classtype = (ClassType)obj1;
            byte abyte0[];
            if (classtype.isExisting())
            {
                obj1 = classtype.reflectClass;
            } else
            {
                obj1 = classtype.writeToArray();
            }
        }
        if (!(obj1 instanceof byte[]))
        {
            break MISSING_BLOCK_LABEL_146;
        }
        this;
        JVM INSTR monitorenter ;
        obj2 = map.get(s);
        if (!(obj2 instanceof byte[])) goto _L2; else goto _L1
_L1:
        abyte0 = (byte[])(byte[])obj2;
        class1 = defineClass(s, abyte0, 0, abyte0.length);
        cmap.put(s, class1);
_L3:
        this;
        JVM INSTR monitorexit ;
_L4:
        return class1;
_L2:
        class1 = (Class)obj2;
          goto _L3
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if (obj1 == null)
        {
            class1 = getParent().loadClass(s);
        } else
        {
            class1 = (Class)obj1;
        }
          goto _L4
    }

    public Class loadClass(String s, boolean flag)
        throws ClassNotFoundException
    {
        Class class1 = loadClass(s);
        if (flag)
        {
            resolveClass(class1);
        }
        return class1;
    }

    public void setResourceContext(URL url)
    {
        context = url;
    }
}
