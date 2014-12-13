// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.lists.UnescapedData;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;

public class MakeUnescapedData extends Procedure1
{

    public static final MakeUnescapedData unescapedData = new MakeUnescapedData();

    public MakeUnescapedData()
    {
        setProperty(Procedure.validateApplyKey, "gnu.kawa.xml.CompileXmlFunctions:validateApplyMakeUnescapedData");
    }

    public Object apply1(Object obj)
    {
        String s;
        if (obj == null)
        {
            s = "";
        } else
        {
            s = obj.toString();
        }
        return new UnescapedData(s);
    }

}
