// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

// Referenced classes of package gnu.math:
//            Complex, Dimensions, Numeric, Unit, 
//            DFloNum, CComplex, RealNum

public class DComplex extends Complex
    implements Externalizable
{

    double imag;
    double real;

    public DComplex()
    {
    }

    public DComplex(double d, double d1)
    {
        real = d;
        imag = d1;
    }

    public static DComplex div(double d, double d1, double d2, double d3)
    {
        double d5;
        double d6;
        double d7;
        if (Math.abs(d2) <= Math.abs(d3))
        {
            double d8 = d2 / d3;
            d5 = d3 * (1.0D + d8 * d8);
            d6 = d1 + d * d8;
            d7 = d1 * d8 - d;
        } else
        {
            double d4 = d3 / d2;
            d5 = d2 * (1.0D + d4 * d4);
            d6 = d + d1 * d4;
            d7 = d1 - d * d4;
        }
        return new DComplex(d6 / d5, d7 / d5);
    }

    public static Complex log(double d, double d1)
    {
        return make(Math.log(Math.hypot(d, d1)), Math.atan2(d1, d));
    }

    public static DComplex power(double d, double d1, double d2, double d3)
    {
        double d4 = Math.log(Math.hypot(d, d1));
        double d5 = Math.atan2(d1, d);
        return Complex.polar(Math.exp(d4 * d2 - d3 * d5), d3 * d4 + d2 * d5);
    }

    public static Complex sqrt(double d, double d1)
    {
        double d2 = Math.hypot(d, d1);
        double d3;
        double d4;
        if (d2 == 0.0D)
        {
            d3 = d2;
            d4 = d2;
        } else
        if (d > 0.0D)
        {
            d4 = Math.sqrt(0.5D * (d2 + d));
            d3 = d1 / d4 / 2D;
        } else
        {
            d3 = Math.sqrt(0.5D * (d2 - d));
            if (d1 < 0.0D)
            {
                d3 = -d3;
            }
            d4 = d1 / d3 / 2D;
        }
        return new DComplex(d4, d3);
    }

    public Numeric add(Object obj, int i)
    {
        if (obj instanceof Complex)
        {
            Complex complex = (Complex)obj;
            if (complex.dimensions() != Dimensions.Empty)
            {
                throw new ArithmeticException("units mis-match");
            } else
            {
                return new DComplex(real + (double)i * complex.reValue(), imag + (double)i * complex.imValue());
            }
        } else
        {
            return ((Numeric)obj).addReversed(this, i);
        }
    }

    public Numeric div(Object obj)
    {
        if (obj instanceof Complex)
        {
            Complex complex = (Complex)obj;
            return div(real, imag, complex.doubleValue(), complex.doubleImagValue());
        } else
        {
            return ((Numeric)obj).divReversed(this);
        }
    }

    public double doubleImagValue()
    {
        return imag;
    }

    public double doubleValue()
    {
        return real;
    }

    public boolean equals(Object obj)
    {
        Complex complex;
        if (obj != null && (obj instanceof Complex))
        {
            if ((complex = (Complex)obj).unit() == Unit.Empty && Double.doubleToLongBits(real) == Double.doubleToLongBits(complex.reValue()) && Double.doubleToLongBits(imag) == Double.doubleToLongBits(complex.imValue()))
            {
                return true;
            }
        }
        return false;
    }

    public RealNum im()
    {
        return new DFloNum(imag);
    }

    public boolean isExact()
    {
        return false;
    }

    public Numeric mul(Object obj)
    {
        if (obj instanceof Complex)
        {
            Complex complex = (Complex)obj;
            if (complex.unit() == Unit.Empty)
            {
                double d = complex.reValue();
                double d1 = complex.imValue();
                return new DComplex(d * real - d1 * imag, d1 * real + d * imag);
            } else
            {
                return Complex.times(this, complex);
            }
        } else
        {
            return ((Numeric)obj).mulReversed(this);
        }
    }

    public final Numeric neg()
    {
        return new DComplex(-real, -imag);
    }

    public RealNum re()
    {
        return new DFloNum(real);
    }

    public void readExternal(ObjectInput objectinput)
        throws IOException, ClassNotFoundException
    {
        real = objectinput.readDouble();
        imag = objectinput.readDouble();
    }

    public Complex toExact()
    {
        return new CComplex(DFloNum.toExact(real), DFloNum.toExact(imag));
    }

    public volatile Numeric toExact()
    {
        return toExact();
    }

    public String toString()
    {
        String s = "";
        String s1;
        if (real == (1.0D / 0.0D))
        {
            s = "#i";
            s1 = "1/0";
        } else
        if (real == (-1.0D / 0.0D))
        {
            s = "#i";
            s1 = "-1/0";
        } else
        if (Double.isNaN(real))
        {
            s = "#i";
            s1 = "0/0";
        } else
        {
            s1 = Double.toString(real);
        }
        if (Double.doubleToLongBits(imag) == 0L)
        {
            return (new StringBuilder()).append(s).append(s1).toString();
        }
        String s2;
        StringBuilder stringbuilder;
        String s3;
        if (imag == (1.0D / 0.0D))
        {
            s = "#i";
            s2 = "+1/0i";
        } else
        if (imag == (-1.0D / 0.0D))
        {
            s = "#i";
            s2 = "-1/0i";
        } else
        if (Double.isNaN(imag))
        {
            s = "#i";
            s2 = "+0/0i";
        } else
        {
            s2 = (new StringBuilder()).append(Double.toString(imag)).append("i").toString();
            if (s2.charAt(0) != '-')
            {
                s2 = (new StringBuilder()).append("+").append(s2).toString();
            }
        }
        stringbuilder = new StringBuilder();
        if (Double.doubleToLongBits(real) == 0L)
        {
            s3 = s;
        } else
        {
            s3 = (new StringBuilder()).append(s).append(s1).toString();
        }
        return stringbuilder.append(s3).append(s2).toString();
    }

    public String toString(int i)
    {
        if (i == 10)
        {
            return toString();
        } else
        {
            return (new StringBuilder()).append("#d").append(toString()).toString();
        }
    }

    public void writeExternal(ObjectOutput objectoutput)
        throws IOException
    {
        objectoutput.writeDouble(real);
        objectoutput.writeDouble(imag);
    }
}
