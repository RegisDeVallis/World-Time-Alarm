// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.lispexpr;

import gnu.lists.PairWithPosition;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

// Referenced classes of package gnu.kawa.lispexpr:
//            ReadTableEntry, LispReader

public class ReaderQuote extends ReadTableEntry
{

    Object magicSymbol;
    Object magicSymbol2;
    char next;

    public ReaderQuote(Object obj)
    {
        magicSymbol = obj;
    }

    public ReaderQuote(Object obj, char c, Object obj1)
    {
        next = c;
        magicSymbol = obj;
        magicSymbol2 = obj1;
    }

    public Object read(Lexer lexer, int i, int j)
        throws IOException, SyntaxException
    {
        LispReader lispreader;
        String s;
        int k;
        int l;
        Object obj;
        lispreader = (LispReader)lexer;
        s = lispreader.getName();
        k = 1 + lispreader.getLineNumber();
        l = 1 + lispreader.getColumnNumber();
        obj = magicSymbol;
        if (next == 0) goto _L2; else goto _L1
_L1:
        int k1 = lispreader.read();
        if (k1 != next) goto _L4; else goto _L3
_L3:
        obj = magicSymbol2;
_L2:
        int i1 = 1 + lispreader.getLineNumber();
        int j1 = 1 + lispreader.getColumnNumber();
        return PairWithPosition.make(obj, PairWithPosition.make(lispreader.readObject(), lispreader.makeNil(), s, i1, j1), s, k, l);
_L4:
        if (k1 >= 0)
        {
            lispreader.unread(k1);
        }
        if (true) goto _L2; else goto _L5
_L5:
    }
}
