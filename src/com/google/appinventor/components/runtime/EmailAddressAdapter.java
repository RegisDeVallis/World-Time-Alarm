// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.view.View;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import com.google.appinventor.components.runtime.util.HoneycombUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;

public class EmailAddressAdapter extends ResourceCursorAdapter
{

    private static final boolean DEBUG = false;
    private static final String POST_HONEYCOMB_PROJECTION[] = HoneycombUtil.getEmailAdapterProjection();
    public static final int PRE_HONEYCOMB_DATA_INDEX = 2;
    public static final int PRE_HONEYCOMB_NAME_INDEX = 1;
    private static final String PRE_HONEYCOMB_PROJECTION[] = {
        "_id", "name", "data"
    };
    private static String SORT_ORDER;
    private static final String TAG = "EmailAddressAdapter";
    private ContentResolver contentResolver;
    private Context context;

    public EmailAddressAdapter(Context context1)
    {
        super(context1, 0x109000a, null);
        contentResolver = context1.getContentResolver();
        context = context1;
        if (SdkLevel.getLevel() >= 12)
        {
            SORT_ORDER = (new StringBuilder()).append(HoneycombUtil.getTimesContacted()).append(" DESC, ").append(HoneycombUtil.getDisplayName()).toString();
            return;
        } else
        {
            SORT_ORDER = "times_contacted DESC, name";
            return;
        }
    }

    private final String makeDisplayString(Cursor cursor)
    {
        int i = cursor.getColumnIndex(HoneycombUtil.getDisplayName());
        int j = cursor.getColumnIndex(HoneycombUtil.getEmailAddress());
        StringBuilder stringbuilder = new StringBuilder();
        String s;
        String s1;
        boolean flag;
        boolean flag1;
        if (SdkLevel.getLevel() >= 12)
        {
            s = cursor.getString(i);
            s1 = cursor.getString(j);
        } else
        {
            s = cursor.getString(1);
            s1 = cursor.getString(2);
        }
        flag = TextUtils.isEmpty(s);
        flag1 = false;
        if (!flag)
        {
            stringbuilder.append(s);
            flag1 = true;
        }
        if (flag1)
        {
            stringbuilder.append(" <");
        }
        stringbuilder.append(s1);
        if (flag1)
        {
            stringbuilder.append(">");
        }
        return stringbuilder.toString();
    }

    public final void bindView(View view, Context context1, Cursor cursor)
    {
        ((TextView)view).setText(makeDisplayString(cursor));
    }

    public volatile CharSequence convertToString(Cursor cursor)
    {
        return convertToString(cursor);
    }

    public final String convertToString(Cursor cursor)
    {
        int i = cursor.getColumnIndex(HoneycombUtil.getDisplayName());
        int j = cursor.getColumnIndex(HoneycombUtil.getEmailAddress());
        String s;
        String s1;
        if (SdkLevel.getLevel() >= 12)
        {
            s = cursor.getString(i);
            s1 = cursor.getString(j);
        } else
        {
            s = cursor.getString(1);
            s1 = cursor.getString(2);
        }
        return (new Rfc822Token(s, s1, null)).toString();
    }

    public Cursor runQueryOnBackgroundThread(CharSequence charsequence)
    {
        StringBuilder stringbuilder = new StringBuilder();
        android.net.Uri uri = null;
        String s;
        if (charsequence != null)
        {
            String s1 = DatabaseUtils.sqlEscapeString((new StringBuilder()).append(charsequence.toString()).append('%').toString());
            if (SdkLevel.getLevel() >= 12)
            {
                uri = HoneycombUtil.getDataContentUri();
                stringbuilder.append((new StringBuilder()).append("(").append(HoneycombUtil.getDataMimeType()).append("='").append(HoneycombUtil.getEmailType()).append("')").toString());
                stringbuilder.append(" AND ");
                stringbuilder.append("(display_name LIKE ");
                stringbuilder.append(s1);
                stringbuilder.append(")");
            } else
            {
                uri = android.provider.Contacts.ContactMethods.CONTENT_EMAIL_URI;
                stringbuilder.append("(name LIKE ");
                stringbuilder.append(s1);
                stringbuilder.append(") OR (display_name LIKE ");
                stringbuilder.append(s1);
                stringbuilder.append(")");
            }
        }
        s = stringbuilder.toString();
        if (SdkLevel.getLevel() >= 12)
        {
            return contentResolver.query(uri, POST_HONEYCOMB_PROJECTION, s, null, SORT_ORDER);
        } else
        {
            return contentResolver.query(uri, PRE_HONEYCOMB_PROJECTION, s, null, SORT_ORDER);
        }
    }

}
