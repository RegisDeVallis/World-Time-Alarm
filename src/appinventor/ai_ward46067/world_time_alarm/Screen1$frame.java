// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package appinventor.ai_ward46067.world_time_alarm;

import com.google.appinventor.components.runtime.Component;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.mapping.CallContext;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;

// Referenced classes of package appinventor.ai_ward46067.world_time_alarm:
//            Screen1

public class  extends ModuleBody
{

    Screen1 $main;

    public Object apply0(ModuleMethod modulemethod)
    {
        switch (modulemethod.selector)
        {
        case 92: // '\\'
        default:
            return super.apply0(modulemethod);

        case 15: // '\017'
            return Screen1.lambda2();

        case 16: // '\020'
            $main.$define();
            return Values.empty;

        case 17: // '\021'
            return Screen1.lambda3();

        case 18: // '\022'
            return Screen1.lambda5();

        case 19: // '\023'
            return Screen1.lambda4();

        case 20: // '\024'
            return Screen1.lambda6();

        case 21: // '\025'
            return Screen1.lambda8();

        case 22: // '\026'
            return Screen1.lambda7();

        case 23: // '\027'
            return Screen1.lambda9();

        case 24: // '\030'
            return Screen1.lambda11();

        case 25: // '\031'
            return Screen1.lambda10();

        case 26: // '\032'
            return Screen1.lambda12();

        case 27: // '\033'
            return Screen1.lambda13();

        case 28: // '\034'
            return Screen1.lambda14();

        case 29: // '\035'
            return Screen1.lambda16();

        case 30: // '\036'
            return Screen1.lambda15();

        case 31: // '\037'
            return Screen1.lambda17();

        case 32: // ' '
            return Screen1.lambda19();

        case 33: // '!'
            return Screen1.lambda18();

        case 34: // '"'
            return Screen1.lambda20();

        case 35: // '#'
            return Screen1.lambda21();

        case 36: // '$'
            return Screen1.lambda22();

        case 37: // '%'
            return Screen1.lambda23();

        case 38: // '&'
            return Screen1.lambda24();

        case 39: // '\''
            return Screen1.lambda25();

        case 40: // '('
            return Screen1.lambda26();

        case 41: // ')'
            return Screen1.lambda27();

        case 42: // '*'
            return Screen1.lambda28();

        case 43: // '+'
            return Screen1.lambda29();

        case 44: // ','
            return Screen1.lambda30();

        case 45: // '-'
            return Screen1.lambda31();

        case 46: // '.'
            return $main.Screen1$Initialize();

        case 47: // '/'
            return Screen1.lambda32();

        case 48: // '0'
            return Screen1.lambda33();

        case 49: // '1'
            return Screen1.lambda34();

        case 50: // '2'
            return Screen1.lambda35();

        case 51: // '3'
            return $main.ListPicker1$AfterPicking();

        case 52: // '4'
            return Screen1.lambda36();

        case 53: // '5'
            return Screen1.lambda37();

        case 54: // '6'
            return Screen1.lambda38();

        case 55: // '7'
            return Screen1.lambda39();

        case 56: // '8'
            return $main.ListPicker2$AfterPicking();

        case 57: // '9'
            return Screen1.lambda40();

        case 58: // ':'
            return Screen1.lambda41();

        case 59: // ';'
            return Screen1.lambda42();

        case 60: // '<'
            return Screen1.lambda43();

        case 61: // '='
            return Screen1.lambda44();

        case 62: // '>'
            return Screen1.lambda45();

        case 63: // '?'
            return Screen1.lambda46();

        case 64: // '@'
            return Screen1.lambda47();

        case 65: // 'A'
            return $main.Button5$Click();

        case 66: // 'B'
            return Screen1.lambda48();

        case 67: // 'C'
            return Screen1.lambda49();

        case 68: // 'D'
            return $main.Button6$Click();

        case 69: // 'E'
            return Screen1.lambda50();

        case 70: // 'F'
            return Screen1.lambda51();

        case 71: // 'G'
            return $main.Button7$Click();

        case 72: // 'H'
            return Screen1.lambda52();

        case 73: // 'I'
            return Screen1.lambda53();

        case 74: // 'J'
            return Screen1.lambda54();

        case 75: // 'K'
            return Screen1.lambda55();

        case 76: // 'L'
            return $main.DatePicker1$AfterDateSet();

        case 77: // 'M'
            return Screen1.lambda56();

        case 78: // 'N'
            return Screen1.lambda57();

        case 79: // 'O'
            return Screen1.lambda58();

        case 80: // 'P'
            return Screen1.lambda59();

        case 81: // 'Q'
            return $main.PhoneNumberPicker1$AfterPicking();

        case 82: // 'R'
            return Screen1.lambda60();

        case 83: // 'S'
            return Screen1.lambda61();

        case 84: // 'T'
            return Screen1.lambda62();

        case 85: // 'U'
            return Screen1.lambda63();

        case 86: // 'V'
            return $main.Button4$Click();

        case 87: // 'W'
            return Screen1.lambda64();

        case 88: // 'X'
            return Screen1.lambda65();

        case 89: // 'Y'
            return Screen1.lambda66();

        case 90: // 'Z'
            return Screen1.lambda67();

        case 91: // '['
            return $main.Button8$Click();

        case 93: // ']'
            return Screen1.lambda68();

        case 94: // '^'
            return Screen1.lambda69();

        case 95: // '_'
            return $main.Clock1$Timer();
        }
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.apply1(modulemethod, obj);

        case 1: // '\001'
            $main.androidLogForm(obj);
            return Values.empty;

        case 3: // '\003'
            Screen1 screen1_1 = $main;
            Screen1 screen1;
            Symbol symbol;
            Symbol symbol1;
            try
            {
                symbol1 = (Symbol)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "lookup-in-form-environment", 1, obj);
            }
            return screen1_1.lookupInFormEnvironment(symbol1);

        case 5: // '\005'
            screen1 = $main;
            try
            {
                symbol = (Symbol)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "is-bound-in-form-environment", 1, obj);
            }
            if (screen1.isBoundInFormEnvironment(symbol))
            {
                return Boolean.TRUE;
            } else
            {
                return Boolean.FALSE;
            }

        case 10: // '\n'
            $main.addToFormDoAfterCreation(obj);
            return Values.empty;

        case 11: // '\013'
            $main.sendError(obj);
            return Values.empty;

        case 12: // '\f'
            $main.processException(obj);
            return Values.empty;

        case 92: // '\\'
            return $main.Notifier1$AfterChoosing(obj);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        switch (modulemethod.selector)
        {
        case 4: // '\004'
        case 5: // '\005'
        case 8: // '\b'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        default:
            return super.apply2(modulemethod, obj, obj1);

        case 2: // '\002'
            Screen1 screen1_2 = $main;
            Screen1 screen1;
            Symbol symbol;
            Screen1 screen1_1;
            Symbol symbol1;
            Symbol symbol2;
            try
            {
                symbol2 = (Symbol)obj;
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "add-to-form-environment", 1, obj);
            }
            screen1_2.addToFormEnvironment(symbol2, obj1);
            return Values.empty;

        case 3: // '\003'
            screen1_1 = $main;
            try
            {
                symbol1 = (Symbol)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "lookup-in-form-environment", 1, obj);
            }
            return screen1_1.lookupInFormEnvironment(symbol1, obj1);

        case 6: // '\006'
            screen1 = $main;
            try
            {
                symbol = (Symbol)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "add-to-global-var-environment", 1, obj);
            }
            screen1.addToGlobalVarEnvironment(symbol, obj1);
            return Values.empty;

        case 7: // '\007'
            $main.addToEvents(obj, obj1);
            return Values.empty;

        case 9: // '\t'
            $main.addToGlobalVars(obj, obj1);
            return Values.empty;

        case 14: // '\016'
            return $main.lookupHandler(obj, obj1);
        }
    }

    public Object apply4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3)
    {
        Screen1 screen1;
        switch (modulemethod.selector)
        {
        default:
            return super.apply4(modulemethod, obj, obj1, obj2, obj3);

        case 8: // '\b'
            $main.addToComponents(obj, obj1, obj2, obj3);
            return Values.empty;

        case 13: // '\r'
            screen1 = $main;
            break;
        }
        Component component;
        String s;
        String s1;
        Object aobj[];
        try
        {
            component = (Component)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "dispatchEvent", 1, obj);
        }
        try
        {
            s = (String)obj1;
        }
        catch (ClassCastException classcastexception1)
        {
            throw new WrongType(classcastexception1, "dispatchEvent", 2, obj1);
        }
        try
        {
            s1 = (String)obj2;
        }
        catch (ClassCastException classcastexception2)
        {
            throw new WrongType(classcastexception2, "dispatchEvent", 3, obj2);
        }
        try
        {
            aobj = (Object[])obj3;
        }
        catch (ClassCastException classcastexception3)
        {
            throw new WrongType(classcastexception3, "dispatchEvent", 4, obj3);
        }
        if (screen1.dispatchEvent(component, s, s1, aobj))
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 92: // '\\'
        default:
            return super.match0(modulemethod, callcontext);

        case 95: // '_'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 94: // '^'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 93: // ']'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 91: // '['
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 90: // 'Z'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 89: // 'Y'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 88: // 'X'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 87: // 'W'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 86: // 'V'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 85: // 'U'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 84: // 'T'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 83: // 'S'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 82: // 'R'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 81: // 'Q'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 80: // 'P'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 79: // 'O'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 78: // 'N'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 77: // 'M'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 76: // 'L'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 75: // 'K'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 74: // 'J'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 73: // 'I'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 72: // 'H'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 71: // 'G'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 70: // 'F'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 69: // 'E'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 68: // 'D'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 67: // 'C'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 66: // 'B'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 65: // 'A'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 64: // '@'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 63: // '?'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 62: // '>'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 61: // '='
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 60: // '<'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 59: // ';'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 58: // ':'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 57: // '9'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 56: // '8'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 55: // '7'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 54: // '6'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 53: // '5'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 52: // '4'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 51: // '3'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 50: // '2'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 49: // '1'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 48: // '0'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 47: // '/'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 46: // '.'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 45: // '-'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 44: // ','
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 43: // '+'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 42: // '*'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 41: // ')'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 40: // '('
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 39: // '\''
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 38: // '&'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 37: // '%'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 36: // '$'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 35: // '#'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 34: // '"'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 33: // '!'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 32: // ' '
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 31: // '\037'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 30: // '\036'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 29: // '\035'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 28: // '\034'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 27: // '\033'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 26: // '\032'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 25: // '\031'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 24: // '\030'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 23: // '\027'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 22: // '\026'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 21: // '\025'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 20: // '\024'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 19: // '\023'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 18: // '\022'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 17: // '\021'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 16: // '\020'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;

        case 15: // '\017'
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match1(modulemethod, obj, callcontext);

        case 92: // '\\'
            callcontext.value1 = obj;
            callcontext.proc = modulemethod;
            callcontext.pc = 1;
            return 0;

        case 12: // '\f'
            if (!(obj instanceof Screen1))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.proc = modulemethod;
                callcontext.pc = 1;
                return 0;
            }

        case 11: // '\013'
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
            if (!(obj instanceof Symbol))
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
            if (!(obj instanceof Symbol))
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
        case 4: // '\004'
        case 5: // '\005'
        case 8: // '\b'
        case 10: // '\n'
        case 11: // '\013'
        case 12: // '\f'
        case 13: // '\r'
        default:
            return super.match2(modulemethod, obj, obj1, callcontext);

        case 14: // '\016'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;

        case 9: // '\t'
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
            if (!(obj instanceof Symbol))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 3: // '\003'
            if (!(obj instanceof Symbol))
            {
                return 0xfff40001;
            } else
            {
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }

        case 2: // '\002'
            break;
        }
        if (!(obj instanceof Symbol))
        {
            return 0xfff40001;
        } else
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        }
    }

    public int match4(ModuleMethod modulemethod, Object obj, Object obj1, Object obj2, Object obj3, CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        default:
            return super.match4(modulemethod, obj, obj1, obj2, obj3, callcontext);

        case 13: // '\r'
            if (!(obj instanceof Screen1))
            {
                return 0xfff40001;
            }
            callcontext.value1 = obj;
            if (!(obj1 instanceof Component))
            {
                return 0xfff40002;
            }
            callcontext.value2 = obj1;
            if (!(obj2 instanceof String))
            {
                return 0xfff40003;
            }
            callcontext.value3 = obj2;
            if (!(obj3 instanceof String))
            {
                return 0xfff40004;
            } else
            {
                callcontext.value4 = obj3;
                callcontext.proc = modulemethod;
                callcontext.pc = 4;
                return 0;
            }

        case 8: // '\b'
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.value3 = obj2;
            callcontext.value4 = obj3;
            callcontext.proc = modulemethod;
            callcontext.pc = 4;
            return 0;
        }
    }

    public ()
    {
    }
}
