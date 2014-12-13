// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.lispexpr;

import gnu.mapping.InPort;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

// Referenced classes of package gnu.kawa.lispexpr:
//            ReadTableEntry, ReadTable, LispReader

public class ReaderTypespec extends ReadTableEntry
{

    public ReaderTypespec()
    {
    }

    public int getKind()
    {
        return 6;
    }

    public Object read(Lexer lexer, int i, int j)
        throws IOException, SyntaxException
    {
        int k;
        LineBufferedReader linebufferedreader;
        ReadTable readtable;
        int l;
        char c;
        boolean flag1;
        k = lexer.tokenBufferLength;
        linebufferedreader = lexer.getPort();
        readtable = ReadTable.getCurrent();
        lexer.tokenBufferAppend(i);
        l = i;
        boolean flag = linebufferedreader instanceof InPort;
        c = '\0';
        if (flag)
        {
            c = ((InPort)linebufferedreader).readState;
            ((InPort)linebufferedreader).readState = (char)i;
        }
        flag1 = false;
_L7:
        int i1 = l;
        if (linebufferedreader.pos >= linebufferedreader.limit || i1 == 10) goto _L2; else goto _L1
_L1:
        char ac[] = linebufferedreader.buffer;
        int j1 = linebufferedreader.pos;
        linebufferedreader.pos = j1 + 1;
        l = ac[j1];
_L8:
        if (l != 92) goto _L4; else goto _L3
_L3:
        if (!(lexer instanceof LispReader)) goto _L6; else goto _L5
_L5:
        l = ((LispReader)lexer).readEscape();
          goto _L7
_L2:
        l = linebufferedreader.read();
          goto _L8
_L6:
        l = linebufferedreader.read();
          goto _L7
_L13:
        if (readtable.lookup(l).getKind() != 2) goto _L10; else goto _L9
_L9:
        lexer.tokenBufferAppend(l);
          goto _L7
        Exception exception;
        exception;
        lexer.tokenBufferLength = k;
        if (linebufferedreader instanceof InPort)
        {
            ((InPort)linebufferedreader).readState = c;
        }
        throw exception;
_L10:
        String s;
        lexer.unread(l);
        s = (new String(lexer.tokenBuffer, k, lexer.tokenBufferLength - k)).intern();
        lexer.tokenBufferLength = k;
        if (linebufferedreader instanceof InPort)
        {
            ((InPort)linebufferedreader).readState = c;
        }
        return s;
_L4:
        if (flag1 || l != 91)
        {
            break; /* Loop/switch isn't completed */
        }
        flag1 = true;
        if (flag1) goto _L9; else goto _L11
_L11:
        if (!flag1 || l != 93) goto _L13; else goto _L12
_L12:
        flag1 = false;
        if (true) goto _L9; else goto _L14
_L14:
        flag1 = false;
          goto _L13
    }
}
