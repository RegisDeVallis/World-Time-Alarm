// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.xml;

import gnu.lists.Consumer;
import gnu.text.LineBufferedReader;
import gnu.text.LineInputStreamReader;
import gnu.text.Path;
import gnu.text.SourceMessages;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package gnu.xml:
//            XMLFilter

public class XMLParser
{

    private static final int ATTRIBUTE_SEEN_EQ_STATE = 11;
    private static final int ATTRIBUTE_SEEN_NAME_STATE = 8;
    static final String BAD_ENCODING_SYNTAX = "bad 'encoding' declaration";
    static final String BAD_STANDALONE_SYNTAX = "bad 'standalone' declaration";
    private static final int BEGIN_ELEMENT_STATE = 2;
    private static final int DOCTYPE_NAME_SEEN_STATE = 16;
    private static final int DOCTYPE_SEEN_STATE = 13;
    private static final int END_ELEMENT_STATE = 4;
    private static final int EXPECT_NAME_MODIFIER = 1;
    private static final int EXPECT_RIGHT_STATE = 27;
    private static final int INIT_LEFT_QUEST_STATE = 30;
    private static final int INIT_LEFT_STATE = 34;
    private static final int INIT_STATE = 0;
    private static final int INIT_TEXT_STATE = 31;
    private static final int INVALID_VERSION_DECL = 35;
    private static final int MAYBE_ATTRIBUTE_STATE = 10;
    private static final int PREV_WAS_CR_STATE = 28;
    private static final int SAW_AMP_SHARP_STATE = 26;
    private static final int SAW_AMP_STATE = 25;
    private static final int SAW_ENTITY_REF = 6;
    private static final int SAW_EOF_ERROR = 37;
    private static final int SAW_ERROR = 36;
    private static final int SAW_LEFT_EXCL_MINUS_STATE = 22;
    private static final int SAW_LEFT_EXCL_STATE = 20;
    private static final int SAW_LEFT_QUEST_STATE = 21;
    private static final int SAW_LEFT_SLASH_STATE = 19;
    private static final int SAW_LEFT_STATE = 14;
    private static final int SKIP_SPACES_MODIFIER = 2;
    private static final int TEXT_STATE = 1;

    public XMLParser()
    {
    }

    public static LineInputStreamReader XMLStreamReader(InputStream inputstream)
        throws IOException
    {
        int i;
        LineInputStreamReader lineinputstreamreader;
        int j;
        int k;
        int l;
        i = -1;
        lineinputstreamreader = new LineInputStreamReader(inputstream);
        j = lineinputstreamreader.getByte();
        if (j < 0)
        {
            k = i;
        } else
        {
            k = lineinputstreamreader.getByte();
        }
        if (k < 0)
        {
            l = i;
        } else
        {
            l = lineinputstreamreader.getByte();
        }
        if (j != 239 || k != 187 || l != 191) goto _L2; else goto _L1
_L1:
        lineinputstreamreader.resetStart(3);
        lineinputstreamreader.setCharset("UTF-8");
_L11:
        lineinputstreamreader.setKeepFullLines(false);
        return lineinputstreamreader;
_L2:
        char ac[];
        int i1;
        int j1;
        if (j == 255 && k == 254 && l != 0)
        {
            lineinputstreamreader.resetStart(2);
            lineinputstreamreader.setCharset("UTF-16LE");
            continue; /* Loop/switch isn't completed */
        }
        if (j == 254 && k == 255 && l != 0)
        {
            lineinputstreamreader.resetStart(2);
            lineinputstreamreader.setCharset("UTF-16BE");
            continue; /* Loop/switch isn't completed */
        }
        if (l >= 0)
        {
            i = lineinputstreamreader.getByte();
        }
        if (j == 76 && k == 111 && l == 167 && i == 148)
        {
            throw new RuntimeException("XMLParser: EBCDIC encodings not supported");
        }
        lineinputstreamreader.resetStart(0);
        if ((j != 60 || (k != 63 || l != 120 || i != 109) && (k != 0 || l != 63 || i != 0)) && (j != 0 || k != 60 || l != 0 || i != 63))
        {
            break MISSING_BLOCK_LABEL_413;
        }
        ac = lineinputstreamreader.buffer;
        if (ac == null)
        {
            ac = new char[8192];
            lineinputstreamreader.buffer = ac;
        }
        i1 = 0;
        j1 = 0;
_L7:
        int k1;
        do
        {
            k1 = lineinputstreamreader.getByte();
        } while (k1 == 0);
        if (k1 >= 0) goto _L4; else goto _L3
_L3:
        lineinputstreamreader.pos = 0;
        lineinputstreamreader.limit = i1;
        continue; /* Loop/switch isn't completed */
_L4:
        int l1;
        l1 = i1 + 1;
        ac[i1] = (char)(k1 & 0xff);
        if (j1 != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        if (k1 != 62)
        {
            break; /* Loop/switch isn't completed */
        }
        i1 = l1;
        if (true) goto _L3; else goto _L5
_L5:
        if (k1 == 39 || k1 == 34)
        {
            j1 = k1;
        }
_L9:
        i1 = l1;
        if (true) goto _L7; else goto _L6
_L6:
        if (k1 != j1) goto _L9; else goto _L8
_L8:
        j1 = 0;
          goto _L9
        lineinputstreamreader.setCharset("UTF-8");
        if (true) goto _L11; else goto _L10
_L10:
    }

    public static void parse(LineBufferedReader linebufferedreader, SourceMessages sourcemessages, Consumer consumer)
        throws IOException
    {
        XMLFilter xmlfilter = new XMLFilter(consumer);
        xmlfilter.setMessages(sourcemessages);
        xmlfilter.setSourceLocator(linebufferedreader);
        xmlfilter.startDocument();
        Path path = linebufferedreader.getPath();
        if (path != null)
        {
            xmlfilter.writeDocumentUri(path);
        }
        parse(linebufferedreader, xmlfilter);
        xmlfilter.endDocument();
    }

    public static void parse(LineBufferedReader linebufferedreader, SourceMessages sourcemessages, XMLFilter xmlfilter)
        throws IOException
    {
        xmlfilter.setMessages(sourcemessages);
        xmlfilter.setSourceLocator(linebufferedreader);
        xmlfilter.startDocument();
        Path path = linebufferedreader.getPath();
        if (path != null)
        {
            xmlfilter.writeDocumentUri(path);
        }
        parse(linebufferedreader, xmlfilter);
        xmlfilter.endDocument();
        linebufferedreader.close();
    }

    public static void parse(LineBufferedReader linebufferedreader, XMLFilter xmlfilter)
    {
        char ac[];
        int i;
        int j;
        int k;
        char c;
        byte byte0;
        char c1;
        int l;
        int i1;
        String s;
        int j1;
        ac = linebufferedreader.buffer;
        i = linebufferedreader.pos;
        j = linebufferedreader.limit;
        k = 0;
        c = '<';
        byte0 = 14;
        c1 = ' ';
        l = 0;
        i1 = -1;
        s = null;
        j1 = j;
_L111:
        k;
        JVM INSTR tableswitch 0 37: default 212
    //                   0 236
    //                   1 368
    //                   2 1479
    //                   3 856
    //                   4 3602
    //                   5 856
    //                   6 1369
    //                   7 856
    //                   8 3396
    //                   9 856
    //                   10 3318
    //                   11 3499
    //                   12 788
    //                   13 3121
    //                   14 1411
    //                   15 788
    //                   16 3133
    //                   17 856
    //                   18 212
    //                   19 3591
    //                   20 3819
    //                   21 1507
    //                   22 212
    //                   23 788
    //                   24 856
    //                   25 1331
    //                   26 3825
    //                   27 3627
    //                   28 721
    //                   29 788
    //                   30 1507
    //                   31 246
    //                   32 788
    //                   33 856
    //                   34 269
    //                   35 296
    //                   36 303
    //                   37 354;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L5 _L7 _L5 _L8 _L5 _L9 _L10 _L11 _L12 _L13 _L11 _L14 _L5 _L1 _L15 _L16 _L17 _L1 _L11 _L5 _L18 _L19 _L20 _L21 _L11 _L17 _L22 _L11 _L5 _L23 _L24 _L25 _L26
_L1:
        int k1 = i;
_L28:
        int k2;
        int l2;
        int i3;
        int j3;
        int k3;
        int l3;
        int i4;
        int j4;
        int k4;
        char c2;
        int l4;
        char c3;
        int i5;
        char c4;
        int j5;
        String s1;
        int k5;
        boolean flag;
        boolean flag1;
        int l5;
        int i6;
        int j6;
        int k6;
        int l6;
        int i7;
        int j7;
        if (k1 < j)
        {
            i = k1 + 1;
            c1 = ac[k1];
            continue; /* Loop/switch isn't completed */
        }
        l1 = k1 - j1;
          goto _L27
_L2:
        k = 31;
        k1 = i;
          goto _L28
_L22:
        if (c1 == '<')
        {
            k = 34;
            k1 = i;
        } else
        {
            k = 1;
            continue; /* Loop/switch isn't completed */
        }
          goto _L28
_L23:
        if (c1 == '?')
        {
            j1 = i;
            k = 33;
            k1 = i;
        } else
        {
            k = 14;
            continue; /* Loop/switch isn't completed */
        }
          goto _L28
_L24:
        i = i1;
        s = "invalid xml version specifier";
_L25:
        linebufferedreader.pos = i;
        xmlfilter.error('e', s);
        do
        {
            j7 = i;
            if (j7 >= j)
            {
                return;
            }
            i = j7 + 1;
            c1 = ac[j7];
        } while (c1 != '>');
        k = 1;
        k1 = i;
          goto _L28
_L26:
        linebufferedreader.pos = i;
        xmlfilter.error('f', "unexpected end-of-file");
        return;
_L3:
        j1 = i - 1;
        l5 = i;
        k1 = i;
_L45:
        if (c1 != c) goto _L30; else goto _L29
_L29:
        k = byte0;
        k6 = k1;
_L33:
        l = k6 - l5;
        if (l > 0)
        {
            linebufferedreader.pos = k6;
            xmlfilter.textFromParser(ac, j1, l);
        }
        j1 = ac.length;
        k1 = k6;
          goto _L28
_L30:
        if (c1 != '&') goto _L32; else goto _L31
_L31:
        k = 25;
        k6 = k1;
          goto _L33
_L32:
        if (c1 != '\r') goto _L35; else goto _L34
_L34:
        l = k1 - l5;
        linebufferedreader.pos = k1;
        if (l > 0)
        {
            xmlfilter.textFromParser(ac, j1, l);
        }
        if (k1 >= j) goto _L37; else goto _L36
_L36:
        c1 = ac[k1];
        if (c1 != '\n') goto _L39; else goto _L38
_L38:
        j1 = k1;
        i7 = k1 + 1;
        l5 = i7;
_L44:
        linebufferedreader.incrLineNumber(1, i7);
        k1 = i7;
_L46:
        if (k1 != j) goto _L41; else goto _L40
_L40:
        l5--;
        k6 = k1;
          goto _L33
_L39:
        xmlfilter.linefeedFromParser();
        if (c1 != '\205') goto _L43; else goto _L42
_L42:
        i7 = k1 + 1;
        j1 = k1;
        l5 = i7 + 1;
          goto _L44
_L43:
        linebufferedreader.incrLineNumber(1, k1);
        j1 = k1;
        l6 = k1 + 1;
        l5 = l6;
        k1 = l6;
          goto _L45
_L37:
        xmlfilter.linefeedFromParser();
        k = 28;
          goto _L28
_L35:
        if (c1 == '\205' || c1 == '\u2028')
        {
            i6 = k1 - l5;
            linebufferedreader.pos = k1 - 1;
            if (i6 > 0)
            {
                xmlfilter.textFromParser(ac, j1, i6);
            }
            xmlfilter.linefeedFromParser();
            linebufferedreader.incrLineNumber(1, k1);
            l5 = k1 + 1;
            j1 = k1;
        } else
        if (c1 == '\n')
        {
            linebufferedreader.incrLineNumber(1, k1);
        }
          goto _L46
_L41:
        j6 = k1 + 1;
        c1 = ac[k1];
        k1 = j6;
          goto _L45
_L21:
        k = 1;
        if (c1 == '\n')
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (c1 == '\205')
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 | flag)
        {
            linebufferedreader.incrLineNumber(1, i);
            k1 = i;
        } else
        {
            linebufferedreader.incrLineNumber(1, i - 1);
            continue; /* Loop/switch isn't completed */
        }
          goto _L28
_L11:
        if (c1 == ' ') goto _L1; else goto _L47
_L47:
        if (c1 == '\t')
        {
            k1 = i;
        } else
        if (c1 == '\n' || c1 == '\r' || c1 == '\205' || c1 == '\u2028')
        {
            linebufferedreader.incrLineNumber(1, i);
            k1 = i;
        } else
        {
            k -= 2;
            continue; /* Loop/switch isn't completed */
        }
          goto _L28
_L5:
        l = j1 + 1;
        k1 = i;
_L51:
        if ((c1 < 'a' || c1 > 'z') && (c1 < 'A' || c1 > 'Z') && c1 != '_' && c1 != ':' && (c1 < '\300' || c1 > '\u02FF' && (c1 < '\u0370' || (c1 > '\u1FFF' || c1 == '\u037E') && (c1 < '\u200C' || c1 > '\u200D' && (c1 < '\u2070' || c1 > '\u218F') && (c1 < '\u2C00' || c1 > '\u2FEF') && (c1 < '\u3001' || c1 > '\uD7FF') && (c1 < '\uF900' || c1 > '\uFFFD')))) && (k1 <= l || c1 < '0' || c1 > '9') && c1 != '.' && c1 != '-' && c1 != '\267' && (c1 <= '\u0300' || c1 > '\u036F' && (c1 < '\u203F' || c1 > '\u2040'))) goto _L49; else goto _L48
_L48:
        if (k1 >= j) goto _L28; else goto _L50
_L50:
        k5 = k1 + 1;
        c1 = ac[k1];
        k1 = k5;
          goto _L51
_L49:
        k--;
        l = k1 - l;
        int l1;
        IOException ioexception;
        int i2;
        int j2;
        if (l == 0)
        {
            if (k == 8)
            {
                s = "missing or invalid attribute name";
            } else
            if (k == 2 || k == 4)
            {
                s = "missing or invalid element name";
            } else
            {
                s = "missing or invalid name";
            }
            k = 36;
            i = k1;
        } else
        {
            i = k1;
        }
        continue; /* Loop/switch isn't completed */
_L56:
        if (c1 != 'x' || i1 != 0) goto _L53; else goto _L52
_L52:
        i1 = 16;
_L60:
        if (k1 >= j) goto _L28; else goto _L54
_L54:
        i3 = k1 + 1;
        c1 = ac[k1];
        k1 = i3;
_L109:
        if (c1 != ';') goto _L56; else goto _L55
_L55:
        linebufferedreader.pos = k1;
        xmlfilter.emitCharacterReference(l, ac, j1, k1 - 1 - j1);
        k = 1;
          goto _L28
_L53:
        if (l < 0x8000000) goto _L58; else goto _L57
_L57:
        linebufferedreader.pos = k1;
        xmlfilter.error('e', "invalid character reference");
        k = 1;
          goto _L28
_L58:
        if (i1 == 0)
        {
            k2 = 10;
        } else
        {
            k2 = i1;
        }
        l2 = Character.digit(c1, k2);
        if (l2 < 0) goto _L57; else goto _L59
_L59:
        l = l2 + l * k2;
          goto _L60
_L18:
        if (c1 == '#')
        {
            k = 26;
            j1 = i;
            k1 = i;
            l = 0;
            i1 = 0;
        } else
        {
            j1 = i - 1;
            k = 7;
            continue; /* Loop/switch isn't completed */
        }
          goto _L28
_L7:
        linebufferedreader.pos = i;
        if (c1 != ';')
        {
            xmlfilter.error('w', "missing ';'");
        }
        xmlfilter.emitEntityReference(ac, j1, l);
        j1 = j;
        k = 1;
        k1 = i;
          goto _L28
_L13:
        if (c1 == '/')
        {
            k = 19;
            k1 = i;
        } else
        if (c1 == '?')
        {
            j1 = i;
            k = 24;
            k1 = i;
        } else
        if (c1 == '!')
        {
            k = 20;
            j1 = i;
            k1 = i;
        } else
        {
            j1 = i - 1;
            k = 3;
            continue; /* Loop/switch isn't completed */
        }
          goto _L28
_L4:
        linebufferedreader.pos = i - l;
        xmlfilter.emitStartElement(ac, j1, l);
        k = 12;
        j1 = j;
        continue; /* Loop/switch isn't completed */
_L17:
        if (i1 < 0)
        {
            i1 = i - 1;
            k1 = i;
        } else
        {
            k1 = i;
        }
_L83:
        if (c1 != '>') goto _L62; else goto _L61
_L61:
        k4 = k1 - 2;
        if (ac[k4] != '?' || k4 < i1) goto _L62; else goto _L63
_L63:
        linebufferedreader.pos = k1;
        if (l != 3 || ac[j1] != 'x' || ac[j1 + 1] != 'm' || ac[j1 + 2] != 'l') goto _L65; else goto _L64
_L64:
        if (k != 30) goto _L67; else goto _L66
_L66:
        if (k4 <= i1 + 7 || ac[i1] != 'v' || ac[i1 + 1] != 'e' || ac[i1 + 2] != 'r' || ac[i1 + 3] != 's' || ac[i1 + 4] != 'i' || ac[i1 + 5] != 'o' || ac[i1 + 6] != 'n')
        {
            i = i1;
            s = "xml declaration without version";
            k = 36;
            continue; /* Loop/switch isn't completed */
        }
        i1 += 7;
        for (c1 = ac[i1]; Character.isWhitespace(c1) && ++i1 < k4; c1 = ac[i1]) { }
        if (c1 != '=')
        {
            k = 35;
            i = k1;
            continue; /* Loop/switch isn't completed */
        }
        i1++;
        for (c1 = ac[i1]; Character.isWhitespace(c1) && ++i1 < k4; c1 = ac[i1]) { }
        if (c1 != '\'' && c1 != '"')
        {
            k = 35;
            i = k1;
            continue; /* Loop/switch isn't completed */
        }
        c2 = c1;
        l4 = ++i1;
_L75:
        if (l4 == k4)
        {
            k = 35;
            i = k1;
            continue; /* Loop/switch isn't completed */
        }
        c1 = ac[l4];
        if (c1 != c2) goto _L69; else goto _L68
_L68:
        if (l4 != i1 + 3 || ac[i1] != '1' || ac[i1 + 1] != '.') goto _L71; else goto _L70
_L70:
        c1 = ac[i1 + 2];
        if (c1 == '0') goto _L72; else goto _L71
_L71:
        if (c1 != '1')
        {
            break; /* Loop/switch isn't completed */
        }
_L72:
        for (i1 = l4 + 1; i1 < k4 && Character.isWhitespace(ac[i1]); i1++) { }
        if (k4 > i1 + 7 && ac[i1] == 'e' && ac[i1 + 1] == 'n' && ac[i1 + 2] == 'c' && ac[i1 + 3] == 'o' && ac[i1 + 4] == 'd' && ac[i1 + 5] == 'i' && ac[i1 + 6] == 'n' && ac[i1 + 7] == 'g')
        {
            i1 += 8;
            for (c1 = ac[i1]; Character.isWhitespace(c1) && ++i1 < k4; c1 = ac[i1]) { }
            if (c1 != '=')
            {
                s = "bad 'encoding' declaration";
                k = 36;
                i = k1;
                continue; /* Loop/switch isn't completed */
            }
            i1++;
            for (c1 = ac[i1]; Character.isWhitespace(c1) && ++i1 < k4; c1 = ac[i1]) { }
            if (c1 != '\'' && c1 != '"')
            {
                s = "bad 'encoding' declaration";
                k = 36;
                i = k1;
                continue; /* Loop/switch isn't completed */
            }
            c4 = c1;
            j5 = ++i1;
            do
            {
                if (j5 == k4)
                {
                    s = "bad 'encoding' declaration";
                    k = 36;
                    i = k1;
                    continue; /* Loop/switch isn't completed */
                }
                c1 = ac[j5];
                if (c1 == c4)
                {
                    s1 = new String(ac, i1, j5 - i1);
                    if (linebufferedreader instanceof LineInputStreamReader)
                    {
                        ((LineInputStreamReader)linebufferedreader).setCharset(s1);
                    }
                    for (i1 = j5 + 1; i1 < k4 && Character.isWhitespace(ac[i1]); i1++) { }
                    break;
                }
                j5++;
            } while (true);
        }
          goto _L73
_L69:
        l4++;
        if (true) goto _L75; else goto _L74
_L74:
        k = 35;
        i = k1;
        continue; /* Loop/switch isn't completed */
_L73:
        if (k4 <= i1 + 9 || ac[i1] != 's' || ac[i1 + 1] != 't' || ac[i1 + 2] != 'a' || ac[i1 + 3] != 'n' || ac[i1 + 4] != 'd' || ac[i1 + 5] != 'a' || ac[i1 + 6] != 'l' || ac[i1 + 7] != 'o' || ac[i1 + 8] != 'n' || ac[i1 + 9] != 'e') goto _L77; else goto _L76
_L76:
        i1 += 10;
        for (c1 = ac[i1]; Character.isWhitespace(c1) && ++i1 < k4; c1 = ac[i1]) { }
        if (c1 != '=')
        {
            s = "bad 'standalone' declaration";
            k = 36;
            i = k1;
            continue; /* Loop/switch isn't completed */
        }
        i1++;
        for (c1 = ac[i1]; Character.isWhitespace(c1) && ++i1 < k4; c1 = ac[i1]) { }
        if (c1 != '\'' && c1 != '"')
        {
            s = "bad 'standalone' declaration";
            k = 36;
            i = k1;
            continue; /* Loop/switch isn't completed */
        }
        c3 = c1;
        i5 = ++i1;
_L80:
        if (i5 == k4)
        {
            s = "bad 'standalone' declaration";
            k = 36;
            i = k1;
            continue; /* Loop/switch isn't completed */
        }
        c1 = ac[i5];
        if (c1 != c3) goto _L79; else goto _L78
_L79:
        i5++;
          goto _L80
_L78:
        if (i5 == i1 + 3 && ac[i1] == 'y' && ac[i1 + 1] == 'e' && ac[i1 + 2] == 's' || i5 == i1 + 2 && ac[i1] == 'n' && ac[i1 + 1] == 'o')
        {
            for (i1 = i5 + 1; i1 < k4 && Character.isWhitespace(ac[i1]); i1++) { }
        } else
        {
            s = "bad 'standalone' declaration";
            k = 36;
            i = k1;
            continue; /* Loop/switch isn't completed */
        }
_L77:
        if (k4 != i1)
        {
            s = "junk at end of xml declaration";
            i = i1;
            k = 36;
            continue; /* Loop/switch isn't completed */
        }
          goto _L81
_L67:
        s = "<?xml must be at start of file";
        k = 36;
        i = k1;
        continue; /* Loop/switch isn't completed */
_L65:
        xmlfilter.processingInstructionFromParser(ac, j1, l, i1, k4 - i1);
_L81:
        j1 = j;
        i1 = -1;
        k = 1;
          goto _L28
_L62:
        if (k1 >= j) goto _L28; else goto _L82
_L82:
        j4 = k1 + 1;
        c1 = ac[k1];
        k1 = j4;
          goto _L83
_L90:
        if (k1 >= j) goto _L28; else goto _L84
_L84:
        k3 = k1 + 1;
        c1 = ac[k1];
        k1 = k3;
_L108:
        if (c1 != '>') goto _L86; else goto _L85
_L85:
        l = k1 - 1 - j1;
        if (l < 4 || ac[j1] != '-' || ac[j1 + 1] != '-') goto _L88; else goto _L87
_L87:
        if (ac[k1 - 2] != '-' || ac[k1 - 3] != '-') goto _L90; else goto _L89
_L89:
        linebufferedreader.pos = k1;
        xmlfilter.commentFromParser(ac, j1 + 2, l - 4);
_L92:
        j1 = j;
        k = 1;
          goto _L28
_L88:
        if (l < 6 || ac[j1] != '[' || ac[j1 + 1] != 'C' || ac[j1 + 2] != 'D' || ac[j1 + 3] != 'A' || ac[j1 + 4] != 'T' || ac[j1 + 5] != 'A' || ac[j1 + 6] != '[') goto _L92; else goto _L91
_L91:
        if (ac[k1 - 2] != ']' || ac[k1 - 3] != ']') goto _L90; else goto _L93
_L93:
        linebufferedreader.pos = k1;
        xmlfilter.writeCDATA(ac, j1 + 7, k1 - 10 - j1);
          goto _L92
_L86:
        j3 = j1 + 7;
        if (k1 != j3 || ac[j1] != 'D' || ac[j1 + 1] != 'O' || ac[j1 + 2] != 'C' || ac[j1 + 3] != 'T' || ac[j1 + 4] != 'Y' || ac[j1 + 5] != 'P' || c1 != 'E') goto _L90; else goto _L94
_L94:
        j1 = j;
        k = 15;
          goto _L28
_L12:
        k = 17;
        j1 = i - 1;
        continue; /* Loop/switch isn't completed */
_L14:
        if (i1 < 0)
        {
            i1 = i - 1 - j1 << 1;
            c = '\0';
            k1 = i;
        } else
        {
            k1 = i;
        }
_L96:
        if (c1 == '\'' || c1 == '"')
        {
            if (c == 0)
            {
                c = c1;
            } else
            if (c == c1)
            {
                c = '\0';
            }
        } else
        if (c == 0)
        {
            if (c1 == '[')
            {
                i1 |= 1;
            } else
            {
                if (c1 != ']')
                {
                    continue; /* Loop/switch isn't completed */
                }
                i1 &= -2;
            }
        }
_L98:
        if (k1 >= j) goto _L28; else goto _L95
_L95:
        l3 = k1 + 1;
        c1 = ac[k1];
        k1 = l3;
          goto _L96
        if (c1 != '>' || (i1 & 1) != 0) goto _L98; else goto _L97
_L97:
        linebufferedreader.pos = k1;
        i4 = j1 + (i1 >> 1);
        xmlfilter.emitDoctypeDecl(ac, j1, l, i4, k1 - 1 - i4);
        c = '<';
        j1 = j;
        i1 = -1;
        k = 1;
          goto _L28
_L9:
        c = '<';
        byte0 = 14;
        if (c1 == '/')
        {
            linebufferedreader.pos = i;
            xmlfilter.emitEndAttributes();
            xmlfilter.emitEndElement(null, 0, 0);
            k = 27;
            k1 = i;
        } else
        if (c1 == '>')
        {
            linebufferedreader.pos = i;
            xmlfilter.emitEndAttributes();
            k = 1;
            k1 = i;
        } else
        {
            j1 = i - 1;
            k = 9;
            continue; /* Loop/switch isn't completed */
        }
          goto _L28
_L8:
        if (c1 == ' ' || c1 == '\t' || c1 == '\r' || c1 == '\n' || c1 == '\205') goto _L1; else goto _L99
_L99:
        if (c1 == '\u2028')
        {
            k1 = i;
        } else
        {
            linebufferedreader.pos = i - l;
            xmlfilter.emitStartAttribute(ac, j1, l);
            j1 = j;
            if (c1 == '=')
            {
                k = 11;
                k1 = i;
            } else
            {
                xmlfilter.emitEndAttributes();
                s = "missing or misplaced '=' after attribute name";
                k = 36;
                continue; /* Loop/switch isn't completed */
            }
        }
          goto _L28
_L10:
        if (c1 != '\'' && c1 != '"')
        {
            continue; /* Loop/switch isn't completed */
        }
        c = c1;
        byte0 = 12;
        k = 1;
        k1 = i;
          goto _L28
        if (c1 == ' ' || c1 == '\t' || c1 == '\r' || c1 == '\n' || c1 == '\205') goto _L1; else goto _L100
_L100:
        if (c1 == '\u2028')
        {
            k1 = i;
        } else
        {
            s = "missing or unquoted attribute value";
            k = 36;
            continue; /* Loop/switch isn't completed */
        }
          goto _L28
_L15:
        j1 = i - 1;
        k = 5;
        continue; /* Loop/switch isn't completed */
_L6:
        linebufferedreader.pos = i;
        xmlfilter.emitEndElement(ac, j1, l);
        j1 = j;
        k = 29;
        continue; /* Loop/switch isn't completed */
_L20:
        if (c1 != '>')
        {
            s = "missing '>'";
            k = 36;
            continue; /* Loop/switch isn't completed */
        }
        k = 1;
        k1 = i;
          goto _L28
_L27:
        if (l1 <= 0) goto _L102; else goto _L101
_L101:
        linebufferedreader.pos = j1;
        linebufferedreader.mark(l1 + 1);
_L102:
        linebufferedreader.pos = k1;
        if (linebufferedreader.read() >= 0) goto _L104; else goto _L103
_L104:
        if (l1 <= 0) goto _L106; else goto _L105
_L105:
        try
        {
            linebufferedreader.reset();
            linebufferedreader.skip(l1);
        }
        // Misplaced declaration of an exception variable
        catch (IOException ioexception)
        {
            throw new RuntimeException(ioexception.getMessage());
        }
_L107:
        i2 = linebufferedreader.pos;
        ac = linebufferedreader.buffer;
        j = linebufferedreader.limit;
        if (l1 > 0)
        {
            j1 = i2 - l1;
        } else
        {
            j1 = j;
        }
        j2 = i2 + 1;
        c1 = ac[i2];
        i = j2;
        continue; /* Loop/switch isn't completed */
_L106:
        linebufferedreader.unread_quick();
          goto _L107
_L16:
        k1 = i;
          goto _L108
_L19:
        k1 = i;
          goto _L109
_L103:
        if (k == 1 || k == 28)
        {
            k1;
            return;
        }
        k = 37;
        i = k1;
        if (true) goto _L111; else goto _L110
_L110:
    }

    public static void parse(InputStream inputstream, Object obj, SourceMessages sourcemessages, Consumer consumer)
        throws IOException
    {
        LineInputStreamReader lineinputstreamreader = XMLStreamReader(inputstream);
        if (obj != null)
        {
            lineinputstreamreader.setName(obj);
        }
        parse(((LineBufferedReader) (lineinputstreamreader)), sourcemessages, consumer);
        lineinputstreamreader.close();
    }

    public static void parse(Object obj, SourceMessages sourcemessages, Consumer consumer)
        throws IOException
    {
        parse(Path.openInputStream(obj), obj, sourcemessages, consumer);
    }
}
