// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;

import gnu.lists.CharSeq;
import gnu.lists.FString;
import gnu.text.NullReader;
import gnu.text.Path;
import java.io.IOException;

// Referenced classes of package gnu.mapping:
//            InPort

public class CharArrayInPort extends InPort
{

    static final Path stringPath = Path.valueOf("<string>");

    public CharArrayInPort(String s)
    {
        this(s.toCharArray());
    }

    public CharArrayInPort(char ac[])
    {
        this(ac, ac.length);
    }

    public CharArrayInPort(char ac[], int i)
    {
        super(NullReader.nullReader, stringPath);
        try
        {
            setBuffer(ac);
        }
        catch (IOException ioexception)
        {
            throw new Error(ioexception.toString());
        }
        limit = i;
    }

    public CharArrayInPort make(CharSequence charsequence)
    {
        if (charsequence instanceof FString)
        {
            FString fstring = (FString)charsequence;
            return new CharArrayInPort(fstring.data, fstring.size);
        }
        int i = charsequence.length();
        char ac[] = new char[i];
        if (charsequence instanceof String)
        {
            ((String)charsequence).getChars(0, i, ac, 0);
        } else
        if (!(charsequence instanceof CharSeq))
        {
            int j = i;
            while (--j >= 0) 
            {
                ac[j] = charsequence.charAt(j);
            }
        } else
        {
            ((CharSeq)charsequence).getChars(0, i, ac, 0);
        }
        return new CharArrayInPort(ac, i);
    }

    public int read()
        throws IOException
    {
        if (pos >= limit)
        {
            return -1;
        } else
        {
            return super.read();
        }
    }

}
