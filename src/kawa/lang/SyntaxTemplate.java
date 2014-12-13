// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.expr.Compilation;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.IdentityHashMap;
import java.util.Vector;

// Referenced classes of package kawa.lang:
//            Translator, PatternScope, SyntaxForm, SyntaxForms, 
//            TemplateScope

public class SyntaxTemplate
    implements Externalizable
{

    static final int BUILD_CONS = 1;
    static final int BUILD_DOTS = 5;
    static final int BUILD_LIST1 = 8;
    static final int BUILD_LITERAL = 4;
    static final int BUILD_MISC = 0;
    static final int BUILD_NIL = 16;
    static final int BUILD_SYNTAX = 24;
    static final int BUILD_VAR = 2;
    static final int BUILD_VAR_CAR = 3;
    static final int BUILD_VECTOR = 40;
    static final int BUILD_WIDE = 7;
    static final String dots3 = "...";
    Object literal_values[];
    int max_nesting;
    String patternNesting;
    String template_program;

    protected SyntaxTemplate()
    {
    }

    public SyntaxTemplate(Object obj, SyntaxForm syntaxform, Translator translator)
    {
        String s;
        StringBuffer stringbuffer;
        Vector vector;
        if (translator == null || translator.patternScope == null)
        {
            s = "";
        } else
        {
            s = translator.patternScope.patternNesting.toString();
        }
        patternNesting = s;
        stringbuffer = new StringBuffer();
        vector = new Vector();
        convert_template(obj, syntaxform, stringbuffer, 0, vector, new IdentityHashMap(), false, translator);
        template_program = stringbuffer.toString();
        literal_values = new Object[vector.size()];
        vector.copyInto(literal_values);
    }

    public SyntaxTemplate(String s, String s1, Object aobj[], int i)
    {
        patternNesting = s;
        template_program = s1;
        literal_values = aobj;
        max_nesting = i;
    }

    private int get_count(Object obj, int i, int ai[])
    {
        for (int j = 0; j < i; j++)
        {
            obj = ((Object[])(Object[])obj)[ai[j]];
        }

        return ((Object[])(Object[])obj).length;
    }

    static int indexOf(Vector vector, Object obj)
    {
        int i = vector.size();
        for (int j = 0; j < i; j++)
        {
            if (vector.elementAt(j) == obj)
            {
                return j;
            }
        }

        return -1;
    }

    public int convert_template(Object obj, SyntaxForm syntaxform, StringBuffer stringbuffer, int i, Vector vector, Object obj1, boolean flag, 
            Translator translator)
    {
        for (; obj instanceof SyntaxForm; obj = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj;
        }

        if (!(obj instanceof Pair) && !(obj instanceof FVector)) goto _L2; else goto _L1
_L1:
        IdentityHashMap identityhashmap = (IdentityHashMap)obj1;
        if (!identityhashmap.containsKey(obj)) goto _L4; else goto _L3
_L3:
        int j2;
        translator.syntaxError("self-referential (cyclic) syntax template");
        j2 = -2;
_L10:
        return j2;
_L4:
        identityhashmap.put(obj, obj);
_L2:
        Pair pair;
        int i1;
        int j1;
        Object obj2;
        if (!(obj instanceof Pair))
        {
            break MISSING_BLOCK_LABEL_538;
        }
        pair = (Pair)obj;
        i1 = -2;
        j1 = stringbuffer.length();
        obj2 = pair.getCar();
        if (!translator.matches(obj2, "...")) goto _L6; else goto _L5
_L5:
        Object obj4 = Translator.stripSyntax(pair.getCdr());
        if (!(obj4 instanceof Pair)) goto _L6; else goto _L7
_L7:
        Pair pair2 = (Pair)obj4;
        if (pair2.getCar() != "..." || pair2.getCdr() != LList.Empty) goto _L6; else goto _L8
_L8:
        obj = "...";
_L11:
        int l = indexOf(vector, obj);
        if (l < 0)
        {
            l = vector.size();
            vector.addElement(obj);
        }
        if (obj instanceof Symbol)
        {
            gnu.expr.ScopeExp scopeexp = translator.currentScope();
            translator.noteAccess(obj, scopeexp);
        }
        if (!(obj instanceof SyntaxForm) && obj != "...")
        {
            stringbuffer.append('\030');
        }
        stringbuffer.append((char)(4 + l * 8));
        LList llist;
        int j;
        char c;
        byte byte0;
        int k;
        byte byte1;
        int k1;
        int l1;
        Object obj3;
        int i2;
        LList llist1;
        int k2;
        char c1;
        int l2;
        Pair pair1;
        if (obj == "...")
        {
            byte1 = -1;
        } else
        {
            byte1 = -2;
        }
        return byte1;
_L6:
        k1 = vector.size();
        stringbuffer.append('\b');
        l1 = 0;
        obj3 = pair.getCdr();
label0:
        do
        {
label1:
            {
                if (obj3 instanceof Pair)
                {
                    pair1 = (Pair)obj3;
                    if (translator.matches(pair1.getCar(), "..."))
                    {
                        break label1;
                    }
                }
                i2 = i + l1;
                j2 = convert_template(obj2, syntaxform, stringbuffer, i2, vector, obj1, false, translator);
                llist1 = LList.Empty;
                if (obj3 != llist1)
                {
                    stringbuffer.setCharAt(j1, (char)(1 + (-1 + (stringbuffer.length() - j1) << 3)));
                    i1 = convert_template(obj3, syntaxform, stringbuffer, i, vector, obj1, flag, translator);
                }
                if (l1 <= 0)
                {
                    break label0;
                }
                if (j2 < 0)
                {
                    translator.syntaxError("... follows template with no suitably-nested pattern variable");
                }
                k2 = l1;
                do
                {
                    do
                    {
                        if (--k2 < 0)
                        {
                            break label0;
                        }
                        c1 = (char)(5 + (j2 << 3));
                        stringbuffer.setCharAt(1 + (j1 + k2), c1);
                        l2 = i + l1;
                    } while (l2 < max_nesting);
                    max_nesting = l2;
                } while (true);
            }
            l1++;
            obj3 = pair1.getCdr();
            stringbuffer.append('\005');
        } while (true);
        if (j2 >= 0) goto _L10; else goto _L9
_L9:
        if (i1 >= 0)
        {
            return i1;
        }
        if (j2 == -1 || i1 == -1)
        {
            return -1;
        }
        if (flag)
        {
            return -2;
        }
        vector.setSize(k1);
        stringbuffer.setLength(j1);
          goto _L11
        if (obj instanceof FVector)
        {
            stringbuffer.append('(');
            return convert_template(LList.makeList((FVector)obj), syntaxform, stringbuffer, i, vector, obj1, true, translator);
        }
        llist = LList.Empty;
        if (obj == llist)
        {
            stringbuffer.append('\020');
            return -2;
        }
        if ((obj instanceof Symbol) && translator != null && translator.patternScope != null)
        {
            j = indexOf(translator.patternScope.pattern_names, obj);
            if (j >= 0)
            {
                c = patternNesting.charAt(j);
                if ((c & '\001') != 0)
                {
                    byte0 = 3;
                } else
                {
                    byte0 = 2;
                }
                k = c >> 1;
                if (k > i)
                {
                    translator.syntaxError((new StringBuilder()).append("inconsistent ... nesting of ").append(obj).toString());
                }
                stringbuffer.append((char)(byte0 + j * 8));
                if (k != i)
                {
                    j = -1;
                }
                return j;
            }
        }
          goto _L11
    }

    Object execute(int i, Object aobj[], int j, int ai[], Translator translator, TemplateScope templatescope)
    {
        int k;
        int j1;
        String s;
        for (k = template_program.charAt(i); (k & 7) == 7; k = j1 | s.charAt(i))
        {
            j1 = k - 7 << 13;
            s = template_program;
            i++;
        }

        Object obj5;
        if (k == 8)
        {
            obj5 = executeToList(i + 1, aobj, j, ai, translator, templatescope);
        } else
        {
            if (k == 16)
            {
                return LList.Empty;
            }
            if (k == 24)
            {
                obj5 = execute(i + 1, aobj, j, ai, translator, templatescope);
                LList llist = LList.Empty;
                if (obj5 != llist)
                {
                    return SyntaxForms.makeForm(obj5, templatescope);
                }
            } else
            {
                if ((k & 7) == 1)
                {
                    Pair pair = null;
                    Object obj2 = null;
                    do
                    {
                        int i1 = i + 1;
                        Object obj3 = executeToList(i1, aobj, j, ai, translator, templatescope);
                        if (pair == null)
                        {
                            obj2 = obj3;
                        } else
                        {
                            pair.setCdrBackdoor(obj3);
                        }
                        for (; obj3 instanceof Pair; obj3 = pair.getCdr())
                        {
                            pair = (Pair)obj3;
                        }

                        i = i1 + (k >> 3);
                        k = template_program.charAt(i);
                    } while ((k & 7) == 1);
                    Object obj4 = execute(i, aobj, j, ai, translator, templatescope);
                    if (pair == null)
                    {
                        obj2 = obj4;
                    } else
                    {
                        pair.setCdrBackdoor(obj4);
                    }
                    return obj2;
                }
                if (k == 40)
                {
                    Object obj1 = execute(i + 1, aobj, j, ai, translator, templatescope);
                    FVector fvector = new FVector((LList)obj1);
                    return fvector;
                }
                if ((k & 7) == 4)
                {
                    int l = k >> 3;
                    return literal_values[l];
                }
                if ((k & 6) == 2)
                {
                    Object obj = get_var(k >> 3, aobj, ai);
                    if ((k & 7) == 3)
                    {
                        obj = ((Pair)obj).getCar();
                    }
                    return obj;
                } else
                {
                    throw new Error((new StringBuilder()).append("unknown template code: ").append(k).append(" at ").append(i).toString());
                }
            }
        }
        return obj5;
    }

    public Object execute(Object aobj[], TemplateScope templatescope)
    {
        return execute(0, aobj, 0, new int[max_nesting], (Translator)Compilation.getCurrent(), templatescope);
    }

    public Object execute(Object aobj[], Translator translator, TemplateScope templatescope)
    {
        return execute(0, aobj, 0, new int[max_nesting], translator, templatescope);
    }

    LList executeToList(int i, Object aobj[], int j, int ai[], Translator translator, TemplateScope templatescope)
    {
        int k = i;
        int l;
        int l1;
        String s;
        for (l = template_program.charAt(i); (l & 7) == 7; l = l1 | s.charAt(i))
        {
            l1 = l - 7 << 13;
            s = template_program;
            i++;
        }

        Object obj1;
        if ((l & 7) == 3)
        {
            Pair pair2 = (Pair)get_var(l >> 3, aobj, ai);
            obj1 = Translator.makePair(pair2, pair2.getCar(), LList.Empty);
        } else
        if ((l & 7) == 5)
        {
            int i1 = get_count(aobj[l >> 3], j, ai);
            obj1 = LList.Empty;
            Pair pair1 = null;
            int j1 = i + 1;
            int k1 = 0;
            while (k1 < i1) 
            {
                ai[j] = k1;
                LList llist = executeToList(j1, aobj, j + 1, ai, translator, templatescope);
                if (pair1 == null)
                {
                    obj1 = llist;
                } else
                {
                    pair1.setCdrBackdoor(llist);
                }
                for (; llist instanceof Pair; llist = (LList)pair1.getCdr())
                {
                    pair1 = (Pair)llist;
                }

                k1++;
            }
        } else
        {
            Object obj = execute(k, aobj, j, ai, translator, templatescope);
            Pair pair = new Pair(obj, LList.Empty);
            return pair;
        }
        return ((LList) (obj1));
    }

    Object get_var(int i, Object aobj[], int ai[])
    {
        Object obj = aobj[i];
        if (i < patternNesting.length())
        {
            int j = patternNesting.charAt(i) >> 1;
            for (int k = 0; k < j; k++)
            {
                obj = ((Object[])(Object[])obj)[ai[k]];
            }

        }
        return obj;
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        patternNesting = (String)objectinput.readObject();
        template_program = (String)objectinput.readObject();
        literal_values = (Object[])(Object[])objectinput.readObject();
        max_nesting = objectinput.readInt();
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(patternNesting);
        objectoutput.writeObject(template_program);
        objectoutput.writeObject(((Object) (literal_values)));
        objectoutput.writeInt(max_nesting);
    }
}
