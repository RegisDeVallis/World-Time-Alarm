// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Hashtable;

// Referenced classes of package gnu.mapping:
//            HasNamedParts, SymbolRef, SimpleSymbol, Symbol, 
//            Environment

public class Namespace
    implements Externalizable, HasNamedParts
{

    public static final Namespace EmptyNamespace = valueOf("");
    protected static final Hashtable nsTable = new Hashtable(50);
    int log2Size;
    private int mask;
    String name;
    int num_bindings;
    protected String prefix;
    protected SymbolRef table[];

    protected Namespace()
    {
        this(64);
    }

    protected Namespace(int i)
    {
        prefix = "";
        for (log2Size = 4; i > 1 << log2Size; log2Size = 1 + log2Size) { }
        int j = 1 << log2Size;
        table = new SymbolRef[j];
        mask = j - 1;
    }

    public static Namespace create()
    {
        return new Namespace(64);
    }

    public static Namespace create(int i)
    {
        return new Namespace(i);
    }

    public static Namespace getDefault()
    {
        return EmptyNamespace;
    }

    public static Symbol getDefaultSymbol(String s)
    {
        return EmptyNamespace.getSymbol(s);
    }

    public static Namespace makeUnknownNamespace(String s)
    {
        String s1;
        if (s == null || s == "")
        {
            s1 = "";
        } else
        {
            s1 = (new StringBuilder()).append("http://kawa.gnu.org/unknown-namespace/").append(s).toString();
        }
        return valueOf(s1, s);
    }

    public static Namespace valueOf()
    {
        return EmptyNamespace;
    }

    public static Namespace valueOf(String s)
    {
        if (s == null)
        {
            s = "";
        }
        Hashtable hashtable = nsTable;
        hashtable;
        JVM INSTR monitorenter ;
        Namespace namespace = (Namespace)nsTable.get(s);
        if (namespace == null)
        {
            break MISSING_BLOCK_LABEL_32;
        }
        hashtable;
        JVM INSTR monitorexit ;
        return namespace;
        Namespace namespace1;
        namespace1 = new Namespace();
        namespace1.setName(s.intern());
        nsTable.put(s, namespace1);
        hashtable;
        JVM INSTR monitorexit ;
        return namespace1;
        Exception exception;
        exception;
        hashtable;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public static Namespace valueOf(String s, SimpleSymbol simplesymbol)
    {
        String s1;
        if (simplesymbol == null)
        {
            s1 = null;
        } else
        {
            s1 = simplesymbol.getName();
        }
        return valueOf(s, s1);
    }

    public static Namespace valueOf(String s, String s1)
    {
        String s2;
        if (s1 == null || s1.length() == 0)
        {
            return valueOf(s);
        }
        s2 = (new StringBuilder()).append(s1).append(" -> ").append(s).toString();
        Namespace namespace1;
        synchronized (nsTable)
        {
            Object obj = nsTable.get(s2);
            if (!(obj instanceof Namespace))
            {
                break MISSING_BLOCK_LABEL_82;
            }
            namespace1 = (Namespace)obj;
        }
        return namespace1;
        exception;
        hashtable;
        JVM INSTR monitorexit ;
        throw exception;
        Namespace namespace;
        namespace = new Namespace();
        namespace.setName(s.intern());
        namespace.prefix = s1.intern();
        nsTable.put(s2, namespace);
        hashtable;
        JVM INSTR monitorexit ;
        return namespace;
    }

    public Symbol add(Symbol symbol, int i)
    {
        int j = i & mask;
        SymbolRef symbolref = new SymbolRef(symbol, this);
        symbol.namespace = this;
        symbolref.next = table[j];
        table[j] = symbolref;
        num_bindings = 1 + num_bindings;
        if (num_bindings >= table.length)
        {
            rehash();
        }
        return symbol;
    }

    public Object get(String s)
    {
        return Environment.getCurrent().get(getSymbol(s));
    }

    public final String getName()
    {
        return name;
    }

    public final String getPrefix()
    {
        return prefix;
    }

    public Symbol getSymbol(String s)
    {
        return lookup(s, s.hashCode(), true);
    }

    public boolean isConstant(String s)
    {
        return false;
    }

    public Symbol lookup(String s)
    {
        return lookup(s, s.hashCode(), false);
    }

    public Symbol lookup(String s, int i, boolean flag)
    {
        this;
        JVM INSTR monitorenter ;
        Symbol symbol = lookupInternal(s, i);
        if (symbol == null)
        {
            break MISSING_BLOCK_LABEL_20;
        }
        this;
        JVM INSTR monitorexit ;
        return symbol;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_76;
        }
        Object obj;
        if (this != EmptyNamespace)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        obj = new SimpleSymbol(s);
_L1:
        Symbol symbol1 = add(((Symbol) (obj)), i);
        this;
        JVM INSTR monitorexit ;
        return symbol1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        obj = new Symbol(this, s);
          goto _L1
        this;
        JVM INSTR monitorexit ;
        return null;
    }

    protected final Symbol lookupInternal(String s, int i)
    {
        int j = i & mask;
        SymbolRef symbolref = null;
        SymbolRef symbolref1 = table[j];
        while (symbolref1 != null) 
        {
            SymbolRef symbolref2 = symbolref1.next;
            Symbol symbol = symbolref1.getSymbol();
            if (symbol == null)
            {
                if (symbolref == null)
                {
                    table[j] = symbolref2;
                } else
                {
                    symbolref.next = symbolref2;
                }
                num_bindings = -1 + num_bindings;
            } else
            {
                if (symbol.getLocalPart().equals(s))
                {
                    return symbol;
                }
                symbolref = symbolref1;
            }
            symbolref1 = symbolref2;
        }
        return null;
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        name = ((String)objectinput.readObject()).intern();
        prefix = (String)objectinput.readObject();
    }

    public Object readResolve()
        throws ObjectStreamException
    {
        String s = getName();
        if (s != null)
        {
            String s1;
            Namespace namespace;
            if (prefix == null || prefix.length() == 0)
            {
                s1 = s;
            } else
            {
                s1 = (new StringBuilder()).append(prefix).append(" -> ").append(s).toString();
            }
            namespace = (Namespace)nsTable.get(s1);
            if (namespace != null)
            {
                return namespace;
            }
            nsTable.put(s1, this);
        }
        return this;
    }

    protected void rehash()
    {
        int i = table.length;
        int j = i * 2;
        int k = j - 1;
        int l = 0;
        SymbolRef asymbolref[] = table;
        SymbolRef asymbolref1[] = new SymbolRef[j];
        for (int i1 = i; --i1 >= 0;)
        {
            SymbolRef symbolref = asymbolref[i1];
            while (symbolref != null) 
            {
                SymbolRef symbolref1 = symbolref.next;
                Symbol symbol = symbolref.getSymbol();
                if (symbol != null)
                {
                    int j1 = k & symbol.getName().hashCode();
                    l++;
                    symbolref.next = asymbolref1[j1];
                    asymbolref1[j1] = symbolref;
                }
                symbolref = symbolref1;
            }
        }

        table = asymbolref1;
        log2Size = 1 + log2Size;
        mask = k;
        num_bindings = l;
    }

    public boolean remove(Symbol symbol)
    {
        this;
        JVM INSTR monitorenter ;
        int i = symbol.getLocalPart().hashCode() & mask;
        SymbolRef symbolref = null;
        SymbolRef symbolref1 = table[i];
_L4:
        if (symbolref1 == null) goto _L2; else goto _L1
_L1:
        SymbolRef symbolref2;
        Symbol symbol1;
        symbolref2 = symbolref1.next;
        symbol1 = symbolref1.getSymbol();
        if (symbol1 != null && symbol1 != symbol)
        {
            break MISSING_BLOCK_LABEL_103;
        }
        if (symbolref != null)
        {
            break MISSING_BLOCK_LABEL_88;
        }
        table[i] = symbolref2;
_L3:
        num_bindings = -1 + num_bindings;
        if (symbol1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        this;
        JVM INSTR monitorexit ;
        return true;
        symbolref.next = symbolref2;
          goto _L3
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        symbolref = symbolref1;
        symbolref1 = symbolref2;
          goto _L4
_L2:
        this;
        JVM INSTR monitorexit ;
        return false;
    }

    public final void setName(String s)
    {
        name = s;
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder("#,(namespace \"");
        stringbuilder.append(name);
        stringbuilder.append('"');
        if (prefix != null && prefix != "")
        {
            stringbuilder.append(' ');
            stringbuilder.append(prefix);
        }
        stringbuilder.append(')');
        return stringbuilder.toString();
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(getName());
        objectoutput.writeObject(prefix);
    }

}
