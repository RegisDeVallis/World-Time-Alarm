// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.mapping.Procedure;
import kawa.lib.lists;

// Referenced classes of package gnu.kawa.slib:
//            srfi1

public class  extends ModuleBody
{

    Procedure f;

    public Object lambda21recur(Object obj, Object obj1)
    {
        if (lists.isPair(obj1))
        {
            obj = f.apply2(obj, lambda21recur(lists.car.apply1(obj1), lists.cdr.apply1(obj1)));
        }
        return obj;
    }

    public ()
    {
    }
}
