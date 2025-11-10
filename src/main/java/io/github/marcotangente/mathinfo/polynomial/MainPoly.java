package io.github.marcotangente.mathinfo.polynomial;

public class MainPoly {
    public static void main(String[] args) {
        Polynomial p = new Polynomial(6, 12, 74);
        Polynomial q = new Polynomial();

        System.out.println(p.pow(2)); //$P(Q) = X^4 - 4X^2 + 5$
    }
}
