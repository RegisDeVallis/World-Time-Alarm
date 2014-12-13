// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.appinventor.components.runtime;

import android.util.Log;
import com.google.appinventor.components.runtime.util.BluetoothReflection;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.appinventor.components.runtime:
//            AndroidNonvisibleComponent, Component, OnDestroyListener, Deleteable, 
//            ComponentContainer, Form, BluetoothConnectionListener

public abstract class BluetoothConnectionBase extends AndroidNonvisibleComponent
    implements Component, OnDestroyListener, Deleteable
{

    private final List bluetoothConnectionListeners;
    private ByteOrder byteOrder;
    private Object connectedBluetoothSocket;
    private byte delimiter;
    private String encoding;
    private InputStream inputStream;
    protected final String logTag;
    private OutputStream outputStream;
    protected boolean secure;

    protected BluetoothConnectionBase(ComponentContainer componentcontainer, String s)
    {
        this(componentcontainer.$form(), s);
        form.registerForOnDestroy(this);
    }

    private BluetoothConnectionBase(Form form, String s)
    {
        super(form);
        bluetoothConnectionListeners = new ArrayList();
        logTag = s;
        HighByteFirst(false);
        CharacterEncoding("UTF-8");
        DelimiterByte(0);
        Secure(true);
    }

    protected BluetoothConnectionBase(OutputStream outputstream, InputStream inputstream)
    {
        this((Form)null, (String)null);
        connectedBluetoothSocket = "Not Null";
        outputStream = outputstream;
        inputStream = inputstream;
    }

    private void fireAfterConnectEvent()
    {
        for (Iterator iterator = bluetoothConnectionListeners.iterator(); iterator.hasNext(); ((BluetoothConnectionListener)iterator.next()).afterConnect(this)) { }
    }

    private void fireBeforeDisconnectEvent()
    {
        for (Iterator iterator = bluetoothConnectionListeners.iterator(); iterator.hasNext(); ((BluetoothConnectionListener)iterator.next()).beforeDisconnect(this)) { }
    }

    private void prepareToDie()
    {
        if (connectedBluetoothSocket != null)
        {
            Disconnect();
        }
    }

    public boolean Available()
    {
        return BluetoothReflection.getBluetoothAdapter() != null;
    }

    public void BluetoothError(String s, String s1)
    {
    }

    public int BytesAvailableToReceive()
    {
        if (!IsConnected())
        {
            bluetoothError("BytesAvailableToReceive", 515, new Object[0]);
            return 0;
        }
        int i;
        try
        {
            i = inputStream.available();
        }
        catch (IOException ioexception)
        {
            Object aobj[] = new Object[1];
            aobj[0] = ioexception.getMessage();
            bluetoothError("BytesAvailableToReceive", 517, aobj);
            return 0;
        }
        return i;
    }

    public String CharacterEncoding()
    {
        return encoding;
    }

    public void CharacterEncoding(String s)
    {
        try
        {
            "check".getBytes(s);
            encoding = s;
            return;
        }
        catch (UnsupportedEncodingException unsupportedencodingexception)
        {
            bluetoothError("CharacterEncoding", 519, new Object[] {
                s
            });
        }
    }

    public int DelimiterByte()
    {
        return delimiter;
    }

    public void DelimiterByte(int i)
    {
        byte byte0 = (byte)i;
        int j = i >> 8;
        if (j != 0 && j != -1)
        {
            Object aobj[] = new Object[1];
            aobj[0] = Integer.valueOf(i);
            bluetoothError("DelimiterByte", 511, aobj);
            return;
        } else
        {
            delimiter = byte0;
            return;
        }
    }

    public final void Disconnect()
    {
        if (connectedBluetoothSocket != null)
        {
            fireBeforeDisconnectEvent();
            try
            {
                BluetoothReflection.closeBluetoothSocket(connectedBluetoothSocket);
                Log.i(logTag, "Disconnected from Bluetooth device.");
            }
            catch (IOException ioexception)
            {
                Log.w(logTag, (new StringBuilder()).append("Error while disconnecting: ").append(ioexception.getMessage()).toString());
            }
            connectedBluetoothSocket = null;
        }
        inputStream = null;
        outputStream = null;
    }

    public boolean Enabled()
    {
        Object obj = BluetoothReflection.getBluetoothAdapter();
        return obj != null && BluetoothReflection.isBluetoothEnabled(obj);
    }

    public void HighByteFirst(boolean flag)
    {
        ByteOrder byteorder;
        if (flag)
        {
            byteorder = ByteOrder.BIG_ENDIAN;
        } else
        {
            byteorder = ByteOrder.LITTLE_ENDIAN;
        }
        byteOrder = byteorder;
    }

    public boolean HighByteFirst()
    {
        return byteOrder == ByteOrder.BIG_ENDIAN;
    }

    public final void Initialize()
    {
    }

    public final boolean IsConnected()
    {
        return connectedBluetoothSocket != null;
    }

    public int ReceiveSigned1ByteNumber()
    {
        byte abyte0[] = read("ReceiveSigned1ByteNumber", 1);
        if (abyte0.length != 1)
        {
            return 0;
        } else
        {
            return abyte0[0];
        }
    }

    public int ReceiveSigned2ByteNumber()
    {
        byte abyte0[] = read("ReceiveSigned2ByteNumber", 2);
        if (abyte0.length != 2)
        {
            return 0;
        }
        if (byteOrder == ByteOrder.BIG_ENDIAN)
        {
            return 0xff & abyte0[1] | abyte0[0] << 8;
        } else
        {
            return 0xff & abyte0[0] | abyte0[1] << 8;
        }
    }

    public long ReceiveSigned4ByteNumber()
    {
        byte abyte0[] = read("ReceiveSigned4ByteNumber", 4);
        if (abyte0.length != 4)
        {
            return 0L;
        }
        if (byteOrder == ByteOrder.BIG_ENDIAN)
        {
            return (long)(0xff & abyte0[3] | (0xff & abyte0[2]) << 8 | (0xff & abyte0[1]) << 16 | abyte0[0] << 24);
        } else
        {
            return (long)(0xff & abyte0[0] | (0xff & abyte0[1]) << 8 | (0xff & abyte0[2]) << 16 | abyte0[3] << 24);
        }
    }

    public List ReceiveSignedBytes(int i)
    {
        byte abyte0[] = read("ReceiveSignedBytes", i);
        ArrayList arraylist = new ArrayList();
        for (int j = 0; j < abyte0.length; j++)
        {
            arraylist.add(Integer.valueOf(abyte0[j]));
        }

        return arraylist;
    }

    public String ReceiveText(int i)
    {
        byte abyte0[];
        abyte0 = read("ReceiveText", i);
        if (i >= 0)
        {
            break MISSING_BLOCK_LABEL_31;
        }
        return new String(abyte0, 0, -1 + abyte0.length, encoding);
        String s = new String(abyte0, encoding);
        return s;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        Log.w(logTag, (new StringBuilder()).append("UnsupportedEncodingException: ").append(unsupportedencodingexception.getMessage()).toString());
        return new String(abyte0);
    }

    public int ReceiveUnsigned1ByteNumber()
    {
        byte abyte0[] = read("ReceiveUnsigned1ByteNumber", 1);
        if (abyte0.length != 1)
        {
            return 0;
        } else
        {
            return 0xff & abyte0[0];
        }
    }

    public int ReceiveUnsigned2ByteNumber()
    {
        byte abyte0[] = read("ReceiveUnsigned2ByteNumber", 2);
        if (abyte0.length != 2)
        {
            return 0;
        }
        if (byteOrder == ByteOrder.BIG_ENDIAN)
        {
            return 0xff & abyte0[1] | (0xff & abyte0[0]) << 8;
        } else
        {
            return 0xff & abyte0[0] | (0xff & abyte0[1]) << 8;
        }
    }

    public long ReceiveUnsigned4ByteNumber()
    {
        byte abyte0[] = read("ReceiveUnsigned4ByteNumber", 4);
        if (abyte0.length != 4)
        {
            return 0L;
        }
        if (byteOrder == ByteOrder.BIG_ENDIAN)
        {
            return 255L & (long)abyte0[3] | (255L & (long)abyte0[2]) << 8 | (255L & (long)abyte0[1]) << 16 | (255L & (long)abyte0[0]) << 24;
        } else
        {
            return 255L & (long)abyte0[0] | (255L & (long)abyte0[1]) << 8 | (255L & (long)abyte0[2]) << 16 | (255L & (long)abyte0[3]) << 24;
        }
    }

    public List ReceiveUnsignedBytes(int i)
    {
        byte abyte0[] = read("ReceiveUnsignedBytes", i);
        ArrayList arraylist = new ArrayList();
        for (int j = 0; j < abyte0.length; j++)
        {
            arraylist.add(Integer.valueOf(0xff & abyte0[j]));
        }

        return arraylist;
    }

    public void Secure(boolean flag)
    {
        secure = flag;
    }

    public boolean Secure()
    {
        return secure;
    }

    public void Send1ByteNumber(String s)
    {
        int i;
        byte byte0;
        int j;
        try
        {
            i = Integer.decode(s).intValue();
        }
        catch (NumberFormatException numberformatexception)
        {
            bluetoothError("Send1ByteNumber", 510, new Object[] {
                s
            });
            return;
        }
        byte0 = (byte)i;
        j = i >> 8;
        if (j != 0 && j != -1)
        {
            bluetoothError("Send1ByteNumber", 511, new Object[] {
                s
            });
            return;
        } else
        {
            write("Send1ByteNumber", byte0);
            return;
        }
    }

    public void Send2ByteNumber(String s)
    {
        int i;
        byte abyte0[];
        int j;
        int k;
        try
        {
            i = Integer.decode(s).intValue();
        }
        catch (NumberFormatException numberformatexception)
        {
            bluetoothError("Send2ByteNumber", 510, new Object[] {
                s
            });
            return;
        }
        abyte0 = new byte[2];
        if (byteOrder == ByteOrder.BIG_ENDIAN)
        {
            abyte0[1] = (byte)(i & 0xff);
            j = i >> 8;
            abyte0[0] = (byte)(j & 0xff);
        } else
        {
            abyte0[0] = (byte)(i & 0xff);
            j = i >> 8;
            abyte0[1] = (byte)(j & 0xff);
        }
        k = j >> 8;
        if (k != 0 && k != -1)
        {
            Object aobj[] = new Object[2];
            aobj[0] = s;
            aobj[1] = Integer.valueOf(2);
            bluetoothError("Send2ByteNumber", 512, aobj);
            return;
        } else
        {
            write("Send2ByteNumber", abyte0);
            return;
        }
    }

    public void Send4ByteNumber(String s)
    {
        long l;
        byte abyte0[];
        long l3;
        long l4;
        try
        {
            l = Long.decode(s).longValue();
        }
        catch (NumberFormatException numberformatexception)
        {
            bluetoothError("Send4ByteNumber", 510, new Object[] {
                s
            });
            return;
        }
        abyte0 = new byte[4];
        if (byteOrder == ByteOrder.BIG_ENDIAN)
        {
            abyte0[3] = (byte)(int)(l & 255L);
            long l5 = l >> 8;
            abyte0[2] = (byte)(int)(l5 & 255L);
            long l6 = l5 >> 8;
            abyte0[1] = (byte)(int)(l6 & 255L);
            l3 = l6 >> 8;
            abyte0[0] = (byte)(int)(l3 & 255L);
        } else
        {
            abyte0[0] = (byte)(int)(l & 255L);
            long l1 = l >> 8;
            abyte0[1] = (byte)(int)(l1 & 255L);
            long l2 = l1 >> 8;
            abyte0[2] = (byte)(int)(l2 & 255L);
            l3 = l2 >> 8;
            abyte0[3] = (byte)(int)(l3 & 255L);
        }
        l4 = l3 >> 8;
        if (l4 != 0L && l4 != -1L)
        {
            Object aobj[] = new Object[2];
            aobj[0] = s;
            aobj[1] = Integer.valueOf(4);
            bluetoothError("Send4ByteNumber", 512, aobj);
            return;
        } else
        {
            write("Send4ByteNumber", abyte0);
            return;
        }
    }

    public void SendBytes(YailList yaillist)
    {
        Object aobj[] = yaillist.toArray();
        byte abyte0[] = new byte[aobj.length];
        NumberFormatException numberformatexception;
        Object aobj1[];
        for (int i = 0; i < aobj.length; i++)
        {
            String s = aobj[i].toString();
            int j;
            int k;
            try
            {
                j = Integer.decode(s).intValue();
            }
            // Misplaced declaration of an exception variable
            catch (NumberFormatException numberformatexception)
            {
                aobj1 = new Object[1];
                aobj1[0] = Integer.valueOf(i + 1);
                bluetoothError("SendBytes", 513, aobj1);
                return;
            }
            abyte0[i] = (byte)(j & 0xff);
            k = j >> 8;
            if (k != 0 && k != -1)
            {
                Object aobj2[] = new Object[1];
                aobj2[0] = Integer.valueOf(i + 1);
                bluetoothError("SendBytes", 514, aobj2);
                return;
            }
        }

        write("SendBytes", abyte0);
    }

    public void SendText(String s)
    {
        byte abyte1[] = s.getBytes(encoding);
        byte abyte0[] = abyte1;
_L2:
        write("SendText", abyte0);
        return;
        UnsupportedEncodingException unsupportedencodingexception;
        unsupportedencodingexception;
        Log.w(logTag, (new StringBuilder()).append("UnsupportedEncodingException: ").append(unsupportedencodingexception.getMessage()).toString());
        abyte0 = s.getBytes();
        if (true) goto _L2; else goto _L1
_L1:
    }

    void addBluetoothConnectionListener(BluetoothConnectionListener bluetoothconnectionlistener)
    {
        bluetoothConnectionListeners.add(bluetoothconnectionlistener);
    }

    protected transient void bluetoothError(String s, int i, Object aobj[])
    {
        form.dispatchErrorOccurredEvent(this, s, i, aobj);
    }

    public void onDelete()
    {
        prepareToDie();
    }

    public void onDestroy()
    {
        prepareToDie();
    }

    protected final byte[] read(String s, int i)
    {
        ByteArrayOutputStream bytearrayoutputstream;
        if (!IsConnected())
        {
            bluetoothError(s, 515, new Object[0]);
            return new byte[0];
        }
        bytearrayoutputstream = new ByteArrayOutputStream();
        if (i < 0) goto _L2; else goto _L1
_L1:
        byte abyte0[];
        int k;
        abyte0 = new byte[i];
        k = 0;
_L7:
        if (k >= i) goto _L4; else goto _L3
_L3:
        int l = inputStream.read(abyte0, k, abyte0.length - k);
        if (l != -1) goto _L6; else goto _L5
_L5:
        try
        {
            bluetoothError(s, 518, new Object[0]);
        }
        catch (IOException ioexception1)
        {
            Object aobj1[] = new Object[1];
            aobj1[0] = ioexception1.getMessage();
            bluetoothError(s, 517, aobj1);
        }
_L4:
        bytearrayoutputstream.write(abyte0, 0, k);
_L8:
        return bytearrayoutputstream.toByteArray();
_L6:
        k += l;
          goto _L7
_L2:
        int j = inputStream.read();
        if (j == -1)
        {
            try
            {
                bluetoothError(s, 518, new Object[0]);
            }
            catch (IOException ioexception)
            {
                Object aobj[] = new Object[1];
                aobj[0] = ioexception.getMessage();
                bluetoothError(s, 517, aobj);
            }
            break; /* Loop/switch isn't completed */
        }
        byte byte0;
        bytearrayoutputstream.write(j);
        byte0 = delimiter;
        if (j != byte0) goto _L2; else goto _L8
    }

    void removeBluetoothConnectionListener(BluetoothConnectionListener bluetoothconnectionlistener)
    {
        bluetoothConnectionListeners.remove(bluetoothconnectionlistener);
    }

    protected final void setConnection(Object obj)
        throws IOException
    {
        connectedBluetoothSocket = obj;
        inputStream = new BufferedInputStream(BluetoothReflection.getInputStream(connectedBluetoothSocket));
        outputStream = new BufferedOutputStream(BluetoothReflection.getOutputStream(connectedBluetoothSocket));
        fireAfterConnectEvent();
    }

    protected void write(String s, byte byte0)
    {
        if (!IsConnected())
        {
            bluetoothError(s, 515, new Object[0]);
            return;
        }
        try
        {
            outputStream.write(byte0);
            outputStream.flush();
            return;
        }
        catch (IOException ioexception)
        {
            Object aobj[] = new Object[1];
            aobj[0] = ioexception.getMessage();
            bluetoothError(s, 516, aobj);
            return;
        }
    }

    protected void write(String s, byte abyte0[])
    {
        if (!IsConnected())
        {
            bluetoothError(s, 515, new Object[0]);
            return;
        }
        try
        {
            outputStream.write(abyte0);
            outputStream.flush();
            return;
        }
        catch (IOException ioexception)
        {
            Object aobj[] = new Object[1];
            aobj[0] = ioexception.getMessage();
            bluetoothError(s, 516, aobj);
            return;
        }
    }
}
