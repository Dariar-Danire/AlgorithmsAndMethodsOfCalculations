package com.example.algorithmsandmethodsofcalculations.lab4;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class CubicSplineInterpolation {

    public static void solve() {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите левую границу:");
        double start = in.nextDouble();
        System.out.println("Введите правую границу:");
        double end = in.nextDouble();

        double h = 0.04;

        List<Double> x = new ArrayList<Double>();
        List<Double> y = new ArrayList<Double>();
        for (double xi = start; xi <= end; xi += h/40) {
            x.add(xi);
            y.add(f(xi));
        }

        CubicSpline spline = new CubicSpline(x, y);

        double[] X =
                {1.03, 1.04, 1.05, 1.08, 1.09, 1.11, 1.12, 1.15, 1.19, 1.2};
        for (double xi : X) {
            System.out.println("S(" + xi + ")" + " = " + spline.findValueInX(xi, end));
        }
    }

    private static double f(double x) {
        return sin(x);
    }

    private static class CubicSpline {

        private class Spline {
            double a, b, c, d, x;
        }

        private int n;
        private List<Spline> splines = new ArrayList<Spline>();

        public CubicSpline(List<Double> x, List<Double> y) {
            if (x.size() != y.size() || x.size() < 2) {
                System.err.println("Неверно заданные исходные данные!");
            }

            n = x.size();

            for (int i = 0; i < n; i++) {
                Spline newSpline = new Spline();
                newSpline.x = x.get(i);
                newSpline.a = y.get(i);

                splines.add(newSpline);
            }

            // Находим коэфициенты c для сплайнов методом прогонки
            double[] alpha = new double[n - 1];
            double[] beta = new double[n - 1];
            alpha[0] = 0;
            beta[0] = 0;

            double A = 0, B = 0, C, F = 0, hi, hi_1;

            // Возможно, у меня все A, B, C и F должны будут быть [i - 1], как и alpha, и beta. Но пока так проверю.
            // Находим коэфициенты alpha и beta
            splines.getFirst().c = 0;
            for (int i = 1; i < n - 1; i++) {
                hi = x.get(i) - x.get(i - 1); // h[i]
                hi_1 = x.get(i + 1) - x.get(i); // h[i + 1]
                A = hi;
                B = 2 * (hi + hi_1); // 2 * (h[i] + h[i + 1])
                C = hi_1;
                F = 6 * ((y.get(i + 1) - y.get(i)) / hi_1 - (y.get(i) - y.get(i - 1)) / hi);
                double divider = A * alpha[i - 1] + B;

                alpha[i] = -C / divider;
                beta[i] = (F - A * beta[i - 1]) / divider;
            }

            // Находим c для каждого splines[i]
            splines.get(n - 1).c = (F - A * beta[n - 2]) / (B + A * alpha[n - 2]); // Почему именно эта формула???
            for (int i = n - 2; i > 0; --i) {
                splines.get(i).c = alpha[i] * splines.get(i + 1).c + beta[i];
            }

            // Находим коэфициенты b и d
            for (int i = 1; i < n; i++) {
                hi = x.get(i) - x.get(i - 1);

                splines.get(i).b = (splines.get(i).a - splines.get(i - 1).a)/hi
                        - ((2 * splines.get(i - 1).c + splines.get(i).c) / 3) * hi;

                splines.get(i).d = (splines.get(i).c - splines.get(i - 1).c) / (3 * hi);
            }
        }

        public double findValueInX(double x, double b) {
            double result = 0;

            for (int i = 0; i < n - 1; i++) {
                if (x >= splines.get(i).x && x < splines.get(i + 1).x) {
                    result = splines.get(i).a
                            + splines.get(i).b * (x - splines.get(i).x)
                            + splines.get(i).c * pow(x - splines.get(i).x, 2)
                            + splines.get(i).d * pow(x - splines.get(i).x, 3);
                }
            }

            if (x >= splines.get(n - 1).x && x <= b) {
                result = splines.get(n - 1).a
                        + splines.get(n - 1).b * (x - splines.get(n - 1).x)
                        + splines.get(n - 1).c * pow(x - splines.get(n - 1).x, 2)
                        + splines.get(n - 1).d * pow(x - splines.get(n - 1).x, 3);
            }

            return result;
        }
    }
}
