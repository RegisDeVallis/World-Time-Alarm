// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.media.MediaRecorder;
import android.os.Environment;
import android.util.Log;
import com.google.appinventor.components.runtime.util.FileUtil;
import java.io.File;
import java.io.IOException;

// Referenced classes of package com.google.appinventor.components.runtime:
//            AndroidNonvisibleComponent, Component, ComponentContainer, EventDispatcher, 
//            Form

public final class SoundRecorder extends AndroidNonvisibleComponent
    implements Component, android.media.MediaRecorder.OnErrorListener, android.media.MediaRecorder.OnInfoListener
{
    private class RecordingController
    {

        final String file = FileUtil.getRecordingFile("3gp").getAbsolutePath();
        final MediaRecorder recorder = new MediaRecorder();
        final SoundRecorder this$0;

        void start()
        {
            Log.i("SoundRecorder", "starting");
            recorder.start();
        }

        void stop()
        {
            recorder.setOnErrorListener(null);
            recorder.setOnInfoListener(null);
            recorder.stop();
            recorder.reset();
            recorder.release();
        }

        RecordingController()
            throws IOException
        {
            this$0 = SoundRecorder.this;
            super();
            recorder.setAudioSource(1);
            recorder.setOutputFormat(1);
            recorder.setAudioEncoder(1);
            Log.i("SoundRecorder", (new StringBuilder()).append("Setting output file to ").append(file).toString());
            recorder.setOutputFile(file);
            Log.i("SoundRecorder", "preparing");
            recorder.prepare();
            recorder.setOnErrorListener(SoundRecorder.this);
            recorder.setOnInfoListener(SoundRecorder.this);
        }
    }


    private static final String TAG = "SoundRecorder";
    private RecordingController controller;

    public SoundRecorder(ComponentContainer componentcontainer)
    {
        super(componentcontainer.$form());
    }

    public void AfterSoundRecorded(String s)
    {
        EventDispatcher.dispatchEvent(this, "AfterSoundRecorded", new Object[] {
            s
        });
    }

    public void Start()
    {
        if (controller != null)
        {
            Log.i("SoundRecorder", (new StringBuilder()).append("Start() called, but already recording to ").append(controller.file).toString());
            return;
        }
        Log.i("SoundRecorder", "Start() called");
        if (!Environment.getExternalStorageState().equals("mounted"))
        {
            this.form.dispatchErrorOccurredEvent(this, "Start", 705, new Object[0]);
            return;
        }
        try
        {
            controller = new RecordingController();
        }
        catch (Throwable throwable)
        {
            Form form = this.form;
            Object aobj[] = new Object[1];
            aobj[0] = throwable.getMessage();
            form.dispatchErrorOccurredEvent(this, "Start", 802, aobj);
            return;
        }
        try
        {
            controller.start();
        }
        catch (Throwable throwable1)
        {
            controller.stop();
            controller = null;
            Form form1 = this.form;
            Object aobj1[] = new Object[1];
            aobj1[0] = throwable1.getMessage();
            form1.dispatchErrorOccurredEvent(this, "Start", 802, aobj1);
            return;
        }
        StartedRecording();
    }

    public void StartedRecording()
    {
        EventDispatcher.dispatchEvent(this, "StartedRecording", new Object[0]);
    }

    public void Stop()
    {
        if (controller == null)
        {
            Log.i("SoundRecorder", "Stop() called, but already stopped.");
            return;
        }
        Log.i("SoundRecorder", "Stop() called");
        Log.i("SoundRecorder", "stopping");
        controller.stop();
        Log.i("SoundRecorder", (new StringBuilder()).append("Firing AfterSoundRecorded with ").append(controller.file).toString());
        AfterSoundRecorded(controller.file);
        controller = null;
        StoppedRecording();
        return;
        Throwable throwable;
        throwable;
        form.dispatchErrorOccurredEvent(this, "Stop", 801, new Object[0]);
        controller = null;
        StoppedRecording();
        return;
        Exception exception;
        exception;
        controller = null;
        StoppedRecording();
        throw exception;
    }

    public void StoppedRecording()
    {
        EventDispatcher.dispatchEvent(this, "StoppedRecording", new Object[0]);
    }

    public void onError(MediaRecorder mediarecorder, int i, int j)
    {
        if (controller == null || mediarecorder != controller.recorder)
        {
            Log.w("SoundRecorder", "onError called with wrong recorder. Ignoring.");
            return;
        }
        form.dispatchErrorOccurredEvent(this, "onError", 801, new Object[0]);
        controller.stop();
        controller = null;
        StoppedRecording();
        return;
        Throwable throwable;
        throwable;
        Log.w("SoundRecorder", throwable.getMessage());
        controller = null;
        StoppedRecording();
        return;
        Exception exception;
        exception;
        controller = null;
        StoppedRecording();
        throw exception;
    }

    public void onInfo(MediaRecorder mediarecorder, int i, int j)
    {
        if (controller == null || mediarecorder != controller.recorder)
        {
            Log.w("SoundRecorder", "onInfo called with wrong recorder. Ignoring.");
            return;
        } else
        {
            Log.i("SoundRecorder", "Recoverable condition while recording. Will attempt to stop normally.");
            controller.recorder.stop();
            return;
        }
    }
}
