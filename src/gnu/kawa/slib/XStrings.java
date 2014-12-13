// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;

public class XStrings extends ModuleBody
{

    public static final XStrings $instance;
    static final IntNum Lit0 = IntNum.make(0x7fffffff);
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    public static final ModuleMethod string$Mnlength;
    public static final ModuleMethod substring;

    public XStrings()
    {
        ModuleInfo.register(this);
    }

    public static Object stringLength(Object obj)
    {
        if (obj == Values.empty)
        {
            return Values.empty;
        } else
        {
            return Integer.valueOf(((String)obj).length());
        }
    }

    public static Object substring(Object obj, Object obj1)
    {
        return substring(obj, obj1, Lit0);
    }

    public static Object substring(Object obj, Object obj1, Object obj2)
    {
        boolean flag;
        if (obj == Values.empty)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        if (!flag) goto _L4; else goto _L3
_L3:
        return Values.empty;
_L2:
        boolean flag1;
        String s;
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        if (obj1 == Values.empty)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag1 ? !flag1 : obj2 != Values.empty) goto _L4; else goto _L3
_L4:
        try
        {
            s = (String)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "s", -2, obj);
        }
        i = s.length();
        try
        {
            j = ((Number)obj1).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "sindex", -2, obj1);
        }
        k = j - 1;
        try
        {
            l = ((Number)obj2).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "len", -2, obj2);
        }
        i1 = i - k;
        if (l > i1)
        {
            j1 = i1;
        } else
        {
            j1 = l;
        }
        return s.substring(k, k + j1);
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        if (modulemethod.selector == 3)
        {
            return stringLength(obj);
        } else
        {
            return super.apply1(modulemethod, obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 1)
        {
            return substring(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
    {
        if (modulemethod.selector == 1)
        {
            return substring(obj, obj1, obj2);
        } else
        {
            return super.apply3(modulemethod, obj, obj1, obj2);
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        if (modulemethod.selector == 3)
        {
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        } else
        {
            return super.match1(modulemethod, obj, callcontext);
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        if (modulemethod.selector == 1)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        } else
        {
            return super.match2(modulemethod, obj, obj1, callcontext);
        }
    }

    public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        if (modulemethod.selector == 1)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;
        } else
        {
            return super.match3(modulemethod, obj, obj1, obj2, callcontext);
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit2 = (SimpleSymbol)(new SimpleSymbol("string-length")).readResolve();
        Lit1 = (SimpleSymbol)(new SimpleSymbol("substring")).readResolve();
        $instance = new XStrings();
        XStrings xstrings = $instance;
        substring = new ModuleMethod(xstrings, 1, Lit1, 12290);
        string$Mnlength = new ModuleMethod(xstrings, 3, Lit2, 4097);
        $instance.run();
    }
}
