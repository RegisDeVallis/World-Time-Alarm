// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.os.Handler;

// Referenced classes of package com.google.appinventor.components.runtime:
//            NxtSoundSensor, BluetoothClient

class this._cls0
    implements Runnable
{

    final NxtSoundSensor this$0;

    public void run()
    {
        if (bluetooth != null && bluetooth.IsConnected())
        {
            Sensor.SensorValue sensorvalue = NxtSoundSensor.access$000(NxtSoundSensor.this, "");
            if (sensorvalue.valid)
            {
                ate ate;
                if (((Integer)sensorvalue.value).intValue() < NxtSoundSensor.access$100(NxtSoundSensor.this))
                {
                    ate = ate.BELOW_RANGE;
                } else
                if (((Integer)sensorvalue.value).intValue() > NxtSoundSensor.access$200(NxtSoundSensor.this))
                {
                    ate = ate.ABOVE_RANGE;
                } else
                {
                    ate = ate.WITHIN_RANGE;
                }
                if (ate != NxtSoundSensor.access$300(NxtSoundSensor.this))
                {
                    if (ate == ate.BELOW_RANGE && NxtSoundSensor.access$400(NxtSoundSensor.this))
                    {
                        BelowRange();
                    }
                    if (ate == ate.WITHIN_RANGE && NxtSoundSensor.access$500(NxtSoundSensor.this))
                    {
                        WithinRange();
                    }
                    if (ate == ate.ABOVE_RANGE && NxtSoundSensor.access$600(NxtSoundSensor.this))
                    {
                        AboveRange();
                    }
                }
                NxtSoundSensor.access$302(NxtSoundSensor.this, ate);
            }
        }
        if (NxtSoundSensor.access$700(NxtSoundSensor.this))
        {
            NxtSoundSensor.access$900(NxtSoundSensor.this).post(NxtSoundSensor.access$800(NxtSoundSensor.this));
        }
    }

    ate()
    {
        this$0 = NxtSoundSensor.this;
        super();
    }
}
