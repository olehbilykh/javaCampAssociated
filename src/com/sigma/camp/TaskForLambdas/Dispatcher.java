package com.sigma.camp.TaskForLambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

public class Dispatcher {
    public static void main(String[] args) {
        Integer[] array1 = {25, -6, 7, 23, -149, -11, 25, 5, -8, -21, 6, -2};
        Integer[] array2 = {-3, -5, 7, 18, -23, 164, -22, 2, -29, -16, 13};

        Predicate<Integer> isNegative = x -> x < 0;
        Predicate<Integer> isEven = x -> x % 2 == 0;

        System.out.println("Negative/Positive numbers : " +
                Handler.filterArrayByPredicate.apply(array1, isNegative));
        System.out.println("Even/Odd numbers : " +
                Handler.filterArrayByPredicate.apply(array2, isEven));

        System.out.println("Elements between arithmetic means: " +
                Handler.elementsBetweenArithmeticMeans.
                        apply(new ArrayList<>(Arrays.asList(array1, array2))));
    }
}

class Handler {
    public static BiFunction<Integer[], Predicate<Integer>, ArrayList<ArrayList<Integer>>> filterArrayByPredicate = (
            arr, predicate) -> {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> listYes = new ArrayList<>();
        ArrayList<Integer> listNo = new ArrayList<>();
        for (Integer elem : arr) {
            if (predicate.test(elem)) {
                listYes.add(elem);
            } else {
                listNo.add(elem);
            }
        }
        result.add(listYes);
        result.add(listNo);
        return result;
    };
    public static Function<Integer[], Double> arithmeticMean = arr -> {
        int res = 0;
        for (Integer elem : arr) {
            res += elem;
        }
        return (double) res / arr.length;
    };
    public static BiFunction<Integer[], Double[], ArrayList<Integer>> filter = (list, array) -> {
        ArrayList<Integer> result = new ArrayList<>();
        for (Integer elem : list) {
            if (elem > array[0] && elem < array[1])
                result.add(elem);
        }
        return result;
    };
    public static Function<ArrayList<Integer[]>, ArrayList<Integer>> elementsBetweenArithmeticMeans = (list) -> {
        ArrayList<Integer> result = new ArrayList<>();
        double arithmeticMean1 = arithmeticMean.apply(list.get(0));
        double arithmeticMean2 = arithmeticMean.apply(list.get(1));
        if (arithmeticMean1 > arithmeticMean2) {
            double temp = arithmeticMean1;
            arithmeticMean1 = arithmeticMean2;
            arithmeticMean2 = temp;
        }
        Double[] means = {arithmeticMean1, arithmeticMean2};
        System.out.println("Arithmetic mean 1: " + arithmeticMean1 + ",  Arithmetic mean 2: " + arithmeticMean2);
        result.addAll(filter.apply(list.get(0), means));
        result.addAll(filter.apply(list.get(1), means));
        return result;
    };

}