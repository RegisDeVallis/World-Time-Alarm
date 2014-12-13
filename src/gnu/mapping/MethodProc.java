// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;

import gnu.bytecode.ArrayType;
import gnu.bytecode.Type;

// Referenced classes of package gnu.mapping:
//            ProcedureN, WrongArguments, WrongType, CallContext, 
//            Procedure

public abstract class MethodProc extends ProcedureN
{

    public static final int NO_MATCH = -1;
    public static final int NO_MATCH_AMBIGUOUS = 0xfff30000;
    public static final int NO_MATCH_BAD_TYPE = 0xfff40000;
    public static final int NO_MATCH_TOO_FEW_ARGS = 0xfff10000;
    public static final int NO_MATCH_TOO_MANY_ARGS = 0xfff20000;
    static final Type unknownArgTypes[];
    protected Object argTypes;

    public MethodProc()
    {
    }

    public static RuntimeException matchFailAsException(int i, Procedure procedure, Object aobj[])
    {
        short word0 = (short)i;
        if ((i & 0xffff0000) != 0xfff40000)
        {
            return new WrongArguments(procedure, aobj.length);
        }
        Object obj;
        if (word0 > 0)
        {
            obj = aobj[word0 - 1];
        } else
        {
            obj = null;
        }
        return new WrongType(procedure, word0, obj);
    }

    public static int mostSpecific(MethodProc amethodproc[], int i)
    {
        if (i > 1) goto _L2; else goto _L1
_L1:
        int k = i - 1;
_L8:
        return k;
_L2:
        MethodProc methodproc;
        MethodProc amethodproc1[];
        int j;
        methodproc = amethodproc[0];
        amethodproc1 = null;
        j = 1;
        k = 0;
_L4:
        if (j >= i)
        {
            continue; /* Loop/switch isn't completed */
        }
        MethodProc methodproc1 = amethodproc[j];
        if (methodproc == null)
        {
            break; /* Loop/switch isn't completed */
        }
        MethodProc methodproc4 = mostSpecific(methodproc, methodproc1);
        int l;
        int i1;
        MethodProc methodproc2;
        MethodProc methodproc3;
        if (methodproc4 == null)
        {
            if (amethodproc1 == null)
            {
                amethodproc1 = new MethodProc[i];
            }
            amethodproc1[0] = methodproc;
            amethodproc1[1] = methodproc1;
            i1 = 2;
            methodproc = null;
        } else
        if (methodproc4 == methodproc1)
        {
            methodproc = methodproc1;
            i1 = j;
        } else
        {
            i1 = k;
        }
_L5:
        j++;
        k = i1;
        if (true) goto _L4; else goto _L3
_L3:
        l = 0;
_L6:
        if (l >= k)
        {
            break MISSING_BLOCK_LABEL_169;
        }
        methodproc2 = amethodproc1[l];
        methodproc3 = mostSpecific(methodproc2, methodproc1);
        if (methodproc3 == methodproc2)
        {
            i1 = k;
        } else
        {
label0:
            {
                if (methodproc3 != null)
                {
                    break label0;
                }
                i1 = k + 1;
                amethodproc1[k] = methodproc1;
            }
        }
          goto _L5
        l++;
          goto _L6
        methodproc = methodproc1;
        i1 = j;
          goto _L5
        if (methodproc != null) goto _L8; else goto _L7
_L7:
        return -1;
    }

    public static MethodProc mostSpecific(MethodProc methodproc, MethodProc methodproc1)
    {
        int i = methodproc.minArgs();
        int j = methodproc1.minArgs();
        int k = methodproc.maxArgs();
        int l = methodproc1.maxArgs();
        if (k >= 0 && k < j || l >= 0 && l < i)
        {
            methodproc = null;
        } else
        {
            int i1 = methodproc.numParameters();
            int j1 = methodproc1.numParameters();
            int k1;
            boolean flag;
            boolean flag1;
            int l1;
            if (i1 > j1)
            {
                k1 = i1;
            } else
            {
                k1 = j1;
            }
            flag = false;
            flag1 = false;
            if (k != l)
            {
                flag = false;
                if (k < 0)
                {
                    flag = true;
                }
                flag1 = false;
                if (l < 0)
                {
                    flag1 = true;
                }
            }
            if (i < j)
            {
                flag = true;
            } else
            if (i > j)
            {
                flag1 = true;
            }
            for (l1 = 0; l1 < k1; l1++)
            {
                int i2 = methodproc.getParameterType(l1).compare(methodproc1.getParameterType(l1));
                if (i2 == -1)
                {
                    flag1 = true;
                    if (flag)
                    {
                        return null;
                    }
                }
                if (i2 != 1)
                {
                    continue;
                }
                flag = true;
                if (flag1)
                {
                    return null;
                }
            }

            if (!flag1)
            {
                if (flag)
                {
                    return methodproc1;
                } else
                {
                    return null;
                }
            }
        }
        return methodproc;
    }

    public Object applyN(Object aobj[])
        throws Throwable
    {
        checkArgCount(this, aobj.length);
        CallContext callcontext = CallContext.getInstance();
        checkN(aobj, callcontext);
        return callcontext.runUntilValue();
    }

    public Type getParameterType(int i)
    {
        if (!(argTypes instanceof Type[]))
        {
            resolveParameterTypes();
        }
        Type atype[] = (Type[])(Type[])argTypes;
        if (i < atype.length && (i < -1 + atype.length || maxArgs() >= 0))
        {
            return atype[i];
        }
        if (maxArgs() < 0)
        {
            Type type = atype[-1 + atype.length];
            if (type instanceof ArrayType)
            {
                return ((ArrayType)type).getComponentType();
            }
        }
        return Type.objectType;
    }

    public int isApplicable(Type atype[])
    {
        int i = atype.length;
        int j = numArgs();
        byte byte0;
        if (i < (j & 0xfff) || j >= 0 && i > j >> 12)
        {
            byte0 = -1;
        } else
        {
            byte0 = 1;
            int k = i;
            while (--k >= 0) 
            {
                int l = getParameterType(k).compare(atype[k]);
                if (l == -3)
                {
                    return -1;
                }
                if (l < 0)
                {
                    byte0 = 0;
                }
            }
        }
        return byte0;
    }

    public int numParameters()
    {
        int i = numArgs();
        int j = i >> 12;
        if (j >= 0)
        {
            return j;
        } else
        {
            return 1 + (i & 0xfff);
        }
    }

    protected void resolveParameterTypes()
    {
        argTypes = unknownArgTypes;
    }

    static 
    {
        Type atype[] = new Type[1];
        atype[0] = Type.pointer_type;
        unknownArgTypes = atype;
    }
}
