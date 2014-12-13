// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

// Referenced classes of package gnu.bytecode:
//            ClassType

public class AnnotationEntry
    implements Annotation
{

    ClassType annotationType;
    LinkedHashMap elementsValue;

    public AnnotationEntry()
    {
        elementsValue = new LinkedHashMap(10);
    }

    public void addMember(String s, Object obj)
    {
        elementsValue.put(s, obj);
    }

    public Class annotationType()
    {
        return annotationType.getReflectClass();
    }

    public boolean equals(Object obj)
    {
        if (obj instanceof AnnotationEntry) goto _L2; else goto _L1
_L1:
        AnnotationEntry annotationentry;
        return false;
_L2:
        if (!getAnnotationType().getName().equals((annotationentry = (AnnotationEntry)obj).getAnnotationType().getName())) goto _L4; else goto _L3
_L3:
        Iterator iterator = elementsValue.entrySet().iterator();
_L8:
        if (!iterator.hasNext()) goto _L6; else goto _L5
_L5:
        Object obj3;
        Object obj4;
        java.util.Map.Entry entry1 = (java.util.Map.Entry)iterator.next();
        String s1 = (String)entry1.getKey();
        obj3 = entry1.getValue();
        obj4 = annotationentry.elementsValue.get(s1);
        if (obj3 == obj4) goto _L8; else goto _L7
_L7:
        if (obj3 == null || obj4 == null) goto _L4; else goto _L9
_L9:
        if (!obj3.equals(obj4))
        {
            return false;
        }
          goto _L8
_L6:
        Iterator iterator1 = annotationentry.elementsValue.entrySet().iterator();
label0:
        do
        {
label1:
            {
                Object obj1;
                Object obj2;
                do
                {
                    if (!iterator1.hasNext())
                    {
                        break label1;
                    }
                    java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
                    String s = (String)entry.getKey();
                    obj1 = entry.getValue();
                    obj2 = elementsValue.get(s);
                } while (obj2 == obj1);
                if (obj2 == null || obj1 == null)
                {
                    continue;
                }
                if (!obj2.equals(obj1))
                {
                    return false;
                }
            }
        } while (true);
_L4:
        if (true) goto _L1; else goto _L10
_L10:
        return true;
    }

    public ClassType getAnnotationType()
    {
        return annotationType;
    }

    public int hashCode()
    {
        int i = 0;
        for (Iterator iterator = elementsValue.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            int j = ((String)entry.getKey()).hashCode();
            i += entry.getValue().hashCode() ^ j * 127;
        }

        return i;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append('@');
        stringbuilder.append(getAnnotationType().getName());
        stringbuilder.append('(');
        int i = 0;
        for (Iterator iterator = elementsValue.entrySet().iterator(); iterator.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
            if (i > 0)
            {
                stringbuilder.append(", ");
            }
            stringbuilder.append((String)entry.getKey());
            stringbuilder.append('=');
            stringbuilder.append(entry.getValue());
            i++;
        }

        stringbuilder.append(')');
        return stringbuilder.toString();
    }
}
