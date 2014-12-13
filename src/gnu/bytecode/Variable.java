// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.util.Enumeration;
import java.util.NoSuchElementException;

// Referenced classes of package gnu.bytecode:
//            Location, Type, CodeAttr, LocalVarsAttr, 
//            Method, Scope, Label

public class Variable extends Location
    implements Enumeration
{

    private static final int LIVE_FLAG = 4;
    private static final int PARAMETER_FLAG = 2;
    private static final int SIMPLE_FLAG = 1;
    static final int UNASSIGNED = -1;
    private int flags;
    Variable next;
    int offset;
    Scope scope;

    public Variable()
    {
        flags = 1;
        offset = -1;
    }

    public Variable(String s)
    {
        flags = 1;
        offset = -1;
        setName(s);
    }

    public Variable(String s, Type type)
    {
        flags = 1;
        offset = -1;
        setName(s);
        setType(type);
    }

    private void setFlag(boolean flag, int i)
    {
        if (flag)
        {
            flags = i | flags;
            return;
        } else
        {
            flags = flags & ~i;
            return;
        }
    }

    public void allocateLocal(CodeAttr codeattr)
    {
        if (offset == -1)
        {
            int i = 0;
            while (!reserveLocal(i, codeattr)) 
            {
                i++;
            }
        }
    }

    public final boolean dead()
    {
        return (4 & flags) == 0;
    }

    public void freeLocal(CodeAttr codeattr)
    {
        flags = -5 & flags;
        byte byte0;
        int i;
        if (getType().size > 4)
        {
            byte0 = 2;
        } else
        {
            byte0 = 1;
        }
        i = byte0 + offset;
        do
        {
            if (--i < offset)
            {
                break;
            }
            codeattr.locals.used[i] = null;
            Type atype[] = codeattr.local_types;
            if (atype != null)
            {
                atype[i] = null;
            }
        } while (true);
    }

    public final boolean hasMoreElements()
    {
        return next != null;
    }

    public final boolean isAssigned()
    {
        return offset != -1;
    }

    public final boolean isParameter()
    {
        return (2 & flags) != 0;
    }

    public final boolean isSimple()
    {
        return (1 & flags) != 0;
    }

    public Object nextElement()
    {
        if (next == null)
        {
            throw new NoSuchElementException("Variable enumeration");
        } else
        {
            return next;
        }
    }

    public final Variable nextVar()
    {
        return next;
    }

    public boolean reserveLocal(int i, CodeAttr codeattr)
    {
        int j = getType().getSizeInWords();
        if (codeattr.locals.used != null) goto _L2; else goto _L1
_L1:
        codeattr.locals.used = new Variable[j + 20];
_L6:
        int k = 0;
_L4:
        if (k >= j)
        {
            break; /* Loop/switch isn't completed */
        }
        Variable avariable[];
        if (codeattr.locals.used[i + k] != null)
        {
            return false;
        }
        k++;
        continue; /* Loop/switch isn't completed */
_L2:
        if (j + codeattr.getMaxLocals() >= codeattr.locals.used.length)
        {
            avariable = new Variable[j + 2 * codeattr.locals.used.length];
            System.arraycopy(codeattr.locals.used, 0, avariable, 0, codeattr.getMaxLocals());
            codeattr.locals.used = avariable;
        }
        continue; /* Loop/switch isn't completed */
        if (true) goto _L4; else goto _L3
_L3:
        for (int l = 0; l < j; l++)
        {
            codeattr.locals.used[i + l] = this;
        }

        if (i + j > codeattr.getMaxLocals())
        {
            codeattr.setMaxLocals(i + j);
        }
        offset = i;
        flags = 4 | flags;
        if (offset == 0 && "<init>".equals(codeattr.getMethod().getName()))
        {
            setType(codeattr.local_types[0]);
        }
        return true;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final void setParameter(boolean flag)
    {
        setFlag(flag, 2);
    }

    public final void setSimple(boolean flag)
    {
        setFlag(flag, 1);
    }

    boolean shouldEmit()
    {
        Scope scope1 = scope;
        if (isSimple() && name != null && scope1 != null)
        {
            Label label = scope1.start;
            if (label != null)
            {
                int i = label.position;
                if (i >= 0)
                {
                    Label label1 = scope1.end;
                    if (label1 != null && label1.position > i)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String toString()
    {
        return (new StringBuilder()).append("Variable[").append(getName()).append(" offset:").append(offset).append(']').toString();
    }
}
