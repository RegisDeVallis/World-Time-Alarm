// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

// Referenced classes of package com.google.appinventor.components.runtime:
//            Texting

class isInitialized
{

    private final int MAX_REDIRECTS = 5;
    String authToken;
    String general;
    private boolean isInitialized;
    int redirectCounter;
    String rnrSEE;
    final Texting this$0;

    private String sendGvSms(String s)
    {
        String s1;
        Log.i("Texting Component", "sendGvSms()");
        s1 = "";
        OutputStreamWriter outputstreamwriter;
        BufferedReader bufferedreader;
        String s2 = (new StringBuilder()).append(s).append("&").append(URLEncoder.encode("_rnr_se", "UTF-8")).append("=").append(URLEncoder.encode(rnrSEE, "UTF-8")).toString();
        Log.i("Texting Component", (new StringBuilder()).append("smsData = ").append(s2).toString());
        URLConnection urlconnection = (new URL("https://www.google.com/voice/b/0/sms/send/")).openConnection();
        urlconnection.setRequestProperty("Authorization", (new StringBuilder()).append("GoogleLogin auth=").append(authToken).toString());
        urlconnection.setRequestProperty("User-agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.A.B.C Safari/525.13");
        urlconnection.setDoOutput(true);
        urlconnection.setConnectTimeout(30000);
        Log.i("Texting Component", (new StringBuilder()).append("sms request = ").append(urlconnection).toString());
        outputstreamwriter = new OutputStreamWriter(urlconnection.getOutputStream());
        outputstreamwriter.write(s2);
        outputstreamwriter.flush();
        bufferedreader = new BufferedReader(new InputStreamReader(urlconnection.getInputStream()));
_L1:
        String s3 = bufferedreader.readLine();
label0:
        {
            if (s3 == null)
            {
                break label0;
            }
            try
            {
                s1 = (new StringBuilder()).append(s1).append(s3).append("\n\r").toString();
            }
            catch (IOException ioexception)
            {
                Log.i("Texting Component", (new StringBuilder()).append("IO Error on Send ").append(ioexception.getMessage()).toString());
                ioexception.printStackTrace();
                return "IO Error Message not sent";
            }
        }
          goto _L1
        Log.i("Texting Component", (new StringBuilder()).append("sendGvSms:  Sent SMS, response = ").append(s1).toString());
        outputstreamwriter.close();
        bufferedreader.close();
        if (s1.equals(""))
        {
            throw new IOException("No Response Data Received.");
        }
        return s1;
    }

    private void setRNRSEE()
        throws IOException
    {
        Log.i("Texting Component", "setRNRSEE()");
        if (general != null)
        {
            if (general.contains("'_rnr_se': '"))
            {
                rnrSEE = general.split("'_rnr_se': '", 2)[1].split("',", 2)[0];
                Log.i("Texting Component", "Successfully Received rnr_se.");
                return;
            } else
            {
                Log.i("Texting Component", (new StringBuilder()).append("Answer did not contain rnr_se! ").append(general).toString());
                throw new IOException((new StringBuilder()).append("Answer did not contain rnr_se! ").append(general).toString());
            }
        } else
        {
            Log.i("Texting Component", "setRNRSEE(): Answer was null!");
            throw new IOException("setRNRSEE(): Answer was null!");
        }
    }

    String get(String s)
        throws IOException
    {
        HttpURLConnection httpurlconnection;
        int i;
        java.io.InputStream inputstream;
        httpurlconnection = (HttpURLConnection)(new URL(s)).openConnection();
        i = 0;
        try
        {
            httpurlconnection.setRequestProperty("Authorization", (new StringBuilder()).append("GoogleLogin auth=").append(authToken).toString());
            httpurlconnection.setRequestProperty("User-agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.A.B.C Safari/525.13");
            httpurlconnection.setInstanceFollowRedirects(false);
            httpurlconnection.connect();
            i = httpurlconnection.getResponseCode();
            Log.i("Texting Component", (new StringBuilder()).append(s).append(" - ").append(httpurlconnection.getResponseMessage()).toString());
        }
        catch (Exception exception)
        {
            throw new IOException((new StringBuilder()).append(s).append(" : ").append(httpurlconnection.getResponseMessage()).append("(").append(i).append(") : IO Error.").toString());
        }
        if (i == 200)
        {
            inputstream = httpurlconnection.getInputStream();
        } else
        {
            if (i == 301 || i == 302 || i == 303 || i == 307)
            {
                redirectCounter = 1 + redirectCounter;
                if (redirectCounter > 5)
                {
                    redirectCounter = 0;
                    throw new IOException((new StringBuilder()).append(s).append(" : ").append(httpurlconnection.getResponseMessage()).append("(").append(i).append(") : Too many redirects. exiting.").toString());
                }
                String s1 = httpurlconnection.getHeaderField("Location");
                if (s1 != null && !s1.equals(""))
                {
                    System.out.println((new StringBuilder()).append(s).append(" - ").append(i).append(" - new URL: ").append(s1).toString());
                    return get(s1);
                } else
                {
                    throw new IOException((new StringBuilder()).append(s).append(" : ").append(httpurlconnection.getResponseMessage()).append("(").append(i).append(") : Received moved answer but no Location. exiting.").toString());
                }
            }
            inputstream = httpurlconnection.getErrorStream();
        }
        redirectCounter = 0;
        if (inputstream == null)
        {
            throw new IOException((new StringBuilder()).append(s).append(" : ").append(httpurlconnection.getResponseMessage()).append("(").append(i).append(") : InputStream was null : exiting.").toString());
        }
        BufferedReader bufferedreader;
        StringBuffer stringbuffer;
        bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        stringbuffer = new StringBuffer();
_L1:
        String s2 = bufferedreader.readLine();
label0:
        {
            if (s2 == null)
            {
                break label0;
            }
            try
            {
                stringbuffer.append((new StringBuilder()).append(s2).append("\n\r").toString());
            }
            catch (Exception exception1)
            {
                throw new IOException((new StringBuilder()).append(s).append(" - ").append(httpurlconnection.getResponseMessage()).append("(").append(i).append(") - ").append(exception1.getLocalizedMessage()).toString());
            }
        }
          goto _L1
        String s3;
        bufferedreader.close();
        s3 = stringbuffer.toString();
        return s3;
    }

    public String getGeneral()
        throws IOException
    {
        Log.i("Texting Component", "getGeneral()");
        return get("https://www.google.com/voice/b/0");
    }

    public boolean isInitialized()
    {
        return isInitialized;
    }


    public (String s)
    {
        this$0 = Texting.this;
        super();
        Log.i("Texting Component", "Creating GV Util");
        authToken = s;
        try
        {
            general = getGeneral();
            Log.i("Texting Component", (new StringBuilder()).append("general = ").append(general).toString());
            setRNRSEE();
            isInitialized = true;
            return;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }
}
