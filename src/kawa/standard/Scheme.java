// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.functions.DisplayFormat;
import gnu.kawa.functions.IsEq;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.IsEqv;
import gnu.kawa.functions.Map;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.functions.Not;
import gnu.kawa.functions.NumberCompare;
import gnu.kawa.functions.NumberPredicate;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.lispexpr.LispReader;
import gnu.kawa.lispexpr.ReadTable;
import gnu.kawa.lispexpr.ReaderDispatch;
import gnu.kawa.lispexpr.ReaderDispatchMisc;
import gnu.kawa.lispexpr.ReaderParens;
import gnu.kawa.lispexpr.ReaderQuote;
import gnu.kawa.reflect.InstanceOf;
import gnu.kawa.servlet.HttpRequestContext;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.AbstractFormat;
import gnu.mapping.CharArrayInPort;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.Namespace;
import gnu.mapping.SimpleEnvironment;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Unit;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import kawa.lang.Eval;
import kawa.lang.Translator;

// Referenced classes of package kawa.standard:
//            expt

public class Scheme extends LispLanguage
{

    public static final Apply apply;
    static final Declaration applyFieldDecl;
    public static final ApplyToArgs applyToArgs;
    public static LangPrimType booleanType;
    public static final AbstractFormat displayFormat;
    public static final Map forEach;
    public static final Scheme instance;
    public static final InstanceOf instanceOf;
    public static final IsEq isEq;
    public static final IsEqual isEqual;
    public static final IsEqv isEqv;
    public static final NumberPredicate isEven;
    public static final NumberPredicate isOdd;
    protected static final SimpleEnvironment kawaEnvironment;
    public static final Map map;
    public static final Not not;
    public static final Environment nullEnvironment;
    public static final NumberCompare numEqu;
    public static final NumberCompare numGEq;
    public static final NumberCompare numGrt;
    public static final NumberCompare numLEq;
    public static final NumberCompare numLss;
    public static final Environment r4Environment;
    public static final Environment r5Environment;
    static HashMap typeToStringMap;
    static HashMap types;
    public static final Namespace unitNamespace;
    public static final AbstractFormat writeFormat;

    public Scheme()
    {
        environ = kawaEnvironment;
        userEnv = getNewEnvironment();
    }

    protected Scheme(Environment environment)
    {
        environ = environment;
    }

    public static Environment builtin()
    {
        return kawaEnvironment;
    }

    public static Object eval(InPort inport, Environment environment)
    {
        SourceMessages sourcemessages = new SourceMessages();
        Object obj;
        Object obj1;
        try
        {
            obj = ReaderParens.readList((LispReader)Language.getDefaultLanguage().getLexer(inport, sourcemessages), 0, 1, -1);
            if (sourcemessages.seenErrors())
            {
                throw new SyntaxException(sourcemessages);
            }
        }
        catch (SyntaxException syntaxexception)
        {
            throw new RuntimeException((new StringBuilder()).append("eval: errors while compiling:\n").append(syntaxexception.getMessages().toString(20)).toString());
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException((new StringBuilder()).append("eval: I/O exception: ").append(ioexception.toString()).toString());
        }
        catch (RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch (Error error)
        {
            throw error;
        }
        catch (Throwable throwable)
        {
            throw new WrappedException(throwable);
        }
        obj1 = Eval.evalBody(obj, environment, sourcemessages);
        return obj1;
    }

    public static Object eval(Object obj, Environment environment)
    {
        Object obj1;
        try
        {
            obj1 = Eval.eval(obj, environment);
        }
        catch (RuntimeException runtimeexception)
        {
            throw runtimeexception;
        }
        catch (Error error)
        {
            throw error;
        }
        catch (Throwable throwable)
        {
            throw new WrappedException(throwable);
        }
        return obj1;
    }

    public static Object eval(String s, Environment environment)
    {
        return eval(((InPort) (new CharArrayInPort(s))), environment);
    }

    public static Type exp2Type(Expression expression)
    {
        return getInstance().getTypeFor(expression);
    }

    public static Scheme getInstance()
    {
        return instance;
    }

    public static Type getNamedType(String s)
    {
        getTypeMap();
        Type type = (Type)types.get(s);
        if (type == null && (s.startsWith("elisp:") || s.startsWith("clisp:")))
        {
            int i = s.indexOf(':');
            Class class1 = getNamedType(s.substring(i + 1)).getReflectClass();
            String s1 = s.substring(0, i);
            Language language = Language.getInstance(s1);
            if (language == null)
            {
                throw new RuntimeException((new StringBuilder()).append("unknown type '").append(s).append("' - unknown language '").append(s1).append('\'').toString());
            }
            type = language.getTypeFor(class1);
            if (type != null)
            {
                types.put(s, type);
            }
        }
        return type;
    }

    static HashMap getTypeMap()
    {
        kawa/standard/Scheme;
        JVM INSTR monitorenter ;
        HashMap hashmap;
        if (types == null)
        {
            booleanType = new LangPrimType(Type.booleanType, getInstance());
            types = new HashMap();
            types.put("void", LangPrimType.voidType);
            types.put("int", LangPrimType.intType);
            types.put("char", LangPrimType.charType);
            types.put("boolean", booleanType);
            types.put("byte", LangPrimType.byteType);
            types.put("short", LangPrimType.shortType);
            types.put("long", LangPrimType.longType);
            types.put("float", LangPrimType.floatType);
            types.put("double", LangPrimType.doubleType);
            types.put("never-returns", Type.neverReturnsType);
            types.put("Object", Type.objectType);
            types.put("String", Type.toStringType);
            types.put("object", Type.objectType);
            types.put("number", LangObjType.numericType);
            types.put("quantity", ClassType.make("gnu.math.Quantity"));
            types.put("complex", ClassType.make("gnu.math.Complex"));
            types.put("real", LangObjType.realType);
            types.put("rational", LangObjType.rationalType);
            types.put("integer", LangObjType.integerType);
            types.put("symbol", ClassType.make("gnu.mapping.Symbol"));
            types.put("namespace", ClassType.make("gnu.mapping.Namespace"));
            types.put("keyword", ClassType.make("gnu.expr.Keyword"));
            types.put("pair", ClassType.make("gnu.lists.Pair"));
            types.put("pair-with-position", ClassType.make("gnu.lists.PairWithPosition"));
            types.put("constant-string", ClassType.make("java.lang.String"));
            types.put("abstract-string", ClassType.make("gnu.lists.CharSeq"));
            types.put("character", ClassType.make("gnu.text.Char"));
            types.put("vector", LangObjType.vectorType);
            types.put("string", LangObjType.stringType);
            types.put("list", LangObjType.listType);
            types.put("function", ClassType.make("gnu.mapping.Procedure"));
            types.put("procedure", ClassType.make("gnu.mapping.Procedure"));
            types.put("input-port", ClassType.make("gnu.mapping.InPort"));
            types.put("output-port", ClassType.make("gnu.mapping.OutPort"));
            types.put("string-output-port", ClassType.make("gnu.mapping.CharArrayOutPort"));
            types.put("record", ClassType.make("kawa.lang.Record"));
            types.put("type", LangObjType.typeType);
            types.put("class-type", LangObjType.typeClassType);
            types.put("class", LangObjType.typeClass);
            types.put("s8vector", ClassType.make("gnu.lists.S8Vector"));
            types.put("u8vector", ClassType.make("gnu.lists.U8Vector"));
            types.put("s16vector", ClassType.make("gnu.lists.S16Vector"));
            types.put("u16vector", ClassType.make("gnu.lists.U16Vector"));
            types.put("s32vector", ClassType.make("gnu.lists.S32Vector"));
            types.put("u32vector", ClassType.make("gnu.lists.U32Vector"));
            types.put("s64vector", ClassType.make("gnu.lists.S64Vector"));
            types.put("u64vector", ClassType.make("gnu.lists.U64Vector"));
            types.put("f32vector", ClassType.make("gnu.lists.F32Vector"));
            types.put("f64vector", ClassType.make("gnu.lists.F64Vector"));
            types.put("document", ClassType.make("gnu.kawa.xml.KDocument"));
            types.put("readtable", ClassType.make("gnu.kawa.lispexpr.ReadTable"));
        }
        hashmap = types;
        kawa/standard/Scheme;
        JVM INSTR monitorexit ;
        return hashmap;
        Exception exception;
        exception;
        throw exception;
    }

    public static Type getTypeValue(Expression expression)
    {
        return getInstance().getTypeFor(expression);
    }

    private void initScheme()
    {
        environ = nullEnvironment;
        environ.addLocation(LispLanguage.lookup_sym, null, getNamedPartLocation);
        defSntxStFld("lambda", "kawa.standard.SchemeCompilation", "lambda");
        defSntxStFld("quote", "kawa.lang.Quote", "plainQuote");
        defSntxStFld("%define", "kawa.standard.define", "defineRaw");
        defSntxStFld("define", "kawa.lib.prim_syntax");
        defSntxStFld("if", "kawa.lib.prim_syntax");
        defSntxStFld("set!", "kawa.standard.set_b", "set");
        defSntxStFld("cond", "kawa.lib.std_syntax");
        defSntxStFld("case", "kawa.lib.std_syntax");
        defSntxStFld("and", "kawa.lib.std_syntax");
        defSntxStFld("or", "kawa.lib.std_syntax");
        defSntxStFld("%let", "kawa.standard.let", "let");
        defSntxStFld("let", "kawa.lib.std_syntax");
        defSntxStFld("let*", "kawa.lib.std_syntax");
        defSntxStFld("letrec", "kawa.lib.prim_syntax");
        defSntxStFld("begin", "kawa.standard.begin", "begin");
        defSntxStFld("do", "kawa.lib.std_syntax");
        defSntxStFld("delay", "kawa.lib.std_syntax");
        defProcStFld("%make-promise", "kawa.lib.std_syntax");
        defSntxStFld("quasiquote", "kawa.lang.Quote", "quasiQuote");
        defSntxStFld("define-syntax", "kawa.lib.prim_syntax");
        defSntxStFld("let-syntax", "kawa.standard.let_syntax", "let_syntax");
        defSntxStFld("letrec-syntax", "kawa.standard.let_syntax", "letrec_syntax");
        defSntxStFld("syntax-rules", "kawa.standard.syntax_rules", "syntax_rules");
        nullEnvironment.setLocked();
        environ = r4Environment;
        defProcStFld("not", "kawa.standard.Scheme");
        defProcStFld("boolean?", "kawa.lib.misc");
        defProcStFld("eq?", "kawa.standard.Scheme", "isEq");
        defProcStFld("eqv?", "kawa.standard.Scheme", "isEqv");
        defProcStFld("equal?", "kawa.standard.Scheme", "isEqual");
        defProcStFld("pair?", "kawa.lib.lists");
        defProcStFld("cons", "kawa.lib.lists");
        defProcStFld("car", "kawa.lib.lists");
        defProcStFld("cdr", "kawa.lib.lists");
        defProcStFld("set-car!", "kawa.lib.lists");
        defProcStFld("set-cdr!", "kawa.lib.lists");
        defProcStFld("caar", "kawa.lib.lists");
        defProcStFld("cadr", "kawa.lib.lists");
        defProcStFld("cdar", "kawa.lib.lists");
        defProcStFld("cddr", "kawa.lib.lists");
        defProcStFld("caaar", "kawa.lib.lists");
        defProcStFld("caadr", "kawa.lib.lists");
        defProcStFld("cadar", "kawa.lib.lists");
        defProcStFld("caddr", "kawa.lib.lists");
        defProcStFld("cdaar", "kawa.lib.lists");
        defProcStFld("cdadr", "kawa.lib.lists");
        defProcStFld("cddar", "kawa.lib.lists");
        defProcStFld("cdddr", "kawa.lib.lists");
        defProcStFld("caaaar", "kawa.lib.lists");
        defProcStFld("caaadr", "kawa.lib.lists");
        defProcStFld("caadar", "kawa.lib.lists");
        defProcStFld("caaddr", "kawa.lib.lists");
        defProcStFld("cadaar", "kawa.lib.lists");
        defProcStFld("cadadr", "kawa.lib.lists");
        defProcStFld("caddar", "kawa.lib.lists");
        defProcStFld("cadddr", "kawa.lib.lists");
        defProcStFld("cdaaar", "kawa.lib.lists");
        defProcStFld("cdaadr", "kawa.lib.lists");
        defProcStFld("cdadar", "kawa.lib.lists");
        defProcStFld("cdaddr", "kawa.lib.lists");
        defProcStFld("cddaar", "kawa.lib.lists");
        defProcStFld("cddadr", "kawa.lib.lists");
        defProcStFld("cdddar", "kawa.lib.lists");
        defProcStFld("cddddr", "kawa.lib.lists");
        defProcStFld("null?", "kawa.lib.lists");
        defProcStFld("list?", "kawa.lib.lists");
        defProcStFld("length", "kawa.lib.lists");
        defProcStFld("append", "kawa.standard.append", "append");
        defProcStFld("reverse", "kawa.lib.lists");
        defProcStFld("reverse!", "kawa.lib.lists");
        defProcStFld("list-tail", "kawa.lib.lists");
        defProcStFld("list-ref", "kawa.lib.lists");
        defProcStFld("memq", "kawa.lib.lists");
        defProcStFld("memv", "kawa.lib.lists");
        defProcStFld("member", "kawa.lib.lists");
        defProcStFld("assq", "kawa.lib.lists");
        defProcStFld("assv", "kawa.lib.lists");
        defProcStFld("assoc", "kawa.lib.lists");
        defProcStFld("symbol?", "kawa.lib.misc");
        defProcStFld("symbol->string", "kawa.lib.misc");
        defProcStFld("string->symbol", "kawa.lib.misc");
        defProcStFld("symbol=?", "kawa.lib.misc");
        defProcStFld("symbol-local-name", "kawa.lib.misc");
        defProcStFld("symbol-namespace", "kawa.lib.misc");
        defProcStFld("symbol-namespace-uri", "kawa.lib.misc");
        defProcStFld("symbol-prefix", "kawa.lib.misc");
        defProcStFld("namespace-uri", "kawa.lib.misc");
        defProcStFld("namespace-prefix", "kawa.lib.misc");
        defProcStFld("number?", "kawa.lib.numbers");
        defProcStFld("quantity?", "kawa.lib.numbers");
        defProcStFld("complex?", "kawa.lib.numbers");
        defProcStFld("real?", "kawa.lib.numbers");
        defProcStFld("rational?", "kawa.lib.numbers");
        defProcStFld("integer?", "kawa.lib.numbers");
        defProcStFld("exact?", "kawa.lib.numbers");
        defProcStFld("inexact?", "kawa.lib.numbers");
        defProcStFld("=", "kawa.standard.Scheme", "numEqu");
        defProcStFld("<", "kawa.standard.Scheme", "numLss");
        defProcStFld(">", "kawa.standard.Scheme", "numGrt");
        defProcStFld("<=", "kawa.standard.Scheme", "numLEq");
        defProcStFld(">=", "kawa.standard.Scheme", "numGEq");
        defProcStFld("zero?", "kawa.lib.numbers");
        defProcStFld("positive?", "kawa.lib.numbers");
        defProcStFld("negative?", "kawa.lib.numbers");
        defProcStFld("odd?", "kawa.standard.Scheme", "isOdd");
        defProcStFld("even?", "kawa.standard.Scheme", "isEven");
        defProcStFld("max", "kawa.lib.numbers");
        defProcStFld("min", "kawa.lib.numbers");
        defProcStFld("+", "gnu.kawa.functions.AddOp", "$Pl");
        defProcStFld("-", "gnu.kawa.functions.AddOp", "$Mn");
        defProcStFld("*", "gnu.kawa.functions.MultiplyOp", "$St");
        defProcStFld("/", "gnu.kawa.functions.DivideOp", "$Sl");
        defProcStFld("abs", "kawa.lib.numbers");
        defProcStFld("quotient", "gnu.kawa.functions.DivideOp", "quotient");
        defProcStFld("remainder", "gnu.kawa.functions.DivideOp", "remainder");
        defProcStFld("modulo", "gnu.kawa.functions.DivideOp", "modulo");
        defProcStFld("div", "gnu.kawa.functions.DivideOp", "div");
        defProcStFld("mod", "gnu.kawa.functions.DivideOp", "mod");
        defProcStFld("div0", "gnu.kawa.functions.DivideOp", "div0");
        defProcStFld("mod0", "gnu.kawa.functions.DivideOp", "mod0");
        defProcStFld("div-and-mod", "kawa.lib.numbers");
        defProcStFld("div0-and-mod0", "kawa.lib.numbers");
        defProcStFld("gcd", "kawa.lib.numbers");
        defProcStFld("lcm", "kawa.lib.numbers");
        defProcStFld("numerator", "kawa.lib.numbers");
        defProcStFld("denominator", "kawa.lib.numbers");
        defProcStFld("floor", "kawa.lib.numbers");
        defProcStFld("ceiling", "kawa.lib.numbers");
        defProcStFld("truncate", "kawa.lib.numbers");
        defProcStFld("round", "kawa.lib.numbers");
        defProcStFld("rationalize", "kawa.lib.numbers");
        defProcStFld("exp", "kawa.lib.numbers");
        defProcStFld("log", "kawa.lib.numbers");
        defProcStFld("sin", "kawa.lib.numbers");
        defProcStFld("cos", "kawa.lib.numbers");
        defProcStFld("tan", "kawa.lib.numbers");
        defProcStFld("asin", "kawa.lib.numbers");
        defProcStFld("acos", "kawa.lib.numbers");
        defProcStFld("atan", "kawa.lib.numbers");
        defProcStFld("sqrt", "kawa.lib.numbers");
        defProcStFld("expt", "kawa.standard.expt");
        defProcStFld("make-rectangular", "kawa.lib.numbers");
        defProcStFld("make-polar", "kawa.lib.numbers");
        defProcStFld("real-part", "kawa.lib.numbers");
        defProcStFld("imag-part", "kawa.lib.numbers");
        defProcStFld("magnitude", "kawa.lib.numbers");
        defProcStFld("angle", "kawa.lib.numbers");
        defProcStFld("inexact", "kawa.lib.numbers");
        defProcStFld("exact", "kawa.lib.numbers");
        defProcStFld("exact->inexact", "kawa.lib.numbers");
        defProcStFld("inexact->exact", "kawa.lib.numbers");
        defProcStFld("number->string", "kawa.lib.numbers");
        defProcStFld("string->number", "kawa.lib.numbers");
        defProcStFld("char?", "kawa.lib.characters");
        defProcStFld("char=?", "kawa.lib.characters");
        defProcStFld("char<?", "kawa.lib.characters");
        defProcStFld("char>?", "kawa.lib.characters");
        defProcStFld("char<=?", "kawa.lib.characters");
        defProcStFld("char>=?", "kawa.lib.characters");
        defProcStFld("char-ci=?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-ci<?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-ci>?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-ci<=?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-ci>=?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-alphabetic?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-numeric?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-whitespace?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-upper-case?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-lower-case?", "kawa.lib.rnrs.unicode");
        defProcStFld("char-title-case?", "kawa.lib.rnrs.unicode");
        defProcStFld("char->integer", "kawa.lib.characters");
        defProcStFld("integer->char", "kawa.lib.characters");
        defProcStFld("char-upcase", "kawa.lib.rnrs.unicode");
        defProcStFld("char-downcase", "kawa.lib.rnrs.unicode");
        defProcStFld("char-titlecase", "kawa.lib.rnrs.unicode");
        defProcStFld("char-foldcase", "kawa.lib.rnrs.unicode");
        defProcStFld("char-general-category", "kawa.lib.rnrs.unicode");
        defProcStFld("string?", "kawa.lib.strings");
        defProcStFld("make-string", "kawa.lib.strings");
        defProcStFld("string-length", "kawa.lib.strings");
        defProcStFld("string-ref", "kawa.lib.strings");
        defProcStFld("string-set!", "kawa.lib.strings");
        defProcStFld("string=?", "kawa.lib.strings");
        defProcStFld("string<?", "kawa.lib.strings");
        defProcStFld("string>?", "kawa.lib.strings");
        defProcStFld("string<=?", "kawa.lib.strings");
        defProcStFld("string>=?", "kawa.lib.strings");
        defProcStFld("string-ci=?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-ci<?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-ci>?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-ci<=?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-ci>=?", "kawa.lib.rnrs.unicode");
        defProcStFld("string-normalize-nfd", "kawa.lib.rnrs.unicode");
        defProcStFld("string-normalize-nfkd", "kawa.lib.rnrs.unicode");
        defProcStFld("string-normalize-nfc", "kawa.lib.rnrs.unicode");
        defProcStFld("string-normalize-nfkc", "kawa.lib.rnrs.unicode");
        defProcStFld("substring", "kawa.lib.strings");
        defProcStFld("string-append", "kawa.lib.strings");
        defProcStFld("string-append/shared", "kawa.lib.strings");
        defProcStFld("string->list", "kawa.lib.strings");
        defProcStFld("list->string", "kawa.lib.strings");
        defProcStFld("string-copy", "kawa.lib.strings");
        defProcStFld("string-fill!", "kawa.lib.strings");
        defProcStFld("vector?", "kawa.lib.vectors");
        defProcStFld("make-vector", "kawa.lib.vectors");
        defProcStFld("vector-length", "kawa.lib.vectors");
        defProcStFld("vector-ref", "kawa.lib.vectors");
        defProcStFld("vector-set!", "kawa.lib.vectors");
        defProcStFld("list->vector", "kawa.lib.vectors");
        defProcStFld("vector->list", "kawa.lib.vectors");
        defProcStFld("vector-fill!", "kawa.lib.vectors");
        defProcStFld("vector-append", "kawa.standard.vector_append", "vectorAppend");
        defProcStFld("values-append", "gnu.kawa.functions.AppendValues", "appendValues");
        defProcStFld("procedure?", "kawa.lib.misc");
        defProcStFld("apply", "kawa.standard.Scheme", "apply");
        defProcStFld("map", "kawa.standard.Scheme", "map");
        defProcStFld("for-each", "kawa.standard.Scheme", "forEach");
        defProcStFld("call-with-current-continuation", "gnu.kawa.functions.CallCC", "callcc");
        defProcStFld("call/cc", "kawa.standard.callcc", "callcc");
        defProcStFld("force", "kawa.lib.misc");
        defProcStFld("call-with-input-file", "kawa.lib.ports");
        defProcStFld("call-with-output-file", "kawa.lib.ports");
        defProcStFld("input-port?", "kawa.lib.ports");
        defProcStFld("output-port?", "kawa.lib.ports");
        defProcStFld("current-input-port", "kawa.lib.ports");
        defProcStFld("current-output-port", "kawa.lib.ports");
        defProcStFld("with-input-from-file", "kawa.lib.ports");
        defProcStFld("with-output-to-file", "kawa.lib.ports");
        defProcStFld("open-input-file", "kawa.lib.ports");
        defProcStFld("open-output-file", "kawa.lib.ports");
        defProcStFld("close-input-port", "kawa.lib.ports");
        defProcStFld("close-output-port", "kawa.lib.ports");
        defProcStFld("read", "kawa.lib.ports");
        defProcStFld("read-line", "kawa.lib.ports");
        defProcStFld("read-char", "kawa.standard.readchar", "readChar");
        defProcStFld("peek-char", "kawa.standard.readchar", "peekChar");
        defProcStFld("eof-object?", "kawa.lib.ports");
        defProcStFld("char-ready?", "kawa.lib.ports");
        defProcStFld("write", "kawa.lib.ports");
        defProcStFld("display", "kawa.lib.ports");
        defProcStFld("print-as-xml", "gnu.xquery.lang.XQuery", "writeFormat");
        defProcStFld("write-char", "kawa.lib.ports");
        defProcStFld("newline", "kawa.lib.ports");
        defProcStFld("load", "kawa.standard.load", "load");
        defProcStFld("load-relative", "kawa.standard.load", "loadRelative");
        defProcStFld("transcript-off", "kawa.lib.ports");
        defProcStFld("transcript-on", "kawa.lib.ports");
        defProcStFld("call-with-input-string", "kawa.lib.ports");
        defProcStFld("open-input-string", "kawa.lib.ports");
        defProcStFld("open-output-string", "kawa.lib.ports");
        defProcStFld("get-output-string", "kawa.lib.ports");
        defProcStFld("call-with-output-string", "kawa.lib.ports");
        defProcStFld("force-output", "kawa.lib.ports");
        defProcStFld("port-line", "kawa.lib.ports");
        defProcStFld("set-port-line!", "kawa.lib.ports");
        defProcStFld("port-column", "kawa.lib.ports");
        defProcStFld("current-error-port", "kawa.lib.ports");
        defProcStFld("input-port-line-number", "kawa.lib.ports");
        defProcStFld("set-input-port-line-number!", "kawa.lib.ports");
        defProcStFld("input-port-column-number", "kawa.lib.ports");
        defProcStFld("input-port-read-state", "kawa.lib.ports");
        defProcStFld("default-prompter", "kawa.lib.ports");
        defProcStFld("input-port-prompter", "kawa.lib.ports");
        defProcStFld("set-input-port-prompter!", "kawa.lib.ports");
        defProcStFld("base-uri", "kawa.lib.misc");
        defProcStFld("%syntax-error", "kawa.standard.syntax_error", "syntax_error");
        defProcStFld("syntax-error", "kawa.lib.prim_syntax");
        r4Environment.setLocked();
        environ = r5Environment;
        defProcStFld("values", "kawa.lib.misc");
        defProcStFld("call-with-values", "kawa.standard.call_with_values", "callWithValues");
        defSntxStFld("let-values", "kawa.lib.syntax");
        defSntxStFld("let*-values", "kawa.lib.syntax");
        defSntxStFld("case-lambda", "kawa.lib.syntax");
        defSntxStFld("receive", "kawa.lib.syntax");
        defProcStFld("eval", "kawa.lang.Eval");
        defProcStFld("repl", "kawa.standard.SchemeCompilation", "repl");
        defProcStFld("scheme-report-environment", "kawa.lib.misc");
        defProcStFld("null-environment", "kawa.lib.misc");
        defProcStFld("interaction-environment", "kawa.lib.misc");
        defProcStFld("dynamic-wind", "kawa.lib.misc");
        r5Environment.setLocked();
        environ = kawaEnvironment;
        defSntxStFld("define-private", "kawa.lib.prim_syntax");
        defSntxStFld("define-constant", "kawa.lib.prim_syntax");
        defSntxStFld("define-autoload", "kawa.standard.define_autoload", "define_autoload");
        defSntxStFld("define-autoloads-from-file", "kawa.standard.define_autoload", "define_autoloads_from_file");
        defProcStFld("exit", "kawa.lib.rnrs.programs");
        defProcStFld("command-line", "kawa.lib.rnrs.programs");
        defProcStFld("bitwise-arithmetic-shift", "gnu.kawa.functions.BitwiseOp", "ashift");
        defProcStFld("arithmetic-shift", "gnu.kawa.functions.BitwiseOp", "ashift");
        defProcStFld("ash", "gnu.kawa.functions.BitwiseOp", "ashift");
        defProcStFld("bitwise-arithmetic-shift-left", "gnu.kawa.functions.BitwiseOp", "ashiftl");
        defProcStFld("bitwise-arithmetic-shift-right", "gnu.kawa.functions.BitwiseOp", "ashiftr");
        defProcStFld("bitwise-and", "gnu.kawa.functions.BitwiseOp", "and");
        defProcStFld("logand", "gnu.kawa.functions.BitwiseOp", "and");
        defProcStFld("bitwise-ior", "gnu.kawa.functions.BitwiseOp", "ior");
        defProcStFld("logior", "gnu.kawa.functions.BitwiseOp", "ior");
        defProcStFld("bitwise-xor", "gnu.kawa.functions.BitwiseOp", "xor");
        defProcStFld("logxor", "gnu.kawa.functions.BitwiseOp", "xor");
        defProcStFld("bitwise-if", "kawa.lib.numbers");
        defProcStFld("bitwise-not", "gnu.kawa.functions.BitwiseOp", "not");
        defProcStFld("lognot", "gnu.kawa.functions.BitwiseOp", "not");
        defProcStFld("logop", "kawa.lib.numbers");
        defProcStFld("bitwise-bit-set?", "kawa.lib.numbers");
        defProcStFld("logbit?", "kawa.lib.numbers", Compilation.mangleNameIfNeeded("bitwise-bit-set?"));
        defProcStFld("logtest", "kawa.lib.numbers");
        defProcStFld("bitwise-bit-count", "kawa.lib.numbers");
        defProcStFld("logcount", "kawa.lib.numbers");
        defProcStFld("bitwise-copy-bit", "kawa.lib.numbers");
        defProcStFld("bitwise-copy-bit-field", "kawa.lib.numbers");
        defProcStFld("bitwise-bit-field", "kawa.lib.numbers");
        defProcStFld("bit-extract", "kawa.lib.numbers", Compilation.mangleNameIfNeeded("bitwise-bit-field"));
        defProcStFld("bitwise-length", "kawa.lib.numbers");
        defProcStFld("integer-length", "kawa.lib.numbers", "bitwise$Mnlength");
        defProcStFld("bitwise-first-bit-set", "kawa.lib.numbers");
        defProcStFld("bitwise-rotate-bit-field", "kawa.lib.numbers");
        defProcStFld("bitwise-reverse-bit-field", "kawa.lib.numbers");
        defProcStFld("string-upcase!", "kawa.lib.strings");
        defProcStFld("string-downcase!", "kawa.lib.strings");
        defProcStFld("string-capitalize!", "kawa.lib.strings");
        defProcStFld("string-upcase", "kawa.lib.rnrs.unicode");
        defProcStFld("string-downcase", "kawa.lib.rnrs.unicode");
        defProcStFld("string-titlecase", "kawa.lib.rnrs.unicode");
        defProcStFld("string-foldcase", "kawa.lib.rnrs.unicode");
        defProcStFld("string-capitalize", "kawa.lib.strings");
        defSntxStFld("primitive-virtual-method", "kawa.standard.prim_method", "virtual_method");
        defSntxStFld("primitive-static-method", "kawa.standard.prim_method", "static_method");
        defSntxStFld("primitive-interface-method", "kawa.standard.prim_method", "interface_method");
        defSntxStFld("primitive-constructor", "kawa.lib.reflection");
        defSntxStFld("primitive-op1", "kawa.standard.prim_method", "op1");
        defSntxStFld("primitive-get-field", "kawa.lib.reflection");
        defSntxStFld("primitive-set-field", "kawa.lib.reflection");
        defSntxStFld("primitive-get-static", "kawa.lib.reflection");
        defSntxStFld("primitive-set-static", "kawa.lib.reflection");
        defSntxStFld("primitive-array-new", "kawa.lib.reflection");
        defSntxStFld("primitive-array-get", "kawa.lib.reflection");
        defSntxStFld("primitive-array-set", "kawa.lib.reflection");
        defSntxStFld("primitive-array-length", "kawa.lib.reflection");
        defProcStFld("subtype?", "kawa.lib.reflection");
        defProcStFld("primitive-throw", "kawa.standard.prim_throw", "primitiveThrow");
        defSntxStFld("try-finally", "kawa.lib.syntax");
        defSntxStFld("try-catch", "kawa.lib.prim_syntax");
        defProcStFld("throw", "kawa.standard.throw_name", "throwName");
        defProcStFld("catch", "kawa.lib.system");
        defProcStFld("error", "kawa.lib.misc");
        defProcStFld("as", "gnu.kawa.functions.Convert", "as");
        defProcStFld("instance?", "kawa.standard.Scheme", "instanceOf");
        defSntxStFld("synchronized", "kawa.lib.syntax");
        defSntxStFld("object", "kawa.standard.object", "objectSyntax");
        defSntxStFld("define-class", "kawa.standard.define_class", "define_class");
        defSntxStFld("define-simple-class", "kawa.standard.define_class", "define_simple_class");
        defSntxStFld("this", "kawa.standard.thisRef", "thisSyntax");
        defProcStFld("make", "gnu.kawa.reflect.Invoke", "make");
        defProcStFld("slot-ref", "gnu.kawa.reflect.SlotGet", "slotRef");
        defProcStFld("slot-set!", "gnu.kawa.reflect.SlotSet", "set$Mnfield$Ex");
        defProcStFld("field", "gnu.kawa.reflect.SlotGet");
        defProcStFld("class-methods", "gnu.kawa.reflect.ClassMethods", "classMethods");
        defProcStFld("static-field", "gnu.kawa.reflect.SlotGet", "staticField");
        defProcStFld("invoke", "gnu.kawa.reflect.Invoke", "invoke");
        defProcStFld("invoke-static", "gnu.kawa.reflect.Invoke", "invokeStatic");
        defProcStFld("invoke-special", "gnu.kawa.reflect.Invoke", "invokeSpecial");
        defSntxStFld("define-macro", "kawa.lib.syntax");
        defSntxStFld("%define-macro", "kawa.standard.define_syntax", "define_macro");
        defSntxStFld("define-syntax-case", "kawa.lib.syntax");
        defSntxStFld("syntax-case", "kawa.standard.syntax_case", "syntax_case");
        defSntxStFld("%define-syntax", "kawa.standard.define_syntax", "define_syntax");
        defSntxStFld("syntax", "kawa.standard.syntax", "syntax");
        defSntxStFld("quasisyntax", "kawa.standard.syntax", "quasiSyntax");
        defProcStFld("syntax-object->datum", "kawa.lib.std_syntax");
        defProcStFld("datum->syntax-object", "kawa.lib.std_syntax");
        defProcStFld("syntax->expression", "kawa.lib.prim_syntax");
        defProcStFld("syntax-body->expression", "kawa.lib.prim_syntax");
        defProcStFld("generate-temporaries", "kawa.lib.std_syntax");
        defSntxStFld("with-syntax", "kawa.lib.std_syntax");
        defProcStFld("identifier?", "kawa.lib.std_syntax");
        defProcStFld("free-identifier=?", "kawa.lib.std_syntax");
        defProcStFld("syntax-source", "kawa.lib.std_syntax");
        defProcStFld("syntax-line", "kawa.lib.std_syntax");
        defProcStFld("syntax-column", "kawa.lib.std_syntax");
        defSntxStFld("begin-for-syntax", "kawa.lib.std_syntax");
        defSntxStFld("define-for-syntax", "kawa.lib.std_syntax");
        defSntxStFld("include", "kawa.lib.misc_syntax");
        defSntxStFld("include-relative", "kawa.lib.misc_syntax");
        defProcStFld("file-exists?", "kawa.lib.files");
        defProcStFld("file-directory?", "kawa.lib.files");
        defProcStFld("file-readable?", "kawa.lib.files");
        defProcStFld("file-writable?", "kawa.lib.files");
        defProcStFld("delete-file", "kawa.lib.files");
        defProcStFld("system-tmpdir", "kawa.lib.files");
        defProcStFld("make-temporary-file", "kawa.lib.files");
        defProcStFld("rename-file", "kawa.lib.files");
        defProcStFld("copy-file", "kawa.lib.files");
        defProcStFld("create-directory", "kawa.lib.files");
        defProcStFld("->pathname", "kawa.lib.files");
        define("port-char-encoding", Boolean.TRUE);
        define("symbol-read-case", "P");
        defProcStFld("system", "kawa.lib.system");
        defProcStFld("make-process", "kawa.lib.system");
        defProcStFld("tokenize-string-to-string-array", "kawa.lib.system");
        defProcStFld("tokenize-string-using-shell", "kawa.lib.system");
        defProcStFld("command-parse", "kawa.lib.system");
        defProcStFld("process-command-line-assignments", "kawa.lib.system");
        defProcStFld("record-accessor", "kawa.lib.reflection");
        defProcStFld("record-modifier", "kawa.lib.reflection");
        defProcStFld("record-predicate", "kawa.lib.reflection");
        defProcStFld("record-constructor", "kawa.lib.reflection");
        defProcStFld("make-record-type", "kawa.lib.reflection");
        defProcStFld("record-type-descriptor", "kawa.lib.reflection");
        defProcStFld("record-type-name", "kawa.lib.reflection");
        defProcStFld("record-type-field-names", "kawa.lib.reflection");
        defProcStFld("record?", "kawa.lib.reflection");
        defSntxStFld("define-record-type", "gnu.kawa.slib.DefineRecordType");
        defSntxStFld("when", "kawa.lib.syntax");
        defSntxStFld("unless", "kawa.lib.syntax");
        defSntxStFld("fluid-let", "kawa.standard.fluid_let", "fluid_let");
        defSntxStFld("constant-fold", "kawa.standard.constant_fold", "constant_fold");
        defProcStFld("make-parameter", "kawa.lib.parameters");
        defSntxStFld("parameterize", "kawa.lib.parameters");
        defProcStFld("compile-file", "kawa.lib.system");
        defProcStFld("environment-bound?", "kawa.lib.misc");
        defProcStFld("scheme-implementation-version", "kawa.lib.misc");
        defProcStFld("scheme-window", "kawa.lib.windows");
        defSntxStFld("define-procedure", "kawa.lib.std_syntax");
        defProcStFld("add-procedure-properties", "kawa.lib.misc");
        defProcStFld("make-procedure", "gnu.kawa.functions.MakeProcedure", "makeProcedure");
        defProcStFld("procedure-property", "kawa.lib.misc");
        defProcStFld("set-procedure-property!", "kawa.lib.misc");
        defSntxStFld("provide", "kawa.lib.misc_syntax");
        defSntxStFld("test-begin", "kawa.lib.misc_syntax");
        defProcStFld("quantity->number", "kawa.lib.numbers");
        defProcStFld("quantity->unit", "kawa.lib.numbers");
        defProcStFld("make-quantity", "kawa.lib.numbers");
        defSntxStFld("define-namespace", "gnu.kawa.lispexpr.DefineNamespace", "define_namespace");
        defSntxStFld("define-xml-namespace", "gnu.kawa.lispexpr.DefineNamespace", "define_xml_namespace");
        defSntxStFld("define-private-namespace", "gnu.kawa.lispexpr.DefineNamespace", "define_private_namespace");
        defSntxStFld("define-unit", "kawa.standard.define_unit", "define_unit");
        defSntxStFld("define-base-unit", "kawa.standard.define_unit", "define_base_unit");
        defProcStFld("duration", "kawa.lib.numbers");
        defProcStFld("gentemp", "kawa.lib.misc");
        defSntxStFld("defmacro", "kawa.lib.syntax");
        defProcStFld("setter", "gnu.kawa.functions.Setter", "setter");
        defSntxStFld("resource-url", "kawa.lib.misc_syntax");
        defSntxStFld("module-uri", "kawa.lib.misc_syntax");
        defSntxStFld("future", "kawa.lib.thread");
        defProcStFld("sleep", "kawa.lib.thread");
        defProcStFld("runnable", "kawa.lib.thread");
        defSntxStFld("trace", "kawa.lib.trace");
        defSntxStFld("untrace", "kawa.lib.trace");
        defSntxStFld("disassemble", "kawa.lib.trace");
        defProcStFld("format", "gnu.kawa.functions.Format");
        defProcStFld("parse-format", "gnu.kawa.functions.ParseFormat", "parseFormat");
        defProcStFld("make-element", "gnu.kawa.xml.MakeElement", "makeElement");
        defProcStFld("make-attribute", "gnu.kawa.xml.MakeAttribute", "makeAttribute");
        defProcStFld("map-values", "gnu.kawa.functions.ValuesMap", "valuesMap");
        defProcStFld("children", "gnu.kawa.xml.Children", "children");
        defProcStFld("attributes", "gnu.kawa.xml.Attributes");
        defProcStFld("unescaped-data", "gnu.kawa.xml.MakeUnescapedData", "unescapedData");
        defProcStFld("keyword?", "kawa.lib.keywords");
        defProcStFld("keyword->string", "kawa.lib.keywords");
        defProcStFld("string->keyword", "kawa.lib.keywords");
        defSntxStFld("location", "kawa.standard.location", "location");
        defSntxStFld("define-alias", "kawa.standard.define_alias", "define_alias");
        defSntxStFld("define-variable", "kawa.standard.define_variable", "define_variable");
        defSntxStFld("define-member-alias", "kawa.standard.define_member_alias", "define_member_alias");
        defSntxStFld("define-enum", "gnu.kawa.slib.enums");
        defSntxStFld("import", "kawa.lib.syntax");
        defSntxStFld("require", "kawa.standard.require", "require");
        defSntxStFld("module-name", "kawa.standard.module_name", "module_name");
        defSntxStFld("module-extends", "kawa.standard.module_extends", "module_extends");
        defSntxStFld("module-implements", "kawa.standard.module_implements", "module_implements");
        defSntxStFld("module-static", "kawa.standard.module_static", "module_static");
        defSntxStFld("module-export", "kawa.standard.export", "module_export");
        defSntxStFld("export", "kawa.standard.export", "export");
        defSntxStFld("module-compile-options", "kawa.standard.module_compile_options", "module_compile_options");
        defSntxStFld("with-compile-options", "kawa.standard.with_compile_options", "with_compile_options");
        defProcStFld("array?", "kawa.lib.arrays");
        defProcStFld("array-rank", "kawa.lib.arrays");
        defProcStFld("make-array", "kawa.lib.arrays");
        defProcStFld("array", "kawa.lib.arrays");
        defProcStFld("array-start", "kawa.lib.arrays");
        defProcStFld("array-end", "kawa.lib.arrays");
        defProcStFld("shape", "kawa.lib.arrays");
        defProcStFld("array-ref", "gnu.kawa.functions.ArrayRef", "arrayRef");
        defProcStFld("array-set!", "gnu.kawa.functions.ArraySet", "arraySet");
        defProcStFld("share-array", "kawa.lib.arrays");
        defProcStFld("s8vector?", "kawa.lib.uniform");
        defProcStFld("make-s8vector", "kawa.lib.uniform");
        defProcStFld("s8vector", "kawa.lib.uniform");
        defProcStFld("s8vector-length", "kawa.lib.uniform");
        defProcStFld("s8vector-ref", "kawa.lib.uniform");
        defProcStFld("s8vector-set!", "kawa.lib.uniform");
        defProcStFld("s8vector->list", "kawa.lib.uniform");
        defProcStFld("list->s8vector", "kawa.lib.uniform");
        defProcStFld("u8vector?", "kawa.lib.uniform");
        defProcStFld("make-u8vector", "kawa.lib.uniform");
        defProcStFld("u8vector", "kawa.lib.uniform");
        defProcStFld("u8vector-length", "kawa.lib.uniform");
        defProcStFld("u8vector-ref", "kawa.lib.uniform");
        defProcStFld("u8vector-set!", "kawa.lib.uniform");
        defProcStFld("u8vector->list", "kawa.lib.uniform");
        defProcStFld("list->u8vector", "kawa.lib.uniform");
        defProcStFld("s16vector?", "kawa.lib.uniform");
        defProcStFld("make-s16vector", "kawa.lib.uniform");
        defProcStFld("s16vector", "kawa.lib.uniform");
        defProcStFld("s16vector-length", "kawa.lib.uniform");
        defProcStFld("s16vector-ref", "kawa.lib.uniform");
        defProcStFld("s16vector-set!", "kawa.lib.uniform");
        defProcStFld("s16vector->list", "kawa.lib.uniform");
        defProcStFld("list->s16vector", "kawa.lib.uniform");
        defProcStFld("u16vector?", "kawa.lib.uniform");
        defProcStFld("make-u16vector", "kawa.lib.uniform");
        defProcStFld("u16vector", "kawa.lib.uniform");
        defProcStFld("u16vector-length", "kawa.lib.uniform");
        defProcStFld("u16vector-ref", "kawa.lib.uniform");
        defProcStFld("u16vector-set!", "kawa.lib.uniform");
        defProcStFld("u16vector->list", "kawa.lib.uniform");
        defProcStFld("list->u16vector", "kawa.lib.uniform");
        defProcStFld("s32vector?", "kawa.lib.uniform");
        defProcStFld("make-s32vector", "kawa.lib.uniform");
        defProcStFld("s32vector", "kawa.lib.uniform");
        defProcStFld("s32vector-length", "kawa.lib.uniform");
        defProcStFld("s32vector-ref", "kawa.lib.uniform");
        defProcStFld("s32vector-set!", "kawa.lib.uniform");
        defProcStFld("s32vector->list", "kawa.lib.uniform");
        defProcStFld("list->s32vector", "kawa.lib.uniform");
        defProcStFld("u32vector?", "kawa.lib.uniform");
        defProcStFld("make-u32vector", "kawa.lib.uniform");
        defProcStFld("u32vector", "kawa.lib.uniform");
        defProcStFld("u32vector-length", "kawa.lib.uniform");
        defProcStFld("u32vector-ref", "kawa.lib.uniform");
        defProcStFld("u32vector-set!", "kawa.lib.uniform");
        defProcStFld("u32vector->list", "kawa.lib.uniform");
        defProcStFld("list->u32vector", "kawa.lib.uniform");
        defProcStFld("s64vector?", "kawa.lib.uniform");
        defProcStFld("make-s64vector", "kawa.lib.uniform");
        defProcStFld("s64vector", "kawa.lib.uniform");
        defProcStFld("s64vector-length", "kawa.lib.uniform");
        defProcStFld("s64vector-ref", "kawa.lib.uniform");
        defProcStFld("s64vector-set!", "kawa.lib.uniform");
        defProcStFld("s64vector->list", "kawa.lib.uniform");
        defProcStFld("list->s64vector", "kawa.lib.uniform");
        defProcStFld("u64vector?", "kawa.lib.uniform");
        defProcStFld("make-u64vector", "kawa.lib.uniform");
        defProcStFld("u64vector", "kawa.lib.uniform");
        defProcStFld("u64vector-length", "kawa.lib.uniform");
        defProcStFld("u64vector-ref", "kawa.lib.uniform");
        defProcStFld("u64vector-set!", "kawa.lib.uniform");
        defProcStFld("u64vector->list", "kawa.lib.uniform");
        defProcStFld("list->u64vector", "kawa.lib.uniform");
        defProcStFld("f32vector?", "kawa.lib.uniform");
        defProcStFld("make-f32vector", "kawa.lib.uniform");
        defProcStFld("f32vector", "kawa.lib.uniform");
        defProcStFld("f32vector-length", "kawa.lib.uniform");
        defProcStFld("f32vector-ref", "kawa.lib.uniform");
        defProcStFld("f32vector-set!", "kawa.lib.uniform");
        defProcStFld("f32vector->list", "kawa.lib.uniform");
        defProcStFld("list->f32vector", "kawa.lib.uniform");
        defProcStFld("f64vector?", "kawa.lib.uniform");
        defProcStFld("make-f64vector", "kawa.lib.uniform");
        defProcStFld("f64vector", "kawa.lib.uniform");
        defProcStFld("f64vector-length", "kawa.lib.uniform");
        defProcStFld("f64vector-ref", "kawa.lib.uniform");
        defProcStFld("f64vector-set!", "kawa.lib.uniform");
        defProcStFld("f64vector->list", "kawa.lib.uniform");
        defProcStFld("list->f64vector", "kawa.lib.uniform");
        defSntxStFld("cut", "gnu.kawa.slib.cut");
        defSntxStFld("cute", "gnu.kawa.slib.cut");
        defSntxStFld("cond-expand", "kawa.lib.syntax");
        defSntxStFld("%cond-expand", "kawa.lib.syntax");
        defAliasStFld("*print-base*", "gnu.kawa.functions.DisplayFormat", "outBase");
        defAliasStFld("*print-radix*", "gnu.kawa.functions.DisplayFormat", "outRadix");
        defAliasStFld("*print-right-margin*", "gnu.text.PrettyWriter", "lineLengthLoc");
        defAliasStFld("*print-miser-width*", "gnu.text.PrettyWriter", "miserWidthLoc");
        defAliasStFld("html", "gnu.kawa.xml.XmlNamespace", "HTML");
        defAliasStFld("unit", "kawa.standard.Scheme", "unitNamespace");
        defAliasStFld("path", "gnu.kawa.lispexpr.LangObjType", "pathType");
        defAliasStFld("filepath", "gnu.kawa.lispexpr.LangObjType", "filepathType");
        defAliasStFld("URI", "gnu.kawa.lispexpr.LangObjType", "URIType");
        defProcStFld("resolve-uri", "kawa.lib.files");
        defAliasStFld("vector", "gnu.kawa.lispexpr.LangObjType", "vectorType");
        defAliasStFld("string", "gnu.kawa.lispexpr.LangObjType", "stringType");
        defAliasStFld("list", "gnu.kawa.lispexpr.LangObjType", "listType");
        defAliasStFld("regex", "gnu.kawa.lispexpr.LangObjType", "regexType");
        defProcStFld("path?", "kawa.lib.files");
        defProcStFld("filepath?", "kawa.lib.files");
        defProcStFld("URI?", "kawa.lib.files");
        defProcStFld("absolute-path?", "kawa.lib.files");
        defProcStFld("path-scheme", "kawa.lib.files");
        defProcStFld("path-authority", "kawa.lib.files");
        defProcStFld("path-user-info", "kawa.lib.files");
        defProcStFld("path-host", "kawa.lib.files");
        defProcStFld("path-port", "kawa.lib.files");
        defProcStFld("path-file", "kawa.lib.files");
        defProcStFld("path-parent", "kawa.lib.files");
        defProcStFld("path-directory", "kawa.lib.files");
        defProcStFld("path-last", "kawa.lib.files");
        defProcStFld("path-extension", "kawa.lib.files");
        defProcStFld("path-fragment", "kawa.lib.files");
        defProcStFld("path-query", "kawa.lib.files");
        kawaEnvironment.setLocked();
    }

    public static void registerEnvironment()
    {
        Language.setDefaults(getInstance());
    }

    public static Type string2Type(String s)
    {
        Object obj;
        if (s.endsWith("[]"))
        {
            obj = string2Type(s.substring(0, -2 + s.length()));
            if (obj != null)
            {
                obj = ArrayType.make(((Type) (obj)));
            }
        } else
        {
            obj = getNamedType(s);
        }
        if (obj != null)
        {
            return ((Type) (obj));
        }
        Type type = Language.string2Type(s);
        if (type != null)
        {
            types.put(s, type);
        }
        return type;
    }

    public Symbol asSymbol(String s)
    {
        return Namespace.EmptyNamespace.getSymbol(s);
    }

    public Expression checkDefaultBinding(Symbol symbol, Translator translator)
    {
        Namespace namespace;
        String s;
        String s1;
        int i;
        namespace = symbol.getNamespace();
        s = symbol.getLocalPart();
        if (namespace instanceof XmlNamespace)
        {
            return QuoteExp.getInstance(((XmlNamespace)namespace).get(s));
        }
        if (namespace.getName() == unitNamespace.getName())
        {
            gnu.math.NamedUnit namedunit = Unit.lookup(s);
            if (namedunit != null)
            {
                return QuoteExp.getInstance(namedunit);
            }
        }
        s1 = symbol.toString();
        i = s1.length();
        if (i == 0)
        {
            return null;
        }
        if (i <= 1) goto _L2; else goto _L1
_L1:
        int i6 = i - 1;
        if (s1.charAt(i6) != '?') goto _L2; else goto _L3
_L3:
        int j6 = s.length();
        if (j6 <= 1) goto _L2; else goto _L4
_L4:
        Expression expression1 = translator.rewrite(namespace.getSymbol(s.substring(0, j6 - 1).intern()), false);
        if (!(expression1 instanceof ReferenceExp)) goto _L6; else goto _L5
_L5:
        Declaration declaration1 = ((ReferenceExp)expression1).getBinding();
        if (declaration1 == null || declaration1.getFlag(0x10000L))
        {
            expression1 = null;
        }
_L7:
        if (expression1 != null)
        {
            LambdaExp lambdaexp = new LambdaExp(1);
            lambdaexp.setSymbol(symbol);
            Declaration declaration = lambdaexp.addDeclaration((Object)null);
            InstanceOf instanceof1 = instanceOf;
            Expression aexpression3[] = new Expression[2];
            ReferenceExp referenceexp = new ReferenceExp(declaration);
            aexpression3[0] = referenceexp;
            aexpression3[1] = expression1;
            lambdaexp.body = new ApplyExp(instanceof1, aexpression3);
            return lambdaexp;
        }
        break; /* Loop/switch isn't completed */
_L6:
        if (!(expression1 instanceof QuoteExp))
        {
            expression1 = null;
        }
        if (true) goto _L7; else goto _L2
_L2:
        char c = s1.charAt(0);
        if (c != '-' && c != '+' && Character.digit(c, 10) < 0) goto _L9; else goto _L8
_L8:
        int j;
        int k;
        j = 0;
        k = 0;
_L11:
        char c2;
        if (k >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        c2 = s1.charAt(k);
        if (Character.digit(c2, 10) >= 0)
        {
            if (j < 3)
            {
                j = 2;
            } else
            if (j < 5)
            {
                j = 4;
            } else
            {
                j = 5;
            }
        } else
        if ((c2 == '+' || c2 == '-') && j == 0)
        {
            j = 1;
        } else
        {
label0:
            {
                if (c2 != '.' || j >= 3)
                {
                    break label0;
                }
                j = 3;
            }
        }
_L20:
        k++;
        if (true) goto _L11; else goto _L10
        if (c2 != 'e' && c2 != 'E' || j != 2 && j != 4 || k + 1 >= i) goto _L10; else goto _L12
_L12:
        int l5;
        char c3;
        l5 = k + 1;
        c3 = s1.charAt(l5);
        if ((c3 == '-' || c3 == '+') && ++l5 < i)
        {
            c3 = s1.charAt(l5);
        }
        if (Character.digit(c3, 10) >= 0) goto _L13; else goto _L10
_L10:
        if (k >= i || j <= 1) goto _L9; else goto _L14
_L14:
        DFloNum dflonum;
        boolean flag1;
        Vector vector;
        int i2;
        dflonum = new DFloNum(s1.substring(0, k));
        flag1 = false;
        vector = new Vector();
        i2 = k;
_L33:
        if (i2 >= i) goto _L16; else goto _L15
_L15:
        int l2;
        char c1;
        l2 = i2 + 1;
        c1 = s1.charAt(i2);
        if (c1 != '*') goto _L18; else goto _L17
_L17:
        boolean flag;
        int l;
        if (l2 != i) goto _L19; else goto _L9
_L13:
        j = 5;
        k = l5 + 1;
          goto _L20
_L19:
        int k5 = l2 + 1;
        c1 = s1.charAt(l2);
        l2 = k5;
_L35:
        j3 = l2 - 1;
_L37:
        if (Character.isLetter(c1)) goto _L22; else goto _L21
_L21:
        l3 = l2 - 1;
        if (l3 == j3) goto _L9; else goto _L23
_L23:
        vector.addElement(s1.substring(j3, l3));
        if (c1 != '^') goto _L25; else goto _L24
_L24:
        boolean flag2 = true;
        if (l2 == i) goto _L9; else goto _L26
_L26:
        int i4;
        i4 = l2 + 1;
        c1 = s1.charAt(l2);
_L58:
        flag3 = flag1;
        if (c1 != '+') goto _L28; else goto _L27
_L27:
        flag2 = true;
        if (i4 == i) goto _L9; else goto _L29
_L29:
        int j4;
        j4 = i4 + 1;
        c1 = s1.charAt(i4);
_L41:
        k4 = 0;
        l4 = 0;
_L44:
        i5 = Character.digit(c1, 10);
        if (i5 > 0) goto _L31; else goto _L30
_L30:
        j4--;
_L43:
        if (k4 != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        l4 = 1;
        if (flag2) goto _L9; else goto _L32
_L32:
        if (flag3)
        {
            l4 = -l4;
        }
        vector.addElement(IntNum.make(l4));
        i2 = j4;
          goto _L33
_L18:
        if (c1 != '/') goto _L35; else goto _L34
_L34:
        if (l2 == i || flag1) goto _L9; else goto _L36
_L36:
        flag1 = true;
        int i3 = l2 + 1;
        c1 = s1.charAt(l2);
        l2 = i3;
          goto _L35
_L22:
label1:
        {
            if (l2 != i)
            {
                break label1;
            }
            l3 = l2;
            c1 = '1';
        }
          goto _L23
        int k3 = l2 + 1;
        c1 = s1.charAt(l2);
        l2 = k3;
          goto _L37
_L28:
        if (c1 != '-') goto _L39; else goto _L38
_L38:
        flag2 = true;
        if (i4 == i) goto _L9; else goto _L40
_L40:
        j4 = i4 + 1;
        c1 = s1.charAt(i4);
        if (!flag3)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
          goto _L41
_L31:
        l4 = i5 + l4 * 10;
        k4++;
        if (j4 == i) goto _L43; else goto _L42
_L42:
        int j5 = j4 + 1;
        c1 = s1.charAt(j4);
        j4 = j5;
          goto _L44
_L16:
        if (i2 == i)
        {
            int j2 = vector.size() >> 1;
            Expression aexpression[] = new Expression[j2];
            for (int k2 = 0; k2 < j2; k2++)
            {
                String s3 = (String)vector.elementAt(k2 * 2);
                Object obj3 = translator.rewrite(unitNamespace.getSymbol(s3.intern()));
                IntNum intnum = (IntNum)vector.elementAt(1 + k2 * 2);
                if (intnum.longValue() != 1L)
                {
                    expt expt1 = expt.expt;
                    Expression aexpression2[] = new Expression[2];
                    aexpression2[0] = ((Expression) (obj3));
                    aexpression2[1] = QuoteExp.getInstance(intnum);
                    ApplyExp applyexp1 = new ApplyExp(expt1, aexpression2);
                    obj3 = applyexp1;
                }
                aexpression[k2] = ((Expression) (obj3));
            }

            Object obj2;
            MultiplyOp multiplyop1;
            Expression aexpression1[];
            ApplyExp applyexp;
            if (j2 == 1)
            {
                obj2 = aexpression[0];
            } else
            {
                MultiplyOp multiplyop = MultiplyOp.$St;
                obj2 = new ApplyExp(multiplyop, aexpression);
            }
            multiplyop1 = MultiplyOp.$St;
            aexpression1 = new Expression[2];
            aexpression1[0] = QuoteExp.getInstance(dflonum);
            aexpression1[1] = ((Expression) (obj2));
            applyexp = new ApplyExp(multiplyop1, aexpression1);
            return applyexp;
        }
_L9:
        String s2;
        int j3;
        int l3;
        boolean flag3;
        int k4;
        int l4;
        int i5;
        if (i > 2 && c == '<')
        {
label2:
            {
                int k1 = i - 1;
                if (s1.charAt(k1) == '>')
                {
                    int l1 = i - 1;
                    s1 = s1.substring(1, l1);
                    i -= 2;
                    flag = true;
                    break label2;
                }
            }
        }
        flag = false;
        l = 0;
        do
        {
            if (i <= 2)
            {
                break;
            }
            int i1 = i - 2;
            if (s1.charAt(i1) != '[')
            {
                break;
            }
            int j1 = i - 1;
            if (s1.charAt(j1) != ']')
            {
                break;
            }
            i -= 2;
            l++;
        } while (true);
        s2 = s1;
        if (l != 0)
        {
            s2 = s1.substring(0, i);
        }
        Object obj = getNamedType(s2);
        if (l <= 0 || flag && obj != null) goto _L46; else goto _L45
_L45:
        Expression expression = InlineCalls.inlineCalls(translator.rewrite(namespace.getSymbol(s2.intern()), false), translator);
        if (!(expression instanceof ErrorExp))
        {
            obj = translator.getLanguage().getTypeFor(expression);
        }
          goto _L46
_L47:
        if (--l < 0)
        {
            break MISSING_BLOCK_LABEL_1408;
        }
        obj = ArrayType.make(((Type) (obj)));
          goto _L47
        return QuoteExp.getInstance(obj);
_L59:
        Type type = Type.lookupType(s2);
        if (!(type instanceof PrimType)) goto _L49; else goto _L48
_L48:
        Class class1 = type.getReflectClass();
_L57:
        if (class1 == null) goto _L51; else goto _L50
_L50:
        if (l <= 0) goto _L53; else goto _L52
_L52:
        Object obj1 = Type.make(class1);
_L56:
        if (--l < 0) goto _L55; else goto _L54
_L54:
        obj1 = ArrayType.make(((Type) (obj1)));
          goto _L56
_L49:
        if (s2.indexOf('.') < 0)
        {
            s2 = (new StringBuilder()).append(translator.classPrefix).append(Compilation.mangleNameIfNeeded(s2)).toString();
        }
        class1 = ClassType.getContextClass(s2);
          goto _L57
_L55:
        class1 = ((Type) (obj1)).getReflectClass();
_L53:
        QuoteExp quoteexp = QuoteExp.getInstance(class1);
        return quoteexp;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        Package package1 = ArrayClassLoader.getContextPackage(s1);
        if (package1 != null)
        {
            return QuoteExp.getInstance(package1);
        }
          goto _L51
        Throwable throwable;
        throwable;
_L51:
        return null;
_L39:
        j4 = i4;
          goto _L41
_L25:
        i4 = l2;
        flag2 = false;
          goto _L58
_L46:
        if (obj == null) goto _L59; else goto _L47
    }

    public ReadTable createReadTable()
    {
        ReadTable readtable = ReadTable.createInitial();
        readtable.postfixLookupOperator = ':';
        ReaderDispatch readerdispatch = (ReaderDispatch)readtable.lookup(35);
        readerdispatch.set(39, new ReaderQuote(asSymbol("syntax")));
        readerdispatch.set(96, new ReaderQuote(asSymbol("quasisyntax")));
        readerdispatch.set(44, ReaderDispatchMisc.getInstance());
        readtable.putReaderCtorFld("path", "gnu.kawa.lispexpr.LangObjType", "pathType");
        readtable.putReaderCtorFld("filepath", "gnu.kawa.lispexpr.LangObjType", "filepathType");
        readtable.putReaderCtorFld("URI", "gnu.kawa.lispexpr.LangObjType", "URIType");
        readtable.putReaderCtor("symbol", ClassType.make("gnu.mapping.Symbol"));
        readtable.putReaderCtor("namespace", ClassType.make("gnu.mapping.Namespace"));
        readtable.putReaderCtorFld("duration", "kawa.lib.numbers", "duration");
        readtable.setFinalColonIsKeyword(true);
        return readtable;
    }

    public String formatType(Type type)
    {
        if (typeToStringMap == null)
        {
            typeToStringMap = new HashMap();
            Iterator iterator = getTypeMap().entrySet().iterator();
            do
            {
                if (!iterator.hasNext())
                {
                    break;
                }
                java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                String s1 = (String)entry.getKey();
                Type type1 = (Type)entry.getValue();
                typeToStringMap.put(type1, s1);
                Type type2 = type1.getImplementationType();
                if (type2 != type1)
                {
                    typeToStringMap.put(type2, s1);
                }
            } while (true);
        }
        String s = (String)typeToStringMap.get(type);
        if (s != null)
        {
            return s;
        } else
        {
            return super.formatType(type);
        }
    }

    public AbstractFormat getFormat(boolean flag)
    {
        if (flag)
        {
            return writeFormat;
        } else
        {
            return displayFormat;
        }
    }

    public String getName()
    {
        return "Scheme";
    }

    public int getNamespaceOf(Declaration declaration)
    {
        return 3;
    }

    public Type getTypeFor(Class class1)
    {
        String s = class1.getName();
        if (class1.isPrimitive())
        {
            return getNamedType(s);
        }
        if ("java.lang.String".equals(s))
        {
            return Type.toStringType;
        }
        if ("gnu.math.IntNum".equals(s))
        {
            return LangObjType.integerType;
        }
        if ("gnu.math.DFloNum".equals(s))
        {
            return LangObjType.dflonumType;
        }
        if ("gnu.math.RatNum".equals(s))
        {
            return LangObjType.rationalType;
        }
        if ("gnu.math.RealNum".equals(s))
        {
            return LangObjType.realType;
        }
        if ("gnu.math.Numeric".equals(s))
        {
            return LangObjType.numericType;
        }
        if ("gnu.lists.FVector".equals(s))
        {
            return LangObjType.vectorType;
        }
        if ("gnu.lists.LList".equals(s))
        {
            return LangObjType.listType;
        }
        if ("gnu.text.Path".equals(s))
        {
            return LangObjType.pathType;
        }
        if ("gnu.text.URIPath".equals(s))
        {
            return LangObjType.URIType;
        }
        if ("gnu.text.FilePath".equals(s))
        {
            return LangObjType.filepathType;
        }
        if ("java.lang.Class".equals(s))
        {
            return LangObjType.typeClass;
        }
        if ("gnu.bytecode.Type".equals(s))
        {
            return LangObjType.typeType;
        }
        if ("gnu.bytecode.ClassType".equals(s))
        {
            return LangObjType.typeClassType;
        } else
        {
            return Type.make(class1);
        }
    }

    public Type getTypeFor(String s)
    {
        return string2Type(s);
    }

    public Expression makeApply(Expression expression, Expression aexpression[])
    {
        Expression aexpression1[] = new Expression[1 + aexpression.length];
        aexpression1[0] = expression;
        System.arraycopy(aexpression, 0, aexpression1, 1, aexpression.length);
        return new ApplyExp(new ReferenceExp(applyFieldDecl), aexpression1);
    }

    static 
    {
        int i;
        nullEnvironment = Environment.make("null-environment");
        r4Environment = Environment.make("r4rs-environment", nullEnvironment);
        r5Environment = Environment.make("r5rs-environment", r4Environment);
        kawaEnvironment = Environment.make("kawa-environment", r5Environment);
        instance = new Scheme(kawaEnvironment);
        instanceOf = new InstanceOf(instance, "instance?");
        not = new Not(instance, "not");
        applyToArgs = new ApplyToArgs("apply-to-args", instance);
        applyFieldDecl = Declaration.getDeclarationFromStatic("kawa.standard.Scheme", "applyToArgs");
        apply = new Apply("apply", applyToArgs);
        isEq = new IsEq(instance, "eq?");
        isEqv = new IsEqv(instance, "eqv?", isEq);
        isEqual = new IsEqual(instance, "equal?");
        map = new Map(true, applyToArgs, applyFieldDecl, isEq);
        forEach = new Map(false, applyToArgs, applyFieldDecl, isEq);
        numEqu = NumberCompare.make(instance, "=", 8);
        numGrt = NumberCompare.make(instance, ">", 16);
        numGEq = NumberCompare.make(instance, ">=", 24);
        numLss = NumberCompare.make(instance, "<", 4);
        numLEq = NumberCompare.make(instance, "<=", 12);
        isOdd = new NumberPredicate(instance, "odd?", 1);
        isEven = new NumberPredicate(instance, "even?", 2);
        instance.initScheme();
        i = HttpRequestContext.importServletDefinitions;
        if (i <= 0)
        {
            break MISSING_BLOCK_LABEL_339;
        }
        Scheme scheme = instance;
        String s;
        if (i > 1)
        {
            s = "gnu.kawa.servlet.servlets";
        } else
        {
            s = "gnu.kawa.servlet.HTTP";
        }
        try
        {
            scheme.loadClass(s);
        }
        catch (Throwable throwable) { }
        writeFormat = new DisplayFormat(true, 'S');
        displayFormat = new DisplayFormat(false, 'S');
        unitNamespace = Namespace.valueOf("http://kawa.gnu.org/unit", "unit");
        return;
    }
}
