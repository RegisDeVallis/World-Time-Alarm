// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.xml;

import gnu.mapping.Symbol;

// Referenced classes of package gnu.xml:
//            NamespaceBinding, XName

final class MappingInfo
{

    int index;
    String local;
    NamespaceBinding namespaces;
    MappingInfo nextInBucket;
    String prefix;
    Symbol qname;
    int tagHash;
    XName type;
    String uri;

    MappingInfo()
    {
        index = -1;
    }

    static boolean equals(String s, StringBuffer stringbuffer)
    {
        int i = stringbuffer.length();
        if (s.length() == i) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int j = 0;
label0:
        do
        {
label1:
            {
                if (j >= i)
                {
                    break label1;
                }
                if (stringbuffer.charAt(j) != s.charAt(j))
                {
                    break label0;
                }
                j++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return true;
    }

    static boolean equals(String s, char ac[], int i, int j)
    {
        if (s.length() == j) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int k = 0;
label0:
        do
        {
label1:
            {
                if (k >= j)
                {
                    break label1;
                }
                if (ac[i + k] != s.charAt(k))
                {
                    break label0;
                }
                k++;
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        return true;
    }

    static int hash(String s, String s1)
    {
        int i = s1.hashCode();
        if (s != null)
        {
            i ^= s.hashCode();
        }
        return i;
    }

    static int hash(char ac[], int i, int j)
    {
        int k = 0;
        int l = 0;
        int i1 = -1;
        int j1 = 0;
        while (j1 < j) 
        {
            char c = ac[i + j1];
            if (c == ':' && i1 < 0)
            {
                i1 = j1;
                l = k;
                k = 0;
            } else
            {
                k = c + k * 31;
            }
            j1++;
        }
        return l ^ k;
    }

    boolean match(char ac[], int i, int j)
    {
        if (prefix != null)
        {
            int k = local.length();
            int l = prefix.length();
            return j == k + (l + 1) && ac[l] == ':' && equals(prefix, ac, i, l) && equals(local, ac, 1 + (i + l), k);
        } else
        {
            return equals(local, ac, i, j);
        }
    }
}
