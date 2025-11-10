package io.github.marcotangente.mathinfo.polynomial;

public class MainPoly {
    public static void main(String[] args) {
        Polynomial p = new Polynomial(-1, 0, 1);
        Polynomial q = new Polynomial(2, -2, 1);

        System.out.println(q.composition(p)); //$P(Q) = X^4 - 4X^2 + 5$
    }
}
