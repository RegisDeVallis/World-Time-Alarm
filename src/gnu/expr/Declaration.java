// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.Named;
import gnu.mapping.Namespace;
import gnu.mapping.OutPort;
import gnu.mapping.Symbol;
import gnu.mapping.WrappedException;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.SourceLocator;

// Referenced classes of package gnu.expr:
//            QuoteExp, ReferenceExp, PrimProcedure, Compilation, 
//            ScopeExp, Expression, LambdaExp, ModuleExp, 
//            ThisExp, IgnoreTarget, AccessExp, FluidLetExp, 
//            Target, Language, ClassExp, LitTable, 
//            Literal, BindingInitializer, TypeValue, ApplyExp

public class Declaration
    implements SourceLocator
{

    static final int CAN_CALL = 4;
    static final int CAN_READ = 2;
    static final int CAN_WRITE = 8;
    public static final long CLASS_ACCESS_FLAGS = 0x603000000L;
    public static final int EARLY_INIT = 0x20000000;
    public static final long ENUM_ACCESS = 0x200000000L;
    public static final int EXPORT_SPECIFIED = 1024;
    public static final int EXTERNAL_ACCESS = 0x80000;
    public static final long FIELD_ACCESS_FLAGS = 0x78f000000L;
    public static final int FIELD_OR_METHOD = 0x100000;
    public static final long FINAL_ACCESS = 0x400000000L;
    static final int INDIRECT_BINDING = 1;
    public static final int IS_ALIAS = 256;
    public static final int IS_CONSTANT = 16384;
    public static final int IS_DYNAMIC = 0x10000000;
    static final int IS_FLUID = 16;
    public static final int IS_IMPORTED = 0x20000;
    public static final int IS_NAMESPACE_PREFIX = 0x200000;
    static final int IS_SIMPLE = 64;
    public static final int IS_SINGLE_VALUE = 0x40000;
    public static final int IS_SYNTAX = 32768;
    public static final int IS_UNKNOWN = 0x10000;
    public static final long METHOD_ACCESS_FLAGS = 0x40f000000L;
    public static final int MODULE_REFERENCE = 0x40000000;
    public static final int NONSTATIC_SPECIFIED = 4096;
    public static final int NOT_DEFINING = 512;
    public static final int PACKAGE_ACCESS = 0x8000000;
    static final int PRIVATE = 32;
    public static final int PRIVATE_ACCESS = 0x1000000;
    public static final String PRIVATE_PREFIX = "$Prvt$";
    public static final int PRIVATE_SPECIFIED = 0x1000000;
    static final int PROCEDURE = 128;
    public static final int PROTECTED_ACCESS = 0x2000000;
    public static final int PUBLIC_ACCESS = 0x4000000;
    public static final int STATIC_SPECIFIED = 2048;
    public static final long TRANSIENT_ACCESS = 0x100000000L;
    public static final int TYPE_SPECIFIED = 8192;
    static final String UNKNOWN_PREFIX = "loc$";
    public static final long VOLATILE_ACCESS = 0x80000000L;
    static int counter;
    public Declaration base;
    public ScopeExp context;
    int evalIndex;
    public Field field;
    String filename;
    public ApplyExp firstCall;
    protected long flags;
    protected int id;
    Method makeLocationMethod;
    Declaration next;
    Declaration nextCapturedVar;
    int position;
    Object symbol;
    protected Type type;
    protected Expression typeExp;
    protected Expression value;
    Variable var;

    protected Declaration()
    {
        int i = 1 + counter;
        counter = i;
        id = i;
        value = QuoteExp.undefined_exp;
        flags = 64L;
        makeLocationMethod = null;
    }

    public Declaration(Variable variable)
    {
        this(variable.getName(), variable.getType());
        var = variable;
    }

    public Declaration(Object obj)
    {
        int i = 1 + counter;
        counter = i;
        id = i;
        value = QuoteExp.undefined_exp;
        flags = 64L;
        makeLocationMethod = null;
        setName(obj);
    }

    public Declaration(Object obj, Field field1)
    {
        this(obj, field1.getType());
        field = field1;
        setSimple(false);
    }

    public Declaration(Object obj, Type type1)
    {
        int i = 1 + counter;
        counter = i;
        id = i;
        value = QuoteExp.undefined_exp;
        flags = 64L;
        makeLocationMethod = null;
        setName(obj);
        setType(type1);
    }

    public static Declaration followAliases(Declaration declaration)
    {
_L6:
        if (declaration == null || !declaration.isAlias()) goto _L2; else goto _L1
_L1:
        Expression expression = declaration.getValue();
        if (expression instanceof ReferenceExp) goto _L3; else goto _L2
_L2:
        Declaration declaration1;
        return declaration;
_L3:
        if ((declaration1 = ((ReferenceExp)expression).binding) == null) goto _L2; else goto _L4
_L4:
        declaration = declaration1;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static Declaration getDeclaration(Named named)
    {
        return getDeclaration(named, named.getName());
    }

    public static Declaration getDeclaration(Object obj, String s)
    {
        Field field1 = null;
        if (s != null)
        {
            Class class1 = PrimProcedure.getProcedureClass(obj);
            field1 = null;
            if (class1 != null)
            {
                field1 = ((ClassType)Type.make(class1)).getDeclaredField(Compilation.mangleNameIfNeeded(s));
            }
        }
        if (field1 != null)
        {
            int i = field1.getModifiers();
            if ((i & 8) != 0)
            {
                Declaration declaration = new Declaration(s, field1);
                declaration.noteValue(new QuoteExp(obj));
                if ((i & 0x10) != 0)
                {
                    declaration.setFlag(16384L);
                }
                return declaration;
            }
        }
        return null;
    }

    public static Declaration getDeclarationFromStatic(String s, String s1)
    {
        Declaration declaration = new Declaration(s1, ClassType.make(s).getDeclaredField(s1));
        declaration.setFlag(18432L);
        return declaration;
    }

    public static Declaration getDeclarationValueFromStatic(String s, String s1, String s2)
    {
        Declaration declaration;
        try
        {
            Object obj = Class.forName(s).getDeclaredField(s1).get(null);
            declaration = new Declaration(s2, ClassType.make(s).getDeclaredField(s1));
            declaration.noteValue(new QuoteExp(obj));
            declaration.setFlag(18432L);
        }
        catch (Exception exception)
        {
            throw new WrappedException(exception);
        }
        return declaration;
    }

    public static final boolean isUnknown(Declaration declaration)
    {
        return declaration == null || declaration.getFlag(0x10000L);
    }

    public final Variable allocateVariable(CodeAttr codeattr)
    {
        if (!isSimple() || var == null)
        {
            Object obj = symbol;
            String s = null;
            if (obj != null)
            {
                s = Compilation.mangleNameIfNeeded(getName());
            }
            if (isAlias() && (getValue() instanceof ReferenceExp))
            {
                Declaration declaration = followAliases(this);
                Variable variable;
                if (declaration == null)
                {
                    variable = null;
                } else
                {
                    variable = declaration.var;
                }
                var = variable;
            } else
            {
                Object obj1;
                if (isIndirectBinding())
                {
                    obj1 = Compilation.typeLocation;
                } else
                {
                    obj1 = getType().getImplementationType();
                }
                var = context.getVarScope().addVariable(codeattr, ((Type) (obj1)), s);
            }
        }
        return var;
    }

    public void compileStore(Compilation compilation)
    {
        CodeAttr codeattr = compilation.getCode();
        if (isSimple())
        {
            codeattr.emitStore(getVariable());
            return;
        }
        if (!field.getStaticFlag())
        {
            loadOwningObject(null, compilation);
            codeattr.emitSwap();
            codeattr.emitPutField(field);
            return;
        } else
        {
            codeattr.emitPutStatic(field);
            return;
        }
    }

    public short getAccessFlags(short word0)
    {
        short word1;
        if (getFlag(0xf000000L))
        {
            boolean flag = getFlag(0x1000000L);
            word1 = 0;
            if (flag)
            {
                word1 = (short)2;
            }
            if (getFlag(0x2000000L))
            {
                word1 |= 4;
            }
            if (getFlag(0x4000000L))
            {
                word1 |= 1;
            }
        } else
        {
            word1 = word0;
        }
        if (getFlag(0x80000000L))
        {
            word1 |= 0x40;
        }
        if (getFlag(0x100000000L))
        {
            word1 |= 0x80;
        }
        if (getFlag(0x200000000L))
        {
            word1 |= 0x4000;
        }
        if (getFlag(0x400000000L))
        {
            word1 |= 0x10;
        }
        return word1;
    }

    public final boolean getCanCall()
    {
        return (4L & flags) != 0L;
    }

    public final boolean getCanRead()
    {
        return (2L & flags) != 0L;
    }

    public final boolean getCanWrite()
    {
        return (8L & flags) != 0L;
    }

    public int getCode()
    {
        return id;
    }

    public final int getColumnNumber()
    {
        int i = 0xfff & position;
        if (i == 0)
        {
            i = -1;
        }
        return i;
    }

    public final Object getConstantValue()
    {
        Expression expression = getValue();
        if (!(expression instanceof QuoteExp) || expression == QuoteExp.undefined_exp)
        {
            return null;
        } else
        {
            return ((QuoteExp)expression).getValue();
        }
    }

    public final ScopeExp getContext()
    {
        return context;
    }

    public final String getFileName()
    {
        return filename;
    }

    public final boolean getFlag(long l)
    {
        return (l & flags) != 0L;
    }

    public final int getLineNumber()
    {
        int i = position >> 12;
        if (i == 0)
        {
            i = -1;
        }
        return i;
    }

    public final String getName()
    {
        if (symbol == null)
        {
            return null;
        }
        if (symbol instanceof Symbol)
        {
            return ((Symbol)symbol).getName();
        } else
        {
            return symbol.toString();
        }
    }

    public String getPublicId()
    {
        return null;
    }

    public final Object getSymbol()
    {
        return symbol;
    }

    public String getSystemId()
    {
        return filename;
    }

    public final Type getType()
    {
        if (type == null)
        {
            setType(Type.objectType);
        }
        return type;
    }

    public final Expression getTypeExp()
    {
        if (typeExp == null)
        {
            setType(Type.objectType);
        }
        return typeExp;
    }

    public final Expression getValue()
    {
label0:
        {
            {
                if (value != QuoteExp.undefined_exp)
                {
                    break label0;
                }
                Throwable throwable;
                Object obj;
                Type type1;
                if (field != null && (0x18 & field.getModifiers()) == 24 && !isIndirectBinding())
                {
                    try
                    {
                        value = new QuoteExp(field.getReflectField().get(null));
                    }
                    catch (Throwable throwable1) { }
                }
            }
            return value;
        }
        if ((value instanceof QuoteExp) && getFlag(8192L) && value.getType() != type)
        {
            try
            {
                obj = ((QuoteExp)value).getValue();
                type1 = getType();
                value = new QuoteExp(type1.coerceFromObject(obj), type1);
            }
            // Misplaced declaration of an exception variable
            catch (Throwable throwable) { }
        }
        if (false)
        {
        } else
        {
            break MISSING_BLOCK_LABEL_61;
        }
    }

    public Variable getVariable()
    {
        return var;
    }

    public final boolean hasConstantValue()
    {
        Expression expression = getValue();
        return (expression instanceof QuoteExp) && expression != QuoteExp.undefined_exp;
    }

    public boolean ignorable()
    {
        if (!getCanRead() && !isPublic() && (!getCanWrite() || !getFlag(0x10000L)))
        {
            if (!getCanCall())
            {
                return true;
            }
            Expression expression = getValue();
            if (expression != null && (expression instanceof LambdaExp))
            {
                LambdaExp lambdaexp = (LambdaExp)expression;
                if (!lambdaexp.isHandlingTailCalls() || lambdaexp.getInlineOnly())
                {
                    return true;
                }
            }
        }
        return false;
    }

    public final boolean isAlias()
    {
        return (256L & flags) != 0L;
    }

    public boolean isCompiletimeConstant()
    {
        return getFlag(16384L) && hasConstantValue();
    }

    public final boolean isFluid()
    {
        return (16L & flags) != 0L;
    }

    public final boolean isIndirectBinding()
    {
        return (1L & flags) != 0L;
    }

    public final boolean isLexical()
    {
        return (0x10010010L & flags) == 0L;
    }

    public final boolean isNamespaceDecl()
    {
        return (0x200000L & flags) != 0L;
    }

    public final boolean isPrivate()
    {
        return (32L & flags) != 0L;
    }

    public final boolean isProcedureDecl()
    {
        return (128L & flags) != 0L;
    }

    public final boolean isPublic()
    {
        return (context instanceof ModuleExp) && (32L & flags) == 0L;
    }

    public final boolean isSimple()
    {
        return (64L & flags) != 0L;
    }

    public boolean isStableSourceLocation()
    {
        return true;
    }

    public boolean isStatic()
    {
        boolean flag = true;
        if (field != null)
        {
            flag = field.getStaticFlag();
        } else
        if (!getFlag(2048L) && !isCompiletimeConstant())
        {
            if (getFlag(4096L))
            {
                return false;
            }
            LambdaExp lambdaexp = context.currentLambda();
            if (!(lambdaexp instanceof ModuleExp) || !((ModuleExp)lambdaexp).isStatic())
            {
                return false;
            }
        }
        return flag;
    }

    public final boolean isThisParameter()
    {
        return symbol == ThisExp.THIS_NAME;
    }

    public void load(AccessExp accessexp, int i, Compilation compilation, Target target)
    {
        Declaration declaration;
        CodeAttr codeattr;
        Object obj;
        if (target instanceof IgnoreTarget)
        {
            return;
        }
        if (accessexp == null)
        {
            declaration = null;
        } else
        {
            declaration = accessexp.contextDecl();
        }
        if (isAlias() && (value instanceof ReferenceExp))
        {
            ReferenceExp referenceexp = (ReferenceExp)value;
            Declaration declaration1 = referenceexp.binding;
            if (declaration1 != null && ((i & 2) == 0 || declaration1.isIndirectBinding()) && (declaration == null || !declaration1.needsContext()))
            {
                declaration1.load(((AccessExp) (referenceexp)), i, compilation, target);
                return;
            }
        }
        if (isFluid() && (context instanceof FluidLetExp))
        {
            base.load(accessexp, i, compilation, target);
            return;
        }
        codeattr = compilation.getCode();
        obj = getType();
        if (isIndirectBinding() || (i & 2) == 0) goto _L2; else goto _L1
_L1:
        if (field == null)
        {
            throw new Error((new StringBuilder()).append("internal error: cannot take location of ").append(this).toString());
        }
        boolean flag3 = compilation.immediate;
        ClassType classtype1;
        Method method1;
        if (field.getStaticFlag())
        {
            classtype1 = ClassType.make("gnu.kawa.reflect.StaticFieldLocation");
            int i1;
            if (flag3)
            {
                i1 = 1;
            } else
            {
                i1 = 2;
            }
            method1 = classtype1.getDeclaredMethod("make", i1);
        } else
        {
            classtype1 = ClassType.make("gnu.kawa.reflect.FieldLocation");
            byte byte0;
            if (flag3)
            {
                byte0 = 2;
            } else
            {
                byte0 = 3;
            }
            method1 = classtype1.getDeclaredMethod("make", byte0);
            loadOwningObject(declaration, compilation);
        }
        if (flag3)
        {
            compilation.compileConstant(this);
        } else
        {
            compilation.compileConstant(field.getDeclaringClass().getName());
            compilation.compileConstant(field.getName());
        }
        codeattr.emitInvokeStatic(method1);
        obj = classtype1;
_L4:
        target.compileFromStack(compilation, ((Type) (obj)));
        return;
_L2:
        if (field != null)
        {
            compilation.usedClass(field.getDeclaringClass());
            compilation.usedClass(field.getType());
            String s;
            int j;
            ClassType classtype;
            boolean flag;
            int k;
            Label label;
            Label label1;
            Label label2;
            if (!field.getStaticFlag())
            {
                loadOwningObject(declaration, compilation);
                codeattr.emitGetField(field);
            } else
            {
                codeattr.emitGetStatic(field);
            }
        } else
        if (isIndirectBinding() && compilation.immediate && getVariable() == null)
        {
            Environment environment = Environment.getCurrent();
            Symbol symbol1;
            boolean flag1;
            Object obj2;
            if (symbol instanceof Symbol)
            {
                symbol1 = (Symbol)symbol;
            } else
            {
                symbol1 = environment.getSymbol(symbol.toString());
            }
            flag1 = isProcedureDecl();
            obj2 = null;
            if (flag1)
            {
                boolean flag2 = compilation.getLanguage().hasSeparateFunctionNamespace();
                obj2 = null;
                if (flag2)
                {
                    obj2 = EnvironmentKey.FUNCTION;
                }
            }
            compilation.compileConstant(environment.getLocation(symbol1, obj2), Target.pushValue(Compilation.typeLocation));
        } else
        {
label0:
            {
                if (compilation.immediate)
                {
                    Object obj1 = getConstantValue();
                    if (obj1 != null)
                    {
                        compilation.compileConstant(obj1, target);
                        return;
                    }
                }
                if (value != QuoteExp.undefined_exp && ignorable() && (!(value instanceof LambdaExp) || !(((LambdaExp)value).outer instanceof ModuleExp)))
                {
                    value.compile(compilation, target);
                    return;
                }
                variable = getVariable();
                if (!(context instanceof ClassExp) || variable != null || getFlag(128L))
                {
                    break label0;
                }
                ClassExp classexp = (ClassExp)context;
                if (!classexp.isMakingClassPair())
                {
                    break label0;
                }
                String s1 = ClassExp.slotToMethodName("get", getName());
                Method method = classexp.type.getDeclaredMethod(s1, 0);
                classexp.loadHeapFrame(compilation);
                codeattr.emitInvoke(method);
            }
        }
_L5:
        if (!isIndirectBinding() || (i & 2) != 0) goto _L4; else goto _L3
_L3:
        if (accessexp == null)
        {
            break MISSING_BLOCK_LABEL_971;
        }
        s = accessexp.getFileName();
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_971;
        }
        j = accessexp.getLineNumber();
        if (j <= 0)
        {
            break MISSING_BLOCK_LABEL_971;
        }
        classtype = ClassType.make("gnu.mapping.UnboundLocationException");
        flag = codeattr.isInTry();
        k = accessexp.getColumnNumber();
        label = new Label(codeattr);
        label.define(codeattr);
        codeattr.emitInvokeVirtual(Compilation.getLocationMethod);
        label1 = new Label(codeattr);
        label1.define(codeattr);
        label2 = new Label(codeattr);
        label2.setTypes(codeattr);
        Variable variable;
        int l;
        if (flag)
        {
            codeattr.emitGoto(label2);
        } else
        {
            codeattr.setUnreachable();
        }
        l = 0;
        if (!flag)
        {
            l = codeattr.beginFragment(label2);
        }
        codeattr.addHandler(label, label1, classtype);
        codeattr.emitDup(classtype);
        codeattr.emitPushString(s);
        codeattr.emitPushInt(j);
        codeattr.emitPushInt(k);
        codeattr.emitInvokeVirtual(classtype.getDeclaredMethod("setLine", 3));
        codeattr.emitThrow();
        if (flag)
        {
            label2.define(codeattr);
        } else
        {
            codeattr.endFragment(l);
        }
_L6:
        obj = Type.pointer_type;
          goto _L4
        if (variable == null)
        {
            variable = allocateVariable(codeattr);
        }
        codeattr.emitLoad(variable);
          goto _L5
        codeattr.emitInvokeVirtual(Compilation.getLocationMethod);
          goto _L6
    }

    void loadOwningObject(Declaration declaration, Compilation compilation)
    {
        if (declaration == null)
        {
            declaration = base;
        }
        if (declaration != null)
        {
            declaration.load(null, 0, compilation, Target.pushObject);
            return;
        } else
        {
            getContext().currentLambda().loadHeapFrame(compilation);
            return;
        }
    }

    public void makeField(ClassType classtype, Compilation compilation, Expression expression)
    {
        boolean flag;
        boolean flag1;
        int i;
label0:
        {
            flag = needsExternalAccess();
            flag1 = getFlag(16384L);
            boolean flag2 = getFlag(8192L);
            if (compilation.immediate && (context instanceof ModuleExp) && !flag1 && !flag2)
            {
                setIndirectBinding(true);
            }
            if (!isPublic() && !flag)
            {
                boolean flag3 = compilation.immediate;
                i = 0;
                if (!flag3)
                {
                    break label0;
                }
            }
            i = false | true;
        }
        if (isStatic() || getFlag(0x10010010L) && isIndirectBinding() && !isAlias() || (expression instanceof ClassExp) && !((LambdaExp)expression).getNeedsClosureEnv())
        {
            i |= 8;
        }
        if ((isIndirectBinding() || flag1 && (shouldEarlyInit() || (context instanceof ModuleExp) && ((ModuleExp)context).staticInitRun())) && ((context instanceof ClassExp) || (context instanceof ModuleExp)))
        {
            i |= 0x10;
        }
        Object obj = getType().getImplementationType();
        if (isIndirectBinding() && !((Type) (obj)).isSubtype(Compilation.typeLocation))
        {
            obj = Compilation.typeLocation;
        }
        if (!ignorable())
        {
            String s = getName();
            String s1;
            int j;
            int k;
            if (s == null)
            {
                s1 = "$unnamed$0";
                j = -2 + s1.length();
            } else
            {
                s1 = Compilation.mangleNameIfNeeded(s);
                if (getFlag(0x10000L))
                {
                    s1 = (new StringBuilder()).append("loc$").append(s1).toString();
                }
                if (flag && !getFlag(0x40000000L))
                {
                    s1 = (new StringBuilder()).append("$Prvt$").append(s1).toString();
                }
                j = s1.length();
            }
            k = 0;
            StringBuilder stringbuilder;
            for (; classtype.getDeclaredField(s1) != null; s1 = stringbuilder.append(k).toString())
            {
                stringbuilder = (new StringBuilder()).append(s1.substring(0, j)).append('$');
                k++;
            }

            field = classtype.addField(s1, ((Type) (obj)), i);
            if (expression instanceof QuoteExp)
            {
                Object obj1 = ((QuoteExp)expression).getValue();
                if (field.getStaticFlag() && obj1.getClass().getName().equals(((Type) (obj)).getName()))
                {
                    Literal literal = compilation.litTable.findLiteral(obj1);
                    if (literal.field == null)
                    {
                        literal.assign(field, compilation.litTable);
                    }
                } else
                if ((obj instanceof PrimType) || "java.lang.String".equals(((Type) (obj)).getName()))
                {
                    if (obj1 instanceof Char)
                    {
                        obj1 = IntNum.make(((Char)obj1).intValue());
                    }
                    field.setConstantValue(obj1, classtype);
                    return;
                }
            }
        }
        if (!shouldEarlyInit() && (isIndirectBinding() || expression != null && !(expression instanceof ClassExp)))
        {
            BindingInitializer.create(this, expression, compilation);
        }
    }

    public void makeField(Compilation compilation, Expression expression)
    {
        setSimple(false);
        makeField(compilation.mainClass, compilation, expression);
    }

    Location makeIndirectLocationFor()
    {
        Symbol symbol1;
        if (symbol instanceof Symbol)
        {
            symbol1 = (Symbol)symbol;
        } else
        {
            symbol1 = Namespace.EmptyNamespace.getSymbol(symbol.toString().intern());
        }
        return Location.make(symbol1);
    }

    public void maybeIndirectBinding(Compilation compilation)
    {
        if (isLexical() && !(context instanceof ModuleExp) || context == compilation.mainLambda)
        {
            setIndirectBinding(true);
        }
    }

    public final boolean needsContext()
    {
        return base == null && field != null && !field.getStaticFlag();
    }

    public final boolean needsExternalAccess()
    {
        return (0x80020L & flags) == 0x80020L || (0x200020L & flags) == 0x200020L;
    }

    public boolean needsInit()
    {
        return !ignorable() && (value != QuoteExp.nullExp || base == null);
    }

    public final Declaration nextDecl()
    {
        return next;
    }

    public void noteValue(Expression expression)
    {
        if (value == QuoteExp.undefined_exp)
        {
            if (expression instanceof LambdaExp)
            {
                ((LambdaExp)expression).nameDecl = this;
            }
            value = expression;
        } else
        if (value != expression)
        {
            if (value instanceof LambdaExp)
            {
                ((LambdaExp)value).nameDecl = null;
            }
            value = null;
            return;
        }
    }

    public void printInfo(OutPort outport)
    {
        StringBuffer stringbuffer = new StringBuffer();
        printInfo(stringbuffer);
        outport.print(stringbuffer.toString());
    }

    public void printInfo(StringBuffer stringbuffer)
    {
        Expression expression;
        Type type1;
        stringbuffer.append(symbol);
        stringbuffer.append('/');
        stringbuffer.append(id);
        stringbuffer.append("/fl:");
        stringbuffer.append(Long.toHexString(flags));
        if (ignorable())
        {
            stringbuffer.append("(ignorable)");
        }
        expression = typeExp;
        type1 = getType();
        if (expression == null || (expression instanceof QuoteExp)) goto _L2; else goto _L1
_L1:
        stringbuffer.append("::");
        stringbuffer.append(expression);
_L4:
        if (base != null)
        {
            stringbuffer.append("(base:#");
            stringbuffer.append(base.id);
            stringbuffer.append(')');
        }
        return;
_L2:
        if (type != null && type1 != Type.pointer_type)
        {
            stringbuffer.append("::");
            stringbuffer.append(type1.getName());
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void pushIndirectBinding(Compilation compilation)
    {
        CodeAttr codeattr = compilation.getCode();
        codeattr.emitPushString(getName());
        if (makeLocationMethod == null)
        {
            Type atype[] = new Type[2];
            atype[0] = Type.pointer_type;
            atype[1] = Type.string_type;
            makeLocationMethod = Compilation.typeLocation.addMethod("make", atype, Compilation.typeLocation, 9);
        }
        codeattr.emitInvokeStatic(makeLocationMethod);
    }

    public final void setAlias(boolean flag)
    {
        setFlag(flag, 256L);
    }

    public final void setCanCall()
    {
        setFlag(true, 4L);
        if (base != null)
        {
            base.setCanRead();
        }
    }

    public final void setCanCall(boolean flag)
    {
        setFlag(flag, 4L);
    }

    public final void setCanRead()
    {
        setFlag(true, 2L);
        if (base != null)
        {
            base.setCanRead();
        }
    }

    public final void setCanRead(boolean flag)
    {
        setFlag(flag, 2L);
    }

    public final void setCanWrite()
    {
        flags = 8L | flags;
        if (base != null)
        {
            base.setCanRead();
        }
    }

    public final void setCanWrite(boolean flag)
    {
        if (flag)
        {
            flags = 8L | flags;
            return;
        } else
        {
            flags = -9L & flags;
            return;
        }
    }

    public void setCode(int i)
    {
        if (i >= 0)
        {
            throw new Error("code must be negative");
        } else
        {
            id = i;
            return;
        }
    }

    public final void setFile(String s)
    {
        filename = s;
    }

    public final void setFlag(long l)
    {
        flags = l | flags;
    }

    public final void setFlag(boolean flag, long l)
    {
        if (flag)
        {
            flags = l | flags;
            return;
        } else
        {
            flags = flags & (-1L ^ l);
            return;
        }
    }

    public final void setFluid(boolean flag)
    {
        setFlag(flag, 16L);
    }

    public final void setIndirectBinding(boolean flag)
    {
        setFlag(flag, 1L);
    }

    public final void setLine(int i)
    {
        setLine(i, 0);
    }

    public final void setLine(int i, int j)
    {
        if (i < 0)
        {
            i = 0;
        }
        if (j < 0)
        {
            j = 0;
        }
        position = j + (i << 12);
    }

    public final void setLocation(SourceLocator sourcelocator)
    {
        filename = sourcelocator.getFileName();
        setLine(sourcelocator.getLineNumber(), sourcelocator.getColumnNumber());
    }

    public final void setName(Object obj)
    {
        symbol = obj;
    }

    public final void setNext(Declaration declaration)
    {
        next = declaration;
    }

    public final void setPrivate(boolean flag)
    {
        setFlag(flag, 32L);
    }

    public final void setProcedureDecl(boolean flag)
    {
        setFlag(flag, 128L);
    }

    public final void setSimple(boolean flag)
    {
        setFlag(flag, 64L);
        if (var != null && !var.isParameter())
        {
            var.setSimple(flag);
        }
    }

    public final void setSymbol(Object obj)
    {
        symbol = obj;
    }

    public final void setSyntax()
    {
        setSimple(false);
        setFlag(0x2000c000L);
    }

    public final void setType(Type type1)
    {
        type = type1;
        if (var != null)
        {
            var.setType(type1);
        }
        typeExp = QuoteExp.getInstance(type1);
    }

    public final void setTypeExp(Expression expression)
    {
        typeExp = expression;
        Object obj;
        if (expression instanceof TypeValue)
        {
            obj = ((TypeValue)expression).getImplementationType();
        } else
        {
            obj = Language.getDefaultLanguage().getTypeFor(expression, false);
        }
        if (obj == null)
        {
            obj = Type.pointer_type;
        }
        type = ((Type) (obj));
        if (var != null)
        {
            var.setType(((Type) (obj)));
        }
    }

    public final void setValue(Expression expression)
    {
        value = expression;
    }

    boolean shouldEarlyInit()
    {
        return getFlag(0x20000000L) || isCompiletimeConstant();
    }

    public String toString()
    {
        return (new StringBuilder()).append("Declaration[").append(symbol).append('/').append(id).append(']').toString();
    }
}
