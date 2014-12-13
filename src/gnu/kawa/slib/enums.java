// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.Pattern;
import kawa.lang.Quote;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.prim_syntax;
import kawa.lib.std_syntax;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.syntax_case;

public class enums extends ModuleBody
{

    public static final Macro $Prvt$$Pcdefine$Mnenum;
    public static final enums $instance;
    static final PairWithPosition Lit0;
    static final PairWithPosition Lit1;
    static final PairWithPosition Lit10;
    static final SimpleSymbol Lit11;
    static final SyntaxPattern Lit12 = new SyntaxPattern("\f\007\f\002\f\017,\r\027\020\b\b\f\037\f'\r/(\b\b", new Object[] {
        "findkeywords"
    }, 6);
    static final SyntaxTemplate Lit13 = new SyntaxTemplate("\001\001\003\001\001\003", "\033", new Object[0], 0);
    static final SyntaxTemplate Lit14;
    static final SyntaxPattern Lit15 = new SyntaxPattern("\f\007\f\002\f\017,\r\027\020\b\b\r\037\030\b\b", new Object[] {
        "findkeywords"
    }, 4);
    static final SyntaxTemplate Lit16;
    static final SyntaxPattern Lit17 = new SyntaxPattern("\f\007\b", new Object[0], 1);
    static final SyntaxPattern Lit18 = new SyntaxPattern("\f\007\f\017\b", new Object[0], 2);
    static final SyntaxPattern Lit19 = new SyntaxPattern("\f\007\f\017\r\027\020\b\b", new Object[0], 3);
    static final PairWithPosition Lit2;
    static final SyntaxTemplate Lit20;
    static final SimpleSymbol Lit21;
    static final SyntaxPattern Lit22 = new SyntaxPattern("\f\007\f\017\f\027,\r\037\030\b\b\r' \b\b", new Object[0], 5);
    static final SyntaxTemplate Lit23 = new SyntaxTemplate("\001\001\001\003\003", "\013", new Object[0], 0);
    static final SimpleSymbol Lit24 = (SimpleSymbol)(new SimpleSymbol("[]")).readResolve();
    static final SyntaxTemplate Lit25 = new SyntaxTemplate("\001\001\001\003\003", "\b\035\033", new Object[0], 1);
    static final SyntaxTemplate Lit26 = new SyntaxTemplate("\001\001\001\003\003", "\023", new Object[0], 0);
    static final SyntaxTemplate Lit27 = new SyntaxTemplate("\001\001\001\003\003", "\b%#", new Object[0], 1);
    static final SyntaxTemplate Lit28;
    static final SyntaxTemplate Lit29;
    static final PairWithPosition Lit3;
    static final SyntaxTemplate Lit30;
    static final SyntaxTemplate Lit31;
    static final SyntaxTemplate Lit32;
    static final SyntaxTemplate Lit33;
    static final SyntaxTemplate Lit34;
    static final SyntaxTemplate Lit35;
    static final SyntaxTemplate Lit36;
    static final SyntaxTemplate Lit37;
    static final SyntaxTemplate Lit38 = new SyntaxTemplate("\001\001\001\003\003", "\020", new Object[0], 0);
    static final SyntaxTemplate Lit39 = new SyntaxTemplate("\001\001\001\003\003", "\020", new Object[0], 0);
    static final PairWithPosition Lit4;
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final SimpleSymbol Lit42;
    static final SimpleSymbol Lit43;
    static final SimpleSymbol Lit44;
    static final SimpleSymbol Lit45;
    static final Keyword Lit46;
    static final SimpleSymbol Lit47;
    static final Keyword Lit48;
    static final SimpleSymbol Lit49;
    static final PairWithPosition Lit5;
    static final SimpleSymbol Lit50;
    static final SimpleSymbol Lit51;
    static final SimpleSymbol Lit52;
    static final SimpleSymbol Lit53;
    static final PairWithPosition Lit6;
    static final PairWithPosition Lit7;
    static final PairWithPosition Lit8;
    static final PairWithPosition Lit9;
    public static final Macro define$Mnenum;

    public enums()
    {
        ModuleInfo.register(this);
    }

    static Object lambda1(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(6, null);
        if (Lit12.match(obj, aobj, 0))
        {
            TemplateScope templatescope2 = TemplateScope.make();
            if (std_syntax.isIdentifier(Lit13.execute(aobj, templatescope2)))
            {
                TemplateScope templatescope3 = TemplateScope.make();
                return Lit14.execute(aobj, templatescope3);
            }
        }
        if (Lit15.match(obj, aobj, 0))
        {
            TemplateScope templatescope1 = TemplateScope.make();
            return Lit16.execute(aobj, templatescope1);
        }
        if (Lit17.match(obj, aobj, 0))
        {
            Object aobj2[];
            if ("no enum type name given" instanceof Object[])
            {
                aobj2 = (Object[])"no enum type name given";
            } else
            {
                aobj2 = (new Object[] {
                    "no enum type name given"
                });
            }
            return prim_syntax.syntaxError(obj, aobj2);
        }
        if (Lit18.match(obj, aobj, 0))
        {
            Object aobj1[];
            if ("no enum constants given" instanceof Object[])
            {
                aobj1 = (Object[])"no enum constants given";
            } else
            {
                aobj1 = (new Object[] {
                    "no enum constants given"
                });
            }
            return prim_syntax.syntaxError(obj, aobj1);
        }
        if (Lit19.match(obj, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            return Lit20.execute(aobj, templatescope);
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    static Object lambda2(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(5, null);
        if (Lit22.match(obj, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            Object obj1 = Lit23.execute(aobj, templatescope);
            Symbol symbol;
            Object aobj1[];
            SimpleSymbol simplesymbol;
            TemplateScope templatescope1;
            Object obj2;
            LList llist;
            LList llist1;
            PairWithPosition pairwithposition;
            Pair pair;
            TemplateScope templatescope2;
            Object obj3;
            LList llist2;
            TemplateScope templatescope3;
            Object obj4;
            LList llist3;
            TemplateScope templatescope4;
            Object aobj2[];
            Object aobj3[];
            Object obj5;
            Object aobj4[];
            Object obj6;
            Object aobj5[];
            Object aobj6[];
            Object aobj7[];
            Object obj7;
            Object aobj8[];
            Object aobj9[];
            Object aobj10[];
            Object obj8;
            Object obj9;
            Object aobj11[];
            Pair pair1;
            Object aobj12[];
            Object aobj13[];
            try
            {
                symbol = (Symbol)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "t-name", -2, obj1);
            }
            aobj1 = new Object[2];
            aobj1[0] = symbol;
            aobj1[1] = Lit24;
            simplesymbol = symbolAppend$V(aobj1);
            templatescope1 = TemplateScope.make();
            obj2 = Lit25.execute(aobj, templatescope1);
            try
            {
                llist = (LList)obj2;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "e-names", -2, obj2);
            }
            lists.length(llist);
            llist1 = mapNames(symbol, llist, 0);
            pairwithposition = makeInit();
            pair = makeValues(simplesymbol, llist);
            templatescope2 = TemplateScope.make();
            obj3 = Lit26.execute(aobj, templatescope2);
            try
            {
                llist2 = (LList)obj3;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "opts", -2, obj3);
            }
            templatescope3 = TemplateScope.make();
            obj4 = Lit27.execute(aobj, templatescope3);
            try
            {
                llist3 = (LList)obj4;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "other-defs", -2, obj4);
            }
            templatescope4 = TemplateScope.make();
            aobj2 = new Object[2];
            aobj2[0] = Lit28.execute(aobj, templatescope4);
            aobj3 = new Object[2];
            aobj3[0] = std_syntax.datum$To$SyntaxObject(obj, symbol);
            obj5 = Lit29.execute(aobj, templatescope4);
            aobj4 = new Object[2];
            aobj4[0] = Lit30.execute(aobj, templatescope4);
            obj6 = Lit31.execute(aobj, templatescope4);
            aobj5 = new Object[2];
            aobj5[0] = std_syntax.datum$To$SyntaxObject(obj, llist2);
            aobj6 = new Object[2];
            aobj6[0] = std_syntax.datum$To$SyntaxObject(obj, pairwithposition);
            aobj7 = new Object[2];
            aobj7[0] = std_syntax.datum$To$SyntaxObject(obj, pair);
            obj7 = Lit32.execute(aobj, templatescope4);
            aobj8 = new Object[2];
            aobj8[0] = Lit33.execute(aobj, templatescope4);
            aobj9 = new Object[2];
            aobj9[0] = std_syntax.datum$To$SyntaxObject(obj, symbol);
            aobj10 = new Object[2];
            aobj10[0] = Lit34.execute(aobj, templatescope4);
            obj8 = Lit35.execute(aobj, templatescope4);
            obj9 = Lit36.execute(aobj, templatescope4);
            aobj11 = new Object[2];
            aobj11[0] = std_syntax.datum$To$SyntaxObject(obj, symbol);
            aobj11[1] = Lit37.execute(aobj, templatescope4);
            aobj10[1] = Pair.make(obj8, Pair.make(Pair.make(obj9, Quote.consX$V(aobj11)), Lit38.execute(aobj, templatescope4)));
            aobj9[1] = Quote.append$V(aobj10);
            aobj8[1] = Quote.consX$V(aobj9);
            pair1 = Pair.make(obj7, Quote.append$V(aobj8));
            aobj12 = new Object[2];
            aobj12[0] = std_syntax.datum$To$SyntaxObject(obj, llist1);
            aobj13 = new Object[2];
            aobj13[0] = std_syntax.datum$To$SyntaxObject(obj, llist3);
            aobj13[1] = Lit39.execute(aobj, templatescope4);
            aobj12[1] = Quote.append$V(aobj13);
            aobj7[1] = Pair.make(pair1, Quote.append$V(aobj12));
            aobj6[1] = Quote.consX$V(aobj7);
            aobj5[1] = Quote.consX$V(aobj6);
            aobj4[1] = Pair.make(obj6, Quote.append$V(aobj5));
            aobj3[1] = Pair.make(obj5, Quote.append$V(aobj4));
            aobj2[1] = Quote.consX$V(aobj3);
            return Quote.append$V(aobj2);
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    static Object makeFieldDesc(Symbol symbol, Symbol symbol1, int i)
    {
        Object aobj[] = new Object[2];
        aobj[0] = symbol1;
        Object aobj1[] = new Object[2];
        aobj1[0] = Lit0;
        Object aobj2[] = new Object[2];
        aobj2[0] = symbol;
        Object aobj3[] = new Object[2];
        aobj3[0] = Lit1;
        PairWithPosition pairwithposition = Lit2;
        Object aobj4[] = new Object[2];
        aobj4[0] = Lit3;
        PairWithPosition pairwithposition1 = Lit4;
        Object aobj5[] = new Object[2];
        aobj5[0] = Lit5;
        Object aobj6[] = new Object[2];
        aobj6[0] = symbol;
        Object aobj7[] = new Object[2];
        aobj7[0] = misc.symbol$To$String(symbol1);
        Object aobj8[] = new Object[2];
        aobj8[0] = Integer.valueOf(i);
        aobj8[1] = LList.Empty;
        aobj7[1] = Quote.consX$V(aobj8);
        aobj6[1] = Quote.consX$V(aobj7);
        aobj5[1] = Pair.make(Quote.consX$V(aobj6), LList.Empty);
        aobj4[1] = Pair.make(pairwithposition1, Quote.append$V(aobj5));
        aobj3[1] = Pair.make(pairwithposition, Quote.append$V(aobj4));
        aobj2[1] = Quote.append$V(aobj3);
        aobj1[1] = Quote.consX$V(aobj2);
        aobj[1] = Quote.append$V(aobj1);
        return Quote.consX$V(aobj);
    }

    static PairWithPosition makeInit()
    {
        return Lit6;
    }

    static Pair makeValues(Symbol symbol, LList llist)
    {
        PairWithPosition pairwithposition = Lit7;
        Object aobj[] = new Object[2];
        aobj[0] = Lit8;
        Object aobj1[] = new Object[2];
        aobj1[0] = symbol;
        Object aobj2[] = new Object[2];
        aobj2[0] = Lit9;
        PairWithPosition pairwithposition1 = Lit10;
        Object aobj3[] = new Object[2];
        aobj3[0] = symbol;
        Object aobj4[] = new Object[2];
        aobj4[0] = llist;
        aobj4[1] = LList.Empty;
        aobj3[1] = Quote.append$V(aobj4);
        aobj2[1] = Pair.make(pairwithposition1, Pair.make(Quote.consX$V(aobj3), LList.Empty));
        aobj1[1] = Quote.append$V(aobj2);
        aobj[1] = Quote.consX$V(aobj1);
        return Pair.make(pairwithposition, Quote.append$V(aobj));
    }

    static LList mapNames(Symbol symbol, LList llist, int i)
    {
        if (lists.isNull(llist))
        {
            return LList.Empty;
        }
        Object obj = lists.car.apply1(llist);
        Symbol symbol1;
        Object obj1;
        Object obj2;
        LList llist1;
        try
        {
            symbol1 = (Symbol)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "make-field-desc", 1, obj);
        }
        obj1 = makeFieldDesc(symbol, symbol1, i);
        obj2 = lists.cdr.apply1(llist);
        try
        {
            llist1 = (LList)obj2;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "map-names", 1, obj2);
        }
        return lists.cons(obj1, mapNames(symbol, llist1, i + 1));
    }

    static SimpleSymbol symbolAppend$V(Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        gnu.kawa.functions.Apply apply = Scheme.apply;
        ModuleMethod modulemethod = strings.string$Mnappend;
        Object obj = LList.Empty;
        Object obj1 = llist;
        do
        {
            if (obj1 == LList.Empty)
            {
                Object obj4 = apply.apply2(modulemethod, LList.reverseInPlace(obj));
                ClassCastException classcastexception;
                Pair pair;
                Object obj2;
                Object obj3;
                ClassCastException classcastexception1;
                Symbol symbol;
                CharSequence charsequence;
                try
                {
                    charsequence = (CharSequence)obj4;
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "string->symbol", 1, obj4);
                }
                return misc.string$To$Symbol(charsequence);
            }
            try
            {
                pair = (Pair)obj1;
            }
            // Misplaced declaration of an exception variable
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "arg0", -2, obj1);
            }
            obj2 = pair.getCdr();
            obj3 = pair.getCar();
            try
            {
                symbol = (Symbol)obj3;
            }
            // Misplaced declaration of an exception variable
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "symbol->string", 1, obj3);
            }
            obj = Pair.make(misc.symbol$To$String(symbol), obj);
            obj1 = obj2;
        } while (true);
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply1(modulemethod, obj);

        case 1: // '\001'
            return lambda1(obj);

        case 2: // '\002'
            return lambda2(obj);
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match1(modulemethod, obj, callcontext);

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
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit53 = (SimpleSymbol)(new SimpleSymbol("final")).readResolve();
        Lit52 = (SimpleSymbol)(new SimpleSymbol("enum")).readResolve();
        Lit51 = (SimpleSymbol)(new SimpleSymbol("num")).readResolve();
        Lit50 = (SimpleSymbol)(new SimpleSymbol("str")).readResolve();
        Lit49 = (SimpleSymbol)(new SimpleSymbol("*init*")).readResolve();
        Lit48 = Keyword.make("access");
        Lit47 = (SimpleSymbol)(new SimpleSymbol("String")).readResolve();
        Lit46 = Keyword.make("allocation");
        Lit45 = (SimpleSymbol)(new SimpleSymbol("static")).readResolve();
        Lit44 = (SimpleSymbol)(new SimpleSymbol("java.lang.Enum")).readResolve();
        Lit43 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
        Lit42 = (SimpleSymbol)(new SimpleSymbol("::")).readResolve();
        Lit41 = (SimpleSymbol)(new SimpleSymbol("s")).readResolve();
        Lit40 = (SimpleSymbol)(new SimpleSymbol("valueOf")).readResolve();
        Object aobj[] = new Object[1];
        aobj[0] = PairWithPosition.make(Lit41, LList.Empty, "enums.scm", 0x47042);
        Lit37 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", aobj, 0);
        Object aobj1[] = new Object[1];
        aobj1[0] = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("$lookup$")).readResolve(), Pair.make(Lit44, Pair.make(Pair.make((SimpleSymbol)(new SimpleSymbol("quasiquote")).readResolve(), Pair.make(Lit40, LList.Empty)), LList.Empty)), "enums.scm", 0x47007);
        Lit36 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", aobj1, 0);
        Object aobj2[] = new Object[1];
        aobj2[0] = PairWithPosition.make(Lit43, PairWithPosition.make(Lit45, LList.Empty, "enums.scm", 0x46013), "enums.scm", 0x46013);
        Lit35 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", aobj2, 0);
        Object aobj3[] = new Object[1];
        aobj3[0] = PairWithPosition.make(Lit46, LList.Empty, "enums.scm", 0x46006);
        Lit34 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", aobj3, 0);
        Object aobj4[] = new Object[1];
        aobj4[0] = PairWithPosition.make(Lit42, LList.Empty, "enums.scm", 0x45019);
        Lit33 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", aobj4, 0);
        Object aobj5[] = new Object[1];
        aobj5[0] = PairWithPosition.make(Lit40, PairWithPosition.make(Lit41, PairWithPosition.make(Lit42, PairWithPosition.make(Lit47, LList.Empty, "enums.scm", 0x45012), "enums.scm", 0x45010), "enums.scm", 0x4500f), "enums.scm", 0x45006);
        Lit32 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", aobj5, 0);
        Object aobj6[] = new Object[1];
        aobj6[0] = PairWithPosition.make(Lit43, PairWithPosition.make(PairWithPosition.make(Lit52, PairWithPosition.make(Lit53, LList.Empty, "enums.scm", 0x4102c), "enums.scm", 0x41026), LList.Empty, "enums.scm", 0x41026), "enums.scm", 0x41026);
        Lit31 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", aobj6, 0);
        Object aobj7[] = new Object[1];
        aobj7[0] = PairWithPosition.make(Lit48, LList.Empty, "enums.scm", 0x4101d);
        Lit30 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", aobj7, 0);
        Object aobj8[] = new Object[1];
        aobj8[0] = PairWithPosition.make(Lit44, LList.Empty, "enums.scm", 0x4100c);
        Lit29 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", aobj8, 0);
        Object aobj9[] = new Object[1];
        aobj9[0] = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("define-simple-class")).readResolve(), LList.Empty, "enums.scm", 0x4000a);
        Lit28 = new SyntaxTemplate("\001\001\001\003\003", "\030\004", aobj9, 0);
        Lit21 = (SimpleSymbol)(new SimpleSymbol("%define-enum")).readResolve();
        Object aobj10[] = new Object[2];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("define-enum")).readResolve();
        Lit11 = simplesymbol;
        aobj10[0] = simplesymbol;
        aobj10[1] = "findkeywords";
        Lit20 = new SyntaxTemplate("\001\001\003", "\021\030\004\021\030\f\t\013\t\020\b\025\023", aobj10, 1);
        Object aobj11[] = new Object[1];
        aobj11[0] = Lit21;
        Lit16 = new SyntaxTemplate("\001\001\003\003", "\021\030\004\t\013\031\b\025\023\b\035\033", aobj11, 1);
        Object aobj12[] = new Object[2];
        aobj12[0] = Lit11;
        aobj12[1] = "findkeywords";
        Lit14 = new SyntaxTemplate("\001\001\003\001\001\003", "\021\030\004\021\030\f\t\0139\t\033\t#\b\025\023\b-+", aobj12, 1);
        Lit10 = PairWithPosition.make(Lit43, PairWithPosition.make(Lit45, LList.Empty, "enums.scm", 0x1f025), "enums.scm", 0x1f025);
        Lit9 = PairWithPosition.make(Lit46, LList.Empty, "enums.scm", 0x1f018);
        Lit8 = PairWithPosition.make(Lit42, LList.Empty, "enums.scm", 0x1f00e);
        Lit7 = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("values")).readResolve(), LList.Empty, "enums.scm", 0x1f005);
        Lit6 = PairWithPosition.make(PairWithPosition.make(Lit49, PairWithPosition.make(PairWithPosition.make(Lit50, PairWithPosition.make(Lit42, PairWithPosition.make(Lit47, LList.Empty, "enums.scm", 0x16015), "enums.scm", 0x16012), "enums.scm", 0x1600d), PairWithPosition.make(PairWithPosition.make(Lit51, PairWithPosition.make(Lit42, PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("int")).readResolve(), LList.Empty, "enums.scm", 0x16025), "enums.scm", 0x16022), "enums.scm", 0x1601d), LList.Empty, "enums.scm", 0x1601d), "enums.scm", 0x1600d), "enums.scm", 0x16005), PairWithPosition.make(Lit48, PairWithPosition.make(PairWithPosition.make(Lit43, PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("private")).readResolve(), LList.Empty, "enums.scm", 0x1700e), "enums.scm", 0x1700e), PairWithPosition.make(PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("invoke-special")).readResolve(), PairWithPosition.make(Lit44, PairWithPosition.make(PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("this")).readResolve(), LList.Empty, "enums.scm", 0x18024), PairWithPosition.make(PairWithPosition.make(Lit43, PairWithPosition.make(Lit49, LList.Empty, "enums.scm", 0x1802c), "enums.scm", 0x1802c), PairWithPosition.make(Lit50, PairWithPosition.make(Lit51, LList.Empty, "enums.scm", 0x18037), "enums.scm", 0x18033), "enums.scm", 0x1802b), "enums.scm", 0x18024), "enums.scm", 0x18015), "enums.scm", 0x18005), LList.Empty, "enums.scm", 0x18005), "enums.scm", 0x1700d), "enums.scm", 0x17005), "enums.scm", 0x16004);
        Lit5 = PairWithPosition.make(Keyword.make("init"), LList.Empty, "enums.scm", 0x1200d);
        Lit4 = PairWithPosition.make(Lit43, PairWithPosition.make(PairWithPosition.make(Lit52, PairWithPosition.make(Lit53, LList.Empty, "enums.scm", 0x11030), "enums.scm", 0x1102a), LList.Empty, "enums.scm", 0x1102a), "enums.scm", 0x1102a);
        Lit3 = PairWithPosition.make(Lit48, LList.Empty, "enums.scm", 0x11021);
        Lit2 = PairWithPosition.make(Lit43, PairWithPosition.make(Lit45, LList.Empty, "enums.scm", 0x1101a), "enums.scm", 0x1101a);
        Lit1 = PairWithPosition.make(Lit46, LList.Empty, "enums.scm", 0x1100d);
        Lit0 = PairWithPosition.make(Lit42, LList.Empty, "enums.scm", 0x1000d);
        $instance = new enums();
        SimpleSymbol simplesymbol1 = Lit11;
        enums enums1 = $instance;
        define$Mnenum = Macro.make(simplesymbol1, new ModuleMethod(enums1, 1, null, 4097), $instance);
        $Prvt$$Pcdefine$Mnenum = Macro.make(Lit21, new ModuleMethod(enums1, 2, null, 4097), $instance);
        $instance.run();
    }
}
