package com.sigma.camp.TaskForLambdas;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Dispatcher {
    public static Map<String, List<Integer>> groupBy(int[] array, Function<Integer, String> groupFn) {
        Map<String, List<Integer>> result = new HashMap<>();

        for(Integer val : array){
            result.computeIfAbsent(groupFn.apply(val),  key -> new LinkedList<>()).add(val);
        }

        return result;
    }
    public static <T> List<T> joinFiltering(int[] array1, int[] array2) {
        double avg1 = average(array1);
        double avg2 = average(array2);
        double min = Math.min(avg1, avg2);
        double max = Math.max(avg1, avg2);
        Predicate<Integer> filterFn = val -> val >= min && val <= max;
        List<Integer> result = new LinkedList<>();
        fill(result, array1, filterFn);
        fill(result, array2, filterFn);
        return (List<T>) result;
    }

    private static double average(int[] array) {
        double sum = 0;
        for (int val : array) {
            sum += val;
        }
        return sum / array.length;
    }

    private static void fill(List<Integer> list, int[] array, Predicate<Integer> filter) {
        for (int val : array) {
            if (filter.test(val)) {
                list.add(val);
            }
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{-1, 3, 2, 4, -6, 8};
        Function<Integer, String> fn1 = val -> val > 0 ? "positive" : "negative";
        Map<String, List<Integer>> task1 = groupBy(array, fn1);

        Function<Integer, String> fn2 = val -> val % 2 == 0? "even" : "odd";
        Map<String, List<Integer>> task2 = groupBy(array, fn2);

        System.out.println(task1);
        System.out.println(task2);

    }

}

class Car {
    private int maxSpeed;

    public Car(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public boolean isSlower(Car car) {
        return maxSpeed < car.getMaxSpeed();
    }

    @Override
    public String toString() {
        return "Car{" +
                "maxSpeed=" + maxSpeed +
                '}';
    }
}
