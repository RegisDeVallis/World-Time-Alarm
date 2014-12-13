// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.xml;

import gnu.kawa.xml.KNode;
import gnu.lists.Consumer;
import gnu.lists.TreeList;
import gnu.mapping.Values;
import gnu.math.DFloNum;
import java.math.BigDecimal;

// Referenced classes of package gnu.xml:
//            NodeTree, XMLPrinter

public class TextUtils
{

    public TextUtils()
    {
    }

    public static String asString(Object obj)
    {
        if (obj == Values.empty || obj == null)
        {
            return "";
        }
        if (obj instanceof Values)
        {
            throw new ClassCastException();
        } else
        {
            StringBuffer stringbuffer = new StringBuffer(100);
            stringValue(obj, stringbuffer);
            return stringbuffer.toString();
        }
    }

    public static String replaceWhitespace(String s, boolean flag)
    {
        StringBuilder stringbuilder = null;
        int i = s.length();
        int j;
        int k;
        if (flag)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        k = 0;
        do
        {
            if (k >= i)
            {
                break;
            }
            int l = k + 1;
            char c = s.charAt(k);
            int i1;
            if (c == ' ')
            {
                i1 = 1;
            } else
            if (c == '\t' || c == '\r' || c == '\n')
            {
                i1 = 2;
            } else
            {
                i1 = 0;
            }
            if (stringbuilder == null && (i1 == 2 || i1 == 1 && j > 0 && flag || i1 == 1 && l == i && flag))
            {
                stringbuilder = new StringBuilder();
                int j1;
                int k1;
                if (j > 0)
                {
                    j1 = l - 2;
                } else
                {
                    j1 = l - 1;
                }
                for (k1 = 0; k1 < j1; k1++)
                {
                    stringbuilder.append(s.charAt(k1));
                }

                c = ' ';
            }
            if (flag)
            {
                if (j > 0 && i1 == 0)
                {
                    if (stringbuilder != null && stringbuilder.length() > 0)
                    {
                        stringbuilder.append(' ');
                    }
                    j = 0;
                } else
                if (i1 == 2 || i1 == 1 && j > 0)
                {
                    j = 2;
                } else
                if (i1 > 0)
                {
                    j = 1;
                } else
                {
                    j = 0;
                }
                if (j > 0)
                {
                    k = l;
                    continue;
                }
            }
            if (stringbuilder != null)
            {
                stringbuilder.append(c);
            }
            k = l;
        } while (true);
        if (stringbuilder != null)
        {
            s = stringbuilder.toString();
        }
        return s;
    }

    public static String stringValue(Object obj)
    {
        StringBuffer stringbuffer;
        TreeList treelist;
        int i;
        stringbuffer = new StringBuffer(100);
        if (!(obj instanceof Values))
        {
            break MISSING_BLOCK_LABEL_80;
        }
        treelist = (TreeList)obj;
        i = 0;
_L3:
        int j = treelist.getNextKind(i);
        if (j != 0) goto _L2; else goto _L1
_L1:
        return stringbuffer.toString();
_L2:
        if (j == 32)
        {
            stringValue(treelist.getPosNext(i), stringbuffer);
        } else
        {
            treelist.stringValue(treelist.posToDataIndex(i), stringbuffer);
        }
        i = treelist.nextPos(i);
          goto _L3
        stringValue(obj, stringbuffer);
          goto _L1
    }

    public static void stringValue(Object obj, StringBuffer stringbuffer)
    {
        if (!(obj instanceof KNode)) goto _L2; else goto _L1
_L1:
        KNode knode = (KNode)obj;
        NodeTree nodetree = (NodeTree)knode.sequence;
        nodetree.stringValue(nodetree.posToDataIndex(knode.ipos), stringbuffer);
_L4:
        return;
_L2:
        if (!(obj instanceof BigDecimal))
        {
            break; /* Loop/switch isn't completed */
        }
        obj = XMLPrinter.formatDecimal((BigDecimal)obj);
_L5:
        if (obj != null && obj != Values.empty)
        {
            stringbuffer.append(obj);
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        if ((obj instanceof Double) || (obj instanceof DFloNum))
        {
            obj = XMLPrinter.formatDouble(((Number)obj).doubleValue());
        } else
        if (obj instanceof Float)
        {
            obj = XMLPrinter.formatFloat(((Number)obj).floatValue());
        }
          goto _L5
        if (true) goto _L4; else goto _L6
_L6:
    }

    public static void textValue(Object obj, Consumer consumer)
    {
        if (obj == null || (obj instanceof Values) && ((Values)obj).isEmpty())
        {
            return;
        }
        String s;
        if (obj instanceof String)
        {
            s = (String)obj;
        } else
        {
            StringBuffer stringbuffer = new StringBuffer();
            if (obj instanceof Values)
            {
                Object aobj[] = ((Values)obj).getValues();
                for (int i = 0; i < aobj.length; i++)
                {
                    if (i > 0)
                    {
                        stringbuffer.append(' ');
                    }
                    stringValue(aobj[i], stringbuffer);
                }

            } else
            {
                stringValue(obj, stringbuffer);
            }
            s = stringbuffer.toString();
        }
        consumer.write(s);
    }
}
