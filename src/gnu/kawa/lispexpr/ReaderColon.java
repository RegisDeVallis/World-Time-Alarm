// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.lispexpr;

import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

// Referenced classes of package gnu.kawa.lispexpr:
//            ReadTableEntry, LispReader, ReadTable

public class ReaderColon extends ReadTableEntry
{

    public ReaderColon()
    {
    }

    public int getKind()
    {
        return 6;
    }

    public Object read(Lexer lexer, int i, int j)
        throws IOException, SyntaxException
    {
        LispReader lispreader = (LispReader)lexer;
        ReadTable readtable = ReadTable.getCurrent();
        int k = lispreader.tokenBufferLength;
        if (i == readtable.postfixLookupOperator)
        {
            int l = lispreader.read();
            if (l == 58)
            {
                return readtable.makeSymbol("::");
            }
            lispreader.tokenBufferAppend(i);
            i = l;
        }
        return lispreader.readAndHandleToken(i, k, readtable);
    }
}
