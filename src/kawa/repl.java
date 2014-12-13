// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa;

import gnu.bytecode.ClassType;
import gnu.expr.ApplicationMainSupport;
import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleManager;
import gnu.kawa.servlet.HttpRequestContext;
import gnu.lists.FString;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure0or1;
import gnu.mapping.Values;
import gnu.text.Options;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import gnu.text.WriterManager;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;

// Referenced classes of package kawa:
//            Shell, Telnet, TelnetRepl, Version

public class repl extends Procedure0or1
{

    public static String compilationTopname = null;
    static int defaultParseOptions = 72;
    public static String homeDirectory;
    public static boolean noConsole;
    static Language previousLanguage;
    static boolean shutdownRegistered;
    Language language;

    public repl(Language language1)
    {
        language = language1;
    }

    static void bad_option(String s)
    {
        System.err.println((new StringBuilder()).append("kawa: bad option '").append(s).append("'").toString());
        printOptions(System.err);
        System.exit(-1);
    }

    static void checkInitFile()
    {
        if (homeDirectory == null)
        {
            homeDirectory = System.getProperty("user.home");
            Object obj;
            File file;
            if (homeDirectory != null)
            {
                obj = new FString(homeDirectory);
                String s;
                if ("/".equals(System.getProperty("file.separator")))
                {
                    s = ".kawarc.scm";
                } else
                {
                    s = "kawarc.scm";
                }
                file = new File(homeDirectory, s);
            } else
            {
                obj = Boolean.FALSE;
                file = null;
            }
            Environment.getCurrent().put("home-directory", obj);
            if (file != null && file.exists() && !Shell.runFileOrClass(file.getPath(), true, 0))
            {
                System.exit(-1);
            }
        }
    }

    public static void compileFiles(String as[], int i, int j)
    {
        ModuleManager modulemanager;
        Compilation acompilation[];
        ModuleInfo amoduleinfo[];
        SourceMessages sourcemessages;
        int k;
        modulemanager = ModuleManager.getInstance();
        acompilation = new Compilation[j - i];
        amoduleinfo = new ModuleInfo[j - i];
        sourcemessages = new SourceMessages();
        k = i;
_L2:
        String s1;
        Language language1;
        Compilation compilation1;
        if (k >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        s1 = as[k];
        getLanguageFromFilenameExtension(s1);
        language1 = Language.getDefaultLanguage();
        compilation1 = null;
        InPort inport = InPort.openFile(s1);
        compilation1 = language1.parse(inport, sourcemessages, defaultParseOptions);
        if (compilationTopname != null)
        {
            ClassType classtype = new ClassType(Compilation.mangleNameIfNeeded(compilationTopname));
            ModuleExp moduleexp = compilation1.getModule();
            moduleexp.setType(classtype);
            moduleexp.setName(compilationTopname);
            compilation1.mainClass = classtype;
        }
        amoduleinfo[k - i] = modulemanager.find(compilation1);
        acompilation[k - i] = compilation1;
_L5:
        if (sourcemessages.seenErrorsOrWarnings())
        {
            System.err.println((new StringBuilder()).append("(compiling ").append(s1).append(')').toString());
            if (sourcemessages.checkErrors(System.err, 20))
            {
                System.exit(1);
            }
        }
        k++;
        if (true) goto _L2; else goto _L1
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        System.err.println(filenotfoundexception);
        System.exit(-1);
_L1:
        int l = i;
_L4:
        String s;
        Compilation compilation;
        if (l >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        s = as[l];
        compilation = acompilation[l - i];
        boolean flag;
        System.err.println((new StringBuilder()).append("(compiling ").append(s).append(" to ").append(compilation.mainClass.getName()).append(')').toString());
        amoduleinfo[l - i].loadByStages(14);
        flag = sourcemessages.seenErrors();
        sourcemessages.checkErrors(System.err, 50);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_336;
        }
        System.exit(-1);
        boolean flag1;
        acompilation[l - i] = compilation;
        flag1 = sourcemessages.seenErrors();
        sourcemessages.checkErrors(System.err, 50);
        Throwable throwable1;
        if (flag1)
        {
            try
            {
                System.exit(-1);
            }
            catch (Throwable throwable)
            {
                internalError(throwable, compilation, s);
            }
        }
        l++;
        if (true) goto _L4; else goto _L3
        throwable1;
        if (!(throwable1 instanceof SyntaxException) || ((SyntaxException)throwable1).getMessages() != sourcemessages)
        {
            internalError(throwable1, compilation1, s1);
        }
          goto _L5
_L3:
    }

    public static void getLanguage()
    {
        if (previousLanguage == null)
        {
            previousLanguage = Language.getInstance(null);
            Language.setDefaults(previousLanguage);
        }
    }

    public static void getLanguageFromFilenameExtension(String s)
    {
        if (previousLanguage == null)
        {
            previousLanguage = Language.getInstanceFromFilenameExtension(s);
            if (previousLanguage != null)
            {
                Language.setDefaults(previousLanguage);
                return;
            }
        }
        getLanguage();
    }

    static void internalError(Throwable throwable, Compilation compilation, Object obj)
    {
        StringBuffer stringbuffer = new StringBuffer();
        if (compilation != null)
        {
            String s = compilation.getFileName();
            int i = compilation.getLineNumber();
            if (s != null && i > 0)
            {
                stringbuffer.append(s);
                stringbuffer.append(':');
                stringbuffer.append(i);
                stringbuffer.append(": ");
            }
        }
        stringbuffer.append("internal error while compiling ");
        stringbuffer.append(obj);
        System.err.println(stringbuffer.toString());
        throwable.printStackTrace(System.err);
        System.exit(-1);
    }

    public static void main(String args[])
    {
        int i = processArgs(args, 0, args.length);
        if (i < 0)
        {
            if (!shutdownRegistered)
            {
                OutPort.runCleanups();
            }
            ModuleBody.exitDecrement();
            return;
        }
        if (i >= args.length) goto _L2; else goto _L1
_L1:
        String s = args[i];
        getLanguageFromFilenameExtension(s);
        setArgs(args, i + 1);
        checkInitFile();
        Shell.runFileOrClass(s, false, 0);
_L5:
        if (!shutdownRegistered)
        {
            OutPort.runCleanups();
        }
        ModuleBody.exitDecrement();
        return;
_L2:
        getLanguage();
        setArgs(args, i);
        checkInitFile();
        if (!shouldUseGuiConsole()) goto _L4; else goto _L3
_L3:
        startGuiConsole();
          goto _L5
        Exception exception;
        exception;
        if (!shutdownRegistered)
        {
            OutPort.runCleanups();
        }
        ModuleBody.exitDecrement();
        throw exception;
_L4:
        if (Shell.run(Language.getDefaultLanguage(), Environment.getCurrent())) goto _L5; else goto _L6
_L6:
        System.exit(-1);
          goto _L5
    }

    public static void printOption(PrintStream printstream, String s, String s1)
    {
        printstream.print(" ");
        printstream.print(s);
        int i = 1 + s.length();
        for (int j = 0; j < 30 - i; j++)
        {
            printstream.print(" ");
        }

        printstream.print(" ");
        printstream.println(s1);
    }

    public static void printOptions(PrintStream printstream)
    {
        printstream.println("Usage: [java kawa.repl | kawa] [options ...]");
        printstream.println();
        printstream.println(" Generic options:");
        printOption(printstream, "--help", "Show help about options");
        printOption(printstream, "--author", "Show author information");
        printOption(printstream, "--version", "Show version information");
        printstream.println();
        printstream.println(" Options");
        printOption(printstream, "-e <expr>", "Evaluate expression <expr>");
        printOption(printstream, "-c <expr>", "Same as -e, but make sure ~/.kawarc.scm is run first");
        printOption(printstream, "-f <filename>", "File to interpret");
        printOption(printstream, "-s| --", "Start reading commands interactively from console");
        printOption(printstream, "-w", "Launch the interpreter in a GUI window");
        printOption(printstream, "--server <port>", "Start a server accepting telnet connections on <port>");
        printOption(printstream, "--debug-dump-zip", "Compiled interactive expressions to a zip archive");
        printOption(printstream, "--debug-print-expr", "Print generated internal expressions");
        printOption(printstream, "--debug-print-final-expr", "Print expression after any optimizations");
        printOption(printstream, "--debug-error-prints-stack-trace", "Print stack trace with errors");
        printOption(printstream, "--debug-warning-prints-stack-trace", "Print stack trace with warnings");
        printOption(printstream, "--[no-]full-tailcalls", "(Don't) use full tail-calls");
        printOption(printstream, "-C <filename> ...", "Compile named files to Java class files");
        printOption(printstream, "--output-format <format>", "Use <format> when printing top-level output");
        printOption(printstream, "--<language>", "Select source language, one of:");
        String as[][] = Language.getLanguages();
        for (int i = 0; i < as.length; i++)
        {
            printstream.print("   ");
            String as1[] = as[i];
            int k = -1 + as1.length;
            for (int l = 0; l < k; l++)
            {
                printstream.print((new StringBuilder()).append(as1[l]).append(" ").toString());
            }

            if (i == 0)
            {
                printstream.print("[default]");
            }
            printstream.println();
        }

        printstream.println(" Compilation options, must be specified before -C");
        printOption(printstream, "-d <dirname>", "Directory to place .class files in");
        printOption(printstream, "-P <prefix>", "Prefix to prepand to class names");
        printOption(printstream, "-T <topname>", "name to give to top-level class");
        printOption(printstream, "--main", "Generate an application, with a main method");
        printOption(printstream, "--applet", "Generate an applet");
        printOption(printstream, "--servlet", "Generate a servlet");
        printOption(printstream, "--module-static", "Top-level definitions are by default static");
        ArrayList arraylist = Compilation.options.keys();
        for (int j = 0; j < arraylist.size(); j++)
        {
            String s = (String)arraylist.get(j);
            printOption(printstream, (new StringBuilder()).append("--").append(s).toString(), Compilation.options.getDoc(s));
        }

        printstream.println();
        printstream.println("For more information go to:  http://www.gnu.org/software/kawa/");
    }

    public static int processArgs(String as[], int i, int j)
    {
        boolean flag = false;
_L2:
        String s;
        if (i >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        s = as[i];
        if (s.equals("-c") || s.equals("-e"))
        {
            if (++i == j)
            {
                bad_option(s);
            }
            getLanguage();
            setArgs(as, i + 1);
            if (s.equals("-c"))
            {
                checkInitFile();
            }
            Language language1 = Language.getDefaultLanguage();
            SourceMessages sourcemessages = new SourceMessages();
            Throwable throwable = Shell.run(language1, Environment.getCurrent(), new CharArrayInPort(as[i]), OutPort.outDefault(), null, sourcemessages);
            if (throwable != null)
            {
                Shell.printError(throwable, sourcemessages, OutPort.errDefault());
                System.exit(-1);
            }
            flag = true;
        } else
        {
label0:
            {
                if (!s.equals("-f"))
                {
                    break label0;
                }
                if (++i == j)
                {
                    bad_option(s);
                }
                String s8 = as[i];
                getLanguageFromFilenameExtension(s8);
                setArgs(as, i + 1);
                checkInitFile();
                if (!Shell.runFileOrClass(s8, true, 0))
                {
                    System.exit(-1);
                }
                flag = true;
            }
        }
_L19:
        i++;
        if (true) goto _L2; else goto _L1
_L1:
        break; /* Loop/switch isn't completed */
        if (!s.startsWith("--script")) goto _L4; else goto _L3
_L3:
        String s6;
        int k4;
        int i5;
        s6 = s.substring(8);
        k4 = i + 1;
        int l4 = s6.length();
        i5 = 0;
        if (l4 <= 0)
        {
            break MISSING_BLOCK_LABEL_252;
        }
        int j5 = Integer.parseInt(s6);
        i5 = j5;
_L5:
        if (k4 == j)
        {
            bad_option(s);
        }
        String s7 = as[k4];
        getLanguageFromFilenameExtension(s7);
        setArgs(as, k4 + 1);
        checkInitFile();
        if (!Shell.runFileOrClass(s7, true, i5))
        {
            System.exit(-1);
        }
        return -1;
        Throwable throwable3;
        throwable3;
        k4 = j;
        i5 = 0;
        if (true) goto _L5; else goto _L4
_L4:
        if (!s.equals("\\")) goto _L7; else goto _L6
_L6:
        int i3;
        String s5;
        SourceMessages sourcemessages1;
        i3 = i + 1;
        if (i3 == j)
        {
            bad_option(s);
        }
        s5 = as[i3];
        sourcemessages1 = new SourceMessages();
        BufferedInputStream bufferedinputstream;
        int j3;
        bufferedinputstream = new BufferedInputStream(new FileInputStream(s5));
        j3 = bufferedinputstream.read();
        if (j3 != 35) goto _L9; else goto _L8
_L8:
        StringBuffer stringbuffer;
        Vector vector;
        stringbuffer = new StringBuffer(100);
        vector = new Vector(10);
_L10:
        int k3;
        k3 = 0;
        if (j3 == 10)
        {
            break MISSING_BLOCK_LABEL_447;
        }
        k3 = 0;
        if (j3 == 13)
        {
            break MISSING_BLOCK_LABEL_447;
        }
        k3 = 0;
        if (j3 < 0)
        {
            break MISSING_BLOCK_LABEL_447;
        }
        j3 = bufferedinputstream.read();
          goto _L10
_L16:
        int l3 = bufferedinputstream.read();
        if (l3 >= 0) goto _L12; else goto _L11
_L11:
        System.err.println((new StringBuilder()).append("unexpected end-of-file processing argument line for: '").append(s5).append('\'').toString());
        System.exit(-1);
          goto _L12
_L29:
        int i4;
        if (stringbuffer.length() > 0)
        {
            vector.addElement(stringbuffer.toString());
        }
        i4 = vector.size();
        if (i4 <= 0) goto _L9; else goto _L13
_L13:
        int j4;
        String as1[] = new String[i4];
        vector.copyInto(as1);
        j4 = processArgs(as1, 0, i4);
        if (j4 < 0 || j4 >= i4) goto _L9; else goto _L14
_L14:
        System.err.println((new StringBuilder()).append("").append(i4 - j4).append(" unused meta args").toString());
_L9:
        Throwable throwable2;
        getLanguageFromFilenameExtension(s5);
        InPort inport = InPort.openFile(bufferedinputstream, s5);
        setArgs(as, i3 + 1);
        checkInitFile();
        OutPort outport = OutPort.errDefault();
        throwable2 = Shell.run(Language.getDefaultLanguage(), Environment.getCurrent(), inport, OutPort.outDefault(), null, sourcemessages1);
        sourcemessages1.printAll(outport, 20);
        if (throwable2 != null)
        {
            try
            {
                if ((throwable2 instanceof SyntaxException) && ((SyntaxException)throwable2).getMessages() == sourcemessages1)
                {
                    System.exit(1);
                }
                throw throwable2;
            }
            catch (Throwable throwable1)
            {
                Shell.printError(throwable1, sourcemessages1, OutPort.errDefault());
            }
            System.exit(1);
        }
        return -1;
_L30:
        if (l3 != 32 && l3 != 9)
        {
            break MISSING_BLOCK_LABEL_750;
        }
        if (stringbuffer.length() <= 0) goto _L16; else goto _L15
_L15:
        vector.addElement(stringbuffer.toString());
        stringbuffer.setLength(0);
          goto _L16
_L18:
        stringbuffer.append((char)l3);
          goto _L16
_L32:
        if (l3 != k3) goto _L18; else goto _L17
_L17:
        k3 = 0;
          goto _L16
_L7:
        if (s.equals("-s") || s.equals("--"))
        {
            int k = i + 1;
            getLanguage();
            setArgs(as, k);
            checkInitFile();
            Shell.run(Language.getDefaultLanguage(), Environment.getCurrent());
            return -1;
        }
        if (s.equals("-w"))
        {
            i++;
            getLanguage();
            setArgs(as, i);
            checkInitFile();
            startGuiConsole();
            flag = true;
        } else
        if (s.equals("-d"))
        {
            if (++i == j)
            {
                bad_option(s);
            }
            ModuleManager.getInstance().setCompilationDirectory(as[i]);
        } else
        if (s.equals("--target") || s.equals("target"))
        {
            if (++i == j)
            {
                bad_option(s);
            }
            String s1 = as[i];
            if (s1.equals("7"))
            {
                Compilation.defaultClassFileVersion = 0x330000;
            }
            if (s1.equals("6") || s1.equals("1.6"))
            {
                Compilation.defaultClassFileVersion = 0x320000;
            } else
            if (s1.equals("5") || s1.equals("1.5"))
            {
                Compilation.defaultClassFileVersion = 0x310000;
            } else
            if (s1.equals("1.4"))
            {
                Compilation.defaultClassFileVersion = 0x300000;
            } else
            if (s1.equals("1.3"))
            {
                Compilation.defaultClassFileVersion = 0x2f0000;
            } else
            if (s1.equals("1.2"))
            {
                Compilation.defaultClassFileVersion = 0x2e0000;
            } else
            if (s1.equals("1.1"))
            {
                Compilation.defaultClassFileVersion = 0x2d0003;
            } else
            {
                bad_option(s1);
            }
        } else
        if (s.equals("-P"))
        {
            if (++i == j)
            {
                bad_option(s);
            }
            Compilation.classPrefixDefault = as[i];
        } else
        if (s.equals("-T"))
        {
            if (++i == j)
            {
                bad_option(s);
            }
            compilationTopname = as[i];
        } else
        {
label1:
            {
                if (s.equals("-C"))
                {
                    int l2 = i + 1;
                    if (l2 == j)
                    {
                        bad_option(s);
                    }
                    compileFiles(as, l2, j);
                    return -1;
                }
                if (!s.equals("--output-format") && !s.equals("--format"))
                {
                    break label1;
                }
                if (++i == j)
                {
                    bad_option(s);
                }
                Shell.setDefaultFormat(as[i]);
            }
        }
          goto _L19
        if (!s.equals("--connect"))
        {
            break MISSING_BLOCK_LABEL_1410;
        }
        if (++i == j)
        {
            bad_option(s);
        }
        if (!as[i].equals("-")) goto _L21; else goto _L20
_L20:
        int j2 = 0;
_L22:
        try
        {
            Socket socket1 = new Socket(InetAddress.getByName(null), j2);
            Telnet telnet = new Telnet(socket1, true);
            TelnetInputStream telnetinputstream = telnet.getInputStream();
            TelnetOutputStream telnetoutputstream = telnet.getOutputStream();
            PrintStream printstream = new PrintStream(telnetoutputstream, true);
            System.setIn(telnetinputstream);
            System.setOut(printstream);
            System.setErr(printstream);
        }
        catch (IOException ioexception1)
        {
            ioexception1.printStackTrace(System.err);
            throw new Error(ioexception1.toString());
        }
          goto _L19
_L21:
        int k2 = Integer.parseInt(as[i]);
        j2 = k2;
          goto _L22
        NumberFormatException numberformatexception1;
        numberformatexception1;
        bad_option("--connect port#");
        j2 = -1;
          goto _L22
        if (!s.equals("--server")) goto _L24; else goto _L23
_L23:
        int j1;
        getLanguage();
        j1 = i + 1;
        if (j1 == j)
        {
            bad_option(s);
        }
        if (!as[j1].equals("-")) goto _L26; else goto _L25
_L25:
        int k1 = 0;
_L27:
        try
        {
            ServerSocket serversocket = new ServerSocket(k1);
            int l1 = serversocket.getLocalPort();
            System.err.println((new StringBuilder()).append("Listening on port ").append(l1).toString());
            do
            {
                System.err.print("waiting ... ");
                System.err.flush();
                Socket socket = serversocket.accept();
                System.err.println((new StringBuilder()).append("got connection from ").append(socket.getInetAddress()).append(" port:").append(socket.getPort()).toString());
                TelnetRepl.serve(Language.getDefaultLanguage(), socket);
            } while (true);
        }
        catch (IOException ioexception)
        {
            throw new Error(ioexception.toString());
        }
_L26:
        int i2 = Integer.parseInt(as[j1]);
        k1 = i2;
        continue; /* Loop/switch isn't completed */
        NumberFormatException numberformatexception;
        numberformatexception;
        bad_option("--server port#");
        k1 = -1;
        if (true) goto _L27; else goto _L24
_L24:
        if (s.equals("--http-auto-handler"))
        {
            if ((i += 2) >= j)
            {
                bad_option(s);
            }
            System.err.println("kawa: HttpServer classes not found");
            System.exit(-1);
        } else
        if (s.equals("--http-start"))
        {
            if (++i >= j)
            {
                bad_option("missing httpd port argument");
            }
            System.err.println("kawa: HttpServer classes not found");
            System.exit(-1);
        } else
        if (s.equals("--main"))
        {
            Compilation.generateMainDefault = true;
        } else
        if (s.equals("--applet"))
        {
            defaultParseOptions = 0x10 | defaultParseOptions;
        } else
        if (s.equals("--servlet"))
        {
            defaultParseOptions = 0x20 | defaultParseOptions;
            HttpRequestContext.importServletDefinitions = 2;
        } else
        if (s.equals("--debug-dump-zip"))
        {
            ModuleExp.dumpZipPrefix = "kawa-zip-dump-";
        } else
        if (s.equals("--debug-print-expr"))
        {
            Compilation.debugPrintExpr = true;
        } else
        if (s.equals("--debug-print-final-expr"))
        {
            Compilation.debugPrintFinalExpr = true;
        } else
        if (s.equals("--debug-error-prints-stack-trace"))
        {
            SourceMessages.debugStackTraceOnError = true;
        } else
        if (s.equals("--debug-warning-prints-stack-trace"))
        {
            SourceMessages.debugStackTraceOnWarning = true;
        } else
        if (s.equals("--module-nonstatic") || s.equals("--no-module-static"))
        {
            Compilation.moduleStatic = -1;
        } else
        if (s.equals("--module-static"))
        {
            Compilation.moduleStatic = 1;
        } else
        if (s.equals("--module-static-run"))
        {
            Compilation.moduleStatic = 2;
        } else
        if (s.equals("--no-inline") || s.equals("--inline=none"))
        {
            Compilation.inlineOk = false;
        } else
        if (s.equals("--no-console"))
        {
            noConsole = true;
        } else
        if (s.equals("--inline"))
        {
            Compilation.inlineOk = true;
        } else
        if (s.equals("--cps"))
        {
            Compilation.defaultCallConvention = 4;
        } else
        if (s.equals("--full-tailcalls"))
        {
            Compilation.defaultCallConvention = 3;
        } else
        if (s.equals("--no-full-tailcalls"))
        {
            Compilation.defaultCallConvention = 1;
        } else
        if (s.equals("--pedantic"))
        {
            Language.requirePedantic = true;
        } else
        if (s.equals("--help"))
        {
            printOptions(System.out);
            System.exit(0);
        } else
        if (s.equals("--author"))
        {
            System.out.println("Per Bothner <per@bothner.com>");
            System.exit(0);
        } else
        if (s.equals("--version"))
        {
            System.out.print("Kawa ");
            System.out.print(Version.getVersion());
            System.out.println();
            System.out.println("Copyright (C) 2009 Per Bothner");
            flag = true;
        } else
        {
            if (s.length() <= 0 || s.charAt(0) != '-')
            {
                continue; /* Loop/switch isn't completed */
            }
            String s2 = s;
            Language language2;
            if (s2.length() > 2 && s2.charAt(0) == '-')
            {
                byte byte0;
                if (s2.charAt(1) == '-')
                {
                    byte0 = 2;
                } else
                {
                    byte0 = 1;
                }
                s2 = s2.substring(byte0);
            }
            language2 = Language.getInstance(s2);
            if (language2 != null)
            {
                if (previousLanguage == null)
                {
                    Language.setDefaults(language2);
                } else
                {
                    Language.setCurrentLanguage(language2);
                }
                previousLanguage = language2;
            } else
            {
                int l = s2.indexOf("=");
                String s3;
                boolean flag1;
                String s4;
                if (l < 0)
                {
                    s3 = null;
                } else
                {
                    int i1 = l + 1;
                    s3 = s2.substring(i1);
                    s2 = s2.substring(0, l);
                }
                if (s2.startsWith("no-") && s2.length() > 3)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (s3 == null && flag1)
                {
                    s3 = "no";
                    s2 = s2.substring(3);
                }
                s4 = Compilation.options.set(s2, s3);
                if (s4 != null)
                {
                    if (flag1 && s4 == "unknown option name")
                    {
                        s4 = (new StringBuilder()).append("both '--no-' prefix and '=").append(s3).append("' specified").toString();
                    }
                    if (s4 == "unknown option name")
                    {
                        bad_option(s);
                    } else
                    {
                        System.err.println((new StringBuilder()).append("kawa: bad option '").append(s).append("': ").append(s4).toString());
                        System.exit(-1);
                    }
                }
            }
        }
          goto _L19
        if (ApplicationMainSupport.processSetProperty(s)) goto _L19; else goto _L28
_L28:
        if (flag)
        {
            return -1;
        } else
        {
            return i;
        }
_L12:
        if (k3 != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (l3 != 92 && l3 != 39 && l3 != 34)
        {
            continue; /* Loop/switch isn't completed */
        }
        k3 = l3;
          goto _L16
        if (l3 != 10 && l3 != 13) goto _L30; else goto _L29
        if (k3 != 92) goto _L32; else goto _L31
_L31:
        k3 = 0;
          goto _L18
    }

    public static void setArgs(String as[], int i)
    {
        ApplicationMainSupport.setArgs(as, i);
    }

    public static boolean shouldUseGuiConsole()
    {
        if (!noConsole) goto _L2; else goto _L1
_L1:
        Object obj;
        return true;
_L2:
        if ((obj = Class.forName("java.lang.System").getMethod("console", new Class[0]).invoke(((Object) (new Object[0])), new Object[0])) == null) goto _L1; else goto _L3
_L3:
        return false;
        Throwable throwable;
        throwable;
        if (true) goto _L3; else goto _L4
_L4:
    }

    private static void startGuiConsole()
    {
        try
        {
            Class.forName("kawa.GuiConsole").newInstance();
            return;
        }
        catch (Exception exception)
        {
            System.err.println((new StringBuilder()).append("failed to create Kawa window: ").append(exception).toString());
        }
        System.exit(-1);
    }

    public Object apply0()
    {
        Shell.run(language, Environment.getCurrent());
        return Values.empty;
    }

    public Object apply1(Object obj)
    {
        Shell.run(language, (Environment)obj);
        return Values.empty;
    }

    static 
    {
        shutdownRegistered = WriterManager.instance.registerShutdownHook();
    }
}
