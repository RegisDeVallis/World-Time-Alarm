// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.os.Handler;

// Referenced classes of package com.google.appinventor.components.runtime:
//            NxtUltrasonicSensor, BluetoothClient

class this._cls0
    implements Runnable
{

    final NxtUltrasonicSensor this$0;

    public void run()
    {
        if (bluetooth != null && bluetooth.IsConnected())
        {
            r.SensorValue sensorvalue = NxtUltrasonicSensor.access$000(NxtUltrasonicSensor.this, "");
            if (sensorvalue.valid)
            {
                ate ate;
                if (((Integer)sensorvalue.value).intValue() < NxtUltrasonicSensor.access$100(NxtUltrasonicSensor.this))
                {
                    ate = ate.BELOW_RANGE;
                } else
                if (((Integer)sensorvalue.value).intValue() > NxtUltrasonicSensor.access$200(NxtUltrasonicSensor.this))
                {
                    ate = ate.ABOVE_RANGE;
                } else
                {
                    ate = ate.WITHIN_RANGE;
                }
                if (ate != NxtUltrasonicSensor.access$300(NxtUltrasonicSensor.this))
                {
                    if (ate == ate.BELOW_RANGE && NxtUltrasonicSensor.access$400(NxtUltrasonicSensor.this))
                    {
                        BelowRange();
                    }
                    if (ate == ate.WITHIN_RANGE && NxtUltrasonicSensor.access$500(NxtUltrasonicSensor.this))
                    {
                        WithinRange();
                    }
                    if (ate == ate.ABOVE_RANGE && NxtUltrasonicSensor.access$600(NxtUltrasonicSensor.this))
                    {
                        AboveRange();
                    }
                }
                NxtUltrasonicSensor.access$302(NxtUltrasonicSensor.this, ate);
            }
        }
        if (NxtUltrasonicSensor.access$700(NxtUltrasonicSensor.this))
        {
            NxtUltrasonicSensor.access$900(NxtUltrasonicSensor.this).post(NxtUltrasonicSensor.access$800(NxtUltrasonicSensor.this));
        }
    }

    ate()
    {
        this$0 = NxtUltrasonicSensor.this;
        super();
    }
}
