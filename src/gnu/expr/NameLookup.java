// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.kawa.util.GeneralHashTable;
import gnu.kawa.util.HashNode;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.Symbol;

// Referenced classes of package gnu.expr:
//            Declaration, Language, ScopeExp

public class NameLookup extends GeneralHashTable
{

    static final Symbol KEY = Symbol.makeUninterned("<current-NameLookup>");
    Language language;

    public NameLookup(Language language1)
    {
        language = language1;
    }

    public static NameLookup getInstance(Environment environment, Language language1)
    {
        Location location = environment.getLocation(KEY);
        NameLookup namelookup = (NameLookup)location.get(null);
        if (namelookup == null)
        {
            NameLookup namelookup1 = new NameLookup(language1);
            location.set(namelookup1);
            return namelookup1;
        } else
        {
            namelookup.setLanguage(language1);
            return namelookup;
        }
    }

    public static void setInstance(Environment environment, NameLookup namelookup)
    {
        if (namelookup == null)
        {
            environment.remove(KEY);
            return;
        } else
        {
            environment.put(KEY, null, namelookup);
            return;
        }
    }

    public Language getLanguage()
    {
        return language;
    }

    public Declaration lookup(Object obj, int i)
    {
        int j = hashToIndex(hash(obj));
        for (HashNode hashnode = ((HashNode[])table)[j]; hashnode != null; hashnode = hashnode.next)
        {
            Declaration declaration = (Declaration)hashnode.getValue();
            if (obj.equals(declaration.getSymbol()) && language.hasNamespace(declaration, i))
            {
                return declaration;
            }
        }

        return null;
    }

    public Declaration lookup(Object obj, boolean flag)
    {
        byte byte0;
        if (flag)
        {
            byte0 = 2;
        } else
        {
            byte0 = 1;
        }
        return lookup(obj, byte0);
    }

    public void pop(ScopeExp scopeexp)
    {
        for (Declaration declaration = scopeexp.firstDecl(); declaration != null; declaration = declaration.nextDecl())
        {
            pop(declaration);
        }

    }

    public boolean pop(Declaration declaration)
    {
        Object obj = declaration.getSymbol();
        if (obj == null)
        {
            return false;
        }
        int i = hash(obj);
        HashNode hashnode = null;
        int j = hashToIndex(i);
        HashNode hashnode2;
        for (HashNode hashnode1 = ((HashNode[])table)[j]; hashnode1 != null; hashnode1 = hashnode2)
        {
            hashnode2 = hashnode1.next;
            if (hashnode1.getValue() == declaration)
            {
                if (hashnode == null)
                {
                    ((HashNode[])table)[j] = hashnode2;
                } else
                {
                    hashnode.next = hashnode2;
                }
                num_bindings = -1 + num_bindings;
                return true;
            }
            hashnode = hashnode1;
        }

        return false;
    }

    public void push(Declaration declaration)
    {
        Object obj = declaration.getSymbol();
        if (obj == null)
        {
            return;
        }
        int i = 1 + num_bindings;
        num_bindings = i;
        if (i >= ((HashNode[])table).length)
        {
            rehash();
        }
        int j = hash(obj);
        HashNode hashnode = makeEntry(obj, j, declaration);
        int k = hashToIndex(j);
        hashnode.next = ((HashNode[])table)[k];
        ((HashNode[])table)[k] = hashnode;
    }

    public void push(ScopeExp scopeexp)
    {
        for (Declaration declaration = scopeexp.firstDecl(); declaration != null; declaration = declaration.nextDecl())
        {
            push(declaration);
        }

    }

    public void removeSubsumed(Declaration declaration)
    {
        int i = hashToIndex(hash(declaration.getSymbol()));
        HashNode hashnode = null;
        HashNode hashnode1 = ((HashNode[])table)[i];
        while (hashnode1 != null) 
        {
            HashNode hashnode2 = hashnode1.next;
            Declaration declaration1 = (Declaration)hashnode1.getValue();
            if (declaration1 != declaration && subsumedBy(declaration, declaration1))
            {
                if (hashnode == null)
                {
                    ((HashNode[])table)[i] = hashnode2;
                } else
                {
                    hashnode.next = hashnode2;
                }
            } else
            {
                hashnode = hashnode1;
            }
            hashnode1 = hashnode2;
        }
    }

    public void setLanguage(Language language1)
    {
        language = language1;
    }

    protected boolean subsumedBy(Declaration declaration, Declaration declaration1)
    {
        return declaration.getSymbol() == declaration1.getSymbol() && (language.getNamespaceOf(declaration) & language.getNamespaceOf(declaration1)) != 0;
    }

}
