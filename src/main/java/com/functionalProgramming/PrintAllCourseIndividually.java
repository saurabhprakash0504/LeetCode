package com.functionalProgramming;

import java.util.List;

public class PrintAllCourseIndividually {

    public static void main(String[] args) {
        PrintAllCourseIndividually printAllCourseIndividually = new PrintAllCourseIndividually();
        printAllCourseIndividually.printAllCourseIndividually();
        printAllCourseIndividually.printCorsesContainingSpring();
        printAllCourseIndividually.printCorsesWithLengthMoreThan4();
    }

    void printAllCourseIndividually() {
        List<String> list = List.of("Spring", "Spring Boot", "Java", "Kafka");
        list.stream().forEach(System.out::println);
        System.out.println("");
    }

    void printCorsesContainingSpring() {
        List<String> list = List.of("Spring", "Spring Boot", "Java", "Kafka");
        list.stream().filter(s -> s.contains("Spring")).forEach(System.out::println);
        System.out.println("");
    }

    void printCorsesWithLengthMoreThan4() {
        List<String> list = List.of("Spring", "Spring Boot", "Java", "Kafka");
        list.stream().filter(s -> s.length() > 4).forEach(System.out::println);
    }
}
