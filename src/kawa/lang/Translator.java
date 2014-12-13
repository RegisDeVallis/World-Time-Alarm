// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.bytecode.ArrayClassLoader;
import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.bytecode.ZipLoader;
import gnu.expr.ApplyExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.LangExp;
import gnu.expr.Language;
import gnu.expr.LetExp;
import gnu.expr.ModuleExp;
import gnu.expr.ModuleInfo;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.Special;
import gnu.expr.ThisExp;
import gnu.kawa.functions.AppendValues;
import gnu.kawa.functions.CompileNamedPart;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.kawa.reflect.ClassMethods;
import gnu.kawa.reflect.FieldLocation;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.kawa.xml.MakeAttribute;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Location;
import gnu.mapping.Namespace;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;
import gnu.xml.NamespaceBinding;
import java.util.Stack;
import java.util.Vector;
import kawa.standard.begin;
import kawa.standard.object;
import kawa.standard.require;

// Referenced classes of package kawa.lang:
//            SyntaxForm, SyntaxForms, Syntax, TemplateScope, 
//            PatternScope, Quote, AutoloadProcedure, Macro

public class Translator extends Compilation
{

    private static Expression errorExp = new ErrorExp("unknown syntax error");
    public static final Declaration getNamedPartDecl;
    public LambdaExp curMethodLambda;
    public Macro currentMacroDefinition;
    Syntax currentSyntax;
    private Environment env;
    public int firstForm;
    public Stack formStack;
    Declaration macroContext;
    public Declaration matchArray;
    Vector notedAccess;
    public PatternScope patternScope;
    public Object pendingForm;
    PairWithPosition positionPair;
    Stack renamedAliasStack;
    public Declaration templateScopeDecl;
    public NamespaceBinding xmlElementNamespaces;

    public Translator(Language language, SourceMessages sourcemessages, NameLookup namelookup)
    {
        super(language, sourcemessages, namelookup);
        formStack = new Stack();
        xmlElementNamespaces = NamespaceBinding.predefinedXML;
        env = Environment.getCurrent();
    }

    static ReferenceExp getOriginalRef(Declaration declaration)
    {
        if (declaration != null && declaration.isAlias() && !declaration.isIndirectBinding())
        {
            Expression expression = declaration.getValue();
            if (expression instanceof ReferenceExp)
            {
                return (ReferenceExp)expression;
            }
        }
        return null;
    }

    public static int listLength(Object obj)
    {
        int i = 0;
        Object obj1 = obj;
        Object obj2;
        do
        {
            for (obj2 = obj; obj2 instanceof SyntaxForm; obj2 = ((SyntaxForm)obj2).getDatum()) { }
            for (; obj1 instanceof SyntaxForm; obj1 = ((SyntaxForm)obj1).getDatum()) { }
            if (obj2 == LList.Empty)
            {
                return i;
            }
            if (!(obj2 instanceof Pair))
            {
                return -1 - i;
            }
            int j = i + 1;
            Object obj3;
            for (obj3 = ((Pair)obj2).getCdr(); obj3 instanceof SyntaxForm; obj3 = ((SyntaxForm)obj3).getDatum()) { }
            if (obj3 == LList.Empty)
            {
                return j;
            }
            if (!(obj3 instanceof Pair))
            {
                return -1 - j;
            }
            obj1 = ((Pair)obj1).getCdr();
            obj2 = ((Pair)obj3).getCdr();
            i = j + 1;
        } while (obj2 != obj1);
        return 0x80000000;
    }

    private Expression makeBody(int i, ScopeExp scopeexp)
    {
        int j = formStack.size() - i;
        if (j == 0)
        {
            return QuoteExp.voidExp;
        }
        if (j == 1)
        {
            return (Expression)formStack.pop();
        }
        Expression aexpression[] = new Expression[j];
        for (int k = 0; k < j; k++)
        {
            aexpression[k] = (Expression)formStack.elementAt(i + k);
        }

        formStack.setSize(i);
        if (scopeexp instanceof ModuleExp)
        {
            return new ApplyExp(AppendValues.appendValues, aexpression);
        } else
        {
            return ((LispLanguage)getLanguage()).makeBody(aexpression);
        }
    }

    public static Pair makePair(Pair pair, Object obj, Object obj1)
    {
        if (pair instanceof PairWithPosition)
        {
            return new PairWithPosition((PairWithPosition)pair, obj, obj1);
        } else
        {
            return new Pair(obj, obj1);
        }
    }

    private void rewriteBody(LList llist)
    {
_L2:
        Pair pair;
        Object obj;
        if (llist == LList.Empty)
        {
            break; /* Loop/switch isn't completed */
        }
        pair = (Pair)llist;
        obj = pushPositionOf(pair);
        rewriteInBody(pair.getCar());
        popPositionOf(obj);
        llist = (LList)pair.getCdr();
        if (true) goto _L2; else goto _L1
        Exception exception;
        exception;
        popPositionOf(obj);
        throw exception;
_L1:
    }

    public static Object safeCar(Object obj)
    {
        for (; obj instanceof SyntaxForm; obj = ((SyntaxForm)obj).getDatum()) { }
        if (!(obj instanceof Pair))
        {
            return null;
        } else
        {
            return stripSyntax(((Pair)obj).getCar());
        }
    }

    public static Object safeCdr(Object obj)
    {
        for (; obj instanceof SyntaxForm; obj = ((SyntaxForm)obj).getDatum()) { }
        if (!(obj instanceof Pair))
        {
            return null;
        } else
        {
            return stripSyntax(((Pair)obj).getCdr());
        }
    }

    public static void setLine(Declaration declaration, Object obj)
    {
        if (obj instanceof SourceLocator)
        {
            declaration.setLocation((SourceLocator)obj);
        }
    }

    public static void setLine(Expression expression, Object obj)
    {
        if (obj instanceof SourceLocator)
        {
            expression.setLocation((SourceLocator)obj);
        }
    }

    public static Object stripSyntax(Object obj)
    {
        for (; obj instanceof SyntaxForm; obj = ((SyntaxForm)obj).getDatum()) { }
        return obj;
    }

    static void vectorReverse(Vector vector, int i, int j)
    {
        int k = j / 2;
        int l = -1 + (i + j);
        for (int i1 = 0; i1 < k; i1++)
        {
            Object obj = vector.elementAt(i + i1);
            vector.setElementAt(vector.elementAt(l - i1), i + i1);
            vector.setElementAt(obj, l - i1);
        }

    }

    public static Object wrapSyntax(Object obj, SyntaxForm syntaxform)
    {
        if (syntaxform == null || (obj instanceof Expression))
        {
            return obj;
        } else
        {
            return SyntaxForms.fromDatumIfNeeded(obj, syntaxform);
        }
    }

    Expression apply_rewrite(Syntax syntax, Pair pair)
    {
        Syntax syntax1;
        errorExp;
        syntax1 = currentSyntax;
        currentSyntax = syntax;
        Expression expression = syntax.rewriteForm(pair, this);
        currentSyntax = syntax1;
        return expression;
        Exception exception;
        exception;
        currentSyntax = syntax1;
        throw exception;
    }

    Syntax check_if_Syntax(Declaration declaration)
    {
        Expression expression;
        Declaration declaration1 = Declaration.followAliases(declaration);
        expression = declaration1.getValue();
        if (expression == null || !declaration1.getFlag(32768L))
        {
            break MISSING_BLOCK_LABEL_181;
        }
        if (!(declaration.getValue() instanceof ReferenceExp)) goto _L2; else goto _L1
_L1:
        Declaration declaration2 = ((ReferenceExp)declaration.getValue()).contextDecl();
        if (declaration2 == null) goto _L4; else goto _L3
_L3:
        macroContext = declaration2;
_L6:
        Object obj1 = expression.eval(env);
        Object obj = obj1;
_L7:
        Throwable throwable;
        if (obj instanceof Syntax)
        {
            return (Syntax)obj;
        } else
        {
            return null;
        }
_L4:
        if (!(current_scope instanceof TemplateScope)) goto _L6; else goto _L5
_L5:
        macroContext = ((TemplateScope)current_scope).macroContext;
          goto _L6
        throwable;
        throwable.printStackTrace();
        error('e', (new StringBuilder()).append("unable to evaluate macro for ").append(declaration.getSymbol()).toString());
        obj = null;
          goto _L7
_L2:
        if (!(current_scope instanceof TemplateScope)) goto _L6; else goto _L8
_L8:
        macroContext = ((TemplateScope)current_scope).macroContext;
          goto _L6
        boolean flag = declaration.getFlag(32768L);
        obj = null;
        if (flag)
        {
            boolean flag1 = declaration.needsContext();
            obj = null;
            if (!flag1)
            {
                obj = StaticFieldLocation.make(declaration).get(null);
            }
        }
          goto _L7
    }

    public Declaration define(Object obj, SyntaxForm syntaxform, ScopeExp scopeexp)
    {
        boolean flag;
        Object obj1;
        Declaration declaration;
        if (syntaxform != null && syntaxform.getScope() != currentScope())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            obj1 = new String(obj.toString());
        } else
        {
            obj1 = obj;
        }
        declaration = scopeexp.getDefine(obj1, 'w', this);
        if (flag)
        {
            Declaration declaration1 = makeRenamedAlias(obj, declaration, syntaxform.getScope());
            syntaxform.getScope().addDeclaration(declaration1);
        }
        push(declaration);
        return declaration;
    }

    public Type exp2Type(Pair pair)
    {
        Object obj = pushPositionOf(pair);
        Expression expression;
        boolean flag;
        expression = InlineCalls.inlineCalls(rewrite_car(pair, false), this);
        flag = expression instanceof ErrorExp;
        if (flag)
        {
            popPositionOf(obj);
            return null;
        }
        Type type = getLanguage().getTypeFor(expression);
        Type type1 = type;
        if (type1 != null) goto _L2; else goto _L1
_L1:
        Object obj1 = expression.eval(env);
        if (!(obj1 instanceof Class)) goto _L4; else goto _L3
_L3:
        Type type2 = Type.make((Class)obj1);
        type1 = type2;
_L2:
        if (type1 != null) goto _L6; else goto _L5
_L5:
        if (!(expression instanceof ReferenceExp)) goto _L8; else goto _L7
_L7:
        error('e', (new StringBuilder()).append("unknown type name '").append(((ReferenceExp)expression).getName()).append('\'').toString());
_L9:
        ClassType classtype = Type.pointer_type;
        popPositionOf(obj);
        return classtype;
_L4:
        try
        {
            if (obj1 instanceof Type)
            {
                type1 = (Type)obj1;
            }
        }
        catch (Throwable throwable) { }
        finally
        {
            popPositionOf(obj);
            throw exception;
        }
        continue; /* Loop/switch isn't completed */
_L8:
        error('e', "invalid type spec (must be \"type\" or 'type or <type>)");
          goto _L9
_L6:
        popPositionOf(obj);
        return type1;
        if (true) goto _L2; else goto _L10
_L10:
    }

    public void finishModule(ModuleExp moduleexp)
    {
        boolean flag;
        Declaration declaration;
        flag = moduleexp.isStatic();
        declaration = moduleexp.firstDecl();
_L7:
        if (declaration == null) goto _L2; else goto _L1
_L1:
        if (declaration.getFlag(512L))
        {
            String s;
            if (declaration.getFlag(1024L))
            {
                s = "' exported but never defined";
            } else
            if (declaration.getFlag(2048L))
            {
                s = "' declared static but never defined";
            } else
            {
                s = "' declared but never defined";
            }
            error('e', declaration, "'", s);
        }
        if (moduleexp.getFlag(16384) || generateMain && !immediate)
        {
            if (declaration.getFlag(1024L))
            {
                if (declaration.isPrivate())
                {
                    if (declaration.getFlag(0x1000000L))
                    {
                        error('e', declaration, "'", "' is declared both private and exported");
                    }
                    declaration.setPrivate(false);
                }
            } else
            {
                declaration.setPrivate(true);
            }
        }
        if (!flag) goto _L4; else goto _L3
_L3:
        declaration.setFlag(2048L);
_L5:
        declaration = declaration.nextDecl();
        continue; /* Loop/switch isn't completed */
_L4:
        if (moduleexp.getFlag(0x10000) && !declaration.getFlag(2048L) || Compilation.moduleStatic < 0 || moduleexp.getFlag(0x20000))
        {
            declaration.setFlag(4096L);
        }
        if (true) goto _L5; else goto _L2
_L2:
        return;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public Syntax getCurrentSyntax()
    {
        return currentSyntax;
    }

    public final Environment getGlobalEnvironment()
    {
        return env;
    }

    public Declaration lookup(Object obj, int i)
    {
        Declaration declaration = lexical.lookup(obj, i);
        if (declaration != null && getLanguage().hasNamespace(declaration, i))
        {
            return declaration;
        } else
        {
            return currentModule().lookup(obj, getLanguage(), i);
        }
    }

    public Declaration lookupGlobal(Object obj)
    {
        return lookupGlobal(obj, -1);
    }

    public Declaration lookupGlobal(Object obj, int i)
    {
        ModuleExp moduleexp = currentModule();
        Declaration declaration = moduleexp.lookup(obj, getLanguage(), i);
        if (declaration == null)
        {
            declaration = moduleexp.getNoDefine(obj);
            declaration.setIndirectBinding(true);
        }
        return declaration;
    }

    public Declaration makeRenamedAlias(Declaration declaration, ScopeExp scopeexp)
    {
        if (scopeexp == null)
        {
            return declaration;
        } else
        {
            return makeRenamedAlias(declaration.getSymbol(), declaration, scopeexp);
        }
    }

    public Declaration makeRenamedAlias(Object obj, Declaration declaration, ScopeExp scopeexp)
    {
        Declaration declaration1 = new Declaration(obj);
        declaration1.setAlias(true);
        declaration1.setPrivate(true);
        declaration1.context = scopeexp;
        ReferenceExp referenceexp = new ReferenceExp(declaration);
        referenceexp.setDontDereference(true);
        declaration1.noteValue(referenceexp);
        return declaration1;
    }

    public Object matchQuoted(Pair pair)
    {
        if (matches(pair.getCar(), "quote") && (pair.getCdr() instanceof Pair))
        {
            Pair pair1 = (Pair)pair.getCdr();
            if (pair1.getCdr() == LList.Empty)
            {
                return pair1.getCar();
            }
        }
        return null;
    }

    public final boolean matches(Object obj, String s)
    {
        return matches(obj, null, s);
    }

    public boolean matches(Object obj, SyntaxForm syntaxform, Symbol symbol)
    {
        if (syntaxform == null);
        if (obj instanceof SyntaxForm)
        {
            obj = ((SyntaxForm)obj).getDatum();
        }
        if ((obj instanceof SimpleSymbol) && !selfEvaluatingSymbol(obj))
        {
            ReferenceExp referenceexp = getOriginalRef(lexical.lookup(obj, -1));
            if (referenceexp != null)
            {
                obj = referenceexp.getSymbol();
            }
        }
        return obj == symbol;
    }

    public boolean matches(Object obj, SyntaxForm syntaxform, String s)
    {
        if (syntaxform == null);
        if (obj instanceof SyntaxForm)
        {
            obj = ((SyntaxForm)obj).getDatum();
        }
        if ((obj instanceof SimpleSymbol) && !selfEvaluatingSymbol(obj))
        {
            ReferenceExp referenceexp = getOriginalRef(lexical.lookup(obj, -1));
            if (referenceexp != null)
            {
                obj = referenceexp.getSymbol();
            }
        }
        return (obj instanceof SimpleSymbol) && ((Symbol)obj).getLocalPart() == s;
    }

    public Symbol namespaceResolve(Expression expression, Expression expression1)
    {
        return namespaceResolve(namespaceResolvePrefix(expression), expression1);
    }

    public Symbol namespaceResolve(Namespace namespace, Expression expression)
    {
        if (namespace != null && (expression instanceof QuoteExp))
        {
            return namespace.getSymbol(((QuoteExp)expression).getValue().toString().intern());
        } else
        {
            return null;
        }
    }

    public Object namespaceResolve(Object obj)
    {
        if (!(obj instanceof SimpleSymbol) && (obj instanceof Pair))
        {
            Pair pair = (Pair)obj;
            if (safeCar(pair) == LispLanguage.lookup_sym && (pair.getCdr() instanceof Pair))
            {
                Pair pair1 = (Pair)pair.getCdr();
                if (pair1.getCdr() instanceof Pair)
                {
                    Expression expression = rewrite(pair1.getCar());
                    Expression expression1 = rewrite(((Pair)pair1.getCdr()).getCar());
                    Symbol symbol = namespaceResolve(expression, expression1);
                    if (symbol != null)
                    {
                        return symbol;
                    }
                    String s = CompileNamedPart.combineName(expression, expression1);
                    if (s != null)
                    {
                        return Namespace.EmptyNamespace.getSymbol(s);
                    }
                }
            }
        }
        return obj;
    }

    public Namespace namespaceResolvePrefix(Expression expression)
    {
        if (expression instanceof ReferenceExp)
        {
            ReferenceExp referenceexp = (ReferenceExp)expression;
            Declaration declaration = referenceexp.getBinding();
            Object obj1;
            if (declaration == null || declaration.getFlag(0x10000L))
            {
                Object obj = referenceexp.getSymbol();
                Symbol symbol;
                Namespace namespace;
                String s;
                if (obj instanceof Symbol)
                {
                    symbol = (Symbol)obj;
                } else
                {
                    symbol = env.getSymbol(obj.toString());
                }
                obj1 = env.get(symbol, null);
            } else
            if (declaration.isNamespaceDecl())
            {
                obj1 = declaration.getConstantValue();
            } else
            {
                obj1 = null;
            }
            if (obj1 instanceof Namespace)
            {
                namespace = (Namespace)obj1;
                s = namespace.getName();
                if (s != null && s.startsWith("class:"))
                {
                    namespace = null;
                }
                return namespace;
            }
        }
        return null;
    }

    public void noteAccess(Object obj, ScopeExp scopeexp)
    {
        if (notedAccess == null)
        {
            notedAccess = new Vector();
        }
        notedAccess.addElement(obj);
        notedAccess.addElement(scopeexp);
    }

    public Expression parse(Object obj)
    {
        return rewrite(obj);
    }

    public Object popForms(int i)
    {
        int j = formStack.size();
        if (j == i)
        {
            return Values.empty;
        }
        Object obj;
        if (j == i + 1)
        {
            obj = formStack.elementAt(i);
        } else
        {
            Values values = new Values();
            for (int k = i; k < j; k++)
            {
                values.writeObject(formStack.elementAt(k));
            }

            obj = values;
        }
        formStack.setSize(i);
        return obj;
    }

    public void popPositionOf(Object obj)
    {
        if (obj != null)
        {
            setLine(obj);
            positionPair = (PairWithPosition)obj;
            if (positionPair.getCar() == Special.eof)
            {
                positionPair = (PairWithPosition)positionPair.getCdr();
                return;
            }
        }
    }

    public void popRenamedAlias(int i)
    {
        do
        {
            if (--i < 0)
            {
                break;
            }
            ScopeExp scopeexp = (ScopeExp)renamedAliasStack.pop();
            Declaration declaration = (Declaration)renamedAliasStack.pop();
            getOriginalRef(declaration).getBinding().setSymbol(declaration.getSymbol());
            scopeexp.remove(declaration);
            Object obj = renamedAliasStack.pop();
            if (obj != null)
            {
                scopeexp.addDeclaration((Declaration)obj);
            }
        } while (true);
    }

    public void processAccesses()
    {
        if (notedAccess != null)
        {
            int i = notedAccess.size();
            ScopeExp scopeexp = current_scope;
            for (int j = 0; j < i; j += 2)
            {
                Object obj = notedAccess.elementAt(j);
                ScopeExp scopeexp1 = (ScopeExp)notedAccess.elementAt(j + 1);
                if (current_scope != scopeexp1)
                {
                    setCurrentScope(scopeexp1);
                }
                Declaration declaration = lexical.lookup(obj, -1);
                if (declaration != null && !declaration.getFlag(0x10000L))
                {
                    declaration.getContext().currentLambda().capture(declaration);
                    declaration.setCanRead(true);
                    declaration.setSimple(false);
                    declaration.setFlag(0x80000L);
                }
            }

            if (current_scope != scopeexp)
            {
                setCurrentScope(scopeexp);
                return;
            }
        }
    }

    public Object pushPositionOf(Object obj)
    {
        if (obj instanceof SyntaxForm)
        {
            obj = ((SyntaxForm)obj).getDatum();
        }
        if (!(obj instanceof PairWithPosition))
        {
            return null;
        }
        PairWithPosition pairwithposition = (PairWithPosition)obj;
        PairWithPosition pairwithposition1;
        if (positionPair == null || positionPair.getFileName() != getFileName() || positionPair.getLineNumber() != getLineNumber() || positionPair.getColumnNumber() != getColumnNumber())
        {
            pairwithposition1 = new PairWithPosition(this, Special.eof, positionPair);
        } else
        {
            pairwithposition1 = positionPair;
        }
        setLine(obj);
        positionPair = pairwithposition;
        return pairwithposition1;
    }

    public void pushRenamedAlias(Declaration declaration)
    {
        Declaration declaration1 = getOriginalRef(declaration).getBinding();
        ScopeExp scopeexp = declaration.context;
        declaration1.setSymbol(null);
        Declaration declaration2 = scopeexp.lookup(declaration1.getSymbol());
        if (declaration2 != null)
        {
            scopeexp.remove(declaration2);
        }
        scopeexp.addDeclaration(declaration);
        if (renamedAliasStack == null)
        {
            renamedAliasStack = new Stack();
        }
        renamedAliasStack.push(declaration2);
        renamedAliasStack.push(declaration);
        renamedAliasStack.push(scopeexp);
    }

    public void resolveModule(ModuleExp moduleexp)
    {
        Compilation compilation;
        int i;
        int j;
        if (pendingImports == null)
        {
            i = 0;
        } else
        {
            i = pendingImports.size();
        }
        j = 0;
        do
        {
            if (j >= i)
            {
                break;
            }
            Stack stack = pendingImports;
            int k = j + 1;
            ModuleInfo moduleinfo = (ModuleInfo)stack.elementAt(j);
            Stack stack1 = pendingImports;
            int l = k + 1;
            ScopeExp scopeexp = (ScopeExp)stack1.elementAt(k);
            Stack stack2 = pendingImports;
            int i1 = l + 1;
            Expression expression = (Expression)stack2.elementAt(l);
            Stack stack3 = pendingImports;
            j = i1 + 1;
            Integer integer = (Integer)stack3.elementAt(i1);
            if (moduleexp == scopeexp)
            {
                ReferenceExp referenceexp = new ReferenceExp((Object)null);
                referenceexp.setLine(this);
                setLine(expression);
                int j1 = formStack.size();
                require.importDefinitions(null, moduleinfo, null, formStack, scopeexp, this);
                int k1 = integer.intValue();
                if (integer.intValue() != j1)
                {
                    int l1 = formStack.size();
                    int i2 = l1 - k1;
                    vectorReverse(formStack, k1, j1 - k1);
                    vectorReverse(formStack, j1, l1 - j1);
                    vectorReverse(formStack, k1, i2);
                }
                setLine(referenceexp);
            }
        } while (true);
        pendingImports = null;
        processAccesses();
        setModule(moduleexp);
        compilation = Compilation.setSaveCurrent(this);
        rewriteInBody(popForms(firstForm));
        moduleexp.body = makeBody(firstForm, moduleexp);
        if (!immediate)
        {
            lexical.pop(moduleexp);
        }
        Compilation.restoreCurrent(compilation);
        return;
        Exception exception;
        exception;
        Compilation.restoreCurrent(compilation);
        throw exception;
    }

    public Expression rewrite(Object obj)
    {
        return rewrite(obj, false);
    }

    public Expression rewrite(Object obj, boolean flag)
    {
        SyntaxForm syntaxform;
        ScopeExp scopeexp2;
        if (!(obj instanceof SyntaxForm))
        {
            break MISSING_BLOCK_LABEL_64;
        }
        syntaxform = (SyntaxForm)obj;
        scopeexp2 = current_scope;
        Expression expression1;
        setCurrentScope(syntaxform.getScope());
        expression1 = rewrite(syntaxform.getDatum(), flag);
        setCurrentScope(scopeexp2);
        return expression1;
        Exception exception;
        exception;
        setCurrentScope(scopeexp2);
        throw exception;
        if (obj instanceof PairWithPosition)
        {
            return rewrite_with_position(obj, flag, (PairWithPosition)obj);
        }
        if (obj instanceof Pair)
        {
            return rewrite_pair((Pair)obj, flag);
        }
        if (!(obj instanceof Symbol) || selfEvaluatingSymbol(obj)) goto _L2; else goto _L1
_L1:
        Declaration declaration;
        Declaration declaration1;
        ScopeExp scopeexp;
        String s;
        Object obj1;
        Object obj2;
        Symbol symbol;
        boolean flag1;
        declaration = lexical.lookup(obj, flag);
        declaration1 = null;
        scopeexp = current_scope;
        int i;
        ReferenceExp referenceexp1;
        Object obj4;
        if (declaration == null)
        {
            i = -1;
        } else
        {
            i = ScopeExp.nesting(declaration.context);
        }
        if ((obj instanceof Symbol) && ((Symbol)obj).hasEmptyNamespace())
        {
            s = obj.toString();
        } else
        {
            s = null;
            scopeexp = null;
        }
_L19:
        if (scopeexp == null) goto _L4; else goto _L3
_L3:
        if (!(scopeexp instanceof LambdaExp) || !(scopeexp.outer instanceof ClassExp) || !((LambdaExp)scopeexp).isClassMethod()) goto _L6; else goto _L5
_L5:
        if (i < ScopeExp.nesting(scopeexp.outer)) goto _L7; else goto _L4
_L4:
        LambdaExp lambdaexp;
        ClassExp classexp;
        ClassType classtype;
        gnu.bytecode.Member member;
        boolean flag7;
        Declaration declaration3;
        Object obj5;
        QuoteExp quoteexp;
        Declaration declaration4;
        char c;
        Language language;
        if (declaration != null)
        {
            obj1 = declaration.getSymbol();
            referenceexp1 = getOriginalRef(declaration);
            obj4 = null;
            if (referenceexp1 != null)
            {
                declaration = referenceexp1.getBinding();
                obj4 = null;
                if (declaration == null)
                {
                    obj4 = referenceexp1.getSymbol();
                    obj1 = obj4;
                }
            }
            obj2 = obj4;
        } else
        {
            obj1 = obj;
            obj2 = obj;
        }
        symbol = (Symbol)obj2;
        flag1 = getLanguage().hasSeparateFunctionNamespace();
        if (declaration == null) goto _L9; else goto _L8
_L7:
        lambdaexp = (LambdaExp)scopeexp;
        classexp = (ClassExp)scopeexp.outer;
        classtype = classexp.getClassType();
        member = SlotGet.lookupMember(classtype, s, classtype);
        if (lambdaexp == classexp.clinitMethod || lambdaexp != classexp.initMethod && lambdaexp.nameDecl.isStatic())
        {
            flag7 = true;
        } else
        {
            flag7 = false;
        }
        if (member != null) goto _L11; else goto _L10
_L10:
        if (flag7)
        {
            c = 'S';
        } else
        {
            c = 'V';
        }
        language = this.language;
        if (ClassMethods.getMethods(classtype, s, c, classtype, language).length != 0) goto _L11; else goto _L6
_L6:
        scopeexp = scopeexp.outer;
        continue; /* Loop/switch isn't completed */
_L11:
        if (flag7)
        {
            declaration4 = ((ClassExp)lambdaexp.outer).nameDecl;
            obj5 = new ReferenceExp(declaration4);
        } else
        {
            declaration3 = lambdaexp.firstDecl();
            obj5 = new ThisExp(declaration3);
        }
        quoteexp = QuoteExp.getInstance(s);
        return CompileNamedPart.makeExp(((Expression) (obj5)), quoteexp);
_L8:
        if ((current_scope instanceof TemplateScope) && declaration.needsContext())
        {
            declaration1 = ((TemplateScope)current_scope).macroContext;
            break MISSING_BLOCK_LABEL_312;
        }
        boolean flag5 = declaration.getFlag(0x100000L);
        declaration1 = null;
        if (!flag5)
        {
            continue; /* Loop/switch isn't completed */
        }
        boolean flag6 = declaration.isStatic();
        declaration1 = null;
        if (flag6)
        {
            continue; /* Loop/switch isn't completed */
        }
        scopeexp1 = currentScope();
_L12:
label0:
        {
            if (scopeexp1 == null)
            {
                throw new Error((new StringBuilder()).append("internal error: missing ").append(declaration).toString());
            }
            if (scopeexp1.outer != declaration.context)
            {
                break label0;
            }
            declaration1 = scopeexp1.firstDecl();
        }
        continue; /* Loop/switch isn't completed */
        scopeexp1 = scopeexp1.outer;
        if (true) goto _L12; else goto _L9
_L9:
        Environment environment = env;
        Object obj3;
        Location location;
        Expression expression;
        ReferenceExp referenceexp;
        boolean flag2;
        FieldLocation fieldlocation;
        Throwable throwable;
        Class class1;
        ClassLoader classloader;
        boolean flag3;
        boolean flag4;
        Declaration declaration2;
        if (flag && flag1)
        {
            obj3 = EnvironmentKey.FUNCTION;
        } else
        {
            obj3 = null;
        }
        location = environment.lookup(symbol, obj3);
        if (location != null)
        {
            location = location.getBase();
        }
        if (!(location instanceof FieldLocation)) goto _L14; else goto _L13
_L13:
        fieldlocation = (FieldLocation)location;
        declaration = fieldlocation.getDeclaration();
        if (!inlineOk(null) && declaration != getNamedPartDecl && (!"objectSyntax".equals(fieldlocation.getMemberName()) || !"kawa.standard.object".equals(fieldlocation.getDeclaringClass().getName())))
        {
            break MISSING_BLOCK_LABEL_1142;
        }
        if (!immediate)
        {
            break MISSING_BLOCK_LABEL_833;
        }
        flag4 = declaration.isStatic();
        declaration1 = null;
        if (flag4)
        {
            continue; /* Loop/switch isn't completed */
        }
        declaration2 = new Declaration("(module-instance)");
        declaration2.setValue(new QuoteExp(fieldlocation.getInstance()));
        declaration1 = declaration2;
        continue; /* Loop/switch isn't completed */
        if (!declaration.isStatic())
        {
            break MISSING_BLOCK_LABEL_890;
        }
        class1 = fieldlocation.getRClass();
        if (class1 == null)
        {
            break MISSING_BLOCK_LABEL_882;
        }
        classloader = class1.getClassLoader();
        if (classloader instanceof ZipLoader)
        {
            break MISSING_BLOCK_LABEL_882;
        }
        flag3 = classloader instanceof ArrayClassLoader;
        declaration1 = null;
        if (!flag3)
        {
            continue; /* Loop/switch isn't completed */
        }
        declaration1 = null;
        declaration = null;
        continue; /* Loop/switch isn't completed */
        declaration1 = null;
        declaration = null;
        continue; /* Loop/switch isn't completed */
        throwable;
_L15:
        error('e', (new StringBuilder()).append("exception loading '").append(obj2).append("' - ").append(throwable.getMessage()).toString());
        declaration = null;
        continue; /* Loop/switch isn't completed */
_L14:
        if (location != null)
        {
            flag2 = location.isBound();
            declaration1 = null;
            if (flag2)
            {
                continue; /* Loop/switch isn't completed */
            }
        }
        expression = ((LispLanguage)getLanguage()).checkDefaultBinding(symbol, this);
        declaration1 = null;
        if (expression != null)
        {
            return expression;
        }
_L17:
        if (declaration != null)
        {
            ScopeExp scopeexp1;
            if (!flag && (declaration.getConstantValue() instanceof object))
            {
                return QuoteExp.getInstance(java/lang/Object);
            }
            if (declaration.getContext() instanceof PatternScope)
            {
                return syntaxError((new StringBuilder()).append("reference to pattern variable ").append(declaration.getName()).append(" outside syntax template").toString());
            }
        }
        referenceexp = new ReferenceExp(obj1, declaration);
        referenceexp.setContextDecl(declaration1);
        referenceexp.setLine(this);
        if (flag && flag1)
        {
            referenceexp.setFlag(8);
        }
        return referenceexp;
_L2:
        if (obj instanceof LangExp)
        {
            return rewrite(((LangExp)obj).getLangValue(), flag);
        }
        if (obj instanceof Expression)
        {
            return (Expression)obj;
        }
        if (obj == Special.abstractSpecial)
        {
            return QuoteExp.abstractExp;
        } else
        {
            return QuoteExp.getInstance(Quote.quote(obj, this), this);
        }
        throwable;
        declaration1 = declaration2;
          goto _L15
        declaration1 = null;
        declaration = null;
        if (true) goto _L17; else goto _L16
_L16:
        if (true) goto _L19; else goto _L18
_L18:
    }

    public void rewriteInBody(Object obj)
    {
        if (!(obj instanceof SyntaxForm)) goto _L2; else goto _L1
_L1:
        SyntaxForm syntaxform;
        ScopeExp scopeexp;
        syntaxform = (SyntaxForm)obj;
        scopeexp = current_scope;
        setCurrentScope(syntaxform.getScope());
        rewriteInBody(syntaxform.getDatum());
        setCurrentScope(scopeexp);
_L4:
        return;
        Exception exception;
        exception;
        setCurrentScope(scopeexp);
        throw exception;
_L2:
        if (obj instanceof Values)
        {
            Object aobj[] = ((Values)obj).getValues();
            int i = 0;
            while (i < aobj.length) 
            {
                rewriteInBody(aobj[i]);
                i++;
            }
        } else
        {
            formStack.add(rewrite(obj, false));
            return;
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public Expression rewrite_body(Object obj)
    {
        Object obj1;
        LetExp letexp;
        int i;
        obj1 = pushPositionOf(obj);
        letexp = new LetExp(null);
        i = formStack.size();
        letexp.outer = current_scope;
        current_scope = letexp;
        LList llist;
        int j;
        llist = scanBody(obj, letexp, true);
        if (llist.isEmpty())
        {
            formStack.add(syntaxError("body with no expressions"));
        }
        j = letexp.countNonDynamicDecls();
        if (j == 0)
        {
            break MISSING_BLOCK_LABEL_131;
        }
        Expression aexpression[] = new Expression[j];
        int k = j;
_L2:
        if (--k < 0)
        {
            break; /* Loop/switch isn't completed */
        }
        aexpression[k] = QuoteExp.undefined_exp;
        if (true) goto _L2; else goto _L1
        Exception exception;
        exception;
        pop(letexp);
        popPositionOf(obj1);
        throw exception;
_L1:
        letexp.inits = aexpression;
        Expression expression;
        rewriteBody(llist);
        expression = makeBody(i, null);
        setLineOf(expression);
        if (j == 0)
        {
            pop(letexp);
            popPositionOf(obj1);
            return expression;
        }
        letexp.body = expression;
        setLineOf(letexp);
        pop(letexp);
        popPositionOf(obj1);
        return letexp;
    }

    public final Expression rewrite_car(Pair pair, SyntaxForm syntaxform)
    {
        ScopeExp scopeexp;
        if (syntaxform == null || syntaxform.getScope() == current_scope || (pair.getCar() instanceof SyntaxForm))
        {
            return rewrite_car(pair, false);
        }
        scopeexp = current_scope;
        Expression expression;
        setCurrentScope(syntaxform.getScope());
        expression = rewrite_car(pair, false);
        setCurrentScope(scopeexp);
        return expression;
        Exception exception;
        exception;
        setCurrentScope(scopeexp);
        throw exception;
    }

    public final Expression rewrite_car(Pair pair, boolean flag)
    {
        Object obj = pair.getCar();
        if (pair instanceof PairWithPosition)
        {
            return rewrite_with_position(obj, flag, (PairWithPosition)pair);
        } else
        {
            return rewrite(obj, flag);
        }
    }

    public Expression rewrite_pair(Pair pair, boolean flag)
    {
        Expression expression = rewrite_car(pair, true);
        if (expression instanceof QuoteExp)
        {
            Object obj6 = expression.valueIfConstant();
            if (obj6 instanceof Syntax)
            {
                return apply_rewrite((Syntax)obj6, pair);
            }
        }
        Object obj;
        int i;
        if (expression instanceof ReferenceExp)
        {
            ReferenceExp referenceexp = (ReferenceExp)expression;
            Declaration declaration = referenceexp.getBinding();
            if (declaration == null)
            {
                Object obj3 = referenceexp.getSymbol();
                Symbol symbol1;
                Environment environment;
                Object obj4;
                Object obj5;
                if ((obj3 instanceof Symbol) && !selfEvaluatingSymbol(obj3))
                {
                    symbol1 = (Symbol)obj3;
                    symbol1.getName();
                } else
                {
                    String s = obj3.toString();
                    symbol1 = env.getSymbol(s);
                }
                environment = env;
                if (getLanguage().hasSeparateFunctionNamespace())
                {
                    obj4 = EnvironmentKey.FUNCTION;
                } else
                {
                    obj4 = null;
                }
                obj5 = environment.get(symbol1, obj4, null);
                if (obj5 instanceof Syntax)
                {
                    return apply_rewrite((Syntax)obj5, pair);
                }
                if (obj5 instanceof AutoloadProcedure)
                {
                    try
                    {
                        ((AutoloadProcedure)obj5).getLoaded();
                    }
                    catch (RuntimeException runtimeexception) { }
                }
            } else
            {
                Declaration declaration1 = macroContext;
                Syntax syntax = check_if_Syntax(declaration);
                if (syntax != null)
                {
                    Expression expression3 = apply_rewrite(syntax, pair);
                    macroContext = declaration1;
                    return expression3;
                }
            }
            referenceexp.setProcedureName(true);
            if (getLanguage().hasSeparateFunctionNamespace())
            {
                expression.setFlag(8);
            }
        }
        obj = pair.getCdr();
        i = listLength(obj);
        if (i == -1)
        {
            return syntaxError((new StringBuilder()).append("circular list is not allowed after ").append(pair.getCar()).toString());
        }
        if (i < 0)
        {
            return syntaxError((new StringBuilder()).append("dotted list [").append(obj).append("] is not allowed after ").append(pair.getCar()).toString());
        }
        Stack stack = new Stack();
        ScopeExp scopeexp = current_scope;
        int j = 0;
        while (j < i) 
        {
            if (obj instanceof SyntaxForm)
            {
                SyntaxForm syntaxform = (SyntaxForm)obj;
                obj = syntaxform.getDatum();
                setCurrentScope(syntaxform.getScope());
            }
            Pair pair1 = (Pair)obj;
            Object obj1 = rewrite_car(pair1, false);
            j++;
            if (false)
            {
                if ((j & 1) == 0)
                {
                    Expression aexpression1[] = new Expression[2];
                    aexpression1[0] = (Expression)stack.pop();
                    aexpression1[1] = ((Expression) (obj1));
                    obj1 = new ApplyExp(MakeAttribute.makeAttribute, aexpression1);
                } else
                if (obj1 instanceof QuoteExp)
                {
                    Object obj2 = ((QuoteExp)obj1).getValue();
                    if ((obj2 instanceof Keyword) && j < i)
                    {
                        obj1 = new QuoteExp(((Keyword)obj2).asSymbol());
                    }
                }
            }
            stack.addElement(obj1);
            obj = pair1.getCdr();
        }
        Expression aexpression[] = new Expression[stack.size()];
        stack.copyInto(aexpression);
        if (scopeexp != current_scope)
        {
            setCurrentScope(scopeexp);
        }
        if ((expression instanceof ReferenceExp) && ((ReferenceExp)expression).getBinding() == getNamedPartDecl)
        {
            Expression expression1 = aexpression[0];
            Expression expression2 = aexpression[1];
            Symbol symbol = namespaceResolve(expression1, expression2);
            if (symbol != null)
            {
                return rewrite(symbol, flag);
            } else
            {
                return CompileNamedPart.makeExp(expression1, expression2);
            }
        } else
        {
            return ((LispLanguage)getLanguage()).makeApply(expression, aexpression);
        }
    }

    public Expression rewrite_with_position(Object obj, boolean flag, PairWithPosition pairwithposition)
    {
        Object obj1 = pushPositionOf(pairwithposition);
        if (obj != pairwithposition) goto _L2; else goto _L1
_L1:
        Expression expression1 = rewrite_pair(pairwithposition, flag);
_L3:
        setLineOf(expression1);
        popPositionOf(obj1);
        return expression1;
_L2:
        Expression expression = rewrite(obj, flag);
        expression1 = expression;
          goto _L3
        Exception exception;
        exception;
        popPositionOf(obj1);
        throw exception;
    }

    public LList scanBody(Object obj, ScopeExp scopeexp, boolean flag)
    {
        Object obj1;
        Pair pair;
        SyntaxForm syntaxform;
        ScopeExp scopeexp1;
        int l;
        LList llist1;
        LList llist;
        if (flag)
        {
            obj1 = LList.Empty;
        } else
        {
            obj1 = null;
        }
        pair = null;
_L2:
        if (obj == LList.Empty)
        {
            break MISSING_BLOCK_LABEL_104;
        }
        if (!(obj instanceof SyntaxForm))
        {
            break MISSING_BLOCK_LABEL_167;
        }
        syntaxform = (SyntaxForm)obj;
        scopeexp1 = current_scope;
        setCurrentScope(syntaxform.getScope());
        l = formStack.size();
        llist = scanBody(syntaxform.getDatum(), scopeexp, flag);
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_129;
        }
        llist1 = (LList)SyntaxForms.fromDatumIfNeeded(llist, syntaxform);
        if (pair != null)
        {
            break MISSING_BLOCK_LABEL_113;
        }
        setCurrentScope(scopeexp1);
        obj1 = llist1;
        return ((LList) (obj1));
        pair.setCdrBackdoor(llist1);
        setCurrentScope(scopeexp1);
        return ((LList) (obj1));
        formStack.add(wrapSyntax(popForms(l), syntaxform));
        setCurrentScope(scopeexp1);
        return null;
        Exception exception;
        exception;
        setCurrentScope(scopeexp1);
        throw exception;
        if (obj instanceof Pair)
        {
            Pair pair1 = (Pair)obj;
            int i = formStack.size();
            scanForm(pair1.getCar(), scopeexp);
            if (getState() == 2)
            {
                if (pair1.getCar() != pendingForm)
                {
                    pair1 = makePair(pair1, pendingForm, pair1.getCdr());
                }
                pendingForm = new Pair(begin.begin, pair1);
                return LList.Empty;
            }
            int j = formStack.size();
            if (flag)
            {
                int k = i;
                while (k < j) 
                {
                    Pair pair2 = makePair(pair1, formStack.elementAt(k), LList.Empty);
                    if (pair == null)
                    {
                        obj1 = pair2;
                    } else
                    {
                        pair.setCdrBackdoor(pair2);
                    }
                    pair = pair2;
                    k++;
                }
                formStack.setSize(i);
            }
            obj = pair1.getCdr();
        } else
        {
            formStack.add(syntaxError("body is not a proper list"));
            return ((LList) (obj1));
        }
        if (true) goto _L2; else goto _L1
_L1:
    }

    public void scanForm(Object obj, ScopeExp scopeexp)
    {
        if (!(obj instanceof SyntaxForm)) goto _L2; else goto _L1
_L1:
        SyntaxForm syntaxform1;
        ScopeExp scopeexp2;
        syntaxform1 = (SyntaxForm)obj;
        scopeexp2 = currentScope();
        setCurrentScope(syntaxform1.getScope());
        int l = formStack.size();
        scanForm(syntaxform1.getDatum(), scopeexp);
        formStack.add(wrapSyntax(popForms(l), syntaxform1));
        setCurrentScope(scopeexp2);
_L24:
        return;
        Exception exception2;
        exception2;
        setCurrentScope(scopeexp2);
        throw exception2;
_L2:
        if (!(obj instanceof Values)) goto _L4; else goto _L3
_L3:
        Values values = Values.empty;
        if (obj != values) goto _L6; else goto _L5
_L5:
        obj = QuoteExp.voidExp;
_L4:
        Pair pair;
        Declaration declaration;
        ScopeExp scopeexp1;
        Object obj1;
        if (!(obj instanceof Pair))
        {
            break MISSING_BLOCK_LABEL_687;
        }
        pair = (Pair)obj;
        declaration = macroContext;
        scopeexp1 = current_scope;
        obj1 = pushPositionOf(obj);
        if ((obj instanceof SourceLocator) && scopeexp.getLineNumber() < 0)
        {
            scopeexp.setLocation((SourceLocator)obj);
        }
        Object obj2;
        boolean flag;
        obj2 = pair.getCar();
        if (obj2 instanceof SyntaxForm)
        {
            SyntaxForm syntaxform = (SyntaxForm)pair.getCar();
            setCurrentScope(syntaxform.getScope());
            obj2 = syntaxform.getDatum();
        }
        flag = obj2 instanceof Pair;
        Syntax syntax = null;
        if (!flag) goto _L8; else goto _L7
_L7:
        Pair pair1;
        Object obj3;
        Symbol symbol;
        pair1 = (Pair)obj2;
        obj3 = pair1.getCar();
        symbol = LispLanguage.lookup_sym;
        syntax = null;
        if (obj3 != symbol) goto _L8; else goto _L9
_L9:
        boolean flag1 = pair1.getCdr() instanceof Pair;
        syntax = null;
        if (!flag1) goto _L8; else goto _L10
_L10:
        Pair pair2;
        boolean flag2;
        pair2 = (Pair)pair1.getCdr();
        flag2 = pair2.getCdr() instanceof Pair;
        syntax = null;
        if (!flag2) goto _L8; else goto _L11
_L11:
        Expression expression;
        Expression expression1;
        Object obj4;
        Object obj5;
        expression = rewrite(pair2.getCar());
        expression1 = rewrite(((Pair)pair2.getCdr()).getCar());
        obj4 = expression.valueIfConstant();
        obj5 = expression1.valueIfConstant();
        if (!(obj4 instanceof Class)) goto _L13; else goto _L12
_L12:
        boolean flag3 = obj5 instanceof Symbol;
        if (!flag3) goto _L13; else goto _L14
_L14:
        boolean flag4;
        obj2 = GetNamedPart.getNamedPart(obj4, (Symbol)obj5);
        flag4 = obj2 instanceof Syntax;
        syntax = null;
        if (!flag4) goto _L8; else goto _L15
_L15:
        String s;
        int i;
        int j;
        Expression expression2;
        Declaration declaration1;
        Syntax syntax1;
        try
        {
            syntax = (Syntax)obj2;
        }
        catch (Throwable throwable)
        {
            obj2 = null;
            syntax = null;
        }
_L8:
        if (!(obj2 instanceof Symbol) || selfEvaluatingSymbol(obj2)) goto _L17; else goto _L16
_L16:
        expression2 = rewrite(obj2, true);
        if (!(expression2 instanceof ReferenceExp)) goto _L19; else goto _L18
_L18:
        declaration1 = ((ReferenceExp)expression2).getBinding();
        if (declaration1 == null) goto _L21; else goto _L20
_L20:
        syntax1 = check_if_Syntax(declaration1);
        syntax = syntax1;
_L19:
        if (scopeexp1 != current_scope)
        {
            setCurrentScope(scopeexp1);
        }
        popPositionOf(obj1);
        if (syntax == null)
        {
            break MISSING_BLOCK_LABEL_687;
        }
        s = getFileName();
        i = getLineNumber();
        j = getColumnNumber();
        setLine(pair);
        syntax.scanForm(pair, scopeexp, this);
        macroContext = declaration;
        setLine(s, i, j);
        return;
_L6:
        Object aobj[] = ((Values)obj).getValues();
        int k = 0;
        while (k < aobj.length) 
        {
            scanForm(aobj[k], scopeexp);
            k++;
        }
        continue; /* Loop/switch isn't completed */
_L13:
        obj2 = namespaceResolve(expression, expression1);
        syntax = null;
          goto _L8
_L21:
        Object obj6 = resolve(obj2, true);
        if (obj6 instanceof Syntax)
        {
            syntax = (Syntax)obj6;
        }
          goto _L19
_L17:
        if (obj2 != begin.begin) goto _L19; else goto _L22
_L22:
        syntax = (Syntax)obj2;
          goto _L19
        Exception exception;
        exception;
        if (scopeexp1 != current_scope)
        {
            setCurrentScope(scopeexp1);
        }
        popPositionOf(obj1);
        throw exception;
        Exception exception1;
        exception1;
        macroContext = declaration;
        setLine(s, i, j);
        throw exception1;
        formStack.add(obj);
        return;
        if (true) goto _L24; else goto _L23
_L23:
    }

    public final boolean selfEvaluatingSymbol(Object obj)
    {
        return ((LispLanguage)getLanguage()).selfEvaluatingSymbol(obj);
    }

    public void setLineOf(Expression expression)
    {
        if (expression instanceof QuoteExp)
        {
            return;
        } else
        {
            expression.setLocation(this);
            return;
        }
    }

    static 
    {
        getNamedPartDecl = Declaration.getDeclarationFromStatic("gnu.kawa.functions.GetNamedPart", "getNamedPart");
        LispLanguage.getNamedPartLocation.setDeclaration(getNamedPartDecl);
    }
}
