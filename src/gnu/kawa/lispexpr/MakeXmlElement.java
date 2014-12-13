// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.lispexpr;

import gnu.bytecode.ClassType;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.kawa.xml.MakeElement;
import gnu.kawa.xml.MakeText;
import gnu.kawa.xml.XmlNamespace;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import gnu.xml.NamespaceBinding;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class MakeXmlElement extends Syntax
{

    public static final MakeXmlElement makeXml;
    static final ClassType typeNamespace = ClassType.make("gnu.mapping.Namespace");

    public MakeXmlElement()
    {
    }

    public Expression rewriteForm(Pair pair, Translator translator)
    {
        Object obj1;
        boolean flag;
        NamespaceBinding namespacebinding;
        MakeElement makeelement;
        Pair pair1 = (Pair)pair.getCdr();
        Object obj = pair1.getCar();
        obj1 = pair1.getCdr();
        flag = false;
        namespacebinding = translator.xmlElementNamespaces;
        NamespaceBinding namespacebinding1 = namespacebinding;
        while (obj instanceof Pair) 
        {
            if (!flag)
            {
                translator.letStart();
                flag = true;
            }
            Pair pair2 = (Pair)obj;
            Pair pair3 = (Pair)pair2.getCar();
            String s = (String)pair3.getCar();
            String s1;
            Object obj3;
            StringBuilder stringbuilder;
            if (s.length() == 0)
            {
                s1 = null;
            } else
            {
                s1 = s.intern();
            }
            obj3 = pair3.getCdr();
            stringbuilder = new StringBuilder();
            while (obj3 instanceof Pair) 
            {
                Pair pair4 = (Pair)obj3;
                Object obj5 = pair4.getCar();
                Object obj6;
                if (LList.listLength(obj5, false) == 2 && (obj5 instanceof Pair) && ((Pair)obj5).getCar() == MakeText.makeText)
                {
                    obj6 = ((Pair)((Pair)obj5).getCdr()).getCar();
                } else
                {
                    obj6 = translator.rewrite_car(pair4, false).valueIfConstant();
                }
                if (obj6 == null)
                {
                    Object obj7 = translator.pushPositionOf(pair4);
                    translator.error('e', "namespace URI must be literal");
                    translator.popPositionOf(obj7);
                } else
                {
                    stringbuilder.append(obj6);
                }
                obj3 = pair4.getCdr();
            }
            String s2 = stringbuilder.toString().intern();
            String s3;
            NamespaceBinding namespacebinding2;
            Object obj4;
            gnu.mapping.Symbol symbol;
            ClassType classtype;
            QuoteExp quoteexp;
            if (s2 == "")
            {
                s3 = null;
            } else
            {
                s3 = s2;
            }
            namespacebinding2 = new NamespaceBinding(s1, s3, namespacebinding1);
            if (s1 == null)
            {
                obj4 = Namespace.valueOf(s2);
                s1 = "[default-element-namespace]";
            } else
            {
                obj4 = XmlNamespace.getInstance(s1, s2);
            }
            symbol = Namespace.EmptyNamespace.getSymbol(s1);
            classtype = typeNamespace;
            quoteexp = new QuoteExp(obj4);
            translator.letVariable(symbol, classtype, quoteexp).setFlag(0x206000L);
            obj = pair2.getCdr();
            namespacebinding1 = namespacebinding2;
        }
        makeelement = new MakeElement();
        makeelement.setNamespaceNodes(namespacebinding1);
        translator.xmlElementNamespaces = namespacebinding1;
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_407;
        }
        translator.letEnter();
        Object obj2 = translator.rewrite(Translator.makePair(pair, makeelement, obj1));
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_438;
        }
        gnu.expr.LetExp letexp = translator.letDone(((Expression) (obj2)));
        obj2 = letexp;
        translator.xmlElementNamespaces = namespacebinding;
        return ((Expression) (obj2));
        Exception exception;
        exception;
        translator.xmlElementNamespaces = namespacebinding;
        throw exception;
    }

    static 
    {
        makeXml = new MakeXmlElement();
        makeXml.setName("$make-xml$");
    }
}
