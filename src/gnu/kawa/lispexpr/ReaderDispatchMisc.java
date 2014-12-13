// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.lispexpr;

import gnu.bytecode.Type;
import gnu.expr.Keyword;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.util.GeneralHashTable;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.util.regex.Pattern;

// Referenced classes of package gnu.kawa.lispexpr:
//            ReadTableEntry, LispReader, ReadTable

public class ReaderDispatchMisc extends ReadTableEntry
{

    private static ReaderDispatchMisc instance = new ReaderDispatchMisc();
    protected int code;

    public ReaderDispatchMisc()
    {
        code = -1;
    }

    public ReaderDispatchMisc(int i)
    {
        code = i;
    }

    public static ReaderDispatchMisc getInstance()
    {
        return instance;
    }

    public static Pattern readRegex(Lexer lexer, int i, int j)
        throws IOException, SyntaxException
    {
        int k;
        LineBufferedReader linebufferedreader;
        int l;
        char c;
        k = lexer.tokenBufferLength;
        linebufferedreader = lexer.getPort();
        l = 0;
        boolean flag = linebufferedreader instanceof InPort;
        c = '\0';
        if (flag)
        {
            c = ((InPort)linebufferedreader).readState;
            ((InPort)linebufferedreader).readState = '/';
        }
_L7:
        int i1 = linebufferedreader.read();
        if (i1 >= 0)
        {
            break MISSING_BLOCK_LABEL_67;
        }
        lexer.eofError("unexpected EOF in regex literal");
        if (i1 != i) goto _L2; else goto _L1
_L1:
        String s = new String(lexer.tokenBuffer, k, lexer.tokenBufferLength - k);
_L3:
        int j1 = lexer.peek();
        Exception exception;
        Pattern pattern;
        if (j1 == 105 || j1 == 73)
        {
            l |= 0x42;
        } else
        {
            if (j1 != 115 && j1 != 83)
            {
                continue; /* Loop/switch isn't completed */
            }
            l |= 0x20;
        }
_L10:
        lexer.skip();
          goto _L3
        exception;
        lexer.tokenBufferLength = k;
        if (linebufferedreader instanceof InPort)
        {
            ((InPort)linebufferedreader).readState = c;
        }
        throw exception;
_L2:
        if (i1 != 92)
        {
            break MISSING_BLOCK_LABEL_241;
        }
        i1 = linebufferedreader.read();
        if (i1 != 32 && i1 != 9 && i1 != 13 && i1 != 10) goto _L5; else goto _L4
_L4:
        if (!(lexer instanceof LispReader)) goto _L5; else goto _L6
_L6:
        i1 = ((LispReader)lexer).readEscape(i1);
        if (i1 == -2) goto _L7; else goto _L5
_L5:
        if (i1 >= 0)
        {
            break MISSING_BLOCK_LABEL_229;
        }
        lexer.eofError("unexpected EOF in regex literal");
        if (i1 == i)
        {
            break MISSING_BLOCK_LABEL_241;
        }
        lexer.tokenBufferAppend(92);
        lexer.tokenBufferAppend(i1);
          goto _L7
_L9:
label0:
        {
            if (!Character.isLetter(j1))
            {
                break label0;
            }
            lexer.error((new StringBuilder()).append("unrecognized regex option '").append((char)j1).append('\'').toString());
        }
        break MISSING_BLOCK_LABEL_116;
        pattern = Pattern.compile(s, l);
        lexer.tokenBufferLength = k;
        if (linebufferedreader instanceof InPort)
        {
            ((InPort)linebufferedreader).readState = c;
        }
        return pattern;
        if (j1 != 109 && j1 != 77) goto _L9; else goto _L8
_L8:
        l |= 8;
          goto _L10
    }

    public Object read(Lexer lexer, int i, int j)
        throws IOException, SyntaxException
    {
        LispReader lispreader;
        lispreader = (LispReader)lexer;
        if (code >= 0)
        {
            i = code;
        }
        i;
        JVM INSTR lookupswitch 20: default 188
    //                   33: 274
    //                   35: 972
    //                   44: 612
    //                   47: 416
    //                   58: 202
    //                   59: 519
    //                   61: 909
    //                   66: 387
    //                   68: 369
    //                   69: 395
    //                   70: 284
    //                   73: 395
    //                   79: 378
    //                   82: 315
    //                   83: 307
    //                   84: 280
    //                   85: 307
    //                   88: 360
    //                   92: 268
    //                   124: 423;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L11 _L13 _L14 _L15 _L16 _L15 _L17 _L18 _L19
_L1:
        Object obj;
        lexer.error("An invalid #-construct was read.");
        obj = Values.empty;
_L31:
        return obj;
_L6:
        int k1 = lispreader.tokenBufferLength;
        lispreader.readToken(lispreader.read(), 'P', ReadTable.getCurrent());
        int l1 = lispreader.tokenBufferLength - k1;
        String s1 = new String(lispreader.tokenBuffer, k1, l1);
        lispreader.tokenBufferLength = k1;
        return Keyword.make(s1.intern());
_L18:
        return LispReader.readCharacter(lispreader);
_L2:
        return LispReader.readSpecial(lispreader);
_L16:
        return Boolean.TRUE;
_L12:
        if (Character.isDigit((char)lexer.peek()))
        {
            return LispReader.readSimpleVector(lispreader, 'F');
        } else
        {
            return Boolean.FALSE;
        }
_L15:
        return LispReader.readSimpleVector(lispreader, (char)i);
_L14:
        if (j > 36)
        {
            lexer.error((new StringBuilder()).append("the radix ").append(j).append(" is too big (max is 36)").toString());
            j = 36;
        }
        return LispReader.readNumberWithRadix(0, lispreader, j);
_L17:
        return LispReader.readNumberWithRadix(0, lispreader, 16);
_L10:
        return LispReader.readNumberWithRadix(0, lispreader, 10);
_L13:
        return LispReader.readNumberWithRadix(0, lispreader, 8);
_L9:
        return LispReader.readNumberWithRadix(0, lispreader, 2);
_L11:
        lispreader.tokenBufferAppend(35);
        lispreader.tokenBufferAppend(i);
        return LispReader.readNumberWithRadix(2, lispreader, 0);
_L5:
        return readRegex(lexer, i, j);
_L19:
        LineBufferedReader linebufferedreader1;
        char c1;
        linebufferedreader1 = lispreader.getPort();
        boolean flag1 = linebufferedreader1 instanceof InPort;
        c1 = '\0';
        if (flag1)
        {
            c1 = ((InPort)linebufferedreader1).readState;
            ((InPort)linebufferedreader1).readState = '|';
        }
        lispreader.readNestedComment('#', '|');
        if (linebufferedreader1 instanceof InPort)
        {
            ((InPort)linebufferedreader1).readState = c1;
        }
        return Values.empty;
        Exception exception1;
        exception1;
        if (linebufferedreader1 instanceof InPort)
        {
            ((InPort)linebufferedreader1).readState = c1;
        }
        throw exception1;
_L7:
        LineBufferedReader linebufferedreader;
        char c;
        linebufferedreader = lispreader.getPort();
        boolean flag = linebufferedreader instanceof InPort;
        c = '\0';
        if (flag)
        {
            c = ((InPort)linebufferedreader).readState;
            ((InPort)linebufferedreader).readState = ';';
        }
        lispreader.readObject();
        if (linebufferedreader instanceof InPort)
        {
            ((InPort)linebufferedreader).readState = c;
        }
        return Values.empty;
        Exception exception;
        exception;
        if (linebufferedreader instanceof InPort)
        {
            ((InPort)linebufferedreader).readState = c;
        }
        throw exception;
_L4:
        if (lispreader.getPort().peek() != 40) goto _L21; else goto _L20
_L20:
        Object obj1;
        int k;
        obj1 = lispreader.readObject();
        k = LList.listLength(obj1, false);
        if (k <= 0 || !(((Pair)obj1).getCar() instanceof Symbol)) goto _L21; else goto _L22
_L22:
        String s;
        Object obj2;
        s = ((Pair)obj1).getCar().toString();
        obj2 = ReadTable.getCurrent().getReaderCtor(s);
        if (obj2 != null) goto _L24; else goto _L23
_L23:
        lexer.error((new StringBuilder()).append("unknown reader constructor ").append(s).toString());
_L27:
        return Boolean.FALSE;
_L24:
        if ((obj2 instanceof Procedure) || (obj2 instanceof Type)) goto _L26; else goto _L25
_L25:
        lexer.error("reader constructor must be procedure or type name");
          goto _L27
_L26:
        int i1;
        Object aobj[];
        int l = k - 1;
        Object obj3;
        if (obj2 instanceof Type)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        aobj = new Object[i1 + l];
        obj3 = ((Pair)obj1).getCdr();
        for (int j1 = 0; j1 < l; j1++)
        {
            Pair pair = (Pair)obj3;
            aobj[i1 + j1] = pair.getCar();
            obj3 = pair.getCdr();
        }

        if (i1 <= 0) goto _L29; else goto _L28
_L28:
        aobj[0] = obj2;
        return Invoke.make.applyN(aobj);
_L29:
        Object obj4 = ((Procedure)obj2).applyN(aobj);
        return obj4;
        Throwable throwable;
        throwable;
        lexer.error((new StringBuilder()).append("caught ").append(throwable).append(" applying reader constructor ").append(s).toString());
          goto _L27
_L21:
        lexer.error("a non-empty list starting with a symbol must follow #,");
          goto _L27
_L8:
        obj = lispreader.readObject();
        if (!(lexer instanceof LispReader)) goto _L31; else goto _L30
_L30:
        LispReader lispreader1 = (LispReader)lexer;
        GeneralHashTable generalhashtable1 = lispreader1.sharedStructureTable;
        if (generalhashtable1 == null)
        {
            generalhashtable1 = new GeneralHashTable();
            lispreader1.sharedStructureTable = generalhashtable1;
        }
        generalhashtable1.put(Integer.valueOf(j), obj);
        return obj;
_L3:
        if (!(lexer instanceof LispReader))
        {
            break; /* Loop/switch isn't completed */
        }
        GeneralHashTable generalhashtable = ((LispReader)lexer).sharedStructureTable;
        if (generalhashtable == null)
        {
            break; /* Loop/switch isn't completed */
        }
        obj = generalhashtable.get(Integer.valueOf(j), lexer);
        if (obj != lexer) goto _L31; else goto _L32
_L32:
        lexer.error("an unrecognized #n# back-reference was read");
        return Values.empty;
    }

}
