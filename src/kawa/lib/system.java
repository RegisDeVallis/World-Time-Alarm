// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.ApplicationMainSupport;
import gnu.expr.Compilation;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.IsEqual;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.InputStream;
import java.util.StringTokenizer;
import kawa.lang.CompileFile;
import kawa.lang.NamedException;
import kawa.standard.Scheme;

// Referenced classes of package kawa.lib:
//            lists, vectors, strings, misc

public class system extends ModuleBody
{

    public static final system $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final IntNum Lit1 = IntNum.make(1);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod _fldcatch;
    public static Procedure command$Mnparse;
    public static final ModuleMethod compile$Mnfile;
    public static final ModuleMethod convert$Mnlist$Mnto$Mnstring$Mnarray;
    public static final ModuleMethod convert$Mnvector$Mnto$Mnstring$Mnarray;
    public static final ModuleMethod make$Mnprocess;
    public static final ModuleMethod open$Mninput$Mnpipe;
    public static final ModuleMethod process$Mncommand$Mnline$Mnassignments;
    public static final ModuleMethod system;
    public static final ModuleMethod tokenize$Mnstring$Mnto$Mnstring$Mnarray;
    public static final ModuleMethod tokenize$Mnstring$Mnusing$Mnshell;

    public system()
    {
        ModuleInfo.register(this);
    }

    public static Object _mthcatch(Object obj, Procedure procedure, Procedure procedure1)
    {
        Object obj1;
        try
        {
            obj1 = procedure.apply0();
        }
        catch (NamedException namedexception)
        {
            return namedexception.applyHandler(obj, procedure1);
        }
        return obj1;
    }

    public static void compileFile(CharSequence charsequence, String s)
    {
        SourceMessages sourcemessages = new SourceMessages();
        Compilation compilation = CompileFile.read(charsequence.toString(), sourcemessages);
        compilation.explicit = true;
        if (sourcemessages.seenErrors())
        {
            throw (Throwable)new SyntaxException(sourcemessages);
        }
        compilation.compileToArchive(compilation.getModule(), s);
        if (sourcemessages.seenErrors())
        {
            throw (Throwable)new SyntaxException(sourcemessages);
        } else
        {
            return;
        }
    }

    public static Object convertListToStringArray(Object obj)
    {
        LList llist;
        String as[];
        int i;
        Object obj1;
        try
        {
            llist = (LList)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "length", 1, obj);
        }
        as = new String[lists.length(llist)];
        i = 0;
        obj1 = obj;
        do
        {
            if (lists.isNull(obj1))
            {
                return as;
            }
            Pair pair;
            Object obj2;
            String s;
            try
            {
                pair = (Pair)obj1;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "pp", -2, obj1);
            }
            obj2 = pair.getCar();
            if (obj2 == null)
            {
                s = null;
            } else
            {
                s = obj2.toString();
            }
            as[i] = s;
            obj1 = pair.getCdr();
            i++;
        } while (true);
    }

    public static Object convertVectorToStringArray(Object obj)
    {
        FVector fvector;
        int i;
        String as[];
        Object obj1;
        try
        {
            fvector = (FVector)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "vector-length", 1, obj);
        }
        i = vectors.vectorLength(fvector);
        as = new String[i];
        obj1 = Lit0;
        while (Scheme.numEqu.apply2(obj1, Integer.valueOf(i)) == Boolean.FALSE) 
        {
            int j = ((Number)obj1).intValue();
            FVector fvector1;
            int k;
            Object obj2;
            String s;
            try
            {
                fvector1 = (FVector)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "vector-ref", 1, obj);
            }
            try
            {
                k = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "vector-ref", 2, obj1);
            }
            obj2 = vectors.vectorRef(fvector1, k);
            if (obj2 == null)
            {
                s = null;
            } else
            {
                s = obj2.toString();
            }
            as[j] = s;
            obj1 = AddOp.$Pl.apply2(obj1, Lit1);
        }
        return as;
    }

    public static Process makeProcess(Object obj, Object obj1)
    {
        Object obj2;
        Runtime runtime;
        String as[];
        String as1[];
        if (vectors.isVector(obj))
        {
            obj2 = convertVectorToStringArray(obj);
        } else
        if (lists.isList(obj))
        {
            obj2 = convertListToStringArray(obj);
        } else
        if (strings.isString(obj))
        {
            obj2 = command$Mnparse.apply1(obj);
        } else
        if (obj instanceof String[])
        {
            obj2 = obj;
        } else
        {
            obj2 = misc.error$V("invalid arguments to make-process", new Object[0]);
        }
        runtime = Runtime.getRuntime();
        try
        {
            as = (String[])obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "java.lang.Runtime.exec(java.lang.String[],java.lang.String[])", 2, obj2);
        }
        try
        {
            as1 = (String[])obj1;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "java.lang.Runtime.exec(java.lang.String[],java.lang.String[])", 3, obj1);
        }
        return runtime.exec(as, as1);
    }

    public static InputStream openInputPipe(Object obj)
    {
        return makeProcess(obj, null).getInputStream();
    }

    public static void processCommandLineAssignments()
    {
        ApplicationMainSupport.processSetProperties();
    }

    public static int system(Object obj)
    {
        return makeProcess(obj, null).waitFor();
    }

    public static Object tokenizeStringToStringArray(String s)
    {
        StringTokenizer stringtokenizer = new StringTokenizer(s);
        Object obj;
        for (obj = LList.Empty; stringtokenizer.hasMoreTokens(); obj = lists.cons(stringtokenizer.nextToken(), obj)) { }
        LList llist;
        int i;
        String as[];
        int j;
        Object obj1;
        try
        {
            llist = (LList)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "length", 1, obj);
        }
        i = lists.length(llist);
        as = new String[i];
        j = i - 1;
        obj1 = obj;
        do
        {
            if (lists.isNull(obj1))
            {
                return as;
            }
            Pair pair;
            Object obj2;
            String s1;
            try
            {
                pair = (Pair)obj1;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "pp", -2, obj1);
            }
            obj2 = pair.getCar();
            if (obj2 == null)
            {
                s1 = null;
            } else
            {
                s1 = obj2.toString();
            }
            as[j] = s1;
            obj1 = pair.getCdr();
            j--;
        } while (true);
    }

    public static String[] tokenizeStringUsingShell(Object obj)
    {
        String as[] = new String[3];
        as[0] = "/bin/sh";
        as[1] = "-c";
        String s;
        if (obj == null)
        {
            s = null;
        } else
        {
            s = obj.toString();
        }
        as[2] = s;
        return as;
    }

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 10)
        {
            processCommandLineAssignments();
            return Values.empty;
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
            return openInputPipe(obj);

        case 3: // '\003'
            return Integer.valueOf(system(obj));

        case 4: // '\004'
            return convertVectorToStringArray(obj);

        case 5: // '\005'
            return convertListToStringArray(obj);

        case 6: // '\006'
            String s;
            if (obj == null)
            {
                s = null;
            } else
            {
                s = obj.toString();
            }
            return tokenizeStringToStringArray(s);

        case 7: // '\007'
            return tokenizeStringUsingShell(obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 1: // '\001'
            return makeProcess(obj, obj1);

        case 8: // '\b'
            break;
        }
        CharSequence charsequence;
        String s;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "compile-file", 1, obj);
        }
        if (obj1 == null)
        {
            s = null;
        } else
        {
            s = obj1.toString();
        }
        compileFile(charsequence, s);
        return Values.empty;
    }

    public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
    {
        if (modulemethod.selector == 9)
        {
            Procedure procedure;
            Procedure procedure1;
            try
            {
                procedure = (Procedure)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "catch", 2, obj1);
            }
            try
            {
                procedure1 = (Procedure)obj2;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "catch", 3, obj2);
            }
            return _mthcatch(obj, procedure, procedure1);
        } else
        {
            return super.apply3(modulemethod, obj, obj1, obj2);
        }
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 10)
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

        case 7: // '\007'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 6: // '\006'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 5: // '\005'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

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

        case 2: // '\002'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match2(modulemethod, obj, obj1, callcontext);

        case 8: // '\b'
            if (obj instanceof CharSequence)
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 1: // '\001'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        }
    }

    public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        if (modulemethod.selector == 9)
        {
            callcontext.value1 = obj;
            if (!(obj1 instanceof Procedure))
            {
                return 0xfff40002;
            }
            callcontext.value2 = obj1;
            if (!(obj2 instanceof Procedure))
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
            return super.match3(modulemethod, obj, obj1, obj2, callcontext);
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
        ModuleMethod modulemethod;
        if (IsEqual.apply(System.getProperty("file.separator"), "/"))
        {
            modulemethod = tokenize$Mnstring$Mnusing$Mnshell;
        } else
        {
            modulemethod = tokenize$Mnstring$Mnto$Mnstring$Mnarray;
        }
        command$Mnparse = modulemethod;
    }

    static 
    {
        Lit11 = (SimpleSymbol)(new SimpleSymbol("process-command-line-assignments")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("catch")).readResolve();
        Lit9 = (SimpleSymbol)(new SimpleSymbol("compile-file")).readResolve();
        Lit8 = (SimpleSymbol)(new SimpleSymbol("tokenize-string-using-shell")).readResolve();
        Lit7 = (SimpleSymbol)(new SimpleSymbol("tokenize-string-to-string-array")).readResolve();
        Lit6 = (SimpleSymbol)(new SimpleSymbol("convert-list-to-string-array")).readResolve();
        Lit5 = (SimpleSymbol)(new SimpleSymbol("convert-vector-to-string-array")).readResolve();
        Lit4 = (SimpleSymbol)(new SimpleSymbol("system")).readResolve();
        Lit3 = (SimpleSymbol)(new SimpleSymbol("open-input-pipe")).readResolve();
        Lit2 = (SimpleSymbol)(new SimpleSymbol("make-process")).readResolve();
        $instance = new system();
        system system1 = $instance;
        make$Mnprocess = new ModuleMethod(system1, 1, Lit2, 8194);
        open$Mninput$Mnpipe = new ModuleMethod(system1, 2, Lit3, 4097);
        system = new ModuleMethod(system1, 3, Lit4, 4097);
        convert$Mnvector$Mnto$Mnstring$Mnarray = new ModuleMethod(system1, 4, Lit5, 4097);
        convert$Mnlist$Mnto$Mnstring$Mnarray = new ModuleMethod(system1, 5, Lit6, 4097);
        tokenize$Mnstring$Mnto$Mnstring$Mnarray = new ModuleMethod(system1, 6, Lit7, 4097);
        tokenize$Mnstring$Mnusing$Mnshell = new ModuleMethod(system1, 7, Lit8, 4097);
        compile$Mnfile = new ModuleMethod(system1, 8, Lit9, 8194);
        _fldcatch = new ModuleMethod(system1, 9, Lit10, 12291);
        process$Mncommand$Mnline$Mnassignments = new ModuleMethod(system1, 10, Lit11, 0);
        $instance.run();
    }
}
