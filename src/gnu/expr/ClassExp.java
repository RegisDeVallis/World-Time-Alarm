// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.OutPort;
import java.util.Hashtable;
import java.util.Vector;

// Referenced classes of package gnu.expr:
//            LambdaExp, PairClassType, Compilation, Target, 
//            Expression, Declaration, IgnoreTarget, ModuleExp, 
//            BeginExp, ApplyExp, QuoteExp, PrimProcedure, 
//            Initializer, ClassInitializer, Language, ObjectExp, 
//            ExpVisitor

public class ClassExp extends LambdaExp
{

    public static final int CLASS_SPECIFIED = 0x10000;
    public static final int HAS_SUBCLASS = 0x20000;
    public static final int INTERFACE_SPECIFIED = 32768;
    public static final int IS_ABSTRACT = 16384;
    public String classNameSpecifier;
    public LambdaExp clinitMethod;
    boolean explicitInit;
    public LambdaExp initMethod;
    ClassType instanceType;
    boolean partsDeclared;
    boolean simple;
    public int superClassIndex;
    public Expression supers[];

    public ClassExp()
    {
        superClassIndex = -1;
    }

    public ClassExp(boolean flag)
    {
        superClassIndex = -1;
        simple = flag;
        ClassType classtype = new ClassType();
        type = classtype;
        instanceType = classtype;
    }

    static void getImplMethods(ClassType classtype, String s, Type atype[], Vector vector)
    {
        if (!(classtype instanceof PairClassType)) goto _L2; else goto _L1
_L1:
        ClassType classtype1 = ((PairClassType)classtype).instanceType;
_L6:
        Type atype1[] = new Type[1 + atype.length];
        atype1[0] = classtype;
        System.arraycopy(atype, 0, atype1, 1, atype.length);
        Method method = classtype1.getDeclaredMethod(s, atype1);
        Throwable throwable;
        Class class1;
        if (method != null)
        {
            int j = vector.size();
            if (j == 0 || !vector.elementAt(j - 1).equals(method))
            {
                vector.addElement(method);
            }
        } else
        {
            ClassType aclasstype[] = classtype.getInterfaces();
            int i = 0;
            while (i < aclasstype.length) 
            {
                getImplMethods(aclasstype[i], s, atype, vector);
                i++;
            }
        }
_L4:
        return;
_L2:
        if (!classtype.isInterface()) goto _L4; else goto _L3
_L3:
        try
        {
            class1 = classtype.getReflectClass();
        }
        // Misplaced declaration of an exception variable
        catch (Throwable throwable)
        {
            return;
        }
        if (class1 == null) goto _L4; else goto _L5
_L5:
        classtype1 = (ClassType)Type.make(Class.forName((new StringBuilder()).append(classtype.getName()).append("$class").toString(), false, class1.getClassLoader()));
          goto _L6
    }

    static void invokeDefaultSuperConstructor(ClassType classtype, Compilation compilation, LambdaExp lambdaexp)
    {
        CodeAttr codeattr = compilation.getCode();
        Method method = classtype.getDeclaredMethod("<init>", 0);
        if (method == null)
        {
            compilation.error('e', "super class does not have a default constructor");
            return;
        }
        codeattr.emitPushThis();
        if (classtype.hasOuterLink() && (lambdaexp instanceof ClassExp))
        {
            ClassExp classexp = (ClassExp)lambdaexp;
            loadSuperStaticLink(classexp.supers[classexp.superClassIndex], classtype, compilation);
        }
        codeattr.emitInvokeSpecial(method);
    }

    static void loadSuperStaticLink(Expression expression, ClassType classtype, Compilation compilation)
    {
        CodeAttr codeattr = compilation.getCode();
        expression.compile(compilation, Target.pushValue(Compilation.typeClassType));
        codeattr.emitInvokeStatic(ClassType.make("gnu.expr.PairClassType").getDeclaredMethod("extractStaticLink", 1));
        codeattr.emitCheckcast(classtype.getOuterLinkType());
    }

    public static String slotToMethodName(String s, String s1)
    {
        if (!Compilation.isValidJavaName(s1))
        {
            s1 = Compilation.mangleName(s1, false);
        }
        int i = s1.length();
        StringBuffer stringbuffer = new StringBuffer(i + 3);
        stringbuffer.append(s);
        if (i > 0)
        {
            stringbuffer.append(Character.toTitleCase(s1.charAt(0)));
            stringbuffer.append(s1.substring(1));
        }
        return stringbuffer.toString();
    }

    private static void usedSuperClasses(ClassType classtype, Compilation compilation)
    {
        compilation.usedClass(classtype.getSuperclass());
        ClassType aclasstype[] = classtype.getInterfaces();
        if (aclasstype != null)
        {
            for (int i = aclasstype.length; --i >= 0;)
            {
                compilation.usedClass(aclasstype[i]);
            }

        }
    }

    public Declaration addMethod(LambdaExp lambdaexp, Object obj)
    {
        Declaration declaration = addDeclaration(obj, Compilation.typeProcedure);
        lambdaexp.outer = this;
        lambdaexp.setClassMethod(true);
        declaration.noteValue(lambdaexp);
        declaration.setFlag(0x100000L);
        declaration.setProcedureDecl(true);
        lambdaexp.setSymbol(obj);
        return declaration;
    }

    public void compile(Compilation compilation, Target target)
    {
        if (target instanceof IgnoreTarget)
        {
            return;
        } else
        {
            compileMembers(compilation);
            compilePushClass(compilation, target);
            return;
        }
    }

    public ClassType compileMembers(Compilation compilation)
    {
        ClassType classtype;
        Method method;
        classtype = compilation.curClass;
        method = compilation.method;
        ClassType classtype1;
        LambdaExp lambdaexp;
        classtype1 = getCompiledClassType(compilation);
        compilation.curClass = classtype1;
        lambdaexp = outerLambda();
        if (!(lambdaexp instanceof ClassExp)) goto _L2; else goto _L1
_L1:
        Object obj = lambdaexp.type;
_L5:
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_74;
        }
        classtype1.setEnclosingMember(((gnu.bytecode.Member) (obj)));
        if (obj instanceof ClassType)
        {
            ((ClassType)obj).addMemberClass(classtype1);
        }
        String s;
        if (instanceType != classtype1)
        {
            instanceType.setEnclosingMember(this.type);
            this.type.addMemberClass(instanceType);
        }
        usedSuperClasses(this.type, compilation);
        if (this.type != instanceType)
        {
            usedSuperClasses(instanceType, compilation);
        }
        s = getFileName();
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_150;
        }
        classtype1.setSourceFile(s);
        LambdaExp lambdaexp1;
        LambdaExp lambdaexp2;
        lambdaexp1 = compilation.curLambda;
        compilation.curLambda = this;
        allocFrame(compilation);
        lambdaexp2 = firstChild;
_L4:
        if (lambdaexp2 == null)
        {
            break MISSING_BLOCK_LABEL_787;
        }
        if (!lambdaexp2.isAbstract())
        {
            break; /* Loop/switch isn't completed */
        }
_L13:
        lambdaexp2 = lambdaexp2.nextSibling;
        if (true) goto _L4; else goto _L3
_L2:
        if (lambdaexp == null)
        {
            break MISSING_BLOCK_LABEL_214;
        }
        if (lambdaexp instanceof ModuleExp)
        {
            break MISSING_BLOCK_LABEL_214;
        }
        obj = method;
          goto _L5
        boolean flag5 = lambdaexp instanceof ModuleExp;
        obj = null;
        if (!flag5) goto _L5; else goto _L6
_L6:
        int i1 = this.type.getName().indexOf('$');
        obj = null;
        if (i1 <= 0) goto _L5; else goto _L7
_L7:
        obj = lambdaexp.type;
          goto _L5
_L3:
        Method method1;
        LambdaExp lambdaexp3;
        String s1;
        int i;
        int j;
        Declaration declaration;
        method1 = compilation.method;
        lambdaexp3 = compilation.curLambda;
        s1 = compilation.getFileName();
        i = compilation.getLineNumber();
        j = compilation.getColumnNumber();
        compilation.setLine(lambdaexp2);
        compilation.method = lambdaexp2.getMainMethod();
        declaration = lambdaexp2.nameDecl;
        if (declaration == null)
        {
            break MISSING_BLOCK_LABEL_329;
        }
        if (declaration.getFlag(2048L))
        {
            break MISSING_BLOCK_LABEL_339;
        }
        lambdaexp2.declareThis(compilation.curClass);
        Expression expression;
        compilation.curClass = instanceType;
        compilation.curLambda = lambdaexp2;
        compilation.method.initCode();
        lambdaexp2.allocChildClasses(compilation);
        lambdaexp2.allocParameters(compilation);
        if (!"*init*".equals(lambdaexp2.getName()))
        {
            break MISSING_BLOCK_LABEL_772;
        }
        CodeAttr codeattr = compilation.getCode();
        if (staticLinkField != null)
        {
            codeattr.emitPushThis();
            codeattr.emitLoad(codeattr.getCurrentScope().getVariable(1));
            codeattr.emitPutField(staticLinkField);
        }
        expression = lambdaexp2.body;
_L8:
        BeginExp beginexp;
        if (!(expression instanceof BeginExp))
        {
            break MISSING_BLOCK_LABEL_475;
        }
        beginexp = (BeginExp)expression;
        if (beginexp.length != 0)
        {
            break MISSING_BLOCK_LABEL_463;
        }
        expression = null;
          goto _L8
        expression = beginexp.exps[0];
          goto _L8
        boolean flag = expression instanceof ApplyExp;
        ClassType classtype2;
        classtype2 = null;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_595;
        }
        Expression expression1;
        boolean flag1;
        expression1 = ((ApplyExp)expression).func;
        flag1 = expression1 instanceof QuoteExp;
        classtype2 = null;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_595;
        }
        Object obj1;
        boolean flag2;
        obj1 = ((QuoteExp)expression1).getValue();
        flag2 = obj1 instanceof PrimProcedure;
        classtype2 = null;
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_595;
        }
        PrimProcedure primprocedure;
        boolean flag3;
        primprocedure = (PrimProcedure)obj1;
        flag3 = primprocedure.isSpecial();
        classtype2 = null;
        if (!flag3)
        {
            break MISSING_BLOCK_LABEL_595;
        }
        boolean flag4 = "<init>".equals(primprocedure.method.getName());
        classtype2 = null;
        if (!flag4)
        {
            break MISSING_BLOCK_LABEL_595;
        }
        classtype2 = primprocedure.method.getDeclaringClass();
        ClassType classtype3 = instanceType.getSuperclass();
        if (classtype2 == null) goto _L10; else goto _L9
_L9:
        expression.compileWithPosition(compilation, Target.Ignore);
        if (classtype2 == instanceType || classtype2 == classtype3)
        {
            break MISSING_BLOCK_LABEL_643;
        }
        compilation.error('e', "call to <init> for not this or super class");
_L15:
        lambdaexp2.enterFunction(compilation);
        if (classtype2 != instanceType)
        {
            compilation.callInitMethods(getCompiledClassType(compilation), new Vector(10));
        }
        if (classtype2 == null) goto _L12; else goto _L11
_L11:
        Expression.compileButFirst(lambdaexp2.body, compilation);
_L16:
        lambdaexp2.compileEnd(compilation);
        lambdaexp2.generateApplyMethods(compilation);
        compilation.method = method1;
        compilation.curClass = classtype1;
        compilation.curLambda = lambdaexp3;
        compilation.setLine(s1, i, j);
          goto _L13
        Exception exception;
        exception;
        compilation.curClass = classtype;
        compilation.method = method;
        throw exception;
_L10:
        if (classtype3 == null) goto _L15; else goto _L14
_L14:
        invokeDefaultSuperConstructor(classtype3, compilation, this);
          goto _L15
_L12:
        lambdaexp2.compileBody(compilation);
          goto _L16
        lambdaexp2.enterFunction(compilation);
        lambdaexp2.compileBody(compilation);
          goto _L16
        if (explicitInit || instanceType.isInterface()) goto _L18; else goto _L17
_L17:
        compilation.generateConstructor(instanceType, this);
_L27:
        if (!isAbstract()) goto _L20; else goto _L19
_L19:
        Method amethod[];
        int k;
        amethod = null;
        k = 0;
          goto _L21
_L35:
        int l;
        if (l >= k) goto _L23; else goto _L22
_L22:
        Method method2;
        String s2;
        Type atype[];
        Type type;
        Method method3;
        method2 = amethod[l];
        s2 = method2.getName();
        atype = method2.getParameterTypes();
        type = method2.getReturnType();
        method3 = instanceType.getMethod(s2, atype);
        if (method3 == null) goto _L25; else goto _L24
_L24:
        if (!method3.isAbstract())
        {
            break MISSING_BLOCK_LABEL_1338;
        }
          goto _L25
_L18:
        if (initChain == null) goto _L27; else goto _L26
_L26:
        initChain.reportError("unimplemented: explicit constructor cannot initialize ", compilation);
          goto _L27
_L20:
        amethod = this.type.getAbstractMethods();
        k = amethod.length;
          goto _L21
_L25:
        char c;
        if (s2.length() <= 3 || s2.charAt(2) != 't' || s2.charAt(1) != 'e')
        {
            break MISSING_BLOCK_LABEL_1162;
        }
        c = s2.charAt(0);
        if (c != 'g' && c != 's')
        {
            break MISSING_BLOCK_LABEL_1162;
        }
        if (c != 's') goto _L29; else goto _L28
_L28:
        if (!type.isVoid() || atype.length != 1) goto _L29; else goto _L30
_L30:
        Type type1 = atype[0];
_L31:
        String s4;
        Field field;
        s4 = (new StringBuilder()).append(Character.toLowerCase(s2.charAt(3))).append(s2.substring(4)).toString();
        field = instanceType.getField(s4);
        if (field != null)
        {
            break MISSING_BLOCK_LABEL_1075;
        }
        field = instanceType.addField(s4, type1, 1);
        CodeAttr codeattr2;
        codeattr2 = instanceType.addMethod(s2, 1, atype, type).startCode();
        codeattr2.emitPushThis();
        if (c != 'g')
        {
            break MISSING_BLOCK_LABEL_1141;
        }
        codeattr2.emitGetField(field);
_L32:
        codeattr2.emitReturn();
        break MISSING_BLOCK_LABEL_1338;
_L29:
        if (c != 'g')
        {
            break MISSING_BLOCK_LABEL_1338;
        }
        if (atype.length != 0)
        {
            break MISSING_BLOCK_LABEL_1338;
        }
        type1 = type;
          goto _L31
        codeattr2.emitLoad(codeattr2.getArg(1));
        codeattr2.emitPutField(field);
          goto _L32
        Vector vector;
        vector = new Vector();
        getImplMethods(this.type, s2, atype, vector);
        if (vector.size() == 1)
        {
            break MISSING_BLOCK_LABEL_1235;
        }
        CodeAttr codeattr1;
        Variable variable;
        String s3;
        if (vector.size() == 0)
        {
            s3 = "missing implementation for ";
        } else
        {
            s3 = "ambiguous implementation for ";
        }
        compilation.error('e', (new StringBuilder()).append(s3).append(method2).toString());
        break MISSING_BLOCK_LABEL_1338;
        codeattr1 = instanceType.addMethod(s2, 1, atype, type).startCode();
        variable = codeattr1.getCurrentScope().firstVar();
_L34:
        if (variable == null)
        {
            break; /* Loop/switch isn't completed */
        }
        codeattr1.emitLoad(variable);
        variable = variable.nextVar();
        if (true) goto _L34; else goto _L33
_L33:
        codeattr1.emitInvokeStatic((Method)vector.elementAt(0));
        codeattr1.emitReturn();
        break MISSING_BLOCK_LABEL_1338;
_L23:
        generateApplyMethods(compilation);
        compilation.curLambda = lambdaexp1;
        compilation.curClass = classtype;
        compilation.method = method;
        return classtype1;
_L21:
        l = 0;
          goto _L35
        l++;
          goto _L35
    }

    public void compilePushClass(Compilation compilation, Target target)
    {
        ClassType classtype = type;
        CodeAttr codeattr = compilation.getCode();
        compilation.loadClassRef(classtype);
        boolean flag = getNeedsClosureEnv();
        if (isSimple() && !flag)
        {
            return;
        }
        ClassType classtype1;
        int i;
        Type atype[];
        if (isMakingClassPair() || flag)
        {
            ClassType classtype2;
            if (classtype == instanceType)
            {
                codeattr.emitDup(instanceType);
            } else
            {
                compilation.loadClassRef(instanceType);
            }
            classtype1 = ClassType.make("gnu.expr.PairClassType");
            if (flag)
            {
                i = 3;
            } else
            {
                i = 2;
            }
        } else
        {
            classtype1 = ClassType.make("gnu.bytecode.Type");
            i = 1;
        }
        atype = new Type[i];
        if (flag)
        {
            getOwningLambda().loadHeapFrame(compilation);
            i--;
            atype[i] = Type.pointer_type;
        }
        classtype2 = ClassType.make("java.lang.Class");
        while (--i >= 0) 
        {
            atype[i] = classtype2;
        }
        codeattr.emitInvokeStatic(classtype1.addMethod("make", atype, classtype1, 9));
        target.compileFromStack(compilation, classtype1);
    }

    public Field compileSetField(Compilation compilation)
    {
        return (new ClassInitializer(this, compilation)).field;
    }

    public void declareParts(Compilation compilation)
    {
        if (!partsDeclared)
        {
            partsDeclared = true;
            Hashtable hashtable = new Hashtable();
            Declaration declaration = firstDecl();
            while (declaration != null) 
            {
                if (declaration.getCanRead())
                {
                    int i = declaration.getAccessFlags((short)1);
                    if (declaration.getFlag(2048L))
                    {
                        i |= 8;
                    }
                    if (isMakingClassPair())
                    {
                        int j = i | 0x400;
                        Type type = declaration.getType().getImplementationType();
                        this.type.addMethod(slotToMethodName("get", declaration.getName()), j, Type.typeArray0, type);
                        Type atype[] = {
                            type
                        };
                        this.type.addMethod(slotToMethodName("set", declaration.getName()), j, atype, Type.voidType);
                    } else
                    {
                        String s = Compilation.mangleNameIfNeeded(declaration.getName());
                        declaration.field = instanceType.addField(s, declaration.getType(), i);
                        declaration.setSimple(false);
                        Declaration declaration1 = (Declaration)hashtable.get(s);
                        if (declaration1 != null)
                        {
                            duplicateDeclarationError(declaration1, declaration, compilation);
                        }
                        hashtable.put(s, declaration);
                    }
                }
                declaration = declaration.nextDecl();
            }
            for (LambdaExp lambdaexp = firstChild; lambdaexp != null; lambdaexp = lambdaexp.nextSibling)
            {
                if (lambdaexp.isAbstract())
                {
                    setFlag(16384);
                }
                if ("*init*".equals(lambdaexp.getName()))
                {
                    explicitInit = true;
                    if (lambdaexp.isAbstract())
                    {
                        compilation.error('e', "*init* method cannot be abstract", lambdaexp);
                    }
                    if (this.type instanceof PairClassType)
                    {
                        compilation.error('e', "'*init*' methods only supported for simple classes");
                    }
                }
                lambdaexp.outer = this;
                if (lambdaexp != initMethod && lambdaexp != clinitMethod && lambdaexp.nameDecl != null && !lambdaexp.nameDecl.getFlag(2048L) || !isMakingClassPair())
                {
                    lambdaexp.addMethodFor(this.type, compilation, null);
                }
                if (isMakingClassPair())
                {
                    lambdaexp.addMethodFor(instanceType, compilation, this.type);
                }
            }

            if (!explicitInit && !instanceType.isInterface())
            {
                Compilation.getConstructor(instanceType, this);
            }
            if (isAbstract())
            {
                instanceType.setModifiers(0x400 | instanceType.getModifiers());
            }
            if (nameDecl != null)
            {
                instanceType.setModifiers(-2 & instanceType.getModifiers() | nameDecl.getAccessFlags((short)1));
                return;
            }
        }
    }

    public ClassType getClassType()
    {
        return type;
    }

    protected ClassType getCompiledClassType(Compilation compilation)
    {
        return type;
    }

    public Type getType()
    {
        if (simple)
        {
            return Compilation.typeClass;
        } else
        {
            return Compilation.typeClassType;
        }
    }

    public final boolean isAbstract()
    {
        return getFlag(16384);
    }

    public boolean isMakingClassPair()
    {
        return type != instanceType;
    }

    public boolean isSimple()
    {
        return simple;
    }

    protected boolean mustCompile()
    {
        return true;
    }

    public void print(OutPort outport)
    {
        outport.startLogicalBlock((new StringBuilder()).append("(").append(getExpClassName()).append("/").toString(), ")", 2);
        Object obj = getSymbol();
        if (obj != null)
        {
            outport.print(obj);
            outport.print('/');
        }
        outport.print(id);
        outport.print("/fl:");
        outport.print(Integer.toHexString(flags));
        if (supers.length > 0)
        {
            outport.writeSpaceFill();
            outport.startLogicalBlock("supers:", "", 2);
            for (int j = 0; j < supers.length; j++)
            {
                supers[j].print(outport);
                outport.writeSpaceFill();
            }

            outport.endLogicalBlock("");
        }
        outport.print('(');
        int i = 0;
        Declaration declaration;
        if (keywords != null)
        {
            int _tmp = keywords.length;
        }
        for (declaration = firstDecl(); declaration != null; declaration = declaration.nextDecl())
        {
            if (i > 0)
            {
                outport.print(' ');
            }
            declaration.printInfo(outport);
            i++;
        }

        outport.print(") ");
        for (LambdaExp lambdaexp = firstChild; lambdaexp != null; lambdaexp = lambdaexp.nextSibling)
        {
            outport.writeBreakLinear();
            lambdaexp.print(outport);
        }

        if (body != null)
        {
            outport.writeBreakLinear();
            body.print(outport);
        }
        outport.endLogicalBlock(")");
    }

    public void setSimple(boolean flag)
    {
        simple = flag;
    }

    public void setTypes(Compilation compilation)
    {
        int i;
        ClassType aclasstype[];
        ClassType classtype;
        int j;
        int k;
        Type type;
        int j3;
        if (supers == null)
        {
            i = 0;
        } else
        {
            i = supers.length;
        }
        aclasstype = new ClassType[i];
        classtype = null;
        j = 0;
        k = 0;
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_232;
        }
        type = Language.getDefaultLanguage().getTypeFor(supers[j]);
        if (type instanceof ClassType)
        {
            break; /* Loop/switch isn't completed */
        }
        compilation.setLine(supers[j]);
        compilation.error('e', "invalid super type");
        j3 = k;
_L4:
        j++;
        k = j3;
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_23;
_L1:
        ClassType classtype2 = (ClassType)type;
        int k3 = classtype2.getModifiers();
        int i3 = k3;
_L5:
        RuntimeException runtimeexception;
        if ((i3 & 0x200) == 0)
        {
            if (k < j)
            {
                compilation.error('e', (new StringBuilder()).append("duplicate superclass for ").append(this).toString());
            }
            classtype = classtype2;
            superClassIndex = j;
            j3 = k;
        } else
        {
            j3 = k + 1;
            aclasstype[k] = classtype2;
        }
        if (true) goto _L4; else goto _L3
_L3:
        runtimeexception;
        i3 = 0;
        if (compilation != null)
        {
            compilation.error('e', (new StringBuilder()).append("unknown super-type ").append(classtype2.getName()).toString());
            i3 = 0;
        }
          goto _L5
        String s;
        StringBuffer stringbuffer;
        int i1;
        int j1;
        String s1;
        if (classtype != null && (0x8000 & flags) != 0)
        {
            compilation.error('e', "cannot be interface since has superclass");
        }
        ClassType classtype1;
        ClassType aclasstype1[];
        if (!simple && classtype == null && (0x10000 & flags) == 0 && (getFlag(0x20000) || nameDecl != null && nameDecl.isPublic()))
        {
            PairClassType pairclasstype = new PairClassType();
            this.type = pairclasstype;
            pairclasstype.setInterface(true);
            pairclasstype.instanceType = instanceType;
            ClassType aclasstype2[] = new ClassType[1];
            aclasstype2[0] = this.type;
            instanceType.setSuper(Type.pointer_type);
            instanceType.setInterfaces(aclasstype2);
        } else
        if (getFlag(32768))
        {
            instanceType.setInterface(true);
        }
        classtype1 = this.type;
        if (classtype == null)
        {
            classtype = Type.pointer_type;
        }
        classtype1.setSuper(classtype);
        if (k == i)
        {
            aclasstype1 = aclasstype;
        } else
        {
            aclasstype1 = new ClassType[k];
            System.arraycopy(aclasstype, 0, aclasstype1, 0, k);
        }
        this.type.setInterfaces(aclasstype1);
        if (this.type.getName() != null) goto _L7; else goto _L6
_L6:
        if (classNameSpecifier != null)
        {
            s = classNameSpecifier;
        } else
        {
            s = getName();
            if (s != null)
            {
                int l = s.length();
                if (l > 2 && s.charAt(0) == '<' && s.charAt(l - 1) == '>')
                {
                    s = s.substring(1, l - 1);
                }
            }
        }
        if (s != null) goto _L9; else goto _L8
_L8:
        stringbuffer = new StringBuffer(100);
        compilation.getModule().classFor(compilation);
        stringbuffer.append(compilation.mainClass.getName());
        stringbuffer.append('$');
        i1 = stringbuffer.length();
        j1 = 0;
_L12:
        stringbuffer.append(j1);
        s1 = stringbuffer.toString();
        if (compilation.findNamedClass(s1) != null) goto _L11; else goto _L10
_L10:
        this.type.setName(s1);
        compilation.addClass(this.type);
        if (isMakingClassPair())
        {
            instanceType.setName((new StringBuilder()).append(this.type.getName()).append("$class").toString());
            compilation.addClass(instanceType);
        }
_L7:
        return;
_L11:
        stringbuffer.setLength(i1);
        j1++;
          goto _L12
_L9:
label0:
        {
            if (isSimple() && !(this instanceof ObjectExp))
            {
                break label0;
            }
            s1 = compilation.generateClassName(s);
        }
          goto _L10
        int k1;
        StringBuffer stringbuffer1;
        k1 = 0;
        stringbuffer1 = new StringBuffer(100);
_L13:
label1:
        {
            int l1 = s.indexOf('.', k1);
            if (l1 >= 0)
            {
                break label1;
            }
            if (k1 == 0)
            {
                int i2;
                int k2;
                String s2;
                int l2;
                if (compilation.mainClass == null)
                {
                    s2 = null;
                } else
                {
                    s2 = compilation.mainClass.getName();
                }
                if (s2 == null)
                {
                    l2 = -1;
                } else
                {
                    l2 = s2.lastIndexOf('.');
                }
                if (l2 > 0)
                {
                    stringbuffer1.append(s2.substring(0, l2 + 1));
                } else
                if (compilation.classPrefix != null)
                {
                    stringbuffer1.append(compilation.classPrefix);
                }
            } else
            if (k1 == 1)
            {
                int j2 = s.length();
                if (k1 < j2)
                {
                    stringbuffer1.setLength(0);
                    stringbuffer1.append(compilation.mainClass.getName());
                    stringbuffer1.append('$');
                }
            }
            k2 = s.length();
            if (k1 < k2)
            {
                stringbuffer1.append(Compilation.mangleNameIfNeeded(s.substring(k1)));
            }
            s1 = stringbuffer1.toString();
        }
          goto _L10
        stringbuffer1.append(Compilation.mangleNameIfNeeded(s.substring(k1, l1)));
        k1 = l1 + 1;
        i2 = s.length();
        if (k1 < i2)
        {
            stringbuffer1.append('.');
        }
          goto _L13
    }

    protected Object visit(ExpVisitor expvisitor, Object obj)
    {
        Compilation compilation;
        ClassType classtype;
        compilation = expvisitor.getCompilation();
        if (compilation == null)
        {
            return expvisitor.visitClassExp(this, obj);
        }
        classtype = compilation.curClass;
        Object obj1;
        compilation.curClass = type;
        obj1 = expvisitor.visitClassExp(this, obj);
        compilation.curClass = classtype;
        return obj1;
        Exception exception;
        exception;
        compilation.curClass = classtype;
        throw exception;
    }

    protected void visitChildren(ExpVisitor expvisitor, Object obj)
    {
        LambdaExp lambdaexp;
        lambdaexp = expvisitor.currentLambda;
        expvisitor.currentLambda = this;
        supers = expvisitor.visitExps(supers, supers.length, obj);
        LambdaExp lambdaexp1 = firstChild;
_L2:
        if (lambdaexp1 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        Declaration declaration;
        if (expvisitor.exitValue != null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (instanceType == null)
        {
            break MISSING_BLOCK_LABEL_82;
        }
        declaration = lambdaexp1.firstDecl();
        if (declaration == null)
        {
            break MISSING_BLOCK_LABEL_82;
        }
        if (declaration.isThisParameter())
        {
            declaration.setType(type);
        }
        expvisitor.visitLambdaExp(lambdaexp1, obj);
        lambdaexp1 = lambdaexp1.nextSibling;
        if (true) goto _L2; else goto _L1
_L1:
        expvisitor.currentLambda = lambdaexp;
        return;
        Exception exception;
        exception;
        expvisitor.currentLambda = lambdaexp;
        throw exception;
    }
}
