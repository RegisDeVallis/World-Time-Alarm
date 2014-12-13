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
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import kawa.lib.strings;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            srfi13

public class  extends ModuleBody
{

    final ModuleMethod lambda$Fn202 = new ModuleMethod(this, 177, null, 0);
    final ModuleMethod lambda$Fn203 = new ModuleMethod(this, 178, null, 8194);
    LList maybe$Mnstart$Plend;
    Object s;

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 177)
        {
            return lambda202();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 178)
        {
            return lambda203(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda202()
    {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreverse$Ex, s, maybe$Mnstart$Plend);
    }

    Object lambda203(Object obj, Object obj1)
    {
        Object obj2 = AddOp.$Mn.apply2(obj1, srfi13.Lit1);
        Object obj3 = obj;
        while (Scheme.numLEq.apply2(obj2, obj3) == Boolean.FALSE) 
        {
            Object obj4 = s;
            CharSequence charsequence;
            int i;
            char c;
            Object obj5;
            CharSeq charseq;
            int j;
            Object obj6;
            CharSequence charsequence1;
            int k;
            Object obj7;
            CharSeq charseq1;
            int l;
            try
            {
                charsequence = (CharSequence)obj4;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-ref", 1, obj4);
            }
            try
            {
                i = ((Number)obj2).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 2, obj2);
            }
            c = strings.stringRef(charsequence, i);
            obj5 = s;
            try
            {
                charseq = (CharSeq)obj5;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-set!", 1, obj5);
            }
            try
            {
                j = ((Number)obj2).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "string-set!", 2, obj2);
            }
            obj6 = s;
            try
            {
                charsequence1 = (CharSequence)obj6;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "string-ref", 1, obj6);
            }
            try
            {
                k = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "string-ref", 2, obj3);
            }
            strings.stringSet$Ex(charseq, j, strings.stringRef(charsequence1, k));
            obj7 = s;
            try
            {
                charseq1 = (CharSeq)obj7;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "string-set!", 1, obj7);
            }
            try
            {
                l = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "string-set!", 2, obj3);
            }
            strings.stringSet$Ex(charseq1, l, c);
            obj2 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
            obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
        }
        return Values.empty;
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 177)
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
        if (modulemethod.selector == 178)
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
