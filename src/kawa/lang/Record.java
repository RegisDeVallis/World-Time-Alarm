// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrappedException;
import java.io.PrintWriter;
import java.util.Vector;

// Referenced classes of package kawa.lang:
//            GenericError

public class Record
{

    public Record()
    {
    }

    static java.lang.reflect.Field getField(Class class1, String s)
        throws NoSuchFieldException
    {
        Field field = ((ClassType)Type.make(class1)).getFields();
_L3:
        if (field == null)
        {
            break; /* Loop/switch isn't completed */
        }
          goto _L1
_L5:
        field = field.getNext();
        if (true) goto _L3; else goto _L2
_L1:
        if ((9 & field.getModifiers()) != 1 || !field.getSourceName().equals(s)) goto _L5; else goto _L4
_L4:
        return field.getReflectField();
_L2:
        throw new NoSuchFieldException();
    }

    public static boolean isRecord(Object obj)
    {
        return obj instanceof Record;
    }

    public static ClassType makeRecordType(String s, LList llist)
    {
        ClassType classtype = ClassType.make("kawa.lang.Record");
        String s1 = Compilation.mangleNameIfNeeded(s);
        ClassType classtype1 = new ClassType(s1);
        classtype1.setSuper(classtype);
        classtype1.setModifiers(33);
        Method method = classtype1.addMethod("<init>", Type.typeArray0, Type.voidType, 1);
        Method method1 = classtype.addMethod("<init>", Type.typeArray0, Type.voidType, 1);
        CodeAttr codeattr = method.startCode();
        codeattr.emitPushThis();
        codeattr.emitInvokeSpecial(method1);
        codeattr.emitReturn();
        if (!s.equals(s1))
        {
            CodeAttr codeattr1 = classtype1.addMethod("getTypeName", Type.typeArray0, Compilation.typeString, 1).startCode();
            codeattr1.emitPushString(s);
            codeattr1.emitReturn();
        }
        do
        {
            LList llist1 = LList.Empty;
            if (llist == llist1)
            {
                break;
            }
            Pair pair = (Pair)llist;
            String s2 = pair.getCar().toString();
            classtype1.addField(Compilation.mangleNameIfNeeded(s2), Type.pointer_type, 1).setSourceName(s2.intern());
            llist = (LList)pair.getCdr();
        } while (true);
        byte abyte0[][] = new byte[1][];
        String as[] = {
            s1
        };
        abyte0[0] = classtype1.writeToArray();
        ArrayClassLoader arrayclassloader = new ArrayClassLoader(as, abyte0);
        try
        {
            Type.registerTypeForClass(arrayclassloader.loadClass(s1), classtype1);
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            throw new InternalError(classnotfoundexception.toString());
        }
        return classtype1;
    }

    public static Object set1(Object obj, String s, Object obj1)
    {
        Class class1 = obj.getClass();
        Object obj2;
        try
        {
            java.lang.reflect.Field field = getField(class1, s);
            obj2 = field.get(obj);
            field.set(obj, obj1);
        }
        catch (NoSuchFieldException nosuchfieldexception)
        {
            throw new GenericError((new StringBuilder()).append("no such field ").append(s).append(" in ").append(class1.getName()).toString());
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new GenericError((new StringBuilder()).append("illegal access for field ").append(s).toString());
        }
        return obj2;
    }

    public static LList typeFieldNames(ClassType classtype)
    {
        return typeFieldNames(classtype.getReflectClass());
    }

    public static LList typeFieldNames(Class class1)
    {
        LList llist = LList.Empty;
        Field field = ((ClassType)Type.make(class1)).getFields();
        Vector vector = new Vector(100);
        for (; field != null; field = field.getNext())
        {
            if ((9 & field.getModifiers()) == 1)
            {
                vector.addElement(SimpleSymbol.valueOf(field.getSourceName()));
            }
        }

        int i = vector.size();
        Object obj;
        for (obj = llist; --i >= 0; obj = new Pair(vector.elementAt(i), obj)) { }
        return ((LList) (obj));
    }

    public boolean equals(Object obj)
    {
        if (this != obj)
        {
            Class class1 = getClass();
            if (obj == null || obj.getClass() != class1)
            {
                return false;
            }
            Field field = ((ClassType)Type.make(class1)).getFields();
            while (field != null) 
            {
                if ((9 & field.getModifiers()) == 1)
                {
                    Object obj1;
                    Object obj2;
                    try
                    {
                        java.lang.reflect.Field field1 = field.getReflectField();
                        obj1 = field1.get(this);
                        obj2 = field1.get(obj);
                    }
                    catch (Exception exception)
                    {
                        throw new WrappedException(exception);
                    }
                    if (!obj1.equals(obj2))
                    {
                        return false;
                    }
                }
                field = field.getNext();
            }
        }
        return true;
    }

    public Object get(String s, Object obj)
    {
        Class class1 = getClass();
        Object obj1;
        try
        {
            obj1 = getField(class1, s).get(this);
        }
        catch (NoSuchFieldException nosuchfieldexception)
        {
            throw new GenericError((new StringBuilder()).append("no such field ").append(s).append(" in ").append(class1.getName()).toString());
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new GenericError((new StringBuilder()).append("illegal access for field ").append(s).toString());
        }
        return obj1;
    }

    public String getTypeName()
    {
        return getClass().getName();
    }

    public int hashCode()
    {
        java.lang.reflect.Field afield[];
        int i;
        int j;
        afield = getClass().getFields();
        i = 12345;
        j = 0;
_L2:
        java.lang.reflect.Field field;
        if (j >= afield.length)
        {
            break; /* Loop/switch isn't completed */
        }
        field = afield[j];
        Object obj = field.get(this);
        if (obj != null)
        {
            i ^= obj.hashCode();
        }
_L3:
        j++;
        if (true) goto _L2; else goto _L1
        IllegalAccessException illegalaccessexception;
        illegalaccessexception;
          goto _L3
_L1:
        return i;
    }

    public void print(PrintWriter printwriter)
    {
        printwriter.print(toString());
    }

    public Object put(String s, Object obj)
    {
        return set1(this, s, obj);
    }

    public String toString()
    {
        StringBuffer stringbuffer;
        Field field;
        stringbuffer = new StringBuffer(200);
        stringbuffer.append("#<");
        stringbuffer.append(getTypeName());
        field = ((ClassType)Type.make(getClass())).getFields();
_L2:
        if (field == null)
        {
            break MISSING_BLOCK_LABEL_130;
        }
        if ((9 & field.getModifiers()) == 1)
        {
            break; /* Loop/switch isn't completed */
        }
_L3:
        field = field.getNext();
        if (true) goto _L2; else goto _L1
_L1:
        Object obj1 = field.getReflectField().get(this);
        Object obj = obj1;
_L4:
        stringbuffer.append(' ');
        stringbuffer.append(field.getSourceName());
        stringbuffer.append(": ");
        stringbuffer.append(obj);
          goto _L3
        Exception exception;
        exception;
        obj = "#<illegal-access>";
          goto _L4
        stringbuffer.append(">");
        return stringbuffer.toString();
    }
}
