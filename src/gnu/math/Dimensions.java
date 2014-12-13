// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.math;


// Referenced classes of package gnu.math:
//            BaseUnit, Unit

public class Dimensions
{

    public static Dimensions Empty = new Dimensions();
    private static Dimensions hashTable[] = new Dimensions[100];
    BaseUnit bases[];
    private Dimensions chain;
    int hash_code;
    short powers[];

    private Dimensions()
    {
        bases = new BaseUnit[1];
        bases[0] = Unit.Empty;
        enterHash(0);
    }

    Dimensions(BaseUnit baseunit)
    {
        bases = new BaseUnit[2];
        powers = new short[1];
        bases[0] = baseunit;
        bases[1] = Unit.Empty;
        powers[0] = 1;
        enterHash(baseunit.index);
    }

    private Dimensions(Dimensions dimensions, int i, Dimensions dimensions1, int j, int k)
    {
        int k1;
        int l1;
        int i2;
        hash_code = k;
        int l;
        for (l = 0; dimensions.bases[l] != Unit.Empty; l++) { }
        int i1;
        for (i1 = 0; dimensions1.bases[i1] != Unit.Empty; i1++) { }
        int j1 = 1 + (l + i1);
        bases = new BaseUnit[j1];
        powers = new short[j1];
        k1 = 0;
        l1 = 0;
        i2 = 0;
_L7:
        BaseUnit baseunit;
        BaseUnit baseunit1;
        baseunit = dimensions.bases[i2];
        baseunit1 = dimensions1.bases[l1];
        if (baseunit.index >= baseunit1.index) goto _L2; else goto _L1
_L1:
        int j2;
        j2 = i * dimensions.powers[i2];
        i2++;
_L4:
        if ((short)j2 != j2)
        {
            throw new ArithmeticException("overflow in dimensions");
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (baseunit1.index >= baseunit.index)
        {
            break; /* Loop/switch isn't completed */
        }
        baseunit = baseunit1;
        j2 = j * dimensions1.powers[l1];
        l1++;
        if (true) goto _L4; else goto _L3
_L3:
        if (baseunit1 == Unit.Empty)
        {
            bases[k1] = Unit.Empty;
            enterHash(k);
            return;
        }
        j2 = i * dimensions.powers[i2] + j * dimensions1.powers[l1];
        i2++;
        l1++;
        if (j2 == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (true) goto _L4; else goto _L5
_L5:
        bases[k1] = baseunit;
        short aword0[] = powers;
        int k2 = k1 + 1;
        aword0[k1] = (short)j2;
        k1 = k2;
        if (true) goto _L7; else goto _L6
_L6:
    }

    private void enterHash(int i)
    {
        hash_code = i;
        int j = (0x7fffffff & i) % hashTable.length;
        chain = hashTable[j];
        hashTable[j] = this;
    }

    private boolean matchesProduct(Dimensions dimensions, int i, Dimensions dimensions1, int j)
    {
        int k;
        int l;
        int i1;
        k = 0;
        l = 0;
        i1 = 0;
_L8:
        BaseUnit baseunit;
        BaseUnit baseunit1;
        baseunit = dimensions.bases[k];
        baseunit1 = dimensions1.bases[l];
        if (baseunit.index >= baseunit1.index) goto _L2; else goto _L1
_L1:
        int j1;
        j1 = i * dimensions.powers[k];
        k++;
_L6:
        if (bases[i1] == baseunit && powers[i1] == j1)
        {
            break; /* Loop/switch isn't completed */
        }
_L4:
        return false;
_L2:
        if (baseunit1.index < baseunit.index)
        {
            baseunit = baseunit1;
            j1 = j * dimensions1.powers[l];
            l++;
            continue; /* Loop/switch isn't completed */
        }
        if (baseunit1 != Unit.Empty)
        {
            break; /* Loop/switch isn't completed */
        }
        if (bases[i1] == baseunit1)
        {
            return true;
        }
        if (true) goto _L4; else goto _L3
_L3:
        j1 = i * dimensions.powers[k] + j * dimensions1.powers[l];
        k++;
        l++;
        if (j1 == 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (true) goto _L6; else goto _L5
_L5:
        i1++;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public static Dimensions product(Dimensions dimensions, int i, Dimensions dimensions1, int j)
    {
        int k = i * dimensions.hashCode() + j * dimensions1.hashCode();
        int l = (0x7fffffff & k) % hashTable.length;
        for (Dimensions dimensions2 = hashTable[l]; dimensions2 != null; dimensions2 = dimensions2.chain)
        {
            if (dimensions2.hash_code == k && dimensions2.matchesProduct(dimensions, i, dimensions1, j))
            {
                return dimensions2;
            }
        }

        return new Dimensions(dimensions, i, dimensions1, j, k);
    }

    public int getPower(BaseUnit baseunit)
    {
        for (int i = 0; bases[i].index <= baseunit.index; i++)
        {
            if (bases[i] == baseunit)
            {
                return powers[i];
            }
        }

        return 0;
    }

    public final int hashCode()
    {
        return hash_code;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        for (int i = 0; bases[i] != Unit.Empty; i++)
        {
            if (i > 0)
            {
                stringbuffer.append('*');
            }
            stringbuffer.append(bases[i]);
            short word0 = powers[i];
            if (word0 != 1)
            {
                stringbuffer.append('^');
                stringbuffer.append(word0);
            }
        }

        return stringbuffer.toString();
    }

}
