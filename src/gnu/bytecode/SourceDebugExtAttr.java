// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package gnu.bytecode:
//            Attribute, SourceFileAttr, ClassTypeWriter, ClassType

public class SourceDebugExtAttr extends Attribute
{

    int curFileIndex;
    String curFileName;
    int curLineIndex;
    byte data[];
    private String defaultStratumId;
    int dlength;
    int fileCount;
    int fileIDs[];
    String fileNames[];
    int lineCount;
    int lines[];
    int maxFileID;
    private String outputFileName;

    public SourceDebugExtAttr(ClassType classtype)
    {
        super("SourceDebugExtension");
        curLineIndex = -1;
        curFileIndex = -1;
        addToFrontOf(classtype);
    }

    private int fixLine(int i, int j)
    {
        int k;
        int l;
        k = lines[j];
        l = lines[j + 2];
        if (i >= k) goto _L2; else goto _L1
_L1:
        if (j <= 0) goto _L4; else goto _L3
_L3:
        return -1;
_L4:
        int j1 = -1 + (k + l);
        lines[j] = i;
        lines[j + 2] = 1 + (j1 - i);
        lines[j + 3] = i;
        k = i;
_L2:
        int i1 = lines[j + 3] - k;
        if (i < k + l)
        {
            return i + i1;
        }
        if (j == 5 * (-1 + lineCount) || j == 0 && i < lines[8])
        {
            lines[j + 2] = 1 + (i - k);
            return i + i1;
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    void addFile(String s)
    {
        if (curFileName != s && (s == null || !s.equals(curFileName))) goto _L2; else goto _L1
_L1:
        return;
_L2:
        String s1;
        int i;
        String s2;
        int j;
        curFileName = s;
        s1 = SourceFileAttr.fixSourceFile(s);
        i = s1.lastIndexOf('/');
        if (i >= 0)
        {
            String s3 = s1;
            s1 = s1.substring(i + 1);
            s2 = (new StringBuilder()).append(s1).append('\n').append(s3).toString();
        } else
        {
            s2 = s1;
        }
        if (curFileIndex >= 0 && s2.equals(fileNames[curFileIndex])) goto _L1; else goto _L3
_L3:
        j = fileCount;
        for (int k = 0; k < j; k++)
        {
            if (k != curFileIndex && s2.equals(fileNames[k]))
            {
                curFileIndex = k;
                curLineIndex = -1;
                return;
            }
        }

        if (fileIDs != null) goto _L5; else goto _L4
_L4:
        fileIDs = new int[5];
        fileNames = new String[5];
_L7:
        fileCount = 1 + fileCount;
        int l = 1 + maxFileID;
        maxFileID = l;
        int i1 = l << 1;
        if (i >= 0)
        {
            i1++;
        }
        fileNames[j] = s2;
        if (outputFileName == null)
        {
            outputFileName = s1;
        }
        fileIDs[j] = i1;
        curFileIndex = j;
        curLineIndex = -1;
        return;
_L5:
        if (j >= fileIDs.length)
        {
            int ai[] = new int[j * 2];
            String as[] = new String[j * 2];
            System.arraycopy(fileIDs, 0, ai, 0, j);
            System.arraycopy(fileNames, 0, as, 0, j);
            fileIDs = ai;
            fileNames = as;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    public void addStratum(String s)
    {
        defaultStratumId = s;
    }

    public void assignConstants(ClassType classtype)
    {
        super.assignConstants(classtype);
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("SMAP\n");
        nonAsteriskString(outputFileName, stringbuffer);
        stringbuffer.append('\n');
        String s;
        int i;
        if (defaultStratumId == null)
        {
            s = "Java";
        } else
        {
            s = defaultStratumId;
        }
        nonAsteriskString(s, stringbuffer);
        stringbuffer.append('\n');
        stringbuffer.append("*S ");
        stringbuffer.append(s);
        stringbuffer.append('\n');
        stringbuffer.append("*F\n");
        i = 0;
        while (i < fileCount) 
        {
            int j2 = fileIDs[i];
            boolean flag;
            int k2;
            if ((j2 & 1) != 0)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            k2 = j2 >> 1;
            if (flag)
            {
                stringbuffer.append("+ ");
            }
            stringbuffer.append(k2);
            stringbuffer.append(' ');
            stringbuffer.append(fileNames[i]);
            stringbuffer.append('\n');
            i++;
        }
        if (lineCount > 0)
        {
            int j = 0;
            stringbuffer.append("*L\n");
            int k = 0;
            int l = 0;
            do
            {
                int i1 = lines[l];
                int j1 = fileIDs[lines[l + 1]] >> 1;
                int k1 = lines[l + 2];
                int l1 = lines[l + 3];
                int i2 = lines[l + 4];
                stringbuffer.append(i1);
                if (j1 != j)
                {
                    stringbuffer.append('#');
                    stringbuffer.append(j1);
                    j = j1;
                }
                if (k1 != 1)
                {
                    stringbuffer.append(',');
                    stringbuffer.append(k1);
                }
                stringbuffer.append(':');
                stringbuffer.append(l1);
                if (i2 != 1)
                {
                    stringbuffer.append(',');
                    stringbuffer.append(i2);
                }
                stringbuffer.append('\n');
                l += 5;
            } while (++k < lineCount);
        }
        stringbuffer.append("*E\n");
        try
        {
            data = stringbuffer.toString().getBytes("UTF-8");
        }
        catch (Exception exception)
        {
            throw new RuntimeException(exception.toString());
        }
        dlength = data.length;
    }

    int fixLine(int i)
    {
        if (curLineIndex >= 0)
        {
            int l1 = fixLine(i, curLineIndex);
            if (l1 >= 0)
            {
                return l1;
            }
        }
        int j = 0;
        int k = curFileIndex;
        for (int l = 0; l < lineCount; l++)
        {
            if (j != curLineIndex && k == lines[j + 1])
            {
                int k1 = fixLine(i, j);
                if (k1 >= 0)
                {
                    curLineIndex = j;
                    return k1;
                }
            }
            j += 5;
        }

        int i1;
        int j1;
        if (lines == null)
        {
            lines = new int[20];
        } else
        if (j >= lines.length)
        {
            int ai[] = new int[j * 2];
            System.arraycopy(lines, 0, ai, 0, j);
            lines = ai;
        }
        i1 = i;
        if (j == 0)
        {
            j1 = i;
        } else
        {
            j1 = lines[3 + (j - 5)] + lines[2 + (j - 5)];
            if (j == 5 && j1 < 10000)
            {
                j1 = 10000;
            }
            i = j1;
        }
        lines[j] = i1;
        lines[j + 1] = k;
        lines[j + 2] = 1;
        lines[j + 3] = j1;
        lines[j + 4] = 1;
        curLineIndex = j;
        lineCount = 1 + lineCount;
        return i;
    }

    public int getLength()
    {
        return dlength;
    }

    void nonAsteriskString(String s, StringBuffer stringbuffer)
    {
        if (s == null || s.length() == 0 || s.charAt(0) == '*')
        {
            stringbuffer.append(' ');
        }
        stringbuffer.append(s);
    }

    public void print(ClassTypeWriter classtypewriter)
    {
        classtypewriter.print("Attribute \"");
        classtypewriter.print(getName());
        classtypewriter.print("\", length:");
        classtypewriter.println(dlength);
        try
        {
            classtypewriter.print(new String(data, 0, dlength, "UTF-8"));
        }
        catch (Exception exception)
        {
            classtypewriter.print("(Caught ");
            classtypewriter.print(exception);
            classtypewriter.println(')');
        }
        if (dlength > 0 && data[-1 + dlength] != 13 && data[-1 + dlength] != 10)
        {
            classtypewriter.println();
        }
    }

    public void write(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.write(data, 0, dlength);
    }
}
