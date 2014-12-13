// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.util;

import java.io.File;
import java.io.PrintStream;

// Referenced classes of package gnu.kawa.util:
//            FileInfo

public class FixupHtmlToc
{

    static FileInfo argFiles[];

    public FixupHtmlToc()
    {
    }

    public static void main(String args[])
    {
        argFiles = new FileInfo[args.length];
        int i = 0;
_L2:
        if (i >= args.length)
        {
            break; /* Loop/switch isn't completed */
        }
        FileInfo fileinfo = FileInfo.find(new File(args[i]));
        fileinfo.writeNeeded = true;
        argFiles[i] = fileinfo;
        i++;
        if (true) goto _L2; else goto _L1
_L4:
        int j;
        if (j >= args.length)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        argFiles[j].scan();
        argFiles[j].write();
        j++;
        continue; /* Loop/switch isn't completed */
        Throwable throwable;
        throwable;
        System.err.println((new StringBuilder()).append("caught ").append(throwable).toString());
        throwable.printStackTrace();
        return;
_L1:
        j = 0;
        if (true) goto _L4; else goto _L3
_L3:
    }
}
