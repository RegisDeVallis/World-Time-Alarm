// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa;

import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.Future;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure0;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.text.FilePath;
import java.io.IOException;
import java.net.Socket;

// Referenced classes of package kawa:
//            Telnet, Shell

public class TelnetRepl extends Procedure0
{

    Language language;
    Socket socket;

    public TelnetRepl(Language language1, Socket socket1)
    {
        language = language1;
        socket = socket1;
    }

    public static void serve(Language language1, Socket socket1)
        throws IOException
    {
        Telnet telnet = new Telnet(socket1, true);
        TelnetOutputStream telnetoutputstream = telnet.getOutputStream();
        TelnetInputStream telnetinputstream = telnet.getInputStream();
        OutPort outport = new OutPort(telnetoutputstream, FilePath.valueOf("/dev/stdout"));
        TtyInPort ttyinport = new TtyInPort(telnetinputstream, FilePath.valueOf("/dev/stdin"), outport);
        (new Future(new TelnetRepl(language1, socket1), ttyinport, outport, outport)).start();
    }

    public Object apply0()
    {
        Values values;
        Shell.run(language, Environment.getCurrent());
        values = Values.empty;
        Exception exception;
        IOException ioexception;
        try
        {
            socket.close();
        }
        catch (IOException ioexception1)
        {
            return values;
        }
        return values;
        exception;
        try
        {
            socket.close();
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception) { }
        throw exception;
    }
}
