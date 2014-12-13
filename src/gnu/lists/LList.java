// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package gnu.lists:
//            ExtSequence, Sequence, Pair, Consumer, 
//            LListPosition, PositionManager, SeqPosition

public class LList extends ExtSequence
    implements Sequence, Externalizable, Comparable
{

    public static final LList Empty = new LList();

    public LList()
    {
    }

    public static Pair chain1(Pair pair, Object obj)
    {
        Pair pair1 = new Pair(obj, Empty);
        pair.cdr = pair1;
        return pair1;
    }

    public static Pair chain4(Pair pair, Object obj, Object obj1, Object obj2, Object obj3)
    {
        Pair pair1 = new Pair(obj3, Empty);
        pair.cdr = new Pair(obj, new Pair(obj1, new Pair(obj2, pair1)));
        return pair1;
    }

    public static Object checkNonList(Object obj)
    {
        if (obj instanceof LList)
        {
            obj = "#<not a pair>";
        }
        return obj;
    }

    public static Object consX(Object aobj[])
    {
        Object obj = aobj[0];
        int i = -1 + aobj.length;
        if (i <= 0)
        {
            return obj;
        }
        Pair pair = new Pair(obj, null);
        Pair pair1 = pair;
        for (int j = 1; j < i; j++)
        {
            Pair pair2 = new Pair(aobj[j], null);
            pair1.cdr = pair2;
            pair1 = pair2;
        }

        pair1.cdr = aobj[i];
        return pair;
    }

    public static final int length(Object obj)
    {
        int i = 0;
        for (; obj instanceof Pair; obj = ((Pair)obj).cdr)
        {
            i++;
        }

        return i;
    }

    public static Pair list1(Object obj)
    {
        return new Pair(obj, Empty);
    }

    public static Pair list2(Object obj, Object obj1)
    {
        return new Pair(obj, new Pair(obj1, Empty));
    }

    public static Pair list3(Object obj, Object obj1, Object obj2)
    {
        return new Pair(obj, new Pair(obj1, new Pair(obj2, Empty)));
    }

    public static Pair list4(Object obj, Object obj1, Object obj2, Object obj3)
    {
        return new Pair(obj, new Pair(obj1, new Pair(obj2, new Pair(obj3, Empty))));
    }

    public static int listLength(Object obj, boolean flag)
    {
        int i = 0;
        Object obj1 = obj;
        Object obj2 = obj;
        do
        {
            if (obj2 == Empty)
            {
                return i;
            }
            if (!(obj2 instanceof Pair))
            {
                if ((obj2 instanceof Sequence) && flag)
                {
                    int j = ((Sequence)obj2).size();
                    if (j >= 0)
                    {
                        j += i;
                    }
                    return j;
                } else
                {
                    return -2;
                }
            }
            Pair pair = (Pair)obj2;
            if (pair.cdr == Empty)
            {
                return i + 1;
            }
            if (obj2 == obj1 && i > 0)
            {
                return -1;
            }
            if (!(pair.cdr instanceof Pair))
            {
                i++;
                obj2 = pair.cdr;
            } else
            {
                if (!(obj1 instanceof Pair))
                {
                    return -2;
                }
                obj1 = ((Pair)obj1).cdr;
                obj2 = ((Pair)pair.cdr).cdr;
                i += 2;
            }
        } while (true);
    }

    public static Object listTail(Object obj, int i)
    {
        while (--i >= 0) 
        {
            if (!(obj instanceof Pair))
            {
                throw new IndexOutOfBoundsException("List is too short.");
            }
            obj = ((Pair)obj).cdr;
        }
        return obj;
    }

    public static LList makeList(List list)
    {
        Iterator iterator = list.iterator();
        Object obj = Empty;
        Pair pair = null;
        while (iterator.hasNext()) 
        {
            Pair pair1 = new Pair(iterator.next(), Empty);
            if (pair == null)
            {
                obj = pair1;
            } else
            {
                pair.cdr = pair1;
            }
            pair = pair1;
        }
        return ((LList) (obj));
    }

    public static LList makeList(Object aobj[], int i)
    {
        LList llist = Empty;
        int j = aobj.length - i;
        Object obj;
        for (obj = llist; --j >= 0; obj = new Pair(aobj[i + j], obj)) { }
        return ((LList) (obj));
    }

    public static LList makeList(Object aobj[], int i, int j)
    {
        LList llist = Empty;
        int k = j;
        Object obj;
        for (obj = llist; --k >= 0; obj = new Pair(aobj[i + k], obj)) { }
        return ((LList) (obj));
    }

    public static LList reverseInPlace(Object obj)
    {
        Object obj1;
        Pair pair;
        for (obj1 = Empty; obj != Empty; obj1 = pair)
        {
            pair = (Pair)obj;
            obj = pair.cdr;
            pair.cdr = obj1;
        }

        return ((LList) (obj1));
    }

    public int compareTo(Object obj)
    {
        return obj != Empty ? -1 : 0;
    }

    public void consume(Consumer consumer)
    {
        Object obj = this;
        consumer.startElement("list");
        Pair pair;
        for (; obj instanceof Pair; obj = pair.cdr)
        {
            if (obj != this)
            {
                consumer.write(32);
            }
            pair = (Pair)obj;
            consumer.writeObject(pair.car);
        }

        if (obj != Empty)
        {
            consumer.write(32);
            consumer.write(". ");
            consumer.writeObject(checkNonList(obj));
        }
        consumer.endElement();
    }

    public int createPos(int i, boolean flag)
    {
        LListPosition llistposition = new LListPosition(this, i, flag);
        return PositionManager.manager.register(llistposition);
    }

    public int createRelativePos(int i, int j, boolean flag)
    {
        boolean flag1 = isAfterPos(i);
        if (j < 0 || i == 0)
        {
            return super.createRelativePos(i, j, flag);
        }
        if (j == 0)
        {
            if (flag == flag1)
            {
                return copyPos(i);
            }
            if (flag && !flag1)
            {
                return super.createRelativePos(i, j, flag);
            }
        }
        if (i < 0)
        {
            throw new IndexOutOfBoundsException();
        }
        LListPosition llistposition = (LListPosition)PositionManager.getPositionObject(i);
        if (llistposition.xpos == null)
        {
            return super.createRelativePos(i, j, flag);
        }
        LListPosition llistposition1 = new LListPosition(llistposition);
        Object obj = llistposition1.xpos;
        int k = llistposition1.ipos;
        if (flag && !flag1)
        {
            j--;
            k += 3;
        }
        if (!flag && flag1)
        {
            j++;
            k -= 3;
        }
        do
        {
            if (!(obj instanceof Pair))
            {
                throw new IndexOutOfBoundsException();
            }
            if (--j < 0)
            {
                llistposition1.ipos = k;
                llistposition1.xpos = obj;
                return PositionManager.manager.register(llistposition1);
            }
            Pair pair = (Pair)obj;
            k += 2;
            obj = pair.cdr;
        } while (true);
    }

    public boolean equals(Object obj)
    {
        return this == obj;
    }

    public Object get(int i)
    {
        throw new IndexOutOfBoundsException();
    }

    public SeqPosition getIterator(int i)
    {
        return new LListPosition(this, i, false);
    }

    public Object getPosNext(int i)
    {
        return eofValue;
    }

    public Object getPosPrevious(int i)
    {
        return eofValue;
    }

    public boolean hasNext(int i)
    {
        return false;
    }

    public boolean isEmpty()
    {
        return true;
    }

    public int nextPos(int i)
    {
        return 0;
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
    }

    public Object readResolve()
        throws ObjectStreamException
    {
        return Empty;
    }

    protected void setPosNext(int i, Object obj)
    {
        if (i <= 0)
        {
            if (i == -1 || !(this instanceof Pair))
            {
                throw new IndexOutOfBoundsException();
            } else
            {
                ((Pair)this).car = obj;
                return;
            }
        } else
        {
            PositionManager.getPositionObject(i).setNext(obj);
            return;
        }
    }

    protected void setPosPrevious(int i, Object obj)
    {
        if (i <= 0)
        {
            if (i == 0 || !(this instanceof Pair))
            {
                throw new IndexOutOfBoundsException();
            } else
            {
                ((Pair)this).lastPair().car = obj;
                return;
            }
        } else
        {
            PositionManager.getPositionObject(i).setPrevious(obj);
            return;
        }
    }

    public int size()
    {
        return 0;
    }

    public String toString()
    {
        Object obj;
        int i;
        StringBuffer stringbuffer;
        obj = this;
        i = 0;
        stringbuffer = new StringBuffer(100);
        stringbuffer.append('(');
_L1:
        if (obj != Empty)
        {
label0:
            {
                if (i > 0)
                {
                    stringbuffer.append(' ');
                }
                if (i < 10)
                {
                    break label0;
                }
                stringbuffer.append("...");
            }
        }
_L2:
        stringbuffer.append(')');
        return stringbuffer.toString();
label1:
        {
            if (!(obj instanceof Pair))
            {
                break label1;
            }
            Pair pair = (Pair)obj;
            stringbuffer.append(pair.car);
            obj = pair.cdr;
            i++;
        }
          goto _L1
        stringbuffer.append(". ");
        stringbuffer.append(checkNonList(obj));
          goto _L2
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
    }

}
