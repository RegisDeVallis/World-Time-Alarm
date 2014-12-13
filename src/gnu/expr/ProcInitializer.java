// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.LocalVarsAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.mapping.Environment;
import gnu.mapping.PropertySet;
import gnu.mapping.Symbol;

// Referenced classes of package gnu.expr:
//            Initializer, Compilation, ModuleExp, LambdaExp, 
//            Language, ModuleMethod, ClassExp, Target, 
//            Expression, Declaration

public class ProcInitializer extends Initializer
{

    LambdaExp proc;

    public ProcInitializer(LambdaExp lambdaexp, Compilation compilation, Field field)
    {
        this.field = field;
        proc = lambdaexp;
        Object obj;
        if (field.getStaticFlag())
        {
            obj = compilation.getModule();
        } else
        {
            obj = lambdaexp.getOwningLambda();
        }
        if ((obj instanceof ModuleExp) && compilation.isStatic())
        {
            next = compilation.clinitChain;
            compilation.clinitChain = this;
            return;
        } else
        {
            next = ((LambdaExp) (obj)).initChain;
            obj.initChain = this;
            return;
        }
    }

    public static void emitLoadModuleMethod(LambdaExp lambdaexp, Compilation compilation)
    {
        Declaration declaration = lambdaexp.nameDecl;
        Object obj;
        boolean flag;
        ModuleMethod modulemethod;
        CodeAttr codeattr;
        ClassType classtype;
        String s;
        Object obj1;
        int j;
        if (declaration == null)
        {
            obj = lambdaexp.getName();
        } else
        {
            obj = declaration.getSymbol();
        }
        flag = compilation.immediate;
        modulemethod = null;
        if (flag)
        {
            modulemethod = null;
            if (obj != null)
            {
                modulemethod = null;
                if (declaration != null)
                {
                    Environment environment = Environment.getCurrent();
                    Method method;
                    int i;
                    int k;
                    int l;
                    Object obj2;
                    Symbol symbol;
                    Object obj4;
                    Object obj5;
                    boolean flag1;
                    if (obj instanceof Symbol)
                    {
                        symbol = (Symbol)obj;
                    } else
                    {
                        symbol = Symbol.make("", obj.toString().intern());
                    }
                    obj4 = compilation.getLanguage().getEnvPropertyFor(lambdaexp.nameDecl);
                    obj5 = environment.get(symbol, obj4, null);
                    flag1 = obj5 instanceof ModuleMethod;
                    modulemethod = null;
                    if (flag1)
                    {
                        modulemethod = (ModuleMethod)obj5;
                    }
                }
            }
        }
        codeattr = compilation.getCode();
        classtype = Compilation.typeModuleMethod;
        if (modulemethod == null)
        {
            codeattr.emitNew(classtype);
            codeattr.emitDup(1);
            s = "<init>";
        } else
        {
            compilation.compileConstant(modulemethod, Target.pushValue(classtype));
            s = "init";
        }
        method = classtype.getDeclaredMethod(s, 4);
        if (lambdaexp.getNeedsClosureEnv())
        {
            obj1 = lambdaexp.getOwningLambda();
        } else
        {
            obj1 = compilation.getModule();
        }
        if ((obj1 instanceof ClassExp) && ((LambdaExp) (obj1)).staticLinkField != null)
        {
            codeattr.emitLoad(codeattr.getCurrentScope().getVariable(1));
        } else
        if (!(obj1 instanceof ModuleExp) || compilation.moduleClass == compilation.mainClass && !compilation.method.getStaticFlag())
        {
            codeattr.emitPushThis();
        } else
        {
            if (compilation.moduleInstanceVar == null)
            {
                compilation.moduleInstanceVar = codeattr.locals.current_scope.addVariable(codeattr, compilation.moduleClass, "$instance");
                if (compilation.moduleClass != compilation.mainClass && !compilation.isStatic())
                {
                    codeattr.emitNew(compilation.moduleClass);
                    codeattr.emitDup(compilation.moduleClass);
                    codeattr.emitInvokeSpecial(compilation.moduleClass.constructor);
                    compilation.moduleInstanceMainField = compilation.moduleClass.addField("$main", compilation.mainClass, 0);
                    codeattr.emitDup(compilation.moduleClass);
                    codeattr.emitPushThis();
                    codeattr.emitPutField(compilation.moduleInstanceMainField);
                } else
                {
                    codeattr.emitGetStatic(compilation.moduleInstanceMainField);
                }
                codeattr.emitStore(compilation.moduleInstanceVar);
            }
            codeattr.emitLoad(compilation.moduleInstanceVar);
        }
        codeattr.emitPushInt(lambdaexp.getSelectorValue(compilation));
        compilation.compileConstant(obj, Target.pushObject);
        i = lambdaexp.min_args;
        if (lambdaexp.keywords == null)
        {
            j = lambdaexp.max_args;
        } else
        {
            j = -1;
        }
        codeattr.emitPushInt(i | j << 12);
        codeattr.emitInvoke(method);
        if (lambdaexp.properties != null)
        {
            k = lambdaexp.properties.length;
            l = 0;
            while (l < k) 
            {
                obj2 = lambdaexp.properties[l];
                if (obj2 != null && obj2 != PropertySet.nameKey)
                {
                    Object obj3 = lambdaexp.properties[l + 1];
                    codeattr.emitDup(1);
                    compilation.compileConstant(obj2);
                    Target target = Target.pushObject;
                    if (obj3 instanceof Expression)
                    {
                        ((Expression)obj3).compile(compilation, target);
                    } else
                    {
                        compilation.compileConstant(obj3, target);
                    }
                    codeattr.emitInvokeVirtual(ClassType.make("gnu.mapping.PropertySet").getDeclaredMethod("setProperty", 2));
                }
                l += 2;
            }
        }
    }

    public void emit(Compilation compilation)
    {
        CodeAttr codeattr = compilation.getCode();
        if (!field.getStaticFlag())
        {
            codeattr.emitPushThis();
        }
        emitLoadModuleMethod(proc, compilation);
        if (field.getStaticFlag())
        {
            codeattr.emitPutStatic(field);
            return;
        } else
        {
            codeattr.emitPutField(field);
            return;
        }
    }

    public void reportError(String s, Compilation compilation)
    {
        String s1 = compilation.getFileName();
        int i = compilation.getLineNumber();
        int j = compilation.getColumnNumber();
        compilation.setLocation(proc);
        String s2 = proc.getName();
        StringBuffer stringbuffer = new StringBuffer(s);
        if (s2 == null)
        {
            stringbuffer.append("unnamed procedure");
        } else
        {
            stringbuffer.append("procedure ");
            stringbuffer.append(s2);
        }
        compilation.error('e', stringbuffer.toString());
        compilation.setLine(s1, i, j);
    }
}
