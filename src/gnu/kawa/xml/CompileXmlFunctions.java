// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.QuoteExp;
import gnu.mapping.Procedure;

// Referenced classes of package gnu.kawa.xml:
//            MakeUnescapedData, TreeScanner, NodeSetType

public class CompileXmlFunctions
{

    public CompileXmlFunctions()
    {
    }

    public static Expression validateApplyMakeUnescapedData(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        Expression aexpression[] = applyexp.getArgs();
        if (aexpression.length == 1 && (aexpression[0] instanceof QuoteExp))
        {
            applyexp = new QuoteExp(((MakeUnescapedData)procedure).apply1(((QuoteExp)aexpression[0]).getValue()));
        }
        return applyexp;
    }

    public static Expression validateApplyTreeScanner(ApplyExp applyexp, InlineCalls inlinecalls, Type type, Procedure procedure)
    {
        applyexp.visitArgs(inlinecalls);
        gnu.lists.NodePredicate nodepredicate = ((TreeScanner)procedure).type;
        if (applyexp.getTypeRaw() == null && (nodepredicate instanceof Type))
        {
            applyexp.setType(NodeSetType.getInstance((Type)nodepredicate));
        }
        return applyexp;
    }
}
