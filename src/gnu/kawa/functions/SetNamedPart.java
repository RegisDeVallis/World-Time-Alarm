// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.kawa.reflect.SlotSet;
import gnu.mapping.Environment;
import gnu.mapping.HasSetter;
import gnu.mapping.Namespace;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure3;
import gnu.mapping.Values;

public class SetNamedPart extends Procedure3
    implements HasSetter
{

    public static final SetNamedPart setNamedPart;

    public SetNamedPart()
    {
    }

    public Object apply3(Object obj, Object obj1, Object obj2)
    {
        Namespace namespace;
        if (obj instanceof Namespace)
        {
            namespace = (Namespace)obj;
            String s = namespace.getName();
            if (!s.startsWith("class:"))
            {
                break MISSING_BLOCK_LABEL_83;
            }
            obj = ClassType.make(s.substring(6));
        }
        if (obj instanceof Class)
        {
            obj = (ClassType)Type.make((Class)obj);
        }
        if (!(obj instanceof ClassType))
        {
            break MISSING_BLOCK_LABEL_113;
        }
        Values values;
        SlotSet.setStaticField(obj, obj1.toString(), obj2);
        values = Values.empty;
        return values;
        gnu.mapping.Symbol symbol = namespace.getSymbol(obj1.toString());
        Environment.getCurrent();
        Environment.getCurrent().put(symbol, obj2);
        return Values.empty;
        Throwable throwable;
        throwable;
        SlotSet.setField(obj, obj1.toString(), obj2);
        return Values.empty;
    }

    static 
    {
        setNamedPart = new SetNamedPart();
        setNamedPart.setName("setNamedPart");
        setNamedPart.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileNamedPart:validateSetNamedPart");
    }
}
