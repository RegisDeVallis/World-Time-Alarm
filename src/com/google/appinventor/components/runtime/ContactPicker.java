// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import com.google.appinventor.components.runtime.util.HoneycombUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.google.appinventor.components.runtime:
//            Picker, ActivityResultListener, ComponentContainer, Form

public class ContactPicker extends Picker
    implements ActivityResultListener
{

    private static String CONTACT_PROJECTION[];
    private static String DATA_PROJECTION[];
    private static final int EMAIL_INDEX = 1;
    private static final int NAME_INDEX = 0;
    private static final int PHONE_INDEX = 2;
    private static final String PROJECTION[] = {
        "name", "primary_email"
    };
    protected final Activity activityContext;
    protected String contactName;
    protected String contactPictureUri;
    protected String emailAddress;
    protected List emailAddressList;
    private final Uri intentUri;
    protected String phoneNumber;
    protected List phoneNumberList;

    public ContactPicker(ComponentContainer componentcontainer)
    {
        this(componentcontainer, android.provider.Contacts.People.CONTENT_URI);
    }

    protected ContactPicker(ComponentContainer componentcontainer, Uri uri)
    {
        super(componentcontainer);
        activityContext = componentcontainer.$context();
        if (SdkLevel.getLevel() >= 12 && uri.equals(android.provider.Contacts.People.CONTENT_URI))
        {
            intentUri = HoneycombUtil.getContentUri();
            return;
        }
        if (SdkLevel.getLevel() >= 12 && uri.equals(android.provider.Contacts.Phones.CONTENT_URI))
        {
            intentUri = HoneycombUtil.getPhoneContentUri();
            return;
        } else
        {
            intentUri = uri;
            return;
        }
    }

    public String ContactName()
    {
        return ensureNotNull(contactName);
    }

    public String EmailAddress()
    {
        return ensureNotNull(emailAddress);
    }

    public List EmailAddressList()
    {
        return ensureNotNull(emailAddressList);
    }

    public String PhoneNumber()
    {
        return ensureNotNull(phoneNumber);
    }

    public List PhoneNumberList()
    {
        return ensureNotNull(phoneNumberList);
    }

    public String Picture()
    {
        return ensureNotNull(contactPictureUri);
    }

    protected boolean checkContactUri(Uri uri, String s)
    {
        Log.i("ContactPicker", (new StringBuilder()).append("contactUri is ").append(uri).toString());
        if (uri == null || !"content".equals(uri.getScheme()))
        {
            Log.i("ContactPicker", "checkContactUri failed: A");
            puntContactSelection(1107);
            return false;
        }
        if (!uri.getSchemeSpecificPart().startsWith(s))
        {
            Log.i("ContactPicker", "checkContactUri failed: C");
            Log.i("ContactPicker", uri.getPath());
            puntContactSelection(1107);
            return false;
        } else
        {
            return true;
        }
    }

    protected String ensureNotNull(String s)
    {
        if (s == null)
        {
            s = "";
        }
        return s;
    }

    protected List ensureNotNull(List list)
    {
        if (list == null)
        {
            list = new ArrayList();
        }
        return list;
    }

    protected String getEmailAddress(String s)
    {
        Cursor cursor;
        int i;
        String s1;
        String s2;
        String as[];
        String s3;
        try
        {
            i = Integer.parseInt(s);
        }
        catch (NumberFormatException numberformatexception)
        {
            return "";
        }
        s1 = "";
        s2 = (new StringBuilder()).append("contact_methods._id = ").append(i).toString();
        as = (new String[] {
            "data"
        });
        cursor = activityContext.getContentResolver().query(android.provider.Contacts.ContactMethods.CONTENT_EMAIL_URI, as, s2, null, null);
        if (!cursor.moveToFirst())
        {
            break MISSING_BLOCK_LABEL_85;
        }
        s3 = guardCursorGetString(cursor, 0);
        s1 = s3;
        cursor.close();
        return ensureNotNull(s1);
        Exception exception;
        exception;
        cursor.close();
        throw exception;
    }

    protected Intent getIntent()
    {
        return new Intent("android.intent.action.PICK", intentUri);
    }

    protected String guardCursorGetString(Cursor cursor, int i)
    {
        String s1 = cursor.getString(i);
        String s = s1;
_L2:
        return ensureNotNull(s);
        Exception exception;
        exception;
        s = "";
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void postHoneycombGetContactEmailAndPhone(Cursor cursor)
    {
        phoneNumber = "";
        emailAddress = "";
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
        }
        if (!arraylist.isEmpty())
        {
            phoneNumber = (String)arraylist.get(0);
        }
        if (!arraylist1.isEmpty())
        {
            emailAddress = (String)arraylist1.get(0);
        }
        phoneNumberList = arraylist;
        emailAddressList = arraylist1;
    }

    public String postHoneycombGetContactNameAndPicture(Cursor cursor)
    {
        String s = "";
        if (cursor.moveToFirst())
        {
            int i = HoneycombUtil.getIdIndex(cursor);
            int j = HoneycombUtil.getNameIndex(cursor);
            int k = HoneycombUtil.getThumbnailIndex(cursor);
            int l = HoneycombUtil.getPhotoIndex(cursor);
            s = guardCursorGetString(cursor, i);
            contactName = guardCursorGetString(cursor, j);
            contactPictureUri = guardCursorGetString(cursor, k);
            Log.i("ContactPicker", (new StringBuilder()).append("photo_uri=").append(guardCursorGetString(cursor, l)).toString());
        }
        return s;
    }

    public void preHoneycombGetContactInfo(Cursor cursor, Uri uri)
    {
        if (cursor.moveToFirst())
        {
            contactName = guardCursorGetString(cursor, 0);
            emailAddress = getEmailAddress(guardCursorGetString(cursor, 1));
            contactPictureUri = uri.toString();
            Object obj;
            if (emailAddress.equals(""))
            {
                obj = new ArrayList();
            } else
            {
                String as[] = new String[1];
                as[0] = emailAddress;
                obj = Arrays.asList(as);
            }
            emailAddressList = ((List) (obj));
        }
    }

    protected void puntContactSelection(int i)
    {
        contactName = "";
        emailAddress = "";
        contactPictureUri = "";
        container.$form().dispatchErrorOccurredEvent(this, "", i, new Object[0]);
    }

    public void resultReturned(int i, int j, Intent intent)
    {
        if (i != requestCode || j != -1) goto _L2; else goto _L1
_L1:
        Uri uri;
        Cursor cursor;
        Cursor cursor1;
        Log.i("ContactPicker", (new StringBuilder()).append("received intent is ").append(intent).toString());
        uri = intent.getData();
        String s;
        int k;
        String s1;
        if (SdkLevel.getLevel() >= 12)
        {
            s = "//com.android.contacts/contact";
        } else
        {
            s = "//contacts/people";
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
        CONTACT_PROJECTION = HoneycombUtil.getContactProjection();
        cursor = activityContext.getContentResolver().query(uri, CONTACT_PROJECTION, null, null, null);
        s1 = postHoneycombGetContactNameAndPicture(cursor);
        DATA_PROJECTION = HoneycombUtil.getDataProjection();
        cursor1 = HoneycombUtil.getDataCursor(s1, activityContext, DATA_PROJECTION);
        postHoneycombGetContactEmailAndPhone(cursor1);
_L7:
        Log.i("ContactPicker", (new StringBuilder()).append("Contact name = ").append(contactName).append(", email address = ").append(emailAddress).append(", phone number = ").append(phoneNumber).append(", contactPhotoUri = ").append(contactPictureUri).toString());
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
        preHoneycombGetContactInfo(cursor, uri);
        cursor1 = null;
          goto _L7
        Exception exception1;
        exception1;
        Log.i("ContactPicker", "checkContactUri failed: D");
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
