// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;
import com.google.appinventor.components.runtime.util.OAuth2Helper;
import com.google.appinventor.components.runtime.util.OnInitializeListener;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.appinventor.components.runtime:
//            AndroidNonvisibleComponent, Component, OnResumeListener, OnPauseListener, 
//            OnStopListener, ComponentContainer, Form, EventDispatcher

public class Texting extends AndroidNonvisibleComponent
    implements Component, OnResumeListener, OnPauseListener, OnInitializeListener, OnStopListener
{
    class AsyncAuthenticate extends AsyncTask
    {

        final Texting this$0;

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((Void[])aobj);
        }

        protected transient String doInBackground(Void avoid[])
        {
            Log.i("Texting Component", "Authenticating");
            return (new OAuth2Helper()).getRefreshedAuthToken(Texting.activity, "grandcentral");
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((String)obj);
        }

        protected void onPostExecute(String s)
        {
            Log.i("Texting Component", (new StringBuilder()).append("authToken = ").append(s).toString());
            authToken = s;
            Toast.makeText(Texting.activity, "Finished authentication", 0).show();
            processPendingQueue();
        }

        AsyncAuthenticate()
        {
            this$0 = Texting.this;
            super();
        }
    }

    class AsyncSendMessage extends AsyncTask
    {

        final Texting this$0;

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient String doInBackground(String as[])
        {
            String s;
            String s1;
            String s2;
            s = as[0];
            s1 = as[1];
            s2 = "";
            Log.i("Texting Component", (new StringBuilder()).append("Async sending phoneNumber = ").append(s).append(" message = ").append(s1).toString());
            String s3;
            s3 = (new StringBuilder()).append(URLEncoder.encode("phoneNumber", "UTF-8")).append("=").append(URLEncoder.encode(s, "UTF-8")).append("&").append(URLEncoder.encode("text", "UTF-8")).append("=").append(URLEncoder.encode(s1, "UTF-8")).toString();
            if (gvHelper == null)
            {
                gvHelper = new GoogleVoiceUtil(authToken);
            }
            if (gvHelper.isInitialized())
            {
                s2 = gvHelper.sendGvSms(s3);
                Log.i("Texting Component", (new StringBuilder()).append("Sent SMS, response = ").append(s2).toString());
                break MISSING_BLOCK_LABEL_213;
            } else
            {
                return "IO Error: unable to create GvHelper";
            }
            Exception exception;
            exception;
            exception.printStackTrace();
            return s2;
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((String)obj);
        }

        protected void onPostExecute(String s)
        {
            int i;
            super.onPostExecute(s);
            boolean flag = false;
            int j;
            try
            {
                JSONObject jsonobject = new JSONObject(s);
                flag = jsonobject.getBoolean("ok");
                j = jsonobject.getJSONObject("data").getInt("code");
            }
            catch (JSONException jsonexception)
            {
                jsonexception.printStackTrace();
                i = 0;
                continue; /* Loop/switch isn't completed */
            }
            i = j;
_L6:
            if (!flag) goto _L2; else goto _L1
_L1:
            Toast.makeText(Texting.activity, "Message sent", 0).show();
_L4:
            return;
_L2:
            if (i == 58)
            {
                Toast.makeText(Texting.activity, "Errcode 58: SMS limit reached", 0).show();
                return;
            }
            if (!s.contains("IO Error")) goto _L4; else goto _L3
_L3:
            Toast.makeText(Texting.activity, s, 0).show();
            return;
            if (true) goto _L6; else goto _L5
_L5:
        }

        AsyncSendMessage()
        {
            this$0 = Texting.this;
            super();
        }
    }

    class GoogleVoiceUtil
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


        public GoogleVoiceUtil(String s)
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


    private static final String CACHE_FILE = "textingmsgcache";
    public static final String GV_INTENT_FILTER = "com.google.android.apps.googlevoice.SMS_RECEIVED";
    public static final String GV_PACKAGE_NAME = "com.google.android.apps.googlevoice";
    private static final String GV_SERVICE = "grandcentral";
    public static final String GV_SMS_RECEIVED = "com.google.android.apps.googlevoice.SMS_RECEIVED";
    public static final String GV_SMS_SEND_URL = "https://www.google.com/voice/b/0/sms/send/";
    public static final String GV_URL = "https://www.google.com/voice/b/0";
    private static final String MESSAGE_DELIMITER = "\001";
    public static final String MESSAGE_TAG = "com.google.android.apps.googlevoice.TEXT";
    public static final String META_DATA_SMS_KEY = "sms_handler_component";
    public static final String META_DATA_SMS_VALUE = "Texting";
    public static final String PHONE_NUMBER_TAG = "com.google.android.apps.googlevoice.PHONE_NUMBER";
    private static final String PREF_FILE = "TextingState";
    private static final String PREF_GVENABLED = "gvenabled";
    private static final String PREF_RCVENABLED = "receiving2";
    private static final String PREF_RCVENABLED_LEGACY = "receiving";
    private static final String SENT = "SMS_SENT";
    private static final int SERVER_TIMEOUT_MS = 30000;
    public static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    public static final String TAG = "Texting Component";
    public static final String TELEPHONY_INTENT_FILTER = "android.provider.Telephony.SMS_RECEIVED";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.A.B.C Safari/525.13";
    private static final String UTF8 = "UTF-8";
    private static Activity activity;
    private static Object cacheLock = new Object();
    private static Component component;
    private static boolean isRunning;
    private static int messagesCached;
    private static int receivingEnabled = 2;
    private String authToken;
    private ComponentContainer container;
    private boolean googleVoiceEnabled;
    private GoogleVoiceUtil gvHelper;
    private boolean isInitialized;
    private String message;
    private Queue pendingQueue;
    private String phoneNumber;
    private SmsManager smsManager;

    public Texting(ComponentContainer componentcontainer)
    {
        super(componentcontainer.$form());
        pendingQueue = new ConcurrentLinkedQueue();
        Log.d("Texting Component", "Texting constructor");
        container = componentcontainer;
        component = this;
        activity = componentcontainer.$context();
        SharedPreferences sharedpreferences = activity.getSharedPreferences("TextingState", 0);
        if (sharedpreferences != null)
        {
            receivingEnabled = sharedpreferences.getInt("receiving2", -1);
            if (receivingEnabled == -1)
            {
                if (sharedpreferences.getBoolean("receiving", true))
                {
                    receivingEnabled = 2;
                } else
                {
                    receivingEnabled = 1;
                }
            }
            googleVoiceEnabled = sharedpreferences.getBoolean("gvenabled", false);
            Log.i("Texting Component", (new StringBuilder()).append("Starting with receiving Enabled=").append(receivingEnabled).append(" GV enabled=").append(googleVoiceEnabled).toString());
        } else
        {
            receivingEnabled = 2;
            googleVoiceEnabled = false;
        }
        if (googleVoiceEnabled)
        {
            (new AsyncAuthenticate()).execute(new Void[0]);
        }
        smsManager = SmsManager.getDefault();
        PhoneNumber("");
        isInitialized = false;
        isRunning = false;
        componentcontainer.$form().registerForOnInitialize(this);
        componentcontainer.$form().registerForOnResume(this);
        componentcontainer.$form().registerForOnPause(this);
        componentcontainer.$form().registerForOnStop(this);
    }

    public static void MessageReceived(String s, String s1)
    {
label0:
        {
            if (receivingEnabled > 1)
            {
                Log.i("Texting Component", (new StringBuilder()).append("MessageReceived from ").append(s).append(":").append(s1).toString());
                if (!EventDispatcher.dispatchEvent(component, "MessageReceived", new Object[] {
    s, s1
}))
                {
                    break label0;
                }
                Log.i("Texting Component", "Dispatch successful");
            }
            return;
        }
        Log.i("Texting Component", "Dispatch failed, caching");
        synchronized (cacheLock)
        {
            addMessageToCache(activity, s, s1);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static void addMessageToCache(Context context, String s, String s1)
    {
        try
        {
            String s2 = (new StringBuilder()).append(s).append(":").append(s1).append("\001").toString();
            Log.i("Texting Component", (new StringBuilder()).append("Caching ").append(s2).toString());
            FileOutputStream fileoutputstream = context.openFileOutput("textingmsgcache", 32768);
            fileoutputstream.write(s2.getBytes());
            fileoutputstream.close();
            messagesCached = 1 + messagesCached;
            Log.i("Texting Component", (new StringBuilder()).append("Cached ").append(s2).toString());
            return;
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            Log.e("Texting Component", "File not found error writing to cache file");
            filenotfoundexception.printStackTrace();
            return;
        }
        catch (IOException ioexception)
        {
            Log.e("Texting Component", "I/O Error writing to cache file");
            ioexception.printStackTrace();
            return;
        }
    }

    public static int getCachedMsgCount()
    {
        return messagesCached;
    }

    public static SmsMessage[] getMessagesFromIntent(Intent intent)
    {
        Object aobj[] = (Object[])(Object[])intent.getSerializableExtra("pdus");
        byte abyte0[][] = new byte[aobj.length][];
        for (int i = 0; i < aobj.length; i++)
        {
            abyte0[i] = (byte[])(byte[])aobj[i];
        }

        byte abyte1[][] = new byte[abyte0.length][];
        int j = abyte1.length;
        SmsMessage asmsmessage[] = new SmsMessage[j];
        for (int k = 0; k < j; k++)
        {
            abyte1[k] = abyte0[k];
            asmsmessage[k] = SmsMessage.createFromPdu(abyte1[k]);
        }

        return asmsmessage;
    }

    private void handleSentMessage(Context context, BroadcastReceiver broadcastreceiver, int i, String s)
    {
        this;
        JVM INSTR monitorenter ;
        i;
        JVM INSTR tableswitch -1 4: default 40
    //                   -1 43
    //                   0 40
    //                   1 93
    //                   2 222
    //                   3 179
    //                   4 136;
           goto _L1 _L2 _L1 _L3 _L4 _L5 _L6
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Log.i("Texting Component", (new StringBuilder()).append("Received OK, msg:").append(s).toString());
        Toast.makeText(activity, "Message sent", 0).show();
          goto _L1
        Exception exception;
        exception;
        throw exception;
_L3:
        Log.e("Texting Component", (new StringBuilder()).append("Received generic failure, msg:").append(s).toString());
        Toast.makeText(activity, "Generic failure: message not sent", 0).show();
          goto _L1
_L6:
        Log.e("Texting Component", (new StringBuilder()).append("Received no service error, msg:").append(s).toString());
        Toast.makeText(activity, "No Sms service available. Message not sent.", 0).show();
          goto _L1
_L5:
        Log.e("Texting Component", (new StringBuilder()).append("Received null PDU error, msg:").append(s).toString());
        Toast.makeText(activity, "Received null PDU error. Message not sent.", 0).show();
          goto _L1
_L4:
        Log.e("Texting Component", (new StringBuilder()).append("Received radio off error, msg:").append(s).toString());
        Toast.makeText(activity, "Could not send SMS message: radio off.", 1).show();
          goto _L1
    }

    public static void handledReceivedMessage(Context context, String s, String s1)
    {
        if (isRunning)
        {
            MessageReceived(s, s1);
            return;
        }
        synchronized (cacheLock)
        {
            addMessageToCache(context, s, s1);
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static int isReceivingEnabled(Context context)
    {
label0:
        {
            SharedPreferences sharedpreferences = context.getSharedPreferences("TextingState", 0);
            int i = sharedpreferences.getInt("receiving2", -1);
            if (i == -1)
            {
                if (!sharedpreferences.getBoolean("receiving", true))
                {
                    break label0;
                }
                i = 2;
            }
            return i;
        }
        return 1;
    }

    public static boolean isRunning()
    {
        return isRunning;
    }

    private void processCachedMessages()
    {
        String as[];
        synchronized (cacheLock)
        {
            as = retrieveCachedMessages();
        }
        if (as == null)
        {
            return;
        }
        break MISSING_BLOCK_LABEL_23;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        Log.i("Texting Component", (new StringBuilder()).append("processing ").append(as.length).append(" cached messages ").toString());
        for (int i = 0; i < as.length; i++)
        {
            String s = as[i];
            Log.i("Texting Component", (new StringBuilder()).append("Message + ").append(i).append(" ").append(s).toString());
            int j = s.indexOf(":");
            if (receivingEnabled > 1 && j != -1)
            {
                MessageReceived(s.substring(0, j), s.substring(j + 1));
            }
        }

        return;
    }

    private void processPendingQueue()
    {
        String s1;
        String s2;
        for (; pendingQueue.size() != 0; (new AsyncSendMessage()).execute(new String[] {
    s1, s2
}))
        {
            String s = (String)pendingQueue.remove();
            s1 = s.substring(0, s.indexOf(":::"));
            s2 = s.substring(3 + s.indexOf(":::"));
            Log.i("Texting Component", (new StringBuilder()).append("Sending queued message ").append(s1).append(" ").append(s2).toString());
        }

    }

    private String[] retrieveCachedMessages()
    {
        Log.i("Texting Component", "Retrieving cached messages");
        FileInputStream fileinputstream;
        byte abyte0[];
        fileinputstream = activity.openFileInput("textingmsgcache");
        abyte0 = new byte[8192];
        if (fileinputstream != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        Log.e("Texting Component", "Null file stream returned from openFileInput");
        return null;
        String s;
        int i = fileinputstream.read(abyte0);
        Log.i("Texting Component", (new StringBuilder()).append("Read ").append(i).append(" bytes from ").append("textingmsgcache").toString());
        s = new String(abyte0, 0, i);
        fileinputstream.close();
        activity.deleteFile("textingmsgcache");
        messagesCached = 0;
        Log.i("Texting Component", (new StringBuilder()).append("Retrieved cache ").append(s).toString());
        return s.split("\001");
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
_L4:
        Log.e("Texting Component", "No Cache file found -- this is not (usually) an error");
        return null;
        IOException ioexception;
        ioexception;
_L2:
        Log.e("Texting Component", "I/O Error reading from cache file");
        ioexception.printStackTrace();
        return null;
        ioexception;
        if (true) goto _L2; else goto _L1
_L1:
        FileNotFoundException filenotfoundexception1;
        filenotfoundexception1;
        if (true) goto _L4; else goto _L3
_L3:
    }

    private void sendViaSms()
    {
        Log.i("Texting Component", "Sending via built-in Sms");
        ArrayList arraylist = smsManager.divideMessage(message);
        int i = arraylist.size();
        ArrayList arraylist1 = new ArrayList();
        for (int j = 0; j < i; j++)
        {
            arraylist1.add(PendingIntent.getBroadcast(activity, 0, new Intent("SMS_SENT"), 0));
        }

        BroadcastReceiver broadcastreceiver = new BroadcastReceiver() {

            final Texting this$0;

            public void onReceive(Context context, Intent intent)
            {
                this;
                JVM INSTR monitorenter ;
                handleSentMessage(context, null, getResultCode(), message);
                Texting.activity.unregisterReceiver(this);
_L2:
                this;
                JVM INSTR monitorexit ;
                return;
                Exception exception1;
                exception1;
                Log.e("BroadcastReceiver", (new StringBuilder()).append("Error in onReceive for msgId ").append(intent.getAction()).toString());
                Log.e("BroadcastReceiver", exception1.getMessage());
                exception1.printStackTrace();
                if (true) goto _L2; else goto _L1
_L1:
                Exception exception;
                exception;
                throw exception;
            }

            
            {
                this$0 = Texting.this;
                super();
            }
        };
        activity.registerReceiver(broadcastreceiver, new IntentFilter("SMS_SENT"));
        smsManager.sendMultipartTextMessage(phoneNumber, null, arraylist, arraylist1, null);
    }

    public void GoogleVoiceEnabled(boolean flag)
    {
        if (SdkLevel.getLevel() >= 5)
        {
            googleVoiceEnabled = flag;
            android.content.SharedPreferences.Editor editor = activity.getSharedPreferences("TextingState", 0).edit();
            editor.putBoolean("gvenabled", flag);
            editor.commit();
            return;
        } else
        {
            Toast.makeText(activity, "Sorry, your phone's system does not support this option.", 1).show();
            return;
        }
    }

    public boolean GoogleVoiceEnabled()
    {
        return googleVoiceEnabled;
    }

    public String Message()
    {
        return message;
    }

    public void Message(String s)
    {
        Log.i("Texting Component", (new StringBuilder()).append("Message set: ").append(s).toString());
        message = s;
    }

    public String PhoneNumber()
    {
        return phoneNumber;
    }

    public void PhoneNumber(String s)
    {
        Log.i("Texting Component", (new StringBuilder()).append("PhoneNumber set: ").append(s).toString());
        phoneNumber = s;
    }

    public int ReceivingEnabled()
    {
        return receivingEnabled;
    }

    public void ReceivingEnabled(int i)
    {
        if (i < 1 || i > 3)
        {
            Form form = container.$form();
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            form.dispatchErrorOccurredEvent(this, "Texting", 1701, aobj);
            return;
        } else
        {
            receivingEnabled = i;
            android.content.SharedPreferences.Editor editor = activity.getSharedPreferences("TextingState", 0).edit();
            editor.putInt("receiving2", i);
            editor.remove("receiving");
            editor.commit();
            return;
        }
    }

    public void SendMessage()
    {
        String s;
        String s1;
        Log.i("Texting Component", (new StringBuilder()).append("Sending message ").append(message).append(" to ").append(phoneNumber).toString());
        s = phoneNumber;
        s1 = message;
        if (!googleVoiceEnabled)
        {
            break MISSING_BLOCK_LABEL_216;
        }
        if (authToken != null)
        {
            break MISSING_BLOCK_LABEL_182;
        }
        Log.i("Texting Component", (new StringBuilder()).append("Need to get an authToken -- enqueing ").append(s).append(" ").append(s1).toString());
        if (pendingQueue.offer((new StringBuilder()).append(s).append(":::").append(s1).toString())) goto _L2; else goto _L1
_L1:
        Toast.makeText(activity, "Pending message queue full. Can't send message", 0).show();
_L4:
        return;
_L2:
        if (pendingQueue.size() != 1) goto _L4; else goto _L3
_L3:
        (new AsyncAuthenticate()).execute(new Void[0]);
        return;
        Log.i("Texting Component", "Creating AsyncSendMessage");
        (new AsyncSendMessage()).execute(new String[] {
            s, s1
        });
        return;
        Log.i("Texting Component", "Sending via SMS");
        sendViaSms();
        return;
    }

    public void onInitialize()
    {
        Log.i("Texting Component", "onInitialize()");
        isInitialized = true;
        isRunning = true;
        processCachedMessages();
        ((NotificationManager)activity.getSystemService("notification")).cancel(8647);
    }

    public void onPause()
    {
        Log.i("Texting Component", "onPause()");
        isRunning = false;
    }

    public void onResume()
    {
        Log.i("Texting Component", "onResume()");
        isRunning = true;
        if (isInitialized)
        {
            processCachedMessages();
            ((NotificationManager)activity.getSystemService("notification")).cancel(8647);
        }
    }

    public void onStop()
    {
        android.content.SharedPreferences.Editor editor = activity.getSharedPreferences("TextingState", 0).edit();
        editor.putInt("receiving2", receivingEnabled);
        editor.putBoolean("gvenabled", googleVoiceEnabled);
        editor.commit();
    }







/*
    static String access$302(Texting texting, String s)
    {
        texting.authToken = s;
        return s;
    }

*/




/*
    static GoogleVoiceUtil access$502(Texting texting, GoogleVoiceUtil googlevoiceutil)
    {
        texting.gvHelper = googlevoiceutil;
        return googlevoiceutil;
    }

*/
}
