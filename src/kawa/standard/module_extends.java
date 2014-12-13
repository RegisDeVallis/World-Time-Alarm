// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_extends extends Syntax
{

    public static final module_extends module_extends;

    public module_extends()
    {
    }

    public void scanForm(Pair pair, ScopeExp scopeexp, Translator translator)
    {
        gnu.bytecode.Type type = translator.exp2Type((Pair)pair.getCdr());
        ModuleExp moduleexp = translator.getModule();
        moduleexp.setSuperType((ClassType)type);
        moduleexp.setFlag(0x20000);
    }

    static 
    {
        module_extends = new module_extends();
        module_extends.setName("module-extends");
    }
}
