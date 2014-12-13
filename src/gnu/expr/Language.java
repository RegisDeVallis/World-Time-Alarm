// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.kawa.lispexpr.ClassNamespace;
import gnu.kawa.reflect.ClassMemberLocation;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.AbstractFormat;
import gnu.lists.CharSeq;
import gnu.lists.Consumer;
import gnu.lists.Convert;
import gnu.lists.FString;
import gnu.lists.PrintConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.InPort;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.NamedLocation;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kawa.repl;

// Referenced classes of package gnu.expr:
//            BuiltinEnvironment, KawaConvert, Compilation, ModuleExp, 
//            Declaration, SimplePrompter, QuoteExp, ReferenceExp, 
//            ClassExp, LambdaExp, ModuleBody, NameLookup, 
//            ModuleInfo, Expression

public abstract class Language
{

    public static final int FUNCTION_NAMESPACE = 2;
    public static final int NAMESPACE_PREFIX_NAMESPACE = 4;
    public static final int PARSE_CURRENT_NAMES = 2;
    public static final int PARSE_EXPLICIT = 64;
    public static final int PARSE_FOR_APPLET = 16;
    public static final int PARSE_FOR_EVAL = 3;
    public static final int PARSE_FOR_SERVLET = 32;
    public static final int PARSE_IMMEDIATE = 1;
    public static final int PARSE_ONE_LINE = 4;
    public static final int PARSE_PROLOG = 8;
    public static final int VALUE_NAMESPACE = 1;
    protected static final InheritableThreadLocal current = new InheritableThreadLocal();
    static int envCounter;
    protected static int env_counter = 0;
    protected static Language global;
    static String languages[][] = {
        {
            "scheme", ".scm", ".sc", "kawa.standard.Scheme"
        }, {
            "krl", ".krl", "gnu.kawa.brl.BRL"
        }, {
            "brl", ".brl", "gnu.kawa.brl.BRL"
        }, {
            "emacs", "elisp", "emacs-lisp", ".el", "gnu.jemacs.lang.ELisp"
        }, {
            "xquery", ".xquery", ".xq", ".xql", "gnu.xquery.lang.XQuery"
        }, {
            "q2", ".q2", "gnu.q2.lang.Q2"
        }, {
            "xslt", "xsl", ".xsl", "gnu.kawa.xslt.XSLT"
        }, {
            "commonlisp", "common-lisp", "clisp", "lisp", ".lisp", ".lsp", ".cl", "gnu.commonlisp.lang.CommonLisp"
        }
    };
    public static boolean requirePedantic;
    protected Environment environ;
    protected Environment userEnv;

    protected Language()
    {
        Convert.setInstance(KawaConvert.getInstance());
    }

    public static Language detect(InPort inport)
        throws IOException
    {
        StringBuffer stringbuffer = new StringBuffer();
        inport.mark(300);
        inport.readLine(stringbuffer, 'P');
        inport.reset();
        return detect(stringbuffer.toString());
    }

    public static Language detect(InputStream inputstream)
        throws IOException
    {
        StringBuffer stringbuffer;
        if (!inputstream.markSupported())
        {
            return null;
        }
        stringbuffer = new StringBuffer();
        inputstream.mark(200);
_L5:
        if (stringbuffer.length() < 200) goto _L2; else goto _L1
_L1:
        int i;
        inputstream.reset();
        return detect(stringbuffer.toString());
_L2:
        if ((i = inputstream.read()) < 0 || i == 10 || i == 13) goto _L1; else goto _L3
_L3:
        stringbuffer.append((char)i);
        if (true) goto _L5; else goto _L4
_L4:
    }

    public static Language detect(String s)
    {
        String s1 = s.trim();
        int i = s1.indexOf("kawa:");
        if (i >= 0)
        {
            int j = i + 5;
            int k;
            for (k = j; k < s1.length() && Character.isJavaIdentifierPart(s1.charAt(k)); k++) { }
            if (k > j)
            {
                Language language = getInstance(s1.substring(j, k));
                if (language != null)
                {
                    return language;
                }
            }
        }
        if (s1.indexOf("-*- scheme -*-") >= 0)
        {
            return getInstance("scheme");
        }
        if (s1.indexOf("-*- xquery -*-") >= 0)
        {
            return getInstance("xquery");
        }
        if (s1.indexOf("-*- emacs-lisp -*-") >= 0)
        {
            return getInstance("elisp");
        }
        if (s1.indexOf("-*- common-lisp -*-") >= 0 || s1.indexOf("-*- lisp -*-") >= 0)
        {
            return getInstance("common-lisp");
        }
        if (s1.charAt(0) == '(' && s1.charAt(1) == ':' || s1.length() >= 7 && s1.substring(0, 7).equals("xquery "))
        {
            return getInstance("xquery");
        }
        if (s1.charAt(0) == ';' && s1.charAt(1) == ';')
        {
            return getInstance("scheme");
        } else
        {
            return null;
        }
    }

    public static Language getDefaultLanguage()
    {
        Language language = (Language)current.get();
        if (language != null)
        {
            return language;
        } else
        {
            return global;
        }
    }

    public static Language getInstance(String s)
    {
        int i;
        int j;
        i = languages.length;
        j = 0;
_L3:
        if (j >= i) goto _L2; else goto _L1
_L1:
        String as[];
        int k;
        as = languages[j];
        k = -1 + as.length;
        int l = k;
        do
        {
            if (--l < 0)
            {
                continue; /* Loop/switch isn't completed */
            }
        } while (s != null && !as[l].equalsIgnoreCase(s));
        Class class1 = Class.forName(as[k]);
        return getInstance(as[0], class1);
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        j++;
          goto _L3
_L2:
        return null;
    }

    public static Language getInstance(String s, Class class1)
    {
        Class aclass[];
        Exception exception;
        Method method;
        Method method1;
        String s2;
        Method method2;
        try
        {
            aclass = new Class[0];
        }
        catch (Exception exception1)
        {
            String s1 = class1.getName();
            Object obj;
            if (exception1 instanceof InvocationTargetException)
            {
                obj = ((InvocationTargetException)exception1).getTargetException();
            } else
            {
                obj = exception1;
            }
            throw new WrappedException((new StringBuilder()).append("getInstance for '").append(s1).append("' failed").toString(), ((Throwable) (obj)));
        }
        s2 = (new StringBuilder()).append(Character.toTitleCase(s.charAt(0))).append(s.substring(1).toLowerCase()).toString();
        method2 = class1.getDeclaredMethod((new StringBuilder()).append("get").append(s2).append("Instance").toString(), aclass);
        method1 = method2;
_L2:
        return (Language)method1.invoke(null, Values.noArgs);
        exception;
        method = class1.getDeclaredMethod("getInstance", aclass);
        method1 = method;
        if (true) goto _L2; else goto _L1
_L1:
    }

    public static Language getInstanceFromFilenameExtension(String s)
    {
        int i = s.lastIndexOf('.');
        if (i > 0)
        {
            Language language = getInstance(s.substring(i));
            if (language != null)
            {
                return language;
            }
        }
        return null;
    }

    public static String[][] getLanguages()
    {
        return languages;
    }

    public static void registerLanguage(String as[])
    {
        String as1[][] = new String[1 + languages.length][];
        System.arraycopy(languages, 0, as1, 0, languages.length);
        as1[-1 + as1.length] = as;
        languages = as1;
    }

    public static void restoreCurrent(Language language)
    {
        current.set(language);
    }

    public static void setCurrentLanguage(Language language)
    {
        current.set(language);
    }

    public static void setDefaults(Language language)
    {
        gnu/expr/Language;
        JVM INSTR monitorenter ;
        setCurrentLanguage(language);
        global = language;
        if (Environment.getGlobal() == BuiltinEnvironment.getInstance())
        {
            Environment.setGlobal(Environment.getCurrent());
        }
        gnu/expr/Language;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static Language setSaveCurrent(Language language)
    {
        Language language1 = (Language)current.get();
        current.set(language);
        return language1;
    }

    public static Type string2Type(String s)
    {
        if (s.endsWith("[]"))
        {
            Type type = string2Type(s.substring(0, -2 + s.length()));
            if (type == null)
            {
                return null;
            } else
            {
                return ArrayType.make(type);
            }
        }
        if (Type.isValidJavaTypeName(s))
        {
            return Type.getType(s);
        } else
        {
            return null;
        }
    }

    public static Type unionType(Type type, Type type1)
    {
        if (type == Type.toStringType)
        {
            type = Type.javalangStringType;
        }
        if (type1 == Type.toStringType)
        {
            type1 = Type.javalangStringType;
        }
        if (type != type1)
        {
            if ((type instanceof PrimType) && (type1 instanceof PrimType))
            {
                char c = type.getSignature().charAt(0);
                char c1 = type1.getSignature().charAt(0);
                if (c != c1)
                {
                    if ((c == 'B' || c == 'S' || c == 'I') && (c1 == 'I' || c1 == 'J'))
                    {
                        return type1;
                    }
                    if (c1 != 'B' && c1 != 'S' && c1 != 'I' || c != 'I' && c != 'J')
                    {
                        if (c == 'F' && c1 == 'D')
                        {
                            return type1;
                        }
                        if (c1 != 'F' || c != 'D')
                        {
                            return Type.objectType;
                        }
                    }
                }
            } else
            {
                return Type.objectType;
            }
        }
        return type;
    }

    public final Type asType(Object obj)
    {
        Type type = getTypeFor(obj, true);
        if (type == null)
        {
            return (Type)obj;
        } else
        {
            return type;
        }
    }

    public Object booleanObject(boolean flag)
    {
        if (flag)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public Object coerceFromObject(Class class1, Object obj)
    {
        return getTypeFor(class1).coerceFromObject(obj);
    }

    public Object coerceToObject(Class class1, Object obj)
    {
        return getTypeFor(class1).coerceToObject(obj);
    }

    public Declaration declFromField(ModuleExp moduleexp, Object obj, Field field)
    {
        Type type;
        boolean flag2;
        Declaration declaration;
        String s = field.getName();
        type = field.getType();
        boolean flag = type.isSubtype(Compilation.typeLocation);
        boolean flag1 = false;
        boolean flag3;
        Object obj1;
        Object obj2;
        boolean flag5;
        if ((0x10 & field.getModifiers()) != 0)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        flag3 = s.endsWith("$instance");
        if (flag3)
        {
            obj1 = s;
        } else
        if (flag2 && (obj instanceof Named))
        {
            obj1 = ((Named)obj).getSymbol();
            flag1 = false;
        } else
        {
            boolean flag4 = s.startsWith("$Prvt$");
            flag1 = false;
            if (flag4)
            {
                flag1 = true;
                s = s.substring("$Prvt$".length());
            }
            obj1 = Compilation.demangleName(s, true).intern();
        }
        if (obj1 instanceof String)
        {
            String s1 = moduleexp.getNamespaceUri();
            String s2 = (String)obj1;
            if (s1 == null)
            {
                obj1 = SimpleSymbol.valueOf(s2);
            } else
            {
                obj1 = Symbol.make(s1, s2);
            }
        }
        if (flag)
        {
            obj2 = Type.objectType;
        } else
        {
            obj2 = getTypeFor(type.getReflectClass());
        }
        declaration = moduleexp.addDeclaration(obj1, ((Type) (obj2)));
        if ((8 & field.getModifiers()) != 0)
        {
            flag5 = true;
        } else
        {
            flag5 = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        declaration.setIndirectBinding(true);
        if ((type instanceof ClassType) && ((ClassType)type).isSubclass("gnu.mapping.ThreadLocation"))
        {
            declaration.setFlag(0x10000000L);
        }
_L4:
        if (flag5)
        {
            declaration.setFlag(2048L);
        }
        declaration.field = field;
        if (flag2 && !flag)
        {
            declaration.setFlag(16384L);
        }
        if (flag3)
        {
            declaration.setFlag(0x40000000L);
        }
        declaration.setSimple(false);
        if (flag1)
        {
            declaration.setFlag(0x80020L);
        }
        return declaration;
_L2:
        if (flag2 && (type instanceof ClassType))
        {
            if (type.isSubtype(Compilation.typeProcedure))
            {
                declaration.setProcedureDecl(true);
            } else
            if (((ClassType)type).isSubclass("gnu.mapping.Namespace"))
            {
                declaration.setFlag(0x200000L);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    protected void defAliasStFld(String s, String s1, String s2)
    {
        StaticFieldLocation.define(environ, getSymbol(s), null, s1, s2);
    }

    protected void defProcStFld(String s, String s1)
    {
        defProcStFld(s, s1, Compilation.mangleNameIfNeeded(s));
    }

    protected void defProcStFld(String s, String s1, String s2)
    {
        Object obj;
        Symbol symbol;
        if (hasSeparateFunctionNamespace())
        {
            obj = EnvironmentKey.FUNCTION;
        } else
        {
            obj = null;
        }
        symbol = getSymbol(s);
        StaticFieldLocation.define(environ, symbol, obj, s1, s2).setProcedure();
    }

    public void define(String s, Object obj)
    {
        Symbol symbol = getSymbol(s);
        environ.define(symbol, null, obj);
    }

    public final void defineFunction(Named named)
    {
        Object obj = named.getSymbol();
        Symbol symbol;
        Object obj1;
        if (obj instanceof Symbol)
        {
            symbol = (Symbol)obj;
        } else
        {
            symbol = getSymbol(obj.toString());
        }
        if (hasSeparateFunctionNamespace())
        {
            obj1 = EnvironmentKey.FUNCTION;
        } else
        {
            obj1 = null;
        }
        environ.define(symbol, obj1, named);
    }

    public void defineFunction(String s, Object obj)
    {
        Object obj1;
        if (hasSeparateFunctionNamespace())
        {
            obj1 = EnvironmentKey.FUNCTION;
        } else
        {
            obj1 = null;
        }
        environ.define(getSymbol(s), obj1, obj);
    }

    public void emitCoerceToBoolean(CodeAttr codeattr)
    {
        emitPushBoolean(false, codeattr);
        codeattr.emitIfNEq();
        codeattr.emitPushInt(1);
        codeattr.emitElse();
        codeattr.emitPushInt(0);
        codeattr.emitFi();
    }

    public void emitPushBoolean(boolean flag, CodeAttr codeattr)
    {
        Field field;
        if (flag)
        {
            field = Compilation.trueConstant;
        } else
        {
            field = Compilation.falseConstant;
        }
        codeattr.emitGetStatic(field);
    }

    public final Object eval(InPort inport)
        throws Throwable
    {
        CallContext callcontext = CallContext.getInstance();
        int i = callcontext.startFromContext();
        Object obj;
        try
        {
            eval(inport, callcontext);
            obj = callcontext.getFromContext(i);
        }
        catch (Throwable throwable)
        {
            callcontext.cleanupFromContext(i);
            throw throwable;
        }
        return obj;
    }

    public final Object eval(Reader reader)
        throws Throwable
    {
        InPort inport;
        if (reader instanceof InPort)
        {
            inport = (InPort)reader;
        } else
        {
            inport = new InPort(reader);
        }
        return eval(inport);
    }

    public final Object eval(String s)
        throws Throwable
    {
        return eval(((InPort) (new CharArrayInPort(s))));
    }

    public void eval(InPort inport, CallContext callcontext)
        throws Throwable
    {
        SourceMessages sourcemessages;
        Language language;
        sourcemessages = new SourceMessages();
        language = setSaveCurrent(this);
        Compilation compilation = parse(inport, sourcemessages, 3);
        ModuleExp.evalModule(getEnvironment(), callcontext, compilation, null, null);
        restoreCurrent(language);
        Exception exception;
        if (sourcemessages.seenErrors())
        {
            throw new RuntimeException((new StringBuilder()).append("invalid syntax in eval form:\n").append(sourcemessages.toString(20)).toString());
        } else
        {
            return;
        }
        exception;
        restoreCurrent(language);
        throw exception;
    }

    public void eval(Reader reader, Consumer consumer)
        throws Throwable
    {
        CallContext callcontext;
        Consumer consumer1;
        InPort inport;
        if (reader instanceof InPort)
        {
            inport = (InPort)reader;
        } else
        {
            inport = new InPort(reader);
        }
        callcontext = CallContext.getInstance();
        consumer1 = callcontext.consumer;
        callcontext.consumer = consumer;
        eval(inport, callcontext);
        callcontext.consumer = consumer1;
        return;
        Exception exception;
        exception;
        callcontext.consumer = consumer1;
        throw exception;
    }

    public final void eval(Reader reader, Writer writer)
        throws Throwable
    {
        eval(reader, getOutputConsumer(writer));
    }

    public final void eval(String s, Consumer consumer)
        throws Throwable
    {
        eval(((Reader) (new CharArrayInPort(s))), consumer);
    }

    public final void eval(String s, PrintConsumer printconsumer)
        throws Throwable
    {
        eval(s, getOutputConsumer(printconsumer));
    }

    public final void eval(String s, Writer writer)
        throws Throwable
    {
        eval(((Reader) (new CharArrayInPort(s))), writer);
    }

    public String formatType(Type type)
    {
        return type.getName();
    }

    public Compilation getCompilation(Lexer lexer, SourceMessages sourcemessages, NameLookup namelookup)
    {
        return new Compilation(this, sourcemessages, namelookup);
    }

    public Object getEnvPropertyFor(Declaration declaration)
    {
        if (hasSeparateFunctionNamespace() && declaration.isProcedureDecl())
        {
            return EnvironmentKey.FUNCTION;
        } else
        {
            return null;
        }
    }

    public Object getEnvPropertyFor(java.lang.reflect.Field field, Object obj)
    {
        while (!hasSeparateFunctionNamespace() || !Compilation.typeProcedure.getReflectClass().isAssignableFrom(field.getType())) 
        {
            return null;
        }
        return EnvironmentKey.FUNCTION;
    }

    public final Environment getEnvironment()
    {
        if (userEnv != null)
        {
            return userEnv;
        } else
        {
            return Environment.getCurrent();
        }
    }

    public AbstractFormat getFormat(boolean flag)
    {
        return null;
    }

    public Environment getLangEnvironment()
    {
        return environ;
    }

    public final Type getLangTypeFor(Type type)
    {
        if (type.isExisting())
        {
            Class class1 = type.getReflectClass();
            if (class1 != null)
            {
                type = getTypeFor(class1);
            }
        }
        return type;
    }

    public abstract Lexer getLexer(InPort inport, SourceMessages sourcemessages);

    public String getName()
    {
        String s = getClass().getName();
        int i = s.lastIndexOf('.');
        if (i >= 0)
        {
            s = s.substring(i + 1);
        }
        return s;
    }

    public int getNamespaceOf(Declaration declaration)
    {
        return 1;
    }

    public final Environment getNewEnvironment()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("environment-");
        int i = 1 + envCounter;
        envCounter = i;
        return Environment.make(stringbuilder.append(i).toString(), environ);
    }

    public Consumer getOutputConsumer(Writer writer)
    {
        OutPort outport;
        if (writer instanceof OutPort)
        {
            outport = (OutPort)writer;
        } else
        {
            outport = new OutPort(writer);
        }
        outport.objectFormat = getFormat(false);
        return outport;
    }

    public Procedure getPrompter()
    {
        boolean flag = hasSeparateFunctionNamespace();
        Object obj = null;
        if (flag)
        {
            obj = EnvironmentKey.FUNCTION;
        }
        Procedure procedure = (Procedure)getEnvironment().get(getSymbol("default-prompter"), obj, null);
        if (procedure != null)
        {
            return procedure;
        } else
        {
            return new SimplePrompter();
        }
    }

    public Symbol getSymbol(String s)
    {
        return environ.getSymbol(s);
    }

    public final Type getTypeFor(Expression expression)
    {
        return getTypeFor(expression, true);
    }

    public Type getTypeFor(Expression expression, boolean flag)
    {
        if (!(expression instanceof QuoteExp)) goto _L2; else goto _L1
_L1:
        Object obj2 = ((QuoteExp)expression).getValue();
        if (!(obj2 instanceof Type)) goto _L4; else goto _L3
_L3:
        Type type = (Type)obj2;
_L11:
        return type;
_L4:
        if (obj2 instanceof Class)
        {
            return Type.make((Class)obj2);
        } else
        {
            return getTypeFor(obj2, flag);
        }
_L2:
        Declaration declaration;
        String s;
        if (!(expression instanceof ReferenceExp))
        {
            break; /* Loop/switch isn't completed */
        }
        ReferenceExp referenceexp = (ReferenceExp)expression;
        declaration = Declaration.followAliases(referenceexp.getBinding());
        s = referenceexp.getName();
        if (declaration == null) goto _L6; else goto _L5
_L5:
        Expression expression1;
        expression1 = declaration.getValue();
        if ((expression1 instanceof QuoteExp) && declaration.getFlag(16384L) && !declaration.isIndirectBinding())
        {
            return getTypeFor(((QuoteExp)expression1).getValue(), flag);
        }
        if ((expression1 instanceof ClassExp) || (expression1 instanceof ModuleExp))
        {
            declaration.setCanRead(true);
            return ((LambdaExp)expression1).getClassType();
        }
        if (!declaration.isAlias() || !(expression1 instanceof QuoteExp)) goto _L8; else goto _L7
_L7:
        Object obj1 = ((QuoteExp)expression1).getValue();
        if (obj1 instanceof Location)
        {
            Location location = (Location)obj1;
            if (location.isBound())
            {
                return getTypeFor(location.get(), flag);
            }
            boolean flag2 = location instanceof Named;
            type = null;
            if (!flag2)
            {
                continue; /* Loop/switch isn't completed */
            }
            s = ((Named)location).getName();
        }
_L6:
        Object obj;
        obj = getEnvironment().get(s);
        if (obj instanceof Type)
        {
            return (Type)obj;
        }
        break; /* Loop/switch isn't completed */
_L8:
        if (!declaration.getFlag(0x10000L))
        {
            return getTypeFor(expression1, flag);
        }
        if (true) goto _L6; else goto _L9
_L9:
        if (obj instanceof ClassNamespace)
        {
            return ((ClassNamespace)obj).getClassType();
        }
        int i = s.length();
        type = null;
        if (i > 2)
        {
            char c = s.charAt(0);
            type = null;
            if (c == '<')
            {
                char c1 = s.charAt(i - 1);
                type = null;
                if (c1 == '>')
                {
                    return getTypeFor(s.substring(1, i - 1));
                }
            }
        }
        if (true) goto _L11; else goto _L10
_L10:
        boolean flag1;
        if (expression instanceof ClassExp)
        {
            break; /* Loop/switch isn't completed */
        }
        flag1 = expression instanceof ModuleExp;
        type = null;
        if (!flag1) goto _L11; else goto _L12
_L12:
        return ((LambdaExp)expression).getClassType();
    }

    public Type getTypeFor(Class class1)
    {
        return Type.make(class1);
    }

    public final Type getTypeFor(Object obj, boolean flag)
    {
        if (obj instanceof Type)
        {
            return (Type)obj;
        }
        if (obj instanceof Class)
        {
            return getTypeFor((Class)obj);
        }
        if (flag && ((obj instanceof FString) || (obj instanceof String) || (obj instanceof Symbol) && ((Symbol)obj).hasEmptyNamespace() || (obj instanceof CharSeq)))
        {
            return getTypeFor(obj.toString());
        }
        if (obj instanceof Namespace)
        {
            String s = ((Namespace)obj).getName();
            if (s != null && s.startsWith("class:"))
            {
                return getLangTypeFor(string2Type(s.substring(6)));
            }
        }
        return null;
    }

    public Type getTypeFor(String s)
    {
        return string2Type(s);
    }

    public boolean hasNamespace(Declaration declaration, int i)
    {
        return (i & getNamespaceOf(declaration)) != 0;
    }

    public boolean hasSeparateFunctionNamespace()
    {
        return false;
    }

    public boolean isTrue(Object obj)
    {
        return obj != Boolean.FALSE;
    }

    public void loadClass(String s)
        throws ClassNotFoundException
    {
        Class class1;
        try
        {
            class1 = Class.forName(s);
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            throw classnotfoundexception;
        }
        try
        {
            Object obj = class1.newInstance();
            ClassMemberLocation.defineAll(obj, this, Environment.getCurrent());
            if (obj instanceof ModuleBody)
            {
                ((ModuleBody)obj).run();
            }
            return;
        }
        catch (Exception exception)
        {
            throw new WrappedException((new StringBuilder()).append("cannot load ").append(s).toString(), exception);
        }
    }

    public Object lookup(String s)
    {
        return environ.get(s);
    }

    public NamedLocation lookupBuiltin(Symbol symbol, Object obj, int i)
    {
        if (environ == null)
        {
            return null;
        } else
        {
            return environ.lookup(symbol, obj, i);
        }
    }

    public Object noValue()
    {
        return Values.empty;
    }

    public final Compilation parse(InPort inport, SourceMessages sourcemessages, int i)
        throws IOException, SyntaxException
    {
        return parse(getLexer(inport, sourcemessages), i, ((ModuleInfo) (null)));
    }

    public final Compilation parse(InPort inport, SourceMessages sourcemessages, int i, ModuleInfo moduleinfo)
        throws IOException, SyntaxException
    {
        return parse(getLexer(inport, sourcemessages), i, moduleinfo);
    }

    public final Compilation parse(InPort inport, SourceMessages sourcemessages, ModuleInfo moduleinfo)
        throws IOException, SyntaxException
    {
        return parse(getLexer(inport, sourcemessages), 8, moduleinfo);
    }

    public final Compilation parse(Lexer lexer, int i, ModuleInfo moduleinfo)
        throws IOException, SyntaxException
    {
        SourceMessages sourcemessages = lexer.getMessages();
        NameLookup namelookup;
        boolean flag;
        Compilation compilation;
        if ((i & 2) != 0)
        {
            namelookup = NameLookup.getInstance(getEnvironment(), this);
        } else
        {
            namelookup = new NameLookup(this);
        }
        if ((i & 1) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        compilation = getCompilation(lexer, sourcemessages, namelookup);
        if (requirePedantic)
        {
            compilation.pedantic = true;
        }
        if (!flag)
        {
            compilation.mustCompile = true;
        }
        compilation.immediate = flag;
        compilation.langOptions = i;
        if ((i & 0x40) != 0)
        {
            compilation.explicit = true;
        }
        if ((i & 8) != 0)
        {
            compilation.setState(1);
        }
        compilation.pushNewModule(lexer);
        if (moduleinfo != null)
        {
            moduleinfo.setCompilation(compilation);
        }
        if (!parse(compilation, i))
        {
            compilation = null;
        } else
        if (compilation.getState() == 1)
        {
            compilation.setState(2);
            return compilation;
        }
        return compilation;
    }

    public abstract boolean parse(Compilation compilation, int i)
        throws IOException, SyntaxException;

    public void resolve(Compilation compilation)
    {
    }

    public void runAsApplication(String as[])
    {
        setDefaults(this);
        repl.main(as);
    }

    static 
    {
        Environment.setGlobal(BuiltinEnvironment.getInstance());
    }
}
