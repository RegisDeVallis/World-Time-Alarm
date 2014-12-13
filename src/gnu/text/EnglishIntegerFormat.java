// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import gnu.lists.Consumer;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class EnglishIntegerFormat extends NumberFormat
{

    private static EnglishIntegerFormat cardinalEnglish;
    public static final String ones[] = {
        null, "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", 
        "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };
    public static final String onesth[] = {
        null, "first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", 
        "tenth", "eleventh", "twelveth", "thirteenth", "fourteenth", "fifteenth", "sixteenth", "seventeenth", "eighteenth", "nineteenth"
    };
    private static EnglishIntegerFormat ordinalEnglish;
    public static final String power1000s[] = {
        null, " thousand", " million", " billion", " trillion", " quadrillion", " quintillion", " sextillion", " septillion", " octillion", 
        " nonillion", " decillion", " undecillion", " duodecillion", " tredecillion", " quattuordecillion", " quindecillion", " sexdecillion", " septendecillion", " octodecillion", 
        " novemdecillion", " vigintillion"
    };
    public static final String tens[] = {
        null, null, "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };
    public static final String tensth[] = {
        null, null, "twentieth", "thirtieth", "fortieth", "fiftieth", "sixtieth", "seventieth", "eightieth", "ninetieth"
    };
    public boolean ordinal;

    public EnglishIntegerFormat(boolean flag)
    {
        ordinal = flag;
    }

    public static EnglishIntegerFormat getInstance(boolean flag)
    {
        if (flag)
        {
            if (ordinalEnglish == null)
            {
                ordinalEnglish = new EnglishIntegerFormat(true);
            }
            return ordinalEnglish;
        }
        if (cardinalEnglish == null)
        {
            cardinalEnglish = new EnglishIntegerFormat(false);
        }
        return cardinalEnglish;
    }

    public StringBuffer format(double d, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        long l = (long)d;
        if ((double)l == d)
        {
            return format(l, stringbuffer, fieldposition);
        } else
        {
            stringbuffer.append(Double.toString(d));
            return stringbuffer;
        }
    }

    public StringBuffer format(long l, StringBuffer stringbuffer, FieldPosition fieldposition)
    {
        if (l == 0L)
        {
            String s;
            if (ordinal)
            {
                s = "zeroth";
            } else
            {
                s = "zero";
            }
            stringbuffer.append(s);
        } else
        {
            if (l < 0L)
            {
                stringbuffer.append("minus ");
                l = -l;
            }
            boolean flag = ordinal;
            format(stringbuffer, l, 0, flag);
        }
        if (fieldposition == null);
        return stringbuffer;
    }

    void format(StringBuffer stringbuffer, long l, int i, boolean flag)
    {
        if (l < 1000L) goto _L2; else goto _L1
_L1:
        format(stringbuffer, l / 1000L, i + 1, false);
        l %= 1000L;
        if (l <= 0L) goto _L4; else goto _L3
_L3:
        stringbuffer.append(", ");
_L2:
        if (l <= 0L) goto _L6; else goto _L5
_L5:
        int j = (int)l;
        boolean flag1;
        if (flag && i == 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        format999(stringbuffer, j, flag1);
        if (i < power1000s.length) goto _L8; else goto _L7
_L7:
        stringbuffer.append(" times ten to the ");
        format(stringbuffer, i * 3, 0, true);
        stringbuffer.append(" power");
_L6:
        return;
_L4:
        if (flag)
        {
            stringbuffer.append("th");
        }
        continue; /* Loop/switch isn't completed */
_L8:
        if (i <= 0) goto _L6; else goto _L9
_L9:
        stringbuffer.append(power1000s[i]);
        return;
        if (true) goto _L2; else goto _L10
_L10:
    }

    void format999(StringBuffer stringbuffer, int i, boolean flag)
    {
        if (i >= 100)
        {
            int k = i / 100;
            i %= 100;
            if (k > 1)
            {
                stringbuffer.append(ones[k]);
                stringbuffer.append(' ');
            }
            stringbuffer.append("hundred");
            int j;
            if (i > 0)
            {
                stringbuffer.append(' ');
            } else
            if (flag)
            {
                stringbuffer.append("th");
            }
        }
        if (i >= 20)
        {
            j = i / 10;
            i %= 10;
            String as1[];
            if (flag && i == 0)
            {
                as1 = tensth;
            } else
            {
                as1 = tens;
            }
            stringbuffer.append(as1[j]);
            if (i > 0)
            {
                stringbuffer.append('-');
            }
        }
        if (i > 0)
        {
            String as[];
            if (flag)
            {
                as = onesth;
            } else
            {
                as = ones;
            }
            stringbuffer.append(as[i]);
        }
    }

    public Number parse(String s, ParsePosition parseposition)
    {
        throw new Error("EnglishIntegerFormat.parseObject - not implemented");
    }

    public void writeBoolean(boolean flag, Consumer consumer)
    {
        long l;
        if (flag)
        {
            l = 1L;
        } else
        {
            l = 0L;
        }
        writeLong(l, consumer);
    }

    public void writeInt(int i, Consumer consumer)
    {
        writeLong(i, consumer);
    }

    public void writeLong(long l, Consumer consumer)
    {
        StringBuffer stringbuffer = new StringBuffer();
        format(l, stringbuffer, null);
        consumer.write(stringbuffer, 0, stringbuffer.length());
    }

    public void writeObject(Object obj, Consumer consumer)
    {
        writeLong(((Number)obj).longValue(), consumer);
    }

}
