// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.expr.ThisExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Macro;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class define_syntax extends Syntax
{

    public static final define_syntax define_macro = new define_syntax("%define-macro", false);
    public static final define_syntax define_syntax = new define_syntax("%define-syntax", true);
    static PrimProcedure makeHygienic;
    static PrimProcedure makeNonHygienic;
    static PrimProcedure setCapturedScope;
    static ClassType typeMacro;
    boolean hygienic;

    public define_syntax()
    {
        hygienic = true;
    }

    public define_syntax(Object obj, boolean flag)
    {
        super(obj);
        hygienic = flag;
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        return translator.syntaxError("define-syntax not in a body");
    }

    public void scanForm(Pair pair, ScopeExp scopeexp, Translator translator)
    {
        SyntaxForm syntaxform = null;
        Object obj;
        for (obj = pair.getCdr(); obj instanceof SyntaxForm; obj = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj;
        }

        Object obj1 = obj;
        Object obj2;
        SyntaxForm syntaxform1;
        Object obj3;
        if (obj1 instanceof Pair)
        {
            Pair pair1 = (Pair)obj1;
            obj2 = pair1.getCar();
            obj1 = pair1.getCdr();
        } else
        {
            obj2 = null;
        }
        syntaxform1 = syntaxform;
        for (obj3 = obj2; obj3 instanceof SyntaxForm; obj3 = syntaxform1.getDatum())
        {
            syntaxform1 = (SyntaxForm)obj3;
        }

        Object obj4 = translator.namespaceResolve(obj3);
        if (!(obj4 instanceof Symbol))
        {
            translator.formStack.addElement(translator.syntaxError((new StringBuilder()).append("missing macro name for ").append(Translator.safeCar(pair)).toString()));
        } else
        {
            if (obj1 == null || Translator.safeCdr(obj1) != LList.Empty)
            {
                translator.formStack.addElement(translator.syntaxError((new StringBuilder()).append("invalid syntax for ").append(getName()).toString()));
                return;
            }
            Declaration declaration = translator.define(obj4, syntaxform1, scopeexp);
            declaration.setType(typeMacro);
            translator.push(declaration);
            Macro macro = translator.currentMacroDefinition;
            Macro macro1 = Macro.make(declaration);
            macro1.setHygienic(hygienic);
            translator.currentMacroDefinition = macro1;
            Expression expression = translator.rewrite_car((Pair)obj1, syntaxform);
            translator.currentMacroDefinition = macro;
            macro1.expander = expression;
            if (expression instanceof LambdaExp)
            {
                ((LambdaExp)expression).setFlag(256);
            }
            Expression aexpression[] = new Expression[3];
            aexpression[0] = new QuoteExp(obj4);
            aexpression[1] = expression;
            aexpression[2] = ThisExp.makeGivingContext(scopeexp);
            PrimProcedure primprocedure;
            ApplyExp applyexp;
            if (hygienic)
            {
                primprocedure = makeHygienic;
            } else
            {
                primprocedure = makeNonHygienic;
            }
            applyexp = new ApplyExp(primprocedure, aexpression);
            declaration.noteValue(applyexp);
            declaration.setProcedureDecl(true);
            if (declaration.context instanceof ModuleExp)
            {
                SetExp setexp = new SetExp(declaration, applyexp);
                setexp.setDefining(true);
                if (translator.getLanguage().hasSeparateFunctionNamespace())
                {
                    setexp.setFuncDef(true);
                }
                translator.formStack.addElement(setexp);
                if (translator.immediate)
                {
                    Expression aexpression1[] = new Expression[2];
                    aexpression1[0] = new ReferenceExp(declaration);
                    aexpression1[1] = new QuoteExp(scopeexp);
                    translator.formStack.addElement(new ApplyExp(setCapturedScope, aexpression1));
                    return;
                }
            }
        }
    }

    static 
    {
        typeMacro = ClassType.make("kawa.lang.Macro");
        makeHygienic = new PrimProcedure(typeMacro.getDeclaredMethod("make", 3));
        makeNonHygienic = new PrimProcedure(typeMacro.getDeclaredMethod("makeNonHygienic", 3));
        setCapturedScope = new PrimProcedure(typeMacro.getDeclaredMethod("setCapturedScope", 1));
        makeHygienic.setSideEffectFree();
        makeNonHygienic.setSideEffectFree();
    }
}
