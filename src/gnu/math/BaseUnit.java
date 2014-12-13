// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

// Referenced classes of package gnu.math:
//            NamedUnit, Dimensions, Unit

public class BaseUnit extends NamedUnit
    implements Externalizable
{

    static int base_count = 0;
    private static final String unitName = "(name)";
    String dimension;
    int index;

    public BaseUnit()
    {
        name = "(name)";
        index = 0x7fffffff;
        dims = Dimensions.Empty;
    }

    public BaseUnit(String s)
    {
        name = s;
        init();
    }

    public BaseUnit(String s, String s1)
    {
        name = s;
        dimension = s1;
        init();
    }

    public static int compare(BaseUnit baseunit, BaseUnit baseunit1)
    {
        int i = baseunit.name.compareTo(baseunit1.name);
        if (i != 0)
        {
            return i;
        }
        String s = baseunit.dimension;
        String s1 = baseunit1.dimension;
        if (s == s1)
        {
            return 0;
        }
        if (s == null)
        {
            return -1;
        }
        if (s1 == null)
        {
            return 1;
        } else
        {
            return s.compareTo(s1);
        }
    }

    public static BaseUnit lookup(String s, String s1)
    {
        String s2 = s.intern();
        if (s2 != "(name)" || s1 != null) goto _L2; else goto _L1
_L1:
        BaseUnit baseunit = Unit.Empty;
_L6:
        return baseunit;
_L2:
        NamedUnit namedunit;
        int i = (0x7fffffff & s2.hashCode()) % table.length;
        namedunit = table[i];
_L7:
        if (namedunit == null) goto _L4; else goto _L3
_L3:
        if (namedunit.name != s2 || !(namedunit instanceof BaseUnit))
        {
            continue; /* Loop/switch isn't completed */
        }
        baseunit = (BaseUnit)namedunit;
        if (baseunit.dimension == s1) goto _L6; else goto _L5
_L5:
        namedunit = namedunit.chain;
          goto _L7
_L4:
        return null;
    }

    public static BaseUnit make(String s, String s1)
    {
        BaseUnit baseunit = lookup(s, s1);
        if (baseunit == null)
        {
            baseunit = new BaseUnit(s, s1);
        }
        return baseunit;
    }

    public String getDimension()
    {
        return dimension;
    }

    public int hashCode()
    {
        return name.hashCode();
    }

    protected void init()
    {
        base = this;
        scale = 1.0D;
        dims = new Dimensions(this);
        super.init();
        int i = base_count;
        base_count = i + 1;
        index = i;
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        name = objectinput.readUTF();
        dimension = (String)objectinput.readObject();
    }

    public Object readResolve()
        throws ObjectStreamException
    {
        BaseUnit baseunit = lookup(name, dimension);
        if (baseunit != null)
        {
            return baseunit;
        } else
        {
            init();
            return this;
        }
    }

    public Unit unit()
    {
        return this;
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeUTF(name);
        objectoutput.writeObject(dimension);
    }

    static 
    {
        base_count = 0;
    }
}
