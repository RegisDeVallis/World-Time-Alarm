// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import gnu.lists.Consumer;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

// Referenced classes of package gnu.text:
//            CharMap

public class Char
    implements Comparable, Externalizable
{

    static Char ascii[];
    static char charNameValues[] = {
        ' ', '\t', '\n', '\n', '\r', '\f', '\b', '\033', '\177', '\177', 
        '\177', '\007', '\007', '\013', '\0'
    };
    static String charNames[] = {
        "space", "tab", "newline", "linefeed", "return", "page", "backspace", "esc", "delete", "del", 
        "rubout", "alarm", "bel", "vtab", "nul"
    };
    static CharMap hashTable = new CharMap();
    int value;

    public Char()
    {
    }

    Char(int i)
    {
        value = i;
    }

    public static Char make(int i)
    {
        if (i < 128)
        {
            return ascii[i];
        }
        Char char1;
        synchronized (hashTable)
        {
            char1 = hashTable.get(i);
        }
        return char1;
        exception;
        charmap;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static int nameToChar(String s)
    {
        int i = charNames.length;
_L4:
        if (--i < 0) goto _L2; else goto _L1
_L1:
        if (!charNames[i].equals(s)) goto _L4; else goto _L3
_L3:
        int l = charNameValues[i];
_L10:
        return l;
_L2:
        int k;
        for (int j = charNames.length; --j >= 0;)
        {
            if (charNames[j].equalsIgnoreCase(s))
            {
                return charNameValues[j];
            }
        }

        k = s.length();
        if (k <= 1 || s.charAt(0) != 'u') goto _L6; else goto _L5
_L5:
        int i1;
        l = 0;
        i1 = 1;
_L8:
        int j1;
        if (i1 == k)
        {
            break; /* Loop/switch isn't completed */
        }
        j1 = Character.digit(s.charAt(i1), 16);
        if (j1 >= 0)
        {
            break MISSING_BLOCK_LABEL_159;
        }
_L6:
        if (k == 3 && s.charAt(1) == '-')
        {
            char c = s.charAt(0);
            if (c == 'c' || c == 'C')
            {
                return 0x1f & s.charAt(2);
            }
        }
        break MISSING_BLOCK_LABEL_174;
        l = j1 + (l << 4);
        i1++;
        if (true) goto _L8; else goto _L7
_L7:
        if (true) goto _L10; else goto _L9
_L9:
        return -1;
    }

    public static void print(int i, Consumer consumer)
    {
        if (i >= 0x10000)
        {
            consumer.write((char)(55296 + (i - 0x10000 >> 10)));
            consumer.write((char)(56320 + (i & 0x3ff)));
            return;
        } else
        {
            consumer.write((char)i);
            return;
        }
    }

    public static String toScmReadableString(int i)
    {
        StringBuffer stringbuffer = new StringBuffer(20);
        stringbuffer.append("#\\");
        for (int j = 0; j < charNameValues.length; j++)
        {
            if ((char)i == charNameValues[j])
            {
                stringbuffer.append(charNames[j]);
                return stringbuffer.toString();
            }
        }

        if (i < 32 || i > 127)
        {
            stringbuffer.append('x');
            stringbuffer.append(Integer.toString(i, 16));
        } else
        {
            stringbuffer.append((char)i);
        }
        return stringbuffer.toString();
    }

    public final char charValue()
    {
        return (char)value;
    }

    public int compareTo(Object obj)
    {
        return value - ((Char)obj).value;
    }

    public boolean equals(Object obj)
    {
        return obj != null && (obj instanceof Char) && ((Char)obj).intValue() == value;
    }

    public int hashCode()
    {
        return value;
    }

    public final int intValue()
    {
        return value;
    }

    public void print(Consumer consumer)
    {
        print(value, consumer);
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        value = objectinput.readChar();
        if (value >= 55296 && value < 56319)
        {
            char c = objectinput.readChar();
            if (c >= '\uDC00' && c <= '\uDFFF')
            {
                value = 0x10000 + ((value - 55296 << 10) + (c - 56320));
            }
        }
    }

    public Object readResolve()
        throws ObjectStreamException
    {
        return make(value);
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append('\'');
        if (value >= 32 && value < 127 && value != 39)
        {
            stringbuffer.append((char)value);
        } else
        {
            stringbuffer.append('\\');
            if (value == 39)
            {
                stringbuffer.append('\'');
            } else
            if (value == 10)
            {
                stringbuffer.append('n');
            } else
            if (value == 13)
            {
                stringbuffer.append('r');
            } else
            if (value == 9)
            {
                stringbuffer.append('t');
            } else
            if (value < 256)
            {
                String s1 = Integer.toOctalString(value);
                for (int j = 3 - s1.length(); --j >= 0;)
                {
                    stringbuffer.append('0');
                }

                stringbuffer.append(s1);
            } else
            {
                stringbuffer.append('u');
                String s = Integer.toHexString(value);
                for (int i = 4 - s.length(); --i >= 0;)
                {
                    stringbuffer.append('0');
                }

                stringbuffer.append(s);
            }
        }
        stringbuffer.append('\'');
        return stringbuffer.toString();
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        if (value <= 55296) goto _L2; else goto _L1
_L1:
        if (value <= 65535) goto _L4; else goto _L3
_L3:
        objectoutput.writeChar(55296 + (value - 0x10000 >> 10));
        value = 56320 + (0x3ff & value);
_L2:
        objectoutput.writeChar(value);
        return;
_L4:
        if (value <= 56319)
        {
            objectoutput.writeChar(value);
            value = 0;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    static 
    {
        ascii = new Char[128];
        for (int i = 128; --i >= 0;)
        {
            ascii[i] = new Char(i);
        }

    }
}
