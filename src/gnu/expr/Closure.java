// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.Type;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.MethodProc;
import java.lang.reflect.Array;

// Referenced classes of package gnu.expr:
//            LambdaExp, ScopeExp, Expression, Declaration, 
//            Keyword, Special

class Closure extends MethodProc
{

    Object evalFrames[][];
    LambdaExp lambda;

    public Closure(LambdaExp lambdaexp, CallContext callcontext)
    {
        lambda = lambdaexp;
        Object aobj[][] = callcontext.evalFrames;
        if (aobj != null)
        {
            int i;
            for (i = aobj.length; i > 0 && aobj[i - 1] == null; i--) { }
            evalFrames = new Object[i][];
            System.arraycopy(((Object) (aobj)), 0, ((Object) (evalFrames)), 0, i);
        }
        setSymbol(lambda.getSymbol());
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        Object aobj1[][];
        int i = ScopeExp.nesting(lambda);
        Object aobj[] = callcontext.values;
        aobj1 = callcontext.evalFrames;
        int j;
        Object aobj2[][];
        Exception exception;
        StringBuffer stringbuffer;
        String s;
        int k;
        if (evalFrames == null)
        {
            j = 0;
        } else
        {
            j = evalFrames.length;
        }
        if (i >= j)
        {
            j = i;
        }
        aobj2 = new Object[j + 10][];
        if (evalFrames != null)
        {
            System.arraycopy(((Object) (evalFrames)), 0, ((Object) (aobj2)), 0, evalFrames.length);
        }
        aobj2[i] = aobj;
        callcontext.evalFrames = aobj2;
        if (lambda.body != null)
        {
            break MISSING_BLOCK_LABEL_201;
        }
        stringbuffer = new StringBuffer("procedure ");
        s = lambda.getName();
        if (s == null)
        {
            s = "<anonymous>";
        }
        stringbuffer.append(s);
        k = lambda.getLineNumber();
        if (k <= 0)
        {
            break MISSING_BLOCK_LABEL_159;
        }
        stringbuffer.append(" at line ");
        stringbuffer.append(k);
        stringbuffer.append(" was called before it was expanded");
        throw new RuntimeException(stringbuffer.toString());
        exception;
        callcontext.evalFrames = aobj1;
        throw exception;
        lambda.body.apply(callcontext);
        callcontext.evalFrames = aobj1;
        return;
    }

    public Object getProperty(Object obj, Object obj1)
    {
        Object obj2 = super.getProperty(obj, obj1);
        if (obj2 == null)
        {
            obj2 = lambda.getProperty(obj, obj1);
        }
        return obj2;
    }

    public int match0(CallContext callcontext)
    {
        return matchN(new Object[0], callcontext);
    }

    public int match1(Object obj, CallContext callcontext)
    {
        return matchN(new Object[] {
            obj
        }, callcontext);
    }

    public int match2(Object obj, Object obj1, CallContext callcontext)
    {
        return matchN(new Object[] {
            obj, obj1
        }, callcontext);
    }

    public int match3(Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        return matchN(new Object[] {
            obj, obj1, obj2
        }, callcontext);
    }

    public int match4(Object obj, Object obj1, Object obj2, Object obj3, CallContext callcontext)
    {
        return matchN(new Object[] {
            obj, obj1, obj2, obj3
        }, callcontext);
    }

    public int matchN(Object aobj[], CallContext callcontext)
    {
        int i = numArgs();
        int j = aobj.length;
        int k = i & 0xfff;
        if (j < k)
        {
            return 0xfff10000 | k;
        }
        int l = i >> 12;
        if (j > l && l >= 0)
        {
            return 0xfff20000 | l;
        }
        Object aobj1[] = new Object[lambda.frameSize];
        int i1;
        int j1;
        int k1;
        int l1;
        Declaration declaration;
        int i2;
        int j2;
        if (lambda.keywords == null)
        {
            i1 = 0;
        } else
        {
            i1 = lambda.keywords.length;
        }
        if (lambda.defaultArgs == null)
        {
            j1 = 0;
        } else
        {
            j1 = lambda.defaultArgs.length - i1;
        }
        k1 = 0;
        l1 = lambda.min_args;
        declaration = lambda.firstDecl();
        i2 = 0;
        j2 = 0;
        while (declaration != null) 
        {
            int k2;
            Object obj;
            int l2;
            if (j2 < l1)
            {
                l2 = j2 + 1;
                obj = aobj[j2];
                k2 = i2;
            } else
            if (j2 < l1 + j1)
            {
                if (j2 < j)
                {
                    l2 = j2 + 1;
                    obj = aobj[j2];
                } else
                {
                    obj = lambda.evalDefaultArg(k1, callcontext);
                    l2 = j2;
                }
                k1++;
                k2 = i2;
            } else
            if (lambda.max_args < 0 && j2 == l1 + j1)
            {
                if (declaration.type instanceof ArrayType)
                {
                    int i3 = j - j2;
                    Type type = ((ArrayType)declaration.type).getComponentType();
                    if (type == Type.objectType)
                    {
                        Object aobj2[] = new Object[i3];
                        System.arraycopy(((Object) (aobj)), j2, ((Object) (aobj2)), 0, i3);
                        obj = ((Object) (aobj2));
                    } else
                    {
                        obj = Array.newInstance(type.getReflectClass(), i3);
                        int j3 = 0;
                        while (j3 < i3) 
                        {
                            int k3 = j2 + j3;
                            Object obj2;
                            try
                            {
                                obj2 = type.coerceFromObject(aobj[k3]);
                            }
                            catch (ClassCastException classcastexception1)
                            {
                                return 0xfff40000 | j2 + j3;
                            }
                            Array.set(obj, j3, obj2);
                            j3++;
                        }
                    }
                    k2 = i2;
                    l2 = j2;
                } else
                {
                    obj = LList.makeList(aobj, j2);
                    k2 = i2;
                    l2 = j2;
                }
            } else
            {
                Keyword akeyword[] = lambda.keywords;
                k2 = i2 + 1;
                Keyword keyword = akeyword[i2];
                obj = Keyword.searchForKeyword(aobj, l1 + j1, keyword);
                Special special = Special.dfault;
                if (obj == special)
                {
                    obj = lambda.evalDefaultArg(k1, callcontext);
                }
                k1++;
                l2 = j2;
            }
            if (declaration.type != null)
            {
                Location location;
                Object obj1;
                try
                {
                    obj1 = declaration.type.coerceFromObject(obj);
                }
                catch (ClassCastException classcastexception)
                {
                    return 0xfff40000 | l2;
                }
                obj = obj1;
            }
            if (declaration.isIndirectBinding())
            {
                location = declaration.makeIndirectLocationFor();
                location.set(obj);
                obj = location;
            }
            aobj1[declaration.evalIndex] = obj;
            declaration = declaration.nextDecl();
            i2 = k2;
            j2 = l2;
        }
        callcontext.values = aobj1;
        callcontext.where = 0;
        callcontext.next = 0;
        callcontext.proc = this;
        return 0;
    }

    public int numArgs()
    {
        return lambda.min_args | lambda.max_args << 12;
    }
}
