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
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            srfi13

public class ception extends ModuleBody
{

    Object criterion;
    final ModuleMethod lambda$Fn185 = new ModuleMethod(this, 160, null, 0);
    final ModuleMethod lambda$Fn186 = new ModuleMethod(this, 161, null, 8194);
    LList maybe$Mnstart$Plend;
    Object s;

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 160)
        {
            return lambda185();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 161)
        {
            return lambda186(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda185()
    {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncount, s, maybe$Mnstart$Plend);
    }

    Object lambda186(Object obj, Object obj1)
    {
        if (!characters.isChar(criterion)) goto _L2; else goto _L1
_L1:
        Object obj8;
        Object obj3 = srfi13.Lit0;
        Object obj13 = obj;
        while (Scheme.numGEq.apply2(obj13, obj1) == Boolean.FALSE) 
        {
            Object obj14 = AddOp.$Pl.apply2(obj13, srfi13.Lit1);
            Object obj15 = criterion;
            gnu.kawa.functions.ApplyToArgs applytoargs;
            Location location;
            Object obj2;
            Object aobj[];
            Object obj5;
            gnu.kawa.functions.ApplyToArgs applytoargs1;
            Object obj6;
            CharSequence charsequence;
            int i;
            Object obj9;
            gnu.kawa.functions.ApplyToArgs applytoargs2;
            Location location1;
            Object obj10;
            Object obj11;
            CharSequence charsequence1;
            int j;
            Char char1;
            Object obj16;
            CharSequence charsequence2;
            int k;
            try
            {
                char1 = (Char)obj15;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "char=?", 1, obj15);
            }
            obj16 = s;
            try
            {
                charsequence2 = (CharSequence)obj16;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "string-ref", 1, obj16);
            }
            try
            {
                k = ((Number)obj13).intValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "string-ref", 2, obj13);
            }
            if (characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence2, k))))
            {
                obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
            }
            obj13 = obj14;
        }
          goto _L3
_L2:
        applytoargs = Scheme.applyToArgs;
        location = srfi13.loc$char$Mnset$Qu;
        try
        {
            obj2 = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("srfi13.scm", 1246, 5);
            throw unboundlocationexception;
        }
        if (applytoargs.apply2(obj2, criterion) == Boolean.FALSE) goto _L5; else goto _L4
_L4:
        obj3 = srfi13.Lit0;
        obj8 = obj;
_L7:
        if (Scheme.numGEq.apply2(obj8, obj1) != Boolean.FALSE) goto _L3; else goto _L6
_L6:
        obj9 = AddOp.$Pl.apply2(obj8, srfi13.Lit1);
        applytoargs2 = Scheme.applyToArgs;
        location1 = srfi13.loc$char$Mnset$Mncontains$Qu;
        Object obj12;
        try
        {
            obj10 = location1.get();
        }
        catch (UnboundLocationException unboundlocationexception1)
        {
            unboundlocationexception1.setLine("srfi13.scm", 1248, 16);
            throw unboundlocationexception1;
        }
        obj11 = criterion;
        obj12 = s;
        try
        {
            charsequence1 = (CharSequence)obj12;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "string-ref", 1, obj12);
        }
        try
        {
            j = ((Number)obj8).intValue();
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "string-ref", 2, obj8);
        }
        if (applytoargs2.apply3(obj10, obj11, Char.make(strings.stringRef(charsequence1, j))) != Boolean.FALSE)
        {
            obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
        }
        obj8 = obj9;
        if (true) goto _L7; else goto _L3
_L5:
        if (misc.isProcedure(criterion))
        {
            obj3 = srfi13.Lit0;
            Object obj4 = obj;
            while (Scheme.numGEq.apply2(obj4, obj1) == Boolean.FALSE) 
            {
                obj5 = AddOp.$Pl.apply2(obj4, srfi13.Lit1);
                applytoargs1 = Scheme.applyToArgs;
                obj6 = criterion;
                Object obj7 = s;
                try
                {
                    charsequence = (CharSequence)obj7;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "string-ref", 1, obj7);
                }
                try
                {
                    i = ((Number)obj4).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-ref", 2, obj4);
                }
                if (applytoargs1.apply2(obj6, Char.make(strings.stringRef(charsequence, i))) != Boolean.FALSE)
                {
                    obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
                }
                obj4 = obj5;
            }
        } else
        {
            aobj = new Object[2];
            aobj[0] = srfi13.string$Mncount;
            aobj[1] = criterion;
            obj3 = misc.error$V("CRITERION param is neither char-set or char.", aobj);
        }
_L3:
        return obj3;
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 160)
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
        if (modulemethod.selector == 161)
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
