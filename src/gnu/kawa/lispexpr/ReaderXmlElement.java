// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.expr.Compilation;
import gnu.expr.PrimProcedure;
import gnu.expr.Special;
import gnu.kawa.xml.CommentConstructor;
import gnu.kawa.xml.MakeAttribute;
import gnu.kawa.xml.MakeCDATA;
import gnu.kawa.xml.MakeProcInst;
import gnu.kawa.xml.MakeText;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Namespace;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import gnu.xml.XName;
import java.io.IOException;

// Referenced classes of package gnu.kawa.lispexpr:
//            ReadTableEntry, LispReader, MakeXmlElement, ReadTable, 
//            ResolveNamespace

public class ReaderXmlElement extends ReadTableEntry
{

    static final String DEFAULT_ELEMENT_NAMESPACE = "[default-element-namespace]";

    public ReaderXmlElement()
    {
    }

    public static void namedEntity(LispReader lispreader, String s)
    {
        String s1 = s.intern();
        byte byte0 = 63;
        if (s1 == "lt")
        {
            byte0 = 60;
        } else
        if (s1 == "gt")
        {
            byte0 = 62;
        } else
        if (s1 == "amp")
        {
            byte0 = 38;
        } else
        if (s1 == "quot")
        {
            byte0 = 34;
        } else
        if (s1 == "apos")
        {
            byte0 = 39;
        } else
        {
            lispreader.error((new StringBuilder()).append("unknown enity reference: '").append(s1).append("'").toString());
        }
        lispreader.tokenBufferAppend(byte0);
    }

    public static Pair quote(Object obj)
    {
        return LList.list2(Namespace.EmptyNamespace.getSymbol("quote"), obj);
    }

    static void readCharRef(LispReader lispreader)
        throws IOException, SyntaxException
    {
        int i = lispreader.read();
        byte byte0;
        int j;
        if (i == 120)
        {
            byte0 = 16;
            i = lispreader.read();
        } else
        {
            byte0 = 10;
        }
        j = 0;
label0:
        do
        {
            int k;
            if (i >= 0)
            {
                k = Character.digit((char)i, byte0);
                break MISSING_BLOCK_LABEL_33;
            }
            do
            {
                if (i != 59)
                {
                    lispreader.unread(i);
                    lispreader.error("invalid character reference");
                    return;
                }
                break label0;
            } while (k < 0 || j >= 0x8000000);
            j = k + j * byte0;
            i = lispreader.read();
        } while (true);
        if (j > 0 && j <= 55295 || j >= 57344 && j <= 65533 || j >= 0x10000 && j <= 0x10ffff)
        {
            lispreader.tokenBufferAppend(j);
            return;
        } else
        {
            lispreader.error((new StringBuilder()).append("invalid character value ").append(j).toString());
            return;
        }
    }

    public static Pair readContent(LispReader lispreader, char c, Pair pair)
        throws IOException, SyntaxException
    {
        boolean flag;
        lispreader.tokenBufferLength = 0;
        flag = false;
_L11:
        Object obj;
        String s;
        int i;
        int j;
        int k;
        obj = null;
        s = null;
        i = 1 + lispreader.getLineNumber();
        j = lispreader.getColumnNumber();
        k = lispreader.read();
        if (k >= 0) goto _L2; else goto _L1
_L1:
        lispreader.error("unexpected end-of-file");
        obj = Special.eof;
_L9:
        if (obj != null && lispreader.tokenBufferLength > 0)
        {
            s = lispreader.tokenBufferString();
            lispreader.tokenBufferLength = 0;
        }
        if (s != null)
        {
            PairWithPosition pairwithposition1 = PairWithPosition.make(Pair.list2(MakeText.makeText, s), lispreader.makeNil(), null, -1, -1);
            pair.setCdrBackdoor(pairwithposition1);
            pair = pairwithposition1;
        }
        if (obj == Special.eof)
        {
            return pair;
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (k == c)
        {
            if (c == '<')
            {
                int j1 = lispreader.tokenBufferLength;
                s = null;
                if (j1 > 0)
                {
                    s = lispreader.tokenBufferString();
                    lispreader.tokenBufferLength = 0;
                }
                int k1 = lispreader.read();
                if (k1 == 47)
                {
                    obj = Special.eof;
                } else
                {
                    obj = readXMLConstructor(lispreader, k1, true);
                }
            } else
            if (lispreader.checkNext(c))
            {
                lispreader.tokenBufferAppend(c);
                obj = null;
                s = null;
            } else
            {
                obj = Special.eof;
                s = null;
            }
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (k != 38) goto _L4; else goto _L3
_L3:
        int l = lispreader.read();
        if (l != 35) goto _L6; else goto _L5
_L5:
        readCharRef(lispreader);
_L7:
        flag = true;
        continue; /* Loop/switch isn't completed */
_L6:
label0:
        {
label1:
            {
                if (l != 40 && l != 123)
                {
                    break label0;
                }
                if (lispreader.tokenBufferLength <= 0)
                {
                    s = null;
                    if (!flag)
                    {
                        break label1;
                    }
                }
                s = lispreader.tokenBufferString();
            }
            lispreader.tokenBufferLength = 0;
            obj = readEscapedExpression(lispreader, l);
            continue; /* Loop/switch isn't completed */
        }
        obj = readEntity(lispreader, l);
        s = null;
        if (flag)
        {
            int i1 = lispreader.tokenBufferLength;
            s = null;
            if (i1 == 0)
            {
                s = "";
            }
        }
        if (true) goto _L7; else goto _L4
_L4:
        if (c != '<' && (k == 9 || k == 10 || k == 13))
        {
            k = 32;
        }
        if (k == 60)
        {
            lispreader.error('e', "'<' must be quoted in a direct attribute value");
        }
        lispreader.tokenBufferAppend((char)k);
        obj = null;
        s = null;
        if (true) goto _L9; else goto _L8
_L8:
        if (obj != null)
        {
            PairWithPosition pairwithposition = PairWithPosition.make(obj, lispreader.makeNil(), null, i, j);
            pair.setCdrBackdoor(pairwithposition);
            pair = pairwithposition;
        }
        if (true) goto _L11; else goto _L10
_L10:
    }

    public static Object readElementConstructor(LispReader lispreader, int i)
        throws IOException, SyntaxException
    {
label0:
        {
            int j = 1 + lispreader.getLineNumber();
            int k = -2 + lispreader.getColumnNumber();
            Object obj = readQNameExpression(lispreader, i, true);
            String s;
            PairWithPosition pairwithposition;
            PairWithPosition pairwithposition1;
            Object obj1;
            if (lispreader.tokenBufferLength == 0)
            {
                s = null;
            } else
            {
                s = lispreader.tokenBufferString();
            }
            pairwithposition = PairWithPosition.make(obj, LList.Empty, lispreader.getName(), j, k);
            pairwithposition1 = pairwithposition;
            obj1 = LList.Empty;
            do
            {
                boolean flag = false;
                int l;
                for (l = lispreader.readUnicodeChar(); l >= 0 && Character.isWhitespace(l);)
                {
                    l = lispreader.read();
                    flag = true;
                }

                if (l < 0 || l == 62 || l == 47)
                {
                    int i1 = l;
                    boolean flag1 = false;
                    if (i1 == 47)
                    {
                        l = lispreader.read();
                        Object obj2;
                        int i2;
                        String s4;
                        int j2;
                        PairWithPosition pairwithposition2;
                        PairWithPosition pairwithposition3;
                        PairWithPosition pairwithposition4;
                        PairWithPosition pairwithposition5;
                        char c;
                        char c1;
                        char c2;
                        char c3;
                        char c4;
                        char c5;
                        if (l == 62)
                        {
                            flag1 = true;
                        } else
                        {
                            lispreader.unread(l);
                            flag1 = false;
                        }
                    }
                    if (flag1)
                    {
                        break label0;
                    }
                    if (l != 62)
                    {
                        lispreader.error("missing '>' after start element");
                        return Boolean.FALSE;
                    }
                    break;
                }
                if (!flag)
                {
                    lispreader.error("missing space before attribute");
                }
                obj2 = readQNameExpression(lispreader, l, false);
                int _tmp = 1 + lispreader.getLineNumber();
                int _tmp1 = (1 + lispreader.getColumnNumber()) - lispreader.tokenBufferLength;
                i2 = lispreader.tokenBufferLength;
                s4 = null;
                if (i2 >= 5)
                {
                    c = lispreader.tokenBuffer[0];
                    s4 = null;
                    if (c == 'x')
                    {
                        c1 = lispreader.tokenBuffer[1];
                        s4 = null;
                        if (c1 == 'm')
                        {
                            c2 = lispreader.tokenBuffer[2];
                            s4 = null;
                            if (c2 == 'l')
                            {
                                c3 = lispreader.tokenBuffer[3];
                                s4 = null;
                                if (c3 == 'n')
                                {
                                    c4 = lispreader.tokenBuffer[4];
                                    s4 = null;
                                    if (c4 == 's')
                                    {
                                        if (lispreader.tokenBufferLength == 5)
                                        {
                                            s4 = "";
                                        } else
                                        {
                                            c5 = lispreader.tokenBuffer[5];
                                            s4 = null;
                                            if (c5 == ':')
                                            {
                                                s4 = new String(lispreader.tokenBuffer, 6, -6 + lispreader.tokenBufferLength);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (skipSpace(lispreader, 32) != 61)
                {
                    lispreader.error("missing '=' after attribute");
                }
                j2 = skipSpace(lispreader, 32);
                pairwithposition2 = PairWithPosition.make(MakeAttribute.makeAttribute, LList.Empty, lispreader.getName(), j, k);
                pairwithposition3 = PairWithPosition.make(obj2, LList.Empty, lispreader.getName(), j, k);
                lispreader.setCdr(pairwithposition2, pairwithposition3);
                readContent(lispreader, (char)j2, pairwithposition3);
                if (s4 != null)
                {
                    pairwithposition4 = new PairWithPosition(pairwithposition3, Pair.make(s4, pairwithposition3.getCdr()), obj1);
                    obj1 = pairwithposition4;
                } else
                {
                    pairwithposition5 = PairWithPosition.make(pairwithposition2, lispreader.makeNil(), null, -1, -1);
                    pairwithposition1.setCdrBackdoor(pairwithposition5);
                    pairwithposition1 = pairwithposition5;
                }
            } while (true);
            readContent(lispreader, '<', pairwithposition1);
            int j1 = lispreader.readUnicodeChar();
            if (XName.isNameStart(j1))
            {
                lispreader.tokenBufferLength = 0;
                do
                {
                    lispreader.tokenBufferAppend(j1);
                    j1 = lispreader.readUnicodeChar();
                } while (XName.isNamePart(j1) || j1 == 58);
                String s1 = lispreader.tokenBufferString();
                if (s == null || !s1.equals(s))
                {
                    String s2 = lispreader.getName();
                    int k1 = 1 + lispreader.getLineNumber();
                    int l1 = lispreader.getColumnNumber() - lispreader.tokenBufferLength;
                    LList llist;
                    String s3;
                    if (s == null)
                    {
                        s3 = (new StringBuilder()).append("computed start tag closed by '</").append(s1).append(">'").toString();
                    } else
                    {
                        s3 = (new StringBuilder()).append("'<").append(s).append(">' closed by '</").append(s1).append(">'").toString();
                    }
                    lispreader.error('e', s2, k1, l1, s3);
                }
                lispreader.tokenBufferLength = 0;
            }
            if (skipSpace(lispreader, j1) != 62)
            {
                lispreader.error("missing '>' after end element");
            }
        }
        llist = LList.reverseInPlace(obj1);
        return PairWithPosition.make(MakeXmlElement.makeXml, Pair.make(llist, pairwithposition), lispreader.getName(), j, k);
    }

    static Object readEntity(LispReader lispreader, int i)
        throws IOException, SyntaxException
    {
        int j = lispreader.tokenBufferLength;
        do
        {
label0:
            {
                char c;
                if (i >= 0)
                {
                    c = (char)i;
                    if (XName.isNamePart(c))
                    {
                        break label0;
                    }
                }
                if (i != 59)
                {
                    lispreader.unread(i);
                    lispreader.error("invalid entity reference");
                    return "?";
                } else
                {
                    String s = new String(lispreader.tokenBuffer, j, lispreader.tokenBufferLength - j);
                    lispreader.tokenBufferLength = j;
                    namedEntity(lispreader, s);
                    return null;
                }
            }
            lispreader.tokenBufferAppend(c);
            i = lispreader.read();
        } while (true);
    }

    static Object readEscapedExpression(LispReader lispreader, int i)
        throws IOException, SyntaxException
    {
        LineBufferedReader linebufferedreader;
        char c;
        int j;
        int k;
        if (i == 40)
        {
            lispreader.unread(i);
            return lispreader.readObject();
        }
        linebufferedreader = lispreader.getPort();
        c = lispreader.pushNesting('{');
        j = linebufferedreader.getLineNumber();
        k = linebufferedreader.getColumnNumber();
        Pair pair = lispreader.makePair(new PrimProcedure(Compilation.typeValues.getDeclaredMethod("values", 1)), j, k);
        Pair pair1 = pair;
        ReadTable readtable = ReadTable.getCurrent();
_L2:
        int l;
        int i1;
        int j1;
        l = linebufferedreader.getLineNumber();
        i1 = linebufferedreader.getColumnNumber();
        j1 = linebufferedreader.read();
        if (j1 != 125)
        {
            break MISSING_BLOCK_LABEL_131;
        }
        Object obj;
        lispreader.tokenBufferLength = 0;
        if (pair1 != pair.getCdr())
        {
            break MISSING_BLOCK_LABEL_211;
        }
        obj = pair1.getCar();
        lispreader.popNesting(c);
        return obj;
        if (j1 >= 0)
        {
            break MISSING_BLOCK_LABEL_149;
        }
        lispreader.eofError("unexpected EOF in list starting here", j + 1, k);
        Object obj1 = lispreader.readValues(j1, readtable.lookup(j1), readtable);
        if (obj1 == Values.empty) goto _L2; else goto _L1
_L1:
        Pair pair2;
        pair2 = lispreader.makePair(lispreader.handlePostfix(obj1, readtable, l, i1), l, i1);
        lispreader.setCdr(pair1, pair2);
        pair1 = pair2;
          goto _L2
        lispreader.popNesting(c);
        return pair;
        Exception exception;
        exception;
        lispreader.popNesting(c);
        throw exception;
    }

    public static Object readQNameExpression(LispReader lispreader, int i, boolean flag)
        throws IOException, SyntaxException
    {
        lispreader.getName();
        int j = 1 + lispreader.getLineNumber();
        int k = lispreader.getColumnNumber();
        lispreader.tokenBufferLength = 0;
        if (XName.isNameStart(i))
        {
            int l = -1;
            do
            {
                do
                {
                    lispreader.tokenBufferAppend(i);
                    i = lispreader.readUnicodeChar();
                    if (i != 58 || l >= 0)
                    {
                        break;
                    }
                    l = lispreader.tokenBufferLength;
                } while (true);
            } while (XName.isNamePart(i));
            lispreader.unread(i);
            if (l >= 0 || flag)
            {
                int i1 = -1 + (lispreader.tokenBufferLength - l);
                String s = (new String(lispreader.tokenBuffer, l + 1, i1)).intern();
                String s1;
                gnu.mapping.Symbol symbol;
                if (l < 0)
                {
                    s1 = "[default-element-namespace]";
                } else
                {
                    s1 = (new String(lispreader.tokenBuffer, 0, l)).intern();
                }
                symbol = Namespace.EmptyNamespace.getSymbol(s1);
                return new Pair(ResolveNamespace.resolveQName, PairWithPosition.make(symbol, new Pair(s, LList.Empty), lispreader.getName(), j, k));
            } else
            {
                return quote(Namespace.getDefaultSymbol(lispreader.tokenBufferString().intern()));
            }
        }
        if (i == 123 || i == 40)
        {
            return readEscapedExpression(lispreader, i);
        } else
        {
            lispreader.error("missing element name");
            return null;
        }
    }

    static Object readXMLConstructor(LispReader lispreader, int i, boolean flag)
        throws IOException, SyntaxException
    {
        int j;
        int k;
        String s;
        int i1;
        char c;
        j = 1 + lispreader.getLineNumber();
        k = -2 + lispreader.getColumnNumber();
        if (i == 33)
        {
            int j1 = lispreader.read();
            if (j1 == 45)
            {
                j1 = lispreader.peek();
                if (j1 == 45)
                {
                    lispreader.skip();
                    if (!lispreader.readDelimited("-->"))
                    {
                        lispreader.error('f', lispreader.getName(), j, k, "unexpected end-of-file in XML comment starting here - expected \"-->\"");
                    }
                    String s3 = lispreader.tokenBufferString();
                    return LList.list2(CommentConstructor.commentConstructor, s3);
                }
            }
            if (j1 == 91)
            {
                j1 = lispreader.read();
                if (j1 == 67)
                {
                    j1 = lispreader.read();
                    if (j1 == 68)
                    {
                        j1 = lispreader.read();
                        if (j1 == 65)
                        {
                            j1 = lispreader.read();
                            if (j1 == 84)
                            {
                                j1 = lispreader.read();
                                if (j1 == 65)
                                {
                                    j1 = lispreader.read();
                                    if (j1 == 91)
                                    {
                                        if (!lispreader.readDelimited("]]>"))
                                        {
                                            lispreader.error('f', lispreader.getName(), j, k, "unexpected end-of-file in CDATA starting here - expected \"]]>\"");
                                        }
                                        String s2 = lispreader.tokenBufferString();
                                        return LList.list2(MakeCDATA.makeCDATA, s2);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            lispreader.error('f', lispreader.getName(), j, k, "'<!' must be followed by '--' or '[CDATA['");
            for (; j1 >= 0 && j1 != 62 && j1 != 10 && j1 != 13; j1 = lispreader.read()) { }
            return null;
        }
        if (i != 63)
        {
            break MISSING_BLOCK_LABEL_451;
        }
        int l = lispreader.readUnicodeChar();
        if (l < 0 || !XName.isNameStart(l))
        {
            lispreader.error("missing target after '<?'");
        }
        do
        {
            lispreader.tokenBufferAppend(l);
            l = lispreader.readUnicodeChar();
        } while (XName.isNamePart(l));
        s = lispreader.tokenBufferString();
        i1 = 0;
        for (; l >= 0 && Character.isWhitespace(l); l = lispreader.read())
        {
            i1++;
        }

        lispreader.unread(l);
        c = lispreader.pushNesting('?');
        if (!lispreader.readDelimited("?>"))
        {
            lispreader.error('f', lispreader.getName(), j, k, "unexpected end-of-file looking for \"?>\"");
        }
        lispreader.popNesting(c);
        if (i1 == 0 && lispreader.tokenBufferLength > 0)
        {
            lispreader.error("target must be followed by space or '?>'");
        }
        String s1 = lispreader.tokenBufferString();
        return LList.list3(MakeProcInst.makeProcInst, s, s1);
        Exception exception;
        exception;
        lispreader.popNesting(c);
        throw exception;
        return readElementConstructor(lispreader, i);
    }

    static int skipSpace(LispReader lispreader, int i)
        throws IOException, SyntaxException
    {
        do
        {
            if (i < 0 || !Character.isWhitespace(i))
            {
                return i;
            }
            i = lispreader.readUnicodeChar();
        } while (true);
    }

    public Object read(Lexer lexer, int i, int j)
        throws IOException, SyntaxException
    {
        LispReader lispreader = (LispReader)lexer;
        return readXMLConstructor(lispreader, lispreader.readUnicodeChar(), false);
    }
}
