// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import android.util.Log;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.Vector;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class NanoHTTPD
{
    private class HTTPSession
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
        //                       37: 84
        //                       43: 62;
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
            printwriter.print((new StringBuilder()).append("Date: ").append(NanoHTTPD.gmtFrmt.format(new Date())).append("\r\n").toString());
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
            abyte0 = new byte[NanoHTTPD.theBufferSize];
_L9:
            if (i <= 0) goto _L4; else goto _L5
_L5:
            int j;
            if (i <= NanoHTTPD.theBufferSize)
            {
                break MISSING_BLOCK_LABEL_359;
            }
            j = NanoHTTPD.theBufferSize;
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
            Response response;
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
            response = serve(s1, s, properties2, properties1, properties3, mySocket);
            if (response != null)
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
            sendResponse(response.status, response.mimeType, response.header, response.data);
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

        public HTTPSession(Socket socket)
        {
            this$0 = NanoHTTPD.this;
            super();
            mySocket = socket;
            Log.d("AppInvHTTPD", (new StringBuilder()).append("NanoHTTPD: getPoolSize() = ").append(myExecutor.getPoolSize()).toString());
            myExecutor.execute(this);
        }
    }

    public class Response
    {

        public InputStream data;
        public Properties header;
        public String mimeType;
        public String status;
        final NanoHTTPD this$0;

        public void addHeader(String s, String s1)
        {
            header.put(s, s1);
        }

        public Response()
        {
            this$0 = NanoHTTPD.this;
            super();
            header = new Properties();
            status = "200 OK";
        }

        public Response(String s, String s1, InputStream inputstream)
        {
            this$0 = NanoHTTPD.this;
            super();
            header = new Properties();
            status = s;
            mimeType = s1;
            data = inputstream;
        }

        public Response(String s, String s1, String s2)
        {
            this$0 = NanoHTTPD.this;
            super();
            header = new Properties();
            status = s;
            mimeType = s1;
            try
            {
                data = new ByteArrayInputStream(s2.getBytes("UTF-8"));
                return;
            }
            catch (UnsupportedEncodingException unsupportedencodingexception)
            {
                unsupportedencodingexception.printStackTrace();
            }
        }
    }

    private class myThreadFactory
        implements ThreadFactory
    {

        final NanoHTTPD this$0;

        public Thread newThread(Runnable runnable)
        {
            Thread thread = new Thread(new ThreadGroup("biggerstack"), runnable, "HTTPD Session", 0x40000L);
            thread.setDaemon(true);
            return thread;
        }

        private myThreadFactory()
        {
            this$0 = NanoHTTPD.this;
            super();
        }

    }


    public static final String HTTP_BADREQUEST = "400 Bad Request";
    public static final String HTTP_FORBIDDEN = "403 Forbidden";
    public static final String HTTP_INTERNALERROR = "500 Internal Server Error";
    public static final String HTTP_NOTFOUND = "404 Not Found";
    public static final String HTTP_NOTIMPLEMENTED = "501 Not Implemented";
    public static final String HTTP_NOTMODIFIED = "304 Not Modified";
    public static final String HTTP_OK = "200 OK";
    public static final String HTTP_PARTIALCONTENT = "206 Partial Content";
    public static final String HTTP_RANGE_NOT_SATISFIABLE = "416 Requested Range Not Satisfiable";
    public static final String HTTP_REDIRECT = "301 Moved Permanently";
    private static final String LICENCE = "Copyright (C) 2001,2005-2011 by Jarno Elonen <elonen@iki.fi>\nand Copyright (C) 2010 by Konstantinos Togias <info@ktogias.gr>\n\nRedistribution and use in source and binary forms, with or without\nmodification, are permitted provided that the following conditions\nare met:\n\nRedistributions of source code must retain the above copyright notice,\nthis list of conditions and the following disclaimer. Redistributions in\nbinary form must reproduce the above copyright notice, this list of\nconditions and the following disclaimer in the documentation and/or other\nmaterials provided with the distribution. The name of the author may not\nbe used to endorse or promote products derived from this software without\nspecific prior written permission. \n \nTHIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR\nIMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES\nOF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.\nIN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,\nINCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT\nNOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\nDATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\nTHEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\nOF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.";
    private static final String LOG_TAG = "AppInvHTTPD";
    public static final String MIME_DEFAULT_BINARY = "application/octet-stream";
    public static final String MIME_HTML = "text/html";
    public static final String MIME_PLAINTEXT = "text/plain";
    public static final String MIME_XML = "text/xml";
    private static final int REPL_STACK_SIZE = 0x40000;
    private static SimpleDateFormat gmtFrmt;
    protected static PrintStream myErr;
    protected static PrintStream myOut;
    private static int theBufferSize = 16384;
    private static Hashtable theMimeTypes;
    private ThreadPoolExecutor myExecutor;
    private File myRootDir;
    private final ServerSocket myServerSocket;
    private int myTcpPort;
    private Thread myThread;

    public NanoHTTPD(int i, File file)
        throws IOException
    {
        myExecutor = new ThreadPoolExecutor(2, 10, 5L, TimeUnit.SECONDS, new SynchronousQueue(), new myThreadFactory());
        myTcpPort = i;
        myRootDir = file;
        myServerSocket = new ServerSocket(myTcpPort);
        myThread = new Thread(new Runnable() {

            final NanoHTTPD this$0;

            public void run()
            {
                do
                {
                    try
                    {
                        new HTTPSession(myServerSocket.accept());
                    }
                    catch (IOException ioexception)
                    {
                        return;
                    }
                } while (true);
            }

            
            {
                this$0 = NanoHTTPD.this;
                super();
            }
        });
        myThread.setDaemon(true);
        myThread.start();
    }

    private String encodeUri(String s)
    {
        String s1 = "";
        for (StringTokenizer stringtokenizer = new StringTokenizer(s, "/ ", true); stringtokenizer.hasMoreTokens();)
        {
            String s2 = stringtokenizer.nextToken();
            if (s2.equals("/"))
            {
                s1 = (new StringBuilder()).append(s1).append("/").toString();
            } else
            if (s2.equals(" "))
            {
                s1 = (new StringBuilder()).append(s1).append("%20").toString();
            } else
            {
                s1 = (new StringBuilder()).append(s1).append(URLEncoder.encode(s2)).toString();
            }
        }

        return s1;
    }

    public static void main(String args[])
    {
        int i;
        File file;
        int j;
        myOut.println("NanoHTTPD 1.25 (C) 2001,2005-2011 Jarno Elonen and (C) 2010 Konstantinos Togias\n(Command line options: [-p port] [-d root-dir] [--licence])\n");
        i = 80;
        file = (new File(".")).getAbsoluteFile();
        j = 0;
_L1:
        if (j >= args.length)
        {
            break MISSING_BLOCK_LABEL_115;
        }
        if (args[j].equalsIgnoreCase("-p"))
        {
            i = Integer.parseInt(args[j + 1]);
        } else
        {
            if (!args[j].equalsIgnoreCase("-d"))
            {
                continue; /* Loop/switch isn't completed */
            }
            file = (new File(args[j + 1])).getAbsoluteFile();
        }
_L3:
        j++;
          goto _L1
        if (!args[j].toLowerCase().endsWith("licence")) goto _L3; else goto _L2
_L2:
        myOut.println("Copyright (C) 2001,2005-2011 by Jarno Elonen <elonen@iki.fi>\nand Copyright (C) 2010 by Konstantinos Togias <info@ktogias.gr>\n\nRedistribution and use in source and binary forms, with or without\nmodification, are permitted provided that the following conditions\nare met:\n\nRedistributions of source code must retain the above copyright notice,\nthis list of conditions and the following disclaimer. Redistributions in\nbinary form must reproduce the above copyright notice, this list of\nconditions and the following disclaimer in the documentation and/or other\nmaterials provided with the distribution. The name of the author may not\nbe used to endorse or promote products derived from this software without\nspecific prior written permission. \n \nTHIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR\nIMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES\nOF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.\nIN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,\nINCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT\nNOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,\nDATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY\nTHEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT\n(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\nOF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\n");
        try
        {
            new NanoHTTPD(i, file);
        }
        catch (IOException ioexception)
        {
            myErr.println((new StringBuilder()).append("Couldn't start server:\n").append(ioexception).toString());
            System.exit(-1);
        }
        myOut.println((new StringBuilder()).append("Now serving files in port ").append(i).append(" from \"").append(file).append("\"").toString());
        myOut.println("Hit Enter to stop.\n");
        try
        {
            System.in.read();
            return;
        }
        catch (Throwable throwable)
        {
            return;
        }
    }

    public Response serve(String s, String s1, Properties properties, Properties properties1, Properties properties2, Socket socket)
    {
        myOut.println((new StringBuilder()).append(s1).append(" '").append(s).append("' ").toString());
        String s4;
        for (Enumeration enumeration = properties.propertyNames(); enumeration.hasMoreElements(); myOut.println((new StringBuilder()).append("  HDR: '").append(s4).append("' = '").append(properties.getProperty(s4)).append("'").toString()))
        {
            s4 = (String)enumeration.nextElement();
        }

        String s3;
        for (Enumeration enumeration1 = properties1.propertyNames(); enumeration1.hasMoreElements(); myOut.println((new StringBuilder()).append("  PRM: '").append(s3).append("' = '").append(properties1.getProperty(s3)).append("'").toString()))
        {
            s3 = (String)enumeration1.nextElement();
        }

        String s2;
        for (Enumeration enumeration2 = properties2.propertyNames(); enumeration2.hasMoreElements(); myOut.println((new StringBuilder()).append("  UPLOADED: '").append(s2).append("' = '").append(properties2.getProperty(s2)).append("'").toString()))
        {
            s2 = (String)enumeration2.nextElement();
        }

        return serveFile(s, properties, myRootDir, true);
    }

    public Response serveFile(String s, Properties properties, File file, boolean flag)
    {
        Response response;
        final File final_file;
        boolean flag1 = file.isDirectory();
        response = null;
        if (!flag1)
        {
            response = new Response("500 Internal Server Error", "text/plain", "INTERNAL ERRROR: serveFile(): given homeDir is not a directory.");
        }
        if (response == null)
        {
            s = s.trim().replace(File.separatorChar, '/');
            if (s.indexOf('?') >= 0)
            {
                int i3 = s.indexOf('?');
                s = s.substring(0, i3);
            }
            if (s.startsWith("..") || s.endsWith("..") || s.indexOf("../") >= 0)
            {
                response = new Response("403 Forbidden", "text/plain", "FORBIDDEN: Won't serve ../ for security reasons.");
            }
        }
        final_file = new File(file, s);
        if (response == null && !final_file.exists())
        {
            response = new Response("404 Not Found", "text/plain", "Error 404, file not found.");
        }
        if (response != null || !final_file.isDirectory()) goto _L2; else goto _L1
_L1:
        if (!s.endsWith("/"))
        {
            s = (new StringBuilder()).append(s).append("/").toString();
            String s14 = (new StringBuilder()).append("<html><body>Redirected: <a href=\"").append(s).append("\">").append(s).append("</a></body></html>").toString();
            response = new Response("301 Moved Permanently", "text/html", s14);
            response.addHeader("Location", s);
        }
        if (response != null) goto _L2; else goto _L3
_L3:
        Response response1;
        Response response2;
        String s1;
        File file1 = new File(final_file, "index.html");
        int i;
        String s3;
        String s5;
        int j;
        int k;
        int i1;
        long l5;
        if (file1.exists())
        {
            final_file = new File(file, (new StringBuilder()).append(s).append("/index.html").toString());
            response1 = response;
        } else
        {
            File file2 = new File(final_file, "index.htm");
            if (file2.exists())
            {
                final_file = new File(file, (new StringBuilder()).append(s).append("/index.htm").toString());
                response1 = response;
            } else
            if (flag && final_file.canRead())
            {
                String as[] = final_file.list();
                String s8 = (new StringBuilder()).append("<html><body><h1>Directory ").append(s).append("</h1><br/>").toString();
                if (s.length() > 1)
                {
                    int i2 = -1 + s.length();
                    String s13 = s.substring(0, i2);
                    int j2 = s13.lastIndexOf('/');
                    if (j2 >= 0 && j2 < s13.length())
                    {
                        StringBuilder stringbuilder = (new StringBuilder()).append(s8).append("<b><a href=\"");
                        int k2 = j2 + 1;
                        s8 = stringbuilder.append(s.substring(0, k2)).append("\">..</a></b><br/>").toString();
                    }
                }
                if (as != null)
                {
                    int j1 = 0;
                    do
                    {
                        int k1 = as.length;
                        if (j1 >= k1)
                        {
                            break;
                        }
                        File file3 = new File(final_file, as[j1]);
                        boolean flag2 = file3.isDirectory();
                        if (flag2)
                        {
                            s8 = (new StringBuilder()).append(s8).append("<b>").toString();
                            as[j1] = (new StringBuilder()).append(as[j1]).append("/").toString();
                        }
                        String s10 = (new StringBuilder()).append(s8).append("<a href=\"").append(encodeUri((new StringBuilder()).append(s).append(as[j1]).toString())).append("\">").append(as[j1]).append("</a>").toString();
                        if (file3.isFile())
                        {
                            long l6 = file3.length();
                            String s11 = (new StringBuilder()).append(s10).append(" &nbsp;<font size=2>(").toString();
                            String s12;
                            if (l6 < 1024L)
                            {
                                s12 = (new StringBuilder()).append(s11).append(l6).append(" bytes").toString();
                            } else
                            if (l6 < 0x100000L)
                            {
                                s12 = (new StringBuilder()).append(s11).append(l6 / 1024L).append(".").append(((l6 % 1024L) / 10L) % 100L).append(" KB").toString();
                            } else
                            {
                                s12 = (new StringBuilder()).append(s11).append(l6 / 0x100000L).append(".").append(((l6 % 0x100000L) / 10L) % 100L).append(" MB").toString();
                            }
                            s10 = (new StringBuilder()).append(s12).append(")</font>").toString();
                        }
                        s8 = (new StringBuilder()).append(s10).append("<br/>").toString();
                        if (flag2)
                        {
                            s8 = (new StringBuilder()).append(s8).append("</b>").toString();
                        }
                        j1++;
                    } while (true);
                }
                String s9 = (new StringBuilder()).append(s8).append("</body></html>").toString();
                Response response4 = new Response("200 OK", "text/html", s9);
                response1 = response4;
            } else
            {
                Response response3 = new Response("403 Forbidden", "text/plain", "FORBIDDEN: No directory listing.");
                response1 = response3;
            }
        }
_L13:
        if (response1 != null) goto _L5; else goto _L4
_L4:
        i = final_file.getCanonicalPath().lastIndexOf('.');
        s1 = null;
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_1682;
        }
        String s2;
        long l;
        long l1;
        long l2;
        FileInputStream fileinputstream;
        String s4;
        IOException ioexception1;
        long l3;
        long l4;
        FileInputStream fileinputstream1;
        String s6;
        String s7;
        try
        {
            s1 = (String)theMimeTypes.get(final_file.getCanonicalPath().substring(i + 1).toLowerCase());
            break MISSING_BLOCK_LABEL_1682;
        }
        catch (IOException ioexception)
        {
            response1;
        }
          goto _L6
_L14:
        s2 = Integer.toHexString((new StringBuilder()).append(final_file.getAbsolutePath()).append(final_file.lastModified()).append("").append(final_file.length()).toString().hashCode());
        l = 0L;
        l1 = -1L;
        s3 = properties.getProperty("range");
        if (s3 == null)
        {
            break MISSING_BLOCK_LABEL_528;
        }
        if (!s3.startsWith("bytes="))
        {
            break MISSING_BLOCK_LABEL_528;
        }
        j = "bytes=".length();
        s3 = s3.substring(j);
        k = s3.indexOf('-');
        if (k <= 0)
        {
            break MISSING_BLOCK_LABEL_528;
        }
        l = Long.parseLong(s3.substring(0, k));
        i1 = k + 1;
        l5 = Long.parseLong(s3.substring(i1));
        l1 = l5;
_L12:
        l2 = final_file.length();
        if (s3 == null || l < 0L) goto _L8; else goto _L7
_L7:
        if (l < l2) goto _L10; else goto _L9
_L9:
        response2 = new Response("416 Requested Range Not Satisfiable", "text/plain", "");
        s5 = (new StringBuilder()).append("bytes 0-0/").append(l2).toString();
        response2.addHeader("Content-Range", s5);
        response2.addHeader("ETag", s2);
_L11:
        response2.addHeader("Accept-Ranges", "bytes");
        return response2;
_L10:
        if (l1 < 0L)
        {
            l1 = l2 - 1L;
        }
        l3 = 1L + (l1 - l);
        if (l3 < 0L)
        {
            l3 = 0L;
        }
        l4 = l3;
        fileinputstream1 = new FileInputStream(l4) {

            final NanoHTTPD this$0;
            final long val$dataLen;

            public int available()
                throws IOException
            {
                return (int)dataLen;
            }

            
            {
                this$0 = NanoHTTPD.this;
                dataLen = l;
                super(final_file);
            }
        };
        fileinputstream1.skip(l);
        response2 = new Response("206 Partial Content", s1, fileinputstream1);
        s6 = (new StringBuilder()).append("").append(l4).toString();
        response2.addHeader("Content-Length", s6);
        s7 = (new StringBuilder()).append("bytes ").append(l).append("-").append(l1).append("/").append(l2).toString();
        response2.addHeader("Content-Range", s7);
        response2.addHeader("ETag", s2);
          goto _L11
        ioexception1;
_L6:
        response2 = new Response("403 Forbidden", "text/plain", "FORBIDDEN: Reading file failed.");
          goto _L11
_L8:
label0:
        {
            if (!s2.equals(properties.getProperty("if-none-match")))
            {
                break label0;
            }
            response2 = new Response("304 Not Modified", s1, "");
        }
          goto _L11
        fileinputstream = new FileInputStream(final_file);
        response2 = new Response("200 OK", s1, fileinputstream);
        s4 = (new StringBuilder()).append("").append(l2).toString();
        response2.addHeader("Content-Length", s4);
        response2.addHeader("ETag", s2);
          goto _L11
        NumberFormatException numberformatexception;
        numberformatexception;
          goto _L12
_L5:
        response2 = response1;
          goto _L11
_L2:
        response1 = response;
          goto _L13
        if (s1 == null)
        {
            s1 = "application/octet-stream";
        }
          goto _L14
    }

    public void stop()
    {
        try
        {
            myServerSocket.close();
            myThread.join();
            return;
        }
        catch (IOException ioexception)
        {
            return;
        }
        catch (InterruptedException interruptedexception)
        {
            return;
        }
    }

    static 
    {
        theMimeTypes = new Hashtable();
        for (StringTokenizer stringtokenizer = new StringTokenizer("css            text/css htm            text/html html           text/html xml            text/xml txt            text/plain asc            text/plain gif            image/gif jpg            image/jpeg jpeg           image/jpeg png            image/png mp3            audio/mpeg m3u            audio/mpeg-url mp4            video/mp4 ogv            video/ogg flv            video/x-flv mov            video/quicktime swf            application/x-shockwave-flash js                     application/javascript pdf            application/pdf doc            application/msword ogg            application/x-ogg zip            application/octet-stream exe            application/octet-stream class          application/octet-stream "); stringtokenizer.hasMoreTokens(); theMimeTypes.put(stringtokenizer.nextToken(), stringtokenizer.nextToken())) { }
        myOut = System.out;
        myErr = System.err;
        gmtFrmt = new SimpleDateFormat("E, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        gmtFrmt.setTimeZone(TimeZone.getTimeZone("GMT"));
    }




}
