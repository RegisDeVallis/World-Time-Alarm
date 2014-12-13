// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;


// Referenced classes of package gnu.bytecode:
//            Label, CodeAttr, ClassType, Type, 
//            TryState

public class SwitchState
{

    Label after_label;
    Label cases_label;
    Label defaultLabel;
    Label labels[];
    int maxValue;
    int minValue;
    int numCases;
    TryState outerTry;
    Label switch_label;
    int values[];

    public SwitchState(CodeAttr codeattr)
    {
        switch_label = new Label(codeattr);
        cases_label = new Label(codeattr);
        after_label = new Label(codeattr);
        outerTry = codeattr.try_stack;
        numCases = 0;
    }

    public boolean addCase(int i, CodeAttr codeattr)
    {
        Label label = new Label(codeattr);
        label.setTypes(cases_label);
        label.define(codeattr);
        return insertCase(i, label, codeattr);
    }

    public boolean addCaseGoto(int i, CodeAttr codeattr, Label label)
    {
        boolean flag = insertCase(i, label, codeattr);
        label.setTypes(cases_label);
        codeattr.setUnreachable();
        return flag;
    }

    public void addDefault(CodeAttr codeattr)
    {
        Label label = new Label(codeattr);
        label.setTypes(cases_label);
        label.define(codeattr);
        if (defaultLabel != null)
        {
            throw new Error();
        } else
        {
            defaultLabel = label;
            return;
        }
    }

    public void exitSwitch(CodeAttr codeattr)
    {
        if (outerTry != codeattr.try_stack)
        {
            throw new Error("exitSwitch cannot exit through a try");
        } else
        {
            codeattr.emitGoto(after_label);
            return;
        }
    }

    public void finish(CodeAttr codeattr)
    {
        if (defaultLabel == null)
        {
            defaultLabel = new Label(codeattr);
            defaultLabel.define(codeattr);
            ClassType classtype = ClassType.make("java.lang.RuntimeException");
            codeattr.emitNew(classtype);
            codeattr.emitDup(classtype);
            codeattr.emitPushString("bad case value!");
            Type atype[] = new Type[1];
            atype[0] = Type.string_type;
            codeattr.emitInvokeSpecial(classtype.addMethod("<init>", 1, atype, Type.voidType));
            codeattr.emitThrow();
        }
        codeattr.fixupChain(switch_label, after_label);
        if (numCases > 1) goto _L2; else goto _L1
_L1:
        codeattr.pushType(Type.intType);
        if (numCases == 1)
        {
            if (minValue == 0)
            {
                codeattr.emitIfIntEqZero();
            } else
            {
                codeattr.emitPushInt(minValue);
                codeattr.emitIfEq();
            }
            codeattr.emitGoto(labels[0]);
            codeattr.emitElse();
            codeattr.emitGoto(defaultLabel);
            codeattr.emitFi();
        } else
        {
            codeattr.emitPop(1);
            codeattr.emitGoto(defaultLabel);
        }
_L4:
        codeattr.fixupChain(after_label, cases_label);
        return;
_L2:
        if (2 * numCases < maxValue - minValue)
        {
            break; /* Loop/switch isn't completed */
        }
        codeattr.reserve(13 + 4 * (1 + (maxValue - minValue)));
        codeattr.fixupAdd(2, null);
        codeattr.put1(170);
        codeattr.fixupAdd(3, defaultLabel);
        codeattr.PC = 4 + codeattr.PC;
        codeattr.put4(minValue);
        codeattr.put4(maxValue);
        int j = 0;
        int k = minValue;
        while (k <= maxValue) 
        {
            Label label;
            if (values[j] == k)
            {
                Label alabel[] = labels;
                int l = j + 1;
                label = alabel[j];
                j = l;
            } else
            {
                label = defaultLabel;
            }
            codeattr.fixupAdd(3, label);
            codeattr.PC = 4 + codeattr.PC;
            k++;
        }
        if (true) goto _L4; else goto _L3
_L3:
        codeattr.reserve(9 + 8 * numCases);
        codeattr.fixupAdd(2, null);
        codeattr.put1(171);
        codeattr.fixupAdd(3, defaultLabel);
        codeattr.PC = 4 + codeattr.PC;
        codeattr.put4(numCases);
        int i = 0;
        while (i < numCases) 
        {
            codeattr.put4(values[i]);
            codeattr.fixupAdd(3, labels[i]);
            codeattr.PC = 4 + codeattr.PC;
            i++;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public int getMaxValue()
    {
        return maxValue;
    }

    public int getNumCases()
    {
        return numCases;
    }

    public boolean insertCase(int i, Label label, CodeAttr codeattr)
    {
        int ai[];
        Label alabel[];
        if (values == null)
        {
            values = new int[10];
            labels = new Label[10];
            numCases = 1;
            maxValue = i;
            minValue = i;
            values[0] = i;
            labels[0] = label;
            return true;
        }
        ai = values;
        alabel = labels;
        if (numCases >= values.length)
        {
            values = new int[2 * numCases];
            labels = new Label[2 * numCases];
        }
        if (i >= minValue) goto _L2; else goto _L1
_L1:
        int l;
        l = 0;
        minValue = i;
_L4:
        int i1 = numCases - l;
        System.arraycopy(ai, l, values, l + 1, i1);
        System.arraycopy(ai, 0, values, 0, l);
        values[l] = i;
        System.arraycopy(alabel, l, labels, l + 1, i1);
        System.arraycopy(alabel, 0, labels, 0, l);
        labels[l] = label;
        numCases = 1 + numCases;
        return true;
_L2:
        if (i <= maxValue)
        {
            break; /* Loop/switch isn't completed */
        }
        l = numCases;
        maxValue = i;
        if (true) goto _L4; else goto _L3
_L3:
        int j = 0;
        int k = -1 + numCases;
        l = 0;
        while (j <= k) 
        {
            l = j + k >>> 1;
            if (ai[l] >= i)
            {
                k = l - 1;
            } else
            {
                j = ++l;
            }
        }
        if (i == ai[l])
        {
            return false;
        }
        if (true) goto _L4; else goto _L5
_L5:
    }

    public void switchValuePushed(CodeAttr codeattr)
    {
        codeattr.popType();
        cases_label.setTypes(codeattr);
        codeattr.fixupChain(cases_label, switch_label);
    }
}
