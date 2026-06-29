package com.functionalProgramming;

import java.util.List;

public class Mapping {

    public static void main(String[] args) {
        Mapping mapping = new Mapping();
        mapping.printSquareOfNumber();
        mapping.printCudesOfOddNumber();
        mapping.printCoursesLength();
    }

    void printSquareOfNumber() {
        List<Integer> list = List.of(3, 1, 5, 7);

        list.stream().map(num -> num * num).forEach(System.out::println);
        System.out.println();
    }

    void printCudesOfOddNumber() {

        List<Integer> list = List.of(2, 3, 1, 5, 7, 4, 9);

        list.stream().filter(a -> a % 2 == 1).map(num -> num * num * num).forEach(System.out::println);
        System.out.println();

    }

    void printCoursesLength() {

        List<String> list = List.of("Spring", "Spring Boot", "Java", "Kafka");

        list.stream().map(course -> course + " " + course.length()).forEach(System.out::println);
    }
}
