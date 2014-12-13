// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package gnu.bytecode:
//            MiscAttr, Label, Type, UninitializedType, 
//            PrimType, CodeAttr, ObjectType, ConstantPool, 
//            CpoolClass, ClassTypeWriter, Method

public class StackMapTableAttr extends MiscAttr
{

    public static boolean compressStackMapTable = true;
    int countLocals;
    int countStack;
    int encodedLocals[];
    int encodedStack[];
    int numEntries;
    int prevPosition;

    public StackMapTableAttr()
    {
        super("StackMapTable", null, 0, 0);
        prevPosition = -1;
        put2(0);
    }

    public StackMapTableAttr(byte abyte0[], CodeAttr codeattr)
    {
        super("StackMapTable", abyte0, 0, abyte0.length);
        prevPosition = -1;
        addToFrontOf(codeattr);
        numEntries = u2(0);
    }

    static int[] reallocBuffer(int ai[], int i)
    {
        if (ai == null)
        {
            ai = new int[i + 10];
        } else
        if (i > ai.length)
        {
            int ai1[] = new int[i + 10];
            System.arraycopy(ai, 0, ai1, 0, ai.length);
            return ai1;
        }
        return ai;
    }

    public void emitStackMapEntry(Label label, CodeAttr codeattr)
    {
        int i;
        int k;
        int l;
        int j1;
        i = -1 + (label.position - prevPosition);
        int j = label.localTypes.length;
        if (j > encodedLocals.length)
        {
            int ai3[] = new int[j + encodedLocals.length];
            System.arraycopy(encodedLocals, 0, ai3, 0, countLocals);
            encodedLocals = ai3;
        }
        k = label.stackTypes.length;
        if (k > encodedStack.length)
        {
            int ai2[] = new int[k + encodedStack.length];
            System.arraycopy(encodedStack, 0, ai2, 0, countStack);
            encodedStack = ai2;
        }
        l = 0;
        int i1 = 0;
        int j4;
        for (j1 = 0; i1 < j; j1 = j4)
        {
            int l3 = encodedLocals[j1];
            int i4 = encodeVerificationType(label.localTypes[i1], codeattr);
            if (l3 == i4 && l == j1)
            {
                l = j1 + 1;
            }
            int ai1[] = encodedLocals;
            j4 = j1 + 1;
            ai1[j1] = i4;
            if (i4 == 3 || i4 == 4)
            {
                i1++;
            }
            i1++;
        }

          goto _L1
_L8:
        int k1;
        int i2;
        int j2;
        for (; k1 > 0 && encodedLocals[k1 - 1] == 0; k1--) { }
        int l1 = 0;
        int k3;
        for (i2 = 0; l1 < k; i2 = k3)
        {
            encodedStack[i2];
            Type type = label.stackTypes[l1];
            PrimType primtype = Type.voidType;
            if (type == primtype)
            {
                Type atype[] = label.stackTypes;
                l1++;
                type = atype[l1];
            }
            int j3 = encodeVerificationType(type, codeattr);
            int ai[] = encodedStack;
            k3 = i2 + 1;
            ai[i2] = j3;
            l1++;
        }

        j2 = k1 - countLocals;
        if (!compressStackMapTable || j2 != 0 || k1 != l || i2 > 1) goto _L3; else goto _L2
_L2:
        if (i2 == 0)
        {
            if (i <= 63)
            {
                put1(i);
            } else
            {
                put1(251);
                put2(i);
            }
        } else
        {
            if (i <= 63)
            {
                put1(i + 64);
            } else
            {
                put1(247);
                put2(i);
            }
            emitVerificationType(encodedStack[0]);
        }
_L5:
        countLocals = k1;
        countStack = i2;
        prevPosition = label.position;
        numEntries = 1 + numEntries;
        return;
_L3:
        if (!compressStackMapTable || i2 != 0 || k1 >= countLocals || l != k1 || j2 < -3)
        {
            break; /* Loop/switch isn't completed */
        }
        put1(j2 + 251);
        put2(i);
        if (true) goto _L5; else goto _L4
_L4:
        if (!compressStackMapTable || i2 != 0 || countLocals != l || j2 > 3)
        {
            break; /* Loop/switch isn't completed */
        }
        put1(j2 + 251);
        put2(i);
        int i3 = 0;
        while (i3 < j2) 
        {
            emitVerificationType(encodedLocals[l + i3]);
            i3++;
        }
        if (true) goto _L5; else goto _L6
_L6:
        put1(255);
        put2(i);
        put2(k1);
        for (int k2 = 0; k2 < k1; k2++)
        {
            emitVerificationType(encodedLocals[k2]);
        }

        put2(i2);
        int l2 = 0;
        while (l2 < i2) 
        {
            emitVerificationType(encodedStack[l2]);
            l2++;
        }
        if (true) goto _L5; else goto _L1
_L1:
        k1 = j1;
        if (true) goto _L8; else goto _L7
_L7:
    }

    void emitVerificationType(int i)
    {
        int j = i & 0xff;
        put1(j);
        if (j >= 7)
        {
            put2(i >> 8);
        }
    }

    int encodeVerificationType(Type type, CodeAttr codeattr)
    {
        if (type == null)
        {
            return 0;
        }
        if (type instanceof UninitializedType)
        {
            Label label = ((UninitializedType)type).label;
            if (label == null)
            {
                return 6;
            } else
            {
                return 8 | label.position << 8;
            }
        }
        Type type1 = type.getImplementationType();
        if (type1 instanceof PrimType)
        {
            switch (type1.signature.charAt(0))
            {
            default:
                return 0;

            case 66: // 'B'
            case 67: // 'C'
            case 73: // 'I'
            case 83: // 'S'
            case 90: // 'Z'
                return 1;

            case 74: // 'J'
                return 4;

            case 70: // 'F'
                return 2;

            case 68: // 'D'
                return 3;
            }
        }
        if (type1 == Type.nullType)
        {
            return 5;
        } else
        {
            return 7 | codeattr.getConstants().addClass((ObjectType)type1).index << 8;
        }
    }

    int extractVerificationType(int i, int j)
    {
        if (j == 7 || j == 8)
        {
            j |= u2(i + 1) << 8;
        }
        return j;
    }

    int extractVerificationTypes(int i, int j, int k, int ai[])
    {
        int l = i;
        int i1 = k;
        while (--j >= 0) 
        {
            int j1;
            int k1;
            if (l >= dataLength)
            {
                j1 = -1;
            } else
            {
                byte byte0 = data[l];
                j1 = extractVerificationType(l, byte0);
                byte byte1;
                if (byte0 == 7 || byte0 == 8)
                {
                    byte1 = 3;
                } else
                {
                    byte1 = 1;
                }
                l += byte1;
            }
            k1 = i1 + 1;
            ai[i1] = j1;
            i1 = k1;
        }
        return l;
    }

    public Method getMethod()
    {
        return ((CodeAttr)container).getMethod();
    }

    public void print(ClassTypeWriter classtypewriter)
    {
        classtypewriter.print("Attribute \"");
        classtypewriter.print(getName());
        classtypewriter.print("\", length:");
        classtypewriter.print(getLength());
        classtypewriter.print(", number of entries: ");
        classtypewriter.println(numEntries);
        int i = 2;
        int j = -1;
        Method method = getMethod();
        int ai[] = null;
        int k;
        int l;
        int i1;
        if (method.getStaticFlag())
        {
            k = 0;
        } else
        {
            k = 1;
        }
        l = k + method.arg_types.length;
        i1 = 0;
        do
        {
            if (i1 >= numEntries || i >= dataLength)
            {
                return;
            }
            int j1 = i + 1;
            int k1 = u1(i);
            int l1 = j + 1;
            if (k1 <= 127)
            {
                j = l1 + (k1 & 0x3f);
                i = j1;
            } else
            {
                if (j1 + 1 >= dataLength)
                {
                    return;
                }
                j = l1 + u2(j1);
                i = j1 + 2;
            }
            classtypewriter.print("  offset: ");
            classtypewriter.print(j);
            if (k1 <= 63)
            {
                classtypewriter.println(" - same_frame");
            } else
            if (k1 <= 127 || k1 == 247)
            {
                String s;
                if (k1 <= 127)
                {
                    s = " - same_locals_1_stack_item_frame";
                } else
                {
                    s = " - same_locals_1_stack_item_frame_extended";
                }
                classtypewriter.println(s);
                ai = reallocBuffer(ai, 1);
                i = extractVerificationTypes(i, 1, 0, ai);
                printVerificationTypes(ai, 0, 1, classtypewriter);
            } else
            {
                if (k1 <= 246)
                {
                    classtypewriter.print(" - tag reserved for future use - ");
                    classtypewriter.println(k1);
                    return;
                }
                if (k1 <= 250)
                {
                    int l3 = 251 - k1;
                    classtypewriter.print(" - chop_frame - undefine ");
                    classtypewriter.print(l3);
                    classtypewriter.println(" locals");
                    l -= l3;
                } else
                if (k1 == 251)
                {
                    classtypewriter.println(" - same_frame_extended");
                } else
                if (k1 <= 254)
                {
                    int k3 = k1 - 251;
                    classtypewriter.print(" - append_frame - define ");
                    classtypewriter.print(k3);
                    classtypewriter.println(" more locals");
                    ai = reallocBuffer(ai, l + k3);
                    i = extractVerificationTypes(i, k3, l, ai);
                    printVerificationTypes(ai, l, k3, classtypewriter);
                    l += k3;
                } else
                {
                    if (i + 1 >= dataLength)
                    {
                        return;
                    }
                    int i2 = u2(i);
                    int j2 = i + 2;
                    classtypewriter.print(" - full_frame.  Locals count: ");
                    classtypewriter.println(i2);
                    int ai1[] = reallocBuffer(ai, i2);
                    int k2 = extractVerificationTypes(j2, i2, 0, ai1);
                    printVerificationTypes(ai1, 0, i2, classtypewriter);
                    l = i2;
                    if (k2 + 1 >= dataLength)
                    {
                        return;
                    }
                    int l2 = u2(k2);
                    int i3 = k2 + 2;
                    classtypewriter.print("    (end of locals)");
                    for (int j3 = Integer.toString(j).length(); --j3 >= 0;)
                    {
                        classtypewriter.print(' ');
                    }

                    classtypewriter.print("       Stack count: ");
                    classtypewriter.println(l2);
                    ai = reallocBuffer(ai1, l2);
                    i = extractVerificationTypes(i3, l2, 0, ai);
                    printVerificationTypes(ai, 0, l2, classtypewriter);
                }
            }
            if (i < 0)
            {
                classtypewriter.println("<ERROR - missing data>");
                return;
            }
            i1++;
        } while (true);
    }

    void printVerificationType(int i, ClassTypeWriter classtypewriter)
    {
        int j = i & 0xff;
        int k;
        switch (j)
        {
        default:
            classtypewriter.print((new StringBuilder()).append("<bad verification type tag ").append(j).append('>').toString());
            return;

        case 0: // '\0'
            classtypewriter.print("top/unavailable");
            return;

        case 1: // '\001'
            classtypewriter.print("integer");
            return;

        case 2: // '\002'
            classtypewriter.print("float");
            return;

        case 3: // '\003'
            classtypewriter.print("double");
            return;

        case 4: // '\004'
            classtypewriter.print("long");
            return;

        case 5: // '\005'
            classtypewriter.print("null");
            return;

        case 6: // '\006'
            classtypewriter.print("uninitialized this");
            return;

        case 7: // '\007'
            int l = i >> 8;
            classtypewriter.printOptionalIndex(l);
            classtypewriter.printConstantTersely(l, 7);
            return;

        case 8: // '\b'
            k = i >> 8;
            break;
        }
        classtypewriter.print("uninitialized object created at ");
        classtypewriter.print(k);
    }

    void printVerificationTypes(int ai[], int i, int j, ClassTypeWriter classtypewriter)
    {
        int k = 0;
        int l = 0;
        do
        {
            while (l < i + j) 
            {
                int i1 = ai[l];
                int j1 = i1 & 0xff;
                if (l >= i)
                {
                    classtypewriter.print("  ");
                    if (k < 100)
                    {
                        if (k < 10)
                        {
                            classtypewriter.print(' ');
                        }
                        classtypewriter.print(' ');
                    }
                    classtypewriter.print(k);
                    classtypewriter.print(": ");
                    printVerificationType(i1, classtypewriter);
                    classtypewriter.println();
                }
                k++;
                if (j1 == 3 || j1 == 4)
                {
                    k++;
                }
                l++;
            }
            return;
        } while (true);
    }

    public void write(DataOutputStream dataoutputstream)
        throws IOException
    {
        put2(0, numEntries);
        super.write(dataoutputstream);
    }

}
