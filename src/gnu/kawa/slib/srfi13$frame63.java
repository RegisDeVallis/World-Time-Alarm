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

    final ModuleMethod lambda$Fn149 = new ModuleMethod(this, 126, null, 0);
    final ModuleMethod lambda$Fn150 = new ModuleMethod(this, 127, null, 8194);
    LList maybe$Mnstart$Plend;
    Object s;

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 126)
        {
            return lambda149();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 127)
        {
            return lambda150(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda149()
    {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntitlecase$Ex, s, maybe$Mnstart$Plend);
    }

    CharSequence lambda150(Object obj, Object obj1)
    {
        Object obj2 = s;
        CharSequence charsequence;
        int i;
        int j;
        CharSequence charsequence1;
        try
        {
            charsequence = (CharSequence)obj2;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "substring", 1, obj2);
        }
        try
        {
            i = ((Number)obj).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "substring", 2, obj);
        }
        try
        {
            j = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "substring", 3, obj1);
        }
        charsequence1 = strings.substring(charsequence, i, j);
        srfi13.$PcStringTitlecase$Ex(charsequence1, srfi13.Lit0, AddOp.$Mn.apply2(obj1, obj));
        return charsequence1;
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 126)
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
        if (modulemethod.selector == 127)
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
