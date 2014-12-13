// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.lispexpr;

import gnu.kawa.util.RangeTable;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

// Referenced classes of package gnu.kawa.lispexpr:
//            ReadTableEntry, ReaderDispatchMisc, ReaderQuote, ReadTable, 
//            ReaderVector, ReaderXmlElement

public class ReaderDispatch extends ReadTableEntry
{

    int kind;
    RangeTable table;

    public ReaderDispatch()
    {
        table = new RangeTable();
        kind = 5;
    }

    public ReaderDispatch(boolean flag)
    {
        table = new RangeTable();
        int i;
        if (flag)
        {
            i = 6;
        } else
        {
            i = 5;
        }
        kind = i;
    }

    public static ReaderDispatch create(ReadTable readtable)
    {
        ReaderDispatch readerdispatch = new ReaderDispatch();
        ReaderDispatchMisc readerdispatchmisc = ReaderDispatchMisc.getInstance();
        readerdispatch.set(58, readerdispatchmisc);
        readerdispatch.set(66, readerdispatchmisc);
        readerdispatch.set(68, readerdispatchmisc);
        readerdispatch.set(69, readerdispatchmisc);
        readerdispatch.set(70, readerdispatchmisc);
        readerdispatch.set(73, readerdispatchmisc);
        readerdispatch.set(79, readerdispatchmisc);
        readerdispatch.set(82, readerdispatchmisc);
        readerdispatch.set(83, readerdispatchmisc);
        readerdispatch.set(84, readerdispatchmisc);
        readerdispatch.set(85, readerdispatchmisc);
        readerdispatch.set(88, readerdispatchmisc);
        readerdispatch.set(124, readerdispatchmisc);
        readerdispatch.set(59, readerdispatchmisc);
        readerdispatch.set(33, readerdispatchmisc);
        readerdispatch.set(92, readerdispatchmisc);
        readerdispatch.set(61, readerdispatchmisc);
        readerdispatch.set(35, readerdispatchmisc);
        readerdispatch.set(47, readerdispatchmisc);
        readerdispatch.set(39, new ReaderQuote(readtable.makeSymbol("function")));
        readerdispatch.set(40, new ReaderVector(')'));
        readerdispatch.set(60, new ReaderXmlElement());
        return readerdispatch;
    }

    public int getKind()
    {
        return kind;
    }

    public ReadTableEntry lookup(int i)
    {
        return (ReadTableEntry)table.lookup(i, null);
    }

    public Object read(Lexer lexer, int i, int j)
        throws IOException, SyntaxException
    {
        int k = -1;
_L2:
        int l = lexer.read();
        if (l < 0)
        {
            lexer.eofError((new StringBuilder()).append("unexpected EOF after ").append((char)l).toString());
        }
        int i1;
        ReadTableEntry readtableentry;
        if (l <= 0x10000)
        {
label0:
            {
                i1 = Character.digit((char)l, 10);
                if (i1 >= 0)
                {
                    break label0;
                }
                l = Character.toUpperCase((char)l);
            }
        }
        readtableentry = (ReadTableEntry)table.lookup(l, null);
        if (readtableentry == null)
        {
            lexer.error('e', lexer.getName(), 1 + lexer.getLineNumber(), lexer.getColumnNumber(), (new StringBuilder()).append("invalid dispatch character '").append((char)l).append('\'').toString());
            return Values.empty;
        } else
        {
            return readtableentry.read(lexer, l, k);
        }
        if (k < 0)
        {
            k = i1;
        } else
        {
            k = i1 + k * 10;
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void set(int i, Object obj)
    {
        table.set(i, i, obj);
    }
}
