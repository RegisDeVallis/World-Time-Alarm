// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.IOException;

// Referenced classes of package com.google.appinventor.components.runtime:
//            AndroidViewComponent, OnDestroyListener, Deleteable, ComponentContainer, 
//            Form, EventDispatcher

public final class VideoPlayer extends AndroidViewComponent
    implements OnDestroyListener, Deleteable, android.media.MediaPlayer.OnCompletionListener, android.media.MediaPlayer.OnErrorListener, android.media.MediaPlayer.OnPreparedListener
{
    class ResizableVideoView extends VideoView
    {

        public int forcedHeight;
        public int forcedWidth;
        private Boolean mFoundMediaPlayer;
        private MediaPlayer mVideoPlayer;
        final VideoPlayer this$0;

        public void changeVideoSize(int i, int j)
        {
            forcedWidth = i;
            forcedHeight = j;
            forceLayout();
            invalidate();
        }

        public void invalidateMediaPlayer(boolean flag)
        {
            mFoundMediaPlayer = Boolean.valueOf(false);
            mVideoPlayer = null;
            if (flag)
            {
                forceLayout();
                invalidate();
            }
        }

        public void onMeasure(int i, int j)
        {
            int k;
            int l;
            Log.i("VideoPlayer..onMeasure", (new StringBuilder()).append("AI setting dimensions as:").append(forcedWidth).append(":").append(forcedHeight).toString());
            Log.i("VideoPlayer..onMeasure", (new StringBuilder()).append("Dimenions from super>>").append(android.view.View.MeasureSpec.getSize(i)).append(":").append(android.view.View.MeasureSpec.getSize(j)).toString());
            k = 176;
            l = 144;
            forcedWidth;
            JVM INSTR tableswitch -2 -1: default 116
        //                       -2 212
        //                       -1 303;
               goto _L1 _L2 _L3
_L1:
            k = forcedWidth;
_L7:
            forcedHeight;
            JVM INSTR tableswitch -2 -1: default 148
        //                       -2 390
        //                       -1 432;
               goto _L4 _L5 _L6
_L4:
            l = forcedHeight;
_L11:
            Log.i("VideoPlayer.onMeasure", (new StringBuilder()).append("Setting dimensions to:").append(k).append("x").append(l).toString());
            getHolder().setFixedSize(k, l);
            setMeasuredDimension(k, l);
            return;
_L2:
            android.view.View.MeasureSpec.getMode(i);
            JVM INSTR lookupswitch 3: default 252
        //                       -2147483648: 255
        //                       0: 264
        //                       1073741824: 255;
               goto _L7 _L8 _L9 _L8
_L8:
            k = android.view.View.MeasureSpec.getSize(i);
              goto _L7
_L9:
            int i1 = ((View)getParent()).getMeasuredWidth();
            k = i1;
              goto _L7
            ClassCastException classcastexception;
            classcastexception;
            k = 176;
              goto _L7
            NullPointerException nullpointerexception2;
            nullpointerexception2;
            k = 176;
              goto _L7
_L3:
            if (mFoundMediaPlayer.booleanValue())
            {
                try
                {
                    k = mVideoPlayer.getVideoWidth();
                    Log.i("VideoPlayer.onMeasure", (new StringBuilder()).append("Got width from MediaPlayer>").append(k).toString());
                }
                catch (NullPointerException nullpointerexception)
                {
                    Log.e("VideoPlayer..onMeasure", (new StringBuilder()).append("Failed to get MediaPlayer for width:\n").append(nullpointerexception.getMessage()).toString());
                    k = 176;
                }
            }
              goto _L7
_L5:
            switch (android.view.View.MeasureSpec.getMode(j))
            {
            case -2147483648: 
            case 1073741824: 
                l = android.view.View.MeasureSpec.getSize(j);
                break;
            }
            if (true) goto _L11; else goto _L10
_L10:
_L6:
            if (mFoundMediaPlayer.booleanValue())
            {
                try
                {
                    l = mVideoPlayer.getVideoHeight();
                    Log.i("VideoPlayer.onMeasure", (new StringBuilder()).append("Got height from MediaPlayer>").append(l).toString());
                }
                catch (NullPointerException nullpointerexception1)
                {
                    Log.e("VideoPlayer..onMeasure", (new StringBuilder()).append("Failed to get MediaPlayer for height:\n").append(nullpointerexception1.getMessage()).toString());
                    l = 144;
                }
            }
              goto _L11
        }

        public void setMediaPlayer(MediaPlayer mediaplayer, boolean flag)
        {
            mVideoPlayer = mediaplayer;
            mFoundMediaPlayer = Boolean.valueOf(true);
            if (flag)
            {
                forceLayout();
                invalidate();
            }
        }

        public ResizableVideoView(Context context)
        {
            this$0 = VideoPlayer.this;
            super(context);
            mFoundMediaPlayer = Boolean.valueOf(false);
            forcedWidth = -1;
            forcedHeight = -1;
        }
    }


    private boolean delayedStart;
    private boolean inFullScreen;
    private MediaPlayer mPlayer;
    private boolean mediaReady;
    private String sourcePath;
    private final ResizableVideoView videoView;

    public VideoPlayer(ComponentContainer componentcontainer)
    {
        super(componentcontainer);
        inFullScreen = false;
        mediaReady = false;
        delayedStart = false;
        componentcontainer.$form().registerForOnDestroy(this);
        videoView = new ResizableVideoView(componentcontainer.$context());
        videoView.setMediaController(new MediaController(componentcontainer.$context()));
        videoView.setOnCompletionListener(this);
        videoView.setOnErrorListener(this);
        videoView.setOnPreparedListener(this);
        componentcontainer.$add(this);
        componentcontainer.setChildWidth(this, 176);
        componentcontainer.setChildHeight(this, 144);
        componentcontainer.$form().setVolumeControlStream(3);
        sourcePath = "";
    }

    private void prepareToDie()
    {
        if (videoView.isPlaying())
        {
            videoView.stopPlayback();
        }
        videoView.setVideoURI(null);
        videoView.clearAnimation();
        delayedStart = false;
        mediaReady = false;
        if (inFullScreen)
        {
            Bundle bundle = new Bundle();
            bundle.putBoolean("FullScreenKey", false);
            container.$form().fullScreenVideoAction(195, this, bundle);
        }
    }

    public void Completed()
    {
        EventDispatcher.dispatchEvent(this, "Completed", new Object[0]);
    }

    public void FullScreen(boolean flag)
    {
        if (flag && SdkLevel.getLevel() <= 4)
        {
            container.$form().dispatchErrorOccurredEvent(this, "FullScreen(true)", 1303, new Object[0]);
        } else
        if (flag != inFullScreen)
        {
            if (flag)
            {
                Bundle bundle = new Bundle();
                bundle.putInt("PositionKey", videoView.getCurrentPosition());
                bundle.putBoolean("PlayingKey", videoView.isPlaying());
                videoView.pause();
                bundle.putBoolean("FullScreenKey", true);
                bundle.putString("SourceKey", sourcePath);
                if (container.$form().fullScreenVideoAction(195, this, bundle).getBoolean("ActionSuccess"))
                {
                    inFullScreen = true;
                    return;
                } else
                {
                    inFullScreen = false;
                    container.$form().dispatchErrorOccurredEvent(this, "FullScreen", 1301, new Object[] {
                        ""
                    });
                    return;
                }
            }
            Bundle bundle1 = new Bundle();
            bundle1.putBoolean("FullScreenKey", false);
            Bundle bundle2 = container.$form().fullScreenVideoAction(195, this, bundle1);
            if (bundle2.getBoolean("ActionSuccess"))
            {
                fullScreenKilled(bundle2);
                return;
            } else
            {
                inFullScreen = true;
                container.$form().dispatchErrorOccurredEvent(this, "FullScreen", 1302, new Object[] {
                    ""
                });
                return;
            }
        }
    }

    public boolean FullScreen()
    {
        return inFullScreen;
    }

    public int GetDuration()
    {
        Log.i("VideoPlayer", "Calling GetDuration");
        if (inFullScreen)
        {
            Bundle bundle = container.$form().fullScreenVideoAction(196, this, null);
            if (bundle.getBoolean("ActionSuccess"))
            {
                return bundle.getInt("ActionData");
            } else
            {
                return 0;
            }
        } else
        {
            return videoView.getDuration();
        }
    }

    public int Height()
    {
        return super.Height();
    }

    public void Height(int i)
    {
        super.Height(i);
        videoView.changeVideoSize(videoView.forcedWidth, i);
    }

    public void Pause()
    {
        Log.i("VideoPlayer", "Calling Pause");
        if (inFullScreen)
        {
            container.$form().fullScreenVideoAction(192, this, null);
            delayedStart = false;
            return;
        } else
        {
            delayedStart = false;
            videoView.pause();
            return;
        }
    }

    public void SeekTo(int i)
    {
        Log.i("VideoPlayer", "Calling SeekTo");
        if (i < 0)
        {
            i = 0;
        }
        if (inFullScreen)
        {
            container.$form().fullScreenVideoAction(190, this, Integer.valueOf(i));
            return;
        } else
        {
            videoView.seekTo(i);
            return;
        }
    }

    public void Source(String s)
    {
        if (inFullScreen)
        {
            container.$form().fullScreenVideoAction(194, this, s);
        } else
        {
            if (s == null)
            {
                s = "";
            }
            sourcePath = s;
            videoView.invalidateMediaPlayer(true);
            if (videoView.isPlaying())
            {
                videoView.stopPlayback();
            }
            videoView.setVideoURI(null);
            videoView.clearAnimation();
            if (sourcePath.length() > 0)
            {
                Log.i("VideoPlayer", (new StringBuilder()).append("Source path is ").append(sourcePath).toString());
                try
                {
                    mediaReady = false;
                    MediaUtil.loadVideoView(videoView, container.$form(), sourcePath);
                }
                catch (IOException ioexception)
                {
                    Form form = container.$form();
                    Object aobj[] = new Object[1];
                    aobj[0] = sourcePath;
                    form.dispatchErrorOccurredEvent(this, "Source", 701, aobj);
                    return;
                }
                Log.i("VideoPlayer", "loading video succeeded");
                return;
            }
        }
    }

    public void Start()
    {
        Log.i("VideoPlayer", "Calling Start");
        if (inFullScreen)
        {
            container.$form().fullScreenVideoAction(191, this, null);
            return;
        }
        if (mediaReady)
        {
            videoView.start();
            return;
        } else
        {
            delayedStart = true;
            return;
        }
    }

    public void VideoPlayerError(String s)
    {
    }

    public void Volume(int i)
    {
        int j = Math.min(Math.max(i, 0), 100);
        if (mPlayer != null)
        {
            mPlayer.setVolume((float)j / 100F, (float)j / 100F);
        }
    }

    public int Width()
    {
        return super.Width();
    }

    public void Width(int i)
    {
        super.Width(i);
        videoView.changeVideoSize(i, videoView.forcedHeight);
    }

    public void delayedStart()
    {
        delayedStart = true;
        Start();
    }

    public void fullScreenKilled(Bundle bundle)
    {
        inFullScreen = false;
        String s = bundle.getString("SourceKey");
        if (!s.equals(sourcePath))
        {
            Source(s);
        }
        videoView.setVisibility(0);
        videoView.requestLayout();
        SeekTo(bundle.getInt("PositionKey"));
        if (bundle.getBoolean("PlayingKey"))
        {
            Start();
        }
    }

    public int getPassedHeight()
    {
        return videoView.forcedHeight;
    }

    public int getPassedWidth()
    {
        return videoView.forcedWidth;
    }

    public View getView()
    {
        return videoView;
    }

    public void onCompletion(MediaPlayer mediaplayer)
    {
        Completed();
    }

    public void onDelete()
    {
        prepareToDie();
    }

    public void onDestroy()
    {
        prepareToDie();
    }

    public boolean onError(MediaPlayer mediaplayer, int i, int j)
    {
        videoView.invalidateMediaPlayer(true);
        delayedStart = false;
        mediaReady = false;
        Log.e("VideoPlayer", (new StringBuilder()).append("onError: what is ").append(i).append(" 0x").append(Integer.toHexString(i)).append(", extra is ").append(j).append(" 0x").append(Integer.toHexString(j)).toString());
        Form form = container.$form();
        Object aobj[] = new Object[1];
        aobj[0] = sourcePath;
        form.dispatchErrorOccurredEvent(this, "Source", 701, aobj);
        return true;
    }

    public void onPrepared(MediaPlayer mediaplayer)
    {
        mediaReady = true;
        delayedStart = false;
        mPlayer = mediaplayer;
        videoView.setMediaPlayer(mPlayer, true);
        if (delayedStart)
        {
            Start();
        }
    }
}
