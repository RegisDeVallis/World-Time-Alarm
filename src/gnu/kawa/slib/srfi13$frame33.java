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
import kawa.lib.misc;
import kawa.standard.Scheme;
import kawa.standard.call_with_values;

// Referenced classes of package gnu.kawa.slib:
//            srfi13

public class s extends ModuleBody
{
    public class srfi13.frame34 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn76 = new ModuleMethod(this, 68, null, 0);
        final ModuleMethod lambda$Fn77 = new ModuleMethod(this, 69, null, 8194);
        Object rest;
        Object start1;
        srfi13.frame33 staticLink;

        static Boolean lambda78(Object obj)
        {
            return Boolean.FALSE;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 68)
            {
                return lambda76();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 69)
            {
                return lambda77(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda76()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls$Gr, staticLink.s2, rest);
        }

        Object lambda77(Object obj, Object obj1)
        {
            int i = 1;
            Object obj2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(end1, start1), AddOp.$Mn.apply2(obj1, obj));
            Boolean boolean1;
            int j;
            int k;
            int l;
            try
            {
                boolean1 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj2);
            }
            if (obj2 != boolean1)
            {
                j = i;
            } else
            {
                j = 0;
            }
            k = 1 & j + 1;
            if (k != 0)
            {
                if (k != 0)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            if (staticLink.s1 == staticLink.s2)
            {
                l = i;
            } else
            {
                l = 0;
            }
            if (l != 0)
            {
                Object obj3 = Scheme.numEqu.apply2(start1, obj);
                int i1;
                Boolean boolean2;
                try
                {
                    boolean2 = Boolean.FALSE;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "x", -2, obj3);
                }
                if (obj3 == boolean2)
                {
                    i = 0;
                }
            } else
            {
                i = l;
            }
            i1 = 1 & i + 1;
            if (i1 != 0)
            {
                return srfi13.$PcStringCompare(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, misc.values, srfi13.lambda$Fn78, misc.values);
            }
            if (i1 != 0)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 68)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                retu