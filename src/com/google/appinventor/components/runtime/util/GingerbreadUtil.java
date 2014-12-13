// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.util.Log;
import com.google.appinventor.components.runtime.NearField;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookieStore;
import java.nio.charset.Charset;
import java.util.Locale;

public class GingerbreadUtil
{

    private GingerbreadUtil()
    {
    }

    public static boolean clearCookies(CookieHandler cookiehandler)
    {
        if (cookiehandler instanceof CookieManager)
        {
            CookieStore cookiestore = ((CookieManager)cookiehandler).getCookieStore();
            if (cookiestore != null)
            {
                cookiestore.removeAll();
                return true;
            }
        }
        return false;
    }

    public static NdefRecord createTextRecord(String s, boolean flag)
    {
        byte abyte0[] = Locale.getDefault().getLanguage().getBytes(Charset.forName("US-ASCII"));
        Charset charset;
        byte abyte1[];
        int i;
        char c;
        byte abyte2[];
        if (flag)
        {
            charset = Charset.forName("UTF-8");
        } else
        {
            charset = Charset.forName("UTF-16");
        }
        abyte1 = s.getBytes(charset);
        if (flag)
        {
            i = 0;
        } else
        {
            i = 128;
        }
        c = (char)(i + abyte0.length);
        abyte2 = new byte[1 + abyte0.length + abyte1.length];
        abyte2[0] = (byte)c;
        System.arraycopy(abyte0, 0, abyte2, 1, abyte0.length);
        System.arraycopy(abyte1, 0, abyte2, 1 + abyte0.length, abyte1.length);
        return new NdefRecord((short)1, NdefRecord.RTD_TEXT, new byte[0], abyte2);
    }

    public static void disableNFCAdapter(Activity activity, NfcAdapter nfcadapter)
    {
        nfcadapter.disableForegroundNdefPush(activity);
    }

    public static void enableNFCWriteMode(Activity activity, NfcAdapter nfcadapter, String s)
    {
        nfcadapter.enableForegroundNdefPush(activity, new NdefMessage(new NdefRecord[] {
            createTextRecord(s, true)
        }));
    }

    public static CookieHandler newCookieManager()
    {
        return new CookieManager();
    }

    public static NfcAdapter newNfcAdapter(Context context)
    {
        return NfcAdapter.getDefaultAdapter(context);
    }

    public static void resolveNFCIntent(Intent intent, NearField nearfield)
    {
        if (!"android.nfc.action.NDEF_DISCOVERED".equals(intent.getAction()))
        {
            break MISSING_BLOCK_LABEL_203;
        }
        if (!nearfield.ReadMode()) goto _L2; else goto _L1
_L1:
        android.os.Parcelable aparcelable[] = intent.getParcelableArrayExtra("android.nfc.extra.NDEF_MESSAGES");
        NdefMessage andefmessage[];
        if (aparcelable != null)
        {
            andefmessage = new NdefMessage[aparcelable.length];
            for (int j = 0; j < aparcelable.length; j++)
            {
                andefmessage[j] = (NdefMessage)aparcelable[j];
            }

        } else
        {
            byte abyte0[] = new byte[0];
            andefmessage = (new NdefMessage[] {
                new NdefMessage(new NdefRecord[] {
                    new NdefRecord((short)5, abyte0, abyte0, abyte0)
                })
            });
        }
        nearfield.TagRead((new String(andefmessage[0].getRecords()[0].getPayload())).substring(3));
_L4:
        return;
_L2:
        Tag tag;
        NdefMessage ndefmessage;
        tag = (Tag)intent.getParcelableExtra("android.nfc.extra.TAG");
        int i = nearfield.WriteType();
        ndefmessage = null;
        if (i == 1)
        {
            ndefmessage = new NdefMessage(new NdefRecord[] {
                createTextRecord(nearfield.TextToWrite(), true)
            });
        }
        if (!writeNFCTag(ndefmessage, tag)) goto _L4; else goto _L3
_L3:
        nearfield.TagWritten();
        return;
        Log.e("nearfield", (new StringBuilder()).append("Unknown intent ").append(intent).toString());
        return;
    }

    public static boolean writeNFCTag(NdefMessage ndefmessage, Tag tag)
    {
        int i = ndefmessage.toByteArray().length;
        Ndef ndef = Ndef.get(tag);
        if (ndef == null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        ndef.connect();
        if (!ndef.isWritable())
        {
            return false;
        }
        if (ndef.getMaxSize() < i)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        ndef.writeNdefMessage(ndefmessage);
        return true;
        NdefFormatable ndefformatable = NdefFormatable.get(tag);
        if (ndefformatable == null)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        ndefformatable.connect();
        ndefformatable.format(ndefmessage);
        return true;
        IOException ioexception;
        ioexception;
        return false;
        Exception exception;
        exception;
        return false;
    }
}
