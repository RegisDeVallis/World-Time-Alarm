// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.acra.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.acra.ACRA;
import org.acra.ACRAConfiguration;
import org.acra.log.ACRALog;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

// Referenced classes of package org.acra.util:
//            FakeSocketFactory

public final class HttpRequest
{
    private static class SocketTimeOutRetryHandler
        implements HttpRequestRetryHandler
    {

        private final HttpParams httpParams;
        private final int maxNrRetries;

        public boolean retryRequest(IOException ioexception, int i, HttpContext httpcontext)
        {
            if (ioexception instanceof SocketTimeoutException)
            {
                if (i <= maxNrRetries)
                {
                    if (httpParams != null)
                    {
                        int j = 2 * HttpConnectionParams.getSoTimeout(httpParams);
                        HttpConnectionParams.setSoTimeout(httpParams, j);
                        ACRA.log.d(ACRA.LOG_TAG, (new StringBuilder()).append("SocketTimeOut - increasing time out to ").append(j).append(" millis and trying again").toString());
                    } else
                    {
                        ACRA.log.d(ACRA.LOG_TAG, "SocketTimeOut - no HttpParams, cannot increase time out. Trying again with current settings");
                    }
                    return true;
                }
                ACRA.log.d(ACRA.LOG_TAG, (new StringBuilder()).append("SocketTimeOut but exceeded max number of retries : ").append(maxNrRetries).toString());
            }
            return false;
        }

        private SocketTimeOutRetryHandler(HttpParams httpparams, int i)
        {
            httpParams = httpparams;
            maxNrRetries = i;
        }

    }


    private int connectionTimeOut;
    private String login;
    private int maxNrRetries;
    private String password;
    private int socketTimeOut;

    public HttpRequest()
    {
        connectionTimeOut = 3000;
        socketTimeOut = 3000;
        maxNrRetries = 3;
    }

    private UsernamePasswordCredentials getCredentials()
    {
        if (login != null || password != null)
        {
            return new UsernamePasswordCredentials(login, password);
        } else
        {
            return null;
        }
    }

    private HttpClient getHttpClient()
    {
        BasicHttpParams basichttpparams = new BasicHttpParams();
        basichttpparams.setParameter("http.protocol.cookie-policy", "rfc2109");
        HttpConnectionParams.setConnectionTimeout(basichttpparams, connectionTimeOut);
        HttpConnectionParams.setSoTimeout(basichttpparams, socketTimeOut);
        HttpConnectionParams.setSocketBufferSize(basichttpparams, 8192);
        SchemeRegistry schemeregistry = new SchemeRegistry();
        schemeregistry.register(new Scheme("http", new PlainSocketFactory(), 80));
        DefaultHttpClient defaulthttpclient;
        if (ACRA.getConfig().disableSSLCertValidation())
        {
            schemeregistry.register(new Scheme("https", new FakeSocketFactory(), 443));
        } else
        {
            schemeregistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
        }
        defaulthttpclient = new DefaultHttpClient(new ThreadSafeClientConnManager(basichttpparams, schemeregistry), basichttpparams);
        defaulthttpclient.setHttpRequestRetryHandler(new SocketTimeOutRetryHandler(basichttpparams, maxNrRetries));
        return defaulthttpclient;
    }

    private HttpPost getHttpPost(URL url, Map map)
        throws UnsupportedEncodingException
    {
        HttpPost httppost = new HttpPost(url.toString());
        UsernamePasswordCredentials usernamepasswordcredentials = getCredentials();
        if (usernamepasswordcredentials != null)
        {
            httppost.addHeader(BasicScheme.authenticate(usernamepasswordcredentials, "UTF-8", false));
        }
        httppost.setHeader("User-Agent", "Android");
        httppost.setHeader("Accept", "text/html,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5");
        httppost.setHeader("Content-Type", "application/x-www-form-urlencoded");
        httppost.setEntity(new StringEntity(getParamsAsString(map), "UTF-8"));
        return httppost;
    }

    private String getParamsAsString(Map map)
        throws UnsupportedEncodingException
    {
        StringBuilder stringbuilder = new StringBuilder();
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) 
        {
            Object obj = iterator.next();
            if (stringbuilder.length() != 0)
            {
                stringbuilder.append('&');
            }
            Object obj1 = map.get(obj);
            Object obj2;
            if (obj1 == null)
            {
                obj2 = "";
            } else
            {
                obj2 = obj1;
            }
            stringbuilder.append(URLEncoder.encode(obj.toString(), "UTF-8"));
            stringbuilder.append('=');
            stringbuilder.append(URLEncoder.encode(obj2.toString(), "UTF-8"));
        }
        return stringbuilder.toString();
    }

    public void sendPost(URL url, Map map)
        throws IOException
    {
        HttpClient httpclient = getHttpClient();
        HttpPost httppost = getHttpPost(url, map);
        ACRA.log.d(ACRA.LOG_TAG, (new StringBuilder()).append("Sending request to ").append(url).toString());
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); iterator.next()) { }
        HttpResponse httpresponse = httpclient.execute(httppost, new BasicHttpContext());
        if (httpresponse != null)
        {
            if (httpresponse.getStatusLine() != null)
            {
                String s = Integer.toString(httpresponse.getStatusLine().getStatusCode());
                if (s.startsWith("4") || s.startsWith("5"))
                {
                    throw new IOException((new StringBuilder()).append("Host returned error code ").append(s).toString());
                }
            }
            EntityUtils.toString(httpresponse.getEntity());
        }
    }

    public void setConnectionTimeOut(int i)
    {
        connectionTimeOut = i;
    }

    public void setLogin(String s)
    {
        login = s;
    }

    public void setMaxNrRetries(int i)
    {
        maxNrRetries = i;
    }

    public void setPassword(String s)
    {
        password = s;
    }

    public void setSocketTimeOut(int i)
    {
        socketTimeOut = i;
    }
}
