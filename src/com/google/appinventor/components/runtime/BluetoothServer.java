// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.os.Handler;
import android.util.Log;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.BluetoothReflection;
import com.google.appinventor.components.runtime.util.SdkLevel;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;

// Referenced classes of package com.google.appinventor.components.runtime:
//            BluetoothConnectionBase, Form, EventDispatcher, ComponentContainer

public final class BluetoothServer extends BluetoothConnectionBase
{

    private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    private final Handler androidUIHandler = new Handler();
    private final AtomicReference arBluetoothServerSocket = new AtomicReference();

    public BluetoothServer(ComponentContainer componentcontainer)
    {
        super(componentcontainer, "BluetoothServer");
    }

    private void accept(final String functionName, String s, String s1)
    {
        Object obj;
        UUID uuid;
        Object obj2;
        obj = BluetoothReflection.getBluetoothAdapter();
        if (obj == null)
        {
            form.dispatchErrorOccurredEvent(this, functionName, 501, new Object[0]);
            return;
        }
        if (!BluetoothReflection.isBluetoothEnabled(obj))
        {
            form.dispatchErrorOccurredEvent(this, functionName, 502, new Object[0]);
            return;
        }
        try
        {
            uuid = UUID.fromString(s1);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            form.dispatchErrorOccurredEvent(this, functionName, 506, new Object[] {
                s1
            });
            return;
        }
        if (secure || SdkLevel.getLevel() < 10)
        {
            break MISSING_BLOCK_LABEL_128;
        }
        obj2 = BluetoothReflection.listenUsingInsecureRfcommWithServiceRecord(obj, s, uuid);
_L1:
        arBluetoothServerSocket.set(obj2);
        AsynchUtil.runAsynchronously(new Runnable() {

            final BluetoothServer this$0;
            final String val$functionName;

            public void run()
            {
                Object obj3;
                Object obj4;
                obj3 = arBluetoothServerSocket.get();
                obj4 = null;
                if (obj3 == null)
                {
                    break MISSING_BLOCK_LABEL_33;
                }
                Object obj5 = BluetoothReflection.accept(obj3);
                obj4 = obj5;
                StopAccepting();
                if (obj4 != null)
                {
                    androidUIHandler.post(((_cls2) (obj4)). new Runnable() {

                        final _cls1 this$1;
                        final Object val$bluetoothSocket;

                        public void run()
                        {
                            try
                            {
                                setConnection(bluetoothSocket);
                            }
                            catch (IOException ioexception)
                            {
                                Disconnect();
                                form.dispatchErrorOccurredEvent(_fld0, functionName, 509, new Object[0]);
                                return;
                            }
                            ConnectionAccepted();
                        }

            
            {
                this$1 = final__pcls1;
                bluetoothSocket = Object.this;
                super();
            }
                    });
                }
                return;
                IOException ioexception1;
                ioexception1;
                androidUIHandler.post(new Runnable() {

                    final _cls1 this$1;

                    public void run()
                    {
                        form.dispatchErrorOccurredEvent(_fld0, functionName, 509, new Object[0]);
                    }

            
            {
                this$1 = _cls1.this;
                super();
            }
                });
                StopAccepting();
                return;
                Exception exception;
                exception;
                StopAccepting();
                throw exception;
            }

            
            {
                this$0 = BluetoothServer.this;
                functionName = s;
                super();
            }
        });
        return;
        Object obj1;
        try
        {
            obj1 = BluetoothReflection.listenUsingRfcommWithServiceRecord(obj, s, uuid);
        }
        catch (IOException ioexception)
        {
            form.dispatchErrorOccurredEvent(this, functionName, 508, new Object[0]);
            return;
        }
        obj2 = obj1;
          goto _L1
    }

    public void AcceptConnection(String s)
    {
        accept("AcceptConnection", s, "00001101-0000-1000-8000-00805F9B34FB");
    }

    public void AcceptConnectionWithUUID(String s, String s1)
    {
        accept("AcceptConnectionWithUUID", s, s1);
    }

    public void ConnectionAccepted()
    {
        Log.i(logTag, "Successfullly accepted bluetooth connection.");
        EventDispatcher.dispatchEvent(this, "ConnectionAccepted", new Object[0]);
    }

    public final boolean IsAccepting()
    {
        return arBluetoothServerSocket.get() != null;
    }

    public void StopAccepting()
    {
        Object obj;
        obj = arBluetoothServerSocket.getAndSet(null);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_17;
        }
        BluetoothReflection.closeBluetoothServerSocket(obj);
        return;
        IOException ioexception;
        ioexception;
        Log.w(logTag, (new StringBuilder()).append("Error while closing bluetooth server socket: ").append(ioexception.getMessage()).toString());
        return;
    }


}
