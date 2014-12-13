// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.mapping;


// Referenced classes of package gnu.mapping:
//            NamedLocation, Named, SharedLocation, Symbol, 
//            SimpleEnvironment, IndirectableLocation, Environment

public class ThreadLocation extends NamedLocation
    implements Named
{
    public class InheritingLocation extends InheritableThreadLocal
    {

        final ThreadLocation this$0;

        protected SharedLocation childValue(NamedLocation namedlocation)
        {
            if (property != ThreadLocation.ANONYMOUS)
            {
                throw new Error();
            }
            if (namedlocation == null)
            {
                namedlocation = (SharedLocation)getLocation();
            }
            Object obj = namedlocation;
            if (((NamedLocation) (obj)).base == null)
            {
                SharedLocation sharedlocation = new SharedLocation(name, property, 0);
                sharedlocation.value = ((NamedLocation) (obj)).value;
                obj.base = sharedlocation;
                obj.value = null;
                obj = sharedlocation;
            }
            SharedLocation sharedlocation1 = new SharedLocation(name, property, 0);
            sharedlocation1.value = null;
            sharedlocation1.base = ((Location) (obj));
            return sharedlocation1;
        }

        protected volatile Object childValue(Object obj)
        {
            return childValue((NamedLocation)obj);
        }

        public InheritingLocation()
        {
            this$0 = ThreadLocation.this;
            super();
        }
    }


    public static final String ANONYMOUS = new String("(dynamic)");
    static int counter;
    static SimpleEnvironment env;
    SharedLocation global;
    private int hash;
    private ThreadLocal thLocal;

    public ThreadLocation()
    {
        this((new StringBuilder()).append("param#").append(nextCounter()).toString());
    }

    private ThreadLocation(Symbol symbol)
    {
        super(symbol, ANONYMOUS);
        thLocal = new InheritingLocation();
        String s;
        if (symbol == null)
        {
            s = null;
        } else
        {
            s = symbol.toString();
        }
        global = new SharedLocation(Symbol.makeUninterned(s), null, 0);
    }

    public ThreadLocation(Symbol symbol, Object obj, SharedLocation sharedlocation)
    {
        super(symbol, obj);
        hash = symbol.hashCode() ^ System.identityHashCode(obj);
        global = sharedlocation;
    }

    public ThreadLocation(String s)
    {
        super(Symbol.makeUninterned(s), ANONYMOUS);
        thLocal = new InheritingLocation();
        global = new SharedLocation(name, null, 0);
    }

    public static ThreadLocation getInstance(Symbol symbol, Object obj)
    {
        gnu/mapping/ThreadLocation;
        JVM INSTR monitorenter ;
        IndirectableLocation indirectablelocation;
        if (env == null)
        {
            env = new SimpleEnvironment("[thread-locations]");
        }
        indirectablelocation = (IndirectableLocation)env.getLocation(symbol, obj);
        if (indirectablelocation.base == null) goto _L2; else goto _L1
_L1:
        ThreadLocation threadlocation1 = (ThreadLocation)indirectablelocation.base;
_L4:
        gnu/mapping/ThreadLocation;
        JVM INSTR monitorexit ;
        return threadlocation1;
_L2:
        ThreadLocation threadlocation;
        threadlocation = new ThreadLocation(symbol, obj, null);
        indirectablelocation.base = threadlocation;
        threadlocation1 = threadlocation;
        if (true) goto _L4; else goto _L3
_L3:
        Exception exception;
        exception;
        throw exception;
    }

    public static ThreadLocation makeAnonymous(Symbol symbol)
    {
        return new ThreadLocation(symbol);
    }

    public static ThreadLocation makeAnonymous(String s)
    {
        return new ThreadLocation(s);
    }

    private static int nextCounter()
    {
        gnu/mapping/ThreadLocation;
        JVM INSTR monitorenter ;
        int i;
        i = 1 + counter;
        counter = i;
        gnu/mapping/ThreadLocation;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public Object get(Object obj)
    {
        return getLocation().get(obj);
    }

    public NamedLocation getLocation()
    {
        NamedLocation namedlocation;
        if (property != ANONYMOUS)
        {
            namedlocation = Environment.getCurrent().getLocation(name, property, hash, true);
        } else
        {
            namedlocation = (NamedLocation)thLocal.get();
            if (namedlocation == null)
            {
                SharedLocation sharedlocation = new SharedLocation(name, property, 0);
                if (global != null)
                {
                    sharedlocation.setBase(global);
                }
                thLocal.set(sharedlocation);
                return sharedlocation;
            }
        }
        return namedlocation;
    }

    public String getName()
    {
        if (name == null)
        {
            return null;
        } else
        {
            return name.toString();
        }
    }

    public Object getSymbol()
    {
        if (name != null && property == ANONYMOUS && global.getKeySymbol() == name)
        {
            return name.toString();
        } else
        {
            return name;
        }
    }

    public void set(Object obj)
    {
        getLocation().set(obj);
    }

    public void setGlobal(Object obj)
    {
        this;
        JVM INSTR monitorenter ;
        if (global == null)
        {
            global = new SharedLocation(name, null, 0);
        }
        global.set(obj);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void setName(String s)
    {
        throw new RuntimeException("setName not allowed");
    }

    public void setRestore(Object obj)
    {
        getLocation().setRestore(obj);
    }

    public Object setWithSave(Object obj)
    {
        return getLocation().setWithSave(obj);
    }

}
