// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;

import gnu.text.Path;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

// Referenced classes of package gnu.mapping:
//            InPort, OutPort, Procedure

public class TtyInPort extends InPort
{

    protected boolean promptEmitted;
    protected Procedure prompter;
    protected OutPort tie;

    public TtyInPort(InputStream inputstream, Path path, OutPort outport)
    {
        super(inputstream, path);
        setConvertCR(true);
        tie = outport;
    }

    public TtyInPort(Reader reader, Path path, OutPort outport)
    {
        super(reader, path);
        setConvertCR(true);
        tie = outport;
    }

    public void emitPrompt(String s)
        throws IOException
    {
        tie.print(s);
        tie.flush();
        tie.clearBuffer();
    }

    public int fill(int i)
        throws IOException
    {
        int j = in.read(buffer, pos, i);
        if (tie != null && j > 0)
        {
            tie.echo(buffer, pos, j);
        }
        return j;
    }

    public Procedure getPrompter()
    {
        return prompter;
    }

    public void lineStart(boolean flag)
        throws IOException
    {
        if (flag)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        if (tie != null)
        {
            tie.freshLine();
        }
        if (prompter == null)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        Object obj;
        String s;
        try
        {
            obj = prompter.apply1(this);
        }
        catch (Throwable throwable)
        {
            throw new IOException((new StringBuilder()).append("Error when evaluating prompt:").append(throwable).toString());
        }
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        s = obj.toString();
        if (s == null)
        {
            break MISSING_BLOCK_LABEL_68;
        }
        if (s.length() > 0)
        {
            emitPrompt(s);
            promptEmitted = true;
        }
    }

    public int read()
        throws IOException
    {
        if (tie != null)
        {
            tie.flush();
        }
        int i = super.read();
        if (i < 0)
        {
            boolean flag = promptEmitted;
            boolean flag1;
            if (tie != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1 & flag)
            {
                tie.println();
            }
        }
        promptEmitted = false;
        return i;
    }

    public int read(char ac[], int i, int j)
        throws IOException
    {
        if (tie != null)
        {
            tie.flush();
        }
        int k = super.read(ac, i, j);
        if (k < 0)
        {
            boolean flag = promptEmitted;
            boolean flag1;
            if (tie != null)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1 & flag)
            {
                tie.println();
            }
        }
        promptEmitted = false;
        return k;
    }

    public void setPrompter(Procedure procedure)
    {
        prompter = procedure;
    }
}
