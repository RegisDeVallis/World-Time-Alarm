// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.lispexpr;

import gnu.lists.Sequence;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

// Referenced classes of package gnu.kawa.lispexpr:
//            ReadTableEntry

public class ReaderIgnoreRestOfLine extends ReadTableEntry
{

    static ReaderIgnoreRestOfLine instance = new ReaderIgnoreRestOfLine();

    public ReaderIgnoreRestOfLine()
    {
    }

    public static ReaderIgnoreRestOfLine getInstance()
    {
        return instance;
    }

    public Object read(Lexer lexer, int i, int j)
        throws IOException, SyntaxException
    {
        int k;
        do
        {
            k = lexer.read();
            if (k < 0)
            {
                return Sequence.eofValue;
            }
        } while (k != 10 && k != 13);
        return Values.empty;
    }

}
