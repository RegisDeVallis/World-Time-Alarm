// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import android.util.Log;
import gnu.expr.Language;
import gnu.mapping.Environment;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure0;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.text.FilePath;
import java.io.IOException;
import java.net.Socket;
import kawa.Shell;
import kawa.Telnet;

// Referenced classes of package com.google.appinventor.components.runtime.util:
//            BiggerFuture

public class TelnetRepl extends Procedure0
{

    private static final int REPL_STACK_SIZE = 0x40000;
    Language language;
    Socket socket;

    public TelnetRepl(Language language1, Socket socket1)
    {
        language = language1;
        socket = socket1;
    }

    public static Thread serve(Language language1, Socket socket1)
        throws IOException
    {
        Telnet telnet = new Telnet(socket1, true);
        kawa.TelnetOutputStream telnetoutputstream = telnet.getOutputStream();
        kawa.TelnetInputStream telnetinputstream = telnet.getInputStream();
        OutPort outport = new OutPort(telnetoutputstream, FilePath.valueOf("/dev/stdout"));
        TtyInPort ttyinport = new TtyInPort(telnetinputstream, FilePath.valueOf("/dev/stdin"), outport);
        BiggerFuture biggerfuture = new BiggerFuture(new TelnetRepl(language1, socket1), ttyinport, outport, outport, "Telnet Repl Thread", 0x40000L);
        biggerfuture.start();
        return biggerfuture;
    }

    public Object apply0()
    {
        Thread thread = Thread.currentThread();
        if (thread.getContextClassLoader() == null)
        {
            thread.setContextClassLoader(kawa/Telnet.getClassLoader());
        }
        Values values;
        Shell.run(language, Environment.getCurrent());
        values = Values.empty;
        Exception exception;
        IOException ioexception;
        RuntimeException runtimeexception;
        try
        {
            socket.close();
        }
        catch (IOException ioexception1)
        {
            return values;
        }
        return values;
        runtimeexception;
        Log.d("TelnetRepl", (new StringBuilder()).append("Repl is exiting with error ").append(runtimeexception.getMessage()).toString());
        runtimeexception.printStackTrace();
        throw runtimeexception;
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
