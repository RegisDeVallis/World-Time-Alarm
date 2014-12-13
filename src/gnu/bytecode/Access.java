// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;


public class Access
{

    public static final short ABSTRACT = 1024;
    public static final short ANNOTATION = 8192;
    public static final short BRIDGE = 64;
    public static final char CLASS_CONTEXT = 67;
    public static final short CLASS_MODIFIERS = 30257;
    public static final short ENUM = 16384;
    public static final char FIELD_CONTEXT = 70;
    public static final short FIELD_MODIFIERS = 20703;
    public static final short FINAL = 16;
    public static final char INNERCLASS_CONTEXT = 73;
    public static final short INNERCLASS_MODIFIERS = 30239;
    public static final short INTERFACE = 512;
    public static final char METHOD_CONTEXT = 77;
    public static final short METHOD_MODIFIERS = 7679;
    public static final short NATIVE = 256;
    public static final short PRIVATE = 2;
    public static final short PROTECTED = 4;
    public static final short PUBLIC = 1;
    public static final short STATIC = 8;
    public static final short STRICT = 2048;
    public static final short SUPER = 32;
    public static final short SYNCHRONIZED = 32;
    public static final short SYNTHETIC = 4096;
    public static final short TRANSIENT = 128;
    public static final short VARARGS = 128;
    public static final short VOLATILE = 64;

    public Access()
    {
    }

    public static String toString(int i)
    {
        return toString(i, '\0');
    }

    public static String toString(int i, char c)
    {
        char c1;
        short word0;
        int j;
        StringBuffer stringbuffer;
        if (c == 'C')
        {
            c1 = '\u7631';
        } else
        if (c == 'I')
        {
            c1 = '\u761F';
        } else
        if (c == 'F')
        {
            c1 = '\u50DF';
        } else
        if (c == 'M')
        {
            c1 = '\u1DFF';
        } else
        {
            c1 = '\u7FFF';
        }
        word0 = (short)(i & ~c1);
        j = i & c1;
        stringbuffer = new StringBuffer();
        if ((j & 1) != 0)
        {
            stringbuffer.append(" public");
        }
        if ((j & 2) != 0)
        {
            stringbuffer.append(" private");
        }
        if ((j & 4) != 0)
        {
            stringbuffer.append(" protected");
        }
        if ((j & 8) != 0)
        {
            stringbuffer.append(" static");
        }
        if ((j & 0x10) != 0)
        {
            stringbuffer.append(" final");
        }
        if ((j & 0x20) != 0)
        {
            String s2;
            if (c == 'C')
            {
                s2 = " super";
            } else
            {
                s2 = " synchronized";
            }
            stringbuffer.append(s2);
        }
        if ((j & 0x40) != 0)
        {
            String s1;
            if (c == 'M')
            {
                s1 = " bridge";
            } else
            {
                s1 = " volatile";
            }
            stringbuffer.append(s1);
        }
        if ((j & 0x80) != 0)
        {
            String s;
            if (c == 'M')
            {
                s = " varargs";
            } else
            {
                s = " transient";
            }
            stringbuffer.append(s);
        }
        if ((j & 0x100) != 0)
        {
            stringbuffer.append(" native");
        }
        if ((j & 0x200) != 0)
        {
            stringbuffer.append(" interface");
        }
        if ((j & 0x400) != 0)
        {
            stringbuffer.append(" abstract");
        }
        if ((j & 0x800) != 0)
        {
            stringbuffer.append(" strict");
        }
        if ((j & 0x4000) != 0)
        {
            stringbuffer.append(" enum");
        }
        if ((j & 0x1000) != 0)
        {
            stringbuffer.append(" synthetic");
        }
        if ((j & 0x2000) != 0)
        {
            stringbuffer.append(" annotation");
        }
        if (word0 != 0)
        {
            stringbuffer.append(" unknown-flags:0x");
            stringbuffer.append(Integer.toHexString(word0));
        }
        return stringbuffer.toString();
    }
}
