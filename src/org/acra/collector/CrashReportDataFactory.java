// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.acra.collector;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Environment;
import android.text.format.Time;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.acra.ACRA;
import org.acra.ACRAConfiguration;
import org.acra.ReportField;
import org.acra.annotation.ReportsCrashes;
import org.acra.util.Installation;
import org.acra.util.PackageManagerWrapper;
import org.acra.util.ReportUtils;

// Referenced classes of package org.acra.collector:
//            CrashReportData, ConfigurationCollector, DumpSysCollector, ReflectionCollector, 
//            DeviceFeaturesCollector, SettingsCollector, SharedPreferencesCollector, Compatibility, 
//            LogCatCollector, DropBoxCollector, LogFileCollector, MediaCodecListCollector, 
//            ThreadCollector

public final class CrashReportDataFactory
{

    private final Time appStartDate;
    private final Context context;
    private final List crashReportFields;
    private final Map customParameters = new HashMap();
    private final String initialConfiguration;
    private final SharedPreferences prefs;

    public CrashReportDataFactory(Context context1, SharedPreferences sharedpreferences, Time time, String s)
    {
        context = context1;
        prefs = sharedpreferences;
        appStartDate = time;
        initialConfiguration = s;
        ACRAConfiguration acraconfiguration = ACRA.getConfig();
        ReportField areportfield[] = acraconfiguration.customReportContent();
        ReportField areportfield1[];
        if (areportfield.length != 0)
        {
            Log.d(ACRA.LOG_TAG, "Using custom Report Fields");
            areportfield1 = areportfield;
        } else
        if (acraconfiguration.mailTo() == null || "".equals(acraconfiguration.mailTo()))
        {
            Log.d(ACRA.LOG_TAG, "Using default Report Fields");
            areportfield1 = ACRA.DEFAULT_REPORT_FIELDS;
        } else
        {
            Log.d(ACRA.LOG_TAG, "Using default Mail Report Fields");
            areportfield1 = ACRA.DEFAULT_MAIL_REPORT_FIELDS;
        }
        crashReportFields = Arrays.asList(areportfield1);
    }

    private String createCustomInfoString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        for (Iterator iterator = customParameters.keySet().iterator(); iterator.hasNext(); stringbuilder.append("\n"))
        {
            String s = (String)iterator.next();
            String s1 = (String)customParameters.get(s);
            stringbuilder.append(s);
            stringbuilder.append(" = ");
            stringbuilder.append(s1);
        }

        return stringbuilder.toString();
    }

    private String getStackTrace(Throwable throwable)
    {
        StringWriter stringwriter = new StringWriter();
        PrintWriter printwriter = new PrintWriter(stringwriter);
        for (Throwable throwable1 = throwable; throwable1 != null; throwable1 = throwable1.getCause())
        {
            throwable1.printStackTrace(printwriter);
        }

        String s = stringwriter.toString();
        printwriter.close();
        return s;
    }

    public CrashReportData createCrashData(Throwable throwable, boolean flag, Thread thread)
    {
        CrashReportData crashreportdata = new CrashReportData();
        crashreportdata.put(ReportField.STACK_TRACE, getStackTrace(throwable));
        crashreportdata.put(ReportField.USER_APP_START_DATE, appStartDate.format3339(false));
        if (!flag)
        {
            break MISSING_BLOCK_LABEL_55;
        }
        crashreportdata.put(ReportField.IS_SILENT, "true");
        PackageManagerWrapper packagemanagerwrapper;
        PackageInfo packageinfo;
        if (crashReportFields.contains(ReportField.REPORT_ID))
        {
            crashreportdata.put(ReportField.REPORT_ID, UUID.randomUUID().toString());
        }
        if (crashReportFields.contains(ReportField.INSTALLATION_ID))
        {
            crashreportdata.put(ReportField.INSTALLATION_ID, Installation.id(context));
        }
        if (crashReportFields.contains(ReportField.INITIAL_CONFIGURATION))
        {
            crashreportdata.put(ReportField.INITIAL_CONFIGURATION, initialConfiguration);
        }
        if (crashReportFields.contains(ReportField.CRASH_CONFIGURATION))
        {
            crashreportdata.put(ReportField.CRASH_CONFIGURATION, ConfigurationCollector.collectConfiguration(context));
        }
        if (!(throwable instanceof OutOfMemoryError) && crashReportFields.contains(ReportField.DUMPSYS_MEMINFO))
        {
            crashreportdata.put(ReportField.DUMPSYS_MEMINFO, DumpSysCollector.collectMemInfo());
        }
        if (crashReportFields.contains(ReportField.PACKAGE_NAME))
        {
            crashreportdata.put(ReportField.PACKAGE_NAME, context.getPackageName());
        }
        if (crashReportFields.contains(ReportField.BUILD))
        {
            crashreportdata.put(ReportField.BUILD, ReflectionCollector.collectConstants(android/os/Build));
        }
        if (crashReportFields.contains(ReportField.PHONE_MODEL))
        {
            crashreportdata.put(ReportField.PHONE_MODEL, Build.MODEL);
        }
        if (crashReportFields.contains(ReportField.ANDROID_VERSION))
        {
            crashreportdata.put(ReportField.ANDROID_VERSION, android.os.Build.VERSION.RELEASE);
        }
        if (crashReportFields.contains(ReportField.BRAND))
        {
            crashreportdata.put(ReportField.BRAND, Build.BRAND);
        }
        if (crashReportFields.contains(ReportField.PRODUCT))
        {
            crashreportdata.put(ReportField.PRODUCT, Build.PRODUCT);
        }
        if (crashReportFields.contains(ReportField.TOTAL_MEM_SIZE))
        {
            crashreportdata.put(ReportField.TOTAL_MEM_SIZE, Long.toString(ReportUtils.getTotalInternalMemorySize()));
        }
        if (crashReportFields.contains(ReportField.AVAILABLE_MEM_SIZE))
        {
            crashreportdata.put(ReportField.AVAILABLE_MEM_SIZE, Long.toString(ReportUtils.getAvailableInternalMemorySize()));
        }
        if (crashReportFields.contains(ReportField.FILE_PATH))
        {
            crashreportdata.put(ReportField.FILE_PATH, ReportUtils.getApplicationFilePath(context));
        }
        if (crashReportFields.contains(ReportField.DISPLAY))
        {
            crashreportdata.put(ReportField.DISPLAY, ReportUtils.getDisplayDetails(context));
        }
        if (crashReportFields.contains(ReportField.USER_CRASH_DATE))
        {
            Time time = new Time();
            time.setToNow();
            crashreportdata.put(ReportField.USER_CRASH_DATE, time.format3339(false));
        }
        if (crashReportFields.contains(ReportField.CUSTOM_DATA))
        {
            crashreportdata.put(ReportField.CUSTOM_DATA, createCustomInfoString());
        }
        if (crashReportFields.contains(ReportField.USER_EMAIL))
        {
            crashreportdata.put(ReportField.USER_EMAIL, prefs.getString("acra.user.email", "N/A"));
        }
        if (crashReportFields.contains(ReportField.DEVICE_FEATURES))
        {
            crashreportdata.put(ReportField.DEVICE_FEATURES, DeviceFeaturesCollector.getFeatures(context));
        }
        if (crashReportFields.contains(ReportField.ENVIRONMENT))
        {
            crashreportdata.put(ReportField.ENVIRONMENT, ReflectionCollector.collectStaticGettersResults(android/os/Environment));
        }
        if (crashReportFields.contains(ReportField.SETTINGS_SYSTEM))
        {
            crashreportdata.put(ReportField.SETTINGS_SYSTEM, SettingsCollector.collectSystemSettings(context));
        }
        if (crashReportFields.contains(ReportField.SETTINGS_SECURE))
        {
            crashreportdata.put(ReportField.SETTINGS_SECURE, SettingsCollector.collectSecureSettings(context));
        }
        if (crashReportFields.contains(ReportField.SHARED_PREFERENCES))
        {
            crashreportdata.put(ReportField.SHARED_PREFERENCES, SharedPreferencesCollector.collect(context));
        }
        packagemanagerwrapper = new PackageManagerWrapper(context);
        packageinfo = packagemanagerwrapper.getPackageInfo();
        if (packageinfo == null) goto _L2; else goto _L1
_L1:
        if (crashReportFields.contains(ReportField.APP_VERSION_CODE))
        {
            crashreportdata.put(ReportField.APP_VERSION_CODE, Integer.toString(packageinfo.versionCode));
        }
        if (!crashReportFields.contains(ReportField.APP_VERSION_NAME)) goto _L4; else goto _L3
_L3:
        ReportField reportfield;
        String s1;
        reportfield = ReportField.APP_VERSION_NAME;
        if (packageinfo.versionName == null)
        {
            break MISSING_BLOCK_LABEL_1339;
        }
        s1 = packageinfo.versionName;
_L13:
        crashreportdata.put(reportfield, s1);
_L4:
        if (!crashReportFields.contains(ReportField.DEVICE_ID) || !prefs.getBoolean("acra.deviceid.enable", true) || !packagemanagerwrapper.hasPermission("android.permission.READ_PHONE_STATE")) goto _L6; else goto _L5
_L5:
        String s = ReportUtils.getDeviceId(context);
        if (s == null) goto _L6; else goto _L7
_L7:
        crashreportdata.put(ReportField.DEVICE_ID, s);
_L6:
        if ((!prefs.getBoolean("acra.syslog.enable", true) || !packagemanagerwrapper.hasPermission("android.permission.READ_LOGS")) && Compatibility.getAPILevel() < 16) goto _L9; else goto _L8
_L8:
        Log.i(ACRA.LOG_TAG, "READ_LOGS granted! ACRA can include LogCat and DropBox data.");
        if (crashReportFields.contains(ReportField.LOGCAT))
        {
            crashreportdata.put(ReportField.LOGCAT, LogCatCollector.collectLogCat(null));
        }
        if (crashReportFields.contains(ReportField.EVENTSLOG))
        {
            crashreportdata.put(ReportField.EVENTSLOG, LogCatCollector.collectLogCat("events"));
        }
        if (crashReportFields.contains(ReportField.RADIOLOG))
        {
            crashreportdata.put(ReportField.RADIOLOG, LogCatCollector.collectLogCat("radio"));
        }
        if (crashReportFields.contains(ReportField.DROPBOX))
        {
            crashreportdata.put(ReportField.DROPBOX, DropBoxCollector.read(context, ACRA.getConfig().additionalDropBoxTags()));
        }
_L12:
        if (crashReportFields.contains(ReportField.APPLICATION_LOG))
        {
            crashreportdata.put(ReportField.APPLICATION_LOG, LogFileCollector.collectLogFile(context, ACRA.getConfig().applicationLogFile(), ACRA.getConfig().applicationLogFileLines()));
        }
        if (crashReportFields.contains(ReportField.MEDIA_CODEC_LIST))
        {
            crashreportdata.put(ReportField.MEDIA_CODEC_LIST, MediaCodecListCollector.collecMediaCodecList());
        }
        if (!crashReportFields.contains(ReportField.THREAD_DETAILS)) goto _L11; else goto _L10
_L10:
        crashreportdata.put(ReportField.THREAD_DETAILS, ThreadCollector.collect(thread));
        return crashreportdata;
_L2:
        crashreportdata.put(ReportField.APP_VERSION_NAME, "Package info unavailable");
          goto _L4
        RuntimeException runtimeexception;
        runtimeexception;
        Log.e(ACRA.LOG_TAG, "Error while retrieving crash data", runtimeexception);
        return crashreportdata;
_L9:
        Log.i(ACRA.LOG_TAG, "READ_LOGS not allowed. ACRA will not include LogCat and DropBox data.");
          goto _L12
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        Log.e(ACRA.LOG_TAG, (new StringBuilder()).append("Error : application log file ").append(ACRA.getConfig().applicationLogFile()).append(" not found.").toString(), filenotfoundexception);
        return crashreportdata;
        IOException ioexception;
        ioexception;
        Log.e(ACRA.LOG_TAG, (new StringBuilder()).append("Error while reading application log file ").append(ACRA.getConfig().applicationLogFile()).append(".").toString(), ioexception);
_L11:
        return crashreportdata;
        s1 = "not set";
          goto _L13
    }

    public String getCustomData(String s)
    {
        return (String)customParameters.get(s);
    }

    public String putCustomData(String s, String s1)
    {
        return (String)customParameters.put(s, s1);
    }

    public String removeCustomData(String s)
    {
        return (String)customParameters.remove(s);
    }
}
