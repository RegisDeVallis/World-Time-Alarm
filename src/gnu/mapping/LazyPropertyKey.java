// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

// Referenced classes of package gnu.mapping:
//            PropertyKey, PropertySet

public class LazyPropertyKey extends PropertyKey
{

    public LazyPropertyKey(String s)
    {
        super(s);
    }

    public Object get(PropertySet propertyset, Object obj)
    {
        Object obj1;
        obj1 = propertyset.getProperty(this, obj);
        if (!(obj1 instanceof String))
        {
            break MISSING_BLOCK_LABEL_277;
        }
        String s = (String)obj1;
        int i;
        int j;
        if (s.charAt(0) == '*')
        {
            i = 1;
        } else
        {
            i = 0;
        }
        j = s.indexOf(':');
        if (j <= i || j >= -1 + s.length())
        {
            throw new RuntimeException((new StringBuilder()).append("lazy property ").append(this).append(" must have the form \"ClassName:fieldName\" or \"ClassName:staticMethodName\"").toString());
        }
        String s1 = s.substring(i, j);
        String s2 = s.substring(j + 1);
        Class class1;
        Object obj2;
        Object obj3;
        Object obj4;
        try
        {
            class1 = Class.forName(s1, true, propertyset.getClass().getClassLoader());
        }
        catch (Throwable throwable)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("lazy property ").append(this).append(" has specifier \"").append(s).append("\" but there is no such ");
            String s3;
            if (i == 0)
            {
                s3 = "field";
            } else
            {
                s3 = "method";
            }
            throw new RuntimeException(stringbuilder.append(s3).toString(), throwable);
        }
        if (i != 0) goto _L2; else goto _L1
_L1:
        obj2 = class1.getField(s2).get(null);
        obj3 = obj2;
_L4:
        propertyset.setProperty(this, obj3);
        return obj3;
_L2:
        obj4 = class1.getDeclaredMethod(s2, new Class[] {
            java/lang/Object
        }).invoke(null, new Object[] {
            propertyset
        });
        obj3 = obj4;
        if (true) goto _L4; else goto _L3
_L3:
        return obj1;
    }

    public void set(PropertySet propertyset, String s)
    {
        propertyset.setProperty(this, s);
    }
}
