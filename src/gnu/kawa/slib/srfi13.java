// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.BitwiseOp;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.kawa.reflect.Invoke;
import gnu.lists.CharSeq;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;
import kawa.standard.call_with_values;

public class srfi13 extends ModuleBody
{
    public class frame extends ModuleBody
    {

        Object args;
        final ModuleMethod lambda$Fn1 = new ModuleMethod(this, 1, null, 0);
        final ModuleMethod lambda$Fn2;
        Object proc;
        Object s;
        int slen;
        Object start;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 1)
            {
                return lambda1();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 2)
            {
                return lambda2(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda1()
        {
            if (lists.isPair(args))
            {
                Object obj = lists.car.apply1(args);
                Object obj1 = lists.cdr.apply1(args);
                boolean flag = numbers.isInteger(obj);
                boolean flag1;
                if (flag ? (flag1 = numbers.isExact(obj)) ? Scheme.numLEq.apply2(obj, Integer.valueOf(slen)) != Boolean.FALSE : flag1 : flag)
                {
                    return misc.values(new Object[] {
                        obj, obj1
                    });
                } else
                {
                    Object aobj1[] = new Object[3];
                    aobj1[0] = proc;
                    aobj1[1] = obj;
                    aobj1[2] = s;
                    return misc.error$V("Illegal substring END spec", aobj1);
                }
            } else
            {
                Object aobj[] = new Object[2];
                aobj[0] = Integer.valueOf(slen);
                aobj[1] = args;
                return misc.values(aobj);
            }
        }

        Object lambda2(Object obj, Object obj1)
        {
            if (Scheme.numLEq.apply2(start, obj) != Boolean.FALSE)
            {
                Object aobj1[] = new Object[3];
                aobj1[0] = obj1;
                aobj1[1] = start;
                aobj1[2] = obj;
                return misc.values(aobj1);
            } else
            {
                Object aobj[] = new Object[4];
                aobj[0] = proc;
                aobj[1] = start;
                aobj[2] = obj;
                aobj[3] = s;
                return misc.error$V("Illegal substring START/END spec", aobj);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 1)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 2)
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

        public frame()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 2, null, 8194);
            modulemethod.setProperty("source-location", "srfi13.scm:150");
            lambda$Fn2 = modulemethod;
        }
    }

    public class frame0 extends ModuleBody
    {

        Object args;
        final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, null, 0);
        final ModuleMethod lambda$Fn4;
        Object proc;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 3)
            {
                return lambda3();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 4)
            {
                return lambda4(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda3()
        {
            return srfi13.stringParseStart$PlEnd(proc, s, args);
        }

        Object lambda4(Object obj, Object obj1, Object obj2)
        {
            if (lists.isPair(obj))
            {
                Object aobj[] = new Object[2];
                aobj[0] = proc;
                aobj[1] = obj;
                return misc.error$V("Extra arguments to procedure", aobj);
            } else
            {
                return misc.values(new Object[] {
                    obj1, obj2
                });
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 3)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 4)
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

        public frame0()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 4, null, 12291);
            modulemethod.setProperty("source-location", "srfi13.scm:174");
            lambda$Fn4 = modulemethod;
        }
    }

    public class frame1 extends ModuleBody
    {

        final ModuleMethod lambda$Fn6;
        int slen;
        Object start;

        static boolean lambda5(Object obj)
        {
            boolean flag = numbers.isInteger(obj);
            if (flag)
            {
                flag = numbers.isExact(obj);
                if (flag)
                {
                    flag = ((Boolean)Scheme.numLEq.apply2(srfi13.Lit0, obj)).booleanValue();
                }
            }
            return flag;
        }

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 5)
            {
                if (lambda6(obj))
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        boolean lambda6(Object obj)
        {
            boolean flag = numbers.isInteger(obj);
            if (flag)
            {
                flag = numbers.isExact(obj);
                if (flag)
                {
                    Object obj1 = Scheme.numLEq.apply2(start, obj);
                    boolean flag1;
                    try
                    {
                        flag1 = ((Boolean)obj1).booleanValue();
                    }
                    catch (ClassCastException classcastexception)
                    {
                        throw new WrongType(classcastexception, "x", -2, obj1);
                    }
                    flag = flag1;
                    if (flag)
                    {
                        flag = ((Boolean)Scheme.numLEq.apply2(obj, Integer.valueOf(slen))).booleanValue();
                    }
                }
            }
            return flag;
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 5)
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

        public frame1()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 5, null, 4097);
            modulemethod.setProperty("source-location", "srfi13.scm:227");
            lambda$Fn6 = modulemethod;
        }
    }

    public class frame10 extends ModuleBody
    {

        Object criterion;
        final ModuleMethod lambda$Fn25 = new ModuleMethod(this, 22, null, 0);
        final ModuleMethod lambda$Fn26 = new ModuleMethod(this, 23, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 22)
            {
                return lambda25();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 23)
            {
                return lambda26(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda25()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnany, s, maybe$Mnstart$Plend);
        }

        Object lambda26(Object obj, Object obj1)
        {
            if (!characters.isChar(criterion)) goto _L2; else goto _L1
_L1:
            Object obj13 = obj;
_L7:
            Object obj4;
            Object obj5;
            Object obj8;
            Object obj9;
            Object obj12;
            Object obj14 = Scheme.numLss.apply2(obj13, obj1);
            gnu.kawa.functions.ApplyToArgs applytoargs;
            Location location;
            Object obj2;
            Object aobj[];
            boolean flag;
            CharSequence charsequence;
            int i;
            char c;
            Object obj6;
            Object obj7;
            boolean flag1;
            gnu.kawa.functions.ApplyToArgs applytoargs1;
            Location location1;
            Object obj10;
            Object obj11;
            CharSequence charsequence1;
            int j;
            boolean flag2;
            Boolean boolean1;
            Char char1;
            CharSequence charsequence2;
            int k;
            boolean flag3;
            try
            {
                flag2 = ((Boolean)obj14).booleanValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "x", -2, obj14);
            }
            if (!flag2) goto _L4; else goto _L3
_L3:
            Object obj15 = criterion;
            Object obj16;
            try
            {
                char1 = (Char)obj15;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "char=?", 1, obj15);
            }
            obj16 = s;
            try
            {
                charsequence2 = (CharSequence)obj16;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "string-ref", 1, obj16);
            }
            try
            {
                k = ((Number)obj13).intValue();
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "string-ref", 2, obj13);
            }
            flag3 = characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence2, k)));
            if (!flag3) goto _L6; else goto _L5
_L5:
            if (flag3)
            {
                boolean1 = Boolean.TRUE;
            } else
            {
                boolean1 = Boolean.FALSE;
            }
_L8:
            obj7 = boolean1;
_L10:
            return obj7;
_L6:
            obj13 = AddOp.$Pl.apply2(obj13, srfi13.Lit1);
              goto _L7
_L4:
            if (flag2)
            {
                boolean1 = Boolean.TRUE;
            } else
            {
                boolean1 = Boolean.FALSE;
            }
              goto _L8
_L2:
            applytoargs = Scheme.applyToArgs;
            location = srfi13.loc$char$Mnset$Qu;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 515, 5);
                throw unboundlocationexception;
            }
            if (applytoargs.apply2(obj2, criterion) == Boolean.FALSE)
            {
                break MISSING_BLOCK_LABEL_324;
            }
            obj8 = obj;
            obj9 = Scheme.numLss.apply2(obj8, obj1);
            try
            {
                flag1 = ((Boolean)obj9).booleanValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "x", -2, obj9);
            }
            if (!flag1)
            {
                break MISSING_BLOCK_LABEL_311;
            }
            applytoargs1 = Scheme.applyToArgs;
            location1 = srfi13.loc$char$Mnset$Mncontains$Qu;
            try
            {
                obj10 = location1.get();
            }
            catch (UnboundLocationException unboundlocationexception1)
            {
                unboundlocationexception1.setLine("srfi13.scm", 518, 9);
                throw unboundlocationexception1;
            }
            obj11 = criterion;
            obj12 = s;
            try
            {
                charsequence1 = (CharSequence)obj12;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "string-ref", 1, obj12);
            }
            try
            {
                j = ((Number)obj8).intValue();
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "string-ref", 2, obj8);
            }
            obj7 = applytoargs1.apply3(obj10, obj11, Char.make(strings.stringRef(charsequence1, j)));
            if (obj7 != Boolean.FALSE) goto _L10; else goto _L9
_L9:
            obj8 = AddOp.$Pl.apply2(obj8, srfi13.Lit1);
            break MISSING_BLOCK_LABEL_194;
            if (flag1)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
            if (!misc.isProcedure(criterion))
            {
                break MISSING_BLOCK_LABEL_483;
            }
            Object obj3 = Scheme.numLss.apply2(obj, obj1);
            try
            {
                flag = ((Boolean)obj3).booleanValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj3);
            }
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_470;
            }
            obj4 = obj;
            obj5 = s;
            try
            {
                charsequence = (CharSequence)obj5;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 1, obj5);
            }
            i = ((Number)obj4).intValue();
            c = strings.stringRef(charsequence, i);
            obj6 = AddOp.$Pl.apply2(obj4, srfi13.Lit1);
            if (Scheme.numEqu.apply2(obj6, obj1) != Boolean.FALSE)
            {
                return Scheme.applyToArgs.apply2(criterion, Char.make(c));
            }
            obj7 = Scheme.applyToArgs.apply2(criterion, Char.make(c));
            if (obj7 != Boolean.FALSE) goto _L10; else goto _L11
_L11:
            obj4 = obj6;
            break MISSING_BLOCK_LABEL_362;
            if (flag)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
            aobj = new Object[2];
            aobj[0] = srfi13.string$Mnany;
            aobj[1] = criterion;
            return misc.error$V("Second param is neither char-set, char, or predicate procedure.", aobj);
            ClassCastException classcastexception2;
            classcastexception2;
            throw new WrongType(classcastexception2, "string-ref", 2, obj4);
              goto _L8
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 22)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 23)
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

        public frame10()
        {
        }
    }

    public class frame11 extends ModuleBody
    {

        final ModuleMethod lambda$Fn28 = new ModuleMethod(this, 26, null, 0);
        final ModuleMethod lambda$Fn29 = new ModuleMethod(this, 27, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 26)
            {
                return lambda28();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 27)
            {
                return lambda29(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda28()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Mnlength, s1, maybe$Mnstarts$Plends);
        }

        Object lambda29(Object obj, Object obj1, Object obj2)
        {
            frame12 frame12_1 = new frame12();
            frame12_1.staticLink = this;
            frame12_1.rest = obj;
            frame12_1.start1 = obj1;
            frame12_1.end1 = obj2;
            return call_with_values.callWithValues(frame12_1.Fn30, frame12_1.Fn31);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 26)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 27)
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

        public frame11()
        {
        }
    }

    public class frame12 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn30 = new ModuleMethod(this, 24, null, 0);
        final ModuleMethod lambda$Fn31 = new ModuleMethod(this, 25, null, 8194);
        Object rest;
        Object start1;
        frame11 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 24)
            {
                return lambda30();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 25)
            {
                return lambda31(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda30()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnlength, staticLink.s2, rest);
        }

        Object lambda31(Object obj, Object obj1)
        {
            return srfi13.$PcStringPrefixLength(staticLink.s1, start1, end1, staticLink.s2, obj, obj1);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 24)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 25)
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

        public frame12()
        {
        }
    }

    public class frame13 extends ModuleBody
    {

        final ModuleMethod lambda$Fn32 = new ModuleMethod(this, 30, null, 0);
        final ModuleMethod lambda$Fn33 = new ModuleMethod(this, 31, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 30)
            {
                return lambda32();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 31)
            {
                return lambda33(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda32()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Mnlength, s1, maybe$Mnstarts$Plends);
        }

        Object lambda33(Object obj, Object obj1, Object obj2)
        {
            frame14 frame14_1 = new frame14();
            frame14_1.staticLink = this;
            frame14_1.rest = obj;
            frame14_1.start1 = obj1;
            frame14_1.end1 = obj2;
            return call_with_values.callWithValues(frame14_1.Fn34, frame14_1.Fn35);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 30)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 31)
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

        public frame13()
        {
        }
    }

    public class frame14 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn34 = new ModuleMethod(this, 28, null, 0);
        final ModuleMethod lambda$Fn35 = new ModuleMethod(this, 29, null, 8194);
        Object rest;
        Object start1;
        frame13 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 28)
            {
                return lambda34();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 29)
            {
                return lambda35(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda34()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnlength, staticLink.s2, rest);
        }

        Object lambda35(Object obj, Object obj1)
        {
            return srfi13.$PcStringSuffixLength(staticLink.s1, start1, end1, staticLink.s2, obj, obj1);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 28)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 29)
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

        public frame14()
        {
        }
    }

    public class frame15 extends ModuleBody
    {

        final ModuleMethod lambda$Fn36 = new ModuleMethod(this, 34, null, 0);
        final ModuleMethod lambda$Fn37 = new ModuleMethod(this, 35, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 34)
            {
                return lambda36();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 35)
            {
                return lambda37(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda36()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Mnlength$Mnci, s1, maybe$Mnstarts$Plends);
        }

        Object lambda37(Object obj, Object obj1, Object obj2)
        {
            frame16 frame16_1 = new frame16();
            frame16_1.staticLink = this;
            frame16_1.rest = obj;
            frame16_1.start1 = obj1;
            frame16_1.end1 = obj2;
            return call_with_values.callWithValues(frame16_1.Fn38, frame16_1.Fn39);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 34)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 35)
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

        public frame15()
        {
        }
    }

    public class frame16 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn38 = new ModuleMethod(this, 32, null, 0);
        final ModuleMethod lambda$Fn39 = new ModuleMethod(this, 33, null, 8194);
        Object rest;
        Object start1;
        frame15 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 32)
            {
                return lambda38();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 33)
            {
                return Integer.valueOf(lambda39(obj, obj1));
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda38()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnlength$Mnci, staticLink.s2, rest);
        }

        int lambda39(Object obj, Object obj1)
        {
            Object obj2 = staticLink.s1;
            Object obj3 = start1;
            int i;
            Object obj4;
            int j;
            Object obj5;
            int k;
            int l;
            try
            {
                i = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "%string-prefix-length-ci", 1, obj3);
            }
            obj4 = end1;
            try
            {
                j = ((Number)obj4).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "%string-prefix-length-ci", 2, obj4);
            }
            obj5 = staticLink.s2;
            try
            {
                k = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "%string-prefix-length-ci", 4, obj);
            }
            try
            {
                l = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "%string-prefix-length-ci", 5, obj1);
            }
            return srfi13.$PcStringPrefixLengthCi(obj2, i, j, obj5, k, l);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 32)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 33)
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

        public frame16()
        {
        }
    }

    public class frame17 extends ModuleBody
    {

        final ModuleMethod lambda$Fn40 = new ModuleMethod(this, 38, null, 0);
        final ModuleMethod lambda$Fn41 = new ModuleMethod(this, 39, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 38)
            {
                return lambda40();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 39)
            {
                return lambda41(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda40()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Mnlength$Mnci, s1, maybe$Mnstarts$Plends);
        }

        Object lambda41(Object obj, Object obj1, Object obj2)
        {
            frame18 frame18_1 = new frame18();
            frame18_1.staticLink = this;
            frame18_1.rest = obj;
            frame18_1.start1 = obj1;
            frame18_1.end1 = obj2;
            return call_with_values.callWithValues(frame18_1.Fn42, frame18_1.Fn43);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 38)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 39)
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

        public frame17()
        {
        }
    }

    public class frame18 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn42 = new ModuleMethod(this, 36, null, 0);
        final ModuleMethod lambda$Fn43 = new ModuleMethod(this, 37, null, 8194);
        Object rest;
        Object start1;
        frame17 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 36)
            {
                return lambda42();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 37)
            {
                return Integer.valueOf(lambda43(obj, obj1));
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda42()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnlength$Mnci, staticLink.s2, rest);
        }

        int lambda43(Object obj, Object obj1)
        {
            Object obj2 = staticLink.s1;
            Object obj3 = start1;
            int i;
            Object obj4;
            int j;
            Object obj5;
            int k;
            int l;
            try
            {
                i = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "%string-suffix-length-ci", 1, obj3);
            }
            obj4 = end1;
            try
            {
                j = ((Number)obj4).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "%string-suffix-length-ci", 2, obj4);
            }
            obj5 = staticLink.s2;
            try
            {
                k = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "%string-suffix-length-ci", 4, obj);
            }
            try
            {
                l = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "%string-suffix-length-ci", 5, obj1);
            }
            return srfi13.$PcStringSuffixLengthCi(obj2, i, j, obj5, k, l);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 36)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 37)
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

        public frame18()
        {
        }
    }

    public class frame19 extends ModuleBody
    {

        final ModuleMethod lambda$Fn44 = new ModuleMethod(this, 42, null, 0);
        final ModuleMethod lambda$Fn45 = new ModuleMethod(this, 43, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 42)
            {
                return lambda44();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 43)
            {
                return lambda45(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda44()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Qu, s1, maybe$Mnstarts$Plends);
        }

        Object lambda45(Object obj, Object obj1, Object obj2)
        {
            frame20 frame20_1 = new frame20();
            frame20_1.staticLink = this;
            frame20_1.rest = obj;
            frame20_1.start1 = obj1;
            frame20_1.end1 = obj2;
            return call_with_values.callWithValues(frame20_1.Fn46, frame20_1.Fn47);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 42)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 43)
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

        public frame19()
        {
        }
    }

    public class frame2 extends ModuleBody
    {

        final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 6, null, 0);
        final ModuleMethod lambda$Fn8 = new ModuleMethod(this, 7, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 6)
            {
                return lambda7();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 7)
            {
                return lambda8(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda7()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncopy, s, maybe$Mnstart$Plend);
        }

        CharSequence lambda8(Object obj, Object obj1)
        {
            Object obj2 = s;
            CharSequence charsequence;
            int i;
            int j;
            try
            {
                charsequence = (CharSequence)obj2;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "substring", 1, obj2);
            }
            try
            {
                i = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "substring", 2, obj);
            }
            try
            {
                j = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "substring", 3, obj1);
            }
            return strings.substring(charsequence, i, j);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 6)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
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

        public frame2()
        {
        }
    }

    public class frame20 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn46 = new ModuleMethod(this, 40, null, 0);
        final ModuleMethod lambda$Fn47 = new ModuleMethod(this, 41, null, 8194);
        Object rest;
        Object start1;
        frame19 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 40)
            {
                return lambda46();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 41)
            {
                return lambda47(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda46()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Qu, staticLink.s2, rest);
        }

        Object lambda47(Object obj, Object obj1)
        {
            return srfi13.$PcStringPrefix$Qu(staticLink.s1, start1, end1, staticLink.s2, obj, obj1);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 40)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 41)
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

        public frame20()
        {
        }
    }

    public class frame21 extends ModuleBody
    {

        final ModuleMethod lambda$Fn48 = new ModuleMethod(this, 46, null, 0);
        final ModuleMethod lambda$Fn49 = new ModuleMethod(this, 47, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 46)
            {
                return lambda48();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 47)
            {
                return lambda49(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda48()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Qu, s1, maybe$Mnstarts$Plends);
        }

        Object lambda49(Object obj, Object obj1, Object obj2)
        {
            frame22 frame22_1 = new frame22();
            frame22_1.staticLink = this;
            frame22_1.rest = obj;
            frame22_1.start1 = obj1;
            frame22_1.end1 = obj2;
            return call_with_values.callWithValues(frame22_1.Fn50, frame22_1.Fn51);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 46)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 47)
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

        public frame21()
        {
        }
    }

    public class frame22 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn50 = new ModuleMethod(this, 44, null, 0);
        final ModuleMethod lambda$Fn51 = new ModuleMethod(this, 45, null, 8194);
        Object rest;
        Object start1;
        frame21 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 44)
            {
                return lambda50();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 45)
            {
                return lambda51(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda50()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Qu, staticLink.s2, rest);
        }

        Object lambda51(Object obj, Object obj1)
        {
            return srfi13.$PcStringSuffix$Qu(staticLink.s1, start1, end1, staticLink.s2, obj, obj1);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 44)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 45)
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

        public frame22()
        {
        }
    }

    public class frame23 extends ModuleBody
    {

        final ModuleMethod lambda$Fn52 = new ModuleMethod(this, 50, null, 0);
        final ModuleMethod lambda$Fn53 = new ModuleMethod(this, 51, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 50)
            {
                return lambda52();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 51)
            {
                return lambda53(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda52()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnprefix$Mnci$Qu, s1, maybe$Mnstarts$Plends);
        }

        Object lambda53(Object obj, Object obj1, Object obj2)
        {
            frame24 frame24_1 = new frame24();
            frame24_1.staticLink = this;
            frame24_1.rest = obj;
            frame24_1.start1 = obj1;
            frame24_1.end1 = obj2;
            return call_with_values.callWithValues(frame24_1.Fn54, frame24_1.Fn55);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 50)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 51)
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

        public frame23()
        {
        }
    }

    public class frame24 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn54 = new ModuleMethod(this, 48, null, 0);
        final ModuleMethod lambda$Fn55 = new ModuleMethod(this, 49, null, 8194);
        Object rest;
        Object start1;
        frame23 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 48)
            {
                return lambda54();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 49)
            {
                return lambda55(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda54()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnprefix$Mnci$Qu, staticLink.s2, rest);
        }

        Object lambda55(Object obj, Object obj1)
        {
            return srfi13.$PcStringPrefixCi$Qu(staticLink.s1, start1, end1, staticLink.s2, obj, obj1);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 48)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 49)
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

        public frame24()
        {
        }
    }

    public class frame25 extends ModuleBody
    {

        final ModuleMethod lambda$Fn56 = new ModuleMethod(this, 54, null, 0);
        final ModuleMethod lambda$Fn57 = new ModuleMethod(this, 55, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 54)
            {
                return lambda56();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 55)
            {
                return lambda57(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda56()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnsuffix$Mnci$Qu, s1, maybe$Mnstarts$Plends);
        }

        Object lambda57(Object obj, Object obj1, Object obj2)
        {
            frame26 frame26_1 = new frame26();
            frame26_1.staticLink = this;
            frame26_1.rest = obj;
            frame26_1.start1 = obj1;
            frame26_1.end1 = obj2;
            return call_with_values.callWithValues(frame26_1.Fn58, frame26_1.Fn59);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 54)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 55)
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

        public frame25()
        {
        }
    }

    public class frame26 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn58 = new ModuleMethod(this, 52, null, 0);
        final ModuleMethod lambda$Fn59 = new ModuleMethod(this, 53, null, 8194);
        Object rest;
        Object start1;
        frame25 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 52)
            {
                return lambda58();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 53)
            {
                return lambda59(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda58()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnsuffix$Mnci$Qu, staticLink.s2, rest);
        }

        Object lambda59(Object obj, Object obj1)
        {
            return srfi13.$PcStringSuffixCi$Qu(staticLink.s1, start1, end1, staticLink.s2, obj, obj1);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 52)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 53)
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

        public frame26()
        {
        }
    }

    public class frame27 extends ModuleBody
    {

        final ModuleMethod lambda$Fn60 = new ModuleMethod(this, 58, null, 0);
        final ModuleMethod lambda$Fn61 = new ModuleMethod(this, 59, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object proc$Eq;
        Object proc$Gr;
        Object proc$Ls;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 58)
            {
                return lambda60();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 59)
            {
                return lambda61(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda60()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mncompare, s1, maybe$Mnstarts$Plends);
        }

        Object lambda61(Object obj, Object obj1, Object obj2)
        {
            frame28 frame28_1 = new frame28();
            frame28_1.staticLink = this;
            frame28_1.rest = obj;
            frame28_1.start1 = obj1;
            frame28_1.end1 = obj2;
            return call_with_values.callWithValues(frame28_1.Fn62, frame28_1.Fn63);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 58)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 59)
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

        public frame27()
        {
        }
    }

    public class frame28 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn62 = new ModuleMethod(this, 56, null, 0);
        final ModuleMethod lambda$Fn63 = new ModuleMethod(this, 57, null, 8194);
        Object rest;
        Object start1;
        frame27 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 56)
            {
                return lambda62();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 57)
            {
                return lambda63(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda62()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncompare, staticLink.s2, rest);
        }

        Object lambda63(Object obj, Object obj1)
        {
            return srfi13.$PcStringCompare(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, staticLink.Ls, staticLink.Eq, staticLink.Gr);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 56)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 57)
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

        public frame28()
        {
        }
    }

    public class frame29 extends ModuleBody
    {

        final ModuleMethod lambda$Fn64 = new ModuleMethod(this, 62, null, 0);
        final ModuleMethod lambda$Fn65 = new ModuleMethod(this, 63, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object proc$Eq;
        Object proc$Gr;
        Object proc$Ls;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 62)
            {
                return lambda64();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 63)
            {
                return lambda65(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda64()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mncompare$Mnci, s1, maybe$Mnstarts$Plends);
        }

        Object lambda65(Object obj, Object obj1, Object obj2)
        {
            frame30 frame30_1 = new frame30();
            frame30_1.staticLink = this;
            frame30_1.rest = obj;
            frame30_1.start1 = obj1;
            frame30_1.end1 = obj2;
            return call_with_values.callWithValues(frame30_1.Fn66, frame30_1.Fn67);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 62)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 63)
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

        public frame29()
        {
        }
    }

    public class frame3 extends ModuleBody
    {

        final ModuleMethod lambda$Fn10 = new ModuleMethod(this, 9, null, 8194);
        final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 8, null, 0);
        LList maybe$Mnstart$Plend;
        Object proc;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 8)
            {
                return lambda9();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 9)
            {
                return lambda10(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda10(Object obj, Object obj1)
        {
            return srfi13.$PcStringMap(proc, s, obj, obj1);
        }

        Object lambda9()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnmap, s, maybe$Mnstart$Plend);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 8)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
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

        public frame3()
        {
        }
    }

    public class frame30 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn66 = new ModuleMethod(this, 60, null, 0);
        final ModuleMethod lambda$Fn67 = new ModuleMethod(this, 61, null, 8194);
        Object rest;
        Object start1;
        frame29 staticLink;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 60)
            {
                return lambda66();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 61)
            {
                return lambda67(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda66()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncompare$Mnci, staticLink.s2, rest);
        }

        Object lambda67(Object obj, Object obj1)
        {
            return srfi13.$PcStringCompareCi(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, staticLink.Ls, staticLink.Eq, staticLink.Gr);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 60)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 61)
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

        public frame30()
        {
        }
    }

    public class frame31 extends ModuleBody
    {

        final ModuleMethod lambda$Fn68 = new ModuleMethod(this, 66, null, 0);
        final ModuleMethod lambda$Fn69 = new ModuleMethod(this, 67, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 66)
            {
                return lambda68();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 67)
            {
                return lambda69(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda68()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Eq, s1, maybe$Mnstarts$Plends);
        }

        Object lambda69(Object obj, Object obj1, Object obj2)
        {
            frame32 frame32_1 = new frame32();
            frame32_1.staticLink = this;
            frame32_1.rest = obj;
            frame32_1.start1 = obj1;
            frame32_1.end1 = obj2;
            return call_with_values.callWithValues(frame32_1.Fn70, frame32_1.Fn71);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 66)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 67)
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

        public frame31()
        {
        }
    }

    public class frame32 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn70 = new ModuleMethod(this, 64, null, 0);
        final ModuleMethod lambda$Fn71 = new ModuleMethod(this, 65, null, 8194);
        Object rest;
        Object start1;
        frame31 staticLink;

        static Boolean lambda72(Object obj)
        {
            return Boolean.FALSE;
        }

        static Boolean lambda73(Object obj)
        {
            return Boolean.FALSE;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 64)
            {
                return lambda70();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 65)
            {
                return lambda71(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda70()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Eq, staticLink.s2, rest);
        }

        Object lambda71(Object obj, Object obj1)
        {
            Object obj2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(end1, start1), AddOp.$Mn.apply2(obj1, obj));
            boolean flag;
            try
            {
                flag = ((Boolean)obj2).booleanValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj2);
            }
            if (flag)
            {
                boolean flag1;
                if (staticLink.s1 == staticLink.s2)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    Object obj3 = Scheme.numEqu.apply2(start1, obj);
                    boolean flag2;
                    try
                    {
                        flag2 = ((Boolean)obj3).booleanValue();
                    }
                    catch (ClassCastException classcastexception1)
                    {
                        throw new WrongType(classcastexception1, "x", -2, obj3);
                    }
                    flag1 = flag2;
                }
                if (flag1)
                {
                    if (flag1)
                    {
                        return Boolean.TRUE;
                    } else
                    {
                        return Boolean.FALSE;
                    }
                } else
                {
                    return srfi13.$PcStringCompare(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, srfi13.lambda$Fn72, misc.values, srfi13.lambda$Fn73);
                }
            }
            if (flag)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 64)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 65)
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

        public frame32()
        {
        }
    }

    public class frame33 extends ModuleBody
    {

        final ModuleMethod lambda$Fn74 = new ModuleMethod(this, 70, null, 0);
        final ModuleMethod lambda$Fn75 = new ModuleMethod(this, 71, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 70)
            {
                return lambda74();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 71)
            {
                return lambda75(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda74()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Ls$Gr, s1, maybe$Mnstarts$Plends);
        }

        Object lambda75(Object obj, Object obj1, Object obj2)
        {
            frame34 frame34_1 = new frame34();
            frame34_1.staticLink = this;
            frame34_1.rest = obj;
            frame34_1.start1 = obj1;
            frame34_1.end1 = obj2;
            return call_with_values.callWithValues(frame34_1.Fn76, frame34_1.Fn77);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 70)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 71)
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

        public frame33()
        {
        }
    }

    public class frame34 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn76 = new ModuleMethod(this, 68, null, 0);
        final ModuleMethod lambda$Fn77 = new ModuleMethod(this, 69, null, 8194);
        Object rest;
        Object start1;
        frame33 staticLink;

        static Boolean lambda78(Object obj)
        {
            return Boolean.FALSE;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 68)
            {
                return lambda76();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 69)
            {
                return lambda77(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda76()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls$Gr, staticLink.s2, rest);
        }

        Object lambda77(Object obj, Object obj1)
        {
            int i = 1;
            Object obj2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(end1, start1), AddOp.$Mn.apply2(obj1, obj));
            Boolean boolean1;
            int j;
            int k;
            int l;
            try
            {
                boolean1 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj2);
            }
            if (obj2 != boolean1)
            {
                j = i;
            } else
            {
                j = 0;
            }
            k = 1 & j + 1;
            if (k != 0)
            {
                if (k != 0)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            if (staticLink.s1 == staticLink.s2)
            {
                l = i;
            } else
            {
                l = 0;
            }
            if (l != 0)
            {
                Object obj3 = Scheme.numEqu.apply2(start1, obj);
                int i1;
                Boolean boolean2;
                try
                {
                    boolean2 = Boolean.FALSE;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "x", -2, obj3);
                }
                if (obj3 == boolean2)
                {
                    i = 0;
                }
            } else
            {
                i = l;
            }
            i1 = 1 & i + 1;
            if (i1 != 0)
            {
                return srfi13.$PcStringCompare(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, misc.values, srfi13.lambda$Fn78, misc.values);
            }
            if (i1 != 0)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 68)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 69)
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

        public frame34()
        {
        }
    }

    public class frame35 extends ModuleBody
    {

        final ModuleMethod lambda$Fn79 = new ModuleMethod(this, 74, null, 0);
        final ModuleMethod lambda$Fn80 = new ModuleMethod(this, 75, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 74)
            {
                return lambda79();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 75)
            {
                return lambda80(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda79()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Ls, s1, maybe$Mnstarts$Plends);
        }

        Object lambda80(Object obj, Object obj1, Object obj2)
        {
            frame36 frame36_1 = new frame36();
            frame36_1.staticLink = this;
            frame36_1.rest = obj;
            frame36_1.start1 = obj1;
            frame36_1.end1 = obj2;
            return call_with_values.callWithValues(frame36_1.Fn81, frame36_1.Fn82);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 74)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 75)
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

        public frame35()
        {
        }
    }

    public class frame36 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn81 = new ModuleMethod(this, 72, null, 0);
        final ModuleMethod lambda$Fn82 = new ModuleMethod(this, 73, null, 8194);
        Object rest;
        Object start1;
        frame35 staticLink;

        static Boolean lambda83(Object obj)
        {
            return Boolean.FALSE;
        }

        static Boolean lambda84(Object obj)
        {
            return Boolean.FALSE;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 72)
            {
                return lambda81();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 73)
            {
                return lambda82(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda81()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls, staticLink.s2, rest);
        }

        Object lambda82(Object obj, Object obj1)
        {
            boolean flag;
            if (staticLink.s1 == staticLink.s2)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag ? Scheme.numEqu.apply2(start1, obj) != Boolean.FALSE : flag)
            {
                return Scheme.numLss.apply2(end1, obj1);
            } else
            {
                return srfi13.$PcStringCompare(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, misc.values, srfi13.lambda$Fn83, srfi13.lambda$Fn84);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 72)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 73)
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

        public frame36()
        {
        }
    }

    public class frame37 extends ModuleBody
    {

        final ModuleMethod lambda$Fn85 = new ModuleMethod(this, 78, null, 0);
        final ModuleMethod lambda$Fn86 = new ModuleMethod(this, 79, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 78)
            {
                return lambda85();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 79)
            {
                return lambda86(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda85()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Gr, s1, maybe$Mnstarts$Plends);
        }

        Object lambda86(Object obj, Object obj1, Object obj2)
        {
            frame38 frame38_1 = new frame38();
            frame38_1.staticLink = this;
            frame38_1.rest = obj;
            frame38_1.start1 = obj1;
            frame38_1.end1 = obj2;
            return call_with_values.callWithValues(frame38_1.Fn87, frame38_1.Fn88);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 78)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 79)
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

        public frame37()
        {
        }
    }

    public class frame38 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn87 = new ModuleMethod(this, 76, null, 0);
        final ModuleMethod lambda$Fn88 = new ModuleMethod(this, 77, null, 8194);
        Object rest;
        Object start1;
        frame37 staticLink;

        static Boolean lambda89(Object obj)
        {
            return Boolean.FALSE;
        }

        static Boolean lambda90(Object obj)
        {
            return Boolean.FALSE;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 76)
            {
                return lambda87();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 77)
            {
                return lambda88(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda87()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Gr, staticLink.s2, rest);
        }

        Object lambda88(Object obj, Object obj1)
        {
            boolean flag;
            if (staticLink.s1 == staticLink.s2)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag ? Scheme.numEqu.apply2(start1, obj) != Boolean.FALSE : flag)
            {
                return Scheme.numGrt.apply2(end1, obj1);
            } else
            {
                return srfi13.$PcStringCompare(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, srfi13.lambda$Fn89, srfi13.lambda$Fn90, misc.values);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 76)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 77)
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

        public frame38()
        {
        }
    }

    public class frame39 extends ModuleBody
    {

        final ModuleMethod lambda$Fn91 = new ModuleMethod(this, 82, null, 0);
        final ModuleMethod lambda$Fn92 = new ModuleMethod(this, 83, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 82)
            {
                return lambda91();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 83)
            {
                return lambda92(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda91()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Ls$Eq, s1, maybe$Mnstarts$Plends);
        }

        Object lambda92(Object obj, Object obj1, Object obj2)
        {
            frame40 frame40_1 = new frame40();
            frame40_1.staticLink = this;
            frame40_1.rest = obj;
            frame40_1.start1 = obj1;
            frame40_1.end1 = obj2;
            return call_with_values.callWithValues(frame40_1.Fn93, frame40_1.Fn94);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 82)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 83)
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

        public frame39()
        {
        }
    }

    public class frame4 extends ModuleBody
    {

        final ModuleMethod lambda$Fn11 = new ModuleMethod(this, 10, null, 0);
        final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 11, null, 8194);
        LList maybe$Mnstart$Plend;
        Object proc;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 10)
            {
                return lambda11();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 11)
            {
                return lambda12(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda11()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnmap$Ex, s, maybe$Mnstart$Plend);
        }

        Object lambda12(Object obj, Object obj1)
        {
            return srfi13.$PcStringMap$Ex(proc, s, obj, obj1);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 10)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 11)
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

        public frame4()
        {
        }
    }

    public class frame40 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn93 = new ModuleMethod(this, 80, null, 0);
        final ModuleMethod lambda$Fn94 = new ModuleMethod(this, 81, null, 8194);
        Object rest;
        Object start1;
        frame39 staticLink;

        static Boolean lambda95(Object obj)
        {
            return Boolean.FALSE;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 80)
            {
                return lambda93();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 81)
            {
                return lambda94(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda93()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Ls$Eq, staticLink.s2, rest);
        }

        Object lambda94(Object obj, Object obj1)
        {
            boolean flag;
            if (staticLink.s1 == staticLink.s2)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag ? Scheme.numEqu.apply2(start1, obj) != Boolean.FALSE : flag)
            {
                return Scheme.numLEq.apply2(end1, obj1);
            } else
            {
                return srfi13.$PcStringCompare(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, misc.values, misc.values, srfi13.lambda$Fn95);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 80)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 81)
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

        public frame40()
        {
        }
    }

    public class frame41 extends ModuleBody
    {

        final ModuleMethod lambda$Fn96 = new ModuleMethod(this, 86, null, 0);
        final ModuleMethod lambda$Fn97 = new ModuleMethod(this, 87, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 86)
            {
                return lambda96();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 87)
            {
                return lambda97(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda96()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Gr$Eq, s1, maybe$Mnstarts$Plends);
        }

        Object lambda97(Object obj, Object obj1, Object obj2)
        {
            frame42 frame42_1 = new frame42();
            frame42_1.staticLink = this;
            frame42_1.rest = obj;
            frame42_1.start1 = obj1;
            frame42_1.end1 = obj2;
            return call_with_values.callWithValues(frame42_1.Fn98, frame42_1.Fn99);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 86)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 87)
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

        public frame41()
        {
        }
    }

    public class frame42 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn98 = new ModuleMethod(this, 84, null, 0);
        final ModuleMethod lambda$Fn99 = new ModuleMethod(this, 85, null, 8194);
        Object rest;
        Object start1;
        frame41 staticLink;

        static Boolean lambda100(Object obj)
        {
            return Boolean.FALSE;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 84)
            {
                return lambda98();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 85)
            {
                return lambda99(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda98()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Gr$Eq, staticLink.s2, rest);
        }

        Object lambda99(Object obj, Object obj1)
        {
            boolean flag;
            if (staticLink.s1 == staticLink.s2)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag ? Scheme.numEqu.apply2(start1, obj) != Boolean.FALSE : flag)
            {
                return Scheme.numGEq.apply2(end1, obj1);
            } else
            {
                return srfi13.$PcStringCompare(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, srfi13.lambda$Fn100, misc.values, misc.values);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 84)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 85)
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

        public frame42()
        {
        }
    }

    public class frame43 extends ModuleBody
    {

        final ModuleMethod lambda$Fn101 = new ModuleMethod(this, 90, null, 0);
        final ModuleMethod lambda$Fn102 = new ModuleMethod(this, 91, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 90)
            {
                return lambda101();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 91)
            {
                return lambda102(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda101()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Eq, s1, maybe$Mnstarts$Plends);
        }

        Object lambda102(Object obj, Object obj1, Object obj2)
        {
            frame44 frame44_1 = new frame44();
            frame44_1.staticLink = this;
            frame44_1.rest = obj;
            frame44_1.start1 = obj1;
            frame44_1.end1 = obj2;
            return call_with_values.callWithValues(frame44_1.Fn103, frame44_1.Fn104);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 90)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 91)
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

        public frame43()
        {
        }
    }

    public class frame44 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn103 = new ModuleMethod(this, 88, null, 0);
        final ModuleMethod lambda$Fn104 = new ModuleMethod(this, 89, null, 8194);
        Object rest;
        Object start1;
        frame43 staticLink;

        static Boolean lambda105(Object obj)
        {
            return Boolean.FALSE;
        }

        static Boolean lambda106(Object obj)
        {
            return Boolean.FALSE;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 88)
            {
                return lambda103();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 89)
            {
                return lambda104(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda103()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Eq, staticLink.s2, rest);
        }

        Object lambda104(Object obj, Object obj1)
        {
            Object obj2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(end1, start1), AddOp.$Mn.apply2(obj1, obj));
            boolean flag;
            try
            {
                flag = ((Boolean)obj2).booleanValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj2);
            }
            if (flag)
            {
                boolean flag1;
                if (staticLink.s1 == staticLink.s2)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    Object obj3 = Scheme.numEqu.apply2(start1, obj);
                    boolean flag2;
                    try
                    {
                        flag2 = ((Boolean)obj3).booleanValue();
                    }
                    catch (ClassCastException classcastexception1)
                    {
                        throw new WrongType(classcastexception1, "x", -2, obj3);
                    }
                    flag1 = flag2;
                }
                if (flag1)
                {
                    if (flag1)
                    {
                        return Boolean.TRUE;
                    } else
                    {
                        return Boolean.FALSE;
                    }
                } else
                {
                    return srfi13.$PcStringCompareCi(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, srfi13.lambda$Fn105, misc.values, srfi13.lambda$Fn106);
                }
            }
            if (flag)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 88)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 89)
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

        public frame44()
        {
        }
    }

    public class frame45 extends ModuleBody
    {

        final ModuleMethod lambda$Fn107 = new ModuleMethod(this, 94, null, 0);
        final ModuleMethod lambda$Fn108 = new ModuleMethod(this, 95, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 94)
            {
                return lambda107();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 95)
            {
                return lambda108(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda107()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Ls$Gr, s1, maybe$Mnstarts$Plends);
        }

        Object lambda108(Object obj, Object obj1, Object obj2)
        {
            frame46 frame46_1 = new frame46();
            frame46_1.staticLink = this;
            frame46_1.rest = obj;
            frame46_1.start1 = obj1;
            frame46_1.end1 = obj2;
            return call_with_values.callWithValues(frame46_1.Fn109, frame46_1.Fn110);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 94)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 95)
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

        public frame45()
        {
        }
    }

    public class frame46 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn109 = new ModuleMethod(this, 92, null, 0);
        final ModuleMethod lambda$Fn110 = new ModuleMethod(this, 93, null, 8194);
        Object rest;
        Object start1;
        frame45 staticLink;

        static Boolean lambda111(Object obj)
        {
            return Boolean.FALSE;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 92)
            {
                return lambda109();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 93)
            {
                return lambda110(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda109()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls$Gr, staticLink.s2, rest);
        }

        Object lambda110(Object obj, Object obj1)
        {
            int i = 1;
            Object obj2 = Scheme.numEqu.apply2(AddOp.$Mn.apply2(end1, start1), AddOp.$Mn.apply2(obj1, obj));
            Boolean boolean1;
            int j;
            int k;
            int l;
            try
            {
                boolean1 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj2);
            }
            if (obj2 != boolean1)
            {
                j = i;
            } else
            {
                j = 0;
            }
            k = 1 & j + 1;
            if (k != 0)
            {
                if (k != 0)
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            }
            if (staticLink.s1 == staticLink.s2)
            {
                l = i;
            } else
            {
                l = 0;
            }
            if (l != 0)
            {
                Object obj3 = Scheme.numEqu.apply2(start1, obj);
                int i1;
                Boolean boolean2;
                try
                {
                    boolean2 = Boolean.FALSE;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "x", -2, obj3);
                }
                if (obj3 == boolean2)
                {
                    i = 0;
                }
            } else
            {
                i = l;
            }
            i1 = 1 & i + 1;
            if (i1 != 0)
            {
                return srfi13.$PcStringCompareCi(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, misc.values, srfi13.lambda$Fn111, misc.values);
            }
            if (i1 != 0)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 92)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 93)
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

        public frame46()
        {
        }
    }

    public class frame47 extends ModuleBody
    {

        final ModuleMethod lambda$Fn112 = new ModuleMethod(this, 98, null, 0);
        final ModuleMethod lambda$Fn113 = new ModuleMethod(this, 99, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 98)
            {
                return lambda112();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 99)
            {
                return lambda113(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda112()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Ls, s1, maybe$Mnstarts$Plends);
        }

        Object lambda113(Object obj, Object obj1, Object obj2)
        {
            frame48 frame48_1 = new frame48();
            frame48_1.staticLink = this;
            frame48_1.rest = obj;
            frame48_1.start1 = obj1;
            frame48_1.end1 = obj2;
            return call_with_values.callWithValues(frame48_1.Fn114, frame48_1.Fn115);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 98)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 99)
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

        public frame47()
        {
        }
    }

    public class frame48 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn114 = new ModuleMethod(this, 96, null, 0);
        final ModuleMethod lambda$Fn115 = new ModuleMethod(this, 97, null, 8194);
        Object rest;
        Object start1;
        frame47 staticLink;

        static Boolean lambda116(Object obj)
        {
            return Boolean.FALSE;
        }

        static Boolean lambda117(Object obj)
        {
            return Boolean.FALSE;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 96)
            {
                return lambda114();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 97)
            {
                return lambda115(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda114()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls, staticLink.s2, rest);
        }

        Object lambda115(Object obj, Object obj1)
        {
            boolean flag;
            if (staticLink.s1 == staticLink.s2)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag ? Scheme.numEqu.apply2(start1, obj) != Boolean.FALSE : flag)
            {
                return Scheme.numLss.apply2(end1, obj1);
            } else
            {
                return srfi13.$PcStringCompareCi(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, misc.values, srfi13.lambda$Fn116, srfi13.lambda$Fn117);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 96)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 97)
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

        public frame48()
        {
        }
    }

    public class frame49 extends ModuleBody
    {

        final ModuleMethod lambda$Fn118 = new ModuleMethod(this, 102, null, 0);
        final ModuleMethod lambda$Fn119 = new ModuleMethod(this, 103, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 102)
            {
                return lambda118();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 103)
            {
                return lambda119(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda118()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Gr, s1, maybe$Mnstarts$Plends);
        }

        Object lambda119(Object obj, Object obj1, Object obj2)
        {
            frame50 frame50_1 = new frame50();
            frame50_1.staticLink = this;
            frame50_1.rest = obj;
            frame50_1.start1 = obj1;
            frame50_1.end1 = obj2;
            return call_with_values.callWithValues(frame50_1.Fn120, frame50_1.Fn121);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 102)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 103)
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

        public frame49()
        {
        }
    }

    public class frame5 extends ModuleBody
    {

        Object knil;
        Object kons;
        final ModuleMethod lambda$Fn13 = new ModuleMethod(this, 12, null, 0);
        final ModuleMethod lambda$Fn14 = new ModuleMethod(this, 13, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 12)
            {
                return lambda13();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 13)
            {
                return lambda14(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda13()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfold, s, maybe$Mnstart$Plend);
        }

        Object lambda14(Object obj, Object obj1)
        {
            Object obj2 = knil;
            Object obj3 = obj;
            while (Scheme.numLss.apply2(obj3, obj1) != Boolean.FALSE) 
            {
                gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
                Object obj4 = kons;
                Object obj5 = s;
                CharSequence charsequence;
                int i;
                try
                {
                    charsequence = (CharSequence)obj5;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "string-ref", 1, obj5);
                }
                try
                {
                    i = ((Number)obj3).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-ref", 2, obj3);
                }
                obj2 = applytoargs.apply3(obj4, Char.make(strings.stringRef(charsequence, i)), obj2);
                obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
            }
            return obj2;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 12)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 13)
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

        public frame5()
        {
        }
    }

    public class frame50 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn120 = new ModuleMethod(this, 100, null, 0);
        final ModuleMethod lambda$Fn121 = new ModuleMethod(this, 101, null, 8194);
        Object rest;
        Object start1;
        frame49 staticLink;

        static Boolean lambda122(Object obj)
        {
            return Boolean.FALSE;
        }

        static Boolean lambda123(Object obj)
        {
            return Boolean.FALSE;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 100)
            {
                return lambda120();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 101)
            {
                return lambda121(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda120()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Gr, staticLink.s2, rest);
        }

        Object lambda121(Object obj, Object obj1)
        {
            boolean flag;
            if (staticLink.s1 == staticLink.s2)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag ? Scheme.numEqu.apply2(start1, obj) != Boolean.FALSE : flag)
            {
                return Scheme.numGrt.apply2(end1, obj1);
            } else
            {
                return srfi13.$PcStringCompareCi(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, srfi13.lambda$Fn122, srfi13.lambda$Fn123, misc.values);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 100)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 101)
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

        public frame50()
        {
        }
    }

    public class frame51 extends ModuleBody
    {

        final ModuleMethod lambda$Fn124 = new ModuleMethod(this, 106, null, 0);
        final ModuleMethod lambda$Fn125 = new ModuleMethod(this, 107, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 106)
            {
                return lambda124();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 107)
            {
                return lambda125(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda124()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Ls$Eq, s1, maybe$Mnstarts$Plends);
        }

        Object lambda125(Object obj, Object obj1, Object obj2)
        {
            frame52 frame52_1 = new frame52();
            frame52_1.staticLink = this;
            frame52_1.rest = obj;
            frame52_1.start1 = obj1;
            frame52_1.end1 = obj2;
            return call_with_values.callWithValues(frame52_1.Fn126, frame52_1.Fn127);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 106)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 107)
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

        public frame51()
        {
        }
    }

    public class frame52 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn126 = new ModuleMethod(this, 104, null, 0);
        final ModuleMethod lambda$Fn127 = new ModuleMethod(this, 105, null, 8194);
        Object rest;
        Object start1;
        frame51 staticLink;

        static Boolean lambda128(Object obj)
        {
            return Boolean.FALSE;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 104)
            {
                return lambda126();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 105)
            {
                return lambda127(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda126()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Ls$Eq, staticLink.s2, rest);
        }

        Object lambda127(Object obj, Object obj1)
        {
            boolean flag;
            if (staticLink.s1 == staticLink.s2)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag ? Scheme.numEqu.apply2(start1, obj) != Boolean.FALSE : flag)
            {
                return Scheme.numLEq.apply2(end1, obj1);
            } else
            {
                return srfi13.$PcStringCompareCi(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, misc.values, misc.values, srfi13.lambda$Fn128);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 104)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 105)
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

        public frame52()
        {
        }
    }

    public class frame53 extends ModuleBody
    {

        final ModuleMethod lambda$Fn129 = new ModuleMethod(this, 110, null, 0);
        final ModuleMethod lambda$Fn130 = new ModuleMethod(this, 111, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object s1;
        Object s2;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 110)
            {
                return lambda129();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 111)
            {
                return lambda130(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda129()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnci$Gr$Eq, s1, maybe$Mnstarts$Plends);
        }

        Object lambda130(Object obj, Object obj1, Object obj2)
        {
            frame54 frame54_1 = new frame54();
            frame54_1.staticLink = this;
            frame54_1.rest = obj;
            frame54_1.start1 = obj1;
            frame54_1.end1 = obj2;
            return call_with_values.callWithValues(frame54_1.Fn131, frame54_1.Fn132);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 110)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 111)
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

        public frame53()
        {
        }
    }

    public class frame54 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn131 = new ModuleMethod(this, 108, null, 0);
        final ModuleMethod lambda$Fn132 = new ModuleMethod(this, 109, null, 8194);
        Object rest;
        Object start1;
        frame53 staticLink;

        static Boolean lambda133(Object obj)
        {
            return Boolean.FALSE;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 108)
            {
                return lambda131();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 109)
            {
                return lambda132(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda131()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnci$Gr$Eq, staticLink.s2, rest);
        }

        Object lambda132(Object obj, Object obj1)
        {
            boolean flag;
            if (staticLink.s1 == staticLink.s2)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag ? Scheme.numEqu.apply2(start1, obj) != Boolean.FALSE : flag)
            {
                return Scheme.numGEq.apply2(end1, obj1);
            } else
            {
                return srfi13.$PcStringCompareCi(staticLink.s1, start1, end1, staticLink.s2, obj, obj1, srfi13.lambda$Fn133, misc.values, misc.values);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 108)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 109)
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

        public frame54()
        {
        }
    }

    public class frame55 extends ModuleBody
    {

        Object char$Mn$Grint;

        public frame55()
        {
        }
    }

    public class frame56 extends ModuleBody
    {

        Object bound;
        final ModuleMethod lambda$Fn134 = new ModuleMethod(this, 112, null, 0);
        final ModuleMethod lambda$Fn135 = new ModuleMethod(this, 113, null, 8194);
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 112)
            {
                return lambda134();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 113)
            {
                return lambda135(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda134()
        {
            ModuleMethod modulemethod = srfi13.string$Mnhash;
            Object obj = s;
            Location location = srfi13.loc$rest;
            Object obj1;
            try
            {
                obj1 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 912, 55);
                throw unboundlocationexception;
            }
            return srfi13.stringParseFinalStart$PlEnd(modulemethod, obj, obj1);
        }

        Object lambda135(Object obj, Object obj1)
        {
            return srfi13.$PcStringHash(s, characters.char$Mn$Grinteger, bound, obj, obj1);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 112)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 113)
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

        public frame56()
        {
        }
    }

    public class frame57 extends ModuleBody
    {

        Object bound;
        final ModuleMethod lambda$Fn136 = new ModuleMethod(this, 114, null, 0);
        final ModuleMethod lambda$Fn137 = new ModuleMethod(this, 115, null, 8194);
        Object s;

        static int lambda138(Object obj)
        {
            Char char1;
            try
            {
                char1 = (Char)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "char-downcase", 1, obj);
            }
            return characters.char$To$Integer(unicode.charDowncase(char1));
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 114)
            {
                return lambda136();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 115)
            {
                return lambda137(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda136()
        {
            ModuleMethod modulemethod = srfi13.string$Mnhash$Mnci;
            Object obj = s;
            Location location = srfi13.loc$rest;
            Object obj1;
            try
            {
                obj1 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 921, 58);
                throw unboundlocationexception;
            }
            return srfi13.stringParseFinalStart$PlEnd(modulemethod, obj, obj1);
        }

        Object lambda137(Object obj, Object obj1)
        {
            return srfi13.$PcStringHash(s, srfi13.lambda$Fn138, bound, obj, obj1);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 114)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 115)
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

        public frame57()
        {
        }
    }

    public class frame58 extends ModuleBody
    {

        final ModuleMethod lambda$Fn139 = new ModuleMethod(this, 116, null, 0);
        final ModuleMethod lambda$Fn140 = new ModuleMethod(this, 117, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 116)
            {
                return lambda139();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 117)
            {
                return lambda140(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda139()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnupcase, s, maybe$Mnstart$Plend);
        }

        Object lambda140(Object obj, Object obj1)
        {
            return srfi13.$PcStringMap(unicode.char$Mnupcase, s, obj, obj1);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 116)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 117)
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

        public frame58()
        {
        }
    }

    public class frame59 extends ModuleBody
    {

        final ModuleMethod lambda$Fn141 = new ModuleMethod(this, 118, null, 0);
        final ModuleMethod lambda$Fn142 = new ModuleMethod(this, 119, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 118)
            {
                return lambda141();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 119)
            {
                return lambda142(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda141()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnupcase$Ex, s, maybe$Mnstart$Plend);
        }

        Object lambda142(Object obj, Object obj1)
        {
            return srfi13.$PcStringMap$Ex(unicode.char$Mnupcase, s, obj, obj1);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 118)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 119)
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

        public frame59()
        {
        }
    }

    public class frame6 extends ModuleBody
    {

        Object knil;
        Object kons;
        final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 14, null, 0);
        final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 15, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 14)
            {
                return lambda15();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 15)
            {
                return lambda16(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda15()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfold$Mnright, s, maybe$Mnstart$Plend);
        }

        Object lambda16(Object obj, Object obj1)
        {
            Object obj2 = knil;
            Object obj3 = AddOp.$Mn.apply2(obj1, srfi13.Lit1);
            while (Scheme.numGEq.apply2(obj3, obj) != Boolean.FALSE) 
            {
                gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
                Object obj4 = kons;
                Object obj5 = s;
                CharSequence charsequence;
                int i;
                try
                {
                    charsequence = (CharSequence)obj5;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "string-ref", 1, obj5);
                }
                try
                {
                    i = ((Number)obj3).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-ref", 2, obj3);
                }
                obj2 = applytoargs.apply3(obj4, Char.make(strings.stringRef(charsequence, i)), obj2);
                obj3 = AddOp.$Mn.apply2(obj3, srfi13.Lit1);
            }
            return obj2;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 14)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 15)
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

        public frame6()
        {
        }
    }

    public class frame60 extends ModuleBody
    {

        final ModuleMethod lambda$Fn143 = new ModuleMethod(this, 120, null, 0);
        final ModuleMethod lambda$Fn144 = new ModuleMethod(this, 121, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 120)
            {
                return lambda143();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 121)
            {
                return lambda144(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda143()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mndowncase, s, maybe$Mnstart$Plend);
        }

        Object lambda144(Object obj, Object obj1)
        {
            return srfi13.$PcStringMap(unicode.char$Mndowncase, s, obj, obj1);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 120)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 121)
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

        public frame60()
        {
        }
    }

    public class frame61 extends ModuleBody
    {

        final ModuleMethod lambda$Fn145 = new ModuleMethod(this, 122, null, 0);
        final ModuleMethod lambda$Fn146 = new ModuleMethod(this, 123, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 122)
            {
                return lambda145();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 123)
            {
                return lambda146(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda145()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mndowncase$Ex, s, maybe$Mnstart$Plend);
        }

        Object lambda146(Object obj, Object obj1)
        {
            return srfi13.$PcStringMap$Ex(unicode.char$Mndowncase, s, obj, obj1);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 122)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 123)
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

        public frame61()
        {
        }
    }

    public class frame62 extends ModuleBody
    {

        final ModuleMethod lambda$Fn147 = new ModuleMethod(this, 124, null, 0);
        final ModuleMethod lambda$Fn148 = new ModuleMethod(this, 125, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 124)
            {
                return lambda147();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 125)
            {
                return lambda148(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda147()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntitlecase$Ex, s, maybe$Mnstart$Plend);
        }

        Object lambda148(Object obj, Object obj1)
        {
            return srfi13.$PcStringTitlecase$Ex(s, obj, obj1);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 124)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 125)
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

        public frame62()
        {
        }
    }

    public class frame63 extends ModuleBody
    {

        final ModuleMethod lambda$Fn149 = new ModuleMethod(this, 126, null, 0);
        final ModuleMethod lambda$Fn150 = new ModuleMethod(this, 127, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 126)
            {
                return lambda149();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 127)
            {
                return lambda150(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda149()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mntitlecase$Ex, s, maybe$Mnstart$Plend);
        }

        CharSequence lambda150(Object obj, Object obj1)
        {
            Object obj2 = s;
            CharSequence charsequence;
            int i;
            int j;
            CharSequence charsequence1;
            try
            {
                charsequence = (CharSequence)obj2;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "substring", 1, obj2);
            }
            try
            {
                i = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "substring", 2, obj);
            }
            try
            {
                j = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "substring", 3, obj1);
            }
            charsequence1 = strings.substring(charsequence, i, j);
            srfi13.$PcStringTitlecase$Ex(charsequence1, srfi13.Lit0, AddOp.$Mn.apply2(obj1, obj));
            return charsequence1;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 126)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 127)
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

        public frame63()
        {
        }
    }

    public class frame64 extends ModuleBody
    {

        final ModuleMethod lambda$Fn151;
        Object n;
        Object s;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 128)
            {
                if (lambda151(obj))
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        boolean lambda151(Object obj)
        {
            boolean flag = numbers.isInteger(n);
            if (flag)
            {
                flag = numbers.isExact(n);
                if (flag)
                {
                    gnu.kawa.functions.NumberCompare numbercompare = Scheme.numLEq;
                    IntNum intnum = srfi13.Lit0;
                    Object obj1 = n;
                    Object obj2 = s;
                    CharSequence charsequence;
                    try
                    {
                        charsequence = (CharSequence)obj2;
                    }
                    catch (ClassCastException classcastexception)
                    {
                        throw new WrongType(classcastexception, "string-length", 1, obj2);
                    }
                    flag = ((Boolean)numbercompare.apply3(intnum, obj1, Integer.valueOf(strings.stringLength(charsequence)))).booleanValue();
                }
            }
            return flag;
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 128)
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

        public frame64()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 128, null, 4097);
            modulemethod.setProperty("source-location", "srfi13.scm:996");
            lambda$Fn151 = modulemethod;
        }
    }

    public class frame65 extends ModuleBody
    {

        final ModuleMethod lambda$Fn152;
        int len;
        Object n;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 129)
            {
                if (lambda152(obj))
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        boolean lambda152(Object obj)
        {
            boolean flag = numbers.isInteger(n);
            if (flag)
            {
                flag = numbers.isExact(n);
                if (flag)
                {
                    flag = ((Boolean)Scheme.numLEq.apply3(srfi13.Lit0, n, Integer.valueOf(len))).booleanValue();
                }
            }
            return flag;
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 129)
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

        public frame65()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 129, null, 4097);
            modulemethod.setProperty("source-location", "srfi13.scm:1004");
            lambda$Fn152 = modulemethod;
        }
    }

    public class frame66 extends ModuleBody
    {

        final ModuleMethod lambda$Fn153;
        int len;
        Object n;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 130)
            {
                if (lambda153(obj))
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        boolean lambda153(Object obj)
        {
            boolean flag = numbers.isInteger(n);
            if (flag)
            {
                flag = numbers.isExact(n);
                if (flag)
                {
                    flag = ((Boolean)Scheme.numLEq.apply3(srfi13.Lit0, n, Integer.valueOf(len))).booleanValue();
                }
            }
            return flag;
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 130)
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

        public frame66()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 130, null, 4097);
            modulemethod.setProperty("source-location", "srfi13.scm:1010");
            lambda$Fn153 = modulemethod;
        }
    }

    public class frame67 extends ModuleBody
    {

        final ModuleMethod lambda$Fn154;
        int len;
        Object n;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 131)
            {
                if (lambda154(obj))
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        boolean lambda154(Object obj)
        {
            boolean flag = numbers.isInteger(n);
            if (flag)
            {
                flag = numbers.isExact(n);
                if (flag)
                {
                    flag = ((Boolean)Scheme.numLEq.apply3(srfi13.Lit0, n, Integer.valueOf(len))).booleanValue();
                }
            }
            return flag;
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 131)
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

        public frame67()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 131, null, 4097);
            modulemethod.setProperty("source-location", "srfi13.scm:1016");
            lambda$Fn154 = modulemethod;
        }
    }

    public class frame68 extends ModuleBody
    {

        final ModuleMethod lambda$Fn155 = new ModuleMethod(this, 132, null, 0);
        final ModuleMethod lambda$Fn156 = new ModuleMethod(this, 133, null, 8194);
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 132)
            {
                return lambda155();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 133)
            {
                return lambda156(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda155()
        {
            ModuleMethod modulemethod = srfi13.string$Mntrim;
            Object obj = s;
            Location location = srfi13.loc$rest;
            Object obj1;
            try
            {
                obj1 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1023, 53);
                throw unboundlocationexception;
            }
            return srfi13.stringParseFinalStart$PlEnd(modulemethod, obj, obj1);
        }

        Object lambda156(Object obj, Object obj1)
        {
            Object obj2 = s;
            Location location = srfi13.loc$criterion;
            Object obj3;
            Object obj4;
            try
            {
                obj3 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1024, 29);
                throw unboundlocationexception;
            }
            obj4 = srfi13.stringSkip$V(obj2, obj3, new Object[] {
                obj, obj1
            });
            if (obj4 != Boolean.FALSE)
            {
                Object obj5 = s;
                CharSequence charsequence;
                int i;
                int j;
                try
                {
                    charsequence = (CharSequence)obj5;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "%substring/shared", 0, obj5);
                }
                try
                {
                    i = ((Number)obj4).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "%substring/shared", 1, obj4);
                }
                try
                {
                    j = ((Number)obj1).intValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "%substring/shared", 2, obj1);
                }
                return srfi13.$PcSubstring$SlShared(charsequence, i, j);
            } else
            {
                return "";
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 132)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 133)
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

        public frame68()
        {
        }
    }

    public class frame69 extends ModuleBody
    {

        final ModuleMethod lambda$Fn157 = new ModuleMethod(this, 134, null, 0);
        final ModuleMethod lambda$Fn158 = new ModuleMethod(this, 135, null, 8194);
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 134)
            {
                return lambda157();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 135)
            {
                return lambda158(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda157()
        {
            ModuleMethod modulemethod = srfi13.string$Mntrim$Mnright;
            Object obj = s;
            Location location = srfi13.loc$rest;
            Object obj1;
            try
            {
                obj1 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1030, 59);
                throw unboundlocationexception;
            }
            return srfi13.stringParseFinalStart$PlEnd(modulemethod, obj, obj1);
        }

        Object lambda158(Object obj, Object obj1)
        {
            Object obj2 = s;
            Location location = srfi13.loc$criterion;
            Object obj3;
            Object obj4;
            try
            {
                obj3 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1031, 35);
                throw unboundlocationexception;
            }
            obj4 = srfi13.stringSkipRight$V(obj2, obj3, new Object[] {
                obj, obj1
            });
            if (obj4 != Boolean.FALSE)
            {
                Object obj5 = s;
                CharSequence charsequence;
                Object obj6;
                int i;
                try
                {
                    charsequence = (CharSequence)obj5;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "%substring/shared", 0, obj5);
                }
                obj6 = AddOp.$Pl.apply2(srfi13.Lit1, obj4);
                try
                {
                    i = ((Number)obj6).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "%substring/shared", 2, obj6);
                }
                return srfi13.$PcSubstring$SlShared(charsequence, 0, i);
            } else
            {
                return "";
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 134)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 135)
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

        public frame69()
        {
        }
    }

    public class frame7 extends ModuleBody
    {

        final ModuleMethod lambda$Fn19 = new ModuleMethod(this, 16, null, 0);
        final ModuleMethod lambda$Fn20 = new ModuleMethod(this, 17, null, 8194);
        LList maybe$Mnstart$Plend;
        Object proc;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 16)
            {
                return lambda19();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 17)
            {
                return lambda20(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda19()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfor$Mneach, s, maybe$Mnstart$Plend);
        }

        Object lambda20(Object obj, Object obj1)
        {
            Object obj2 = obj;
            while (Scheme.numLss.apply2(obj2, obj1) != Boolean.FALSE) 
            {
                gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
                Object obj3 = proc;
                Object obj4 = s;
                CharSequence charsequence;
                int i;
                try
                {
                    charsequence = (CharSequence)obj4;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "string-ref", 1, obj4);
                }
                try
                {
                    i = ((Number)obj2).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-ref", 2, obj2);
                }
                applytoargs.apply2(obj3, Char.make(strings.stringRef(charsequence, i)));
                obj2 = AddOp.$Pl.apply2(obj2, srfi13.Lit1);
            }
            return Values.empty;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 16)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 17)
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

        public frame7()
        {
        }
    }

    public class frame70 extends ModuleBody
    {

        final ModuleMethod lambda$Fn159 = new ModuleMethod(this, 136, null, 0);
        final ModuleMethod lambda$Fn160 = new ModuleMethod(this, 137, null, 8194);
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 136)
            {
                return lambda159();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 137)
            {
                return lambda160(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda159()
        {
            ModuleMethod modulemethod = srfi13.string$Mntrim$Mnboth;
            Object obj = s;
            Location location = srfi13.loc$rest;
            Object obj1;
            try
            {
                obj1 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1037, 58);
                throw unboundlocationexception;
            }
            return srfi13.stringParseFinalStart$PlEnd(modulemethod, obj, obj1);
        }

        Object lambda160(Object obj, Object obj1)
        {
            Object obj2 = s;
            Location location = srfi13.loc$criterion;
            Object obj3;
            Object obj4;
            try
            {
                obj3 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1038, 29);
                throw unboundlocationexception;
            }
            obj4 = srfi13.stringSkip$V(obj2, obj3, new Object[] {
                obj, obj1
            });
            if (obj4 != Boolean.FALSE)
            {
                Object obj5 = s;
                CharSequence charsequence;
                int i;
                AddOp addop;
                IntNum intnum;
                Object obj6;
                Location location1;
                Object obj7;
                Object obj8;
                int j;
                try
                {
                    charsequence = (CharSequence)obj5;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "%substring/shared", 0, obj5);
                }
                try
                {
                    i = ((Number)obj4).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "%substring/shared", 1, obj4);
                }
                addop = AddOp.$Pl;
                intnum = srfi13.Lit1;
                obj6 = s;
                location1 = srfi13.loc$criterion;
                try
                {
                    obj7 = location1.get();
                }
                catch (UnboundLocationException unboundlocationexception1)
                {
                    unboundlocationexception1.setLine("srfi13.scm", 1040, 58);
                    throw unboundlocationexception1;
                }
                obj8 = addop.apply2(intnum, srfi13.stringSkipRight$V(obj6, obj7, new Object[] {
                    obj4, obj1
                }));
                try
                {
                    j = ((Number)obj8).intValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "%substring/shared", 2, obj8);
                }
                return srfi13.$PcSubstring$SlShared(charsequence, i, j);
            } else
            {
                return "";
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 136)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 137)
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

        public frame70()
        {
        }
    }

    public class frame71 extends ModuleBody
    {

        final ModuleMethod lambda$Fn161 = new ModuleMethod(this, 138, null, 0);
        final ModuleMethod lambda$Fn162 = new ModuleMethod(this, 139, null, 8194);
        Object n;
        Object s;

        static boolean lambda163(Object obj)
        {
            boolean flag = numbers.isInteger(obj);
            if (flag)
            {
                flag = numbers.isExact(obj);
                if (flag)
                {
                    flag = ((Boolean)Scheme.numLEq.apply2(srfi13.Lit0, obj)).booleanValue();
                }
            }
            return flag;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 138)
            {
                return lambda161();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 139)
            {
                return lambda162(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda161()
        {
            ModuleMethod modulemethod = srfi13.string$Mnpad$Mnright;
            Object obj = s;
            Location location = srfi13.loc$rest;
            Object obj1;
            try
            {
                obj1 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1046, 58);
                throw unboundlocationexception;
            }
            return srfi13.stringParseFinalStart$PlEnd(modulemethod, obj, obj1);
        }

        Object lambda162(Object obj, Object obj1)
        {
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Location location = srfi13.loc$check$Mnarg;
            Object obj2;
            Object obj3;
            Object obj4;
            Object obj5;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1047, 7);
                throw unboundlocationexception;
            }
            applytoargs.apply4(obj2, srfi13.lambda$Fn163, n, srfi13.string$Mnpad$Mnright);
            obj3 = AddOp.$Mn.apply2(obj1, obj);
            if (Scheme.numLEq.apply2(n, obj3) != Boolean.FALSE)
            {
                Object obj6 = s;
                int i;
                CharSequence charsequence;
                CharSequence charsequence1;
                int j;
                int k;
                CharSequence charsequence2;
                int l;
                Object obj7;
                int i1;
                try
                {
                    charsequence2 = (CharSequence)obj6;
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "%substring/shared", 0, obj6);
                }
                try
                {
                    l = ((Number)obj).intValue();
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "%substring/shared", 1, obj);
                }
                obj7 = AddOp.$Pl.apply2(obj, n);
                try
                {
                    i1 = ((Number)obj7).intValue();
                }
                catch (ClassCastException classcastexception6)
                {
                    throw new WrongType(classcastexception6, "%substring/shared", 2, obj7);
                }
                return srfi13.$PcSubstring$SlShared(charsequence2, l, i1);
            }
            obj4 = n;
            try
            {
                i = ((Number)obj4).intValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "make-string", 1, obj4);
            }
            charsequence = strings.makeString(i, LangPrimType.charType);
            obj5 = s;
            try
            {
                charsequence1 = (CharSequence)obj5;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "%string-copy!", 2, obj5);
            }
            try
            {
                j = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "%string-copy!", 3, obj);
            }
            try
            {
                k = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "%string-copy!", 4, obj1);
            }
            srfi13.$PcStringCopy$Ex(charsequence, 0, charsequence1, j, k);
            return charsequence;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 138)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 139)
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

        public frame71()
        {
        }
    }

    public class frame72 extends ModuleBody
    {

        final ModuleMethod lambda$Fn164 = new ModuleMethod(this, 140, null, 0);
        final ModuleMethod lambda$Fn165 = new ModuleMethod(this, 141, null, 8194);
        Object n;
        Object s;

        static boolean lambda166(Object obj)
        {
            boolean flag = numbers.isInteger(obj);
            if (flag)
            {
                flag = numbers.isExact(obj);
                if (flag)
                {
                    flag = ((Boolean)Scheme.numLEq.apply2(srfi13.Lit0, obj)).booleanValue();
                }
            }
            return flag;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 140)
            {
                return lambda164();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 141)
            {
                return lambda165(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda164()
        {
            ModuleMethod modulemethod = srfi13.string$Mnpad;
            Object obj = s;
            Location location = srfi13.loc$rest;
            Object obj1;
            try
            {
                obj1 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1058, 52);
                throw unboundlocationexception;
            }
            return srfi13.stringParseFinalStart$PlEnd(modulemethod, obj, obj1);
        }

        Object lambda165(Object obj, Object obj1)
        {
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Location location = srfi13.loc$check$Mnarg;
            Object obj2;
            Object obj3;
            Object obj4;
            Object obj5;
            Object obj6;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1059, 7);
                throw unboundlocationexception;
            }
            applytoargs.apply4(obj2, srfi13.lambda$Fn166, n, srfi13.string$Mnpad);
            obj3 = AddOp.$Mn.apply2(obj1, obj);
            if (Scheme.numLEq.apply2(n, obj3) != Boolean.FALSE)
            {
                Object obj7 = s;
                int i;
                CharSequence charsequence;
                int j;
                CharSequence charsequence1;
                int k;
                int l;
                CharSequence charsequence2;
                Object obj8;
                int i1;
                int j1;
                try
                {
                    charsequence2 = (CharSequence)obj7;
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "%substring/shared", 0, obj7);
                }
                obj8 = AddOp.$Mn.apply2(obj1, n);
                try
                {
                    i1 = ((Number)obj8).intValue();
                }
                catch (ClassCastException classcastexception6)
                {
                    throw new WrongType(classcastexception6, "%substring/shared", 1, obj8);
                }
                try
                {
                    j1 = ((Number)obj1).intValue();
                }
                catch (ClassCastException classcastexception7)
                {
                    throw new WrongType(classcastexception7, "%substring/shared", 2, obj1);
                }
                return srfi13.$PcSubstring$SlShared(charsequence2, i1, j1);
            }
            obj4 = n;
            try
            {
                i = ((Number)obj4).intValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "make-string", 1, obj4);
            }
            charsequence = strings.makeString(i, LangPrimType.charType);
            obj5 = AddOp.$Mn.apply2(n, obj3);
            try
            {
                j = ((Number)obj5).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "%string-copy!", 1, obj5);
            }
            obj6 = s;
            try
            {
                charsequence1 = (CharSequence)obj6;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "%string-copy!", 2, obj6);
            }
            try
            {
                k = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "%string-copy!", 3, obj);
            }
            try
            {
                l = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "%string-copy!", 4, obj1);
            }
            srfi13.$PcStringCopy$Ex(charsequence, j, charsequence1, k, l);
            return charsequence;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 140)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 141)
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

        public frame72()
        {
        }
    }

    public class frame73 extends ModuleBody
    {

        Object criterion;
        final ModuleMethod lambda$Fn167 = new ModuleMethod(this, 145, null, 0);
        final ModuleMethod lambda$Fn168 = new ModuleMethod(this, 146, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 145)
            {
                return lambda167();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 146)
            {
                return lambda168(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda167()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mndelete, s, maybe$Mnstart$Plend);
        }

        CharSequence lambda168(Object obj, Object obj1)
        {
            frame74 frame74_1 = new frame74();
            frame74_1.staticLink = this;
            Object obj4;
            if (misc.isProcedure(criterion))
            {
                Object obj6 = AddOp.$Mn.apply2(obj1, obj);
                gnu.kawa.functions.ApplyToArgs applytoargs;
                Location location;
                Object obj2;
                Object aobj[];
                Object obj3;
                int i;
                gnu.kawa.functions.ApplyToArgs applytoargs1;
                Location location1;
                Object obj5;
                int j;
                Object obj7;
                CharSequence charsequence;
                int k;
                try
                {
                    j = ((Number)obj6).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "make-string", 1, obj6);
                }
                frame74_1.temp = strings.makeString(j);
                obj7 = srfi13.stringFold$V(frame74_1.Fn169, srfi13.Lit0, s, new Object[] {
                    obj, obj1
                });
                if (Scheme.numEqu.apply2(obj7, obj6) != Boolean.FALSE)
                {
                    return frame74_1.temp;
                }
                charsequence = frame74_1.temp;
                try
                {
                    k = ((Number)obj7).intValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "substring", 3, obj7);
                }
                return strings.substring(charsequence, 0, k);
            }
            applytoargs = Scheme.applyToArgs;
            location = srfi13.loc$char$Mnset$Qu;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1096, 22);
                throw unboundlocationexception;
            }
            if (applytoargs.apply2(obj2, criterion) != Boolean.FALSE)
            {
                obj3 = criterion;
            } else
            if (characters.isChar(criterion))
            {
                applytoargs1 = Scheme.applyToArgs;
                location1 = srfi13.loc$char$Mnset;
                try
                {
                    obj5 = location1.get();
                }
                catch (UnboundLocationException unboundlocationexception1)
                {
                    unboundlocationexception1.setLine("srfi13.scm", 1097, 26);
                    throw unboundlocationexception1;
                }
                obj3 = applytoargs1.apply2(obj5, criterion);
            } else
            {
                aobj = new Object[1];
                aobj[0] = criterion;
                obj3 = misc.error$V("string-delete criterion not predicate, char or char-set", aobj);
            }
            frame74_1.cset = obj3;
            obj4 = srfi13.stringFold$V(frame74_1.Fn170, srfi13.Lit0, s, new Object[] {
                obj, obj1
            });
            try
            {
                i = ((Number)obj4).intValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "make-string", 1, obj4);
            }
            frame74_1.ans = strings.makeString(i);
            srfi13.stringFold$V(frame74_1.Fn171, srfi13.Lit0, s, new Object[] {
                obj, obj1
            });
            return frame74_1.ans;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 145)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 146)
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

        public frame73()
        {
        }
    }

    public class frame74 extends ModuleBody
    {

        CharSequence ans;
        Object cset;
        final ModuleMethod lambda$Fn169;
        final ModuleMethod lambda$Fn170;
        final ModuleMethod lambda$Fn171;
        frame73 staticLink;
        CharSequence temp;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.apply2(modulemethod, obj, obj1);

            case 142: 
                return lambda169(obj, obj1);

            case 143: 
                return lambda170(obj, obj1);

            case 144: 
                return lambda171(obj, obj1);
            }
        }

        Object lambda169(Object obj, Object obj1)
        {
            if (Scheme.applyToArgs.apply2(staticLink.criterion, obj) != Boolean.FALSE)
            {
                return obj1;
            }
            CharSequence charsequence = temp;
            CharSeq charseq;
            int i;
            char c;
            try
            {
                charseq = (CharSeq)charsequence;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-set!", 1, charsequence);
            }
            try
            {
                i = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-set!", 2, obj1);
            }
            try
            {
                c = ((Char)obj).charValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-set!", 3, obj);
            }
            strings.stringSet$Ex(charseq, i, c);
            return AddOp.$Pl.apply2(obj1, srfi13.Lit1);
        }

        Object lambda170(Object obj, Object obj1)
        {
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
            Object obj2;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1099, 45);
                throw unboundlocationexception;
            }
            if (applytoargs.apply3(obj2, cset, obj) != Boolean.FALSE)
            {
                return obj1;
            } else
            {
                return AddOp.$Pl.apply2(obj1, srfi13.Lit1);
            }
        }

        Object lambda171(Object obj, Object obj1)
        {
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
            Object obj2;
            CharSequence charsequence;
            CharSeq charseq;
            int i;
            char c;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1104, 35);
                throw unboundlocationexception;
            }
            if (applytoargs.apply3(obj2, cset, obj) != Boolean.FALSE)
            {
                return obj1;
            }
            charsequence = ans;
            try
            {
                charseq = (CharSeq)charsequence;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-set!", 1, charsequence);
            }
            try
            {
                i = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-set!", 2, obj1);
            }
            try
            {
                c = ((Char)obj).charValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-set!", 3, obj);
            }
            strings.stringSet$Ex(charseq, i, c);
            return AddOp.$Pl.apply2(obj1, srfi13.Lit1);
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.match2(modulemethod, obj, obj1, callcontext);

            case 144: 
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;

            case 143: 
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;

            case 142: 
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }

        public frame74()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 142, null, 8194);
            modulemethod.setProperty("source-location", "srfi13.scm:1089");
            lambda$Fn169 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 143, null, 8194);
            modulemethod1.setProperty("source-location", "srfi13.scm:1099");
            lambda$Fn170 = modulemethod1;
            ModuleMethod modulemethod2 = new ModuleMethod(this, 144, null, 8194);
            modulemethod2.setProperty("source-location", "srfi13.scm:1104");
            lambda$Fn171 = modulemethod2;
        }
    }

    public class frame75 extends ModuleBody
    {

        Object criterion;
        final ModuleMethod lambda$Fn172 = new ModuleMethod(this, 150, null, 0);
        final ModuleMethod lambda$Fn173 = new ModuleMethod(this, 151, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 150)
            {
                return lambda172();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 151)
            {
                return lambda173(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda172()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfilter, s, maybe$Mnstart$Plend);
        }

        CharSequence lambda173(Object obj, Object obj1)
        {
            frame76 frame76_1 = new frame76();
            frame76_1.staticLink = this;
            Object obj4;
            if (misc.isProcedure(criterion))
            {
                Object obj6 = AddOp.$Mn.apply2(obj1, obj);
                gnu.kawa.functions.ApplyToArgs applytoargs;
                Location location;
                Object obj2;
                Object aobj[];
                Object obj3;
                int i;
                gnu.kawa.functions.ApplyToArgs applytoargs1;
                Location location1;
                Object obj5;
                int j;
                Object obj7;
                CharSequence charsequence;
                int k;
                try
                {
                    j = ((Number)obj6).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "make-string", 1, obj6);
                }
                frame76_1.temp = strings.makeString(j);
                obj7 = srfi13.stringFold$V(frame76_1.Fn174, srfi13.Lit0, s, new Object[] {
                    obj, obj1
                });
                if (Scheme.numEqu.apply2(obj7, obj6) != Boolean.FALSE)
                {
                    return frame76_1.temp;
                }
                charsequence = frame76_1.temp;
                try
                {
                    k = ((Number)obj7).intValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "substring", 3, obj7);
                }
                return strings.substring(charsequence, 0, k);
            }
            applytoargs = Scheme.applyToArgs;
            location = srfi13.loc$char$Mnset$Qu;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1124, 22);
                throw unboundlocationexception;
            }
            if (applytoargs.apply2(obj2, criterion) != Boolean.FALSE)
            {
                obj3 = criterion;
            } else
            if (characters.isChar(criterion))
            {
                applytoargs1 = Scheme.applyToArgs;
                location1 = srfi13.loc$char$Mnset;
                try
                {
                    obj5 = location1.get();
                }
                catch (UnboundLocationException unboundlocationexception1)
                {
                    unboundlocationexception1.setLine("srfi13.scm", 1125, 26);
                    throw unboundlocationexception1;
                }
                obj3 = applytoargs1.apply2(obj5, criterion);
            } else
            {
                aobj = new Object[1];
                aobj[0] = criterion;
                obj3 = misc.error$V("string-delete criterion not predicate, char or char-set", aobj);
            }
            frame76_1.cset = obj3;
            obj4 = srfi13.stringFold$V(frame76_1.Fn175, srfi13.Lit0, s, new Object[] {
                obj, obj1
            });
            try
            {
                i = ((Number)obj4).intValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "make-string", 1, obj4);
            }
            frame76_1.ans = strings.makeString(i);
            srfi13.stringFold$V(frame76_1.Fn176, srfi13.Lit0, s, new Object[] {
                obj, obj1
            });
            return frame76_1.ans;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 150)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 151)
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

        public frame75()
        {
        }
    }

    public class frame76 extends ModuleBody
    {

        CharSequence ans;
        Object cset;
        final ModuleMethod lambda$Fn174;
        final ModuleMethod lambda$Fn175;
        final ModuleMethod lambda$Fn176;
        frame75 staticLink;
        CharSequence temp;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.apply2(modulemethod, obj, obj1);

            case 147: 
                return lambda174(obj, obj1);

            case 148: 
                return lambda175(obj, obj1);

            case 149: 
                return lambda176(obj, obj1);
            }
        }

        Object lambda174(Object obj, Object obj1)
        {
            if (Scheme.applyToArgs.apply2(staticLink.criterion, obj) != Boolean.FALSE)
            {
                CharSequence charsequence = temp;
                CharSeq charseq;
                int i;
                char c;
                try
                {
                    charseq = (CharSeq)charsequence;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "string-set!", 1, charsequence);
                }
                try
                {
                    i = ((Number)obj1).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-set!", 2, obj1);
                }
                try
                {
                    c = ((Char)obj).charValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "string-set!", 3, obj);
                }
                strings.stringSet$Ex(charseq, i, c);
                obj1 = AddOp.$Pl.apply2(obj1, srfi13.Lit1);
            }
            return obj1;
        }

        Object lambda175(Object obj, Object obj1)
        {
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
            Object obj2;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1128, 45);
                throw unboundlocationexception;
            }
            if (applytoargs.apply3(obj2, cset, obj) != Boolean.FALSE)
            {
                obj1 = AddOp.$Pl.apply2(obj1, srfi13.Lit1);
            }
            return obj1;
        }

        Object lambda176(Object obj, Object obj1)
        {
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
            Object obj2;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1133, 35);
                throw unboundlocationexception;
            }
            if (applytoargs.apply3(obj2, cset, obj) != Boolean.FALSE)
            {
                CharSequence charsequence = ans;
                CharSeq charseq;
                int i;
                char c;
                try
                {
                    charseq = (CharSeq)charsequence;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "string-set!", 1, charsequence);
                }
                try
                {
                    i = ((Number)obj1).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-set!", 2, obj1);
                }
                try
                {
                    c = ((Char)obj).charValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "string-set!", 3, obj);
                }
                strings.stringSet$Ex(charseq, i, c);
                obj1 = AddOp.$Pl.apply2(obj1, srfi13.Lit1);
            }
            return obj1;
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.match2(modulemethod, obj, obj1, callcontext);

            case 149: 
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;

            case 148: 
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;

            case 147: 
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }

        public frame76()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 147, null, 8194);
            modulemethod.setProperty("source-location", "srfi13.scm:1116");
            lambda$Fn174 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 148, null, 8194);
            modulemethod1.setProperty("source-location", "srfi13.scm:1128");
            lambda$Fn175 = modulemethod1;
            ModuleMethod modulemethod2 = new ModuleMethod(this, 149, null, 8194);
            modulemethod2.setProperty("source-location", "srfi13.scm:1133");
            lambda$Fn176 = modulemethod2;
        }
    }

    public class frame77 extends ModuleBody
    {

        Object criterion;
        final ModuleMethod lambda$Fn177 = new ModuleMethod(this, 152, null, 0);
        final ModuleMethod lambda$Fn178 = new ModuleMethod(this, 153, null, 8194);
        LList maybe$Mnstart$Plend;
        Object str;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 152)
            {
                return lambda177();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 153)
            {
                return lambda178(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda177()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnindex, str, maybe$Mnstart$Plend);
        }

        Object lambda178(Object obj, Object obj1)
        {
            if (!characters.isChar(criterion)) goto _L2; else goto _L1
_L1:
            Object obj3 = obj;
_L7:
            Object obj7;
            Object obj10;
            Object obj11 = Scheme.numLss.apply2(obj3, obj1);
            gnu.kawa.functions.ApplyToArgs applytoargs;
            Location location;
            Object obj2;
            Object aobj[];
            boolean flag;
            gnu.kawa.functions.ApplyToArgs applytoargs1;
            Object obj5;
            CharSequence charsequence;
            int i;
            boolean flag1;
            gnu.kawa.functions.ApplyToArgs applytoargs2;
            Location location1;
            Object obj8;
            Object obj9;
            CharSequence charsequence1;
            int j;
            boolean flag2;
            Char char1;
            CharSequence charsequence2;
            int k;
            try
            {
                flag2 = ((Boolean)obj11).booleanValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "x", -2, obj11);
            }
            if (!flag2) goto _L4; else goto _L3
_L3:
            Object obj12 = criterion;
            Object obj13;
            try
            {
                char1 = (Char)obj12;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "char=?", 1, obj12);
            }
            obj13 = str;
            try
            {
                charsequence2 = (CharSequence)obj13;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "string-ref", 1, obj13);
            }
            try
            {
                k = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "string-ref", 2, obj3);
            }
            if (!characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence2, k)))) goto _L6; else goto _L5
_L5:
            return obj3;
_L6:
            obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
              goto _L7
_L4:
            if (flag2)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
_L2:
            applytoargs = Scheme.applyToArgs;
            location = srfi13.loc$char$Mnset$Qu;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1159, 5);
                throw unboundlocationexception;
            }
            if (applytoargs.apply2(obj2, criterion) == Boolean.FALSE)
            {
                break MISSING_BLOCK_LABEL_286;
            }
            obj3 = obj;
            obj7 = Scheme.numLss.apply2(obj3, obj1);
            try
            {
                flag1 = ((Boolean)obj7).booleanValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "x", -2, obj7);
            }
            if (!flag1)
            {
                break MISSING_BLOCK_LABEL_273;
            }
            applytoargs2 = Scheme.applyToArgs;
            location1 = srfi13.loc$char$Mnset$Mncontains$Qu;
            try
            {
                obj8 = location1.get();
            }
            catch (UnboundLocationException unboundlocationexception1)
            {
                unboundlocationexception1.setLine("srfi13.scm", 1162, 9);
                throw unboundlocationexception1;
            }
            obj9 = criterion;
            obj10 = str;
            try
            {
                charsequence1 = (CharSequence)obj10;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "string-ref", 1, obj10);
            }
            try
            {
                j = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "string-ref", 2, obj3);
            }
            if (applytoargs2.apply3(obj8, obj9, Char.make(strings.stringRef(charsequence1, j))) != Boolean.FALSE) goto _L5; else goto _L8
_L8:
            obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
            break MISSING_BLOCK_LABEL_160;
            if (flag1)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
            if (!misc.isProcedure(criterion))
            {
                break MISSING_BLOCK_LABEL_411;
            }
            obj3 = obj;
_L10:
            Object obj4 = Scheme.numLss.apply2(obj3, obj1);
            Object obj6;
            try
            {
                flag = ((Boolean)obj4).booleanValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj4);
            }
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_398;
            }
            applytoargs1 = Scheme.applyToArgs;
            obj5 = criterion;
            obj6 = str;
            try
            {
                charsequence = (CharSequence)obj6;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 1, obj6);
            }
            try
            {
                i = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 2, obj3);
            }
            if (applytoargs1.apply2(obj5, Char.make(strings.stringRef(charsequence, i))) != Boolean.FALSE) goto _L5; else goto _L9
_L9:
            obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
              goto _L10
            if (flag)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
            aobj = new Object[2];
            aobj[0] = srfi13.string$Mnindex;
            aobj[1] = criterion;
            return misc.error$V("Second param is neither char-set, char, or predicate procedure.", aobj);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 152)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 153)
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

        public frame77()
        {
        }
    }

    public class frame78 extends ModuleBody
    {

        Object criterion;
        final ModuleMethod lambda$Fn179 = new ModuleMethod(this, 154, null, 0);
        final ModuleMethod lambda$Fn180 = new ModuleMethod(this, 155, null, 8194);
        LList maybe$Mnstart$Plend;
        Object str;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 154)
            {
                return lambda179();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 155)
            {
                return lambda180(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda179()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnindex$Mnright, str, maybe$Mnstart$Plend);
        }

        Object lambda180(Object obj, Object obj1)
        {
            if (!characters.isChar(criterion)) goto _L2; else goto _L1
_L1:
            Object obj3 = AddOp.$Mn.apply2(obj1, srfi13.Lit1);
_L7:
            Object obj7;
            Object obj10;
            Object obj11 = Scheme.numGEq.apply2(obj3, obj);
            gnu.kawa.functions.ApplyToArgs applytoargs;
            Location location;
            Object obj2;
            Object aobj[];
            boolean flag;
            gnu.kawa.functions.ApplyToArgs applytoargs1;
            Object obj5;
            CharSequence charsequence;
            int i;
            boolean flag1;
            gnu.kawa.functions.ApplyToArgs applytoargs2;
            Location location1;
            Object obj8;
            Object obj9;
            CharSequence charsequence1;
            int j;
            boolean flag2;
            Char char1;
            CharSequence charsequence2;
            int k;
            try
            {
                flag2 = ((Boolean)obj11).booleanValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "x", -2, obj11);
            }
            if (!flag2) goto _L4; else goto _L3
_L3:
            Object obj12 = criterion;
            Object obj13;
            try
            {
                char1 = (Char)obj12;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "char=?", 1, obj12);
            }
            obj13 = str;
            try
            {
                charsequence2 = (CharSequence)obj13;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "string-ref", 1, obj13);
            }
            try
            {
                k = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "string-ref", 2, obj3);
            }
            if (!characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence2, k)))) goto _L6; else goto _L5
_L5:
            return obj3;
_L6:
            obj3 = AddOp.$Mn.apply2(obj3, srfi13.Lit1);
              goto _L7
_L4:
            if (flag2)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
_L2:
            applytoargs = Scheme.applyToArgs;
            location = srfi13.loc$char$Mnset$Qu;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1179, 5);
                throw unboundlocationexception;
            }
            if (applytoargs.apply2(obj2, criterion) == Boolean.FALSE)
            {
                break MISSING_BLOCK_LABEL_304;
            }
            obj3 = AddOp.$Mn.apply2(obj1, srfi13.Lit1);
            obj7 = Scheme.numGEq.apply2(obj3, obj);
            try
            {
                flag1 = ((Boolean)obj7).booleanValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "x", -2, obj7);
            }
            if (!flag1)
            {
                break MISSING_BLOCK_LABEL_291;
            }
            applytoargs2 = Scheme.applyToArgs;
            location1 = srfi13.loc$char$Mnset$Mncontains$Qu;
            try
            {
                obj8 = location1.get();
            }
            catch (UnboundLocationException unboundlocationexception1)
            {
                unboundlocationexception1.setLine("srfi13.scm", 1182, 9);
                throw unboundlocationexception1;
            }
            obj9 = criterion;
            obj10 = str;
            try
            {
                charsequence1 = (CharSequence)obj10;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "string-ref", 1, obj10);
            }
            try
            {
                j = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "string-ref", 2, obj3);
            }
            if (applytoargs2.apply3(obj8, obj9, Char.make(strings.stringRef(charsequence1, j))) != Boolean.FALSE) goto _L5; else goto _L8
_L8:
            obj3 = AddOp.$Mn.apply2(obj3, srfi13.Lit1);
            break MISSING_BLOCK_LABEL_178;
            if (flag1)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
            if (!misc.isProcedure(criterion))
            {
                break MISSING_BLOCK_LABEL_438;
            }
            obj3 = AddOp.$Mn.apply2(obj1, srfi13.Lit1);
_L10:
            Object obj4 = Scheme.numGEq.apply2(obj3, obj);
            Object obj6;
            try
            {
                flag = ((Boolean)obj4).booleanValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj4);
            }
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_425;
            }
            applytoargs1 = Scheme.applyToArgs;
            obj5 = criterion;
            obj6 = str;
            try
            {
                charsequence = (CharSequence)obj6;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 1, obj6);
            }
            try
            {
                i = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 2, obj3);
            }
            if (applytoargs1.apply2(obj5, Char.make(strings.stringRef(charsequence, i))) != Boolean.FALSE) goto _L5; else goto _L9
_L9:
            obj3 = AddOp.$Mn.apply2(obj3, srfi13.Lit1);
              goto _L10
            if (flag)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
            aobj = new Object[2];
            aobj[0] = srfi13.string$Mnindex$Mnright;
            aobj[1] = criterion;
            return misc.error$V("Second param is neither char-set, char, or predicate procedure.", aobj);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 154)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 155)
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

        public frame78()
        {
        }
    }

    public class frame79 extends ModuleBody
    {

        Object criterion;
        final ModuleMethod lambda$Fn181 = new ModuleMethod(this, 156, null, 0);
        final ModuleMethod lambda$Fn182 = new ModuleMethod(this, 157, null, 8194);
        LList maybe$Mnstart$Plend;
        Object str;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 156)
            {
                return lambda181();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 157)
            {
                return lambda182(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda181()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnskip, str, maybe$Mnstart$Plend);
        }

        Object lambda182(Object obj, Object obj1)
        {
            if (!characters.isChar(criterion)) goto _L2; else goto _L1
_L1:
            Object obj3 = obj;
_L7:
            Object obj7;
            Object obj10;
            Object obj11 = Scheme.numLss.apply2(obj3, obj1);
            gnu.kawa.functions.ApplyToArgs applytoargs;
            Location location;
            Object obj2;
            Object aobj[];
            boolean flag;
            gnu.kawa.functions.ApplyToArgs applytoargs1;
            Object obj5;
            CharSequence charsequence;
            int i;
            boolean flag1;
            gnu.kawa.functions.ApplyToArgs applytoargs2;
            Location location1;
            Object obj8;
            Object obj9;
            CharSequence charsequence1;
            int j;
            boolean flag2;
            Char char1;
            CharSequence charsequence2;
            int k;
            try
            {
                flag2 = ((Boolean)obj11).booleanValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "x", -2, obj11);
            }
            if (!flag2) goto _L4; else goto _L3
_L3:
            Object obj12 = criterion;
            Object obj13;
            try
            {
                char1 = (Char)obj12;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "char=?", 1, obj12);
            }
            obj13 = str;
            try
            {
                charsequence2 = (CharSequence)obj13;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "string-ref", 1, obj13);
            }
            try
            {
                k = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "string-ref", 2, obj3);
            }
            if (!characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence2, k)))) goto _L6; else goto _L5
_L5:
            obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
              goto _L7
_L4:
            if (!flag2) goto _L9; else goto _L8
_L8:
            obj3 = Boolean.TRUE;
_L6:
            return obj3;
_L9:
            return Boolean.FALSE;
_L2:
            applytoargs = Scheme.applyToArgs;
            location = srfi13.loc$char$Mnset$Qu;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1200, 5);
                throw unboundlocationexception;
            }
            if (applytoargs.apply2(obj2, criterion) == Boolean.FALSE)
            {
                break MISSING_BLOCK_LABEL_287;
            }
            obj3 = obj;
            obj7 = Scheme.numLss.apply2(obj3, obj1);
            try
            {
                flag1 = ((Boolean)obj7).booleanValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "x", -2, obj7);
            }
            if (!flag1)
            {
                break MISSING_BLOCK_LABEL_274;
            }
            applytoargs2 = Scheme.applyToArgs;
            location1 = srfi13.loc$char$Mnset$Mncontains$Qu;
            try
            {
                obj8 = location1.get();
            }
            catch (UnboundLocationException unboundlocationexception1)
            {
                unboundlocationexception1.setLine("srfi13.scm", 1203, 9);
                throw unboundlocationexception1;
            }
            obj9 = criterion;
            obj10 = str;
            try
            {
                charsequence1 = (CharSequence)obj10;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "string-ref", 1, obj10);
            }
            try
            {
                j = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "string-ref", 2, obj3);
            }
            if (applytoargs2.apply3(obj8, obj9, Char.make(strings.stringRef(charsequence1, j))) == Boolean.FALSE) goto _L6; else goto _L10
_L10:
            obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
            break MISSING_BLOCK_LABEL_161;
            if (flag1)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
            if (!misc.isProcedure(criterion))
            {
                break MISSING_BLOCK_LABEL_412;
            }
            obj3 = obj;
_L12:
            Object obj4 = Scheme.numLss.apply2(obj3, obj1);
            Object obj6;
            try
            {
                flag = ((Boolean)obj4).booleanValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj4);
            }
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_399;
            }
            applytoargs1 = Scheme.applyToArgs;
            obj5 = criterion;
            obj6 = str;
            try
            {
                charsequence = (CharSequence)obj6;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 1, obj6);
            }
            try
            {
                i = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 2, obj3);
            }
            if (applytoargs1.apply2(obj5, Char.make(strings.stringRef(charsequence, i))) == Boolean.FALSE) goto _L6; else goto _L11
_L11:
            obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
              goto _L12
            if (flag)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
            aobj = new Object[2];
            aobj[0] = srfi13.string$Mnskip;
            aobj[1] = criterion;
            return misc.error$V("Second param is neither char-set, char, or predicate procedure.", aobj);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 156)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 157)
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

        public frame79()
        {
        }
    }

    public class frame8 extends ModuleBody
    {

        final ModuleMethod lambda$Fn21 = new ModuleMethod(this, 18, null, 0);
        final ModuleMethod lambda$Fn22 = new ModuleMethod(this, 19, null, 8194);
        LList maybe$Mnstart$Plend;
        Object proc;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 18)
            {
                return lambda21();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 19)
            {
                return lambda22(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda21()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfor$Mneach$Mnindex, s, maybe$Mnstart$Plend);
        }

        Object lambda22(Object obj, Object obj1)
        {
            for (Object obj2 = obj; Scheme.numLss.apply2(obj2, obj1) != Boolean.FALSE; obj2 = AddOp.$Pl.apply2(obj2, srfi13.Lit1))
            {
                Scheme.applyToArgs.apply2(proc, obj2);
            }

            return Values.empty;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 18)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 19)
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

        public frame8()
        {
        }
    }

    public class frame80 extends ModuleBody
    {

        Object criterion;
        final ModuleMethod lambda$Fn183 = new ModuleMethod(this, 158, null, 0);
        final ModuleMethod lambda$Fn184 = new ModuleMethod(this, 159, null, 8194);
        LList maybe$Mnstart$Plend;
        Object str;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 158)
            {
                return lambda183();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 159)
            {
                return lambda184(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda183()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnskip$Mnright, str, maybe$Mnstart$Plend);
        }

        Object lambda184(Object obj, Object obj1)
        {
            if (!characters.isChar(criterion)) goto _L2; else goto _L1
_L1:
            Object obj3 = AddOp.$Mn.apply2(obj1, srfi13.Lit1);
_L7:
            Object obj7;
            Object obj10;
            Object obj11 = Scheme.numGEq.apply2(obj3, obj);
            gnu.kawa.functions.ApplyToArgs applytoargs;
            Location location;
            Object obj2;
            Object aobj[];
            boolean flag;
            gnu.kawa.functions.ApplyToArgs applytoargs1;
            Object obj5;
            CharSequence charsequence;
            int i;
            boolean flag1;
            gnu.kawa.functions.ApplyToArgs applytoargs2;
            Location location1;
            Object obj8;
            Object obj9;
            CharSequence charsequence1;
            int j;
            boolean flag2;
            Char char1;
            CharSequence charsequence2;
            int k;
            try
            {
                flag2 = ((Boolean)obj11).booleanValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "x", -2, obj11);
            }
            if (!flag2) goto _L4; else goto _L3
_L3:
            Object obj12 = criterion;
            Object obj13;
            try
            {
                char1 = (Char)obj12;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "char=?", 1, obj12);
            }
            obj13 = str;
            try
            {
                charsequence2 = (CharSequence)obj13;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "string-ref", 1, obj13);
            }
            try
            {
                k = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "string-ref", 2, obj3);
            }
            if (!characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence2, k)))) goto _L6; else goto _L5
_L5:
            obj3 = AddOp.$Mn.apply2(obj3, srfi13.Lit1);
              goto _L7
_L4:
            if (!flag2) goto _L9; else goto _L8
_L8:
            obj3 = Boolean.TRUE;
_L6:
            return obj3;
_L9:
            return Boolean.FALSE;
_L2:
            applytoargs = Scheme.applyToArgs;
            location = srfi13.loc$char$Mnset$Qu;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1222, 5);
                throw unboundlocationexception;
            }
            if (applytoargs.apply2(obj2, criterion) == Boolean.FALSE)
            {
                break MISSING_BLOCK_LABEL_305;
            }
            obj3 = AddOp.$Mn.apply2(obj1, srfi13.Lit1);
            obj7 = Scheme.numGEq.apply2(obj3, obj);
            try
            {
                flag1 = ((Boolean)obj7).booleanValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "x", -2, obj7);
            }
            if (!flag1)
            {
                break MISSING_BLOCK_LABEL_292;
            }
            applytoargs2 = Scheme.applyToArgs;
            location1 = srfi13.loc$char$Mnset$Mncontains$Qu;
            try
            {
                obj8 = location1.get();
            }
            catch (UnboundLocationException unboundlocationexception1)
            {
                unboundlocationexception1.setLine("srfi13.scm", 1225, 9);
                throw unboundlocationexception1;
            }
            obj9 = criterion;
            obj10 = str;
            try
            {
                charsequence1 = (CharSequence)obj10;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "string-ref", 1, obj10);
            }
            try
            {
                j = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "string-ref", 2, obj3);
            }
            if (applytoargs2.apply3(obj8, obj9, Char.make(strings.stringRef(charsequence1, j))) == Boolean.FALSE) goto _L6; else goto _L10
_L10:
            obj3 = AddOp.$Mn.apply2(obj3, srfi13.Lit1);
            break MISSING_BLOCK_LABEL_179;
            if (flag1)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
            if (!misc.isProcedure(criterion))
            {
                break MISSING_BLOCK_LABEL_439;
            }
            obj3 = AddOp.$Mn.apply2(obj1, srfi13.Lit1);
_L12:
            Object obj4 = Scheme.numGEq.apply2(obj3, obj);
            Object obj6;
            try
            {
                flag = ((Boolean)obj4).booleanValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj4);
            }
            if (!flag)
            {
                break MISSING_BLOCK_LABEL_426;
            }
            applytoargs1 = Scheme.applyToArgs;
            obj5 = criterion;
            obj6 = str;
            try
            {
                charsequence = (CharSequence)obj6;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-ref", 1, obj6);
            }
            try
            {
                i = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 2, obj3);
            }
            if (applytoargs1.apply2(obj5, Char.make(strings.stringRef(charsequence, i))) == Boolean.FALSE) goto _L6; else goto _L11
_L11:
            obj3 = AddOp.$Mn.apply2(obj3, srfi13.Lit1);
              goto _L12
            if (flag)
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
            aobj = new Object[2];
            aobj[0] = srfi13.string$Mnskip$Mnright;
            aobj[1] = criterion;
            return misc.error$V("CRITERION param is neither char-set or char.", aobj);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 158)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 159)
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

        public frame80()
        {
        }
    }

    public class frame81 extends ModuleBody
    {

        Object criterion;
        final ModuleMethod lambda$Fn185 = new ModuleMethod(this, 160, null, 0);
        final ModuleMethod lambda$Fn186 = new ModuleMethod(this, 161, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 160)
            {
                return lambda185();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 161)
            {
                return lambda186(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda185()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncount, s, maybe$Mnstart$Plend);
        }

        Object lambda186(Object obj, Object obj1)
        {
            if (!characters.isChar(criterion)) goto _L2; else goto _L1
_L1:
            Object obj8;
            Object obj3 = srfi13.Lit0;
            Object obj13 = obj;
            while (Scheme.numGEq.apply2(obj13, obj1) == Boolean.FALSE) 
            {
                Object obj14 = AddOp.$Pl.apply2(obj13, srfi13.Lit1);
                Object obj15 = criterion;
                gnu.kawa.functions.ApplyToArgs applytoargs;
                Location location;
                Object obj2;
                Object aobj[];
                Object obj5;
                gnu.kawa.functions.ApplyToArgs applytoargs1;
                Object obj6;
                CharSequence charsequence;
                int i;
                Object obj9;
                gnu.kawa.functions.ApplyToArgs applytoargs2;
                Location location1;
                Object obj10;
                Object obj11;
                CharSequence charsequence1;
                int j;
                Char char1;
                Object obj16;
                CharSequence charsequence2;
                int k;
                try
                {
                    char1 = (Char)obj15;
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "char=?", 1, obj15);
                }
                obj16 = s;
                try
                {
                    charsequence2 = (CharSequence)obj16;
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "string-ref", 1, obj16);
                }
                try
                {
                    k = ((Number)obj13).intValue();
                }
                catch (ClassCastException classcastexception6)
                {
                    throw new WrongType(classcastexception6, "string-ref", 2, obj13);
                }
                if (characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence2, k))))
                {
                    obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
                }
                obj13 = obj14;
            }
              goto _L3
_L2:
            applytoargs = Scheme.applyToArgs;
            location = srfi13.loc$char$Mnset$Qu;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1246, 5);
                throw unboundlocationexception;
            }
            if (applytoargs.apply2(obj2, criterion) == Boolean.FALSE) goto _L5; else goto _L4
_L4:
            obj3 = srfi13.Lit0;
            obj8 = obj;
_L7:
            if (Scheme.numGEq.apply2(obj8, obj1) != Boolean.FALSE) goto _L3; else goto _L6
_L6:
            obj9 = AddOp.$Pl.apply2(obj8, srfi13.Lit1);
            applytoargs2 = Scheme.applyToArgs;
            location1 = srfi13.loc$char$Mnset$Mncontains$Qu;
            Object obj12;
            try
            {
                obj10 = location1.get();
            }
            catch (UnboundLocationException unboundlocationexception1)
            {
                unboundlocationexception1.setLine("srfi13.scm", 1248, 16);
                throw unboundlocationexception1;
            }
            obj11 = criterion;
            obj12 = s;
            try
            {
                charsequence1 = (CharSequence)obj12;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ref", 1, obj12);
            }
            try
            {
                j = ((Number)obj8).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "string-ref", 2, obj8);
            }
            if (applytoargs2.apply3(obj10, obj11, Char.make(strings.stringRef(charsequence1, j))) != Boolean.FALSE)
            {
                obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
            }
            obj8 = obj9;
            if (true) goto _L7; else goto _L3
_L5:
            if (misc.isProcedure(criterion))
            {
                obj3 = srfi13.Lit0;
                Object obj4 = obj;
                while (Scheme.numGEq.apply2(obj4, obj1) == Boolean.FALSE) 
                {
                    obj5 = AddOp.$Pl.apply2(obj4, srfi13.Lit1);
                    applytoargs1 = Scheme.applyToArgs;
                    obj6 = criterion;
                    Object obj7 = s;
                    try
                    {
                        charsequence = (CharSequence)obj7;
                    }
                    catch (ClassCastException classcastexception)
                    {
                        throw new WrongType(classcastexception, "string-ref", 1, obj7);
                    }
                    try
                    {
                        i = ((Number)obj4).intValue();
                    }
                    catch (ClassCastException classcastexception1)
                    {
                        throw new WrongType(classcastexception1, "string-ref", 2, obj4);
                    }
                    if (applytoargs1.apply2(obj6, Char.make(strings.stringRef(charsequence, i))) != Boolean.FALSE)
                    {
                        obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
                    }
                    obj4 = obj5;
                }
            } else
            {
                aobj = new Object[2];
                aobj[0] = srfi13.string$Mncount;
                aobj[1] = criterion;
                obj3 = misc.error$V("CRITERION param is neither char-set or char.", aobj);
            }
_L3:
            return obj3;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 160)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 161)
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

        public frame81()
        {
        }
    }

    public class frame82 extends ModuleBody
    {

        Object _fldchar;
        final ModuleMethod lambda$Fn187 = new ModuleMethod(this, 162, null, 0);
        final ModuleMethod lambda$Fn188 = new ModuleMethod(this, 163, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 162)
            {
                return lambda187();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 163)
            {
                return lambda188(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda187()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfill$Ex, s, maybe$Mnstart$Plend);
        }

        Object lambda188(Object obj, Object obj1)
        {
            Object obj2 = AddOp.$Mn.apply2(obj1, srfi13.Lit1);
            while (Scheme.numLss.apply2(obj2, obj) == Boolean.FALSE) 
            {
                Object obj3 = s;
                CharSeq charseq;
                int i;
                Object obj4;
                char c;
                try
                {
                    charseq = (CharSeq)obj3;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "string-set!", 1, obj3);
                }
                try
                {
                    i = ((Number)obj2).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-set!", 2, obj2);
                }
                obj4 = _fldchar;
                try
                {
                    c = ((Char)obj4).charValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "string-set!", 3, obj4);
                }
                strings.stringSet$Ex(charseq, i, c);
                obj2 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
            }
            return Values.empty;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 162)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 163)
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

        public frame82()
        {
        }
    }

    public class frame83 extends ModuleBody
    {

        final ModuleMethod lambda$Fn189 = new ModuleMethod(this, 166, null, 0);
        final ModuleMethod lambda$Fn190 = new ModuleMethod(this, 167, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object pattern;
        Object text;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 166)
            {
                return lambda189();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 167)
            {
                return lambda190(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda189()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mncontains, text, maybe$Mnstarts$Plends);
        }

        Object lambda190(Object obj, Object obj1, Object obj2)
        {
            frame84 frame84_1 = new frame84();
            frame84_1.staticLink = this;
            frame84_1.rest = obj;
            frame84_1.Mnstart = obj1;
            frame84_1.Mnend = obj2;
            return call_with_values.callWithValues(frame84_1.Fn191, frame84_1.Fn192);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 166)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 167)
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

        public frame83()
        {
        }
    }

    public class frame84 extends ModuleBody
    {

        final ModuleMethod lambda$Fn191 = new ModuleMethod(this, 164, null, 0);
        final ModuleMethod lambda$Fn192 = new ModuleMethod(this, 165, null, 8194);
        Object rest;
        frame83 staticLink;
        Object t$Mnend;
        Object t$Mnstart;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 164)
            {
                return lambda191();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 165)
            {
                return lambda192(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda191()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncontains, staticLink.pattern, rest);
        }

        Object lambda192(Object obj, Object obj1)
        {
            return srfi13.$PcKmpSearch(staticLink.pattern, staticLink.text, characters.char$Eq$Qu, obj, obj1, t$Mnstart, t$Mnend);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 164)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 165)
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

        public frame84()
        {
        }
    }

    public class frame85 extends ModuleBody
    {

        final ModuleMethod lambda$Fn193 = new ModuleMethod(this, 170, null, 0);
        final ModuleMethod lambda$Fn194 = new ModuleMethod(this, 171, null, 12291);
        LList maybe$Mnstarts$Plends;
        Object pattern;
        Object text;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 170)
            {
                return lambda193();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 171)
            {
                return lambda194(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda193()
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mncontains$Mnci, text, maybe$Mnstarts$Plends);
        }

        Object lambda194(Object obj, Object obj1, Object obj2)
        {
            frame86 frame86_1 = new frame86();
            frame86_1.staticLink = this;
            frame86_1.rest = obj;
            frame86_1.Mnstart = obj1;
            frame86_1.Mnend = obj2;
            return call_with_values.callWithValues(frame86_1.Fn195, frame86_1.Fn196);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 170)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
        {
            if (modulemethod.selector == 171)
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

        public frame85()
        {
        }
    }

    public class frame86 extends ModuleBody
    {

        final ModuleMethod lambda$Fn195 = new ModuleMethod(this, 168, null, 0);
        final ModuleMethod lambda$Fn196 = new ModuleMethod(this, 169, null, 8194);
        Object rest;
        frame85 staticLink;
        Object t$Mnend;
        Object t$Mnstart;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 168)
            {
                return lambda195();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 169)
            {
                return lambda196(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda195()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mncontains$Mnci, staticLink.pattern, rest);
        }

        Object lambda196(Object obj, Object obj1)
        {
            return srfi13.$PcKmpSearch(staticLink.pattern, staticLink.text, unicode.char$Mnci$Eq$Qu, obj, obj1, t$Mnstart, t$Mnend);
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 168)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 169)
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

        public frame86()
        {
        }
    }

    public class frame87 extends ModuleBody
    {

        final ModuleMethod lambda$Fn197;
        Object pattern;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 172)
            {
                return lambda197(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda197(Object obj)
        {
            return srfi13.stringParseStart$PlEnd(srfi13.make$Mnkmp$Mnrestart$Mnvector, pattern, obj);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 172)
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

        public frame87()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 172, null, 4097);
            modulemethod.setProperty("source-location", "srfi13.scm:1399");
            lambda$Fn197 = modulemethod;
        }
    }

    public class frame88 extends ModuleBody
    {

        final ModuleMethod lambda$Fn198;
        final ModuleMethod lambda$Fn199;
        int patlen;
        Object s;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.apply1(modulemethod, obj);

            case 173: 
                return lambda198(obj);

            case 174: 
                break;
            }
            if (lambda199(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }
        }

        Object lambda198(Object obj)
        {
            return srfi13.stringParseStart$PlEnd(srfi13.string$Mnkmp$Mnpartial$Mnsearch, s, obj);
        }

        boolean lambda199(Object obj)
        {
            boolean flag = numbers.isInteger(obj);
            if (flag)
            {
                flag = numbers.isExact(obj);
                if (flag)
                {
                    Object obj1 = Scheme.numLEq.apply2(srfi13.Lit0, obj);
                    boolean flag1;
                    try
                    {
                        flag1 = ((Boolean)obj1).booleanValue();
                    }
                    catch (ClassCastException classcastexception)
                    {
                        throw new WrongType(classcastexception, "x", -2, obj1);
                    }
                    flag = flag1;
                    if (flag)
                    {
                        flag = ((Boolean)Scheme.numLss.apply2(obj, Integer.valueOf(patlen))).booleanValue();
                    }
                }
            }
            return flag;
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.match1(modulemethod, obj, callcontext);

            case 174: 
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;

            case 173: 
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }
        }

        public frame88()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 173, null, 4097);
            modulemethod.setProperty("source-location", "srfi13.scm:1468");
            lambda$Fn198 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 174, null, 4097);
            modulemethod1.setProperty("source-location", "srfi13.scm:1472");
            lambda$Fn199 = modulemethod1;
        }
    }

    public class frame89 extends ModuleBody
    {

        final ModuleMethod lambda$Fn200 = new ModuleMethod(this, 175, null, 0);
        final ModuleMethod lambda$Fn201 = new ModuleMethod(this, 176, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 175)
            {
                return lambda200();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 176)
            {
                return lambda201(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda200()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreverse, s, maybe$Mnstart$Plend);
        }

        CharSequence lambda201(Object obj, Object obj1)
        {
            Object obj2 = AddOp.$Mn.apply2(obj1, obj);
            int i;
            CharSequence charsequence;
            Object obj3;
            Object obj4;
            try
            {
                i = ((Number)obj2).intValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "make-string", 1, obj2);
            }
            charsequence = strings.makeString(i);
            obj3 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
            obj4 = obj;
            while (Scheme.numLss.apply2(obj3, srfi13.Lit0) == Boolean.FALSE) 
            {
                CharSeq charseq;
                int j;
                Object obj5;
                CharSequence charsequence1;
                int k;
                try
                {
                    charseq = (CharSeq)charsequence;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-set!", 1, charsequence);
                }
                try
                {
                    j = ((Number)obj3).intValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "string-set!", 2, obj3);
                }
                obj5 = s;
                try
                {
                    charsequence1 = (CharSequence)obj5;
                }
                catch (ClassCastException classcastexception3)
                {
                    throw new WrongType(classcastexception3, "string-ref", 1, obj5);
                }
                try
                {
                    k = ((Number)obj4).intValue();
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "string-ref", 2, obj4);
                }
                strings.stringSet$Ex(charseq, j, strings.stringRef(charsequence1, k));
                obj4 = AddOp.$Pl.apply2(obj4, srfi13.Lit1);
                obj3 = AddOp.$Mn.apply2(obj3, srfi13.Lit1);
            }
            return charsequence;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 175)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 176)
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

        public frame89()
        {
        }
    }

    public class frame9 extends ModuleBody
    {

        Object criterion;
        final ModuleMethod lambda$Fn23 = new ModuleMethod(this, 20, null, 0);
        final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 21, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 20)
            {
                return lambda23();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 21)
            {
                return lambda24(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda23()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnevery, s, maybe$Mnstart$Plend);
        }

        Object lambda24(Object obj, Object obj1)
        {
            if (characters.isChar(criterion))
            {
                Object obj14 = obj;
                do
                {
                    Object obj15 = Scheme.numGEq.apply2(obj14, obj1);
                    gnu.kawa.functions.ApplyToArgs applytoargs;
                    Location location;
                    Object obj2;
                    Object aobj[];
                    boolean flag;
                    CharSequence charsequence;
                    int i;
                    char c;
                    Object obj6;
                    Object obj7;
                    boolean flag1;
                    gnu.kawa.functions.ApplyToArgs applytoargs1;
                    Location location1;
                    Object obj10;
                    Object obj11;
                    CharSequence charsequence1;
                    int j;
                    Object obj13;
                    boolean flag2;
                    Object obj16;
                    Char char1;
                    Object obj17;
                    CharSequence charsequence2;
                    int k;
                    boolean flag3;
                    try
                    {
                        flag2 = ((Boolean)obj15).booleanValue();
                    }
                    catch (ClassCastException classcastexception6)
                    {
                        throw new WrongType(classcastexception6, "x", -2, obj15);
                    }
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
                    obj16 = criterion;
                    try
                    {
                        char1 = (Char)obj16;
                    }
                    catch (ClassCastException classcastexception7)
                    {
                        throw new WrongType(classcastexception7, "char=?", 1, obj16);
                    }
                    obj17 = s;
                    try
                    {
                        charsequence2 = (CharSequence)obj17;
                    }
                    catch (ClassCastException classcastexception8)
                    {
                        throw new WrongType(classcastexception8, "string-ref", 1, obj17);
                    }
                    try
                    {
                        k = ((Number)obj14).intValue();
                    }
                    catch (ClassCastException classcastexception9)
                    {
                        throw new WrongType(classcastexception9, "string-ref", 2, obj14);
                    }
                    flag3 = characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence2, k)));
                    if (flag3)
                    {
                        obj14 = AddOp.$Pl.apply2(obj14, srfi13.Lit1);
                    } else
                    if (flag3)
                    {
                        return Boolean.TRUE;
                    } else
                    {
                        return Boolean.FALSE;
                    }
                } while (true);
            }
            applytoargs = Scheme.applyToArgs;
            location = srfi13.loc$char$Mnset$Qu;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 489, 5);
                throw unboundlocationexception;
            }
            if (applytoargs.apply2(obj2, criterion) != Boolean.FALSE)
            {
                Object obj8 = obj;
                do
                {
                    Object obj9 = Scheme.numGEq.apply2(obj8, obj1);
                    Object obj12;
                    try
                    {
                        flag1 = ((Boolean)obj9).booleanValue();
                    }
                    catch (ClassCastException classcastexception3)
                    {
                        throw new WrongType(classcastexception3, "x", -2, obj9);
                    }
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
                    applytoargs1 = Scheme.applyToArgs;
                    location1 = srfi13.loc$char$Mnset$Mncontains$Qu;
                    try
                    {
                        obj10 = location1.get();
                    }
                    catch (UnboundLocationException unboundlocationexception1)
                    {
                        unboundlocationexception1.setLine("srfi13.scm", 492, 9);
                        throw unboundlocationexception1;
                    }
                    obj11 = criterion;
                    obj12 = s;
                    try
                    {
                        charsequence1 = (CharSequence)obj12;
                    }
                    catch (ClassCastException classcastexception4)
                    {
                        throw new WrongType(classcastexception4, "string-ref", 1, obj12);
                    }
                    try
                    {
                        j = ((Number)obj8).intValue();
                    }
                    catch (ClassCastException classcastexception5)
                    {
                        throw new WrongType(classcastexception5, "string-ref", 2, obj8);
                    }
                    obj13 = applytoargs1.apply3(obj10, obj11, Char.make(strings.stringRef(charsequence1, j)));
                    if (obj13 != Boolean.FALSE)
                    {
                        obj8 = AddOp.$Pl.apply2(obj8, srfi13.Lit1);
                    } else
                    {
                        return obj13;
                    }
                } while (true);
            } else
            if (misc.isProcedure(criterion))
            {
                Object obj3 = Scheme.numEqu.apply2(obj, obj1);
                Object obj4;
                try
                {
                    flag = ((Boolean)obj3).booleanValue();
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "x", -2, obj3);
                }
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
                obj4 = obj;
                do
                {
                    Object obj5 = s;
                    try
                    {
                        charsequence = (CharSequence)obj5;
                    }
                    catch (ClassCastException classcastexception1)
                    {
                        throw new WrongType(classcastexception1, "string-ref", 1, obj5);
                    }
                    try
                    {
                        i = ((Number)obj4).intValue();
                    }
                    catch (ClassCastException classcastexception2)
                    {
                        throw new WrongType(classcastexception2, "string-ref", 2, obj4);
                    }
                    c = strings.stringRef(charsequence, i);
                    obj6 = AddOp.$Pl.apply2(obj4, srfi13.Lit1);
                    if (Scheme.numEqu.apply2(obj6, obj1) != Boolean.FALSE)
                    {
                        return Scheme.applyToArgs.apply2(criterion, Char.make(c));
                    }
                    obj7 = Scheme.applyToArgs.apply2(criterion, Char.make(c));
                    if (obj7 != Boolean.FALSE)
                    {
                        obj4 = obj6;
                    } else
                    {
                        return obj7;
                    }
                } while (true);
            } else
            {
                aobj = new Object[2];
                aobj[0] = srfi13.string$Mnevery;
                aobj[1] = criterion;
                return misc.error$V("Second param is neither char-set, char, or predicate procedure.", aobj);
            }
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 20)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 21)
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

        public frame9()
        {
        }
    }

    public class frame90 extends ModuleBody
    {

        final ModuleMethod lambda$Fn202 = new ModuleMethod(this, 177, null, 0);
        final ModuleMethod lambda$Fn203 = new ModuleMethod(this, 178, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 177)
            {
                return lambda202();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 178)
            {
                return lambda203(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda202()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreverse$Ex, s, maybe$Mnstart$Plend);
        }

        Object lambda203(Object obj, Object obj1)
        {
            Object obj2 = AddOp.$Mn.apply2(obj1, srfi13.Lit1);
            Object obj3 = obj;
            while (Scheme.numLEq.apply2(obj2, obj3) == Boolean.FALSE) 
            {
                Object obj4 = s;
                CharSequence charsequence;
                int i;
                char c;
                Object obj5;
                CharSeq charseq;
                int j;
                Object obj6;
                CharSequence charsequence1;
                int k;
                Object obj7;
                CharSeq charseq1;
                int l;
                try
                {
                    charsequence = (CharSequence)obj4;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "string-ref", 1, obj4);
                }
                try
                {
                    i = ((Number)obj2).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-ref", 2, obj2);
                }
                c = strings.stringRef(charsequence, i);
                obj5 = s;
                try
                {
                    charseq = (CharSeq)obj5;
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "string-set!", 1, obj5);
                }
                try
                {
                    j = ((Number)obj2).intValue();
                }
                catch (ClassCastException classcastexception3)
                {
                    throw new WrongType(classcastexception3, "string-set!", 2, obj2);
                }
                obj6 = s;
                try
                {
                    charsequence1 = (CharSequence)obj6;
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "string-ref", 1, obj6);
                }
                try
                {
                    k = ((Number)obj3).intValue();
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "string-ref", 2, obj3);
                }
                strings.stringSet$Ex(charseq, j, strings.stringRef(charsequence1, k));
                obj7 = s;
                try
                {
                    charseq1 = (CharSeq)obj7;
                }
                catch (ClassCastException classcastexception6)
                {
                    throw new WrongType(classcastexception6, "string-set!", 1, obj7);
                }
                try
                {
                    l = ((Number)obj3).intValue();
                }
                catch (ClassCastException classcastexception7)
                {
                    throw new WrongType(classcastexception7, "string-set!", 2, obj3);
                }
                strings.stringSet$Ex(charseq1, l, c);
                obj2 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
                obj3 = AddOp.$Pl.apply2(obj3, srfi13.Lit1);
            }
            return Values.empty;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 177)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 178)
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

        public frame90()
        {
        }
    }

    public class frame91 extends ModuleBody
    {

        final ModuleMethod lambda$Fn204 = new ModuleMethod(this, 179, null, 0);
        final ModuleMethod lambda$Fn205 = new ModuleMethod(this, 180, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 179)
            {
                return lambda204();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 180)
            {
                return lambda205(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda204()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mn$Grlist, s, maybe$Mnstart$Plend);
        }

        Object lambda205(Object obj, Object obj1)
        {
            Object obj2 = AddOp.$Mn.apply2(obj1, srfi13.Lit1);
            Object obj3 = LList.Empty;
            while (Scheme.numLss.apply2(obj2, obj) == Boolean.FALSE) 
            {
                Object obj4 = AddOp.$Mn.apply2(obj2, srfi13.Lit1);
                Object obj5 = s;
                CharSequence charsequence;
                int i;
                try
                {
                    charsequence = (CharSequence)obj5;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "string-ref", 1, obj5);
                }
                try
                {
                    i = ((Number)obj2).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-ref", 2, obj2);
                }
                obj3 = lists.cons(Char.make(strings.stringRef(charsequence, i)), obj3);
                obj2 = obj4;
            }
            return obj3;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 179)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 180)
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

        public frame91()
        {
        }
    }

    public class frame92 extends ModuleBody
    {

        Object end1;
        final ModuleMethod lambda$Fn206 = new ModuleMethod(this, 181, null, 0);
        final ModuleMethod lambda$Fn207 = new ModuleMethod(this, 182, null, 8194);
        LList maybe$Mnstart$Plend;
        Object s1;
        Object s2;
        Object start1;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 181)
            {
                return lambda206();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 182)
            {
                return lambda207(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda206()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnreplace, s2, maybe$Mnstart$Plend);
        }

        CharSequence lambda207(Object obj, Object obj1)
        {
            Object obj2 = s1;
            CharSequence charsequence;
            int i;
            Object obj3;
            Object obj4;
            int j;
            CharSequence charsequence1;
            Object obj5;
            CharSequence charsequence2;
            Object obj6;
            int k;
            Object obj7;
            int l;
            Object obj8;
            CharSequence charsequence3;
            int i1;
            int j1;
            Object obj9;
            int k1;
            Object obj10;
            CharSequence charsequence4;
            Object obj11;
            int l1;
            try
            {
                charsequence = (CharSequence)obj2;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-length", 1, obj2);
            }
            i = strings.stringLength(charsequence);
            obj3 = AddOp.$Mn.apply2(obj1, obj);
            obj4 = AddOp.$Pl.apply2(AddOp.$Mn.apply2(Integer.valueOf(i), AddOp.$Mn.apply2(end1, start1)), obj3);
            try
            {
                j = ((Number)obj4).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "make-string", 1, obj4);
            }
            charsequence1 = strings.makeString(j);
            obj5 = s1;
            try
            {
                charsequence2 = (CharSequence)obj5;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "%string-copy!", 2, obj5);
            }
            obj6 = start1;
            try
            {
                k = ((Number)obj6).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "%string-copy!", 4, obj6);
            }
            srfi13.$PcStringCopy$Ex(charsequence1, 0, charsequence2, 0, k);
            obj7 = start1;
            try
            {
                l = ((Number)obj7).intValue();
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "%string-copy!", 1, obj7);
            }
            obj8 = s2;
            try
            {
                charsequence3 = (CharSequence)obj8;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "%string-copy!", 2, obj8);
            }
            try
            {
                i1 = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "%string-copy!", 3, obj);
            }
            try
            {
                j1 = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "%string-copy!", 4, obj1);
            }
            srfi13.$PcStringCopy$Ex(charsequence1, l, charsequence3, i1, j1);
            obj9 = AddOp.$Pl.apply2(start1, obj3);
            try
            {
                k1 = ((Number)obj9).intValue();
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "%string-copy!", 1, obj9);
            }
            obj10 = s1;
            try
            {
                charsequence4 = (CharSequence)obj10;
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "%string-copy!", 2, obj10);
            }
            obj11 = end1;
            try
            {
                l1 = ((Number)obj11).intValue();
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "%string-copy!", 3, obj11);
            }
            srfi13.$PcStringCopy$Ex(charsequence1, k1, charsequence4, l1, i);
            return charsequence1;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 181)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 182)
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

        public frame92()
        {
        }
    }

    public class frame93 extends ModuleBody
    {

        final ModuleMethod lambda$Fn208 = new ModuleMethod(this, 183, null, 0);
        final ModuleMethod lambda$Fn209 = new ModuleMethod(this, 184, null, 8194);
        Object s;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 183)
            {
                return lambda208();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 184)
            {
                return lambda209(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        Object lambda208()
        {
            ModuleMethod modulemethod = srfi13.string$Mntokenize;
            Object obj = s;
            Location location = srfi13.loc$rest;
            Object obj1;
            try
            {
                obj1 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1696, 57);
                throw unboundlocationexception;
            }
            return srfi13.stringParseFinalStart$PlEnd(modulemethod, obj, obj1);
        }

        Object lambda209(Object obj, Object obj1)
        {
            Object obj2;
            Object obj3;
            obj2 = LList.Empty;
            obj3 = obj1;
_L2:
            Object obj6;
            Object obj4 = Scheme.numLss.apply2(obj, obj3);
            boolean flag;
            Object obj10;
            try
            {
                flag = ((Boolean)obj4).booleanValue();
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "x", -2, obj4);
            }
            if (flag)
            {
                Object obj13 = s;
                Location location1 = srfi13.loc$token$Mnchars;
                Object obj5;
                Object obj7;
                Location location;
                Object obj8;
                Object obj9;
                CharSequence charsequence;
                int i;
                int j;
                CharSequence charsequence1;
                int k;
                int l;
                Object obj14;
                try
                {
                    obj14 = location1.get();
                }
                catch (UnboundLocationException unboundlocationexception1)
                {
                    unboundlocationexception1.setLine("srfi13.scm", 1698, 48);
                    throw unboundlocationexception1;
                }
                obj5 = srfi13.stringIndexRight$V(obj13, obj14, new Object[] {
                    obj, obj3
                });
            } else
            if (flag)
            {
                obj5 = Boolean.TRUE;
            } else
            {
                obj5 = Boolean.FALSE;
            }
            if (obj5 == Boolean.FALSE)
            {
                break MISSING_BLOCK_LABEL_276;
            }
            obj6 = AddOp.$Pl.apply2(srfi13.Lit1, obj5);
            obj7 = s;
            location = srfi13.loc$token$Mnchars;
            try
            {
                obj8 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1701, 34);
                throw unboundlocationexception;
            }
            obj9 = srfi13.stringSkipRight$V(obj7, obj8, new Object[] {
                obj, obj5
            });
            if (obj9 != Boolean.FALSE)
            {
                Object obj11 = s;
                Object obj12;
                try
                {
                    charsequence1 = (CharSequence)obj11;
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "substring", 1, obj11);
                }
                obj12 = AddOp.$Pl.apply2(srfi13.Lit1, obj9);
                try
                {
                    k = ((Number)obj12).intValue();
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "substring", 2, obj12);
                }
                try
                {
                    l = ((Number)obj6).intValue();
                }
                catch (ClassCastException classcastexception6)
                {
                    throw new WrongType(classcastexception6, "substring", 3, obj6);
                }
                obj2 = lists.cons(strings.substring(charsequence1, k, l), obj2);
                obj3 = obj9;
                continue; /* Loop/switch isn't completed */
            }
            obj10 = s;
            try
            {
                charsequence = (CharSequence)obj10;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "substring", 1, obj10);
            }
            try
            {
                i = ((Number)obj).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "substring", 2, obj);
            }
            j = ((Number)obj6).intValue();
            obj2 = lists.cons(strings.substring(charsequence, i, j), obj2);
            return obj2;
            ClassCastException classcastexception3;
            classcastexception3;
            throw new WrongType(classcastexception3, "substring", 3, obj6);
            if (true) goto _L2; else goto _L1
_L1:
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 183)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            if (modulemethod.selector == 184)
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

        public frame93()
        {
        }
    }

    public class frame94 extends ModuleBody
    {

        Object from;
        final ModuleMethod lambda$Fn211 = new ModuleMethod(this, 188, null, 0);
        final ModuleMethod lambda$Fn212 = new ModuleMethod(this, 185, null, 0);
        final ModuleMethod lambda$Fn213 = new ModuleMethod(this, 187, null, 8194);
        final ModuleMethod lambda$Fn214;
        final ModuleMethod lambda$Fn215;
        LList maybe$Mnto$Plstart$Plend;
        Object s;

        static boolean lambda210(Object obj)
        {
            boolean flag = numbers.isInteger(obj);
            if (flag)
            {
                flag = numbers.isExact(obj);
            }
            return flag;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            switch (modulemethod.selector)
            {
            case 186: 
            case 187: 
            default:
                return super.apply0(modulemethod);

            case 185: 
                return lambda212();

            case 188: 
                return lambda211();
            }
        }

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 186)
            {
                if (lambda214(obj))
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 187)
            {
                return lambda213(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 189)
            {
                return lambda215(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda211()
        {
            if (lists.isPair(maybe$Mnto$Plstart$Plend))
            {
                return call_with_values.callWithValues(lambda$Fn212, lambda$Fn213);
            }
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Location location = srfi13.loc$check$Mnarg;
            Object obj;
            Object obj1;
            CharSequence charsequence;
            int i;
            Object aobj[];
            try
            {
                obj = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1749, 36);
                throw unboundlocationexception;
            }
            obj1 = applytoargs.apply4(obj, strings.string$Qu, s, srfi13.xsubstring);
            try
            {
                charsequence = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-length", 1, obj1);
            }
            i = strings.stringLength(charsequence);
            aobj = new Object[3];
            aobj[0] = AddOp.$Pl.apply2(from, Integer.valueOf(i));
            aobj[1] = srfi13.Lit0;
            aobj[2] = Integer.valueOf(i);
            return misc.values(aobj);
        }

        Object lambda212()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.xsubstring, s, lists.cdr.apply1(maybe$Mnto$Plstart$Plend));
        }

        Object lambda213(Object obj, Object obj1)
        {
            Object obj2 = lists.car.apply1(maybe$Mnto$Plstart$Plend);
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Location location = srfi13.loc$check$Mnarg;
            Object obj3;
            try
            {
                obj3 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1744, 6);
                throw unboundlocationexception;
            }
            applytoargs.apply4(obj3, lambda$Fn214, obj2, srfi13.xsubstring);
            return misc.values(new Object[] {
                obj2, obj, obj1
            });
        }

        boolean lambda214(Object obj)
        {
            boolean flag = numbers.isInteger(obj);
            if (flag)
            {
                flag = numbers.isExact(obj);
                if (flag)
                {
                    flag = ((Boolean)Scheme.numLEq.apply2(from, obj)).booleanValue();
                }
            }
            return flag;
        }

        Object lambda215(Object obj, Object obj1, Object obj2)
        {
            Object obj3 = AddOp.$Mn.apply2(obj2, obj1);
            Object obj4 = AddOp.$Mn.apply2(obj, from);
            Number number;
            Number number1;
            Object obj5;
            Object obj6;
            try
            {
                number = (Number)obj4;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "zero?", 1, obj4);
            }
            if (numbers.isZero(number))
            {
                return "";
            }
            try
            {
                number1 = (Number)obj3;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "zero?", 1, obj3);
            }
            if (numbers.isZero(number1))
            {
                Object aobj[] = new Object[6];
                aobj[0] = srfi13.xsubstring;
                aobj[1] = s;
                aobj[2] = from;
                aobj[3] = obj;
                aobj[4] = obj1;
                aobj[5] = obj2;
                return misc.error$V("Cannot replicate empty (sub)string", aobj);
            }
            if (Scheme.numEqu.apply2(srfi13.Lit1, obj3) != Boolean.FALSE)
            {
                gnu.math.RealNum realnum;
                double d;
                gnu.math.RealNum realnum1;
                int i;
                CharSequence charsequence;
                CharSequence charsequence1;
                int j;
                int k;
                int l;
                Object obj10;
                CharSequence charsequence2;
                int i1;
                try
                {
                    l = ((Number)obj4).intValue();
                }
                catch (ClassCastException classcastexception8)
                {
                    throw new WrongType(classcastexception8, "make-string", 1, obj4);
                }
                obj10 = s;
                try
                {
                    charsequence2 = (CharSequence)obj10;
                }
                catch (ClassCastException classcastexception9)
                {
                    throw new WrongType(classcastexception9, "string-ref", 1, obj10);
                }
                try
                {
                    i1 = ((Number)obj1).intValue();
                }
                catch (ClassCastException classcastexception10)
                {
                    throw new WrongType(classcastexception10, "string-ref", 2, obj1);
                }
                return strings.makeString(l, Char.make(strings.stringRef(charsequence2, i1)));
            }
            obj5 = DivideOp.$Sl.apply2(from, obj3);
            try
            {
                realnum = LangObjType.coerceRealNum(obj5);
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "floor", 1, obj5);
            }
            d = numbers.floor(realnum).doubleValue();
            obj6 = DivideOp.$Sl.apply2(obj, obj3);
            try
            {
                realnum1 = LangObjType.coerceRealNum(obj6);
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "floor", 1, obj6);
            }
            if (d == numbers.floor(realnum1).doubleValue())
            {
                Object obj7 = s;
                Object obj8;
                Object obj9;
                try
                {
                    charsequence1 = (CharSequence)obj7;
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "substring", 1, obj7);
                }
                obj8 = AddOp.$Pl.apply2(obj1, DivideOp.modulo.apply2(from, obj3));
                try
                {
                    j = ((Number)obj8).intValue();
                }
                catch (ClassCastException classcastexception6)
                {
                    throw new WrongType(classcastexception6, "substring", 2, obj8);
                }
                obj9 = AddOp.$Pl.apply2(obj1, DivideOp.modulo.apply2(obj, obj3));
                try
                {
                    k = ((Number)obj9).intValue();
                }
                catch (ClassCastException classcastexception7)
                {
                    throw new WrongType(classcastexception7, "substring", 3, obj9);
                }
                return strings.substring(charsequence1, j, k);
            }
            try
            {
                i = ((Number)obj4).intValue();
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "make-string", 1, obj4);
            }
            charsequence = strings.makeString(i);
            srfi13.$PcMultispanRepcopy$Ex(charsequence, srfi13.Lit0, s, from, obj, obj1, obj2);
            return charsequence;
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            case 186: 
            case 187: 
            default:
                return super.match0(modulemethod, callcontext);

            case 188: 
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;

            case 185: 
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            }
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 186)
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
            if (modulemethod.selector == 187)
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
            if (modulemethod.selector == 189)
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

        public frame94()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 186, null, 4097);
            modulemethod.setProperty("source-location", "srfi13.scm:1744");
            lambda$Fn214 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 189, null, 12291);
            modulemethod1.setProperty("source-location", "srfi13.scm:1740");
            lambda$Fn215 = modulemethod1;
        }
    }

    public class frame95 extends ModuleBody
    {

        final ModuleMethod lambda$Fn217 = new ModuleMethod(this, 192, null, 0);
        final ModuleMethod lambda$Fn218 = new ModuleMethod(this, 190, null, 0);
        final ModuleMethod lambda$Fn219 = new ModuleMethod(this, 191, null, 8194);
        final ModuleMethod lambda$Fn221;
        LList maybe$Mnsto$Plstart$Plend;
        Object s;
        Object sfrom;
        Object target;
        Object tstart;

        static boolean lambda216(Object obj)
        {
            boolean flag = numbers.isInteger(obj);
            if (flag)
            {
                flag = numbers.isExact(obj);
            }
            return flag;
        }

        static boolean lambda220(Object obj)
        {
            boolean flag = numbers.isInteger(obj);
            if (flag)
            {
                flag = numbers.isExact(obj);
            }
            return flag;
        }

        public Object apply0(ModuleMethod modulemethod)
        {
            switch (modulemethod.selector)
            {
            case 191: 
            default:
                return super.apply0(modulemethod);

            case 190: 
                return lambda218();

            case 192: 
                return lambda217();
            }
        }

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            if (modulemethod.selector == 191)
            {
                return lambda219(obj, obj1);
            } else
            {
                return super.apply2(modulemethod, obj, obj1);
            }
        }

        public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
        {
            if (modulemethod.selector == 193)
            {
                return lambda221(obj, obj1, obj2);
            } else
            {
                return super.apply3(modulemethod, obj, obj1, obj2);
            }
        }

        Object lambda217()
        {
            if (lists.isPair(maybe$Mnsto$Plstart$Plend))
            {
                return call_with_values.callWithValues(lambda$Fn218, lambda$Fn219);
            }
            Object obj = s;
            CharSequence charsequence;
            int i;
            Object aobj[];
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "string-length", 1, obj);
            }
            i = strings.stringLength(charsequence);
            aobj = new Object[3];
            aobj[0] = AddOp.$Pl.apply2(sfrom, Integer.valueOf(i));
            aobj[1] = srfi13.Lit0;
            aobj[2] = Integer.valueOf(i);
            return misc.values(aobj);
        }

        Object lambda218()
        {
            return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnxcopy$Ex, s, lists.cdr.apply1(maybe$Mnsto$Plstart$Plend));
        }

        Object lambda219(Object obj, Object obj1)
        {
            Object obj2 = lists.car.apply1(maybe$Mnsto$Plstart$Plend);
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Location location = srfi13.loc$check$Mnarg;
            Object obj3;
            try
            {
                obj3 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1785, 6);
                throw unboundlocationexception;
            }
            applytoargs.apply4(obj3, srfi13.lambda$Fn220, obj2, srfi13.string$Mnxcopy$Ex);
            return misc.values(new Object[] {
                obj2, obj, obj1
            });
        }

        Object lambda221(Object obj, Object obj1, Object obj2)
        {
            Object obj3 = AddOp.$Mn.apply2(obj, sfrom);
            Object obj4 = AddOp.$Pl.apply2(tstart, obj3);
            Object obj5 = AddOp.$Mn.apply2(obj2, obj1);
            srfi13.checkSubstringSpec(srfi13.string$Mnxcopy$Ex, target, tstart, obj4);
            Number number;
            boolean flag;
            Number number1;
            Object obj6;
            Object obj7;
            try
            {
                number = (Number)obj3;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "zero?", 1, obj3);
            }
            flag = numbers.isZero(number);
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
            try
            {
                number1 = (Number)obj5;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "zero?", 1, obj5);
            }
            if (numbers.isZero(number1))
            {
                Object aobj1[] = new Object[8];
                aobj1[0] = srfi13.string$Mnxcopy$Ex;
                aobj1[1] = target;
                aobj1[2] = tstart;
                aobj1[3] = s;
                aobj1[4] = sfrom;
                aobj1[5] = obj;
                aobj1[6] = obj1;
                aobj1[7] = obj2;
                return misc.error$V("Cannot replicate empty (sub)string", aobj1);
            }
            if (Scheme.numEqu.apply2(srfi13.Lit1, obj5) != Boolean.FALSE)
            {
                Object obj13 = target;
                Object obj14 = s;
                gnu.math.RealNum realnum;
                double d;
                gnu.math.RealNum realnum1;
                CharSequence charsequence;
                int i;
                CharSequence charsequence1;
                int j;
                int k;
                CharSequence charsequence2;
                int l;
                Char char1;
                Object aobj[];
                try
                {
                    charsequence2 = (CharSequence)obj14;
                }
                catch (ClassCastException classcastexception9)
                {
                    throw new WrongType(classcastexception9, "string-ref", 1, obj14);
                }
                try
                {
                    l = ((Number)obj1).intValue();
                }
                catch (ClassCastException classcastexception10)
                {
                    throw new WrongType(classcastexception10, "string-ref", 2, obj1);
                }
                char1 = Char.make(strings.stringRef(charsequence2, l));
                aobj = new Object[2];
                aobj[0] = tstart;
                aobj[1] = obj4;
                return srfi13.stringFill$Ex$V(obj13, char1, aobj);
            }
            obj6 = DivideOp.$Sl.apply2(sfrom, obj5);
            try
            {
                realnum = LangObjType.coerceRealNum(obj6);
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "floor", 1, obj6);
            }
            d = numbers.floor(realnum).doubleValue();
            obj7 = DivideOp.$Sl.apply2(obj, obj5);
            try
            {
                realnum1 = LangObjType.coerceRealNum(obj7);
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "floor", 1, obj7);
            }
            if (d == numbers.floor(realnum1).doubleValue())
            {
                Object obj8 = target;
                Object obj9;
                Object obj10;
                Object obj11;
                Object obj12;
                try
                {
                    charsequence = (CharSequence)obj8;
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "%string-copy!", 0, obj8);
                }
                obj9 = tstart;
                try
                {
                    i = ((Number)obj9).intValue();
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "%string-copy!", 1, obj9);
                }
                obj10 = s;
                try
                {
                    charsequence1 = (CharSequence)obj10;
                }
                catch (ClassCastException classcastexception6)
                {
                    throw new WrongType(classcastexception6, "%string-copy!", 2, obj10);
                }
                obj11 = AddOp.$Pl.apply2(obj1, DivideOp.modulo.apply2(sfrom, obj5));
                try
                {
                    j = ((Number)obj11).intValue();
                }
                catch (ClassCastException classcastexception7)
                {
                    throw new WrongType(classcastexception7, "%string-copy!", 3, obj11);
                }
                obj12 = AddOp.$Pl.apply2(obj1, DivideOp.modulo.apply2(obj, obj5));
                try
                {
                    k = ((Number)obj12).intValue();
               