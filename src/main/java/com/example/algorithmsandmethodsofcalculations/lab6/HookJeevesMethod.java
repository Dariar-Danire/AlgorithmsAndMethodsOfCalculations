package com.example.algorithmsandmethodsofcalculations.lab6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.pow;

public class HookJeevesMethod {

    // Функция Химмельблау
    private static double f(double[] x) {
        return pow(pow(x[0], 2) + x[1] - 11, 2) + pow(pow(x[1], 2) + x[0] - 7, 2);
    }

    public static void solve() {
        Scanner in = new Scanner(System.in);

        int N = 2; // Количестов измерений
        double[] h = new double[N]; // Массив шагов для каждого измерения
        double[] x0 = new double[N]; // Массив координат начальной точки

        for (int i = 0; i < N; i++) {
            h[i] = 0.1;
        }

        System.out.println("Введите координаты начальной точки:");
        for (int i = 0; i < N; i++) {
            x0[i] = in.nextDouble();
        }

        System.out.println("Введите точность вычислений:");
        double e = in.nextDouble();

        double[] mins = methodHookJeeves(x0, h, e); // Вычисляем значение минимума функции

        System.out.println("\nОтвет:");
        System.out.println(String.format("f(x_min) = %f", f(mins)));
        for (int i = 0; i < mins.length; i++) {
            System.out.println(String.format("x%d = %f", i, mins[i]));
        }

    }

    private static double[] methodHookJeeves(double[] x, double[] h, double e) {

        // Вычисляем, пока все h[i] >= e
        while (endCondition(h, e)) {

            // Перебираем все существующие направления
            for (int j = 0; j < x.length; j++) {
                double[] x_1 = x.clone();
                double[] x_2 = x.clone();

                // Вычисляем новое возможное значение x
                x_1[j] = x[j] + h[j];
                x_2[j] = x[j] - h[j];

                if (f(x_1) < f(x)) {
                    x = x_1.clone();
                } else if (f(x_2) < f(x)) {
                    x = x_2.clone();
                } else {
                    h[j] /= 10;
                }
            }
        }

        return x;
    }

    // Если все h[i] >= e, возвращает true. Иначе false
    private static boolean endCondition(double[] h, double e) {
        List<Boolean> res = new ArrayList<Boolean>();
        for (int i = 0; i < h.length; i++) {
            if (h[i] >= e) {
                res.add(true);
            } else {
                res.add(false);
            }
        }
        return !res.contains(false);
    }

}
