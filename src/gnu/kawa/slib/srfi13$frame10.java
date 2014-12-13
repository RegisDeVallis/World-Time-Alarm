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
    final ModuleMethod lambda$Fn25 = new ModuleMethod(this, 22, null, 0);
    final ModuleMethod lambda$Fn26 = new ModuleMethod(this, 23, null, 8194);
    LList maybe$Mnstart$Plend;
    Object s;

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 22)
        {
            return lambda25();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 23)
        {
            return lambda26(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda25()
    {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnany, s, maybe$Mnstart$Plend);
    }

    Object lambda26(Object obj, Object obj1)
    {
        if (!characters.isChar(criterion)) goto _L2; else goto _L1
_L1:
        Object obj13 = obj;
_L7:
        Object obj4;
        Object obj5;
        Object obj8;
        Object obj9;
        Object obj12;
        Object obj14 = Scheme.numLss.apply2(obj13, obj1);
        gnu.kawa.functions.ApplyToArgs applytoargs;
        Location location;
        Object obj2;
        Object aobj[];
        boolean flag;
        CharSequence charsequence;
        int i;
        char c;
        Object obj6;
        Object obj7;
        boolean flag1;
        gnu.kawa.functions.ApplyToArgs applytoargs1;
        Location location1;
        Object obj10;
        Object obj11;
        CharSequence charsequence1;
        int j;
        boolean flag2;
        Boolean boolean1;
        Char char1;
        CharSequence charsequence2;
        int k;
        boolean flag3;
        try
        {
            flag2 = ((Boolean)obj14).booleanValue();
        }
        catch (ClassCastException classcastexception6)
        {
            throw new WrongType(classcastexception6, "x", -2, obj14);
        }
        if (!flag2) goto _L4; else goto _L3
_L3:
        Object obj15 = criterion;
        Object obj16;
        try
        {
            char1 = (Char)obj15;
        }
        catch (ClassCastException classcastexception7)
        {
            throw new WrongType(classcastexception7, "char=?", 1, obj15);
        }
        obj16 = s;
        try
        {
            charsequence2 = (CharSequence)obj16;
        }
        catch (ClassCastException classcastexception8)
        {
            throw new WrongType(classcastexception8, "string-ref", 1, obj16);
        }
        try
        {
            k = ((Number)obj13).intValue();
        }
        catch (ClassCastException classcastexception9)
        {
            throw new WrongType(classcastexception9, "string-ref", 2, obj13);
        }
        flag3 = characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence2, k)));
        if (!flag3) goto _L6; else goto _L5
_L5:
        if (flag3)
        {
            boolean1 = Boolean.TRUE;
        } else
        {
            boolean1 = Boolean.FALSE;
        }
_L8:
        obj7 = boolean1;
_L10:
        return obj7;
_L6:
        obj13 = AddOp.$Pl.apply2(obj13, srfi13.Lit1);
          goto _L7
_L4:
        if (flag2)
        {
            boolean1 = Boolean.TRUE;
        } else
        {
            boolean1 = Boolean.FALSE;
        }
          goto _L8
_L2:
        applytoargs = Scheme.applyToArgs;
        location = srfi13.loc$char$Mnset$Qu;
        try
        {
            obj2 = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("srfi13.scm", 515, 5);
            throw unboundlocationexception;
        }
        if (applytoargs.apply2(obj2, criterion) == Boolean.FALSE)
        {
            break MISSING_BLOCK_LABEL_324;
        }
        obj8 = obj;
        obj9 = Scheme.numLss.apply2(obj8, obj1);
        try
        {
            flag1 = ((Boolean)obj9).booleanValue();
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "x", -2, obj9);
        }
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_311;
        }
        applytoargs1 = Scheme.applyToArgs;
        location1 = srfi13.loc$char$Mnset$Mncontains$Qu;
        try
        {
            obj10 = location1.get();
        }
        catch (UnboundLocationException unboundlocationexception1)
        {
            unboundlocationexception1.setLine("srfi13.scm", 518, 9);
            throw unboundlocationexception1;
        }
        obj11 = criterion;
        obj12 = s;
        try
        {
            charsequence1 = (CharSequence)obj12;
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "string-ref", 1, obj12);
        }
        try
        {
            j = ((Number)obj8).intValue();
        }
        catch (ClassCastException classcastexception5)
        {
            throw new WrongType(classcastexception5, "string-ref", 2, obj8);
        }
        obj7 = applytoargs1.apply3(obj10, obj11, Char.make(strings.stringRef(charsequence1, j)));
        if (obj7 != Boolean.FALSE) goto _L10; else goto _L9
_L9:
        obj8 = AddOp.$Pl.apply2(obj8, srfi13.Lit1);
        break MISSING_BLOCK_LABEL_194;
        if (flag1)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
        if (!misc.isProcedure(criterion))
        {
            break MISSING_BLOCK_LABEL_483;
        }
        Object obj3 = Scheme.numLss.apply2(obj, obj1);
        try
        {
            flag = ((Boolean)obj3).booleanValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "x", -2, obj3);
        }
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_470;
        }
        obj4 = obj;
        obj5 = s;
        try
        {
            charsequence = (CharSequence)obj5;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ref", 1, obj5);
        }
        i = ((Number)obj4).intValue();
        c = strings.stringRef(charsequence, i);
        obj6 = AddOp.$Pl.apply2(obj4, srfi13.Lit1);
        if (Scheme.numEqu.apply2(obj6, obj1) != Boolean.FALSE)
        {
            return Scheme.applyToArgs.apply2(criterion, Char.make(c));
        }
        obj7 = Scheme.applyToArgs.apply2(criterion, Char.make(c));
        if (obj7 != Boolean.FALSE) goto _L10; else goto _L11
_L11:
        obj4 = obj6;
        break MISSING_BLOCK_LABEL_362;
        if (flag)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
        aobj = new Object[2];
        aobj[0] = srfi13.string$Mnany;
        aobj[1] = criterion;
        return misc.error$V("Second param is neither char-set, char, or predicate procedure.", aobj);
        ClassCastException classcastexception2;
        classcastexception2;
        throw new WrongType(classcastexception2, "string-ref", 2, obj4);
          goto _L8
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 22)
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
        if (modulemethod.selector == 23)
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
