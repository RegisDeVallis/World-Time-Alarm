// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;

import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.Writer;

// Referenced classes of package gnu.lists:
//            StableVector, CharSeq, FString, Consumer, 
//            SimpleVector, SubCharSeq

public class CharBuffer extends StableVector
    implements CharSeq, Serializable
{

    private FString string;

    protected CharBuffer()
    {
    }

    public CharBuffer(int i)
    {
        this(new FString(i));
    }

    public CharBuffer(FString fstring)
    {
        super(fstring);
        string = fstring;
    }

    public char charAt(int i)
    {
        if (i >= gapStart)
        {
            i += gapEnd - gapStart;
        }
        return string.charAt(i);
    }

    public void consume(int i, int j, Consumer consumer)
    {
        char ac[] = string.data;
        if (i < gapStart)
        {
            int k = gapStart - i;
            if (k > j)
            {
                k = j;
            }
            consumer.write(ac, i, k);
            j -= k;
            i += j;
        }
        if (j > 0)
        {
            consumer.write(ac, i + (gapEnd - gapStart), j);
        }
    }

    public void delete(int i, int j)
    {
        int k = createPos(i, false);
        removePos(k, j);
        releasePos(k);
    }

    public void dump()
    {
        System.err.println((new StringBuilder()).append("Buffer Content dump.  size:").append(size()).append("  buffer:").append(getArray().length).toString());
        System.err.print("before gap: \"");
        System.err.print(new String(getArray(), 0, gapStart));
        System.err.println((new StringBuilder()).append("\" (gapStart:").append(gapStart).append(" gapEnd:").append(gapEnd).append(')').toString());
        System.err.print("after gap: \"");
        System.err.print(new String(getArray(), gapEnd, getArray().length - gapEnd));
        System.err.println("\"");
        int ai[] = positions;
        int i = 0;
        int j;
        boolean aflag[];
        if (ai != null)
        {
            i = positions.length;
        }
        System.err.println((new StringBuilder()).append("Positions (size: ").append(i).append(" free:").append(free).append("):").toString());
        j = free;
        aflag = null;
        if (j != -2)
        {
            aflag = new boolean[positions.length];
            for (int i1 = free; i1 >= 0; i1 = positions[i1])
            {
                aflag[i1] = true;
            }

        }
        int k = 0;
        while (k < i) 
        {
            int l = positions[k];
            if (free != -2 ? !aflag[k] : l != -2)
            {
                System.err.println((new StringBuilder()).append("position#").append(k).append(": ").append(l >> 1).append(" isAfter:").append(l & 1).toString());
            }
            k++;
        }
    }

    public final void fill(char c)
    {
        char ac[] = string.data;
        for (int i = ac.length; --i >= gapEnd;)
        {
            ac[i] = c;
        }

        for (int j = gapStart; --j >= 0;)
        {
            ac[j] = c;
        }

    }

    public void fill(int i, int j, char c)
    {
        char ac[] = string.data;
        int k = i;
        int l;
        if (gapStart < j)
        {
            l = gapStart;
        } else
        {
            l = j;
        }
        for (; k < l; k++)
        {
            ac[k] = c;
        }

        int i1 = l + (gapEnd - gapStart);
        for (int j1 = l + j; i1 < j1; i1++)
        {
            ac[i1] = c;
        }

    }

    public char[] getArray()
    {
        return (char[])(char[])base.getBuffer();
    }

    public void getChars(int i, int j, char ac[], int k)
    {
        char ac1[] = string.data;
        if (i < gapStart)
        {
            int l;
            int i1;
            int j1;
            int k1;
            int l1;
            if (j < gapStart)
            {
                k1 = j;
            } else
            {
                k1 = gapStart;
            }
            l1 = k1 - i;
            if (l1 > 0)
            {
                System.arraycopy(ac1, i, ac, k, l1);
                i += l1;
                k += l1;
            }
        }
        l = gapEnd - gapStart;
        i1 = i + l;
        j1 = (j + l) - i1;
        if (j1 > 0)
        {
            System.arraycopy(ac1, i1, ac, k, j1);
        }
    }

    public int indexOf(int i, int j)
    {
        char c;
        char c1;
        char ac[];
        int k;
        int l;
        if (i >= 0x10000)
        {
            c = (char)(55296 + (i - 0x10000 >> 10));
            c1 = (char)(56320 + (i & 0x3ff));
        } else
        {
            c = (char)i;
            c1 = '\0';
        }
        ac = getArray();
        k = j;
        l = gapStart;
        if (k >= l)
        {
            k += gapEnd - gapStart;
            l = ac.length;
        }
        do
        {
            if (k == l)
            {
                l = ac.length;
                if (k >= l)
                {
                    break;
                }
                k = gapEnd;
            }
            if (ac[k] == c && (c1 == 0 || (k + 1 >= l ? gapEnd < ac.length && ac[gapEnd] == c1 : ac[k + 1] == c1)))
            {
                if (k > gapStart)
                {
                    return k - (gapEnd - gapStart);
                } else
                {
                    return k;
                }
            }
            k++;
        } while (true);
        return -1;
    }

    public void insert(int i, String s, boolean flag)
    {
        int j = s.length();
        gapReserve(i, j);
        s.getChars(0, j, string.data, i);
        gapStart = j + gapStart;
    }

    public int lastIndexOf(int i, int j)
    {
        char c;
        char c1;
        int k;
        if (i >= 0x10000)
        {
            c1 = (char)(55296 + (i - 0x10000 >> 10));
            c = (char)(56320 + (i & 0x3ff));
        } else
        {
            c = (char)i;
            c1 = '\0';
        }
        for (k = j; --k >= 0;)
        {
            if (charAt(k) == c)
            {
                if (c1 == 0)
                {
                    return k;
                }
                if (k > 0 && charAt(k - 1) == c1)
                {
                    return k - 1;
                }
            }
        }

        return -1;
    }

    public int length()
    {
        return size();
    }

    public void setCharAt(int i, char c)
    {
        if (i >= gapStart)
        {
            i += gapEnd - gapStart;
        }
        string.setCharAt(i, c);
    }

    public CharSequence subSequence(int i, int j)
    {
        int k = size();
        if (i < 0 || j < i || j > k)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            return new SubCharSeq(this, base.createPos(i, false), base.createPos(j, true));
        }
    }

    public String substring(int i, int j)
    {
        int k = size();
        if (i < 0 || j < i || j > k)
        {
            throw new IndexOutOfBoundsException();
        } else
        {
            int l = j - i;
            int i1 = getSegment(i, l);
            return new String(getArray(), i1, l);
        }
    }

    public String toString()
    {
        int i = size();
        int j = getSegment(0, i);
        return new String(getArray(), j, i);
    }

    public void writeTo(int i, int j, Writer writer)
        throws IOException
    {
        char ac[] = string.data;
        if (i < gapStart)
        {
            int k = gapStart - i;
            if (k > j)
            {
                k = j;
            }
            writer.write(ac, i, k);
            j -= k;
            i += j;
        }
        if (j > 0)
        {
            writer.write(ac, i + (gapEnd - gapStart), j);
        }
    }

    public void writeTo(int i, int j, Appendable appendable)
        throws IOException
    {
        if (appendable instanceof Writer)
        {
            writeTo(i, j, (Writer)appendable);
            return;
        } else
        {
            appendable.append(this, i, i + j);
            return;
        }
    }

    public void writeTo(Writer writer)
        throws IOException
    {
        char ac[] = string.data;
        writer.write(ac, 0, gapStart);
        writer.write(ac, gapEnd, ac.length - gapEnd);
    }

    public void writeTo(Appendable appendable)
        throws IOException
    {
        writeTo(0, size(), appendable);
    }
}
