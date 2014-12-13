// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;

// Referenced classes of package kawa.lang:
//            GenericError

public class RecordConstructor extends ProcedureN
{

    Field fields[];
    ClassType type;

    public RecordConstructor(ClassType classtype)
    {
        init(classtype);
    }

    public RecordConstructor(ClassType classtype, Object obj)
    {
        type = classtype;
        if (obj != null) goto _L2; else goto _L1
_L1:
        init(classtype);
_L4:
        return;
_L2:
        int i;
        Field field;
        int j;
        i = LList.listLength(obj, false);
        fields = new Field[i];
        field = classtype.getFields();
        j = 0;
_L5:
        if (j >= i) goto _L4; else goto _L3
_L3:
        Pair pair;
        String s;
        Field field1;
        pair = (Pair)obj;
        s = pair.getCar().toString();
        field1 = field;
_L6:
label0:
        {
            if (field1 == null)
            {
                throw new RuntimeException((new StringBuilder()).append("no such field ").append(s).append(" in ").append(classtype.getName()).toString());
            }
            if (field1.getSourceName() != s)
            {
                break label0;
            }
            fields[j] = field1;
            obj = pair.getCdr();
            j++;
        }
          goto _L5
          goto _L4
        field1 = field1.getNext();
          goto _L6
    }

    public RecordConstructor(ClassType classtype, Field afield[])
    {
        type = classtype;
        fields = afield;
    }

    public RecordConstructor(Class class1)
    {
        init((ClassType)Type.make(class1));
    }

    public RecordConstructor(Class class1, Object obj)
    {
        this((ClassType)Type.make(class1), obj);
    }

    public RecordConstructor(Class class1, Field afield[])
    {
        this((ClassType)Type.make(class1), afield);
    }

    private void init(ClassType classtype)
    {
        type = classtype;
        Field field = classtype.getFields();
        int i = 0;
        for (Field field1 = field; field1 != null; field1 = field1.getNext())
        {
            if ((9 & field1.getModifiers()) == 1)
            {
                i++;
            }
        }

        fields = new Field[i];
        Field field2 = field;
        int j = 0;
        while (field2 != null) 
        {
            int k;
            if ((9 & field2.getModifiers()) == 1)
            {
                Field afield[] = fields;
                k = j + 1;
                afield[j] = field2;
            } else
            {
                k = j;
            }
            field2 = field2.getNext();
            j = k;
        }
    }

    public Object applyN(Object aobj[])
    {
        Object obj;
        try
        {
            obj = type.getReflectClass().newInstance();
        }
        catch (InstantiationException instantiationexception)
        {
            throw new GenericError(instantiationexception.toString());
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new GenericError(illegalaccessexception.toString());
        }
        if (aobj.length != fields.length)
        {
            throw new WrongArguments(this, aobj.length);
        }
        int i = 0;
        while (i < aobj.length) 
        {
            Field field = fields[i];
            try
            {
                field.getReflectField().set(obj, aobj[i]);
            }
            catch (Exception exception)
            {
                throw new WrappedException((new StringBuilder()).append("illegal access for field ").append(field.getName()).toString(), exception);
            }
            i++;
        }
        return obj;
    }

    public String getName()
    {
        return (new StringBuilder()).append(type.getName()).append(" constructor").toString();
    }

    public int numArgs()
    {
        int i = fields.length;
        return i | i << 12;
    }
}
