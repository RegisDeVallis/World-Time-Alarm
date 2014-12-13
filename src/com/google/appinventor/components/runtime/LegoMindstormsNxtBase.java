// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.appinventor.components.runtime:
//            AndroidNonvisibleComponent, BluetoothConnectionListener, Component, Deleteable, 
//            ComponentContainer, Form, BluetoothClient, BluetoothConnectionBase

public class LegoMindstormsNxtBase extends AndroidNonvisibleComponent
    implements BluetoothConnectionListener, Component, Deleteable
{

    private static final Map ERROR_MESSAGES;
    private static final int TOY_ROBOT = 2052;
    protected BluetoothClient bluetooth;
    protected final String logTag;

    protected LegoMindstormsNxtBase()
    {
        super(null);
        logTag = null;
    }

    protected LegoMindstormsNxtBase(ComponentContainer componentcontainer, String s)
    {
        super(componentcontainer.$form());
        logTag = s;
    }

    private void handleError(String s, int i)
    {
        if (i < 0)
        {
            return;
        }
        String s1 = (String)ERROR_MESSAGES.get(Integer.valueOf(i));
        if (s1 != null)
        {
            this.form.dispatchErrorOccurredEvent(this, s, 404, new Object[] {
                s1
            });
            return;
        } else
        {
            Form form = this.form;
            Object aobj[] = new Object[1];
            aobj[0] = (new StringBuilder()).append("Error code 0x").append(Integer.toHexString(i & 0xff)).toString();
            form.dispatchErrorOccurredEvent(this, s, 404, aobj);
            return;
        }
    }

    private byte[] receiveReturnPackage(String s)
    {
        byte abyte0[] = bluetooth.read(s, 2);
        if (abyte0.length == 2)
        {
            int i = getUWORDValueFromBytes(abyte0, 0);
            byte abyte1[] = bluetooth.read(s, i);
            if (abyte1.length >= 3)
            {
                return abyte1;
            }
        }
        form.dispatchErrorOccurredEvent(this, s, 403, new Object[0]);
        return new byte[0];
    }

    public BluetoothClient BluetoothClient()
    {
        return bluetooth;
    }

    public void BluetoothClient(BluetoothClient bluetoothclient)
    {
        if (bluetooth != null)
        {
            bluetooth.removeBluetoothConnectionListener(this);
            bluetooth.detachComponent(this);
            bluetooth = null;
        }
        if (bluetoothclient != null)
        {
            bluetooth = bluetoothclient;
            bluetooth.attachComponent(this, Collections.singleton(Integer.valueOf(2052)));
            bluetooth.addBluetoothConnectionListener(this);
            if (bluetooth.IsConnected())
            {
                afterConnect(bluetooth);
            }
        }
    }

    public final void Initialize()
    {
    }

    public void afterConnect(BluetoothConnectionBase bluetoothconnectionbase)
    {
    }

    public void beforeDisconnect(BluetoothConnectionBase bluetoothconnectionbase)
    {
    }

    protected final boolean checkBluetooth(String s)
    {
        if (bluetooth == null)
        {
            form.dispatchErrorOccurredEvent(this, s, 401, new Object[0]);
            return false;
        }
        if (!bluetooth.IsConnected())
        {
            form.dispatchErrorOccurredEvent(this, s, 402, new Object[0]);
            return false;
        } else
        {
            return true;
        }
    }

    protected final int convertMotorPortLetterToNumber(char c)
    {
        if (c == 'A' || c == 'a')
        {
            return 0;
        }
        if (c == 'B' || c == 'b')
        {
            return 1;
        }
        if (c == 'C' || c == 'c')
        {
            return 2;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal motor port letter ").append(c).toString());
        }
    }

    protected final int convertMotorPortLetterToNumber(String s)
    {
        if (s.length() == 1)
        {
            return convertMotorPortLetterToNumber(s.charAt(0));
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal motor port letter ").append(s).toString());
        }
    }

    protected final int convertSensorPortLetterToNumber(char c)
    {
        if (c == '1')
        {
            return 0;
        }
        if (c == '2')
        {
            return 1;
        }
        if (c == '3')
        {
            return 2;
        }
        if (c == '4')
        {
            return 3;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal sensor port letter ").append(c).toString());
        }
    }

    protected final int convertSensorPortLetterToNumber(String s)
    {
        if (s.length() == 1)
        {
            return convertSensorPortLetterToNumber(s.charAt(0));
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Illegal sensor port letter ").append(s).toString());
        }
    }

    protected final void copyBooleanValueToBytes(boolean flag, byte abyte0[], int i)
    {
        byte byte0;
        if (flag)
        {
            byte0 = 1;
        } else
        {
            byte0 = 0;
        }
        abyte0[i] = byte0;
    }

    protected final void copySBYTEValueToBytes(int i, byte abyte0[], int j)
    {
        abyte0[j] = (byte)i;
    }

    protected final void copySLONGValueToBytes(int i, byte abyte0[], int j)
    {
        abyte0[j] = (byte)(i & 0xff);
        int k = i >> 8;
        abyte0[j + 1] = (byte)(k & 0xff);
        int l = k >> 8;
        abyte0[j + 2] = (byte)(l & 0xff);
        int i1 = l >> 8;
        abyte0[j + 3] = (byte)(i1 & 0xff);
    }

    protected final void copySWORDValueToBytes(int i, byte abyte0[], int j)
    {
        abyte0[j] = (byte)(i & 0xff);
        int k = i >> 8;
        abyte0[j + 1] = (byte)(k & 0xff);
    }

    protected final void copyStringValueToBytes(String s, byte abyte0[], int i, int j)
    {
        if (s.length() > j)
        {
            s = s.substring(0, j);
        }
        byte abyte2[] = s.getBytes("ISO-8859-1");
        byte abyte1[] = abyte2;
_L2:
        System.arraycopy(abyte1, 0, abyte0, i, Math.min(j, abyte1.length));
        return;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        Log.w(logTag, (new StringBuilder()).append("UnsupportedEncodingException: ").append(unsupportedencodingexception.getMessage()).toString());
        abyte1 = s.getBytes();
        if (true) goto _L2; else goto _L1
_L1:
    }

    protected final void copyUBYTEValueToBytes(int i, byte abyte0[], int j)
    {
        abyte0[j] = (byte)i;
    }

    protected final void copyULONGValueToBytes(long l, byte abyte0[], int i)
    {
        abyte0[i] = (byte)(int)(l & 255L);
        long l1 = l >> 8;
        abyte0[i + 1] = (byte)(int)(l1 & 255L);
        long l2 = l1 >> 8;
        abyte0[i + 2] = (byte)(int)(l2 & 255L);
        long l3 = l2 >> 8;
        abyte0[i + 3] = (byte)(int)(l3 & 255L);
    }

    protected final void copyUWORDValueToBytes(int i, byte abyte0[], int j)
    {
        abyte0[j] = (byte)(i & 0xff);
        int k = i >> 8;
        abyte0[j + 1] = (byte)(k & 0xff);
    }

    protected final boolean evaluateStatus(String s, byte abyte0[], byte byte0)
    {
        int i = getStatus(s, abyte0, byte0);
        if (i == 0)
        {
            return true;
        } else
        {
            handleError(s, i);
            return false;
        }
    }

    protected final boolean getBooleanValueFromBytes(byte abyte0[], int i)
    {
        return abyte0[i] != 0;
    }

    protected final byte[] getInputValues(String s, int i)
    {
        byte abyte0[] = new byte[3];
        abyte0[0] = 0;
        abyte0[1] = 7;
        copyUBYTEValueToBytes(i, abyte0, 2);
        byte abyte1[] = sendCommandAndReceiveReturnPackage(s, abyte0);
        if (evaluateStatus(s, abyte1, abyte0[1]))
        {
            if (abyte1.length == 16)
            {
                return abyte1;
            }
            Log.w(logTag, (new StringBuilder()).append(s).append(": unexpected return package length ").append(abyte1.length).append(" (expected 16)").toString());
        }
        return null;
    }

    protected final int getSBYTEValueFromBytes(byte abyte0[], int i)
    {
        return abyte0[i];
    }

    protected final int getSLONGValueFromBytes(byte abyte0[], int i)
    {
        return 0xff & abyte0[i] | (0xff & abyte0[i + 1]) << 8 | (0xff & abyte0[i + 2]) << 16 | abyte0[i + 3] << 24;
    }

    protected final int getSWORDValueFromBytes(byte abyte0[], int i)
    {
        return 0xff & abyte0[i] | abyte0[i + 1] << 8;
    }

    protected final int getStatus(String s, byte abyte0[], byte byte0)
    {
        if (abyte0.length >= 3)
        {
            if (abyte0[0] != 2)
            {
                Log.w(logTag, (new StringBuilder()).append(s).append(": unexpected return package byte 0: 0x").append(Integer.toHexString(0xff & abyte0[0])).append(" (expected 0x02)").toString());
            }
            if (abyte0[1] != byte0)
            {
                Log.w(logTag, (new StringBuilder()).append(s).append(": unexpected return package byte 1: 0x").append(Integer.toHexString(0xff & abyte0[1])).append(" (expected 0x").append(Integer.toHexString(byte0 & 0xff)).append(")").toString());
            }
            return getUBYTEValueFromBytes(abyte0, 2);
        } else
        {
            Log.w(logTag, (new StringBuilder()).append(s).append(": unexpected return package length ").append(abyte0.length).append(" (expected >= 3)").toString());
            return -1;
        }
    }

    protected final String getStringValueFromBytes(byte abyte0[], int i)
    {
        int j = i;
        do
        {
label0:
            {
                int k = abyte0.length;
                int l = 0;
                if (j < k)
                {
                    if (abyte0[j] != 0)
                    {
                        break label0;
                    }
                    l = j - i;
                }
                return getStringValueFromBytes(abyte0, i, l);
            }
            j++;
        } while (true);
    }

    protected final String getStringValueFromBytes(byte abyte0[], int i, int j)
    {
        String s;
        try
        {
            s = new String(abyte0, i, j, "ISO-8859-1");
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            Log.w(logTag, (new StringBuilder()).append("UnsupportedEncodingException: ").append(unsupportedencodingexception.getMessage()).toString());
            return new String(abyte0, i, j);
        }
        return s;
    }

    protected final int getUBYTEValueFromBytes(byte abyte0[], int i)
    {
        return 0xff & abyte0[i];
    }

    protected final long getULONGValueFromBytes(byte abyte0[], int i)
    {
        return 255L & (long)abyte0[i] | (255L & (long)abyte0[i + 1]) << 8 | (255L & (long)abyte0[i + 2]) << 16 | (255L & (long)abyte0[i + 3]) << 24;
    }

    protected final int getUWORDValueFromBytes(byte abyte0[], int i)
    {
        return 0xff & abyte0[i] | (0xff & abyte0[i + 1]) << 8;
    }

    protected final int lsGetStatus(String s, int i)
    {
        byte abyte1[];
label0:
        {
            byte abyte0[] = new byte[3];
            abyte0[0] = 0;
            abyte0[1] = 14;
            copyUBYTEValueToBytes(i, abyte0, 2);
            abyte1 = sendCommandAndReceiveReturnPackage(s, abyte0);
            boolean flag = evaluateStatus(s, abyte1, abyte0[1]);
            int j = 0;
            if (flag)
            {
                if (abyte1.length != 4)
                {
                    break label0;
                }
                j = getUBYTEValueFromBytes(abyte1, 3);
            }
            return j;
        }
        Log.w(logTag, (new StringBuilder()).append(s).append(": unexpected return package length ").append(abyte1.length).append(" (expected 4)").toString());
        return 0;
    }

    protected final byte[] lsRead(String s, int i)
    {
        byte abyte0[] = new byte[3];
        abyte0[0] = 0;
        abyte0[1] = 16;
        copyUBYTEValueToBytes(i, abyte0, 2);
        byte abyte1[] = sendCommandAndReceiveReturnPackage(s, abyte0);
        if (evaluateStatus(s, abyte1, abyte0[1]))
        {
            if (abyte1.length == 20)
            {
                return abyte1;
            }
            Log.w(logTag, (new StringBuilder()).append(s).append(": unexpected return package length ").append(abyte1.length).append(" (expected 20)").toString());
        }
        return null;
    }

    protected final void lsWrite(String s, int i, byte abyte0[], int j)
    {
        if (abyte0.length > 16)
        {
            throw new IllegalArgumentException("length must be <= 16");
        } else
        {
            byte abyte1[] = new byte[5 + abyte0.length];
            abyte1[0] = 0;
            abyte1[1] = 15;
            copyUBYTEValueToBytes(i, abyte1, 2);
            copyUBYTEValueToBytes(abyte0.length, abyte1, 3);
            copyUBYTEValueToBytes(j, abyte1, 4);
            System.arraycopy(abyte0, 0, abyte1, 5, abyte0.length);
            evaluateStatus(s, sendCommandAndReceiveReturnPackage(s, abyte1), abyte1[1]);
            return;
        }
    }

    public void onDelete()
    {
        if (bluetooth != null)
        {
            bluetooth.removeBluetoothConnectionListener(this);
            bluetooth.detachComponent(this);
            bluetooth = null;
        }
    }

    protected final void resetInputScaledValue(String s, int i)
    {
        byte abyte0[] = new byte[3];
        abyte0[0] = -128;
        abyte0[1] = 8;
        copyUBYTEValueToBytes(i, abyte0, 2);
        sendCommand(s, abyte0);
    }

    protected final int sanitizePower(int i)
    {
        if (i < -100)
        {
            Log.w(logTag, (new StringBuilder()).append("power ").append(i).append(" is invalid, using -100.").toString());
            i = -100;
        }
        if (i > 100)
        {
            Log.w(logTag, (new StringBuilder()).append("power ").append(i).append(" is invalid, using 100.").toString());
            i = 100;
        }
        return i;
    }

    protected final int sanitizeTurnRatio(int i)
    {
        if (i < -100)
        {
            Log.w(logTag, (new StringBuilder()).append("turnRatio ").append(i).append(" is invalid, using -100.").toString());
            i = -100;
        }
        if (i > 100)
        {
            Log.w(logTag, (new StringBuilder()).append("turnRatio ").append(i).append(" is invalid, using 100.").toString());
            i = 100;
        }
        return i;
    }

    protected final void sendCommand(String s, byte abyte0[])
    {
        byte abyte1[] = new byte[2];
        copyUWORDValueToBytes(abyte0.length, abyte1, 0);
        bluetooth.write(s, abyte1);
        bluetooth.write(s, abyte0);
    }

    protected final byte[] sendCommandAndReceiveReturnPackage(String s, byte abyte0[])
    {
        sendCommand(s, abyte0);
        return receiveReturnPackage(s);
    }

    protected final void setInputMode(String s, int i, int j, int k)
    {
        byte abyte0[] = new byte[5];
        abyte0[0] = -128;
        abyte0[1] = 5;
        copyUBYTEValueToBytes(i, abyte0, 2);
        copyUBYTEValueToBytes(j, abyte0, 3);
        copyUBYTEValueToBytes(k, abyte0, 4);
        sendCommand(s, abyte0);
    }

    protected final void setOutputState(String s, int i, int j, int k, int l, int i1, int j1, 
            long l1)
    {
        int k1 = sanitizePower(j);
        byte abyte0[] = new byte[12];
        abyte0[0] = -128;
        abyte0[1] = 4;
        copyUBYTEValueToBytes(i, abyte0, 2);
        copySBYTEValueToBytes(k1, abyte0, 3);
        copyUBYTEValueToBytes(k, abyte0, 4);
        copyUBYTEValueToBytes(l, abyte0, 5);
        copySBYTEValueToBytes(i1, abyte0, 6);
        copyUBYTEValueToBytes(j1, abyte0, 7);
        copyULONGValueToBytes(l1, abyte0, 8);
        sendCommand(s, abyte0);
    }

    static 
    {
        ERROR_MESSAGES = new HashMap();
        ERROR_MESSAGES.put(Integer.valueOf(32), "Pending communication transaction in progress");
        ERROR_MESSAGES.put(Integer.valueOf(64), "Specified mailbox queue is empty");
        ERROR_MESSAGES.put(Integer.valueOf(129), "No more handles");
        ERROR_MESSAGES.put(Integer.valueOf(130), "No space");
        ERROR_MESSAGES.put(Integer.valueOf(131), "No more files");
        ERROR_MESSAGES.put(Integer.valueOf(132), "End of file expected");
        ERROR_MESSAGES.put(Integer.valueOf(133), "End of file");
        ERROR_MESSAGES.put(Integer.valueOf(134), "Not a linear file");
        ERROR_MESSAGES.put(Integer.valueOf(135), "File not found");
        ERROR_MESSAGES.put(Integer.valueOf(136), "Handle already closed");
        ERROR_MESSAGES.put(Integer.valueOf(137), "No linear space");
        ERROR_MESSAGES.put(Integer.valueOf(138), "Undefined error");
        ERROR_MESSAGES.put(Integer.valueOf(139), "File is busy");
        ERROR_MESSAGES.put(Integer.valueOf(140), "No write buffers");
        ERROR_MESSAGES.put(Integer.valueOf(141), "Append not possible");
        ERROR_MESSAGES.put(Integer.valueOf(142), "File is full");
        ERROR_MESSAGES.put(Integer.valueOf(143), "File exists");
        ERROR_MESSAGES.put(Integer.valueOf(144), "Module not found");
        ERROR_MESSAGES.put(Integer.valueOf(145), "Out of boundary");
        ERROR_MESSAGES.put(Integer.valueOf(146), "Illegal file name");
        ERROR_MESSAGES.put(Integer.valueOf(147), "Illegal handle");
        ERROR_MESSAGES.put(Integer.valueOf(189), "Request failed (i.e. specified file not found)");
        ERROR_MESSAGES.put(Integer.valueOf(190), "Unknown command opcode");
        ERROR_MESSAGES.put(Integer.valueOf(191), "Insane packet");
        ERROR_MESSAGES.put(Integer.valueOf(192), "Data contains out-of-range values");
        ERROR_MESSAGES.put(Integer.valueOf(221), "Communication bus error");
        ERROR_MESSAGES.put(Integer.valueOf(222), "No free memory in communication buffer");
        ERROR_MESSAGES.put(Integer.valueOf(223), "Specified channel/connection is not valid");
        ERROR_MESSAGES.put(Integer.valueOf(224), "Specified channel/connection not configured or busy");
        ERROR_MESSAGES.put(Integer.valueOf(236), "No active program");
        ERROR_MESSAGES.put(Integer.valueOf(237), "Illegal size specified");
        ERROR_MESSAGES.put(Integer.valueOf(238), "Illegal mailbox queue ID specified");
        ERROR_MESSAGES.put(Integer.valueOf(239), "Attempted to access invalid field of a structure");
        ERROR_MESSAGES.put(Integer.valueOf(240), "Bad input or output specified");
        ERROR_MESSAGES.put(Integer.valueOf(251), "Insufficient memory available");
        ERROR_MESSAGES.put(Integer.valueOf(255), "Bad arguments");
    }
}
