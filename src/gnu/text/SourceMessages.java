// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import java.io.PrintStream;
import java.io.PrintWriter;

// Referenced classes of package gnu.text:
//            SourceLocator, SourceError

public class SourceMessages
    implements SourceLocator
{

    public static boolean debugStackTraceOnError = false;
    public static boolean debugStackTraceOnWarning = false;
    int current_column;
    String current_filename;
    int current_line;
    private int errorCount;
    SourceError firstError;
    SourceError lastError;
    SourceError lastPrevFilename;
    SourceLocator locator;
    public boolean sortMessages;

    public SourceMessages()
    {
        errorCount = 0;
        lastPrevFilename = null;
    }

    public boolean checkErrors(PrintStream printstream, int i)
    {
        SourceError sourceerror = firstError;
        boolean flag = false;
        if (sourceerror != null)
        {
            printAll(printstream, i);
            lastError = null;
            firstError = null;
            int j = errorCount;
            errorCount = 0;
            flag = false;
            if (j > 0)
            {
                flag = true;
            }
        }
        return flag;
    }

    public boolean checkErrors(PrintWriter printwriter, int i)
    {
        SourceError sourceerror = firstError;
        boolean flag = false;
        if (sourceerror != null)
        {
            printAll(printwriter, i);
            lastError = null;
            firstError = null;
            int j = errorCount;
            errorCount = 0;
            flag = false;
            if (j > 0)
            {
                flag = true;
            }
        }
        return flag;
    }

    public void clear()
    {
        lastError = null;
        firstError = null;
        errorCount = 0;
    }

    public void clearErrors()
    {
        errorCount = 0;
    }

    public void error(char c, SourceLocator sourcelocator, String s)
    {
        error(new SourceError(c, sourcelocator, s));
    }

    public void error(char c, SourceLocator sourcelocator, String s, String s1)
    {
        SourceError sourceerror = new SourceError(c, sourcelocator, s);
        sourceerror.code = s1;
        error(sourceerror);
    }

    public void error(char c, String s)
    {
        error(new SourceError(c, current_filename, current_line, current_column, s));
    }

    public void error(char c, String s, int i, int j, String s1)
    {
        error(new SourceError(c, s, i, j, s1));
    }

    public void error(char c, String s, int i, int j, String s1, String s2)
    {
        SourceError sourceerror = new SourceError(c, s, i, j, s1);
        sourceerror.code = s2;
        error(sourceerror);
    }

    public void error(char c, String s, String s1)
    {
        SourceError sourceerror = new SourceError(c, current_filename, current_line, current_column, s);
        sourceerror.code = s1;
        error(sourceerror);
    }

    public void error(char c, String s, Throwable throwable)
    {
        SourceError sourceerror = new SourceError(c, current_filename, current_line, current_column, s);
        sourceerror.fakeException = throwable;
        error(sourceerror);
    }

    public void error(SourceError sourceerror)
    {
        SourceError sourceerror1;
        if (sourceerror.severity == 'f')
        {
            errorCount = 1000;
        } else
        if (sourceerror.severity != 'w')
        {
            errorCount = 1 + errorCount;
        }
        if (debugStackTraceOnError && (sourceerror.severity == 'e' || sourceerror.severity == 'f') || debugStackTraceOnWarning && sourceerror.severity == 'w')
        {
            sourceerror.fakeException = new Throwable();
        }
        if (lastError != null && lastError.filename != null && !lastError.filename.equals(sourceerror.filename))
        {
            lastPrevFilename = lastError;
        }
        sourceerror1 = lastPrevFilename;
        if (sortMessages && sourceerror.severity != 'f') goto _L2; else goto _L1
_L1:
        sourceerror1 = lastError;
_L3:
        SourceError sourceerror2;
        if (sourceerror1 == null)
        {
            sourceerror.next = firstError;
            firstError = sourceerror;
        } else
        {
            sourceerror.next = sourceerror1.next;
            sourceerror1.next = sourceerror;
        }
        if (sourceerror1 == lastError)
        {
            lastError = sourceerror;
        }
        return;
_L4:
        sourceerror1 = sourceerror2;
_L2:
        if (sourceerror1 == null)
        {
            sourceerror2 = firstError;
        } else
        {
            sourceerror2 = sourceerror1.next;
        }
        if (sourceerror2 != null && (sourceerror.line == 0 || sourceerror2.line == 0 || sourceerror.line >= sourceerror2.line && (sourceerror.line != sourceerror2.line || sourceerror.column == 0 || sourceerror2.column == 0 || sourceerror.column >= sourceerror2.column))) goto _L4; else goto _L3
    }

    public final int getColumnNumber()
    {
        if (locator == null)
        {
            return current_column;
        } else
        {
            return locator.getColumnNumber();
        }
    }

    public int getErrorCount()
    {
        return errorCount;
    }

    public SourceError getErrors()
    {
        return firstError;
    }

    public final String getFileName()
    {
        return current_filename;
    }

    public final int getLineNumber()
    {
        if (locator == null)
        {
            return current_line;
        } else
        {
            return locator.getLineNumber();
        }
    }

    public String getPublicId()
    {
        if (locator == null)
        {
            return null;
        } else
        {
            return locator.getPublicId();
        }
    }

    public String getSystemId()
    {
        if (locator == null)
        {
            return current_filename;
        } else
        {
            return locator.getSystemId();
        }
    }

    public boolean isStableSourceLocation()
    {
        return false;
    }

    public void printAll(PrintStream printstream, int i)
    {
        for (SourceError sourceerror = firstError; sourceerror != null && --i >= 0; sourceerror = sourceerror.next)
        {
            sourceerror.println(printstream);
        }

    }

    public void printAll(PrintWriter printwriter, int i)
    {
        for (SourceError sourceerror = firstError; sourceerror != null && --i >= 0; sourceerror = sourceerror.next)
        {
            sourceerror.println(printwriter);
        }

    }

    public boolean seenErrors()
    {
        return errorCount > 0;
    }

    public boolean seenErrorsOrWarnings()
    {
        return firstError != null;
    }

    public void setColumn(int i)
    {
        current_column = i;
    }

    public void setFile(String s)
    {
        current_filename = s;
    }

    public void setLine(int i)
    {
        current_line = i;
    }

    public void setLine(String s, int i, int j)
    {
        current_filename = s;
        current_line = i;
        current_column = j;
    }

    public final void setLocation(SourceLocator sourcelocator)
    {
        locator = null;
        current_line = sourcelocator.getLineNumber();
        current_column = sourcelocator.getColumnNumber();
        current_filename = sourcelocator.getFileName();
    }

    public final void setSourceLocator(SourceLocator sourcelocator)
    {
        if (sourcelocator == this)
        {
            sourcelocator = null;
        }
        locator = sourcelocator;
    }

    public final SourceLocator swapSourceLocator(SourceLocator sourcelocator)
    {
        SourceLocator sourcelocator1 = locator;
        locator = sourcelocator;
        return sourcelocator1;
    }

    public String toString(int i)
    {
        if (firstError == null)
        {
            return null;
        }
        StringBuffer stringbuffer = new StringBuffer();
        for (SourceError sourceerror = firstError; sourceerror != null && --i >= 0; sourceerror = sourceerror.next)
        {
            stringbuffer.append(sourceerror);
            stringbuffer.append('\n');
        }

        return stringbuffer.toString();
    }

}
