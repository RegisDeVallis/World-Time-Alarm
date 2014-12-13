// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.expr.Declaration;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;

// Referenced classes of package gnu.kawa.functions:
//            ApplyToArgs, IsEq

public class Map extends ProcedureN
{

    final Declaration applyFieldDecl;
    final ApplyToArgs applyToArgs;
    boolean collect;
    final IsEq isEq;

    public Map(boolean flag, ApplyToArgs applytoargs, Declaration declaration, IsEq iseq)
    {
        String s;
        if (flag)
        {
            s = "map";
        } else
        {
            s = "for-each";
        }
        super(s);
        collect = flag;
        applyToArgs = applytoargs;
        applyFieldDecl = declaration;
        isEq = iseq;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyMap");
    }

    public static void forEach1(Procedure procedure, Object obj)
        throws Throwable
    {
        Pair pair;
        for (; obj != LList.Empty; obj = pair.getCdr())
        {
            pair = (Pair)obj;
            procedure.apply1(pair.getCar());
        }

    }

    public static Object map1(Procedure procedure, Object obj)
        throws Throwable
    {
        Object obj1 = LList.Empty;
        Pair pair = null;
        while (obj != LList.Empty) 
        {
            Pair pair1 = (Pair)obj;
            Pair pair2 = new Pair(procedure.apply1(pair1.getCar()), LList.Empty);
            if (pair == null)
            {
                obj1 = pair2;
            } else
            {
                pair.setCdr(pair2);
            }
            pair = pair2;
            obj = pair1.getCdr();
        }
        return obj1;
    }

    public Object apply2(Object obj, Object obj1)
        throws Throwable
    {
        if (obj instanceof Procedure)
        {
            Procedure procedure = (Procedure)obj;
            if (collect)
            {
                return map1(procedure, obj1);
            } else
            {
                forEach1(procedure, obj1);
                return Values.empty;
            }
        } else
        {
            return applyN(new Object[] {
                obj, obj1
            });
        }
    }

    public Object applyN(Object aobj[])
        throws Throwable
    {
        int i = -1 + aobj.length;
        if (i != 1 || !(aobj[0] instanceof Procedure)) goto _L2; else goto _L1
_L1:
        Procedure procedure = (Procedure)(Procedure)aobj[0];
        if (!collect) goto _L4; else goto _L3
_L3:
        Object obj = map1(procedure, aobj[1]);
_L6:
        return obj;
_L4:
        forEach1(procedure, aobj[1]);
        return Values.empty;
_L2:
        Pair pair;
        Object aobj2[];
        Object obj1;
        pair = null;
        Object aobj1[];
        int j;
        int k;
        Object obj3;
        Pair pair2;
        if (collect)
        {
            obj = LList.Empty;
        } else
        {
            obj = Values.empty;
        }
        aobj1 = new Object[i];
        System.arraycopy(((Object) (aobj)), 1, ((Object) (aobj1)), 0, i);
        if (aobj[0] instanceof Procedure)
        {
            j = 0;
            aobj2 = new Object[i];
            obj1 = (Procedure)aobj[0];
        } else
        {
            j = 1;
            aobj2 = new Object[i + 1];
            aobj2[0] = aobj[0];
            obj1 = applyToArgs;
            pair = null;
        }
_L8:
        k = 0;
_L7:
        if (k >= i)
        {
            break MISSING_BLOCK_LABEL_214;
        }
        obj3 = aobj1[k];
        if (obj3 == LList.Empty) goto _L6; else goto _L5
_L5:
        pair2 = (Pair)obj3;
        aobj2[j + k] = pair2.getCar();
        aobj1[k] = pair2.getCdr();
        k++;
          goto _L7
        Object obj2 = ((Procedure) (obj1)).applyN(aobj2);
        if (collect)
        {
            Pair pair1 = new Pair(obj2, LList.Empty);
            if (pair == null)
            {
                obj = pair1;
            } else
            {
                pair.setCdr(pair1);
            }
            pair = pair1;
        }
          goto _L8
    }
}
