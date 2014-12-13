// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.lists.Consumer;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import gnu.text.Printable;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

// Referenced classes of package gnu.expr:
//            Special, Symbols

public class Keyword extends Symbol
    implements Printable, Externalizable
{

    public static final Namespace keywordNamespace;

    public Keyword()
    {
    }

    public Keyword(Namespace namespace, String s)
    {
        super(namespace, s);
    }

    private Keyword(String s)
    {
        super(keywordNamespace, s);
    }

    public static boolean isKeyword(Object obj)
    {
        return obj instanceof Keyword;
    }

    public static Keyword make(String s)
    {
        int i = s.hashCode();
        Keyword keyword = (Keyword)keywordNamespace.lookup(s, i, false);
        if (keyword == null)
        {
            keyword = new Keyword(s);
            keywordNamespace.add(keyword, i);
        }
        return keyword;
    }

    public static Object searchForKeyword(Object aobj[], int i, Object obj)
    {
        for (int j = i; j < aobj.length; j += 2)
        {
            if (aobj[j] == obj)
            {
                return aobj[j + 1];
            }
        }

        return Special.dfault;
    }

    public static Object searchForKeyword(Object aobj[], int i, Object obj, Object obj1)
    {
        int j = i;
        do
        {
label0:
            {
                if (j < aobj.length)
                {
                    if (aobj[j] != obj)
                    {
                        break label0;
                    }
                    obj1 = aobj[j + 1];
                }
                return obj1;
            }
            j += 2;
        } while (true);
    }

    public Symbol asSymbol()
    {
        return Namespace.EmptyNamespace.getSymbol(getName());
    }

    public void print(Consumer consumer)
    {
        Symbols.print(getName(), consumer);
        consumer.write(58);
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        name = (String)objectinput.readObject();
    }

    public Object readResolve()
        throws ObjectStreamException
    {
        return make(keywordNamespace, getName());
    }

    public final String toString()
    {
        return (new StringBuilder()).append(getName()).append(':').toString();
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(getName());
    }

    static 
    {
        keywordNamespace = Namespace.create();
        keywordNamespace.setName("(keywords)");
    }
}
