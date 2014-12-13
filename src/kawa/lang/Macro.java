// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Procedure;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Referenced classes of package kawa.lang:
//            Syntax, Quote, Translator

public class Macro extends Syntax
    implements Printable, Externalizable
{

    private ScopeExp capturedScope;
    public Object expander;
    private boolean hygienic;
    Object instance;

    public Macro()
    {
        hygienic = true;
    }

    public Macro(Object obj)
    {
        super(obj);
        hygienic = true;
    }

    public Macro(Object obj, Procedure procedure)
    {
        super(obj);
        hygienic = true;
        expander = new QuoteExp(procedure);
    }

    public Macro(Macro macro)
    {
        hygienic = true;
        name = macro.name;
        expander = macro.expander;
        hygienic = macro.hygienic;
    }

    public static Macro make(Declaration declaration)
    {
        Macro macro = new Macro(declaration.getSymbol());
        declaration.setSyntax();
        macro.capturedScope = declaration.context;
        return macro;
    }

    public static Macro make(Object obj, Procedure procedure)
    {
        return new Macro(obj, procedure);
    }

    public static Macro make(Object obj, Procedure procedure, Object obj1)
    {
        Macro macro = new Macro(obj, procedure);
        macro.instance = obj1;
        return macro;
    }

    public static Macro makeNonHygienic(Object obj, Procedure procedure)
    {
        Macro macro = new Macro(obj, procedure);
        macro.hygienic = false;
        return macro;
    }

    public static Macro makeNonHygienic(Object obj, Procedure procedure, Object obj1)
    {
        Macro macro = new Macro(obj, procedure);
        macro.hygienic = false;
        macro.instance = obj1;
        return macro;
    }

    public Object expand(Object obj, Translator translator)
    {
        Object obj1 = expander;
        if (!(obj1 instanceof Procedure) || (obj1 instanceof Expression)) goto _L2; else goto _L1
_L1:
        Procedure procedure = (Procedure)obj1;
_L9:
        if (hygienic) goto _L4; else goto _L3
_L3:
        int i;
        obj = Quote.quote(obj, translator);
        i = Translator.listLength(obj);
        if (i > 0) goto _L6; else goto _L5
_L5:
        return translator.syntaxError((new StringBuilder()).append("invalid macro argument list to ").append(this).toString());
_L2:
        if (obj1 instanceof Expression) goto _L8; else goto _L7
_L7:
        Macro macro;
        macro = translator.currentMacroDefinition;
        translator.currentMacroDefinition = this;
        obj1 = translator.rewrite(obj1);
        expander = obj1;
        translator.currentMacroDefinition = macro;
_L8:
        procedure = (Procedure)((Expression)obj1).eval(translator.getGlobalEnvironment());
          goto _L9
        Exception exception;
        exception;
        try
        {
            translator.currentMacroDefinition = macro;
            throw exception;
        }
        catch (Throwable throwable)
        {
            return translator.syntaxError((new StringBuilder()).append("evaluating syntax transformer '").append(getName()).append("' threw ").append(throwable).toString());
        }
_L6:
        int j = i - 1;
        Object aobj[] = new Object[j];
        int k = 0;
_L11:
        if (k >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        Pair pair1 = (Pair)obj;
        if (k <= 0)
        {
            break MISSING_BLOCK_LABEL_230;
        }
        aobj[k - 1] = pair1.getCar();
        obj = pair1.getCdr();
        k++;
        if (true) goto _L11; else goto _L10
_L10:
        Object obj3 = procedure.applyN(aobj);
_L12:
        if ((obj instanceof PairWithPosition) && (obj3 instanceof Pair) && !(obj3 instanceof PairWithPosition))
        {
            Pair pair = (Pair)obj3;
            return new PairWithPosition((PairWithPosition)obj, pair.getCar(), pair.getCdr());
        }
        break MISSING_BLOCK_LABEL_318;
_L4:
        Object obj2 = procedure.apply1(obj);
        obj3 = obj2;
          goto _L12
        return obj3;
    }

    public ScopeExp getCapturedScope()
    {
        if (capturedScope != null) goto _L2; else goto _L1
_L1:
        if (!(instance instanceof ModuleExp)) goto _L4; else goto _L3
_L3:
        capturedScope = (ModuleExp)instance;
_L2:
        return capturedScope;
_L4:
        if (instance != null)
        {
            capturedScope = ModuleInfo.findFromInstance(instance).getModuleExp();
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public final boolean isHygienic()
    {
        return hygienic;
    }

    public void print(Consumer consumer)
    {
        consumer.write("#<macro ");
        consumer.write(getName());
        consumer.write(62);
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        setName((String)objectinput.readObject());
        expander = new QuoteExp(objectinput.readObject());
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        return translator.rewrite(expand(pair, translator));
    }

    public Expression rewriteForm(Object obj, Translator translator)
    {
        return translator.rewrite(expand(obj, translator));
    }

    public void scanForm(Pair pair, ScopeExp scopeexp, Translator translator)
    {
        String s;
        int i;
        int j;
        Syntax syntax;
        s = translator.getFileName();
        i = translator.getLineNumber();
        j = translator.getColumnNumber();
        syntax = translator.currentSyntax;
        translator.setLine(pair);
        translator.currentSyntax = this;
        translator.scanForm(expand(pair, translator), scopeexp);
        translator.setLine(s, i, j);
        translator.currentSyntax = syntax;
        return;
        Exception exception;
        exception;
        translator.setLine(s, i, j);
        translator.currentSyntax = syntax;
        throw exception;
    }

    public void setCapturedScope(ScopeExp scopeexp)
    {
        capturedScope = scopeexp;
    }

    public final void setHygienic(boolean flag)
    {
        hygienic = flag;
    }

    public String toString()
    {
        return (new StringBuilder()).append("#<macro ").append(getName()).append('>').toString();
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(getName());
        objectoutput.writeObject(((QuoteExp)expander).getValue());
    }
}
