.class Lcom/google/appinventor/components/runtime/Form$5;
.super Ljava/lang/Object;
.source "Form.java"

# interfaces
.implements Landroid/view/MenuItem$OnMenuItemClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/google/appinventor/components/runtime/Form;->addAboutInfoToMenu(Landroid/view/Menu;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/google/appinventor/components/runtime/Form;


# direct methods
.method constructor <init>(Lcom/google/appinventor/components/runtime/Form;)V
    .locals 0
    .parameter

    .prologue
    .line 1331
    iput-object p1, p0, Lcom/google/appinventor/components/runtime/Form$5;->this$0:Lcom/google/appinventor/components/runtime/Form;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onMenuItemClick(Landroid/view/MenuItem;)Z
    .locals 1
    .parameter "item"

    .prologue
    .line 1333
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/Form$5;->this$0:Lcom/google/appinventor/components/runtime/Form;

    #calls: Lcom/google/appinventor/components/runtime/Form;->showAboutApplicationNotification()V
    invoke-static {v0}, Lcom/google/appinventor/components/runtime/Form;->access$500(Lcom/google/appinventor/components/runtime/Form;)V

    .line 1334
    const/4 v0, 0x1

    return v0
.end method
