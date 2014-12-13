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
//            ReadTableEntry, LispReader

public class ReaderString extends ReadTableEntry
{

    public ReaderString()
    {
    }

    public static String readString(Lexer lexer, int i, int j)
        throws IOException, SyntaxException
    {
        int k;
        LineBufferedReader linebufferedreader;
        int l;
        char c;
        k = lexer.tokenBufferLength;
        linebufferedreader = lexer.getPort();
        l = i;
        boolean flag = linebufferedreader instanceof InPort;
        c = '\0';
        if (flag)
        {
            c = ((InPort)linebufferedreader).readState;
            ((InPort)linebufferedreader).readState = (char)i;
        }
_L10:
        int i1 = l;
        if (i1 != 13) goto _L2; else goto _L1
_L1:
        l = linebufferedreader.read();
        if (l == 10)
        {
            continue; /* Loop/switch isn't completed */
        }
_L4:
        if (l != i)
        {
            break; /* Loop/switch isn't completed */
        }
        String s = (new String(lexer.tokenBuffer, k, lexer.tokenBufferLength - k)).intern();
        lexer.tokenBufferLength = k;
        if (linebufferedreader instanceof InPort)
        {
            ((InPort)linebufferedreader).readState = c;
        }
        return s;
_L2:
        if (linebufferedreader.pos >= linebufferedreader.limit || i1 == 10)
        {
            break MISSING_BLOCK_LABEL_182;
        }
        char ac[] = linebufferedreader.buffer;
        int j1 = linebufferedreader.pos;
        linebufferedreader.pos = j1 + 1;
        l = ac[j1];
        continue; /* Loop/switch isn't completed */
        l = linebufferedreader.read();
        if (true) goto _L4; else goto _L3
_L5:
        if (l >= 0)
        {
            break MISSING_BLOCK_LABEL_203;
        }
        lexer.eofError("unexpected EOF in string literal");
        lexer.tokenBufferAppend(l);
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        lexer.tokenBufferLength = k;
        if (linebufferedreader instanceof InPort)
        {
            ((InPort)linebufferedreader).readState = c;
        }
        throw exception;
_L6:
        int k1;
        byte byte0;
        if (linebufferedreader.getConvertCR())
        {
            byte0 = 10;
        } else
        {
            byte0 = 13;
            l = 32;
        }
        lexer.tokenBufferAppend(byte0);
        continue; /* Loop/switch isn't completed */
_L7:
        if (lexer instanceof LispReader)
        {
            l = ((LispReader)lexer).readEscape();
            continue; /* Loop/switch isn't completed */
        }
        k1 = linebufferedreader.read();
        l = k1;
        continue; /* Loop/switch isn't completed */
_L3:
        l;
        JVM INSTR lookupswitch 2: default 192
    //                   13: 240
    //                   92: 261;
           goto _L5 _L6 _L7
        if (l != -2) goto _L5; else goto _L8
_L8:
        l = 10;
        if (true) goto _L10; else goto _L9
_L9:
    }

    public Object read(Lexer lexer, int i, int j)
        throws IOException, SyntaxException
    {
        return readString(lexer, i, j);
    }
}
