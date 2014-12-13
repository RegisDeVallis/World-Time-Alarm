// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;


// Referenced classes of package gnu.mapping:
//            Environment, SimpleEnvironment

static class  extends InheritableThreadLocal
{

    protected Environment childValue(Environment environment)
    {
        if (environment == null)
        {
            environment = Environment.getCurrent();
        }
        SimpleEnvironment simpleenvironment = environment.cloneForThread();
        simpleenvironment.flags = 8 | simpleenvironment.flags;
        simpleenvironment.flags = 0xffffffef & simpleenvironment.flags;
        return simpleenvironment;
    }

    protected volatile Object childValue(Object obj)
    {
        return childValue((Environment)obj);
    }

    ()
    {
    }
}
