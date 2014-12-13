// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.lists.ItemPredicate;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Referenced classes of package gnu.kawa.reflect:
//            SingletonType, InstanceOf

public class OccurrenceType extends ObjectType
    implements Externalizable, TypeValue
{

    public static final Type emptySequenceType;
    static final Method isInstanceMethod;
    public static final ClassType typeOccurrenceType;
    Type base;
    int maxOccurs;
    int minOccurs;

    public OccurrenceType(Type type, int i, int j)
    {
        base = type;
        minOccurs = i;
        maxOccurs = j;
    }

    public static Type getInstance(Type type, int i, int j)
    {
        if (i == 1 && j == 1)
        {
            return type;
        }
        if (i == 0 && j < 0 && (type == SingletonType.instance || type == Type.pointer_type))
        {
            return Type.pointer_type;
        } else
        {
            return new OccurrenceType(type, i, j);
        }
    }

    public static char itemCountCode(Type type)
    {
        int i = itemCountRange(type);
        int j = i & 0xfff;
        int k = i >> 12;
        if (k == 0)
        {
            return '0';
        }
        if (j == 0)
        {
            return k != 1 ? '*' : '?';
        }
        return j != 1 || k != 1 ? '+' : '1';
    }

    public static boolean itemCountIsOne(Type type)
    {
        return itemCountRange(type) == 4097;
    }

    public static boolean itemCountIsZeroOrOne(Type type)
    {
        return itemCountRange(type) >> 13 == 0;
    }

    public static int itemCountRange(Type type)
    {
        if (!(type instanceof SingletonType))
        {
            if (type instanceof OccurrenceType)
            {
                OccurrenceType occurrencetype = (OccurrenceType)type;
                int j = occurrencetype.minOccurs();
                int k = occurrencetype.maxOccurs();
                int l = itemCountRange(occurrencetype.getBase());
                if (j == 1 && k == 1 || l == 0)
                {
                    return l;
                }
                if (k > 0xfffff)
                {
                    k = -1;
                }
                if (k == 0)
                {
                    return 0;
                }
                int i1 = l & 0xfff;
                int j1 = l >> 12;
                if (l != 4097)
                {
                    if (j > 4095)
                    {
                        j = 4095;
                    }
                    j *= i1;
                    if (j > 4095)
                    {
                        j = 4095;
                    }
                    if (k < 0 || j1 < 0)
                    {
                        k = -1;
                    } else
                    {
                        k *= j1;
                    }
                    if (k > 0xfffff)
                    {
                        k = -1;
                    }
                }
                return j | k << 12;
            }
            if (type instanceof PrimType)
            {
                boolean flag = type.isVoid();
                int i = 0;
                if (!flag)
                {
                    i = 4097;
                }
                return i;
            }
            if (!(type instanceof ArrayType) && (!(type instanceof ObjectType) || type.compare(Compilation.typeValues) != -3))
            {
                return -4096;
            }
        }
        return 4097;
    }

    public static Type itemPrimeType(Type type)
    {
        for (; type instanceof OccurrenceType; type = ((OccurrenceType)type).getBase()) { }
        if (itemCountIsOne(type))
        {
            return type;
        } else
        {
            return SingletonType.instance;
        }
    }

    public Object coerceFromObject(Object obj)
    {
        if ((obj instanceof Values) || minOccurs > 1 || maxOccurs == 0)
        {
            if (!isInstance(obj))
            {
                throw new ClassCastException();
            }
        } else
        {
            obj = base.coerceFromObject(obj);
        }
        return obj;
    }

    public int compare(Type type)
    {
        if (type instanceof OccurrenceType)
        {
            OccurrenceType occurrencetype = (OccurrenceType)type;
            if (minOccurs == occurrencetype.minOccurs && maxOccurs == occurrencetype.maxOccurs)
            {
                return base.compare(occurrencetype.getBase());
            }
        }
        return -2;
    }

    public Expression convertValue(Expression expression)
    {
        return null;
    }

    public void emitIsInstance(Variable variable, Compilation compilation, Target target)
    {
        InstanceOf.emitIsInstance(this, variable, compilation, target);
    }

    public void emitTestIf(Variable variable, Declaration declaration, Compilation compilation)
    {
        CodeAttr codeattr = compilation.getCode();
        if (variable != null)
        {
            codeattr.emitLoad(variable);
        }
        if (declaration != null)
        {
            codeattr.emitDup();
            declaration.compileStore(compilation);
        }
        compilation.compileConstant(this);
        codeattr.emitSwap();
        codeattr.emitInvokeVirtual(isInstanceMethod);
        codeattr.emitIfIntNotZero();
    }

    public Type getBase()
    {
        return base;
    }

    public Procedure getConstructor()
    {
        return null;
    }

    public Type getImplementationType()
    {
        return Type.pointer_type;
    }

    public boolean isInstance(Object obj)
    {
        boolean flag = true;
        if (!(obj instanceof Values)) goto _L2; else goto _L1
_L1:
        Values values;
        int k;
        boolean flag2;
        int l;
        values = (Values)obj;
        k = values.startPos();
        flag2 = base instanceof ItemPredicate;
        l = 0;
        if (!flag2) goto _L4; else goto _L3
_L3:
        ItemPredicate itempredicate = (ItemPredicate)base;
_L14:
        boolean flag3;
        flag3 = itempredicate.isInstancePos(values, k);
        k = values.nextPos(k);
        if (k != 0) goto _L6; else goto _L5
_L5:
        boolean flag1;
        if (l < minOccurs || maxOccurs >= 0 && l > maxOccurs)
        {
            flag = false;
        }
        flag1 = flag;
_L8:
        return flag1;
_L6:
        flag1 = false;
        if (!flag3) goto _L8; else goto _L7
_L7:
        l++;
        continue; /* Loop/switch isn't completed */
_L10:
        l++;
_L4:
        Object obj1;
        k = values.nextPos(k);
        if (k == 0)
        {
            if (l < minOccurs || maxOccurs >= 0 && l > maxOccurs)
            {
                flag = false;
            }
            return flag;
        }
        obj1 = values.getPosPrevious(k);
        if (base.isInstance(obj1)) goto _L10; else goto _L9
_L9:
        return false;
_L2:
        int i;
        i = minOccurs;
        flag1 = false;
        if (i > flag) goto _L8; else goto _L11
_L11:
        int j;
        j = maxOccurs;
        flag1 = false;
        if (j == 0) goto _L8; else goto _L12
_L12:
        return base.isInstance(obj);
        if (true) goto _L14; else goto _L13
_L13:
    }

    public int maxOccurs()
    {
        return maxOccurs;
    }

    public int minOccurs()
    {
        return minOccurs;
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        base = (Type)objectinput.readObject();
        minOccurs = objectinput.readInt();
        maxOccurs = objectinput.readInt();
    }

    public String toString()
    {
        String s = base.toString();
        boolean flag;
        StringBuffer stringbuffer;
        if (s == null || s.indexOf(' ') >= 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        stringbuffer = new StringBuffer();
        if (flag)
        {
            stringbuffer.append('(');
        }
        stringbuffer.append(s);
        if (flag)
        {
            stringbuffer.append(')');
        }
        if (minOccurs != 1 || maxOccurs != 1)
        {
            if (minOccurs == 0 && maxOccurs == 1)
            {
                stringbuffer.append('?');
            } else
            if (minOccurs == 1 && maxOccurs == -1)
            {
                stringbuffer.append('+');
            } else
            if (minOccurs == 0 && maxOccurs == -1)
            {
                stringbuffer.append('*');
            } else
            {
                stringbuffer.append('{');
                stringbuffer.append(minOccurs);
                stringbuffer.append(',');
                if (maxOccurs >= 0)
                {
                    stringbuffer.append(maxOccurs);
                } else
                {
                    stringbuffer.append('*');
                }
                stringbuffer.append('}');
            }
        }
        return stringbuffer.toString();
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(base);
        objectoutput.writeInt(minOccurs);
        objectoutput.writeInt(maxOccurs);
    }

    static 
    {
        emptySequenceType = getInstance(SingletonType.instance, 0, 0);
        typeOccurrenceType = ClassType.make("gnu.kawa.reflect.OccurrenceType");
        isInstanceMethod = typeOccurrenceType.getDeclaredMethod("isInstance", 1);
    }
}
