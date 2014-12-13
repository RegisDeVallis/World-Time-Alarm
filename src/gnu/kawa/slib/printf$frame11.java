// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.lists;
import kawa.lib.numbers;
import kawa.lib.rnrs.unicode;
import kawa.lib.strings;
import kawa.lib.vectors;
import kawa.standard.Scheme;
import kawa.standard.append;

// Referenced classes of package gnu.kawa.slib:
//            printf

public class lambda.Fn17 extends ModuleBody
{

    Object fc;
    Procedure format$Mnreal;
    final ModuleMethod lambda$Fn17;
    s staticLink;

    public Object applyN(ModuleMethod modulemethod, Object aobj[])
    {
        Object obj;
        Object obj1;
        Object obj2;
        int i;
        Object aobj1[];
        switch (modulemethod.selector)
        {
        default:
            return super.applyN(modulemethod, aobj);

        case 13: // '\r'
            Object obj3 = aobj[0];
            Object obj4 = aobj[1];
            Object obj5 = aobj[2];
            Object obj6 = aobj[3];
            int j = -4 + aobj.length;
            Object aobj2[] = new Object[j];
            do
            {
                if (--j < 0)
                {
                    return lambda30formatReal$V(obj3, obj4, obj5, obj6, aobj2);
                }
                aobj2[j] = aobj[j + 4];
            } while (true);

        case 14: // '\016'
            obj = aobj[0];
            obj1 = aobj[1];
            obj2 = aobj[2];
            i = -3 + aobj.length;
            aobj1 = new Object[i];
            break;
        }
        do
        {
            if (--i < 0)
            {
                return lambda31$V(obj, obj1, obj2, aobj1);
            }
            aobj1[i] = aobj[i + 3];
        } while (true);
    }

    public Object lambda29f(Object obj, Object obj1, Object obj2)
    {
        CharSequence charsequence;
        Object obj3;
        Object obj4;
        Object obj5;
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "stdio:round-string", 0, obj);
        }
        obj3 = AddOp.$Pl.apply2(obj1, staticLink.precision);
        if (obj2 != Boolean.FALSE)
        {
            obj4 = obj1;
        } else
        {
            obj4 = obj2;
        }
        obj5 = printf.stdio$ClRoundString(charsequence, obj3, obj4);
        if (Scheme.numGEq.apply2(obj1, printf.Lit1) != Boolean.FALSE)
        {
            Object obj7;
            Number number1;
            gnu.math.IntNum intnum;
            Object aobj1[];
            Object obj10;
            CharSequence charsequence2;
            int j;
            int k;
            CharSequence charsequence3;
            CharSequence charsequence4;
            int l;
            CharSequence charsequence5;
            CharSequence charsequence6;
            boolean flag1;
            Object obj11;
            try
            {
                number1 = (Number)obj1;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "zero?", 1, obj1);
            }
            if (numbers.isZero(number1))
            {
                intnum = printf.Lit1;
            } else
            {
                Char char1 = printf.Lit9;
                Number number;
                Object aobj[];
                int i;
                boolean flag;
                Object obj9;
                String s;
                CharSequence charsequence1;
                try
                {
                    charsequence1 = (CharSequence)obj5;
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "string-ref", 1, obj5);
                }
                if (characters.isChar$Eq(char1, Char.make(strings.stringRef(charsequence1, 0))))
                {
                    intnum = printf.Lit7;
                } else
                {
                    intnum = printf.Lit1;
                }
            }
            aobj1 = new Object[2];
            aobj1[0] = printf.Lit7;
            aobj1[1] = AddOp.$Pl.apply2(printf.Lit7, obj1);
            obj10 = numbers.max(aobj1);
            try
            {
                charsequence2 = (CharSequence)obj5;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "substring", 1, obj5);
            }
            try
            {
                j = intnum.intValue();
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "substring", 2, intnum);
            }
            try
            {
                k = ((Number)obj10).intValue();
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "substring", 3, obj10);
            }
            charsequence3 = strings.substring(charsequence2, j, k);
            try
            {
                charsequence4 = (CharSequence)obj5;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "substring", 1, obj5);
            }
            try
            {
                l = ((Number)obj10).intValue();
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "substring", 2, obj10);
            }
            try
            {
                charsequence5 = (CharSequence)obj5;
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "string-length", 1, obj5);
            }
            charsequence6 = strings.substring(charsequence4, l, strings.stringLength(charsequence5));
            flag1 = strings.isString$Eq(charsequence6, "");
            if (flag1 ? staticLink.Mnform == Boolean.FALSE : flag1)
            {
                obj11 = LList.Empty;
            } else
            {
                obj11 = LList.list2(".", charsequence6);
            }
            obj7 = lists.cons(charsequence3, obj11);
        } else
        {
            Object obj6 = staticLink.precision;
            try
            {
                number = (Number)obj6;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "zero?", 1, obj6);
            }
            if (numbers.isZero(number))
            {
                if (staticLink.Mnform != Boolean.FALSE)
                {
                    s = "0.";
                } else
                {
                    s = "0";
                }
                return LList.list1(s);
            }
            if (obj2 != Boolean.FALSE)
            {
                flag = strings.isString$Eq(obj5, "");
                if (flag)
                {
                    obj9 = LList.list1("0");
                } else
                if (flag)
                {
                    obj9 = Boolean.TRUE;
                } else
                {
                    obj9 = Boolean.FALSE;
                }
                obj7 = obj9;
            } else
            {
                obj7 = obj2;
            }
            if (obj7 == Boolean.FALSE)
            {
                aobj = new Object[2];
                aobj[0] = staticLink.precision;
                aobj[1] = AddOp.$Mn.apply2(printf.Lit17, obj1);
                Object obj8 = numbers.min(aobj);
                try
                {
                    i = ((Number)obj8).intValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "make-string", 1, obj8);
                }
                return LList.list3("0.", strings.makeString(i, printf.Lit9), obj5);
            }
        }
        return obj7;
    }

    public Object lambda30formatReal$V(Object obj, Object obj1, Object obj2, Object obj3, Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        if (!lists.isNull(llist)) goto _L2; else goto _L1
_L1:
        Object obj9;
        Object obj11;
        Object obj20;
        Char char1 = printf.Lit5;
        Object aobj1[];
        Object aobj2[];
        Char char2;
        String s;
        Object obj4;
        Object obj5;
        Object obj6;
        Object obj7;
        String s1;
        gnu.math.RealNum realnum;
        DivideOp divideop;
        AddOp addop;
        gnu.math.IntNum intnum;
        Object obj8;
        boolean flag;
        Object obj10;
        Boolean boolean1;
        int i;
        int j;
        gnu.kawa.functions.NumberCompare numbercompare;
        Object obj12;
        Object obj13;
        Object obj14;
        CharSequence charsequence;
        Object obj15;
        Char char3;
        CharSequence charsequence1;
        CharSequence charsequence2;
        int k;
        CharSequence charsequence3;
        CharSequence charsequence4;
        AddOp addop1;
        gnu.math.IntNum intnum2;
        CharSequence charsequence5;
        int l;
        gnu.lists.Pair pair;
        boolean flag1;
        String s2;
        Char char4;
        String s3;
        gnu.math.RealNum realnum1;
        String s4;
        gnu.lists.Pair pair1;
        gnu.kawa.functions.NumberCompare numbercompare1;
        gnu.math.IntNum intnum3;
        gnu.math.IntNum intnum4;
        String s5;
        gnu.lists.Pair pair2;
        Number number;
        Boolean boolean2;
        AddOp addop2;
        Object obj18;
        Object obj19;
        are are;
        Object aobj3[];
        Object aobj4[];
        gnu.lists.FVector fvector;
        int i1;
        DivideOp divideop1;
        AddOp addop3;
        gnu.math.IntNum intnum5;
        Boolean boolean3;
        try
        {
            char2 = (Char)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "char=?", 2, obj1);
        }
        if (characters.isChar$Eq(char1, char2))
        {
            s = "-";
        } else
        if (obj != Boolean.FALSE)
        {
            s = "+";
        } else
        if (staticLink.blank != Boolean.FALSE)
        {
            s = " ";
        } else
        {
            s = "";
        }
_L11:
        obj4 = Scheme.isEqv.apply2(fc, printf.Lit13);
        if (obj4 == Boolean.FALSE ? Scheme.isEqv.apply2(fc, printf.Lit54) != Boolean.FALSE : obj4 != Boolean.FALSE) goto _L4; else goto _L3
_L4:
        obj14 = Boolean.FALSE;
_L9:
        Object obj16;
        gnu.math.IntNum intnum1;
        Object obj17;
        try
        {
            charsequence = (CharSequence)obj2;
        }
        catch (ClassCastException classcastexception4)
        {
            throw new WrongType(classcastexception4, "stdio:round-string", 0, obj2);
        }
        obj15 = AddOp.$Pl.apply2(printf.Lit7, staticLink.precision);
        if (obj14 != Boolean.FALSE)
        {
            obj14 = printf.Lit1;
        }
        obj16 = printf.stdio$ClRoundString(charsequence, obj15, obj14);
        char3 = printf.Lit9;
        try
        {
            charsequence1 = (CharSequence)obj16;
        }
        catch (ClassCastException classcastexception5)
        {
            throw new WrongType(classcastexception5, "string-ref", 1, obj16);
        }
        if (characters.isChar$Eq(char3, Char.make(strings.stringRef(charsequence1, 0))))
        {
            intnum1 = printf.Lit7;
        } else
        {
            intnum1 = printf.Lit1;
        }
        try
        {
            charsequence2 = (CharSequence)obj16;
        }
        catch (ClassCastException classcastexception6)
        {
            throw new WrongType(classcastexception6, "substring", 1, obj16);
        }
        k = 1 + intnum1.intValue();
        try
        {
            charsequence3 = (CharSequence)obj16;
        }
        catch (ClassCastException classcastexception7)
        {
            throw new WrongType(classcastexception7, "string-length", 1, obj16);
        }
        charsequence4 = strings.substring(charsequence2, k, strings.stringLength(charsequence3));
        if (!numbers.isZero(intnum1))
        {
            addop1 = AddOp.$Mn;
            intnum2 = printf.Lit7;
            obj3 = addop1.apply2(obj3, intnum2);
        }
        try
        {
            charsequence5 = (CharSequence)obj16;
        }
        catch (ClassCastException classcastexception8)
        {
            throw new WrongType(classcastexception8, "substring", 1, obj16);
        }
        try
        {
            l = intnum1.intValue();
        }
        catch (ClassCastException classcastexception9)
        {
            throw new WrongType(classcastexception9, "substring", 2, intnum1);
        }
        pair = LList.list1(strings.substring(charsequence5, l, 1 + intnum1.intValue()));
        flag1 = strings.isString$Eq(charsequence4, "");
        if (flag1 ? staticLink.Mnform == Boolean.FALSE : flag1)
        {
            s2 = "";
        } else
        {
            s2 = ".";
        }
        obj17 = fc;
        try
        {
            char4 = (Char)obj17;
        }
        catch (ClassCastException classcastexception10)
        {
            throw new WrongType(classcastexception10, "char-upper-case?", 1, obj17);
        }
        if (unicode.isCharUpperCase(char4))
        {
            s3 = "E";
        } else
        {
            s3 = "e";
        }
        try
        {
            realnum1 = LangObjType.coerceRealNum(obj3);
        }
        catch (ClassCastException classcastexception11)
        {
            throw new WrongType(classcastexception11, "negative?", 1, obj3);
        }
        if (numbers.isNegative(realnum1))
        {
            s4 = "-";
        } else
        {
            s4 = "+";
        }
        pair1 = LList.chain4(pair, s2, charsequence4, s3, s4);
        numbercompare1 = Scheme.numLss;
        intnum3 = printf.Lit60;
        intnum4 = printf.Lit45;
        if (numbercompare1.apply3(intnum3, obj3, intnum4) != Boolean.FALSE)
        {
            s5 = "0";
        } else
        {
            s5 = "";
        }
        pair2 = LList.chain1(pair1, s5);
        try
        {
            number = (Number)obj3;
        }
        catch (ClassCastException classcastexception12)
        {
            throw new WrongType(classcastexception12, "abs", 1, obj3);
        }
        LList.chain1(pair2, numbers.number$To$String(numbers.abs(number)));
        obj7 = pair;
_L5:
        return lists.cons(s, obj7);
_L3:
        obj5 = Scheme.isEqv.apply2(fc, printf.Lit25);
        if (obj5 == Boolean.FALSE ? Scheme.isEqv.apply2(fc, printf.Lit26) != Boolean.FALSE : obj5 != Boolean.FALSE)
        {
            boolean3 = Boolean.FALSE;
            obj7 = lambda29f(obj2, obj3, boolean3);
        } else
        {
            break MISSING_BLOCK_LABEL_603;
        }
          goto _L5
        obj6 = Scheme.isEqv.apply2(fc, printf.Lit55);
        if (obj6 == Boolean.FALSE ? Scheme.isEqv.apply2(fc, printf.Lit56) != Boolean.FALSE : obj6 != Boolean.FALSE) goto _L7; else goto _L6
_L7:
        obj11 = staticLink.Mnform;
        try
        {
            boolean1 = Boolean.FALSE;
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "strip-0s", -2, obj11);
        }
        if (obj11 != boolean1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        j = 1 & i + 1;
        staticLink.Mnform = Boolean.FALSE;
        numbercompare = Scheme.numLEq;
        obj12 = AddOp.$Mn.apply2(printf.Lit7, staticLink.precision);
        obj13 = staticLink.precision;
        if (numbercompare.apply3(obj12, obj3, obj13) == Boolean.FALSE)
        {
            break MISSING_BLOCK_LABEL_1085;
        }
        staticLink.precision = AddOp.$Mn.apply2(staticLink.precision, obj3);
        if (j != 0)
        {
            boolean2 = Boolean.TRUE;
        } else
        {
            boolean2 = Boolean.FALSE;
        }
        obj7 = lambda29f(obj2, obj3, boolean2);
          goto _L5
_L6:
        if (Scheme.isEqv.apply2(fc, printf.Lit57) != Boolean.FALSE)
        {
            s1 = "";
        } else
        {
label0:
            {
                if (Scheme.isEqv.apply2(fc, printf.Lit58) == Boolean.FALSE)
                {
                    break label0;
                }
                s1 = " ";
            }
        }
        try
        {
            realnum = LangObjType.coerceRealNum(obj3);
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "negative?", 1, obj3);
        }
        if (numbers.isNegative(realnum))
        {
            divideop1 = DivideOp.quotient;
            addop3 = AddOp.$Mn;
            intnum5 = printf.Lit61;
            obj8 = divideop1.apply2(addop3.apply2(obj3, intnum5), printf.Lit61);
        } else
        {
            divideop = DivideOp.quotient;
            addop = AddOp.$Mn;
            intnum = printf.Lit7;
            obj8 = divideop.apply2(addop.apply2(obj3, intnum), printf.Lit61);
        }
        obj9 = Scheme.numLss.apply3(printf.Lit17, AddOp.$Pl.apply2(obj8, printf.Lit48), Integer.valueOf(vectors.vectorLength(printf.Lit62)));
        try
        {
            flag = ((Boolean)obj9).booleanValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "x", -2, obj9);
        }
        if (flag)
        {
            obj10 = obj8;
        } else
        if (flag)
        {
            obj10 = Boolean.TRUE;
        } else
        {
            obj10 = Boolean.FALSE;
        }
        if (obj10 == Boolean.FALSE) goto _L7; else goto _L8
_L8:
        addop2 = AddOp.$Mn;
        obj18 = MultiplyOp.$St.apply2(printf.Lit61, obj10);
        obj19 = addop2.apply2(obj3, obj18);
        are = staticLink;
        aobj3 = new Object[2];
        aobj3[0] = printf.Lit1;
        aobj3[1] = AddOp.$Mn.apply2(staticLink.precision, obj19);
        are.precision = numbers.max(aobj3);
        aobj4 = new Object[2];
        aobj4[0] = lambda29f(obj2, obj19, Boolean.FALSE);
        fvector = printf.Lit62;
        obj20 = AddOp.$Pl.apply2(obj10, printf.Lit48);
        i1 = ((Number)obj20).intValue();
        aobj4[1] = LList.list2(s1, vectors.vectorRef(fvector, i1));
        obj7 = append.append$V(aobj4);
          goto _L5
        staticLink.precision = AddOp.$Mn.apply2(staticLink.precision, printf.Lit7);
        if (j != 0)
        {
            obj14 = Boolean.TRUE;
        } else
        {
            obj14 = Boolean.FALSE;
        }
          goto _L9
        obj7 = Values.empty;
          goto _L5
_L2:
        aobj1 = new Object[3];
        aobj2 = new Object[0];
        aobj1[0] = lambda30formatReal$V(obj, obj1, obj2, obj3, aobj2);
        aobj1[1] = Scheme.apply.apply3(format$Mnreal, Boolean.TRUE, llist);
        aobj1[2] = printf.Lit63;
        return append.append$V(aobj1);
          goto _L9
        ClassCastException classcastexception13;
        classcastexception13;
        throw new WrongType(classcastexception13, "vector-ref", 2, obj20);
        if (true) goto _L11; else goto _L10
_L10:
    }

    Object lambda31$V(Object obj, Object obj1, Object obj2, Object aobj[])
    {
        LList llist = LList.makeList(aobj, 0);
        gnu.kawa.functions.Apply apply = Scheme.apply;
        Procedure procedure = staticLink.pad;
        gnu.kawa.functions.Apply apply1 = Scheme.apply;
        Object aobj1[] = new Object[6];
        aobj1[0] = format$Mnreal;
        aobj1[1] = staticLink.signed;
        aobj1[2] = obj;
        aobj1[3] = obj1;
        aobj1[4] = obj2;
        aobj1[5] = llist;
        return apply.apply2(procedure, apply1.applyN(aobj1));
    }

    public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.matchN(modulemethod, aobj, callcontext);

        case 14: // '\016'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 13: // '\r'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;
        }
    }

    public ()
    {
        format$Mnreal = new ModuleMethod(this, 13, printf.Lit64, -4092);
        ModuleMethod modulemethod = new ModuleMethod(this, 14, null, -4093);
        modulemethod.setProperty("source-location", "printf.scm:401");
        lambda$Fn17 = modulemethod;
    }
}
