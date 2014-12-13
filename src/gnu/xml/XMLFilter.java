// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.xml;

import gnu.expr.Keyword;
import gnu.kawa.sax.ContentConsumer;
import gnu.lists.AbstractSequence;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.TreeList;
import gnu.lists.UnescapedData;
import gnu.lists.XConsumer;
import gnu.mapping.Symbol;
import gnu.text.Char;
import gnu.text.LineBufferedReader;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.xml.sax.AttributeList;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.DocumentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

// Referenced classes of package gnu.xml:
//            MappingInfo, NodeTree, NamespaceBinding, XName, 
//            TextUtils

public class XMLFilter
    implements DocumentHandler, ContentHandler, SourceLocator, XConsumer, PositionConsumer
{

    public static final int COPY_NAMESPACES_INHERIT = 2;
    public static final int COPY_NAMESPACES_PRESERVE = 1;
    private static final int SAW_KEYWORD = 1;
    private static final int SAW_WORD = 2;
    int attrCount;
    String attrLocalName;
    String attrPrefix;
    Consumer base;
    public transient int copyNamespacesMode;
    String currentNamespacePrefix;
    protected int ignoringLevel;
    LineBufferedReader in;
    boolean inStartTag;
    SourceLocator locator;
    MappingInfo mappingTable[];
    int mappingTableMask;
    private SourceMessages messages;
    boolean mismatchReported;
    NamespaceBinding namespaceBindings;
    public boolean namespacePrefixes;
    protected int nesting;
    public Consumer out;
    int previous;
    int startIndexes[];
    protected int stringizingElementNesting;
    protected int stringizingLevel;
    TreeList tlist;
    Object workStack[];

    public XMLFilter(Consumer consumer)
    {
        copyNamespacesMode = 1;
        previous = 0;
        stringizingElementNesting = -1;
        startIndexes = null;
        attrCount = -1;
        namespacePrefixes = false;
        mappingTable = new MappingInfo[128];
        mappingTableMask = -1 + mappingTable.length;
        base = consumer;
        out = consumer;
        if (consumer instanceof NodeTree)
        {
            tlist = (NodeTree)consumer;
        } else
        {
            tlist = new TreeList();
        }
        namespaceBindings = NamespaceBinding.predefinedXML;
    }

    public static String duplicateAttributeMessage(Symbol symbol, Object obj)
    {
        StringBuffer stringbuffer = new StringBuffer("duplicate attribute: ");
        String s = symbol.getNamespaceURI();
        if (s != null && s.length() > 0)
        {
            stringbuffer.append('{');
            stringbuffer.append('}');
            stringbuffer.append(s);
        }
        stringbuffer.append(symbol.getLocalPart());
        if (obj != null)
        {
            stringbuffer.append(" in <");
            stringbuffer.append(obj);
            stringbuffer.append('>');
        }
        return stringbuffer.toString();
    }

    private void ensureSpaceInStartIndexes(int i)
    {
        if (startIndexes == null)
        {
            startIndexes = new int[20];
        } else
        if (i >= startIndexes.length)
        {
            int ai[] = new int[2 * startIndexes.length];
            System.arraycopy(startIndexes, 0, ai, 0, i);
            startIndexes = ai;
            return;
        }
    }

    private void ensureSpaceInWorkStack(int i)
    {
        if (workStack == null)
        {
            workStack = new Object[20];
        } else
        if (i >= workStack.length)
        {
            Object aobj[] = new Object[2 * workStack.length];
            System.arraycopy(((Object) (workStack)), 0, ((Object) (aobj)), 0, i);
            workStack = aobj;
            return;
        }
    }

    private NamespaceBinding mergeHelper(NamespaceBinding namespacebinding, NamespaceBinding namespacebinding1)
    {
        if (namespacebinding1 == NamespaceBinding.predefinedXML)
        {
            return namespacebinding;
        }
        NamespaceBinding namespacebinding2 = mergeHelper(namespacebinding, namespacebinding1.next);
        String s = namespacebinding1.uri;
        if (namespacebinding2 == null)
        {
            if (s == null)
            {
                return namespacebinding2;
            }
            namespacebinding2 = NamespaceBinding.predefinedXML;
        }
        String s1 = namespacebinding1.prefix;
        String s2 = namespacebinding2.resolve(s1);
        if (s2 != null ? s2.equals(s) : s == null)
        {
            return namespacebinding2;
        } else
        {
            return findNamespaceBinding(s1, s, namespacebinding2);
        }
    }

    private String resolve(String s, boolean flag)
    {
        String s1;
        if (flag && s == null)
        {
            s1 = "";
        } else
        {
            s1 = namespaceBindings.resolve(s);
            if (s1 == null)
            {
                if (s != null)
                {
                    error('e', (new StringBuilder()).append("unknown namespace prefix '").append(s).append('\'').toString());
                }
                return "";
            }
        }
        return s1;
    }

    private boolean startAttributeCommon()
    {
        if (stringizingElementNesting >= 0)
        {
            ignoringLevel = 1 + ignoringLevel;
        }
        int i = stringizingLevel;
        stringizingLevel = i + 1;
        if (i > 0)
        {
            return false;
        }
        if (attrCount < 0)
        {
            attrCount = 0;
        }
        ensureSpaceInWorkStack(nesting + attrCount);
        ensureSpaceInStartIndexes(attrCount);
        startIndexes[attrCount] = tlist.gapStart;
        attrCount = 1 + attrCount;
        return true;
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

    public XMLFilter append(char c)
    {
        write(c);
        return this;
    }

    public XMLFilter append(CharSequence charsequence)
    {
        if (charsequence == null)
        {
            charsequence = "null";
        }
        append(charsequence, 0, charsequence.length());
        return this;
    }

    public XMLFilter append(CharSequence charsequence, int i, int j)
    {
        if (charsequence == null)
        {
            charsequence = "null";
        }
        write(charsequence, i, j - i);
        return this;
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

    public void beginEntity(Object obj)
    {
        if (base instanceof XConsumer)
        {
            ((XConsumer)base).beginEntity(obj);
        }
    }

    public void characters(char ac[], int i, int j)
        throws SAXException
    {
        write(ac, i, j);
    }

    protected void checkValidComment(char ac[], int i, int j)
    {
        int k = j;
        boolean flag = true;
        do
        {
            boolean flag1;
label0:
            {
                if (--k >= 0)
                {
                    if (ac[i + k] == '-')
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (!flag || !flag1)
                    {
                        break label0;
                    }
                    error('e', "consecutive or final hyphen in XML comment");
                }
                return;
            }
            flag = flag1;
        } while (true);
    }

    protected boolean checkWriteAtomic()
    {
        previous = 0;
        if (ignoringLevel > 0)
        {
            return false;
        } else
        {
            closeStartTag();
            return true;
        }
    }

    void closeStartTag()
    {
        NamespaceBinding namespacebinding;
        NamespaceBinding namespacebinding1;
        int i;
        Symbol symbol3;
        String s4;
        String s5;
        if (attrCount < 0 || stringizingLevel > 0)
        {
            return;
        }
        inStartTag = false;
        previous = 0;
        if (attrLocalName != null)
        {
            endAttribute();
        }
        Object obj5;
        if (nesting == 0)
        {
            namespacebinding = NamespaceBinding.predefinedXML;
        } else
        {
            namespacebinding = (NamespaceBinding)workStack[-2 + nesting];
        }
        namespacebinding1 = namespaceBindings;
        i = 0;
        if (i > attrCount) goto _L2; else goto _L1
_L1:
        obj5 = workStack[-1 + (i + nesting)];
        if (!(obj5 instanceof Symbol)) goto _L4; else goto _L3
_L3:
        symbol3 = (Symbol)obj5;
        s4 = symbol3.getPrefix();
        if (s4 == "")
        {
            s4 = null;
        }
        s5 = symbol3.getNamespaceURI();
        if (s5 == "")
        {
            s5 = null;
        }
        if (i <= 0 || s4 != null || s5 != null) goto _L5; else goto _L4
_L4:
        i++;
        break MISSING_BLOCK_LABEL_54;
_L5:
        boolean flag2;
        NamespaceBinding namespacebinding2;
        flag2 = false;
        namespacebinding2 = namespacebinding1;
_L14:
        if (namespacebinding2 == namespacebinding)
        {
            flag2 = true;
        }
        if (namespacebinding2 == null)
        {
            if (s4 != null || s5 != null)
            {
                namespacebinding1 = findNamespaceBinding(s4, s5, namespacebinding1);
            }
        } else
        {
            if (namespacebinding2.prefix != s4)
            {
                break MISSING_BLOCK_LABEL_387;
            }
            if (namespacebinding2.uri != s5)
            {
label0:
                {
                    if (!flag2)
                    {
                        break label0;
                    }
                    namespacebinding1 = findNamespaceBinding(s4, s5, namespacebinding1);
                }
            }
        }
        continue; /* Loop/switch isn't completed */
        NamespaceBinding namespacebinding3 = namespacebinding1;
_L13:
        if (namespacebinding3 != null) goto _L7; else goto _L6
_L6:
        int j2 = 1;
_L10:
        String s6 = (new StringBuilder()).append("_ns_").append(j2).toString().intern();
        if (namespacebinding1.resolve(s6) != null) goto _L9; else goto _L8
_L8:
        namespacebinding1 = findNamespaceBinding(s6, s5, namespacebinding1);
        String s7 = symbol3.getLocalName();
        if (s5 == null)
        {
            s5 = "";
        }
        workStack[-1 + (i + nesting)] = Symbol.make(s5, s7, s6);
        continue; /* Loop/switch isn't completed */
_L9:
        j2++;
          goto _L10
_L7:
        if (namespacebinding3.uri != s5)
        {
            break; /* Loop/switch isn't completed */
        }
        s6 = namespacebinding3.prefix;
        if (namespacebinding1.resolve(s6) == s5) goto _L8; else goto _L11
_L11:
        namespacebinding3 = namespacebinding3.next;
        if (true) goto _L13; else goto _L12
_L12:
        namespacebinding2 = namespacebinding2.next;
        if (true) goto _L14; else goto _L2
_L2:
        int j = 0;
_L35:
        Object obj;
        if (j > attrCount)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = workStack[-1 + (j + nesting)];
        if (!(obj instanceof MappingInfo) && out != tlist) goto _L16; else goto _L15
_L15:
        String s;
        String s1;
        String s2;
        boolean flag;
        int l;
        MappingInfo mappinginfo1;
        Object obj3;
        Symbol symbol1;
        MappingInfo mappinginfo;
        if (obj instanceof MappingInfo)
        {
            mappinginfo = (MappingInfo)obj;
            s = mappinginfo.prefix;
            s1 = mappinginfo.local;
            int i1;
            XName xname;
            int j1;
            String s3;
            Object obj4;
            if (j > 0 && (s == null && s1 == "xmlns" || s == "xmlns"))
            {
                flag = true;
                s2 = "(namespace-node)";
            } else
            {
                boolean flag1;
                if (j > 0)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                s2 = resolve(s, flag1);
                flag = false;
            }
        } else
        {
            Symbol symbol = (Symbol)obj;
            mappinginfo = lookupTag(symbol);
            s = mappinginfo.prefix;
            s1 = mappinginfo.local;
            s2 = symbol.getNamespaceURI();
            flag = false;
        }
        l = mappinginfo.tagHash;
        i1 = l & mappingTableMask;
        mappinginfo1 = mappingTable[i1];
_L28:
        if (mappinginfo1 != null) goto _L18; else goto _L17
_L17:
        mappinginfo1 = new MappingInfo();
        mappinginfo1.tagHash = l;
        mappinginfo1.prefix = s;
        mappinginfo1.local = s1;
        mappinginfo1.nextInBucket = mappingTable[i1];
        mappingTable[i1] = mappinginfo1;
        mappinginfo1.uri = s2;
        mappinginfo1.qname = Symbol.make(s2, s1, s);
        if (j == 0)
        {
            xname = new XName(mappinginfo1.qname, namespacebinding1);
            mappinginfo1.type = xname;
            mappinginfo1.namespaces = namespacebinding1;
        }
_L27:
        workStack[-1 + (j + nesting)] = mappinginfo1;
_L30:
        j1 = 1;
_L21:
        if (j1 >= j)
        {
            break MISSING_BLOCK_LABEL_1074;
        }
        obj3 = workStack[-1 + (j1 + nesting)];
        if (!(obj3 instanceof Symbol)) goto _L20; else goto _L19
_L19:
        symbol1 = (Symbol)obj3;
_L33:
        if (s1 == symbol1.getLocalPart())
        {
            s3 = symbol1.getNamespaceURI();
            if (s2 == s3)
            {
                obj4 = workStack[-1 + nesting];
                if (obj4 instanceof MappingInfo)
                {
                    obj4 = ((MappingInfo)obj4).qname;
                }
                error('e', duplicateAttributeMessage(symbol1, obj4));
            }
        }
_L32:
        j1++;
          goto _L21
_L18:
        if (mappinginfo1.tagHash != l || mappinginfo1.local != s1 || mappinginfo1.prefix != s) goto _L23; else goto _L22
_L22:
        if (mappinginfo1.uri != null) goto _L25; else goto _L24
_L24:
        mappinginfo1.uri = s2;
        mappinginfo1.qname = Symbol.make(s2, s1, s);
_L29:
        if (j != 0)
        {
            break MISSING_BLOCK_LABEL_1014;
        }
        if (mappinginfo1.namespaces != namespacebinding1 && mappinginfo1.namespaces != null) goto _L23; else goto _L26
_L26:
        XName xname1 = mappinginfo1.type;
        mappinginfo1.namespaces = namespacebinding1;
        if (xname1 == null)
        {
            XName xname2 = new XName(mappinginfo1.qname, namespacebinding1);
            mappinginfo1.type = xname2;
        }
          goto _L27
_L25:
        if (mappinginfo1.uri == s2)
        {
            break MISSING_BLOCK_LABEL_989;
        }
_L23:
        mappinginfo1 = mappinginfo1.nextInBucket;
          goto _L28
        if (mappinginfo1.qname == null)
        {
            mappinginfo1.qname = Symbol.make(s2, s1, s);
        }
          goto _L29
        mappinginfo1.qname;
          goto _L27
_L16:
        Symbol symbol2 = (Symbol)obj;
        s2 = symbol2.getNamespaceURI();
        s1 = symbol2.getLocalName();
        mappinginfo1 = null;
        flag = false;
          goto _L30
_L20:
        if (!(obj3 instanceof MappingInfo)) goto _L32; else goto _L31
_L31:
        symbol1 = ((MappingInfo)obj3).qname;
          goto _L33
        if (out == tlist)
        {
            Object obj2;
            int i2;
            if (j == 0)
            {
                obj2 = mappinginfo1.type;
            } else
            {
                obj2 = mappinginfo1.qname;
            }
            i2 = mappinginfo1.index;
            if (i2 <= 0 || tlist.objects[i2] != obj2)
            {
                i2 = tlist.find(obj2);
                mappinginfo1.index = i2;
            }
            if (j == 0)
            {
                tlist.setElementName(tlist.gapEnd, i2);
            } else
            if (!flag || namespacePrefixes)
            {
                tlist.setAttributeName(startIndexes[j - 1], i2);
            }
        } else
        {
            Object obj1;
            if (mappinginfo1 == null)
            {
                obj1 = obj;
            } else
            if (j == 0)
            {
                obj1 = mappinginfo1.type;
            } else
            {
                obj1 = mappinginfo1.qname;
            }
            if (j == 0)
            {
                out.startElement(obj1);
            } else
            if (!flag || namespacePrefixes)
            {
                out.startAttribute(obj1);
                int k1 = startIndexes[j - 1];
                int l1;
                if (j < attrCount)
                {
                    l1 = startIndexes[j];
                } else
                {
                    l1 = tlist.gapStart;
                }
                tlist.consumeIRange(k1 + 5, l1 - 1, out);
                out.endAttribute();
            }
        }
        j++;
        if (true) goto _L35; else goto _L34
_L34:
        if (out instanceof ContentConsumer)
        {
            ((ContentConsumer)out).endStartTag();
        }
        for (int k = 1; k <= attrCount; k++)
        {
            workStack[-1 + (k + nesting)] = null;
        }

        if (out != tlist)
        {
            base = out;
            tlist.clear();
        }
        attrCount = -1;
        return;
        if (true) goto _L4; else goto _L36
_L36:
    }

    public void commentFromParser(char ac[], int i, int j)
    {
        if (stringizingLevel == 0)
        {
            closeStartTag();
            if (base instanceof XConsumer)
            {
                ((XConsumer)base).writeComment(ac, i, j);
            }
        } else
        if (stringizingElementNesting < 0)
        {
            base.write(ac, i, j);
            return;
        }
    }

    public void consume(SeqPosition seqposition)
    {
        writePosition(seqposition.sequence, seqposition.ipos);
    }

    public void emitCharacterReference(int i, char ac[], int j, int k)
    {
        if (i >= 0x10000)
        {
            Char.print(i, this);
            return;
        } else
        {
            write(i);
            return;
        }
    }

    public void emitDoctypeDecl(char ac[], int i, int j, int k, int l)
    {
    }

    public void emitEndAttributes()
    {
        if (attrLocalName != null)
        {
            endAttribute();
        }
        closeStartTag();
    }

    public void emitEndElement(char ac[], int i, int j)
    {
        if (attrLocalName != null)
        {
            error('e', "unclosed attribute");
            endAttribute();
        }
        if (!inElement())
        {
            error('e', "unmatched end element");
        } else
        {
            if (ac != null)
            {
                MappingInfo mappinginfo = lookupTag(ac, i, j);
                Object obj = workStack[-1 + nesting];
                if ((obj instanceof MappingInfo) && !mismatchReported)
                {
                    MappingInfo mappinginfo1 = (MappingInfo)obj;
                    if (mappinginfo.local != mappinginfo1.local || mappinginfo.prefix != mappinginfo1.prefix)
                    {
                        StringBuffer stringbuffer = new StringBuffer("</");
                        stringbuffer.append(ac, i, j);
                        stringbuffer.append("> matching <");
                        String s = mappinginfo1.prefix;
                        if (s != null)
                        {
                            stringbuffer.append(s);
                            stringbuffer.append(':');
                        }
                        stringbuffer.append(mappinginfo1.local);
                        stringbuffer.append('>');
                        error('e', stringbuffer.toString());
                        mismatchReported = true;
                    }
                }
            }
            closeStartTag();
            if (nesting > 0)
            {
                endElement();
                return;
            }
        }
    }

    public void emitEntityReference(char ac[], int i, int j)
    {
        char c;
        byte byte0;
        c = ac[i];
        byte0 = 63;
        if (j != 2 || ac[i + 1] != 't') goto _L2; else goto _L1
_L1:
        if (c != 'l') goto _L4; else goto _L3
_L3:
        byte0 = 60;
_L6:
        write(byte0);
        return;
_L4:
        if (c == 'g')
        {
            byte0 = 62;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (j == 3)
        {
            if (c == 'a' && ac[i + 1] == 'm' && ac[i + 2] == 'p')
            {
                byte0 = 38;
            }
        } else
        if (j == 4)
        {
            char c1 = ac[i + 1];
            char c2 = ac[i + 2];
            char c3 = ac[i + 3];
            if (c == 'q' && c1 == 'u' && c2 == 'o' && c3 == 't')
            {
                byte0 = 34;
            } else
            if (c == 'a' && c1 == 'p' && c2 == 'o' && c3 == 's')
            {
                byte0 = 39;
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public void emitStartAttribute(char ac[], int i, int j)
    {
        if (attrLocalName != null)
        {
            endAttribute();
        }
        if (startAttributeCommon())
        {
            MappingInfo mappinginfo = lookupTag(ac, i, j);
            workStack[-1 + (nesting + attrCount)] = mappinginfo;
            String s = mappinginfo.prefix;
            String s1 = mappinginfo.local;
            attrLocalName = s1;
            attrPrefix = s;
            if (s != null)
            {
                if (s == "xmlns")
                {
                    currentNamespacePrefix = s1;
                }
            } else
            if (s1 == "xmlns" && s == null)
            {
                currentNamespacePrefix = "";
            }
            if (currentNamespacePrefix == null || namespacePrefixes)
            {
                tlist.startAttribute(0);
                return;
            }
        }
    }

    public void emitStartElement(char ac[], int i, int j)
    {
        closeStartTag();
        MappingInfo mappinginfo = lookupTag(ac, i, j);
        startElementCommon();
        ensureSpaceInWorkStack(-1 + nesting);
        workStack[-1 + nesting] = mappinginfo;
    }

    public void endAttribute()
    {
        if (attrLocalName != null) goto _L2; else goto _L1
_L1:
        return;
_L2:
        int i;
        if (previous == 1)
        {
            previous = 0;
            return;
        }
        if (stringizingElementNesting >= 0)
        {
            ignoringLevel = -1 + ignoringLevel;
        }
        i = -1 + stringizingLevel;
        stringizingLevel = i;
        if (i != 0) goto _L1; else goto _L3
_L3:
        if (attrLocalName != "id" || attrPrefix != "xml") goto _L5; else goto _L4
_L4:
        int i2;
        int j2;
        char ac1[];
        int k2;
        i2 = 5 + startIndexes[-1 + attrCount];
        j2 = tlist.gapStart;
        ac1 = tlist.data;
        k2 = i2;
_L7:
        int j;
        int k;
        int l;
        int i1;
        char ac[];
        int j1;
        int k1;
        if (k2 < j2)
        {
label0:
            {
                l2 = k2 + 1;
                char c1 = ac1[k2];
                if ((0xffff & c1) <= 40959 && c1 != '\t' && c1 != '\r' && c1 != '\n' && (c1 != ' ' || l2 != j2 && ac1[l2] != ' '))
                {
                    break label0;
                }
                StringBuffer stringbuffer1 = new StringBuffer();
                tlist.stringValue(i2, j2, stringbuffer1);
                tlist.gapStart = i2;
                tlist.write(TextUtils.replaceWhitespace(stringbuffer1.toString(), true));
            }
        }
_L5:
        attrLocalName = null;
        attrPrefix = null;
        if (currentNamespacePrefix == null || namespacePrefixes)
        {
            tlist.endAttribute();
        }
        if (currentNamespacePrefix == null) goto _L1; else goto _L6
_L6:
        j = startIndexes[-1 + attrCount];
        k = j;
        l = tlist.gapStart;
        i1 = l - k;
        ac = tlist.data;
        j1 = 0;
        k1 = k;
_L8:
        char c;
        if (k1 < l)
        {
            c = ac[k1];
            if ((0xffff & c) <= 40959)
            {
                break MISSING_BLOCK_LABEL_465;
            }
            StringBuffer stringbuffer = new StringBuffer();
            tlist.stringValue(k, l, stringbuffer);
            j1 = stringbuffer.hashCode();
            k = 0;
            i1 = stringbuffer.length();
            int l1 = i1;
            ac = new char[stringbuffer.length()];
            stringbuffer.getChars(0, l1, ac, 0);
        }
        tlist.gapStart = j;
        String s;
        int l2;
        if (currentNamespacePrefix == "")
        {
            s = null;
        } else
        {
            s = currentNamespacePrefix;
        }
        namespaceBindings = lookupNamespaceBinding(s, ac, k, i1, j1, namespaceBindings).namespaces;
        currentNamespacePrefix = null;
        return;
        k2 = l2;
          goto _L7
        j1 = c + j1 * 31;
        k1++;
          goto _L8
    }

    public void endDocument()
    {
        if (stringizingLevel > 0)
        {
            writeJoiner();
            return;
        }
        nesting = -2 + nesting;
        namespaceBindings = (NamespaceBinding)workStack[nesting];
        workStack[nesting] = null;
        workStack[1 + nesting] = null;
        if (nesting == 0)
        {
            base.endDocument();
            return;
        } else
        {
            writeJoiner();
            return;
        }
    }

    public void endElement()
    {
        closeStartTag();
        nesting = -2 + nesting;
        previous = 0;
        if (stringizingLevel == 0)
        {
            namespaceBindings = (NamespaceBinding)workStack[nesting];
            workStack[nesting] = null;
            workStack[1 + nesting] = null;
            base.endElement();
        } else
        if (stringizingElementNesting == nesting)
        {
            stringizingElementNesting = -1;
            previous = 2;
            return;
        }
    }

    public void endElement(String s)
        throws SAXException
    {
        endElement();
    }

    public void endElement(String s, String s1, String s2)
    {
        endElement();
    }

    public void endEntity()
    {
        if (base instanceof XConsumer)
        {
            ((XConsumer)base).endEntity();
        }
    }

    public void endPrefixMapping(String s)
    {
        namespaceBindings = namespaceBindings.getNext();
    }

    public void error(char c, String s)
    {
        if (messages == null)
        {
            throw new RuntimeException(s);
        }
        if (locator != null)
        {
            messages.error(c, locator, s);
            return;
        } else
        {
            messages.error(c, s);
            return;
        }
    }

    public NamespaceBinding findNamespaceBinding(String s, String s1, NamespaceBinding namespacebinding)
    {
        int i;
        int j;
        MappingInfo mappinginfo;
        if (s1 == null)
        {
            i = 0;
        } else
        {
            i = s1.hashCode();
        }
        if (s != null)
        {
            i ^= s.hashCode();
        }
        j = i & mappingTableMask;
        mappinginfo = mappingTable[j];
        do
        {
            if (mappinginfo == null)
            {
                MappingInfo mappinginfo1 = new MappingInfo();
                mappinginfo1.nextInBucket = mappingTable[j];
                mappingTable[j] = mappinginfo1;
                mappinginfo1.tagHash = i;
                mappinginfo1.prefix = s;
                mappinginfo1.local = s1;
                mappinginfo1.uri = s1;
                if (s1 == "")
                {
                    s1 = null;
                }
                mappinginfo1.namespaces = new NamespaceBinding(s, s1, namespacebinding);
                return mappinginfo1.namespaces;
            }
            if (mappinginfo.tagHash == i && mappinginfo.prefix == s)
            {
                NamespaceBinding namespacebinding1 = mappinginfo.namespaces;
                if (namespacebinding1 != null && namespacebinding1.getNext() == namespaceBindings && namespacebinding1.getPrefix() == s && mappinginfo.uri == s1)
                {
                    return mappinginfo.namespaces;
                }
            }
            mappinginfo = mappinginfo.nextInBucket;
        } while (true);
    }

    public int getColumnNumber()
    {
        if (in != null)
        {
            int i = in.getColumnNumber();
            if (i > 0)
            {
                return i;
            }
        }
        return -1;
    }

    public String getFileName()
    {
        if (in == null)
        {
            return null;
        } else
        {
            return in.getName();
        }
    }

    public int getLineNumber()
    {
        int i;
        if (in != null)
        {
            if ((i = in.getLineNumber()) >= 0)
            {
                return i + 1;
            }
        }
        return -1;
    }

    public String getPublicId()
    {
        return null;
    }

    public String getSystemId()
    {
        if (in == null)
        {
            return null;
        } else
        {
            return in.getName();
        }
    }

    public void ignorableWhitespace(char ac[], int i, int j)
        throws SAXException
    {
        write(ac, i, j);
    }

    public boolean ignoring()
    {
        return ignoringLevel > 0;
    }

    final boolean inElement()
    {
        int i;
        for (i = nesting; i > 0 && workStack[i - 1] == null; i -= 2) { }
        return i != 0;
    }

    public boolean isStableSourceLocation()
    {
        return false;
    }

    public void linefeedFromParser()
    {
        if (inElement() && checkWriteAtomic())
        {
            base.write(10);
        }
    }

    public MappingInfo lookupNamespaceBinding(String s, char ac[], int i, int j, int k, NamespaceBinding namespacebinding)
    {
        int l;
        int i1;
        MappingInfo mappinginfo;
        if (s == null)
        {
            l = k;
        } else
        {
            l = k ^ s.hashCode();
        }
        i1 = l & mappingTableMask;
        mappinginfo = mappingTable[i1];
        do
        {
            if (mappinginfo == null)
            {
                MappingInfo mappinginfo1 = new MappingInfo();
                mappinginfo1.nextInBucket = mappingTable[i1];
                mappingTable[i1] = mappinginfo1;
                String s1 = (new String(ac, i, j)).intern();
                mappinginfo1.tagHash = l;
                mappinginfo1.prefix = s;
                mappinginfo1.local = s1;
                mappinginfo1.uri = s1;
                if (s1 == "")
                {
                    s1 = null;
                }
                mappinginfo1.namespaces = new NamespaceBinding(s, s1, namespacebinding);
                return mappinginfo1;
            }
            if (mappinginfo.tagHash == l && mappinginfo.prefix == s)
            {
                NamespaceBinding namespacebinding1 = mappinginfo.namespaces;
                if (namespacebinding1 != null && namespacebinding1.getNext() == namespaceBindings && namespacebinding1.getPrefix() == s && MappingInfo.equals(mappinginfo.uri, ac, i, j))
                {
                    return mappinginfo;
                }
            }
            mappinginfo = mappinginfo.nextInBucket;
        } while (true);
    }

    MappingInfo lookupTag(Symbol symbol)
    {
        String s = symbol.getLocalPart();
        String s1 = symbol.getPrefix();
        if (s1 == "")
        {
            s1 = null;
        }
        String s2 = symbol.getNamespaceURI();
        int i = MappingInfo.hash(s1, s);
        int j = i & mappingTableMask;
        MappingInfo mappinginfo = mappingTable[j];
        MappingInfo mappinginfo1 = mappinginfo;
        do
        {
            if (mappinginfo1 == null)
            {
                MappingInfo mappinginfo2 = new MappingInfo();
                mappinginfo2.qname = symbol;
                mappinginfo2.prefix = s1;
                mappinginfo2.uri = s2;
                mappinginfo2.local = s;
                mappinginfo2.tagHash = i;
                mappinginfo2.nextInBucket = mappinginfo;
                mappingTable[j] = mappinginfo;
                return mappinginfo2;
            }
            if (symbol == mappinginfo1.qname)
            {
                return mappinginfo1;
            }
            if (s == mappinginfo1.local && mappinginfo1.qname == null && (s2 == mappinginfo1.uri || mappinginfo1.uri == null) && s1 == mappinginfo1.prefix)
            {
                mappinginfo1.uri = s2;
                mappinginfo1.qname = symbol;
                return mappinginfo1;
            }
            mappinginfo1 = mappinginfo1.nextInBucket;
        } while (true);
    }

    MappingInfo lookupTag(char ac[], int i, int j)
    {
        int k = 0;
        int l = 0;
        int i1 = -1;
        int j1 = 0;
        while (j1 < j) 
        {
            char c = ac[i + j1];
            if (c == ':' && i1 < 0)
            {
                i1 = j1;
                l = k;
                k = 0;
            } else
            {
                k = c + k * 31;
            }
            j1++;
        }
        int k1 = k ^ l;
        int l1 = k1 & mappingTableMask;
        MappingInfo mappinginfo = mappingTable[l1];
        MappingInfo mappinginfo1 = mappinginfo;
        do
        {
            if (mappinginfo1 == null)
            {
                MappingInfo mappinginfo2 = new MappingInfo();
                mappinginfo2.tagHash = k1;
                if (i1 >= 0)
                {
                    mappinginfo2.prefix = (new String(ac, i, i1)).intern();
                    int i2 = i1 + 1;
                    mappinginfo2.local = (new String(ac, i + i2, j - i2)).intern();
                } else
                {
                    mappinginfo2.prefix = null;
                    mappinginfo2.local = (new String(ac, i, j)).intern();
                }
                mappinginfo2.nextInBucket = mappinginfo;
                mappingTable[l1] = mappinginfo;
                return mappinginfo2;
            }
            if (k1 == mappinginfo1.tagHash && mappinginfo1.match(ac, i, j))
            {
                return mappinginfo1;
            }
            mappinginfo1 = mappinginfo1.nextInBucket;
        } while (true);
    }

    public void processingInstruction(String s, String s1)
    {
        char ac[] = s1.toCharArray();
        processingInstructionCommon(s, ac, 0, ac.length);
    }

    void processingInstructionCommon(String s, char ac[], int i, int j)
    {
        if (stringizingLevel == 0)
        {
            closeStartTag();
            if (base instanceof XConsumer)
            {
                ((XConsumer)base).writeProcessingInstruction(s, ac, i, j);
            }
        } else
        if (stringizingElementNesting < 0)
        {
            base.write(ac, i, j);
            return;
        }
    }

    public void processingInstructionFromParser(char ac[], int i, int j, int k, int l)
    {
        if (j == 3 && !inElement() && ac[i] == 'x' && ac[i + 1] == 'm' && ac[i + 2] == 'l')
        {
            return;
        } else
        {
            processingInstructionCommon(new String(ac, i, j), ac, k, l);
            return;
        }
    }

    public void setDocumentLocator(Locator locator1)
    {
        if (locator1 instanceof SourceLocator)
        {
            locator = (SourceLocator)locator1;
        }
    }

    public void setMessages(SourceMessages sourcemessages)
    {
        messages = sourcemessages;
    }

    public void setSourceLocator(LineBufferedReader linebufferedreader)
    {
        in = linebufferedreader;
        locator = this;
    }

    public void setSourceLocator(SourceLocator sourcelocator)
    {
        locator = sourcelocator;
    }

    public void skippedEntity(String s)
    {
    }

    public void startAttribute(Object obj)
    {
        previous = 0;
        if (obj instanceof Symbol)
        {
            Symbol symbol = (Symbol)obj;
            String s = symbol.getLocalPart();
            attrLocalName = s;
            attrPrefix = symbol.getPrefix();
            String s1 = symbol.getNamespaceURI();
            if (s1 == "http://www.w3.org/2000/xmlns/" || s1 == "" && s == "xmlns")
            {
                error('e', "arttribute name cannot be 'xmlns' or in xmlns namespace");
            }
        }
        if (nesting == 2 && workStack[1] == null)
        {
            error('e', "attribute not allowed at document level");
        }
        if (attrCount < 0 && nesting > 0)
        {
            error('e', (new StringBuilder()).append("attribute '").append(obj).append("' follows non-attribute content").toString());
        }
        if (!startAttributeCommon())
        {
            return;
        }
        workStack[-1 + (nesting + attrCount)] = obj;
        if (nesting == 0)
        {
            base.startAttribute(obj);
            return;
        } else
        {
            tlist.startAttribute(0);
            return;
        }
    }

    public void startDocument()
    {
        closeStartTag();
        if (stringizingLevel > 0)
        {
            writeJoiner();
            return;
        }
        if (nesting == 0)
        {
            base.startDocument();
        } else
        {
            writeJoiner();
        }
        ensureSpaceInWorkStack(nesting);
        workStack[nesting] = namespaceBindings;
        workStack[1 + nesting] = null;
        nesting = 2 + nesting;
    }

    public void startElement(Object obj)
    {
        int i;
label0:
        {
            startElementCommon();
            if (stringizingLevel == 0)
            {
                ensureSpaceInWorkStack(-1 + nesting);
                workStack[-1 + nesting] = obj;
                if (copyNamespacesMode != 0)
                {
                    break label0;
                }
                namespaceBindings = NamespaceBinding.predefinedXML;
            }
            return;
        }
        if (copyNamespacesMode == 1 || nesting == 2)
        {
            NamespaceBinding namespacebinding;
            if (obj instanceof XName)
            {
                namespacebinding = ((XName)obj).getNamespaceNodes();
            } else
            {
                namespacebinding = NamespaceBinding.predefinedXML;
            }
            namespaceBindings = namespacebinding;
            return;
        }
        i = 2;
_L2:
        NamespaceBinding namespacebinding1;
        if (i == nesting)
        {
            namespacebinding1 = null;
        } else
        {
label1:
            {
                if (workStack[i + 1] == null)
                {
                    break label1;
                }
                namespacebinding1 = (NamespaceBinding)workStack[i];
            }
        }
        if (namespacebinding1 == null)
        {
            NamespaceBinding namespacebinding3;
            if (obj instanceof XName)
            {
                namespacebinding3 = ((XName)obj).getNamespaceNodes();
            } else
            {
                namespacebinding3 = NamespaceBinding.predefinedXML;
            }
            namespaceBindings = namespacebinding3;
            return;
        }
        break; /* Loop/switch isn't completed */
        i += 2;
        if (true) goto _L2; else goto _L1
_L1:
        if (copyNamespacesMode == 2)
        {
            namespaceBindings = namespacebinding1;
            return;
        }
        if (obj instanceof XName)
        {
            NamespaceBinding namespacebinding2 = ((XName)obj).getNamespaceNodes();
            if (NamespaceBinding.commonAncestor(namespacebinding1, namespacebinding2) == namespacebinding1)
            {
                namespaceBindings = namespacebinding2;
                return;
            } else
            {
                namespaceBindings = mergeHelper(namespacebinding1, namespacebinding2);
                return;
            }
        } else
        {
            namespaceBindings = namespacebinding1;
            return;
        }
    }

    public void startElement(String s, String s1, String s2, Attributes attributes)
    {
        startElement(Symbol.make(s, s1));
        int i = attributes.getLength();
        for (int j = 0; j < i; j++)
        {
            startAttribute(Symbol.make(attributes.getURI(j), attributes.getLocalName(j)));
            write(attributes.getValue(j));
            endAttribute();
        }

    }

    public void startElement(String s, AttributeList attributelist)
    {
        startElement(s.intern());
        int i = attributelist.getLength();
        for (int j = 0; j < i; j++)
        {
            String s1 = attributelist.getName(j).intern();
            attributelist.getType(j);
            String s2 = attributelist.getValue(j);
            startAttribute(s1);
            write(s2);
            endAttribute();
        }

    }

    protected void startElementCommon()
    {
        closeStartTag();
        if (stringizingLevel != 0) goto _L2; else goto _L1
_L1:
        ensureSpaceInWorkStack(nesting);
        workStack[nesting] = namespaceBindings;
        tlist.startElement(0);
        base = tlist;
        attrCount = 0;
_L4:
        nesting = 2 + nesting;
        return;
_L2:
        if (previous == 2 && stringizingElementNesting < 0)
        {
            write(32);
        }
        previous = 0;
        if (stringizingElementNesting < 0)
        {
            stringizingElementNesting = nesting;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void startPrefixMapping(String s, String s1)
    {
        namespaceBindings = findNamespaceBinding(s.intern(), s1.intern(), namespaceBindings);
    }

    public void textFromParser(char ac[], int i, int j)
    {
        if (inElement()) goto _L2; else goto _L1
_L1:
        int k = 0;
_L7:
        if (k != j) goto _L4; else goto _L3
_L3:
        return;
_L4:
        if (!Character.isWhitespace(ac[i + k]))
        {
            error('e', "text at document level");
            return;
        }
        k++;
        continue; /* Loop/switch isn't completed */
_L2:
        if (j <= 0 || !checkWriteAtomic()) goto _L3; else goto _L5
_L5:
        base.write(ac, i, j);
        return;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public void write(int i)
    {
        if (checkWriteAtomic())
        {
            base.write(i);
        }
    }

    public void write(CharSequence charsequence, int i, int j)
    {
        if (j == 0)
        {
            writeJoiner();
        } else
        if (checkWriteAtomic())
        {
            base.write(charsequence, i, j);
            return;
        }
    }

    public void write(String s)
    {
        write(((CharSequence) (s)), 0, s.length());
    }

    public void write(char ac[], int i, int j)
    {
        if (j == 0)
        {
            writeJoiner();
        } else
        if (checkWriteAtomic())
        {
            base.write(ac, i, j);
            return;
        }
    }

    public void writeBoolean(boolean flag)
    {
        if (checkWriteAtomic())
        {
            base.writeBoolean(flag);
        }
    }

    public void writeCDATA(char ac[], int i, int j)
    {
label0:
        {
            if (checkWriteAtomic())
            {
                if (!(base instanceof XConsumer))
                {
                    break label0;
                }
                ((XConsumer)base).writeCDATA(ac, i, j);
            }
            return;
        }
        write(ac, i, j);
    }

    public void writeComment(char ac[], int i, int j)
    {
        checkValidComment(ac, i, j);
        commentFromParser(ac, i, j);
    }

    public void writeDocumentUri(Object obj)
    {
        if (nesting == 2 && (base instanceof TreeList))
        {
            ((TreeList)base).writeDocumentUri(obj);
        }
    }

    public void writeDouble(double d)
    {
        if (checkWriteAtomic())
        {
            base.writeDouble(d);
        }
    }

    public void writeFloat(float f)
    {
        if (checkWriteAtomic())
        {
            base.writeFloat(f);
        }
    }

    public void writeInt(int i)
    {
        if (checkWriteAtomic())
        {
            base.writeInt(i);
        }
    }

    protected void writeJoiner()
    {
        previous = 0;
        if (ignoringLevel == 0)
        {
            ((TreeList)base).writeJoiner();
        }
    }

    public void writeLong(long l)
    {
        if (checkWriteAtomic())
        {
            base.writeLong(l);
        }
    }

    public void writeObject(Object obj)
    {
        if (ignoringLevel <= 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (obj instanceof SeqPosition)
        {
            SeqPosition seqposition = (SeqPosition)obj;
            writePosition(seqposition.sequence, seqposition.getPos());
            return;
        }
        if (obj instanceof TreeList)
        {
            ((TreeList)obj).consume(this);
            return;
        }
        if (!(obj instanceof List) || (obj instanceof CharSeq))
        {
            break; /* Loop/switch isn't completed */
        }
        Iterator iterator = ((List)obj).iterator();
        int i = 0;
        while (iterator.hasNext()) 
        {
            writeObject(iterator.next());
            i++;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (obj instanceof Keyword)
        {
            startAttribute(((Keyword)obj).asSymbol());
            previous = 1;
            return;
        }
        closeStartTag();
        if (obj instanceof UnescapedData)
        {
            base.writeObject(obj);
            previous = 0;
            return;
        }
        if (previous == 2)
        {
            write(32);
        }
        TextUtils.textValue(obj, this);
        previous = 2;
        return;
    }

    public void writePosition(AbstractSequence abstractsequence, int i)
    {
        if (ignoringLevel <= 0)
        {
            if (stringizingLevel > 0 && previous == 2)
            {
                if (stringizingElementNesting < 0)
                {
                    write(32);
                }
                previous = 0;
            }
            abstractsequence.consumeNext(i, this);
            if (stringizingLevel > 0 && stringizingElementNesting < 0)
            {
                previous = 2;
                return;
            }
        }
    }

    public void writeProcessingInstruction(String s, char ac[], int i, int j)
    {
        String s1 = TextUtils.replaceWhitespace(s, true);
        int k = i + j;
label0:
        do
        {
            if (--k < i)
            {
                break;
            }
            char c = ac[k];
            do
            {
                if (c != '>' || --k < i)
                {
                    continue label0;
                }
                c = ac[k];
            } while (c != '?');
            error('e', "'?>' is not allowed in a processing-instruction");
        } while (true);
        if ("xml".equalsIgnoreCase(s1))
        {
            error('e', "processing-instruction target may not be 'xml' (ignoring case)");
        }
        if (!XName.isNCName(s1))
        {
            error('e', (new StringBuilder()).append("processing-instruction target '").append(s1).append("' is not a valid Name").toString());
        }
        processingInstructionCommon(s1, ac, i, j);
    }
}
