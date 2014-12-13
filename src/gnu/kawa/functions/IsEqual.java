// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure2;

// Referenced classes of package gnu.kawa.functions:
//            Arithmetic, NumberCompare

public class IsEqual extends Procedure2
{

    Language language;

    public IsEqual(Language language1, String s)
    {
        language = language1;
        setName(s);
    }

    public static boolean apply(Object obj, Object obj1)
    {
        if (obj == obj1)
        {
            return true;
        }
        if (obj == null || obj1 == null)
        {
            return false;
        }
        if ((obj instanceof Number) && (obj1 instanceof Number))
        {
            return numberEquals((Number)obj, (Number)obj1);
        }
        if (obj instanceof CharSequence)
        {
            if (!(obj1 instanceof CharSequence))
            {
                return false;
            }
            CharSequence charsequence = (CharSequence)obj;
            CharSequence charsequence1 = (CharSequence)obj1;
            int k = charsequence.length();
            if (k != charsequence1.length())
            {
                return false;
            }
            for (int l = k; --l >= 0;)
            {
                if (charsequence.charAt(l) != charsequence1.charAt(l))
                {
                    return false;
                }
            }

            return true;
        }
        if (obj instanceof FVector)
        {
            if (!(obj1 instanceof FVector))
            {
                return false;
            }
            FVector fvector = (FVector)obj;
            FVector fvector1 = (FVector)obj1;
            int i = fvector.size;
            if (fvector1.data == null || fvector1.size != i)
            {
                return false;
            }
            Object aobj[] = fvector.data;
            Object aobj1[] = fvector1.data;
            for (int j = i; --j >= 0;)
            {
                if (!apply(aobj[j], aobj1[j]))
                {
                    return false;
                }
            }

            return true;
        } else
        if (obj instanceof LList)
        {
            if (!(obj instanceof Pair) || !(obj1 instanceof Pair))
            {
                return false;
            }
            Pair pair = (Pair)obj;
            Pair pair1 = (Pair)obj1;
            do
            {
                if (!apply(pair.getCar(), pair1.getCar()))
                {
                    return false;
                }
                Object obj2 = pair.getCdr();
                Object obj3 = pair1.getCdr();
                if (obj2 == obj3)
                {
                    return true;
                }
                if (obj2 == null || obj3 == null)
                {
                    return false;
                }
                if (!(obj2 instanceof Pair) || !(obj3 instanceof Pair))
                {
                    return apply(obj2, obj3);
                }
                pair = (Pair)obj2;
                pair1 = (Pair)obj3;
            } while (true);
        } else
        {
            return obj.equals(obj1);
        }
    }

    public static boolean numberEquals(Number number, Number number1)
    {
        boolean flag = Arithmetic.isExact(number);
        boolean flag1 = Arithmetic.isExact(number1);
        if (flag && flag1)
        {
            return NumberCompare.$Eq(number, number1);
        }
        return flag == flag1 && number.equals(number1);
    }

    public Object apply2(Object obj, Object obj1)
    {
        return language.booleanObject(apply(obj, obj1));
    }
}
