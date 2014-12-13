// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.mapping.LazyPropertyKey;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;

public class Convert extends Procedure2
{

    public static final Convert as;

    public Convert()
    {
    }

    public static Convert getInstance()
    {
        return as;
    }

    public Object apply2(Object obj, Object obj1)
    {
        Type type;
        if (obj instanceof Class)
        {
            type = Type.make((Class)obj);
        } else
        {
            type = (Type)obj;
        }
        return type.coerceFromObject(obj1);
    }

    static 
    {
        as = new Convert();
        as.setName("as");
        as.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyConvert");
        Procedure.compilerKey.set(as, "*gnu.kawa.functions.CompileMisc:forConvert");
    }
}
