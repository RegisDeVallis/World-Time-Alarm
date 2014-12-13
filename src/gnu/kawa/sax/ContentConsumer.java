// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.sax;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.mapping.Symbol;
import gnu.text.Char;
import gnu.xml.XName;
import java.io.IOException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class ContentConsumer
    implements Consumer
{

    String attrLocalName;
    String attrQName;
    String attrURI;
    AttributesImpl attributes;
    char chBuffer[];
    int inStartTag;
    String names[];
    int nesting;
    ContentHandler out;
    StringBuilder strBuffer;

    public ContentConsumer()
    {
        nesting = 0;
        names = new String[15];
        attributes = new AttributesImpl();
        strBuffer = new StringBuilder(200);
    }

    public ContentConsumer(ContentHandler contenthandler)
    {
        nesting = 0;
        names = new String[15];
        attributes = new AttributesImpl();
        strBuffer = new StringBuilder(200);
        out = contenthandler;
    }

    public ContentConsumer append(char c)
    {
        write(c);
        return this;
    }

    public ContentConsumer append(CharSequence charsequence)
    {
        if (charsequence == null)
        {
            charsequence = "null";
        }
        write(charsequence, 0, charsequence.length());
        return this;
    }

    public ContentConsumer append(CharSequence charsequence, int i, int j)
    {
        if (charsequence == null)
        {
            charsequence = "null";
        }
        write(charsequence, i, j);
        return this;
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
        attributes.addAttribute(attrURI, attrLocalName, attrQName, "CDATA", strBuffer.toString());
        strBuffer.setLength(0);
        inStartTag = 1;
    }

    public void endDocument()
    {
        try
        {
            out.endDocument();
            return;
        }
        catch (SAXException saxexception)
        {
            error("endDocument", saxexception);
        }
    }

    public void endElement()
    {
        endStartTag();
        flushStrBuffer();
        nesting = -1 + nesting;
        int i = 3 * nesting;
        try
        {
            out.endElement(names[i], names[i + 1], names[i + 2]);
        }
        catch (SAXException saxexception)
        {
            error("endElement", saxexception);
        }
        names[i] = null;
        names[i + 1] = null;
        names[i + 2] = null;
    }

    public void endStartTag()
    {
        if (inStartTag != 1)
        {
            return;
        }
        int i = 3 * (-1 + nesting);
        try
        {
            out.startElement(names[i], names[i + 1], names[i + 2], attributes);
        }
        catch (SAXException saxexception)
        {
            error("startElement", saxexception);
        }
        attributes.clear();
        inStartTag = 0;
    }

    public void error(String s, SAXException saxexception)
    {
        throw new RuntimeException((new StringBuilder()).append("caught ").append(saxexception).append(" in ").append(s).toString());
    }

    public void finalize()
    {
        flushStrBuffer();
    }

    void flushStrBuffer()
    {
        if (strBuffer.length() <= 0) goto _L2; else goto _L1
_L1:
        if (chBuffer == null)
        {
            chBuffer = new char[200];
        }
        int i = strBuffer.length();
        int j = 0;
_L3:
        int k = i - j;
        if (k <= 0)
        {
            try
            {
                strBuffer.setLength(0);
                return;
            }
            catch (SAXException saxexception)
            {
                error("characters", saxexception);
            }
            break; /* Loop/switch isn't completed */
        }
        if (k > chBuffer.length)
        {
            k = chBuffer.length;
        }
        strBuffer.getChars(j, j + k, chBuffer, j);
        out.characters(chBuffer, 0, k);
        j += k;
        if (true) goto _L3; else goto _L2
_L2:
    }

    public ContentHandler getContentHandler()
    {
        return out;
    }

    public boolean ignoring()
    {
        return false;
    }

    public void setContentHandler(ContentHandler contenthandler)
    {
        out = contenthandler;
    }

    public void startAttribute(Object obj)
    {
        attrURI = ((Symbol)obj).getNamespaceURI();
        attrLocalName = ((Symbol)obj).getLocalName();
        attrQName = obj.toString();
        inStartTag = 2;
    }

    public void startDocument()
    {
        try
        {
            out.startDocument();
            return;
        }
        catch (SAXException saxexception)
        {
            error("startDocument", saxexception);
        }
    }

    public void startElement(Object obj)
    {
        if (inStartTag == 1)
        {
            endStartTag();
        }
        flushStrBuffer();
        int i = 3 * nesting;
        if (i >= names.length)
        {
            String as[] = new String[i * 2];
            System.arraycopy(names, 0, as, 0, i);
            names = as;
        }
        String s;
        String s1;
        if (obj instanceof Symbol)
        {
            Symbol symbol = (Symbol)obj;
            s = symbol.getNamespaceURI();
            s1 = symbol.getLocalName();
        } else
        if (obj instanceof XName)
        {
            XName xname = (XName)obj;
            s = xname.getNamespaceURI();
            s1 = xname.getLocalName();
        } else
        {
            s = "";
            s1 = obj.toString();
        }
        names[i] = s;
        names[i + 1] = s1;
        names[i + 2] = obj.toString();
        inStartTag = 1;
        nesting = 1 + nesting;
    }

    public void write(int i)
    {
        if (inStartTag == 1)
        {
            endStartTag();
        }
        if (i >= 0x10000)
        {
            strBuffer.append((char)(55296 + (i - 0x10000 >> 10)));
            i = 56320 + (i & 0x3ff);
        }
        strBuffer.append((char)i);
    }

    public void write(CharSequence charsequence, int i, int j)
    {
        if (inStartTag == 1)
        {
            endStartTag();
        }
        strBuffer.append(charsequence, i, j);
    }

    public void write(String s)
    {
        if (inStartTag == 1)
        {
            endStartTag();
        }
        strBuffer.append(s);
    }

    public void write(char ac[], int i, int j)
    {
        if (inStartTag == 1)
        {
            endStartTag();
        }
        if (inStartTag == 2)
        {
            strBuffer.append(ac, i, j);
            return;
        }
        flushStrBuffer();
        try
        {
            out.characters(ac, i, j);
            return;
        }
        catch (SAXException saxexception)
        {
            error("characters", saxexception);
        }
    }

    public void writeBoolean(boolean flag)
    {
        if (inStartTag == 1)
        {
            endStartTag();
        }
        strBuffer.append(flag);
    }

    public void writeDouble(double d)
    {
        if (inStartTag == 1)
        {
            endStartTag();
        }
        strBuffer.append(d);
    }

    public void writeFloat(float f)
    {
        if (inStartTag == 1)
        {
            endStartTag();
        }
        strBuffer.append(f);
    }

    public void writeInt(int i)
    {
        if (inStartTag == 1)
        {
            endStartTag();
        }
        strBuffer.append(i);
    }

    public void writeLong(long l)
    {
        if (inStartTag == 1)
        {
            endStartTag();
        }
        strBuffer.append(l);
    }

    public void writeObject(Object obj)
    {
        if (obj instanceof Consumable)
        {
            ((Consumable)obj).consume(this);
            return;
        }
        if (obj instanceof SeqPosition)
        {
            SeqPosition seqposition = (SeqPosition)obj;
            seqposition.sequence.consumeNext(seqposition.ipos, this);
            return;
        }
        if (obj instanceof Char)
        {
            ((Char)obj).print(this);
            return;
        }
        String s;
        if (obj == null)
        {
            s = "(null)";
        } else
        {
            s = obj.toString();
        }
        write(s);
    }
}
