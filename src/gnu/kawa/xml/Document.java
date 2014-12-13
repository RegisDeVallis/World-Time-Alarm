// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.lists.XConsumer;
import gnu.mapping.Location;
import gnu.mapping.ThreadLocation;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.xml.NodeTree;
import gnu.xml.XMLParser;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Hashtable;

// Referenced classes of package gnu.kawa.xml:
//            KDocument

public class Document
{
    private static class DocReference extends SoftReference
    {

        static ReferenceQueue queue = new ReferenceQueue();
        Path key;


        public DocReference(Path path, KDocument kdocument)
        {
            super(kdocument, queue);
            key = path;
        }
    }


    private static HashMap cache = new HashMap();
    private static ThreadLocation docMapLocation = new ThreadLocation("document-map");
    public static final Document document = new Document();

    public Document()
    {
    }

    public static void clearLocalCache()
    {
        docMapLocation.getLocation().set(null);
    }

    public static void clearSoftCache()
    {
        cache = new HashMap();
    }

    public static KDocument parse(Object obj)
        throws Throwable
    {
        NodeTree nodetree = new NodeTree();
        parse(obj, ((Consumer) (nodetree)));
        return new KDocument(nodetree, 10);
    }

    public static void parse(Object obj, Consumer consumer)
        throws Throwable
    {
        SourceMessages sourcemessages = new SourceMessages();
        if (consumer instanceof XConsumer)
        {
            ((XConsumer)consumer).beginEntity(obj);
        }
        XMLParser.parse(obj, sourcemessages, consumer);
        if (sourcemessages.seenErrors())
        {
            throw new SyntaxException("document function read invalid XML", sourcemessages);
        }
        if (consumer instanceof XConsumer)
        {
            ((XConsumer)consumer).endEntity();
        }
    }

    public static KDocument parseCached(Path path)
        throws Throwable
    {
        gnu/kawa/xml/Document;
        JVM INSTR monitorenter ;
_L5:
        DocReference docreference = (DocReference)DocReference.queue.poll();
        if (docreference != null) goto _L2; else goto _L1
_L1:
        gnu.mapping.NamedLocation namedlocation;
        Hashtable hashtable;
        namedlocation = docMapLocation.getLocation();
        hashtable = (Hashtable)namedlocation.get(null);
        if (hashtable != null)
        {
            break MISSING_BLOCK_LABEL_54;
        }
        hashtable = new Hashtable();
        namedlocation.set(hashtable);
        KDocument kdocument = (KDocument)hashtable.get(path);
        if (kdocument == null) goto _L4; else goto _L3
_L3:
        KDocument kdocument3 = kdocument;
_L6:
        gnu/kawa/xml/Document;
        JVM INSTR monitorexit ;
        return kdocument3;
_L2:
        cache.remove(docreference.key);
          goto _L5
        Exception exception;
        exception;
        throw exception;
_L4:
        DocReference docreference1 = (DocReference)cache.get(path);
        if (docreference1 == null)
        {
            break MISSING_BLOCK_LABEL_140;
        }
        KDocument kdocument1 = (KDocument)docreference1.get();
        if (kdocument1 != null)
        {
            break MISSING_BLOCK_LABEL_180;
        }
        cache.remove(path);
        KDocument kdocument2;
        kdocument2 = parse(path);
        hashtable.put(path, kdocument2);
        cache.put(path, new DocReference(path, kdocument2));
        kdocument3 = kdocument2;
          goto _L6
        hashtable.put(path, kdocument1);
        kdocument3 = kdocument1;
          goto _L6
    }

    public static KDocument parseCached(Object obj)
        throws Throwable
    {
        return parseCached(Path.valueOf(obj));
    }

}
