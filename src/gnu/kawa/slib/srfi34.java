// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.standard.Scheme;

public class srfi34 extends ModuleBody
{
    public class raise.Mnobject.Mnexception extends Throwable
    {

        public Object value;

        public raise.Mnobject.Mnexception(Object obj)
        {
            value = obj;
        }
    }


    public static final Class $Prvt$$Lsraise$Mnobject$Mnexception$Gr = gnu/kawa/slib/raise$Mnobject$Mnexception;
    public static final Macro $Prvt$guard$Mnaux;
    public static final srfi34 $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit2;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit4;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final Macro guard;
    public static final ModuleMethod raise;
    public static final ModuleMethod with$Mnexception$Mnhandler;

    public srfi34()
    {
        ModuleInfo.register(this);
    }

    public static void raise(Object obj)
    {
        throw (Throwable)new raise.Mnobject.Mnexception(obj);
    }

    public static Object withExceptionHandler(Object obj, Object obj1)
    {
        Object obj2;
        try
        {
            obj2 = Scheme.applyToArgs.apply1(obj1);
        }
        catch (raise.Mnobject.Mnexception mnexception)
        {
            return Scheme.applyToArgs.apply2(obj, mnexception.value);
        }
        catch (Throwable throwable)
        {
            return Scheme.applyToArgs.apply2(obj, throwable);
        }
        return obj2;
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        if (modulemethod.selector == 2)
        {
            raise(obj);
            return Values.empty;
        } else
        {
            return super.apply1(modulemethod, obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 1)
        {
            return withExceptionHandler(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        if (modulemethod.selector == 2)
        {
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        } else
        {
            return super.match1(modulemethod, obj, callcontext);
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        if (modulemethod.selector == 1)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        } else
        {
            return super.match2(modulemethod, obj, obj1, callcontext);
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit13 = (SimpleSymbol)(new SimpleSymbol("<raise-object-exception>")).readResolve();
        Lit12 = (SimpleSymbol)(new SimpleSymbol("ex")).readResolve();
        Lit11 = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("if")).readResolve();
        Lit9 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
        Lit8 = (SimpleSymbol)(new SimpleSymbol("temp")).readResolve();
        Lit7 = (SimpleSymbol)(new SimpleSymbol("=>")).readResolve();
        Lit6 = (SimpleSymbol)(new SimpleSymbol("else")).readResolve();
        Object aobj[] = new Object[3];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("guard-aux")).readResolve();
        Lit4 = simplesymbol;
        aobj[0] = simplesymbol;
        aobj[1] = Lit6;
        aobj[2] = Lit7;
        SyntaxRule asyntaxrule[] = new SyntaxRule[7];
        Object aobj1[] = new Object[1];
        aobj1[0] = Lit6;
        SyntaxPattern syntaxpattern = new SyntaxPattern("\f\030\f\007L\f\002\f\017\r\027\020\b\b\b", aobj1, 3);
        Object aobj2[] = new Object[1];
        aobj2[0] = Lit11;
        asyntaxrule[0] = new SyntaxRule(syntaxpattern, "\001\001\003", "\021\030\004\t\013\b\025\023", aobj2, 1);
        Object aobj3[] = new Object[1];
        aobj3[0] = Lit7;
        SyntaxPattern syntaxpattern1 = new SyntaxPattern("\f\030\f\007<\f\017\f\002\f\027\b\b", aobj3, 3);
        Object aobj4[] = new Object[4];
        aobj4[0] = Lit9;
        aobj4[1] = Lit8;
        aobj4[2] = Lit10;
        aobj4[3] = PairWithPosition.make(Lit8, LList.Empty, "srfi34.scm", 0x43014);
        asyntaxrule[1] = new SyntaxRule(syntaxpattern1, "\001\001\001", "\021\030\0041\b\021\030\f\b\013\b\021\030\024\021\030\f!\t\023\030\034\b\003", aobj4, 0);
        Object aobj5[] = new Object[1];
        aobj5[0] = Lit7;
        SyntaxPattern syntaxpattern2 = new SyntaxPattern("\f\030\f\007<\f\017\f\002\f\027\b\f\037\r' \b\b", aobj5, 5);
        Object aobj6[] = new Object[5];
        aobj6[0] = Lit9;
        aobj6[1] = Lit8;
        aobj6[2] = Lit10;
        aobj6[3] = PairWithPosition.make(Lit8, LList.Empty, "srfi34.scm", 0x48014);
        aobj6[4] = Lit4;
        asyntaxrule[2] = new SyntaxRule(syntaxpattern2, "\001\001\001\001\003", "\021\030\0041\b\021\030\f\b\013\b\021\030\024\021\030\f!\t\023\030\034\b\021\030$\t\003\t\033\b%#", aobj6, 1);
        asyntaxrule[3] = new SyntaxRule(new SyntaxPattern("\f\030\f\007\034\f\017\b\b", new Object[0], 2), "\001\001", "\013", new Object[0], 0);
        SyntaxPattern syntaxpattern3 = new SyntaxPattern("\f\030\f\007\034\f\017\b\f\027\r\037\030\b\b", new Object[0], 4);
        Object aobj7[] = new Object[4];
        aobj7[0] = Lit9;
        aobj7[1] = Lit8;
        aobj7[2] = Lit10;
        aobj7[3] = Lit4;
        asyntaxrule[4] = new SyntaxRule(syntaxpattern3, "\001\001\001\003", "\021\030\0041\b\021\030\f\b\013\b\021\030\024\021\030\f\021\030\f\b\021\030\034\t\003\t\023\b\035\033", aobj7, 1);
        SyntaxPattern syntaxpattern4 = new SyntaxPattern("\f\030\f\007L\f\017\f\027\r\037\030\b\b\b", new Object[0], 4);
        Object aobj8[] = new Object[2];
        aobj8[0] = Lit10;
        aobj8[1] = Lit11;
        asyntaxrule[5] = new SyntaxRule(syntaxpattern4, "\001\001\001\003", "\021\030\004\t\013A\021\030\f\t\023\b\035\033\b\003", aobj8, 1);
        SyntaxPattern syntaxpattern5 = new SyntaxPattern("\f\030\f\007L\f\017\f\027\r\037\030\b\b\f'\r/(\b\b", new Object[0], 6);
        Object aobj9[] = new Object[3];
        aobj9[0] = Lit10;
        aobj9[1] = Lit11;
        aobj9[2] = Lit4;
        asyntaxrule[6] = new SyntaxRule(syntaxpattern5, "\001\001\001\003\001\003", "\021\030\004\t\013A\021\030\f\t\023\b\035\033\b\021\030\024\t\003\t#\b-+", aobj9, 1);
        Lit5 = new SyntaxRules(aobj, asyntaxrule, 6);
        Object aobj10[] = new Object[1];
        SimpleSymbol simplesymbol1 = (SimpleSymbol)(new SimpleSymbol("guard")).readResolve();
        Lit2 = simplesymbol1;
        aobj10[0] = simplesymbol1;
        SyntaxRule asyntaxrule1[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern6 = new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3);
        Object aobj11[] = new Object[8];
        aobj11[0] = (SimpleSymbol)(new SimpleSymbol("try-catch")).readResolve();
        aobj11[1] = Lit11;
        aobj11[2] = Lit12;
        aobj11[3] = (SimpleSymbol)(new SimpleSymbol("<java.lang.Throwable>")).readResolve();
        aobj11[4] = Lit9;
        aobj11[5] = PairWithPosition.make(PairWithPosition.make(Lit10, PairWithPosition.make(PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("instance?")).readResolve(), PairWithPosition.make(Lit12, PairWithPosition.make(Lit13, LList.Empty, "srfi34.scm", 0x1b016), "srfi34.scm", 0x1b013), "srfi34.scm", 0x1b008), PairWithPosition.make(PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("field")).readResolve(), PairWithPosition.make(PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("as")).readResolve(), PairWithPosition.make(Lit13, PairWithPosition.make(Lit12, LList.Empty, "srfi34.scm", 0x1c02c), "srfi34.scm", 0x1c013), "srfi34.scm", 0x1c00f), PairWithPosition.make(PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("quote")).readResolve(), PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("value")).readResolve(), LList.Empty, "srfi34.scm", 0x1c031), "srfi34.scm", 0x1c031), LList.Empty, "srfi34.scm", 0x1c030), "srfi34.scm", 0x1c00f), "srfi34.scm", 0x1c008), PairWithPosition.make(Lit12, LList.Empty, "srfi34.scm", 0x1d008), "srfi34.scm", 0x1c008), "srfi34.scm", 0x1b008), "srfi34.scm", 0x1b004), LList.Empty, "srfi34.scm", 0x1b004);
        aobj11[6] = Lit4;
        aobj11[7] = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("primitive-throw")).readResolve(), PairWithPosition.make(Lit12, LList.Empty, "srfi34.scm", 0x1e022), "srfi34.scm", 0x1e011);
        asyntaxrule1[0] = new SyntaxRule(syntaxpattern6, "\001\000\0", "\021\030\004!\021\030\f\022\b\021\030\024\021\030\034\b\021\030$)\b\t\003\030,\b\021\0304\021\030<\n", aobj11, 0);
        Lit3 = new SyntaxRules(aobj10, asyntaxrule1, 3);
        Lit1 = (SimpleSymbol)(new SimpleSymbol("raise")).readResolve();
        Lit0 = (SimpleSymbol)(new SimpleSymbol("with-exception-handler")).readResolve();
        $instance = new srfi34();
        srfi34 srfi34_1 = $instance;
        with$Mnexception$Mnhandler = new ModuleMethod(srfi34_1, 1, Lit0, 8194);
        raise = new ModuleMethod(srfi34_1, 2, Lit1, 4097);
        guard = Macro.make(Lit2, Lit3, $instance);
        $Prvt$guard$Mnaux = Macro.make(Lit4, Lit5, $instance);
        $instance.run();
    }
}
