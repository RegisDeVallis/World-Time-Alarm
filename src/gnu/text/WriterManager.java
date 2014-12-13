// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import java.io.Writer;
import java.lang.reflect.Method;

// Referenced classes of package gnu.text:
//            WriterRef

public class WriterManager
    implements Runnable
{

    public static final WriterManager instance = new WriterManager();
    WriterRef first;

    public WriterManager()
    {
    }

    public WriterRef register(Writer writer)
    {
        this;
        JVM INSTR monitorenter ;
        WriterRef writerref;
        WriterRef writerref1;
        writerref = new WriterRef(writer);
        writerref1 = first;
        if (writerref1 == null)
        {
            break MISSING_BLOCK_LABEL_37;
        }
        writerref.next = writerref1.next;
        writerref1.prev = writerref;
        first = writerref;
        this;
        JVM INSTR monitorexit ;
        return writerref;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean registerShutdownHook()
    {
        try
        {
            Runtime runtime = Runtime.getRuntime();
            Method method = runtime.getClass().getDeclaredMethod("addShutdownHook", new Class[] {
                java/lang/Thread
            });
            Object aobj[] = new Object[1];
            aobj[0] = new Thread(this);
            method.invoke(runtime, aobj);
        }
        catch (Throwable throwable)
        {
            return false;
        }
        return true;
    }

    public void run()
    {
        this;
        JVM INSTR monitorenter ;
        WriterRef writerref = first;
_L1:
        if (writerref == null)
        {
            break MISSING_BLOCK_LABEL_35;
        }
        Object obj = writerref.get();
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_27;
        }
        Exception exception;
        try
        {
            ((Writer)obj).close();
        }
        catch (Exception exception1) { }
        writerref = writerref.next;
          goto _L1
        first = null;
        this;
        JVM INSTR monitorexit ;
        return;
        exception;
        throw exception;
    }

    public void unregister(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        if (obj != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        WriterRef writerref;
        WriterRef writerref1;
        WriterRef writerref2;
        writerref = (WriterRef)obj;
        writerref1 = writerref.next;
        writerref2 = writerref.prev;
        if (writerref1 == null)
        {
            break MISSING_BLOCK_LABEL_38;
        }
        writerref1.prev = writerref2;
        if (writerref2 == null)
        {
            break MISSING_BLOCK_LABEL_50;
        }
        writerref2.next = writerref1;
        if (writerref == first)
        {
            first = writerref1;
        }
        if (true) goto _L1; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

}
