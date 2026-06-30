package com.functionalProgramming;

import java.util.Comparator;
import java.util.List;

public class SortedAndDistinct {

    public static void main(String[] args) {
        SortedAndDistinct sortedAndDistinct = new SortedAndDistinct();
        sortedAndDistinct.printSorted();
        sortedAndDistinct.printSortedReverse();
        sortedAndDistinct.printSortedBasedOnLength();
    }

    void printSorted() {
        List<String> list = List.of("abs", "nbb", "aaa", "aa", "aaa", "zaer");
        list.stream().distinct().sorted().forEach(System.out::println);
    }

    void printSortedReverse() {
        List<String> list = List.of("abs", "nbb", "aaa", "aa", "aaa", "zaer");
        System.out.println();
        list.stream().distinct().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }

    void printSortedBasedOnLength() {
        List<String> list = List.of("abs", "nbb", "aaa", "aa", "aaa", "zaer");
        System.out.println();
        list.stream().distinct().sorted(Comparator.comparing((String s) -> s.length()).reversed()).forEach(System.out::println);
    }
}
