// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.sax;

import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.xml.XMLFilter;
import gnu.xml.XMLParser;
import java.io.IOException;
import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

// Referenced classes of package gnu.kawa.sax:
//            ContentConsumer

public class KawaXMLReader extends ContentConsumer
    implements XMLReader
{

    ErrorHandler errorHandler;

    public KawaXMLReader()
    {
    }

    public DTDHandler getDTDHandler()
    {
        return null;
    }

    public EntityResolver getEntityResolver()
    {
        return null;
    }

    public ErrorHandler getErrorHandler()
    {
        return errorHandler;
    }

    public boolean getFeature(String s)
    {
        return false;
    }

    public Object getProperty(String s)
    {
        return null;
    }

    public void parse(String s)
    {
    }

    public void parse(InputSource inputsource)
        throws IOException, SAXException
    {
        Object obj = inputsource.getCharacterStream();
        if (obj == null)
        {
            obj = XMLParser.XMLStreamReader(inputsource.getByteStream());
        }
        SourceMessages sourcemessages = new SourceMessages();
        XMLFilter xmlfilter = new XMLFilter(this);
        LineBufferedReader linebufferedreader = new LineBufferedReader(((java.io.Reader) (obj)));
        xmlfilter.setSourceLocator(linebufferedreader);
        getContentHandler().setDocumentLocator(xmlfilter);
        XMLParser.parse(linebufferedreader, sourcemessages, xmlfilter);
        String s = sourcemessages.toString(20);
        if (s != null)
        {
            throw new SAXParseException(s, xmlfilter);
        } else
        {
            return;
        }
    }

    public void setDTDHandler(DTDHandler dtdhandler)
    {
    }

    public void setEntityResolver(EntityResolver entityresolver)
    {
    }

    public void setErrorHandler(ErrorHandler errorhandler)
    {
        errorHandler = errorhandler;
    }

    public void setFeature(String s, boolean flag)
    {
    }

    public void setProperty(String s, Object obj)
    {
    }
}
