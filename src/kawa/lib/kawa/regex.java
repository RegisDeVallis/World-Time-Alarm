// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib.kawa;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.standard.Scheme;

public class regex extends ModuleBody
{
    public class frame extends ModuleBody
    {

        Object loop;
        Matcher matcher;
        Object repl;
        StringBuffer sbuf;

        public Object apply0(ModuleMethod modulemethod)
        {
            if (modulemethod.selector == 1)
            {
                return lambda1loop();
            } else
            {
                return super.apply0(modulemethod);
            }
        }

        public String lambda1loop()
        {
            if (matcher.find())
            {
                Matcher matcher1 = matcher;
                StringBuffer stringbuffer = sbuf;
                Object obj = Scheme.applyToArgs.apply2(repl, matcher.group());
                String s;
                if (obj == null)
                {
                    s = null;
                } else
                {
                    s = obj.toString();
                }
                matcher1.appendReplacement(stringbuffer, Matcher.quoteReplacement(s));
            }
            matcher.appendTail(sbuf);
            return sbuf.toString();
        }

        public int match0(ModuleMethod modulemethod, CallContext callcontext)
        {
            if (modulemethod.selector == 1)
            {
                callcontext.proc = modulemethod;
                callcontext.pc = 0;
                return 0;
            } else
            {
                return super.match0(modulemethod, callcontext);
            }
        }

        public frame()
        {
            loop = new ModuleMethod(this, 1, regex.Lit0, 0);
        }
    }


    public static final regex $instance;
    static final SimpleSymbol Lit0 = (SimpleSymbol)(new SimpleSymbol("loop")).readResolve();
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    public static final ModuleMethod regex$Mnmatch;
    public static final ModuleMethod regex$Mnmatch$Mnpositions;
    public static final ModuleMethod regex$Mnmatch$Qu;
    public static final ModuleMethod regex$Mnquote;
    public static final ModuleMethod regex$Mnreplace;
    public static final ModuleMethod regex$Mnreplace$St;
    public static final ModuleMethod regex$Mnsplit;

    public regex()
    {
        ModuleInfo.register(this);
    }

    public static boolean isRegexMatch(Object obj, CharSequence charsequence)
    {
        return isRegexMatch(obj, charsequence, 0);
    }

    public static boolean isRegexMatch(Object obj, CharSequence charsequence, int i)
    {
        return isRegexMatch(obj, charsequence, i, charsequence.length());
    }

    public static boolean isRegexMatch(Object obj, CharSequence charsequence, int i, int j)
    {
        if (obj instanceof Pattern)
        {
            Pattern pattern;
            Matcher matcher;
            Pattern pattern1;
            try
            {
                pattern1 = (Pattern)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "rex", -2, obj);
            }
            pattern = pattern1;
        } else
        {
            pattern = Pattern.compile(obj.toString());
        }
        matcher = pattern.matcher(charsequence);
        matcher.region(i, j);
        return matcher.find();
    }

    public static Object regexMatch(Object obj, CharSequence charsequence)
    {
        return regexMatch(obj, charsequence, 0);
    }

    public static Object regexMatch(Object obj, CharSequence charsequence, int i)
    {
        return regexMatch(obj, charsequence, i, charsequence.length());
    }

    public static Object regexMatch(Object obj, CharSequence charsequence, int i, int j)
    {
        if (obj instanceof Pattern)
        {
            Pattern pattern;
            Matcher matcher;
            int k;
            Object obj1;
            int l;
            Object obj2;
            gnu.lists.Pair pair;
            Pattern pattern1;
            try
            {
                pattern1 = (Pattern)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "rex", -2, obj);
            }
            pattern = pattern1;
        } else
        {
            pattern = Pattern.compile(obj.toString());
        }
        matcher = pattern.matcher(charsequence);
        matcher.region(i, j);
        if (matcher.find())
        {
            k = matcher.groupCount();
            obj1 = LList.Empty;
            do
            {
                if (k < 0)
                {
                    return obj1;
                }
                l = matcher.start(k);
                if (l < 0)
                {
                    obj2 = Boolean.FALSE;
                } else
                {
                    obj2 = charsequence.subSequence(l, matcher.end(k));
                }
                pair = lists.cons(obj2, obj1);
                k--;
                obj1 = pair;
            } while (true);
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static Object regexMatchPositions(Object obj, CharSequence charsequence)
    {
        return regexMatchPositions(obj, charsequence, 0);
    }

    public static Object regexMatchPositions(Object obj, CharSequence charsequence, int i)
    {
        return regexMatchPositions(obj, charsequence, i, charsequence.length());
    }

    public static Object regexMatchPositions(Object obj, CharSequence charsequence, int i, int j)
    {
        if (obj instanceof Pattern)
        {
            Pattern pattern;
            Matcher matcher;
            int k;
            Object obj1;
            int l;
            Object obj2;
            gnu.lists.Pair pair;
            Pattern pattern1;
            try
            {
                pattern1 = (Pattern)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "rex", -2, obj);
            }
            pattern = pattern1;
        } else
        {
            pattern = Pattern.compile(obj.toString());
        }
        matcher = pattern.matcher(charsequence);
        matcher.region(i, j);
        if (matcher.find())
        {
            k = matcher.groupCount();
            obj1 = LList.Empty;
            do
            {
                if (k < 0)
                {
                    return obj1;
                }
                l = matcher.start(k);
                if (l < 0)
                {
                    obj2 = Boolean.FALSE;
                } else
                {
                    obj2 = lists.cons(Integer.valueOf(l), Integer.valueOf(matcher.end(k)));
                }
                pair = lists.cons(obj2, obj1);
                k--;
                obj1 = pair;
            } while (true);
        } else
        {
            return Boolean.FALSE;
        }
    }

    public static String regexQuote(CharSequence charsequence)
    {
        String s;
        if (charsequence == null)
        {
            s = null;
        } else
        {
            s = charsequence.toString();
        }
        return Pattern.quote(s);
    }

    public static CharSequence regexReplace(Object obj, CharSequence charsequence, Object obj1)
    {
        if (obj instanceof Pattern)
        {
            Pattern pattern;
            Matcher matcher;
            StringBuffer stringbuffer;
            String s;
            Object obj2;
            String s1;
            Pattern pattern1;
            try
            {
                pattern1 = (Pattern)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "rex", -2, obj);
            }
            pattern = pattern1;
        } else
        {
            pattern = Pattern.compile(obj.toString());
        }
        matcher = pattern.matcher(charsequence);
        if (!matcher.find()) goto _L2; else goto _L1
_L1:
        stringbuffer = new StringBuffer();
        if (!misc.isProcedure(obj1)) goto _L4; else goto _L3
_L3:
        obj2 = Scheme.applyToArgs.apply2(obj1, matcher.group());
        s1 = null;
        if (obj2 != null)
        {
            s1 = obj2.toString();
        }
        s = Matcher.quoteReplacement(s1);
_L6:
        matcher.appendReplacement(stringbuffer, s);
        matcher.appendTail(stringbuffer);
        charsequence = stringbuffer.toString();
_L2:
        return charsequence;
_L4:
        s = null;
        if (obj1 != null)
        {
            s = obj1.toString();
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public static CharSequence regexReplace$St(Object obj, CharSequence charsequence, Object obj1)
    {
        frame frame1 = new frame();
        frame1.repl = obj1;
        if (obj instanceof Pattern)
        {
            Pattern pattern;
            Matcher matcher;
            Object obj2;
            String s;
            Pattern pattern1;
            try
            {
                pattern1 = (Pattern)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "rex", -2, obj);
            }
            pattern = pattern1;
        } else
        {
            pattern = Pattern.compile(obj.toString());
        }
        frame1.matcher = pattern.matcher(charsequence);
        frame1.sbuf = new StringBuffer();
        if (misc.isProcedure(frame1.repl))
        {
            frame1.loop = frame1.loop;
            return frame1.lambda1loop();
        }
        matcher = frame1.matcher;
        obj2 = frame1.repl;
        if (obj2 == null)
        {
            s = null;
        } else
        {
            s = obj2.toString();
        }
        return matcher.replaceAll(s);
    }

    public static LList regexSplit(Object obj, CharSequence charsequence)
    {
        if (obj instanceof Pattern)
        {
            Pattern pattern;
            Pattern pattern1;
            try
            {
                pattern1 = (Pattern)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "rex", -2, obj);
            }
            pattern = pattern1;
        } else
        {
            pattern = Pattern.compile(obj.toString());
        }
        return LList.makeList(pattern.split(charsequence, -1), 0);
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        if (modulemethod.selector == 2)
        {
            CharSequence charsequence;
            try
            {
                charsequence = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "regex-quote", 1, obj);
            }
            return regexQuote(charsequence);
        } else
        {
            return super.apply1(modulemethod, obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 3: // '\003'
            CharSequence charsequence;
            CharSequence charsequence1;
            CharSequence charsequence2;
            CharSequence charsequence3;
            try
            {
                charsequence3 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "regex-match?", 2, obj1);
            }
            if (isRegexMatch(obj, charsequence3))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 6: // '\006'
            try
            {
                charsequence2 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "regex-match", 2, obj1);
            }
            return regexMatch(obj, charsequence2);

        case 9: // '\t'
            try
            {
                charsequence1 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "regex-match-positions", 2, obj1);
            }
            return regexMatchPositions(obj, charsequence1);

        case 12: // '\f'
            break;
        }
        try
        {
            charsequence = (CharSequence)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "regex-split", 2, obj1);
        }
        return regexSplit(obj, charsequence);
    }

    public Object apply3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply3(modulemethod, obj, obj1, obj2);

        case 3: // '\003'
            CharSequence charsequence;
            CharSequence charsequence1;
            CharSequence charsequence2;
            int i;
            CharSequence charsequence3;
            int j;
            CharSequence charsequence4;
            int k;
            try
            {
                charsequence4 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "regex-match?", 2, obj1);
            }
            try
            {
                k = ((Number)obj2).intValue();
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "regex-match?", 3, obj2);
            }
            if (isRegexMatch(obj, charsequence4, k))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 6: // '\006'
            try
            {
                charsequence3 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "regex-match", 2, obj1);
            }
            try
            {
                j = ((Number)obj2).intValue();
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "regex-match", 3, obj2);
            }
            return regexMatch(obj, charsequence3, j);

        case 9: // '\t'
            try
            {
                charsequence2 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "regex-match-positions", 2, obj1);
            }
            try
            {
                i = ((Number)obj2).intValue();
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "regex-match-positions", 3, obj2);
            }
            return regexMatchPositions(obj, charsequence2, i);

        case 13: // '\r'
            try
            {
                charsequence1 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "regex-replace", 2, obj1);
            }
            return regexReplace(obj, charsequence1, obj2);

        case 14: // '\016'
            break;
        }
        try
        {
            charsequence = (CharSequence)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "regex-replace*", 2, obj1);
        }
        return regexReplace$St(obj, charsequence, obj2);
    }

    public Object apply4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply4(modulemethod, obj, obj1, obj2, obj3);

        case 3: // '\003'
            CharSequence charsequence;
            int i;
            int j;
            CharSequence charsequence1;
            int k;
            int l;
            CharSequence charsequence2;
            int i1;
            int j1;
            try
            {
                charsequence2 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "regex-match?", 2, obj1);
            }
            try
            {
                i1 = ((Number)obj2).intValue();
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "regex-match?", 3, obj2);
            }
            try
            {
                j1 = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "regex-match?", 4, obj3);
            }
            if (isRegexMatch(obj, charsequence2, i1, j1))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 6: // '\006'
            try
            {
                charsequence1 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "regex-match", 2, obj1);
            }
            try
            {
                k = ((Number)obj2).intValue();
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "regex-match", 3, obj2);
            }
            try
            {
                l = ((Number)obj3).intValue();
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "regex-match", 4, obj3);
            }
            return regexMatch(obj, charsequence1, k, l);

        case 9: // '\t'
            break;
        }
        try
        {
            charsequence = (CharSequence)obj1;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "regex-match-positions", 2, obj1);
        }
        try
        {
            i = ((Number)obj2).intValue();
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "regex-match-positions", 3, obj2);
        }
        try
        {
            j = ((Number)obj3).intValue();
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "regex-match-positions", 4, obj3);
        }
        return regexMatchPositions(obj, charsequence, i, j);
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        if (modulemethod.selector == 2)
        {
            if (obj instanceof CharSequence)
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            } else
            {
                return 0xfff40001;
            }
        } else
        {
            return super.match1(modulemethod, obj, callcontext);
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match2(modulemethod, obj, obj1, callcontext);

        case 12: // '\f'
            callcontext.value1 = obj;
            if (obj1 instanceof CharSequence)
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 9: // '\t'
            callcontext.value1 = obj;
            if (obj1 instanceof CharSequence)
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 6: // '\006'
            callcontext.value1 = obj;
            if (obj1 instanceof CharSequence)
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 3: // '\003'
            callcontext.value1 = obj;
            break;
        }
        if (obj1 instanceof CharSequence)
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

    public int match3(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match3(modulemethod, obj, obj1, obj2, callcontext);

        case 14: // '\016'
            callcontext.value1 = obj;
            if (obj1 instanceof CharSequence)
            {
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 13: // '\r'
            callcontext.value1 = obj;
            if (obj1 instanceof CharSequence)
            {
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 9: // '\t'
            callcontext.value1 = obj;
            if (obj1 instanceof CharSequence)
            {
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 6: // '\006'
            callcontext.value1 = obj;
            if (obj1 instanceof CharSequence)
            {
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.proc = modulemethod;
                callcontext.pc = 3;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 3: // '\003'
            callcontext.value1 = obj;
            break;
        }
        if (obj1 instanceof CharSequence)
        {
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.proc = modulemethod;
            callcontext.pc = 3;
            return 0;
        } else
        {
            return 0xfff40002;
        }
    }

    public int match4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match4(modulemethod, obj, obj1, obj2, obj3, callcontext);

        case 9: // '\t'
            callcontext.value1 = obj;
            if (obj1 instanceof CharSequence)
            {
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.value4 = obj3;
                callcontext.proc = modulemethod;
                callcontext.pc = 4;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 6: // '\006'
            callcontext.value1 = obj;
            if (obj1 instanceof CharSequence)
            {
                callcontext.value2 = obj1;
                callcontext.value3 = obj2;
                callcontext.value4 = obj3;
                callcontext.proc = modulemethod;
                callcontext.pc = 4;
                return 0;
            } else
            {
                return 0xfff40002;
            }

        case 3: // '\003'
            callcontext.value1 = obj;
            break;
        }
        if (obj1 instanceof CharSequence)
        {
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.value4 = obj3;
            callcontext.proc = modulemethod;
            callcontext.pc = 4;
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
        Lit7 = (SimpleSymbol)(new SimpleSymbol("regex-replace*")).readResolve();
        Lit6 = (SimpleSymbol)(new SimpleSymbol("regex-replace")).readResolve();
        Lit5 = (SimpleSymbol)(new SimpleSymbol("regex-split")).readResolve();
        Lit4 = (SimpleSymbol)(new SimpleSymbol("regex-match-positions")).readResolve();
        Lit3 = (SimpleSymbol)(new SimpleSymbol("regex-match")).readResolve();
        Lit2 = (SimpleSymbol)(new SimpleSymbol("regex-match?")).readResolve();
        Lit1 = (SimpleSymbol)(new SimpleSymbol("regex-quote")).readResolve();
        $instance = new regex();
        regex regex1 = $instance;
        regex$Mnquote = new ModuleMethod(regex1, 2, Lit1, 4097);
        regex$Mnmatch$Qu = new ModuleMethod(regex1, 3, Lit2, 16386);
        regex$Mnmatch = new ModuleMethod(regex1, 6, Lit3, 16386);
        regex$Mnmatch$Mnpositions = new ModuleMethod(regex1, 9, Lit4, 16386);
        regex$Mnsplit = new ModuleMethod(regex1, 12, Lit5, 8194);
        regex$Mnreplace = new ModuleMethod(regex1, 13, Lit6, 12291);
        regex$Mnreplace$St = new ModuleMethod(regex1, 14, Lit7, 12291);
        $instance.run();
    }
}
