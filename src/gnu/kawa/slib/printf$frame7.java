// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.WrongType;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.numbers;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            printf

public class lambda.Fn12 extends ModuleBody
{

    final ModuleMethod lambda$Fn12;
    Object sgn;
     staticLink;

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 4)
        {
            return lambda16(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda16(Object obj, Object obj1)
    {
        gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
        Object obj2 = staticLink.cont;
        Char char1 = printf.Lit5;
        Object obj3 = sgn;
        Char char2;
        try
        {
            char2 = (Char)obj3;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "char=?", 2, obj3);
        }
        if (characters.isChar$Eq(char1, char2))
        {
            AddOp addop = AddOp.$Mn;
            CharSequence charsequence;
            Object obj4;
            CharSequence charsequence1;
            try
            {
                charsequence1 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string->number", 1, obj1);
            }
            obj4 = addop.apply1(numbers.string$To$Number(charsequence1));
        } else
        {
            try
            {
                charsequence = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string->number", 1, obj1);
            }
            obj4 = numbers.string$To$Number(charsequence);
        }
        return applytoargs.apply3(obj2, obj, obj4);
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        if (modulemethod.selector == 4)
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
        ModuleMethod modulemethod = new ModuleMethod(this, 4, null, 8194);
        modulemethod.setProperty("source-location", "printf.scm:69");
        lambda$Fn12 = modulemethod;
    }
}
