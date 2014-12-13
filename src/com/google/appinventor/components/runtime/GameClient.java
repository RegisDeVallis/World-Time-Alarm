// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.util.AsyncCallbackPair;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.GameInstance;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.PlayerListDelta;
import com.google.appinventor.components.runtime.util.WebServiceUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.appinventor.components.runtime:
//            AndroidNonvisibleComponent, Component, OnResumeListener, OnStopListener, 
//            ComponentContainer, Form, EventDispatcher

public class GameClient extends AndroidNonvisibleComponent
    implements Component, OnResumeListener, OnStopListener
{

    private static final String COMMAND_ARGUMENTS_KEY = "args";
    private static final String COMMAND_TYPE_KEY = "command";
    private static final String COUNT_KEY = "count";
    private static final String ERROR_RESPONSE_KEY = "e";
    private static final String GAME_ID_KEY = "gid";
    private static final String GET_INSTANCE_LISTS_COMMAND = "getinstancelists";
    private static final String GET_MESSAGES_COMMAND = "messages";
    private static final String INSTANCE_ID_KEY = "iid";
    private static final String INSTANCE_PUBLIC_KEY = "makepublic";
    private static final String INVITED_LIST_KEY = "invited";
    private static final String INVITEE_KEY = "inv";
    private static final String INVITE_COMMAND = "invite";
    private static final String JOINED_LIST_KEY = "joined";
    private static final String JOIN_INSTANCE_COMMAND = "joininstance";
    private static final String LEADER_KEY = "leader";
    private static final String LEAVE_INSTANCE_COMMAND = "leaveinstance";
    private static final String LOG_TAG = "GameClient";
    private static final String MESSAGES_LIST_KEY = "messages";
    private static final String MESSAGE_CONTENT_KEY = "contents";
    private static final String MESSAGE_RECIPIENTS_KEY = "mrec";
    private static final String MESSAGE_SENDER_KEY = "msender";
    private static final String MESSAGE_TIME_KEY = "mtime";
    private static final String NEW_INSTANCE_COMMAND = "newinstance";
    private static final String NEW_MESSAGE_COMMAND = "newmessage";
    private static final String PLAYERS_LIST_KEY = "players";
    private static final String PLAYER_ID_KEY = "pid";
    private static final String PUBLIC_LIST_KEY = "public";
    private static final String SERVER_COMMAND = "servercommand";
    private static final String SERVER_RETURN_VALUE_KEY = "response";
    private static final String SET_LEADER_COMMAND = "setleader";
    private static final String TYPE_KEY = "type";
    private Activity activityContext;
    private Handler androidUIHandler;
    private String gameId;
    private GameInstance instance;
    private List invitedInstances;
    private List joinedInstances;
    private List publicInstances;
    private String serviceUrl;
    private String userEmailAddress;

    public GameClient(ComponentContainer componentcontainer)
    {
        super(componentcontainer.$form());
        userEmailAddress = "";
        androidUIHandler = new Handler();
        activityContext = componentcontainer.$context();
        form.registerForOnResume(this);
        form.registerForOnStop(this);
        gameId = "";
        instance = new GameInstance("");
        joinedInstances = Lists.newArrayList();
        invitedInstances = Lists.newArrayList();
        publicInstances = Lists.newArrayList();
        serviceUrl = "http://appinvgameserver.appspot.com";
    }

    private void postCommandToGameServer(String s, List list, AsyncCallbackPair asynccallbackpair)
    {
        postCommandToGameServer(s, list, asynccallbackpair, false);
    }

    private void postCommandToGameServer(final String commandName, final List params, final AsyncCallbackPair callback, final boolean allowInstanceIdChange)
    {
        AsyncCallbackPair asynccallbackpair = new AsyncCallbackPair() {

            final GameClient this$0;
            final boolean val$allowInstanceIdChange;
            final AsyncCallbackPair val$callback;
            final String val$commandName;
            final List val$params;

            public void onFailure(String s)
            {
                Log.d("GameClient", (new StringBuilder()).append("Posting to server failed for ").append(commandName).append(" with arguments ").append(params).append("\n Failure message: ").append(s).toString());
                callback.onFailure(s);
            }

            public volatile void onSuccess(Object obj)
            {
                onSuccess((JSONObject)obj);
            }

            public void onSuccess(JSONObject jsonobject)
            {
                Log.d("GameClient", (new StringBuilder()).append("Received response for ").append(commandName).append(": ").append(jsonobject.toString()).toString());
                String s;
                try
                {
                    if (jsonobject.getBoolean("e"))
                    {
                        callback.onFailure(jsonobject.getString("response"));
                        return;
                    }
                }
                catch (JSONException jsonexception)
                {
                    Log.w("GameClient", jsonexception);
                    callback.onFailure((new StringBuilder()).append("Failed to parse JSON response to command ").append(commandName).toString());
                    return;
                }
                s = jsonobject.getString("gid");
                if (!s.equals(GameId()))
                {
                    Info((new StringBuilder()).append("Incorrect game id in response: + ").append(s).append(".").toString());
                    return;
                }
                String s1;
                s1 = jsonobject.getString("iid");
                if (s1.equals(""))
                {
                    callback.onSuccess(jsonobject.getJSONObject("response"));
                    return;
                }
                if (!s1.equals(InstanceId()))
                {
                    break MISSING_BLOCK_LABEL_234;
                }
                updateInstanceInfo(jsonobject);
_L2:
                callback.onSuccess(jsonobject.getJSONObject("response"));
                return;
label0:
                {
                    if (!allowInstanceIdChange && !InstanceId().equals(""))
                    {
                        break label0;
                    }
                    instance = new GameInstance(s1);
                    updateInstanceInfo(jsonobject);
                    InstanceIdChanged(s1);
                }
                if (true) goto _L2; else goto _L1
_L1:
                Info((new StringBuilder()).append("Ignored server response to ").append(commandName).append(" for incorrect instance ").append(s1).append(".").toString());
                return;
            }

            
            {
                this$0 = GameClient.this;
                commandName = s;
                callback = asynccallbackpair;
                allowInstanceIdChange = flag;
                params = list;
                super();
            }
        };
        WebServiceUtil.getInstance().postCommandReturningObject(ServiceUrl(), commandName, params, asynccallbackpair);
    }

    private void postGetInstanceLists()
    {
        AsyncCallbackPair asynccallbackpair = new AsyncCallbackPair() {

            final GameClient this$0;

            public void onFailure(String s)
            {
                WebServiceError("GetInstanceLists", "Failed to get up to date instance lists.");
            }

            public volatile void onSuccess(Object obj)
            {
                onSuccess((JSONObject)obj);
            }

            public void onSuccess(JSONObject jsonobject)
            {
                processInstanceLists(jsonobject);
                FunctionCompleted("GetInstanceLists");
            }

            
            {
                this$0 = GameClient.this;
                super();
            }
        };
        NameValuePair anamevaluepair[] = new NameValuePair[3];
        anamevaluepair[0] = new BasicNameValuePair("gid", GameId());
        anamevaluepair[1] = new BasicNameValuePair("iid", InstanceId());
        anamevaluepair[2] = new BasicNameValuePair("pid", UserEmailAddress());
        postCommandToGameServer("getinstancelists", Lists.newArrayList(anamevaluepair), asynccallbackpair);
    }

    private void postGetMessages(final String requestedType, int i)
    {
        AsyncCallbackPair asynccallbackpair = new AsyncCallbackPair() {

            final GameClient this$0;
            final String val$requestedType;

            public void onFailure(String s)
            {
                WebServiceError("GetMessages", s);
            }

            public volatile void onSuccess(Object obj)
            {
                onSuccess((JSONObject)obj);
            }

            public void onSuccess(JSONObject jsonobject)
            {
                int j;
                JSONArray jsonarray;
                j = jsonobject.getInt("count");
                jsonarray = jsonobject.getJSONArray("messages");
                int k = 0;
_L2:
                if (k >= j)
                {
                    break; /* Loop/switch isn't completed */
                }
                JSONObject jsonobject1 = jsonarray.getJSONObject(k);
                String s = jsonobject1.getString("type");
                String s1 = jsonobject1.getString("msender");
                String s2 = jsonobject1.getString("mtime");
                List list = JsonUtil.getListFromJsonArray(jsonobject1.getJSONArray("contents"));
                if (requestedType.equals(""))
                {
                    instance.putMessageTime(requestedType, s2);
                }
                instance.putMessageTime(s, s2);
                GotMessage(s, s1, list);
                k++;
                if (true) goto _L2; else goto _L1
                JSONException jsonexception;
                jsonexception;
                Log.w("GameClient", jsonexception);
                Info("Failed to parse messages response.");
_L1:
                FunctionCompleted("GetMessages");
                return;
            }

            
            {
                this$0 = GameClient.this;
                requestedType = s;
                super();
            }
        };
        if (InstanceId().equals(""))
        {
            Info("You must join an instance before attempting to fetch messages.");
            return;
        } else
        {
            NameValuePair anamevaluepair[] = new NameValuePair[6];
            anamevaluepair[0] = new BasicNameValuePair("gid", GameId());
            anamevaluepair[1] = new BasicNameValuePair("iid", InstanceId());
            anamevaluepair[2] = new BasicNameValuePair("pid", UserEmailAddress());
            anamevaluepair[3] = new BasicNameValuePair("count", Integer.toString(i));
            anamevaluepair[4] = new BasicNameValuePair("mtime", instance.getMessageTime(requestedType));
            anamevaluepair[5] = new BasicNameValuePair("type", requestedType);
            postCommandToGameServer("messages", Lists.newArrayList(anamevaluepair), asynccallbackpair);
            return;
        }
    }

    private void postInvite(String s)
    {
        AsyncCallbackPair asynccallbackpair = new AsyncCallbackPair() {

            final GameClient this$0;

            public void onFailure(String s1)
            {
                WebServiceError("Invite", s1);
            }

            public volatile void onSuccess(Object obj)
            {
                onSuccess((JSONObject)obj);
            }

            public void onSuccess(JSONObject jsonobject)
            {
                String s1 = jsonobject.getString("inv");
                if (!s1.equals("")) goto _L2; else goto _L1
_L1:
                Info((new StringBuilder()).append(s1).append(" was already invited.").toString());
_L4:
                FunctionCompleted("Invite");
                return;
_L2:
                try
                {
                    Info((new StringBuilder()).append("Successfully invited ").append(s1).append(".").toString());
                }
                catch (JSONException jsonexception)
                {
                    Log.w("GameClient", jsonexception);
                    Info("Failed to parse invite player response.");
                }
                if (true) goto _L4; else goto _L3
_L3:
            }

            
            {
                this$0 = GameClient.this;
                super();
            }
        };
        if (InstanceId().equals(""))
        {
            Info("You must have joined an instance before you can invite new players.");
            return;
        } else
        {
            NameValuePair anamevaluepair[] = new NameValuePair[4];
            anamevaluepair[0] = new BasicNameValuePair("gid", GameId());
            anamevaluepair[1] = new BasicNameValuePair("iid", InstanceId());
            anamevaluepair[2] = new BasicNameValuePair("pid", UserEmailAddress());
            anamevaluepair[3] = new BasicNameValuePair("inv", s);
            postCommandToGameServer("invite", Lists.newArrayList(anamevaluepair), asynccallbackpair);
            return;
        }
    }

    private void postLeaveInstance()
    {
        AsyncCallbackPair asynccallbackpair = new AsyncCallbackPair() {

            final GameClient this$0;

            public void onFailure(String s)
            {
                WebServiceError("LeaveInstance", s);
            }

            public volatile void onSuccess(Object obj)
            {
                onSuccess((JSONObject)obj);
            }

            public void onSuccess(JSONObject jsonobject)
            {
                SetInstance("");
                processInstanceLists(jsonobject);
                FunctionCompleted("LeaveInstance");
            }

            
            {
                this$0 = GameClient.this;
                super();
            }
        };
        NameValuePair anamevaluepair[] = new NameValuePair[3];
        anamevaluepair[0] = new BasicNameValuePair("gid", GameId());
        anamevaluepair[1] = new BasicNameValuePair("iid", InstanceId());
        anamevaluepair[2] = new BasicNameValuePair("pid", UserEmailAddress());
        postCommandToGameServer("leaveinstance", Lists.newArrayList(anamevaluepair), asynccallbackpair);
    }

    private void postMakeNewInstance(String s, Boolean boolean1)
    {
        AsyncCallbackPair asynccallbackpair = new AsyncCallbackPair() {

            final GameClient this$0;

            public void onFailure(String s1)
            {
                WebServiceError("MakeNewInstance", s1);
            }

            public volatile void onSuccess(Object obj)
            {
                onSuccess((JSONObject)obj);
            }

            public void onSuccess(JSONObject jsonobject)
            {
                processInstanceLists(jsonobject);
                NewInstanceMade(InstanceId());
                FunctionCompleted("MakeNewInstance");
            }

            
            {
                this$0 = GameClient.this;
                super();
            }
        };
        NameValuePair anamevaluepair[] = new NameValuePair[4];
        anamevaluepair[0] = new BasicNameValuePair("pid", UserEmailAddress());
        anamevaluepair[1] = new BasicNameValuePair("gid", GameId());
        anamevaluepair[2] = new BasicNameValuePair("iid", s);
        anamevaluepair[3] = new BasicNameValuePair("makepublic", boolean1.toString());
        postCommandToGameServer("newinstance", Lists.newArrayList(anamevaluepair), asynccallbackpair, true);
    }

    private void postNewMessage(String s, YailList yaillist, YailList yaillist1)
    {
        AsyncCallbackPair asynccallbackpair = new AsyncCallbackPair() {

            final GameClient this$0;

            public void onFailure(String s1)
            {
                WebServiceError("SendMessage", s1);
            }

            public volatile void onSuccess(Object obj)
            {
                onSuccess((JSONObject)obj);
            }

            public void onSuccess(JSONObject jsonobject)
            {
                FunctionCompleted("SendMessage");
            }

            
            {
                this$0 = GameClient.this;
                super();
            }
        };
        if (InstanceId().equals(""))
        {
            Info("You must have joined an instance before you can send messages.");
            return;
        } else
        {
            NameValuePair anamevaluepair[] = new NameValuePair[7];
            anamevaluepair[0] = new BasicNameValuePair("gid", GameId());
            anamevaluepair[1] = new BasicNameValuePair("iid", InstanceId());
            anamevaluepair[2] = new BasicNameValuePair("pid", UserEmailAddress());
            anamevaluepair[3] = new BasicNameValuePair("type", s);
            anamevaluepair[4] = new BasicNameValuePair("mrec", yaillist.toJSONString());
            anamevaluepair[5] = new BasicNameValuePair("contents", yaillist1.toJSONString());
            anamevaluepair[6] = new BasicNameValuePair("mtime", instance.getMessageTime(s));
            postCommandToGameServer("newmessage", Lists.newArrayList(anamevaluepair), asynccallbackpair);
            return;
        }
    }

    private void postServerCommand(final String command, final YailList arguments)
    {
        AsyncCallbackPair asynccallbackpair = new AsyncCallbackPair() {

            final GameClient this$0;
            final YailList val$arguments;
            final String val$command;

            public void onFailure(String s)
            {
                ServerCommandFailure(command, arguments);
                WebServiceError("ServerCommand", s);
            }

            public volatile void onSuccess(Object obj)
            {
                onSuccess((JSONObject)obj);
            }

            public void onSuccess(JSONObject jsonobject)
            {
                try
                {
                    ServerCommandSuccess(command, JsonUtil.getListFromJsonArray(jsonobject.getJSONArray("contents")));
                }
                catch (JSONException jsonexception)
                {
                    Log.w("GameClient", jsonexception);
                    Info("Server command response failed to parse.");
                }
                FunctionCompleted("ServerCommand");
            }

            
            {
                this$0 = GameClient.this;
                command = s;
                arguments = yaillist;
                super();
            }
        };
        Log.d("GameClient", (new StringBuilder()).append("Going to post ").append(command).append(" with args ").append(arguments).toString());
        NameValuePair anamevaluepair[] = new NameValuePair[5];
        anamevaluepair[0] = new BasicNameValuePair("gid", GameId());
        anamevaluepair[1] = new BasicNameValuePair("iid", InstanceId());
        anamevaluepair[2] = new BasicNameValuePair("pid", UserEmailAddress());
        anamevaluepair[3] = new BasicNameValuePair("command", command);
        anamevaluepair[4] = new BasicNameValuePair("args", arguments.toJSONString());
        postCommandToGameServer("servercommand", Lists.newArrayList(anamevaluepair), asynccallbackpair);
    }

    private void postSetInstance(String s)
    {
        AsyncCallbackPair asynccallbackpair = new AsyncCallbackPair() {

            final GameClient this$0;

            public void onFailure(String s1)
            {
                WebServiceError("SetInstance", s1);
            }

            public volatile void onSuccess(Object obj)
            {
                onSuccess((JSONObject)obj);
            }

            public void onSuccess(JSONObject jsonobject)
            {
                processInstanceLists(jsonobject);
                FunctionCompleted("SetInstance");
            }

            
            {
                this$0 = GameClient.this;
                super();
            }
        };
        NameValuePair anamevaluepair[] = new NameValuePair[3];
        anamevaluepair[0] = new BasicNameValuePair("gid", GameId());
        anamevaluepair[1] = new BasicNameValuePair("iid", s);
        anamevaluepair[2] = new BasicNameValuePair("pid", UserEmailAddress());
        postCommandToGameServer("joininstance", Lists.newArrayList(anamevaluepair), asynccallbackpair, true);
    }

    private void postSetLeader(String s)
    {
        AsyncCallbackPair asynccallbackpair = new AsyncCallbackPair() {

            final GameClient this$0;

            public void onFailure(String s1)
            {
                WebServiceError("SetLeader", s1);
            }

            public volatile void onSuccess(Object obj)
            {
                onSuccess((JSONObject)obj);
            }

            public void onSuccess(JSONObject jsonobject)
            {
                FunctionCompleted("SetLeader");
            }

            
            {
                this$0 = GameClient.this;
                super();
            }
        };
        if (InstanceId().equals(""))
        {
            Info("You must join an instance before attempting to set a leader.");
            return;
        } else
        {
            NameValuePair anamevaluepair[] = new NameValuePair[4];
            anamevaluepair[0] = new BasicNameValuePair("gid", GameId());
            anamevaluepair[1] = new BasicNameValuePair("iid", InstanceId());
            anamevaluepair[2] = new BasicNameValuePair("pid", UserEmailAddress());
            anamevaluepair[3] = new BasicNameValuePair("leader", s);
            postCommandToGameServer("setleader", Lists.newArrayList(anamevaluepair), asynccallbackpair);
            return;
        }
    }

    private void processInstanceLists(JSONObject jsonobject)
    {
        try
        {
            joinedInstances = JsonUtil.getStringListFromJsonArray(jsonobject.getJSONArray("joined"));
            publicInstances = JsonUtil.getStringListFromJsonArray(jsonobject.getJSONArray("public"));
            List list = JsonUtil.getStringListFromJsonArray(jsonobject.getJSONArray("invited"));
            if (!list.equals(InvitedInstances()))
            {
                List list1 = invitedInstances;
                invitedInstances = list;
                ArrayList arraylist = new ArrayList(list);
                arraylist.removeAll(list1);
                for (Iterator iterator = arraylist.iterator(); iterator.hasNext(); Invited((String)iterator.next())) { }
            }
        }
        catch (JSONException jsonexception)
        {
            Log.w("GameClient", jsonexception);
            Info("Instance lists failed to parse.");
        }
    }

    private void updateInstanceInfo(JSONObject jsonobject)
        throws JSONException
    {
        String s = jsonobject.getString("leader");
        List list = JsonUtil.getStringListFromJsonArray(jsonobject.getJSONArray("players"));
        boolean flag = Leader().equals(s);
        boolean flag1 = false;
        if (!flag)
        {
            instance.setLeader(s);
            flag1 = true;
        }
        PlayerListDelta playerlistdelta = instance.setPlayers(list);
        if (playerlistdelta != PlayerListDelta.NO_CHANGE)
        {
            for (Iterator iterator = playerlistdelta.getPlayersRemoved().iterator(); iterator.hasNext(); PlayerLeft((String)iterator.next())) { }
            for (Iterator iterator1 = playerlistdelta.getPlayersAdded().iterator(); iterator1.hasNext(); PlayerJoined((String)iterator1.next())) { }
        }
        if (flag1)
        {
            NewLeader(Leader());
        }
    }

    public void FunctionCompleted(final String functionName)
    {
        androidUIHandler.post(new Runnable() {

            final GameClient this$0;
            final String val$functionName;

            public void run()
            {
                Log.d("GameClient", (new StringBuilder()).append("Request completed: ").append(functionName).toString());
                GameClient gameclient = GameClient.this;
                Object aobj[] = new Object[1];
                aobj[0] = functionName;
                EventDispatcher.dispatchEvent(gameclient, "FunctionCompleted", aobj);
            }

            
            {
                this$0 = GameClient.this;
                functionName = s;
                super();
            }
        });
    }

    public String GameId()
    {
        return gameId;
    }

    public void GameId(String s)
    {
        gameId = s;
    }

    public void GetInstanceLists()
    {
        AsynchUtil.runAsynchronously(new Runnable() {

            final GameClient this$0;

            public void run()
            {
                postGetInstanceLists();
            }

            
            {
                this$0 = GameClient.this;
                super();
            }
        });
    }

    public void GetMessages(final String type, final int count)
    {
        AsynchUtil.runAsynchronously(new Runnable() {

            final GameClient this$0;
            final int val$count;
            final String val$type;

            public void run()
            {
                postGetMessages(type, count);
            }

            
            {
                this$0 = GameClient.this;
                type = s;
                count = i;
                super();
            }
        });
    }

    public void GotMessage(final String type, final String sender, final List contents)
    {
        Log.d("GameClient", (new StringBuilder()).append("Got message of type ").append(type).toString());
        androidUIHandler.post(new Runnable() {

            final GameClient this$0;
            final List val$contents;
            final String val$sender;
            final String val$type;

            public void run()
            {
                GameClient gameclient = GameClient.this;
                Object aobj[] = new Object[3];
                aobj[0] = type;
                aobj[1] = sender;
                aobj[2] = contents;
                EventDispatcher.dispatchEvent(gameclient, "GotMessage", aobj);
            }

            
            {
                this$0 = GameClient.this;
                type = s;
                sender = s1;
                contents = list;
                super();
            }
        });
    }

    public void Info(final String message)
    {
        Log.d("GameClient", (new StringBuilder()).append("Info: ").append(message).toString());
        androidUIHandler.post(new Runnable() {

            final GameClient this$0;
            final String val$message;

            public void run()
            {
                GameClient gameclient = GameClient.this;
                Object aobj[] = new Object[1];
                aobj[0] = message;
                EventDispatcher.dispatchEvent(gameclient, "Info", aobj);
            }

            
            {
                this$0 = GameClient.this;
                message = s;
                super();
            }
        });
    }

    public void Initialize()
    {
        Log.d("GameClient", "Initialize");
        if (gameId.equals(""))
        {
            throw new YailRuntimeError("Game Id must not be empty.", "GameClient Configuration Error.");
        } else
        {
            return;
        }
    }

    public String InstanceId()
    {
        return instance.getInstanceId();
    }

    public void InstanceIdChanged(final String instanceId)
    {
        Log.d("GameClient", (new StringBuilder()).append("Instance id changed to ").append(instanceId).toString());
        androidUIHandler.post(new Runnable() {

            final GameClient this$0;
            final String val$instanceId;

            public void run()
            {
                GameClient gameclient = GameClient.this;
                Object aobj[] = new Object[1];
                aobj[0] = instanceId;
                EventDispatcher.dispatchEvent(gameclient, "InstanceIdChanged", aobj);
            }

            
            {
                this$0 = GameClient.this;
                instanceId = s;
                super();
            }
        });
    }

    public void Invite(final String playerEmail)
    {
        AsynchUtil.runAsynchronously(new Runnable() {

            final GameClient this$0;
            final String val$playerEmail;

            public void run()
            {
                postInvite(playerEmail);
            }

            
            {
                this$0 = GameClient.this;
                playerEmail = s;
                super();
            }
        });
    }

    public void Invited(final String instanceId)
    {
        Log.d("GameClient", (new StringBuilder()).append("Player invited to ").append(instanceId).toString());
        androidUIHandler.post(new Runnable() {

            final GameClient this$0;
            final String val$instanceId;

            public void run()
            {
                GameClient gameclient = GameClient.this;
                Object aobj[] = new Object[1];
                aobj[0] = instanceId;
                EventDispatcher.dispatchEvent(gameclient, "Invited", aobj);
            }

            
            {
                this$0 = GameClient.this;
                instanceId = s;
                super();
            }
        });
    }

    public List InvitedInstances()
    {
        return invitedInstances;
    }

    public List JoinedInstances()
    {
        return joinedInstances;
    }

    public String Leader()
    {
        return instance.getLeader();
    }

    public void LeaveInstance()
    {
        AsynchUtil.runAsynchronously(new Runnable() {

            final GameClient this$0;

            public void run()
            {
                postLeaveInstance();
            }

            
            {
                this$0 = GameClient.this;
                super();
            }
        });
    }

    public void MakeNewInstance(final String instanceId, final boolean makePublic)
    {
        AsynchUtil.runAsynchronously(new Runnable() {

            final GameClient this$0;
            final String val$instanceId;
            final boolean val$makePublic;

            public void run()
            {
                postMakeNewInstance(instanceId, Boolean.valueOf(makePublic));
            }

            
            {
                this$0 = GameClient.this;
                instanceId = s;
                makePublic = flag;
                super();
            }
        });
    }

    public void NewInstanceMade(final String instanceId)
    {
        androidUIHandler.post(new Runnable() {

            final GameClient this$0;
            final String val$instanceId;

            public void run()
            {
                Log.d("GameClient", (new StringBuilder()).append("New instance made: ").append(instanceId).toString());
                GameClient gameclient = GameClient.this;
                Object aobj[] = new Object[1];
                aobj[0] = instanceId;
                EventDispatcher.dispatchEvent(gameclient, "NewInstanceMade", aobj);
            }

            
            {
                this$0 = GameClient.this;
                instanceId = s;
                super();
            }
        });
    }

    public void NewLeader(final String playerId)
    {
        androidUIHandler.post(new Runnable() {

            final GameClient this$0;
            final String val$playerId;

            public void run()
            {
                Log.d("GameClient", (new StringBuilder()).append("Leader change to ").append(playerId).toString());
                GameClient gameclient = GameClient.this;
                Object aobj[] = new Object[1];
                aobj[0] = playerId;
                EventDispatcher.dispatchEvent(gameclient, "NewLeader", aobj);
            }

            
            {
                this$0 = GameClient.this;
                playerId = s;
                super();
            }
        });
    }

    public void PlayerJoined(final String playerId)
    {
        androidUIHandler.post(new Runnable() {

            final GameClient this$0;
            final String val$playerId;

            public void run()
            {
                if (!playerId.equals(UserEmailAddress()))
                {
                    Log.d("GameClient", (new StringBuilder()).append("Player joined: ").append(playerId).toString());
                    GameClient gameclient = GameClient.this;
                    Object aobj[] = new Object[1];
                    aobj[0] = playerId;
                    EventDispatcher.dispatchEvent(gameclient, "PlayerJoined", aobj);
                }
            }

            
            {
                this$0 = GameClient.this;
                playerId = s;
                super();
            }
        });
    }

    public void PlayerLeft(final String playerId)
    {
        androidUIHandler.post(new Runnable() {

            final GameClient this$0;
            final String val$playerId;

            public void run()
            {
                Log.d("GameClient", (new StringBuilder()).append("Player left: ").append(playerId).toString());
                GameClient gameclient = GameClient.this;
                Object aobj[] = new Object[1];
                aobj[0] = playerId;
                EventDispatcher.dispatchEvent(gameclient, "PlayerLeft", aobj);
            }

            
            {
                this$0 = GameClient.this;
                playerId = s;
                super();
            }
        });
    }

    public List Players()
    {
        return instance.getPlayers();
    }

    public List PublicInstances()
    {
        return publicInstances;
    }

    public void SendMessage(final String type, final YailList recipients, final YailList contents)
    {
        AsynchUtil.runAsynchronously(new Runnable() {

            final GameClient this$0;
            final YailList val$contents;
            final YailList val$recipients;
            final String val$type;

            public void run()
            {
                postNewMessage(type, recipients, contents);
            }

            
            {
                this$0 = GameClient.this;
                type = s;
                recipients = yaillist;
                contents = yaillist1;
                super();
            }
        });
    }

    public void ServerCommand(final String command, final YailList arguments)
    {
        AsynchUtil.runAsynchronously(new Runnable() {

            final GameClient this$0;
            final YailList val$arguments;
            final String val$command;

            public void run()
            {
                postServerCommand(command, arguments);
            }

            
            {
                this$0 = GameClient.this;
                command = s;
                arguments = yaillist;
                super();
            }
        });
    }

    public void ServerCommandFailure(final String command, final YailList arguments)
    {
        androidUIHandler.post(new Runnable() {

            final GameClient this$0;
            final YailList val$arguments;
            final String val$command;

            public void run()
            {
                Log.d("GameClient", (new StringBuilder()).append("Server command failed: ").append(command).toString());
                GameClient gameclient = GameClient.this;
                Object aobj[] = new Object[2];
                aobj[0] = command;
                aobj[1] = arguments;
                EventDispatcher.dispatchEvent(gameclient, "ServerCommandFailure", aobj);
            }

            
            {
                this$0 = GameClient.this;
                command = s;
                arguments = yaillist;
                super();
            }
        });
    }

    public void ServerCommandSuccess(final String command, final List response)
    {
        Log.d("GameClient", (new StringBuilder()).append(command).append(" server command returned.").toString());
        androidUIHandler.post(new Runnable() {

            final GameClient this$0;
            final String val$command;
            final List val$response;

            public void run()
            {
                GameClient gameclient = GameClient.this;
                Object aobj[] = new Object[2];
                aobj[0] = command;
                aobj[1] = response;
                EventDispatcher.dispatchEvent(gameclient, "ServerCommandSuccess", aobj);
            }

            
            {
                this$0 = GameClient.this;
                command = s;
                response = list;
                super();
            }
        });
    }

    public void ServiceURL(String s)
    {
        if (s.endsWith("/"))
        {
            serviceUrl = s.substring(0, -1 + s.length());
            return;
        } else
        {
            serviceUrl = s;
            return;
        }
    }

    public String ServiceUrl()
    {
        return serviceUrl;
    }

    public void SetInstance(final String instanceId)
    {
        AsynchUtil.runAsynchronously(new Runnable() {

            final GameClient this$0;
            final String val$instanceId;

            public void run()
            {
                if (instanceId.equals(""))
                {
                    Log.d("GameClient", "Instance id set to empty string.");
                    if (!InstanceId().equals(""))
                    {
                        instance = new GameInstance("");
                        InstanceIdChanged("");
                        FunctionCompleted("SetInstance");
                    }
                    return;
                } else
                {
                    postSetInstance(instanceId);
                    return;
                }
            }

            
            {
                this$0 = GameClient.this;
                instanceId = s;
                super();
            }
        });
    }

    public void SetLeader(final String playerEmail)
    {
        AsynchUtil.runAsynchronously(new Runnable() {

            final GameClient this$0;
            final String val$playerEmail;

            public void run()
            {
                postSetLeader(playerEmail);
            }

            
            {
                this$0 = GameClient.this;
                playerEmail = s;
                super();
            }
        });
    }

    public String UserEmailAddress()
    {
        if (userEmailAddress.equals(""))
        {
            Info("User email address is empty.");
        }
        return userEmailAddress;
    }

    public void UserEmailAddress(String s)
    {
        userEmailAddress = s;
        UserEmailAddressSet(s);
    }

    public void UserEmailAddressSet(final String emailAddress)
    {
        Log.d("GameClient", "Email address set.");
        androidUIHandler.post(new Runnable() {

            final GameClient this$0;
            final String val$emailAddress;

            public void run()
            {
                GameClient gameclient = GameClient.this;
                Object aobj[] = new Object[1];
                aobj[0] = emailAddress;
                EventDispatcher.dispatchEvent(gameclient, "UserEmailAddressSet", aobj);
            }

            
            {
                this$0 = GameClient.this;
                emailAddress = s;
                super();
            }
        });
    }

    public void WebServiceError(final String functionName, final String message)
    {
        Log.e("GameClient", (new StringBuilder()).append("WebServiceError: ").append(message).toString());
        androidUIHandler.post(new Runnable() {

            final GameClient this$0;
            final String val$functionName;
            final String val$message;

            public void run()
            {
                GameClient gameclient = GameClient.this;
                Object aobj[] = new Object[2];
                aobj[0] = functionName;
                aobj[1] = message;
                EventDispatcher.dispatchEvent(gameclient, "WebServiceError", aobj);
            }

            
            {
                this$0 = GameClient.this;
                functionName = s;
                message = s1;
                super();
            }
        });
    }

    public void onResume()
    {
        Log.d("GameClient", "Activity Resumed.");
    }

    public void onStop()
    {
        Log.d("GameClient", "Activity Stopped.");
    }








/*
    static GameInstance access$302(GameClient gameclient, GameInstance gameinstance)
    {
        gameclient.instance = gameinstance;
        return gameinstance;
    }

*/






}
