// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.google.appinventor.components.runtime.util.HoneycombUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.appinventor.components.runtime:
//            ContactPicker, ComponentContainer

public class PhoneNumberPicker extends ContactPicker
{

    private static String DATA_PROJECTION[];
    private static final int EMAIL_INDEX = 3;
    private static final String LOG_TAG = "PhoneNumberPicker";
    private static final int NAME_INDEX = 0;
    private static String NAME_PROJECTION[];
    private static final int NUMBER_INDEX = 1;
    private static final int PERSON_INDEX = 2;
    private static final String PROJECTION[] = {
        "name", "number", "person", "primary_email"
    };

    public PhoneNumberPicker(ComponentContainer componentcontainer)
    {
        super(componentcontainer, android.provider.Contacts.Phones.CONTENT_URI);
    }

    public String PhoneNumber()
    {
        return ensureNotNull(phoneNumber);
    }

    public void postHoneycombGetContactEmailsAndPhones(Cursor cursor)
    {
label0:
        {
            ArrayList arraylist = new ArrayList();
            ArrayList arraylist1 = new ArrayList();
            if (cursor.moveToFirst())
            {
                int i = HoneycombUtil.getPhoneIndex(cursor);
                int j = HoneycombUtil.getEmailIndex(cursor);
                int k = HoneycombUtil.getMimeIndex(cursor);
                String s = HoneycombUtil.getPhoneType();
                String s1 = HoneycombUtil.getEmailType();
                while (!cursor.isAfterLast()) 
                {
                    String s2 = guardCursorGetString(cursor, k);
                    if (s2.contains(s))
                    {
                        arraylist.add(guardCursorGetString(cursor, i));
                    } else
                    if (s2.contains(s1))
                    {
                        arraylist1.add(guardCursorGetString(cursor, j));
                    } else
                    {
                        Log.i("ContactPicker", (new StringBuilder()).append("Type mismatch: ").append(s2).append(" not ").append(s).append(" or ").append(s1).toString());
                    }
                    cursor.moveToNext();
                }
                phoneNumberList = arraylist;
                emailAddressList = arraylist1;
                if (emailAddressList.isEmpty())
                {
                    break label0;
                }
                emailAddress = (String)emailAddressList.get(0);
            }
            return;
        }
        emailAddress = "";
    }

    public String postHoneycombGetContactNameAndPicture(Cursor cursor)
    {
        String s = "";
        if (cursor.moveToFirst())
        {
            int i = HoneycombUtil.getContactIdIndex(cursor);
            int j = HoneycombUtil.getNameIndex(cursor);
            int k = HoneycombUtil.getThumbnailIndex(cursor);
            phoneNumber = guardCursorGetString(cursor, HoneycombUtil.getPhoneIndex(cursor));
            s = guardCursorGetString(cursor, i);
            contactName = guardCursorGetString(cursor, j);
            contactPictureUri = guardCursorGetString(cursor, k);
        }
        return s;
    }

    public void preHoneycombGetContactInfo(Cursor cursor)
    {
        if (cursor.moveToFirst())
        {
            contactName = guardCursorGetString(cursor, 0);
            phoneNumber = guardCursorGetString(cursor, 1);
            int i = cursor.getInt(2);
            contactPictureUri = ContentUris.withAppendedId(android.provider.Contacts.People.CONTENT_URI, i).toString();
            emailAddress = getEmailAddress(guardCursorGetString(cursor, 3));
        }
    }

    public void resultReturned(int i, int j, Intent intent)
    {
        if (i != requestCode || j != -1) goto _L2; else goto _L1
_L1:
        Uri uri;
        Cursor cursor;
        Cursor cursor1;
        Log.i("PhoneNumberPicker", (new StringBuilder()).append("received intent is ").append(intent).toString());
        uri = intent.getData();
        String s;
        int k;
        String s1;
        if (SdkLevel.getLevel() >= 12)
        {
            s = "//com.android.contacts/data";
        } else
        {
            s = "//contacts/phones";
        }
        if (!checkContactUri(uri, s)) goto _L4; else goto _L3
_L3:
        cursor = null;
        cursor1 = null;
        k = SdkLevel.getLevel();
        cursor = null;
        cursor1 = null;
        if (k < 12) goto _L6; else goto _L5
_L5:
        NAME_PROJECTION = HoneycombUtil.getNameProjection();
        cursor = activityContext.getContentResolver().query(uri, NAME_PROJECTION, null, null, null);
        s1 = postHoneycombGetContactNameAndPicture(cursor);
        DATA_PROJECTION = HoneycombUtil.getDataProjection();
        cursor1 = HoneycombUtil.getDataCursor(s1, activityContext, DATA_PROJECTION);
        postHoneycombGetContactEmailsAndPhones(cursor1);
_L7:
        Log.i("PhoneNumberPicker", (new StringBuilder()).append("Contact name = ").append(contactName).append(", phone number = ").append(phoneNumber).append(", emailAddress = ").append(emailAddress).append(", contactPhotoUri = ").append(contactPictureUri).toString());
        if (cursor != null)
        {
            cursor.close();
        }
        if (cursor1 != null)
        {
            cursor1.close();
        }
_L4:
        AfterPicking();
_L2:
        return;
_L6:
        cursor = activityContext.getContentResolver().query(uri, PROJECTION, null, null, null);
        preHoneycombGetContactInfo(cursor);
        cursor1 = null;
          goto _L7
        Exception exception1;
        exception1;
        Log.e("PhoneNumberPicker", "Exception in resultReturned", exception1);
        puntContactSelection(1107);
        if (cursor != null)
        {
            cursor.close();
        }
        if (cursor1 != null)
        {
            cursor1.close();
        }
          goto _L4
        Exception exception;
        exception;
        if (cursor != null)
        {
            cursor.close();
        }
        if (cursor1 != null)
        {
            cursor1.close();
        }
        throw exception;
          goto _L7
    }

}
