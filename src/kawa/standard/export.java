// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class export extends Syntax
{

    public static final export export = new export();
    public static final export module_export;

    public export()
    {
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        return null;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeexp, Translator translator)
    {
        Object obj;
        Object obj1;
        obj = pair.getCdr();
        obj1 = translator.pushPositionOf(pair);
        if (!(scopeexp instanceof ModuleExp)) goto _L2; else goto _L1
_L1:
        ((ModuleExp)scopeexp).setFlag(16384);
        SyntaxForm syntaxform = null;
_L8:
        if (obj == LList.Empty) goto _L4; else goto _L3
_L3:
        translator.pushPositionOf(obj);
        for (; obj instanceof SyntaxForm; obj = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj;
        }

          goto _L5
_L2:
        translator.error('e', (new StringBuilder()).append("'").append(getName()).append("' not at module level").toString());
        translator.popPositionOf(obj1);
        return true;
_L5:
        SyntaxForm syntaxform1 = syntaxform;
        if (!(obj instanceof Pair)) goto _L7; else goto _L6
_L6:
        Pair pair1;
        Object obj2;
        pair1 = (Pair)obj;
        for (obj2 = pair1.getCar(); obj2 instanceof SyntaxForm; obj2 = syntaxform1.getDatum())
        {
            syntaxform1 = (SyntaxForm)obj2;
        }

        if (obj2 instanceof String)
        {
            String s = (String)obj2;
            if (s.startsWith("namespace:"))
            {
                translator.error('w', "'namespace:' prefix ignored");
                obj2 = s.substring(10).intern();
            }
        }
        if ((obj2 instanceof String) || (obj2 instanceof Symbol))
        {
            break MISSING_BLOCK_LABEL_346;
        }
          goto _L7
_L9:
        Declaration declaration = scopeexp.getNoDefine(obj2);
        if (declaration.getFlag(512L))
        {
            Translator.setLine(declaration, pair1);
        }
        declaration.setFlag(1024L);
        obj = pair1.getCdr();
          goto _L8
_L7:
        translator.error('e', (new StringBuilder()).append("invalid syntax in '").append(getName()).append('\'').toString());
        translator.popPositionOf(obj1);
        return false;
_L4:
        translator.popPositionOf(obj1);
        return true;
        Exception exception;
        exception;
        translator.popPositionOf(obj1);
        throw exception;
        if (syntaxform1 == null);
          goto _L9
    }

    static 
    {
        module_export = new export();
        module_export.setName("module-export");
        module_export.setName("export");
    }
}
