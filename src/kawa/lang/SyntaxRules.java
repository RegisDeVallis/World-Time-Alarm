// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.ErrorExp;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure1;
import gnu.text.Printable;
import gnu.text.ReportFormat;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Referenced classes of package kawa.lang:
//            Translator, SyntaxRule, SyntaxForm, PatternScope, 
//            SyntaxPattern, Macro, Pattern, TemplateScope

public class SyntaxRules extends Procedure1
    implements Printable, Externalizable
{

    Object literal_identifiers[];
    int maxVars;
    SyntaxRule rules[];

    public SyntaxRules()
    {
        maxVars = 0;
    }

    public SyntaxRules(Object aobj[], Object obj, Translator translator)
    {
        int i;
        SyntaxForm syntaxform;
        int j;
        maxVars = 0;
        literal_identifiers = aobj;
        i = Translator.listLength(obj);
        if (i < 0)
        {
            i = 0;
            translator.syntaxError("missing or invalid syntax-rules");
        }
        rules = new SyntaxRule[i];
        syntaxform = null;
        j = 0;
_L3:
        Pair pair;
        SyntaxForm syntaxform1;
        Object obj1;
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_549;
        }
        for (; obj instanceof SyntaxForm; obj = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj;
        }

        pair = (Pair)obj;
        syntaxform1 = syntaxform;
        for (obj1 = pair.getCar(); obj1 instanceof SyntaxForm; obj1 = syntaxform1.getDatum())
        {
            syntaxform1 = (SyntaxForm)obj1;
        }

        if (obj1 instanceof Pair) goto _L2; else goto _L1
_L1:
        translator.syntaxError((new StringBuilder()).append("missing pattern in ").append(j).append("'th syntax rule").toString());
_L4:
        return;
_L2:
        SyntaxForm syntaxform2;
        Pair pair1;
        Object obj2;
        String s;
        int i1;
        int j1;
        SyntaxForm syntaxform3;
        syntaxform2 = syntaxform1;
        pair1 = (Pair)obj1;
        obj2 = pair1.getCar();
        s = translator.getFileName();
        i1 = translator.getLineNumber();
        j1 = translator.getColumnNumber();
        syntaxform3 = syntaxform1;
        Object obj3;
        translator.setLine(pair1);
        for (obj3 = pair1.getCdr(); obj3 instanceof SyntaxForm; obj3 = syntaxform3.getDatum())
        {
            syntaxform3 = (SyntaxForm)obj3;
        }

        if (obj3 instanceof Pair)
        {
            break MISSING_BLOCK_LABEL_293;
        }
        translator.syntaxError((new StringBuilder()).append("missing template in ").append(j).append("'th syntax rule").toString());
        translator.setLine(s, i1, j1);
        return;
        Pair pair2;
        pair2 = (Pair)obj3;
        if (pair2.getCdr() == LList.Empty)
        {
            break MISSING_BLOCK_LABEL_352;
        }
        translator.syntaxError((new StringBuilder()).append("junk after ").append(j).append("'th syntax rule").toString());
        translator.setLine(s, i1, j1);
        return;
        Object obj4;
        obj4 = pair2.getCar();
        translator.push(PatternScope.push(translator));
        for (; obj2 instanceof SyntaxForm; obj2 = syntaxform2.getDatum())
        {
            syntaxform2 = (SyntaxForm)obj2;
        }

        StringBuffer stringbuffer = new StringBuffer();
        if (!(obj2 instanceof Pair))
        {
            break MISSING_BLOCK_LABEL_516;
        }
        aobj[0] = ((Pair)obj2).getCar();
        Pair pair3 = (Pair)obj2;
        stringbuffer.append('\f');
        stringbuffer.append('\030');
        SyntaxPattern syntaxpattern = new SyntaxPattern(stringbuffer, pair3.getCdr(), syntaxform2, aobj, translator);
        rules[j] = new SyntaxRule(syntaxpattern, obj4, syntaxform3, translator);
        PatternScope.pop(translator);
        translator.pop();
        translator.setLine(s, i1, j1);
        j++;
        obj = pair.getCdr();
          goto _L3
        translator.syntaxError("pattern does not start with name");
        translator.setLine(s, i1, j1);
        return;
        Exception exception;
        exception;
        translator.setLine(s, i1, j1);
        throw exception;
        int k = rules.length;
        while (--k >= 0) 
        {
            int l = rules[k].patternNesting.length();
            if (l > maxVars)
            {
                maxVars = l;
            }
        }
          goto _L4
    }

    public SyntaxRules(Object aobj[], SyntaxRule asyntaxrule[], int i)
    {
        maxVars = 0;
        literal_identifiers = aobj;
        rules = asyntaxrule;
        maxVars = i;
    }

    public Object apply1(Object obj)
    {
        SyntaxForm syntaxform;
        Translator translator;
        gnu.expr.ScopeExp scopeexp;
        if (!(obj instanceof SyntaxForm))
        {
            break MISSING_BLOCK_LABEL_63;
        }
        syntaxform = (SyntaxForm)obj;
        translator = (Translator)Compilation.getCurrent();
        scopeexp = translator.currentScope();
        translator.setCurrentScope(syntaxform.getScope());
        Object obj1 = expand(syntaxform, translator);
        translator.setCurrentScope(scopeexp);
        return obj1;
        Exception exception;
        exception;
        translator.setCurrentScope(scopeexp);
        throw exception;
        return expand(obj, (Translator)Compilation.getCurrent());
    }

    public Object expand(Object obj, Translator translator)
    {
        Object aobj[] = new Object[maxVars];
        Macro macro = (Macro)translator.getCurrentSyntax();
        for (int i = 0; i < rules.length; i++)
        {
            SyntaxRule syntaxrule = rules[i];
            if (syntaxrule == null)
            {
                return new ErrorExp((new StringBuilder()).append("error defining ").append(macro).toString());
            }
            if (syntaxrule.pattern.match(obj, aobj, 0))
            {
                return syntaxrule.execute(aobj, translator, TemplateScope.make(translator));
            }
        }

        return translator.syntaxError((new StringBuilder()).append("no matching syntax-rule for ").append(literal_identifiers[0]).toString());
    }

    public void print(Consumer consumer)
    {
        consumer.write("#<macro ");
        ReportFormat.print(literal_identifiers[0], consumer);
        consumer.write(62);
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        literal_identifiers = (Object[])(Object[])objectinput.readObject();
        rules = (SyntaxRule[])(SyntaxRule[])objectinput.readObject();
        maxVars = objectinput.readInt();
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(((Object) (literal_identifiers)));
        objectoutput.writeObject(rules);
        objectoutput.writeInt(maxVars);
    }
}
