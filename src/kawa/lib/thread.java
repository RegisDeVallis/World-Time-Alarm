// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.mapping.CallContext;
import gnu.mapping.Future;
import gnu.mapping.Procedure;
import gnu.mapping.RunnableClosure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.Quantity;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.standard.sleep;

public class thread extends ModuleBody
{

    public static final ModuleMethod $Prvt$$Pcmake$Mnfuture;
    public static final thread $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SyntaxRules Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    public static final Macro future;
    public static final ModuleMethod runnable;
    public static final ModuleMethod sleep;

    public static Future $PcMakeFuture(Procedure procedure)
    {
        Future future1 = new Future(procedure);
        future1.start();
        return future1;
    }

    public thread()
    {
        ModuleInfo.register(this);
    }

    public static RunnableClosure runnable(Procedure procedure)
    {
        return new RunnableClosure(procedure);
    }

    public static void sleep(Quantity quantity)
    {
        kawa.standard.sleep.sleep(quantity);
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply1(modulemethod, obj);

        case 1: // '\001'
            Procedure procedure;
            Procedure procedure1;
            Quantity quantity;
            try
            {
                quantity = (Quantity)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "sleep", 1, obj);
            }
            sleep(quantity);
            return Values.empty;

        case 2: // '\002'
            try
            {
                procedure1 = (Procedure)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "%make-future", 1, obj);
            }
            return $PcMakeFuture(procedure1);

        case 3: // '\003'
            break;
        }
        try
        {
            procedure = (Procedure)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "runnable", 1, obj);
        }
        return runnable(procedure);
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        int i = 0xfff40001;
        modulemethod.selector;
        JVM INSTR tableswitch 1 3: default 36
    //                   1 96
    //                   2 72
    //                   3 48;
           goto _L1 _L2 _L3 _L4
_L1:
        i = super.match1(modulemethod, obj, callcontext);
_L6:
        return i;
_L4:
        if (obj instanceof Procedure)
        {
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (obj instanceof Procedure)
        {
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (obj instanceof Quantity)
        {
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit4 = (SimpleSymbol)(new SimpleSymbol("runnable")).readResolve();
        Lit3 = (SimpleSymbol)(new SimpleSymbol("%make-future")).readResolve();
        Object aobj[] = new Object[1];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("future")).readResolve();
        Lit1 = simplesymbol;
        aobj[0] = simplesymbol;
        SyntaxRule asyntaxrule[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
        Object aobj1[] = new Object[2];
        aobj1[0] = Lit3;
        aobj1[1] = (SimpleSymbol)(new SimpleSymbol("lambda")).readResolve();
        asyntaxrule[0] = new SyntaxRule(syntaxpattern, "\001", "\021\030\004\b\021\030\f\t\020\b\003", aobj1, 0);
        Lit2 = new SyntaxRules(aobj, asyntaxrule, 1);
        Lit0 = (SimpleSymbol)(new SimpleSymbol("sleep")).readResolve();
        $instance = new thread();
        thread thread1 = $instance;
        sleep = new ModuleMethod(thread1, 1, Lit0, 4097);
        future = Macro.make(Lit1, Lit2, $instance);
        $Prvt$$Pcmake$Mnfuture = new ModuleMethod(thread1, 2, Lit3, 4097);
        runnable = new ModuleMethod(thread1, 3, Lit4, 4097);
        $instance.run();
    }
}
