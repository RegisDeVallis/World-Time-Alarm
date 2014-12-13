// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;

// Referenced classes of package gnu.lists:
//            Consumer, ConsumerWriter

public class PrintConsumer extends PrintWriter
    implements Appendable, Consumer
{

    public PrintConsumer(Consumer consumer, boolean flag)
    {
        Object obj;
        if (consumer instanceof Writer)
        {
            obj = (Writer)consumer;
        } else
        {
            obj = new ConsumerWriter(consumer);
        }
        super(((Writer) (obj)), flag);
    }

    public PrintConsumer(OutputStream outputstream, boolean flag)
    {
        super(outputstream, flag);
    }

    public PrintConsumer(Writer writer)
    {
        super(writer);
    }

    public PrintConsumer(Writer writer, boolean flag)
    {
        super(writer, flag);
    }

    public volatile Consumer append(char c)
    {
        return append(c);
    }

    public volatile Consumer append(CharSequence charsequence)
    {
        return append(charsequence);
    }

    public volatile Consumer append(CharSequence charsequence, int i, int j)
    {
        return append(charsequence, i, j);
    }

    public PrintConsumer append(char c)
    {
        print(c);
        return this;
    }

    public PrintConsumer append(CharSequence charsequence)
    {
        if (charsequence == null)
        {
            charsequence = "null";
        }
        append(charsequence, 0, charsequence.length());
        return this;
    }

    public PrintConsumer append(CharSequence charsequence, int i, int j)
    {
        if (charsequence == null)
        {
            charsequence = "null";
        }
        for (int k = i; k < j; k++)
        {
            append(charsequence.charAt(k));
        }

        return this;
    }

    public volatile PrintWriter append(char c)
    {
        return append(c);
    }

    public volatile PrintWriter append(CharSequence charsequence)
    {
        return append(charsequence);
    }

    public volatile PrintWriter append(CharSequence charsequence, int i, int j)
    {
        return append(charsequence, i, j);
    }

    public volatile Writer append(char c)
        throws IOException
    {
        return append(c);
    }

    public volatile Writer append(CharSequence charsequence)
        throws IOException
    {
        return append(charsequence);
    }

    public volatile Writer append(CharSequence charsequence, int i, int j)
        throws IOException
    {
        return append(charsequence, i, j);
    }

    public volatile Appendable append(char c)
        throws IOException
    {
        return append(c);
    }

    public volatile Appendable append(CharSequence charsequence)
        throws IOException
    {
        return append(charsequence);
    }

    public volatile Appendable append(CharSequence charsequence, int i, int j)
        throws IOException
    {
        return append(charsequence, i, j);
    }

    public void endAttribute()
    {
    }

    public void endDocument()
    {
    }

    public void endElement()
    {
    }

    protected void endNumber()
    {
    }

    public boolean ignoring()
    {
        return false;
    }

    public void startAttribute(Object obj)
    {
    }

    public void startDocument()
    {
    }

    public void startElement(Object obj)
    {
    }

    protected void startNumber()
    {
    }

    public void write(CharSequence charsequence, int i, int j)
    {
        if (charsequence instanceof String)
        {
            write((String)charsequence, i, j);
        } else
        {
            int k = i;
            while (k < j) 
            {
                write(charsequence.charAt(k));
                k++;
            }
        }
    }

    public void writeBoolean(boolean flag)
    {
        print(flag);
    }

    public void writeDouble(double d)
    {
        startNumber();
        print(d);
        endNumber();
    }

    public void writeFloat(float f)
    {
        startNumber();
        print(f);
        endNumber();
    }

    public void writeInt(int i)
    {
        startNumber();
        print(i);
        endNumber();
    }

    public void writeLong(long l)
    {
        startNumber();
        print(l);
        endNumber();
    }

    public void writeObject(Object obj)
    {
        print(obj);
    }
}
