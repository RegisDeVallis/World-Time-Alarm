// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;


// Referenced classes of package gnu.kawa.xml:
//            BinaryObject

public class Base64Binary extends BinaryObject
{

    public static final String ENCODING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    public Base64Binary(String s)
    {
        int i;
        byte abyte0[];
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        i = s.length();
        int j = 0;
        for (int k = 0; k < i; k++)
        {
            char c1 = s.charAt(k);
            if (!Character.isWhitespace(c1) && c1 != '=')
            {
                j++;
            }
        }

        abyte0 = new byte[(j * 3) / 4];
        l = 0;
        i1 = 0;
        j1 = 0;
        k1 = 0;
        l1 = 0;
_L3:
        if (k1 >= i) goto _L2; else goto _L1
_L1:
        char c;
        int j2;
        c = s.charAt(k1);
        if (c >= 'A' && c <= 'Z')
        {
            j2 = c - 65;
        } else
        if (c >= 'a' && c <= 'z')
        {
            j2 = 26 + (c - 97);
        } else
        if (c >= '0' && c <= '9')
        {
            j2 = 52 + (c - 48);
        } else
        if (c == '+')
        {
            j2 = 62;
        } else
        {
label0:
            {
                if (c != '/')
                {
                    break label0;
                }
                j2 = 63;
            }
        }
_L4:
        if (j2 < 0 || j1 > 0)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("illegal character in base64Binary string at position ").append(k1).toString());
        }
        break MISSING_BLOCK_LABEL_276;
        int k2;
        if (Character.isWhitespace(c))
        {
            k2 = l1;
        } else
        {
label1:
            {
                if (c != '=')
                {
                    break label1;
                }
                j1++;
                k2 = l1;
            }
        }
_L5:
        k1++;
        l1 = k2;
          goto _L3
        j2 = -1;
          goto _L4
        l = j2 + (l << 6);
        int i2;
        if (++i1 == 4)
        {
            int l2 = l1 + 1;
            abyte0[l1] = (byte)(l >> 16);
            int i3 = l2 + 1;
            abyte0[l2] = (byte)(l >> 8);
            k2 = i3 + 1;
            abyte0[i3] = (byte)l;
            i1 = 0;
        } else
        {
            k2 = l1;
        }
          goto _L5
_L2:
        if (i1 + j1 <= 0 ? l1 != abyte0.length : i1 + j1 != 4 || (l & -1 + (1 << j1)) != 0 || (l1 + 3) - j1 != abyte0.length)
        {
            throw new IllegalArgumentException();
        }
        j1;
        JVM INSTR tableswitch 1 2: default 432
    //                   1 442
    //                   2 477;
           goto _L6 _L7 _L8
_L6:
        l1;
_L10:
        data = abyte0;
        return;
_L7:
        i2 = l1 + 1;
        abyte0[l1] = (byte)(l << 10);
        i2 + 1;
        abyte0[i2] = (byte)(l >> 2);
        continue; /* Loop/switch isn't completed */
_L8:
        l1 + 1;
        abyte0[l1] = (byte)(l >> 4);
        if (true) goto _L10; else goto _L9
_L9:
          goto _L4
    }

    public Base64Binary(byte abyte0[])
    {
        data = abyte0;
    }

    public static Base64Binary valueOf(String s)
    {
        return new Base64Binary(s);
    }

    public String toString()
    {
        return toString(new StringBuffer()).toString();
    }

    public StringBuffer toString(StringBuffer stringbuffer)
    {
        byte abyte0[] = data;
        int i = abyte0.length;
        int j = 0;
        int k = 0;
        do
        {
            if (k >= i)
            {
                break;
            }
            byte byte0 = abyte0[k];
            j = j << 8 | byte0 & 0xff;
            if (++k % 3 == 0)
            {
                stringbuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(0x3f & j >> 18));
                stringbuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(0x3f & j >> 12));
                stringbuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(0x3f & j >> 6));
                stringbuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(j & 0x3f));
            }
        } while (true);
        switch (i % 3)
        {
        default:
            return stringbuffer;

        case 1: // '\001'
            stringbuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(0x3f & j >> 2));
            stringbuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(0x3f & j << 4));
            stringbuffer.append("==");
            return stringbuffer;

        case 2: // '\002'
            stringbuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(0x3f & j >> 10));
            break;
        }
        stringbuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(0x3f & j >> 4));
        stringbuffer.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".charAt(0x3f & j << 2));
        stringbuffer.append('=');
        return stringbuffer;
    }
}
