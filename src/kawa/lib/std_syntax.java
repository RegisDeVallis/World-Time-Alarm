// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.QuoteExp;
import gnu.expr.Symbols;
import gnu.kawa.functions.AddOp;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.Eval;
import kawa.lang.Macro;
import kawa.lang.Pattern;
import kawa.lang.Quote;
import kawa.lang.SyntaxForm;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;
import kawa.standard.Scheme;
import kawa.standard.syntax_case;

public class std_syntax extends ModuleBody
{

    public static final Macro $Prvt$$Pccase;
    public static final Macro $Prvt$$Pccase$Mnmatch;
    public static final Macro $Prvt$$Pcdo$Mninit;
    public static final Macro $Prvt$$Pcdo$Mnlambda1;
    public static final Macro $Prvt$$Pcdo$Mnlambda2;
    public static final Macro $Prvt$$Pcdo$Mnstep;
    public static final Macro $Prvt$$Pclet$Mninit;
    public static final Macro $Prvt$$Pclet$Mnlambda1;
    public static final Macro $Prvt$$Pclet$Mnlambda2;
    public static final Location $Prvt$define = StaticFieldLocation.make("kawa.lib.prim_syntax", "define");
    public static final Location $Prvt$define$Mnconstant = StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnconstant");
    public static final Location $Prvt$if = StaticFieldLocation.make("kawa.lib.prim_syntax", "if");
    public static final Location $Prvt$letrec = StaticFieldLocation.make("kawa.lib.prim_syntax", "letrec");
    public static final std_syntax $instance;
    static final IntNum Lit0 = IntNum.make(0);
    static final IntNum Lit1 = IntNum.make(1);
    static final SimpleSymbol Lit10;
    static final SyntaxPattern Lit11 = new SyntaxPattern("\f\007\b", new Object[0], 1);
    static final SyntaxPattern Lit12 = new SyntaxPattern("\f\007\f\017\b", new Object[0], 2);
    static final SyntaxTemplate Lit13 = new SyntaxTemplate("\001\001", "\013", new Object[0], 0);
    static final SyntaxPattern Lit14 = new SyntaxPattern("\f\007\f\017\r\027\020\b\b", new Object[0], 3);
    static final SyntaxTemplate Lit15;
    static final SimpleSymbol Lit16;
    static final SyntaxPattern Lit17 = new SyntaxPattern("\f\007\b", new Object[0], 1);
    static final SyntaxPattern Lit18 = new SyntaxPattern("\f\007\f\017\b", new Object[0], 2);
    static final SyntaxTemplate Lit19 = new SyntaxTemplate("\001\001", "\013", new Object[0], 0);
    static final SimpleSymbol Lit2;
    static final SyntaxPattern Lit20 = new SyntaxPattern("\f\007\f\017\r\027\020\b\b", new Object[0], 3);
    static final SyntaxTemplate Lit21;
    static final SimpleSymbol Lit22;
    static final SyntaxRules Lit23;
    static final SimpleSymbol Lit24;
    static final SyntaxRules Lit25;
    static final SimpleSymbol Lit26;
    static final SyntaxRules Lit27;
    static final SimpleSymbol Lit28;
    static final SyntaxRules Lit29;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit30;
    static final SyntaxRules Lit31;
    static final SimpleSymbol Lit32;
    static final SyntaxRules Lit33;
    static final SimpleSymbol Lit34;
    static final SyntaxRules Lit35;
    static final SimpleSymbol Lit36;
    static final SyntaxRules Lit37;
    static final SimpleSymbol Lit38;
    static final SyntaxRules Lit39;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit40;
    static final SyntaxRules Lit41;
    static final SimpleSymbol Lit42;
    static final SyntaxRules Lit43;
    static final SimpleSymbol Lit44;
    static final SyntaxRules Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit50;
    static final SimpleSymbol Lit51;
    static final SimpleSymbol Lit52;
    static final SimpleSymbol Lit53;
    static final SimpleSymbol Lit54;
    static final SyntaxPattern Lit55 = new SyntaxPattern("\f\007\013", new Object[0], 2);
    static final SimpleSymbol Lit56;
    static final SyntaxTemplate Lit57 = new SyntaxTemplate("\001\0", "\n", new Object[0], 0);
    static final SyntaxTemplate Lit58;
    static final SimpleSymbol Lit59;
    static final SimpleSymbol Lit6;
    static final SyntaxRules Lit60;
    static final SimpleSymbol Lit61;
    static final SyntaxRules Lit62;
    static final SimpleSymbol Lit63;
    static final SimpleSymbol Lit64;
    static final SimpleSymbol Lit65;
    static final SimpleSymbol Lit66;
    static final SimpleSymbol Lit67;
    static final SimpleSymbol Lit68;
    static final SimpleSymbol Lit69;
    static final SyntaxRules Lit7;
    static final SimpleSymbol Lit70;
    static final SimpleSymbol Lit71;
    static final SimpleSymbol Lit72;
    static final SimpleSymbol Lit73;
    static final SimpleSymbol Lit74;
    static final SimpleSymbol Lit75;
    static final SimpleSymbol Lit76;
    static final SimpleSymbol Lit77;
    static final SimpleSymbol Lit8;
    static final SyntaxRules Lit9;
    public static final Macro and;
    public static final Macro begin$Mnfor$Mnsyntax;
    public static final Macro _fldcase;
    public static final Macro cond;
    public static final ModuleMethod datum$Mn$Grsyntax$Mnobject;
    public static final Macro define$Mnfor$Mnsyntax;
    public static final Macro define$Mnprocedure;
    public static final Macro delay;
    public static final Macro _flddo;
    public static final ModuleMethod free$Mnidentifier$Eq$Qu;
    public static final ModuleMethod generate$Mntemporaries;
    public static final ModuleMethod identifier$Qu;
    public static final Macro let;
    public static final Macro let$St;
    public static final Macro or;
    public static final ModuleMethod syntax$Mncolumn;
    public static final ModuleMethod syntax$Mnline;
    public static final ModuleMethod syntax$Mnobject$Mn$Grdatum;
    public static final ModuleMethod syntax$Mnsource;
    public static final Macro with$Mnsyntax;

    public std_syntax()
    {
        ModuleInfo.register(this);
    }

    public static Object datum$To$SyntaxObject(Object obj, Object obj1)
    {
        return SyntaxForms.makeWithTemplate(obj, obj1);
    }

    public static Object generateTemporaries(Object obj)
    {
        Object obj1 = Integer.valueOf(Translator.listLength(obj));
        Object obj2 = LList.Empty;
        do
        {
            if (Scheme.numEqu.apply2(obj1, Lit0) != Boolean.FALSE)
            {
                return obj2;
            }
            obj1 = AddOp.$Mn.apply2(obj1, Lit1);
            obj2 = new Pair(datum$To$SyntaxObject(obj, Symbols.gentemp()), obj2);
        } while (true);
    }

    public static boolean isFreeIdentifier$Eq(Object obj, Object obj1)
    {
        SyntaxForm syntaxform;
        SyntaxForm syntaxform1;
        try
        {
            syntaxform = (SyntaxForm)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "kawa.lang.SyntaxForms.freeIdentifierEquals(kawa.lang.SyntaxForm,kawa.lang.SyntaxForm)", 1, obj);
        }
        try
        {
            syntaxform1 = (SyntaxForm)obj1;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "kawa.lang.SyntaxForms.freeIdentifierEquals(kawa.lang.SyntaxForm,kawa.lang.SyntaxForm)", 2, obj1);
        }
        return SyntaxForms.freeIdentifierEquals(syntaxform, syntaxform1);
    }

    public static boolean isIdentifier(Object obj)
    {
        boolean flag = obj instanceof Symbol;
        if (!flag)
        {
            if (flag = obj instanceof SyntaxForm)
            {
                SyntaxForm syntaxform;
                try
                {
                    syntaxform = (SyntaxForm)obj;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "kawa.lang.SyntaxForms.isIdentifier(kawa.lang.SyntaxForm)", 1, obj);
                }
                return SyntaxForms.isIdentifier(syntaxform);
            }
        }
        return flag;
    }

    static Object lambda1(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(3, null);
        if (Lit11.match(obj, aobj, 0))
        {
            return new QuoteExp(Language.getDefaultLanguage().booleanObject(true));
        }
        if (Lit12.match(obj, aobj, 0))
        {
            TemplateScope templatescope1 = TemplateScope.make();
            return Lit13.execute(aobj, templatescope1);
        }
        if (Lit14.match(obj, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            return Lit15.execute(aobj, templatescope);
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    static Object lambda2(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(3, null);
        if (Lit17.match(obj, aobj, 0))
        {
            return new QuoteExp(Language.getDefaultLanguage().booleanObject(false));
        }
        if (Lit18.match(obj, aobj, 0))
        {
            TemplateScope templatescope1 = TemplateScope.make();
            return Lit19.execute(aobj, templatescope1);
        }
        if (Lit20.match(obj, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            return Lit21.execute(aobj, templatescope);
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    static Object lambda3(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(2, null);
        if (Lit55.match(obj, aobj, 0))
        {
            Eval eval = Eval.eval;
            SimpleSymbol simplesymbol = Lit56;
            TemplateScope templatescope = TemplateScope.make();
            if (eval.apply1(syntaxObject$To$Datum(new Pair(simplesymbol, Lit57.execute(aobj, templatescope)))) != Boolean.FALSE)
            {
                TemplateScope templatescope1 = TemplateScope.make();
                return Lit58.execute(aobj, templatescope1);
            }
        }
        return syntax_case.error("syntax-case", obj);
    }

    public static Object syntaxColumn(Object obj)
    {
        if (obj instanceof SyntaxForm)
        {
            return syntaxLine(((SyntaxForm)obj).getDatum());
        }
        if (obj instanceof PairWithPosition)
        {
            return Integer.valueOf(0 + ((PairWithPosition)obj).getColumnNumber());
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static Object syntaxLine(Object obj)
    {
        if (obj instanceof SyntaxForm)
        {
            return syntaxLine(((SyntaxForm)obj).getDatum());
        }
        if (obj instanceof PairWithPosition)
        {
            return Integer.valueOf(((PairWithPosition)obj).getLineNumber());
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static Object syntaxObject$To$Datum(Object obj)
    {
        return Quote.quote(obj);
    }

    public static Object syntaxSource(Object obj)
    {
        Object obj1;
        if (obj instanceof SyntaxForm)
        {
            obj1 = syntaxSource(((SyntaxForm)obj).getDatum());
        } else
        if (obj instanceof PairWithPosition)
        {
            obj1 = ((PairWithPosition)obj).getFileName();
            if (obj1 == null)
            {
                return Boolean.FALSE;
            }
        } else
        {
            return Boolean.FALSE;
        }
        return obj1;
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        case 4: // '\004'
        case 7: // '\007'
        default:
            return super.apply1(modulemethod, obj);

        case 3: // '\003'
            return syntaxObject$To$Datum(obj);

        case 5: // '\005'
            return generateTemporaries(obj);

        case 6: // '\006'
            if (isIdentifier(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 8: // '\b'
            return syntaxSource(obj);

        case 9: // '\t'
            return syntaxLine(obj);

        case 10: // '\n'
            return syntaxColumn(obj);

        case 1: // '\001'
            return lambda1(obj);

        case 2: // '\002'
            return lambda2(obj);

        case 11: // '\013'
            return lambda3(obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        case 5: // '\005'
        case 6: // '\006'
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 4: // '\004'
            return datum$To$SyntaxObject(obj, obj1);

        case 7: // '\007'
            break;
        }
        if (isFreeIdentifier$Eq(obj, obj1))
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 4: // '\004'
        case 7: // '\007'
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 11: // '\013'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 2: // '\002'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 1: // '\001'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 10: // '\n'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 9: // '\t'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 8: // '\b'
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

        case 3: // '\003'
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
        case 5: // '\005'
        case 6: // '\006'
        default:
            return super.match2(modulemethod, obj, obj1, callcontext);

        case 7: // '\007'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 4: // '\004'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit77 = (SimpleSymbol)(new SimpleSymbol("temp")).readResolve();
        Lit76 = (SimpleSymbol)(new SimpleSymbol("=>")).readResolve();
        Lit75 = (SimpleSymbol)(new SimpleSymbol("else")).readResolve();
        Lit74 = (SimpleSymbol)(new SimpleSymbol("eqv?")).readResolve();
        Lit73 = (SimpleSymbol)(new SimpleSymbol("x")).readResolve();
        Lit72 = (SimpleSymbol)(new SimpleSymbol("if")).readResolve();
        Lit71 = (SimpleSymbol)(new SimpleSymbol("letrec")).readResolve();
        Lit70 = (SimpleSymbol)(new SimpleSymbol("%let")).readResolve();
        Lit69 = (SimpleSymbol)(new SimpleSymbol("%syntax-error")).readResolve();
        Lit68 = (SimpleSymbol)(new SimpleSymbol("lambda")).readResolve();
        Lit67 = (SimpleSymbol)(new SimpleSymbol("make")).readResolve();
        Lit66 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
        Lit65 = (SimpleSymbol)(new SimpleSymbol("<gnu.expr.GenericProc>")).readResolve();
        Lit64 = (SimpleSymbol)(new SimpleSymbol("::")).readResolve();
        Lit63 = (SimpleSymbol)(new SimpleSymbol("syntax-case")).readResolve();
        Object aobj[] = new Object[1];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("with-syntax")).readResolve();
        Lit61 = simplesymbol;
        aobj[0] = simplesymbol;
        SyntaxRule asyntaxrule[] = new SyntaxRule[3];
        SyntaxPattern syntaxpattern = new SyntaxPattern("\f\030\f\b\f\007\r\017\b\b\b", new Object[0], 2);
        Object aobj1[] = new Object[1];
        SimpleSymbol simplesymbol1 = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
        Lit56 = simplesymbol1;
        aobj1[0] = simplesymbol1;
        asyntaxrule[0] = new SyntaxRule(syntaxpattern, "\001\003", "\021\030\004\t\003\b\r\013", aobj1, 1);
        SyntaxPattern syntaxpattern1 = new SyntaxPattern("\f\030<,\f\007\f\017\b\b\f\027\r\037\030\b\b", new Object[0], 4);
        Object aobj2[] = new Object[2];
        aobj2[0] = Lit63;
        aobj2[1] = Lit56;
        asyntaxrule[1] = new SyntaxRule(syntaxpattern1, "\001\001\001\003", "\021\030\004\t\013\t\020\b\t\003\b\021\030\f\t\023\b\035\033", aobj2, 1);
        SyntaxPattern syntaxpattern2 = new SyntaxPattern("\f\030L-\f\007\f\017\b\000\020\b\f\027\r\037\030\b\b", new Object[0], 4);
        Object aobj3[] = new Object[3];
        aobj3[0] = Lit63;
        aobj3[1] = (SimpleSymbol)(new SimpleSymbol("list")).readResolve();
        aobj3[2] = Lit56;
        asyntaxrule[2] = new SyntaxRule(syntaxpattern2, "\003\003\001\003", "\021\030\0041\021\030\f\b\r\013\t\020\b\031\b\005\003\b\021\030\024\t\023\b\035\033", aobj3, 1);
        Lit62 = new SyntaxRules(aobj, asyntaxrule, 4);
        Object aobj4[] = new Object[1];
        SimpleSymbol simplesymbol2 = (SimpleSymbol)(new SimpleSymbol("define-for-syntax")).readResolve();
        Lit59 = simplesymbol2;
        aobj4[0] = simplesymbol2;
        SyntaxRule asyntaxrule1[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern3 = new SyntaxPattern("\f\030\003", new Object[0], 1);
        Object aobj5[] = new Object[2];
        SimpleSymbol simplesymbol3 = (SimpleSymbol)(new SimpleSymbol("begin-for-syntax")).readResolve();
        Lit54 = simplesymbol3;
        aobj5[0] = simplesymbol3;
        aobj5[1] = (SimpleSymbol)(new SimpleSymbol("define")).readResolve();
        asyntaxrule1[0] = new SyntaxRule(syntaxpattern3, "\0", "\021\030\004\b\021\030\f\002", aobj5, 0);
        Lit60 = new SyntaxRules(aobj4, asyntaxrule1, 1);
        Object aobj6[] = new Object[1];
        aobj6[0] = Values.empty;
        Lit58 = new SyntaxTemplate("\001\0", "\030\004", aobj6, 0);
        Lit53 = (SimpleSymbol)(new SimpleSymbol("syntax-column")).readResolve();
        Lit52 = (SimpleSymbol)(new SimpleSymbol("syntax-line")).readResolve();
        Lit51 = (SimpleSymbol)(new SimpleSymbol("syntax-source")).readResolve();
        Lit50 = (SimpleSymbol)(new SimpleSymbol("free-identifier=?")).readResolve();
        Lit49 = (SimpleSymbol)(new SimpleSymbol("identifier?")).readResolve();
        Lit48 = (SimpleSymbol)(new SimpleSymbol("generate-temporaries")).readResolve();
        Lit47 = (SimpleSymbol)(new SimpleSymbol("datum->syntax-object")).readResolve();
        Lit46 = (SimpleSymbol)(new SimpleSymbol("syntax-object->datum")).readResolve();
        Object aobj7[] = new Object[3];
        SimpleSymbol simplesymbol4 = (SimpleSymbol)(new SimpleSymbol("define-procedure")).readResolve();
        Lit44 = simplesymbol4;
        aobj7[0] = simplesymbol4;
        aobj7[1] = Lit64;
        aobj7[2] = Lit65;
        SyntaxRule asyntaxrule2[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern4 = new SyntaxPattern("\f\030\f\007\r\017\b\b\b", new Object[0], 2);
        Object aobj8[] = new Object[9];
        aobj8[0] = Lit56;
        aobj8[1] = (SimpleSymbol)(new SimpleSymbol("define-constant")).readResolve();
        aobj8[2] = Lit64;
        aobj8[3] = Lit65;
        aobj8[4] = Lit67;
        aobj8[5] = Lit66;
        aobj8[6] = (SimpleSymbol)(new SimpleSymbol("invoke")).readResolve();
        aobj8[7] = PairWithPosition.make(Lit66, PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("setProperties")).readResolve(), LList.Empty, "std_syntax.scm", 0xfa014), "std_syntax.scm", 0xfa014);
        aobj8[8] = (SimpleSymbol)(new SimpleSymbol("java.lang.Object[]")).readResolve();
        asyntaxrule2[0] = new SyntaxRule(syntaxpattern4, "\001\003", "\021\030\004\301\021\030\f\t\003\021\030\024\021\030\034\b\021\030$\021\030\034\b\021\030,\b\003\b\021\0304\t\003\021\030<\b\021\030D\b\r\013", aobj8, 1);
        Lit45 = new SyntaxRules(aobj7, asyntaxrule2, 2);
        Object aobj9[] = new Object[1];
        SimpleSymbol simplesymbol5 = (SimpleSymbol)(new SimpleSymbol("delay")).readResolve();
        Lit42 = simplesymbol5;
        aobj9[0] = simplesymbol5;
        SyntaxRule asyntaxrule3[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern5 = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
        Object aobj10[] = new Object[3];
        aobj10[0] = Lit67;
        aobj10[1] = (SimpleSymbol)(new SimpleSymbol("<kawa.lang.Promise>")).readResolve();
        aobj10[2] = Lit68;
        asyntaxrule3[0] = new SyntaxRule(syntaxpattern5, "\001", "\021\030\004\021\030\f\b\021\030\024\t\020\b\003", aobj10, 0);
        Lit43 = new SyntaxRules(aobj9, asyntaxrule3, 1);
        Object aobj11[] = new Object[2];
        SimpleSymbol simplesymbol6 = (SimpleSymbol)(new SimpleSymbol("do")).readResolve();
        Lit40 = simplesymbol6;
        aobj11[0] = simplesymbol6;
        aobj11[1] = Lit64;
        SyntaxRule asyntaxrule4[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern6 = new SyntaxPattern("\f\030,\r\007\000\b\b\034\f\017\023\r\037\030\b\b", new Object[0], 4);
        Object aobj12[] = new Object[9];
        aobj12[0] = Lit71;
        aobj12[1] = (SimpleSymbol)(new SimpleSymbol("%do%loop")).readResolve();
        SimpleSymbol simplesymbol7 = (SimpleSymbol)(new SimpleSymbol("%do-lambda1")).readResolve();
        Lit36 = simplesymbol7;
        aobj12[2] = simplesymbol7;
        aobj12[3] = Lit72;
        aobj12[4] = (SimpleSymbol)(new SimpleSymbol("not")).readResolve();
        aobj12[5] = Lit56;
        SimpleSymbol simplesymbol8 = (SimpleSymbol)(new SimpleSymbol("%do-step")).readResolve();
        Lit32 = simplesymbol8;
        aobj12[6] = simplesymbol8;
        aobj12[7] = Values.empty;
        SimpleSymbol simplesymbol9 = (SimpleSymbol)(new SimpleSymbol("%do-init")).readResolve();
        Lit34 = simplesymbol9;
        aobj12[8] = simplesymbol9;
        asyntaxrule4[0] = new SyntaxRule(syntaxpattern6, "\003\001\000\003", "\021\030\004\u0189\b\021\030\f\b\021\030\024\031\b\005\003\t\020\b\021\030\034)\021\030$\b\013\201\021\030,\021\035\033\b\021\030\f\b\005\021\0304\003\b\021\030,\021\030<\022\b\021\030\f\b\005\021\030D\b\003", aobj12, 1);
        Lit41 = new SyntaxRules(aobj11, asyntaxrule4, 4);
        Object aobj13[] = new Object[1];
        SimpleSymbol simplesymbol10 = (SimpleSymbol)(new SimpleSymbol("%do-lambda2")).readResolve();
        Lit38 = simplesymbol10;
        aobj13[0] = simplesymbol10;
        SyntaxRule asyntaxrule5[] = new SyntaxRule[2];
        SyntaxPattern syntaxpattern7 = new SyntaxPattern("\f\030\034\f\007\013\f\027\f\037\b", new Object[0], 4);
        Object aobj14[] = new Object[1];
        aobj14[0] = Lit38;
        asyntaxrule5[0] = new SyntaxRule(syntaxpattern7, "\001\000\001\001", "\021\030\004\t\n\031\t\003\023\b\033", aobj14, 0);
        SyntaxPattern syntaxpattern8 = new SyntaxPattern("\f\030\f\b\f\007\f\017\b", new Object[0], 2);
        Object aobj15[] = new Object[1];
        aobj15[0] = Lit68;
        asyntaxrule5[1] = new SyntaxRule(syntaxpattern8, "\001\001", "\021\030\004\t\003\b\013", aobj15, 0);
        Lit39 = new SyntaxRules(aobj13, asyntaxrule5, 4);
        Object aobj16[] = new Object[2];
        aobj16[0] = Lit36;
        aobj16[1] = Lit64;
        SyntaxRule asyntaxrule6[] = new SyntaxRule[5];
        Object aobj17[] = new Object[1];
        aobj17[0] = Lit64;
        SyntaxPattern syntaxpattern9 = new SyntaxPattern("\f\030l\\\f\007\f\002\f\017\f\027\f\037\b#\f/\f7\b", aobj17, 7);
        Object aobj18[] = new Object[2];
        aobj18[0] = Lit36;
        aobj18[1] = Lit64;
        asyntaxrule6[0] = new SyntaxRule(syntaxpattern9, "\001\001\001\001\000\001\001", "\021\030\004\t\"I9\t\003\021\030\f\b\013+\b3", aobj18, 0);
        Object aobj19[] = new Object[1];
        aobj19[0] = Lit64;
        SyntaxPattern syntaxpattern10 = new SyntaxPattern("\f\030\\L\f\007\f\002\f\017\f\027\b\033\f'\f/\b", aobj19, 6);
        Object aobj20[] = new Object[2];
        aobj20[0] = Lit36;
        aobj20[1] = Lit64;
        asyntaxrule6[1] = new SyntaxRule(syntaxpattern10, "\001\001\001\000\001\001", "\021\030\004\t\032I9\t\003\021\030\f\b\013#\b+", aobj20, 0);
        SyntaxPattern syntaxpattern11 = new SyntaxPattern("\f\030L<\f\007\f\017\f\027\b\033\f'\f/\b", new Object[0], 6);
        Object aobj21[] = new Object[1];
        aobj21[0] = Lit36;
        asyntaxrule6[2] = new SyntaxRule(syntaxpattern11, "\001\001\001\000\001\001", "\021\030\004\t\032\031\t\003#\b+", aobj21, 0);
        SyntaxPattern syntaxpattern12 = new SyntaxPattern("\f\030<,\f\007\f\017\b\023\f\037\f'\b", new Object[0], 5);
        Object aobj22[] = new Object[1];
        aobj22[0] = Lit36;
        asyntaxrule6[3] = new SyntaxRule(syntaxpattern12, "\001\001\000\001\001", "\021\030\004\t\022\031\t\003\033\b#", aobj22, 0);
        SyntaxPattern syntaxpattern13 = new SyntaxPattern("\f\030\f\b\f\007\f\017\b", new Object[0], 2);
        Object aobj23[] = new Object[1];
        aobj23[0] = Lit38;
        asyntaxrule6[4] = new SyntaxRule(syntaxpattern13, "\001\001", "\021\030\004\t\003\t\020\b\013", aobj23, 0);
        Lit37 = new SyntaxRules(aobj16, asyntaxrule6, 7);
        Object aobj24[] = new Object[2];
        aobj24[0] = Lit34;
        aobj24[1] = Lit64;
        SyntaxRule asyntaxrule7[] = new SyntaxRule[7];
        Object aobj25[] = new Object[1];
        aobj25[0] = Lit64;
        asyntaxrule7[0] = new SyntaxRule(new SyntaxPattern("\f\030\\\f\007\f\002\f\017\f\027\f\037\b\b", aobj25, 4), "\001\001\001\001", "\023", new Object[0], 0);
        Object aobj26[] = new Object[1];
        aobj26[0] = Lit64;
        asyntaxrule7[1] = new SyntaxRule(new SyntaxPattern("\f\030L\f\007\f\002\f\017\f\027\b\b", aobj26, 3), "\001\001\001", "\023", new Object[0], 0);
        asyntaxrule7[2] = new SyntaxRule(new SyntaxPattern("\f\030<\f\007\f\017\f\027\b\b", new Object[0], 3), "\001\001\001", "\013", new Object[0], 0);
        asyntaxrule7[3] = new SyntaxRule(new SyntaxPattern("\f\030,\f\007\f\017\b\b", new Object[0], 2), "\001\001", "\013", new Object[0], 0);
        asyntaxrule7[4] = new SyntaxRule(new SyntaxPattern("\f\030<\f\007\f\017\f\027\b\b", new Object[0], 3), "\001\001\001", "\023", new Object[0], 0);
        SyntaxPattern syntaxpattern14 = new SyntaxPattern("\f\030\034\f\007\b\b", new Object[0], 1);
        Object aobj27[] = new Object[1];
        aobj27[0] = PairWithPosition.make(Lit69, PairWithPosition.make("do binding with no value", LList.Empty, "std_syntax.scm", 0xc2013), "std_syntax.scm", 0xc2004);
        asyntaxrule7[5] = new SyntaxRule(syntaxpattern14, "\001", "\030\004", aobj27, 0);
        SyntaxPattern syntaxpattern15 = new SyntaxPattern("\f\030L\f\007\f\017\f\027\f\037\b\b", new Object[0], 4);
        Object aobj28[] = new Object[1];
        aobj28[0] = PairWithPosition.make(Lit69, PairWithPosition.make("do binding must have syntax: (var [:: type] init [step])", LList.Empty, "std_syntax.scm", 0xc5005), "std_syntax.scm", 0xc4004);
        asyntaxrule7[6] = new SyntaxRule(syntaxpattern15, "\001\001\001\001", "\030\004", aobj28, 0);
        Lit35 = new SyntaxRules(aobj24, asyntaxrule7, 4);
        Object aobj29[] = new Object[2];
        aobj29[0] = Lit32;
        aobj29[1] = Lit64;
        SyntaxRule asyntaxrule8[] = new SyntaxRule[4];
        Object aobj30[] = new Object[1];
        aobj30[0] = Lit64;
        asyntaxrule8[0] = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\002\f\017\f\027\f\037\b", aobj30, 4), "\001\001\001\001", "\033", new Object[0], 0);
        Object aobj31[] = new Object[1];
        aobj31[0] = Lit64;
        asyntaxrule8[1] = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\002\f\017\f\027\b", aobj31, 3), "\001\001\001", "\003", new Object[0], 0);
        asyntaxrule8[2] = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3), "\001\001\001", "\023", new Object[0], 0);
        asyntaxrule8[3] = new SyntaxRule(new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2), "\001\001", "\003", new Object[0], 0);
        Lit33 = new SyntaxRules(aobj29, asyntaxrule8, 4);
        Object aobj32[] = new Object[1];
        SimpleSymbol simplesymbol11 = (SimpleSymbol)(new SimpleSymbol("let*")).readResolve();
        Lit30 = simplesymbol11;
        aobj32[0] = simplesymbol11;
        SyntaxRule asyntaxrule9[] = new SyntaxRule[5];
        SyntaxPattern syntaxpattern16 = new SyntaxPattern("\f\030\f\b\003", new Object[0], 1);
        Object aobj33[] = new Object[1];
        aobj33[0] = Lit70;
        asyntaxrule9[0] = new SyntaxRule(syntaxpattern16, "\0", "\021\030\004\t\020\002", aobj33, 0);
        SyntaxPattern syntaxpattern17 = new SyntaxPattern("\f\030\034\f\007\b\013", new Object[0], 2);
        Object aobj34[] = new Object[1];
        aobj34[0] = Lit70;
        asyntaxrule9[1] = new SyntaxRule(syntaxpattern17, "\001\0", "\021\030\004\021\b\003\n", aobj34, 0);
        SyntaxPattern syntaxpattern18 = new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3);
        Object aobj35[] = new Object[2];
        aobj35[0] = Lit70;
        aobj35[1] = Lit30;
        asyntaxrule9[2] = new SyntaxRule(syntaxpattern18, "\001\000\0", "\021\030\004\021\b\003\b\021\030\f\t\n\022", aobj35, 0);
        SyntaxPattern syntaxpattern19 = new SyntaxPattern("\f\030\f\007\013", new Object[0], 2);
        Object aobj36[] = new Object[1];
        aobj36[0] = PairWithPosition.make(Lit69, PairWithPosition.make("invalid bindings list in let*", LList.Empty, "std_syntax.scm", 0xa6007), "std_syntax.scm", 0xa5006);
        asyntaxrule9[3] = new SyntaxRule(syntaxpattern19, "\001\0", "\030\004", aobj36, 0);
        SyntaxPattern syntaxpattern20 = new SyntaxPattern("\f\030\003", new Object[0], 1);
        Object aobj37[] = new Object[1];
        aobj37[0] = PairWithPosition.make(Lit69, PairWithPosition.make("missing bindings list in let*", LList.Empty, "std_syntax.scm", 0xa9007), "std_syntax.scm", 0xa8006);
        asyntaxrule9[4] = new SyntaxRule(syntaxpattern20, "\0", "\030\004", aobj37, 0);
        Lit31 = new SyntaxRules(aobj32, asyntaxrule9, 3);
        Object aobj38[] = new Object[1];
        SimpleSymbol simplesymbol12 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
        Lit28 = simplesymbol12;
        aobj38[0] = simplesymbol12;
        SyntaxRule asyntaxrule10[] = new SyntaxRule[2];
        SyntaxPattern syntaxpattern21 = new SyntaxPattern("\f\030,\r\007\000\b\b\013", new Object[0], 2);
        Object aobj39[] = new Object[1];
        aobj39[0] = Lit70;
        asyntaxrule10[0] = new SyntaxRule(syntaxpattern21, "\003\0", "\021\030\004\031\b\005\003\n", aobj39, 1);
        SyntaxPattern syntaxpattern22 = new SyntaxPattern("\f\030\f\007,\r\017\b\b\b\023", new Object[0], 3);
        Object aobj40[] = new Object[3];
        aobj40[0] = Lit71;
        SimpleSymbol simplesymbol13 = (SimpleSymbol)(new SimpleSymbol("%let-lambda1")).readResolve();
        Lit22 = simplesymbol13;
        aobj40[1] = simplesymbol13;
        SimpleSymbol simplesymbol14 = (SimpleSymbol)(new SimpleSymbol("%let-init")).readResolve();
        Lit26 = simplesymbol14;
        aobj40[2] = simplesymbol14;
        asyntaxrule10[1] = new SyntaxRule(syntaxpattern22, "\001\003\0", "\251\021\030\004y\b\t\003\b\021\030\f\031\b\r\013\t\020\b\022\b\003\b\r\021\030\024\b\013", aobj40, 1);
        Lit29 = new SyntaxRules(aobj38, asyntaxrule10, 3);
        Object aobj41[] = new Object[2];
        aobj41[0] = Lit26;
        aobj41[1] = Lit64;
        SyntaxRule asyntaxrule11[] = new SyntaxRule[5];
        asyntaxrule11[0] = new SyntaxRule(new SyntaxPattern("\f\030,\f\007\f\017\b\b", new Object[0], 2), "\001\001", "\013", new Object[0], 0);
        Object aobj42[] = new Object[1];
        aobj42[0] = Lit64;
        asyntaxrule11[1] = new SyntaxRule(new SyntaxPattern("\f\030L\f\007\f\002\f\017\f\027\b\b", aobj42, 3), "\001\001\001", "\023", new Object[0], 0);
        asyntaxrule11[2] = new SyntaxRule(new SyntaxPattern("\f\030<\f\007\f\017\f\027\b\b", new Object[0], 3), "\001\001\001", "\023", new Object[0], 0);
        SyntaxPattern syntaxpattern23 = new SyntaxPattern("\f\030\034\f\007\b\b", new Object[0], 1);
        Object aobj43[] = new Object[1];
        aobj43[0] = PairWithPosition.make(Lit69, PairWithPosition.make("let binding with no value", LList.Empty, "std_syntax.scm", 0x87013), "std_syntax.scm", 0x87004);
        asyntaxrule11[3] = new SyntaxRule(syntaxpattern23, "\001", "\030\004", aobj43, 0);
        SyntaxPattern syntaxpattern24 = new SyntaxPattern("\f\030L\f\007\f\017\f\027\f\037\b\b", new Object[0], 4);
        Object aobj44[] = new Object[1];
        aobj44[0] = PairWithPosition.make(Lit69, PairWithPosition.make("let binding must have syntax: (var [type] init)", LList.Empty, "std_syntax.scm", 0x8a005), "std_syntax.scm", 0x89004);
        asyntaxrule11[4] = new SyntaxRule(syntaxpattern24, "\001\001\001\001", "\030\004", aobj44, 0);
        Lit27 = new SyntaxRules(aobj41, asyntaxrule11, 4);
        Object aobj45[] = new Object[1];
        SimpleSymbol simplesymbol15 = (SimpleSymbol)(new SimpleSymbol("%let-lambda2")).readResolve();
        Lit24 = simplesymbol15;
        aobj45[0] = simplesymbol15;
        SyntaxRule asyntaxrule12[] = new SyntaxRule[2];
        SyntaxPattern syntaxpattern25 = new SyntaxPattern("\f\030\034\f\007\013\f\027\f\037\b", new Object[0], 4);
        Object aobj46[] = new Object[1];
        aobj46[0] = Lit24;
        asyntaxrule12[0] = new SyntaxRule(syntaxpattern25, "\001\000\001\001", "\021\030\004\t\n\031\t\003\023\b\033", aobj46, 0);
        SyntaxPattern syntaxpattern26 = new SyntaxPattern("\f\030\f\b\f\007\f\017\b", new Object[0], 2);
        Object aobj47[] = new Object[1];
        aobj47[0] = Lit68;
        asyntaxrule12[1] = new SyntaxRule(syntaxpattern26, "\001\001", "\021\030\004\t\003\013", aobj47, 0);
        Lit25 = new SyntaxRules(aobj45, asyntaxrule12, 4);
        Object aobj48[] = new Object[2];
        aobj48[0] = Lit22;
        aobj48[1] = Lit64;
        SyntaxRule asyntaxrule13[] = new SyntaxRule[4];
        SyntaxPattern syntaxpattern27 = new SyntaxPattern("\f\030L<\f\007\f\017\f\027\b\033\f'\f/\b", new Object[0], 6);
        Object aobj49[] = new Object[1];
        aobj49[0] = Lit22;
        asyntaxrule13[0] = new SyntaxRule(syntaxpattern27, "\001\001\001\000\001\001", "\021\030\004\t\0321!\t\003\b\013#\b+", aobj49, 0);
        Object aobj50[] = new Object[1];
        aobj50[0] = Lit64;
        SyntaxPattern syntaxpattern28 = new SyntaxPattern("\f\030\\L\f\007\f\002\f\017\f\027\b\033\f'\f/\b", aobj50, 6);
        Object aobj51[] = new Object[1];
        aobj51[0] = Lit22;
        asyntaxrule13[1] = new SyntaxRule(syntaxpattern28, "\001\001\001\000\001\001", "\021\030\004\t\0321!\t\003\b\013#\b+", aobj51, 0);
        SyntaxPattern syntaxpattern29 = new SyntaxPattern("\f\030<,\f\007\f\017\b\023\f\037\f'\b", new Object[0], 5);
        Object aobj52[] = new Object[1];
        aobj52[0] = Lit22;
        asyntaxrule13[2] = new SyntaxRule(syntaxpattern29, "\001\001\000\001\001", "\021\030\004\t\022\031\t\003\033\b#", aobj52, 0);
        SyntaxPattern syntaxpattern30 = new SyntaxPattern("\f\030\f\b\f\007\f\017\b", new Object[0], 2);
        Object aobj53[] = new Object[1];
        aobj53[0] = Lit24;
        asyntaxrule13[3] = new SyntaxRule(syntaxpattern30, "\001\001", "\021\030\004\t\003\t\020\b\013", aobj53, 0);
        Lit23 = new SyntaxRules(aobj48, asyntaxrule13, 6);
        Object aobj54[] = new Object[3];
        aobj54[0] = Lit70;
        aobj54[1] = Lit73;
        aobj54[2] = Lit72;
        Lit21 = new SyntaxTemplate("\001\001\003", "\021\030\0041\b\021\030\f\b\013\b\021\030\024\021\030\f\021\030\f\b\t\003\b\025\023", aobj54, 1);
        Lit16 = (SimpleSymbol)(new SimpleSymbol("or")).readResolve();
        Object aobj55[] = new Object[4];
        aobj55[0] = Lit70;
        aobj55[1] = Lit73;
        aobj55[2] = Lit72;
        aobj55[3] = PairWithPosition.make(Lit73, LList.Empty, "std_syntax.scm", 0x5e01c);
        Lit15 = new SyntaxTemplate("\001\001\003", "\021\030\0041\b\021\030\f\b\013\b\021\030\024\021\030\f)\t\003\b\025\023\030\034", aobj55, 1);
        Lit10 = (SimpleSymbol)(new SimpleSymbol("and")).readResolve();
        Object aobj56[] = new Object[1];
        SimpleSymbol simplesymbol16 = (SimpleSymbol)(new SimpleSymbol("%case-match")).readResolve();
        Lit8 = simplesymbol16;
        aobj56[0] = simplesymbol16;
        SyntaxRule asyntaxrule14[] = new SyntaxRule[2];
        SyntaxPattern syntaxpattern31 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
        Object aobj57[] = new Object[2];
        aobj57[0] = Lit74;
        aobj57[1] = Lit66;
        asyntaxrule14[0] = new SyntaxRule(syntaxpattern31, "\001\001", "\021\030\004\t\003\b\021\030\f\b\013", aobj57, 0);
        SyntaxPattern syntaxpattern32 = new SyntaxPattern("\f\030\f\007\f\017\r\027\020\b\b", new Object[0], 3);
        Object aobj58[] = new Object[4];
        aobj58[0] = Lit16;
        aobj58[1] = Lit74;
        aobj58[2] = Lit66;
        aobj58[3] = Lit8;
        asyntaxrule14[1] = new SyntaxRule(syntaxpattern32, "\001\001\003", "\021\030\004Y\021\030\f\t\003\b\021\030\024\b\013\b\021\030\034\t\003\b\025\023", aobj58, 1);
        Lit9 = new SyntaxRules(aobj56, asyntaxrule14, 3);
        Object aobj59[] = new Object[2];
        SimpleSymbol simplesymbol17 = (SimpleSymbol)(new SimpleSymbol("%case")).readResolve();
        Lit6 = simplesymbol17;
        aobj59[0] = simplesymbol17;
        aobj59[1] = Lit75;
        SyntaxRule asyntaxrule15[] = new SyntaxRule[4];
        Object aobj60[] = new Object[1];
        aobj60[0] = Lit75;
        SyntaxPattern syntaxpattern33 = new SyntaxPattern("\f\030\f\007<\f\002\r\017\b\b\b\b", aobj60, 2);
        Object aobj61[] = new Object[1];
        aobj61[0] = Lit56;
        asyntaxrule15[0] = new SyntaxRule(syntaxpattern33, "\001\003", "\021\030\004\b\r\013", aobj61, 1);
        Object aobj62[] = new Object[1];
        aobj62[0] = Lit75;
        SyntaxPattern syntaxpattern34 = new SyntaxPattern("\f\030\f\007<\f\002\r\017\b\b\b\023", aobj62, 3);
        Object aobj63[] = new Object[1];
        aobj63[0] = PairWithPosition.make(Lit69, PairWithPosition.make("junk following else (in case)", LList.Empty, "std_syntax.scm", 0x3b00a), "std_syntax.scm", 0x3a009);
        asyntaxrule15[1] = new SyntaxRule(syntaxpattern34, "\001\003\0", "\030\004", aobj63, 0);
        SyntaxPattern syntaxpattern35 = new SyntaxPattern("\f\030\f\007\\,\r\017\b\b\b\r\027\020\b\b\b", new Object[0], 3);
        Object aobj64[] = new Object[3];
        aobj64[0] = Lit72;
        aobj64[1] = Lit8;
        aobj64[2] = Lit56;
        asyntaxrule15[2] = new SyntaxRule(syntaxpattern35, "\001\003\003", "\021\030\004A\021\030\f\t\003\b\r\013\b\021\030\024\b\025\023", aobj64, 1);
        SyntaxPattern syntaxpattern36 = new SyntaxPattern("\f\030\f\007\\,\r\017\b\b\b\r\027\020\b\b\f\037\r' \b\b", new Object[0], 5);
        Object aobj65[] = new Object[4];
        aobj65[0] = Lit72;
        aobj65[1] = Lit8;
        aobj65[2] = Lit56;
        aobj65[3] = Lit6;
        asyntaxrule15[3] = new SyntaxRule(syntaxpattern36, "\001\003\003\001\003", "\021\030\004A\021\030\f\t\003\b\r\0131\021\030\024\b\025\023\b\021\030\034\t\003\t\033\b%#", aobj65, 1);
        Lit7 = new SyntaxRules(aobj59, asyntaxrule15, 5);
        Object aobj66[] = new Object[1];
        SimpleSymbol simplesymbol18 = (SimpleSymbol)(new SimpleSymbol("case")).readResolve();
        Lit4 = simplesymbol18;
        aobj66[0] = simplesymbol18;
        SyntaxRule asyntaxrule16[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern37 = new SyntaxPattern("\f\030\f\007\r\017\b\b\b", new Object[0], 2);
        Object aobj67[] = new Object[3];
        aobj67[0] = Lit70;
        aobj67[1] = (SimpleSymbol)(new SimpleSymbol("tmp")).readResolve();
        aobj67[2] = Lit6;
        asyntaxrule16[0] = new SyntaxRule(syntaxpattern37, "\001\003", "\021\030\0041\b\021\030\f\b\003\b\021\030\024\021\030\f\b\r\013", aobj67, 1);
        Lit5 = new SyntaxRules(aobj66, asyntaxrule16, 2);
        Object aobj68[] = new Object[3];
        SimpleSymbol simplesymbol19 = (SimpleSymbol)(new SimpleSymbol("cond")).readResolve();
        Lit2 = simplesymbol19;
        aobj68[0] = simplesymbol19;
        aobj68[1] = Lit75;
        aobj68[2] = Lit76;
        SyntaxRule asyntaxrule17[] = new SyntaxRule[8];
        Object aobj69[] = new Object[1];
        aobj69[0] = Lit75;
        SyntaxPattern syntaxpattern38 = new SyntaxPattern("\f\030L\f\002\f\007\r\017\b\b\b\b", aobj69, 2);
        Object aobj70[] = new Object[1];
        aobj70[0] = Lit56;
        asyntaxrule17[0] = new SyntaxRule(syntaxpattern38, "\001\003", "\021\030\004\t\003\b\r\013", aobj70, 1);
        Object aobj71[] = new Object[1];
        aobj71[0] = Lit75;
        SyntaxPattern syntaxpattern39 = new SyntaxPattern("\f\030L\f\002\f\007\r\017\b\b\b\r\027\020\b\b", aobj71, 3);
        Object aobj72[] = new Object[1];
        aobj72[0] = PairWithPosition.make(Lit69, PairWithPosition.make("else clause must be last clause of cond", LList.Empty, "std_syntax.scm", 0x15013), "std_syntax.scm", 0x15004);
        asyntaxrule17[1] = new SyntaxRule(syntaxpattern39, "\001\003\003", "\030\004", aobj72, 0);
        Object aobj73[] = new Object[1];
        aobj73[0] = Lit76;
        SyntaxPattern syntaxpattern40 = new SyntaxPattern("\f\030<\f\007\f\002\f\017\b\b", aobj73, 2);
        Object aobj74[] = new Object[4];
        aobj74[0] = Lit70;
        aobj74[1] = Lit77;
        aobj74[2] = Lit72;
        aobj74[3] = PairWithPosition.make(Lit77, LList.Empty, "std_syntax.scm", 0x19017);
        asyntaxrule17[2] = new SyntaxRule(syntaxpattern40, "\001\001", "\021\030\0041\b\021\030\f\b\003\b\021\030\024\021\030\f\b\t\013\030\034", aobj74, 0);
        Object aobj75[] = new Object[1];
        aobj75[0] = Lit76;
        SyntaxPattern syntaxpattern41 = new SyntaxPattern("\f\030<\f\007\f\002\f\017\b\f\027\r\037\030\b\b", aobj75, 4);
        Object aobj76[] = new Object[5];
        aobj76[0] = Lit70;
        aobj76[1] = Lit77;
        aobj76[2] = Lit72;
        aobj76[3] = PairWithPosition.make(Lit77, LList.Empty, "std_syntax.scm", 0x1e012);
        aobj76[4] = Lit2;
        asyntaxrule17[3] = new SyntaxRule(syntaxpattern41, "\001\001\001\003", "\021\030\0041\b\021\030\f\b\003\b\021\030\024\021\030\f!\t\013\030\034\b\021\030$\t\023\b\035\033", aobj76, 1);
        asyntaxrule17[4] = new SyntaxRule(new SyntaxPattern("\f\030\034\f\007\b\b", new Object[0], 1), "\001", "\003", new Object[0], 0);
        SyntaxPattern syntaxpattern42 = new SyntaxPattern("\f\030\034\f\007\b\f\017\r\027\020\b\b", new Object[0], 3);
        Object aobj77[] = new Object[2];
        aobj77[0] = Lit16;
        aobj77[1] = Lit2;
        asyntaxrule17[5] = new SyntaxRule(syntaxpattern42, "\001\001\003", "\021\030\004\t\003\b\021\030\f\t\013\b\025\023", aobj77, 1);
        SyntaxPattern syntaxpattern43 = new SyntaxPattern("\f\030L\f\007\f\017\r\027\020\b\b\b", new Object[0], 3);
        Object aobj78[] = new Object[2];
        aobj78[0] = Lit72;
        aobj78[1] = Lit56;
        asyntaxrule17[6] = new SyntaxRule(syntaxpattern43, "\001\001\003", "\021\030\004\t\003\b\021\030\f\t\013\b\025\023", aobj78, 1);
        SyntaxPattern syntaxpattern44 = new SyntaxPattern("\f\030L\f\007\f\017\r\027\020\b\b\f\037\r' \b\b", new Object[0], 5);
        Object aobj79[] = new Object[3];
        aobj79[0] = Lit72;
        aobj79[1] = Lit56;
        aobj79[2] = Lit2;
        asyntaxrule17[7] = new SyntaxRule(syntaxpattern44, "\001\001\003\001\003", "\021\030\004\t\003A\021\030\f\t\013\b\025\023\b\021\030\024\t\033\b%#", aobj79, 1);
        Lit3 = new SyntaxRules(aobj68, asyntaxrule17, 5);
        $instance = new std_syntax();
        cond = Macro.make(Lit2, Lit3, $instance);
        _fldcase = Macro.make(Lit4, Lit5, $instance);
        $Prvt$$Pccase = Macro.make(Lit6, Lit7, $instance);
        $Prvt$$Pccase$Mnmatch = Macro.make(Lit8, Lit9, $instance);
        SimpleSymbol simplesymbol20 = Lit10;
        std_syntax std_syntax1 = $instance;
        and = Macro.make(simplesymbol20, new ModuleMethod(std_syntax1, 1, null, 4097), $instance);
        or = Macro.make(Lit16, new ModuleMethod(std_syntax1, 2, null, 4097), $instance);
        $Prvt$$Pclet$Mnlambda1 = Macro.make(Lit22, Lit23, $instance);
        $Prvt$$Pclet$Mnlambda2 = Macro.make(Lit24, Lit25, $instance);
        $Prvt$$Pclet$Mninit = Macro.make(Lit26, Lit27, $instance);
        let = Macro.make(Lit28, Lit29, $instance);
        let$St = Macro.make(Lit30, Lit31, $instance);
        $Prvt$$Pcdo$Mnstep = Macro.make(Lit32, Lit33, $instance);
        $Prvt$$Pcdo$Mninit = Macro.make(Lit34, Lit35, $instance);
        $Prvt$$Pcdo$Mnlambda1 = Macro.make(Lit36, Lit37, $instance);
        $Prvt$$Pcdo$Mnlambda2 = Macro.make(Lit38, Lit39, $instance);
        _flddo = Macro.make(Lit40, Lit41, $instance);
        delay = Macro.make(Lit42, Lit43, $instance);
        define$Mnprocedure = Macro.make(Lit44, Lit45, $instance);
        syntax$Mnobject$Mn$Grdatum = new ModuleMethod(std_syntax1, 3, Lit46, 4097);
        datum$Mn$Grsyntax$Mnobject = new ModuleMethod(std_syntax1, 4, Lit47, 8194);
        generate$Mntemporaries = new ModuleMethod(std_syntax1, 5, Lit48, 4097);
        identifier$Qu = new ModuleMethod(std_syntax1, 6, Lit49, 4097);
        free$Mnidentifier$Eq$Qu = new ModuleMethod(std_syntax1, 7, Lit50, 8194);
        syntax$Mnsource = new ModuleMethod(std_syntax1, 8, Lit51, 4097);
        syntax$Mnline = new ModuleMethod(std_syntax1, 9, Lit52, 4097);
        syntax$Mncolumn = new ModuleMethod(std_syntax1, 10, Lit53, 4097);
        SimpleSymbol simplesymbol21 = Lit54;
        ModuleMethod modulemethod = new ModuleMethod(std_syntax1, 11, null, 4097);
        modulemethod.setProperty("source-location", "std_syntax.scm:298");
        begin$Mnfor$Mnsyntax = Macro.make(simplesymbol21, modulemethod, $instance);
        define$Mnfor$Mnsyntax = Macro.make(Lit59, Lit60, $instance);
        with$Mnsyntax = Macro.make(Lit61, Lit62, $instance);
        $instance.run();
    }
}
