// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;

// Referenced classes of package gnu.kawa.functions:
//            ApplyToArgs

public class Apply extends ProcedureN
{

    ApplyToArgs applyToArgs;

    public Apply(String s, ApplyToArgs applytoargs)
    {
        super(s);
        applyToArgs = applytoargs;
    }

    public static Object[] getArguments(Object aobj[], int i, Procedure procedure)
    {
        Object obj;
        int k;
        Object aobj1[];
        int l;
        int j = aobj.length;
        if (j < i + 1)
        {
            throw new WrongArguments("apply", 2, (new StringBuilder()).append("(apply proc [args] args) [count:").append(j).append(" skip:").append(i).append("]").toString());
        }
        obj = aobj[j - 1];
        if (obj instanceof Object[])
        {
            Object aobj2[] = (Object[])(Object[])obj;
            if (j == 2)
            {
                return aobj2;
            }
            k = aobj2.length;
        } else
        if (obj instanceof Sequence)
        {
            k = ((Sequence)obj).size();
        } else
        {
            k = -1;
        }
        if (k < 0)
        {
            throw new WrongType(procedure, j, obj, "sequence or array");
        }
        aobj1 = new Object[k + (-1 + (j - i))];
        for (l = 0; l < -1 + (j - i); l++)
        {
            aobj1[l] = aobj[l + i];
        }

        if (!(obj instanceof Object[])) goto _L2; else goto _L1
_L1:
        System.arraycopy(((Object) ((Object[])(Object[])obj)), 0, ((Object) (aobj1)), l, k);
_L4:
        return aobj1;
_L2:
        while (obj instanceof Pair) 
        {
            Pair pair = (Pair)obj;
            int l1 = l + 1;
            aobj1[l] = pair.getCar();
            obj = pair.getCdr();
            k--;
            l = l1;
        }
        if (k > 0)
        {
            Sequence sequence = (Sequence)obj;
            int i1 = 0;
            int j1;
            int k1;
            for (j1 = l; i1 < k; j1 = k1)
            {
                k1 = j1 + 1;
                aobj1[j1] = sequence.get(i1);
                i1++;
            }

            j1;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        Object aobj[] = callcontext.getArgs();
        applyToArgs.checkN(getArguments(aobj, 0, this), callcontext);
    }

    public Object applyN(Object aobj[])
        throws Throwable
    {
        return applyToArgs.applyN(getArguments(aobj, 0, this));
    }
}
