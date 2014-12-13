// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.WrongType;

// Referenced classes of package gnu.expr:
//            Keyword, Language

public class GenericProc extends MethodProc
{

    int count;
    int maxArgs;
    protected MethodProc methods[];
    int minArgs;

    public GenericProc()
    {
    }

    public GenericProc(String s)
    {
        setName(s);
    }

    public static GenericProc make(Object aobj[])
    {
        GenericProc genericproc = new GenericProc();
        genericproc.setProperties(aobj);
        return genericproc;
    }

    public static transient GenericProc makeWithoutSorting(Object aobj[])
    {
        GenericProc genericproc = new GenericProc();
        int i = aobj.length;
        int j = 0;
        while (j < i) 
        {
            Object obj = aobj[j];
            if (obj instanceof Keyword)
            {
                Keyword keyword = (Keyword)obj;
                j++;
                genericproc.setProperty(keyword, aobj[j]);
            } else
            {
                genericproc.addAtEnd((MethodProc)obj);
            }
            j++;
        }
        return genericproc;
    }

    public void add(MethodProc methodproc)
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        i = count;
        addAtEnd(methodproc);
        int j = 0;
_L2:
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_65;
        }
        if (MethodProc.mostSpecific(methodproc, methods[j]) != methodproc)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        System.arraycopy(methods, j, methods, j + 1, i - j);
        methods[j] = methodproc;
        this;
        JVM INSTR monitorexit ;
        return;
        j++;
        if (true) goto _L2; else goto _L1
_L1:
        Exception exception;
        exception;
        throw exception;
    }

    protected void add(MethodProc amethodproc[])
    {
        this;
        JVM INSTR monitorenter ;
        int i;
        i = amethodproc.length;
        if (methods == null)
        {
            methods = new MethodProc[i];
        }
          goto _L1
_L3:
        int j;
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        add(amethodproc[j]);
        j++;
        continue; /* Loop/switch isn't completed */
        return;
        Exception exception;
        exception;
        throw exception;
_L1:
        j = 0;
        if (true) goto _L3; else goto _L2
_L2:
    }

    public void addAtEnd(MethodProc methodproc)
    {
        this;
        JVM INSTR monitorenter ;
        int i = count;
        if (methods != null) goto _L2; else goto _L1
_L1:
        methods = new MethodProc[8];
_L4:
        int k;
        methods[i] = methodproc;
        int j = methodproc.minArgs();
        if (j < minArgs || count == 0)
        {
            minArgs = j;
        }
        k = methodproc.maxArgs();
        if (k == -1)
        {
            break MISSING_BLOCK_LABEL_79;
        }
        if (k <= maxArgs)
        {
            break MISSING_BLOCK_LABEL_85;
        }
        maxArgs = k;
        count = i + 1;
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (i < methods.length) goto _L4; else goto _L3
_L3:
        MethodProc amethodproc[] = new MethodProc[2 * methods.length];
        System.arraycopy(methods, 0, amethodproc, 0, i);
        methods = amethodproc;
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    public Object applyN(Object aobj[])
        throws Throwable
    {
        if (count == 1)
        {
            return methods[0].applyN(aobj);
        }
        checkArgCount(this, aobj.length);
        CallContext callcontext = CallContext.getInstance();
        for (int i = 0; i < count; i++)
        {
            if (methods[i].matchN(aobj, callcontext) == 0)
            {
                return callcontext.runUntilValue();
            }
        }

        throw new WrongType(this, -1, null);
    }

    public MethodProc getMethod(int i)
    {
        if (i >= count)
        {
            return null;
        } else
        {
            return methods[i];
        }
    }

    public int getMethodCount()
    {
        return count;
    }

    public int isApplicable(Type atype[])
    {
        byte byte0 = -1;
        int i = count;
        do
        {
            int j;
label0:
            {
                if (--i >= 0)
                {
                    j = methods[i].isApplicable(atype);
                    if (j != 1)
                    {
                        break label0;
                    }
                    byte0 = 1;
                }
                return byte0;
            }
            if (j == 0)
            {
                byte0 = 0;
            }
        } while (true);
    }

    public int match0(CallContext callcontext)
    {
        if (count != 1) goto _L2; else goto _L1
_L1:
        int k = methods[0].match0(callcontext);
_L4:
        return k;
_L2:
        int i = 0;
label0:
        do
        {
label1:
            {
                if (i >= count)
                {
                    break label1;
                }
                int j = methods[i].match0(callcontext);
                k = 0;
                if (j == 0)
                {
                    break label0;
                }
                i++;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        callcontext.proc = null;
        return -1;
    }

    public int match1(Object obj, CallContext callcontext)
    {
        if (count != 1) goto _L2; else goto _L1
_L1:
        int k = methods[0].match1(obj, callcontext);
_L4:
        return k;
_L2:
        int i = 0;
label0:
        do
        {
label1:
            {
                if (i >= count)
                {
                    break label1;
                }
                int j = methods[i].match1(obj, callcontext);
                k = 0;
                if (j == 0)
                {
                    break label0;
                }
                i++;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        callcontext.proc = null;
        return -1;
    }

    public int match2(Object obj, Object obj1, CallContext callcontext)
    {
        if (count != 1) goto _L2; else goto _L1
_L1:
        int k = methods[0].match2(obj, obj1, callcontext);
_L4:
        return k;
_L2:
        int i = 0;
label0:
        do
        {
label1:
            {
                if (i >= count)
                {
                    break label1;
                }
                int j = methods[i].match2(obj, obj1, callcontext);
                k = 0;
                if (j == 0)
                {
                    break label0;
                }
                i++;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        callcontext.proc = null;
        return -1;
    }

    public int match3(Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        if (count != 1) goto _L2; else goto _L1
_L1:
        int k = methods[0].match3(obj, obj1, obj2, callcontext);
_L4:
        return k;
_L2:
        int i = 0;
label0:
        do
        {
label1:
            {
                if (i >= count)
                {
                    break label1;
                }
                int j = methods[i].match3(obj, obj1, obj2, callcontext);
                k = 0;
                if (j == 0)
                {
                    break label0;
                }
                i++;
            }
        } while (true);
        if (true) goto _L4; else goto _L3
_L3:
        callcontext.proc = null;
        return -1;
    }

    public int match4(Object obj, Object obj1, Object obj2, Object obj3, CallContext callcontext)
    {
        if (count == 1)
        {
            return methods[0].match4(obj, obj1, obj2, obj3, callcontext);
        }
        for (int i = 0; i < count; i++)
        {
            if (methods[i].match4(obj, obj1, obj2, obj3, callcontext) == 0)
            {
                return 0;
            }
        }

        callcontext.proc = null;
        return -1;
    }

    public int matchN(Object aobj[], CallContext callcontext)
    {
        int ai[];
        int k;
        int k1;
        if (count == 1)
        {
            return methods[0].matchN(aobj, callcontext);
        }
        int i = aobj.length;
        Type atype[] = new Type[i];
        Language language = Language.getDefaultLanguage();
        int j = 0;
        while (j < i) 
        {
            Object obj = aobj[j];
            Object obj1;
            if (obj == null)
            {
                obj1 = Type.nullType;
            } else
            {
                Class class1 = obj.getClass();
                if (language != null)
                {
                    obj1 = language.getTypeFor(class1);
                } else
                {
                    obj1 = Type.make(class1);
                }
            }
            atype[j] = ((Type) (obj1));
            j++;
        }
        ai = new int[count];
        k = 0;
        int l = 0;
        int i1 = -1;
        int j1 = 0;
        while (j1 < count) 
        {
            int i2 = methods[j1].isApplicable(atype);
            if (k == 0 && i2 >= 0)
            {
                i1 = j1;
            }
            if (i2 > 0)
            {
                k++;
            } else
            if (i2 == 0)
            {
                l++;
            }
            ai[j1] = i2;
            j1++;
        }
        if (k == 1 || k == 0 && l == 1)
        {
            return methods[i1].matchN(aobj, callcontext);
        }
        k1 = 0;
_L3:
        int l1;
        if (k1 >= count)
        {
            break; /* Loop/switch isn't completed */
        }
        l1 = ai[k1];
          goto _L1
_L5:
        k1++;
        if (true) goto _L3; else goto _L2
_L1:
        if (l1 < 0 || l1 == 0 && k > 0 || methods[k1].matchN(aobj, callcontext) != 0) goto _L5; else goto _L4
_L4:
        return 0;
_L2:
        callcontext.proc = null;
        return -1;
    }

    public int numArgs()
    {
        return minArgs | maxArgs << 12;
    }

    public final void setProperties(Object aobj[])
    {
        int i = aobj.length;
        int j = 0;
        while (j < i) 
        {
            Object obj = aobj[j];
            if (obj instanceof Keyword)
            {
                Keyword keyword = (Keyword)obj;
                j++;
                setProperty(keyword, aobj[j]);
            } else
            {
                add((MethodProc)obj);
            }
            j++;
        }
    }

    public void setProperty(Keyword keyword, Object obj)
    {
        String s = keyword.getName();
        if (s == "name")
        {
            setName(obj.toString());
            return;
        }
        if (s == "method")
        {
            add((MethodProc)obj);
            return;
        } else
        {
            super.setProperty(keyword.asSymbol(), obj);
            return;
        }
    }
}
