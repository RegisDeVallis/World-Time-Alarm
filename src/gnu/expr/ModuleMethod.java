// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import gnu.mapping.WrongArguments;
import java.lang.reflect.Method;

// Referenced classes of package gnu.expr:
//            ModuleBody, Compilation, Language

public class ModuleMethod extends MethodProc
{

    public ModuleBody module;
    protected int numArgs;
    public int selector;

    public ModuleMethod(ModuleBody modulebody, int i, Object obj, int j)
    {
        init(modulebody, i, obj, j);
    }

    public ModuleMethod(ModuleBody modulebody, int i, Object obj, int j, Object obj1)
    {
        init(modulebody, i, obj, j);
        argTypes = obj1;
    }

    public static Object apply0Default(ModuleMethod modulemethod)
        throws Throwable
    {
        return modulemethod.module.applyN(modulemethod, Values.noArgs);
    }

    public static Object apply1Default(ModuleMethod modulemethod, Object obj)
        throws Throwable
    {
        Object aobj[] = {
            obj
        };
        return modulemethod.module.applyN(modulemethod, aobj);
    }

    public static Object apply2Default(ModuleMethod modulemethod, Object obj, Object obj1)
        throws Throwable
    {
        Object aobj[] = {
            obj, obj1
        };
        return modulemethod.module.applyN(modulemethod, aobj);
    }

    public static Object apply3Default(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        throws Throwable
    {
        Object aobj[] = {
            obj, obj1, obj2
        };
        return modulemethod.module.applyN(modulemethod, aobj);
    }

    public static Object apply4Default(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3)
        throws Throwable
    {
        Object aobj[] = {
            obj, obj1, obj2, obj3
        };
        return modulemethod.module.applyN(modulemethod, aobj);
    }

    public static void applyError()
    {
        throw new Error("internal error - bad selector");
    }

    public static Object applyNDefault(ModuleMethod modulemethod, Object aobj[])
        throws Throwable
    {
        int i;
        int j;
        ModuleBody modulebody;
        i = aobj.length;
        j = modulemethod.numArgs();
        modulebody = modulemethod.module;
        if (i < (j & 0xfff) || j >= 0 && i > j >> 12) goto _L2; else goto _L1
_L1:
        i;
        JVM INSTR tableswitch 0 4: default 72
    //                   0 82
    //                   1 89
    //                   2 99
    //                   3 112
    //                   4 128;
           goto _L2 _L3 _L4 _L5 _L6 _L7
_L2:
        throw new WrongArguments(modulemethod, i);
_L3:
        return modulebody.apply0(modulemethod);
_L4:
        return modulebody.apply1(modulemethod, aobj[0]);
_L5:
        return modulebody.apply2(modulemethod, aobj[0], aobj[1]);
_L6:
        return modulebody.apply3(modulemethod, aobj[0], aobj[1], aobj[2]);
_L7:
        return modulebody.apply4(modulemethod, aobj[0], aobj[1], aobj[2], aobj[3]);
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        callcontext.pc;
        JVM INSTR tableswitch 0 5: default 44
    //                   0 71
    //                   1 82
    //                   2 94
    //                   3 110
    //                   4 130
    //                   5 154;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7
_L1:
        throw new Error((new StringBuilder()).append("internal error - apply ").append(this).toString());
_L2:
        Object obj = apply0();
_L9:
        callcontext.writeValue(obj);
        return;
_L3:
        obj = apply1(callcontext.value1);
        continue; /* Loop/switch isn't completed */
_L4:
        obj = apply2(callcontext.value1, callcontext.value2);
        continue; /* Loop/switch isn't completed */
_L5:
        obj = apply3(callcontext.value1, callcontext.value2, callcontext.value3);
        continue; /* Loop/switch isn't completed */
_L6:
        obj = apply4(callcontext.value1, callcontext.value2, callcontext.value3, callcontext.value4);
        continue; /* Loop/switch isn't completed */
_L7:
        obj = applyN(callcontext.values);
        if (true) goto _L9; else goto _L8
_L8:
    }

    public Object apply0()
        throws Throwable
    {
        return module.apply0(this);
    }

    public Object apply1(Object obj)
        throws Throwable
    {
        return module.apply1(this, obj);
    }

    public Object apply2(Object obj, Object obj1)
        throws Throwable
    {
        Object obj2;
        try
        {
            obj2 = module.apply2(this, obj, obj1);
        }
        catch (Exception exception)
        {
            throw exception;
        }
        return obj2;
    }

    public Object apply3(Object obj, Object obj1, Object obj2)
        throws Throwable
    {
        return module.apply3(this, obj, obj1, obj2);
    }

    public Object apply4(Object obj, Object obj1, Object obj2, Object obj3)
        throws Throwable
    {
        return module.apply4(this, obj, obj1, obj2, obj3);
    }

    public Object applyN(Object aobj[])
        throws Throwable
    {
        return module.applyN(this, aobj);
    }

    public ModuleMethod init(ModuleBody modulebody, int i, Object obj, int j)
    {
        module = modulebody;
        selector = i;
        numArgs = j;
        if (obj != null)
        {
            setSymbol(obj);
        }
        return this;
    }

    public int match0(CallContext callcontext)
    {
        callcontext.count = 0;
        callcontext.where = 0;
        return module.match0(this, callcontext);
    }

    public int match1(Object obj, CallContext callcontext)
    {
        callcontext.count = 1;
        callcontext.where = 1;
        return module.match1(this, obj, callcontext);
    }

    public int match2(Object obj, Object obj1, CallContext callcontext)
    {
        callcontext.count = 2;
        callcontext.where = 33;
        return module.match2(this, obj, obj1, callcontext);
    }

    public int match3(Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        callcontext.count = 3;
        callcontext.where = 801;
        return module.match3(this, obj, obj1, obj2, callcontext);
    }

    public int match4(Object obj, Object obj1, Object obj2, Object obj3, CallContext callcontext)
    {
        callcontext.count = 4;
        callcontext.where = 17185;
        return module.match4(this, obj, obj1, obj2, obj3, callcontext);
    }

    public int matchN(Object aobj[], CallContext callcontext)
    {
        callcontext.count = aobj.length;
        callcontext.where = 0;
        return module.matchN(this, aobj, callcontext);
    }

    public int numArgs()
    {
        return numArgs;
    }

    protected void resolveParameterTypes()
    {
        Method method;
        String s;
        method = null;
        s = getName();
        if (s == null) goto _L2; else goto _L1
_L1:
        Method amethod[];
        String s1;
        int i;
        amethod = module.getClass().getDeclaredMethods();
        s1 = Compilation.mangleNameIfNeeded(s);
        i = amethod.length;
_L6:
        if (--i < 0) goto _L4; else goto _L3
_L3:
        if (!amethod[i].getName().equals(s1)) goto _L6; else goto _L5
_L5:
        if (method == null) goto _L8; else goto _L7
_L7:
        method = null;
_L4:
        if (method == null) goto _L2; else goto _L9
_L9:
        Language language = Language.getDefaultLanguage();
        if (language == null) goto _L2; else goto _L10
_L10:
        Class aclass[];
        int j;
        Type atype[];
        aclass = method.getParameterTypes();
        j = aclass.length;
        atype = new Type[j];
        int k = j;
_L12:
        if (--k < 0)
        {
            break; /* Loop/switch isn't completed */
        }
        atype[k] = language.getTypeFor(aclass[k]);
        Throwable throwable;
        if (true) goto _L12; else goto _L11
_L2:
        if (argTypes == null)
        {
            super.resolveParameterTypes();
        }
        return;
_L8:
        method = amethod[i];
          goto _L6
_L11:
        try
        {
            argTypes = atype;
        }
        // Misplaced declaration of an exception variable
        catch (Throwable throwable) { }
          goto _L2
    }
}
