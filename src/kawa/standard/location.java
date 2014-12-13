// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ReferenceExp;
import gnu.kawa.functions.ApplyToArgs;
import gnu.kawa.reflect.Invoke;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Location;
import gnu.mapping.LocationProc;
import gnu.mapping.ProcLocation;
import gnu.mapping.Procedure;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class location extends Syntax
{

    public static final location location;
    private static ClassType thisType = ClassType.make("kawa.standard.location");

    public location()
    {
    }

    public static Procedure makeLocationProc(Location location1)
    {
        return new LocationProc(location1);
    }

    public static Location makeProcLocation$V(Procedure procedure, Object aobj[])
    {
        int i = aobj.length;
        if ((procedure instanceof ApplyToArgs) && i > 0 && (aobj[0] instanceof Procedure))
        {
            Procedure procedure1 = (Procedure)aobj[0];
            if ((procedure1 instanceof LocationProc) && i == 1)
            {
                return ((LocationProc)procedure1).getLocation();
            } else
            {
                Object aobj1[] = new Object[i - 1];
                System.arraycopy(((Object) (aobj)), 1, ((Object) (aobj1)), 0, aobj1.length);
                return new ProcLocation(procedure1, aobj1);
            }
        }
        if ((procedure instanceof LocationProc) && i == 0)
        {
            return ((LocationProc)procedure).getLocation();
        } else
        {
            return new ProcLocation(procedure, aobj);
        }
    }

    public static Expression rewrite(Expression expression, Translator translator)
    {
        if (expression instanceof ReferenceExp)
        {
            ReferenceExp referenceexp = (ReferenceExp)expression;
            referenceexp.setDontDereference(true);
            Declaration declaration = referenceexp.getBinding();
            if (declaration != null)
            {
                declaration.maybeIndirectBinding(translator);
                Declaration declaration1 = Declaration.followAliases(declaration);
                declaration1.setCanRead(true);
                declaration1.setCanWrite(true);
            }
            return referenceexp;
        }
        if (expression instanceof ApplyExp)
        {
            ApplyExp applyexp = (ApplyExp)expression;
            Expression aexpression[] = new Expression[1 + applyexp.getArgs().length];
            aexpression[0] = applyexp.getFunction();
            System.arraycopy(applyexp.getArgs(), 0, aexpression, 1, -1 + aexpression.length);
            return Invoke.makeInvokeStatic(thisType, "makeProcLocation", aexpression);
        } else
        {
            return translator.syntaxError("invalid argument to location");
        }
    }

    public Expression rewrite(Object obj, Translator translator)
    {
        if (!(obj instanceof Pair))
        {
            return translator.syntaxError("missing argument to location");
        }
        Pair pair = (Pair)obj;
        if (pair.getCdr() != LList.Empty)
        {
            return translator.syntaxError("extra arguments to location");
        } else
        {
            Expression aexpression[] = new Expression[1];
            location _tmp = location;
            aexpression[0] = rewrite(translator.rewrite(pair.getCar()), translator);
            return Invoke.makeInvokeStatic(thisType, "makeLocationProc", aexpression);
        }
    }

    static 
    {
        location = new location();
        location.setName("location");
    }
}
