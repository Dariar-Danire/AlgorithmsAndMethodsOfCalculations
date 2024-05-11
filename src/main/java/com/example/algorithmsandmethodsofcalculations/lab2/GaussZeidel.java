package com.example.algorithmsandmethodsofcalculations.lab2;

import java.util.Scanner;

public class GaussZeidel {

    public static void solve() {

        Scanner in = new Scanner(System.in);

        System.out.println("Введите количество уравнений (количество строк матрицы):");
        int n = in.nextInt();
        System.out.println("Введите количество переменных (количество колонок матрицы):");
        int m = in.nextInt();

        double[][] matrix = new double[n][m];

        System.out.println("Введите матрицу:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = in.nextDouble();
            }
        }

        double error = 1e-6;
        int maxNumIterations = 10000;

        double[] decision = zeidelMethod(matrix, error, maxNumIterations);

        if (decision != null) {
            System.out.println("Ответ:");
            for (int i = 0; i < n; i++) {
                System.out.println(decision[i]);
            }
        } else {
            System.out.println("Метод не сошёлся за заданное число итераций.");
        }

    }

    public static double[] zeidelMethod(double[][] matrix, double sourceError, int maxNumIterations) {
        int n = matrix.length;

        double[] solution = new double[n]; // Массив для текущего решения
        double[] oldSolution = new double[n]; // Массив для предыдущего решения

        int iterations = 0; // Счётчик итераций
        double error = Double.MAX_VALUE;

        while (iterations < maxNumIterations && error > sourceError) {
            for (int i = 0; i < n; i++) {
                double sum = 0;

                for (int j = 0; j < n; j++) {
                    if (j != i) {
                        sum += matrix[i][j] * solution[j]; // Сумма произведений коэффициентов и значений переменных
                    }
                }

                oldSolution[i] = solution[i];
                solution[i] = (matrix[i][n] - sum) / matrix[i][i];
            }

            error = calculateError(solution, oldSolution);
            iterations++;
        }

        if (iterations < maxNumIterations) {
            return solution; // Если метод сходится, возвращаем решение СЛАУ
        } else {
            return null;
        }
    }

    public static double calculateError(double[] solution, double[] oldSolution) {
        int n = solution.length; // Получаем число переменных
        double maxError = 0; // Инициализируем максимальную погрешность

        for (int i = 0; i < n; i++) { // Проходим по всем переменным
            double error = Math.abs(solution[i] - oldSolution[i]); // Вычисляем погрешность для текущей переменной
            maxError = Math.max(maxError, error); // Получаем максимальную погрешность
        }

        return maxError; // Возвращаем максимальную погрешность
    }

}
