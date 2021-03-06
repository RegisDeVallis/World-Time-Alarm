// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Format;
import gnu.kawa.models.Box;
import gnu.kawa.models.Button;
import gnu.kawa.models.Column;
import gnu.kawa.models.Display;
import gnu.kawa.models.Label;
import gnu.kawa.models.Model;
import gnu.kawa.models.Row;
import gnu.kawa.models.Text;
import gnu.kawa.models.Window;
import gnu.kawa.reflect.SlotGet;
import gnu.kawa.xml.KAttr;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.ThreadLocation;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Path;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import kawa.lang.Macro;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRule;
import kawa.lang.SyntaxRules;
import kawa.lib.misc;
import kawa.standard.Scheme;

public class gui extends ModuleBody
{

    public static final gui $instance;
    public static final ModuleMethod Button;
    public static final ModuleMethod Column;
    public static final Macro Image;
    public static final ModuleMethod Label;
    static final Class Lit0 = java/awt/Color;
    static final SimpleSymbol Lit1 = (SimpleSymbol)(new SimpleSymbol("text")).readResolve();
    static final SimpleSymbol Lit10;
    static final SyntaxRules Lit11;
    static final SimpleSymbol Lit12;
    static final SimpleSymbol Lit13;
    static final SimpleSymbol Lit14;
    static final SimpleSymbol Lit15;
    static final SimpleSymbol Lit16;
    static final SimpleSymbol Lit17;
    static final SimpleSymbol Lit18;
    static final SimpleSymbol Lit19;
    static final SimpleSymbol Lit2 = (SimpleSymbol)(new SimpleSymbol("cell-spacing")).readResolve();
    static final SimpleSymbol Lit20;
    static final SimpleSymbol Lit21;
    static final SyntaxRules Lit22;
    static final SimpleSymbol Lit23;
    static final SimpleSymbol Lit24;
    static final SimpleSymbol Lit25;
    static final SimpleSymbol Lit26;
    static final SimpleSymbol Lit27;
    static final SimpleSymbol Lit28;
    static final SimpleSymbol Lit29;
    static final SimpleSymbol Lit3;
    static final SimpleSymbol Lit30;
    static final SimpleSymbol Lit31;
    static final SimpleSymbol Lit32;
    static final SimpleSymbol Lit33;
    static final SimpleSymbol Lit34;
    static final SimpleSymbol Lit35;
    static final SimpleSymbol Lit36;
    static final SimpleSymbol Lit37;
    static final SimpleSymbol Lit38;
    static final SimpleSymbol Lit39;
    static final SimpleSymbol Lit4;
    static final SimpleSymbol Lit40;
    static final SimpleSymbol Lit41;
    static final IntNum Lit42;
    static final SimpleSymbol Lit5;
    static final SyntaxRules Lit6;
    static final SimpleSymbol Lit7;
    static final SimpleSymbol Lit8;
    static final SimpleSymbol Lit9;
    public static final ModuleMethod Row;
    public static final ModuleMethod Text;
    public static final ModuleMethod Window;
    public static final ModuleMethod as$Mncolor;
    public static final ModuleMethod button;
    public static final ModuleMethod image$Mnheight;
    public static final ModuleMethod image$Mnread;
    public static final ModuleMethod image$Mnwidth;
    static final Location loc$$St$DtgetHeight;
    static final Location loc$$St$DtgetWidth;
    public static final Macro process$Mnkeywords;
    public static final Macro run$Mnapplication;
    public static final ModuleMethod set$Mncontent;

    public gui()
    {
        ModuleInfo.register(this);
    }

    public static transient Button Button(Object aobj[])
    {
        Button button1 = new Button();
        int i = aobj.length;
        for (int j = 0; j < i;)
        {
            Object obj = aobj[j];
            if (obj instanceof Keyword)
            {
                KAttr kattr;
                Keyword keyword;
                try
                {
                    keyword = (Keyword)obj;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "gnu.expr.Keyword.getName()", 1, obj);
                }
                buttonKeyword(button1, keyword.getName(), aobj[j + 1]);
                j += 2;
            } else
            if (obj instanceof KAttr)
            {
                try
                {
                    kattr = (KAttr)obj;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "attr", -2, obj);
                }
                buttonKeyword(button1, kattr.getName(), kattr.getObjectValue());
                j++;
            } else
            {
                buttonNonKeyword(button1, obj);
                j++;
            }
        }

        return button1;
    }

    public static transient Column Column(Object aobj[])
    {
        Column column = new Column();
        int i = aobj.length;
        for (int j = 0; j < i;)
        {
            Object obj = aobj[j];
            if (obj instanceof Keyword)
            {
                KAttr kattr;
                Keyword keyword;
                try
                {
                    keyword = (Keyword)obj;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "gnu.expr.Keyword.getName()", 1, obj);
                }
                boxKeyword(column, keyword.getName(), aobj[j + 1]);
                j += 2;
            } else
            if (obj instanceof KAttr)
            {
                try
                {
                    kattr = (KAttr)obj;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "attr", -2, obj);
                }
                boxKeyword(column, kattr.getName(), kattr.getObjectValue());
                j++;
            } else
            {
                boxNonKeyword(column, obj);
                j++;
            }
        }

        return column;
    }

    public static transient Label Label(Object aobj[])
    {
        Label label = new Label();
        int i = aobj.length;
        for (int j = 0; j < i;)
        {
            Object obj = aobj[j];
            if (obj instanceof Keyword)
            {
                KAttr kattr;
                Keyword keyword;
                try
                {
                    keyword = (Keyword)obj;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "gnu.expr.Keyword.getName()", 1, obj);
                }
                labelKeyword(label, keyword.getName(), aobj[j + 1]);
                j += 2;
            } else
            if (obj instanceof KAttr)
            {
                try
                {
                    kattr = (KAttr)obj;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "attr", -2, obj);
                }
                labelKeyword(label, kattr.getName(), kattr.getObjectValue());
                j++;
            } else
            {
                labelNonKeyword(label, obj);
                j++;
            }
        }

        return label;
    }

    public static transient Row Row(Object aobj[])
    {
        Row row = new Row();
        int i = aobj.length;
        for (int j = 0; j < i;)
        {
            Object obj = aobj[j];
            if (obj instanceof Keyword)
            {
                KAttr kattr;
                Keyword keyword;
                try
                {
                    keyword = (Keyword)obj;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "gnu.expr.Keyword.getName()", 1, obj);
                }
                boxKeyword(row, keyword.getName(), aobj[j + 1]);
                j += 2;
            } else
            if (obj instanceof KAttr)
            {
                try
                {
                    kattr = (KAttr)obj;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "attr", -2, obj);
                }
                boxKeyword(row, kattr.getName(), kattr.getObjectValue());
                j++;
            } else
            {
                boxNonKeyword(row, obj);
                j++;
            }
        }

        return row;
    }

    public static transient Text Text(Object aobj[])
    {
        Text text = new Text();
        int i = aobj.length;
        for (int j = 0; j < i;)
        {
            Object obj = aobj[j];
            if (obj instanceof Keyword)
            {
                KAttr kattr;
                Keyword keyword;
                try
                {
                    keyword = (Keyword)obj;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "gnu.expr.Keyword.getName()", 1, obj);
                }
                textKeyword(text, keyword.getName(), aobj[j + 1]);
                j += 2;
            } else
            if (obj instanceof KAttr)
            {
                try
                {
                    kattr = (KAttr)obj;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "attr", -2, obj);
                }
                textKeyword(text, kattr.getName(), kattr.getObjectValue());
                j++;
            } else
            {
                textNonKeyword(text, obj);
                j++;
            }
        }

        return text;
    }

    public static transient Window Window(Object aobj[])
    {
        Window window = Display.getInstance().makeWindow();
        int i = aobj.length;
        for (int j = 0; j < i;)
        {
            Object obj = aobj[j];
            if (obj instanceof Keyword)
            {
                KAttr kattr;
                Keyword keyword;
                try
                {
                    keyword = (Keyword)obj;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "gnu.expr.Keyword.getName()", 1, obj);
                }
                windowKeyword(window, keyword.getName(), aobj[j + 1]);
                j += 2;
            } else
            if (obj instanceof KAttr)
            {
                try
                {
                    kattr = (KAttr)obj;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "attr", -2, obj);
                }
                windowKeyword(window, kattr.getName(), kattr.getObjectValue());
                j++;
            } else
            {
                windowNonKeyword(window, obj);
                j++;
            }
        }

        return window;
    }

    public static Color asColor(Object obj)
    {
        if (obj instanceof Color)
        {
            return (Color)obj;
        }
        if (obj instanceof Integer)
        {
            Integer integer;
            try
            {
                integer = (Integer)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "java.lang.Integer.intValue()", 1, obj);
            }
            return new Color(integer.intValue());
        }
        if (obj instanceof IntNum)
        {
            return new Color(IntNum.intValue(obj));
        } else
        {
            return (Color)SlotGet.staticField.apply2(Lit0, obj.toString());
        }
    }

    static Model asModel(Object obj)
    {
        return Display.getInstance().coerceToModel(obj);
    }

    static Object boxKeyword(Box box, String s, Object obj)
    {
        if (s == Lit2)
        {
            box.setCellSpacing(obj);
            return Values.empty;
        } else
        {
            return misc.error$V(Format.formatToString(0, new Object[] {
                "unknown box attribute ~s", s
            }), new Object[0]);
        }
    }

    static void boxNonKeyword(Box box, Object obj)
    {
        box.add(asModel(obj));
    }

    public static transient Button button(Object aobj[])
    {
        Button button1 = new Button();
        int i = aobj.length;
        for (int j = 0; j < i;)
        {
            Object obj = aobj[j];
            if (obj instanceof Keyword)
            {
                KAttr kattr;
                Keyword keyword;
                try
                {
                    keyword = (Keyword)obj;
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "gnu.expr.Keyword.getName()", 1, obj);
                }
                buttonKeyword(button1, keyword.getName(), aobj[j + 1]);
                j += 2;
            } else
            if (obj instanceof KAttr)
            {
                try
                {
                    kattr = (KAttr)obj;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "attr", -2, obj);
                }
                buttonKeyword(button1, kattr.getName(), kattr.getObjectValue());
                j++;
            } else
            {
                buttonNonKeyword(button1, obj);
                j++;
            }
        }

        return button1;
    }

    static Object buttonKeyword(Button button1, String s, Object obj)
    {
        boolean flag = true;
        if (s == "foreground")
        {
            button1.setForeground(asColor(obj));
            return Values.empty;
        }
        if (s == "background")
        {
            button1.setBackground(asColor(obj));
            return Values.empty;
        }
        if (s == "action")
        {
            button1.setAction(obj);
            return Values.empty;
        }
        if (s == "text")
        {
            String s1;
            if (obj == null)
            {
                s1 = null;
            } else
            {
                s1 = obj.toString();
            }
            button1.setText(s1);
            return Values.empty;
        }
        if (s == "disabled")
        {
            Object aobj[];
            Boolean boolean1;
            try
            {
                boolean1 = Boolean.FALSE;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "gnu.kawa.models.Button.setDisabled(boolean)", 2, obj);
            }
            if (obj == boolean1)
            {
                flag = false;
            }
            button1.setDisabled(flag);
            return Values.empty;
        } else
        {
            aobj = new Object[2];
            aobj[0] = "unknown button attribute ~s";
            aobj[flag] = s;
            return misc.error$V(Format.formatToString(0, aobj), new Object[0]);
        }
    }

    static Boolean buttonNonKeyword(Button button1, Object obj)
    {
        return Boolean.TRUE;
    }

    public static int imageHeight(BufferedImage bufferedimage)
    {
        gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
        Location location = loc$$St$DtgetHeight;
        Object obj;
        try
        {
            obj = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("gui.scm", 77, 3);
            throw unboundlocationexception;
        }
        return ((Number)applytoargs.apply2(obj, bufferedimage)).intValue();
    }

    public static BufferedImage imageRead(Path path)
    {
        return ImageIO.read(path.openInputStream());
    }

    public static int imageWidth(BufferedImage bufferedimage)
    {
        gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
        Location location = loc$$St$DtgetWidth;
        Object obj;
        try
        {
            obj = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("gui.scm", 74, 3);
            throw unboundlocationexception;
        }
        return ((Number)applytoargs.apply2(obj, bufferedimage)).intValue();
    }

    static Object labelKeyword(Label label, String s, Object obj)
    {
        if (s == Lit1)
        {
            String s1;
            if (obj == null)
            {
                s1 = null;
            } else
            {
                s1 = obj.toString();
            }
            label.setText(s1);
            return Values.empty;
        } else
        {
            return misc.error$V(Format.formatToString(0, new Object[] {
                "unknown label attribute ~s", s
            }), new Object[0]);
        }
    }

    static void labelNonKeyword(Label label, Object obj)
    {
        String s;
        if (obj == null)
        {
            s = null;
        } else
        {
            s = obj.toString();
        }
        label.setText(s);
    }

    public static void setContent(Window window, Object obj)
    {
        window.setContent(obj);
    }

    static Object textKeyword(Text text, String s, Object obj)
    {
        if (s == Lit1)
        {
            String s1;
            if (obj == null)
            {
                s1 = null;
            } else
            {
                s1 = obj.toString();
            }
            text.setText(s1);
            return Values.empty;
        } else
        {
            return misc.error$V(Format.formatToString(0, new Object[] {
                "unknown text attribute ~s", s
            }), new Object[0]);
        }
    }

    static void textNonKeyword(Text text, Object obj)
    {
        String s;
        if (obj == null)
        {
            s = null;
        } else
        {
            s = obj.toString();
        }
        text.setText(s);
    }

    static Object windowKeyword(Window window, String s, Object obj)
    {
        if (s == "title")
        {
            String s1;
            if (obj == null)
            {
                s1 = null;
            } else
            {
                s1 = obj.toString();
            }
            window.setTitle(s1);
            return Values.empty;
        }
        if (s == "content")
        {
            window.setContent(obj);
            return Values.empty;
        }
        if (s == "menubar")
        {
            window.setMenuBar(obj);
            return Values.empty;
        } else
        {
            return misc.error$V(Format.formatToString(0, new Object[] {
                "unknown window attribute ~s", s
            }), new Object[0]);
        }
    }

    static void windowNonKeyword(Window window, Object obj)
    {
        window.setContent(obj);
    }

    public Object apply1(ModuleMethod modulemethod, Object obj)
    {
        switch (modulemethod.selector)
        {
        case 2: // '\002'
        case 3: // '\003'
        default:
            return super.apply1(modulemethod, obj);

        case 1: // '\001'
            return asColor(obj);

        case 4: // '\004'
            BufferedImage bufferedimage;
            BufferedImage bufferedimage1;
            Path path;
            try
            {
                path = Path.valueOf(obj);
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "image-read", 1, obj);
            }
            return imageRead(path);

        case 5: // '\005'
            try
            {
                bufferedimage1 = (BufferedImage)obj;
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "image-width", 1, obj);
            }
            return Integer.valueOf(imageWidth(bufferedimage1));

        case 6: // '\006'
            break;
        }
        try
        {
            bufferedimage = (BufferedImage)obj;
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "image-height", 1, obj);
        }
        return Integer.valueOf(imageHeight(bufferedimage));
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 11)
        {
            Window window;
            try
            {
                window = (Window)obj;
            }
            catch (ClassCastException classcastexception)
            {
                throw new WrongType(classcastexception, "set-content", 1, obj);
            }
            setContent(window, obj1);
            return Values.empty;
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    public Object applyN(ModuleMethod modulemethod, Object aobj[])
    {
        switch (modulemethod.selector)
        {
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 11: // '\013'
        default:
            return super.applyN(modulemethod, aobj);

        case 2: // '\002'
            return button(aobj);

        case 3: // '\003'
            return Button(aobj);

        case 7: // '\007'
            return Label(aobj);

        case 8: // '\b'
            return Text(aobj);

        case 9: // '\t'
            return Row(aobj);

        case 10: // '\n'
            return Column(aobj);

        case 12: // '\f'
            return Window(aobj);
        }
    }

    public int match1(ModuleMethod modulemethod, Object obj, CallContext callcontext)
    {
        int i = 0xfff40001;
        modulemethod.selector;
        JVM INSTR tableswitch 1 6: default 48
    //                   1 132
    //                   2 48
    //                   3 48
    //                   4 108
    //                   5 84
    //                   6 60;
           goto _L1 _L2 _L1 _L1 _L3 _L4 _L5
_L1:
        i = super.match1(modulemethod, obj, callcontext);
_L7:
        return i;
_L5:
        if (!(obj instanceof BufferedImage)) goto _L7; else goto _L6
_L6:
        callcontext.value1 = obj;
        callcontext.proc = modulemethod;
        callcontext.pc = 1;
        return 0;
_L4:
        if (!(obj instanceof BufferedImage)) goto _L7; else goto _L8
_L8:
        callcontext.value1 = obj;
        callcontext.proc = modulemethod;
        callcontext.pc = 1;
        return 0;
_L3:
        if (Path.coerceToPathOrNull(obj) == null) goto _L7; else goto _L9
_L9:
        callcontext.value1 = obj;
        callcontext.proc = modulemethod;
        callcontext.pc = 1;
        return 0;
_L2:
        callcontext.value1 = obj;
        callcontext.proc = modulemethod;
        callcontext.pc = 1;
        return 0;
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        if (modulemethod.selector == 11)
        {
            if (!(obj instanceof Window))
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
        } else
        {
            return super.match2(modulemethod, obj, obj1, callcontext);
        }
    }

    public int matchN(ModuleMethod modulemethod, Object aobj[], CallContext callcontext)
    {
        switch (modulemethod.selector)
        {
        case 4: // '\004'
        case 5: // '\005'
        case 6: // '\006'
        case 11: // '\013'
        default:
            return super.matchN(modulemethod, aobj, callcontext);

        case 12: // '\f'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 10: // '\n'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 9: // '\t'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 8: // '\b'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 7: // '\007'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 3: // '\003'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;

        case 2: // '\002'
            callcontext.values = aobj;
            callcontext.proc = modulemethod;
            callcontext.pc = 5;
            return 0;
        }
    }

    public final void run(CallContext callcontext)
    {
        gnu.lists.Consumer _tmp = callcontext.consumer;
    }

    static 
    {
        Lit42 = IntNum.make(1);
        Lit41 = (SimpleSymbol)(new SimpleSymbol("value")).readResolve();
        Lit40 = (SimpleSymbol)(new SimpleSymbol("name")).readResolve();
        Lit39 = (SimpleSymbol)(new SimpleSymbol("invoke")).readResolve();
        Lit38 = (SimpleSymbol)(new SimpleSymbol("getName")).readResolve();
        Lit37 = (SimpleSymbol)(new SimpleSymbol("quote")).readResolve();
        Lit36 = (SimpleSymbol)(new SimpleSymbol("attr")).readResolve();
        Lit35 = (SimpleSymbol)(new SimpleSymbol("<gnu.kawa.xml.KAttr>")).readResolve();
        Lit34 = (SimpleSymbol)(new SimpleSymbol("instance?")).readResolve();
        Lit33 = (SimpleSymbol)(new SimpleSymbol("+")).readResolve();
        Lit32 = (SimpleSymbol)(new SimpleSymbol("loop")).readResolve();
        Lit31 = (SimpleSymbol)(new SimpleSymbol("<object>")).readResolve();
        Lit30 = (SimpleSymbol)(new SimpleSymbol("primitive-array-get")).readResolve();
        Lit29 = (SimpleSymbol)(new SimpleSymbol("quasiquote")).readResolve();
        Lit28 = (SimpleSymbol)(new SimpleSymbol("$lookup$")).readResolve();
        Lit27 = (SimpleSymbol)(new SimpleSymbol("arg")).readResolve();
        Lit26 = (SimpleSymbol)(new SimpleSymbol("num-args")).readResolve();
        Lit25 = (SimpleSymbol)(new SimpleSymbol("i")).readResolve();
        Lit24 = (SimpleSymbol)(new SimpleSymbol("<int>")).readResolve();
        Lit23 = (SimpleSymbol)(new SimpleSymbol("::")).readResolve();
        Object aobj[] = new Object[1];
        SimpleSymbol simplesymbol = (SimpleSymbol)(new SimpleSymbol("run-application")).readResolve();
        Lit21 = simplesymbol;
        aobj[0] = simplesymbol;
        SyntaxRule asyntaxrule[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern = new SyntaxPattern("\f\030\f\007\b", new Object[0], 1);
        Object aobj1[] = new Object[1];
        aobj1[0] = PairWithPosition.make(Lit28, Pair.make((SimpleSymbol)(new SimpleSymbol("gnu.kawa.models.Window")).readResolve(), Pair.make(Pair.make(Lit29, Pair.make((SimpleSymbol)(new SimpleSymbol("open")).readResolve(), LList.Empty)), LList.Empty)), "gui.scm", 0xb7007);
        asyntaxrule[0] = new SyntaxRule(syntaxpattern, "\001", "\021\030\004\b\003", aobj1, 0);
        Lit22 = new SyntaxRules(aobj, asyntaxrule, 1);
        Lit20 = (SimpleSymbol)(new SimpleSymbol("Window")).readResolve();
        Lit19 = (SimpleSymbol)(new SimpleSymbol("set-content")).readResolve();
        Lit18 = (SimpleSymbol)(new SimpleSymbol("Column")).readResolve();
        Lit17 = (SimpleSymbol)(new SimpleSymbol("Row")).readResolve();
        Lit16 = (SimpleSymbol)(new SimpleSymbol("Text")).readResolve();
        Lit15 = (SimpleSymbol)(new SimpleSymbol("Label")).readResolve();
        Lit14 = (SimpleSymbol)(new SimpleSymbol("image-height")).readResolve();
        Lit13 = (SimpleSymbol)(new SimpleSymbol("image-width")).readResolve();
        Lit12 = (SimpleSymbol)(new SimpleSymbol("image-read")).readResolve();
        Object aobj2[] = new Object[1];
        aobj2[0] = (SimpleSymbol)(new SimpleSymbol("text-field")).readResolve();
        SyntaxRule asyntaxrule1[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern1 = new SyntaxPattern("\f\030\003", new Object[0], 1);
        Object aobj3[] = new Object[2];
        aobj3[0] = (SimpleSymbol)(new SimpleSymbol("make")).readResolve();
        aobj3[1] = (SimpleSymbol)(new SimpleSymbol("<gnu.kawa.models.DrawImage>")).readResolve();
        asyntaxrule1[0] = new SyntaxRule(syntaxpattern1, "\0", "\021\030\004\021\030\f\002", aobj3, 0);
        Lit11 = new SyntaxRules(aobj2, asyntaxrule1, 1);
        Lit10 = (SimpleSymbol)(new SimpleSymbol("Image")).readResolve();
        Lit9 = (SimpleSymbol)(new SimpleSymbol("Button")).readResolve();
        Lit8 = (SimpleSymbol)(new SimpleSymbol("button")).readResolve();
        Lit7 = (SimpleSymbol)(new SimpleSymbol("as-color")).readResolve();
        Object aobj4[] = new Object[1];
        SimpleSymbol simplesymbol1 = (SimpleSymbol)(new SimpleSymbol("process-keywords")).readResolve();
        Lit5 = simplesymbol1;
        aobj4[0] = simplesymbol1;
        SyntaxRule asyntaxrule2[] = new SyntaxRule[1];
        SyntaxPattern syntaxpattern2 = new SyntaxPattern("\f\030\f\007\f\017\f\027\f\037\b", new Object[0], 4);
        Object aobj5[] = new Object[27];
        aobj5[0] = (SimpleSymbol)(new SimpleSymbol("let")).readResolve();
        aobj5[1] = Lit26;
        aobj5[2] = Lit23;
        aobj5[3] = Lit24;
        aobj5[4] = (SimpleSymbol)(new SimpleSymbol("field")).readResolve();
        aobj5[5] = PairWithPosition.make(PairWithPosition.make(Lit37, PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("length")).readResolve(), LList.Empty, "gui.scm", 16426), "gui.scm", 16426), LList.Empty, "gui.scm", 16425);
        aobj5[6] = Lit32;
        aobj5[7] = PairWithPosition.make(PairWithPosition.make(Lit25, PairWithPosition.make(Lit23, PairWithPosition.make(Lit24, PairWithPosition.make(IntNum.make(0), LList.Empty, "gui.scm", 20509), "gui.scm", 20503), "gui.scm", 20500), "gui.scm", 20497), LList.Empty, "gui.scm", 20496);
        aobj5[8] = (SimpleSymbol)(new SimpleSymbol("if")).readResolve();
        aobj5[9] = PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("<")).readResolve(), PairWithPosition.make(Lit25, PairWithPosition.make(Lit26, LList.Empty, "gui.scm", 24593), "gui.scm", 24591), "gui.scm", 24588);
        aobj5[10] = Lit27;
        aobj5[11] = PairWithPosition.make(Lit30, PairWithPosition.make(Lit31, LList.Empty, "gui.scm", 28710), "gui.scm", 28689);
        aobj5[12] = PairWithPosition.make(Lit25, LList.Empty, "gui.scm", 28725);
        aobj5[13] = (SimpleSymbol)(new SimpleSymbol("cond")).readResolve();
        aobj5[14] = PairWithPosition.make(Lit34, PairWithPosition.make(Lit27, PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("<gnu.expr.Keyword>")).readResolve(), LList.Empty, "gui.scm", 32797), "gui.scm", 32793), "gui.scm", 32782);
        aobj5[15] = PairWithPosition.make(PairWithPosition.make(Lit28, Pair.make((SimpleSymbol)(new SimpleSymbol("gnu.expr.Keyword")).readResolve(), Pair.make(Pair.make(Lit29, Pair.make(Lit38, LList.Empty)), LList.Empty)), "gui.scm", 40970), PairWithPosition.make(Lit27, LList.Empty, "gui.scm", 40995), "gui.scm", 40969);
        aobj5[16] = PairWithPosition.make(Lit30, PairWithPosition.make(Lit31, LList.Empty, "gui.scm", 45087), "gui.scm", 45066);
        aobj5[17] = PairWithPosition.make(PairWithPosition.make(Lit33, PairWithPosition.make(Lit25, PairWithPosition.make(Lit42, LList.Empty, "gui.scm", 45107), "gui.scm", 45105), "gui.scm", 45102), LList.Empty, "gui.scm", 45102);
        aobj5[18] = PairWithPosition.make(PairWithPosition.make(Lit32, PairWithPosition.make(PairWithPosition.make(Lit33, PairWithPosition.make(Lit25, PairWithPosition.make(IntNum.make(2), LList.Empty, "gui.scm", 49170), "gui.scm", 49168), "gui.scm", 49165), LList.Empty, "gui.scm", 49165), "gui.scm", 49159), LList.Empty, "gui.scm", 49159);
        aobj5[19] = PairWithPosition.make(Lit34, PairWithPosition.make(Lit27, PairWithPosition.make(Lit35, LList.Empty, "gui.scm", 53270), "gui.scm", 53266), "gui.scm", 53255);
        aobj5[20] = (SimpleSymbol)(new SimpleSymbol("let*")).readResolve();
        aobj5[21] = PairWithPosition.make(PairWithPosition.make(Lit36, PairWithPosition.make(Lit23, PairWithPosition.make(Lit35, PairWithPosition.make(Lit27, LList.Empty, "gui.scm", 57388), "gui.scm", 57367), "gui.scm", 57364), "gui.scm", 57358), PairWithPosition.make(PairWithPosition.make(Lit40, PairWithPosition.make(Lit23, PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("<java.lang.String>")).readResolve(), PairWithPosition.make(PairWithPosition.make(Lit39, PairWithPosition.make(Lit36, PairWithPosition.make(PairWithPosition.make(Lit37, PairWithPosition.make(Lit38, LList.Empty, "gui.scm", 61489), "gui.scm", 61489), LList.Empty, "gui.scm", 61488), "gui.scm", 61483), "gui.scm", 61475), LList.Empty, "gui.scm", 61475), "gui.scm", 61456), "gui.scm", 61453), "gui.scm", 61447), PairWithPosition.make(PairWithPosition.make(Lit41, PairWithPosition.make(PairWithPosition.make(Lit39, PairWithPosition.make(Lit36, PairWithPosition.make(PairWithPosition.make(Lit37, PairWithPosition.make((SimpleSymbol)(new SimpleSymbol("getObjectValue")).readResolve(), LList.Empty, "gui.scm", 0x1001c), "gui.scm", 0x1001c), LList.Empty, "gui.scm", 0x1001b), "gui.scm", 0x10016), "gui.scm", 0x1000e), LList.Empty, "gui.scm", 0x1000e), "gui.scm", 0x10007), LList.Empty, "gui.scm", 0x10007), "gui.scm", 61447), "gui.scm", 57357);
        aobj5[22] = PairWithPosition.make(Lit40, PairWithPosition.make(Lit41, LList.Empty, "gui.scm", 0x11022), "gui.scm", 0x1101d);
        aobj5[23] = PairWithPosition.make(PairWithPosition.make(Lit32, PairWithPosition.make(PairWithPosition.make(Lit33, PairWithPosition.make(Lit25, PairWithPosition.make(Lit42, LList.Empty, "gui.scm", 0x12012), "gui.scm", 0x12010), "gui.scm", 0x1200d), LList.Empty, "gui.scm", 0x1200d), "gui.scm", 0x12007), LList.Empty, "gui.scm", 0x12007);
        aobj5[24] = (SimpleSymbol)(new SimpleSymbol("else")).readResolve();
        aobj5[25] = PairWithPosition.make(Lit27, LList.Empty, "gui.scm", 0x1401f);
        aobj5[26] = PairWithPosition.make(PairWithPosition.make(Lit32, PairWithPosition.make(PairWithPosition.make(Lit33, PairWithPosition.make(Lit25, PairWithPosition.make(Lit42, LList.Empty, "gui.scm", 0x15012), "gui.scm", 0x15010), "gui.scm", 0x1500d), LList.Empty, "gui.scm", 0x1500d), "gui.scm", 0x15007), LList.Empty, "gui.scm", 0x15007);
        asyntaxrule2[0] = new SyntaxRule(syntaxpattern2, "\001\001\001\001", "\021\030\004\221\b\021\030\f\021\030\024\021\030\034\b\021\030$\t\013\030,\b\021\030\004\021\0304\021\030<\b\021\030D\021\030L\b\021\030\004a\b\021\030T\b\021\030\\\t\013\030d\b\021\030l\251\021\030ty\t\023\t\003\021\030|\b\021\030\204\t\013\030\214\030\224\231\021\030\234i\021\030\244\021\030\254\b\t\023\t\003\030\264\030\274\b\021\030\3041\t\033\t\003\030\314\030\324", aobj5, 0);
        Lit6 = new SyntaxRules(aobj4, asyntaxrule2, 4);
        Lit4 = (SimpleSymbol)(new SimpleSymbol("*.getHeight")).readResolve();
        Lit3 = (SimpleSymbol)(new SimpleSymbol("*.getWidth")).readResolve();
        $instance = new gui();
        loc$$St$DtgetWidth = ThreadLocation.getInstance(Lit3, null);
        loc$$St$DtgetHeight = ThreadLocation.getInstance(Lit4, null);
        process$Mnkeywords = Macro.make(Lit5, Lit6, $instance);
        gui gui1 = $instance;
        as$Mncolor = new ModuleMethod(gui1, 1, Lit7, 4097);
        button = new ModuleMethod(gui1, 2, Lit8, -4096);
        Button = new ModuleMethod(gui1, 3, Lit9, -4096);
        Image = Macro.make(Lit10, Lit11, $instance);
        image$Mnread = new ModuleMethod(gui1, 4, Lit12, 4097);
        image$Mnwidth = new ModuleMethod(gui1, 5, Lit13, 4097);
        image$Mnheight = new ModuleMethod(gui1, 6, Lit14, 4097);
        Label = new ModuleMethod(gui1, 7, Lit15, -4096);
        Text = new ModuleMethod(gui1, 8, Lit16, -4096);
        Row = new ModuleMethod(gui1, 9, Lit17, -4096);
        Column = new ModuleMethod(gui1, 10, Lit18, -4096);
        set$Mncontent = new ModuleMethod(gui1, 11, Lit19, 8194);
        Window = new ModuleMethod(gui1, 12, Lit20, -4096);
        run$Mnapplication = Macro.make(Lit21, Lit22, $instance);
        $instance.run();
    }
}
