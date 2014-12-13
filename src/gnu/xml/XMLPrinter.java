// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.xml;

import gnu.expr.Keyword;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.AbstractSequence;
import gnu.lists.Consumable;
import gnu.lists.PositionConsumer;
import gnu.lists.SeqPosition;
import gnu.lists.UnescapedData;
import gnu.lists.XConsumer;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.math.DFloNum;
import gnu.math.RealNum;
import gnu.text.Char;
import gnu.text.Path;
import gnu.text.PrettyWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;

// Referenced classes of package gnu.xml:
//            NamespaceBinding, XName, NodeTree

public class XMLPrinter extends OutPort
    implements PositionConsumer, XConsumer
{

    private static final int COMMENT = -5;
    private static final int ELEMENT_END = -4;
    private static final int ELEMENT_START = -3;
    static final String HtmlEmptyTags = "/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/";
    private static final int KEYWORD = -6;
    private static final int PROC_INST = -7;
    private static final int WORD = -2;
    public static final ThreadLocation doctypePublic = new ThreadLocation("doctype-public");
    public static final ThreadLocation doctypeSystem = new ThreadLocation("doctype-system");
    public static final ThreadLocation indentLoc = new ThreadLocation("xml-indent");
    boolean canonicalize;
    public boolean canonicalizeCDATA;
    Object elementNameStack[];
    int elementNesting;
    public boolean escapeNonAscii;
    public boolean escapeText;
    boolean inAttribute;
    int inComment;
    boolean inDocument;
    boolean inStartTag;
    public boolean indentAttributes;
    boolean isHtml;
    boolean isHtmlOrXhtml;
    NamespaceBinding namespaceBindings;
    NamespaceBinding namespaceSaveStack[];
    boolean needXMLdecl;
    int prev;
    public int printIndent;
    boolean printXMLdecl;
    char savedHighSurrogate;
    public boolean strict;
    Object style;
    boolean undeclareNamespaces;
    public int useEmptyElementTag;

    public XMLPrinter(OutPort outport, boolean flag)
    {
        super(outport, flag);
        printIndent = -1;
        printXMLdecl = false;
        inAttribute = false;
        inStartTag = false;
        needXMLdecl = false;
        canonicalize = true;
        useEmptyElementTag = 2;
        escapeText = true;
        escapeNonAscii = true;
        isHtml = false;
        isHtmlOrXhtml = false;
        undeclareNamespaces = false;
        namespaceBindings = NamespaceBinding.predefinedXML;
        namespaceSaveStack = new NamespaceBinding[20];
        elementNameStack = new Object[20];
        prev = 32;
    }

    public XMLPrinter(OutputStream outputstream)
    {
        super(new OutputStreamWriter(outputstream), false, false);
        printIndent = -1;
        printXMLdecl = false;
        inAttribute = false;
        inStartTag = false;
        needXMLdecl = false;
        canonicalize = true;
        useEmptyElementTag = 2;
        escapeText = true;
        escapeNonAscii = true;
        isHtml = false;
        isHtmlOrXhtml = false;
        undeclareNamespaces = false;
        namespaceBindings = NamespaceBinding.predefinedXML;
        namespaceSaveStack = new NamespaceBinding[20];
        elementNameStack = new Object[20];
        prev = 32;
    }

    public XMLPrinter(OutputStream outputstream, Path path)
    {
        super(new OutputStreamWriter(outputstream), true, false, path);
        printIndent = -1;
        printXMLdecl = false;
        inAttribute = false;
        inStartTag = false;
        needXMLdecl = false;
        canonicalize = true;
        useEmptyElementTag = 2;
        escapeText = true;
        escapeNonAscii = true;
        isHtml = false;
        isHtmlOrXhtml = false;
        undeclareNamespaces = false;
        namespaceBindings = NamespaceBinding.predefinedXML;
        namespaceSaveStack = new NamespaceBinding[20];
        elementNameStack = new Object[20];
        prev = 32;
    }

    public XMLPrinter(OutputStream outputstream, boolean flag)
    {
        super(new OutputStreamWriter(outputstream), true, flag);
        printIndent = -1;
        printXMLdecl = false;
        inAttribute = false;
        inStartTag = false;
        needXMLdecl = false;
        canonicalize = true;
        useEmptyElementTag = 2;
        escapeText = true;
        escapeNonAscii = true;
        isHtml = false;
        isHtmlOrXhtml = false;
        undeclareNamespaces = false;
        namespaceBindings = NamespaceBinding.predefinedXML;
        namespaceSaveStack = new NamespaceBinding[20];
        elementNameStack = new Object[20];
        prev = 32;
    }

    public XMLPrinter(Writer writer)
    {
        super(writer);
        printIndent = -1;
        printXMLdecl = false;
        inAttribute = false;
        inStartTag = false;
        needXMLdecl = false;
        canonicalize = true;
        useEmptyElementTag = 2;
        escapeText = true;
        escapeNonAscii = true;
        isHtml = false;
        isHtmlOrXhtml = false;
        undeclareNamespaces = false;
        namespaceBindings = NamespaceBinding.predefinedXML;
        namespaceSaveStack = new NamespaceBinding[20];
        elementNameStack = new Object[20];
        prev = 32;
    }

    public XMLPrinter(Writer writer, boolean flag)
    {
        super(writer, flag);
        printIndent = -1;
        printXMLdecl = false;
        inAttribute = false;
        inStartTag = false;
        needXMLdecl = false;
        canonicalize = true;
        useEmptyElementTag = 2;
        escapeText = true;
        escapeNonAscii = true;
        isHtml = false;
        isHtmlOrXhtml = false;
        undeclareNamespaces = false;
        namespaceBindings = NamespaceBinding.predefinedXML;
        namespaceSaveStack = new NamespaceBinding[20];
        elementNameStack = new Object[20];
        prev = 32;
    }

    static String formatDecimal(String s)
    {
        int j;
label0:
        {
            if (s.indexOf('.') >= 0)
            {
                int i = s.length();
                j = i;
                char c;
                do
                {
                    j--;
                    c = s.charAt(j);
                } while (c == '0');
                if (c != '.')
                {
                    j++;
                }
                if (j != i)
                {
                    break label0;
                }
            }
            return s;
        }
        return s.substring(0, j);
    }

    public static String formatDecimal(BigDecimal bigdecimal)
    {
        return formatDecimal(bigdecimal.toPlainString());
    }

    public static String formatDouble(double d)
    {
        if (Double.isNaN(d))
        {
            return "NaN";
        }
        boolean flag;
        if (d < 0.0D)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (Double.isInfinite(d))
        {
            if (flag)
            {
                return "-INF";
            } else
            {
                return "INF";
            }
        }
        double d1;
        String s;
        if (flag)
        {
            d1 = -d;
        } else
        {
            d1 = d;
        }
        s = Double.toString(d);
        if ((d1 >= 1000000D || d1 < 9.9999999999999995E-07D) && d1 != 0.0D)
        {
            return RealNum.toStringScientific(s);
        } else
        {
            return formatDecimal(RealNum.toStringDecimal(s));
        }
    }

    public static String formatFloat(float f)
    {
        if (Float.isNaN(f))
        {
            return "NaN";
        }
        boolean flag;
        if (f < 0.0F)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (Float.isInfinite(f))
        {
            if (flag)
            {
                return "-INF";
            } else
            {
                return "INF";
            }
        }
        float f1;
        String s;
        if (flag)
        {
            f1 = -f;
        } else
        {
            f1 = f;
        }
        s = Float.toString(f);
        if ((f1 >= 1000000F || (double)f1 < 9.9999999999999995E-07D) && (double)f1 != 0.0D)
        {
            return RealNum.toStringScientific(s);
        } else
        {
            return formatDecimal(RealNum.toStringDecimal(s));
        }
    }

    public static boolean isHtmlEmptyElementTag(String s)
    {
        int i = "/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/".indexOf(s);
        return i > 0 && "/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/".charAt(i - 1) == '/' && "/area/base/basefont/br/col/frame/hr/img/input/isindex/link/meta/para/".charAt(i + s.length()) == '/';
    }

    public static XMLPrinter make(OutPort outport, Object obj)
    {
        XMLPrinter xmlprinter = new XMLPrinter(outport, true);
        xmlprinter.setStyle(obj);
        return xmlprinter;
    }

    private void startWord()
    {
        closeTag();
        writeWordStart();
    }

    public static String toString(Object obj)
    {
        StringWriter stringwriter = new StringWriter();
        (new XMLPrinter(stringwriter)).writeObject(obj);
        return stringwriter.toString();
    }

    public void beginComment()
    {
        closeTag();
        if (printIndent >= 0 && (prev == -3 || prev == -4 || prev == -5))
        {
            byte byte0;
            if (printIndent > 0)
            {
                byte0 = 82;
            } else
            {
                byte0 = 78;
            }
            writeBreak(byte0);
        }
        bout.write("<!--");
        inComment = 1;
    }

    public void beginEntity(Object obj)
    {
    }

    public void closeTag()
    {
        if (inStartTag && !inAttribute)
        {
            if (printIndent >= 0 && indentAttributes)
            {
                endLogicalBlock("");
            }
            bout.write(62);
            inStartTag = false;
            prev = -3;
        } else
        if (needXMLdecl)
        {
            bout.write("<?xml version=\"1.0\"?>\n");
            if (printIndent >= 0)
            {
                startLogicalBlock("", "", 2);
            }
            needXMLdecl = false;
            prev = 62;
            return;
        }
    }

    public void consume(SeqPosition seqposition)
    {
        seqposition.sequence.consumeNext(seqposition.ipos, this);
    }

    public void endAttribute()
    {
        if (inAttribute)
        {
            if (prev != -6)
            {
                bout.write(34);
                inAttribute = false;
            }
            prev = 32;
        }
    }

    public void endComment()
    {
        bout.write("-->");
        prev = -5;
        inComment = 0;
    }

    public void endDocument()
    {
        inDocument = false;
        if (printIndent >= 0)
        {
            endLogicalBlock("");
        }
        freshLine();
    }

    public void endElement()
    {
        Object obj;
        String s1;
        String s3;
        String s4;
        if (useEmptyElementTag == 0)
        {
            closeTag();
        }
        obj = elementNameStack[-1 + elementNesting];
        String s = getHtmlTag(obj);
        if (!inStartTag)
        {
            break MISSING_BLOCK_LABEL_400;
        }
        if (printIndent >= 0 && indentAttributes)
        {
            endLogicalBlock("");
        }
        NamespaceBinding anamespacebinding[];
        int i;
        boolean flag;
        boolean flag1;
        Symbol symbol;
        String s2;
        if (s != null && isHtmlEmptyElementTag(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (useEmptyElementTag == 0) goto _L2; else goto _L1
_L1:
        s1 = null;
        if (s == null) goto _L4; else goto _L3
_L3:
        s1 = null;
        if (flag) goto _L4; else goto _L2
_L2:
        flag1 = obj instanceof Symbol;
        s1 = null;
        if (!flag1) goto _L4; else goto _L5
_L5:
        symbol = (Symbol)obj;
        s2 = symbol.getPrefix();
        s3 = symbol.getNamespaceURI();
        s4 = symbol.getLocalName();
        if (s2 == "") goto _L7; else goto _L6
_L6:
        s1 = (new StringBuilder()).append("></").append(s2).append(":").append(s4).append(">").toString();
_L4:
        if (s1 == null)
        {
            if (flag && isHtml)
            {
                s1 = ">";
            } else
            if (useEmptyElementTag == 2)
            {
                s1 = " />";
            } else
            {
                s1 = "/>";
            }
        }
        bout.write(s1);
        inStartTag = false;
_L10:
        if (printIndent >= 0)
        {
            endLogicalBlock("");
        }
        prev = -4;
        if (s != null && !escapeText && ("script".equals(s) || "style".equals(s)))
        {
            escapeText = true;
        }
        anamespacebinding = namespaceSaveStack;
        i = -1 + elementNesting;
        elementNesting = i;
        namespaceBindings = anamespacebinding[i];
        namespaceSaveStack[elementNesting] = null;
        elementNameStack[elementNesting] = null;
        return;
_L7:
        if (s3 == "") goto _L9; else goto _L8
_L8:
        s1 = null;
        if (s3 != null) goto _L4; else goto _L9
_L9:
        s1 = (new StringBuilder()).append("></").append(s4).append(">").toString();
          goto _L4
        if (printIndent >= 0)
        {
            setIndentation(0, false);
            if (prev == -4)
            {
                byte byte0;
                if (printIndent > 0)
                {
                    byte0 = 82;
                } else
                {
                    byte0 = 78;
                }
                writeBreak(byte0);
            }
        }
        bout.write("</");
        writeQName(obj);
        bout.write(">");
          goto _L10
    }

    public void endEntity()
    {
    }

    protected void endNumber()
    {
        writeWordEnd();
    }

    public void error(String s, String s1)
    {
        throw new RuntimeException((new StringBuilder()).append("serialization error: ").append(s).append(" [").append(s1).append(']').toString());
    }

    protected String getHtmlTag(Object obj)
    {
        if (obj instanceof Symbol)
        {
            Symbol symbol = (Symbol)obj;
            String s = symbol.getNamespaceURI();
            if (s == "http://www.w3.org/1999/xhtml" || isHtmlOrXhtml && s == "")
            {
                return symbol.getLocalPart();
            }
        } else
        if (isHtmlOrXhtml)
        {
            return obj.toString();
        }
        return null;
    }

    public boolean ignoring()
    {
        return false;
    }

    boolean mustHexEscape(int i)
    {
        return i >= 127 && (i <= 159 || escapeNonAscii) || i == 8232 || i < 32 && (inAttribute || i != 9 && i != 10);
    }

    public void print(Object obj)
    {
        String s;
        if (obj instanceof BigDecimal)
        {
            obj = formatDecimal((BigDecimal)obj);
        } else
        if ((obj instanceof Double) || (obj instanceof DFloNum))
        {
            obj = formatDouble(((Number)obj).doubleValue());
        } else
        if (obj instanceof Float)
        {
            obj = formatFloat(((Float)obj).floatValue());
        }
        if (obj == null)
        {
            s = "(null)";
        } else
        {
            s = obj.toString();
        }
        write(s);
    }

    void setIndentMode()
    {
        Object obj = indentLoc.get(null);
        String s = null;
        if (obj != null)
        {
            s = obj.toString();
        }
        if (s == null)
        {
            printIndent = -1;
            return;
        }
        if (s.equals("pretty"))
        {
            printIndent = 0;
            return;
        }
        if (s.equals("always") || s.equals("yes"))
        {
            printIndent = 1;
            return;
        } else
        {
            printIndent = -1;
            return;
        }
    }

    public void setPrintXMLdecl(boolean flag)
    {
        printXMLdecl = flag;
    }

    public void setStyle(Object obj)
    {
        style = obj;
        int i;
        if (canonicalize)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        useEmptyElementTag = i;
        if (!"html".equals(obj)) goto _L2; else goto _L1
_L1:
        isHtml = true;
        isHtmlOrXhtml = true;
        useEmptyElementTag = 2;
        if (namespaceBindings == NamespaceBinding.predefinedXML)
        {
            namespaceBindings = XmlNamespace.HTML_BINDINGS;
        }
_L4:
        if ("xhtml".equals(obj))
        {
            isHtmlOrXhtml = true;
            useEmptyElementTag = 2;
        }
        if ("plain".equals(obj))
        {
            escapeText = false;
        }
        return;
_L2:
        if (namespaceBindings == XmlNamespace.HTML_BINDINGS)
        {
            namespaceBindings = NamespaceBinding.predefinedXML;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void startAttribute(Object obj)
    {
        if (!inStartTag && strict)
        {
            error("attribute not in element", "SENR0001");
        }
        if (inAttribute)
        {
            bout.write(34);
        }
        inAttribute = true;
        bout.write(32);
        if (printIndent >= 0)
        {
            writeBreakFill();
        }
        bout.write(obj.toString());
        bout.write("=\"");
        prev = 32;
    }

    public void startDocument()
    {
        if (printXMLdecl)
        {
            needXMLdecl = true;
        }
        setIndentMode();
        inDocument = true;
        if (printIndent >= 0 && !needXMLdecl)
        {
            startLogicalBlock("", "", 2);
        }
    }

    public void startElement(Object obj)
    {
        NamespaceBinding namespacebinding;
        NamespaceBinding namespacebinding1;
        NamespaceBinding anamespacebinding2[];
        int k;
        boolean flag;
        NamespaceBinding namespacebinding2;
        int i1;
        String s4;
        NamespaceBinding namespacebinding5;
        String s5;
        closeTag();
        if (elementNesting == 0)
        {
            if (!inDocument)
            {
                setIndentMode();
            }
            if (prev == -7)
            {
                write(10);
            }
            Object obj1 = doctypeSystem.get(null);
            if (obj1 != null)
            {
                String s6 = obj1.toString();
                if (s6.length() > 0)
                {
                    Object obj2 = doctypePublic.get(null);
                    bout.write("<!DOCTYPE ");
                    bout.write(obj.toString());
                    NamespaceBinding anamespacebinding[];
                    int i;
                    String s7;
                    if (obj2 == null)
                    {
                        s7 = null;
                    } else
                    {
                        s7 = obj2.toString();
                    }
                    if (s7 != null && s7.length() > 0)
                    {
                        bout.write(" PUBLIC \"");
                        bout.write(s7);
                        bout.write("\" \"");
                    } else
                    {
                        bout.write(" SYSTEM \"");
                    }
                    bout.write(s6);
                    bout.write("\">");
                    println();
                }
            }
        }
        if (printIndent >= 0)
        {
            if (prev == -3 || prev == -4 || prev == -5)
            {
                byte byte0;
                if (printIndent > 0)
                {
                    byte0 = 82;
                } else
                {
                    byte0 = 78;
                }
                writeBreak(byte0);
            }
            startLogicalBlock("", "", 2);
        }
        bout.write(60);
        writeQName(obj);
        if (printIndent >= 0 && indentAttributes)
        {
            startLogicalBlock("", "", 2);
        }
        elementNameStack[elementNesting] = obj;
        anamespacebinding = namespaceSaveStack;
        i = elementNesting;
        elementNesting = i + 1;
        anamespacebinding[i] = namespaceBindings;
        if (!(obj instanceof XName)) goto _L2; else goto _L1
_L1:
        namespacebinding = ((XName)obj).namespaceNodes;
        namespacebinding1 = NamespaceBinding.commonAncestor(namespacebinding, namespaceBindings);
        int j;
        if (namespacebinding == null)
        {
            j = 0;
        } else
        {
            j = namespacebinding.count(namespacebinding1);
        }
        anamespacebinding2 = new NamespaceBinding[j];
        k = 0;
        flag = canonicalize;
        namespacebinding2 = namespacebinding;
_L13:
        if (namespacebinding2 == namespacebinding1) goto _L4; else goto _L3
_L3:
        i1 = k;
        namespacebinding2.getUri();
        s4 = namespacebinding2.getPrefix();
_L11:
        if (--i1 < 0) goto _L6; else goto _L5
_L5:
        namespacebinding5 = anamespacebinding2[i1];
        s5 = namespacebinding5.getPrefix();
        if (s4 != s5) goto _L8; else goto _L7
_L7:
        namespacebinding2 = namespacebinding2.next;
        continue; /* Loop/switch isn't completed */
_L8:
        if (!flag)
        {
            continue; /* Loop/switch isn't completed */
        }
          goto _L9
_L6:
        int j1;
        if (flag)
        {
            j1 = i1 + 1;
        } else
        {
            j1 = k;
        }
        anamespacebinding2[j1] = namespacebinding2;
        k++;
        if (true) goto _L7; else goto _L9
_L9:
        if (s4 == null || s5 != null && s4.compareTo(s5) <= 0) goto _L6; else goto _L10
_L10:
        anamespacebinding2[i1 + 1] = namespacebinding5;
        if (true) goto _L11; else goto _L4
_L4:
        int l = k;
        do
        {
            if (--l < 0)
            {
                break;
            }
            NamespaceBinding namespacebinding4 = anamespacebinding2[l];
            String s2 = namespacebinding4.prefix;
            String s3 = namespacebinding4.uri;
            if (s3 != namespaceBindings.resolve(s2) && (s3 != null || s2 == null || undeclareNamespaces))
            {
                bout.write(32);
                if (s2 == null)
                {
                    bout.write("xmlns");
                } else
                {
                    bout.write("xmlns:");
                    bout.write(s2);
                }
                bout.write("=\"");
                inAttribute = true;
                if (s3 != null)
                {
                    write(s3);
                }
                inAttribute = false;
                bout.write(34);
            }
        } while (true);
        if (undeclareNamespaces)
        {
            NamespaceBinding namespacebinding3 = namespaceBindings;
            while (namespacebinding3 != namespacebinding1) 
            {
                String s1 = namespacebinding3.prefix;
                if (namespacebinding3.uri != null && namespacebinding.resolve(s1) == null)
                {
                    bout.write(32);
                    if (s1 == null)
                    {
                        bout.write("xmlns");
                    } else
                    {
                        bout.write("xmlns:");
                        bout.write(s1);
                    }
                    bout.write("=\"\"");
                }
                namespacebinding3 = namespacebinding3.next;
            }
        }
        namespaceBindings = namespacebinding;
_L2:
        if (elementNesting >= namespaceSaveStack.length)
        {
            NamespaceBinding anamespacebinding1[] = new NamespaceBinding[2 * elementNesting];
            System.arraycopy(namespaceSaveStack, 0, anamespacebinding1, 0, elementNesting);
            namespaceSaveStack = anamespacebinding1;
            Object aobj[] = new Object[2 * elementNesting];
            System.arraycopy(((Object) (elementNameStack)), 0, ((Object) (aobj)), 0, elementNesting);
            elementNameStack = aobj;
        }
        inStartTag = true;
        String s = getHtmlTag(obj);
        if ("script".equals(s) || "style".equals(s))
        {
            escapeText = false;
        }
        return;
        if (true) goto _L13; else goto _L12
_L12:
    }

    protected void startNumber()
    {
        startWord();
    }

    public void write(int i)
    {
        closeTag();
        if (printIndent >= 0 && (i == 13 || i == 10))
        {
            if (i != 10 || prev != 13)
            {
                writeBreak(82);
            }
            if (inComment > 0)
            {
                inComment = 1;
            }
            return;
        }
        if (!escapeText)
        {
            bout.write(i);
            prev = i;
            return;
        }
        if (inComment > 0)
        {
            if (i == 45)
            {
                if (inComment == 1)
                {
                    inComment = 2;
                } else
                {
                    bout.write(32);
                }
            } else
            {
                inComment = 1;
            }
            super.write(i);
            return;
        }
        prev = 59;
        if (i == 60 && (!isHtml || !inAttribute))
        {
            bout.write("&lt;");
            return;
        }
        if (i == 62)
        {
            bout.write("&gt;");
            return;
        }
        if (i == 38)
        {
            bout.write("&amp;");
            return;
        }
        if (i == 34 && inAttribute)
        {
            bout.write("&quot;");
            return;
        }
        if (mustHexEscape(i))
        {
            int j = i;
            if (i >= 55296)
            {
                if (i < 56320)
                {
                    savedHighSurrogate = (char)i;
                    return;
                }
                if (i < 57344)
                {
                    j = 0x10000 + (1024 * (savedHighSurrogate - 55296) + (j - 56320));
                    savedHighSurrogate = '\0';
                }
            }
            bout.write((new StringBuilder()).append("&#x").append(Integer.toHexString(j).toUpperCase()).append(";").toString());
            return;
        } else
        {
            bout.write(i);
            prev = i;
            return;
        }
    }

    public void write(String s, int i, int j)
    {
        if (j > 0)
        {
            closeTag();
            int k = i + j;
            int l = 0;
            int i1 = i;
            while (i1 < k) 
            {
                int j1 = i1 + 1;
                char c = s.charAt(i1);
                if (mustHexEscape(c) || (inComment <= 0 ? c == '<' || c == '>' || c == '&' || inAttribute && (c == '"' || c < ' ') : c == '-' || inComment == 2))
                {
                    if (l > 0)
                    {
                        bout.write(s, j1 - 1 - l, l);
                    }
                    write(c);
                    l = 0;
                } else
                {
                    l++;
                }
                i1 = j1;
            }
            if (l > 0)
            {
                bout.write(s, k - l, l);
            }
            int _tmp = i1;
        }
        prev = 45;
    }

    public void write(char ac[], int i, int j)
    {
        if (j > 0)
        {
            closeTag();
            int k = i + j;
            int l = 0;
            int i1 = i;
            while (i1 < k) 
            {
                int j1 = i1 + 1;
                char c = ac[i1];
                if (mustHexEscape(c) || (inComment <= 0 ? c == '<' || c == '>' || c == '&' || inAttribute && (c == '"' || c < ' ') : c == '-' || inComment == 2))
                {
                    if (l > 0)
                    {
                        bout.write(ac, j1 - 1 - l, l);
                    }
                    write(c);
                    l = 0;
                } else
                {
                    l++;
                }
                i1 = j1;
            }
            if (l > 0)
            {
                bout.write(ac, k - l, l);
            }
            int _tmp = i1;
        }
        prev = 45;
    }

    public void writeBaseUri(Object obj)
    {
    }

    public void writeBoolean(boolean flag)
    {
        startWord();
        super.print(flag);
        writeWordEnd();
    }

    public void writeCDATA(char ac[], int i, int j)
    {
        if (canonicalizeCDATA)
        {
            write(ac, i, j);
            return;
        }
        closeTag();
        bout.write("<![CDATA[");
        int k = i + j;
        for (int l = i; l < k - 2; l++)
        {
            if (ac[l] != ']' || ac[l + 1] != ']' || ac[l + 2] != '>')
            {
                continue;
            }
            if (l > i)
            {
                bout.write(ac, i, l - i);
            }
            print("]]]><![CDATA[]>");
            i = l + 3;
            j = k - i;
            l += 2;
        }

        bout.write(ac, i, j);
        bout.write("]]>");
        prev = 62;
    }

    public void writeComment(String s)
    {
        beginComment();
        write(s);
        endComment();
    }

    public void writeComment(char ac[], int i, int j)
    {
        beginComment();
        write(ac, i, j);
        endComment();
    }

    public void writeDouble(double d)
    {
        startWord();
        bout.write(formatDouble(d));
    }

    public void writeFloat(float f)
    {
        startWord();
        bout.write(formatFloat(f));
    }

    public void writeObject(Object obj)
    {
        if (obj instanceof SeqPosition)
        {
            bout.clearWordEnd();
            SeqPosition seqposition = (SeqPosition)obj;
            seqposition.sequence.consumeNext(seqposition.ipos, this);
            if (seqposition.sequence instanceof NodeTree)
            {
                prev = 45;
            }
            return;
        }
        if ((obj instanceof Consumable) && !(obj instanceof UnescapedData))
        {
            ((Consumable)obj).consume(this);
            return;
        }
        if (obj instanceof Keyword)
        {
            startAttribute(((Keyword)obj).getName());
            prev = -6;
            return;
        }
        closeTag();
        if (obj instanceof UnescapedData)
        {
            bout.clearWordEnd();
            bout.write(((UnescapedData)obj).getData());
            prev = 45;
            return;
        }
        if (obj instanceof Char)
        {
            Char.print(((Char)obj).intValue(), this);
            return;
        } else
        {
            startWord();
            prev = 32;
            print(obj);
            writeWordEnd();
            prev = -2;
            return;
        }
    }

    public void writePosition(AbstractSequence abstractsequence, int i)
    {
        abstractsequence.consumeNext(i, this);
    }

    public void writeProcessingInstruction(String s, char ac[], int i, int j)
    {
        if ("xml".equals(s))
        {
            needXMLdecl = false;
        }
        closeTag();
        bout.write("<?");
        print(s);
        print(' ');
        bout.write(ac, i, j);
        bout.write("?>");
        prev = -7;
    }

    protected void writeQName(Object obj)
    {
        if (obj instanceof Symbol)
        {
            Symbol symbol = (Symbol)obj;
            String s1 = symbol.getPrefix();
            if (s1 != null && s1.length() > 0)
            {
                bout.write(s1);
                bout.write(58);
            }
            bout.write(symbol.getLocalPart());
            return;
        }
        PrettyWriter prettywriter = bout;
        String s;
        if (obj == null)
        {
            s = "{null name}";
        } else
        {
            s = (String)obj;
        }
        prettywriter.write(s);
    }

}
