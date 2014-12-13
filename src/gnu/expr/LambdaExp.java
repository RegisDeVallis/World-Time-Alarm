// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.ExceptionsAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Filter;
import gnu.bytecode.LocalVarsAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.Values;
import gnu.mapping.WrappedException;
import gnu.mapping.WrongArguments;
import java.util.Set;
import java.util.Vector;

// Referenced classes of package gnu.expr:
//            ScopeExp, ApplyExp, Expression, Compilation, 
//            ModuleExp, Declaration, ClassExp, ReferenceExp, 
//            QuoteExp, Closure, IgnoreTarget, ProcInitializer, 
//            Target, StackTarget, ConsumerTarget, CheckedTarget, 
//            Special, Language, InlineCalls, PrimProcedure, 
//            ThisExp, ExpVisitor, Initializer, Keyword

public class LambdaExp extends ScopeExp
{

    public static final int ATTEMPT_INLINE = 4096;
    static final int CANNOT_INLINE = 32;
    static final int CAN_CALL = 4;
    static final int CAN_READ = 2;
    static final int CLASS_METHOD = 64;
    static final int DEFAULT_CAPTURES_ARG = 512;
    static final int IMPORTS_LEX_VARS = 8;
    static final int INLINE_ONLY = 8192;
    static final int METHODS_COMPILED = 128;
    static final int NEEDS_STATIC_LINK = 16;
    protected static final int NEXT_AVAIL_FLAG = 16384;
    public static final int NO_FIELD = 256;
    public static final int OVERLOADABLE_FIELD = 2048;
    public static final int SEQUENCE_RESULT = 1024;
    static Method searchForKeywordMethod3;
    static Method searchForKeywordMethod4;
    static final ApplyExp unknownContinuation = new ApplyExp((Expression)null, null);
    Vector applyMethods;
    Variable argsArray;
    public Expression body;
    Declaration capturedVars;
    Variable closureEnv;
    public Field closureEnvField;
    public Expression defaultArgs[];
    private Declaration firstArgsArrayArg;
    public LambdaExp firstChild;
    Variable heapFrame;
    Initializer initChain;
    public LambdaExp inlineHome;
    public Keyword keywords[];
    public int max_args;
    public int min_args;
    public Declaration nameDecl;
    public LambdaExp nextSibling;
    Method primBodyMethods[];
    Method primMethods[];
    Object properties[];
    public Expression returnContinuation;
    public Type returnType;
    int selectorValue;
    public Field staticLinkField;
    Set tailCallers;
    Procedure thisValue;
    Variable thisVariable;
    Expression throwsSpecification[];
    ClassType type;

    public LambdaExp()
    {
        type = Compilation.typeProcedure;
    }

    public LambdaExp(int i)
    {
        type = Compilation.typeProcedure;
        min_args = i;
        max_args = i;
    }

    public LambdaExp(Expression expression)
    {
        type = Compilation.typeProcedure;
        body = expression;
    }

    final void addApplyMethod(Compilation compilation, Field field)
    {
        Object obj = this;
        if (field == null || !field.getStaticFlag()) goto _L2; else goto _L1
_L1:
        obj = compilation.getModule();
_L4:
        if (((LambdaExp) (obj)).applyMethods == null)
        {
            obj.applyMethods = new Vector();
        }
        ((LambdaExp) (obj)).applyMethods.addElement(this);
        return;
_L2:
        do
        {
            obj = ((LambdaExp) (obj)).outerLambda();
        } while (!(obj instanceof ModuleExp) && ((LambdaExp) (obj)).heapFrame == null);
        if (!((LambdaExp) (obj)).getHeapFrameType().getSuperclass().isSubtype(Compilation.typeModuleBody))
        {
            obj = compilation.getModule();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    void addMethodFor(ClassType classtype, Compilation compilation, ObjectType objecttype)
    {
        LambdaExp lambdaexp;
        int i;
        int j;
        int k;
        Method amethod[];
        StringBuffer stringbuffer;
        int i1;
        boolean flag2;
        Method amethod1[];
        int l4;
        Declaration declaration2;
        String s = getName();
        lambdaexp = outerLambda();
        boolean flag;
        int l;
        boolean flag1;
        int k1;
        int i2;
        int i3;
        int k4;
        if (keywords == null)
        {
            i = 0;
        } else
        {
            i = keywords.length;
        }
        if (defaultArgs == null)
        {
            j = 0;
        } else
        {
            j = defaultArgs.length - i;
        }
        if ((0x200 & flags) != 0)
        {
            k = 0;
        } else
        {
            k = j;
        }
        if (max_args < 0 || k + min_args < max_args)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        amethod = new Method[k + 1];
        primBodyMethods = amethod;
        if (primMethods == null)
        {
            primMethods = amethod;
        }
        l = 0;
        if (nameDecl != null && nameDecl.getFlag(4096L))
        {
            flag1 = false;
        } else
        if (nameDecl != null && nameDecl.getFlag(2048L))
        {
            flag1 = true;
            l = 0;
        } else
        if (isClassMethod())
        {
            if (lambdaexp instanceof ClassExp)
            {
                ClassExp classexp = (ClassExp)lambdaexp;
                if (classexp.isMakingClassPair() && objecttype != null)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (this == classexp.initMethod)
                {
                    l = 73;
                } else
                {
                    LambdaExp lambdaexp1 = classexp.clinitMethod;
                    l = 0;
                    if (this == lambdaexp1)
                    {
                        l = 67;
                        flag1 = true;
                    }
                }
            } else
            {
                l = 0;
                flag1 = false;
            }
        } else
        if (thisVariable != null || objecttype == classtype)
        {
            l = 0;
            flag1 = false;
        } else
        if (nameDecl != null && (nameDecl.context instanceof ModuleExp))
        {
            ModuleExp moduleexp = (ModuleExp)nameDecl.context;
            if (moduleexp.getSuperType() == null && moduleexp.getInterfaces() == null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            l = 0;
        } else
        {
            flag1 = true;
            l = 0;
        }
        stringbuffer = new StringBuffer(60);
        if (flag1)
        {
            i1 = 8;
        } else
        {
            i1 = 0;
        }
        if (nameDecl != null)
        {
            if (nameDecl.needsExternalAccess())
            {
                i1 |= 1;
            } else
            {
                short word0;
                if (nameDecl.isPrivate())
                {
                    word0 = 0;
                } else
                {
                    word0 = 1;
                }
                if (isClassMethod())
                {
                    word0 = nameDecl.getAccessFlags(word0);
                }
                i1 |= word0;
            }
        }
        if (!lambdaexp.isModuleBody() && !(lambdaexp instanceof ClassExp) || s == null)
        {
            stringbuffer.append("lambda");
            int j5 = 1 + compilation.method_counter;
            compilation.method_counter = j5;
            stringbuffer.append(j5);
        }
        if (l == 67)
        {
            stringbuffer.append("<clinit>");
        } else
        if (getSymbol() != null)
        {
            stringbuffer.append(Compilation.mangleName(s));
        }
        if (getFlag(1024))
        {
            stringbuffer.append("$C");
        }
        if (getCallConvention() >= 2 && l == 0)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (l != 0)
        {
            if (flag1)
            {
                i1 = 1 + (i1 & -3);
            } else
            {
                i1 = 2 + (i1 & 2);
            }
        }
        if (classtype.isInterface() || isAbstract())
        {
            i1 = 0x400 | i1;
        }
        if (!isClassMethod() || !(lambdaexp instanceof ClassExp) || min_args != max_args) goto _L2; else goto _L1
_L1:
        amethod1 = null;
        l4 = 0;
        declaration2 = firstDecl();
_L11:
        if (declaration2 != null) goto _L4; else goto _L3
_L3:
        if (returnType == null) goto _L5; else goto _L2
_L4:
        if (!declaration2.isThisParameter()) goto _L7; else goto _L6
_L6:
        l4--;
_L9:
        declaration2 = declaration2.nextDecl();
        l4++;
        continue; /* Loop/switch isn't completed */
_L7:
        if (declaration2.getFlag(8192L))
        {
            continue; /* Loop/switch isn't completed */
        }
_L5:
        if (amethod1 == null)
        {
            amethod1 = classtype.getMethods(new Filter() {

                final LambdaExp this$0;
                final String val$mangled;

                public boolean select(Object obj3)
                {
                    for (Method method2 = (Method)obj3; !method2.getName().equals(mangled) || method2.getParameterTypes().length != min_args;)
                    {
                        return false;
                    }

                    return true;
                }

            
            {
                this$0 = LambdaExp.this;
                mangled = s;
                super();
            }
            }, 2);
        }
        type1 = null;
        int i5 = amethod1.length;
        Type type2;
label0:
        do
        {
            while (--i5 >= 0) 
            {
                Method method1 = amethod1[i5];
                if (declaration2 == null)
                {
                    type2 = method1.getReturnType();
                } else
                {
                    type2 = method1.getParameterTypes()[l4];
                }
                if (type1 != null)
                {
                    continue label0;
                }
                type1 = type2;
            }
            break; /* Loop/switch isn't completed */
        } while (type2 == type1);
        if (declaration2 == null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (true) goto _L9; else goto _L8
_L8:
        if (type1 != null)
        {
            if (declaration2 != null)
            {
                declaration2.setType(type1);
            } else
            {
                setCoercedReturnType(type1);
            }
        }
        if (declaration2 != null) goto _L9; else goto _L2
_L2:
        Object obj;
        int j1;
        int l1;
        if (getFlag(1024) || getCallConvention() >= 2)
        {
            obj = Type.voidType;
        } else
        {
            obj = getReturnType().getImplementationType();
        }
        if (objecttype != null && objecttype != classtype)
        {
            j1 = 1;
        } else
        {
            j1 = 0;
        }
        k1 = getCallConvention();
        l1 = 0;
        if (k1 >= 2)
        {
            l1 = 0;
            if (l == 0)
            {
                l1 = 1;
            }
        }
        i2 = stringbuffer.length();
        for (int j2 = 0; j2 <= k; j2++)
        {
            stringbuffer.setLength(i2);
            int k2 = j2 + min_args;
            int l2 = k2;
            if (j2 == k && flag)
            {
                l2++;
            }
            Type atype[] = new Type[l1 + (j1 + l2)];
            if (j1 > 0)
            {
                atype[0] = objecttype;
            }
            Declaration declaration = firstDecl();
            if (declaration != null && declaration.isThisParameter())
            {
                declaration = declaration.nextDecl();
            }
            Type type1;
            for (i3 = 0; i3 < k2; i3 = k4)
            {
                k4 = i3 + 1;
                atype[j1 + i3] = declaration.getType().getImplementationType();
                declaration = declaration.nextDecl();
            }

            Method method;
            ClassType aclasstype[];
label1:
            {
label2:
                {
                    {
                        if (l1 != 0)
                        {
                            atype[-1 + atype.length] = Compilation.typeCallContext;
                        }
                        boolean flag3;
                        String s1;
                        if (k2 < l2)
                        {
                            Object obj2 = declaration.getType();
                            String s3 = ((Type) (obj2)).getName();
                            int j3;
                            int k3;
                            int j4;
                            byte byte0;
                            if (classtype.getClassfileVersion() >= 0x310000 && (obj2 instanceof ArrayType))
                            {
                                i1 = 0x80 | i1;
                            } else
                            {
                                stringbuffer.append("$V");
                            }
                            if (i > 0 || k < j || !"gnu.lists.LList".equals(s3) && !(obj2 instanceof ArrayType))
                            {
                                obj2 = Compilation.objArrayType;
                                argsArray = new Variable("argsArray", Compilation.objArrayType);
                                argsArray.setParameter(true);
                            }
                            firstArgsArrayArg = declaration;
                            j4 = atype.length;
                            if (flag2)
                            {
                                byte0 = 2;
                            } else
                            {
                                byte0 = 1;
                            }
                            atype[j4 - byte0] = ((Type) (obj2));
                        }
                        if (flag2)
                        {
                            stringbuffer.append("$X");
                        }
                        if ((lambdaexp instanceof ClassExp) || (lambdaexp instanceof ModuleExp) && ((ModuleExp)lambdaexp).getFlag(0x20000))
                        {
                            flag3 = true;
                        } else
                        {
                            flag3 = false;
                        }
                        s1 = stringbuffer.toString();
                        j3 = 0;
                        k3 = stringbuffer.length();
                        do
                        {
label3:
                            {
                                int l3;
                                int i4;
                                Expression expression1;
                                for (ClassType classtype1 = classtype; classtype1 == null; classtype1 = classtype1.getSuperclass())
                                {
                                    break label2;
                                }

                                if (classtype1.getDeclaredMethod(s1, atype) == null)
                                {
                                    break label3;
                                }
                                stringbuffer.setLength(k3);
                                stringbuffer.append('$');
                                j3++;
                                stringbuffer.append(j3);
                                s1 = stringbuffer.toString();
                            }
                        } while (true);
                    }
                    if (!flag3)
                    {
                        break label1;
                    }
                }
                method = classtype.addMethod(s1, atype, ((Type) (obj)), i1);
                amethod[j2] = method;
                if (throwsSpecification == null || throwsSpecification.length <= 0)
                {
                    continue;
                }
                l3 = throwsSpecification.length;
                aclasstype = new ClassType[l3];
                i4 = 0;
                while (i4 < l3) 
                {
                    Expression expression = throwsSpecification[i4];
                    String s2 = null;
                    ClassType classtype2;
                    if (expression instanceof ReferenceExp)
                    {
                        ReferenceExp referenceexp = (ReferenceExp)expression;
                        Declaration declaration1 = referenceexp.getBinding();
                        if (declaration1 != null)
                        {
                            expression1 = declaration1.getValue();
                            if (expression1 instanceof ClassExp)
                            {
                                classtype2 = ((ClassExp)expression1).getCompiledClassType(compilation);
                            } else
                            {
                                s2 = (new StringBuilder()).append("throws specification ").append(declaration1.getName()).append(" has non-class lexical binding").toString();
                                classtype2 = null;
                            }
                        } else
                        {
                            s2 = (new StringBuilder()).append("unknown class ").append(referenceexp.getName()).toString();
                            classtype2 = null;
                        }
                    } else
                    {
                        boolean flag4 = expression instanceof QuoteExp;
                        classtype2 = null;
                        s2 = null;
                        if (flag4)
                        {
                            Object obj1 = ((QuoteExp)expression).getValue();
                            if (obj1 instanceof Class)
                            {
                                obj1 = Type.make((Class)obj1);
                            }
                            boolean flag5 = obj1 instanceof ClassType;
                            classtype2 = null;
                            if (flag5)
                            {
                                classtype2 = (ClassType)obj1;
                            }
                            s2 = null;
                            if (classtype2 != null)
                            {
                                boolean flag6 = classtype2.isSubtype(Type.javalangThrowableType);
                                s2 = null;
                                if (!flag6)
                                {
                                    s2 = (new StringBuilder()).append(classtype2.getName()).append(" does not extend Throwable").toString();
                                }
                            }
                        }
                    }
                    if (classtype2 == null && s2 == null)
                    {
                        s2 = "invalid throws specification";
                    }
                    if (s2 != null)
                    {
                        compilation.error('e', s2, expression);
                        classtype2 = Type.javalangThrowableType;
                    }
                    aclasstype[i4] = classtype2;
                    i4++;
                }
                break MISSING_BLOCK_LABEL_1774;
            }
            (new ExceptionsAttr(method)).setExceptions(aclasstype);
        }

        return;
        if (true) goto _L11; else goto _L10
_L10:
    }

    void addMethodFor(Compilation compilation, ObjectType objecttype)
    {
        Object obj;
        for (obj = this; obj != null && !(obj instanceof ClassExp); obj = ((ScopeExp) (obj)).outer) { }
        ClassType classtype;
        if (obj != null)
        {
            classtype = ((ClassExp)obj).instanceType;
        } else
        {
            classtype = getOwningLambda().getHeapFrameType();
        }
        addMethodFor(classtype, compilation, objecttype);
    }

    public void allocChildClasses(Compilation compilation)
    {
        Declaration declaration;
        Method method = getMainMethod();
        if (method != null && !method.getStaticFlag())
        {
            declareThis(method.getDeclaringClass());
        }
        declaration = firstDecl();
_L2:
        if (declaration == firstArgsArrayArg && argsArray != null)
        {
            getVarScope().addVariable(argsArray);
        }
        if (!getInlineOnly() && getCallConvention() >= 2 && (firstArgsArrayArg != null ? argsArray == null ? declaration == firstArgsArrayArg.nextDecl() : declaration == firstArgsArrayArg : declaration == null))
        {
            getVarScope().addVariable(null, Compilation.typeCallContext, "$ctx").setParameter(true);
        }
        if (declaration == null)
        {
            declareClosureEnv();
            allocFrame(compilation);
            allocChildMethods(compilation);
            return;
        }
        if (declaration.var == null && (!getInlineOnly() || !declaration.ignorable()))
        {
            break; /* Loop/switch isn't completed */
        }
_L3:
        declaration = declaration.nextDecl();
        if (true) goto _L2; else goto _L1
_L1:
        if (declaration.isSimple() && !declaration.isIndirectBinding())
        {
            declaration.allocateVariable(null);
        } else
        {
            String s = Compilation.mangleName(declaration.getName()).intern();
            Type type1 = declaration.getType().getImplementationType();
            Variable variable = getVarScope().addVariable(null, type1, s);
            declaration.var = variable;
            variable.setParameter(true);
        }
          goto _L3
        if (true) goto _L2; else goto _L4
_L4:
    }

    void allocChildMethods(Compilation compilation)
    {
        LambdaExp lambdaexp = firstChild;
        do
        {
            while (lambdaexp != null) 
            {
                if (!lambdaexp.isClassGenerated() && !lambdaexp.getInlineOnly() && lambdaexp.nameDecl != null)
                {
                    lambdaexp.allocMethod(this, compilation);
                }
                if (lambdaexp instanceof ClassExp)
                {
                    ClassExp classexp = (ClassExp)lambdaexp;
                    if (classexp.getNeedsClosureEnv())
                    {
                        ClassType classtype;
                        Field field;
                        if ((this instanceof ModuleExp) || (this instanceof ClassExp))
                        {
                            classtype = (ClassType)getType();
                        } else
                        {
                            Variable variable;
                            if (heapFrame != null)
                            {
                                variable = heapFrame;
                            } else
                            {
                                variable = closureEnv;
                            }
                            classtype = (ClassType)variable.getType();
                        }
                        field = classexp.instanceType.setOuterLink(classtype);
                        classexp.staticLinkField = field;
                        classexp.closureEnvField = field;
                    }
                }
                lambdaexp = lambdaexp.nextSibling;
            }
            return;
        } while (true);
    }

    Field allocFieldFor(Compilation compilation)
    {
        if (nameDecl == null || nameDecl.field == null) goto _L2; else goto _L1
_L1:
        Field field = nameDecl.field;
_L7:
        return field;
_L2:
        boolean flag;
        String s1;
        int i;
        int k;
        int l;
        flag = getNeedsClosureEnv();
        ClassType classtype;
        String s;
        boolean flag1;
        String s2;
        StringBuilder stringbuilder1;
        if (flag)
        {
            classtype = getOwningLambda().getHeapFrameType();
        } else
        {
            classtype = compilation.mainClass;
        }
        s = getName();
        if (s == null)
        {
            s1 = "lambda";
        } else
        {
            s1 = Compilation.mangleNameIfNeeded(s);
        }
        i = 16;
        if (nameDecl == null || !(nameDecl.context instanceof ModuleExp)) goto _L4; else goto _L3
_L3:
        flag1 = nameDecl.needsExternalAccess();
        if (flag1)
        {
            s1 = (new StringBuilder()).append("$Prvt$").append(s1).toString();
        }
        if (nameDecl.getFlag(2048L))
        {
            i |= 8;
            if (!((ModuleExp)nameDecl.context).isStatic())
            {
                i &= 0xffffffef;
            }
        }
        if (!nameDecl.isPrivate() || flag1 || compilation.immediate)
        {
            i |= 1;
        }
        if ((0x800 & flags) == 0) goto _L6; else goto _L5
_L5:
        s2 = s1;
        if (min_args == max_args)
        {
            k = min_args;
        } else
        {
            k = 1;
        }
_L8:
        stringbuilder1 = (new StringBuilder()).append(s2).append('$');
        l = k + 1;
        s1 = stringbuilder1.append(k).toString();
        if (classtype.getDeclaredField(s1) != null)
        {
            break MISSING_BLOCK_LABEL_387;
        }
_L6:
        field = classtype.addField(s1, Compilation.typeModuleMethod, i);
        if (nameDecl != null)
        {
            nameDecl.field = field;
            return field;
        }
          goto _L7
_L4:
        StringBuilder stringbuilder = (new StringBuilder()).append(s1).append("$Fn");
        int j = 1 + compilation.localFieldIndex;
        compilation.localFieldIndex = j;
        s1 = stringbuilder.append(j).toString();
        if (!flag)
        {
            i |= 8;
        }
          goto _L6
        k = l;
          goto _L8
    }

    public void allocFrame(Compilation compilation)
    {
        if (heapFrame != null)
        {
            ClassType classtype;
            if ((this instanceof ModuleExp) || (this instanceof ClassExp))
            {
                classtype = getCompiledClassType(compilation);
            } else
            {
                classtype = new ClassType(compilation.generateClassName("frame"));
                classtype.setSuper(compilation.getModuleType());
                compilation.addClass(classtype);
            }
            heapFrame.setType(classtype);
        }
    }

    void allocMethod(LambdaExp lambdaexp, Compilation compilation)
    {
        Object obj;
        if (!getNeedsClosureEnv())
        {
            obj = null;
        } else
        if ((lambdaexp instanceof ClassExp) || (lambdaexp instanceof ModuleExp))
        {
            obj = lambdaexp.getCompiledClassType(compilation);
        } else
        {
            LambdaExp lambdaexp1;
            for (lambdaexp1 = lambdaexp; lambdaexp1.heapFrame == null; lambdaexp1 = lambdaexp1.outerLambda()) { }
            obj = (ClassType)lambdaexp1.heapFrame.getType();
        }
        addMethodFor(compilation, ((ObjectType) (obj)));
    }

    void allocParameters(Compilation compilation)
    {
        CodeAttr codeattr = compilation.getCode();
        codeattr.locals.enterScope(getVarScope());
        int i = getLineNumber();
        if (i > 0)
        {
            codeattr.putLineNumber(getFileName(), i);
        }
        if (heapFrame != null)
        {
            heapFrame.allocateLocal(codeattr);
        }
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        setIndexes();
        callcontext.writeValue(new Closure(this, callcontext));
    }

    public void capture(Declaration declaration)
    {
        if (declaration.isSimple())
        {
            if (capturedVars == null && !declaration.isStatic() && !(this instanceof ModuleExp) && !(this instanceof ClassExp))
            {
                heapFrame = new Variable("heapFrame");
            }
            declaration.setSimple(false);
            if (!declaration.isPublic())
            {
                declaration.nextCapturedVar = capturedVars;
                capturedVars = declaration;
            }
        }
    }

    public void compile(Compilation compilation, Target target)
    {
        if (target instanceof IgnoreTarget)
        {
            return;
        }
        CodeAttr codeattr = compilation.getCode();
        LambdaExp lambdaexp = outerLambda();
        ClassType classtype = Compilation.typeModuleMethod;
        if ((0x100 & flags) != 0 || compilation.immediate && (lambdaexp instanceof ModuleExp))
        {
            if (primMethods == null)
            {
                allocMethod(outerLambda(), compilation);
            }
            compileAsMethod(compilation);
            addApplyMethod(compilation, null);
            ProcInitializer.emitLoadModuleMethod(this, compilation);
        } else
        {
            Field field = compileSetField(compilation);
            if (field.getStaticFlag())
            {
                codeattr.emitGetStatic(field);
            } else
            {
                LambdaExp lambdaexp1 = compilation.curLambda;
                Variable variable;
                if (lambdaexp1.heapFrame != null)
                {
                    variable = lambdaexp1.heapFrame;
                } else
                {
                    variable = lambdaexp1.closureEnv;
                }
                codeattr.emitLoad(variable);
                codeattr.emitGetField(field);
            }
        }
        target.compileFromStack(compilation, classtype);
    }

    void compileAsMethod(Compilation compilation)
    {
        if ((0x80 & flags) == 0 && !isAbstract())
        {
            flags = 0x80 | flags;
            if (primMethods != null)
            {
                Method method = compilation.method;
                LambdaExp lambdaexp = compilation.curLambda;
                compilation.curLambda = this;
                boolean flag = primMethods[0].getStaticFlag();
                int i = -1 + primMethods.length;
                Type type1 = restArgType();
                long al[] = null;
                if (i > 0)
                {
                    al = new long[i + min_args];
                    int l1 = 0;
                    Declaration declaration2 = firstDecl();
                    int i2;
                    for (; l1 < i + min_args; l1 = i2)
                    {
                        i2 = l1 + 1;
                        al[l1] = declaration2.flags;
                        declaration2 = declaration2.nextDecl();
                    }

                }
                boolean flag1;
                int j;
                if (getCallConvention() >= 2)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                j = 0;
                do
                {
                    while (j <= i) 
                    {
                        compilation.method = primMethods[j];
                        if (j < i)
                        {
                            CodeAttr codeattr = compilation.method.startCode();
                            int i1;
                            for (i1 = j + 1; i1 < i && (defaultArgs[i1] instanceof QuoteExp); i1++) { }
                            boolean flag2;
                            Variable variable;
                            Variable variable1;
                            Declaration declaration1;
                            if (i1 == i && type1 != null)
                            {
                                flag2 = true;
                            } else
                            {
                                flag2 = false;
                            }
                            variable = compilation.callContextVar;
                            variable1 = codeattr.getArg(0);
                            if (!flag)
                            {
                                codeattr.emitPushThis();
                                if (getNeedsClosureEnv())
                                {
                                    closureEnv = variable1;
                                }
                                variable1 = codeattr.getArg(1);
                            }
                            declaration1 = firstDecl();
                            for (int j1 = 0; j1 < j + min_args;)
                            {
                                declaration1.flags = 64L | declaration1.flags;
                                declaration1.var = variable1;
                                codeattr.emitLoad(variable1);
                                variable1 = variable1.nextVar();
                                j1++;
                                declaration1 = declaration1.nextDecl();
                            }

                            Variable variable2;
                            if (flag1)
                            {
                                variable2 = variable1;
                            } else
                            {
                                variable2 = null;
                            }
                            compilation.callContextVar = variable2;
                            for (int k1 = j; k1 < i1;)
                            {
                                Target target = StackTarget.getInstance(declaration1.getType());
                                defaultArgs[k1].compile(compilation, target);
                                k1++;
                                declaration1 = declaration1.nextDecl();
                            }

                            if (flag2)
                            {
                                String s = type1.getName();
                                QuoteExp quoteexp;
                                if ("gnu.lists.LList".equals(s))
                                {
                                    quoteexp = new QuoteExp(LList.Empty);
                                } else
                                if ("java.lang.Object[]".equals(s))
                                {
                                    quoteexp = new QuoteExp(((Object) (Values.noArgs)));
                                } else
                                {
                                    throw new Error((new StringBuilder()).append("unimplemented #!rest type ").append(s).toString());
                                }
                                quoteexp.compile(compilation, type1);
                            }
                            if (flag1)
                            {
                                codeattr.emitLoad(variable1);
                            }
                            if (flag)
                            {
                                codeattr.emitInvokeStatic(primMethods[i1]);
                            } else
                            {
                                codeattr.emitInvokeVirtual(primMethods[i1]);
                            }
                            codeattr.emitReturn();
                            closureEnv = null;
                            compilation.callContextVar = variable;
                        } else
                        {
                            if (al != null)
                            {
                                int k = 0;
                                Declaration declaration = firstDecl();
                                int l;
                                for (; k < i + min_args; k = l)
                                {
                                    l = k + 1;
                                    declaration.flags = al[k];
                                    declaration.var = null;
                                    declaration = declaration.nextDecl();
                                }

                            }
                            compilation.method.initCode();
                            allocChildClasses(compilation);
                            allocParameters(compilation);
                            enterFunction(compilation);
                            compileBody(compilation);
                            compileEnd(compilation);
                            generateApplyMethods(compilation);
                        }
                        j++;
                    }
                    compilation.method = method;
                    compilation.curLambda = lambdaexp;
                    return;
                } while (true);
            }
        }
    }

    public void compileBody(Compilation compilation)
    {
        Variable variable = compilation.callContextVar;
        compilation.callContextVar = null;
        Target target;
        Expression expression;
        if (getCallConvention() >= 2)
        {
            Variable variable1 = getVarScope().lookup("$ctx");
            if (variable1 != null && variable1.getType() == Compilation.typeCallContext)
            {
                compilation.callContextVar = variable1;
            }
            target = ConsumerTarget.makeContextTarget(compilation);
        } else
        {
            target = Target.pushValue(getReturnType());
        }
        expression = body;
        if (body.getLineNumber() > 0)
        {
            this = body;
        }
        expression.compileWithPosition(compilation, target, this);
        compilation.callContextVar = variable;
    }

    public void compileEnd(Compilation compilation)
    {
        CodeAttr codeattr = compilation.getCode();
        if (!getInlineOnly())
        {
            if (compilation.method.reachableHere() && (Compilation.defaultCallConvention < 3 || isModuleBody() || isClassMethod() || isHandlingTailCalls()))
            {
                codeattr.emitReturn();
            }
            popScope(codeattr);
            codeattr.popScope();
        }
        for (LambdaExp lambdaexp = firstChild; lambdaexp != null; lambdaexp = lambdaexp.nextSibling)
        {
            if (!lambdaexp.getCanRead() && !lambdaexp.getInlineOnly())
            {
                lambdaexp.compileAsMethod(compilation);
            }
        }

        if (heapFrame != null)
        {
            compilation.generateConstructor(this);
        }
    }

    public Field compileSetField(Compilation compilation)
    {
        if (primMethods == null)
        {
            allocMethod(outerLambda(), compilation);
        }
        Field field = allocFieldFor(compilation);
        if (compilation.usingCPStyle())
        {
            compile(compilation, Type.objectType);
        } else
        {
            compileAsMethod(compilation);
            addApplyMethod(compilation, field);
        }
        return (new ProcInitializer(this, compilation, field)).field;
    }

    public Variable declareClosureEnv()
    {
        if (closureEnv == null && getNeedsClosureEnv())
        {
            LambdaExp lambdaexp = outerLambda();
            if (lambdaexp instanceof ClassExp)
            {
                lambdaexp = lambdaexp.outerLambda();
            }
            Variable variable;
            if (lambdaexp.heapFrame != null)
            {
                variable = lambdaexp.heapFrame;
            } else
            {
                variable = lambdaexp.closureEnv;
            }
            if (isClassMethod() && !"*init*".equals(getName()))
            {
                closureEnv = declareThis(type);
            } else
            if (lambdaexp.heapFrame == null && !lambdaexp.getNeedsStaticLink() && !(lambdaexp instanceof ModuleExp))
            {
                closureEnv = null;
            } else
            if (!isClassGenerated() && !getInlineOnly())
            {
                Method method = getMainMethod();
                boolean flag = "*init*".equals(getName());
                if (!method.getStaticFlag() && !flag)
                {
                    closureEnv = declareThis(method.getDeclaringClass());
                } else
                {
                    closureEnv = new Variable("closureEnv", method.getParameterTypes()[0]);
                    Variable variable1;
                    if (flag)
                    {
                        variable1 = declareThis(method.getDeclaringClass());
                    } else
                    {
                        variable1 = null;
                    }
                    getVarScope().addVariableAfter(variable1, closureEnv);
                    closureEnv.setParameter(true);
                }
            } else
            if (inlinedIn(lambdaexp))
            {
                closureEnv = variable;
            } else
            {
                closureEnv = new Variable("closureEnv", variable.getType());
                getVarScope().addVariable(closureEnv);
            }
        }
        return closureEnv;
    }

    public Variable declareThis(ClassType classtype)
    {
        if (thisVariable == null)
        {
            thisVariable = new Variable("this");
            getVarScope().addVariableAfter(null, thisVariable);
            thisVariable.setParameter(true);
        }
        if (thisVariable.getType() == null)
        {
            thisVariable.setType(classtype);
        }
        if (decls != null && decls.isThisParameter())
        {
            decls.var = thisVariable;
        }
        return thisVariable;
    }

    void enterFunction(Compilation compilation)
    {
        CodeAttr codeattr = compilation.getCode();
        getVarScope().noteStartFunction(codeattr);
        if (closureEnv != null && !closureEnv.isParameter() && !compilation.usingCPStyle())
        {
            if (!getInlineOnly())
            {
                codeattr.emitPushThis();
                Field field = closureEnvField;
                if (field == null)
                {
                    field = outerLambda().closureEnvField;
                }
                codeattr.emitGetField(field);
                codeattr.emitStore(closureEnv);
            } else
            if (!inlinedIn(outerLambda()))
            {
                outerLambda().loadHeapFrame(compilation);
                codeattr.emitStore(closureEnv);
            }
        }
        if (!compilation.usingCPStyle())
        {
            ClassType classtype1;
            Declaration declaration2;
            if (heapFrame == null)
            {
                classtype1 = currentModule().getCompiledClassType(compilation);
            } else
            {
                classtype1 = (ClassType)heapFrame.getType();
            }
            declaration2 = capturedVars;
            while (declaration2 != null) 
            {
                if (declaration2.field == null)
                {
                    declaration2.makeField(classtype1, compilation, null);
                }
                declaration2 = declaration2.nextCapturedVar;
            }
        }
        if (heapFrame != null && !compilation.usingCPStyle())
        {
            ClassType classtype = (ClassType)heapFrame.getType();
            if (closureEnv != null && !(this instanceof ModuleExp))
            {
                staticLinkField = classtype.addField("staticLink", closureEnv.getType());
            }
            if (!(this instanceof ModuleExp) && !(this instanceof ClassExp))
            {
                classtype.setEnclosingMember(compilation.method);
                codeattr.emitNew(classtype);
                codeattr.emitDup(classtype);
                codeattr.emitInvokeSpecial(Compilation.getConstructor(classtype, this));
                if (staticLinkField != null)
                {
                    codeattr.emitDup(classtype);
                    codeattr.emitLoad(closureEnv);
                    codeattr.emitPutField(staticLinkField);
                }
                codeattr.emitStore(heapFrame);
            }
        }
        Variable variable = argsArray;
        if (min_args == max_args && primMethods == null && getCallConvention() < 2)
        {
            variable = null;
        }
        int i = 0;
        int j;
        int k;
        if (keywords == null)
        {
            j = 0;
        } else
        {
            j = keywords.length;
        }
        if (defaultArgs == null)
        {
            k = 0;
        } else
        {
            k = defaultArgs.length - j;
        }
        if (this instanceof ModuleExp)
        {
            return;
        }
        int l = -1;
        int i1 = 0;
        getMainMethod();
        Variable variable1 = compilation.callContextVar;
        Declaration declaration = firstDecl();
        int j1 = 0;
        int k1 = 0;
        while (declaration != null) 
        {
            Variable variable2;
            Declaration declaration1;
            int l1;
            int i2;
            if (getCallConvention() < 2)
            {
                variable2 = null;
            } else
            {
                variable2 = getVarScope().lookup("$ctx");
            }
            compilation.callContextVar = variable2;
            declaration1 = firstArgsArrayArg;
            if (declaration == declaration1 && variable != null)
            {
                if (primMethods != null)
                {
                    l = i;
                    i1 = l - min_args;
                } else
                {
                    i1 = 0;
                    l = 0;
                }
            }
            if (l >= 0 || !declaration.isSimple() || declaration.isIndirectBinding())
            {
                Type type1 = declaration.getType();
                Object obj;
                if (l >= 0)
                {
                    obj = Type.objectType;
                } else
                {
                    obj = type1;
                }
                if (!declaration.isSimple())
                {
                    declaration.loadOwningObject(null, compilation);
                }
                if (l < 0)
                {
                    codeattr.emitLoad(declaration.getVariable());
                    l1 = j1;
                    i2 = k1;
                } else
                if (i < min_args)
                {
                    codeattr.emitLoad(variable);
                    codeattr.emitPushInt(i);
                    codeattr.emitArrayLoad(Type.objectType);
                    l1 = j1;
                    i2 = k1;
                } else
                if (i < k + min_args)
                {
                    codeattr.emitPushInt(i - l);
                    codeattr.emitLoad(variable);
                    codeattr.emitArrayLength();
                    codeattr.emitIfIntLt();
                    codeattr.emitLoad(variable);
                    codeattr.emitPushInt(i - l);
                    codeattr.emitArrayLoad();
                    codeattr.emitElse();
                    Expression aexpression1[] = defaultArgs;
                    i2 = k1 + 1;
                    aexpression1[i1 + k1].compile(compilation, type1);
                    codeattr.emitFi();
                    l1 = j1;
                } else
                if (max_args < 0 && i == k + min_args)
                {
                    codeattr.emitLoad(variable);
                    codeattr.emitPushInt(i - l);
                    codeattr.emitInvokeStatic(Compilation.makeListMethod);
                    obj = Compilation.scmListType;
                    l1 = j1;
                    i2 = k1;
                } else
                {
                    codeattr.emitLoad(variable);
                    codeattr.emitPushInt((k + min_args) - l);
                    Keyword akeyword[] = keywords;
                    l1 = j1 + 1;
                    compilation.compileConstant(akeyword[j1]);
                    Expression aexpression[] = defaultArgs;
                    i2 = k1 + 1;
                    Expression expression = aexpression[i1 + k1];
                    if (expression instanceof QuoteExp)
                    {
                        if (searchForKeywordMethod4 == null)
                        {
                            Type atype1[] = new Type[4];
                            atype1[0] = Compilation.objArrayType;
                            atype1[1] = Type.intType;
                            atype1[2] = Type.objectType;
                            atype1[3] = Type.objectType;
                            searchForKeywordMethod4 = Compilation.scmKeywordType.addMethod("searchForKeyword", atype1, Type.objectType, 9);
                        }
                        expression.compile(compilation, type1);
                        codeattr.emitInvokeStatic(searchForKeywordMethod4);
                    } else
                    {
                        if (searchForKeywordMethod3 == null)
                        {
                            Type atype[] = new Type[3];
                            atype[0] = Compilation.objArrayType;
                            atype[1] = Type.intType;
                            atype[2] = Type.objectType;
                            searchForKeywordMethod3 = Compilation.scmKeywordType.addMethod("searchForKeyword", atype, Type.objectType, 9);
                        }
                        codeattr.emitInvokeStatic(searchForKeywordMethod3);
                        codeattr.emitDup(1);
                        compilation.compileConstant(Special.dfault);
                        codeattr.emitIfEq();
                        codeattr.emitPop(1);
                        expression.compile(compilation, type1);
                        codeattr.emitFi();
                    }
                }
                if (type1 != obj)
                {
                    CheckedTarget.emitCheckedCoerce(compilation, this, i + 1, type1);
                }
                if (declaration.isIndirectBinding())
                {
                    declaration.pushIndirectBinding(compilation);
                }
                if (declaration.isSimple())
                {
                    Variable variable3 = declaration.getVariable();
                    if (declaration.isIndirectBinding())
                    {
                        variable3.setType(Compilation.typeLocation);
                    }
                    codeattr.emitStore(variable3);
                } else
                {
                    codeattr.emitPutField(declaration.field);
                }
            } else
            {
                l1 = j1;
                i2 = k1;
            }
            i++;
            declaration = declaration.nextDecl();
            j1 = l1;
            k1 = i2;
        }
        compilation.callContextVar = variable1;
        int _tmp = j1;
        int _tmp1 = k1;
    }

    Object evalDefaultArg(int i, CallContext callcontext)
    {
        Object obj;
        try
        {
            obj = defaultArgs[i].eval(callcontext);
        }
        catch (Throwable throwable)
        {
            throw new WrappedException("error evaluating default argument", throwable);
        }
        return obj;
    }

    public void generateApplyMethods(Compilation compilation)
    {
        compilation.generateMatchMethods(this);
        if (Compilation.defaultCallConvention >= 2)
        {
            compilation.generateApplyMethodsWithContext(this);
            return;
        } else
        {
            compilation.generateApplyMethodsWithoutContext(this);
            return;
        }
    }

    Declaration getArg(int i)
    {
        Declaration declaration = firstDecl();
        do
        {
            if (declaration == null)
            {
                throw new Error("internal error - getArg");
            }
            if (i == 0)
            {
                return declaration;
            }
            i--;
            declaration = declaration.nextDecl();
        } while (true);
    }

    public int getCallConvention()
    {
        int i = 2;
        if (isModuleBody())
        {
            if (Compilation.defaultCallConvention >= i)
            {
                i = Compilation.defaultCallConvention;
            }
            return i;
        }
        if (isClassMethod())
        {
            return 1;
        }
        if (Compilation.defaultCallConvention != 0)
        {
            return Compilation.defaultCallConvention;
        } else
        {
            return 1;
        }
    }

    public LambdaExp getCaller()
    {
        return inlineHome;
    }

    public final boolean getCanCall()
    {
        return (4 & flags) != 0;
    }

    public final boolean getCanRead()
    {
        return (2 & flags) != 0;
    }

    public ClassType getClassType()
    {
        return type;
    }

    protected ClassType getCompiledClassType(Compilation compilation)
    {
        if (type == Compilation.typeProcedure)
        {
            throw new Error("internal error: getCompiledClassType");
        } else
        {
            return type;
        }
    }

    protected final String getExpClassName()
    {
        String s = getClass().getName();
        int i = s.lastIndexOf('.');
        if (i >= 0)
        {
            s = s.substring(i + 1);
        }
        return s;
    }

    public ClassType getHeapFrameType()
    {
        if ((this instanceof ModuleExp) || (this instanceof ClassExp))
        {
            return (ClassType)getType();
        } else
        {
            return (ClassType)heapFrame.getType();
        }
    }

    public final boolean getImportsLexVars()
    {
        return (8 & flags) != 0;
    }

    public final boolean getInlineOnly()
    {
        return (0x2000 & flags) != 0;
    }

    public final Method getMainMethod()
    {
        Method amethod[] = primBodyMethods;
        if (amethod == null)
        {
            return null;
        } else
        {
            return amethod[-1 + amethod.length];
        }
    }

    public final Method getMethod(int i)
    {
        int j;
        if (primMethods != null && (max_args < 0 || i <= max_args))
        {
            if ((j = i - min_args) >= 0)
            {
                int k = primMethods.length;
                Method amethod[] = primMethods;
                if (j >= k)
                {
                    j = k - 1;
                }
                return amethod[j];
            }
        }
        return null;
    }

    public final boolean getNeedsClosureEnv()
    {
        return (0x18 & flags) != 0;
    }

    public final boolean getNeedsStaticLink()
    {
        return (0x10 & flags) != 0;
    }

    public LambdaExp getOwningLambda()
    {
        ScopeExp scopeexp = outer;
        do
        {
            if (scopeexp == null)
            {
                return null;
            }
            if ((scopeexp instanceof ModuleExp) || (scopeexp instanceof ClassExp) && getNeedsClosureEnv() || (scopeexp instanceof LambdaExp) && ((LambdaExp)scopeexp).heapFrame != null)
            {
                return (LambdaExp)scopeexp;
            }
            scopeexp = scopeexp.outer;
        } while (true);
    }

    public Object getProperty(Object obj, Object obj1)
    {
label0:
        {
            if (properties == null)
            {
                break label0;
            }
            int i = properties.length;
            do
            {
                if ((i -= 2) < 0)
                {
                    break label0;
                }
            } while (properties[i] != obj);
            obj1 = properties[i + 1];
        }
        return obj1;
    }

    public final Type getReturnType()
    {
        if (returnType == null)
        {
            returnType = Type.objectType;
            if (body != null && !isAbstract())
            {
                returnType = body.getType();
            }
        }
        return returnType;
    }

    int getSelectorValue(Compilation compilation)
    {
        int i = selectorValue;
        if (i == 0)
        {
            int j = compilation.maxSelectorValue;
            compilation.maxSelectorValue = j + primMethods.length;
            i = j + 1;
            selectorValue = i;
        }
        return i;
    }

    public Type getType()
    {
        return type;
    }

    public int incomingArgs()
    {
        if (min_args == max_args && max_args <= 4 && max_args > 0)
        {
            return max_args;
        } else
        {
            return 1;
        }
    }

    boolean inlinedIn(LambdaExp lambdaexp)
    {
        for (LambdaExp lambdaexp1 = this; lambdaexp1.getInlineOnly(); lambdaexp1 = lambdaexp1.getCaller())
        {
            if (lambdaexp1 == lambdaexp)
            {
                return true;
            }
        }

        return false;
    }

    public boolean isAbstract()
    {
        return body == QuoteExp.abstractExp;
    }

    public final boolean isClassGenerated()
    {
        return isModuleBody() || (this instanceof ClassExp);
    }

    public final boolean isClassMethod()
    {
        return (0x40 & flags) != 0;
    }

    public final boolean isHandlingTailCalls()
    {
        return isModuleBody() || Compilation.defaultCallConvention >= 3 && !isClassMethod();
    }

    public final boolean isModuleBody()
    {
        return this instanceof ModuleExp;
    }

    public void loadHeapFrame(Compilation compilation)
    {
        LambdaExp lambdaexp;
        for (lambdaexp = compilation.curLambda; lambdaexp != this && lambdaexp.getInlineOnly(); lambdaexp = lambdaexp.getCaller()) { }
        CodeAttr codeattr = compilation.getCode();
        if (lambdaexp.heapFrame != null && this == lambdaexp)
        {
            codeattr.emitLoad(lambdaexp.heapFrame);
        } else
        {
            ClassType classtype;
            if (lambdaexp.closureEnv != null)
            {
                codeattr.emitLoad(lambdaexp.closureEnv);
                classtype = (ClassType)lambdaexp.closureEnv.getType();
            } else
            {
                codeattr.emitPushThis();
                classtype = compilation.curClass;
            }
            while (lambdaexp != this) 
            {
                Field field = lambdaexp.staticLinkField;
                if (field != null && field.getDeclaringClass() == classtype)
                {
                    codeattr.emitGetField(field);
                    classtype = (ClassType)field.getType();
                }
                lambdaexp = lambdaexp.outerLambda();
            }
        }
    }

    protected boolean mustCompile()
    {
label0:
        {
            if (keywords != null && keywords.length > 0)
            {
                return true;
            }
            if (defaultArgs == null)
            {
                break label0;
            }
            int i = defaultArgs.length;
            Expression expression;
            do
            {
                if (--i < 0)
                {
                    break label0;
                }
                expression = defaultArgs[i];
            } while (expression == null || (expression instanceof QuoteExp));
            return true;
        }
        return false;
    }

    public LambdaExp outerLambda()
    {
        if (outer == null)
        {
            return null;
        } else
        {
            return outer.currentLambda();
        }
    }

    public LambdaExp outerLambdaNotInline()
    {
        Object obj = this;
        do
        {
            obj = ((ScopeExp) (obj)).outer;
            if (obj != null)
            {
                if (obj instanceof LambdaExp)
                {
                    LambdaExp lambdaexp = (LambdaExp)obj;
                    if (!lambdaexp.getInlineOnly())
                    {
                        return lambdaexp;
                    }
                }
            } else
            {
                return null;
            }
        } while (true);
    }

    public void print(OutPort outport)
    {
        outport.startLogicalBlock("(Lambda/", ")", 2);
        Object obj = getSymbol();
        if (obj != null)
        {
            outport.print(obj);
            outport.print('/');
        }
        outport.print(id);
        outport.print('/');
        outport.print("fl:");
        outport.print(Integer.toHexString(flags));
        outport.writeSpaceFill();
        printLineColumn(outport);
        outport.startLogicalBlock("(", false, ")");
        Special special = null;
        int i;
        int j;
        Declaration declaration;
        int k;
        int l;
        if (keywords == null)
        {
            i = 0;
        } else
        {
            i = keywords.length;
        }
        if (defaultArgs == null)
        {
            j = 0;
        } else
        {
            j = defaultArgs.length - i;
        }
        declaration = firstDecl();
        if (declaration != null && declaration.isThisParameter())
        {
            k = -1;
            l = 0;
        } else
        {
            k = 0;
            l = 0;
            special = null;
        }
        while (declaration != null) 
        {
            Special special1;
            int i1;
            Expression expression;
            if (k < min_args)
            {
                special1 = null;
            } else
            if (k < j + min_args)
            {
                special1 = Special.optional;
            } else
            if (max_args < 0 && k == j + min_args)
            {
                special1 = Special.rest;
            } else
            {
                special1 = Special.key;
            }
            if (declaration != firstDecl())
            {
                outport.writeSpaceFill();
            }
            if (special1 != special)
            {
                outport.print(special1);
                outport.writeSpaceFill();
            }
            if (special1 == Special.optional || special1 == Special.key)
            {
                Expression aexpression[] = defaultArgs;
                i1 = l + 1;
                expression = aexpression[l];
            } else
            {
                i1 = l;
                expression = null;
            }
            if (expression != null)
            {
                outport.print('(');
            }
            declaration.printInfo(outport);
            if (expression != null && expression != QuoteExp.falseExp)
            {
                outport.print(' ');
                expression.print(outport);
                outport.print(')');
            }
            k++;
            special = special1;
            declaration = declaration.nextDecl();
            l = i1;
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

    public final Type restArgType()
    {
        if (min_args != max_args)
        {
            if (primMethods == null)
            {
                throw new Error("internal error - restArgType");
            }
            Method amethod[] = primMethods;
            if (max_args < 0 || amethod.length <= max_args - min_args)
            {
                Method method = amethod[-1 + amethod.length];
                Type atype[] = method.getParameterTypes();
                int i = -1 + atype.length;
                if (method.getName().endsWith("$X"))
                {
                    i--;
                }
                return atype[i];
            }
        }
        return null;
    }

    void setCallersNeedStaticLink()
    {
        LambdaExp lambdaexp = outerLambda();
        for (ApplyExp applyexp = nameDecl.firstCall; applyexp != null; applyexp = applyexp.nextCall)
        {
            for (LambdaExp lambdaexp1 = applyexp.context; lambdaexp1 != lambdaexp && !(lambdaexp1 instanceof ModuleExp); lambdaexp1 = lambdaexp1.outerLambda())
            {
                lambdaexp1.setNeedsStaticLink();
            }

        }

    }

    public final void setCanCall(boolean flag)
    {
        if (flag)
        {
            flags = 4 | flags;
            return;
        } else
        {
            flags = -5 & flags;
            return;
        }
    }

    public final void setCanRead(boolean flag)
    {
        if (flag)
        {
            flags = 2 | flags;
            return;
        } else
        {
            flags = -3 & flags;
            return;
        }
    }

    public final void setClassMethod(boolean flag)
    {
        if (flag)
        {
            flags = 0x40 | flags;
            return;
        } else
        {
            flags = 0xffffffbf & flags;
            return;
        }
    }

    public final void setCoercedReturnType(Type type1)
    {
        returnType = type1;
        if (type1 != null && type1 != Type.objectType && type1 != Type.voidType && body != QuoteExp.abstractExp)
        {
            Expression expression = body;
            body = Compilation.makeCoercion(expression, type1);
            body.setLine(expression);
        }
    }

    public final void setCoercedReturnValue(Expression expression, Language language)
    {
        if (!isAbstract())
        {
            Expression expression1 = body;
            body = Compilation.makeCoercion(expression1, expression);
            body.setLine(expression1);
        }
        Type type1 = language.getTypeFor(expression);
        if (type1 != null)
        {
            setReturnType(type1);
        }
    }

    public void setExceptions(Expression aexpression[])
    {
        throwsSpecification = aexpression;
    }

    public final void setImportsLexVars()
    {
        int i = flags;
        flags = 8 | flags;
        if ((i & 8) == 0 && nameDecl != null)
        {
            setCallersNeedStaticLink();
        }
    }

    public final void setImportsLexVars(boolean flag)
    {
        if (flag)
        {
            flags = 8 | flags;
            return;
        } else
        {
            flags = -9 & flags;
            return;
        }
    }

    public final void setInlineOnly(boolean flag)
    {
        setFlag(flag, 8192);
    }

    public final void setNeedsStaticLink()
    {
        int i = flags;
        flags = 0x10 | flags;
        if ((i & 0x10) == 0 && nameDecl != null)
        {
            setCallersNeedStaticLink();
        }
    }

    public final void setNeedsStaticLink(boolean flag)
    {
        if (flag)
        {
            flags = 0x10 | flags;
            return;
        } else
        {
            flags = 0xffffffef & flags;
            return;
        }
    }

    public void setProperty(Object obj, Object obj1)
    {
        this;
        JVM INSTR monitorenter ;
        properties = PropertySet.setProperty(properties, obj, obj1);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public final void setReturnType(Type type1)
    {
        returnType = type1;
    }

    public void setType(ClassType classtype)
    {
        type = classtype;
    }

    public boolean side_effects()
    {
        return false;
    }

    public String toString()
    {
        String s = (new StringBuilder()).append(getExpClassName()).append(':').append(getSymbol()).append('/').append(id).append('/').toString();
        int i = getLineNumber();
        if (i <= 0 && body != null)
        {
            i = body.getLineNumber();
        }
        if (i > 0)
        {
            s = (new StringBuilder()).append(s).append("l:").append(i).toString();
        }
        return s;
    }

    public Expression validateApply(ApplyExp applyexp, InlineCalls inlinecalls, Type type1, Declaration declaration)
    {
        Expression aexpression[] = applyexp.getArgs();
        if ((0x1000 & flags) == 0) goto _L2; else goto _L1
_L1:
        Expression expression = InlineCalls.inlineCall(this, aexpression, true);
        if (expression == null) goto _L2; else goto _L3
_L3:
        applyexp = inlinecalls.visit(expression, type1);
_L5:
        return applyexp;
_L2:
        applyexp.visitArgs(inlinecalls);
        int i = applyexp.args.length;
        String s = WrongArguments.checkArgCount(getName(), min_args, max_args, i);
        if (s != null)
        {
            return inlinecalls.noteError(s);
        }
        int j = getCallConvention();
        if (inlinecalls.getCompilation().inlineOk(this) && isClassMethod() && (j <= 2 || j == 3))
        {
            Method method = getMethod(i);
            if (method != null)
            {
                boolean flag = nameDecl.isStatic();
                if (!flag && (outer instanceof ClassExp))
                {
                    if (!((ClassExp)outer).isMakingClassPair());
                }
                PrimProcedure primprocedure = new PrimProcedure(method, this);
                Expression aexpression1[];
                ApplyExp applyexp1;
                if (flag)
                {
                    aexpression1 = applyexp.args;
                } else
                {
                    LambdaExp lambdaexp = inlinecalls.getCurrentLambda();
                    Declaration declaration1;
                    do
                    {
                        if (lambdaexp == null)
                        {
                            return inlinecalls.noteError((new StringBuilder()).append("internal error: missing ").append(this).toString());
                        }
                        if (lambdaexp.outer == outer)
                        {
                            declaration1 = lambdaexp.firstDecl();
                            if (declaration1 == null || !declaration1.isThisParameter())
                            {
                                return inlinecalls.noteError((new StringBuilder()).append("calling non-static method ").append(getName()).append(" from static method ").append(lambdaexp.getName()).toString());
                            }
                            break;
                        }
                        lambdaexp = lambdaexp.outerLambda();
                    } while (true);
                    int k = applyexp.getArgCount();
                    aexpression1 = new Expression[k + 1];
                    System.arraycopy(applyexp.getArgs(), 0, aexpression1, 1, k);
                    ThisExp thisexp = new ThisExp(declaration1);
                    aexpression1[0] = thisexp;
                }
                applyexp1 = new ApplyExp(primprocedure, aexpression1);
                return applyexp1.setLine(applyexp);
            }
        }
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final boolean variable_args()
    {
        return max_args < 0;
    }

    protected Object visit(ExpVisitor expvisitor, Object obj)
    {
        Compilation compilation;
        LambdaExp lambdaexp;
        compilation = expvisitor.getCompilation();
        Object obj1;
        if (compilation == null)
        {
            lambdaexp = null;
        } else
        {
            lambdaexp = compilation.curLambda;
            compilation.curLambda = this;
        }
        obj1 = expvisitor.visitLambdaExp(this, obj);
        if (compilation != null)
        {
            compilation.curLambda = lambdaexp;
        }
        return obj1;
        Exception exception;
        exception;
        if (compilation != null)
        {
            compilation.curLambda = lambdaexp;
        }
        throw exception;
    }

    protected void visitChildren(ExpVisitor expvisitor, Object obj)
    {
        visitChildrenOnly(expvisitor, obj);
        visitProperties(expvisitor, obj);
    }

    protected final void visitChildrenOnly(ExpVisitor expvisitor, Object obj)
    {
        LambdaExp lambdaexp;
        lambdaexp = expvisitor.currentLambda;
        expvisitor.currentLambda = this;
        throwsSpecification = expvisitor.visitExps(throwsSpecification, obj);
        expvisitor.visitDefaultArgs(this, obj);
        if (expvisitor.exitValue == null && body != null)
        {
            body = expvisitor.update(body, expvisitor.visit(body, obj));
        }
        expvisitor.currentLambda = lambdaexp;
        return;
        Exception exception;
        exception;
        expvisitor.currentLambda = lambdaexp;
        throw exception;
    }

    protected final void visitProperties(ExpVisitor expvisitor, Object obj)
    {
        if (properties != null)
        {
            int i = properties.length;
            for (int j = 1; j < i; j += 2)
            {
                Object obj1 = properties[j];
                if (obj1 instanceof Expression)
                {
                    properties[j] = expvisitor.visitAndUpdate((Expression)obj1, obj);
                }
            }

        }
    }

}
