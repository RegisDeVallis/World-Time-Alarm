// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import android.content.ContentResolver;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.VideoView;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.ReplForm;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.appinventor.components.runtime.util:
//            FileUtil, SdkLevel, HoneycombUtil

public class MediaUtil
{
    private static class FlushedInputStream extends FilterInputStream
    {

        public long skip(long l)
            throws IOException
        {
            long l1 = 0L;
            do
            {
                long l2;
label0:
                {
label1:
                    {
                        if (l1 < l)
                        {
                            l2 = in.skip(l - l1);
                            if (l2 != 0L)
                            {
                                break label0;
                            }
                            if (read() >= 0)
                            {
                                break label1;
                            }
                        }
                        return l1;
                    }
                    l2 = 1L;
                }
                l1 += l2;
            } while (true);
        }

        public FlushedInputStream(InputStream inputstream)
        {
            super(inputstream);
        }
    }

    private static final class MediaSource extends Enum
    {

        private static final MediaSource $VALUES[];
        public static final MediaSource ASSET;
        public static final MediaSource CONTACT_URI;
        public static final MediaSource CONTENT_URI;
        public static final MediaSource FILE_URL;
        public static final MediaSource REPL_ASSET;
        public static final MediaSource SDCARD;
        public static final MediaSource URL;

        public static MediaSource valueOf(String s)
        {
            return (MediaSource)Enum.valueOf(com/google/appinventor/components/runtime/util/MediaUtil$MediaSource, s);
        }

        public static MediaSource[] values()
        {
            return (MediaSource[])$VALUES.clone();
        }

        static 
        {
            ASSET = new MediaSource("ASSET", 0);
            REPL_ASSET = new MediaSource("REPL_ASSET", 1);
            SDCARD = new MediaSource("SDCARD", 2);
            FILE_URL = new MediaSource("FILE_URL", 3);
            URL = new MediaSource("URL", 4);
            CONTENT_URI = new MediaSource("CONTENT_URI", 5);
            CONTACT_URI = new MediaSource("CONTACT_URI", 6);
            MediaSource amediasource[] = new MediaSource[7];
            amediasource[0] = ASSET;
            amediasource[1] = REPL_ASSET;
            amediasource[2] = SDCARD;
            amediasource[3] = FILE_URL;
            amediasource[4] = URL;
            amediasource[5] = CONTENT_URI;
            amediasource[6] = CONTACT_URI;
            $VALUES = amediasource;
        }

        private MediaSource(String s, int i)
        {
            super(s, i);
        }
    }


    private static final String LOG_TAG = "MediaUtil";
    private static final String REPL_ASSET_DIR = "/sdcard/AppInventor/assets/";
    private static final Map tempFileMap = new HashMap();

    private MediaUtil()
    {
    }

    private static File cacheMediaTempFile(Form form, String s, MediaSource mediasource)
        throws IOException
    {
        File file = (File)tempFileMap.get(s);
        if (file == null || !file.exists())
        {
            Log.i("MediaUtil", (new StringBuilder()).append("Copying media ").append(s).append(" to temp file...").toString());
            file = copyMediaToTempFile(form, s, mediasource);
            Log.i("MediaUtil", (new StringBuilder()).append("Finished copying media ").append(s).append(" to temp file ").append(file.getAbsolutePath()).toString());
            tempFileMap.put(s, file);
        }
        return file;
    }

    public static File copyMediaToTempFile(Form form, String s)
        throws IOException
    {
        return copyMediaToTempFile(form, s, determineMediaSource(form, s));
    }

    private static File copyMediaToTempFile(Form form, String s, MediaSource mediasource)
        throws IOException
    {
        InputStream inputstream;
        File file;
        inputstream = openMedia(form, s, mediasource);
        file = null;
        file = File.createTempFile("AI_Media_", null);
        file.deleteOnExit();
        FileUtil.writeStreamToFile(inputstream, file.getAbsolutePath());
        inputstream.close();
        return file;
        IOException ioexception;
        ioexception;
        if (file == null)
        {
            break MISSING_BLOCK_LABEL_103;
        }
        Log.e("MediaUtil", (new StringBuilder()).append("Could not copy media ").append(s).append(" to temp file ").append(file.getAbsolutePath()).toString());
        file.delete();
_L1:
        throw ioexception;
        Exception exception;
        exception;
        inputstream.close();
        throw exception;
        Log.e("MediaUtil", (new StringBuilder()).append("Could not copy media ").append(s).append(" to temp file.").toString());
          goto _L1
    }

    private static Bitmap decodeStream(InputStream inputstream, Rect rect, android.graphics.BitmapFactory.Options options)
    {
        return BitmapFactory.decodeStream(new FlushedInputStream(inputstream), rect, options);
    }

    private static MediaSource determineMediaSource(Form form, String s)
    {
        if (s.startsWith("/sdcard/") || s.startsWith(Environment.getExternalStorageDirectory().getAbsolutePath()))
        {
            return MediaSource.SDCARD;
        }
        if (s.startsWith("content://contacts/"))
        {
            return MediaSource.CONTACT_URI;
        }
        if (s.startsWith("content://"))
        {
            return MediaSource.CONTENT_URI;
        }
        MediaSource mediasource;
        try
        {
            new URL(s);
            if (s.startsWith("file:"))
            {
                return MediaSource.FILE_URL;
            }
            mediasource = com.google.appinventor.components.runtime.util.MediaSource.URL;
        }
        catch (MalformedURLException malformedurlexception)
        {
            if (form instanceof ReplForm)
            {
                if (((ReplForm)form).isAssetsLoaded())
                {
                    return MediaSource.REPL_ASSET;
                } else
                {
                    return MediaSource.ASSET;
                }
            } else
            {
                return MediaSource.ASSET;
            }
        }
        return mediasource;
    }

    static String fileUrlToFilePath(String s)
        throws IOException
    {
        String s1;
        try
        {
            s1 = (new File((new URL(s)).toURI())).getAbsolutePath();
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            throw new IOException((new StringBuilder()).append("Unable to determine file path of file url ").append(s).toString());
        }
        catch (Exception exception)
        {
            throw new IOException((new StringBuilder()).append("Unable to determine file path of file url ").append(s).toString());
        }
        return s1;
    }

    private static String findCaseinsensitivePath(Form form, String s)
        throws IOException
    {
        String as[] = form.getAssets().list("");
        int i = Array.getLength(as);
        for (int j = 0; j < i; j++)
        {
            String s1 = as[j];
            if (s1.equalsIgnoreCase(s))
            {
                return s1;
            }
        }

        return null;
    }

    private static AssetFileDescriptor getAssetsIgnoreCaseAfd(Form form, String s)
        throws IOException
    {
        AssetFileDescriptor assetfiledescriptor;
        try
        {
            assetfiledescriptor = form.getAssets().openFd(s);
        }
        catch (IOException ioexception)
        {
            if (findCaseinsensitivePath(form, s) == null)
            {
                throw ioexception;
            } else
            {
                String s1 = findCaseinsensitivePath(form, s);
                return form.getAssets().openFd(s1);
            }
        }
        return assetfiledescriptor;
    }

    private static InputStream getAssetsIgnoreCaseInputStream(Form form, String s)
        throws IOException
    {
        InputStream inputstream;
        try
        {
            inputstream = form.getAssets().open(s);
        }
        catch (IOException ioexception)
        {
            if (findCaseinsensitivePath(form, s) == null)
            {
                throw ioexception;
            } else
            {
                String s1 = findCaseinsensitivePath(form, s);
                return form.getAssets().open(s1);
            }
        }
        return inputstream;
    }

    public static BitmapDrawable getBitmapDrawable(Form form, String s)
        throws IOException
    {
        if (s != null && s.length() != 0) goto _L2; else goto _L1
_L1:
        BitmapDrawable bitmapdrawable = null;
_L4:
        return bitmapdrawable;
_L2:
        InputStream inputstream;
        InputStream inputstream1;
        MediaSource mediasource = determineMediaSource(form, s);
        android.graphics.BitmapFactory.Options options;
        try
        {
            inputstream = openMedia(form, s, mediasource);
        }
        catch (IOException ioexception)
        {
            if (mediasource == MediaSource.CONTACT_URI)
            {
                return new BitmapDrawable(BitmapFactory.decodeResource(form.getResources(), 0x1080066, null));
            } else
            {
                throw ioexception;
            }
        }
        options = getBitmapOptions(form, inputstream);
        inputstream.close();
        inputstream1 = openMedia(form, s, mediasource);
        bitmapdrawable = new BitmapDrawable(decodeStream(inputstream1, null, options));
        if (inputstream1 == null) goto _L4; else goto _L3
_L3:
        inputstream1.close();
        return bitmapdrawable;
        Exception exception;
        exception;
        inputstream.close();
        throw exception;
        Exception exception1;
        exception1;
        if (inputstream1 != null)
        {
            inputstream1.close();
        }
        throw exception1;
    }

    private static android.graphics.BitmapFactory.Options getBitmapOptions(Form form, InputStream inputstream)
    {
        android.graphics.BitmapFactory.Options options = new android.graphics.BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        decodeStream(inputstream, null, options);
        int i = options.outWidth;
        int j = options.outHeight;
        Display display = ((WindowManager)form.getSystemService("window")).getDefaultDisplay();
        int k = 2 * display.getWidth();
        int l = 2 * display.getHeight();
        int i1;
        for (i1 = 1; i / i1 > k && j / i1 > l; i1 *= 2) { }
        android.graphics.BitmapFactory.Options options1 = new android.graphics.BitmapFactory.Options();
        options1.inSampleSize = i1;
        return options1;
    }

    public static void loadMediaPlayer(MediaPlayer mediaplayer, Form form, String s)
        throws IOException
    {
        MediaSource mediasource = determineMediaSource(form, s);
        static class _cls1
        {

            static final int $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[];

            static 
            {
                $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource = new int[MediaSource.values().length];
                try
                {
                    $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[MediaSource.ASSET.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[MediaSource.REPL_ASSET.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[MediaSource.SDCARD.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[MediaSource.FILE_URL.ordinal()] = 4;
                }
                catch (NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[com.google.appinventor.components.runtime.util.MediaSource.URL.ordinal()] = 5;
                }
                catch (NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[MediaSource.CONTENT_URI.ordinal()] = 6;
                }
                catch (NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$google$appinventor$components$runtime$util$MediaUtil$MediaSource[MediaSource.CONTACT_URI.ordinal()] = 7;
                }
                catch (NoSuchFieldError nosuchfielderror6)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.com.google.appinventor.components.runtime.util.MediaUtil.MediaSource[mediasource.ordinal()];
        JVM INSTR tableswitch 1 7: default 56
    //                   1 90
    //                   2 132
    //                   3 141
    //                   4 147
    //                   5 156
    //                   6 162
    //                   7 172;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        throw new IOException((new StringBuilder()).append("Unable to load audio or video ").append(s).append(".").toString());
_L2:
        AssetFileDescriptor assetfiledescriptor = getAssetsIgnoreCaseAfd(form, s);
        mediaplayer.setDataSource(assetfiledescriptor.getFileDescriptor(), assetfiledescriptor.getStartOffset(), assetfiledescriptor.getLength());
        assetfiledescriptor.close();
        return;
        Exception exception;
        exception;
        assetfiledescriptor.close();
        throw exception;
_L3:
        mediaplayer.setDataSource(replAssetPath(s));
        return;
_L4:
        mediaplayer.setDataSource(s);
        return;
_L5:
        mediaplayer.setDataSource(fileUrlToFilePath(s));
        return;
_L6:
        mediaplayer.setDataSource(s);
        return;
_L7:
        mediaplayer.setDataSource(form, Uri.parse(s));
        return;
_L8:
        throw new IOException((new StringBuilder()).append("Unable to load audio or video for contact ").append(s).append(".").toString());
    }

    public static int loadSoundPool(SoundPool soundpool, Form form, String s)
        throws IOException
    {
        MediaSource mediasource = determineMediaSource(form, s);
        switch (_cls1..SwitchMap.com.google.appinventor.components.runtime.util.MediaUtil.MediaSource[mediasource.ordinal()])
        {
        default:
            throw new IOException((new StringBuilder()).append("Unable to load audio ").append(s).append(".").toString());

        case 1: // '\001'
            return soundpool.load(getAssetsIgnoreCaseAfd(form, s), 1);

        case 2: // '\002'
            return soundpool.load(replAssetPath(s), 1);

        case 3: // '\003'
            return soundpool.load(s, 1);

        case 4: // '\004'
            return soundpool.load(fileUrlToFilePath(s), 1);

        case 5: // '\005'
        case 6: // '\006'
            return soundpool.load(cacheMediaTempFile(form, s, mediasource).getAbsolutePath(), 1);

        case 7: // '\007'
            throw new IOException((new StringBuilder()).append("Unable to load audio for contact ").append(s).append(".").toString());
        }
    }

    public static void loadVideoView(VideoView videoview, Form form, String s)
        throws IOException
    {
        MediaSource mediasource = determineMediaSource(form, s);
        switch (_cls1..SwitchMap.com.google.appinventor.components.runtime.util.MediaUtil.MediaSource[mediasource.ordinal()])
        {
        default:
            throw new IOException((new StringBuilder()).append("Unable to load video ").append(s).append(".").toString());

        case 1: // '\001'
        case 5: // '\005'
            videoview.setVideoPath(cacheMediaTempFile(form, s, mediasource).getAbsolutePath());
            return;

        case 2: // '\002'
            videoview.setVideoPath(replAssetPath(s));
            return;

        case 3: // '\003'
            videoview.setVideoPath(s);
            return;

        case 4: // '\004'
            videoview.setVideoPath(fileUrlToFilePath(s));
            return;

        case 6: // '\006'
            videoview.setVideoURI(Uri.parse(s));
            return;

        case 7: // '\007'
            throw new IOException((new StringBuilder()).append("Unable to load video for contact ").append(s).append(".").toString());
        }
    }

    public static InputStream openMedia(Form form, String s)
        throws IOException
    {
        return openMedia(form, s, determineMediaSource(form, s));
    }

    private static InputStream openMedia(Form form, String s, MediaSource mediasource)
        throws IOException
    {
        _cls1..SwitchMap.com.google.appinventor.components.runtime.util.MediaUtil.MediaSource[mediasource.ordinal()];
        JVM INSTR tableswitch 1 7: default 52
    //                   1 86
    //                   2 94
    //                   3 106
    //                   4 115
    //                   5 115
    //                   6 127
    //                   7 139;
           goto _L1 _L2 _L3 _L4 _L5 _L5 _L6 _L7
_L1:
        throw new IOException((new StringBuilder()).append("Unable to open media ").append(s).append(".").toString());
_L2:
        InputStream inputstream = getAssetsIgnoreCaseInputStream(form, s);
_L9:
        return inputstream;
_L3:
        return new FileInputStream(replAssetPath(s));
_L4:
        return new FileInputStream(s);
_L5:
        return (new URL(s)).openStream();
_L6:
        return form.getContentResolver().openInputStream(Uri.parse(s));
_L7:
        if (SdkLevel.getLevel() >= 12)
        {
            inputstream = HoneycombUtil.openContactPhotoInputStreamHelper(form.getContentResolver(), Uri.parse(s));
        } else
        {
            inputstream = android.provider.Contacts.People.openContactPhotoInputStream(form.getContentResolver(), Uri.parse(s));
        }
        if (inputstream == null)
        {
            throw new IOException((new StringBuilder()).append("Unable to open contact photo ").append(s).append(".").toString());
        }
        if (true) goto _L9; else goto _L8
_L8:
    }

    private static String replAssetPath(String s)
    {
        return (new StringBuilder()).append("/sdcard/AppInventor/assets/").append(s).toString();
    }

}
