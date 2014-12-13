// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.SynchronizedExp;
import gnu.expr.TryExp;
import gnu.kawa.reflect.StaticFieldLocation;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.math.IntNum;
import kawa.lang.Macro;
import kawa.lang.Pattern;
import kawa.lang.Quote;
import kawa.lang.SyntaxForms;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.lang.Translator;
import kawa.standard.IfFeature;
import kawa.standard.Scheme;
import kawa.standard.syntax_case;

// Referenced classes of package kawa.lib:
//            lists, std_syntax, prim_syntax

public class syntax extends ModuleBody
{
    public class frame extends ModuleBody
    {

        final ModuleMethod lambda$Fn1;
        Object list;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 1)
            {
                return lambda1(obj);
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        Object lambda1(Object obj)
        {
            Object obj1 = list;
            do
            {
                boolean flag;
                if (obj == null)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag ? flag : lists.isNull(obj1))
                {
                    return obj;
                }
                Object obj2 = lists.cdr.apply1(obj1);
                obj = Scheme.applyToArgs.apply3(lists.caar.apply1(obj1), obj, lists.cdar.apply1(obj1));
                obj1 = obj2;
            } while (true);
        }

        public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
        {
            if (modulemethod.selector == 1)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return super.match1(modulemethod, obj, callcontext);
            }
        }

        public frame()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 1, null, 4097);
            modulemethod.setProperty("source-location", "syntax.scm:83");
            lambda$Fn1 = modulemethod;
        }
    }

    public class frame0 extends ModuleBody
    {

        TemplateScope $unnamed$0;
        Object $unnamed$1[];

        public Object lambda6loop(Object obj)
        {
            Object aobj[] = SyntaxPattern.allocVars(5, $unnamed$1);
            if (syntax.Lit85.match(obj, aobj, 0))
            {
                return Pair.make(syntax.Lit86.execute(aobj, $unnamed$0), lambda6loop(syntax.Lit87.execute(aobj, $unnamed$0)));
            }
            if (syntax.Lit88.match(obj, aobj, 0))
            {
                return LList.Empty;
            }
            if (syntax.Lit89.match(obj, aobj, 0))
            {
                Object obj1 = syntax.Lit90.execute(aobj, $unnamed$0);
                Object aobj1[];
                if ("invalid case-lambda clause" instanceof Object[])
                {
                    aobj1 = (Object[])"invalid case-lambda clause";
                } else
                {
                    aobj1 = (new Object[] {
                        "invalid case-lambda clause"
                    });
                }
                return LList.list1(prim_syntax.syntaxError(obj1, aobj1));
            } else
            {
                return syntax_case.error("syntax-case", obj);
            }
        }

        public frame0()
        {
        }
    }


    public static final Macro $Pccond$Mnexpand;
    public static final Macro $Pcimport;
    public static final Location $Prvt$and = StaticFieldLocation.make("kawa.lib.std_syntax", "and");
    public static final Location $Prvt$define$Mnconstant = StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnconstant");
    public static final Location $Prvt$define$Mnsyntax = StaticFieldLocation.make("kawa.lib.prim_syntax", "define$Mnsyntax");
    public static final Location $Prvt$if = StaticFieldLocation.make("kawa.lib.prim_syntax", "if");
    public static final Location $Prvt$let = StaticFieldLocation.make("kawa.lib.std_syntax", "let");
    public static final Location $Prvt$or = StaticFieldLocation.make("kawa.lib.std_syntax", "or");
    public static final Location $Prvt$try$Mncatch = StaticFieldLocation.make("kawa.lib.prim_syntax", "try$Mncatch");
    public static final syntax $instance;
    static final SyntaxPattern Lit0 = new SyntaxPattern("\f\007\013", new Object[0], 2);
    static final SyntaxTemplate Lit1 = new SyntaxTemplate("\001\0", "\003", new Object[0], 0);
    static final SyntaxPattern Lit10 = new SyntaxPattern("\003", new Object[0], 1);
    static final SimpleSymbol Lit100;
    static final SyntaxRules Lit101;
    static final SimpleSymbol Lit102;
    static final SimpleSymbol Lit103;
    static final SimpleSymbol Lit104;
    static final SimpleSymbol Lit105;
    static final SimpleSymbol Lit106;
    static final SimpleSymbol Lit107;
    static final SimpleSymbol Lit108;
    static final SimpleSymbol Lit109;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit110;
    static final SimpleSymbol Lit111;
    static final SimpleSymbol Lit112;
    static final SimpleSymbol Lit113;
    static final SimpleSymbol Lit114;
    static final SimpleSymbol Lit115;
    static final SimpleSymbol Lit116;
    static final SimpleSymbol Lit117;
    static final SimpleSymbol Lit118;
    static final SimpleSymbol Lit119;
    static final SyntaxRules Lit12;
    static final SimpleSymbol Lit120;
    static final SimpleSymbol Lit121;
    static final SimpleSymbol Lit122;
    static final SimpleSymbol Lit123;
    static final SimpleSymbol Lit13;
    static final SyntaxRules Lit14;
    static final SimpleSymbol Lit15;
    static final SyntaxRules Lit16;
    static final SimpleSymbol Lit17;
    static final SyntaxRules Lit18;
    static final SimpleSymbol Lit19;
    static final SyntaxTemplate Lit2 = new SyntaxTemplate("\001\0", "\n", new Object[0], 0);
    static final SyntaxRules Lit20;
    static final SimpleSymbol Lit21;
    static final SyntaxPattern Lit22 = new SyntaxPattern("\f\007\f\017\f\027\b", new Object[0], 3);
    static final SyntaxTemplate Lit23 = new SyntaxTemplate("\001\001\001", "\013", new Object[0], 0);
    static final SyntaxTemplate Lit24 = new SyntaxTemplate("\001\001\001", "\023", new Object[0], 0);
    static final SimpleSymbol Lit25;
    static final SyntaxPattern Lit26 = new SyntaxPattern("\f\007\f\017\023", new Object[0], 3);
    static final SyntaxTemplate Lit27 = new SyntaxTemplate("\001\001\0", "\013", new Object[0], 0);
    static final SyntaxTemplate Lit28 = new SyntaxTemplate("\001\001\0", "\022", new Object[0], 0);
    static final SimpleSymbol Lit29;
    static final SyntaxPattern Lit3 = new SyntaxPattern("\b", new Object[0], 0);
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final SimpleSymbol Lit33;
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit36;
    static final SyntaxRules Lit37;
    static final SimpleSymbol Lit38;
    static final SyntaxPattern Lit39;
    static final SyntaxPattern Lit4 = new SyntaxPattern("\003", new Object[0], 1);
    static final SyntaxTemplate Lit40 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    static final SyntaxTemplate Lit41;
    static final SyntaxTemplate Lit42 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    static final SyntaxTemplate Lit43 = new SyntaxTemplate("\001\001\000\001", "\033", new Object[0], 0);
    static final SyntaxTemplate Lit44 = new SyntaxTemplate("\001\001\000\001", "\020", new Object[0], 0);
    static final SyntaxTemplate Lit45;
    static final SyntaxPattern Lit46;
    static final SyntaxTemplate Lit47 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    static final SyntaxTemplate Lit48;
    static final SyntaxTemplate Lit49 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    static final SyntaxPattern Lit5 = new SyntaxPattern(",\f\007\f\017\b\023", new Object[0], 3);
    static final SyntaxTemplate Lit50 = new SyntaxTemplate("\001\001\000\001", "\033", new Object[0], 0);
    static final SyntaxTemplate Lit51 = new SyntaxTemplate("\001\001\000\001", "\020", new Object[0], 0);
    static final SyntaxTemplate Lit52 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    static final SyntaxPattern Lit53;
    static final SyntaxTemplate Lit54 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    static final SyntaxTemplate Lit55;
    static final SyntaxTemplate Lit56 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    static final SyntaxTemplate Lit57 = new SyntaxTemplate("\001\001\000\001", "\033", new Object[0], 0);
    static final SyntaxTemplate Lit58 = new SyntaxTemplate("\001\001\000\001", "\020", new Object[0], 0);
    static final SyntaxTemplate Lit59 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    static final SyntaxTemplate Lit6 = new SyntaxTemplate("\001\001\0", "\003", new Object[0], 0);
    static final SyntaxPattern Lit60;
    static final SyntaxTemplate Lit61;
    static final SyntaxTemplate Lit62 = new SyntaxTemplate("\001\001\001\001", "\023", new Object[0], 0);
    static final SyntaxTemplate Lit63 = new SyntaxTemplate("\001\001\001\001", "\033", new Object[0], 0);
    static final SyntaxTemplate Lit64 = new SyntaxTemplate("\001\001\001\001", "\020", new Object[0], 0);
    static final SyntaxPattern Lit65;
    static final SyntaxTemplate Lit66 = new SyntaxTemplate("\001\001\000\001", "\022", new Object[0], 0);
    static final SyntaxPattern Lit67;
    static final SyntaxTemplate Lit68;
    static final SyntaxTemplate Lit69 = new SyntaxTemplate("\001\001\001", "\b\013", new Object[0], 0);
    static final SyntaxTemplate Lit7 = new SyntaxTemplate("\001\001\0", "\013", new Object[0], 0);
    static final SyntaxTemplate Lit70 = new SyntaxTemplate("\001\001\001", "\023", new Object[0], 0);
    static final SyntaxTemplate Lit71 = new SyntaxTemplate("\001\001\001", "\020", new Object[0], 0);
    static final SyntaxPattern Lit72 = new SyntaxPattern("\f\007\f\017\f\027\b", new Object[0], 3);
    static final SyntaxTemplate Lit73;
    static final SyntaxTemplate Lit74 = new SyntaxTemplate("\001\001\001", "\b\013", new Object[0], 0);
    static final SyntaxTemplate Lit75 = new SyntaxTemplate("\001\001\001", "\023", new Object[0], 0);
    static final SyntaxTemplate Lit76 = new SyntaxTemplate("\001\001\001", "\020", new Object[0], 0);
    static final SimpleSymbol Lit77;
    static final SyntaxRules Lit78;
    static final SimpleSymbol Lit79;
    static final SyntaxTemplate Lit8 = new SyntaxTemplate("\001\001\0", "\022", new Object[0], 0);
    static final SyntaxRules Lit80;
    static final SimpleSymbol Lit81;
    static final SyntaxPattern Lit82 = new SyntaxPattern("\f\007\013", new Object[0], 2);
    static final SyntaxTemplate Lit83;
    static final SyntaxTemplate Lit84 = new SyntaxTemplate("\001\0", "\n", new Object[0], 0);
    static final SyntaxPattern Lit85 = new SyntaxPattern("\034\f\027\033#", new Object[0], 5);
    static final SyntaxTemplate Lit86;
    static final SyntaxTemplate Lit87 = new SyntaxTemplate("\001\000\001\000\0", "\"", new Object[0], 0);
    static final SyntaxPattern Lit88 = new SyntaxPattern("\b", new Object[0], 2);
    static final SyntaxPattern Lit89 = new SyntaxPattern("\023", new Object[0], 3);
    static final SyntaxPattern Lit9 = new SyntaxPattern("\b", new Object[0], 0);
    static final SyntaxTemplate Lit90 = new SyntaxTemplate("\001\000\0", "\022", new Object[0], 0);
    static final SimpleSymbol Lit91;
    static final SyntaxRules Lit92;
    static final SimpleSymbol Lit93;
    static final SyntaxPattern Lit94 = new SyntaxPattern("\f\007\034\f\017\023\033", new Object[0], 4);
    static final SyntaxTemplate Lit95 = new SyntaxTemplate("\001\001\000\0", "\013", new Object[0], 0);
    static final SyntaxTemplate Lit96;
    static final SyntaxTemplate Lit97;
    static final SimpleSymbol Lit98;
    static final SyntaxRules Lit99;
    public static final Macro case$Mnlambda;
    public static final Macro cond$Mnexpand;
    public static final Macro define$Mnalias$Mnparameter;
    public static final Macro define$Mnmacro;
    public static final Macro define$Mnsyntax$Mncase;
    public static final Macro defmacro;
    public static final ModuleMethod identifier$Mnlist$Qu;
    public static final ModuleMethod identifier$Mnpair$Mnlist$Qu;
    public static final Macro _fldimport;
    public static final ModuleMethod import$Mnhandle$Mnexcept;
    public static final ModuleMethod import$Mnhandle$Mnonly;
    public static final ModuleMethod import$Mnhandle$Mnprefix;
    public static final ModuleMethod import$Mnhandle$Mnrename;
    public static final ModuleMethod import$Mnmapper;
    public static final Macro let$Mnvalues;
    public static final Macro let$St$Mnvalues;
    public static final Macro receive;
    public static final Macro _fldsynchronized;
    public static final Macro try$Mnfinally;
    public static final Macro unless;
    public static final Macro when;

    public syntax()
    {
        ModuleInfo.register(this);
    }

    public static Object importHandleExcept(Object obj, Object obj1)
    {
        if (lists.memq(obj, obj1) != Boolean.FALSE)
        {
            obj = null;
        }
        return obj;
    }

    public static Object importHandleOnly(Object obj, Object obj1)
    {
        if (lists.memq(obj, obj1) != Boolean.FALSE)
        {
            return obj;
        } else
        {
            return null;
        }
    }

    public static Object importHandlePrefix(Object obj, Object obj1)
    {
        if (obj != null);
        return null;
    }

    public static Object importHandleRename(Object obj, Object obj1)
    {
        if (lists.isPair(obj1))
        {
            if (obj == lists.caar.apply1(obj1))
            {
                return lists.cadar.apply1(obj1);
            } else
            {
                return importHandleRename(obj, lists.cdr.apply1(obj1));
            }
        } else
        {
            return obj;
        }
    }

    public static Procedure importMapper(Object obj)
    {
        frame frame1 = new frame();
        frame1.list = obj;
        return frame1.Fn1;
    }

    public static boolean isIdentifierList(Object obj)
    {
        Object aobj[];
label0:
        {
            boolean flag;
label1:
            {
                if (Translator.listLength(obj) >= 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label1;
                }
                do
                {
                    aobj = SyntaxPattern.allocVars(2, null);
                    if (!Lit0.match(obj, aobj, 0))
                    {
                        break;
                    }
                    TemplateScope templatescope = TemplateScope.make();
                    flag = std_syntax.isIdentifier(Lit1.execute(aobj, templatescope));
                    if (!flag)
                    {
                        break label1;
                    }
                    TemplateScope templatescope1 = TemplateScope.make();
                    obj = Lit2.execute(aobj, templatescope1);
                } while (true);
                if (!Lit3.match(obj, aobj, 0))
                {
                    break label0;
                }
                flag = true;
            }
            return flag;
        }
        if (Lit4.match(obj, aobj, 0))
        {
            return false;
        }
        return syntax_case.error("syntax-case", obj) != Boolean.FALSE;
    }

    public static boolean isIdentifierPairList(Object obj)
    {
        Object aobj[];
label0:
        {
            boolean flag;
label1:
            {
                if (Translator.listLength(obj) >= 0)
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (!flag)
                {
                    break label1;
                }
                do
                {
                    aobj = SyntaxPattern.allocVars(3, null);
                    if (!Lit5.match(obj, aobj, 0))
                    {
                        break;
                    }
                    TemplateScope templatescope = TemplateScope.make();
                    flag = std_syntax.isIdentifier(Lit6.execute(aobj, templatescope));
                    if (!flag)
                    {
                        break label1;
                    }
                    TemplateScope templatescope1 = TemplateScope.make();
                    flag = std_syntax.isIdentifier(Lit7.execute(aobj, templatescope1));
                    if (!flag)
                    {
                        break label1;
                    }
                    TemplateScope templatescope2 = TemplateScope.make();
                    obj = Lit8.execute(aobj, templatescope2);
                } while (true);
                if (!Lit9.match(obj, aobj, 0))
                {
                    break label0;
                }
                flag = true;
            }
            return flag;
        }
        if (Lit10.match(obj, aobj, 0))
        {
            return false;
        }
        return syntax_case.error("syntax-case", obj) != Boolean.FALSE;
    }

    static Object lambda2(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(3, null);
        if (Lit22.match(obj, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            gnu.expr.Expression expression = SyntaxForms.rewrite(Lit23.execute(aobj, templatescope));
            TemplateScope templatescope1 = TemplateScope.make();
            return new TryExp(expression, SyntaxForms.rewrite(Lit24.execute(aobj, templatescope1)));
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    static Object lambda3(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(3, null);
        if (Lit26.match(obj, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            gnu.expr.Expression expression = SyntaxForms.rewrite(Lit27.execute(aobj, templatescope));
            TemplateScope templatescope1 = TemplateScope.make();
            return new SynchronizedExp(expression, SyntaxForms.rewriteBody(Lit28.execute(aobj, templatescope1)));
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    static Object lambda4(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(4, null);
        if (Lit39.match(obj, aobj, 0))
        {
            TemplateScope templatescope10 = TemplateScope.make();
            if (isIdentifierPairList(Lit40.execute(aobj, templatescope10)))
            {
                TemplateScope templatescope12 = TemplateScope.make();
                Object aobj15[] = new Object[2];
                aobj15[0] = Lit41.execute(aobj, templatescope12);
                Object aobj16[] = new Object[2];
                aobj16[0] = lists.cons(lists.cons(import$Mnhandle$Mnrename, Lit42.execute(aobj, templatescope12)), Lit43.execute(aobj, templatescope12));
                aobj16[1] = Lit44.execute(aobj, templatescope12);
                aobj15[1] = Quote.consX$V(aobj16);
                return Quote.append$V(aobj15);
            }
            TemplateScope templatescope11 = TemplateScope.make();
            Object obj6 = Lit45.execute(aobj, templatescope11);
            Object aobj14[];
            if ("invalid 'rename' clause in import" instanceof Object[])
            {
                aobj14 = (Object[])"invalid 'rename' clause in import";
            } else
            {
                aobj14 = (new Object[] {
                    "invalid 'rename' clause in import"
                });
            }
            return prim_syntax.syntaxError(obj6, aobj14);
        }
        if (Lit46.match(obj, aobj, 0))
        {
            TemplateScope templatescope7 = TemplateScope.make();
            if (isIdentifierList(Lit47.execute(aobj, templatescope7)))
            {
                TemplateScope templatescope9 = TemplateScope.make();
                Object aobj12[] = new Object[2];
                aobj12[0] = Lit48.execute(aobj, templatescope9);
                Object aobj13[] = new Object[2];
                aobj13[0] = lists.cons(lists.cons(import$Mnhandle$Mnonly, Lit49.execute(aobj, templatescope9)), Lit50.execute(aobj, templatescope9));
                aobj13[1] = Lit51.execute(aobj, templatescope9);
                aobj12[1] = Quote.consX$V(aobj13);
                return Quote.append$V(aobj12);
            }
            TemplateScope templatescope8 = TemplateScope.make();
            Object obj5 = Lit52.execute(aobj, templatescope8);
            Object aobj11[];
            if ("invalid 'only' identifier list" instanceof Object[])
            {
                aobj11 = (Object[])"invalid 'only' identifier list";
            } else
            {
                aobj11 = (new Object[] {
                    "invalid 'only' identifier list"
                });
            }
            return prim_syntax.syntaxError(obj5, aobj11);
        }
        if (Lit53.match(obj, aobj, 0))
        {
            TemplateScope templatescope4 = TemplateScope.make();
            if (isIdentifierList(Lit54.execute(aobj, templatescope4)))
            {
                TemplateScope templatescope6 = TemplateScope.make();
                Object aobj9[] = new Object[2];
                aobj9[0] = Lit55.execute(aobj, templatescope6);
                Object aobj10[] = new Object[2];
                aobj10[0] = lists.cons(lists.cons(import$Mnhandle$Mnexcept, Lit56.execute(aobj, templatescope6)), Lit57.execute(aobj, templatescope6));
                aobj10[1] = Lit58.execute(aobj, templatescope6);
                aobj9[1] = Quote.consX$V(aobj10);
                return Quote.append$V(aobj9);
            }
            TemplateScope templatescope5 = TemplateScope.make();
            Object obj4 = Lit59.execute(aobj, templatescope5);
            Object aobj8[];
            if ("invalid 'except' identifier list" instanceof Object[])
            {
                aobj8 = (Object[])"invalid 'except' identifier list";
            } else
            {
                aobj8 = (new Object[] {
                    "invalid 'except' identifier list"
                });
            }
            return prim_syntax.syntaxError(obj4, aobj8);
        }
        if (Lit60.match(obj, aobj, 0))
        {
            TemplateScope templatescope3 = TemplateScope.make();
            Object aobj6[] = new Object[2];
            aobj6[0] = Lit61.execute(aobj, templatescope3);
            Object aobj7[] = new Object[2];
            aobj7[0] = lists.cons(lists.cons(import$Mnhandle$Mnprefix, Lit62.execute(aobj, templatescope3)), Lit63.execute(aobj, templatescope3));
            aobj7[1] = Lit64.execute(aobj, templatescope3);
            aobj6[1] = Quote.consX$V(aobj7);
            return Quote.append$V(aobj6);
        }
        if (Lit65.match(obj, aobj, 0))
        {
            TemplateScope templatescope2 = TemplateScope.make();
            Object obj3 = Lit66.execute(aobj, templatescope2);
            Object aobj5[];
            if ("invalid prefix clause in import" instanceof Object[])
            {
                aobj5 = (Object[])"invalid prefix clause in import";
            } else
            {
                aobj5 = (new Object[] {
                    "invalid prefix clause in import"
                });
            }
            return prim_syntax.syntaxError(obj3, aobj5);
        }
        if (Lit67.match(obj, aobj, 0))
        {
            TemplateScope templatescope1 = TemplateScope.make();
            Object obj2 = Lit68.execute(aobj, templatescope1);
            Object aobj3[] = new Object[2];
            aobj3[0] = Lit69.execute(aobj, templatescope1);
            Object aobj4[] = new Object[2];
            aobj4[0] = importMapper(std_syntax.syntaxObject$To$Datum(Lit70.execute(aobj, templatescope1)));
            aobj4[1] = Lit71.execute(aobj, templatescope1);
            aobj3[1] = Quote.consX$V(aobj4);
            return Pair.make(obj2, Quote.append$V(aobj3));
        }
        if (Lit72.match(obj, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            Object obj1 = Lit73.execute(aobj, templatescope);
            Object aobj1[] = new Object[2];
            aobj1[0] = Lit74.execute(aobj, templatescope);
            Object aobj2[] = new Object[2];
            aobj2[0] = importMapper(std_syntax.syntaxObject$To$Datum(Lit75.execute(aobj, templatescope)));
            aobj2[1] = Lit76.execute(aobj, templatescope);
            aobj1[1] = Quote.consX$V(aobj2);
            return Pair.make(obj1, Quote.append$V(aobj1));
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    static Object lambda5(Object obj)
    {
        frame0 frame0_1 = new frame0();
        frame0_1._fld1 = SyntaxPattern.allocVars(2, null);
        if (Lit82.match(obj, frame0_1._fld1, 0))
        {
            frame0_1._fld0 = TemplateScope.make();
            return Pair.make(Lit83.execute(frame0_1._fld1, frame0_1._fld0), frame0_1.lambda6loop(Lit84.execute(frame0_1._fld1, frame0_1._fld0)));
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    static Object lambda7(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(4, null);
        if (Lit94.match(obj, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            if (IfFeature.testFeature(Lit95.execute(aobj, templatescope)))
            {
                TemplateScope templatescope2 = TemplateScope.make();
                return Lit96.execute(aobj, templatescope2);
            } else
            {
                TemplateScope templatescope1 = TemplateScope.make();
                return Lit97.execute(aobj, templatescope1);
            }
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        default:
            return super.apply1(modulemethod, obj);

        case 4: // '\004'
            if (isIdentifierList(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 5: // '\005'
            if (isIdentifierPairList(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 10: // '\n'
            return importMapper(obj);

        case 2: // '\002'
            return lambda2(obj);

        case 3: // '\003'
            return lambda3(obj);

        case 11: // '\013'
            return lambda4(obj);

        case 12: // '\f'
            return lambda5(obj);

        case 13: // '\r'
            return lambda7(obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 6: // '\006'
            return importHandleOnly(obj, obj1);

        case 7: // '\007'
            return importHandleExcept(obj, obj1);

        case 8: // '\b'
            return importHandlePrefix(obj, obj1);

        case 9: // '\t'
            return importHandleRename(obj, obj1);
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 6: // '\006'
        case 7: // '\007'
        case 8: // '\b'
        case 9: // '\t'
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 13: // '\r'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 12: // '\f'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 11: // '\013'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 3: // '\003'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 2: // '\002'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 10: // '\n'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 5: // '\005'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 4: // '\004'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match2(modulemethod, obj, obj1, callcontext);

        case 9: // '\t'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 8: // '\b'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 7: // '\007'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 6: // '\006'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit123 = (SimpleSymbol)(new SimpleSymbol("%define-macro")).readResolve();
        Lit122 = (SimpleSymbol)(new SimpleSymbol("form")).readResolve();
        Lit121 = (SimpleSymbol)(new SimpleSymbol("if")).readResolve();
        Lit120 = (SimpleSymbol)(new SimpleSymbol("prefix")).readResolve();
        Lit119 = (SimpleSymbol)(new SimpleSymbol("instance")).readResolve();
        Lit118 = (SimpleSymbol)(new SimpleSymbol("kawa.standard.ImportFromLibrary")).readResolve();
        Lit117 = (SimpleSymbol)(new SimpleSymbol("x")).readResolve();
        Lit116 = (SimpleSymbol)(new SimpleSymbol("call-with-values")).readResolve();
        Lit115 = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
        Lit114 = (SimpleSymbol)(new SimpleSymbol("not")).readResolve();
        Lit113 = (SimpleSymbol)(new SimpleSymbol("or")).readResolve();
        Lit112 = (SimpleSymbol)(new SimpleSymbol("and")).readResolve();
        Lit111 = (SimpleSymbol)(new SimpleSymbol("else")).readResolve();
        Lit110 = (SimpleSymbol)(new SimpleSymbol("begin")).readResolve();
        Lit109 = (SimpleSymbol)(new SimpleSymbol("lambda")).readResolve();
        Lit108 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
        Lit107 = (SimpleSymbol)(new SimpleSymbol("wt")).readResolve();
        Lit106 = (SimpleSymbol)(new SimpleSymbol("as")).readResolve();
        Lit105 = (SimpleSymbol)(new SimpleSymbol("arg")).readResolve();
        Lit104 = (SimpleSymbol)(new SimpleSymbol("quasiquote")).readResolve();
        Lit103 = (SimpleSymbol)(new SimpleSymbol("gnu.mapping.LocationProc")).readResolve();
        Lit102 = (SimpleSymbol)(new SimpleSymbol("$lookup$")).readResolve();
        Object aobj[] = new Object[1];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("define-alias-parameter")).readResolve();
        Lit100 = simplesymbol;
        aobj[0] = simplesymbol;
        SyntaxRule asyntaxrule[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern = new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3);
        Object aobj1[] = new Object[21];
        aobj1[0] = Lit110;
        aobj1[1] = (SimpleSymbol)(new SimpleSymbol("define-constant")).readResolve();
        aobj1[2] = (SimpleSymbol)(new SimpleSymbol("::")).readResolve();
        aobj1[3] = (SimpleSymbol)(new SimpleSymbol("<gnu.mapping.LocationProc>")).readResolve();
        aobj1[4] = PairWithPosition.make(Lit102, Pair.make(Lit103, Pair.make(Pair.make(Lit104, Pair.make((SimpleSymbol)(new SimpleSymbol("makeNamed")).readResolve(), LList.Empty)), LList.Empty)), "syntax.scm", 0x105004);
        aobj1[5] = Lit108;
        aobj1[6] = PairWithPosition.make(Lit102, Pair.make(Lit103, Pair.make(Pair.make(Lit104, Pair.make((SimpleSymbol)(new SimpleSymbol("pushConverter")).readResolve(), LList.Empty)), LList.Empty)), "syntax.scm", 0x106009);
        aobj1[7] = Lit109;
        aobj1[8] = PairWithPosition.make(Lit105, LList.Empty, "syntax.scm", 0x10800a);
        aobj1[9] = (SimpleSymbol)(new SimpleSymbol("try-catch")).readResolve();
        aobj1[10] = Lit106;
        aobj1[11] = PairWithPosition.make(Lit105, LList.Empty, "syntax.scm", 0x10a00e);
        aobj1[12] = (SimpleSymbol)(new SimpleSymbol("ex")).readResolve();
        aobj1[13] = (SimpleSymbol)(new SimpleSymbol("<java.lang.ClassCastException>")).readResolve();
        aobj1[14] = Lit115;
        aobj1[15] = Lit107;
        aobj1[16] = PairWithPosition.make(Lit102, Pair.make((SimpleSymbol)(new SimpleSymbol("gnu.mapping.WrongType")).readResolve(), Pair.make(Pair.make(Lit104, Pair.make((SimpleSymbol)(new SimpleSymbol("make")).readResolve(), LList.Empty)), LList.Empty)), "syntax.scm", 0x10c014);
        aobj1[17] = PairWithPosition.make(PairWithPosition.make(Lit106, PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("<int>")).readResolve(), PairWithPosition.make(IntNum.make(1), LList.Empty, "syntax.scm", 0x10d016), "syntax.scm", 0x10d010), "syntax.scm", 0x10d00c), PairWithPosition.make(Lit105, LList.Empty, "syntax.scm", 0x10d019), "syntax.scm", 0x10d00c);
        aobj1[18] = (SimpleSymbol)(new SimpleSymbol("set!")).readResolve();
        aobj1[19] = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("field")).readResolve(), PairWithPosition.make(Lit107, PairWithPosition.make(PairWithPosition.make(Lit108, PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("expectedType")).readResolve(), LList.Empty, "syntax.scm", 0x10e015), "syntax.scm", 0x10e015), LList.Empty, "syntax.scm", 0x10e014), "syntax.scm", 0x10e011), "syntax.scm", 0x10e00a);
        aobj1[20] = PairWithPosition.make(PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("primitive-throw")).readResolve(), PairWithPosition.make(Lit107, LList.Empty, "syntax.scm", 0x10f015), "syntax.scm", 0x10f004), LList.Empty, "syntax.scm", 0x10f004);
        asyntaxrule[0] = new SyntaxRule(syntaxpattern, "\001\001\001", "\021\030\004\271\021\030\f\t\003\021\030\024\021\030\034\b\021\030$)\021\030,\b\003\b\023\b\021\0304\t\003\b\021\030<\021\030D\b\021\030L9\021\030T\t\013\030\\\b\021\030d\021\030l\b\021\030ty\b\021\030|\b\021\030\204\021\030d\t\003\030\214A\021\030\224\021\030\234\b\013\030\244", aobj1, 0);
        Lit101 = new SyntaxRules(aobj, asyntaxrule, 3);
        Object aobj2[] = new Object[1];
        SimpleSymbol simplesymbol1 = (SimpleSymbol)(new SimpleSymbol("receive")).readResolve();
        Lit98 = simplesymbol1;
        aobj2[0] = simplesymbol1;
        SyntaxRule asyntaxrule1[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern1 = new SyntaxPattern("\f\030\f\007\f\017\r\027\020\b\b", new Object[0], 3);
        Object aobj3[] = new Object[2];
        aobj3[0] = Lit116;
        aobj3[1] = Lit109;
        asyntaxrule1[0] = new SyntaxRule(syntaxpattern1, "\001\001\003", "\021\030\0049\021\030\f\t\020\b\013\b\021\030\f\t\003\b\025\023", aobj3, 1);
        Lit99 = new SyntaxRules(aobj2, asyntaxrule1, 3);
        Object aobj4[] = new Object[1];
        SimpleSymbol simplesymbol2 = (SimpleSymbol)(new SimpleSymbol("cond-expand")).readResolve();
        Lit91 = simplesymbol2;
        aobj4[0] = simplesymbol2;
        Lit97 = new SyntaxTemplate("\001\001\000\0", "\021\030\004\032", aobj4, 0);
        Object aobj5[] = new Object[1];
        aobj5[0] = Lit110;
        Lit96 = new SyntaxTemplate("\001\001\000\0", "\021\030\004\022", aobj5, 0);
        Lit93 = (SimpleSymbol)(new SimpleSymbol("%cond-expand")).readResolve();
        Object aobj6[] = new Object[5];
        aobj6[0] = Lit91;
        aobj6[1] = Lit112;
        aobj6[2] = Lit113;
        aobj6[3] = Lit114;
        aobj6[4] = Lit111;
        SyntaxRule asyntaxrule2[] = new SyntaxRule[8];
        SyntaxPattern syntaxpattern2 = new SyntaxPattern("\f\030\b", new Object[0], 0);
        Object aobj7[] = new Object[1];
        aobj7[0] = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("%syntax-error")).readResolve(), PairWithPosition.make("Unfulfilled cond-expand", LList.Empty, "syntax.scm", 0xc4023), "syntax.scm", 0xc4014);
        asyntaxrule2[0] = new SyntaxRule(syntaxpattern2, "", "\030\004", aobj7, 0);
        Object aobj8[] = new Object[1];
        aobj8[0] = Lit111;
        SyntaxPattern syntaxpattern3 = new SyntaxPattern("\f\030<\f\002\r\007\000\b\b\b", aobj8, 1);
        Object aobj9[] = new Object[1];
        aobj9[0] = Lit110;
        asyntaxrule2[1] = new SyntaxRule(syntaxpattern3, "\003", "\021\030\004\b\005\003", aobj9, 1);
        Object aobj10[] = new Object[1];
        aobj10[0] = Lit112;
        SyntaxPattern syntaxpattern4 = new SyntaxPattern("\f\030L\034\f\002\b\r\007\000\b\b\r\017\b\b\b", aobj10, 2);
        Object aobj11[] = new Object[1];
        aobj11[0] = Lit110;
        asyntaxrule2[2] = new SyntaxRule(syntaxpattern4, "\003\003", "\021\030\004\b\005\003", aobj11, 1);
        Object aobj12[] = new Object[1];
        aobj12[0] = Lit112;
        SyntaxPattern syntaxpattern5 = new SyntaxPattern("\f\030|L\f\002\f\007\r\017\b\b\b\r\027\020\b\b\r\037\030\b\b", aobj12, 4);
        Object aobj13[] = new Object[2];
        aobj13[0] = Lit91;
        aobj13[1] = Lit112;
        asyntaxrule2[3] = new SyntaxRule(syntaxpattern5, "\001\003\003\003", "\021\030\004\241\t\003\b\021\030\004Q1\021\030\f\b\r\013\b\025\023\b\035\033\b\035\033", aobj13, 1);
        Object aobj14[] = new Object[1];
        aobj14[0] = Lit113;
        SyntaxPattern syntaxpattern6 = new SyntaxPattern("\f\030L\034\f\002\b\r\007\000\b\b\r\017\b\b\b", aobj14, 2);
        Object aobj15[] = new Object[1];
        aobj15[0] = Lit91;
        asyntaxrule2[4] = new SyntaxRule(syntaxpattern6, "\003\003", "\021\030\004\b\r\013", aobj15, 1);
        Object aobj16[] = new Object[1];
        aobj16[0] = Lit113;
        SyntaxPattern syntaxpattern7 = new SyntaxPattern("\f\030|L\f\002\f\007\r\017\b\b\b\r\027\020\b\b\r\037\030\b\b", aobj16, 4);
        Object aobj17[] = new Object[4];
        aobj17[0] = Lit91;
        aobj17[1] = Lit110;
        aobj17[2] = Lit111;
        aobj17[3] = Lit113;
        asyntaxrule2[5] = new SyntaxRule(syntaxpattern7, "\001\003\003\003", "\021\030\004I\t\003\b\021\030\f\b\025\023\b\021\030\024\b\021\030\004Q1\021\030\034\b\r\013\b\025\023\b\035\033", aobj17, 1);
        Object aobj18[] = new Object[1];
        aobj18[0] = Lit114;
        SyntaxPattern syntaxpattern8 = new SyntaxPattern("\f\030\\,\f\002\f\007\b\r\017\b\b\b\r\027\020\b\b", aobj18, 3);
        Object aobj19[] = new Object[2];
        aobj19[0] = Lit91;
        aobj19[1] = Lit111;
        asyntaxrule2[6] = new SyntaxRule(syntaxpattern8, "\001\003\003", "\021\030\004I\t\003\b\021\030\004\b\025\023\b\021\030\f\b\r\013", aobj19, 1);
        SyntaxPattern syntaxpattern9 = new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3);
        Object aobj20[] = new Object[1];
        aobj20[0] = Lit93;
        asyntaxrule2[7] = new SyntaxRule(syntaxpattern9, "\001\000\0", "\021\030\004\031\t\003\n\022", aobj20, 0);
        Lit92 = new SyntaxRules(aobj6, asyntaxrule2, 4);
        Object aobj21[] = new Object[1];
        aobj21[0] = Lit109;
        Lit86 = new SyntaxTemplate("\001\000\001\000\0", "\021\030\004\t\023\032", aobj21, 0);
        Object aobj22[] = new Object[1];
        aobj22[0] = PairWithPosition.make(Lit102, Pair.make((SimpleSymbol)(new SimpleSymbol("gnu.expr.GenericProc")).readResolve(), Pair.make(Pair.make(Lit104, Pair.make((SimpleSymbol)(new SimpleSymbol("makeWithoutSorting")).readResolve(), LList.Empty)), LList.Empty)), "syntax.scm", 0x9f009);
        Lit83 = new SyntaxTemplate("\001\0", "\030\004", aobj22, 0);
        Lit81 = (SimpleSymbol)(new SimpleSymbol("case-lambda")).readResolve();
        Object aobj23[] = new Object[1];
        SimpleSymbol simplesymbol3 = (SimpleSymbol)(new SimpleSymbol("let*-values")).readResolve();
        Lit79 = simplesymbol3;
        aobj23[0] = simplesymbol3;
        SyntaxRule asyntaxrule3[] = new SyntaxRule[2];
        SyntaxPattern syntaxpattern10 = new SyntaxPattern("\f\030\f\b\f\007\r\017\b\b\b", new Object[0], 2);
        Object aobj24[] = new Object[1];
        aobj24[0] = Lit110;
        asyntaxrule3[0] = new SyntaxRule(syntaxpattern10, "\001\003", "\021\030\004\t\003\b\r\013", aobj24, 1);
        SyntaxPattern syntaxpattern11 = new SyntaxPattern("\f\030<\f\007\r\017\b\b\b\f\027\r\037\030\b\b", new Object[0], 4);
        Object aobj25[] = new Object[2];
        SimpleSymbol simplesymbol4 = (SimpleSymbol)(new SimpleSymbol("let-values")).readResolve();
        Lit77 = simplesymbol4;
        aobj25[0] = simplesymbol4;
        aobj25[1] = Lit79;
        asyntaxrule3[1] = new SyntaxRule(syntaxpattern11, "\001\003\001\003", "\021\030\004\021\b\003\b\021\030\f\031\b\r\013\t\023\b\035\033", aobj25, 1);
        Lit80 = new SyntaxRules(aobj23, asyntaxrule3, 4);
        Object aobj26[] = new Object[1];
        aobj26[0] = Lit77;
        SyntaxRule asyntaxrule4[] = new SyntaxRule[6];
        SyntaxPattern syntaxpattern12 = new SyntaxPattern("\f\030,\r\007\000\b\b\f\017\r\027\020\b\b", new Object[0], 3);
        Object aobj27[] = new Object[3];
        aobj27[0] = Lit77;
        aobj27[1] = "bind";
        aobj27[2] = Lit110;
        asyntaxrule4[0] = new SyntaxRule(syntaxpattern12, "\003\001\003", "\021\030\004\021\030\f\031\b\005\003\t\020\b\021\030\024\t\013\b\025\023", aobj27, 1);
        SyntaxPattern syntaxpattern13 = new SyntaxPattern("\f\030\f\002\f\b\f\007\f\017\b", new Object[] {
            "bind"
        }, 2);
        Object aobj28[] = new Object[1];
        aobj28[0] = Lit115;
        asyntaxrule4[1] = new SyntaxRule(syntaxpattern13, "\001\001", "\021\030\004\t\003\b\013", aobj28, 0);
        SyntaxPattern syntaxpattern14 = new SyntaxPattern("\f\030\f\002\\,\f\007\f\017\b\r\027\020\b\b\f\037\f'\b", new Object[] {
            "bind"
        }, 5);
        Object aobj29[] = new Object[2];
        aobj29[0] = Lit77;
        aobj29[1] = "mktmp";
        asyntaxrule4[2] = new SyntaxRule(syntaxpattern14, "\001\001\003\001\001", "\021\030\004\021\030\f\t\003\t\013\t\020\031\b\025\023\t\033\b#", aobj29, 1);
        SyntaxPattern syntaxpattern15 = new SyntaxPattern("\f\030\f\002\f\b\f\007\f\017\f\027\f\037\f'\b", new Object[] {
            "mktmp"
        }, 5);
        Object aobj30[] = new Object[4];
        aobj30[0] = Lit116;
        aobj30[1] = Lit109;
        aobj30[2] = Lit77;
        aobj30[3] = "bind";
        asyntaxrule4[3] = new SyntaxRule(syntaxpattern15, "\001\001\001\001\001", "\021\030\0049\021\030\f\t\020\b\003\b\021\030\f\t\013\b\021\030\024\021\030\034\t\023\t\033\b#", aobj30, 0);
        SyntaxPattern syntaxpattern16 = new SyntaxPattern("\f\030\f\002\034\f\007\013\f\027,\r\037\030\b\b\f',\r/(\b\b\f7\b", new Object[] {
            "mktmp"
        }, 7);
        Object aobj31[] = new Object[4];
        aobj31[0] = Lit77;
        aobj31[1] = "mktmp";
        aobj31[2] = PairWithPosition.make(Lit117, LList.Empty, "syntax.scm", 0x8b02b);
        aobj31[3] = PairWithPosition.make(Lit117, LList.Empty, "syntax.scm", 0x8b046);
        asyntaxrule4[4] = new SyntaxRule(syntaxpattern16, "\001\000\001\003\001\003\001", "\021\030\004\021\030\f\t\n\t\023)\021\035\033\030\024\t#A\021-+\b\t\003\030\034\b3", aobj31, 1);
        SyntaxPattern syntaxpattern17 = new SyntaxPattern("\f\030\f\002\f\007\f\017,\r\027\020\b\b\f\037,\r' \b\b\f/\b", new Object[] {
            "mktmp"
        }, 6);
        Object aobj32[] = new Object[6];
        aobj32[0] = Lit116;
        aobj32[1] = Lit109;
        aobj32[2] = Lit117;
        aobj32[3] = Lit77;
        aobj32[4] = "bind";
        aobj32[5] = PairWithPosition.make(Lit117, LList.Empty, "syntax.scm", 0x91035);
        asyntaxrule4[5] = new SyntaxRule(syntaxpattern17, "\001\001\003\001\003\001", "\021\030\0049\021\030\f\t\020\b\013\b\021\030\f)\021\025\023\030\024\b\021\030\034\021\030$\t\033A\021%#\b\t\003\030,\b+", aobj32, 1);
        Lit78 = new SyntaxRules(aobj26, asyntaxrule4, 7);
        Object aobj33[] = new Object[1];
        aobj33[0] = PairWithPosition.make(Lit102, Pair.make(Lit118, Pair.make(Pair.make(Lit104, Pair.make(Lit119, LList.Empty)), LList.Empty)), "syntax.scm", 0x72007);
        Lit73 = new SyntaxTemplate("\001\001\001", "\030\004", aobj33, 0);
        Object aobj34[] = new Object[1];
        aobj34[0] = PairWithPosition.make(Lit102, Pair.make(Lit118, Pair.make(Pair.make(Lit104, Pair.make(Lit119, LList.Empty)), LList.Empty)), "syntax.scm", 0x70007);
        Lit68 = new SyntaxTemplate("\001\001\001", "\030\004", aobj34, 0);
        Object aobj35[] = new Object[1];
        aobj35[0] = (SimpleSymbol)(new SimpleSymbol("library")).readResolve();
        Lit67 = new SyntaxPattern("\f\007,\f\002\f\017\b\f\027\b", aobj35, 3);
        Object aobj36[] = new Object[1];
        aobj36[0] = Lit120;
        Lit65 = new SyntaxPattern("\f\007,\f\002\f\017\023\f\037\b", aobj36, 4);
        Object aobj37[] = new Object[1];
        SimpleSymbol simplesymbol5 = (SimpleSymbol)(new SimpleSymbol("%import")).readResolve();
        Lit38 = simplesymbol5;
        aobj37[0] = simplesymbol5;
        Lit61 = new SyntaxTemplate("\001\001\001\001", "\021\030\004\b\013", aobj37, 0);
        Object aobj38[] = new Object[1];
        aobj38[0] = Lit120;
        Lit60 = new SyntaxPattern("\f\007<\f\002\f\017\f\027\b\f\037\b", aobj38, 4);
        Object aobj39[] = new Object[1];
        aobj39[0] = Lit38;
        Lit55 = new SyntaxTemplate("\001\001\000\001", "\021\030\004\b\013", aobj39, 0);
        Object aobj40[] = new Object[1];
        aobj40[0] = (SimpleSymbol)(new SimpleSymbol("except")).readResolve();
        Lit53 = new SyntaxPattern("\f\007,\f\002\f\017\023\f\037\b", aobj40, 4);
        Object aobj41[] = new Object[1];
        aobj41[0] = Lit38;
        Lit48 = new SyntaxTemplate("\001\001\000\001", "\021\030\004\b\013", aobj41, 0);
        Object aobj42[] = new Object[1];
        aobj42[0] = (SimpleSymbol)(new SimpleSymbol("only")).readResolve();
        Lit46 = new SyntaxPattern("\f\007,\f\002\f\017\023\f\037\b", aobj42, 4);
        Object aobj43[] = new Object[1];
        aobj43[0] = (SimpleSymbol)(new SimpleSymbol("rest")).readResolve();
        Lit45 = new SyntaxTemplate("\001\001\000\001", "\030\004", aobj43, 0);
        Object aobj44[] = new Object[1];
        aobj44[0] = Lit38;
        Lit41 = new SyntaxTemplate("\001\001\000\001", "\021\030\004\b\013", aobj44, 0);
        Object aobj45[] = new Object[1];
        aobj45[0] = (SimpleSymbol)(new SimpleSymbol("rename")).readResolve();
        Lit39 = new SyntaxPattern("\f\007,\f\002\f\017\023\f\037\b", aobj45, 4);
        Object aobj46[] = new Object[1];
        SimpleSymbol simplesymbol6 = (SimpleSymbol)(new SimpleSymbol("import")).readResolve();
        Lit36 = simplesymbol6;
        aobj46[0] = simplesymbol6;
        SyntaxRule asyntaxrule5[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern18 = new SyntaxPattern("\f\030\r\007\000\b\b", new Object[0], 1);
        Object aobj47[] = new Object[3];
        aobj47[0] = Lit110;
        aobj47[1] = Lit38;
        aobj47[2] = PairWithPosition.make(LList.Empty, LList.Empty, "syntax.scm", 0x5c022);
        asyntaxrule5[0] = new SyntaxRule(syntaxpattern18, "\003", "\021\030\004\b\005\021\030\f\t\003\030\024", aobj47, 1);
        Lit37 = new SyntaxRules(aobj46, asyntaxrule5, 1);
        Lit35 = (SimpleSymbol)(new SimpleSymbol("import-mapper")).readResolve();
        Lit34 = (SimpleSymbol)(new SimpleSymbol("import-handle-rename")).readResolve();
        Lit33 = (SimpleSymbol)(new SimpleSymbol("import-handle-prefix")).readResolve();
        Lit32 = (SimpleSymbol)(new SimpleSymbol("import-handle-except")).readResolve();
        Lit31 = (SimpleSymbol)(new SimpleSymbol("import-handle-only")).readResolve();
        Lit30 = (SimpleSymbol)(new SimpleSymbol("identifier-pair-list?")).readResolve();
        Lit29 = (SimpleSymbol)(new SimpleSymbol("identifier-list?")).readResolve();
        Lit25 = (SimpleSymbol)(new SimpleSymbol("synchronized")).readResolve();
        Lit21 = (SimpleSymbol)(new SimpleSymbol("try-finally")).readResolve();
        Object aobj48[] = new Object[1];
        SimpleSymbol simplesymbol7 = (SimpleSymbol)(new SimpleSymbol("when")).readResolve();
        Lit17 = simplesymbol7;
        aobj48[0] = simplesymbol7;
        SyntaxRule asyntaxrule6[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern19 = new SyntaxPattern("\f\030\f\007\r\017\b\b\b", new Object[0], 2);
        Object aobj49[] = new Object[3];
        aobj49[0] = Lit121;
        aobj49[1] = Lit114;
        aobj49[2] = Lit110;
        asyntaxrule6[0] = new SyntaxRule(syntaxpattern19, "\001\003", "\021\030\004)\021\030\f\b\003\b\021\030\024\b\r\013", aobj49, 1);
        Lit20 = new SyntaxRules(aobj48, asyntaxrule6, 2);
        Lit19 = (SimpleSymbol)(new SimpleSymbol("unless")).readResolve();
        Object aobj50[] = new Object[1];
        aobj50[0] = Lit17;
        SyntaxRule asyntaxrule7[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern20 = new SyntaxPattern("\f\030\f\007\r\017\b\b\b", new Object[0], 2);
        Object aobj51[] = new Object[2];
        aobj51[0] = Lit121;
        aobj51[1] = Lit110;
        asyntaxrule7[0] = new SyntaxRule(syntaxpattern20, "\001\003", "\021\030\004\t\003\b\021\030\f\b\r\013", aobj51, 1);
        Lit18 = new SyntaxRules(aobj50, asyntaxrule7, 2);
        Object aobj52[] = new Object[1];
        SimpleSymbol simplesymbol8 = (SimpleSymbol)(new SimpleSymbol("define-syntax-case")).readResolve();
        Lit15 = simplesymbol8;
        aobj52[0] = simplesymbol8;
        SyntaxRule asyntaxrule8[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern21 = new SyntaxPattern("\f\030\f\007\f\017\023", new Object[0], 3);
        Object aobj53[] = new Object[5];
        aobj53[0] = (SimpleSymbol)(new SimpleSymbol("define-syntax")).readResolve();
        aobj53[1] = Lit109;
        aobj53[2] = PairWithPosition.make(Lit122, LList.Empty, "syntax.scm", 0x16011);
        aobj53[3] = (SimpleSymbol)(new SimpleSymbol("syntax-case")).readResolve();
        aobj53[4] = Lit122;
        asyntaxrule8[0] = new SyntaxRule(syntaxpattern21, "\001\001\0", "\021\030\004\t\003\b\021\030\f\021\030\024\b\021\030\034\021\030$\t\013\022", aobj53, 0);
        Lit16 = new SyntaxRules(aobj52, asyntaxrule8, 3);
        Object aobj54[] = new Object[1];
        SimpleSymbol simplesymbol9 = (SimpleSymbol)(new SimpleSymbol("define-macro")).readResolve();
        Lit13 = simplesymbol9;
        aobj54[0] = simplesymbol9;
        SyntaxRule asyntaxrule9[] = new SyntaxRule[2];
        SyntaxPattern syntaxpattern22 = new SyntaxPattern("\f\030\034\f\007\013\023", new Object[0], 3);
        Object aobj55[] = new Object[2];
        aobj55[0] = Lit123;
        aobj55[1] = Lit109;
        asyntaxrule9[0] = new SyntaxRule(syntaxpattern22, "\001\000\0", "\021\030\004\t\003\b\021\030\f\t\n\022", aobj55, 0);
        SyntaxPattern syntaxpattern23 = new SyntaxPattern("\f\030\f\007\f\017\b", new Object[0], 2);
        Object aobj56[] = new Object[1];
        aobj56[0] = Lit123;
        asyntaxrule9[1] = new SyntaxRule(syntaxpattern23, "\001\001", "\021\030\004\t\003\b\013", aobj56, 0);
        Lit14 = new SyntaxRules(aobj54, asyntaxrule9, 3);
        Object aobj57[] = new Object[1];
        SimpleSymbol simplesymbol10 = (SimpleSymbol)(new SimpleSymbol("defmacro")).readResolve();
        Lit11 = simplesymbol10;
        aobj57[0] = simplesymbol10;
        SyntaxRule asyntaxrule10[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern24 = new SyntaxPattern("\f\030\f\007\f\017\023", new Object[0], 3);
        Object aobj58[] = new Object[2];
        aobj58[0] = Lit123;
        aobj58[1] = Lit109;
        asyntaxrule10[0] = new SyntaxRule(syntaxpattern24, "\001\001\0", "\021\030\004\t\003\b\021\030\f\t\013\022", aobj58, 0);
        Lit12 = new SyntaxRules(aobj57, asyntaxrule10, 3);
        $instance = new syntax();
        defmacro = Macro.make(Lit11, Lit12, $instance);
        define$Mnmacro = Macro.make(Lit13, Lit14, $instance);
        define$Mnsyntax$Mncase = Macro.make(Lit15, Lit16, $instance);
        when = Macro.make(Lit17, Lit18, $instance);
        unless = Macro.make(Lit19, Lit20, $instance);
        SimpleSymbol simplesymbol11 = Lit21;
        syntax syntax1 = $instance;
        try$Mnfinally = Macro.make(simplesymbol11, new ModuleMethod(syntax1, 2, null, 4097), $instance);
        _fldsynchronized = Macro.make(Lit25, new ModuleMethod(syntax1, 3, null, 4097), $instance);
        identifier$Mnlist$Qu = new ModuleMethod(syntax1, 4, Lit29, 4097);
        identifier$Mnpair$Mnlist$Qu = new ModuleMethod(syntax1, 5, Lit30, 4097);
        import$Mnhandle$Mnonly = new ModuleMethod(syntax1, 6, Lit31, 8194);
        import$Mnhandle$Mnexcept = new ModuleMethod(syntax1, 7, Lit32, 8194);
        import$Mnhandle$Mnprefix = new ModuleMethod(syntax1, 8, Lit33, 8194);
        import$Mnhandle$Mnrename = new ModuleMethod(syntax1, 9, Lit34, 8194);
        import$Mnmapper = new ModuleMethod(syntax1, 10, Lit35, 4097);
        _fldimport = Macro.make(Lit36, Lit37, $instance);
        $Pcimport = Macro.make(Lit38, new ModuleMethod(syntax1, 11, null, 4097), $instance);
        let$Mnvalues = Macro.make(Lit77, Lit78, $instance);
        let$St$Mnvalues = Macro.make(Lit79, Lit80, $instance);
        case$Mnlambda = Macro.make(Lit81, new ModuleMethod(syntax1, 12, null, 4097), $instance);
        cond$Mnexpand = Macro.make(Lit91, Lit92, $instance);
        SimpleSymbol simplesymbol12 = Lit93;
        ModuleMethod modulemethod = new ModuleMethod(syntax1, 13, null, 4097);
        modulemethod.setProperty("source-location", "syntax.scm:227");
        $Pccond$Mnexpand = Macro.make(simplesymbol12, modulemethod, $instance);
        receive = Macro.make(Lit98, Lit99, $instance);
        define$Mnalias$Mnparameter = Macro.make(Lit100, Lit101, $instance);
        $instance.run();
    }
}
