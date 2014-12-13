// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import android.util.Log;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.ThreadPoolExecutor;

// Referenced classes of package com.google.appinventor.components.runtime.util:
//            NanoHTTPD

private class mySocket
    implements Runnable
{

    private Socket mySocket;
    final NanoHTTPD this$0;

    private void decodeHeader(BufferedReader bufferedreader, Properties properties, Properties properties1, Properties properties2)
        throws InterruptedException
    {
        String s = bufferedreader.readLine();
        if (s == null)
        {
            return;
        }
        StringTokenizer stringtokenizer;
        String s1;
        int i;
        stringtokenizer = new StringTokenizer(s);
        if (!stringtokenizer.hasMoreTokens())
        {
            sendError("400 Bad Request", "BAD REQUEST: Syntax error. Usage: GET /example/file.html");
        }
        properties.put("method", stringtokenizer.nextToken());
        if (!stringtokenizer.hasMoreTokens())
        {
            sendError("400 Bad Request", "BAD REQUEST: Missing URI. Usage: GET /example/file.html");
        }
        s1 = stringtokenizer.nextToken();
        i = s1.indexOf('?');
        if (i < 0) goto _L2; else goto _L1
_L1:
        String s2;
        decodeParms(s1.substring(i + 1), properties1);
        s2 = decodePercent(s1.substring(0, i));
_L6:
        if (!stringtokenizer.hasMoreTokens()) goto _L4; else goto _L3
_L3:
        String s3 = bufferedreader.readLine();
_L5:
        if (s3 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        int j;
        if (s3.trim().length() <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        j = s3.indexOf(':');
        if (j < 0)
        {
            break MISSING_BLOCK_LABEL_192;
        }
        properties2.put(s3.substring(0, j).trim().toLowerCase(), s3.substring(j + 1).trim());
        s3 = bufferedreader.readLine();
        if (true) goto _L5; else goto _L4
_L2:
        s2 = decodePercent(s1);
          goto _L6
_L4:
        try
        {
            properties.put("uri", s2);
            return;
        }
        catch (IOException ioexception)
        {
            sendError("500 Internal Server Error", (new StringBuilder()).append("SERVER INTERNAL ERROR: IOException: ").append(ioexception.getMessage()).toString());
        }
        return;
    }

    private void decodeMultipartData(String s, byte abyte0[], BufferedReader bufferedreader, Properties properties, Properties properties1)
        throws InterruptedException
    {
        int ai[] = getBoundaryPositions(abyte0, s.getBytes());
        int i = 1;
        String s1 = bufferedreader.readLine();
_L4:
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_278;
        }
        if (s1.indexOf(s) == -1)
        {
            sendError("400 Bad Request", "BAD REQUEST: Content type is multipart/form-data but next chunk does not start with boundary. Usage: GET /example/file.html");
        }
        i++;
        Properties properties2;
        properties2 = new Properties();
        s1 = bufferedreader.readLine();
_L2:
        if (s1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        int i1;
        if (s1.trim().length() <= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        i1 = s1.indexOf(':');
        if (i1 == -1)
        {
            break MISSING_BLOCK_LABEL_124;
        }
        properties2.put(s1.substring(0, i1).trim().toLowerCase(), s1.substring(i1 + 1).trim());
        s1 = bufferedreader.readLine();
        if (true) goto _L2; else goto _L1
_L1:
        if (s1 == null) goto _L4; else goto _L3
_L3:
        String s2 = properties2.getProperty("content-disposition");
        if (s2 != null)
        {
            break MISSING_BLOCK_LABEL_160;
        }
        sendError("400 Bad Request", "BAD REQUEST: Content type is multipart/form-data but no content-disposition info found. Usage: GET /example/file.html");
        StringTokenizer stringtokenizer;
        Properties properties3;
        stringtokenizer = new StringTokenizer(s2, "; ");
        properties3 = new Properties();
_L6:
        String s7;
        int l;
        do
        {
            if (!stringtokenizer.hasMoreTokens())
            {
                break MISSING_BLOCK_LABEL_279;
            }
            s7 = stringtokenizer.nextToken();
            l = s7.indexOf('=');
        } while (l == -1);
        properties3.put(s7.substring(0, l).trim().toLowerCase(), s7.substring(l + 1).trim());
        if (true) goto _L6; else goto _L5
_L5:
        IOException ioexception;
        ioexception;
        sendError("500 Internal Server Error", (new StringBuilder()).append("SERVER INTERNAL ERROR: IOException: ").append(ioexception.getMessage()).toString());
        return;
        String s4;
        String s3 = properties3.getProperty("name");
        s4 = s3.substring(1, -1 + s3.length());
        String s5 = "";
        if (properties2.getProperty("content-type") != null) goto _L8; else goto _L7
_L7:
        if (s1 == null) goto _L10; else goto _L9
_L9:
        if (s1.indexOf(s) != -1) goto _L10; else goto _L11
_L11:
        s1 = bufferedreader.readLine();
        if (s1 == null) goto _L7; else goto _L12
_L12:
        int j = s1.indexOf(s);
        if (j != -1)
        {
            break MISSING_BLOCK_LABEL_382;
        }
        s5 = (new StringBuilder()).append(s5).append(s1).toString();
          goto _L7
        s5 = (new StringBuilder()).append(s5).append(s1.substring(0, j - 2)).toString();
          goto _L7
_L8:
        if (i > ai.length)
        {
            sendError("500 Internal Server Error", "Error processing request");
        }
        int k = stripMultipartHeaders(abyte0, ai[i - 2]);
        properties1.put(s4, saveTmpFile(abyte0, k, -4 + (ai[i - 1] - k)));
        String s6 = properties3.getProperty("filename");
        s5 = s6.substring(1, -1 + s6.length());
_L13:
        s1 = bufferedreader.readLine();
        if (s1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (s1.indexOf(s) == -1) goto _L13; else goto _L10
_L10:
        properties.put(s4, s5);
          goto _L4
    }

    private void decodeParms(String s, Properties properties)
        throws InterruptedException
    {
        if (s != null)
        {
            StringTokenizer stringtokenizer = new StringTokenizer(s, "&");
            while (stringtokenizer.hasMoreTokens()) 
            {
                String s1 = stringtokenizer.nextToken();
                int i = s1.indexOf('=');
                if (i >= 0)
                {
                    properties.put(decodePercent(s1.substring(0, i)).trim(), decodePercent(s1.substring(i + 1)));
                }
            }
        }
    }

    private String decodePercent(String s)
        throws InterruptedException
    {
        StringBuffer stringbuffer;
        int i;
        char c;
        try
        {
            stringbuffer = new StringBuffer();
        }
        catch (Exception exception)
        {
            sendError("400 Bad Request", "BAD REQUEST: Bad percent-encoding.");
            return null;
        }
        i = 0;
_L7:
        if (i >= s.length()) goto _L2; else goto _L1
_L1:
        c = s.charAt(i);
        c;
        JVM INSTR lookupswitch 2: default 52
    //                   37: 84
    //                   43: 62;
           goto _L3 _L4 _L5
_L3:
        stringbuffer.append(c);
        break MISSING_BLOCK_LABEL_128;
_L5:
        stringbuffer.append(' ');
        break MISSING_BLOCK_LABEL_128;
_L4:
        int j;
        int k;
        j = i + 1;
        k = i + 3;
        stringbuffer.append((char)Integer.parseInt(s.substring(j, k), 16));
        i += 2;
        break MISSING_BLOCK_LABEL_128;
_L2:
        String s1 = stringbuffer.toString();
        return s1;
        i++;
        if (true) goto _L7; else goto _L6
_L6:
    }

    private String saveTmpFile(byte abyte0[], int i, int j)
    {
        String s = "";
        if (j > 0)
        {
            String s1 = System.getProperty("java.io.tmpdir");
            String s2;
            try
            {
                File file = File.createTempFile("NanoHTTPD", "", new File(s1));
                FileOutputStream fileoutputstream = new FileOutputStream(file);
                fileoutputstream.write(abyte0, i, j);
                fileoutputstream.close();
                s2 = file.getAbsolutePath();
            }
            catch (Exception exception)
            {
                NanoHTTPD.myErr.println((new StringBuilder()).append("Error: ").append(exception.getMessage()).toString());
                return s;
            }
            s = s2;
        }
        return s;
    }

    private void sendError(String s, String s1)
        throws InterruptedException
    {
        sendResponse(s, "text/plain", null, new ByteArrayInputStream(s1.getBytes()));
        throw new InterruptedException();
    }

    private void sendResponse(String s, String s1, Properties properties, InputStream inputstream)
    {
        if (s != null) goto _L2; else goto _L1
_L1:
        try
        {
            throw new Error("sendResponse(): Status can't be null.");
        }
        catch (IOException ioexception) { }
        mySocket.close();
_L8:
        return;
_L2:
        OutputStream outputstream;
        PrintWriter printwriter;
        outputstream = mySocket.getOutputStream();
        printwriter = new PrintWriter(outputstream);
        printwriter.print((new StringBuilder()).append("HTTP/1.0 ").append(s).append(" \r\n").toString());
        if (s1 == null)
        {
            break MISSING_BLOCK_LABEL_111;
        }
        printwriter.print((new StringBuilder()).append("Content-Type: ").append(s1).append("\r\n").toString());
        if (properties == null)
        {
            break MISSING_BLOCK_LABEL_125;
        }
        if (properties.getProperty("Date") != null)
        {
            break MISSING_BLOCK_LABEL_168;
        }
        printwriter.print((new StringBuilder()).append("Date: ").append(NanoHTTPD.access$300().format(new Date())).append("\r\n").toString());
        if (properties == null)
        {
            break MISSING_BLOCK_LABEL_248;
        }
        String s2;
        String s3;
        for (Enumeration enumeration = properties.keys(); enumeration.hasMoreElements(); printwriter.print((new StringBuilder()).append(s2).append(": ").append(s3).append("\r\n").toString()))
        {
            s2 = (String)enumeration.nextElement();
            s3 = properties.getProperty(s2);
        }

        printwriter.print("\r\n");
        printwriter.flush();
        if (inputstream == null) goto _L4; else goto _L3
_L3:
        int i;
        byte abyte0[];
        i = inputstream.available();
        abyte0 = new byte[NanoHTTPD.access$400()];
_L9:
        if (i <= 0) goto _L4; else goto _L5
_L5:
        int j;
        if (i <= NanoHTTPD.access$400())
        {
            break MISSING_BLOCK_LABEL_359;
        }
        j = NanoHTTPD.access$400();
_L10:
        int k = inputstream.read(abyte0, 0, j);
        if (k > 0) goto _L6; else goto _L4
_L4:
        outputstream.flush();
        outputstream.close();
        if (inputstream == null) goto _L8; else goto _L7
_L7:
        inputstream.close();
        return;
_L6:
        outputstream.write(abyte0, 0, k);
        i -= k;
          goto _L9
        Throwable throwable;
        throwable;
        return;
        j = i;
          goto _L10
    }

    private int stripMultipartHeaders(byte abyte0[], int i)
    {
        int j = i;
        do
        {
label0:
            {
                if (j < abyte0.length)
                {
                    if (abyte0[j] != 13)
                    {
                        break label0;
                    }
                    j++;
                    if (abyte0[j] != 10)
                    {
                        break label0;
                    }
                    j++;
                    if (abyte0[j] != 13)
                    {
                        break label0;
                    }
                    j++;
                    if (abyte0[j] != 10)
                    {
                        break label0;
                    }
                }
                return j + 1;
            }
            j++;
        } while (true);
    }

    public int[] getBoundaryPositions(byte abyte0[], byte abyte1[])
    {
        int i = 0;
        int j = -1;
        Vector vector = new Vector();
        int k = 0;
        while (k < abyte0.length) 
        {
            if (abyte0[k] == abyte1[i])
            {
                if (i == 0)
                {
                    j = k;
                }
                if (++i == abyte1.length)
                {
                    vector.addElement(new Integer(j));
                    i = 0;
                    j = -1;
                }
            } else
            {
                k -= i;
                j = -1;
                i = 0;
            }
            k++;
        }
        int ai[] = new int[vector.size()];
        for (int l = 0; l < ai.length; l++)
        {
            ai[l] = ((Integer)vector.elementAt(l)).intValue();
        }

        return ai;
    }

    public void run()
    {
        InputStream inputstream;
        byte abyte0[];
        int i;
        ByteArrayInputStream bytearrayinputstream;
        BufferedReader bufferedreader;
        Properties properties;
        Properties properties1;
        Properties properties2;
        Properties properties3;
        String s;
        String s1;
        long l;
        String s2;
        int j;
        int k;
        int i1;
        boolean flag;
        int j1;
        ByteArrayOutputStream bytearrayoutputstream;
        byte abyte1[];
        byte abyte2[];
        ByteArrayInputStream bytearrayinputstream1;
        BufferedReader bufferedreader1;
        String s3;
        String s4;
        StringTokenizer stringtokenizer;
        String s5;
        char ac[];
        int k1;
        mySocket mysocket;
        String s6;
        StringTokenizer stringtokenizer1;
        NumberFormatException numberformatexception;
        int l1;
        try
        {
            inputstream = mySocket.getInputStream();
        }
        catch (IOException ioexception)
        {
            try
            {
                sendError("500 Internal Server Error", (new StringBuilder()).append("SERVER INTERNAL ERROR: IOException: ").append(ioexception.getMessage()).toString());
                return;
            }
            catch (Throwable throwable)
            {
                return;
            }
        }
        catch (InterruptedException interruptedexception)
        {
            return;
        }
        if (inputstream == null)
        {
            return;
        }
        abyte0 = new byte[8192];
        i = inputstream.read(abyte0, 0, 8192);
        if (i <= 0) goto _L2; else goto _L1
_L1:
        bytearrayinputstream = new ByteArrayInputStream(abyte0, 0, i);
        bufferedreader = new BufferedReader(new InputStreamReader(bytearrayinputstream));
        properties = new Properties();
        properties1 = new Properties();
        properties2 = new Properties();
        properties3 = new Properties();
        decodeHeader(bufferedreader, properties, properties1, properties2);
        s = properties.getProperty("method");
        s1 = properties.getProperty("uri");
        l = 0x7fffffffffffffffL;
        s2 = properties2.getProperty("content-length");
        if (s2 == null)
        {
            break MISSING_BLOCK_LABEL_170;
        }
        l1 = Integer.parseInt(s2);
        l = l1;
_L22:
        j = 0;
_L14:
        k = j;
        i1 = i;
        flag = false;
        if (k >= i1) goto _L4; else goto _L3
_L3:
        if (abyte0[j] != 13) goto _L6; else goto _L5
_L5:
        j++;
        if (abyte0[j] != 10) goto _L6; else goto _L7
_L7:
        j++;
        if (abyte0[j] != 13) goto _L6; else goto _L8
_L8:
        j++;
        if (abyte0[j] != 10) goto _L6; else goto _L9
_L9:
        flag = true;
_L4:
        j1 = j + 1;
        bytearrayoutputstream = new ByteArrayOutputStream();
        if (j1 >= i) goto _L11; else goto _L10
_L10:
        bytearrayoutputstream.write(abyte0, j1, i - j1);
          goto _L11
_L15:
        abyte1 = new byte[512];
_L13:
        if (i < 0 || l <= 0L)
        {
            break; /* Loop/switch isn't completed */
        }
        i = inputstream.read(abyte1, 0, 512);
        l -= i;
        if (i <= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        bytearrayoutputstream.write(abyte1, 0, i);
        if (true) goto _L13; else goto _L12
_L6:
        j++;
          goto _L14
_L24:
        if (!flag || l == 0x7fffffffffffffffL)
        {
            l = 0L;
        }
          goto _L15
_L12:
        abyte2 = bytearrayoutputstream.toByteArray();
        bytearrayinputstream1 = new ByteArrayInputStream(abyte2);
        bufferedreader1 = new BufferedReader(new InputStreamReader(bytearrayinputstream1));
        if (!s.equalsIgnoreCase("POST")) goto _L17; else goto _L16
_L16:
        s3 = "";
        s4 = properties2.getProperty("content-type");
        stringtokenizer = new StringTokenizer(s4, "; ");
        if (stringtokenizer.hasMoreTokens())
        {
            s3 = stringtokenizer.nextToken();
        }
        if (!s3.equalsIgnoreCase("multipart/form-data")) goto _L19; else goto _L18
_L18:
        if (!stringtokenizer.hasMoreTokens())
        {
            sendError("400 Bad Request", "BAD REQUEST: Content type is multipart/form-data but boundary missing. Usage: GET /example/file.html");
        }
        s6 = stringtokenizer.nextToken();
        stringtokenizer1 = new StringTokenizer(s6, "=");
        if (stringtokenizer1.countTokens() != 2)
        {
            sendError("400 Bad Request", "BAD REQUEST: Content type is multipart/form-data but boundary syntax error. Usage: GET /example/file.html");
        }
        stringtokenizer1.nextToken();
        decodeMultipartData(stringtokenizer1.nextToken(), abyte2, bufferedreader1, properties1, properties3);
_L17:
        if (s.equalsIgnoreCase("PUT"))
        {
            properties3.put("content", saveTmpFile(abyte2, 0, bytearrayoutputstream.size()));
        }
        mysocket = serve(s1, s, properties2, properties1, properties3, mySocket);
        if (mysocket != null)
        {
            break MISSING_BLOCK_LABEL_746;
        }
        sendError("500 Internal Server Error", "SERVER INTERNAL ERROR: Serve() returned a null response.");
_L21:
        bufferedreader1.close();
        inputstream.close();
        return;
_L19:
        s5 = "";
        ac = new char[512];
        k1 = bufferedreader1.read(ac);
_L20:
        if (k1 < 0)
        {
            break MISSING_BLOCK_LABEL_732;
        }
        if (s5.endsWith("\r\n"))
        {
            break MISSING_BLOCK_LABEL_732;
        }
        s5 = (new StringBuilder()).append(s5).append(String.valueOf(ac, 0, k1)).toString();
        k1 = bufferedreader1.read(ac);
          goto _L20
        decodeParms(s5.trim(), properties1);
          goto _L17
        sendResponse(mysocket.tus, mysocket.eType, mysocket.der, mysocket.a);
          goto _L21
        numberformatexception;
          goto _L22
_L2:
        return;
_L11:
        if (j1 >= i) goto _L24; else goto _L23
_L23:
        l -= 1 + (i - j1);
          goto _L15
    }

    public (Socket socket)
    {
        this$0 = NanoHTTPD.this;
        super();
        mySocket = socket;
        Log.d("AppInvHTTPD", (new StringBuilder()).append("NanoHTTPD: getPoolSize() = ").append(NanoHTTPD.access$200(NanoHTTPD.this).getPoolSize()).toString());
        NanoHTTPD.access$200(NanoHTTPD.this).execute(this);
    }
}
