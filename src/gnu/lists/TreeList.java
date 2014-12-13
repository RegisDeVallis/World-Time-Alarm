// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;

import gnu.text.Char;
import java.io.IOException;
import java.io.PrintWriter;

// Referenced classes of package gnu.lists:
//            AbstractSequence, XConsumer, PositionConsumer, Consumable, 
//            SeqPosition, Consumer, Sequence, Convert, 
//            TreePosition, NodePredicate, ElementPredicate, AttributePredicate, 
//            ItemPredicate

public class TreeList extends AbstractSequence
    implements Appendable, XConsumer, PositionConsumer, Consumable
{

    protected static final int BEGIN_ATTRIBUTE_LONG = 61705;
    public static final int BEGIN_ATTRIBUTE_LONG_SIZE = 5;
    protected static final int BEGIN_DOCUMENT = 61712;
    protected static final int BEGIN_ELEMENT_LONG = 61704;
    protected static final int BEGIN_ELEMENT_SHORT = 40960;
    protected static final int BEGIN_ELEMENT_SHORT_INDEX_MAX = 4095;
    public static final int BEGIN_ENTITY = 61714;
    public static final int BEGIN_ENTITY_SIZE = 5;
    static final char BOOL_FALSE = 61696;
    static final char BOOL_TRUE = 61697;
    static final int BYTE_PREFIX = 61440;
    static final int CDATA_SECTION = 61717;
    static final int CHAR_FOLLOWS = 61702;
    static final int COMMENT = 61719;
    protected static final int DOCUMENT_URI = 61720;
    static final int DOUBLE_FOLLOWS = 61701;
    static final int END_ATTRIBUTE = 61706;
    public static final int END_ATTRIBUTE_SIZE = 1;
    protected static final int END_DOCUMENT = 61713;
    protected static final int END_ELEMENT_LONG = 61708;
    protected static final int END_ELEMENT_SHORT = 61707;
    protected static final int END_ENTITY = 61715;
    static final int FLOAT_FOLLOWS = 61700;
    public static final int INT_FOLLOWS = 61698;
    static final int INT_SHORT_ZERO = 49152;
    static final int JOINER = 61718;
    static final int LONG_FOLLOWS = 61699;
    public static final int MAX_CHAR_SHORT = 40959;
    static final int MAX_INT_SHORT = 8191;
    static final int MIN_INT_SHORT = -4096;
    static final char OBJECT_REF_FOLLOWS = 61709;
    static final int OBJECT_REF_SHORT = 57344;
    static final int OBJECT_REF_SHORT_INDEX_MAX = 4095;
    protected static final char POSITION_PAIR_FOLLOWS = 61711;
    static final char POSITION_REF_FOLLOWS = 61710;
    protected static final int PROCESSING_INSTRUCTION = 61716;
    public int attrStart;
    int currentParent;
    public char data[];
    public int docStart;
    public int gapEnd;
    public int gapStart;
    public Object objects[];
    public int oindex;

    public TreeList()
    {
        currentParent = -1;
        resizeObjects();
        gapEnd = 200;
        data = new char[gapEnd];
    }

    public TreeList(TreeList treelist)
    {
        this(treelist, 0, treelist.data.length);
    }

    public TreeList(TreeList treelist, int i, int j)
    {
        this();
        treelist.consumeIRange(i, j, this);
    }

    private Object copyToList(int i, int j)
    {
        return new TreeList(this, i, j);
    }

    public Consumer append(char c)
    {
        write(c);
        return this;
    }

    public Consumer append(CharSequence charsequence)
    {
        if (charsequence == null)
        {
            charsequence = "null";
        }
        return append(charsequence, 0, charsequence.length());
    }

    public Consumer append(CharSequence charsequence, int i, int j)
    {
        if (charsequence == null)
        {
            charsequence = "null";
        }
        for (int k = i; k < j; k++)
        {
            append(charsequence.charAt(k));
        }

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
        int i = -1;
        if (gapStart != 0)
        {
            return;
        }
        ensureSpace(6);
        gapEnd = -1 + gapEnd;
        int j = gapStart;
        data[j] = '\uF112';
        setIntN(j + 1, find(obj));
        int k = j + 3;
        if (currentParent != i)
        {
            i = currentParent - j;
        }
        setIntN(k, i);
        gapStart = j + 5;
        currentParent = j;
        data[gapEnd] = '\uF113';
    }

    public void clear()
    {
        gapStart = 0;
        gapEnd = data.length;
        attrStart = 0;
        if (gapEnd > 1500)
        {
            gapEnd = 200;
            data = new char[gapEnd];
        }
        objects = null;
        oindex = 0;
        resizeObjects();
    }

    public int compare(int i, int j)
    {
        int k = posToDataIndex(i);
        int l = posToDataIndex(j);
        if (k < l)
        {
            return -1;
        }
        return k <= l ? 0 : 1;
    }

    public void consume(Consumer consumer)
    {
        consumeIRange(0, data.length, consumer);
    }

    public void consume(SeqPosition seqposition)
    {
        ensureSpace(3);
        int i = find(seqposition.copy());
        char ac[] = data;
        int j = gapStart;
        gapStart = j + 1;
        ac[j] = '\uF10E';
        setIntN(gapStart, i);
        gapStart = 2 + gapStart;
    }

    public int consumeIRange(int i, int j, Consumer consumer)
    {
        int k;
        int i1;
        char c;
        k = i;
        int l;
        char ac[];
        int k4;
        int l4;
        if (i <= gapStart && j > gapStart)
        {
            l = gapStart;
        } else
        {
            l = j;
        }
_L11:
        if (k < l) goto _L2; else goto _L1
_L1:
        if (k != gapStart || j <= gapEnd) goto _L4; else goto _L3
_L3:
        k = gapEnd;
        l = j;
_L2:
        ac = data;
        i1 = k + 1;
        c = ac[k];
        if (c > '\u9FFF') goto _L6; else goto _L5
_L5:
        k4 = i1 - 1;
        l4 = l;
_L37:
        if (i1 < l4) goto _L8; else goto _L7
_L7:
        k = i1;
_L10:
        consumer.write(data, k4, k - k4);
        break MISSING_BLOCK_LABEL_25;
_L8:
        int i5;
        char ac1[] = data;
        i5 = i1 + 1;
        if (ac1[i1] <= '\u9FFF')
        {
            break; /* Loop/switch isn't completed */
        }
        k = i5 - 1;
        if (true) goto _L10; else goto _L9
_L6:
        if (c >= '\uE000' && c <= '\uEFFF')
        {
            consumer.writeObject(objects[c - 57344]);
            k = i1;
        } else
        if (c >= '\uA000' && c <= '\uAFFF')
        {
            int j4 = c - 40960;
            consumer.startElement(objects[j4]);
            k = i1 + 2;
        } else
        {
label0:
            {
                if (c < '\uB000' || c > '\uDFFF')
                {
                    break label0;
                }
                consumer.writeInt(c - 49152);
                k = i1;
            }
        }
          goto _L11
        c;
        JVM INSTR tableswitch 61696 61720: default 392
    //                   61696 718
    //                   61697 718
    //                   61698 1078
    //                   61699 1123
    //                   61700 1099
    //                   61701 1144
    //                   61702 765
    //                   61703 392
    //                   61704 944
    //                   61705 1035
    //                   61706 1065
    //                   61707 929
    //                   61708 1012
    //                   61709 903
    //                   61710 864
    //                   61711 793
    //                   61712 420
    //                   61713 435
    //                   61714 448
    //                   61715 482
    //                   61716 650
    //                   61717 585
    //                   61718 749
    //                   61719 537
    //                   61720 503;
           goto _L12 _L13 _L13 _L14 _L15 _L16 _L17 _L18 _L12 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34 _L35
_L12:
        throw new Error((new StringBuilder()).append("unknown code:").append(c).toString());
_L27:
        consumer.startDocument();
        k = i1 + 4;
          goto _L11
_L28:
        consumer.endDocument();
        k = i1;
          goto _L11
_L29:
        if (consumer instanceof TreeList)
        {
            ((TreeList)consumer).beginEntity(objects[getIntN(i1)]);
        }
        k = i1 + 4;
          goto _L11
_L30:
        if (consumer instanceof TreeList)
        {
            ((TreeList)consumer).endEntity();
            k = i1;
        } else
        {
            k = i1;
        }
          goto _L11
_L35:
        if (consumer instanceof TreeList)
        {
            ((TreeList)consumer).writeDocumentUri(objects[getIntN(i1)]);
        }
        k = i1 + 2;
          goto _L11
_L34:
        int l3 = getIntN(i1);
        int i4 = i1 + 2;
        if (consumer instanceof XConsumer)
        {
            ((XConsumer)consumer).writeComment(data, i4, l3);
        }
        k = i4 + l3;
          goto _L11
_L32:
        int j3 = getIntN(i1);
        int k3 = i1 + 2;
        if (consumer instanceof XConsumer)
        {
            ((XConsumer)consumer).writeCDATA(data, k3, j3);
        } else
        {
            consumer.write(data, k3, j3);
        }
        k = k3 + j3;
          goto _L11
_L31:
        String s = (String)objects[getIntN(i1)];
        int l2 = getIntN(i1 + 2);
        int i3 = i1 + 4;
        if (consumer instanceof XConsumer)
        {
            ((XConsumer)consumer).writeProcessingInstruction(s, data, i3, l2);
        }
        k = i3 + l2;
          goto _L11
_L13:
        boolean flag;
        if (c != '\uF100')
        {
            flag = true;
        } else
        {
            flag = false;
        }
        consumer.writeBoolean(flag);
        k = i1;
          goto _L11
_L33:
        consumer.write("");
        k = i1;
          goto _L11
_L18:
        consumer.write(data, i1, (c + 1) - 61702);
        k = i1 + 1;
          goto _L11
_L26:
        AbstractSequence abstractsequence = (AbstractSequence)objects[getIntN(i1)];
        int k2 = getIntN(i1 + 2);
        if (consumer instanceof PositionConsumer)
        {
            ((PositionConsumer)consumer).writePosition(abstractsequence, k2);
        } else
        {
            consumer.writeObject(abstractsequence.getIteratorAtPos(k2));
        }
        k = i1 + 4;
          goto _L11
_L25:
        if (!(consumer instanceof PositionConsumer)) goto _L24; else goto _L36
_L36:
        ((PositionConsumer)consumer).consume((SeqPosition)objects[getIntN(i1)]);
        k = i1 + 2;
          goto _L11
_L24:
        consumer.writeObject(objects[getIntN(i1)]);
        k = i1 + 2;
          goto _L11
_L22:
        k = i1 + 1;
        consumer.endElement();
          goto _L11
_L19:
        int k1 = getIntN(i1);
        int l1;
        int i2;
        int j2;
        if (k1 >= 0)
        {
            l1 = i1 - 1;
        } else
        {
            l1 = data.length;
        }
        i2 = k1 + l1;
        k = i1 + 2;
        j2 = getIntN(i2 + 1);
        consumer.startElement(objects[j2]);
          goto _L11
_L23:
        getIntN(i1);
        consumer.endElement();
        k = i1 + 6;
          goto _L11
_L20:
        int j1 = getIntN(i1);
        consumer.startAttribute(objects[j1]);
        k = i1 + 4;
          goto _L11
_L21:
        consumer.endAttribute();
        k = i1;
          goto _L11
_L14:
        consumer.writeInt(getIntN(i1));
        k = i1 + 2;
          goto _L11
_L16:
        consumer.writeFloat(Float.intBitsToFloat(getIntN(i1)));
        k = i1 + 2;
          goto _L11
_L15:
        consumer.writeLong(getLongN(i1));
        k = i1 + 4;
          goto _L11
_L17:
        consumer.writeDouble(Double.longBitsToDouble(getLongN(i1)));
        k = i1 + 4;
          goto _L11
_L4:
        return k;
_L9:
        i1 = i5;
          goto _L37
    }

    public boolean consumeNext(int i, Consumer consumer)
    {
        if (!hasNext(i))
        {
            return false;
        }
        int j = posToDataIndex(i);
        int k = nextNodeIndex(j, 0x7fffffff);
        if (k == j)
        {
            k = nextDataIndex(j);
        }
        if (k >= 0)
        {
            consumeIRange(j, k, consumer);
        }
        return true;
    }

    public void consumePosRange(int i, int j, Consumer consumer)
    {
        consumeIRange(posToDataIndex(i), posToDataIndex(j), consumer);
    }

    public int createPos(int i, boolean flag)
    {
        return createRelativePos(0, i, flag);
    }

    public int createRelativePos(int i, int j, boolean flag)
    {
        if (flag)
        {
            if (j == 0)
            {
                if ((i & 1) != 0)
                {
                    return i;
                }
                if (i == 0)
                {
                    return 1;
                }
            }
            j--;
        }
        if (j < 0)
        {
            throw unsupported("backwards createRelativePos");
        }
        int k = posToDataIndex(i);
        while (--j >= 0) 
        {
            k = nextDataIndex(k);
            if (k < 0)
            {
                throw new IndexOutOfBoundsException();
            }
        }
        if (k >= gapEnd)
        {
            k -= gapEnd - gapStart;
        }
        int l;
        if (flag)
        {
            l = 1 | k + 1 << 1;
        } else
        {
            l = k << 1;
        }
        return l;
    }

    public Object documentUriOfPos(int i)
    {
        int j;
        j = posToDataIndex(i);
        break MISSING_BLOCK_LABEL_6;
        if (j != data.length && data[j] == '\uF110')
        {
            int k = j + 5;
            if (k == gapStart)
            {
                k = gapEnd;
            }
            if (k < data.length && data[k] == '\uF118')
            {
                return objects[getIntN(k + 1)];
            }
        }
        return null;
    }

    public void dump()
    {
        PrintWriter printwriter = new PrintWriter(System.out);
        dump(printwriter);
        printwriter.flush();
    }

    public void dump(PrintWriter printwriter)
    {
        printwriter.println((new StringBuilder()).append(getClass().getName()).append(" @").append(Integer.toHexString(System.identityHashCode(this))).append(" gapStart:").append(gapStart).append(" gapEnd:").append(gapEnd).append(" length:").append(data.length).toString());
        dump(printwriter, 0, data.length);
    }

    public void dump(PrintWriter printwriter, int i, int j)
    {
        int k;
        int l;
        k = 0;
        l = i;
_L10:
        if (l >= j)
        {
            break MISSING_BLOCK_LABEL_1785;
        }
        if (l >= gapStart && l < gapEnd) goto _L2; else goto _L1
_L1:
        char c;
        c = data[l];
        printwriter.print((new StringBuilder()).append("").append(l).append(": 0x").append(Integer.toHexString(c)).append('=').append((short)c).toString());
        if (--k >= 0) goto _L4; else goto _L3
_L3:
        if (c > '\u9FFF') goto _L6; else goto _L5
_L5:
        if (c >= ' ' && c < '\177')
        {
            printwriter.print((new StringBuilder()).append("='").append(c).append("'").toString());
        } else
        if (c == '\n')
        {
            printwriter.print("='\\n'");
        } else
        {
            printwriter.print((new StringBuilder()).append("='\\u").append(Integer.toHexString(c)).append("'").toString());
        }
_L4:
        printwriter.println();
        if (true && k > 0)
        {
            l += k;
            k = 0;
        }
_L2:
        l++;
        continue; /* Loop/switch isn't completed */
_L6:
        if (c >= '\uE000' && c <= '\uEFFF')
        {
            int j8 = c - 57344;
            Object obj1 = objects[j8];
            printwriter.print((new StringBuilder()).append("=Object#").append(j8).append('=').append(obj1).append(':').append(obj1.getClass().getName()).append('@').append(Integer.toHexString(System.identityHashCode(obj1))).toString());
            continue; /* Loop/switch isn't completed */
        }
        if (c >= '\uA000' && c <= '\uAFFF')
        {
            int k7 = c - 40960;
            int i8 = l + data[l + 1];
            printwriter.print((new StringBuilder()).append("=BEGIN_ELEMENT_SHORT end:").append(i8).append(" index#").append(k7).append("=<").append(objects[k7]).append('>').toString());
            k = 2;
            continue; /* Loop/switch isn't completed */
        }
        if (c < '\uB000' || c > '\uDFFF')
        {
            break; /* Loop/switch isn't completed */
        }
        printwriter.print((new StringBuilder()).append("= INT_SHORT:").append(c - 49152).toString());
        if (true) goto _L4; else goto _L7
_L7:
        switch (c)
        {
        case 61696: 
            printwriter.print("= false");
            break;

        case 61698: 
            int j7 = getIntN(l + 1);
            printwriter.print((new StringBuilder()).append("=INT_FOLLOWS value:").append(j7).toString());
            k = 2;
            break;

        case 61699: 
            long l7 = getLongN(l + 1);
            printwriter.print((new StringBuilder()).append("=LONG_FOLLOWS value:").append(l7).toString());
            k = 4;
            break;

        case 61700: 
            int i7 = getIntN(l + 1);
            printwriter.write((new StringBuilder()).append("=FLOAT_FOLLOWS value:").append(Float.intBitsToFloat(i7)).toString());
            k = 2;
            break;

        case 61701: 
            long l6 = getLongN(l + 1);
            printwriter.print((new StringBuilder()).append("=DOUBLE_FOLLOWS value:").append(Double.longBitsToDouble(l6)).toString());
            k = 4;
            break;

        case 61712: 
            int i6 = getIntN(l + 1);
            int j6;
            int k6;
            if (i6 < 0)
            {
                j6 = data.length;
            } else
            {
                j6 = l;
            }
            k6 = i6 + j6;
            printwriter.print("=BEGIN_DOCUMENT end:");
            printwriter.print(k6);
            printwriter.print(" parent:");
            printwriter.print(getIntN(l + 3));
            k = 4;
            break;

        case 61714: 
            int l5 = getIntN(l + 1);
            printwriter.print("=BEGIN_ENTITY base:");
            printwriter.print(l5);
            printwriter.print(" parent:");
            printwriter.print(getIntN(l + 3));
            k = 4;
            break;

        case 61715: 
            printwriter.print("=END_ENTITY");
            break;

        case 61720: 
            printwriter.print("=DOCUMENT_URI: ");
            int k5 = getIntN(l + 1);
            printwriter.print(objects[k5]);
            k = 2;
            break;

        case 61719: 
            printwriter.print("=COMMENT: '");
            int j5 = getIntN(l + 1);
            printwriter.write(data, l + 3, j5);
            printwriter.print('\'');
            k = j5 + 2;
            break;

        case 61717: 
            printwriter.print("=CDATA: '");
            int i5 = getIntN(l + 1);
            printwriter.write(data, l + 3, i5);
            printwriter.print('\'');
            k = i5 + 2;
            break;

        case 61716: 
            printwriter.print("=PROCESSING_INSTRUCTION: ");
            int k4 = getIntN(l + 1);
            printwriter.print(objects[k4]);
            printwriter.print(" '");
            int l4 = getIntN(l + 3);
            printwriter.write(data, l + 5, l4);
            printwriter.print('\'');
            k = l4 + 4;
            break;

        case 61713: 
            printwriter.print("=END_DOCUMENT");
            break;

        case 61697: 
            printwriter.print("= true");
            break;

        case 61718: 
            printwriter.print("= joiner");
            break;

        case 61702: 
            printwriter.print("=CHAR_FOLLOWS");
            k = 1;
            break;

        case 61709: 
        case 61710: 
            k = 2;
            break;

        case 61707: 
            printwriter.print("=END_ELEMENT_SHORT begin:");
            int i4 = l - data[l + 1];
            printwriter.print(i4);
            int j4 = data[i4] - 40960;
            printwriter.print(" -> #");
            printwriter.print(j4);
            printwriter.print("=<");
            printwriter.print(objects[j4]);
            printwriter.print('>');
            k = 1;
            break;

        case 61704: 
            int i3 = getIntN(l + 1);
            int j3;
            int k3;
            int l3;
            if (i3 < 0)
            {
                j3 = data.length;
            } else
            {
                j3 = l;
            }
            k3 = i3 + j3;
            printwriter.print("=BEGIN_ELEMENT_LONG end:");
            printwriter.print(k3);
            l3 = getIntN(k3 + 1);
            printwriter.print(" -> #");
            printwriter.print(l3);
            if (l3 >= 0 && l3 + 1 < objects.length)
            {
                printwriter.print((new StringBuilder()).append("=<").append(objects[l3]).append('>').toString());
            } else
            {
                printwriter.print("=<out-of-bounds>");
            }
            k = 2;
            break;

        case 61708: 
            int j2 = getIntN(l + 1);
            printwriter.print((new StringBuilder()).append("=END_ELEMENT_LONG name:").append(j2).append("=<").append(objects[j2]).append('>').toString());
            int k2 = getIntN(l + 3);
            if (k2 < 0)
            {
                k2 += l;
            }
            printwriter.print((new StringBuilder()).append(" begin:").append(k2).toString());
            int l2 = getIntN(l + 5);
            if (l2 < 0)
            {
                l2 += l;
            }
            printwriter.print((new StringBuilder()).append(" parent:").append(l2).toString());
            k = 6;
            break;

        case 61705: 
            int j1 = getIntN(l + 1);
            printwriter.print((new StringBuilder()).append("=BEGIN_ATTRIBUTE name:").append(j1).append("=").append(objects[j1]).toString());
            int k1 = getIntN(l + 3);
            int l1;
            int i2;
            if (k1 < 0)
            {
                l1 = data.length;
            } else
            {
                l1 = l;
            }
            i2 = k1 + l1;
            printwriter.print((new StringBuilder()).append(" end:").append(i2).toString());
            k = 4;
            break;

        case 61706: 
            printwriter.print("=END_ATTRIBUTE");
            break;

        case 61711: 
            printwriter.print("=POSITION_PAIR_FOLLOWS seq:");
            int i1 = getIntN(l + 1);
            printwriter.print(i1);
            printwriter.print('=');
            Object obj = objects[i1];
            String s;
            if (obj == null)
            {
                s = null;
            } else
            {
                s = obj.getClass().getName();
            }
            printwriter.print(s);
            printwriter.print('@');
            if (obj == null)
            {
                printwriter.print("null");
            } else
            {
                printwriter.print(Integer.toHexString(System.identityHashCode(obj)));
            }
            printwriter.print(" ipos:");
            printwriter.print(getIntN(l + 3));
            k = 4;
            break;
        }
        if (true) goto _L4; else goto _L8
_L8:
        return;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public void endAttribute()
    {
        if (attrStart <= 0)
        {
            return;
        }
        if (data[gapEnd] != '\uF10A')
        {
            throw new Error("unexpected endAttribute");
        } else
        {
            gapEnd = 1 + gapEnd;
            setIntN(2 + attrStart, 1 + (gapStart - attrStart));
            attrStart = 0;
            char ac[] = data;
            int i = gapStart;
            gapStart = i + 1;
            ac[i] = '\uF10A';
            return;
        }
    }

    public void endDocument()
    {
        if (data[gapEnd] != '\uF111' || docStart <= 0 || data[currentParent] != '\uF110')
        {
            throw new Error("unexpected endDocument");
        }
        gapEnd = 1 + gapEnd;
        setIntN(docStart, 1 + (gapStart - docStart));
        docStart = 0;
        char ac[] = data;
        int i = gapStart;
        gapStart = i + 1;
        ac[i] = '\uF111';
        int j = getIntN(3 + currentParent);
        if (j < -1)
        {
            j += currentParent;
        }
        currentParent = j;
    }

    public void endElement()
    {
        if (data[gapEnd] != '\uF10C')
        {
            throw new Error("unexpected endElement");
        }
        int i = getIntN(1 + gapEnd);
        int j = getIntN(3 + gapEnd);
        int k = getIntN(5 + gapEnd);
        currentParent = k;
        gapEnd = 7 + gapEnd;
        int l = gapStart - j;
        int i1 = j - k;
        if (i < 4095 && l < 0x10000 && i1 < 0x10000)
        {
            data[j] = (char)(0xa000 | i);
            data[j + 1] = (char)l;
            data[j + 2] = (char)i1;
            data[gapStart] = '\uF10B';
            data[1 + gapStart] = (char)l;
            gapStart = 2 + gapStart;
            return;
        }
        data[j] = '\uF108';
        setIntN(j + 1, l);
        data[gapStart] = '\uF10C';
        setIntN(1 + gapStart, i);
        setIntN(3 + gapStart, -l);
        if (k >= gapStart || j <= gapStart)
        {
            k -= gapStart;
        }
        setIntN(5 + gapStart, k);
        gapStart = 7 + gapStart;
    }

    public void endEntity()
    {
        if (1 + gapEnd != data.length || data[gapEnd] != '\uF113')
        {
            return;
        }
        if (data[currentParent] != '\uF112')
        {
            throw new Error("unexpected endEntity");
        }
        gapEnd = 1 + gapEnd;
        char ac[] = data;
        int i = gapStart;
        gapStart = i + 1;
        ac[i] = '\uF113';
        int j = getIntN(3 + currentParent);
        if (j < -1)
        {
            j += currentParent;
        }
        currentParent = j;
    }

    public void ensureSpace(int i)
    {
        int j = gapEnd - gapStart;
        if (i > j)
        {
            int k = data.length;
            int l = i + (k - j);
            int i1 = k * 2;
            if (i1 < l)
            {
                i1 = l;
            }
            char ac[] = new char[i1];
            if (gapStart > 0)
            {
                System.arraycopy(data, 0, ac, 0, gapStart);
            }
            int j1 = k - gapEnd;
            if (j1 > 0)
            {
                System.arraycopy(data, gapEnd, ac, i1 - j1, j1);
            }
            gapEnd = i1 - j1;
            data = ac;
        }
    }

    public int find(Object obj)
    {
        if (oindex == objects.length)
        {
            resizeObjects();
        }
        objects[oindex] = obj;
        int i = oindex;
        oindex = i + 1;
        return i;
    }

    public int firstAttributePos(int i)
    {
        int j = gotoAttributesStart(posToDataIndex(i));
        if (j < 0)
        {
            return 0;
        } else
        {
            return j << 1;
        }
    }

    public int firstChildPos(int i)
    {
        int j = gotoChildrenStart(posToDataIndex(i));
        if (j < 0)
        {
            return 0;
        } else
        {
            return j << 1;
        }
    }

    public Object get(int i)
    {
        int j = 0;
        while (--i >= 0) 
        {
            j = nextPos(j);
            if (j == 0)
            {
                throw new IndexOutOfBoundsException();
            }
        }
        return getPosNext(j);
    }

    public int getAttributeCount(int i)
    {
        int j = 0;
        for (int k = firstAttributePos(i); k != 0 && getNextKind(k) == 35; k = nextPos(k))
        {
            j++;
        }

        return j;
    }

    protected int getIndexDifference(int i, int j)
    {
        int k = posToDataIndex(j);
        int l = posToDataIndex(i);
        boolean flag = false;
        if (k > l)
        {
            flag = true;
            int j1 = l;
            l = k;
            k = j1;
        }
        int i1;
        for (i1 = 0; k < l; i1++)
        {
            k = nextDataIndex(k);
        }

        if (flag)
        {
            i1 = -i1;
        }
        return i1;
    }

    protected final int getIntN(int i)
    {
        return data[i] << 16 | 0xffff & data[i + 1];
    }

    protected final long getLongN(int i)
    {
        char ac[] = data;
        return (65535L & (long)ac[i]) << 48 | (65535L & (long)ac[i + 1]) << 32 | (65535L & (long)ac[i + 2]) << 16 | 65535L & (long)ac[i + 3];
    }

    public int getNextKind(int i)
    {
        return getNextKindI(posToDataIndex(i));
    }

    public int getNextKindI(int i)
    {
        if (i != data.length) goto _L2; else goto _L1
_L1:
        return 0;
_L2:
        char c = data[i];
        if (c <= '\u9FFF')
        {
            return 29;
        }
        if (c >= '\uE000' && c <= '\uEFFF')
        {
            return 32;
        }
        if (c >= '\uA000' && c <= '\uAFFF')
        {
            return 33;
        }
        if ((0xff00 & c) == 61440)
        {
            return 28;
        }
        if (c >= '\uB000' && c <= '\uDFFF')
        {
            return 22;
        }
        switch (c)
        {
        case 61703: 
        case 61709: 
        case 61710: 
        case 61711: 
        case 61718: 
        default:
            return 32;

        case 61696: 
        case 61697: 
            return 27;

        case 61698: 
            return 22;

        case 61699: 
            return 24;

        case 61700: 
            return 25;

        case 61701: 
            return 26;

        case 61702: 
        case 61712: 
            return 34;

        case 61714: 
            return getNextKind(i + 5 << 1);

        case 61704: 
            return 33;

        case 61705: 
            return 35;

        case 61717: 
            return 31;

        case 61719: 
            return 36;

        case 61716: 
            return 37;

        case 61706: 
        case 61707: 
        case 61708: 
        case 61713: 
        case 61715: 
            break;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public String getNextTypeName(int i)
    {
        Object obj = getNextTypeObject(i);
        if (obj == null)
        {
            return null;
        } else
        {
            return obj.toString();
        }
    }

    public Object getNextTypeObject(int i)
    {
        int j = posToDataIndex(i);
_L4:
        if (j != data.length) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        char c;
        int k;
        c = data[j];
        if (c == '\uF112')
        {
            break; /* Loop/switch isn't completed */
        }
        if (c >= '\uA000' && c <= '\uAFFF')
        {
            k = c - 40960;
        } else
        if (c == '\uF108')
        {
            int l = getIntN(j + 1);
            if (l < 0)
            {
                j = data.length;
            }
            k = getIntN(1 + (l + j));
        } else
        {
            if (c != '\uF109')
            {
                continue; /* Loop/switch isn't completed */
            }
            k = getIntN(j + 1);
        }
_L6:
        if (k >= 0)
        {
            return objects[k];
        }
        if (true) goto _L1; else goto _L3
_L3:
        j += 5;
          goto _L4
        if (c != '\uF114') goto _L1; else goto _L5
_L5:
        k = getIntN(j + 1);
          goto _L6
    }

    public Object getPosNext(int i)
    {
        int j = posToDataIndex(i);
        if (j == data.length)
        {
            return Sequence.eofValue;
        }
        char c = data[j];
        if (c <= '\u9FFF')
        {
            return Convert.toObject(c);
        }
        if (c >= '\uE000' && c <= '\uEFFF')
        {
            return objects[c - 57344];
        }
        if (c >= '\uA000' && c <= '\uAFFF')
        {
            return copyToList(j, 2 + (j + data[j + 1]));
        }
        if (c >= '\uB000' && c <= '\uDFFF')
        {
            return Convert.toObject(c - 49152);
        }
        switch (c)
        {
        case 61703: 
        case 61714: 
        case 61715: 
        case 61716: 
        case 61717: 
        default:
            throw unsupported((new StringBuilder()).append("getPosNext, code=").append(Integer.toHexString(c)).toString());

        case 61712: 
            int k1 = getIntN(j + 1);
            int l1;
            if (k1 < 0)
            {
                l1 = data.length;
            } else
            {
                l1 = j;
            }
            return copyToList(j, 1 + (k1 + l1));

        case 61696: 
        case 61697: 
            boolean flag;
            if (c != '\uF100')
            {
                flag = true;
            } else
            {
                flag = false;
            }
            return Convert.toObject(flag);

        case 61698: 
            return Convert.toObject(getIntN(j + 1));

        case 61699: 
            return Convert.toObject(getLongN(j + 1));

        case 61700: 
            return Convert.toObject(Float.intBitsToFloat(getIntN(j + 1)));

        case 61701: 
            return Convert.toObject(Double.longBitsToDouble(getLongN(j + 1)));

        case 61702: 
            return Convert.toObject(data[j + 1]);

        case 61705: 
            int i1 = getIntN(j + 3);
            int j1;
            if (i1 < 0)
            {
                j1 = data.length;
            } else
            {
                j1 = j;
            }
            return copyToList(j, 1 + (i1 + j1));

        case 61704: 
            int k = getIntN(j + 1);
            int l;
            if (k < 0)
            {
                l = data.length;
            } else
            {
                l = j;
            }
            return copyToList(j, 7 + (k + l));

        case 61706: 
        case 61707: 
        case 61708: 
        case 61713: 
            return Sequence.eofValue;

        case 61709: 
        case 61710: 
            return objects[getIntN(j + 1)];

        case 61718: 
            return "";

        case 61711: 
            return ((AbstractSequence)objects[getIntN(j + 1)]).getIteratorAtPos(getIntN(j + 3));
        }
    }

    public int getPosNextInt(int i)
    {
        int j = posToDataIndex(i);
        if (j < data.length)
        {
            char c = data[j];
            if (c >= '\uB000' && c <= '\uDFFF')
            {
                return c - 49152;
            }
            if (c == '\uF102')
            {
                return getIntN(j + 1);
            }
        }
        return ((Number)getPosNext(i)).intValue();
    }

    public Object getPosPrevious(int i)
    {
        if ((i & 1) != 0 && i != -1)
        {
            return getPosNext(i - 3);
        } else
        {
            return super.getPosPrevious(i);
        }
    }

    public int gotoAttributesStart(int i)
    {
        if (i >= gapStart)
        {
            i += gapEnd - gapStart;
        }
        char c;
        if (i != data.length)
        {
            if ((c = data[i]) >= '\uA000' && c <= '\uAFFF' || c == '\uF108')
            {
                return i + 3;
            }
        }
        return -1;
    }

    public boolean gotoAttributesStart(TreePosition treeposition)
    {
        int i = gotoAttributesStart(treeposition.ipos >> 1);
        if (i < 0)
        {
            return false;
        } else
        {
            treeposition.push(this, i << 1);
            return true;
        }
    }

    public final int gotoChildrenStart(int i)
    {
        if (i != data.length) goto _L2; else goto _L1
_L1:
        return -1;
_L2:
        char c;
        int j;
        c = data[i];
        if ((c < '\uA000' || c > '\uAFFF') && c != '\uF108')
        {
            continue; /* Loop/switch isn't completed */
        }
        j = i + 3;
_L4:
        do
        {
            if (j >= gapStart)
            {
                j += gapEnd - gapStart;
            }
            char c1 = data[j];
            if (c1 == '\uF109')
            {
                int k = getIntN(j + 3);
                if (k < 0)
                {
                    j = data.length;
                }
                j += k;
            } else
            if (c1 == '\uF10A' || c1 == '\uF116')
            {
                j++;
            } else
            if (c1 == '\uF118')
            {
                j += 3;
            } else
            {
                return j;
            }
        } while (true);
        if (c != '\uF110' && c != '\uF112') goto _L1; else goto _L3
_L3:
        j = i + 5;
          goto _L4
    }

    public boolean hasNext(int i)
    {
        int j = posToDataIndex(i);
        char c;
        if (j != data.length)
        {
            if ((c = data[j]) != '\uF10A' && c != '\uF10B' && c != '\uF10C' && c != '\uF111')
            {
                return true;
            }
        }
        return false;
    }

    public int hashCode()
    {
        return System.identityHashCode(this);
    }

    public boolean ignoring()
    {
        return false;
    }

    public boolean isEmpty()
    {
        int i;
        int j;
        boolean flag;
        if (gapStart == 0)
        {
            i = gapEnd;
        } else
        {
            i = 0;
        }
        j = data.length;
        flag = false;
        if (i == j)
        {
            flag = true;
        }
        return flag;
    }

    public final int nextDataIndex(int i)
    {
        int j;
        char c;
        if (i == gapStart)
        {
            i = gapEnd;
        }
        if (i == data.length)
        {
            return -1;
        }
        char ac[] = data;
        j = i + 1;
        c = ac[i];
        if (c <= '\u9FFF' || c >= '\uE000' && c <= '\uEFFF' || c >= '\uB000' && c <= '\uDFFF')
        {
            return j;
        }
        if (c >= '\uA000' && c <= '\uAFFF')
        {
            return 1 + (j + data[j]);
        }
        c;
        JVM INSTR tableswitch 61696 61719: default 212
    //                   61696 338
    //                   61697 338
    //                   61698 344
    //                   61699 427
    //                   61700 344
    //                   61701 427
    //                   61702 340
    //                   61703 212
    //                   61704 354
    //                   61705 390
    //                   61706 352
    //                   61707 352
    //                   61708 352
    //                   61709 344
    //                   61710 344
    //                   61711 348
    //                   61712 243
    //                   61713 352
    //                   61714 278
    //                   61715 352
    //                   61716 431
    //                   61717 448
    //                   61718 338
    //                   61719 448;
           goto _L1 _L2 _L2 _L3 _L4 _L3 _L4 _L5 _L1 _L6 _L7 _L8 _L8 _L8 _L3 _L3 _L9 _L10 _L8 _L11 _L8 _L12 _L13 _L2 _L13
_L1:
        throw new Error((new StringBuilder()).append("unknown code:").append(Integer.toHexString(c)).toString());
_L10:
        int i2 = getIntN(j);
        int j2;
        if (i2 < 0)
        {
            j2 = data.length;
        } else
        {
            j2 = j - 1;
        }
        return 1 + (i2 + j2);
_L11:
        int l1 = j + 4;
        do
        {
            if (l1 == gapStart)
            {
                l1 = gapEnd;
            }
            if (l1 == data.length)
            {
                return -1;
            }
            if (data[l1] == '\uF113')
            {
                return l1 + 1;
            }
            l1 = nextDataIndex(l1);
        } while (true);
_L2:
        return j;
_L5:
        return j + 1;
_L3:
        return j + 2;
_L9:
        return j + 4;
_L8:
        return -1;
_L6:
        int j1 = getIntN(j);
        int k1;
        if (j1 < 0)
        {
            k1 = data.length;
        } else
        {
            k1 = j - 1;
        }
        return 7 + (j1 + k1);
_L7:
        int l = getIntN(j + 2);
        int i1;
        if (l < 0)
        {
            i1 = data.length;
        } else
        {
            i1 = j - 1;
        }
        return 1 + (l + i1);
_L4:
        return j + 4;
_L12:
        int k = j + 2;
_L15:
        return k + 2 + getIntN(k);
_L13:
        k = j;
        if (true) goto _L15; else goto _L14
_L14:
    }

    public int nextMatching(int i, ItemPredicate itempredicate, int j, boolean flag)
    {
        int k;
        int i1;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        k = posToDataIndex(i);
        int l = posToDataIndex(j);
        i1 = k;
        if (itempredicate instanceof NodePredicate)
        {
            i1 = nextNodeIndex(i1, l);
        }
        if (itempredicate instanceof ElementPredicate)
        {
            flag1 = true;
            flag2 = true;
            flag3 = false;
        } else
        if (itempredicate instanceof AttributePredicate)
        {
            flag1 = true;
            flag2 = false;
            flag3 = false;
        } else
        {
            flag1 = true;
            flag2 = true;
            flag3 = true;
        }
_L2:
        char c;
        int j1;
        if (i1 == gapStart)
        {
            i1 = gapEnd;
        }
        if (i1 >= l && l != -1)
        {
            return 0;
        }
        c = data[i1];
        if (c > '\u9FFF' && (c < '\uE000' || c > '\uEFFF') && (c < '\uB000' || c > '\uDFFF'))
        {
            break; /* Loop/switch isn't completed */
        }
        if (flag3 && itempredicate.isInstancePos(this, i1 << 1))
        {
            if (i1 >= gapEnd)
            {
                i1 -= gapEnd - gapStart;
            }
            return i1 << 1;
        }
        j1 = i1 + 1;
_L25:
        i1 = j1;
        if (true) goto _L2; else goto _L1
_L1:
        c;
        JVM INSTR tableswitch 61696 61720: default 332
    //                   61696 592
    //                   61697 592
    //                   61698 443
    //                   61699 615
    //                   61700 443
    //                   61701 615
    //                   61702 457
    //                   61703 332
    //                   61704 698
    //                   61705 529
    //                   61706 513
    //                   61707 466
    //                   61708 496
    //                   61709 443
    //                   61710 443
    //                   61711 482
    //                   61712 420
    //                   61713 513
    //                   61714 434
    //                   61715 520
    //                   61716 629
    //                   61717 675
    //                   61718 606
    //                   61719 652
    //                   61720 411;
           goto _L3 _L4 _L4 _L5 _L6 _L5 _L6 _L7 _L3 _L8 _L9 _L10 _L11 _L12 _L5 _L5 _L13 _L14 _L10 _L15 _L16 _L17 _L18 _L19 _L20 _L21
_L3:
        if (c < '\uA000' || c > '\uAFFF') goto _L23; else goto _L22
_L22:
        if (flag)
        {
            j1 = i1 + 3;
        } else
        {
            j1 = 2 + (i1 + data[i1 + 1]);
        }
        if (!flag2) goto _L25; else goto _L24
_L24:
        if (i1 > k && itempredicate.isInstancePos(this, i1 << 1))
        {
            if (i1 >= gapEnd)
            {
                i1 -= gapEnd - gapStart;
            }
            return i1 << 1;
        } else
        {
            break; /* Loop/switch isn't completed */
        }
_L21:
        j1 = i1 + 3;
        break; /* Loop/switch isn't completed */
_L14:
        j1 = i1 + 5;
        if (!flag1) goto _L26; else goto _L24
_L26:
        break; /* Loop/switch isn't completed */
_L15:
        j1 = i1 + 5;
        break; /* Loop/switch isn't completed */
_L5:
        j1 = i1 + 3;
        if (!flag3) goto _L27; else goto _L24
_L27:
        break; /* Loop/switch isn't completed */
_L7:
        j1 = i1 + 2;
        break; /* Loop/switch isn't completed */
_L11:
        if (!flag)
        {
            return 0;
        }
        j1 = i1 + 2;
        break; /* Loop/switch isn't completed */
_L13:
        j1 = i1 + 5;
        if (!flag3) goto _L28; else goto _L24
_L28:
        break; /* Loop/switch isn't completed */
_L12:
        if (!flag)
        {
            return 0;
        }
        j1 = i1 + 7;
        break; /* Loop/switch isn't completed */
_L10:
        if (!flag)
        {
            return 0;
        }
_L16:
        j1 = i1 + 1;
        break; /* Loop/switch isn't completed */
_L9:
        if (flag1)
        {
            int i2 = getIntN(i1 + 3);
            int j2 = i2 + 1;
            int k2;
            if (i2 < 0)
            {
                k2 = data.length;
            } else
            {
                k2 = i1;
            }
            j1 = j2 + k2;
        } else
        {
            j1 = i1 + 5;
        }
        if (true) goto _L29; else goto _L24
_L29:
        break; /* Loop/switch isn't completed */
_L4:
        j1 = i1 + 1;
        if (!flag3) goto _L30; else goto _L24
_L30:
        break; /* Loop/switch isn't completed */
_L19:
        j1 = i1 + 1;
        break; /* Loop/switch isn't completed */
_L6:
        j1 = i1 + 5;
        if (!flag3) goto _L31; else goto _L24
_L31:
        break; /* Loop/switch isn't completed */
_L17:
        j1 = i1 + 5 + getIntN(i1 + 3);
        if (!flag1) goto _L32; else goto _L24
_L32:
        break; /* Loop/switch isn't completed */
_L20:
        j1 = i1 + 3 + getIntN(i1 + 1);
        if (!flag1) goto _L33; else goto _L24
_L33:
        break; /* Loop/switch isn't completed */
_L18:
        j1 = i1 + 3 + getIntN(i1 + 1);
        if (!flag3) goto _L34; else goto _L24
_L34:
        break; /* Loop/switch isn't completed */
_L8:
        if (flag)
        {
            j1 = i1 + 3;
        } else
        {
            int k1 = getIntN(i1 + 1);
            int l1;
            if (k1 < 0)
            {
                l1 = data.length;
            } else
            {
                l1 = i1;
            }
            j1 = 7 + (l1 + k1);
        }
        if (!flag2) goto _L25; else goto _L24
_L23:
        throw new Error((new StringBuilder()).append("unknown code:").append(c).toString());
    }

    public final int nextNodeIndex(int i, int j)
    {
        if ((0x80000000 | j) == -1)
        {
            j = data.length;
        }
_L6:
        if (i == gapStart)
        {
            i = gapEnd;
        }
        if (i < j) goto _L2; else goto _L1
_L1:
        return i;
_L2:
        char c;
        c = data[i];
        if (c <= '\u9FFF' || c >= '\uE000' && c <= '\uEFFF' || c >= '\uB000' && c <= '\uDFFF' || (0xff00 & c) == 61440)
        {
            i++;
            break; /* Loop/switch isn't completed */
        }
        if (c >= '\uA000' && c <= '\uAFFF') goto _L1; else goto _L3
_L3:
        switch (c)
        {
        case 61709: 
        case 61710: 
        case 61711: 
        case 61717: 
        default:
            i = nextDataIndex(i);
            break; /* Loop/switch isn't completed */

        case 61704: 
        case 61705: 
        case 61706: 
        case 61707: 
        case 61708: 
        case 61712: 
        case 61713: 
        case 61715: 
        case 61716: 
        case 61719: 
            break;

        case 61720: 
            i += 3;
            break; /* Loop/switch isn't completed */

        case 61718: 
            i++;
            break; /* Loop/switch isn't completed */

        case 61714: 
            i += 5;
            break; /* Loop/switch isn't completed */
        }
        if (true) goto _L1; else goto _L4
_L4:
        if (true) goto _L6; else goto _L5
_L5:
    }

    public int nextPos(int i)
    {
        int j = posToDataIndex(i);
        if (j == data.length)
        {
            return 0;
        }
        if (j >= gapEnd)
        {
            j -= gapEnd - gapStart;
        }
        return 3 + (j << 1);
    }

    public int parentOrEntityI(int i)
    {
        if (i != data.length) goto _L2; else goto _L1
_L1:
        return -1;
_L2:
        char c;
        c = data[i];
        if (c == '\uF110' || c == '\uF112')
        {
            int j = getIntN(i + 3);
            if (j >= -1)
            {
                return j;
            } else
            {
                return i + j;
            }
        }
        if (c < '\uA000' || c > '\uAFFF')
        {
            break; /* Loop/switch isn't completed */
        }
        char c1 = data[i + 2];
        if (c1 != 0)
        {
            return i - c1;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (c != '\uF108') goto _L5; else goto _L4
_L4:
        int l = getIntN(i + 1);
        int i1;
        int j1;
        int k1;
        if (l < 0)
        {
            i1 = data.length;
        } else
        {
            i1 = i;
        }
        j1 = l + i1;
        k1 = getIntN(j1 + 5);
        if (k1 == 0) goto _L1; else goto _L6
_L6:
        if (k1 < 0)
        {
            k1 += j1;
        }
        return k1;
_L9:
        i++;
_L5:
        if (i == gapStart)
        {
            i = gapEnd;
        }
        if (i == data.length) goto _L1; else goto _L7
_L7:
        data[i];
        JVM INSTR tableswitch 61706 61713: default 228
    //                   61706 151
    //                   61707 240
    //                   61708 251
    //                   61709 228
    //                   61710 228
    //                   61711 228
    //                   61712 228
    //                   61713 9;
           goto _L8 _L9 _L10 _L11 _L8 _L8 _L8 _L8 _L12
_L12:
        continue; /* Loop/switch isn't completed */
_L8:
        i = nextDataIndex(i);
        if (i < 0)
        {
            return -1;
        }
          goto _L5
_L10:
        return i - data[i + 1];
_L11:
        int k = getIntN(i + 3);
        if (k < 0)
        {
            k += i;
        }
        return k;
        if (true) goto _L1; else goto _L13
_L13:
    }

    public int parentOrEntityPos(int i)
    {
        int j = parentOrEntityI(posToDataIndex(i));
        if (j < 0)
        {
            return -1;
        } else
        {
            return j << 1;
        }
    }

    public int parentPos(int i)
    {
        int j = posToDataIndex(i);
        do
        {
            j = parentOrEntityI(j);
            if (j == -1)
            {
                return -1;
            }
        } while (data[j] == '\uF112');
        return j << 1;
    }

    public final int posToDataIndex(int i)
    {
        int j;
        if (i == -1)
        {
            j = data.length;
        } else
        {
            j = i >>> 1;
            if ((i & 1) != 0)
            {
                j--;
            }
            if (j >= gapStart)
            {
                j += gapEnd - gapStart;
            }
            if ((i & 1) != 0)
            {
                j = nextDataIndex(j);
                if (j < 0)
                {
                    return data.length;
                }
                if (j == gapStart)
                {
                    return j + (gapEnd - gapStart);
                }
            }
        }
        return j;
    }

    public final void resizeObjects()
    {
        Object aobj[];
        if (objects == null)
        {
            aobj = new Object[100];
        } else
        {
            int i = objects.length;
            aobj = new Object[i * 2];
            System.arraycopy(((Object) (objects)), 0, ((Object) (aobj)), 0, i);
        }
        objects = aobj;
    }

    public void setAttributeName(int i, int j)
    {
        setIntN(i + 1, j);
    }

    public void setElementName(int i, int j)
    {
        if (data[i] == '\uF108')
        {
            int k = getIntN(i + 1);
            if (k < 0)
            {
                i = data.length;
            }
            i += k;
        }
        if (i < gapEnd)
        {
            throw new Error("setElementName before gapEnd");
        } else
        {
            setIntN(i + 1, j);
            return;
        }
    }

    public final void setIntN(int i, int j)
    {
        data[i] = (char)(j >> 16);
        data[i + 1] = (char)j;
    }

    public int size()
    {
        int i = 0;
        int j = 0;
        do
        {
            j = nextPos(j);
            if (j == 0)
            {
                return i;
            }
            i++;
        } while (true);
    }

    public void startAttribute(int i)
    {
        ensureSpace(6);
        gapEnd = -1 + gapEnd;
        char ac[] = data;
        int j = gapStart;
        gapStart = j + 1;
        ac[j] = '\uF109';
        if (attrStart != 0)
        {
            throw new Error("nested attribute");
        } else
        {
            attrStart = gapStart;
            setIntN(gapStart, i);
            setIntN(2 + gapStart, gapEnd - data.length);
            gapStart = 4 + gapStart;
            data[gapEnd] = '\uF10A';
            return;
        }
    }

    public void startAttribute(Object obj)
    {
        startAttribute(find(obj));
    }

    public void startDocument()
    {
        int i = -1;
        ensureSpace(6);
        gapEnd = -1 + gapEnd;
        int j = gapStart;
        data[j] = '\uF110';
        if (docStart != 0)
        {
            throw new Error("nested document");
        }
        docStart = j + 1;
        setIntN(j + 1, gapEnd - data.length);
        int k = j + 3;
        if (currentParent != i)
        {
            i = currentParent - j;
        }
        setIntN(k, i);
        currentParent = j;
        gapStart = j + 5;
        currentParent = j;
        data[gapEnd] = '\uF111';
    }

    public void startElement(int i)
    {
        ensureSpace(10);
        gapEnd = -7 + gapEnd;
        char ac[] = data;
        int j = gapStart;
        gapStart = j + 1;
        ac[j] = '\uF108';
        setIntN(gapStart, gapEnd - data.length);
        gapStart = 2 + gapStart;
        data[gapEnd] = '\uF10C';
        setIntN(1 + gapEnd, i);
        setIntN(3 + gapEnd, -3 + gapStart);
        setIntN(5 + gapEnd, currentParent);
        currentParent = -3 + gapStart;
    }

    public void startElement(Object obj)
    {
        startElement(find(obj));
    }

    public void statistics()
    {
        PrintWriter printwriter = new PrintWriter(System.out);
        statistics(printwriter);
        printwriter.flush();
    }

    public void statistics(PrintWriter printwriter)
    {
        printwriter.print("data array length: ");
        printwriter.println(data.length);
        printwriter.print("data array gap: ");
        printwriter.println(gapEnd - gapStart);
        printwriter.print("object array length: ");
        printwriter.println(objects.length);
    }

    public int stringValue(int i, StringBuffer stringbuffer)
    {
        int j = nextNodeIndex(i, 0x7fffffff);
        if (j > i)
        {
            stringValue(i, j, stringbuffer);
            return i;
        } else
        {
            return stringValue(false, i, stringbuffer);
        }
    }

    public int stringValue(boolean flag, int i, StringBuffer stringbuffer)
    {
        int j;
        char c;
        int k;
        j = 0;
        if (i >= gapStart)
        {
            i += gapEnd - gapStart;
        }
        if (i == data.length)
        {
            return -1;
        }
        c = data[i];
        k = i + 1;
        if (c <= '\u9FFF')
        {
            stringbuffer.append(c);
            return k;
        }
        if (c < '\uE000' || c > '\uEFFF') goto _L2; else goto _L1
_L1:
        Object obj;
        if (false)
        {
            stringbuffer.append(' ');
        }
        obj = objects[c - 57344];
_L4:
        if (obj != null)
        {
            stringbuffer.append(obj);
        }
        if (j > 0)
        {
            do
            {
                j = stringValue(true, j, stringbuffer);
            } while (j >= 0);
        }
        return k;
_L2:
        if (c < '\uA000' || c > '\uAFFF')
        {
            break; /* Loop/switch isn't completed */
        }
        j = k + 2;
        k = 1 + (k + data[k]);
        obj = null;
        if (true) goto _L4; else goto _L3
_L3:
        if ((0xff00 & c) == 61440)
        {
            stringbuffer.append(c & 0xff);
            return k;
        }
        if (c >= '\uB000' && c <= '\uDFFF')
        {
            stringbuffer.append(c - 49152);
            return k;
        }
        switch (c)
        {
        case 61703: 
        case 61709: 
        case 61710: 
        default:
            throw new Error((new StringBuilder()).append("unimplemented: ").append(Integer.toHexString(c)).append(" at:").append(k).toString());

        case 61720: 
            return k + 2;

        case 61716: 
            k += 2;
            // fall through

        case 61717: 
        case 61719: 
            int l1 = getIntN(k);
            int i2 = k + 2;
            if (!flag || c == '\uF115')
            {
                stringbuffer.append(data, i2, l1);
            }
            return i2 + l1;

        case 61696: 
        case 61697: 
            if (false)
            {
                stringbuffer.append(' ');
            }
            boolean flag1;
            if (c != '\uF100')
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            stringbuffer.append(flag1);
            return k;

        case 61698: 
            if (false)
            {
                stringbuffer.append(' ');
            }
            stringbuffer.append(getIntN(k));
            return k + 2;

        case 61699: 
            if (false)
            {
                stringbuffer.append(' ');
            }
            stringbuffer.append(getLongN(k));
            return k + 4;

        case 61700: 
            if (false)
            {
                stringbuffer.append(' ');
            }
            stringbuffer.append(Float.intBitsToFloat(getIntN(k)));
            return k + 2;

        case 61701: 
            if (false)
            {
                stringbuffer.append(' ');
            }
            stringbuffer.append(Double.longBitsToDouble(getLongN(k)));
            return k + 4;

        case 61702: 
            stringbuffer.append(data[k]);
            return k + 1;

        case 61712: 
        case 61714: 
            j = k + 4;
            k = nextDataIndex(k - 1);
            obj = null;
            break;

        case 61704: 
            j = k + 2;
            int j1 = getIntN(k);
            int k1;
            if (j1 < 0)
            {
                k1 = data.length;
            } else
            {
                k1 = k - 1;
            }
            k = 7 + (j1 + k1);
            obj = null;
            break;

        case 61718: 
            j = 0;
            obj = null;
            break;

        case 61706: 
        case 61707: 
        case 61708: 
        case 61713: 
        case 61715: 
            return -1;

        case 61705: 
            j = 0;
            if (!flag)
            {
                j = k + 4;
            }
            int i1 = getIntN(k + 2);
            if (i1 < 0)
            {
                k = 1 + data.length;
            }
            k += i1;
            obj = null;
            break;

        case 61711: 
            AbstractSequence abstractsequence = (AbstractSequence)objects[getIntN(k)];
            int l = getIntN(k + 2);
            ((TreeList)abstractsequence).stringValue(flag, l >> 1, stringbuffer);
            k += 4;
            j = 0;
            obj = null;
            break;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public void stringValue(int i, int j, StringBuffer stringbuffer)
    {
        for (int k = i; k < j && k >= 0; k = stringValue(false, k, stringbuffer)) { }
    }

    public void toString(String s, StringBuffer stringbuffer)
    {
        int i;
        int j;
        boolean flag;
        boolean flag1;
        i = 0;
        j = gapStart;
        flag = false;
        flag1 = false;
_L3:
        int k;
        char c;
label0:
        {
            if (i < j)
            {
                break label0;
            }
            if (i == gapStart)
            {
                i = gapEnd;
                j = data.length;
                if (i != j)
                {
                    break label0;
                }
            }
            return;
        }
        char ac[] = data;
        k = i + 1;
        c = ac[i];
        if (c > '\u9FFF') goto _L2; else goto _L1
_L1:
        int j4;
        int k4;
        j4 = k - 1;
        k4 = j;
_L4:
        int l4;
        if (k >= k4)
        {
            i = k;
        } else
        {
            char ac2[] = data;
            l4 = k + 1;
            if (ac2[k] <= '\u9FFF')
            {
                break MISSING_BLOCK_LABEL_1497;
            }
            i = l4 - 1;
        }
        if (flag1)
        {
            stringbuffer.append('>');
            flag1 = false;
        }
        stringbuffer.append(data, j4, i - j4);
        flag = false;
          goto _L3
_L2:
        if (c >= '\uE000' && c <= '\uEFFF')
        {
            if (flag1)
            {
                stringbuffer.append('>');
                flag1 = false;
            }
            if (flag)
            {
                stringbuffer.append(s);
            } else
            {
                flag = true;
            }
            stringbuffer.append(objects[c - 57344]);
            i = k;
        } else
        if (c >= '\uA000' && c <= '\uAFFF')
        {
            if (flag1)
            {
                stringbuffer.append('>');
            }
            int i4 = c - 40960;
            if (flag)
            {
                stringbuffer.append(s);
            }
            stringbuffer.append('<');
            stringbuffer.append(objects[i4].toString());
            i = k + 2;
            flag1 = true;
            flag = false;
        } else
        if (c >= '\uB000' && c <= '\uDFFF')
        {
            if (flag1)
            {
                stringbuffer.append('>');
                flag1 = false;
            }
            if (flag)
            {
                stringbuffer.append(s);
            } else
            {
                flag = true;
            }
            stringbuffer.append(c - 49152);
            i = k;
        } else
        {
            switch (c)
            {
            case 61703: 
            default:
                throw new Error((new StringBuilder()).append("unknown code:").append(c).toString());

            case 61712: 
            case 61714: 
                i = k + 4;
                break;

            case 61720: 
                i = k + 2;
                break;

            case 61719: 
                if (flag1)
                {
                    stringbuffer.append('>');
                    flag1 = false;
                }
                int k3 = getIntN(k);
                int l3 = k + 2;
                stringbuffer.append("<!--");
                stringbuffer.append(data, l3, k3);
                stringbuffer.append("-->");
                i = l3 + k3;
                break;

            case 61717: 
                if (flag1)
                {
                    stringbuffer.append('>');
                    flag1 = false;
                }
                int i3 = getIntN(k);
                int j3 = k + 2;
                stringbuffer.append("<![CDATA[");
                stringbuffer.append(data, j3, i3);
                stringbuffer.append("]]>");
                i = j3 + i3;
                break;

            case 61716: 
                if (flag1)
                {
                    stringbuffer.append('>');
                    flag1 = false;
                }
                stringbuffer.append("<?");
                int j2 = getIntN(k);
                int k2 = k + 2;
                stringbuffer.append(objects[j2]);
                int l2 = getIntN(k2);
                i = k2 + 2;
                if (l2 > 0)
                {
                    stringbuffer.append(' ');
                    stringbuffer.append(data, i, l2);
                    i += l2;
                }
                stringbuffer.append("?>");
                break;

            case 61713: 
            case 61715: 
                i = k;
                break;

            case 61696: 
            case 61697: 
                if (flag1)
                {
                    stringbuffer.append('>');
                    flag1 = false;
                }
                boolean flag2;
                if (flag)
                {
                    stringbuffer.append(s);
                } else
                {
                    flag = true;
                }
                if (c != '\uF100')
                {
                    flag2 = true;
                } else
                {
                    flag2 = false;
                }
                stringbuffer.append(flag2);
                i = k;
                break;

            case 61718: 
                i = k;
                break;

            case 61702: 
                if (flag1)
                {
                    stringbuffer.append('>');
                    flag1 = false;
                }
                stringbuffer.append(data, k, (c + 1) - 61702);
                i = k + 1;
                flag = false;
                break;

            case 61711: 
                if (flag1)
                {
                    stringbuffer.append('>');
                    flag1 = false;
                }
                if (flag)
                {
                    stringbuffer.append(s);
                } else
                {
                    flag = true;
                }
                stringbuffer.append(((AbstractSequence)objects[getIntN(k)]).getIteratorAtPos(getIntN(k + 2)));
                i = k + 4;
                break;

            case 61709: 
            case 61710: 
                if (flag1)
                {
                    stringbuffer.append('>');
                    flag1 = false;
                }
                if (flag)
                {
                    stringbuffer.append(s);
                } else
                {
                    flag = true;
                }
                stringbuffer.append(objects[getIntN(k)]);
                i = k + 2;
                break;

            case 61704: 
                int j1 = getIntN(k);
                int k1;
                int l1;
                int i2;
                if (j1 >= 0)
                {
                    k1 = k - 1;
                } else
                {
                    k1 = data.length;
                }
                l1 = j1 + k1;
                i = k + 2;
                i2 = getIntN(l1 + 1);
                if (flag1)
                {
                    stringbuffer.append('>');
                } else
                if (flag)
                {
                    stringbuffer.append(s);
                }
                stringbuffer.append('<');
                stringbuffer.append(objects[i2]);
                flag1 = true;
                flag = false;
                break;

            case 61707: 
            case 61708: 
                int i1;
                if (c == '\uF10B')
                {
                    char ac1[] = data;
                    i = k + 1;
                    char c1 = ac1[k];
                    i1 = data[i - 2 - c1] - 40960;
                } else
                {
                    i1 = getIntN(k);
                    i = k + 6;
                }
                if (flag1)
                {
                    stringbuffer.append("/>");
                } else
                {
                    stringbuffer.append("</");
                    stringbuffer.append(objects[i1]);
                    stringbuffer.append('>');
                }
                flag = true;
                flag1 = false;
                break;

            case 61705: 
                int l = getIntN(k);
                stringbuffer.append(' ');
                stringbuffer.append(objects[l]);
                stringbuffer.append("=\"");
                i = k + 4;
                flag1 = false;
                break;

            case 61706: 
                stringbuffer.append('"');
                flag1 = true;
                i = k;
                flag = false;
                break;

            case 61698: 
                if (flag1)
                {
                    stringbuffer.append('>');
                    flag1 = false;
                }
                if (flag)
                {
                    stringbuffer.append(s);
                } else
                {
                    flag = true;
                }
                stringbuffer.append(getIntN(k));
                i = k + 2;
                break;

            case 61700: 
                if (flag1)
                {
                    stringbuffer.append('>');
                    flag1 = false;
                }
                if (flag)
                {
                    stringbuffer.append(s);
                } else
                {
                    flag = true;
                }
                stringbuffer.append(Float.intBitsToFloat(getIntN(k)));
                i = k + 2;
                break;

            case 61699: 
                if (flag1)
                {
                    stringbuffer.append('>');
                    flag1 = false;
                }
                if (flag)
                {
                    stringbuffer.append(s);
                } else
                {
                    flag = true;
                }
                stringbuffer.append(getLongN(k));
                i = k + 4;
                break;

            case 61701: 
                if (flag1)
                {
                    stringbuffer.append('>');
                    flag1 = false;
                }
                if (flag)
                {
                    stringbuffer.append(s);
                } else
                {
                    flag = true;
                }
                stringbuffer.append(Double.longBitsToDouble(getLongN(k)));
                i = k + 4;
                break;
            }
            continue; /* Loop/switch isn't completed */
        }
          goto _L3
        k = l4;
          goto _L4
        if (true) goto _L3; else goto _L5
_L5:
    }

    public void write(int i)
    {
        ensureSpace(3);
        if (i <= 40959)
        {
            char ac2[] = data;
            int l = gapStart;
            gapStart = l + 1;
            ac2[l] = (char)i;
            return;
        }
        if (i < 0x10000)
        {
            char ac[] = data;
            int j = gapStart;
            gapStart = j + 1;
            ac[j] = '\uF106';
            char ac1[] = data;
            int k = gapStart;
            gapStart = k + 1;
            ac1[k] = (char)i;
            return;
        } else
        {
            Char.print(i, this);
            return;
        }
    }

    public void write(CharSequence charsequence, int i, int j)
    {
        if (j == 0)
        {
            writeJoiner();
        }
        ensureSpace(j);
        int k = i;
        while (j > 0) 
        {
            int l = k + 1;
            char c = charsequence.charAt(k);
            j--;
            if (c <= '\u9FFF')
            {
                char ac[] = data;
                int i1 = gapStart;
                gapStart = i1 + 1;
                ac[i1] = c;
            } else
            {
                write(c);
                ensureSpace(j);
            }
            k = l;
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
        }
        ensureSpace(j);
        int k = i;
        while (j > 0) 
        {
            int l = k + 1;
            char c = ac[k];
            j--;
            if (c <= '\u9FFF')
            {
                char ac1[] = data;
                int i1 = gapStart;
                gapStart = i1 + 1;
                ac1[i1] = c;
            } else
            {
                write(c);
                ensureSpace(j);
            }
            k = l;
        }
    }

    public void writeBoolean(boolean flag)
    {
        ensureSpace(1);
        char ac[] = data;
        int i = gapStart;
        gapStart = i + 1;
        int j;
        if (flag)
        {
            j = 61697;
        } else
        {
            j = 61696;
        }
        ac[i] = j;
    }

    public void writeByte(int i)
    {
        ensureSpace(1);
        char ac[] = data;
        int j = gapStart;
        gapStart = j + 1;
        ac[j] = (char)(61440 + (i & 0xff));
    }

    public void writeCDATA(char ac[], int i, int j)
    {
        ensureSpace(j + 3);
        int k = gapStart;
        char ac1[] = data;
        int l = k + 1;
        ac1[k] = '\uF115';
        setIntN(l, j);
        int i1 = l + 2;
        System.arraycopy(ac, i, data, i1, j);
        gapStart = i1 + j;
    }

    public void writeComment(String s, int i, int j)
    {
        ensureSpace(j + 3);
        int k = gapStart;
        char ac[] = data;
        int l = k + 1;
        ac[k] = '\uF117';
        setIntN(l, j);
        int i1 = l + 2;
        s.getChars(i, i + j, data, i1);
        gapStart = i1 + j;
    }

    public void writeComment(char ac[], int i, int j)
    {
        ensureSpace(j + 3);
        int k = gapStart;
        char ac1[] = data;
        int l = k + 1;
        ac1[k] = '\uF117';
        setIntN(l, j);
        int i1 = l + 2;
        System.arraycopy(ac, i, data, i1, j);
        gapStart = i1 + j;
    }

    public void writeDocumentUri(Object obj)
    {
        ensureSpace(3);
        int i = find(obj);
        char ac[] = data;
        int j = gapStart;
        gapStart = j + 1;
        ac[j] = '\uF118';
        setIntN(gapStart, i);
        gapStart = 2 + gapStart;
    }

    public void writeDouble(double d)
    {
        ensureSpace(5);
        long l = Double.doubleToLongBits(d);
        char ac[] = data;
        int i = gapStart;
        gapStart = i + 1;
        ac[i] = '\uF105';
        char ac1[] = data;
        int j = gapStart;
        gapStart = j + 1;
        ac1[j] = (char)(int)(l >>> 48);
        char ac2[] = data;
        int k = gapStart;
        gapStart = k + 1;
        ac2[k] = (char)(int)(l >>> 32);
        char ac3[] = data;
        int i1 = gapStart;
        gapStart = i1 + 1;
        ac3[i1] = (char)(int)(l >>> 16);
        char ac4[] = data;
        int j1 = gapStart;
        gapStart = j1 + 1;
        ac4[j1] = (char)(int)l;
    }

    public void writeFloat(float f)
    {
        ensureSpace(3);
        int i = Float.floatToIntBits(f);
        char ac[] = data;
        int j = gapStart;
        gapStart = j + 1;
        ac[j] = '\uF104';
        char ac1[] = data;
        int k = gapStart;
        gapStart = k + 1;
        ac1[k] = (char)(i >>> 16);
        char ac2[] = data;
        int l = gapStart;
        gapStart = l + 1;
        ac2[l] = (char)i;
    }

    public void writeInt(int i)
    {
        ensureSpace(3);
        if (i >= -4096 && i <= 8191)
        {
            char ac1[] = data;
            int k = gapStart;
            gapStart = k + 1;
            ac1[k] = (char)(49152 + i);
            return;
        } else
        {
            char ac[] = data;
            int j = gapStart;
            gapStart = j + 1;
            ac[j] = '\uF102';
            setIntN(gapStart, i);
            gapStart = 2 + gapStart;
            return;
        }
    }

    public void writeJoiner()
    {
        ensureSpace(1);
        char ac[] = data;
        int i = gapStart;
        gapStart = i + 1;
        ac[i] = '\uF116';
    }

    public void writeLong(long l)
    {
        ensureSpace(5);
        char ac[] = data;
        int i = gapStart;
        gapStart = i + 1;
        ac[i] = '\uF103';
        char ac1[] = data;
        int j = gapStart;
        gapStart = j + 1;
        ac1[j] = (char)(int)(l >>> 48);
        char ac2[] = data;
        int k = gapStart;
        gapStart = k + 1;
        ac2[k] = (char)(int)(l >>> 32);
        char ac3[] = data;
        int i1 = gapStart;
        gapStart = i1 + 1;
        ac3[i1] = (char)(int)(l >>> 16);
        char ac4[] = data;
        int j1 = gapStart;
        gapStart = j1 + 1;
        ac4[j1] = (char)(int)l;
    }

    public void writeObject(Object obj)
    {
        ensureSpace(3);
        int i = find(obj);
        if (i < 4096)
        {
            char ac1[] = data;
            int k = gapStart;
            gapStart = k + 1;
            ac1[k] = (char)(0xe000 | i);
            return;
        } else
        {
            char ac[] = data;
            int j = gapStart;
            gapStart = j + 1;
            ac[j] = '\uF10D';
            setIntN(gapStart, i);
            gapStart = 2 + gapStart;
            return;
        }
    }

    public void writePosition(AbstractSequence abstractsequence, int i)
    {
        ensureSpace(5);
        data[gapStart] = '\uF10F';
        int j = find(abstractsequence);
        setIntN(1 + gapStart, j);
        setIntN(3 + gapStart, i);
        gapStart = 5 + gapStart;
    }

    public void writeProcessingInstruction(String s, String s1, int i, int j)
    {
        ensureSpace(j + 5);
        int k = gapStart;
        char ac[] = data;
        int l = k + 1;
        ac[k] = '\uF114';
        setIntN(l, find(s));
        setIntN(l + 2, j);
        int i1 = l + 4;
        s1.getChars(i, i + j, data, i1);
        gapStart = i1 + j;
    }

    public void writeProcessingInstruction(String s, char ac[], int i, int j)
    {
        ensureSpace(j + 5);
        int k = gapStart;
        char ac1[] = data;
        int l = k + 1;
        ac1[k] = '\uF114';
        setIntN(l, find(s));
        setIntN(l + 2, j);
        int i1 = l + 4;
        System.arraycopy(ac, i, data, i1, j);
        gapStart = i1 + j;
    }
}
