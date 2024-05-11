package com.example.algorithmsandmethodsofcalculations.lab4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LagrangeMethod {

    public static void solve() {
        Scanner in = new Scanner(System.in);

        double[][] points = {{1, 7.2}, {2, 3}, {3, 4.9}, {4, 4}, {5, 3.2}};

        String polinomial = LagrangePolynomial(points);
        System.out.println("Полином Лагранжа:\n");
        printStringInChunks("L(x) = " + polinomial, 173);

        new TheardForGraph().run(points);

        System.out.println("Введите x");
        double findedX = in.nextDouble();
        System.out.println(String.format("L(%f) = %f", findedX, LagrangePolinomialInX(points, findedX)));
    }

    // Вычисляет полином Лагранжа для n точек (строка)
    private static String LagrangePolynomial(double[][] points) {

        //  Фиксирую количество точек, переданное в функцию
        int n = points.length;
        // Создаю список произведений y[i]-х и l[i]-х
        List<String> terms = new ArrayList<String>();

        // Перебираю n точек, переданных в функцию как параметр
        for (int i = 0; i < n; i++) {
            // Сразу фиксирую множитель y[i]
            String term = "(" + Double.toString(points[i][1]) + ")";

            // Перебирая все точки из массива, вычисляю базисный полином Лагранжа.
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    term += String.format(" * (x - %f)/(%f - %f)", points[j][0], points[i][0], points[j][0]);
                }
            }

            // Добавляю вычисленный элемент суммы в список таких сумм
            terms.add(term);
        }

        // Соединяю все произведения с разделителем "+"
        return String.join(" + ", terms);
    }

    // Вычисляет значение полинома Лагранжа в точке x
    public static double LagrangePolinomialInX(double[][] points, double x) {
        double result = 0;
        int n = points.length;

        // Перебираю n точек, переданных в функцию как параметр
        for (int i = 0; i < n; i++) {
            // Сразу фиксирую множитель y[i]
            double term = points[i][1];

            // Перебирая все точки из массива, вычисляю базисный полином Лагранжа для данного x[i].
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    term *= (x - points[j][0]) / (points[i][0] - points[j][0]);
                }
            }

            // Добавляю вычисленный фрагмент к конечной сумме
            result += term;
        }

        return result;
    }

    private static void printStringInChunks(String str, int chunkSize) {
        int n = str.length();
        StringBuilder chunk = new StringBuilder();

        for (int i = 0; i < n; i++) {
            chunk.append(str.charAt(i));
            if (chunk.length() == chunkSize) {
                System.out.println(chunk);
                chunk = new StringBuilder();
            }
        }

        if (chunk.length() > 0) {
            System.out.println(chunk);
        }
    }

}
