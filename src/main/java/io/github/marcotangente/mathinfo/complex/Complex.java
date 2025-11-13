package io.github.marcotangente.mathinfo.complex;

public class Complex {
    private static final double EPSILON = 1e-12;

    public final double re;
    public final double im;

    public static final Complex IMAGINARY_UNIT = new Complex(0, 1);
    public static final Complex ONE = new Complex(1, 0);
    public static final Complex ZERO = new Complex(0, 0);


    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }

    public Complex(double re) {
        this.re = re;
        this.im = 0;
    }

    @Override
    public String toString() {
        if (this.equalsTo(IMAGINARY_UNIT))
            return "i";
        if (Math.abs(im) < EPSILON)
            return String.format("%g", re);
        if (Math.abs(re) < EPSILON)
            return String.format("%gi", im);
        return String.format("%g %s %gi", re, im >= 0 ? "+" : "-", Math.abs(im));
    }

    public boolean equalsTo(double n) {
        if (-EPSILON < im && im < EPSILON) {
            double res = Math.abs(re - n);
            return res < EPSILON;
        }
        return false;
    }

    public boolean equalsTo(Complex z) {
        double dre = Math.abs(this.re - z.re);
        double dim = Math.abs(this.im - z.im);
        return dre < EPSILON && dim < EPSILON;
    }

    public Complex add(Complex z) {
        return new Complex(this.re + z.re, this.im + z.im);
    }

    public Complex sub(Complex z) {
        return new Complex(this.re - z.re, this.im - z.im);
    }

    public Complex mult(Complex z) {
        double real = this.re * z.re - this.im * z.im;
        double img = this.re * z.im + this.im * z.re;
        return new Complex(real, img);
    }

    public Complex mult(double k) {
        return new Complex(k * re, k * im);
    }

    public Complex conjugate(Complex z) {
        return new Complex(re, -im);
    }

    public double modulus() {
        return Math.sqrt(re*re + im*im);
    }

    public double arg() {
        return Math.atan2(im, re);
    }

    public Complex inverse() {
        if (this.equalsTo(0))
            throw new ArithmeticException("Complex division by zero");
        double real = re / (re*re + im*im);
        double img = - im / (re*re + im*im);
        return new Complex(real, img);
    }

    public Complex divide(Complex z) {
        return this.mult(z.inverse());
    }

    public Complex pow(int n) {
        if (n < 0)
            throw new ArithmeticException("Negative power of ComplexNumber (roots not implemented)");
        double coeff = Math.pow(this.modulus(), n);
        double real = Math.cos(n * this.arg());
        double img = Math.sin(n * this.arg());
        return new Complex(real, img).mult(coeff);
    }
}

