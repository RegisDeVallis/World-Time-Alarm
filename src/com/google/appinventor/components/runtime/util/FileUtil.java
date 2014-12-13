// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import android.os.Environment;
import com.google.appinventor.components.runtime.errors.RuntimeError;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

public class FileUtil
{
    public static class FileException extends RuntimeError
    {

        private final int msgNumber;

        public int getErrorMessageNumber()
        {
            return msgNumber;
        }

        public FileException(int i)
        {
            msgNumber = i;
        }
    }


    private static final String DIRECTORY_DOWNLOADS = "Downloads";
    private static final String DIRECTORY_PICTURES = "Pictures";
    private static final String DIRECTORY_RECORDINGS = "Recordings";
    private static final String DOCUMENT_DIRECTORY = "My Documents/";
    private static final String FILENAME_PREFIX = "app_inventor_";

    private FileUtil()
    {
    }

    public static void checkExternalStorageWriteable()
        throws FileException
    {
        String s = Environment.getExternalStorageState();
        if ("mounted".equals(s))
        {
            return;
        }
        if ("mounted_ro".equals(s))
        {
            throw new FileException(704);
        } else
        {
            throw new FileException(705);
        }
    }

    private static void copy(InputStream inputstream, OutputStream outputstream)
        throws IOException
    {
        BufferedOutputStream bufferedoutputstream = new BufferedOutputStream(outputstream, 4096);
        BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream, 4096);
        do
        {
            int i = bufferedinputstream.read();
            if (i == -1)
            {
                bufferedoutputstream.flush();
                return;
            }
            bufferedoutputstream.write(i);
        } while (true);
    }

    public static String copyFile(String s, String s1)
        throws IOException
    {
        FileInputStream fileinputstream = new FileInputStream(s);
        String s2 = writeStreamToFile(fileinputstream, s1);
        fileinputstream.close();
        return s2;
        Exception exception;
        exception;
        fileinputstream.close();
        throw exception;
    }

    public static String downloadUrlToFile(String s, String s1)
        throws IOException
    {
        InputStream inputstream = (new URL(s)).openStream();
        String s2 = writeStreamToFile(inputstream, s1);
        inputstream.close();
        return s2;
        Exception exception;
        exception;
        inputstream.close();
        throw exception;
    }

    public static File getDownloadFile(String s)
        throws IOException, FileException
    {
        return getFile("Downloads", s);
    }

    public static File getExternalFile(String s)
        throws IOException, FileException
    {
        checkExternalStorageWriteable();
        File file = new File(Environment.getExternalStorageDirectory(), s);
        File file1 = file.getParentFile();
        if (!file1.exists() && !file1.mkdirs())
        {
            throw new IOException((new StringBuilder()).append("Unable to create directory ").append(file1.getAbsolutePath()).toString());
        }
        if (file.exists() && !file.delete())
        {
            throw new IOException((new StringBuilder()).append("Cannot overwrite existing file ").append(file.getAbsolutePath()).toString());
        } else
        {
            return file;
        }
    }

    private static File getFile(String s, String s1)
        throws IOException, FileException
    {
        return getExternalFile((new StringBuilder()).append("My Documents/").append(s).append("/").append("app_inventor_").append(System.currentTimeMillis()).append(".").append(s1).toString());
    }

    public static String getFileUrl(String s)
    {
        return (new File(s)).toURI().toString();
    }

    public static File getPictureFile(String s)
        throws IOException, FileException
    {
        return getFile("Pictures", s);
    }

    public static File getRecordingFile(String s)
        throws IOException, FileException
    {
        return getFile("Recordings", s);
    }

    public static byte[] readFile(String s)
        throws IOException
    {
        ByteArrayOutputStream bytearrayoutputstream;
        FileInputStream fileinputstream;
        bytearrayoutputstream = new ByteArrayOutputStream();
        fileinputstream = new FileInputStream(s);
        copy(fileinputstream, bytearrayoutputstream);
        fileinputstream.close();
        return bytearrayoutputstream.toByteArray();
        Exception exception;
        exception;
        fileinputstream.close();
        throw exception;
    }

    public static String writeFile(byte abyte0[], String s)
        throws IOException
    {
        ByteArrayInputStream bytearrayinputstream = new ByteArrayInputStream(abyte0);
        String s1 = writeStreamToFile(bytearrayinputstream, s);
        bytearrayinputstream.close();
        return s1;
        Exception exception;
        exception;
        bytearrayinputstream.close();
        throw exception;
    }

    public static String writeStreamToFile(InputStream inputstream, String s)
        throws IOException
    {
        File file;
        FileOutputStream fileoutputstream;
        file = new File(s);
        file.getParentFile().mkdirs();
        fileoutputstream = new FileOutputStream(file);
        String s1;
        copy(inputstream, fileoutputstream);
        s1 = file.toURI().toString();
        fileoutputstream.flush();
        fileoutputstream.close();
        return s1;
        Exception exception;
        exception;
        fileoutputstream.flush();
        fileoutputstream.close();
        throw exception;
    }
}
