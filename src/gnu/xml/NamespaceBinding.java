// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.xml;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public final class NamespaceBinding
    implements Externalizable
{

    public static final String XML_NAMESPACE = "http://www.w3.org/XML/1998/namespace";
    public static final NamespaceBinding predefinedXML = new NamespaceBinding("xml", "http://www.w3.org/XML/1998/namespace", null);
    int depth;
    NamespaceBinding next;
    String prefix;
    String uri;

    public NamespaceBinding(String s, String s1, NamespaceBinding namespacebinding)
    {
        prefix = s;
        uri = s1;
        setNext(namespacebinding);
    }

    public static NamespaceBinding commonAncestor(NamespaceBinding namespacebinding, NamespaceBinding namespacebinding1)
    {
        if (namespacebinding.depth > namespacebinding1.depth)
        {
            NamespaceBinding namespacebinding2 = namespacebinding;
            namespacebinding = namespacebinding1;
            namespacebinding1 = namespacebinding2;
        }
        for (; namespacebinding1.depth > namespacebinding.depth; namespacebinding1 = namespacebinding1.next) { }
        for (; namespacebinding != namespacebinding1; namespacebinding1 = namespacebinding1.next)
        {
            namespacebinding = namespacebinding.next;
        }

        return namespacebinding;
    }

    public static NamespaceBinding maybeAdd(String s, String s1, NamespaceBinding namespacebinding)
    {
        if (namespacebinding == null)
        {
            if (s1 == null)
            {
                return namespacebinding;
            }
            namespacebinding = predefinedXML;
        }
        String s2 = namespacebinding.resolve(s);
        if (s2 != null ? s2.equals(s1) : s1 == null)
        {
            return namespacebinding;
        } else
        {
            return new NamespaceBinding(s, s1, namespacebinding);
        }
    }

    public static final NamespaceBinding nconc(NamespaceBinding namespacebinding, NamespaceBinding namespacebinding1)
    {
        if (namespacebinding == null)
        {
            return namespacebinding1;
        } else
        {
            namespacebinding.setNext(nconc(namespacebinding.next, namespacebinding1));
            return namespacebinding;
        }
    }

    public int count(NamespaceBinding namespacebinding)
    {
        int i = 0;
        for (NamespaceBinding namespacebinding1 = this; namespacebinding1 != namespacebinding; namespacebinding1 = namespacebinding1.next)
        {
            i++;
        }

        return i;
    }

    public final NamespaceBinding getNext()
    {
        return next;
    }

    public final String getPrefix()
    {
        return prefix;
    }

    public final String getUri()
    {
        return uri;
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        prefix = objectinput.readUTF();
        uri = objectinput.readUTF();
        next = (NamespaceBinding)objectinput.readObject();
    }

    public String resolve(String s)
    {
        for (NamespaceBinding namespacebinding = this; namespacebinding != null; namespacebinding = namespacebinding.next)
        {
            if (namespacebinding.prefix == s)
            {
                return namespacebinding.uri;
            }
        }

        return null;
    }

    public String resolve(String s, NamespaceBinding namespacebinding)
    {
        for (NamespaceBinding namespacebinding1 = this; namespacebinding1 != namespacebinding; namespacebinding1 = namespacebinding1.next)
        {
            if (namespacebinding1.prefix == s)
            {
                return namespacebinding1.uri;
            }
        }

        return null;
    }

    public NamespaceBinding reversePrefix(NamespaceBinding namespacebinding)
    {
        NamespaceBinding namespacebinding1 = namespacebinding;
        NamespaceBinding namespacebinding2 = this;
        int i;
        NamespaceBinding namespacebinding3;
        if (namespacebinding == null)
        {
            i = -1;
        } else
        {
            i = namespacebinding.depth;
        }
        for (; namespacebinding2 != namespacebinding; namespacebinding2 = namespacebinding3)
        {
            namespacebinding3 = namespacebinding2.next;
            namespacebinding2.next = namespacebinding1;
            namespacebinding1 = namespacebinding2;
            i++;
            namespacebinding2.depth = i;
        }

        return namespacebinding1;
    }

    public final void setNext(NamespaceBinding namespacebinding)
    {
        next = namespacebinding;
        int i;
        if (namespacebinding == null)
        {
            i = 0;
        } else
        {
            i = 1 + namespacebinding.depth;
        }
        depth = i;
    }

    public final void setPrefix(String s)
    {
        prefix = s;
    }

    public final void setUri(String s)
    {
        uri = s;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Namespace{").append(prefix).append("=").append(uri).append(", depth:").append(depth).append("}").toString();
    }

    public String toStringAll()
    {
        StringBuffer stringbuffer = new StringBuffer("Namespaces{");
        NamespaceBinding namespacebinding = this;
        while (namespacebinding != null) 
        {
            stringbuffer.append(namespacebinding.prefix);
            stringbuffer.append("=\"");
            stringbuffer.append(namespacebinding.uri);
            String s;
            if (namespacebinding == null)
            {
                s = "\"";
            } else
            {
                s = "\", ";
            }
            stringbuffer.append(s);
            namespacebinding = namespacebinding.next;
        }
        stringbuffer.append('}');
        return stringbuffer.toString();
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeUTF(prefix);
        objectoutput.writeUTF(uri);
        objectoutput.writeObject(next);
    }

}
