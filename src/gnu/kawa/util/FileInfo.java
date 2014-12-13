// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.util;

import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.text.Path;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URI;
import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class FileInfo
{

    static final Pattern childPat = Pattern.compile("<a .*</a>");
    static Hashtable fileMap = new Hashtable();
    static final Pattern linkPat = Pattern.compile(" href=['\"]([^'\"]*)['\"]");
    static final Pattern parentPat = Pattern.compile("<ul[^>]* parent=['\"]([^'\"]*)['\"]");
    StringBuffer beforeNavbarText;
    ByteArrayOutputStream bout;
    String childLinkText[];
    OutPort cout;
    File file;
    FileInputStream fin;
    InPort in;
    int nchildren;
    StringBuffer newNavbarText;
    StringBuffer oldNavbarText;
    FileInfo parent;
    String parentName;
    String path;
    boolean scanned;
    boolean writeNeeded;

    FileInfo()
    {
    }

    public static FileInfo find(File file1)
        throws Throwable
    {
        String s = file1.getCanonicalPath();
        FileInfo fileinfo = (FileInfo)fileMap.get(s);
        if (fileinfo == null)
        {
            fileinfo = new FileInfo();
            fileinfo.file = file1;
            fileMap.put(s, fileinfo);
        }
        return fileinfo;
    }

    public void scan()
        throws Throwable
    {
        if (!scanned) goto _L2; else goto _L1
_L1:
        return;
_L2:
        boolean flag;
        boolean flag1;
        Vector vector;
        scanned = true;
        fin = new FileInputStream(file);
        in = new InPort(new BufferedInputStream(fin));
        oldNavbarText = new StringBuffer();
        newNavbarText = new StringBuffer();
        if (writeNeeded)
        {
            bout = new ByteArrayOutputStream();
            cout = new OutPort(bout);
        }
        flag = false;
        flag1 = false;
        vector = new Vector();
_L6:
        String s = in.readLine();
        if (s != null) goto _L4; else goto _L3
_L3:
        String as[] = new String[vector.size()];
        nchildren = as.length;
        vector.copyInto(as);
        childLinkText = as;
        if (!writeNeeded)
        {
            in.close();
        }
        if (parentName != null)
        {
            FileInfo fileinfo = find(new File(file.toURI().resolve(parentName)));
            fileinfo.scan();
            parent = fileinfo;
            return;
        }
          goto _L1
_L4:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_372;
        }
        if (s.indexOf("<!--end-generated-navbar-->") < 0) goto _L5; else goto _L3
_L5:
        oldNavbarText.append(s);
        oldNavbarText.append('\n');
        if (flag1)
        {
            if (s.indexOf("<!--end-children-toc-->") >= 0)
            {
                flag1 = false;
            } else
            {
                Matcher matcher = childPat.matcher(s);
                if (matcher.find())
                {
                    vector.add(matcher.group());
                }
                Matcher matcher1 = parentPat.matcher(s);
                if (matcher1.find() && parentName == null)
                {
                    parentName = matcher1.group(1);
                }
            }
        }
        if (s.indexOf("<!--start-children-toc-->") >= 0)
        {
            flag1 = true;
        }
_L7:
        if (writeNeeded && !flag)
        {
            cout.println(s);
        }
          goto _L6
        if (s.indexOf("<!--start-generated-navbar-->") >= 0)
        {
            flag = true;
        }
          goto _L7
    }

    public void write()
        throws Throwable
    {
        int i = 0;
        for (FileInfo fileinfo = this; fileinfo.parent != null;)
        {
            fileinfo = fileinfo.parent;
            i++;
        }

        cout.println("<!--start-generated-navbar-->");
        writeLinks(i, newNavbarText);
        cout.print(newNavbarText);
        cout.println("<!--end-generated-navbar-->");
        do
        {
            String s = in.readLine();
            if (s == null)
            {
                new StringBuffer();
                in.close();
                if (oldNavbarText.toString().equals(newNavbarText.toString()))
                {
                    System.err.println((new StringBuilder()).append("fixup ").append(file).append(" - no change").toString());
                    return;
                } else
                {
                    FileOutputStream fileoutputstream = new FileOutputStream(file);
                    fileoutputstream.write(bout.toByteArray());
                    fileoutputstream.close();
                    System.err.println((new StringBuilder()).append("fixup ").append(file).append(" - updated").toString());
                    return;
                }
            }
            cout.println(s);
        } while (true);
    }

    public void writeLinks(int i, StringBuffer stringbuffer)
        throws Throwable
    {
        String s;
        boolean flag;
        FileInfo fileinfo = this;
        FileInfo fileinfo1 = null;
        for (int j = i; --j >= 0;)
        {
            fileinfo1 = fileinfo;
            fileinfo = fileinfo.parent;
        }

        if (i == 0)
        {
            stringbuffer.append("<!--start-children-toc-->\n");
        }
        URI uri;
        URI uri1;
        int k;
        Matcher matcher;
        String s1;
        URI uri2;
        int l;
        if (i == 0 && parentName != null)
        {
            stringbuffer.append("<ul parent=\"");
            stringbuffer.append(parentName);
            stringbuffer.append("\">\n");
        } else
        {
            stringbuffer.append("<ul>\n");
        }
        uri = file.toURI();
        uri1 = fileinfo.file.toURI();
        k = 0;
_L7:
        if (k >= fileinfo.nchildren) goto _L2; else goto _L1
_L1:
        s = fileinfo.childLinkText[k];
        flag = false;
        if (i <= 0) goto _L4; else goto _L3
_L3:
        matcher = linkPat.matcher(s);
        matcher.find();
        s1 = matcher.group(1);
        uri2 = uri1.resolve(s1);
        s = matcher.replaceFirst((new StringBuilder()).append(" href=\"").append(Path.relativize(uri2.toString(), uri.toString())).append("\"").toString());
        l = s1.indexOf('#');
        if (l >= 0)
        {
            s1 = s1.substring(0, l);
        }
        if (find(new File(uri1.resolve(s1))) == fileinfo1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag || i <= 1) goto _L4; else goto _L5
_L5:
        k++;
        continue; /* Loop/switch isn't completed */
_L4:
        stringbuffer.append("<li>");
        stringbuffer.append(s);
        if (flag)
        {
            stringbuffer.append('\n');
            writeLinks(i - 1, stringbuffer);
        }
        stringbuffer.append("</li>\n");
        if (true) goto _L5; else goto _L2
_L2:
        stringbuffer.append("</ul>\n");
        if (i == 0)
        {
            stringbuffer.append("<!--end-children-toc-->\n");
        }
        return;
        if (true) goto _L7; else goto _L6
_L6:
    }

}
