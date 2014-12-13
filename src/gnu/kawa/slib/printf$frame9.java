// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.kawa.functions.AddOp;
import gnu.lists.LList;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Char;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            printf

public class  extends ModuleBody
{

    LList args;
    Object fc;
    int fl;
    Object format$Mnstring;
    Object out;
    Object pos;

    public Object lambda18mustAdvance()
    {
        pos = AddOp.$Pl.apply2(printf.Lit7, pos);
        if (Scheme.numGEq.apply2(pos, Integer.valueOf(fl)) != Boolean.FALSE)
        {
            return lambda20incomplete();
        }
        Object obj = format$Mnstring;
        CharSequence charsequence;
        Object obj1;
        int i;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-ref", 1, obj);
        }
        obj1 = pos;
        try
        {
            i = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 2, obj1);
        }
        fc = Char.make(strings.stringRef(charsequence, i));
        return Values.empty;
    }

    public boolean lambda19isEndOfFormat()
    {
        return ((Boolean)Scheme.numGEq.apply2(pos, Integer.valueOf(fl))).booleanValue();
    }

    public Object lambda20incomplete()
    {
        gnu.mapping.SimpleSymbol simplesymbol = printf.Lit34;
        Object aobj[] = new Object[2];
        aobj[0] = "conversion specification incomplete";
        aobj[1] = format$Mnstring;
        return misc.error$V(simplesymbol, aobj);
    }

    public Object lambda21out$St(Object obj)
    {
        Object obj1;
label0:
        {
            if (strings.isString(obj))
            {
                return Scheme.applyToArgs.apply2(out, obj);
            }
            boolean flag;
            while (!(flag = lists.isNull(obj))) 
            {
                obj1 = Scheme.applyToArgs.apply2(out, lists.car.apply1(obj));
                if (obj1 == Boolean.FALSE)
                {
                    break label0;
                }
                obj = lists.cdr.apply1(obj);
            }
            if (flag)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }
        return obj1;
    }

    public ()
    {
    }
}
