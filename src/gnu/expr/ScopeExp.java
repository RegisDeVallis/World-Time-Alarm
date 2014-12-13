// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.expr;

import gnu.bytecode.CodeAttr;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;

// Referenced classes of package gnu.expr:
//            Expression, Compilation, Declaration, LambdaExp, 
//            ModuleExp, Language, ExpVisitor

public abstract class ScopeExp extends Expression
{

    static int counter;
    Declaration decls;
    protected int frameSize;
    public int id;
    Declaration last;
    public ScopeExp outer;
    private Scope scope;

    public ScopeExp()
    {
        int i = 1 + counter;
        counter = i;
        id = i;
    }

    public static void duplicateDeclarationError(Declaration declaration, Declaration declaration1, Compilation compilation)
    {
        compilation.error('e', declaration1, "duplicate declaration of '", "'");
        compilation.error('e', declaration, "(this is the previous declaration of '", "')");
    }

    public static int nesting(ScopeExp scopeexp)
    {
        int i;
        for (i = 0; scopeexp != null; i++)
        {
            scopeexp = scopeexp.outer;
        }

        return i;
    }

    public void add(Declaration declaration)
    {
        if (last == null)
        {
            decls = declaration;
        } else
        {
            last.next = declaration;
        }
        last = declaration;
        declaration.context = this;
    }

    public void add(Declaration declaration, Declaration declaration1)
    {
        if (declaration == null)
        {
            declaration1.next = decls;
            decls = declaration1;
        } else
        {
            declaration1.next = declaration.next;
            declaration.next = declaration1;
        }
        if (last == declaration)
        {
            last = declaration1;
        }
        declaration1.context = this;
    }

    public final Declaration addDeclaration(Object obj)
    {
        Declaration declaration = new Declaration(obj);
        add(declaration);
        return declaration;
    }

    public final Declaration addDeclaration(Object obj, Type type)
    {
        Declaration declaration = new Declaration(obj, type);
        add(declaration);
        return declaration;
    }

    public final void addDeclaration(Declaration declaration)
    {
        add(declaration);
    }

    public int countDecls()
    {
        int i = 0;
        for (Declaration declaration = firstDecl(); declaration != null; declaration = declaration.nextDecl())
        {
            i++;
        }

        return i;
    }

    public int countNonDynamicDecls()
    {
        int i = 0;
        for (Declaration declaration = firstDecl(); declaration != null; declaration = declaration.nextDecl())
        {
            if (!declaration.getFlag(0x10000000L))
            {
                i++;
            }
        }

        return i;
    }

    public LambdaExp currentLambda()
    {
        ScopeExp scopeexp = this;
        do
        {
            if (scopeexp == null)
            {
                return null;
            }
            if (scopeexp instanceof LambdaExp)
            {
                return (LambdaExp)scopeexp;
            }
            scopeexp = scopeexp.outer;
        } while (true);
    }

    public ModuleExp currentModule()
    {
        ScopeExp scopeexp = this;
        do
        {
            if (scopeexp == null)
            {
                return null;
            }
            if (scopeexp instanceof ModuleExp)
            {
                return (ModuleExp)scopeexp;
            }
            scopeexp = scopeexp.outer;
        } while (true);
    }

    public Declaration firstDecl()
    {
        return decls;
    }

    public Declaration getDefine(Object obj, char c, Compilation compilation)
    {
        Declaration declaration = lookup(obj);
        if (declaration == null)
        {
            return addDeclaration(obj);
        }
        if ((0x10200L & declaration.flags) != 0L)
        {
            declaration.flags = 0xfffffffffffefdffL & declaration.flags;
            return declaration;
        } else
        {
            Declaration declaration1 = addDeclaration(obj);
            duplicateDeclarationError(declaration, declaration1, compilation);
            return declaration1;
        }
    }

    public Declaration getNoDefine(Object obj)
    {
        Declaration declaration = lookup(obj);
        if (declaration == null)
        {
            declaration = addDeclaration(obj);
            declaration.flags = 0x10200L | declaration.flags;
        }
        return declaration;
    }

    public Scope getVarScope()
    {
        Scope scope1 = scope;
        if (scope1 == null)
        {
            scope1 = new Scope();
            scope = scope1;
        }
        return scope1;
    }

    public Declaration lookup(Object obj)
    {
        if (obj != null)
        {
            for (Declaration declaration = firstDecl(); declaration != null; declaration = declaration.nextDecl())
            {
                if (obj.equals(declaration.symbol))
                {
                    return declaration;
                }
            }

        }
        return null;
    }

    public Declaration lookup(Object obj, Language language, int i)
    {
        for (Declaration declaration = firstDecl(); declaration != null; declaration = declaration.nextDecl())
        {
            if (obj.equals(declaration.symbol) && language.hasNamespace(declaration, i))
            {
                return declaration;
            }
        }

        return null;
    }

    public boolean nestedIn(ScopeExp scopeexp)
    {
        for (ScopeExp scopeexp1 = this; scopeexp1 != null; scopeexp1 = scopeexp1.outer)
        {
            if (scopeexp1 == scopeexp)
            {
                return true;
            }
        }

        return false;
    }

    public void popScope(CodeAttr codeattr)
    {
        for (Declaration declaration = firstDecl(); declaration != null; declaration = declaration.nextDecl())
        {
            declaration.var = null;
        }

        codeattr.popScope();
        scope = null;
    }

    public void remove(Declaration declaration)
    {
        Declaration declaration1 = null;
        Declaration declaration2 = firstDecl();
        do
        {
label0:
            {
                if (declaration2 != null)
                {
                    if (declaration2 != declaration)
                    {
                        break label0;
                    }
                    remove(declaration1, declaration);
                }
                return;
            }
            declaration1 = declaration2;
            declaration2 = declaration2.nextDecl();
        } while (true);
    }

    public void remove(Declaration declaration, Declaration declaration1)
    {
        if (declaration == null)
        {
            decls = declaration1.next;
        } else
        {
            declaration.next = declaration1.next;
        }
        if (last == declaration1)
        {
            last = declaration;
        }
    }

    public void replaceFollowing(Declaration declaration, Declaration declaration1)
    {
        Declaration declaration2;
        if (declaration == null)
        {
            declaration2 = decls;
            decls = declaration1;
        } else
        {
            declaration2 = declaration.next;
            declaration.next = declaration1;
        }
        declaration1.next = declaration2.next;
        if (last == declaration2)
        {
            last = declaration1;
        }
        declaration2.next = null;
        declaration1.context = this;
    }

    protected void setIndexes()
    {
        Declaration declaration = firstDecl();
        int i;
        int j;
        for (i = 0; declaration != null; i = j)
        {
            j = i + 1;
            declaration.evalIndex = i;
            declaration = declaration.nextDecl();
        }

        frameSize = i;
    }

    public String toString()
    {
        return (new StringBuilder()).append(getClass().getName()).append("#").append(id).toString();
    }

    public ScopeExp topLevel()
    {
        ScopeExp scopeexp = this;
        do
        {
            ScopeExp scopeexp1 = scopeexp.outer;
            if (scopeexp1 == null || (scopeexp1 instanceof ModuleExp))
            {
                return scopeexp;
            }
            scopeexp = scopeexp1;
        } while (true);
    }

    protected Object visit(ExpVisitor expvisitor, Object obj)
    {
        return expvisitor.visitScopeExp(this, obj);
    }
}
