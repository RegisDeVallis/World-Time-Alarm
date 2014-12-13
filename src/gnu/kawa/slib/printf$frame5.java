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
import kawa.lib.strings;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            printf

public class lambda.Fn10 extends ModuleBody
{

    Object fdigs;
    final ModuleMethod lambda$Fn10;
     staticLink;

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 6)
        {
            return lambda14(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda14(Object obj, Object obj1)
    {
        Object aobj[] = new Object[3];
        aobj[0] = "0";
        aobj[1] = staticLink.idigs;
        aobj[2] = fdigs;
        gnu.lists.FString fstring = strings.stringAppend(aobj);
        int i = strings.stringLength(fstring);
        Object obj2 = printf.Lit7;
        AddOp addop = AddOp.$Pl;
        Object obj3 = staticLink.idigs;
        CharSequence charsequence;
        Object obj4;
        try
        {
            charsequence = (CharSequence)obj3;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj3);
        }
        obj4 = addop.apply2(obj1, Integer.valueOf(strings.stringLength(charsequence)));
        do
        {
            if (Scheme.numGEq.apply2(obj2, Integer.valueOf(i)) != Boolean.FALSE)
            {
                gnu.kawa.functions.ApplyToArgs applytoargs1 = Scheme.applyToArgs;
                Object aobj2[] = new Object[5];
                aobj2[0] = staticLink.staticLink.staticLink.cont;
                aobj2[1] = obj;
                aobj2[2] = staticLink.staticLink.sgn;
                aobj2[3] = "0";
                aobj2[4] = printf.Lit7;
                return applytoargs1.applyN(aobj2);
            }
            Char char1 = printf.Lit9;
            int j;
            try
            {
                j = ((Number)obj2).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 2, obj2);
            }
            if (characters.isChar$Eq(char1, Char.make(strings.stringRef(fstring, j))))
            {
                obj2 = AddOp.$Pl.apply2(obj2, printf.Lit7);
                obj4 = AddOp.$Mn.apply2(obj4, printf.Lit7);
            } else
            {
                gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
                Object aobj1[] = new Object[5];
                aobj1[0] = staticLink.staticLink.staticLink.cont;
                aobj1[1] = obj;
                aobj1[2] = staticLink.staticLink.sgn;
                Object obj5 = AddOp.$Mn.apply2(obj2, printf.Lit7);
                int k;
                try
                {
                    k = ((Number)obj5).intValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "substring", 2, obj5);
                }
                aobj1[3] = strings.substring(fstring, k, i);
                aobj1[4] = obj4;
                return applytoargs.applyN(aobj1);
            }
        } while (true);
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        if (modulemethod.selector == 6)
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
        ModuleMethod modulemethod = new ModuleMethod(this, 6, null, 8194);
        modulemethod.setProperty("source-location", "printf.scm:92");
        lambda$Fn10 = modulemethod;
    }
}
