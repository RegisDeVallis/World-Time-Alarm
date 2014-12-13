// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.LocationProc;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.WrongType;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.standard.Scheme;

public class parameters extends ModuleBody
{

    public static final ModuleMethod $Prvt$as$Mnlocation$Pc;
    public static final Macro $Prvt$parameterize$Pc;
    public static final parameters $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit2;
    static final SyntaxRules Lit3;
    static final SimpleSymbol Lit4;
    static final SyntaxRules Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod make$Mnparameter;
    public static final Macro parameterize;

    public parameters()
    {
        ModuleInfo.register(this);
    }

    public static Location asLocation$Pc(Object obj)
    {
        Object obj1;
        if (obj instanceof LocationProc)
        {
            ThreadLocation threadlocation;
            LocationProc locationproc;
            try
            {
                locationproc = (LocationProc)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "gnu.mapping.LocationProc.getLocation()", 1, obj);
            }
            obj1 = locationproc.getLocation();
        } else
        {
            obj1 = (Location)obj;
        }
        if (obj1 instanceof ThreadLocation)
        {
            try
            {
                threadlocation = (ThreadLocation)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "gnu.mapping.ThreadLocation.getLocation()", 1, obj1);
            }
            obj1 = threadlocation.getLocation();
        }
        return (Location)obj1;
    }

    public static LocationProc makeParameter(Object obj)
    {
        return makeParameter(obj, null);
    }

    public static LocationProc makeParameter(Object obj, Object obj1)
    {
        if (obj1 != null)
        {
            obj = Scheme.applyToArgs.apply2(obj1, obj);
        }
        ThreadLocation threadlocation = new ThreadLocation();
        threadlocation.setGlobal(obj);
        Procedure procedure;
        try
        {
            procedure = (Procedure)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "gnu.mapping.LocationProc.<init>(gnu.mapping.Location,gnu.mapping.Procedure)", 2, obj1);
        }
        return new LocationProc(threadlocation, procedure);
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        case 2: // '\002'
        default:
            return super.apply1(modulemethod, obj);

        case 1: // '\001'
            return makeParameter(obj);

        case 3: // '\003'
            return asLocation$Pc(obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 1)
        {
            return makeParameter(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 2: // '\002'
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 3: // '\003'
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
        Lit12 = (SimpleSymbol)(new SimpleSymbol("save")).readResolve();
        Lit11 = (SimpleSymbol)(new SimpleSymbol("quasiquote")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("gnu.mapping.Location")).readResolve();
        Lit9 = (SimpleSymbol)(new SimpleSymbol("$lookup$")).readResolve();
        Lit8 = (SimpleSymbol)(new SimpleSymbol("v")).readResolve();
        Lit7 = (SimpleSymbol)(new SimpleSymbol("p")).readResolve();
        Lit6 = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
        Object aobj[] = new Object[1];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("parameterize")).readResolve();
        Lit4 = simplesymbol;
        aobj[0] = simplesymbol;
        SyntaxRule asyntaxrule[] = new SyntaxRule[2];
        SyntaxPattern syntaxpattern = new SyntaxPattern("\f\030\f\b\003", new Object[0], 1);
        Object aobj1[] = new Object[1];
        aobj1[0] = Lit6;
        asyntaxrule[0] = new SyntaxRule(syntaxpattern, "\0", "\021\030\004\002", aobj1, 0);
        SyntaxPattern syntaxpattern1 = new SyntaxPattern("\f\030<,\f\007\f\017\b\023\033", new Object[0], 4);
        Object aobj2[] = new Object[1];
        SimpleSymbol simplesymbol1 = (SimpleSymbol)(new SimpleSymbol("parameterize%")).readResolve();
        Lit2 = simplesymbol1;
        aobj2[0] = simplesymbol1;
        asyntaxrule[1] = new SyntaxRule(syntaxpattern1, "\001\001\000\0", "\021\030\0041!\t\003\b\013\022\t\020\032", aobj2, 0);
        Lit5 = new SyntaxRules(aobj, asyntaxrule, 4);
        Object aobj3[] = new Object[1];
        aobj3[0] = Lit2;
        SyntaxRule asyntaxrule1[] = new SyntaxRule[2];
        SyntaxPattern syntaxpattern2 = new SyntaxPattern("\f\030\f\b\f\007\013", new Object[0], 2);
        Object aobj4[] = new Object[2];
        aobj4[0] = (SimpleSymbol)(new SimpleSymbol("try-finally")).readResolve();
        aobj4[1] = Lit6;
        asyntaxrule1[0] = new SyntaxRule(syntaxpattern2, "\001\0", "\021\030\004!\021\030\f\n\b\021\030\f\003", aobj4, 0);
        SyntaxPattern syntaxpattern3 = new SyntaxPattern("\f\030<,\f\007\f\017\b\023\f\037#", new Object[0], 5);
        Object aobj5[] = new Object[9];
        aobj5[0] = (SimpleSymbol)(new SimpleSymbol("let*")).readResolve();
        aobj5[1] = Lit7;
        aobj5[2] = (SimpleSymbol)(new SimpleSymbol("::")).readResolve();
        aobj5[3] = (SimpleSymbol)(new SimpleSymbol("<gnu.mapping.Location>")).readResolve();
        SimpleSymbol simplesymbol2 = (SimpleSymbol)(new SimpleSymbol("as-location%")).readResolve();
        Lit1 = simplesymbol2;
        aobj5[4] = simplesymbol2;
        aobj5[5] = Lit8;
        aobj5[6] = PairWithPosition.make(PairWithPosition.make(Lit12, PairWithPosition.make(PairWithPosition.make(PairWithPosition.make(Lit9, Pair.make(Lit10, Pair.make(Pair.make(Lit11, Pair.make((SimpleSymbol)(new SimpleSymbol("setWithSave")).readResolve(), LList.Empty)), LList.Empty)), "parameters.scm", 0x1e00d), PairWithPosition.make(Lit7, PairWithPosition.make(Lit8, LList.Empty, "parameters.scm", 0x1e030), "parameters.scm", 0x1e02e), "parameters.scm", 0x1e00c), LList.Empty, "parameters.scm", 0x1e00c), "parameters.scm", 0x1e006), LList.Empty, "parameters.scm", 0x1e006);
        aobj5[7] = Lit2;
        aobj5[8] = PairWithPosition.make(PairWithPosition.make(Lit9, Pair.make(Lit10, Pair.make(Pair.make(Lit11, Pair.make((SimpleSymbol)(new SimpleSymbol("setRestore")).readResolve(), LList.Empty)), LList.Empty)), "parameters.scm", 0x2000b), PairWithPosition.make(Lit7, PairWithPosition.make(Lit12, LList.Empty, "parameters.scm", 0x2002d), "parameters.scm", 0x2002b), "parameters.scm", 0x2000a);
        asyntaxrule1[1] = new SyntaxRule(syntaxpattern3, "\001\001\000\001\0", "\021\030\004\301y\021\030\f\021\030\024\021\030\034\b\021\030$\b\003)\021\030,\b\013\0304\b\021\030<\t\022!\021\030D\033\"", aobj5, 0);
        Lit3 = new SyntaxRules(aobj3, asyntaxrule1, 5);
        Lit0 = (SimpleSymbol)(new SimpleSymbol("make-parameter")).readResolve();
        $instance = new parameters();
        parameters parameters1 = $instance;
        make$Mnparameter = new ModuleMethod(parameters1, 1, Lit0, 8193);
        $Prvt$as$Mnlocation$Pc = new ModuleMethod(parameters1, 3, Lit1, 4097);
        $Prvt$parameterize$Pc = Macro.make(Lit2, Lit3, $instance);
        parameterize = Macro.make(Lit4, Lit5, $instance);
        $instance.run();
    }
}
