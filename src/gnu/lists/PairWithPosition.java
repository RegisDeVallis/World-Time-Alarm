// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;

import gnu.text.SourceLocator;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Referenced classes of package gnu.lists:
//            ImmutablePair

public class PairWithPosition extends ImmutablePair
    implements SourceLocator
{

    String filename;
    int position;

    public PairWithPosition()
    {
    }

    public PairWithPosition(SourceLocator sourcelocator, Object obj, Object obj1)
    {
        super(obj, obj1);
        filename = sourcelocator.getFileName();
        setLine(sourcelocator.getLineNumber(), sourcelocator.getColumnNumber());
    }

    public PairWithPosition(Object obj, Object obj1)
    {
        super(obj, obj1);
    }

    public static PairWithPosition make(Object obj, Object obj1, String s, int i)
    {
        PairWithPosition pairwithposition = new PairWithPosition(obj, obj1);
        pairwithposition.filename = s;
        pairwithposition.position = i;
        return pairwithposition;
    }

    public static PairWithPosition make(Object obj, Object obj1, String s, int i, int j)
    {
        PairWithPosition pairwithposition = new PairWithPosition(obj, obj1);
        pairwithposition.filename = s;
        pairwithposition.setLine(i, j);
        return pairwithposition;
    }

    public final int getColumnNumber()
    {
        int i = 0xfff & position;
        if (i == 0)
        {
            i = -1;
        }
        return i;
    }

    public final String getFileName()
    {
        return filename;
    }

    public final int getLineNumber()
    {
        int i = position >> 12;
        if (i == 0)
        {
            i = -1;
        }
        return i;
    }

    public String getPublicId()
    {
        return null;
    }

    public String getSystemId()
    {
        return filename;
    }

    public boolean isStableSourceLocation()
    {
        return true;
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        car = objectinput.readObject();
        cdr = objectinput.readObject();
        filename = (String)objectinput.readObject();
        position = objectinput.readInt();
    }

    public final void setFile(String s)
    {
        filename = s;
    }

    public final void setLine(int i)
    {
        setLine(i, 0);
    }

    public final void setLine(int i, int j)
    {
        if (i < 0)
        {
            i = 0;
        }
        if (j < 0)
        {
            j = 0;
        }
        position = j + (i << 12);
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(car);
        objectoutput.writeObject(cdr);
        objectoutput.writeObject(filename);
        objectoutput.writeInt(position);
    }
}
