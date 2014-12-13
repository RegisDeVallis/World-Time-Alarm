// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package kawa.lib.rnrs;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.UnicodeUtils;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;
import gnu.text.Char;
import java.util.Locale;
import kawa.lib.misc;

public class unicode extends ModuleBody
{

    public static final unicode $instance;
    static final SimpleSymbol Lit0;
    static final SimpleSymbol Lit1;
    static final SimpleSymbol Lit10;
    static final SimpleSymbol Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2;
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SimpleSymbol Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit5;
    static final SimpleSymbol Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod char$Mnalphabetic$Qu;
    public static final ModuleMethod char$Mnci$Eq$Qu;
    public static final ModuleMethod char$Mnci$Gr$Eq$Qu;
    public static final ModuleMethod char$Mnci$Gr$Qu;
    public static final ModuleMethod char$Mnci$Ls$Eq$Qu;
    public static final ModuleMethod char$Mnci$Ls$Qu;
    public static final ModuleMethod char$Mndowncase;
    public static final ModuleMethod char$Mnfoldcase;
    public static final ModuleMethod char$Mngeneral$Mncategory;
    public static final ModuleMethod char$Mnlower$Mncase$Qu;
    public static final ModuleMethod char$Mnnumeric$Qu;
    public static final ModuleMethod char$Mntitle$Mncase$Qu;
    public static final ModuleMethod char$Mntitlecase;
    public static final ModuleMethod char$Mnupcase;
    public static final ModuleMethod char$Mnupper$Mncase$Qu;
    public static final ModuleMethod char$Mnwhitespace$Qu;
    public static final ModuleMethod string$Mnci$Eq$Qu;
    public static final ModuleMethod string$Mnci$Gr$Eq$Qu;
    public static final ModuleMethod string$Mnci$Gr$Qu;
    public static final ModuleMethod string$Mnci$Ls$Eq$Qu;
    public static final ModuleMethod string$Mnci$Ls$Qu;
    public static final ModuleMethod string$Mndowncase;
    public static final ModuleMethod string$Mnfoldcase;
    public static final ModuleMethod string$Mnnormalize$Mnnfc;
    public static final ModuleMethod string$Mnnormalize$Mnnfd;
    public static final ModuleMethod string$Mnnormalize$Mnnfkc;
    public static final ModuleMethod string$Mnnormalize$Mnnfkd;
    public static final ModuleMethod string$Mntitlecase;
    public static final ModuleMethod string$Mnupcase;

    public unicode()
    {
        ModuleInfo.register(this);
    }

    public static Char charDowncase(Char char1)
    {
        return Char.make(Character.toLowerCase(char1.intValue()));
    }

    public static Char charFoldcase(Char char1)
    {
        int i = char1.intValue();
        boolean flag;
        if (i == 304)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag ? flag : i == 305)
        {
            return char1;
        } else
        {
            return Char.make(Character.toLowerCase(Character.toUpperCase(i)));
        }
    }

    public static Symbol charGeneralCategory(Char char1)
    {
        return UnicodeUtils.generalCategory(char1.intValue());
    }

    public static Char charTitlecase(Char char1)
    {
        return Char.make(Character.toTitleCase(char1.intValue()));
    }

    public static Char charUpcase(Char char1)
    {
        return Char.make(Character.toUpperCase(char1.intValue()));
    }

    public static boolean isCharAlphabetic(Char char1)
    {
        return Character.isLetter(char1.intValue());
    }

    public static boolean isCharCi$Eq(Char char1, Char char2)
    {
        return Character.toUpperCase(char1.intValue()) == Character.toUpperCase(char2.intValue());
    }

    public static boolean isCharCi$Gr(Char char1, Char char2)
    {
        return Character.toUpperCase(char1.intValue()) > Character.toUpperCase(char2.intValue());
    }

    public static boolean isCharCi$Gr$Eq(Char char1, Char char2)
    {
        return Character.toUpperCase(char1.intValue()) >= Character.toUpperCase(char2.intValue());
    }

    public static boolean isCharCi$Ls(Char char1, Char char2)
    {
        return Character.toUpperCase(char1.intValue()) < Character.toUpperCase(char2.intValue());
    }

    public static boolean isCharCi$Ls$Eq(Char char1, Char char2)
    {
        return Character.toUpperCase(char1.intValue()) <= Character.toUpperCase(char2.intValue());
    }

    public static boolean isCharLowerCase(Char char1)
    {
        return Character.isLowerCase(char1.intValue());
    }

    public static boolean isCharNumeric(Char char1)
    {
        return Character.isDigit(char1.intValue());
    }

    public static boolean isCharTitleCase(Char char1)
    {
        return Character.isTitleCase(char1.intValue());
    }

    public static boolean isCharUpperCase(Char char1)
    {
        return Character.isUpperCase(char1.intValue());
    }

    public static boolean isCharWhitespace(Char char1)
    {
        return UnicodeUtils.isWhitespace(char1.intValue());
    }

    public static boolean isStringCi$Eq(CharSequence charsequence, CharSequence charsequence1)
    {
        return UnicodeUtils.foldCase(charsequence).equals(UnicodeUtils.foldCase(charsequence1));
    }

    public static boolean isStringCi$Gr(CharSequence charsequence, CharSequence charsequence1)
    {
        return UnicodeUtils.foldCase(charsequence).compareTo(UnicodeUtils.foldCase(charsequence1)) > 0;
    }

    public static boolean isStringCi$Gr$Eq(CharSequence charsequence, CharSequence charsequence1)
    {
        return UnicodeUtils.foldCase(charsequence).compareTo(UnicodeUtils.foldCase(charsequence1)) >= 0;
    }

    public static boolean isStringCi$Ls(CharSequence charsequence, CharSequence charsequence1)
    {
        return UnicodeUtils.foldCase(charsequence).compareTo(UnicodeUtils.foldCase(charsequence1)) < 0;
    }

    public static boolean isStringCi$Ls$Eq(CharSequence charsequence, CharSequence charsequence1)
    {
        return UnicodeUtils.foldCase(charsequence).compareTo(UnicodeUtils.foldCase(charsequence1)) <= 0;
    }

    public static CharSequence stringDowncase(CharSequence charsequence)
    {
        return new FString(charsequence.toString().toLowerCase(Locale.ENGLISH));
    }

    public static CharSequence stringFoldcase(CharSequence charsequence)
    {
        return new FString(UnicodeUtils.foldCase(charsequence));
    }

    public static CharSequence stringNormalizeNfc(CharSequence charsequence)
    {
        return (CharSequence)misc.error$V("unicode string normalization not available", new Object[0]);
    }

    public static CharSequence stringNormalizeNfd(CharSequence charsequence)
    {
        return (CharSequence)misc.error$V("unicode string normalization not available", new Object[0]);
    }

    public static CharSequence stringNormalizeNfkc(CharSequence charsequence)
    {
        return (CharSequence)misc.error$V("unicode string normalization not available", new Object[0]);
    }

    public static CharSequence stringNormalizeNfkd(CharSequence charsequence)
    {
        return (CharSequence)misc.error$V("unicode string normalization not available", new Object[0]);
    }

    public static CharSequence stringTitlecase(CharSequence charsequence)
    {
        String s;
        if (charsequence == null)
        {
            s = null;
        } else
        {
            s = charsequence.toString();
        }
        return new FString(UnicodeUtils.capitalize(s));
    }

    public static CharSequence stringUpcase(CharSequence charsequence)
    {
        return new FString(charsequence.toString().toUpperCase(Locale.ENGLISH));
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 21: // '\025'
        case 22: // '\026'
        case 23: // '\027'
        case 24: // '\030'
        case 25: // '\031'
        default:
            return super.apply1(modulemethod, obj);

        case 1: // '\001'
            CharSequence charsequence;
            CharSequence charsequence1;
            CharSequence charsequence2;
            CharSequence charsequence3;
            CharSequence charsequence4;
            CharSequence charsequence5;
            CharSequence charsequence6;
            CharSequence charsequence7;
            Char char1;
            Char char2;
            Char char3;
            Char char4;
            Char char5;
            Char char6;
            Char char7;
            Char char8;
            Char char9;
            Char char10;
            Char char11;
            try
            {
                char11 = (Char)obj;
            }
            catch (ClassCastException classcastexception18)
            {
                throw new WrongType(classcastexception18, "char-upcase", 1, obj);
            }
            return charUpcase(char11);

        case 2: // '\002'
            try
            {
                char10 = (Char)obj;
            }
            catch (ClassCastException classcastexception17)
            {
                throw new WrongType(classcastexception17, "char-downcase", 1, obj);
            }
            return charDowncase(char10);

        case 3: // '\003'
            try
            {
                char9 = (Char)obj;
            }
            catch (ClassCastException classcastexception16)
            {
                throw new WrongType(classcastexception16, "char-titlecase", 1, obj);
            }
            return charTitlecase(char9);

        case 4: // '\004'
            try
            {
                char8 = (Char)obj;
            }
            catch (ClassCastException classcastexception15)
            {
                throw new WrongType(classcastexception15, "char-alphabetic?", 1, obj);
            }
            if (isCharAlphabetic(char8))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 5: // '\005'
            try
            {
                char7 = (Char)obj;
            }
            catch (ClassCastException classcastexception14)
            {
                throw new WrongType(classcastexception14, "char-numeric?", 1, obj);
            }
            if (isCharNumeric(char7))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 6: // '\006'
            try
            {
                char6 = (Char)obj;
            }
            catch (ClassCastException classcastexception13)
            {
                throw new WrongType(classcastexception13, "char-whitespace?", 1, obj);
            }
            if (isCharWhitespace(char6))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 7: // '\007'
            try
            {
                char5 = (Char)obj;
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "char-upper-case?", 1, obj);
            }
            if (isCharUpperCase(char5))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 8: // '\b'
            try
            {
                char4 = (Char)obj;
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "char-lower-case?", 1, obj);
            }
            if (isCharLowerCase(char4))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 9: // '\t'
            try
            {
                char3 = (Char)obj;
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "char-title-case?", 1, obj);
            }
            if (isCharTitleCase(char3))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 10: // '\n'
            try
            {
                char2 = (Char)obj;
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "char-foldcase", 1, obj);
            }
            return charFoldcase(char2);

        case 16: // '\020'
            try
            {
                char1 = (Char)obj;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "char-general-category", 1, obj);
            }
            return charGeneralCategory(char1);

        case 17: // '\021'
            try
            {
                charsequence7 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "string-upcase", 1, obj);
            }
            return stringUpcase(charsequence7);

        case 18: // '\022'
            try
            {
                charsequence6 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "string-downcase", 1, obj);
            }
            return stringDowncase(charsequence6);

        case 19: // '\023'
            try
            {
                charsequence5 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "string-titlecase", 1, obj);
            }
            return stringTitlecase(charsequence5);

        case 20: // '\024'
            try
            {
                charsequence4 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "string-foldcase", 1, obj);
            }
            return stringFoldcase(charsequence4);

        case 26: // '\032'
            try
            {
                charsequence3 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "string-normalize-nfd", 1, obj);
            }
            return stringNormalizeNfd(charsequence3);

        case 27: // '\033'
            try
            {
                charsequence2 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-normalize-nfkd", 1, obj);
            }
            return stringNormalizeNfkd(charsequence2);

        case 28: // '\034'
            try
            {
                charsequence1 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "string-normalize-nfc", 1, obj);
            }
            return stringNormalizeNfc(charsequence1);

        case 29: // '\035'
            break;
        }
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-normalize-nfkc", 1, obj);
        }
        return stringNormalizeNfkc(charsequence);
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        case 16: // '\020'
        case 17: // '\021'
        case 18: // '\022'
        case 19: // '\023'
        case 20: // '\024'
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 11: // '\013'
            CharSequence charsequence;
            CharSequence charsequence1;
            CharSequence charsequence2;
            CharSequence charsequence3;
            CharSequence charsequence4;
            CharSequence charsequence5;
            CharSequence charsequence6;
            CharSequence charsequence7;
            CharSequence charsequence8;
            CharSequence charsequence9;
            Char char1;
            Char char2;
            Char char3;
            Char char4;
            Char char5;
            Char char6;
            Char char7;
            Char char8;
            Char char9;
            Char char10;
            try
            {
                char9 = (Char)obj;
            }
            catch (ClassCastException classcastexception18)
            {
                throw new WrongType(classcastexception18, "char-ci=?", 1, obj);
            }
            try
            {
                char10 = (Char)obj1;
            }
            catch (ClassCastException classcastexception19)
            {
                throw new WrongType(classcastexception19, "char-ci=?", 2, obj1);
            }
            if (isCharCi$Eq(char9, char10))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 12: // '\f'
            try
            {
                char7 = (Char)obj;
            }
            catch (ClassCastException classcastexception16)
            {
                throw new WrongType(classcastexception16, "char-ci<?", 1, obj);
            }
            try
            {
                char8 = (Char)obj1;
            }
            catch (ClassCastException classcastexception17)
            {
                throw new WrongType(classcastexception17, "char-ci<?", 2, obj1);
            }
            if (isCharCi$Ls(char7, char8))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 13: // '\r'
            try
            {
                char5 = (Char)obj;
            }
            catch (ClassCastException classcastexception14)
            {
                throw new WrongType(classcastexception14, "char-ci>?", 1, obj);
            }
            try
            {
                char6 = (Char)obj1;
            }
            catch (ClassCastException classcastexception15)
            {
                throw new WrongType(classcastexception15, "char-ci>?", 2, obj1);
            }
            if (isCharCi$Gr(char5, char6))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 14: // '\016'
            try
            {
                char3 = (Char)obj;
            }
            catch (ClassCastException classcastexception12)
            {
                throw new WrongType(classcastexception12, "char-ci<=?", 1, obj);
            }
            try
            {
                char4 = (Char)obj1;
            }
            catch (ClassCastException classcastexception13)
            {
                throw new WrongType(classcastexception13, "char-ci<=?", 2, obj1);
            }
            if (isCharCi$Ls$Eq(char3, char4))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 15: // '\017'
            try
            {
                char1 = (Char)obj;
            }
            catch (ClassCastException classcastexception10)
            {
                throw new WrongType(classcastexception10, "char-ci>=?", 1, obj);
            }
            try
            {
                char2 = (Char)obj1;
            }
            catch (ClassCastException classcastexception11)
            {
                throw new WrongType(classcastexception11, "char-ci>=?", 2, obj1);
            }
            if (isCharCi$Gr$Eq(char1, char2))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 21: // '\025'
            try
            {
                charsequence8 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception8)
            {
                throw new WrongType(classcastexception8, "string-ci=?", 1, obj);
            }
            try
            {
                charsequence9 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception9)
            {
                throw new WrongType(classcastexception9, "string-ci=?", 2, obj1);
            }
            if (isStringCi$Eq(charsequence8, charsequence9))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 22: // '\026'
            try
            {
                charsequence6 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception6)
            {
                throw new WrongType(classcastexception6, "string-ci<?", 1, obj);
            }
            try
            {
                charsequence7 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception7)
            {
                throw new WrongType(classcastexception7, "string-ci<?", 2, obj1);
            }
            if (isStringCi$Ls(charsequence6, charsequence7))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 23: // '\027'
            try
            {
                charsequence4 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception4)
            {
                throw new WrongType(classcastexception4, "string-ci>?", 1, obj);
            }
            try
            {
                charsequence5 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception5)
            {
                throw new WrongType(classcastexception5, "string-ci>?", 2, obj1);
            }
            if (isStringCi$Gr(charsequence4, charsequence5))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 24: // '\030'
            try
            {
                charsequence2 = (CharSequence)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "string-ci<=?", 1, obj);
            }
            try
            {
                charsequence3 = (CharSequence)obj1;
            }
            catch (ClassCastException classcastexception3)
            {
                throw new WrongType(classcastexception3, "string-ci<=?", 2, obj1);
            }
            if (isStringCi$Ls$Eq(charsequence2, charsequence3))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 25: // '\031'
            break;
        }
        try
        {
            charsequence = (CharSequence)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "string-ci>=?", 1, obj);
        }
        try
        {
            charsequence1 = (CharSequence)obj1;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "string-ci>=?", 2, obj1);
        }
        if (isStringCi$Gr$Eq(charsequence, charsequence1))
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
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        case 14: // '\016'
        case 15: // '\017'
        case 21: // '\025'
        case 22: // '\026'
        case 23: // '\027'
        case 24: // '\030'
        case 25: // '\031'
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 29: // '\035'
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

        case 28: // '\034'
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

        case 27: // '\033'
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

        case 26: // '\032'
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

        case 20: // '\024'
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

        case 19: // '\023'
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

        case 18: // '\022'
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

        case 17: // '\021'
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

        case 16: // '\020'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 10: // '\n'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 9: // '\t'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 8: // '\b'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 7: // '\007'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 6: // '\006'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 5: // '\005'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 4: // '\004'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 3: // '\003'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 2: // '\002'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 1: // '\001'
            break;
        }
        if (!(obj instanceof Char))
        {
            return 0xfff40001;
        } else
        {
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
        case 16: // '\020'
        case 17: // '\021'
        case 18: // '\022'
        case 19: // '\023'
        case 20: // '\024'
        default:
            return super.match2(modulemethod, obj, obj1, callcontext);

        case 25: // '\031'
            if (obj instanceof CharSequence)
            {
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
            } else
            {
                return 0xfff40001;
            }

        case 24: // '\030'
            if (obj instanceof CharSequence)
            {
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
            } else
            {
                return 0xfff40001;
            }

        case 23: // '\027'
            if (obj instanceof CharSequence)
            {
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
            } else
            {
                return 0xfff40001;
            }

        case 22: // '\026'
            if (obj instanceof CharSequence)
            {
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
            } else
            {
                return 0xfff40001;
            }

        case 21: // '\025'
            if (obj instanceof CharSequence)
            {
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
            } else
            {
                return 0xfff40001;
            }

        case 15: // '\017'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            }
            callcontext.value1 = obj;
            if (!(obj1 instanceof Char))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 14: // '\016'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            }
            callcontext.value1 = obj;
            if (!(obj1 instanceof Char))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 13: // '\r'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            }
            callcontext.value1 = obj;
            if (!(obj1 instanceof Char))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 12: // '\f'
            if (!(obj instanceof Char))
            {
                return 0xfff40001;
            }
            callcontext.value1 = obj;
            if (!(obj1 instanceof Char))
            {
                return 0xfff40002;
            } else
            {
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 11: // '\013'
            break;
        }
        if (!(obj instanceof Char))
        {
            return 0xfff40001;
        }
        callcontext.value1 = obj;
        if (!(obj1 instanceof Char))
        {
            return 0xfff40002;
        } else
        {
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
        Lit28 = (SimpleSymbol)(new SimpleSymbol("string-normalize-nfkc")).readResolve();
        Lit27 = (SimpleSymbol)(new SimpleSymbol("string-normalize-nfc")).readResolve();
        Lit26 = (SimpleSymbol)(new SimpleSymbol("string-normalize-nfkd")).readResolve();
        Lit25 = (SimpleSymbol)(new SimpleSymbol("string-normalize-nfd")).readResolve();
        Lit24 = (SimpleSymbol)(new SimpleSymbol("string-ci>=?")).readResolve();
        Lit23 = (SimpleSymbol)(new SimpleSymbol("string-ci<=?")).readResolve();
        Lit22 = (SimpleSymbol)(new SimpleSymbol("string-ci>?")).readResolve();
        Lit21 = (SimpleSymbol)(new SimpleSymbol("string-ci<?")).readResolve();
        Lit20 = (SimpleSymbol)(new SimpleSymbol("string-ci=?")).readResolve();
        Lit19 = (SimpleSymbol)(new SimpleSymbol("string-foldcase")).readResolve();
        Lit18 = (SimpleSymbol)(new SimpleSymbol("string-titlecase")).readResolve();
        Lit17 = (SimpleSymbol)(new SimpleSymbol("string-downcase")).readResolve();
        Lit16 = (SimpleSymbol)(new SimpleSymbol("string-upcase")).readResolve();
        Lit15 = (SimpleSymbol)(new SimpleSymbol("char-general-category")).readResolve();
        Lit14 = (SimpleSymbol)(new SimpleSymbol("char-ci>=?")).readResolve();
        Lit13 = (SimpleSymbol)(new SimpleSymbol("char-ci<=?")).readResolve();
        Lit12 = (SimpleSymbol)(new SimpleSymbol("char-ci>?")).readResolve();
        Lit11 = (SimpleSymbol)(new SimpleSymbol("char-ci<?")).readResolve();
        Lit10 = (SimpleSymbol)(new SimpleSymbol("char-ci=?")).readResolve();
        Lit9 = (SimpleSymbol)(new SimpleSymbol("char-foldcase")).readResolve();
        Lit8 = (SimpleSymbol)(new SimpleSymbol("char-title-case?")).readResolve();
        Lit7 = (SimpleSymbol)(new SimpleSymbol("char-lower-case?")).readResolve();
        Lit6 = (SimpleSymbol)(new SimpleSymbol("char-upper-case?")).readResolve();
        Lit5 = (SimpleSymbol)(new SimpleSymbol("char-whitespace?")).readResolve();
        Lit4 = (SimpleSymbol)(new SimpleSymbol("char-numeric?")).readResolve();
        Lit3 = (SimpleSymbol)(new SimpleSymbol("char-alphabetic?")).readResolve();
        Lit2 = (SimpleSymbol)(new SimpleSymbol("char-titlecase")).readResolve();
        Lit1 = (SimpleSymbol)(new SimpleSymbol("char-downcase")).readResolve();
        Lit0 = (SimpleSymbol)(new SimpleSymbol("char-upcase")).readResolve();
        $instance = new unicode();
        unicode unicode1 = $instance;
        char$Mnupcase = new ModuleMethod(unicode1, 1, Lit0, 4097);
        char$Mndowncase = new ModuleMethod(unicode1, 2, Lit1, 4097);
        char$Mntitlecase = new ModuleMethod(unicode1, 3, Lit2, 4097);
        char$Mnalphabetic$Qu = new ModuleMethod(unicode1, 4, Lit3, 4097);
        char$Mnnumeric$Qu = new ModuleMethod(unicode1, 5, Lit4, 4097);
        char$Mnwhitespace$Qu = new ModuleMethod(unicode1, 6, Lit5, 4097);
        char$Mnupper$Mncase$Qu = new ModuleMethod(unicode1, 7, Lit6, 4097);
        char$Mnlower$Mncase$Qu = new ModuleMethod(unicode1, 8, Lit7, 4097);
        char$Mntitle$Mncase$Qu = new ModuleMethod(unicode1, 9, Lit8, 4097);
        char$Mnfoldcase = new ModuleMethod(unicode1, 10, Lit9, 4097);
        char$Mnci$Eq$Qu = new ModuleMethod(unicode1, 11, Lit10, 8194);
        char$Mnci$Ls$Qu = new ModuleMethod(unicode1, 12, Lit11, 8194);
        char$Mnci$Gr$Qu = new ModuleMethod(unicode1, 13, Lit12, 8194);
        char$Mnci$Ls$Eq$Qu = new ModuleMethod(unicode1, 14, Lit13, 8194);
        char$Mnci$Gr$Eq$Qu = new ModuleMethod(unicode1, 15, Lit14, 8194);
        char$Mngeneral$Mncategory = new ModuleMethod(unicode1, 16, Lit15, 4097);
        string$Mnupcase = new ModuleMethod(unicode1, 17, Lit16, 4097);
        string$Mndowncase = new ModuleMethod(unicode1, 18, Lit17, 4097);
        string$Mntitlecase = new ModuleMethod(unicode1, 19, Lit18, 4097);
        string$Mnfoldcase = new ModuleMethod(unicode1, 20, Lit19, 4097);
        string$Mnci$Eq$Qu = new ModuleMethod(unicode1, 21, Lit20, 8194);
        string$Mnci$Ls$Qu = new ModuleMethod(unicode1, 22, Lit21, 8194);
        string$Mnci$Gr$Qu = new ModuleMethod(unicode1, 23, Lit22, 8194);
        string$Mnci$Ls$Eq$Qu = new ModuleMethod(unicode1, 24, Lit23, 8194);
        string$Mnci$Gr$Eq$Qu = new ModuleMethod(unicode1, 25, Lit24, 8194);
        string$Mnnormalize$Mnnfd = new ModuleMethod(unicode1, 26, Lit25, 4097);
        string$Mnnormalize$Mnnfkd = new ModuleMethod(unicode1, 27, Lit26, 4097);
        string$Mnnormalize$Mnnfc = new ModuleMethod(unicode1, 28, Lit27, 4097);
        string$Mnnormalize$Mnnfkc = new ModuleMethod(unicode1, 29, Lit28, 4097);
        $instance.run();
    }
}
