// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.lists.Consumer;
import gnu.mapping.Symbol;
import gnu.text.Printable;
import gnu.text.ReportFormat;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Referenced classes of package kawa.lang:
//            Pattern

public class EqualPat extends Pattern
    implements Printable, Externalizable
{

    Object value;

    public EqualPat()
    {
    }

    public EqualPat(Object obj)
    {
        value = obj;
    }

    public static EqualPat make(Object obj)
    {
        return new EqualPat(obj);
    }

    public boolean match(Object obj, Object aobj[], int i)
    {
        if ((value instanceof String) && (obj instanceof Symbol))
        {
            obj = ((Symbol)obj).getName();
        }
        return value.equals(obj);
    }

    public void print(Consumer consumer)
    {
        consumer.write("#<equals: ");
        ReportFormat.print(value, consumer);
        consumer.write(62);
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        value = objectinput.readObject();
    }

    public int varCount()
    {
        return 0;
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(value);
    }
}
