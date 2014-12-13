// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.lispexpr;

import gnu.expr.QuoteExp;
import gnu.lists.FVector;
import gnu.mapping.InPort;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.util.Vector;

// Referenced classes of package gnu.kawa.lispexpr:
//            ReadTableEntry, ReadTable, LispReader

public class ReaderVector extends ReadTableEntry
{

    char close;

    public ReaderVector(char c)
    {
        close = c;
    }

    public static FVector readVector(LispReader lispreader, LineBufferedReader linebufferedreader, int i, char c)
        throws IOException, SyntaxException
    {
        char c1;
        Vector vector;
        ReadTable readtable;
        int j;
        c1 = ' ';
        if (linebufferedreader instanceof InPort)
        {
            c1 = ((InPort)linebufferedreader).readState;
            InPort inport = (InPort)linebufferedreader;
            Object aobj[];
            FVector fvector;
            char c2;
            if (c == ']')
            {
                c2 = '[';
            } else
            {
                c2 = '(';
            }
            inport.readState = c2;
        }
        vector = new Vector();
        readtable = ReadTable.getCurrent();
_L2:
        j = lispreader.read();
        if (j >= 0)
        {
            break MISSING_BLOCK_LABEL_74;
        }
        lispreader.eofError("unexpected EOF in vector");
        if (j != c)
        {
            break MISSING_BLOCK_LABEL_134;
        }
        aobj = new Object[vector.size()];
        vector.copyInto(aobj);
        fvector = new FVector(aobj);
        if (linebufferedreader instanceof InPort)
        {
            ((InPort)linebufferedreader).readState = c1;
        }
        return fvector;
        Object obj;
        Object aobj1[];
        int k;
        obj = lispreader.readValues(j, readtable);
        if (!(obj instanceof Values))
        {
            break MISSING_BLOCK_LABEL_193;
        }
        aobj1 = ((Values)obj).getValues();
        k = aobj1.length;
        int l = 0;
_L3:
        if (l >= k) goto _L2; else goto _L1
_L1:
        vector.addElement(aobj1[l]);
        l++;
          goto _L3
        if (obj == QuoteExp.voidExp)
        {
            obj = Values.empty;
        }
        vector.addElement(obj);
          goto _L2
        Exception exception;
        exception;
        if (linebufferedreader instanceof InPort)
        {
            ((InPort)linebufferedreader).readState = c1;
        }
        throw exception;
    }

    public Object read(Lexer lexer, int i, int j)
        throws IOException, SyntaxException
    {
        return readVector((LispReader)lexer, lexer.getPort(), j, close);
    }
}
