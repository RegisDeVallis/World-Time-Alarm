// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreePosition;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.xml.NodeTree;
import gnu.xml.XMLPrinter;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.UserDataHandler;

// Referenced classes of package gnu.kawa.xml:
//            KText, KElement, KAttr, KDocument, 
//            KCDATASection, KComment, KProcessingInstruction, SortedNodes, 
//            Nodes

public abstract class KNode extends SeqPosition
    implements Node, Consumable
{

    public KNode(NodeTree nodetree, int i)
    {
        super(nodetree, i);
    }

    public static Object atomicValue(Object obj)
    {
        if (obj instanceof KNode)
        {
            KNode knode = (KNode)obj;
            obj = ((NodeTree)knode.sequence).typedValue(knode.ipos);
        }
        return obj;
    }

    public static KNode coerce(Object obj)
    {
        if (obj instanceof KNode)
        {
            return (KNode)obj;
        }
        if (obj instanceof NodeTree)
        {
            NodeTree nodetree = (NodeTree)obj;
            return make(nodetree, nodetree.startPos());
        }
        if ((obj instanceof SeqPosition) && !(obj instanceof TreePosition))
        {
            SeqPosition seqposition = (SeqPosition)obj;
            if (seqposition.sequence instanceof NodeTree)
            {
                return make((NodeTree)seqposition.sequence, seqposition.ipos);
            }
        }
        return null;
    }

    public static String getNodeValue(NodeTree nodetree, int i)
    {
        StringBuffer stringbuffer = new StringBuffer();
        getNodeValue(nodetree, i, stringbuffer);
        return stringbuffer.toString();
    }

    public static void getNodeValue(NodeTree nodetree, int i, StringBuffer stringbuffer)
    {
        nodetree.stringValue(nodetree.posToDataIndex(i), stringbuffer);
    }

    public static KNode make(NodeTree nodetree)
    {
        return make(nodetree, 0);
    }

    public static KNode make(NodeTree nodetree, int i)
    {
        for (int j = nodetree.posToDataIndex(i); j < nodetree.data.length && nodetree.data[j] == '\uF112';)
        {
            if ((j += 5) == nodetree.gapStart)
            {
                j = nodetree.gapEnd;
            }
            i = j << 1;
        }

        nodetree.getNextKindI(nodetree.posToDataIndex(i));
        JVM INSTR lookupswitch 7: default 124
    //                   0: 194
    //                   31: 164
    //                   33: 134
    //                   34: 154
    //                   35: 144
    //                   36: 174
    //                   37: 184;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8
_L1:
        return new KText(nodetree, i);
_L4:
        return new KElement(nodetree, i);
_L6:
        return new KAttr(nodetree, i);
_L5:
        return new KDocument(nodetree, i);
_L3:
        return new KCDATASection(nodetree, i);
_L7:
        return new KComment(nodetree, i);
_L8:
        return new KProcessingInstruction(nodetree, i);
_L2:
        if (!nodetree.isEmpty())
        {
            return null;
        }
        if (true) goto _L1; else goto _L9
_L9:
    }

    public Node appendChild(Node node)
        throws DOMException
    {
        throw new DOMException((short)7, "appendChild not supported");
    }

    public Path baseURI()
    {
        return ((NodeTree)sequence).baseUriOfPos(ipos, true);
    }

    public Node cloneNode(boolean flag)
    {
        if (!flag)
        {
            throw new UnsupportedOperationException("shallow cloneNode not implemented");
        } else
        {
            NodeTree nodetree = new NodeTree();
            ((NodeTree)sequence).consumeNext(ipos, nodetree);
            return make(nodetree);
        }
    }

    public short compareDocumentPosition(Node node)
        throws DOMException
    {
        if (!(node instanceof KNode))
        {
            throw new DOMException((short)9, (new StringBuilder()).append("other Node is a ").append(node.getClass().getName()).toString());
        }
        KNode knode = (KNode)node;
        AbstractSequence abstractsequence = knode.sequence;
        int i;
        if (sequence == abstractsequence)
        {
            i = abstractsequence.compare(ipos, knode.ipos);
        } else
        {
            i = sequence.stableCompare(abstractsequence);
        }
        return (short)i;
    }

    public void consume(Consumer consumer)
    {
        if (consumer instanceof PositionConsumer)
        {
            ((PositionConsumer)consumer).consume(this);
            return;
        } else
        {
            ((NodeTree)sequence).consumeNext(ipos, consumer);
            return;
        }
    }

    public KNode copy()
    {
        return make((NodeTree)sequence, sequence.copyPos(getPos()));
    }

    public volatile SeqPosition copy()
    {
        return copy();
    }

    public NamedNodeMap getAttributes()
    {
        throw new UnsupportedOperationException("getAttributes not implemented yet");
    }

    public String getBaseURI()
    {
        Path path = ((NodeTree)sequence).baseUriOfPos(ipos, true);
        if (path == null)
        {
            return null;
        } else
        {
            return path.toString();
        }
    }

    public NodeList getChildNodes()
    {
        SortedNodes sortednodes = new SortedNodes();
        for (int i = sequence.firstChildPos(ipos); i != 0; i = sequence.nextPos(i))
        {
            sortednodes.writePosition(sequence, i);
        }

        return sortednodes;
    }

    public NodeList getElementsByTagName(String s)
    {
        throw new UnsupportedOperationException("getElementsByTagName not implemented yet");
    }

    public Object getFeature(String s, String s1)
    {
        return null;
    }

    public Node getFirstChild()
    {
        int i = ((NodeTree)sequence).posFirstChild(ipos);
        return make((NodeTree)sequence, i);
    }

    public Node getLastChild()
    {
        int i = 0;
        for (int j = sequence.firstChildPos(ipos); j != 0; j = sequence.nextPos(j))
        {
            i = j;
        }

        if (i == 0)
        {
            return null;
        } else
        {
            return make((NodeTree)sequence, i);
        }
    }

    public String getLocalName()
    {
        return ((NodeTree)sequence).posLocalName(ipos);
    }

    public String getNamespaceURI()
    {
        return ((NodeTree)sequence).posNamespaceURI(ipos);
    }

    public Node getNextSibling()
    {
        int i = ((NodeTree)sequence).nextPos(ipos);
        if (i == 0)
        {
            return null;
        } else
        {
            return make((NodeTree)sequence, i);
        }
    }

    public String getNodeName()
    {
        return sequence.getNextTypeName(ipos);
    }

    public Object getNodeNameObject()
    {
        return ((NodeTree)sequence).getNextTypeObject(ipos);
    }

    public Symbol getNodeSymbol()
    {
        Object obj = ((NodeTree)sequence).getNextTypeObject(ipos);
        if (obj == null)
        {
            return null;
        }
        if (obj instanceof Symbol)
        {
            return (Symbol)obj;
        } else
        {
            return Namespace.EmptyNamespace.getSymbol(obj.toString().intern());
        }
    }

    public abstract short getNodeType();

    public String getNodeValue()
    {
        StringBuffer stringbuffer = new StringBuffer();
        getNodeValue(stringbuffer);
        return stringbuffer.toString();
    }

    public void getNodeValue(StringBuffer stringbuffer)
    {
        getNodeValue((NodeTree)sequence, ipos, stringbuffer);
    }

    public Document getOwnerDocument()
    {
        if (sequence.getNextKind(ipos) == 34)
        {
            return new KDocument((NodeTree)sequence, 0);
        } else
        {
            return null;
        }
    }

    public Node getParentNode()
    {
        int i = sequence.parentPos(ipos);
        if (i == -1)
        {
            return null;
        } else
        {
            return make((NodeTree)sequence, i);
        }
    }

    public String getPrefix()
    {
        return ((NodeTree)sequence).posPrefix(ipos);
    }

    public Node getPreviousSibling()
    {
        int i = sequence.parentPos(ipos);
        if (i == -1)
        {
            i = 0;
        }
        int j = ((NodeTree)sequence).posToDataIndex(ipos);
        int k = sequence.firstChildPos(i);
        do
        {
            int l = k;
            k = sequence.nextPos(k);
            while (k == 0 || ((NodeTree)sequence).posToDataIndex(k) == j) 
            {
                if (l == 0)
                {
                    return null;
                } else
                {
                    return make((NodeTree)sequence, l);
                }
            }
        } while (true);
    }

    public String getTextContent()
    {
        StringBuffer stringbuffer = new StringBuffer();
        getTextContent(stringbuffer);
        return stringbuffer.toString();
    }

    protected void getTextContent(StringBuffer stringbuffer)
    {
        getNodeValue(stringbuffer);
    }

    public Object getUserData(String s)
    {
        return null;
    }

    public boolean hasAttributes()
    {
        return false;
    }

    public boolean hasChildNodes()
    {
        return ((NodeTree)sequence).posFirstChild(ipos) >= 0;
    }

    public Node insertBefore(Node node, Node node1)
        throws DOMException
    {
        throw new DOMException((short)7, "insertBefore not supported");
    }

    public boolean isDefaultNamespace(String s)
    {
        return ((NodeTree)sequence).posIsDefaultNamespace(ipos, s);
    }

    public boolean isEqualNode(Node node)
    {
        throw new UnsupportedOperationException("getAttributesisEqualNode not implemented yet");
    }

    public boolean isSameNode(Node node)
    {
        KNode knode;
        if (node instanceof KNode)
        {
            if (sequence == (knode = (KNode)node).sequence)
            {
                return sequence.equals(ipos, knode.ipos);
            }
        }
        return false;
    }

    public boolean isSupported(String s, String s1)
    {
        return false;
    }

    public String lookupNamespaceURI(String s)
    {
        return ((NodeTree)sequence).posLookupNamespaceURI(ipos, s);
    }

    public String lookupPrefix(String s)
    {
        return ((NodeTree)sequence).posLookupPrefix(ipos, s);
    }

    public void normalize()
    {
    }

    public Node removeChild(Node node)
        throws DOMException
    {
        throw new DOMException((short)7, "removeChild not supported");
    }

    public Node replaceChild(Node node, Node node1)
        throws DOMException
    {
        throw new DOMException((short)7, "replaceChild not supported");
    }

    public void setNodeValue(String s)
        throws DOMException
    {
        throw new DOMException((short)7, "setNodeValue not supported");
    }

    public void setPrefix(String s)
        throws DOMException
    {
        throw new DOMException((short)7, "setPrefix not supported");
    }

    public void setTextContent(String s)
        throws DOMException
    {
        throw new DOMException((short)7, "setTextContent not supported");
    }

    public Object setUserData(String s, Object obj, UserDataHandler userdatahandler)
    {
        throw new UnsupportedOperationException("setUserData not implemented yet");
    }

    public String toString()
    {
        CharArrayOutPort chararrayoutport = new CharArrayOutPort();
        XMLPrinter xmlprinter = new XMLPrinter(chararrayoutport);
        ((NodeTree)sequence).consumeNext(ipos, xmlprinter);
        xmlprinter.close();
        chararrayoutport.close();
        return chararrayoutport.toString();
    }
}
