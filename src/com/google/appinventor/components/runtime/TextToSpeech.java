// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.util.ExternalTextToSpeech;
import com.google.appinventor.components.runtime.util.ITextToSpeech;
import com.google.appinventor.components.runtime.util.InternalTextToSpeech;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

// Referenced classes of package com.google.appinventor.components.runtime:
//            AndroidNonvisibleComponent, Component, OnStopListener, OnResumeListener, 
//            OnDestroyListener, ComponentContainer, Form, EventDispatcher

public class TextToSpeech extends AndroidNonvisibleComponent
    implements Component, OnStopListener, OnResumeListener, OnDestroyListener
{

    private static final String LOG_TAG = "TextToSpeech";
    private static final Map iso3CountryToLocaleMap = Maps.newHashMap();
    private static final Map iso3LanguageToLocaleMap = Maps.newHashMap();
    private String country;
    private String iso2Country;
    private String iso2Language;
    private String language;
    private float pitch;
    private boolean result;
    private float speechRate;
    private final ITextToSpeech tts;

    public TextToSpeech(ComponentContainer componentcontainer)
    {
        super(componentcontainer.$form());
        pitch = 1.0F;
        speechRate = 1.0F;
        result = false;
        Language("");
        Country("");
        int i = SdkLevel.getLevel();
        boolean flag = false;
        if (i < 4)
        {
            flag = true;
        }
        StringBuilder stringbuilder = (new StringBuilder()).append("Using ");
        String s;
        com.google.appinventor.components.runtime.util.ITextToSpeech.TextToSpeechCallback texttospeechcallback;
        Object obj;
        if (flag)
        {
            s = "external";
        } else
        {
            s = "internal";
        }
        Log.v("TextToSpeech", stringbuilder.append(s).append(" TTS library.").toString());
        texttospeechcallback = new com.google.appinventor.components.runtime.util.ITextToSpeech.TextToSpeechCallback() {

            final TextToSpeech this$0;

            public void onFailure()
            {
                result = false;
                AfterSpeaking(false);
            }

            public void onSuccess()
            {
                result = true;
                AfterSpeaking(true);
            }

            
            {
                this$0 = TextToSpeech.this;
                super();
            }
        };
        if (flag)
        {
            obj = new ExternalTextToSpeech(componentcontainer, texttospeechcallback);
        } else
        {
            obj = new InternalTextToSpeech(componentcontainer.$context(), texttospeechcallback);
        }
        tts = ((ITextToSpeech) (obj));
        form.registerForOnStop(this);
        form.registerForOnResume(this);
        form.registerForOnDestroy(this);
        form.setVolumeControlStream(3);
    }

    private static void initLocaleMaps()
    {
        Locale alocale[] = Locale.getAvailableLocales();
        int i = alocale.length;
        int j = 0;
        while (j < i) 
        {
            Locale locale = alocale[j];
            try
            {
                String s1 = locale.getISO3Country();
                if (s1.length() > 0)
                {
                    iso3CountryToLocaleMap.put(s1, locale);
                }
            }
            catch (MissingResourceException missingresourceexception) { }
            try
            {
                String s = locale.getISO3Language();
                if (s.length() > 0)
                {
                    iso3LanguageToLocaleMap.put(s, locale);
                }
            }
            catch (MissingResourceException missingresourceexception1) { }
            j++;
        }
    }

    private static Locale iso3CountryToLocale(String s)
    {
        Locale locale = (Locale)iso3CountryToLocaleMap.get(s);
        if (locale == null)
        {
            locale = (Locale)iso3CountryToLocaleMap.get(s.toUpperCase(Locale.ENGLISH));
        }
        if (locale == null)
        {
            locale = Locale.getDefault();
        }
        return locale;
    }

    private static Locale iso3LanguageToLocale(String s)
    {
        Locale locale = (Locale)iso3LanguageToLocaleMap.get(s);
        if (locale == null)
        {
            locale = (Locale)iso3LanguageToLocaleMap.get(s.toLowerCase(Locale.ENGLISH));
        }
        if (locale == null)
        {
            locale = Locale.getDefault();
        }
        return locale;
    }

    public void AfterSpeaking(boolean flag)
    {
        Object aobj[] = new Object[1];
        aobj[0] = Boolean.valueOf(flag);
        EventDispatcher.dispatchEvent(this, "AfterSpeaking", aobj);
    }

    public void BeforeSpeaking()
    {
        EventDispatcher.dispatchEvent(this, "BeforeSpeaking", new Object[0]);
    }

    public String Country()
    {
        return country;
    }

    public void Country(String s)
    {
        s.length();
        JVM INSTR tableswitch 2 3: default 28
    //                   2 65
    //                   3 49;
           goto _L1 _L2 _L3
_L1:
        Locale locale;
        locale = Locale.getDefault();
        country = locale.getCountry();
_L5:
        iso2Country = locale.getCountry();
        return;
_L3:
        locale = iso3CountryToLocale(s);
        country = locale.getISO3Country();
        continue; /* Loop/switch isn't completed */
_L2:
        locale = new Locale(s);
        country = locale.getCountry();
        if (true) goto _L5; else goto _L4
_L4:
    }

    public String Language()
    {
        return language;
    }

    public void Language(String s)
    {
        s.length();
        JVM INSTR tableswitch 2 3: default 28
    //                   2 65
    //                   3 49;
           goto _L1 _L2 _L3
_L1:
        Locale locale;
        locale = Locale.getDefault();
        language = locale.getLanguage();
_L5:
        iso2Language = locale.getLanguage();
        return;
_L3:
        locale = iso3LanguageToLocale(s);
        language = locale.getISO3Language();
        continue; /* Loop/switch isn't completed */
_L2:
        locale = new Locale(s);
        language = locale.getLanguage();
        if (true) goto _L5; else goto _L4
_L4:
    }

    public float Pitch()
    {
        return pitch;
    }

    public void Pitch(float f)
    {
        if (f < 0.0F || f > 2.0F)
        {
            Log.i("TextToSpeech", (new StringBuilder()).append("Pitch value should be between 0 and 2, but user specified: ").append(f).toString());
            return;
        }
        pitch = f;
        ITextToSpeech itexttospeech = tts;
        if (f == 0.0F)
        {
            f = 0.1F;
        }
        itexttospeech.setPitch(f);
    }

    public boolean Result()
    {
        return result;
    }

    public void Speak(String s)
    {
        BeforeSpeaking();
        Locale locale = new Locale(iso2Language, iso2Country);
        tts.speak(s, locale);
    }

    public float SpeechRate()
    {
        return speechRate;
    }

    public void SpeechRate(float f)
    {
        if (f < 0.0F || f > 2.0F)
        {
            Log.i("TextToSpeech", (new StringBuilder()).append("speechRate value should be between 0 and 2, but user specified: ").append(f).toString());
            return;
        }
        speechRate = f;
        ITextToSpeech itexttospeech = tts;
        if (f == 0.0F)
        {
            f = 0.1F;
        }
        itexttospeech.setSpeechRate(f);
    }

    public void onDestroy()
    {
        tts.onDestroy();
    }

    public void onResume()
    {
        tts.onResume();
    }

    public void onStop()
    {
        tts.onStop();
    }

    static 
    {
        initLocaleMaps();
    }


/*
    static boolean access$002(TextToSpeech texttospeech, boolean flag)
    {
        texttospeech.result = flag;
        return flag;
    }

*/
}
