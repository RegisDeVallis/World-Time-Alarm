// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Filter;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;

class MethodFilter
    implements Filter
{

    ClassType caller;
    int modifiers;
    int modmask;
    String name;
    int nlen;
    ObjectType receiver;

    public MethodFilter(String s, int i, int j, ClassType classtype, ObjectType objecttype)
    {
        name = s;
        nlen = s.length();
        modifiers = i;
        modmask = j;
        caller = classtype;
        receiver = objecttype;
    }

    public boolean select(Object obj)
    {
        Method method;
        String s;
        int i;
        method = (Method)obj;
        s = method.getName();
        i = method.getModifiers();
        if ((i & modmask) == modifiers && (i & 0x1000) == 0 && s.startsWith(name)) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int j;
        j = s.length();
        if (j == nlen)
        {
            break; /* Loop/switch isn't completed */
        }
        if (j != 2 + nlen || s.charAt(nlen) != '$')
        {
            continue; /* Loop/switch isn't completed */
        }
        char c = s.charAt(1 + nlen);
        if (c == 'V' || c == 'X')
        {
            break; /* Loop/switch isn't completed */
        }
        if (j != 4 + nlen || !s.endsWith("$V$X")) goto _L1; else goto _L3
_L3:
        ClassType classtype;
        boolean flag;
        if (receiver instanceof ClassType)
        {
            classtype = (ClassType)receiver;
        } else
        {
            classtype = method.getDeclaringClass();
        }
        if (caller == null || caller.isAccessible(classtype, receiver, method.getModifiers()))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        return flag;
    }
}
