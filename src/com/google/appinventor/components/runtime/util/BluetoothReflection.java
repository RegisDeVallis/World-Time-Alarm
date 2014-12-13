// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;

public class BluetoothReflection
{

    private static final int BOND_BONDED = 12;

    private BluetoothReflection()
    {
    }

    public static Object accept(Object obj)
        throws IOException
    {
        return invokeMethodThrowsIOException(getMethod(obj.getClass(), "accept"), obj, new Object[0]);
    }

    public static boolean checkBluetoothAddress(Object obj, String s)
    {
        return ((Boolean)invokeMethod(getMethod(obj.getClass(), "checkBluetoothAddress", new Class[] {
            java/lang/String
        }), obj, new Object[] {
            s
        })).booleanValue();
    }

    public static void closeBluetoothServerSocket(Object obj)
        throws IOException
    {
        invokeMethodThrowsIOException(getMethod(obj.getClass(), "close"), obj, new Object[0]);
    }

    public static void closeBluetoothSocket(Object obj)
        throws IOException
    {
        invokeMethodThrowsIOException(getMethod(obj.getClass(), "close"), obj, new Object[0]);
    }

    public static void connectToBluetoothSocket(Object obj)
        throws IOException
    {
        invokeMethodThrowsIOException(getMethod(obj.getClass(), "connect"), obj, new Object[0]);
    }

    public static Object createInsecureRfcommSocketToServiceRecord(Object obj, UUID uuid)
        throws IOException
    {
        return invokeMethodThrowsIOException(getMethod(obj.getClass(), "createInsecureRfcommSocketToServiceRecord", new Class[] {
            java/util/UUID
        }), obj, new Object[] {
            uuid
        });
    }

    public static Object createRfcommSocketToServiceRecord(Object obj, UUID uuid)
        throws IOException
    {
        return invokeMethodThrowsIOException(getMethod(obj.getClass(), "createRfcommSocketToServiceRecord", new Class[] {
            java/util/UUID
        }), obj, new Object[] {
            uuid
        });
    }

    public static Object getBluetoothAdapter()
    {
        Class class1;
        try
        {
            class1 = Class.forName("android.bluetooth.BluetoothAdapter");
        }
        catch (ClassNotFoundException classnotfoundexception)
        {
            return null;
        }
        return invokeStaticMethod(getMethod(class1, "getDefaultAdapter"));
    }

    public static Object getBluetoothClass(Object obj)
    {
        return invokeMethod(getMethod(obj.getClass(), "getBluetoothClass"), obj, new Object[0]);
    }

    public static String getBluetoothDeviceAddress(Object obj)
    {
        return (String)invokeMethod(getMethod(obj.getClass(), "getAddress"), obj, new Object[0]);
    }

    public static String getBluetoothDeviceName(Object obj)
    {
        return (String)invokeMethod(getMethod(obj.getClass(), "getName"), obj, new Object[0]);
    }

    public static Set getBondedDevices(Object obj)
    {
        return (Set)invokeMethod(getMethod(obj.getClass(), "getBondedDevices"), obj, new Object[0]);
    }

    public static int getDeviceClass(Object obj)
    {
        return ((Integer)invokeMethod(getMethod(obj.getClass(), "getDeviceClass"), obj, new Object[0])).intValue();
    }

    public static InputStream getInputStream(Object obj)
        throws IOException
    {
        return (InputStream)invokeMethodThrowsIOException(getMethod(obj.getClass(), "getInputStream"), obj, new Object[0]);
    }

    private static Method getMethod(Class class1, String s)
    {
        Method method;
        try
        {
            method = class1.getMethod(s, new Class[0]);
        }
        catch (NoSuchMethodException nosuchmethodexception)
        {
            throw new RuntimeException(nosuchmethodexception);
        }
        return method;
    }

    private static transient Method getMethod(Class class1, String s, Class aclass[])
    {
        Method method;
        try
        {
            method = class1.getMethod(s, aclass);
        }
        catch (NoSuchMethodException nosuchmethodexception)
        {
            throw new RuntimeException(nosuchmethodexception);
        }
        return method;
    }

    public static OutputStream getOutputStream(Object obj)
        throws IOException
    {
        return (OutputStream)invokeMethodThrowsIOException(getMethod(obj.getClass(), "getOutputStream"), obj, new Object[0]);
    }

    public static Object getRemoteDevice(Object obj, String s)
        throws IllegalArgumentException
    {
        return invokeMethodThrowsIllegalArgumentException(getMethod(obj.getClass(), "getRemoteDevice", new Class[] {
            java/lang/String
        }), obj, new Object[] {
            s
        });
    }

    private static transient Object invokeMethod(Method method, Object obj, Object aobj[])
    {
        Object obj1;
        try
        {
            obj1 = method.invoke(obj, aobj);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new RuntimeException(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            Throwable throwable = invocationtargetexception.getCause();
            throwable.printStackTrace();
            if (throwable instanceof RuntimeException)
            {
                throw (RuntimeException)throwable;
            } else
            {
                throw new RuntimeException(throwable);
            }
        }
        return obj1;
    }

    private static transient Object invokeMethodThrowsIOException(Method method, Object obj, Object aobj[])
        throws IOException
    {
        Object obj1;
        try
        {
            obj1 = method.invoke(obj, aobj);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new RuntimeException(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            Throwable throwable = invocationtargetexception.getCause();
            throwable.printStackTrace();
            if (throwable instanceof IOException)
            {
                throw (IOException)throwable;
            }
            if (throwable instanceof RuntimeException)
            {
                throw (RuntimeException)throwable;
            } else
            {
                throw new RuntimeException(invocationtargetexception);
            }
        }
        return obj1;
    }

    private static transient Object invokeMethodThrowsIllegalArgumentException(Method method, Object obj, Object aobj[])
        throws IllegalArgumentException
    {
        Object obj1;
        try
        {
            obj1 = method.invoke(obj, aobj);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new RuntimeException(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            Throwable throwable = invocationtargetexception.getCause();
            throwable.printStackTrace();
            if (throwable instanceof IllegalArgumentException)
            {
                throw (IllegalArgumentException)throwable;
            }
            if (throwable instanceof RuntimeException)
            {
                throw (RuntimeException)throwable;
            } else
            {
                throw new RuntimeException(invocationtargetexception);
            }
        }
        return obj1;
    }

    private static Object invokeStaticMethod(Method method)
    {
        Object obj;
        try
        {
            obj = method.invoke(null, new Object[0]);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
            throw new RuntimeException(illegalaccessexception);
        }
        catch (InvocationTargetException invocationtargetexception)
        {
            Throwable throwable = invocationtargetexception.getCause();
            throwable.printStackTrace();
            if (throwable instanceof RuntimeException)
            {
                throw (RuntimeException)throwable;
            } else
            {
                throw new RuntimeException(throwable);
            }
        }
        return obj;
    }

    public static boolean isBluetoothEnabled(Object obj)
    {
        return ((Boolean)invokeMethod(getMethod(obj.getClass(), "isEnabled"), obj, new Object[0])).booleanValue();
    }

    public static boolean isBonded(Object obj)
    {
        return ((Integer)invokeMethod(getMethod(obj.getClass(), "getBondState"), obj, new Object[0])).intValue() == 12;
    }

    public static Object listenUsingInsecureRfcommWithServiceRecord(Object obj, String s, UUID uuid)
        throws IOException
    {
        return invokeMethodThrowsIOException(getMethod(obj.getClass(), "listenUsingInsecureRfcommWithServiceRecord", new Class[] {
            java/lang/String, java/util/UUID
        }), obj, new Object[] {
            s, uuid
        });
    }

    public static Object listenUsingRfcommWithServiceRecord(Object obj, String s, UUID uuid)
        throws IOException
    {
        return invokeMethodThrowsIOException(getMethod(obj.getClass(), "listenUsingRfcommWithServiceRecord", new Class[] {
            java/lang/String, java/util/UUID
        }), obj, new Object[] {
            s, uuid
        });
    }
}
