// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.xml;

import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Referenced classes of package gnu.xml:
//            NamespaceBinding

public class XName extends Symbol
    implements Externalizable
{

    NamespaceBinding namespaceNodes;

    public XName()
    {
    }

    public XName(Symbol symbol, NamespaceBinding namespacebinding)
    {
        super(symbol.getNamespace(), symbol.getName());
        namespaceNodes = namespacebinding;
    }

    public static int checkName(String s)
    {
        int i = s.length();
        byte byte0;
        if (i == 0)
        {
            byte0 = -1;
        } else
        {
            byte0 = 2;
            int j = 0;
            while (j < i) 
            {
                boolean flag;
                int k;
                int l;
                if (j == 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                k = j + 1;
                l = s.charAt(j);
                if (l >= 55296 && l < 56320 && k < i)
                {
                    int i1 = 1024 * (l - 55296);
                    int j1 = k + 1;
                    l = 0x10000 + (i1 + (s.charAt(k) - 56320));
                    k = j1;
                }
                if (l == 58)
                {
                    if (byte0 == 2)
                    {
                        byte0 = 1;
                    }
                } else
                {
                    if (!isNamePart(l))
                    {
                        return -1;
                    }
                    if (flag && !isNameStart(l))
                    {
                        byte0 = 0;
                    }
                }
                j = k;
            }
        }
        return byte0;
    }

    public static boolean isNCName(String s)
    {
        return checkName(s) > 1;
    }

    public static boolean isName(String s)
    {
        return checkName(s) > 0;
    }

    public static boolean isNamePart(int i)
    {
        return Character.isUnicodeIdentifierPart(i) || i == 45 || i == 46;
    }

    public static boolean isNameStart(int i)
    {
        return Character.isUnicodeIdentifierStart(i) || i == 95;
    }

    public static boolean isNmToken(String s)
    {
        return checkName(s) >= 0;
    }

    public final NamespaceBinding getNamespaceNodes()
    {
        return namespaceNodes;
    }

    String lookupNamespaceURI(String s)
    {
        for (NamespaceBinding namespacebinding = namespaceNodes; namespacebinding != null; namespacebinding = namespacebinding.next)
        {
            if (s == namespacebinding.prefix)
            {
                return namespacebinding.uri;
            }
        }

        return null;
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        super.readExternal(objectinput);
        namespaceNodes = (NamespaceBinding)objectinput.readObject();
    }

    public final void setNamespaceNodes(NamespaceBinding namespacebinding)
    {
        namespaceNodes = namespacebinding;
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        super.writeExternal(objectoutput);
        objectoutput.writeObject(namespaceNodes);
    }
}
