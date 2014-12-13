// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.IsEqual;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import gnu.text.Char;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.numbers;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.append;

// Referenced classes of package gnu.kawa.slib:
//            printf

public class lambda.Fn16 extends ModuleBody
{

    Object alternate$Mnform;
    Object args;
    Object blank;
    final ModuleMethod lambda$Fn13;
    final ModuleMethod lambda$Fn14;
    final ModuleMethod lambda$Fn15;
    final ModuleMethod lambda$Fn16;
    Object leading$Mn0s;
    Object left$Mnadjust;
    Object os;
    Procedure pad;
    Object pr;
    Object precision;
    Object signed;
    s staticLink;
    Object width;

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply1(modulemethod, obj);

        case 16: // '\020'
            return lambda25(obj);

        case 17: // '\021'
            if (lambda26(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 18: // '\022'
            return lambda27(obj);

        case 19: // '\023'
            break;
        }
        if (lambda28(obj))
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public Object applyN(ModuleMethod modulemethod, Object aobj[])
    {
        if (modulemethod.selector == 15)
        {
            Object obj = aobj[0];
            int i = -1 + aobj.length;
            Object aobj1[] = new Object[i];
            do
            {
                if (--i < 0)
                {
                    return lambda23pad$V(obj, aobj1);
                }
                aobj1[i] = aobj[i + 1];
            } while (true);
        } else
        {
            return super.applyN(modulemethod, aobj);
        }
    }

    public Object lambda22readFormatNumber()
    {
        if (Scheme.isEqv.apply2(printf.Lit66, staticLink.c) != Boolean.FALSE)
        {
            staticLink.ambda18mustAdvance();
            Object obj5 = lists.car.apply1(args);
            args = lists.cdr.apply1(args);
            return obj5;
        }
        Object obj = staticLink.c;
        Object obj1 = printf.Lit1;
        do
        {
            Object obj2 = staticLink.c;
            Char char1;
            try
            {
                char1 = (Char)obj2;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "char-numeric?", 1, obj2);
            }
            if (unicode.isCharNumeric(char1))
            {
                staticLink.ambda18mustAdvance();
                Object obj3 = staticLink.c;
                AddOp addop = AddOp.$Pl;
                Object obj4 = MultiplyOp.$St.apply2(obj1, printf.Lit45);
                Object aobj[];
                if (obj instanceof Object[])
                {
                    aobj = (Object[])obj;
                } else
                {
                    aobj = (new Object[] {
                        obj
                    });
                }
                obj1 = addop.apply2(obj4, numbers.string$To$Number(strings.$make$string$(aobj)));
                obj = obj3;
            } else
            {
                return obj1;
            }
        } while (true);
    }

    public Object lambda23pad$V(Object obj, Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        CharSequence charsequence;
        Object obj1;
        Object obj2;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj);
        }
        obj1 = Integer.valueOf(strings.stringLength(charsequence));
        obj2 = llist;
        do
        {
            if (Scheme.numGEq.apply2(obj1, width) != Boolean.FALSE)
            {
                return lists.cons(obj, llist);
            }
            Object obj3;
            if (lists.isNull(obj2))
            {
                Object obj4;
                if (left$Mnadjust != Boolean.FALSE)
                {
                    Object aobj1[] = new Object[2];
                    aobj1[0] = llist;
                    Object obj6 = AddOp.$Mn.apply2(width, obj1);
                    AddOp addop;
                    CharSequence charsequence1;
                    int i;
                    int j;
                    int k;
                    try
                    {
                        k = ((Number)obj6).intValue();
                    }
                    catch (ClassCastException classcastexception4)
                    {
                        throw new WrongType(classcastexception4, "make-string", 1, obj6);
                    }
                    aobj1[1] = LList.list1(strings.makeString(k, printf.Lit29));
                    return lists.cons(obj, append.append$V(aobj1));
                }
                if (leading$Mn0s != Boolean.FALSE)
                {
                    Object obj5 = AddOp.$Mn.apply2(width, obj1);
                    try
                    {
                        j = ((Number)obj5).intValue();
                    }
                    catch (ClassCastException classcastexception3)
                    {
                        throw new WrongType(classcastexception3, "make-string", 1, obj5);
                    }
                    return lists.cons(obj, lists.cons(strings.makeString(j, printf.Lit9), llist));
                }
                obj4 = AddOp.$Mn.apply2(width, obj1);
                try
                {
                    i = ((Number)obj4).intValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "make-string", 1, obj4);
                }
                return lists.cons(strings.makeString(i, printf.Lit29), lists.cons(obj, llist));
            }
            addop = AddOp.$Pl;
            obj3 = lists.car.apply1(obj2);
            try
            {
                charsequence1 = (CharSequence)obj3;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-length", 1, obj3);
            }
            obj1 = addop.apply2(obj1, Integer.valueOf(strings.stringLength(charsequence1)));
            obj2 = lists.cdr.apply1(obj2);
        } while (true);
    }

    public Object lambda24integerConvert(Object obj, Object obj1, Object obj2)
    {
        Object obj3 = precision;
        gnu.math.RealNum realnum;
        Object obj4;
        Object obj5;
        Object obj8;
        try
        {
            realnum = LangObjType.coerceRealNum(obj3);
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "negative?", 1, obj3);
        }
        ClassCastException classcastexception2;
        ClassCastException classcastexception3;
        ClassCastException classcastexception4;
        ClassCastException classcastexception5;
        ClassCastException classcastexception6;
        ClassCastException classcastexception7;
        if (!numbers.isNegative(realnum))
        {
            leading$Mn0s = Boolean.FALSE;
            Object obj9 = precision;
            Boolean boolean1;
            int i;
            int j;
            gnu.kawa.functions.IsEqv iseqv;
            Char char1;
            CharSequence charsequence;
            String s;
            Object aobj[];
            gnu.kawa.functions.NumberCompare numbercompare;
            CharSequence charsequence1;
            Object obj6;
            AddOp addop;
            Object obj7;
            CharSequence charsequence2;
            int k;
            String s1;
            CharSequence charsequence3;
            CharSequence charsequence4;
            Number number;
            int l;
            Symbol symbol;
            Number number1;
            boolean flag;
            try
            {
                number1 = (Number)obj9;
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "zero?", 1, obj9);
            }
            flag = numbers.isZero(number1);
            if (flag ? Scheme.isEqv.apply2(printf.Lit1, obj) != Boolean.FALSE : flag)
            {
                obj = "";
            }
            obj4 = obj;
        } else
        {
            obj4 = obj;
        }
        if (misc.isSymbol(obj4))
        {
            try
            {
                symbol = (Symbol)obj4;
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "symbol->string", 1, obj4);
            }
            obj5 = misc.symbol$To$String(symbol);
        } else
        if (numbers.isNumber(obj4))
        {
            try
            {
                number = (Number)obj4;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "number->string", 1, obj4);
            }
            try
            {
                l = ((Number)obj1).intValue();
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "number->string", 2, obj1);
            }
            obj5 = numbers.number$To$String(number, l);
        } else
        {
            try
            {
                boolean1 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "x", -2, obj4);
            }
            if (obj4 != boolean1)
            {
                i = 1;
            } else
            {
                i = 0;
            }
            j = 1 & i + 1;
            if (j == 0 ? lists.isNull(obj4) : j != 0)
            {
                obj5 = "0";
            } else
            if (strings.isString(obj4))
            {
                obj5 = obj4;
            } else
            {
                obj5 = "1";
            }
        }
        if (obj2 != Boolean.FALSE)
        {
            obj5 = Scheme.applyToArgs.apply2(obj2, obj5);
        }
        if (!IsEqual.apply("", obj5)) goto _L2; else goto _L1
_L1:
        s = "";
_L3:
        aobj = new Object[2];
        numbercompare = Scheme.numLss;
        try
        {
            charsequence1 = (CharSequence)obj5;
        }
        // Misplaced declaration of an exception variable
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "string-length", 1, obj5);
        }
        if (numbercompare.apply2(Integer.valueOf(strings.stringLength(charsequence1)), precision) != Boolean.FALSE)
        {
            addop = AddOp.$Mn;
            obj7 = precision;
            try
            {
                charsequence2 = (CharSequence)obj5;
            }
            // Misplaced declaration of an exception variable
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "string-length", 1, obj5);
            }
            obj8 = addop.apply2(obj7, Integer.valueOf(strings.stringLength(charsequence2)));
            try
            {
                k = ((Number)obj8).intValue();
            }
            // Misplaced declaration of an exception variable
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "make-string", 1, obj8);
            }
            obj6 = strings.makeString(k, printf.Lit9);
        } else
        {
            obj6 = "";
        }
        aobj[0] = obj6;
        aobj[1] = obj5;
        return lambda23pad$V(s, aobj);
_L2:
        iseqv = Scheme.isEqv;
        char1 = printf.Lit5;
        try
        {
            charsequence = (CharSequence)obj5;
        }
        // Misplaced declaration of an exception variable
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "string-ref", 1, obj5);
        }
        if (iseqv.apply2(char1, Char.make(strings.stringRef(charsequence, 0))) == Boolean.FALSE)
        {
            break MISSING_BLOCK_LABEL_445;
        }
        try
        {
            charsequence3 = (CharSequence)obj5;
        }
        // Misplaced declaration of an exception variable
        catch (ClassCastException classcastexception6)
        {
            throw new WrongType(classcastexception6, "substring", 1, obj5);
        }
        charsequence4 = (CharSequence)obj5;
        obj5 = strings.substring(charsequence3, 1, strings.stringLength(charsequence4));
        s = "-";
          goto _L3
        if (signed != Boolean.FALSE)
        {
            s = "+";
        } else
        if (blank != Boolean.FALSE)
        {
            s = " ";
        } else
        if (alternate$Mnform != Boolean.FALSE)
        {
            if (Scheme.isEqv.apply2(obj1, printf.Lit48) != Boolean.FALSE)
            {
                s1 = "0";
            } else
            if (Scheme.isEqv.apply2(obj1, printf.Lit50) != Boolean.FALSE)
            {
                s1 = "0x";
            } else
            {
                s1 = "";
            }
            s = s1;
        } else
        {
            s = "";
        }
          goto _L3
        classcastexception7;
        throw new WrongType(classcastexception7, "string-length", 1, obj5);
        if (true) goto _L5; else goto _L4
_L5:
        break MISSING_BLOCK_LABEL_98;
_L4:
    }

    Object lambda25(Object obj)
    {
        AddOp addop = AddOp.$Pl;
        Object obj1 = pr;
        CharSequence charsequence;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj);
        }
        pr = addop.apply2(obj1, Integer.valueOf(strings.stringLength(charsequence)));
        return Scheme.applyToArgs.apply2(staticLink.ut, obj);
    }

    boolean lambda26(Object obj)
    {
        Special _tmp = Special.undefined;
        AddOp addop = AddOp.$Mn;
        Object obj1 = pr;
        CharSequence charsequence;
        Object obj2;
        gnu.math.RealNum realnum;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj);
        }
        obj2 = addop.apply2(obj1, Integer.valueOf(strings.stringLength(charsequence)));
        try
        {
            realnum = LangObjType.coerceRealNum(obj2);
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "negative?", 1, obj2);
        }
        if (numbers.isNegative(realnum))
        {
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Object obj4 = staticLink.ut;
            Object obj3;
            gnu.math.RealNum realnum1;
            CharSequence charsequence1;
            Object obj5;
            int i;
            try
            {
                charsequence1 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "substring", 1, obj);
            }
            obj5 = pr;
            try
            {
                i = ((Number)obj5).intValue();
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "substring", 3, obj5);
            }
            applytoargs.apply2(obj4, strings.substring(charsequence1, 0, i));
            obj3 = printf.Lit1;
        } else
        {
            Scheme.applyToArgs.apply2(staticLink.ut, obj);
            obj3 = obj2;
        }
        pr = obj3;
        try
        {
            realnum1 = LangObjType.coerceRealNum(obj2);
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "positive?", 1, obj2);
        }
        return numbers.isPositive(realnum1);
    }

    Boolean lambda27(Object obj)
    {
        AddOp addop = AddOp.$Mn;
        Object obj1 = pr;
        CharSequence charsequence;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj);
        }
        pr = addop.apply2(obj1, Integer.valueOf(strings.stringLength(charsequence)));
        if (os == Boolean.FALSE)
        {
            Scheme.applyToArgs.apply2(staticLink.ut, obj);
        } else
        {
            Object obj2 = pr;
            gnu.math.RealNum realnum;
            try
            {
                realnum = LangObjType.coerceRealNum(obj2);
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "negative?", 1, obj2);
            }
            if (numbers.isNegative(realnum))
            {
                Scheme.applyToArgs.apply2(staticLink.ut, os);
                os = Boolean.FALSE;
                Scheme.applyToArgs.apply2(staticLink.ut, obj);
            } else
            {
                Object aobj[] = new Object[2];
                aobj[0] = os;
                aobj[1] = obj;
                os = strings.stringAppend(aobj);
            }
        }
        return Boolean.TRUE;
    }

    boolean lambda28(Object obj)
    {
        Special _tmp = Special.undefined;
        AddOp addop = AddOp.$Mn;
        Object obj1 = pr;
        CharSequence charsequence;
        Object obj2;
        gnu.math.RealNum realnum;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-length", 1, obj);
        }
        obj2 = addop.apply2(obj1, Integer.valueOf(strings.stringLength(charsequence)));
        try
        {
            realnum = LangObjType.coerceRealNum(obj2);
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "negative?", 1, obj2);
        }
        if (numbers.isNegative(realnum))
        {
            Object aobj1[] = new Object[2];
            aobj1[0] = os;
            Object aobj[];
            gnu.math.RealNum realnum1;
            CharSequence charsequence1;
            Object obj3;
            int i;
            try
            {
                charsequence1 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "substring", 1, obj);
            }
            obj3 = pr;
            try
            {
                i = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "substring", 3, obj3);
            }
            aobj1[1] = strings.substring(charsequence1, 0, i);
            os = strings.stringAppend(aobj1);
        } else
        {
            aobj = new Object[2];
            aobj[0] = os;
            aobj[1] = obj;
            os = strings.stringAppend(aobj);
        }
        pr = obj2;
        try
        {
            realnum1 = LangObjType.coerceRealNum(obj2);
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "positive?", 1, obj2);
        }
        return numbers.isPositive(realnum1);
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 19: // '\023'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 18: // '\022'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 17: // '\021'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 16: // '\020'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        }
    }

    public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
    {
        if (modulemethod.selector == 15)
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

    public ()
    {
        pad = new ModuleMethod(this, 15, printf.Lit67, -4095);
        ModuleMethod modulemethod = new ModuleMethod(this, 16, null, 4097);
        modulemethod.setProperty("source-location", "printf.scm:472");
        lambda$Fn13 = modulemethod;
        ModuleMethod modulemethod1 = new ModuleMethod(this, 17, null, 4097);
        modulemethod1.setProperty("source-location", "printf.scm:476");
        lambda$Fn14 = modulemethod1;
        ModuleMethod modulemethod2 = new ModuleMethod(this, 18, null, 4097);
        modulemethod2.setProperty("source-location", "printf.scm:484");
        lambda$Fn15 = modulemethod2;
        ModuleMethod modulemethod3 = new ModuleMethod(this, 19, null, 4097);
        modulemethod3.setProperty("source-location", "printf.scm:494");
        lambda$Fn16 = modulemethod3;
    }
}
