// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.reflect.Invoke;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.math.BaseUnit;
import gnu.math.Quantity;
import gnu.math.Unit;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

// Referenced classes of package kawa.standard:
//            Scheme

public class define_unit extends Syntax
{

    public static final define_unit define_base_unit;
    public static final define_unit define_unit;
    boolean base;

    public define_unit(boolean flag)
    {
        base = flag;
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Pair pair1;
        Declaration declaration;
        String s;
        ClassType classtype;
        Object obj1;
label0:
        {
            Object obj = pair.getCdr();
            if (obj instanceof Pair)
            {
                pair1 = (Pair)obj;
                if (pair1.getCar() instanceof Declaration)
                {
                    break label0;
                }
            }
            return translator.syntaxError((new StringBuilder()).append("invalid syntax for ").append(getName()).toString());
        }
        declaration = (Declaration)pair1.getCar();
        s = ((Symbol)declaration.getSymbol()).getLocalPart();
        classtype = ClassType.make("gnu.math.Unit");
        declaration.setType(classtype);
        obj1 = declaration.getValue();
        if (!(obj1 instanceof QuoteExp) || !(((QuoteExp)obj1).getValue() instanceof Unit)) goto _L2; else goto _L1
_L1:
        SetExp setexp = new SetExp(declaration, ((Expression) (obj1)));
        setexp.setDefining(true);
        declaration.noteValue(((Expression) (obj1)));
        return setexp;
_L2:
        if (base)
        {
            Object obj3 = pair1.getCdr();
            LList llist = LList.Empty;
            String s1 = null;
            if (obj3 != llist)
            {
                s1 = ((Pair)pair1.getCdr()).getCar().toString();
            }
            obj1 = new QuoteExp(BaseUnit.make(s, s1));
            continue; /* Loop/switch isn't completed */
        }
        if (!(pair1.getCdr() instanceof Pair))
        {
            return translator.syntaxError("missing value for define-unit");
        }
        Expression expression = translator.rewrite(((Pair)pair1.getCdr()).getCar());
        if (expression instanceof QuoteExp)
        {
            Object obj2 = ((QuoteExp)expression).getValue();
            if (obj2 instanceof Quantity)
            {
                obj1 = new QuoteExp(Unit.make(s, (Quantity)obj2));
                continue; /* Loop/switch isn't completed */
            }
        }
        Expression aexpression[] = new Expression[2];
        aexpression[0] = new QuoteExp(s);
        aexpression[1] = expression;
        obj1 = Invoke.makeInvokeStatic(classtype, "make", aexpression);
        if (true) goto _L1; else goto _L3
_L3:
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeexp, Translator translator)
    {
        if (!(pair.getCdr() instanceof Pair)) goto _L2; else goto _L1
_L1:
        Pair pair1;
        Object obj;
        pair1 = (Pair)pair.getCdr();
        obj = pair1.getCar();
        if (!(obj instanceof SimpleSymbol)) goto _L2; else goto _L3
_L3:
        String s;
        Declaration declaration;
        s = obj.toString();
        declaration = scopeexp.getDefine(Scheme.unitNamespace.getSymbol(s), 'w', translator);
        translator.push(declaration);
        Translator.setLine(declaration, pair1);
        declaration.setFlag(16384L);
        if (scopeexp instanceof ModuleExp)
        {
            declaration.setCanRead(true);
        }
        if (!base || pair1.getCdr() != LList.Empty) goto _L5; else goto _L4
_L4:
        Object obj1 = BaseUnit.make(s, (String)null);
_L6:
        if (obj1 != null)
        {
            declaration.noteValue(new QuoteExp(obj1));
        }
        vector.addElement(Translator.makePair(pair, this, Translator.makePair(pair1, declaration, pair1.getCdr())));
        return true;
_L5:
        boolean flag = pair1.getCdr() instanceof Pair;
        obj1 = null;
        if (flag)
        {
            Object obj2 = ((Pair)pair1.getCdr()).getCar();
            if (base && (obj2 instanceof CharSequence))
            {
                obj1 = BaseUnit.make(s, obj2.toString());
            } else
            {
                boolean flag1 = base;
                obj1 = null;
                if (!flag1)
                {
                    boolean flag2 = obj2 instanceof Quantity;
                    obj1 = null;
                    if (flag2)
                    {
                        obj1 = Unit.make(s, (Quantity)obj2);
                    }
                }
            }
        }
        if (true) goto _L6; else goto _L2
_L2:
        translator.error('e', "missing name in define-unit");
        return false;
    }

    static 
    {
        define_unit = new define_unit(false);
        define_unit.setName("define-unit");
        define_base_unit = new define_unit(true);
        define_base_unit.setName("define-base-unit");
    }
}
