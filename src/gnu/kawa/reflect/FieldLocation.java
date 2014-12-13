// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.WrappedException;
import java.lang.reflect.Field;
import kawa.lang.Syntax;

// Referenced classes of package gnu.kawa.reflect:
//            ClassMemberLocation

public class FieldLocation extends ClassMemberLocation
{

    static final int CONSTANT = 4;
    static final int INDIRECT_LOCATION = 2;
    public static final int KIND_FLAGS_SET = 64;
    public static final int PROCEDURE = 16;
    static final int SETUP_DONE = 1;
    public static final int SYNTAX = 32;
    static final int VALUE_SET = 8;
    Declaration decl;
    private int flags;
    Object value;

    public FieldLocation(Object obj, ClassType classtype, String s)
    {
        super(obj, classtype, s);
    }

    public FieldLocation(Object obj, String s, String s1)
    {
        super(obj, ClassType.make(s), s1);
    }

    public FieldLocation(Object obj, Field field)
    {
        super(obj, field);
        type = (ClassType)Type.make(field.getDeclaringClass());
    }

    private Object getFieldValue()
    {
        super.setup();
        Object obj;
        try
        {
            obj = rfield.get(instance);
        }
        catch (Throwable throwable)
        {
            throw WrappedException.wrapIfNeeded(throwable);
        }
        return obj;
    }

    public static FieldLocation make(Object obj, Declaration declaration)
    {
        gnu.bytecode.Field field = declaration.field;
        FieldLocation fieldlocation = new FieldLocation(obj, field.getDeclaringClass(), field.getName());
        fieldlocation.setDeclaration(declaration);
        return fieldlocation;
    }

    public static FieldLocation make(Object obj, String s, String s1)
    {
        return new FieldLocation(obj, ClassType.make(s), s1);
    }

    public Object get(Object obj)
    {
        Object obj1;
        try
        {
            setup();
        }
        catch (Throwable throwable)
        {
            return obj;
        }
        if ((8 & flags) == 0) goto _L2; else goto _L1
_L1:
        obj1 = value;
        if ((4 & flags) == 0) goto _L4; else goto _L3
_L3:
        return obj1;
_L2:
        obj1 = getFieldValue();
        if ((0x10 & type.getDeclaredField(mname).getModifiers()) != 0)
        {
            flags = 8 | flags;
            if ((2 & flags) == 0)
            {
                flags = 4 | flags;
            }
            value = obj1;
        }
_L4:
        if ((2 & flags) != 0)
        {
            String s = Location.UNBOUND;
            Location location = (Location)obj1;
            obj1 = location.get(s);
            if (obj1 == s)
            {
                return obj;
            }
            if (location.isConstant())
            {
                flags = 4 | flags;
                value = obj1;
                return obj1;
            }
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public Declaration getDeclaration()
    {
        this;
        JVM INSTR monitorenter ;
        Declaration declaration;
        if ((0x40 & flags) == 0)
        {
            setKindFlags();
        }
        declaration = decl;
        if (declaration != null) goto _L2; else goto _L1
_L1:
        String s;
        ClassType classtype;
        gnu.bytecode.Field field;
        s = getMemberName();
        classtype = getDeclaringClass();
        field = classtype.getDeclaredField(s);
        if (field != null) goto _L4; else goto _L3
_L3:
        Declaration declaration1 = null;
_L7:
        this;
        JVM INSTR monitorexit ;
        return declaration1;
_L4:
        declaration = ModuleInfo.find(classtype).getModuleExp().firstDecl();
_L5:
        if (declaration == null)
        {
            break MISSING_BLOCK_LABEL_94;
        }
        if (declaration.field == null || !declaration.field.getName().equals(s))
        {
            break MISSING_BLOCK_LABEL_130;
        }
        if (declaration != null)
        {
            break MISSING_BLOCK_LABEL_138;
        }
        throw new RuntimeException((new StringBuilder()).append("no field found for ").append(this).toString());
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        declaration = declaration.nextDecl();
          goto _L5
        decl = declaration;
_L2:
        declaration1 = declaration;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public Type getFType()
    {
        return type.getDeclaredField(mname).getType();
    }

    public gnu.bytecode.Field getField()
    {
        return type.getDeclaredField(mname);
    }

    public boolean isBound()
    {
        if ((0x40 & flags) == 0)
        {
            setKindFlags();
        }
        if ((4 & flags) != 0 || (2 & flags) == 0)
        {
            return true;
        }
        Object obj;
        if ((8 & flags) != 0)
        {
            obj = value;
        } else
        {
            try
            {
                setup();
            }
            catch (Throwable throwable)
            {
                return false;
            }
            obj = getFieldValue();
            flags = 8 | flags;
            value = obj;
        }
        return ((Location)obj).isBound();
    }

    public boolean isConstant()
    {
        if ((0x40 & flags) == 0)
        {
            setKindFlags();
        }
        boolean flag1;
        if ((4 & flags) != 0)
        {
            flag1 = true;
        } else
        {
            boolean flag = isIndirectLocation();
            flag1 = false;
            if (flag)
            {
                Object obj;
                if ((8 & flags) != 0)
                {
                    obj = value;
                } else
                {
                    try
                    {
                        setup();
                    }
                    catch (Throwable throwable)
                    {
                        return false;
                    }
                    obj = getFieldValue();
                    flags = 8 | flags;
                    value = obj;
                }
                return ((Location)obj).isConstant();
            }
        }
        return flag1;
    }

    public boolean isIndirectLocation()
    {
        return (2 & flags) != 0;
    }

    public boolean isProcedureOrSyntax()
    {
        if ((0x40 & flags) == 0)
        {
            setKindFlags();
        }
        return (0x30 & flags) != 0;
    }

    public void set(Object obj)
    {
        setup();
        if ((2 & flags) == 0)
        {
            try
            {
                rfield.set(instance, obj);
                return;
            }
            catch (Throwable throwable)
            {
                throw WrappedException.wrapIfNeeded(throwable);
            }
        }
        Object obj1;
        if ((8 & flags) != 0)
        {
            obj1 = value;
        } else
        {
            flags = 8 | flags;
            obj1 = getFieldValue();
            value = obj1;
        }
        ((Location)obj1).set(obj);
    }

    public void setDeclaration(Declaration declaration)
    {
        decl = declaration;
    }

    void setKindFlags()
    {
        int i;
        Type type;
        String s = getMemberName();
        gnu.bytecode.Field field = getDeclaringClass().getDeclaredField(s);
        i = field.getModifiers();
        type = field.getType();
        if (type.isSubtype(Compilation.typeLocation))
        {
            flags = 2 | flags;
        }
        if ((i & 0x10) == 0) goto _L2; else goto _L1
_L1:
        if ((2 & flags) != 0) goto _L4; else goto _L3
_L3:
        flags = 4 | flags;
        if (type.isSubtype(Compilation.typeProcedure))
        {
            flags = 0x10 | flags;
        }
        if ((type instanceof ClassType) && ((ClassType)type).isSubclass("kawa.lang.Syntax"))
        {
            flags = 0x20 | flags;
        }
_L2:
        flags = 0x40 | flags;
        return;
_L4:
        Location location = (Location)getFieldValue();
        if (location instanceof FieldLocation)
        {
            FieldLocation fieldlocation = (FieldLocation)location;
            if ((0x40 & fieldlocation.flags) == 0)
            {
                fieldlocation.setKindFlags();
            }
            flags = flags | 0x34 & fieldlocation.flags;
            if ((4 & fieldlocation.flags) != 0)
            {
                if ((8 & fieldlocation.flags) != 0)
                {
                    value = fieldlocation.value;
                    flags = 8 | flags;
                }
            } else
            {
                value = fieldlocation;
                flags = 8 | flags;
            }
        } else
        if (location.isConstant())
        {
            Object obj = location.get(null);
            if (obj instanceof Procedure)
            {
                flags = 0x10 | flags;
            }
            if (obj instanceof Syntax)
            {
                flags = 0x20 | flags;
            }
            flags = 0xc | flags;
            value = obj;
        }
        if (true) goto _L2; else goto _L5
_L5:
    }

    public void setProcedure()
    {
        flags = 0x54 | flags;
    }

    public void setRestore(Object obj)
    {
        if ((2 & flags) == 0)
        {
            super.setRestore(obj);
            return;
        } else
        {
            ((Location)value).setRestore(obj);
            return;
        }
    }

    public void setSyntax()
    {
        flags = 0x64 | flags;
    }

    public Object setWithSave(Object obj)
    {
        if ((0x40 & flags) == 0)
        {
            setKindFlags();
        }
        if ((2 & flags) == 0)
        {
            return super.setWithSave(obj);
        }
        Object obj1;
        if ((8 & flags) != 0)
        {
            obj1 = value;
        } else
        {
            flags = 8 | flags;
            obj1 = getFieldValue();
            value = obj1;
        }
        return ((Location)obj1).setWithSave(obj);
    }

    void setup()
    {
        this;
        JVM INSTR monitorenter ;
        if ((1 & flags) == 0)
        {
            break MISSING_BLOCK_LABEL_14;
        }
        this;
        JVM INSTR monitorexit ;
        return;
        super.setup();
        if ((0x40 & flags) == 0)
        {
            setKindFlags();
        }
        flags = 1 | flags;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append("FieldLocation[");
        if (instance != null)
        {
            stringbuffer.append(instance);
            stringbuffer.append(' ');
        }
        String s;
        if (type == null)
        {
            s = "(null)";
        } else
        {
            s = type.getName();
        }
        stringbuffer.append(s);
        stringbuffer.append('.');
        stringbuffer.append(mname);
        stringbuffer.append(']');
        return stringbuffer.toString();
    }
}
