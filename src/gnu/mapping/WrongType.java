// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;

import gnu.bytecode.Type;

// Referenced classes of package gnu.mapping:
//            WrappedException, Procedure, MethodProc

public class WrongType extends WrappedException
{

    public static final int ARG_CAST = -4;
    public static final int ARG_DESCRIPTION = -3;
    public static final int ARG_UNKNOWN = -1;
    public static final int ARG_VARNAME = -2;
    public Object argValue;
    public Object expectedType;
    public int number;
    public Procedure proc;
    public String procname;

    public WrongType(int i, Object obj, Type type)
    {
        number = i;
        argValue = obj;
        expectedType = type;
    }

    public WrongType(Procedure procedure, int i, ClassCastException classcastexception)
    {
        super(classcastexception);
        proc = procedure;
        procname = procedure.getName();
        number = i;
    }

    public WrongType(Procedure procedure, int i, Object obj)
    {
        proc = procedure;
        procname = procedure.getName();
        number = i;
        argValue = obj;
    }

    public WrongType(Procedure procedure, int i, Object obj, Type type)
    {
        proc = procedure;
        procname = procedure.getName();
        number = i;
        argValue = obj;
        expectedType = type;
    }

    public WrongType(Procedure procedure, int i, Object obj, String s)
    {
        this(procedure.getName(), i, obj, s);
        proc = procedure;
    }

    public WrongType(ClassCastException classcastexception, Procedure procedure, int i, Object obj)
    {
        this(procedure, i, classcastexception);
        argValue = obj;
    }

    public WrongType(ClassCastException classcastexception, String s, int i, Object obj)
    {
        this(s, i, classcastexception);
        argValue = obj;
    }

    public WrongType(String s, int i, ClassCastException classcastexception)
    {
        super(classcastexception);
        procname = s;
        number = i;
    }

    public WrongType(String s, int i, Object obj, String s1)
    {
        procname = s;
        number = i;
        argValue = obj;
        expectedType = s1;
    }

    public WrongType(String s, int i, String s1)
    {
        super(null, null);
        procname = s;
        number = i;
        expectedType = s1;
    }

    public static WrongType make(ClassCastException classcastexception, Procedure procedure, int i)
    {
        return new WrongType(procedure, i, classcastexception);
    }

    public static WrongType make(ClassCastException classcastexception, Procedure procedure, int i, Object obj)
    {
        WrongType wrongtype = new WrongType(procedure, i, classcastexception);
        wrongtype.argValue = obj;
        return wrongtype;
    }

    public static WrongType make(ClassCastException classcastexception, String s, int i)
    {
        return new WrongType(s, i, classcastexception);
    }

    public static WrongType make(ClassCastException classcastexception, String s, int i, Object obj)
    {
        WrongType wrongtype = new WrongType(s, i, classcastexception);
        wrongtype.argValue = obj;
        return wrongtype;
    }

    public String getMessage()
    {
        StringBuffer stringbuffer = new StringBuffer(100);
        Object obj;
        if (number == -3)
        {
            stringbuffer.append(procname);
        } else
        if (number == -4 || number == -2)
        {
            stringbuffer.append("Value");
        } else
        {
            stringbuffer.append("Argument ");
            if (number > 0)
            {
                stringbuffer.append('#');
                stringbuffer.append(number);
            }
        }
        if (argValue != null)
        {
            stringbuffer.append(" (");
            String s2 = argValue.toString();
            Throwable throwable;
            String s;
            if (s2.length() > 50)
            {
                stringbuffer.append(s2.substring(0, 47));
                stringbuffer.append("...");
            } else
            {
                stringbuffer.append(s2);
            }
            stringbuffer.append(")");
        }
        if (procname != null && number != -3)
        {
            String s1;
            if (number == -2)
            {
                s1 = " for variable '";
            } else
            {
                s1 = " to '";
            }
            stringbuffer.append(s1);
            stringbuffer.append(procname);
            stringbuffer.append("'");
        }
        stringbuffer.append(" has wrong type");
        if (argValue != null)
        {
            stringbuffer.append(" (");
            stringbuffer.append(argValue.getClass().getName());
            stringbuffer.append(")");
        }
        obj = expectedType;
        if (obj == null && number > 0 && (proc instanceof MethodProc))
        {
            obj = ((MethodProc)proc).getParameterType(-1 + number);
        }
        if (obj != null && obj != Type.pointer_type)
        {
            stringbuffer.append(" (expected: ");
            if (obj instanceof Type)
            {
                obj = ((Type)obj).getName();
            } else
            if (obj instanceof Class)
            {
                obj = ((Class)obj).getName();
            }
            stringbuffer.append(obj);
            stringbuffer.append(")");
        }
        throwable = getCause();
        if (throwable != null)
        {
            s = throwable.getMessage();
            if (s != null)
            {
                stringbuffer.append(" (");
                stringbuffer.append(s);
                stringbuffer.append(')');
            }
        }
        return stringbuffer.toString();
    }
}
