// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.Special;
import gnu.expr.Target;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Symbol;
import gnu.xml.NamespaceBinding;
import gnu.xml.XMLFilter;
import gnu.xml.XName;

// Referenced classes of package gnu.kawa.xml:
//            NodeConstructor, MakeAttribute

public class MakeElement extends NodeConstructor
{

    static final Method endElementMethod;
    public static final MakeElement makeElement = new MakeElement();
    static final Method startElementMethod3;
    static final Method startElementMethod4;
    static final ClassType typeMakeElement;
    public int copyNamespacesMode;
    private boolean handlingKeywordParameters;
    NamespaceBinding namespaceNodes;
    public Symbol tag;

    public MakeElement()
    {
        copyNamespacesMode = 1;
    }

    public static void endElement(Consumer consumer, Object obj)
    {
        consumer.endElement();
    }

    public static Symbol getTagName(ApplyExp applyexp)
    {
        Expression aexpression[] = applyexp.getArgs();
        if (aexpression.length > 0)
        {
            Expression expression = aexpression[0];
            if (expression instanceof QuoteExp)
            {
                Object obj = ((QuoteExp)expression).getValue();
                if (obj instanceof Symbol)
                {
                    return (Symbol)obj;
                }
            }
        }
        return null;
    }

    public static void startElement(Consumer consumer, Object obj, int i)
    {
        Symbol symbol;
        if (obj instanceof Symbol)
        {
            symbol = (Symbol)obj;
        } else
        {
            symbol = Symbol.make("", obj.toString(), "");
        }
        if (consumer instanceof XMLFilter)
        {
            ((XMLFilter)consumer).copyNamespacesMode = i;
        }
        consumer.startElement(symbol);
    }

    public static void startElement(Consumer consumer, Object obj, int i, NamespaceBinding namespacebinding)
    {
        XName xname;
        if (obj instanceof Symbol)
        {
            xname = new XName((Symbol)obj, namespacebinding);
        } else
        {
            xname = new XName(Symbol.make("", obj.toString(), ""), namespacebinding);
        }
        if (consumer instanceof XMLFilter)
        {
            ((XMLFilter)consumer).copyNamespacesMode = i;
        }
        consumer.startElement(xname);
    }

    public void apply(CallContext callcontext)
    {
        Consumer consumer;
        XMLFilter xmlfilter;
        consumer = callcontext.consumer;
        xmlfilter = pushNodeContext(callcontext);
        if (tag == null) goto _L2; else goto _L1
_L1:
        Object obj = tag;
_L7:
        if (namespaceNodes == null) goto _L4; else goto _L3
_L3:
        startElement(xmlfilter, obj, copyNamespacesMode, namespaceNodes);
_L8:
        Special special = Special.dfault;
_L9:
        Object obj1 = callcontext.getNextArg(special);
        if (obj1 != special) goto _L6; else goto _L5
_L5:
        endElement(xmlfilter, obj);
        popNodeContext(consumer, callcontext);
        return;
_L2:
        obj = callcontext.getNextArg();
          goto _L7
_L4:
        startElement(xmlfilter, obj, copyNamespacesMode);
          goto _L8
        Exception exception;
        exception;
        popNodeContext(consumer, callcontext);
        throw exception;
_L6:
        if (!(obj1 instanceof Consumable))
        {
            break MISSING_BLOCK_LABEL_143;
        }
        ((Consumable)obj1).consume(xmlfilter);
_L10:
        if (isHandlingKeywordParameters())
        {
            xmlfilter.endAttribute();
        }
          goto _L9
        callcontext.writeValue(obj1);
          goto _L10
    }

    public void compileToNode(ApplyExp applyexp, Compilation compilation, ConsumerTarget consumertarget)
    {
        gnu.bytecode.Variable variable = consumertarget.getConsumerVariable();
        Expression aexpression[] = applyexp.getArgs();
        int i = aexpression.length;
        CodeAttr codeattr = compilation.getCode();
        codeattr.emitLoad(variable);
        codeattr.emitDup();
        int j;
        if (tag == null)
        {
            aexpression[0].compile(compilation, Target.pushObject);
            j = 1;
        } else
        {
            compilation.compileConstant(tag, Target.pushObject);
            j = 0;
        }
        codeattr.emitDup(1, 1);
        codeattr.emitPushInt(copyNamespacesMode);
        if (namespaceNodes != null)
        {
            compilation.compileConstant(namespaceNodes, Target.pushObject);
            codeattr.emitInvokeStatic(startElementMethod4);
        } else
        {
            codeattr.emitInvokeStatic(startElementMethod3);
        }
        for (; j < i; j++)
        {
            compileChild(aexpression[j], compilation, consumertarget);
            if (isHandlingKeywordParameters())
            {
                codeattr.emitLoad(variable);
                codeattr.emitInvokeInterface(MakeAttribute.endAttributeMethod);
            }
        }

        codeattr.emitInvokeStatic(endElementMethod);
    }

    public NamespaceBinding getNamespaceNodes()
    {
        return namespaceNodes;
    }

    public Type getReturnType(Expression aexpression[])
    {
        return Compilation.typeObject;
    }

    public boolean isHandlingKeywordParameters()
    {
        return handlingKeywordParameters;
    }

    public int numArgs()
    {
        return tag != null ? -4096 : -4095;
    }

    public void setHandlingKeywordParameters(boolean flag)
    {
        handlingKeywordParameters = flag;
    }

    public void setNamespaceNodes(NamespaceBinding namespacebinding)
    {
        namespaceNodes = namespacebinding;
    }

    public String toString()
    {
        return (new StringBuilder()).append("makeElement[").append(tag).append("]").toString();
    }

    static 
    {
        typeMakeElement = ClassType.make("gnu.kawa.xml.MakeElement");
        startElementMethod3 = typeMakeElement.getDeclaredMethod("startElement", 3);
        startElementMethod4 = typeMakeElement.getDeclaredMethod("startElement", 4);
        endElementMethod = typeMakeElement.getDeclaredMethod("endElement", 2);
    }
}
