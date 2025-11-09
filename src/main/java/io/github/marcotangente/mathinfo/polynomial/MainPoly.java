package io.github.marcotangente.mathinfo.polynomial;

public class MainPoly {
    public static void main(String[] args) {
        Polynomial p = new Polynomial(-1, 0, 2, 3);
        Polynomial q = new Polynomial(2, -1, 1);


        System.out.println(p);
        System.out.println(q);

        Polynomial res1 = p.mult(q);
        Polynomial res2 = q.mult(2);

        System.out.println(res1);
        System.out.println(res2);
    }
}
