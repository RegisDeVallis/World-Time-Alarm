// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.kawa.util.AbstractWeakHashTable;

// Referenced classes of package gnu.expr:
//            ModuleContext

static class  extends AbstractWeakHashTable
{

    protected Class getKeyFromValue(Object obj)
    {
        return obj.getClass();
    }

    protected volatile Object getKeyFromValue(Object obj)
    {
        return getKeyFromValue(obj);
    }

    protected boolean matches(Class class1, Class class2)
    {
        return class1 == class2;
    }

    ()
    {
    }
}
