package com.functionalProgramming;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class FunctionExample {

    public static void main(String[] args) {
        FunctionExample example = new FunctionExample();
        example.reduce();
    }

    void reduce() {
        List<Integer> list = List.of(4, 5, 6, 1, 2, 3);
        int val = list.stream().reduce(0, Integer::sum);

        int val3 = list.stream().reduce(0, (a, b) -> a + b);

        System.out.println("val3 >> " + val3);

        BinaryOperator<Integer> f1 = new BinaryOperator<Integer>() {


            @Override
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };

        int val2 = list.stream().reduce(0, f1);

        System.out.println("val2 >> " + val2);

    }
}
