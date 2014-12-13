// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.xml;

import gnu.kawa.xml.ElementType;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.lists.AbstractSequence;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.text.URIPath;

// Referenced classes of package gnu.xml:
//            XName, XMLPrinter

public class NodeTree extends TreeList
{

    static int counter;
    int id;
    int idCount;
    String idNames[];
    int idOffsets[];

    public NodeTree()
    {
    }

    public static NodeTree make()
    {
        return new NodeTree();
    }

    public int ancestorAttribute(int i, String s, String s1)
    {
_L6:
        if (i != -1) goto _L2; else goto _L1
_L1:
        int j = 0;
_L4:
        return j;
_L2:
        j = getAttributeI(i, s, s1);
        if (j != 0) goto _L4; else goto _L3
_L3:
        i = parentPos(i);
        if (true) goto _L6; else goto _L5
_L5:
    }

    public Path baseUriOfPos(int i, boolean flag)
    {
        Object obj;
        int j;
        obj = null;
        j = posToDataIndex(i);
_L7:
        char c;
        if (j == data.length)
        {
            return null;
        }
        c = data[j];
        if (c != '\uF112') goto _L2; else goto _L1
_L1:
        URIPath uripath;
        int l = getIntN(j + 1);
        uripath = null;
        if (l >= 0)
        {
            uripath = URIPath.makeURI(objects[l]);
        }
_L5:
        if (uripath != null)
        {
            int k;
            if (obj == null || !flag)
            {
                obj = uripath;
            } else
            {
                obj = uripath.resolve(((Path) (obj)));
            }
            if (((Path) (obj)).isAbsolute() || !flag)
            {
                return ((Path) (obj));
            }
        }
        break MISSING_BLOCK_LABEL_163;
_L2:
        if (c >= '\uA000' && c <= '\uAFFF') goto _L4; else goto _L3
_L3:
        uripath = null;
        if (c != '\uF108') goto _L5; else goto _L4
_L4:
        k = getAttributeI(i, "http://www.w3.org/XML/1998/namespace", "base");
        uripath = null;
        if (k != 0)
        {
            uripath = URIPath.valueOf(KNode.getNodeValue(this, k));
        }
          goto _L5
        j = parentOrEntityI(j);
        if (j == -1)
        {
            return ((Path) (obj));
        }
        i = j << 1;
        if (true) goto _L7; else goto _L6
_L6:
    }

    void enterID(String s, int i)
    {
        int j1;
        int k1;
        int l1;
        String s1;
        String as[] = idNames;
        int ai[] = idOffsets;
        int k;
        int i1;
        if (as == null)
        {
            k = 64;
            idNames = new String[k];
            idOffsets = new int[k];
        } else
        {
            int j = 4 * idCount;
            k = idNames.length;
            if (j >= k * 3)
            {
                idNames = new String[k * 2];
                idOffsets = new int[k * 2];
                idCount = 0;
                int l = k;
                do
                {
                    if (--l < 0)
                    {
                        break;
                    }
                    String s2 = as[l];
                    if (s2 != null)
                    {
                        enterID(s2, ai[l]);
                    }
                } while (true);
                as = idNames;
                ai = idOffsets;
                k *= 2;
            }
        }
        i1 = s.hashCode();
        j1 = k - 1;
        k1 = i1 & j1;
        l1 = 1 | ~i1 << 1;
_L6:
        s1 = as[k1];
        if (s1 != null) goto _L2; else goto _L1
_L1:
        as[k1] = s;
        ai[k1] = i;
        idCount = 1 + idCount;
_L4:
        return;
_L2:
        if (s1.equals(s)) goto _L4; else goto _L3
_L3:
        k1 = j1 & k1 + l1;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public int getAttribute(int i, String s, String s1)
    {
        String s2;
        String s3;
        if (s == null)
        {
            s2 = null;
        } else
        {
            s2 = s.intern();
        }
        s3 = null;
        if (s1 != null)
        {
            s3 = s1.intern();
        }
        return getAttributeI(i, s2, s3);
    }

    public int getAttributeI(int i, String s, String s1)
    {
        int j = firstAttributePos(i);
_L6:
        if (j != 0 && getNextKind(j) == 35) goto _L2; else goto _L1
_L1:
        j = 0;
_L4:
        return j;
_L2:
        if ((s1 == null || posLocalName(j) == s1) && (s == null || posNamespaceURI(j) == s)) goto _L4; else goto _L3
_L3:
        j = nextPos(j);
        if (true) goto _L6; else goto _L5
_L5:
    }

    public int getId()
    {
        if (id == 0)
        {
            int i = 1 + counter;
            counter = i;
            id = i;
        }
        return id;
    }

    public SeqPosition getIteratorAtPos(int i)
    {
        return KNode.make(this, i);
    }

    public int lookupID(String s)
    {
        String as[] = idNames;
        int ai[] = idOffsets;
        int i = idNames.length;
        int j = s.hashCode();
        int k = i - 1;
        int l = j & k;
        int i1 = 1 | ~j << 1;
        do
        {
            String s1 = as[l];
            if (s1 == null)
            {
                return -1;
            }
            if (s1.equals(s))
            {
                return ai[l];
            }
            l = k & l + i1;
        } while (true);
    }

    public void makeIDtableIfNeeded()
    {
        if (idNames == null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        idNames = new String[64];
        idOffsets = new int[64];
        int i = endPos();
        int j = 0;
        do
        {
            j = nextMatching(j, ElementType.anyElement, i, true);
            if (j == 0)
            {
                continue;
            }
            int k = getAttributeI(j, "http://www.w3.org/XML/1998/namespace", "id");
            if (k != 0)
            {
                enterID(KNode.getNodeValue(this, k), j);
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
    }

    public int nextPos(int i)
    {
        if ((i & 1) == 0);
        int j = posToDataIndex(i);
        int k = nextNodeIndex(j, 0x7fffffff);
        int i1;
        if (k != j)
        {
            i1 = k << 1;
        } else
        {
            int l = data.length;
            i1 = 0;
            if (j != l)
            {
                return 3 + (j << 1);
            }
        }
        return i1;
    }

    public int posFirstChild(int i)
    {
        int j = gotoChildrenStart(posToDataIndex(i));
        char c;
        if (j >= 0)
        {
            if ((c = data[j]) != '\uF10B' && c != '\uF10C' && c != '\uF111')
            {
                return j << 1;
            }
        }
        return -1;
    }

    public boolean posHasAttributes(int i)
    {
        for (int j = gotoAttributesStart(posToDataIndex(i)); j < 0 || j < 0 || data[j] != '\uF109';)
        {
            return false;
        }

        return true;
    }

    public boolean posIsDefaultNamespace(int i, String s)
    {
        throw new Error("posIsDefaultNamespace not implemented");
    }

    public String posLocalName(int i)
    {
        Object obj = getNextTypeObject(i);
        if (obj instanceof XName)
        {
            return ((XName)obj).getLocalPart();
        }
        if (obj instanceof Symbol)
        {
            return ((Symbol)obj).getLocalName();
        } else
        {
            return getNextTypeName(i);
        }
    }

    public String posLookupNamespaceURI(int i, String s)
    {
        if (getNextKind(i) != 33)
        {
            throw new IllegalArgumentException("argument must be an element");
        }
        Object obj = getNextTypeObject(i);
        if (obj instanceof XName)
        {
            return ((XName)obj).lookupNamespaceURI(s);
        } else
        {
            return null;
        }
    }

    public String posLookupPrefix(int i, String s)
    {
        throw new Error("posLookupPrefix not implemented");
    }

    public String posNamespaceURI(int i)
    {
        Object obj = getNextTypeObject(i);
        if (obj instanceof XName)
        {
            return ((XName)obj).getNamespaceURI();
        }
        if (obj instanceof Symbol)
        {
            return ((Symbol)obj).getNamespaceURI();
        } else
        {
            return null;
        }
    }

    public String posPrefix(int i)
    {
        String s = getNextTypeName(i);
        int j;
        if (s != null)
        {
            if ((j = s.indexOf(':')) >= 0)
            {
                return s.substring(0, j);
            }
        }
        return null;
    }

    public String posTarget(int i)
    {
        int j = posToDataIndex(i);
        if (data[j] != '\uF114')
        {
            throw new ClassCastException("expected process-instruction");
        } else
        {
            return (String)objects[getIntN(j + 1)];
        }
    }

    public int stableCompare(AbstractSequence abstractsequence)
    {
        if (this == abstractsequence)
        {
            return 0;
        }
        int i = super.stableCompare(abstractsequence);
        if (i == 0 && (abstractsequence instanceof NodeTree))
        {
            int j = getId();
            int k = ((NodeTree)abstractsequence).getId();
            if (j < k)
            {
                i = -1;
            } else
            if (j > k)
            {
                i = 1;
            } else
            {
                i = 0;
            }
        }
        return i;
    }

    public String toString()
    {
        CharArrayOutPort chararrayoutport = new CharArrayOutPort();
        consume(new XMLPrinter(chararrayoutport));
        chararrayoutport.close();
        return chararrayoutport.toString();
    }

    public Object typedValue(int i)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringValue(posToDataIndex(i), stringbuffer);
        String s = stringbuffer.toString();
        int j = getNextKind(i);
        if (j == 37 || j == 36)
        {
            return s;
        } else
        {
            return new UntypedAtomic(s);
        }
    }
}
