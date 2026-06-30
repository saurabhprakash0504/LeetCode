package com.functionalProgramming;

import java.util.List;

public class Reduce {

    public static void main(String[] args) {
        Reduce reduce = new Reduce();
        reduce.printSum();
        reduce.printMax();
        reduce.squareEveryElement();
        reduce.cubeEveryElement();
        reduce.sumOfOdd();
    }

    static int sum(int a, int b) {
        System.out.println("a " + a + " b > " + b);
        return a + b;
    }

    void printSum() {
        List<Integer> list = List.of(1, 3, 5, 3, 6, 8);
        System.out.println(list.stream().reduce(0, Reduce::sum));
        //  System.out.println(list.stream().reduce(0, (a,b) -> a+b));
        // System.out.println(list.stream().reduce(0, Integer::sum));
    }

    void printMax() {
        List<Integer> list = List.of(1, 3, 5, 3, 6, 8);
        System.out.println();
        System.out.println(list.stream().reduce(0, (a, b) -> (a > b) ? a : b));

    }

    void squareEveryElement() {
        List<Integer> list = List.of(1, 3, 5, 3, 6, 8);
        System.out.println();
        System.out.println(list.stream().map(a -> a * a).reduce(0, (a, b) -> a + b));

    }

    void cubeEveryElement() {
        List<Integer> list = List.of(1, 3, 5, 3, 6, 8);
        System.out.println();
        System.out.println(list.stream().map(a -> a * a * a).reduce(0, (a, b) -> a + b));

    }

    void sumOfOdd() {
        List<Integer> list = List.of(1, 3, 5, 3, 6, 8);
        System.out.println("odd ");
        System.out.println(list.stream().filter(a -> (a % 2 == 1)).reduce(0, (a, b) -> a + b));

    }
}
