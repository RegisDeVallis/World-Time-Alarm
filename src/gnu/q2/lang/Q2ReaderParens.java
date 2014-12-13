// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.q2.lang;

import gnu.kawa.lispexpr.ReaderDispatchMisc;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

// Referenced classes of package gnu.q2.lang:
//            Q2Read

class Q2ReaderParens extends ReaderDispatchMisc
{

    Q2ReaderParens()
    {
    }

    public Object read(Lexer lexer, int i, int j)
        throws IOException, SyntaxException
    {
        Q2Read q2read;
        char c;
        q2read = (Q2Read)lexer;
        c = q2read.pushNesting('(');
        Object obj;
        obj = q2read.readCommand(true);
        if (q2read.getPort().read() != 41)
        {
            q2read.error("missing ')'");
        }
        q2read.popNesting(c);
        return obj;
        Exception exception;
        exception;
        q2read.popNesting(c);
        throw exception;
    }
}
