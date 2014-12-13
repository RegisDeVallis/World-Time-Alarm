// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.kawa.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class PreProcess
{

    static final String JAVA4_FEATURES = "+JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio";
    static final String JAVA5_FEATURES = "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName";
    static final String NO_JAVA4_FEATURES = "-JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android";
    static final String NO_JAVA6_FEATURES = "-JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer";
    static String version_features[] = {
        "java1", "-JAVA2 -JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java2", "+JAVA2 -JAVA5 -use:java.util.IdentityHashMap -use:java.lang.CharSequence -use:java.lang.Throwable.getCause -use:java.net.URI -use:java.util.regex -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAVA5 -JAVA6 -JAVA6COMPAT5 -JAXP-QName -use:java.text.Normalizer -SAX2 -use:java.nio -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java4", "-JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio -use:org.w3c.dom.Node -JAXP-1.3 -use:javax.xml.transform -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java4x", "-JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +JAXP-1.3 +use:javax.xml.transform -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", "java5", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName -JAVA6COMPAT5 -Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer", 
        "java6compat5", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName -JAVA6 -JAVA7 +JAVA6COMPAT5 +use:java.text.Normalizer -use:java.dyn -Android", "java6", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName +JAVA6 -JAVA7 -JAVA6COMPAT5 +use:java.text.Normalizer -use:java.dyn -Android", "java7", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +use:javax.xml.transform +JAXP-1.3 -JAXP-QName +JAVA6 +JAVA7 -JAVA6COMPAT5 +use:java.text.Normalizer +use:java.dyn -Android", "android", "+JAVA5 +JAVA2 +use:java.util.IdentityHashMap +use:java.lang.CharSequence +use:java.lang.Throwable.getCause +use:java.net.URI +use:java.util.regex +SAX2 +use:java.nio +use:org.w3c.dom.Node +JAXP-1.3 -JAXP-QName -use:javax.xml.transform -JAVA6 -JAVA6COMPAT5 +Android -JAVA6 -JAVA7 -use:java.dyn -use:java.text.Normalizer"
    };
    String filename;
    Hashtable keywords;
    int lineno;
    byte resultBuffer[];
    int resultLength;

    public PreProcess()
    {
        keywords = new Hashtable();
    }

    public static void main(String args[])
    {
        PreProcess preprocess = new PreProcess();
        preprocess.keywords.put("true", Boolean.TRUE);
        preprocess.keywords.put("false", Boolean.FALSE);
        for (int i = 0; i < args.length; i++)
        {
            preprocess.handleArg(args[i]);
        }

    }

    void error(String s)
    {
        System.err.println((new StringBuilder()).append(filename).append(':').append(lineno).append(": ").append(s).toString());
        System.exit(-1);
    }

    public void filter(String s)
        throws Throwable
    {
        if (filter(s, new BufferedInputStream(new FileInputStream(s))))
        {
            FileOutputStream fileoutputstream = new FileOutputStream(s);
            fileoutputstream.write(resultBuffer, 0, resultLength);
            fileoutputstream.close();
            System.err.println((new StringBuilder()).append("Pre-processed ").append(s).toString());
        }
    }

    public boolean filter(String s, BufferedInputStream bufferedinputstream)
        throws Throwable
    {
        byte abyte0[];
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        String s1;
        boolean flag;
        int l1;
        int i2;
        filename = s;
        abyte0 = new byte[2000];
        i = 0;
        j = 0;
        k = -1;
        lineno = 1;
        l = -1;
        i1 = 0;
        j1 = 0;
        k1 = 0;
        s1 = null;
        flag = false;
        l1 = 0;
        i2 = 0;
_L3:
        int j2 = bufferedinputstream.read();
        if (j2 >= 0) goto _L2; else goto _L1
_L1:
        byte abyte1[];
        int k2;
        abyte1 = abyte0;
        k2 = i;
_L7:
        if (j1 != 0)
        {
            lineno = i2;
            error((new StringBuilder()).append("unterminated ").append(s1).toString());
        }
        resultBuffer = abyte1;
        resultLength = k2;
        return flag;
_L2:
label0:
        {
            int l2;
            byte byte0;
            int i3;
            boolean flag1;
            int j3;
            int k3;
            boolean flag2;
            int l3;
            int i4;
            int j4;
            byte byte1;
            int k4;
            int l4;
            int i5;
            int j5;
            String s2;
            int k5;
            String s3;
            boolean flag3;
            boolean flag4;
            byte byte2;
            byte byte3;
            int l6;
            int i7;
            int j7;
            int k7;
            boolean flag5;
            int l7;
            int i8;
            int j8;
            int k8;
            int l8;
            int i9;
            if (i + 10 >= abyte0.length)
            {
                abyte1 = new byte[i * 2];
                System.arraycopy(abyte0, 0, abyte1, 0, i);
            } else
            {
                abyte1 = abyte0;
            }
            if (j2 != 10 || i <= 0 || abyte1[i - 1] != 13)
            {
                break label0;
            }
            i9 = i + 1;
            abyte1[i] = (byte)j2;
            i = i9;
            abyte0 = abyte1;
        }
          goto _L3
        if (l < 0 || k >= 0 || l1 > 0 || j2 == 13 || j2 == 10 || l != i1 && (j2 == 32 || j2 == 9))
        {
            break MISSING_BLOCK_LABEL_1381;
        }
        if (j2 == 47)
        {
            bufferedinputstream.mark(100);
            k8 = bufferedinputstream.read();
            if (k8 == 47)
            {
                flag5 = false;
            } else
            if (k8 == 42)
            {
                do
                {
                    l8 = bufferedinputstream.read();
                } while (l8 == 32 || l8 == 9);
                if (l8 != 35)
                {
                    flag5 = true;
                } else
                {
                    flag5 = false;
                }
            } else
            {
                flag5 = true;
            }
            bufferedinputstream.reset();
        } else
        {
            flag5 = true;
        }
        if (!flag5)
        {
            break MISSING_BLOCK_LABEL_1381;
        }
        l7 = i + 1;
        abyte1[i] = 47;
        i8 = l7 + 1;
        abyte1[l7] = 47;
        j8 = i8 + 1;
        abyte1[i8] = 32;
        flag = true;
        k2 = j8;
        l2 = 1;
_L12:
        if (j2 == 32 || j2 == 9 || k >= 0)
        {
            break MISSING_BLOCK_LABEL_1362;
        }
        if (j1 <= 0 || l == i1 || j2 != 47) goto _L5; else goto _L4
_L4:
        l6 = bufferedinputstream.read();
        if (l6 < 0) goto _L7; else goto _L6
_L6:
        if (l6 == 47) goto _L9; else goto _L8
_L8:
        k7 = k2 + 1;
        abyte1[k2] = 47;
        byte0 = l2;
        i3 = k7;
        k = k2;
        flag1 = flag;
        j3 = l6;
_L11:
        abyte1[i3] = (byte)j3;
        i = i3 + 1;
        if (j3 == 13 || j3 == 10)
        {
            {
                k3 = 0;
                flag2 = j;
                l3 = -1;
                i4 = ((flag2) ? 1 : 0);
                while (i4 < i - 1) 
                {
                    if (abyte1[i4] != 32 && abyte1[i4] != 9)
                    {
                        if (l3 < 0)
                        {
                            k3 = i4;
                            l3 = i4;
                        } else
                        {
                            k3 = i4;
                        }
                    }
                    i4++;
                }
                if (k3 - l3 >= 4 && abyte1[l3] == 47 && abyte1[l3 + 1] == 42 && abyte1[k3 - 1] == 42 && abyte1[k3] == 47)
                {
                    for (i5 = l3 + 2; i5 < k3 && abyte1[i5] == 32; i5++) { }
                    for (j5 = k3 - 2; j5 > i5 && abyte1[j5] == 32; j5--) { }
                    if (abyte1[i5] == 35)
                    {
                        s2 = new String(abyte1, i5, 1 + (j5 - i5), "ISO-8859-1");
                        k5 = s2.indexOf(' ');
                        int l5 = lineno;
                        Object obj;
                        if (k5 > 0)
                        {
                            s1 = s2.substring(0, k5);
                            s3 = s2.substring(k5).trim();
                            obj = keywords.get(s3);
                        } else
                        {
                            s3 = "";
                            s1 = s2;
                            obj = null;
                        }
                        if ("#ifdef".equals(s1) || "#ifndef".equals(s1))
                        {
                            Object obj1;
                            int i6;
                            if (obj == null)
                            {
                                System.err.println((new StringBuilder()).append(s).append(":").append(lineno).append(": warning - undefined keyword: ").append(s3).toString());
                                obj1 = Boolean.FALSE;
                            } else
                            {
                                obj1 = obj;
                            }
                            i6 = j1 + 1;
                            if (k1 > 0)
                            {
                                j1 = i6;
                                l = i1;
                                i2 = l5;
                            } else
                            {
                                if (s1.charAt(3) == 'n')
                                {
                                    flag3 = true;
                                } else
                                {
                                    flag3 = false;
                                }
                                if (obj1 == Boolean.FALSE)
                                {
                                    flag4 = true;
                                } else
                                {
                                    flag4 = false;
                                }
                                int j6;
                                int k6;
                                if (flag3 != flag4)
                                {
                                    k1 = i6;
                                    j1 = i6;
                                    l = i1;
                                    i2 = l5;
                                } else
                                {
                                    j1 = i6;
                                    i2 = l5;
                                }
                            }
                        } else
                        if ("#else".equals(s1))
                        {
                            if (j1 == 0)
                            {
                                error((new StringBuilder()).append("unexpected ").append(s1).toString());
                                i2 = l5;
                            } else
                            if (j1 == k1)
                            {
                                l = -1;
                                i2 = l5;
                                k1 = 0;
                            } else
                            if (k1 == 0)
                            {
                                k1 = j1;
                                l = i1;
                                i2 = l5;
                            } else
                            {
                                l = i1;
                                i2 = l5;
                            }
                        } else
                        if ("#endif".equals(s1))
                        {
                            if (j1 == 0)
                            {
                                error((new StringBuilder()).append("unexpected ").append(s1).toString());
                            }
                            if (j1 == k1)
                            {
                                j6 = 0;
                                k6 = -1;
                            } else
                            if (k1 > 0)
                            {
                                j6 = k1;
                                k6 = i1;
                            } else
                            {
                                j6 = k1;
                                k6 = l;
                            }
                            j1--;
                            k1 = j6;
                            l = k6;
                            i2 = l5;
                        } else
                        {
                            error((new StringBuilder()).append("unknown command: ").append(s2).toString());
                            i2 = l5;
                        }
                    }
                }
                k = -1;
                i1 = 0;
                lineno = 1 + lineno;
                j = i;
                j4 = i2;
                byte1 = 0;
                k4 = j4;
            }
            flag = flag1;
            abyte0 = abyte1;
            l4 = k4;
            l1 = byte1;
            i2 = l4;
        } else
        {
            if (k < 0)
            {
                if (j3 == 9)
                {
                    i1 = -8 & i1 + 8;
                } else
                {
                    i1++;
                }
                byte3 = byte0;
                k4 = i2;
                byte1 = byte3;
            } else
            {
                byte2 = byte0;
                k4 = i2;
                byte1 = byte2;
            }
            break MISSING_BLOCK_LABEL_951;
        }
          goto _L3
_L9:
        i7 = bufferedinputstream.read();
        if (i7 < 0) goto _L7; else goto _L10
_L10:
label1:
        {
            if (i7 != 32)
            {
                break label1;
            }
            i7 = bufferedinputstream.read();
            if (i7 != 32 && i7 != 9)
            {
                break label1;
            }
            j7 = i7;
            byte0 = -1;
            k = -1;
            i3 = k2;
            flag1 = true;
            j3 = j7;
        }
          goto _L11
        i3 = k2;
        k = k2;
        flag1 = true;
        j3 = i7;
        byte0 = -1;
          goto _L11
_L5:
        byte0 = l2;
        k = k2;
        i3 = k2;
        flag1 = flag;
        j3 = j2;
          goto _L11
        byte0 = l2;
        i3 = k2;
        flag1 = flag;
        j3 = j2;
          goto _L11
        k2 = i;
        l2 = l1;
          goto _L12
    }

    void handleArg(String s)
    {
        int j;
label0:
        {
            int i = 0;
            if (s.charAt(0) == '%')
            {
                String s3 = s.substring(1);
                do
                {
                    if (i >= version_features.length)
                    {
                        System.err.println((new StringBuilder()).append("Unknown version: ").append(s3).toString());
                        System.exit(-1);
                    }
                    if (s3.equals(version_features[i]))
                    {
                        String s4 = version_features[i + 1];
                        System.err.println((new StringBuilder()).append("(variant ").append(s3).append(" maps to: ").append(s4).append(")").toString());
                        for (StringTokenizer stringtokenizer = new StringTokenizer(s4); stringtokenizer.hasMoreTokens(); handleArg(stringtokenizer.nextToken())) { }
                        break;
                    }
                    i += 2;
                } while (true);
            } else
            {
                if (s.charAt(0) != '+')
                {
                    break label0;
                }
                keywords.put(s.substring(1), Boolean.TRUE);
            }
            return;
        }
        if (s.charAt(0) != '-')
        {
            break MISSING_BLOCK_LABEL_352;
        }
        j = s.indexOf('=');
        if (j <= 1) goto _L2; else goto _L1
_L1:
        String s1;
        String s2;
        byte byte0;
        Boolean boolean1;
        if (s.charAt(1) == '-')
        {
            byte0 = 2;
        } else
        {
            byte0 = 1;
        }
        s1 = s.substring(byte0, j);
        s2 = s.substring(j + 1);
        boolean1 = Boolean.FALSE;
        if (!s2.equalsIgnoreCase("true")) goto _L4; else goto _L3
_L3:
        boolean1 = Boolean.TRUE;
_L5:
        keywords.put(s1, boolean1);
        return;
_L4:
        if (!s2.equalsIgnoreCase("false"))
        {
            System.err.println((new StringBuilder()).append("invalid value ").append(s2).append(" for ").append(s1).toString());
            System.exit(-1);
        }
        if (true) goto _L5; else goto _L2
_L2:
        keywords.put(s.substring(1), Boolean.FALSE);
        return;
        try
        {
            filter(s);
            return;
        }
        catch (Throwable throwable)
        {
            System.err.println((new StringBuilder()).append("caught ").append(throwable).toString());
            throwable.printStackTrace();
            System.exit(-1);
            return;
        }
    }

}
