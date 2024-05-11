package com.example.algorithmsandmethodsofcalculations.lab3;

import java.util.Scanner;

import static java.lang.Math.*;

public class Dihotomia {

    public static void solve() {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите левую границу по оси Х:");
        double a = in.nextDouble();
        System.out.println("Введите правую границу по оси Х:");
        double b = in.nextDouble();
        System.out.println("Введите точность вычислений:");
        double e = in.nextDouble();


        double x = dihotomia(a, b, e);
        if (x == Double.MIN_VALUE) {
            return;
        }

        System.out.println("x = " + x);
    }

    private static double f(double x) {
        return pow(Math.E, -pow(x, 2)) - pow(x - 1, 2);
    }

    // Ищет только 1 корень на отрезке
    private static double dihotomia(double a, double b, double e) {
        if (f(a)*f(b) > 0) {
            System.out.println("Неверно заданные границы!");
            return Double.MIN_VALUE;
        }

        if (f(a) == 0) {
            System.out.println("x = " + a);
        } else if (f(b) == 0) {
            System.out.println("x = " + b);
        }

        // Находим число итераций
        double n = log((b - a)/e)/log(2);

        double c = (a + b)/2;
        for (int i = 0; i < n; i++) {
            if (f(c)*f(a) < 0) {
                a = min(c, a);
                b = max(c, a);
            } else if (f(c)*f(b) < 0){
                a = min(c, b);
                b = max(c, b);
            }

            c = (a + b)/2;
        }

        return c;
    }

}
