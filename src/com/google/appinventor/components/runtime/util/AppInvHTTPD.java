// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.runtime.ReplForm;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Formatter;
import java.util.Properties;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kawa.standard.Scheme;

// Referenced classes of package com.google.appinventor.components.runtime.util:
//            NanoHTTPD, PackageInstaller, RetValManager, SdkLevel, 
//            EclairUtil

public class AppInvHTTPD extends NanoHTTPD
{

    private static final String LOG_TAG = "AppInvHTTPD";
    private static final String MIME_JSON = "application/json";
    private static final int YAV_SKEW_BACKWARD = 4;
    private static final int YAV_SKEW_FORWARD = 1;
    private static byte hmacKey[];
    private static int seq;
    private final Handler androidUIHandler = new Handler();
    private ReplForm form;
    private File rootDir;
    private Language scheme;
    private boolean secure;

    public AppInvHTTPD(int i, File file, boolean flag, ReplForm replform)
        throws IOException
    {
        super(i, file);
        rootDir = file;
        scheme = Scheme.getInstance("scheme");
        form = replform;
        secure = flag;
        ModuleExp.mustNeverCompile();
    }

    private void copyFile(File file, File file1)
    {
        FileInputStream fileinputstream;
        FileOutputStream fileoutputstream;
        byte abyte0[];
        fileinputstream = new FileInputStream(file);
        fileoutputstream = new FileOutputStream(file1);
        abyte0 = new byte[32768];
_L1:
        int i = fileinputstream.read(abyte0);
label0:
        {
            if (i <= 0)
            {
                break label0;
            }
            try
            {
                fileoutputstream.write(abyte0, 0, i);
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
                return;
            }
        }
          goto _L1
        fileinputstream.close();
        fileoutputstream.close();
        return;
    }

    private void doPackageUpdate(String s)
    {
        PackageInstaller.doPackageInstall(form, s);
    }

    public static void setHmacKey(String s)
    {
        hmacKey = s.getBytes();
        seq = 1;
    }

    public void resetSeq()
    {
        seq = 1;
    }

    public NanoHTTPD.Response serve(String s, String s1, Properties properties, Properties properties1, Properties properties2, Socket socket)
    {
        Log.d("AppInvHTTPD", (new StringBuilder()).append(s1).append(" '").append(s).append("' ").toString());
        if (!secure) goto _L2; else goto _L1
_L1:
        String s26 = socket.getInetAddress().getHostAddress();
        if (s26.equals("127.0.0.1")) goto _L2; else goto _L3
_L3:
        NanoHTTPD.Response response10;
        Log.d("AppInvHTTPD", (new StringBuilder()).append("Debug: hostAddress = ").append(s26).append(" while in secure mode, closing connection.").toString());
        String s27 = (new StringBuilder()).append("{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid Source Location ").append(s26).append("\"}").toString();
        response10 = new NanoHTTPD.Response(this, "200 OK", "application/json", s27);
        response10.addHeader("Access-Control-Allow-Origin", "*");
        response10.addHeader("Access-Control-Allow-Headers", "origin, content-type");
        response10.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
        response10.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
_L16:
        return response10;
_L2:
        String s17;
        int k;
        String s18;
        String s19;
        String s20;
        if (s1.equals("OPTIONS"))
        {
            String s25;
            for (Enumeration enumeration3 = properties.propertyNames(); enumeration3.hasMoreElements(); Log.d("AppInvHTTPD", (new StringBuilder()).append("  HDR: '").append(s25).append("' = '").append(properties.getProperty(s25)).append("'").toString()))
            {
                s25 = (String)enumeration3.nextElement();
            }

            NanoHTTPD.Response response16 = new NanoHTTPD.Response(this, "200 OK", "text/plain", "OK");
            response16.addHeader("Access-Control-Allow-Origin", "*");
            response16.addHeader("Access-Control-Allow-Headers", "origin, content-type");
            response16.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
            response16.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
            return response16;
        }
        if (!s.equals("/_newblocks"))
        {
            break MISSING_BLOCK_LABEL_1151;
        }
        s17 = properties1.getProperty("seq", "0");
        k = Integer.parseInt(s17);
        s18 = properties1.getProperty("blockid");
        s19 = properties1.getProperty("code");
        s20 = properties1.getProperty("mac", "no key provided");
        if (hmacKey == null) goto _L5; else goto _L4
_L4:
        byte abyte1[];
        StringBuffer stringbuffer1;
        Formatter formatter1;
        int l;
        int i1;
        byte byte1;
        Object aobj1[];
        String s21;
        NanoHTTPD.Response response15;
        try
        {
            Mac mac1 = Mac.getInstance("HmacSHA1");
            SecretKeySpec secretkeyspec1 = new SecretKeySpec(hmacKey, "RAW");
            mac1.init(secretkeyspec1);
            abyte1 = mac1.doFinal((new StringBuilder()).append(s19).append(s17).append(s18).toString().getBytes());
            stringbuffer1 = new StringBuffer(2 * abyte1.length);
            formatter1 = new Formatter(stringbuffer1);
            l = abyte1.length;
        }
        catch (Exception exception1)
        {
            Log.e("AppInvHTTPD", "Error working with hmac", exception1);
            form.dispatchErrorOccurredEvent(form, "AppInvHTTPD", 1801, new Object[] {
                "Exception working on HMAC"
            });
            NanoHTTPD.Response response12 = new NanoHTTPD.Response(this, "200 OK", "text/plain", "NOT");
            return response12;
        }
        i1 = 0;
        if (i1 >= l)
        {
            break; /* Loop/switch isn't completed */
        }
        byte1 = abyte1[i1];
        aobj1 = new Object[1];
        aobj1[0] = Byte.valueOf(byte1);
        formatter1.format("%02x", aobj1);
        i1++;
        if (true) goto _L7; else goto _L6
_L7:
        break MISSING_BLOCK_LABEL_472;
_L6:
        s21 = stringbuffer1.toString();
        String s22;
        Log.d("AppInvHTTPD", (new StringBuilder()).append("Incoming Mac = ").append(s20).toString());
        Log.d("AppInvHTTPD", (new StringBuilder()).append("Computed Mac = ").append(s21).toString());
        Log.d("AppInvHTTPD", (new StringBuilder()).append("Incoming seq = ").append(s17).toString());
        Log.d("AppInvHTTPD", (new StringBuilder()).append("Computed seq = ").append(seq).toString());
        Log.d("AppInvHTTPD", (new StringBuilder()).append("blockid = ").append(s18).toString());
        if (!s20.equals(s21))
        {
            Log.e("AppInvHTTPD", "Hmac does not match");
            form.dispatchErrorOccurredEvent(form, "AppInvHTTPD", 1801, new Object[] {
                "Invalid HMAC"
            });
            response15 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid MAC\"}");
            return response15;
        }
        if (seq != k && seq != k + 1)
        {
            Log.e("AppInvHTTPD", "Seq does not match");
            form.dispatchErrorOccurredEvent(form, "AppInvHTTPD", 1801, new Object[] {
                "Invalid Seq"
            });
            NanoHTTPD.Response response14 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid Seq\"}");
            return response14;
        }
        if (seq == k + 1)
        {
            Log.e("AppInvHTTPD", "Seq Fixup Invoked");
        }
        seq = k + 1;
        s22 = (new StringBuilder()).append("(begin (require <com.google.youngandroid.runtime>) (process-repl-input ").append(s18).append(" (begin ").append(s19).append(" )))").toString();
        Log.d("AppInvHTTPD", (new StringBuilder()).append("To Eval: ").append(s22).toString());
        if (!s19.equals("#f")) goto _L9; else goto _L8
_L8:
        Log.e("AppInvHTTPD", "Skipping evaluation of #f");
_L10:
        NanoHTTPD.Response response13;
        String s24 = RetValManager.fetch(false);
        response13 = new NanoHTTPD.Response(this, "200 OK", "application/json", s24);
_L11:
        response13.addHeader("Access-Control-Allow-Origin", "*");
        response13.addHeader("Access-Control-Allow-Headers", "origin, content-type");
        response13.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
        response13.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
        return response13;
_L5:
        Log.e("AppInvHTTPD", "No HMAC Key");
        form.dispatchErrorOccurredEvent(form, "AppInvHTTPD", 1801, new Object[] {
            "No HMAC Key"
        });
        NanoHTTPD.Response response11 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"BAD\", \"message\" : \"Security Error: No HMAC Key\"}");
        return response11;
_L9:
        scheme.eval(s22);
          goto _L10
        Throwable throwable;
        throwable;
        Log.e("AppInvHTTPD", "newblocks: Scheme Failure", throwable);
        RetValManager.appendReturnValue(s18, "BAD", throwable.toString());
        String s23 = RetValManager.fetch(false);
        response13 = new NanoHTTPD.Response(this, "200 OK", "application/json", s23);
          goto _L11
        if (s.equals("/_values"))
        {
            NanoHTTPD.Response response = new NanoHTTPD.Response(this, "200 OK", "application/json", RetValManager.fetch(true));
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Headers", "origin, content-type");
            response.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
            response.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
            return response;
        }
        if (!s.equals("/_getversion")) goto _L13; else goto _L12
_L12:
        String s13;
        PackageInfo packageinfo;
        s13 = form.getPackageName();
        packageinfo = form.getPackageManager().getPackageInfo(s13, 0);
        if (SdkLevel.getLevel() < 5) goto _L15; else goto _L14
_L14:
        String s14 = EclairUtil.getInstallerPackageName("edu.mit.appinventor.aicompanion3", form);
_L17:
        String s15 = packageinfo.versionName;
        if (s14 == null)
        {
            s14 = "Not Known";
        }
        try
        {
            String s16 = (new StringBuilder()).append("{\"version\" : \"").append(s15).append("\", \"fingerprint\" : \"").append(Build.FINGERPRINT).append("\",").append(" \"installer\" : \"").append(s14).append("\", \"package\" : \"").append(s13).append("\" }").toString();
            response10 = new NanoHTTPD.Response(this, "200 OK", "application/json", s16);
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            namenotfoundexception.printStackTrace();
            response10 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"verison\" : \"Unknown\"");
        }
        response10.addHeader("Access-Control-Allow-Origin", "*");
        response10.addHeader("Access-Control-Allow-Headers", "origin, content-type");
        response10.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
        response10.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
        if (secure)
        {
            seq = 1;
            Handler handler = androidUIHandler;
            Runnable runnable = new Runnable() {

                final AppInvHTTPD this$0;

                public void run()
                {
                    form.clear();
                }

            
            {
                this$0 = AppInvHTTPD.this;
                super();
            }
            };
            handler.post(runnable);
            return response10;
        }
          goto _L16
_L15:
        s14 = "Not Known";
          goto _L17
_L13:
        String s2;
        if (!s.equals("/_update") && !s.equals("/_install"))
        {
            break MISSING_BLOCK_LABEL_2049;
        }
        s2 = properties1.getProperty("url", "");
        String s3 = properties1.getProperty("mac", "");
        if (s2.equals("") || hmacKey == null || s3.equals(""))
        {
            break MISSING_BLOCK_LABEL_1993;
        }
        byte abyte0[];
        StringBuffer stringbuffer;
        Formatter formatter;
        int i;
        int j;
        byte byte0;
        Object aobj[];
        String s4;
        NanoHTTPD.Response response4;
        try
        {
            SecretKeySpec secretkeyspec = new SecretKeySpec(hmacKey, "RAW");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretkeyspec);
            abyte0 = mac.doFinal(s2.getBytes());
            stringbuffer = new StringBuffer(2 * abyte0.length);
            formatter = new Formatter(stringbuffer);
            i = abyte0.length;
        }
        catch (Exception exception)
        {
            Log.e("AppInvHTTPD", "Error verifying update", exception);
            form.dispatchErrorOccurredEvent(form, "AppInvHTTPD", 1801, new Object[] {
                "Exception working on HMAC for update"
            });
            NanoHTTPD.Response response2 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"BAD\", \"message\" : \"Security Error: Exception processing MAC\"}");
            response2.addHeader("Access-Control-Allow-Origin", "*");
            response2.addHeader("Access-Control-Allow-Headers", "origin, content-type");
            response2.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
            response2.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
            return response2;
        }
        j = 0;
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        byte0 = abyte0[j];
        aobj = new Object[1];
        aobj[0] = Byte.valueOf(byte0);
        formatter.format("%02x", aobj);
        j++;
        if (true) goto _L19; else goto _L18
_L19:
        break MISSING_BLOCK_LABEL_1628;
_L18:
        s4 = stringbuffer.toString();
        Log.d("AppInvHTTPD", (new StringBuilder()).append("Incoming Mac (update) = ").append(s3).toString());
        Log.d("AppInvHTTPD", (new StringBuilder()).append("Computed Mac (update) = ").append(s4).toString());
        if (!s3.equals(s4))
        {
            Log.e("AppInvHTTPD", "Hmac does not match");
            form.dispatchErrorOccurredEvent(form, "AppInvHTTPD", 1801, new Object[] {
                "Invalid HMAC (update)"
            });
            response4 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"BAD\", \"message\" : \"Security Error: Invalid MAC\"}");
            response4.addHeader("Access-Control-Allow-Origin", "*");
            response4.addHeader("Access-Control-Allow-Headers", "origin, content-type");
            response4.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
            response4.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
            return response4;
        } else
        {
            doPackageUpdate(s2);
            NanoHTTPD.Response response3 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"OK\", \"message\" : \"Update Should Happen\"}");
            response3.addHeader("Access-Control-Allow-Origin", "*");
            response3.addHeader("Access-Control-Allow-Headers", "origin, content-type");
            response3.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
            response3.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
            return response3;
        }
        NanoHTTPD.Response response1 = new NanoHTTPD.Response(this, "200 OK", "application/json", "{\"status\" : \"BAD\", \"message\" : \"Missing Parameters\"}");
        response1.addHeader("Access-Control-Allow-Origin", "*");
        response1.addHeader("Access-Control-Allow-Headers", "origin, content-type");
        response1.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
        response1.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
        return response1;
        Enumeration enumeration2;
        if (s.equals("/_package"))
        {
            String s12 = properties1.getProperty("package", null);
            if (s12 == null)
            {
                NanoHTTPD.Response response8 = new NanoHTTPD.Response(this, "200 OK", "text/plain", "NOT OK");
                return response8;
            } else
            {
                Log.d("AppInvHTTPD", (new StringBuilder()).append(rootDir).append("/").append(s12).toString());
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setDataAndType(Uri.fromFile(new File((new StringBuilder()).append(rootDir).append("/").append(s12).toString())), "application/vnd.android.package-archive");
                form.startActivity(intent);
                NanoHTTPD.Response response9 = new NanoHTTPD.Response(this, "200 OK", "text/plain", "OK");
                return response9;
            }
        }
        if (s1.equals("PUT"))
        {
            Boolean boolean1 = Boolean.valueOf(false);
            String s10 = properties2.getProperty("content", null);
            if (s10 != null)
            {
                File file2 = new File(s10);
                String s11 = properties1.getProperty("filename", null);
                if (s11 != null && (s11.startsWith("..") || s11.endsWith("..") || s11.indexOf("../") >= 0))
                {
                    Log.d("AppInvHTTPD", (new StringBuilder()).append(" Ignoring invalid filename: ").append(s11).toString());
                    s11 = null;
                }
                NanoHTTPD.Response response6;
                if (s11 != null)
                {
                    File file3 = new File((new StringBuilder()).append(rootDir).append("/").append(s11).toString());
                    if (!file2.renameTo(file3))
                    {
                        copyFile(file2, file3);
                        file2.delete();
                    }
                } else
                {
                    file2.delete();
                    Log.e("AppInvHTTPD", "Received content without a file name!");
                    boolean1 = Boolean.valueOf(true);
                }
            } else
            {
                Log.e("AppInvHTTPD", "Received PUT without content.");
                boolean1 = Boolean.valueOf(true);
            }
            if (boolean1.booleanValue())
            {
                response6 = new NanoHTTPD.Response(this, "200 OK", "text/plain", "NOTOK");
                response6.addHeader("Access-Control-Allow-Origin", "*");
                response6.addHeader("Access-Control-Allow-Headers", "origin, content-type");
                response6.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
                response6.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
                return response6;
            } else
            {
                NanoHTTPD.Response response7 = new NanoHTTPD.Response(this, "200 OK", "text/plain", "OK");
                response7.addHeader("Access-Control-Allow-Origin", "*");
                response7.addHeader("Access-Control-Allow-Headers", "origin, content-type");
                response7.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
                response7.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
                return response7;
            }
        }
        String s9;
        for (Enumeration enumeration = properties.propertyNames(); enumeration.hasMoreElements(); Log.d("AppInvHTTPD", (new StringBuilder()).append("  HDR: '").append(s9).append("' = '").append(properties.getProperty(s9)).append("'").toString()))
        {
            s9 = (String)enumeration.nextElement();
        }

        String s8;
        for (Enumeration enumeration1 = properties1.propertyNames(); enumeration1.hasMoreElements(); Log.d("AppInvHTTPD", (new StringBuilder()).append("  PRM: '").append(s8).append("' = '").append(properties1.getProperty(s8)).append("'").toString()))
        {
            s8 = (String)enumeration1.nextElement();
        }

        enumeration2 = properties2.propertyNames();
        if (!enumeration2.hasMoreElements()) goto _L21; else goto _L20
_L20:
        String s6;
        String s7;
        File file;
        String s5 = (String)enumeration2.nextElement();
        s6 = properties2.getProperty(s5);
        s7 = properties1.getProperty(s5);
        if (s7.startsWith("..") || s7.endsWith("..") || s7.indexOf("../") >= 0)
        {
            Log.d("AppInvHTTPD", (new StringBuilder()).append(" Ignoring invalid filename: ").append(s7).toString());
            s7 = null;
        }
        file = new File(s6);
        if (s7 != null) goto _L23; else goto _L22
_L22:
        file.delete();
_L24:
        Log.d("AppInvHTTPD", (new StringBuilder()).append(" UPLOADED: '").append(s7).append("' was at '").append(s6).append("'").toString());
        NanoHTTPD.Response response5 = new NanoHTTPD.Response(this, "200 OK", "text/plain", "OK");
        response5.addHeader("Access-Control-Allow-Origin", "*");
        response5.addHeader("Access-Control-Allow-Headers", "origin, content-type");
        response5.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
        response5.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
        return response5;
_L23:
        File file1 = new File((new StringBuilder()).append(rootDir).append("/").append(s7).toString());
        if (!file.renameTo(file1))
        {
            copyFile(file, file1);
            file.delete();
        }
        if (true) goto _L24; else goto _L21
_L21:
        return serveFile(s, properties, rootDir, true);
          goto _L17
    }

}
