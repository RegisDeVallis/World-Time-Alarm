// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import com.google.appinventor.components.common.HtmlEntities;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.GingerbreadUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

// Referenced classes of package com.google.appinventor.components.runtime:
//            AndroidNonvisibleComponent, Component, ComponentContainer, Form, 
//            EventDispatcher

public class Web extends AndroidNonvisibleComponent
    implements Component
{
    static class BuildRequestDataException extends Exception
    {

        final int errorNumber;
        final int index;

        BuildRequestDataException(int i, int j)
        {
            errorNumber = i;
            index = j;
        }
    }

    private static class CapturedProperties
    {

        final boolean allowCookies;
        final Map cookies;
        final Map requestHeaders;
        final String responseFileName;
        final boolean saveResponse;
        final URL url;
        final String urlString;

        CapturedProperties(Web web)
            throws MalformedURLException, InvalidRequestHeadersException
        {
            Map map;
            urlString = web.urlString;
            url = new URL(urlString);
            allowCookies = web.allowCookies;
            saveResponse = web.saveResponse;
            responseFileName = web.responseFileName;
            requestHeaders = Web.processRequestHeaders(web.requestHeaders);
            boolean flag = allowCookies;
            map = null;
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_109;
            }
            CookieHandler cookiehandler = web.cookieHandler;
            map = null;
            if (cookiehandler == null)
            {
                break MISSING_BLOCK_LABEL_109;
            }
            Map map1 = web.cookieHandler.get(url.toURI(), requestHeaders);
            map = map1;
_L2:
            cookies = map;
            return;
            IOException ioexception;
            ioexception;
            map = null;
            continue; /* Loop/switch isn't completed */
            URISyntaxException urisyntaxexception;
            urisyntaxexception;
            map = null;
            if (true) goto _L2; else goto _L1
_L1:
        }
    }

    private static class InvalidRequestHeadersException extends Exception
    {

        final int errorNumber;
        final int index;

        InvalidRequestHeadersException(int i, int j)
        {
            errorNumber = i;
            index = j;
        }
    }


    private static final String LOG_TAG = "Web";
    private static final Map mimeTypeToExtension;
    private final Activity activity;
    private boolean allowCookies;
    private final CookieHandler cookieHandler;
    private YailList requestHeaders;
    private String responseFileName;
    private boolean saveResponse;
    private String urlString;

    protected Web()
    {
        super(null);
        urlString = "";
        requestHeaders = new YailList();
        responseFileName = "";
        activity = null;
        cookieHandler = null;
    }

    public Web(ComponentContainer componentcontainer)
    {
        super(componentcontainer.$form());
        urlString = "";
        requestHeaders = new YailList();
        responseFileName = "";
        activity = componentcontainer.$context();
        CookieHandler cookiehandler;
        if (SdkLevel.getLevel() >= 9)
        {
            cookiehandler = GingerbreadUtil.newCookieManager();
        } else
        {
            cookiehandler = null;
        }
        cookieHandler = cookiehandler;
    }

    private CapturedProperties capturePropertyValues(String s)
    {
        CapturedProperties capturedproperties = new CapturedProperties(this);
        return capturedproperties;
        MalformedURLException malformedurlexception;
        malformedurlexception;
        Form form1 = this.form;
        Object aobj1[] = new Object[1];
        aobj1[0] = urlString;
        form1.dispatchErrorOccurredEvent(this, s, 1109, aobj1);
_L2:
        return null;
        InvalidRequestHeadersException invalidrequestheadersexception;
        invalidrequestheadersexception;
        Form form = this.form;
        int i = invalidrequestheadersexception.errorNumber;
        Object aobj[] = new Object[1];
        aobj[0] = Integer.valueOf(invalidrequestheadersexception.index);
        form.dispatchErrorOccurredEvent(this, s, i, aobj);
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static File createFile(String s, String s1)
        throws IOException, com.google.appinventor.components.runtime.util.FileUtil.FileException
    {
        if (!TextUtils.isEmpty(s))
        {
            return FileUtil.getExternalFile(s);
        }
        int i = s1.indexOf(';');
        if (i != -1)
        {
            s1 = s1.substring(0, i);
        }
        String s2 = (String)mimeTypeToExtension.get(s1);
        if (s2 == null)
        {
            s2 = "tmp";
        }
        return FileUtil.getDownloadFile(s2);
    }

    static Object decodeJsonText(String s)
        throws IllegalArgumentException
    {
        Object obj;
        try
        {
            obj = JsonUtil.getObjectFromJson(s);
        }
        catch (JSONException jsonexception)
        {
            throw new IllegalArgumentException("jsonText is not a legal JSON value");
        }
        return obj;
    }

    private static InputStream getConnectionStream(HttpURLConnection httpurlconnection)
    {
        InputStream inputstream;
        try
        {
            inputstream = httpurlconnection.getInputStream();
        }
        catch (IOException ioexception)
        {
            return httpurlconnection.getErrorStream();
        }
        return inputstream;
    }

    private static String getResponseContent(HttpURLConnection httpurlconnection)
        throws IOException
    {
        InputStreamReader inputstreamreader;
        String s = httpurlconnection.getContentEncoding();
        if (s == null)
        {
            s = "UTF-8";
        }
        inputstreamreader = new InputStreamReader(getConnectionStream(httpurlconnection), s);
        int i = httpurlconnection.getContentLength();
        if (i == -1)
        {
            break MISSING_BLOCK_LABEL_91;
        }
        StringBuilder stringbuilder = new StringBuilder(i);
_L2:
        char ac[] = new char[1024];
_L1:
        int j = inputstreamreader.read(ac);
        if (j == -1)
        {
            break MISSING_BLOCK_LABEL_103;
        }
        stringbuilder.append(ac, 0, j);
          goto _L1
        Exception exception;
        exception;
        inputstreamreader.close();
        throw exception;
        stringbuilder = new StringBuilder();
          goto _L2
        String s1 = stringbuilder.toString();
        inputstreamreader.close();
        return s1;
    }

    private static String getResponseType(HttpURLConnection httpurlconnection)
    {
        String s = httpurlconnection.getContentType();
        if (s != null)
        {
            return s;
        } else
        {
            return "";
        }
    }

    private static HttpURLConnection openConnection(CapturedProperties capturedproperties, String s)
        throws IOException, ClassCastException, ProtocolException
    {
        HttpURLConnection httpurlconnection = (HttpURLConnection)capturedproperties.url.openConnection();
        if (s.equals("PUT") || s.equals("DELETE"))
        {
            httpurlconnection.setRequestMethod(s);
        }
        for (Iterator iterator = capturedproperties.requestHeaders.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator.next();
            String s2 = (String)entry1.getKey();
            Iterator iterator3 = ((List)entry1.getValue()).iterator();
            while (iterator3.hasNext()) 
            {
                httpurlconnection.addRequestProperty(s2, (String)iterator3.next());
            }
        }

        if (capturedproperties.cookies != null)
        {
            for (Iterator iterator1 = capturedproperties.cookies.entrySet().iterator(); iterator1.hasNext();)
            {
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
                String s1 = (String)entry.getKey();
                Iterator iterator2 = ((List)entry.getValue()).iterator();
                while (iterator2.hasNext()) 
                {
                    httpurlconnection.addRequestProperty(s1, (String)iterator2.next());
                }
            }

        }
        return httpurlconnection;
    }

    private void performRequest(final CapturedProperties webProps, byte abyte0[], String s, String s1)
        throws IOException
    {
        HttpURLConnection httpurlconnection = openConnection(webProps, s1);
        if (httpurlconnection == null) goto _L2; else goto _L1
_L1:
        if (abyte0 == null) goto _L4; else goto _L3
_L3:
        writeRequestData(httpurlconnection, abyte0);
_L6:
        final int responseCode;
        final String responseType;
        responseCode = httpurlconnection.getResponseCode();
        responseType = getResponseType(httpurlconnection);
        processResponseCookies(httpurlconnection);
        if (!saveResponse)
        {
            break MISSING_BLOCK_LABEL_115;
        }
        final String path = saveResponseContent(httpurlconnection, webProps.responseFileName, responseType);
        activity.runOnUiThread(new Runnable() {

            final Web this$0;
            final String val$path;
            final int val$responseCode;
            final String val$responseType;
            final CapturedProperties val$webProps;

            public void run()
            {
                GotFile(webProps.urlString, responseCode, responseType, path);
            }

            
            {
                this$0 = Web.this;
                webProps = capturedproperties;
                responseCode = i;
                responseType = s;
                path = s1;
                super();
            }
        });
_L7:
        httpurlconnection.disconnect();
_L2:
        return;
_L4:
        if (s == null) goto _L6; else goto _L5
_L5:
        writeRequestFile(httpurlconnection, s);
          goto _L6
        Exception exception;
        exception;
        httpurlconnection.disconnect();
        throw exception;
        final String responseContent = getResponseContent(httpurlconnection);
        activity.runOnUiThread(new Runnable() {

            final Web this$0;
            final int val$responseCode;
            final String val$responseContent;
            final String val$responseType;
            final CapturedProperties val$webProps;

            public void run()
            {
                GotText(webProps.urlString, responseCode, responseType, responseContent);
            }

            
            {
                this$0 = Web.this;
                webProps = capturedproperties;
                responseCode = i;
                responseType = s;
                responseContent = s1;
                super();
            }
        });
          goto _L7
    }

    private static Map processRequestHeaders(YailList yaillist)
        throws InvalidRequestHeadersException
    {
        java.util.HashMap hashmap = Maps.newHashMap();
        for (int i = 0; i < yaillist.size();)
        {
            Object obj = yaillist.getObject(i);
            if (obj instanceof YailList)
            {
                YailList yaillist1 = (YailList)obj;
                if (yaillist1.size() == 2)
                {
                    String s = yaillist1.getObject(0).toString();
                    Object obj1 = yaillist1.getObject(1);
                    java.util.ArrayList arraylist = Lists.newArrayList();
                    if (obj1 instanceof YailList)
                    {
                        YailList yaillist2 = (YailList)obj1;
                        for (int j = 0; j < yaillist2.size(); j++)
                        {
                            arraylist.add(yaillist2.getObject(j).toString());
                        }

                    } else
                    {
                        arraylist.add(obj1.toString());
                    }
                    hashmap.put(s, arraylist);
                    i++;
                } else
                {
                    throw new InvalidRequestHeadersException(1111, i + 1);
                }
            } else
            {
                throw new InvalidRequestHeadersException(1110, i + 1);
            }
        }

        return hashmap;
    }

    private void processResponseCookies(HttpURLConnection httpurlconnection)
    {
        if (!allowCookies || cookieHandler == null)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        Map map = httpurlconnection.getHeaderFields();
        cookieHandler.put(httpurlconnection.getURL().toURI(), map);
        return;
        IOException ioexception;
        ioexception;
        return;
        URISyntaxException urisyntaxexception;
        urisyntaxexception;
    }

    private void requestTextImpl(final String text, final String encoding, final String functionName, final String httpVerb)
    {
        final CapturedProperties webProps = capturePropertyValues(functionName);
        if (webProps == null)
        {
            return;
        } else
        {
            AsynchUtil.runAsynchronously(new Runnable() {

                final Web this$0;
                final String val$encoding;
                final String val$functionName;
                final String val$httpVerb;
                final String val$text;
                final CapturedProperties val$webProps;

                public void run()
                {
                    if (encoding != null && encoding.length() != 0) goto _L2; else goto _L1
_L1:
                    byte abyte0[] = text.getBytes("UTF-8");
                    byte abyte1[] = abyte0;
_L4:
                    byte abyte2[];
                    try
                    {
                        performRequest(webProps, abyte1, null, httpVerb);
                        return;
                    }
                    catch (com.google.appinventor.components.runtime.util.FileUtil.FileException fileexception)
                    {
                        Web.this.form.dispatchErrorOccurredEvent(Web.this, functionName, fileexception.getErrorMessageNumber(), new Object[0]);
                        return;
                    }
                    catch (Exception exception)
                    {
                        Form form1 = Web.this.form;
                        Web web1 = Web.this;
                        String s1 = functionName;
                        Object aobj1[] = new Object[2];
                        aobj1[0] = text;
                        aobj1[1] = webProps.urlString;
                        form1.dispatchErrorOccurredEvent(web1, s1, 1103, aobj1);
                        return;
                    }
_L2:
                    try
                    {
                        abyte2 = text.getBytes(encoding);
                    }
                    catch (UnsupportedEncodingException unsupportedencodingexception)
                    {
                        Form form = Web.this.form;
                        Web web = Web.this;
                        String s = functionName;
                        Object aobj[] = new Object[1];
                        aobj[0] = encoding;
                        form.dispatchErrorOccurredEvent(web, s, 1102, aobj);
                        return;
                    }
                    abyte1 = abyte2;
                    if (true) goto _L4; else goto _L3
_L3:
                }

            
            {
                this$0 = Web.this;
                encoding = s;
                text = s1;
                functionName = s2;
                webProps = capturedproperties;
                httpVerb = s3;
                super();
            }
            });
            return;
        }
    }

    private static String saveResponseContent(HttpURLConnection httpurlconnection, String s, String s1)
        throws IOException
    {
        File file;
        BufferedInputStream bufferedinputstream;
        file = createFile(s, s1);
        bufferedinputstream = new BufferedInputStream(getConnectionStream(httpurlconnection), 4096);
        BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(new FileOutputStream(file), 4096);
_L1:
        int i = bufferedinputstream.read();
        if (i != -1)
        {
            break MISSING_BLOCK_LABEL_75;
        }
        bufferedoutputstream.flush();
        bufferedoutputstream.close();
        bufferedinputstream.close();
        return file.getAbsolutePath();
        bufferedoutputstream.write(i);
          goto _L1
        Exception exception;
        exception;
        bufferedoutputstream.close();
        throw exception;
        Exception exception1;
        exception1;
        bufferedinputstream.close();
        throw exception1;
    }

    private static void writeRequestData(HttpURLConnection httpurlconnection, byte abyte0[])
        throws IOException
    {
        BufferedOutputStream bufferedoutputstream;
        httpurlconnection.setDoOutput(true);
        httpurlconnection.setFixedLengthStreamingMode(abyte0.length);
        bufferedoutputstream = new BufferedOutputStream(httpurlconnection.getOutputStream());
        bufferedoutputstream.write(abyte0, 0, abyte0.length);
        bufferedoutputstream.flush();
        bufferedoutputstream.close();
        return;
        Exception exception;
        exception;
        bufferedoutputstream.close();
        throw exception;
    }

    private void writeRequestFile(HttpURLConnection httpurlconnection, String s)
        throws IOException
    {
        BufferedInputStream bufferedinputstream = new BufferedInputStream(MediaUtil.openMedia(form, s));
        BufferedOutputStream bufferedoutputstream;
        httpurlconnection.setDoOutput(true);
        httpurlconnection.setChunkedStreamingMode(0);
        bufferedoutputstream = new BufferedOutputStream(httpurlconnection.getOutputStream());
_L1:
        int i = bufferedinputstream.read();
        if (i != -1)
        {
            break MISSING_BLOCK_LABEL_66;
        }
        bufferedoutputstream.flush();
        bufferedoutputstream.close();
        bufferedinputstream.close();
        return;
        bufferedoutputstream.write(i);
          goto _L1
        Exception exception1;
        exception1;
        bufferedoutputstream.close();
        throw exception1;
        Exception exception;
        exception;
        bufferedinputstream.close();
        throw exception;
    }

    public void AllowCookies(boolean flag)
    {
        allowCookies = flag;
        if (flag && cookieHandler == null)
        {
            form.dispatchErrorOccurredEvent(this, "AllowCookies", 4, new Object[0]);
        }
    }

    public boolean AllowCookies()
    {
        return allowCookies;
    }

    public String BuildRequestData(YailList yaillist)
    {
        String s;
        try
        {
            s = buildRequestData(yaillist);
        }
        catch (BuildRequestDataException buildrequestdataexception)
        {
            Form form = this.form;
            int i = buildrequestdataexception.errorNumber;
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(buildrequestdataexception.index);
            form.dispatchErrorOccurredEvent(this, "BuildRequestData", i, aobj);
            return "";
        }
        return s;
    }

    public void ClearCookies()
    {
        if (cookieHandler != null)
        {
            GingerbreadUtil.clearCookies(cookieHandler);
            return;
        } else
        {
            form.dispatchErrorOccurredEvent(this, "ClearCookies", 4, new Object[0]);
            return;
        }
    }

    public void Delete()
    {
        final CapturedProperties webProps = capturePropertyValues("Delete");
        if (webProps == null)
        {
            return;
        } else
        {
            AsynchUtil.runAsynchronously(new Runnable() {

                final Web this$0;
                final CapturedProperties val$webProps;

                public void run()
                {
                    try
                    {
                        performRequest(webProps, null, null, "DELETE");
                        return;
                    }
                    catch (com.google.appinventor.components.runtime.util.FileUtil.FileException fileexception)
                    {
                        Web.this.form.dispatchErrorOccurredEvent(Web.this, "Delete", fileexception.getErrorMessageNumber(), new Object[0]);
                        return;
                    }
                    catch (Exception exception)
                    {
                        Form form = Web.this.form;
                        Web web = Web.this;
                        Object aobj[] = new Object[1];
                        aobj[0] = webProps.urlString;
                        form.dispatchErrorOccurredEvent(web, "Delete", 1114, aobj);
                        return;
                    }
                }

            
            {
                this$0 = Web.this;
                webProps = capturedproperties;
                super();
            }
            });
            return;
        }
    }

    public void Get()
    {
        final CapturedProperties webProps = capturePropertyValues("Get");
        if (webProps == null)
        {
            return;
        } else
        {
            AsynchUtil.runAsynchronously(new Runnable() {

                final Web this$0;
                final CapturedProperties val$webProps;

                public void run()
                {
                    try
                    {
                        performRequest(webProps, null, null, "GET");
                        return;
                    }
                    catch (com.google.appinventor.components.runtime.util.FileUtil.FileException fileexception)
                    {
                        Web.this.form.dispatchErrorOccurredEvent(Web.this, "Get", fileexception.getErrorMessageNumber(), new Object[0]);
                        return;
                    }
                    catch (Exception exception)
                    {
                        Form form = Web.this.form;
                        Web web = Web.this;
                        Object aobj[] = new Object[1];
                        aobj[0] = webProps.urlString;
                        form.dispatchErrorOccurredEvent(web, "Get", 1101, aobj);
                        return;
                    }
                }

            
            {
                this$0 = Web.this;
                webProps = capturedproperties;
                super();
            }
            });
            return;
        }
    }

    public void GotFile(String s, int i, String s1, String s2)
    {
        Object aobj[] = new Object[4];
        aobj[0] = s;
        aobj[1] = Integer.valueOf(i);
        aobj[2] = s1;
        aobj[3] = s2;
        EventDispatcher.dispatchEvent(this, "GotFile", aobj);
    }

    public void GotText(String s, int i, String s1, String s2)
    {
        Object aobj[] = new Object[4];
        aobj[0] = s;
        aobj[1] = Integer.valueOf(i);
        aobj[2] = s1;
        aobj[3] = s2;
        EventDispatcher.dispatchEvent(this, "GotText", aobj);
    }

    public String HtmlTextDecode(String s)
    {
        String s1;
        try
        {
            s1 = HtmlEntities.decodeHtmlText(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            form.dispatchErrorOccurredEvent(this, "HtmlTextDecode", 1106, new Object[] {
                s
            });
            return "";
        }
        return s1;
    }

    public Object JsonTextDecode(String s)
    {
        Object obj;
        try
        {
            obj = decodeJsonText(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            form.dispatchErrorOccurredEvent(this, "JsonTextDecode", 1105, new Object[] {
                s
            });
            return "";
        }
        return obj;
    }

    public void PostFile(final String path)
    {
        final CapturedProperties webProps = capturePropertyValues("PostFile");
        if (webProps == null)
        {
            return;
        } else
        {
            AsynchUtil.runAsynchronously(new Runnable() {

                final Web this$0;
                final String val$path;
                final CapturedProperties val$webProps;

                public void run()
                {
                    try
                    {
                        performRequest(webProps, null, path, "POST");
                        return;
                    }
                    catch (com.google.appinventor.components.runtime.util.FileUtil.FileException fileexception)
                    {
                        Web.this.form.dispatchErrorOccurredEvent(Web.this, "PostFile", fileexception.getErrorMessageNumber(), new Object[0]);
                        return;
                    }
                    catch (Exception exception)
                    {
                        Form form = Web.this.form;
                        Web web = Web.this;
                        Object aobj[] = new Object[2];
                        aobj[0] = path;
                        aobj[1] = webProps.urlString;
                        form.dispatchErrorOccurredEvent(web, "PostFile", 1104, aobj);
                        return;
                    }
                }

            
            {
                this$0 = Web.this;
                webProps = capturedproperties;
                path = s;
                super();
            }
            });
            return;
        }
    }

    public void PostText(String s)
    {
        requestTextImpl(s, "UTF-8", "PostText", "POST");
    }

    public void PostTextWithEncoding(String s, String s1)
    {
        requestTextImpl(s, s1, "PostTextWithEncoding", "POST");
    }

    public void PutFile(final String path)
    {
        final CapturedProperties webProps = capturePropertyValues("PutFile");
        if (webProps == null)
        {
            return;
        } else
        {
            AsynchUtil.runAsynchronously(new Runnable() {

                final Web this$0;
                final String val$path;
                final CapturedProperties val$webProps;

                public void run()
                {
                    try
                    {
                        performRequest(webProps, null, path, "PUT");
                        return;
                    }
                    catch (com.google.appinventor.components.runtime.util.FileUtil.FileException fileexception)
                    {
                        Web.this.form.dispatchErrorOccurredEvent(Web.this, "PutFile", fileexception.getErrorMessageNumber(), new Object[0]);
                        return;
                    }
                    catch (Exception exception)
                    {
                        Form form = Web.this.form;
                        Web web = Web.this;
                        Object aobj[] = new Object[2];
                        aobj[0] = path;
                        aobj[1] = webProps.urlString;
                        form.dispatchErrorOccurredEvent(web, "PutFile", 1104, aobj);
                        return;
                    }
                }

            
            {
                this$0 = Web.this;
                webProps = capturedproperties;
                path = s;
                super();
            }
            });
            return;
        }
    }

    public void PutText(String s)
    {
        requestTextImpl(s, "UTF-8", "PutText", "PUT");
    }

    public void PutTextWithEncoding(String s, String s1)
    {
        requestTextImpl(s, s1, "PutTextWithEncoding", "PUT");
    }

    public YailList RequestHeaders()
    {
        return requestHeaders;
    }

    public void RequestHeaders(YailList yaillist)
    {
        try
        {
            processRequestHeaders(yaillist);
            requestHeaders = yaillist;
            return;
        }
        catch (InvalidRequestHeadersException invalidrequestheadersexception)
        {
            Form form = this.form;
            int i = invalidrequestheadersexception.errorNumber;
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(invalidrequestheadersexception.index);
            form.dispatchErrorOccurredEvent(this, "RequestHeaders", i, aobj);
            return;
        }
    }

    public String ResponseFileName()
    {
        return responseFileName;
    }

    public void ResponseFileName(String s)
    {
        responseFileName = s;
    }

    public void SaveResponse(boolean flag)
    {
        saveResponse = flag;
    }

    public boolean SaveResponse()
    {
        return saveResponse;
    }

    public String UriEncode(String s)
    {
        String s1;
        try
        {
            s1 = URLEncoder.encode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            Log.e("Web", "UTF-8 is unsupported?", unsupportedencodingexception);
            return "";
        }
        return s1;
    }

    public String Url()
    {
        return urlString;
    }

    public void Url(String s)
    {
        urlString = s;
    }

    public Object XMLTextDecode(String s)
    {
        Object obj;
        try
        {
            obj = JsonTextDecode(XML.toJSONObject(s).toString());
        }
        catch (JSONException jsonexception)
        {
            Log.e("Exception in XMLTextDecode", jsonexception.getMessage());
            Form form = this.form;
            Object aobj[] = new Object[1];
            aobj[0] = jsonexception.getMessage();
            form.dispatchErrorOccurredEvent(this, "XMLTextDecode", 1105, aobj);
            return YailList.makeEmptyList();
        }
        return obj;
    }

    String buildRequestData(YailList yaillist)
        throws BuildRequestDataException
    {
        StringBuilder stringbuilder = new StringBuilder();
        String s = "";
        for (int i = 0; i < yaillist.size();)
        {
            Object obj = yaillist.getObject(i);
            if (obj instanceof YailList)
            {
                YailList yaillist1 = (YailList)obj;
                if (yaillist1.size() == 2)
                {
                    String s1 = yaillist1.getObject(0).toString();
                    String s2 = yaillist1.getObject(1).toString();
                    stringbuilder.append(s).append(UriEncode(s1)).append('=').append(UriEncode(s2));
                    s = "&";
                    i++;
                } else
                {
                    throw new BuildRequestDataException(1113, i + 1);
                }
            } else
            {
                throw new BuildRequestDataException(1112, i + 1);
            }
        }

        return stringbuilder.toString();
    }

    static 
    {
        mimeTypeToExtension = Maps.newHashMap();
        mimeTypeToExtension.put("application/pdf", "pdf");
        mimeTypeToExtension.put("application/zip", "zip");
        mimeTypeToExtension.put("audio/mpeg", "mpeg");
        mimeTypeToExtension.put("audio/mp3", "mp3");
        mimeTypeToExtension.put("audio/mp4", "mp4");
        mimeTypeToExtension.put("image/gif", "gif");
        mimeTypeToExtension.put("image/jpeg", "jpg");
        mimeTypeToExtension.put("image/png", "png");
        mimeTypeToExtension.put("image/tiff", "tiff");
        mimeTypeToExtension.put("text/plain", "txt");
        mimeTypeToExtension.put("text/html", "html");
        mimeTypeToExtension.put("text/xml", "xml");
    }








}
