// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.xml.NodeTree;
import gnu.xml.TextUtils;
import gnu.xml.XMLFilter;

// Referenced classes of package gnu.kawa.xml:
//            NodeConstructor, KText

public class MakeText extends NodeConstructor
{

    public static final MakeText makeText = new MakeText();

    public MakeText()
    {
    }

    public static void text$X(Object obj, CallContext callcontext)
    {
        gnu.lists.Consumer consumer;
        XMLFilter xmlfilter;
        if (obj == null || (obj instanceof Values) && ((Values)obj).isEmpty())
        {
            return;
        }
        consumer = callcontext.consumer;
        xmlfilter = NodeConstructor.pushNodeContext(callcontext);
        TextUtils.textValue(obj, xmlfilter);
        NodeConstructor.popNodeContext(consumer, callcontext);
        return;
        Exception exception;
        exception;
        NodeConstructor.popNodeContext(consumer, callcontext);
        throw exception;
    }

    public void apply(CallContext callcontext)
    {
        text$X(callcontext.getNextArg(null), callcontext);
    }

    public Object apply1(Object obj)
    {
        if (obj == null || (obj instanceof Values) && ((Values)obj).isEmpty())
        {
            return obj;
        } else
        {
            NodeTree nodetree = new NodeTree();
            TextUtils.textValue(obj, new XMLFilter(nodetree));
            return KText.make(nodetree);
        }
    }

    public void compile(ApplyExp applyexp, Compilation compilation, Target target)
    {
        ApplyExp.compile(applyexp, compilation, target);
    }

    public void compileToNode(ApplyExp applyexp, Compilation compilation, ConsumerTarget consumertarget)
    {
label0:
        {
            CodeAttr codeattr = compilation.getCode();
            Expression expression = applyexp.getArgs()[0];
            Variable variable = consumertarget.getConsumerVariable();
            if (expression instanceof QuoteExp)
            {
                Object obj = ((QuoteExp)expression).getValue();
                if (obj instanceof String)
                {
                    String s = (String)obj;
                    String s1 = CodeAttr.calculateSplit(s);
                    int i = s1.length();
                    ClassType classtype = (ClassType)variable.getType();
                    Type atype[] = new Type[1];
                    atype[0] = Type.string_type;
                    gnu.bytecode.Method method = classtype.getMethod("write", atype);
                    int j = 0;
                    for (int k = 0; k < i; k++)
                    {
                        codeattr.emitLoad(variable);
                        int l = j + s1.charAt(k);
                        codeattr.emitPushString(s.substring(j, l));
                        codeattr.emitInvoke(method);
                        j = l;
                    }

                    break label0;
                }
            }
            expression.compile(compilation, Target.pushObject);
            codeattr.emitLoad(variable);
            codeattr.emitInvokeStatic(ClassType.make("gnu.xml.TextUtils").getDeclaredMethod("textValue", 2));
        }
    }

    public int numArgs()
    {
        return 4097;
    }

}
