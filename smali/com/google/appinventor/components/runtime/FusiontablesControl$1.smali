.class Lcom/google/appinventor/components/runtime/FusiontablesControl$1;
.super Ljava/lang/Object;
.source "FusiontablesControl.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/google/appinventor/components/runtime/FusiontablesControl;->showNoticeAndDie(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;


# direct methods
.method constructor <init>(Lcom/google/appinventor/components/runtime/FusiontablesControl;)V
    .locals 0
    .parameter

    .prologue
    .line 240
    iput-object p1, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 1
    .parameter "dialog"
    .parameter "which"

    .prologue
    .line 242
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->activity:Landroid/app/Activity;
    invoke-static {v0}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$000(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Landroid/app/Activity;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/Activity;->finish()V

    .line 243
    return-void
.end method
