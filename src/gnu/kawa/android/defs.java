// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.android;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import kawa.lang.Macro;
import kawa.lang.Pattern;
import kawa.lang.Quote;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lib.lists;
import kawa.standard.syntax_case;

public class defs extends ModuleBody
{

    public static final ModuleMethod $Pcprocess$Mnactivity;
    public static final defs $instance;
    static final SyntaxPattern Lit0;
    static final SyntaxTemplate Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SyntaxPattern Lit12 = new SyntaxPattern("\f\007\f\017\023", new Object[0], 3);
    static final SyntaxTemplate Lit13;
    static final SyntaxTemplate Lit14;
    static final SyntaxTemplate Lit15 = new SyntaxTemplate("\001\001\0", "\022", new Object[0], 0);
    static final SyntaxTemplate Lit16 = new SyntaxTemplate("\001\001\0", "\020", new Object[0], 0);
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SyntaxTemplate Lit2 = new SyntaxTemplate("\003\0", "\n", new Object[0], 0);
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SyntaxPattern Lit3;
    static final SyntaxTemplate Lit4;
    static final SyntaxTemplate Lit5 = new SyntaxTemplate("\003\001\0", "\022", new Object[0], 0);
    static final SyntaxPattern Lit6 = new SyntaxPattern("\f\007\013", new Object[0], 2);
    static final SyntaxTemplate Lit7 = new SyntaxTemplate("\001\0", "\003", new Object[0], 0);
    static final SyntaxTemplate Lit8 = new SyntaxTemplate("\001\0", "\n", new Object[0], 0);
    static final SyntaxPattern Lit9 = new SyntaxPattern("\b", new Object[0], 0);
    public static final Macro activity;

    public static Object $PcProcessActivity(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(3, null);
        if (Lit0.match(obj, aobj, 0))
        {
            TemplateScope templatescope4 = TemplateScope.make();
            Object obj3 = Lit1.execute(aobj, templatescope4);
            TemplateScope templatescope5 = TemplateScope.make();
            return lists.cons(obj3, $PcProcessActivity(Lit2.execute(aobj, templatescope5)));
        }
        if (Lit3.match(obj, aobj, 0))
        {
            TemplateScope templatescope2 = TemplateScope.make();
            Object obj2 = Lit4.execute(aobj, templatescope2);
            TemplateScope templatescope3 = TemplateScope.make();
            return lists.cons(obj2, $PcProcessActivity(Lit5.execute(aobj, templatescope3)));
        }
        if (Lit6.match(obj, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            Object obj1 = Lit7.execute(aobj, templatescope);
            TemplateScope templatescope1 = TemplateScope.make();
            return lists.cons(obj1, $PcProcessActivity(Lit8.execute(aobj, templatescope1)));
        }
        if (Lit9.match(obj, aobj, 0))
        {
            return LList.Empty;
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    public defs()
    {
        ModuleInfo.register(this);
    }

    static Object lambda1(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(3, null);
        if (Lit12.match(obj, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            Object aobj1[] = new Object[2];
            aobj1[0] = Lit13.execute(aobj, templatescope);
            Object obj1 = Lit14.execute(aobj, templatescope);
            Object aobj2[] = new Object[2];
            aobj2[0] = $PcProcessActivity(Lit15.execute(aobj, templatescope));
            aobj2[1] = Lit16.execute(aobj, templatescope);
            aobj1[1] = Pair.make(obj1, Quote.append$V(aobj2));
            return Quote.append$V(aobj1);
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply1(modulemethod, obj);

        case 1: // '\001'
            return $PcProcessActivity(obj);

        case 2: // '\002'
            return lambda1(obj);
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
        Lit25 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
        Lit24 = (SimpleSymbol)(new SimpleSymbol("invoke-special")).readResolve();
        Lit23 = (SimpleSymbol)(new SimpleSymbol("void")).readResolve();
        Lit22 = (SimpleSymbol)(new SimpleSymbol("android.os.Bundle")).readResolve();
        Lit21 = (SimpleSymbol)(new SimpleSymbol("this")).readResolve();
        Lit20 = (SimpleSymbol)(new SimpleSymbol("savedInstanceState")).readResolve();
        Lit19 = (SimpleSymbol)(new SimpleSymbol("onCreate")).readResolve();
        Lit18 = (SimpleSymbol)(new SimpleSymbol("android.app.Activity")).readResolve();
        Lit17 = (SimpleSymbol)(new SimpleSymbol("::")).readResolve();
        Object aobj[] = new Object[1];
        aobj[0] = PairWithPosition.make(Lit18, LList.Empty, "defs.scm", 0x15020);
        Lit14 = new SyntaxTemplate("\001\001\0", "\030\004", aobj, 0);
        Object aobj1[] = new Object[1];
        aobj1[0] = (SimpleSymbol)(new SimpleSymbol("define-simple-class")).readResolve();
        Lit13 = new SyntaxTemplate("\001\001\0", "\021\030\004\b\013", aobj1, 0);
        Lit11 = (SimpleSymbol)(new SimpleSymbol("activity")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("%process-activity")).readResolve();
        Object aobj2[] = new Object[5];
        aobj2[0] = PairWithPosition.make(Lit19, PairWithPosition.make(PairWithPosition.make(Lit20, PairWithPosition.make(Lit17, PairWithPosition.make(Lit22, LList.Empty, "defs.scm", 36913), "defs.scm", 36910), "defs.scm", 36890), LList.Empty, "defs.scm", 36890), "defs.scm", 36880);
        aobj2[1] = Lit17;
        aobj2[2] = Lit23;
        aobj2[3] = PairWithPosition.make(Lit24, PairWithPosition.make(Lit18, PairWithPosition.make(PairWithPosition.make(Lit21, LList.Empty, "defs.scm", 41006), PairWithPosition.make(PairWithPosition.make(Lit25, PairWithPosition.make(Lit19, LList.Empty, "defs.scm", 41014), "defs.scm", 41014), PairWithPosition.make(Lit20, LList.Empty, "defs.scm", 41023), "defs.scm", 41013), "defs.scm", 41006), "defs.scm", 40985), "defs.scm", 40969);
        aobj2[4] = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("$lookup$")).readResolve(), Pair.make(PairWithPosition.make(Lit21, LList.Empty, "defs.scm", 49162), Pair.make(Pair.make((SimpleSymbol)(new SimpleSymbol("quasiquote")).readResolve(), Pair.make((SimpleSymbol)(new SimpleSymbol("setContentView")).readResolve(), LList.Empty)), LList.Empty)), "defs.scm", 49162);
        Lit4 = new SyntaxTemplate("\003\001\0", "\021\030\004\021\030\f\021\030\024\021\030\034\021\005\003\b\021\030$\b\013", aobj2, 1);
        Object aobj3[] = new Object[1];
        aobj3[0] = (SimpleSymbol)(new SimpleSymbol("on-create-view")).readResolve();
        Lit3 = new SyntaxPattern("T\f\002\r\007\000\b\026\f\017\b\023", aobj3, 3);
        Object aobj4[] = new Object[4];
        aobj4[0] = PairWithPosition.make(Lit19, PairWithPosition.make(PairWithPosition.make(Lit20, PairWithPosition.make(Lit17, PairWithPosition.make(Lit22, LList.Empty, "defs.scm", 16433), "defs.scm", 16430), "defs.scm", 16410), LList.Empty, "defs.scm", 16410), "defs.scm", 16400);
        aobj4[1] = Lit17;
        aobj4[2] = Lit23;
        aobj4[3] = PairWithPosition.make(Lit24, PairWithPosition.make(Lit18, PairWithPosition.make(PairWithPosition.make(Lit21, LList.Empty, "defs.scm", 20526), PairWithPosition.make(PairWithPosition.make(Lit25, PairWithPosition.make(Lit19, LList.Empty, "defs.scm", 20534), "defs.scm", 20534), PairWithPosition.make(Lit20, LList.Empty, "defs.scm", 20543), "defs.scm", 20533), "defs.scm", 20526), "defs.scm", 20505), "defs.scm", 20489);
        Lit1 = new SyntaxTemplate("\003\0", "\021\030\004\021\030\f\021\030\024\021\030\034\b\005\003", aobj4, 1);
        Object aobj5[] = new Object[1];
        aobj5[0] = (SimpleSymbol)(new SimpleSymbol("on-create")).readResolve();
        Lit0 = new SyntaxPattern("<\f\002\r\007\000\b\b\013", aobj5, 2);
        $instance = new defs();
        defs defs1 = $instance;
        $Pcprocess$Mnactivity = new ModuleMethod(defs1, 1, Lit10, 4097);
        activity = Macro.make(Lit11, new ModuleMethod(defs1, 2, null, 4097), $instance);
        $instance.run();
    }
}
