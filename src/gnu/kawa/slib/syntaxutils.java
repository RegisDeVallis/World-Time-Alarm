// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.SetExp;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Convert;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.reflect.SlotGet;
import gnu.lists.EofClass;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import gnu.text.SourceMessages;
import kawa.lang.Macro;
import kawa.lang.Quote;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.Translator;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;

public class syntaxutils extends ModuleBody
{
    public class frame extends ModuleBody
    {

        LList pack;

        public frame()
        {
        }
    }

    public class frame0 extends ModuleBody
    {

        LList pack;

        public frame0()
        {
        }
    }

    public class frame1 extends ModuleBody
    {

        LList pack;

        public frame1()
        {
        }
    }


    public static final Macro $Prvt$$Ex;
    public static final Macro $Prvt$typecase$Pc;
    public static final syntaxutils $instance;
    static final Keyword Lit0 = Keyword.make("env");
    static final PairWithPosition Lit1;
    static final PairWithPosition Lit10;
    static final PairWithPosition Lit11;
    static final PairWithPosition Lit12;
    static final SimpleSymbol Lit13;
    static final SyntaxRules Lit14;
    static final SimpleSymbol Lit15;
    static final SyntaxRules Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final Keyword Lit2 = Keyword.make("lang");
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final PairWithPosition Lit3;
    static final PairWithPosition Lit4;
    static final PairWithPosition Lit5;
    static final PairWithPosition Lit6;
    static final IntNum Lit7 = IntNum.make(0);
    static final IntNum Lit8 = IntNum.make(1);
    static final PairWithPosition Lit9;
    public static final ModuleMethod expand;

    public syntaxutils()
    {
        ModuleInfo.register(this);
    }

    public static Object expand$V(Object obj, Object aobj[])
    {
        Object obj1 = Keyword.searchForKeyword(aobj, 0, Lit0);
        if (obj1 == Special.dfault)
        {
            obj1 = misc.interactionEnvironment();
        }
        Object aobj1[] = new Object[2];
        aobj1[0] = Lit1;
        Object aobj2[] = new Object[2];
        aobj2[0] = obj;
        aobj2[1] = LList.Empty;
        aobj1[1] = Quote.consX$V(aobj2);
        Object obj2 = Quote.append$V(aobj1);
        Object aobj3[] = new Object[2];
        aobj3[0] = Lit0;
        aobj3[1] = obj1;
        return unrewrite(rewriteForm$V(obj2, aobj3));
    }

    static Expression rewriteForm$V(Object obj, Object aobj[])
    {
        Object obj1 = Keyword.searchForKeyword(aobj, 0, Lit2);
        if (obj1 == Special.dfault)
        {
            obj1 = Language.getDefaultLanguage();
        }
        Object obj2 = Keyword.searchForKeyword(aobj, 0, Lit0);
        if (obj2 == Special.dfault)
        {
            obj2 = misc.interactionEnvironment();
        }
        Environment environment;
        Language language;
        NameLookup namelookup;
        SourceMessages sourcemessages;
        Language language1;
        Translator translator;
        Compilation compilation;
        Exception exception;
        Expression expression;
        try
        {
            environment = (Environment)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "gnu.expr.NameLookup.getInstance(gnu.mapping.Environment,gnu.expr.Language)", 1, obj2);
        }
        try
        {
            language = (Language)obj1;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "gnu.expr.NameLookup.getInstance(gnu.mapping.Environment,gnu.expr.Language)", 2, obj1);
        }
        namelookup = NameLookup.getInstance(environment, language);
        sourcemessages = new SourceMessages();
        try
        {
            language1 = (Language)obj1;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "kawa.lang.Translator.<init>(gnu.expr.Language,gnu.text.SourceMessages,gnu.expr.NameLookup)", 1, obj1);
        }
        translator = new Translator(language1, sourcemessages, namelookup);
        translator.pushNewModule(null);
        compilation = Compilation.setSaveCurrent(translator);
        expression = translator.rewrite(obj);
        Compilation.restoreCurrent(compilation);
        return expression;
        exception;
        Compilation.restoreCurrent(compilation);
        throw exception;
    }

    static Object unrewrite(Expression expression)
    {
        frame frame2 = new frame();
        if (expression instanceof LetExp)
        {
            IfExp ifexp;
            Object aobj[];
            Object aobj1[];
            Object aobj2[];
            Object aobj3[];
            Expression expression1;
            Object obj;
            BeginExp beginexp;
            Object aobj4[];
            ApplyExp applyexp;
            ReferenceExp referenceexp;
            LambdaExp lambdaexp;
            Object aobj5[];
            Object aobj6[];
            Declaration declaration;
            Object aobj7[];
            SetExp setexp;
            Object aobj8[];
            Object aobj9[];
            Object aobj10[];
            QuoteExp quoteexp;
            LetExp letexp;
            try
            {
                letexp = (LetExp)expression;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "exp", -2, expression);
            }
            expression = ((Expression) (unrewriteLet(letexp)));
        } else
        {
            if (expression instanceof QuoteExp)
            {
                try
                {
                    quoteexp = (QuoteExp)expression;
                }
                catch (ClassCastException classcastexception6)
                {
                    throw new WrongType(classcastexception6, "exp", -2, expression);
                }
                return unrewriteQuote(quoteexp);
            }
            if (expression instanceof SetExp)
            {
                try
                {
                    setexp = (SetExp)expression;
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "exp", -2, expression);
                }
                aobj8 = new Object[2];
                aobj8[0] = Lit3;
                aobj9 = new Object[2];
                aobj9[0] = setexp.getSymbol();
                aobj10 = new Object[2];
                aobj10[0] = unrewrite(setexp.getNewValue());
                aobj10[1] = LList.Empty;
                aobj9[1] = Quote.consX$V(aobj10);
                aobj8[1] = Quote.consX$V(aobj9);
                return Quote.append$V(aobj8);
            }
            if (expression instanceof LambdaExp)
            {
                try
                {
                    lambdaexp = (LambdaExp)expression;
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "exp", -2, expression);
                }
                aobj5 = new Object[2];
                aobj5[0] = Lit4;
                aobj6 = new Object[2];
                frame2.pack = LList.Empty;
                for (declaration = lambdaexp.firstDecl(); declaration != null; declaration = declaration.nextDecl())
                {
                    frame2.pack = lists.cons(declaration.getSymbol(), frame2.pack);
                }

                aobj6[0] = lists.reverse$Ex(frame2.pack);
                aobj7 = new Object[2];
                aobj7[0] = unrewrite(lambdaexp.body);
                aobj7[1] = LList.Empty;
                aobj6[1] = Quote.consX$V(aobj7);
                aobj5[1] = Quote.consX$V(aobj6);
                return Quote.append$V(aobj5);
            }
            if (expression instanceof ReferenceExp)
            {
                try
                {
                    referenceexp = (ReferenceExp)expression;
                }
                catch (ClassCastException classcastexception3)
                {
                    throw new WrongType(classcastexception3, "exp", -2, expression);
                }
                return referenceexp.getSymbol();
            }
            if (expression instanceof ApplyExp)
            {
                try
                {
                    applyexp = (ApplyExp)expression;
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "exp", -2, expression);
                }
                return unrewriteApply(applyexp);
            }
            if (expression instanceof BeginExp)
            {
                try
                {
                    beginexp = (BeginExp)expression;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "exp", -2, expression);
                }
                aobj4 = new Object[2];
                aobj4[0] = Lit5;
                aobj4[1] = unrewrite$St(beginexp.getExpressions());
                return Quote.append$V(aobj4);
            }
            if (expression instanceof IfExp)
            {
                try
                {
                    ifexp = (IfExp)expression;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "exp", -2, expression);
                }
                aobj = new Object[2];
                aobj[0] = Lit6;
                aobj1 = new Object[2];
                aobj1[0] = unrewrite(ifexp.getTest());
                aobj2 = new Object[2];
                aobj2[0] = unrewrite(ifexp.getThenClause());
                aobj3 = new Object[2];
                expression1 = ifexp.getElseClause();
                if (expression1 == null)
                {
                    obj = LList.Empty;
                } else
                {
                    obj = LList.list1(unrewrite(expression1));
                }
                aobj3[0] = obj;
                aobj3[1] = LList.Empty;
                aobj2[1] = Quote.append$V(aobj3);
                aobj1[1] = Quote.consX$V(aobj2);
                aobj[1] = Quote.consX$V(aobj1);
                return Quote.append$V(aobj);
            }
        }
        return expression;
    }

    static Object unrewrite$St(Expression aexpression[])
    {
        frame0 frame0_1 = new frame0();
        frame0_1.pack = LList.Empty;
        Integer integer = Integer.valueOf(aexpression.length);
        for (Object obj = Lit7; Scheme.numEqu.apply2(obj, integer) == Boolean.FALSE; obj = AddOp.$Pl.apply2(obj, Lit8))
        {
            frame0_1.pack = lists.cons(unrewrite(aexpression[((Number)obj).intValue()]), frame0_1.pack);
        }

        return lists.reverse$Ex(frame0_1.pack);
    }

    static Object unrewriteApply(ApplyExp applyexp)
    {
        Expression expression = applyexp.getFunction();
        Object obj = unrewrite$St(applyexp.getArgs());
        if (expression instanceof ReferenceExp)
        {
            Declaration declaration;
            Declaration declaration1;
            Object obj1;
            int i;
            int j;
            Object obj2;
            Object aobj[];
            Object aobj1[];
            Object aobj2[];
            int k;
            int l;
            ReferenceExp referenceexp;
            try
            {
                referenceexp = (ReferenceExp)expression;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "fun", -2, expression);
            }
            declaration = referenceexp.getBinding();
        } else
        {
            declaration = null;
        }
        declaration1 = Declaration.getDeclarationFromStatic("kawa.standard.Scheme", "applyToArgs");
        obj1 = applyexp.getFunctionValue();
        if (declaration == null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        j = 1 & i + 1;
        if (j != 0)
        {
            if (declaration1 == null)
            {
                k = 1;
            } else
            {
                k = 0;
            }
            l = 1 & k + 1;
            if (l == 0 ? l != 0 : SlotGet.getSlotValue(false, declaration, "field", "field", "getField", "isField", Scheme.instance) == declaration1.field)
            {
                return obj;
            }
        } else
        if (j != 0)
        {
            return obj;
        }
        if (obj1 instanceof Convert)
        {
            aobj2 = new Object[2];
            aobj2[0] = Lit11;
            aobj2[1] = obj;
            obj2 = Quote.append$V(aobj2);
        } else
        if (obj1 instanceof GetNamedPart)
        {
            aobj1 = new Object[2];
            aobj1[0] = Lit12;
            aobj1[1] = obj;
            obj2 = Quote.append$V(aobj1);
        } else
        {
            obj2 = Boolean.FALSE;
        }
        if (obj2 != Boolean.FALSE)
        {
            return obj2;
        } else
        {
            aobj = new Object[2];
            aobj[0] = unrewrite(expression);
            aobj[1] = obj;
            return Quote.consX$V(aobj);
        }
    }

    static Object unrewriteLet(LetExp letexp)
    {
        frame1 frame1_1 = new frame1();
        Object aobj[] = new Object[2];
        aobj[0] = Lit9;
        Object aobj1[] = new Object[2];
        frame1_1.pack = LList.Empty;
        Declaration declaration = letexp.firstDecl();
        for (Object obj = Lit7; declaration != null; obj = AddOp.$Pl.apply2(obj, Lit8))
        {
            frame1_1.pack = lists.cons(LList.list2(declaration.getSymbol(), unrewrite(letexp.inits[((Number)obj).intValue()])), frame1_1.pack);
            declaration = declaration.nextDecl();
        }

        aobj1[0] = lists.reverse$Ex(frame1_1.pack);
        Object aobj2[] = new Object[2];
        aobj2[0] = unrewrite(letexp.body);
        aobj2[1] = LList.Empty;
        aobj1[1] = Quote.consX$V(aobj2);
        aobj[1] = Quote.consX$V(aobj1);
        return Quote.append$V(aobj);
    }

    static Object unrewriteQuote(QuoteExp quoteexp)
    {
        Object obj = quoteexp.getValue();
        if (Numeric.asNumericOrNull(obj) != null)
        {
            Object aobj[];
            Object aobj1[];
            Class class1;
            String s;
            Type type;
            CharSequence charsequence;
            Keyword keyword;
            Char char1;
            Boolean boolean1;
            boolean flag;
            Boolean boolean2;
            Numeric numeric;
            try
            {
                numeric = LangObjType.coerceNumeric(obj);
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "val", -2, obj);
            }
            obj = numeric;
        } else
        {
            if (obj instanceof Boolean)
            {
                try
                {
                    boolean1 = Boolean.FALSE;
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "val", -2, obj);
                }
                if (obj != boolean1)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    boolean2 = Boolean.TRUE;
                } else
                {
                    boolean2 = Boolean.FALSE;
                }
                return boolean2;
            }
            if (obj instanceof Char)
            {
                try
                {
                    char1 = (Char)obj;
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "val", -2, obj);
                }
                return char1;
            }
            if (obj instanceof Keyword)
            {
                try
                {
                    keyword = (Keyword)obj;
                }
                catch (ClassCastException classcastexception3)
                {
                    throw new WrongType(classcastexception3, "val", -2, obj);
                }
                return keyword;
            }
            if (obj instanceof CharSequence)
            {
                try
                {
                    charsequence = (CharSequence)obj;
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "val", -2, obj);
                }
                return charsequence;
            }
            if (obj != Special.undefined && obj != EofClass.eofValue)
            {
                if (obj instanceof Type)
                {
                    try
                    {
                        type = (Type)obj;
                    }
                    catch (ClassCastException classcastexception1)
                    {
                        throw new WrongType(classcastexception1, "val", -2, obj);
                    }
                    s = type.getName();
                } else
                if (obj instanceof Class)
                {
                    try
                    {
                        class1 = (Class)obj;
                    }
                    catch (ClassCastException classcastexception)
                    {
                        throw new WrongType(classcastexception, "val", -2, obj);
                    }
                    s = class1.getName();
                } else
                {
                    aobj = new Object[2];
                    aobj[0] = Lit10;
                    aobj1 = new Object[2];
                    aobj1[0] = obj;
                    aobj1[1] = LList.Empty;
                    aobj[1] = Quote.consX$V(aobj1);
                    return Quote.append$V(aobj);
                }
                return misc.string$To$Symbol(Format.formatToString(0, new Object[] {
                    "<~a>", s
                }));
            }
        }
        return obj;
    }

    public Object applyN(ModuleMethod modulemethod, Object aobj[])
    {
        if (modulemethod.selector == 1)
        {
            Object obj = aobj[0];
            int i = -1 + aobj.length;
            Object aobj1[] = new Object[i];
            do
            {
                if (--i < 0)
                {
                    return expand$V(obj, aobj1);
                }
                aobj1[i] = aobj[i + 1];
            } while (true);
        } else
        {
            return super.applyN(modulemethod, aobj);
        }
    }

    public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
    {
        if (modulemethod.selector == 1)
        {
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;
        } else
        {
            return super.matchN(modulemethod, aobj, callcontext);
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit26 = (SimpleSymbol)(new SimpleSymbol("lambda")).readResolve();
        Lit25 = (SimpleSymbol)(new SimpleSymbol("as")).readResolve();
        Lit24 = (SimpleSymbol)(new SimpleSymbol("else")).readResolve();
        Lit23 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
        Lit22 = (SimpleSymbol)(new SimpleSymbol("cond")).readResolve();
        Lit21 = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
        Lit20 = (SimpleSymbol)(new SimpleSymbol("or")).readResolve();
        Lit19 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
        Lit18 = (SimpleSymbol)(new SimpleSymbol("eql")).readResolve();
        Lit17 = (SimpleSymbol)(new SimpleSymbol("expand")).readResolve();
        Object aobj[] = new Object[1];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("!")).readResolve();
        Lit15 = simplesymbol;
        aobj[0] = simplesymbol;
        SyntaxRule asyntaxrule[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern = new SyntaxPattern("\f\030\f\007\f\017\r\027\020\b\b", new Object[0], 3);
        Object aobj1[] = new Object[2];
        aobj1[0] = (SimpleSymbol)(new SimpleSymbol("invoke")).readResolve();
        aobj1[1] = Lit19;
        asyntaxrule[0] = new SyntaxRule(syntaxpattern, "\001\001\003", "\021\030\004\t\013)\021\030\f\b\003\b\025\023", aobj1, 1);
        Lit16 = new SyntaxRules(aobj, asyntaxrule, 3);
        Object aobj2[] = new Object[3];
        SimpleSymbol simplesymbol1 = (SimpleSymbol)(new SimpleSymbol("typecase%")).readResolve();
        Lit13 = simplesymbol1;
        aobj2[0] = simplesymbol1;
        aobj2[1] = Lit18;
        aobj2[2] = Lit20;
        SyntaxRule asyntaxrule1[] = new SyntaxRule[6];
        Object aobj3[] = new Object[1];
        aobj3[0] = Boolean.TRUE;
        SyntaxPattern syntaxpattern1 = new SyntaxPattern("\f\030\f\007<\f\002\r\017\b\b\b\r\027\020\b\b", aobj3, 3);
        Object aobj4[] = new Object[1];
        aobj4[0] = Lit21;
        asyntaxrule1[0] = new SyntaxRule(syntaxpattern1, "\001\003\003", "\021\030\004\b\r\013", aobj4, 1);
        Object aobj5[] = new Object[1];
        aobj5[0] = Lit18;
        SyntaxPattern syntaxpattern2 = new SyntaxPattern("\f\030\f\007\\,\f\002\f\017\b\r\027\020\b\b\r\037\030\b\b", aobj5, 4);
        Object aobj6[] = new Object[5];
        aobj6[0] = Lit22;
        aobj6[1] = (SimpleSymbol)(new SimpleSymbol("eqv?")).readResolve();
        aobj6[2] = Lit19;
        aobj6[3] = Lit24;
        aobj6[4] = Lit13;
        asyntaxrule1[1] = new SyntaxRule(syntaxpattern2, "\001\001\003\003", "\021\030\004yY\021\030\f\t\003\b\021\030\024\b\013\b\025\023\b\021\030\034\b\021\030$\t\003\b\035\033", aobj6, 1);
        Object aobj7[] = new Object[1];
        aobj7[0] = Lit20;
        SyntaxPattern syntaxpattern3 = new SyntaxPattern("\f\030\f\007\\,\f\002\f\017\b\r\027\020\b\b\r\037\030\b\b", aobj7, 4);
        Object aobj8[] = new Object[1];
        aobj8[0] = Lit13;
        asyntaxrule1[2] = new SyntaxRule(syntaxpattern3, "\001\001\003\003", "\021\030\004\t\003)\t\013\b\025\023\b\035\033", aobj8, 1);
        Object aobj9[] = new Object[1];
        aobj9[0] = Lit20;
        SyntaxPattern syntaxpattern4 = new SyntaxPattern("\f\030\f\007l<\f\002\r\017\b\b\b\r\027\020\b\b\r\037\030\b\b", aobj9, 4);
        Object aobj10[] = new Object[6];
        aobj10[0] = Lit23;
        aobj10[1] = (SimpleSymbol)(new SimpleSymbol("f")).readResolve();
        aobj10[2] = Lit26;
        aobj10[3] = Lit21;
        aobj10[4] = Lit13;
        aobj10[5] = Boolean.TRUE;
        asyntaxrule1[3] = new SyntaxRule(syntaxpattern4, "\001\003\003\003", "\021\030\004\221\b\021\030\f\b\021\030\024\021\b\003\b\021\030\034\b\025\023\b\021\030$\t\003I\r\t\013\b\021\030\f\b\003\b\021\030,\b\021\030$\t\003\b\035\033", aobj10, 1);
        SyntaxPattern syntaxpattern5 = new SyntaxPattern("\f\030\f\007<\f\017\r\027\020\b\b\r\037\030\b\b", new Object[0], 4);
        Object aobj11[] = new Object[7];
        aobj11[0] = Lit22;
        aobj11[1] = (SimpleSymbol)(new SimpleSymbol("instance?")).readResolve();
        aobj11[2] = Lit23;
        aobj11[3] = (SimpleSymbol)(new SimpleSymbol("::")).readResolve();
        aobj11[4] = Lit21;
        aobj11[5] = Lit24;
        aobj11[6] = Lit13;
        asyntaxrule1[4] = new SyntaxRule(syntaxpattern5, "\001\001\003\003", "\021\030\004\3619\021\030\f\t\003\b\013\b\021\030\024Q\b\t\003\021\030\034\t\013\b\003\b\021\030$\b\025\023\b\021\030,\b\021\0304\t\003\b\035\033", aobj11, 1);
        SyntaxPattern syntaxpattern6 = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
        Object aobj12[] = new Object[6];
        aobj12[0] = (SimpleSymbol)(new SimpleSymbol("error")).readResolve();
        aobj12[1] = "typecase% failed";
        aobj12[2] = Lit15;
        aobj12[3] = (SimpleSymbol)(new SimpleSymbol("getClass")).readResolve();
        aobj12[4] = Lit25;
        aobj12[5] = (SimpleSymbol)(new SimpleSymbol("<object>")).readResolve();
        asyntaxrule1[5] = new SyntaxRule(syntaxpattern6, "\001", "\021\030\004\021\030\f\t\003\b\021\030\024\021\030\034\b\021\030$\021\030,\b\003", aobj12, 0);
        Lit14 = new SyntaxRules(aobj2, asyntaxrule1, 4);
        Lit12 = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol(":")).readResolve(), LList.Empty, "syntaxutils.scm", 0x9b010);
        Lit11 = PairWithPosition.make(Lit25, LList.Empty, "syntaxutils.scm", 0x99010);
        Lit10 = PairWithPosition.make(Lit19, LList.Empty, "syntaxutils.scm", 0x8700c);
        Lit9 = PairWithPosition.make(Lit23, LList.Empty, "syntaxutils.scm", 0x75004);
        Lit6 = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("if")).readResolve(), LList.Empty, "syntaxutils.scm", 0x66007);
        Lit5 = PairWithPosition.make(Lit21, LList.Empty, "syntaxutils.scm", 0x6401b);
        Lit4 = PairWithPosition.make(Lit26, LList.Empty, "syntaxutils.scm", 0x5c007);
        Lit3 = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("set")).readResolve(), LList.Empty, "syntaxutils.scm", 0x5a007);
        Lit1 = PairWithPosition.make(Lit21, LList.Empty, "syntaxutils.scm", 0x4401d);
        $instance = new syntaxutils();
        $Prvt$typecase$Pc = Macro.make(Lit13, Lit14, $instance);
        $Prvt$$Ex = Macro.make(Lit15, Lit16, $instance);
        expand = new ModuleMethod($instance, 1, Lit17, -4095);
        $instance.run();
    }
}
