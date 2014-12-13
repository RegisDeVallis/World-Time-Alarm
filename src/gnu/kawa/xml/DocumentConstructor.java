// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;

// Referenced classes of package gnu.kawa.xml:
//            NodeConstructor

public class DocumentConstructor extends NodeConstructor
{

    public static final DocumentConstructor documentConstructor = new DocumentConstructor();
    static final Method endDocumentMethod;
    static final Method startDocumentMethod;

    public DocumentConstructor()
    {
    }

    public void apply(CallContext callcontext)
    {
        Consumer consumer;
        gnu.xml.XMLFilter xmlfilter;
        consumer = callcontext.consumer;
        xmlfilter = pushNodeContext(callcontext);
        String s;
        s = Location.UNBOUND;
        xmlfilter.startDocument();
_L1:
        Object obj = callcontext.getNextArg(s);
        if (obj != s)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        xmlfilter.endDocument();
        popNodeContext(consumer, callcontext);
        return;
        if (!(obj instanceof Consumable))
        {
            break MISSING_BLOCK_LABEL_80;
        }
        ((Consumable)obj).consume(xmlfilter);
          goto _L1
        Exception exception;
        exception;
        popNodeContext(consumer, callcontext);
        throw exception;
        xmlfilter.writeObject(obj);
          goto _L1
    }

    public void compileToNode(ApplyExp applyexp, Compilation compilation, ConsumerTarget consumertarget)
    {
        gnu.bytecode.Variable variable = consumertarget.getConsumerVariable();
        gnu.expr.Expression aexpression[] = applyexp.getArgs();
        int i = aexpression.length;
        CodeAttr codeattr = compilation.getCode();
        codeattr.emitLoad(variable);
        codeattr.emitInvokeInterface(startDocumentMethod);
        for (int j = 0; j < i; j++)
        {
            compileChild(aexpression[j], compilation, consumertarget);
        }

        codeattr.emitLoad(variable);
        codeattr.emitInvokeInterface(endDocumentMethod);
    }

    static 
    {
        startDocumentMethod = Compilation.typeConsumer.getDeclaredMethod("startDocument", 0);
        endDocumentMethod = Compilation.typeConsumer.getDeclaredMethod("endDocument", 0);
    }
}
