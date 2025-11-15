package io.github.marcotangente.mathinfo.polynomial;

import io.github.marcotangente.mathinfo.complex.Complex;

public class MainPoly {
    // TODO doc
    public static void main(String[] args) {
        Polynomial p = new Polynomial(-3,-2,-2,1);
        Polynomial d = new Polynomial(-2,-1,1,4);
        System.out.println(p.evaluate(new Complex(3)));
        System.out.println(PolynomialCalculator.multiplicityOfZero(new Complex(3), p));
    }
}
