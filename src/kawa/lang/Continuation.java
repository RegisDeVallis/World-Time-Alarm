// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;

// Referenced classes of package kawa.lang:
//            CalledContinuation, GenericError

public class Continuation extends MethodProc
{

    static int counter;
    int id;
    public boolean invoked;

    public Continuation(CallContext callcontext)
    {
    }

    public static Object handleException(Throwable throwable, Continuation continuation)
        throws Throwable
    {
        CalledContinuation calledcontinuation;
label0:
        {
            if (throwable instanceof CalledContinuation)
            {
                calledcontinuation = (CalledContinuation)throwable;
                if (calledcontinuation.continuation == continuation)
                {
                    break label0;
                }
            }
            throw throwable;
        }
        continuation.invoked = true;
        return Values.make(calledcontinuation.values);
    }

    public static void handleException$X(Throwable throwable, Continuation continuation, CallContext callcontext)
        throws Throwable
    {
        CalledContinuation calledcontinuation;
label0:
        {
            if (throwable instanceof CalledContinuation)
            {
                calledcontinuation = (CalledContinuation)throwable;
                if (calledcontinuation.continuation == continuation)
                {
                    break label0;
                }
            }
            throw throwable;
        }
        continuation.invoked = true;
        Object aobj[] = calledcontinuation.values;
        int i = aobj.length;
        for (int j = 0; j < i; j++)
        {
            callcontext.consumer.writeObject(aobj[j]);
        }

    }

    public void apply(CallContext callcontext)
    {
        if (invoked)
        {
            throw new GenericError("implementation restriction: continuation can only be used once");
        } else
        {
            throw new CalledContinuation(callcontext.values, this, callcontext);
        }
    }

    public final String toString()
    {
        StringBuilder stringbuilder = (new StringBuilder()).append("#<continuation ").append(id);
        String s;
        if (invoked)
        {
            s = " (invoked)>";
        } else
        {
            s = ">";
        }
        return stringbuilder.append(s).toString();
    }
}
