// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleManager;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.SetExp;
import gnu.kawa.reflect.Invoke;
import gnu.kawa.reflect.SlotGet;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.InPort;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.text.Path;
import gnu.text.SyntaxException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class require extends Syntax
{

    private static final String SLIB_PREFIX = "gnu.kawa.slib.";
    static Hashtable featureMap = new Hashtable();
    public static final require require;

    public require()
    {
    }

    public static Object find(String s)
    {
        return ModuleManager.getInstance().findWithClassName(s).getInstance();
    }

    public static boolean importDefinitions(String s, ModuleInfo moduleinfo, Procedure procedure, Vector vector, ScopeExp scopeexp, Compilation compilation)
    {
        ModuleManager modulemanager = ModuleManager.getInstance();
        if ((1 & moduleinfo.getState()) != 0 || moduleinfo.getCompilation() != null || moduleinfo.checkCurrent(modulemanager, System.currentTimeMillis()))
        {
            break MISSING_BLOCK_LABEL_112;
        }
        gnu.text.SourceMessages sourcemessages = compilation.getMessages();
        Language language1 = Language.getDefaultLanguage();
        InPort inport;
        int l;
        Compilation compilation1;
        try
        {
            inport = InPort.openFile(moduleinfo.getSourceAbsPath());
            moduleinfo.clearClass();
            moduleinfo.setClassName(s);
        }
        catch (FileNotFoundException filenotfoundexception)
        {
            compilation.error('e', (new StringBuilder()).append("not found: ").append(filenotfoundexception.getMessage()).toString());
            return false;
        }
        catch (IOException ioexception)
        {
            compilation.error('e', (new StringBuilder()).append("caught ").append(ioexception).toString());
            return false;
        }
        catch (SyntaxException syntaxexception)
        {
            if (syntaxexception.getMessages() != sourcemessages)
            {
                throw new RuntimeException((new StringBuilder()).append("confussing syntax error: ").append(syntaxexception).toString());
            } else
            {
                return false;
            }
        }
        l = 8;
        if (compilation.immediate)
        {
            l |= 1;
        }
        compilation1 = language1.parse(inport, sourcemessages, l, moduleinfo);
        moduleinfo.setClassName(compilation1.getModule().classFor(compilation1).getName());
        ClassType classtype;
        String s1;
        boolean flag;
        boolean flag1;
        Declaration declaration;
        Object obj;
        Field field;
        Language language;
        int i;
        Vector vector1;
        Declaration declaration1;
        if (compilation.minfo != null && compilation.getState() < 4)
        {
            compilation.minfo.addDependency(moduleinfo);
            if (!moduleinfo.loadEager(12) && moduleinfo.getState() < 6)
            {
                compilation.pushPendingImport(moduleinfo, scopeexp, vector.size());
                return true;
            }
        }
        classtype = moduleinfo.getClassType();
        s1 = classtype.getName();
        flag = compilation.sharedModuleDefs();
        ClassType classtype1;
        Expression aexpression[];
        QuoteExp quoteexp;
        ModuleExp moduleexp;
        if (moduleinfo.getState() < 6)
        {
            flag1 = moduleinfo.getCompilation().makeRunnable();
        } else
        {
            flag1 = classtype.isSubtype(Compilation.typeRunnable);
        }
        declaration = null;
        classtype1 = ClassType.make("kawa.standard.require");
        aexpression = new Expression[1];
        quoteexp = new QuoteExp(s1);
        aexpression[0] = quoteexp;
        obj = Invoke.makeInvokeStatic(classtype1, "find", aexpression);
        field = null;
        language = compilation.getLanguage();
        ((Expression) (obj)).setLine(compilation);
        i = vector.size();
        moduleexp = moduleinfo.setupModuleExp();
        vector1 = new Vector();
        declaration1 = moduleexp.firstDecl();
        if (declaration1 == null)
        {
            break MISSING_BLOCK_LABEL_1117;
        }
        if (!declaration1.isPrivate())
        {
            break; /* Loop/switch isn't completed */
        }
_L6:
        declaration1 = declaration1.nextDecl();
        if (true) goto _L2; else goto _L1
_L2:
        break MISSING_BLOCK_LABEL_409;
_L1:
        Symbol symbol;
        symbol = (Symbol)declaration1.getSymbol();
        if (procedure == null)
        {
            break MISSING_BLOCK_LABEL_537;
        }
        Object obj2 = procedure.apply1(symbol);
        Object obj1 = obj2;
_L4:
        if (obj1 != null)
        {
            if (obj1 instanceof Symbol)
            {
                break MISSING_BLOCK_LABEL_530;
            }
            compilation.error('e', (new StringBuilder()).append("internal error - import name mapper returned non-symbol: ").append(obj1.getClass().getName()).toString());
        }
        continue; /* Loop/switch isn't completed */
        Throwable throwable;
        throwable;
        obj1 = throwable;
        if (true) goto _L4; else goto _L3
_L3:
        symbol = (Symbol)obj1;
        boolean flag2;
label0:
        {
            flag2 = declaration1.getFlag(2048L);
            if (!flag2 && declaration == null)
            {
                declaration = new Declaration(SimpleSymbol.valueOf((new StringBuilder()).append(s1.replace('.', '$')).append("$instance").toString()), classtype);
                declaration.setPrivate(true);
                declaration.setFlag(0x40004000L);
                scopeexp.addDeclaration(declaration);
                declaration.noteValue(((Expression) (obj)));
                SetExp setexp1 = new SetExp(declaration, ((Expression) (obj)));
                setexp1.setLine(compilation);
                setexp1.setDefining(true);
                vector.addElement(setexp1);
                i = vector.size();
                declaration.setFlag(0x20000000L);
                if (flag1)
                {
                    declaration.setSimple(false);
                }
                declaration.setFlag(8192L);
            }
            if (declaration1.field == null || !declaration1.field.getName().equals("$instance"))
            {
                break label0;
            }
            field = declaration1.field;
        }
        continue; /* Loop/switch isn't completed */
        Declaration declaration6;
        Declaration declaration7;
        SetExp setexp;
        boolean flag3;
        ReferenceExp referenceexp1;
        if (declaration1.field != null && declaration1.field.getName().endsWith("$instance"))
        {
            flag3 = true;
        } else
        {
            flag3 = false;
        }
        declaration6 = scopeexp.lookup(symbol, language, language.getNamespaceOf(declaration1));
        if (!flag3)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (declaration6 != null)
        {
            continue; /* Loop/switch isn't completed */
        }
        declaration7 = scopeexp.addDeclaration(symbol);
        declaration7.setFlag(0x40004000L);
        declaration7.setType(declaration1.getType());
        declaration7.setFlag(8192L);
_L9:
        declaration7.setLocation(compilation);
        referenceexp1 = new ReferenceExp(declaration1);
        referenceexp1.setContextDecl(declaration);
        if (!flag3)
        {
            referenceexp1.setDontDereference(true);
            if (!flag)
            {
                declaration7.setPrivate(true);
            }
        }
        declaration7.setFlag(16384L);
        if (declaration1.getFlag(32768L))
        {
            declaration7.setFlag(32768L);
        }
        if (declaration1.isProcedureDecl())
        {
            declaration7.setProcedureDecl(true);
        }
        if (flag2)
        {
            declaration7.setFlag(2048L);
        }
        setexp = new SetExp(declaration7, referenceexp1);
        declaration7.setFlag(0x20000000L);
        setexp.setDefining(true);
        if (flag3)
        {
            vector.insertElementAt(setexp, i);
            i++;
        } else
        {
            vector.addElement(setexp);
        }
        vector1.add(declaration7);
        vector1.add(declaration1);
        declaration7.noteValue(referenceexp1);
        declaration7.setFlag(0x20000L);
        compilation.push(declaration7);
        if (true) goto _L6; else goto _L5
_L5:
        if (declaration6 != null && !declaration6.getFlag(512L) && Declaration.followAliases(declaration6) == Declaration.followAliases(declaration1)) goto _L8; else goto _L7
_L7:
        break MISSING_BLOCK_LABEL_1038;
_L8:
        break; /* Loop/switch isn't completed */
        if (declaration6 != null && declaration6.getFlag(0x10200L))
        {
            declaration6.setFlag(false, 0x10200L);
            declaration7 = declaration6;
        } else
        {
            declaration7 = scopeexp.addDeclaration(symbol);
            if (declaration6 != null)
            {
                ScopeExp.duplicateDeclarationError(declaration6, declaration7, compilation);
            }
        }
        declaration7.setAlias(true);
        declaration7.setIndirectBinding(true);
          goto _L9
        int j = vector1.size();
        for (int k = 0; k < j; k += 2)
        {
            Declaration declaration2 = (Declaration)vector1.elementAt(k);
            Declaration declaration3 = (Declaration)vector1.elementAt(k + 1);
            Expression expression = declaration3.getValue();
            if (!declaration3.isIndirectBinding() || !(expression instanceof ReferenceExp))
            {
                continue;
            }
            ReferenceExp referenceexp = (ReferenceExp)declaration2.getValue();
            Declaration declaration4 = ((ReferenceExp)expression).getBinding();
            referenceexp.setBinding(declaration4);
            if (declaration4.needsContext())
            {
                Declaration declaration5 = scopeexp.lookup(SimpleSymbol.valueOf((new StringBuilder()).append(declaration4.field.getDeclaringClass().getName().replace('.', '$')).append("$instance").toString()));
                declaration5.setFlag(1024L);
                referenceexp.setContextDecl(declaration5);
            }
        }

        if (!flag1) goto _L11; else goto _L10
_L10:
        gnu.bytecode.Method method = Compilation.typeRunnable.getDeclaredMethod("run", 0);
        if (declaration == null) goto _L13; else goto _L12
_L12:
        obj = new ReferenceExp(declaration);
_L15:
        ApplyExp applyexp = new ApplyExp(method, new Expression[] {
            obj
        });
        applyexp.setLine(compilation);
        vector.addElement(applyexp);
_L11:
        return true;
_L13:
        if (field != null)
        {
            Expression aexpression1[] = new Expression[2];
            QuoteExp quoteexp1 = new QuoteExp(classtype);
            aexpression1[0] = quoteexp1;
            aexpression1[1] = new QuoteExp("$instance");
            obj = new ApplyExp(SlotGet.staticField, aexpression1);
        }
        if (true) goto _L15; else goto _L14
_L14:
    }

    public static ModuleInfo lookupModuleFromSourcePath(String s, ScopeExp scopeexp)
    {
        ModuleManager modulemanager = ModuleManager.getInstance();
        String s1 = scopeexp.getFileName();
        if (s1 != null)
        {
            s = Path.valueOf(s1).resolve(s).toString();
        }
        return modulemanager.findWithSourcePath(s);
    }

    static void map(String s, String s1)
    {
        featureMap.put(s, s1);
    }

    public static String mapFeature(String s)
    {
        return (String)featureMap.get(s);
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        return null;
    }

    public boolean scanForDefinitions(Pair pair, Vector vector, ScopeExp scopeexp, Translator translator)
    {
        Pair pair1;
        Object obj;
        if (translator.getState() == 1)
        {
            translator.setState(2);
            translator.pendingForm = pair;
            return true;
        }
        pair1 = (Pair)pair.getCdr();
        obj = pair1.getCar();
        if (!(obj instanceof Pair)) goto _L2; else goto _L1
_L1:
        Pair pair2 = (Pair)obj;
        if (!translator.matches(pair2.getCar(), "quote")) goto _L2; else goto _L3
_L3:
        Object obj1;
        Pair pair3;
label0:
        {
            Object obj3 = pair2.getCdr();
            if (obj3 instanceof Pair)
            {
                pair3 = (Pair)obj3;
                if (pair3.getCdr() == LList.Empty && (pair3.getCar() instanceof Symbol))
                {
                    break label0;
                }
            }
            translator.error('e', "invalid quoted symbol for 'require'");
            return false;
        }
        String s2 = mapFeature(pair3.getCar().toString());
        if (s2 == null)
        {
            translator.error('e', (new StringBuilder()).append("unknown feature name '").append(pair3.getCar()).append("' for 'require'").toString());
            return false;
        }
        obj1 = ClassType.make((String)s2);
_L5:
        if (!(obj1 instanceof ClassType))
        {
            translator.error('e', "invalid specifier for 'require'");
            return false;
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (obj instanceof CharSequence)
        {
            String s1 = obj.toString();
            ModuleInfo moduleinfo2 = lookupModuleFromSourcePath(s1, scopeexp);
            if (moduleinfo2 == null)
            {
                translator.error('e', (new StringBuilder()).append("malformed URL: ").append(s1).toString());
                return false;
            } else
            {
                return importDefinitions(null, moduleinfo2, null, vector, scopeexp, translator);
            }
        }
        boolean flag = obj instanceof Symbol;
        obj1 = null;
        if (flag)
        {
            boolean flag1 = translator.selfEvaluatingSymbol(obj);
            obj1 = null;
            if (!flag1)
            {
                obj1 = translator.getLanguage().getTypeFor(translator.rewrite(obj, false));
                if ((obj1 instanceof ClassType) && (pair1.getCdr() instanceof Pair))
                {
                    Object obj2 = ((Pair)pair1.getCdr()).getCar();
                    if (obj2 instanceof CharSequence)
                    {
                        String s = obj2.toString();
                        ModuleInfo moduleinfo = lookupModuleFromSourcePath(s, scopeexp);
                        if (moduleinfo == null)
                        {
                            translator.error('e', (new StringBuilder()).append("malformed URL: ").append(s).toString());
                            return false;
                        } else
                        {
                            return importDefinitions(((Type) (obj1)).getName(), moduleinfo, null, vector, scopeexp, translator);
                        }
                    }
                }
            }
        }
        if (true) goto _L5; else goto _L4
_L4:
        ModuleInfo moduleinfo1;
        try
        {
            moduleinfo1 = ModuleInfo.find((ClassType)obj1);
        }
        catch (Exception exception)
        {
            translator.error('e', (new StringBuilder()).append("unknown class ").append(((Type) (obj1)).getName()).toString());
            return false;
        }
        importDefinitions(null, moduleinfo1, null, vector, scopeexp, translator);
        return true;
    }

    static 
    {
        require = new require();
        require.setName("require");
        map("generic-write", "gnu.kawa.slib.genwrite");
        map("pretty-print", "gnu.kawa.slib.pp");
        map("pprint-file", "gnu.kawa.slib.ppfile");
        map("printf", "gnu.kawa.slib.printf");
        map("xml", "gnu.kawa.slib.XML");
        map("readtable", "gnu.kawa.slib.readtable");
        map("srfi-10", "gnu.kawa.slib.readtable");
        map("http", "gnu.kawa.servlet.HTTP");
        map("servlets", "gnu.kawa.servlet.servlets");
        map("srfi-1", "gnu.kawa.slib.srfi1");
        map("list-lib", "gnu.kawa.slib.srfi1");
        map("srfi-2", "gnu.kawa.slib.srfi2");
        map("and-let*", "gnu.kawa.slib.srfi2");
        map("srfi-13", "gnu.kawa.slib.srfi13");
        map("string-lib", "gnu.kawa.slib.srfi13");
        map("srfi-34", "gnu.kawa.slib.srfi34");
        map("srfi-35", "gnu.kawa.slib.conditions");
        map("condition", "gnu.kawa.slib.conditions");
        map("conditions", "gnu.kawa.slib.conditions");
        map("srfi-37", "gnu.kawa.slib.srfi37");
        map("args-fold", "gnu.kawa.slib.srfi37");
        map("srfi-64", "gnu.kawa.slib.testing");
        map("testing", "gnu.kawa.slib.testing");
        map("srfi-69", "gnu.kawa.slib.srfi69");
        map("hash-table", "gnu.kawa.slib.srfi69");
        map("basic-hash-tables", "gnu.kawa.slib.srfi69");
        map("srfi-95", "kawa.lib.srfi95");
        map("sorting-and-merging", "kawa.lib.srfi95");
        map("regex", "kawa.lib.kawa.regex");
        map("pregexp", "gnu.kawa.slib.pregexp");
        map("gui", "gnu.kawa.slib.gui");
        map("swing-gui", "gnu.kawa.slib.swing");
        map("android-defs", "gnu.kawa.android.defs");
        map("syntax-utils", "gnu.kawa.slib.syntaxutils");
    }
}
