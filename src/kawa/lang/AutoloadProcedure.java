// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.expr.Language;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleContext;
import gnu.kawa.reflect.ClassMemberLocation;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.UnboundLocationException;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;
import java.lang.reflect.Field;

public class AutoloadProcedure extends Procedure
    implements Externalizable
{

    static final Class classModuleBody = gnu/expr/ModuleBody;
    String className;
    Language language;
    Procedure loaded;

    public AutoloadProcedure()
    {
    }

    public AutoloadProcedure(String s, String s1)
    {
        super(s);
        className = s1;
    }

    public AutoloadProcedure(String s, String s1, Language language1)
    {
        super(s);
        className = s1;
        language = language1;
    }

    private void throw_error(String s)
    {
        loaded = null;
        String s1 = getName();
        StringBuilder stringbuilder = (new StringBuilder()).append(s).append(className).append(" while autoloading ");
        String s2;
        if (s1 == null)
        {
            s2 = "";
        } else
        {
            s2 = s1.toString();
        }
        throw new RuntimeException(stringbuilder.append(s2).toString());
    }

    public Object apply0()
        throws Throwable
    {
        return getLoaded().apply0();
    }

    public Object apply1(Object obj)
        throws Throwable
    {
        return getLoaded().apply1(obj);
    }

    public Object apply2(Object obj, Object obj1)
        throws Throwable
    {
        return getLoaded().apply2(obj, obj1);
    }

    public Object apply3(Object obj, Object obj1, Object obj2)
        throws Throwable
    {
        return getLoaded().apply3(obj, obj1, obj2);
    }

    public Object apply4(Object obj, Object obj1, Object obj2, Object obj3)
        throws Throwable
    {
        return getLoaded().apply4(obj, obj1, obj2, obj3);
    }

    public Object applyN(Object aobj[])
        throws Throwable
    {
        if (loaded == null)
        {
            load();
        }
        if (loaded instanceof AutoloadProcedure)
        {
            throw new InternalError((new StringBuilder()).append("circularity in autoload of ").append(getName()).toString());
        } else
        {
            return loaded.applyN(aobj);
        }
    }

    public Procedure getLoaded()
    {
        if (loaded == null)
        {
            load();
        }
        return loaded;
    }

    public Object getProperty(Object obj, Object obj1)
    {
        Object obj2 = super.getProperty(obj, null);
        if (obj2 != null)
        {
            return obj2;
        } else
        {
            return getLoaded().getProperty(obj, obj1);
        }
    }

    public Procedure getSetter()
    {
        if (loaded == null)
        {
            load();
        }
        if (loaded instanceof HasSetter)
        {
            return loaded.getSetter();
        } else
        {
            return super.getSetter();
        }
    }

    void load()
    {
        Object obj;
        Language language1;
        Environment environment;
        Symbol symbol;
        Class class1;
        Object obj4;
        obj = getSymbol();
        language1 = language;
        if (language1 == null)
        {
            language1 = Language.getDefaultLanguage();
        }
        environment = language1.getLangEnvironment();
        Object obj2;
        Object obj3;
        Object obj5;
        if (obj instanceof Symbol)
        {
            symbol = (Symbol)obj;
        } else
        {
            symbol = environment.getSymbol(obj.toString());
        }
        class1 = Class.forName(className);
        if (!classModuleBody.isAssignableFrom(class1)) goto _L2; else goto _L1
_L1:
        obj2 = ModuleContext.getContext().searchInstance(class1);
        if (obj2 != null) goto _L4; else goto _L3
_L3:
        obj5 = class1.getDeclaredField("$instance").get(null);
        obj4 = obj5;
_L5:
        ClassMemberLocation.defineAll(obj4, language1, environment);
        if (obj4 instanceof ModuleBody)
        {
            ((ModuleBody)obj4).run();
        }
_L4:
        obj3 = environment.getFunction(symbol, null);
        if (obj3 == null)
        {
            break MISSING_BLOCK_LABEL_133;
        }
        if (obj3 instanceof Procedure)
        {
            break MISSING_BLOCK_LABEL_156;
        }
        throw_error((new StringBuilder()).append("invalid ModuleBody class - does not define ").append(obj).toString());
        loaded = (Procedure)obj3;
_L7:
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_187;
        }
        if (loaded.getSymbol() == null)
        {
            loaded.setSymbol(obj);
        }
        return;
        NoSuchFieldException nosuchfieldexception;
        nosuchfieldexception;
        UnboundLocationException unboundlocationexception;
        boolean flag;
        Object obj1;
        try
        {
            obj4 = class1.newInstance();
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            throw_error("failed to find class ");
            return;
        }
        catch (InstantiationException instantiationexception)
        {
            throw_error("failed to instantiate class ");
            return;
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw_error("illegal access in class ");
            return;
        }
          goto _L5
_L2:
        loaded = (Procedure)class1.newInstance();
        if (loaded == this)
        {
            throw_error("circularity detected");
        }
        if (obj == null) goto _L7; else goto _L6
_L6:
        flag = language1.hasSeparateFunctionNamespace();
        obj1 = null;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_262;
        }
        obj1 = EnvironmentKey.FUNCTION;
        environment.put(symbol, obj1, loaded);
          goto _L7
        unboundlocationexception;
          goto _L7
    }

    public int numArgs()
    {
        return getLoaded().numArgs();
    }

    public void print(PrintWriter printwriter)
    {
        printwriter.print("#<procedure ");
        String s = getName();
        if (s != null)
        {
            printwriter.print(s);
        }
        printwriter.print('>');
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        setName((String)objectinput.readObject());
        className = (String)objectinput.readObject();
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(getName());
        objectoutput.writeObject(className);
    }

}
