// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.XConsumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import gnu.xml.TextUtils;

// Referenced classes of package gnu.kawa.xml:
//            NodeConstructor

public class CommentConstructor extends MethodProc
{

    public static final CommentConstructor commentConstructor = new CommentConstructor();

    public CommentConstructor()
    {
    }

    public void apply(CallContext callcontext)
    {
        gnu.lists.Consumer consumer;
        gnu.xml.XMLFilter xmlfilter;
        consumer = callcontext.consumer;
        xmlfilter = NodeConstructor.pushNodeContext(callcontext);
        StringBuffer stringbuffer;
        String s;
        stringbuffer = new StringBuffer();
        s = Location.UNBOUND;
        boolean flag;
        int i;
        flag = true;
        i = 0;
_L3:
        Object obj = callcontext.getNextArg(s);
        if (obj != s)
        {
            break MISSING_BLOCK_LABEL_86;
        }
        int j = stringbuffer.length();
        char ac[] = new char[j];
        stringbuffer.getChars(0, j, ac, 0);
        xmlfilter.writeComment(ac, 0, j);
        NodeConstructor.popNodeContext(consumer, callcontext);
        return;
        Values values;
        if (!(obj instanceof Values))
        {
            break MISSING_BLOCK_LABEL_159;
        }
        values = (Values)obj;
        int k = 0;
_L1:
        k = values.nextPos(k);
        if (k == 0)
        {
            break MISSING_BLOCK_LABEL_182;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_131;
        }
        stringbuffer.append(' ');
        TextUtils.stringValue(values.getPosPrevious(k), stringbuffer);
        flag = false;
          goto _L1
        Exception exception;
        exception;
        NodeConstructor.popNodeContext(consumer, callcontext);
        throw exception;
        if (flag)
        {
            break MISSING_BLOCK_LABEL_172;
        }
        stringbuffer.append(' ');
        flag = false;
        TextUtils.stringValue(obj, stringbuffer);
        i++;
        if (true) goto _L3; else goto _L2
_L2:
    }

    public int numArgs()
    {
        return 4097;
    }

}
