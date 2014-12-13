// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.mapping.Environment;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.text.Path;
import gnu.text.SourceMessages;
import gnu.text.SyntaxException;
import java.io.FileNotFoundException;
import kawa.Shell;

public class load extends Procedure1
{

    public static final load load = new load("load", false);
    public static final load loadRelative = new load("load-relative", true);
    boolean relative;

    public load(String s, boolean flag)
    {
        super(s);
        relative = flag;
    }

    public final Object apply1(Object obj)
        throws Throwable
    {
        return apply2(obj, Environment.getCurrent());
    }

    public final Object apply2(Object obj, Object obj1)
        throws Throwable
    {
        Environment environment;
        Path path;
        Values values;
        Path path1;
        try
        {
            environment = (Environment)obj1;
            path = Path.valueOf(obj);
            if (!relative)
            {
                break MISSING_BLOCK_LABEL_44;
            }
            path1 = (Path)Shell.currentLoadPath.get();
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            throw new RuntimeException((new StringBuilder()).append("cannot load ").append(filenotfoundexception.getMessage()).toString());
        }
        catch (SyntaxException syntaxexception)
        {
            throw new RuntimeException((new StringBuilder()).append("load: errors while compiling '").append(obj).append("':\n").append(syntaxexception.getMessages().toString(20)).toString());
        }
        if (path1 == null)
        {
            break MISSING_BLOCK_LABEL_44;
        }
        path = path1.resolve(path);
        Shell.runFile(path.openInputStream(), path, environment, true, 0);
        values = Values.empty;
        return values;
    }

}
