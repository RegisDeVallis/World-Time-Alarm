// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.lispexpr;

import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

// Referenced classes of package gnu.kawa.lispexpr:
//            ReadTableEntry, LispReader, ReadTable

public class ReaderParens extends ReadTableEntry
{

    private static ReaderParens instance;
    char close;
    Object command;
    int kind;
    char open;

    public ReaderParens(char c, char c1, int i, Object obj)
    {
        open = c;
        close = c1;
        kind = i;
        command = obj;
    }

    public static ReaderParens getInstance(char c, char c1)
    {
        return getInstance(c, c1, 5);
    }

    public static ReaderParens getInstance(char c, char c1, int i)
    {
        if (c == '(' && c1 == ')' && i == 5)
        {
            if (instance == null)
            {
                instance = new ReaderParens(c, c1, i, null);
            }
            return instance;
        } else
        {
            return new ReaderParens(c, c1, i, null);
        }
    }

    public static ReaderParens getInstance(char c, char c1, int i, Object obj)
    {
        if (obj == null)
        {
            return getInstance(c, c1, i);
        } else
        {
            return new ReaderParens(c, c1, i, obj);
        }
    }

    public static Object readList(LispReader lispreader, int i, int j, int k)
        throws IOException, SyntaxException
    {
        LineBufferedReader linebufferedreader;
        char c1;
        int l;
        int i1;
        Object obj;
        Object obj1;
        boolean flag;
        boolean flag1;
        ReadTable readtable;
        int j1;
        int k1;
        int i2;
        linebufferedreader = lispreader.getPort();
        char c;
        int l1;
        if (k == 93)
        {
            c = '[';
        } else
        {
            c = '(';
        }
        c1 = lispreader.pushNesting(c);
        l = linebufferedreader.getLineNumber();
        i1 = linebufferedreader.getColumnNumber();
        obj = null;
        obj1 = lispreader.makeNil();
        flag = false;
        flag1 = false;
        readtable = ReadTable.getCurrent();
_L11:
        j1 = linebufferedreader.getLineNumber();
        k1 = linebufferedreader.getColumnNumber();
        l1 = linebufferedreader.read();
        i2 = l1;
        if (i2 != k) goto _L2; else goto _L1
_L1:
        lispreader.popNesting(c1);
        return obj1;
_L2:
        if (i2 >= 0)
        {
            break MISSING_BLOCK_LABEL_122;
        }
        lispreader.eofError("unexpected EOF in list starting here", l + 1, i1);
        if (i2 != 46) goto _L4; else goto _L3
_L3:
        ReadTableEntry readtableentry;
        int j2;
        i2 = linebufferedreader.peek();
        readtableentry = readtable.lookup(i2);
        j2 = readtableentry.getKind();
        if (j2 != 1 && j2 != 5 && j2 != 0) goto _L6; else goto _L5
_L5:
        linebufferedreader.skip();
        k1++;
        if (i2 != k)
        {
            break MISSING_BLOCK_LABEL_226;
        }
        lispreader.error((new StringBuilder()).append("unexpected '").append((char)k).append("' after '.'").toString());
          goto _L1
        Exception exception;
        exception;
        lispreader.popNesting(c1);
        throw exception;
        if (i2 >= 0)
        {
            break MISSING_BLOCK_LABEL_243;
        }
        lispreader.eofError("unexpected EOF in list starting here", l + 1, i1);
        if (!flag) goto _L8; else goto _L7
_L7:
        lispreader.error("multiple '.' in list");
        obj1 = lispreader.makeNil();
        obj = null;
        flag1 = false;
          goto _L8
_L13:
        Object obj3 = lispreader.readValues(i2, readtableentry, readtable);
        if (obj3 != Values.empty) goto _L10; else goto _L9
_L9:
        Object obj2;
        obj1 = obj2;
          goto _L11
_L6:
        i2 = 46;
        readtableentry = ReadTableEntry.getConstituentInstance();
          goto _L12
_L4:
        readtableentry = readtable.lookup(i2);
        obj2 = obj1;
          goto _L13
_L10:
        Object obj4 = lispreader.handlePostfix(obj3, readtable, j1, k1);
        if (!flag1) goto _L15; else goto _L14
_L14:
        lispreader.error("multiple values after '.'");
        obj1 = lispreader.makeNil();
        obj = null;
        flag1 = false;
          goto _L11
_L20:
        Object obj5 = lispreader.makePair(obj4, j1, k1);
          goto _L16
_L19:
        lispreader.setCdr(obj, obj5);
          goto _L17
_L8:
        flag = true;
_L12:
        obj2 = obj1;
          goto _L13
_L15:
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_432;
        }
        flag1 = true;
        obj5 = obj4;
_L16:
        if (obj != null) goto _L19; else goto _L18
_L18:
        obj2 = obj5;
_L17:
        obj = obj5;
        obj1 = obj2;
          goto _L11
        if (obj == null)
        {
            j1 = l;
            k1 = i1 - 1;
        }
          goto _L20
    }

    public int getKind()
    {
        return kind;
    }

    public Object read(Lexer lexer, int i, int j)
        throws IOException, SyntaxException
    {
        Object obj = readList((LispReader)lexer, i, j, close);
        if (command != null)
        {
            LineBufferedReader linebufferedreader = lexer.getPort();
            int k = linebufferedreader.getLineNumber();
            int l = linebufferedreader.getColumnNumber();
            gnu.lists.Pair pair = ((LispReader)lexer).makePair(command, k, l);
            ((LispReader)lexer).setCdr(pair, obj);
            obj = pair;
        }
        return obj;
    }
}
