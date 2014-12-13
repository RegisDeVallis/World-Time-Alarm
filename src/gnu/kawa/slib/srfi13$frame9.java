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

public class  extends ModuleBody
{

    Object criterion;
    final ModuleMethod lambda$Fn23 = new ModuleMethod(this, 20, null, 0);
    final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 21, null, 8194);
    LList maybe$Mnstart$Plend;
    Object s;

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 20)
        {
            return lambda23();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 21)
        {
            return lambda24(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda23()
    {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnevery, s, maybe$Mnstart$Plend);
    }

    Object lambda24(Object obj, Object obj1)
    {
        if (characters.isChar(criterion))
        {
            Object obj14 = obj;
            do
            {
                Object obj15 = Scheme.numGEq.apply2(obj14, obj1);
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
                Object obj13;
                boolean flag2;
                Object obj16;
                Char char1;
                Object obj17;
                CharSequence charsequence2;
                int k;
                boolean flag3;
                try
                {
                    flag2 = ((Boolean)obj15).booleanValue();
                }
                catch (ClassCastException classcastexception6)
                {
                    throw new WrongType(classcastexception6, "x", -2, obj15);
                }
                if (flag2)
                {
                    if (flag2)
                    {
                        return Boolean.TRUE;
                    } else
                    {
                        return Boolean.FALSE;
                    }
                }
                obj16 = criterion;
                try
                {
                    char1 = (Char)obj16;
                }
                catch (ClassCastException classcastexception7)
                {
                    throw new WrongType(classcastexception7, "char=?", 1, obj16);
                }
                obj17 = s;
                try
                {
                    charsequence2 = (CharSequence)obj17;
                }
                catch (ClassCastException classcastexception8)
                {
                    throw new WrongType(classcastexception8, "string-ref", 1, obj17);
                }
                try
                {
                    k = ((Number)obj14).intValue();
                }
                catch (ClassCastException classcastexception9)
                {
                    throw new WrongType(classcastexception9, "string-ref", 2, obj14);
                }
                flag3 = characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence2, k)));
                if (flag3)
                {
                    obj14 = AddOp.$Pl.apply2(obj14, srfi13.Lit1);
                } else
                if (flag3)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            } while (true);
        }
        applytoargs = Scheme.applyToArgs;
        location = srfi13.loc$char$Mnset$Qu;
        try
        {
            obj2 = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("srfi13.scm", 489, 5);
            throw unboundlocationexception;
        }
        if (applytoargs.apply2(obj2, criterion) != Boolean.FALSE)
        {
            Object obj8 = obj;
            do
            {
                Object obj9 = Scheme.numGEq.apply2(obj8, obj1);
                Object obj12;
                try
                {
                    flag1 = ((Boolean)obj9).booleanValue();
                }
                catch (ClassCastException classcastexception3)
                {
                    throw new WrongType(classcastexception3, "x", -2, obj9);
                }
                if (flag1)
                {
                    if (flag1)
                    {
                        return Boolean.TRUE;
                    } else
                    {
                        return Boolean.FALSE;
                    }
                }
                applytoargs1 = Scheme.applyToArgs;
                location1 = srfi13.loc$char$Mnset$Mncontains$Qu;
                try
                {
                    obj10 = location1.get();
                }
                catch (UnboundLocationException unboundlocationexception1)
                {
                    unboundlocationexception1.setLine("srfi13.scm", 492, 9);
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
                obj13 = applytoargs1.apply3(obj10, obj11, Char.make(strings.stringRef(charsequence1, j)));
                if (obj13 != Boolean.FALSE)
                {
                    obj8 = AddOp.$Pl.apply2(obj8, srfi13.Lit1);
                } else
                {
                    return obj13;
                }
            } while (true);
        } else
        if (misc.isProcedure(criterion))
        {
            Object obj3 = Scheme.numEqu.apply2(obj, obj1);
            Object obj4;
            try
            {
                flag = ((Boolean)obj3).booleanValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj3);
            }
            if (flag)
            {
                if (flag)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            obj4 = obj;
            do
            {
                Object obj5 = s;
                try
                {
                    charsequence = (CharSequence)obj5;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-ref", 1, obj5);
                }
                try
                {
                    i = ((Number)obj4).intValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "string-ref", 2, obj4);
                }
                c = strings.stringRef(charsequence, i);
                obj6 = AddOp.$Pl.apply2(obj4, srfi13.Lit1);
                if (Scheme.numEqu.apply2(obj6, obj1) != Boolean.FALSE)
                {
                    return Scheme.applyToArgs.apply2(criterion, Char.make(c));
                }
                obj7 = Scheme.applyToArgs.apply2(criterion, Char.make(c));
                if (obj7 != Boolean.FALSE)
                {
                    obj4 = obj6;
                } else
                {
                    return obj7;
                }
            } while (true);
        } else
        {
            aobj = new Object[2];
            aobj[0] = srfi13.string$Mnevery;
            aobj[1] = criterion;
            return misc.error$V("Second param is neither char-set, char, or predicate procedure.", aobj);
        }
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 20)
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
        if (modulemethod.selector == 21)
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
