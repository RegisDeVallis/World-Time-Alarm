// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.kawa.functions.CompileNamedPart;
import gnu.kawa.lispexpr.LispLanguage;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Environment;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;
import java.util.IdentityHashMap;
import java.util.Vector;

// Referenced classes of package kawa.lang:
//            Syntax, SyntaxForm, SyntaxForms, Translator

public class Quote extends Syntax
{

    private static final Object CYCLE = new String("(cycle)");
    protected static final int QUOTE_DEPTH = -1;
    private static final Object WORKING = new String("(working)");
    static final Method appendMethod;
    static final Method consXMethod;
    static final Method makePairMethod;
    static final Method makeVectorMethod = ClassType.make("gnu.lists.FVector").getDeclaredMethod("make", 1);
    public static final Quote plainQuote = new Quote("quote", false);
    public static final Quote quasiQuote = new Quote("quasiquote", true);
    static final ClassType quoteType;
    static final Method vectorAppendMethod = ClassType.make("kawa.standard.vector_append").getDeclaredMethod("apply$V", 1);
    protected boolean isQuasi;

    public Quote(String s, boolean flag)
    {
        super(s);
        isQuasi = flag;
    }

    public static Object append$V(Object aobj[])
    {
        int i = aobj.length;
        if (i != 0) goto _L2; else goto _L1
_L1:
        Object obj1 = LList.Empty;
_L4:
        return obj1;
_L2:
        int j;
        Object obj = aobj[i - 1];
        j = i - 1;
        obj1 = obj;
_L5:
        if (--j < 0) goto _L4; else goto _L3
_L3:
        Object obj2;
        Pair pair;
        SyntaxForm syntaxform;
        Object obj3;
        obj2 = aobj[j];
        pair = null;
        syntaxform = null;
        obj3 = null;
_L6:
label0:
        {
            for (; obj2 instanceof SyntaxForm; obj2 = syntaxform.getDatum())
            {
                syntaxform = (SyntaxForm)obj2;
            }

            if (obj2 != LList.Empty)
            {
                break label0;
            }
            Pair pair1;
            Object obj4;
            Pair pair2;
            Pair pair3;
            if (pair != null)
            {
                pair.setCdr(obj1);
            } else
            {
                obj3 = obj1;
            }
            obj1 = obj3;
        }
          goto _L5
          goto _L4
        pair1 = (Pair)obj2;
        obj4 = pair1.getCar();
        if (syntaxform != null && !(obj4 instanceof SyntaxForm))
        {
            obj4 = SyntaxForms.makeForm(obj4, syntaxform.getScope());
        }
        pair2 = new Pair(obj4, null);
        if (pair == null)
        {
            pair3 = pair2;
        } else
        {
            pair.setCdr(pair2);
            pair3 = ((Pair) (obj3));
        }
        pair = pair2;
        obj2 = pair1.getCdr();
        obj3 = pair3;
          goto _L6
    }

    public static Object consX$V(Object aobj[])
    {
        return LList.consX(aobj);
    }

    private static ApplyExp makeInvokeMakeVector(Expression aexpression[])
    {
        return new ApplyExp(makeVectorMethod, aexpression);
    }

    public static Symbol makeSymbol(Namespace namespace, Object obj)
    {
        String s;
        if (obj instanceof CharSequence)
        {
            s = ((CharSequence)obj).toString();
        } else
        {
            s = (String)obj;
        }
        return namespace.getSymbol(s.intern());
    }

    public static Object quote(Object obj)
    {
        return plainQuote.expand(obj, -1, (Translator)Compilation.getCurrent());
    }

    public static Object quote(Object obj, Translator translator)
    {
        return plainQuote.expand(obj, -1, translator);
    }

    protected Expression coerceExpression(Object obj, Translator translator)
    {
        if (obj instanceof Expression)
        {
            return (Expression)obj;
        } else
        {
            return leaf(obj, translator);
        }
    }

    Object expand(Object obj, int i, SyntaxForm syntaxform, Object obj1, Translator translator)
    {
        IdentityHashMap identityhashmap;
        Object obj2;
        identityhashmap = (IdentityHashMap)obj1;
        obj2 = identityhashmap.get(obj);
        if (obj2 != WORKING) goto _L2; else goto _L1
_L1:
        identityhashmap.put(obj, CYCLE);
_L4:
        return obj2;
_L2:
        if (obj2 == CYCLE || obj2 != null) goto _L4; else goto _L3
_L3:
        if (!(obj instanceof Pair)) goto _L6; else goto _L5
_L5:
        Object obj3 = expand_pair((Pair)obj, i, syntaxform, obj1, translator);
_L17:
        if (obj != obj3 && identityhashmap.get(obj) == CYCLE)
        {
            translator.error('e', "cycle in non-literal data");
        }
        identityhashmap.put(obj, obj3);
        return obj3;
_L6:
        if (obj instanceof SyntaxForm)
        {
            SyntaxForm syntaxform1 = (SyntaxForm)obj;
            obj3 = expand(syntaxform1.getDatum(), i, syntaxform1, obj1, translator);
            continue; /* Loop/switch isn't completed */
        }
        if (!(obj instanceof FVector)) goto _L8; else goto _L7
_L7:
        FVector fvector;
        int j;
        Object aobj[];
        byte abyte0[];
        int k;
        int l;
        fvector = (FVector)obj;
        j = fvector.size();
        aobj = new Object[j];
        abyte0 = new byte[j];
        k = 0;
        l = 0;
_L15:
        if (l >= j) goto _L10; else goto _L9
_L9:
        Object obj5;
        int j1;
        obj5 = fvector.get(l);
        j1 = i;
        if (!(obj5 instanceof Pair) || i <= -1) goto _L12; else goto _L11
_L11:
        Pair pair = (Pair)obj5;
        if (!translator.matches(pair.getCar(), syntaxform, "unquote-splicing") || --j1 != 0) goto _L12; else goto _L13
_L13:
        Pair pair1;
label0:
        {
            if (pair.getCdr() instanceof Pair)
            {
                pair1 = (Pair)pair.getCdr();
                if (pair1.getCdr() == LList.Empty)
                {
                    break label0;
                }
            }
            return translator.syntaxError((new StringBuilder()).append("invalid used of ").append(pair.getCar()).append(" in quasiquote template").toString());
        }
        aobj[l] = translator.rewrite_car(pair1, syntaxform);
        abyte0[l] = 3;
_L14:
        if (abyte0[l] > k)
        {
            k = abyte0[l];
        }
        l++;
        continue; /* Loop/switch isn't completed */
_L12:
        aobj[l] = expand(obj5, j1, syntaxform, obj1, translator);
        if (aobj[l] == obj5)
        {
            abyte0[l] = 0;
        } else
        if (aobj[l] instanceof Expression)
        {
            abyte0[l] = 2;
        } else
        {
            abyte0[l] = 1;
        }
        if (true) goto _L14; else goto _L10
_L10:
        Object obj4;
        if (k == 0)
        {
            obj4 = fvector;
        } else
        if (k == 1)
        {
            obj4 = new FVector(aobj);
        } else
        {
            Expression aexpression[] = new Expression[j];
            int i1 = 0;
            while (i1 < j) 
            {
                if (abyte0[i1] == 3)
                {
                    aexpression[i1] = (Expression)aobj[i1];
                } else
                if (k < 3)
                {
                    aexpression[i1] = coerceExpression(aobj[i1], translator);
                } else
                if (abyte0[i1] < 2)
                {
                    Object aobj1[] = new Object[1];
                    aobj1[0] = aobj[i1];
                    aexpression[i1] = leaf(new FVector(aobj1), translator);
                } else
                {
                    Expression aexpression1[] = new Expression[1];
                    aexpression1[0] = (Expression)aobj[i1];
                    aexpression[i1] = makeInvokeMakeVector(aexpression1);
                }
                i1++;
            }
            if (k < 3)
            {
                obj4 = makeInvokeMakeVector(aexpression);
            } else
            {
                Method method = vectorAppendMethod;
                obj4 = new ApplyExp(method, aexpression);
            }
        }
        obj3 = obj4;
        continue; /* Loop/switch isn't completed */
        if (true) goto _L15; else goto _L8
_L8:
        obj3 = obj;
        if (true) goto _L17; else goto _L16
_L16:
    }

    protected Object expand(Object obj, int i, Translator translator)
    {
        return expand(obj, i, null, new IdentityHashMap(), translator);
    }

    protected boolean expandColonForms()
    {
        return true;
    }

    Object expand_pair(Pair pair, int i, SyntaxForm syntaxform, Object obj, Translator translator)
    {
        Pair pair1 = pair;
_L8:
        Pair pair2 = pair1;
        if (!expandColonForms() || pair1 != pair || !translator.matches(pair1.getCar(), syntaxform, LispLanguage.lookup_sym) || !(pair1.getCdr() instanceof Pair)) goto _L2; else goto _L1
_L1:
        Pair pair7 = (Pair)pair1.getCdr();
        if (!(pair7 instanceof Pair)) goto _L2; else goto _L3
_L3:
        Pair pair8 = (Pair)pair7.getCdr();
        if (!(pair8 instanceof Pair) || pair8.getCdr() != LList.Empty) goto _L2; else goto _L4
_L4:
        Object obj3;
        Object obj4;
        Expression expression1 = translator.rewrite_car(pair7, false);
        Expression expression2 = translator.rewrite_car(pair8, false);
        Namespace namespace = translator.namespaceResolvePrefix(expression1);
        Symbol symbol = translator.namespaceResolve(namespace, expression2);
        if (symbol != null)
        {
            obj3 = pair2;
            obj4 = symbol;
        } else
        if (namespace != null && i == 1)
        {
            Method method1 = quoteType.getDeclaredMethod("makeSymbol", 2);
            Expression aexpression3[] = new Expression[2];
            aexpression3[0] = QuoteExp.getInstance(namespace);
            aexpression3[1] = expression2;
            ApplyExp applyexp1 = new ApplyExp(method1, aexpression3);
            obj3 = pair2;
            obj4 = applyexp1;
        } else
        if ((expression1 instanceof ReferenceExp) && (expression2 instanceof QuoteExp))
        {
            Symbol symbol2 = translator.getGlobalEnvironment().getSymbol((new StringBuilder()).append(((ReferenceExp)expression1).getName()).append(':').append(((QuoteExp)expression2).getValue().toString()).toString());
            obj3 = pair2;
            obj4 = symbol2;
        } else
        {
            String s = CompileNamedPart.combineName(expression1, expression2);
            if (s != null)
            {
                Symbol symbol1 = translator.getGlobalEnvironment().getSymbol(s);
                obj3 = pair2;
                obj4 = symbol1;
            } else
            {
                Object obj10 = translator.pushPositionOf(pair1);
                translator.error('e', (new StringBuilder()).append("'").append(pair7.getCar()).append("' is not a valid prefix").toString());
                translator.popPositionOf(obj10);
                obj3 = pair2;
                obj4 = symbol;
            }
        }
_L6:
        if (pair == obj3)
        {
            return obj4;
        }
        break; /* Loop/switch isn't completed */
_L2:
label0:
        {
            Object obj6;
            SyntaxForm syntaxform1;
label1:
            {
                if (i >= 0)
                {
                    if (translator.matches(pair1.getCar(), syntaxform, "quasiquote"))
                    {
                        i++;
                    } else
                    {
                        if (translator.matches(pair1.getCar(), syntaxform, "unquote"))
                        {
                            i--;
                            if (pair1.getCdr() instanceof Pair)
                            {
                                pair6 = (Pair)pair1.getCdr();
                                if (pair6.getCdr() == LList.Empty)
                                {
                                    continue;
                                }
                            }
                            return translator.syntaxError((new StringBuilder()).append("invalid used of ").append(pair1.getCar()).append(" in quasiquote template").toString());
                        }
                        continue;
                    }
                }
                do
                {
                    Pair pair6;
                    do
                    {
                        if (i != 1 || !(pair1.getCar() instanceof Pair))
                        {
                            break label0;
                        }
                        obj6 = pair1.getCar();
                        syntaxform1 = syntaxform;
                        for (; obj6 instanceof SyntaxForm; obj6 = syntaxform1.getDatum())
                        {
                            syntaxform1 = (SyntaxForm)obj6;
                        }

                        break label1;
                    } while (i != 0);
                    Expression expression = translator.rewrite_car(pair6, syntaxform);
                    obj3 = pair2;
                    obj4 = expression;
                    continue; /* Loop/switch isn't completed */
                } while (!translator.matches(pair1.getCar(), syntaxform, "unquote-splicing"));
                return translator.syntaxError((new StringBuilder()).append("invalid used of ").append(pair1.getCar()).append(" in quasiquote template").toString());
            }
            byte byte0 = -1;
            if (obj6 instanceof Pair)
            {
                Object obj9 = ((Pair)obj6).getCar();
                int j1;
                Object obj8;
                Expression aexpression2[];
                if (translator.matches(obj9, syntaxform1, "unquote"))
                {
                    byte0 = 0;
                } else
                if (translator.matches(obj9, syntaxform1, "unquote-splicing"))
                {
                    byte0 = 1;
                }
            }
            if (byte0 >= 0)
            {
                Object obj7 = ((Pair)obj6).getCdr();
                Vector vector = new Vector();
                do
                {
                    if (obj7 instanceof SyntaxForm)
                    {
                        syntaxform1 = (SyntaxForm)obj7;
                        obj7 = syntaxform1.getDatum();
                    }
                    if (obj7 == LList.Empty)
                    {
                        j1 = 1 + vector.size();
                        obj8 = expand(pair1.getCdr(), 1, syntaxform, obj, translator);
                        if (j1 > 1)
                        {
                            aexpression2 = new Expression[j1];
                            vector.copyInto(aexpression2);
                            aexpression2[j1 - 1] = coerceExpression(obj8, translator);
                            Method method;
                            if (byte0 == 0)
                            {
                                method = consXMethod;
                            } else
                            {
                                method = appendMethod;
                            }
                            obj8 = new ApplyExp(method, aexpression2);
                        }
                        obj3 = pair1;
                        obj4 = obj8;
                        continue; /* Loop/switch isn't completed */
                    }
                    if (obj7 instanceof Pair)
                    {
                        vector.addElement(translator.rewrite_car((Pair)obj7, syntaxform1));
                        obj7 = ((Pair)obj7).getCdr();
                    } else
                    {
                        return translator.syntaxError("improper list argument to unquote");
                    }
                } while (true);
            }
        }
        Object obj1 = expand(pair1.getCar(), i, syntaxform, obj, translator);
        if (obj1 == pair1.getCar())
        {
            obj3 = pair1.getCdr();
            if (obj3 instanceof Pair)
            {
                pair1 = (Pair)obj3;
            } else
            {
                obj4 = expand(obj3, i, syntaxform, obj, translator);
                continue; /* Loop/switch isn't completed */
            }
            continue; /* Loop/switch isn't completed */
        }
        Object obj2 = expand(pair1.getCdr(), i, syntaxform, obj, translator);
        if ((obj1 instanceof Expression) || (obj2 instanceof Expression))
        {
            Expression aexpression[] = new Expression[2];
            aexpression[0] = coerceExpression(obj1, translator);
            aexpression[1] = coerceExpression(obj2, translator);
            ApplyExp applyexp = new ApplyExp(makePairMethod, aexpression);
            obj3 = pair2;
            obj4 = applyexp;
        } else
        {
            Pair pair5 = Translator.makePair(pair1, obj1, obj2);
            obj3 = pair2;
            obj4 = pair5;
        }
        if (true) goto _L6; else goto _L5
_L5:
        Pair pair3 = pair;
        Pair apair[] = new Pair[20];
        int j = 0;
        Object obj5;
        int i1;
        do
        {
            int k = apair.length;
            if (j >= k)
            {
                Pair apair1[] = new Pair[j * 2];
                System.arraycopy(apair, 0, apair1, 0, j);
                apair = apair1;
            }
            int l = j + 1;
            apair[j] = pair3;
            if (pair3.getCdr() == obj3)
            {
                if (obj4 instanceof Expression)
                {
                    obj5 = LList.Empty;
                } else
                {
                    obj5 = obj4;
                }
                for (i1 = l; --i1 >= 0;)
                {
                    Pair pair4 = apair[i1];
                    obj5 = Translator.makePair(pair4, pair4.getCar(), obj5);
                }

                break;
            }
            pair3 = (Pair)pair3.getCdr();
            j = l;
        } while (true);
        if (obj4 instanceof Expression)
        {
            Expression aexpression1[] = new Expression[2];
            aexpression1[1] = (Expression)obj4;
            if (i1 == 1)
            {
                aexpression1[0] = leaf(pair.getCar(), translator);
                return new ApplyExp(makePairMethod, aexpression1);
            } else
            {
                aexpression1[0] = leaf(obj5, translator);
                return new ApplyExp(appendMethod, aexpression1);
            }
        }
        return obj5;
        if (true) goto _L8; else goto _L7
_L7:
    }

    protected Expression leaf(Object obj, Translator translator)
    {
        return new QuoteExp(obj);
    }

    public Expression rewrite(Object obj, Translator translator)
    {
        Pair pair;
label0:
        {
            if (obj instanceof Pair)
            {
                pair = (Pair)obj;
                if (pair.getCdr() == LList.Empty)
                {
                    break label0;
                }
            }
            return translator.syntaxError("wrong number of arguments to quote");
        }
        Object obj1 = pair.getCar();
        int i;
        if (isQuasi)
        {
            i = 1;
        } else
        {
            i = -1;
        }
        return coerceExpression(expand(obj1, i, translator), translator);
    }

    static 
    {
        quoteType = ClassType.make("kawa.lang.Quote");
        consXMethod = quoteType.getDeclaredMethod("consX$V", 1);
        appendMethod = quoteType.getDeclaredMethod("append$V", 1);
        makePairMethod = Compilation.typePair.getDeclaredMethod("make", 2);
    }
}
