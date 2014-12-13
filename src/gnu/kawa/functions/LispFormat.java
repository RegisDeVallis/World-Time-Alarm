// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.math.IntNum;
import gnu.text.CaseConvertFormat;
import gnu.text.Char;
import gnu.text.CompoundFormat;
import gnu.text.FlushFormat;
import gnu.text.LiteralFormat;
import gnu.text.ReportFormat;
import java.text.Format;
import java.text.ParseException;
import java.util.Stack;
import java.util.Vector;

// Referenced classes of package gnu.kawa.functions:
//            IntegerFormat, LispPluralFormat, LispRealFormat, ObjectFormat, 
//            LispObjectFormat, LispCharacterFormat, LispRepositionFormat, LispIterationFormat, 
//            LispPrettyFormat, LispChoiceFormat, LispEscapeFormat, LispTabulateFormat, 
//            LispFreshlineFormat, LispIndentFormat, LispNewlineFormat

public class LispFormat extends CompoundFormat
{

    public static final String paramFromCount = "<from count>";
    public static final String paramFromList = "<from list>";
    public static final String paramUnspecified = "<unspecified>";

    public LispFormat(String s)
        throws ParseException
    {
        this(s.toCharArray());
    }

    public LispFormat(char ac[])
        throws ParseException
    {
        this(ac, 0, ac.length);
    }

    public LispFormat(char ac[], int i, int j)
        throws ParseException
    {
        int k;
        int l;
        StringBuffer stringbuffer;
        Stack stack;
        int i1;
        int j1;
        super(null, 0);
        k = -1;
        l = 0;
        stringbuffer = new StringBuffer(100);
        stack = new Stack();
        i1 = i + j;
        j1 = i;
_L35:
        int i2;
        int l2;
        do
        {
            if ((j1 >= i1 || ac[j1] == '~') && stringbuffer.length() > 0)
            {
                stack.push(new LiteralFormat(stringbuffer));
                stringbuffer.setLength(0);
            }
            if (j1 >= i1)
            {
                int k1;
                char c;
                int l1;
                char c1;
                int j2;
                int k2;
                boolean flag;
                boolean flag1;
                char c2;
                int i3;
                int j3;
                int k3;
                int l3;
                int i4;
                byte byte0;
                int j4;
                LispPrettyFormat lispprettyformat;
                LispChoiceFormat lispchoiceformat;
                LispChoiceFormat lispchoiceformat1;
                LispPrettyFormat lispprettyformat1;
                int l4;
                LispPrettyFormat lispprettyformat2;
                LispIterationFormat lispiterationformat;
                LispIterationFormat lispiterationformat1;
                LispIterationFormat lispiterationformat2;
                char c3;
                CaseConvertFormat caseconvertformat;
                int i5;
                boolean flag2;
                int j5;
                int k5;
                int l5;
                int i6;
                ReportFormat reportformat;
                int j6;
                LispRealFormat lisprealformat;
                int k6;
                int l6;
                int i7;
                int j7;
                int k7;
                int l7;
                int i8;
                int j8;
                int k8;
                boolean flag3;
                int i9;
                int j9;
                int k9;
                int l9;
                IntNum intnum;
                int i10;
                int j10;
                if (j1 > i1)
                {
                    throw new IndexOutOfBoundsException();
                }
                if (k >= 0)
                {
                    throw new ParseException("missing ~] or ~}", j1);
                } else
                {
                    length = stack.size();
                    formats = new Format[length];
                    stack.copyInto(formats);
                    return;
                }
            }
label0:
            {
                k1 = j1 + 1;
                c = ac[j1];
                if (c == '~')
                {
                    break label0;
                }
                stringbuffer.append(c);
                j1 = k1;
            }
        } while (true);
        l1 = stack.size();
        i2 = k1 + 1;
        c1 = ac[k1];
_L6:
        if (c1 == '#')
        {
            stack.push("<from count>");
            j10 = i2 + 1;
            c1 = ac[i2];
            i2 = j10;
        } else
        {
label1:
            {
                if (c1 != 'v' && c1 != 'V')
                {
                    break label1;
                }
                stack.push("<from list>");
                j2 = i2 + 1;
                c1 = ac[i2];
                i2 = j2;
            }
        }
_L5:
        if (c1 == ',') goto _L2; else goto _L1
_L1:
        l2 = i2;
_L39:
        flag = false;
        flag1 = false;
        j1 = l2;
_L4:
        if (c1 != ':')
        {
            break; /* Loop/switch isn't completed */
        }
        flag = true;
_L7:
        k8 = j1 + 1;
        c1 = ac[j1];
        j1 = k8;
        if (true) goto _L4; else goto _L3
        if (c1 != '-' && Character.digit(c1, 10) < 0)
        {
            break MISSING_BLOCK_LABEL_444;
        }
        int l8;
        if (c1 == '-')
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        Object obj;
        int k4;
        ObjectFormat objectformat;
        if (flag3)
        {
            l8 = i2 + 1;
            c1 = ac[i2];
        } else
        {
            l8 = i2;
        }
        i9 = 0;
        j9 = l8;
label2:
        {
            k9 = Character.digit(c1, 10);
            if (k9 >= 0)
            {
                break label2;
            }
            if (l8 - j9 < 8)
            {
                if (flag3)
                {
                    i9 = -i9;
                }
                intnum = IntNum.make(i9);
            } else
            {
                intnum = IntNum.valueOf(ac, j9, l8 - j9, 10, flag3);
            }
            stack.push(intnum);
            i2 = l8;
        }
          goto _L5
        i9 = k9 + i9 * 10;
        l9 = l8 + 1;
        c1 = ac[l8];
        l8 = l9;
        break MISSING_BLOCK_LABEL_333;
        if (c1 == '\'')
        {
            i10 = i2 + 1;
            stack.push(Char.make(ac[i2]));
            i2 = i10 + 1;
            c1 = ac[i10];
        } else
        {
            if (c1 != ',')
            {
                break MISSING_BLOCK_LABEL_2985;
            }
            stack.push("<unspecified>");
        }
          goto _L5
_L2:
        k2 = i2 + 1;
        c1 = ac[i2];
        i2 = k2;
          goto _L6
_L3:
label3:
        {
            if (c1 != '@')
            {
                break label3;
            }
            flag1 = true;
        }
          goto _L7
        c2 = Character.toUpperCase(c1);
        i3 = stack.size() - l1;
        c2;
        JVM INSTR lookupswitch 36: default 852
    //                   10: 2566
    //                   33: 2616
    //                   36: 1073
    //                   37: 2891
    //                   38: 2663
    //                   40: 1423
    //                   41: 1508
    //                   42: 1400
    //                   59: 2271
    //                   60: 1771
    //                   62: 1872
    //                   63: 1578
    //                   65: 1248
    //                   66: 882
    //                   67: 1364
    //                   68: 882
    //                   69: 1073
    //                   70: 1073
    //                   71: 1073
    //                   73: 2682
    //                   79: 882
    //                   80: 1061
    //                   82: 882
    //                   83: 1248
    //                   84: 2624
    //                   87: 1248
    //                   88: 882
    //                   89: 1248
    //                   91: 2163
    //                   93: 2413
    //                   94: 2529
    //                   95: 2714
    //                   123: 1613
    //                   124: 2820
    //                   125: 1681
    //                   126: 2804;
           goto _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L19 _L20 _L21 _L22 _L23 _L22 _L11 _L11 _L11 _L24 _L22 _L25 _L22 _L21 _L26 _L21 _L22 _L21 _L27 _L28 _L29 _L30 _L31 _L32 _L33 _L34
_L8:
        throw new ParseException((new StringBuilder()).append("unrecognized format specifier ~").append(c2).toString(), j1);
_L22:
        k6 = l1;
        if (c2 == 'R')
        {
            j8 = k6 + 1;
            l6 = getParam(stack, k6);
            k6 = j8;
        } else
        if (c2 == 'D')
        {
            l6 = 10;
        } else
        if (c2 == 'O')
        {
            l6 = 8;
        } else
        if (c2 == 'X')
        {
            l6 = 16;
        } else
        {
            l6 = 2;
        }
        i7 = getParam(stack, k6);
        j7 = getParam(stack, k6 + 1);
        k7 = getParam(stack, k6 + 2);
        l7 = getParam(stack, k6 + 3);
        i8 = 0;
        if (flag)
        {
            i8 = false | true;
        }
        if (flag1)
        {
            i8 |= 2;
        }
        obj = IntegerFormat.getInstance(l6, i7, j7, k7, l7, i8);
_L36:
        stack.setSize(l1);
        stack.push(obj);
          goto _L35
_L25:
        obj = LispPluralFormat.getInstance(flag, flag1);
          goto _L36
_L11:
        lisprealformat = new LispRealFormat();
        lisprealformat.op = c2;
        lisprealformat.arg1 = getParam(stack, l1);
        lisprealformat.arg2 = getParam(stack, l1 + 1);
        lisprealformat.arg3 = getParam(stack, l1 + 2);
        lisprealformat.arg4 = getParam(stack, l1 + 3);
        if (c2 != '$')
        {
            lisprealformat.arg5 = getParam(stack, l1 + 4);
            if (c2 == 'E' || c2 == 'G')
            {
                lisprealformat.arg6 = getParam(stack, l1 + 5);
                lisprealformat.arg7 = getParam(stack, l1 + 6);
            }
        }
        lisprealformat.showPlus = flag1;
        lisprealformat.internalPad = flag;
        if (lisprealformat.argsUsed == 0)
        {
            obj = lisprealformat.resolve(null, 0);
        } else
        {
            obj = lisprealformat;
        }
          goto _L36
_L21:
        if (c2 != 'A')
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        objectformat = ObjectFormat.getInstance(flag2);
        if (i3 > 0)
        {
            j5 = getParam(stack, l1);
            k5 = getParam(stack, l1 + 1);
            l5 = getParam(stack, l1 + 2);
            i6 = getParam(stack, l1 + 3);
            reportformat = (ReportFormat)objectformat;
            if (flag1)
            {
                j6 = 0;
            } else
            {
                j6 = 100;
            }
            obj = new LispObjectFormat(reportformat, j5, k5, l5, i6, j6);
        } else
        {
            obj = objectformat;
        }
          goto _L36
_L23:
        if (i3 > 0)
        {
            i5 = getParam(stack, l1);
        } else
        {
            i5 = 0xa0000000;
        }
        obj = LispCharacterFormat.getInstance(i5, 1, flag1, flag);
          goto _L36
_L16:
        obj = new LispRepositionFormat(getParam(stack, l1), flag, flag1);
          goto _L36
_L14:
        if (flag)
        {
            if (flag1)
            {
                c3 = 'U';
            } else
            {
                c3 = 'C';
            }
        } else
        if (flag1)
        {
            c3 = 'T';
        } else
        {
            c3 = 'L';
        }
        caseconvertformat = new CaseConvertFormat(null, c3);
        stack.setSize(l1);
        stack.push(caseconvertformat);
        stack.push(IntNum.make(k));
        k = l1;
          goto _L35
_L15:
        if (k < 0 || !(stack.elementAt(k) instanceof CaseConvertFormat))
        {
            throw new ParseException("saw ~) without matching ~(", j1);
        }
        ((CaseConvertFormat)stack.elementAt(k)).setBaseFormat(popFormats(stack, k + 2, l1));
        k = ((IntNum)stack.pop()).intValue();
          goto _L35
_L20:
        lispiterationformat2 = new LispIterationFormat();
        lispiterationformat2.seenAt = flag1;
        lispiterationformat2.maxIterations = 1;
        lispiterationformat2.atLeastOnce = true;
        obj = lispiterationformat2;
          goto _L36
_L31:
        lispiterationformat1 = new LispIterationFormat();
        lispiterationformat1.seenAt = flag1;
        lispiterationformat1.seenColon = flag;
        lispiterationformat1.maxIterations = getParam(stack, l1);
        stack.setSize(l1);
        stack.push(lispiterationformat1);
        stack.push(IntNum.make(k));
        k = l1;
          goto _L35
_L33:
        if (k < 0 || !(stack.elementAt(k) instanceof LispIterationFormat))
        {
            throw new ParseException("saw ~} without matching ~{", j1);
        }
        lispiterationformat = (LispIterationFormat)stack.elementAt(k);
        lispiterationformat.atLeastOnce = flag;
        if (l1 > k + 2)
        {
            lispiterationformat.body = popFormats(stack, k + 2, l1);
        }
        k = ((IntNum)stack.pop()).intValue();
          goto _L35
_L18:
        lispprettyformat2 = new LispPrettyFormat();
        lispprettyformat2.seenAt = flag1;
        if (flag)
        {
            lispprettyformat2.prefix = "(";
            lispprettyformat2.suffix = ")";
        } else
        {
            lispprettyformat2.prefix = "";
            lispprettyformat2.suffix = "";
        }
        stack.setSize(l1);
        stack.push(lispprettyformat2);
        stack.push(IntNum.make(k));
        stack.push(IntNum.make(l));
        k = l1;
        l = 0;
          goto _L35
_L19:
        if (k < 0 || !(stack.elementAt(k) instanceof LispPrettyFormat))
        {
            throw new ParseException("saw ~> without matching ~<", j1);
        }
        stack.push(popFormats(stack, l + (k + 3), l1));
        lispprettyformat1 = (LispPrettyFormat)stack.elementAt(k);
        lispprettyformat1.segments = getFormats(stack, k + 3, stack.size());
        stack.setSize(k + 3);
        ((IntNum)stack.pop()).intValue();
        k = ((IntNum)stack.pop()).intValue();
        if (flag)
        {
            l4 = lispprettyformat1.segments.length;
            if (l4 > 3)
            {
                throw new ParseException("too many segments in Logical Block format", j1);
            }
            if (l4 >= 2)
            {
                if (!(lispprettyformat1.segments[0] instanceof LiteralFormat))
                {
                    throw new ParseException("prefix segment is not literal", j1);
                }
                lispprettyformat1.prefix = ((LiteralFormat)lispprettyformat1.segments[0]).content();
                lispprettyformat1.body = lispprettyformat1.segments[1];
            } else
            {
                lispprettyformat1.body = lispprettyformat1.segments[0];
            }
            if (l4 >= 3)
            {
                if (!(lispprettyformat1.segments[2] instanceof LiteralFormat))
                {
                    throw new ParseException("suffix segment is not literal", j1);
                }
                lispprettyformat1.suffix = ((LiteralFormat)lispprettyformat1.segments[2]).content();
            }
        } else
        {
            throw new ParseException("not implemented: justfication i.e. ~<...~>", j1);
        }
          goto _L35
_L27:
        lispchoiceformat1 = new LispChoiceFormat();
        lispchoiceformat1.param = getParam(stack, l1);
        if (lispchoiceformat1.param == 0xc0000000)
        {
            lispchoiceformat1.param = 0xa0000000;
        }
        if (flag)
        {
            lispchoiceformat1.testBoolean = true;
        }
        if (flag1)
        {
            lispchoiceformat1.skipIfFalse = true;
        }
        stack.setSize(l1);
        stack.push(lispchoiceformat1);
        stack.push(IntNum.make(k));
        stack.push(IntNum.make(l));
        k = l1;
        l = 0;
          goto _L35
_L17:
label4:
        {
            if (k < 0)
            {
                break label4;
            }
            if (stack.elementAt(k) instanceof LispChoiceFormat)
            {
                lispchoiceformat = (LispChoiceFormat)stack.elementAt(k);
                if (flag)
                {
                    lispchoiceformat.lastIsDefault = true;
                }
                stack.push(popFormats(stack, l + (k + 3), l1));
                l++;
            } else
            {
                if (!(stack.elementAt(k) instanceof LispPrettyFormat))
                {
                    break label4;
                }
                lispprettyformat = (LispPrettyFormat)stack.elementAt(k);
                if (flag1)
                {
                    lispprettyformat.perLine = true;
                }
                stack.push(popFormats(stack, l + (k + 3), l1));
                l++;
            }
        }
          goto _L35
        throw new ParseException("saw ~; without matching ~[ or ~<", j1);
_L28:
        if (k < 0 || !(stack.elementAt(k) instanceof LispChoiceFormat))
        {
            throw new ParseException("saw ~] without matching ~[", j1);
        }
        stack.push(popFormats(stack, l + (k + 3), l1));
        ((LispChoiceFormat)stack.elementAt(k)).choices = getFormats(stack, k + 3, stack.size());
        stack.setSize(k + 3);
        l = ((IntNum)stack.pop()).intValue();
        k = ((IntNum)stack.pop()).intValue();
          goto _L35
_L29:
        obj = new LispEscapeFormat(getParam(stack, l1), getParam(stack, l1 + 1), getParam(stack, l1 + 2));
          goto _L36
_L9:
        if (flag1)
        {
            stringbuffer.append(c2);
        }
        if (flag) goto _L35; else goto _L37
_L37:
        if (j1 < i1)
        {
            k4 = j1 + 1;
            if (Character.isWhitespace(ac[j1]))
            {
                break MISSING_BLOCK_LABEL_2971;
            }
            j1 = k4 - 1;
        }
          goto _L35
_L10:
        obj = FlushFormat.getInstance();
          goto _L36
_L26:
        obj = new LispTabulateFormat(getParam(stack, l1), getParam(stack, l1 + 1), getParam(stack, l1 + 2), flag1);
          goto _L36
_L13:
        obj = new LispFreshlineFormat(getParam(stack, l1));
          goto _L36
_L24:
        j4 = getParam(stack, l1);
        if (j4 == 0xc0000000)
        {
            j4 = 0;
        }
        obj = LispIndentFormat.getInstance(j4, flag);
          goto _L36
_L30:
        i4 = getParam(stack, l1);
        if (i4 == 0xc0000000)
        {
            i4 = 1;
        }
        if (!flag || !flag1);
        if (flag1 && flag)
        {
            byte0 = 82;
        } else
        if (flag1)
        {
            byte0 = 77;
        } else
        if (flag)
        {
            byte0 = 70;
        } else
        {
            byte0 = 78;
        }
        obj = LispNewlineFormat.getInstance(i4, byte0);
          goto _L36
_L34:
        if (i3 != 0) goto _L32; else goto _L38
_L38:
        stringbuffer.append(c2);
          goto _L35
_L32:
        k3 = getParam(stack, l1);
        if (k3 == 0xc0000000)
        {
            k3 = 1;
        }
        l3 = getParam(stack, l1 + 1);
        if (l3 == 0xc0000000)
        {
            if (c2 == '|')
            {
                l3 = 12;
            } else
            {
                l3 = 126;
            }
        }
        obj = LispCharacterFormat.getInstance(l3, k3, false, false);
          goto _L36
_L12:
        j3 = getParam(stack, l1);
        if (j3 == 0xc0000000)
        {
            j3 = 1;
        }
        obj = LispNewlineFormat.getInstance(j3, 76);
          goto _L36
        j1 = k4;
          goto _L37
        l2 = i2;
          goto _L39
    }

    public static Object[] asArray(Object obj)
    {
        if (obj instanceof Object[])
        {
            return (Object[])(Object[])obj;
        }
        if (!(obj instanceof Sequence))
        {
            return null;
        }
        int i = ((Sequence)obj).size();
        Object aobj[] = new Object[i];
        int j;
        int l;
        for (j = 0; obj instanceof Pair; j = l)
        {
            Pair pair = (Pair)obj;
            l = j + 1;
            aobj[j] = pair.getCar();
            obj = pair.getCdr();
        }

        if (j < i)
        {
            if (!(obj instanceof Sequence))
            {
                return null;
            }
            int k = j;
            Sequence sequence = (Sequence)obj;
            for (; j < i; j++)
            {
                aobj[j] = sequence.get(k + j);
            }

        }
        return aobj;
    }

    static Format[] getFormats(Vector vector, int i, int j)
    {
        Format aformat[] = new Format[j - i];
        for (int k = i; k < j; k++)
        {
            aformat[k - i] = (Format)vector.elementAt(k);
        }

        return aformat;
    }

    public static int getParam(Vector vector, int i)
    {
        if (i < vector.size())
        {
            Object obj = vector.elementAt(i);
            if (obj == "<from list>")
            {
                return 0xa0000000;
            }
            if (obj == "<from count>")
            {
                return 0xb0000000;
            }
            if (obj != "<unspecified>")
            {
                return getParam(obj, 0xc0000000);
            }
        }
        return 0xc0000000;
    }

    static Format popFormats(Vector vector, int i, int j)
    {
        Object obj;
        if (j == i + 1)
        {
            obj = (Format)vector.elementAt(i);
        } else
        {
            obj = new CompoundFormat(getFormats(vector, i, j));
        }
        vector.setSize(i);
        return ((Format) (obj));
    }
}
