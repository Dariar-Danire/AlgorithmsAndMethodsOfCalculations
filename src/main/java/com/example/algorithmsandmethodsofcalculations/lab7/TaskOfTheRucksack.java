package com.example.algorithmsandmethodsofcalculations.lab7;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class TaskOfTheRucksack {

    public static void solve() {

        Rucksack rucksack = new Rucksack();

        List<Item> items = new ArrayList<>();
        items.add(new Item(10, 60));
        items.add(new Item(20, 100));
        items.add(new Item(30, 120));
        items.add(new Item(15, 90));
        items.add(new Item(10, 75));
        items.add(new Item(5, 50));
        items.add(new Item(5, 30));

        rucksack.fillARucksack(items);

        rucksack.unloadTheRucksack();
    }

    @Getter
    @Setter
    private static class Rucksack {
        // Объявляем максимальную вместимость рюкзака (в кг)
        private int maxCapacity;
        // Объявляем список элементов, которые в него положили
        List<Item> itemsTaken = new ArrayList<Item>();

        public  Rucksack() {
            this.maxCapacity = 50;
        }

        public Rucksack(int maxCapacity) {
            if (maxCapacity <= 0) {
                System.err.println("Недопустимый объём рюкзака!");
                return;
            }

            this.maxCapacity = maxCapacity;
        }

        public Rucksack(int maxCapacity, List<Item> itemsTaken) {
            if (maxCapacity <= 0) {
                System.err.println("Недопустимый объём рюкзака!");
                return;
            }

            double sumWeight = 0;
            for (int i = 0; i < itemsTaken.size(); i++) {
                sumWeight += itemsTaken.get(i).getWeight();
            }

            if (sumWeight > maxCapacity) {
                System.err.println("Столько в рюкзак не поместится!");
                return;
            }

            this.maxCapacity = maxCapacity;
            this.itemsTaken = itemsTaken;
        }

        public void unloadTheRucksack() {
            double maxSum = 0;

            for(int i = 0; i < this.itemsTaken.size(); i++) {
                maxSum += this.itemsTaken.get(i).cost;

                System.out.println(String.format("%d)  [%f, %d];",
                        (i + 1),
                        this.itemsTaken.get(i).getWeight(),
                        this.itemsTaken.get(i).getCost()));
            }

            System.out.println("Максимальная сумма: " + maxSum);
        }

        public void addItem(Item newItem) {
            this.itemsTaken.add(newItem);
        }

        public void fillARucksack(List<Item> sourceItems) {
            int n = sourceItems.size(); // Количество элементов
            int capacity = this.maxCapacity;

            int[][] dp = new int[n + 1][capacity + 1];

            for (int i = 1; i <= n; i++) {
                Item currentItem = sourceItems.get(i - 1);
                for (int j = 0; j <= capacity; j++) {
                    if (currentItem.getWeight() > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][(int) (j - currentItem.getWeight())] + currentItem.getCost());
                    }
                }
            }

            int maxPrice = dp[n][capacity];
            int remainingCapacity = capacity;

            for (int i = n; i > 0 && maxPrice > 0; i--) {
                if (maxPrice != dp[i - 1][remainingCapacity]) {
                    Item currentItem = sourceItems.get(i - 1);
                    addItem(currentItem);
                    maxPrice -= currentItem.getCost();
                    remainingCapacity -= (int) currentItem.getWeight();
                }
            }
        }
    }

    @Getter
    @Setter
    private static class Item {
        private double weight;
        private int cost;

        public Item(double weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }
    }

}
