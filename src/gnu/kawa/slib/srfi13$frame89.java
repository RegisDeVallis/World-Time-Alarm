// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.lists.CharSeq;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;
import kawa.lib.strings;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            srfi13

public class  extends ModuleBody
{

    final ModuleMethod lambda$Fn200 = new ModuleMethod(this, 175, null, 0);
    final ModuleMethod lambda$Fn201 = new ModuleMethod(this, 176, null, 8194);
    LList maybe$Mnstart$Plend;
    Object s;

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 175)
        {
            return lambda200();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 176)
        {
            return lambda201(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda200()
    {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreverse, s, maybe$Mnstart$Plend);
    }

    CharSequence lambda201(Object obj, Object obj1)
    {
        Object obj2 = AddOp.$Mn.apply2(obj1, obj);
        int i;
        CharSequence charsequence;
        Object obj3;
        Object obj4;
        try
        {
            i = ((Number)obj2).intValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "make-string", 1, obj2);
        }
        charsequence = strings.makeString(i);
        obj3 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
        obj4 = obj;
        while (Scheme.numLss.apply2(obj3, srfi13.Lit0) == Boolean.FALSE) 
        {
            CharSeq charseq;
            int j;
            Object obj5;
            CharSequence charsequence1;
            int k;
            try
            {
                charseq = (CharSeq)charsequence;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-set!", 1, charsequence);
            }
            try
            {
                j = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-set!", 2, obj3);
            }
            obj5 = s;
            try
            {
                charsequence1 = (CharSequence)obj5;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "string-ref", 1, obj5);
            }
            try
            {
                k = ((Number)obj4).intValue();
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "string-ref", 2, obj4);
            }
            strings.stringSet$Ex(charseq, j, strings.stringRef(charsequence1, k));
            obj4 = AddOp.$Pl.apply2(obj4, srfi13.Lit1);
            obj3 = AddOp.$Mn.apply2(obj3, srfi13.Lit1);
        }
        return charsequence;
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 175)
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
        if (modulemethod.selector == 176)
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
