package com.example.algorithmsandmethodsofcalculations.lab2;

import java.util.Scanner;

public class GaussJordan {

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

        double[][] resultMatrix = gaussJordan(matrix, n, m);

        System.out.println("Ответ:");
        for (int i = 0; i < n; i++) {
            System.out.println(resultMatrix[i][m - 1]);
        }
    }

    private static double[][] gaussJordan(double[][] matrix, int n, int m) {

        for (int i = 0; i < n; i++) {
            int maxRow = findMaxRow(matrix, i, n); // Ищем строку с максимальным первым ненулевым элементом
            if (maxRow != i) {
                double[] buffer = matrix[maxRow];
                matrix[maxRow] = matrix[i];
                matrix[i] = buffer;
            }

            double divider = matrix[i][i]; // Находим ведущий элемент теущей строки

            for (int j = i; j < m; j++) { // Делим текущую строку на ведущий элемент
                matrix[i][j] /= divider;
            }

            for (int row = 0; row < n; row++) { // Перебираем строки, которые собираемся обнулять
                if (row == i) {
                    continue;
                }

                double muilt = matrix[row][i];
                for (int j = i; j < m; j++) {
                    matrix[row][j] -= muilt * matrix[i][j];
                }
            }
        }

        return matrix;
    }

    private static int findMaxRow(double[][] matrix, int col, int rows) {
        double valMax = 0;
        int rowMax = 0;

        for (int i = 0; i < rows; i++) {
            if (matrix[i][col] > valMax) {
                valMax = matrix[i][col];
                rowMax = i;
            }
        }

        return rowMax;
    }

    private static void getMatrix(double[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }


}
