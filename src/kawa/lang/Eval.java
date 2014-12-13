// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.NameLookup;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1or2;
import gnu.text.SourceMessages;
import java.util.Stack;

// Referenced classes of package kawa.lang:
//            Translator

public class Eval extends Procedure1or2
{

    public static final Eval eval;
    static final String evalFunctionName = "atEvalLevel$";

    public Eval()
    {
    }

    public static Object eval(Object obj, Environment environment)
        throws Throwable
    {
        CallContext callcontext = CallContext.getInstance();
        int i = callcontext.startFromContext();
        Object obj1;
        try
        {
            eval(obj, environment, callcontext);
            obj1 = callcontext.getFromContext(i);
        }
        catch (Throwable throwable)
        {
            callcontext.cleanupFromContext(i);
            throw throwable;
        }
        return obj1;
    }

    public static void eval(Object obj, Environment environment, CallContext callcontext)
        throws Throwable
    {
        PairWithPosition pairwithposition;
        if (obj instanceof PairWithPosition)
        {
            pairwithposition = new PairWithPosition((PairWithPosition)obj, obj, LList.Empty);
        } else
        {
            pairwithposition = new PairWithPosition(obj, LList.Empty);
            pairwithposition.setFile("<eval>");
        }
        evalBody(pairwithposition, environment, new SourceMessages(), callcontext);
    }

    public static Object evalBody(Object obj, Environment environment, SourceMessages sourcemessages)
        throws Throwable
    {
        CallContext callcontext = CallContext.getInstance();
        int i = callcontext.startFromContext();
        Object obj1;
        try
        {
            evalBody(obj, environment, sourcemessages, callcontext);
            obj1 = callcontext.getFromContext(i);
        }
        catch (Throwable throwable)
        {
            callcontext.cleanupFromContext(i);
            throw throwable;
        }
        return obj1;
    }

    public static void evalBody(Object obj, Environment environment, SourceMessages sourcemessages, CallContext callcontext)
        throws Throwable
    {
        Language language;
        Environment environment1;
        language = Language.getDefaultLanguage();
        environment1 = Environment.getCurrent();
        if (environment == environment1)
        {
            break MISSING_BLOCK_LABEL_20;
        }
        Environment.setCurrent(environment);
        Translator translator;
        ModuleExp moduleexp;
        Compilation compilation;
        translator = new Translator(language, sourcemessages, NameLookup.getInstance(environment, language));
        translator.immediate = true;
        translator.setState(3);
        translator.setSharedModuleDefs(true);
        moduleexp = translator.pushNewModule((String)null);
        compilation = Compilation.setSaveCurrent(translator);
        int i = translator.formStack.size();
        translator.scanBody(obj, moduleexp, false);
        translator.firstForm = i;
        translator.finishModule(moduleexp);
        Compilation.restoreCurrent(compilation);
        if (obj instanceof PairWithPosition)
        {
            moduleexp.setFile(((PairWithPosition)obj).getFileName());
        }
        StringBuilder stringbuilder = (new StringBuilder()).append("atEvalLevel$");
        int j = 1 + ModuleExp.interactiveCounter;
        ModuleExp.interactiveCounter = j;
        moduleexp.setName(stringbuilder.append(j).toString());
        ModuleExp.evalModule(environment, callcontext, translator, null, null);
        if (sourcemessages.seenErrors())
        {
            throw new RuntimeException((new StringBuilder()).append("invalid syntax in eval form:\n").append(sourcemessages.toString(20)).toString());
        }
        break MISSING_BLOCK_LABEL_248;
        Exception exception;
        exception;
        if (environment != environment1)
        {
            Environment.setCurrent(environment1);
        }
        throw exception;
        Exception exception1;
        exception1;
        Compilation.restoreCurrent(compilation);
        throw exception1;
        if (environment != environment1)
        {
            Environment.setCurrent(environment1);
        }
        return;
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        Procedure.checkArgCount(this, callcontext.count);
        Object obj = callcontext.getNextArg();
        Environment environment = (Environment)callcontext.getNextArg(null);
        if (environment == null)
        {
            environment = Environment.user();
        }
        callcontext.lastArg();
        eval(obj, environment, callcontext);
    }

    public Object apply1(Object obj)
        throws Throwable
    {
        return eval(obj, Environment.user());
    }

    public Object apply2(Object obj, Object obj1)
        throws Throwable
    {
        return eval(obj, (Environment)obj1);
    }

    static 
    {
        eval = new Eval();
        eval.setName("eval");
    }
}
