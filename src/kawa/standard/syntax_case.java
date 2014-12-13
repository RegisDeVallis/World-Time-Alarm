// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BlockExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ExitExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.LetExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.math.IntNum;
import java.util.Vector;
import kawa.lang.Pattern;
import kawa.lang.PatternScope;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.SyntaxPattern;
import kawa.lang.Translator;

// Referenced classes of package kawa.standard:
//            syntax_case_work

public class syntax_case extends Syntax
{

    public static final syntax_case syntax_case;
    PrimProcedure call_error;

    public syntax_case()
    {
    }

    public static Object error(String s, Object obj)
    {
        Translator translator = (Translator)Compilation.getCurrent();
        if (translator == null)
        {
            throw new RuntimeException("no match in syntax-case");
        }
        Syntax syntax = translator.getCurrentSyntax();
        String s1;
        if (syntax == null)
        {
            s1 = "some syntax";
        } else
        {
            s1 = syntax.getName();
        }
        return translator.syntaxError((new StringBuilder()).append("no matching case while expanding ").append(s1).toString());
    }

    Expression rewriteClauses(Object obj, syntax_case_work syntax_case_work1, Translator translator)
    {
        gnu.expr.Language language;
        Object obj1;
        language = translator.getLanguage();
        if (obj == LList.Empty)
        {
            Expression aexpression2[] = new Expression[2];
            aexpression2[0] = new QuoteExp("syntax-case");
            aexpression2[1] = new ReferenceExp(syntax_case_work1.inputExpression);
            if (call_error == null)
            {
                ClassType classtype = ClassType.make("kawa.standard.syntax_case");
                Type atype[] = new Type[2];
                atype[0] = Compilation.javaStringType;
                atype[1] = Type.objectType;
                gnu.bytecode.Method method = classtype.addMethod("error", atype, Type.objectType, 9);
                PrimProcedure primprocedure1 = new PrimProcedure(method, language);
                call_error = primprocedure1;
            }
            return new ApplyExp(call_error, aexpression2);
        }
        obj1 = translator.pushPositionOf(obj);
        Expression expression;
        Object obj2;
        if (obj instanceof Pair)
        {
            obj2 = ((Pair)obj).getCar();
            if (obj2 instanceof Pair)
            {
                break MISSING_BLOCK_LABEL_178;
            }
        }
        expression = translator.syntaxError("syntax-case:  bad clause list");
        translator.popPositionOf(obj1);
        return expression;
        Pair pair;
        PatternScope patternscope;
        pair = (Pair)obj2;
        patternscope = PatternScope.push(translator);
        patternscope.matchArray = translator.matchArray;
        translator.push(patternscope);
        SyntaxForm syntaxform = null;
        Object obj3;
        for (obj3 = pair.getCdr(); obj3 instanceof SyntaxForm; obj3 = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj3;
        }

        Expression expression4;
        if (obj3 instanceof Pair)
        {
            break MISSING_BLOCK_LABEL_268;
        }
        expression4 = translator.syntaxError("missing syntax-case output expression");
        translator.popPositionOf(obj1);
        return expression4;
        int i;
        int j;
        BlockExp blockexp;
        ApplyExp applyexp;
        i = patternscope.pattern_names.size();
        SyntaxPattern syntaxpattern = new SyntaxPattern(pair.getCar(), syntax_case_work1.literal_identifiers, translator);
        j = syntaxpattern.varCount();
        if (j > syntax_case_work1.maxVars)
        {
            syntax_case_work1.maxVars = j;
        }
        blockexp = new BlockExp();
        Expression aexpression[] = new Expression[4];
        QuoteExp quoteexp = new QuoteExp(syntaxpattern);
        aexpression[0] = quoteexp;
        aexpression[1] = new ReferenceExp(syntax_case_work1.inputExpression);
        aexpression[2] = new ReferenceExp(translator.matchArray);
        aexpression[3] = new QuoteExp(IntNum.zero());
        PrimProcedure primprocedure = new PrimProcedure(Pattern.matchPatternMethod, language);
        applyexp = new ApplyExp(primprocedure, aexpression);
        int k = j - i;
        Expression aexpression1[] = new Expression[k];
        int l = k;
_L2:
        if (--l < 0)
        {
            break; /* Loop/switch isn't completed */
        }
        aexpression1[l] = QuoteExp.undefined_exp;
        if (true) goto _L2; else goto _L1
        Exception exception;
        exception;
        translator.popPositionOf(obj1);
        throw exception;
_L1:
        Pair pair1;
        patternscope.inits = aexpression1;
        pair1 = (Pair)obj3;
        if (pair1.getCdr() != LList.Empty) goto _L4; else goto _L3
_L3:
        Object obj4 = translator.rewrite_car(pair1, syntaxform);
_L5:
        patternscope.setBody(((Expression) (obj4)));
        translator.pop(patternscope);
        PatternScope.pop(translator);
        ExitExp exitexp1 = new ExitExp(blockexp);
        IfExp ifexp = new IfExp(applyexp, patternscope, exitexp1);
        blockexp.setBody(ifexp, rewriteClauses(((Pair)obj).getCdr(), syntax_case_work1, translator));
        translator.popPositionOf(obj1);
        return blockexp;
_L4:
        Expression expression1;
        Expression expression2;
        Pair pair2;
        expression1 = translator.rewrite_car(pair1, syntaxform);
        if (pair1.getCdr() instanceof Pair)
        {
            pair2 = (Pair)pair1.getCdr();
            if (pair2.getCdr() == LList.Empty)
            {
                break MISSING_BLOCK_LABEL_636;
            }
        }
        expression2 = translator.syntaxError("syntax-case:  bad clause");
        translator.popPositionOf(obj1);
        return expression2;
        Expression expression3 = translator.rewrite_car(pair2, syntaxform);
        ExitExp exitexp = new ExitExp(blockexp);
        obj4 = new IfExp(expression1, expression3, exitexp);
          goto _L5
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        syntax_case_work syntax_case_work1 = new syntax_case_work();
        Object obj = pair.getCdr();
        if ((obj instanceof Pair) && (((Pair)obj).getCdr() instanceof Pair))
        {
            Expression aexpression[] = new Expression[2];
            LetExp letexp = new LetExp(aexpression);
            syntax_case_work1.inputExpression = letexp.addDeclaration((String)null);
            Declaration declaration = translator.matchArray;
            Declaration declaration1 = letexp.addDeclaration((String)null);
            declaration1.setType(Compilation.objArrayType);
            declaration1.setCanRead(true);
            translator.matchArray = declaration1;
            syntax_case_work1.inputExpression.setCanRead(true);
            translator.push(letexp);
            Pair pair1 = (Pair)obj;
            aexpression[0] = translator.rewrite(pair1.getCar());
            syntax_case_work1.inputExpression.noteValue(aexpression[0]);
            Pair pair2 = (Pair)pair1.getCdr();
            syntax_case_work1.literal_identifiers = SyntaxPattern.getLiteralsList(pair2.getCar(), null, translator);
            letexp.body = rewriteClauses(pair2.getCdr(), syntax_case_work1, translator);
            translator.pop(letexp);
            gnu.bytecode.Method method = ClassType.make("kawa.lang.SyntaxPattern").getDeclaredMethod("allocVars", 2);
            Expression aexpression1[] = new Expression[2];
            aexpression1[0] = new QuoteExp(IntNum.make(syntax_case_work1.maxVars));
            if (declaration == null)
            {
                aexpression1[1] = QuoteExp.nullExp;
            } else
            {
                aexpression1[1] = new ReferenceExp(declaration);
            }
            aexpression[1] = new ApplyExp(method, aexpression1);
            declaration1.noteValue(aexpression[1]);
            translator.matchArray = declaration;
            return letexp;
        } else
        {
            return translator.syntaxError("insufficiant arguments to syntax-case");
        }
    }

    static 
    {
        syntax_case = new syntax_case();
        syntax_case.setName("syntax-case");
    }
}
