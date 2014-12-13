// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import java.io.File;
import java.io.IOException;

public class FileUtils
{

    public FileUtils()
    {
    }

    public static File createTempFile(String s)
        throws IOException
    {
        if (s == null)
        {
            s = "kawa~d.tmp";
        }
        int i = s.indexOf('~');
        String s1;
        String s2;
        int j;
        File file;
        if (i < 0)
        {
            s1 = s;
            s2 = ".tmp";
        } else
        {
            s1 = s.substring(0, i);
            s2 = s.substring(i + 2);
        }
        j = s1.indexOf(File.separatorChar);
        file = null;
        if (j >= 0)
        {
            file = new File(s1.substring(0, j));
            s1 = s1.substring(j + 1);
        }
        return File.createTempFile(s1, s2, file);
    }
}
