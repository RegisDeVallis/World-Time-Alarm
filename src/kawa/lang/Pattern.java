// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.text.Printable;

public abstract class Pattern
    implements Printable
{

    private static Type matchArgs[];
    public static final Method matchPatternMethod;
    public static ClassType typePattern;

    public Pattern()
    {
    }

    public abstract boolean match(Object obj, Object aobj[], int i);

    public Object[] match(Object obj)
    {
        Object aobj[] = new Object[varCount()];
        if (match(obj, aobj, 0))
        {
            return aobj;
        } else
        {
            return null;
        }
    }

    public abstract int varCount();

    static 
    {
        typePattern = ClassType.make("kawa.lang.Pattern");
        Type atype[] = new Type[3];
        atype[0] = Type.pointer_type;
        atype[1] = Compilation.objArrayType;
        atype[2] = Type.intType;
        matchArgs = atype;
        matchPatternMethod = typePattern.addMethod("match", matchArgs, Type.booleanType, 1);
    }
}
