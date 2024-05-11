package com.example.algorithmsandmethodsofcalculations.lab1;

import java.util.Scanner;

import static java.lang.Math.*;

public class SympsonMethod {

    private static double f(double x) {
        return 1 / (1 - 0.49 * pow(sin(x), 2));
    }

    public static void solve() {
        Scanner in = new Scanner(System.in);

        System.out.println("Введите левую границу: ");
        double a = in.nextDouble();

        System.out.println("Введите правую границу: ");
        double b = in.nextDouble();

        System.out.println("Введите точность e: ");
        double e = in.nextDouble();

        double n = 4; // Количество разбиений начинается с 2

        double Jk = sympsonMethod(a, b, n / 2);
        double Jk2 = sympsonMethod(a, b, n);
        double errorRunge = (Jk2 - Jk)/15;

        while(Math.abs(errorRunge) > e) {
            Jk = Jk2;

            n *= 2;
            Jk2 = sympsonMethod(a, b, n);

            errorRunge = abs(Jk2 - Jk)/15;

        }

        System.out.println("Погрешность: " + errorRunge);
        System.out.println("Значение интеграла: " + Jk2);
        System.out.println("Количество разбиений: " + n);
    }

    public static double sympsonMethod(double a, double b, double n) {
        double h = abs(b - a) / n;
        double J = h / 3;

        double y0 = f(a);
        double yn =  f(b);
        double sum = y0 + yn;

        double x = a + h; // Начальное значение x для вычисления y
        int k = 1; // Коэфициент k для y. y0 у нас уже есть, следовательно, мы начинаем цикл с y1

        while (x < b) {
            double yk = f(x);
            sum += (k % 2 != 0) ? (4 * yk) : (2 * yk);

            x += h;
            ++k;
        }

        return J * sum; // Возвращаем готовый интеграл
    }

}
