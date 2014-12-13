// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

// Referenced classes of package gnu.bytecode:
//            ClassFileInput, ClassType, ClassTypeWriter, ObjectType, 
//            AttrContainer, Attribute, ConstantPool

public class dump extends ClassFileInput
{

    ClassTypeWriter writer;

    dump(InputStream inputstream, ClassTypeWriter classtypewriter)
        throws IOException, ClassFormatError
    {
        super(inputstream);
        ctype = new ClassType();
        readFormatVersion();
        readConstants();
        readClassInfo();
        readFields();
        readMethods();
        readAttributes(ctype);
        classtypewriter.print(ctype);
        classtypewriter.flush();
    }

    public static void main(String args[])
    {
        int i;
        ClassTypeWriter classtypewriter;
        int j;
        i = args.length;
        classtypewriter = new ClassTypeWriter(null, System.out, 0);
        if (i == 0)
        {
            usage(System.err);
        }
        j = 0;
_L2:
        String s;
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_731;
        }
        s = args[j];
        if (!s.equals("-verbose") && !s.equals("--verbose"))
        {
            break; /* Loop/switch isn't completed */
        }
        classtypewriter.setFlags(15);
_L10:
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        if (!uriSchemeSpecified(s)) goto _L4; else goto _L3
_L3:
        boolean flag = s.startsWith("jar:");
        if (!flag) goto _L6; else goto _L5
_L5:
        String s3;
        int i1;
        s3 = s.substring(4);
        if (uriSchemeSpecified(s3))
        {
            break MISSING_BLOCK_LABEL_188;
        }
        i1 = s3.indexOf('!');
        if (i1 < 0)
        {
            break MISSING_BLOCK_LABEL_188;
        }
        String s8 = s3.substring(0, i1);
        File file = new File(s8);
        String s9 = file.toURI().toURL().toString();
        s = (new StringBuilder()).append("jar:").append(s9).append(s3.substring(i1)).toString();
        if (s3.indexOf("!/") >= 0) goto _L6; else goto _L7
_L7:
        int l = s.lastIndexOf('!');
        if (l > 0) goto _L9; else goto _L8
_L8:
        flag = false;
_L6:
        URL url1 = new URL(s);
        InputStream inputstream = url1.openConnection().getInputStream();
        Object obj = inputstream;
_L12:
        try
        {
            process(((InputStream) (obj)), s, classtypewriter);
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            System.err.println("caught ");
            System.err.print(ioexception);
            System.exit(-1);
        }
          goto _L10
_L9:
        if (s.indexOf('/', l) >= 0) goto _L6; else goto _L11
_L11:
        String s7;
        String s6 = s.substring(l + 1).replace('.', '/');
        s7 = (new StringBuilder()).append(s.substring(0, l + 1)).append('/').append(s6).append(".class").toString();
        s = s7;
          goto _L6
        ZipException zipexception;
        zipexception;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_420;
        }
        String s4;
        int k;
        s4 = url1.getFile();
        k = s4.lastIndexOf('!');
        if (k <= 0)
        {
            break MISSING_BLOCK_LABEL_400;
        }
        String s5 = s4.substring(0, k);
        s4 = s5;
        URL url2 = new URL(s4);
        url2.openConnection().getInputStream();
_L13:
        try
        {
            throw zipexception;
        }
        catch (FileNotFoundException filenotfoundexception1) { }
        System.err.print("File for URL ");
        System.err.print(s);
        System.err.println(" not found.");
        System.exit(-1);
        obj = null;
          goto _L12
        FileNotFoundException filenotfoundexception2;
        filenotfoundexception2;
        System.err.print("Jar File for URL ");
        System.err.print(s4);
        System.err.println(" not found.");
        System.exit(-1);
          goto _L13
        ZipException zipexception1;
        zipexception1;
        System.err.print("Error opening zip archive ");
        System.err.print(s);
        System.err.println(" not found.");
        zipexception1.printStackTrace();
        if (zipexception1.getCause() != null)
        {
            zipexception1.getCause().printStackTrace();
        }
        System.exit(-1);
        obj = null;
          goto _L12
_L4:
        obj = new FileInputStream(s);
          goto _L12
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        ClassLoader classloader1 = ObjectType.getContextClass(s).getClassLoader();
        ClassLoader classloader = classloader1;
_L14:
        String s1 = (new StringBuilder()).append(s.replace('.', '/')).append(".class").toString();
        String s2;
        URL url = classloader.getResource(s1);
        obj = url.openConnection().getInputStream();
        s2 = url.toString();
        s = s2;
          goto _L12
        NoClassDefFoundError noclassdeffounderror;
        noclassdeffounderror;
        classloader = ObjectType.getContextClassLoader();
          goto _L14
        Throwable throwable;
        throwable;
        System.err.print("File ");
        System.err.print(s);
        System.err.println(" not found.");
        System.exit(-1);
        classloader = null;
          goto _L14
        Throwable throwable1;
        throwable1;
        System.err.print("Can't find .class file for class ");
        System.err.print(s);
        System.err.print(" - ");
        System.err.println(throwable1);
        System.exit(-1);
        obj = null;
          goto _L12
          goto _L10
    }

    public static void process(InputStream inputstream, String s, ClassTypeWriter classtypewriter)
        throws IOException
    {
        BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream);
        bufferedinputstream.mark(5);
        int i = readMagic(bufferedinputstream);
        if (i == 0xcafebabe)
        {
            classtypewriter.print("Reading .class from ");
            classtypewriter.print(s);
            classtypewriter.println('.');
            new dump(bufferedinputstream, classtypewriter);
            return;
        }
        if (i == 0x504b0304)
        {
            bufferedinputstream.reset();
            classtypewriter.print("Reading classes from archive ");
            classtypewriter.print(s);
            classtypewriter.println('.');
            ZipInputStream zipinputstream = new ZipInputStream(bufferedinputstream);
            do
            {
                ZipEntry zipentry = zipinputstream.getNextEntry();
                if (zipentry != null)
                {
                    String s1 = zipentry.getName();
                    if (zipentry.isDirectory())
                    {
                        classtypewriter.print("Archive directory: ");
                        classtypewriter.print(s1);
                        classtypewriter.println('.');
                    } else
                    {
                        classtypewriter.println();
                        if (readMagic(zipinputstream) == 0xcafebabe)
                        {
                            classtypewriter.print("Reading class member: ");
                            classtypewriter.print(s1);
                            classtypewriter.println('.');
                            new dump(zipinputstream, classtypewriter);
                        } else
                        {
                            classtypewriter.print("Skipping non-class member: ");
                            classtypewriter.print(s1);
                            classtypewriter.println('.');
                        }
                    }
                } else
                {
                    System.exit(-1);
                    return;
                }
            } while (true);
        } else
        {
            System.err.println((new StringBuilder()).append("File ").append(s).append(" is not a valid .class file").toString());
            System.exit(-1);
            return;
        }
    }

    public static void process(InputStream inputstream, String s, OutputStream outputstream, int i)
        throws IOException
    {
        process(inputstream, s, new ClassTypeWriter(null, outputstream, i));
    }

    public static void process(InputStream inputstream, String s, Writer writer1, int i)
        throws IOException
    {
        process(inputstream, s, new ClassTypeWriter(null, writer1, i));
    }

    static int readMagic(InputStream inputstream)
        throws IOException
    {
        int i = 0;
        int j = 0;
        do
        {
            int k;
label0:
            {
                if (j < 4)
                {
                    k = inputstream.read();
                    if (k >= 0)
                    {
                        break label0;
                    }
                }
                return i;
            }
            i = i << 8 | k & 0xff;
            j++;
        } while (true);
    }

    static int uriSchemeLength(String s)
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

    static boolean uriSchemeSpecified(String s)
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

    public static void usage(PrintStream printstream)
    {
        printstream.println("Prints and dis-assembles the contents of JVM .class files.");
        printstream.println("Usage: [--verbose] class-or-jar ...");
        printstream.println("where a class-or-jar can be one of:");
        printstream.println("- a fully-qualified class name; or");
        printstream.println("- the name of a .class file, or a URL reference to one; or");
        printstream.println("- the name of a .jar or .zip archive file, or a URL reference to one.");
        printstream.println("If a .jar/.zip archive is named, all its.class file members are printed.");
        printstream.println();
        printstream.println("You can name a single .class member of an archive with a jar: URL,");
        printstream.println("which looks like: jar:jar-spec!/p1/p2/cl.class");
        printstream.println("The jar-spec can be a URL or the name of the .jar file.");
        printstream.println("You can also use the shorthand syntax: jar:jar-spec!p1.p2.cl");
        System.exit(-1);
    }

    public Attribute readAttribute(String s, int i, AttrContainer attrcontainer)
        throws IOException
    {
        return super.readAttribute(s, i, attrcontainer);
    }

    public ConstantPool readConstants()
        throws IOException
    {
        ctype.constants = super.readConstants();
        return ctype.constants;
    }
}
