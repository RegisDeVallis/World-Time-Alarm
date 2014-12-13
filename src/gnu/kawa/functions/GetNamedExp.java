// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.PrimProcedure;
import gnu.expr.ReferenceExp;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Location;

// Referenced classes of package gnu.kawa.functions:
//            GetNamedPart

class GetNamedExp extends ApplyExp
{

    static final Declaration castDecl = Declaration.getDeclarationFromStatic("gnu.kawa.functions.Convert", "as");
    static final Declaration fieldDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.SlotGet", "field");
    static final Declaration instanceOfDecl = Declaration.getDeclarationFromStatic("kawa.standard.Scheme", "instanceOf");
    static final Declaration invokeDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "invoke");
    static final Declaration invokeStaticDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "invokeStatic");
    static final Declaration makeDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.Invoke", "make");
    static final Declaration staticFieldDecl = Declaration.getDeclarationFromStatic("gnu.kawa.reflect.SlotGet", "staticField");
    public String combinedName;
    char kind;
    PrimProcedure methods[];

    public GetNamedExp(Expression aexpression[])
    {
        super(GetNamedPart.getNamedPart, aexpression);
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        if (combinedName != null)
        {
            Environment environment = Environment.getCurrent();
            gnu.mapping.Symbol symbol = environment.getSymbol(combinedName);
            String s = Location.UNBOUND;
            Object obj = environment.get(symbol, null, s);
            if (obj != s)
            {
                callcontext.writeValue(obj);
                return;
            }
        }
        super.apply(callcontext);
    }

    protected GetNamedExp setProcedureKind(char c)
    {
        type = Compilation.typeProcedure;
        kind = c;
        return this;
    }

    public boolean side_effects()
    {
        if (kind == 'S' || kind == 'N' || kind == 'C' || kind == 'I')
        {
            return false;
        }
        if (kind == 'M')
        {
            return getArgs()[0].side_effects();
        } else
        {
            return true;
        }
    }

    public Expression validateApply(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Declaration declaration)
    {
        Expression aexpression[];
        Expression expression;
        Expression aexpression1[];
        aexpression = getArgs();
        expression = aexpression[0];
        aexpression1 = applyexp.getArgs();
        kind;
        JVM INSTR lookupswitch 5: default 72
    //                   67: 234
    //                   73: 188
    //                   77: 74
    //                   78: 152
    //                   83: 280;
           goto _L1 _L2 _L3 _L4 _L5 _L6
_L1:
        return applyexp;
_L4:
        Declaration declaration1;
        Expression aexpression2[];
        declaration1 = invokeDecl;
        aexpression2 = new Expression[2 + aexpression1.length];
        aexpression2[0] = aexpression[0];
        aexpression2[1] = aexpression[1];
        System.arraycopy(aexpression1, 0, aexpression2, 2, aexpression1.length);
_L8:
        ApplyExp applyexp1 = new ApplyExp(new ReferenceExp(declaration1), aexpression2);
        applyexp1.setLine(applyexp);
        return inlinecalls.visit(applyexp1, type);
_L5:
        declaration1 = makeDecl;
        aexpression2 = new Expression[1 + aexpression1.length];
        System.arraycopy(aexpression1, 0, aexpression2, 1, aexpression1.length);
        aexpression2[0] = expression;
        continue; /* Loop/switch isn't completed */
_L3:
        declaration1 = instanceOfDecl;
        aexpression2 = new Expression[1 + aexpression1.length];
        System.arraycopy(aexpression1, 1, aexpression2, 2, -1 + aexpression1.length);
        aexpression2[0] = aexpression1[0];
        aexpression2[1] = expression;
        continue; /* Loop/switch isn't completed */
_L2:
        declaration1 = castDecl;
        aexpression2 = new Expression[1 + aexpression1.length];
        System.arraycopy(aexpression1, 1, aexpression2, 2, -1 + aexpression1.length);
        aexpression2[0] = expression;
        aexpression2[1] = aexpression1[0];
        continue; /* Loop/switch isn't completed */
_L6:
        declaration1 = invokeStaticDecl;
        aexpression2 = new Expression[2 + aexpression1.length];
        aexpression2[0] = expression;
        aexpression2[1] = aexpression[1];
        System.arraycopy(aexpression1, 0, aexpression2, 2, aexpression1.length);
        if (true) goto _L8; else goto _L7
_L7:
    }

}
