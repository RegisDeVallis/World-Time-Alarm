// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;

import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package gnu.bytecode:
//            CodeAttr, LocalVarsAttr, PrimType, Type

public class Label
{

    int first_fixup;
    Type localTypes[];
    boolean needsStackMapEntry;
    int position;
    Type stackTypes[];
    private Object typeChangeListeners[];

    public Label()
    {
        this(-1);
    }

    public Label(int i)
    {
        position = i;
    }

    public Label(CodeAttr codeattr)
    {
        this(-1);
    }

    private void mergeLocalType(int i, Type type)
    {
        Type type1 = localTypes[i];
        Type type2 = mergeTypes(type1, type);
        localTypes[i] = type2;
        if (type2 != type1)
        {
            notifyTypeChangeListeners(i, type2);
        }
    }

    private void notifyTypeChangeListeners(int i, Type type)
    {
        Object aobj[] = typeChangeListeners;
        Object obj;
        if (aobj != null && aobj.length > i)
        {
            if ((obj = aobj[i]) != null)
            {
                if (obj instanceof Label)
                {
                    ((Label)obj).mergeLocalType(i, type);
                } else
                {
                    Iterator iterator = ((ArrayList)obj).iterator();
                    while (iterator.hasNext()) 
                    {
                        ((Label)iterator.next()).mergeLocalType(i, type);
                    }
                }
                if (type == null)
                {
                    aobj[i] = null;
                    return;
                }
            }
        }
    }

    void addTypeChangeListener(int i, Label label)
    {
        Object aobj[] = typeChangeListeners;
        if (aobj != null) goto _L2; else goto _L1
_L1:
        aobj = new Object[i + 10];
        typeChangeListeners = aobj;
_L4:
        Object obj;
        obj = aobj[i];
        if (obj == null)
        {
            aobj[i] = label;
            return;
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (aobj.length <= i)
        {
            aobj = new Object[i + 10];
            System.arraycopy(((Object) (typeChangeListeners)), 0, ((Object) (aobj)), 0, typeChangeListeners.length);
            typeChangeListeners = aobj;
        }
        if (true) goto _L4; else goto _L3
_L3:
        ArrayList arraylist;
        if (obj instanceof Label)
        {
            arraylist = new ArrayList();
            arraylist.add((Label)obj);
            aobj[i] = arraylist;
        } else
        {
            arraylist = (ArrayList)obj;
        }
        arraylist.add(label);
        return;
    }

    void addTypeChangeListeners(CodeAttr codeattr)
    {
        if (codeattr.local_types != null && codeattr.previousLabel != null)
        {
            int i = codeattr.local_types.length;
            for (int j = 0; j < i; j++)
            {
                if (codeattr.local_types[j] != null && (codeattr.varsSetInCurrentBlock == null || codeattr.varsSetInCurrentBlock.length <= j || !codeattr.varsSetInCurrentBlock[j]))
                {
                    codeattr.previousLabel.addTypeChangeListener(j, this);
                }
            }

        }
    }

    public void define(CodeAttr codeattr)
    {
        if (codeattr.reachableHere())
        {
            setTypes(codeattr);
        } else
        if (localTypes != null)
        {
            int i = localTypes.length;
            while (--i >= 0) 
            {
                if (localTypes[i] != null && (codeattr.locals.used == null || codeattr.locals.used[i] == null))
                {
                    localTypes[i] = null;
                }
            }
        }
        codeattr.previousLabel = this;
        codeattr.varsSetInCurrentBlock = null;
        defineRaw(codeattr);
        if (localTypes != null)
        {
            codeattr.setTypes(this);
        }
        codeattr.setReachable(true);
    }

    public void defineRaw(CodeAttr codeattr)
    {
        if (position >= 0)
        {
            throw new Error("label definition more than once");
        }
        position = codeattr.PC;
        first_fixup = codeattr.fixup_count;
        if (first_fixup >= 0)
        {
            codeattr.fixupAdd(1, this);
        }
    }

    public final boolean defined()
    {
        return position >= 0;
    }

    Type mergeTypes(Type type, Type type1)
    {
        if ((type instanceof PrimType) != (type1 instanceof PrimType))
        {
            return null;
        } else
        {
            return Type.lowestCommonSuperType(type, type1);
        }
    }

    public void setTypes(CodeAttr codeattr)
    {
        addTypeChangeListeners(codeattr);
        if (stackTypes != null && codeattr.SP != stackTypes.length)
        {
            throw new InternalError();
        }
        Type atype[] = codeattr.local_types;
        int i;
        if (codeattr.local_types == null)
        {
            i = 0;
        } else
        {
            i = codeattr.local_types.length;
        }
        setTypes(atype, i, codeattr.stack_types, codeattr.SP);
    }

    public void setTypes(Label label)
    {
        setTypes(label.localTypes, label.localTypes.length, label.stackTypes, label.stackTypes.length);
    }

    void setTypes(Type atype[], int i, Type atype1[], int j)
    {
_L5:
        if (i > 0 && atype[i - 1] == null) goto _L2; else goto _L1
_L1:
        if (stackTypes != null)
        {
            break MISSING_BLOCK_LABEL_93;
        }
        if (j == 0)
        {
            stackTypes = Type.typeArray0;
        } else
        {
            stackTypes = new Type[j];
            System.arraycopy(atype1, 0, stackTypes, 0, j);
        }
        if (i != 0) goto _L4; else goto _L3
_L3:
        localTypes = Type.typeArray0;
_L6:
        return;
_L2:
        i--;
          goto _L5
_L4:
        localTypes = new Type[i];
        System.arraycopy(atype, 0, localTypes, 0, i);
        return;
        if (j != stackTypes.length)
        {
            throw new InternalError("inconsistent stack length");
        }
        for (int k = 0; k < j; k++)
        {
            stackTypes[k] = mergeTypes(stackTypes[k], atype1[k]);
        }

        int l;
        int i1;
        if (i < localTypes.length)
        {
            l = i;
        } else
        {
            l = localTypes.length;
        }
        for (i1 = 0; i1 < l; i1++)
        {
            mergeLocalType(i1, atype[i1]);
        }

        int j1 = i;
        while (j1 < localTypes.length) 
        {
            localTypes[j1] = null;
            j1++;
        }
          goto _L6
    }
}
