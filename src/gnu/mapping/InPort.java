// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;

import gnu.lists.Consumer;
import gnu.text.LineBufferedReader;
import gnu.text.Path;
import gnu.text.Printable;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

// Referenced classes of package gnu.mapping:
//            TtyInPort, OutPort, ThreadLocation, Environment

public class InPort extends LineBufferedReader
    implements Printable
{

    public static final ThreadLocation inLocation;
    private static InPort systemInPort;

    public InPort(InputStream inputstream)
    {
        super(inputstream);
    }

    public InPort(InputStream inputstream, Path path)
    {
        this(inputstream);
        setPath(path);
    }

    public InPort(InputStream inputstream, Path path, Object obj)
        throws UnsupportedEncodingException
    {
        this(convertToReader(inputstream, obj), path);
        if (obj == Boolean.FALSE)
        {
            try
            {
                setBuffer(new char[2048]);
                return;
            }
            catch (IOException ioexception)
            {
                return;
            }
        } else
        {
            setConvertCR(true);
            return;
        }
    }

    public InPort(Reader reader)
    {
        super(reader);
    }

    public InPort(Reader reader, Path path)
    {
        this(reader);
        setPath(path);
    }

    public static Reader convertToReader(InputStream inputstream, Object obj)
    {
        if (obj != null && obj != Boolean.TRUE)
        {
            String s;
            InputStreamReader inputstreamreader;
            if (obj == Boolean.FALSE)
            {
                s = "8859_1";
            } else
            {
                s = obj.toString();
            }
            try
            {
                inputstreamreader = new InputStreamReader(inputstream, s);
            }
            catch (UnsupportedEncodingException unsupportedencodingexception)
            {
                throw new RuntimeException((new StringBuilder()).append("unknown character encoding: ").append(s).toString());
            }
            return inputstreamreader;
        } else
        {
            return new InputStreamReader(inputstream);
        }
    }

    public static InPort inDefault()
    {
        return (InPort)inLocation.get();
    }

    public static InPort openFile(InputStream inputstream, Object obj)
        throws UnsupportedEncodingException
    {
        return new InPort(inputstream, Path.valueOf(obj), Environment.user().get("port-char-encoding"));
    }

    public static InPort openFile(Object obj)
        throws IOException
    {
        Path path = Path.valueOf(obj);
        return openFile(((InputStream) (new BufferedInputStream(path.openInputStream()))), path);
    }

    public static void setInDefault(InPort inport)
    {
        inLocation.set(inport);
    }

    public void print(Consumer consumer)
    {
        consumer.write("#<input-port");
        String s = getName();
        if (s != null)
        {
            consumer.write(32);
            consumer.write(s);
        }
        consumer.write(62);
    }

    static 
    {
        systemInPort = new TtyInPort(System.in, Path.valueOf("/dev/stdin"), OutPort.outInitial);
        inLocation = new ThreadLocation("in-default");
        inLocation.setGlobal(systemInPort);
    }
}
