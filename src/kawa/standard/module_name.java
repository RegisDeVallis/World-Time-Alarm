// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ModuleExp;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Stack;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class module_name extends Syntax
{

    public static final module_name module_name;

    public module_name()
    {
    }

    public void scanForm(Pair pair, ScopeExp scopeexp, Translator translator)
    {
        SyntaxForm syntaxform;
        Object obj1;
        String s;
        Declaration declaration;
        Object obj = pair.getCdr();
        syntaxform = null;
        for (; obj instanceof SyntaxForm; obj = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj;
        }

        if (obj instanceof Pair)
        {
            obj1 = ((Pair)obj).getCar();
        } else
        {
            obj1 = null;
        }
        for (; obj1 instanceof SyntaxForm; obj1 = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj1;
        }

        s = null;
        declaration = null;
        if (!(obj1 instanceof Pair)) goto _L2; else goto _L1
_L1:
        Pair pair1 = (Pair)obj1;
        if (pair1.getCar() != "quote") goto _L2; else goto _L3
_L3:
        Object obj2 = pair1.getCdr();
        if (!(obj2 instanceof Pair)) goto _L5; else goto _L4
_L4:
        Pair pair2 = (Pair)obj2;
        if (pair2.getCdr() == LList.Empty && (pair2.getCar() instanceof String)) goto _L6; else goto _L5
_L5:
        String s1 = "invalid quoted symbol for 'module-name'";
_L8:
        if (s1 != null)
        {
            translator.formStack.add(translator.syntaxError(s1));
            return;
        }
        break; /* Loop/switch isn't completed */
_L6:
        s = (String)pair2.getCar();
        declaration = null;
        s1 = null;
        continue; /* Loop/switch isn't completed */
_L2:
        if ((obj1 instanceof FString) || (obj1 instanceof String))
        {
            s = obj1.toString();
            declaration = null;
            s1 = null;
        } else
        if (obj1 instanceof Symbol)
        {
            s = obj1.toString();
            int j = s.length();
            if (j > 2 && s.charAt(0) == '<' && s.charAt(j - 1) == '>')
            {
                s = s.substring(1, j - 1);
            }
            declaration = translator.define(obj1, syntaxform, scopeexp);
            s1 = null;
        } else
        {
            s1 = "un-implemented expression in module-name";
            declaration = null;
            s = null;
        }
        if (true) goto _L8; else goto _L7
_L7:
        String s2;
        int i = s.lastIndexOf('.');
        s2 = s;
        ModuleExp moduleexp;
        if (i >= 0)
        {
            translator.classPrefix = s.substring(0, i + 1);
        } else
        {
            s = (new StringBuilder()).append(translator.classPrefix).append(s).toString();
            s2 = (new StringBuilder()).append(translator.classPrefix).append(Compilation.mangleName(s)).toString();
        }
        moduleexp = translator.getModule();
        if (translator.mainClass != null) goto _L10; else goto _L9
_L9:
        translator.mainClass = new ClassType(s2);
_L12:
        moduleexp.setType(translator.mainClass);
        moduleexp.setName(s);
        if (declaration != null)
        {
            declaration.noteValue(new QuoteExp(translator.mainClass, Compilation.typeClass));
            declaration.setFlag(0x1004000L);
            if (moduleexp.outer == null)
            {
                declaration.setFlag(2048L);
            }
            declaration.setPrivate(true);
            declaration.setType(Compilation.typeClass);
        }
        translator.mustCompileHere();
        return;
_L10:
        String s3 = translator.mainClass.getName();
        if (s3 == null)
        {
            translator.mainClass.setName(s2);
        } else
        if (!s3.equals(s2))
        {
            translator.syntaxError((new StringBuilder()).append("duplicate module-name: old name: ").append(s3).toString());
        }
        if (true) goto _L12; else goto _L11
_L11:
    }

    static 
    {
        module_name = new module_name();
        module_name.setName("module-name");
    }
}
