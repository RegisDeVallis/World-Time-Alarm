// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.lispexpr;

import gnu.expr.Keyword;
import gnu.expr.QuoteExp;
import gnu.expr.Special;
import gnu.kawa.util.GeneralHashTable;
import gnu.lists.Convert;
import gnu.lists.F32Vector;
import gnu.lists.F64Vector;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.lists.S16Vector;
import gnu.lists.S32Vector;
import gnu.lists.S64Vector;
import gnu.lists.S8Vector;
import gnu.lists.Sequence;
import gnu.lists.SimpleVector;
import gnu.lists.U16Vector;
import gnu.lists.U32Vector;
import gnu.lists.U64Vector;
import gnu.lists.U8Vector;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.math.Complex;
import gnu.math.DComplex;
import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.RatNum;
import gnu.math.RealNum;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.math.BigDecimal;

// Referenced classes of package gnu.kawa.lispexpr:
//            ReadTable, ReaderParens, ReaderIgnoreRestOfLine, LispLanguage, 
//            ReadTableEntry

public class LispReader extends Lexer
{

    static final int SCM_COMPLEX = 1;
    public static final int SCM_NUMBERS = 1;
    public static final char TOKEN_ESCAPE_CHAR = 65535;
    protected boolean seenEscapes;
    GeneralHashTable sharedStructureTable;

    public LispReader(LineBufferedReader linebufferedreader)
    {
        super(linebufferedreader);
    }

    public LispReader(LineBufferedReader linebufferedreader, SourceMessages sourcemessages)
    {
        super(linebufferedreader, sourcemessages);
    }

    static char getReadCase()
    {
        char c;
        try
        {
            c = Environment.getCurrent().get("symbol-read-case", "P").toString().charAt(0);
        }
        catch (Exception exception)
        {
            return 'P';
        }
        if (c != 'P')
        {
            if (c == 'u')
            {
                return 'U';
            }
            if (c == 'd' || c == 'l' || c == 'L')
            {
                return 'D';
            }
            if (c == 'i')
            {
                return 'I';
            }
        }
        return c;
    }

    private boolean isPotentialNumber(char ac[], int i, int j)
    {
        boolean flag;
        int k;
        int l;
        flag = true;
        k = 0;
        l = i;
_L2:
        char c;
        if (l >= j)
        {
            break MISSING_BLOCK_LABEL_118;
        }
        c = ac[l];
        if (!Character.isDigit(c))
        {
            break; /* Loop/switch isn't completed */
        }
        k++;
_L3:
        l++;
        if (true) goto _L2; else goto _L1
_L1:
label0:
        {
            if (c != '-' && c != '+')
            {
                break label0;
            }
            if (l + 1 == j)
            {
                return false;
            }
        }
          goto _L3
        continue; /* Loop/switch isn't completed */
        if (c == '#')
        {
            return flag;
        }
        if (!Character.isLetter(c) && c != '/' && c != '_' && c != '^')
        {
            continue; /* Loop/switch isn't completed */
        }
        if (l != i) goto _L3; else goto _L4
_L4:
        return false;
        if (c == '.') goto _L3; else goto _L5
_L5:
        return false;
        if (k <= 0)
        {
            flag = false;
        }
        return flag;
        if (true) goto _L2; else goto _L6
_L6:
    }

    public static Object parseNumber(CharSequence charsequence, int i)
    {
        char ac[];
        if (charsequence instanceof FString)
        {
            ac = ((FString)charsequence).data;
        } else
        {
            ac = charsequence.toString().toCharArray();
        }
        return parseNumber(ac, 0, charsequence.length(), '\0', i, 1);
    }

    public static Object parseNumber(char ac[], int i, int j, char c, int k, int l)
    {
        int i1 = i + j;
        if (i < i1) goto _L2; else goto _L1
_L1:
        Object obj2 = "no digits";
_L43:
        return obj2;
_L2:
        int j1;
        char c1;
        j1 = i + 1;
        c1 = ac[i];
_L19:
        int i6;
        char c12;
        if (c1 != '#')
        {
            break; /* Loop/switch isn't completed */
        }
        if (j1 >= i1)
        {
            j1;
            return "no digits";
        }
        i6 = j1 + 1;
        c12 = ac[j1];
        c12;
        JVM INSTR lookupswitch 12: default 168
    //                   66: 207
    //                   68: 254
    //                   69: 292
    //                   73: 292
    //                   79: 235
    //                   88: 273
    //                   98: 207
    //                   100: 254
    //                   101: 292
    //                   105: 292
    //                   111: 235
    //                   120: 273;
           goto _L3 _L4 _L5 _L6 _L6 _L7 _L8 _L4 _L5 _L6 _L6 _L7 _L8
_L3:
        int l6 = 0;
_L17:
        int i7 = Character.digit(c12, 10);
        if (i7 >= 0) goto _L10; else goto _L9
_L9:
        if (c12 != 'R' && c12 != 'r') goto _L12; else goto _L11
_L11:
        int j6;
        int j7;
        if (k != 0)
        {
            return "duplicate radix specifier";
        }
        if (l6 < 2 || l6 > 35)
        {
            return "invalid radix specifier";
        }
        k = l6;
        j6 = i6;
          goto _L13
_L4:
        if (k != 0)
        {
            return "duplicate radix specifier";
        }
        k = 2;
        j6 = i6;
_L15:
        if (j6 >= i1)
        {
            j6;
            return "no digits";
        }
        break; /* Loop/switch isn't completed */
_L7:
        if (k != 0)
        {
            return "duplicate radix specifier";
        }
        k = 8;
        j6 = i6;
        continue; /* Loop/switch isn't completed */
_L5:
        if (k != 0)
        {
            return "duplicate radix specifier";
        }
        k = 10;
        j6 = i6;
        continue; /* Loop/switch isn't completed */
_L8:
        if (k != 0)
        {
            return "duplicate radix specifier";
        }
        k = 16;
        j6 = i6;
        continue; /* Loop/switch isn't completed */
_L6:
        if (c != 0)
        {
            if (c == ' ')
            {
                return "non-prefix exactness specifier";
            } else
            {
                return "duplicate exactness specifier";
            }
        }
        c = c12;
        j6 = i6;
        continue; /* Loop/switch isn't completed */
_L10:
        l6 = i7 + l6 * 10;
        if (i6 >= i1)
        {
            return "missing letter after '#'";
        }
        j7 = i6 + 1;
        c12 = ac[i6];
        i6 = j7;
        continue; /* Loop/switch isn't completed */
_L13:
        if (true) goto _L15; else goto _L14
_L14:
        break; /* Loop/switch isn't completed */
_L12:
        return (new StringBuilder()).append("unknown modifier '#").append(c12).append('\'').toString();
        if (true) goto _L17; else goto _L16
_L16:
        int k6 = j6 + 1;
        c1 = ac[j6];
        j1 = k6;
        if (true) goto _L19; else goto _L18
_L18:
        if (c == 0)
        {
            c = ' ';
        }
        if (k != 0) goto _L21; else goto _L20
_L20:
        int l5 = j;
_L23:
        if (--l5 >= 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        k = 10;
          goto _L21
        if (ac[i + l5] != '.') goto _L23; else goto _L22
_L22:
        k = 10;
_L21:
        boolean flag;
        boolean flag1;
        boolean flag2;
        if (c1 == '-')
        {
            flag = true;
        } else
        {
            flag = false;
        }
        flag1 = flag;
        if (c1 == '-' || c1 == '+')
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        int k1;
        int l1;
        int i2;
        int j2;
        IntNum intnum;
        long l2;
        int k2;
        int i3;
        byte byte0;
        int j3;
        boolean flag3;
        char c2;
        int k3;
        String s;
        double d;
        Object obj;
        double d1;
        BigDecimal bigdecimal;
        Double double1;
        Float float1;
        int l3;
        char c3;
        int i4;
        Object obj1;
        Complex complex;
        RealNum realnum;
        int j4;
        int k4;
        char c4;
        RealNum realnum1;
        DFloNum dflonum;
        int l4;
        StringBuilder stringbuilder;
        int i5;
        IntNum intnum1;
        Object obj3;
        double d2;
        boolean flag4;
        Object obj4;
        double d3;
        double d4;
        int j5;
        char c5;
        char c6;
        char c7;
        char c8;
        char c9;
        char c10;
        int k5;
        if (flag2)
        {
            if (j1 >= i1)
            {
                j1;
                return "no digits following sign";
            }
            k1 = j1 + 1;
            c1 = ac[j1];
        } else
        {
            k1 = j1;
        }
        if ((c1 == 'i' || c1 == 'I') && k1 == i1 && i == k1 - 2 && (l & 1) != 0)
        {
            char c11 = ac[i];
            if (c11 != '+' && c11 != '-')
            {
                return "no digits";
            }
            if (c == 'i' || c == 'I')
            {
                double d5;
                DComplex dcomplex;
                if (flag)
                {
                    d5 = -1D;
                } else
                {
                    d5 = 1.0D;
                }
                dcomplex = new DComplex(0.0D, d5);
                return dcomplex;
            }
            gnu.math.CComplex ccomplex;
            if (flag)
            {
                ccomplex = Complex.imMinusOne();
            } else
            {
                ccomplex = Complex.imOne();
            }
            return ccomplex;
        }
        k1 - 1;
        l1 = -1;
        i2 = -1;
        j2 = -1;
        intnum = null;
        l2 = 0L;
        k2 = Character.digit(c1, k);
        if (k2 < 0) goto _L25; else goto _L24
_L24:
        if (false && j2 < 0)
        {
            return "digit after '#' in number";
        }
        if (i2 < 0)
        {
            i2 = k1 - 1;
        }
        l2 = l2 * (long)k + (long)k2;
_L38:
        if (k1 != i1)
        {
            break; /* Loop/switch isn't completed */
        }
          goto _L26
_L25:
        c1;
        JVM INSTR lookupswitch 12: default 960
    //                   46: 966
    //                   47: 1135
    //                   68: 993
    //                   69: 993
    //                   70: 993
    //                   76: 993
    //                   83: 993
    //                   100: 993
    //                   101: 993
    //                   102: 993
    //                   108: 993
    //                   115: 993;
           goto _L27 _L28 _L29 _L30 _L30 _L30 _L30 _L30 _L30 _L30 _L30 _L30 _L30
_L27:
        k1--;
          goto _L26
_L28:
        if (j2 >= 0)
        {
            return "duplicate '.' in number";
        }
        if (k != 10)
        {
            return "'.' in non-decimal number";
        }
        j2 = k1 - 1;
        continue; /* Loop/switch isn't completed */
_L30:
        if (k1 != i1 && k == 10) goto _L32; else goto _L31
_L31:
        k1--;
          goto _L26
_L32:
        c10 = ac[k1];
        k5 = k1 - 1;
        if (c10 != '+' && c10 != '-') goto _L34; else goto _L33
_L33:
        if (++k1 >= i1 || Character.digit(ac[k1], 10) < 0)
        {
            return "missing exponent digits";
        }
          goto _L35
_L34:
        if (Character.digit(c10, 10) >= 0) goto _L35; else goto _L36
_L36:
        k1--;
          goto _L26
_L35:
        if (l1 >= 0)
        {
            return "duplicate exponent";
        }
        if (k != 10)
        {
            return "exponent in non-decimal number";
        }
        if (i2 < 0)
        {
            return "mantissa with no digits";
        }
        l1 = k5;
        while (++k1 < i1 && Character.digit(ac[k1], 10) >= 0) ;
          goto _L26
_L29:
        if (intnum != null)
        {
            return "multiple fraction symbol '/'";
        }
        if (i2 < 0)
        {
            return "no digits before fraction symbol '/'";
        }
        if (l1 >= 0 || j2 >= 0)
        {
            return "fraction symbol '/' following exponent or '.'";
        }
        intnum = valueOf(ac, i2, k1 - i2, k, flag, l2);
        i2 = -1;
        l2 = 0L;
        flag = false;
        if (true) goto _L38; else goto _L37
_L37:
        i3 = k1 + 1;
        c1 = ac[k1];
        k1 = i3;
        break MISSING_BLOCK_LABEL_689;
_L26:
        if (i2 < 0)
        {
            byte0 = 0;
            if (flag2)
            {
                j5 = k1 + 4;
                byte0 = 0;
                if (j5 < i1)
                {
                    c5 = ac[k1 + 3];
                    byte0 = 0;
                    if (c5 == '.')
                    {
                        c6 = ac[k1 + 4];
                        byte0 = 0;
                        if (c6 == '0')
                        {
                            if (ac[k1] == 'i' && ac[k1 + 1] == 'n' && ac[k1 + 2] == 'f')
                            {
                                byte0 = 105;
                            } else
                            {
                                c7 = ac[k1];
                                byte0 = 0;
                                if (c7 == 'n')
                                {
                                    c8 = ac[k1 + 1];
                                    byte0 = 0;
                                    if (c8 == 'a')
                                    {
                                        c9 = ac[k1 + 2];
                                        byte0 = 0;
                                        if (c9 == 'n')
                                        {
                                            byte0 = 110;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (byte0 == 0)
            {
                return "no digits";
            }
            j3 = k1 + 5;
        } else
        {
            j3 = k1;
            byte0 = 0;
        }
        if (true)
        {
            if (true);
        }
        if (c == 'i' || c == 'I' || c == ' ' && false)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        c2 = '\0';
        if (byte0 != 0)
        {
            if (byte0 == 105)
            {
                d4 = (1.0D / 0.0D);
            } else
            {
                d4 = (0.0D / 0.0D);
            }
            if (flag)
            {
                d4 = -d4;
            }
            obj = new DFloNum(d4);
        } else
        if (l1 >= 0 || j2 >= 0)
        {
            if (i2 > j2 && j2 >= 0)
            {
                i2 = j2;
            }
            if (intnum != null)
            {
                j3;
                return "floating-point number after fraction symbol '/'";
            }
            k3 = j3 - i2;
            s = new String(ac, i2, k3);
            c2 = '\0';
            if (l1 >= 0)
            {
                c2 = Character.toLowerCase(ac[l1]);
                if (c2 != 'e')
                {
                    l4 = l1 - i2;
                    stringbuilder = (new StringBuilder()).append(s.substring(0, l4)).append('e');
                    i5 = l4 + 1;
                    s = stringbuilder.append(s.substring(i5)).toString();
                }
            }
            d = Convert.parseDouble(s);
            if (flag)
            {
                d = -d;
            }
            obj = new DFloNum(d);
        } else
        {
            intnum1 = valueOf(ac, i2, j3 - i2, k, flag, l2);
            if (intnum == null)
            {
                obj3 = intnum1;
            } else
            if (intnum1.isZero())
            {
                flag4 = intnum.isZero();
                if (flag3)
                {
                    if (flag4)
                    {
                        d3 = (0.0D / 0.0D);
                    } else
                    if (flag1)
                    {
                        d3 = (-1.0D / 0.0D);
                    } else
                    {
                        d3 = (1.0D / 0.0D);
                    }
                    obj4 = new DFloNum(d3);
                } else
                {
                    if (flag4)
                    {
                        j3;
                        return "0/0 is undefined";
                    }
                    obj4 = RatNum.make(intnum, intnum1);
                }
                obj3 = obj4;
            } else
            {
                obj3 = RatNum.make(intnum, intnum1);
            }
            if (flag3 && ((RealNum) (obj3)).isExact())
            {
                if (flag1 && ((RealNum) (obj3)).isZero())
                {
                    d2 = 0.0D;
                } else
                {
                    d2 = ((RealNum) (obj3)).doubleValue();
                }
                obj = new DFloNum(d2);
                c2 = '\0';
            } else
            {
                obj = obj3;
                c2 = '\0';
            }
        }
        if (c == 'e' || c == 'E')
        {
            obj = ((RealNum) (obj)).toExact();
        }
        if (j3 >= i1) goto _L40; else goto _L39
_L39:
        l3 = j3 + 1;
        c3 = ac[j3];
        if (c3 != '@') goto _L42; else goto _L41
_L41:
        obj2 = parseNumber(ac, l3, i1 - l3, c, 10, l);
        if (!(obj2 instanceof String))
        {
            if (!(obj2 instanceof RealNum))
            {
                return "invalid complex polar constant";
            }
            realnum1 = (RealNum)obj2;
            if (((RealNum) (obj)).isZero() && !realnum1.isExact())
            {
                dflonum = new DFloNum(0.0D);
                return dflonum;
            } else
            {
                return Complex.polar(((RealNum) (obj)), realnum1);
            }
        }
          goto _L43
_L42:
        if (c3 == '-' || c3 == '+')
        {
            i4 = l3 - 1;
            obj1 = parseNumber(ac, i4, i1 - i4, c, 10, l);
            if (obj1 instanceof String)
            {
                return obj1;
            }
            if (!(obj1 instanceof Complex))
            {
                return (new StringBuilder()).append("invalid numeric constant (").append(obj1).append(")").toString();
            }
            complex = (Complex)obj1;
            if (!complex.re().isZero())
            {
                return "invalid numeric constant";
            } else
            {
                realnum = complex.im();
                return Complex.make(((RealNum) (obj)), realnum);
            }
        }
        j4 = 0;
_L49:
        if (Character.isLetter(c3)) goto _L45; else goto _L44
_L44:
        l3--;
_L47:
        if (j4 == 1)
        {
            c4 = ac[l3 - 1];
            if (c4 == 'i' || c4 == 'I')
            {
                if (l3 < i1)
                {
                    return "junk after imaginary suffix 'i'";
                } else
                {
                    return Complex.make(IntNum.zero(), ((RealNum) (obj)));
                }
            }
        }
        break; /* Loop/switch isn't completed */
_L45:
        j4++;
        if (l3 == i1) goto _L47; else goto _L46
_L46:
        k4 = l3 + 1;
        c3 = ac[l3];
        l3 = k4;
        if (true) goto _L49; else goto _L48
_L48:
        return "excess junk after number";
_L40:
        if (!(obj instanceof DFloNum) || c2 <= 0 || c2 == 'e') goto _L51; else goto _L50
_L50:
        d1 = ((RealNum) (obj)).doubleValue();
        c2;
        JVM INSTR lookupswitch 4: default 2132
    //                   100: 2152
    //                   102: 2138
    //                   108: 2165
    //                   115: 2138;
           goto _L51 _L52 _L53 _L54 _L53
_L51:
        j3;
        return obj;
_L53:
        float1 = Float.valueOf((float)d1);
        j3;
        return float1;
_L52:
        double1 = Double.valueOf(d1);
        j3;
        return double1;
_L54:
        bigdecimal = BigDecimal.valueOf(d1);
        j3;
        return bigdecimal;
    }

    public static Object readCharacter(LispReader lispreader)
        throws IOException, SyntaxException
    {
        int j;
        char ac[];
        int k;
        String s;
        char c;
        int i = lispreader.read();
        if (i < 0)
        {
            lispreader.eofError("unexpected EOF in character literal");
        }
        j = lispreader.tokenBufferLength;
        lispreader.tokenBufferAppend(i);
        lispreader.readToken(lispreader.read(), 'D', ReadTable.getCurrent());
        ac = lispreader.tokenBuffer;
        k = lispreader.tokenBufferLength - j;
        if (k == 1)
        {
            return Char.make(ac[j]);
        }
        s = new String(ac, j, k);
        int l = Char.nameToChar(s);
        if (l >= 0)
        {
            return Char.make(l);
        }
        c = ac[j];
        if (c != 'x' && c != 'X') goto _L2; else goto _L1
_L1:
        int i1;
        int j1;
        i1 = 0;
        j1 = 1;
_L6:
        int k1;
        if (j1 == k)
        {
            return Char.make(i1);
        }
        k1 = Character.digit(ac[j + j1], 16);
        if (k1 >= 0) goto _L3; else goto _L2
_L2:
        int i2;
        int j2;
label0:
        {
            int l1 = Character.digit(c, 8);
            if (l1 >= 0)
            {
                i2 = l1;
                j2 = 1;
                break label0;
            }
        }
        break; /* Loop/switch isn't completed */
_L3:
        if ((i1 = k1 + i1 * 16) > 0x10ffff) goto _L2; else goto _L4
_L4:
        j1++;
        if (true) goto _L6; else goto _L5
        i2 = k2 + i2 * 8;
        j2++;
        if (j2 == k)
        {
            return Char.make(i2);
        }
        k2 = Character.digit(ac[j + j2], 8);
        if (k2 >= 0)
        {
            break MISSING_BLOCK_LABEL_259;
        }
_L5:
        lispreader.error((new StringBuilder()).append("unknown character name: ").append(s).toString());
        return Char.make(63);
    }

    public static Object readNumberWithRadix(int i, LispReader lispreader, int j)
        throws IOException, SyntaxException
    {
        int k = lispreader.tokenBufferLength - i;
        lispreader.readToken(lispreader.read(), 'P', ReadTable.getCurrent());
        int l = lispreader.tokenBufferLength;
        Object obj;
        if (k == l)
        {
            lispreader.error("missing numeric token");
            obj = IntNum.zero();
        } else
        {
            obj = parseNumber(lispreader.tokenBuffer, k, l - k, '\0', j, 0);
            if (obj instanceof String)
            {
                lispreader.error((String)obj);
                return IntNum.zero();
            }
            if (obj == null)
            {
                lispreader.error("invalid numeric constant");
                return IntNum.zero();
            }
        }
        return obj;
    }

    public static SimpleVector readSimpleVector(LispReader lispreader, char c)
        throws IOException, SyntaxException
    {
        int i;
        Sequence sequence;
        i = 0;
        do
        {
            int j = lispreader.read();
            if (j < 0)
            {
                lispreader.eofError("unexpected EOF reading uniform vector");
            }
            int k = Character.digit((char)j, 10);
            if (k < 0)
            {
                if (i != 8 && i != 16 && i != 32 && i != 64 || c == 'F' && i < 32 || j != 40)
                {
                    lispreader.error("invalid uniform vector syntax");
                    return null;
                }
                break;
            }
            i = k + i * 10;
        } while (true);
        Object obj = ReaderParens.readList(lispreader, 40, -1, 41);
        if (LList.listLength(obj, false) < 0)
        {
            lispreader.error("invalid elements in uniform vector syntax");
            return null;
        }
        sequence = (Sequence)obj;
        c;
        JVM INSTR lookupswitch 3: default 164
    //                   70: 166
    //                   83: 192
    //                   85: 236;
           goto _L1 _L2 _L3 _L4
_L1:
        return null;
_L2:
        i;
        JVM INSTR lookupswitch 2: default 192
    //                   32: 292
    //                   64: 302;
           goto _L3 _L5 _L6
_L3:
        i;
        JVM INSTR lookupswitch 4: default 236
    //                   8: 312
    //                   16: 322
    //                   32: 332
    //                   64: 342;
           goto _L4 _L7 _L8 _L9 _L10
_L4:
        switch (i)
        {
        default:
            return null;

        case 8: // '\b'
            return new U8Vector(sequence);

        case 16: // '\020'
            return new U16Vector(sequence);

        case 32: // ' '
            return new U32Vector(sequence);

        case 64: // '@'
            return new U64Vector(sequence);
        }
_L5:
        return new F32Vector(sequence);
_L6:
        return new F64Vector(sequence);
_L7:
        return new S8Vector(sequence);
_L8:
        return new S16Vector(sequence);
_L9:
        return new S32Vector(sequence);
_L10:
        return new S64Vector(sequence);
    }

    public static Object readSpecial(LispReader lispreader)
        throws IOException, SyntaxException
    {
        int i = lispreader.read();
        if (i < 0)
        {
            lispreader.eofError("unexpected EOF in #! special form");
        }
        Values values;
        if (i == 47 && lispreader.getLineNumber() == 0 && lispreader.getColumnNumber() == 3)
        {
            ReaderIgnoreRestOfLine.getInstance().read(lispreader, 35, 1);
            values = Values.empty;
        } else
        {
            int j = lispreader.tokenBufferLength;
            lispreader.tokenBufferAppend(i);
            lispreader.readToken(lispreader.read(), 'D', ReadTable.getCurrent());
            int k = lispreader.tokenBufferLength - j;
            String s = new String(lispreader.tokenBuffer, j, k);
            if (s.equals("optional"))
            {
                return Special.optional;
            }
            if (s.equals("rest"))
            {
                return Special.rest;
            }
            if (s.equals("key"))
            {
                return Special.key;
            }
            if (s.equals("eof"))
            {
                return Special.eof;
            }
            if (s.equals("void"))
            {
                return QuoteExp.voidExp;
            }
            if (s.equals("default"))
            {
                return Special.dfault;
            }
            if (s.equals("undefined"))
            {
                return Special.undefined;
            }
            if (s.equals("abstract"))
            {
                return Special.abstractSpecial;
            }
            boolean flag = s.equals("null");
            values = null;
            if (!flag)
            {
                lispreader.error((new StringBuilder()).append("unknown named constant #!").append(s).toString());
                return null;
            }
        }
        return values;
    }

    private static IntNum valueOf(char ac[], int i, int j, int k, boolean flag, long l)
    {
        if (j + k <= 28)
        {
            if (flag)
            {
                l = -l;
            }
            return IntNum.make(l);
        } else
        {
            return IntNum.valueOf(ac, i, j, k, flag);
        }
    }

    Object handlePostfix(Object obj, ReadTable readtable, int i, int j)
        throws IOException, SyntaxException
    {
        if (obj == QuoteExp.voidExp)
        {
            obj = Values.empty;
        }
        do
        {
            int k = port.peek();
            if (k < 0 || k != readtable.postfixLookupOperator)
            {
                return obj;
            }
            port.read();
            if (!validPostfixLookupStart(port.peek(), readtable))
            {
                unread();
                return obj;
            }
            int l = port.read();
            Object obj1 = readValues(l, readtable.lookup(l), readtable);
            Pair pair = LList.list2(obj, LList.list2(readtable.makeSymbol("quasiquote"), obj1));
            obj = PairWithPosition.make(LispLanguage.lookup_sym, pair, port.getName(), i + 1, j + 1);
        } while (true);
    }

    protected Object makeNil()
    {
        return LList.Empty;
    }

    protected Pair makePair(Object obj, int i, int j)
    {
        return makePair(obj, LList.Empty, i, j);
    }

    protected Pair makePair(Object obj, Object obj1, int i, int j)
    {
        String s = port.getName();
        if (s != null && i >= 0)
        {
            return PairWithPosition.make(obj, obj1, s, i + 1, j + 1);
        } else
        {
            return Pair.make(obj, obj1);
        }
    }

    protected Object readAndHandleToken(int i, int j, ReadTable readtable)
        throws IOException, SyntaxException
    {
        readToken(i, getReadCase(), readtable);
        int k = tokenBufferLength;
        if (!seenEscapes)
        {
            Object obj1 = parseNumber(tokenBuffer, j, k - j, '\0', 0, 1);
            if (obj1 != null && !(obj1 instanceof String))
            {
                return obj1;
            }
        }
        char c = getReadCase();
        boolean flag;
        int l;
        int i1;
        int j1;
        int k1;
        int i2;
        if (c == 'I')
        {
            int j4 = 0;
            int k4 = 0;
            int l4 = j;
            while (l4 < k) 
            {
                char c2 = tokenBuffer[l4];
                if (c2 == '\uFFFF')
                {
                    l4++;
                } else
                if (Character.isLowerCase(c2))
                {
                    k4++;
                } else
                if (Character.isUpperCase(c2))
                {
                    j4++;
                }
                l4++;
            }
            int l1;
            char ac2[];
            if (k4 == 0)
            {
                c = 'D';
            } else
            if (j4 == 0)
            {
                c = 'U';
            } else
            {
                c = 'P';
            }
        }
        if (k >= j + 2 && tokenBuffer[k - 1] == '}' && tokenBuffer[k - 2] != '\uFFFF' && peek() == 58)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        l = -1;
        i1 = -1;
        j1 = -1;
        k1 = 0;
        l1 = j;
        i2 = j;
        do
        {
            if (l1 < k)
            {
                char c1 = tokenBuffer[l1];
                int j2;
                int k2;
                int l2;
                String s;
                int i3;
                String s1;
                String s2;
                int j3;
                String s3;
                int k3;
                Object obj;
                char ac[];
                int l3;
                int i4;
                if (c1 == '\uFFFF')
                {
                    if (++l1 < k)
                    {
                        ac2 = tokenBuffer;
                        i4 = i2 + 1;
                        ac2[i2] = tokenBuffer[l1];
                    } else
                    {
                        i4 = i2;
                    }
                } else
                {
                    if (flag)
                    {
                        if (c1 == '{')
                        {
                            char ac1[];
                            if (i1 < 0)
                            {
                                i1 = i2;
                            } else
                            if (k1 != 0);
                            k1++;
                        } else
                        if (c1 == '}' && --k1 >= 0 && k1 == 0 && j1 < 0)
                        {
                            j1 = i2;
                        }
                    }
                    if (k1 <= 0)
                    {
                        if (c1 == ':')
                        {
                            if (l >= 0)
                            {
                                l = -1;
                            } else
                            {
                                l = i2;
                            }
                        } else
                        if (c == 'U')
                        {
                            c1 = Character.toUpperCase(c1);
                        } else
                        if (c == 'D')
                        {
                            c1 = Character.toLowerCase(c1);
                        }
                    }
                    ac1 = tokenBuffer;
                    i4 = i2 + 1;
                    ac1[i2] = c1;
                }
            } else
            {
                j2 = i2;
                k2 = j2 - j;
                if (i1 >= 0 && j1 > i1)
                {
                    if (i1 > 0)
                    {
                        ac = tokenBuffer;
                        l3 = i1 - j;
                        s2 = new String(ac, j, l3);
                    } else
                    {
                        s2 = null;
                    }
                    j3 = i1 + 1;
                    s3 = new String(tokenBuffer, j3, j1 - j3);
                    read();
                    k3 = read();
                    obj = readValues(k3, readtable.lookup(k3), readtable);
                    if (!(obj instanceof SimpleSymbol))
                    {
                        error("expected identifier in symbol after '{URI}:'");
                    }
                    return Symbol.valueOf(obj.toString(), s3, s2);
                }
                if (readtable.initialColonIsKeyword && l == j && k2 > 1)
                {
                    i3 = j + 1;
                    s1 = new String(tokenBuffer, i3, j2 - i3);
                    return Keyword.make(s1.intern());
                }
                if (readtable.finalColonIsKeyword)
                {
                    l2 = j2 - 1;
                    if (l == l2 && (k2 > 1 || seenEscapes))
                    {
                        s = new String(tokenBuffer, j, k2 - 1);
                        return Keyword.make(s.intern());
                    }
                }
                return readtable.makeSymbol(new String(tokenBuffer, j, k2));
            }
            l1++;
            i2 = i4;
        } while (true);
    }

    public Object readCommand()
        throws IOException, SyntaxException
    {
        return readObject();
    }

    public int readEscape()
        throws IOException, SyntaxException
    {
        int i = read();
        if (i < 0)
        {
            eofError("unexpected EOF in character literal");
            return -1;
        } else
        {
            return readEscape(i);
        }
    }

    public final int readEscape(int i)
        throws IOException, SyntaxException
    {
        (char)i;
        JVM INSTR lookupswitch 28: default 236
    //                   9: 303
    //                   10: 303
    //                   13: 303
    //                   32: 303
    //                   34: 286
    //                   48: 516
    //                   49: 516
    //                   50: 516
    //                   51: 516
    //                   52: 516
    //                   53: 516
    //                   54: 516
    //                   55: 516
    //                   67: 461
    //                   77: 416
    //                   88: 635
    //                   92: 292
    //                   94: 480
    //                   97: 238
    //                   98: 244
    //                   101: 280
    //                   102: 268
    //                   110: 256
    //                   114: 274
    //                   116: 250
    //                   117: 576
    //                   118: 262
    //                   120: 635;
           goto _L1 _L2 _L2 _L2 _L2 _L3 _L4 _L4 _L4 _L4 _L4 _L4 _L4 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L17 _L18 _L7
_L1:
        return i;
_L10:
        i = 7;
        continue; /* Loop/switch isn't completed */
_L11:
        i = 8;
        continue; /* Loop/switch isn't completed */
_L16:
        i = 9;
        continue; /* Loop/switch isn't completed */
_L14:
        i = 10;
        continue; /* Loop/switch isn't completed */
_L18:
        i = 11;
        continue; /* Loop/switch isn't completed */
_L13:
        i = 12;
        continue; /* Loop/switch isn't completed */
_L15:
        i = 13;
        continue; /* Loop/switch isn't completed */
_L12:
        i = 27;
        continue; /* Loop/switch isn't completed */
_L3:
        i = 34;
        continue; /* Loop/switch isn't completed */
_L8:
        i = 92;
        continue; /* Loop/switch isn't completed */
_L20:
        i = read();
_L2:
        int j2;
        if (i < 0)
        {
            eofError("unexpected EOF in literal");
            return -1;
        }
        if (i != 10)
        {
            if (i != 13)
            {
                continue; /* Loop/switch isn't completed */
            }
            if (peek() == 10)
            {
                skip();
            }
            i = 10;
        }
_L21:
        if (i != 10)
        {
            continue; /* Loop/switch isn't completed */
        }
_L23:
        j2 = read();
        if (j2 < 0)
        {
            eofError("unexpected EOF in literal");
            return -1;
        }
        continue; /* Loop/switch isn't completed */
        if (i == 32 || i == 9) goto _L20; else goto _L19
_L19:
        unread(i);
          goto _L21
        if (j2 == 32 || j2 == 9) goto _L23; else goto _L22
_L22:
        unread(j2);
        return -2;
_L6:
        if (read() != 45)
        {
            error("Invalid escape character syntax");
            return 63;
        }
        int i2 = read();
        if (i2 == 92)
        {
            i2 = readEscape();
        }
        return i2 | 0x80;
_L5:
        if (read() != 45)
        {
            error("Invalid escape character syntax");
            return 63;
        }
_L9:
        int l1 = read();
        if (l1 == 92)
        {
            l1 = readEscape();
        }
        if (l1 == 63)
        {
            return 127;
        } else
        {
            return l1 & 0x9f;
        }
_L4:
        i -= 48;
        int i1 = 0;
        int j1;
        do
        {
            if (++i1 >= 3)
            {
                continue; /* Loop/switch isn't completed */
            }
            j1 = read();
            int k1 = Character.digit((char)j1, 8);
            if (k1 < 0)
            {
                break;
            }
            i = k1 + (i << 3);
        } while (true);
        if (j1 >= 0)
        {
            unread(j1);
        }
        if (true) goto _L1; else goto _L17
_L17:
        i = 0;
        int j = 4;
        while (--j >= 0) 
        {
            int k = read();
            if (k < 0)
            {
                eofError("premature EOF in \\u escape");
            }
            int l = Character.digit((char)k, 16);
            if (l < 0)
            {
                error("non-hex character following \\u");
            }
            i = l + i * 16;
        }
        if (true) goto _L1; else goto _L7
_L7:
        return readHexEscape();
    }

    public int readHexEscape()
        throws IOException, SyntaxException
    {
        int i = 0;
        int j;
        do
        {
            j = read();
            int k = Character.digit((char)j, 16);
            if (k < 0)
            {
                break;
            }
            i = k + (i << 4);
        } while (true);
        if (j != 59 && j >= 0)
        {
            unread(j);
        }
        return i;
    }

    public final void readNestedComment(char c, char c1)
        throws IOException, SyntaxException
    {
        int i;
        int j;
        int k;
        i = 1;
        j = port.getLineNumber();
        k = port.getColumnNumber();
_L6:
        int l = read();
        if (l != 124) goto _L2; else goto _L1
_L1:
        l = read();
        if (l == c)
        {
            i--;
        }
_L4:
        if (l < 0)
        {
            eofError((new StringBuilder()).append("unexpected end-of-file in ").append(c).append(c1).append(" comment starting here").toString(), j + 1, k - 1);
            return;
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (l == c)
        {
            l = read();
            if (l == c1)
            {
                i++;
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (i > 0) goto _L6; else goto _L5
_L5:
    }

    public Object readObject()
        throws IOException, SyntaxException
    {
        char c;
        int i;
        c = ((InPort)port).readState;
        i = tokenBufferLength;
        ((InPort)port).readState = ' ';
        ReadTable readtable = ReadTable.getCurrent();
_L2:
        int j;
        int k;
        int l;
        j = port.getLineNumber();
        k = port.getColumnNumber();
        l = port.read();
        if (l >= 0)
        {
            break MISSING_BLOCK_LABEL_89;
        }
        Object obj = Sequence.eofValue;
        tokenBufferLength = i;
        ((InPort)port).readState = c;
        return obj;
        Object obj1 = readValues(l, readtable);
        if (obj1 == Values.empty) goto _L2; else goto _L1
_L1:
        Object obj2 = handlePostfix(obj1, readtable, j, k);
        tokenBufferLength = i;
        ((InPort)port).readState = c;
        return obj2;
        Exception exception;
        exception;
        tokenBufferLength = i;
        ((InPort)port).readState = c;
        throw exception;
    }

    public final Object readObject(int i)
        throws IOException, SyntaxException
    {
        unread(i);
        return readObject();
    }

    void readToken(int i, char c, ReadTable readtable)
        throws IOException, SyntaxException
    {
        boolean flag;
        int j;
        flag = false;
        j = 0;
_L2:
        ReadTableEntry readtableentry;
        int k;
        if (i < 0)
        {
            if (!flag)
            {
                break; /* Loop/switch isn't completed */
            }
            eofError("unexpected EOF between escapes");
        }
        readtableentry = readtable.lookup(i);
        k = readtableentry.getKind();
        if (k == 0)
        {
            if (flag)
            {
                tokenBufferAppend(65535);
                tokenBufferAppend(i);
            } else
            {
label0:
                {
                    if (i != 125 || --j < 0)
                    {
                        break label0;
                    }
                    tokenBufferAppend(i);
                }
            }
        } else
        {
            if (i == readtable.postfixLookupOperator && !flag)
            {
                int i1 = port.peek();
                if (i1 == readtable.postfixLookupOperator)
                {
                    unread(i);
                    return;
                }
                if (validPostfixLookupStart(i1, readtable))
                {
                    k = 5;
                }
            }
            if (k == 3)
            {
                int l = read();
                if (l < 0)
                {
                    eofError("unexpected EOF after single escape");
                }
                if (readtable.hexEscapeAfterBackslash && (l == 120 || l == 88))
                {
                    l = readHexEscape();
                }
                tokenBufferAppend(65535);
                tokenBufferAppend(l);
                seenEscapes = true;
            } else
            if (k == 4)
            {
                if (!flag)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                seenEscapes = true;
            } else
            {
label1:
                {
                    if (!flag)
                    {
                        break label1;
                    }
                    tokenBufferAppend(65535);
                    tokenBufferAppend(i);
                }
            }
        }
_L4:
        i = read();
        if (true) goto _L2; else goto _L1
        unread(i);
_L1:
        return;
        switch (k)
        {
        case 1: // '\001'
            unread(i);
            return;

        case 2: // '\002'
            if (i == 123 && readtableentry == ReadTableEntry.brace)
            {
                j++;
            }
            // fall through

        case 6: // '\006'
            tokenBufferAppend(i);
            break;

        case 4: // '\004'
            flag = true;
            seenEscapes = true;
            break;

        case 5: // '\005'
            unread(i);
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public Object readValues(int i, ReadTable readtable)
        throws IOException, SyntaxException
    {
        return readValues(i, readtable.lookup(i), readtable);
    }

    public Object readValues(int i, ReadTableEntry readtableentry, ReadTable readtable)
        throws IOException, SyntaxException
    {
        int j = tokenBufferLength;
        seenEscapes = false;
        switch (readtableentry.getKind())
        {
        case 2: // '\002'
        case 3: // '\003'
        case 4: // '\004'
        default:
            return readAndHandleToken(i, j, readtable);

        case 0: // '\0'
            String s = (new StringBuilder()).append("invalid character #\\").append((char)i).toString();
            if (interactive)
            {
                fatal(s);
            } else
            {
                error(s);
            }
            return Values.empty;

        case 1: // '\001'
            return Values.empty;

        case 5: // '\005'
        case 6: // '\006'
            return readtableentry.read(this, i, -1);
        }
    }

    protected void setCdr(Object obj, Object obj1)
    {
        ((Pair)obj).setCdrBackdoor(obj1);
    }

    protected boolean validPostfixLookupStart(int i, ReadTable readtable)
        throws IOException
    {
        if (i >= 0 && i != 58 && i != readtable.postfixLookupOperator)
        {
            if (i == 44)
            {
                return true;
            }
            int j = readtable.lookup(i).getKind();
            if (j == 2 || j == 6 || j == 4 || j == 3)
            {
                return true;
            }
        }
        return false;
    }
}
