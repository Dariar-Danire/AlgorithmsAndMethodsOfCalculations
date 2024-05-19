package com.example.algorithmsandmethodsofcalculations.lab7;

import java.util.HashMap;
import java.util.Map;

public class GreedyAlgorithm {

    public static void solve() {
        Map<String, Double> solution = greedyAlgorithm();

        if (solution.get("F(x)") == null || solution.get("x1") == null || solution.get("x2") == null || solution.get("x3") == null) {
            System.err.println("Решение не найдено!");
            return;
        }

        System.out.println("Ответ:\n" +
                "+----------+-------+\n" +
                "| Variable | Value |\n" +
                String.format("|   F(x)   |   %d   |\n", Math.round(solution.get("F(x)"))) +
                String.format("|    x1    |   %d   |\n", Math.round(solution.get("x1"))) +
                String.format("|    x2    |   %d   |\n", Math.round(solution.get("x2"))) +
                String.format("|    x3    |   %d   |\n", Math.round(solution.get("x3"))) +
                "+----------+-------+");
    }

    public static Map<String, Double> greedyAlgorithm() {
        Map<String, Double> best_solution = new HashMap<>();
        best_solution.put("x1", null);
        best_solution.put("x2", null);
        best_solution.put("x3", null);

//        Для функций, стремящихся к минимуму
//        double bestValue = Double.POSITIVE_INFINITY;

//        Для функций, стремящихся к максимуму
        double bestValue = Double.NEGATIVE_INFINITY;

        for (double x1 = 0; x1 < 100; x1 += 1) {
            for (double x2 = 0; x2 < 100; x2 += 1) {
                for (double x3 = 0; x3 < 100; x3 += 1) {
                    if (MathModel.limitation1(x1, x2, x3)) {
                        double currentValue = MathModel.F(x1, x2, x3);

                        if (currentValue > bestValue) {
                            best_solution.put("x1", x1);
                            best_solution.put("x2", x2);
                            best_solution.put("x3", x3);

                            bestValue = currentValue;
                        }
                    }
                }
            }
        }

        best_solution.put("F(x)", bestValue);
        return best_solution;
    }

}
