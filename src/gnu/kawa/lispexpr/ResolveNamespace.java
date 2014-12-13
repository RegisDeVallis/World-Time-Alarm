// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.lispexpr;

import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class ResolveNamespace extends Syntax
{

    public static final ResolveNamespace resolveNamespace;
    public static final ResolveNamespace resolveQName = new ResolveNamespace("$resolve-qname", true);
    boolean resolvingQName;

    public ResolveNamespace(String s, boolean flag)
    {
        super(s);
        resolvingQName = flag;
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Pair pair1 = (Pair)pair.getCdr();
        Namespace namespace = translator.namespaceResolvePrefix(translator.rewrite_car(pair1, false));
        if (namespace == null)
        {
            String s = pair1.getCar().toString();
            if (s == "[default-element-namespace]")
            {
                namespace = Namespace.EmptyNamespace;
            } else
            {
                Object obj = translator.pushPositionOf(pair1);
                translator.error('e', (new StringBuilder()).append("unknown namespace prefix ").append(s).toString());
                translator.popPositionOf(obj);
                namespace = Namespace.valueOf(s, s);
            }
        }
        if (resolvingQName)
        {
            return new QuoteExp(namespace.getSymbol(((Pair)pair1.getCdr()).getCar().toString()));
        } else
        {
            return new QuoteExp(namespace);
        }
    }

    static 
    {
        resolveNamespace = new ResolveNamespace("$resolve-namespace$", false);
        resolveNamespace.setName("$resolve-namespace$");
    }
}
