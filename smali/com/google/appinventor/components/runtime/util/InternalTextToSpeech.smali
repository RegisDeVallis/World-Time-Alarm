.class public Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;
.super Ljava/lang/Object;
.source "InternalTextToSpeech.java"

# interfaces
.implements Lcom/google/appinventor/components/runtime/util/ITextToSpeech;


# static fields
.field private static final LOG_TAG:Ljava/lang/String; = "InternalTTS"


# instance fields
.field private final activity:Landroid/app/Activity;

.field private final callback:Lcom/google/appinventor/components/runtime/util/ITextToSpeech$TextToSpeechCallback;

.field private volatile isTtsInitialized:Z

.field private mHandler:Landroid/os/Handler;

.field private nextUtteranceId:I

.field private tts:Landroid/speech/tts/TextToSpeech;

.field private ttsMaxRetries:I

.field private ttsRetryDelay:I


# direct methods
.method public constructor <init>(Landroid/app/Activity;Lcom/google/appinventor/components/runtime/util/ITextToSpeech$TextToSpeechCallback;)V
    .locals 1
    .parameter "activity"
    .parameter "callback"

    .prologue
    .line 53
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 41
    new-instance v0, Landroid/os/Handler;

    invoke-direct {v0}, Landroid/os/Handler;-><init>()V

    iput-object v0, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->mHandler:Landroid/os/Handler;

    .line 43
    const/4 v0, 0x1

    iput v0, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->nextUtteranceId:I

    .line 46
    const/16 v0, 0x1f4

    iput v0, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->ttsRetryDelay:I

    .line 51
    const/16 v0, 0x14

    iput v0, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->ttsMaxRetries:I

    .line 54
    iput-object p1, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->activity:Landroid/app/Activity;

    .line 55
    iput-object p2, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->callback:Lcom/google/appinventor/components/runtime/util/ITextToSpeech$TextToSpeechCallback;

    .line 56
    invoke-direct {p0}, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->initializeTts()V

    .line 57
    return-void
.end method

.method static synthetic access$002(Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;Z)Z
    .locals 0
    .parameter "x0"
    .parameter "x1"

    .prologue
    .line 32
    iput-boolean p1, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->isTtsInitialized:Z

    return p1
.end method

.method static synthetic access$100(Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;)Lcom/google/appinventor/components/runtime/util/ITextToSpeech$TextToSpeechCallback;
    .locals 1
    .parameter "x0"

    .prologue
    .line 32
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->callback:Lcom/google/appinventor/components/runtime/util/ITextToSpeech$TextToSpeechCallback;

    return-object v0
.end method

.method static synthetic access$200(Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;)Landroid/app/Activity;
    .locals 1
    .parameter "x0"

    .prologue
    .line 32
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->activity:Landroid/app/Activity;

    return-object v0
.end method

.method static synthetic access$300(Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;Ljava/lang/String;Ljava/util/Locale;I)V
    .locals 0
    .parameter "x0"
    .parameter "x1"
    .parameter "x2"
    .parameter "x3"

    .prologue
    .line 32
    invoke-direct {p0, p1, p2, p3}, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->speak(Ljava/lang/String;Ljava/util/Locale;I)V

    return-void
.end method

.method private initializeTts()V
    .locals 3

    .prologue
    .line 60
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->tts:Landroid/speech/tts/TextToSpeech;

    if-nez v0, :cond_0

    .line 61
    const-string v0, "InternalTTS"

    const-string v1, "INTERNAL TTS is reinitializing"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 62
    new-instance v0, Landroid/speech/tts/TextToSpeech;

    iget-object v1, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->activity:Landroid/app/Activity;

    new-instance v2, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech$1;

    invoke-direct {v2, p0}, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech$1;-><init>(Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;)V

    invoke-direct {v0, v1, v2}, Landroid/speech/tts/TextToSpeech;-><init>(Landroid/content/Context;Landroid/speech/tts/TextToSpeech$OnInitListener;)V

    iput-object v0, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->tts:Landroid/speech/tts/TextToSpeech;

    .line 71
    :cond_0
    return-void
.end method

.method private speak(Ljava/lang/String;Ljava/util/Locale;I)V
    .locals 6
    .parameter "message"
    .parameter "loc"
    .parameter "retries"

    .prologue
    .line 81
    const-string v2, "InternalTTS"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "InternalTTS speak called, message = "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 82
    iget v2, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->ttsMaxRetries:I

    if-le p3, v2, :cond_0

    .line 83
    const-string v2, "InternalTTS"

    const-string v3, "max number of speak retries exceeded: speak will fail"

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 84
    iget-object v2, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->callback:Lcom/google/appinventor/components/runtime/util/ITextToSpeech$TextToSpeechCallback;

    invoke-interface {v2}, Lcom/google/appinventor/components/runtime/util/ITextToSpeech$TextToSpeechCallback;->onFailure()V

    .line 88
    :cond_0
    iget-boolean v2, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->isTtsInitialized:Z

    if-eqz v2, :cond_2

    .line 89
    const-string v2, "InternalTTS"

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "TTS initialized after "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p3}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, " retries."

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 90
    iget-object v2, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->tts:Landroid/speech/tts/TextToSpeech;

    invoke-virtual {v2, p2}, Landroid/speech/tts/TextToSpeech;->setLanguage(Ljava/util/Locale;)I

    .line 91
    iget-object v2, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->tts:Landroid/speech/tts/TextToSpeech;

    new-instance v3, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech$2;

    invoke-direct {v3, p0}, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech$2;-><init>(Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;)V

    invoke-virtual {v2, v3}, Landroid/speech/tts/TextToSpeech;->setOnUtteranceCompletedListener(Landroid/speech/tts/TextToSpeech$OnUtteranceCompletedListener;)I

    .line 105
    new-instance v0, Ljava/util/HashMap;

    invoke-direct {v0}, Ljava/util/HashMap;-><init>()V

    .line 106
    .local v0, params:Ljava/util/HashMap;,"Ljava/util/HashMap<Ljava/lang/String;Ljava/lang/String;>;"
    const-string v2, "utteranceId"

    iget v3, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->nextUtteranceId:I

    add-int/lit8 v4, v3, 0x1

    iput v4, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->nextUtteranceId:I

    invoke-static {v3}, Ljava/lang/Integer;->toString(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v2, v3}, Ljava/util/HashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    .line 107
    iget-object v2, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->tts:Landroid/speech/tts/TextToSpeech;

    iget-object v3, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->tts:Landroid/speech/tts/TextToSpeech;

    const/4 v3, 0x0

    invoke-virtual {v2, p1, v3, v0}, Landroid/speech/tts/TextToSpeech;->speak(Ljava/lang/String;ILjava/util/HashMap;)I

    move-result v1

    .line 108
    .local v1, result:I
    const/4 v2, -0x1

    if-ne v1, v2, :cond_1

    .line 109
    const-string v2, "InternalTTS"

    const-string v3, "speak called and tts.speak result was an error"

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 110
    iget-object v2, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->callback:Lcom/google/appinventor/components/runtime/util/ITextToSpeech$TextToSpeechCallback;

    invoke-interface {v2}, Lcom/google/appinventor/components/runtime/util/ITextToSpeech$TextToSpeechCallback;->onFailure()V

    .line 122
    .end local v0           #params:Ljava/util/HashMap;,"Ljava/util/HashMap<Ljava/lang/String;Ljava/lang/String;>;"
    .end local v1           #result:I
    :cond_1
    :goto_0
    return-void

    .line 113
    :cond_2
    const-string v2, "InternalTTS"

    const-string v3, "speak called when TTS not initialized"

    invoke-static {v2, v3}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 114
    iget-object v2, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->mHandler:Landroid/os/Handler;

    new-instance v3, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech$3;

    invoke-direct {v3, p0, p3, p1, p2}, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech$3;-><init>(Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;ILjava/lang/String;Ljava/util/Locale;)V

    iget v4, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->ttsRetryDelay:I

    int-to-long v4, v4

    invoke-virtual {v2, v3, v4, v5}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    goto :goto_0
.end method


# virtual methods
.method public onDestroy()V
    .locals 2

    .prologue
    .line 132
    const-string v0, "InternalTTS"

    const-string v1, "Internal TTS got onDestroy"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 133
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->tts:Landroid/speech/tts/TextToSpeech;

    if-eqz v0, :cond_0

    .line 134
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->tts:Landroid/speech/tts/TextToSpeech;

    invoke-virtual {v0}, Landroid/speech/tts/TextToSpeech;->shutdown()V

    .line 135
    const/4 v0, 0x0

    iput-boolean v0, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->isTtsInitialized:Z

    .line 136
    const/4 v0, 0x0

    iput-object v0, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->tts:Landroid/speech/tts/TextToSpeech;

    .line 138
    :cond_0
    return-void
.end method

.method public onResume()V
    .locals 2

    .prologue
    .line 142
    const-string v0, "InternalTTS"

    const-string v1, "Internal TTS got onResume"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 143
    invoke-direct {p0}, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->initializeTts()V

    .line 144
    return-void
.end method

.method public onStop()V
    .locals 2

    .prologue
    .line 126
    const-string v0, "InternalTTS"

    const-string v1, "Internal TTS got onStop"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 128
    return-void
.end method

.method public setPitch(F)V
    .locals 1
    .parameter "pitch"

    .prologue
    .line 148
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->tts:Landroid/speech/tts/TextToSpeech;

    invoke-virtual {v0, p1}, Landroid/speech/tts/TextToSpeech;->setPitch(F)I

    .line 149
    return-void
.end method

.method public setSpeechRate(F)V
    .locals 1
    .parameter "speechRate"

    .prologue
    .line 153
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->tts:Landroid/speech/tts/TextToSpeech;

    invoke-virtual {v0, p1}, Landroid/speech/tts/TextToSpeech;->setSpeechRate(F)I

    .line 154
    return-void
.end method

.method public speak(Ljava/lang/String;Ljava/util/Locale;)V
    .locals 2
    .parameter "message"
    .parameter "loc"

    .prologue
    .line 75
    const-string v0, "InternalTTS"

    const-string v1, "Internal TTS got speak"

    invoke-static {v0, v1}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    .line 76
    const/4 v0, 0x0

    invoke-direct {p0, p1, p2, v0}, Lcom/google/appinventor/components/runtime/util/InternalTextToSpeech;->speak(Ljava/lang/String;Ljava/util/Locale;I)V

    .line 77
    return-void
.end method
