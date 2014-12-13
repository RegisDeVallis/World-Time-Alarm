// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;


// Referenced classes of package gnu.kawa.xml:
//            BinaryObject

public class HexBinary extends BinaryObject
{

    public HexBinary(byte abyte0[])
    {
        data = abyte0;
    }

    static char forHexDigit(int i)
    {
        if (i < 10)
        {
            return (char)(i + 48);
        } else
        {
            return (char)(65 + (i - 10));
        }
    }

    static byte[] parseHexBinary(String s)
    {
        String s1;
        int j;
        byte abyte0[];
        int k;
        s1 = s.trim();
        int i = s1.length();
        if ((i & 1) != 0)
        {
            throw new IllegalArgumentException("hexBinary string length not a multiple of 2");
        }
        j = i >> 1;
        abyte0 = new byte[j];
        k = 0;
_L7:
        if (k >= j) goto _L2; else goto _L1
_L1:
        int l;
        int i1;
        int j1;
        l = Character.digit(s1.charAt(k * 2), 16);
        i1 = Character.digit(s1.charAt(1 + k * 2), 16);
        j1 = -1;
        if (l >= 0) goto _L4; else goto _L3
_L3:
        j1 = k * 2;
_L6:
        if (j1 >= 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("invalid hexBinary character at position ").append(j1).toString());
        }
        break; /* Loop/switch isn't completed */
_L4:
        if (i1 < 0)
        {
            j1 = 1 + k * 2;
        }
        if (true) goto _L6; else goto _L5
_L5:
        abyte0[k] = (byte)(i1 + l * 16);
        k++;
          goto _L7
_L2:
        return abyte0;
    }

    static HexBinary valueOf(String s)
    {
        return new HexBinary(parseHexBinary(s));
    }

    public String toString()
    {
        return toString(new StringBuffer()).toString();
    }

    public StringBuffer toString(StringBuffer stringbuffer)
    {
        byte abyte0[] = data;
        int i = abyte0.length;
        for (int j = 0; j < i; j++)
        {
            byte byte0 = abyte0[j];
            stringbuffer.append(forHexDigit(0xf & byte0 >> 4));
            stringbuffer.append(forHexDigit(byte0 & 0xf));
        }

        return stringbuffer;
    }
}
