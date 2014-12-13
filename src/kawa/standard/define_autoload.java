// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.Sequence;
import gnu.mapping.InPort;
import gnu.mapping.Symbol;
import gnu.text.SyntaxException;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import kawa.lang.AutoloadProcedure;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class define_autoload extends Syntax
{

    public static final define_autoload define_autoload = new define_autoload("define-autoload", false);
    public static final define_autoload define_autoloads_from_file = new define_autoload("define-autoloads-from-file", true);
    boolean fromFile;

    public define_autoload(String s, boolean flag)
    {
        super(s);
        fromFile = flag;
    }

    public static void findAutoloadComments(LispReader lispreader, String s, ScopeExp scopeexp, Translator translator)
        throws IOException, SyntaxException
    {
        boolean flag;
        int i;
        flag = true;
        i = ";;;###autoload".length();
_L5:
        int j = lispreader.peek();
        if (j >= 0) goto _L2; else goto _L1
_L1:
        return;
_L2:
        if (j == 10 || j == 13)
        {
            lispreader.read();
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
        if (!flag || j != 59)
        {
            break;
        }
        int k = 0;
        do
        {
            if (k == i)
            {
                if (k > 0)
                {
                    Object obj2 = lispreader.readObject();
                    if (obj2 instanceof Pair)
                    {
                        Pair pair = (Pair)obj2;
                        Object obj3 = pair.getCar();
                        int l;
                        String s1;
                        String s2;
                        AutoloadProcedure autoloadprocedure;
                        if (obj3 instanceof String)
                        {
                            s1 = obj3.toString();
                        } else
                        if (obj3 instanceof Symbol)
                        {
                            s1 = ((Symbol)obj3).getName();
                        } else
                        {
                            s1 = null;
                        }
                        if (s1 == "defun")
                        {
                            s2 = ((Pair)pair.getCdr()).getCar().toString();
                            Language language = translator.getLanguage();
                            autoloadprocedure = new AutoloadProcedure(s2, s, language);
                        } else
                        {
                            translator.error('w', (new StringBuilder()).append("unsupported ;;;###autoload followed by: ").append(pair.getCar()).toString());
                            s2 = null;
                            autoloadprocedure = null;
                        }
                        if (autoloadprocedure != null)
                        {
                            Declaration declaration = scopeexp.getDefine(s2, 'w', translator);
                            QuoteExp quoteexp = new QuoteExp(autoloadprocedure);
                            declaration.setFlag(16384L);
                            declaration.noteValue(quoteexp);
                            declaration.setProcedureDecl(true);
                            declaration.setType(Compilation.typeProcedure);
                        }
                    }
                    flag = false;
                    continue; /* Loop/switch isn't completed */
                }
                break;
            }
            j = lispreader.read();
            if (j < 0)
            {
                continue;
            }
            if (j == 10 || j == 13)
            {
                flag = true;
                continue; /* Loop/switch isn't completed */
            }
            if (k >= 0)
            {
                l = k + 1;
                if (j == ";;;###autoload".charAt(k))
                {
                    k = l;
                } else
                {
                    k = -1;
                }
            }
        } while (true);
        if (true) goto _L1; else goto _L3
_L3:
        lispreader.skip();
        if (j != 35 || lispreader.peek() != 124)
        {
            break; /* Loop/switch isn't completed */
        }
        lispreader.skip();
        lispreader.readNestedComment('#', '|');
        flag = false;
        if (true) goto _L5; else goto _L4
_L4:
        boolean flag1 = Character.isWhitespace((char)j);
        flag = false;
        if (!flag1)
        {
            Object obj = lispreader.readObject(j);
            Object obj1 = Sequence.eofValue;
            flag = false;
            if (obj == obj1)
            {
                return;
            }
        }
        if (true) goto _L5; else goto _L6
_L6:
    }

    public static boolean process(Object obj, Object obj1, Vector vector, ScopeExp scopeexp, Translator translator)
    {
        if (!(obj instanceof Pair)) goto _L2; else goto _L1
_L1:
        Pair pair = (Pair)obj;
        if (!process(pair.getCar(), obj1, vector, scopeexp, translator) || !process(pair.getCdr(), obj1, vector, scopeexp, translator)) goto _L4; else goto _L3
_L3:
        return true;
_L4:
        return false;
_L2:
        if (obj != LList.Empty)
        {
            if ((obj instanceof String) || (obj instanceof Symbol))
            {
                String s = obj.toString();
                Declaration declaration = scopeexp.getDefine(s, 'w', translator);
                if (obj1 instanceof String)
                {
                    String s1 = (String)obj1;
                    int i = s1.length();
                    if (i > 2 && s1.charAt(0) == '<' && s1.charAt(i - 1) == '>')
                    {
                        obj1 = s1.substring(1, i - 1);
                    }
                }
                QuoteExp quoteexp = new QuoteExp(new AutoloadProcedure(s, obj1.toString(), translator.getLanguage()));
                declaration.setFlag(16384L);
                declaration.noteValue(quoteexp);
                return true;
            } else
            {
                return false;
            }
        }
        if (true) goto _L3; else goto _L5
_L5:
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        return null;
    }

    public boolean scanFile(String s, ScopeExp scopeexp, Translator translator)
    {
        if (!s.endsWith(".el"));
        File file = new File(s);
        if (!file.isAbsolute())
        {
            file = new File((new File(translator.getFileName())).getParent(), s);
        }
        String s1 = file.getPath();
        int i = s1.lastIndexOf('.');
        if (i >= 0)
        {
            String s2 = s1.substring(i);
            Language language = Language.getInstance(s2);
            if (language == null)
            {
                translator.syntaxError((new StringBuilder()).append("unknown extension for ").append(s1).toString());
                return true;
            }
            String s3 = translator.classPrefix;
            int j = s2.length();
            String s4;
            for (s4 = s.substring(0, s.length() - j); s4.startsWith("../"); s4 = s4.substring(3))
            {
                int k = s3.lastIndexOf('.', -2 + s3.length());
                if (k < 0)
                {
                    translator.syntaxError((new StringBuilder()).append("cannot use relative filename \"").append(s).append("\" with simple prefix \"").append(s3).append("\"").toString());
                    return false;
                }
                s3 = s3.substring(0, k + 1);
            }

            String s5 = (new StringBuilder()).append(s3).append(s4).toString().replace('/', '.');
            try
            {
                findAutoloadComments((LispReader)language.getLexer(InPort.openFile(s1), translator.getMessages()), s5, scopeexp, translator);
            }
            catch (Exception exception)
            {
                translator.syntaxError((new StringBuilder()).append("error reading ").append(s1).append(": ").append(exception).toString());
                return true;
            }
        }
        return true;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeexp, Translator translator)
    {
        if (pair.getCdr() instanceof Pair) goto _L2; else goto _L1
_L1:
        boolean flag1 = super.scanForDefinitions(pair, vector, scopeexp, translator);
_L6:
        return flag1;
_L2:
        Pair pair1;
        pair1 = (Pair)pair.getCdr();
        if (!fromFile)
        {
            break MISSING_BLOCK_LABEL_125;
        }
_L8:
        if (pair1.getCar() instanceof CharSequence) goto _L4; else goto _L3
_L3:
        translator.syntaxError("invalid syntax for define-autoloads-from-file");
        return false;
_L4:
        boolean flag;
        flag = scanFile(pair1.getCar().toString(), scopeexp, translator);
        flag1 = false;
        if (!flag) goto _L6; else goto _L5
_L5:
        Object obj1;
        obj1 = pair1.getCdr();
        if (obj1 == LList.Empty)
        {
            return true;
        }
        if (!(obj1 instanceof Pair)) goto _L3; else goto _L7
_L7:
        pair1 = (Pair)pair1.getCdr();
          goto _L8
        Object obj = pair1.getCar();
        if (pair1.getCdr() instanceof Pair)
        {
            return process(obj, ((Pair)pair1.getCdr()).getCar(), vector, scopeexp, translator);
        } else
        {
            translator.syntaxError("invalid syntax for define-autoload");
            return false;
        }
    }

}
