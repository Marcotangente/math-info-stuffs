package io.github.marcotangente.mathinfo.polynomial;

import io.github.marcotangente.mathinfo.complex.Complex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Polynomial{
    private final List<Complex> coeffs;

    public Polynomial() {
        this.coeffs = new ArrayList<>();
        this.trimTrailingZeros();
    }

    public Polynomial(List<Complex> coeffs) {
        this.coeffs = new ArrayList<>(coeffs);
        this.trimTrailingZeros();
    }

    public Polynomial(Complex... coeffs) {
        this.coeffs = new ArrayList<>();
        Collections.addAll(this.coeffs, coeffs);
        this.trimTrailingZeros();
    }

    public Polynomial(double... coeffs) {
        this.coeffs = new ArrayList<>();
        for (double coeff : coeffs)
            this.coeffs.add(new Complex(coeff));
        this.trimTrailingZeros();
    }

    /**
     * Cleans the end of the list to make its size matches the degree
     */
    private void trimTrailingZeros() {
        ListIterator<Complex> it = coeffs.listIterator(coeffs.size());
        while (it.hasPrevious()) {
            Complex z = it.previous();
            if (z.equalsTo(0))
                it.remove();
            else
                return;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = coeffs.size() - 1; i >= 0; i--) {
            if (coeffs.get(i).equalsTo(0))
                continue;
            sb.append("(").append(coeffs.get(i)).append(")x^").append(i);
            if (i > 0)
                sb.append(" + ");
        }
        if (sb.isEmpty())
            sb.append(0);
        return sb.toString();
    }

    public boolean isEqualTo(double real) {
        return this.isEqualTo(new Complex(real));
    }

    public boolean isEqualTo(Complex complex) {
        return this.degree() < 1 && complex.equalsTo(this.get(0));
    }

    public boolean isEqualTo(Polynomial p) {
        int greaterDegree = Math.max(this.degree(), p.degree());
        for (int i = 0; i <= greaterDegree; i++)
            if (!this.get(i).equalsTo(p.get(i)))
                return false;
        return true;
    }

    public void set(Complex coeff, int degree) {
        if (degree < 0) return;
        if (degree <= this.degree())
            this.coeffs.set(degree, coeff);
        else {
            for (int i = this.degree() + 1; i < degree; i++)
                this.coeffs.addLast(Complex.ZERO);
            this.coeffs.addLast(coeff);
        }
        this.trimTrailingZeros();
    }

    public void set(double coeff, int degree) {
        this.set(new Complex(coeff), degree);
    }

    public Complex get(int degree) {
        if (degree < 0)
            throw new ArithmeticException("Negative degree");

        if (degree <= this.degree())
            return coeffs.get(degree);
        return Complex.ZERO;
    }

    /**
     *
     * @return The degree of the polynomial. -1 for negative infinity
     */
    public int degree() {
        return coeffs.size() - 1;
    }

    public Polynomial add(Polynomial p) {
        int greaterDegree = Math.max(this.degree(), p.degree());

        Polynomial res = new Polynomial();
        for (int i = 0; i <= greaterDegree; i++)
            res.set(this.get(i).add(p.get(i)), i);

        return res;
    }

    public Polynomial mult(Polynomial p) {
        if (p.isEqualTo(0) || this.isEqualTo(0))
            return new Polynomial();

        int degree = this.degree() + p.degree();


        Polynomial res = new Polynomial();
        for (int i = 0; i <= degree; i++) {
            Complex coeff = Complex.ZERO;
            for (int j = 0; j <= i; j++)
                coeff = coeff.add(this.get(j).mult(p.get(i - j)));
            res.set(coeff, i);
        }

        return res;
    }

    public Polynomial mult(Complex complex) {
        Polynomial res = new Polynomial();
        for (int i = 0; i <= this.degree(); i++)
            res.set(this.get(i).mult(complex), i);
        return res;
    }

    public Polynomial mult(double real) {
        return this.mult(new Complex(real));
    }

    // search horner composition
    public Polynomial composition(Polynomial p) {
        // needs pow
        Polynomial res = new Polynomial();
        for (int i = 0; i <= this.degree(); i++)
            res = res.add(p.pow(i).mult(this.get(i)));
        return res;
    }

    // search exponentiation par carrés (ou rapide)
    public Polynomial pow(int power) {
        if (power <= 0)
            return new Polynomial(1);
        Polynomial res = new Polynomial(this.coeffs);
        for (int i = 1; i < power; i++)
            res = res.mult(this);
        return res;
    }

}
