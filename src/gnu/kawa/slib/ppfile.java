// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.text.Char;
import gnu.text.Path;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.ports;
import kawa.lib.rnrs.unicode;
import kawa.standard.Scheme;
import kawa.standard.readchar;

// Referenced classes of package gnu.kawa.slib:
//            pp

public class ppfile extends ModuleBody
{
    public class frame extends ModuleBody
    {

        Object filter;
        final ModuleMethod lambda$Fn1;
        LList optarg;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 2)
            {
                return lambda1(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda1(Object obj)
        {
            frame0 frame0_1 = new frame0();
            frame0_1.staticLink = this;
            frame0_1.port = obj;
            ModuleMethod modulemethod = frame0_1.Fn2;
            Object obj1;
            if (lists.isNull(optarg))
            {
                obj1 = ports.current$Mnoutput$Mnport.apply0();
            } else
            {
                obj1 = lists.car.apply1(optarg);
            }
            if (ports.isOutputPort(obj1))
            {
                return frame0_1.lambda2(obj1);
            }
            Path path;
            try
            {
                path = Path.valueOf(obj1);
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "call-with-output-file", 1, obj1);
            }
            return ports.callWithOutputFile(path, modulemethod);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 2)
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

        public frame()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 2, null, 4097);
            modulemethod.setProperty("source-location", "ppfile.scm:27");
            lambda$Fn1 = modulemethod;
        }
    }

    public class frame0 extends ModuleBody
    {

        final ModuleMethod lambda$Fn2;
        Object port;
        frame staticLink;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 1)
            {
                return lambda2(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda2(Object obj)
        {
            Object obj1 = readchar.peekChar.apply1(port);
label0:
            do
            {
                boolean flag = ports.isEofObject(obj1);
                if (flag)
                {
                    if (flag)
                    {
                        return Boolean.TRUE;
                    } else
                    {
                        return Boolean.FALSE;
                    }
                }
                Char char1;
                Char char2;
                Char char3;
                Object obj2;
                InPort inport;
                Object obj3;
                boolean flag1;
                boolean flag2;
                Char char4;
                Char char5;
                try
                {
                    char1 = (Char)obj1;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "char-whitespace?", 1, obj1);
                }
                if (unicode.isCharWhitespace(char1))
                {
                    ports.display(readchar.readChar.apply1(port), obj);
                    obj1 = readchar.peekChar.apply1(port);
                    continue;
                }
                char2 = ppfile.Lit0;
                try
                {
                    char3 = (Char)obj1;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "char=?", 2, obj1);
                }
                if (characters.isChar$Eq(char2, char3))
                {
                    do
                    {
                        flag2 = ports.isEofObject(obj1);
                        if (flag2)
                        {
                            if (flag2)
                            {
                                return Boolean.TRUE;
                            } else
                            {
                                return Boolean.FALSE;
                            }
                        }
                        char4 = ppfile.Lit1;
                        try
                        {
                            char5 = (Char)obj1;
                        }
                        // Misplaced declaration of an exception variable
                        catch (ClassCastException classcastexception3)
                        {
                            throw new WrongType(classcastexception3, "char=?", 2, obj1);
                        }
                        if (characters.isChar$Eq(char4, char5))
                        {
                            ports.display(readchar.readChar.apply1(port), obj);
                            obj1 = readchar.peekChar.apply1(port);
                            continue label0;
                        }
                        ports.display(readchar.readChar.apply1(port), obj);
                        obj1 = readchar.peekChar.apply1(port);
                    } while (true);
                }
                obj2 = port;
                ClassCastException classcastexception3;
                try
                {
                    inport = (InPort)obj2;
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "read", 1, obj2);
                }
                obj3 = ports.read(inport);
                flag1 = ports.isEofObject(obj3);
                if (flag1)
                {
                    if (flag1)
                    {
                        return Boolean.TRUE;
                    } else
                    {
                        return Boolean.FALSE;
                    }
                }
                pp.prettyPrint(Scheme.applyToArgs.apply2(staticLink.filter, obj3), obj);
                obj1 = readchar.peekChar.apply1(port);
                if (Scheme.isEqv.apply2(ppfile.Lit1, obj1) != Boolean.FALSE)
                {
                    readchar.readChar.apply1(port);
                    obj1 = readchar.peekChar.apply1(port);
                }
            } while (true);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 1)
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

        public frame0()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 1, null, 4097);
            modulemethod.setProperty("source-location", "ppfile.scm:34");
            lambda$Fn2 = modulemethod;
        }
    }


    public static final ppfile $instance;
    static final Char Lit0 = Char.make(59);
    static final Char Lit1 = Char.make(10);
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final ModuleMethod lambda$Fn3;
    public static final ModuleMethod pprint$Mnfile;
    public static final ModuleMethod pprint$Mnfilter$Mnfile;

    public ppfile()
    {
        ModuleInfo.register(this);
    }

    static Object lambda3(Object obj)
    {
        return obj;
    }

    public static Object pprintFile(Object obj)
    {
        return pprintFile(obj, ports.current$Mnoutput$Mnport.apply0());
    }

    public static Object pprintFile(Object obj, Object obj1)
    {
        return pprintFilterFile$V(obj, lambda$Fn3, new Object[] {
            obj1
        });
    }

    public static Object pprintFilterFile$V(Object obj, Object obj1, Object aobj[])
    {
        frame frame1 = new frame();
        frame1.filter = obj1;
        frame1.optarg = LList.makeList(aobj, 0);
        ModuleMethod modulemethod = frame1.Fn1;
        if (ports.isInputPort(obj))
        {
            return frame1.lambda1(obj);
        }
        Path path;
        try
        {
            path = Path.valueOf(obj);
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "call-with-input-file", 1, obj);
        }
        return ports.callWithInputFile(path, modulemethod);
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply1(modulemethod, obj);

        case 4: // '\004'
            return lambda3(obj);

        case 5: // '\005'
            return pprintFile(obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 5)
        {
            return pprintFile(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    public Object applyN(ModuleMethod modulemethod, Object aobj[])
    {
        if (modulemethod.selector == 3)
        {
            Object obj = aobj[0];
            Object obj1 = aobj[1];
            int i = -2 + aobj.length;
            Object aobj1[] = new Object[i];
            do
            {
                if (--i < 0)
                {
                    return pprintFilterFile$V(obj, obj1, aobj1);
                }
                aobj1[i] = aobj[i + 2];
            } while (true);
        } else
        {
            return super.applyN(modulemethod, aobj);
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 5: // '\005'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 4: // '\004'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        if (modulemethod.selector == 5)
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

    public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
    {
        if (modulemethod.selector == 3)
        {
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;
        } else
        {
            return super.matchN(modulemethod, aobj, callcontext);
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit3 = (SimpleSymbol)(new SimpleSymbol("pprint-file")).readResolve();
        Lit2 = (SimpleSymbol)(new SimpleSymbol("pprint-filter-file")).readResolve();
        $instance = new ppfile();
        ppfile ppfile1 = $instance;
        pprint$Mnfilter$Mnfile = new ModuleMethod(ppfile1, 3, Lit2, -4094);
        ModuleMethod modulemethod = new ModuleMethod(ppfile1, 4, null, 4097);
        modulemethod.setProperty("source-location", "ppfile.scm:70");
        lambda$Fn3 = modulemethod;
        pprint$Mnfile = new ModuleMethod(ppfile1, 5, Lit3, 8193);
        $instance.run();
    }
}
