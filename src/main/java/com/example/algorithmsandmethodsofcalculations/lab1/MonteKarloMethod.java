package com.example.algorithmsandmethodsofcalculations.lab1;

import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.Math.sin;

public class MonteKarloMethod {

    public static void solve() {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите количество бросков: ");
        int n_throws = in.nextInt();
        System.out.println("Введите количество серий бросков: ");
        int n_sessions = in.nextInt();

        double[] answer = monteCarlo(n_throws, n_sessions);

        System.out.println("Значение интеграла: " + answer[0]);
        System.out.println("Погрешность: " + answer[1]);
        System.out.println("Количество бросков: " + n_throws + "\nКоличество серий бросков: " + n_sessions);
    }

    private static double[] monteCarlo(int n_throws, int n_sessions) {
        double s_picture = 4; // площадь всего рисунка

        int n_hits = 0;

        double[] integrals = new double[n_sessions];

        for (int i = 0; i < n_sessions; i++) {
            int n_n_hits = 0;

            for(int j = 0; j < n_throws; j++) {
                // Генерирую псевдослучайные координаты точки в заданном диапазоне
                double y = Math.random() * 2; // (0;2)
                double x = Math.random() * 2; // (0;2)

                double y_f = f(x);
                if (y <= y_f) { // y > 1 всегда
                    n_n_hits += 1;
                }
            }

            double I = (n_n_hits * s_picture)/(n_throws);
            integrals[i] = I;
            n_hits += n_n_hits;
        }

        double error = ErrorInTheStandardDeviationAverages(integrals, n_sessions) / Math.sqrt(n_sessions);

        double s_function = (n_hits * s_picture)/(n_throws * n_sessions); // Итоговое значение интеграла

        return new double[]{s_function, error};
    }

    private static double f(double x) {
        return 1 / (1 - 0.49 * pow(sin(x), 2));
    }

    private static double ErrorInTheStandardDeviationAverages(double[] integrals, int n_sessions) {
        double sum = 0.0;

        // Вычисление суммы всех значений массива
        for (double integral : integrals) {
            sum += integral;
        }

        // Вычисление среднего значения
        double mean = sum / n_sessions;
        double squaredDifferencesSum = 0.0;

        // Вычисление суммы квадратов разностей от среднего значения
        for (double integral : integrals) {
            double difference = integral - mean;
            squaredDifferencesSum += pow(difference, 2);
        }

        // Вычисление дисперсии и стандартного отклонения
        double variance = squaredDifferencesSum / n_sessions;
        return Math.sqrt(variance);
    }

}
