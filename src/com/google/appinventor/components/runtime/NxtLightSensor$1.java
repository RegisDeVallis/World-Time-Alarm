// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.os.Handler;

// Referenced classes of package com.google.appinventor.components.runtime:
//            NxtLightSensor, BluetoothClient

class this._cls0
    implements Runnable
{

    final NxtLightSensor this$0;

    public void run()
    {
        if (bluetooth != null && bluetooth.IsConnected())
        {
            Sensor.SensorValue sensorvalue = NxtLightSensor.access$000(NxtLightSensor.this, "");
            if (sensorvalue.valid)
            {
                ate ate;
                if (((Integer)sensorvalue.value).intValue() < NxtLightSensor.access$100(NxtLightSensor.this))
                {
                    ate = ate.BELOW_RANGE;
                } else
                if (((Integer)sensorvalue.value).intValue() > NxtLightSensor.access$200(NxtLightSensor.this))
                {
                    ate = ate.ABOVE_RANGE;
                } else
                {
                    ate = ate.WITHIN_RANGE;
                }
                if (ate != NxtLightSensor.access$300(NxtLightSensor.this))
                {
                    if (ate == ate.BELOW_RANGE && NxtLightSensor.access$400(NxtLightSensor.this))
                    {
                        BelowRange();
                    }
                    if (ate == ate.WITHIN_RANGE && NxtLightSensor.access$500(NxtLightSensor.this))
                    {
                        WithinRange();
                    }
                    if (ate == ate.ABOVE_RANGE && NxtLightSensor.access$600(NxtLightSensor.this))
                    {
                        AboveRange();
                    }
                }
                NxtLightSensor.access$302(NxtLightSensor.this, ate);
            }
        }
        if (NxtLightSensor.access$700(NxtLightSensor.this))
        {
            NxtLightSensor.access$900(NxtLightSensor.this).post(NxtLightSensor.access$800(NxtLightSensor.this));
        }
    }

    ate()
    {
        this$0 = NxtLightSensor.this;
        super();
    }
}
