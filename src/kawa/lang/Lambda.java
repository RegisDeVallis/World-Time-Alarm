// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.bytecode.Type;
import gnu.expr.BeginExp;
import gnu.expr.Declaration;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.Keyword;
import gnu.expr.LambdaExp;
import gnu.expr.LangExp;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import gnu.expr.NameLookup;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.ScopeExp;
import gnu.expr.ThisExp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.text.SourceMessages;
import kawa.standard.object;

// Referenced classes of package kawa.lang:
//            Syntax, Translator, SyntaxForm, SyntaxForms, 
//            TemplateScope

public class Lambda extends Syntax
{

    public static final Keyword nameKeyword = Keyword.make("name");
    public Expression defaultDefault;
    public Object keyKeyword;
    public Object optionalKeyword;
    public Object restKeyword;

    public Lambda()
    {
        defaultDefault = QuoteExp.falseExp;
    }

    private static void addParam(Declaration declaration, ScopeExp scopeexp, LambdaExp lambdaexp, Translator translator)
    {
        if (scopeexp != null)
        {
            declaration = translator.makeRenamedAlias(declaration, scopeexp);
        }
        lambdaexp.addDeclaration(declaration);
        if (scopeexp != null)
        {
            declaration.context = scopeexp;
        }
    }

    public void print(Consumer consumer)
    {
        consumer.write("#<builtin lambda>");
    }

    public Expression rewrite(Object obj, Translator translator)
    {
        Object obj1;
        if (!(obj instanceof Pair))
        {
            obj1 = translator.syntaxError("missing formals in lambda");
        } else
        {
            int i = translator.getMessages().getErrorCount();
            obj1 = new LambdaExp();
            Pair pair = (Pair)obj;
            Translator.setLine(((Expression) (obj1)), pair);
            rewrite(((LambdaExp) (obj1)), pair.getCar(), pair.getCdr(), translator, null);
            if (translator.getMessages().getErrorCount() > i)
            {
                return new ErrorExp("bad lambda expression");
            }
        }
        return ((Expression) (obj1));
    }

    public void rewrite(LambdaExp lambdaexp, Object obj, Object obj1, Translator translator, TemplateScope templatescope)
    {
        rewriteFormals(lambdaexp, obj, translator, templatescope);
        if (obj1 instanceof PairWithPosition)
        {
            lambdaexp.setFile(((PairWithPosition)obj1).getFileName());
        }
        rewriteBody(lambdaexp, rewriteAttrs(lambdaexp, obj1, translator), translator);
    }

    public Object rewriteAttrs(LambdaExp lambdaexp, Object obj, Translator translator)
    {
        String s;
        String s1;
        int i;
        char c;
        SyntaxForm syntaxform;
        s = null;
        s1 = null;
        i = 0;
        c = '\0';
        syntaxform = null;
_L8:
        for (; obj instanceof SyntaxForm; obj = syntaxform.getDatum())
        {
            syntaxform = (SyntaxForm)obj;
        }

        if (obj instanceof Pair) goto _L2; else goto _L1
_L1:
        int j = i | c;
        if (j != 0)
        {
            lambdaexp.nameDecl.setFlag(j);
        }
        if (syntaxform != null)
        {
            obj = SyntaxForms.fromDatumIfNeeded(obj, syntaxform);
        }
        return obj;
_L2:
        Pair pair;
        Object obj1;
        pair = (Pair)obj;
        obj1 = Translator.stripSyntax(pair.getCar());
        if (!translator.matches(obj1, "::")) goto _L4; else goto _L3
_L3:
        obj1 = null;
_L5:
        SyntaxForm syntaxform1;
        Object obj2;
        syntaxform1 = syntaxform;
        for (obj2 = pair.getCdr(); obj2 instanceof SyntaxForm; obj2 = syntaxform1.getDatum())
        {
            syntaxform1 = (SyntaxForm)obj2;
        }

        continue; /* Loop/switch isn't completed */
_L4:
        if (obj1 instanceof Keyword) goto _L5; else goto _L1
        if (!(obj2 instanceof Pair)) goto _L1; else goto _L6
_L6:
        Pair pair1;
        pair1 = (Pair)obj2;
        if (obj1 != null)
        {
            break; /* Loop/switch isn't completed */
        }
        if (lambdaexp.isClassMethod() && "*init*".equals(lambdaexp.getName()))
        {
            translator.error('e', "explicit return type for '*init*' method");
        } else
        {
            lambdaexp.body = new LangExp(((Object) (new Object[] {
                pair1, syntaxform1
            })));
        }
_L9:
        obj = pair1.getCdr();
        if (true) goto _L8; else goto _L7
_L7:
        Object obj5;
label0:
        {
            if (obj1 != object.accessKeyword)
            {
                break MISSING_BLOCK_LABEL_462;
            }
            Expression expression2 = translator.rewrite_car(pair1, syntaxform1);
            if (expression2 instanceof QuoteExp)
            {
                obj5 = ((QuoteExp)expression2).getValue();
                if ((obj5 instanceof SimpleSymbol) || (obj5 instanceof CharSequence))
                {
                    break label0;
                }
            }
            translator.error('e', "access: value not a constant symbol or string");
        }
          goto _L9
        if (lambdaexp.nameDecl == null)
        {
            translator.error('e', "access: not allowed for anonymous function");
        } else
        {
            String s3 = obj5.toString();
            if ("private".equals(s3))
            {
                i = 0x1000000;
            } else
            if ("protected".equals(s3))
            {
                i = 0x2000000;
            } else
            if ("public".equals(s3))
            {
                i = 0x4000000;
            } else
            if ("package".equals(s3))
            {
                i = 0x8000000;
            } else
            {
                translator.error('e', "unknown access specifier");
            }
            if (s != null && s3 != null)
            {
                translator.error('e', (new StringBuilder()).append("duplicate access specifiers - ").append(s).append(" and ").append(s3).toString());
            }
            s = s3;
        }
          goto _L9
        Object obj4;
label1:
        {
            if (obj1 != object.allocationKeyword)
            {
                break MISSING_BLOCK_LABEL_657;
            }
            Expression expression1 = translator.rewrite_car(pair1, syntaxform1);
            if (expression1 instanceof QuoteExp)
            {
                obj4 = ((QuoteExp)expression1).getValue();
                if ((obj4 instanceof SimpleSymbol) || (obj4 instanceof CharSequence))
                {
                    break label1;
                }
            }
            translator.error('e', "allocation: value not a constant symbol or string");
        }
          goto _L9
        if (lambdaexp.nameDecl == null)
        {
            translator.error('e', "allocation: not allowed for anonymous function");
        } else
        {
            String s2 = obj4.toString();
            if ("class".equals(s2) || "static".equals(s2))
            {
                c = '\u0800';
            } else
            if ("instance".equals(s2))
            {
                c = '\u1000';
            } else
            {
                translator.error('e', "unknown allocation specifier");
            }
            if (s1 != null && s2 != null)
            {
                translator.error('e', (new StringBuilder()).append("duplicate allocation specifiers - ").append(s1).append(" and ").append(s2).toString());
            }
            s1 = s2;
        }
          goto _L9
        if (obj1 == object.throwsKeyword)
        {
            Object obj3 = pair1.getCar();
            int k = Translator.listLength(obj3);
            if (k < 0)
            {
                translator.error('e', "throws: not followed by a list");
            } else
            {
                Expression aexpression[] = new Expression[k];
                SyntaxForm syntaxform2 = syntaxform1;
                for (int l = 0; l < k; l++)
                {
                    for (; obj3 instanceof SyntaxForm; obj3 = syntaxform2.getDatum())
                    {
                        syntaxform2 = (SyntaxForm)obj3;
                    }

                    Pair pair2 = (Pair)obj3;
                    aexpression[l] = translator.rewrite_car(pair2, syntaxform2);
                    Translator.setLine(aexpression[l], pair2);
                    obj3 = pair2.getCdr();
                }

                lambdaexp.setExceptions(aexpression);
            }
        } else
        if (obj1 == nameKeyword)
        {
            Expression expression = translator.rewrite_car(pair1, syntaxform1);
            if (expression instanceof QuoteExp)
            {
                lambdaexp.setName(((QuoteExp)expression).getValue().toString());
            }
        } else
        {
            translator.error('w', (new StringBuilder()).append("unknown procedure property ").append(obj1).toString());
        }
          goto _L9
    }

    public void rewriteBody(LambdaExp lambdaexp, Object obj, Translator translator)
    {
        int i;
        Type type;
        i = 0;
        if (translator.curMethodLambda == null && lambdaexp.nameDecl != null && translator.getModule().getFlag(0x20000))
        {
            translator.curMethodLambda = lambdaexp;
        }
        translator.currentScope();
        translator.pushScope(lambdaexp);
        Declaration declaration = null;
        int j;
        int k;
        int l;
        int i1;
        if (lambdaexp.keywords == null)
        {
            j = 0;
        } else
        {
            j = lambdaexp.keywords.length;
        }
        if (lambdaexp.defaultArgs == null)
        {
            k = 0;
        } else
        {
            k = lambdaexp.defaultArgs.length - j;
        }
        l = 0;
        i1 = 0;
        for (Declaration declaration1 = lambdaexp.firstDecl(); declaration1 != null; declaration1 = declaration1.nextDecl())
        {
            if (declaration1.isAlias())
            {
                Declaration declaration2 = Translator.getOriginalRef(declaration1).getBinding();
                lambdaexp.replaceFollowing(declaration, declaration2);
                declaration2.context = lambdaexp;
                translator.pushRenamedAlias(declaration1);
                i++;
                declaration1 = declaration2;
            }
            Expression expression2 = declaration1.getTypeExp();
            if (expression2 instanceof LangExp)
            {
                declaration1.setType(translator.exp2Type((Pair)((LangExp)expression2).getLangValue()));
            }
            declaration = declaration1;
            if (l >= lambdaexp.min_args && (l < k + lambdaexp.min_args || lambdaexp.max_args >= 0 || l != k + lambdaexp.min_args))
            {
                lambdaexp.defaultArgs[i1] = translator.rewrite(lambdaexp.defaultArgs[i1]);
                i1++;
            }
            l++;
            translator.lexical.push(declaration1);
        }

        if (lambdaexp.isClassMethod() && !lambdaexp.nameDecl.getFlag(2048L))
        {
            lambdaexp.add(null, new Declaration(ThisExp.THIS_NAME));
        }
        LambdaExp lambdaexp1 = translator.curLambda;
        translator.curLambda = lambdaexp;
        type = lambdaexp.returnType;
        if (lambdaexp.body instanceof LangExp)
        {
            Object aobj[] = (Object[])(Object[])((LangExp)lambdaexp.body).getLangValue();
            Expression expression1 = translator.rewrite_car((Pair)aobj[0], (SyntaxForm)aobj[1]);
            type = translator.getLanguage().getTypeFor(expression1);
        }
        lambdaexp.body = translator.rewrite_body(obj);
        translator.curLambda = lambdaexp1;
        if (!(lambdaexp.body instanceof BeginExp)) goto _L2; else goto _L1
_L1:
        Expression aexpression[];
        int j1;
        aexpression = ((BeginExp)lambdaexp.body).getExpressions();
        j1 = aexpression.length;
        if (j1 <= 1) goto _L2; else goto _L3
_L3:
        if (aexpression[0] instanceof ReferenceExp) goto _L5; else goto _L4
_L4:
        Object obj1 = aexpression[0].valueIfConstant();
        if (!(obj1 instanceof Type) && !(obj1 instanceof Class)) goto _L2; else goto _L5
_L5:
        Expression expression = aexpression[0];
        int k1 = j1 - 1;
        if (k1 == 1)
        {
            lambdaexp.body = aexpression[1];
        } else
        {
            Expression aexpression1[] = new Expression[k1];
            System.arraycopy(aexpression, 1, aexpression1, 0, k1);
            lambdaexp.body = BeginExp.canonicalize(aexpression1);
        }
        lambdaexp.setCoercedReturnValue(expression, translator.getLanguage());
_L7:
        translator.pop(lambdaexp);
        lambdaexp.countDecls();
        translator.popRenamedAlias(i);
        lambdaexp.countDecls();
        if (translator.curMethodLambda == lambdaexp)
        {
            translator.curMethodLambda = null;
        }
        return;
_L2:
        lambdaexp.setCoercedReturnType(type);
        if (true) goto _L7; else goto _L6
_L6:
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Expression expression = rewrite(pair.getCdr(), translator);
        Translator.setLine(expression, pair);
        return expression;
    }

    public void rewriteFormals(LambdaExp lambdaexp, Object obj, Translator translator, TemplateScope templatescope)
    {
        Object obj1;
        int i;
        int j;
        int k;
        if (lambdaexp.getSymbol() == null)
        {
            String s1 = lambdaexp.getFileName();
            int l1 = lambdaexp.getLineNumber();
            if (s1 != null && l1 > 0)
            {
                lambdaexp.setSourceLocation(s1, l1);
            }
        }
        obj1 = obj;
        i = -1;
        j = -1;
        k = -1;
_L7:
        if (obj1 instanceof SyntaxForm)
        {
            obj1 = ((SyntaxForm)obj1).getDatum();
        }
        if (obj1 instanceof Pair) goto _L2; else goto _L1
_L1:
        if (!(obj1 instanceof Symbol)) goto _L4; else goto _L3
_L3:
        if (i < 0 && k < 0 && j < 0) goto _L6; else goto _L5
_L5:
        translator.syntaxError((new StringBuilder()).append("dotted rest-arg after ").append(optionalKeyword).append(", ").append(restKeyword).append(", or ").append(keyKeyword).toString());
_L10:
        return;
_L2:
        Pair pair = (Pair)obj1;
        Object obj2 = pair.getCar();
        if (obj2 instanceof SyntaxForm)
        {
            obj2 = ((SyntaxForm)obj2).getDatum();
        }
        Object obj3 = optionalKeyword;
        if (obj2 == obj3)
        {
            if (i >= 0)
            {
                translator.syntaxError((new StringBuilder()).append("multiple ").append(optionalKeyword).append(" in parameter list").toString());
                return;
            }
            if (j >= 0 || k >= 0)
            {
                translator.syntaxError((new StringBuilder()).append(optionalKeyword.toString()).append(" after ").append(restKeyword).append(" or ").append(keyKeyword).toString());
                return;
            }
            i = 0;
        } else
        {
            Object obj4 = restKeyword;
            if (obj2 == obj4)
            {
                if (j >= 0)
                {
                    translator.syntaxError((new StringBuilder()).append("multiple ").append(restKeyword).append(" in parameter list").toString());
                    return;
                }
                if (k >= 0)
                {
                    translator.syntaxError((new StringBuilder()).append(restKeyword.toString()).append(" after ").append(keyKeyword).toString());
                    return;
                }
                j = 0;
            } else
            {
                Object obj5 = keyKeyword;
                if (obj2 == obj5)
                {
                    if (k >= 0)
                    {
                        translator.syntaxError((new StringBuilder()).append("multiple ").append(keyKeyword).append(" in parameter list").toString());
                        return;
                    }
                    k = 0;
                } else
                if (translator.matches(pair.getCar(), "::") && (pair.getCdr() instanceof Pair))
                {
                    pair = (Pair)pair.getCdr();
                } else
                if (k >= 0)
                {
                    k++;
                } else
                if (j >= 0)
                {
                    j++;
                } else
                if (i >= 0)
                {
                    i++;
                } else
                {
                    lambdaexp.min_args = 1 + lambdaexp.min_args;
                }
            }
        }
        pair.getCdr();
        obj1 = pair.getCdr();
          goto _L7
_L6:
        j = 1;
_L9:
        if (j > 1)
        {
            translator.syntaxError((new StringBuilder()).append("multiple ").append(restKeyword).append(" parameters").toString());
            return;
        }
        break; /* Loop/switch isn't completed */
_L4:
        if (obj1 != LList.Empty)
        {
            translator.syntaxError("misformed formals in lambda");
            return;
        }
        if (true) goto _L9; else goto _L8
_L8:
        Object obj6;
        int l;
        int i1;
        Object obj7;
        TemplateScope templatescope1;
        if (i < 0)
        {
            i = 0;
        }
        if (j < 0)
        {
            j = 0;
        }
        if (k < 0)
        {
            k = 0;
        }
        Declaration declaration1;
        SyntaxForm syntaxform2;
        SyntaxForm syntaxform3;
        if (j > 0)
        {
            lambdaexp.max_args = -1;
        } else
        {
            lambdaexp.max_args = i + lambdaexp.min_args + k * 2;
        }
        if (i + k > 0)
        {
            lambdaexp.defaultArgs = new Expression[i + k];
        }
        if (k > 0)
        {
            lambdaexp.keywords = new Keyword[k];
        }
        obj6 = obj;
        l = 0;
        i1 = 0;
        obj7 = null;
_L11:
label0:
        {
            if (obj6 instanceof SyntaxForm)
            {
                syntaxform3 = (SyntaxForm)obj6;
                obj6 = syntaxform3.getDatum();
                templatescope = syntaxform3.getScope();
            }
            templatescope1 = templatescope;
            if (obj6 instanceof Pair)
            {
                break label0;
            }
            if (obj6 instanceof SyntaxForm)
            {
                syntaxform2 = (SyntaxForm)obj6;
                obj6 = syntaxform2.getDatum();
                templatescope = syntaxform2.getScope();
            }
            if (obj6 instanceof Symbol)
            {
                declaration1 = new Declaration(obj6);
                declaration1.setType(LangObjType.listType);
                declaration1.setFlag(0x40000L);
                declaration1.noteValue(null);
                addParam(declaration1, templatescope, lambdaexp, translator);
                return;
            }
        }
          goto _L10
        Pair pair1;
        Object obj8;
        pair1 = (Pair)obj6;
        obj8 = pair1.getCar();
        if (obj8 instanceof SyntaxForm)
        {
            SyntaxForm syntaxform1 = (SyntaxForm)obj8;
            obj8 = syntaxform1.getDatum();
            templatescope1 = syntaxform1.getScope();
        }
        Object obj9 = optionalKeyword;
        if (obj8 != obj9)
        {
            Object obj10 = restKeyword;
            if (obj8 != obj10)
            {
                Object obj11 = keyKeyword;
                if (obj8 != obj11)
                {
                    break MISSING_BLOCK_LABEL_958;
                }
            }
        }
        obj7 = obj8;
_L12:
        obj6 = pair1.getCdr();
          goto _L11
        Object obj12 = translator.pushPositionOf(pair1);
        Object obj13 = defaultDefault;
        if (translator.matches(obj8, "::"))
        {
            translator.syntaxError("'::' must follow parameter name");
            return;
        }
        Object obj14 = translator.namespaceResolve(obj8);
        Object obj15;
        Pair pair2;
        if (obj14 instanceof Symbol)
        {
            obj15 = obj14;
            boolean flag4 = pair1.getCdr() instanceof Pair;
            pair2 = null;
            if (flag4)
            {
                Pair pair6 = (Pair)pair1.getCdr();
                boolean flag5 = translator.matches(pair6.getCar(), "::");
                pair2 = null;
                if (flag5)
                {
                    if (!(pair1.getCdr() instanceof Pair))
                    {
                        translator.syntaxError((new StringBuilder()).append("'::' not followed by a type specifier (for parameter '").append(obj15).append("')").toString());
                        return;
                    }
                    Pair pair7 = (Pair)pair6.getCdr();
                    pair2 = pair7;
                    pair1 = pair7;
                }
            }
        } else
        {
            boolean flag = obj14 instanceof Pair;
            obj15 = null;
            pair2 = null;
            if (flag)
            {
                Pair pair3 = (Pair)obj14;
                Object obj16 = pair3.getCar();
                if (obj16 instanceof SyntaxForm)
                {
                    SyntaxForm syntaxform = (SyntaxForm)obj16;
                    obj16 = syntaxform.getDatum();
                    templatescope1 = syntaxform.getScope();
                }
                Object obj17 = translator.namespaceResolve(obj16);
                boolean flag1 = obj17 instanceof Symbol;
                obj15 = null;
                pair2 = null;
                if (flag1)
                {
                    boolean flag2 = pair3.getCdr() instanceof Pair;
                    obj15 = null;
                    pair2 = null;
                    if (flag2)
                    {
                        obj15 = obj17;
                        Pair pair4 = (Pair)pair3.getCdr();
                        boolean flag3 = translator.matches(pair4.getCar(), "::");
                        pair2 = null;
                        if (flag3)
                        {
                            if (!(pair4.getCdr() instanceof Pair))
                            {
                                translator.syntaxError((new StringBuilder()).append("'::' not followed by a type specifier (for parameter '").append(obj15).append("')").toString());
                                return;
                            }
                            Pair pair5 = (Pair)pair4.getCdr();
                            pair2 = pair5;
                            if (pair5.getCdr() instanceof Pair)
                            {
                                pair4 = (Pair)pair5.getCdr();
                            } else
                            if (pair5.getCdr() == LList.Empty)
                            {
                                pair4 = null;
                            } else
                            {
                                translator.syntaxError((new StringBuilder()).append("improper list in specifier for parameter '").append(obj15).append("')").toString());
                                return;
                            }
                        }
                        if (pair4 != null && obj7 != null)
                        {
                            obj13 = pair4.getCar();
                            if (pair4.getCdr() instanceof Pair)
                            {
                                pair4 = (Pair)pair4.getCdr();
                            } else
                            if (pair4.getCdr() == LList.Empty)
                            {
                                pair4 = null;
                            } else
                            {
                                translator.syntaxError((new StringBuilder()).append("improper list in specifier for parameter '").append(obj15).append("')").toString());
                                return;
                            }
                        }
                        if (pair4 != null)
                        {
                            if (pair2 != null)
                            {
                                translator.syntaxError((new StringBuilder()).append("duplicate type specifier for parameter '").append(obj15).append('\'').toString());
                                return;
                            }
                            pair2 = pair4;
                            if (pair4.getCdr() != LList.Empty)
                            {
                                translator.syntaxError((new StringBuilder()).append("junk at end of specifier for parameter '").append(obj15).append('\'').append(" after type ").append(pair4.getCar()).toString());
                                return;
                            }
                        }
                    }
                }
            }
        }
        if (obj15 == null)
        {
            translator.syntaxError((new StringBuilder()).append("parameter is neither name nor (name :: type) nor (name default): ").append(pair1).toString());
            return;
        }
        if (obj7 == optionalKeyword || obj7 == keyKeyword)
        {
            Expression aexpression[] = lambdaexp.defaultArgs;
            int j1 = l + 1;
            LangExp langexp = new LangExp(obj13);
            aexpression[l] = langexp;
            l = j1;
        }
        Declaration declaration;
        if (obj7 == keyKeyword)
        {
            Keyword akeyword[] = lambdaexp.keywords;
            int k1 = i1 + 1;
            LangExp langexp1;
            String s;
            if (obj15 instanceof Symbol)
            {
                s = ((Symbol)obj15).getName();
            } else
            {
                s = obj15.toString();
            }
            akeyword[i1] = Keyword.make(s);
            i1 = k1;
        }
        declaration = new Declaration(obj15);
        Translator.setLine(declaration, obj6);
        if (pair2 != null)
        {
            langexp1 = new LangExp(pair2);
            declaration.setTypeExp(langexp1);
            declaration.setFlag(8192L);
        } else
        if (obj7 == restKeyword)
        {
            declaration.setType(LangObjType.listType);
        }
        declaration.setFlag(0x40000L);
        declaration.noteValue(null);
        addParam(declaration, templatescope1, lambdaexp, translator);
        translator.popPositionOf(obj12);
          goto _L12
    }

    public void setKeywords(Object obj, Object obj1, Object obj2)
    {
        optionalKeyword = obj;
        restKeyword = obj1;
        keyKeyword = obj2;
    }

    public Object skipAttrs(LambdaExp lambdaexp, Object obj, Translator translator)
    {
_L2:
        Pair pair;
        Object obj1;
label0:
        {
            if (obj instanceof Pair)
            {
                pair = (Pair)obj;
                if (pair.getCdr() instanceof Pair)
                {
                    break label0;
                }
            }
            return obj;
        }
        obj1 = pair.getCar();
          goto _L1
_L4:
        obj = ((Pair)pair.getCdr()).getCdr();
        if (true) goto _L2; else goto _L1
_L1:
        if (translator.matches(obj1, "::") || (obj1 instanceof Keyword)) goto _L4; else goto _L3
_L3:
        return obj;
    }

}
