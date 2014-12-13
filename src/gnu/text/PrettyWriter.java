// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import gnu.lists.LList;
import gnu.mapping.ThreadLocation;
import java.io.IOException;
import java.io.Writer;

public class PrettyWriter extends Writer
{

    private static final int BLOCK_PER_LINE_PREFIX_END = -3;
    private static final int BLOCK_PREFIX_LENGTH = -4;
    private static final int BLOCK_SECTION_COLUMN = -2;
    private static final int BLOCK_SECTION_START_LINE = -6;
    private static final int BLOCK_START_COLUMN = -1;
    private static final int BLOCK_SUFFIX_LENGTH = -5;
    private static final int LOGICAL_BLOCK_LENGTH = 6;
    public static final int NEWLINE_FILL = 70;
    public static final int NEWLINE_LINEAR = 78;
    public static final int NEWLINE_LITERAL = 76;
    public static final int NEWLINE_MANDATORY = 82;
    public static final int NEWLINE_MISER = 77;
    public static final int NEWLINE_SPACE = 83;
    static final int QITEM_BASE_SIZE = 2;
    static final int QITEM_BLOCK_END_SIZE = 2;
    static final int QITEM_BLOCK_END_TYPE = 5;
    static final int QITEM_BLOCK_START_BLOCK_END = 4;
    static final int QITEM_BLOCK_START_PREFIX = 5;
    static final int QITEM_BLOCK_START_SIZE = 7;
    static final int QITEM_BLOCK_START_SUFFIX = 6;
    static final int QITEM_BLOCK_START_TYPE = 4;
    static final int QITEM_INDENTATION_AMOUNT = 3;
    static final char QITEM_INDENTATION_BLOCK = 66;
    static final char QITEM_INDENTATION_CURRENT = 67;
    static final int QITEM_INDENTATION_KIND = 2;
    static final int QITEM_INDENTATION_SIZE = 4;
    static final int QITEM_INDENTATION_TYPE = 3;
    static final int QITEM_NEWLINE_KIND = 4;
    static final int QITEM_NEWLINE_SIZE = 5;
    static final int QITEM_NEWLINE_TYPE = 2;
    static final int QITEM_NOP_TYPE = 0;
    static final int QITEM_POSN = 1;
    static final int QITEM_SECTION_START_DEPTH = 2;
    static final int QITEM_SECTION_START_SECTION_END = 3;
    static final int QITEM_SECTION_START_SIZE = 4;
    static final int QITEM_TAB_COLINC = 4;
    static final int QITEM_TAB_COLNUM = 3;
    static final int QITEM_TAB_FLAGS = 2;
    static final int QITEM_TAB_IS_RELATIVE = 2;
    static final int QITEM_TAB_IS_SECTION = 1;
    static final int QITEM_TAB_SIZE = 5;
    static final int QITEM_TAB_TYPE = 6;
    static final int QITEM_TYPE_AND_SIZE = 0;
    static final int QUEUE_INIT_ALLOC_SIZE = 300;
    public static ThreadLocation indentLoc = new ThreadLocation("indent");
    public static int initialBufferSize = 126;
    public static ThreadLocation lineLengthLoc = new ThreadLocation("line-length");
    public static ThreadLocation miserWidthLoc = new ThreadLocation("miser-width");
    int blockDepth;
    int blocks[];
    public char buffer[];
    public int bufferFillPointer;
    int bufferOffset;
    int bufferStartColumn;
    int currentBlock;
    int lineLength;
    int lineNumber;
    int miserWidth;
    protected Writer out;
    public int pendingBlocksCount;
    char prefix[];
    int prettyPrintingMode;
    int queueInts[];
    int queueSize;
    String queueStrings[];
    int queueTail;
    char suffix[];
    boolean wordEndSeen;

    public PrettyWriter(Writer writer)
    {
        lineLength = 80;
        miserWidth = 40;
        buffer = new char[initialBufferSize];
        blocks = new int[60];
        blockDepth = 6;
        prefix = new char[initialBufferSize];
        suffix = new char[initialBufferSize];
        queueInts = new int[300];
        queueStrings = new String[300];
        currentBlock = -1;
        out = writer;
        prettyPrintingMode = 1;
    }

    public PrettyWriter(Writer writer, int i)
    {
        int j = 1;
        super();
        lineLength = 80;
        miserWidth = 40;
        buffer = new char[initialBufferSize];
        blocks = new int[60];
        blockDepth = 6;
        prefix = new char[initialBufferSize];
        suffix = new char[initialBufferSize];
        queueInts = new int[300];
        queueStrings = new String[300];
        currentBlock = -1;
        out = writer;
        lineLength = i;
        if (i <= j)
        {
            j = 0;
        }
        prettyPrintingMode = j;
    }

    public PrettyWriter(Writer writer, boolean flag)
    {
        lineLength = 80;
        miserWidth = 40;
        buffer = new char[initialBufferSize];
        blocks = new int[60];
        blockDepth = 6;
        prefix = new char[initialBufferSize];
        suffix = new char[initialBufferSize];
        queueInts = new int[300];
        queueStrings = new String[300];
        currentBlock = -1;
        out = writer;
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        prettyPrintingMode = i;
    }

    private static int enoughSpace(int i, int j)
    {
        int k = i * 2;
        int l = i + (j * 5 >> 2);
        if (k > l)
        {
            return k;
        } else
        {
            return l;
        }
    }

    private int getPerLinePrefixEnd()
    {
        return blocks[-3 + blockDepth];
    }

    private int getPrefixLength()
    {
        return blocks[-4 + blockDepth];
    }

    private int getQueueSize(int i)
    {
        return queueInts[i] >> 16;
    }

    private int getQueueType(int i)
    {
        return 0xff & queueInts[i];
    }

    private int getSectionColumn()
    {
        return blocks[-2 + blockDepth];
    }

    private int getSectionStartLine()
    {
        return blocks[-6 + blockDepth];
    }

    private int getStartColumn()
    {
        return blocks[-1 + blockDepth];
    }

    private int getSuffixLength()
    {
        return blocks[-5 + blockDepth];
    }

    private int indexPosn(int i)
    {
        return i + bufferOffset;
    }

    private int posnColumn(int i)
    {
        return indexColumn(posnIndex(i));
    }

    private int posnIndex(int i)
    {
        return i - bufferOffset;
    }

    private void pushLogicalBlock(int i, int j, int k, int l, int i1)
    {
        int j1 = 6 + blockDepth;
        if (j1 >= blocks.length)
        {
            int ai[] = new int[2 * blocks.length];
            System.arraycopy(blocks, 0, ai, 0, blockDepth);
            blocks = ai;
        }
        blockDepth = j1;
        blocks[-1 + blockDepth] = i;
        blocks[-2 + blockDepth] = i;
        blocks[-3 + blockDepth] = j;
        blocks[-4 + blockDepth] = k;
        blocks[-5 + blockDepth] = l;
        blocks[-6 + blockDepth] = i1;
    }

    public void addIndentation(int i, boolean flag)
    {
        if (prettyPrintingMode > 0)
        {
            char c;
            if (flag)
            {
                c = 'C';
            } else
            {
                c = 'B';
            }
            enqueueIndent(c, i);
        }
    }

    public void clearBuffer()
    {
        bufferStartColumn = 0;
        bufferFillPointer = 0;
        lineNumber = 0;
        bufferOffset = 0;
        blockDepth = 6;
        queueTail = 0;
        queueSize = 0;
        pendingBlocksCount = 0;
    }

    public void clearWordEnd()
    {
        wordEndSeen = false;
    }

    public void close()
        throws IOException
    {
        if (out != null)
        {
            forcePrettyOutput();
            out.close();
            out = null;
        }
        buffer = null;
    }

    public void closeThis()
        throws IOException
    {
        if (out != null)
        {
            forcePrettyOutput();
            out = null;
        }
        buffer = null;
    }

    int computeTabSize(int i, int j, int k)
    {
        int l = queueInts[i + 2];
        boolean flag;
        boolean flag1;
        int i1;
        int j1;
        int k1;
        if ((l & 1) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if ((l & 2) != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        i1 = 0;
        if (flag)
        {
            i1 = j;
        }
        j1 = queueInts[i + 3];
        k1 = queueInts[i + 4];
        if (flag1)
        {
            if (k1 > 1)
            {
                int l1 = (k + j1) % k1;
                if (l1 != 0)
                {
                    j1 += l1;
                }
            }
            return j1;
        }
        if (k <= j1 + i1)
        {
            return (k + i1) - k;
        } else
        {
            return k1 - (k - i1) % k1;
        }
    }

    public void endLogicalBlock()
    {
        int i = enqueue(5, 2);
        pendingBlocksCount = -1 + pendingBlocksCount;
        if (currentBlock < 0)
        {
            int k1 = blocks[-5 + blockDepth];
            int l1 = blocks[-5 + (-6 + blockDepth)];
            if (k1 > l1)
            {
                write(suffix, suffix.length - k1, k1 - l1);
            }
            currentBlock = -1;
            return;
        }
        int j = currentBlock;
        int k = queueInts[j + 4];
        String s;
        int j1;
        if (k == 0)
        {
            currentBlock = -1;
        } else
        {
            int l = queueTail - j;
            if (l > 0)
            {
                l -= queueInts.length;
            }
            if (k < l)
            {
                currentBlock = -1;
            } else
            {
                int i1 = k + j;
                if (i1 < 0)
                {
                    i1 += queueInts.length;
                }
                currentBlock = i1;
            }
        }
        s = queueStrings[j + 6];
        if (s != null)
        {
            write(s);
        }
        j1 = i - j;
        if (j1 < 0)
        {
            j1 += queueInts.length;
        }
        queueInts[j + 4] = j1;
    }

    public void endLogicalBlock(String s)
    {
        if (prettyPrintingMode > 0)
        {
            endLogicalBlock();
        } else
        if (s != null)
        {
            write(s);
            return;
        }
    }

    public int enqueue(int i, int j)
    {
        int k = queueInts.length;
        int l = k - queueTail - queueSize;
        if (l > 0 && j > l)
        {
            enqueue(0, l);
        }
        if (j + queueSize > k)
        {
            int j1 = enoughSpace(k, j);
            int ai[] = new int[j1];
            String as[] = new String[j1];
            int k1 = (queueTail + queueSize) - k;
            if (k1 > 0)
            {
                System.arraycopy(queueInts, 0, ai, 0, k1);
                System.arraycopy(queueStrings, 0, as, 0, k1);
            }
            int l1 = k - queueTail;
            int i2 = j1 - k;
            System.arraycopy(queueInts, queueTail, ai, i2 + queueTail, l1);
            System.arraycopy(queueStrings, queueTail, as, i2 + queueTail, l1);
            queueInts = ai;
            queueStrings = as;
            if (currentBlock >= queueTail)
            {
                currentBlock = i2 + currentBlock;
            }
            queueTail = i2 + queueTail;
        }
        int i1 = queueTail + queueSize;
        if (i1 >= queueInts.length)
        {
            i1 -= queueInts.length;
        }
        queueInts[i1 + 0] = i | j << 16;
        if (j > 1)
        {
            queueInts[i1 + 1] = indexPosn(bufferFillPointer);
        }
        queueSize = j + queueSize;
        return i1;
    }

    public int enqueueIndent(char c, int i)
    {
        int j = enqueue(3, 4);
        queueInts[j + 2] = c;
        queueInts[j + 3] = i;
        return j;
    }

    public void enqueueNewline(int i)
    {
        wordEndSeen = false;
        int j = pendingBlocksCount;
        int k = enqueue(2, 5);
        queueInts[k + 4] = i;
        queueInts[k + 2] = pendingBlocksCount;
        queueInts[k + 3] = 0;
        int l = queueTail;
        int i1 = queueSize;
        do
        {
label0:
            {
                if (i1 > 0)
                {
                    if (l == queueInts.length)
                    {
                        l = 0;
                    }
                    if (l != k)
                    {
                        break label0;
                    }
                }
                boolean flag;
                int j1;
                int k1;
                int l1;
                if (i == 76 || i == 82)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                maybeOutput(flag, false);
                return;
            }
            j1 = getQueueType(l);
            if ((j1 == 2 || j1 == 4) && queueInts[l + 3] == 0 && j <= queueInts[l + 2])
            {
                l1 = k - l;
                if (l1 < 0)
                {
                    l1 += queueInts.length;
                }
                queueInts[l + 3] = l1;
            }
            k1 = getQueueSize(l);
            i1 -= k1;
            l += k1;
        } while (true);
    }

    int enqueueTab(int i, int j, int k)
    {
        int l = enqueue(6, 5);
        queueInts[l + 2] = i;
        queueInts[l + 3] = j;
        queueInts[l + 4] = k;
        return l;
    }

    int ensureSpaceInBuffer(int i)
    {
        char ac[] = buffer;
        int j = ac.length;
        int k = bufferFillPointer;
        int l = j - k;
        if (l > 0)
        {
            return l;
        }
        if (prettyPrintingMode > 0 && k > lineLength)
        {
            if (!maybeOutput(false, false))
            {
                outputPartialLine();
            }
            return ensureSpaceInBuffer(i);
        }
        int i1 = enoughSpace(j, i);
        char ac1[] = new char[i1];
        buffer = ac1;
        for (int j1 = k; --j1 >= 0;)
        {
            ac1[j1] = ac[j1];
        }

        return i1 - k;
    }

    void expandTabs(int i)
    {
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        j = 0;
        k = 0;
        l = bufferStartColumn;
        i1 = getSectionColumn();
        j1 = queueTail;
        k1 = queueSize;
        l1 = 6 * pendingBlocksCount;
_L12:
        if (k1 <= 0) goto _L2; else goto _L1
_L1:
        int k4 = queueInts.length;
        if (j1 == k4)
        {
            j1 = 0;
        }
        if (j1 != i) goto _L3; else goto _L2
_L2:
        char ac[];
        char ac1[];
        int l2;
        int i3;
        if (j <= 0)
        {
            break MISSING_BLOCK_LABEL_463;
        }
        int i2 = bufferFillPointer;
        int j2 = i2 + k;
        ac = buffer;
        ac1 = ac;
        int k2 = ac.length;
        l2 = i2;
        if (j2 > k2)
        {
            ac1 = new char[enoughSpace(i2, k)];
            buffer = ac1;
        }
        bufferFillPointer = j2;
        bufferOffset = bufferOffset - k;
        i3 = j;
_L10:
        if (--i3 < 0) goto _L5; else goto _L4
_L4:
        int j3;
        int k3;
        j3 = blocks[l1 + i3 * 2];
        k3 = blocks[1 + (l1 + i3 * 2)];
        int l3 = j3 + k;
        int i4 = l2 - j3;
        System.arraycopy(ac, j3, ac1, l3, i4);
        for (int j4 = l3 - k3; j4 < l3; j4++)
        {
            ac1[j4] = ' ';
        }

          goto _L6
_L3:
        if (getQueueType(j1) != 6) goto _L8; else goto _L7
_L7:
        int i5 = posnIndex(queueInts[j1 + 1]);
        int j5 = l + i5;
        int k5 = computeTabSize(j1, i1, j5);
        if (k5 != 0)
        {
            if (1 + (l1 + j * 2) >= blocks.length)
            {
                int ai[] = new int[2 * blocks.length];
                System.arraycopy(blocks, 0, ai, 0, blocks.length);
                blocks = ai;
            }
            blocks[l1 + j * 2] = i5;
            blocks[1 + (l1 + j * 2)] = k5;
            j++;
            k += k5;
            l += k5;
        }
_L9:
        int l4 = getQueueSize(j1);
        k1 -= l4;
        j1 += l4;
        continue; /* Loop/switch isn't completed */
_L8:
        if (j1 == 2 || j1 == 4)
        {
            i1 = l + posnIndex(queueInts[j1 + 1]);
        }
        if (true) goto _L9; else goto _L6
_L6:
        k -= k3;
        l2 = j3;
          goto _L10
_L5:
        if (ac1 != ac)
        {
            System.arraycopy(ac, 0, ac1, 0, l2);
        }
        return;
        if (true) goto _L12; else goto _L11
_L11:
    }

    int fitsOnLine(int i, boolean flag)
    {
        byte byte0 = -1;
        int j = lineLength;
        if (!printReadably() && getMaxLines() == lineNumber)
        {
            j = j - 3 - getSuffixLength();
        }
        if (i >= 0)
        {
            if (posnColumn(queueInts[i + 1]) <= j)
            {
                byte0 = 1;
            }
        } else
        if (!flag && indexColumn(bufferFillPointer) <= j)
        {
            return 0;
        }
        return byte0;
    }

    public void flush()
    {
        if (out == null)
        {
            return;
        }
        try
        {
            forcePrettyOutput();
            out.flush();
            return;
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException(ioexception.toString());
        }
    }

    public void forcePrettyOutput()
        throws IOException
    {
        maybeOutput(false, true);
        if (bufferFillPointer > 0)
        {
            outputPartialLine();
        }
        expandTabs(-1);
        bufferStartColumn = getColumnNumber();
        out.write(buffer, 0, bufferFillPointer);
        bufferFillPointer = 0;
        queueSize = 0;
    }

    public int getColumnNumber()
    {
        int i = bufferFillPointer;
        char c;
        do
        {
            if (--i < 0)
            {
                return bufferStartColumn + bufferFillPointer;
            }
            c = buffer[i];
        } while (c != '\n' && c != '\r');
        return bufferFillPointer - (i + 1);
    }

    int getMaxLines()
    {
        return -1;
    }

    protected int getMiserWidth()
    {
        return miserWidth;
    }

    public int getPrettyPrintingMode()
    {
        return prettyPrintingMode;
    }

    int indexColumn(int i)
    {
        int j;
        int k;
        int l;
        int i1;
        int j1;
        j = bufferStartColumn;
        k = getSectionColumn();
        l = indexPosn(i);
        i1 = queueTail;
        j1 = queueSize;
_L2:
        int k1;
label0:
        {
            int i2;
label1:
            {
                if (j1 > 0)
                {
                    if (i1 >= queueInts.length)
                    {
                        i1 = 0;
                    }
                    k1 = getQueueType(i1);
                    if (k1 == 0)
                    {
                        break label0;
                    }
                    i2 = queueInts[i1 + 1];
                    if (i2 < l)
                    {
                        break label1;
                    }
                }
                return j + i;
            }
            if (k1 != 6)
            {
                break; /* Loop/switch isn't completed */
            }
            j += computeTabSize(i1, k, j + posnIndex(i2));
        }
_L4:
        int l1 = getQueueSize(i1);
        j1 -= l1;
        i1 += l1;
        if (true) goto _L2; else goto _L1
_L1:
        if (k1 != 2 && k1 != 4) goto _L4; else goto _L3
_L3:
        k = j + posnIndex(queueInts[i1 + 1]);
          goto _L4
    }

    boolean isMisering()
    {
        int i = getMiserWidth();
        return i > 0 && lineLength - getStartColumn() <= i;
    }

    public boolean isPrettyPrinting()
    {
        return prettyPrintingMode > 0;
    }

    public void lineAbbreviationHappened()
    {
    }

    boolean maybeOutput(boolean flag, boolean flag1)
    {
        boolean flag2 = false;
_L8:
        int i;
        if (queueSize <= 0)
        {
            break MISSING_BLOCK_LABEL_584;
        }
        if (queueTail >= queueInts.length)
        {
            queueTail = 0;
        }
        i = queueTail;
        getQueueType(i);
        JVM INSTR tableswitch 2 6: default 72
    //                   2 105
    //                   3 320
    //                   4 395
    //                   5 568
    //                   6 575;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L14:
        int j = getQueueSize(queueTail);
        queueSize = queueSize - j;
        queueTail = i + j;
        if (true) goto _L8; else goto _L7
_L7:
        int k2 = -1;
        queueInts[i + 4];
        JVM INSTR lookupswitch 3: default 152
    //                   70: 203
    //                   77: 194
    //                   83: 227;
           goto _L9 _L10 _L11 _L12
_L9:
        boolean flag3 = true;
_L17:
        if (!flag3) goto _L14; else goto _L13
_L13:
        flag2 = true;
        if (!flag1 || k2 != 0) goto _L16; else goto _L15
_L15:
        try
        {
            outputPartialLine();
        }
        catch (IOException ioexception)
        {
            RuntimeException runtimeexception = new RuntimeException(ioexception);
            throw runtimeexception;
        }
          goto _L14
_L11:
        flag3 = isMisering();
          goto _L17
_L10:
        if (!isMisering() && lineNumber <= getSectionStartLine()) goto _L12; else goto _L18
_L18:
        flag3 = true;
          goto _L17
_L12:
        int l2 = queueInts[i + 3];
        int i3;
        if (l2 == 0)
        {
            i3 = -1;
        } else
        {
            i3 = l2 + i;
            if (i3 >= queueInts.length)
            {
                i3 -= queueInts.length;
            }
        }
        k2 = fitsOnLine(i3, flag);
        if (k2 > 0)
        {
            flag3 = false;
        } else
        {
            if (k2 >= 0 && !flag1)
            {
                break MISSING_BLOCK_LABEL_584;
            }
            flag3 = true;
        }
          goto _L17
_L16:
        outputLine(i);
          goto _L14
_L3:
        if (!isMisering())
        {
            int l1 = queueInts[i + 2];
            int i2 = queueInts[i + 3];
            int j2;
            if (l1 == 66)
            {
                j2 = i2 + getStartColumn();
            } else
            {
                j2 = i2 + posnColumn(queueInts[i + 1]);
            }
            setIndentation(j2);
        }
          goto _L14
_L4:
        int k = i;
        int l = queueInts[i + 3];
        int i1;
        int j1;
        if (l > 0)
        {
            i1 = (l + i) % queueInts.length;
        } else
        {
            i1 = -1;
        }
        j1 = fitsOnLine(i1, flag);
        if (j1 > 0)
        {
            int k1 = queueInts[i + 4];
            i = (k1 + i) % queueInts.length;
            expandTabs(i);
            queueTail = i;
            queueSize = queueSize - k1;
        } else
        {
            if (j1 >= 0 && !flag1)
            {
                break MISSING_BLOCK_LABEL_584;
            }
            String s = queueStrings[i + 5];
            String s1 = queueStrings[i + 6];
            reallyStartLogicalBlock(posnColumn(queueInts[i + 1]), s, s1);
        }
        if (currentBlock == k)
        {
            currentBlock = -1;
        }
          goto _L14
_L5:
        reallyEndLogicalBlock();
          goto _L14
_L6:
        expandTabs(i);
          goto _L14
        return flag2;
    }

    void outputLine(int i)
        throws IOException
    {
        char ac[];
        int j;
        int l;
        ac = buffer;
        boolean flag;
        int i1;
        int j1;
        int l1;
        int i2;
        char ac1[];
        int j2;
        int k2;
        int l2;
        int i3;
        char ac2[];
        int j3;
        if (queueInts[i + 4] == 76)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        j = posnIndex(queueInts[i + 1]);
        if (!flag) goto _L2; else goto _L1
_L1:
        l = j;
_L3:
        out.write(ac, 0, l);
        i1 = 1 + lineNumber;
        if (!printReadably())
        {
            l2 = getMaxLines();
            if (l2 > 0 && i1 >= l2)
            {
                out.write(" ..");
                i3 = getSuffixLength();
                if (i3 != 0)
                {
                    ac2 = suffix;
                    j3 = ac2.length;
                    out.write(ac2, j3 - i3, i3);
                }
                lineAbbreviationHappened();
            }
        }
        lineNumber = i1;
        out.write(10);
        bufferStartColumn = 0;
        j1 = bufferFillPointer;
        int k;
        int k1;
        if (flag)
        {
            k1 = getPerLinePrefixEnd();
        } else
        {
            k1 = getPrefixLength();
        }
        l1 = j - k1;
        i2 = j1 - l1;
        ac1 = ac;
        j2 = ac.length;
        if (i2 > j2)
        {
            ac1 = new char[enoughSpace(j2, i2 - j2)];
            buffer = ac1;
        }
        k2 = j1 - j;
        System.arraycopy(ac, j, ac1, k1, k2);
        System.arraycopy(prefix, 0, ac1, 0, k1);
        bufferFillPointer = i2;
        bufferOffset = l1 + bufferOffset;
        if (!flag)
        {
            blocks[-2 + blockDepth] = k1;
            blocks[-6 + blockDepth] = i1;
        }
        return;
_L2:
        k = j;
_L5:
        if (--k >= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        l = 0;
          goto _L3
        if (ac[k] == ' ') goto _L5; else goto _L4
_L4:
        l = k + 1;
          goto _L3
    }

    void outputPartialLine()
    {
        int i;
        for (i = queueTail; queueSize > 0 && getQueueType(i) == 0;)
        {
            int i1 = getQueueSize(i);
            queueSize = queueSize - i1;
            i += i1;
            if (i == queueInts.length)
            {
                i = 0;
            }
            queueTail = i;
        }

        int j = bufferFillPointer;
        int k;
        int l;
        if (queueSize > 0)
        {
            k = posnIndex(queueInts[i + 1]);
        } else
        {
            k = j;
        }
        l = j - k;
        if (k <= 0)
        {
            throw new Error("outputPartialLine called when nothing can be output.");
        }
        try
        {
            out.write(buffer, 0, k);
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException(ioexception);
        }
        bufferFillPointer = k;
        bufferStartColumn = getColumnNumber();
        System.arraycopy(buffer, k, buffer, 0, l);
        bufferFillPointer = l;
        bufferOffset = k + bufferOffset;
    }

    boolean printReadably()
    {
        return true;
    }

    void reallyEndLogicalBlock()
    {
        int i = getPrefixLength();
        blockDepth = -6 + blockDepth;
        int j = getPrefixLength();
        if (j > i)
        {
            for (int k = i; k < j; k++)
            {
                prefix[k] = ' ';
            }

        }
    }

    void reallyStartLogicalBlock(int i, String s, String s1)
    {
        int j = getPerLinePrefixEnd();
        int k = getPrefixLength();
        int l = getSuffixLength();
        pushLogicalBlock(i, j, k, l, lineNumber);
        setIndentation(i);
        if (s != null)
        {
            blocks[-3 + blockDepth] = i;
            int i2 = s.length();
            s.getChars(0, i2, suffix, i - i2);
        }
        if (s1 != null)
        {
            char ac[] = suffix;
            int i1 = ac.length;
            int j1 = s1.length();
            int k1 = l + j1;
            if (k1 > i1)
            {
                int l1 = enoughSpace(i1, j1);
                suffix = new char[l1];
                System.arraycopy(ac, i1 - l, suffix, l1 - l, l);
                i1 = l1;
            }
            s1.getChars(0, j1, ac, i1 - k1);
            blocks[-5 + blockDepth] = k1;
        }
    }

    public void setColumnNumber(int i)
    {
        bufferStartColumn = bufferStartColumn + (i - getColumnNumber());
    }

    public void setIndentation(int i)
    {
        char ac[] = prefix;
        int j = ac.length;
        int k = getPrefixLength();
        int l = getPerLinePrefixEnd();
        if (l > i)
        {
            i = l;
        }
        if (i > j)
        {
            ac = new char[enoughSpace(j, i - j)];
            System.arraycopy(prefix, 0, ac, 0, k);
            prefix = ac;
        }
        if (i > k)
        {
            for (int i1 = k; i1 < i; i1++)
            {
                ac[i1] = ' ';
            }

        }
        blocks[-4 + blockDepth] = i;
    }

    public void setPrettyPrinting(boolean flag)
    {
        int i;
        if (flag)
        {
            i = 0;
        } else
        {
            i = 1;
        }
        prettyPrintingMode = i;
    }

    public void setPrettyPrintingMode(int i)
    {
        prettyPrintingMode = i;
    }

    public void startLogicalBlock(String s, boolean flag, String s1)
    {
        int i;
        int k;
        int l;
        if (queueSize == 0 && bufferFillPointer == 0)
        {
            Object obj = lineLengthLoc.get(null);
            Object obj1;
            if (obj == null)
            {
                lineLength = 80;
            } else
            {
                lineLength = Integer.parseInt(obj.toString());
            }
            obj1 = miserWidthLoc.get(null);
            if (obj1 == null || obj1 == Boolean.FALSE || obj1 == LList.Empty)
            {
                miserWidth = -1;
            } else
            {
                miserWidth = Integer.parseInt(obj1.toString());
            }
            indentLoc.get(null);
        }
        if (s != null)
        {
            write(s);
        }
        if (prettyPrintingMode == 0)
        {
            return;
        }
        i = enqueue(4, 7);
        queueInts[i + 2] = pendingBlocksCount;
        String as[] = queueStrings;
        int j = i + 5;
        if (!flag)
        {
            s = null;
        }
        as[j] = s;
        queueStrings[i + 6] = s1;
        pendingBlocksCount = 1 + pendingBlocksCount;
        k = currentBlock;
        if (k >= 0) goto _L2; else goto _L1
_L1:
        l = 0;
_L4:
        queueInts[i + 4] = l;
        queueInts[i + 3] = 0;
        currentBlock = i;
        return;
_L2:
        l = k - i;
        if (l > 0)
        {
            l -= queueInts.length;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void write(int i)
    {
        wordEndSeen = false;
        if (i == 10 && prettyPrintingMode > 0)
        {
            enqueueNewline(76);
        } else
        {
            ensureSpaceInBuffer(1);
            int j = bufferFillPointer;
            buffer[j] = (char)i;
            bufferFillPointer = j + 1;
            if (i == 32 && prettyPrintingMode > 1 && currentBlock < 0)
            {
                enqueueNewline(83);
                return;
            }
        }
    }

    public void write(String s)
    {
        write(s, 0, s.length());
    }

    public void write(String s, int i, int j)
    {
        wordEndSeen = false;
        do
        {
            if (j <= 0)
            {
                break;
            }
            int k = j;
            int l = ensureSpaceInBuffer(j);
            if (k > l)
            {
                k = l;
            }
            int i1 = bufferFillPointer;
            j -= k;
            int j1 = i1;
            int k1 = i;
            while (--k >= 0) 
            {
                int l1 = k1 + 1;
                char c = s.charAt(k1);
                int i2;
                if (c == '\n' && prettyPrintingMode > 0)
                {
                    bufferFillPointer = j1;
                    enqueueNewline(76);
                    i2 = bufferFillPointer;
                } else
                {
                    char ac[] = buffer;
                    i2 = j1 + 1;
                    ac[j1] = c;
                    if (c == ' ' && prettyPrintingMode > 1 && currentBlock < 0)
                    {
                        bufferFillPointer = i2;
                        enqueueNewline(83);
                        i2 = bufferFillPointer;
                    }
                }
                j1 = i2;
                k1 = l1;
            }
            bufferFillPointer = j1;
            i = k1;
        } while (true);
    }

    public void write(char ac[])
    {
        write(ac, 0, ac.length);
    }

    public void write(char ac[], int i, int j)
    {
        wordEndSeen = false;
        int k = i + j;
label0:
        do
        {
            if (j <= 0)
            {
                break;
            }
            int l = i;
            do
            {
                if (l >= k)
                {
                    break;
                }
                if (prettyPrintingMode > 0)
                {
                    char c = ac[l];
                    if (c == '\n' || c == ' ' && currentBlock < 0)
                    {
                        write(ac, i, l - i);
                        write(c);
                        i = l + 1;
                        j = k - i;
                        continue label0;
                    }
                }
                l++;
            } while (true);
            int j2;
            do
            {
                int i1 = ensureSpaceInBuffer(j);
                int j1;
                int k1;
                int l1;
                int i2;
                if (i1 < j)
                {
                    j1 = i1;
                } else
                {
                    j1 = j;
                }
                k1 = bufferFillPointer;
                l1 = k1 + j1;
                i2 = k1;
                int k2;
                for (j2 = i; i2 < l1; j2 = k2)
                {
                    char ac1[] = buffer;
                    k2 = j2 + 1;
                    ac1[i2] = ac[j2];
                    i2++;
                }

                bufferFillPointer = l1;
                j -= j1;
                if (j != 0)
                {
                    i = j2;
                } else
                {
                    break;
                }
            } while (true);
            i = j2;
        } while (true);
    }

    public final void writeBreak(int i)
    {
        if (prettyPrintingMode > 0)
        {
            enqueueNewline(i);
        }
    }

    public void writeWordEnd()
    {
        wordEndSeen = true;
    }

    public void writeWordStart()
    {
        if (wordEndSeen)
        {
            write(32);
        }
        wordEndSeen = false;
    }

}
