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
import gnu.lists.AttributePredicate;
import gnu.lists.SeqPosition;
import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import javax.xml.namespace.QName;

// Referenced classes of package gnu.kawa.xml:
//            NodeType, KNode, KAttr, ElementType

public class AttributeType extends NodeType
    implements TypeValue, Externalizable, AttributePredicate
{

    static final Method coerceMethod;
    static final Method coerceOrNullMethod;
    public static final ClassType typeAttributeType;
    Symbol qname;

    public AttributeType(Symbol symbol)
    {
        this(null, symbol);
    }

    public AttributeType(String s, Symbol symbol)
    {
        if (s == null || s.length() <= 0)
        {
            s = (new StringBuilder()).append("ATTRIBUTE ").append(symbol).append(" (*)").toString();
        }
        super(s);
        qname = symbol;
    }

    public static SeqPosition coerce(Object obj, String s, String s1)
    {
        KAttr kattr = coerceOrNull(obj, s, s1);
        if (kattr == null)
        {
            throw new ClassCastException();
        } else
        {
            return kattr;
        }
    }

    public static KAttr coerceOrNull(Object obj, String s, String s1)
    {
        KNode knode = NodeType.coerceOrNull(obj, 4);
        if (knode == null)
        {
            return null;
        }
        if (s1 != null && s1.length() == 0)
        {
            s1 = null;
        }
        Object obj1 = knode.getNextTypeObject();
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
        if ((s1 == s3 || s1 == null) && (s == s2 || s == null))
        {
            return (KAttr)knode;
        } else
        {
            return null;
        }
    }

    public static AttributeType make(Symbol symbol)
    {
        return new AttributeType(symbol);
    }

    public static AttributeType make(String s, String s1)
    {
        Symbol symbol;
        if (s != null)
        {
            symbol = Symbol.make(s, s1);
        } else
        if (s1 == "")
        {
            symbol = ElementType.MATCH_ANY_QNAME;
        } else
        {
            symbol = new Symbol(null, s1);
        }
        return new AttributeType(symbol);
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

    public Type getImplementationType()
    {
        return ClassType.make("gnu.kawa.xml.KAttr");
    }

    public final String getLocalName()
    {
        return qname.getLocalName();
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
        if (j == 35)
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

    public String toString()
    {
        return (new StringBuilder()).append("AttributeType ").append(qname).toString();
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
        typeAttributeType = ClassType.make("gnu.kawa.xml.AttributeType");
        coerceMethod = typeAttributeType.getDeclaredMethod("coerce", 3);
        coerceOrNullMethod = typeAttributeType.getDeclaredMethod("coerceOrNull", 3);
    }
}
