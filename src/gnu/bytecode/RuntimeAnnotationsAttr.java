// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.bytecode;


// Referenced classes of package gnu.bytecode:
//            MiscAttr, ClassTypeWriter, CpoolEntry, CpoolValue1, 
//            AttrContainer

public class RuntimeAnnotationsAttr extends MiscAttr
{

    int numEntries;

    public RuntimeAnnotationsAttr(String s, byte abyte0[], AttrContainer attrcontainer)
    {
        super(s, abyte0, 0, abyte0.length);
        addToFrontOf(attrcontainer);
        numEntries = u2(0);
    }

    public void print(ClassTypeWriter classtypewriter)
    {
        classtypewriter.print("Attribute \"");
        classtypewriter.print(getName());
        classtypewriter.print("\", length:");
        classtypewriter.print(getLength());
        classtypewriter.print(", number of entries: ");
        classtypewriter.println(numEntries);
        int i = offset;
        offset = i + 2;
        for (int j = 0; j < numEntries; j++)
        {
            printAnnotation(2, classtypewriter);
        }

        offset = i;
    }

    public void printAnnotation(int i, ClassTypeWriter classtypewriter)
    {
        int j = u2();
        classtypewriter.printSpaces(i);
        classtypewriter.printOptionalIndex(j);
        classtypewriter.print('@');
        classtypewriter.printContantUtf8AsClass(j);
        int k = u2();
        classtypewriter.println();
        int l = i + 2;
        for (int i1 = 0; i1 < k; i1++)
        {
            int j1 = u2();
            classtypewriter.printSpaces(l);
            classtypewriter.printOptionalIndex(j1);
            classtypewriter.printConstantTersely(j1, 1);
            classtypewriter.print(" => ");
            printAnnotationElementValue(l, classtypewriter);
            classtypewriter.println();
        }

    }

    public void printAnnotationElementValue(int i, ClassTypeWriter classtypewriter)
    {
        int j;
        byte byte0;
        j = u1();
        if ((8 & classtypewriter.flags) != 0)
        {
            classtypewriter.print("[kind:");
            if (j >= 65 && j <= 122)
            {
                classtypewriter.print((char)j);
            } else
            {
                classtypewriter.print(j);
            }
            classtypewriter.print("] ");
        }
        byte0 = 0;
        j;
        JVM INSTR lookupswitch 13: default 164
    //                   64: 425
    //                   66: 173
    //                   67: 173
    //                   68: 191
    //                   70: 200
    //                   73: 173
    //                   74: 183
    //                   83: 173
    //                   90: 173
    //                   91: 445
    //                   99: 406
    //                   101: 315
    //                   115: 208;
           goto _L1 _L2 _L3 _L3 _L4 _L5 _L3 _L6 _L3 _L3 _L7 _L8 _L9 _L10
_L1:
        return;
_L3:
        byte0 = 0;
        if (true)
        {
            byte0 = 3;
        }
_L6:
        if (byte0 == 0)
        {
            byte0 = 5;
        }
_L4:
        if (byte0 == 0)
        {
            byte0 = 6;
        }
_L5:
        if (byte0 == 0)
        {
            byte0 = 4;
        }
_L10:
        if (byte0 == 0)
        {
            byte0 = 1;
        }
        int l1 = u2();
        CpoolEntry cpoolentry = classtypewriter.getCpoolEntry(l1);
        classtypewriter.printOptionalIndex(cpoolentry);
        if (j == 90 && cpoolentry != null && cpoolentry.getTag() == 3)
        {
            CpoolValue1 cpoolvalue1 = (CpoolValue1)cpoolentry;
            if (cpoolvalue1.value == 0 || cpoolvalue1.value == 1)
            {
                String s;
                if (cpoolvalue1.value == 0)
                {
                    s = "false";
                } else
                {
                    s = "true";
                }
                classtypewriter.print(s);
                return;
            }
        }
        classtypewriter.printConstantTersely(l1, byte0);
        return;
_L9:
        int j1 = u2();
        int k1 = u2();
        classtypewriter.print("enum[");
        if ((8 & classtypewriter.flags) != 0)
        {
            classtypewriter.print("type:");
        }
        classtypewriter.printOptionalIndex(j1);
        classtypewriter.printContantUtf8AsClass(j1);
        if ((8 & classtypewriter.flags) != 0)
        {
            classtypewriter.print(" value:");
        } else
        {
            classtypewriter.print(' ');
        }
        classtypewriter.printOptionalIndex(k1);
        classtypewriter.printConstantTersely(k1, 1);
        classtypewriter.print("]");
        return;
_L8:
        int i1 = u2();
        classtypewriter.printOptionalIndex(i1);
        classtypewriter.printContantUtf8AsClass(i1);
        return;
_L2:
        classtypewriter.println();
        classtypewriter.printSpaces(i + 2);
        printAnnotation(i + 2, classtypewriter);
        return;
_L7:
        int k = u2();
        classtypewriter.print("array length:");
        classtypewriter.print(k);
        int l = 0;
        while (l < k) 
        {
            classtypewriter.println();
            classtypewriter.printSpaces(i + 2);
            classtypewriter.print(l);
            classtypewriter.print(": ");
            printAnnotationElementValue(i + 2, classtypewriter);
            l++;
        }
        if (true) goto _L1; else goto _L11
_L11:
    }
}
