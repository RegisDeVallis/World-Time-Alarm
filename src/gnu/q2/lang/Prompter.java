// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.q2.lang;

import gnu.mapping.InPort;
import gnu.mapping.Procedure1;

class Prompter extends Procedure1
{

    Prompter()
    {
    }

    public Object apply1(Object obj)
    {
        InPort inport = (InPort)obj;
        int i = 1 + inport.getLineNumber();
        char c = inport.readState;
        if (c == ']')
        {
            return (new StringBuilder()).append("<!--Q2:").append(i).append("-->").toString();
        }
        if (c == '\n')
        {
            c = '-';
        }
        return (new StringBuilder()).append("#|--Q2:").append(i).append("|#").append(c).toString();
    }
}
