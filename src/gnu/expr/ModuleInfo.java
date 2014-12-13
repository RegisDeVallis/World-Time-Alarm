// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.kawa.reflect.FieldLocation;
import gnu.kawa.util.AbstractWeakHashTable;
import gnu.mapping.Location;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

// Referenced classes of package gnu.expr:
//            ModuleManager, ModuleContext, Declaration, ReferenceExp, 
//            ModuleExp, Compilation, Language, QuoteExp

public class ModuleInfo
{
    static class ClassToInfoMap extends AbstractWeakHashTable
    {

        protected Class getKeyFromValue(ModuleInfo moduleinfo)
        {
            return moduleinfo.moduleClass;
        }

        protected volatile Object getKeyFromValue(Object obj)
        {
            return getKeyFromValue((ModuleInfo)obj);
        }

        protected boolean matches(Class class1, Class class2)
        {
            return class1 == class2;
        }

        ClassToInfoMap()
        {
        }
    }


    static ClassToInfoMap mapClassToInfo = new ClassToInfoMap();
    private String className;
    Compilation comp;
    ModuleInfo dependencies[];
    ModuleExp exp;
    public long lastCheckedTime;
    public long lastModifiedTime;
    Class moduleClass;
    int numDependencies;
    Path sourceAbsPath;
    String sourceAbsPathname;
    public String sourcePath;
    String uri;

    public ModuleInfo()
    {
    }

    public static Path absPath(String s)
    {
        return Path.valueOf(s).getCanonical();
    }

    public static ModuleInfo find(ClassType classtype)
    {
        if (!classtype.isExisting())
        {
            break MISSING_BLOCK_LABEL_18;
        }
        ModuleInfo moduleinfo = ModuleManager.findWithClass(classtype.getReflectClass());
        return moduleinfo;
        Exception exception;
        exception;
        return ModuleManager.getInstance().findWithClassName(classtype.getName());
    }

    public static ModuleInfo findFromInstance(Object obj)
    {
        return ModuleContext.getContext().findFromInstance(obj);
    }

    static void makeDeclInModule2(ModuleExp moduleexp, Declaration declaration)
    {
        Object obj = declaration.getConstantValue();
        if (!(obj instanceof FieldLocation)) goto _L2; else goto _L1
_L1:
        FieldLocation fieldlocation;
        ReferenceExp referenceexp;
        fieldlocation = (FieldLocation)obj;
        Declaration declaration1 = fieldlocation.getDeclaration();
        referenceexp = new ReferenceExp(declaration1);
        declaration.setAlias(true);
        referenceexp.setDontDereference(true);
        declaration.setValue(referenceexp);
        if (declaration1.isProcedureDecl())
        {
            declaration.setProcedureDecl(true);
        }
        if (declaration1.getFlag(32768L))
        {
            declaration.setSyntax();
        }
        if (declaration.getFlag(2048L)) goto _L2; else goto _L3
_L3:
        String s;
        Declaration declaration2;
        s = fieldlocation.getDeclaringClass().getName();
        declaration2 = moduleexp.firstDecl();
_L8:
        if (declaration2 == null) goto _L2; else goto _L4
_L4:
        if (!s.equals(declaration2.getType().getName()) || !declaration2.getFlag(0x40000000L)) goto _L6; else goto _L5
_L5:
        referenceexp.setContextDecl(declaration2);
_L2:
        return;
_L6:
        declaration2 = declaration2.nextDecl();
        if (true) goto _L8; else goto _L7
_L7:
    }

    public static void register(Object obj)
    {
        ModuleContext.getContext().setInstance(obj);
    }

    public void addDependency(ModuleInfo moduleinfo)
    {
        this;
        JVM INSTR monitorenter ;
        if (dependencies != null) goto _L2; else goto _L1
_L1:
        dependencies = new ModuleInfo[8];
_L4:
        ModuleInfo amoduleinfo1[] = dependencies;
        int i = numDependencies;
        numDependencies = i + 1;
        amoduleinfo1[i] = moduleinfo;
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (numDependencies != dependencies.length) goto _L4; else goto _L3
_L3:
        ModuleInfo amoduleinfo[] = new ModuleInfo[2 * numDependencies];
        System.arraycopy(dependencies, 0, amoduleinfo, 0, numDependencies);
        dependencies = amoduleinfo;
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    public boolean checkCurrent(ModuleManager modulemanager, long l)
    {
        long l1;
        long l2;
        URL url;
        if (sourceAbsPath == null)
        {
            return true;
        }
        if (lastCheckedTime + modulemanager.lastModifiedCacheTime >= l)
        {
            return moduleClass != null;
        }
        l1 = sourceAbsPath.getLastModified();
        l2 = lastModifiedTime;
        lastModifiedTime = l1;
        lastCheckedTime = l;
        if (className == null)
        {
            return false;
        }
        String s;
        int j;
        String s1;
        long l3;
        if (moduleClass == null)
        {
            try
            {
                moduleClass = ClassType.getContextClass(className);
            }
            catch (ClassNotFoundException classnotfoundexception)
            {
                return false;
            }
        }
        if (l2 != 0L || moduleClass == null) goto _L2; else goto _L1
_L1:
        s = className;
        j = s.lastIndexOf('.');
        if (j >= 0)
        {
            s = s.substring(j + 1);
        }
        s1 = (new StringBuilder()).append(s).append(".class").toString();
        url = moduleClass.getResource(s1);
        if (url == null)
        {
            break MISSING_BLOCK_LABEL_184;
        }
        l3 = url.openConnection().getLastModified();
        l2 = l3;
_L3:
        if (url == null)
        {
            return true;
        }
        break; /* Loop/switch isn't completed */
        IOException ioexception;
        ioexception;
        url = null;
        if (true) goto _L3; else goto _L2
_L2:
        if (l1 > l2)
        {
            moduleClass = null;
            return false;
        }
        for (int i = numDependencies; --i >= 0;)
        {
            ModuleInfo moduleinfo = dependencies[i];
            if (moduleinfo.comp == null && !moduleinfo.checkCurrent(modulemanager, l))
            {
                moduleClass = null;
                return false;
            }
        }

        return true;
    }

    public void cleanupAfterCompilation()
    {
        if (comp != null)
        {
            comp.cleanupAfterCompilation();
        }
    }

    public void clearClass()
    {
        moduleClass = null;
        numDependencies = 0;
        dependencies = null;
    }

    public String getClassName()
    {
        this;
        JVM INSTR monitorenter ;
        if (className != null) goto _L2; else goto _L1
_L1:
        if (moduleClass == null) goto _L4; else goto _L3
_L3:
        className = moduleClass.getName();
_L2:
        String s = className;
        this;
        JVM INSTR monitorexit ;
        return s;
_L4:
        if (comp == null || comp.mainClass == null) goto _L2; else goto _L5
_L5:
        className = comp.mainClass.getName();
          goto _L2
        Exception exception;
        exception;
        throw exception;
    }

    public ClassType getClassType()
    {
        this;
        JVM INSTR monitorenter ;
        if (moduleClass == null) goto _L2; else goto _L1
_L1:
        ClassType classtype1 = (ClassType)Type.make(moduleClass);
_L4:
        this;
        JVM INSTR monitorexit ;
        return classtype1;
_L2:
        if (comp != null && comp.mainClass != null)
        {
            classtype1 = comp.mainClass;
            continue; /* Loop/switch isn't completed */
        }
        ClassType classtype = ClassType.make(className);
        classtype1 = classtype;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public Compilation getCompilation()
    {
        return comp;
    }

    public Object getInstance()
    {
        return ModuleContext.getContext().findInstance(this);
    }

    public Class getModuleClass()
        throws ClassNotFoundException
    {
        this;
        JVM INSTR monitorenter ;
        Class class1 = moduleClass;
        if (class1 == null) goto _L2; else goto _L1
_L1:
        Class class3 = class1;
_L4:
        this;
        JVM INSTR monitorexit ;
        return class3;
_L2:
        Class class2;
        class2 = ClassType.getContextClass(className);
        moduleClass = class2;
        class3 = class2;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public Class getModuleClassRaw()
    {
        return moduleClass;
    }

    public ModuleExp getModuleExp()
    {
        this;
        JVM INSTR monitorenter ;
        ModuleExp moduleexp = exp;
        if (moduleexp != null) goto _L2; else goto _L1
_L1:
        if (comp == null) goto _L4; else goto _L3
_L3:
        ModuleExp moduleexp1 = comp.mainLambda;
_L6:
        this;
        JVM INSTR monitorexit ;
        return moduleexp1;
_L4:
        ClassType classtype = ClassType.make(className);
        moduleexp = new ModuleExp();
        moduleexp.type = classtype;
        moduleexp.setName(classtype.getName());
        moduleexp.flags = 0x80000 | moduleexp.flags;
        moduleexp.info = this;
        exp = moduleexp;
_L2:
        moduleexp1 = moduleexp;
        if (true) goto _L6; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public String getNamespaceUri()
    {
        return uri;
    }

    public Object getRunInstance()
    {
        Object obj = getInstance();
        if (obj instanceof Runnable)
        {
            ((Runnable)obj).run();
        }
        return obj;
    }

    public Path getSourceAbsPath()
    {
        return sourceAbsPath;
    }

    public String getSourceAbsPathname()
    {
        String s = sourceAbsPathname;
        if (s == null && sourceAbsPath != null)
        {
            s = sourceAbsPath.toString();
            sourceAbsPathname = s;
        }
        return s;
    }

    public int getState()
    {
        if (comp == null)
        {
            return 14;
        } else
        {
            return comp.getState();
        }
    }

    public void loadByStages(int i)
    {
        if (1 + getState() < i)
        {
            loadByStages(i - 2);
            int j = getState();
            if (j < i)
            {
                comp.setState(j + 1);
                int k = numDependencies;
                for (int l = 0; l < k; l++)
                {
                    dependencies[l].loadByStages(i);
                }

                int i1 = getState();
                if (i1 < i)
                {
                    comp.setState(i1 & -2);
                    comp.process(i);
                    return;
                }
            }
        }
    }

    public boolean loadEager(int i)
    {
        boolean flag = true;
        if (comp != null || className == null) goto _L2; else goto _L1
_L1:
        return false;
_L2:
        int j = getState();
        if (j >= i)
        {
            return flag;
        }
        if ((j & 1) != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        comp.setState(j + 1);
        int k = numDependencies;
        int l = 0;
        do
        {
            if (l >= k)
            {
                break;
            }
            if (!dependencies[l].loadEager(i))
            {
                if (getState() == j + 1)
                {
                    comp.setState(j);
                    return false;
                }
                continue; /* Loop/switch isn't completed */
            }
            l++;
        } while (true);
        if (getState() == j + 1)
        {
            comp.setState(j);
        }
        comp.process(i);
        if (getState() != i)
        {
            flag = false;
        }
        return flag;
        if (true) goto _L1; else goto _L3
_L3:
    }

    public void setClassName(String s)
    {
        className = s;
    }

    public void setCompilation(Compilation compilation)
    {
        compilation.minfo = this;
        comp = compilation;
        ModuleExp moduleexp = compilation.mainLambda;
        exp = moduleexp;
        if (moduleexp != null)
        {
            String s = moduleexp.getFileName();
            sourcePath = s;
            sourceAbsPath = absPath(s);
        }
    }

    public void setModuleClass(Class class1)
    {
        moduleClass = class1;
        className = class1.getName();
        mapClassToInfo.put(class1, this);
    }

    public void setNamespaceUri(String s)
    {
        uri = s;
    }

    public void setSourceAbsPath(Path path)
    {
        sourceAbsPath = path;
        sourceAbsPathname = null;
    }

    public ModuleExp setupModuleExp()
    {
        this;
        JVM INSTR monitorenter ;
        ModuleExp moduleexp;
        int i;
        moduleexp = getModuleExp();
        i = moduleexp.flags;
        if ((i & 0x80000) != 0) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return moduleexp;
_L2:
        moduleexp.setFlag(false, 0x80000);
        if (moduleClass == null) goto _L4; else goto _L3
_L3:
        ClassType classtype;
        Class class2;
        class2 = moduleClass;
        classtype = (ClassType)Type.make(class2);
_L7:
        Object obj = null;
        Language language;
        Field field;
        language = Language.getDefaultLanguage();
        field = classtype.getFields();
_L6:
        if (field == null)
        {
            break MISSING_BLOCK_LABEL_237;
        }
        int j = field.getFlags();
        if ((j & 1) != 0)
        {
            break; /* Loop/switch isn't completed */
        }
_L8:
        field = field.getNext();
        if (true) goto _L6; else goto _L5
_L4:
        Class class1;
        classtype = ClassType.make(className);
        class1 = classtype.getReflectClass();
        class2 = class1;
          goto _L7
_L5:
        if ((j & 8) != 0 || obj != null)
        {
            break MISSING_BLOCK_LABEL_141;
        }
        obj = getInstance();
        Object obj1;
        Declaration declaration;
        obj1 = class2.getField(field.getName()).get(obj);
        declaration = language.declFromField(moduleexp, obj1, field);
        if ((j & 0x10) == 0)
        {
            break MISSING_BLOCK_LABEL_228;
        }
        if ((obj1 instanceof Location) && !(obj1 instanceof FieldLocation))
        {
            break MISSING_BLOCK_LABEL_228;
        }
        declaration.noteValue(new QuoteExp(obj1));
          goto _L8
        Exception exception1;
        exception1;
        throw new WrappedException(exception1);
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        declaration.noteValue(null);
          goto _L8
        Declaration declaration1 = moduleexp.firstDecl();
_L10:
        if (declaration1 == null)
        {
            continue; /* Loop/switch isn't completed */
        }
        Declaration declaration2;
        makeDeclInModule2(moduleexp, declaration1);
        declaration2 = declaration1.nextDecl();
        declaration1 = declaration2;
        if (true) goto _L10; else goto _L9
_L9:
        if (true) goto _L1; else goto _L11
_L11:
    }

    public String toString()
    {
        StringBuffer stringbuffer;
        stringbuffer = new StringBuffer();
        stringbuffer.append("ModuleInfo[");
        if (moduleClass == null) goto _L2; else goto _L1
_L1:
        stringbuffer.append("class: ");
        stringbuffer.append(moduleClass);
_L4:
        stringbuffer.append(']');
        return stringbuffer.toString();
_L2:
        if (className != null)
        {
            stringbuffer.append("class-name: ");
            stringbuffer.append(className);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

}
