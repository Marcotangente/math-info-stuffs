package io.github.marcotangente.mathinfo.polynomial;

import io.github.marcotangente.mathinfo.complex.Complex;

public class MainPoly {
    public static void main(String[] args) {
        Polynomial p = new Polynomial(new Complex(-1), new Complex(1,1), Complex.ZERO, Complex.IMAGINARY_UNIT, Complex.ZERO, Complex.ONE);
        Polynomial d = new Polynomial(1, 0, -2, 2);
        PolynomialCalculator.EuclideanDivisionResult res = PolynomialCalculator.euclideanDivision(p, d);
        System.out.println("q = " + res.q().toString());
        System.out.println("r = " + res.r().toString());
    }
}
