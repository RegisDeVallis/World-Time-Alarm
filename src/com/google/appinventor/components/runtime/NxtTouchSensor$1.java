// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.os.Handler;

// Referenced classes of package com.google.appinventor.components.runtime:
//            NxtTouchSensor, BluetoothClient

class this._cls0
    implements Runnable
{

    final NxtTouchSensor this$0;

    public void run()
    {
        if (bluetooth != null && bluetooth.IsConnected())
        {
            Sensor.SensorValue sensorvalue = NxtTouchSensor.access$000(NxtTouchSensor.this, "");
            if (sensorvalue.valid)
            {
                ate ate;
                if (((Boolean)sensorvalue.value).booleanValue())
                {
                    ate = ate.PRESSED;
                } else
                {
                    ate = ate.RELEASED;
                }
                if (ate != NxtTouchSensor.access$100(NxtTouchSensor.this))
                {
                    if (ate == ate.PRESSED && NxtTouchSensor.access$200(NxtTouchSensor.this))
                    {
                        Pressed();
                    }
                    if (ate == ate.RELEASED && NxtTouchSensor.access$300(NxtTouchSensor.this))
                    {
                        Released();
                    }
                }
                NxtTouchSensor.access$102(NxtTouchSensor.this, ate);
            }
        }
        if (NxtTouchSensor.access$400(NxtTouchSensor.this))
        {
            NxtTouchSensor.access$600(NxtTouchSensor.this).post(NxtTouchSensor.access$500(NxtTouchSensor.this));
        }
    }

    ate()
    {
        this$0 = NxtTouchSensor.this;
        super();
    }
}
