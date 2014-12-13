// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

// Referenced classes of package kawa.standard:
//            object

public class define_class extends Syntax
{

    public static final define_class define_class = new define_class("define-class", false);
    public static final define_class define_simple_class = new define_class("define-simple-class", true);
    boolean isSimple;
    object objectSyntax;

    define_class(String s, boolean flag)
    {
        super(s);
        objectSyntax = object.objectSyntax;
        isSimple = flag;
    }

    define_class(object object1, boolean flag)
    {
        objectSyntax = object1;
        isSimple = flag;
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Object obj = pair.getCdr();
        boolean flag = obj instanceof Pair;
        Declaration declaration = null;
        if (flag)
        {
            pair = (Pair)obj;
            Object obj1 = pair.getCar();
            if (!(obj1 instanceof Declaration))
            {
                return translator.syntaxError((new StringBuilder()).append(getName()).append(" can only be used in <body>").toString());
            }
            declaration = (Declaration)obj1;
        }
        ClassExp classexp = (ClassExp)declaration.getValue();
        objectSyntax.rewriteClassDef((Object[])(Object[])pair.getCdr(), translator);
        SetExp setexp = new SetExp(declaration, classexp);
        setexp.setDefining(true);
        return setexp;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeexp, Translator translator)
    {
        Object obj = pair.getCdr();
        SyntaxForm syntaxform = null;
        for (; obj instanceof SyntaxForm; obj = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj;
        }

        if (!(obj instanceof Pair))
        {
            return super.scanForDefinitions(pair, vector, scopeexp, translator);
        }
        Pair pair1 = (Pair)obj;
        Object obj1;
        for (obj1 = pair1.getCar(); obj1 instanceof SyntaxForm; obj1 = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj1;
        }

        Object obj2 = translator.namespaceResolve(obj1);
        if (!(obj2 instanceof String) && !(obj2 instanceof Symbol))
        {
            translator.error('e', "missing class name");
            return false;
        }
        Declaration declaration = translator.define(obj2, syntaxform, scopeexp);
        if (pair1 instanceof PairWithPosition)
        {
            declaration.setLocation((PairWithPosition)pair1);
        }
        ClassExp classexp = new ClassExp(isSimple);
        declaration.noteValue(classexp);
        declaration.setFlag(0x20004000L);
        gnu.bytecode.ClassType classtype;
        String s;
        int i;
        Object obj3;
        if (isSimple)
        {
            classtype = Compilation.typeClass;
        } else
        {
            classtype = Compilation.typeClassType;
        }
        declaration.setType(classtype);
        translator.mustCompileHere();
        if (obj2 instanceof Symbol)
        {
            s = ((Symbol)obj2).getName();
        } else
        {
            s = obj2.toString();
        }
        i = s.length();
        if (i > 2 && s.charAt(0) == '<' && s.charAt(i - 1) == '>')
        {
            s = s.substring(1, i - 1);
        }
        classexp.setName(s);
        for (obj3 = pair1.getCdr(); obj3 instanceof SyntaxForm; obj3 = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj3;
        }

        if (!(obj3 instanceof Pair))
        {
            translator.error('e', "missing class members");
            return false;
        }
        Pair pair2 = (Pair)obj3;
        ScopeExp scopeexp1 = translator.currentScope();
        if (syntaxform != null)
        {
            translator.setCurrentScope(syntaxform.getScope());
        }
        Object aobj[] = objectSyntax.scanClassDef(pair2, classexp, translator);
        if (syntaxform != null)
        {
            translator.setCurrentScope(scopeexp1);
        }
        if (aobj == null)
        {
            return false;
        } else
        {
            vector.addElement(Translator.makePair(pair, this, Translator.makePair(pair2, declaration, ((Object) (aobj)))));
            return true;
        }
    }

}
