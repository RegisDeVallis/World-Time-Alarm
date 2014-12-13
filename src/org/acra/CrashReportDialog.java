// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.acra;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.io.IOException;
import org.acra.collector.CrashReportData;
import org.acra.util.ToastSender;

// Referenced classes of package org.acra:
//            ACRA, ACRAConfiguration, ErrorReporter, CrashReportPersister, 
//            ReportField

public final class CrashReportDialog extends Activity
{

    private static final String STATE_COMMENT = "comment";
    private static final String STATE_EMAIL = "email";
    String mReportFileName;
    private SharedPreferences prefs;
    private EditText userComment;
    private EditText userEmail;

    public CrashReportDialog()
    {
    }

    protected void cancelNotification()
    {
        ((NotificationManager)getSystemService("notification")).cancel(666);
    }

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        mReportFileName = getIntent().getStringExtra("REPORT_FILE_NAME");
        Log.d(ACRA.LOG_TAG, (new StringBuilder()).append("Opening CrashReportDialog for ").append(mReportFileName).toString());
        if (mReportFileName == null)
        {
            finish();
        }
        requestWindowFeature(3);
        LinearLayout linearlayout = new LinearLayout(this);
        linearlayout.setOrientation(1);
        linearlayout.setPadding(10, 10, 10, 10);
        linearlayout.setLayoutParams(new android.view.ViewGroup.LayoutParams(-1, -2));
        linearlayout.setFocusable(true);
        linearlayout.setFocusableInTouchMode(true);
        final ScrollView scroll = new ScrollView(this);
        linearlayout.addView(scroll, new android.widget.LinearLayout.LayoutParams(-1, -1, 1.0F));
        LinearLayout linearlayout1 = new LinearLayout(this);
        linearlayout1.setOrientation(1);
        scroll.addView(linearlayout1);
        TextView textview = new TextView(this);
        textview.setText(getText(ACRA.getConfig().resDialogText()));
        linearlayout1.addView(textview);
        int i = ACRA.getConfig().resDialogCommentPrompt();
        if (i != 0)
        {
            TextView textview1 = new TextView(this);
            textview1.setText(getText(i));
            textview1.setPadding(textview1.getPaddingLeft(), 10, textview1.getPaddingRight(), textview1.getPaddingBottom());
            linearlayout1.addView(textview1, new android.widget.LinearLayout.LayoutParams(-1, -2));
            userComment = new EditText(this);
            userComment.setLines(2);
            if (bundle != null)
            {
                String s1 = bundle.getString("comment");
                if (s1 != null)
                {
                    userComment.setText(s1);
                }
            }
            linearlayout1.addView(userComment);
        }
        int j = ACRA.getConfig().resDialogEmailPrompt();
        if (j != 0)
        {
            TextView textview2 = new TextView(this);
            textview2.setText(getText(j));
            textview2.setPadding(textview2.getPaddingLeft(), 10, textview2.getPaddingRight(), textview2.getPaddingBottom());
            linearlayout1.addView(textview2);
            userEmail = new EditText(this);
            userEmail.setSingleLine();
            userEmail.setInputType(33);
            prefs = getSharedPreferences(ACRA.getConfig().sharedPreferencesName(), ACRA.getConfig().sharedPreferencesMode());
            String s = null;
            if (bundle != null)
            {
                s = bundle.getString("email");
            }
            LinearLayout linearlayout2;
            Button button;
            Button button1;
            int k;
            if (s != null)
            {
                userEmail.setText(s);
            } else
            {
                userEmail.setText(prefs.getString("acra.user.email", ""));
            }
            linearlayout1.addView(userEmail);
        }
        linearlayout2 = new LinearLayout(this);
        linearlayout2.setLayoutParams(new android.widget.LinearLayout.LayoutParams(-1, -2));
        linearlayout2.setPadding(linearlayout2.getPaddingLeft(), 10, linearlayout2.getPaddingRight(), linearlayout2.getPaddingBottom());
        button = new Button(this);
        button.setText(0x1040013);
        button.setOnClickListener(new android.view.View.OnClickListener() {

            final CrashReportDialog this$0;

            public void onClick(View view)
            {
                String s2;
                String s3;
                CrashReportPersister crashreportpersister;
                int l;
                if (userComment != null)
                {
                    s2 = userComment.getText().toString();
                } else
                {
                    s2 = "";
                }
                if (prefs != null && userEmail != null)
                {
                    s3 = userEmail.getText().toString();
                    android.content.SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("acra.user.email", s3);
                    editor.commit();
                } else
                {
                    s3 = "";
                }
                crashreportpersister = new CrashReportPersister(getApplicationContext());
                try
                {
                    Log.d(ACRA.LOG_TAG, (new StringBuilder()).append("Add user comment to ").append(mReportFileName).toString());
                    CrashReportData crashreportdata = crashreportpersister.load(mReportFileName);
                    crashreportdata.put(ReportField.USER_COMMENT, s2);
                    crashreportdata.put(ReportField.USER_EMAIL, s3);
                    crashreportpersister.store(crashreportdata, mReportFileName);
                }
                catch (IOException ioexception)
                {
                    Log.w(ACRA.LOG_TAG, "User comment not added: ", ioexception);
                }
                Log.v(ACRA.LOG_TAG, "About to start SenderWorker from CrashReportDialog");
                ACRA.getErrorReporter().startSendingReports(false, true);
                l = ACRA.getConfig().resDialogOkToast();
                if (l != 0)
                {
                    ToastSender.sendToast(getApplicationContext(), l, 1);
                }
                finish();
            }

            
            {
                this$0 = CrashReportDialog.this;
                super();
            }
        });
        linearlayout2.addView(button, new android.widget.LinearLayout.LayoutParams(-1, -2, 1.0F));
        button1 = new Button(this);
        button1.setText(0x1040009);
        button1.setOnClickListener(new android.view.View.OnClickListener() {

            final CrashReportDialog this$0;

            public void onClick(View view)
            {
                ACRA.getErrorReporter().deletePendingNonApprovedReports(false);
                finish();
            }

            
            {
                this$0 = CrashReportDialog.this;
                super();
            }
        });
        linearlayout2.addView(button1, new android.widget.LinearLayout.LayoutParams(-1, -2, 1.0F));
        linearlayout.addView(linearlayout2, new android.widget.LinearLayout.LayoutParams(-1, -2));
        setContentView(linearlayout);
        k = ACRA.getConfig().resDialogTitle();
        if (k != 0)
        {
            setTitle(k);
        }
        getWindow().setFeatureDrawableResource(3, ACRA.getConfig().resDialogIcon());
        cancelNotification();
        scroll.post(new Runnable() {

            final CrashReportDialog this$0;
            final ScrollView val$scroll;

            public void run()
            {
                scroll.scrollTo(0, 0);
            }

            
            {
                this$0 = CrashReportDialog.this;
                scroll = scrollview;
                super();
            }
        });
    }

    public boolean onKeyUp(int i, KeyEvent keyevent)
    {
        if (i == 4)
        {
            ACRA.getErrorReporter().deletePendingNonApprovedReports(false);
        }
        return super.onKeyUp(i, keyevent);
    }

    protected void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        if (userComment != null && userComment.getText() != null)
        {
            bundle.putString("comment", userComment.getText().toString());
        }
        if (userEmail != null && userEmail.getText() != null)
        {
            bundle.putString("email", userEmail.getText().toString());
        }
    }



}
