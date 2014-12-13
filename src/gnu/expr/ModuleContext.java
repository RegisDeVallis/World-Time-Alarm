// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.kawa.util.AbstractWeakHashTable;
import gnu.mapping.WrappedException;
import java.lang.reflect.Field;

// Referenced classes of package gnu.expr:
//            ModuleManager, ModuleInfo

public class ModuleContext
{
    static class ClassToInstanceMap extends AbstractWeakHashTable
    {

        protected Class getKeyFromValue(Object obj)
        {
            return obj.getClass();
        }

        protected volatile Object getKeyFromValue(Object obj)
        {
            return getKeyFromValue(obj);
        }

        protected boolean matches(Class class1, Class class2)
        {
            return class1 == class2;
        }

        ClassToInstanceMap()
        {
        }
    }


    public static int IN_HTTP_SERVER = 1;
    public static int IN_SERVLET = 2;
    static ModuleContext global;
    int flags;
    ModuleManager manager;
    private ClassToInstanceMap table;

    public ModuleContext(ModuleManager modulemanager)
    {
        table = new ClassToInstanceMap();
        manager = modulemanager;
    }

    public static ModuleContext getContext()
    {
        return global;
    }

    public void addFlags(int i)
    {
        flags = i | flags;
    }

    public void clear()
    {
        this;
        JVM INSTR monitorenter ;
        table.clear();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public ModuleInfo findFromInstance(Object obj)
    {
        Class class1 = obj.getClass();
        this;
        JVM INSTR monitorenter ;
        ModuleInfo moduleinfo;
        manager;
        moduleinfo = ModuleManager.findWithClass(class1);
        setInstance(obj);
        this;
        JVM INSTR monitorexit ;
        return moduleinfo;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Object findInstance(ModuleInfo moduleinfo)
    {
        this;
        JVM INSTR monitorenter ;
        Class class1 = moduleinfo.getModuleClass();
        Object obj = findInstance(class1);
        this;
        JVM INSTR monitorexit ;
        return obj;
        ClassNotFoundException classnotfoundexception;
        classnotfoundexception;
        String s = moduleinfo.getClassName();
        throw new WrappedException((new StringBuilder()).append("cannot find module ").append(s).toString(), classnotfoundexception);
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Object findInstance(Class class1)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = table.get(class1);
        Object obj1 = obj;
        if (obj1 != null) goto _L2; else goto _L1
_L1:
        Object obj3 = class1.getDeclaredField("$instance").get(null);
        obj1 = obj3;
_L3:
        setInstance(obj1);
_L2:
        this;
        JVM INSTR monitorexit ;
        return obj1;
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
        Object obj2 = class1.newInstance();
        obj1 = obj2;
          goto _L3
        Throwable throwable;
        throwable;
        throw new WrappedException((new StringBuilder()).append("exception while initializing module ").append(class1.getName()).toString(), throwable);
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getFlags()
    {
        return flags;
    }

    public ModuleManager getManager()
    {
        return manager;
    }

    public Object searchInstance(Class class1)
    {
        this;
        JVM INSTR monitorenter ;
        Object obj = table.get(class1);
        this;
        JVM INSTR monitorexit ;
        return obj;
        Exception exception;
        exception;
        throw exception;
    }

    public void setFlags(int i)
    {
        flags = i;
    }

    public void setInstance(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        table.put(obj.getClass(), obj);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    static 
    {
        global = new ModuleContext(ModuleManager.instance);
    }
}
