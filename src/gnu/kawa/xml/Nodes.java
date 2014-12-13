// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.lists.SeqPosition;
import gnu.lists.Sequence;
import gnu.lists.TreeList;
import gnu.mapping.Values;
import gnu.xml.NodeTree;
import gnu.xml.XMLFilter;
import java.io.IOException;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// Referenced classes of package gnu.kawa.xml:
//            KNode

public class Nodes extends Values
    implements NodeList
{

    static final int POS_SIZE = 5;
    int count;
    XMLFilter curFragment;
    NodeTree curNode;
    boolean inAttribute;
    int nesting;

    public Nodes()
    {
        nesting = 0;
    }

    private void maybeEndNonTextNode()
    {
        int i = -1 + nesting;
        nesting = i;
        if (i == 0)
        {
            finishFragment();
        }
    }

    private void maybeStartNonTextNode()
    {
        if (curFragment != null && nesting == 0)
        {
            finishFragment();
        }
        if (curFragment == null)
        {
            startFragment();
        }
        nesting = 1 + nesting;
    }

    public static KNode root(NodeTree nodetree, int i)
    {
        byte byte0;
        if (nodetree.gapStart > 5 && nodetree.data[0] == '\uF112')
        {
            byte0 = 10;
        } else
        {
            byte0 = 0;
        }
        return KNode.make(nodetree, byte0);
    }

    public Consumer append(CharSequence charsequence, int i, int j)
    {
        maybeStartTextNode();
        curFragment.write(charsequence, i, j);
        return this;
    }

    public volatile Appendable append(CharSequence charsequence, int i, int j)
        throws IOException
    {
        return append(charsequence, i, j);
    }

    public void beginEntity(Object obj)
    {
        maybeStartNonTextNode();
        curFragment.beginEntity(obj);
    }

    public void endAttribute()
    {
        if (!inAttribute)
        {
            return;
        } else
        {
            inAttribute = false;
            curFragment.endAttribute();
            maybeEndNonTextNode();
            return;
        }
    }

    public void endDocument()
    {
        curFragment.endDocument();
        maybeEndNonTextNode();
    }

    public void endElement()
    {
        curFragment.endElement();
        maybeEndNonTextNode();
    }

    public void endEntity()
    {
        curFragment.endEntity();
        maybeEndNonTextNode();
    }

    public int find(Object obj)
    {
        if (gapStart <= 0) goto _L2; else goto _L1
_L1:
        int i = getIntN(1 + (-5 + gapStart));
        if (objects[i] != obj) goto _L2; else goto _L3
_L3:
        return i;
_L2:
        if (gapEnd >= data.length || objects[i = getIntN(1 + gapEnd)] != obj)
        {
            return super.find(obj);
        }
        if (true) goto _L3; else goto _L4
_L4:
    }

    void finishFragment()
    {
        curNode = null;
        curFragment = null;
    }

    public Object get(int i)
    {
        int j = i * 5;
        if (j >= gapStart)
        {
            j += gapEnd - gapStart;
        }
        if (j < 0 || j >= data.length)
        {
            throw new IndexOutOfBoundsException();
        }
        if (data[j] != '\uF10F')
        {
            throw new RuntimeException("internal error - unexpected data");
        } else
        {
            return KNode.make((NodeTree)objects[getIntN(j + 1)], getIntN(j + 3));
        }
    }

    public int getLength()
    {
        return count;
    }

    public int getPos(int i)
    {
        int j = i * 5;
        if (j >= gapStart)
        {
            j += gapEnd - gapStart;
        }
        if (data[j] != '\uF10F')
        {
            throw new RuntimeException("internal error - unexpected data");
        } else
        {
            return getIntN(j + 3);
        }
    }

    public Object getPosNext(int i)
    {
        int j = posToDataIndex(i);
        if (j == data.length)
        {
            return Sequence.eofValue;
        }
        if (data[j] != '\uF10F')
        {
            throw new RuntimeException("internal error - unexpected data");
        } else
        {
            return KNode.make((NodeTree)objects[getIntN(j + 1)], getIntN(j + 3));
        }
    }

    public AbstractSequence getSeq(int i)
    {
        int j = i * 5;
        if (j >= gapStart)
        {
            j += gapEnd - gapStart;
        }
        if (j < 0 || j >= data.length)
        {
            return null;
        }
        if (data[j] != '\uF10F')
        {
            throw new RuntimeException("internal error - unexpected data");
        } else
        {
            return (AbstractSequence)objects[getIntN(j + 1)];
        }
    }

    void handleNonNode()
    {
        if (curFragment == null)
        {
            throw new ClassCastException("atomic value where node is required");
        } else
        {
            return;
        }
    }

    public Node item(int i)
    {
        if (i >= count)
        {
            return null;
        } else
        {
            return (Node)get(i);
        }
    }

    void maybeStartTextNode()
    {
        if (curFragment == null)
        {
            throw new IllegalArgumentException("non-node where node required");
        } else
        {
            return;
        }
    }

    public int size()
    {
        return count;
    }

    public void startAttribute(Object obj)
    {
        maybeStartNonTextNode();
        curFragment.startAttribute(obj);
        inAttribute = true;
    }

    public void startDocument()
    {
        maybeStartNonTextNode();
        curFragment.startDocument();
    }

    public void startElement(Object obj)
    {
        maybeStartNonTextNode();
        curFragment.startElement(obj);
    }

    void startFragment()
    {
        curNode = new NodeTree();
        curFragment = new XMLFilter(curNode);
        writePosition(curNode, 0);
    }

    public void write(int i)
    {
        maybeStartTextNode();
        curFragment.write(i);
    }

    public void write(CharSequence charsequence, int i, int j)
    {
        maybeStartTextNode();
        curFragment.write(charsequence, i, j);
    }

    public void write(String s)
    {
        maybeStartTextNode();
        curFragment.write(s);
    }

    public void write(char ac[], int i, int j)
    {
        maybeStartTextNode();
        curFragment.write(ac, i, j);
    }

    public void writeBoolean(boolean flag)
    {
        handleNonNode();
        curFragment.writeBoolean(flag);
    }

    public void writeCDATA(char ac[], int i, int j)
    {
        maybeStartNonTextNode();
        curFragment.writeCDATA(ac, i, j);
    }

    public void writeComment(char ac[], int i, int j)
    {
        maybeStartNonTextNode();
        curFragment.writeComment(ac, i, j);
        maybeEndNonTextNode();
    }

    public void writeDouble(double d)
    {
        handleNonNode();
        curFragment.writeDouble(d);
    }

    public void writeFloat(float f)
    {
        handleNonNode();
        curFragment.writeFloat(f);
    }

    public void writeInt(int i)
    {
        handleNonNode();
        curFragment.writeInt(i);
    }

    public void writeLong(long l)
    {
        handleNonNode();
        curFragment.writeLong(l);
    }

    public void writeObject(Object obj)
    {
label0:
        {
label1:
            {
                if (curFragment != null)
                {
                    if (nesting != 0 || !(obj instanceof SeqPosition) && !(obj instanceof TreeList))
                    {
                        break label1;
                    }
                    finishFragment();
                }
                if (obj instanceof SeqPosition)
                {
                    SeqPosition seqposition = (SeqPosition)obj;
                    writePosition(seqposition.sequence, seqposition.ipos);
                    return;
                }
                break label0;
            }
            curFragment.writeObject(obj);
            return;
        }
        if (obj instanceof TreeList)
        {
            writePosition((TreeList)obj, 0);
            return;
        } else
        {
            handleNonNode();
            curFragment.writeObject(obj);
            return;
        }
    }

    public void writePosition(AbstractSequence abstractsequence, int i)
    {
        count = 1 + count;
        super.writePosition(abstractsequence, i);
    }

    public void writeProcessingInstruction(String s, char ac[], int i, int j)
    {
        maybeStartNonTextNode();
        curFragment.writeProcessingInstruction(s, ac, i, j);
        maybeEndNonTextNode();
    }
}
