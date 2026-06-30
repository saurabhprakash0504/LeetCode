package com.functionalProgramming;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class BehaviourParameterization {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 43, 5, 6, 7, 8, 8);
        BehaviourParameterization behaviourParameterization = new BehaviourParameterization();
        behaviourParameterization.behavior(list, a -> a % 2 == 0);
        System.out.println();
        behaviourParameterization.behavior(list, a -> a % 2 == 1);
        System.out.println();
        behaviourParameterization.functionBehaviour(list, x -> x * x);
        System.out.println();
        behaviourParameterization.functionBehaviour(list, x -> x * x * x);

        //Example
        Predicate<Integer> p = x -> x % 2 == 0;

        Predicate<Integer> p1 = x -> {
            return x % 2 == 0;
        };

        ///

    }

    void behavior(List<Integer> list, Predicate<Integer> predicate) {

        list.stream().filter(predicate).forEach(System.out::println);
    }

    void functionBehaviour(List<Integer> list, Function<Integer, Integer> function) {

        list.stream().map(function).forEach(System.out::println);
    }
}
