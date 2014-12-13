// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.standard;

import gnu.bytecode.Type;
import gnu.expr.BeginExp;
import gnu.expr.ClassExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.ObjectExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.SetExp;
import gnu.expr.ThisExp;
import gnu.lists.FString;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import java.util.Vector;
import kawa.lang.Lambda;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

// Referenced classes of package kawa.standard:
//            SchemeCompilation

public class object extends Syntax
{

    public static final Keyword accessKeyword = Keyword.make("access");
    public static final Keyword allocationKeyword = Keyword.make("allocation");
    public static final Keyword classNameKeyword = Keyword.make("class-name");
    static final Symbol coloncolon;
    static final Keyword initKeyword = Keyword.make("init");
    static final Keyword init_formKeyword = Keyword.make("init-form");
    static final Keyword init_keywordKeyword = Keyword.make("init-keyword");
    static final Keyword init_valueKeyword = Keyword.make("init-value");
    static final Keyword initformKeyword = Keyword.make("initform");
    public static final Keyword interfaceKeyword = Keyword.make("interface");
    public static final object objectSyntax;
    public static final Keyword throwsKeyword = Keyword.make("throws");
    static final Keyword typeKeyword = Keyword.make("type");
    Lambda lambda;

    public object(Lambda lambda1)
    {
        lambda = lambda1;
    }

    static long addAccessFlags(Object obj, long l, long l1, String s, Translator translator)
    {
        long l2 = matchAccess(obj, translator);
        if (l2 != 0L) goto _L2; else goto _L1
_L1:
        translator.error('e', (new StringBuilder()).append("unknown access specifier ").append(obj).toString());
_L4:
        return l | l2;
_L2:
        if ((l2 & (-1L ^ l1)) != 0L)
        {
            translator.error('e', (new StringBuilder()).append("invalid ").append(s).append(" access specifier ").append(obj).toString());
        } else
        if ((l & l2) != 0L)
        {
            translator.error('w', (new StringBuilder()).append("duplicate ").append(s).append(" access specifiers ").append(obj).toString());
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    static long matchAccess(Object obj, Translator translator)
    {
        for (; obj instanceof SyntaxForm; obj = ((SyntaxForm)obj).getDatum()) { }
        if (obj instanceof Pair)
        {
            Pair _tmp = (Pair)obj;
            obj = translator.matchQuoted((Pair)obj);
            if (obj instanceof Pair)
            {
                return matchAccess2((Pair)obj, translator);
            }
        }
        return matchAccess1(obj, translator);
    }

    private static long matchAccess1(Object obj, Translator translator)
    {
        if (!(obj instanceof Keyword)) goto _L2; else goto _L1
_L1:
        obj = ((Keyword)obj).getName();
_L4:
        if ("private".equals(obj))
        {
            return 0x1000000L;
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (obj instanceof FString)
        {
            obj = ((FString)obj).toString();
        } else
        if (obj instanceof SimpleSymbol)
        {
            obj = obj.toString();
        }
        if (true) goto _L4; else goto _L3
_L3:
        if ("protected".equals(obj))
        {
            return 0x2000000L;
        }
        if ("public".equals(obj))
        {
            return 0x4000000L;
        }
        if ("package".equals(obj))
        {
            return 0x8000000L;
        }
        if ("volatile".equals(obj))
        {
            return 0x80000000L;
        }
        if ("transient".equals(obj))
        {
            return 0x100000000L;
        }
        if ("enum".equals(obj))
        {
            return 0x200000000L;
        }
        return !"final".equals(obj) ? 0L : 0x400000000L;
    }

    private static long matchAccess2(Pair pair, Translator translator)
    {
        long l = matchAccess1(pair.getCar(), translator);
        Object obj = pair.getCdr();
        if (obj == LList.Empty || l == 0L)
        {
            return l;
        }
        if (obj instanceof Pair)
        {
            long l1 = matchAccess2((Pair)obj, translator);
            if (l1 != 0L)
            {
                return l | l1;
            }
        }
        return 0L;
    }

    static boolean matches(Object obj, String s, Translator translator)
    {
        if (!(obj instanceof Keyword)) goto _L2; else goto _L1
_L1:
        String s1 = ((Keyword)obj).getName();
_L7:
        boolean flag1;
label0:
        {
            if (s != null)
            {
                boolean flag3 = s.equals(s1);
                flag1 = false;
                if (!flag3)
                {
                    break label0;
                }
            }
            flag1 = true;
        }
_L4:
        return flag1;
_L2:
        boolean flag;
        if (obj instanceof FString)
        {
            s1 = ((FString)obj).toString();
            continue; /* Loop/switch isn't completed */
        }
        flag = obj instanceof Pair;
        flag1 = false;
        if (!flag) goto _L4; else goto _L3
_L3:
        Object obj1;
        boolean flag2;
        obj1 = translator.matchQuoted((Pair)obj);
        flag2 = obj1 instanceof SimpleSymbol;
        flag1 = false;
        if (!flag2) goto _L4; else goto _L5
_L5:
        s1 = obj1.toString();
        if (true) goto _L7; else goto _L6
_L6:
    }

    private static void rewriteInit(Object obj, ClassExp classexp, Pair pair, Translator translator, SyntaxForm syntaxform)
    {
        boolean flag;
        LambdaExp lambdaexp;
        Expression expression;
        Object obj1;
        if (obj instanceof Declaration)
        {
            flag = ((Declaration)obj).getFlag(2048L);
        } else
        if (obj == Boolean.TRUE)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            lambdaexp = classexp.clinitMethod;
        } else
        {
            lambdaexp = classexp.initMethod;
        }
        if (lambdaexp == null)
        {
            lambdaexp = new LambdaExp(new BeginExp());
            lambdaexp.setClassMethod(true);
            lambdaexp.setReturnType(Type.voidType);
            LambdaExp lambdaexp1;
            Declaration declaration;
            SetExp setexp;
            if (flag)
            {
                lambdaexp.setName("$clinit$");
                classexp.clinitMethod = lambdaexp;
            } else
            {
                lambdaexp.setName("$finit$");
                classexp.initMethod = lambdaexp;
                lambdaexp.add(null, new Declaration(ThisExp.THIS_NAME));
            }
            lambdaexp.nextSibling = classexp.firstChild;
            classexp.firstChild = lambdaexp;
        }
        translator.push(lambdaexp);
        lambdaexp1 = translator.curMethodLambda;
        translator.curMethodLambda = lambdaexp;
        expression = translator.rewrite_car(pair, syntaxform);
        if (obj instanceof Declaration)
        {
            declaration = (Declaration)obj;
            setexp = new SetExp(declaration, expression);
            setexp.setLocation(declaration);
            declaration.noteValue(null);
            obj1 = setexp;
        } else
        {
            obj1 = Compilation.makeCoercion(expression, new QuoteExp(Type.voidType));
        }
        ((BeginExp)lambdaexp.body).add(((Expression) (obj1)));
        translator.curMethodLambda = lambdaexp1;
        translator.pop(lambdaexp);
    }

    public void rewriteClassDef(Object aobj[], Translator translator)
    {
        ClassExp classexp;
        Object obj;
        Vector vector;
        LambdaExp lambdaexp;
        Object obj2;
        Expression aexpression[];
        classexp = (ClassExp)aobj[0];
        obj = aobj[1];
        vector = (Vector)aobj[2];
        lambdaexp = (LambdaExp)aobj[3];
        Object obj1 = aobj[4];
        obj2 = aobj[5];
        classexp.firstChild = lambdaexp;
        int i = Translator.listLength(obj1);
        if (i < 0)
        {
            translator.error('e', "object superclass specification not a list");
            i = 0;
        }
        aexpression = new Expression[i];
        for (int j = 0; j < i; j++)
        {
            for (; obj1 instanceof SyntaxForm; obj1 = ((SyntaxForm)obj1).getDatum()) { }
            Pair pair6 = (Pair)obj1;
            aexpression[j] = translator.rewrite_car(pair6, false);
            if (aexpression[j] instanceof ReferenceExp)
            {
                Declaration declaration = Declaration.followAliases(((ReferenceExp)aexpression[j]).getBinding());
                if (declaration != null)
                {
                    Expression expression = declaration.getValue();
                    if (expression instanceof ClassExp)
                    {
                        ((ClassExp)expression).setFlag(0x20000);
                    }
                }
            }
            obj1 = pair6.getCdr();
        }

        if (obj2 == null) goto _L2; else goto _L1
_L1:
        Object obj18 = translator.rewrite_car((Pair)obj2, false).valueIfConstant();
        if (!(obj18 instanceof CharSequence)) goto _L4; else goto _L3
_L3:
        String s = obj18.toString();
        if (s.length() <= 0) goto _L4; else goto _L5
_L5:
        classexp.classNameSpecifier = s;
_L2:
        classexp.supers = aexpression;
        classexp.setTypes(translator);
        int k = vector.size();
        for (int l = 0; l < k; l += 2)
        {
            Object obj17 = vector.elementAt(l + 1);
            if (obj17 != null)
            {
                rewriteInit(vector.elementAt(l), classexp, (Pair)obj17, translator, null);
            }
        }

        break; /* Loop/switch isn't completed */
_L4:
        Object obj19 = translator.pushPositionOf(obj2);
        translator.error('e', "class-name specifier must be a non-empty string literal");
        translator.popPositionOf(obj19);
        if (true) goto _L2; else goto _L6
_L6:
        LambdaExp lambdaexp1;
        int i1;
        SyntaxForm syntaxform;
        Object obj3;
        translator.push(classexp);
        lambdaexp1 = lambdaexp;
        i1 = 0;
        syntaxform = null;
        obj3 = obj;
_L9:
        LList llist = LList.Empty;
        if (obj3 == llist) goto _L8; else goto _L7
_L7:
        Pair pair;
        Object obj4;
        Object obj5;
        SyntaxForm syntaxform1;
        for (; obj3 instanceof SyntaxForm; obj3 = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj3;
        }

        pair = (Pair)obj3;
        obj4 = translator.pushPositionOf(pair);
        obj5 = pair.getCar();
        syntaxform1 = syntaxform;
        for (; obj5 instanceof SyntaxForm; obj5 = syntaxform1.getDatum())
        {
            syntaxform1 = (SyntaxForm)obj5;
        }

        Object obj16;
        obj3 = pair.getCdr();
        if (!(obj5 instanceof Keyword) || !(obj3 instanceof Pair))
        {
            break MISSING_BLOCK_LABEL_498;
        }
        obj16 = ((Pair)obj3).getCdr();
        obj3 = obj16;
        translator.popPositionOf(obj4);
          goto _L9
        Pair pair1;
        Object obj6;
        pair1 = (Pair)obj5;
        obj6 = pair1.getCar();
        SyntaxForm syntaxform2 = syntaxform1;
        for (; obj6 instanceof SyntaxForm; obj6 = syntaxform2.getDatum())
        {
            syntaxform2 = (SyntaxForm)obj6;
        }

        if (!(obj6 instanceof String) && !(obj6 instanceof Symbol) && !(obj6 instanceof Keyword)) goto _L11; else goto _L10
_L10:
        if (!(obj6 instanceof Keyword)) goto _L13; else goto _L12
_L12:
        Object obj7 = pair1;
          goto _L14
_L25:
        if (obj7 == LList.Empty) goto _L16; else goto _L15
_L15:
        for (; obj7 instanceof SyntaxForm; obj7 = syntaxform1.getDatum())
        {
            syntaxform1 = (SyntaxForm)obj7;
        }

          goto _L17
_L13:
        obj7 = pair1.getCdr();
          goto _L14
_L17:
        Pair pair3;
        Object obj9;
        pair3 = (Pair)obj7;
        for (obj9 = pair3.getCar(); obj9 instanceof SyntaxForm; obj9 = ((SyntaxForm)obj9).getDatum()) { }
        Object obj10;
        Symbol symbol;
        obj10 = translator.pushPositionOf(pair3);
        obj7 = pair3.getCdr();
        symbol = coloncolon;
        if (obj9 == symbol) goto _L19; else goto _L18
_L18:
        if (!(obj9 instanceof Keyword)) goto _L20; else goto _L19
_L19:
        if (!(obj7 instanceof Pair)) goto _L20; else goto _L21
_L21:
        int j1;
        j1++;
        Pair pair4;
        Object obj11;
        Symbol symbol1;
        pair4 = (Pair)obj7;
        obj11 = pair4.getCar();
        obj7 = pair4.getCdr();
        symbol1 = coloncolon;
        if (obj9 == symbol1) goto _L23; else goto _L22
_L22:
        Keyword keyword = typeKeyword;
        if (obj9 != keyword) goto _L24; else goto _L23
_L30:
        translator.popPositionOf(obj10);
          goto _L25
        Exception exception;
        exception;
_L40:
        translator.popPositionOf(obj4);
        throw exception;
_L24:
        Keyword keyword1 = initKeyword;
        if (obj9 == keyword1) goto _L27; else goto _L26
_L26:
        Keyword keyword2 = initformKeyword;
        if (obj9 == keyword2) goto _L27; else goto _L28
_L28:
        Keyword keyword3 = init_formKeyword;
        if (obj9 == keyword3) goto _L27; else goto _L29
_L29:
        Keyword keyword4 = init_valueKeyword;
        if (obj9 != keyword4) goto _L30; else goto _L27
_L20:
        Pair pair2;
        SyntaxForm syntaxform3;
label0:
        {
            if (obj7 != LList.Empty || pair2 != null)
            {
                break label0;
            }
            pair2 = pair3;
            syntaxform3 = syntaxform1;
        }
          goto _L30
        Object obj8;
        if (!(obj7 instanceof Pair) || j1 != 0 || pair2 != null || obj8 != null) goto _L16; else goto _L31
_L31:
        Pair pair5 = (Pair)obj7;
        if (pair5.getCdr() != LList.Empty) goto _L16; else goto _L32
_L32:
        obj8 = obj9;
        pair2 = pair5;
        syntaxform3 = syntaxform1;
        Object obj13 = pair5.getCdr();
        obj7 = obj13;
          goto _L30
_L16:
        if (pair2 == null) goto _L34; else goto _L33
_L33:
        int k1 = i1 + 1;
        Object obj12 = vector.elementAt(i1);
        if (!(obj12 instanceof Declaration)) goto _L36; else goto _L35
_L35:
        ((Declaration)obj12).getFlag(2048L);
_L37:
        i1 = k1 + 1;
        if (vector.elementAt(k1) == null)
        {
            rewriteInit(obj12, classexp, pair2, translator, syntaxform3);
        }
_L34:
        translator.popPositionOf(obj4);
          goto _L9
_L36:
        Boolean boolean1 = Boolean.TRUE;
        if (obj12 != boolean1);
          goto _L37
_L11:
        if (!(obj6 instanceof Pair)) goto _L39; else goto _L38
_L38:
        gnu.expr.ScopeExp scopeexp = translator.currentScope();
        if (syntaxform1 == null)
        {
            break MISSING_BLOCK_LABEL_1044;
        }
        translator.setCurrentScope(syntaxform1.getScope());
        LambdaExp lambdaexp2;
        Lambda lambda1;
        Object obj14;
        Object obj15;
        if ("*init*".equals(lambdaexp1.getName()))
        {
            lambdaexp1.setReturnType(Type.voidType);
        }
        Translator.setLine(lambdaexp1, pair1);
        lambdaexp2 = translator.curMethodLambda;
        translator.curMethodLambda = lambdaexp1;
        lambda1 = lambda;
        obj14 = ((Pair)obj6).getCdr();
        obj15 = pair1.getCdr();
        if (syntaxform2 == null)
        {
            break MISSING_BLOCK_LABEL_1275;
        }
        if (syntaxform1 == null)
        {
            break MISSING_BLOCK_LABEL_1135;
        }
        if (syntaxform2.getScope() == syntaxform1.getScope())
        {
            break MISSING_BLOCK_LABEL_1275;
        }
        kawa.lang.TemplateScope templatescope = syntaxform2.getScope();
_L41:
        lambda1.rewrite(lambdaexp1, obj14, obj15, translator, templatescope);
        translator.curMethodLambda = lambdaexp2;
        if (syntaxform1 == null)
        {
            break MISSING_BLOCK_LABEL_1175;
        }
        translator.setCurrentScope(scopeexp);
        lambdaexp1 = lambdaexp1.nextSibling;
          goto _L34
_L39:
        translator.syntaxError("invalid field/method definition");
          goto _L34
_L8:
        if (classexp.initMethod != null)
        {
            classexp.initMethod.outer = classexp;
        }
        if (classexp.clinitMethod != null)
        {
            classexp.clinitMethod.outer = classexp;
        }
        translator.pop(classexp);
        classexp.declareParts(translator);
        return;
        exception;
          goto _L40
_L14:
        pair2 = null;
        syntaxform3 = null;
        j1 = 0;
        obj8 = null;
          goto _L25
_L23:
        obj8 = obj11;
          goto _L30
_L27:
        pair2 = pair4;
        syntaxform3 = syntaxform1;
          goto _L30
        templatescope = null;
          goto _L41
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Object obj;
        if (!(pair.getCdr() instanceof Pair))
        {
            obj = translator.syntaxError("missing superclass specification in object");
        } else
        {
            Pair pair1 = (Pair)pair.getCdr();
            obj = new ObjectExp();
            if (pair1.getCar() instanceof FString)
            {
                if (!(pair1.getCdr() instanceof Pair))
                {
                    return translator.syntaxError("missing superclass specification after object class name");
                }
                pair1 = (Pair)pair1.getCdr();
            }
            Object aobj[] = scanClassDef(pair1, ((ClassExp) (obj)), translator);
            if (aobj != null)
            {
                rewriteClassDef(aobj, translator);
                return ((Expression) (obj));
            }
        }
        return ((Expression) (obj));
    }

    public Object[] scanClassDef(Pair pair, ClassExp classexp, Translator translator)
    {
        Object obj;
        Object obj1;
        Object obj2;
        LambdaExp lambdaexp;
        LambdaExp lambdaexp1;
        long l;
        Vector vector;
        Object obj3;
        translator.mustCompileHere();
        obj = pair.getCar();
        obj1 = pair.getCdr();
        obj2 = null;
        lambdaexp = null;
        lambdaexp1 = null;
        l = 0L;
        vector = new Vector(20);
        obj3 = obj1;
_L10:
        LList llist = LList.Empty;
        if (obj3 == llist) goto _L2; else goto _L1
_L1:
        Object obj5;
        Pair pair2;
        Object obj6;
        for (; obj3 instanceof SyntaxForm; obj3 = ((SyntaxForm)obj3).getDatum()) { }
        if (!(obj3 instanceof Pair))
        {
            translator.error('e', "object member not a list");
            return null;
        }
        Pair pair1 = (Pair)obj3;
        Object obj4;
        for (obj4 = pair1.getCar(); obj4 instanceof SyntaxForm; obj4 = ((SyntaxForm)obj4).getDatum()) { }
        obj3 = pair1.getCdr();
        obj5 = translator.pushPositionOf(pair1);
        if (obj4 instanceof Keyword)
        {
            for (; obj3 instanceof SyntaxForm; obj3 = ((SyntaxForm)obj3).getDatum()) { }
            if (obj3 instanceof Pair)
            {
                Keyword keyword9 = interfaceKeyword;
                if (obj4 == keyword9)
                {
                    if (((Pair)obj3).getCar() == Boolean.FALSE)
                    {
                        classexp.setFlag(0x10000);
                    } else
                    {
                        classexp.setFlag(32768);
                    }
                    obj3 = ((Pair)obj3).getCdr();
                    translator.popPositionOf(obj5);
                    continue; /* Loop/switch isn't completed */
                }
                Keyword keyword10 = classNameKeyword;
                if (obj4 == keyword10)
                {
                    if (obj2 != null)
                    {
                        translator.error('e', "duplicate class-name specifiers");
                    }
                    obj2 = obj3;
                    obj3 = ((Pair)obj3).getCdr();
                    translator.popPositionOf(obj5);
                    continue; /* Loop/switch isn't completed */
                }
                Keyword keyword11 = accessKeyword;
                if (obj4 == keyword11)
                {
                    Object obj16 = translator.pushPositionOf(obj3);
                    l = addAccessFlags(((Pair)obj3).getCar(), l, 0x603000000L, "class", translator);
                    if (classexp.nameDecl == null)
                    {
                        translator.error('e', "access specifier for anonymous class");
                    }
                    translator.popPositionOf(obj16);
                    obj3 = ((Pair)obj3).getCdr();
                    translator.popPositionOf(obj5);
                    continue; /* Loop/switch isn't completed */
                }
            }
        }
        if (!(obj4 instanceof Pair))
        {
            translator.error('e', "object member not a list");
            return null;
        }
        pair2 = (Pair)obj4;
        for (obj6 = pair2.getCar(); obj6 instanceof SyntaxForm; obj6 = ((SyntaxForm)obj6).getDatum()) { }
        if (!(obj6 instanceof String) && !(obj6 instanceof Symbol) && !(obj6 instanceof Keyword)) goto _L4; else goto _L3
_L3:
        Pair pair3;
        Object obj7;
        int i;
        long l1;
        Declaration declaration;
        Object obj8;
        int j;
        boolean flag;
        Pair pair4;
        Object obj10;
        pair3 = null;
        obj7 = obj6;
        i = 0;
        l1 = 0L;
        if (obj7 instanceof Keyword)
        {
            declaration = null;
            obj8 = pair2;
        } else
        {
            declaration = classexp.addDeclaration(obj7);
            declaration.setSimple(false);
            declaration.setFlag(0x100000L);
            Translator.setLine(declaration, pair2);
            obj8 = pair2.getCdr();
        }
        j = 0;
        flag = false;
        pair4 = null;
_L6:
        Pair pair5;
        Object obj11;
        Object obj12;
        Pair pair7;
        Object obj13;
        if (obj8 == LList.Empty)
        {
            break MISSING_BLOCK_LABEL_1141;
        }
        for (obj10 = obj8; obj10 instanceof SyntaxForm; obj10 = ((SyntaxForm)obj10).getDatum()) { }
        pair5 = (Pair)obj10;
        for (obj11 = pair5.getCar(); obj11 instanceof SyntaxForm; obj11 = ((SyntaxForm)obj11).getDatum()) { }
        obj12 = translator.pushPositionOf(pair5);
        obj8 = pair5.getCdr();
        Symbol symbol = coloncolon;
        if (obj11 != symbol && !(obj11 instanceof Keyword) || !(obj8 instanceof Pair))
        {
            break MISSING_BLOCK_LABEL_1053;
        }
        j++;
        pair7 = (Pair)obj8;
        obj13 = pair7.getCar();
        obj8 = pair7.getCdr();
        Symbol symbol1 = coloncolon;
        if (obj11 != symbol1)
        {
            Keyword keyword = typeKeyword;
            if (obj11 != keyword)
            {
                break; /* Loop/switch isn't completed */
            }
        }
        pair3 = pair7;
_L7:
        translator.popPositionOf(obj12);
        if (true) goto _L6; else goto _L5
_L5:
        Keyword keyword1 = allocationKeyword;
        if (obj11 == keyword1)
        {
            if (i != 0)
            {
                translator.error('e', "duplicate allocation: specification");
            }
            if (matches(obj13, "class", translator) || matches(obj13, "static", translator))
            {
                i = 2048;
            } else
            if (matches(obj13, "instance", translator))
            {
                i = 4096;
            } else
            {
                translator.error('e', (new StringBuilder()).append("unknown allocation kind '").append(obj13).append("'").toString());
            }
        } else
        {
label0:
            {
                Keyword keyword2 = initKeyword;
                if (obj11 != keyword2)
                {
                    Keyword keyword4 = initformKeyword;
                    if (obj11 != keyword4)
                    {
                        Keyword keyword5 = init_formKeyword;
                        if (obj11 != keyword5)
                        {
                            Keyword keyword6 = init_valueKeyword;
                            if (obj11 != keyword6)
                            {
                                break label0;
                            }
                        }
                    }
                }
                if (flag)
                {
                    translator.error('e', "duplicate initialization");
                }
                flag = true;
                Keyword keyword3 = initKeyword;
                if (obj11 != keyword3)
                {
                    pair4 = pair7;
                }
            }
        }
          goto _L7
        Keyword keyword7 = init_keywordKeyword;
        if (obj11 == keyword7)
        {
            if (!(obj13 instanceof Keyword))
            {
                translator.error('e', "invalid 'init-keyword' - not a keyword");
            } else
            if (((Keyword)obj13).getName() != obj7.toString())
            {
                translator.error('w', "init-keyword option ignored");
            }
        } else
        {
            Keyword keyword8 = accessKeyword;
            if (obj11 == keyword8)
            {
                Object obj14 = translator.pushPositionOf(pair7);
                l1 = addAccessFlags(obj13, l1, 0x78f000000L, "field", translator);
                translator.popPositionOf(obj14);
            } else
            {
                translator.error('w', (new StringBuilder()).append("unknown slot keyword '").append(obj11).append("'").toString());
            }
        }
          goto _L7
        if (obj8 == LList.Empty && !flag)
        {
            pair4 = pair5;
            flag = true;
        } else
        {
label1:
            {
                if (!(obj8 instanceof Pair) || j != 0 || flag || pair3 != null)
                {
                    break label1;
                }
                Pair pair6 = (Pair)obj8;
                if (pair6.getCdr() != LList.Empty)
                {
                    break label1;
                }
                pair3 = pair5;
                pair4 = pair6;
                obj8 = pair6.getCdr();
                flag = true;
            }
        }
          goto _L7
        obj8 = null;
        if (obj8 != LList.Empty)
        {
            StringBuilder stringbuilder = (new StringBuilder()).append("invalid argument list for slot '").append(obj7).append('\'').append(" args:");
            String s;
            if (obj8 == null)
            {
                s = "null";
            } else
            {
                s = obj8.getClass().getName();
            }
            translator.error('e', stringbuilder.append(s).toString());
            return null;
        }
        if (flag)
        {
            boolean flag1;
            Object obj9;
            if (i == 2048)
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (declaration != null)
            {
                obj9 = declaration;
            } else
            if (flag1)
            {
                obj9 = Boolean.TRUE;
            } else
            {
                obj9 = Boolean.FALSE;
            }
            vector.addElement(obj9);
            vector.addElement(pair4);
        }
        if (declaration == null)
        {
            if (!flag)
            {
                translator.error('e', "missing field name");
                return null;
            }
        } else
        {
            if (pair3 != null)
            {
                Type type = translator.exp2Type(pair3);
                declaration.setType(type);
            }
            if (i != 0)
            {
                long l2 = i;
                declaration.setFlag(l2);
            }
            if (l1 != 0L)
            {
                declaration.setFlag(l1);
            }
            declaration.setCanRead(true);
            declaration.setCanWrite(true);
        }
_L8:
        translator.popPositionOf(obj5);
        continue; /* Loop/switch isn't completed */
_L4:
        if (obj6 instanceof Pair)
        {
            Pair pair8 = (Pair)obj6;
            Object obj15 = pair8.getCar();
            if (!(obj15 instanceof String) && !(obj15 instanceof Symbol))
            {
                translator.error('e', "missing method name");
                return null;
            }
            LambdaExp lambdaexp2 = new LambdaExp();
            Translator.setLine(classexp.addMethod(lambdaexp2, obj15), pair8);
            if (lambdaexp1 == null)
            {
                lambdaexp = lambdaexp2;
            } else
            {
                lambdaexp1.nextSibling = lambdaexp2;
            }
            lambdaexp1 = lambdaexp2;
        } else
        {
            translator.error('e', "invalid field/method definition");
        }
        if (true) goto _L8; else goto _L2
_L2:
        if (l != 0L)
        {
            classexp.nameDecl.setFlag(l);
        }
        return (new Object[] {
            classexp, obj1, vector, lambdaexp, obj, obj2
        });
        if (true) goto _L10; else goto _L9
_L9:
    }

    static 
    {
        objectSyntax = new object(SchemeCompilation.lambda);
        objectSyntax.setName("object");
        coloncolon = Namespace.EmptyNamespace.getSymbol("::");
    }
}
