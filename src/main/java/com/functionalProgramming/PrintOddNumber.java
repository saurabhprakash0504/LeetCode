package com.functionalProgramming;

import java.util.List;

public class PrintOddNumber {

    public static void main(String[] args) {
        PrintOddNumber printOddNumber = new PrintOddNumber();
        printOddNumber.printOddNumber();
    }

    void printOddNumber() {
       List<Integer> list =  List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
       list.stream().filter(a1 -> a1 % 2 == 1).forEach(System.out::println);
    }
}
