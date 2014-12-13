// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.os.Handler;

// Referenced classes of package com.google.appinventor.components.runtime:
//            LegoMindstormsNxtSensor, Deleteable, EventDispatcher, ComponentContainer, 
//            BluetoothClient

public class NxtUltrasonicSensor extends LegoMindstormsNxtSensor
    implements Deleteable
{
    private static final class State extends Enum
    {

        private static final State $VALUES[];
        public static final State ABOVE_RANGE;
        public static final State BELOW_RANGE;
        public static final State UNKNOWN;
        public static final State WITHIN_RANGE;

        public static State valueOf(String s)
        {
            return (State)Enum.valueOf(com/google/appinventor/components/runtime/NxtUltrasonicSensor$State, s);
        }

        public static State[] values()
        {
            return (State[])$VALUES.clone();
        }

        static 
        {
            UNKNOWN = new State("UNKNOWN", 0);
            BELOW_RANGE = new State("BELOW_RANGE", 1);
            WITHIN_RANGE = new State("WITHIN_RANGE", 2);
            ABOVE_RANGE = new State("ABOVE_RANGE", 3);
            State astate[] = new State[4];
            astate[0] = UNKNOWN;
            astate[1] = BELOW_RANGE;
            astate[2] = WITHIN_RANGE;
            astate[3] = ABOVE_RANGE;
            $VALUES = astate;
        }

        private State(String s, int i)
        {
            super(s, i);
        }
    }


    private static final int DEFAULT_BOTTOM_OF_RANGE = 30;
    private static final String DEFAULT_SENSOR_PORT = "4";
    private static final int DEFAULT_TOP_OF_RANGE = 90;
    private boolean aboveRangeEventEnabled;
    private boolean belowRangeEventEnabled;
    private int bottomOfRange;
    private Handler handler;
    private State previousState;
    private final Runnable sensorReader = new Runnable() {

        final NxtUltrasonicSensor this$0;

        public void run()
        {
            if (bluetooth != null && bluetooth.IsConnected())
            {
                LegoMindstormsNxtSensor.SensorValue sensorvalue = getDistanceValue("");
                if (sensorvalue.valid)
                {
                    State state;
                    if (((Integer)sensorvalue.value).intValue() < bottomOfRange)
                    {
                        state = State.BELOW_RANGE;
                    } else
                    if (((Integer)sensorvalue.value).intValue() > topOfRange)
                    {
                        state = State.ABOVE_RANGE;
                    } else
                    {
                        state = State.WITHIN_RANGE;
                    }
                    if (state != previousState)
                    {
                        if (state == State.BELOW_RANGE && belowRangeEventEnabled)
                        {
                            BelowRange();
                        }
                        if (state == State.WITHIN_RANGE && withinRangeEventEnabled)
                        {
                            WithinRange();
                        }
                        if (state == State.ABOVE_RANGE && aboveRangeEventEnabled)
                        {
                            AboveRange();
                        }
                    }
                    previousState = state;
                }
            }
            if (isHandlerNeeded())
            {
                handler.post(sensorReader);
            }
        }

            
            {
                this$0 = NxtUltrasonicSensor.this;
                super();
            }
    };
    private int topOfRange;
    private boolean withinRangeEventEnabled;

    public NxtUltrasonicSensor(ComponentContainer componentcontainer)
    {
        super(componentcontainer, "NxtUltrasonicSensor");
        handler = new Handler();
        previousState = State.UNKNOWN;
        SensorPort("4");
        BottomOfRange(30);
        TopOfRange(90);
        BelowRangeEventEnabled(false);
        WithinRangeEventEnabled(false);
        AboveRangeEventEnabled(false);
    }

    private void configureUltrasonicSensor(String s)
    {
        byte abyte0[] = {
            2, 65, 2
        };
        lsWrite(s, port, abyte0, 0);
    }

    private LegoMindstormsNxtSensor.SensorValue getDistanceValue(String s)
    {
        byte abyte0[] = {
            2, 66
        };
        lsWrite(s, port, abyte0, 1);
        int i = 0;
        do
        {
            if (i >= 3)
            {
                break;
            }
            if (lsGetStatus(s, port) > 0)
            {
                byte abyte1[] = lsRead(s, port);
                if (abyte1 != null)
                {
                    int j = getUBYTEValueFromBytes(abyte1, 4);
                    if (j >= 0 && j <= 254)
                    {
                        return new LegoMindstormsNxtSensor.SensorValue(true, Integer.valueOf(j));
                    }
                }
                break;
            }
            i++;
        } while (true);
        return new LegoMindstormsNxtSensor.SensorValue(false, null);
    }

    private boolean isHandlerNeeded()
    {
        return belowRangeEventEnabled || withinRangeEventEnabled || aboveRangeEventEnabled;
    }

    public void AboveRange()
    {
        EventDispatcher.dispatchEvent(this, "AboveRange", new Object[0]);
    }

    public void AboveRangeEventEnabled(boolean flag)
    {
        boolean flag1 = isHandlerNeeded();
        aboveRangeEventEnabled = flag;
        boolean flag2 = isHandlerNeeded();
        if (flag1 && !flag2)
        {
            handler.removeCallbacks(sensorReader);
        }
        if (!flag1 && flag2)
        {
            previousState = State.UNKNOWN;
            handler.post(sensorReader);
        }
    }

    public boolean AboveRangeEventEnabled()
    {
        return aboveRangeEventEnabled;
    }

    public void BelowRange()
    {
        EventDispatcher.dispatchEvent(this, "BelowRange", new Object[0]);
    }

    public void BelowRangeEventEnabled(boolean flag)
    {
        boolean flag1 = isHandlerNeeded();
        belowRangeEventEnabled = flag;
        boolean flag2 = isHandlerNeeded();
        if (flag1 && !flag2)
        {
            handler.removeCallbacks(sensorReader);
        }
        if (!flag1 && flag2)
        {
            previousState = State.UNKNOWN;
            handler.post(sensorReader);
        }
    }

    public boolean BelowRangeEventEnabled()
    {
        return belowRangeEventEnabled;
    }

    public int BottomOfRange()
    {
        return bottomOfRange;
    }

    public void BottomOfRange(int i)
    {
        bottomOfRange = i;
        previousState = State.UNKNOWN;
    }

    public int GetDistance()
    {
        LegoMindstormsNxtSensor.SensorValue sensorvalue;
        if (checkBluetooth("GetDistance"))
        {
            if ((sensorvalue = getDistanceValue("GetDistance")).valid)
            {
                return ((Integer)sensorvalue.value).intValue();
            }
        }
        return -1;
    }

    public void SensorPort(String s)
    {
        setSensorPort(s);
    }

    public int TopOfRange()
    {
        return topOfRange;
    }

    public void TopOfRange(int i)
    {
        topOfRange = i;
        previousState = State.UNKNOWN;
    }

    public void WithinRange()
    {
        EventDispatcher.dispatchEvent(this, "WithinRange", new Object[0]);
    }

    public void WithinRangeEventEnabled(boolean flag)
    {
        boolean flag1 = isHandlerNeeded();
        withinRangeEventEnabled = flag;
        boolean flag2 = isHandlerNeeded();
        if (flag1 && !flag2)
        {
            handler.removeCallbacks(sensorReader);
        }
        if (!flag1 && flag2)
        {
            previousState = State.UNKNOWN;
            handler.post(sensorReader);
        }
    }

    public boolean WithinRangeEventEnabled()
    {
        return withinRangeEventEnabled;
    }

    protected void initializeSensor(String s)
    {
        setInputMode(s, port, 11, 0);
        configureUltrasonicSensor(s);
    }

    public void onDelete()
    {
        handler.removeCallbacks(sensorReader);
        super.onDelete();
    }






/*
    static State access$302(NxtUltrasonicSensor nxtultrasonicsensor, State state)
    {
        nxtultrasonicsensor.previousState = state;
        return state;
    }

*/






}
