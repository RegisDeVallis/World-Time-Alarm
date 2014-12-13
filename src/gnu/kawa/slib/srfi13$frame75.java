// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.lists.CharSeq;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.Procedure;
import gnu.mapping.PropertySet;
import gnu.mapping.UnboundLocationException;
import gnu.mapping.WrongType;
import gnu.text.Char;
import kawa.lib.characters;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;

// Referenced classes of package gnu.kawa.slib:
//            srfi13

public class lambda.Fn176 extends ModuleBody
{
    public class srfi13.frame76 extends ModuleBody
    {

        CharSequence ans;
        Object cset;
        final ModuleMethod lambda$Fn174;
        final ModuleMethod lambda$Fn175;
        final ModuleMethod lambda$Fn176;
        srfi13.frame75 staticLink;
        CharSequence temp;

        public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.apply2(modulemethod, obj, obj1);

            case 147: 
                return lambda174(obj, obj1);

            case 148: 
                return lambda175(obj, obj1);

            case 149: 
                return lambda176(obj, obj1);
            }
        }

        Object lambda174(Object obj, Object obj1)
        {
            if (Scheme.applyToArgs.apply2(staticLink.criterion, obj) != Boolean.FALSE)
            {
                CharSequence charsequence = temp;
                CharSeq charseq;
                int i;
                char c;
                try
                {
                    charseq = (CharSeq)charsequence;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "string-set!", 1, charsequence);
                }
                try
                {
                    i = ((Number)obj1).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-set!", 2, obj1);
                }
                try
                {
                    c = ((Char)obj).charValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "string-set!", 3, obj);
                }
                strings.stringSet$Ex(charseq, i, c);
                obj1 = AddOp.$Pl.apply2(obj1, srfi13.Lit1);
            }
            return obj1;
        }

        Object lambda175(Object obj, Object obj1)
        {
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
            Object obj2;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1128, 45);
                throw unboundlocationexception;
            }
            if (applytoargs.apply3(obj2, cset, obj) != Boolean.FALSE)
            {
                obj1 = AddOp.$Pl.apply2(obj1, srfi13.Lit1);
            }
            return obj1;
        }

        Object lambda176(Object obj, Object obj1)
        {
            gnu.kawa.functions.ApplyToArgs applytoargs = Scheme.applyToArgs;
            Location location = srfi13.loc$char$Mnset$Mncontains$Qu;
            Object obj2;
            try
            {
                obj2 = location.get();
            }
            catch (UnboundLocationException unboundlocationexception)
            {
                unboundlocationexception.setLine("srfi13.scm", 1133, 35);
                throw unboundlocationexception;
            }
            if (applytoargs.apply3(obj2, cset, obj) != Boolean.FALSE)
            {
                CharSequence charsequence = ans;
                CharSeq charseq;
                int i;
                char c;
                try
                {
                    charseq = (CharSeq)charsequence;
                }
                catch (ClassCastException classcastexception)
                {
                    throw new WrongType(classcastexception, "string-set!", 1, charsequence);
                }
                try
                {
                    i = ((Number)obj1).intValue();
                }
                catch (ClassCastException classcastexception1)
                {
                    throw new WrongType(classcastexception1, "string-set!", 2, obj1);
                }
                try
                {
                    c = ((Char)obj).charValue();
                }
                catch (ClassCastException classcastexception2)
                {
                    throw new WrongType(classcastexception2, "string-set!", 3, obj);
                }
                strings.stringSet$Ex(charseq, i, c);
                obj1 = AddOp.$Pl.apply2(obj1, srfi13.Lit1);
            }
            return obj1;
        }

        public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
        {
            switch (modulemethod.selector)
            {
            default:
                return super.match2(modulemethod, obj, obj1, callcontext);

            case 149: 
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;

            case 148: 
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;

            case 147: 
                callcontext.value1 = obj;
                callcontext.value2 = obj1;
                callcontext.proc = modulemethod;
                callcontext.pc = 2;
                return 0;
            }
        }

        public srfi13.frame76()
        {
            ModuleMethod modulemethod = new ModuleMethod(this, 147, null, 8194);
            modulemethod.setProperty("source-location", "srfi13.scm:1116");
            lambda$Fn174 = modulemethod;
            ModuleMethod modulemethod1 = new ModuleMethod(this, 148, null, 8194);
            modulemethod1.setProperty("source-location", "srfi13.scm:1128");
            lambda$Fn175 = modulemethod1;
            ModuleMethod modulemethod2 = new ModuleMethod(this, 149, null, 8194);
            modulemethod2.setProperty("source-location", "srfi13.scm:1133");
            lambda$Fn176 = modulemethod2;
        }
    }


    Object criterion;
    final ModuleMethod lambda$Fn172 = new ModuleMethod(this, 150, null, 0);
    final ModuleMethod lambda$Fn173 = new ModuleMethod(this, 151, null, 8194);
    LList maybe$Mnstart$Plend;
    Object s;

    public Object apply0(ModuleMethod modulemethod)
    {
        if (modulemethod.selector == 150)
        {
            return lambda172();
        } else
        {
            return super.apply0(modulemethod);
        }
    }

    public Object apply2(ModuleMethod modulemethod, Object obj, Object obj1)
    {
        if (modulemethod.selector == 151)
        {
            return lambda173(obj, obj1);
        } else
        {
            return super.apply2(modulemethod, obj, obj1);
        }
    }

    Object lambda172()
    {
        return srfi13.stringParseFinalStart$PlEnd(srfi13.string$Mnfilter, s, maybe$Mnstart$Plend);
    }

    CharSequence lambda173(Object obj, Object obj1)
    {
        maybe.Mnstart.Plend plend = new <init>();
        plend.staticLink = this;
        Object obj4;
        if (misc.isProcedure(criterion))
        {
            Object obj6 = AddOp.$Mn.apply2(obj1, obj);
            gnu.kawa.functions.ApplyToArgs applytoargs;
            Location location;
            Object obj2;
            Object aobj[];
            Object obj3;
            int i;
            gnu.kawa.functions.ApplyToArgs applytoargs1;
            Location location1;
            Object obj5;
            int j;
            Object obj7;
            CharSequence charsequence;
            int k;
            try
            {
                j = ((Number)obj6).intValue();
            }
            catch (ClassCastException classcastexception1)
            {
                throw new WrongType(classcastexception1, "make-string", 1, obj6);
            }
            plend.temp = strings.makeString(j);
            obj7 = srfi13.stringFold$V(plend.Fn174, srfi13.Lit0, s, new Object[] {
                obj, obj1
            });
            if (Scheme.numEqu.apply2(obj7, obj6) != Boolean.FALSE)
            {
                return plend.temp;
            }
            charsequence = plend.temp;
            try
            {
                k = ((Number)obj7).intValue();
            }
            catch (ClassCastException classcastexception2)
            {
                throw new WrongType(classcastexception2, "substring", 3, obj7);
            }
            return strings.substring(charsequence, 0, k);
        }
        applytoargs = Scheme.applyToArgs;
        location = srfi13.loc$char$Mnset$Qu;
        try
        {
            obj2 = location.get();
        }
        catch (UnboundLocationException unboundlocationexception)
        {
            unboundlocationexception.setLine("srfi13.scm", 1124, 22);
            throw unboundlocationexception;
        }
        if (applytoargs.apply2(obj2, criterion) != Boolean.FALSE)
        {
            obj3 = criterion;
        } else
        if (characters.isChar(criterion))
        {
            applytoargs1 = Scheme.applyToArgs;
            location1 = srfi13.loc$char$Mnset;
            try
            {
                obj5 = location1.get();
            }
            catch (UnboundLocationException unboundlocationexception1)
            {
                unboundlocationexception1.setLine("srfi13.scm", 1125, 26);
                throw unboundlocationexception1;
            }
            obj3 = applytoargs1.apply2(obj5, criterion);
        } else
        {
            aobj = new Object[1];
            aobj[0] = criterion;
            obj3 = misc.error$V("string-delete criterion not predicate, char or char-set", aobj);
        }
        plend.cset = obj3;
        obj4 = srfi13.stringFold$V(plend.Fn175, srfi13.Lit0, s, new Object[] {
            obj, obj1
        });
        try
        {
            i = ((Number)obj4).intValue();
        }
        catch (ClassCastException classcastexception)
        {
            throw new WrongType(classcastexception, "make-string", 1, obj4);
        }
        plend.ans = strings.makeString(i);
        srfi13.stringFold$V(plend.Fn176, srfi13.Lit0, s, new Object[] {
            obj, obj1
        });
        return plend.ans;
    }

    public int match0(ModuleMethod modulemethod, CallContext callcontext)
    {
        if (modulemethod.selector == 150)
        {
            callcontext.proc = modulemethod;
            callcontext.pc = 0;
            return 0;
        } else
        {
            return super.match0(modulemethod, callcontext);
        }
    }

    public int match2(ModuleMethod modulemethod, Object obj, Object obj1, CallContext callcontext)
    {
        if (modulemethod.selector == 151)
        {
            callcontext.value1 = obj;
            callcontext.value2 = obj1;
            callcontext.proc = modulemethod;
            callcontext.pc = 2;
            return 0;
        } else
        {
            return super.match2(modulemethod, obj, obj1, callcontext);
        }
    }

    public lambda.Fn176()
    {
    }
}
