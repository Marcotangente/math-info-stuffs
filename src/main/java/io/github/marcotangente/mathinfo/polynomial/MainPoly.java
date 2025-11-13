package io.github.marcotangente.mathinfo.polynomial;

import io.github.marcotangente.mathinfo.complex.Complex;

public class MainPoly {
    public static void main(String[] args) {
        Polynomial p = new Polynomial(1,1,1,1);
        Polynomial d = new Polynomial(-2,-1,1);
        PolynomialCalculator.ExtendedEuclideanAlgorithmResult res = PolynomialCalculator.extendedEuclideanAlgorithm(p,d);
        System.out.println(res.pgcd());
        System.out.println(res.u());
        System.out.println(res.v());

        Polynomial up = p.mult(res.u());
        Polynomial vq = d.mult(res.v());
        Polynomial pgcd = up.add(vq);
        System.out.println(pgcd);
    }
}
