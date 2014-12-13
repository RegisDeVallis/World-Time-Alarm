// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.os.Handler;

// Referenced classes of package com.google.appinventor.components.runtime:
//            NxtColorSensor, BluetoothClient

class this._cls0
    implements Runnable
{

    final NxtColorSensor this$0;

    public void run()
    {
        if (bluetooth == null || !bluetooth.IsConnected()) goto _L2; else goto _L1
_L1:
        if (!NxtColorSensor.access$000(NxtColorSensor.this)) goto _L4; else goto _L3
_L3:
        Sensor.SensorValue sensorvalue1 = NxtColorSensor.access$100(NxtColorSensor.this, "");
        if (sensorvalue1.valid)
        {
            int i = ((Integer)sensorvalue1.value).intValue();
            if (i != NxtColorSensor.access$200(NxtColorSensor.this))
            {
                ColorChanged(i);
            }
            NxtColorSensor.access$202(NxtColorSensor.this, i);
        }
_L2:
        if (NxtColorSensor.access$1000(NxtColorSensor.this))
        {
            NxtColorSensor.access$1200(NxtColorSensor.this).post(NxtColorSensor.access$1100(NxtColorSensor.this));
        }
        return;
_L4:
        Sensor.SensorValue sensorvalue;
        ate ate;
        sensorvalue = NxtColorSensor.access$300(NxtColorSensor.this, "");
        if (!sensorvalue.valid)
        {
            continue; /* Loop/switch isn't completed */
        }
        if (((Integer)sensorvalue.value).intValue() >= NxtColorSensor.access$400(NxtColorSensor.this))
        {
            break; /* Loop/switch isn't completed */
        }
        ate = ate.BELOW_RANGE;
_L6:
        if (ate != NxtColorSensor.access$600(NxtColorSensor.this))
        {
            if (ate == ate.BELOW_RANGE && NxtColorSensor.access$700(NxtColorSensor.this))
            {
                BelowRange();
            }
            if (ate == ate.WITHIN_RANGE && NxtColorSensor.access$800(NxtColorSensor.this))
            {
                WithinRange();
            }
            if (ate == ate.ABOVE_RANGE && NxtColorSensor.access$900(NxtColorSensor.this))
            {
                AboveRange();
            }
        }
        NxtColorSensor.access$602(NxtColorSensor.this, ate);
        if (true) goto _L2; else goto _L5
_L5:
        if (((Integer)sensorvalue.value).intValue() > NxtColorSensor.access$500(NxtColorSensor.this))
        {
            ate = ate.ABOVE_RANGE;
        } else
        {
            ate = ate.WITHIN_RANGE;
        }
          goto _L6
        if (true) goto _L2; else goto _L7
_L7:
    }

    ate()
    {
        this$0 = NxtColorSensor.this;
        super();
    }
}
