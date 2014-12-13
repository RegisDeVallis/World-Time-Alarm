// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrongType;
import kawa.lib.numbers;
import kawa.lib.strings;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            srfi13

public class ception extends ModuleBody
{

    final ModuleMethod lambda$Fn161 = new ModuleMethod(this, 138, null, 0);
    final ModuleMethod lambda$Fn162 = new ModuleMethod(this, 139, null, 8194);
    Object n;
    Object s;

    static boolean lambda163(Object obj)
    {
        boolean flag = numbers.isInteger(obj);
        if (flag)
        {
            flag = numbers.isExact(obj);
            if (flag)
            {
                flag = ((Boolean)Scheme.numLEq.apply2(srfi13.Lit0, obj)).booleanValue();
            }
        }
        return flag;
    }

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 138)
        {
            return lambda161();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 139)
        {
            return lambda162(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda161()
    {
        ModuleMethod modulemethod = srfi13.string$Mnpad$Mnright;
        Object obj = s;
        Location location = srfi13.loc$rest;
        Object obj1;
        try
        {
            obj1 = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("srfi13.scm", 1046, 58);
            throw unboundlocationexception;
        }
        return srfi13.stringParseFinalStart$PlEnd(modulemethod, obj, obj1);
    }

    Object lambda162(Object obj, Object obj1)
    {
        gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
        Location location = srfi13.loc$check$Mnarg;
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        try
        {
            obj2 = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("srfi13.scm", 1047, 7);
            throw unboundlocationexception;
        }
        applytoargs.apply4(obj2, srfi13.lambda$Fn163, n, srfi13.string$Mnpad$Mnright);
        obj3 = AddOp.$Mn.apply2(obj1, obj);
        if (Scheme.numLEq.apply2(n, obj3) != Boolean.FALSE)
        {
            Object obj6 = s;
            int i;
            CharSequence charsequence;
            CharSequence charsequence1;
            int j;
            int k;
            CharSequence charsequence2;
            int l;
            Object obj7;
            int i1;
            try
            {
                charsequence2 = (CharSequence)obj6;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "%substring/shared", 0, obj6);
            }
            try
            {
                l = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "%substring/shared", 1, obj);
            }
            obj7 = AddOp.$Pl.apply2(obj, n);
            try
            {
                i1 = ((Number)obj7).intValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "%substring/shared", 2, obj7);
            }
            return srfi13.$PcSubstring$SlShared(charsequence2, l, i1);
        }
        obj4 = n;
        try
        {
            i = ((Number)obj4).intValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "make-string", 1, obj4);
        }
        charsequence = strings.makeString(i, LangPrimType.charType);
        obj5 = s;
        try
        {
            charsequence1 = (CharSequence)obj5;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "%string-copy!", 2, obj5);
        }
        try
        {
            j = ((Number)obj).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "%string-copy!", 3, obj);
        }
        try
        {
            k = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "%string-copy!", 4, obj1);
        }
        srfi13.$PcStringCopy$Ex(charsequence, 0, charsequence1, j, k);
        return charsequence;
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 138)
        {
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;
        } else
        {
            return super.match0(modulemethod, callcontext);
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        if (modulemethod.selector == 139)
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

    public ception()
    {
    }
}
