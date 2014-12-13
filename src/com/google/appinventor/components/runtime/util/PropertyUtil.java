// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.SimplePropertyCopier;
import com.google.appinventor.components.runtime.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PropertyUtil
{

    public PropertyUtil()
    {
    }

    public static Component copyComponentProperties(Component component, Component component1)
        throws Throwable
    {
        Class class1;
        Method amethod[];
        int i;
        int j;
        if (!component.getClass().equals(component1.getClass()))
        {
            throw new IllegalArgumentException("Source and target classes must be identical");
        }
        class1 = component.getClass();
        amethod = class1.getMethods();
        i = amethod.length;
        j = 0;
_L2:
        Method method;
        if (j >= i)
        {
            break MISSING_BLOCK_LABEL_214;
        }
        method = amethod[j];
        if (!method.isAnnotationPresent(com/google/appinventor/components/annotations/SimpleProperty) || method.getParameterTypes().length != 1)
        {
            break MISSING_BLOCK_LABEL_216;
        }
        String s;
        Method method1;
        s = method.getName();
        method1 = getPropertyCopierMethod((new StringBuilder()).append("Copy").append(s).toString(), class1);
        if (method1 != null)
        {
            Method method2;
            Class class2;
            try
            {
                method1.invoke(component1, new Object[] {
                    component
                });
            }
            catch (NoSuchMethodException nosuchmethodexception) { }
            catch (InvocationTargetException invocationtargetexception)
            {
                throw invocationtargetexception.getCause();
            }
            break MISSING_BLOCK_LABEL_216;
        }
        method2 = class1.getMethod(s, new Class[0]);
        class2 = method.getParameterTypes()[0];
        if (method2.isAnnotationPresent(com/google/appinventor/components/annotations/SimpleProperty) && class2.isAssignableFrom(method2.getReturnType()))
        {
            method.invoke(component1, new Object[] {
                method2.invoke(component, new Object[0])
            });
        }
        break MISSING_BLOCK_LABEL_216;
        return component1;
        j++;
        if (true) goto _L2; else goto _L1
_L1:
    }

    private static Method getPropertyCopierMethod(String s, Class class1)
    {
_L2:
        Method method;
        boolean flag;
        method = class1.getMethod(s, new Class[] {
            class1
        });
        flag = method.isAnnotationPresent(com/google/appinventor/components/annotations/SimplePropertyCopier);
label0:
        {
            if (flag)
            {
                return method;
            }
            break label0;
        }
        NoSuchMethodException nosuchmethodexception;
        nosuchmethodexception;
        class1 = class1.getSuperclass();
        if (class1 == null)
        {
            return null;
        }
        if (true) goto _L2; else goto _L1
_L1:
    }
}
