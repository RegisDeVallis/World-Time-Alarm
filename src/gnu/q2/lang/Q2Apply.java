// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.q2.lang;

import gnu.bytecode.Type;
import gnu.expr.Special;
import gnu.kawa.reflect.Invoke;
import gnu.lists.Consumable;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import java.util.Vector;

public class Q2Apply extends MethodProc
{

    public static Q2Apply q2Apply = new Q2Apply();

    public Q2Apply()
    {
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        Special special = Special.dfault;
        Object obj = callcontext.getNextArg(special);
        if ((obj instanceof Procedure) || (obj instanceof Type) || (obj instanceof Class))
        {
            Vector vector = new Vector();
            Object obj1;
            if (obj instanceof Procedure)
            {
                obj1 = (Procedure)obj;
            } else
            {
                vector.add(obj);
                obj1 = Invoke.make;
            }
            do
            {
                Object obj2 = callcontext.getNextArg(special);
                if (obj2 == special)
                {
                    Object obj3 = ((Procedure) (obj1)).applyN(vector.toArray());
                    Object aobj[];
                    int i;
                    if (obj3 instanceof Consumable)
                    {
                        ((Consumable)obj3).consume(callcontext.consumer);
                        return;
                    } else
                    {
                        callcontext.writeValue(obj3);
                        return;
                    }
                }
                if (obj2 instanceof Values)
                {
                    aobj = ((Values)obj2).getValues();
                    i = 0;
                    while (i < aobj.length) 
                    {
                        vector.add(aobj[i]);
                        i++;
                    }
                } else
                {
                    vector.add(obj2);
                }
            } while (true);
        }
        while (obj != special) 
        {
            if (obj instanceof Consumable)
            {
                ((Consumable)obj).consume(callcontext.consumer);
            } else
            {
                callcontext.writeValue(obj);
            }
            obj = callcontext.getNextArg(special);
        }
    }

}
