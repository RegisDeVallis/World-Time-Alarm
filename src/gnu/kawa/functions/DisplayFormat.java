// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.expr.Keyword;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.UntypedAtomic;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.AbstractFormat;
import gnu.lists.Array;
import gnu.lists.CharSeq;
import gnu.lists.Consumable;
import gnu.lists.Consumer;
import gnu.lists.ConsumerWriter;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.SimpleVector;
import gnu.lists.Strings;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.Values;
import gnu.math.IntNum;
import gnu.math.RatNum;
import gnu.text.Char;
import gnu.text.Printable;
import gnu.xml.XMLPrinter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DisplayFormat extends AbstractFormat
{

    public static final ThreadLocation outBase;
    public static final ThreadLocation outRadix = new ThreadLocation("out-radix");
    static Pattern r5rsIdentifierMinusInteriorColons = Pattern.compile("(([a-zA-Z]|[!$%&*/:<=>?^_~])([a-zA-Z]|[!$%&*/<=>?^_~]|[0-9]|([-+.@]))*[:]?)|([-+]|[.][.][.])");
    char language;
    boolean readable;

    public DisplayFormat(boolean flag, char c)
    {
        readable = flag;
        language = c;
    }

    public static DisplayFormat getCommonLispFormat(boolean flag)
    {
        return new DisplayFormat(flag, 'C');
    }

    public static DisplayFormat getEmacsLispFormat(boolean flag)
    {
        return new DisplayFormat(flag, 'E');
    }

    public static DisplayFormat getSchemeFormat(boolean flag)
    {
        return new DisplayFormat(flag, 'S');
    }

    public boolean getReadableOutput()
    {
        return readable;
    }

    int write(Array array, int i, int j, Consumer consumer)
    {
        int k = array.rank();
        String s;
        int l;
        if (j > 0)
        {
            s = "(";
        } else
        if (k == 1)
        {
            s = "#(";
        } else
        {
            s = (new StringBuilder()).append("#").append(k).append("a(").toString();
        }
        if (consumer instanceof OutPort)
        {
            ((OutPort)consumer).startLogicalBlock(s, false, ")");
        } else
        {
            write(s, consumer);
        }
        l = 0;
        if (k > 0)
        {
            int i1 = array.getSize(j);
            int j1 = j + 1;
            int k1 = 0;
            while (k1 < i1) 
            {
                if (k1 > 0)
                {
                    write(" ", consumer);
                    if (consumer instanceof OutPort)
                    {
                        ((OutPort)consumer).writeBreakFill();
                    }
                }
                int l1;
                if (j1 == k)
                {
                    writeObject(array.getRowMajor(i), consumer);
                    l1 = 1;
                } else
                {
                    l1 = write(array, i, j1, consumer);
                }
                i += l1;
                l += l1;
                k1++;
            }
        }
        if (consumer instanceof OutPort)
        {
            ((OutPort)consumer).endLogicalBlock(")");
            return l;
        } else
        {
            write(")", consumer);
            return l;
        }
    }

    public void write(int i, Consumer consumer)
    {
        if (!getReadableOutput())
        {
            Char.print(i, consumer);
            return;
        }
        if (language == 'E' && i > 32)
        {
            consumer.write(63);
            Char.print(i, consumer);
            return;
        } else
        {
            write(Char.toScmReadableString(i), consumer);
            return;
        }
    }

    public void writeBoolean(boolean flag, Consumer consumer)
    {
        String s;
        if (language == 'S')
        {
            if (flag)
            {
                s = "#t";
            } else
            {
                s = "#f";
            }
        } else
        if (flag)
        {
            s = "t";
        } else
        {
            s = "nil";
        }
        write(s, consumer);
    }

    public void writeList(LList llist, OutPort outport)
    {
        Object obj = llist;
        outport.startLogicalBlock("(", false, ")");
        Pair pair;
        for (; obj instanceof Pair; obj = pair.getCdr())
        {
            if (obj != llist)
            {
                outport.writeSpaceFill();
            }
            pair = (Pair)obj;
            writeObject(pair.getCar(), outport);
        }

        if (obj != LList.Empty)
        {
            outport.writeSpaceFill();
            outport.write(". ");
            writeObject(LList.checkNonList(obj), outport);
        }
        outport.endLogicalBlock(")");
    }

    public void writeObject(Object obj, Consumer consumer)
    {
        boolean flag1;
label0:
        {
            boolean flag = consumer instanceof OutPort;
            flag1 = false;
            if (!flag)
            {
                break label0;
            }
            boolean flag2 = obj instanceof UntypedAtomic;
            flag1 = false;
            if (flag2)
            {
                break label0;
            }
            boolean flag3 = obj instanceof Values;
            flag1 = false;
            if (flag3)
            {
                break label0;
            }
            if (!getReadableOutput())
            {
                boolean flag4 = obj instanceof Char;
                flag1 = false;
                if (flag4)
                {
                    break label0;
                }
                boolean flag5 = obj instanceof CharSequence;
                flag1 = false;
                if (flag5)
                {
                    break label0;
                }
                boolean flag6 = obj instanceof Character;
                flag1 = false;
                if (flag6)
                {
                    break label0;
                }
            }
            ((OutPort)consumer).writeWordStart();
            flag1 = true;
        }
        writeObjectRaw(obj, consumer);
        if (flag1)
        {
            ((OutPort)consumer).writeWordEnd();
        }
    }

    public void writeObjectRaw(Object obj, Consumer consumer)
    {
        if (!(obj instanceof Boolean)) goto _L2; else goto _L1
_L1:
        writeBoolean(((Boolean)obj).booleanValue(), consumer);
_L4:
        return;
_L2:
        if (obj instanceof Char)
        {
            write(((Char)obj).intValue(), consumer);
            return;
        }
        if (obj instanceof Character)
        {
            write(((Character)obj).charValue(), consumer);
            return;
        }
        if (obj instanceof Symbol)
        {
            Symbol symbol = (Symbol)obj;
            if (symbol.getNamespace() == XmlNamespace.HTML)
            {
                write("html:", consumer);
                write(symbol.getLocalPart(), consumer);
                return;
            } else
            {
                writeSymbol(symbol, consumer, readable);
                return;
            }
        }
        if ((obj instanceof URI) && getReadableOutput() && (consumer instanceof PrintWriter))
        {
            write("#,(URI ", consumer);
            Strings.printQuoted(obj.toString(), (PrintWriter)consumer, 1);
            consumer.write(41);
            return;
        }
        if (!(obj instanceof CharSequence))
        {
            break; /* Loop/switch isn't completed */
        }
        CharSequence charsequence = (CharSequence)obj;
        if (getReadableOutput() && (consumer instanceof PrintWriter))
        {
            Strings.printQuoted(charsequence, (PrintWriter)consumer, 1);
            return;
        }
        if (obj instanceof String)
        {
            consumer.write((String)obj);
            return;
        }
        if (obj instanceof CharSeq)
        {
            CharSeq charseq = (CharSeq)obj;
            charseq.consume(0, charseq.size(), consumer);
            return;
        }
        int j1 = charsequence.length();
        int k1 = 0;
        while (k1 < j1) 
        {
            consumer.write(charsequence.charAt(k1));
            k1++;
        }
        if (true) goto _L4; else goto _L3
_L3:
        int k;
        Object obj1;
        if ((obj instanceof LList) && (consumer instanceof OutPort))
        {
            writeList((LList)obj, (OutPort)consumer);
            return;
        }
        if (obj instanceof SimpleVector)
        {
            SimpleVector simplevector = (SimpleVector)obj;
            String s2 = simplevector.getTag();
            String s3;
            String s4;
            int l;
            int i1;
            if (language == 'E')
            {
                s3 = "[";
                s4 = "]";
            } else
            {
                if (s2 == null)
                {
                    s3 = "#(";
                } else
                {
                    s3 = (new StringBuilder()).append("#").append(s2).append("(").toString();
                }
                s4 = ")";
            }
            if (consumer instanceof OutPort)
            {
                ((OutPort)consumer).startLogicalBlock(s3, false, s4);
            } else
            {
                write(s3, consumer);
            }
            l = simplevector.size() << 1;
            i1 = 0;
            do
            {
label0:
                {
                    if (i1 < l)
                    {
                        if (i1 > 0 && (consumer instanceof OutPort))
                        {
                            ((OutPort)consumer).writeSpaceFill();
                        }
                        if (simplevector.consumeNext(i1, consumer))
                        {
                            break label0;
                        }
                    }
                    if (consumer instanceof OutPort)
                    {
                        ((OutPort)consumer).endLogicalBlock(s4);
                        return;
                    } else
                    {
                        write(s4, consumer);
                        return;
                    }
                }
                i1 += 2;
            } while (true);
        }
        boolean flag;
label1:
        {
            if (obj instanceof Array)
            {
                write((Array)obj, 0, 0, consumer);
                return;
            }
            if (obj instanceof KNode)
            {
                if (getReadableOutput())
                {
                    write("#", consumer);
                }
                Object obj3;
                XMLPrinter xmlprinter;
                if (consumer instanceof Writer)
                {
                    obj3 = (Writer)consumer;
                } else
                {
                    obj3 = new ConsumerWriter(consumer);
                }
                xmlprinter = new XMLPrinter(((Writer) (obj3)));
                xmlprinter.writeObject(obj);
                xmlprinter.closeThis();
                return;
            }
            if (obj == Values.empty && getReadableOutput())
            {
                write("#!void", consumer);
                return;
            }
            if (obj instanceof Consumable)
            {
                ((Consumable)obj).consume(consumer);
                return;
            }
            if (obj instanceof Printable)
            {
                ((Printable)obj).print(consumer);
                return;
            }
            if (!(obj instanceof RatNum))
            {
                break; /* Loop/switch isn't completed */
            }
            k = 10;
            obj1 = outBase.get(null);
            Object obj2 = outRadix.get(null);
            flag = false;
            if (obj2 == null)
            {
                break label1;
            }
            if (obj2 != Boolean.TRUE)
            {
                boolean flag1 = "yes".equals(obj2.toString());
                flag = false;
                if (!flag1)
                {
                    break label1;
                }
            }
            flag = true;
        }
        String s1;
        if (obj1 instanceof Number)
        {
            k = ((IntNum)obj1).intValue();
        } else
        if (obj1 != null)
        {
            k = Integer.parseInt(obj1.toString());
        }
_L6:
        s1 = ((RatNum)obj).toString(k);
        if (flag)
        {
            if (k == 16)
            {
                write("#x", consumer);
            } else
            if (k == 8)
            {
                write("#o", consumer);
            } else
            if (k == 2)
            {
                write("#b", consumer);
            } else
            if (k != 10 || !(obj instanceof IntNum))
            {
                write((new StringBuilder()).append("#").append(obj1).append("r").toString(), consumer);
            }
        }
        write(s1, consumer);
        if (flag && k == 10 && (obj instanceof IntNum))
        {
            write(".", consumer);
            return;
        }
        if (true) goto _L4; else goto _L5
_L5:
        if ((obj instanceof Enum) && getReadableOutput())
        {
            write(obj.getClass().getName(), consumer);
            write(":", consumer);
            write(((Enum)obj).name(), consumer);
            return;
        }
        String s;
        if (obj == null)
        {
            s = null;
        } else
        {
            if (obj.getClass().isArray())
            {
                int i = java.lang.reflect.Array.getLength(obj);
                int j;
                if (consumer instanceof OutPort)
                {
                    ((OutPort)consumer).startLogicalBlock("[", false, "]");
                } else
                {
                    write("[", consumer);
                }
                for (j = 0; j < i; j++)
                {
                    if (j > 0)
                    {
                        write(" ", consumer);
                        if (consumer instanceof OutPort)
                        {
                            ((OutPort)consumer).writeBreakFill();
                        }
                    }
                    writeObject(java.lang.reflect.Array.get(obj, j), consumer);
                }

                if (consumer instanceof OutPort)
                {
                    ((OutPort)consumer).endLogicalBlock("]");
                    return;
                } else
                {
                    write("]", consumer);
                    return;
                }
            }
            s = obj.toString();
        }
        if (s == null)
        {
            write("#!null", consumer);
            return;
        }
        write(s, consumer);
        return;
          goto _L6
        if (true) goto _L4; else goto _L7
_L7:
    }

    void writeSymbol(Symbol symbol, Consumer consumer, boolean flag)
    {
        boolean flag1;
        String s;
        String s1;
        boolean flag2;
        boolean flag3;
        flag1 = true;
        s = symbol.getPrefix();
        Namespace namespace = symbol.getNamespace();
        if (namespace == null)
        {
            s1 = null;
        } else
        {
            s1 = namespace.getName();
        }
        if (s1 != null && s1.length() > 0)
        {
            flag2 = flag1;
        } else
        {
            flag2 = false;
        }
        if (s == null || s.length() <= 0)
        {
            flag1 = false;
        }
        flag3 = false;
        if (namespace != Keyword.keywordNamespace) goto _L2; else goto _L1
_L1:
        if (language == 'C' || language == 'E')
        {
            consumer.write(58);
        } else
        {
            flag3 = true;
        }
_L4:
        writeSymbol(symbol.getName(), consumer, flag);
        if (flag3)
        {
            consumer.write(58);
        }
        return;
_L2:
        if (!flag1)
        {
            flag3 = false;
            if (!flag2)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        if (flag1)
        {
            writeSymbol(s, consumer, flag);
        }
        if (flag2 && (flag || !flag1))
        {
            consumer.write(123);
            consumer.write(s1);
            consumer.write(125);
        }
        consumer.write(58);
        flag3 = false;
        if (true) goto _L4; else goto _L3
_L3:
    }

    void writeSymbol(String s, Consumer consumer, boolean flag)
    {
        int i;
        if (!flag || r5rsIdentifierMinusInteriorColons.matcher(s).matches())
        {
            break MISSING_BLOCK_LABEL_140;
        }
        i = s.length();
        if (i != 0) goto _L2; else goto _L1
_L1:
        write("||", consumer);
_L8:
        return;
_L2:
        boolean flag1;
        int j;
        flag1 = false;
        j = 0;
_L4:
        if (j >= i)
        {
            continue; /* Loop/switch isn't completed */
        }
        char c = s.charAt(j);
        if (c != '|')
        {
            break; /* Loop/switch isn't completed */
        }
        String s1;
        if (flag1)
        {
            s1 = "|\\";
        } else
        {
            s1 = "\\";
        }
        write(s1, consumer);
        flag1 = false;
_L6:
        consumer.write(c);
        j++;
        if (true) goto _L4; else goto _L3
_L3:
        if (flag1) goto _L6; else goto _L5
_L5:
        consumer.write(124);
        flag1 = true;
          goto _L6
        if (!flag1) goto _L8; else goto _L7
_L7:
        consumer.write(124);
        return;
        write(s, consumer);
        return;
    }

    static 
    {
        outBase = new ThreadLocation("out-base");
        outBase.setGlobal(IntNum.ten());
    }
}
