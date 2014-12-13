// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

// Referenced classes of package gnu.lists:
//            LList, Sequence, PositionManager, SeqPosition, 
//            LListPosition

public class Pair extends LList
    implements Externalizable
{

    protected Object car;
    protected Object cdr;

    public Pair()
    {
    }

    public Pair(Object obj, Object obj1)
    {
        car = obj;
        cdr = obj1;
    }

    public static int compareTo(Pair pair, Pair pair1)
    {
        if (pair != pair1) goto _L2; else goto _L1
_L1:
        int i = 0;
_L7:
        return i;
_L2:
        Object obj2;
        Object obj3;
        if (pair == null)
        {
            return -1;
        }
        if (pair1 == null)
        {
            return 1;
        }
_L5:
        Object obj = pair.car;
        Object obj1 = pair1.car;
        i = ((Comparable)obj).compareTo((Comparable)obj1);
        if (i != 0)
        {
            continue; /* Loop/switch isn't completed */
        }
        obj2 = pair.cdr;
        obj3 = pair1.cdr;
        if (obj2 == obj3)
        {
            return 0;
        }
        if (obj2 == null)
        {
            return -1;
        }
        if (obj3 == null)
        {
            return 1;
        }
        if (!(obj2 instanceof Pair) || !(obj3 instanceof Pair))
        {
            return ((Comparable)obj2).compareTo((Comparable)obj3);
        }
        if (true) goto _L4; else goto _L3
_L4:
        pair = (Pair)obj2;
        pair1 = (Pair)obj3;
        if (true) goto _L5; else goto _L3
_L3:
        if (true) goto _L7; else goto _L6
_L6:
    }

    public static boolean equals(Pair pair, Pair pair1)
    {
        if (pair != pair1) goto _L2; else goto _L1
_L1:
        return true;
_L2:
        if (pair == null || pair1 == null)
        {
            return false;
        }
        do
        {
            obj = pair.car;
            obj1 = pair1.car;
            if (obj != obj1 && (obj == null || !obj.equals(obj1)))
            {
                return false;
            }
            obj2 = pair.cdr;
            obj3 = pair1.cdr;
            if (obj2 != obj3)
            {
                if (obj2 == null || obj3 == null)
                {
                    return false;
                }
                if (!(obj2 instanceof Pair) || !(obj3 instanceof Pair))
                {
                    return obj2.equals(obj3);
                }
                pair = (Pair)obj2;
                pair1 = (Pair)obj3;
            } else
            {
                continue; /* Loop/switch isn't completed */
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
    }

    public static Pair make(Object obj, Object obj1)
    {
        return new Pair(obj, obj1);
    }

    public int compareTo(Object obj)
    {
        if (obj == Empty)
        {
            return 1;
        } else
        {
            return compareTo(this, (Pair)obj);
        }
    }

    public boolean equals(Object obj)
    {
        if (obj != null && (obj instanceof Pair))
        {
            return equals(this, (Pair)obj);
        } else
        {
            return false;
        }
    }

    public Object get(int i)
    {
        Pair pair;
        int j;
label0:
        {
            pair = this;
            j = i;
            do
            {
                if (j <= 0)
                {
                    break label0;
                }
                j--;
                if (!(pair.cdr instanceof Pair))
                {
                    break;
                }
                pair = (Pair)pair.cdr;
            } while (true);
            if (pair.cdr instanceof Sequence)
            {
                return ((Sequence)pair.cdr).get(j);
            }
        }
        if (j == 0)
        {
            return pair.car;
        } else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    public Object getCar()
    {
        return car;
    }

    public Object getCdr()
    {
        return cdr;
    }

    public Object getPosNext(int i)
    {
        if (i <= 0)
        {
            if (i == 0)
            {
                return car;
            } else
            {
                return eofValue;
            }
        } else
        {
            return PositionManager.getPositionObject(i).getNext();
        }
    }

    public Object getPosPrevious(int i)
    {
        if (i <= 0)
        {
            if (i == 0)
            {
                return eofValue;
            } else
            {
                return lastPair().car;
            }
        } else
        {
            return PositionManager.getPositionObject(i).getPrevious();
        }
    }

    public boolean hasNext(int i)
    {
        if (i <= 0)
        {
            return i == 0;
        } else
        {
            return PositionManager.getPositionObject(i).hasNext();
        }
    }

    public int hashCode()
    {
        int i = 1;
        Object obj = this;
        while (obj instanceof Pair) 
        {
            Pair pair = (Pair)obj;
            Object obj1 = pair.car;
            int j = i * 31;
            int k;
            if (obj1 == null)
            {
                k = 0;
            } else
            {
                k = obj1.hashCode();
            }
            i = j + k;
            obj = pair.cdr;
        }
        if (obj != LList.Empty && obj != null)
        {
            i ^= obj.hashCode();
        }
        return i;
    }

    public boolean isEmpty()
    {
        return false;
    }

    public final Pair lastPair()
    {
        Pair pair = this;
        do
        {
            Object obj = pair.cdr;
            if (obj instanceof Pair)
            {
                pair = (Pair)obj;
            } else
            {
                return pair;
            }
        } while (true);
    }

    public int length()
    {
        int i = 0;
        Object obj = this;
        Object obj1 = this;
        do
        {
            if (obj == Empty)
            {
                return i;
            }
            if (!(obj instanceof Pair))
            {
                if (obj instanceof Sequence)
                {
                    int j = ((Sequence)obj).size();
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
            Pair pair = (Pair)obj;
            if (pair.cdr == Empty)
            {
                return i + 1;
            }
            if (obj == obj1 && i > 0)
            {
                return -1;
            }
            if (!(pair.cdr instanceof Pair))
            {
                i++;
                obj = pair.cdr;
            } else
            {
                if (!(obj1 instanceof Pair))
                {
                    return -2;
                }
                Object obj2 = ((Pair)obj1).cdr;
                obj = ((Pair)pair.cdr).cdr;
                i += 2;
                obj1 = obj2;
            }
        } while (true);
    }

    public int nextPos(int i)
    {
        if (i <= 0)
        {
            if (i < 0)
            {
                return 0;
            } else
            {
                return PositionManager.manager.register(new LListPosition(this, 1, true));
            }
        }
        if (!((LListPosition)PositionManager.getPositionObject(i)).gotoNext())
        {
            i = 0;
        }
        return i;
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        car = objectinput.readObject();
        cdr = objectinput.readObject();
    }

    public Object readResolve()
        throws ObjectStreamException
    {
        return this;
    }

    public void setCar(Object obj)
    {
        car = obj;
    }

    public void setCdr(Object obj)
    {
        cdr = obj;
    }

    public void setCdrBackdoor(Object obj)
    {
        cdr = obj;
    }

    public int size()
    {
        int i = listLength(this, true);
        if (i >= 0)
        {
            return i;
        }
        if (i == -1)
        {
            return 0x7fffffff;
        } else
        {
            throw new RuntimeException("not a true list");
        }
    }

    public Object[] toArray()
    {
        int i = size();
        Object aobj[] = new Object[i];
        int j = 0;
        Object obj;
        for (obj = this; j < i && (obj instanceof Pair); j++)
        {
            Pair pair = (Pair)obj;
            aobj[j] = pair.car;
            obj = (Sequence)pair.cdr;
        }

        int k = j;
        for (; j < i; j++)
        {
            aobj[j] = ((Sequence) (obj)).get(j - k);
        }

        return aobj;
    }

    public Object[] toArray(Object aobj[])
    {
        int i = aobj.length;
        int j = length();
        if (j > i)
        {
            aobj = new Object[j];
            i = j;
        }
        int k = 0;
        Object obj;
        for (obj = this; k < j && (obj instanceof Pair); k++)
        {
            Pair pair = (Pair)obj;
            aobj[k] = pair.car;
            obj = (Sequence)pair.cdr;
        }

        int l = k;
        for (; k < j; k++)
        {
            aobj[k] = ((Sequence) (obj)).get(k - l);
        }

        if (j < i)
        {
            aobj[j] = null;
        }
        return aobj;
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeObject(car);
        objectoutput.writeObject(cdr);
    }
}
