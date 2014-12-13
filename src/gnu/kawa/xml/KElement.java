// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.xml.NodeTree;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

// Referenced classes of package gnu.kawa.xml:
//            KNode, KAttr

public class KElement extends KNode
    implements Element
{

    public KElement(NodeTree nodetree, int i)
    {
        super(nodetree, i);
    }

    public String getAttribute(String s)
    {
        if (s == null)
        {
            s = "";
        }
        NodeTree nodetree = (NodeTree)sequence;
        int i = nodetree.getAttribute(ipos, null, s);
        if (i == 0)
        {
            return "";
        } else
        {
            return KNode.getNodeValue(nodetree, i);
        }
    }

    public String getAttributeNS(String s, String s1)
    {
        if (s == null)
        {
            s = "";
        }
        if (s1 == null)
        {
            s1 = "";
        }
        NodeTree nodetree = (NodeTree)sequence;
        int i = nodetree.getAttribute(ipos, s, s1);
        if (i == 0)
        {
            return "";
        } else
        {
            return getNodeValue(nodetree, i);
        }
    }

    public KAttr getAttributeNode(String s)
    {
        if (s == null)
        {
            s = "";
        }
        NodeTree nodetree = (NodeTree)sequence;
        int i = nodetree.getAttribute(ipos, null, s);
        if (i == 0)
        {
            return null;
        } else
        {
            return new KAttr(nodetree, i);
        }
    }

    public volatile Attr getAttributeNode(String s)
    {
        return getAttributeNode(s);
    }

    public KAttr getAttributeNodeNS(String s, String s1)
    {
        if (s == null)
        {
            s = "";
        }
        if (s1 == null)
        {
            s1 = "";
        }
        NodeTree nodetree = (NodeTree)sequence;
        int i = nodetree.getAttribute(ipos, s, s1);
        if (i == 0)
        {
            return null;
        } else
        {
            return new KAttr(nodetree, i);
        }
    }

    public volatile Attr getAttributeNodeNS(String s, String s1)
        throws DOMException
    {
        return getAttributeNodeNS(s, s1);
    }

    public NodeList getElementsByTagNameNS(String s, String s1)
    {
        throw new UnsupportedOperationException("getElementsByTagNameNS not implemented yet");
    }

    public short getNodeType()
    {
        return 1;
    }

    public String getNodeValue()
    {
        return null;
    }

    public TypeInfo getSchemaTypeInfo()
    {
        return null;
    }

    public String getTagName()
    {
        return sequence.getNextTypeName(ipos);
    }

    public boolean hasAttribute(String s)
    {
        NodeTree nodetree = (NodeTree)sequence;
        int i = ipos;
        if (s == null)
        {
            s = "";
        }
        return nodetree.getAttribute(i, null, s) != 0;
    }

    public boolean hasAttributeNS(String s, String s1)
    {
        if (s == null)
        {
            s = "";
        }
        if (s1 == null)
        {
            s1 = "";
        }
        return ((NodeTree)sequence).getAttribute(ipos, s, s1) != 0;
    }

    public boolean hasAttributes()
    {
        return ((NodeTree)sequence).posHasAttributes(ipos);
    }

    public void removeAttribute(String s)
        throws DOMException
    {
        throw new DOMException((short)7, "removeAttribute not supported");
    }

    public void removeAttributeNS(String s, String s1)
        throws DOMException
    {
        throw new DOMException((short)7, "removeAttributeNS not supported");
    }

    public Attr removeAttributeNode(Attr attr)
        throws DOMException
    {
        throw new DOMException((short)7, "removeAttributeNode not supported");
    }

    public void setAttribute(String s, String s1)
        throws DOMException
    {
        throw new DOMException((short)7, "setAttribute not supported");
    }

    public void setAttributeNS(String s, String s1, String s2)
        throws DOMException
    {
        throw new DOMException((short)7, "setAttributeNS not supported");
    }

    public Attr setAttributeNode(Attr attr)
        throws DOMException
    {
        throw new DOMException((short)7, "setAttributeNode not supported");
    }

    public Attr setAttributeNodeNS(Attr attr)
        throws DOMException
    {
        throw new DOMException((short)7, "setAttributeNodeNS not supported");
    }

    public void setIdAttribute(String s, boolean flag)
        throws DOMException
    {
        throw new DOMException((short)7, "setIdAttribute not supported");
    }

    public void setIdAttributeNS(String s, String s1, boolean flag)
        throws DOMException
    {
        throw new DOMException((short)7, "setIdAttributeNS not supported");
    }

    public void setIdAttributeNode(Attr attr, boolean flag)
        throws DOMException
    {
        throw new DOMException((short)7, "setIdAttributeNode not supported");
    }
}
