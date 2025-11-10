package io.github.marcotangente.mathinfo.polynomial;

import io.github.marcotangente.mathinfo.complex.Complex;

public class PolynomialCalculator {
    private PolynomialCalculator() {}

    public record EuclideanDivisionResult(Polynomial q, Polynomial r) {}

    //TODO search horner composition
    public static Polynomial composition(Polynomial p, Polynomial q) {
        Polynomial res = new Polynomial();
        for (int i = 0; i <= p.degree(); i++)
            res = res.add(pow(q, i).mult(p.get(i)));
        return res;
    }

    //TODO search exponentiation par carrés (ou rapide)
    public static Polynomial pow(Polynomial p, int power) {
        Polynomial res = new Polynomial(1);
        for (int i = 0; i < power; i++)
            res = res.mult(p);
        return res;
    }

    public static EuclideanDivisionResult euclideanDivision(Polynomial p, Polynomial d) {
        if (d.isEqualTo(0))
            throw new ArithmeticException("Polynomial division by zero");

        Polynomial q = new Polynomial();
        while (p.degree() >= d.degree()) {
            int degree = p.degree() - d.degree();
            Complex coeff = p.get(p.degree()).divide(d.get(d.degree()));
            Polynomial monomial = new Polynomial();
            monomial.set(coeff, degree);
            q = q.add(monomial);
            Polynomial toSubstract = d.mult(monomial);
            p = p.sub(toSubstract);
        }
        return new EuclideanDivisionResult(q, p);
    }

    //TODO solve q,r whith system (slide 29)

    public static Polynomial pgcd(Polynomial p, Polynomial q) {
        Polynomial biggest = p;
        Polynomial smallest = q;
        if (p.degree() < q.degree()) {
            biggest = q;
            smallest = p;
        }

        Polynomial pol = biggest;
        Polynomial lastR = smallest;
        while (!lastR.isEqualTo(0)) {

        }
    }
}
