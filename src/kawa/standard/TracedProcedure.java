// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.kawa.functions.ObjectFormat;
import gnu.mapping.Environment;
import gnu.mapping.Location;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import gnu.math.IntNum;
import java.io.IOException;
import java.io.PrintWriter;

public class TracedProcedure extends ProcedureN
{

    static Symbol curIndentSym = Symbol.makeUninterned("current-indentation");
    static int indentationStep = 2;
    boolean enabled;
    public Procedure proc;

    public TracedProcedure(Procedure procedure, boolean flag)
    {
        proc = procedure;
        enabled = flag;
        String s = procedure.getName();
        if (s != null)
        {
            setName(s);
        }
    }

    public static Procedure doTrace(Procedure procedure, boolean flag)
    {
        if (procedure instanceof TracedProcedure)
        {
            ((TracedProcedure)procedure).enabled = flag;
            return procedure;
        } else
        {
            return new TracedProcedure(procedure, flag);
        }
    }

    static void indent(int i, PrintWriter printwriter)
    {
        while (--i >= 0) 
        {
            printwriter.print(' ');
        }
    }

    static void put(Object obj, PrintWriter printwriter)
    {
        try
        {
            if (!ObjectFormat.format(obj, printwriter, 50, true))
            {
                printwriter.print("...");
            }
            return;
        }
        catch (IOException ioexception)
        {
            printwriter.print("<caught ");
            printwriter.print(ioexception);
            printwriter.print('>');
            return;
        }
    }

    public Object applyN(Object aobj[])
        throws Throwable
    {
        Location location;
        int i;
        OutPort outport;
        String s;
        Object obj1;
        if (!enabled)
        {
            break MISSING_BLOCK_LABEL_280;
        }
        location = Environment.getCurrent().getLocation(curIndentSym);
        Object obj = location.get(null);
        int j;
        if (!(obj instanceof IntNum))
        {
            i = 0;
            location.set(IntNum.zero());
        } else
        {
            i = ((IntNum)obj).intValue();
        }
        outport = OutPort.errDefault();
        s = getName();
        if (s == null)
        {
            s = "??";
        }
        indent(i, outport);
        outport.print("call to ");
        outport.print(s);
        j = aobj.length;
        outport.print(" (");
        for (int k = 0; k < j; k++)
        {
            if (k > 0)
            {
                outport.print(' ');
            }
            put(aobj[k], outport);
        }

        outport.println(")");
        obj1 = location.setWithSave(IntNum.make(i + indentationStep));
        Object obj2 = proc.applyN(aobj);
        location.setRestore(obj1);
        indent(i, outport);
        outport.print("return from ");
        outport.print(s);
        outport.print(" => ");
        put(obj2, outport);
        outport.println();
        return obj2;
        RuntimeException runtimeexception;
        runtimeexception;
        indent(i, outport);
        outport.println((new StringBuilder()).append("procedure ").append(s).append(" throws exception ").append(runtimeexception).toString());
        throw runtimeexception;
        Exception exception;
        exception;
        location.setRestore(obj1);
        throw exception;
        return proc.applyN(aobj);
    }

    public void print(PrintWriter printwriter)
    {
        printwriter.print("#<procedure ");
        String s = getName();
        String s1;
        if (s == null)
        {
            printwriter.print("<unnamed>");
        } else
        {
            printwriter.print(s);
        }
        if (enabled)
        {
            s1 = ", traced>";
        } else
        {
            s1 = ">";
        }
        printwriter.print(s1);
    }

}
