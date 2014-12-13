// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import gnu.text.URLPath;
import java.io.File;
import java.net.URL;

// Referenced classes of package gnu.expr:
//            ModuleInfo, ModuleSet, Compilation, ModuleExp

public class ModuleManager
{

    public static final long LAST_MODIFIED_CACHE_TIME = 1000L;
    static ModuleManager instance = new ModuleManager();
    private String compilationDirectory;
    public long lastModifiedCacheTime;
    ModuleInfo modules[];
    int numModules;
    ModuleSet packageInfoChain;

    public ModuleManager()
    {
        compilationDirectory = "";
        lastModifiedCacheTime = 1000L;
    }

    private void add(ModuleInfo moduleinfo)
    {
        this;
        JVM INSTR monitorenter ;
        if (modules != null) goto _L2; else goto _L1
_L1:
        modules = new ModuleInfo[10];
_L4:
        ModuleInfo amoduleinfo1[] = modules;
        int i = numModules;
        numModules = i + 1;
        amoduleinfo1[i] = moduleinfo;
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        if (numModules != modules.length) goto _L4; else goto _L3
_L3:
        ModuleInfo amoduleinfo[] = new ModuleInfo[2 * numModules];
        System.arraycopy(modules, 0, amoduleinfo, 0, numModules);
        modules = amoduleinfo;
          goto _L4
        Exception exception;
        exception;
        throw exception;
    }

    public static ModuleInfo findWithClass(Class class1)
    {
        gnu/expr/ModuleManager;
        JVM INSTR monitorenter ;
        ModuleInfo moduleinfo = (ModuleInfo)ModuleInfo.mapClassToInfo.get(class1);
        if (moduleinfo != null)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        moduleinfo = new ModuleInfo();
        moduleinfo.setModuleClass(class1);
        gnu/expr/ModuleManager;
        JVM INSTR monitorexit ;
        return moduleinfo;
        Exception exception;
        exception;
        throw exception;
    }

    public static ModuleManager getInstance()
    {
        return instance;
    }

    private ModuleInfo searchWithAbsSourcePath(String s)
    {
        this;
        JVM INSTR monitorenter ;
        int i = numModules;
_L4:
        if (--i < 0) goto _L2; else goto _L1
_L1:
        ModuleInfo moduleinfo;
        boolean flag;
        moduleinfo = modules[i];
        flag = s.equals(moduleinfo.getSourceAbsPathname());
        if (!flag) goto _L4; else goto _L3
_L3:
        this;
        JVM INSTR monitorexit ;
        return moduleinfo;
_L2:
        moduleinfo = null;
        if (true) goto _L3; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public void clear()
    {
        this;
        JVM INSTR monitorenter ;
        ModuleSet moduleset = packageInfoChain;
_L2:
        if (moduleset == null)
        {
            break; /* Loop/switch isn't completed */
        }
        ModuleSet moduleset1;
        moduleset1 = moduleset.next;
        moduleset.next = null;
        moduleset = moduleset1;
        if (true) goto _L2; else goto _L1
_L1:
        packageInfoChain = null;
        modules = null;
        numModules = 0;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public ModuleInfo find(Compilation compilation)
    {
        this;
        JVM INSTR monitorenter ;
        ModuleInfo moduleinfo;
        ModuleExp moduleexp = compilation.getModule();
        ClassType classtype = moduleexp.classFor(compilation);
        String s = moduleexp.getFileName();
        moduleinfo = findWithSourcePath(ModuleInfo.absPath(s), s);
        moduleinfo.setClassName(classtype.getName());
        moduleinfo.exp = moduleexp;
        compilation.minfo = moduleinfo;
        moduleinfo.comp = compilation;
        this;
        JVM INSTR monitorexit ;
        return moduleinfo;
        Exception exception;
        exception;
        throw exception;
    }

    public ModuleInfo findWithClassName(String s)
    {
        ModuleInfo moduleinfo = searchWithClassName(s);
        if (moduleinfo != null)
        {
            return moduleinfo;
        }
        ModuleInfo moduleinfo1;
        try
        {
            moduleinfo1 = findWithClass(ClassType.getContextClass(s));
        }
        catch (Throwable throwable)
        {
            throw WrappedException.wrapIfNeeded(throwable);
        }
        return moduleinfo1;
    }

    public ModuleInfo findWithSourcePath(Path path, String s)
    {
        this;
        JVM INSTR monitorenter ;
        String s1;
        ModuleInfo moduleinfo;
        s1 = path.toString();
        moduleinfo = searchWithAbsSourcePath(s1);
        if (moduleinfo != null)
        {
            break MISSING_BLOCK_LABEL_55;
        }
        moduleinfo = new ModuleInfo();
        moduleinfo.sourcePath = s;
        moduleinfo.sourceAbsPath = path;
        moduleinfo.sourceAbsPathname = s1;
        add(moduleinfo);
        this;
        JVM INSTR monitorexit ;
        return moduleinfo;
        Exception exception;
        exception;
        throw exception;
    }

    public ModuleInfo findWithSourcePath(String s)
    {
        this;
        JVM INSTR monitorenter ;
        ModuleInfo moduleinfo = findWithSourcePath(ModuleInfo.absPath(s), s);
        this;
        JVM INSTR monitorexit ;
        return moduleinfo;
        Exception exception;
        exception;
        throw exception;
    }

    public ModuleInfo findWithURL(URL url)
    {
        this;
        JVM INSTR monitorenter ;
        ModuleInfo moduleinfo = findWithSourcePath(URLPath.valueOf(url), url.toExternalForm());
        this;
        JVM INSTR monitorexit ;
        return moduleinfo;
        Exception exception;
        exception;
        throw exception;
    }

    public String getCompilationDirectory()
    {
        return compilationDirectory;
    }

    public ModuleInfo getModule(int i)
    {
        this;
        JVM INSTR monitorenter ;
        int j = numModules;
        if (i < j) goto _L2; else goto _L1
_L1:
        ModuleInfo moduleinfo = null;
_L4:
        this;
        JVM INSTR monitorexit ;
        return moduleinfo;
_L2:
        moduleinfo = modules[i];
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public void loadPackageInfo(String s)
        throws ClassNotFoundException, InstantiationException, IllegalAccessException
    {
        this;
        JVM INSTR monitorenter ;
        String s1;
        ModuleSet moduleset;
        s1 = (new StringBuilder()).append(s).append(".").append("$ModulesMap$").toString();
        moduleset = packageInfoChain;
_L1:
        if (moduleset == null)
        {
            break MISSING_BLOCK_LABEL_63;
        }
        if (!moduleset.getClass().getName().equals(s1));
        moduleset = moduleset.next;
          goto _L1
        ModuleSet moduleset1 = (ModuleSet)Class.forName(s1).newInstance();
        moduleset1.next = packageInfoChain;
        packageInfoChain = moduleset1;
        moduleset1.register(this);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public void register(String s, String s1, String s2)
    {
        this;
        JVM INSTR monitorenter ;
        ModuleInfo moduleinfo = searchWithClassName(s);
        if (moduleinfo == null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Path path;
        String s3;
        ModuleInfo moduleinfo1;
        path = Path.valueOf(s1);
        s3 = path.getCanonical().toString();
        if (searchWithAbsSourcePath(s3) != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        moduleinfo1 = new ModuleInfo();
        if (!path.isAbsolute()) goto _L4; else goto _L3
_L3:
        moduleinfo1.sourceAbsPath = path;
        moduleinfo1.sourceAbsPathname = s3;
_L6:
        moduleinfo1.setClassName(s);
        moduleinfo1.sourcePath = s1;
        moduleinfo1.uri = s2;
        add(moduleinfo1);
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
_L4:
        Class class1 = packageInfoChain.getClass();
        String s4 = (new StringBuilder()).append(class1.getName().replace('.', '/')).append(".class").toString();
        Path path1 = URLPath.valueOf(class1.getClassLoader().getResource(s4)).resolve(s1);
        moduleinfo1.sourceAbsPath = path1;
        moduleinfo1.sourceAbsPathname = path1.toString();
        if (true) goto _L6; else goto _L5
_L5:
        Throwable throwable;
        throwable;
        if (true) goto _L1; else goto _L7
_L7:
    }

    public ModuleInfo searchWithClassName(String s)
    {
        this;
        JVM INSTR monitorenter ;
        int i = numModules;
_L4:
        if (--i < 0) goto _L2; else goto _L1
_L1:
        ModuleInfo moduleinfo;
        boolean flag;
        moduleinfo = modules[i];
        flag = s.equals(moduleinfo.getClassName());
        if (!flag) goto _L4; else goto _L3
_L3:
        this;
        JVM INSTR monitorexit ;
        return moduleinfo;
_L2:
        moduleinfo = null;
        if (true) goto _L3; else goto _L5
_L5:
        Exception exception;
        exception;
        throw exception;
    }

    public void setCompilationDirectory(String s)
    {
        if (s == null)
        {
            s = "";
        }
        int i = s.length();
        if (i > 0)
        {
            char c = File.separatorChar;
            if (s.charAt(i - 1) != c)
            {
                s = (new StringBuilder()).append(s).append(c).toString();
            }
        }
        compilationDirectory = s;
    }

}
