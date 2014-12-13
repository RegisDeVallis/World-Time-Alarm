// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.mapping.OutPort;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import gnu.text.Path;
import gnu.xml.XMLPrinter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class WriteTo extends Procedure2
{

    public static final WriteTo writeTo = new WriteTo();
    public static final WriteTo writeToIfChanged;
    boolean ifChanged;

    public WriteTo()
    {
    }

    public static void writeTo(Object obj, Path path, OutputStream outputstream)
        throws Throwable
    {
        OutPort outport = new OutPort(outputstream, path);
        XMLPrinter xmlprinter = new XMLPrinter(outport, false);
        if ("html".equals(path.getExtension()))
        {
            xmlprinter.setStyle("html");
        }
        Values.writeValues(obj, xmlprinter);
        outport.close();
    }

    public static void writeTo(Object obj, Object obj1)
        throws Throwable
    {
        Path path = Path.valueOf(obj1);
        writeTo(obj, path, path.openOutputStream());
    }

    public static void writeToIfChanged(Object obj, Object obj1)
        throws Throwable
    {
        Path path;
        byte abyte0[];
        path = Path.valueOf(obj1);
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        writeTo(obj, path, bytearrayoutputstream);
        abyte0 = bytearrayoutputstream.toByteArray();
        BufferedInputStream bufferedinputstream = new BufferedInputStream(path.openInputStream());
        int i = 0;
_L4:
        int j = bufferedinputstream.read();
        BufferedOutputStream bufferedoutputstream;
        boolean flag;
        if (i == abyte0.length)
        {
            flag = true;
        } else
        {
            flag = false;
        }
          goto _L1
_L3:
        int k;
        byte byte0;
        try
        {
            bufferedinputstream.close();
        }
        catch (Throwable throwable) { }
        bufferedoutputstream = new BufferedOutputStream(path.openOutputStream());
        bufferedoutputstream.write(abyte0);
        bufferedoutputstream.close();
        return;
_L8:
        bufferedinputstream.close();
        return;
_L6:
        if (flag)
        {
            break MISSING_BLOCK_LABEL_137;
        }
        k = i + 1;
        byte0 = abyte0[i];
        if (byte0 != j) goto _L3; else goto _L2
_L2:
        i = k;
          goto _L4
        i;
          goto _L3
_L1:
        if (j >= 0) goto _L6; else goto _L5
_L5:
        if (flag) goto _L8; else goto _L7
_L7:
        i;
          goto _L3
    }

    public Object apply2(Object obj, Object obj1)
        throws Throwable
    {
        if (ifChanged)
        {
            writeToIfChanged(obj, obj1.toString());
        } else
        {
            writeTo(obj, obj1.toString());
        }
        return Values.empty;
    }

    static 
    {
        writeToIfChanged = new WriteTo();
        writeToIfChanged.ifChanged = true;
    }
}
