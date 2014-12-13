// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.ModuleManager;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Procedure;
import java.io.PrintStream;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

// Referenced classes of package kawa.standard:
//            require

public class ImportFromLibrary extends Syntax
{

    private static final String BUILTIN = "";
    private static final String MISSING = null;
    static final String SRFI97Map[][];
    public static final ImportFromLibrary instance = new ImportFromLibrary();
    public String classPrefixPath[] = {
        "", "kawa.lib."
    };

    public ImportFromLibrary()
    {
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        return null;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeexp, Translator translator)
    {
        Procedure procedure;
        gnu.expr.ModuleInfo moduleinfo;
        String s1;
        String s3;
        String s4;
        int l;
        String s5;
        Object obj = pair.getCdr();
        if (!(obj instanceof Pair))
        {
            return false;
        }
        Pair pair1 = (Pair)obj;
        Object obj1 = pair1.getCar();
        if (LList.listLength(obj1, false) <= 0)
        {
            translator.error('e', "expected <library reference> which must be a list");
            return false;
        }
        Object obj2 = pair1.getCdr();
        boolean flag = obj2 instanceof Pair;
        procedure = null;
        if (flag)
        {
            boolean flag1 = ((Pair)obj2).getCar() instanceof Procedure;
            procedure = null;
            if (flag1)
            {
                procedure = (Procedure)((Pair)obj2).getCar();
            }
        }
        Object obj3 = null;
        String s = null;
        StringBuffer stringbuffer = new StringBuffer();
        while (obj1 instanceof Pair) 
        {
            Pair pair2 = (Pair)obj1;
            Object obj4 = pair2.getCar();
            Object obj5 = pair2.getCdr();
            if (obj4 instanceof Pair)
            {
                if (obj3 != null)
                {
                    translator.error('e', (new StringBuilder()).append("duplicate version reference - was ").append(obj3).toString());
                }
                obj3 = obj4;
                System.err.println((new StringBuilder()).append("import version ").append(obj4).toString());
            } else
            if (obj4 instanceof String)
            {
                if (obj5 instanceof Pair)
                {
                    translator.error('e', "source specifier must be last elemnt in library reference");
                }
                s = (String)obj4;
            } else
            {
                if (stringbuffer.length() > 0)
                {
                    stringbuffer.append('.');
                }
                stringbuffer.append(Compilation.mangleNameIfNeeded(obj4.toString()));
            }
            obj1 = obj5;
        }
        moduleinfo = null;
        if (s != null)
        {
            moduleinfo = require.lookupModuleFromSourcePath(s, scopeexp);
            if (moduleinfo == null)
            {
                translator.error('e', (new StringBuilder()).append("malformed URL: ").append(s).toString());
                return false;
            }
        }
        s1 = stringbuffer.toString();
        if (!s1.startsWith("srfi."))
        {
            break MISSING_BLOCK_LABEL_717;
        }
        s3 = Compilation.demangleName(s1.substring(5));
        int k = s3.indexOf('.');
        char c;
        if (k < 0)
        {
            s4 = null;
            k = s3.length();
        } else
        {
            s4 = s3.substring(k + 1);
        }
        if (k >= 2) goto _L2; else goto _L1
_L1:
        c = s3.charAt(0);
        s5 = null;
        if (c != ':') goto _L3; else goto _L2
_L2:
        l = 1;
_L8:
        if (l != k) goto _L5; else goto _L4
_L4:
        s5 = s3.substring(1, k);
_L3:
        if (s5 == null)
        {
            translator.error('e', "SRFI library reference must have the form: (srfi :NNN [name])");
            return false;
        }
        break; /* Loop/switch isn't completed */
_L5:
        int i1;
        i1 = Character.digit(s3.charAt(l), 10);
        s5 = null;
        if (i1 < 0) goto _L3; else goto _L6
_L6:
        l++;
        if (true) goto _L8; else goto _L7
_L7:
        int j1 = SRFI97Map.length;
        do
        {
            if (--j1 < 0)
            {
                translator.error('e', (new StringBuilder()).append("unknown SRFI number '").append(s5).append("' in SRFI library reference").toString());
                return false;
            }
        } while (!SRFI97Map[j1][0].equals(s5));
        String s6 = SRFI97Map[j1][1];
        String s7 = SRFI97Map[j1][2];
        if (s4 != null && !s4.equals(s6))
        {
            translator.error('w', (new StringBuilder()).append("the name of SRFI ").append(s5).append(" should be '").append(s6).append('\'').toString());
        }
        if (s7 == "")
        {
            return true;
        }
        if (s7 == MISSING)
        {
            translator.error('e', (new StringBuilder()).append("sorry - Kawa does not support SRFI ").append(s5).append(" (").append(s6).append(')').toString());
            return false;
        }
        s1 = s7;
        int i;
        int j;
        i = classPrefixPath.length;
        j = 0;
_L10:
        String s2;
        if (j >= i)
        {
            break; /* Loop/switch isn't completed */
        }
        s2 = (new StringBuilder()).append(classPrefixPath[j]).append(s1).toString();
        gnu.expr.ModuleInfo moduleinfo1 = ModuleManager.getInstance().findWithClassName(s2);
        moduleinfo = moduleinfo1;
_L11:
        j++;
        if (true) goto _L10; else goto _L9
        Exception exception;
        exception;
          goto _L11
_L9:
        if (moduleinfo == null)
        {
            translator.error('e', (new StringBuilder()).append("unknown class ").append(s1).toString());
            return false;
        } else
        {
            require.importDefinitions(null, moduleinfo, procedure, vector, scopeexp, translator);
            return true;
        }
    }

    static 
    {
        String as[][] = new String[48][];
        as[0] = (new String[] {
            "1", "lists", "gnu.kawa.slib.srfi1"
        });
        as[1] = (new String[] {
            "2", "and-let*", "gnu.kawa.slib.srfi2"
        });
        String as1[] = new String[3];
        as1[0] = "5";
        as1[1] = "let";
        as1[2] = MISSING;
        as[2] = as1;
        as[3] = (new String[] {
            "6", "basic-string-ports", ""
        });
        as[4] = (new String[] {
            "8", "receive", ""
        });
        as[5] = (new String[] {
            "9", "records", ""
        });
        as[6] = (new String[] {
            "11", "let-values", ""
        });
        as[7] = (new String[] {
            "13", "strings", "gnu.kawa.slib.srfi13"
        });
        String as2[] = new String[3];
        as2[0] = "14";
        as2[1] = "char-sets";
        as2[2] = MISSING;
        as[8] = as2;
        as[9] = (new String[] {
            "16", "case-lambda", ""
        });
        as[10] = (new String[] {
            "17", "generalized-set!", ""
        });
        String as3[] = new String[3];
        as3[0] = "18";
        as3[1] = "multithreading";
        as3[2] = MISSING;
        as[11] = as3;
        String as4[] = new String[3];
        as4[0] = "19";
        as4[1] = "time";
        as4[2] = MISSING;
        as[12] = as4;
        String as5[] = new String[3];
        as5[0] = "21";
        as5[1] = "real-time-multithreading";
        as5[2] = MISSING;
        as[13] = as5;
        as[14] = (new String[] {
            "23", "error", ""
        });
        as[15] = (new String[] {
            "25", "multi-dimensional-arrays", ""
        });
        as[16] = (new String[] {
            "26", "cut", ""
        });
        String as6[] = new String[3];
        as6[0] = "27";
        as6[1] = "random-bits";
        as6[2] = MISSING;
        as[17] = as6;
        as[18] = (new String[] {
            "28", "basic-format-strings", ""
        });
        String as7[] = new String[3];
        as7[0] = "29";
        as7[1] = "localization";
        as7[2] = MISSING;
        as[19] = as7;
        String as8[] = new String[3];
        as8[0] = "31";
        as8[1] = "rec";
        as8[2] = MISSING;
        as[20] = as8;
        String as9[] = new String[3];
        as9[0] = "38";
        as9[1] = "with-shared-structure";
        as9[2] = MISSING;
        as[21] = as9;
        as[22] = (new String[] {
            "39", "parameters", ""
        });
        String as10[] = new String[3];
        as10[0] = "41";
        as10[1] = "streams";
        as10[2] = MISSING;
        as[23] = as10;
        String as11[] = new String[3];
        as11[0] = "42";
        as11[1] = "eager-comprehensions";
        as11[2] = MISSING;
        as[24] = as11;
        String as12[] = new String[3];
        as12[0] = "43";
        as12[1] = "vectors";
        as12[2] = MISSING;
        as[25] = as12;
        String as13[] = new String[3];
        as13[0] = "44";
        as13[1] = "collections";
        as13[2] = MISSING;
        as[26] = as13;
        String as14[] = new String[3];
        as14[0] = "45";
        as14[1] = "lazy";
        as14[2] = MISSING;
        as[27] = as14;
        String as15[] = new String[3];
        as15[0] = "46";
        as15[1] = "syntax-rules";
        as15[2] = MISSING;
        as[28] = as15;
        String as16[] = new String[3];
        as16[0] = "47";
        as16[1] = "arrays";
        as16[2] = MISSING;
        as[29] = as16;
        String as17[] = new String[3];
        as17[0] = "48";
        as17[1] = "intermediate-format-strings";
        as17[2] = MISSING;
        as[30] = as17;
        String as18[] = new String[3];
        as18[0] = "51";
        as18[1] = "rest-values";
        as18[2] = MISSING;
        as[31] = as18;
        String as19[] = new String[3];
        as19[0] = "54";
        as19[1] = "cat";
        as19[2] = MISSING;
        as[32] = as19;
        String as20[] = new String[3];
        as20[0] = "57";
        as20[1] = "records";
        as20[2] = MISSING;
        as[33] = as20;
        String as21[] = new String[3];
        as21[0] = "59";
        as21[1] = "vicinities";
        as21[2] = MISSING;
        as[34] = as21;
        String as22[] = new String[3];
        as22[0] = "60";
        as22[1] = "integer-bits";
        as22[2] = MISSING;
        as[35] = as22;
        String as23[] = new String[3];
        as23[0] = "61";
        as23[1] = "cond";
        as23[2] = MISSING;
        as[36] = as23;
        String as24[] = new String[3];
        as24[0] = "63";
        as24[1] = "arrays";
        as24[2] = MISSING;
        as[37] = as24;
        as[38] = (new String[] {
            "64", "testing", "gnu.kawa.slib.testing"
        });
        String as25[] = new String[3];
        as25[0] = "66";
        as25[1] = "octet-vectors";
        as25[2] = MISSING;
        as[39] = as25;
        String as26[] = new String[3];
        as26[0] = "67";
        as26[1] = "compare-procedures";
        as26[2] = MISSING;
        as[40] = as26;
        as[41] = (new String[] {
            "69", "basic-hash-tables", "gnu.kawa.slib.srfi69"
        });
        String as27[] = new String[3];
        as27[0] = "71";
        as27[1] = "let";
        as27[2] = MISSING;
        as[42] = as27;
        String as28[] = new String[3];
        as28[0] = "74";
        as28[1] = "blobs";
        as28[2] = MISSING;
        as[43] = as28;
        String as29[] = new String[3];
        as29[0] = "78";
        as29[1] = "lightweight-testing";
        as29[2] = MISSING;
        as[44] = as29;
        String as30[] = new String[3];
        as30[0] = "86";
        as30[1] = "mu-and-nu";
        as30[2] = MISSING;
        as[45] = as30;
        String as31[] = new String[3];
        as31[0] = "87";
        as31[1] = "case";
        as31[2] = MISSING;
        as[46] = as31;
        as[47] = (new String[] {
            "95", "sorting-and-merging", "kawa.lib.srfi95"
        });
        SRFI97Map = as;
    }
}
