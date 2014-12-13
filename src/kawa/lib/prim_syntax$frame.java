// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.lists.Pair;
import gnu.mapping.Values;
import kawa.lang.Pattern;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.standard.syntax_case;

// Referenced classes of package kawa.lib:
//            prim_syntax

public class  extends ModuleBody
{

    Object $unnamed$0[];
    Object out$Mnbindings;
    Object out$Mninits;

    public Object lambda4processBinding(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(8, $unnamed$0);
        if (prim_syntax.Lit34.match(obj, aobj, 0))
        {
            return Values.empty;
        }
        if (prim_syntax.Lit35.match(obj, aobj, 0))
        {
            TemplateScope templatescope3 = TemplateScope.make();
            out$Mnbindings = new Pair(prim_syntax.Lit36.execute(aobj, templatescope3), out$Mnbindings);
            TemplateScope templatescope4 = TemplateScope.make();
            out$Mninits = new Pair(prim_syntax.Lit37.execute(aobj, templatescope4), out$Mninits);
            TemplateScope templatescope5 = TemplateScope.make();
            return lambda4processBinding(prim_syntax.Lit38.execute(aobj, templatescope5));
        }
        if (prim_syntax.Lit39.match(obj, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            out$Mnbindings = new Pair(prim_syntax.Lit40.execute(aobj, templatescope), out$Mnbindings);
            TemplateScope templatescope1 = TemplateScope.make();
            out$Mninits = new Pair(prim_syntax.Lit41.execute(aobj, templatescope1), out$Mninits);
            TemplateScope templatescope2 = TemplateScope.make();
            return lambda4processBinding(prim_syntax.Lit42.execute(aobj, templatescope2));
        }
        if (prim_syntax.Lit43.match(obj, aobj, 0))
        {
            Object aobj2[];
            if ("missing initializion in letrec" instanceof Object[])
            {
                aobj2 = (Object[])"missing initializion in letrec";
            } else
            {
                aobj2 = (new Object[] {
                    "missing initializion in letrec"
                });
            }
            return prim_syntax.syntaxError(obj, aobj2);
        }
        if (prim_syntax.Lit44.match(obj, aobj, 0))
        {
            Object aobj1[];
            if ("invalid bindings syntax in letrec" instanceof Object[])
            {
                aobj1 = (Object[])"invalid bindings syntax in letrec";
            } else
            {
                aobj1 = (new Object[] {
                    "invalid bindings syntax in letrec"
                });
            }
            return prim_syntax.syntaxError(obj, aobj1);
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    public ()
    {
    }
}
