// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.PrimProcedure;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;

public class trace extends ModuleBody
{

    public static final Macro $Pcdo$Mntrace;
    public static final trace $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxRules Lit1;
    static final SimpleSymbol Lit2;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit4;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    public static final ModuleMethod disassemble;
    public static final Macro trace;
    public static final Macro untrace;

    public trace()
    {
        ModuleInfo.register(this);
    }

    public static Object disassemble(Procedure procedure)
    {
        CallContext callcontext;
        int i;
        callcontext = CallContext.getInstance();
        i = callcontext.startFromContext();
        PrimProcedure.disassemble$X(procedure, callcontext);
        return callcontext.getFromContext(i);
        Exception exception;
        exception;
        callcontext.cleanupFromContext(i);
        throw exception;
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        if (modulemethod.selector == 1)
        {
            Procedure procedure;
            try
            {
                procedure = (Procedure)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "disassemble", 1, obj);
            }
            return disassemble(procedure);
        } else
        {
            return super.apply1(modulemethod, obj);
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        if (modulemethod.selector == 1)
        {
            if (!(obj instanceof Procedure))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }
        } else
        {
            return super.match1(modulemethod, obj, callcontext);
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit7 = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
        Lit6 = (SimpleSymbol)(new SimpleSymbol("disassemble")).readResolve();
        Object aobj[] = new Object[1];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("untrace")).readResolve();
        Lit4 = simplesymbol;
        aobj[0] = simplesymbol;
        SyntaxRule asyntaxrule[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
        Object aobj1[] = new Object[3];
        aobj1[0] = Lit7;
        SimpleSymbol simplesymbol1 = (SimpleSymbol)(new SimpleSymbol("%do-trace")).readResolve();
        Lit0 = simplesymbol1;
        aobj1[1] = simplesymbol1;
        aobj1[2] = PairWithPosition.make(Boolean.FALSE, LList.Empty, "trace.scm", 0x1301b);
        asyntaxrule[0] = new SyntaxRule(syntaxpattern, "\003", "\021\030\004\b\005\021\030\f\t\003\030\024", aobj1, 1);
        Lit5 = new SyntaxRules(aobj, asyntaxrule, 1);
        Object aobj2[] = new Object[1];
        SimpleSymbol simplesymbol2 = (SimpleSymbol)(new SimpleSymbol("trace")).readResolve();
        Lit2 = simplesymbol2;
        aobj2[0] = simplesymbol2;
        SyntaxRule asyntaxrule1[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern1 = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
        Object aobj3[] = new Object[3];
        aobj3[0] = Lit7;
        aobj3[1] = Lit0;
        aobj3[2] = PairWithPosition.make(Boolean.TRUE, LList.Empty, "trace.scm", 57371);
        asyntaxrule1[0] = new SyntaxRule(syntaxpattern1, "\003", "\021\030\004\b\005\021\030\f\t\003\030\024", aobj3, 1);
        Lit3 = new SyntaxRules(aobj2, asyntaxrule1, 1);
        Object aobj4[] = new Object[1];
        aobj4[0] = Lit0;
        SyntaxRule asyntaxrule2[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern2 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
        Object aobj5[] = new Object[4];
        aobj5[0] = (SimpleSymbol)(new SimpleSymbol("set!")).readResolve();
        aobj5[1] = (SimpleSymbol)(new SimpleSymbol("invoke-static")).readResolve();
        aobj5[2] = (SimpleSymbol)(new SimpleSymbol("<kawa.standard.TracedProcedure>")).readResolve();
        aobj5[3] = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("quote")).readResolve(), PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("doTrace")).readResolve(), LList.Empty, "trace.scm", 32806), "trace.scm", 32806);
        asyntaxrule2[0] = new SyntaxRule(syntaxpattern2, "\001\001", "\021\030\004\t\003\b\021\030\f\021\030\024\021\030\034\t\003\b\013", aobj5, 0);
        Lit1 = new SyntaxRules(aobj4, asyntaxrule2, 2);
        $instance = new trace();
        $Pcdo$Mntrace = Macro.make(Lit0, Lit1, $instance);
        trace = Macro.make(Lit2, Lit3, $instance);
        untrace = Macro.make(Lit4, Lit5, $instance);
        disassemble = new ModuleMethod($instance, 1, Lit6, 4097);
        $instance.run();
    }
}
