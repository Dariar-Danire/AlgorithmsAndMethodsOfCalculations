package com.example.algorithmsandmethodsofcalculations.lab3;

import java.util.Scanner;

import static java.lang.Math.*;

public class NewtonRafson {

    //  cos(x + 0.5) + y - 0.8 = 0
    private static double f1(double x, double y) {
        return cos(x + 0.5) + y - 0.8;
    }

    // sin(y) + 2*x - 1.6 = 0
    private static double f2(double x, double y) {
        return sin(y) + 2*x - 1.6;
    }

    public static void solve() {
        Scanner in = new Scanner(System.in);

        int n = 2; // Я не ввожу систему с клавиатуры, значит смысла в ручном вводе n нет.

        System.out.println("Введите точность:");
        double e = in.nextDouble(); // Точность

        // Начальное приближение x и y. Найдены в результате анализа графиков функций, составляющих систему.
        double x = 0.6;
        double y = 0.4;

        double[] x_res = NewtonRafson(n, e, x, y);

        System.out.println("x = " + x_res[0]);
        System.out.println("y = " + x_res[1]);

    }

    // Для одинакового количества уравнений и переменных. Написано исключительно под систему из 2х уравнений с 2мя переменными
    // На вход: количество уравнений и переменных, точность, начальное приближение x и y.
    private static double[] NewtonRafson(int n, double e, double x, double y) {

        // Массив разниц между решением и приближением решения для x и для y
        double[] x_dif = new double[n];

        while(arithmeticMean(x_dif) < e) {
            double[] jacobi = jakobi(x, y); // Элементы матрицы Якоби
            double det = jacobi[0]*jacobi[3] - jacobi[1]*jacobi[2]; // Определитель матрицы Якоби

            // Находим миноры для каждого элемента матрицы Якоби
            double minor_d1_x = jacobi[3]/det; // А11 -> +
            double minor_d1_y = -jacobi[2]/det; // А12 -> -
            double minor_d2_x = -jacobi[1]/det; // А21 -> -
            double minor_d2_y = jacobi[0]/det; // А22 -> +

            // Находим значения уравнений системы при текущих приближениях x и y
            double f1 = f1(x, y);
            double f2 = f2(x, y);

            // Находим новые разницы между решениями и приближениями x и y
            x_dif[0] = minor_d1_x*f1 + minor_d2_x*f2;
            x_dif[1] = minor_d1_y*f1 + minor_d2_y*f2;

            // Находим новые приближения x и y
            x -= x_dif[0];
            y -= x_dif[1];
        }

        // Оборачиваем в массив приблизительные решения системы уравнений
        double[] x_res = new double[n];
        x_res[0] = x;
        x_res[1] = y;

        // Возвращаем приблизительные значения x и y
        return x_res;
    }

    // Находит среднее арифметическое модулей элементов массива
    private static double arithmeticMean(double[] arr) {
        double sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += abs(arr[i]);
        }

        return sum/arr.length;
    }

    // Находит элементы матрицы Якоби
    private static double[] jakobi(double x, double y) {
        //cos(x + 0.5) + y = 0.8
        double d1_x = -sin(x + 0.5); // Производная по x
        double d1_y = 1; // Производная по y

        // sin(y) + 2*x = 1.6
        double d2_x = 2; // Производная по x
        double d2_y = cos(y); // Производная по y

        double[] res = new double[4];
        res[0] = d1_x;
        res[1] = d1_y;
        res[2] = d2_x;
        res[3] = d2_y;

        return res;
    }
}
