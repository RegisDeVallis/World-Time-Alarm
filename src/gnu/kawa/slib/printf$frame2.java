// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.WrongType;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.numbers;
import kawa.lib.strings;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            printf

public class lambda.Fn5 extends ModuleBody
{
    public class printf.frame3 extends ModuleBody
    {

        final ModuleMethod lambda$Fn7;
        Object sgn;
        printf.frame2 staticLink;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 9)
            {
                return lambda11(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda11(Object obj, Object obj1)
        {
            printf.frame4 frame4_1 = new printf.frame4();
            frame4_1.staticLink = this;
            frame4_1.idigs = obj1;
            ModuleMethod modulemethod = frame4_1.lambda$Fn8;
            Object obj2 = Scheme.numLss.apply2(obj, Integer.valueOf(staticLink.staticLink.n));
            boolean flag;
            Char char1;
            CharSequence charsequence;
            int i;
            try
            {
                flag = ((Boolean)obj2).booleanValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj2);
            }
            if (!flag) goto _L2; else goto _L1
_L1:
            char1 = printf.Lit11;
            Object obj3 = staticLink.staticLink.str;
            try
            {
                charsequence = (CharSequence)obj3;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 1, obj3);
            }
            try
            {
                i = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 2, obj);
            }
            if (!characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence, i)))) goto _L4; else goto _L3
_L3:
            return Scheme.applyToArgs.apply2(modulemethod, AddOp.$Pl.apply2(obj, printf.Lit7));
_L2:
            if (flag) goto _L3; else goto _L4
_L4:
            return Scheme.applyToArgs.apply2(modulemethod, obj);
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 9)
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

        public printf.frame3()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 9, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:84");
            lambda$Fn7 = modulemethod;
        }
    }

    public class printf.frame4 extends ModuleBody
    {

        Object idigs;
        final ModuleMethod lambda$Fn8;
        final ModuleMethod lambda$Fn9;
        printf.frame3 staticLink;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 8)
            {
                return lambda12(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 7)
            {
                return lambda13(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda12(Object obj)
        {
            return staticLink.staticLink.staticLink.lambda3digits(obj, lambda$Fn9);
        }

        Object lambda13(Object obj, Object obj1)
        {
            printf.frame5 frame5_1 = new printf.frame5();
            frame5_1.staticLink = this;
            frame5_1.fdigs = obj1;
            ModuleMethod modulemethod = frame5_1.lambda$Fn10;
            printf.frame frame1 = staticLink.staticLink.staticLink;
            printf.frame6 frame6_1 = new printf.frame6();
            frame6_1.staticLink = frame1;
            frame6_1.cont = modulemethod;
            if (Scheme.numGEq.apply2(obj, Integer.valueOf(staticLink.staticLink.staticLink.n)) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply3(frame6_1.cont, obj, printf.Lit1);
            }
            Object obj2 = staticLink.staticLink.staticLink.str;
            CharSequence charsequence;
            int i;
            try
            {
                charsequence = (CharSequence)obj2;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-ref", 1, obj2);
            }
            try
            {
                i = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 2, obj);
            }
            if (lists.memv(Char.make(strings.stringRef(charsequence, i)), printf.Lit10) != Boolean.FALSE)
            {
                return staticLink.staticLink.staticLink.lambda2sign(AddOp.$Pl.apply2(obj, printf.Lit7), frame6_1.lambda$Fn11);
            } else
            {
                return Scheme.applyToArgs.apply3(frame6_1.cont, obj, printf.Lit1);
            }
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 8)
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
            if (modulemethod.selector == 7)
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

        public printf.frame4()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 7, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:90");
            lambda$Fn9 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 8, null, 4097);
            modulemethod1.setProperty("source-location", "printf.scm:87");
            lambda$Fn8 = modulemethod1;
        }
    }

    public class printf.frame5 extends ModuleBody
    {

        Object fdigs;
        final ModuleMethod lambda$Fn10;
        printf.frame4 staticLink;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 6)
            {
                return lambda14(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda14(Object obj, Object obj1)
        {
            Object aobj[] = new Object[3];
            aobj[0] = "0";
            aobj[1] = staticLink.idigs;
            aobj[2] = fdigs;
            gnu.lists.FString fstring = strings.stringAppend(aobj);
            int i = strings.stringLength(fstring);
            Object obj2 = printf.Lit7;
            AddOp addop = AddOp.$Pl;
            Object obj3 = staticLink.idigs;
            CharSequence charsequence;
            Object obj4;
            try
            {
                charsequence = (CharSequence)obj3;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-length", 1, obj3);
            }
            obj4 = addop.apply2(obj1, Integer.valueOf(strings.stringLength(charsequence)));
            do
            {
                if (Scheme.numGEq.apply2(obj2, Integer.valueOf(i)) != Boolean.FALSE)
                {
                    gnu.kawa.functions.ApplyToArgs applytoargs1 = Scheme.applyToArgs;
                    Object aobj2[] = new Object[5];
                    aobj2[0] = staticLink.staticLink.staticLink.cont;
                    aobj2[1] = obj;
                    aobj2[2] = staticLink.staticLink.sgn;
                    aobj2[3] = "0";
                    aobj2[4] = printf.Lit7;
                    return applytoargs1.applyN(aobj2);
                }
                Char char1 = printf.Lit9;
                int j;
                try
                {
                    j = ((Number)obj2).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-ref", 2, obj2);
                }
                if (characters.isChar$Eq(char1, Char.make(strings.stringRef(fstring, j))))
                {
                    obj2 = AddOp.$Pl.apply2(obj2, printf.Lit7);
                    obj4 = AddOp.$Mn.apply2(obj4, printf.Lit7);
                } else
                {
                    gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
                    Object aobj1[] = new Object[5];
                    aobj1[0] = staticLink.staticLink.staticLink.cont;
                    aobj1[1] = obj;
                    aobj1[2] = staticLink.staticLink.sgn;
                    Object obj5 = AddOp.$Mn.apply2(obj2, printf.Lit7);
                    int k;
                    try
                    {
                        k = ((Number)obj5).intValue();
                    }
                    catch (ClassCastException classcastexception2)
                    {
                        throw new WrongType(classcastexception2, "substring", 2, obj5);
                    }
                    aobj1[3] = strings.substring(fstring, k, i);
                    aobj1[4] = obj4;
                    return applytoargs.applyN(aobj1);
                }
            } while (true);
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 6)
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

        public printf.frame5()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 6, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:92");
            lambda$Fn10 = modulemethod;
        }
    }

    public class printf.frame6 extends ModuleBody
    {

        Object cont;
        final ModuleMethod lambda$Fn11;
        printf.frame staticLink;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 5)
            {
                return lambda15(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda15(Object obj, Object obj1)
        {
            printf.frame7 frame7_1 = new printf.frame7();
            frame7_1.staticLink = this;
            frame7_1.sgn = obj1;
            return staticLink.lambda3digits(obj, frame7_1.lambda$Fn12);
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

        public printf.frame6()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 5, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:67");
            lambda$Fn11 = modulemethod;
        }
    }

    public class printf.frame7 extends ModuleBody
    {

        final ModuleMethod lambda$Fn12;
        Object sgn;
        printf.frame6 staticLink;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 4)
            {
                return lambda16(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda16(Object obj, Object obj1)
        {
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Object obj2 = staticLink.cont;
            Char char1 = printf.Lit5;
            Object obj3 = sgn;
            Char char2;
            try
            {
                char2 = (Char)obj3;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "char=?", 2, obj3);
            }
            if (characters.isChar$Eq(char1, char2))
            {
                AddOp addop = AddOp.$Mn;
                CharSequence charsequence;
                Object obj4;
                CharSequence charsequence1;
                try
                {
                    charsequence1 = (CharSequence)obj1;
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "string->number", 1, obj1);
                }
                obj4 = addop.apply1(numbers.string$To$Number(charsequence1));
            } else
            {
                try
                {
                    charsequence = (CharSequence)obj1;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string->number", 1, obj1);
                }
                obj4 = numbers.string$To$Number(charsequence);
            }
            return applytoargs.apply3(obj2, obj, obj4);
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 4)
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

        public printf.frame7()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 4, null, 8194);
            modulemethod.setProperty("source-location", "printf.scm:69");
            lambda$Fn12 = modulemethod;
        }
    }


    Object cont;
    final ModuleMethod lambda$Fn5;
    final ModuleMethod lambda$Fn6;
     staticLink;

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        if (modulemethod.selector == 11)
        {
            return lambda9(obj);
        } else
        {
            return super.apply1(modulemethod, obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 10)
        {
            return lambda10(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda10(Object obj, Object obj1)
    {
        lambda10 lambda10_1 = new <init>();
        lambda10_1.staticLink = this;
        lambda10_1.sgn = obj1;
        return staticLink.ambda3digits(obj, lambda10_1.Fn7);
    }

    Object lambda9(Object obj)
    {
        return staticLink.ambda2sign(obj, lambda$Fn6);
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        if (modulemethod.selector == 11)
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
        if (modulemethod.selector == 10)
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

    public lambda.Fn7()
    {
        ModuleMethod modulemethod = new ModuleMethod(this, 10, null, 8194);
        modulemethod.setProperty("source-location", "printf.scm:81");
        lambda$Fn6 = modulemethod;
        ModuleMethod modulemethod1 = new ModuleMethod(this, 11, null, 4097);
        modulemethod1.setProperty("source-location", "printf.scm:78");
        lambda$Fn5 = modulemethod1;
    }
}
