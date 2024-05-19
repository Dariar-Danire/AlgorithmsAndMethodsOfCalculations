package com.example.algorithmsandmethodsofcalculations.lab7;

public class MathModel {

    public static double F(double x1, double x2, double x3) {
        return 2.5*x1 + 4*x2 + 3*x3;
    }

    public static boolean limitation1(double x1, double x2, double x3) {
        return (4.5*x1 + 3*x2 + 5*x3) <= 14 &&
                (2*x1 + 6*x2 + x3) <= 11 &&
                x1 >= 0 &&
                x2 >= 0 &&
                Math.floor(x1) == x1 &&
                Math.floor(x2) == x2;
    }

    public static boolean limitation3(double x1, double x2) {
        return x1 >= 0 && x2 >= 0;
    }

    public static boolean limitation4(double x1, double x2) {
        return Math.floor(x1) == x1 && Math.floor(x2) == x2;
    }
}
