// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ZipLoader;
import gnu.expr.Compilation;
import gnu.expr.CompiledModule;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleManager;
import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.lists.VoidConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import gnu.text.FilePath;
import gnu.text.Lexer;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.URL;

public class Shell
{

    private static Class boolClasses[];
    public static ThreadLocal currentLoadPath = new ThreadLocal();
    public static Object defaultFormatInfo[];
    public static Method defaultFormatMethod;
    public static String defaultFormatName;
    static Object formats[][];
    private static Class httpPrinterClasses[] = {
        gnu/mapping/OutPort
    };
    private static Class noClasses[];
    private static Object portArg;
    private static Class xmlPrinterClasses[] = {
        gnu/mapping/OutPort, java/lang/Object
    };

    public Shell()
    {
    }

    public static final CompiledModule checkCompiledZip(InputStream inputstream, Path path, Environment environment, Language language)
        throws IOException
    {
        inputstream.mark(5);
        CompiledModule compiledmodule;
        boolean flag;
        if (inputstream.read() == 80 && inputstream.read() == 75 && inputstream.read() == 3 && inputstream.read() == 4)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        try
        {
            inputstream.reset();
        }
        catch (IOException ioexception)
        {
            return null;
        }
        compiledmodule = null;
        if (flag) goto _L2; else goto _L1
_L1:
        return compiledmodule;
_L2:
        Environment environment1;
        String s;
        inputstream.close();
        environment1 = Environment.getCurrent();
        s = path.toString();
        if (environment == environment1)
        {
            break MISSING_BLOCK_LABEL_92;
        }
        Environment.setCurrent(environment);
        if (!(path instanceof FilePath))
        {
            throw new RuntimeException((new StringBuilder()).append("load: ").append(s).append(" - not a file path").toString());
        }
        break MISSING_BLOCK_LABEL_193;
        IOException ioexception1;
        ioexception1;
        throw new WrappedException((new StringBuilder()).append("load: ").append(s).append(" - ").append(ioexception1.toString()).toString(), ioexception1);
        Exception exception;
        exception;
        if (environment != environment1)
        {
            Environment.setCurrent(environment1);
        }
        throw exception;
        CompiledModule compiledmodule1;
        File file = ((FilePath)path).toFile();
        if (!file.exists())
        {
            throw new RuntimeException((new StringBuilder()).append("load: ").append(s).append(" - not found").toString());
        }
        if (!file.canRead())
        {
            throw new RuntimeException((new StringBuilder()).append("load: ").append(s).append(" - not readable").toString());
        }
        compiledmodule1 = CompiledModule.make((new ZipLoader(s)).loadAllClasses(), language);
        compiledmodule = compiledmodule1;
        if (environment != environment1)
        {
            Environment.setCurrent(environment1);
            return compiledmodule;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    static CompiledModule compileSource(InPort inport, Environment environment, URL url, Language language, SourceMessages sourcemessages)
        throws SyntaxException, IOException
    {
        Compilation compilation = language.parse(inport, sourcemessages, 1, ModuleManager.getInstance().findWithSourcePath(inport.getName()));
        CallContext.getInstance().values = Values.noArgs;
        Object obj = ModuleExp.evalModule1(environment, compilation, url, null);
        if (obj == null || sourcemessages.seenErrors())
        {
            return null;
        } else
        {
            return new CompiledModule(compilation.getModule(), obj, language);
        }
    }

    public static Consumer getOutputConsumer(OutPort outport)
    {
        Object aobj1[];
        Object aobj[] = defaultFormatInfo;
        if (outport == null)
        {
            return VoidConsumer.getInstance();
        }
        if (aobj == null)
        {
            return Language.getDefaultLanguage().getOutputConsumer(outport);
        }
        int i;
        try
        {
            aobj1 = new Object[-4 + aobj.length];
            System.arraycopy(((Object) (aobj)), 4, ((Object) (aobj1)), 0, aobj1.length);
            i = aobj1.length;
        }
        catch (Throwable throwable)
        {
            throw new RuntimeException((new StringBuilder()).append("cannot get output-format '").append(defaultFormatName).append("' - caught ").append(throwable).toString());
        }
        if (--i < 0)
        {
            break MISSING_BLOCK_LABEL_113;
        }
        if (aobj1[i] != portArg)
        {
            break MISSING_BLOCK_LABEL_46;
        }
        aobj1[i] = outport;
        break MISSING_BLOCK_LABEL_46;
        Object obj;
        obj = defaultFormatMethod.invoke(null, aobj1);
        if (!(obj instanceof AbstractFormat))
        {
            break MISSING_BLOCK_LABEL_142;
        }
        outport.objectFormat = (AbstractFormat)obj;
        return outport;
        Consumer consumer = (Consumer)obj;
        return consumer;
    }

    public static void printError(Throwable throwable, SourceMessages sourcemessages, OutPort outport)
    {
        if (throwable instanceof WrongArguments)
        {
            WrongArguments wrongarguments = (WrongArguments)throwable;
            sourcemessages.printAll(outport, 20);
            if (wrongarguments.usage != null)
            {
                outport.println((new StringBuilder()).append("usage: ").append(wrongarguments.usage).toString());
            }
            wrongarguments.printStackTrace(outport);
            return;
        }
        if (throwable instanceof ClassCastException)
        {
            sourcemessages.printAll(outport, 20);
            outport.println((new StringBuilder()).append("Invalid parameter, was: ").append(throwable.getMessage()).toString());
            throwable.printStackTrace(outport);
            return;
        }
        if (throwable instanceof SyntaxException)
        {
            SyntaxException syntaxexception = (SyntaxException)throwable;
            if (syntaxexception.getMessages() == sourcemessages)
            {
                syntaxexception.printAll(outport, 20);
                syntaxexception.clear();
                return;
            }
        }
        sourcemessages.printAll(outport, 20);
        throwable.printStackTrace(outport);
    }

    public static Throwable run(Language language, Environment environment, InPort inport, Consumer consumer, OutPort outport, URL url, SourceMessages sourcemessages)
    {
        int j;
        Language language1 = Language.setSaveCurrent(language);
        Lexer lexer = language.getLexer(inport, sourcemessages);
        boolean flag;
        CallContext callcontext;
        Consumer consumer1;
        Compilation compilation;
        boolean flag1;
        boolean flag2;
        if (outport != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        lexer.setInteractive(flag);
        callcontext = CallContext.getInstance();
        consumer1 = null;
        if (consumer != null)
        {
            consumer1 = callcontext.consumer;
            callcontext.consumer = consumer;
        }
        Exception exception;
        Throwable throwable;
        ModuleExp moduleexp;
        StringBuilder stringbuilder;
        int i;
        try
        {
            Thread thread = Thread.currentThread();
            ClassLoader classloader = thread.getContextClassLoader();
            if (!(classloader instanceof ArrayClassLoader))
            {
                thread.setContextClassLoader(new ArrayClassLoader(classloader));
            }
        }
        catch (SecurityException securityexception) { }
_L11:
        compilation = language.parse(lexer, 7, null);
        if (!flag) goto _L2; else goto _L1
_L1:
        flag1 = sourcemessages.checkErrors(outport, 20);
        flag2 = flag1;
_L7:
        if (compilation != null) goto _L4; else goto _L3
_L3:
        if (consumer != null)
        {
            callcontext.consumer = consumer1;
        }
        Language.restoreCurrent(language1);
        return null;
_L2:
        if (sourcemessages.seenErrors())
        {
            throw new SyntaxException(sourcemessages);
        }
          goto _L5
        throwable;
        if (!flag)
        {
            if (consumer != null)
            {
                callcontext.consumer = consumer1;
            }
            Language.restoreCurrent(language1);
            return throwable;
        }
          goto _L6
_L5:
        flag2 = false;
          goto _L7
_L4:
        if (flag2)
        {
            break; /* Loop/switch isn't completed */
        }
        moduleexp = compilation.getModule();
        stringbuilder = (new StringBuilder()).append("atInteractiveLevel$");
        i = 1 + ModuleExp.interactiveCounter;
        ModuleExp.interactiveCounter = i;
        moduleexp.setName(stringbuilder.append(i).toString());
_L9:
        j = inport.read();
        if (j >= 0 && j != 13 && j != 10)
        {
            continue; /* Loop/switch isn't completed */
        }
_L10:
        if (!ModuleExp.evalModule(environment, callcontext, compilation, url, outport))
        {
            break; /* Loop/switch isn't completed */
        }
        if (consumer instanceof Writer)
        {
            ((Writer)consumer).flush();
        }
        continue; /* Loop/switch isn't completed */
        if (j == 32 || j == 9) goto _L9; else goto _L8
_L8:
        inport.unread();
          goto _L10
        exception;
        if (consumer != null)
        {
            callcontext.consumer = consumer1;
        }
        Language.restoreCurrent(language1);
        throw exception;
_L6:
        printError(throwable, sourcemessages, outport);
        break; /* Loop/switch isn't completed */
        if (j >= 0) goto _L11; else goto _L3
    }

    public static Throwable run(Language language, Environment environment, InPort inport, OutPort outport, OutPort outport1, SourceMessages sourcemessages)
    {
        AbstractFormat abstractformat;
        Consumer consumer;
        abstractformat = null;
        if (outport != null)
        {
            abstractformat = outport.objectFormat;
        }
        consumer = getOutputConsumer(outport);
        Throwable throwable = run(language, environment, inport, consumer, outport1, null, sourcemessages);
        if (outport != null)
        {
            outport.objectFormat = abstractformat;
        }
        return throwable;
        Exception exception;
        exception;
        if (outport != null)
        {
            outport.objectFormat = abstractformat;
        }
        throw exception;
    }

    public static boolean run(Language language, Environment environment)
    {
        InPort inport = InPort.inDefault();
        SourceMessages sourcemessages = new SourceMessages();
        OutPort outport;
        Throwable throwable;
        if (inport instanceof TtyInPort)
        {
            gnu.mapping.Procedure procedure = language.getPrompter();
            if (procedure != null)
            {
                ((TtyInPort)inport).setPrompter(procedure);
            }
            outport = OutPort.errDefault();
        } else
        {
            outport = null;
        }
        throwable = run(language, environment, inport, OutPort.outDefault(), outport, sourcemessages);
        if (throwable == null)
        {
            return true;
        } else
        {
            printError(throwable, sourcemessages, OutPort.errDefault());
            return false;
        }
    }

    public static boolean run(Language language, Environment environment, InPort inport, Consumer consumer, OutPort outport, URL url)
    {
        SourceMessages sourcemessages = new SourceMessages();
        Throwable throwable = run(language, environment, inport, consumer, outport, url, sourcemessages);
        if (throwable != null)
        {
            printError(throwable, sourcemessages, outport);
        }
        return throwable == null;
    }

    public static final boolean runFile(InputStream inputstream, Path path, Environment environment, boolean flag, int i)
        throws Throwable
    {
        Language language;
        Path path1;
        if (!(inputstream instanceof BufferedInputStream))
        {
            inputstream = new BufferedInputStream(inputstream);
        }
        language = Language.getDefaultLanguage();
        path1 = (Path)currentLoadPath.get();
        CompiledModule compiledmodule;
        currentLoadPath.set(path);
        compiledmodule = checkCompiledZip(inputstream, path, environment, language);
        if (compiledmodule != null)
        {
            break MISSING_BLOCK_LABEL_219;
        }
        InPort inport = InPort.openFile(inputstream, path);
_L2:
        if (--i < 0)
        {
            break; /* Loop/switch isn't completed */
        }
        inport.skipRestOfLine();
        if (true) goto _L2; else goto _L1
        Exception exception;
        exception;
        currentLoadPath.set(path1);
        throw exception;
_L1:
        SourceMessages sourcemessages;
        URL url;
        sourcemessages = new SourceMessages();
        url = path.toURL();
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_170;
        }
        Object obj;
        if (!ModuleBody.getMainPrintValues())
        {
            break MISSING_BLOCK_LABEL_158;
        }
        obj = getOutputConsumer(OutPort.outDefault());
_L3:
        Throwable throwable = run(language, environment, inport, ((Consumer) (obj)), null, url, sourcemessages);
        if (throwable == null)
        {
            break MISSING_BLOCK_LABEL_214;
        }
        throw throwable;
        Exception exception1;
        exception1;
        inport.close();
        throw exception1;
        obj = new VoidConsumer();
          goto _L3
        compiledmodule = compileSource(inport, environment, url, language, sourcemessages);
        sourcemessages.printAll(OutPort.errDefault(), 20);
        if (compiledmodule != null)
        {
            break MISSING_BLOCK_LABEL_214;
        }
        inport.close();
        currentLoadPath.set(path1);
        return false;
        inport.close();
        if (compiledmodule == null)
        {
            break MISSING_BLOCK_LABEL_233;
        }
        compiledmodule.evalModule(environment, OutPort.outDefault());
        currentLoadPath.set(path1);
        return true;
    }

    public static boolean runFileOrClass(String s, boolean flag, int i)
    {
        Language language = Language.getDefaultLanguage();
        if (!s.equals("-")) goto _L2; else goto _L1
_L1:
        Path path;
        InputStream inputstream;
        path = Path.valueOf("/dev/stdin");
        inputstream = System.in;
_L4:
        boolean flag1 = runFile(inputstream, path, Environment.getCurrent(), flag, i);
        return flag1;
_L2:
        Throwable throwable3;
        try
        {
            path = Path.valueOf(s);
            inputstream = path.openInputStream();
        }
        catch (Throwable throwable)
        {
            Class class1;
            try
            {
                class1 = Class.forName(s);
            }
            catch (Throwable throwable1)
            {
                System.err.println((new StringBuilder()).append("Cannot read file ").append(throwable.getMessage()).toString());
                return false;
            }
            try
            {
                CompiledModule.make(class1, language).evalModule(Environment.getCurrent(), OutPort.outDefault());
            }
            catch (Throwable throwable2)
            {
                throwable2.printStackTrace();
                return false;
            }
            return true;
        }
        if (true) goto _L4; else goto _L3
_L3:
        throwable3;
        throwable3.printStackTrace(System.err);
        return false;
    }

    public static void setDefaultFormat(String s)
    {
        String s1;
        int i;
        s1 = s.intern();
        defaultFormatName = s1;
        i = 0;
_L2:
        Object aobj[];
        Object obj;
        aobj = formats[i];
        obj = aobj[0];
        if (obj != null)
        {
            break; /* Loop/switch isn't completed */
        }
        System.err.println((new StringBuilder()).append("kawa: unknown output format '").append(s1).append("'").toString());
        System.exit(-1);
_L4:
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        if (obj != s1) goto _L4; else goto _L3
_L3:
        defaultFormatInfo = aobj;
        try
        {
            defaultFormatMethod = Class.forName((String)aobj[1]).getMethod((String)aobj[2], (Class[])(Class[])aobj[3]);
        }
        catch (Throwable throwable)
        {
            System.err.println((new StringBuilder()).append("kawa:  caught ").append(throwable).append(" while looking for format '").append(s1).append("'").toString());
            System.exit(-1);
        }
        if (!defaultFormatInfo[1].equals("gnu.lists.VoidConsumer"))
        {
            ModuleBody.setMainPrintValues(true);
        }
        return;
    }

    static 
    {
        noClasses = new Class[0];
        Class aclass[] = new Class[1];
        aclass[0] = Boolean.TYPE;
        boolClasses = aclass;
        portArg = "(port)";
        Object aobj[][] = new Object[14][];
        Object aobj1[] = new Object[5];
        aobj1[0] = "scheme";
        aobj1[1] = "gnu.kawa.functions.DisplayFormat";
        aobj1[2] = "getSchemeFormat";
        aobj1[3] = boolClasses;
        aobj1[4] = Boolean.FALSE;
        aobj[0] = aobj1;
        Object aobj2[] = new Object[5];
        aobj2[0] = "readable-scheme";
        aobj2[1] = "gnu.kawa.functions.DisplayFormat";
        aobj2[2] = "getSchemeFormat";
        aobj2[3] = boolClasses;
        aobj2[4] = Boolean.TRUE;
        aobj[1] = aobj2;
        Object aobj3[] = new Object[5];
        aobj3[0] = "elisp";
        aobj3[1] = "gnu.kawa.functions.DisplayFormat";
        aobj3[2] = "getEmacsLispFormat";
        aobj3[3] = boolClasses;
        aobj3[4] = Boolean.FALSE;
        aobj[2] = aobj3;
        Object aobj4[] = new Object[5];
        aobj4[0] = "readable-elisp";
        aobj4[1] = "gnu.kawa.functions.DisplayFormat";
        aobj4[2] = "getEmacsLispFormat";
        aobj4[3] = boolClasses;
        aobj4[4] = Boolean.TRUE;
        aobj[3] = aobj4;
        Object aobj5[] = new Object[5];
        aobj5[0] = "clisp";
        aobj5[1] = "gnu.kawa.functions.DisplayFormat";
        aobj5[2] = "getCommonLispFormat";
        aobj5[3] = boolClasses;
        aobj5[4] = Boolean.FALSE;
        aobj[4] = aobj5;
        Object aobj6[] = new Object[5];
        aobj6[0] = "readable-clisp";
        aobj6[1] = "gnu.kawa.functions.DisplayFormat";
        aobj6[2] = "getCommonLispFormat";
        aobj6[3] = boolClasses;
        aobj6[4] = Boolean.TRUE;
        aobj[5] = aobj6;
        Object aobj7[] = new Object[5];
        aobj7[0] = "commonlisp";
        aobj7[1] = "gnu.kawa.functions.DisplayFormat";
        aobj7[2] = "getCommonLispFormat";
        aobj7[3] = boolClasses;
        aobj7[4] = Boolean.FALSE;
        aobj[6] = aobj7;
        Object aobj8[] = new Object[5];
        aobj8[0] = "readable-commonlisp";
        aobj8[1] = "gnu.kawa.functions.DisplayFormat";
        aobj8[2] = "getCommonLispFormat";
        aobj8[3] = boolClasses;
        aobj8[4] = Boolean.TRUE;
        aobj[7] = aobj8;
        Object aobj9[] = new Object[6];
        aobj9[0] = "xml";
        aobj9[1] = "gnu.xml.XMLPrinter";
        aobj9[2] = "make";
        aobj9[3] = xmlPrinterClasses;
        aobj9[4] = portArg;
        aobj9[5] = null;
        aobj[8] = aobj9;
        Object aobj10[] = new Object[6];
        aobj10[0] = "html";
        aobj10[1] = "gnu.xml.XMLPrinter";
        aobj10[2] = "make";
        aobj10[3] = xmlPrinterClasses;
        aobj10[4] = portArg;
        aobj10[5] = "html";
        aobj[9] = aobj10;
        Object aobj11[] = new Object[6];
        aobj11[0] = "xhtml";
        aobj11[1] = "gnu.xml.XMLPrinter";
        aobj11[2] = "make";
        aobj11[3] = xmlPrinterClasses;
        aobj11[4] = portArg;
        aobj11[5] = "xhtml";
        aobj[10] = aobj11;
        Object aobj12[] = new Object[5];
        aobj12[0] = "cgi";
        aobj12[1] = "gnu.kawa.xml.HttpPrinter";
        aobj12[2] = "make";
        aobj12[3] = httpPrinterClasses;
        aobj12[4] = portArg;
        aobj[11] = aobj12;
        Object aobj13[] = new Object[4];
        aobj13[0] = "ignore";
        aobj13[1] = "gnu.lists.VoidConsumer";
        aobj13[2] = "getInstance";
        aobj13[3] = noClasses;
        aobj[12] = aobj13;
        aobj[13] = (new Object[] {
            null
        });
        formats = aobj;
    }
}
