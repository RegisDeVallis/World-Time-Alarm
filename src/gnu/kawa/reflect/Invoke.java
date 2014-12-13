// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.reflect;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.Language;
import gnu.expr.PairClassType;
import gnu.expr.PrimProcedure;
import gnu.expr.TypeValue;
import gnu.kawa.lispexpr.ClassNamespace;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import java.lang.reflect.Array;

// Referenced classes of package gnu.kawa.reflect:
//            CompileInvoke, ClassMethods, SlotSet

public class Invoke extends ProcedureN
{

    public static final Invoke invoke = new Invoke("invoke", '*');
    public static final Invoke invokeSpecial = new Invoke("invoke-special", 'P');
    public static final Invoke invokeStatic = new Invoke("invoke-static", 'S');
    public static final Invoke make = new Invoke("make", 'N');
    char kind;
    Language language;

    public Invoke(String s, char c)
    {
        this(s, c, Language.getDefaultLanguage());
    }

    public Invoke(String s, char c, Language language1)
    {
        super(s);
        kind = c;
        language = language1;
        setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileInvoke:validateApplyInvoke");
    }

    public static PrimProcedure getStaticMethod(ClassType classtype, String s, Expression aexpression[])
    {
        gnu/kawa/reflect/Invoke;
        JVM INSTR monitorenter ;
        PrimProcedure primprocedure = CompileInvoke.getStaticMethod(classtype, s, aexpression);
        gnu/kawa/reflect/Invoke;
        JVM INSTR monitorexit ;
        return primprocedure;
        Exception exception;
        exception;
        throw exception;
    }

    public static Object invoke$V(Object aobj[])
        throws Throwable
    {
        return invoke.applyN(aobj);
    }

    public static Object invokeStatic$V(Object aobj[])
        throws Throwable
    {
        return invokeStatic.applyN(aobj);
    }

    public static Object make$V(Object aobj[])
        throws Throwable
    {
        return make.applyN(aobj);
    }

    public static ApplyExp makeInvokeStatic(ClassType classtype, String s, Expression aexpression[])
    {
        gnu/kawa/reflect/Invoke;
        JVM INSTR monitorenter ;
        PrimProcedure primprocedure = getStaticMethod(classtype, s, aexpression);
        if (primprocedure != null)
        {
            break MISSING_BLOCK_LABEL_61;
        }
        throw new RuntimeException((new StringBuilder()).append("missing or ambiguous method `").append(s).append("' in ").append(classtype.getName()).toString());
        Exception exception;
        exception;
        gnu/kawa/reflect/Invoke;
        JVM INSTR monitorexit ;
        throw exception;
        ApplyExp applyexp = new ApplyExp(primprocedure, aexpression);
        gnu/kawa/reflect/Invoke;
        JVM INSTR monitorexit ;
        return applyexp;
    }

    private static ObjectType typeFrom(Object obj, Invoke invoke1)
    {
        if (obj instanceof Class)
        {
            obj = Type.make((Class)obj);
        }
        if (obj instanceof ObjectType)
        {
            return (ObjectType)obj;
        }
        if ((obj instanceof String) || (obj instanceof FString))
        {
            return ClassType.make(obj.toString());
        }
        if (obj instanceof Symbol)
        {
            return ClassType.make(((Symbol)obj).getName());
        }
        if (obj instanceof ClassNamespace)
        {
            return ((ClassNamespace)obj).getClassType();
        } else
        {
            throw new WrongType(invoke1, 0, obj, "class-specifier");
        }
    }

    public void apply(CallContext callcontext)
        throws Throwable
    {
        Object aobj[];
label0:
        {
label1:
            {
                aobj = callcontext.getArgs();
                if (kind != 'S' && kind != 'V' && kind != 's' && kind != '*')
                {
                    break label0;
                }
                int i = aobj.length;
                Procedure.checkArgCount(this, i);
                Object obj = aobj[0];
                Object obj1;
                MethodProc methodproc;
                byte byte0;
                Object aobj1[];
                int j;
                int k;
                if (kind == 'S' || kind == 's')
                {
                    obj1 = typeFrom(obj, this);
                } else
                {
                    obj1 = Type.make(obj.getClass());
                }
                methodproc = lookupMethods((ObjectType)obj1, aobj[1]);
                if (kind == 'S')
                {
                    byte0 = 2;
                } else
                {
                    byte0 = 1;
                }
                aobj1 = new Object[i - byte0];
                if (kind != 'V')
                {
                    char c = kind;
                    k = 0;
                    if (c != '*')
                    {
                        break label1;
                    }
                }
                j = 0 + 1;
                aobj1[0] = aobj[0];
                k = j;
            }
            System.arraycopy(((Object) (aobj)), 2, ((Object) (aobj1)), k, i - 2);
            methodproc.checkN(aobj1, callcontext);
            return;
        }
        callcontext.writeValue(applyN(aobj));
    }

    public Object applyN(Object aobj[])
        throws Throwable
    {
        int i;
        Object obj1;
        Object obj7;
        if (kind == 'P')
        {
            throw new RuntimeException((new StringBuilder()).append(getName()).append(": invoke-special not allowed at run time").toString());
        }
        i = aobj.length;
        Procedure.checkArgCount(this, i);
        Object obj = aobj[0];
        Procedure procedure;
        int l3;
        Object aobj4[];
        if (kind != 'V' && kind != '*')
        {
            obj1 = typeFrom(obj, this);
        } else
        {
            obj1 = (ObjectType)Type.make(obj.getClass());
        }
        if (kind != 'N')
        {
            break MISSING_BLOCK_LABEL_424;
        }
        if (!(obj1 instanceof TypeValue)) goto _L2; else goto _L1
_L1:
        procedure = ((TypeValue)obj1).getConstructor();
        if (procedure == null) goto _L2; else goto _L3
_L3:
        l3 = i - 1;
        aobj4 = new Object[l3];
        System.arraycopy(((Object) (aobj)), 1, ((Object) (aobj4)), 0, l3);
        obj7 = procedure.applyN(aobj4);
_L7:
        return obj7;
_L2:
        Object obj2;
        Type type;
        int k2;
        if (obj1 instanceof PairClassType)
        {
            obj1 = ((PairClassType)obj1).instanceType;
        }
        boolean flag = obj1 instanceof ArrayType;
        obj2 = null;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_429;
        }
        type = ((ArrayType)obj1).getComponentType();
        k2 = -1 + aobj.length;
        if (k2 < 2 || !(aobj[1] instanceof Keyword)) goto _L5; else goto _L4
_L4:
        String s2 = ((Keyword)aobj[1]).getName();
        if (!"length".equals(s2) && !"size".equals(s2)) goto _L5; else goto _L6
_L6:
        int l2;
        int i3;
        boolean flag1;
        l2 = ((Number)aobj[2]).intValue();
        i3 = 3;
        flag1 = true;
_L8:
        obj7 = Array.newInstance(type.getReflectClass(), l2);
        int j3 = 0;
        while (i3 <= k2) 
        {
            Object obj8 = aobj[i3];
            if (flag1 && (obj8 instanceof Keyword) && i3 < k2)
            {
                String s1 = ((Keyword)obj8).getName();
                Object obj9;
                int k3;
                try
                {
                    k3 = Integer.parseInt(s1);
                }
                catch (Throwable throwable)
                {
                    throw new RuntimeException((new StringBuilder()).append("non-integer keyword '").append(s1).append("' in array constructor").toString());
                }
                j3 = k3;
                i3++;
                obj8 = aobj[i3];
            }
            obj9 = type.coerceFromObject(obj8);
            Array.set(obj7, j3, obj9);
            j3++;
            i3++;
        }
        if (true) goto _L7; else goto _L5
_L5:
        l2 = k2;
        i3 = 1;
        flag1 = false;
          goto _L8
        obj2 = aobj[1];
        MethodProc methodproc;
label0:
        {
label1:
            {
                methodproc = lookupMethods(((ObjectType) (obj1)), obj2);
                if (kind == 'N')
                {
                    break label0;
                }
                byte byte0;
                Object aobj3[];
                int i2;
                int j2;
                if (kind == 'S' || kind == 's')
                {
                    byte0 = 2;
                } else
                {
                    byte0 = 1;
                }
                aobj3 = new Object[i - byte0];
                if (kind != 'V')
                {
                    char c = kind;
                    j2 = 0;
                    if (c != '*')
                    {
                        break label1;
                    }
                }
                i2 = 0 + 1;
                aobj3[0] = aobj[0];
                j2 = i2;
            }
            System.arraycopy(((Object) (aobj)), 2, ((Object) (aobj3)), j2, i - 2);
            return methodproc.applyN(aobj3);
        }
        Object obj3;
label2:
        {
            CallContext callcontext = CallContext.getInstance();
            int j = 0;
            do
            {
                int k = aobj.length;
                if (j >= k || (aobj[j] instanceof Keyword))
                {
                    break;
                }
                j++;
            } while (true);
            int l = -1;
            int i1 = aobj.length;
            int j1;
            MethodProc methodproc1;
            if (j == i1)
            {
                l = methodproc.matchN(aobj, callcontext);
                if (l == 0)
                {
                    return callcontext.runUntilValue();
                }
                MethodProc methodproc2 = ClassMethods.apply((ClassType)obj1, "valueOf", '\0', language);
                if (methodproc2 != null)
                {
                    Object aobj2[] = new Object[i - 1];
                    System.arraycopy(((Object) (aobj)), 1, ((Object) (aobj2)), 0, i - 1);
                    l = methodproc2.matchN(aobj2, callcontext);
                    if (l == 0)
                    {
                        return callcontext.runUntilValue();
                    }
                }
                obj3 = methodproc.apply1(aobj[0]);
            } else
            {
                Object aobj1[] = new Object[j];
                System.arraycopy(((Object) (aobj)), 0, ((Object) (aobj1)), 0, j);
                obj3 = methodproc.applyN(aobj1);
            }
            j1 = j;
label3:
            do
            {
                Object obj5;
label4:
                {
                    if (j1 + 1 < aobj.length)
                    {
                        obj5 = aobj[j1];
                        if (obj5 instanceof Keyword)
                        {
                            break label4;
                        }
                    }
                    int k1 = aobj.length;
                    if (j == k1)
                    {
                        j1 = 1;
                    }
                    if (j1 == aobj.length)
                    {
                        break label2;
                    }
                    methodproc1 = ClassMethods.apply((ClassType)obj1, "add", '\0', language);
                    if (methodproc1 == null)
                    {
                        throw MethodProc.matchFailAsException(l, methodproc, aobj);
                    }
                    break label3;
                }
                Keyword keyword = (Keyword)obj5;
                Object obj6 = aobj[j1 + 1];
                String s = keyword.getName();
                SlotSet.apply(false, obj3, s, obj6);
                j1 += 2;
            } while (true);
            int l1;
            for (; j1 < aobj.length; j1 = l1)
            {
                l1 = j1 + 1;
                Object obj4 = aobj[j1];
                methodproc1.apply2(obj3, obj4);
            }

        }
        return obj3;
    }

    protected MethodProc lookupMethods(ObjectType objecttype, Object obj)
    {
        char c = 'P';
        String s1;
        MethodProc methodproc;
        if (kind == 'N')
        {
            s1 = "<init>";
        } else
        {
            String s;
            if ((obj instanceof String) || (obj instanceof FString))
            {
                s = obj.toString();
            } else
            if (obj instanceof Symbol)
            {
                s = ((Symbol)obj).getName();
            } else
            {
                throw new WrongType(this, 1, null);
            }
            s1 = Compilation.mangleName(s);
        }
        if (kind != c)
        {
            if (kind == '*' || kind == 'V')
            {
                c = 'V';
            } else
            {
                c = '\0';
            }
        }
        methodproc = ClassMethods.apply(objecttype, s1, c, language);
        if (methodproc == null)
        {
            throw new RuntimeException((new StringBuilder()).append(getName()).append(": no method named `").append(s1).append("' in class ").append(objecttype.getName()).toString());
        } else
        {
            return methodproc;
        }
    }

    public int numArgs()
    {
        int i;
        if (kind == 'N')
        {
            i = 1;
        } else
        {
            i = 2;
        }
        return i | 0xfffff000;
    }

}
