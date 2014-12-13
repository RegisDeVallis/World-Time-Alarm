// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.math.IntNum;
import kawa.lang.Macro;
import kawa.lang.Pattern;
import kawa.lang.Quote;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.standard.syntax_case;
import kawa.standard.syntax_error;
import kawa.standard.try_catch;

public class prim_syntax extends ModuleBody
{
    public class frame extends ModuleBody
    {

        Object $unnamed$0[];
        Object out$Mnbindings;
        Object out$Mninits;

        public Object lambda4processBinding(Object obj)
        {
            Object aobj[] = SyntaxPattern.allocVars(8, $unnamed$0);
            if (prim_syntax.Lit34.match(obj, aobj, 0))
            {
                return Values.empty;
            }
            if (prim_syntax.Lit35.match(obj, aobj, 0))
            {
                TemplateScope templatescope3 = TemplateScope.make();
                out$Mnbindings = new Pair(prim_syntax.Lit36.execute(aobj, templatescope3), out$Mnbindings);
                TemplateScope templatescope4 = TemplateScope.make();
                out$Mninits = new Pair(prim_syntax.Lit37.execute(aobj, templatescope4), out$Mninits);
                TemplateScope templatescope5 = TemplateScope.make();
                return lambda4processBinding(prim_syntax.Lit38.execute(aobj, templatescope5));
            }
            if (prim_syntax.Lit39.match(obj, aobj, 0))
            {
                TemplateScope templatescope = TemplateScope.make();
                out$Mnbindings = new Pair(prim_syntax.Lit40.execute(aobj, templatescope), out$Mnbindings);
                TemplateScope templatescope1 = TemplateScope.make();
                out$Mninits = new Pair(prim_syntax.Lit41.execute(aobj, templatescope1), out$Mninits);
                TemplateScope templatescope2 = TemplateScope.make();
                return lambda4processBinding(prim_syntax.Lit42.execute(aobj, templatescope2));
            }
            if (prim_syntax.Lit43.match(obj, aobj, 0))
            {
                Object aobj2[];
                if ("missing initializion in letrec" instanceof Object[])
                {
                    aobj2 = (Object[])"missing initializion in letrec";
                } else
                {
                    aobj2 = (new Object[] {
                        "missing initializion in letrec"
                    });
                }
                return prim_syntax.syntaxError(obj, aobj2);
            }
            if (prim_syntax.Lit44.match(obj, aobj, 0))
            {
                Object aobj1[];
                if ("invalid bindings syntax in letrec" instanceof Object[])
                {
                    aobj1 = (Object[])"invalid bindings syntax in letrec";
                } else
                {
                    aobj1 = (new Object[] {
                        "invalid bindings syntax in letrec"
                    });
                }
                return prim_syntax.syntaxError(obj, aobj1);
            } else
            {
                return syntax_case.error("syntax-case", obj);
            }
        }

        public frame()
        {
        }
    }


    public static final prim_syntax $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxRules Lit1;
    static final SyntaxRules Lit10;
    static final SimpleSymbol Lit11;
    static final SyntaxRules Lit12;
    static final SimpleSymbol Lit13;
    static final SyntaxPattern Lit14 = new SyntaxPattern("\f\007\f\017\f\027\b", new Object[0], 3);
    static final SyntaxTemplate Lit15 = new SyntaxTemplate("\001\001\001", "\013", new Object[0], 0);
    static final SyntaxTemplate Lit16 = new SyntaxTemplate("\001\001\001", "\023", new Object[0], 0);
    static final SyntaxPattern Lit17 = new SyntaxPattern("\f\007\f\017\f\027\f\037\b", new Object[0], 4);
    static final SyntaxTemplate Lit18 = new SyntaxTemplate("\001\001\001\001", "\013", new Object[0], 0);
    static final SyntaxTemplate Lit19 = new SyntaxTemplate("\001\001\001\001", "\023", new Object[0], 0);
    static final SimpleSymbol Lit2;
    static final SyntaxTemplate Lit20 = new SyntaxTemplate("\001\001\001\001", "\033", new Object[0], 0);
    static final SyntaxPattern Lit21 = new SyntaxPattern("\f\007\f\017\f\027\f\037\f'+", new Object[0], 6);
    static final SyntaxTemplate Lit22 = new SyntaxTemplate("\001\001\001\001\001\0", "#", new Object[0], 0);
    static final SyntaxPattern Lit23 = new SyntaxPattern("\f\007\013", new Object[0], 2);
    static final SyntaxTemplate Lit24 = new SyntaxTemplate("\001\0", "\n", new Object[0], 0);
    static final SimpleSymbol Lit25;
    static final SyntaxPattern Lit26 = new SyntaxPattern("\f\007\f\017-\f\027\f\037#\020\030\b", new Object[0], 5);
    static final SyntaxTemplate Lit27 = new SyntaxTemplate("\001\001\003\003\002", "\013", new Object[0], 0);
    static final SyntaxTemplate Lit28;
    static final SimpleSymbol Lit29;
    static final SyntaxRules Lit3;
    static final SyntaxPattern Lit30 = new SyntaxPattern("\f\007\f\017\023", new Object[0], 3);
    static final SyntaxTemplate Lit31 = new SyntaxTemplate("\001\001\0", "\013", new Object[0], 0);
    static final SyntaxTemplate Lit32;
    static final SyntaxTemplate Lit33 = new SyntaxTemplate("\001\001\0", "\022", new Object[0], 0);
    static final SyntaxPattern Lit34 = new SyntaxPattern("\b", new Object[0], 3);
    static final SyntaxPattern Lit35 = new SyntaxPattern(",\f\037\f'\b+", new Object[0], 6);
    static final SyntaxTemplate Lit36;
    static final SyntaxTemplate Lit37;
    static final SyntaxTemplate Lit38 = new SyntaxTemplate("\001\001\000\001\001\0", "*", new Object[0], 0);
    static final SyntaxPattern Lit39 = new SyntaxPattern("L\f\037\f'\f/\f7\b;", new Object[0], 8);
    static final SimpleSymbol Lit4;
    static final SyntaxTemplate Lit40;
    static final SyntaxTemplate Lit41;
    static final SyntaxTemplate Lit42 = new SyntaxTemplate("\001\001\000\001\001\001\001\0", ":", new Object[0], 0);
    static final SyntaxPattern Lit43 = new SyntaxPattern("\034\f\037\b#", new Object[0], 5);
    static final SyntaxPattern Lit44 = new SyntaxPattern("\033", new Object[0], 4);
    static final SimpleSymbol Lit45;
    static final SimpleSymbol Lit46;
    static final SimpleSymbol Lit47;
    static final SimpleSymbol Lit48;
    static final SimpleSymbol Lit49;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit50;
    static final IntNum Lit51;
    static final IntNum Lit52;
    static final IntNum Lit53;
    static final IntNum Lit54;
    static final IntNum Lit55;
    static final IntNum Lit56;
    static final SimpleSymbol Lit57;
    static final SimpleSymbol Lit58;
    static final SimpleSymbol Lit6;
    static final SyntaxRules Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final Macro define;
    public static final Macro define$Mnconstant;
    public static final Macro define$Mnprivate;
    public static final Macro define$Mnsyntax;
    public static final Macro _fldif;
    public static final Macro letrec;
    public static final Macro syntax$Mn$Grexpression;
    public static final Macro syntax$Mnbody$Mn$Grexpression;
    public static final ModuleMethod syntax$Mnerror;
    public static final Macro try$Mncatch;

    public prim_syntax()
    {
        ModuleInfo.register(this);
    }

    static Object lambda1(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(6, null);
        if (Lit14.match(obj, aobj, 0))
        {
            TemplateScope templatescope5 = TemplateScope.make();
            Expression expression2 = SyntaxForms.rewrite(Lit15.execute(aobj, templatescope5));
            TemplateScope templatescope6 = TemplateScope.make();
            return new IfExp(expression2, SyntaxForms.rewrite(Lit16.execute(aobj, templatescope6)), null);
        }
        if (Lit17.match(obj, aobj, 0))
        {
            TemplateScope templatescope2 = TemplateScope.make();
            Expression expression = SyntaxForms.rewrite(Lit18.execute(aobj, templatescope2));
            TemplateScope templatescope3 = TemplateScope.make();
            Expression expression1 = SyntaxForms.rewrite(Lit19.execute(aobj, templatescope3));
            TemplateScope templatescope4 = TemplateScope.make();
            return new IfExp(expression, expression1, SyntaxForms.rewrite(Lit20.execute(aobj, templatescope4)));
        }
        if (Lit21.match(obj, aobj, 0))
        {
            TemplateScope templatescope1 = TemplateScope.make();
            Object obj2 = Lit22.execute(aobj, templatescope1);
            Object aobj2[];
            if ("too many expressions for 'if'" instanceof Object[])
            {
                aobj2 = (Object[])"too many expressions for 'if'";
            } else
            {
                aobj2 = (new Object[] {
                    "too many expressions for 'if'"
                });
            }
            return syntaxError(obj2, aobj2);
        }
        if (Lit23.match(obj, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            Object obj1 = Lit24.execute(aobj, templatescope);
            Object aobj1[];
            if ("too few expressions for 'if'" instanceof Object[])
            {
                aobj1 = (Object[])"too few expressions for 'if'";
            } else
            {
                aobj1 = (new Object[] {
                    "too few expressions for 'if'"
                });
            }
            return syntaxError(obj1, aobj1);
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    static Object lambda2(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(5, null);
        if (Lit26.match(obj, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            Object obj1 = Lit27.execute(aobj, templatescope);
            TemplateScope templatescope1 = TemplateScope.make();
            return try_catch.rewrite(obj1, Lit28.execute(aobj, templatescope1));
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    static Object lambda3(Object obj)
    {
        frame frame1 = new frame();
        LList llist = LList.Empty;
        frame1.Mninits = LList.Empty;
        frame1.Mnbindings = llist;
        frame1._fld0 = SyntaxPattern.allocVars(3, null);
        if (Lit30.match(obj, frame1._fld0, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            frame1.lambda4processBinding(Lit31.execute(frame1._fld0, templatescope));
            frame1.Mnbindings = LList.reverseInPlace(frame1.Mnbindings);
            frame1.Mninits = LList.reverseInPlace(frame1.Mninits);
            TemplateScope templatescope1 = TemplateScope.make();
            Object aobj[] = new Object[2];
            aobj[0] = Lit32.execute(frame1._fld0, templatescope1);
            Object aobj1[] = new Object[2];
            aobj1[0] = frame1.Mnbindings;
            Object aobj2[] = new Object[2];
            aobj2[0] = frame1.Mninits;
            aobj2[1] = Lit33.execute(frame1._fld0, templatescope1);
            aobj1[1] = Quote.append$V(aobj2);
            aobj[1] = Quote.consX$V(aobj1);
            return Quote.append$V(aobj);
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    public static transient Expression syntaxError(Object obj, Object aobj[])
    {
        return syntax_error.error(obj, aobj);
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply1(modulemethod, obj);

        case 2: // '\002'
            return lambda1(obj);

        case 3: // '\003'
            return lambda2(obj);

        case 4: // '\004'
            return lambda3(obj);
        }
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
                    return syntaxError(obj, aobj1);
                }
                aobj1[i] = aobj[i + 1];
            } while (true);
        } else
        {
            return super.applyN(modulemethod, aobj);
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 4: // '\004'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 3: // '\003'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 2: // '\002'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
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
        Lit58 = (SimpleSymbol)(new SimpleSymbol("lambda")).readResolve();
        Lit57 = (SimpleSymbol)(new SimpleSymbol("%define-syntax")).readResolve();
        Lit56 = IntNum.make(0);
        Lit55 = IntNum.make(1);
        Lit54 = IntNum.make(4);
        Lit53 = IntNum.make(5);
        Lit52 = IntNum.make(8);
        Lit51 = IntNum.make(9);
        Lit50 = (SimpleSymbol)(new SimpleSymbol("%define")).readResolve();
        Lit49 = (SimpleSymbol)(new SimpleSymbol("::")).readResolve();
        Lit48 = (SimpleSymbol)(new SimpleSymbol("quasiquote")).readResolve();
        Lit47 = (SimpleSymbol)(new SimpleSymbol("kawa.lang.SyntaxForms")).readResolve();
        Lit46 = (SimpleSymbol)(new SimpleSymbol("$lookup$")).readResolve();
        Lit45 = (SimpleSymbol)(new SimpleSymbol("set!")).readResolve();
        Object aobj[] = new Object[1];
        aobj[0] = Lit45;
        Lit41 = new SyntaxTemplate("\001\001\000\001\001\001\001\0", "\021\030\004\t\033\b3", aobj, 0);
        Object aobj1[] = new Object[1];
        aobj1[0] = PairWithPosition.make(Special.undefined, LList.Empty, "prim_syntax.scm", 0x7303e);
        Lit40 = new SyntaxTemplate("\001\001\000\001\001\001\001\0", "\t\033\t#\t+\030\004", aobj1, 0);
        Object aobj2[] = new Object[1];
        aobj2[0] = Lit45;
        Lit37 = new SyntaxTemplate("\001\001\000\001\001\0", "\021\030\004\t\033\b#", aobj2, 0);
        Object aobj3[] = new Object[1];
        aobj3[0] = PairWithPosition.make(Special.undefined, LList.Empty, "prim_syntax.scm", 0x6e022);
        Lit36 = new SyntaxTemplate("\001\001\000\001\001\0", "\t\033\030\004", aobj3, 0);
        Object aobj4[] = new Object[1];
        aobj4[0] = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("%let")).readResolve(), LList.Empty, "prim_syntax.scm", 0x7d00b);
        Lit32 = new SyntaxTemplate("\001\001\0", "\030\004", aobj4, 0);
        Lit29 = (SimpleSymbol)(new SimpleSymbol("letrec")).readResolve();
        Object aobj5[] = new Object[1];
        aobj5[0] = Lit49;
        Lit28 = new SyntaxTemplate("\001\001\003\003\002", "(\b\025A\b\t\023\021\030\004\b\033\"", aobj5, 1);
        Lit25 = (SimpleSymbol)(new SimpleSymbol("try-catch")).readResolve();
        Lit13 = (SimpleSymbol)(new SimpleSymbol("if")).readResolve();
        Object aobj6[] = new Object[1];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("syntax-body->expression")).readResolve();
        Lit11 = simplesymbol;
        aobj6[0] = simplesymbol;
        SyntaxRule asyntaxrule[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
        Object aobj7[] = new Object[1];
        aobj7[0] = PairWithPosition.make(Lit46, Pair.make(Lit47, Pair.make(Pair.make(Lit48, Pair.make((SimpleSymbol)(new SimpleSymbol("rewriteBody")).readResolve(), LList.Empty)), LList.Empty)), "prim_syntax.scm", 0x42007);
        asyntaxrule[0] = new SyntaxRule(syntaxpattern, "\001", "\021\030\004\b\003", aobj7, 0);
        Lit12 = new SyntaxRules(aobj6, asyntaxrule, 1);
        Object aobj8[] = new Object[1];
        SimpleSymbol simplesymbol1 = (SimpleSymbol)(new SimpleSymbol("syntax->expression")).readResolve();
        Lit9 = simplesymbol1;
        aobj8[0] = simplesymbol1;
        SyntaxRule asyntaxrule1[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern1 = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
        Object aobj9[] = new Object[1];
        aobj9[0] = PairWithPosition.make(Lit46, Pair.make(Lit47, Pair.make(Pair.make(Lit48, Pair.make((SimpleSymbol)(new SimpleSymbol("rewrite")).readResolve(), LList.Empty)), LList.Empty)), "prim_syntax.scm", 0x3d007);
        asyntaxrule1[0] = new SyntaxRule(syntaxpattern1, "\001", "\021\030\004\b\003", aobj9, 0);
        Lit10 = new SyntaxRules(aobj8, asyntaxrule1, 1);
        Lit8 = (SimpleSymbol)(new SimpleSymbol("syntax-error")).readResolve();
        Object aobj10[] = new Object[3];
        SimpleSymbol simplesymbol2 = (SimpleSymbol)(new SimpleSymbol("define-constant")).readResolve();
        Lit6 = simplesymbol2;
        aobj10[0] = simplesymbol2;
        aobj10[1] = Lit49;
        aobj10[2] = Lit46;
        SyntaxRule asyntaxrule2[] = new SyntaxRule[5];
        Object aobj11[] = new Object[2];
        aobj11[0] = Lit46;
        aobj11[1] = Lit49;
        SyntaxPattern syntaxpattern2 = new SyntaxPattern("\f\030\\\f\002\f\007,\f\017\f\027\b\b\f\n\f\037\f'\b", aobj11, 5);
        Object aobj12[] = new Object[3];
        aobj12[0] = Lit50;
        aobj12[1] = Lit46;
        aobj12[2] = Lit51;
        asyntaxrule2[0] = new SyntaxRule(syntaxpattern2, "\001\001\001\001\001", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\021\030\024\t\033\b#", aobj12, 0);
        Object aobj13[] = new Object[1];
        aobj13[0] = Lit46;
        SyntaxPattern syntaxpattern3 = new SyntaxPattern("\f\030\\\f\002\f\007,\f\017\f\027\b\b\f\037\b", aobj13, 4);
        Object aobj14[] = new Object[4];
        aobj14[0] = Lit50;
        aobj14[1] = Lit46;
        aobj14[2] = Lit52;
        asyntaxrule2[1] = new SyntaxRule(syntaxpattern3, "\001\001\001\001", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\021\030\024\021\030\034\b\033", aobj14, 0);
        SyntaxPattern syntaxpattern4 = new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3);
        Object aobj15[] = new Object[3];
        aobj15[0] = Lit50;
        aobj15[1] = IntNum.make(10);
        aobj15[2] = Boolean.TRUE;
        asyntaxrule2[2] = new SyntaxRule(syntaxpattern4, "\001\000\0", "\021\030\004\t\003\021\030\f\021\030\024\t\n\022", aobj15, 0);
        Object aobj16[] = new Object[1];
        aobj16[0] = Lit49;
        SyntaxPattern syntaxpattern5 = new SyntaxPattern("\f\030\f\007\f\002\f\017\f\027\b", aobj16, 3);
        Object aobj17[] = new Object[2];
        aobj17[0] = Lit50;
        aobj17[1] = Lit51;
        asyntaxrule2[3] = new SyntaxRule(syntaxpattern5, "\001\001\001", "\021\030\004\t\003\021\030\f\t\013\b\023", aobj17, 0);
        SyntaxPattern syntaxpattern6 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
        Object aobj18[] = new Object[3];
        aobj18[0] = Lit50;
        aobj18[1] = Lit52;
        asyntaxrule2[4] = new SyntaxRule(syntaxpattern6, "\001\001", "\021\030\004\t\003\021\030\f\021\030\024\b\013", aobj18, 0);
        Lit7 = new SyntaxRules(aobj10, asyntaxrule2, 5);
        Object aobj19[] = new Object[3];
        SimpleSymbol simplesymbol3 = (SimpleSymbol)(new SimpleSymbol("define-private")).readResolve();
        Lit4 = simplesymbol3;
        aobj19[0] = simplesymbol3;
        aobj19[1] = Lit49;
        aobj19[2] = Lit46;
        SyntaxRule asyntaxrule3[] = new SyntaxRule[5];
        Object aobj20[] = new Object[2];
        aobj20[0] = Lit46;
        aobj20[1] = Lit49;
        SyntaxPattern syntaxpattern7 = new SyntaxPattern("\f\030\\\f\002\f\007,\f\017\f\027\b\b\f\n\f\037\f'\b", aobj20, 5);
        Object aobj21[] = new Object[3];
        aobj21[0] = Lit50;
        aobj21[1] = Lit46;
        aobj21[2] = Lit53;
        asyntaxrule3[0] = new SyntaxRule(syntaxpattern7, "\001\001\001\001\001", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\021\030\024\t\033\b#", aobj21, 0);
        Object aobj22[] = new Object[1];
        aobj22[0] = Lit46;
        SyntaxPattern syntaxpattern8 = new SyntaxPattern("\f\030\\\f\002\f\007,\f\017\f\027\b\b\f\037\b", aobj22, 4);
        Object aobj23[] = new Object[4];
        aobj23[0] = Lit50;
        aobj23[1] = Lit46;
        aobj23[2] = Lit54;
        asyntaxrule3[1] = new SyntaxRule(syntaxpattern8, "\001\001\001\001", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\021\030\024\021\030\034\b\033", aobj23, 0);
        SyntaxPattern syntaxpattern9 = new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3);
        Object aobj24[] = new Object[3];
        aobj24[0] = Lit50;
        aobj24[1] = IntNum.make(6);
        aobj24[2] = Boolean.TRUE;
        asyntaxrule3[2] = new SyntaxRule(syntaxpattern9, "\001\000\0", "\021\030\004\t\003\021\030\f\021\030\024\t\n\022", aobj24, 0);
        Object aobj25[] = new Object[1];
        aobj25[0] = Lit49;
        SyntaxPattern syntaxpattern10 = new SyntaxPattern("\f\030\f\007\f\002\f\017\f\027\b", aobj25, 3);
        Object aobj26[] = new Object[2];
        aobj26[0] = Lit50;
        aobj26[1] = Lit53;
        asyntaxrule3[3] = new SyntaxRule(syntaxpattern10, "\001\001\001", "\021\030\004\t\003\021\030\f\t\013\b\023", aobj26, 0);
        SyntaxPattern syntaxpattern11 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
        Object aobj27[] = new Object[3];
        aobj27[0] = Lit50;
        aobj27[1] = Lit54;
        asyntaxrule3[4] = new SyntaxRule(syntaxpattern11, "\001\001", "\021\030\004\t\003\021\030\f\021\030\024\b\013", aobj27, 0);
        Lit5 = new SyntaxRules(aobj19, asyntaxrule3, 5);
        Object aobj28[] = new Object[3];
        SimpleSymbol simplesymbol4 = (SimpleSymbol)(new SimpleSymbol("define")).readResolve();
        Lit2 = simplesymbol4;
        aobj28[0] = simplesymbol4;
        aobj28[1] = Lit49;
        aobj28[2] = Lit46;
        SyntaxRule asyntaxrule4[] = new SyntaxRule[5];
        Object aobj29[] = new Object[2];
        aobj29[0] = Lit46;
        aobj29[1] = Lit49;
        SyntaxPattern syntaxpattern12 = new SyntaxPattern("\f\030\\\f\002\f\007,\f\017\f\027\b\b\f\n\f\037\f'\b", aobj29, 5);
        Object aobj30[] = new Object[3];
        aobj30[0] = Lit50;
        aobj30[1] = Lit46;
        aobj30[2] = Lit55;
        asyntaxrule4[0] = new SyntaxRule(syntaxpattern12, "\001\001\001\001\001", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\021\030\024\t\033\b#", aobj30, 0);
        Object aobj31[] = new Object[1];
        aobj31[0] = Lit46;
        SyntaxPattern syntaxpattern13 = new SyntaxPattern("\f\030\\\f\002\f\007,\f\017\f\027\b\b\f\037\b", aobj31, 4);
        Object aobj32[] = new Object[4];
        aobj32[0] = Lit50;
        aobj32[1] = Lit46;
        aobj32[2] = Lit56;
        asyntaxrule4[1] = new SyntaxRule(syntaxpattern13, "\001\001\001\001", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\021\030\024\021\030\034\b\033", aobj32, 0);
        SyntaxPattern syntaxpattern14 = new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3);
        Object aobj33[] = new Object[3];
        aobj33[0] = Lit50;
        aobj33[1] = IntNum.make(2);
        aobj33[2] = Boolean.TRUE;
        asyntaxrule4[2] = new SyntaxRule(syntaxpattern14, "\001\000\0", "\021\030\004\t\003\021\030\f\021\030\024\t\n\022", aobj33, 0);
        Object aobj34[] = new Object[1];
        aobj34[0] = Lit49;
        SyntaxPattern syntaxpattern15 = new SyntaxPattern("\f\030\f\007\f\002\f\017\f\027\b", aobj34, 3);
        Object aobj35[] = new Object[2];
        aobj35[0] = Lit50;
        aobj35[1] = Lit55;
        asyntaxrule4[3] = new SyntaxRule(syntaxpattern15, "\001\001\001", "\021\030\004\t\003\021\030\f\t\013\b\023", aobj35, 0);
        SyntaxPattern syntaxpattern16 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
        Object aobj36[] = new Object[3];
        aobj36[0] = Lit50;
        aobj36[1] = Lit56;
        asyntaxrule4[4] = new SyntaxRule(syntaxpattern16, "\001\001", "\021\030\004\t\003\021\030\f\021\030\024\b\013", aobj36, 0);
        Lit3 = new SyntaxRules(aobj28, asyntaxrule4, 5);
        Object aobj37[] = new Object[2];
        SimpleSymbol simplesymbol5 = (SimpleSymbol)(new SimpleSymbol("define-syntax")).readResolve();
        Lit0 = simplesymbol5;
        aobj37[0] = simplesymbol5;
        aobj37[1] = Lit46;
        SyntaxRule asyntaxrule5[] = new SyntaxRule[4];
        Object aobj38[] = new Object[1];
        aobj38[0] = Lit46;
        SyntaxPattern syntaxpattern17 = new SyntaxPattern("\f\030l\\\f\002\f\007,\f\017\f\027\b\b\033#", aobj38, 5);
        Object aobj39[] = new Object[3];
        aobj39[0] = Lit57;
        aobj39[1] = Lit46;
        aobj39[2] = Lit58;
        asyntaxrule5[0] = new SyntaxRule(syntaxpattern17, "\001\001\001\000\0", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\b\021\030\024\t\032\"", aobj39, 0);
        Object aobj40[] = new Object[1];
        aobj40[0] = Lit46;
        SyntaxPattern syntaxpattern18 = new SyntaxPattern("\f\030\\\f\002\f\007,\f\017\f\027\b\b\f\037\b", aobj40, 4);
        Object aobj41[] = new Object[2];
        aobj41[0] = Lit57;
        aobj41[1] = Lit46;
        asyntaxrule5[1] = new SyntaxRule(syntaxpattern18, "\001\001\001\001", "\021\030\004Q\021\030\f\t\003\b\t\013\b\023\b\033", aobj41, 0);
        SyntaxPattern syntaxpattern19 = new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3);
        Object aobj42[] = new Object[2];
        aobj42[0] = Lit57;
        aobj42[1] = Lit58;
        asyntaxrule5[2] = new SyntaxRule(syntaxpattern19, "\001\000\0", "\021\030\004\t\003\b\021\030\f\t\n\022", aobj42, 0);
        SyntaxPattern syntaxpattern20 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
        Object aobj43[] = new Object[1];
        aobj43[0] = Lit57;
        asyntaxrule5[3] = new SyntaxRule(syntaxpattern20, "\001\001", "\021\030\004\t\003\b\013", aobj43, 0);
        Lit1 = new SyntaxRules(aobj37, asyntaxrule5, 5);
        $instance = new prim_syntax();
        define$Mnsyntax = Macro.make(Lit0, Lit1, $instance);
        define = Macro.make(Lit2, Lit3, $instance);
        define$Mnprivate = Macro.make(Lit4, Lit5, $instance);
        define$Mnconstant = Macro.make(Lit6, Lit7, $instance);
        prim_syntax prim_syntax1 = $instance;
        syntax$Mnerror = new ModuleMethod(prim_syntax1, 1, Lit8, -4095);
        syntax$Mn$Grexpression = Macro.make(Lit9, Lit10, $instance);
        syntax$Mnbody$Mn$Grexpression = Macro.make(Lit11, Lit12, $instance);
        SimpleSymbol simplesymbol6 = Lit13;
        ModuleMethod modulemethod = new ModuleMethod(prim_syntax1, 2, null, 4097);
        modulemethod.setProperty("source-location", "prim_syntax.scm:69");
        _fldif = Macro.make(simplesymbol6, modulemethod, $instance);
        SimpleSymbol simplesymbol7 = Lit25;
        ModuleMethod modulemethod1 = new ModuleMethod(prim_syntax1, 3, null, 4097);
        modulemethod1.setProperty("source-location", "prim_syntax.scm:89");
        try$Mncatch = Macro.make(simplesymbol7, modulemethod1, $instance);
        SimpleSymbol simplesymbol8 = Lit29;
        ModuleMethod modulemethod2 = new ModuleMethod(prim_syntax1, 4, null, 4097);
        modulemethod2.setProperty("source-location", "prim_syntax.scm:98");
        letrec = Macro.make(simplesymbol8, modulemethod2, $instance);
        $instance.run();
    }
}
