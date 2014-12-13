// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.util.Log;
import com.google.api.client.extensions.android2.AndroidHttp;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.googleapis.services.GoogleKeyInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.fusiontables.Fusiontables;
import com.google.appinventor.components.runtime.util.ClientLoginHelper;
import com.google.appinventor.components.runtime.util.IClientLoginHelper;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.OAuth2Helper;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.appinventor.components.runtime:
//            AndroidNonvisibleComponent, Component, ComponentContainer, Form, 
//            EventDispatcher

public class FusiontablesControl extends AndroidNonvisibleComponent
    implements Component
{
    private class QueryProcessor extends AsyncTask
    {

        private ProgressDialog progress;
        final FusiontablesControl this$0;

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient String doInBackground(String as[])
        {
            String s;
            try
            {
                HttpUriRequest httpurirequest = genFusiontablesQuery(as[0]);
                Log.d("fusion", (new StringBuilder()).append("Fetching: ").append(as[0]).toString());
                HttpResponse httpresponse = requestHelper.execute(httpurirequest);
                ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
                httpresponse.getEntity().writeTo(bytearrayoutputstream);
                Log.d("fusion", (new StringBuilder()).append("Response: ").append(httpresponse.getStatusLine().toString()).toString());
                s = bytearrayoutputstream.toString();
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
                return ioexception.getMessage();
            }
            return s;
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((String)obj);
        }

        protected void onPostExecute(String s)
        {
            progress.dismiss();
            GotResult(s);
        }

        protected void onPreExecute()
        {
            progress = ProgressDialog.show(activity, "Fusiontables", "processing query...", true);
        }

        private QueryProcessor()
        {
            this$0 = FusiontablesControl.this;
            super();
            progress = null;
        }

    }

    private class QueryProcessorV1 extends AsyncTask
    {

        private static final String TAG = "QueryProcessorV1";
        private final Activity activity;
        private final ProgressDialog dialog;
        final FusiontablesControl this$0;

        private String serviceAuthRequest(String s)
        {
            com.google.api.client.http.HttpTransport httptransport;
            GsonFactory gsonfactory;
            httptransport = AndroidHttp.newCompatibleTransport();
            gsonfactory = new GsonFactory();
            Log.i("SERVICE_ACCOUNT", (new StringBuilder()).append("keyPath ").append(keyPath).toString());
            com.google.api.client.http.HttpResponse httpresponse;
            if (cachedServiceCredentials == null)
            {
                cachedServiceCredentials = MediaUtil.copyMediaToTempFile(container.$form(), keyPath);
            }
            com.google.api.client.googleapis.auth.oauth2.GoogleCredential.Builder builder = (new com.google.api.client.googleapis.auth.oauth2.GoogleCredential.Builder()).setTransport(httptransport).setJsonFactory(gsonfactory).setServiceAccountId(serviceAccountEmail);
            String as[] = new String[1];
            as[0] = scope;
            com.google.api.services.fusiontables.Fusiontables.Query.Sql sql = (new com.google.api.services.fusiontables.Fusiontables.Builder(httptransport, gsonfactory, builder.setServiceAccountScopes(as).setServiceAccountPrivateKeyFromP12File(cachedServiceCredentials).build())).setJsonHttpRequestInitializer(new GoogleKeyInitializer(ApiKey())).build().query().sql(s);
            sql.put("alt", "csv");
            httpresponse = sql.executeUnparsed();
            if (httpresponse == null) goto _L2; else goto _L1
_L1:
            queryResultStr = FusiontablesControl.httpResponseToString(httpresponse);
            Log.i("QueryProcessorV1", (new StringBuilder()).append("Query = ").append(s).append("\nResultStr = ").append(queryResultStr).toString());
_L3:
            Log.i("SERVICE_ACCOUNT", "executed sql query");
_L4:
            return queryResultStr;
_L2:
            queryResultStr = errorMessage;
            Log.i("QueryProcessorV1", (new StringBuilder()).append("Error:  ").append(errorMessage).toString());
              goto _L3
            Throwable throwable;
            throwable;
            throwable.printStackTrace();
            errorMessage = throwable.getMessage();
              goto _L4
        }

        private String userAuthRequest(String s)
        {
            queryResultStr = "";
            String s1 = (new OAuth2Helper()).getRefreshedAuthToken(activity, authTokenType);
            if (s1 != null)
            {
                if (s.toLowerCase().contains("create table"))
                {
                    queryResultStr = doPostRequest(parseSqlCreateQueryToJson(s), s1);
                    return queryResultStr;
                }
                com.google.api.client.http.HttpResponse httpresponse = sendQuery(s, s1);
                if (httpresponse != null)
                {
                    queryResultStr = FusiontablesControl.httpResponseToString(httpresponse);
                    Log.i("QueryProcessorV1", (new StringBuilder()).append("Query = ").append(s).append("\nResultStr = ").append(queryResultStr).toString());
                } else
                {
                    queryResultStr = errorMessage;
                    Log.i("QueryProcessorV1", (new StringBuilder()).append("Error:  ").append(errorMessage).toString());
                }
                return queryResultStr;
            } else
            {
                return OAuth2Helper.getErrorMessage();
            }
        }

        protected volatile Object doInBackground(Object aobj[])
        {
            return doInBackground((String[])aobj);
        }

        protected transient String doInBackground(String as[])
        {
            String s = as[0];
            Log.i("QueryProcessorV1", (new StringBuilder()).append("Starting doInBackground ").append(s).toString());
            if (isServiceAuth)
            {
                return serviceAuthRequest(s);
            } else
            {
                return userAuthRequest(s);
            }
        }

        protected volatile void onPostExecute(Object obj)
        {
            onPostExecute((String)obj);
        }

        protected void onPostExecute(String s)
        {
            Log.i("fusion", (new StringBuilder()).append("Query result ").append(s).toString());
            if (s == null)
            {
                s = "Error";
            }
            dialog.dismiss();
            GotResult(s);
        }

        protected void onPreExecute()
        {
            dialog.setMessage("Fusiontables...");
            dialog.show();
        }

        QueryProcessorV1(Activity activity1)
        {
            this$0 = FusiontablesControl.this;
            super();
            Log.i("QueryProcessorV1", "Creating AsyncFusiontablesQuery");
            activity = activity1;
            dialog = new ProgressDialog(activity1);
        }
    }


    public static final String APP_NAME = "App Inventor";
    public static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";
    public static final String AUTH_TOKEN_TYPE_FUSIONTABLES = "oauth2:https://www.googleapis.com/auth/fusiontables";
    private static final String DEFAULT_QUERY = "show tables";
    private static final String DIALOG_TEXT = "Choose an account to access FusionTables";
    public static final String FUSIONTABLES_POST = "https://www.googleapis.com/fusiontables/v1/tables";
    private static final String FUSIONTABLES_SERVICE = "fusiontables";
    public static final String FUSIONTABLES_URL = "https://www.googleapis.com/fusiontables/v1/query";
    private static final String FUSION_QUERY_URL = "http://www.google.com/fusiontables/api/query";
    private static final String LOG_TAG = "fusion";
    private static final int SERVER_TIMEOUT_MS = 30000;
    private final Activity activity;
    private String apiKey;
    private String authTokenType;
    private File cachedServiceCredentials;
    private final ComponentContainer container;
    private String errorMessage;
    private boolean isServiceAuth;
    private String keyPath;
    private String query;
    private String queryResultStr;
    private final IClientLoginHelper requestHelper = createClientLoginHelper("Choose an account to access FusionTables", "fusiontables");
    private String scope;
    private String serviceAccountEmail;

    public FusiontablesControl(ComponentContainer componentcontainer)
    {
        super(componentcontainer.$form());
        cachedServiceCredentials = null;
        authTokenType = "oauth2:https://www.googleapis.com/auth/fusiontables";
        errorMessage = "Error on Fusiontables query";
        keyPath = "";
        isServiceAuth = false;
        serviceAccountEmail = "";
        scope = "https://www.googleapis.com/auth/fusiontables";
        container = componentcontainer;
        activity = componentcontainer.$context();
        query = "show tables";
        if (SdkLevel.getLevel() < 5)
        {
            showNoticeAndDie("Sorry. The Fusiontables component is not compatible with this phone.", "This application must exit.", "Rats!");
        }
    }

    private IClientLoginHelper createClientLoginHelper(String s, String s1)
    {
        if (SdkLevel.getLevel() >= 5)
        {
            DefaultHttpClient defaulthttpclient = new DefaultHttpClient();
            HttpConnectionParams.setSoTimeout(defaulthttpclient.getParams(), 30000);
            HttpConnectionParams.setConnectionTimeout(defaulthttpclient.getParams(), 30000);
            return new ClientLoginHelper(activity, s1, s, defaulthttpclient);
        } else
        {
            return null;
        }
    }

    private String doPostRequest(String s, String s1)
    {
        HttpResponse httpresponse;
        String s3;
        String s2 = s.trim().substring("create table".length());
        Log.i("fusion", (new StringBuilder()).append("Http Post content = ").append(s2).toString());
        HttpPost httppost = new HttpPost((new StringBuilder()).append("https://www.googleapis.com/fusiontables/v1/tables?key=").append(ApiKey()).toString());
        StringEntity stringentity;
        DefaultHttpClient defaulthttpclient;
        int i;
        JSONObject jsonobject;
        try
        {
            stringentity = new StringEntity(s2);
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            unsupportedencodingexception.printStackTrace();
            return (new StringBuilder()).append("Error: ").append(unsupportedencodingexception.getMessage()).toString();
        }
        stringentity.setContentType("application/json");
        httppost.addHeader("Authorization", (new StringBuilder()).append("Bearer ").append(s1).toString());
        httppost.setEntity(stringentity);
        defaulthttpclient = new DefaultHttpClient();
        try
        {
            httpresponse = defaulthttpclient.execute(httppost);
        }
        catch (ClientProtocolException clientprotocolexception)
        {
            clientprotocolexception.printStackTrace();
            return (new StringBuilder()).append("Error: ").append(clientprotocolexception.getMessage()).toString();
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return (new StringBuilder()).append("Error: ").append(ioexception.getMessage()).toString();
        }
        i = httpresponse.getStatusLine().getStatusCode();
        if (httpresponse == null || i != 200)
        {
            break MISSING_BLOCK_LABEL_473;
        }
        s3 = httpApacheResponseToString(httpresponse);
        jsonobject = new JSONObject(s3);
        if (!jsonobject.has("tableId")) goto _L2; else goto _L1
_L1:
        queryResultStr = (new StringBuilder()).append("tableId,").append(jsonobject.get("tableId")).toString();
_L3:
        Log.i("fusion", (new StringBuilder()).append("Response code = ").append(httpresponse.getStatusLine()).toString());
        Log.i("fusion", (new StringBuilder()).append("Query = ").append(s).append("\nResultStr = ").append(queryResultStr).toString());
_L4:
        return queryResultStr;
_L2:
        try
        {
            queryResultStr = s3;
        }
        catch (IllegalStateException illegalstateexception)
        {
            illegalstateexception.printStackTrace();
            return (new StringBuilder()).append("Error: ").append(illegalstateexception.getMessage()).toString();
        }
        catch (JSONException jsonexception)
        {
            jsonexception.printStackTrace();
            return (new StringBuilder()).append("Error: ").append(jsonexception.getMessage()).toString();
        }
          goto _L3
        Log.i("fusion", (new StringBuilder()).append("Error: ").append(httpresponse.getStatusLine().toString()).toString());
        queryResultStr = httpresponse.getStatusLine().toString();
          goto _L4
    }

    private HttpUriRequest genFusiontablesQuery(String s)
        throws IOException
    {
        HttpPost httppost = new HttpPost("http://www.google.com/fusiontables/api/query");
        ArrayList arraylist = new ArrayList(1);
        arraylist.add(new BasicNameValuePair("sql", s));
        UrlEncodedFormEntity urlencodedformentity = new UrlEncodedFormEntity(arraylist, "UTF-8");
        urlencodedformentity.setContentType("application/x-www-form-urlencoded");
        httppost.setEntity(urlencodedformentity);
        return httppost;
    }

    public static String httpApacheResponseToString(HttpResponse httpresponse)
    {
        String s;
label0:
        {
            s = "";
            if (httpresponse != null)
            {
                if (httpresponse.getStatusLine().getStatusCode() == 200)
                {
                    break label0;
                }
                s = (new StringBuilder()).append(httpresponse.getStatusLine().getStatusCode()).append(" ").append(httpresponse.getStatusLine().getReasonPhrase()).toString();
            }
            return s;
        }
        String s1;
        try
        {
            s1 = parseResponse(httpresponse.getEntity().getContent());
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return s;
        }
        return s1;
    }

    public static String httpResponseToString(com.google.api.client.http.HttpResponse httpresponse)
    {
        String s;
label0:
        {
            s = "";
            if (httpresponse != null)
            {
                if (httpresponse.getStatusCode() == 200)
                {
                    break label0;
                }
                s = (new StringBuilder()).append(httpresponse.getStatusCode()).append(" ").append(httpresponse.getStatusMessage()).toString();
            }
            return s;
        }
        String s1;
        try
        {
            s1 = parseResponse(httpresponse.getContent());
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            return s;
        }
        return s1;
    }

    public static String parseResponse(InputStream inputstream)
    {
        String s = "";
        BufferedReader bufferedreader;
        StringBuilder stringbuilder;
        bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
        stringbuilder = new StringBuilder();
_L1:
        String s1 = bufferedreader.readLine();
label0:
        {
            if (s1 == null)
            {
                break label0;
            }
            try
            {
                stringbuilder.append((new StringBuilder()).append(s1).append("\n").toString());
            }
            catch (IOException ioexception)
            {
                ioexception.printStackTrace();
                return s;
            }
        }
          goto _L1
        s = stringbuilder.toString();
        Log.i("fusion", (new StringBuilder()).append("resultStr = ").append(s).toString());
        bufferedreader.close();
        return s;
    }

    private String parseSqlCreateQueryToJson(String s)
    {
        Log.i("fusion", (new StringBuilder()).append("parsetoJSonSqlCreate :").append(s).toString());
        StringBuilder stringbuilder = new StringBuilder();
        String s1 = s.trim();
        String s2 = s1.substring("create table".length(), s1.indexOf('(')).trim();
        String as[] = s1.substring(1 + s1.indexOf('('), s1.indexOf(')')).split(",");
        stringbuilder.append("{'columns':[");
        for (int i = 0; i < as.length; i++)
        {
            String as1[] = as[i].split(":");
            stringbuilder.append((new StringBuilder()).append("{'name': '").append(as1[0].trim()).append("', 'type': '").append(as1[1].trim()).append("'}").toString());
            if (i < -1 + as.length)
            {
                stringbuilder.append(",");
            }
        }

        stringbuilder.append("],");
        stringbuilder.append("'isExportable':'true',");
        stringbuilder.append((new StringBuilder()).append("'name': '").append(s2).append("'").toString());
        stringbuilder.append("}");
        stringbuilder.insert(0, "CREATE TABLE ");
        Log.i("fusion", (new StringBuilder()).append("result = ").append(stringbuilder.toString()).toString());
        return stringbuilder.toString();
    }

    private void showNoticeAndDie(String s, String s1, String s2)
    {
        AlertDialog alertdialog = (new android.app.AlertDialog.Builder(activity)).create();
        alertdialog.setTitle(s1);
        alertdialog.setCancelable(false);
        alertdialog.setMessage(s);
        alertdialog.setButton(s2, new android.content.DialogInterface.OnClickListener() {

            final FusiontablesControl this$0;

            public void onClick(DialogInterface dialoginterface, int i)
            {
                activity.finish();
            }

            
            {
                this$0 = FusiontablesControl.this;
                super();
            }
        });
        alertdialog.show();
    }

    public String ApiKey()
    {
        return apiKey;
    }

    public void ApiKey(String s)
    {
        apiKey = s;
    }

    public void DoQuery()
    {
        if (requestHelper != null)
        {
            QueryProcessor queryprocessor = new QueryProcessor();
            String as[] = new String[1];
            as[0] = query;
            queryprocessor.execute(as);
            return;
        } else
        {
            form.dispatchErrorOccurredEvent(this, "DoQuery", 3, new Object[0]);
            return;
        }
    }

    public void ForgetLogin()
    {
        OAuth2Helper.resetAccountCredential(activity);
    }

    public void GetRows(String s, String s1)
    {
        query = (new StringBuilder()).append("SELECT ").append(s1).append(" FROM ").append(s).toString();
        QueryProcessorV1 queryprocessorv1 = new QueryProcessorV1(activity);
        String as[] = new String[1];
        as[0] = query;
        queryprocessorv1.execute(as);
    }

    public void GetRowsWithConditions(String s, String s1, String s2)
    {
        query = (new StringBuilder()).append("SELECT ").append(s1).append(" FROM ").append(s).append(" WHERE ").append(s2).toString();
        QueryProcessorV1 queryprocessorv1 = new QueryProcessorV1(activity);
        String as[] = new String[1];
        as[0] = query;
        queryprocessorv1.execute(as);
    }

    public void GotResult(String s)
    {
        EventDispatcher.dispatchEvent(this, "GotResult", new Object[] {
            s
        });
    }

    public void InsertRow(String s, String s1, String s2)
    {
        query = (new StringBuilder()).append("INSERT INTO ").append(s).append(" (").append(s1).append(")").append(" VALUES ").append("(").append(s2).append(")").toString();
        QueryProcessorV1 queryprocessorv1 = new QueryProcessorV1(activity);
        String as[] = new String[1];
        as[0] = query;
        queryprocessorv1.execute(as);
    }

    public String KeyFile()
    {
        return keyPath;
    }

    public void KeyFile(String s)
    {
        if (s.equals(keyPath))
        {
            return;
        }
        if (cachedServiceCredentials != null)
        {
            cachedServiceCredentials.delete();
            cachedServiceCredentials = null;
        }
        if (s == null)
        {
            s = "";
        }
        keyPath = s;
    }

    public String Query()
    {
        return query;
    }

    public void Query(String s)
    {
        query = s;
    }

    public void SendQuery()
    {
        QueryProcessorV1 queryprocessorv1 = new QueryProcessorV1(activity);
        String as[] = new String[1];
        as[0] = query;
        queryprocessorv1.execute(as);
    }

    public String ServiceAccountEmail()
    {
        return serviceAccountEmail;
    }

    public void ServiceAccountEmail(String s)
    {
        serviceAccountEmail = s;
    }

    public void UseServiceAuthentication(boolean flag)
    {
        isServiceAuth = flag;
    }

    public boolean UseServiceAuthentication()
    {
        return isServiceAuth;
    }

    public void handleOAuthError(String s)
    {
        Log.i("fusion", (new StringBuilder()).append("handleOAuthError: ").append(s).toString());
        errorMessage = s;
    }

    public com.google.api.client.http.HttpResponse sendQuery(String s, String s1)
    {
        Log.i("fusion", (new StringBuilder()).append("executing ").append(s).toString());
        Fusiontables fusiontables = (new com.google.api.services.fusiontables.Fusiontables.Builder(AndroidHttp.newCompatibleTransport(), new GsonFactory(), new GoogleCredential())).setApplicationName("App Inventor Fusiontables/v1.0").setJsonHttpRequestInitializer(new GoogleKeyInitializer(ApiKey())).build();
        com.google.api.client.http.HttpResponse httpresponse;
        try
        {
            com.google.api.services.fusiontables.Fusiontables.Query.Sql sql = fusiontables.query().sql(s);
            sql.put("alt", "csv");
            sql.setOauthToken(s1);
            httpresponse = sql.executeUnparsed();
        }
        catch (GoogleJsonResponseException googlejsonresponseexception)
        {
            googlejsonresponseexception.printStackTrace();
            errorMessage = googlejsonresponseexception.getMessage();
            return null;
        }
        catch (IOException ioexception)
        {
            ioexception.printStackTrace();
            errorMessage = ioexception.getMessage();
            return null;
        }
        return httpresponse;
    }





/*
    static File access$1102(FusiontablesControl fusiontablescontrol, File file)
    {
        fusiontablescontrol.cachedServiceCredentials = file;
        return file;
    }

*/









/*
    static String access$502(FusiontablesControl fusiontablescontrol, String s)
    {
        fusiontablescontrol.queryResultStr = s;
        return s;
    }

*/






/*
    static String access$902(FusiontablesControl fusiontablescontrol, String s)
    {
        fusiontablescontrol.errorMessage = s;
        return s;
    }

*/
}
