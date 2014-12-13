// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.xml;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.Compilation;
import gnu.expr.TypeValue;
import gnu.lists.AbstractSequence;
import gnu.lists.ElementPredicate;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.xml.NamespaceBinding;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.xml.namespace.QName;

// Referenced classes of package gnu.kawa.xml:
//            NodeType, KElement, MakeElement

public class ElementType extends NodeType
    implements TypeValue, Externalizable, ElementPredicate
{

    public static final String MATCH_ANY_LOCALNAME = "";
    public static final Symbol MATCH_ANY_QNAME = new Symbol(null, "");
    public static final ElementType anyElement = make(null, null);
    static final Method coerceMethod;
    static final Method coerceOrNullMethod;
    public static final ClassType typeElementType;
    NamespaceBinding namespaceNodes;
    Symbol qname;

    public ElementType(Symbol symbol)
    {
        this(null, symbol);
    }

    public ElementType(String s, Symbol symbol)
    {
        if (s == null || s.length() <= 0)
        {
            s = (new StringBuilder()).append("ELEMENT ").append(symbol).append(" (*)").toString();
        }
        super(s);
        qname = symbol;
    }

    public static KElement coerce(Object obj, String s, String s1)
    {
        KElement kelement = coerceOrNull(obj, s, s1);
        if (kelement == null)
        {
            throw new ClassCastException();
        } else
        {
            return kelement;
        }
    }

    public static KElement coerceOrNull(Object obj, String s, String s1)
    {
        KElement kelement = (KElement)NodeType.coerceOrNull(obj, 2);
        if (kelement == null)
        {
            kelement = null;
        } else
        {
            if (s1 != null && s1.length() == 0)
            {
                s1 = null;
            }
            Object obj1 = kelement.getNextTypeObject();
            String s2;
            String s3;
            if (obj1 instanceof Symbol)
            {
                Symbol symbol = (Symbol)obj1;
                s2 = symbol.getNamespaceURI();
                s3 = symbol.getLocalName();
            } else
            if (obj1 instanceof QName)
            {
                QName qname1 = (QName)obj1;
                s2 = qname1.getNamespaceURI();
                s3 = qname1.getLocalPart();
            } else
            {
                s2 = "";
                s3 = obj1.toString().intern();
            }
            if (s1 != s3 && s1 != null || s != s2 && s != null)
            {
                return null;
            }
        }
        return kelement;
    }

    public static ElementType make(Symbol symbol)
    {
        return new ElementType(symbol);
    }

    public static ElementType make(String s, String s1)
    {
        Symbol symbol;
        if (s != null)
        {
            symbol = Symbol.make(s, s1);
        } else
        if (s1 == "")
        {
            symbol = MATCH_ANY_QNAME;
        } else
        {
            symbol = new Symbol(null, s1);
        }
        return new ElementType(symbol);
    }

    public Object coerceFromObject(Object obj)
    {
        return coerce(obj, qname.getNamespaceURI(), qname.getLocalName());
    }

    public void emitCoerceFromObject(CodeAttr codeattr)
    {
        codeattr.emitPushString(qname.getNamespaceURI());
        codeattr.emitPushString(qname.getLocalName());
        codeattr.emitInvokeStatic(coerceMethod);
    }

    protected void emitCoerceOrNullMethod(Variable variable, Compilation compilation)
    {
        CodeAttr codeattr = compilation.getCode();
        if (variable != null)
        {
            codeattr.emitLoad(variable);
        }
        codeattr.emitPushString(qname.getNamespaceURI());
        codeattr.emitPushString(qname.getLocalName());
        codeattr.emitInvokeStatic(coerceOrNullMethod);
    }

    public Procedure getConstructor()
    {
        MakeElement makeelement = new MakeElement();
        makeelement.tag = qname;
        makeelement.setHandlingKeywordParameters(true);
        if (namespaceNodes != null)
        {
            makeelement.setNamespaceNodes(namespaceNodes);
        }
        return makeelement;
    }

    public Type getImplementationType()
    {
        return ClassType.make("gnu.kawa.xml.KElement");
    }

    public final String getLocalName()
    {
        return qname.getLocalName();
    }

    public NamespaceBinding getNamespaceNodes()
    {
        return namespaceNodes;
    }

    public final String getNamespaceURI()
    {
        return qname.getNamespaceURI();
    }

    public boolean isInstance(AbstractSequence abstractsequence, int i, Object obj)
    {
        String s = qname.getNamespaceURI();
        String s1 = qname.getLocalName();
        String s2;
        String s3;
        if (obj instanceof Symbol)
        {
            Symbol symbol = (Symbol)obj;
            s2 = symbol.getNamespaceURI();
            s3 = symbol.getLocalName();
        } else
        if (obj instanceof QName)
        {
            QName qname1 = (QName)obj;
            s2 = qname1.getNamespaceURI();
            s3 = qname1.getLocalPart();
        } else
        {
            s2 = "";
            s3 = obj.toString().intern();
        }
        if (s1 != null && s1.length() == 0)
        {
            s1 = null;
        }
        return (s1 == s3 || s1 == null) && (s == s2 || s == null);
    }

    public boolean isInstance(Object obj)
    {
        return coerceOrNull(obj, qname.getNamespaceURI(), qname.getLocalName()) != null;
    }

    public boolean isInstancePos(AbstractSequence abstractsequence, int i)
    {
        int j = abstractsequence.getNextKind(i);
        if (j == 33)
        {
            return isInstance(abstractsequence, i, abstractsequence.getNextTypeObject(i));
        }
        if (j == 32)
        {
            return isInstance(abstractsequence.getPosNext(i));
        } else
        {
            return false;
        }
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        String s = objectinput.readUTF();
        if (s.length() > 0)
        {
            setName(s);
        }
        qname = (Symbol)objectinput.readObject();
    }

    public void setNamespaceNodes(NamespaceBinding namespacebinding)
    {
        namespaceNodes = namespacebinding;
    }

    public String toString()
    {
        return (new StringBuilder()).append("ElementType ").append(qname).toString();
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        String s = getName();
        if (s == null)
        {
            s = "";
        }
        objectoutput.writeUTF(s);
        objectoutput.writeObject(qname);
    }

    static 
    {
        typeElementType = ClassType.make("gnu.kawa.xml.ElementType");
        coerceMethod = typeElementType.getDeclaredMethod("coerce", 3);
        coerceOrNullMethod = typeElementType.getDeclaredMethod("coerceOrNull", 3);
    }
}
