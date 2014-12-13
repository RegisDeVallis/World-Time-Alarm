// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;

// Referenced classes of package gnu.bytecode:
//            Attribute, AttrContainer, Method, ClassType, 
//            Type, UninitializedType, ArrayType, PrimType, 
//            ObjectType, ConstantPool, TryState, Label, 
//            CpoolClass, LocalVarsAttr, Scope, Variable, 
//            StackMapTableAttr, LineNumbersAttr, ClassTypeWriter, IfState, 
//            ExitableBlock, SwitchState, Field, CpoolEntry, 
//            CpoolValue2, SourceDebugExtAttr

public class CodeAttr extends Attribute
    implements AttrContainer
{

    public static final int DONT_USE_JSR = 2;
    static final int FIXUP_CASE = 3;
    static final int FIXUP_DEFINE = 1;
    static final int FIXUP_DELETE3 = 8;
    static final int FIXUP_GOTO = 4;
    static final int FIXUP_JSR = 5;
    static final int FIXUP_LINE_NUMBER = 15;
    static final int FIXUP_LINE_PC = 14;
    static final int FIXUP_MOVE = 9;
    static final int FIXUP_MOVE_TO_END = 10;
    static final int FIXUP_NONE = 0;
    static final int FIXUP_SWITCH = 2;
    static final int FIXUP_TRANSFER = 6;
    static final int FIXUP_TRANSFER2 = 7;
    static final int FIXUP_TRY = 11;
    static final int FIXUP_TRY_END = 12;
    static final int FIXUP_TRY_HANDLER = 13;
    public static final int GENERATE_STACK_MAP_TABLE = 1;
    public static boolean instructionLineMode = false;
    int PC;
    int SP;
    Attribute attributes;
    byte code[];
    ExitableBlock currentExitableBlock;
    short exception_table[];
    int exception_table_length;
    int exitableBlockLevel;
    int fixup_count;
    Label fixup_labels[];
    int fixup_offsets[];
    int flags;
    IfState if_stack;
    LineNumbersAttr lines;
    Type local_types[];
    public LocalVarsAttr locals;
    private int max_locals;
    private int max_stack;
    Label previousLabel;
    SourceDebugExtAttr sourceDbgExt;
    public StackMapTableAttr stackMap;
    public Type stack_types[];
    TryState try_stack;
    private boolean unreachable_here;
    boolean varsSetInCurrentBlock[];

    public CodeAttr(Method method)
    {
        super("Code");
        addToFrontOf(method);
        method.code = this;
        if (method.getDeclaringClass().getClassfileMajorVersion() >= 50)
        {
            flags = 3 | flags;
        }
    }

    private int adjustTypedOp(char c)
    {
        switch (c)
        {
        default:
            return 4;

        case 73: // 'I'
            return 0;

        case 74: // 'J'
            return 1;

        case 70: // 'F'
            return 2;

        case 68: // 'D'
            return 3;

        case 66: // 'B'
        case 90: // 'Z'
            return 5;

        case 67: // 'C'
            return 6;

        case 83: // 'S'
            return 7;
        }
    }

    private int adjustTypedOp(Type type)
    {
        return adjustTypedOp(type.getSignature().charAt(0));
    }

    public static final String calculateSplit(String s)
    {
        int i = s.length();
        StringBuffer stringbuffer = new StringBuffer(20);
        int j = 0;
        int k = 0;
        int l = 0;
        while (l < i) 
        {
            char c = s.charAt(l);
            byte byte0;
            if (c >= '\u0800')
            {
                byte0 = 3;
            } else
            if (c >= '\200' || c == 0)
            {
                byte0 = 2;
            } else
            {
                byte0 = 1;
            }
            if (k + byte0 > 65535)
            {
                stringbuffer.append((char)(l - j));
                j = l;
                k = 0;
            }
            k += byte0;
            l++;
        }
        stringbuffer.append((char)(i - j));
        return stringbuffer.toString();
    }

    public static boolean castNeeded(Type type, Type type1)
    {
        if (type instanceof UninitializedType)
        {
            type = ((UninitializedType)type).getImplementationType();
        }
        do
        {
            if (type == type1)
            {
                return false;
            }
            if ((type1 instanceof ClassType) && (type instanceof ClassType) && ((ClassType)type).isSubclass((ClassType)type1))
            {
                return false;
            }
            if ((type1 instanceof ArrayType) && (type instanceof ArrayType))
            {
                type1 = ((ArrayType)type1).getComponentType();
                type = ((ArrayType)type).getComponentType();
            } else
            {
                return true;
            }
        } while (true);
    }

    private void emitBinop(int i)
    {
        Type type = popType().promote();
        Type type1 = popType();
        Type type2 = type1.promote();
        if (type2 != type || !(type2 instanceof PrimType))
        {
            throw new Error("non-matching or bad types in binary operation");
        } else
        {
            emitTypedOp(i, type2);
            pushType(type1);
            return;
        }
    }

    private void emitBinop(int i, char c)
    {
        popType();
        popType();
        emitTypedOp(i, c);
        pushType(Type.signatureToPrimitive(c));
    }

    private void emitCheckcast(Type type, int i)
    {
        reserve(3);
        popType();
        put1(i);
        if (type instanceof ObjectType)
        {
            putIndex2(getConstants().addClass((ObjectType)type));
            return;
        } else
        {
            throw new Error((new StringBuilder()).append("unimplemented type ").append(type).append(" in emitCheckcast/emitInstanceof").toString());
        }
    }

    private final void emitFieldop(Field field, int i)
    {
        reserve(3);
        put1(i);
        putIndex2(getConstants().addFieldRef(field));
    }

    private void emitShift(int i)
    {
        Type type = popType().promote();
        Type type1 = popType();
        Type type2 = type1.promote();
        if (type2 != Type.intType && type2 != Type.longType)
        {
            throw new Error("the value shifted must be an int or a long");
        }
        if (type != Type.intType)
        {
            throw new Error("the amount of shift must be an int");
        } else
        {
            emitTypedOp(i, type2);
            pushType(type1);
            return;
        }
    }

    private void emitTryEnd(boolean flag)
    {
        if (try_stack.tryClauseDone)
        {
            return;
        }
        try_stack.tryClauseDone = true;
        if (try_stack.finally_subr != null)
        {
            try_stack.exception = addLocal(Type.javalangThrowableType);
        }
        gotoFinallyOrEnd(flag);
        try_stack.end_try = getLabel();
    }

    private void emitTypedOp(int i, char c)
    {
        reserve(1);
        put1(i + adjustTypedOp(c));
    }

    private void emitTypedOp(int i, Type type)
    {
        reserve(1);
        put1(i + adjustTypedOp(type));
    }

    private final int fixupKind(int i)
    {
        return 0xf & fixup_offsets[i];
    }

    private final int fixupOffset(int i)
    {
        return fixup_offsets[i] >> 4;
    }

    private void gotoFinallyOrEnd(boolean flag)
    {
        if (reachableHere())
        {
            if (try_stack.saved_result != null)
            {
                emitStore(try_stack.saved_result);
            }
            if (try_stack.end_label == null)
            {
                try_stack.end_label = new Label();
            }
            if (try_stack.finally_subr == null || useJsr())
            {
                if (try_stack.finally_subr != null)
                {
                    emitJsr(try_stack.finally_subr);
                }
                emitGoto(try_stack.end_label);
            } else
            {
                if (try_stack.exitCases != null)
                {
                    emitPushInt(0);
                }
                emitPushNull();
                if (!flag)
                {
                    emitGoto(try_stack.finally_subr);
                    return;
                }
            }
        }
    }

    private Label mergeLabels(Label label, Label label1)
    {
        if (label != null)
        {
            label1.setTypes(label);
        }
        return label1;
    }

    private void print(String s, int i, PrintWriter printwriter)
    {
        int j = 0;
        int k = -1;
        for (; i >= 0; i--)
        {
            j = k + 1;
            k = s.indexOf(';', j);
        }

        printwriter.write(s, j, k - j);
    }

    private int readInt(int i)
    {
        return readUnsignedShort(i) << 16 | readUnsignedShort(i + 2);
    }

    private int readUnsignedShort(int i)
    {
        return (0xff & code[i]) << 8 | 0xff & code[i + 1];
    }

    private int words(Type atype[])
    {
        int i = 0;
        for (int j = atype.length; --j >= 0;)
        {
            if (atype[j].size > 4)
            {
                i += 2;
            } else
            {
                i++;
            }
        }

        return i;
    }

    public void addHandler(int i, int j, int k, int l)
    {
        int i1 = 4 * exception_table_length;
        if (exception_table != null) goto _L2; else goto _L1
_L1:
        exception_table = new short[20];
_L4:
        short aword1[] = exception_table;
        int j1 = i1 + 1;
        aword1[i1] = (short)i;
        short aword2[] = exception_table;
        int k1 = j1 + 1;
        aword2[j1] = (short)j;
        short aword3[] = exception_table;
        int l1 = k1 + 1;
        aword3[k1] = (short)k;
        short aword4[] = exception_table;
        l1 + 1;
        aword4[l1] = (short)l;
        exception_table_length = 1 + exception_table_length;
        return;
_L2:
        if (exception_table.length <= i1)
        {
            short aword0[] = new short[2 * exception_table.length];
            System.arraycopy(exception_table, 0, aword0, 0, i1);
            exception_table = aword0;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void addHandler(Label label, Label label1, ClassType classtype)
    {
        ConstantPool constantpool = getConstants();
        int i;
        Label label2;
        ClassType classtype1;
        if (classtype == null)
        {
            i = 0;
        } else
        {
            i = constantpool.addClass(classtype).index;
        }
        fixupAdd(11, label);
        fixupAdd(12, i, label1);
        label2 = new Label();
        label2.localTypes = label.localTypes;
        label2.stackTypes = new Type[1];
        if (classtype == null)
        {
            classtype1 = Type.javalangThrowableType;
        } else
        {
            classtype1 = classtype;
        }
        label2.stackTypes[0] = classtype1;
        setTypes(label2);
        fixupAdd(13, 0, label2);
    }

    public Variable addLocal(Type type)
    {
        return locals.current_scope.addVariable(this, type, null);
    }

    public Variable addLocal(Type type, String s)
    {
        return locals.current_scope.addVariable(this, type, s);
    }

    public void addParamLocals()
    {
        Method method = getMethod();
        if ((8 & method.access_flags) == 0)
        {
            addLocal(method.classfile).setParameter(true);
        }
        int i = method.arg_types.length;
        for (int j = 0; j < i; j++)
        {
            addLocal(method.arg_types[j]).setParameter(true);
        }

    }

    public void assignConstants(ClassType classtype)
    {
        if (locals != null && locals.container == null && !locals.isEmpty())
        {
            locals.addToFrontOf(this);
        }
        processFixups();
        if (stackMap != null && stackMap.numEntries > 0)
        {
            stackMap.addToFrontOf(this);
        }
        if (instructionLineMode)
        {
            if (lines == null)
            {
                lines = new LineNumbersAttr(this);
            }
            lines.linenumber_count = 0;
            int i = getCodeLength();
            for (int j = 0; j < i; j++)
            {
                lines.put(j, j);
            }

        }
        super.assignConstants(classtype);
        Attribute.assignConstants(this, classtype);
    }

    public int beginFragment(Label label)
    {
        return beginFragment(new Label(), label);
    }

    public int beginFragment(Label label, Label label1)
    {
        int i = fixup_count;
        fixupAdd(10, label1);
        label.define(this);
        return i;
    }

    public void disAssemble(ClassTypeWriter classtypewriter, int i, int j)
    {
        boolean flag;
        int k;
        flag = false;
        k = i;
_L3:
        int l;
        int i1;
        int j1;
        int k1;
        if (k >= j)
        {
            break MISSING_BLOCK_LABEL_2102;
        }
        l = k + 1;
        i1 = k;
        j1 = 0xff & code[i1];
        String s = Integer.toString(i1);
        k1 = 0;
        for (int l1 = s.length(); ++l1 <= 3;)
        {
            classtypewriter.print(' ');
        }

        classtypewriter.print(s);
        classtypewriter.print(": ");
        if (j1 >= 120) goto _L2; else goto _L1
_L1:
        int i2;
        int j2;
        if (j1 < 87)
        {
            if (j1 < 3)
            {
                print("nop;aconst_null;iconst_m1;", j1, classtypewriter);
                i2 = l;
            } else
            if (j1 < 9)
            {
                classtypewriter.print("iconst_");
                classtypewriter.print(j1 - 3);
                i2 = l;
                k1 = 0;
            } else
            if (j1 < 16)
            {
                char c1;
                int i11;
                if (j1 < 11)
                {
                    c1 = 'l';
                    i11 = j1 - 9;
                } else
                if (j1 < 14)
                {
                    c1 = 'f';
                    i11 = j1 - 11;
                } else
                {
                    c1 = 'd';
                    i11 = j1 - 14;
                }
                classtypewriter.print(c1);
                classtypewriter.print("const_");
                classtypewriter.print(i11);
                i2 = l;
                k1 = 0;
            } else
            if (j1 < 21)
            {
                if (j1 < 18)
                {
                    print("bipush ;sipush ;", j1 - 16, classtypewriter);
                    short word3;
                    int k10;
                    if (j1 == 16)
                    {
                        byte abyte5[] = code;
                        int l10 = l + 1;
                        word3 = abyte5[l];
                        k10 = l10;
                    } else
                    {
                        word3 = (short)readUnsignedShort(l);
                        k10 = l + 2;
                    }
                    classtypewriter.print(word3);
                    i2 = k10;
                    k1 = 0;
                } else
                {
                    if (j1 == 18)
                    {
                        k1 = 1;
                    } else
                    {
                        k1 = 2;
                    }
                    print("ldc;ldc_w;ldc2_w;", j1 - 18, classtypewriter);
                    i2 = l;
                }
            } else
            {
                String s1;
                int k9;
                int l9;
                if (j1 < 54)
                {
                    s1 = "load";
                } else
                {
                    s1 = "store";
                    j1 -= 33;
                }
                if (j1 < 26)
                {
                    k9 = -1;
                    l9 = j1 - 21;
                } else
                if (j1 < 46)
                {
                    int j10 = j1 - 26;
                    k9 = j10 % 4;
                    l9 = j10 >> 2;
                } else
                {
                    k9 = -2;
                    l9 = j1 - 46;
                }
                classtypewriter.print("ilfdabcs".charAt(l9));
                if (k9 == -2)
                {
                    classtypewriter.write(97);
                }
                classtypewriter.print(s1);
                if (k9 >= 0)
                {
                    classtypewriter.write(95);
                    classtypewriter.print(k9);
                } else
                if (k9 == -1)
                {
                    int i10;
                    if (flag)
                    {
                        i10 = readUnsignedShort(l);
                        l += 2;
                    } else
                    {
                        i10 = 0xff & code[l];
                        l++;
                    }
                    classtypewriter.print(' ');
                    classtypewriter.print(i10);
                    flag = false;
                }
                i2 = l;
                k1 = 0;
            }
        } else
        if (j1 < 96)
        {
            print("pop;pop2;dup;dup_x1;dup_x2;dup2;dup2_x1;dup2_x2;swap;", j1 - 87, classtypewriter);
            i2 = l;
            k1 = 0;
        } else
        {
            classtypewriter.print("ilfda".charAt((j1 - 96) % 4));
            print("add;sub;mul;div;rem;neg;", j1 - 96 >> 2, classtypewriter);
            i2 = l;
            k1 = 0;
        }
_L4:
        if (k1 > 0)
        {
            int k2;
            int l2;
            int i3;
            short word0;
            int j3;
            int k3;
            int l3;
            byte abyte1[];
            int i4;
            byte abyte2[];
            byte byte0;
            int j4;
            int k4;
            int l4;
            int i5;
            int j5;
            int k5;
            int l5;
            int i6;
            int j6;
            int k6;
            int l6;
            int i7;
            int j7;
            int k7;
            int l7;
            int i8;
            short word1;
            int j8;
            int k8;
            int l8;
            short word2;
            int i9;
            byte abyte3[];
            int j9;
            byte abyte4[];
            char c;
            if (k1 == 1)
            {
                byte abyte0[] = code;
                j2 = i2 + 1;
                k2 = 0xff & abyte0[i2];
            } else
            {
                k2 = readUnsignedShort(i2);
                j2 = i2 + 2;
            }
            classtypewriter.printConstantOperand(k2);
        } else
        {
            j2 = i2;
        }
        classtypewriter.println();
        k = j2;
        if (true) goto _L3; else goto _L2
_L2:
        if (j1 < 170)
        {
            if (j1 < 132)
            {
                if ((j1 & 1) == 0)
                {
                    c = 'i';
                } else
                {
                    c = 'l';
                }
                classtypewriter.print(c);
                print("shl;shr;ushr;and;or;xor;", j1 - 120 >> 1, classtypewriter);
                i2 = l;
                k1 = 0;
            } else
            if (j1 == 132)
            {
                classtypewriter.print("iinc");
                if (!flag)
                {
                    abyte3 = code;
                    j9 = l + 1;
                    k8 = 0xff & abyte3[l];
                    abyte4 = code;
                    i9 = j9 + 1;
                    word2 = abyte4[j9];
                } else
                {
                    k8 = readUnsignedShort(l);
                    l8 = l + 2;
                    word2 = (short)readUnsignedShort(l8);
                    i9 = l8 + 2;
                    flag = false;
                }
                classtypewriter.print(' ');
                classtypewriter.print(k8);
                classtypewriter.print(' ');
                classtypewriter.print(word2);
                i2 = i9;
                k1 = 0;
            } else
            if (j1 < 148)
            {
                classtypewriter.print("ilfdi".charAt((-133 + j1) / 3));
                classtypewriter.print('2');
                classtypewriter.print("lfdifdildilfbcs".charAt(-133 + j1));
                i2 = l;
                k1 = 0;
            } else
            if (j1 < 153)
            {
                print("lcmp;fcmpl;fcmpg;dcmpl;dcmpg;", -148 + j1, classtypewriter);
                i2 = l;
                k1 = 0;
            } else
            if (j1 < 169)
            {
                if (j1 < 159)
                {
                    classtypewriter.print("if");
                    print("eq;ne;lt;ge;gt;le;", -153 + j1, classtypewriter);
                } else
                if (j1 < 167)
                {
                    if (j1 < 165)
                    {
                        classtypewriter.print("if_icmp");
                    } else
                    {
                        classtypewriter.print("if_acmp");
                        j1 -= 6;
                    }
                    print("eq;ne;lt;ge;gt;le;", -159 + j1, classtypewriter);
                } else
                {
                    print("goto;jsr;", -167 + j1, classtypewriter);
                }
                word1 = (short)readUnsignedShort(l);
                j8 = l + 2;
                classtypewriter.print(' ');
                classtypewriter.print(i1 + word1);
                i2 = j8;
                k1 = 0;
            } else
            {
                classtypewriter.print("ret ");
                if (flag)
                {
                    i8 = 2 + readUnsignedShort(l);
                } else
                {
                    i8 = 0xff & code[l];
                    l++;
                }
                classtypewriter.print(i8);
                i2 = l;
                k1 = 0;
                flag = false;
            }
        } else
        if (j1 < 172)
        {
            if (fixup_count <= 0)
            {
                l = -4 & l + 3;
            }
            j5 = readInt(l);
            k5 = l + 4;
            if (j1 == 170)
            {
                classtypewriter.print("tableswitch");
                i7 = readInt(k5);
                j7 = k5 + 4;
                k7 = readInt(j7);
                i6 = j7 + 4;
                classtypewriter.print(" low: ");
                classtypewriter.print(i7);
                classtypewriter.print(" high: ");
                classtypewriter.print(k7);
                classtypewriter.print(" default: ");
                classtypewriter.print(i1 + j5);
                for (; i7 <= k7; i7++)
                {
                    l7 = readInt(i6);
                    i6 += 4;
                    classtypewriter.println();
                    classtypewriter.print("  ");
                    classtypewriter.print(i7);
                    classtypewriter.print(": ");
                    classtypewriter.print(i1 + l7);
                }

            } else
            {
                classtypewriter.print("lookupswitch");
                l5 = readInt(k5);
                i6 = k5 + 4;
                classtypewriter.print(" npairs: ");
                classtypewriter.print(l5);
                classtypewriter.print(" default: ");
                classtypewriter.print(i1 + j5);
                while (--l5 >= 0) 
                {
                    j6 = readInt(i6);
                    k6 = i6 + 4;
                    l6 = readInt(k6);
                    i6 = k6 + 4;
                    classtypewriter.println();
                    classtypewriter.print("  ");
                    classtypewriter.print(j6);
                    classtypewriter.print(": ");
                    classtypewriter.print(i1 + l6);
                }
            }
            i2 = i6;
            k1 = 0;
        } else
        if (j1 < 178)
        {
            if (j1 < 177)
            {
                classtypewriter.print("ilfda".charAt(-172 + j1));
            }
            classtypewriter.print("return");
            i2 = l;
            k1 = 0;
        } else
        if (j1 < 182)
        {
            print("getstatic;putstatic;getfield;putfield;", -178 + j1, classtypewriter);
            k1 = 2;
            i2 = l;
        } else
        if (j1 < 185)
        {
            classtypewriter.print("invoke");
            print("virtual;special;static;", -182 + j1, classtypewriter);
            k1 = 2;
            i2 = l;
        } else
        if (j1 == 185)
        {
            classtypewriter.print("invokeinterface (");
            j4 = readUnsignedShort(l);
            k4 = l + 2;
            l4 = 0xff & code[k4];
            i5 = k4 + 2;
            classtypewriter.print((new StringBuilder()).append(l4).append(" args)").toString());
            classtypewriter.printConstantOperand(j4);
            i2 = i5;
            k1 = 0;
        } else
        if (j1 < 196)
        {
            print("186;new;newarray;anewarray;arraylength;athrow;checkcast;instanceof;monitorenter;monitorexit;", -186 + j1, classtypewriter);
            if (j1 == 187 || j1 == 189 || j1 == 192 || j1 == 193)
            {
                k1 = 2;
                i2 = l;
            } else
            {
                if (j1 != 188)
                {
                    break MISSING_BLOCK_LABEL_2075;
                }
                abyte2 = code;
                i2 = l + 1;
                byte0 = abyte2[l];
                classtypewriter.print(' ');
                if (byte0 >= 4 && byte0 <= 11)
                {
                    print("boolean;char;float;double;byte;short;int;long;", byte0 - 4, classtypewriter);
                    k1 = 0;
                } else
                {
                    classtypewriter.print(byte0);
                    k1 = 0;
                }
            }
        } else
        if (j1 == 196)
        {
            classtypewriter.print("wide");
            flag = true;
            i2 = l;
            k1 = 0;
        } else
        if (j1 == 197)
        {
            classtypewriter.print("multianewarray");
            k3 = readUnsignedShort(l);
            l3 = l + 2;
            classtypewriter.printConstantOperand(k3);
            abyte1 = code;
            i2 = l3 + 1;
            i4 = 0xff & abyte1[l3];
            classtypewriter.print(' ');
            classtypewriter.print(i4);
            k1 = 0;
        } else
        if (j1 < 200)
        {
            print("ifnull;ifnonnull;", -198 + j1, classtypewriter);
            word0 = (short)readUnsignedShort(l);
            j3 = l + 2;
            classtypewriter.print(' ');
            classtypewriter.print(i1 + word0);
            i2 = j3;
            k1 = 0;
        } else
        {
label0:
            {
                if (j1 >= 202)
                {
                    break label0;
                }
                print("goto_w;jsr_w;", -200 + j1, classtypewriter);
                l2 = readInt(l);
                i3 = l + 4;
                classtypewriter.print(' ');
                classtypewriter.print(i1 + l2);
                i2 = i3;
                k1 = 0;
            }
        }
          goto _L4
        classtypewriter.print(j1);
        i2 = l;
        k1 = 0;
          goto _L4
    }

    public final void emitAdd()
    {
        emitBinop(96);
    }

    public final void emitAdd(char c)
    {
        emitBinop(96, c);
    }

    public final void emitAdd(PrimType primtype)
    {
        emitBinop(96, primtype);
    }

    public final void emitAnd()
    {
        emitBinop(126);
    }

    public final void emitArrayLength()
    {
        if (!(popType() instanceof ArrayType))
        {
            throw new Error("non-array type in emitArrayLength");
        } else
        {
            reserve(1);
            put1(190);
            pushType(Type.intType);
            return;
        }
    }

    public void emitArrayLoad()
    {
        popType();
        Type type = ((ArrayType)popType().getImplementationType()).getComponentType();
        emitTypedOp(46, type);
        pushType(type);
    }

    public void emitArrayLoad(Type type)
    {
        popType();
        popType();
        emitTypedOp(46, type);
        pushType(type);
    }

    public void emitArrayStore()
    {
        popType();
        popType();
        emitTypedOp(79, ((ArrayType)popType().getImplementationType()).getComponentType());
    }

    public void emitArrayStore(Type type)
    {
        popType();
        popType();
        popType();
        emitTypedOp(79, type);
    }

    public void emitBinop(int i, Type type)
    {
        popType();
        popType();
        emitTypedOp(i, type);
        pushType(type);
    }

    public void emitCatchEnd()
    {
        gotoFinallyOrEnd(false);
        try_stack.try_type = null;
    }

    public void emitCatchStart(Variable variable)
    {
        emitTryEnd(false);
        setTypes(try_stack.start_try.localTypes, Type.typeArray0);
        if (try_stack.try_type != null)
        {
            emitCatchEnd();
        }
        ClassType classtype;
        if (variable == null)
        {
            classtype = null;
        } else
        {
            classtype = (ClassType)variable.getType();
        }
        try_stack.try_type = classtype;
        addHandler(try_stack.start_try, try_stack.end_try, classtype);
        if (variable != null)
        {
            emitStore(variable);
        }
    }

    public void emitCheckcast(Type type)
    {
        if (castNeeded(topType(), type))
        {
            emitCheckcast(type, 192);
            pushType(type);
        }
    }

    public final void emitConvert(Type type, Type type1)
    {
        String s;
        String s1;
        char c;
        s = type1.getSignature();
        s1 = type.getSignature();
        c = '\uFFFF';
        if (s.length() != 1 && s1.length() != 1) goto _L2; else goto _L1
_L1:
        char c1;
        char c2;
        c1 = s.charAt(0);
        c2 = s1.charAt(0);
        if (c2 != c1) goto _L4; else goto _L3
_L3:
        return;
_L4:
        if (type.size < 4)
        {
            c2 = 'I';
        }
        if (type1.size < 4)
        {
            emitConvert(type, ((Type) (Type.intType)));
            c2 = 'I';
        }
        if (c2 == c1) goto _L3; else goto _L5
_L5:
        c2;
        JVM INSTR tableswitch 68 74: default 136
    //                   68 395
    //                   69 136
    //                   70 331
    //                   71 136
    //                   72 136
    //                   73 152
    //                   74 263;
           goto _L6 _L7 _L6 _L8 _L6 _L6 _L9 _L10
_L2:
        if (c < 0)
        {
            throw new Error("unsupported CodeAttr.emitConvert");
        } else
        {
            reserve(1);
            popType();
            put1(c);
            pushType(type1);
            return;
        }
_L9:
        switch (c1)
        {
        case 66: // 'B'
            c = '\221';
            break;

        case 67: // 'C'
            c = '\222';
            break;

        case 83: // 'S'
            c = '\223';
            break;

        case 74: // 'J'
            c = '\205';
            break;

        case 70: // 'F'
            c = '\206';
            break;

        case 68: // 'D'
            c = '\207';
            break;
        }
_L6:
        if (true) goto _L2; else goto _L11
_L11:
_L10:
        switch (c1)
        {
        case 68: // 'D'
            c = '\212';
            break;

        case 73: // 'I'
            c = '\210';
            break;

        case 70: // 'F'
            c = '\211';
            break;
        }
        if (true) goto _L2; else goto _L12
_L12:
_L8:
        switch (c1)
        {
        case 68: // 'D'
            c = '\215';
            break;

        case 73: // 'I'
            c = '\213';
            break;

        case 74: // 'J'
            c = '\214';
            break;
        }
        if (true) goto _L2; else goto _L13
_L13:
_L7:
        switch (c1)
        {
        case 70: // 'F'
            c = '\220';
            break;

        case 73: // 'I'
            c = '\216';
            break;

        case 74: // 'J'
            c = '\217';
            break;
        }
        if (true) goto _L2; else goto _L14
_L14:
    }

    public final void emitDiv()
    {
        emitBinop(108);
    }

    public void emitDup()
    {
        reserve(1);
        Type type = topType();
        byte byte0;
        if (type.size <= 4)
        {
            byte0 = 89;
        } else
        {
            byte0 = 92;
        }
        put1(byte0);
        pushType(type);
    }

    public void emitDup(int i)
    {
        emitDup(i, 0);
    }

    public void emitDup(int i, int j)
    {
        Type type;
        Type type1;
        Type type2;
        Type type3;
        if (i == 0)
        {
            return;
        }
        reserve(1);
        type = popType();
        if (i == 1)
        {
            int j1 = type.size;
            type1 = null;
            if (j1 > 4)
            {
                throw new Error("using dup for 2-word type");
            }
        } else
        {
            if (i != 2)
            {
                throw new Error("invalid size to emitDup");
            }
            int k = type.size;
            type1 = null;
            if (k <= 4)
            {
                type1 = popType();
                if (type1.size > 4)
                {
                    throw new Error("dup will cause invalid types on stack");
                }
            }
        }
        type2 = null;
        type3 = null;
        if (j != 0) goto _L2; else goto _L1
_L1:
        byte byte0;
        if (i == 1)
        {
            byte0 = 89;
        } else
        {
            byte0 = 92;
            type2 = null;
            type3 = null;
        }
_L4:
        put1(byte0);
        if (type1 != null)
        {
            pushType(type1);
        }
        pushType(type);
        if (type3 != null)
        {
            pushType(type3);
        }
        if (type2 != null)
        {
            pushType(type2);
        }
        if (type1 != null)
        {
            pushType(type1);
        }
        pushType(type);
        return;
_L2:
        if (j != 1)
        {
            break; /* Loop/switch isn't completed */
        }
        int i1;
        if (i == 1)
        {
            byte0 = 90;
        } else
        {
            byte0 = 93;
        }
        type2 = popType();
        i1 = type2.size;
        type3 = null;
        if (i1 > 4)
        {
            throw new Error("dup will cause invalid types on stack");
        }
        if (true) goto _L4; else goto _L3
_L3:
        if (j == 2)
        {
            int l;
            if (i == 1)
            {
                byte0 = 91;
            } else
            {
                byte0 = 94;
            }
            type2 = popType();
            l = type2.size;
            type3 = null;
            if (l <= 4)
            {
                type3 = popType();
                if (type3.size > 4)
                {
                    throw new Error("dup will cause invalid types on stack");
                }
            }
        } else
        {
            throw new Error("emitDup:  invalid offset");
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public void emitDup(Type type)
    {
        byte byte0;
        if (type.size > 4)
        {
            byte0 = 2;
        } else
        {
            byte0 = 1;
        }
        emitDup(byte0, 0);
    }

    public void emitDupX()
    {
        reserve(1);
        Type type = popType();
        Type type1 = popType();
        if (type1.size <= 4)
        {
            byte byte1;
            if (type.size <= 4)
            {
                byte1 = 90;
            } else
            {
                byte1 = 93;
            }
            put1(byte1);
        } else
        {
            byte byte0;
            if (type.size <= 4)
            {
                byte0 = 91;
            } else
            {
                byte0 = 94;
            }
            put1(byte0);
        }
        pushType(type);
        pushType(type1);
        pushType(type);
    }

    public final void emitElse()
    {
        Label label = if_stack.end_label;
        if (reachableHere())
        {
            Label label1 = new Label(this);
            if_stack.end_label = label1;
            int i = SP - if_stack.start_stack_size;
            if_stack.stack_growth = i;
            if (i > 0)
            {
                if_stack.then_stacked_types = new Type[i];
                System.arraycopy(stack_types, if_stack.start_stack_size, if_stack.then_stacked_types, 0, i);
            } else
            {
                if_stack.then_stacked_types = new Type[0];
            }
            emitGoto(label1);
        } else
        {
            if_stack.end_label = null;
        }
        while (SP > if_stack.start_stack_size) 
        {
            popType();
        }
        SP = if_stack.start_stack_size;
        if (label != null)
        {
            label.define(this);
        }
        if_stack.doing_else = true;
    }

    public final void emitFi()
    {
        boolean flag = false;
        if (if_stack.doing_else) goto _L2; else goto _L1
_L1:
        boolean flag2 = reachableHere();
        flag = false;
        if (flag2)
        {
            int k = SP;
            int l = if_stack.start_stack_size;
            flag = false;
            if (k != l)
            {
                throw new Error((new StringBuilder()).append("at PC ").append(PC).append(" then clause grows stack with no else clause").toString());
            }
        }
          goto _L3
_L2:
        if (if_stack.then_stacked_types == null) goto _L5; else goto _L4
_L4:
        int i = if_stack.start_stack_size + if_stack.stack_growth;
        if (reachableHere()) goto _L7; else goto _L6
_L6:
        if (if_stack.stack_growth > 0)
        {
            System.arraycopy(if_stack.then_stacked_types, 0, stack_types, if_stack.start_stack_size, if_stack.stack_growth);
        }
        SP = i;
_L3:
        if (if_stack.end_label != null)
        {
            if_stack.end_label.define(this);
        }
        if (flag)
        {
            setUnreachable();
        }
        if_stack = if_stack.previous;
        return;
_L7:
        int j = SP;
        flag = false;
        if (j != i)
        {
            throw new Error((new StringBuilder()).append("at PC ").append(PC).append(": SP at end of 'then' was ").append(i).append(" while SP at end of 'else' was ").append(SP).toString());
        }
        continue; /* Loop/switch isn't completed */
_L5:
        boolean flag1 = unreachable_here;
        flag = false;
        if (flag1)
        {
            flag = true;
        }
        if (true) goto _L3; else goto _L8
_L8:
    }

    public void emitFinallyEnd()
    {
        if (useJsr())
        {
            emitRet(try_stack.finally_ret_addr);
        } else
        if (try_stack.end_label == null && try_stack.exitCases == null)
        {
            emitThrow();
        } else
        {
            emitStore(try_stack.exception);
            emitLoad(try_stack.exception);
            emitIfNotNull();
            emitLoad(try_stack.exception);
            emitThrow();
            emitElse();
            ExitableBlock exitableblock = try_stack.exitCases;
            if (exitableblock != null)
            {
                SwitchState switchstate = startSwitch();
                while (exitableblock != null) 
                {
                    ExitableBlock exitableblock1 = exitableblock.nextCase;
                    exitableblock.nextCase = null;
                    exitableblock.currentTryState = null;
                    TryState trystate = TryState.outerHandler(try_stack.previous, exitableblock.initialTryState);
                    if (trystate == exitableblock.initialTryState)
                    {
                        switchstate.addCaseGoto(exitableblock.switchCase, this, exitableblock.endLabel);
                    } else
                    {
                        switchstate.addCase(exitableblock.switchCase, this);
                        exitableblock.exit(trystate);
                    }
                    exitableblock = exitableblock1;
                }
                try_stack.exitCases = null;
                switchstate.addDefault(this);
                switchstate.finish(this);
            }
            emitFi();
            setUnreachable();
        }
        popScope();
        try_stack.finally_subr = null;
    }

    public void emitFinallyStart()
    {
        emitTryEnd(true);
        if (try_stack.try_type != null)
        {
            emitCatchEnd();
        }
        try_stack.end_try = getLabel();
        pushScope();
        if (useJsr())
        {
            SP = 0;
            emitCatchStart(null);
            emitStore(try_stack.exception);
            emitJsr(try_stack.finally_subr);
            emitLoad(try_stack.exception);
            emitThrow();
        } else
        {
            setUnreachable();
            int i = beginFragment(new Label(this));
            addHandler(try_stack.start_try, try_stack.end_try, Type.javalangThrowableType);
            if (try_stack.saved_result != null)
            {
                emitStoreDefaultValue(try_stack.saved_result);
            }
            if (try_stack.exitCases != null)
            {
                emitPushInt(-1);
                emitSwap();
            }
            emitGoto(try_stack.finally_subr);
            endFragment(i);
        }
        try_stack.finally_subr.define(this);
        if (useJsr())
        {
            ClassType classtype = Type.objectType;
            try_stack.finally_ret_addr = addLocal(classtype);
            pushType(classtype);
            emitStore(try_stack.finally_ret_addr);
        }
    }

    public final void emitGetField(Field field)
    {
        popType();
        pushType(field.type);
        emitFieldop(field, 180);
    }

    public final void emitGetStatic(Field field)
    {
        pushType(field.type);
        emitFieldop(field, 178);
    }

    public final void emitGoto(Label label)
    {
        label.setTypes(this);
        fixupAdd(4, label);
        reserve(3);
        put1(167);
        PC = 2 + PC;
        setUnreachable();
    }

    public final void emitGotoIfCompare1(Label label, int i)
    {
        popType();
        reserve(3);
        emitTransfer(label, i);
    }

    public final void emitGotoIfCompare2(Label label, int i)
    {
        char c;
        char c1;
        boolean flag;
label0:
        {
            if (i < 153 || i > 158)
            {
                throw new Error("emitGotoIfCompare2: logop must be one of ifeq...ifle");
            }
            Type type = popType().promote();
            Type type1 = popType().promote();
            reserve(4);
            c = type1.getSignature().charAt(0);
            c1 = type.getSignature().charAt(0);
            if (i != 155)
            {
                flag = false;
                if (i != 158)
                {
                    break label0;
                }
            }
            flag = true;
        }
        if (c == 'I' && c1 == 'I')
        {
            i += 6;
        } else
        if (c == 'J' && c1 == 'J')
        {
            put1(148);
        } else
        if (c == 'F' && c1 == 'F')
        {
            char c3;
            if (flag)
            {
                c3 = '\225';
            } else
            {
                c3 = '\226';
            }
            put1(c3);
        } else
        if (c == 'D' && c1 == 'D')
        {
            char c2;
            if (flag)
            {
                c2 = '\227';
            } else
            {
                c2 = '\230';
            }
            put1(c2);
        } else
        if ((c == 'L' || c == '[') && (c1 == 'L' || c1 == '[') && i <= 154)
        {
            i += 12;
        } else
        {
            throw new Error("invalid types to emitGotoIfCompare2");
        }
        emitTransfer(label, i);
    }

    public final void emitGotoIfEq(Label label)
    {
        emitGotoIfCompare2(label, 153);
    }

    public final void emitGotoIfEq(Label label, boolean flag)
    {
        char c;
        if (flag)
        {
            c = '\232';
        } else
        {
            c = '\231';
        }
        emitGotoIfCompare2(label, c);
    }

    public final void emitGotoIfGe(Label label)
    {
        emitGotoIfCompare2(label, 156);
    }

    public final void emitGotoIfGt(Label label)
    {
        emitGotoIfCompare2(label, 157);
    }

    public final void emitGotoIfIntEqZero(Label label)
    {
        emitGotoIfCompare1(label, 153);
    }

    public final void emitGotoIfIntGeZero(Label label)
    {
        emitGotoIfCompare1(label, 156);
    }

    public final void emitGotoIfIntGtZero(Label label)
    {
        emitGotoIfCompare1(label, 157);
    }

    public final void emitGotoIfIntLeZero(Label label)
    {
        emitGotoIfCompare1(label, 158);
    }

    public final void emitGotoIfIntLtZero(Label label)
    {
        emitGotoIfCompare1(label, 155);
    }

    public final void emitGotoIfIntNeZero(Label label)
    {
        emitGotoIfCompare1(label, 154);
    }

    public final void emitGotoIfLe(Label label)
    {
        emitGotoIfCompare2(label, 158);
    }

    public final void emitGotoIfLt(Label label)
    {
        emitGotoIfCompare2(label, 155);
    }

    public final void emitGotoIfNE(Label label)
    {
        emitGotoIfCompare2(label, 154);
    }

    public final void emitIOr()
    {
        emitBinop(128);
    }

    public final void emitIfCompare1(int i)
    {
        IfState ifstate = new IfState(this);
        if (popType().promote() != Type.intType)
        {
            throw new Error("non-int type to emitIfCompare1");
        } else
        {
            reserve(3);
            emitTransfer(ifstate.end_label, i);
            ifstate.start_stack_size = SP;
            return;
        }
    }

    public final void emitIfEq()
    {
        IfState ifstate = new IfState(this);
        emitGotoIfNE(ifstate.end_label);
        ifstate.start_stack_size = SP;
    }

    public final void emitIfGe()
    {
        IfState ifstate = new IfState(this);
        emitGotoIfLt(ifstate.end_label);
        ifstate.start_stack_size = SP;
    }

    public final void emitIfGt()
    {
        IfState ifstate = new IfState(this);
        emitGotoIfLe(ifstate.end_label);
        ifstate.start_stack_size = SP;
    }

    public final void emitIfIntCompare(int i)
    {
        IfState ifstate = new IfState(this);
        popType();
        popType();
        reserve(3);
        emitTransfer(ifstate.end_label, i);
        ifstate.start_stack_size = SP;
    }

    public final void emitIfIntEqZero()
    {
        emitIfCompare1(154);
    }

    public final void emitIfIntLEqZero()
    {
        emitIfCompare1(157);
    }

    public final void emitIfIntLt()
    {
        emitIfIntCompare(162);
    }

    public final void emitIfIntNotZero()
    {
        emitIfCompare1(153);
    }

    public final void emitIfLe()
    {
        IfState ifstate = new IfState(this);
        emitGotoIfGt(ifstate.end_label);
        ifstate.start_stack_size = SP;
    }

    public final void emitIfLt()
    {
        IfState ifstate = new IfState(this);
        emitGotoIfGe(ifstate.end_label);
        ifstate.start_stack_size = SP;
    }

    public final void emitIfNEq()
    {
        IfState ifstate = new IfState(this);
        emitGotoIfEq(ifstate.end_label);
        ifstate.start_stack_size = SP;
    }

    public final void emitIfNotNull()
    {
        emitIfRefCompare1(198);
    }

    public final void emitIfNull()
    {
        emitIfRefCompare1(199);
    }

    public final void emitIfRefCompare1(int i)
    {
        IfState ifstate = new IfState(this);
        if (!(popType() instanceof ObjectType))
        {
            throw new Error("non-ref type to emitIfRefCompare1");
        } else
        {
            reserve(3);
            emitTransfer(ifstate.end_label, i);
            ifstate.start_stack_size = SP;
            return;
        }
    }

    public final void emitIfThen()
    {
        new IfState(this, null);
    }

    public void emitInc(Variable variable, short word0)
    {
        if (variable.dead())
        {
            throw new Error("attempting to increment dead variable");
        }
        int i = variable.offset;
        if (i < 0 || !variable.isSimple())
        {
            throw new Error((new StringBuilder()).append("attempting to increment unassigned variable").append(variable.getName()).append(" simple:").append(variable.isSimple()).append(", offset: ").append(i).toString());
        }
        reserve(6);
        if (variable.getType().getImplementationType().promote() != Type.intType)
        {
            throw new Error("attempting to increment non-int variable");
        }
        boolean flag;
        if (i > 255 || word0 > 255 || word0 < -256)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            put1(196);
            put1(132);
            put2(i);
            put2(word0);
            return;
        } else
        {
            put1(132);
            put1(i);
            put1(word0);
            return;
        }
    }

    public void emitInstanceof(Type type)
    {
        emitCheckcast(type, 193);
        pushType(Type.booleanType);
    }

    public void emitInvoke(Method method)
    {
        char c;
        if ((8 & method.access_flags) != 0)
        {
            c = '\270';
        } else
        if (method.classfile.isInterface())
        {
            c = '\271';
        } else
        if ("<init>".equals(method.getName()))
        {
            c = '\267';
        } else
        {
            c = '\266';
        }
        emitInvokeMethod(method, c);
    }

    public void emitInvokeInterface(Method method)
    {
        emitInvokeMethod(method, 185);
    }

    public void emitInvokeMethod(Method method, int i)
    {
        boolean flag = true;
        byte byte0;
        int j;
        boolean flag1;
        boolean flag2;
        if (i == 185)
        {
            byte0 = 5;
        } else
        {
            byte0 = 3;
        }
        reserve(byte0);
        j = method.arg_types.length;
        if (i == 184)
        {
            flag1 = flag;
        } else
        {
            flag1 = false;
        }
        if (i == 183 && "<init>".equals(method.getName()))
        {
            flag2 = flag;
        } else
        {
            flag2 = false;
        }
        if ((8 & method.access_flags) == 0)
        {
            flag = false;
        }
        if (flag1 != flag)
        {
            throw new Error((new StringBuilder()).append("emitInvokeXxx static flag mis-match method.flags=").append(method.access_flags).toString());
        }
        if (!flag1 && !flag2)
        {
            j++;
        }
        put1(i);
        putIndex2(getConstants().addMethodRef(method));
        if (i == 185)
        {
            put1(1 + words(method.arg_types));
            put1(0);
        }
        while (--j >= 0) 
        {
            Type type1 = popType();
            if (type1 instanceof UninitializedType)
            {
                throw new Error((new StringBuilder()).append("passing ").append(type1).append(" as parameter").toString());
            }
        }
        if (flag2)
        {
            Type type = popType();
            if (!(type instanceof UninitializedType))
            {
                throw new Error("calling <init> on already-initialized object");
            }
            ClassType classtype = ((UninitializedType)type).ctype;
            for (int k = 0; k < SP; k++)
            {
                if (stack_types[k] == type)
                {
                    stack_types[k] = classtype;
                }
            }

            Variable avariable[] = locals.used;
            int l;
            if (avariable == null)
            {
                l = 0;
            } else
            {
                l = avariable.length;
            }
            do
            {
                if (--l < 0)
                {
                    break;
                }
                Variable variable = avariable[l];
                if (variable != null && variable.type == type)
                {
                    variable.type = classtype;
                }
            } while (true);
            int i1;
            if (local_types == null)
            {
                i1 = 0;
            } else
            {
                i1 = local_types.length;
            }
            do
            {
                if (--i1 < 0)
                {
                    break;
                }
                if (local_types[i1] == type)
                {
                    local_types[i1] = classtype;
                }
            } while (true);
        }
        if (method.return_type.size != 0)
        {
            pushType(method.return_type);
        }
    }

    public void emitInvokeSpecial(Method method)
    {
        emitInvokeMethod(method, 183);
    }

    public void emitInvokeStatic(Method method)
    {
        emitInvokeMethod(method, 184);
    }

    public void emitInvokeVirtual(Method method)
    {
        emitInvokeMethod(method, 182);
    }

    public final void emitJsr(Label label)
    {
        fixupAdd(5, label);
        reserve(3);
        put1(168);
        PC = 2 + PC;
    }

    public final void emitLoad(Variable variable)
    {
        if (variable.dead())
        {
            throw new Error("attempting to push dead variable");
        }
        int i = variable.offset;
        if (i < 0 || !variable.isSimple())
        {
            throw new Error((new StringBuilder()).append("attempting to load from unassigned variable ").append(variable).append(" simple:").append(variable.isSimple()).append(", offset: ").append(i).toString());
        }
        Type type = variable.getType().promote();
        reserve(4);
        int j = adjustTypedOp(type);
        if (i <= 3)
        {
            put1(i + (26 + j * 4));
        } else
        {
            emitMaybeWide(j + 21, i);
        }
        pushType(variable.getType());
    }

    void emitMaybeWide(int i, int j)
    {
        if (j >= 256)
        {
            put1(196);
            put1(i);
            put2(j);
            return;
        } else
        {
            put1(i);
            put1(j);
            return;
        }
    }

    public final void emitMonitorEnter()
    {
        popType();
        reserve(1);
        put1(194);
    }

    public final void emitMonitorExit()
    {
        popType();
        reserve(1);
        put1(195);
    }

    public final void emitMul()
    {
        emitBinop(104);
    }

    public void emitNew(ClassType classtype)
    {
        reserve(3);
        Label label = new Label(this);
        label.defineRaw(this);
        put1(187);
        putIndex2(getConstants().addClass(classtype));
        pushType(new UninitializedType(classtype, label));
    }

    void emitNewArray(int i)
    {
        reserve(2);
        put1(188);
        put1(i);
    }

    public void emitNewArray(Type type)
    {
        emitNewArray(type, 1);
    }

    public void emitNewArray(Type type, int i)
    {
        if (popType().promote() != Type.intType)
        {
            throw new Error("non-int dim. spec. in emitNewArray");
        }
        if (!(type instanceof PrimType)) goto _L2; else goto _L1
_L1:
        type.getSignature().charAt(0);
        JVM INSTR lookupswitch 8: default 112
    //                   66: 123
    //                   67: 179
    //                   68: 168
    //                   70: 162
    //                   73: 150
    //                   74: 156
    //                   83: 144
    //                   90: 174;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L3:
        throw new Error("bad PrimType in emitNewArray");
_L4:
        byte byte0 = 8;
_L18:
        emitNewArray(byte0);
_L13:
        pushType(new ArrayType(type));
        return;
_L10:
        byte0 = 9;
        continue; /* Loop/switch isn't completed */
_L8:
        byte0 = 10;
        continue; /* Loop/switch isn't completed */
_L9:
        byte0 = 11;
        continue; /* Loop/switch isn't completed */
_L7:
        byte0 = 6;
        continue; /* Loop/switch isn't completed */
_L6:
        byte0 = 7;
        continue; /* Loop/switch isn't completed */
_L11:
        byte0 = 4;
        continue; /* Loop/switch isn't completed */
_L5:
        byte0 = 5;
        continue; /* Loop/switch isn't completed */
_L2:
        if (!(type instanceof ObjectType))
        {
            break; /* Loop/switch isn't completed */
        }
        reserve(3);
        put1(189);
        putIndex2(getConstants().addClass((ObjectType)type));
        if (true) goto _L13; else goto _L12
_L12:
        if (!(type instanceof ArrayType))
        {
            break MISSING_BLOCK_LABEL_318;
        }
        reserve(4);
        put1(197);
        putIndex2(getConstants().addClass(new ArrayType(type)));
        if (i < 1 || i > 255)
        {
            throw new Error("dims out of range in emitNewArray");
        }
        put1(i);
_L16:
        if (--i <= 0) goto _L13; else goto _L14
_L14:
        if (popType().promote() == Type.intType) goto _L16; else goto _L15
_L15:
        throw new Error("non-int dim. spec. in emitNewArray");
        throw new Error("unimplemented type in emitNewArray");
        if (true) goto _L18; else goto _L17
_L17:
    }

    public final void emitNot(Type type)
    {
        emitPushConstant(1, type);
        emitAdd();
        emitPushConstant(1, type);
        emitAnd();
    }

    public void emitPop(int i)
    {
        while (i > 0) 
        {
            reserve(1);
            if (popType().size > 4)
            {
                put1(88);
            } else
            if (i > 1)
            {
                if (popType().size > 4)
                {
                    put1(87);
                    reserve(1);
                }
                put1(88);
                i--;
            } else
            {
                put1(87);
            }
            i--;
        }
    }

    public void emitPrimop(int i, int j, Type type)
    {
        reserve(1);
        while (--j >= 0) 
        {
            popType();
        }
        put1(i);
        pushType(type);
    }

    public final void emitPushClass(ObjectType objecttype)
    {
        emitPushConstant(getConstants().addClass(objecttype));
        pushType(Type.javalangClassType);
    }

    public final void emitPushConstant(int i, Type type)
    {
        switch (type.getSignature().charAt(0))
        {
        default:
            throw new Error("bad type to emitPushConstant");

        case 66: // 'B'
        case 67: // 'C'
        case 73: // 'I'
        case 83: // 'S'
        case 90: // 'Z'
            emitPushInt(i);
            return;

        case 74: // 'J'
            emitPushLong(i);
            return;

        case 70: // 'F'
            emitPushFloat(i);
            return;

        case 68: // 'D'
            emitPushDouble(i);
            return;
        }
    }

    public final void emitPushConstant(CpoolEntry cpoolentry)
    {
        reserve(3);
        int i = cpoolentry.index;
        if (cpoolentry instanceof CpoolValue2)
        {
            put1(20);
            put2(i);
            return;
        }
        if (i < 256)
        {
            put1(18);
            put1(i);
            return;
        } else
        {
            put1(19);
            put2(i);
            return;
        }
    }

    public void emitPushDefaultValue(Type type)
    {
        Type type1 = type.getImplementationType();
        if (type1 instanceof PrimType)
        {
            emitPushConstant(0, type1);
            return;
        } else
        {
            emitPushNull();
            return;
        }
    }

    public void emitPushDouble(double d)
    {
        int i = (int)d;
        if ((double)i == d && i >= -128 && i < 128)
        {
            if (i == 0 || i == 1)
            {
                reserve(1);
                put1(i + 14);
                if (i == 0 && Double.doubleToLongBits(d) != 0L)
                {
                    reserve(1);
                    put1(119);
                }
            } else
            {
                emitPushInt(i);
                reserve(1);
                popType();
                put1(135);
            }
        } else
        {
            emitPushConstant(getConstants().addDouble(d));
        }
        pushType(Type.doubleType);
    }

    public void emitPushFloat(float f)
    {
        int i = (int)f;
        if ((float)i == f && i >= -128 && i < 128)
        {
            if (i >= 0 && i <= 2)
            {
                reserve(1);
                put1(i + 11);
                if (i == 0 && Float.floatToIntBits(f) != 0)
                {
                    reserve(1);
                    put1(118);
                }
            } else
            {
                emitPushInt(i);
                reserve(1);
                popType();
                put1(134);
            }
        } else
        {
            emitPushConstant(getConstants().addFloat(f));
        }
        pushType(Type.floatType);
    }

    public final void emitPushInt(int i)
    {
        reserve(3);
        if (i >= -1 && i <= 5)
        {
            put1(i + 3);
        } else
        if (i >= -128 && i < 128)
        {
            put1(16);
            put1(i);
        } else
        if (i >= -32768 && i < 32768)
        {
            put1(17);
            put2(i);
        } else
        {
            emitPushConstant(getConstants().addInt(i));
        }
        pushType(Type.intType);
    }

    public void emitPushLong(long l)
    {
        if (l == 0L || l == 1L)
        {
            reserve(1);
            put1(9 + (int)l);
        } else
        if ((long)(int)l == l)
        {
            emitPushInt((int)l);
            reserve(1);
            popType();
            put1(133);
        } else
        {
            emitPushConstant(getConstants().addLong(l));
        }
        pushType(Type.longType);
    }

    public void emitPushNull()
    {
        reserve(1);
        put1(1);
        pushType(Type.nullType);
    }

    public final void emitPushPrimArray(Object obj, ArrayType arraytype)
    {
        Type type;
        int i;
        char c;
        int j;
        type = arraytype.getComponentType();
        i = Array.getLength(obj);
        emitPushInt(i);
        emitNewArray(type);
        c = type.getSignature().charAt(0);
        j = 0;
_L26:
        if (j >= i) goto _L2; else goto _L1
_L1:
        long l;
        double d;
        float f;
        l = 0L;
        d = 0.0D;
        f = 0.0F;
        c;
        JVM INSTR lookupswitch 8: default 128
    //                   66: 346
    //                   67: 316
    //                   68: 438
    //                   70: 415
    //                   73: 256
    //                   74: 227
    //                   83: 286
    //                   90: 376;
           goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L3:
        emitDup(arraytype);
        emitPushInt(j);
        c;
        JVM INSTR lookupswitch 8: default 216
    //                   66: 467
    //                   67: 467
    //                   68: 495
    //                   70: 486
    //                   73: 467
    //                   74: 477
    //                   83: 467
    //                   90: 467;
           goto _L12 _L13 _L13 _L14 _L15 _L13 _L16 _L13 _L13
_L12:
        emitArrayStore(type);
_L17:
        j++;
        continue; /* Loop/switch isn't completed */
_L9:
        int j2;
        l = ((long[])(long[])obj)[j];
        j2 = l != 0L;
        f = 0.0F;
        if (j2 != 0) goto _L18; else goto _L17
_L18:
        break; /* Loop/switch isn't completed */
_L8:
        int i2;
        l = ((int[])(int[])obj)[j];
        i2 = l != 0L;
        f = 0.0F;
        if (i2 != 0) goto _L19; else goto _L17
_L19:
        break; /* Loop/switch isn't completed */
_L10:
        int l1;
        l = ((short[])(short[])obj)[j];
        l1 = l != 0L;
        f = 0.0F;
        if (l1 != 0) goto _L20; else goto _L17
_L20:
        break; /* Loop/switch isn't completed */
_L5:
        int k1;
        l = ((char[])(char[])obj)[j];
        k1 = l != 0L;
        f = 0.0F;
        if (k1 != 0) goto _L21; else goto _L17
_L21:
        break; /* Loop/switch isn't completed */
_L4:
        int j1;
        l = ((byte[])(byte[])obj)[j];
        j1 = l != 0L;
        f = 0.0F;
        if (j1 != 0) goto _L22; else goto _L17
_L22:
        break; /* Loop/switch isn't completed */
_L11:
        int i1;
        if (((boolean[])(boolean[])obj)[j])
        {
            l = 1L;
        } else
        {
            l = 0L;
        }
        i1 = l != 0L;
        f = 0.0F;
        if (i1 != 0) goto _L23; else goto _L17
_L23:
        break; /* Loop/switch isn't completed */
_L7:
        f = ((float[])(float[])obj)[j];
        if ((double)f != 0.0D) goto _L24; else goto _L17
_L24:
        break; /* Loop/switch isn't completed */
_L6:
        int k;
        d = ((double[])(double[])obj)[j];
        k = d != 0.0D;
        f = 0.0F;
        if (k != 0) goto _L3; else goto _L17
_L13:
        emitPushInt((int)l);
          goto _L12
_L16:
        emitPushLong(l);
          goto _L12
_L15:
        emitPushFloat(f);
          goto _L12
_L14:
        emitPushDouble(d);
          goto _L12
_L2:
        return;
        if (true) goto _L26; else goto _L25
_L25:
    }

    public final void emitPushString(String s)
    {
        if (s == null)
        {
            emitPushNull();
        } else
        {
            int i = s.length();
            String s1 = calculateSplit(s);
            int j = s1.length();
            if (j <= 1)
            {
                emitPushConstant(getConstants().addString(s));
                pushType(Type.javalangStringType);
                return;
            }
            if (j == 2)
            {
                char c = s1.charAt(0);
                emitPushString(s.substring(0, c));
                emitPushString(s.substring(c));
                emitInvokeVirtual(Type.javalangStringType.getDeclaredMethod("concat", 1));
            } else
            {
                ClassType classtype = ClassType.make("java.lang.StringBuffer");
                emitNew(classtype);
                emitDup(classtype);
                emitPushInt(i);
                Type atype[] = new Type[1];
                atype[0] = Type.intType;
                emitInvokeSpecial(classtype.getDeclaredMethod("<init>", atype));
                Type atype1[] = new Type[1];
                atype1[0] = Type.javalangStringType;
                Method method = classtype.getDeclaredMethod("append", atype1);
                int k = 0;
                for (int l = 0; l < j; l++)
                {
                    emitDup(classtype);
                    int i1 = k + s1.charAt(l);
                    emitPushString(s.substring(k, i1));
                    emitInvokeVirtual(method);
                    k = i1;
                }

                emitInvokeVirtual(Type.toString_method);
            }
            if (s == s.intern())
            {
                emitInvokeVirtual(Type.javalangStringType.getDeclaredMethod("intern", 0));
                return;
            }
        }
    }

    public final void emitPushThis()
    {
        emitLoad(locals.used[0]);
    }

    public final void emitPutField(Field field)
    {
        popType();
        popType();
        emitFieldop(field, 181);
    }

    public final void emitPutStatic(Field field)
    {
        popType();
        emitFieldop(field, 179);
    }

    final void emitRawReturn()
    {
        if (getMethod().getReturnType().size == 0)
        {
            reserve(1);
            put1(177);
        } else
        {
            emitTypedOp(172, popType().promote());
        }
        setUnreachable();
    }

    public final void emitRem()
    {
        emitBinop(112);
    }

    public void emitRet(Variable variable)
    {
        int i = variable.offset;
        if (i < 256)
        {
            reserve(2);
            put1(169);
            put1(i);
            return;
        } else
        {
            reserve(4);
            put1(196);
            put1(169);
            put2(i);
            return;
        }
    }

    public final void emitReturn()
    {
        if (try_stack != null)
        {
            new Error();
        }
        emitRawReturn();
    }

    public final void emitShl()
    {
        emitShift(120);
    }

    public final void emitShr()
    {
        emitShift(122);
    }

    public void emitStore(Variable variable)
    {
        int i = variable.offset;
        if (i < 0 || !variable.isSimple())
        {
            throw new Error((new StringBuilder()).append("attempting to store in unassigned ").append(variable).append(" simple:").append(variable.isSimple()).append(", offset: ").append(i).toString());
        }
        Type type = variable.getType().promote();
        noteVarType(i, type);
        reserve(4);
        popType();
        int j = adjustTypedOp(type);
        if (i <= 3)
        {
            put1(i + (59 + j * 4));
            return;
        } else
        {
            emitMaybeWide(j + 54, i);
            return;
        }
    }

    public void emitStoreDefaultValue(Variable variable)
    {
        emitPushDefaultValue(variable.getType());
        emitStore(variable);
    }

    public final void emitSub()
    {
        emitBinop(100);
    }

    public final void emitSub(char c)
    {
        emitBinop(100, c);
    }

    public final void emitSub(PrimType primtype)
    {
        emitBinop(100, primtype);
    }

    public void emitSwap()
    {
        reserve(1);
        Type type = popType();
        Type type1 = popType();
        if (type.size > 4 || type1.size > 4)
        {
            pushType(type1);
            pushType(type);
            emitDupX();
            emitPop(1);
            return;
        } else
        {
            pushType(type);
            put1(95);
            pushType(type1);
            return;
        }
    }

    public void emitTailCall(boolean flag, Scope scope)
    {
        if (flag)
        {
            Method method = getMethod();
            int i;
            int j;
            if ((8 & method.access_flags) != 0)
            {
                i = 0;
            } else
            {
                i = 1;
            }
            j = method.arg_types.length;
            while (--j >= 0) 
            {
                byte byte1;
                if (method.arg_types[j].size > 4)
                {
                    byte1 = 2;
                } else
                {
                    byte1 = 1;
                }
                i += byte1;
            }
            int k = method.arg_types.length;
            while (--k >= 0) 
            {
                byte byte0;
                if (method.arg_types[k].size > 4)
                {
                    byte0 = 2;
                } else
                {
                    byte0 = 1;
                }
                i -= byte0;
                emitStore(locals.used[i]);
            }
        }
        emitGoto(scope.start);
    }

    public final void emitThen()
    {
        if_stack.start_stack_size = SP;
    }

    public final void emitThrow()
    {
        popType();
        reserve(1);
        put1(191);
        setUnreachable();
    }

    final void emitTransfer(Label label, int i)
    {
        label.setTypes(this);
        fixupAdd(6, label);
        put1(i);
        PC = 2 + PC;
    }

    public void emitTryCatchEnd()
    {
        Variable avariable[];
        if (try_stack.finally_subr != null)
        {
            emitFinallyEnd();
        }
        avariable = try_stack.savedStack;
        if (try_stack.end_label != null) goto _L2; else goto _L1
_L1:
        setUnreachable();
_L4:
        if (try_stack.saved_result != null || avariable != null)
        {
            popScope();
        }
        try_stack = try_stack.previous;
        return;
_L2:
        setTypes(try_stack.start_try.localTypes, Type.typeArray0);
        try_stack.end_label.define(this);
        if (avariable != null)
        {
            int i = avariable.length;
            do
            {
                if (--i < 0)
                {
                    break;
                }
                Variable variable = avariable[i];
                if (variable != null)
                {
                    emitLoad(variable);
                }
            } while (true);
        }
        if (try_stack.saved_result != null)
        {
            emitLoad(try_stack.saved_result);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void emitTryEnd()
    {
        emitTryEnd(false);
    }

    public void emitTryStart(boolean flag, Type type)
    {
        if (type != null && type.isVoid())
        {
            type = null;
        }
        if (type != null || SP > 0)
        {
            pushScope();
        }
        int i = SP;
        Variable avariable[] = null;
        if (i > 0)
        {
            avariable = new Variable[SP];
            int l;
            for (int k = 0; SP > 0; k = l)
            {
                Variable variable = addLocal(topType());
                emitStore(variable);
                l = k + 1;
                avariable[k] = variable;
            }

        }
        TryState trystate = new TryState(this);
        trystate.savedStack = avariable;
        int j;
        if (local_types == null)
        {
            j = 0;
        } else
        {
            j = local_types.length;
        }
        do
        {
            if (j <= 0 || local_types[j - 1] != null)
            {
                Type atype[];
                if (j == 0)
                {
                    atype = Type.typeArray0;
                } else
                {
                    atype = new Type[j];
                    System.arraycopy(local_types, 0, atype, 0, j);
                }
                trystate.start_try.localTypes = atype;
                if (type != null)
                {
                    trystate.saved_result = addLocal(type);
                }
                if (flag)
                {
                    trystate.finally_subr = new Label();
                }
                return;
            }
            j--;
        } while (true);
    }

    public final void emitUshr()
    {
        emitShift(124);
    }

    public void emitWithCleanupCatch(Variable variable)
    {
        emitTryEnd();
        Type atype[];
        if (SP > 0)
        {
            atype = new Type[SP];
            System.arraycopy(stack_types, 0, atype, 0, SP);
            SP = 0;
        } else
        {
            atype = null;
        }
        try_stack.savedTypes = atype;
        try_stack.saved_result = variable;
        int _tmp = SP;
        emitCatchStart(variable);
    }

    public void emitWithCleanupDone()
    {
        Variable variable = try_stack.saved_result;
        try_stack.saved_result = null;
        if (variable != null)
        {
            emitLoad(variable);
        }
        emitThrow();
        emitCatchEnd();
        Type atype[] = try_stack.savedTypes;
        emitTryCatchEnd();
        if (atype != null)
        {
            SP = atype.length;
            if (SP >= stack_types.length)
            {
                stack_types = atype;
                return;
            } else
            {
                System.arraycopy(atype, 0, stack_types, 0, SP);
                return;
            }
        } else
        {
            SP = 0;
            return;
        }
    }

    public void emitWithCleanupStart()
    {
        int i = SP;
        SP = 0;
        emitTryStart(false, null);
        SP = i;
    }

    public final void emitXOr()
    {
        emitBinop(130);
    }

    public void endExitableBlock()
    {
        ExitableBlock exitableblock = currentExitableBlock;
        exitableblock.finish();
        currentExitableBlock = exitableblock.outer;
    }

    public void endFragment(int i)
    {
        fixup_offsets[i] = 0xa | fixup_count << 4;
        Label label = fixup_labels[i];
        fixupAdd(9, 0, null);
        label.define(this);
    }

    public void enterScope(Scope scope)
    {
        scope.setStartPC(this);
        locals.enterScope(scope);
    }

    final void fixupAdd(int i, int j, Label label)
    {
        int k;
        if (label != null && i != 1 && i != 0 && i != 2 && i != 11)
        {
            label.needsStackMapEntry = true;
        }
        k = fixup_count;
        if (k != 0) goto _L2; else goto _L1
_L1:
        fixup_offsets = new int[30];
        fixup_labels = new Label[30];
_L4:
        fixup_offsets[k] = i | j << 4;
        fixup_labels[k] = label;
        fixup_count = k + 1;
        return;
_L2:
        if (fixup_count == fixup_offsets.length)
        {
            int l = k * 2;
            Label alabel[] = new Label[l];
            System.arraycopy(fixup_labels, 0, alabel, 0, k);
            fixup_labels = alabel;
            int ai[] = new int[l];
            System.arraycopy(fixup_offsets, 0, ai, 0, k);
            fixup_offsets = ai;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void fixupAdd(int i, Label label)
    {
        fixupAdd(i, PC, label);
    }

    public final void fixupChain(Label label, Label label1)
    {
        fixupAdd(9, 0, label1);
        label.defineRaw(this);
    }

    public Variable getArg(int i)
    {
        return locals.parameter_scope.getVariable(i);
    }

    public final Attribute getAttributes()
    {
        return attributes;
    }

    public byte[] getCode()
    {
        return code;
    }

    public int getCodeLength()
    {
        return PC;
    }

    public final ConstantPool getConstants()
    {
        return getMethod().classfile.constants;
    }

    public Scope getCurrentScope()
    {
        return locals.current_scope;
    }

    public final TryState getCurrentTry()
    {
        return try_stack;
    }

    public Label getLabel()
    {
        Label label = new Label();
        label.defineRaw(this);
        return label;
    }

    public final int getLength()
    {
        return 12 + getCodeLength() + 8 * exception_table_length + Attribute.getLengthAll(this);
    }

    public int getMaxLocals()
    {
        return max_locals;
    }

    public int getMaxStack()
    {
        return max_stack;
    }

    public final Method getMethod()
    {
        return (Method)getContainer();
    }

    public final int getPC()
    {
        return PC;
    }

    public final int getSP()
    {
        return SP;
    }

    byte invert_opcode(byte byte0)
    {
        int i = byte0 & 0xff;
        if (i >= 153 && i <= 166 || i >= 198 && i <= 199)
        {
            return (byte)(i ^ 1);
        } else
        {
            throw new Error("unknown opcode to invert_opcode");
        }
    }

    public final boolean isInTry()
    {
        return try_stack != null;
    }

    public Variable lookup(String s)
    {
        for (Scope scope = locals.current_scope; scope != null; scope = scope.parent)
        {
            Variable variable = scope.lookup(s);
            if (variable != null)
            {
                return variable;
            }
        }

        return null;
    }

    void noteParamTypes()
    {
        Method method = getMethod();
        int i = 8 & method.access_flags;
        int j = 0;
        if (i == 0)
        {
            Object obj = method.classfile;
            if ("<init>".equals(method.getName()) && !"java.lang.Object".equals(((Type) (obj)).getName()))
            {
                obj = UninitializedType.uninitializedThis((ClassType)obj);
            }
            int i3 = 0 + 1;
            noteVarType(0, ((Type) (obj)));
            j = i3;
        }
        int k = method.arg_types.length;
        int l = 0;
        int i1;
        int k2;
        for (i1 = j; l < k; i1 = k2)
        {
            Type type = method.arg_types[l];
            k2 = i1 + 1;
            noteVarType(i1, type);
            for (int l2 = type.getSizeInWords(); --l2 > 0;)
            {
                k2++;
            }

            l++;
        }

        if ((1 & flags) != 0)
        {
            stackMap = new StackMapTableAttr();
            int ai[] = new int[i1 + 20];
            int j1 = 0;
            int k1;
            int i2;
            for (k1 = 0; j1 < i1; k1 = i2)
            {
                int l1 = stackMap.encodeVerificationType(local_types[j1], this);
                i2 = k1 + 1;
                ai[k1] = l1;
                int j2 = l1 & 0xff;
                if (j2 == 3 || j2 == 4)
                {
                    j1++;
                }
                j1++;
            }

            stackMap.encodedLocals = ai;
            stackMap.countLocals = k1;
            stackMap.encodedStack = new int[10];
            stackMap.countStack = 0;
        }
    }

    public void noteVarType(int i, Type type)
    {
        int j = type.getSizeInWords();
        if (local_types != null) goto _L2; else goto _L1
_L1:
        local_types = new Type[20 + (i + j)];
_L8:
        local_types[i] = type;
        if (varsSetInCurrentBlock != null) goto _L4; else goto _L3
_L3:
        varsSetInCurrentBlock = new boolean[local_types.length];
_L6:
        varsSetInCurrentBlock[i] = true;
        if (i > 0)
        {
            Type type1 = local_types[i - 1];
            if (type1 != null && type1.getSizeInWords() == 2)
            {
                local_types[i - 1] = null;
            }
        }
        while (--j > 0) 
        {
            Type atype1[] = local_types;
            i++;
            atype1[i] = null;
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (i + j > local_types.length)
        {
            Type atype[] = new Type[2 * (i + j)];
            System.arraycopy(local_types, 0, atype, 0, local_types.length);
            local_types = atype;
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (varsSetInCurrentBlock.length <= i)
        {
            boolean aflag[] = new boolean[local_types.length];
            System.arraycopy(varsSetInCurrentBlock, 0, aflag, 0, varsSetInCurrentBlock.length);
            varsSetInCurrentBlock = aflag;
        }
        if (true) goto _L6; else goto _L5
_L5:
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    public Scope popScope()
    {
        Scope scope = locals.current_scope;
        locals.current_scope = scope.parent;
        scope.freeLocals(this);
        scope.end = getLabel();
        return scope;
    }

    public final Type popType()
    {
        if (SP <= 0)
        {
            throw new Error((new StringBuilder()).append("popType called with empty stack ").append(getMethod()).toString());
        }
        Type atype[] = stack_types;
        int i = -1 + SP;
        SP = i;
        Type type = atype[i];
        if (type.size == 8 && !popType().isVoid())
        {
            throw new Error("missing void type on stack");
        } else
        {
            return type;
        }
    }

    public void print(ClassTypeWriter classtypewriter)
    {
        classtypewriter.print("Attribute \"");
        classtypewriter.print(getName());
        classtypewriter.print("\", length:");
        classtypewriter.print(getLength());
        classtypewriter.print(", max_stack:");
        classtypewriter.print(max_stack);
        classtypewriter.print(", max_locals:");
        classtypewriter.print(max_locals);
        classtypewriter.print(", code_length:");
        int i = getCodeLength();
        classtypewriter.println(i);
        disAssemble(classtypewriter, 0, i);
        if (exception_table_length > 0)
        {
            classtypewriter.print("Exceptions (count: ");
            classtypewriter.print(exception_table_length);
            classtypewriter.println("):");
            int j = exception_table_length;
            int k = 0;
            while (--j >= 0) 
            {
                classtypewriter.print("  start: ");
                classtypewriter.print(0xffff & exception_table[k]);
                classtypewriter.print(", end: ");
                classtypewriter.print(0xffff & exception_table[k + 1]);
                classtypewriter.print(", handler: ");
                classtypewriter.print(0xffff & exception_table[k + 2]);
                classtypewriter.print(", type: ");
                int l = 0xffff & exception_table[k + 3];
                if (l == 0)
                {
                    classtypewriter.print("0 /* finally */");
                } else
                {
                    classtypewriter.printOptionalIndex(l);
                    classtypewriter.printConstantTersely(l, 7);
                }
                classtypewriter.println();
                k += 4;
            }
        }
        classtypewriter.printAttributes(this);
    }

    public void processFixups()
    {
        int i;
        int j;
        int k;
        if (fixup_count <= 0)
        {
            return;
        }
        i = 0;
        j = fixup_count;
        fixupAdd(9, 0, null);
        k = 0;
_L12:
        int i1;
        int j1;
        Label label;
        int l = fixup_offsets[k];
        i1 = l & 0xf;
        j1 = l >> 4;
        label = fixup_labels[k];
        i1;
        JVM INSTR tableswitch 0 14: default 132
    //                   0 146
    //                   1 158
    //                   2 173
    //                   3 146
    //                   4 179
    //                   5 230
    //                   6 246
    //                   7 132
    //                   8 146
    //                   9 279
    //                   10 262
    //                   11 143
    //                   12 132
    //                   13 132
    //                   14 152;
           goto _L1 _L2 _L3 _L4 _L2 _L5 _L6 _L7 _L1 _L2 _L8 _L9 _L10 _L1 _L1 _L11
_L7:
        break MISSING_BLOCK_LABEL_246;
_L9:
        break MISSING_BLOCK_LABEL_262;
_L8:
        break MISSING_BLOCK_LABEL_279;
_L2:
        break; /* Loop/switch isn't completed */
_L1:
        throw new Error("unexpected fixup");
_L10:
        k += 2;
_L13:
        k++;
          goto _L12
_L11:
        k++;
          goto _L13
_L3:
        label.position = i + label.position;
          goto _L13
_L4:
        i += 3;
          goto _L13
_L5:
        if (label.first_fixup != k + 1 || fixupOffset(k + 1) != j1 + 3) goto _L6; else goto _L14
_L14:
        fixup_offsets[k] = 8 | j1 << 4;
        fixup_labels[k] = null;
        i -= 3;
          goto _L13
_L6:
        if (PC >= 32768)
        {
            i += 2;
        }
          goto _L13
        if (PC >= 32768)
        {
            i += 5;
        }
          goto _L13
        fixup_labels[j] = fixup_labels[k + 1];
        j = j1;
        int k1;
        int i2;
        int j2;
        int k2;
        int j13;
        Label label4;
        if (k + 1 >= fixup_count)
        {
            k1 = PC;
        } else
        {
            k1 = fixupOffset(fixup_labels[k + 1].first_fixup);
        }
        fixup_offsets[k] = 9 | k1 << 4;
        if (label != null) goto _L16; else goto _L15
_L15:
        i2 = PC;
        j2 = 0;
        k2 = 0;
_L28:
        if (k2 >= fixup_count) goto _L18; else goto _L17
_L16:
        k = label.first_fixup;
        int l1 = fixupOffset(k);
        i = (k1 + i) - l1;
          goto _L12
_L17:
        int k13;
        int i13 = fixup_offsets[k2];
        j13 = i13 & 0xf;
        label4 = fixup_labels[k2];
        if (label4 != null && label4.position < 0)
        {
            throw new Error((new StringBuilder()).append("undefined label ").append(label4).toString());
        }
        while (label4 != null && j13 >= 4 && j13 <= 7 && 1 + label4.first_fixup < fixup_count && fixup_offsets[1 + label4.first_fixup] == (4 | 0xf & fixup_offsets[label4.first_fixup])) 
        {
            label4 = fixup_labels[1 + label4.first_fixup];
            fixup_labels[k2] = label4;
        }
        k13 = i13 >> 4;
        j13;
        JVM INSTR tableswitch 0 14: default 616
    //                   0 645
    //                   1 666
    //                   2 679
    //                   3 645
    //                   4 707
    //                   5 707
    //                   6 707
    //                   7 616
    //                   8 657
    //                   9 794
    //                   10 616
    //                   11 627
    //                   12 616
    //                   13 616
    //                   14 651;
           goto _L19 _L20 _L21 _L22 _L20 _L23 _L23 _L23 _L19 _L24 _L25 _L19 _L26 _L19 _L19 _L27
_L23:
        break MISSING_BLOCK_LABEL_707;
_L25:
        break MISSING_BLOCK_LABEL_794;
_L20:
        break; /* Loop/switch isn't completed */
_L19:
        throw new Error("unexpected fixup");
_L26:
        k2 += 2;
        fixup_labels[k2].position = k13 + j2;
_L29:
        k2++;
          goto _L28
_L27:
        k2++;
          goto _L29
_L24:
        j2 -= 3;
        i2 -= 3;
          goto _L29
_L21:
        label4.position = k13 + j2;
          goto _L29
_L22:
        int j14 = 3 & 3 - (k13 + j2);
        j2 += j14;
        i2 += j14;
          goto _L29
        int i14 = label4.position - (k13 + j2);
        if ((short)i14 == i14)
        {
            fixup_offsets[k2] = 7 | k13 << 4;
        } else
        {
            byte byte3;
            byte byte4;
            if (j13 == 6)
            {
                byte3 = 5;
            } else
            {
                byte3 = 2;
            }
            j2 += byte3;
            if (j13 == 6)
            {
                byte4 = 5;
            } else
            {
                byte4 = 2;
            }
            i2 += byte4;
        }
          goto _L29
        if (label4 != null) goto _L30; else goto _L18
_L18:
        byte abyte0[];
        int l2;
        int i3;
        int j3;
        Label label1;
        int k3;
        int l3;
        abyte0 = new byte[i2];
        l2 = -1;
        i3 = 0;
        j3 = fixupOffset(0);
        label1 = null;
        k3 = 0;
        break MISSING_BLOCK_LABEL_824;
_L30:
        k2 = label4.first_fixup;
        int l13 = fixupOffset(k2);
        j2 = (k13 + j2) - l13;
          goto _L28
_L41:
        int i4;
        Label label2;
        int k12;
        for (l3 = 0; k3 < j3; l3 = k12)
        {
            k12 = l3 + 1;
            byte abyte3[] = code;
            int l12 = k3 + 1;
            abyte0[l3] = abyte3[k3];
            k3 = l12;
        }

        i4 = 0xf & fixup_offsets[i3];
        label2 = fixup_labels[i3];
        if (label1 != null && label1.position < l3)
        {
            stackMap.emitStackMapEntry(label1, this);
            label1 = null;
        }
        if (label1 != null && label1.position > l3)
        {
            throw new Error("labels out of order");
        }
        i4;
        JVM INSTR tableswitch 0 14: default 1052
    //                   0 1063
    //                   1 1093
    //                   2 1413
    //                   3 1052
    //                   4 1227
    //                   5 1227
    //                   6 1227
    //                   7 1155
    //                   8 1142
    //                   9 1817
    //                   10 1052
    //                   11 1668
    //                   12 1052
    //                   13 1052
    //                   14 1750;
           goto _L31 _L32 _L33 _L34 _L31 _L35 _L35 _L35 _L36 _L37 _L38 _L31 _L39 _L31 _L31 _L40
_L31:
        throw new Error("unexpected fixup");
_L32:
        int l4;
        int i5;
        l4 = k3;
        i5 = l3;
_L42:
        i3++;
        j3 = fixupOffset(i3);
        k3 = l4;
        l3 = i5;
          goto _L41
_L33:
        int l5;
        int j6;
        if (stackMap != null && label2 != null && label2.stackTypes != null && label2.needsStackMapEntry)
        {
            label1 = mergeLabels(label1, label2);
            l4 = k3;
            i5 = l3;
        } else
        {
            l4 = k3;
            i5 = l3;
        }
          goto _L42
_L37:
        l4 = k3 + 3;
        i5 = l3;
          goto _L42
_L36:
        int l11 = label2.position - l3;
        int i12 = l3 + 1;
        abyte0[l3] = code[k3];
        int j12 = i12 + 1;
        abyte0[i12] = (byte)(l11 >> 8);
        i5 = j12 + 1;
        abyte0[j12] = (byte)(l11 & 0xff);
        l4 = k3 + 3;
          goto _L42
_L35:
        int k9 = label2.position - l3;
        byte byte0 = code[k3];
        byte byte1;
        int l9;
        int i10;
        int j10;
        int k10;
        int l10;
        int i11;
        if (i4 == 6)
        {
            byte byte2 = invert_opcode(byte0);
            int j11 = l3 + 1;
            abyte0[l3] = byte2;
            int k11 = j11 + 1;
            abyte0[j11] = 0;
            l9 = k11 + 1;
            abyte0[k11] = 8;
            byte1 = -56;
        } else
        {
            byte1 = (byte)(byte0 + 33);
            l9 = l3;
        }
        i10 = l9 + 1;
        abyte0[l9] = byte1;
        j10 = i10 + 1;
        abyte0[i10] = (byte)(k9 >> 24);
        k10 = j10 + 1;
        abyte0[j10] = (byte)(k9 >> 16);
        l10 = k10 + 1;
        abyte0[k10] = (byte)(k9 >> 8);
        i11 = l10 + 1;
        abyte0[l10] = (byte)(k9 & 0xff);
        l4 = k3 + 3;
        i5 = i11;
          goto _L42
_L34:
        int k5 = 3 & 3 - l3;
        l5 = l3;
        int i6 = l3 + 1;
        byte abyte1[] = code;
        l4 = k3 + 1;
        abyte0[l3] = abyte1[k3];
        int j9;
        for (j6 = i6; --k5 >= 0; j6 = j9)
        {
            j9 = j6 + 1;
            abyte0[j6] = 0;
        }

        break MISSING_BLOCK_LABEL_1944;
_L43:
        i3++;
        int l6 = fixupOffset(i3);
        i7 = l4;
        j7 = i5;
        while (i7 < l6) 
        {
            int k7 = j7 + 1;
            byte abyte2[] = code;
            int l7 = i7 + 1;
            abyte0[j7] = abyte2[i7];
            i7 = l7;
            j7 = k7;
        }
        i8 = fixup_labels[i3].position - l5;
        j8 = j7 + 1;
        abyte0[j7] = (byte)(i8 >> 24);
        k8 = j8 + 1;
        abyte0[j8] = (byte)(i8 >> 16);
        l8 = k8 + 1;
        abyte0[k8] = (byte)(i8 >> 8);
        i9 = l8 + 1;
        abyte0[l8] = (byte)(i8 & 0xff);
        l4 = i7 + 4;
        i5 = i9;
_L44:
        k6 = fixup_count;
        if (i3 >= k6 || fixupKind(i3 + 1) != 3) goto _L42; else goto _L43
_L39:
        Label label3 = fixup_labels[i3 + 2];
        int j5 = fixupOffset(i3 + 1);
        if (stackMap != null)
        {
            label1 = mergeLabels(label1, label3);
        }
        addHandler(fixup_labels[i3].position, fixup_labels[i3 + 1].position, l3, j5);
        i3 += 2;
        l4 = k3;
        i5 = l3;
          goto _L42
_L40:
        if (lines == null)
        {
            LineNumbersAttr linenumbersattr = new LineNumbersAttr(this);
            lines = linenumbersattr;
        }
        i3++;
        int k4 = fixupOffset(i3);
        if (k4 != l2)
        {
            lines.put(k4, l3);
        }
        l2 = k4;
        l4 = k3;
        i5 = l3;
          goto _L42
_L38:
        int j4;
        if (label2 == null)
        {
            if (i2 != l3)
            {
                throw new Error((new StringBuilder()).append("PC confusion new_pc:").append(l3).append(" new_size:").append(i2).toString());
            } else
            {
                PC = i2;
                code = abyte0;
                fixup_count = 0;
                fixup_labels = null;
                fixup_offsets = null;
                return;
            }
        }
        i3 = label2.first_fixup;
        j4 = fixupOffset(i3);
        j3 = j4;
        if (label2.position != l3)
        {
            throw new Error("bad pc");
        }
        k3 = j4;
          goto _L41
        i5 = j6;
          goto _L44
    }

    public Scope pushScope()
    {
        Scope scope = new Scope();
        if (locals == null)
        {
            locals = new LocalVarsAttr(getMethod());
        }
        enterScope(scope);
        if (locals.parameter_scope == null)
        {
            locals.parameter_scope = scope;
        }
        return scope;
    }

    public final void pushType(Type type)
    {
        if (type.size == 0)
        {
            throw new Error("pushing void type onto stack");
        }
        if (stack_types != null && stack_types.length != 0) goto _L2; else goto _L1
_L1:
        stack_types = new Type[20];
_L4:
        if (type.size == 8)
        {
            Type atype1[] = stack_types;
            int j = SP;
            SP = j + 1;
            atype1[j] = Type.voidType;
        }
        Type atype[] = stack_types;
        int i = SP;
        SP = i + 1;
        atype[i] = type;
        if (SP > max_stack)
        {
            max_stack = SP;
        }
        return;
_L2:
        if (1 + SP >= stack_types.length)
        {
            Type atype2[] = new Type[2 * stack_types.length];
            System.arraycopy(stack_types, 0, atype2, 0, SP);
            stack_types = atype2;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void put1(int i)
    {
        byte abyte0[] = code;
        int j = PC;
        PC = j + 1;
        abyte0[j] = (byte)i;
        unreachable_here = false;
    }

    public final void put2(int i)
    {
        byte abyte0[] = code;
        int j = PC;
        PC = j + 1;
        abyte0[j] = (byte)(i >> 8);
        byte abyte1[] = code;
        int k = PC;
        PC = k + 1;
        abyte1[k] = (byte)i;
        unreachable_here = false;
    }

    public final void put4(int i)
    {
        byte abyte0[] = code;
        int j = PC;
        PC = j + 1;
        abyte0[j] = (byte)(i >> 24);
        byte abyte1[] = code;
        int k = PC;
        PC = k + 1;
        abyte1[k] = (byte)(i >> 16);
        byte abyte2[] = code;
        int l = PC;
        PC = l + 1;
        abyte2[l] = (byte)(i >> 8);
        byte abyte3[] = code;
        int i1 = PC;
        PC = i1 + 1;
        abyte3[i1] = (byte)i;
        unreachable_here = false;
    }

    public final void putIndex2(CpoolEntry cpoolentry)
    {
        put2(cpoolentry.index);
    }

    public final void putLineNumber(int i)
    {
        if (sourceDbgExt != null)
        {
            i = sourceDbgExt.fixLine(i);
        }
        fixupAdd(14, null);
        fixupAdd(15, i, null);
    }

    public final void putLineNumber(String s, int i)
    {
        if (s != null)
        {
            getMethod().classfile.setSourceFile(s);
        }
        putLineNumber(i);
    }

    public final boolean reachableHere()
    {
        return !unreachable_here;
    }

    public final void reserve(int i)
    {
        if (code == null)
        {
            code = new byte[i + 100];
        } else
        if (i + PC > code.length)
        {
            byte abyte0[] = new byte[i + 2 * code.length];
            System.arraycopy(code, 0, abyte0, 0, PC);
            code = abyte0;
            return;
        }
    }

    public final void setAttributes(Attribute attribute)
    {
        attributes = attribute;
    }

    public void setCode(byte abyte0[])
    {
        code = abyte0;
        PC = abyte0.length;
    }

    public void setCodeLength(int i)
    {
        PC = i;
    }

    public void setMaxLocals(int i)
    {
        max_locals = i;
    }

    public void setMaxStack(int i)
    {
        max_stack = i;
    }

    public final void setReachable(boolean flag)
    {
        boolean flag1;
        if (!flag)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        unreachable_here = flag1;
    }

    public final void setTypes(Label label)
    {
        setTypes(label.localTypes, label.stackTypes);
    }

    public final void setTypes(Type atype[], Type atype1[])
    {
        int i = atype1.length;
        int j = atype.length;
        if (local_types != null)
        {
            if (j > 0)
            {
                System.arraycopy(atype, 0, local_types, 0, j);
            }
            for (int l = j; l < local_types.length; l++)
            {
                local_types[l] = null;
            }

        }
        if (stack_types == null || i > stack_types.length)
        {
            stack_types = new Type[i];
        } else
        {
            int k = i;
            while (k < stack_types.length) 
            {
                stack_types[k] = null;
                k++;
            }
        }
        System.arraycopy(atype1, 0, stack_types, 0, i);
        SP = i;
    }

    public final void setUnreachable()
    {
        unreachable_here = true;
    }

    public ExitableBlock startExitableBlock(Type type, boolean flag)
    {
        ExitableBlock exitableblock = new ExitableBlock(type, this, flag);
        exitableblock.outer = currentExitableBlock;
        currentExitableBlock = exitableblock;
        return exitableblock;
    }

    public SwitchState startSwitch()
    {
        SwitchState switchstate = new SwitchState(this);
        switchstate.switchValuePushed(this);
        return switchstate;
    }

    public final Type topType()
    {
        return stack_types[-1 + SP];
    }

    boolean useJsr()
    {
        return (2 & flags) == 0;
    }

    public void write(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeShort(max_stack);
        dataoutputstream.writeShort(max_locals);
        dataoutputstream.writeInt(PC);
        dataoutputstream.write(code, 0, PC);
        dataoutputstream.writeShort(exception_table_length);
        int i = exception_table_length;
        for (int j = 0; --i >= 0; j += 4)
        {
            dataoutputstream.writeShort(exception_table[j]);
            dataoutputstream.writeShort(exception_table[j + 1]);
            dataoutputstream.writeShort(exception_table[j + 2]);
            dataoutputstream.writeShort(exception_table[j + 3]);
        }

        Attribute.writeAll(this, dataoutputstream);
    }

}
