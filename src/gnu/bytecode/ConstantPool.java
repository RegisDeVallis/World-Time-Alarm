// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

// Referenced classes of package gnu.bytecode:
//            CpoolEntry, CpoolUtf8, CpoolValue1, CpoolValue2, 
//            CpoolClass, CpoolString, CpoolRef, CpoolNameAndType, 
//            ObjectType, Field, Method, ClassType

public class ConstantPool
{

    public static final byte CLASS = 7;
    public static final byte DOUBLE = 6;
    public static final byte FIELDREF = 9;
    public static final byte FLOAT = 4;
    public static final byte INTEGER = 3;
    public static final byte INTERFACE_METHODREF = 11;
    public static final byte LONG = 5;
    public static final byte METHODREF = 10;
    public static final byte NAME_AND_TYPE = 12;
    public static final byte STRING = 8;
    public static final byte UTF8 = 1;
    int count;
    CpoolEntry hashTab[];
    boolean locked;
    CpoolEntry pool[];

    public ConstantPool()
    {
    }

    public ConstantPool(DataInputStream datainputstream)
        throws IOException
    {
        int i;
        count = -1 + datainputstream.readUnsignedShort();
        pool = new CpoolEntry[1 + count];
        i = 1;
_L10:
        byte byte0;
        CpoolEntry cpoolentry;
        if (i > count)
        {
            break MISSING_BLOCK_LABEL_297;
        }
        byte0 = datainputstream.readByte();
        cpoolentry = getForced(i, byte0);
        byte0;
        JVM INSTR tableswitch 1 12: default 112
    //                   1 118
    //                   2 112
    //                   3 133
    //                   4 133
    //                   5 148
    //                   6 148
    //                   7 166
    //                   8 189
    //                   9 212
    //                   10 212
    //                   11 212
    //                   12 253;
           goto _L1 _L2 _L1 _L3 _L3 _L4 _L4 _L5 _L6 _L7 _L7 _L7 _L8
_L8:
        break MISSING_BLOCK_LABEL_253;
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        break; /* Loop/switch isn't completed */
_L11:
        i++;
        if (true) goto _L10; else goto _L9
_L9:
        ((CpoolUtf8)cpoolentry).string = datainputstream.readUTF();
          goto _L11
_L3:
        ((CpoolValue1)cpoolentry).value = datainputstream.readInt();
          goto _L11
_L4:
        ((CpoolValue2)cpoolentry).value = datainputstream.readLong();
        i++;
          goto _L11
_L5:
        ((CpoolClass)cpoolentry).name = (CpoolUtf8)getForced(datainputstream.readUnsignedShort(), 1);
          goto _L11
_L6:
        ((CpoolString)cpoolentry).str = (CpoolUtf8)getForced(datainputstream.readUnsignedShort(), 1);
          goto _L11
_L7:
        CpoolRef cpoolref = (CpoolRef)cpoolentry;
        cpoolref.clas = getForcedClass(datainputstream.readUnsignedShort());
        cpoolref.nameAndType = (CpoolNameAndType)getForced(datainputstream.readUnsignedShort(), 12);
          goto _L11
        CpoolNameAndType cpoolnameandtype = (CpoolNameAndType)cpoolentry;
        cpoolnameandtype.name = (CpoolUtf8)getForced(datainputstream.readUnsignedShort(), 1);
        cpoolnameandtype.type = (CpoolUtf8)getForced(datainputstream.readUnsignedShort(), 1);
          goto _L11
    }

    public CpoolClass addClass(CpoolUtf8 cpoolutf8)
    {
        int i = CpoolClass.hashCode(cpoolutf8);
        if (hashTab == null)
        {
            rehash();
        }
        int j = (0x7fffffff & i) % hashTab.length;
        for (CpoolEntry cpoolentry = hashTab[j]; cpoolentry != null; cpoolentry = cpoolentry.next)
        {
            if (i != cpoolentry.hash || !(cpoolentry instanceof CpoolClass))
            {
                continue;
            }
            CpoolClass cpoolclass = (CpoolClass)cpoolentry;
            if (cpoolclass.name == cpoolutf8)
            {
                return cpoolclass;
            }
        }

        return new CpoolClass(this, i, cpoolutf8);
    }

    public CpoolClass addClass(ObjectType objecttype)
    {
        CpoolClass cpoolclass = addClass(addUtf8(objecttype.getInternalName()));
        cpoolclass.clas = objecttype;
        return cpoolclass;
    }

    public CpoolValue2 addDouble(double d)
    {
        return addValue2(6, Double.doubleToLongBits(d));
    }

    public CpoolRef addFieldRef(Field field)
    {
        return addRef(9, addClass(field.owner), addNameAndType(field));
    }

    public CpoolValue1 addFloat(float f)
    {
        return addValue1(4, Float.floatToIntBits(f));
    }

    public CpoolValue1 addInt(int i)
    {
        return addValue1(3, i);
    }

    public CpoolValue2 addLong(long l)
    {
        return addValue2(5, l);
    }

    public CpoolRef addMethodRef(Method method)
    {
        CpoolClass cpoolclass = addClass(method.classfile);
        byte byte0;
        if ((0x200 & method.getDeclaringClass().getModifiers()) == 0)
        {
            byte0 = 10;
        } else
        {
            byte0 = 11;
        }
        return addRef(byte0, cpoolclass, addNameAndType(method));
    }

    public CpoolNameAndType addNameAndType(CpoolUtf8 cpoolutf8, CpoolUtf8 cpoolutf8_1)
    {
        int i = CpoolNameAndType.hashCode(cpoolutf8, cpoolutf8_1);
        if (hashTab == null)
        {
            rehash();
        }
        int j = (0x7fffffff & i) % hashTab.length;
        for (CpoolEntry cpoolentry = hashTab[j]; cpoolentry != null; cpoolentry = cpoolentry.next)
        {
            if (i == cpoolentry.hash && (cpoolentry instanceof CpoolNameAndType) && ((CpoolNameAndType)cpoolentry).name == cpoolutf8 && ((CpoolNameAndType)cpoolentry).type == cpoolutf8_1)
            {
                return (CpoolNameAndType)cpoolentry;
            }
        }

        return new CpoolNameAndType(this, i, cpoolutf8, cpoolutf8_1);
    }

    public CpoolNameAndType addNameAndType(Field field)
    {
        return addNameAndType(addUtf8(field.getName()), addUtf8(field.getSignature()));
    }

    public CpoolNameAndType addNameAndType(Method method)
    {
        return addNameAndType(addUtf8(method.getName()), addUtf8(method.getSignature()));
    }

    public CpoolRef addRef(int i, CpoolClass cpoolclass, CpoolNameAndType cpoolnameandtype)
    {
        int j = CpoolRef.hashCode(cpoolclass, cpoolnameandtype);
        if (hashTab == null)
        {
            rehash();
        }
        int k = (0x7fffffff & j) % hashTab.length;
        for (CpoolEntry cpoolentry = hashTab[k]; cpoolentry != null; cpoolentry = cpoolentry.next)
        {
            if (j != cpoolentry.hash || !(cpoolentry instanceof CpoolRef))
            {
                continue;
            }
            CpoolRef cpoolref = (CpoolRef)cpoolentry;
            if (cpoolref.tag == i && cpoolref.clas == cpoolclass && cpoolref.nameAndType == cpoolnameandtype)
            {
                return cpoolref;
            }
        }

        return new CpoolRef(this, j, i, cpoolclass, cpoolnameandtype);
    }

    public CpoolString addString(CpoolUtf8 cpoolutf8)
    {
        int i = CpoolString.hashCode(cpoolutf8);
        if (hashTab == null)
        {
            rehash();
        }
        int j = (0x7fffffff & i) % hashTab.length;
        for (CpoolEntry cpoolentry = hashTab[j]; cpoolentry != null; cpoolentry = cpoolentry.next)
        {
            if (i != cpoolentry.hash || !(cpoolentry instanceof CpoolString))
            {
                continue;
            }
            CpoolString cpoolstring = (CpoolString)cpoolentry;
            if (cpoolstring.str == cpoolutf8)
            {
                return cpoolstring;
            }
        }

        return new CpoolString(this, i, cpoolutf8);
    }

    public final CpoolString addString(String s)
    {
        return addString(addUtf8(s));
    }

    public CpoolUtf8 addUtf8(String s)
    {
        String s1 = s.intern();
        int i = s1.hashCode();
        if (hashTab == null)
        {
            rehash();
        }
        int j = (0x7fffffff & i) % hashTab.length;
        for (CpoolEntry cpoolentry = hashTab[j]; cpoolentry != null; cpoolentry = cpoolentry.next)
        {
            if (i != cpoolentry.hash || !(cpoolentry instanceof CpoolUtf8))
            {
                continue;
            }
            CpoolUtf8 cpoolutf8 = (CpoolUtf8)cpoolentry;
            if (cpoolutf8.string == s1)
            {
                return cpoolutf8;
            }
        }

        if (locked)
        {
            throw new Error((new StringBuilder()).append("adding new Utf8 entry to locked contant pool: ").append(s1).toString());
        } else
        {
            return new CpoolUtf8(this, i, s1);
        }
    }

    CpoolValue1 addValue1(int i, int j)
    {
        int k = CpoolValue1.hashCode(j);
        if (hashTab == null)
        {
            rehash();
        }
        int l = (0x7fffffff & k) % hashTab.length;
        for (CpoolEntry cpoolentry = hashTab[l]; cpoolentry != null; cpoolentry = cpoolentry.next)
        {
            if (k != cpoolentry.hash || !(cpoolentry instanceof CpoolValue1))
            {
                continue;
            }
            CpoolValue1 cpoolvalue1 = (CpoolValue1)cpoolentry;
            if (cpoolvalue1.tag == i && cpoolvalue1.value == j)
            {
                return cpoolvalue1;
            }
        }

        return new CpoolValue1(this, i, k, j);
    }

    CpoolValue2 addValue2(int i, long l)
    {
        int j = CpoolValue2.hashCode(l);
        if (hashTab == null)
        {
            rehash();
        }
        int k = (0x7fffffff & j) % hashTab.length;
        for (CpoolEntry cpoolentry = hashTab[k]; cpoolentry != null; cpoolentry = cpoolentry.next)
        {
            if (j != cpoolentry.hash || !(cpoolentry instanceof CpoolValue2))
            {
                continue;
            }
            CpoolValue2 cpoolvalue2 = (CpoolValue2)cpoolentry;
            if (cpoolvalue2.tag == i && cpoolvalue2.value == l)
            {
                return cpoolvalue2;
            }
        }

        return new CpoolValue2(this, i, j, l);
    }

    public final int getCount()
    {
        return count;
    }

    CpoolEntry getForced(int i, int j)
    {
        int k;
        Object obj;
        k = i & 0xffff;
        obj = pool[k];
        if (obj != null) goto _L2; else goto _L1
_L1:
        if (locked)
        {
            throw new Error("adding new entry to locked contant pool");
        }
        j;
        JVM INSTR tableswitch 1 12: default 100
    //                   1 117
    //                   2 100
    //                   3 129
    //                   4 129
    //                   5 142
    //                   6 142
    //                   7 155
    //                   8 167
    //                   9 179
    //                   10 179
    //                   11 179
    //                   12 192;
           goto _L3 _L4 _L3 _L5 _L5 _L6 _L6 _L7 _L8 _L9 _L9 _L9 _L10
_L3:
        pool[k] = ((CpoolEntry) (obj));
        obj.index = k;
_L12:
        return ((CpoolEntry) (obj));
_L4:
        obj = new CpoolUtf8();
        continue; /* Loop/switch isn't completed */
_L5:
        obj = new CpoolValue1(j);
        continue; /* Loop/switch isn't completed */
_L6:
        obj = new CpoolValue2(j);
        continue; /* Loop/switch isn't completed */
_L7:
        obj = new CpoolClass();
        continue; /* Loop/switch isn't completed */
_L8:
        obj = new CpoolString();
        continue; /* Loop/switch isn't completed */
_L9:
        obj = new CpoolRef(j);
        continue; /* Loop/switch isn't completed */
_L10:
        obj = new CpoolNameAndType();
        continue; /* Loop/switch isn't completed */
_L2:
        if (((CpoolEntry) (obj)).getTag() == j) goto _L12; else goto _L11
_L11:
        throw new ClassFormatError((new StringBuilder()).append("conflicting constant pool tags at ").append(k).toString());
        if (true) goto _L3; else goto _L13
_L13:
    }

    CpoolClass getForcedClass(int i)
    {
        return (CpoolClass)getForced(i, 7);
    }

    public final CpoolEntry getPoolEntry(int i)
    {
        return pool[i];
    }

    void rehash()
    {
        if (hashTab == null && count > 0)
        {
            int k = pool.length;
            do
            {
                if (--k < 0)
                {
                    break;
                }
                CpoolEntry cpoolentry1 = pool[k];
                if (cpoolentry1 != null)
                {
                    cpoolentry1.hashCode();
                }
            } while (true);
        }
        int i;
        if (count < 5)
        {
            i = 101;
        } else
        {
            i = 2 * count;
        }
        hashTab = new CpoolEntry[i];
        if (pool != null)
        {
            int j = pool.length;
            do
            {
                if (--j < 0)
                {
                    break;
                }
                CpoolEntry cpoolentry = pool[j];
                if (cpoolentry != null)
                {
                    cpoolentry.add_hashed(this);
                }
            } while (true);
        }
    }

    void write(DataOutputStream dataoutputstream)
        throws IOException
    {
        dataoutputstream.writeShort(1 + count);
        for (int i = 1; i <= count; i++)
        {
            CpoolEntry cpoolentry = pool[i];
            if (cpoolentry != null)
            {
                cpoolentry.write(dataoutputstream);
            }
        }

        locked = true;
    }
}
