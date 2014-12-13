// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.Keyword;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;
import kawa.lang.Record;

public class make extends ProcedureN
{

    public make()
    {
    }

    public Object applyN(Object aobj[])
    {
        int i = aobj.length;
        if (i == 0)
        {
            throw new WrongArguments(this, i);
        }
        Object obj = aobj[0];
        Class class1;
        if (obj instanceof Class)
        {
            class1 = (Class)obj;
        } else
        if (obj instanceof ClassType)
        {
            class1 = ((ClassType)obj).getReflectClass();
        } else
        {
            class1 = null;
        }
        if (class1 == null)
        {
            throw new WrongType(this, 1, obj, "class");
        }
        Object obj1;
        int j;
        try
        {
            obj1 = class1.newInstance();
        }
        catch (Exception exception)
        {
            throw new WrappedException(exception);
        }
        for (j = 1; j < i;)
        {
            int k = j + 1;
            Keyword keyword = (Keyword)aobj[j];
            j = k + 1;
            Record.set1(aobj[k], keyword.getName(), obj1);
        }

        return obj1;
    }

    public int numArgs()
    {
        return -4095;
    }
}
