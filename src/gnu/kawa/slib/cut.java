// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;

public class cut extends ModuleBody
{

    public static final Macro $Prvt$srfi$Mn26$Mninternal$Mncut;
    public static final Macro $Prvt$srfi$Mn26$Mninternal$Mncute;
    public static final cut $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxRules Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit2;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit4;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit6;
    static final SyntaxRules Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final Macro cut;
    public static final Macro cute;

    public cut()
    {
        ModuleInfo.register(this);
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit14 = (SimpleSymbol)(new SimpleSymbol("rest-slot")).readResolve();
        Lit13 = (SimpleSymbol)(new SimpleSymbol("apply")).readResolve();
        Lit12 = (SimpleSymbol)(new SimpleSymbol("<>")).readResolve();
        Lit11 = (SimpleSymbol)(new SimpleSymbol("x")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("lambda")).readResolve();
        Lit9 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
        Lit8 = (SimpleSymbol)(new SimpleSymbol("<...>")).readResolve();
        Object aobj[] = new Object[1];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("cute")).readResolve();
        Lit6 = simplesymbol;
        aobj[0] = simplesymbol;
        SyntaxRule asyntaxrule[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern = new SyntaxPattern("\f\030\003", new Object[0], 1);
        Object aobj1[] = new Object[1];
        SimpleSymbol simplesymbol1 = (SimpleSymbol)(new SimpleSymbol("srfi-26-internal-cute")).readResolve();
        Lit2 = simplesymbol1;
        aobj1[0] = simplesymbol1;
        asyntaxrule[0] = new SyntaxRule(syntaxpattern, "\0", "\021\030\004\t\020\t\020\t\020\002", aobj1, 0);
        Lit7 = new SyntaxRules(aobj, asyntaxrule, 1);
        Object aobj2[] = new Object[1];
        SimpleSymbol simplesymbol2 = (SimpleSymbol)(new SimpleSymbol("cut")).readResolve();
        Lit4 = simplesymbol2;
        aobj2[0] = simplesymbol2;
        SyntaxRule asyntaxrule1[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern1 = new SyntaxPattern("\f\030\003", new Object[0], 1);
        Object aobj3[] = new Object[1];
        SimpleSymbol simplesymbol3 = (SimpleSymbol)(new SimpleSymbol("srfi-26-internal-cut")).readResolve();
        Lit0 = simplesymbol3;
        aobj3[0] = simplesymbol3;
        asyntaxrule1[0] = new SyntaxRule(syntaxpattern1, "\0", "\021\030\004\t\020\t\020\002", aobj3, 0);
        Lit5 = new SyntaxRules(aobj2, asyntaxrule1, 1);
        Object aobj4[] = new Object[3];
        aobj4[0] = Lit2;
        aobj4[1] = Lit12;
        aobj4[2] = Lit8;
        SyntaxRule asyntaxrule2[] = new SyntaxRule[4];
        SyntaxPattern syntaxpattern2 = new SyntaxPattern("\f\030,\r\007\000\b\b\f\017<\f\027\r\037\030\b\b\b", new Object[0], 4);
        Object aobj5[] = new Object[2];
        aobj5[0] = Lit9;
        aobj5[1] = Lit10;
        asyntaxrule2[0] = new SyntaxRule(syntaxpattern2, "\003\001\001\003", "\021\030\004\t\013\b\021\030\f\031\b\005\003\b\t\023\b\035\033", aobj5, 1);
        Object aobj6[] = new Object[1];
        aobj6[0] = Lit8;
        SyntaxPattern syntaxpattern3 = new SyntaxPattern("\f\030,\r\007\000\b\b\f\017<\f\027\r\037\030\b\b\f\002\b", aobj6, 4);
        Object aobj7[] = new Object[5];
        aobj7[0] = Lit9;
        aobj7[1] = Lit10;
        aobj7[2] = Lit11;
        aobj7[3] = Lit13;
        aobj7[4] = PairWithPosition.make(Lit11, LList.Empty, "cut.scm", 0x57048);
        asyntaxrule2[1] = new SyntaxRule(syntaxpattern3, "\003\001\001\003", "\021\030\004\t\013\b\021\030\f)\021\005\003\030\024\b\021\030\034\t\023\021\035\033\030$", aobj7, 1);
        Object aobj8[] = new Object[1];
        aobj8[0] = Lit12;
        SyntaxPattern syntaxpattern4 = new SyntaxPattern("\f\030,\r\007\000\b\b\f\017,\r\027\020\b\b\f\002\033", aobj8, 4);
        Object aobj9[] = new Object[3];
        aobj9[0] = Lit2;
        aobj9[1] = PairWithPosition.make(Lit11, LList.Empty, "cut.scm", 0x5d016);
        aobj9[2] = PairWithPosition.make(Lit11, LList.Empty, "cut.scm", 0x5d03b);
        asyntaxrule2[2] = new SyntaxRule(syntaxpattern4, "\003\001\003\0", "\021\030\004)\021\005\003\030\f\t\013)\021\025\023\030\024\032", aobj9, 1);
        SyntaxPattern syntaxpattern5 = new SyntaxPattern("\f\030\f\007\f\017,\r\027\020\b\b\f\037#", new Object[0], 5);
        Object aobj10[] = new Object[3];
        aobj10[0] = Lit2;
        aobj10[1] = Lit11;
        aobj10[2] = PairWithPosition.make(Lit11, LList.Empty, "cut.scm", 0x62039);
        asyntaxrule2[3] = new SyntaxRule(syntaxpattern5, "\001\001\003\001\0", "\021\030\004\t\0039)\021\030\f\b\033\013)\021\025\023\030\024\"", aobj10, 1);
        Lit3 = new SyntaxRules(aobj4, asyntaxrule2, 5);
        Object aobj11[] = new Object[3];
        aobj11[0] = Lit0;
        aobj11[1] = Lit12;
        aobj11[2] = Lit8;
        SyntaxRule asyntaxrule3[] = new SyntaxRule[4];
        SyntaxPattern syntaxpattern6 = new SyntaxPattern("\f\030,\r\007\000\b\b<\f\017\r\027\020\b\b\b", new Object[0], 3);
        Object aobj12[] = new Object[2];
        aobj12[0] = Lit10;
        aobj12[1] = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
        asyntaxrule3[0] = new SyntaxRule(syntaxpattern6, "\003\001\003", "\021\030\004\031\b\005\003\b)\021\030\f\b\013\b\025\023", aobj12, 1);
        Object aobj13[] = new Object[1];
        aobj13[0] = Lit8;
        SyntaxPattern syntaxpattern7 = new SyntaxPattern("\f\030,\r\007\000\b\b<\f\017\r\027\020\b\b\f\002\b", aobj13, 3);
        Object aobj14[] = new Object[4];
        aobj14[0] = Lit10;
        aobj14[1] = Lit14;
        aobj14[2] = Lit13;
        aobj14[3] = PairWithPosition.make(Lit14, LList.Empty, "cut.scm", 0x3d03e);
        asyntaxrule3[1] = new SyntaxRule(syntaxpattern7, "\003\001\003", "\021\030\004)\021\005\003\030\f\b\021\030\024\t\013\021\025\023\030\034", aobj14, 1);
        Object aobj15[] = new Object[1];
        aobj15[0] = Lit12;
        SyntaxPattern syntaxpattern8 = new SyntaxPattern("\f\030,\r\007\000\b\b,\r\017\b\b\b\f\002\023", aobj15, 3);
        Object aobj16[] = new Object[3];
        aobj16[0] = Lit0;
        aobj16[1] = PairWithPosition.make(Lit11, LList.Empty, "cut.scm", 0x4102b);
        aobj16[2] = PairWithPosition.make(Lit11, LList.Empty, "cut.scm", 0x4103c);
        asyntaxrule3[2] = new SyntaxRule(syntaxpattern8, "\003\003\0", "\021\030\004)\021\005\003\030\f)\021\r\013\030\024\022", aobj16, 1);
        SyntaxPattern syntaxpattern9 = new SyntaxPattern("\f\030,\r\007\000\b\b,\r\017\b\b\b\f\027\033", new Object[0], 4);
        Object aobj17[] = new Object[1];
        aobj17[0] = Lit0;
        asyntaxrule3[3] = new SyntaxRule(syntaxpattern9, "\003\003\001\0", "\021\030\004\031\b\005\003)\021\r\013\b\023\032", aobj17, 1);
        Lit1 = new SyntaxRules(aobj11, asyntaxrule3, 4);
        $instance = new cut();
        $Prvt$srfi$Mn26$Mninternal$Mncut = Macro.make(Lit0, Lit1, $instance);
        $Prvt$srfi$Mn26$Mninternal$Mncute = Macro.make(Lit2, Lit3, $instance);
        cut = Macro.make(Lit4, Lit5, $instance);
        cute = Macro.make(Lit6, Lit7, $instance);
        $instance.run();
    }
}
