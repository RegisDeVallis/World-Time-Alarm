// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrongType;
import kawa.lib.lists;
import kawa.lib.strings;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            srfi13

public class ception extends ModuleBody
{

    final ModuleMethod lambda$Fn208 = new ModuleMethod(this, 183, null, 0);
    final ModuleMethod lambda$Fn209 = new ModuleMethod(this, 184, null, 8194);
    Object s;

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 183)
        {
            return lambda208();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 184)
        {
            return lambda209(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda208()
    {
        ModuleMethod modulemethod = srfi13.string$Mntokenize;
        Object obj = s;
        Location location = srfi13.loc$rest;
        Object obj1;
        try
        {
            obj1 = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("srfi13.scm", 1696, 57);
            throw unboundlocationexception;
        }
        return srfi13.stringParseFinalStart$PlEnd(modulemethod, obj, obj1);
    }

    Object lambda209(Object obj, Object obj1)
    {
        Object obj2;
        Object obj3;
        obj2 = LList.Empty;
        obj3 = obj1;
_L2:
        Object obj6;
        Object obj4 = Scheme.numLss.apply2(obj, obj3);
        boolean flag;
        Object obj10;
        try
        {
            flag = ((Boolean)obj4).booleanValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "x", -2, obj4);
        }
        if (flag)
        {
            Object obj13 = s;
            Location location1 = srfi13.loc$token$Mnchars;
            Object obj5;
            Object obj7;
            Location location;
            Object obj8;
            Object obj9;
            CharSequence charsequence;
            int i;
            int j;
            CharSequence charsequence1;
            int k;
            int l;
            Object obj14;
            try
            {
                obj14 = location1.get();
            }
            catch (UnboundLocationException unboundlocationexception1)
            {
                unboundlocationexception1.setLine("srfi13.scm", 1698, 48);
                throw unboundlocationexception1;
            }
            obj5 = srfi13.stringIndexRight$V(obj13, obj14, new Object[] {
                obj, obj3
            });
        } else
        if (flag)
        {
            obj5 = Boolean.TRUE;
        } else
        {
            obj5 = Boolean.FALSE;
        }
        if (obj5 == Boolean.FALSE)
        {
            break MISSING_BLOCK_LABEL_276;
        }
        obj6 = AddOp.$Pl.apply2(srfi13.Lit1, obj5);
        obj7 = s;
        location = srfi13.loc$token$Mnchars;
        try
        {
            obj8 = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("srfi13.scm", 1701, 34);
            throw unboundlocationexception;
        }
        obj9 = srfi13.stringSkipRight$V(obj7, obj8, new Object[] {
            obj, obj5
        });
        if (obj9 != Boolean.FALSE)
        {
            Object obj11 = s;
            Object obj12;
            try
            {
                charsequence1 = (CharSequence)obj11;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "substring", 1, obj11);
            }
            obj12 = AddOp.$Pl.apply2(srfi13.Lit1, obj9);
            try
            {
                k = ((Number)obj12).intValue();
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "substring", 2, obj12);
            }
            try
            {
                l = ((Number)obj6).intValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "substring", 3, obj6);
            }
            obj2 = lists.cons(strings.substring(charsequence1, k, l), obj2);
            obj3 = obj9;
            continue; /* Loop/switch isn't completed */
        }
        obj10 = s;
        try
        {
            charsequence = (CharSequence)obj10;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "substring", 1, obj10);
        }
        try
        {
            i = ((Number)obj).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "substring", 2, obj);
        }
        j = ((Number)obj6).intValue();
        obj2 = lists.cons(strings.substring(charsequence, i, j), obj2);
        return obj2;
        ClassCastException classcastexception3;
        classcastexception3;
        throw new WrongType(classcastexception3, "substring", 3, obj6);
        if (true) goto _L2; else goto _L1
_L1:
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 183)
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
        if (modulemethod.selector == 184)
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
