// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.ecmascript;

import gnu.expr.QuoteExp;
import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Hashtable;

// Referenced classes of package gnu.ecmascript:
//            Reserved

public class Lexer extends gnu.text.Lexer
{

    public static final Char colonToken = Char.make(58);
    public static final Char commaToken = Char.make(44);
    public static final Char condToken = Char.make(63);
    public static final Char dotToken = Char.make(46);
    public static final Reserved elseToken = new Reserved("else", 38);
    public static final Object eofToken;
    public static final Object eolToken = Char.make(10);
    public static final Char equalToken = Char.make(61);
    public static final Char lbraceToken = Char.make(123);
    public static final Char lbracketToken = Char.make(91);
    public static final Char lparenToken = Char.make(40);
    public static final Reserved newToken = new Reserved("new", 39);
    public static final Char notToken = Char.make(33);
    public static final Char rbraceToken = Char.make(125);
    public static final Char rbracketToken = Char.make(93);
    static Hashtable reserved;
    public static final Char rparenToken = Char.make(41);
    public static final Char semicolonToken = Char.make(59);
    public static final Char tildeToken = Char.make(126);
    private boolean prevWasCR;

    public Lexer(InPort inport)
    {
        super(inport);
        prevWasCR = false;
    }

    public static Object checkReserved(String s)
    {
        if (reserved == null)
        {
            initReserved();
        }
        return reserved.get(s);
    }

    public static Object getToken(InPort inport)
        throws IOException, SyntaxException
    {
        return (new Lexer(inport)).getToken();
    }

    static void initReserved()
    {
        gnu/ecmascript/Lexer;
        JVM INSTR monitorenter ;
        if (reserved == null)
        {
            reserved = new Hashtable(20);
            reserved.put("null", new QuoteExp(null));
            reserved.put("true", new QuoteExp(Boolean.TRUE));
            reserved.put("false", new QuoteExp(Boolean.FALSE));
            reserved.put("var", new Reserved("var", 30));
            reserved.put("if", new Reserved("if", 31));
            reserved.put("while", new Reserved("while", 32));
            reserved.put("for", new Reserved("for", 33));
            reserved.put("continue", new Reserved("continue", 34));
            reserved.put("break", new Reserved("break", 35));
            reserved.put("return", new Reserved("return", 36));
            reserved.put("with", new Reserved("with", 37));
            reserved.put("function", new Reserved("function", 41));
            reserved.put("this", new Reserved("this", 40));
            reserved.put("else", elseToken);
            reserved.put("new", newToken);
        }
        gnu/ecmascript/Lexer;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public static void main(String args[])
    {
        Lexer lexer = new Lexer(InPort.inDefault());
        Object obj;
        Object obj1;
        do
        {
            obj = lexer.getToken();
            OutPort outport = OutPort.outDefault();
            outport.print("token:");
            outport.print(obj);
            outport.println((new StringBuilder()).append(" [class:").append(obj.getClass()).append("]").toString());
            obj1 = Sequence.eofValue;
        } while (obj != obj1);
        return;
        Exception exception;
        exception;
        System.err.println((new StringBuilder()).append("caught exception:").append(exception).toString());
        return;
    }

    public String getIdentifier(int i)
        throws IOException
    {
        StringBuffer stringbuffer;
        int j = port.pos;
        int k = j - 1;
        int l = port.limit;
        char ac[];
        for (ac = port.buffer; j < l && Character.isJavaIdentifierPart(ac[j]); j++) { }
        port.pos = j;
        if (j < l)
        {
            return new String(ac, k, j - k);
        }
        stringbuffer = new StringBuffer();
        stringbuffer.append(ac, k, j - k);
_L3:
        int i1 = port.read();
        if (i1 >= 0) goto _L2; else goto _L1
_L1:
        return stringbuffer.toString();
_L2:
label0:
        {
            if (!Character.isJavaIdentifierPart((char)i1))
            {
                break label0;
            }
            stringbuffer.append((char)i1);
        }
          goto _L3
        port.unread_quick();
          goto _L1
    }

    public Double getNumericLiteral(int i)
        throws IOException
    {
        byte byte0;
        boolean flag;
        StringBuffer stringbuffer;
        int k;
        byte0 = 10;
        int j;
        if (i == 48)
        {
            i = read();
            long l;
            char c;
            if (i == 120 || i == 88)
            {
                byte0 = 16;
                i = read();
            } else
            if (i != 46 && i != 101 && i != 69)
            {
                byte0 = 8;
            }
        }
        j = port.pos;
        if (i >= 0)
        {
            j--;
        }
        port.pos = j;
        l = readDigitsInBuffer(port, byte0);
        if (port.pos > j)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag && port.pos < port.limit)
        {
            c = port.buffer[port.pos];
            if (!Character.isLetterOrDigit(c) && c != '.')
            {
                double d;
                if (l >= 0L)
                {
                    d = l;
                } else
                {
                    d = IntNum.valueOf(port.buffer, j, port.pos - j, byte0, false).doubleValue();
                }
                return new Double(d);
            }
        }
        if (byte0 != 10)
        {
            error("invalid character in non-decimal number");
        }
        stringbuffer = new StringBuffer(20);
        if (flag)
        {
            stringbuffer.append(port.buffer, j, port.pos - j);
        }
        k = -1;
_L4:
        int i1;
        do
        {
label0:
            {
                i1 = port.read();
                if (Character.digit((char)i1, byte0) < 0)
                {
                    break label0;
                }
                flag = true;
                stringbuffer.append((char)i1);
            }
        } while (true);
        int j1 = 0;
        i1;
        JVM INSTR lookupswitch 3: default 344
    //                   46: 390
    //                   69: 423
    //                   101: 423;
           goto _L1 _L2 _L3 _L3
_L1:
        if (i1 >= 0)
        {
            port.unread();
        }
        if (j1 != 0)
        {
            stringbuffer.append('e');
            stringbuffer.append(j1);
        }
        return new Double(stringbuffer.toString());
_L2:
        if (k >= 0)
        {
            error("duplicate '.' in number");
        } else
        {
            k = stringbuffer.length();
            stringbuffer.append('.');
        }
          goto _L4
_L3:
        j1 = 0;
        if (byte0 != 10) goto _L1; else goto _L5
_L5:
        int k1 = port.peek();
        if (k1 == 43 || k1 == 45) goto _L7; else goto _L6
_L6:
        int l1;
        l1 = Character.digit((char)k1, 10);
        j1 = 0;
        if (l1 < 0) goto _L1; else goto _L7
_L7:
        if (!flag)
        {
            error("mantissa with no digits");
        }
        j1 = readOptionalExponent();
        i1 = read();
          goto _L1
    }

    public String getStringLiteral(char c)
        throws IOException, SyntaxException
    {
        int i;
        int j;
        int k;
        char ac[];
        i = port.pos;
        j = i;
        k = port.limit;
        ac = port.buffer;
_L4:
        if (i >= k) goto _L2; else goto _L1
_L1:
        char c3;
        c3 = ac[i];
        if (c3 == c)
        {
            port.pos = i + 1;
            return new String(ac, j, i - j);
        }
        if (c3 != '\\' && c3 != '\n' && c3 != '\r') goto _L3; else goto _L2
_L2:
        StringBuffer stringbuffer;
        int l;
        port.pos = i;
        stringbuffer = new StringBuffer();
        stringbuffer.append(ac, j, i - j);
        break MISSING_BLOCK_LABEL_120;
_L3:
        i++;
          goto _L4
_L18:
        l = port.read();
        if (l == c)
        {
            return stringbuffer.toString();
        }
        if (l < 0)
        {
            eofError("unterminated string literal");
        }
        if (l == 10 || l == 13)
        {
            fatal("string literal not terminated before end of line");
        }
        if (l != 92) goto _L6; else goto _L5
_L5:
        l = port.read();
        l;
        JVM INSTR lookupswitch 13: default 312
    //                   -1: 338
    //                   10: 345
    //                   13: 345
    //                   34: 326
    //                   39: 326
    //                   92: 326
    //                   98: 355
    //                   102: 376
    //                   110: 369
    //                   114: 383
    //                   116: 362
    //                   117: 390
    //                   120: 390;
           goto _L7 _L8 _L9 _L9 _L10 _L10 _L10 _L11 _L12 _L13 _L14 _L15 _L16 _L16
_L10:
        break; /* Loop/switch isn't completed */
_L7:
        if (l >= 48 && l <= 55) goto _L17; else goto _L6
_L6:
        stringbuffer.append((char)l);
          goto _L18
_L8:
        eofError("eof following '\\' in string");
_L9:
        fatal("line terminator following '\\' in string");
          goto _L6
_L11:
        l = 8;
          goto _L6
_L15:
        l = 9;
          goto _L6
_L13:
        l = 10;
          goto _L6
_L12:
        l = 12;
          goto _L6
_L14:
        l = 13;
          goto _L6
_L16:
        int i1;
        char c1;
        i1 = 0;
        int j1;
        int k1;
        if (l == 120)
        {
            j1 = 2;
        } else
        {
            j1 = 4;
            i1 = 0;
        }
_L19:
label0:
        {
            if (--j1 >= 0)
            {
                k1 = port.read();
                if (k1 < 0)
                {
                    eofError((new StringBuilder()).append("eof following '\\").append((char)l).append("' in string").toString());
                }
                c1 = Character.forDigit((char)k1, 16);
                if (c1 >= 0)
                {
                    break label0;
                }
                error((new StringBuilder()).append("invalid char following '\\").append((char)l).append("' in string").toString());
                i1 = 63;
            }
            l = i1;
        }
          goto _L6
        i1 = c1 + i1 * 16;
          goto _L19
_L17:
        int l1;
        int i2;
        l1 = 0;
        i2 = 3;
_L20:
        char c2;
label1:
        {
            if (--i2 >= 0)
            {
                int j2 = port.read();
                if (j2 < 0)
                {
                    eofError("eof in octal escape in string literal");
                }
                c2 = Character.forDigit((char)j2, 8);
                if (c2 >= 0)
                {
                    break label1;
                }
                port.unread_quick();
            }
            l = l1;
        }
          goto _L6
        l1 = c2 + l1 * 8;
          goto _L20
    }

    public Object getToken()
        throws IOException, SyntaxException
    {
        int i = read();
_L8:
        if (i >= 0) goto _L2; else goto _L1
_L1:
        Object obj = eofToken;
_L6:
        return obj;
_L2:
        if (Character.isWhitespace((char)i)) goto _L4; else goto _L3
_L3:
        switch (i)
        {
        default:
            if (Character.isJavaIdentifierStart((char)i))
            {
                String s = getIdentifier(i).intern();
                obj = checkReserved(s);
                if (obj == null)
                {
                    return s;
                }
            } else
            {
                return Char.make((char)i);
            }
            break;

        case 46: // '.'
            int j = port.peek();
            if (j >= 48 && j <= 57)
            {
                return new QuoteExp(getNumericLiteral(46));
            } else
            {
                return dotToken;
            }

        case 48: // '0'
        case 49: // '1'
        case 50: // '2'
        case 51: // '3'
        case 52: // '4'
        case 53: // '5'
        case 54: // '6'
        case 55: // '7'
        case 56: // '8'
        case 57: // '9'
            return new QuoteExp(getNumericLiteral(i));

        case 34: // '"'
        case 39: // '\''
            return new QuoteExp(getStringLiteral((char)i));

        case 40: // '('
            return lparenToken;

        case 41: // ')'
            return rparenToken;

        case 91: // '['
            return lbracketToken;

        case 93: // ']'
            return rbracketToken;

        case 123: // '{'
            return lbraceToken;

        case 125: // '}'
            return rbraceToken;

        case 63: // '?'
            return condToken;

        case 58: // ':'
            return colonToken;

        case 59: // ';'
            return semicolonToken;

        case 44: // ','
            return commaToken;

        case 61: // '='
            if (port.peek() == 61)
            {
                port.skip_quick();
                return Reserved.opEqual;
            } else
            {
                return equalToken;
            }

        case 33: // '!'
            if (port.peek() == 61)
            {
                port.skip_quick();
                return Reserved.opNotEqual;
            } else
            {
                return notToken;
            }

        case 126: // '~'
            return tildeToken;

        case 42: // '*'
            return maybeAssignment(Reserved.opTimes);

        case 47: // '/'
            return maybeAssignment(Reserved.opDivide);

        case 94: // '^'
            return maybeAssignment(Reserved.opBitXor);

        case 37: // '%'
            return maybeAssignment(Reserved.opRemainder);

        case 43: // '+'
            if (port.peek() == 43)
            {
                port.skip_quick();
                return maybeAssignment(Reserved.opPlusPlus);
            } else
            {
                return maybeAssignment(Reserved.opPlus);
            }

        case 45: // '-'
            if (port.peek() == 45)
            {
                port.skip_quick();
                return maybeAssignment(Reserved.opMinusMinus);
            } else
            {
                return maybeAssignment(Reserved.opMinus);
            }

        case 38: // '&'
            if (port.peek() == 38)
            {
                port.skip_quick();
                return maybeAssignment(Reserved.opBoolAnd);
            } else
            {
                return maybeAssignment(Reserved.opBitAnd);
            }

        case 124: // '|'
            if (port.peek() == 124)
            {
                port.skip_quick();
                return maybeAssignment(Reserved.opBoolOr);
            } else
            {
                return maybeAssignment(Reserved.opBitOr);
            }

        case 62: // '>'
            switch (port.peek())
            {
            default:
                return Reserved.opGreater;

            case 62: // '>'
                port.skip_quick();
                if (port.peek() == 62)
                {
                    port.skip_quick();
                    return maybeAssignment(Reserved.opRshiftUnsigned);
                } else
                {
                    return maybeAssignment(Reserved.opRshiftSigned);
                }

            case 61: // '='
                port.skip_quick();
                return Reserved.opGreaterEqual;
            }

        case 60: // '<'
            switch (port.peek())
            {
            default:
                return Reserved.opLess;

            case 60: // '<'
                port.skip_quick();
                return maybeAssignment(Reserved.opLshift);

            case 61: // '='
                port.skip_quick();
                break;
            }
            return Reserved.opLessEqual;
        }
        if (true) goto _L6; else goto _L5
_L5:
_L4:
        if (i == 13)
        {
            prevWasCR = true;
            return eolToken;
        }
        if (i == 10 && !prevWasCR)
        {
            return eolToken;
        }
        prevWasCR = false;
        i = read();
        if (true) goto _L8; else goto _L7
_L7:
    }

    public Object maybeAssignment(Object obj)
        throws IOException, SyntaxException
    {
        int i = read();
        if (i == 61)
        {
            error("assignment operation not implemented");
        }
        if (i >= 0)
        {
            port.unread_quick();
        }
        return obj;
    }

    static 
    {
        eofToken = Sequence.eofValue;
    }
}
