// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

// Referenced classes of package gnu.mapping:
//            EnvironmentKey, Namespace, SimpleSymbol

public class Symbol
    implements EnvironmentKey, Comparable, Externalizable
{

    public static final Symbol FUNCTION = makeUninterned("(function)");
    public static final Symbol PLIST = makeUninterned("(property-list)");
    protected String name;
    Namespace namespace;

    public Symbol()
    {
    }

    public Symbol(Namespace namespace1, String s)
    {
        name = s;
        namespace = namespace1;
    }

    public static boolean equals(Symbol symbol, Symbol symbol1)
    {
        if (symbol != symbol1) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (symbol == null || symbol1 == null)
        {
            return false;
        }
        if (symbol.name != symbol1.name)
        {
            break; /* Loop/switch isn't completed */
        }
        Namespace namespace1 = symbol.namespace;
        Namespace namespace2 = symbol1.namespace;
        if (namespace1 == null || namespace2 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (namespace1.name != namespace2.name)
        {
            return false;
        }
        if (true) goto _L1; else goto _L3
_L3:
        return false;
    }

    public static Symbol make(Object obj, String s)
    {
        Namespace namespace1;
        if (obj instanceof String)
        {
            namespace1 = Namespace.valueOf((String)obj);
        } else
        {
            namespace1 = (Namespace)obj;
        }
        if (namespace1 == null || s == null)
        {
            return makeUninterned(s);
        } else
        {
            return namespace1.getSymbol(s.intern());
        }
    }

    public static Symbol make(String s, String s1, String s2)
    {
        return Namespace.valueOf(s, s2).getSymbol(s1.intern());
    }

    public static Symbol makeUninterned(String s)
    {
        return new Symbol(null, s);
    }

    public static Symbol makeWithUnknownNamespace(String s, String s1)
    {
        return Namespace.makeUnknownNamespace(s1).getSymbol(s.intern());
    }

    public static Symbol parse(String s)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        i = s.length();
        j = -1;
        k = -1;
        l = 0;
        i1 = 0;
        j1 = 0;
_L2:
        int k1;
        k1 = 0;
        if (j1 < i)
        {
            char c = s.charAt(j1);
            String s1;
            if (c == ':' && l == 0)
            {
                i1 = j1;
                k1 = j1 + 1;
            } else
            {
label0:
                {
                    if (c == '{')
                    {
                        if (j < 0)
                        {
                            i1 = j1;
                            j = j1;
                        }
                        l++;
                    }
                    if (c != '}')
                    {
                        break label0;
                    }
                    if (--l == 0)
                    {
                        k = j1;
                        if (j1 < i && s.charAt(j1 + 1) == ':')
                        {
                            k1 = j1 + 2;
                        } else
                        {
                            k1 = j1 + 1;
                        }
                    } else
                    {
                        if (l >= 0)
                        {
                            break label0;
                        }
                        k1 = i1;
                    }
                }
            }
        }
        if (j >= 0 && k > 0)
        {
            s1 = s.substring(j + 1, k);
            String s2;
            if (i1 > 0)
            {
                s2 = s.substring(0, i1);
            } else
            {
                s2 = null;
            }
            return valueOf(s.substring(k1), s1, s2);
        }
        break; /* Loop/switch isn't completed */
        j1++;
        if (true) goto _L2; else goto _L1
_L1:
        if (i1 > 0)
        {
            return makeWithUnknownNamespace(s.substring(k1), s.substring(0, i1));
        } else
        {
            return valueOf(s);
        }
    }

    public static SimpleSymbol valueOf(String s)
    {
        return (SimpleSymbol)Namespace.EmptyNamespace.getSymbol(s.intern());
    }

    public static Symbol valueOf(String s, Object obj)
    {
        if (obj == null || obj == Boolean.FALSE)
        {
            return makeUninterned(s);
        }
        Namespace namespace1;
        if (obj instanceof Namespace)
        {
            namespace1 = (Namespace)obj;
        } else
        if (obj == Boolean.TRUE)
        {
            namespace1 = Namespace.EmptyNamespace;
        } else
        {
            namespace1 = Namespace.valueOf(((CharSequence)obj).toString());
        }
        return namespace1.getSymbol(s.intern());
    }

    public static Symbol valueOf(String s, String s1, String s2)
    {
        return Namespace.valueOf(s1, s2).getSymbol(s.intern());
    }

    public int compareTo(Object obj)
    {
        Symbol symbol = (Symbol)obj;
        if (getNamespaceURI() != symbol.getNamespaceURI())
        {
            throw new IllegalArgumentException("comparing Symbols in different namespaces");
        } else
        {
            return getLocalName().compareTo(symbol.getLocalName());
        }
    }

    public final boolean equals(Object obj)
    {
        return (obj instanceof Symbol) && equals(this, (Symbol)obj);
    }

    public final Object getKeyProperty()
    {
        return null;
    }

    public final Symbol getKeySymbol()
    {
        return this;
    }

    public final String getLocalName()
    {
        return name;
    }

    public final String getLocalPart()
    {
        return name;
    }

    public final String getName()
    {
        return name;
    }

    public final Namespace getNamespace()
    {
        return namespace;
    }

    public final String getNamespaceURI()
    {
        Namespace namespace1 = getNamespace();
        if (namespace1 == null)
        {
            return null;
        } else
        {
            return namespace1.getName();
        }
    }

    public final String getPrefix()
    {
        Namespace namespace1 = namespace;
        if (namespace1 == null)
        {
            return "";
        } else
        {
            return namespace1.prefix;
        }
    }

    public final boolean hasEmptyNamespace()
    {
label0:
        {
            Namespace namespace1 = getNamespace();
            if (namespace1 != null)
            {
                String s = namespace1.getName();
                if (s != null && s.length() != 0)
                {
                    break label0;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode()
    {
        if (name == null)
        {
            return 0;
        } else
        {
            return name.hashCode();
        }
    }

    public boolean matches(EnvironmentKey environmentkey)
    {
        return equals(environmentkey.getKeySymbol(), this) && environmentkey.getKeyProperty() == null;
    }

    public boolean matches(Symbol symbol, Object obj)
    {
        return equals(symbol, this) && obj == null;
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        namespace = (Namespace)objectinput.readObject();
        name = (String)objectinput.readObject();
    }

    public Object readResolve()
        throws ObjectStreamException
    {
        if (namespace == null)
        {
            return this;
        } else
        {
            return make(namespace, getName());
        }
    }

    public final void setNamespace(Namespace namespace1)
    {
        namespace = namespace1;
    }

    public String toString()
    {
        return toString('P');
    }

    public String toString(char c)
    {
        boolean flag = true;
        String s = getNamespaceURI();
        String s1 = getPrefix();
        boolean flag1;
        String s2;
        if (s != null && s.length() > 0)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        if (s1 == null || s1.length() <= 0)
        {
            flag = false;
        }
        s2 = getName();
        if (flag1 || flag)
        {
            StringBuilder stringbuilder = new StringBuilder();
            if (flag && (c != 'U' || !flag1))
            {
                stringbuilder.append(s1);
            }
            if (flag1 && (c != 'P' || !flag))
            {
                stringbuilder.append('{');
                stringbuilder.append(getNamespaceURI());
                stringbuilder.append('}');
            }
            stringbuilder.append(':');
            stringbuilder.append(s2);
            s2 = stringbuilder.toString();
        }
        return s2;
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(getNamespace());
        objectoutput.writeObject(getName());
    }

}
