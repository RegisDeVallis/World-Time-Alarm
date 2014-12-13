// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.appinventor.components.runtime:
//            LegoMindstormsNxtBase, Form, ComponentContainer

public class NxtDirectCommands extends LegoMindstormsNxtBase
{

    public NxtDirectCommands(ComponentContainer componentcontainer)
    {
        super(componentcontainer, "NxtDirectCommands");
    }

    private void closeHandle(String s, int i)
    {
        byte abyte0[] = new byte[3];
        abyte0[0] = 1;
        abyte0[1] = -124;
        copyUBYTEValueToBytes(i, abyte0, 2);
        evaluateStatus(s, sendCommandAndReceiveReturnPackage(s, abyte0), abyte0[1]);
    }

    private byte[] getOutputState(String s, int i)
    {
        byte abyte0[] = new byte[3];
        abyte0[0] = 0;
        abyte0[1] = 6;
        copyUBYTEValueToBytes(i, abyte0, 2);
        byte abyte1[] = sendCommandAndReceiveReturnPackage(s, abyte0);
        if (evaluateStatus(s, abyte1, abyte0[1]))
        {
            if (abyte1.length == 25)
            {
                return abyte1;
            }
            Log.w(logTag, (new StringBuilder()).append(s).append(": unexpected return package length ").append(abyte1.length).append(" (expected 25)").toString());
        }
        return null;
    }

    private Integer openWrite(String s, String s1, long l)
    {
        byte abyte0[] = new byte[26];
        abyte0[0] = 1;
        abyte0[1] = -127;
        copyStringValueToBytes(s1, abyte0, 2, 19);
        copyULONGValueToBytes(l, abyte0, 22);
        byte abyte1[] = sendCommandAndReceiveReturnPackage(s, abyte0);
        if (evaluateStatus(s, abyte1, abyte0[1]))
        {
            if (abyte1.length == 4)
            {
                return Integer.valueOf(getUBYTEValueFromBytes(abyte1, 3));
            }
            Log.w(logTag, (new StringBuilder()).append(s).append(": unexpected return package length ").append(abyte1.length).append(" (expected 4)").toString());
        }
        return null;
    }

    private Integer openWriteLinear(String s, String s1, long l)
    {
        byte abyte0[] = new byte[26];
        abyte0[0] = 1;
        abyte0[1] = -119;
        copyStringValueToBytes(s1, abyte0, 2, 19);
        copyULONGValueToBytes(l, abyte0, 22);
        byte abyte1[] = sendCommandAndReceiveReturnPackage(s, abyte0);
        if (evaluateStatus(s, abyte1, abyte0[1]))
        {
            if (abyte1.length == 4)
            {
                return Integer.valueOf(getUBYTEValueFromBytes(abyte1, 3));
            }
            Log.w(logTag, (new StringBuilder()).append(s).append(": unexpected return package length ").append(abyte1.length).append(" (expected 4)").toString());
        }
        return null;
    }

    private int writeChunk(String s, int i, byte abyte0[], int j)
        throws IOException
    {
        if (j > 32)
        {
            throw new IllegalArgumentException("length must be <= 32");
        }
        byte abyte1[] = new byte[j + 3];
        abyte1[0] = 1;
        abyte1[1] = -125;
        copyUBYTEValueToBytes(i, abyte1, 2);
        System.arraycopy(abyte0, 0, abyte1, 3, j);
        byte abyte2[] = sendCommandAndReceiveReturnPackage(s, abyte1);
        boolean flag = evaluateStatus(s, abyte2, abyte1[1]);
        int k = 0;
        if (flag)
        {
            if (abyte2.length == 6)
            {
                k = getUWORDValueFromBytes(abyte2, 4);
                if (k != j)
                {
                    Log.e(logTag, (new StringBuilder()).append(s).append(": only ").append(k).append(" bytes were written ").append("(expected ").append(j).append(")").toString());
                    throw new IOException("Unable to write file on robot");
                }
            } else
            {
                Log.w(logTag, (new StringBuilder()).append(s).append(": unexpected return package length ").append(abyte2.length).append(" (expected 6)").toString());
            }
        }
        return k;
    }

    public void DeleteFile(String s)
    {
        if (!checkBluetooth("DeleteFile"))
        {
            return;
        }
        if (s.length() == 0)
        {
            form.dispatchErrorOccurredEvent(this, "DeleteFile", 406, new Object[0]);
            return;
        } else
        {
            byte abyte0[] = new byte[22];
            abyte0[0] = 1;
            abyte0[1] = -123;
            copyStringValueToBytes(s, abyte0, 2, 19);
            evaluateStatus("DeleteFile", sendCommandAndReceiveReturnPackage("DeleteFile", abyte0), abyte0[1]);
            return;
        }
    }

    public void DownloadFile(String s, String s1)
    {
        File file;
        BufferedInputStream bufferedinputstream;
        long l;
        Integer integer1;
        if (!checkBluetooth("DownloadFile"))
        {
            return;
        }
        if (s.length() == 0)
        {
            this.form.dispatchErrorOccurredEvent(this, "DownloadFile", 414, new Object[0]);
            return;
        }
        if (s1.length() == 0)
        {
            this.form.dispatchErrorOccurredEvent(this, "DownloadFile", 415, new Object[0]);
            return;
        }
        Integer integer;
        try
        {
            file = MediaUtil.copyMediaToTempFile(this.form, s);
        }
        catch (IOException ioexception)
        {
            Form form = this.form;
            Object aobj[] = new Object[1];
            aobj[0] = ioexception.getMessage();
            form.dispatchErrorOccurredEvent(this, "DownloadFile", 416, aobj);
            return;
        }
        bufferedinputstream = new BufferedInputStream(new FileInputStream(file), 1024);
        l = file.length();
        if (!s1.endsWith(".rxe") && !s1.endsWith(".ric")) goto _L2; else goto _L1
_L1:
        integer = openWriteLinear("DownloadFile", s1, l);
        integer1 = integer;
_L4:
        if (integer1 != null)
        {
            break; /* Loop/switch isn't completed */
        }
        bufferedinputstream.close();
        file.delete();
        return;
_L2:
        Integer integer2 = openWrite("DownloadFile", s1, l);
        integer1 = integer2;
        if (true) goto _L4; else goto _L3
_L3:
        byte abyte0[] = new byte[32];
        long l1 = 0L;
_L6:
        if (l1 >= l)
        {
            break; /* Loop/switch isn't completed */
        }
        int j;
        int i = (int)Math.min(32L, l - l1);
        bufferedinputstream.read(abyte0, 0, i);
        j = writeChunk("DownloadFile", integer1.intValue(), abyte0, i);
        l1 += j;
        if (true) goto _L6; else goto _L5
_L5:
        closeHandle("DownloadFile", integer1.intValue());
        bufferedinputstream.close();
        file.delete();
        return;
        Exception exception2;
        exception2;
        closeHandle("DownloadFile", integer1.intValue());
        throw exception2;
        Exception exception;
        exception;
        bufferedinputstream.close();
        throw exception;
        Exception exception1;
        exception1;
        file.delete();
        throw exception1;
    }

    public int GetBatteryLevel()
    {
        if (checkBluetooth("GetBatteryLevel"))
        {
            byte abyte0[] = {
                0, 11
            };
            byte abyte1[] = sendCommandAndReceiveReturnPackage("GetBatteryLevel", abyte0);
            if (evaluateStatus("GetBatteryLevel", abyte1, abyte0[1]))
            {
                if (abyte1.length == 5)
                {
                    return getUWORDValueFromBytes(abyte1, 3);
                } else
                {
                    Log.w(logTag, (new StringBuilder()).append("GetBatteryLevel: unexpected return package length ").append(abyte1.length).append(" (expected 5)").toString());
                    return 0;
                }
            }
        }
        return 0;
    }

    public String GetBrickName()
    {
        if (!checkBluetooth("GetBrickName"))
        {
            return "";
        }
        byte abyte0[] = {
            1, -101
        };
        byte abyte1[] = sendCommandAndReceiveReturnPackage("GetBrickName", abyte0);
        if (evaluateStatus("GetBrickName", abyte1, abyte0[1]))
        {
            return getStringValueFromBytes(abyte1, 3);
        } else
        {
            return "";
        }
    }

    public String GetCurrentProgramName()
    {
        if (!checkBluetooth("GetCurrentProgramName"))
        {
            return "";
        }
        byte abyte0[] = {
            0, 17
        };
        byte abyte1[] = sendCommandAndReceiveReturnPackage("GetCurrentProgramName", abyte0);
        int i = getStatus("GetCurrentProgramName", abyte1, abyte0[1]);
        if (i == 0)
        {
            return getStringValueFromBytes(abyte1, 3);
        }
        if (i == 236)
        {
            return "";
        } else
        {
            evaluateStatus("GetCurrentProgramName", abyte1, abyte0[1]);
            return "";
        }
    }

    public List GetFirmwareVersion()
    {
        if (!checkBluetooth("GetFirmwareVersion"))
        {
            return new ArrayList();
        }
        byte abyte0[] = {
            1, -120
        };
        byte abyte1[] = sendCommandAndReceiveReturnPackage("GetFirmwareVersion", abyte0);
        if (evaluateStatus("GetFirmwareVersion", abyte1, abyte0[1]))
        {
            ArrayList arraylist = new ArrayList();
            arraylist.add((new StringBuilder()).append(abyte1[6]).append(".").append(abyte1[5]).toString());
            arraylist.add((new StringBuilder()).append(abyte1[4]).append(".").append(abyte1[3]).toString());
            return arraylist;
        } else
        {
            return new ArrayList();
        }
    }

    public List GetInputValues(String s)
    {
        if (!checkBluetooth("GetInputValues"))
        {
            return new ArrayList();
        }
        int i;
        byte abyte0[];
        try
        {
            i = convertSensorPortLetterToNumber(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            form.dispatchErrorOccurredEvent(this, "GetInputValues", 408, new Object[] {
                s
            });
            return new ArrayList();
        }
        abyte0 = getInputValues("GetInputValues", i);
        if (abyte0 != null)
        {
            ArrayList arraylist = new ArrayList();
            arraylist.add(Boolean.valueOf(getBooleanValueFromBytes(abyte0, 4)));
            arraylist.add(Boolean.valueOf(getBooleanValueFromBytes(abyte0, 5)));
            arraylist.add(Integer.valueOf(getUBYTEValueFromBytes(abyte0, 6)));
            arraylist.add(Integer.valueOf(getUBYTEValueFromBytes(abyte0, 7)));
            arraylist.add(Integer.valueOf(getUWORDValueFromBytes(abyte0, 8)));
            arraylist.add(Integer.valueOf(getUWORDValueFromBytes(abyte0, 10)));
            arraylist.add(Integer.valueOf(getSWORDValueFromBytes(abyte0, 12)));
            arraylist.add(Integer.valueOf(getSWORDValueFromBytes(abyte0, 14)));
            return arraylist;
        } else
        {
            return new ArrayList();
        }
    }

    public List GetOutputState(String s)
    {
        if (!checkBluetooth("GetOutputState"))
        {
            return new ArrayList();
        }
        int i;
        byte abyte0[];
        try
        {
            i = convertMotorPortLetterToNumber(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            form.dispatchErrorOccurredEvent(this, "GetOutputState", 407, new Object[] {
                s
            });
            return new ArrayList();
        }
        abyte0 = getOutputState("GetOutputState", i);
        if (abyte0 != null)
        {
            ArrayList arraylist = new ArrayList();
            arraylist.add(Integer.valueOf(getSBYTEValueFromBytes(abyte0, 4)));
            arraylist.add(Integer.valueOf(getUBYTEValueFromBytes(abyte0, 5)));
            arraylist.add(Integer.valueOf(getUBYTEValueFromBytes(abyte0, 6)));
            arraylist.add(Integer.valueOf(getSBYTEValueFromBytes(abyte0, 7)));
            arraylist.add(Integer.valueOf(getUBYTEValueFromBytes(abyte0, 8)));
            arraylist.add(Long.valueOf(getULONGValueFromBytes(abyte0, 9)));
            arraylist.add(Integer.valueOf(getSLONGValueFromBytes(abyte0, 13)));
            arraylist.add(Integer.valueOf(getSLONGValueFromBytes(abyte0, 17)));
            arraylist.add(Integer.valueOf(getSLONGValueFromBytes(abyte0, 21)));
            return arraylist;
        } else
        {
            return new ArrayList();
        }
    }

    public long KeepAlive()
    {
        if (checkBluetooth("KeepAlive"))
        {
            byte abyte0[] = {
                0, 13
            };
            byte abyte1[] = sendCommandAndReceiveReturnPackage("KeepAlive", abyte0);
            if (evaluateStatus("KeepAlive", abyte1, abyte0[1]))
            {
                if (abyte1.length == 7)
                {
                    return getULONGValueFromBytes(abyte1, 3);
                } else
                {
                    Log.w(logTag, (new StringBuilder()).append("KeepAlive: unexpected return package length ").append(abyte1.length).append(" (expected 7)").toString());
                    return 0L;
                }
            }
        }
        return 0L;
    }

    public List ListFiles(String s)
    {
        ArrayList arraylist;
        if (!checkBluetooth("ListFiles"))
        {
            arraylist = new ArrayList();
        } else
        {
            arraylist = new ArrayList();
            if (s.length() == 0)
            {
                s = "*.*";
            }
            byte abyte0[] = new byte[22];
            abyte0[0] = 1;
            abyte0[1] = -122;
            copyStringValueToBytes(s, abyte0, 2, 19);
            byte abyte1[] = sendCommandAndReceiveReturnPackage("ListFiles", abyte0);
            int i = getStatus("ListFiles", abyte1, abyte0[1]);
            while (i == 0) 
            {
                int j = getUBYTEValueFromBytes(abyte1, 3);
                arraylist.add(getStringValueFromBytes(abyte1, 4));
                byte abyte2[] = new byte[3];
                abyte2[0] = 1;
                abyte2[1] = -121;
                copyUBYTEValueToBytes(j, abyte2, 2);
                abyte1 = sendCommandAndReceiveReturnPackage("ListFiles", abyte2);
                i = getStatus("ListFiles", abyte1, abyte2[1]);
            }
        }
        return arraylist;
    }

    public int LsGetStatus(String s)
    {
        if (!checkBluetooth("LsGetStatus"))
        {
            return 0;
        }
        int i;
        try
        {
            i = convertSensorPortLetterToNumber(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            form.dispatchErrorOccurredEvent(this, "LsGetStatus", 408, new Object[] {
                s
            });
            return 0;
        }
        return lsGetStatus("LsGetStatus", i);
    }

    public List LsRead(String s)
    {
        if (checkBluetooth("LsRead")) goto _L2; else goto _L1
_L1:
        ArrayList arraylist = new ArrayList();
_L4:
        return arraylist;
_L2:
        int i;
        byte abyte0[];
        try
        {
            i = convertSensorPortLetterToNumber(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            form.dispatchErrorOccurredEvent(this, "LsRead", 408, new Object[] {
                s
            });
            return new ArrayList();
        }
        abyte0 = lsRead("LsRead", i);
        if (abyte0 != null)
        {
            arraylist = new ArrayList();
            int j = getUBYTEValueFromBytes(abyte0, 3);
            int k = 0;
            while (k < j) 
            {
                arraylist.add(Integer.valueOf(0xff & abyte0[k + 4]));
                k++;
            }
        } else
        {
            return new ArrayList();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public void LsWrite(String s, YailList yaillist, int i)
    {
        if (!checkBluetooth("LsWrite"))
        {
            return;
        }
        int j;
        try
        {
            j = convertSensorPortLetterToNumber(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            this.form.dispatchErrorOccurredEvent(this, "LsWrite", 408, new Object[] {
                s
            });
            return;
        }
        if (yaillist.size() > 16)
        {
            this.form.dispatchErrorOccurredEvent(this, "LsWrite", 411, new Object[0]);
            return;
        }
        Object aobj[] = yaillist.toArray();
        byte abyte0[] = new byte[aobj.length];
        NumberFormatException numberformatexception;
        Form form;
        Object aobj1[];
        for (int k = 0; k < aobj.length; k++)
        {
            String s1 = aobj[k].toString();
            int l;
            int i1;
            try
            {
                l = Integer.decode(s1).intValue();
            }
            // Misplaced declaration of an exception variable
            catch (NumberFormatException numberformatexception)
            {
                form = this.form;
                aobj1 = new Object[1];
                aobj1[0] = Integer.valueOf(k + 1);
                form.dispatchErrorOccurredEvent(this, "LsWrite", 412, aobj1);
                return;
            }
            abyte0[k] = (byte)(l & 0xff);
            i1 = l >> 8;
            if (i1 != 0 && i1 != -1)
            {
                Form form1 = this.form;
                Object aobj2[] = new Object[1];
                aobj2[0] = Integer.valueOf(k + 1);
                form1.dispatchErrorOccurredEvent(this, "LsWrite", 413, aobj2);
                return;
            }
        }

        lsWrite("LsWrite", j, abyte0, i);
    }

    public String MessageRead(int i)
    {
        if (!checkBluetooth("MessageRead"))
        {
            return "";
        }
        if (i < 1 || i > 10)
        {
            Form form = this.form;
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            form.dispatchErrorOccurredEvent(this, "MessageRead", 409, aobj);
            return "";
        }
        int j = i - 1;
        byte abyte0[] = new byte[5];
        abyte0[0] = 0;
        abyte0[1] = 19;
        copyUBYTEValueToBytes(0, abyte0, 2);
        copyUBYTEValueToBytes(j, abyte0, 3);
        copyBooleanValueToBytes(true, abyte0, 4);
        byte abyte1[] = sendCommandAndReceiveReturnPackage("MessageRead", abyte0);
        if (evaluateStatus("MessageRead", abyte1, abyte0[1]))
        {
            if (abyte1.length == 64)
            {
                int k = getUBYTEValueFromBytes(abyte1, 3);
                if (k != j)
                {
                    Log.w(logTag, (new StringBuilder()).append("MessageRead: unexpected return mailbox: ").append(k).append(" (expected ").append(j).append(")").toString());
                }
                return getStringValueFromBytes(abyte1, 5, -1 + getUBYTEValueFromBytes(abyte1, 4));
            }
            Log.w(logTag, (new StringBuilder()).append("MessageRead: unexpected return package length ").append(abyte1.length).append(" (expected 64)").toString());
        }
        return "";
    }

    public void MessageWrite(int i, String s)
    {
        if (!checkBluetooth("MessageWrite"))
        {
            return;
        }
        if (i < 1 || i > 10)
        {
            Form form = this.form;
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            form.dispatchErrorOccurredEvent(this, "MessageWrite", 409, aobj);
            return;
        }
        int j = s.length();
        if (j > 58)
        {
            this.form.dispatchErrorOccurredEvent(this, "MessageWrite", 410, new Object[0]);
            return;
        } else
        {
            int k = i - 1;
            byte abyte0[] = new byte[1 + (j + 4)];
            abyte0[0] = -128;
            abyte0[1] = 9;
            copyUBYTEValueToBytes(k, abyte0, 2);
            copyUBYTEValueToBytes(j + 1, abyte0, 3);
            copyStringValueToBytes(s, abyte0, 4, j);
            sendCommand("MessageWrite", abyte0);
            return;
        }
    }

    public void PlaySoundFile(String s)
    {
        if (!checkBluetooth("PlaySoundFile"))
        {
            return;
        }
        if (s.length() == 0)
        {
            form.dispatchErrorOccurredEvent(this, "PlaySoundFile", 406, new Object[0]);
            return;
        }
        if (s.indexOf(".") == -1)
        {
            s = (new StringBuilder()).append(s).append(".rso").toString();
        }
        byte abyte0[] = new byte[23];
        abyte0[0] = -128;
        abyte0[1] = 2;
        copyBooleanValueToBytes(false, abyte0, 2);
        copyStringValueToBytes(s, abyte0, 3, 19);
        sendCommand("PlaySoundFile", abyte0);
    }

    public void PlayTone(int i, int j)
    {
        if (!checkBluetooth("PlayTone"))
        {
            return;
        }
        if (i < 200)
        {
            Log.w(logTag, (new StringBuilder()).append("frequencyHz ").append(i).append(" is invalid, using 200.").toString());
            i = 200;
        }
        if (i > 14000)
        {
            Log.w(logTag, (new StringBuilder()).append("frequencyHz ").append(i).append(" is invalid, using 14000.").toString());
            i = 14000;
        }
        byte abyte0[] = new byte[6];
        abyte0[0] = -128;
        abyte0[1] = 3;
        copyUWORDValueToBytes(i, abyte0, 2);
        copyUWORDValueToBytes(j, abyte0, 4);
        sendCommand("PlayTone", abyte0);
    }

    public void ResetInputScaledValue(String s)
    {
        if (!checkBluetooth("ResetInputScaledValue"))
        {
            return;
        }
        int i;
        byte abyte0[];
        try
        {
            i = convertSensorPortLetterToNumber(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            form.dispatchErrorOccurredEvent(this, "ResetInputScaledValue", 408, new Object[] {
                s
            });
            return;
        }
        resetInputScaledValue("ResetInputScaledValue", i);
        abyte0 = new byte[3];
        abyte0[0] = -128;
        abyte0[1] = 8;
        copyUBYTEValueToBytes(i, abyte0, 2);
        sendCommand("ResetInputScaledValue", abyte0);
    }

    public void ResetMotorPosition(String s, boolean flag)
    {
        if (!checkBluetooth("ResetMotorPosition"))
        {
            return;
        }
        int i;
        byte abyte0[];
        try
        {
            i = convertMotorPortLetterToNumber(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            form.dispatchErrorOccurredEvent(this, "ResetMotorPosition", 407, new Object[] {
                s
            });
            return;
        }
        abyte0 = new byte[4];
        abyte0[0] = -128;
        abyte0[1] = 10;
        copyUBYTEValueToBytes(i, abyte0, 2);
        copyBooleanValueToBytes(flag, abyte0, 3);
        sendCommand("ResetMotorPosition", abyte0);
    }

    public void SetBrickName(String s)
    {
        if (!checkBluetooth("SetBrickName"))
        {
            return;
        } else
        {
            byte abyte0[] = new byte[18];
            abyte0[0] = 1;
            abyte0[1] = -104;
            copyStringValueToBytes(s, abyte0, 2, 15);
            evaluateStatus("SetBrickName", sendCommandAndReceiveReturnPackage("SetBrickName", abyte0), abyte0[1]);
            return;
        }
    }

    public void SetInputMode(String s, int i, int j)
    {
        if (!checkBluetooth("SetInputMode"))
        {
            return;
        }
        int k;
        try
        {
            k = convertSensorPortLetterToNumber(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            form.dispatchErrorOccurredEvent(this, "SetInputMode", 408, new Object[] {
                s
            });
            return;
        }
        setInputMode("SetInputMode", k, i, j);
    }

    public void SetOutputState(String s, int i, int j, int k, int l, int i1, long l1)
    {
        if (!checkBluetooth("SetOutputState"))
        {
            return;
        }
        int j1;
        try
        {
            j1 = convertMotorPortLetterToNumber(s);
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            form.dispatchErrorOccurredEvent(this, "SetOutputState", 407, new Object[] {
                s
            });
            return;
        }
        setOutputState("SetOutputState", j1, i, j, k, sanitizeTurnRatio(l), i1, l1);
    }

    public void StartProgram(String s)
    {
        if (!checkBluetooth("StartProgram"))
        {
            return;
        }
        if (s.length() == 0)
        {
            form.dispatchErrorOccurredEvent(this, "StartProgram", 405, new Object[0]);
            return;
        }
        if (s.indexOf(".") == -1)
        {
            s = (new StringBuilder()).append(s).append(".rxe").toString();
        }
        byte abyte0[] = new byte[22];
        abyte0[0] = -128;
        abyte0[1] = 0;
        copyStringValueToBytes(s, abyte0, 2, 19);
        sendCommand("StartProgram", abyte0);
    }

    public void StopProgram()
    {
        if (!checkBluetooth("StopProgram"))
        {
            return;
        } else
        {
            sendCommand("StopProgram", new byte[] {
                -128, 1
            });
            return;
        }
    }

    public void StopSoundPlayback()
    {
        if (!checkBluetooth("StopSoundPlayback"))
        {
            return;
        } else
        {
            sendCommand("StopSoundPlayback", new byte[] {
                -128, 12
            });
            return;
        }
    }
}
