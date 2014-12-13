// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.q2.lang;

import gnu.expr.Keyword;
import gnu.expr.QuoteExp;
import gnu.kawa.lispexpr.LispReader;
import gnu.kawa.xml.MakeAttribute;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import kawa.standard.begin;

// Referenced classes of package gnu.q2.lang:
//            Q2

public class Q2Read extends LispReader
{

    int curIndentation;
    int expressionStartColumn;
    String expressionStartFile;
    int expressionStartLine;

    public Q2Read(InPort inport)
    {
        super(inport);
        init();
    }

    public Q2Read(InPort inport, SourceMessages sourcemessages)
    {
        super(inport, sourcemessages);
        init();
    }

    public static Object readObject(InPort inport)
        throws IOException, SyntaxException
    {
        return (new Q2Read(inport)).readObject();
    }

    void init()
    {
        ((InPort)port).readState = ' ';
    }

    public Object readCommand()
        throws IOException, SyntaxException
    {
        int i = skipIndentation();
        Object obj;
        if (i < 0)
        {
            obj = Sequence.eofValue;
        } else
        {
            curIndentation = i;
            obj = readIndentCommand();
            if (!interactive)
            {
                port.reset();
                return obj;
            }
        }
        return obj;
    }

    public Object readCommand(boolean flag)
        throws IOException, SyntaxException
    {
        int i;
        int j;
        Object obj;
        PairWithPosition pairwithposition;
        Object obj1;
        port.getLineNumber();
        i = port.getColumnNumber();
        j = i;
        obj = LList.Empty;
        pairwithposition = null;
        obj1 = null;
_L21:
        int k = read();
        if (k >= 0) goto _L2; else goto _L1
_L1:
        if (flag) goto _L4; else goto _L3
_L3:
        if (obj != obj1) goto _L6; else goto _L5
_L5:
        obj = ((PairWithPosition) (obj1)).getCar();
_L4:
        return obj;
_L2:
        if (k == 32 || k == 9)
        {
            continue; /* Loop/switch isn't completed */
        }
        unread();
        if (k == 41) goto _L1; else goto _L7
_L7:
        int l;
        int i1;
        l = port.getLineNumber();
        i1 = port.getColumnNumber();
_L10:
        if (k != 13 && k != 10)
        {
            break; /* Loop/switch isn't completed */
        }
        if (singleLine()) goto _L4; else goto _L8
_L8:
        read();
        skipIndentation();
        i1 = port.getColumnNumber();
        k = peek();
        if (i1 > i) goto _L10; else goto _L9
_L9:
        if (i1 <= i && obj1 != null) goto _L1; else goto _L11
_L11:
        if (i1 != j || obj1 == null) goto _L13; else goto _L12
_L12:
        Object obj2 = readCommand();
_L16:
        if (obj2 != Sequence.eofValue)
        {
            j = i1;
            String s = port.getName();
            PairWithPosition pairwithposition1 = PairWithPosition.make(obj2, LList.Empty, s, l + 1, i1 + 1);
            PairWithPosition pairwithposition4;
            Object obj3;
            PairWithPosition pairwithposition5;
            int j1;
            Object obj4;
            if (obj1 == null)
            {
                pairwithposition = pairwithposition1;
                obj = pairwithposition1;
            } else
            {
                if (((PairWithPosition) (obj1)).getCar() instanceof Keyword)
                {
                    QuoteExp quoteexp = new QuoteExp(((Keyword)((PairWithPosition) (obj1)).getCar()).getName());
                    MakeAttribute makeattribute = MakeAttribute.makeAttribute;
                    PairWithPosition pairwithposition2 = new PairWithPosition(((gnu.text.SourceLocator) (obj1)), quoteexp, pairwithposition1);
                    PairWithPosition pairwithposition3 = new PairWithPosition(((gnu.text.SourceLocator) (obj1)), makeattribute, pairwithposition2);
                    ((PairWithPosition) (obj1)).setCar(pairwithposition3);
                    continue; /* Loop/switch isn't completed */
                }
                ((PairWithPosition) (obj1)).setCdrBackdoor(pairwithposition1);
            }
            obj1 = pairwithposition1;
            continue; /* Loop/switch isn't completed */
        }
          goto _L1
_L13:
        if (i1 >= j || obj1 == null)
        {
            break MISSING_BLOCK_LABEL_402;
        }
        pairwithposition4 = pairwithposition;
_L18:
        obj3 = pairwithposition4.getCdr();
        if (obj3 != LList.Empty) goto _L15; else goto _L14
_L14:
        obj2 = readCommand();
          goto _L16
_L15:
        pairwithposition5 = (PairWithPosition)obj3;
        j1 = -1 + pairwithposition5.getColumnNumber();
        if (j1 < i1)
        {
            break MISSING_BLOCK_LABEL_395;
        }
        if (j1 > i1)
        {
            error('e', "some tokens on previous line indented more than current line");
        }
        obj4 = pairwithposition5.getCdr();
        if (obj4 == LList.Empty) goto _L14; else goto _L17
_L17:
label0:
        {
            if (-1 + ((PairWithPosition)obj4).getColumnNumber() != i1)
            {
                break label0;
            }
            pairwithposition4 = (PairWithPosition)obj4;
        }
          goto _L18
        obj1 = (PairWithPosition)makePair(pairwithposition5, port.getLineNumber(), i1);
        pairwithposition4.setCdrBackdoor(obj1);
          goto _L14
        pairwithposition4 = pairwithposition5;
          goto _L18
        obj2 = readObject();
          goto _L16
_L6:
        if (obj1 != null) goto _L4; else goto _L19
_L19:
        return QuoteExp.voidExp;
        if (true) goto _L21; else goto _L20
_L20:
    }

    Object readIndentCommand()
        throws IOException, SyntaxException
    {
        int i;
        Object obj;
        i = curIndentation;
        obj = LList.Empty;
        LList.Empty;
_L11:
        int j = read();
        if (j >= 0) goto _L2; else goto _L1
_L1:
        return LList.reverseInPlace(obj);
_L2:
        if (j == 32 || j == 9)
        {
            continue; /* Loop/switch isn't completed */
        }
        unread();
        if (j == 41) goto _L1; else goto _L3
_L3:
        if (j != 13 && j != 10) goto _L5; else goto _L4
_L4:
        if (singleLine()) goto _L1; else goto _L6
_L6:
        int k;
        Object obj1;
        read();
        port.mark(0x7fffffff);
        k = skipIndentation();
        obj1 = LList.Empty;
        curIndentation = k;
          goto _L7
_L8:
        if (obj1 != LList.Empty)
        {
            obj = new Pair(new Pair(begin.begin, LList.reverseInPlace(obj1)), obj);
        }
          goto _L1
_L7:
        int l;
        if (curIndentation != -1 && k == curIndentation)
        {
            l = Q2.compareIndentation(k, i);
            if (l == 0x80000000)
            {
                error('e', "cannot compare indentation - mix of tabs and spaces");
            } else
            {
                if (l != -1 && l != 1)
                {
                    continue; /* Loop/switch isn't completed */
                }
                error('e', "indentation must differ by 2 or more");
            }
        }
          goto _L8
        if (l <= 0) goto _L8; else goto _L9
_L9:
        int i1 = port.getLineNumber();
        int j1 = port.getColumnNumber();
        obj1 = makePair(readIndentCommand(), obj1, i1, j1);
        if (true) goto _L7; else goto _L5
_L5:
        int k1 = port.getLineNumber();
        int l1 = port.getColumnNumber();
        obj = makePair(readObject(), obj, k1, l1);
        if (true) goto _L11; else goto _L10
_L10:
    }

    void saveExpressionStartPosition()
    {
        expressionStartFile = port.getName();
        expressionStartLine = port.getLineNumber();
        expressionStartColumn = port.getColumnNumber();
    }

    boolean singleLine()
    {
        return interactive && nesting == 0;
    }

    int skipIndentation()
        throws IOException, SyntaxException
    {
        int i = 0;
        int j = port.read();
        int k;
        do
        {
            k = 0;
            if (j != 9)
            {
                break;
            }
            i++;
            j = port.read();
        } while (true);
        for (; j == 32; j = port.read())
        {
            k++;
        }

        if (j < 0)
        {
            return -1;
        } else
        {
            port.unread();
            return k + (i << 16);
        }
    }
}
