// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.common;

import java.util.HashMap;
import java.util.Map;

public final class ComponentCategory extends Enum
{

    private static final ComponentCategory $VALUES[];
    public static final ComponentCategory ANIMATION;
    public static final ComponentCategory CONNECTIVITY;
    private static final Map DOC_MAP;
    public static final ComponentCategory INTERNAL;
    public static final ComponentCategory LAYOUT;
    public static final ComponentCategory LEGOMINDSTORMS;
    public static final ComponentCategory MEDIA;
    public static final ComponentCategory SENSORS;
    public static final ComponentCategory SOCIAL;
    public static final ComponentCategory STORAGE;
    public static final ComponentCategory UNINITIALIZED;
    public static final ComponentCategory USERINTERFACE;
    private String name;

    private ComponentCategory(String s, int i, String s1)
    {
        super(s, i);
        name = s1;
    }

    public static ComponentCategory valueOf(String s)
    {
        return (ComponentCategory)Enum.valueOf(com/google/appinventor/components/common/ComponentCategory, s);
    }

    public static ComponentCategory[] values()
    {
        return (ComponentCategory[])$VALUES.clone();
    }

    public String getDocName()
    {
        return (String)DOC_MAP.get(name);
    }

    public String getName()
    {
        return name;
    }

    static 
    {
        USERINTERFACE = new ComponentCategory("USERINTERFACE", 0, "User Interface");
        LAYOUT = new ComponentCategory("LAYOUT", 1, "Layout");
        MEDIA = new ComponentCategory("MEDIA", 2, "Media");
        ANIMATION = new ComponentCategory("ANIMATION", 3, "Drawing and Animation");
        SENSORS = new ComponentCategory("SENSORS", 4, "Sensors");
        SOCIAL = new ComponentCategory("SOCIAL", 5, "Social");
        STORAGE = new ComponentCategory("STORAGE", 6, "Storage");
        CONNECTIVITY = new ComponentCategory("CONNECTIVITY", 7, "Connectivity");
        LEGOMINDSTORMS = new ComponentCategory("LEGOMINDSTORMS", 8, "LEGO\256 MINDSTORMS\256");
        INTERNAL = new ComponentCategory("INTERNAL", 9, "For internal use only");
        UNINITIALIZED = new ComponentCategory("UNINITIALIZED", 10, "Uninitialized");
        ComponentCategory acomponentcategory[] = new ComponentCategory[11];
        acomponentcategory[0] = USERINTERFACE;
        acomponentcategory[1] = LAYOUT;
        acomponentcategory[2] = MEDIA;
        acomponentcategory[3] = ANIMATION;
        acomponentcategory[4] = SENSORS;
        acomponentcategory[5] = SOCIAL;
        acomponentcategory[6] = STORAGE;
        acomponentcategory[7] = CONNECTIVITY;
        acomponentcategory[8] = LEGOMINDSTORMS;
        acomponentcategory[9] = INTERNAL;
        acomponentcategory[10] = UNINITIALIZED;
        $VALUES = acomponentcategory;
        DOC_MAP = new HashMap();
        DOC_MAP.put("User Interface", "userinterface");
        DOC_MAP.put("Layout", "layout");
        DOC_MAP.put("media", "media");
        DOC_MAP.put("Drawing and Animation", "animation");
        DOC_MAP.put("Sensors", "sensors");
        DOC_MAP.put("Social", "social");
        DOC_MAP.put("Storage", "storage");
        DOC_MAP.put("Connectivity", "connectivity");
        DOC_MAP.put("LEGO\256 MINDSTORMS\256", "legomindstorms");
    }
}
