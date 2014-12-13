// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.text.Char;

public class characters extends ModuleBody
{

    public static final characters $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    public static final ModuleMethod char$Eq$Qu;
    public static final ModuleMethod char$Gr$Eq$Qu;
    public static final ModuleMethod char$Gr$Qu;
    public static final ModuleMethod char$Ls$Eq$Qu;
    public static final ModuleMethod char$Ls$Qu;
    public static final ModuleMethod char$Mn$Grinteger;
    public static final ModuleMethod char$Qu;
    public static final ModuleMethod integer$Mn$Grchar;

    public characters()
    {
        ModuleInfo.register(this);
    }

    public static int char$To$Integer(Char char1)
    {
        return char1.intValue();
    }

    public static Char integer$To$Char(int i)
    {
        return Char.make(i);
    }

    public static boolean isChar(Object obj)
    {
        return obj instanceof Char;
    }

    public static boolean isChar$Eq(Char char1, Char char2)
    {
        return char1.intValue() == char2.intValue();
    }

    public static boolean isChar$Gr(Char char1, Char char2)
    {
        return char1.intValue() > char2.intValue();
    }

    public static boolean isChar$Gr$Eq(Char char1, Char char2)
    {
        return char1.intValue() >= char2.intValue();
    }

    public static boolean isChar$Ls(Char char1, Char char2)
    {
        return char1.intValue() < char2.intValue();
    }

    public static boolean isChar$Ls$Eq(Char char1, Char char2)
    {
        return char1.intValue() <= char2.intValue();
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply1(modulemethod, obj);

        case 1: // '\001'
            if (isChar(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 2: // '\002'
            int i;
            Char char1;
            try
            {
                char1 = (Char)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "char->integer", 1, obj);
            }
            return Integer.valueOf(char$To$Integer(char1));

        case 3: // '\003'
            break;
        }
        try
        {
            i = ((Number)obj).intValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "integer->char", 1, obj);
        }
        return integer$To$Char(i);
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 4: // '\004'
            Char char1;
            Char char2;
            Char char3;
            Char char4;
            Char char5;
            Char char6;
            Char char7;
            Char char8;
            Char char9;
            Char char10;
            try
            {
                char9 = (Char)obj;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "char=?", 1, obj);
            }
            try
            {
                char10 = (Char)obj1;
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "char=?", 2, obj1);
            }
            if (isChar$Eq(char9, char10))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 5: // '\005'
            try
            {
                char7 = (Char)obj;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "char<?", 1, obj);
            }
            try
            {
                char8 = (Char)obj1;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "char<?", 2, obj1);
            }
            if (isChar$Ls(char7, char8))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 6: // '\006'
            try
            {
                char5 = (Char)obj;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "char>?", 1, obj);
            }
            try
            {
                char6 = (Char)obj1;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "char>?", 2, obj1);
            }
            if (isChar$Gr(char5, char6))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 7: // '\007'
            try
            {
                char3 = (Char)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "char<=?", 1, obj);
            }
            try
            {
                char4 = (Char)obj1;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "char<=?", 2, obj1);
            }
            if (isChar$Ls$Eq(char3, char4))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 8: // '\b'
            break;
        }
        try
        {
            char1 = (Char)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "char>=?", 1, obj);
        }
        try
        {
            char2 = (Char)obj1;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "char>=?", 2, obj1);
        }
        if (isChar$Gr$Eq(char1, char2))
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 3: // '\003'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 2: // '\002'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 1: // '\001'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        int i = 0xfff40001;
        modulemethod.selector;
        JVM INSTR tableswitch 4 8: default 44
    //                   4 230
    //                   5 187
    //                   6 144
    //                   7 101
    //                   8 58;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        i = super.match2(modulemethod, obj, obj1, callcontext);
_L8:
        return i;
_L6:
        if (obj instanceof Char)
        {
            callcontext.value1 = obj;
            if (!(obj1 instanceof Char))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }
        continue; /* Loop/switch isn't completed */
_L5:
        if (obj instanceof Char)
        {
            callcontext.value1 = obj;
            if (!(obj1 instanceof Char))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (obj instanceof Char)
        {
            callcontext.value1 = obj;
            if (!(obj1 instanceof Char))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (obj instanceof Char)
        {
            callcontext.value1 = obj;
            if (!(obj1 instanceof Char))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if (obj instanceof Char)
        {
            callcontext.value1 = obj;
            if (!(obj1 instanceof Char))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }
        if (true) goto _L8; else goto _L7
_L7:
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit7 = (SimpleSymbol)(new SimpleSymbol("char>=?")).readResolve();
        Lit6 = (SimpleSymbol)(new SimpleSymbol("char<=?")).readResolve();
        Lit5 = (SimpleSymbol)(new SimpleSymbol("char>?")).readResolve();
        Lit4 = (SimpleSymbol)(new SimpleSymbol("char<?")).readResolve();
        Lit3 = (SimpleSymbol)(new SimpleSymbol("char=?")).readResolve();
        Lit2 = (SimpleSymbol)(new SimpleSymbol("integer->char")).readResolve();
        Lit1 = (SimpleSymbol)(new SimpleSymbol("char->integer")).readResolve();
        Lit0 = (SimpleSymbol)(new SimpleSymbol("char?")).readResolve();
        $instance = new characters();
        characters characters1 = $instance;
        char$Qu = new ModuleMethod(characters1, 1, Lit0, 4097);
        char$Mn$Grinteger = new ModuleMethod(characters1, 2, Lit1, 4097);
        integer$Mn$Grchar = new ModuleMethod(characters1, 3, Lit2, 4097);
        char$Eq$Qu = new ModuleMethod(characters1, 4, Lit3, 8194);
        char$Ls$Qu = new ModuleMethod(characters1, 5, Lit4, 8194);
        char$Gr$Qu = new ModuleMethod(characters1, 6, Lit5, 8194);
        char$Ls$Eq$Qu = new ModuleMethod(characters1, 7, Lit6, 8194);
        char$Gr$Eq$Qu = new ModuleMethod(characters1, 8, Lit7, 8194);
        $instance.run();
    }
}
