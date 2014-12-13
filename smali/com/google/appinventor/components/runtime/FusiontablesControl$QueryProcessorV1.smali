.class Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;
.super Landroid/os/AsyncTask;
.source "FusiontablesControl.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lcom/google/appinventor/components/runtime/FusiontablesControl;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "QueryProcessorV1"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroid/os/AsyncTask",
        "<",
        "Ljava/lang/String;",
        "Ljava/lang/Void;",
        "Ljava/lang/String;",
        ">;"
    }
.end annotation


# static fields
.field private static final TAG:Ljava/lang/String; = "QueryProcessorV1"


# instance fields
.field private final activity:Landroid/app/Activity;

.field private final dialog:Landroid/app/ProgressDialog;

.field final synthetic this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;


# direct methods
.method constructor <init>(Lcom/google/appinventor/components/runtime/FusiontablesControl;Landroid/app/Activity;)V
    .locals 2
    .parameter
    .parameter "activity"

    .prologue
    .line 749
    iput-object p1, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    invoke-direct {p0}, Landroid/os/AsyncTask;-><init>()V

    .line 750
    const-string v0, "QueryProcessorV1"

    const-string v1, "Creating AsyncFusiontablesQuery"

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 751
    iput-object p2, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->activity:Landroid/app/Activity;

    .line 752
    new-instance v0, Landroid/app/ProgressDialog;

    invoke-direct {v0, p2}, Landroid/app/ProgressDialog;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->dialog:Landroid/app/ProgressDialog;

    .line 753
    return-void
.end method

.method private serviceAuthRequest(Ljava/lang/String;)Ljava/lang/String;
    .locals 12
    .parameter "query"

    .prologue
    .line 811
    const-string v1, "SERVICE_ACCOUNT"

    .line 813
    .local v1, STAG:Ljava/lang/String;
    invoke-static {}, Lcom/google/api/client/extensions/android2/AndroidHttp;->newCompatibleTransport()Lcom/google/api/client/http/HttpTransport;

    move-result-object v2

    .line 814
    .local v2, TRANSPORT:Lcom/google/api/client/http/HttpTransport;
    new-instance v0, Lcom/google/api/client/json/gson/GsonFactory;

    invoke-direct {v0}, Lcom/google/api/client/json/gson/GsonFactory;-><init>()V

    .line 816
    .local v0, JSON_FACTORY:Lcom/google/api/client/json/JsonFactory;
    new-instance v8, Ljava/lang/StringBuilder;

    invoke-direct {v8}, Ljava/lang/StringBuilder;-><init>()V

    const-string v9, "keyPath "

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    iget-object v9, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->keyPath:Ljava/lang/String;
    invoke-static {v9}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$1000(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v8

    invoke-virtual {v8}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-static {v1, v8}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 819
    :try_start_0
    iget-object v8, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->cachedServiceCredentials:Ljava/io/File;
    invoke-static {v8}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$1100(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/io/File;

    move-result-object v8

    if-nez v8, :cond_0

    .line 823
    iget-object v8, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    iget-object v9, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->container:Lcom/google/appinventor/components/runtime/ComponentContainer;
    invoke-static {v9}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$1200(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Lcom/google/appinventor/components/runtime/ComponentContainer;

    move-result-object v9

    invoke-interface {v9}, Lcom/google/appinventor/components/runtime/ComponentContainer;->$form()Lcom/google/appinventor/components/runtime/Form;

    move-result-object v9

    iget-object v10, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->keyPath:Ljava/lang/String;
    invoke-static {v10}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$1000(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;

    move-result-object v10

    invoke-static {v9, v10}, Lcom/google/appinventor/components/runtime/util/MediaUtil;->copyMediaToTempFile(Lcom/google/appinventor/components/runtime/Form;Ljava/lang/String;)Ljava/io/File;

    move-result-object v9

    #setter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->cachedServiceCredentials:Ljava/io/File;
    invoke-static {v8, v9}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$1102(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/io/File;)Ljava/io/File;

    .line 825
    :cond_0
    new-instance v8, Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;

    invoke-direct {v8}, Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;-><init>()V

    invoke-virtual {v8, v2}, Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;->setTransport(Lcom/google/api/client/http/HttpTransport;)Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;

    move-result-object v8

    invoke-virtual {v8, v0}, Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;->setJsonFactory(Lcom/google/api/client/json/JsonFactory;)Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;

    move-result-object v8

    iget-object v9, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->serviceAccountEmail:Ljava/lang/String;
    invoke-static {v9}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$1400(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;

    move-result-object v9

    invoke-virtual {v8, v9}, Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;->setServiceAccountId(Ljava/lang/String;)Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;

    move-result-object v8

    const/4 v9, 0x1

    new-array v9, v9, [Ljava/lang/String;

    const/4 v10, 0x0

    iget-object v11, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->scope:Ljava/lang/String;
    invoke-static {v11}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$1300(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;

    move-result-object v11

    aput-object v11, v9, v10

    invoke-virtual {v8, v9}, Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;->setServiceAccountScopes([Ljava/lang/String;)Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;

    move-result-object v8

    iget-object v9, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->cachedServiceCredentials:Ljava/io/File;
    invoke-static {v9}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$1100(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/io/File;

    move-result-object v9

    invoke-virtual {v8, v9}, Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;->setServiceAccountPrivateKeyFromP12File(Ljava/io/File;)Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;

    move-result-object v8

    invoke-virtual {v8}, Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential$Builder;->build()Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential;

    move-result-object v3

    .line 833
    .local v3, credential:Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential;
    new-instance v8, Lcom/google/api/services/fusiontables/Fusiontables$Builder;

    invoke-direct {v8, v2, v0, v3}, Lcom/google/api/services/fusiontables/Fusiontables$Builder;-><init>(Lcom/google/api/client/http/HttpTransport;Lcom/google/api/client/json/JsonFactory;Lcom/google/api/client/http/HttpRequestInitializer;)V

    new-instance v9, Lcom/google/api/client/googleapis/services/GoogleKeyInitializer;

    iget-object v10, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    invoke-virtual {v10}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->ApiKey()Ljava/lang/String;

    move-result-object v10

    invoke-direct {v9, v10}, Lcom/google/api/client/googleapis/services/GoogleKeyInitializer;-><init>(Ljava/lang/String;)V

    invoke-virtual {v8, v9}, Lcom/google/api/services/fusiontables/Fusiontables$Builder;->setJsonHttpRequestInitializer(Lcom/google/api/client/http/json/JsonHttpRequestInitializer;)Lcom/google/api/services/fusiontables/Fusiontables$Builder;

    move-result-object v8

    invoke-virtual {v8}, Lcom/google/api/services/fusiontables/Fusiontables$Builder;->build()Lcom/google/api/services/fusiontables/Fusiontables;

    move-result-object v5

    .line 837
    .local v5, fusiontables:Lcom/google/api/services/fusiontables/Fusiontables;
    invoke-virtual {v5}, Lcom/google/api/services/fusiontables/Fusiontables;->query()Lcom/google/api/services/fusiontables/Fusiontables$Query;

    move-result-object v8

    invoke-virtual {v8, p1}, Lcom/google/api/services/fusiontables/Fusiontables$Query;->sql(Ljava/lang/String;)Lcom/google/api/services/fusiontables/Fusiontables$Query$Sql;

    move-result-object v7

    .line 838
    .local v7, sql:Lcom/google/api/services/fusiontables/Fusiontables$Query$Sql;
    const-string v8, "alt"

    const-string v9, "csv"

    invoke-virtual {v7, v8, v9}, Lcom/google/api/services/fusiontables/Fusiontables$Query$Sql;->put(Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;

    .line 840
    invoke-virtual {v7}, Lcom/google/api/services/fusiontables/Fusiontables$Query$Sql;->executeUnparsed()Lcom/google/api/client/http/HttpResponse;

    move-result-object v6

    .line 843
    .local v6, response:Lcom/google/api/client/http/HttpResponse;
    if-eqz v6, :cond_1

    .line 844
    iget-object v8, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    invoke-static {v6}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->httpResponseToString(Lcom/google/api/client/http/HttpResponse;)Ljava/lang/String;

    move-result-object v9

    #setter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->queryResultStr:Ljava/lang/String;
    invoke-static {v8, v9}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$502(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;

    .line 845
    const-string v8, "QueryProcessorV1"

    new-instance v9, Ljava/lang/StringBuilder;

    invoke-direct {v9}, Ljava/lang/StringBuilder;-><init>()V

    const-string v10, "Query = "

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v9

    invoke-virtual {v9, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v9

    const-string v10, "\nResultStr = "

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v9

    iget-object v10, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->queryResultStr:Ljava/lang/String;
    invoke-static {v10}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$500(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v9

    invoke-static {v8, v9}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 851
    :goto_0
    const-string v8, "executed sql query"

    invoke-static {v1, v8}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_0
    .catch Ljava/lang/Throwable; {:try_start_0 .. :try_end_0} :catch_0

    .line 858
    .end local v3           #credential:Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential;
    .end local v5           #fusiontables:Lcom/google/api/services/fusiontables/Fusiontables;
    .end local v6           #response:Lcom/google/api/client/http/HttpResponse;
    .end local v7           #sql:Lcom/google/api/services/fusiontables/Fusiontables$Query$Sql;
    :goto_1
    iget-object v8, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->queryResultStr:Ljava/lang/String;
    invoke-static {v8}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$500(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;

    move-result-object v8

    return-object v8

    .line 847
    .restart local v3       #credential:Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential;
    .restart local v5       #fusiontables:Lcom/google/api/services/fusiontables/Fusiontables;
    .restart local v6       #response:Lcom/google/api/client/http/HttpResponse;
    .restart local v7       #sql:Lcom/google/api/services/fusiontables/Fusiontables$Query$Sql;
    :cond_1
    :try_start_1
    iget-object v8, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    iget-object v9, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->errorMessage:Ljava/lang/String;
    invoke-static {v9}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$900(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;

    move-result-object v9

    #setter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->queryResultStr:Ljava/lang/String;
    invoke-static {v8, v9}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$502(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;

    .line 848
    const-string v8, "QueryProcessorV1"

    new-instance v9, Ljava/lang/StringBuilder;

    invoke-direct {v9}, Ljava/lang/StringBuilder;-><init>()V

    const-string v10, "Error:  "

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v9

    iget-object v10, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->errorMessage:Ljava/lang/String;
    invoke-static {v10}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$900(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;

    move-result-object v10

    invoke-virtual {v9, v10}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v9

    invoke-virtual {v9}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v9

    invoke-static {v8, v9}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I
    :try_end_1
    .catch Ljava/lang/Throwable; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_0

    .line 853
    .end local v3           #credential:Lcom/google/api/client/googleapis/auth/oauth2/GoogleCredential;
    .end local v5           #fusiontables:Lcom/google/api/services/fusiontables/Fusiontables;
    .end local v6           #response:Lcom/google/api/client/http/HttpResponse;
    .end local v7           #sql:Lcom/google/api/services/fusiontables/Fusiontables$Query$Sql;
    :catch_0
    move-exception v4

    .line 854
    .local v4, e:Ljava/lang/Throwable;
    invoke-virtual {v4}, Ljava/lang/Throwable;->printStackTrace()V

    .line 855
    iget-object v8, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    invoke-virtual {v4}, Ljava/lang/Throwable;->getMessage()Ljava/lang/String;

    move-result-object v9

    #setter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->errorMessage:Ljava/lang/String;
    invoke-static {v8, v9}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$902(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;

    goto :goto_1
.end method

.method private userAuthRequest(Ljava/lang/String;)Ljava/lang/String;
    .locals 6
    .parameter "query"

    .prologue
    .line 776
    iget-object v3, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    const-string v4, ""

    #setter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->queryResultStr:Ljava/lang/String;
    invoke-static {v3, v4}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$502(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;

    .line 779
    new-instance v1, Lcom/google/appinventor/components/runtime/util/OAuth2Helper;

    invoke-direct {v1}, Lcom/google/appinventor/components/runtime/util/OAuth2Helper;-><init>()V

    .line 780
    .local v1, oauthHelper:Lcom/google/appinventor/components/runtime/util/OAuth2Helper;
    iget-object v3, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->activity:Landroid/app/Activity;

    iget-object v4, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->authTokenType:Ljava/lang/String;
    invoke-static {v4}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$600(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v1, v3, v4}, Lcom/google/appinventor/components/runtime/util/OAuth2Helper;->getRefreshedAuthToken(Landroid/app/Activity;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 784
    .local v0, authToken:Ljava/lang/String;
    if-eqz v0, :cond_2

    .line 787
    invoke-virtual {p1}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v3

    const-string v4, "create table"

    invoke-virtual {v3, v4}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v3

    if-eqz v3, :cond_0

    .line 788
    iget-object v3, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    iget-object v4, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    iget-object v5, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #calls: Lcom/google/appinventor/components/runtime/FusiontablesControl;->parseSqlCreateQueryToJson(Ljava/lang/String;)Ljava/lang/String;
    invoke-static {v5, p1}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$700(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v5

    #calls: Lcom/google/appinventor/components/runtime/FusiontablesControl;->doPostRequest(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    invoke-static {v4, v5, v0}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$800(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v4

    #setter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->queryResultStr:Ljava/lang/String;
    invoke-static {v3, v4}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$502(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;

    .line 789
    iget-object v3, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->queryResultStr:Ljava/lang/String;
    invoke-static {v3}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$500(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;

    move-result-object v3

    .line 806
    :goto_0
    return-object v3

    .line 793
    :cond_0
    iget-object v3, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    invoke-virtual {v3, p1, v0}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->sendQuery(Ljava/lang/String;Ljava/lang/String;)Lcom/google/api/client/http/HttpResponse;

    move-result-object v2

    .line 796
    .local v2, response:Lcom/google/api/client/http/HttpResponse;
    if-eqz v2, :cond_1

    .line 797
    iget-object v3, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    invoke-static {v2}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->httpResponseToString(Lcom/google/api/client/http/HttpResponse;)Ljava/lang/String;

    move-result-object v4

    #setter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->queryResultStr:Ljava/lang/String;
    invoke-static {v3, v4}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$502(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;

    .line 798
    const-string v3, "QueryProcessorV1"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "Query = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, "\nResultStr = "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    iget-object v5, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->queryResultStr:Ljava/lang/String;
    invoke-static {v5}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$500(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 803
    :goto_1
    iget-object v3, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->queryResultStr:Ljava/lang/String;
    invoke-static {v3}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$500(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;

    move-result-object v3

    goto :goto_0

    .line 800
    :cond_1
    iget-object v3, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    iget-object v4, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->errorMessage:Ljava/lang/String;
    invoke-static {v4}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$900(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;

    move-result-object v4

    #setter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->queryResultStr:Ljava/lang/String;
    invoke-static {v3, v4}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$502(Lcom/google/appinventor/components/runtime/FusiontablesControl;Ljava/lang/String;)Ljava/lang/String;

    .line 801
    const-string v3, "QueryProcessorV1"

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "Error:  "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    iget-object v5, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->errorMessage:Ljava/lang/String;
    invoke-static {v5}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$900(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-static {v3, v4}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_1

    .line 806
    .end local v2           #response:Lcom/google/api/client/http/HttpResponse;
    :cond_2
    invoke-static {}, Lcom/google/appinventor/components/runtime/util/OAuth2Helper;->getErrorMessage()Ljava/lang/String;

    move-result-object v3

    goto :goto_0
.end method


# virtual methods
.method protected bridge synthetic doInBackground([Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1
    .parameter "x0"

    .prologue
    .line 740
    check-cast p1, [Ljava/lang/String;

    .end local p1
    invoke-virtual {p0, p1}, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->doInBackground([Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method protected varargs doInBackground([Ljava/lang/String;)Ljava/lang/String;
    .locals 4
    .parameter "params"

    .prologue
    .line 766
    const/4 v1, 0x0

    aget-object v0, p1, v1

    .line 767
    .local v0, query:Ljava/lang/String;
    const-string v1, "QueryProcessorV1"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Starting doInBackground "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 768
    iget-object v1, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    #getter for: Lcom/google/appinventor/components/runtime/FusiontablesControl;->isServiceAuth:Z
    invoke-static {v1}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->access$400(Lcom/google/appinventor/components/runtime/FusiontablesControl;)Z

    move-result v1

    if-eqz v1, :cond_0

    .line 769
    invoke-direct {p0, v0}, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->serviceAuthRequest(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 771
    :goto_0
    return-object v1

    :cond_0
    invoke-direct {p0, v0}, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->userAuthRequest(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    goto :goto_0
.end method

.method protected bridge synthetic onPostExecute(Ljava/lang/Object;)V
    .locals 0
    .parameter "x0"

    .prologue
    .line 740
    check-cast p1, Ljava/lang/String;

    .end local p1
    invoke-virtual {p0, p1}, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->onPostExecute(Ljava/lang/String;)V

    return-void
.end method

.method protected onPostExecute(Ljava/lang/String;)V
    .locals 3
    .parameter "result"

    .prologue
    .line 866
    const-string v0, "fusion"

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Query result "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-static {v0, v1}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    .line 867
    if-nez p1, :cond_0

    .line 868
    const-string p1, "Error"

    .line 870
    :cond_0
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->dialog:Landroid/app/ProgressDialog;

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->dismiss()V

    .line 871
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->this$0:Lcom/google/appinventor/components/runtime/FusiontablesControl;

    invoke-virtual {v0, p1}, Lcom/google/appinventor/components/runtime/FusiontablesControl;->GotResult(Ljava/lang/String;)V

    .line 872
    return-void
.end method

.method protected onPreExecute()V
    .locals 2

    .prologue
    .line 757
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->dialog:Landroid/app/ProgressDialog;

    const-string v1, "Fusiontables..."

    invoke-virtual {v0, v1}, Landroid/app/ProgressDialog;->setMessage(Ljava/lang/CharSequence;)V

    .line 758
    iget-object v0, p0, Lcom/google/appinventor/components/runtime/FusiontablesControl$QueryProcessorV1;->dialog:Landroid/app/ProgressDialog;

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->show()V

    .line 759
    return-void
.end method
