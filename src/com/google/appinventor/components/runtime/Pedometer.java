// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.Array;
import java.util.List;

// Referenced classes of package com.google.appinventor.components.runtime:
//            AndroidNonvisibleComponent, Component, Deleteable, ComponentContainer, 
//            EventDispatcher

public class Pedometer extends AndroidNonvisibleComponent
    implements Component, LocationListener, SensorEventListener, Deleteable
{

    private static final int DIMENSIONS = 3;
    private static final int INTERVAL_VARIATION = 250;
    private static final int MIN_SATELLITES = 4;
    private static final int NUM_INTERVALS = 2;
    private static final float PEAK_VALLEY_RANGE = 4F;
    private static final String PREFS_NAME = "PedometerPrefs";
    private static final float STRIDE_LENGTH = 0.73F;
    private static final String TAG = "Pedometer";
    private static final int WIN_SIZE = 20;
    private boolean calibrateSteps;
    private final Context context;
    private Location currentLocation;
    private float distWhenGPSLost;
    private long elapsedTimestamp;
    private boolean firstGpsReading;
    private boolean foundNonStep;
    private boolean foundValley[];
    private boolean gpsAvailable;
    private float gpsDistance;
    private long gpsStepTime;
    private int intervalPos;
    private int lastNumSteps;
    private float lastValley[];
    private float lastValues[][];
    private final LocationManager locationManager;
    private Location locationWhenGPSLost;
    private int numStepsRaw;
    private int numStepsWithFilter;
    private int peak[];
    private boolean pedometerPaused;
    private float prevDiff[];
    private Location prevLocation;
    private long prevStopClockTime;
    private final SensorManager sensorManager;
    private boolean startPeaking;
    private long startTime;
    private boolean statusMoving;
    private long stepInterval[];
    private long stepTimestamp;
    private int stopDetectionTimeout;
    private float strideLength;
    private float totalDistance;
    private boolean useGps;
    private int valley[];
    private int winPos;

    public Pedometer(ComponentContainer componentcontainer)
    {
        super(componentcontainer.$form());
        stopDetectionTimeout = 2000;
        winPos = 0;
        intervalPos = 0;
        numStepsWithFilter = 0;
        numStepsRaw = 0;
        lastNumSteps = 0;
        peak = new int[3];
        valley = new int[3];
        lastValley = new float[3];
        int ai[] = {
            20, 3
        };
        lastValues = (float[][])Array.newInstance(Float.TYPE, ai);
        prevDiff = new float[3];
        strideLength = 0.73F;
        totalDistance = 0.0F;
        distWhenGPSLost = 0.0F;
        gpsDistance = 0.0F;
        stepInterval = new long[2];
        stepTimestamp = 0L;
        elapsedTimestamp = 0L;
        startTime = 0L;
        prevStopClockTime = 0L;
        gpsStepTime = 0L;
        foundValley = new boolean[3];
        startPeaking = false;
        foundNonStep = true;
        gpsAvailable = false;
        calibrateSteps = true;
        pedometerPaused = true;
        useGps = true;
        statusMoving = false;
        firstGpsReading = true;
        context = componentcontainer.$context();
        winPos = 0;
        startPeaking = false;
        numStepsWithFilter = 0;
        numStepsRaw = 0;
        firstGpsReading = true;
        gpsDistance = 0.0F;
        for (int i = 0; i < 3; i++)
        {
            foundValley[i] = true;
            lastValley[i] = 0.0F;
        }

        sensorManager = (SensorManager)context.getSystemService("sensor");
        locationManager = (LocationManager)context.getSystemService("location");
        locationManager.requestLocationUpdates("gps", 0L, 0.0F, this);
        SharedPreferences sharedpreferences = context.getSharedPreferences("PedometerPrefs", 0);
        strideLength = sharedpreferences.getFloat("Pedometer.stridelength", 0.73F);
        totalDistance = sharedpreferences.getFloat("Pedometer.distance", 0.0F);
        numStepsRaw = sharedpreferences.getInt("Pedometer.prevStepCount", 0);
        prevStopClockTime = sharedpreferences.getLong("Pedometer.clockTime", 0L);
        numStepsWithFilter = numStepsRaw;
        startTime = System.currentTimeMillis();
        Log.d("Pedometer", "Pedometer Created");
    }

    private boolean areStepsEquallySpaced()
    {
        float f = 0.0F;
        int i = 0;
        long al[] = stepInterval;
        int j = al.length;
        for (int k = 0; k < j; k++)
        {
            long l1 = al[k];
            if (l1 > 0L)
            {
                i++;
                f += l1;
            }
        }

        float f1 = f / (float)i;
        long al1[] = stepInterval;
        int l = al1.length;
        for (int i1 = 0; i1 < l; i1++)
        {
            if (Math.abs((float)al1[i1] - f1) > 250F)
            {
                return false;
            }
        }

        return true;
    }

    private void getPeak()
    {
        int i = (10 + winPos) % 20;
        int j = 0;
label0:
        do
        {
            if (j < 3)
            {
                peak[j] = i;
                int k = 0;
                do
                {
label1:
                    {
                        if (k < 20)
                        {
                            if (k == i || lastValues[k][j] < lastValues[i][j])
                            {
                                break label1;
                            }
                            peak[j] = -1;
                        }
                        j++;
                        continue label0;
                    }
                    k++;
                } while (true);
            }
            return;
        } while (true);
    }

    private void getValley()
    {
        int i = (10 + winPos) % 20;
        int j = 0;
label0:
        do
        {
            if (j < 3)
            {
                valley[j] = i;
                int k = 0;
                do
                {
label1:
                    {
                        if (k < 20)
                        {
                            if (k == i || lastValues[k][j] > lastValues[i][j])
                            {
                                break label1;
                            }
                            valley[j] = -1;
                        }
                        j++;
                        continue label0;
                    }
                    k++;
                } while (true);
            }
            return;
        } while (true);
    }

    private void setGpsAvailable(boolean flag)
    {
        if (!gpsAvailable && flag)
        {
            gpsAvailable = true;
            GPSAvailable();
        } else
        if (gpsAvailable && !flag)
        {
            gpsAvailable = false;
            GPSLost();
            return;
        }
    }

    public void CalibrateStrideLength(boolean flag)
    {
        if (!gpsAvailable && flag)
        {
            CalibrationFailed();
            return;
        }
        if (flag)
        {
            useGps = true;
        }
        calibrateSteps = flag;
    }

    public boolean CalibrateStrideLength()
    {
        return calibrateSteps;
    }

    public void CalibrationFailed()
    {
        EventDispatcher.dispatchEvent(this, "CalibrationFailed", new Object[0]);
    }

    public float Distance()
    {
        return totalDistance;
    }

    public long ElapsedTime()
    {
        if (pedometerPaused)
        {
            return prevStopClockTime;
        } else
        {
            return prevStopClockTime + (System.currentTimeMillis() - startTime);
        }
    }

    public void GPSAvailable()
    {
        EventDispatcher.dispatchEvent(this, "GPSAvailable", new Object[0]);
    }

    public void GPSLost()
    {
        EventDispatcher.dispatchEvent(this, "GPSLost", new Object[0]);
    }

    public boolean Moving()
    {
        return statusMoving;
    }

    public void Pause()
    {
        if (!pedometerPaused)
        {
            pedometerPaused = true;
            statusMoving = false;
            sensorManager.unregisterListener(this);
            Log.d("Pedometer", "Unregistered listener on pause");
            prevStopClockTime = prevStopClockTime + (System.currentTimeMillis() - startTime);
        }
    }

    public void Reset()
    {
        numStepsWithFilter = 0;
        numStepsRaw = 0;
        totalDistance = 0.0F;
        calibrateSteps = false;
        prevStopClockTime = 0L;
        startTime = System.currentTimeMillis();
    }

    public void Resume()
    {
        Start();
    }

    public void Save()
    {
        android.content.SharedPreferences.Editor editor = context.getSharedPreferences("PedometerPrefs", 0).edit();
        editor.putFloat("Pedometer.stridelength", strideLength);
        editor.putFloat("Pedometer.distance", totalDistance);
        editor.putInt("Pedometer.prevStepCount", numStepsRaw);
        if (pedometerPaused)
        {
            editor.putLong("Pedometer.clockTime", prevStopClockTime);
        } else
        {
            editor.putLong("Pedometer.clockTime", prevStopClockTime + (System.currentTimeMillis() - startTime));
        }
        editor.putLong("Pedometer.closeTime", System.currentTimeMillis());
        editor.commit();
        Log.d("Pedometer", "Pedometer state saved.");
    }

    public void SimpleStep(int i, float f)
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Float.valueOf(f);
        EventDispatcher.dispatchEvent(this, "SimpleStep", aobj);
    }

    public void Start()
    {
        if (pedometerPaused)
        {
            pedometerPaused = false;
            sensorManager.registerListener(this, (Sensor)sensorManager.getSensorList(1).get(0), 0);
            startTime = System.currentTimeMillis();
        }
    }

    public void StartedMoving()
    {
        EventDispatcher.dispatchEvent(this, "StartedMoving", new Object[0]);
    }

    public void Stop()
    {
        Pause();
        locationManager.removeUpdates(this);
        useGps = false;
        calibrateSteps = false;
        setGpsAvailable(false);
    }

    public int StopDetectionTimeout()
    {
        return stopDetectionTimeout;
    }

    public void StopDetectionTimeout(int i)
    {
        stopDetectionTimeout = i;
    }

    public void StoppedMoving()
    {
        EventDispatcher.dispatchEvent(this, "StoppedMoving", new Object[0]);
    }

    public float StrideLength()
    {
        return strideLength;
    }

    public void StrideLength(float f)
    {
        CalibrateStrideLength(false);
        strideLength = f;
    }

    public void UseGPS(boolean flag)
    {
        if (flag || !useGps) goto _L2; else goto _L1
_L1:
        locationManager.removeUpdates(this);
_L4:
        useGps = flag;
        return;
_L2:
        if (flag && !useGps)
        {
            locationManager.requestLocationUpdates("gps", 0L, 0.0F, this);
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public boolean UseGPS()
    {
        return useGps;
    }

    public void WalkStep(int i, float f)
    {
        Object aobj[] = new Object[2];
        aobj[0] = Integer.valueOf(i);
        aobj[1] = Float.valueOf(f);
        EventDispatcher.dispatchEvent(this, "WalkStep", aobj);
    }

    public void onAccuracyChanged(Sensor sensor, int i)
    {
        Log.d("Pedometer", "Accelerometer accuracy changed.");
    }

    public void onDelete()
    {
        sensorManager.unregisterListener(this);
        locationManager.removeUpdates(this);
    }

    public void onLocationChanged(Location location)
    {
        if (!statusMoving || pedometerPaused || !useGps)
        {
            return;
        }
        currentLocation = location;
        if (currentLocation.getAccuracy() > 10F)
        {
            setGpsAvailable(false);
            return;
        }
        setGpsAvailable(true);
        float f;
        if (prevLocation != null)
        {
            f = currentLocation.distanceTo(prevLocation);
            if ((double)f > 0.10000000000000001D && f < 10F)
            {
                totalDistance = f + totalDistance;
                prevLocation = currentLocation;
            }
        } else
        {
            if (locationWhenGPSLost != null)
            {
                float f1 = currentLocation.distanceTo(locationWhenGPSLost);
                totalDistance = distWhenGPSLost + (f1 + (totalDistance - distWhenGPSLost)) / 2.0F;
            }
            gpsStepTime = System.currentTimeMillis();
            prevLocation = currentLocation;
            f = 0.0F;
        }
        if (calibrateSteps)
        {
            if (!firstGpsReading)
            {
                gpsDistance = f + gpsDistance;
                int i = numStepsRaw - lastNumSteps;
                strideLength = gpsDistance / (float)i;
                return;
            } else
            {
                firstGpsReading = false;
                lastNumSteps = numStepsRaw;
                return;
            }
        } else
        {
            firstGpsReading = true;
            gpsDistance = 0.0F;
            return;
        }
    }

    public void onProviderDisabled(String s)
    {
        distWhenGPSLost = totalDistance;
        locationWhenGPSLost = currentLocation;
        firstGpsReading = true;
        prevLocation = null;
        setGpsAvailable(false);
    }

    public void onProviderEnabled(String s)
    {
        setGpsAvailable(true);
    }

    public void onSensorChanged(SensorEvent sensorevent)
    {
        if (sensorevent.sensor.getType() != 1)
        {
            return;
        }
        float af[] = sensorevent.values;
        if (startPeaking)
        {
            getPeak();
            getValley();
        }
        int i;
        int j;
        if (prevDiff[0] > prevDiff[1])
        {
            i = 0;
        } else
        {
            i = 1;
        }
        if (prevDiff[2] > prevDiff[i])
        {
            i = 2;
        }
        j = 0;
        while (j < 3) 
        {
            if (startPeaking && peak[j] >= 0)
            {
                if (foundValley[j] && lastValues[peak[j]][j] - lastValley[j] > 4F)
                {
                    if (i == j)
                    {
                        long l1 = System.currentTimeMillis();
                        stepInterval[intervalPos] = l1 - stepTimestamp;
                        intervalPos = (1 + intervalPos) % 2;
                        stepTimestamp = l1;
                        if (areStepsEquallySpaced())
                        {
                            if (foundNonStep)
                            {
                                numStepsWithFilter = 2 + numStepsWithFilter;
                                if (!gpsAvailable)
                                {
                                    totalDistance = totalDistance + 2.0F * strideLength;
                                }
                                foundNonStep = false;
                            }
                            numStepsWithFilter = 1 + numStepsWithFilter;
                            WalkStep(numStepsWithFilter, totalDistance);
                            if (!gpsAvailable)
                            {
                                totalDistance = totalDistance + strideLength;
                            }
                        } else
                        {
                            foundNonStep = true;
                        }
                        numStepsRaw = 1 + numStepsRaw;
                        SimpleStep(numStepsRaw, totalDistance);
                        if (!statusMoving)
                        {
                            statusMoving = true;
                            StartedMoving();
                        }
                    }
                    foundValley[j] = false;
                    prevDiff[j] = lastValues[peak[j]][j] - lastValley[j];
                } else
                {
                    prevDiff[j] = 0.0F;
                }
            }
            if (startPeaking && valley[j] >= 0)
            {
                foundValley[j] = true;
                lastValley[j] = lastValues[valley[j]][j];
            }
            lastValues[winPos][j] = af[j];
            j++;
        }
        elapsedTimestamp = System.currentTimeMillis();
        if (elapsedTimestamp - stepTimestamp > (long)stopDetectionTimeout)
        {
            if (statusMoving)
            {
                statusMoving = false;
                StoppedMoving();
            }
            stepTimestamp = elapsedTimestamp;
        }
        int k;
        int l;
        if (-1 + winPos < 0)
        {
            k = 19;
        } else
        {
            k = -1 + winPos;
        }
        for (l = 0; l < 3; l++)
        {
            if (lastValues[k][l] == lastValues[winPos][l])
            {
                float af1[] = lastValues[winPos];
                af1[l] = (float)(0.001D + (double)af1[l]);
            }
        }

        if (winPos == 19 && !startPeaking)
        {
            startPeaking = true;
        }
        winPos = (1 + winPos) % 20;
    }

    public void onStatusChanged(String s, int i, Bundle bundle)
    {
    }
}
