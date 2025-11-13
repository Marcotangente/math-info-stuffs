package io.github.marcotangente.mathinfo.polynomial;

import io.github.marcotangente.mathinfo.complex.Complex;

public class PolynomialCalculator {
    private PolynomialCalculator() {}

    public record EuclideanDivisionResult(Polynomial q, Polynomial r) {}
    public record ExtendedEuclideanAlgorithmResult(Polynomial pgcd, Polynomial u, Polynomial v) {}

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

    private static Polynomial euclideanAlgorithm(Polynomial p, Polynomial q) {
        Polynomial a = p;
        Polynomial b = q;
        while (!b.isEqualTo(0)) {
            Polynomial t = b;
            b = euclideanDivision(a, b).r();
            a = t;
        }
        return a;
    }

    public static Polynomial pgcd(Polynomial p, Polynomial q, boolean makeMonic) {
        Polynomial res = euclideanAlgorithm(p, q);
        if (makeMonic) {
            Complex factor = Complex.ONE.divide(res.get(res.degree()));
            res = res.mult(factor);
        }
        return res;
    }

    public static ExtendedEuclideanAlgorithmResult extendedEuclideanAlgorithm(Polynomial p, Polynomial q) {
        Polynomial r = p;
        Polynomial u = new Polynomial(1);
        Polynomial v = new Polynomial(0);
        Polynomial r_ = q;
        Polynomial u_ = new Polynomial(0);
        Polynomial v_ = new Polynomial(1);

        while (!r_.isEqualTo(0)) {
            // r = r' * a + r'' <==> r'' = r - r' * a = (up + vq) - (u'p + v'q) * a = (u - au')p + (v - av')q
            // r <- r'
            // r' <- r''
            EuclideanDivisionResult euclideanDivisionRes = euclideanDivision(r, r_);
            Polynomial a = euclideanDivisionRes.q();
            Polynomial newU = u.sub(u_.mult(a));
            Polynomial newV = v.sub(v_.mult(a));

            r = r_;
            r_ = euclideanDivisionRes.r();
            u = u_;
            u_ = newU;
            v = v_;
            v_ = newV;
        }

        return new ExtendedEuclideanAlgorithmResult(r, u, v);
    }

}
