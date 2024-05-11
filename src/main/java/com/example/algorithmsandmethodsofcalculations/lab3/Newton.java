package com.example.algorithmsandmethodsofcalculations.lab3;

import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.pow;

public class Newton {

    public static void solve() {
        Scanner in = new Scanner(System.in);

        System.out.println("Какой корень хотите найти?");
        int N = in.nextInt();
        System.out.println("Введите точность вычислений:");
        double e = in.nextDouble();

        double x = Newton(e, N);

        System.out.println("x = " + x);
    }

    private static double f(double x) {
        return pow(Math.E, -pow(x, 2)) - pow(x - 1, 2);
    }

    private static double f_derivative1(double x) {
        return -2 * x * pow(Math.E, -pow(x, 2)) -2 * x + 2;
    }

    private static double Newton(double e, int N) {
        double xn_1 = 1.35799999999999;
        if (N == 1) {
            xn_1 = 0.0000001;
        }

        double xn = xn_1 - (f(xn_1)/f_derivative1(xn_1));

        while (abs(f(xn_1)/f_derivative1(xn_1)) > e) {
            xn = xn_1 - (f(xn_1)/f_derivative1(xn_1));
            xn_1 = xn;
        }

        return xn;
    }

}
