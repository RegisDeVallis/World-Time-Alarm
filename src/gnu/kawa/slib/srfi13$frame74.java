// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.lists.CharSeq;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrongType;
import gnu.text.Char;
import kawa.lib.strings;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            srfi13

public class lambda.Fn171 extends ModuleBody
{

    CharSequence ans;
    Object cset;
    final ModuleMethod lambda$Fn169;
    final ModuleMethod lambda$Fn170;
    final ModuleMethod lambda$Fn171;
    _cls2 staticLink;
    CharSequence temp;

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 142: 
            return lambda169(obj, obj1);

        case 143: 
            return lambda170(obj, obj1);

        case 144: 
            return lambda171(obj, obj1);
        }
    }

    Object lambda169(Object obj, Object obj1)
    {
        if (Scheme.applyToArgs.apply2(staticLink.criterion, obj) != Boolean.FALSE)
        {
            return obj1;
        }
        CharSequence charsequence = temp;
        CharSeq charseq;
        int i;
        char c;
        try
        {
            charseq = (CharSeq)charsequence;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-set!", 1, charsequence);
        }
        try
        {
            i = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-set!", 2, obj1);
        }
        try
        {
            c = ((Char)obj).charValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "string-set!", 3, obj);
        }
        strings.stringSet$Ex(charseq, i, c);
        return AddOp.$Pl.apply2(obj1, srfi13.Lit1);
    }

    Object lambda170(Object obj, Object obj1)
    {
        gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
        Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
        Object obj2;
        try
        {
            obj2 = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("srfi13.scm", 1099, 45);
            throw unboundlocationexception;
        }
        if (applytoargs.apply3(obj2, cset, obj) != Boolean.FALSE)
        {
            return obj1;
        } else
        {
            return AddOp.$Pl.apply2(obj1, srfi13.Lit1);
        }
    }

    Object lambda171(Object obj, Object obj1)
    {
        gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
        Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
        Object obj2;
        CharSequence charsequence;
        CharSeq charseq;
        int i;
        char c;
        try
        {
            obj2 = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("srfi13.scm", 1104, 35);
            throw unboundlocationexception;
        }
        if (applytoargs.apply3(obj2, cset, obj) != Boolean.FALSE)
        {
            return obj1;
        }
        charsequence = ans;
        try
        {
            charseq = (CharSeq)charsequence;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-set!", 1, charsequence);
        }
        try
        {
            i = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-set!", 2, obj1);
        }
        try
        {
            c = ((Char)obj).charValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "string-set!", 3, obj);
        }
        strings.stringSet$Ex(charseq, i, c);
        return AddOp.$Pl.apply2(obj1, srfi13.Lit1);
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match2(modulemethod, obj, obj1, callcontext);

        case 144: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 143: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 142: 
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        }
    }

    public ception()
    {
        ModuleMethod modulemethod = new ModuleMethod(this, 142, null, 8194);
        modulemethod.setProperty("source-location", "srfi13.scm:1089");
        lambda$Fn169 = modulemethod;
        ModuleMethod modulemethod1 = new ModuleMethod(this, 143, null, 8194);
        modulemethod1.setProperty("source-location", "srfi13.scm:1099");
        lambda$Fn170 = modulemethod1;
        ModuleMethod modulemethod2 = new ModuleMethod(this, 144, null, 8194);
        modulemethod2.setProperty("source-location", "srfi13.scm:1104");
        lambda$Fn171 = modulemethod2;
    }
}
