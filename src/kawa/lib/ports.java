// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.bytecode.ClassType;
import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.lists.EofClass;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.InPort;
import gnu.mapping.LocationProc;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.LineBufferedReader;
import gnu.text.Path;
import gnu.text.SyntaxException;
import java.io.Writer;
import kawa.standard.Scheme;
import kawa.standard.char_ready_p;
import kawa.standard.read_line;

// Referenced classes of package kawa.lib:
//            characters, numbers, strings

public class ports extends ModuleBody
{

    public static final ports $instance;
    static final SimpleSymbol Lit0 = (SimpleSymbol)(new SimpleSymbol("current-input-port")).readResolve();
    static final ClassType Lit1 = ClassType.make("gnu.mapping.InPort");
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2 = (SimpleSymbol)(new SimpleSymbol("current-output-port")).readResolve();
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final ClassType Lit3 = ClassType.make("gnu.mapping.OutPort");
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final SimpleSymbol Lit33;
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit36;
    static final SimpleSymbol Lit37;
    static final SimpleSymbol Lit38;
    static final SimpleSymbol Lit39;
    static final SimpleSymbol Lit4 = (SimpleSymbol)(new SimpleSymbol("current-error-port")).readResolve();
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final SimpleSymbol Lit42;
    static final SimpleSymbol Lit43;
    static final SimpleSymbol Lit44;
    static final SimpleSymbol Lit45;
    static final Keyword Lit5 = Keyword.make("setter");
    static final IntNum Lit6 = IntNum.make(1);
    static final Char Lit7 = Char.make(10);
    static final Char Lit8 = Char.make(32);
    static final SimpleSymbol Lit9 = (SimpleSymbol)(new SimpleSymbol("trim")).readResolve();
    public static final ModuleMethod call$Mnwith$Mninput$Mnfile;
    public static final ModuleMethod call$Mnwith$Mninput$Mnstring;
    public static final ModuleMethod call$Mnwith$Mnoutput$Mnfile;
    public static final ModuleMethod call$Mnwith$Mnoutput$Mnstring;
    public static final ModuleMethod char$Mnready$Qu;
    public static final ModuleMethod close$Mninput$Mnport;
    public static final ModuleMethod close$Mnoutput$Mnport;
    public static final LocationProc current$Mnerror$Mnport;
    public static final LocationProc current$Mninput$Mnport;
    public static final LocationProc current$Mnoutput$Mnport;
    public static final ModuleMethod default$Mnprompter;
    public static final ModuleMethod display;
    public static final ModuleMethod eof$Mnobject$Qu;
    public static final ModuleMethod force$Mnoutput;
    public static final ModuleMethod get$Mnoutput$Mnstring;
    public static final ModuleMethod input$Mnport$Mncolumn$Mnnumber;
    public static final GenericProc input$Mnport$Mnline$Mnnumber;
    static final ModuleMethod input$Mnport$Mnline$Mnnumber$Fn5;
    public static final GenericProc input$Mnport$Mnprompter;
    static final ModuleMethod input$Mnport$Mnprompter$Fn6;
    public static final ModuleMethod input$Mnport$Mnread$Mnstate;
    public static final ModuleMethod input$Mnport$Qu;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn2;
    static final ModuleMethod lambda$Fn3;
    public static final ModuleMethod newline;
    public static final ModuleMethod open$Mninput$Mnfile;
    public static final ModuleMethod open$Mninput$Mnstring;
    public static final ModuleMethod open$Mnoutput$Mnfile;
    public static final ModuleMethod open$Mnoutput$Mnstring;
    public static final ModuleMethod output$Mnport$Qu;
    public static final ModuleMethod port$Mncolumn;
    public static final GenericProc port$Mnline;
    static final ModuleMethod port$Mnline$Fn4;
    public static final ModuleMethod read;
    public static final ModuleMethod read$Mnline;
    public static final ModuleMethod set$Mninput$Mnport$Mnline$Mnnumber$Ex;
    public static final ModuleMethod set$Mninput$Mnport$Mnprompter$Ex;
    public static final ModuleMethod set$Mnport$Mnline$Ex;
    public static final ModuleMethod transcript$Mnoff;
    public static final ModuleMethod transcript$Mnon;
    public static final ModuleMethod with$Mninput$Mnfrom$Mnfile;
    public static final ModuleMethod with$Mnoutput$Mnto$Mnfile;
    public static final ModuleMethod write;
    public static final ModuleMethod write$Mnchar;

    public ports()
    {
        ModuleInfo.register(this);
    }

    public static Object callWithInputFile(Path path, Procedure procedure)
    {
        InPort inport = openInputFile(path);
        Object obj = procedure.apply1(inport);
        closeInputPort(inport);
        return obj;
        Exception exception;
        exception;
        closeInputPort(inport);
        throw exception;
    }

    public static Object callWithInputString(CharSequence charsequence, Procedure procedure)
    {
        String s;
        CharArrayInPort chararrayinport;
        Object obj;
        if (charsequence == null)
        {
            s = null;
        } else
        {
            s = charsequence.toString();
        }
        chararrayinport = new CharArrayInPort(s);
        obj = procedure.apply1(chararrayinport);
        closeInputPort(chararrayinport);
        return obj;
    }

    public static Object callWithOutputFile(Path path, Procedure procedure)
    {
        OutPort outport = openOutputFile(path);
        Object obj = procedure.apply1(outport);
        closeOutputPort(outport);
        return obj;
        Exception exception;
        exception;
        closeOutputPort(outport);
        throw exception;
    }

    public static FString callWithOutputString(Procedure procedure)
    {
        CharArrayOutPort chararrayoutport = new CharArrayOutPort();
        procedure.apply1(chararrayoutport);
        char ac[] = chararrayoutport.toCharArray();
        chararrayoutport.close();
        return new FString(ac);
    }

    public static Object closeInputPort(InPort inport)
    {
        inport.close();
        return Values.empty;
    }

    public static Object closeOutputPort(OutPort outport)
    {
        outport.close();
        return Values.empty;
    }

    public static Object defaultPrompter(Object obj)
    {
        char c = inputPortReadState(obj);
        if (characters.isChar$Eq(Char.make(c), Lit7))
        {
            return "";
        }
        Object aobj[] = new Object[3];
        Object obj1;
        Object obj2;
        Number number;
        if (characters.isChar$Eq(Char.make(c), Lit8))
        {
            obj1 = "#|kawa:";
        } else
        {
            Object aobj1[] = new Object[3];
            aobj1[0] = "#|";
            aobj1[1] = strings.makeString(1, Char.make(c));
            aobj1[2] = "---:";
            obj1 = strings.stringAppend(aobj1);
        }
        aobj[0] = obj1;
        obj2 = input$Mnport$Mnline$Mnnumber.apply1(obj);
        try
        {
            number = (Number)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "number->string", 0, obj2);
        }
        aobj[1] = numbers.number$To$String(number);
        aobj[2] = "|# ";
        return strings.stringAppend(aobj);
    }

    public static void display(Object obj)
    {
        display(obj, current$Mnoutput$Mnport.apply0());
    }

    public static void display(Object obj, Object obj1)
    {
        AbstractFormat abstractformat = Scheme.displayFormat;
        Consumer consumer;
        try
        {
            consumer = (Consumer)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "gnu.lists.AbstractFormat.format(java.lang.Object,gnu.lists.Consumer)", 3, obj1);
        }
        abstractformat.format(obj, consumer);
    }

    public static void forceOutput()
    {
        forceOutput(current$Mnoutput$Mnport.apply0());
    }

    public static void forceOutput(Object obj)
    {
        Writer writer;
        try
        {
            writer = (Writer)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "java.io.Writer.flush()", 1, obj);
        }
        writer.flush();
    }

    public static FString getOutputString(CharArrayOutPort chararrayoutport)
    {
        return new FString(chararrayoutport.toCharArray());
    }

    public static int inputPortColumnNumber(Object obj)
    {
        return 1 + portColumn(obj);
    }

    public static Object inputPortLineNumber(LineBufferedReader linebufferedreader)
    {
        return AddOp.$Pl.apply2(Lit6, port$Mnline.apply1(linebufferedreader));
    }

    public static Procedure inputPortPrompter(TtyInPort ttyinport)
    {
        return ttyinport.getPrompter();
    }

    public static char inputPortReadState(Object obj)
    {
        InPort inport;
        try
        {
            inport = (InPort)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "gnu.mapping.InPort.getReadState()", 1, obj);
        }
        return inport.getReadState();
    }

    public static boolean isCharReady()
    {
        return isCharReady(current$Mninput$Mnport.apply0());
    }

    public static boolean isCharReady(Object obj)
    {
        return char_ready_p.ready(obj);
    }

    public static boolean isEofObject(Object obj)
    {
        return obj == EofClass.eofValue;
    }

    public static boolean isInputPort(Object obj)
    {
        return obj instanceof InPort;
    }

    public static boolean isOutputPort(Object obj)
    {
        return obj instanceof OutPort;
    }

    static Object lambda1(Object obj)
    {
        InPort inport;
        try
        {
            inport = (InPort)obj;
        }
        catch (ClassCastException classcastexception)
        {
            WrongType wrongtype = WrongType.make(classcastexception, current$Mninput$Mnport, 1, obj);
            wrongtype.expectedType = Lit1;
            throw (Throwable)wrongtype;
        }
        return inport;
    }

    static Object lambda2(Object obj)
    {
        OutPort outport;
        try
        {
            outport = (OutPort)obj;
        }
        catch (ClassCastException classcastexception)
        {
            WrongType wrongtype = WrongType.make(classcastexception, current$Mnoutput$Mnport, 1, obj);
            wrongtype.expectedType = Lit3;
            throw (Throwable)wrongtype;
        }
        return outport;
    }

    static Object lambda3(Object obj)
    {
        OutPort outport;
        try
        {
            outport = (OutPort)obj;
        }
        catch (ClassCastException classcastexception)
        {
            WrongType wrongtype = WrongType.make(classcastexception, current$Mnerror$Mnport, 1, obj);
            wrongtype.expectedType = Lit3;
            throw (Throwable)wrongtype;
        }
        return outport;
    }

    public static void newline()
    {
        newline(current$Mnoutput$Mnport.apply0());
    }

    public static void newline(Object obj)
    {
        OutPort outport;
        try
        {
            outport = (OutPort)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "gnu.mapping.OutPort.println()", 1, obj);
        }
        outport.println();
    }

    public static InPort openInputFile(Path path)
    {
        return InPort.openFile(path);
    }

    public static InPort openInputString(CharSequence charsequence)
    {
        String s;
        if (charsequence == null)
        {
            s = null;
        } else
        {
            s = charsequence.toString();
        }
        return new CharArrayInPort(s);
    }

    public static OutPort openOutputFile(Path path)
    {
        return OutPort.openFile(path);
    }

    public static CharArrayOutPort openOutputString()
    {
        return new CharArrayOutPort();
    }

    public static int portColumn(Object obj)
    {
        LineBufferedReader linebufferedreader;
        try
        {
            linebufferedreader = (LineBufferedReader)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "gnu.text.LineBufferedReader.getColumnNumber()", 1, obj);
        }
        return linebufferedreader.getColumnNumber();
    }

    public static int portLine(LineBufferedReader linebufferedreader)
    {
        return linebufferedreader.getLineNumber();
    }

    public static Object read()
    {
        return read((InPort)current$Mninput$Mnport.apply0());
    }

    public static Object read(InPort inport)
    {
        LispReader lispreader = new LispReader(inport);
        Object obj;
        try
        {
            obj = lispreader.readObject();
            if (lispreader.seenErrors())
            {
                throw (Throwable)new SyntaxException(lispreader.getMessages());
            }
        }
        catch (SyntaxException syntaxexception)
        {
            syntaxexception.setHeader("syntax error in read:");
            throw (Throwable)syntaxexception;
        }
        return obj;
    }

    public static Object readLine()
    {
        return readLine((LineBufferedReader)current$Mninput$Mnport.apply0(), ((Symbol) (Lit9)));
    }

    public static Object readLine(LineBufferedReader linebufferedreader)
    {
        return readLine(linebufferedreader, ((Symbol) (Lit9)));
    }

    public static Object readLine(LineBufferedReader linebufferedreader, Symbol symbol)
    {
        String s;
        if (symbol == null)
        {
            s = null;
        } else
        {
            s = symbol.toString();
        }
        return read_line.apply(linebufferedreader, s);
    }

    public static void setInputPortLineNumber$Ex(Object obj, Object obj1)
    {
        setPortLine$Ex(obj, AddOp.$Mn.apply2(obj1, Lit6));
    }

    public static void setInputPortPrompter$Ex(TtyInPort ttyinport, Procedure procedure)
    {
        ttyinport.setPrompter(procedure);
    }

    public static void setPortLine$Ex(Object obj, Object obj1)
    {
        LineBufferedReader linebufferedreader;
        int i;
        try
        {
            linebufferedreader = (LineBufferedReader)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "gnu.text.LineBufferedReader.setLineNumber(int)", 1, obj);
        }
        try
        {
            i = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "gnu.text.LineBufferedReader.setLineNumber(int)", 2, obj1);
        }
        linebufferedreader.setLineNumber(i);
    }

    public static void transcriptOff()
    {
        OutPort.closeLogFile();
    }

    public static void transcriptOn(Object obj)
    {
        OutPort.setLogFile(obj.toString());
    }

    public static Object withInputFromFile(Path path, Procedure procedure)
    {
        InPort inport;
        InPort inport1;
        inport = InPort.openFile(path);
        inport1 = InPort.inDefault();
        Object obj;
        InPort.setInDefault(inport);
        obj = procedure.apply0();
        InPort.setInDefault(inport1);
        inport.close();
        return obj;
        Exception exception;
        exception;
        InPort.setInDefault(inport1);
        inport.close();
        throw exception;
    }

    public static Object withOutputToFile(Path path, Procedure procedure)
    {
        OutPort outport;
        OutPort outport1;
        outport = OutPort.openFile(path);
        outport1 = OutPort.outDefault();
        Object obj;
        OutPort.setOutDefault(outport);
        obj = procedure.apply0();
        OutPort.setOutDefault(outport1);
        outport.close();
        return obj;
        Exception exception;
        exception;
        OutPort.setOutDefault(outport1);
        outport.close();
        throw exception;
    }

    public static void write(Object obj)
    {
        write(obj, current$Mnoutput$Mnport.apply0());
    }

    public static void write(Object obj, Object obj1)
    {
        AbstractFormat abstractformat = Scheme.writeFormat;
        Consumer consumer;
        try
        {
            consumer = (Consumer)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "gnu.lists.AbstractFormat.format(java.lang.Object,gnu.lists.Consumer)", 3, obj1);
        }
        abstractformat.format(obj, consumer);
    }

    public static void writeChar(Object obj)
    {
        writeChar(obj, OutPort.outDefault());
    }

    public static void writeChar(Object obj, OutPort outport)
    {
        Char char1;
        try
        {
            char1 = (Char)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "char->integer", 1, obj);
        }
        Char.print(characters.char$To$Integer(char1), outport);
    }

    public Object apply0(ModuleMethod modulemethod)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply0(modulemethod);

        case 15: // '\017'
            return openOutputString();

        case 19: // '\023'
            forceOutput();
            return Values.empty;

        case 21: // '\025'
            newline();
            return Values.empty;

        case 24: // '\030'
            if (isCharReady())
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 42: // '*'
            return read();

        case 44: // ','
            return readLine();

        case 48: // '0'
            transcriptOff();
            return Values.empty;
        }
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 13: // '\r'
        case 15: // '\017'
        case 17: // '\021'
        case 20: // '\024'
        case 22: // '\026'
        case 25: // '\031'
        case 27: // '\033'
        case 29: // '\035'
        case 31: // '\037'
        case 33: // '!'
        case 38: // '&'
        case 43: // '+'
        case 45: // '-'
        case 46: // '.'
        default:
            return super.apply1(modulemethod, obj);

        case 1: // '\001'
            LineBufferedReader linebufferedreader;
            InPort inport;
            OutPort outport;
            InPort inport1;
            TtyInPort ttyinport;
            LineBufferedReader linebufferedreader1;
            LineBufferedReader linebufferedreader2;
            Procedure procedure;
            CharArrayOutPort chararrayoutport;
            CharSequence charsequence;
            Path path;
            Path path1;
            try
            {
                path1 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "open-input-file", 1, obj);
            }
            return openInputFile(path1);

        case 2: // '\002'
            try
            {
                path = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "open-output-file", 1, obj);
            }
            return openOutputFile(path);

        case 7: // '\007'
            if (isInputPort(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 8: // '\b'
            if (isOutputPort(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 9: // '\t'
            return lambda1(obj);

        case 10: // '\n'
            return lambda2(obj);

        case 11: // '\013'
            return lambda3(obj);

        case 12: // '\f'
            writeChar(obj);
            return Values.empty;

        case 14: // '\016'
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "open-input-string", 1, obj);
            }
            return openInputString(charsequence);

        case 16: // '\020'
            try
            {
                chararrayoutport = (CharArrayOutPort)obj;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "get-output-string", 1, obj);
            }
            return getOutputString(chararrayoutport);

        case 18: // '\022'
            try
            {
                procedure = (Procedure)obj;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "call-with-output-string", 1, obj);
            }
            return callWithOutputString(procedure);

        case 19: // '\023'
            forceOutput(obj);
            return Values.empty;

        case 21: // '\025'
            newline(obj);
            return Values.empty;

        case 23: // '\027'
            if (isEofObject(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 24: // '\030'
            if (isCharReady(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 26: // '\032'
            write(obj);
            return Values.empty;

        case 28: // '\034'
            display(obj);
            return Values.empty;

        case 30: // '\036'
            return Char.make(inputPortReadState(obj));

        case 32: // ' '
            try
            {
                linebufferedreader2 = (LineBufferedReader)obj;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "port-line", 1, obj);
            }
            return Integer.valueOf(portLine(linebufferedreader2));

        case 34: // '"'
            try
            {
                linebufferedreader1 = (LineBufferedReader)obj;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "input-port-line-number", 1, obj);
            }
            return inputPortLineNumber(linebufferedreader1);

        case 35: // '#'
            return Integer.valueOf(portColumn(obj));

        case 36: // '$'
            return Integer.valueOf(inputPortColumnNumber(obj));

        case 37: // '%'
            return defaultPrompter(obj);

        case 39: // '\''
            try
            {
                ttyinport = (TtyInPort)obj;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "input-port-prompter", 1, obj);
            }
            return inputPortPrompter(ttyinport);

        case 40: // '('
            try
            {
                inport1 = (InPort)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "close-input-port", 1, obj);
            }
            return closeInputPort(inport1);

        case 41: // ')'
            try
            {
                outport = (OutPort)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "close-output-port", 1, obj);
            }
            return closeOutputPort(outport);

        case 42: // '*'
            try
            {
                inport = (InPort)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "read", 1, obj);
            }
            return read(inport);

        case 44: // ','
            try
            {
                linebufferedreader = (LineBufferedReader)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "read-line", 1, obj);
            }
            return readLine(linebufferedreader);

        case 47: // '/'
            transcriptOn(obj);
            return Values.empty;
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 3: // '\003'
            LineBufferedReader linebufferedreader;
            Symbol symbol;
            TtyInPort ttyinport;
            Procedure procedure;
            CharSequence charsequence;
            Procedure procedure1;
            OutPort outport;
            Path path;
            Procedure procedure2;
            Path path1;
            Procedure procedure3;
            Path path2;
            Procedure procedure4;
            Path path3;
            Procedure procedure5;
            try
            {
                path3 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception13)
            {
                throw new WrongType(classcastexception13, "call-with-input-file", 1, obj);
            }
            try
            {
                procedure5 = (Procedure)obj1;
            }
            catch (ClassCastException classcastexception14)
            {
                throw new WrongType(classcastexception14, "call-with-input-file", 2, obj1);
            }
            return callWithInputFile(path3, procedure5);

        case 4: // '\004'
            try
            {
                path2 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "call-with-output-file", 1, obj);
            }
            try
            {
                procedure4 = (Procedure)obj1;
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "call-with-output-file", 2, obj1);
            }
            return callWithOutputFile(path2, procedure4);

        case 5: // '\005'
            try
            {
                path1 = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "with-input-from-file", 1, obj);
            }
            try
            {
                procedure3 = (Procedure)obj1;
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "with-input-from-file", 2, obj1);
            }
            return withInputFromFile(path1, procedure3);

        case 6: // '\006'
            try
            {
                path = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "with-output-to-file", 1, obj);
            }
            try
            {
                procedure2 = (Procedure)obj1;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "with-output-to-file", 2, obj1);
            }
            return withOutputToFile(path, procedure2);

        case 12: // '\f'
            try
            {
                outport = (OutPort)obj1;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "write-char", 2, obj1);
            }
            writeChar(obj, outport);
            return Values.empty;

        case 17: // '\021'
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "call-with-input-string", 1, obj);
            }
            try
            {
                procedure1 = (Procedure)obj1;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "call-with-input-string", 2, obj1);
            }
            return callWithInputString(charsequence, procedure1);

        case 26: // '\032'
            write(obj, obj1);
            return Values.empty;

        case 28: // '\034'
            display(obj, obj1);
            return Values.empty;

        case 31: // '\037'
            setPortLine$Ex(obj, obj1);
            return Values.empty;

        case 33: // '!'
            setInputPortLineNumber$Ex(obj, obj1);
            return Values.empty;

        case 38: // '&'
            try
            {
                ttyinport = (TtyInPort)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "set-input-port-prompter!", 1, obj);
            }
            try
            {
                procedure = (Procedure)obj1;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "set-input-port-prompter!", 2, obj1);
            }
            setInputPortPrompter$Ex(ttyinport, procedure);
            return Values.empty;

        case 44: // ','
            break;
        }
        try
        {
            linebufferedreader = (LineBufferedReader)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "read-line", 1, obj);
        }
        try
        {
            symbol = (Symbol)obj1;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "read-line", 2, obj1);
        }
        return readLine(linebufferedreader, symbol);
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match0(modulemethod, callcontext);

        case 48: // '0'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 44: // ','
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 42: // '*'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 24: // '\030'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 21: // '\025'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 19: // '\023'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 15: // '\017'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 3: // '\003'
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 13: // '\r'
        case 15: // '\017'
        case 17: // '\021'
        case 20: // '\024'
        case 22: // '\026'
        case 25: // '\031'
        case 27: // '\033'
        case 29: // '\035'
        case 31: // '\037'
        case 33: // '!'
        case 38: // '&'
        case 43: // '+'
        case 45: // '-'
        case 46: // '.'
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 47: // '/'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 44: // ','
            if (!(obj instanceof LineBufferedReader))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 42: // '*'
            if (!(obj instanceof InPort))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 41: // ')'
            if (!(obj instanceof OutPort))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 40: // '('
            if (!(obj instanceof InPort))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 39: // '\''
            if (!(obj instanceof TtyInPort))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 37: // '%'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 36: // '$'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 35: // '#'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 34: // '"'
            if (!(obj instanceof LineBufferedReader))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 32: // ' '
            if (!(obj instanceof LineBufferedReader))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 30: // '\036'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 28: // '\034'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 26: // '\032'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 24: // '\030'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 23: // '\027'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 21: // '\025'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 19: // '\023'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 18: // '\022'
            if (!(obj instanceof Procedure))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 16: // '\020'
            if (!(obj instanceof CharArrayOutPort))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 14: // '\016'
            if (obj instanceof CharSequence)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 12: // '\f'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 11: // '\013'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 10: // '\n'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 9: // '\t'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 8: // '\b'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 7: // '\007'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 2: // '\002'
            if (Path.coerceToPathOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 1: // '\001'
            break;
        }
        if (Path.coerceToPathOrNull(obj) != null)
        {
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        } else
        {
            return 0xfff40001;
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        int i = 0xfff40001;
        modulemethod.selector;
        JVM INSTR lookupswitch 12: default 116
    //                   3: 535
    //                   4: 491
    //                   5: 447
    //                   6: 403
    //                   12: 366
    //                   17: 322
    //                   26: 296
    //                   28: 270
    //                   31: 244
    //                   33: 218
    //                   38: 174
    //                   44: 130;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13
_L1:
        i = super.match2(modulemethod, obj, obj1, callcontext);
_L15:
        return i;
_L13:
        if (obj instanceof LineBufferedReader)
        {
            callcontext.value1 = obj;
            if (!(obj1 instanceof Symbol))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }
        continue; /* Loop/switch isn't completed */
_L12:
        if (obj instanceof TtyInPort)
        {
            callcontext.value1 = obj;
            if (!(obj1 instanceof Procedure))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }
        continue; /* Loop/switch isn't completed */
_L11:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.proc = modulemethod;
        callcontext.pc = 2;
        return 0;
_L10:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.proc = modulemethod;
        callcontext.pc = 2;
        return 0;
_L9:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.proc = modulemethod;
        callcontext.pc = 2;
        return 0;
_L8:
        callcontext.value1 = obj;
        callcontext.value2 = obj1;
        callcontext.proc = modulemethod;
        callcontext.pc = 2;
        return 0;
_L7:
        if (obj instanceof CharSequence)
        {
            callcontext.value1 = obj;
            if (!(obj1 instanceof Procedure))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }
        continue; /* Loop/switch isn't completed */
_L6:
        callcontext.value1 = obj;
        if (!(obj1 instanceof OutPort))
        {
            return 0xfff40002;
        } else
        {
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        }
_L5:
        if (Path.coerceToPathOrNull(obj) != null)
        {
            callcontext.value1 = obj;
            if (!(obj1 instanceof Procedure))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (Path.coerceToPathOrNull(obj) != null)
        {
            callcontext.value1 = obj;
            if (!(obj1 instanceof Procedure))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (Path.coerceToPathOrNull(obj) != null)
        {
            callcontext.value1 = obj;
            if (!(obj1 instanceof Procedure))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (Path.coerceToPathOrNull(obj) != null)
        {
            callcontext.value1 = obj;
            if (!(obj1 instanceof Procedure))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }
        if (true) goto _L15; else goto _L14
_L14:
    }

    public final void run(CallContext callcontext)
    {
        Consumer _tmp = callcontext.consumer;
        current$Mninput$Mnport = LocationProc.makeNamed(Lit0, InPort.inLocation);
        current$Mninput$Mnport.pushConverter(lambda$Fn1);
        current$Mnoutput$Mnport = LocationProc.makeNamed(Lit2, OutPort.outLocation);
        current$Mnoutput$Mnport.pushConverter(lambda$Fn2);
        current$Mnerror$Mnport = LocationProc.makeNamed(Lit4, OutPort.errLocation);
        current$Mnerror$Mnport.pushConverter(lambda$Fn3);
        port$Mnline = new GenericProc("port-line");
        GenericProc genericproc = port$Mnline;
        Object aobj[] = new Object[3];
        aobj[0] = Lit5;
        aobj[1] = set$Mnport$Mnline$Ex;
        ModuleMethod _tmp1 = port$Mnline$Fn4;
        aobj[2] = port$Mnline$Fn4;
        genericproc.setProperties(aobj);
        input$Mnport$Mnline$Mnnumber = new GenericProc("input-port-line-number");
        GenericProc genericproc1 = input$Mnport$Mnline$Mnnumber;
        Object aobj1[] = new Object[3];
        aobj1[0] = Lit5;
        aobj1[1] = set$Mninput$Mnport$Mnline$Mnnumber$Ex;
        ModuleMethod _tmp2 = input$Mnport$Mnline$Mnnumber$Fn5;
        aobj1[2] = input$Mnport$Mnline$Mnnumber$Fn5;
        genericproc1.setProperties(aobj1);
        input$Mnport$Mnprompter = new GenericProc("input-port-prompter");
        GenericProc genericproc2 = input$Mnport$Mnprompter;
        Object aobj2[] = new Object[3];
        aobj2[0] = Lit5;
        aobj2[1] = set$Mninput$Mnport$Mnprompter$Ex;
        ModuleMethod _tmp3 = input$Mnport$Mnprompter$Fn6;
        aobj2[2] = input$Mnport$Mnprompter$Fn6;
        genericproc2.setProperties(aobj2);
    }

    static 
    {
        Lit45 = (SimpleSymbol)(new SimpleSymbol("transcript-off")).readResolve();
        Lit44 = (SimpleSymbol)(new SimpleSymbol("transcript-on")).readResolve();
        Lit43 = (SimpleSymbol)(new SimpleSymbol("read-line")).readResolve();
        Lit42 = (SimpleSymbol)(new SimpleSymbol("read")).readResolve();
        Lit41 = (SimpleSymbol)(new SimpleSymbol("close-output-port")).readResolve();
        Lit40 = (SimpleSymbol)(new SimpleSymbol("close-input-port")).readResolve();
        Lit39 = (SimpleSymbol)(new SimpleSymbol("input-port-prompter")).readResolve();
        Lit38 = (SimpleSymbol)(new SimpleSymbol("set-input-port-prompter!")).readResolve();
        Lit37 = (SimpleSymbol)(new SimpleSymbol("default-prompter")).readResolve();
        Lit36 = (SimpleSymbol)(new SimpleSymbol("input-port-column-number")).readResolve();
        Lit35 = (SimpleSymbol)(new SimpleSymbol("port-column")).readResolve();
        Lit34 = (SimpleSymbol)(new SimpleSymbol("input-port-line-number")).readResolve();
        Lit33 = (SimpleSymbol)(new SimpleSymbol("set-input-port-line-number!")).readResolve();
        Lit32 = (SimpleSymbol)(new SimpleSymbol("port-line")).readResolve();
        Lit31 = (SimpleSymbol)(new SimpleSymbol("set-port-line!")).readResolve();
        Lit30 = (SimpleSymbol)(new SimpleSymbol("input-port-read-state")).readResolve();
        Lit29 = (SimpleSymbol)(new SimpleSymbol("display")).readResolve();
        Lit28 = (SimpleSymbol)(new SimpleSymbol("write")).readResolve();
        Lit27 = (SimpleSymbol)(new SimpleSymbol("char-ready?")).readResolve();
        Lit26 = (SimpleSymbol)(new SimpleSymbol("eof-object?")).readResolve();
        Lit25 = (SimpleSymbol)(new SimpleSymbol("newline")).readResolve();
        Lit24 = (SimpleSymbol)(new SimpleSymbol("force-output")).readResolve();
        Lit23 = (SimpleSymbol)(new SimpleSymbol("call-with-output-string")).readResolve();
        Lit22 = (SimpleSymbol)(new SimpleSymbol("call-with-input-string")).readResolve();
        Lit21 = (SimpleSymbol)(new SimpleSymbol("get-output-string")).readResolve();
        Lit20 = (SimpleSymbol)(new SimpleSymbol("open-output-string")).readResolve();
        Lit19 = (SimpleSymbol)(new SimpleSymbol("open-input-string")).readResolve();
        Lit18 = (SimpleSymbol)(new SimpleSymbol("write-char")).readResolve();
        Lit17 = (SimpleSymbol)(new SimpleSymbol("output-port?")).readResolve();
        Lit16 = (SimpleSymbol)(new SimpleSymbol("input-port?")).readResolve();
        Lit15 = (SimpleSymbol)(new SimpleSymbol("with-output-to-file")).readResolve();
        Lit14 = (SimpleSymbol)(new SimpleSymbol("with-input-from-file")).readResolve();
        Lit13 = (SimpleSymbol)(new SimpleSymbol("call-with-output-file")).readResolve();
        Lit12 = (SimpleSymbol)(new SimpleSymbol("call-with-input-file")).readResolve();
        Lit11 = (SimpleSymbol)(new SimpleSymbol("open-output-file")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("open-input-file")).readResolve();
        $instance = new ports();
        ports ports1 = $instance;
        open$Mninput$Mnfile = new ModuleMethod(ports1, 1, Lit10, 4097);
        open$Mnoutput$Mnfile = new ModuleMethod(ports1, 2, Lit11, 4097);
        call$Mnwith$Mninput$Mnfile = new ModuleMethod(ports1, 3, Lit12, 8194);
        call$Mnwith$Mnoutput$Mnfile = new ModuleMethod(ports1, 4, Lit13, 8194);
        with$Mninput$Mnfrom$Mnfile = new ModuleMethod(ports1, 5, Lit14, 8194);
        with$Mnoutput$Mnto$Mnfile = new ModuleMethod(ports1, 6, Lit15, 8194);
        input$Mnport$Qu = new ModuleMethod(ports1, 7, Lit16, 4097);
        output$Mnport$Qu = new ModuleMethod(ports1, 8, Lit17, 4097);
        lambda$Fn1 = new ModuleMethod(ports1, 9, null, 4097);
        lambda$Fn2 = new ModuleMethod(ports1, 10, null, 4097);
        lambda$Fn3 = new ModuleMethod(ports1, 11, null, 4097);
        write$Mnchar = new ModuleMethod(ports1, 12, Lit18, 8193);
        open$Mninput$Mnstring = new ModuleMethod(ports1, 14, Lit19, 4097);
        open$Mnoutput$Mnstring = new ModuleMethod(ports1, 15, Lit20, 0);
        get$Mnoutput$Mnstring = new ModuleMethod(ports1, 16, Lit21, 4097);
        call$Mnwith$Mninput$Mnstring = new ModuleMethod(ports1, 17, Lit22, 8194);
        call$Mnwith$Mnoutput$Mnstring = new ModuleMethod(ports1, 18, Lit23, 4097);
        force$Mnoutput = new ModuleMethod(ports1, 19, Lit24, 4096);
        newline = new ModuleMethod(ports1, 21, Lit25, 4096);
        eof$Mnobject$Qu = new ModuleMethod(ports1, 23, Lit26, 4097);
        char$Mnready$Qu = new ModuleMethod(ports1, 24, Lit27, 4096);
        write = new ModuleMethod(ports1, 26, Lit28, 8193);
        display = new ModuleMethod(ports1, 28, Lit29, 8193);
        input$Mnport$Mnread$Mnstate = new ModuleMethod(ports1, 30, Lit30, 4097);
        set$Mnport$Mnline$Ex = new ModuleMethod(ports1, 31, Lit31, 8194);
        port$Mnline$Fn4 = new ModuleMethod(ports1, 32, Lit32, 4097);
        set$Mninput$Mnport$Mnline$Mnnumber$Ex = new ModuleMethod(ports1, 33, Lit33, 8194);
        input$Mnport$Mnline$Mnnumber$Fn5 = new ModuleMethod(ports1, 34, Lit34, 4097);
        port$Mncolumn = new ModuleMethod(ports1, 35, Lit35, 4097);
        input$Mnport$Mncolumn$Mnnumber = new ModuleMethod(ports1, 36, Lit36, 4097);
        default$Mnprompter = new ModuleMethod(ports1, 37, Lit37, 4097);
        set$Mninput$Mnport$Mnprompter$Ex = new ModuleMethod(ports1, 38, Lit38, 8194);
        input$Mnport$Mnprompter$Fn6 = new ModuleMethod(ports1, 39, Lit39, 4097);
        close$Mninput$Mnport = new ModuleMethod(ports1, 40, Lit40, 4097);
        close$Mnoutput$Mnport = new ModuleMethod(ports1, 41, Lit41, 4097);
        read = new ModuleMethod(ports1, 42, Lit42, 4096);
        read$Mnline = new ModuleMethod(ports1, 44, Lit43, 8192);
        transcript$Mnon = new ModuleMethod(ports1, 47, Lit44, 4097);
        transcript$Mnoff = new ModuleMethod(ports1, 48, Lit45, 0);
        $instance.run();
    }
}
