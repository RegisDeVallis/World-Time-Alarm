// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.webkit.MimeTypeMap;
import com.google.appinventor.components.runtime.util.MediaUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Comparator;

// Referenced classes of package com.google.appinventor.components.runtime:
//            Picker, ActivityResultListener, ComponentContainer, Form

public class ImagePicker extends Picker
    implements ActivityResultListener
{

    private static final String FILE_PREFIX = "picked_image";
    private static final String LOG_TAG = "ImagePicker";
    private static final String imagePickerDirectoryName = "/Pictures/_app_inventor_image_picker";
    private static int maxSavedFiles = 10;
    private String selectionSavedImage;
    private String selectionURI;

    public ImagePicker(ComponentContainer componentcontainer)
    {
        super(componentcontainer);
        selectionSavedImage = "";
    }

    private void copyToExternalStorageAndDeleteSource(File file, String s)
    {
        IOException ioexception;
        File file1 = null;
        File file2 = new File((new StringBuilder()).append(Environment.getExternalStorageDirectory()).append("/Pictures/_app_inventor_image_picker").toString());
        String s1;
        FileInputStream fileinputstream;
        FileOutputStream fileoutputstream;
        byte abyte0[];
        int i;
        try
        {
            file2.mkdirs();
            file1 = File.createTempFile("picked_image", s, file2);
            selectionSavedImage = file1.getPath();
            Log.i("ImagePicker", (new StringBuilder()).append("saved file path is: ").append(selectionSavedImage).toString());
            fileinputstream = new FileInputStream(file);
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            continue; /* Loop/switch isn't completed */
        }
_L4:
        fileoutputstream = new FileOutputStream(file1);
        abyte0 = new byte[1024];
_L3:
        i = fileinputstream.read(abyte0);
        if (i <= 0) goto _L2; else goto _L1
_L1:
        fileoutputstream.write(abyte0, 0, i);
          goto _L3
        ioexception;
_L7:
        s1 = (new StringBuilder()).append("destination is ").append(selectionSavedImage).append(": ").append("error is ").append(ioexception.getMessage()).toString();
        Log.i("ImagePicker", (new StringBuilder()).append("copyFile failed. ").append(s1).toString());
        container.$form().dispatchErrorOccurredEvent(this, "SaveImage", 1601, new Object[] {
            s1
        });
        selectionSavedImage = "";
        file1.delete();
_L5:
        file.delete();
        trimDirectory(maxSavedFiles, file2);
        return;
_L2:
        fileinputstream.close();
        fileoutputstream.close();
        Log.i("ImagePicker", (new StringBuilder()).append("Image was copied to ").append(selectionSavedImage).toString());
        if (true) goto _L5; else goto _L4
        ioexception;
        if (true) goto _L7; else goto _L6
_L6:
    }

    private void saveSelectedImageToExternalStorage(String s)
    {
        selectionSavedImage = "";
        File file;
        try
        {
            file = MediaUtil.copyMediaToTempFile(container.$form(), selectionURI);
        }
        catch (IOException ioexception)
        {
            Log.i("ImagePicker", (new StringBuilder()).append("copyMediaToTempFile failed: ").append(ioexception.getMessage()).toString());
            Form form = container.$form();
            Object aobj[] = new Object[1];
            aobj[0] = ioexception.getMessage();
            form.dispatchErrorOccurredEvent(this, "ImagePicker", 1602, aobj);
            return;
        }
        Log.i("ImagePicker", (new StringBuilder()).append("temp file path is: ").append(file.getPath()).toString());
        copyToExternalStorageAndDeleteSource(file, s);
    }

    private void trimDirectory(int i, File file)
    {
        File afile[] = file.listFiles();
        Arrays.sort(afile, new Comparator() {

            final ImagePicker this$0;

            public int compare(File file1, File file2)
            {
                return Long.valueOf(file1.lastModified()).compareTo(Long.valueOf(file2.lastModified()));
            }

            public volatile int compare(Object obj, Object obj1)
            {
                return compare((File)obj, (File)obj1);
            }

            
            {
                this$0 = ImagePicker.this;
                super();
            }
        });
        int j = afile.length - i;
        for (int k = 0; k < j; k++)
        {
            afile[k].delete();
        }

    }

    public String Selection()
    {
        return selectionSavedImage;
    }

    protected Intent getIntent()
    {
        return new Intent("android.intent.action.PICK", android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
    }

    public void resultReturned(int i, int j, Intent intent)
    {
        if (i == requestCode && j == -1)
        {
            Uri uri = intent.getData();
            selectionURI = uri.toString();
            Log.i("ImagePicker", (new StringBuilder()).append("selectionURI = ").append(selectionURI).toString());
            ContentResolver contentresolver = container.$context().getContentResolver();
            MimeTypeMap mimetypemap = MimeTypeMap.getSingleton();
            String s = (new StringBuilder()).append(".").append(mimetypemap.getExtensionFromMimeType(contentresolver.getType(uri))).toString();
            Log.i("ImagePicker", (new StringBuilder()).append("extension = ").append(s).toString());
            saveSelectedImageToExternalStorage(s);
            AfterPicking();
        }
    }

}
