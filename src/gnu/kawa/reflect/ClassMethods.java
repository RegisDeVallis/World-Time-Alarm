// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.GenericProc;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.lists.FString;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import java.util.Vector;

// Referenced classes of package gnu.kawa.reflect:
//            MethodFilter

public class ClassMethods extends Procedure2
{

    public static final ClassMethods classMethods;

    public ClassMethods()
    {
    }

    public static MethodProc apply(ObjectType objecttype, String s, char c, Language language)
    {
        PrimProcedure aprimprocedure[] = getMethods(objecttype, s, c, null, language);
        GenericProc genericproc = null;
        Object obj = null;
        for (int i = 0; i < aprimprocedure.length; i++)
        {
            PrimProcedure primprocedure = aprimprocedure[i];
            if (obj != null && genericproc == null)
            {
                genericproc = new GenericProc();
                genericproc.add(((MethodProc) (obj)));
            }
            obj = primprocedure;
            if (genericproc != null)
            {
                genericproc.add(((MethodProc) (obj)));
            }
        }

        if (genericproc != null)
        {
            genericproc.setName((new StringBuilder()).append(objecttype.getName()).append(".").append(s).toString());
            return genericproc;
        } else
        {
            return ((MethodProc) (obj));
        }
    }

    public static MethodProc apply(Procedure procedure, Object obj, Object obj1)
    {
        if (obj instanceof Class)
        {
            obj = Type.make((Class)obj);
        }
        ClassType classtype;
        if (obj instanceof ClassType)
        {
            classtype = (ClassType)obj;
        } else
        if ((obj instanceof String) || (obj instanceof FString) || (obj instanceof Symbol))
        {
            classtype = ClassType.make(obj.toString());
        } else
        {
            throw new WrongType(procedure, 0, null);
        }
        if ((obj1 instanceof String) || (obj1 instanceof FString) || (obj1 instanceof Symbol))
        {
            String s = obj1.toString();
            if (!"<init>".equals(s))
            {
                s = Compilation.mangleName(s);
            }
            MethodProc methodproc = apply(((ObjectType) (classtype)), s, '\0', Language.getDefaultLanguage());
            if (methodproc == null)
            {
                throw new RuntimeException((new StringBuilder()).append("no applicable method named `").append(s).append("' in ").append(classtype.getName()).toString());
            } else
            {
                return methodproc;
            }
        } else
        {
            throw new WrongType(procedure, 1, null);
        }
    }

    static String checkName(Expression expression)
    {
        boolean flag = expression instanceof QuoteExp;
        String s = null;
        if (flag)
        {
            Object obj = ((QuoteExp)expression).getValue();
            if ((obj instanceof FString) || (obj instanceof String))
            {
                s = obj.toString();
            } else
            {
                boolean flag1 = obj instanceof Symbol;
                s = null;
                if (flag1)
                {
                    return ((Symbol)obj).getName();
                }
            }
        }
        return s;
    }

    static String checkName(Expression expression, boolean flag)
    {
        boolean flag1;
        String s;
        flag1 = expression instanceof QuoteExp;
        s = null;
        if (!flag1) goto _L2; else goto _L1
_L1:
        Object obj = ((QuoteExp)expression).getValue();
        if (!(obj instanceof FString) && !(obj instanceof String)) goto _L4; else goto _L3
_L3:
        s = obj.toString();
_L7:
        if (!Compilation.isValidJavaName(s))
        {
            break; /* Loop/switch isn't completed */
        }
_L2:
        return s;
_L4:
        boolean flag2;
        flag2 = obj instanceof Symbol;
        s = null;
        if (!flag2) goto _L2; else goto _L5
_L5:
        s = ((Symbol)obj).getName();
        if (true) goto _L7; else goto _L6
_L6:
        return Compilation.mangleName(s, flag);
    }

    public static PrimProcedure[] getMethods(ObjectType objecttype, String s, char c, ClassType classtype, Language language)
    {
        ClassType classtype1 = Type.tostring_type;
        if (objecttype == classtype1)
        {
            objecttype = Type.string_type;
        }
        ObjectType objecttype1;
        MethodFilter methodfilter;
        boolean flag;
        Vector vector;
        int i;
        int j;
        PrimProcedure aprimprocedure[];
        int k;
        if (c == 'P')
        {
            objecttype1 = null;
        } else
        {
            objecttype1 = objecttype;
        }
        methodfilter = new MethodFilter(s, 0, 0, classtype, objecttype1);
        if (c == 'P' || "<init>".equals(s))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        vector = new Vector();
        if (flag)
        {
            i = 0;
        } else
        {
            i = 2;
        }
        objecttype.getMethods(methodfilter, i, vector);
        if (!flag && (!(objecttype instanceof ClassType) || ((ClassType)objecttype).isInterface()))
        {
            Type.pointer_type.getMethods(methodfilter, 0, vector);
        }
        if (flag)
        {
            j = vector.size();
        } else
        {
            j = removeRedundantMethods(vector);
        }
        aprimprocedure = new PrimProcedure[j];
        k = j;
        int i1;
        for (int l = 0; --k >= 0; l = i1)
        {
            Method method = (Method)vector.elementAt(k);
            if (!flag && method.getDeclaringClass() != objecttype)
            {
                Type type = objecttype.getImplementationType();
                if (type instanceof ClassType)
                {
                    method = new Method(method, (ClassType)type);
                }
            }
            PrimProcedure primprocedure = new PrimProcedure(method, c, language);
            i1 = l + 1;
            aprimprocedure[l] = primprocedure;
        }

        return aprimprocedure;
    }

    private static int removeRedundantMethods(Vector vector)
    {
        int i;
        int j;
        i = vector.size();
        j = 1;
_L6:
        Method method;
        ClassType classtype;
        Type atype[];
        int k;
        int l;
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        method = (Method)vector.elementAt(j);
        classtype = method.getDeclaringClass();
        atype = method.getParameterTypes();
        k = atype.length;
        l = 0;
_L2:
        Method method1;
        Type atype1[];
        if (l >= j)
        {
            break MISSING_BLOCK_LABEL_147;
        }
        method1 = (Method)vector.elementAt(l);
        atype1 = method1.getParameterTypes();
        if (k == atype1.length)
        {
            break; /* Loop/switch isn't completed */
        }
_L4:
        l++;
        if (true) goto _L2; else goto _L1
_L1:
        int i1;
        for (i1 = k; --i1 >= 0 && atype[i1] == atype1[i1];) { }
        if (i1 >= 0) goto _L4; else goto _L3
_L3:
        if (classtype.isSubtype(method1.getDeclaringClass()))
        {
            vector.setElementAt(method, l);
        }
        vector.setElementAt(vector.elementAt(i - 1), j);
        i--;
        continue; /* Loop/switch isn't completed */
        j++;
        if (true) goto _L6; else goto _L5
_L5:
        return i;
    }

    public static int selectApplicable(PrimProcedure aprimprocedure[], int i)
    {
        int j = aprimprocedure.length;
        int k = 0;
        int l = 0;
        int i1 = 0;
        for (int j1 = 0; j1 < j;)
        {
            int k1 = aprimprocedure[j1].numArgs();
            int l1 = Procedure.minArgs(k1);
            int i2 = Procedure.maxArgs(k1);
            boolean flag = false;
            if (i < l1)
            {
                l++;
            } else
            if (i > i2 && i2 >= 0)
            {
                k++;
                flag = false;
            } else
            {
                flag = true;
            }
            if (flag)
            {
                i1++;
                j1++;
            } else
            {
                PrimProcedure primprocedure = aprimprocedure[j - 1];
                aprimprocedure[j - 1] = aprimprocedure[j1];
                aprimprocedure[j1] = primprocedure;
                j--;
            }
        }

        if (i1 > 0)
        {
            return i1;
        }
        if (l > 0)
        {
            return 0xfff10000;
        }
        return k <= 0 ? 0 : 0xfff20000;
    }

    public static long selectApplicable(PrimProcedure aprimprocedure[], Type atype[])
    {
        int i = aprimprocedure.length;
        int j = 0;
        int k = 0;
        for (int l = 0; l < i;)
        {
            int i1 = aprimprocedure[l].isApplicable(atype);
            if (i1 < 0)
            {
                PrimProcedure primprocedure1 = aprimprocedure[i - 1];
                aprimprocedure[i - 1] = aprimprocedure[l];
                aprimprocedure[l] = primprocedure1;
                i--;
            } else
            if (i1 > 0)
            {
                PrimProcedure primprocedure = aprimprocedure[j];
                aprimprocedure[j] = aprimprocedure[l];
                aprimprocedure[l] = primprocedure;
                j++;
                l++;
            } else
            {
                k++;
                l++;
            }
        }

        return ((long)j << 32) + (long)k;
    }

    public Object apply2(Object obj, Object obj1)
    {
        return apply(this, obj, obj1);
    }

    static 
    {
        classMethods = new ClassMethods();
        classMethods.setName("class-methods");
    }
}
