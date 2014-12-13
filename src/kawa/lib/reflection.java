// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import kawa.lang.GetFieldProc;
import kawa.lang.Macro;
import kawa.lang.Pattern;
import kawa.lang.Record;
import kawa.lang.RecordConstructor;
import kawa.lang.SetFieldProc;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lang.SyntaxTemplate;
import kawa.lang.TemplateScope;
import kawa.standard.syntax_case;

// Referenced classes of package kawa.lib:
//            std_syntax

public class reflection extends ModuleBody
{
    public class frame extends ModuleBody
    {

        final ModuleMethod lambda$Fn1;
        Object rtype;

        public Object apply1(ModuleMethod modulemethod, Object obj)
        {
            if (modulemethod.selector == 1)
            {
                if (lambda1(obj))
                {
                    return Boolean.TRUE;
                } else
                {
                    return Boolean.FALSE;
                }
            } else
            {
                return super.apply1(modulemethod, obj);
            }
        }

        boolean lambda1(Object obj)
        {
            Object obj1 = rtype;
            Type type;
            try
            {
                type = (Type)obj1;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "gnu.bytecode.Type.isInstance(java.lang.Object)", 1, obj1);
            }
            return type.isInstance(obj);
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
            modulemethod.setProperty("source-location", "reflection.scm:30");
            lambda$Fn1 = modulemethod;
        }
    }


    public static final reflection $instance;
    static final SimpleSymbol Lit0;
    static final SyntaxPattern Lit1 = new SyntaxPattern("\f\007\f\017,\r\027\020\b\b\b", new Object[0], 3);
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SyntaxRules Lit15;
    static final SimpleSymbol Lit16;
    static final SyntaxRules Lit17;
    static final SimpleSymbol Lit18;
    static final SyntaxRules Lit19;
    static final SyntaxTemplate Lit2 = new SyntaxTemplate("\001\001\003", "\b\025\023", new Object[0], 1);
    static final SimpleSymbol Lit20;
    static final SyntaxRules Lit21;
    static final SimpleSymbol Lit22;
    static final SyntaxRules Lit23;
    static final SimpleSymbol Lit24;
    static final SyntaxRules Lit25;
    static final SimpleSymbol Lit26;
    static final SyntaxRules Lit27;
    static final SimpleSymbol Lit28;
    static final SyntaxRules Lit29;
    static final SyntaxPattern Lit3 = new SyntaxPattern("\r\037\030\b\b", new Object[0], 4);
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final IntNum Lit33;
    static final IntNum Lit34;
    static final SyntaxTemplate Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod make$Mnrecord$Mntype;
    public static final Macro primitive$Mnarray$Mnget;
    public static final Macro primitive$Mnarray$Mnlength;
    public static final Macro primitive$Mnarray$Mnnew;
    public static final Macro primitive$Mnarray$Mnset;
    public static final Macro primitive$Mnconstructor;
    public static final Macro primitive$Mnget$Mnfield;
    public static final Macro primitive$Mnget$Mnstatic;
    public static final Macro primitive$Mnset$Mnfield;
    public static final Macro primitive$Mnset$Mnstatic;
    public static final ModuleMethod record$Mnaccessor;
    public static final ModuleMethod record$Mnconstructor;
    public static final ModuleMethod record$Mnmodifier;
    public static final ModuleMethod record$Mnpredicate;
    public static final ModuleMethod record$Mntype$Mndescriptor;
    public static final ModuleMethod record$Mntype$Mnfield$Mnnames;
    public static final ModuleMethod record$Mntype$Mnname;
    public static final ModuleMethod record$Qu;
    public static final ModuleMethod subtype$Qu;

    public reflection()
    {
        ModuleInfo.register(this);
    }

    public static boolean isRecord(Object obj)
    {
        return obj instanceof Record;
    }

    public static boolean isSubtype(Type type, Type type1)
    {
        return type.isSubtype(type1);
    }

    static Object lambda2(Object obj)
    {
        Object aobj[] = SyntaxPattern.allocVars(3, null);
        if (Lit1.match(obj, aobj, 0))
        {
            TemplateScope templatescope = TemplateScope.make();
            Object obj1 = std_syntax.generateTemporaries(Lit2.execute(aobj, templatescope));
            Object aobj1[] = SyntaxPattern.allocVars(4, aobj);
            if (Lit3.match(obj1, aobj1, 0))
            {
                TemplateScope templatescope1 = TemplateScope.make();
                return Lit4.execute(aobj1, templatescope1);
            } else
            {
                return syntax_case.error("syntax-case", obj1);
            }
        } else
        {
            return syntax_case.error("syntax-case", obj);
        }
    }

    public static ClassType makeRecordType(String s, LList llist)
    {
        return Record.makeRecordType(s, llist);
    }

    public static GetFieldProc recordAccessor(ClassType classtype, String s)
    {
        return new GetFieldProc(classtype, s);
    }

    public static RecordConstructor recordConstructor(ClassType classtype)
    {
        return recordConstructor(classtype, null);
    }

    public static RecordConstructor recordConstructor(ClassType classtype, Object obj)
    {
        return new RecordConstructor(classtype, obj);
    }

    public static SetFieldProc recordModifier(ClassType classtype, String s)
    {
        return new SetFieldProc(classtype, s);
    }

    public static Procedure recordPredicate(Object obj)
    {
        frame frame1 = new frame();
        frame1.rtype = obj;
        return frame1.Fn1;
    }

    public static Type recordTypeDescriptor(Object obj)
    {
        return Type.make(obj.getClass());
    }

    public static LList recordTypeFieldNames(Object obj)
    {
        ClassType classtype;
        try
        {
            classtype = LangObjType.coerceToClassType(obj);
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "kawa.lang.Record.typeFieldNames(class-type)", 1, obj);
        }
        return Record.typeFieldNames(classtype);
    }

    public static String recordTypeName(ClassType classtype)
    {
        return Compilation.demangleName(classtype.getName(), true);
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        case 3: // '\003'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        default:
            return super.apply1(modulemethod, obj);

        case 4: // '\004'
            ClassType classtype;
            ClassType classtype1;
            try
            {
                classtype1 = LangObjType.coerceToClassType(obj);
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "record-constructor", 1, obj);
            }
            return recordConstructor(classtype1);

        case 8: // '\b'
            if (isRecord(obj))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 9: // '\t'
            return recordPredicate(obj);

        case 10: // '\n'
            return recordTypeDescriptor(obj);

        case 11: // '\013'
            try
            {
                classtype = LangObjType.coerceToClassType(obj);
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "record-type-name", 1, obj);
            }
            return recordTypeName(classtype);

        case 12: // '\f'
            return recordTypeFieldNames(obj);

        case 2: // '\002'
            return lambda2(obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        case 5: // '\005'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 3: // '\003'
            String s2 = null;
            LList llist;
            if (obj != null)
            {
                s2 = obj.toString();
            }
            Type type;
            Type type1;
            ClassType classtype;
            String s;
            ClassType classtype1;
            String s1;
            ClassType classtype2;
            try
            {
                llist = (LList)obj1;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "make-record-type", 2, obj1);
            }
            return makeRecordType(s2, llist);

        case 4: // '\004'
            try
            {
                classtype2 = LangObjType.coerceToClassType(obj);
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "record-constructor", 1, obj);
            }
            return recordConstructor(classtype2, obj1);

        case 6: // '\006'
            try
            {
                classtype1 = LangObjType.coerceToClassType(obj);
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "record-accessor", 1, obj);
            }
            s1 = null;
            if (obj1 != null)
            {
                s1 = obj1.toString();
            }
            return recordAccessor(classtype1, s1);

        case 7: // '\007'
            try
            {
                classtype = LangObjType.coerceToClassType(obj);
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "record-modifier", 1, obj);
            }
            s = null;
            if (obj1 != null)
            {
                s = obj1.toString();
            }
            return recordModifier(classtype, s);

        case 13: // '\r'
            break;
        }
        try
        {
            type = LangObjType.coerceToType(obj);
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "subtype?", 1, obj);
        }
        try
        {
            type1 = LangObjType.coerceToType(obj1);
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "subtype?", 2, obj1);
        }
        if (isSubtype(type, type1))
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 3: // '\003'
        case 5: // '\005'
        case 6: // '\006'
        case 7: // '\007'
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 2: // '\002'
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
            if (LangObjType.coerceToClassTypeOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 10: // '\n'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 9: // '\t'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 8: // '\b'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 4: // '\004'
            break;
        }
        if (LangObjType.coerceToClassTypeOrNull(obj) != null)
        {
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;
        } else
        {
            return 0xfff40001;
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 5: // '\005'
        case 8: // '\b'
        case 9: // '\t'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        default:
            return super.match2(modulemethod, obj, obj1, callcontext);

        case 13: // '\r'
            if (LangObjType.coerceToTypeOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                if (LangObjType.coerceToTypeOrNull(obj1) != null)
                {
                    callcontext.value2 = obj1;
                    callcontext.proc = modulemethod;
                    callcontext.pc = 2;
                    return 0;
                } else
                {
                    return 0xfff40002;
                }
            } else
            {
                return 0xfff40001;
            }

        case 7: // '\007'
            if (LangObjType.coerceToClassTypeOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 6: // '\006'
            if (LangObjType.coerceToClassTypeOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 4: // '\004'
            if (LangObjType.coerceToClassTypeOrNull(obj) != null)
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40001;
            }

        case 3: // '\003'
            callcontext.value1 = obj;
            break;
        }
        if (obj1 instanceof LList)
        {
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        } else
        {
            return 0xfff40002;
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit34 = IntNum.make(1);
        Lit33 = IntNum.make(9);
        Lit32 = (SimpleSymbol)(new SimpleSymbol("make")).readResolve();
        Lit31 = (SimpleSymbol)(new SimpleSymbol("constant-fold")).readResolve();
        Lit30 = (SimpleSymbol)(new SimpleSymbol("subtype?")).readResolve();
        Object aobj[] = new Object[1];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("primitive-set-static")).readResolve();
        Lit28 = simplesymbol;
        aobj[0] = simplesymbol;
        SyntaxRule asyntaxrule[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern = new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3);
        Object aobj1[] = new Object[4];
        aobj1[0] = Lit31;
        aobj1[1] = Lit32;
        aobj1[2] = (SimpleSymbol)(new SimpleSymbol("<gnu.kawa.reflect.StaticSet>")).readResolve();
        aobj1[3] = PairWithPosition.make(Lit33, LList.Empty, "reflection.scm", 0x6f017);
        asyntaxrule[0] = new SyntaxRule(syntaxpattern, "\001\001\001", "\021\030\004\021\030\f\021\030\024\t\003\t\013\t\023\030\034", aobj1, 0);
        Lit29 = new SyntaxRules(aobj, asyntaxrule, 3);
        Object aobj2[] = new Object[1];
        SimpleSymbol simplesymbol1 = (SimpleSymbol)(new SimpleSymbol("primitive-get-static")).readResolve();
        Lit26 = simplesymbol1;
        aobj2[0] = simplesymbol1;
        SyntaxRule asyntaxrule1[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern1 = new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3);
        Object aobj3[] = new Object[4];
        aobj3[0] = Lit31;
        aobj3[1] = Lit32;
        aobj3[2] = (SimpleSymbol)(new SimpleSymbol("<gnu.kawa.reflect.StaticGet>")).readResolve();
        aobj3[3] = PairWithPosition.make(Lit33, LList.Empty, "reflection.scm", 0x69017);
        asyntaxrule1[0] = new SyntaxRule(syntaxpattern1, "\001\001\001", "\021\030\004\021\030\f\021\030\024\t\003\t\013\t\023\030\034", aobj3, 0);
        Lit27 = new SyntaxRules(aobj2, asyntaxrule1, 3);
        Object aobj4[] = new Object[1];
        SimpleSymbol simplesymbol2 = (SimpleSymbol)(new SimpleSymbol("primitive-set-field")).readResolve();
        Lit24 = simplesymbol2;
        aobj4[0] = simplesymbol2;
        SyntaxRule asyntaxrule2[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern2 = new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3);
        Object aobj5[] = new Object[4];
        aobj5[0] = Lit31;
        aobj5[1] = Lit32;
        aobj5[2] = (SimpleSymbol)(new SimpleSymbol("<kawa.lang.SetFieldProc>")).readResolve();
        aobj5[3] = PairWithPosition.make(Lit34, LList.Empty, "reflection.scm", 0x62017);
        asyntaxrule2[0] = new SyntaxRule(syntaxpattern2, "\001\001\001", "\021\030\004\021\030\f\021\030\024\t\003\t\013\t\023\030\034", aobj5, 0);
        Lit25 = new SyntaxRules(aobj4, asyntaxrule2, 3);
        Object aobj6[] = new Object[1];
        SimpleSymbol simplesymbol3 = (SimpleSymbol)(new SimpleSymbol("primitive-get-field")).readResolve();
        Lit22 = simplesymbol3;
        aobj6[0] = simplesymbol3;
        SyntaxRule asyntaxrule3[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern3 = new SyntaxPattern("\f\030\f\007\f\017\f\027\b", new Object[0], 3);
        Object aobj7[] = new Object[4];
        aobj7[0] = Lit31;
        aobj7[1] = Lit32;
        aobj7[2] = (SimpleSymbol)(new SimpleSymbol("<kawa.lang.GetFieldProc>")).readResolve();
        aobj7[3] = PairWithPosition.make(Lit34, LList.Empty, "reflection.scm", 0x5c017);
        asyntaxrule3[0] = new SyntaxRule(syntaxpattern3, "\001\001\001", "\021\030\004\021\030\f\021\030\024\t\003\t\013\t\023\030\034", aobj7, 0);
        Lit23 = new SyntaxRules(aobj6, asyntaxrule3, 3);
        Object aobj8[] = new Object[1];
        SimpleSymbol simplesymbol4 = (SimpleSymbol)(new SimpleSymbol("primitive-array-length")).readResolve();
        Lit20 = simplesymbol4;
        aobj8[0] = simplesymbol4;
        SyntaxRule asyntaxrule4[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern4 = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
        Object aobj9[] = new Object[3];
        aobj9[0] = Lit31;
        aobj9[1] = Lit32;
        aobj9[2] = (SimpleSymbol)(new SimpleSymbol("<gnu.kawa.reflect.ArrayLength>")).readResolve();
        asyntaxrule4[0] = new SyntaxRule(syntaxpattern4, "\001", "\021\030\004\021\030\f\021\030\024\b\003", aobj9, 0);
        Lit21 = new SyntaxRules(aobj8, asyntaxrule4, 1);
        Object aobj10[] = new Object[1];
        SimpleSymbol simplesymbol5 = (SimpleSymbol)(new SimpleSymbol("primitive-array-get")).readResolve();
        Lit18 = simplesymbol5;
        aobj10[0] = simplesymbol5;
        SyntaxRule asyntaxrule5[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern5 = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
        Object aobj11[] = new Object[3];
        aobj11[0] = Lit31;
        aobj11[1] = Lit32;
        aobj11[2] = (SimpleSymbol)(new SimpleSymbol("<gnu.kawa.reflect.ArrayGet>")).readResolve();
        asyntaxrule5[0] = new SyntaxRule(syntaxpattern5, "\001", "\021\030\004\021\030\f\021\030\024\b\003", aobj11, 0);
        Lit19 = new SyntaxRules(aobj10, asyntaxrule5, 1);
        Object aobj12[] = new Object[1];
        SimpleSymbol simplesymbol6 = (SimpleSymbol)(new SimpleSymbol("primitive-array-set")).readResolve();
        Lit16 = simplesymbol6;
        aobj12[0] = simplesymbol6;
        SyntaxRule asyntaxrule6[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern6 = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
        Object aobj13[] = new Object[3];
        aobj13[0] = Lit31;
        aobj13[1] = Lit32;
        aobj13[2] = (SimpleSymbol)(new SimpleSymbol("<gnu.kawa.reflect.ArraySet>")).readResolve();
        asyntaxrule6[0] = new SyntaxRule(syntaxpattern6, "\001", "\021\030\004\021\030\f\021\030\024\b\003", aobj13, 0);
        Lit17 = new SyntaxRules(aobj12, asyntaxrule6, 1);
        Object aobj14[] = new Object[1];
        SimpleSymbol simplesymbol7 = (SimpleSymbol)(new SimpleSymbol("primitive-array-new")).readResolve();
        Lit14 = simplesymbol7;
        aobj14[0] = simplesymbol7;
        SyntaxRule asyntaxrule7[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern7 = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
        Object aobj15[] = new Object[3];
        aobj15[0] = Lit31;
        aobj15[1] = Lit32;
        aobj15[2] = (SimpleSymbol)(new SimpleSymbol("<gnu.kawa.reflect.ArrayNew>")).readResolve();
        asyntaxrule7[0] = new SyntaxRule(syntaxpattern7, "\001", "\021\030\004\021\030\f\021\030\024\b\003", aobj15, 0);
        Lit15 = new SyntaxRules(aobj14, asyntaxrule7, 1);
        Lit13 = (SimpleSymbol)(new SimpleSymbol("record-type-field-names")).readResolve();
        Lit12 = (SimpleSymbol)(new SimpleSymbol("record-type-name")).readResolve();
        Lit11 = (SimpleSymbol)(new SimpleSymbol("record-type-descriptor")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("record-predicate")).readResolve();
        Lit9 = (SimpleSymbol)(new SimpleSymbol("record?")).readResolve();
        Lit8 = (SimpleSymbol)(new SimpleSymbol("record-modifier")).readResolve();
        Lit7 = (SimpleSymbol)(new SimpleSymbol("record-accessor")).readResolve();
        Lit6 = (SimpleSymbol)(new SimpleSymbol("record-constructor")).readResolve();
        Lit5 = (SimpleSymbol)(new SimpleSymbol("make-record-type")).readResolve();
        Object aobj16[] = new Object[4];
        aobj16[0] = (SimpleSymbol)(new SimpleSymbol("lambda")).readResolve();
        aobj16[1] = (SimpleSymbol)(new SimpleSymbol("::")).readResolve();
        aobj16[2] = Lit32;
        aobj16[3] = (SimpleSymbol)(new SimpleSymbol("as")).readResolve();
        Lit4 = new SyntaxTemplate("\001\001\003\003", "\021\030\004\031\b\035\033\021\030\f\t\013\b\021\030\024\t\013\b\025\021\030\034\t\023\b\033", aobj16, 1);
        Lit0 = (SimpleSymbol)(new SimpleSymbol("primitive-constructor")).readResolve();
        $instance = new reflection();
        SimpleSymbol simplesymbol8 = Lit0;
        reflection reflection1 = $instance;
        primitive$Mnconstructor = Macro.make(simplesymbol8, new ModuleMethod(reflection1, 2, null, 4097), $instance);
        make$Mnrecord$Mntype = new ModuleMethod(reflection1, 3, Lit5, 8194);
        record$Mnconstructor = new ModuleMethod(reflection1, 4, Lit6, 8193);
        record$Mnaccessor = new ModuleMethod(reflection1, 6, Lit7, 8194);
        record$Mnmodifier = new ModuleMethod(reflection1, 7, Lit8, 8194);
        record$Qu = new ModuleMethod(reflection1, 8, Lit9, 4097);
        record$Mnpredicate = new ModuleMethod(reflection1, 9, Lit10, 4097);
        record$Mntype$Mndescriptor = new ModuleMethod(reflection1, 10, Lit11, 4097);
        record$Mntype$Mnname = new ModuleMethod(reflection1, 11, Lit12, 4097);
        record$Mntype$Mnfield$Mnnames = new ModuleMethod(reflection1, 12, Lit13, 4097);
        primitive$Mnarray$Mnnew = Macro.make(Lit14, Lit15, $instance);
        primitive$Mnarray$Mnset = Macro.make(Lit16, Lit17, $instance);
        primitive$Mnarray$Mnget = Macro.make(Lit18, Lit19, $instance);
        primitive$Mnarray$Mnlength = Macro.make(Lit20, Lit21, $instance);
        primitive$Mnget$Mnfield = Macro.make(Lit22, Lit23, $instance);
        primitive$Mnset$Mnfield = Macro.make(Lit24, Lit25, $instance);
        primitive$Mnget$Mnstatic = Macro.make(Lit26, Lit27, $instance);
        primitive$Mnset$Mnstatic = Macro.make(Lit28, Lit29, $instance);
        subtype$Qu = new ModuleMethod(reflection1, 13, Lit30, 8194);
        $instance.run();
    }
}
