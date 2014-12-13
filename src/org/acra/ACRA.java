// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.acra;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import org.acra.annotation.ReportsCrashes;
import org.acra.log.ACRALog;
import org.acra.log.AndroidLogDelegate;

// Referenced classes of package org.acra:
//            ReportField, ACRAConfigurationException, ReportingInteractionMode, ACRAConfiguration, 
//            ErrorReporter

public class ACRA
{

    public static final ReportField DEFAULT_MAIL_REPORT_FIELDS[];
    public static final ReportField DEFAULT_REPORT_FIELDS[];
    public static final boolean DEV_LOGGING = false;
    public static final String LOG_TAG = org/acra/ACRA.getSimpleName();
    public static final String PREF_ALWAYS_ACCEPT = "acra.alwaysaccept";
    public static final String PREF_DISABLE_ACRA = "acra.disable";
    public static final String PREF_ENABLE_ACRA = "acra.enable";
    public static final String PREF_ENABLE_DEVICE_ID = "acra.deviceid.enable";
    public static final String PREF_ENABLE_SYSTEM_LOGS = "acra.syslog.enable";
    public static final String PREF_LAST_VERSION_NR = "acra.lastVersionNr";
    public static final String PREF_USER_EMAIL_ADDRESS = "acra.user.email";
    private static ACRAConfiguration configProxy;
    private static ErrorReporter errorReporterSingleton;
    public static ACRALog log = new AndroidLogDelegate();
    private static Application mApplication;
    private static android.content.SharedPreferences.OnSharedPreferenceChangeListener mPrefListener;
    private static ReportsCrashes mReportsCrashes;

    public ACRA()
    {
    }

    static void checkCrashResources()
        throws ACRAConfigurationException
    {
        ACRAConfiguration acraconfiguration = getConfig();
        static class _cls2
        {

            static final int $SwitchMap$org$acra$ReportingInteractionMode[];

            static 
            {
                $SwitchMap$org$acra$ReportingInteractionMode = new int[ReportingInteractionMode.values().length];
                try
                {
                    $SwitchMap$org$acra$ReportingInteractionMode[ReportingInteractionMode.TOAST.ordinal()] = 1;
                }
                catch (NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$org$acra$ReportingInteractionMode[ReportingInteractionMode.NOTIFICATION.ordinal()] = 2;
                }
                catch (NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$org$acra$ReportingInteractionMode[ReportingInteractionMode.DIALOG.ordinal()] = 3;
                }
                catch (NoSuchFieldError nosuchfielderror2)
                {
                    return;
                }
            }
        }

        _cls2..SwitchMap.org.acra.ReportingInteractionMode[acraconfiguration.mode().ordinal()];
        JVM INSTR tableswitch 1 3: default 44
    //                   1 45
    //                   2 64
    //                   3 110;
           goto _L1 _L2 _L3 _L4
_L1:
        return;
_L2:
        if (acraconfiguration.resToastText() == 0)
        {
            throw new ACRAConfigurationException("TOAST mode: you have to define the resToastText parameter in your application @ReportsCrashes() annotation.");
        }
        continue; /* Loop/switch isn't completed */
_L3:
        if (acraconfiguration.resNotifTickerText() == 0 || acraconfiguration.resNotifTitle() == 0 || acraconfiguration.resNotifText() == 0 || acraconfiguration.resDialogText() == 0)
        {
            throw new ACRAConfigurationException("NOTIFICATION mode: you have to define at least the resNotifTickerText, resNotifTitle, resNotifText, resDialogText parameters in your application @ReportsCrashes() annotation.");
        }
        continue; /* Loop/switch isn't completed */
_L4:
        if (acraconfiguration.resDialogText() == 0)
        {
            throw new ACRAConfigurationException("DIALOG mode: you have to define at least the resDialogText parameters in your application @ReportsCrashes() annotation.");
        }
        if (true) goto _L1; else goto _L5
_L5:
    }

    public static SharedPreferences getACRASharedPreferences()
    {
        ACRAConfiguration acraconfiguration = getConfig();
        if (!"".equals(acraconfiguration.sharedPreferencesName()))
        {
            return mApplication.getSharedPreferences(acraconfiguration.sharedPreferencesName(), acraconfiguration.sharedPreferencesMode());
        } else
        {
            return PreferenceManager.getDefaultSharedPreferences(mApplication);
        }
    }

    static Application getApplication()
    {
        return mApplication;
    }

    public static ACRAConfiguration getConfig()
    {
        if (configProxy == null)
        {
            if (mApplication == null)
            {
                log.w(LOG_TAG, "Calling ACRA.getConfig() before ACRA.init() gives you an empty configuration instance. You might prefer calling ACRA.getNewDefaultConfig(Application) to get an instance with default values taken from a @ReportsCrashes annotation.");
            }
            configProxy = getNewDefaultConfig(mApplication);
        }
        return configProxy;
    }

    public static ErrorReporter getErrorReporter()
    {
        if (errorReporterSingleton == null)
        {
            throw new IllegalStateException("Cannot access ErrorReporter before ACRA#init");
        } else
        {
            return errorReporterSingleton;
        }
    }

    public static ACRAConfiguration getNewDefaultConfig(Application application)
    {
        if (application != null)
        {
            return new ACRAConfiguration((ReportsCrashes)application.getClass().getAnnotation(org/acra/annotation/ReportsCrashes));
        } else
        {
            return new ACRAConfiguration(null);
        }
    }

    public static void init(Application application)
    {
        SharedPreferences sharedpreferences;
        if (mApplication != null)
        {
            throw new IllegalStateException("ACRA#init called more than once");
        }
        mApplication = application;
        mReportsCrashes = (ReportsCrashes)mApplication.getClass().getAnnotation(org/acra/annotation/ReportsCrashes);
        if (mReportsCrashes == null)
        {
            log.e(LOG_TAG, (new StringBuilder()).append("ACRA#init called but no ReportsCrashes annotation on Application ").append(mApplication.getPackageName()).toString());
            return;
        }
        sharedpreferences = getACRASharedPreferences();
        checkCrashResources();
        log.d(LOG_TAG, (new StringBuilder()).append("ACRA is enabled for ").append(mApplication.getPackageName()).append(", intializing...").toString());
        boolean flag;
        if (!shouldDisableACRA(sharedpreferences))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        try
        {
            ErrorReporter errorreporter = new ErrorReporter(mApplication.getApplicationContext(), sharedpreferences, flag);
            errorreporter.setDefaultReportSenders();
            errorReporterSingleton = errorreporter;
        }
        catch (ACRAConfigurationException acraconfigurationexception)
        {
            log.w(LOG_TAG, "Error : ", acraconfigurationexception);
        }
        mPrefListener = new android.content.SharedPreferences.OnSharedPreferenceChangeListener() {

            public void onSharedPreferenceChanged(SharedPreferences sharedpreferences1, String s)
            {
                if ("acra.disable".equals(s) || "acra.enable".equals(s))
                {
                    boolean flag1;
                    if (!ACRA.shouldDisableACRA(sharedpreferences1))
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    ACRA.getErrorReporter().setEnabled(flag1);
                }
            }

        };
        sharedpreferences.registerOnSharedPreferenceChangeListener(mPrefListener);
        return;
    }

    static boolean isDebuggable()
    {
        PackageManager packagemanager = mApplication.getPackageManager();
        int i;
        int j;
        boolean flag;
        try
        {
            i = packagemanager.getApplicationInfo(mApplication.getPackageName(), 0).flags;
        }
        catch (android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
        {
            return false;
        }
        j = i & 2;
        flag = false;
        if (j > 0)
        {
            flag = true;
        }
        return flag;
    }

    public static void setConfig(ACRAConfiguration acraconfiguration)
    {
        configProxy = acraconfiguration;
    }

    public static void setLog(ACRALog acralog)
    {
        log = acralog;
    }

    private static boolean shouldDisableACRA(SharedPreferences sharedpreferences)
    {
        boolean flag = true;
        boolean flag1;
        if (sharedpreferences.getBoolean("acra.enable", true))
        {
            flag = false;
        }
        flag1 = sharedpreferences.getBoolean("acra.disable", flag);
        return flag1;
        Exception exception;
        exception;
        return false;
    }

    static 
    {
        ReportField areportfield[] = new ReportField[7];
        areportfield[0] = ReportField.USER_COMMENT;
        areportfield[1] = ReportField.ANDROID_VERSION;
        areportfield[2] = ReportField.APP_VERSION_NAME;
        areportfield[3] = ReportField.BRAND;
        areportfield[4] = ReportField.PHONE_MODEL;
        areportfield[5] = ReportField.CUSTOM_DATA;
        areportfield[6] = ReportField.STACK_TRACE;
        DEFAULT_MAIL_REPORT_FIELDS = areportfield;
        ReportField areportfield1[] = new ReportField[30];
        areportfield1[0] = ReportField.REPORT_ID;
        areportfield1[1] = ReportField.APP_VERSION_CODE;
        areportfield1[2] = ReportField.APP_VERSION_NAME;
        areportfield1[3] = ReportField.PACKAGE_NAME;
        areportfield1[4] = ReportField.FILE_PATH;
        areportfield1[5] = ReportField.PHONE_MODEL;
        areportfield1[6] = ReportField.BRAND;
        areportfield1[7] = ReportField.PRODUCT;
        areportfield1[8] = ReportField.ANDROID_VERSION;
        areportfield1[9] = ReportField.BUILD;
        areportfield1[10] = ReportField.TOTAL_MEM_SIZE;
        areportfield1[11] = ReportField.AVAILABLE_MEM_SIZE;
        areportfield1[12] = ReportField.CUSTOM_DATA;
        areportfield1[13] = ReportField.IS_SILENT;
        areportfield1[14] = ReportField.STACK_TRACE;
        areportfield1[15] = ReportField.INITIAL_CONFIGURATION;
        areportfield1[16] = ReportField.CRASH_CONFIGURATION;
        areportfield1[17] = ReportField.DISPLAY;
        areportfield1[18] = ReportField.USER_COMMENT;
        areportfield1[19] = ReportField.USER_EMAIL;
        areportfield1[20] = ReportField.USER_APP_START_DATE;
        areportfield1[21] = ReportField.USER_CRASH_DATE;
        areportfield1[22] = ReportField.DUMPSYS_MEMINFO;
        areportfield1[23] = ReportField.LOGCAT;
        areportfield1[24] = ReportField.INSTALLATION_ID;
        areportfield1[25] = ReportField.DEVICE_FEATURES;
        areportfield1[26] = ReportField.ENVIRONMENT;
        areportfield1[27] = ReportField.SHARED_PREFERENCES;
        areportfield1[28] = ReportField.SETTINGS_SYSTEM;
        areportfield1[29] = ReportField.SETTINGS_SECURE;
        DEFAULT_REPORT_FIELDS = areportfield1;
    }

}
