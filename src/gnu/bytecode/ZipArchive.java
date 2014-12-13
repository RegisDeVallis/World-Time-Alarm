// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipArchive
{

    public ZipArchive()
    {
    }

    public static long copy(InputStream inputstream, OutputStream outputstream, byte abyte0[])
        throws IOException
    {
        long l = 0L;
        do
        {
            int i = inputstream.read(abyte0);
            if (i <= 0)
            {
                return l;
            }
            outputstream.write(abyte0, 0, i);
            l += i;
        } while (true);
    }

    public static void copy(InputStream inputstream, String s, byte abyte0[])
        throws IOException
    {
        File file = new File(s);
        String s1 = file.getParent();
        if (s1 != null)
        {
            File file1 = new File(s1);
            if (!file1.exists())
            {
                System.err.println((new StringBuilder()).append("mkdirs:").append(file1.mkdirs()).toString());
            }
        }
        if (s.charAt(-1 + s.length()) != '/')
        {
            BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(new FileOutputStream(file));
            copy(inputstream, ((OutputStream) (bufferedoutputstream)), abyte0);
            bufferedoutputstream.close();
        }
    }

    public static void main(String args[])
        throws IOException
    {
        String s;
        String s1;
        if (args.length < 2)
        {
            usage();
        }
        s = args[0];
        s1 = args[1];
        if (!s.equals("t") && !s.equals("p") && !s.equals("x")) goto _L2; else goto _L1
_L1:
        PrintStream printstream;
        byte abyte0[];
        printstream = System.out;
        abyte0 = new byte[1024];
        if (args.length != 2) goto _L4; else goto _L3
_L3:
        ZipInputStream zipinputstream;
        FileInputStream fileinputstream = new FileInputStream(s1);
        BufferedInputStream bufferedinputstream = new BufferedInputStream(fileinputstream);
        zipinputstream = new ZipInputStream(bufferedinputstream);
_L9:
        ZipEntry zipentry = zipinputstream.getNextEntry();
        if (zipentry == null) goto _L6; else goto _L5
_L5:
        String s2 = zipentry.getName();
        if (!s.equals("t")) goto _L8; else goto _L7
_L7:
        IOException ioexception;
        printstream.print(s2);
        printstream.print(" size: ");
        printstream.println(zipentry.getSize());
          goto _L9
_L6:
        return;
_L8:
label0:
        {
            if (!s.equals("p"))
            {
                break label0;
            }
            copy(zipinputstream, printstream, abyte0);
        }
          goto _L9
        copy(zipinputstream, s2, abyte0);
          goto _L9
_L4:
        ZipFile zipfile = new ZipFile(s1);
        int i = 2;
_L13:
        if (i >= args.length) goto _L6; else goto _L10
_L10:
        String s3;
        ZipEntry zipentry1;
        s3 = args[i];
        zipentry1 = zipfile.getEntry(s3);
        if (zipentry1 != null)
        {
            break MISSING_BLOCK_LABEL_299;
        }
        System.err.println((new StringBuilder()).append("zipfile ").append(s1).append(":").append(args[i]).append(" - not found").toString());
        System.exit(-1);
        break MISSING_BLOCK_LABEL_644;
        if (s.equals("t"))
        {
            printstream.print(s3);
            printstream.print(" size: ");
            printstream.println(zipentry1.getSize());
            break MISSING_BLOCK_LABEL_644;
        }
        if (s.equals("p"))
        {
            copy(zipfile.getInputStream(zipentry1), printstream, abyte0);
            break MISSING_BLOCK_LABEL_644;
        }
        copy(zipfile.getInputStream(zipentry1), s3, abyte0);
        break MISSING_BLOCK_LABEL_644;
_L2:
        ZipOutputStream zipoutputstream;
        if (!s.equals("q"))
        {
            break MISSING_BLOCK_LABEL_640;
        }
        FileOutputStream fileoutputstream = new FileOutputStream(s1);
        zipoutputstream = new ZipOutputStream(fileoutputstream);
        int j = 2;
_L12:
        if (j >= args.length)
        {
            break; /* Loop/switch isn't completed */
        }
        File file = new File(args[j]);
        if (!file.exists())
        {
            throw new IOException((new StringBuilder()).append(args[j]).append(" - not found").toString());
        }
        if (!file.canRead())
        {
            throw new IOException((new StringBuilder()).append(args[j]).append(" - not readable").toString());
        }
        int k = (int)file.length();
        FileInputStream fileinputstream1 = new FileInputStream(file);
        byte abyte1[] = new byte[k];
        if (fileinputstream1.read(abyte1) != k)
        {
            throw new IOException((new StringBuilder()).append(args[j]).append(" - read error").toString());
        }
        fileinputstream1.close();
        ZipEntry zipentry2 = new ZipEntry(args[j]);
        zipentry2.setSize(k);
        zipentry2.setTime(file.lastModified());
        zipoutputstream.putNextEntry(zipentry2);
        zipoutputstream.write(abyte1, 0, k);
        j++;
        if (true) goto _L12; else goto _L11
_L11:
        try
        {
            zipoutputstream.close();
            return;
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            System.err.println((new StringBuilder()).append("I/O Exception:  ").append(ioexception).toString());
        }
          goto _L6
        usage();
        return;
        i++;
          goto _L13
    }

    private static void usage()
    {
        System.err.println("zipfile [ptxq] archive [file ...]");
        System.exit(-1);
    }
}
