// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Symbols;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.xml.KNode;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import kawa.Version;
import kawa.lang.Promise;
import kawa.standard.Scheme;
import kawa.standard.throw_name;

// Referenced classes of package kawa.lib:
//            ports

public class misc extends ModuleBody
{
    public class frame extends ModuleBody
    {

        final ModuleMethod lambda$Fn4;
        Object msg;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 2)
            {
                lambda3(obj);
                return Values.empty;
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        void lambda3(Object obj)
        {
            ports.display(msg, obj);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 2)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return super.match1(modulemethod, obj, callcontext);
            }
        }

        public frame()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 2, null, 4097);
            modulemethod.setProperty("source-location", "misc.scm:104");
            lambda$Fn4 = modulemethod;
        }
    }

    public class frame0 extends ModuleBody
    {

        Object arg;
        final ModuleMethod lambda$Fn5;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 1)
            {
                lambda4(obj);
                return Values.empty;
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        void lambda4(Object obj)
        {
            ports.write(arg, obj);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 1)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return super.match1(modulemethod, obj, callcontext);
            }
        }

        public frame0()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 1, null, 4097);
            modulemethod.setProperty("source-location", "misc.scm:107");
            lambda$Fn5 = modulemethod;
        }
    }


    public static final misc $instance;
    static final IntNum Lit0 = IntNum.make(4);
    static final IntNum Lit1 = IntNum.make(5);
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
    static final Keyword Lit2 = Keyword.make("setter");
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit3 = (SimpleSymbol)(new SimpleSymbol("misc-error")).readResolve();
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod add$Mnprocedure$Mnproperties;
    public static final ModuleMethod base$Mnuri;
    public static final ModuleMethod boolean$Qu;
    public static final ModuleMethod dynamic$Mnwind;
    public static final ModuleMethod environment$Mnbound$Qu;
    public static final ModuleMethod error;
    public static final ModuleMethod force;
    public static final ModuleMethod gentemp;
    public static final ModuleMethod interaction$Mnenvironment;
    static final ModuleMethod lambda$Fn1;
    static final ModuleMethod lambda$Fn2;
    public static final ModuleMethod namespace$Mnprefix;
    public static final ModuleMethod namespace$Mnuri;
    public static final ModuleMethod null$Mnenvironment;
    public static final GenericProc procedure$Mnproperty;
    static final ModuleMethod procedure$Mnproperty$Fn3;
    public static final ModuleMethod procedure$Qu;
    public static final ModuleMethod scheme$Mnimplementation$Mnversion;
    public static final ModuleMethod scheme$Mnreport$Mnenvironment;
    public static final ModuleMethod set$Mnprocedure$Mnproperty$Ex;
    public static final ModuleMethod string$Mn$Grsymbol;
    public static final GenericProc symbol$Eq$Qu;
    public static final ModuleMethod symbol$Mn$Grstring;
    public static final ModuleMethod symbol$Mnlocal$Mnname;
    public static final ModuleMethod symbol$Mnnamespace;
    public static final ModuleMethod symbol$Mnnamespace$Mnuri;
    public static final ModuleMethod symbol$Mnprefix;
    public static final ModuleMethod symbol$Qu;
    public static final ModuleMethod values;

    public misc()
    {
        ModuleInfo.register(this);
    }

    public static transient void addProcedureProperties(GenericProc genericproc, Object aobj[])
    {
        genericproc.setProperties(aobj);
    }

    public static Object baseUri()
    {
        return baseUri(null);
    }

    public static Object baseUri(Object obj)
    {
        Object obj1;
        if (obj == null)
        {
            obj1 = Path.currentPath();
        } else
        {
            obj1 = ((KNode)obj).baseURI();
        }
        if (obj1 == Values.empty)
        {
            obj1 = Boolean.FALSE;
        }
        return obj1;
    }

    public static Object dynamicWind(Object obj, Object obj1, Object obj2)
    {
        Scheme.applyToArgs.apply1(obj);
        Object obj3 = Scheme.applyToArgs.apply1(obj1);
        Scheme.applyToArgs.apply1(obj2);
        return obj3;
        Exception exception;
        exception;
        Scheme.applyToArgs.apply1(obj2);
        throw exception;
    }

    public static Object error$V(Object obj, Object aobj[])
    {
        frame frame1 = new frame();
        frame1.msg = obj;
        LList llist = LList.makeList(aobj, 0);
        frame1.msg = ports.callWithOutputString(frame1.Fn4);
        Object obj1 = LList.Empty;
        Object obj2 = llist;
        do
        {
            if (obj2 == LList.Empty)
            {
                LList llist1 = LList.reverseInPlace(obj1);
                return Scheme.apply.apply4(throw_name.throwName, Lit3, frame1.msg, llist1);
            }
            Pair pair;
            Object obj3;
            Object obj4;
            frame0 frame0_1;
            try
            {
                pair = (Pair)obj2;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "arg0", -2, obj2);
            }
            obj3 = pair.getCdr();
            obj4 = pair.getCar();
            frame0_1 = new frame0();
            frame0_1.arg = obj4;
            obj1 = Pair.make(ports.callWithOutputString(frame0_1.Fn5), obj1);
            obj2 = obj3;
        } while (true);
    }

    public static Object force(Object obj)
    {
        return Promise.force(obj);
    }

    public static Symbol gentemp()
    {
        return Symbols.gentemp();
    }

    public static Environment interactionEnvironment()
    {
        return Environment.user();
    }

    public static boolean isBoolean(Object obj)
    {
        boolean flag;
        if (obj == Boolean.TRUE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return flag;
        }
        return obj == Boolean.FALSE;
    }

    public static boolean isEnvironmentBound(Environment environment, Object obj)
    {
        return environment.isBound(LispLanguage.langSymbolToSymbol(obj));
    }

    public static boolean isProcedure(Object obj)
    {
        boolean flag = obj instanceof Procedure;
        if (flag)
        {
            return flag;
        } else
        {
            return obj instanceof LangObjType;
        }
    }

    public static boolean isSymbol(Object obj)
    {
        return obj instanceof Symbol;
    }

    static boolean lambda1(Symbol symbol, Symbol symbol1)
    {
        return Symbol.equals(symbol, symbol1);
    }

    static boolean lambda2$V(Symbol symbol, Symbol symbol1, Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        boolean flag = Symbol.equals(symbol, symbol1);
        if (flag)
        {
            Object obj = Scheme.apply.apply3(symbol$Eq$Qu, symbol1, llist);
            Boolean boolean1 = Boolean.FALSE;
            boolean flag1 = false;
            if (obj != boolean1)
            {
                flag1 = true;
            }
            return flag1;
        } else
        {
            return flag;
        }
    }

    public static CharSequence namespacePrefix(Namespace namespace)
    {
        return namespace.getPrefix();
    }

    public static CharSequence namespaceUri(Namespace namespace)
    {
        return namespace.getName();
    }

    public static Environment nullEnvironment()
    {
        return nullEnvironment(Boolean.FALSE);
    }

    public static Environment nullEnvironment(Object obj)
    {
        return Scheme.nullEnvironment;
    }

    public static Object procedureProperty(Procedure procedure, Object obj)
    {
        return procedureProperty(procedure, obj, Boolean.FALSE);
    }

    public static Object procedureProperty(Procedure procedure, Object obj, Object obj1)
    {
        return procedure.getProperty(obj, obj1);
    }

    public static String schemeImplementationVersion()
    {
        return Version.getVersion();
    }

    public static Object schemeReportEnvironment(Object obj)
    {
        if (Scheme.isEqv.apply2(obj, Lit0) != Boolean.FALSE)
        {
            return Scheme.r4Environment;
        }
        if (Scheme.isEqv.apply2(obj, Lit1) != Boolean.FALSE)
        {
            return Scheme.r5Environment;
        } else
        {
            return error$V("scheme-report-environment version must be 4 or 5", new Object[0]);
        }
    }

    public static void setProcedureProperty$Ex(Procedure procedure, Object obj, Object obj1)
    {
        procedure.setProperty(obj, obj1);
    }

    public static SimpleSymbol string$To$Symbol(CharSequence charsequence)
    {
        return SimpleSymbol.valueOf(charsequence.toString());
    }

    public static String symbol$To$String(Symbol symbol)
    {
        return symbol.toString();
    }

    public static String symbolLocalName(Symbol symbol)
    {
        return symbol.getLocalPart();
    }

    public static Namespace symbolNamespace(Symbol symbol)
    {
        return symbol.getNamespace();
    }

    public static String symbolNamespaceUri(Symbol symbol)
    {
        return symbol.getNamespaceURI();
    }

    public static String symbolPrefix(Symbol symbol)
    {
        return symbol.getPrefix();
    }

    public static transient Object values(Object aobj[])
    {
        return Values.make(aobj);
    }

    public Object apply0(ModuleMethod modulemethod)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply0(modulemethod);

        case 18: // '\022'
            return nullEnvironment();

        case 21: // '\025'
            return interactionEnvironment();

        case 22: // '\026'
            return schemeImplementationVersion();

        case 29: // '\035'
            return baseUri();

        case 31: // '\037'
            return gentemp();
        }
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        case 6: // '\006'
        case 7: // '\007'
        case 16: // '\020'
        case 17: // '\021'
        case 19: // '\023'
        case 21: // '\025'
        case 22: // '\026'
        case 23: // '\027'
        case 24: // '\030'
        case 25: // '\031'
        case 26: // '\032'
        case 28: // '\034'
        default:
            return super.apply1(modulemethod, obj);

        case 3: // '\003'
            if (isBoolean(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 4: // '\004'
            if (isSymbol(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 5: // '\005'
            CharSequence charsequence;
            Namespace namespace;
            Namespace namespace1;
            Symbol symbol;
            Symbol symbol1;
            Symbol symbol2;
            Symbol symbol3;
            Symbol symbol4;
            try
            {
                symbol4 = (Symbol)obj;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "symbol->string", 1, obj);
            }
            return symbol$To$String(symbol4);

        case 8: // '\b'
            try
            {
                symbol3 = (Symbol)obj;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "symbol-local-name", 1, obj);
            }
            return symbolLocalName(symbol3);

        case 9: // '\t'
            try
            {
                symbol2 = (Symbol)obj;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "symbol-namespace", 1, obj);
            }
            return symbolNamespace(symbol2);

        case 10: // '\n'
            try
            {
                symbol1 = (Symbol)obj;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "symbol-namespace-uri", 1, obj);
            }
            return symbolNamespaceUri(symbol1);

        case 11: // '\013'
            try
            {
                symbol = (Symbol)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "symbol-prefix", 1, obj);
            }
            return symbolPrefix(symbol);

        case 12: // '\f'
            try
            {
                namespace1 = (Namespace)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "namespace-uri", 1, obj);
            }
            return namespaceUri(namespace1);

        case 13: // '\r'
            try
            {
                namespace = (Namespace)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "namespace-prefix", 1, obj);
            }
            return namespacePrefix(namespace);

        case 14: // '\016'
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string->symbol", 1, obj);
            }
            return string$To$Symbol(charsequence);

        case 15: // '\017'
            if (isProcedure(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 18: // '\022'
            return nullEnvironment(obj);

        case 20: // '\024'
            return schemeReportEnvironment(obj);

        case 27: // '\033'
            return force(obj);

        case 29: // '\035'
            return baseUri(obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 6: // '\006'
            Procedure procedure;
            Environment environment;
            Symbol symbol;
            Symbol symbol1;
            try
            {
                symbol = (Symbol)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "lambda", 1, obj);
            }
            try
            {
                symbol1 = (Symbol)obj1;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "lambda", 2, obj1);
            }
            if (lambda1(symbol, symbol1))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 17: // '\021'
            try
            {
                environment = (Environment)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "environment-bound?", 1, obj);
            }
            if (isEnvironmentBound(environment, obj1))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 24: // '\030'
            break;
        }
        try
        {
            procedure = (Procedure)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "procedure-property", 1, obj);
        }
        return procedureProperty(procedure, obj1);
    }

    public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
    {
        switch (modulemethod.selector)
        {
        case 25: // '\031'
        default:
            return super.apply3(modulemethod, obj, obj1, obj2);

        case 23: // '\027'
            Procedure procedure;
            Procedure procedure1;
            try
            {
                procedure1 = (Procedure)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "set-procedure-property!", 1, obj);
            }
            setProcedureProperty$Ex(procedure1, obj1, obj2);
            return Values.empty;

        case 24: // '\030'
            try
            {
                procedure = (Procedure)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "procedure-property", 1, obj);
            }
            return procedureProperty(procedure, obj1, obj2);

        case 26: // '\032'
            return dynamicWind(obj, obj1, obj2);
        }
    }

    public Object applyN(ModuleMethod modulemethod, Object aobj[])
    {
        Object obj;
        switch (modulemethod.selector)
        {
        default:
            return super.applyN(modulemethod, aobj);

        case 7: // '\007'
            Object obj2 = aobj[0];
            GenericProc genericproc;
            int i;
            Object aobj1[];
            Object obj1;
            int j;
            Object aobj2[];
            Symbol symbol;
            Object obj3;
            Symbol symbol1;
            int k;
            Object aobj3[];
            try
            {
                symbol = (Symbol)obj2;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "lambda", 1, obj2);
            }
            obj3 = aobj[1];
            try
            {
                symbol1 = (Symbol)obj3;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "lambda", 2, obj3);
            }
            k = -2 + aobj.length;
            aobj3 = new Object[k];
            do
            {
                if (--k < 0)
                {
                    if (lambda2$V(symbol, symbol1, aobj3))
                    {
                        return Boolean.TRUE;
                    } else
                    {
                        return Boolean.FALSE;
                    }
                }
                aobj3[k] = aobj[k + 2];
            } while (true);

        case 16: // '\020'
            return values(aobj);

        case 28: // '\034'
            obj1 = aobj[0];
            j = -1 + aobj.length;
            aobj2 = new Object[j];
            do
            {
                if (--j < 0)
                {
                    return error$V(obj1, aobj2);
                }
                aobj2[j] = aobj[j + 1];
            } while (true);

        case 32: // ' '
            obj = aobj[0];
            break;
        }
        try
        {
            genericproc = (GenericProc)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "add-procedure-properties", 1, obj);
        }
        i = -1 + aobj.length;
        aobj1 = new Object[i];
        do
        {
            if (--i < 0)
            {
                addProcedureProperties(genericproc, aobj1);
                return Values.empty;
            }
            aobj1[i] = aobj[i + 1];
        } while (true);
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match0(modulemethod, callcontext);

        case 31: // '\037'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 29: // '\035'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 22: // '\026'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 21: // '\025'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 18: // '\022'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 6: // '\006'
        case 7: // '\007'
        case 16: // '\020'
        case 17: // '\021'
        case 19: // '\023'
        case 21: // '\025'
        case 22: // '\026'
        case 23: // '\027'
        case 24: // '\030'
        case 25: // '\031'
        case 26: // '\032'
        case 28: // '\034'
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 29: // '\035'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 27: // '\033'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 20: // '\024'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 18: // '\022'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 15: // '\017'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

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

        case 13: // '\r'
            if (!(obj instanceof Namespace))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 12: // '\f'
            if (!(obj instanceof Namespace))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 11: // '\013'
            if (!(obj instanceof Symbol))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 10: // '\n'
            if (!(obj instanceof Symbol))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 9: // '\t'
            if (!(obj instanceof Symbol))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 8: // '\b'
            if (!(obj instanceof Symbol))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 5: // '\005'
            if (!(obj instanceof Symbol))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 4: // '\004'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 3: // '\003'
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
        JVM INSTR lookupswitch 3: default 44
    //                   6: 124
    //                   17: 91
    //                   24: 58;
           goto _L1 _L2 _L3 _L4
_L1:
        i = super.match2(modulemethod, obj, obj1, callcontext);
_L6:
        return i;
_L4:
        if (obj instanceof Procedure)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (obj instanceof Environment)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (obj instanceof Symbol)
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
        if (true) goto _L6; else goto _L5
_L5:
    }

    public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 25: // '\031'
        default:
            return super.match3(modulemethod, obj, obj1, obj2, callcontext);

        case 26: // '\032'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;

        case 24: // '\030'
            if (!(obj instanceof Procedure))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            }

        case 23: // '\027'
            break;
        }
        if (!(obj instanceof Procedure))
        {
            return 0xfff40001;
        } else
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;
        }
    }

    public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.matchN(modulemethod, aobj, callcontext);

        case 32: // ' '
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 28: // '\034'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 16: // '\020'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 7: // '\007'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
        symbol$Eq$Qu = new GenericProc("symbol=?");
        GenericProc genericproc = symbol$Eq$Qu;
        Object aobj[] = new Object[2];
        aobj[0] = lambda$Fn1;
        aobj[1] = lambda$Fn2;
        genericproc.setProperties(aobj);
        procedure$Mnproperty = new GenericProc("procedure-property");
        GenericProc genericproc1 = procedure$Mnproperty;
        Object aobj1[] = new Object[3];
        aobj1[0] = Lit2;
        aobj1[1] = set$Mnprocedure$Mnproperty$Ex;
        ModuleMethod _tmp1 = procedure$Mnproperty$Fn3;
        aobj1[2] = procedure$Mnproperty$Fn3;
        genericproc1.setProperties(aobj1);
    }

    static 
    {
        Lit28 = (SimpleSymbol)(new SimpleSymbol("add-procedure-properties")).readResolve();
        Lit27 = (SimpleSymbol)(new SimpleSymbol("gentemp")).readResolve();
        Lit26 = (SimpleSymbol)(new SimpleSymbol("base-uri")).readResolve();
        Lit25 = (SimpleSymbol)(new SimpleSymbol("error")).readResolve();
        Lit24 = (SimpleSymbol)(new SimpleSymbol("force")).readResolve();
        Lit23 = (SimpleSymbol)(new SimpleSymbol("dynamic-wind")).readResolve();
        Lit22 = (SimpleSymbol)(new SimpleSymbol("procedure-property")).readResolve();
        Lit21 = (SimpleSymbol)(new SimpleSymbol("set-procedure-property!")).readResolve();
        Lit20 = (SimpleSymbol)(new SimpleSymbol("scheme-implementation-version")).readResolve();
        Lit19 = (SimpleSymbol)(new SimpleSymbol("interaction-environment")).readResolve();
        Lit18 = (SimpleSymbol)(new SimpleSymbol("scheme-report-environment")).readResolve();
        Lit17 = (SimpleSymbol)(new SimpleSymbol("null-environment")).readResolve();
        Lit16 = (SimpleSymbol)(new SimpleSymbol("environment-bound?")).readResolve();
        Lit15 = (SimpleSymbol)(new SimpleSymbol("values")).readResolve();
        Lit14 = (SimpleSymbol)(new SimpleSymbol("procedure?")).readResolve();
        Lit13 = (SimpleSymbol)(new SimpleSymbol("string->symbol")).readResolve();
        Lit12 = (SimpleSymbol)(new SimpleSymbol("namespace-prefix")).readResolve();
        Lit11 = (SimpleSymbol)(new SimpleSymbol("namespace-uri")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("symbol-prefix")).readResolve();
        Lit9 = (SimpleSymbol)(new SimpleSymbol("symbol-namespace-uri")).readResolve();
        Lit8 = (SimpleSymbol)(new SimpleSymbol("symbol-namespace")).readResolve();
        Lit7 = (SimpleSymbol)(new SimpleSymbol("symbol-local-name")).readResolve();
        Lit6 = (SimpleSymbol)(new SimpleSymbol("symbol->string")).readResolve();
        Lit5 = (SimpleSymbol)(new SimpleSymbol("symbol?")).readResolve();
        Lit4 = (SimpleSymbol)(new SimpleSymbol("boolean?")).readResolve();
        $instance = new misc();
        misc misc1 = $instance;
        boolean$Qu = new ModuleMethod(misc1, 3, Lit4, 4097);
        symbol$Qu = new ModuleMethod(misc1, 4, Lit5, 4097);
        symbol$Mn$Grstring = new ModuleMethod(misc1, 5, Lit6, 4097);
        ModuleMethod modulemethod = new ModuleMethod(misc1, 6, null, 8194);
        modulemethod.setProperty("source-location", "misc.scm:25");
        lambda$Fn1 = modulemethod;
        ModuleMethod modulemethod1 = new ModuleMethod(misc1, 7, null, -4094);
        modulemethod1.setProperty("source-location", "misc.scm:27");
        lambda$Fn2 = modulemethod1;
        symbol$Mnlocal$Mnname = new ModuleMethod(misc1, 8, Lit7, 4097);
        symbol$Mnnamespace = new ModuleMethod(misc1, 9, Lit8, 4097);
        symbol$Mnnamespace$Mnuri = new ModuleMethod(misc1, 10, Lit9, 4097);
        symbol$Mnprefix = new ModuleMethod(misc1, 11, Lit10, 4097);
        namespace$Mnuri = new ModuleMethod(misc1, 12, Lit11, 4097);
        namespace$Mnprefix = new ModuleMethod(misc1, 13, Lit12, 4097);
        string$Mn$Grsymbol = new ModuleMethod(misc1, 14, Lit13, 4097);
        procedure$Qu = new ModuleMethod(misc1, 15, Lit14, 4097);
        values = new ModuleMethod(misc1, 16, Lit15, -4096);
        environment$Mnbound$Qu = new ModuleMethod(misc1, 17, Lit16, 8194);
        null$Mnenvironment = new ModuleMethod(misc1, 18, Lit17, 4096);
        scheme$Mnreport$Mnenvironment = new ModuleMethod(misc1, 20, Lit18, 4097);
        interaction$Mnenvironment = new ModuleMethod(misc1, 21, Lit19, 0);
        scheme$Mnimplementation$Mnversion = new ModuleMethod(misc1, 22, Lit20, 0);
        set$Mnprocedure$Mnproperty$Ex = new ModuleMethod(misc1, 23, Lit21, 12291);
        procedure$Mnproperty$Fn3 = new ModuleMethod(misc1, 24, Lit22, 12290);
        dynamic$Mnwind = new ModuleMethod(misc1, 26, Lit23, 12291);
        force = new ModuleMethod(misc1, 27, Lit24, 4097);
        error = new ModuleMethod(misc1, 28, Lit25, -4095);
        base$Mnuri = new ModuleMethod(misc1, 29, Lit26, 4096);
        gentemp = new ModuleMethod(misc1, 31, Lit27, 0);
        add$Mnprocedure$Mnproperties = new ModuleMethod(misc1, 32, Lit28, -4095);
        $instance.run();
    }
}
