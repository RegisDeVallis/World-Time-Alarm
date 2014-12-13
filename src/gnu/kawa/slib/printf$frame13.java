// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.lists.CharSeq;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.WrongType;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.numbers;
import kawa.lib.strings;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            printf

public class lambda.Fn19 extends ModuleBody
{

    Object cnt;
    Object end;
    final ModuleMethod lambda$Fn19;
    Object s;
    Object str;

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        if (modulemethod.selector == 21)
        {
            if (lambda33(obj))
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

    boolean lambda33(Object obj)
    {
        if (!strings.isString(obj)) goto _L2; else goto _L1
_L1:
label0:
        {
            Object obj11;
            Object obj12;
            Object obj13;
label1:
            {
                Object obj7;
                if (str == Boolean.FALSE)
                {
                    gnu.kawa.functions.NumberCompare numbercompare = Scheme.numGEq;
                    Object obj10 = AddOp.$Mn.apply2(end, cnt);
                    Object obj1;
                    Boolean boolean1;
                    int i;
                    int j;
                    Object aobj[];
                    CharSequence charsequence;
                    CharSeq charseq;
                    int k;
                    char c;
                    int l;
                    char c1;
                    Object aobj1[];
                    CharSequence charsequence1;
                    Object obj6;
                    CharSeq charseq1;
                    int i1;
                    CharSequence charsequence2;
                    int j1;
                    CharSequence charsequence3;
                    Object aobj2[];
                    CharSequence charsequence4;
                    int k1;
                    CharSequence charsequence5;
                    try
                    {
                        charsequence3 = (CharSequence)obj;
                    }
                    catch (ClassCastException classcastexception10)
                    {
                        throw new WrongType(classcastexception10, "string-length", 1, obj);
                    }
                    if (numbercompare.apply2(obj10, Integer.valueOf(strings.stringLength(charsequence3))) == Boolean.FALSE)
                    {
                        break label1;
                    }
                }
                aobj1 = new Object[2];
                try
                {
                    charsequence1 = (CharSequence)obj;
                }
                catch (ClassCastException classcastexception5)
                {
                    throw new WrongType(classcastexception5, "string-length", 1, obj);
                }
                aobj1[0] = Integer.valueOf(strings.stringLength(charsequence1));
                aobj1[1] = AddOp.$Mn.apply2(end, cnt);
                obj6 = numbers.min(aobj1);
                obj7 = printf.Lit1;
                while (Scheme.numGEq.apply2(obj7, obj6) == Boolean.FALSE) 
                {
                    Object obj8 = s;
                    Object obj9;
                    try
                    {
                        charseq1 = (CharSeq)obj8;
                    }
                    catch (ClassCastException classcastexception6)
                    {
                        throw new WrongType(classcastexception6, "string-set!", 1, obj8);
                    }
                    obj9 = cnt;
                    try
                    {
                        i1 = ((Number)obj9).intValue();
                    }
                    catch (ClassCastException classcastexception7)
                    {
                        throw new WrongType(classcastexception7, "string-set!", 2, obj9);
                    }
                    try
                    {
                        charsequence2 = (CharSequence)obj;
                    }
                    catch (ClassCastException classcastexception8)
                    {
                        throw new WrongType(classcastexception8, "string-ref", 1, obj);
                    }
                    try
                    {
                        j1 = ((Number)obj7).intValue();
                    }
                    catch (ClassCastException classcastexception9)
                    {
                        throw new WrongType(classcastexception9, "string-ref", 2, obj7);
                    }
                    strings.stringSet$Ex(charseq1, i1, strings.stringRef(charsequence2, j1));
                    cnt = AddOp.$Pl.apply2(cnt, printf.Lit7);
                    obj7 = AddOp.$Pl.apply2(obj7, printf.Lit7);
                }
                break label0;
            }
            aobj2 = new Object[2];
            obj11 = s;
            try
            {
                charsequence4 = (CharSequence)obj11;
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "substring", 1, obj11);
            }
            obj12 = cnt;
            try
            {
                k1 = ((Number)obj12).intValue();
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "substring", 3, obj12);
            }
            aobj2[0] = strings.substring(charsequence4, 0, k1);
            aobj2[1] = obj;
            s = strings.stringAppend(aobj2);
            obj13 = s;
            try
            {
                charsequence5 = (CharSequence)obj13;
            }
            catch (ClassCastException classcastexception13)
            {
                throw new WrongType(classcastexception13, "string-length", 1, obj13);
            }
            cnt = Integer.valueOf(strings.stringLength(charsequence5));
            end = cnt;
        }
_L4:
        if (str != Boolean.FALSE)
        {
            if (Scheme.numGEq.apply2(cnt, end) != Boolean.FALSE)
            {
                l = 1;
            } else
            {
                l = 0;
            }
        } else
        if (str != Boolean.FALSE)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        return 1 & l + 1;
_L2:
        if (str != Boolean.FALSE)
        {
            obj1 = Scheme.numGEq.apply2(cnt, end);
        } else
        {
            obj1 = str;
        }
        if (obj1 == Boolean.FALSE)
        {
            Object obj2 = str;
            Object obj4;
            Object obj5;
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
                i = 1;
            } else
            {
                i = 0;
            }
            j = 1 & i + 1;
            if (j == 0 ? j != 0 : Scheme.numGEq.apply2(cnt, end) != Boolean.FALSE)
            {
                aobj = new Object[2];
                aobj[0] = s;
                aobj[1] = strings.makeString(100);
                s = strings.stringAppend(aobj);
                Object obj3 = s;
                try
                {
                    charsequence = (CharSequence)obj3;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-length", 1, obj3);
                }
                end = Integer.valueOf(strings.stringLength(charsequence));
            }
            obj4 = s;
            try
            {
                charseq = (CharSeq)obj4;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-set!", 1, obj4);
            }
            obj5 = cnt;
            try
            {
                k = ((Number)obj5).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "string-set!", 2, obj5);
            }
            if (characters.isChar(obj))
            {
                try
                {
                    c1 = ((Char)obj).charValue();
                }
                catch (ClassCastException classcastexception4)
                {
                    throw new WrongType(classcastexception4, "string-set!", 3, obj);
                }
                c = c1;
            } else
            {
                c = '?';
            }
            strings.stringSet$Ex(charseq, k, c);
            cnt = AddOp.$Pl.apply2(cnt, printf.Lit7);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        if (modulemethod.selector == 21)
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

    public ()
    {
        ModuleMethod modulemethod = new ModuleMethod(this, 21, null, 4097);
        modulemethod.setProperty("source-location", "printf.scm:564");
        lambda$Fn19 = modulemethod;
    }
}
