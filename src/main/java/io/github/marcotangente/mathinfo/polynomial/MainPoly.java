package io.github.marcotangente.mathinfo.polynomial;

import io.github.marcotangente.mathinfo.complex.Complex;

public class MainPoly {
    // TODO doc
    public static void main(String[] args) {
        Polynomial p = new Polynomial(1,1,1,1);
        Polynomial d = new Polynomial(-2,-1,1,4);
        System.out.println(d);
        System.out.println(d.derivate());

    }
}
