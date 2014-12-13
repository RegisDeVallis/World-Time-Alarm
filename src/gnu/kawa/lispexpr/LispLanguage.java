// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.lispexpr;

import gnu.bytecode.Field;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.NameLookup;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.Sequence;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.InPort;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import kawa.lang.Syntax;
import kawa.lang.Translator;

// Referenced classes of package gnu.kawa.lispexpr:
//            LispReader, ReadTable

public abstract class LispLanguage extends Language
{

    public static final Symbol bracket_apply_sym;
    public static final Symbol bracket_list_sym;
    public static StaticFieldLocation getNamedPartLocation;
    public static final Symbol lookup_sym;
    public static final String quasiquote_sym = "quasiquote";
    public static final String quote_sym = "quote";
    public static final String unquote_sym = "unquote";
    public static final String unquotesplicing_sym = "unquote-splicing";
    public ReadTable defaultReadTable;

    public LispLanguage()
    {
        defaultReadTable = createReadTable();
    }

    public static Symbol langSymbolToSymbol(Object obj)
    {
        return ((LispLanguage)Language.getDefaultLanguage()).fromLangSymbol(obj);
    }

    public Expression checkDefaultBinding(Symbol symbol, Translator translator)
    {
        return null;
    }

    public abstract ReadTable createReadTable();

    public Declaration declFromField(ModuleExp moduleexp, Object obj, Field field)
    {
        Declaration declaration = super.declFromField(moduleexp, obj, field);
        boolean flag;
        if ((0x10 & field.getModifiers()) != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && (obj instanceof Syntax))
        {
            declaration.setSyntax();
        }
        return declaration;
    }

    protected void defSntxStFld(String s, String s1)
    {
        defSntxStFld(s, s1, Compilation.mangleNameIfNeeded(s));
    }

    protected void defSntxStFld(String s, String s1, String s2)
    {
        Object obj;
        if (hasSeparateFunctionNamespace())
        {
            obj = EnvironmentKey.FUNCTION;
        } else
        {
            obj = null;
        }
        StaticFieldLocation.define(environ, environ.getSymbol(s), obj, s1, s2).setSyntax();
    }

    protected Symbol fromLangSymbol(Object obj)
    {
        if (obj instanceof String)
        {
            return getSymbol((String)obj);
        } else
        {
            return (Symbol)obj;
        }
    }

    public Compilation getCompilation(Lexer lexer, SourceMessages sourcemessages, NameLookup namelookup)
    {
        return new Translator(this, sourcemessages, namelookup);
    }

    public Lexer getLexer(InPort inport, SourceMessages sourcemessages)
    {
        return new LispReader(inport, sourcemessages);
    }

    public Expression makeApply(Expression expression, Expression aexpression[])
    {
        return new ApplyExp(expression, aexpression);
    }

    public Expression makeBody(Expression aexpression[])
    {
        return new BeginExp(aexpression);
    }

    public boolean parse(Compilation compilation, int i)
        throws IOException, SyntaxException
    {
        Translator translator;
        Lexer lexer;
        ModuleExp moduleexp;
        LispReader lispreader;
        Compilation compilation1;
        translator = (Translator)compilation;
        lexer = translator.lexer;
        moduleexp = translator.mainLambda;
        new Values();
        lispreader = (LispReader)lexer;
        compilation1 = Compilation.setSaveCurrent(translator);
        if (translator.pendingForm != null)
        {
            translator.scanForm(translator.pendingForm, moduleexp);
            translator.pendingForm = null;
        }
_L6:
        Object obj;
        Object obj1;
        obj = lispreader.readCommand();
        obj1 = Sequence.eofValue;
        if (obj != obj1) goto _L2; else goto _L1
_L1:
        if ((i & 4) != 0)
        {
            Compilation.restoreCurrent(compilation1);
            return false;
        }
          goto _L3
_L2:
        translator.scanForm(obj, moduleexp);
        if ((i & 4) == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (!translator.getMessages().seenErrors()) goto _L3; else goto _L4
_L4:
        int j = lispreader.peek();
        if (j >= 0 && j != 13 && j != 10)
        {
            break MISSING_BLOCK_LABEL_189;
        }
_L3:
        if (lexer.peek() == 41)
        {
            lexer.fatal("An unexpected close paren was read.");
        }
        translator.finishModule(moduleexp);
        if ((i & 8) != 0)
        {
            break MISSING_BLOCK_LABEL_177;
        }
        translator.firstForm = 0;
        translator.setState(4);
        Compilation.restoreCurrent(compilation1);
        return true;
        lispreader.skip();
          goto _L4
        Exception exception;
        exception;
        Compilation.restoreCurrent(compilation1);
        throw exception;
        if ((i & 8) == 0) goto _L6; else goto _L5
_L5:
        int k = translator.getState();
        if (k >= 2)
        {
            Compilation.restoreCurrent(compilation1);
            return true;
        }
          goto _L6
    }

    public void resolve(Compilation compilation)
    {
        Translator translator = (Translator)compilation;
        translator.resolveModule(translator.getModule());
    }

    public boolean selfEvaluatingSymbol(Object obj)
    {
        return obj instanceof Keyword;
    }

    static 
    {
        lookup_sym = Namespace.EmptyNamespace.getSymbol("$lookup$");
        bracket_list_sym = Namespace.EmptyNamespace.getSymbol("$bracket-list$");
        bracket_apply_sym = Namespace.EmptyNamespace.getSymbol("$bracket-apply$");
        getNamedPartLocation = new StaticFieldLocation("gnu.kawa.functions.GetNamedPart", "getNamedPart");
        getNamedPartLocation.setProcedure();
    }
}
