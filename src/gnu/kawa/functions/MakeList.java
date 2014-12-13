// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.ProcedureN;

public class MakeList extends ProcedureN
    implements Inlineable
{

    public static final MakeList list;

    public MakeList()
    {
    }

    public static void compile(Expression aexpression[], int i, Compilation compilation)
    {
        int j = aexpression.length - i;
        CodeAttr codeattr = compilation.getCode();
        if (j == 0)
        {
            (new QuoteExp(LList.Empty)).compile(compilation, Target.pushObject);
            return;
        }
        if (j <= 4)
        {
            for (int i1 = 0; i1 < j; i1++)
            {
                aexpression[i + i1].compile(compilation, Target.pushObject);
            }

            codeattr.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod((new StringBuilder()).append("list").append(j).toString(), null));
            return;
        }
        aexpression[i].compile(compilation, Target.pushObject);
        codeattr.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("list1", null));
        codeattr.emitDup(1);
        int k = i + 1;
        int l;
        for (l = j - 1; l >= 4;)
        {
            aexpression[k].compile(compilation, Target.pushObject);
            aexpression[k + 1].compile(compilation, Target.pushObject);
            aexpression[k + 2].compile(compilation, Target.pushObject);
            aexpression[k + 3].compile(compilation, Target.pushObject);
            l -= 4;
            k += 4;
            codeattr.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("chain4", null));
        }

        while (l > 0) 
        {
            aexpression[k].compile(compilation, Target.pushObject);
            l--;
            k++;
            codeattr.emitInvokeStatic(Compilation.scmListType.getDeclaredMethod("chain1", null));
        }
        codeattr.emitPop(1);
    }

    public static Object list$V(Object aobj[])
    {
        LList llist = LList.Empty;
        int i = aobj.length;
        Object obj;
        for (obj = llist; --i >= 0; obj = new Pair(aobj[i], obj)) { }
        return obj;
    }

    public Object applyN(Object aobj[])
    {
        return list$V(aobj);
    }

    public void compile(ApplyExp applyexp, Compilation compilation, Target target)
    {
        compile(applyexp.getArgs(), 0, compilation);
        target.compileFromStack(compilation, applyexp.getType());
    }

    public Type getReturnType(Expression aexpression[])
    {
        if (aexpression.length > 0)
        {
            return Compilation.typePair;
        } else
        {
            return LangObjType.listType;
        }
    }

    static 
    {
        list = new MakeList();
        list.setName("list");
    }
}
