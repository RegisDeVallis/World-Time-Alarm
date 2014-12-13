// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import gnu.mapping.Environment;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrongArguments;
import gnu.mapping.WrongType;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.PrintWriter;

// Referenced classes of package kawa.lang:
//            Syntax, GenericError, Translator

public class AutoloadSyntax extends Syntax
    implements Externalizable
{

    String className;
    Environment env;
    Syntax loaded;

    public AutoloadSyntax()
    {
    }

    public AutoloadSyntax(String s, String s1)
    {
        super(s);
        className = s1;
    }

    public AutoloadSyntax(String s, String s1, Environment environment)
    {
        super(s);
        className = s1;
        env = environment;
    }

    private void throw_error(String s)
    {
        StringBuilder stringbuilder = (new StringBuilder()).append(s).append(className).append(" while autoloading ");
        String s1;
        if (getName() == null)
        {
            s1 = "";
        } else
        {
            s1 = getName().toString();
        }
        throw new GenericError(stringbuilder.append(s1).toString());
    }

    void load()
    {
        String s = getName();
        Object obj = Class.forName(className).newInstance();
        if (!(obj instanceof Syntax))
        {
            break MISSING_BLOCK_LABEL_57;
        }
        loaded = (Syntax)obj;
        if (s != null)
        {
            try
            {
                if (loaded.getName() == null)
                {
                    loaded.setName(s);
                    return;
                }
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
            catch (UnboundLocationException unboundlocationexception)
            {
                throw_error((new StringBuilder()).append("missing symbol '").append(unboundlocationexception.getMessage()).append("' ").toString());
                return;
            }
            catch (WrongArguments wrongarguments)
            {
                throw_error("type error");
            }
        }
        break MISSING_BLOCK_LABEL_131;
        throw_error("failed to autoload valid syntax object ");
        return;
    }

    public void print(PrintWriter printwriter)
    {
        printwriter.print(toString());
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        setName((String)objectinput.readObject());
        className = (String)objectinput.readObject();
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Syntax syntax;
        Expression expression;
        if (loaded == null)
        {
            try
            {
                load();
            }
            catch (GenericError genericerror)
            {
                return translator.syntaxError(genericerror.getMessage());
            }
            catch (WrongType wrongtype)
            {
                return translator.syntaxError(wrongtype.getMessage());
            }
        }
        syntax = translator.currentSyntax;
        translator.currentSyntax = loaded;
        expression = loaded.rewriteForm(pair, translator);
        translator.currentSyntax = syntax;
        return expression;
        Exception exception;
        exception;
        translator.currentSyntax = syntax;
        throw exception;
    }

    public void scanForm(Pair pair, ScopeExp scopeexp, Translator translator)
    {
        if (loaded == null)
        {
            try
            {
                load();
            }
            catch (RuntimeException runtimeexception)
            {
                translator.syntaxError(runtimeexception.getMessage());
                return;
            }
        }
        loaded.scanForm(pair, scopeexp, translator);
    }

    public String toString()
    {
        StringBuffer stringbuffer = new StringBuffer(100);
        stringbuffer.append("#<syntax ");
        if (getName() != null)
        {
            stringbuffer.append(getName());
            stringbuffer.append(' ');
        }
        if (loaded != null)
        {
            stringbuffer.append("autoloaded>");
        } else
        {
            stringbuffer.append("autoload ");
            stringbuffer.append(className);
            stringbuffer.append(">");
        }
        return stringbuffer.toString();
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(getName());
        objectoutput.writeObject(className);
    }
}
