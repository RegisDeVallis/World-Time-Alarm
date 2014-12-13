// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_implements extends Syntax
{

    public static final module_implements module_implements;

    public module_implements()
    {
    }

    public void scanForm(Pair pair, ScopeExp scopeexp, Translator translator)
    {
        Object obj = pair.getCdr();
        int i = LList.listLength(obj, false);
        if (i < 0)
        {
            translator.syntaxError((new StringBuilder()).append("improper argument list for ").append(getName()).toString());
            return;
        }
        ClassType aclasstype[] = new ClassType[i];
        for (int j = 0; j < i; j++)
        {
            Pair pair1 = (Pair)obj;
            aclasstype[j] = (ClassType)translator.exp2Type(pair1);
            obj = pair1.getCdr();
        }

        ModuleExp moduleexp = translator.getModule();
        moduleexp.setInterfaces(aclasstype);
        moduleexp.setFlag(0x20000);
    }

    static 
    {
        module_implements = new module_implements();
        module_implements.setName("module-implements");
    }
}
