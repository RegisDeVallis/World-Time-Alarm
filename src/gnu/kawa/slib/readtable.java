// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.ReadTable;
import gnu.kawa.lispexpr.ReaderDispatch;
import gnu.kawa.lispexpr.ReaderDispatchMacro;
import gnu.kawa.lispexpr.ReaderMacro;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Char;

public class readtable extends ModuleBody
{

    public static final readtable $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    public static final ModuleMethod current$Mnreadtable;
    public static final ModuleMethod define$Mnreader$Mnctor;
    public static final ModuleMethod get$Mndispatch$Mnmacro$Mntable;
    public static final ModuleMethod make$Mndispatch$Mnmacro$Mncharacter;
    public static final ModuleMethod readtable$Qu;
    public static final ModuleMethod set$Mndispatch$Mnmacro$Mncharacter;
    public static final ModuleMethod set$Mnmacro$Mncharacter;

    public readtable()
    {
        ModuleInfo.register(this);
    }

    public static ReadTable currentReadtable()
    {
        return ReadTable.getCurrent();
    }

    public static void defineReaderCtor(Symbol symbol, Procedure procedure)
    {
        defineReaderCtor(symbol, procedure, currentReadtable());
    }

    public static void defineReaderCtor(Symbol symbol, Procedure procedure, ReadTable readtable1)
    {
        String s;
        if (symbol == null)
        {
            s = null;
        } else
        {
            s = symbol.toString();
        }
        readtable1.putReaderCtor(s, procedure);
    }

    public static Object getDispatchMacroTable(char c, char c1)
    {
        return getDispatchMacroTable(c, c1, currentReadtable());
    }

    public static Object getDispatchMacroTable(char c, char c1, ReadTable readtable1)
    {
        gnu.kawa.lispexpr.ReadTableEntry readtableentry = readtable1.lookup(c);
        ReaderDispatch readerdispatch;
        Object obj;
        try
        {
            readerdispatch = (ReaderDispatch)readtableentry;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "disp-entry", -2, readtableentry);
        }
        obj = readerdispatch.lookup(c1);
        if (obj == null)
        {
            obj = Boolean.FALSE;
        }
        return obj;
    }

    public static boolean isReadtable(Object obj)
    {
        return obj instanceof ReadTable;
    }

    public static void makeDispatchMacroCharacter(char c)
    {
        makeDispatchMacroCharacter(c, false);
    }

    public static void makeDispatchMacroCharacter(char c, boolean flag)
    {
        makeDispatchMacroCharacter(c, flag, currentReadtable());
    }

    public static void makeDispatchMacroCharacter(char c, boolean flag, ReadTable readtable1)
    {
        readtable1.set(c, new ReaderDispatch(flag));
    }

    public static void setDispatchMacroCharacter(char c, char c1, Object obj)
    {
        setDispatchMacroCharacter(c, c1, obj, currentReadtable());
    }

    public static void setDispatchMacroCharacter(char c, char c1, Object obj, ReadTable readtable1)
    {
        gnu.kawa.lispexpr.ReadTableEntry readtableentry = readtable1.lookup(c);
        ReaderDispatch readerdispatch;
        Procedure procedure;
        try
        {
            readerdispatch = (ReaderDispatch)readtableentry;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "entry", -2, readtableentry);
        }
        try
        {
            procedure = (Procedure)obj;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "gnu.kawa.lispexpr.ReaderDispatchMacro.<init>(gnu.mapping.Procedure)", 1, obj);
        }
        readerdispatch.set(c1, new ReaderDispatchMacro(procedure));
    }

    public static void setMacroCharacter(char c, Object obj)
    {
        setMacroCharacter(c, obj, false);
    }

    public static void setMacroCharacter(char c, Object obj, boolean flag)
    {
        setMacroCharacter(c, obj, flag, currentReadtable());
    }

    public static void setMacroCharacter(char c, Object obj, boolean flag, ReadTable readtable1)
    {
        Procedure procedure;
        try
        {
            procedure = (Procedure)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "gnu.kawa.lispexpr.ReaderMacro.<init>(gnu.mapping.Procedure,boolean)", 1, obj);
        }
        readtable1.set(c, new ReaderMacro(procedure, flag));
    }

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 1)
        {
            return currentReadtable();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply1(modulemethod, obj);

        case 2: // '\002'
            if (isReadtable(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 6: // '\006'
            break;
        }
        char c;
        try
        {
            c = ((Char)obj).charValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "make-dispatch-macro-character", 1, obj);
        }
        makeDispatchMacroCharacter(c);
        return Values.empty;
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 3: // '\003'
            Symbol symbol;
            Procedure procedure;
            char c;
            char c1;
            char c2;
            Boolean boolean1;
            boolean flag;
            char c3;
            try
            {
                c3 = ((Char)obj).charValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "set-macro-character", 1, obj);
            }
            setMacroCharacter(c3, obj1);
            return Values.empty;

        case 6: // '\006'
            try
            {
                c2 = ((Char)obj).charValue();
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "make-dispatch-macro-character", 1, obj);
            }
            try
            {
                boolean1 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "make-dispatch-macro-character", 2, obj1);
            }
            if (obj1 != boolean1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            makeDispatchMacroCharacter(c2, flag);
            return Values.empty;

        case 11: // '\013'
            try
            {
                c = ((Char)obj).charValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "get-dispatch-macro-table", 1, obj);
            }
            try
            {
                c1 = ((Char)obj1).charValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "get-dispatch-macro-table", 2, obj1);
            }
            return getDispatchMacroTable(c, c1);

        case 13: // '\r'
            break;
        }
        try
        {
            symbol = (Symbol)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "define-reader-ctor", 1, obj);
        }
        try
        {
            procedure = (Procedure)obj1;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "define-reader-ctor", 2, obj1);
        }
        defineReaderCtor(symbol, procedure);
        return Values.empty;
    }

    public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
    {
        boolean flag = true;
        switch (modulemethod.selector)
        {
        case 4: // '\004'
        case 5: // '\005'
        case 7: // '\007'
        case 8: // '\b'
        case 10: // '\n'
        case 12: // '\f'
        default:
            return super.apply3(modulemethod, obj, obj1, obj2);

        case 3: // '\003'
            Symbol symbol;
            Procedure procedure;
            ReadTable readtable1;
            char c;
            char c1;
            ReadTable readtable2;
            char c2;
            char c3;
            char c4;
            Boolean boolean1;
            ReadTable readtable3;
            char c5;
            Boolean boolean2;
            boolean flag1;
            try
            {
                c5 = ((Char)obj).charValue();
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "set-macro-character", flag, obj);
            }
            try
            {
                boolean2 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "set-macro-character", 3, obj2);
            }
            if (obj2 != boolean2)
            {
                flag1 = flag;
            } else
            {
                flag1 = false;
            }
            setMacroCharacter(c5, obj1, flag1);
            return Values.empty;

        case 6: // '\006'
            try
            {
                c4 = ((Char)obj).charValue();
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "make-dispatch-macro-character", flag, obj);
            }
            try
            {
                boolean1 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "make-dispatch-macro-character", 2, obj1);
            }
            if (obj1 == boolean1)
            {
                flag = false;
            }
            try
            {
                readtable3 = (ReadTable)obj2;
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "make-dispatch-macro-character", 3, obj2);
            }
            makeDispatchMacroCharacter(c4, flag, readtable3);
            return Values.empty;

        case 9: // '\t'
            try
            {
                c2 = ((Char)obj).charValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "set-dispatch-macro-character", flag, obj);
            }
            try
            {
                c3 = ((Char)obj1).charValue();
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "set-dispatch-macro-character", 2, obj1);
            }
            setDispatchMacroCharacter(c2, c3, obj2);
            return Values.empty;

        case 11: // '\013'
            try
            {
                c = ((Char)obj).charValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "get-dispatch-macro-table", flag, obj);
            }
            try
            {
                c1 = ((Char)obj1).charValue();
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "get-dispatch-macro-table", 2, obj1);
            }
            try
            {
                readtable2 = (ReadTable)obj2;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "get-dispatch-macro-table", 3, obj2);
            }
            return getDispatchMacroTable(c, c1, readtable2);

        case 13: // '\r'
            break;
        }
        try
        {
            symbol = (Symbol)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "define-reader-ctor", flag, obj);
        }
        try
        {
            procedure = (Procedure)obj1;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "define-reader-ctor", 2, obj1);
        }
        try
        {
            readtable1 = (ReadTable)obj2;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "define-reader-ctor", 3, obj2);
        }
        defineReaderCtor(symbol, procedure, readtable1);
        return Values.empty;
    }

    public Object apply4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply4(modulemethod, obj, obj1, obj2, obj3);

        case 3: // '\003'
            char c;
            char c1;
            ReadTable readtable1;
            char c2;
            Boolean boolean1;
            boolean flag;
            ReadTable readtable2;
            try
            {
                c2 = ((Char)obj).charValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "set-macro-character", 1, obj);
            }
            try
            {
                boolean1 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "set-macro-character", 3, obj2);
            }
            if (obj2 != boolean1)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            try
            {
                readtable2 = (ReadTable)obj3;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "set-macro-character", 4, obj3);
            }
            setMacroCharacter(c2, obj1, flag, readtable2);
            return Values.empty;

        case 9: // '\t'
            break;
        }
        try
        {
            c = ((Char)obj).charValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "set-dispatch-macro-character", 1, obj);
        }
        try
        {
            c1 = ((Char)obj1).charValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "set-dispatch-macro-character", 2, obj1);
        }
        try
        {
            readtable1 = (ReadTable)obj3;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "set-dispatch-macro-character", 4, obj3);
        }
        setDispatchMacroCharacter(c, c1, obj2, readtable1);
        return Values.empty;
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 1)
        {
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;
        } else
        {
            return super.match0(modulemethod, callcontext);
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 6: // '\006'
            if (obj instanceof Char)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 2: // '\002'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        int i = 0xfff40001;
        modulemethod.selector;
        JVM INSTR lookupswitch 4: default 52
    //                   3: 187
    //                   6: 154
    //                   11: 110
    //                   13: 66;
           goto _L1 _L2 _L3 _L4 _L5
_L1:
        i = super.match2(modulemethod, obj, obj1, callcontext);
_L7:
        return i;
_L5:
        if (obj instanceof Symbol)
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
        if (obj instanceof Char)
        {
            callcontext.value1 = obj;
            if (obj1 instanceof Char)
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40002;
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (obj instanceof Char)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (obj instanceof Char)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        }
        if (true) goto _L7; else goto _L6
_L6:
    }

    public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        int i = 0xfff40001;
        modulemethod.selector;
        JVM INSTR tableswitch 3 13: default 68
    //                   3 313
    //                   4 68
    //                   5 68
    //                   6 261
    //                   7 68
    //                   8 68
    //                   9 210
    //                   10 68
    //                   11 147
    //                   12 68
    //                   13 84;
           goto _L1 _L2 _L1 _L1 _L3 _L1 _L1 _L4 _L1 _L5 _L1 _L6
_L1:
        i = super.match3(modulemethod, obj, obj1, obj2, callcontext);
_L8:
        return i;
_L6:
        if (obj instanceof Symbol)
        {
            callcontext.value1 = obj;
            if (!(obj1 instanceof Procedure))
            {
                return 0xfff40002;
            }
            callcontext.value2 = obj1;
            if (!(obj2 instanceof ReadTable))
            {
                return 0xfff40003;
            } else
            {
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            }
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (obj instanceof Char)
        {
            callcontext.value1 = obj;
            if (obj1 instanceof Char)
            {
                callcontext.value2 = obj1;
                if (!(obj2 instanceof ReadTable))
                {
                    return 0xfff40003;
                } else
                {
                    callcontext.value3 = obj2;
                    callcontext.proc = modulemethod;
                    callcontext.pc = 3;
                    return 0;
                }
            } else
            {
                return 0xfff40002;
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (obj instanceof Char)
        {
            callcontext.value1 = obj;
            if (obj1 instanceof Char)
            {
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            } else
            {
                return 0xfff40002;
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (obj instanceof Char)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            if (!(obj2 instanceof ReadTable))
            {
                return 0xfff40003;
            } else
            {
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (obj instanceof Char)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    public int match4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3, CallContext callcontext)
    {
        int i = 0xfff40004;
        modulemethod.selector;
        JVM INSTR lookupswitch 2: default 36
    //                   3: 124
    //                   9: 54;
           goto _L1 _L2 _L3
_L1:
        i = super.match4(modulemethod, obj, obj1, obj2, obj3, callcontext);
_L5:
        return i;
_L3:
        if (obj instanceof Char)
        {
            callcontext.value1 = obj;
            if (obj1 instanceof Char)
            {
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                if (obj3 instanceof ReadTable)
                {
                    callcontext.value4 = obj3;
                    callcontext.proc = modulemethod;
                    callcontext.pc = 4;
                    return 0;
                }
            } else
            {
                return 0xfff40002;
            }
        } else
        {
            return 0xfff40001;
        }
        if (true)
        {
            continue; /* Loop/switch isn't completed */
        }
_L2:
        if (obj instanceof Char)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            if (obj3 instanceof ReadTable)
            {
                callcontext.value4 = obj3;
                callcontext.proc = modulemethod;
                callcontext.pc = 4;
                return 0;
            }
        } else
        {
            return 0xfff40001;
        }
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit6 = (SimpleSymbol)(new SimpleSymbol("define-reader-ctor")).readResolve();
        Lit5 = (SimpleSymbol)(new SimpleSymbol("get-dispatch-macro-table")).readResolve();
        Lit4 = (SimpleSymbol)(new SimpleSymbol("set-dispatch-macro-character")).readResolve();
        Lit3 = (SimpleSymbol)(new SimpleSymbol("make-dispatch-macro-character")).readResolve();
        Lit2 = (SimpleSymbol)(new SimpleSymbol("set-macro-character")).readResolve();
        Lit1 = (SimpleSymbol)(new SimpleSymbol("readtable?")).readResolve();
        Lit0 = (SimpleSymbol)(new SimpleSymbol("current-readtable")).readResolve();
        $instance = new readtable();
        readtable readtable1 = $instance;
        current$Mnreadtable = new ModuleMethod(readtable1, 1, Lit0, 0);
        readtable$Qu = new ModuleMethod(readtable1, 2, Lit1, 4097);
        set$Mnmacro$Mncharacter = new ModuleMethod(readtable1, 3, Lit2, 16386);
        make$Mndispatch$Mnmacro$Mncharacter = new ModuleMethod(readtable1, 6, Lit3, 12289);
        set$Mndispatch$Mnmacro$Mncharacter = new ModuleMethod(readtable1, 9, Lit4, 16387);
        get$Mndispatch$Mnmacro$Mntable = new ModuleMethod(readtable1, 11, Lit5, 12290);
        define$Mnreader$Mnctor = new ModuleMethod(readtable1, 13, Lit6, 12290);
        $instance.run();
    }
}
