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
    final ModuleMethod lambda$Fn183 = new ModuleMethod(this, 158, null, 0);
    final ModuleMethod lambda$Fn184 = new ModuleMethod(this, 159, null, 8194);
    LList maybe$Mnstart$Plend;
    Object str;

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 158)
        {
            return lambda183();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 159)
        {
            return lambda184(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda183()
    {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnskip$Mnright, str, maybe$Mnstart$Plend);
    }

    Object lambda184(Object obj, Object obj1)
    {
        if (!characters.isChar(criterion)) goto _L2; else goto _L1
_L1:
        Object obj3 = AddOp.$Mn.apply2(obj1, srfi13.Lit1);
_L7:
        Object obj7;
        Object obj10;
        Object obj11 = Scheme.numGEq.apply2(obj3, obj);
        gnu.kawa.functions.ApplyToArgs applytoargs;
        Location location;
        Object obj2;
        Object aobj[];
        boolean flag;
        gnu.kawa.functions.ApplyToArgs applytoargs1;
        Object obj5;
        CharSequence charsequence;
        int i;
        boolean flag1;
        gnu.kawa.functions.ApplyToArgs applytoargs2;
        Location location1;
        Object obj8;
        Object obj9;
        CharSequence charsequence1;
        int j;
        boolean flag2;
        Char char1;
        CharSequence charsequence2;
        int k;
        try
        {
            flag2 = ((Boolean)obj11).booleanValue();
        }
        catch (ClassCastException classcastexception6)
        {
            throw new WrongType(classcastexception6, "x", -2, obj11);
        }
        if (!flag2) goto _L4; else goto _L3
_L3:
        Object obj12 = criterion;
        Object obj13;
        try
        {
            char1 = (Char)obj12;
        }
        catch (ClassCastException classcastexception7)
        {
            throw new WrongType(classcastexception7, "char=?", 1, obj12);
        }
        obj13 = str;
        try
        {
            charsequence2 = (CharSequence)obj13;
        }
        catch (ClassCastException classcastexception8)
        {
            throw new WrongType(classcastexception8, "string-ref", 1, obj13);
        }
        try
        {
            k = ((Number)obj3).intValue();
        }
        catch (ClassCastException classcastexception9)
        {
            throw new WrongType(classcastexception9, "string-ref", 2, obj3);
        }
        if (!characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence2, k)))) goto _L6; else goto _L5
_L5:
        obj3 = AddOp.$Mn.apply2(obj3, srfi13.Lit1);
          goto _L7
_L4:
        if (!flag2) goto _L9; else goto _L8
_L8:
        obj3 = Boolean.TRUE;
_L6:
        return obj3;
_L9:
        return Boolean.FALSE;
_L2:
        applytoargs = Scheme.applyToArgs;
        location = srfi13.loc$char$Mnset$Qu;
        try
        {
            obj2 = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("srfi13.scm", 1222, 5);
            throw unboundlocationexception;
        }
        if (applytoargs.apply2(obj2, criterion) == Boolean.FALSE)
        {
            break MISSING_BLOCK_LABEL_305;
        }
        obj3 = AddOp.$Mn.apply2(obj1, srfi13.Lit1);
        obj7 = Scheme.numGEq.apply2(obj3, obj);
        try
        {
            flag1 = ((Boolean)obj7).booleanValue();
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "x", -2, obj7);
        }
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_292;
        }
        applytoargs2 = Scheme.applyToArgs;
        location1 = srfi13.loc$char$Mnset$Mncontains$Qu;
        try
        {
            obj8 = location1.get();
        }
        catch (UnboundLocationException unboundlocationexception1)
        {
            unboundlocationexception1.setLine("srfi13.scm", 1225, 9);
            throw unboundlocationexception1;
        }
        obj9 = criterion;
        obj10 = str;
        try
        {
            charsequence1 = (CharSequence)obj10;
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "string-ref", 1, obj10);
        }
        try
        {
            j = ((Number)obj3).intValue();
        }
        catch (ClassCastException classcastexception5)
        {
            throw new WrongType(classcastexception5, "string-ref", 2, obj3);
        }
        if (applytoargs2.apply3(obj8, obj9, Char.make(strings.stringRef(charsequence1, j))) == Boolean.FALSE) goto _L6; else goto _L10
_L10:
        obj3 = AddOp.$Mn.apply2(obj3, srfi13.Lit1);
        break MISSING_BLOCK_LABEL_179;
        if (flag1)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
        if (!misc.isProcedure(criterion))
        {
            break MISSING_BLOCK_LABEL_439;
        }
        obj3 = AddOp.$Mn.apply2(obj1, srfi13.Lit1);
_L12:
        Object obj4 = Scheme.numGEq.apply2(obj3, obj);
        Object obj6;
        try
        {
            flag = ((Boolean)obj4).booleanValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "x", -2, obj4);
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_426;
        }
        applytoargs1 = Scheme.applyToArgs;
        obj5 = criterion;
        obj6 = str;
        try
        {
            charsequence = (CharSequence)obj6;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 1, obj6);
        }
        try
        {
            i = ((Number)obj3).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "string-ref", 2, obj3);
        }
        if (applytoargs1.apply2(obj5, Char.make(strings.stringRef(charsequence, i))) == Boolean.FALSE) goto _L6; else goto _L11
_L11:
        obj3 = AddOp.$Mn.apply2(obj3, srfi13.Lit1);
          goto _L12
        if (flag)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
        aobj = new Object[2];
        aobj[0] = srfi13.string$Mnskip$Mnright;
        aobj[1] = criterion;
        return misc.error$V("CRITERION param is neither char-set or char.", aobj);
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 158)
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
        if (modulemethod.selector == 159)
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
