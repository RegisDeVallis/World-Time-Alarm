// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;
import java.util.Vector;

// Referenced classes of package kawa.lang:
//            Pattern, Translator, PatternScope, SyntaxForm, 
//            Macro, SyntaxForms, SyntaxTemplate

public class SyntaxPattern extends Pattern
    implements Externalizable
{

    static final int MATCH_ANY = 3;
    static final int MATCH_ANY_CAR = 7;
    static final int MATCH_EQUALS = 2;
    static final int MATCH_IGNORE = 24;
    static final int MATCH_LENGTH = 6;
    static final int MATCH_LREPEAT = 5;
    static final int MATCH_MISC = 0;
    static final int MATCH_NIL = 8;
    static final int MATCH_PAIR = 4;
    static final int MATCH_VECTOR = 16;
    static final int MATCH_WIDE = 1;
    Object literals[];
    String program;
    int varCount;

    public SyntaxPattern(Object obj, Object aobj[], Translator translator)
    {
        this(new StringBuffer(), obj, null, aobj, translator);
    }

    public SyntaxPattern(String s, Object aobj[], int i)
    {
        program = s;
        literals = aobj;
        varCount = i;
    }

    SyntaxPattern(StringBuffer stringbuffer, Object obj, SyntaxForm syntaxform, Object aobj[], Translator translator)
    {
        Vector vector = new Vector();
        translate(obj, stringbuffer, aobj, 0, vector, null, '\0', translator);
        program = stringbuffer.toString();
        literals = new Object[vector.size()];
        vector.copyInto(literals);
        varCount = translator.patternScope.pattern_names.size();
    }

    private static void addInt(StringBuffer stringbuffer, int i)
    {
        if (i > 65535)
        {
            addInt(stringbuffer, 1 + (i << 13));
        }
        stringbuffer.append((char)i);
    }

    public static Object[] allocVars(int i, Object aobj[])
    {
        Object aobj1[] = new Object[i];
        if (aobj != null)
        {
            System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj1)), 0, aobj.length);
        }
        return aobj1;
    }

    public static Object[] getLiteralsList(Object obj, SyntaxForm syntaxform, Translator translator)
    {
        Object obj1 = translator.pushPositionOf(obj);
        int i = Translator.listLength(obj);
        if (i < 0)
        {
            translator.error('e', "missing or malformed literals list");
            i = 0;
        }
        Object aobj[] = new Object[i + 1];
        int j = 1;
        while (j <= i) 
        {
            for (; obj instanceof SyntaxForm; obj = ((SyntaxForm)obj).getDatum()) { }
            Pair pair = (Pair)obj;
            translator.pushPositionOf(pair);
            Object obj2 = pair.getCar();
            Object obj3;
            if (obj2 instanceof SyntaxForm)
            {
                obj3 = obj2;
                obj2 = ((SyntaxForm)obj2).getDatum();
            } else
            {
                obj3 = obj2;
            }
            if (!(obj2 instanceof Symbol))
            {
                translator.error('e', (new StringBuilder()).append("non-symbol '").append(obj2).append("' in literals list").toString());
            }
            aobj[j] = obj3;
            obj = pair.getCdr();
            j++;
        }
        translator.popPositionOf(obj1);
        return aobj;
    }

    private static int insertInt(int i, StringBuffer stringbuffer, int j)
    {
        if (j > 65535)
        {
            i += insertInt(i, stringbuffer, 1 + (j << 13));
        }
        stringbuffer.insert(i, (char)j);
        return i + 1;
    }

    public static boolean literalIdentifierEq(Object obj, ScopeExp scopeexp, Object obj1, ScopeExp scopeexp1)
    {
        boolean flag = true;
        if (obj == obj1 || obj != null && obj1 != null && obj.equals(obj1)) goto _L2; else goto _L1
_L1:
        flag = false;
_L4:
        return flag;
_L2:
        if (scopeexp == scopeexp1) goto _L4; else goto _L3
_L3:
        Declaration declaration = null;
_L10:
        Declaration declaration1 = null;
        if (scopeexp == null) goto _L6; else goto _L5
_L5:
        boolean flag1;
        flag1 = scopeexp instanceof ModuleExp;
        declaration1 = null;
        if (flag1) goto _L6; else goto _L7
_L7:
        declaration = scopeexp.lookup(obj);
        declaration1 = null;
        if (declaration == null) goto _L8; else goto _L6
_L6:
        if (scopeexp1 == null || (scopeexp1 instanceof ModuleExp))
        {
            continue; /* Loop/switch isn't completed */
        }
        declaration1 = scopeexp1.lookup(obj1);
        if (declaration1 == null)
        {
            break MISSING_BLOCK_LABEL_114;
        }
        if (declaration == declaration1) goto _L4; else goto _L9
_L9:
        return false;
_L8:
        scopeexp = scopeexp.outer;
          goto _L10
        scopeexp1 = scopeexp1.outer;
          goto _L6
    }

    public void disassemble()
    {
        disassemble(((PrintWriter) (OutPort.errDefault())), (Translator)Compilation.getCurrent(), 0, program.length());
    }

    public void disassemble(PrintWriter printwriter, Translator translator)
    {
        disassemble(printwriter, translator, 0, program.length());
    }

    void disassemble(PrintWriter printwriter, Translator translator, int i, int j)
    {
        Vector vector;
        int k;
        int l;
        vector = null;
        if (translator != null)
        {
            PatternScope patternscope = translator.patternScope;
            vector = null;
            if (patternscope != null)
            {
                vector = translator.patternScope.pattern_names;
            }
        }
        k = 0;
        l = i;
_L13:
        char c;
        int i1;
        if (l >= j)
        {
            break MISSING_BLOCK_LABEL_737;
        }
        c = program.charAt(l);
        printwriter.print((new StringBuilder()).append(" ").append(l).append(": ").append(c).toString());
        l++;
        i1 = c & 7;
        k = k << 13 | c >> 3;
        i1;
        JVM INSTR tableswitch 0 7: default 160
    //                   0 645
    //                   1 200
    //                   2 227
    //                   3 296
    //                   4 384
    //                   5 416
    //                   6 582
    //                   7 296;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L5
_L1:
        printwriter.println((new StringBuilder()).append(" - ").append(i1).append('/').append(k).toString());
_L9:
        k = 0;
        continue; /* Loop/switch isn't completed */
_L3:
        printwriter.println((new StringBuilder()).append(" - WIDE ").append(k).toString());
        continue; /* Loop/switch isn't completed */
_L4:
        printwriter.print((new StringBuilder()).append(" - EQUALS[").append(k).append("]").toString());
        if (literals != null && k >= 0 && k < literals.length)
        {
            printwriter.print(literals[k]);
        }
        printwriter.println();
          goto _L9
_L5:
        StringBuilder stringbuilder3 = new StringBuilder();
        String s3;
        if (i1 == 3)
        {
            s3 = " - ANY[";
        } else
        {
            s3 = " - ANY_CAR[";
        }
        printwriter.print(stringbuilder3.append(s3).append(k).append("]").toString());
        if (vector != null && k >= 0 && k < vector.size())
        {
            printwriter.print(vector.elementAt(k));
        }
        printwriter.println();
          goto _L9
_L6:
        printwriter.println((new StringBuilder()).append(" - PAIR[").append(k).append("]").toString());
          goto _L9
_L7:
        printwriter.println((new StringBuilder()).append(" - LREPEAT[").append(k).append("]").toString());
        disassemble(printwriter, translator, l, l + k);
        int j1 = l + k;
        StringBuilder stringbuilder1 = (new StringBuilder()).append(" ").append(j1).append(": - repeat first var:");
        String s1 = program;
        int k1 = j1 + 1;
        printwriter.println(stringbuilder1.append(s1.charAt(j1) >> 3).toString());
        StringBuilder stringbuilder2 = (new StringBuilder()).append(" ").append(k1).append(": - repeast nested vars:");
        String s2 = program;
        l = k1 + 1;
        printwriter.println(stringbuilder2.append(s2.charAt(k1) >> 3).toString());
          goto _L9
_L8:
        StringBuilder stringbuilder = (new StringBuilder()).append(" - LENGTH ").append(k >> 1).append(" pairs. ");
        String s;
        if ((k & 1) == 0)
        {
            s = "pure list";
        } else
        {
            s = "impure list";
        }
        printwriter.println(stringbuilder.append(s).toString());
          goto _L9
_L2:
        printwriter.print((new StringBuilder()).append("[misc ch:").append(c).append(" n:").append(8).append("]").toString());
        if (c == '\b')
        {
            printwriter.println(" - NIL");
        } else
        {
            if (c != '\020')
            {
                continue; /* Loop/switch isn't completed */
            }
            printwriter.println(" - VECTOR");
        }
          goto _L9
        if (c != '\030') goto _L1; else goto _L10
_L10:
        printwriter.println(" - IGNORE");
          goto _L9
        if (true) goto _L1; else goto _L11
_L11:
        return;
        if (true) goto _L13; else goto _L12
_L12:
    }

    public boolean match(Object obj, Object aobj[], int i)
    {
        return match(obj, aobj, i, 0, null);
    }

    public boolean match(Object obj, Object aobj[], int i, int j, SyntaxForm syntaxform)
    {
        int k = 0;
_L10:
        int l;
        char c;
        int i1;
        for (; obj instanceof SyntaxForm; obj = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj;
        }

        String s = program;
        l = j + 1;
        c = s.charAt(j);
        i1 = c & 7;
        k = k << 13 | c >> 3;
        i1;
        JVM INSTR tableswitch 0 8: default 120
    //                   0 160
    //                   1 153
    //                   2 949
    //                   3 1110
    //                   4 374
    //                   5 425
    //                   6 267
    //                   7 120
    //                   8 244;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L7 _L8 _L1 _L9
_L1:
        disassemble();
        throw new Error((new StringBuilder()).append("unrecognized pattern opcode @pc:").append(l).toString());
_L3:
        j = l;
          goto _L10
_L2:
        if (c == '\b')
        {
            LList llist2 = LList.Empty;
            boolean flag3;
            if (obj == llist2)
            {
                flag3 = true;
            } else
            {
                flag3 = false;
            }
            return flag3;
        }
        if (c == '\020')
        {
            if (!(obj instanceof FVector))
            {
                return false;
            } else
            {
                return match(LList.makeList((FVector)obj), aobj, i, l, syntaxform);
            }
        }
        if (c == '\030')
        {
            return true;
        } else
        {
            throw new Error("unknwon pattern opcode");
        }
_L9:
        LList llist1 = LList.Empty;
        boolean flag2;
        if (obj == llist1)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        return flag2;
_L8:
        int l6;
        Object obj6;
        int i7;
        l6 = k >> 1;
        obj6 = obj;
        i7 = 0;
_L13:
        for (; obj6 instanceof SyntaxForm; obj6 = ((SyntaxForm)obj6).getDatum()) { }
        if (i7 != l6) goto _L12; else goto _L11
_L11:
        LList llist;
        if ((k & 1) != 0 ? obj6 instanceof Pair : obj6 != (llist = LList.Empty))
        {
            return false;
        }
        j = l;
        k = 0;
          goto _L10
_L12:
        if (obj6 instanceof Pair)
        {
            obj6 = ((Pair)obj6).getCdr();
            i7++;
        } else
        {
            return false;
        }
          goto _L13
_L6:
        if (!(obj instanceof Pair))
        {
            return false;
        }
        Pair pair1 = (Pair)obj;
        if (!match_car(pair1, aobj, i, l, syntaxform))
        {
            return false;
        }
        j = l + k;
        obj = pair1.getCdr();
        k = 0;
          goto _L10
_L7:
        int j1 = l + k;
        String s1 = program;
        int k1 = j1 + 1;
        char c1 = s1.charAt(j1);
        int l1 = c1 >> 3;
        while ((c1 & 7) == 1) 
        {
            int j6 = l1 << 13;
            String s6 = program;
            int k6 = k1 + 1;
            c1 = s6.charAt(k1);
            l1 = j6 | c1 >> 3;
            k1 = k6;
        }
        int i2 = l1 + i;
        String s2 = program;
        int j2 = k1 + 1;
        int k2 = s2.charAt(k1) >> 3;
        int l2;
        int i6;
        for (l2 = j2; (c1 & 7) == 1; l2 = i6)
        {
            int l5 = k2 << 13;
            String s5 = program;
            i6 = l2 + 1;
            c1 = s5.charAt(l2);
            k2 = l5 | c1 >> 3;
        }

        String s3 = program;
        j = l2 + 1;
        char c2 = s3.charAt(l2);
        boolean flag = true;
        int k3;
        int l3;
        boolean flag1;
        if (c2 == '\b')
        {
            k3 = 0;
        } else
        {
            int i3 = c2 >> 3;
            int j3;
            int k5;
            for (j3 = j; (c2 & 7) == 1; j3 = k5)
            {
                int j5 = i3 << 13;
                String s4 = program;
                k5 = j3 + 1;
                c2 = s4.charAt(j3);
                i3 = j5 | c2 >> 3;
            }

            if ((i3 & 1) != 0)
            {
                flag = false;
            }
            k3 = i3 >> 1;
            j = j3;
        }
        l3 = Translator.listLength(obj);
        if (l3 >= 0)
        {
            flag1 = true;
        } else
        {
            l3 = -1 - l3;
            flag1 = false;
        }
        if (l3 < k3 || flag && !flag1)
        {
            return false;
        }
        int i4 = l3 - k3;
        Object aobj1[][] = new Object[k2][];
        for (int j4 = 0; j4 < k2; j4++)
        {
            aobj1[j4] = new Object[i4];
        }

        for (int k4 = 0; k4 < i4; k4++)
        {
            for (; obj instanceof SyntaxForm; obj = syntaxform.getDatum())
            {
                syntaxform = (SyntaxForm)obj;
            }

            Pair pair = (Pair)obj;
            if (!match_car(pair, aobj, i, l, syntaxform))
            {
                return false;
            }
            obj = pair.getCdr();
            for (int i5 = 0; i5 < k2; i5++)
            {
                aobj1[i5][k4] = aobj[i2 + i5];
            }

        }

        for (int l4 = 0; l4 < k2; l4++)
        {
            aobj[i2 + l4] = ((Object) (aobj1[l4]));
        }

        k = 0;
        if (k3 == 0)
        {
            k = 0;
            if (flag)
            {
                return true;
            }
        }
          goto _L10
_L4:
        Object obj1 = literals[k];
        Translator translator = (Translator)Compilation.getCurrent();
        Object obj2;
        Object obj3;
        Object obj4;
        Object obj5;
        if (obj1 instanceof SyntaxForm)
        {
            SyntaxForm syntaxform2 = (SyntaxForm)obj1;
            obj2 = syntaxform2.getDatum();
            obj3 = syntaxform2.getScope();
        } else
        {
            obj2 = obj1;
            Syntax syntax = translator.getCurrentSyntax();
            if (syntax instanceof Macro)
            {
                obj3 = ((Macro)syntax).getCapturedScope();
            } else
            {
                obj3 = null;
            }
        }
        if (obj instanceof SyntaxForm)
        {
            SyntaxForm syntaxform1 = (SyntaxForm)obj;
            obj4 = syntaxform1.getDatum();
            obj5 = syntaxform1.getScope();
        } else
        {
            obj4 = obj;
            if (syntaxform == null)
            {
                obj5 = translator.currentScope();
            } else
            {
                obj5 = syntaxform.getScope();
            }
        }
        return literalIdentifierEq(obj2, ((ScopeExp) (obj3)), obj4, ((ScopeExp) (obj5)));
_L5:
        if (syntaxform != null)
        {
            obj = SyntaxForms.fromDatum(obj, syntaxform);
        }
        aobj[i + k] = obj;
        return true;
    }

    boolean match_car(Pair pair, Object aobj[], int i, int j, SyntaxForm syntaxform)
    {
        String s = program;
        int k = j + 1;
        char c = s.charAt(j);
        int l = c >> 3;
        while ((c & 7) == 1) 
        {
            int i1 = l << 13;
            String s1 = program;
            int j1 = k + 1;
            c = s1.charAt(k);
            l = i1 | c >> 3;
            k = j1;
        }
        if ((c & 7) == 7)
        {
            if (syntaxform != null && !(pair.getCar() instanceof SyntaxForm))
            {
                pair = Translator.makePair(pair, SyntaxForms.fromDatum(pair.getCar(), syntaxform), pair.getCdr());
            }
            aobj[i + l] = pair;
            return true;
        } else
        {
            return match(pair.getCar(), aobj, i, j, syntaxform);
        }
    }

    public void print(Consumer consumer)
    {
        consumer.write("#<syntax-pattern>");
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        literals = (Object[])(Object[])objectinput.readObject();
        program = (String)objectinput.readObject();
        varCount = objectinput.readInt();
    }

    void translate(Object obj, StringBuffer stringbuffer, Object aobj[], int i, Vector vector, SyntaxForm syntaxform, char c, 
            Translator translator)
    {
        PatternScope patternscope;
        Vector vector1;
        patternscope = translator.patternScope;
        vector1 = patternscope.pattern_names;
_L7:
        for (; obj instanceof SyntaxForm; obj = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj;
        }

        if (!(obj instanceof Pair)) goto _L2; else goto _L1
_L1:
        Object obj4 = translator.pushPositionOf(obj);
        int j2;
        Pair pair;
        j2 = stringbuffer.length();
        stringbuffer.append('\004');
        pair = (Pair)obj;
        SyntaxForm syntaxform2 = syntaxform;
        Object obj5;
        for (obj5 = pair.getCdr(); obj5 instanceof SyntaxForm; obj5 = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj5;
        }

        boolean flag1 = obj5 instanceof Pair;
        boolean flag2;
        flag2 = false;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_190;
        }
        boolean flag3 = translator.matches(((Pair)obj5).getCar(), "...");
        flag2 = false;
        if (!flag3)
        {
            break MISSING_BLOCK_LABEL_190;
        }
        flag2 = true;
        for (obj5 = ((Pair)obj5).getCdr(); obj5 instanceof SyntaxForm; obj5 = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj5;
        }

        int k2 = vector1.size();
        if (c == 'P')
        {
            c = '\0';
        }
        Object obj6 = pair.getCar();
        int l2;
        char c1;
        int i3;
        int j3;
        int l3;
        if (flag2)
        {
            l2 = i + 1;
        } else
        {
            l2 = i;
        }
          goto _L3
_L6:
        translate(obj6, stringbuffer, aobj, l2, vector, syntaxform2, c1, translator);
        i3 = vector1.size() - k2;
        j3 = -1 + (stringbuffer.length() - j2) << 3;
        byte byte1;
        int k3;
        char c2;
        int l4;
        if (flag2)
        {
            byte1 = 5;
        } else
        {
            byte1 = 4;
        }
        k3 = j3 | byte1;
        if (k3 <= 65535)
        {
            break MISSING_BLOCK_LABEL_313;
        }
        l4 = 1 + (k3 >> 13);
        j2 += insertInt(j2, stringbuffer, l4);
        c2 = (char)k3;
        stringbuffer.setCharAt(j2, c2);
        l3 = Translator.listLength(obj5);
        if (l3 != 0x80000000) goto _L5; else goto _L4
_L4:
        translator.syntaxError("cyclic pattern list");
        translator.popPositionOf(obj4);
        return;
_L9:
        c1 = 'P';
          goto _L6
_L5:
        int i4;
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_456;
        }
        i4 = k2 << 3;
        LList llist1;
        addInt(stringbuffer, i4);
        addInt(stringbuffer, i3 << 3);
        llist1 = LList.Empty;
        if (obj5 != llist1)
        {
            break MISSING_BLOCK_LABEL_430;
        }
        stringbuffer.append('\b');
        translator.popPositionOf(obj4);
        return;
        int j4;
        int k4;
        if (l3 >= 0)
        {
            j4 = l3 << 1;
        } else
        {
            j4 = -1 + (-l3 << 1);
        }
        k4 = 6 | j4 << 3;
        addInt(stringbuffer, k4);
        obj = obj5;
        translator.popPositionOf(obj4);
          goto _L7
        Exception exception;
        exception;
        translator.popPositionOf(obj4);
        throw exception;
_L2:
        if (obj instanceof Symbol)
        {
            for (int k = aobj.length; --k >= 0;)
            {
                ScopeExp scopeexp = translator.currentScope();
                Object obj1;
                Object obj2;
                Object obj3;
                if (syntaxform == null)
                {
                    obj1 = scopeexp;
                } else
                {
                    obj1 = syntaxform.getScope();
                }
                obj2 = aobj[k];
                if (obj2 instanceof SyntaxForm)
                {
                    SyntaxForm syntaxform1 = (SyntaxForm)obj2;
                    obj2 = syntaxform1.getDatum();
                    obj3 = syntaxform1.getScope();
                } else
                if (translator.currentMacroDefinition != null)
                {
                    obj3 = translator.currentMacroDefinition.getCapturedScope();
                } else
                {
                    obj3 = scopeexp;
                }
                if (literalIdentifierEq(obj, ((ScopeExp) (obj1)), obj2, ((ScopeExp) (obj3))))
                {
                    int i2 = SyntaxTemplate.indexOf(vector, obj);
                    if (i2 < 0)
                    {
                        i2 = vector.size();
                        vector.addElement(obj);
                    }
                    addInt(stringbuffer, 2 | i2 << 3);
                    return;
                }
            }

            if (vector1.contains(obj))
            {
                translator.syntaxError((new StringBuilder()).append("duplicated pattern variable ").append(obj).toString());
            }
            int l = vector1.size();
            vector1.addElement(obj);
            boolean flag;
            int i1;
            int j1;
            int k1;
            Declaration declaration;
            int l1;
            byte byte0;
            if (c == 'P')
            {
                flag = true;
            } else
            {
                flag = false;
            }
            i1 = i << 1;
            if (flag)
            {
                j1 = 1;
            } else
            {
                j1 = 0;
            }
            k1 = i1 + j1;
            patternscope.patternNesting.append((char)k1);
            declaration = patternscope.addDeclaration(obj);
            declaration.setLocation(translator);
            translator.push(declaration);
            l1 = l << 3;
            if (flag)
            {
                byte0 = 7;
            } else
            {
                byte0 = 3;
            }
            addInt(stringbuffer, byte0 | l1);
            return;
        }
        LList llist = LList.Empty;
        if (obj == llist)
        {
            stringbuffer.append('\b');
            return;
        }
        if (obj instanceof FVector)
        {
            stringbuffer.append('\020');
            obj = LList.makeList((FVector)obj);
            c = 'V';
        } else
        {
            int j = SyntaxTemplate.indexOf(vector, obj);
            if (j < 0)
            {
                j = vector.size();
                vector.addElement(obj);
            }
            addInt(stringbuffer, 2 | j << 3);
            return;
        }
          goto _L7
_L3:
        if (c != 'V') goto _L9; else goto _L8
_L8:
        c1 = '\0';
          goto _L6
    }

    public int varCount()
    {
        return varCount;
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(program);
        objectoutput.writeObject(((Object) (literals)));
        objectoutput.writeInt(varCount);
    }
}
