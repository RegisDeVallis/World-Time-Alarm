// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

// Referenced classes of package gnu.text:
//            SourceMessages

public class Options
{
    public static final class OptionInfo
    {

        Object defaultValue;
        String documentation;
        String key;
        int kind;
        OptionInfo next;

        public OptionInfo()
        {
        }
    }


    public static final int BOOLEAN_OPTION = 1;
    public static final int STRING_OPTION = 2;
    public static final String UNKNOWN = "unknown option name";
    OptionInfo first;
    HashMap infoTable;
    OptionInfo last;
    Options previous;
    HashMap valueTable;

    public Options()
    {
    }

    public Options(Options options)
    {
        previous = options;
    }

    private void error(String s, SourceMessages sourcemessages)
    {
        if (sourcemessages == null)
        {
            throw new RuntimeException(s);
        } else
        {
            sourcemessages.error('e', s);
            return;
        }
    }

    static Object valueOf(OptionInfo optioninfo, String s)
    {
label0:
        {
            if ((1 & optioninfo.kind) != 0)
            {
                if (s != null && !s.equals("1") && !s.equals("on") && !s.equals("yes") && !s.equals("true"))
                {
                    break label0;
                }
                s = Boolean.TRUE;
            }
            return s;
        }
        if (s.equals("0") || s.equals("off") || s.equals("no") || s.equals("false"))
        {
            return Boolean.FALSE;
        } else
        {
            return null;
        }
    }

    public OptionInfo add(String s, int i, Object obj, String s1)
    {
        OptionInfo optioninfo;
        if (infoTable == null)
        {
            infoTable = new HashMap();
        } else
        if (infoTable.get(s) != null)
        {
            throw new RuntimeException((new StringBuilder()).append("duplicate option key: ").append(s).toString());
        }
        optioninfo = new OptionInfo();
        optioninfo.key = s;
        optioninfo.kind = i;
        optioninfo.defaultValue = obj;
        optioninfo.documentation = s1;
        if (first == null)
        {
            first = optioninfo;
        } else
        {
            last.next = optioninfo;
        }
        last = optioninfo;
        infoTable.put(s, optioninfo);
        return optioninfo;
    }

    public OptionInfo add(String s, int i, String s1)
    {
        return add(s, i, null, s1);
    }

    public Object get(OptionInfo optioninfo)
    {
        return get(optioninfo, null);
    }

    public Object get(OptionInfo optioninfo, Object obj)
    {
        for (Options options = this; options != null; options = options.previous)
        {
            OptionInfo optioninfo1 = optioninfo;
            do
            {
                Object obj1;
                if (options.valueTable == null)
                {
                    obj1 = null;
                } else
                {
                    obj1 = options.valueTable.get(optioninfo1.key);
                }
                if (obj1 != null)
                {
                    return obj1;
                }
                if (!(optioninfo1.defaultValue instanceof OptionInfo))
                {
                    break;
                }
                optioninfo1 = (OptionInfo)optioninfo1.defaultValue;
            } while (true);
            if (optioninfo1.defaultValue != null)
            {
                obj = optioninfo1.defaultValue;
            }
        }

        return obj;
    }

    public Object get(String s, Object obj)
    {
        OptionInfo optioninfo = getInfo(s);
        if (optioninfo == null)
        {
            throw new RuntimeException((new StringBuilder()).append("invalid option key: ").append(s).toString());
        } else
        {
            return get(optioninfo, obj);
        }
    }

    public boolean getBoolean(OptionInfo optioninfo)
    {
        Object obj = get(optioninfo, null);
        if (obj == null)
        {
            return false;
        } else
        {
            return ((Boolean)obj).booleanValue();
        }
    }

    public boolean getBoolean(OptionInfo optioninfo, boolean flag)
    {
        Boolean boolean1;
        if (flag)
        {
            boolean1 = Boolean.TRUE;
        } else
        {
            boolean1 = Boolean.FALSE;
        }
        return ((Boolean)get(optioninfo, boolean1)).booleanValue();
    }

    public boolean getBoolean(String s)
    {
        return ((Boolean)get(s, Boolean.FALSE)).booleanValue();
    }

    public boolean getBoolean(String s, boolean flag)
    {
        Boolean boolean1;
        if (flag)
        {
            boolean1 = Boolean.TRUE;
        } else
        {
            boolean1 = Boolean.FALSE;
        }
        return ((Boolean)get(s, boolean1)).booleanValue();
    }

    public String getDoc(String s)
    {
        OptionInfo optioninfo = getInfo(s);
        if (s == null)
        {
            return null;
        } else
        {
            return optioninfo.documentation;
        }
    }

    public OptionInfo getInfo(String s)
    {
        OptionInfo optioninfo;
        if (infoTable == null)
        {
            optioninfo = null;
        } else
        {
            optioninfo = (OptionInfo)infoTable.get(s);
        }
        if (optioninfo == null && previous != null)
        {
            optioninfo = previous.getInfo(s);
        }
        return (OptionInfo)optioninfo;
    }

    public Object getLocal(String s)
    {
        if (valueTable == null)
        {
            return null;
        } else
        {
            return valueTable.get(s);
        }
    }

    public ArrayList keys()
    {
        ArrayList arraylist = new ArrayList();
label0:
        for (Options options = this; options != null; options = options.previous)
        {
            if (options.infoTable == null)
            {
                continue;
            }
            Iterator iterator = options.infoTable.keySet().iterator();
            do
            {
                String s;
                do
                {
                    if (!iterator.hasNext())
                    {
                        continue label0;
                    }
                    s = (String)iterator.next();
                } while (arraylist.contains(s));
                arraylist.add(s);
            } while (true);
        }

        return arraylist;
    }

    public void popOptionValues(Vector vector)
    {
        for (int i = vector.size(); (i -= 3) >= 0;)
        {
            String s = (String)vector.elementAt(i);
            Object obj = vector.elementAt(i + 1);
            vector.setElementAt(null, i + 1);
            reset(s, obj);
        }

    }

    public void pushOptionValues(Vector vector)
    {
        int i = vector.size();
        int i1;
        for (int j = 0; j < i; j = i1)
        {
            int k = j + 1;
            String s = (String)vector.elementAt(j);
            Object obj = vector.elementAt(k);
            int l = k + 1;
            vector.setElementAt(obj, k);
            i1 = l + 1;
            set(s, vector.elementAt(l));
        }

    }

    public void reset(String s, Object obj)
    {
        if (valueTable == null)
        {
            valueTable = new HashMap();
        }
        if (obj == null)
        {
            valueTable.remove(s);
            return;
        } else
        {
            valueTable.put(s, obj);
            return;
        }
    }

    public String set(String s, String s1)
    {
        OptionInfo optioninfo = getInfo(s);
        if (optioninfo == null)
        {
            return "unknown option name";
        }
        Object obj = valueOf(optioninfo, s1);
        if (obj == null && (1 & optioninfo.kind) != 0)
        {
            return (new StringBuilder()).append("value of option ").append(s).append(" must be yes/no/true/false/on/off/1/0").toString();
        }
        if (valueTable == null)
        {
            valueTable = new HashMap();
        }
        valueTable.put(s, obj);
        return null;
    }

    public void set(String s, Object obj)
    {
        set(s, obj, null);
    }

    public void set(String s, Object obj, SourceMessages sourcemessages)
    {
        OptionInfo optioninfo = getInfo(s);
        if (optioninfo == null)
        {
            error((new StringBuilder()).append("invalid option key: ").append(s).toString(), sourcemessages);
            return;
        }
        if ((1 & optioninfo.kind) != 0)
        {
            if (obj instanceof String)
            {
                obj = valueOf(optioninfo, (String)obj);
            }
            if (!(obj instanceof Boolean))
            {
                error((new StringBuilder()).append("value for option ").append(s).append(" must be boolean or yes/no/true/false/on/off/1/0").toString(), sourcemessages);
                return;
            }
        } else
        if (obj == null)
        {
            obj = "";
        }
        if (valueTable == null)
        {
            valueTable = new HashMap();
        }
        valueTable.put(s, obj);
    }
}
