package com.functionalProgramming;

import java.util.Collections;
import java.util.List;

public class Collectors {
    public static void main(String[] args) {
        Collectors c = new Collectors();
        c.findDouble();
        c.evenNum();
        c.lengthOfCourses();
    }

    void findDouble() {
        List<Integer> list = List.of(9, 9, 2, 1);
        List<Integer> tem = list.stream().map(a -> a * a).collect(java.util.stream.Collectors.toList());
        System.out.println(tem);

    }

    void evenNum() {
        List<Integer> list = List.of(9, 9, 2, 1);
        List<Integer> tem = list.stream().filter(a -> a % 2 == 0).collect(java.util.stream.Collectors.toList());
        System.out.println(tem);

    }

    void lengthOfCourses() {
        List<String> list = List.of("abc", "a", "pppppp", "yyy");
        List<Integer> tem = list.stream().map(a -> a.length()).collect(java.util.stream.Collectors.toList());
        System.out.println(tem);

    }
}
