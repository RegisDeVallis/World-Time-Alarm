// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Scope;
import gnu.bytecode.SwitchState;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.functions.Convert;
import gnu.mapping.Environment;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.text.Lexer;
import gnu.text.Options;
import gnu.text.Path;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Stack;
import java.util.Vector;
import java.util.jar.JarOutputStream;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import kawa.Shell;

// Referenced classes of package gnu.expr:
//            ModuleExp, LambdaExp, Initializer, ClassExp, 
//            Expression, ApplyExp, QuoteExp, LitTable, 
//            Literal, CheckedTarget, Language, PairClassType, 
//            ModuleInfo, IgnoreTarget, ConsumerTarget, ConditionalTarget, 
//            StackTarget, Target, ScopeExp, Declaration, 
//            ModuleManager, TypeValue, ModuleMethod, LetExp, 
//            NameLookup, ReferenceExp, IfExp, BeginExp, 
//            FindCapturedVars, ErrorExp, InlineCalls, PushApply, 
//            ChainLambdas, FindTailCalls

public class Compilation
    implements SourceLocator
{

    public static final int BODY_PARSED = 4;
    public static final int CALL_WITH_CONSUMER = 2;
    public static final int CALL_WITH_CONTINUATIONS = 4;
    public static final int CALL_WITH_RETURN = 1;
    public static final int CALL_WITH_TAILCALLS = 3;
    public static final int CALL_WITH_UNSPECIFIED = 0;
    public static final int CLASS_WRITTEN = 14;
    public static final int COMPILED = 12;
    public static final int COMPILE_SETUP = 10;
    public static final int ERROR_SEEN = 100;
    public static final int MODULE_NONSTATIC = -1;
    public static final int MODULE_STATIC = 1;
    public static final int MODULE_STATIC_DEFAULT = 0;
    public static final int MODULE_STATIC_RUN = 2;
    public static final int PROLOG_PARSED = 2;
    public static final int PROLOG_PARSING = 1;
    public static final int RESOLVED = 6;
    public static final int WALKED = 8;
    public static Type apply0args[];
    public static Method apply0method;
    public static Type apply1args[];
    public static Method apply1method;
    public static Type apply2args[];
    public static Method apply2method;
    public static Method apply3method;
    public static Method apply4method;
    private static Type applyCpsArgs[];
    public static Method applyCpsMethod;
    public static Type applyNargs[];
    public static Method applyNmethod;
    public static Method applymethods[];
    public static Field argsCallContextField;
    private static Compilation chainUninitialized;
    static Method checkArgCountMethod;
    public static String classPrefixDefault = "";
    private static final ThreadLocal current = new InheritableThreadLocal();
    public static boolean debugPrintExpr = false;
    public static boolean debugPrintFinalExpr;
    public static int defaultCallConvention;
    public static int defaultClassFileVersion = 0x310000;
    public static boolean emitSourceDebugExtAttr = true;
    public static final Field falseConstant;
    public static boolean generateMainDefault = false;
    public static Method getCallContextInstanceMethod;
    public static Method getCurrentEnvironmentMethod;
    public static final Method getLocation1EnvironmentMethod;
    public static final Method getLocation2EnvironmentMethod;
    public static final Method getLocationMethod;
    public static final Method getProcedureBindingMethod;
    public static final Method getSymbolProcedureMethod;
    public static final Method getSymbolValueMethod;
    public static boolean inlineOk = true;
    public static final Type int1Args[];
    public static ClassType javaStringType;
    static Method makeListMethod;
    public static int moduleStatic = 0;
    public static Field noArgsField;
    public static final ArrayType objArrayType;
    public static Options options;
    public static Field pcCallContextField;
    public static Field procCallContextField;
    public static ClassType scmBooleanType;
    public static ClassType scmKeywordType = ClassType.make("gnu.expr.Keyword");
    public static ClassType scmListType;
    public static ClassType scmSequenceType = ClassType.make("gnu.lists.Sequence");
    static final Method setNameMethod;
    public static final Type string1Arg[];
    public static final Type sym1Arg[];
    public static final Field trueConstant;
    public static ClassType typeApplet = ClassType.make("java.applet.Applet");
    public static ClassType typeCallContext;
    public static ClassType typeClass;
    public static ClassType typeClassType = ClassType.make("gnu.bytecode.ClassType");
    public static final ClassType typeConsumer = ClassType.make("gnu.lists.Consumer");
    public static ClassType typeEnvironment;
    public static ClassType typeLanguage;
    public static ClassType typeLocation;
    public static ClassType typeMethodProc = ClassType.make("gnu.mapping.MethodProc");
    public static ClassType typeModuleBody = ClassType.make("gnu.expr.ModuleBody");
    public static ClassType typeModuleMethod = ClassType.make("gnu.expr.ModuleMethod");
    public static ClassType typeModuleWithContext = ClassType.make("gnu.expr.ModuleWithContext");
    public static ClassType typeObject;
    public static ClassType typeObjectType = ClassType.make("gnu.bytecode.ObjectType");
    public static ClassType typePair = ClassType.make("gnu.lists.Pair");
    public static ClassType typeProcedure;
    public static ClassType typeProcedure0;
    public static ClassType typeProcedure1;
    public static ClassType typeProcedure2;
    public static ClassType typeProcedure3;
    public static ClassType typeProcedure4;
    public static ClassType typeProcedureArray[];
    public static ClassType typeProcedureN = ClassType.make("gnu.mapping.ProcedureN");
    public static ClassType typeRunnable = ClassType.make("java.lang.Runnable");
    public static ClassType typeServlet = ClassType.make("gnu.kawa.servlet.KawaServlet");
    public static ClassType typeString;
    public static ClassType typeSymbol;
    public static ClassType typeType = ClassType.make("gnu.bytecode.Type");
    public static ClassType typeValues;
    public static gnu.text.Options.OptionInfo warnAsError;
    public static gnu.text.Options.OptionInfo warnInvokeUnknownMethod;
    public static gnu.text.Options.OptionInfo warnUndefinedVariable;
    public static gnu.text.Options.OptionInfo warnUnknownMember;
    Variable callContextVar;
    Variable callContextVarForInit;
    public String classPrefix;
    ClassType classes[];
    Initializer clinitChain;
    Method clinitMethod;
    public ClassType curClass;
    public LambdaExp curLambda;
    public Options currentOptions;
    protected ScopeExp current_scope;
    public boolean explicit;
    public Stack exprStack;
    Method forNameHelper;
    SwitchState fswitch;
    Field fswitchIndex;
    public boolean generateMain;
    public boolean immediate;
    private int keyUninitialized;
    int langOptions;
    protected Language language;
    public Lexer lexer;
    public NameLookup lexical;
    LitTable litTable;
    ArrayClassLoader loader;
    int localFieldIndex;
    public ClassType mainClass;
    public ModuleExp mainLambda;
    int maxSelectorValue;
    protected SourceMessages messages;
    public Method method;
    int method_counter;
    public ModuleInfo minfo;
    public ClassType moduleClass;
    Field moduleInstanceMainField;
    Variable moduleInstanceVar;
    public boolean mustCompile;
    private Compilation nextUninitialized;
    int numClasses;
    boolean pedantic;
    public Stack pendingImports;
    private int state;
    public Variable thisDecl;

    public Compilation(Language language1, SourceMessages sourcemessages, NameLookup namelookup)
    {
        mustCompile = ModuleExp.alwaysCompile;
        currentOptions = new Options(options);
        generateMain = generateMainDefault;
        classPrefix = classPrefixDefault;
        language = language1;
        messages = sourcemessages;
        lexical = namelookup;
    }

    private void checkLoop()
    {
        if (((LambdaExp)current_scope).getName() != "%do%loop")
        {
            throw new Error("internal error - bad loop state");
        } else
        {
            return;
        }
    }

    public static char demangle2(char c, char c1)
    {
        char c2 = '%';
        switch (c1 | c << 16)
        {
        default:
            c2 = '\uFFFF';
            // fall through

        case 5046371: 
        case 5242979: 
            return c2;

        case 4259949: 
            return '&';

        case 4259956: 
            return '@';

        case 4391020: 
            return ':';

        case 4391021: 
            return ',';

        case 4456561: 
            return '"';

        case 4456564: 
            return '.';

        case 4522097: 
            return '=';

        case 4522104: 
            return '!';

        case 4653170: 
            return '>';

        case 4980802: 
            return '[';

        case 4980803: 
            return '{';

        case 4980816: 
            return '(';

        case 4980851: 
            return '<';

        case 5046382: 
            return '-';

        case 5111917: 
            return '#';

        case 5242988: 
            return '+';

        case 5308533: 
            return '?';

        case 5374018: 
            return ']';

        case 5374019: 
            return '}';

        case 5374032: 
            return ')';

        case 5439555: 
            return ';';

        case 5439596: 
            return '/';

        case 5439601: 
            return '\\';

        case 5439604: 
            return '*';

        case 5505132: 
            return '~';

        case 5570672: 
            return '^';

        case 5636162: 
            return '|';
        }
    }

    public static String demangleName(String s)
    {
        return demangleName(s, false);
    }

    public static String demangleName(String s, boolean flag)
    {
        StringBuffer stringbuffer;
        int i;
        boolean flag1;
        boolean flag2;
        boolean flag3;
        int j;
        stringbuffer = new StringBuffer();
        i = s.length();
        flag1 = false;
        flag2 = false;
        flag3 = false;
        j = 0;
_L2:
        char c;
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_347;
        }
        c = s.charAt(j);
        if (flag3 && !flag)
        {
            c = Character.toLowerCase(c);
            flag3 = false;
        }
        if (flag || c != 'i' || j != 0 || i <= 2 || s.charAt(j + 1) != 's')
        {
            break; /* Loop/switch isn't completed */
        }
        char c4 = s.charAt(j + 2);
        if (Character.isLowerCase(c4))
        {
            break; /* Loop/switch isn't completed */
        }
        flag1 = true;
        flag2 = true;
        j++;
        if (Character.isUpperCase(c4) || Character.isTitleCase(c4))
        {
            stringbuffer.append(Character.toLowerCase(c4));
            j++;
        }
_L3:
        j++;
        if (true) goto _L2; else goto _L1
_L1:
label0:
        {
            if (c != '$' || j + 2 >= i)
            {
                break label0;
            }
            char c1 = s.charAt(j + 1);
            char c2 = s.charAt(j + 2);
            char c3 = demangle2(c1, c2);
            if (c3 != '\uFFFF')
            {
                stringbuffer.append(c3);
                j += 2;
                flag1 = true;
                flag3 = true;
            } else
            {
                if (c1 != 'T' || c2 != 'o' || j + 3 >= i || s.charAt(j + 3) != '$')
                {
                    break MISSING_BLOCK_LABEL_337;
                }
                stringbuffer.append("->");
                j += 3;
                flag1 = true;
                flag3 = true;
            }
        }
          goto _L3
        if (!flag && j > 1 && (Character.isUpperCase(c) || Character.isTitleCase(c)) && Character.isLowerCase(s.charAt(j - 1)))
        {
            stringbuffer.append('-');
            flag1 = true;
            c = Character.toLowerCase(c);
        }
        stringbuffer.append(c);
          goto _L3
        if (flag2)
        {
            stringbuffer.append('?');
        }
        if (flag1)
        {
            s = stringbuffer.toString();
        }
        return s;
    }

    private void dumpInitializers(Initializer initializer)
    {
        for (Initializer initializer1 = Initializer.reverse(initializer); initializer1 != null; initializer1 = initializer1.next)
        {
            initializer1.emit(this);
        }

    }

    public static Compilation findForImmediateLiterals(int i)
    {
        gnu/expr/Compilation;
        JVM INSTR monitorenter ;
        Compilation compilation = null;
        Compilation compilation1 = chainUninitialized;
_L2:
        Compilation compilation2;
        compilation2 = compilation1.nextUninitialized;
        if (compilation1.keyUninitialized != i)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        if (compilation != null)
        {
            break MISSING_BLOCK_LABEL_42;
        }
        chainUninitialized = compilation2;
_L1:
        compilation1.nextUninitialized = null;
        gnu/expr/Compilation;
        JVM INSTR monitorexit ;
        return compilation1;
        compilation.nextUninitialized = compilation2;
          goto _L1
        Exception exception;
        exception;
        throw exception;
        compilation = compilation1;
        compilation1 = compilation2;
          goto _L2
    }

    public static final Method getConstructor(ClassType classtype, LambdaExp lambdaexp)
    {
        Method method1 = classtype.getDeclaredMethod("<init>", 0);
        if (method1 != null)
        {
            return method1;
        }
        Type atype[];
        if ((lambdaexp instanceof ClassExp) && lambdaexp.staticLinkField != null)
        {
            atype = new Type[1];
            atype[0] = lambdaexp.staticLinkField.getType();
        } else
        {
            atype = apply0args;
        }
        return classtype.addMethod("<init>", 1, atype, Type.voidType);
    }

    public static Compilation getCurrent()
    {
        return (Compilation)current.get();
    }

    public static boolean isValidJavaName(String s)
    {
        int i = s.length();
        if (i == 0 || !Character.isJavaIdentifierStart(s.charAt(0)))
        {
            return false;
        }
        for (int j = i; --j > 0;)
        {
            if (!Character.isJavaIdentifierPart(s.charAt(j)))
            {
                return false;
            }
        }

        return true;
    }

    public static ApplyExp makeCoercion(Expression expression, Expression expression1)
    {
        Expression aexpression[] = {
            expression1, expression
        };
        return new ApplyExp(new QuoteExp(Convert.getInstance()), aexpression);
    }

    public static Expression makeCoercion(Expression expression, Type type)
    {
        return makeCoercion(expression, ((Expression) (new QuoteExp(type))));
    }

    public static String mangleName(String s)
    {
        return mangleName(s, -1);
    }

    public static String mangleName(String s, int i)
    {
        boolean flag;
        int j;
        if (i >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        j = s.length();
        if (j != 6 || !s.equals("*init*")) goto _L2; else goto _L1
_L1:
        s = "<init>";
_L37:
        return s;
_L2:
        StringBuffer stringbuffer;
        boolean flag1;
        int k;
        stringbuffer = new StringBuffer(j);
        flag1 = false;
        k = 0;
_L4:
        char c;
        if (k >= j)
        {
            break MISSING_BLOCK_LABEL_978;
        }
        c = s.charAt(k);
        if (flag1)
        {
            c = Character.toTitleCase(c);
            flag1 = false;
        }
        if (!Character.isDigit(c))
        {
            break; /* Loop/switch isn't completed */
        }
        if (k == 0)
        {
            stringbuffer.append("$N");
        }
        stringbuffer.append(c);
_L5:
        k++;
        if (true) goto _L4; else goto _L3
_L3:
        if (Character.isLetter(c) || c == '_')
        {
            stringbuffer.append(c);
        } else
        {
label0:
            {
                if (c != '$')
                {
                    break label0;
                }
                String s2;
                if (i > 1)
                {
                    s2 = "$$";
                } else
                {
                    s2 = "$";
                }
                stringbuffer.append(s2);
            }
        }
          goto _L5
        c;
        JVM INSTR lookupswitch 28: default 416
    //                   33: 918
    //                   34: 804
    //                   35: 828
    //                   37: 684
    //                   38: 816
    //                   39: 792
    //                   40: 720
    //                   41: 732
    //                   42: 600
    //                   43: 506
    //                   44: 708
    //                   45: 518
    //                   46: 696
    //                   47: 612
    //                   58: 930
    //                   59: 942
    //                   60: 636
    //                   61: 624
    //                   62: 648
    //                   63: 840
    //                   64: 660
    //                   91: 744
    //                   93: 756
    //                   94: 954
    //                   123: 768
    //                   124: 966
    //                   125: 780
    //                   126: 672;
           goto _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L24 _L25 _L26 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34
_L32:
        break MISSING_BLOCK_LABEL_966;
_L6:
        stringbuffer.append('$');
        stringbuffer.append(Character.forDigit(0xf & c >> 12, 16));
        stringbuffer.append(Character.forDigit(0xf & c >> 8, 16));
        stringbuffer.append(Character.forDigit(0xf & c >> 4, 16));
        stringbuffer.append(Character.forDigit(c & 0xf, 16));
_L35:
        if (!flag)
        {
            flag1 = true;
        }
          goto _L5
_L16:
        stringbuffer.append("$Pl");
          goto _L35
_L18:
        if (flag)
        {
            stringbuffer.append("$Mn");
        } else
        {
            char c2;
            if (k + 1 < j)
            {
                c2 = s.charAt(k + 1);
            } else
            {
                c2 = '\0';
            }
            if (c2 == '>')
            {
                stringbuffer.append("$To$");
                k++;
            } else
            if (!Character.isLowerCase(c2))
            {
                stringbuffer.append("$Mn");
            }
        }
          goto _L35
_L15:
        stringbuffer.append("$St");
          goto _L35
_L20:
        stringbuffer.append("$Sl");
          goto _L35
_L24:
        stringbuffer.append("$Eq");
          goto _L35
_L23:
        stringbuffer.append("$Ls");
          goto _L35
_L25:
        stringbuffer.append("$Gr");
          goto _L35
_L27:
        stringbuffer.append("$At");
          goto _L35
_L34:
        stringbuffer.append("$Tl");
          goto _L35
_L10:
        stringbuffer.append("$Pc");
          goto _L35
_L19:
        stringbuffer.append("$Dt");
          goto _L35
_L17:
        stringbuffer.append("$Cm");
          goto _L35
_L13:
        stringbuffer.append("$LP");
          goto _L35
_L14:
        stringbuffer.append("$RP");
          goto _L35
_L28:
        stringbuffer.append("$LB");
          goto _L35
_L29:
        stringbuffer.append("$RB");
          goto _L35
_L31:
        stringbuffer.append("$LC");
          goto _L35
_L33:
        stringbuffer.append("$RC");
          goto _L35
_L12:
        stringbuffer.append("$Sq");
          goto _L35
_L8:
        stringbuffer.append("$Dq");
          goto _L35
_L11:
        stringbuffer.append("$Am");
          goto _L35
_L9:
        stringbuffer.append("$Nm");
          goto _L35
_L26:
        char c1;
        if (stringbuffer.length() > 0)
        {
            c1 = stringbuffer.charAt(0);
        } else
        {
            c1 = '\0';
        }
        if (!flag && k + 1 == j && Character.isLowerCase(c1))
        {
            stringbuffer.setCharAt(0, Character.toTitleCase(c1));
            stringbuffer.insert(0, "is");
        } else
        {
            stringbuffer.append("$Qu");
        }
          goto _L35
_L7:
        stringbuffer.append("$Ex");
          goto _L35
_L21:
        stringbuffer.append("$Cl");
          goto _L35
_L22:
        stringbuffer.append("$SC");
          goto _L35
_L30:
        stringbuffer.append("$Up");
          goto _L35
        stringbuffer.append("$VB");
          goto _L35
        String s1 = stringbuffer.toString();
        if (!s1.equals(s))
        {
            return s1;
        }
        if (true) goto _L37; else goto _L36
_L36:
    }

    public static String mangleName(String s, boolean flag)
    {
        int i;
        if (flag)
        {
            i = 1;
        } else
        {
            i = -1;
        }
        return mangleName(s, i);
    }

    public static String mangleNameIfNeeded(String s)
    {
        if (s == null || isValidJavaName(s))
        {
            return s;
        } else
        {
            return mangleName(s, 0);
        }
    }

    public static String mangleURI(String s)
    {
        boolean flag;
        int i;
        if (s.indexOf('/') >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        i = s.length();
        if (i > 6 && s.startsWith("class:"))
        {
            return s.substring(6);
        }
        int j;
        StringBuffer stringbuffer;
        if (i > 5 && s.charAt(4) == ':' && s.substring(0, 4).equalsIgnoreCase("http"))
        {
            s = s.substring(5);
            i -= 5;
            flag = true;
        } else
        if (i > 4 && s.charAt(3) == ':' && s.substring(0, 3).equalsIgnoreCase("uri"))
        {
            s = s.substring(4);
            i -= 4;
        }
        j = 0;
        stringbuffer = new StringBuffer();
        do
        {
            int k = s.indexOf('/', j);
            int l;
            boolean flag1;
            if (k < 0)
            {
                l = i;
            } else
            {
                l = k;
            }
            if (stringbuffer.length() == 0)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1 && flag)
            {
                String s1 = s.substring(j, l);
                if (l - j > 4 && s1.startsWith("www."))
                {
                    s1 = s1.substring(4);
                }
                putURLWords(s1, stringbuffer);
            } else
            if (j != l)
            {
                if (!flag1)
                {
                    stringbuffer.append('.');
                }
                if (l == i)
                {
                    int i1 = s.lastIndexOf('.', i);
                    if (i1 > j + 1 && !flag1)
                    {
                        int j1 = i - i1;
                        if (j1 <= 4 || j1 == 5 && s.endsWith("html"))
                        {
                            i -= j1;
                            l = i;
                            s = s.substring(0, i);
                        }
                    }
                }
                stringbuffer.append(s.substring(j, l));
            }
            if (k < 0)
            {
                return stringbuffer.toString();
            }
            j = k + 1;
        } while (true);
    }

    private static void putURLWords(String s, StringBuffer stringbuffer)
    {
        int i = s.indexOf('.');
        if (i > 0)
        {
            putURLWords(s.substring(i + 1), stringbuffer);
            stringbuffer.append('.');
            s = s.substring(0, i);
        }
        stringbuffer.append(s);
    }

    private void registerClass(ClassType classtype)
    {
        int i;
        ClassType aclasstype1[];
        int j;
        if (classes == null)
        {
            classes = new ClassType[20];
        } else
        if (numClasses >= classes.length)
        {
            ClassType aclasstype[] = new ClassType[2 * classes.length];
            System.arraycopy(classes, 0, aclasstype, 0, numClasses);
            classes = aclasstype;
        }
        if (classtype.isInterface())
        {
            i = 1;
        } else
        {
            i = 33;
        }
        classtype.addModifiers(i);
        if (classtype == mainClass && numClasses > 0)
        {
            classtype = classes[0];
            classes[0] = mainClass;
        }
        aclasstype1 = classes;
        j = numClasses;
        numClasses = j + 1;
        aclasstype1[j] = classtype;
    }

    public static int registerForImmediateLiterals(Compilation compilation)
    {
        gnu/expr/Compilation;
        JVM INSTR monitorenter ;
        int i = 0;
        Compilation compilation1 = chainUninitialized;
_L1:
        if (compilation1 == null)
        {
            break MISSING_BLOCK_LABEL_36;
        }
        if (i <= compilation1.keyUninitialized)
        {
            i = 1 + compilation1.keyUninitialized;
        }
        compilation1 = compilation1.nextUninitialized;
          goto _L1
        compilation.keyUninitialized = i;
        compilation.nextUninitialized = chainUninitialized;
        chainUninitialized = compilation;
        gnu/expr/Compilation;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public static void restoreCurrent(Compilation compilation)
    {
        current.set(compilation);
    }

    public static void setCurrent(Compilation compilation)
    {
        current.set(compilation);
    }

    public static Compilation setSaveCurrent(Compilation compilation)
    {
        Compilation compilation1 = (Compilation)current.get();
        current.set(compilation);
        return compilation1;
    }

    public static void setupLiterals(int i)
    {
        Compilation compilation = findForImmediateLiterals(i);
        Class class1;
        Literal literal;
        try
        {
            class1 = compilation.loader.loadClass(compilation.mainClass.getName());
            literal = compilation.litTable.literalsChain;
        }
        catch (Throwable throwable)
        {
            throw new WrappedException("internal error", throwable);
        }
        if (literal == null)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        class1.getDeclaredField(literal.field.getName()).set(null, literal.value);
        literal = literal.next;
        break MISSING_BLOCK_LABEL_29;
        compilation.litTable = null;
        return;
    }

    private Method startClassInit()
    {
        method = curClass.addMethod("<clinit>", apply0args, Type.voidType, 9);
        CodeAttr codeattr = method.startCode();
        if (generateMain || generatingApplet() || generatingServlet())
        {
            Method method1 = ((ClassType)Type.make(getLanguage().getClass())).getDeclaredMethod("registerEnvironment", 0);
            if (method1 != null)
            {
                codeattr.emitInvokeStatic(method1);
            }
        }
        return method;
    }

    private void varArgsToArray(LambdaExp lambdaexp, int i, Variable variable, Type type, Variable variable1)
    {
        CodeAttr codeattr = getCode();
        Type type1 = ((ArrayType)type).getComponentType();
        boolean flag;
        if (!"java.lang.Object".equals(type1.getName()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (variable1 != null && !flag)
        {
            codeattr.emitLoad(variable1);
            codeattr.emitPushInt(i);
            codeattr.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getRestArgsArray", 1));
            return;
        }
        if (i == 0 && !flag)
        {
            codeattr.emitLoad(codeattr.getArg(2));
            return;
        }
        codeattr.pushScope();
        if (variable == null)
        {
            variable = codeattr.addLocal(Type.intType);
            Label label;
            Label label1;
            if (variable1 != null)
            {
                codeattr.emitLoad(variable1);
                codeattr.emitInvoke(typeCallContext.getDeclaredMethod("getArgCount", 0));
            } else
            {
                codeattr.emitLoad(codeattr.getArg(2));
                codeattr.emitArrayLength();
            }
            if (i != 0)
            {
                codeattr.emitPushInt(i);
                codeattr.emitSub(Type.intType);
            }
            codeattr.emitStore(variable);
        }
        codeattr.emitLoad(variable);
        codeattr.emitNewArray(type1.getImplementationType());
        label = new Label(codeattr);
        label1 = new Label(codeattr);
        label1.setTypes(codeattr);
        codeattr.emitGoto(label);
        label1.define(codeattr);
        codeattr.emitDup(1);
        codeattr.emitLoad(variable);
        if (variable1 != null)
        {
            codeattr.emitLoad(variable1);
        } else
        {
            codeattr.emitLoad(codeattr.getArg(2));
        }
        codeattr.emitLoad(variable);
        if (i != 0)
        {
            codeattr.emitPushInt(i);
            codeattr.emitAdd(Type.intType);
        }
        if (variable1 != null)
        {
            codeattr.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getArgAsObject", 1));
        } else
        {
            codeattr.emitArrayLoad(Type.objectType);
        }
        if (flag)
        {
            CheckedTarget.emitCheckedCoerce(this, lambdaexp, lambdaexp.getName(), 0, type1, null);
        }
        codeattr.emitArrayStore(type1);
        label.define(codeattr);
        codeattr.emitInc(variable, (short)-1);
        codeattr.emitLoad(variable);
        codeattr.emitGotoIfIntGeZero(label1);
        codeattr.popScope();
    }

    public void addClass(ClassType classtype)
    {
        if (mainLambda.filename != null)
        {
            if (emitSourceDebugExtAttr)
            {
                classtype.setStratum(getLanguage().getName());
            }
            classtype.setSourceFile(mainLambda.filename);
        }
        registerClass(classtype);
        classtype.setClassfileVersion(defaultClassFileVersion);
    }

    public void addMainClass(ModuleExp moduleexp)
    {
        mainClass = moduleexp.classFor(this);
        ClassType classtype = mainClass;
        ClassType aclasstype[] = moduleexp.getInterfaces();
        if (aclasstype != null)
        {
            classtype.setInterfaces(aclasstype);
        }
        ClassType classtype1 = moduleexp.getSuperType();
        if (classtype1 == null)
        {
            if (generatingApplet())
            {
                classtype1 = typeApplet;
            } else
            if (generatingServlet())
            {
                classtype1 = typeServlet;
            } else
            {
                classtype1 = getModuleType();
            }
        }
        if (makeRunnable())
        {
            classtype.addInterface(typeRunnable);
        }
        classtype.setSuper(classtype1);
        moduleexp.type = classtype;
        addClass(classtype);
        getConstructor(mainClass, moduleexp);
    }

    public Field allocLocalField(Type type, String s)
    {
        if (s == null)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("tmp_");
            int i = 1 + localFieldIndex;
            localFieldIndex = i;
            s = stringbuilder.append(i).toString();
        }
        return curClass.addField(s, type, 0);
    }

    void callInitMethods(ClassType classtype, Vector vector)
    {
        String s;
        if (classtype != null)
        {
            if (!"java.lang.Object".equals(s = classtype.getName()))
            {
                for (int i = vector.size(); --i >= 0;)
                {
                    if (((ClassType)vector.elementAt(i)).getName() == s)
                    {
                        return;
                    }
                }

                vector.addElement(classtype);
                ClassType aclasstype[] = classtype.getInterfaces();
                if (aclasstype != null)
                {
                    int k = aclasstype.length;
                    for (int l = 0; l < k; l++)
                    {
                        callInitMethods(aclasstype[l], vector);
                    }

                }
                int j = 1;
                Method method1;
                if (classtype.isInterface())
                {
                    if (classtype instanceof PairClassType)
                    {
                        classtype = ((PairClassType)classtype).instanceType;
                    } else
                    {
                        try
                        {
                            classtype = (ClassType)Type.make(Class.forName((new StringBuilder()).append(classtype.getName()).append("$class").toString()));
                        }
                        catch (Throwable throwable)
                        {
                            return;
                        }
                    }
                } else
                {
                    j = 0;
                }
                method1 = classtype.getDeclaredMethod("$finit$", j);
                if (method1 != null)
                {
                    CodeAttr codeattr = getCode();
                    codeattr.emitPushThis();
                    codeattr.emitInvoke(method1);
                    return;
                }
            }
        }
    }

    public void cleanupAfterCompilation()
    {
        for (int i = 0; i < numClasses; i++)
        {
            classes[i].cleanupAfterCompilation();
        }

        classes = null;
        minfo.comp = null;
        if (minfo.exp != null)
        {
            minfo.exp.body = null;
        }
        mainLambda.body = null;
        mainLambda = null;
        if (!immediate)
        {
            litTable = null;
        }
    }

    public void compileConstant(Object obj)
    {
        CodeAttr codeattr = getCode();
        if (obj == null)
        {
            codeattr.emitPushNull();
            return;
        }
        if ((obj instanceof String) && !immediate)
        {
            codeattr.emitPushString((String)obj);
            return;
        } else
        {
            codeattr.emitGetStatic(compileConstantToField(obj));
            return;
        }
    }

    public void compileConstant(Object obj, Target target)
    {
        if (!(target instanceof IgnoreTarget)) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (!(obj instanceof Values))
        {
            break; /* Loop/switch isn't completed */
        }
        Object aobj[] = ((Values)obj).getValues();
        int j = aobj.length;
        if (!(target instanceof ConsumerTarget))
        {
            break; /* Loop/switch isn't completed */
        }
        int k = 0;
        while (k < j) 
        {
            compileConstant(aobj[k], target);
            k++;
        }
        if (true) goto _L1; else goto _L3
_L3:
        if (target instanceof ConditionalTarget)
        {
            ConditionalTarget conditionaltarget = (ConditionalTarget)target;
            CodeAttr codeattr1 = getCode();
            Label label;
            if (getLanguage().isTrue(obj))
            {
                label = conditionaltarget.ifTrue;
            } else
            {
                label = conditionaltarget.ifFalse;
            }
            codeattr1.emitGoto(label);
            return;
        }
        if (!(target instanceof StackTarget)) goto _L5; else goto _L4
_L4:
        Type type1 = ((StackTarget)target).getType();
        if (!(type1 instanceof PrimType)) goto _L7; else goto _L6
_L6:
        String s;
        CodeAttr codeattr;
        s = type1.getSignature();
        codeattr = getCode();
        if (s == null) goto _L9; else goto _L8
_L8:
        if (s.length() == 1) goto _L10; else goto _L9
_L24:
        if (!(obj instanceof Number)) goto _L12; else goto _L11
_L11:
        Number number = (Number)obj;
        char c;
        c;
        JVM INSTR lookupswitch 6: default 244
    //                   66: 325
    //                   68: 358
    //                   70: 347
    //                   73: 303
    //                   74: 336
    //                   83: 314;
           goto _L12 _L13 _L14 _L15 _L16 _L17 _L18
_L12:
        if (c != 'C') goto _L20; else goto _L19
_L19:
        try
        {
            codeattr.emitPushInt(((PrimType)type1).charValue(obj));
            return;
        }
        catch (ClassCastException classcastexception) { }
_L7:
        if (type1 == typeClass && (obj instanceof ClassType))
        {
            loadClassRef((ClassType)obj);
            return;
        }
          goto _L21
_L10:
        c = s.charAt(0);
        continue; /* Loop/switch isn't completed */
_L16:
        codeattr.emitPushInt(number.intValue());
        return;
_L18:
        codeattr.emitPushInt(number.shortValue());
        return;
_L13:
        codeattr.emitPushInt(number.byteValue());
        return;
_L17:
        codeattr.emitPushLong(number.longValue());
        return;
_L15:
        codeattr.emitPushFloat(number.floatValue());
        return;
_L14:
        codeattr.emitPushDouble(number.doubleValue());
        return;
_L20:
        if (c != 'Z') goto _L7; else goto _L22
_L22:
        int i;
        if (PrimType.booleanValue(obj))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        codeattr.emitPushInt(i);
        return;
_L21:
        Object obj1 = type1.coerceFromObject(obj);
        obj = obj1;
_L5:
        compileConstant(obj);
        Type type;
        Exception exception;
        StringBuffer stringbuffer;
        Values values;
        if (obj == null)
        {
            type = target.getType();
        } else
        {
            type = Type.make(obj.getClass());
        }
        target.compileFromStack(this, type);
        return;
        exception;
        stringbuffer = new StringBuffer();
        values = Values.empty;
        if (obj == values)
        {
            stringbuffer.append("cannot convert void to ");
        } else
        {
            stringbuffer.append("cannot convert literal (of type ");
            if (obj == null)
            {
                stringbuffer.append("<null>");
            } else
            {
                stringbuffer.append(obj.getClass().getName());
            }
            stringbuffer.append(") to ");
        }
        stringbuffer.append(type1.getName());
        error('w', stringbuffer.toString());
          goto _L5
_L9:
        c = ' ';
        if (true) goto _L24; else goto _L23
_L23:
    }

    public Field compileConstantToField(Object obj)
    {
        Literal literal = litTable.findLiteral(obj);
        if (literal.field == null)
        {
            literal.assign(litTable);
        }
        return literal.field;
    }

    public void compileToArchive(ModuleExp moduleexp, String s)
        throws IOException
    {
        boolean flag;
        File file;
        Object obj;
        byte abyte0[][];
        CRC32 crc32;
        if (s.endsWith(".zip"))
        {
            flag = false;
        } else
        if (s.endsWith(".jar"))
        {
            flag = true;
        } else
        {
            s = (new StringBuilder()).append(s).append(".zip").toString();
            flag = false;
        }
        process(12);
        file = new File(s);
        if (file.exists())
        {
            file.delete();
        }
        if (flag)
        {
            obj = new JarOutputStream(new FileOutputStream(file));
        } else
        {
            obj = new ZipOutputStream(new FileOutputStream(file));
        }
        abyte0 = new byte[numClasses][];
        crc32 = new CRC32();
        for (int i = 0; i < numClasses; i++)
        {
            ClassType classtype = classes[i];
            abyte0[i] = classtype.writeToArray();
            ZipEntry zipentry = new ZipEntry((new StringBuilder()).append(classtype.getName().replace('.', '/')).append(".class").toString());
            zipentry.setSize(abyte0[i].length);
            crc32.reset();
            crc32.update(abyte0[i], 0, abyte0[i].length);
            zipentry.setCrc(crc32.getValue());
            ((ZipOutputStream) (obj)).putNextEntry(zipentry);
            ((ZipOutputStream) (obj)).write(abyte0[i]);
        }

        ((ZipOutputStream) (obj)).close();
    }

    public LambdaExp currentLambda()
    {
        return current_scope.currentLambda();
    }

    public ModuleExp currentModule()
    {
        return current_scope.currentModule();
    }

    public ScopeExp currentScope()
    {
        return current_scope;
    }

    public void error(char c, Declaration declaration, String s, String s1)
    {
        error(c, (new StringBuilder()).append(s).append(declaration.getName()).append(s1).toString(), null, declaration);
    }

    public void error(char c, String s)
    {
        if (c == 'w' && warnAsError())
        {
            c = 'e';
        }
        messages.error(c, this, s);
    }

    public void error(char c, String s, SourceLocator sourcelocator)
    {
        String s1 = sourcelocator.getFileName();
        int i = sourcelocator.getLineNumber();
        int j = sourcelocator.getColumnNumber();
        if (s1 == null || i <= 0)
        {
            s1 = getFileName();
            i = getLineNumber();
            j = getColumnNumber();
        }
        if (c == 'w' && warnAsError())
        {
            c = 'e';
        }
        messages.error(c, s1, i, j, s);
    }

    public void error(char c, String s, String s1, Declaration declaration)
    {
        if (c == 'w' && warnAsError())
        {
            c = 'e';
        }
        String s2 = getFileName();
        int i = getLineNumber();
        int j = getColumnNumber();
        int k = declaration.getLineNumber();
        if (k > 0)
        {
            s2 = declaration.getFileName();
            i = k;
            j = declaration.getColumnNumber();
        }
        messages.error(c, s2, i, j, s, s1);
    }

    public ClassType findNamedClass(String s)
    {
        for (int i = 0; i < numClasses; i++)
        {
            if (s.equals(classes[i].getName()))
            {
                return classes[i];
            }
        }

        return null;
    }

    public void freeLocalField(Field field)
    {
    }

    public void generateApplyMethodsWithContext(LambdaExp lambdaexp)
    {
        int i;
        if (lambdaexp.applyMethods == null)
        {
            i = 0;
        } else
        {
            i = lambdaexp.applyMethods.size();
        }
        if (i == 0)
        {
            return;
        }
        ClassType classtype = curClass;
        curClass = lambdaexp.getHeapFrameType();
        if (!curClass.getSuperclass().isSubtype(typeModuleWithContext))
        {
            curClass = moduleClass;
        }
        ClassType _tmp = typeModuleMethod;
        Method method1 = method;
        Type atype[] = new Type[1];
        atype[0] = typeCallContext;
        method = curClass.addMethod("apply", atype, Type.voidType, 1);
        CodeAttr codeattr = method.startCode();
        Variable variable = codeattr.getArg(1);
        codeattr.emitLoad(variable);
        codeattr.emitGetField(pcCallContextField);
        SwitchState switchstate = codeattr.startSwitch();
        for (int j = 0; j < i; j++)
        {
            LambdaExp lambdaexp1 = (LambdaExp)lambdaexp.applyMethods.elementAt(j);
            Method amethod[] = lambdaexp1.primMethods;
            int k = amethod.length;
            for (int l = 0; l < k; l++)
            {
                boolean flag;
                int i1;
                SourceLocator sourcelocator;
                int j1;
                Method method2;
                Type atype1[];
                int k1;
                int l1;
                Variable variable1;
                int i2;
                byte byte0;
                int j2;
                Declaration declaration;
                int k2;
                if (l == k - 1 && (lambdaexp1.max_args < 0 || lambdaexp1.max_args >= k + lambdaexp1.min_args))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                i1 = l;
                switchstate.addCase(l + lambdaexp1.getSelectorValue(this), codeattr);
                sourcelocator = messages.swapSourceLocator(lambdaexp1);
                j1 = lambdaexp1.getLineNumber();
                if (j1 > 0)
                {
                    codeattr.putLineNumber(lambdaexp1.getFileName(), j1);
                }
                method2 = amethod[i1];
                atype1 = method2.getParameterTypes();
                k1 = i1 + lambdaexp1.min_args;
                l1 = 0;
                variable1 = null;
                if (l > 4)
                {
                    variable1 = null;
                    if (k > 1)
                    {
                        variable1 = codeattr.addLocal(Type.intType);
                        codeattr.emitLoad(variable);
                        codeattr.emitInvoke(typeCallContext.getDeclaredMethod("getArgCount", 0));
                        if (lambdaexp1.min_args != 0)
                        {
                            codeattr.emitPushInt(lambdaexp1.min_args);
                            codeattr.emitSub(Type.intType);
                        }
                        codeattr.emitStore(variable1);
                    }
                }
                if (method2.getStaticFlag())
                {
                    i2 = 0;
                } else
                {
                    i2 = 1;
                }
                if (flag)
                {
                    byte0 = 2;
                } else
                {
                    byte0 = 1;
                }
                if (byte0 + k1 < atype1.length)
                {
                    j2 = 1;
                } else
                {
                    j2 = 0;
                }
                if (i2 + j2 > 0)
                {
                    codeattr.emitPushThis();
                    if (curClass == moduleClass && mainClass != moduleClass)
                    {
                        codeattr.emitGetField(moduleInstanceMainField);
                    }
                }
                declaration = lambdaexp1.firstDecl();
                if (declaration != null && declaration.isThisParameter())
                {
                    declaration = declaration.nextDecl();
                }
                k2 = 0;
                while (k2 < k1) 
                {
                    if (variable1 != null)
                    {
                        int l2 = lambdaexp1.min_args;
                        if (k2 >= l2)
                        {
                            codeattr.emitLoad(variable1);
                            codeattr.emitIfIntLEqZero();
                            codeattr.emitLoad(variable);
                            codeattr.emitInvoke(amethod[k2 - lambdaexp1.min_args]);
                            codeattr.emitElse();
                            l1++;
                            codeattr.emitInc(variable1, (short)-1);
                        }
                    }
                    codeattr.emitLoad(variable);
                    Type type1;
                    if (k2 <= 4 && !flag && lambdaexp1.max_args <= 4)
                    {
                        codeattr.emitGetField(typeCallContext.getDeclaredField((new StringBuilder()).append("value").append(k2 + 1).toString()));
                    } else
                    {
                        codeattr.emitGetField(typeCallContext.getDeclaredField("values"));
                        codeattr.emitPushInt(k2);
                        codeattr.emitArrayLoad(Type.objectType);
                    }
                    type1 = declaration.getType();
                    if (type1 != Type.objectType)
                    {
                        SourceLocator sourcelocator1 = messages.swapSourceLocator(declaration);
                        CheckedTarget.emitCheckedCoerce(this, lambdaexp1, k2 + 1, type1);
                        messages.swapSourceLocator(sourcelocator1);
                    }
                    declaration = declaration.nextDecl();
                    k2++;
                }
                if (flag)
                {
                    Type type = atype1[j2 + k1];
                    if (type instanceof ArrayType)
                    {
                        varArgsToArray(lambdaexp1, k1, variable1, type, variable);
                    } else
                    if ("gnu.lists.LList".equals(type.getName()))
                    {
                        codeattr.emitLoad(variable);
                        codeattr.emitPushInt(k1);
                        codeattr.emitInvokeVirtual(typeCallContext.getDeclaredMethod("getRestArgsList", 1));
                    } else
                    if (type == typeCallContext)
                    {
                        codeattr.emitLoad(variable);
                    } else
                    {
                        throw new RuntimeException((new StringBuilder()).append("unsupported #!rest type:").append(type).toString());
                    }
                }
                codeattr.emitLoad(variable);
                codeattr.emitInvoke(method2);
                while (--l1 >= 0) 
                {
                    codeattr.emitFi();
                }
                if (defaultCallConvention < 2)
                {
                    Target.pushObject.compileFromStack(this, lambdaexp1.getReturnType());
                }
                messages.swapSourceLocator(sourcelocator);
                codeattr.emitReturn();
            }

        }

        switchstate.addDefault(codeattr);
        codeattr.emitInvokeStatic(typeModuleMethod.getDeclaredMethod("applyError", 0));
        codeattr.emitReturn();
        switchstate.finish(codeattr);
        method = method1;
        curClass = classtype;
    }

    public void generateApplyMethodsWithoutContext(LambdaExp lambdaexp)
    {
        ClassType classtype;
        ClassType classtype1;
        Method method1;
        CodeAttr codeattr;
        int j;
        boolean flag;
        SwitchState switchstate;
        String s;
        Type atype[];
        LambdaExp lambdaexp1;
        Method amethod[];
        int k1;
        boolean flag1;
        int i2;
        int i;
        if (lambdaexp.applyMethods == null)
        {
            i = 0;
        } else
        {
            i = lambdaexp.applyMethods.size();
        }
        if (i == 0)
        {
            return;
        }
        classtype = curClass;
        curClass = lambdaexp.getHeapFrameType();
        classtype1 = typeModuleMethod;
        if (!curClass.getSuperclass().isSubtype(typeModuleBody))
        {
            curClass = moduleClass;
        }
        method1 = method;
        codeattr = null;
        int k;
        int i5;
        int j5;
        if (defaultCallConvention >= 2)
        {
            j = 5;
        } else
        {
            codeattr = null;
            j = 0;
        }
_L7:
        if (j >= 6)
        {
            break MISSING_BLOCK_LABEL_1267;
        }
        flag = false;
        switchstate = null;
        s = null;
        atype = null;
        k = 0;
_L5:
        if (k >= i)
        {
            break MISSING_BLOCK_LABEL_1145;
        }
        lambdaexp1 = (LambdaExp)lambdaexp.applyMethods.elementAt(k);
        amethod = lambdaexp1.primMethods;
        k1 = amethod.length;
        boolean flag2;
        if (lambdaexp1.max_args < 0 || lambdaexp1.max_args >= k1 + lambdaexp1.min_args)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (j < 5)
        {
label0:
            {
                i2 = j - lambdaexp1.min_args;
                if (i2 >= 0 && i2 < k1)
                {
                    i5 = k1 - 1;
                    j5 = i2;
                    flag2 = false;
                    if (j5 != i5)
                    {
                        break label0;
                    }
                    flag2 = false;
                    if (!flag1)
                    {
                        break label0;
                    }
                }
                flag2 = true;
            }
            k1 = 1;
            flag1 = false;
        } else
        {
            int l1 = 5 - lambdaexp1.min_args;
            flag2 = false;
            if (l1 <= 0)
            {
                continue; /* Loop/switch isn't completed */
            }
            int l4 = k1;
            flag2 = false;
            if (l4 > l1)
            {
                continue; /* Loop/switch isn't completed */
            }
            flag2 = false;
            if (!flag1)
            {
                flag2 = true;
            }
            i2 = k1 - 1;
        }
        if (!flag2) goto _L2; else goto _L1
_L1:
        k++;
        continue; /* Loop/switch isn't completed */
_L2:
        SourceLocator sourcelocator;
        Method method2;
        Type atype1[];
        int k2;
        int l2;
        Variable variable;
        int i3;
        int j3;
        int k3;
        int l3;
        if (!flag)
        {
            if (j < 5)
            {
                s = (new StringBuilder()).append("apply").append(j).toString();
                atype = new Type[j + 1];
                for (int k4 = j; k4 > 0; k4--)
                {
                    atype[k4] = typeObject;
                }

            } else
            {
                s = "applyN";
                atype = new Type[2];
                atype[1] = objArrayType;
            }
            atype[0] = classtype1;
            ClassType classtype2 = curClass;
            int j2;
            Declaration declaration;
            Type type1;
            SourceLocator sourcelocator1;
            int i4;
            int j4;
            Object obj;
            if (defaultCallConvention >= 2)
            {
                obj = Type.voidType;
            } else
            {
                obj = Type.objectType;
            }
            method = classtype2.addMethod(s, atype, ((Type) (obj)), 1);
            codeattr = method.startCode();
            codeattr.emitLoad(codeattr.getArg(1));
            codeattr.emitGetField(classtype1.getField("selector"));
            switchstate = codeattr.startSwitch();
            flag = true;
        }
        switchstate.addCase(lambdaexp1.getSelectorValue(this), codeattr);
        sourcelocator = messages.swapSourceLocator(lambdaexp1);
        j2 = lambdaexp1.getLineNumber();
        if (j2 > 0)
        {
            codeattr.putLineNumber(lambdaexp1.getFileName(), j2);
        }
        method2 = amethod[i2];
        atype1 = method2.getParameterTypes();
        k2 = i2 + lambdaexp1.min_args;
        l2 = 0;
        variable = null;
        if (j > 4)
        {
            j4 = k1;
            variable = null;
            if (j4 > 1)
            {
                variable = codeattr.addLocal(Type.intType);
                codeattr.emitLoad(codeattr.getArg(2));
                codeattr.emitArrayLength();
                if (lambdaexp1.min_args != 0)
                {
                    codeattr.emitPushInt(lambdaexp1.min_args);
                    codeattr.emitSub(Type.intType);
                }
                codeattr.emitStore(variable);
            }
        }
        if (method2.getStaticFlag())
        {
            i3 = 0;
        } else
        {
            i3 = 1;
        }
        if (flag1)
        {
            j3 = 1;
        } else
        {
            j3 = 0;
        }
        if (j3 + k2 < atype1.length)
        {
            k3 = 1;
        } else
        {
            k3 = 0;
        }
        if (i3 + k3 > 0)
        {
            codeattr.emitPushThis();
            if (curClass == moduleClass && mainClass != moduleClass)
            {
                codeattr.emitGetField(moduleInstanceMainField);
            }
        }
        declaration = lambdaexp1.firstDecl();
        if (declaration != null && declaration.isThisParameter())
        {
            declaration = declaration.nextDecl();
        }
        l3 = 0;
        while (l3 < k2) 
        {
            if (variable != null)
            {
                i4 = lambdaexp1.min_args;
                if (l3 >= i4)
                {
                    codeattr.emitLoad(variable);
                    codeattr.emitIfIntLEqZero();
                    codeattr.emitInvoke(amethod[l3 - lambdaexp1.min_args]);
                    codeattr.emitElse();
                    l2++;
                    codeattr.emitInc(variable, (short)-1);
                }
            }
            Variable variable1;
            if (j <= 4)
            {
                variable1 = codeattr.getArg(l3 + 2);
                codeattr.emitLoad(variable1);
            } else
            {
                codeattr.emitLoad(codeattr.getArg(2));
                codeattr.emitPushInt(l3);
                codeattr.emitArrayLoad(Type.objectType);
                variable1 = null;
            }
            type1 = declaration.getType();
            if (type1 != Type.objectType)
            {
                sourcelocator1 = messages.swapSourceLocator(declaration);
                CheckedTarget.emitCheckedCoerce(this, lambdaexp1, l3 + 1, type1, variable1);
                messages.swapSourceLocator(sourcelocator1);
            }
            declaration = declaration.nextDecl();
            l3++;
        }
        if (flag1)
        {
            Type type = atype1[k3 + k2];
            if (type instanceof ArrayType)
            {
                varArgsToArray(lambdaexp1, k2, variable, type, null);
            } else
            if ("gnu.lists.LList".equals(type.getName()))
            {
                codeattr.emitLoad(codeattr.getArg(2));
                codeattr.emitPushInt(k2);
                codeattr.emitInvokeStatic(makeListMethod);
            } else
            if (type == typeCallContext)
            {
                codeattr.emitLoad(codeattr.getArg(2));
            } else
            {
                throw new RuntimeException((new StringBuilder()).append("unsupported #!rest type:").append(type).toString());
            }
        }
        codeattr.emitInvoke(method2);
        while (--l2 >= 0) 
        {
            codeattr.emitFi();
        }
        if (defaultCallConvention < 2)
        {
            Target.pushObject.compileFromStack(this, lambdaexp1.getReturnType());
        }
        messages.swapSourceLocator(sourcelocator);
        codeattr.emitReturn();
        if (true) goto _L1; else goto _L3
_L3:
        if (flag)
        {
            switchstate.addDefault(codeattr);
            if (defaultCallConvention >= 2)
            {
                codeattr.emitInvokeStatic(typeModuleMethod.getDeclaredMethod("applyError", 0));
            } else
            {
                int l;
                int i1;
                if (j > 4)
                {
                    l = 2;
                } else
                {
                    l = j + 1;
                }
                i1 = l + 1;
                for (int j1 = 0; j1 < i1; j1++)
                {
                    codeattr.emitLoad(codeattr.getArg(j1));
                }

                codeattr.emitInvokeSpecial(typeModuleBody.getDeclaredMethod(s, atype));
            }
            codeattr.emitReturn();
            switchstate.finish(codeattr);
        }
        j++;
        continue; /* Loop/switch isn't completed */
        if (true) goto _L5; else goto _L4
_L4:
        method = method1;
        curClass = classtype;
        return;
        if (true) goto _L7; else goto _L6
_L6:
    }

    void generateBytecode()
    {
        Label label;
        Method method1;
        Label label1;
        ModuleExp moduleexp = getModule();
        if (debugPrintFinalExpr)
        {
            OutPort outport = OutPort.errDefault();
            outport.println((new StringBuilder()).append("[Compiling final ").append(moduleexp.getName()).append(" to ").append(mainClass.getName()).append(":").toString());
            moduleexp.print(outport);
            outport.println(']');
            outport.flush();
        }
        ClassType classtype = getModuleType();
        LambdaExp lambdaexp;
        Type atype[];
        Variable variable;
        boolean flag;
        CodeAttr codeattr;
        Variable variable1;
        Variable variable2;
        int i;
        ClassType classtype1;
        ClassType classtype2;
        if (mainClass.getSuperclass().isSubtype(classtype))
        {
            moduleClass = mainClass;
        } else
        {
            moduleClass = new ClassType(generateClassName("frame"));
            moduleClass.setSuper(classtype);
            addClass(moduleClass);
            generateConstructor(moduleClass, null);
        }
        curClass = moduleexp.type;
        lambdaexp = curLambda;
        curLambda = moduleexp;
        if (moduleexp.isHandlingTailCalls())
        {
            atype = new Type[1];
            atype[0] = typeCallContext;
        } else
        if (moduleexp.min_args != moduleexp.max_args || moduleexp.min_args > 4)
        {
            atype = new Type[1];
            atype[0] = new ArrayType(typeObject);
        } else
        {
            int j1 = moduleexp.min_args;
            atype = new Type[j1];
            int k1 = j1;
            while (--k1 >= 0) 
            {
                atype[k1] = typeObject;
            }
        }
        variable = moduleexp.heapFrame;
        flag = moduleexp.isStatic();
        method = curClass.addMethod("run", atype, Type.voidType, 17);
        method.initCode();
        codeattr = getCode();
        if (method.getStaticFlag())
        {
            variable1 = null;
        } else
        {
            variable1 = moduleexp.declareThis(moduleexp.type);
        }
        thisDecl = variable1;
        moduleexp.closureEnv = moduleexp.thisVariable;
        if (moduleexp.isStatic())
        {
            variable2 = null;
        } else
        {
            variable2 = moduleexp.thisVariable;
        }
        moduleexp.heapFrame = variable2;
        moduleexp.allocChildClasses(this);
        if (moduleexp.isHandlingTailCalls() || usingCPStyle())
        {
            callContextVar = new Variable("$ctx", typeCallContext);
            moduleexp.getVarScope().addVariableAfter(thisDecl, callContextVar);
            callContextVar.setParameter(true);
        }
        i = moduleexp.getLineNumber();
        if (i > 0)
        {
            codeattr.putLineNumber(moduleexp.getFileName(), i);
        }
        moduleexp.allocParameters(this);
        moduleexp.enterFunction(this);
        if (usingCPStyle())
        {
            loadCallContext();
            codeattr.emitGetField(pcCallContextField);
            fswitch = codeattr.startSwitch();
            fswitch.addCase(0, codeattr);
        }
        moduleexp.compileBody(this);
        moduleexp.compileEnd(this);
        classtype1 = curClass;
        classtype2 = mainClass;
        label = null;
        method1 = null;
        label1 = null;
        if (classtype1 == classtype2)
        {
            Method method4 = method;
            Variable variable3 = callContextVar;
            callContextVar = null;
            method1 = startClassInit();
            clinitMethod = method1;
            CodeAttr codeattr6 = getCode();
            label1 = new Label(codeattr6);
            label = new Label(codeattr6);
            codeattr6.fixupChain(label, label1);
            if (flag)
            {
                generateConstructor(moduleexp);
                codeattr6.emitNew(moduleClass);
                codeattr6.emitDup(moduleClass);
                codeattr6.emitInvokeSpecial(moduleClass.constructor);
                moduleInstanceMainField = moduleClass.addField("$instance", moduleClass, 25);
                codeattr6.emitPutStatic(moduleInstanceMainField);
            }
            do
            {
                Initializer initializer = clinitChain;
                if (initializer == null)
                {
                    break;
                }
                clinitChain = null;
                dumpInitializers(initializer);
            } while (true);
            if (moduleexp.staticInitRun())
            {
                codeattr6.emitGetStatic(moduleInstanceMainField);
                codeattr6.emitInvoke(typeModuleBody.getDeclaredMethod("run", 0));
            }
            codeattr6.emitReturn();
            if (moduleClass != mainClass && !flag && !generateMain && !immediate)
            {
                method = curClass.addMethod("run", 1, Type.typeArray0, Type.voidType);
                CodeAttr codeattr7 = method.startCode();
                Variable variable4 = codeattr7.addLocal(typeCallContext);
                Variable variable5 = codeattr7.addLocal(typeConsumer);
                Variable variable6 = codeattr7.addLocal(Type.javalangThrowableType);
                codeattr7.emitInvokeStatic(getCallContextInstanceMethod);
                codeattr7.emitStore(variable4);
                Field field = typeCallContext.getDeclaredField("consumer");
                codeattr7.emitLoad(variable4);
                codeattr7.emitGetField(field);
                codeattr7.emitStore(variable5);
                codeattr7.emitLoad(variable4);
                codeattr7.emitGetStatic(ClassType.make("gnu.lists.VoidConsumer").getDeclaredField("instance"));
                codeattr7.emitPutField(field);
                codeattr7.emitTryStart(false, Type.voidType);
                codeattr7.emitPushThis();
                codeattr7.emitLoad(variable4);
                codeattr7.emitInvokeVirtual(method4);
                codeattr7.emitPushNull();
                codeattr7.emitStore(variable6);
                codeattr7.emitTryEnd();
                codeattr7.emitCatchStart(variable6);
                codeattr7.emitCatchEnd();
                codeattr7.emitTryCatchEnd();
                codeattr7.emitLoad(variable4);
                codeattr7.emitLoad(variable6);
                codeattr7.emitLoad(variable5);
                codeattr7.emitInvokeStatic(typeModuleBody.getDeclaredMethod("runCleanup", 3));
                codeattr7.emitReturn();
            }
            method = method4;
            callContextVar = variable3;
        }
        moduleexp.generateApplyMethods(this);
        curLambda = lambdaexp;
        moduleexp.heapFrame = variable;
        if (usingCPStyle())
        {
            CodeAttr codeattr5 = getCode();
            fswitch.finish(codeattr5);
        }
        if (label1 == null && callContextVar == null) goto _L2; else goto _L1
_L1:
        CodeAttr codeattr1;
        Label label2;
        method = method1;
        codeattr1 = getCode();
        label2 = new Label(codeattr1);
        codeattr1.fixupChain(label1, label2);
        if (callContextVarForInit != null)
        {
            codeattr1.emitInvokeStatic(getCallContextInstanceMethod);
            codeattr1.emitStore(callContextVarForInit);
        }
        if (!immediate) goto _L4; else goto _L3
_L3:
        codeattr1.emitPushInt(registerForImmediateLiterals(this));
        codeattr1.emitInvokeStatic(ClassType.make("gnu.expr.Compilation").getDeclaredMethod("setupLiterals", 1));
_L8:
        codeattr1.fixupChain(label2, label);
_L2:
        ModuleManager modulemanager;
        String s;
        int j;
        String s2;
        CodeAttr codeattr3;
        String s4;
        if (generateMain && curClass == mainClass)
        {
            Type atype1[] = new Type[1];
            atype1[0] = new ArrayType(javaStringType);
            method = curClass.addMethod("main", 9, atype1, Type.voidType);
            CodeAttr codeattr4 = method.startCode();
            if (Shell.defaultFormatName != null)
            {
                codeattr4.emitPushString(Shell.defaultFormatName);
                codeattr4.emitInvokeStatic(ClassType.make("kawa.Shell").getDeclaredMethod("setDefaultFormat", 1));
            }
            codeattr4.emitLoad(codeattr4.getArg(0));
            codeattr4.emitInvokeStatic(ClassType.make("gnu.expr.ApplicationMainSupport").getDeclaredMethod("processArgs", 1));
            Throwable throwable;
            ClassType classtype3;
            ClassType classtype4;
            Method method2;
            CodeAttr codeattr2;
            ClassType classtype5;
            Method method3;
            int k;
            ModuleInfo moduleinfo;
            String s3;
            String s5;
            char c;
            String s6;
            String s7;
            int l;
            String s8;
            int i1;
            if (moduleInstanceMainField != null)
            {
                codeattr4.emitGetStatic(moduleInstanceMainField);
            } else
            {
                codeattr4.emitNew(curClass);
                codeattr4.emitDup(curClass);
                codeattr4.emitInvokeSpecial(curClass.constructor);
            }
            codeattr4.emitInvokeVirtual(typeModuleBody.getDeclaredMethod("runAsMain", 0));
            codeattr4.emitReturn();
        }
        if (minfo == null || minfo.getNamespaceUri() == null)
        {
            break MISSING_BLOCK_LABEL_1984;
        }
        modulemanager = ModuleManager.getInstance();
        s = mainClass.getName();
        j = s.lastIndexOf('.');
        if (j < 0)
        {
            s2 = "";
        } else
        {
            String s1 = s.substring(0, j);
            Throwable throwable1;
            WrappedException wrappedexception;
            try
            {
                modulemanager.loadPackageInfo(s1);
            }
            catch (ClassNotFoundException classnotfoundexception) { }
            catch (Throwable throwable2)
            {
                error('e', (new StringBuilder()).append("error loading map for ").append(s1).append(" - ").append(throwable2).toString());
            }
            s2 = s.substring(0, j + 1);
        }
        classtype3 = new ClassType((new StringBuilder()).append(s2).append("$ModulesMap$").toString());
        classtype4 = ClassType.make("gnu.expr.ModuleSet");
        classtype3.setSuper(classtype4);
        registerClass(classtype3);
        method = classtype3.addMethod("<init>", 1, apply0args, Type.voidType);
        method2 = classtype4.addMethod("<init>", 1, apply0args, Type.voidType);
        codeattr2 = method.startCode();
        codeattr2.emitPushThis();
        codeattr2.emitInvokeSpecial(method2);
        codeattr2.emitReturn();
        classtype5 = ClassType.make("gnu.expr.ModuleManager");
        method = classtype3.addMethod("register", new Type[] {
            classtype5
        }, Type.voidType, 1);
        codeattr3 = method.startCode();
        method3 = classtype5.getDeclaredMethod("register", 3);
        k = modulemanager.numModules;
        if (--k < 0)
        {
            break; /* Loop/switch isn't completed */
        }
        moduleinfo = modulemanager.modules[k];
        s3 = moduleinfo.getClassName();
        if (s3 == null || !s3.startsWith(s2))
        {
            continue; /* Loop/switch isn't completed */
        }
        s4 = moduleinfo.sourcePath;
        s5 = moduleinfo.getNamespaceUri();
        codeattr3.emitLoad(codeattr3.getArg(1));
        compileConstant(s3);
        if (Path.valueOf(s4).isAbsolute())
        {
            break MISSING_BLOCK_LABEL_1765;
        }
        try
        {
            c = File.separatorChar;
            s6 = modulemanager.getCompilationDirectory();
            s7 = Path.toURL((new StringBuilder()).append(s6).append(s2.replace('.', c)).toString()).toString();
            l = s7.length();
        }
        // Misplaced declaration of an exception variable
        catch (Throwable throwable1)
        {
            wrappedexception = new WrappedException((new StringBuilder()).append("exception while fixing up '").append(s4).append('\'').toString(), throwable1);
            throw wrappedexception;
        }
        if (l <= 0)
        {
            break MISSING_BLOCK_LABEL_1749;
        }
        i1 = l - 1;
        if (s7.charAt(i1) != c)
        {
            s7 = (new StringBuilder()).append(s7).append(c).toString();
        }
        s8 = Path.relativize(moduleinfo.getSourceAbsPathname(), s7);
        s4 = s8;
        compileConstant(s4);
        compileConstant(s5);
        codeattr3.emitInvokeVirtual(method3);
        if (true) goto _L6; else goto _L5
_L6:
        break MISSING_BLOCK_LABEL_1568;
_L5:
        break; /* Loop/switch isn't completed */
_L4:
        try
        {
            litTable.emit();
        }
        // Misplaced declaration of an exception variable
        catch (Throwable throwable)
        {
            error('e', (new StringBuilder()).append("Literals: Internal error:").append(throwable).toString());
        }
        if (true) goto _L8; else goto _L7
_L7:
        codeattr3.emitReturn();
    }

    public String generateClassName(String s)
    {
        String s1 = mangleName(s, true);
        if (mainClass == null) goto _L2; else goto _L1
_L1:
        s1 = (new StringBuilder()).append(mainClass.getName()).append('$').append(s1).toString();
_L4:
        if (findNamedClass(s1) == null)
        {
            return s1;
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (classPrefix != null)
        {
            s1 = (new StringBuilder()).append(classPrefix).append(s1).toString();
        }
        if (true) goto _L4; else goto _L3
_L3:
        int i = 0;
        do
        {
            String s2 = (new StringBuilder()).append(s1).append(i).toString();
            if (findNamedClass(s2) == null)
            {
                return s2;
            }
            i++;
        } while (true);
    }

    public final void generateConstructor(ClassType classtype, LambdaExp lambdaexp)
    {
        Method method1 = method;
        Variable variable = callContextVar;
        callContextVar = null;
        ClassType classtype1 = curClass;
        curClass = classtype;
        Method method2 = getConstructor(classtype, lambdaexp);
        classtype.constructor = method2;
        method = method2;
        CodeAttr codeattr = method2.startCode();
        if ((lambdaexp instanceof ClassExp) && lambdaexp.staticLinkField != null)
        {
            codeattr.emitPushThis();
            codeattr.emitLoad(codeattr.getCurrentScope().getVariable(1));
            codeattr.emitPutField(lambdaexp.staticLinkField);
        }
        ClassExp.invokeDefaultSuperConstructor(classtype.getSuperclass(), this, lambdaexp);
        if (curClass == mainClass && minfo != null && minfo.sourcePath != null)
        {
            codeattr.emitPushThis();
            codeattr.emitInvokeStatic(ClassType.make("gnu.expr.ModuleInfo").getDeclaredMethod("register", 1));
        }
        if (lambdaexp != null && lambdaexp.initChain != null)
        {
            LambdaExp lambdaexp1 = curLambda;
            curLambda = new LambdaExp();
            curLambda.closureEnv = codeattr.getArg(0);
            curLambda.outer = lambdaexp1;
            do
            {
                Initializer initializer = lambdaexp.initChain;
                if (initializer == null)
                {
                    break;
                }
                lambdaexp.initChain = null;
                dumpInitializers(initializer);
            } while (true);
            curLambda = lambdaexp1;
        }
        if (lambdaexp instanceof ClassExp)
        {
            callInitMethods(((ClassExp)lambdaexp).getCompiledClassType(this), new Vector(10));
        }
        codeattr.emitReturn();
        method = method1;
        curClass = classtype1;
        callContextVar = variable;
    }

    public final void generateConstructor(LambdaExp lambdaexp)
    {
        generateConstructor(lambdaexp.getHeapFrameType(), lambdaexp);
    }

    public void generateMatchMethods(LambdaExp lambdaexp)
    {
        int i;
        if (lambdaexp.applyMethods == null)
        {
            i = 0;
        } else
        {
            i = lambdaexp.applyMethods.size();
        }
        if (i == 0)
        {
            return;
        }
        Method method1 = method;
        ClassType classtype = curClass;
        ClassType classtype1 = typeModuleMethod;
        curClass = lambdaexp.getHeapFrameType();
        if (!curClass.getSuperclass().isSubtype(typeModuleBody))
        {
            curClass = moduleClass;
        }
        CodeAttr codeattr = null;
        for (int j = 0; j <= 5; j++)
        {
            boolean flag = false;
            SwitchState switchstate = null;
            String s = null;
            Type atype[] = null;
            int k = i;
            do
            {
                if (--k < 0)
                {
                    break;
                }
                LambdaExp lambdaexp1 = (LambdaExp)lambdaexp.applyMethods.elementAt(k);
                int k1 = lambdaexp1.primMethods.length;
                boolean flag1;
                int i2;
                if (lambdaexp1.max_args < 0 || lambdaexp1.max_args >= k1 + lambdaexp1.min_args)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (j < 5)
                {
                    i2 = j - lambdaexp1.min_args;
                    if (i2 < 0 || i2 >= k1 || i2 == k1 - 1 && flag1)
                    {
                        continue;
                    }
                } else
                {
                    int l1 = 5 - lambdaexp1.min_args;
                    if (l1 > 0 && k1 <= l1 && !flag1)
                    {
                        continue;
                    }
                    i2 = k1 - 1;
                }
                int k2;
                Variable variable;
                if (!flag)
                {
                    int j2;
                    Declaration declaration;
                    Label label;
                    Label label1;
                    ConditionalTarget conditionaltarget;
                    if (j < 5)
                    {
                        s = (new StringBuilder()).append("match").append(j).toString();
                        atype = new Type[j + 2];
                        for (int i3 = j; i3 >= 0; i3--)
                        {
                            atype[i3 + 1] = typeObject;
                        }

                        atype[j + 1] = typeCallContext;
                    } else
                    {
                        s = "matchN";
                        atype = new Type[3];
                        atype[1] = objArrayType;
                        atype[2] = typeCallContext;
                    }
                    atype[0] = classtype1;
                    method = curClass.addMethod(s, atype, Type.intType, 1);
                    codeattr = method.startCode();
                    codeattr.emitLoad(codeattr.getArg(1));
                    codeattr.emitGetField(classtype1.getField("selector"));
                    switchstate = codeattr.startSwitch();
                    flag = true;
                }
                switchstate.addCase(lambdaexp1.getSelectorValue(this), codeattr);
                j2 = lambdaexp1.getLineNumber();
                if (j2 > 0)
                {
                    codeattr.putLineNumber(lambdaexp1.getFileName(), j2);
                }
                if (j == 5)
                {
                    k2 = 3;
                } else
                {
                    k2 = j + 2;
                }
                variable = codeattr.getArg(k2);
                if (j < 5)
                {
                    declaration = lambdaexp1.firstDecl();
                    int l2 = 1;
                    do
                    {
                        if (l2 > j)
                        {
                            break;
                        }
                        codeattr.emitLoad(variable);
                        codeattr.emitLoad(codeattr.getArg(l2 + 1));
                        Type type = declaration.getType();
                        if (type != Type.objectType)
                        {
                            if (type instanceof TypeValue)
                            {
                                label = new Label(codeattr);
                                label1 = new Label(codeattr);
                                conditionaltarget = new ConditionalTarget(label, label1, getLanguage());
                                codeattr.emitDup();
                                ((TypeValue)type).emitIsInstance(null, this, conditionaltarget);
                                label1.define(codeattr);
                                codeattr.emitPushInt(0xfff40000 | l2);
                                codeattr.emitReturn();
                                label.define(codeattr);
                            } else
                            if ((type instanceof ClassType) && type != Type.objectType && type != Type.toStringType)
                            {
                                codeattr.emitDup();
                                type.emitIsInstance(codeattr);
                                codeattr.emitIfIntEqZero();
                                codeattr.emitPushInt(0xfff40000 | l2);
                                codeattr.emitReturn();
                                codeattr.emitFi();
                            }
                        }
                        codeattr.emitPutField(typeCallContext.getField((new StringBuilder()).append("value").append(l2).toString()));
                        declaration = declaration.nextDecl();
                        l2++;
                    } while (true);
                } else
                {
                    codeattr.emitLoad(variable);
                    codeattr.emitLoad(codeattr.getArg(2));
                    codeattr.emitPutField(typeCallContext.getField("values"));
                }
                codeattr.emitLoad(variable);
                if (defaultCallConvention < 2)
                {
                    codeattr.emitLoad(codeattr.getArg(1));
                } else
                {
                    codeattr.emitLoad(codeattr.getArg(0));
                }
                codeattr.emitPutField(procCallContextField);
                codeattr.emitLoad(variable);
                if (defaultCallConvention >= 2)
                {
                    codeattr.emitPushInt(i2 + lambdaexp1.getSelectorValue(this));
                } else
                {
                    codeattr.emitPushInt(j);
                }
                codeattr.emitPutField(pcCallContextField);
                codeattr.emitPushInt(0);
                codeattr.emitReturn();
            } while (true);
            if (!flag)
            {
                continue;
            }
            switchstate.addDefault(codeattr);
            int l;
            int i1;
            if (j > 4)
            {
                l = 2;
            } else
            {
                l = j + 1;
            }
            i1 = l + 1;
            for (int j1 = 0; j1 <= i1; j1++)
            {
                codeattr.emitLoad(codeattr.getArg(j1));
            }

            codeattr.emitInvokeSpecial(typeModuleBody.getDeclaredMethod(s, atype.length));
            codeattr.emitReturn();
            switchstate.finish(codeattr);
        }

        method = method1;
        curClass = classtype;
    }

    public boolean generatingApplet()
    {
        return (0x10 & langOptions) != 0;
    }

    public boolean generatingServlet()
    {
        return (0x20 & langOptions) != 0;
    }

    public final boolean getBooleanOption(String s)
    {
        return currentOptions.getBoolean(s);
    }

    public final boolean getBooleanOption(String s, boolean flag)
    {
        return currentOptions.getBoolean(s, flag);
    }

    public final CodeAttr getCode()
    {
        return method.getCode();
    }

    public final int getColumnNumber()
    {
        return messages.getColumnNumber();
    }

    public final Method getConstructor(LambdaExp lambdaexp)
    {
        return getConstructor(lambdaexp.getHeapFrameType(), lambdaexp);
    }

    public final String getFileName()
    {
        return messages.getFileName();
    }

    public Method getForNameHelper()
    {
        if (forNameHelper == null)
        {
            Method method1 = method;
            method = curClass.addMethod("class$", 9, string1Arg, typeClass);
            forNameHelper = method;
            CodeAttr codeattr = method.startCode();
            codeattr.emitLoad(codeattr.getArg(0));
            codeattr.emitPushInt(0);
            codeattr.emitPushString(mainClass.getName());
            codeattr.emitInvokeStatic(typeClass.getDeclaredMethod("forName", 1));
            codeattr.emitInvokeVirtual(typeClass.getDeclaredMethod("getClassLoader", 0));
            codeattr.emitInvokeStatic(typeClass.getDeclaredMethod("forName", 3));
            codeattr.emitReturn();
            method = method1;
        }
        return forNameHelper;
    }

    public Language getLanguage()
    {
        return language;
    }

    public final int getLineNumber()
    {
        return messages.getLineNumber();
    }

    public SourceMessages getMessages()
    {
        return messages;
    }

    public final ModuleExp getModule()
    {
        return mainLambda;
    }

    public final ClassType getModuleType()
    {
        if (defaultCallConvention >= 2)
        {
            return typeModuleWithContext;
        } else
        {
            return typeModuleBody;
        }
    }

    public String getPublicId()
    {
        return messages.getPublicId();
    }

    public int getState()
    {
        return state;
    }

    public String getSystemId()
    {
        return messages.getSystemId();
    }

    public boolean inlineOk(Expression expression)
    {
        if (expression instanceof LambdaExp)
        {
            LambdaExp lambdaexp = (LambdaExp)expression;
            Declaration declaration = lambdaexp.nameDecl;
            if (declaration == null || declaration.getSymbol() == null || !(declaration.context instanceof ModuleExp))
            {
                return true;
            }
            if (immediate && declaration.isPublic() && !lambdaexp.getFlag(2048) && (curLambda == null || lambdaexp.topLevel() != curLambda.topLevel()))
            {
                return false;
            }
        }
        return inlineOk;
    }

    public boolean inlineOk(Procedure procedure)
    {
        if (immediate && (procedure instanceof ModuleMethod) && (((ModuleMethod)procedure).module.getClass().getClassLoader() instanceof ArrayClassLoader))
        {
            return false;
        } else
        {
            return inlineOk;
        }
    }

    public boolean isPedantic()
    {
        return pedantic;
    }

    public boolean isStableSourceLocation()
    {
        return false;
    }

    public boolean isStatic()
    {
        return mainLambda.isStatic();
    }

    public LetExp letDone(Expression expression)
    {
        LetExp letexp = (LetExp)current_scope;
        letexp.body = expression;
        pop(letexp);
        return letexp;
    }

    public void letEnter()
    {
        LetExp letexp = (LetExp)current_scope;
        Expression aexpression[] = new Expression[letexp.countDecls()];
        Declaration declaration = letexp.firstDecl();
        int j;
        for (int i = 0; declaration != null; i = j)
        {
            j = i + 1;
            aexpression[i] = declaration.getValue();
            declaration = declaration.nextDecl();
        }

        letexp.inits = aexpression;
        lexical.push(letexp);
    }

    public void letStart()
    {
        pushScope(new LetExp(null));
    }

    public Declaration letVariable(Object obj, Type type, Expression expression)
    {
        Declaration declaration = ((LetExp)current_scope).addDeclaration(obj, type);
        declaration.noteValue(expression);
        return declaration;
    }

    public final void loadCallContext()
    {
        CodeAttr codeattr = getCode();
        if (callContextVar != null && !callContextVar.dead())
        {
            codeattr.emitLoad(callContextVar);
            return;
        }
        if (method == clinitMethod)
        {
            callContextVar = new Variable("$ctx", typeCallContext);
            callContextVar.reserveLocal(codeattr.getMaxLocals(), codeattr);
            codeattr.emitLoad(callContextVar);
            callContextVarForInit = callContextVar;
            return;
        } else
        {
            codeattr.emitInvokeStatic(getCallContextInstanceMethod);
            codeattr.emitDup();
            callContextVar = new Variable("$ctx", typeCallContext);
            codeattr.getCurrentScope().addVariable(codeattr, callContextVar);
            codeattr.emitStore(callContextVar);
            return;
        }
    }

    public void loadClassRef(ObjectType objecttype)
    {
        CodeAttr codeattr = getCode();
        if (curClass.getClassfileVersion() >= 0x310000)
        {
            codeattr.emitPushClass(objecttype);
            return;
        }
        if (objecttype == mainClass && mainLambda.isStatic() && moduleInstanceMainField != null)
        {
            codeattr.emitGetStatic(moduleInstanceMainField);
            codeattr.emitInvokeVirtual(Type.objectType.getDeclaredMethod("getClass", 0));
            return;
        }
        String s;
        if (objecttype instanceof ClassType)
        {
            s = objecttype.getName();
        } else
        {
            s = objecttype.getInternalName().replace('/', '.');
        }
        codeattr.emitPushString(s);
        codeattr.emitInvokeStatic(getForNameHelper());
    }

    public Declaration lookup(Object obj, int i)
    {
        return lexical.lookup(obj, i);
    }

    public void loopBody(Expression expression)
    {
        ((LambdaExp)current_scope).body = expression;
    }

    public void loopCond(Expression expression)
    {
        checkLoop();
        exprStack.push(expression);
    }

    public void loopEnter()
    {
        checkLoop();
        LambdaExp lambdaexp = (LambdaExp)current_scope;
        int i = lambdaexp.min_args;
        lambdaexp.max_args = i;
        Expression aexpression[] = new Expression[i];
        for (int j = i; --j >= 0;)
        {
            aexpression[j] = (Expression)exprStack.pop();
        }

        LetExp letexp = (LetExp)lambdaexp.outer;
        letexp.setBody(new ApplyExp(new ReferenceExp(letexp.firstDecl()), aexpression));
        lexical.push(lambdaexp);
    }

    public Expression loopRepeat()
    {
        return loopRepeat(Expression.noExpressions);
    }

    public Expression loopRepeat(Expression expression)
    {
        return loopRepeat(new Expression[] {
            expression
        });
    }

    public Expression loopRepeat(Expression aexpression[])
    {
        LambdaExp lambdaexp = (LambdaExp)current_scope;
        ScopeExp scopeexp = lambdaexp.outer;
        Declaration declaration = scopeexp.firstDecl();
        Expression expression = (Expression)exprStack.pop();
        ApplyExp applyexp = new ApplyExp(new ReferenceExp(declaration), aexpression);
        lambdaexp.body = new IfExp(expression, new BeginExp(lambdaexp.body, applyexp), QuoteExp.voidExp);
        lexical.pop(lambdaexp);
        current_scope = scopeexp.outer;
        return scopeexp;
    }

    public void loopStart()
    {
        LambdaExp lambdaexp = new LambdaExp();
        LetExp letexp = new LetExp(new Expression[] {
            lambdaexp
        });
        letexp.addDeclaration("%do%loop").noteValue(lambdaexp);
        lambdaexp.setName("%do%loop");
        letexp.outer = current_scope;
        lambdaexp.outer = letexp;
        current_scope = lambdaexp;
    }

    public Declaration loopVariable(Object obj, Type type, Expression expression)
    {
        checkLoop();
        LambdaExp lambdaexp = (LambdaExp)current_scope;
        Declaration declaration = lambdaexp.addDeclaration(obj, type);
        if (exprStack == null)
        {
            exprStack = new Stack();
        }
        exprStack.push(expression);
        lambdaexp.min_args = 1 + lambdaexp.min_args;
        return declaration;
    }

    public boolean makeRunnable()
    {
        return !generatingServlet() && !generatingApplet() && !getModule().staticInitRun();
    }

    public void mustCompileHere()
    {
        if (!mustCompile && !ModuleExp.compilerAvailable)
        {
            error('w', "this expression claimed that it must be compiled, but compiler is unavailable");
            return;
        } else
        {
            mustCompile = true;
            return;
        }
    }

    public void outputClass(String s)
        throws IOException
    {
        char c = File.separatorChar;
        for (int i = 0; i < numClasses; i++)
        {
            ClassType classtype = classes[i];
            String s1 = (new StringBuilder()).append(s).append(classtype.getName().replace('.', c)).append(".class").toString();
            String s2 = (new File(s1)).getParent();
            if (s2 != null)
            {
                (new File(s2)).mkdirs();
            }
            classtype.writeToFile(s1);
        }

        minfo.cleanupAfterCompilation();
    }

    public Expression parse(Object obj)
    {
        throw new Error("unimeplemented parse");
    }

    public final void pop()
    {
        pop(current_scope);
    }

    public void pop(ScopeExp scopeexp)
    {
        lexical.pop(scopeexp);
        current_scope = scopeexp.outer;
    }

    public void process(int i)
    {
        byte byte0;
        byte byte1;
        byte byte2;
        byte byte3;
        Compilation compilation;
        byte0 = 10;
        byte1 = 8;
        byte2 = 6;
        byte3 = 100;
        compilation = setSaveCurrent(this);
        ModuleExp moduleexp = getModule();
        if (i < 4)
        {
            break MISSING_BLOCK_LABEL_109;
        }
        if (getState() >= 3)
        {
            break MISSING_BLOCK_LABEL_109;
        }
        setState(3);
        language.parse(this, 0);
        lexer.close();
        lexer = null;
        byte byte4;
        Stack stack;
        if (messages.seenErrors())
        {
            byte4 = byte3;
        } else
        {
            byte4 = 4;
        }
        setState(byte4);
        stack = pendingImports;
        if (stack != null)
        {
            restoreCurrent(compilation);
            return;
        }
        if (i < byte2)
        {
            break MISSING_BLOCK_LABEL_158;
        }
        if (getState() >= byte2)
        {
            break MISSING_BLOCK_LABEL_158;
        }
        addMainClass(moduleexp);
        language.resolve(this);
        if (messages.seenErrors())
        {
            byte2 = byte3;
        }
        setState(byte2);
        if (!explicit && !immediate && minfo.checkCurrent(ModuleManager.getInstance(), System.currentTimeMillis()))
        {
            minfo.cleanupAfterCompilation();
            setState(14);
        }
        if (i < byte1)
        {
            break MISSING_BLOCK_LABEL_238;
        }
        if (getState() >= byte1)
        {
            break MISSING_BLOCK_LABEL_238;
        }
        walkModule(moduleexp);
        if (messages.seenErrors())
        {
            byte1 = byte3;
        }
        setState(byte1);
        if (i < byte0)
        {
            break MISSING_BLOCK_LABEL_305;
        }
        if (getState() >= byte0)
        {
            break MISSING_BLOCK_LABEL_305;
        }
        litTable = new LitTable(this);
        moduleexp.setCanRead(true);
        FindCapturedVars.findCapturedVars(moduleexp, this);
        moduleexp.allocFields(this);
        moduleexp.allocChildMethods(this);
        if (messages.seenErrors())
        {
            byte0 = byte3;
        }
        setState(byte0);
        if (i < 12)
        {
            break MISSING_BLOCK_LABEL_361;
        }
        if (getState() >= 12)
        {
            break MISSING_BLOCK_LABEL_361;
        }
        if (immediate)
        {
            loader = new ArrayClassLoader(ObjectType.getContextClassLoader());
        }
        generateBytecode();
        if (!messages.seenErrors())
        {
            byte3 = 12;
        }
        setState(byte3);
        if (i < 14)
        {
            break MISSING_BLOCK_LABEL_392;
        }
        if (getState() < 14)
        {
            outputClass(ModuleManager.getInstance().getCompilationDirectory());
            setState(14);
        }
        restoreCurrent(compilation);
        return;
        SyntaxException syntaxexception;
        syntaxexception;
        setState(100);
        if (syntaxexception.getMessages() != getMessages())
        {
            throw new RuntimeException((new StringBuilder()).append("confussing syntax error: ").append(syntaxexception).toString());
        }
        break MISSING_BLOCK_LABEL_464;
        Exception exception;
        exception;
        restoreCurrent(compilation);
        throw exception;
        restoreCurrent(compilation);
        return;
        IOException ioexception;
        ioexception;
        ioexception.printStackTrace();
        error('f', (new StringBuilder()).append("caught ").append(ioexception).toString());
        setState(100);
        restoreCurrent(compilation);
        return;
    }

    public void push(Declaration declaration)
    {
        lexical.push(declaration);
    }

    public void push(ScopeExp scopeexp)
    {
        pushScope(scopeexp);
        lexical.push(scopeexp);
    }

    void pushChain(ScopeExp scopeexp, ScopeExp scopeexp1)
    {
        if (scopeexp != scopeexp1)
        {
            pushChain(scopeexp.outer, scopeexp1);
            pushScope(scopeexp);
            lexical.push(scopeexp);
        }
    }

    public ModuleExp pushNewModule(Lexer lexer1)
    {
        lexer = lexer1;
        return pushNewModule(lexer1.getName());
    }

    public ModuleExp pushNewModule(String s)
    {
        ModuleExp moduleexp = new ModuleExp();
        if (s != null)
        {
            moduleexp.setFile(s);
        }
        if (generatingApplet() || generatingServlet())
        {
            moduleexp.setFlag(0x20000);
        }
        if (immediate)
        {
            moduleexp.setFlag(0x100000);
            (new ModuleInfo()).setCompilation(this);
        }
        mainLambda = moduleexp;
        push(moduleexp);
        return moduleexp;
    }

    public void pushPendingImport(ModuleInfo moduleinfo, ScopeExp scopeexp, int i)
    {
        if (pendingImports == null)
        {
            pendingImports = new Stack();
        }
        pendingImports.push(moduleinfo);
        pendingImports.push(scopeexp);
        ReferenceExp referenceexp = new ReferenceExp((Object)null);
        referenceexp.setLine(this);
        pendingImports.push(referenceexp);
        pendingImports.push(Integer.valueOf(i));
    }

    public final void pushScope(ScopeExp scopeexp)
    {
        if (!mustCompile && (scopeexp.mustCompile() || ModuleExp.compilerAvailable && (scopeexp instanceof LambdaExp) && !(scopeexp instanceof ModuleExp)))
        {
            mustCompileHere();
        }
        scopeexp.outer = current_scope;
        current_scope = scopeexp;
    }

    public Object resolve(Object obj, boolean flag)
    {
        Environment environment = Environment.getCurrent();
        Symbol symbol;
        if (obj instanceof String)
        {
            symbol = environment.defaultNamespace().lookup((String)obj);
        } else
        {
            symbol = (Symbol)obj;
        }
        if (symbol == null)
        {
            return null;
        }
        if (flag && getLanguage().hasSeparateFunctionNamespace())
        {
            return environment.getFunction(symbol, null);
        } else
        {
            return environment.get(symbol, null);
        }
    }

    public void setColumn(int i)
    {
        messages.setColumn(i);
    }

    public void setCurrentScope(ScopeExp scopeexp)
    {
        int i = ScopeExp.nesting(scopeexp);
        int j;
        for (j = ScopeExp.nesting(current_scope); j > i; j--)
        {
            pop(current_scope);
        }

        ScopeExp scopeexp1 = scopeexp;
        for (; i > j; i--)
        {
            scopeexp1 = scopeexp1.outer;
        }

        for (; scopeexp1 != current_scope; scopeexp1 = scopeexp1.outer)
        {
            pop(current_scope);
        }

        pushChain(scopeexp, scopeexp1);
    }

    public void setFile(String s)
    {
        messages.setFile(s);
    }

    public void setLine(int i)
    {
        messages.setLine(i);
    }

    public final void setLine(Expression expression)
    {
        messages.setLocation(expression);
    }

    public void setLine(Object obj)
    {
        if (obj instanceof SourceLocator)
        {
            messages.setLocation((SourceLocator)obj);
        }
    }

    public void setLine(String s, int i, int j)
    {
        messages.setLine(s, i, j);
    }

    public final void setLocation(SourceLocator sourcelocator)
    {
        messages.setLocation(sourcelocator);
    }

    public void setMessages(SourceMessages sourcemessages)
    {
        messages = sourcemessages;
    }

    public void setModule(ModuleExp moduleexp)
    {
        mainLambda = moduleexp;
    }

    public void setSharedModuleDefs(boolean flag)
    {
        if (flag)
        {
            langOptions = 2 | langOptions;
            return;
        } else
        {
            langOptions = -3 & langOptions;
            return;
        }
    }

    public void setState(int i)
    {
        state = i;
    }

    public boolean sharedModuleDefs()
    {
        return (2 & langOptions) != 0;
    }

    public Expression syntaxError(String s)
    {
        error('e', s);
        return new ErrorExp(s);
    }

    public String toString()
    {
        return (new StringBuilder()).append("<compilation ").append(mainLambda).append(">").toString();
    }

    public void usedClass(Type type)
    {
        for (; type instanceof ArrayType; type = ((ArrayType)type).getComponentType()) { }
        if (immediate && (type instanceof ClassType))
        {
            loader.addClass((ClassType)type);
        }
    }

    public boolean usingCPStyle()
    {
        return defaultCallConvention == 4;
    }

    public boolean usingTailCalls()
    {
        return defaultCallConvention >= 3;
    }

    public void walkModule(ModuleExp moduleexp)
    {
        if (debugPrintExpr)
        {
            OutPort outport = OutPort.errDefault();
            outport.println((new StringBuilder()).append("[Module:").append(moduleexp.getName()).toString());
            moduleexp.print(outport);
            outport.println(']');
            outport.flush();
        }
        InlineCalls.inlineCalls(moduleexp, this);
        PushApply.pushApply(moduleexp);
        ChainLambdas.chainLambdas(moduleexp, this);
        FindTailCalls.findTailCalls(moduleexp, this);
    }

    public boolean warnAsError()
    {
        return currentOptions.getBoolean(warnAsError);
    }

    public boolean warnInvokeUnknownMethod()
    {
        return currentOptions.getBoolean(warnInvokeUnknownMethod);
    }

    public boolean warnUndefinedVariable()
    {
        return currentOptions.getBoolean(warnUndefinedVariable);
    }

    public boolean warnUnknownMember()
    {
        return currentOptions.getBoolean(warnUnknownMember);
    }

    static 
    {
        options = new Options();
        warnUndefinedVariable = options.add("warn-undefined-variable", 1, Boolean.TRUE, "warn if no compiler-visible binding for a variable");
        warnUnknownMember = options.add("warn-unknown-member", 1, Boolean.TRUE, "warn if referencing an unknown method or field");
        warnInvokeUnknownMethod = options.add("warn-invoke-unknown-method", 1, warnUnknownMember, "warn if invoke calls an unknown method (subsumed by warn-unknown-member)");
        warnAsError = options.add("warn-as-error", 1, Boolean.FALSE, "Make all warnings into errors");
        typeObject = Type.objectType;
        scmBooleanType = ClassType.make("java.lang.Boolean");
        typeString = ClassType.make("java.lang.String");
        javaStringType = typeString;
        scmListType = ClassType.make("gnu.lists.LList");
        objArrayType = ArrayType.make(typeObject);
        typeClass = Type.javalangClassType;
        typeProcedure = ClassType.make("gnu.mapping.Procedure");
        typeLanguage = ClassType.make("gnu.expr.Language");
        typeEnvironment = ClassType.make("gnu.mapping.Environment");
        typeLocation = ClassType.make("gnu.mapping.Location");
        typeSymbol = ClassType.make("gnu.mapping.Symbol");
        getSymbolValueMethod = typeLanguage.getDeclaredMethod("getSymbolValue", 1);
        getSymbolProcedureMethod = typeLanguage.getDeclaredMethod("getSymbolProcedure", 1);
        getLocationMethod = typeLocation.addMethod("get", Type.typeArray0, Type.objectType, 1);
        getProcedureBindingMethod = typeSymbol.addMethod("getProcedure", Type.typeArray0, typeProcedure, 1);
        trueConstant = scmBooleanType.getDeclaredField("TRUE");
        falseConstant = scmBooleanType.getDeclaredField("FALSE");
        setNameMethod = typeProcedure.getDeclaredMethod("setName", 1);
        Type atype[] = new Type[1];
        atype[0] = Type.intType;
        int1Args = atype;
        Type atype1[] = new Type[1];
        atype1[0] = javaStringType;
        string1Arg = atype1;
        sym1Arg = string1Arg;
        getLocation1EnvironmentMethod = typeEnvironment.getDeclaredMethod("getLocation", 1);
        Type atype2[] = new Type[2];
        atype2[0] = typeSymbol;
        atype2[1] = Type.objectType;
        getLocation2EnvironmentMethod = typeEnvironment.addMethod("getLocation", atype2, typeLocation, 17);
        Type atype3[] = new Type[2];
        atype3[0] = objArrayType;
        atype3[1] = Type.intType;
        makeListMethod = scmListType.addMethod("makeList", atype3, scmListType, 9);
        getCurrentEnvironmentMethod = typeEnvironment.addMethod("getCurrent", Type.typeArray0, typeEnvironment, 9);
        apply0args = Type.typeArray0;
        Type atype4[] = new Type[1];
        atype4[0] = typeObject;
        apply1args = atype4;
        Type atype5[] = new Type[2];
        atype5[0] = typeObject;
        atype5[1] = typeObject;
        apply2args = atype5;
        Type atype6[] = new Type[1];
        atype6[0] = objArrayType;
        applyNargs = atype6;
        apply0method = typeProcedure.addMethod("apply0", apply0args, typeObject, 17);
        apply1method = typeProcedure.addMethod("apply1", apply1args, typeObject, 1);
        apply2method = typeProcedure.addMethod("apply2", apply2args, typeObject, 1);
        Type atype7[] = new Type[3];
        atype7[0] = typeObject;
        atype7[1] = typeObject;
        atype7[2] = typeObject;
        apply3method = typeProcedure.addMethod("apply3", atype7, typeObject, 1);
        Type atype8[] = new Type[4];
        atype8[0] = typeObject;
        atype8[1] = typeObject;
        atype8[2] = typeObject;
        atype8[3] = typeObject;
        apply4method = typeProcedure.addMethod("apply4", atype8, typeObject, 1);
        applyNmethod = typeProcedure.addMethod("applyN", applyNargs, typeObject, 1);
        Type atype9[] = new Type[2];
        atype9[0] = typeProcedure;
        atype9[1] = Type.intType;
        checkArgCountMethod = typeProcedure.addMethod("checkArgCount", atype9, Type.voidType, 9);
        Method amethod[] = new Method[6];
        amethod[0] = apply0method;
        amethod[1] = apply1method;
        amethod[2] = apply2method;
        amethod[3] = apply3method;
        amethod[4] = apply4method;
        amethod[5] = applyNmethod;
        applymethods = amethod;
        typeProcedure0 = ClassType.make("gnu.mapping.Procedure0");
        typeProcedure1 = ClassType.make("gnu.mapping.Procedure1");
        typeProcedure2 = ClassType.make("gnu.mapping.Procedure2");
        typeProcedure3 = ClassType.make("gnu.mapping.Procedure3");
        typeProcedure4 = ClassType.make("gnu.mapping.Procedure4");
        typeCallContext = ClassType.make("gnu.mapping.CallContext");
        getCallContextInstanceMethod = typeCallContext.getDeclaredMethod("getInstance", 0);
        typeValues = ClassType.make("gnu.mapping.Values");
        noArgsField = typeValues.getDeclaredField("noArgs");
        pcCallContextField = typeCallContext.getDeclaredField("pc");
        argsCallContextField = typeCallContext.getDeclaredField("values");
        procCallContextField = typeCallContext.getDeclaredField("proc");
        Type atype10[] = new Type[1];
        atype10[0] = typeCallContext;
        applyCpsArgs = atype10;
        applyCpsMethod = typeProcedure.addMethod("apply", applyCpsArgs, Type.voidType, 1);
        ClassType aclasstype[] = new ClassType[5];
        aclasstype[0] = typeProcedure0;
        aclasstype[1] = typeProcedure1;
        aclasstype[2] = typeProcedure2;
        aclasstype[3] = typeProcedure3;
        aclasstype[4] = typeProcedure4;
        typeProcedureArray = aclasstype;
    }
}
