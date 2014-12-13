// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import kawa.lib.strings;

// Referenced classes of package gnu.kawa.slib:
//            srfi13

public class  extends ModuleBody
{

    Object end1;
    final ModuleMethod lambda$Fn206 = new ModuleMethod(this, 181, null, 0);
    final ModuleMethod lambda$Fn207 = new ModuleMethod(this, 182, null, 8194);
    LList maybe$Mnstart$Plend;
    Object s1;
    Object s2;
    Object start1;

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 181)
        {
            return lambda206();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 182)
        {
            return lambda207(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda206()
    {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreplace, s2, maybe$Mnstart$Plend);
    }

    CharSequence lambda207(Object obj, Object obj1)
    {
        Object obj2 = s1;
        CharSequence charsequence;
        int i;
        Object obj3;
        Object obj4;
        int j;
        CharSequence charsequence1;
        Object obj5;
        CharSequence charsequence2;
        Object obj6;
        int k;
        Object obj7;
        int l;
        Object obj8;
        CharSequence charsequence3;
        int i1;
        int j1;
        Object obj9;
        int k1;
        Object obj10;
        CharSequence charsequence4;
        Object obj11;
        int l1;
        try
        {
            charsequence = (CharSequence)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj2);
        }
        i = strings.stringLength(charsequence);
        obj3 = AddOp.$Mn.apply2(obj1, obj);
        obj4 = AddOp.$Pl.apply2(AddOp.$Mn.apply2(Integer.valueOf(i), AddOp.$Mn.apply2(end1, start1)), obj3);
        try
        {
            j = ((Number)obj4).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "make-string", 1, obj4);
        }
        charsequence1 = strings.makeString(j);
        obj5 = s1;
        try
        {
            charsequence2 = (CharSequence)obj5;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "%string-copy!", 2, obj5);
        }
        obj6 = start1;
        try
        {
            k = ((Number)obj6).intValue();
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "%string-copy!", 4, obj6);
        }
        srfi13.$PcStringCopy$Ex(charsequence1, 0, charsequence2, 0, k);
        obj7 = start1;
        try
        {
            l = ((Number)obj7).intValue();
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "%string-copy!", 1, obj7);
        }
        obj8 = s2;
        try
        {
            charsequence3 = (CharSequence)obj8;
        }
        catch (ClassCastException classcastexception5)
        {
            throw new WrongType(classcastexception5, "%string-copy!", 2, obj8);
        }
        try
        {
            i1 = ((Number)obj).intValue();
        }
        catch (ClassCastException classcastexception6)
        {
            throw new WrongType(classcastexception6, "%string-copy!", 3, obj);
        }
        try
        {
            j1 = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception7)
        {
            throw new WrongType(classcastexception7, "%string-copy!", 4, obj1);
        }
        srfi13.$PcStringCopy$Ex(charsequence1, l, charsequence3, i1, j1);
        obj9 = AddOp.$Pl.apply2(start1, obj3);
        try
        {
            k1 = ((Number)obj9).intValue();
        }
        catch (ClassCastException classcastexception8)
        {
            throw new WrongType(classcastexception8, "%string-copy!", 1, obj9);
        }
        obj10 = s1;
        try
        {
            charsequence4 = (CharSequence)obj10;
        }
        catch (ClassCastException classcastexception9)
        {
            throw new WrongType(classcastexception9, "%string-copy!", 2, obj10);
        }
        obj11 = end1;
        try
        {
            l1 = ((Number)obj11).intValue();
        }
        catch (ClassCastException classcastexception10)
        {
            throw new WrongType(classcastexception10, "%string-copy!", 3, obj11);
        }
        srfi13.$PcStringCopy$Ex(charsequence1, k1, charsequence4, l1, i);
        return charsequence1;
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 181)
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
        if (modulemethod.selector == 182)
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

    public ()
    {
    }
}
