// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.kawa.reflect.ClassMemberLocation;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.Externalizable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.net.URL;
import java.util.zip.CRC32;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

// Referenced classes of package gnu.expr:
//            LambdaExp, Compilation, ModuleInfo, Expression, 
//            ModuleContext, ModuleBody, Declaration, Language, 
//            QuoteExp, ReferenceExp, ClassExp, ExpVisitor

public class ModuleExp extends LambdaExp
    implements Externalizable
{

    public static final int EXPORT_SPECIFIED = 16384;
    public static final int IMMEDIATE = 0x100000;
    public static final int LAZY_DECLARATIONS = 0x80000;
    public static final int NONSTATIC_SPECIFIED = 0x10000;
    public static final int STATIC_RUN_SPECIFIED = 0x40000;
    public static final int STATIC_SPECIFIED = 32768;
    public static final int SUPERTYPE_SPECIFIED = 0x20000;
    public static boolean alwaysCompile;
    public static boolean compilerAvailable;
    public static String dumpZipPrefix;
    public static int interactiveCounter;
    static int lastZipCounter;
    public static boolean neverCompile = false;
    ModuleInfo info;
    ClassType interfaces[];
    ClassType superType;

    public ModuleExp()
    {
    }

    public static final boolean evalModule(Environment environment, CallContext callcontext, Compilation compilation, URL url, OutPort outport)
        throws Throwable
    {
        ModuleExp moduleexp = compilation.getModule();
        Language language = compilation.getLanguage();
        Object obj = evalModule1(environment, compilation, url, outport);
        if (obj == null)
        {
            return false;
        } else
        {
            evalModule2(environment, callcontext, language, moduleexp, obj);
            return true;
        }
    }

    public static final Object evalModule1(Environment environment, Compilation compilation, URL url, OutPort outport)
        throws SyntaxException
    {
        ModuleExp moduleexp;
        Environment environment1;
        Compilation compilation1;
        SourceMessages sourcemessages;
        ClassLoader classloader;
        Thread thread;
        moduleexp = compilation.getModule();
        moduleexp.info = compilation.minfo;
        environment1 = Environment.setSaveCurrent(environment);
        compilation1 = Compilation.setSaveCurrent(compilation);
        sourcemessages = compilation.getMessages();
        classloader = null;
        thread = null;
        if (alwaysCompile && neverCompile)
        {
            throw new RuntimeException("alwaysCompile and neverCompile are both true!");
        }
        if (neverCompile)
        {
            compilation.mustCompile = false;
        }
        compilation.process(6);
        compilation.minfo.loadByStages(8);
        if (outport == null) goto _L2; else goto _L1
_L1:
        boolean flag = sourcemessages.checkErrors(outport, 20);
        if (!flag) goto _L4; else goto _L3
_L3:
        Object obj;
        Environment.restoreCurrent(environment1);
        Compilation.restoreCurrent(compilation1);
        if (false)
        {
            null.setContextClassLoader(null);
        }
        obj = null;
_L6:
        return obj;
_L2:
        if (sourcemessages.seenErrors()) goto _L3; else goto _L4
_L4:
        boolean flag1 = compilation.mustCompile;
        classloader = null;
        thread = null;
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_247;
        }
        if (!Compilation.debugPrintFinalExpr)
        {
            break MISSING_BLOCK_LABEL_220;
        }
        classloader = null;
        thread = null;
        if (outport == null)
        {
            break MISSING_BLOCK_LABEL_220;
        }
        outport.println((new StringBuilder()).append("[Evaluating final module \"").append(moduleexp.getName()).append("\":").toString());
        moduleexp.print(outport);
        outport.println(']');
        outport.flush();
        obj = Boolean.TRUE;
        Environment.restoreCurrent(environment1);
        Compilation.restoreCurrent(compilation1);
        if (true) goto _L6; else goto _L5
_L5:
        null.setContextClassLoader(null);
        return obj;
        Class class1 = evalToClass(compilation, url);
        obj = class1;
        if (obj == null)
        {
            Environment.restoreCurrent(environment1);
            Compilation.restoreCurrent(compilation1);
            if (false)
            {
                null.setContextClassLoader(null);
            }
            return null;
        }
        Boolean boolean1;
        try
        {
            thread = Thread.currentThread();
            classloader = thread.getContextClassLoader();
            thread.setContextClassLoader(((Class) (obj)).getClassLoader());
        }
        catch (Throwable throwable)
        {
            thread = null;
        }
        moduleexp.body = null;
        moduleexp.thisVariable = null;
        if (outport == null) goto _L8; else goto _L7
_L7:
        if (!sourcemessages.checkErrors(outport, 20)) goto _L10; else goto _L9
_L9:
        boolean1 = Boolean.valueOf(false);
        obj = boolean1;
        Environment.restoreCurrent(environment1);
        Compilation.restoreCurrent(compilation1);
        if (thread == null) goto _L6; else goto _L11
_L11:
        thread.setContextClassLoader(classloader);
        return obj;
_L8:
        boolean flag2 = sourcemessages.seenErrors();
        if (flag2) goto _L9; else goto _L10
_L10:
        Environment.restoreCurrent(environment1);
        Compilation.restoreCurrent(compilation1);
        if (thread == null) goto _L6; else goto _L12
_L12:
        thread.setContextClassLoader(classloader);
        return obj;
        Exception exception;
        exception;
        Environment.restoreCurrent(environment1);
        Compilation.restoreCurrent(compilation1);
        if (thread != null)
        {
            thread.setContextClassLoader(classloader);
        }
        throw exception;
    }

    public static final void evalModule2(Environment environment, CallContext callcontext, Language language, ModuleExp moduleexp, Object obj)
        throws Throwable
    {
        Environment environment1 = Environment.setSaveCurrent(environment);
        Boolean boolean1 = Boolean.TRUE;
        if (obj != boolean1) goto _L2; else goto _L1
_L1:
        moduleexp.body.apply(callcontext);
_L3:
        callcontext.runUntilDone();
        Environment.restoreCurrent(environment1);
        if (false)
        {
            null.setContextClassLoader(null);
        }
        return;
_L2:
        if (obj instanceof Class)
        {
            obj = ModuleContext.getContext().findInstance((Class)obj);
        }
        if (obj instanceof Runnable)
        {
            if (!(obj instanceof ModuleBody))
            {
                break MISSING_BLOCK_LABEL_142;
            }
            ModuleBody modulebody = (ModuleBody)obj;
            if (!modulebody.runDone)
            {
                modulebody.runDone = true;
                modulebody.run(callcontext);
            }
        }
_L4:
        if (moduleexp != null)
        {
            break MISSING_BLOCK_LABEL_155;
        }
        ClassMemberLocation.defineAll(obj, language, environment);
          goto _L3
        Exception exception;
        exception;
        Environment.restoreCurrent(environment1);
        if (false)
        {
            null.setContextClassLoader(null);
        }
        throw exception;
        ((Runnable)obj).run();
          goto _L4
        Declaration declaration = moduleexp.firstDecl();
_L8:
        if (declaration == null) goto _L3; else goto _L5
_L5:
        Object obj1 = declaration.getSymbol();
        if (!declaration.isPrivate() && obj1 != null) goto _L7; else goto _L6
_L6:
        declaration = declaration.nextDecl();
          goto _L8
_L7:
        Field field = declaration.field;
        if (!(obj1 instanceof Symbol)) goto _L10; else goto _L9
_L9:
        Symbol symbol = (Symbol)obj1;
_L13:
        Object obj2;
        Expression expression;
        obj2 = language.getEnvPropertyFor(declaration);
        expression = declaration.getValue();
        if ((0x10 & declaration.field.getModifiers()) == 0)
        {
            break MISSING_BLOCK_LABEL_390;
        }
        if (!(expression instanceof QuoteExp) || expression == QuoteExp.undefined_exp) goto _L12; else goto _L11
_L11:
        Object obj3 = ((QuoteExp)expression).getValue();
_L16:
        if (!declaration.isIndirectBinding())
        {
            break MISSING_BLOCK_LABEL_377;
        }
        environment.addLocation(symbol, obj2, (Location)obj3);
          goto _L6
_L10:
        symbol = Symbol.make("", obj1.toString().intern());
          goto _L13
_L12:
        obj3 = declaration.field.getReflectField().get(null);
        if (declaration.isIndirectBinding()) goto _L15; else goto _L14
_L14:
        declaration.setValue(QuoteExp.getInstance(obj3));
          goto _L16
_L15:
        if (declaration.isAlias() && (expression instanceof ReferenceExp)) goto _L16; else goto _L17
_L17:
        declaration.setValue(null);
          goto _L16
        environment.define(symbol, obj2, obj3);
          goto _L6
        StaticFieldLocation staticfieldlocation = new StaticFieldLocation(field.getDeclaringClass(), field.getName());
        staticfieldlocation.setDeclaration(declaration);
        environment.addLocation(symbol, obj2, staticfieldlocation);
        declaration.setValue(null);
          goto _L6
    }

    public static Class evalToClass(Compilation compilation, URL url)
        throws SyntaxException
    {
        SourceMessages sourcemessages;
        compilation.getModule();
        sourcemessages = compilation.getMessages();
        compilation.minfo.loadByStages(12);
        ArrayClassLoader arrayclassloader;
        int i;
        ArrayClassLoader arrayclassloader1;
        if (sourcemessages.seenErrors())
        {
            return null;
        }
        String s;
        ZipOutputStream zipoutputstream;
        StringBuffer stringbuffer;
        FileOutputStream fileoutputstream;
        ModuleInfo moduleinfo;
        int k;
        int l;
        ModuleInfo moduleinfo1;
        Class class2;
        ClassType classtype;
        Class class3;
        ClassType classtype1;
        String s1;
        byte abyte0[];
        String s2;
        ZipEntry zipentry;
        CRC32 crc32;
        try
        {
            arrayclassloader = compilation.loader;
        }
        catch (IOException ioexception)
        {
            WrappedException wrappedexception1 = new WrappedException("I/O error in lambda eval", ioexception);
            throw wrappedexception1;
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            WrappedException wrappedexception = new WrappedException("class not found in lambda eval", classnotfoundexception);
            throw wrappedexception;
        }
        catch (Throwable throwable)
        {
            compilation.getMessages().error('f', (new StringBuilder()).append("internal compile error - caught ").append(throwable).toString(), throwable);
            SyntaxException syntaxexception = new SyntaxException(sourcemessages);
            throw syntaxexception;
        }
        if (url != null)
        {
            break MISSING_BLOCK_LABEL_45;
        }
        url = Path.currentPath().toURL();
        arrayclassloader.setResourceContext(url);
        s = dumpZipPrefix;
        zipoutputstream = null;
        if (s == null) goto _L2; else goto _L1
_L1:
        stringbuffer = new StringBuffer(dumpZipPrefix);
        lastZipCounter = 1 + lastZipCounter;
        if (interactiveCounter > lastZipCounter)
        {
            lastZipCounter = interactiveCounter;
        }
        stringbuffer.append(lastZipCounter);
        stringbuffer.append(".zip");
        fileoutputstream = new FileOutputStream(stringbuffer.toString());
        zipoutputstream = new ZipOutputStream(fileoutputstream);
          goto _L2
_L11:
        if (i >= compilation.numClasses) goto _L4; else goto _L3
_L3:
        classtype1 = compilation.classes[i];
        s1 = classtype1.getName();
        abyte0 = classtype1.writeToArray();
        arrayclassloader.addClass(s1, abyte0);
        if (zipoutputstream == null) goto _L6; else goto _L5
_L5:
        s2 = (new StringBuilder()).append(s1.replace('.', '/')).append(".class").toString();
        zipentry = new ZipEntry(s2);
        zipentry.setSize(abyte0.length);
        crc32 = new CRC32();
        crc32.update(abyte0);
        zipentry.setCrc(crc32.getValue());
        zipentry.setMethod(0);
        zipoutputstream.putNextEntry(zipentry);
        zipoutputstream.write(abyte0);
          goto _L6
_L4:
        if (zipoutputstream == null) goto _L8; else goto _L7
_L7:
        zipoutputstream.close();
          goto _L8
_L12:
        for (; arrayclassloader1.getParent() instanceof ArrayClassLoader; arrayclassloader1 = (ArrayClassLoader)arrayclassloader1.getParent()) { }
        break MISSING_BLOCK_LABEL_588;
_L13:
        Class class1;
        int j;
        for (; j >= compilation.numClasses; j++)
        {
            break MISSING_BLOCK_LABEL_418;
        }

        classtype = compilation.classes[j];
        class3 = arrayclassloader.loadClass(classtype.getName());
        classtype.setReflectClass(class3);
        classtype.setExisting(true);
        if (j == 0)
        {
            class1 = class3;
            break MISSING_BLOCK_LABEL_597;
        }
        if (arrayclassloader1 == arrayclassloader)
        {
            break MISSING_BLOCK_LABEL_597;
        }
        arrayclassloader1.addClass(class3);
        break MISSING_BLOCK_LABEL_597;
        moduleinfo = compilation.minfo;
        moduleinfo.setModuleClass(class1);
        compilation.cleanupAfterCompilation();
        k = moduleinfo.numDependencies;
        l = 0;
_L10:
        if (l >= k)
        {
            break; /* Loop/switch isn't completed */
        }
        moduleinfo1 = moduleinfo.dependencies[l];
        class2 = moduleinfo1.getModuleClassRaw();
        if (class2 != null)
        {
            break MISSING_BLOCK_LABEL_485;
        }
        class2 = evalToClass(moduleinfo1.comp, null);
        compilation.loader.addClass(class2);
        l++;
        if (true) goto _L10; else goto _L9
_L9:
        return class1;
_L2:
        i = 0;
          goto _L11
_L6:
        i++;
          goto _L11
_L8:
        arrayclassloader1 = arrayclassloader;
          goto _L12
        class1 = null;
        j = 0;
          goto _L13
    }

    public static void mustAlwaysCompile()
    {
        alwaysCompile = true;
        neverCompile = false;
    }

    public static void mustNeverCompile()
    {
        alwaysCompile = false;
        neverCompile = true;
        compilerAvailable = false;
    }

    public void allocChildClasses(Compilation compilation)
    {
        declareClosureEnv();
        if (!compilation.usingCPStyle())
        {
            return;
        } else
        {
            allocFrame(compilation);
            return;
        }
    }

    void allocFields(Compilation compilation)
    {
        Declaration declaration = firstDecl();
        do
        {
            if (declaration == null)
            {
                break;
            }
            if ((!declaration.isSimple() || declaration.isPublic()) && declaration.field == null && declaration.getFlag(0x10000L) && declaration.getFlag(6L))
            {
                declaration.makeField(compilation, null);
            }
            declaration = declaration.nextDecl();
        } while (true);
        Declaration declaration1 = firstDecl();
        while (declaration1 != null) 
        {
            if (declaration1.field == null)
            {
                Expression expression = declaration1.getValue();
                if ((!declaration1.isSimple() || declaration1.isPublic() || declaration1.isNamespaceDecl() || declaration1.getFlag(16384L) && declaration1.getFlag(6L)) && !declaration1.getFlag(0x10000L))
                {
                    if ((expression instanceof LambdaExp) && !(expression instanceof ModuleExp) && !(expression instanceof ClassExp))
                    {
                        ((LambdaExp)expression).allocFieldFor(compilation);
                    } else
                    {
                        if (!declaration1.shouldEarlyInit() && !declaration1.isAlias())
                        {
                            expression = null;
                        }
                        declaration1.makeField(compilation, expression);
                    }
                }
            }
            declaration1 = declaration1.nextDecl();
        }
    }

    public ClassType classFor(Compilation compilation)
    {
        if (type == null || type == Compilation.typeProcedure) goto _L2; else goto _L1
_L1:
        ClassType classtype = type;
_L4:
        return classtype;
_L2:
        String s3;
        String s4;
        String s = getFileName();
        String s1 = getName();
        Path path = null;
        String s2;
        Path path1;
        String s5;
        String s6;
        if (s1 != null)
        {
            s2 = s1;
        } else
        if (s == null)
        {
            s2 = getName();
            path = null;
            if (s2 == null)
            {
                s2 = "$unnamed_input_file$";
                path = null;
            }
        } else
        if (filename.equals("-") || filename.equals("/dev/stdin"))
        {
            s2 = getName();
            path = null;
            if (s2 == null)
            {
                s2 = "$stdin$";
                path = null;
            }
        } else
        {
            path = Path.valueOf(s);
            s2 = path.getLast();
            int i = s2.lastIndexOf('.');
            if (i > 0)
            {
                s2 = s2.substring(0, i);
            }
        }
        if (getName() == null)
        {
            setName(s2);
        }
        s3 = Compilation.mangleNameIfNeeded(s2);
        if (compilation.classPrefix.length() != 0 || path == null || path.isAbsolute())
        {
            break; /* Loop/switch isn't completed */
        }
        path1 = path.getParent();
        if (path1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        s5 = path1.toString();
        if (s5.length() <= 0 || s5.indexOf("..") >= 0)
        {
            break; /* Loop/switch isn't completed */
        }
        s6 = s5.replaceAll(System.getProperty("file.separator"), "/");
        if (s6.startsWith("./"))
        {
            s6 = s6.substring(2);
        }
        if (s6.equals("."))
        {
            s4 = s3;
        } else
        {
            s4 = (new StringBuilder()).append(Compilation.mangleURI(s6)).append(".").append(s3).toString();
        }
_L5:
        classtype = new ClassType(s4);
        setType(classtype);
        if (compilation.mainLambda == this)
        {
            if (compilation.mainClass == null)
            {
                compilation.mainClass = classtype;
                return classtype;
            }
            continue; /* Loop/switch isn't completed */
        }
        if (true) goto _L4; else goto _L3
_L3:
        s4 = (new StringBuilder()).append(compilation.classPrefix).append(s3).toString();
          goto _L5
        if (s4.equals(compilation.mainClass.getName())) goto _L4; else goto _L6
_L6:
        compilation.error('e', (new StringBuilder()).append("inconsistent main class name: ").append(s4).append(" - old name: ").append(compilation.mainClass.getName()).toString());
        return classtype;
    }

    public Declaration firstDecl()
    {
        this;
        JVM INSTR monitorenter ;
        if (getFlag(0x80000))
        {
            info.setupModuleExp();
        }
        this;
        JVM INSTR monitorexit ;
        return decls;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final ClassType[] getInterfaces()
    {
        return interfaces;
    }

    public String getNamespaceUri()
    {
        return info.uri;
    }

    public final ClassType getSuperType()
    {
        return superType;
    }

    public final boolean isStatic()
    {
        return getFlag(32768) || (Compilation.moduleStatic >= 0 || getFlag(0x100000)) && !getFlag(0x20000) && !getFlag(0x10000);
    }

    public void print(OutPort outport)
    {
        outport.startLogicalBlock("(Module/", ")", 2);
        Object obj = getSymbol();
        if (obj != null)
        {
            outport.print(obj);
            outport.print('/');
        }
        outport.print(id);
        outport.print('/');
        outport.writeSpaceFill();
        outport.startLogicalBlock("(", false, ")");
        Declaration declaration = firstDecl();
        if (declaration != null)
        {
            outport.print("Declarations:");
            for (; declaration != null; declaration = declaration.nextDecl())
            {
                outport.writeSpaceFill();
                declaration.printInfo(outport);
            }

        }
        outport.endLogicalBlock(")");
        outport.writeSpaceLinear();
        if (body == null)
        {
            outport.print("<null body>");
        } else
        {
            body.print(outport);
        }
        outport.endLogicalBlock(")");
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        Object obj = objectinput.readObject();
        if (obj instanceof ClassType)
        {
            type = (ClassType)obj;
            setName(type.getName());
        } else
        {
            setName((String)obj);
        }
        flags = 0x80000 | flags;
    }

    public final void setInterfaces(ClassType aclasstype[])
    {
        interfaces = aclasstype;
    }

    public final void setSuperType(ClassType classtype)
    {
        superType = classtype;
    }

    public boolean staticInitRun()
    {
        return isStatic() && (getFlag(0x40000) || Compilation.moduleStatic == 2);
    }

    protected Object visit(ExpVisitor expvisitor, Object obj)
    {
        return expvisitor.visitModuleExp(this, obj);
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        if (type != null && type != Compilation.typeProcedure && !type.isExisting())
        {
            objectoutput.writeObject(type);
            return;
        }
        String s = null;
        if (true)
        {
            s = getName();
        }
        if (s == null)
        {
            s = getFileName();
        }
        objectoutput.writeObject(s);
    }

    static 
    {
        compilerAvailable = true;
        alwaysCompile = compilerAvailable;
    }
}
