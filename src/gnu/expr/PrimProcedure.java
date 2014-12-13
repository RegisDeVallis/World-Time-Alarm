// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassFileInput;
import gnu.bytecode.ClassType;
import gnu.bytecode.ClassTypeWriter;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.kawa.functions.MakeList;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.ConsumerWriter;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongArguments;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;

// Referenced classes of package gnu.expr:
//            Inlineable, Language, LambdaExp, Compilation, 
//            Expression, Declaration, CheckedTarget, Target, 
//            IgnoreTarget, ConsumerTarget, GenericProc, ModuleMethod, 
//            PairClassType, ApplyExp, ClassExp, ReferenceExp, 
//            LetExp

public class PrimProcedure extends MethodProc
    implements Inlineable
{

    private static ClassLoader systemClassLoader = gnu/expr/PrimProcedure.getClassLoader();
    Type argTypes[];
    Member member;
    Method method;
    char mode;
    int op_code;
    Type retType;
    boolean sideEffectFree;
    LambdaExp source;

    public PrimProcedure(int i, ClassType classtype, String s, Type type, Type atype[])
    {
        op_code = i;
        byte byte0;
        char c;
        if (i == 184)
        {
            byte0 = 8;
        } else
        {
            byte0 = 0;
        }
        method = classtype.addMethod(s, byte0, atype, type);
        retType = type;
        argTypes = atype;
        c = '\0';
        if (i != 184)
        {
            c = 'V';
        }
        mode = c;
    }

    public PrimProcedure(int i, Type type, Type atype[])
    {
        op_code = i;
        retType = type;
        argTypes = atype;
    }

    public PrimProcedure(Method method1)
    {
        init(method1);
        Object obj;
        if (method1.getName().endsWith("$X"))
        {
            obj = Type.objectType;
        } else
        {
            obj = method1.getReturnType();
        }
        retType = ((Type) (obj));
    }

    public PrimProcedure(Method method1, char c, Language language)
    {
        mode = c;
        init(method1);
        Type atype[] = argTypes;
        int i = atype.length;
        argTypes = null;
        int j = i;
        do
        {
            if (--j < 0)
            {
                break;
            }
            Type type = atype[j];
            Type type1 = language.getLangTypeFor(type);
            if (type != type1)
            {
                if (argTypes == null)
                {
                    argTypes = new Type[i];
                    System.arraycopy(atype, 0, argTypes, 0, i);
                }
                argTypes[j] = type1;
            }
        } while (true);
        if (argTypes == null)
        {
            argTypes = atype;
        }
        if (isConstructor())
        {
            retType = method1.getDeclaringClass();
        } else
        {
            if (method1.getName().endsWith("$X"))
            {
                retType = Type.objectType;
                return;
            }
            retType = language.getLangTypeFor(method1.getReturnType());
            if (retType == Type.toStringType)
            {
                retType = Type.javalangStringType;
                return;
            }
        }
    }

    public PrimProcedure(Method method1, LambdaExp lambdaexp)
    {
        this(method1);
        retType = lambdaexp.getReturnType();
        source = lambdaexp;
    }

    public PrimProcedure(Method method1, Language language)
    {
        this(method1, '\0', language);
    }

    public PrimProcedure(String s, String s1, int i)
    {
        this(ClassType.make(s).getDeclaredMethod(s1, i));
    }

    public PrimProcedure(java.lang.reflect.Method method1, Language language)
    {
        this(((ClassType)language.getTypeFor(method1.getDeclaringClass())).getMethod(method1), language);
    }

    private void compileArgs(Expression aexpression[], int i, Type type, Compilation compilation)
    {
        String s;
        Type type1;
        CodeAttr codeattr;
        int j;
        int l;
        boolean flag1;
        boolean flag2;
        int i1;
        Declaration declaration;
        int j1;
        Type type3;
        boolean flag = takesVarArgs();
        s = getName();
        type1 = null;
        codeattr = compilation.getCode();
        int k;
        if (type == Type.voidType)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        k = argTypes.length - j;
        if (takesContext())
        {
            k--;
        }
        l = aexpression.length - i;
        if (type == null || j != 0)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        flag2 = false;
        if (flag)
        {
            int l1 = 0x80 & method.getModifiers();
            flag2 = false;
            if (l1 != 0)
            {
                flag2 = false;
                if (l > 0)
                {
                    int i2 = argTypes.length;
                    flag2 = false;
                    if (i2 > 0)
                    {
                        int j2;
                        int k2;
                        if (flag1)
                        {
                            j2 = 0;
                        } else
                        {
                            j2 = 1;
                        }
                        k2 = j2 + k;
                        flag2 = false;
                        if (l == k2)
                        {
                            Type type4 = aexpression[-1 + aexpression.length].getType();
                            Type type5 = argTypes[-1 + argTypes.length];
                            boolean flag4 = type4 instanceof ObjectType;
                            flag2 = false;
                            if (flag4)
                            {
                                boolean flag5 = type5 instanceof ArrayType;
                                flag2 = false;
                                if (flag5)
                                {
                                    boolean flag6 = ((ArrayType)type5).getComponentType() instanceof ArrayType;
                                    flag2 = false;
                                    if (!flag6)
                                    {
                                        boolean flag7 = type4 instanceof ArrayType;
                                        flag2 = false;
                                        if (!flag7)
                                        {
                                            flag2 = true;
                                        }
                                        flag = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (flag)
        {
            int k1;
            if (flag1)
            {
                k1 = 1;
            } else
            {
                k1 = 0;
            }
            i1 = k - k1;
        } else
        {
            i1 = aexpression.length - i;
        }
        if (source == null)
        {
            declaration = null;
        } else
        {
            declaration = source.firstDecl();
        }
        if (declaration != null && declaration.isThisParameter())
        {
            declaration = declaration.nextDecl();
        }
        j1 = 0;
_L9:
        if (!flag || j1 != i1) goto _L2; else goto _L1
_L1:
        type3 = argTypes[j + (k - 1)];
        if (type3 != Compilation.scmListType && type3 != LangObjType.listType) goto _L4; else goto _L3
_L3:
        MakeList.compile(aexpression, i + j1, compilation);
_L6:
        return;
_L4:
        codeattr.emitPushInt(aexpression.length - i - i1);
        type1 = ((ArrayType)type3).getComponentType();
        codeattr.emitNewArray(type1);
_L2:
        if (j1 >= l) goto _L6; else goto _L5
_L5:
        boolean flag3;
        if (flag2 && j1 + 1 == l)
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        if (j1 < i1) goto _L8; else goto _L7
_L7:
        codeattr.emitDup(1);
        codeattr.emitPushInt(j1 - i1);
_L10:
        compilation.usedClass(type1);
        Object obj;
        Target target;
        Expression expression;
        Expression expression1;
        if (flag3)
        {
            obj = Type.objectType;
        } else
        {
            obj = type1;
        }
        if (source == null)
        {
            target = CheckedTarget.getInstance(((Type) (obj)), s, j1 + 1);
        } else
        {
            target = CheckedTarget.getInstance(((Type) (obj)), source, j1);
        }
        expression = aexpression[i + j1];
        expression1 = aexpression[i + j1];
        expression.compileNotePosition(compilation, target, expression1);
        if (flag3)
        {
            Type type2 = ((ArrayType)type1).getComponentType();
            codeattr.emitDup();
            codeattr.emitInstanceof(type1);
            codeattr.emitIfIntNotZero();
            codeattr.emitCheckcast(type1);
            codeattr.emitElse();
            codeattr.emitPushInt(1);
            codeattr.emitNewArray(type2);
            codeattr.emitDupX();
            codeattr.emitSwap();
            codeattr.emitPushInt(0);
            codeattr.emitSwap();
            type2.emitCoerceFromObject(codeattr);
            codeattr.emitArrayStore(type1);
            codeattr.emitFi();
        }
        if (j1 >= i1)
        {
            codeattr.emitArrayStore(type1);
        }
        if (declaration != null && (flag1 || j1 > 0))
        {
            declaration = declaration.nextDecl();
        }
        j1++;
        if (true) goto _L9; else goto _L8
_L8:
        if (declaration != null && (flag1 || j1 > 0))
        {
            type1 = declaration.getType();
        } else
        if (flag1)
        {
            type1 = argTypes[j1 + j];
        } else
        if (j1 == 0)
        {
            type1 = type;
        } else
        {
            type1 = argTypes[j1 - 1];
        }
          goto _L10
    }

    public static void compileInvoke(Compilation compilation, Method method1, Target target, boolean flag, int i, Type type)
    {
        CodeAttr codeattr;
        codeattr = compilation.getCode();
        compilation.usedClass(method1.getDeclaringClass());
        compilation.usedClass(method1.getReturnType());
        if (takesContext(method1)) goto _L2; else goto _L1
_L1:
        codeattr.emitInvokeMethod(method1, i);
_L6:
        target.compileFromStack(compilation, type);
_L4:
        return;
_L2:
        if (!(target instanceof IgnoreTarget) && (!(target instanceof ConsumerTarget) || !((ConsumerTarget)target).isContextTarget()))
        {
            break; /* Loop/switch isn't completed */
        }
        compilation.loadCallContext();
        boolean flag1 = target instanceof IgnoreTarget;
        gnu.bytecode.Field field = null;
        gnu.bytecode.Variable variable = null;
        if (flag1)
        {
            ClassType classtype = Compilation.typeCallContext;
            field = classtype.getDeclaredField("consumer");
            codeattr.pushScope();
            variable = codeattr.addLocal(classtype);
            codeattr.emitDup();
            codeattr.emitGetField(field);
            codeattr.emitStore(variable);
            codeattr.emitDup();
            codeattr.emitGetStatic(ClassType.make("gnu.lists.VoidConsumer").getDeclaredField("instance"));
            codeattr.emitPutField(field);
        }
        codeattr.emitInvokeMethod(method1, i);
        if (flag)
        {
            compilation.loadCallContext();
            codeattr.emitInvoke(Compilation.typeCallContext.getDeclaredMethod("runUntilDone", 0));
        }
        if (target instanceof IgnoreTarget)
        {
            compilation.loadCallContext();
            codeattr.emitLoad(variable);
            codeattr.emitPutField(field);
            codeattr.popScope();
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
        compilation.loadCallContext();
        type = Type.objectType;
        codeattr.pushScope();
        gnu.bytecode.Variable variable1 = codeattr.addLocal(Type.intType);
        compilation.loadCallContext();
        codeattr.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("startFromContext", 0));
        codeattr.emitStore(variable1);
        codeattr.emitWithCleanupStart();
        codeattr.emitInvokeMethod(method1, i);
        codeattr.emitWithCleanupCatch(null);
        compilation.loadCallContext();
        codeattr.emitLoad(variable1);
        codeattr.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("cleanupFromContext", 1));
        codeattr.emitWithCleanupDone();
        compilation.loadCallContext();
        codeattr.emitLoad(variable1);
        codeattr.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("getFromContext", 1));
        codeattr.popScope();
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static void disassemble(Procedure procedure, ClassTypeWriter classtypewriter)
        throws Exception
    {
        String s;
        Class class1;
        if (procedure instanceof GenericProc)
        {
            GenericProc genericproc = (GenericProc)procedure;
            int i = genericproc.getMethodCount();
            classtypewriter.print("Generic procedure with ");
            classtypewriter.print(i);
            String s4;
            if (i == 1)
            {
                s4 = " method.";
            } else
            {
                s4 = "methods.";
            }
            classtypewriter.println(s4);
            for (int j = 0; j < i; j++)
            {
                MethodProc methodproc = genericproc.getMethod(j);
                if (methodproc != null)
                {
                    classtypewriter.println();
                    disassemble(((Procedure) (methodproc)), classtypewriter);
                }
            }

            break MISSING_BLOCK_LABEL_348;
        }
        s = null;
        class1 = procedure.getClass();
        if (!(procedure instanceof ModuleMethod)) goto _L2; else goto _L1
_L1:
        class1 = ((ModuleMethod)procedure).module.getClass();
_L4:
        ClassLoader classloader;
        String s1;
        String s2;
        ClassType classtype;
        java.io.InputStream inputstream;
        classloader = class1.getClassLoader();
        s1 = class1.getName();
        s2 = (new StringBuilder()).append(s1.replace('.', '/')).append(".class").toString();
        classtype = new ClassType();
        inputstream = classloader.getResourceAsStream(s2);
        if (inputstream == null)
        {
            throw new RuntimeException((new StringBuilder()).append("missing resource ").append(s2).toString());
        }
        break; /* Loop/switch isn't completed */
_L2:
        boolean flag = procedure instanceof PrimProcedure;
        s = null;
        if (flag)
        {
            Method method1 = ((PrimProcedure)procedure).method;
            s = null;
            if (method1 != null)
            {
                class1 = method1.getDeclaringClass().getReflectClass();
                s = method1.getName();
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
        String s3;
        new ClassFileInput(classtype, inputstream);
        classtypewriter.setClass(classtype);
        java.net.URL url = classloader.getResource(s2);
        classtypewriter.print("In class ");
        classtypewriter.print(s1);
        if (url != null)
        {
            classtypewriter.print(" at ");
            classtypewriter.print(url);
        }
        classtypewriter.println();
        if (s != null)
        {
            break MISSING_BLOCK_LABEL_355;
        }
        s3 = procedure.getName();
        if (s3 != null)
        {
            break MISSING_BLOCK_LABEL_349;
        }
        classtypewriter.println("Anonymous function - unknown method.");
        return;
        s = Compilation.mangleName(s3);
        for (Method method2 = classtype.getMethods(); method2 != null; method2 = method2.getNext())
        {
            if (method2.getName().equals(s))
            {
                classtypewriter.printMethod(method2);
            }
        }

        classtypewriter.flush();
        return;
    }

    public static void disassemble(Procedure procedure, Writer writer)
        throws Exception
    {
        disassemble(procedure, new ClassTypeWriter(null, writer, 0));
    }

    public static void disassemble$X(Procedure procedure, CallContext callcontext)
        throws Exception
    {
        Consumer consumer = callcontext.consumer;
        Object obj;
        if (consumer instanceof Writer)
        {
            obj = (Writer)consumer;
        } else
        {
            obj = new ConsumerWriter(consumer);
        }
        disassemble(procedure, ((Writer) (obj)));
    }

    public static PrimProcedure getMethodFor(ClassType classtype, String s, Declaration declaration, Type atype[], Language language)
    {
        PrimProcedure primprocedure;
        int i;
        boolean flag;
        primprocedure = null;
        i = -1;
        flag = false;
        if (s == null)
        {
            return null;
        }
        String s1;
        String s2;
        String s3;
        String s4;
        s1 = Compilation.mangleName(s);
        s2 = (new StringBuilder()).append(s1).append("$V").toString();
        s3 = (new StringBuilder()).append(s1).append("$V$X").toString();
        s4 = (new StringBuilder()).append(s1).append("$X").toString();
        boolean flag1 = true;
        Method method1 = classtype.getDeclaredMethods();
_L7:
        if (method1 == null) goto _L2; else goto _L1
_L1:
        if ((9 & method1.getModifiers()) == 9) goto _L4; else goto _L3
_L3:
        if (declaration == null) goto _L6; else goto _L5
_L5:
        if (declaration.base != null) goto _L4; else goto _L6
_L6:
        method1 = method1.getNext();
          goto _L7
_L4:
        String s5 = method1.getName();
        if (!s5.equals(s1) && !s5.equals(s2) && !s5.equals(s4) && !s5.equals(s3)) goto _L9; else goto _L8
_L17:
        PrimProcedure primprocedure1;
        int j;
        primprocedure1 = new PrimProcedure(method1, language);
        primprocedure1.setName(s);
        j = primprocedure1.isApplicable(atype);
        if (j < 0 || j < i) goto _L6; else goto _L10
_L10:
        if (j <= i) goto _L12; else goto _L11
_L11:
        primprocedure = primprocedure1;
          goto _L13
_L9:
        if (!flag1) goto _L6; else goto _L14
_L14:
        if (!s5.equals("apply") && !s5.equals("apply$V")) goto _L6; else goto _L15
_L15:
        break MISSING_BLOCK_LABEL_346;
_L12:
        if (primprocedure == null) goto _L13; else goto _L16
_L16:
        primprocedure = (PrimProcedure)MethodProc.mostSpecific(primprocedure, primprocedure1);
        if (primprocedure == null && i > 0)
        {
            return null;
        }
          goto _L13
        SecurityException securityexception;
        securityexception;
_L2:
        return primprocedure;
_L8:
        boolean flag2 = false;
_L18:
        if (!flag2)
        {
            flag1 = false;
            if (flag)
            {
                i = -1;
                flag1 = false;
                primprocedure = null;
                flag = false;
            }
        }
          goto _L17
_L13:
        i = j;
        flag = flag2;
          goto _L6
        flag2 = true;
          goto _L18
    }

    public static PrimProcedure getMethodFor(ClassType classtype, String s, Declaration declaration, Expression aexpression[], Language language)
    {
        int i = aexpression.length;
        Type atype[] = new Type[i];
        for (int j = i; --j >= 0;)
        {
            atype[j] = aexpression[j].getType();
        }

        return getMethodFor(classtype, s, declaration, atype, language);
    }

    public static PrimProcedure getMethodFor(Procedure procedure, Declaration declaration, Type atype[], Language language)
    {
        if (!(procedure instanceof GenericProc)) goto _L2; else goto _L1
_L1:
        MethodProc amethodproc[];
        int i;
        GenericProc genericproc = (GenericProc)procedure;
        amethodproc = genericproc.methods;
        procedure = null;
        i = genericproc.count;
_L10:
        if (--i < 0) goto _L4; else goto _L3
_L3:
        if (amethodproc[i].isApplicable(atype) < 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (procedure == null) goto _L6; else goto _L5
_L5:
        PrimProcedure primprocedure = null;
_L8:
        return primprocedure;
_L6:
        procedure = amethodproc[i];
        continue; /* Loop/switch isn't completed */
_L4:
        if (procedure == null)
        {
            return null;
        }
_L2:
        if (!(procedure instanceof PrimProcedure))
        {
            break; /* Loop/switch isn't completed */
        }
        primprocedure = (PrimProcedure)procedure;
        if (primprocedure.isApplicable(atype) >= 0) goto _L8; else goto _L7
_L7:
        Class class1 = getProcedureClass(procedure);
        if (class1 == null)
        {
            return null;
        }
        return getMethodFor((ClassType)Type.make(class1), procedure.getName(), declaration, atype, language);
        if (true) goto _L10; else goto _L9
_L9:
    }

    public static PrimProcedure getMethodFor(Procedure procedure, Declaration declaration, Expression aexpression[], Language language)
    {
        int i = aexpression.length;
        Type atype[] = new Type[i];
        for (int j = i; --j >= 0;)
        {
            atype[j] = aexpression[j].getType();
        }

        return getMethodFor(procedure, declaration, atype, language);
    }

    public static PrimProcedure getMethodFor(Procedure procedure, Expression aexpression[])
    {
        return getMethodFor(procedure, null, aexpression, Language.getDefaultLanguage());
    }

    public static PrimProcedure getMethodFor(Class class1, String s, Declaration declaration, Expression aexpression[], Language language)
    {
        return getMethodFor((ClassType)Type.make(class1), s, declaration, aexpression, language);
    }

    public static Class getProcedureClass(Object obj)
    {
        Class class1;
        ClassLoader classloader;
        ClassLoader classloader1;
        if (obj instanceof ModuleMethod)
        {
            class1 = ((ModuleMethod)obj).module.getClass();
        } else
        {
            class1 = obj.getClass();
        }
        classloader = class1.getClassLoader();
        classloader1 = systemClassLoader;
        if (classloader == classloader1)
        {
            return class1;
        }
        break MISSING_BLOCK_LABEL_45;
        SecurityException securityexception;
        securityexception;
        return null;
    }

    private void init(Method method1)
    {
        method = method1;
        Type atype[];
        if ((8 & method1.getModifiers()) != 0)
        {
            op_code = 184;
        } else
        {
            ClassType classtype = method1.getDeclaringClass();
            if (mode == 'P')
            {
                op_code = 183;
            } else
            {
                mode = 'V';
                if ("<init>".equals(method1.getName()))
                {
                    op_code = 183;
                } else
                if ((0x200 & classtype.getModifiers()) != 0)
                {
                    op_code = 185;
                } else
                {
                    op_code = 182;
                }
            }
        }
        atype = method1.getParameterTypes();
        if (isConstructor() && method1.getDeclaringClass().hasOuterLink())
        {
            int i = -1 + atype.length;
            Type atype1[] = new Type[i];
            System.arraycopy(atype, 1, atype1, 0, i);
            atype = atype1;
        }
        argTypes = atype;
    }

    public static PrimProcedure makeBuiltinBinary(int i, Type type)
    {
        return new PrimProcedure(i, type, new Type[] {
            type, type
        });
    }

    public static PrimProcedure makeBuiltinUnary(int i, Type type)
    {
        return new PrimProcedure(i, type, new Type[] {
            type
        });
    }

    public static boolean takesContext(Method method1)
    {
        return method1.getName().endsWith("$X");
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        boolean flag;
        boolean flag1;
        Class class2;
        Class aclass[];
        int i1;
        int j1;
        int i = argTypes.length;
        flag = isConstructor();
        InvocationTargetException invocationtargetexception;
        if (flag && method.getDeclaringClass().hasOuterLink())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (member != null) goto _L2; else goto _L1
_L1:
        class2 = method.getDeclaringClass().getReflectClass();
        int l;
        if (flag1)
        {
            l = 1;
        } else
        {
            l = 0;
        }
        try
        {
            aclass = new Class[l + i];
        }
        // Misplaced declaration of an exception variable
        catch (InvocationTargetException invocationtargetexception)
        {
            throw invocationtargetexception.getTargetException();
        }
        i1 = i;
          goto _L3
_L15:
        aclass[j1 + i1] = argTypes[i1].getReflectClass();
          goto _L3
_L13:
        j1 = 0;
        continue; /* Loop/switch isn't completed */
_L11:
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_142;
        }
        aclass[0] = method.getDeclaringClass().getOuterLinkType().getReflectClass();
        if (!flag) goto _L5; else goto _L4
_L4:
        member = class2.getConstructor(aclass);
_L2:
        if (!flag) goto _L7; else goto _L6
_L6:
        Object aobj[] = callcontext.values;
        if (!flag1)
        {
            break MISSING_BLOCK_LABEL_217;
        }
        Object aobj1[];
        int j = 1 + aobj.length;
        aobj1 = new Object[j];
        System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj1)), 1, j - 1);
        aobj1[0] = ((PairClassType)callcontext.value1).staticLink;
        aobj = aobj1;
        Object obj = ((Constructor)member).newInstance(aobj);
_L9:
        if (!takesContext())
        {
            callcontext.consumer.writeObject(obj);
            return;
        }
        break; /* Loop/switch isn't completed */
_L5:
        if (method == Type.clone_method) goto _L2; else goto _L8
_L8:
        member = class2.getMethod(method.getName(), aclass);
          goto _L2
_L7:
label0:
        {
            if (method != Type.clone_method)
            {
                break label0;
            }
            Object obj2 = callcontext.value1;
            Class class1 = obj2.getClass().getComponentType();
            int k = Array.getLength(obj2);
            obj = Array.newInstance(class1, k);
            System.arraycopy(obj2, 0, obj, 0, k);
        }
          goto _L9
        Object obj1 = retType.coerceToObject(((java.lang.reflect.Method)member).invoke(callcontext.value1, callcontext.values));
        obj = obj1;
          goto _L9
_L3:
        if (--i1 < 0) goto _L11; else goto _L10
_L10:
        if (!flag1) goto _L13; else goto _L12
_L12:
        j1 = 1;
        if (true) goto _L15; else goto _L14
_L14:
    }

    void compile(Type type, ApplyExp applyexp, Compilation compilation, Target target)
    {
        Expression aexpression[] = applyexp.getArgs();
        CodeAttr codeattr = compilation.getCode();
        Type type1 = retType;
        int i;
        if (isConstructor())
        {
            Method method2 = method;
            ClassType classtype1 = null;
            if (method2 != null)
            {
                classtype1 = method.getDeclaringClass();
            }
            if (classtype1.hasOuterLink())
            {
                ClassExp.loadSuperStaticLink(aexpression[0], classtype1, compilation);
            }
            type = null;
            i = 1;
        } else
        if (opcode() == 183 && mode == 'P' && "<init>".equals(method.getName()))
        {
            Method method1 = method;
            ClassType classtype = null;
            boolean flag2;
            if (method1 != null)
            {
                classtype = method.getDeclaringClass();
            }
            flag2 = classtype.hasOuterLink();
            i = 0;
            if (flag2)
            {
                codeattr.emitPushThis();
                codeattr.emitLoad(codeattr.getCurrentScope().getVariable(1));
                i = 1;
                type = null;
            }
        } else
        {
            boolean flag = takesTarget();
            i = 0;
            if (flag)
            {
                boolean flag1 = method.getStaticFlag();
                i = 0;
                if (flag1)
                {
                    i = 1;
                }
            }
        }
        compileArgs(aexpression, i, type, compilation);
        if (method == null)
        {
            codeattr.emitPrimop(opcode(), aexpression.length, retType);
            target.compileFromStack(compilation, type1);
            return;
        } else
        {
            compileInvoke(compilation, method, target, applyexp.isTailCall(), op_code, type1);
            return;
        }
    }

    public void compile(ApplyExp applyexp, Compilation compilation, Target target)
    {
        CodeAttr codeattr = compilation.getCode();
        ClassType classtype;
        Expression aexpression[];
        if (method == null)
        {
            classtype = null;
        } else
        {
            classtype = method.getDeclaringClass();
        }
        aexpression = applyexp.getArgs();
        if (isConstructor())
        {
            if (applyexp.getFlag(8))
            {
                int i = aexpression.length;
                compilation.letStart();
                Expression aexpression1[] = new Expression[i];
                aexpression1[0] = aexpression[0];
                for (int j = 1; j < i; j++)
                {
                    Expression expression = aexpression[j];
                    Declaration declaration = compilation.letVariable(null, expression.getType(), expression);
                    declaration.setCanRead(true);
                    aexpression1[j] = new ReferenceExp(declaration);
                }

                compilation.letEnter();
                compilation.letDone(new ApplyExp(applyexp.func, aexpression1)).compile(compilation, target);
                return;
            }
            codeattr.emitNew(classtype);
            codeattr.emitDup(classtype);
        }
        String s = WrongArguments.checkArgCount(this, aexpression.length);
        if (s != null)
        {
            compilation.error('e', s);
        }
        if (getStaticFlag())
        {
            classtype = null;
        }
        compile(((Type) (classtype)), applyexp, compilation, target);
    }

    public Method getMethod()
    {
        return method;
    }

    public String getName()
    {
        String s = super.getName();
        if (s != null)
        {
            return s;
        } else
        {
            String s1 = getVerboseName();
            setName(s1);
            return s1;
        }
    }

    public Type getParameterType(int i)
    {
        if (takesTarget())
        {
            if (i == 0)
            {
                if (isConstructor())
                {
                    return Type.objectType;
                } else
                {
                    return method.getDeclaringClass();
                }
            }
            i--;
        }
        int j = argTypes.length;
        if (i < j - 1)
        {
            return argTypes[i];
        }
        boolean flag = takesVarArgs();
        if (i < j && !flag)
        {
            return argTypes[i];
        }
        Type type = argTypes[j - 1];
        if (type instanceof ArrayType)
        {
            return ((ArrayType)type).getComponentType();
        } else
        {
            return Type.objectType;
        }
    }

    public final Type[] getParameterTypes()
    {
        return argTypes;
    }

    public Type getReturnType()
    {
        return retType;
    }

    public Type getReturnType(Expression aexpression[])
    {
        return retType;
    }

    public final boolean getStaticFlag()
    {
        return method == null || method.getStaticFlag() || isConstructor();
    }

    public String getVerboseName()
    {
        StringBuffer stringbuffer = new StringBuffer(100);
        if (method == null)
        {
            stringbuffer.append("<op ");
            stringbuffer.append(op_code);
            stringbuffer.append('>');
        } else
        {
            stringbuffer.append(method.getDeclaringClass().getName());
            stringbuffer.append('.');
            stringbuffer.append(method.getName());
        }
        stringbuffer.append('(');
        for (int i = 0; i < argTypes.length; i++)
        {
            if (i > 0)
            {
                stringbuffer.append(',');
            }
            stringbuffer.append(argTypes[i].getName());
        }

        stringbuffer.append(')');
        return stringbuffer.toString();
    }

    public int isApplicable(Type atype[])
    {
        int i = super.isApplicable(atype);
        int j = atype.length;
        if (i == -1 && method != null && (0x80 & method.getModifiers()) != 0 && j > 0 && (atype[j - 1] instanceof ArrayType))
        {
            Type atype1[] = new Type[j];
            System.arraycopy(atype, 0, atype1, 0, j - 1);
            atype1[j - 1] = ((ArrayType)atype[j - 1]).getComponentType();
            i = super.isApplicable(atype1);
        }
        return i;
    }

    public final boolean isConstructor()
    {
        return opcode() == 183 && mode != 'P';
    }

    public boolean isSideEffectFree()
    {
        return sideEffectFree;
    }

    public boolean isSpecial()
    {
        return mode == 'P';
    }

    public int match0(CallContext callcontext)
    {
        return matchN(ProcedureN.noArgs, callcontext);
    }

    public int match1(Object obj, CallContext callcontext)
    {
        return matchN(new Object[] {
            obj
        }, callcontext);
    }

    public int match2(Object obj, Object obj1, CallContext callcontext)
    {
        return matchN(new Object[] {
            obj, obj1
        }, callcontext);
    }

    public int match3(Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        return matchN(new Object[] {
            obj, obj1, obj2
        }, callcontext);
    }

    public int match4(Object obj, Object obj1, Object obj2, Object obj3, CallContext callcontext)
    {
        return matchN(new Object[] {
            obj, obj1, obj2, obj3
        }, callcontext);
    }

    public int matchN(Object aobj[], CallContext callcontext)
    {
        int i = aobj.length;
        boolean flag = takesVarArgs();
        int j = minArgs();
        if (i < j)
        {
            return 0xfff10000 | j;
        }
        if (!flag && i > j)
        {
            return 0xfff20000 | j;
        }
        int k = argTypes.length;
        int l;
        boolean flag1;
        Object aobj1[];
        Object obj;
        Object aobj2[];
        Object obj1;
        int i1;
        if (takesTarget() || isConstructor())
        {
            l = 1;
        } else
        {
            l = 0;
        }
        flag1 = takesContext();
        aobj1 = new Object[k];
        if (flag1)
        {
            k--;
            aobj1[k] = callcontext;
        }
        obj = null;
        aobj2 = null;
        if (flag)
        {
            Type type = argTypes[k - 1];
            ClassType classtype;
            Object obj4;
            if (type == Compilation.scmListType || type == LangObjType.listType)
            {
                aobj1[k - 1] = LList.makeList(aobj, j);
                obj = Type.objectType;
            } else
            {
                obj = ((ArrayType)type).getComponentType();
                aobj2 = (Object[])(Object[])Array.newInstance(((Type) (obj)).getReflectClass(), i - j);
                aobj1[k - 1] = ((Object) (aobj2));
            }
        }
        if (isConstructor())
        {
            obj1 = aobj[0];
        } else
        if (l != 0)
        {
            Object obj5;
            try
            {
                obj5 = method.getDeclaringClass().coerceFromObject(aobj[0]);
            }
            catch (ClassCastException classcastexception1)
            {
                return 0xfff40001;
            }
            obj1 = obj5;
        } else
        {
            obj1 = null;
        }
        i1 = l;
        do
        {
            if (i1 >= aobj.length)
            {
                break;
            }
            Object obj2 = aobj[i1];
            Object obj3;
            if (i1 < j)
            {
                obj3 = argTypes[i1 - l];
            } else
            {
                obj3 = obj;
            }
            classtype = Type.objectType;
            if (obj3 != classtype)
            {
                try
                {
                    obj4 = ((Type) (obj3)).coerceFromObject(obj2);
                }
                catch (ClassCastException classcastexception)
                {
                    return 0xfff40000 | i1 + 1;
                }
                obj2 = obj4;
            }
            if (i1 < j)
            {
                aobj1[i1 - l] = obj2;
            } else
            if (aobj2 != null)
            {
                aobj2[i1 - j] = obj2;
            }
            i1++;
        } while (true);
        callcontext.value1 = obj1;
        callcontext.values = aobj1;
        callcontext.proc = this;
        return 0;
    }

    public int numArgs()
    {
        int i = argTypes.length;
        if (takesTarget())
        {
            i++;
        }
        if (takesContext())
        {
            i--;
        }
        if (takesVarArgs())
        {
            return -4096 + (i - 1);
        } else
        {
            return i + (i << 12);
        }
    }

    public final int opcode()
    {
        return op_code;
    }

    public void print(PrintWriter printwriter)
    {
        printwriter.print("#<primitive procedure ");
        printwriter.print(toString());
        printwriter.print('>');
    }

    public void setReturnType(Type type)
    {
        retType = type;
    }

    public void setSideEffectFree()
    {
        sideEffectFree = true;
    }

    public boolean takesContext()
    {
        return method != null && takesContext(method);
    }

    public boolean takesTarget()
    {
        return mode != 0;
    }

    public boolean takesVarArgs()
    {
        Method method1;
        boolean flag;
        method1 = method;
        flag = false;
        if (method1 == null) goto _L2; else goto _L1
_L1:
        if ((0x80 & method.getModifiers()) == 0) goto _L4; else goto _L3
_L3:
        flag = true;
_L2:
        return flag;
_L4:
        boolean flag1;
        String s = method.getName();
        if (s.endsWith("$V"))
        {
            break; /* Loop/switch isn't completed */
        }
        flag1 = s.endsWith("$V$X");
        flag = false;
        if (!flag1) goto _L2; else goto _L5
_L5:
        return true;
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(100);
        String s;
        if (retType == null)
        {
            s = "<unknown>";
        } else
        {
            s = retType.getName();
        }
        stringbuffer.append(s);
        stringbuffer.append(' ');
        stringbuffer.append(getVerboseName());
        return stringbuffer.toString();
    }

}
