// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.lispexpr;

import gnu.bytecode.Type;
import gnu.expr.Language;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.util.RangeTable;
import gnu.mapping.Environment;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.ThreadLocation;

// Referenced classes of package gnu.kawa.lispexpr:
//            LispLanguage, ReadTableEntry, ReaderColon, ReaderString, 
//            ReaderDispatch, ReaderIgnoreRestOfLine, ReaderParens, ReaderQuote, 
//            ReaderTypespec

public class ReadTable extends RangeTable
{

    public static final int CONSTITUENT = 2;
    public static final int ILLEGAL = 0;
    public static final int MULTIPLE_ESCAPE = 4;
    public static final int NON_TERMINATING_MACRO = 6;
    public static final int SINGLE_ESCAPE = 3;
    public static final int TERMINATING_MACRO = 5;
    public static final int WHITESPACE = 1;
    static final ThreadLocation current = new ThreadLocation("read-table");
    public static int defaultBracketMode = -1;
    Environment ctorTable;
    protected boolean finalColonIsKeyword;
    protected boolean hexEscapeAfterBackslash;
    protected boolean initialColonIsKeyword;
    public char postfixLookupOperator;

    public ReadTable()
    {
        postfixLookupOperator = '\uFFFF';
        hexEscapeAfterBackslash = true;
        ctorTable = null;
    }

    public static ReadTable createInitial()
    {
        ReadTable readtable = new ReadTable();
        readtable.initialize();
        return readtable;
    }

    public static ReadTable getCurrent()
    {
        ReadTable readtable = (ReadTable)current.get(null);
        if (readtable == null)
        {
            Language language = Language.getDefaultLanguage();
            if (language instanceof LispLanguage)
            {
                readtable = ((LispLanguage)language).defaultReadTable;
            } else
            {
                readtable = createInitial();
            }
            current.set(readtable);
        }
        return readtable;
    }

    public static void setCurrent(ReadTable readtable)
    {
        current.set(readtable);
    }

    public Object getReaderCtor(String s)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj;
        initCtorTable();
        obj = ctorTable.get(s, null);
        this;
        JVM INSTR monitorexit ;
        return obj;
        Exception exception;
        exception;
        throw exception;
    }

    void initCtorTable()
    {
        if (ctorTable == null)
        {
            ctorTable = Environment.make();
        }
    }

    public void initialize()
    {
        ReadTableEntry readtableentry = ReadTableEntry.getWhitespaceInstance();
        set(32, readtableentry);
        set(9, readtableentry);
        set(10, readtableentry);
        set(13, readtableentry);
        set(12, readtableentry);
        set(124, ReadTableEntry.getMultipleEscapeInstance());
        set(92, ReadTableEntry.getSingleEscapeInstance());
        set(48, 57, ReadTableEntry.getDigitInstance());
        ReadTableEntry readtableentry1 = ReadTableEntry.getConstituentInstance();
        set(97, 122, readtableentry1);
        set(65, 90, readtableentry1);
        set(33, readtableentry1);
        set(36, readtableentry1);
        set(37, readtableentry1);
        set(38, readtableentry1);
        set(42, readtableentry1);
        set(43, readtableentry1);
        set(45, readtableentry1);
        set(46, readtableentry1);
        set(47, readtableentry1);
        set(61, readtableentry1);
        set(62, readtableentry1);
        set(63, readtableentry1);
        set(64, readtableentry1);
        set(94, readtableentry1);
        set(95, readtableentry1);
        set(123, ReadTableEntry.brace);
        set(126, readtableentry1);
        set(127, readtableentry1);
        set(8, readtableentry1);
        set(58, new ReaderColon());
        set(34, new ReaderString());
        set(35, ReaderDispatch.create(this));
        set(59, ReaderIgnoreRestOfLine.getInstance());
        set(40, ReaderParens.getInstance('(', ')'));
        set(39, new ReaderQuote(makeSymbol("quote")));
        set(96, new ReaderQuote(makeSymbol("quasiquote")));
        set(44, new ReaderQuote(makeSymbol("unquote"), '@', makeSymbol("unquote-splicing")));
        setBracketMode();
    }

    public ReadTableEntry lookup(int i)
    {
        ReadTableEntry readtableentry = (ReadTableEntry)lookup(i, null);
        if (readtableentry != null || i < 0 || i >= 0x10000) goto _L2; else goto _L1
_L1:
        if (!Character.isDigit((char)i)) goto _L4; else goto _L3
_L3:
        readtableentry = (ReadTableEntry)lookup(48, null);
_L6:
        if (readtableentry == null && i >= 128)
        {
            readtableentry = ReadTableEntry.getConstituentInstance();
        }
        if (readtableentry == null)
        {
            readtableentry = ReadTableEntry.getIllegalInstance();
        }
_L2:
        return readtableentry;
_L4:
        if (Character.isLowerCase((char)i))
        {
            readtableentry = (ReadTableEntry)lookup(97, null);
        } else
        if (Character.isLetter((char)i))
        {
            readtableentry = (ReadTableEntry)lookup(65, null);
        } else
        if (Character.isWhitespace((char)i))
        {
            readtableentry = (ReadTableEntry)lookup(32, null);
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    protected Object makeSymbol(String s)
    {
        return Namespace.EmptyNamespace.getSymbol(s.intern());
    }

    public void putReaderCtor(String s, Type type)
    {
        this;
        JVM INSTR monitorenter ;
        initCtorTable();
        ctorTable.put(s, type);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void putReaderCtor(String s, Procedure procedure)
    {
        this;
        JVM INSTR monitorenter ;
        initCtorTable();
        ctorTable.put(s, procedure);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void putReaderCtorFld(String s, String s1, String s2)
    {
        this;
        JVM INSTR monitorenter ;
        initCtorTable();
        gnu.mapping.Symbol symbol = ctorTable.getSymbol(s);
        StaticFieldLocation.define(ctorTable, symbol, null, s1, s2);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void setBracketMode()
    {
        setBracketMode(defaultBracketMode);
    }

    public void setBracketMode(int i)
    {
        if (i <= 0)
        {
            ReadTableEntry readtableentry = ReadTableEntry.getConstituentInstance();
            set(60, readtableentry);
            if (i < 0)
            {
                set(91, readtableentry);
                set(93, readtableentry);
            }
        } else
        {
            set(60, new ReaderTypespec());
        }
        if (i >= 0)
        {
            set(91, ReaderParens.getInstance('[', ']'));
            remove(93);
        }
    }

    public void setFinalColonIsKeyword(boolean flag)
    {
        finalColonIsKeyword = flag;
    }

    public void setInitialColonIsKeyword(boolean flag)
    {
        initialColonIsKeyword = flag;
    }

}
