// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.ecmascript;

import gnu.expr.ApplyExp;
import gnu.expr.BeginExp;
import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.IfExp;
import gnu.expr.LambdaExp;
import gnu.expr.QuoteExp;
import gnu.expr.ReferenceExp;
import gnu.expr.SetExp;
import gnu.lists.Sequence;
import gnu.mapping.Environment;
import gnu.mapping.InPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.TtyInPort;
import gnu.mapping.Values;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Vector;
import kawa.standard.Scheme;

// Referenced classes of package gnu.ecmascript:
//            Lexer, Prompter, Reserved

public class Parser
{

    public static final Expression emptyArgs[] = new Expression[0];
    static Expression emptyStatement;
    public static Expression eofExpr;
    public int errors;
    Lexer lexer;
    InPort port;
    Object previous_token;
    Object token;

    public Parser(InPort inport)
    {
        port = inport;
        lexer = new Lexer(inport);
    }

    public static void main(String args[])
    {
        Parser parser;
        OutPort outport;
        new Scheme();
        InPort inport = InPort.inDefault();
        if (inport instanceof TtyInPort)
        {
            Prompter prompter = new Prompter();
            ((TtyInPort)inport).setPrompter((Procedure)prompter);
        }
        parser = new Parser(inport);
        outport = OutPort.outDefault();
_L1:
        Expression expression;
        Object obj;
        try
        {
            expression = parser.parseStatement();
            if (expression == eofExpr)
            {
                return;
            }
        }
        catch (Throwable throwable)
        {
            System.err.println((new StringBuilder()).append("caught exception:").append(throwable).toString());
            throwable.printStackTrace(System.err);
            return;
        }
        outport.print("[Expression: ");
        expression.print(outport);
        outport.println("]");
        obj = expression.eval(Environment.user());
        outport.print("result: ");
        outport.print(obj);
        outport.println();
          goto _L1
    }

    public Expression buildLoop(Expression expression, Expression expression1, Expression expression2, Expression expression3)
    {
        if (expression != null)
        {
            Expression aexpression[] = new Expression[2];
            aexpression[0] = expression;
            aexpression[1] = buildLoop(null, expression1, expression2, expression3);
            return new BeginExp(aexpression);
        } else
        {
            throw new Error("not implemented - buildLoop");
        }
    }

    public String getIdentifier()
        throws IOException, SyntaxException
    {
        Object obj = getToken();
        if (obj instanceof String)
        {
            return (String)obj;
        } else
        {
            syntaxError("missing identifier");
            return "??";
        }
    }

    public void getSemicolon()
        throws IOException, SyntaxException
    {
        token = peekToken();
        if (token == Lexer.semicolonToken)
        {
            skipToken();
        } else
        if (token != Lexer.rbraceToken && token != Lexer.eofToken && previous_token != Lexer.eolToken)
        {
            syntaxError("missing ';' after expression");
            return;
        }
    }

    public Object getToken()
        throws IOException, SyntaxException
    {
        Object obj = peekToken();
        skipToken();
        return obj;
    }

    public Expression makeCallExpression(Expression expression, Expression aexpression[])
    {
        return new ApplyExp(expression, aexpression);
    }

    public Expression makeNewExpression(Expression expression, Expression aexpression[])
    {
        if (aexpression == null)
        {
            aexpression = emptyArgs;
        }
        return new ApplyExp(null, aexpression);
    }

    public Expression makePropertyAccessor(Expression expression, Expression expression1)
    {
        return null;
    }

    public Expression[] parseArguments()
        throws IOException, SyntaxException
    {
        skipToken();
        if (peekToken() == Lexer.rparenToken)
        {
            skipToken();
            return emptyArgs;
        }
        Vector vector = new Vector(10);
        do
        {
            Object obj;
            do
            {
                vector.addElement(parseAssignmentExpression());
                obj = getToken();
                if (obj == Lexer.rparenToken)
                {
                    Expression aexpression[] = new Expression[vector.size()];
                    vector.copyInto(aexpression);
                    return aexpression;
                }
            } while (obj == Lexer.commaToken);
            syntaxError((new StringBuilder()).append("invalid token '").append(obj).append("' in argument list").toString());
        } while (true);
    }

    public Expression parseAssignmentExpression()
        throws IOException, SyntaxException
    {
        Object obj;
        Object obj1;
        obj = parseConditionalExpression();
        obj1 = peekToken();
        if (obj1 != Lexer.equalToken) goto _L2; else goto _L1
_L1:
        Expression expression;
        skipToken();
        expression = parseAssignmentExpression();
        if (!(obj instanceof ReferenceExp)) goto _L4; else goto _L3
_L3:
        SetExp setexp = new SetExp(((ReferenceExp)obj).getName(), expression);
        setexp.setDefining(true);
        obj = setexp;
_L6:
        return ((Expression) (obj));
_L4:
        return syntaxError("unmplemented non-symbol ihs in assignment");
_L2:
        if (obj1 instanceof Reserved)
        {
            Reserved reserved = (Reserved)obj1;
            if (reserved.isAssignmentOp())
            {
                skipToken();
                Expression aexpression[] = {
                    obj, parseAssignmentExpression()
                };
                return new ApplyExp(new QuoteExp(reserved.proc), aexpression);
            }
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public Expression parseBinaryExpression(int i)
        throws IOException, SyntaxException
    {
        Object obj = parseUnaryExpression();
_L5:
        token = peekToken();
        if (token instanceof Reserved) goto _L2; else goto _L1
_L1:
        Reserved reserved;
        return ((Expression) (obj));
_L2:
        if ((reserved = (Reserved)token).prio < i) goto _L1; else goto _L3
_L3:
        getToken();
        Expression aexpression[] = {
            obj, parseBinaryExpression(1 + reserved.prio)
        };
        obj = new ApplyExp(new QuoteExp(reserved.proc), aexpression);
        if (true) goto _L5; else goto _L4
_L4:
    }

    public Expression parseBlock()
        throws IOException, SyntaxException
    {
        Expression aexpression[];
        int i;
        aexpression = null;
        if (getToken() != Lexer.lbraceToken)
        {
            return syntaxError("extened '{'");
        }
        i = 0;
_L6:
        boolean flag;
        token = peekToken();
        if (token == Lexer.rbraceToken)
        {
            skipToken();
            if (aexpression == null)
            {
                return emptyStatement;
            }
            flag = true;
        } else
        {
            flag = false;
        }
        if (aexpression != null) goto _L2; else goto _L1
_L1:
        aexpression = new Expression[2];
_L4:
        if (flag)
        {
            return new BeginExp(aexpression);
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (flag ? aexpression.length != i : aexpression.length <= i)
        {
            int j;
            Expression aexpression1[];
            if (flag)
            {
                j = i;
            } else
            {
                j = 2 * aexpression.length;
            }
            aexpression1 = new Expression[j];
            System.arraycopy(aexpression, 0, aexpression1, 0, i);
            aexpression = aexpression1;
        }
        if (true) goto _L4; else goto _L3
_L3:
        int k = i + 1;
        aexpression[i] = parseStatement();
        i = k;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public Expression parseConditionalExpression()
        throws IOException, SyntaxException
    {
        Expression expression = parseBinaryExpression(1);
        if (peekToken() != Lexer.condToken)
        {
            return expression;
        }
        skipToken();
        Expression expression1 = parseAssignmentExpression();
        if (getToken() != Lexer.colonToken)
        {
            return syntaxError("expected ':' in conditional expression");
        } else
        {
            return new IfExp(expression, expression1, parseAssignmentExpression());
        }
    }

    public Expression parseExpression()
        throws IOException, SyntaxException
    {
        Expression aexpression[];
        int i;
        aexpression = null;
        i = 0;
_L6:
        Expression expression;
        boolean flag;
        expression = parseAssignmentExpression();
        if (peekToken() != Lexer.commaToken)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (aexpression != null) goto _L2; else goto _L1
_L1:
        if (flag)
        {
            return expression;
        }
        aexpression = new Expression[2];
_L4:
        int k;
        k = i + 1;
        aexpression[i] = expression;
        if (flag)
        {
            return new BeginExp(aexpression);
        }
        break; /* Loop/switch isn't completed */
_L2:
        if (flag ? aexpression.length != i + 1 : aexpression.length <= i)
        {
            int j;
            Expression aexpression1[];
            if (flag)
            {
                j = i + 1;
            } else
            {
                j = 2 * aexpression.length;
            }
            aexpression1 = new Expression[j];
            System.arraycopy(aexpression, 0, aexpression1, 0, i);
            aexpression = aexpression1;
        }
        if (true) goto _L4; else goto _L3
_L3:
        skipToken();
        i = k;
        if (true) goto _L6; else goto _L5
_L5:
    }

    public Expression parseFunctionDefinition()
        throws IOException, SyntaxException
    {
        String s;
        Vector vector;
        skipToken();
        s = getIdentifier();
        Object obj = getToken();
        if (obj != Lexer.lparenToken)
        {
            return syntaxError((new StringBuilder()).append("expected '(' - got:").append(obj).toString());
        }
        vector = new Vector(10);
        if (peekToken() != Lexer.rparenToken) goto _L2; else goto _L1
_L1:
        skipToken();
_L3:
        LambdaExp lambdaexp = new LambdaExp(parseBlock());
        lambdaexp.setName(s);
        SetExp setexp = new SetExp(s, lambdaexp);
        setexp.setDefining(true);
        return setexp;
_L4:
        Object obj1;
        if (obj1 != Lexer.commaToken)
        {
            syntaxError((new StringBuilder()).append("invalid token '").append(obj1).append("' in argument list").toString());
        }
_L2:
        vector.addElement(getIdentifier());
        obj1 = getToken();
        if (obj1 != Lexer.rparenToken) goto _L4; else goto _L3
    }

    public Expression parseIfStatement()
        throws IOException, SyntaxException
    {
        skipToken();
        Object obj = getToken();
        if (obj != Lexer.lparenToken)
        {
            return syntaxError((new StringBuilder()).append("expected '(' - got:").append(obj).toString());
        }
        Expression expression = parseExpression();
        Object obj1 = getToken();
        if (obj1 != Lexer.rparenToken)
        {
            return syntaxError((new StringBuilder()).append("expected ')' - got:").append(obj1).toString());
        }
        Expression expression1 = parseStatement();
        Expression expression2;
        if (peekToken() == Lexer.elseToken)
        {
            skipToken();
            expression2 = parseStatement();
        } else
        {
            expression2 = null;
        }
        return new IfExp(expression, expression1, expression2);
    }

    public Expression parseLeftHandSideExpression()
        throws IOException, SyntaxException
    {
        int i = 0;
        for (; peekToken() == Lexer.newToken; skipToken())
        {
            i++;
        }

        Expression expression = parsePrimaryExpression();
        do
        {
            Object obj = peekToken();
            if (obj == Lexer.dotToken)
            {
                skipToken();
                expression = makePropertyAccessor(expression, new QuoteExp(getIdentifier()));
                continue;
            }
            if (obj == Lexer.lbracketToken)
            {
                skipToken();
                Expression expression1 = parseExpression();
                Object obj1 = getToken();
                if (obj1 != Lexer.rbracketToken)
                {
                    return syntaxError((new StringBuilder()).append("expected ']' - got:").append(obj1).toString());
                }
                expression = makePropertyAccessor(expression, expression1);
                continue;
            }
            if (obj != Lexer.lparenToken)
            {
                break;
            }
            Expression aexpression[] = parseArguments();
            System.err.println((new StringBuilder()).append("after parseArgs:").append(peekToken()).toString());
            if (i > 0)
            {
                expression = makeNewExpression(expression, aexpression);
                i--;
            } else
            {
                expression = makeCallExpression(expression, aexpression);
            }
        } while (true);
        for (; i > 0; i--)
        {
            expression = makeNewExpression(expression, null);
        }

        return expression;
    }

    public Expression parsePostfixExpression()
        throws IOException, SyntaxException
    {
        Expression expression = parseLeftHandSideExpression();
        Object obj = peekTokenOrLine();
        if (obj != Reserved.opPlusPlus && obj != Reserved.opMinusMinus)
        {
            return expression;
        } else
        {
            skipToken();
            Expression aexpression[] = {
                expression
            };
            return new ApplyExp(new QuoteExp(((Reserved)obj).proc), aexpression);
        }
    }

    public Expression parsePrimaryExpression()
        throws IOException, SyntaxException
    {
        Object obj = getToken();
        if (obj instanceof QuoteExp)
        {
            return (QuoteExp)obj;
        }
        if (obj instanceof String)
        {
            return new ReferenceExp((String)obj);
        }
        if (obj == Lexer.lparenToken)
        {
            Expression expression = parseExpression();
            Object obj1 = getToken();
            if (obj1 != Lexer.rparenToken)
            {
                return syntaxError((new StringBuilder()).append("expected ')' - got:").append(obj1).toString());
            } else
            {
                return expression;
            }
        } else
        {
            return syntaxError((new StringBuilder()).append("unexpected token: ").append(obj).toString());
        }
    }

    public Expression parseStatement()
        throws IOException, SyntaxException
    {
        Object obj = peekToken();
        if (!(obj instanceof Reserved)) goto _L2; else goto _L1
_L1:
        ((Reserved)obj).prio;
        JVM INSTR lookupswitch 3: default 52
    //                   31: 63
    //                   32: 68
    //                   41: 73;
           goto _L2 _L3 _L4 _L5
_L2:
        if (obj == Lexer.eofToken)
        {
            return eofExpr;
        }
        break; /* Loop/switch isn't completed */
_L3:
        return parseIfStatement();
_L4:
        return parseWhileStatement();
_L5:
        return parseFunctionDefinition();
        if (obj == Lexer.semicolonToken)
        {
            skipToken();
            return emptyStatement;
        }
        if (obj == Lexer.lbraceToken)
        {
            return parseBlock();
        } else
        {
            Expression expression = parseExpression();
            getSemicolon();
            return expression;
        }
    }

    public Expression parseUnaryExpression()
        throws IOException, SyntaxException
    {
        return parsePostfixExpression();
    }

    public Expression parseWhileStatement()
        throws IOException, SyntaxException
    {
        skipToken();
        Object obj = getToken();
        if (obj != Lexer.lparenToken)
        {
            return syntaxError((new StringBuilder()).append("expected '(' - got:").append(obj).toString());
        }
        Expression expression = parseExpression();
        Object obj1 = getToken();
        if (obj1 != Lexer.rparenToken)
        {
            return syntaxError((new StringBuilder()).append("expected ')' - got:").append(obj1).toString());
        } else
        {
            return buildLoop(null, expression, null, parseStatement());
        }
    }

    public Object peekToken()
        throws IOException, SyntaxException
    {
        if (token == null)
        {
            token = lexer.getToken();
        }
        for (; token == Lexer.eolToken; token = lexer.getToken())
        {
            skipToken();
        }

        return token;
    }

    public Object peekTokenOrLine()
        throws IOException, SyntaxException
    {
        if (token == null)
        {
            token = lexer.getToken();
        }
        return token;
    }

    public final void skipToken()
    {
        if (token != Lexer.eofToken)
        {
            previous_token = token;
            token = null;
        }
    }

    public Expression syntaxError(String s)
    {
        errors = 1 + errors;
        OutPort outport = OutPort.errDefault();
        String s1 = port.getName();
        int i = 1 + port.getLineNumber();
        int j = 1 + port.getColumnNumber();
        if (i > 0)
        {
            if (s1 != null)
            {
                outport.print(s1);
            }
            outport.print(':');
            outport.print(i);
            if (j > 1)
            {
                outport.print(':');
                outport.print(j);
            }
            outport.print(": ");
        }
        outport.println(s);
        return new ErrorExp(s);
    }

    static 
    {
        eofExpr = new QuoteExp(Sequence.eofValue);
        emptyStatement = new QuoteExp(Values.empty);
    }
}
