.class Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;
.super Ljava/lang/Object;
.source "Canvas.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/appinventor/components/runtime/Canvas;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = "MotionEventParser"
.end annotation


# static fields
.field public static final FINGER_HEIGHT:I = 0x18

.field public static final FINGER_WIDTH:I = 0x18

.field private static final HALF_FINGER_HEIGHT:I = 0xc

.field private static final HALF_FINGER_WIDTH:I = 0xc

.field public static final TAP_THRESHOLD:I = 0x1e

.field private static final UNSET:I = -0x1


# instance fields
.field private drag:Z

.field private final draggedSprites:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List",
            "<",
            "Lcom/google/appinventor/components/runtime/Sprite;",
            ">;"
        }
    .end annotation
.end field

.field private isDrag:Z

.field private lastX:F

.field private lastY:F

.field private startX:F

.field private startY:F

.field final synthetic this$0:Lcom/google/appinventor/components/runtime/Canvas;


# direct methods
.method constructor <init>(Lcom/google/appinventor/components/runtime/Canvas;)V
    .locals 3
    .parameter

    .prologue
    const/4 v2, 0x0

    const/high16 v1, -0x4080

    .line 156
    iput-object p1, p0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->this$0:Lcom/google/appinventor/components/runtime/Canvas;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 187
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->draggedSprites:Ljava/util/List;

    .line 191
    iput v1, p0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startX:F

    .line 192
    iput v1, p0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startY:F

    .line 195
    iput v1, p0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->lastX:F

    .line 196
    iput v1, p0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->lastY:F

    .line 200
    iput-boolean v2, p0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->isDrag:Z

    .line 202
    iput-boolean v2, p0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->drag:Z

    return-void
.end method


# virtual methods
.method parse(Landroid/view/MotionEvent;)V
    .locals 16
    .parameter "event"

    .prologue
    .line 205
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->this$0:Lcom/google/appinventor/components/runtime/Canvas;

    invoke-virtual {v3}, Lcom/google/appinventor/components/runtime/Canvas;->Width()I

    move-result v13

    .line 206
    .local v13, width:I
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->this$0:Lcom/google/appinventor/components/runtime/Canvas;

    invoke-virtual {v3}, Lcom/google/appinventor/components/runtime/Canvas;->Height()I

    move-result v11

    .line 212
    .local v11, height:I
    const/4 v3, 0x0

    invoke-virtual/range {p1 .. p1}, Landroid/view/MotionEvent;->getX()F

    move-result v4

    float-to-int v4, v4

    invoke-static {v3, v4}, Ljava/lang/Math;->max(II)I

    move-result v3

    int-to-float v14, v3

    .line 213
    .local v14, x:F
    const/4 v3, 0x0

    invoke-virtual/range {p1 .. p1}, Landroid/view/MotionEvent;->getY()F

    move-result v4

    float-to-int v4, v4

    invoke-static {v3, v4}, Ljava/lang/Math;->max(II)I

    move-result v3

    int-to-float v15, v3

    .line 217
    .local v15, y:F
    new-instance v1, Lcom/google/appinventor/components/runtime/util/BoundingBox;

    const/4 v3, 0x0

    float-to-int v4, v14

    add-int/lit8 v4, v4, -0xc

    invoke-static {v3, v4}, Ljava/lang/Math;->max(II)I

    move-result v3

    int-to-double v2, v3

    const/4 v4, 0x0

    float-to-int v5, v15

    add-int/lit8 v5, v5, -0xc

    invoke-static {v4, v5}, Ljava/lang/Math;->max(II)I

    move-result v4

    int-to-double v4, v4

    add-int/lit8 v6, v13, -0x1

    float-to-int v7, v14

    add-int/lit8 v7, v7, 0xc

    invoke-static {v6, v7}, Ljava/lang/Math;->min(II)I

    move-result v6

    int-to-double v6, v6

    add-int/lit8 v8, v11, -0x1

    float-to-int v9, v15

    add-int/lit8 v9, v9, 0xc

    invoke-static {v8, v9}, Ljava/lang/Math;->min(II)I

    move-result v8

    int-to-double v8, v8

    invoke-direct/range {v1 .. v9}, Lcom/google/appinventor/components/runtime/util/BoundingBox;-><init>(DDDD)V

    .line 223
    .local v1, rect:Lcom/google/appinventor/components/runtime/util/BoundingBox;
    invoke-virtual/range {p1 .. p1}, Landroid/view/MotionEvent;->getAction()I

    move-result v3

    packed-switch v3, :pswitch_data_0

    .line 320
    :cond_0
    :goto_0
    return-void

    .line 225
    :pswitch_0
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->draggedSprites:Ljava/util/List;

    invoke-interface {v3}, Ljava/util/List;->clear()V

    .line 226
    move-object/from16 v0, p0

    iput v14, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startX:F

    .line 227
    move-object/from16 v0, p0

    iput v15, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startY:F

    .line 228
    move-object/from16 v0, p0

    iput v14, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->lastX:F

    .line 229
    move-object/from16 v0, p0

    iput v15, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->lastY:F

    .line 230
    const/4 v3, 0x0

    move-object/from16 v0, p0

    iput-boolean v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->drag:Z

    .line 231
    const/4 v3, 0x0

    move-object/from16 v0, p0

    iput-boolean v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->isDrag:Z

    .line 232
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->this$0:Lcom/google/appinventor/components/runtime/Canvas;

    #getter for: Lcom/google/appinventor/components/runtime/Canvas;->sprites:Ljava/util/List;
    invoke-static {v3}, Lcom/google/appinventor/components/runtime/Canvas;->access$000(Lcom/google/appinventor/components/runtime/Canvas;)Ljava/util/List;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v12

    .local v12, i$:Ljava/util/Iterator;
    :cond_1
    :goto_1
    invoke-interface {v12}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_2

    invoke-interface {v12}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/appinventor/components/runtime/Sprite;

    .line 233
    .local v2, sprite:Lcom/google/appinventor/components/runtime/Sprite;
    invoke-virtual {v2}, Lcom/google/appinventor/components/runtime/Sprite;->Enabled()Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-virtual {v2}, Lcom/google/appinventor/components/runtime/Sprite;->Visible()Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-virtual {v2, v1}, Lcom/google/appinventor/components/runtime/Sprite;->intersectsWith(Lcom/google/appinventor/components/runtime/util/BoundingBox;)Z

    move-result v3

    if-eqz v3, :cond_1

    .line 234
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->draggedSprites:Ljava/util/List;

    invoke-interface {v3, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 235
    move-object/from16 v0, p0

    iget v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startX:F

    move-object/from16 v0, p0

    iget v4, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startY:F

    invoke-virtual {v2, v3, v4}, Lcom/google/appinventor/components/runtime/Sprite;->TouchDown(FF)V

    goto :goto_1

    .line 238
    .end local v2           #sprite:Lcom/google/appinventor/components/runtime/Sprite;
    :cond_2
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->this$0:Lcom/google/appinventor/components/runtime/Canvas;

    move-object/from16 v0, p0

    iget v4, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startX:F

    move-object/from16 v0, p0

    iget v5, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startY:F

    invoke-virtual {v3, v4, v5}, Lcom/google/appinventor/components/runtime/Canvas;->TouchDown(FF)V

    goto :goto_0

    .line 243
    .end local v12           #i$:Ljava/util/Iterator;
    :pswitch_1
    move-object/from16 v0, p0

    iget v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startX:F

    const/high16 v4, -0x4080

    cmpl-float v3, v3, v4

    if-eqz v3, :cond_3

    move-object/from16 v0, p0

    iget v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startY:F

    const/high16 v4, -0x4080

    cmpl-float v3, v3, v4

    if-eqz v3, :cond_3

    move-object/from16 v0, p0

    iget v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->lastX:F

    const/high16 v4, -0x4080

    cmpl-float v3, v3, v4

    if-eqz v3, :cond_3

    move-object/from16 v0, p0

    iget v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->lastY:F

    const/high16 v4, -0x4080

    cmpl-float v3, v3, v4

    if-nez v3, :cond_4

    .line 244
    :cond_3
    const-string v3, "Canvas"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "In Canvas.MotionEventParser.parse(), an ACTION_MOVE was passed without a preceding ACTION_DOWN: "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    move-object/from16 v0, p1

    invoke-virtual {v4, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    .line 249
    :cond_4
    move-object/from16 v0, p0

    iget-boolean v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->isDrag:Z

    if-nez v3, :cond_5

    move-object/from16 v0, p0

    iget v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startX:F

    sub-float v3, v14, v3

    invoke-static {v3}, Ljava/lang/Math;->abs(F)F

    move-result v3

    const/high16 v4, 0x41f0

    cmpg-float v3, v3, v4

    if-gez v3, :cond_5

    move-object/from16 v0, p0

    iget v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startY:F

    sub-float v3, v15, v3

    invoke-static {v3}, Ljava/lang/Math;->abs(F)F

    move-result v3

    const/high16 v4, 0x41f0

    cmpg-float v3, v3, v4

    if-ltz v3, :cond_0

    .line 254
    :cond_5
    const/4 v3, 0x1

    move-object/from16 v0, p0

    iput-boolean v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->isDrag:Z

    .line 255
    const/4 v3, 0x1

    move-object/from16 v0, p0

    iput-boolean v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->drag:Z

    .line 259
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->this$0:Lcom/google/appinventor/components/runtime/Canvas;

    #getter for: Lcom/google/appinventor/components/runtime/Canvas;->sprites:Ljava/util/List;
    invoke-static {v3}, Lcom/google/appinventor/components/runtime/Canvas;->access$000(Lcom/google/appinventor/components/runtime/Canvas;)Ljava/util/List;

    move-result-object v3

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v12

    .restart local v12       #i$:Ljava/util/Iterator;
    :cond_6
    :goto_2
    invoke-interface {v12}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_7

    invoke-interface {v12}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/appinventor/components/runtime/Sprite;

    .line 260
    .restart local v2       #sprite:Lcom/google/appinventor/components/runtime/Sprite;
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->draggedSprites:Ljava/util/List;

    invoke-interface {v3, v2}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v3

    if-nez v3, :cond_6

    invoke-virtual {v2}, Lcom/google/appinventor/components/runtime/Sprite;->Enabled()Z

    move-result v3

    if-eqz v3, :cond_6

    invoke-virtual {v2}, Lcom/google/appinventor/components/runtime/Sprite;->Visible()Z

    move-result v3

    if-eqz v3, :cond_6

    invoke-virtual {v2, v1}, Lcom/google/appinventor/components/runtime/Sprite;->intersectsWith(Lcom/google/appinventor/components/runtime/util/BoundingBox;)Z

    move-result v3

    if-eqz v3, :cond_6

    .line 263
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->draggedSprites:Ljava/util/List;

    invoke-interface {v3, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    goto :goto_2

    .line 268
    .end local v2           #sprite:Lcom/google/appinventor/components/runtime/Sprite;
    :cond_7
    const/4 v10, 0x0

    .line 269
    .local v10, handled:Z
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->draggedSprites:Ljava/util/List;

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v12

    :cond_8
    :goto_3
    invoke-interface {v12}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_9

    invoke-interface {v12}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/appinventor/components/runtime/Sprite;

    .line 270
    .restart local v2       #sprite:Lcom/google/appinventor/components/runtime/Sprite;
    invoke-virtual {v2}, Lcom/google/appinventor/components/runtime/Sprite;->Enabled()Z

    move-result v3

    if-eqz v3, :cond_8

    invoke-virtual {v2}, Lcom/google/appinventor/components/runtime/Sprite;->Visible()Z

    move-result v3

    if-eqz v3, :cond_8

    .line 271
    move-object/from16 v0, p0

    iget v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startX:F

    move-object/from16 v0, p0

    iget v4, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startY:F

    move-object/from16 v0, p0

    iget v5, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->lastX:F

    move-object/from16 v0, p0

    iget v6, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->lastY:F

    move v7, v14

    move v8, v15

    invoke-virtual/range {v2 .. v8}, Lcom/google/appinventor/components/runtime/Sprite;->Dragged(FFFFFF)V

    .line 272
    const/4 v10, 0x1

    goto :goto_3

    .line 277
    .end local v2           #sprite:Lcom/google/appinventor/components/runtime/Sprite;
    :cond_9
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->this$0:Lcom/google/appinventor/components/runtime/Canvas;

    move-object/from16 v0, p0

    iget v4, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startX:F

    move-object/from16 v0, p0

    iget v5, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startY:F

    move-object/from16 v0, p0

    iget v6, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->lastX:F

    move-object/from16 v0, p0

    iget v7, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->lastY:F

    move v8, v14

    move v9, v15

    invoke-virtual/range {v3 .. v10}, Lcom/google/appinventor/components/runtime/Canvas;->Dragged(FFFFFFZ)V

    .line 278
    move-object/from16 v0, p0

    iput v14, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->lastX:F

    .line 279
    move-object/from16 v0, p0

    iput v15, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->lastY:F

    goto/16 :goto_0

    .line 286
    .end local v10           #handled:Z
    .end local v12           #i$:Ljava/util/Iterator;
    :pswitch_2
    move-object/from16 v0, p0

    iget-boolean v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->drag:Z

    if-nez v3, :cond_d

    .line 288
    const/4 v10, 0x0

    .line 289
    .restart local v10       #handled:Z
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->draggedSprites:Ljava/util/List;

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v12

    .restart local v12       #i$:Ljava/util/Iterator;
    :cond_a
    :goto_4
    invoke-interface {v12}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_b

    invoke-interface {v12}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/appinventor/components/runtime/Sprite;

    .line 290
    .restart local v2       #sprite:Lcom/google/appinventor/components/runtime/Sprite;
    invoke-virtual {v2}, Lcom/google/appinventor/components/runtime/Sprite;->Enabled()Z

    move-result v3

    if-eqz v3, :cond_a

    invoke-virtual {v2}, Lcom/google/appinventor/components/runtime/Sprite;->Visible()Z

    move-result v3

    if-eqz v3, :cond_a

    .line 291
    invoke-virtual {v2, v14, v15}, Lcom/google/appinventor/components/runtime/Sprite;->Touched(FF)V

    .line 292
    invoke-virtual {v2, v14, v15}, Lcom/google/appinventor/components/runtime/Sprite;->TouchUp(FF)V

    .line 293
    const/4 v10, 0x1

    goto :goto_4

    .line 297
    .end local v2           #sprite:Lcom/google/appinventor/components/runtime/Sprite;
    :cond_b
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->this$0:Lcom/google/appinventor/components/runtime/Canvas;

    invoke-virtual {v3, v14, v15, v10}, Lcom/google/appinventor/components/runtime/Canvas;->Touched(FFZ)V

    .line 310
    .end local v10           #handled:Z
    :cond_c
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->this$0:Lcom/google/appinventor/components/runtime/Canvas;

    invoke-virtual {v3, v14, v15}, Lcom/google/appinventor/components/runtime/Canvas;->TouchUp(FF)V

    .line 313
    const/4 v3, 0x0

    move-object/from16 v0, p0

    iput-boolean v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->drag:Z

    .line 314
    const/high16 v3, -0x4080

    move-object/from16 v0, p0

    iput v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startX:F

    .line 315
    const/high16 v3, -0x4080

    move-object/from16 v0, p0

    iput v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->startY:F

    .line 316
    const/high16 v3, -0x4080

    move-object/from16 v0, p0

    iput v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->lastX:F

    .line 317
    const/high16 v3, -0x4080

    move-object/from16 v0, p0

    iput v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->lastY:F

    goto/16 :goto_0

    .line 300
    .end local v12           #i$:Ljava/util/Iterator;
    :cond_d
    move-object/from16 v0, p0

    iget-object v3, v0, Lcom/google/appinventor/components/runtime/Canvas$MotionEventParser;->draggedSprites:Ljava/util/List;

    invoke-interface {v3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object v12

    .restart local v12       #i$:Ljava/util/Iterator;
    :cond_e
    :goto_5
    invoke-interface {v12}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_c

    invoke-interface {v12}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Lcom/google/appinventor/components/runtime/Sprite;

    .line 301
    .restart local v2       #sprite:Lcom/google/appinventor/components/runtime/Sprite;
    invoke-virtual {v2}, Lcom/google/appinventor/components/runtime/Sprite;->Enabled()Z

    move-result v3

    if-eqz v3, :cond_e

    invoke-virtual {v2}, Lcom/google/appinventor/components/runtime/Sprite;->Visible()Z

    move-result v3

    if-eqz v3, :cond_e

    .line 302
    invoke-virtual {v2, v14, v15}, Lcom/google/appinventor/components/runtime/Sprite;->Touched(FF)V

    .line 303
    invoke-virtual {v2, v14, v15}, Lcom/google/appinventor/components/runtime/Sprite;->TouchUp(FF)V

    goto :goto_5

    .line 223
    nop

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_0
        :pswitch_2
        :pswitch_1
    .end packed-switch
.end method
