// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.lists.FString;
import gnu.lists.FVector;
import gnu.mapping.Environment;
import gnu.mapping.Symbol;
import gnu.mapping.ThreadLocation;
import java.io.PrintStream;
import java.lang.reflect.Field;

// Referenced classes of package gnu.expr:
//            Language

public class ApplicationMainSupport
{

    public static String commandLineArgArray[];
    public static FVector commandLineArguments;
    public static boolean processCommandLinePropertyAssignments;
    static String propertyFields[][] = {
        {
            "out:doctype-system", "gnu.xml.XMLPrinter", "doctypeSystem"
        }, {
            "out:doctype-public", "gnu.xml.XMLPrinter", "doctypePublic"
        }, {
            "out:base", "gnu.kawa.functions.DisplayFormat", "outBase"
        }, {
            "out:radix", "gnu.kawa.functions.DisplayFormat", "outRadix"
        }, {
            "out:line-length", "gnu.text.PrettyWriter", "lineLengthLoc"
        }, {
            "out:right-margin", "gnu.text.PrettyWriter", "lineLengthLoc"
        }, {
            "out:miser-width", "gnu.text.PrettyWriter", "miserWidthLoc"
        }, {
            "out:xml-indent", "gnu.xml.XMLPrinter", "indentLoc"
        }, {
            "display:toolkit", "gnu.kawa.models.Display", "myDisplay"
        }, null
    };

    public ApplicationMainSupport()
    {
    }

    public static void processArgs(String as[])
    {
        boolean flag = processCommandLinePropertyAssignments;
        int i = 0;
        if (flag)
        {
            for (; i < as.length && processSetProperty(as[i]); i++) { }
        }
        setArgs(as, i);
    }

    public static void processSetProperties()
    {
        String as[] = commandLineArgArray;
        if (as == null)
        {
            processCommandLinePropertyAssignments = true;
        } else
        {
            int i;
            for (i = 0; i < as.length && processSetProperty(as[i]); i++) { }
            if (i != 0)
            {
                setArgs(as, i);
                return;
            }
        }
    }

    public static boolean processSetProperty(String s)
    {
        String s1;
        String s2;
        int j;
        int i = s.indexOf('=');
        if (i <= 0)
        {
            return false;
        }
        s1 = s.substring(0, i);
        s2 = s.substring(i + 1);
        j = 0;
_L5:
        String as[] = propertyFields[j];
        if (as != null) goto _L2; else goto _L1
_L1:
        Symbol symbol = Symbol.parse(s1);
        Language.getDefaultLanguage();
        Environment.getCurrent().define(symbol, null, s2);
        return true;
_L2:
        String s3;
        String s4;
        if (!s1.equals(as[0]))
        {
            break; /* Loop/switch isn't completed */
        }
        s3 = as[1];
        s4 = as[2];
        ((ThreadLocation)Class.forName(s3).getDeclaredField(s4).get(null)).setGlobal(s2);
        if (true) goto _L1; else goto _L3
        Throwable throwable;
        throwable;
        System.err.println((new StringBuilder()).append("error setting property ").append(s1).append(" field ").append(s3).append('.').append(s4).append(": ").append(throwable).toString());
        System.exit(-1);
_L3:
        j++;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public static void setArgs(String as[], int i)
    {
        int j = as.length - i;
        Object aobj[] = new Object[j];
        int l;
        if (i == 0)
        {
            commandLineArgArray = as;
        } else
        {
            String as1[] = new String[j];
            for (int k = j; --k >= 0;)
            {
                as1[k] = as[k + i];
            }

            commandLineArgArray = as1;
        }
        for (l = j; --l >= 0;)
        {
            aobj[l] = new FString(as[l + i]);
        }

        commandLineArguments = new FVector(aobj);
        Environment.getCurrent().put("command-line-arguments", commandLineArguments);
    }

}
